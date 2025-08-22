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
package com.helger.photon.atom;

import java.util.Locale;

import com.helger.base.string.StringHelper;

import jakarta.annotation.Nullable;

/**
 * Abstract implementation of {@link IFeedElement} with a common language
 * handling.
 *
 * @author Philip Helger
 */
public abstract class AbstractFeedElement implements IFeedElement
{
  private String m_sContentLanguage;

  public AbstractFeedElement ()
  {}

  @Nullable
  public final String getLanguage ()
  {
    return m_sContentLanguage;
  }

  public final void setLanguage (@Nullable final Locale aContentLocale)
  {
    setLanguage (aContentLocale == null ? null : aContentLocale.getLanguage ());
  }

  public final void setLanguage (@Nullable final String sContentLanguage)
  {
    m_sContentLanguage = StringHelper.isEmpty (sContentLanguage) ? null : sContentLanguage;
  }
}
