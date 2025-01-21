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
package com.helger.html.entity;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.xml.sax.InputSource;

import com.helger.commons.io.stream.NonBlockingByteArrayOutputStream;
import com.helger.commons.io.stream.StreamHelper;
import com.helger.html.CHTMLDocTypes;
import com.helger.xml.EXMLParserFeature;
import com.helger.xml.microdom.IMicroDocument;
import com.helger.xml.microdom.IMicroDocumentType;
import com.helger.xml.microdom.serialize.MicroReader;
import com.helger.xml.serialize.read.SAXReaderSettings;
import com.helger.xml.serialize.write.EXMLIncorrectCharacterHandling;
import com.helger.xml.serialize.write.EXMLSerializeVersion;
import com.helger.xml.serialize.write.XMLEmitter;
import com.helger.xml.serialize.write.XMLWriterSettings;

public final class HTMLEntityResolverTest
{
  private boolean _testResolve (final IMicroDocumentType aDocType)
  {
    return _testResolve ("", aDocType);
  }

  private boolean _testResolve (final String sPrefix, final IMicroDocumentType aDocType)
  {
    final String sHTML = "\n<html><head><title>foo</title></head><body><p>Hallöle&nbsp;B&ouml;ris!</p></body></html>";
    final IMicroDocument doc = MicroReader.readMicroXML (sPrefix +
                                                         XMLEmitter.getDocTypeHTMLRepresentation (EXMLSerializeVersion.XML_10,
                                                                                                  EXMLIncorrectCharacterHandling.DEFAULT,
                                                                                                  aDocType) +
                                                         sHTML,
                                                         new SAXReaderSettings ().setEntityResolver (HTMLEntityResolver.getInstance ())
                                                                                 .setFeatureValue (EXMLParserFeature.VALIDATION,
                                                                                                   true)
                                                                                 .setFeatureValue (EXMLParserFeature.DISALLOW_DOCTYPE_DECL,
                                                                                                   false));
    return doc != null;
  }

  @Test
  public void testHTMLEntityResolve ()
  {
    assertTrue (_testResolve (CHTMLDocTypes.DOCTYPE_XHTML10_STRICT));
    assertTrue (_testResolve (CHTMLDocTypes.DOCTYPE_XHTML10_TRANS));
    assertTrue (_testResolve (CHTMLDocTypes.DOCTYPE_XHTML11));
    assertTrue (_testResolve ("<?xml version=\"1.0\" encoding=\"" + XMLWriterSettings.DEFAULT_XML_CHARSET + "\"?>",
                              CHTMLDocTypes.DOCTYPE_XHTML11));
  }

  @Test
  public void testHTMLEntityResolveError ()
  {
    // because of standalone="yes"!!!
    assertFalse (_testResolve ("<?xml version=\"1.0\" encoding=\"" +
                               XMLWriterSettings.DEFAULT_XML_CHARSET +
                               "\" standalone=\"yes\"?>",
                               CHTMLDocTypes.DOCTYPE_XHTML11));
  }

  @Test
  public void testResolveAll ()
  {
    for (final String sPublicId : HTMLEntityResolver.getInstance ().getAllPublicIds ())
      assertNotNull (HTMLEntityResolver.getInstance ().resolveEntity (sPublicId));
  }

  @Test
  public void testResolveMoreThanOnce ()
  {
    // read 1.
    final InputSource aIS1 = HTMLEntityResolver.getInstance ().resolveEntity ("-//W3C//ENTITIES Latin 1 for XHTML//EN");
    assertNotNull (aIS1);
    final NonBlockingByteArrayOutputStream aBAOS1 = new NonBlockingByteArrayOutputStream ();
    StreamHelper.copyInputStreamToOutputStream (aIS1.getByteStream (), aBAOS1);
    assertTrue (aBAOS1.size () > 0);

    // read 2.
    final InputSource aIS2 = HTMLEntityResolver.getInstance ().resolveEntity ("-//W3C//ENTITIES Latin 1 for XHTML//EN");
    assertNotNull (aIS2);
    final NonBlockingByteArrayOutputStream aBAOS2 = new NonBlockingByteArrayOutputStream ();
    StreamHelper.copyInputStreamToOutputStream (aIS2.getByteStream (), aBAOS2);
    assertTrue (aBAOS2.size () > 0);

    assertArrayEquals (aBAOS1.toByteArray (), aBAOS2.toByteArray ());
  }
}
