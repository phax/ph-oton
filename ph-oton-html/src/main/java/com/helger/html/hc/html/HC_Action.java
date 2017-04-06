/**
 * Copyright (C) 2014-2017 Philip Helger (www.helger.com)
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

import java.nio.charset.Charset;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.concurrent.NotThreadSafe;

import com.helger.commons.ValueEnforcer;
import com.helger.commons.annotation.ReturnsMutableCopy;
import com.helger.commons.lang.ICloneable;
import com.helger.commons.string.ToStringGenerator;
import com.helger.commons.url.ISimpleURL;
import com.helger.html.js.CJS;
import com.helger.html.js.IHasJSCodeWithSettings;
import com.helger.html.js.IJSWriterSettings;
import com.helger.xml.microdom.IMicroElement;
import com.helger.xml.microdom.IMicroQName;

/**
 * Represents the action to be used with button and form.
 *
 * @author Philip Helger
 */
@NotThreadSafe
public class HC_Action implements ICloneable <HC_Action>
{
  private ISimpleURL m_aActionURL;
  private IHasJSCodeWithSettings m_aActionJS;

  public HC_Action ()
  {}

  public HC_Action (@Nonnull final HC_Action aOther)
  {
    ValueEnforcer.notNull (aOther, "Other");
    m_aActionURL = aOther.m_aActionURL;
    m_aActionJS = aOther.m_aActionJS;
  }

  @Nullable
  public ISimpleURL getActionURL ()
  {
    return m_aActionURL;
  }

  @Nullable
  public IHasJSCodeWithSettings getActionJS ()
  {
    return m_aActionJS;
  }

  public void setAction (@Nullable final ISimpleURL aAction)
  {
    m_aActionURL = aAction;
    m_aActionJS = null;
  }

  public void setAction (@Nullable final IHasJSCodeWithSettings aAction)
  {
    m_aActionURL = null;
    m_aActionJS = aAction;
  }

  public void applyProperties (@Nonnull final IMicroQName aAttributeName,
                               @Nonnull final IMicroElement aElement,
                               @Nonnull final IJSWriterSettings aSettings,
                               @Nonnull final Charset aCharset)
  {
    if (m_aActionJS != null)
    {
      final String sJSCode = m_aActionJS.getJSCode (aSettings);
      aElement.setAttribute (aAttributeName, CJS.JS_PREFIX + sJSCode);
    }
    else
      if (m_aActionURL != null)
        aElement.setAttribute (aAttributeName, m_aActionURL.getAsStringWithEncodedParameters (aCharset));
  }

  @Nonnull
  @ReturnsMutableCopy
  public HC_Action getClone ()
  {
    return new HC_Action (this);
  }

  @Override
  public String toString ()
  {
    return new ToStringGenerator (null).appendIfNotNull ("ActionURL", m_aActionURL)
                                       .appendIfNotNull ("ActionJS", m_aActionJS)
                                       .getToString ();
  }
}
