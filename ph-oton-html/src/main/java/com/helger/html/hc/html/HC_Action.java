/*
 * Copyright (C) 2014-2026 Philip Helger (www.helger.com)
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

import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.Nullable;

import com.helger.annotation.concurrent.NotThreadSafe;
import com.helger.annotation.style.ReturnsMutableCopy;
import com.helger.base.clone.ICloneable;
import com.helger.base.enforce.ValueEnforcer;
import com.helger.base.tostring.ToStringGenerator;
import com.helger.html.js.CJS;
import com.helger.html.js.IHasJSCodeWithSettings;
import com.helger.html.js.IJSWriterSettings;
import com.helger.url.ISimpleURL;
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

  public HC_Action (@NonNull final HC_Action aOther)
  {
    ValueEnforcer.notNull (aOther, "Other");
    m_aActionURL = aOther.m_aActionURL;
    m_aActionJS = aOther.m_aActionJS;
  }

  @Nullable
  public final ISimpleURL getActionURL ()
  {
    return m_aActionURL;
  }

  @Nullable
  public final IHasJSCodeWithSettings getActionJS ()
  {
    return m_aActionJS;
  }

  public final void setAction (@Nullable final ISimpleURL aAction)
  {
    m_aActionURL = aAction;
    m_aActionJS = null;
  }

  public final void setAction (@Nullable final IHasJSCodeWithSettings aAction)
  {
    m_aActionURL = null;
    m_aActionJS = aAction;
  }

  public void applyProperties (@NonNull final IMicroQName aAttributeName,
                               @NonNull final IMicroElement aElement,
                               @NonNull final IJSWriterSettings aSettings,
                               @NonNull final Charset aCharset)
  {
    if (m_aActionJS != null)
    {
      final String sJSCode = m_aActionJS.getJSCode (aSettings);
      aElement.setAttribute (aAttributeName, CJS.JS_PREFIX + sJSCode);
    }
    else
      if (m_aActionURL != null)
        aElement.setAttribute (aAttributeName, m_aActionURL.getWithCharset (aCharset).getAsString ());
  }

  @NonNull
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
