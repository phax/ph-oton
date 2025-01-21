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
package com.helger.photon.uictrls.datatables;

import com.helger.photon.uictrls.datatables.supplementary.tools.InternalDataTablesDownloader;

/**
 * Download the DT components from https://www.datatables.net/download/index via
 * the String in the CDN tab. No Minify, no concatenate
 *
 * @author Philip Helger
 */
public class MainDataTablesDownloaderB4
{
  public static void main (final String [] args) throws Exception
  {
    // 3 runs:
    // 1. Run with default styling
    // 2. Run with Bootstrap4 styling
    // 3. Run with Bootstrap3 styling (in other project)
    final String sHTML = "<link href=\"https://cdn.datatables.net/2.2.1/css/dataTables.bootstrap4.css\" rel=\"stylesheet\">\r\n" +
                         "<link href=\"https://cdn.datatables.net/autofill/2.7.0/css/autoFill.bootstrap4.min.css\" rel=\"stylesheet\">\r\n" +
                         "<link href=\"https://cdn.datatables.net/buttons/3.2.0/css/buttons.bootstrap4.css\" rel=\"stylesheet\">\r\n" +
                         "<link href=\"https://cdn.datatables.net/colreorder/2.0.4/css/colReorder.bootstrap4.css\" rel=\"stylesheet\">\r\n" +
                         "<link href=\"https://cdn.datatables.net/datetime/1.5.5/css/dataTables.dateTime.css\" rel=\"stylesheet\">\r\n" +
                         "<link href=\"https://cdn.datatables.net/fixedcolumns/5.0.4/css/fixedColumns.bootstrap4.css\" rel=\"stylesheet\">\r\n" +
                         "<link href=\"https://cdn.datatables.net/fixedheader/4.0.1/css/fixedHeader.bootstrap4.css\" rel=\"stylesheet\">\r\n" +
                         "<link href=\"https://cdn.datatables.net/keytable/2.12.1/css/keyTable.bootstrap4.css\" rel=\"stylesheet\">\r\n" +
                         "<link href=\"https://cdn.datatables.net/responsive/3.0.3/css/responsive.bootstrap4.css\" rel=\"stylesheet\">\r\n" +
                         "<link href=\"https://cdn.datatables.net/rowgroup/1.5.1/css/rowGroup.bootstrap4.css\" rel=\"stylesheet\">\r\n" +
                         "<link href=\"https://cdn.datatables.net/rowreorder/1.5.0/css/rowReorder.bootstrap4.css\" rel=\"stylesheet\">\r\n" +
                         "<link href=\"https://cdn.datatables.net/scroller/2.4.3/css/scroller.bootstrap4.css\" rel=\"stylesheet\">\r\n" +
                         "<link href=\"https://cdn.datatables.net/searchbuilder/1.8.1/css/searchBuilder.bootstrap4.css\" rel=\"stylesheet\">\r\n" +
                         "<link href=\"https://cdn.datatables.net/searchpanes/2.3.3/css/searchPanes.bootstrap4.css\" rel=\"stylesheet\">\r\n" +
                         "<link href=\"https://cdn.datatables.net/select/3.0.0/css/select.bootstrap4.css\" rel=\"stylesheet\">\r\n" +
                         "<link href=\"https://cdn.datatables.net/staterestore/1.4.1/css/stateRestore.bootstrap4.css\" rel=\"stylesheet\">\r\n" +
                         " \r\n" +
                         "<script src=\"https://cdnjs.cloudflare.com/ajax/libs/jszip/3.10.1/jszip.js\"></script>\r\n" +
                         "<script src=\"https://cdnjs.cloudflare.com/ajax/libs/pdfmake/0.2.7/pdfmake.js\"></script>\r\n" +
                         "<script src=\"https://cdnjs.cloudflare.com/ajax/libs/pdfmake/0.2.7/vfs_fonts.js\"></script>\r\n" +
                         "<script src=\"https://cdn.datatables.net/2.2.1/js/dataTables.js\"></script>\r\n" +
                         "<script src=\"https://cdn.datatables.net/2.2.1/js/dataTables.bootstrap4.js\"></script>\r\n" +
                         "<script src=\"https://cdn.datatables.net/autofill/2.7.0/js/dataTables.autoFill.js\"></script>\r\n" +
                         "<script src=\"https://cdn.datatables.net/autofill/2.7.0/js/autoFill.bootstrap4.js\"></script>\r\n" +
                         "<script src=\"https://cdn.datatables.net/buttons/3.2.0/js/dataTables.buttons.js\"></script>\r\n" +
                         "<script src=\"https://cdn.datatables.net/buttons/3.2.0/js/buttons.bootstrap4.js\"></script>\r\n" +
                         "<script src=\"https://cdn.datatables.net/buttons/3.2.0/js/buttons.colVis.js\"></script>\r\n" +
                         "<script src=\"https://cdn.datatables.net/buttons/3.2.0/js/buttons.html5.js\"></script>\r\n" +
                         "<script src=\"https://cdn.datatables.net/buttons/3.2.0/js/buttons.print.js\"></script>\r\n" +
                         "<script src=\"https://cdn.datatables.net/colreorder/2.0.4/js/dataTables.colReorder.js\"></script>\r\n" +
                         "<script src=\"https://cdn.datatables.net/datetime/1.5.5/js/dataTables.dateTime.js\"></script>\r\n" +
                         "<script src=\"https://cdn.datatables.net/fixedcolumns/5.0.4/js/dataTables.fixedColumns.js\"></script>\r\n" +
                         "<script src=\"https://cdn.datatables.net/fixedheader/4.0.1/js/dataTables.fixedHeader.js\"></script>\r\n" +
                         "<script src=\"https://cdn.datatables.net/keytable/2.12.1/js/dataTables.keyTable.js\"></script>\r\n" +
                         "<script src=\"https://cdn.datatables.net/responsive/3.0.3/js/dataTables.responsive.js\"></script>\r\n" +
                         "<script src=\"https://cdn.datatables.net/responsive/3.0.3/js/responsive.bootstrap4.js\"></script>\r\n" +
                         "<script src=\"https://cdn.datatables.net/rowgroup/1.5.1/js/dataTables.rowGroup.js\"></script>\r\n" +
                         "<script src=\"https://cdn.datatables.net/rowreorder/1.5.0/js/dataTables.rowReorder.js\"></script>\r\n" +
                         "<script src=\"https://cdn.datatables.net/scroller/2.4.3/js/dataTables.scroller.js\"></script>\r\n" +
                         "<script src=\"https://cdn.datatables.net/searchbuilder/1.8.1/js/dataTables.searchBuilder.js\"></script>\r\n" +
                         "<script src=\"https://cdn.datatables.net/searchbuilder/1.8.1/js/searchBuilder.bootstrap4.js\"></script>\r\n" +
                         "<script src=\"https://cdn.datatables.net/searchpanes/2.3.3/js/dataTables.searchPanes.js\"></script>\r\n" +
                         "<script src=\"https://cdn.datatables.net/searchpanes/2.3.3/js/searchPanes.bootstrap4.js\"></script>\r\n" +
                         "<script src=\"https://cdn.datatables.net/select/3.0.0/js/dataTables.select.js\"></script>\r\n" +
                         "<script src=\"https://cdn.datatables.net/staterestore/1.4.1/js/dataTables.stateRestore.js\"></script>\r\n" +
                         "<script src=\"https://cdn.datatables.net/staterestore/1.4.1/js/stateRestore.bootstrap4.js\"></script>";

    InternalDataTablesDownloader.downloadDataTables (sHTML, ".bootstrap4.");
  }
}
