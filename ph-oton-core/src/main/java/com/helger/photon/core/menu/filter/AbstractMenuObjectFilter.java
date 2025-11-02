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
package com.helger.photon.core.menu.filter;

import java.util.Locale;

import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.Nullable;

import com.helger.annotation.concurrent.NotThreadSafe;
import com.helger.base.tostring.ToStringGenerator;
import com.helger.photon.core.menu.IMenuObjectFilter;
import com.helger.text.IMultilingualText;
import com.helger.text.ReadOnlyMultilingualText;
import com.helger.text.locale.LocaleHelper;

/**
 * Abstract base class for a menu object filter.
 *
 * @author Philip Helger
 */
@NotThreadSafe
public abstract class AbstractMenuObjectFilter implements IMenuObjectFilter
{
  private IMultilingualText m_aDescription;

  public AbstractMenuObjectFilter ()
  {}

  @Nullable
  public IMultilingualText getDescription ()
  {
    return m_aDescription;
  }

  @NonNull
  public AbstractMenuObjectFilter setDescription (@Nullable final String sDescription)
  {
    return setDescription (sDescription == null ? null : new ReadOnlyMultilingualText (LocaleHelper.LOCALE_INDEPENDENT, sDescription));
  }

  @NonNull
  public AbstractMenuObjectFilter setDescription (@Nullable final IMultilingualText aDescription)
  {
    m_aDescription = aDescription;
    return this;
  }

  @Nullable
  @Override
  public String getDisplayText (@NonNull final Locale aContentLocale)
  {
    return m_aDescription == null ? null : m_aDescription.getText (aContentLocale);
  }

  @Override
  public String toString ()
  {
    return new ToStringGenerator (this).appendIfNotNull ("description", m_aDescription).getToString ();
  }
}
