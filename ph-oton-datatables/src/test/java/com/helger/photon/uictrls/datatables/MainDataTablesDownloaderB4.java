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
    final String sHTML = "<link href=\"https://cdn.datatables.net/2.3.5/css/dataTables.bootstrap4.css\" rel=\"stylesheet\">\n" +
                         "<link href=\"https://cdn.datatables.net/autofill/2.7.1/css/autoFill.bootstrap4.css\" rel=\"stylesheet\">\n" +
                         "<link href=\"https://cdn.datatables.net/buttons/3.2.5/css/buttons.bootstrap4.css\" rel=\"stylesheet\">\n" +
                         "<link href=\"https://cdn.datatables.net/colreorder/2.1.2/css/colReorder.bootstrap4.css\" rel=\"stylesheet\">\n" +
                         "<link href=\"https://cdn.datatables.net/columncontrol/1.1.1/css/columnControl.bootstrap4.css\" rel=\"stylesheet\">\n" +
                         "<link href=\"https://cdn.datatables.net/datetime/1.6.2/css/dataTables.dateTime.css\" rel=\"stylesheet\">\n" +
                         "<link href=\"https://cdn.datatables.net/fixedcolumns/5.0.5/css/fixedColumns.bootstrap4.css\" rel=\"stylesheet\">\n" +
                         "<link href=\"https://cdn.datatables.net/fixedheader/4.0.5/css/fixedHeader.bootstrap4.css\" rel=\"stylesheet\">\n" +
                         "<link href=\"https://cdn.datatables.net/keytable/2.12.2/css/keyTable.bootstrap4.css\" rel=\"stylesheet\">\n" +
                         "<link href=\"https://cdn.datatables.net/responsive/3.0.7/css/responsive.bootstrap4.css\" rel=\"stylesheet\">\n" +
                         "<link href=\"https://cdn.datatables.net/rowgroup/1.6.0/css/rowGroup.bootstrap4.css\" rel=\"stylesheet\">\n" +
                         "<link href=\"https://cdn.datatables.net/rowreorder/1.5.0/css/rowReorder.bootstrap4.css\" rel=\"stylesheet\">\n" +
                         "<link href=\"https://cdn.datatables.net/scroller/2.4.3/css/scroller.bootstrap4.css\" rel=\"stylesheet\">\n" +
                         "<link href=\"https://cdn.datatables.net/searchbuilder/1.8.4/css/searchBuilder.bootstrap4.css\" rel=\"stylesheet\">\n" +
                         "<link href=\"https://cdn.datatables.net/searchpanes/2.3.5/css/searchPanes.bootstrap4.css\" rel=\"stylesheet\">\n" +
                         "<link href=\"https://cdn.datatables.net/select/3.1.3/css/select.bootstrap4.css\" rel=\"stylesheet\">\n" +
                         "<link href=\"https://cdn.datatables.net/staterestore/1.4.3/css/stateRestore.bootstrap4.css\" rel=\"stylesheet\">\n" +
                         " \n" +
                         "<script src=\"https://cdnjs.cloudflare.com/ajax/libs/jszip/3.10.1/jszip.js\"></script>\n" +
                         "<script src=\"https://cdn.datatables.net/2.3.5/js/dataTables.js\"></script>\n" +
                         "<script src=\"https://cdn.datatables.net/2.3.5/js/dataTables.bootstrap4.js\"></script>\n" +
                         "<script src=\"https://cdn.datatables.net/autofill/2.7.1/js/dataTables.autoFill.js\"></script>\n" +
                         "<script src=\"https://cdn.datatables.net/autofill/2.7.1/js/autoFill.bootstrap4.js\"></script>\n" +
                         "<script src=\"https://cdn.datatables.net/buttons/3.2.5/js/dataTables.buttons.js\"></script>\n" +
                         "<script src=\"https://cdn.datatables.net/buttons/3.2.5/js/buttons.bootstrap4.js\"></script>\n" +
                         "<script src=\"https://cdn.datatables.net/buttons/3.2.5/js/buttons.colVis.js\"></script>\n" +
                         "<script src=\"https://cdn.datatables.net/buttons/3.2.5/js/buttons.html5.js\"></script>\n" +
                         "<script src=\"https://cdn.datatables.net/buttons/3.2.5/js/buttons.print.js\"></script>\n" +
                         "<script src=\"https://cdn.datatables.net/colreorder/2.1.2/js/dataTables.colReorder.js\"></script>\n" +
                         "<script src=\"https://cdn.datatables.net/columncontrol/1.1.1/js/dataTables.columnControl.js\"></script>\n" +
                         "<script src=\"https://cdn.datatables.net/columncontrol/1.1.1/js/columnControl.bootstrap4.js\"></script>\n" +
                         "<script src=\"https://cdn.datatables.net/datetime/1.6.2/js/dataTables.dateTime.js\"></script>\n" +
                         "<script src=\"https://cdn.datatables.net/fixedcolumns/5.0.5/js/dataTables.fixedColumns.js\"></script>\n" +
                         "<script src=\"https://cdn.datatables.net/fixedheader/4.0.5/js/dataTables.fixedHeader.js\"></script>\n" +
                         "<script src=\"https://cdn.datatables.net/keytable/2.12.2/js/dataTables.keyTable.js\"></script>\n" +
                         "<script src=\"https://cdn.datatables.net/responsive/3.0.7/js/dataTables.responsive.js\"></script>\n" +
                         "<script src=\"https://cdn.datatables.net/responsive/3.0.7/js/responsive.bootstrap4.js\"></script>\n" +
                         "<script src=\"https://cdn.datatables.net/rowgroup/1.6.0/js/dataTables.rowGroup.js\"></script>\n" +
                         "<script src=\"https://cdn.datatables.net/rowreorder/1.5.0/js/dataTables.rowReorder.js\"></script>\n" +
                         "<script src=\"https://cdn.datatables.net/scroller/2.4.3/js/dataTables.scroller.js\"></script>\n" +
                         "<script src=\"https://cdn.datatables.net/searchbuilder/1.8.4/js/dataTables.searchBuilder.js\"></script>\n" +
                         "<script src=\"https://cdn.datatables.net/searchbuilder/1.8.4/js/searchBuilder.bootstrap4.js\"></script>\n" +
                         "<script src=\"https://cdn.datatables.net/searchpanes/2.3.5/js/dataTables.searchPanes.js\"></script>\n" +
                         "<script src=\"https://cdn.datatables.net/searchpanes/2.3.5/js/searchPanes.bootstrap4.js\"></script>\n" +
                         "<script src=\"https://cdn.datatables.net/select/3.1.3/js/dataTables.select.js\"></script>\n" +
                         "<script src=\"https://cdn.datatables.net/staterestore/1.4.3/js/dataTables.stateRestore.js\"></script>\n" +
                         "<script src=\"https://cdn.datatables.net/staterestore/1.4.3/js/stateRestore.bootstrap4.js\"></script>";

    InternalDataTablesDownloader.downloadDataTables (sHTML, ".bootstrap4.");
  }
}
