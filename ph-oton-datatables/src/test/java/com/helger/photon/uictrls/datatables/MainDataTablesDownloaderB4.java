/*
 * Copyright (C) 2014-2026 Philip Helger (www.helger.com)
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
package com.helger.photon.uictrls.datatables;

import com.helger.photon.uictrls.datatables.supplementary.tools.InternalDataTablesDownloader;

/**
 * Download the DT components from https://www.datatables.net/download/index via the String in the
 * CDN tab. No Minify, no concatenate
 *
 * @author Philip Helger
 */
public class MainDataTablesDownloaderB4
{
  public static void main (final String [] args) throws Exception
  {
    // 4 runs:
    // 1. Run with default styling
    // 2. Run with Bootstrap4 styling
    // 3. Run with Bootstrap5 styling
    // 4. Run with Bootstrap3 styling (in other project)
    // Note: CardView and Editor are deliberately excluded - they are licensed under
    // "DataTables Plus" (https://datatables.net/license/plus) and must not be redistributed
    // Note: SearchPanes and StateRestore are not yet available for DataTables 3
    final String sHTML = """
        <link href="https://cdn.datatables.net/3.0.2/css/dataTables.bootstrap4.css" rel="stylesheet">
        <link href="https://cdn.datatables.net/autofill/3.0.0/css/autoFill.bootstrap4.css" rel="stylesheet">
        <link href="https://cdn.datatables.net/buttons/4.0.2/css/buttons.bootstrap4.css" rel="stylesheet">
        <link href="https://cdn.datatables.net/colreorder/3.0.1/css/colReorder.bootstrap4.css" rel="stylesheet">
        <link href="https://cdn.datatables.net/columncontrol/2.0.1/css/columnControl.bootstrap4.css" rel="stylesheet">
        <link href="https://cdn.datatables.net/datetime/2.0.0/css/dataTables.dateTime.css" rel="stylesheet">
        <link href="https://cdn.datatables.net/fixedcolumns/6.0.0/css/fixedColumns.bootstrap4.css" rel="stylesheet">
        <link href="https://cdn.datatables.net/fixedheader/5.0.0/css/fixedHeader.bootstrap4.css" rel="stylesheet">
        <link href="https://cdn.datatables.net/keytable/3.0.0/css/keyTable.bootstrap4.css" rel="stylesheet">
        <link href="https://cdn.datatables.net/responsive/4.0.2/css/responsive.bootstrap4.css" rel="stylesheet">
        <link href="https://cdn.datatables.net/rowgroup/2.0.0/css/rowGroup.bootstrap4.css" rel="stylesheet">
        <link href="https://cdn.datatables.net/rowreorder/2.0.0/css/rowReorder.bootstrap4.css" rel="stylesheet">
        <link href="https://cdn.datatables.net/scroller/3.0.0/css/scroller.bootstrap4.css" rel="stylesheet">
        <link href="https://cdn.datatables.net/searchbuilder/2.0.0/css/searchBuilder.bootstrap4.css" rel="stylesheet">
        <link href="https://cdn.datatables.net/select/4.0.1/css/select.bootstrap4.css" rel="stylesheet">

        <script src="https://cdn.datatables.net/3.0.2/js/dataTables.js"></script>
        <script src="https://cdn.datatables.net/3.0.2/js/dataTables.bootstrap4.js"></script>
        <script src="https://cdn.datatables.net/autofill/3.0.0/js/dataTables.autoFill.js"></script>
        <script src="https://cdn.datatables.net/autofill/3.0.0/js/autoFill.bootstrap4.js"></script>
        <script src="https://cdn.datatables.net/buttons/4.0.2/js/dataTables.buttons.js"></script>
        <script src="https://cdn.datatables.net/buttons/4.0.2/js/buttons.bootstrap4.js"></script>
        <script src="https://cdn.datatables.net/colreorder/3.0.1/js/dataTables.colReorder.js"></script>
        <script src="https://cdn.datatables.net/colreorder/3.0.1/js/colReorder.bootstrap4.js"></script>
        <script src="https://cdn.datatables.net/columncontrol/2.0.1/js/dataTables.columnControl.js"></script>
        <script src="https://cdn.datatables.net/columncontrol/2.0.1/js/columnControl.bootstrap4.js"></script>
        <script src="https://cdn.datatables.net/datetime/2.0.0/js/dataTables.dateTime.js"></script>
        <script src="https://cdn.datatables.net/fixedcolumns/6.0.0/js/dataTables.fixedColumns.js"></script>
        <script src="https://cdn.datatables.net/fixedcolumns/6.0.0/js/fixedColumns.bootstrap4.js"></script>
        <script src="https://cdn.datatables.net/fixedheader/5.0.0/js/dataTables.fixedHeader.js"></script>
        <script src="https://cdn.datatables.net/fixedheader/5.0.0/js/fixedHeader.bootstrap4.js"></script>
        <script src="https://cdn.datatables.net/keytable/3.0.0/js/dataTables.keyTable.js"></script>
        <script src="https://cdn.datatables.net/keytable/3.0.0/js/keyTable.bootstrap4.js"></script>
        <script src="https://cdn.datatables.net/responsive/4.0.2/js/dataTables.responsive.js"></script>
        <script src="https://cdn.datatables.net/responsive/4.0.2/js/responsive.bootstrap4.js"></script>
        <script src="https://cdn.datatables.net/rowgroup/2.0.0/js/dataTables.rowGroup.js"></script>
        <script src="https://cdn.datatables.net/rowgroup/2.0.0/js/rowGroup.bootstrap4.js"></script>
        <script src="https://cdn.datatables.net/rowreorder/2.0.0/js/dataTables.rowReorder.js"></script>
        <script src="https://cdn.datatables.net/rowreorder/2.0.0/js/rowReorder.bootstrap4.js"></script>
        <script src="https://cdn.datatables.net/scroller/3.0.0/js/dataTables.scroller.js"></script>
        <script src="https://cdn.datatables.net/scroller/3.0.0/js/scroller.bootstrap4.js"></script>
        <script src="https://cdn.datatables.net/searchbuilder/2.0.0/js/dataTables.searchBuilder.js"></script>
        <script src="https://cdn.datatables.net/searchbuilder/2.0.0/js/searchBuilder.bootstrap4.js"></script>
        <script src="https://cdn.datatables.net/select/4.0.1/js/dataTables.select.js"></script>
        <script src="https://cdn.datatables.net/select/4.0.1/js/select.bootstrap4.js"></script>
                """;

    InternalDataTablesDownloader.downloadDataTables (sHTML, ".bootstrap4.");
  }
}
