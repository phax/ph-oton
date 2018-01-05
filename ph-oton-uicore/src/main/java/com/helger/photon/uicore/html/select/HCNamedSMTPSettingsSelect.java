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
package com.helger.photon.uicore.html.select;

import java.util.List;
import java.util.Locale;

import javax.annotation.Nonnull;

import com.helger.html.request.IHCRequestField;
import com.helger.photon.core.smtp.NamedSMTPSettings;
import com.helger.smtp.settings.ISMTPSettings;

public class HCNamedSMTPSettingsSelect extends HCExtSelect
{
  public HCNamedSMTPSettingsSelect (@Nonnull final IHCRequestField aRF,
                                    @Nonnull final List <NamedSMTPSettings> aSettings,
                                    @Nonnull final Locale aDisplayLocale)
  {
    super (aRF);

    for (final NamedSMTPSettings aCurObject : aSettings)
    {
      final ISMTPSettings aSMTP = aCurObject.getSMTPSettings ();
      String sUserName = "";
      if (aSMTP.hasUserName ())
        sUserName = aSMTP.getUserName () + "@";
      addOption (aCurObject.getID (),
                 aCurObject.getName () +
                                      " (" +
                                      sUserName +
                                      aSMTP.getHostName () +
                                      (aSMTP.hasPort () ? ":" + aSMTP.getPort () : "") +
                                      ")");
    }

    addOptionPleaseSelect (aDisplayLocale);
  }
}
