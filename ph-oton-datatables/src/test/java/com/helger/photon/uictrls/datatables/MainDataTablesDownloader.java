/*
 * Copyright (C) 2014-2024 Philip Helger (www.helger.com)
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

import java.io.File;

import org.apache.hc.client5.http.classic.methods.HttpGet;

import com.helger.commons.collection.impl.ICommonsList;
import com.helger.commons.io.file.SimpleFileIO;
import com.helger.commons.string.StringHelper;
import com.helger.httpclient.HttpClientManager;
import com.helger.httpclient.response.ResponseHandlerByteArray;
import com.helger.xml.microdom.IMicroDocument;
import com.helger.xml.microdom.IMicroElement;
import com.helger.xml.microdom.serialize.MicroReader;

/**
 * Download the DT components from https://www.datatables.net/download/index via
 * the String in the CDN tab. No Minify, no concatenate
 *
 * @author Philip Helger
 */
public class MainDataTablesDownloader
{
  public static void main (final String [] args) throws Exception
  {
    // 2 runs:
    // 1. Run with default styling
    // 2. Run with Bootstrap4 styling
    final String sHTML = "<link href=\"https://cdn.datatables.net/2.1.3/css/dataTables.bootstrap4.css\" rel=\"stylesheet\">\r\n" +
                         "<link href=\"https://cdn.datatables.net/autofill/2.7.0/css/autoFill.bootstrap4.min.css\" rel=\"stylesheet\">\r\n" +
                         "<link href=\"https://cdn.datatables.net/buttons/3.1.1/css/buttons.bootstrap4.css\" rel=\"stylesheet\">\r\n" +
                         "<link href=\"https://cdn.datatables.net/colreorder/2.0.3/css/colReorder.bootstrap4.css\" rel=\"stylesheet\">\r\n" +
                         "<link href=\"https://cdn.datatables.net/datetime/1.5.3/css/dataTables.dateTime.css\" rel=\"stylesheet\">\r\n" +
                         "<link href=\"https://cdn.datatables.net/fixedcolumns/5.0.1/css/fixedColumns.bootstrap4.css\" rel=\"stylesheet\">\r\n" +
                         "<link href=\"https://cdn.datatables.net/fixedheader/4.0.1/css/fixedHeader.bootstrap4.css\" rel=\"stylesheet\">\r\n" +
                         "<link href=\"https://cdn.datatables.net/keytable/2.12.1/css/keyTable.bootstrap4.css\" rel=\"stylesheet\">\r\n" +
                         "<link href=\"https://cdn.datatables.net/responsive/3.0.2/css/responsive.bootstrap4.css\" rel=\"stylesheet\">\r\n" +
                         "<link href=\"https://cdn.datatables.net/rowgroup/1.5.0/css/rowGroup.bootstrap4.css\" rel=\"stylesheet\">\r\n" +
                         "<link href=\"https://cdn.datatables.net/rowreorder/1.5.0/css/rowReorder.bootstrap4.css\" rel=\"stylesheet\">\r\n" +
                         "<link href=\"https://cdn.datatables.net/scroller/2.4.3/css/scroller.bootstrap4.css\" rel=\"stylesheet\">\r\n" +
                         "<link href=\"https://cdn.datatables.net/searchbuilder/1.7.1/css/searchBuilder.bootstrap4.css\" rel=\"stylesheet\">\r\n" +
                         "<link href=\"https://cdn.datatables.net/searchpanes/2.3.1/css/searchPanes.bootstrap4.css\" rel=\"stylesheet\">\r\n" +
                         "<link href=\"https://cdn.datatables.net/select/2.0.4/css/select.bootstrap4.css\" rel=\"stylesheet\">\r\n" +
                         "<link href=\"https://cdn.datatables.net/staterestore/1.4.1/css/stateRestore.bootstrap4.css\" rel=\"stylesheet\">\r\n" +
                         " \r\n" +
                         "<script src=\"https://cdnjs.cloudflare.com/ajax/libs/jszip/3.10.1/jszip.js\"></script>\r\n" +
                         "<script src=\"https://cdnjs.cloudflare.com/ajax/libs/pdfmake/0.2.7/pdfmake.js\"></script>\r\n" +
                         "<script src=\"https://cdnjs.cloudflare.com/ajax/libs/pdfmake/0.2.7/vfs_fonts.js\"></script>\r\n" +
                         "<script src=\"https://cdn.datatables.net/2.1.3/js/dataTables.js\"></script>\r\n" +
                         "<script src=\"https://cdn.datatables.net/2.1.3/js/dataTables.bootstrap4.js\"></script>\r\n" +
                         "<script src=\"https://cdn.datatables.net/autofill/2.7.0/js/dataTables.autoFill.js\"></script>\r\n" +
                         "<script src=\"https://cdn.datatables.net/autofill/2.7.0/js/autoFill.bootstrap4.js\"></script>\r\n" +
                         "<script src=\"https://cdn.datatables.net/buttons/3.1.1/js/dataTables.buttons.js\"></script>\r\n" +
                         "<script src=\"https://cdn.datatables.net/buttons/3.1.1/js/buttons.bootstrap4.js\"></script>\r\n" +
                         "<script src=\"https://cdn.datatables.net/buttons/3.1.1/js/buttons.colVis.js\"></script>\r\n" +
                         "<script src=\"https://cdn.datatables.net/buttons/3.1.1/js/buttons.html5.js\"></script>\r\n" +
                         "<script src=\"https://cdn.datatables.net/buttons/3.1.1/js/buttons.print.js\"></script>\r\n" +
                         "<script src=\"https://cdn.datatables.net/colreorder/2.0.3/js/dataTables.colReorder.js\"></script>\r\n" +
                         "<script src=\"https://cdn.datatables.net/datetime/1.5.3/js/dataTables.dateTime.js\"></script>\r\n" +
                         "<script src=\"https://cdn.datatables.net/fixedcolumns/5.0.1/js/dataTables.fixedColumns.js\"></script>\r\n" +
                         "<script src=\"https://cdn.datatables.net/fixedheader/4.0.1/js/dataTables.fixedHeader.js\"></script>\r\n" +
                         "<script src=\"https://cdn.datatables.net/keytable/2.12.1/js/dataTables.keyTable.js\"></script>\r\n" +
                         "<script src=\"https://cdn.datatables.net/responsive/3.0.2/js/dataTables.responsive.js\"></script>\r\n" +
                         "<script src=\"https://cdn.datatables.net/responsive/3.0.2/js/responsive.bootstrap4.js\"></script>\r\n" +
                         "<script src=\"https://cdn.datatables.net/rowgroup/1.5.0/js/dataTables.rowGroup.js\"></script>\r\n" +
                         "<script src=\"https://cdn.datatables.net/rowreorder/1.5.0/js/dataTables.rowReorder.js\"></script>\r\n" +
                         "<script src=\"https://cdn.datatables.net/scroller/2.4.3/js/dataTables.scroller.js\"></script>\r\n" +
                         "<script src=\"https://cdn.datatables.net/searchbuilder/1.7.1/js/dataTables.searchBuilder.js\"></script>\r\n" +
                         "<script src=\"https://cdn.datatables.net/searchbuilder/1.7.1/js/searchBuilder.bootstrap4.js\"></script>\r\n" +
                         "<script src=\"https://cdn.datatables.net/searchpanes/2.3.1/js/dataTables.searchPanes.js\"></script>\r\n" +
                         "<script src=\"https://cdn.datatables.net/searchpanes/2.3.1/js/searchPanes.bootstrap4.js\"></script>\r\n" +
                         "<script src=\"https://cdn.datatables.net/select/2.0.4/js/dataTables.select.js\"></script>\r\n" +
                         "<script src=\"https://cdn.datatables.net/staterestore/1.4.1/js/dataTables.stateRestore.js\"></script>\r\n" +
                         "<script src=\"https://cdn.datatables.net/staterestore/1.4.1/js/stateRestore.bootstrap4.js\"></script>";

    try (HttpClientManager hcm = new HttpClientManager ())
    {
      final IMicroDocument doc = MicroReader.readMicroXML ("<a>" +
                                                           sHTML.replace ("stylesheet\">", "stylesheet\"/>") +
                                                           "</a>");
      for (final IMicroElement e : doc.getDocumentElement ().getAllChildElements ())
      {
        String sURL = e.getAttributeValue ("href");
        if (sURL == null)
          sURL = e.getAttributeValue ("src");
        sURL = sURL.replace (".min.css", ".css").replace (".min.js", ".js");

        if (sURL.startsWith ("https://cdnjs.cloudflare.com"))
          continue;

        final boolean bIsJS = sURL.endsWith (".js");

        System.out.println (sURL);

        final ICommonsList <String> parts = StringHelper.getExploded ('/',
                                                                      StringHelper.trimStart (sURL,
                                                                                              "https://cdn.datatables.net/"));
        final String sPlugin, sVersion, sFilename;
        if (parts.size () == 3)
        {
          sPlugin = "datatables";
          sVersion = parts.get (0);
          sFilename = parts.get (2);
        }
        else
        {
          sPlugin = parts.get (0);
          sVersion = parts.get (1);
          sFilename = parts.get (3);
        }

        // Regular
        HttpGet aGet = new HttpGet (sURL);
        byte [] aBytes = hcm.execute (aGet, new ResponseHandlerByteArray ());
        SimpleFileIO.writeFile (new File ("src\\main\\resources\\external\\datatables",
                                          sPlugin + "-" + sVersion + "/" + (bIsJS ? "js" : "css") + "/" + sFilename),
                                aBytes);

        // Minified
        final String sMinifiedURL = sURL.replace (".css", ".min.css").replace (".js", ".min.js");
        final String sMinifiedFilename = sFilename.replace (".css", ".min.css").replace (".js", ".min.js");
        aGet = new HttpGet (sMinifiedURL);
        aBytes = hcm.execute (aGet, new ResponseHandlerByteArray ());
        SimpleFileIO.writeFile (new File ("src\\main\\resources\\external\\datatables",
                                          sPlugin +
                                                                                        "-" +
                                                                                        sVersion +
                                                                                        "/" +
                                                                                        (bIsJS ? "js" : "css") +
                                                                                        "/" +
                                                                                        sMinifiedFilename),
                                aBytes);
      }
    }
  }
}
