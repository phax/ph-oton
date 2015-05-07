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
package com.helger.photon.bootstrap3.dropdown;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.helger.commons.ValueEnforcer;
import com.helger.commons.url.ISimpleURL;
import com.helger.html.hc.IHCNode;
import com.helger.html.hc.html.AbstractHCA;
import com.helger.html.hc.html.HCA;
import com.helger.html.hc.html.HC_Target;
import com.helger.html.hc.htmlext.HCA_JS;
import com.helger.html.hc.impl.HCNodeList;
import com.helger.html.hc.impl.HCTextNode;
import com.helger.html.js.provider.IJSCodeProviderWithSettings;

/**
 * This class represents a single dropdown menu item.
 *
 * @author Philip Helger
 */
public class BootstrapDropdownMenuItem
{
  public static final boolean DEFAULT_ACTIVE = false;

  private ISimpleURL m_aURL;
  private IJSCodeProviderWithSettings m_aJSCode;
  private IHCNode m_aLabel;
  private HC_Target m_aTarget;
  private boolean m_bActive = DEFAULT_ACTIVE;

  public BootstrapDropdownMenuItem ()
  {}

  @Nonnull
  public BootstrapDropdownMenuItem setLinkAction (@Nonnull final ISimpleURL aURL)
  {
    ValueEnforcer.notNull (aURL, "URL");
    m_aURL = aURL;
    m_aJSCode = null;
    return this;
  }

  @Nonnull
  public BootstrapDropdownMenuItem setLinkAction (@Nonnull final IJSCodeProviderWithSettings aJSCode)
  {
    ValueEnforcer.notNull (aJSCode, "JSCode");
    m_aURL = null;
    m_aJSCode = aJSCode;
    return this;
  }

  @Nonnull
  public BootstrapDropdownMenuItem setLabel (@Nullable final String sText)
  {
    return setLabel (HCTextNode.createOnDemand (sText));
  }

  @Nonnull
  public BootstrapDropdownMenuItem setLabel (@Nullable final IHCNode aLabel)
  {
    m_aLabel = aLabel;
    return this;
  }

  @Nonnull
  public BootstrapDropdownMenuItem setLabel (@Nullable final IHCNode... aLabels)
  {
    return setLabel (new HCNodeList ().addChildren (aLabels));
  }

  @Nonnull
  public BootstrapDropdownMenuItem setLabel (@Nullable final Iterable <? extends IHCNode> aLabels)
  {
    return setLabel (new HCNodeList ().addChildren (aLabels));
  }

  @Nullable
  public IHCNode getLabel ()
  {
    return m_aLabel;
  }

  @Nonnull
  public BootstrapDropdownMenuItem setTarget (@Nullable final HC_Target aTarget)
  {
    m_aTarget = aTarget;
    return this;
  }

  @Nullable
  public HC_Target getTarget ()
  {
    return m_aTarget;
  }

  @Nonnull
  public BootstrapDropdownMenuItem setActive (final boolean bActive)
  {
    m_bActive = bActive;
    return this;
  }

  public boolean isActive ()
  {
    return m_bActive;
  }

  @Nonnull
  public AbstractHCA <?> createLink ()
  {
    if (m_aURL == null && m_aJSCode == null)
      throw new IllegalStateException ("No LinkAction specified!");

    AbstractHCA <?> ret;
    if (m_aURL != null)
      ret = new HCA (m_aURL);
    else
      ret = new HCA_JS (m_aJSCode);
    ret.setTarget (m_aTarget);
    return ret.addChild (m_aLabel);
  }
}
