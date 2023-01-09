/*
 * Copyright (C) 2014-2023 Philip Helger (www.helger.com)
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
package com.helger.photon.core.appid;

import java.io.Serializable;
import java.util.Locale;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.concurrent.ThreadSafe;

import com.helger.commons.ValueEnforcer;
import com.helger.commons.string.ToStringGenerator;
import com.helger.photon.core.locale.GlobalLocaleManager;
import com.helger.photon.core.menu.IMenuItemPage;
import com.helger.photon.core.menu.IMenuTree;

/**
 * Request state
 *
 * @author Philip Helger
 * @since 8.0.0
 */
@ThreadSafe
public final class PhotonRequestState implements Serializable
{
  private final IMenuTree m_aMenuTree;
  private final IMenuItemPage m_aMenuItem;
  private final Locale m_aDisplayLocale;

  public PhotonRequestState (@Nonnull final PhotonSessionStatePerApp aState)
  {
    this (aState.getMenuTree (), aState.getMenuItem (), aState.getDisplayLocale ());
  }

  public PhotonRequestState (@Nonnull final PhotonGlobalStatePerApp aState)
  {
    this (aState.getMenuTree (),
          aState.getMenuTree () == null ? null : aState.getMenuTree ().getDefaultMenuItem (),
          GlobalLocaleManager.getInstance ().getDefaultLocale ());
  }

  public PhotonRequestState (@Nullable final IMenuTree aMenuTree,
                             @Nullable final IMenuItemPage aMenuItem,
                             @Nonnull final Locale aDisplayLocale)
  {
    m_aMenuTree = aMenuTree;
    m_aMenuItem = aMenuItem;
    m_aDisplayLocale = ValueEnforcer.notNull (aDisplayLocale, "DisplayLocale");
  }

  @Nullable
  public IMenuTree getMenuTree ()
  {
    return m_aMenuTree;
  }

  @Nullable
  public IMenuItemPage getMenuItem ()
  {
    return m_aMenuItem;
  }

  @Nullable
  public Locale getDisplayLocale ()
  {
    return m_aDisplayLocale;
  }

  @Override
  public String toString ()
  {
    return new ToStringGenerator (null).append ("MenuTree", m_aMenuTree)
                                       .append ("MenuItem", m_aMenuItem)
                                       .append ("DisplayLocale", m_aDisplayLocale)
                                       .getToString ();
  }
}
