/*
 * Copyright (C) 2014-2025 Philip Helger (www.helger.com)
 * philip[at]helger[dot]com
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
/*! SearchHighlight for DataTables v1.0.1
 * 2014 SpryMedia Ltd - datatables.net/license
 */

/**
 * @summary     SearchHighlight
 * @description Search term highlighter for DataTables
 * @version     1.0.1
 * @file        dataTables.searchHighlight.js
 * @author      SpryMedia Ltd (www.sprymedia.co.uk)
 * @contact     www.sprymedia.co.uk/contact
 * @copyright   Copyright 2014 SpryMedia Ltd.
 * 
 * License      MIT - http://datatables.net/license/mit
 *
 * This feature plug-in for DataTables will highlight search terms in the
 * DataTable as they are entered into the main search input element, or via the
 * `search()` API method.
 *
 * It depends upon the jQuery Highlight plug-in by Bartek Szopka:
 *    http://bartaz.github.io/sandbox.js/jquery.highlight.js
 *
 * Search highlighting in DataTables can be enabled by:
 *
 * * Adding the class `searchHighlight` to the HTML table
 * * Setting the `searchHighlight` parameter in the DataTables initialisation to
 *   be true
 * * Setting the `searchHighlight` parameter to be true in the DataTables
 *   defaults (thus causing all tables to have this feature) - i.e.
 *   `$.fn.dataTable.defaults.searchHighlight = true`.
 *
 * For more detailed information please see:
 *     http://datatables.net/blog/2014-10-22
 */

(function(window, document, $){


function highlight( body, table )
{
  // Removing the old highlighting first
  body.unhighlight();

  // Don't highlight the "not found" row, so we get the rows using the api
  if ( table.rows( { filter: 'applied' } ).data().length ) {
    body.highlight( table.search().trim().split (/\s+/) );
  }
}

// Listen for DataTables initialisations
$(document).on( 'init.dt.dth', function (e, settings, json) {
  var table = new $.fn.dataTable.Api( settings );
  var body = $( table.table().body() );

  if (
    $( table.table().node() ).hasClass( 'searchHighlight' ) || // table has class
    settings.oInit.searchHighlight                          || // option specified
    $.fn.dataTable.defaults.searchHighlight                    // default set
  ) {
    table
      .on( 'draw.dt.dth column-visibility.dt.dth column-reorder.dt.dth', function () {
        highlight( body, table );
      } )
      .on( 'destroy', function () {
        // Remove event handler
        table.off( 'draw.dt.dth column-visibility.dt.dth column-reorder.dt.dth' );
      } );

    // initial highlight for state saved conditions and initial states
    if ( table.search() ) {
      highlight( body, table );
    }
  }
} );


})(window, document, jQuery);
