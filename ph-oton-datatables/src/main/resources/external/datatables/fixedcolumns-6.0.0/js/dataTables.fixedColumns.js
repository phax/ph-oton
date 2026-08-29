/*! FixedColumns 6.0.0 for DataTables
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
var util = DataTable.util;

class FixedColumns {
    constructor(settings, opts) {
        // Check that the required version of DataTables is included
        if (!DataTable ||
            !DataTable.versionCheck ||
            !DataTable.versionCheck('2')) {
            throw new Error('FixedColumns requires DataTables 2 or newer');
        }
        let table = new DataTable.Api(settings);
        this.classes = util.object.assignDeep({}, FixedColumns.classes);
        // Get options from user
        this.c = util.object.assignDeep({}, FixedColumns.defaults, opts);
        this.s = {
            dt: table,
            rtl: Dom.s(table.table().node()).css('direction') === 'rtl'
        };
        // Backwards compatibility for deprecated options
        if (opts && opts.leftColumns !== undefined) {
            opts.left = opts.leftColumns;
        }
        if (opts && opts.left !== undefined) {
            this.c[this.s.rtl ? 'end' : 'start'] = opts.left;
        }
        if (opts && opts.rightColumns !== undefined) {
            opts.right = opts.rightColumns;
        }
        if (opts && opts.right !== undefined) {
            this.c[this.s.rtl ? 'start' : 'end'] = opts.right;
        }
        this.dom = {
            bottomBlocker: Dom.c('div').classAdd(this.classes.bottomBlocker),
            topBlocker: Dom.c('div').classAdd(this.classes.topBlocker),
            scroller: Dom
                .s(this.s.dt.table().container())
                .find('div.dt-scroll-body')
        };
        if (this.s.dt.settings()[0].initDone) {
            // Fixed Columns Initialisation
            this._addStyles();
            this._setKeyTableListener();
        }
        else {
            table.one('init.dt.dtfc', () => {
                // Fixed Columns Initialisation
                this._addStyles();
                this._setKeyTableListener();
            });
        }
        // Lots or reasons to redraw the column styles
        table.on('column-sizing.dt.dtfc column-reorder.dt.dtfc draw.dt.dtfc', () => this._addStyles());
        // Column visibility can trigger a number of times quickly, so we debounce it
        let debounced = DataTable.util.debounce(() => {
            this._addStyles();
        }, 50);
        table.on('column-visibility.dt.dtfc', () => {
            debounced();
        });
        // Add classes to indicate scrolling state for styling
        this.dom.scroller.on('scroll.dtfc', () => this._scroll());
        this._scroll();
        // Make class available through dt object
        table.settings()[0]._fixedColumns = this;
        table.on('destroy', () => this._destroy());
        return this;
    }
    end(newVal) {
        // If the value is to change
        if (newVal !== undefined) {
            if (newVal >= 0 && newVal <= this.s.dt.columns().count()) {
                // Set the new values and redraw the columns
                this.c.end = newVal;
                this._addStyles();
            }
            return this;
        }
        return this.c.end;
    }
    /**
     * Left fix - accounting for RTL
     *
     * @param count Columns to fix, or undefined for getter
     */
    left(count) {
        return this.s.rtl ? this.end(count) : this.start(count);
    }
    /**
     * Right fix - accounting for RTL
     *
     * @param count Columns to fix, or undefined for getter
     */
    right(count) {
        return this.s.rtl ? this.start(count) : this.end(count);
    }
    start(newVal) {
        // If the value is to change
        if (newVal !== undefined) {
            if (newVal >= 0 && newVal <= this.s.dt.columns().count()) {
                // Set the new values and redraw the columns
                this.c.start = newVal;
                this._addStyles();
            }
            return this;
        }
        return this.c.start;
    }
    /**
     * Iterates over the columns, fixing the appropriate ones to the left and right
     */
    _addStyles() {
        let dt = this.s.dt;
        let that = this;
        let colCount = this.s.dt.columns(':visible').count();
        let headerStruct = dt.table().header.structure(':visible');
        let footerStruct = dt.table().footer.structure(':visible');
        let widths = dt.columns(':visible').widths().toArray();
        let wrapper = Dom.s(dt.table().node()).closest('div.dt-scroll');
        let scroller = Dom
            .s(dt.table().node())
            .closest('div.dt-scroll-body')
            .get(0);
        let rtl = this.s.rtl;
        let start = this.c.start;
        let end = this.c.end;
        let left = rtl ? end : start;
        let right = rtl ? start : end;
        let barWidth = dt.settings()[0].browser.barWidth; // dt internal
        // Do nothing if no scrolling in the DataTable
        if (wrapper.count() === 0) {
            return this;
        }
        // Bar not needed - no vertical scrolling
        if (scroller.offsetWidth === scroller.clientWidth) {
            barWidth = 0;
        }
        // Loop over the visible columns, setting their state
        dt.columns().every(function (colIdx) {
            let visIdx = dt.column.index('toVisible', colIdx);
            let offset;
            // Skip the hidden columns
            if (visIdx === null) {
                return;
            }
            if (visIdx < start) {
                // Fix to the start
                offset = that._sum(widths, visIdx);
                that._fixColumn(visIdx, offset, 'start', headerStruct, footerStruct, barWidth);
            }
            else if (visIdx >= colCount - end) {
                // Fix to the end
                offset = that._sum(widths, colCount - visIdx - 1, true);
                that._fixColumn(visIdx, offset, 'end', headerStruct, footerStruct, barWidth);
            }
            else {
                // Release
                that._fixColumn(visIdx, 0, 'none', headerStruct, footerStruct, barWidth);
            }
        });
        // Apply classes to table to indicate what state we are in
        Dom.s(dt.table().node())
            .classToggle(that.classes.tableFixedStart, start > 0)
            .classToggle(that.classes.tableFixedEnd, end > 0)
            .classToggle(that.classes.tableFixedLeft, left > 0)
            .classToggle(that.classes.tableFixedRight, right > 0);
        // Blocker elements for when scroll bars are always visible
        let headerEl = dt.table().header();
        let footerEl = dt.table().footer();
        let headerHeight = Dom.s(headerEl).height('outer');
        let footerHeight = Dom.s(footerEl).height('outer');
        this.dom.topBlocker
            .appendTo(wrapper)
            .css('top', '0')
            .css(this.s.rtl ? 'left' : 'right', '0')
            .css('height', headerHeight + 'px')
            .css('width', (barWidth + 1) + 'px')
            .css('display', barWidth ? 'block' : 'none');
        if (footerEl) {
            this.dom.bottomBlocker
                .appendTo(wrapper)
                .css('bottom', '0')
                .css(this.s.rtl ? 'left' : 'right', '0')
                .css('height', footerHeight + 'px')
                .css('width', (barWidth + 1) + 'px')
                .css('display', barWidth ? 'block' : 'none');
        }
    }
    /**
     * Clean up
     */
    _destroy() {
        this.s.dt.off('.dtfc');
        this.dom.scroller.off('.dtfc');
        Dom.s(this.s.dt.table().node()).classRemove(this.classes.tableScrollingEnd +
            ' ' +
            this.classes.tableScrollingLeft +
            ' ' +
            this.classes.tableScrollingStart +
            ' ' +
            this.classes.tableScrollingRight);
        this.dom.bottomBlocker.remove();
        this.dom.topBlocker.remove();
    }
    /**
     * Fix or unfix a column
     *
     * @param idx Column visible index to operate on
     * @param offset Offset from the start (pixels)
     * @param side start, end or none to unfix a column
     * @param header DT header structure object
     * @param footer DT footer structure object
     */
    _fixColumn(idx, offset, side, header, footer, barWidth) {
        let dt = this.s.dt;
        let applyStyles = (item, part) => {
            if (side === 'none') {
                item.css('position', '')
                    .css('left', '')
                    .css('right', '')
                    .classRemove(this.classes.fixedEnd +
                    ' ' +
                    this.classes.fixedLeft +
                    ' ' +
                    this.classes.fixedRight +
                    ' ' +
                    this.classes.fixedStart);
            }
            else {
                let positionSide = side === 'start' ? 'left' : 'right';
                if (this.s.rtl) {
                    positionSide = side === 'start' ? 'right' : 'left';
                }
                var off = offset;
                if (side === 'end' &&
                    (part === 'header' || part === 'footer')) {
                    off += barWidth;
                }
                item.css('position', 'sticky')
                    .css(positionSide, off + 'px')
                    .classAdd(side === 'start'
                    ? this.classes.fixedStart
                    : this.classes.fixedEnd)
                    .classAdd(positionSide === 'left'
                    ? this.classes.fixedLeft
                    : this.classes.fixedRight);
            }
        };
        header.forEach(row => {
            if (row[idx]) {
                applyStyles(Dom.s(row[idx].cell), 'header');
            }
        });
        applyStyles(Dom.s(dt
            .column(idx + ':visible', { page: 'current' })
            .nodes()
            .toArray()), 'body');
        if (footer) {
            footer.forEach(row => {
                if (row[idx]) {
                    applyStyles(Dom.s(row[idx].cell), 'footer');
                }
            });
        }
    }
    /**
     * Update classes on the table to indicate if the table is scrolling or not
     */
    _scroll() {
        let scroller = this.dom.scroller.get(0);
        // Not a scrolling table
        if (!scroller) {
            return;
        }
        // Need to update the classes on potentially multiple table tags. There is the
        // main one, the scrolling ones and if FixedHeader is active, the holding
        // position ones! jQuery will deduplicate for us.
        let table = Dom
            .s(this.s.dt.table().node())
            .add(this.s.dt.table().header().parentNode)
            .add(this.s.dt.table().footer().parentNode)
            .add(Dom
            .s(this.s.dt.table().container())
            .find('div.dt-scroll-headInner table')
            .get(0))
            .add(Dom
            .s(this.s.dt.table().container())
            .find('div.dt-scroll-footInner table')
            .get(0));
        let scrollLeft = scroller.scrollLeft; // 0 when fully scrolled left
        let ltr = !this.s.rtl;
        let scrollStart = scrollLeft !== 0;
        let scrollEnd = scroller.scrollWidth >
            scroller.clientWidth + Math.abs(scrollLeft) + 1; // extra 1 for Chrome
        table.classToggle(this.classes.tableScrollingStart, scrollStart);
        table.classToggle(this.classes.tableScrollingEnd, scrollEnd);
        table.classToggle(this.classes.tableScrollingLeft, (scrollStart && ltr) || (scrollEnd && !ltr));
        table.classToggle(this.classes.tableScrollingRight, (scrollEnd && ltr) || (scrollStart && !ltr));
    }
    _setKeyTableListener() {
        this.s.dt.on('key-focus.dt.dtfc', (e, dt, cell) => {
            let currScroll;
            let cellPos = Dom.s(cell.node()).offset();
            let scroller = this.dom.scroller.get(0);
            let scroll = Dom
                .s(this.s.dt.table().node())
                .closest('div.dt-scroll-body');
            // If there are fixed columns to the left
            if (this.c.start > 0) {
                // Get the rightmost left fixed column header, it's position and it's width
                let rightMost = Dom.s(this.s.dt.column(this.c.start - 1).header());
                let rightMostPos = rightMost.offset();
                let rightMostWidth = rightMost.width('outer');
                // If the current highlighted cell is left of the rightmost cell on the screen
                if (Dom.s(cell.node()).classHas(this.classes.fixedLeft)) {
                    // Fixed columns have the scrollbar at the start, always
                    scroll.scrollLeft(0);
                }
                else if (cellPos.left < rightMostPos.left + rightMostWidth) {
                    // Scroll it into view
                    currScroll = scroll.scrollLeft();
                    scroll.scrollLeft(currScroll -
                        (rightMostPos.left + rightMostWidth - cellPos.left));
                }
            }
            // If there are fixed columns to the right
            if (this.c.end > 0) {
                // Get the number of columns and the width of the cell as doing right side calc
                let numCols = this.s.dt.columns().data().toArray().length;
                let cellWidth = Dom.s(cell.node()).width('outer');
                // Get the leftmost right fixed column header and it's position
                let leftMost = Dom.s(this.s.dt.column(numCols - this.c.end).header());
                let leftMostPos = leftMost.offset();
                // If the current highlighted cell is right of the leftmost cell on the screen
                if (Dom.s(cell.node()).classHas(this.classes.fixedRight)) {
                    scroll.scrollLeft(scroller.scrollWidth - scroller.clientWidth);
                }
                else if (cellPos.left + cellWidth > leftMostPos.left) {
                    // Scroll it into view
                    currScroll = scroll.scrollLeft();
                    scroll.scrollLeft(currScroll -
                        (leftMostPos.left - (cellPos.left + cellWidth)));
                }
            }
        });
    }
    /**
     * Sum a range of values from an array
     *
     * @param widths
     * @param index
     * @returns
     */
    _sum(widths, index, reverse = false) {
        if (reverse) {
            widths = widths.slice().reverse();
        }
        return widths.slice(0, index).reduce((accum, val) => accum + val, 0);
    }
}
FixedColumns.version = '6.0.0';
FixedColumns.classes = {
    bottomBlocker: 'dtfc-bottom-blocker',
    fixedEnd: 'dtfc-fixed-end',
    fixedLeft: 'dtfc-fixed-left',
    fixedRight: 'dtfc-fixed-right',
    fixedStart: 'dtfc-fixed-start',
    tableFixedEnd: 'dtfc-has-end',
    tableFixedLeft: 'dtfc-has-left',
    tableFixedRight: 'dtfc-has-right',
    tableFixedStart: 'dtfc-has-start',
    tableScrollingEnd: 'dtfc-scrolling-end',
    tableScrollingLeft: 'dtfc-scrolling-left',
    tableScrollingRight: 'dtfc-scrolling-right',
    tableScrollingStart: 'dtfc-scrolling-start',
    topBlocker: 'dtfc-top-blocker'
};
FixedColumns.defaults = {
    i18n: {
        button: 'FixedColumns'
    },
    start: 1,
    end: 0
};


