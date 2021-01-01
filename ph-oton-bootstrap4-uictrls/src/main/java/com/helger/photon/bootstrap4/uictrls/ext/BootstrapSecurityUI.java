/**
 * Copyright (C) 2018-2021 Philip Helger (www.helger.com)
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
package com.helger.photon.bootstrap4.uictrls.ext;

import java.util.Locale;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.concurrent.Immutable;

import com.helger.commons.collection.impl.ICommonsList;
import com.helger.html.hc.IHCNode;
import com.helger.html.hc.ext.HCExtHelper;
import com.helger.photon.security.password.GlobalPasswordSettings;

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
  public static ICommonsList <IHCNode> createPasswordConstraintTip (@Nonnull final Locale aDisplayLocale)
  {
    final ICommonsList <String> aTexts = GlobalPasswordSettings.getPasswordConstraintList ()
                                                               .getAllPasswordConstraintDescriptions (aDisplayLocale);
    if (aTexts.isEmpty ())
      return null;

    return HCExtHelper.list2divList (aTexts);
  }
}
