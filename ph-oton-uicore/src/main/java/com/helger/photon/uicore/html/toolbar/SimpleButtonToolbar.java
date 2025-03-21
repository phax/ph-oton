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
package com.helger.photon.uicore.html.toolbar;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.helger.commons.ValueEnforcer;
import com.helger.commons.url.ISimpleURL;
import com.helger.commons.url.SimpleURL;
import com.helger.html.hc.html.forms.HCButton;
import com.helger.html.hc.html.forms.HCButton_Submit;
import com.helger.html.hc.html.grouping.AbstractHCDiv;
import com.helger.html.js.IHasJSCode;
import com.helger.photon.core.execcontext.ILayoutExecutionContext;
import com.helger.photon.uicore.icon.IIcon;

/**
 * Simple button toolbar
 *
 * @author Philip Helger
 */
public class SimpleButtonToolbar extends AbstractHCDiv <SimpleButtonToolbar> implements IButtonToolbar <SimpleButtonToolbar>
{
  private final SimpleURL m_aSelfHref;

  public SimpleButtonToolbar (@Nonnull final ILayoutExecutionContext aLEC)
  {
    this (aLEC.getSelfHref ());
  }

  public SimpleButtonToolbar (@Nonnull final SimpleURL aSelfHref)
  {
    m_aSelfHref = ValueEnforcer.notNull (aSelfHref, "SelfHref");
  }

  @Override
  @Nonnull
  public ISimpleURL getSelfHref ()
  {
    return m_aSelfHref;
  }

  @Nonnull
  public final HCButton addAndReturnButton (@Nullable final String sCaption,
                                            @Nullable final IHasJSCode aOnClick,
                                            @Nullable final IIcon aIcon)
  {
    return addAndReturnChild (new HCButton ().addChild (aIcon == null ? null : aIcon.getAsNode ())
                                             .addChild (sCaption)
                                             .setOnClick (aOnClick));
  }

  @Nonnull
  public final HCButton_Submit addAndReturnSubmitButton (@Nullable final String sCaption,
                                                         @Nullable final IHasJSCode aOnClick,
                                                         @Nullable final IIcon aIcon)
  {
    return addAndReturnChild (new HCButton_Submit ().addChild (aIcon == null ? null : aIcon.getAsNode ())
                                                    .addChild (sCaption)
                                                    .setOnClick (aOnClick));
  }
}
