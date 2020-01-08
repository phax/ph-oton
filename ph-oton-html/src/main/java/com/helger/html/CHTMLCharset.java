/**
 * Copyright (C) 2014-2020 Philip Helger (www.helger.com)
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

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Locale;

import javax.annotation.concurrent.Immutable;

import com.helger.commons.CGlobal;
import com.helger.commons.annotation.PresentForCodeCoverage;
import com.helger.xml.serialize.write.XMLWriterSettings;

/**
 * Contains the preferred charsets to be used for HTML/JS and CSS.
 *
 * @author Philip Helger
 */
@Immutable
public final class CHTMLCharset
{
  /** HTML charset */
  public static final Charset CHARSET_HTML_OBJ = XMLWriterSettings.DEFAULT_XML_CHARSET_OBJ;
  /** CSS charset */
  public static final Charset CHARSET_CSS_OBJ = StandardCharsets.UTF_8;
  /** JS charset */
  public static final Charset CHARSET_JS_OBJ = StandardCharsets.UTF_8;

  /** The locale to be used for upper/lowercasing stuff */
  public static final Locale LOCALE = CGlobal.DEFAULT_LOCALE;

  @PresentForCodeCoverage
  private static final CHTMLCharset s_aInstance = new CHTMLCharset ();

  private CHTMLCharset ()
  {}
}
