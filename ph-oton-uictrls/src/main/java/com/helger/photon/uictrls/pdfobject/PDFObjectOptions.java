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
package com.helger.photon.uictrls.pdfobject;

import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.Nullable;

import com.helger.annotation.concurrent.NotThreadSafe;
import com.helger.annotation.style.ReturnsMutableCopy;
import com.helger.html.jscode.IJSExpression;
import com.helger.html.jscode.JSAssocArray;
import com.helger.html.jscode.JSExpr;

/**
 * This class represents the options for embedding PDFObject. See
 * https://pdfobject.com/
 * 
 * @author Philip Helger
 */
@NotThreadSafe
public class PDFObjectOptions
{
  private IJSExpression m_aPage;
  private String m_sID;
  private String m_sWidth;
  private String m_sHeight;
  private IJSExpression m_aFallbackLink;

  public PDFObjectOptions ()
  {}

  /**
   * Alias for PDF Open Parameters "page" option. Any number entered here will
   * cause the PDF be opened to the specified page number (if the browser
   * supports it). If left unspecified, the PDF will open on page 1.
   *
   * @param nPage
   *        Page number
   * @return this for chaining
   */
  @NonNull
  public PDFObjectOptions setPage (final int nPage)
  {
    m_aPage = JSExpr.lit (nPage);
    return this;
  }

  /**
   * Alias for PDF Open Parameters "page" option. Any number entered here will
   * cause the PDF be opened to the specified page number (if the browser
   * supports it). If left unspecified, the PDF will open on page 1.
   *
   * @param sPage
   *        Page number
   * @return this for chaining
   */
  @NonNull
  public PDFObjectOptions setPage (@Nullable final String sPage)
  {
    m_aPage = sPage == null ? null : JSExpr.lit (sPage);
    return this;
  }

  /**
   * Any string entered here will be appended to the generated &lt;embed&gt;
   * element as the ID. If left unspecified, no ID will be appended.
   *
   * @param sID
   *        ID to use
   * @return this for chaining
   */
  @NonNull
  public PDFObjectOptions setID (@Nullable final String sID)
  {
    m_sID = sID;
    return this;
  }

  /**
   * Will insert the width as an inline style via the style attribute on the
   * &lt;embed&gt; element. If left unspecified, PDFObject will default to 100%.
   * Is standard CSS, supports all units, including px, %, em, and rem.
   *
   * @param sWidth
   *        Width to use
   * @return this for chaining
   */
  @NonNull
  public PDFObjectOptions setWidth (@Nullable final String sWidth)
  {
    m_sWidth = sWidth;
    return this;
  }

  /**
   * Will insert the height as an inline style via the style attribute on the
   * target element. If left unspecified, PDFObject will default to 100%. Is
   * standard CSS, supports all units, including px, %, em, and rem.
   *
   * @param sHeight
   *        Height to use
   * @return this for chaining
   */
  @NonNull
  public PDFObjectOptions setHeight (@Nullable final String sHeight)
  {
    m_sHeight = sHeight;
    return this;
  }

  /**
   * disable the fallback text option and prevent PDFObject from inserting
   * fallback text
   *
   * @return this for chaining
   */
  @NonNull
  public PDFObjectOptions setFallbackLinkDisabled ()
  {
    m_aFallbackLink = JSExpr.FALSE;
    return this;
  }

  /**
   * Any string entered here will be inserted into the target element when the
   * browser doesn't support inline PDFs. HTML is supported. Use the shortcode
   * <code>[url</code>] to insert the URL of the PDF (as specified via the URL
   * parameter in the embed() method).
   *
   * @param sText
   *        Text to use
   * @return this for chaining
   */
  @NonNull
  public PDFObjectOptions setFallbackLink (@Nullable final String sText)
  {
    m_aFallbackLink = sText == null ? null : JSExpr.lit (sText);
    return this;
  }

  @NonNull
  @ReturnsMutableCopy
  public JSAssocArray getAsJSObject ()
  {
    final JSAssocArray ret = new JSAssocArray ();
    if (m_aPage != null)
      ret.add ("page", m_aPage);
    if (m_sID != null)
      ret.add ("id", m_sID);
    if (m_sWidth != null)
      ret.add ("width", m_sWidth);
    if (m_sHeight != null)
      ret.add ("height", m_sHeight);
    if (m_aFallbackLink != null)
      ret.add ("fallbackLink", m_aFallbackLink);
    return ret;
  }
}
