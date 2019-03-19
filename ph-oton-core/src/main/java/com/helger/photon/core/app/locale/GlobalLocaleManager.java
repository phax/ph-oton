/**
 * Copyright (C) 2014-2019 Philip Helger (www.helger.com)
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
package com.helger.photon.core.app.locale;

import java.util.Locale;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.concurrent.NotThreadSafe;

import com.helger.commons.annotation.ReturnsMutableCopy;
import com.helger.commons.annotation.UsedViaReflection;
import com.helger.commons.collection.impl.ICommonsList;
import com.helger.commons.state.EChange;
import com.helger.commons.string.ToStringGenerator;
import com.helger.web.scope.singleton.AbstractGlobalWebSingleton;

/**
 * This class manages the available locales.
 *
 * @author Philip Helger
 */
@NotThreadSafe
public final class GlobalLocaleManager extends AbstractGlobalWebSingleton implements ILocaleManager
{
  private final LocaleManager m_aInstance = new LocaleManager ();

  @Deprecated
  @UsedViaReflection
  public GlobalLocaleManager ()
  {}

  @Nonnull
  public static GlobalLocaleManager getInstance ()
  {
    return getGlobalSingleton (GlobalLocaleManager.class);
  }

  @Nonnull
  public EChange registerLocale (@Nonnull final Locale aLocale)
  {
    return m_aInstance.registerLocale (aLocale);
  }

  @Nonnull
  public EChange setDefaultLocale (@Nonnull final Locale aDefaultLocale)
  {
    return m_aInstance.setDefaultLocale (aDefaultLocale);
  }

  @Nullable
  public Locale getDefaultLocale ()
  {
    return m_aInstance.getDefaultLocale ();
  }

  @Nonnull
  @ReturnsMutableCopy
  public ICommonsList <Locale> getAllAvailableLocales ()
  {
    return m_aInstance.getAllAvailableLocales ();
  }

  public boolean hasLocales ()
  {
    return m_aInstance.hasLocales ();
  }

  public boolean isSupportedLocale (@Nullable final Locale aLocale)
  {
    return m_aInstance.isSupportedLocale (aLocale);
  }

  @Override
  public String toString ()
  {
    return ToStringGenerator.getDerived (super.toString ()).append ("Instance", m_aInstance).getToString ();
  }
}
