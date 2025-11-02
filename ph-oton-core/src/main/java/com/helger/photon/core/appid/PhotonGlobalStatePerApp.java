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
package com.helger.photon.core.appid;

import java.io.Serializable;

import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.Nullable;

import com.helger.annotation.Nonempty;
import com.helger.annotation.concurrent.GuardedBy;
import com.helger.annotation.concurrent.ThreadSafe;
import com.helger.base.concurrent.SimpleReadWriteLock;
import com.helger.base.enforce.ValueEnforcer;
import com.helger.base.string.StringHelper;
import com.helger.base.tostring.ToStringGenerator;
import com.helger.photon.core.menu.IMenuTree;

/**
 * Single global state per app
 *
 * @author Philip Helger
 * @since 8.0.0
 */
@ThreadSafe
public final class PhotonGlobalStatePerApp implements Serializable
{
  private final SimpleReadWriteLock m_aRWLock = new SimpleReadWriteLock ();
  @GuardedBy ("m_aRWLock")
  private String m_sServletPath;
  @GuardedBy ("m_aRWLock")
  private IMenuTree m_aMenuTree;

  public PhotonGlobalStatePerApp ()
  {}

  @Nullable
  String internalGetServletPath ()
  {
    return m_aRWLock.readLockedGet ( () -> m_sServletPath);
  }

  @NonNull
  @Nonempty
  public String getServletPath () throws IllegalStateException
  {
    final String ret = internalGetServletPath ();
    if (StringHelper.isEmpty (ret))
      throw new IllegalStateException ("No servlet path specified!");
    return ret;
  }

  @NonNull
  public PhotonGlobalStatePerApp setServletPath (@NonNull @Nonempty final String sServletPath)
  {
    ValueEnforcer.notEmpty (sServletPath, "ApplicationServletPath");
    ValueEnforcer.isTrue (StringHelper.startsWith (sServletPath, '/'),
                          "ApplicationServletPath must start with a slash");
    ValueEnforcer.isFalse (StringHelper.endsWith (sServletPath, '/'), "ApplicationServletPath must end with a slash");
    m_aRWLock.writeLocked ( () -> m_sServletPath = sServletPath);
    return this;
  }

  @NonNull
  public PhotonGlobalStatePerApp removeServletPath ()
  {
    m_aRWLock.writeLocked ( () -> m_sServletPath = null);
    return this;
  }

  @Nullable
  public IMenuTree getMenuTree ()
  {
    return m_aRWLock.readLockedGet ( () -> m_aMenuTree);
  }

  @NonNull
  public PhotonGlobalStatePerApp setMenuTree (@Nullable final IMenuTree aMenuTree)
  {
    m_aRWLock.writeLocked ( () -> m_aMenuTree = aMenuTree);
    return this;
  }

  @Override
  public String toString ()
  {
    return new ToStringGenerator (null).append ("ServletPath", m_sServletPath)
                                       .append ("MenuTree", m_aMenuTree)
                                       .getToString ();
  }
}
