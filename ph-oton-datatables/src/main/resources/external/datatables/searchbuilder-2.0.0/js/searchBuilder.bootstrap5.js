/*! SearchBuilder Bootstrap 5 styling 2.0.0 for DataTables
 * Copyright (c) SpryMedia Ltd - datatables.net/license
 */

(function(factory){
	if (typeof define === 'function' && define.amd) {
		// AMD
		define(['datatables.net-bs5', 'datatables.net-searchbuilder'], function (dt) {
			return factory(window, document, dt);
		});
	}
	else if (typeof exports === 'object') {
		// CommonJS
		var cjsRequires = function (root) {
			if (! root.DataTable) {
				require('datatables.net-bs5')(root);
			}

			if (! window.DataTable.SearchBuilder) {
				require('datatables.net-searchbuilder')(root);
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


Object.assign(DataTable.SearchBuilder.classes, {
    clearAll: 'btn btn-secondary dtsb-clearAll'
});
Object.assign(DataTable.Group.classes, {
    add: 'btn btn-secondary dtsb-add',
    clearGroup: 'btn btn-secondary dtsb-clearGroup',
    logic: 'btn btn-secondary dtsb-logic',
    search: 'btn btn-secondary dtsb-search'
});
Object.assign(DataTable.Criteria.classes, {
    condition: 'form-select dtsb-condition',
    data: 'dtsb-data form-select',
    delete: 'btn btn-secondary dtsb-delete',
    input: 'form-control dtsb-input',
    left: 'btn btn-secondary dtsb-left',
    right: 'btn btn-secondary dtsb-right',
    select: 'form-select',
    value: 'dtsb-value'
});


return DataTable;
}));
