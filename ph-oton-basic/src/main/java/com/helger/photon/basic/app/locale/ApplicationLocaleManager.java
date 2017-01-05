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
package com.helger.photon.basic.app.locale;

import javax.annotation.Nonnull;
import javax.annotation.concurrent.NotThreadSafe;

import com.helger.commons.annotation.Nonempty;
import com.helger.commons.annotation.UsedViaReflection;
import com.helger.commons.scope.mgr.ScopeManager;
import com.helger.commons.scope.singleton.AbstractApplicationSingleton;
import com.helger.commons.string.ToStringGenerator;

/**
 * This class manages the locales available in the application.
 *
 * @author Philip Helger
 */
@NotThreadSafe
public final class ApplicationLocaleManager extends AbstractApplicationSingleton
{
  private final LocaleManager m_aProxy = new LocaleManager ();

  @Deprecated
  @UsedViaReflection
  public ApplicationLocaleManager ()
  {}

  @Nonnull
  public static ApplicationLocaleManager getInstance ()
  {
    return getApplicationSingleton (ApplicationLocaleManager.class);
  }

  @Nonnull
  public static ApplicationLocaleManager getInstanceOfScope (@Nonnull @Nonempty final String sApplicationID)
  {
    return getSingleton (ScopeManager.getApplicationScope (sApplicationID), ApplicationLocaleManager.class);
  }

  @Nonnull
  public static ILocaleManager getLocaleMgr ()
  {
    return getInstance ().m_aProxy;
  }

  @Nonnull
  public ILocaleManager getInstanceLocaleMgr ()
  {
    return m_aProxy;
  }

  @Override
  public String toString ()
  {
    return ToStringGenerator.getDerived (super.toString ()).append ("proxy", m_aProxy).toString ();
  }
}
