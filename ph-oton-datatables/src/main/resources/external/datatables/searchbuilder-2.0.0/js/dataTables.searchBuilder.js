/*! SearchBuilder 2.0.0 for DataTables
 * Copyright (c) SpryMedia Ltd - datatables.net/license
 */

(function(factory){
	if (typeof define === 'function' && define.amd) {
		// AMD
		define(['datatables.net'], function (dt) {
			return factory(window, document, dt);
		});
	}
	else if (typeof exports === 'object') {
		// CommonJS
		var cjsRequires = function (root) {
			if (! root.DataTable) {
				require('datatables.net')(root);
			}
		};

		if (typeof window === 'undefined') {
			module.exports = function (root) {
				if (! root) {
					// CommonJS environments without a window global must pass a
					// root. This will give an error otherwise
					root = window;
				}

				cjsRequires(root);
				return factory(root, root.document, root.DataTable);
			};
		}
		else {
			cjsRequires(window);
			module.exports = factory(window, window.document, window.DataTable);
		}
	}
	else {
		// Browser
		factory(window, document, window.DataTable);
	}
}(function(window, document, DataTable) {
'use strict';

var Dom = DataTable.Dom;
var Api = DataTable.Api;
var util = DataTable.util;

/** Get a moment object. Attempt to get from DataTables for module loading first. */
function moment() {
    var used = DataTable.use('moment');
    return used ? used : window.moment;
}
/** Get a luxon object. Attempt to get from DataTables for module loading first. */
function luxon() {
    var used = DataTable.use('luxon');
    return used ? used : window.luxon;
}
/**
 * The Criteria class is used within SearchBuilder to represent a search criteria
 */
class Criteria {
    constructor(table, opts, topGroup, index = 0, depth = 1, serverData = undefined, liveSearch = false) {
        this.classes = util.object.assignDeep({}, Criteria.classes);
        // Get options from user and any extra conditions/column types defined by plug-ins
        this.c = util.object.assignDeep({}, Criteria.defaults, DataTable.ext.searchBuilder, opts);
        let i18n = this.c.i18n;
        this.s = {
            condition: undefined,
            conditions: {},
            data: undefined,
            dataIdx: -1,
            dataPoints: [],
            dateFormat: false,
            depth,
            dt: table,
            filled: false,
            index,
            liveSearch: liveSearch,
            origData: undefined,
            preventRedraw: false,
            serverData,
            topGroup,
            type: '',
            value: []
        };
        this.dom = {
            buttons: Dom.c('div').classAdd(this.classes.buttonContainer),
            condition: Dom
                .c('select')
                .prop('disabled', true)
                .classAdd(this.classes.condition)
                .classAdd(this.classes.dropDown)
                .classAdd(this.classes.italic)
                .attr('autocomplete', 'hacking'),
            conditionTitle: Dom
                .c('option')
                .attr('value', '')
                .prop('disabled', true)
                .prop('selected', true)
                .prop('hidden', true)
                .html(this.s.dt.i18n('searchBuilder.condition', i18n.condition)),
            container: Dom.c('div').classAdd(this.classes.container),
            data: Dom
                .c('select')
                .classAdd(this.classes.data)
                .classAdd(this.classes.dropDown)
                .classAdd(this.classes.italic),
            dataTitle: Dom
                .c('option')
                .attr('value', '')
                .prop('disabled', true)
                .prop('selected', true)
                .prop('hidden', true)
                .html(this.s.dt.i18n('searchBuilder.data', i18n.data)),
            defaultValue: Dom
                .c('select')
                .prop('disabled', true)
                .classAdd(this.classes.value)
                .classAdd(this.classes.dropDown)
                .classAdd(this.classes.select)
                .classAdd(this.classes.italic),
            delete: Dom
                .c('button')
                .html(this.s.dt.i18n('searchBuilder.delete', i18n.delete))
                .classAdd(this.classes.delete)
                .classAdd(this.classes.button)
                .attr('title', this.s.dt.i18n('searchBuilder.deleteTitle', i18n.deleteTitle))
                .attr('type', 'button'),
            inputCont: Dom.c('div').classAdd(this.classes.inputCont),
            left: Dom
                .c('button')
                .html(this.s.dt.i18n('searchBuilder.left', i18n.left))
                .classAdd(this.classes.left)
                .classAdd(this.classes.button)
                .attr('title', this.s.dt.i18n('searchBuilder.leftTitle', i18n.leftTitle))
                .attr('type', 'button'),
            right: Dom
                .c('button')
                .html(this.s.dt.i18n('searchBuilder.right', i18n.right))
                .classAdd(this.classes.right)
                .classAdd(this.classes.button)
                .attr('title', this.s.dt.i18n('searchBuilder.rightTitle', i18n.rightTitle))
                .attr('type', 'button'),
            value: [
                Dom
                    .c('select')
                    .prop('disabled', true)
                    .classAdd(this.classes.value)
                    .classAdd(this.classes.dropDown)
                    .classAdd(this.classes.italic)
                    .classAdd(this.classes.select)
            ],
            valueTitle: Dom
                .c('option')
                .attr('value', '--valueTitle--')
                .prop('disabled', true)
                .prop('selected', true)
                .prop('hidden', true)
                .html(this.s.dt.i18n('searchBuilder.value', i18n.value))
        };
        // If the greyscale option is selected then add the class to add the grey colour to SearchBuilder
        if (this.c.greyscale) {
            this.dom.data.classAdd(this.classes.greyscale);
            this.dom.condition.classAdd(this.classes.greyscale);
            this.dom.defaultValue.classAdd(this.classes.greyscale);
            for (let val of this.dom.value) {
                val.classAdd(this.classes.greyscale);
            }
        }
        Dom.w.on('resize.dtsb', DataTable.util.throttle(() => {
            this.s.topGroup.trigger('dtsb-redrawLogic');
        }));
        this._buildCriteria();
        return this;
    }
    /**
     * Redraw the DataTable with the current search parameters
     */
    doSearch() {
        // Only do the search if live search is disabled, otherwise the search
        // is triggered by the button at the top level group.
        if (this.c.liveSearch) {
            this.s.dt.draw();
        }
    }
    /**
     * Parses formatted numbers down to a form where they can be compared.
     * Note that this does not account for different decimal characters. Use
     * parseNumber instead on the instance.
     *
     * @param val the value to convert
     * @returns the converted value
     */
    static parseNumFmt(val) {
        return +val.replace(/(?!^-)[^0-9.]/g, '');
    }
    /**
     * Adds the left button to the criteria
     */
    updateArrows(hasSiblings = false) {
        // Empty the container and append all of the elements in the correct order
        this.dom.container.children().detach();
        this.dom.container
            .append(this.dom.data)
            .append(this.dom.condition)
            .append(this.dom.inputCont);
        this.setListeners();
        // Trigger the inserted events for the value elements as they are inserted
        if (this.dom.value[0] !== undefined) {
            this.dom.value[0].trigger('dtsb-inserted');
        }
        for (let i = 1; i < this.dom.value.length; i++) {
            this.dom.inputCont.append(this.dom.value[i]);
            this.dom.value[i].trigger('dtsb-inserted');
        }
        // If this is a top level criteria then don't let it move left
        if (this.s.depth > 1) {
            this.dom.buttons.append(this.dom.left);
        }
        // If the depthLimit of the query has been hit then don't add the right button
        if ((this.c.depthLimit === false || this.s.depth < this.c.depthLimit) &&
            hasSiblings) {
            this.dom.buttons.append(this.dom.right);
        }
        else {
            this.dom.right.remove();
        }
        this.dom.buttons.append(this.dom.delete);
        this.dom.container.append(this.dom.buttons);
    }
    /**
     * Destroys the criteria, removing listeners and container from the dom
     */
    destroy() {
        // Turn off listeners
        this.dom.data.off('.dtsb');
        this.dom.condition.off('.dtsb');
        this.dom.delete.off('.dtsb');
        for (let val of this.dom.value) {
            val.off('.dtsb');
        }
        // Remove container from the dom
        this.dom.container.remove();
    }
    /**
     * Passes in the data for the row and compares it against this single criteria
     *
     * @param rowData The data for the row to be compared
     * @returns boolean Whether the criteria has passed
     */
    search(rowData, rowIdx) {
        let settings = this.s.dt.settings()[0];
        let condition = this.s.conditions[this.s.condition];
        if (this.s.condition !== undefined && condition !== undefined) {
            let filter = rowData[this.s.dataIdx];
            // This check is in place for if a custom decimal character is in place
            if (this.s.type &&
                this.s.type.includes('num') &&
                (settings.language.decimal !== '' ||
                    settings.language.thousands !== '')) {
                let splitRD = [rowData[this.s.dataIdx]];
                if (settings.language.decimal !== '') {
                    splitRD = rowData[this.s.dataIdx].split(settings.language.decimal);
                }
                if (settings.language.thousands !== '') {
                    for (let i = 0; i < splitRD.length; i++) {
                        splitRD[i] = splitRD[i].replace(settings.language.thousands, ',');
                    }
                }
                filter = splitRD.join('.');
            }
            // If orthogonal data is in place we need to get it's values for searching
            if (this.c.orthogonal.search !== 'filter') {
                filter = settings.fastData(rowIdx, this.s.dataIdx, typeof this.c.orthogonal === 'string'
                    ? this.c.orthogonal
                    : this.c.orthogonal.search);
            }
            if (this.s.type === 'array') {
                // Make sure we are working with an array
                if (!Array.isArray(filter)) {
                    filter = [filter];
                }
                filter.sort();
                for (let filt of filter) {
                    if (filt && typeof filt === 'string') {
                        filt = filt.replace(/[\r\n\u2028]/g, ' ');
                    }
                }
            }
            else if (filter !== null && typeof filter === 'string') {
                filter = filter.replace(/[\r\n\u2028]/g, ' ');
            }
            if (this.s.type.includes('html') && typeof filter === 'string') {
                filter = filter.replace(/(<([^>]+)>)/gi, '');
            }
            // Not ideal, but jqueries .val() returns an empty string even
            // when the value set is null, so we shall assume the two are equal
            if (filter === null) {
                filter = '';
            }
            return condition.search(filter, this.s.value, this);
        }
    }
    /**
     * Determine if the DataTable has return for search enabled
     *
     * @returns true if enabled
     */
    isReturnSearch() {
        let dtSettings = this.s.dt.settings()[0];
        let dtSearch = dtSettings.init.search;
        if (util.is.plainObject(dtSearch)) {
            return dtSearch.return;
        }
        return false;
    }
    /**
     * Gets the details required to rebuild the criteria
     */
    getDetails(deFormatDates = false) {
        let i;
        let settings = this.s.dt.settings()[0];
        // This check is in place for if a custom decimal character is in place
        if (this.s.type !== null &&
            ['num', 'num-fmt', 'html-num', 'html-num-fmt'].includes(this.s.type) &&
            (settings.language.decimal !== '' ||
                settings.language.thousands !== '')) {
            for (i = 0; i < this.s.value.length; i++) {
                let splitRD = [this.s.value[i].toString()];
                if (settings.language.decimal !== '') {
                    splitRD = this.s.value[i].split(settings.language.decimal);
                }
                if (settings.language.thousands !== '') {
                    for (let j = 0; j < splitRD.length; j++) {
                        splitRD[j] = splitRD[j].replace(settings.language.thousands, ',');
                    }
                }
                this.s.value[i] = splitRD.join('.');
            }
        }
        else if (this.s.type !== null && deFormatDates) {
            let momentLib = moment();
            let luxonLib = luxon();
            if ((this.s.type.includes('date') ||
                this.s.type.includes('time')) &&
                !moment &&
                !luxon) {
                for (i = 0; i < this.s.value.length; i++) {
                    if (this.s.value[i].match(/^\d{4}-([0]\d|1[0-2])-([0-2]\d|3[01])$/g) === null) {
                        this.s.value[i] = '';
                    }
                }
            }
            else if (this.s.type.includes('moment') ||
                (this.s.type.includes('datetime') && moment)) {
                for (i = 0; i < this.s.value.length; i++) {
                    if (this.s.value[i] &&
                        this.s.value[i].length > 0 &&
                        momentLib(this.s.value[i], this.s.dateFormat, true).isValid()) {
                        this.s.value[i] = momentLib(this.s.value[i], this.s.dateFormat).format('YYYY-MM-DD HH:mm:ss');
                    }
                }
            }
            else if (this.s.type.includes('luxon') ||
                (this.s.type.includes('datetime') && luxon)) {
                for (i = 0; i < this.s.value.length; i++) {
                    if (this.s.value[i] &&
                        this.s.value[i].length > 0 &&
                        luxonLib.DateTime.fromFormat(this.s.value[i], this.s.dateFormat).invalid === null) {
                        this.s.value[i] = luxonLib.DateTime.fromFormat(this.s.value[i], this.s.dateFormat).toFormat('yyyy-MM-dd HH:mm:ss');
                    }
                }
            }
        }
        if (this.s.type &&
            this.s.type.includes('num') &&
            this.s.dt.page.info().serverSide) {
            for (i = 0; i < this.s.value.length; i++) {
                this.s.value[i] = this.s.value[i].replace(/[^0-9.\-]/g, '');
            }
        }
        return {
            condition: this.s.condition,
            data: this.s.data,
            origData: this.s.origData,
            type: this.s.type,
            value: this.s.value.map(a => a !== null && a !== undefined ? a.toString() : a)
        };
    }
    /**
     * Getter for the node for the container of the criteria
     *
     * @returns Dom the node for the container
     */
    getNode() {
        return this.dom.container;
    }
    /**
     * Parses formatted numbers down to a form where they can be compared
     *
     * @param val the value to convert
     * @returns the converted value
     */
    parseNumber(val) {
        var decimal = this.s.dt.i18n('decimal', '.');
        // Remove any periods and then replace the decimal with a period
        if (decimal && decimal !== '.') {
            val = val.replace(/\./g, '').replace(decimal, '.');
        }
        return +val.replace(/(?!^-)[^0-9.]/g, '');
    }
    /**
     * Populates the criteria data, condition and value(s) as far as has been selected
     */
    populate() {
        this._populateData();
        // If the column index has been found attempt to select a condition
        if (this.s.dataIdx !== -1) {
            this._populateCondition();
            // If the condittion has been found attempt to select the values
            if (this.s.condition !== undefined) {
                this._populateValue();
            }
        }
    }
    /**
     * Rebuilds the criteria based upon the details passed in
     *
     * @param loadedCriteria the details required to rebuild the criteria
     */
    rebuild(loadedCriteria) {
        // Check to see if the previously selected data exists, if so select it
        let foundData = false;
        let dataIdx, i;
        this._populateData();
        // If a data selection has previously been made attempt to find and select it
        if (loadedCriteria.data !== undefined) {
            let italic = this.classes.italic;
            let data = this.dom.data;
            this.dom.data.children('option').each(function (opt) {
                let option = Dom.s(opt);
                if (!foundData &&
                    (option.text() === loadedCriteria.data ||
                        (loadedCriteria.origData &&
                            option.prop('origData') ===
                                loadedCriteria.origData))) {
                    option.prop('selected', true);
                    data.classRemove(italic);
                    foundData = true;
                    dataIdx = parseInt(option.val(), 10);
                }
                else {
                    option.propRemove('selected');
                }
            });
        }
        // If the data has been found and selected then the condition can be populated and searched
        if (foundData) {
            this.s.data = loadedCriteria.data;
            this.s.origData = loadedCriteria.origData;
            this.s.dataIdx = dataIdx;
            this.c.orthogonal = this._getOptions().orthogonal;
            this.dom.dataTitle.remove();
            this._populateCondition();
            this.dom.conditionTitle.remove();
            let condition;
            // Check to see if the previously selected condition exists, if so select it
            let options = this.dom.condition.children('option');
            for (i = 0; i < options.count(); i++) {
                let option = options.eq(i);
                if (loadedCriteria.condition !== undefined &&
                    option.val() === loadedCriteria.condition &&
                    typeof loadedCriteria.condition === 'string') {
                    option.prop('selected', true);
                    condition = option.val();
                }
                else {
                    option.propRemove('selected');
                }
            }
            this.s.condition = condition;
            // If the condition has been found and selected then the value can be populated and searched
            if (this.s.condition !== undefined) {
                this.dom.conditionTitle.propRemove('selected');
                this.dom.conditionTitle.remove();
                this.dom.condition.classRemove(this.classes.italic);
                for (i = 0; i < options.count(); i++) {
                    let opt = options.eq(i);
                    if (opt.val() !== this.s.condition) {
                        opt.propRemove('selected');
                    }
                }
                this._populateValue(loadedCriteria);
            }
            else {
                this.dom.conditionTitle
                    .prependTo(this.dom.condition)
                    .prop('selected', true);
            }
        }
    }
    /**
     * Sets the listeners for the criteria
     */
    setListeners() {
        this.dom.data.off('change').on('change.dtsb', () => {
            this.dom.dataTitle.propRemove('selected');
            // Need to go over every option to identify the correct selection
            let options = this.dom.data.children('option.' + this.classes.option);
            for (let i = 0; i < options.count(); i++) {
                let option = options.eq(i);
                if (option.val() === this.dom.data.val()) {
                    this.dom.data.classRemove(this.classes.italic);
                    option.prop('selected', true);
                    this.s.dataIdx = +option.val();
                    this.s.data = option.text();
                    this.s.origData = option.prop('origData').toString();
                    this.c.orthogonal = this._getOptions().orthogonal;
                    // When the data is changed, the values in condition and
                    // value may also change so need to renew them
                    this._clearCondition();
                    this._clearValue();
                    this._populateCondition();
                    // If this criteria was previously active in the search then
                    // remove it from the search and trigger a new search
                    if (this.s.filled) {
                        this.s.filled = false;
                        this.doSearch();
                        this.setListeners();
                    }
                    this.s.dt.state.save();
                }
                else {
                    option.propRemove('selected');
                }
            }
        });
        this.dom.condition.off('change').on('change.dtsb', () => {
            this.dom.conditionTitle.propRemove('selected');
            // Need to go over every option to identify the correct selection
            let options = this.dom.condition.children('option.' + this.classes.option);
            for (let i = 0; i < options.count(); i++) {
                let option = options.eq(i);
                if (option.val() === this.dom.condition.val()) {
                    this.dom.condition.classRemove(this.classes.italic);
                    option.prop('selected', true);
                    let condDisp = option.val();
                    // Find the condition that has been selected and store it internally
                    for (let cond of Object.keys(this.s.conditions)) {
                        if (cond === condDisp) {
                            this.s.condition = condDisp;
                            break;
                        }
                    }
                    // When the condition is changed, the value selector may switch between
                    // a select element and an input element
                    this._clearValue();
                    this._populateValue();
                    for (let val of this.dom.value) {
                        // If this criteria was previously active in the search then remove
                        // it from the search and trigger a new search
                        if (this.s.filled &&
                            val !== undefined &&
                            this.dom.inputCont.contains(val[0])) {
                            this.s.filled = false;
                            this.doSearch();
                            this.setListeners();
                        }
                    }
                    if (this.dom.value.length === 0 ||
                        (this.dom.value.length === 1 &&
                            this.dom.value[0] === undefined)) {
                        this.doSearch();
                    }
                }
                else {
                    option.propRemove('selected');
                }
            }
        });
    }
    setupButtons() {
        if (window.innerWidth > 550) {
            this.dom.container.classRemove(this.classes.vertical);
            this.dom.buttons.css('left', null);
            this.dom.buttons.css('top', null);
            return;
        }
        this.dom.container.classAdd(this.classes.vertical);
        this.dom.buttons.css('left', this.dom.data.width('inner') + 'px');
        this.dom.buttons.css('top', this.dom.data.position().top + 'px');
    }
    /**
     * Builds the elements of the dom together
     */
    _buildCriteria() {
        // Append Titles for select elements
        this.dom.data.append(this.dom.dataTitle);
        this.dom.condition.append(this.dom.conditionTitle);
        // Add elements to container
        this.dom.container.append(this.dom.data).append(this.dom.condition);
        this.dom.inputCont.empty();
        for (let val of this.dom.value) {
            val.append(this.dom.valueTitle);
            this.dom.inputCont.append(val);
        }
        // Add buttons to container
        this.dom.buttons.append(this.dom.delete).append(this.dom.right);
        this.dom.container.append(this.dom.inputCont).append(this.dom.buttons);
        this.setListeners();
    }
    /**
     * Clears the condition select element
     */
    _clearCondition() {
        this.dom.condition.empty();
        this.dom.conditionTitle.prop('selected', true).attr('disabled', 'true');
        this.dom.condition
            .prepend(this.dom.conditionTitle)
            .prop('selectedIndex', 0);
        this.s.conditions = {};
        this.s.condition = undefined;
    }
    /**
     * Clears the value elements
     */
    _clearValue() {
        let val;
        if (this.s.condition !== undefined) {
            if (this.dom.value.length > 0 && this.dom.value[0] !== undefined) {
                // Remove all of the value elements
                for (val of this.dom.value) {
                    if (val !== undefined) {
                        // Timeout is annoying but because of IOS
                        setTimeout(function () {
                            val.remove();
                        }, 50);
                    }
                }
            }
            // Call the init function to get the value elements for this condition
            this.dom.value = [].concat(this.s.conditions[this.s.condition].init(this, Criteria.updateListener));
            if (this.dom.value.length > 0 && this.dom.value[0] !== undefined) {
                this.dom.inputCont
                    .empty()
                    .append(this.dom.value[0])
                    .insertAfter(this.dom.condition.get(0));
                this.dom.value[0].trigger('dtsb-inserted');
                // Insert all of the value elements
                for (let i = 1; i < this.dom.value.length; i++) {
                    this.dom.inputCont.append(this.dom.value[i]);
                    this.dom.value[i].trigger('dtsb-inserted');
                }
            }
        }
        else {
            // Remove all of the value elements
            for (val of this.dom.value) {
                if (val !== undefined) {
                    // Timeout is annoying but because of IOS
                    setTimeout(function () {
                        val.remove();
                    }, 50);
                }
            }
            // Append the default valueTitle to the default select element
            this.dom.valueTitle.prop('selected', true);
            this.dom.defaultValue
                .append(this.dom.valueTitle)
                .insertAfter(this.dom.condition.get(0));
        }
        this.s.value = [];
        this.dom.value = [
            Dom
                .c('select')
                .prop('disabled', true)
                .classAdd(this.classes.value)
                .classAdd(this.classes.dropDown)
                .classAdd(this.classes.italic)
                .classAdd(this.classes.select)
                .append(this.dom.valueTitle.clone())
        ];
    }
    /**
     * Gets the options for the column
     *
     * @returns {object} The options for the column
     */
    _getOptions() {
        let table = this.s.dt;
        return util.object.assignDeep({}, Criteria.defaults, table.settings()[0].columns[this.s.dataIdx].searchBuilder);
    }
    /**
     * Populates the condition dropdown
     */
    _populateCondition() {
        let conditionOpts = [];
        let conditionsLength = Object.keys(this.s.conditions).length;
        let dt = this.s.dt;
        let colInits = dt.settings()[0].columns;
        let column = +this.dom.data.val();
        let condition, condName;
        // If there are no conditions stored then we need to get them from the
        // appropriate type
        if (conditionsLength === 0) {
            this.s.type = dt.column(column).type();
            if (colInits !== undefined) {
                let colInit = colInits[column];
                if (colInit.searchBuilderType !== undefined &&
                    colInit.searchBuilderType !== null) {
                    this.s.type = colInit.searchBuilderType;
                }
                else if (this.s.type === undefined || this.s.type === null) {
                    this.s.type = colInit.type;
                }
            }
            // If the column type is still unknown use the internal API to
            // detect type
            if (this.s.type === null || this.s.type === undefined) {
                this.s.type = dt.column(column).type();
            }
            // Enable the condition element
            this.dom.condition
                .attrRemove('disabled')
                .empty()
                .append(this.dom.conditionTitle)
                .classAdd(this.classes.italic);
            this.dom.conditionTitle.prop('selected', true);
            let decimal = dt.settings()[0].language.decimal;
            // This check is in place for if a custom decimal character is in place
            if (decimal !== '' &&
                this.s.type &&
                this.s.type.indexOf(decimal) ===
                    this.s.type.length - decimal.length) {
                if (this.s.type.includes('num-fmt')) {
                    this.s.type = this.s.type.replace(decimal, '');
                }
                else if (this.s.type.includes('num')) {
                    this.s.type = this.s.type.replace(decimal, '');
                }
            }
            // Select which conditions are going to be used based on the column type
            let conditionObj;
            if (this.c.conditions[this.s.type] !== undefined) {
                conditionObj = this.c.conditions[this.s.type];
            }
            else if (this.s.type && this.s.type === 'datetime') {
                // If no format was specified in the DT type, then we need to use
                // Moment / Luxon's default locale formatting.
                let moment = DataTable.use('moment');
                let luxon = DataTable.use('luxon');
                if (moment) {
                    conditionObj = this.c.conditions.moment;
                    this.s.dateFormat =
                        moment().creationData().locale._longDateFormat.L;
                }
                if (luxon) {
                    conditionObj = this.c.conditions.luxon;
                    this.s.dateFormat = luxon.DateTime.DATE_SHORT;
                }
            }
            else if (this.s.type && this.s.type.includes('datetime-')) {
                // Date / time data types in DataTables are driven by Luxon or
                // Moment.js.
                conditionObj = DataTable.use('moment')
                    ? this.c.conditions.moment
                    : this.c.conditions.luxon;
                this.s.dateFormat = this.s.type.replace(/datetime-/g, '');
            }
            else if (this.s.type && this.s.type.includes('moment')) {
                conditionObj = this.c.conditions.moment;
                this.s.dateFormat = this.s.type.replace(/moment-/g, '');
            }
            else if (this.s.type && this.s.type.includes('luxon')) {
                conditionObj = this.c.conditions.luxon;
                this.s.dateFormat = this.s.type.replace(/luxon-/g, '');
            }
            else {
                conditionObj = this.c.conditions.string;
            }
            // Add all of the conditions to the select element
            for (condition of Object.keys(conditionObj)) {
                if (conditionObj[condition] !== null) {
                    // Serverside processing does not supply the options for the select elements
                    // Instead input elements need to be used for these instead
                    if (dt.page.info().serverSide &&
                        conditionObj[condition].init === Criteria.initSelect) {
                        let col = colInits[column];
                        if (this.s.serverData && this.s.serverData[col.data]) {
                            conditionObj[condition].init =
                                Criteria.initSelectSSP;
                            conditionObj[condition].inputValue =
                                Criteria.inputValueSelect;
                            conditionObj[condition].isInputValid =
                                Criteria.isInputValidSelect;
                        }
                        else {
                            conditionObj[condition].init = Criteria.initInput;
                            conditionObj[condition].inputValue =
                                Criteria.inputValueInput;
                            conditionObj[condition].isInputValid =
                                Criteria.isInputValidInput;
                        }
                    }
                    this.s.conditions[condition] = conditionObj[condition];
                    condName = conditionObj[condition].conditionName;
                    if (typeof condName === 'function') {
                        condName = condName(dt, this.c.i18n);
                    }
                    conditionOpts.push(Dom
                        .c('option')
                        .text(condName)
                        .val(condition)
                        .classAdd(this.classes.option)
                        .classAdd(this.classes.notItalic));
                }
            }
        }
        // Otherwise we can just load them in
        else if (conditionsLength > 0) {
            this.dom.condition
                .empty()
                .attrRemove('disabled')
                .classAdd(this.classes.italic);
            for (condition of Object.keys(this.s.conditions)) {
                let name = this.s.conditions[condition].conditionName;
                if (typeof name === 'function') {
                    name = name(dt, this.c.i18n);
                }
                let newOpt = Dom
                    .c('option')
                    .text(name)
                    .val(condition)
                    .classAdd(this.classes.option)
                    .classAdd(this.classes.notItalic);
                if (this.s.condition !== undefined &&
                    this.s.condition === name) {
                    newOpt.prop('selected', true);
                    this.dom.condition.classRemove(this.classes.italic);
                }
                conditionOpts.push(newOpt);
            }
        }
        else {
            this.dom.condition
                .attr('disabled', 'true')
                .classAdd(this.classes.italic);
            return;
        }
        for (let opt of conditionOpts) {
            this.dom.condition.append(opt);
        }
        // Selecting a default condition if one is set
        if (colInits[column].searchBuilder &&
            colInits[column].searchBuilder.defaultCondition) {
            let defaultCondition = colInits[column].searchBuilder.defaultCondition;
            // If it is a number just use it as an index
            if (typeof defaultCondition === 'number') {
                this.dom.condition.prop('selectedIndex', defaultCondition);
                this.dom.condition.trigger('change');
            }
            // If it is a string then things get slightly more tricly
            else if (typeof defaultCondition === 'string') {
                // We need to check each condition option to see if any will match
                for (let i = 0; i < conditionOpts.length; i++) {
                    // Need to check against the stored conditions so we can match the token "cond" to the option
                    for (let cond of Object.keys(this.s.conditions)) {
                        condName = this.s.conditions[cond].conditionName;
                        if (
                        // If the conditionName matches the text of the option
                        (typeof condName === 'string'
                            ? condName
                            : condName(dt, this.c.i18n)) ===
                            conditionOpts[i].text() &&
                            // and the tokens match
                            cond === defaultCondition) {
                            // Select that option
                            this.dom.condition
                                .prop('selectedIndex', this.dom.condition
                                .children()
                                .get()
                                .indexOf(conditionOpts[i].get(0)))
                                .classRemove(this.classes.italic);
                            this.dom.condition.trigger('change');
                            i = conditionOpts.length;
                            break;
                        }
                    }
                }
            }
        }
        // If not default set then default to 0, the title
        else {
            this.dom.condition.prop('selectedIndex', 0);
        }
    }
    /**
     * Populates the data / column select element
     */
    _populateData() {
        let columns = this.s.dt.settings()[0].columns;
        let includeColumns = this.s.dt
            .columns(this.c.columns)
            .indexes()
            .toArray();
        this.dom.data.empty().append(this.dom.dataTitle);
        for (let index = 0; index < columns.length; index++) {
            // Need to check that the column can be filtered on before adding it
            if (this.c.columns === '*' || includeColumns.includes(index)) {
                let col = columns[index];
                let opt = {
                    index,
                    origData: col.data,
                    text: (col.searchBuilderTitle || col.title).replace(/(<([^>]+)>)/gi, '')
                };
                this.dom.data.append(Dom
                    .c('option')
                    .text(opt.text)
                    .val(opt.index)
                    .classAdd(this.classes.option)
                    .classAdd(this.classes.notItalic)
                    .prop('origData', col.data)
                    .prop('selected', this.s.dataIdx === opt.index ? true : false));
                if (this.s.dataIdx === opt.index) {
                    this.dom.dataTitle.propRemove('selected');
                }
            }
        }
    }
    /**
     * Populates the Value select element
     *
     * @param loadedCriteria optional, used to reload criteria from predefined filters
     */
    _populateValue(loadedCriteria) {
        let prevFilled = this.s.filled;
        let i;
        this.s.filled = false;
        // Remove any previous value elements
        // Timeout is annoying but because of IOS
        setTimeout(() => {
            this.dom.defaultValue.remove();
        }, 50);
        for (let val of this.dom.value) {
            // Timeout is annoying but because of IOS
            setTimeout(function () {
                if (val !== undefined) {
                    val.remove();
                }
            }, 50);
        }
        let children = this.dom.inputCont.children();
        if (children.count() > 1) {
            for (i = 0; i < children.count(); i++) {
                children.eq(i).remove();
            }
        }
        // Find the column with the title matching the data for the criteria and take note of the index
        if (loadedCriteria !== undefined) {
            this.s.dt.columns().every(index => {
                if (this.s.dt.settings()[0].columns[index].title ===
                    loadedCriteria.data) {
                    this.s.dataIdx = index;
                }
            });
        }
        // Initialise the value elements based on the condition
        this.dom.value = [].concat(this.s.conditions[this.s.condition].init(this, Criteria.updateListener, loadedCriteria !== undefined ? loadedCriteria.value : undefined));
        if (loadedCriteria !== undefined &&
            loadedCriteria.value !== undefined) {
            this.s.value = loadedCriteria.value;
        }
        this.dom.inputCont.empty();
        // Insert value elements and trigger the inserted event
        if (this.dom.value[0] !== undefined) {
            this.dom.value[0]
                .appendTo(this.dom.inputCont)
                .trigger('dtsb-inserted');
        }
        for (i = 1; i < this.dom.value.length; i++) {
            this.dom.value[i]
                .insertAfter(this.dom.value[i - 1].get(0))
                .trigger('dtsb-inserted');
        }
        // Check if the criteria can be used in a search
        this.s.filled = this.s.conditions[this.s.condition].isInputValid(this.dom.value, this);
        this.setListeners();
        // If it can and this is different to before then trigger a draw
        if (!this.s.preventRedraw && prevFilled !== this.s.filled) {
            // If using SSP we want to restrict the amount of server calls that take place
            //  and this will already have taken place
            if (!this.s.dt.page.info().serverSide) {
                this.doSearch();
            }
            this.setListeners();
        }
    }
    /**
     * Provides throttling capabilities to SearchBuilder without having to use dt's _fnThrottle function
     * This is because that function is not quite suitable for our needs as it runs initially rather than waiting
     *
     * @param args arguments supplied to the throttle function
     * @returns Function that is to be run that implements the throttling
     */
    _throttle(fn, frequency = 200) {
        let last = null;
        let timer = null;
        let that = this;
        if (frequency === null) {
            frequency = 200;
        }
        return function (...args) {
            let now = +new Date();
            if (last !== null && now < last + frequency) {
                clearTimeout(timer);
            }
            else {
                last = now;
            }
            timer = setTimeout(function () {
                last = null;
                fn.apply(that, args);
            }, frequency);
        };
    }
}
Criteria.classes = {
    button: 'dtsb-button',
    buttonContainer: 'dtsb-buttonContainer',
    condition: 'dtsb-condition',
    container: 'dtsb-criteria',
    data: 'dtsb-data',
    delete: 'dtsb-delete',
    dropDown: 'dtsb-dropDown',
    greyscale: 'dtsb-greyscale',
    input: 'dtsb-input',
    inputCont: 'dtsb-inputCont',
    italic: 'dtsb-italic',
    joiner: 'dtsb-joiner',
    left: 'dtsb-left',
    notItalic: 'dtsb-notItalic',
    option: 'dtsb-option',
    right: 'dtsb-right',
    select: 'dtsb-select',
    value: 'dtsb-value',
    vertical: 'dtsb-vertical'
};
/**
 * Default initialisation function for select conditions
 */
Criteria.initSelect = function (that, fn, preDefined = null, array = false) {
    let column = parseInt(that.dom.data.val());
    let indexArray = that.s.dt.rows().indexes().toArray();
    let fastData = that.s.dt.settings()[0].fastData;
    that.dom.valueTitle.prop('selected', true);
    // Declare select element to be used with all of the default classes and listeners.
    let el = Dom
        .c('select')
        .classAdd(Criteria.classes.value)
        .classAdd(Criteria.classes.dropDown)
        .classAdd(Criteria.classes.italic)
        .classAdd(Criteria.classes.select)
        .append(that.dom.valueTitle)
        .on('change.dtsb', function () {
        el.classRemove(Criteria.classes.italic);
        fn(that, this);
    });
    if (that.c.greyscale) {
        el.classAdd(Criteria.classes.greyscale);
    }
    let added = [];
    let options = [];
    // Add all of the options from the table to the select element.
    // Only add one option for each possible value
    for (let index of indexArray) {
        let filter = fastData(index, column, typeof that.c.orthogonal === 'string'
            ? that.c.orthogonal
            : that.c.orthogonal.search);
        let value = {
            filter: typeof filter === 'string'
                ? filter.replace(/[\r\n\u2028]/g, ' ') // Need to replace certain characters to match search values
                : filter,
            text: fastData(index, column, typeof that.c.orthogonal === 'string'
                ? that.c.orthogonal
                : that.c.orthogonal.display)
        };
        // If we are dealing with an array type, either make sure we are working with arrays, or sort them
        if (that.s.type === 'array') {
            value.filter = !Array.isArray(value.filter)
                ? [value.filter]
                : value.filter;
            value.text = !Array.isArray(value.text)
                ? [value.text]
                : value.text;
        }
        // Function to add an option to the select element
        let addOption = (filt, text) => {
            if (that.s.type.includes('html') &&
                filt !== null &&
                typeof filt === 'string') {
                filt.replace(/(<([^>]+)>)/gi, '');
            }
            // Add text and value, stripping out any html if that is the column type
            let opt = Dom
                .c('option')
                .attr('type', Array.isArray(filt) ? 'Array' : 'String')
                .attr('value', filt)
                .data('sbv', filt)
                .classAdd(that.classes.option)
                .classAdd(that.classes.notItalic)
                // Have to add the text this way so that special html characters are not escaped - &amp; etc.
                .html(typeof text === 'string'
                ? text.replace(/(<([^>]+)>)/gi, '')
                : text);
            let val = opt.val();
            // Check that this value has not already been added
            if (added.indexOf(val) === -1) {
                added.push(val);
                options.push(opt);
                if (preDefined !== null && Array.isArray(preDefined[0])) {
                    preDefined[0] = preDefined[0].sort().join(',');
                }
                // If this value was previously selected as indicated by preDefined, then select it again
                if (preDefined !== null && opt.val() === preDefined[0]) {
                    opt.prop('selected', true);
                    el.classRemove(Criteria.classes.italic);
                    that.dom.valueTitle.propRemove('selected');
                }
            }
        };
        // If this is to add the individual values within the array we need to loop over the array
        if (array) {
            for (let i = 0; i < value.filter.length; i++) {
                addOption(value.filter[i], value.text[i]);
            }
        }
        // Otherwise the value that is in the cell is to be added
        else {
            addOption(value.filter, Array.isArray(value.text)
                ? value.text.join(', ')
                : value.text);
        }
    }
    options.sort((a, b) => {
        if (that.s.type === 'array' ||
            that.s.type === 'string' ||
            that.s.type === 'html') {
            if (a.val() < b.val()) {
                return -1;
            }
            else if (a.val() > b.val()) {
                return 1;
            }
            else {
                return 0;
            }
        }
        else if (that.s.type === 'num' || that.s.type === 'html-num') {
            if (+a.val().replace(/(<([^>]+)>)/gi, '') <
                +b.val().replace(/(<([^>]+)>)/gi, '')) {
                return -1;
            }
            else if (+a.val().replace(/(<([^>]+)>)/gi, '') >
                +b.val().replace(/(<([^>]+)>)/gi, '')) {
                return 1;
            }
            else {
                return 0;
            }
        }
        else if (that.s.type === 'num-fmt' ||
            that.s.type === 'html-num-fmt') {
            if (+a.val().replace(/[^0-9.]/g, '') <
                +b.val().replace(/[^0-9.]/g, '')) {
                return -1;
            }
            else if (+a.val().replace(/[^0-9.]/g, '') >
                +b.val().replace(/[^0-9.]/g, '')) {
                return 1;
            }
            else {
                return 0;
            }
        }
    });
    for (let opt of options) {
        el.append(opt);
    }
    return el;
};
/**
 * Default initialisation function for select conditions
 */
Criteria.initSelectSSP = function (that, fn, preDefined = null) {
    that.dom.valueTitle.prop('selected', true);
    // Declare select element to be used with all of the default classes and listeners.
    let el = Dom
        .c('select')
        .classAdd(Criteria.classes.value)
        .classAdd(Criteria.classes.dropDown)
        .classAdd(Criteria.classes.italic)
        .classAdd(Criteria.classes.select)
        .append(that.dom.valueTitle)
        .on('change.dtsb', function () {
        el.classRemove(Criteria.classes.italic);
        fn(that, this);
    });
    if (that.c.greyscale) {
        el.classAdd(Criteria.classes.greyscale);
    }
    let options = [];
    for (let option of that.s.serverData[that.s.origData]) {
        let value = option.value;
        let label = option.label;
        // Function to add an option to the select element
        let addOption = (filt, text) => {
            if (that.s.type.includes('html') &&
                filt !== null &&
                typeof filt === 'string') {
                filt.replace(/(<([^>]+)>)/gi, '');
            }
            // Add text and value, stripping out any html if that is the column type
            let opt = Dom
                .c('option')
                .attr('type', Array.isArray(filt) ? 'Array' : 'String')
                .attr('value', filt)
                .data('sbv', filt)
                .classAdd(that.classes.option)
                .classAdd(that.classes.notItalic)
                // Have to add the text this way so that special html characters are not escaped - &amp; etc.
                .html(typeof text === 'string'
                ? text.replace(/(<([^>]+)>)/gi, '')
                : text);
            options.push(opt);
            // If this value was previously selected as indicated by preDefined, then select it again
            if (preDefined !== null && opt.val() === preDefined[0]) {
                opt.prop('selected', true);
                el.classRemove(Criteria.classes.italic);
                that.dom.valueTitle.propRemove('selected');
            }
        };
        addOption(value, label);
    }
    for (let opt of options) {
        el.append(opt);
    }
    return el;
};
/**
 * Default initialisation function for select array conditions
 *
 * This exists because there needs to be different select functionality for contains/without and equals/not
 */
Criteria.initSelectArray = function (that, fn, preDefined = null) {
    return Criteria.initSelect(that, fn, preDefined, true);
};
/**
 * Default initialisation function for input conditions
 */
Criteria.initInput = function (that, fn, preDefined = null) {
    // Declare the input element
    let searchDelay = that.s.dt.settings()[0].searchDelay;
    let el = Dom
        .c('input')
        .classAdd(Criteria.classes.value)
        .classAdd(Criteria.classes.input)
        .on('input.dtsb keypress.dtsb', that._throttle(function (e) {
        let code = e.keyCode || e.which;
        return fn(that, this, code);
    }, searchDelay === null ? 100 : searchDelay));
    if (that.c.greyscale) {
        el.classAdd(Criteria.classes.greyscale);
    }
    // If there is a preDefined value then add it
    if (preDefined !== null) {
        el.val(preDefined[0]);
    }
    // This is add responsive functionality to the logic button without redrawing everything else
    that.s.dt.one('draw.dtsb', () => {
        that.s.topGroup.trigger('dtsb-redrawLogic');
    });
    return el;
};
/**
 * Default initialisation function for conditions requiring 2 inputs
 */
Criteria.init2Input = function (that, fn, preDefined = null) {
    // Declare all of the necessary jQuery elements
    let searchDelay = that.s.dt.settings()[0].searchDelay;
    let els = [
        Dom
            .c('input')
            .classAdd(Criteria.classes.value)
            .classAdd(Criteria.classes.input)
            .on('input.dtsb keypress.dtsb', that._throttle(function (e) {
            let code = e.keyCode || e.which;
            return fn(that, this, code);
        }, searchDelay === null ? 100 : searchDelay)),
        Dom
            .c('span')
            .classAdd(that.classes.joiner)
            .html(that.s.dt.i18n('searchBuilder.valueJoiner', that.c.i18n.valueJoiner)),
        Dom
            .c('input')
            .classAdd(Criteria.classes.value)
            .classAdd(Criteria.classes.input)
            .on('input.dtsb keypress.dtsb', that._throttle(function (e) {
            let code = e.keyCode || e.which;
            return fn(that, this, code);
        }, searchDelay === null ? 100 : searchDelay))
    ];
    if (that.c.greyscale) {
        els[0].classAdd(Criteria.classes.greyscale);
        els[2].classAdd(Criteria.classes.greyscale);
    }
    // If there is a preDefined value then add it
    if (preDefined !== null) {
        els[0].val(preDefined[0]);
        els[2].val(preDefined[1]);
    }
    // This is add responsive functionality to the logic button without redrawing everything else
    that.s.dt.one('draw.dtsb', () => {
        that.s.topGroup.trigger('dtsb-redrawLogic');
    });
    return els;
};
/**
 * Default initialisation function for date conditions
 */
Criteria.initDate = function (that, fn, preDefined = null) {
    let searchDelay = that.s.dt.settings()[0].searchDelay;
    let i18n = that.s.dt.i18n('datetime', {}, false);
    // Declare date element using DataTables dateTime plugin
    let el = Dom
        .c('input')
        .classAdd(Criteria.classes.value)
        .classAdd(Criteria.classes.input)
        .on('change.dtsb', that._throttle(function () {
        return fn(that, this);
    }, searchDelay === null ? 100 : searchDelay))
        .on('input.dtsb keypress.dtsb', e => {
        that._throttle(function () {
            let code = e.keyCode || e.which;
            return fn(that, this, code);
        }, searchDelay === null ? 100 : searchDelay);
    });
    let DatePicker = DataTable.use('datetime');
    if (DatePicker) {
        new DatePicker(el.get(0), {
            format: that.s.dateFormat ? that.s.dateFormat : undefined,
            i18n
        });
    }
    if (that.c.greyscale) {
        el.classAdd(Criteria.classes.greyscale);
    }
    // If there is a preDefined value then add it
    if (preDefined !== null) {
        el.val(preDefined[0]);
    }
    // This is add responsive functionality to the logic button without redrawing everything else
    that.s.dt.one('draw.dtsb', () => {
        that.s.topGroup.trigger('dtsb-redrawLogic');
    });
    return el;
};
Criteria.initNoValue = function (that) {
    // This is add responsive functionality to the logic button without redrawing everything else
    that.s.dt.one('draw.dtsb', () => {
        that.s.topGroup.trigger('dtsb-redrawLogic');
    });
    return [];
};
Criteria.init2Date = function (that, fn, preDefined = null) {
    let dtContext = that.s.dt.settings()[0];
    let searchDelay = dtContext.searchDelay;
    let searchReturn = that.isReturnSearch();
    let i18n = that.s.dt.i18n('datetime', {}, false);
    // Declare all of the date elements that are required using DataTables dateTime plugin
    let els = [
        Dom
            .c('input')
            .classAdd(Criteria.classes.value)
            .classAdd(Criteria.classes.input)
            .on('change.dtsb', searchDelay !== null
            ? DataTable.util.throttle(function () {
                return fn(that, this);
            }, searchDelay)
            : () => {
                fn(that, this);
            })
            .on('input.dtsb keypress.dtsb', e => {
            DataTable.util.throttle(function () {
                let code = e.keyCode || e.which;
                return fn(that, this, code);
            }, searchDelay === null ? 0 : searchDelay);
        }),
        Dom
            .c('span')
            .classAdd(that.classes.joiner)
            .html(that.s.dt.i18n('searchBuilder.valueJoiner', that.c.i18n.valueJoiner)),
        Dom
            .c('input')
            .classAdd(Criteria.classes.value)
            .classAdd(Criteria.classes.input)
            .on('change.dtsb', searchDelay !== null
            ? DataTable.util.throttle(function () {
                return fn(that, this);
            }, searchDelay)
            : () => {
                fn(that, this);
            })
            .on('input.dtsb keypress.dtsb', !that.c.enterSearch && !searchReturn && searchDelay !== null
            ? DataTable.util.throttle(function () {
                return fn(that, this);
            }, searchDelay)
            : e => {
                let code = e.keyCode || e.which;
                fn(that, this, code);
            })
    ];
    let DatePicker = DataTable.use('datetime');
    if (DatePicker) {
        new DatePicker(els[0], {
            format: that.s.dateFormat ? that.s.dateFormat : undefined,
            i18n
        });
        new DatePicker(els[2], {
            format: that.s.dateFormat ? that.s.dateFormat : undefined,
            i18n
        });
    }
    if (that.c.greyscale) {
        els[0].classAdd(Criteria.classes.greyscale);
        els[2].classAdd(Criteria.classes.greyscale);
    }
    // If there are and preDefined values then add them
    if (preDefined !== null && preDefined.length > 0) {
        els[0].val(preDefined[0]);
        els[2].val(preDefined[1]);
    }
    // This is add responsive functionality to the logic button without redrawing everything else
    that.s.dt.one('draw.dtsb', () => {
        that.s.topGroup.trigger('dtsb-redrawLogic');
    });
    return els;
};
/**
 * Default function for select elements to validate condition
 */
Criteria.isInputValidSelect = function (el) {
    let allFilled = true;
    // Check each element to make sure that the selections are valid
    for (let element of el) {
        let options = element
            .children('option')
            .get();
        let selected = options.filter(e => e.selected);
        let notItalic = element
            .children('option.' + Criteria.classes.notItalic)
            .get();
        if (selected.length === options.length - notItalic.length &&
            selected.length === 1 &&
            selected[0] === options[0]) {
            allFilled = false;
        }
    }
    return allFilled;
};
/**
 * Default function for input and date elements to validate condition
 */
Criteria.isInputValidInput = function (el) {
    let allFilled = true;
    // Check each element to make sure that the inputs are valid
    for (let element of el) {
        if (element.is('input') && element.val().length === 0) {
            allFilled = false;
        }
    }
    return allFilled;
};
/**
 * Default function for getting select conditions
 */
Criteria.inputValueSelect = function (el) {
    let values = [];
    // Go through the select elements and push each selected option to the return array
    for (let element of el) {
        if (element.is('select')) {
            let escapedItems = []
                .concat(element
                .children('option')
                .filter(o => o.selected)
                .data('sbv'))
                .map(item => util.escapeHtml(item));
            values.push(...escapedItems);
        }
    }
    return values;
};
/**
 * Default function for getting input conditions
 */
Criteria.inputValueInput = function (el) {
    let values = [];
    // Go through the input elements and push each value to the return array
    for (let element of el) {
        if (element.is('input')) {
            values.push(util.escapeHtml(element.val()));
        }
    }
    return values.map(v => {
        return DataTable.util.diacritics(v);
    });
};
/**
 * Function that is run on each element as a call back when a search should be triggered
 */
Criteria.updateListener = function (that, el, code) {
    // When the value is changed the criteria is now complete so can be included in searches
    // Get the condition from the map based on the key that has been selected for the condition
    let condition = that.s.conditions[that.s.condition];
    let i;
    that.s.filled = condition.isInputValid(that.dom.value, that);
    that.s.value = condition.inputValue(that.dom.value, that);
    if (!that.s.filled) {
        if ((!that.c.enterSearch && !that.isReturnSearch()) ||
            code === 13) {
            that.doSearch();
        }
        return;
    }
    if (!Array.isArray(that.s.value)) {
        that.s.value = [that.s.value];
    }
    for (i = 0; i < that.s.value.length; i++) {
        // If the value is an array we need to sort it
        if (Array.isArray(that.s.value[i])) {
            that.s.value[i].sort();
        }
    }
    // Take note of the cursor position so that we can refocus there later
    let idx = null;
    let cursorPos = null;
    for (i = 0; i < that.dom.value.length; i++) {
        if (el === that.dom.value[i][0]) {
            idx = i;
            if (el.selectionStart !== undefined) {
                cursorPos = el.selectionStart;
            }
        }
    }
    if ((!that.c.enterSearch && !that.isReturnSearch()) ||
        code === 13 ||
        code === undefined || // A click triggered it
        (el.nodeName && el.nodeName.toLowerCase() === 'select')) {
        // Trigger a search
        that.doSearch();
    }
    // Refocus the element and set the correct cursor position
    if (idx !== null) {
        that.dom.value[idx].classRemove(that.classes.italic);
        that.dom.value[idx].focus();
        if (cursorPos !== null) {
            that.dom.value[idx][0].setSelectionRange(cursorPos, cursorPos);
        }
    }
};
// The order of the conditions will make eslint sad :(
// Has to be in this order so that they are displayed correctly in select elements
// Also have to disable member ordering for this as the private methods used are not yet declared otherwise
Criteria.dateConditions = {
    '=': {
        conditionName(dt, i18n) {
            return dt.i18n('searchBuilder.conditions.date.equals', i18n.conditions.date.equals);
        },
        init: Criteria.initDate,
        inputValue: Criteria.inputValueInput,
        isInputValid: Criteria.isInputValidInput,
        search(value, comparison) {
            value = value.replace(/(\/|-|,)/g, '-');
            return value === comparison[0];
        }
    },
    '!=': {
        conditionName(dt, i18n) {
            return dt.i18n('searchBuilder.conditions.date.not', i18n.conditions.date.not);
        },
        init: Criteria.initDate,
        inputValue: Criteria.inputValueInput,
        isInputValid: Criteria.isInputValidInput,
        search(value, comparison) {
            value = value.replace(/(\/|-|,)/g, '-');
            return value !== comparison[0];
        }
    },
    '<': {
        conditionName(dt, i18n) {
            return dt.i18n('searchBuilder.conditions.date.before', i18n.conditions.date.before);
        },
        init: Criteria.initDate,
        inputValue: Criteria.inputValueInput,
        isInputValid: Criteria.isInputValidInput,
        search(value, comparison) {
            value = value.replace(/(\/|-|,)/g, '-');
            return value < comparison[0];
        }
    },
    '>': {
        conditionName(dt, i18n) {
            return dt.i18n('searchBuilder.conditions.date.after', i18n.conditions.date.after);
        },
        init: Criteria.initDate,
        inputValue: Criteria.inputValueInput,
        isInputValid: Criteria.isInputValidInput,
        search(value, comparison) {
            value = value.replace(/(\/|-|,)/g, '-');
            return value > comparison[0];
        }
    },
    between: {
        conditionName(dt, i18n) {
            return dt.i18n('searchBuilder.conditions.date.between', i18n.conditions.date.between);
        },
        init: Criteria.init2Date,
        inputValue: Criteria.inputValueInput,
        isInputValid: Criteria.isInputValidInput,
        search(value, comparison) {
            value = value.replace(/(\/|-|,)/g, '-');
            if (comparison[0] < comparison[1]) {
                return comparison[0] <= value && value <= comparison[1];
            }
            else {
                return comparison[1] <= value && value <= comparison[0];
            }
        }
    },
    '!between': {
        conditionName(dt, i18n) {
            return dt.i18n('searchBuilder.conditions.date.notBetween', i18n.conditions.date.notBetween);
        },
        init: Criteria.init2Date,
        inputValue: Criteria.inputValueInput,
        isInputValid: Criteria.isInputValidInput,
        search(value, comparison) {
            value = value.replace(/(\/|-|,)/g, '-');
            if (comparison[0] < comparison[1]) {
                return !(comparison[0] <= value && value <= comparison[1]);
            }
            else {
                return !(comparison[1] <= value && value <= comparison[0]);
            }
        }
    },
    null: {
        conditionName(dt, i18n) {
            return dt.i18n('searchBuilder.conditions.date.empty', i18n.conditions.date.empty);
        },
        init: Criteria.initNoValue,
        inputValue() {
            return [];
        },
        isInputValid() {
            return true;
        },
        search(value) {
            return (value === null || value === undefined || value.length === 0);
        }
    },
    '!null': {
        conditionName(dt, i18n) {
            return dt.i18n('searchBuilder.conditions.date.notEmpty', i18n.conditions.date.notEmpty);
        },
        init: Criteria.initNoValue,
        inputValue() {
            return [];
        },
        isInputValid() {
            return true;
        },
        search(value) {
            return !(value === null ||
                value === undefined ||
                value.length === 0);
        }
    }
};
// The order of the conditions will make eslint sad :(
// Has to be in this order so that they are displayed correctly in select elements
// Also have to disable member ordering for this as the private methods used are not yet declared otherwise
Criteria.momentDateConditions = {
    '=': {
        conditionName(dt, i18n) {
            return dt.i18n('searchBuilder.conditions.date.equals', i18n.conditions.date.equals);
        },
        init: Criteria.initDate,
        inputValue: Criteria.inputValueInput,
        isInputValid: Criteria.isInputValidInput,
        search(value, comparison, that) {
            return (moment()(value, that.s.dateFormat).valueOf() ===
                moment()(comparison[0], that.s.dateFormat).valueOf());
        }
    },
    '!=': {
        conditionName(dt, i18n) {
            return dt.i18n('searchBuilder.conditions.date.not', i18n.conditions.date.not);
        },
        init: Criteria.initDate,
        inputValue: Criteria.inputValueInput,
        isInputValid: Criteria.isInputValidInput,
        search(value, comparison, that) {
            return (moment()(value, that.s.dateFormat).valueOf() !==
                moment()(comparison[0], that.s.dateFormat).valueOf());
        }
    },
    '<': {
        conditionName(dt, i18n) {
            return dt.i18n('searchBuilder.conditions.date.before', i18n.conditions.date.before);
        },
        init: Criteria.initDate,
        inputValue: Criteria.inputValueInput,
        isInputValid: Criteria.isInputValidInput,
        search(value, comparison, that) {
            return (moment()(value, that.s.dateFormat).valueOf() <
                moment()(comparison[0], that.s.dateFormat).valueOf());
        }
    },
    '>': {
        conditionName(dt, i18n) {
            return dt.i18n('searchBuilder.conditions.date.after', i18n.conditions.date.after);
        },
        init: Criteria.initDate,
        inputValue: Criteria.inputValueInput,
        isInputValid: Criteria.isInputValidInput,
        search(value, comparison, that) {
            return (moment()(value, that.s.dateFormat).valueOf() >
                moment()(comparison[0], that.s.dateFormat).valueOf());
        }
    },
    between: {
        conditionName(dt, i18n) {
            return dt.i18n('searchBuilder.conditions.date.between', i18n.conditions.date.between);
        },
        init: Criteria.init2Date,
        inputValue: Criteria.inputValueInput,
        isInputValid: Criteria.isInputValidInput,
        search(value, comparison, that) {
            let val = moment()(value, that.s.dateFormat).valueOf();
            let comp0 = moment()(comparison[0], that.s.dateFormat).valueOf();
            let comp1 = moment()(comparison[1], that.s.dateFormat).valueOf();
            if (comp0 < comp1) {
                return comp0 <= val && val <= comp1;
            }
            else {
                return comp1 <= val && val <= comp0;
            }
        }
    },
    '!between': {
        conditionName(dt, i18n) {
            return dt.i18n('searchBuilder.conditions.date.notBetween', i18n.conditions.date.notBetween);
        },
        init: Criteria.init2Date,
        inputValue: Criteria.inputValueInput,
        isInputValid: Criteria.isInputValidInput,
        search(value, comparison, that) {
            let val = moment()(value, that.s.dateFormat).valueOf();
            let comp0 = moment()(comparison[0], that.s.dateFormat).valueOf();
            let comp1 = moment()(comparison[1], that.s.dateFormat).valueOf();
            if (comp0 < comp1) {
                return !(+comp0 <= +val && +val <= +comp1);
            }
            else {
                return !(+comp1 <= +val && +val <= +comp0);
            }
        }
    },
    null: {
        conditionName(dt, i18n) {
            return dt.i18n('searchBuilder.conditions.date.empty', i18n.conditions.date.empty);
        },
        init: Criteria.initNoValue,
        inputValue() {
            return [];
        },
        isInputValid() {
            return true;
        },
        search(value) {
            return (value === null || value === undefined || value.length === 0);
        }
    },
    '!null': {
        conditionName(dt, i18n) {
            return dt.i18n('searchBuilder.conditions.date.notEmpty', i18n.conditions.date.notEmpty);
        },
        init: Criteria.initNoValue,
        inputValue() {
            return [];
        },
        isInputValid() {
            return true;
        },
        search(value) {
            return !(value === null ||
                value === undefined ||
                value.length === 0);
        }
    }
};
// The order of the conditions will make eslint sad :(
// Has to be in this order so that they are displayed correctly in select elements
// Also have to disable member ordering for this as the private methods used are not yet declared otherwise
Criteria.luxonDateConditions = {
    '=': {
        conditionName(dt, i18n) {
            return dt.i18n('searchBuilder.conditions.date.equals', i18n.conditions.date.equals);
        },
        init: Criteria.initDate,
        inputValue: Criteria.inputValueInput,
        isInputValid: Criteria.isInputValidInput,
        search(value, comparison, that) {
            return (luxon().DateTime.fromFormat(value, that.s.dateFormat).ts ===
                luxon().DateTime.fromFormat(comparison[0], that.s.dateFormat).ts);
        }
    },
    '!=': {
        conditionName(dt, i18n) {
            return dt.i18n('searchBuilder.conditions.date.not', i18n.conditions.date.not);
        },
        init: Criteria.initDate,
        inputValue: Criteria.inputValueInput,
        isInputValid: Criteria.isInputValidInput,
        search(value, comparison, that) {
            return (luxon().DateTime.fromFormat(value, that.s.dateFormat).ts !==
                luxon().DateTime.fromFormat(comparison[0], that.s.dateFormat).ts);
        }
    },
    '<': {
        conditionName(dt, i18n) {
            return dt.i18n('searchBuilder.conditions.date.before', i18n.conditions.date.before);
        },
        init: Criteria.initDate,
        inputValue: Criteria.inputValueInput,
        isInputValid: Criteria.isInputValidInput,
        search(value, comparison, that) {
            return (luxon().DateTime.fromFormat(value, that.s.dateFormat).ts <
                luxon().DateTime.fromFormat(comparison[0], that.s.dateFormat).ts);
        }
    },
    '>': {
        conditionName(dt, i18n) {
            return dt.i18n('searchBuilder.conditions.date.after', i18n.conditions.date.after);
        },
        init: Criteria.initDate,
        inputValue: Criteria.inputValueInput,
        isInputValid: Criteria.isInputValidInput,
        search(value, comparison, that) {
            return (luxon().DateTime.fromFormat(value, that.s.dateFormat).ts >
                luxon().DateTime.fromFormat(comparison[0], that.s.dateFormat).ts);
        }
    },
    between: {
        conditionName(dt, i18n) {
            return dt.i18n('searchBuilder.conditions.date.between', i18n.conditions.date.between);
        },
        init: Criteria.init2Date,
        inputValue: Criteria.inputValueInput,
        isInputValid: Criteria.isInputValidInput,
        search(value, comparison, that) {
            let val = luxon().DateTime.fromFormat(value, that.s.dateFormat).ts;
            let comp0 = luxon().DateTime.fromFormat(comparison[0], that.s.dateFormat).ts;
            let comp1 = luxon().DateTime.fromFormat(comparison[1], that.s.dateFormat).ts;
            if (comp0 < comp1) {
                return comp0 <= val && val <= comp1;
            }
            else {
                return comp1 <= val && val <= comp0;
            }
        }
    },
    '!between': {
        conditionName(dt, i18n) {
            return dt.i18n('searchBuilder.conditions.date.notBetween', i18n.conditions.date.notBetween);
        },
        init: Criteria.init2Date,
        inputValue: Criteria.inputValueInput,
        isInputValid: Criteria.isInputValidInput,
        search(value, comparison, that) {
            let val = luxon().DateTime.fromFormat(value, that.s.dateFormat).ts;
            let comp0 = luxon().DateTime.fromFormat(comparison[0], that.s.dateFormat).ts;
            let comp1 = luxon().DateTime.fromFormat(comparison[1], that.s.dateFormat).ts;
            if (comp0 < comp1) {
                return !(+comp0 <= +val && +val <= +comp1);
            }
            else {
                return !(+comp1 <= +val && +val <= +comp0);
            }
        }
    },
    null: {
        conditionName(dt, i18n) {
            return dt.i18n('searchBuilder.conditions.date.empty', i18n.conditions.date.empty);
        },
        init: Criteria.initNoValue,
        inputValue() {
            return [];
        },
        isInputValid() {
            return true;
        },
        search(value) {
            return (value === null || value === undefined || value.length === 0);
        }
    },
    '!null': {
        conditionName(dt, i18n) {
            return dt.i18n('searchBuilder.conditions.date.notEmpty', i18n.conditions.date.notEmpty);
        },
        init: Criteria.initNoValue,
        inputValue() {
            return [];
        },
        isInputValid() {
            return true;
        },
        search(value) {
            return !(value === null ||
                value === undefined ||
                value.length === 0);
        }
    }
};
// The order of the conditions will make eslint sad :(
// Has to be in this order so that they are displayed correctly in select elements
// Also have to disable member ordering for this as the private methods used are not yet declared otherwise
Criteria.numConditions = {
    '=': {
        conditionName(dt, i18n) {
            return dt.i18n('searchBuilder.conditions.number.equals', i18n.conditions.number.equals);
        },
        init: Criteria.initSelect,
        inputValue: Criteria.inputValueSelect,
        isInputValid: Criteria.isInputValidSelect,
        search(value, comparison) {
            return +value === +comparison[0];
        }
    },
    '!=': {
        conditionName(dt, i18n) {
            return dt.i18n('searchBuilder.conditions.number.not', i18n.conditions.number.not);
        },
        init: Criteria.initSelect,
        inputValue: Criteria.inputValueSelect,
        isInputValid: Criteria.isInputValidSelect,
        search(value, comparison) {
            return +value !== +comparison[0];
        }
    },
    '<': {
        conditionName(dt, i18n) {
            return dt.i18n('searchBuilder.conditions.number.lt', i18n.conditions.number.lt);
        },
        init: Criteria.initInput,
        inputValue: Criteria.inputValueInput,
        isInputValid: Criteria.isInputValidInput,
        search(value, comparison) {
            return +value < +comparison[0];
        }
    },
    '<=': {
        conditionName(dt, i18n) {
            return dt.i18n('searchBuilder.conditions.number.lte', i18n.conditions.number.lte);
        },
        init: Criteria.initInput,
        inputValue: Criteria.inputValueInput,
        isInputValid: Criteria.isInputValidInput,
        search(value, comparison) {
            return +value <= +comparison[0];
        }
    },
    '>=': {
        conditionName(dt, i18n) {
            return dt.i18n('searchBuilder.conditions.number.gte', i18n.conditions.number.gte);
        },
        init: Criteria.initInput,
        inputValue: Criteria.inputValueInput,
        isInputValid: Criteria.isInputValidInput,
        search(value, comparison) {
            return +value >= +comparison[0];
        }
    },
    '>': {
        conditionName(dt, i18n) {
            return dt.i18n('searchBuilder.conditions.number.gt', i18n.conditions.number.gt);
        },
        init: Criteria.initInput,
        inputValue: Criteria.inputValueInput,
        isInputValid: Criteria.isInputValidInput,
        search(value, comparison) {
            return +value > +comparison[0];
        }
    },
    between: {
        conditionName(dt, i18n) {
            return dt.i18n('searchBuilder.conditions.number.between', i18n.conditions.number.between);
        },
        init: Criteria.init2Input,
        inputValue: Criteria.inputValueInput,
        isInputValid: Criteria.isInputValidInput,
        search(value, comparison) {
            if (+comparison[0] < +comparison[1]) {
                return +comparison[0] <= +value && +value <= +comparison[1];
            }
            else {
                return +comparison[1] <= +value && +value <= +comparison[0];
            }
        }
    },
    '!between': {
        conditionName(dt, i18n) {
            return dt.i18n('searchBuilder.conditions.number.notBetween', i18n.conditions.number.notBetween);
        },
        init: Criteria.init2Input,
        inputValue: Criteria.inputValueInput,
        isInputValid: Criteria.isInputValidInput,
        search(value, comparison) {
            if (+comparison[0] < +comparison[1]) {
                return !(+comparison[0] <= +value && +value <= +comparison[1]);
            }
            else {
                return !(+comparison[1] <= +value && +value <= +comparison[0]);
            }
        }
    },
    null: {
        conditionName(dt, i18n) {
            return dt.i18n('searchBuilder.conditions.number.empty', i18n.conditions.number.empty);
        },
        init: Criteria.initNoValue,
        inputValue() {
            return [];
        },
        isInputValid() {
            return true;
        },
        search(value) {
            return (value === null || value === undefined || value.length === 0);
        }
    },
    '!null': {
        conditionName(dt, i18n) {
            return dt.i18n('searchBuilder.conditions.number.notEmpty', i18n.conditions.number.notEmpty);
        },
        init: Criteria.initNoValue,
        inputValue() {
            return [];
        },
        isInputValid() {
            return true;
        },
        search(value) {
            return !(value === null ||
                value === undefined ||
                value.length === 0);
        }
    }
};
// The order of the conditions will make eslint sad :(
// Has to be in this order so that they are displayed correctly in select elements
// Also have to disable member ordering for this as the private methods used are not yet declared otherwise
Criteria.numFmtConditions = {
    '=': {
        conditionName(dt, i18n) {
            return dt.i18n('searchBuilder.conditions.number.equals', i18n.conditions.number.equals);
        },
        init: Criteria.initSelect,
        inputValue: Criteria.inputValueSelect,
        isInputValid: Criteria.isInputValidSelect,
        search(value, comparison, criteria) {
            return (criteria.parseNumber(value) ===
                criteria.parseNumber(comparison[0]));
        }
    },
    '!=': {
        conditionName(dt, i18n) {
            return dt.i18n('searchBuilder.conditions.number.not', i18n.conditions.number.not);
        },
        init: Criteria.initSelect,
        inputValue: Criteria.inputValueSelect,
        isInputValid: Criteria.isInputValidSelect,
        search(value, comparison, criteria) {
            return (criteria.parseNumber(value) !==
                criteria.parseNumber(comparison[0]));
        }
    },
    '<': {
        conditionName(dt, i18n) {
            return dt.i18n('searchBuilder.conditions.number.lt', i18n.conditions.number.lt);
        },
        init: Criteria.initInput,
        inputValue: Criteria.inputValueInput,
        isInputValid: Criteria.isInputValidInput,
        search(value, comparison, criteria) {
            return (criteria.parseNumber(value) <
                criteria.parseNumber(comparison[0]));
        }
    },
    '<=': {
        conditionName(dt, i18n) {
            return dt.i18n('searchBuilder.conditions.number.lte', i18n.conditions.number.lte);
        },
        init: Criteria.initInput,
        inputValue: Criteria.inputValueInput,
        isInputValid: Criteria.isInputValidInput,
        search(value, comparison, criteria) {
            return (criteria.parseNumber(value) <=
                criteria.parseNumber(comparison[0]));
        }
    },
    '>=': {
        conditionName(dt, i18n) {
            return dt.i18n('searchBuilder.conditions.number.gte', i18n.conditions.number.gte);
        },
        init: Criteria.initInput,
        inputValue: Criteria.inputValueInput,
        isInputValid: Criteria.isInputValidInput,
        search(value, comparison, criteria) {
            return (criteria.parseNumber(value) >=
                criteria.parseNumber(comparison[0]));
        }
    },
    '>': {
        conditionName(dt, i18n) {
            return dt.i18n('searchBuilder.conditions.number.gt', i18n.conditions.number.gt);
        },
        init: Criteria.initInput,
        inputValue: Criteria.inputValueInput,
        isInputValid: Criteria.isInputValidInput,
        search(value, comparison, criteria) {
            return (criteria.parseNumber(value) >
                criteria.parseNumber(comparison[0]));
        }
    },
    between: {
        conditionName(dt, i18n) {
            return dt.i18n('searchBuilder.conditions.number.between', i18n.conditions.number.between);
        },
        init: Criteria.init2Input,
        inputValue: Criteria.inputValueInput,
        isInputValid: Criteria.isInputValidInput,
        search(value, comparison, criteria) {
            let val = criteria.parseNumber(value);
            let comp0 = criteria.parseNumber(comparison[0]);
            let comp1 = criteria.parseNumber(comparison[1]);
            if (+comp0 < +comp1) {
                return +comp0 <= +val && +val <= +comp1;
            }
            else {
                return +comp1 <= +val && +val <= +comp0;
            }
        }
    },
    '!between': {
        conditionName(dt, i18n) {
            return dt.i18n('searchBuilder.conditions.number.notBetween', i18n.conditions.number.notBetween);
        },
        init: Criteria.init2Input,
        inputValue: Criteria.inputValueInput,
        isInputValid: Criteria.isInputValidInput,
        search(value, comparison, criteria) {
            let val = criteria.parseNumber(value);
            let comp0 = criteria.parseNumber(comparison[0]);
            let comp1 = criteria.parseNumber(comparison[1]);
            if (+comp0 < +comp1) {
                return !(+comp0 <= +val && +val <= +comp1);
            }
            else {
                return !(+comp1 <= +val && +val <= +comp0);
            }
        }
    },
    null: {
        conditionName(dt, i18n) {
            return dt.i18n('searchBuilder.conditions.number.empty', i18n.conditions.number.empty);
        },
        init: Criteria.initNoValue,
        inputValue() {
            return [];
        },
        isInputValid() {
            return true;
        },
        search(value) {
            return (value === null || value === undefined || value.length === 0);
        }
    },
    '!null': {
        conditionName(dt, i18n) {
            return dt.i18n('searchBuilder.conditions.number.notEmpty', i18n.conditions.number.notEmpty);
        },
        init: Criteria.initNoValue,
        inputValue() {
            return [];
        },
        isInputValid() {
            return true;
        },
        search(value) {
            return !(value === null ||
                value === undefined ||
                value.length === 0);
        }
    }
};
// The order of the conditions will make eslint sad :(
// Has to be in this order so that they are displayed correctly in select elements
// Also have to disable member ordering for this as the private methods used are not yet declared otherwise
Criteria.stringConditions = {
    '=': {
        conditionName(dt, i18n) {
            return dt.i18n('searchBuilder.conditions.string.equals', i18n.conditions.string.equals);
        },
        init: Criteria.initSelect,
        inputValue: Criteria.inputValueSelect,
        isInputValid: Criteria.isInputValidSelect,
        search(value, comparison) {
            return value === comparison[0];
        }
    },
    '!=': {
        conditionName(dt, i18n) {
            return dt.i18n('searchBuilder.conditions.string.not', i18n.conditions.string.not);
        },
        init: Criteria.initSelect,
        inputValue: Criteria.inputValueSelect,
        isInputValid: Criteria.isInputValidInput,
        search(value, comparison) {
            return value !== comparison[0];
        }
    },
    starts: {
        conditionName(dt, i18n) {
            return dt.i18n('searchBuilder.conditions.string.startsWith', i18n.conditions.string.startsWith);
        },
        init: Criteria.initInput,
        inputValue: Criteria.inputValueInput,
        isInputValid: Criteria.isInputValidInput,
        search(value, comparison) {
            return (value.toLowerCase().indexOf(comparison[0].toLowerCase()) ===
                0);
        }
    },
    '!starts': {
        conditionName(dt, i18n) {
            return dt.i18n('searchBuilder.conditions.string.notStartsWith', i18n.conditions.string.notStartsWith);
        },
        init: Criteria.initInput,
        inputValue: Criteria.inputValueInput,
        isInputValid: Criteria.isInputValidInput,
        search(value, comparison) {
            return (value.toLowerCase().indexOf(comparison[0].toLowerCase()) !==
                0);
        }
    },
    contains: {
        conditionName(dt, i18n) {
            return dt.i18n('searchBuilder.conditions.string.contains', i18n.conditions.string.contains);
        },
        init: Criteria.initInput,
        inputValue: Criteria.inputValueInput,
        isInputValid: Criteria.isInputValidInput,
        search(value, comparison) {
            return value
                .toLowerCase()
                .includes(comparison[0].toLowerCase());
        }
    },
    '!contains': {
        conditionName(dt, i18n) {
            return dt.i18n('searchBuilder.conditions.string.notContains', i18n.conditions.string.notContains);
        },
        init: Criteria.initInput,
        inputValue: Criteria.inputValueInput,
        isInputValid: Criteria.isInputValidInput,
        search(value, comparison) {
            return !value
                .toLowerCase()
                .includes(comparison[0].toLowerCase());
        }
    },
    ends: {
        conditionName(dt, i18n) {
            return dt.i18n('searchBuilder.conditions.string.endsWith', i18n.conditions.string.endsWith);
        },
        init: Criteria.initInput,
        inputValue: Criteria.inputValueInput,
        isInputValid: Criteria.isInputValidInput,
        search(value, comparison) {
            return value
                .toLowerCase()
                .endsWith(comparison[0].toLowerCase());
        }
    },
    '!ends': {
        conditionName(dt, i18n) {
            return dt.i18n('searchBuilder.conditions.string.notEndsWith', i18n.conditions.string.notEndsWith);
        },
        init: Criteria.initInput,
        inputValue: Criteria.inputValueInput,
        isInputValid: Criteria.isInputValidInput,
        search(value, comparison) {
            return !value
                .toLowerCase()
                .endsWith(comparison[0].toLowerCase());
        }
    },
    null: {
        conditionName(dt, i18n) {
            return dt.i18n('searchBuilder.conditions.string.empty', i18n.conditions.string.empty);
        },
        init: Criteria.initNoValue,
        inputValue() {
            return [];
        },
        isInputValid() {
            return true;
        },
        search(value) {
            return (value === null || value === undefined || value.length === 0);
        }
    },
    '!null': {
        conditionName(dt, i18n) {
            return dt.i18n('searchBuilder.conditions.string.notEmpty', i18n.conditions.string.notEmpty);
        },
        init: Criteria.initNoValue,
        inputValue() {
            return [];
        },
        isInputValid() {
            return true;
        },
        search(value) {
            return !(value === null ||
                value === undefined ||
                value.length === 0);
        }
    }
};
// The order of the conditions will make eslint sad :(
// Also have to disable member ordering for this as the private methods used are not yet declared otherwise
Criteria.arrayConditions = {
    contains: {
        conditionName(dt, i18n) {
            return dt.i18n('searchBuilder.conditions.array.contains', i18n.conditions.array.contains);
        },
        init: Criteria.initSelectArray,
        inputValue: Criteria.inputValueSelect,
        isInputValid: Criteria.isInputValidSelect,
        search(value, comparison) {
            return value.includes(comparison[0]);
        }
    },
    without: {
        conditionName(dt, i18n) {
            return dt.i18n('searchBuilder.conditions.array.without', i18n.conditions.array.without);
        },
        init: Criteria.initSelectArray,
        inputValue: Criteria.inputValueSelect,
        isInputValid: Criteria.isInputValidSelect,
        search(value, comparison) {
            return value.indexOf(comparison[0]) === -1;
        }
    },
    '=': {
        conditionName(dt, i18n) {
            return dt.i18n('searchBuilder.conditions.array.equals', i18n.conditions.array.equals);
        },
        init: Criteria.initSelect,
        inputValue: Criteria.inputValueSelect,
        isInputValid: Criteria.isInputValidSelect,
        search(value, comparison) {
            if (value.length === comparison.length) {
                // Sort the comparison array to match the already-sorted value array
                comparison.sort();
                for (let i = 0; i < value.length; i++) {
                    if (value[i] !== comparison[i]) {
                        return false;
                    }
                }
                return true;
            }
            return false;
        }
    },
    '!=': {
        conditionName(dt, i18n) {
            return dt.i18n('searchBuilder.conditions.array.not', i18n.conditions.array.not);
        },
        init: Criteria.initSelect,
        inputValue: Criteria.inputValueSelect,
        isInputValid: Criteria.isInputValidSelect,
        search(value, comparison) {
            if (value.length === comparison.length) {
                // Sort the comparison array to match the already-sorted value array
                comparison.sort();
                for (let i = 0; i < value.length; i++) {
                    if (value[i] !== comparison[i]) {
                        return true;
                    }
                }
                return false;
            }
            return true;
        }
    },
    null: {
        conditionName(dt, i18n) {
            return dt.i18n('searchBuilder.conditions.array.empty', i18n.conditions.array.empty);
        },
        init: Criteria.initNoValue,
        inputValue() {
            return [];
        },
        isInputValid() {
            return true;
        },
        search(value) {
            return (value === null || value === undefined || value.length === 0);
        }
    },
    '!null': {
        conditionName(dt, i18n) {
            return dt.i18n('searchBuilder.conditions.array.notEmpty', i18n.conditions.array.notEmpty);
        },
        init: Criteria.initNoValue,
        inputValue() {
            return [];
        },
        isInputValid() {
            return true;
        },
        search(value) {
            return (value !== null && value !== undefined && value.length !== 0);
        }
    }
};
// eslint will be sad because we have to disable member ordering for this as the
// private static properties used are not yet declared otherwise
Criteria.defaults = {
    columns: '*',
    conditions: {
        array: Criteria.arrayConditions,
        date: Criteria.dateConditions,
        html: Criteria.stringConditions,
        'html-num': Criteria.numConditions,
        'html-num-fmt': Criteria.numFmtConditions,
        luxon: Criteria.luxonDateConditions,
        moment: Criteria.momentDateConditions,
        num: Criteria.numConditions,
        'num-fmt': Criteria.numFmtConditions,
        string: Criteria.stringConditions
    },
    depthLimit: false,
    enterSearch: false,
    filterChanged: undefined,
    greyscale: false,
    i18n: {
        add: 'Add Condition',
        button: {
            0: 'Search Builder',
            _: 'Search Builder (%d)'
        },
        clearAll: 'Clear All',
        condition: 'Condition',
        data: 'Data',
        delete: '&times',
        deleteTitle: 'Delete filtering rule',
        left: '<',
        leftTitle: 'Outdent criteria',
        logicAnd: 'And',
        logicOr: 'Or',
        right: '>',
        rightTitle: 'Indent criteria',
        search: 'Search',
        title: {
            0: 'Custom Search Builder',
            _: 'Custom Search Builder (%d)'
        },
        value: 'Value',
        valueJoiner: 'and'
    },
    liveSearch: true,
    logic: 'AND',
    orthogonal: {
        display: 'display',
        search: 'filter'
    },
    preDefined: false
};

/**
 * The Group class is used within SearchBuilder to represent a group of criteria
 */
class Group {
    constructor(table, opts, topGroup, index = 0, isChild = false, depth = 1, serverData = undefined) {
        this.classes = DataTable.util.object.assignDeep({}, Group.classes);
        // Get options from user
        this.c = DataTable.util.object.assignDeep({}, Group.defaults, opts);
        this.s = {
            criteria: [],
            depth,
            dt: table,
            index,
            isChild,
            logic: undefined,
            opts,
            preventRedraw: false,
            serverData,
            toDrop: undefined,
            topGroup
        };
        this.dom = {
            add: Dom
                .c('button')
                .classAdd(this.classes.add)
                .classAdd(this.classes.button)
                .attr('type', 'button'),
            clear: Dom
                .c('button')
                .html('&times')
                .classAdd(this.classes.button)
                .classAdd(this.classes.clearGroup)
                .attr('type', 'button'),
            container: Dom.c('div').classAdd(this.classes.group),
            logic: Dom
                .c('button')
                .append(Dom.c('div'))
                .classAdd(this.classes.logic)
                .classAdd(this.classes.button)
                .attr('type', 'button'),
            logicContainer: Dom.c('div').classAdd(this.classes.logicContainer),
            search: Dom
                .c('button')
                .classAdd(this.classes.search)
                .classAdd(this.classes.button)
                .attr('type', 'button')
                .css('display', 'none')
        };
        // A reference to the top level group is maintained throughout any subgroups and criteria that may be created
        if (this.s.topGroup === undefined) {
            this.s.topGroup = this.dom.container;
        }
        this._setup();
        return this;
    }
    /**
     * Destroys the groups buttons, clears the internal criteria and removes it from the dom
     */
    destroy() {
        // Turn off listeners
        this.dom.add.off('.dtsb');
        this.dom.logic.off('.dtsb');
        this.dom.search.off('.dtsb');
        // Trigger event for groups at a higher level to pick up on
        this.dom.container.trigger('dtsb-destroy');
        this.dom.container.remove();
        this.s.criteria = [];
    }
    /**
     * Gets the details required to rebuild the group
     */
    // Eslint upset at empty object but needs to be done
    getDetails(deFormatDates = false) {
        if (this.s.criteria.length === 0) {
            return {};
        }
        let details = {
            criteria: [],
            logic: this.s.logic
        };
        // NOTE here crit could be either a subgroup or a criteria
        for (let crit of this.s.criteria) {
            details.criteria.push(crit.criteria.getDetails(deFormatDates));
        }
        return details;
    }
    /**
     * Getter for the node for the container of the group
     *
     * @returns Node for the container of the group
     */
    getNode() {
        return this.dom.container;
    }
    /**
     * Rebuilds the group based upon the details passed in
     *
     * @param loadedDetails the details required to rebuild the group
     */
    rebuild(loadedDetails) {
        let crit;
        // If no criteria are stored then just return
        if (loadedDetails.criteria === undefined ||
            loadedDetails.criteria === null ||
            (Array.isArray(loadedDetails.criteria) &&
                loadedDetails.criteria.length === 0)) {
            return;
        }
        this.s.logic = loadedDetails.logic;
        this.dom.logic
            .children()
            .eq(0)
            .html(this.s.logic === 'OR'
            ? this.s.dt.i18n('searchBuilder.logicOr', this.c.i18n.logicOr)
            : this.s.dt.i18n('searchBuilder.logicAnd', this.c.i18n.logicAnd));
        // Add all of the criteria, be it a sub group or a criteria
        if (Array.isArray(loadedDetails.criteria)) {
            for (crit of loadedDetails.criteria) {
                if (crit.logic !== undefined) {
                    this._addPrevGroup(crit);
                }
                else if (crit.logic === undefined) {
                    this._addPrevCriteria(crit);
                }
            }
        }
        // For all of the criteria children, update the arrows incase they require changing and set the listeners
        for (crit of this.s.criteria) {
            if (crit.criteria instanceof Criteria) {
                crit.criteria.updateArrows(this.s.criteria.length > 1);
                this._setCriteriaListeners(crit.criteria);
            }
        }
    }
    /**
     * Redraws the Contents of the searchBuilder Groups and Criteria
     */
    redrawContents() {
        if (this.s.preventRedraw) {
            return;
        }
        // Clear the container out and add the basic elements
        this.dom.container.children().detach();
        this.dom.container.append(this.dom.logicContainer).append(this.dom.add);
        if (!this.c.liveSearch) {
            this.dom.container.append(this.dom.search);
        }
        // Sort the criteria by index so that they appear in the correct order
        this.s.criteria.sort(function (a, b) {
            if (a.criteria.s.index < b.criteria.s.index) {
                return -1;
            }
            else if (a.criteria.s.index > b.criteria.s.index) {
                return 1;
            }
            return 0;
        });
        this.setListeners();
        for (let i = 0; i < this.s.criteria.length; i++) {
            let crit = this.s.criteria[i].criteria;
            if (crit instanceof Criteria) {
                // Reset the index to the new value
                this.s.criteria[i].index = i;
                this.s.criteria[i].criteria.s.index = i;
                // Add to the group
                this.s.criteria[i].criteria.dom.container.insertBefore(this.dom.add);
                // Set listeners for various points
                this._setCriteriaListeners(crit);
                this.s.criteria[i].criteria.s.preventRedraw =
                    this.s.preventRedraw;
                this.s.criteria[i].criteria.rebuild(this.s.criteria[i].criteria.getDetails());
                this.s.criteria[i].criteria.s.preventRedraw = false;
            }
            else if (crit instanceof Group && crit.s.criteria.length > 0) {
                // Reset the index to the new value
                this.s.criteria[i].index = i;
                this.s.criteria[i].criteria.s.index = i;
                // Add the sub group to the group
                this.s.criteria[i].criteria.dom.container.insertBefore(this.dom.add);
                // Redraw the contents of the group
                crit.s.preventRedraw = this.s.preventRedraw;
                crit.redrawContents();
                crit.s.preventRedraw = false;
                this._setGroupListeners(crit);
            }
            else {
                // The group is empty so remove it
                this.s.criteria.splice(i, 1);
                i--;
            }
        }
        this.setupLogic();
    }
    /**
     * Resizes the logic button only rather than the entire dom.
     */
    redrawLogic() {
        for (let crit of this.s.criteria) {
            if (crit.criteria instanceof Group) {
                crit.criteria.redrawLogic();
            }
        }
        this.setupLogic();
    }
    /**
     * Search method, checking the row data against the criteria in the group
     *
     * @param rowData The row data to be compared
     * @returns boolean The result of the search
     */
    search(rowData, rowIdx) {
        if (this.s.logic === 'AND') {
            return this._andSearch(rowData, rowIdx);
        }
        else if (this.s.logic === 'OR') {
            return this._orSearch(rowData, rowIdx);
        }
        return true;
    }
    /**
     * Locates the groups logic button to the correct location on the page
     */
    setupLogic() {
        // Remove logic button
        this.dom.logicContainer.remove();
        this.dom.clear.remove();
        // If there are no criteria in the group then keep the logic removed and return
        if (this.s.criteria.length < 1) {
            if (!this.s.isChild) {
                this.dom.container.trigger('dtsb-destroy');
            }
            this.dom.search.css('display', 'none');
            return;
        }
        this.dom.clear.height('0px');
        this.dom.logicContainer.append(this.dom.clear);
        if (!this.s.isChild) {
            this.dom.search.css('display', 'inline-block');
        }
        // Prepend logic button
        this.dom.container.prepend(this.dom.logicContainer);
        for (let crit of this.s.criteria) {
            if (crit.criteria instanceof Criteria) {
                crit.criteria.setupButtons();
            }
        }
        // Set width, take 2 for the border
        let height = this.dom.container.height() - 1;
        this.dom.logicContainer.width(height);
        this.dom.clear.height(this.dom.logicContainer.width());
        this._setLogicListener();
        this._setClearListener();
    }
    /**
     * Sets listeners on the groups elements
     */
    setListeners() {
        this.dom.add.off('click');
        this.dom.add.on('click.dtsb', () => {
            // If this is the parent group then the logic button has not been added yet
            if (!this.s.isChild) {
                this.dom.container.prepend(this.dom.logicContainer);
            }
            this.addCriteria();
            this.dom.container.trigger('dtsb-add');
            this.s.dt.state.save();
            return false;
        });
        this.dom.search.off('click.dtsb').on('click.dtsb', () => {
            this.s.dt.draw();
        });
        for (let crit of this.s.criteria) {
            crit.criteria.setListeners();
        }
        this._setClearListener();
        this._setLogicListener();
    }
    /**
     * Adds a criteria to the group
     *
     * @param crit Instance of Criteria to be added to the group
     */
    addCriteria(crit = null) {
        let index = crit === null ? this.s.criteria.length : crit.s.index;
        let criteria = new Criteria(this.s.dt, this.s.opts, this.s.topGroup, index, this.s.depth, this.s.serverData, this.c.liveSearch);
        // If a Criteria has been passed in then set the values to continue that
        if (crit !== null) {
            criteria.c = crit.c;
            criteria.s = crit.s;
            criteria.s.depth = this.s.depth;
            criteria.classes = crit.classes;
        }
        criteria.populate();
        let inserted = false;
        for (let i = 0; i < this.s.criteria.length; i++) {
            if (i === 0 &&
                this.s.criteria[i].criteria.s.index > criteria.s.index) {
                // Add the node for the criteria at the start of the group
                criteria
                    .getNode()
                    .insertBefore(this.s.criteria[i].criteria.dom.container);
                inserted = true;
            }
            else if (i < this.s.criteria.length - 1 &&
                this.s.criteria[i].criteria.s.index < criteria.s.index &&
                this.s.criteria[i + 1].criteria.s.index > criteria.s.index) {
                // Add the node for the criteria in the correct location
                criteria
                    .getNode()
                    .insertAfter(this.s.criteria[i].criteria.dom.container);
                inserted = true;
            }
        }
        if (!inserted) {
            criteria.getNode().insertBefore(this.dom.add);
        }
        // Add the details for this criteria to the array
        this.s.criteria.push({
            criteria,
            index
        });
        this.s.criteria = this.s.criteria.sort((a, b) => a.criteria.s.index - b.criteria.s.index);
        for (let opt of this.s.criteria) {
            if (opt.criteria instanceof Criteria) {
                opt.criteria.updateArrows(this.s.criteria.length > 1);
            }
        }
        this._setCriteriaListeners(criteria);
        criteria.setListeners();
        this.setupLogic();
    }
    /**
     * Checks the group to see if it has any filled criteria
     */
    checkFilled() {
        for (let crit of this.s.criteria) {
            if ((crit.criteria instanceof Criteria && crit.criteria.s.filled) ||
                (crit.criteria instanceof Group && crit.criteria.checkFilled())) {
                return true;
            }
        }
        return false;
    }
    /**
     * Gets the count for the number of criteria in this group and any sub groups
     */
    count() {
        let count = 0;
        for (let crit of this.s.criteria) {
            if (crit.criteria instanceof Group) {
                count += crit.criteria.count();
            }
            else {
                count++;
            }
        }
        return count;
    }
    /**
     * Rebuilds a sub group that previously existed
     *
     * @param loadedGroup The details of a group within this group
     */
    _addPrevGroup(loadedGroup) {
        let idx = this.s.criteria.length;
        let group = new Group(this.s.dt, this.c, this.s.topGroup, idx, true, this.s.depth + 1, this.s.serverData);
        // Add the new group to the criteria array
        this.s.criteria.push({
            criteria: group,
            index: idx,
            logic: group.s.logic
        });
        // Rebuild it with the previous conditions for that group
        group.rebuild(loadedGroup);
        this.s.criteria[idx].criteria = group;
        this.s.topGroup.trigger('dtsb-redrawContents');
        this._setGroupListeners(group);
    }
    /**
     * Rebuilds a criteria of this group that previously existed
     *
     * @param loadedCriteria The details of a criteria within the group
     */
    _addPrevCriteria(loadedCriteria) {
        let idx = this.s.criteria.length;
        let criteria = new Criteria(this.s.dt, this.s.opts, this.s.topGroup, idx, this.s.depth, this.s.serverData);
        criteria.populate();
        // Add the new criteria to the criteria array
        this.s.criteria.push({
            criteria,
            index: idx
        });
        // Rebuild it with the previous conditions for that criteria
        criteria.s.preventRedraw = this.s.preventRedraw;
        criteria.rebuild(loadedCriteria);
        criteria.s.preventRedraw = false;
        this.s.criteria[idx].criteria = criteria;
        if (!this.s.preventRedraw) {
            this.s.topGroup.trigger('dtsb-redrawContents');
        }
    }
    /**
     * Checks And the criteria using AND logic
     *
     * @param rowData The row data to be checked against the search criteria
     * @returns boolean The result of the AND search
     */
    _andSearch(rowData, rowIdx) {
        // If there are no criteria then return true for this group
        if (this.s.criteria.length === 0) {
            return true;
        }
        for (let crit of this.s.criteria) {
            // If the criteria is not complete then skip it
            if (crit.criteria instanceof Criteria && !crit.criteria.s.filled) {
                continue;
            }
            // Otherwise if a single one fails return false
            else if (!crit.criteria.search(rowData, rowIdx)) {
                return false;
            }
        }
        // If we get to here then everything has passed, so return true for the group
        return true;
    }
    /**
     * Checks And the criteria using OR logic
     *
     * @param rowData The row data to be checked against the search criteria
     * @returns boolean The result of the OR search
     */
    _orSearch(rowData, rowIdx) {
        // If there are no criteria in the group then return true
        if (this.s.criteria.length === 0) {
            return true;
        }
        // This will check to make sure that at least one criteria in the group is complete
        let filledfound = false;
        for (let crit of this.s.criteria) {
            if (crit.criteria instanceof Criteria && crit.criteria.s.filled) {
                // A completed criteria has been found so set the flag
                filledfound = true;
                // If the search passes then return true
                if (crit.criteria.search(rowData, rowIdx)) {
                    return true;
                }
            }
            else if (crit.criteria instanceof Group &&
                crit.criteria.checkFilled()) {
                filledfound = true;
                if (crit.criteria.search(rowData, rowIdx)) {
                    return true;
                }
            }
        }
        // If we get here we need to return the inverse of filledfound,
        //  as if any have been found and we are here then none have passed
        return !filledfound;
    }
    /**
     * Removes a criteria from the group
     *
     * @param criteria The criteria instance to be removed
     */
    _removeCriteria(criteria, group = false) {
        let i;
        // If removing a criteria and there is only then then just destroy the group
        if (this.s.criteria.length <= 1 && this.s.isChild) {
            this.destroy();
        }
        else {
            // Otherwise splice the given criteria out and redo the indexes
            let last;
            for (i = 0; i < this.s.criteria.length; i++) {
                if (this.s.criteria[i].index === criteria.s.index &&
                    (!group || this.s.criteria[i].criteria instanceof Group)) {
                    last = i;
                }
            }
            // We want to remove the last element with the desired index, as its replacement will be inserted before it
            if (last !== undefined) {
                this.s.criteria.splice(last, 1);
            }
            for (i = 0; i < this.s.criteria.length; i++) {
                this.s.criteria[i].index = i;
                this.s.criteria[i].criteria.s.index = i;
            }
        }
    }
    /**
     * Sets the listeners in group for a criteria
     *
     * @param criteria The criteria for the listeners to be set on
     */
    _setCriteriaListeners(criteria) {
        criteria.dom.delete.off('click').on('click.dtsb', () => {
            this._removeCriteria(criteria);
            criteria.dom.container.remove();
            for (let crit of this.s.criteria) {
                if (crit.criteria instanceof Criteria) {
                    crit.criteria.updateArrows(this.s.criteria.length > 1);
                }
            }
            criteria.destroy();
            this.s.dt.draw();
            this.s.topGroup.trigger('dtsb-redrawContents');
            return false;
        });
        criteria.dom.right.off('click').on('click.dtsb', () => {
            let idx = criteria.s.index;
            let group = new Group(this.s.dt, this.s.opts, this.s.topGroup, criteria.s.index, true, this.s.depth + 1, this.s.serverData);
            // Add the criteria that is to be moved to the new group
            group.addCriteria(criteria);
            // Update the details in the current groups criteria array
            this.s.criteria[idx].criteria = group;
            this.s.criteria[idx].logic = 'AND';
            this.s.topGroup.trigger('dtsb-redrawContents');
            this._setGroupListeners(group);
            return false;
        });
        criteria.dom.left.off('click').on('click.dtsb', () => {
            this.s.toDrop = new Criteria(this.s.dt, this.s.opts, this.s.topGroup, criteria.s.index, undefined, this.s.serverData);
            this.s.toDrop.s = criteria.s;
            this.s.toDrop.c = criteria.c;
            this.s.toDrop.classes = criteria.classes;
            this.s.toDrop.populate();
            // The dropCriteria event mutates the reference to the index so need to store it
            let index = this.s.toDrop.s.index;
            this.dom.container.trigger('dtsb-dropCriteria');
            criteria.s.index = index;
            this._removeCriteria(criteria);
            // By tracking the top level group we can directly trigger a redraw on it,
            //  bubbling is also possible, but that is slow with deep levelled groups
            this.s.topGroup.trigger('dtsb-redrawContents');
            this.s.dt.draw();
            return false;
        });
    }
    /**
     * Set's the listeners for the group clear button
     */
    _setClearListener() {
        this.dom.clear.off('click').on('click.dtsb', () => {
            if (!this.s.isChild) {
                this.dom.container.trigger('dtsb-clearContents');
                return false;
            }
            this.destroy();
            this.s.topGroup.trigger('dtsb-redrawContents');
            return false;
        });
    }
    /**
     * Sets listeners for sub groups of this group
     *
     * @param group The sub group that the listeners are to be set on
     */
    _setGroupListeners(group) {
        // Set listeners for the new group
        group.dom.add.off('click').on('click.dtsb', () => {
            this.setupLogic();
            this.dom.container.trigger('dtsb-add');
            return false;
        });
        group.dom.container.off('dtsb-add').on('dtsb-add.dtsb', () => {
            this.setupLogic();
            this.dom.container.trigger('dtsb-add');
            return false;
        });
        group.dom.container
            .off('dtsb-destroy')
            .on('dtsb-destroy.dtsb', () => {
            this._removeCriteria(group, true);
            group.dom.container.remove();
            this.setupLogic();
            return false;
        });
        group.dom.container
            .off('dtsb-dropCriteria')
            .on('dtsb-dropCriteria.dtsb', () => {
            let toDrop = group.s.toDrop;
            toDrop.s.index = group.s.index;
            toDrop.updateArrows(this.s.criteria.length > 1);
            this.addCriteria(toDrop);
            return false;
        });
        group.setListeners();
    }
    /**
     * Sets up the Group instance, setting listeners and appending elements
     */
    _setup() {
        this.setListeners();
        this.dom.add.html(this.s.dt.i18n('searchBuilder.add', this.c.i18n.add));
        this.dom.search.html(this.s.dt.i18n('searchBuilder.search', this.c.i18n.search));
        this.dom.logic
            .children()
            .eq(0)
            .html(this.c.logic === 'OR'
            ? this.s.dt.i18n('searchBuilder.logicOr', this.c.i18n.logicOr)
            : this.s.dt.i18n('searchBuilder.logicAnd', this.c.i18n.logicAnd));
        this.s.logic = this.c.logic === 'OR' ? 'OR' : 'AND';
        if (this.c.greyscale) {
            this.dom.logic.classAdd(this.classes.greyscale);
        }
        this.dom.logicContainer.append(this.dom.logic).append(this.dom.clear);
        // Only append the logic button immediately if this is a sub group,
        //  otherwise it will be prepended later when adding a criteria
        if (this.s.isChild) {
            this.dom.container.append(this.dom.logicContainer);
        }
        this.dom.container.append(this.dom.add);
        if (!this.c.liveSearch) {
            this.dom.container.append(this.dom.search);
        }
    }
    /**
     * Sets the listener for the logic button
     */
    _setLogicListener() {
        this.dom.logic.off('click').on('click.dtsb', () => {
            this._toggleLogic();
            this.s.dt.draw();
            for (let crit of this.s.criteria) {
                crit.criteria.setListeners();
            }
        });
    }
    /**
     * Toggles the logic for the group
     */
    _toggleLogic() {
        if (this.s.logic === 'OR') {
            this.s.logic = 'AND';
            this.dom.logic
                .children()
                .eq(0)
                .html(this.s.dt.i18n('searchBuilder.logicAnd', this.c.i18n.logicAnd));
        }
        else if (this.s.logic === 'AND') {
            this.s.logic = 'OR';
            this.dom.logic
                .children()
                .eq(0)
                .html(this.s.dt.i18n('searchBuilder.logicOr', this.c.i18n.logicOr));
        }
    }
}
Group.classes = {
    add: 'dtsb-add',
    button: 'dtsb-button',
    clearGroup: 'dtsb-clearGroup',
    greyscale: 'dtsb-greyscale',
    group: 'dtsb-group',
    inputButton: 'dtsb-iptbtn',
    logic: 'dtsb-logic',
    logicContainer: 'dtsb-logicContainer',
    search: 'dtsb-search'
};
Group.defaults = {
    columns: '*',
    conditions: {
        date: Criteria.dateConditions,
        html: Criteria.stringConditions,
        'html-num': Criteria.numConditions,
        'html-num-fmt': Criteria.numFmtConditions,
        luxon: Criteria.luxonDateConditions,
        moment: Criteria.momentDateConditions,
        num: Criteria.numConditions,
        'num-fmt': Criteria.numFmtConditions,
        string: Criteria.stringConditions
    },
    depthLimit: false,
    enterSearch: false,
    filterChanged: undefined,
    greyscale: false,
    liveSearch: true,
    i18n: {
        add: 'Add Condition',
        button: {
            0: 'Search Builder',
            _: 'Search Builder (%d)'
        },
        clearAll: 'Clear All',
        condition: 'Condition',
        data: 'Data',
        delete: '&times',
        deleteTitle: 'Delete filtering rule',
        left: '<',
        leftTitle: 'Outdent criteria',
        logicAnd: 'And',
        logicOr: 'Or',
        right: '>',
        rightTitle: 'Indent criteria',
        search: 'Search',
        title: {
            0: 'Custom Search Builder',
            _: 'Custom Search Builder (%d)'
        },
        value: 'Value',
        valueJoiner: 'and'
    },
    logic: 'AND',
    orthogonal: {
        display: 'display',
        search: 'filter'
    },
    preDefined: false
};

// Check that the required version of DataTables is included
if (!DataTable || !DataTable.versionCheck || !DataTable.versionCheck('3')) {
    throw new Error('SearchBuilder requires DataTables 3 or newer');
}
/**
 * SearchBuilder class for DataTables.
 * Allows for complex search queries to be constructed and implemented on a DataTable
 */
class SearchBuilder {
    constructor(builderSettings, opts) {
        let table = new DataTable.Api(builderSettings);
        this.classes = util.object.assignDeep({}, SearchBuilder.classes);
        // Get options from user
        this.c = util.object.assignDeep({}, SearchBuilder.defaults, opts);
        this.dom = {
            clearAll: Dom
                .c('button')
                .attr('type', 'button')
                .html(table.i18n('searchBuilder.clearAll', this.c.i18n.clearAll))
                .classAdd(this.classes.clearAll)
                .classAdd(this.classes.button)
                .attr('type', 'button'),
            container: Dom.c('div').classAdd(this.classes.container),
            title: Dom.c('div').classAdd(this.classes.title),
            titleRow: Dom.c('div').classAdd(this.classes.titleRow),
            topGroup: undefined
        };
        this.s = {
            dt: table,
            opts,
            search: undefined,
            serverData: undefined,
            topGroup: undefined
        };
        // If searchbuilder is already defined for this table then return
        if (table.settings()[0]._searchBuilder !== undefined) {
            return;
        }
        table.settings()[0]._searchBuilder = this;
        // If using SSP we want to include the previous state in the very first server call
        if (this.s.dt.page.info().serverSide) {
            this.s.dt.on('preXhr.dtsb', (e, settings, data) => {
                let loadedState = this.s.dt.state.loaded();
                if (loadedState && loadedState.searchBuilder) {
                    data.searchBuilder = this._collapseArray(loadedState.searchBuilder);
                }
            });
            this.s.dt.on('xhr.dtsb', (e, settings, json) => {
                if (json && json.searchBuilder && json.searchBuilder.options) {
                    this.s.serverData = json.searchBuilder.options;
                }
            });
        }
        // Run the remaining setup when the table is initialised
        if (this.s.dt.settings()[0]._bInitComplete) {
            this._setUp();
        }
        else {
            table.one('init.dt', () => {
                this._setUp();
            });
        }
        return this;
    }
    /**
     * Gets the details required to rebuild the SearchBuilder as it currently is
     */
    // eslint upset at empty object but that is what it is
    getDetails(deFormatDates = false) {
        return this.s.topGroup ? this.s.topGroup.getDetails(deFormatDates) : {};
    }
    /**
     * Getter for the node of the container for the searchBuilder
     *
     * @returns Dom the node of the container
     */
    getNode() {
        return this.dom.container;
    }
    /**
     * Rebuilds the SearchBuilder to a state that is provided
     *
     * @param details The details required to perform a rebuild
     */
    rebuild(details, redraw = true) {
        this.dom.clearAll.trigger('click', false);
        // If there are no details to rebuild then return
        if (details === undefined || details === null) {
            return this;
        }
        this.s.topGroup.s.preventRedraw = true;
        this.s.topGroup.rebuild(details);
        this.s.topGroup.s.preventRedraw = false;
        this._checkClear();
        this._updateTitle(this.s.topGroup.count());
        this.s.topGroup.redrawContents();
        if (redraw) {
            this.s.dt.draw(false);
        }
        this.s.topGroup.setListeners();
        return this;
    }
    /**
     * Applies the defaults to preDefined criteria
     *
     * @param preDef the array of criteria to be processed.
     */
    _applyPreDefDefaults(preDef) {
        if (preDef.criteria !== undefined && preDef.logic === undefined) {
            preDef.logic = 'AND';
        }
        for (let crit of preDef.criteria) {
            // Apply the defaults to any further criteria
            if (crit.criteria !== undefined) {
                crit = this._applyPreDefDefaults(crit);
            }
            else {
                this.s.dt.columns().every(index => {
                    if (this.s.dt.settings()[0].columns[index].title ===
                        crit.data) {
                        crit.dataIdx = index;
                    }
                });
            }
        }
        return preDef;
    }
    /**
     * Set's up the SearchBuilder
     */
    _setUp(loadState = true) {
        // Register an Api method for getting the column type. DataTables 2 has
        // this built in
        if (typeof this.s.dt.column().type !== 'function') {
            DataTable.Api.registerPlural('columns().types()', 'column().type()', function () {
                return this.iterator('column', function (settings, column) {
                    return settings.columns[column].type;
                }, 1);
            });
        }
        // Check that DateTime is included, If not need to check if it could be used
        // eslint-disable-next-line no-extra-parens
        if (!DataTable.DateTime) {
            let types = this.s.dt.columns().types().toArray();
            if (types === undefined ||
                types.includes(undefined) ||
                types.includes(null)) {
                types = [];
                for (let colInit of this.s.dt.settings()[0].columns) {
                    types.push(colInit.searchBuilderType !== undefined
                        ? colInit.searchBuilderType
                        : colInit.type);
                }
            }
            let columnIdxs = this.s.dt.columns().toArray();
            // If the column type is still unknown use the internal API to detect type
            if (types === undefined ||
                types.includes(undefined) ||
                types.includes(null)) {
                types = this.s.dt.columns().types().toArray();
            }
            for (let i = 0; i < columnIdxs[0].length; i++) {
                let column = columnIdxs[0][i];
                let type = types[column];
                if (
                // Check if this column can be filtered
                (this.c.columns === '*' ||
                    (Array.isArray(this.c.columns) &&
                        this.c.columns.includes(i))) &&
                    // Check if the type is one of the restricted types
                    (type.includes('date') ||
                        type.includes('moment') ||
                        type.includes('luxon'))) {
                    alert('SearchBuilder Requires DateTime when used with dates.');
                    throw new Error('SearchBuilder requires DateTime');
                }
            }
        }
        this.s.topGroup = new Group(this.s.dt, this.c, undefined, undefined, undefined, undefined, this.s.serverData);
        this._setClearListener();
        this.s.dt.on('stateSaveParams.dtsb', (e, settings, data) => {
            data.searchBuilder = this.getDetails();
            if (!data.scroller) {
                data.page = this.s.dt.page();
            }
            else {
                data.start = this.s.dt.state().start;
            }
        });
        this.s.dt.on('stateLoadParams.dtsb', (e, settings, data) => {
            this.rebuild(data.searchBuilder);
        });
        this._build();
        this.s.dt.on('preXhr.dtsb', (e, settings, data) => {
            if (this.s.dt.page.info().serverSide) {
                data.searchBuilder = this._collapseArray(this.getDetails(true));
            }
        });
        this.s.dt.on('columns-reordered', () => {
            this.rebuild(this.getDetails());
        });
        if (loadState) {
            let loadedState = this.s.dt.state.loaded();
            // If the loaded State is not null rebuild based on it for statesave
            if (loadedState !== null &&
                loadedState.searchBuilder !== undefined) {
                this.s.topGroup.rebuild(loadedState.searchBuilder);
                this.s.topGroup.dom.container.trigger('dtsb-redrawContents');
                // If using SSP we want to restrict the amount of server calls that take place
                //  and this information will already have been processed
                if (!this.s.dt.page.info().serverSide) {
                    if (loadedState.page) {
                        this.s.dt.page(loadedState.page).draw('page');
                    }
                    else if (this.s.dt.scroller && loadedState.scroller) {
                        this.s.dt
                            .scroller()
                            .scrollToRow(loadedState.scroller.topRow);
                    }
                }
                this.s.topGroup.setListeners();
            }
            // Otherwise load any predefined options
            else if (this.c.preDefined !== false) {
                this.c.preDefined = this._applyPreDefDefaults(this.c.preDefined);
                this.rebuild(this.c.preDefined);
            }
        }
        this._setEmptyListener();
        this.s.dt.state.save();
    }
    _collapseArray(criteria) {
        if (criteria.logic === undefined) {
            if (criteria.value !== undefined) {
                criteria.value.sort((a, b) => {
                    if (!isNaN(+a)) {
                        a = +a;
                        b = +b;
                    }
                    if (a < b) {
                        return -1;
                    }
                    else if (b < a) {
                        return 1;
                    }
                    else {
                        return 0;
                    }
                });
                criteria.value1 = criteria.value[0];
                criteria.value2 = criteria.value[1];
            }
        }
        else {
            for (let i = 0; i < criteria.criteria.length; i++) {
                criteria.criteria[i] = this._collapseArray(criteria.criteria[i]);
            }
        }
        return criteria;
    }
    /**
     * Updates the title of the SearchBuilder
     *
     * @param count the number of filters in the SearchBuilder
     */
    _updateTitle(count) {
        this.dom.title.html(this.s.dt.i18n('searchBuilder.title', this.c.i18n.title, count));
    }
    /**
     * Builds all of the dom elements together
     */
    _build() {
        // Empty and setup the container
        this.dom.clearAll.remove();
        this.dom.container.empty();
        let count = this.s.topGroup.count();
        this._updateTitle(count);
        this.dom.titleRow.append(this.dom.title);
        this.dom.container.append(this.dom.titleRow);
        this.dom.topGroup = this.s.topGroup.getNode();
        this.dom.container.append(this.dom.topGroup);
        this._setRedrawListener();
        let tableNode = this.s.dt.table(0).node();
        if (!DataTable.ext.search.includes(this.s.search)) {
            // Custom search function for SearchBuilder
            this.s.search = (settings, searchData, dataIndex) => {
                if (settings.table !== tableNode) {
                    return true;
                }
                return this.s.topGroup.search(searchData, dataIndex);
            };
            // Add SearchBuilder search function to the dataTables search array
            DataTable.ext.search.push(this.s.search);
        }
        this.s.dt.on('destroy.dtsb', () => {
            this.dom.container.remove();
            this.dom.clearAll.remove();
            let searchIdx = DataTable.ext.search.indexOf(this.s.search);
            while (searchIdx !== -1) {
                DataTable.ext.search.splice(searchIdx, 1);
                searchIdx = DataTable.ext.search.indexOf(this.s.search);
            }
            this.s.dt.off('.dtsb');
            Dom.s(this.s.dt.table().node()).off('.dtsb');
        });
    }
    /**
     * Checks if the clearAll button should be added or not
     */
    _checkClear() {
        if (this.s.topGroup.s.criteria.length > 0) {
            this.dom.clearAll.insertAfter(this.dom.title.get(0));
            this._setClearListener();
        }
        else {
            this.dom.clearAll.remove();
        }
    }
    /**
     * Update the count in the title/button
     *
     * @param count Number of filters applied
     */
    _filterChanged(count) {
        let fn = this.c.filterChanged;
        if (typeof fn === 'function') {
            fn(count, this.s.dt.i18n('searchBuilder.button', this.c.i18n.button, count));
        }
    }
    /**
     * Set the listener for the clear button
     */
    _setClearListener() {
        this.dom.clearAll.off('click');
        this.dom.clearAll.on('click.dtsb', (e, draw) => {
            this.s.topGroup = new Group(this.s.dt, this.c, undefined, undefined, undefined, undefined, this.s.serverData);
            this._build();
            if (draw !== false) {
                this.s.dt.draw();
            }
            this.s.topGroup.setListeners();
            this.dom.clearAll.remove();
            this._setEmptyListener();
            this._filterChanged(0);
            return false;
        });
    }
    /**
     * Set the listener for the Redraw event
     */
    _setRedrawListener() {
        this.s.topGroup.dom.container.off('dtsb-redrawContents');
        this.s.topGroup.dom.container.on('dtsb-redrawContents.dtsb', () => {
            this._checkClear();
            this.s.topGroup.redrawContents();
            this.s.topGroup.setupLogic();
            this._setEmptyListener();
            let count = this.s.topGroup.count();
            this._updateTitle(count);
            this._filterChanged(count);
            // If using SSP we want to restrict the amount of server calls that take place
            //  and this information will already have been processed
            if (!this.s.dt.page.info().serverSide) {
                this.s.dt.draw();
            }
            this.s.dt.state.save();
        });
        this.s.topGroup.dom.container.off('dtsb-redrawContents-noDraw');
        this.s.topGroup.dom.container.on('dtsb-redrawContents-noDraw.dtsb', () => {
            this._checkClear();
            this.s.topGroup.s.preventRedraw = true;
            this.s.topGroup.redrawContents();
            this.s.topGroup.s.preventRedraw = false;
            this.s.topGroup.setupLogic();
            this._setEmptyListener();
            let count = this.s.topGroup.count();
            this._updateTitle(count);
            this._filterChanged(count);
        });
        this.s.topGroup.dom.container.off('dtsb-redrawLogic');
        this.s.topGroup.dom.container.on('dtsb-redrawLogic.dtsb', () => {
            this.s.topGroup.redrawLogic();
            let count = this.s.topGroup.count();
            this._updateTitle(count);
            this._filterChanged(count);
        });
        this.s.topGroup.dom.container.off('dtsb-add');
        this.s.topGroup.dom.container.on('dtsb-add.dtsb', () => {
            let count = this.s.topGroup.count();
            this._updateTitle(count);
            this._filterChanged(count);
            this._checkClear();
        });
        this.s.dt.on('postEdit.dtsb postCreate.dtsb postRemove.dtsb', () => {
            this.s.topGroup.redrawContents();
        });
        this.s.topGroup.dom.container.off('dtsb-clearContents');
        this.s.topGroup.dom.container.on('dtsb-clearContents.dtsb', () => {
            this._setUp(false);
            this._filterChanged(0);
            this.s.dt.draw();
        });
    }
    /**
     * Sets listeners to check whether clearAll should be added or removed
     */
    _setEmptyListener() {
        this.s.topGroup.dom.add.on('click.dtsb', () => {
            this._checkClear();
        });
        this.s.topGroup.dom.container.on('dtsb-destroy.dtsb', () => {
            this.dom.clearAll.remove();
        });
    }
}
SearchBuilder.version = '2.0.0';
SearchBuilder.classes = {
    button: 'dtsb-button',
    clearAll: 'dtsb-clearAll',
    container: 'dtsb-searchBuilder',
    inputButton: 'dtsb-iptbtn',
    title: 'dtsb-title',
    titleRow: 'dtsb-titleRow'
};
SearchBuilder.defaults = {
    columns: '*',
    conditions: {
        date: Criteria.dateConditions,
        html: Criteria.stringConditions,
        'html-num': Criteria.numConditions,
        'html-num-fmt': Criteria.numFmtConditions,
        luxon: Criteria.luxonDateConditions,
        moment: Criteria.momentDateConditions,
        num: Criteria.numConditions,
        'num-fmt': Criteria.numFmtConditions,
        string: Criteria.stringConditions
    },
    depthLimit: false,
    enterSearch: false,
    filterChanged: undefined,
    greyscale: false,
    liveSearch: true,
    i18n: {
        add: 'Add Condition',
        button: {
            0: 'Search Builder',
            _: 'Search Builder (%d)'
        },
        clearAll: 'Clear All',
        condition: 'Condition',
        conditions: {
            array: {
                contains: 'Contains',
                empty: 'Empty',
                equals: 'Equals',
                not: 'Not',
                notEmpty: 'Not Empty',
                without: 'Without'
            },
            date: {
                after: 'After',
                before: 'Before',
                between: 'Between',
                empty: 'Empty',
                equals: 'Equals',
                not: 'Not',
                notBetween: 'Not Between',
                notEmpty: 'Not Empty'
            },
            // eslint-disable-next-line id-blacklist
            number: {
                between: 'Between',
                empty: 'Empty',
                equals: 'Equals',
                gt: 'Greater Than',
                gte: 'Greater Than Equal To',
                lt: 'Less Than',
                lte: 'Less Than Equal To',
                not: 'Not',
                notBetween: 'Not Between',
                notEmpty: 'Not Empty'
            },
            // eslint-disable-next-line id-blacklist
            string: {
                contains: 'Contains',
                empty: 'Empty',
                endsWith: 'Ends With',
                equals: 'Equals',
                not: 'Not',
                notContains: 'Does Not Contain',
                notEmpty: 'Not Empty',
                notEndsWith: 'Does Not End With',
                notStartsWith: 'Does Not Start With',
                startsWith: 'Starts With'
            }
        },
        data: 'Data',
        delete: '&times',
        deleteTitle: 'Delete filtering rule',
        left: '<',
        leftTitle: 'Outdent criteria',
        logicAnd: 'And',
        logicOr: 'Or',
        right: '>',
        rightTitle: 'Indent criteria',
        search: 'Search',
        title: {
            0: 'Custom Search Builder',
            _: 'Custom Search Builder (%d)'
        },
        value: 'Value',
        valueJoiner: 'and'
    },
    logic: 'AND',
    orthogonal: {
        display: 'display',
        search: 'filter'
    },
    preDefined: false
};


DataTable.SearchBuilder = SearchBuilder;
DataTable.Group = Group;
DataTable.Criteria = Criteria;
// Set up object for plugins
DataTable.ext.searchBuilder = {
    conditions: {}
};
DataTable.ext.buttons.searchBuilder = {
    action(e, dt, node, config) {
        this.popover(config._searchBuilder.getNode(), {
            align: 'container',
            span: 'container'
        });
        let topGroup = config._searchBuilder.s.topGroup;
        // Need to redraw the contents to calculate the correct positions for
        // the elements
        if (topGroup !== undefined) {
            topGroup.dom.container.trigger('dtsb-redrawContents-noDraw');
        }
        if (topGroup.s.criteria.length === 0) {
            Dom.s('.' + Group.classes.add.replace(/ /g, '.')).trigger('click');
        }
    },
    config: {},
    init(dt, node, config) {
        let that = this;
        let sb = new DataTable.SearchBuilder(dt, config.config);
        dt.on('draw', function () {
            let count = sb.s.topGroup ? sb.s.topGroup.count() : 0;
            that.text(dt.i18n('searchBuilder.button', sb.c.i18n.button, count));
        });
        that.text(config.text || dt.i18n('searchBuilder.button', sb.c.i18n.button, 0));
        config._searchBuilder = sb;
    },
    text: null
};
Api.register('searchBuilder.getDetails()', function (deFormatDates = false) {
    let ctx = this.context[0];
    // If SearchBuilder has not been initialised on this instance then return
    return ctx._searchBuilder
        ? ctx._searchBuilder.getDetails(deFormatDates)
        : null;
});
Api.register('searchBuilder.rebuild()', function (details, redraw = true) {
    let ctx = this.context[0];
    // If SearchBuilder has not been initialised on this instance then return
    if (ctx._searchBuilder === undefined) {
        return null;
    }
    ctx._searchBuilder.rebuild(details, redraw);
    return this;
});
Api.register('searchBuilder.container()', function () {
    let ctx = this.context[0];
    // If SearchBuilder has not been initialised on this instance then return
    return ctx._searchBuilder ? ctx._searchBuilder.getNode() : null;
});
/**
 * Init function for SearchBuilder
 *
 * @param settings the settings to be applied
 * @param options the options for SearchBuilder
 * @returns Returns the node of the SearchBuilder
 */
function _init(settings, options) {
    let api = new DataTable.Api(settings);
    let opts = options
        ? options
        : api.init().searchBuilder || DataTable.defaults.searchBuilder;
    let searchBuilder = new SearchBuilder(api, opts);
    let node = searchBuilder.getNode();
    return node;
}
// Attach a listener to the document which listens for DataTables initialisation
// events so we can automatically initialise
Dom.s(document).on('preInit.dt.dtsp', function (e, settings) {
    if (e.namespace !== 'dt') {
        return;
    }
    if (settings.init.searchBuilder || DataTable.defaults.searchBuilder) {
        if (!settings._searchBuilder) {
            _init(settings);
        }
    }
});
// DataTables legacy `dom` option
DataTable.ext.feature.push({
    cFeature: 'Q',
    fnInit: _init
});
DataTable.feature.register('searchBuilder', _init);


return DataTable;
}));
