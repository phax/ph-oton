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
package com.helger.photon.uictrls.datatables.supplementary.tools;

import java.io.File;

import javax.annotation.Nonnull;

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
public final class InternalDataTablesDownloader
{
  private InternalDataTablesDownloader ()
  {}

  public static void downloadDataTables (@Nonnull final String sHTML) throws Exception
  {
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
