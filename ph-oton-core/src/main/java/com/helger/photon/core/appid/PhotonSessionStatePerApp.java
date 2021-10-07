/*
 * Copyright (C) 2014-2021 Philip Helger (www.helger.com)
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

import com.helger.commons.concurrent.SimpleReadWriteLock;
import com.helger.commons.string.ToStringGenerator;
import com.helger.photon.core.menu.IMenuItemPage;
import com.helger.photon.core.menu.IMenuTree;

/**
 * Single session state per app
 *
 * @author Philip Helger
 * @since 8.0.0
 */
@ThreadSafe
public final class PhotonSessionStatePerApp implements Serializable
{
  private final SimpleReadWriteLock m_aRWLock = new SimpleReadWriteLock ();
  private IMenuTree m_aMenuTree;
  private IMenuItemPage m_aMenuItem;
  private Locale m_aDisplayLocale;

  public PhotonSessionStatePerApp ()
  {}

  @Nullable
  public IMenuTree getMenuTree ()
  {
    return m_aRWLock.readLockedGet ( () -> m_aMenuTree);
  }

  @Nonnull
  public PhotonSessionStatePerApp setMenuTree (@Nullable final IMenuTree aMenuTree)
  {
    m_aRWLock.writeLocked ( () -> m_aMenuTree = aMenuTree);
    return this;
  }

  @Nullable
  public IMenuItemPage getMenuItem ()
  {
    return m_aRWLock.readLockedGet ( () -> m_aMenuItem);
  }

  @Nonnull
  public PhotonSessionStatePerApp setMenuItem (@Nullable final IMenuItemPage aMenuItem)
  {
    m_aRWLock.writeLocked ( () -> m_aMenuItem = aMenuItem);
    return this;
  }

  @Nullable
  public Locale getDisplayLocale ()
  {
    return m_aRWLock.readLockedGet ( () -> m_aDisplayLocale);
  }

  @Nonnull
  public PhotonSessionStatePerApp setDisplayLocale (@Nullable final Locale aDisplayLocale)
  {
    m_aRWLock.writeLocked ( () -> m_aDisplayLocale = aDisplayLocale);
    return this;
  }

  public boolean isNotEmpty ()
  {
    return m_aRWLock.readLockedBoolean ( () -> m_aMenuTree != null || m_aMenuItem != null || m_aDisplayLocale != null);
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
