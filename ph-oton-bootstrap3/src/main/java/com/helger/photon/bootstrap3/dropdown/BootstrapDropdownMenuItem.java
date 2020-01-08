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
package com.helger.photon.bootstrap3.dropdown;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.helger.commons.ValueEnforcer;
import com.helger.commons.url.ISimpleURL;
import com.helger.html.hc.IHCNode;
import com.helger.html.hc.ext.HCA_JS;
import com.helger.html.hc.html.HC_Target;
import com.helger.html.hc.html.textlevel.HCA;
import com.helger.html.hc.html.textlevel.IHCA;
import com.helger.html.hc.impl.HCNodeList;
import com.helger.html.hc.impl.HCTextNode;
import com.helger.html.js.IHasJSCodeWithSettings;
import com.helger.photon.bootstrap3.AbstractBootstrapObject;

/**
 * This class represents a single dropdown menu item.
 *
 * @author Philip Helger
 */
public class BootstrapDropdownMenuItem extends AbstractBootstrapObject <BootstrapDropdownMenuItem>
{
  public static final boolean DEFAULT_ACTIVE = false;
  public static final boolean DEFAULT_DISABLED = false;

  private ISimpleURL m_aURL;
  private IHasJSCodeWithSettings m_aJSCode;
  private IHCNode m_aLabel;
  private HC_Target m_aTarget;
  private boolean m_bActive = DEFAULT_ACTIVE;
  private boolean m_bDisabled = DEFAULT_DISABLED;

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
  public BootstrapDropdownMenuItem setLinkAction (@Nonnull final IHasJSCodeWithSettings aJSCode)
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
  public BootstrapDropdownMenuItem setDisabled (final boolean bDisabled)
  {
    m_bDisabled = bDisabled;
    return this;
  }

  public boolean isDisabled ()
  {
    return m_bDisabled;
  }

  @Nonnull
  public IHCA <?> createLink ()
  {
    if (m_aURL == null && m_aJSCode == null)
      throw new IllegalStateException ("No LinkAction specified!");

    IHCA <?> ret;
    if (m_aURL != null)
      ret = new HCA (m_aURL);
    else
      ret = new HCA_JS (m_aJSCode);
    ret.setTarget (m_aTarget);
    ret.addChild (m_aLabel);

    // Set ID, class and style
    applyBasicHTMLTo (ret);

    return ret;
  }
}
