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
package com.helger.html.hc.html.script;

import com.helger.base.tostring.ToStringGenerator;
import com.helger.html.CHTMLAttributeValues;
import com.helger.html.CHTMLAttributes;
import com.helger.html.annotation.OutOfBandNode;
import com.helger.html.hc.IHCConversionSettingsToNode;
import com.helger.html.resource.js.IJSPathProvider;
import com.helger.url.ISimpleURL;
import com.helger.xml.microdom.IMicroElement;

import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;

/**
 * Represents an HTML &lt;script&gt; element that loads the code from a source
 * URL.
 *
 * @author Philip Helger
 * @see HCScriptInline
 * @see HCScriptInlineOnDocumentReady
 */
@OutOfBandNode
public class HCScriptFile extends AbstractHCScript <HCScriptFile>
{
  /** By default external scripts are not deferred */
  public static final boolean DEFAULT_DEFER = false;
  /** By default external scripts are not loaded asynchronously */
  public static final boolean DEFAULT_ASYNC = false;

  private ISimpleURL m_aSrc;
  // This member if for post processing purposes only
  private IJSPathProvider m_aJSPathProvider;
  private boolean m_bDefer = DEFAULT_DEFER;
  private boolean m_bAsync = DEFAULT_ASYNC;

  public HCScriptFile ()
  {}

  @Nullable
  public final ISimpleURL getSrc ()
  {
    return m_aSrc;
  }

  @Nonnull
  public final HCScriptFile setSrc (@Nullable final ISimpleURL aSrc)
  {
    m_aSrc = aSrc;
    return this;
  }

  @Nullable
  public final IJSPathProvider getPathProvider ()
  {
    return m_aJSPathProvider;
  }

  @Nonnull
  public final HCScriptFile setPathProvider (@Nullable final IJSPathProvider aJSPathProvider)
  {
    m_aJSPathProvider = aJSPathProvider;
    return thisAsT ();
  }

  public final boolean isDefer ()
  {
    return m_bDefer;
  }

  @Nonnull
  public final HCScriptFile setDefer (final boolean bDefer)
  {
    m_bDefer = bDefer;
    return this;
  }

  public final boolean isAsync ()
  {
    return m_bAsync;
  }

  @Nonnull
  public final HCScriptFile setAsync (final boolean bAsync)
  {
    m_bAsync = bAsync;
    return this;
  }

  @Override
  protected void fillMicroElement (final IMicroElement aElement, final IHCConversionSettingsToNode aConversionSettings)
  {
    super.fillMicroElement (aElement, aConversionSettings);
    if (m_aSrc != null)
      aElement.setAttribute (CHTMLAttributes.SRC,
                             m_aSrc.getAsStringWithEncodedParameters (aConversionSettings.getCharset ()));
    if (m_bDefer)
      aElement.setAttribute (CHTMLAttributes.DEFER, CHTMLAttributeValues.DEFER);
    if (m_bAsync)
      aElement.setAttribute (CHTMLAttributes.ASYNC, CHTMLAttributeValues.ASYNC);

    // Tag may not be self closed
    aElement.addText ("");
  }

  @Override
  public String toString ()
  {
    return ToStringGenerator.getDerived (super.toString ())
                            .appendIfNotNull ("Src", m_aSrc)
                            .appendIfNotNull ("JSPathProvider", m_aJSPathProvider)
                            .append ("Defer", m_bDefer)
                            .append ("Async", m_bAsync)
                            .getToString ();
  }
}
