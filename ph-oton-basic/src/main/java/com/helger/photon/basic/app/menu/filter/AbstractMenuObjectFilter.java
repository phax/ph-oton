/**
 * Copyright (C) 2014-2018 Philip Helger (www.helger.com)
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
package com.helger.photon.basic.app.menu.filter;

import java.util.Locale;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.concurrent.NotThreadSafe;

import com.helger.commons.locale.LocaleHelper;
import com.helger.commons.string.ToStringGenerator;
import com.helger.commons.text.IMultilingualText;
import com.helger.commons.text.ReadOnlyMultilingualText;
import com.helger.photon.basic.app.menu.IMenuObjectFilter;

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

  @Nonnull
  public AbstractMenuObjectFilter setDescription (@Nullable final String sDescription)
  {
    return setDescription (new ReadOnlyMultilingualText (LocaleHelper.LOCALE_INDEPENDENT, sDescription));
  }

  @Nonnull
  public AbstractMenuObjectFilter setDescription (@Nullable final IMultilingualText aDescription)
  {
    m_aDescription = aDescription;
    return this;
  }

  @Nullable
  public String getDisplayText (@Nonnull final Locale aContentLocale)
  {
    return m_aDescription == null ? null : m_aDescription.getText (aContentLocale);
  }

  @Override
  public String toString ()
  {
    return new ToStringGenerator (this).appendIfNotNull ("description", m_aDescription).getToString ();
  }
}
