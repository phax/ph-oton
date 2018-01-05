/**
 * Copyright (C) 2014-2018 Philip Helger (www.helger.com)
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
package com.helger.html;

import javax.annotation.concurrent.Immutable;

import com.helger.html.annotation.SinceHTML5;
import com.helger.xml.microdom.IMicroDocumentType;
import com.helger.xml.microdom.MicroDocumentType;

/**
 * Contains a list of predefined (X)HTML document types.
 *
 * @author Philip Helger
 */
@Immutable
public final class CHTMLDocTypes
{
  public static final String DOCTYPE_XHTML_URI = "http://www.w3.org/1999/xhtml";
  public static final String DOCTYPE_XHTML10_STRICT_QNAME = "-//W3C//DTD XHTML 1.0 Strict//EN";
  public static final String DOCTYPE_XHTML10_STRICT_URI = "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd";
  public static final String DOCTYPE_XHTML10_TRANS_QNAME = "-//W3C//DTD XHTML 1.0 Transitional//EN";
  public static final String DOCTYPE_XHTML10_TRANS_URI = "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd";
  public static final String DOCTYPE_XHTML11_QNAME = "-//W3C//DTD XHTML 1.1//EN";
  public static final String DOCTYPE_XHTML11_URI = "http://www.w3.org/TR/xhtml11/DTD/xhtml11.dtd";

  public static final IMicroDocumentType DOCTYPE_XHTML10_STRICT = new MicroDocumentType (EHTMLElement.HTML.getElementName (),
                                                                                         DOCTYPE_XHTML10_STRICT_QNAME,
                                                                                         DOCTYPE_XHTML10_STRICT_URI);
  public static final IMicroDocumentType DOCTYPE_XHTML10_TRANS = new MicroDocumentType (EHTMLElement.HTML.getElementName (),
                                                                                        DOCTYPE_XHTML10_TRANS_QNAME,
                                                                                        DOCTYPE_XHTML10_TRANS_URI);
  public static final IMicroDocumentType DOCTYPE_XHTML11 = new MicroDocumentType (EHTMLElement.HTML.getElementName (),
                                                                                  DOCTYPE_XHTML11_QNAME,
                                                                                  DOCTYPE_XHTML11_URI);
  @SinceHTML5
  public static final IMicroDocumentType DOCTYPE_HTML5 = new HTML5DocumentType ();

  private CHTMLDocTypes ()
  {}
}