DataTable.FixedColumns = FixedColumns;
const apiRegister = DataTable.Api.register;
apiRegister('fixedColumns()', function () {
    return this.inst(this.context);
});
apiRegister('fixedColumns().start()', function (newVal) {
    let ctx = this.context[0];
    if (newVal !== undefined) {
        ctx._fixedColumns.start(newVal);
        return this;
    }
    else {
        return ctx._fixedColumns.start();
    }
});
apiRegister('fixedColumns().end()', function (newVal) {
    let ctx = this.context[0];
    if (newVal !== undefined) {
        ctx._fixedColumns.end(newVal);
        return this;
    }
    else {
        return ctx._fixedColumns.end();
    }
});
apiRegister('fixedColumns().left()', function (newVal) {
    let ctx = this.context[0];
    if (newVal !== undefined) {
        ctx._fixedColumns.left(newVal);
        return this;
    }
    else {
        return ctx._fixedColumns.left();
    }
});
apiRegister('fixedColumns().right()', function (newVal) {
    let ctx = this.context[0];
    if (newVal !== undefined) {
        ctx._fixedColumns.right(newVal);
        return this;
    }
    else {
        return ctx._fixedColumns.right();
    }
});
DataTable.ext.buttons.fixedColumns = {
    action(e, dt, node, config) {
        if (Dom.s(node).attr('active')) {
            Dom.s(node).attrRemove('active').classRemove('active');
            dt.fixedColumns().start(0);
            dt.fixedColumns().end(0);
        }
        else {
            Dom.s(node).attr('active', 'true').classAdd('active');
            dt.fixedColumns().start(config.config.start);
            dt.fixedColumns().end(config.config.end);
        }
    },
    config: {
        start: 1,
        end: 0
    },
    init(dt, node, config) {
        if (dt.settings()[0]._fixedColumns === undefined) {
            _init(dt.settings(), config.config);
        }
        Dom.s(node).attr('active', 'true').classAdd('active');
        dt.button(node).text(config.text ||
            dt.i18n('buttons.fixedColumns', dt.settings()[0]._fixedColumns.c.i18n.button));
    },
    text: null
};
function _init(settings, options = null) {
    let api = new DataTable.Api(settings);
    let opts = options
        ? options
        : api.init().fixedColumns || DataTable.defaults.fixedColumns;
    let fixedColumns = new FixedColumns(api, opts);
    return fixedColumns;
}
// Attach a listener to the document which listens for DataTables initialisation
// events so we can automatically initialise
Dom.s(document).on('plugin-init.dt', function (e, settings) {
    if (e.namespace !== 'dt') {
        return;
    }
    if (settings.init.fixedColumns || DataTable.defaults.fixedColumns) {
        if (!settings._fixedColumns) {
            _init(settings, null);
        }
    }
});


return DataTable;
}));
