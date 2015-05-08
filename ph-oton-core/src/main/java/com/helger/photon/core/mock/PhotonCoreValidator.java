/**
 * Copyright (C) 2014-2015 Philip Helger (www.helger.com)
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
package com.helger.photon.core.mock;

import java.io.File;

import javax.annotation.concurrent.Immutable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.helger.commons.microdom.IMicroDocument;
import com.helger.commons.microdom.IMicroElement;
import com.helger.commons.microdom.IMicroNode;
import com.helger.commons.microdom.serialize.MicroReader;
import com.helger.commons.microdom.utils.MicroRecursiveIterator;
import com.helger.photon.core.app.html.HTMLConfigManager;
import com.helger.photon.core.mgr.PhotonCoreManager;

@Immutable
public final class PhotonCoreValidator
{
  private static final Logger s_aLogger = LoggerFactory.getLogger (PhotonCoreValidator.class);

  private PhotonCoreValidator ()
  {}

  static void validateWebXML () throws Exception
  {
    final IMicroDocument aDoc = MicroReader.readMicroXML (new File ("src/main/webapp/WEB-INF/web.xml"));
    if (aDoc != null)
      for (final IMicroNode aNode : new MicroRecursiveIterator (aDoc.getDocumentElement ()))
        if (aNode.isElement ())
        {
          final IMicroElement e = (IMicroElement) aNode;
          if (e.getTagName ().endsWith ("-class"))
          {
            final String sClassName = e.getTextContentTrimmed ();
            try
            {
              Class.forName (sClassName);
            }
            catch (final Exception ex)
            {
              s_aLogger.error ("Failed to resolve class '" + sClassName + "'");
              throw ex;
            }
          }
        }
  }

  static void validateHTMLConfiguration ()
  {
    // This will throw an IllegalStateException for wrong files in html/js.xml
    // and html/css.xml
    new HTMLConfigManager (PhotonCoreManager.DIRECTORY_HTML);
  }

  public static void validateExternalResources () throws Exception
  {
    final PhotonCoreWebAppTestRule aRule = new PhotonCoreWebAppTestRule ();

    aRule.before ();
    try
    {
      validateWebXML ();
      validateHTMLConfiguration ();
    }
    finally
    {
      aRule.after ();
    }
  }
}
