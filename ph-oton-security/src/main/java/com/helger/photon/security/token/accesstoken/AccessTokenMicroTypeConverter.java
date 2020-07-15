/**
 * Copyright (C) 2014-2020 Philip Helger (www.helger.com)
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
package com.helger.photon.security.token.accesstoken;

import java.time.LocalDateTime;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.helger.commons.annotation.Nonempty;
import com.helger.photon.security.token.revocation.RevocationStatus;
import com.helger.xml.microdom.IMicroElement;
import com.helger.xml.microdom.MicroElement;
import com.helger.xml.microdom.convert.IMicroTypeConverter;
import com.helger.xml.microdom.convert.MicroTypeConverter;

/**
 * Micro type converter for class {@link AccessToken}.
 *
 * @author Philip Helger
 */
public final class AccessTokenMicroTypeConverter implements IMicroTypeConverter <AccessToken>
{
  private static final String ATTR_TOKEN_STRING = "tokenstring";
  private static final String ATTR_NOT_BEFORE = "notbefore";
  private static final String ATTR_NOT_AFTER = "notafter";
  private static final String ELEMENT_REVOCATION = "revocation";

  @Nonnull
  public IMicroElement convertToMicroElement (@Nonnull final AccessToken aValue,
                                              @Nullable final String sNamespaceURI,
                                              @Nonnull @Nonempty final String sTagName)
  {
    final IMicroElement aElement = new MicroElement (sNamespaceURI, sTagName);
    aElement.setAttribute (ATTR_TOKEN_STRING, aValue.getTokenString ());
    aElement.setAttributeWithConversion (ATTR_NOT_BEFORE, aValue.getNotBefore ());
    aElement.setAttributeWithConversion (ATTR_NOT_AFTER, aValue.getNotAfter ());
    aElement.appendChild (MicroTypeConverter.convertToMicroElement (aValue.getRevocationStatus (), sNamespaceURI, ELEMENT_REVOCATION));
    return aElement;
  }

  @Nonnull
  public AccessToken convertToNative (@Nonnull final IMicroElement aElement)
  {
    final String sTokenString = aElement.getAttributeValue (ATTR_TOKEN_STRING);
    final LocalDateTime aNotBefore = aElement.getAttributeValueWithConversion (ATTR_NOT_BEFORE, LocalDateTime.class);
    final LocalDateTime aNotAfter = aElement.getAttributeValueWithConversion (ATTR_NOT_AFTER, LocalDateTime.class);
    final RevocationStatus aRevocationStatus = MicroTypeConverter.convertToNative (aElement.getFirstChildElement (ELEMENT_REVOCATION),
                                                                                   RevocationStatus.class);

    return new AccessToken (sTokenString, aNotBefore, aNotAfter, aRevocationStatus);
  }
}
