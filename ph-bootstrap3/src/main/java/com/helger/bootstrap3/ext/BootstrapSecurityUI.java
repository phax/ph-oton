/**
 * Copyright (C) 2014-2015 Philip Helger (www.helger.com)
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
package com.helger.bootstrap3.ext;

import java.util.List;
import java.util.Locale;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.concurrent.Immutable;

import com.helger.appbasics.security.password.GlobalPasswordSettings;
import com.helger.bootstrap3.tooltip.BootstrapTooltip;
import com.helger.html.hc.IHCNode;
import com.helger.html.hc.htmlext.HCUtils;

/**
 * Common methods to render security UI with Bootstrap
 *
 * @author Philip Helger
 */
@Immutable
public final class BootstrapSecurityUI
{
  private BootstrapSecurityUI ()
  {}

  /**
   * Create a tooltip with all the requirements for a password
   *
   * @param aDisplayLocale
   *        Display locale to use.
   * @return <code>null</code> if not special constraints are defined.
   */
  @Nullable
  public static IHCNode createPasswordConstraintTip (@Nonnull final Locale aDisplayLocale)
  {
    final List <String> aTexts = GlobalPasswordSettings.getPasswordConstraintList ()
                                                       .getAllPasswordConstraintDescriptions (aDisplayLocale);
    if (aTexts.isEmpty ())
      return null;

    return BootstrapTooltip.createSimpleTooltip (HCUtils.list2divList (aTexts));
  }
}
