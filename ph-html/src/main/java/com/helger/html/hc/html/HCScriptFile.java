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
package com.helger.html.hc.html;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.helger.commons.microdom.IMicroElement;
import com.helger.commons.string.ToStringGenerator;
import com.helger.commons.url.ISimpleURL;
import com.helger.commons.url.SimpleURL;
import com.helger.html.CHTMLAttributeValues;
import com.helger.html.CHTMLAttributes;
import com.helger.html.annotations.OutOfBandNode;
import com.helger.html.hc.conversion.IHCConversionSettingsToNode;

/**
 * Represents an HTML &lt;script&gt; element that loads the code from a source
 * URL.
 *
 * @author Philip Helger
 * @see HCScript
 * @see HCScriptOnDocumentReady
 */
@OutOfBandNode
public class HCScriptFile extends AbstractHCScript <HCScriptFile>
{
  /** By default external scripts are not deferred */
  public static final boolean DEFAULT_DEFER = false;
  /** By default external scripts are not loaded asynchronously */
  public static final boolean DEFAULT_ASYNC = false;

  private ISimpleURL m_aSrc;
  private boolean m_bDefer = DEFAULT_DEFER;
  private boolean m_bAsync = DEFAULT_ASYNC;

  public HCScriptFile ()
  {}

  public boolean isInlineJS ()
  {
    return false;
  }

  @Nullable
  public ISimpleURL getSrc ()
  {
    return m_aSrc;
  }

  @Nullable
  public String getSrcString ()
  {
    return m_aSrc == null ? null : m_aSrc.getAsString ();
  }

  @Nonnull
  public HCScriptFile setSrc (@Nullable final String sSrc)
  {
    return setSrc (sSrc == null ? null : new SimpleURL (sSrc));
  }

  @Nonnull
  public HCScriptFile setSrc (@Nullable final ISimpleURL aSrc)
  {
    m_aSrc = aSrc;
    return this;
  }

  public boolean isDefer ()
  {
    return m_bDefer;
  }

  @Nonnull
  public HCScriptFile setDefer (final boolean bDefer)
  {
    m_bDefer = bDefer;
    return this;
  }

  public boolean isAsync ()
  {
    return m_bAsync;
  }

  @Nonnull
  public HCScriptFile setAsync (final boolean bAsync)
  {
    m_bAsync = bAsync;
    return this;
  }

  @Override
  protected void applyProperties (final IMicroElement aElement, final IHCConversionSettingsToNode aConversionSettings)
  {
    super.applyProperties (aElement, aConversionSettings);
    if (m_aSrc != null)
      aElement.setAttribute (CHTMLAttributes.SRC, m_aSrc.getAsString ());
    if (m_bDefer)
      aElement.setAttribute (CHTMLAttributes.DEFER, CHTMLAttributeValues.DEFER);
    if (m_bAsync)
      aElement.setAttribute (CHTMLAttributes.ASYNC, CHTMLAttributeValues.ASYNC);

    // Tag may not be self closed
    aElement.appendText ("");
  }

  @Override
  public String toString ()
  {
    return ToStringGenerator.getDerived (super.toString ())
                            .appendIfNotNull ("src", m_aSrc)
                            .append ("defer", m_bDefer)
                            .append ("async", m_bAsync)
                            .toString ();
  }

  @Nonnull
  public static HCScriptFile create (@Nullable final ISimpleURL aSrc)
  {
    return new HCScriptFile ().setSrc (aSrc);
  }

  @Nonnull
  public static HCScriptFile create (@Nonnull final String sSrc)
  {
    return create (new SimpleURL (sSrc));
  }
}
