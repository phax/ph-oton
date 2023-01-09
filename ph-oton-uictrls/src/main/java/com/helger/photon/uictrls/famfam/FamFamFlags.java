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
package com.helger.photon.uictrls.famfam;

import java.util.Locale;

import javax.annotation.Nullable;
import javax.annotation.concurrent.Immutable;

import com.helger.commons.string.StringHelper;
import com.helger.html.css.ICSSClassProvider;
import com.helger.html.hc.IHCNode;

/**
 * A wrapper around the FamFam flags icon set
 *
 * @author Philip Helger
 */
@Immutable
public final class FamFamFlags
{
  public static final ICSSClassProvider CSS_CLASS_ICON_FAMFAM_FLAG = EFamFamFlagIcon.CSS_CLASS_ICON_FAMFAM_FLAG;

  private FamFamFlags ()
  {}

  /**
   * Get the flag from the passed locale
   *
   * @param aFlagLocale
   *        The locale to resolve. May be <code>null</code>.
   * @return <code>null</code> if the passed locale is <code>null</code>, if the
   *         locale has no country or if the no flag is present for the passed
   *         locale.
   */
  @Nullable
  public static EFamFamFlagIcon getFlagFromLocale (@Nullable final Locale aFlagLocale)
  {
    if (aFlagLocale != null)
    {
      final String sCountry = aFlagLocale.getCountry ();
      if (StringHelper.hasText (sCountry))
        return EFamFamFlagIcon.getFromIDOrNull (sCountry);
    }
    return null;
  }

  /**
   * Get the flag icon from the passed locale or <code>null</code>.
   *
   * @param aFlagLocale
   *        The locale to resolve. May be <code>null</code>.
   * @return <code>null</code> if the passed locale is <code>null</code>, if the
   *         locale has no country or if the no flag is present for the passed
   *         locale.
   */
  @Nullable
  public static IHCNode getFlagNodeFromLocale (@Nullable final Locale aFlagLocale)
  {
    final EFamFamFlagIcon eIcon = getFlagFromLocale (aFlagLocale);
    return eIcon == null ? null : eIcon.getAsNode ();
  }
}
