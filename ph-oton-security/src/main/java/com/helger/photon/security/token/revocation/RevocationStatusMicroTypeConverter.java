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
package com.helger.photon.security.token.revocation;

import java.time.LocalDateTime;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.helger.commons.annotation.Nonempty;
import com.helger.commons.string.StringHelper;
import com.helger.commons.string.StringParser;
import com.helger.xml.microdom.IMicroElement;
import com.helger.xml.microdom.MicroElement;
import com.helger.xml.microdom.convert.IMicroTypeConverter;
import com.helger.xml.microdom.util.MicroHelper;

/**
 * Micro type converter for class {@link RevocationStatus}.
 *
 * @author Philip Helger
 */
public final class RevocationStatusMicroTypeConverter implements IMicroTypeConverter <RevocationStatus>
{
  private static final String ATTR_IS_REVOKED = "isrevoked";
  private static final String ATTR_USER_ID = "ruserid";
  private static final String ATTR_DT = "rdt";
  private static final String ELEMENT_REASON = "reason";

  @Nonnull
  public IMicroElement convertToMicroElement (@Nonnull final RevocationStatus aValue,
                                              @Nullable final String sNamespaceURI,
                                              @Nonnull @Nonempty final String sTagName)
  {
    final IMicroElement aElement = new MicroElement (sNamespaceURI, sTagName);
    aElement.setAttribute (ATTR_IS_REVOKED, aValue.isRevoked ());
    aElement.setAttribute (ATTR_USER_ID, aValue.getRevocationUserID ());
    aElement.setAttributeWithConversion (ATTR_DT, aValue.getRevocationDateTime ());
    if (StringHelper.hasText (aValue.getRevocationReason ()))
      aElement.appendElement (sNamespaceURI, ELEMENT_REASON).appendText (aValue.getRevocationReason ());
    return aElement;
  }

  @Nonnull
  public RevocationStatus convertToNative (@Nonnull final IMicroElement aElement)
  {
    final String sIsRevoked = aElement.getAttributeValue (ATTR_IS_REVOKED);
    final boolean bIsRevoked = StringParser.parseBool (sIsRevoked);

    final String sRevocationUserID = aElement.getAttributeValue (ATTR_USER_ID);
    final LocalDateTime aRevocationDT = aElement.getAttributeValueWithConversion (ATTR_DT, LocalDateTime.class);
    final String sRevocationReason = MicroHelper.getChildTextContent (aElement, ELEMENT_REASON);

    return new RevocationStatus (bIsRevoked, sRevocationUserID, aRevocationDT, sRevocationReason);
  }
}
