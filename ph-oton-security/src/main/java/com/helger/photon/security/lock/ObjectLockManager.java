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
package com.helger.photon.security.lock;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.concurrent.ThreadSafe;

import com.helger.commons.annotation.UsedViaReflection;
import com.helger.commons.string.ToStringGenerator;
import com.helger.photon.security.login.LoggedInUserManager;
import com.helger.scope.singleton.AbstractGlobalSingleton;

/**
 * Singleton of {@link ILockManager}.
 *
 * @author Philip Helger
 */
@ThreadSafe
public final class ObjectLockManager extends AbstractGlobalSingleton
{
  private final DefaultLockManager <String> m_aMgr = new DefaultLockManager <> (LoggedInUserManager.getInstance ());

  @Deprecated
  @UsedViaReflection
  public ObjectLockManager ()
  {}

  @Nonnull
  public static ObjectLockManager getInstance ()
  {
    return getGlobalSingleton (ObjectLockManager.class);
  }

  @Nullable
  public static ObjectLockManager getInstanceIfInstantiated ()
  {
    return getGlobalSingletonIfInstantiated (ObjectLockManager.class);
  }

  @Nonnull
  public DefaultLockManager <String> getDefaultLockMgr ()
  {
    return m_aMgr;
  }

  @Override
  public String toString ()
  {
    return ToStringGenerator.getDerived (super.toString ()).append ("mgr", m_aMgr).getToString ();
  }
}
