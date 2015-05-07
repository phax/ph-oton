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
import javax.annotation.concurrent.NotThreadSafe;

import com.helger.commons.ICloneable;
import com.helger.commons.ValueEnforcer;
import com.helger.commons.annotations.ReturnsMutableCopy;
import com.helger.commons.microdom.IMicroElement;
import com.helger.commons.string.StringHelper;
import com.helger.commons.string.ToStringGenerator;
import com.helger.commons.url.ISimpleURL;
import com.helger.html.js.CJS;
import com.helger.html.js.IJSCodeProvider;
import com.helger.html.js.builder.IJSStatement;
import com.helger.html.js.writer.IJSWriterSettings;

/**
 * Represents the action to be used with button and form.
 *
 * @author Philip Helger
 */
@NotThreadSafe
public final class HC_Action implements ICloneable <HC_Action>
{
  private String m_sAction;
  private IJSStatement m_aAction;

  public HC_Action ()
  {}

  public HC_Action (@Nonnull final HC_Action aOther)
  {
    ValueEnforcer.notNull (aOther, "Other");
    m_sAction = aOther.m_sAction;
    m_aAction = aOther.m_aAction;
  }

  @Nullable
  public String getActionURL ()
  {
    return m_sAction;
  }

  @Nullable
  public IJSCodeProvider getActionJS ()
  {
    return m_aAction;
  }

  public void setAction (@Nullable final ISimpleURL aAction)
  {
    setAction (aAction == null ? null : aAction.getAsString ());
  }

  public void setAction (@Nullable final String sAction)
  {
    m_sAction = sAction;
    m_aAction = null;
  }

  public void setAction (@Nullable final IJSStatement aAction)
  {
    m_sAction = null;
    m_aAction = aAction;
  }

  public void applyProperties (@Nonnull final String sAttributeName,
                               @Nonnull final IMicroElement aElement,
                               @Nonnull final IJSWriterSettings aSettings)
  {
    if (m_aAction != null)
    {
      final String sJSCode = m_aAction.getJSCode (aSettings);
      aElement.setAttribute (sAttributeName, CJS.JS_PREFIX + sJSCode);
    }
    else
      if (StringHelper.hasText (m_sAction))
        aElement.setAttribute (sAttributeName, m_sAction);
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
    return new ToStringGenerator (null).appendIfNotNull ("actionURL", m_sAction)
                                       .appendIfNotNull ("actionJS", m_aAction)
                                       .toString ();
  }
}
