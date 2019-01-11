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
package com.helger.photon.core.smtp;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.helger.smtp.settings.ISMTPSettings;
import com.helger.smtp.settings.SMTPSettings;
import com.helger.xml.microdom.IMicroElement;
import com.helger.xml.microdom.MicroElement;
import com.helger.xml.microdom.convert.IMicroTypeConverter;
import com.helger.xml.microdom.convert.MicroTypeConverter;
import com.helger.xml.microdom.util.MicroHelper;

public final class NamedSMTPSettingsMicroTypeConverter implements IMicroTypeConverter <NamedSMTPSettings>
{
  private static final String ATTR_ID = "id";
  private static final String ELEMENT_NAME = "name";
  private static final String ELEMENT_SMTPSETTINGS = "smtpsettings";

  @Nonnull
  public IMicroElement convertToMicroElement (@Nonnull final NamedSMTPSettings aNamedSMTPSettings,
                                              @Nullable final String sNamespaceURI,
                                              @Nonnull final String sTagName)
  {
    final IMicroElement eSMTPSettings = new MicroElement (sNamespaceURI, sTagName);
    eSMTPSettings.setAttribute (ATTR_ID, aNamedSMTPSettings.getID ());
    eSMTPSettings.appendElement (sNamespaceURI, ELEMENT_NAME).appendText (aNamedSMTPSettings.getName ());
    eSMTPSettings.appendChild (MicroTypeConverter.convertToMicroElement (aNamedSMTPSettings.getSMTPSettings (),
                                                                         sNamespaceURI,
                                                                         ELEMENT_SMTPSETTINGS));
    return eSMTPSettings;
  }

  @Nonnull
  public NamedSMTPSettings convertToNative (@Nonnull final IMicroElement eNamedSMTPSettings)
  {
    final String sID = eNamedSMTPSettings.getAttributeValue (ATTR_ID);

    final String sName = MicroHelper.getChildTextContent (eNamedSMTPSettings, ELEMENT_NAME);

    final IMicroElement eSMTPSettings = eNamedSMTPSettings.getFirstChildElement (ELEMENT_SMTPSETTINGS);
    final ISMTPSettings aSMTPSettings = MicroTypeConverter.convertToNative (eSMTPSettings, SMTPSettings.class);

    return new NamedSMTPSettings (sID, sName, aSMTPSettings);
  }
}
