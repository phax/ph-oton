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
package com.helger.photon.security.token.app;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.helger.commons.annotation.Nonempty;
import com.helger.commons.microdom.IMicroElement;
import com.helger.commons.microdom.MicroElement;
import com.helger.commons.microdom.convert.MicroTypeConverter;
import com.helger.photon.security.object.AbstractObjectMicroTypeConverter;
import com.helger.photon.security.token.accesstoken.AccessToken;
import com.helger.photon.security.token.accesstoken.IAccessToken;

/**
 * Micro type converter for class {@link AppToken}.
 *
 * @author Philip Helger
 */
public final class AppTokenMicroTypeConverter extends AbstractObjectMicroTypeConverter
{
  private static final String ELEMENT_ACCESS_TOKEN = "accesstoken";
  private static final String ATTR_OWNER_NAME = "ownername";
  private static final String ATTR_OWNER_URL = "ownerurl";
  private static final String ATTR_OWNER_CONTACT = "ownercontact";
  private static final String ATTR_OWNER_CONTACT_EMAIL = "ownercontactemail";

  @Nonnull
  public IMicroElement convertToMicroElement (@Nonnull final Object aObject,
                                              @Nullable final String sNamespaceURI,
                                              @Nonnull @Nonempty final String sTagName)
  {
    final IAppToken aValue = (IAppToken) aObject;
    final IMicroElement aElement = new MicroElement (sNamespaceURI, sTagName);
    setObjectFields (aValue, aElement);
    for (final IAccessToken aAccessToken : aValue.getAllAccessTokens ())
      aElement.appendChild (MicroTypeConverter.convertToMicroElement (aAccessToken,
                                                                      sNamespaceURI,
                                                                      ELEMENT_ACCESS_TOKEN));
    aElement.setAttribute (ATTR_OWNER_NAME, aValue.getOwnerName ());
    aElement.setAttribute (ATTR_OWNER_URL, aValue.getOwnerURL ());
    aElement.setAttribute (ATTR_OWNER_CONTACT, aValue.getOwnerContact ());
    aElement.setAttribute (ATTR_OWNER_CONTACT_EMAIL, aValue.getOwnerContactEmail ());
    return aElement;
  }

  @Nonnull
  public AppToken convertToNative (@Nonnull final IMicroElement aElement)
  {
    final List <AccessToken> aAccessTokens = new ArrayList <> ();
    for (final IMicroElement e : aElement.getAllChildElements (ELEMENT_ACCESS_TOKEN))
      aAccessTokens.add (MicroTypeConverter.convertToNative (e, AccessToken.class));
    final String sOwnerName = aElement.getAttributeValue (ATTR_OWNER_NAME);
    final String sOwnerURL = aElement.getAttributeValue (ATTR_OWNER_URL);
    final String sOwnerContact = aElement.getAttributeValue (ATTR_OWNER_CONTACT);
    final String sOwnerContactEmail = aElement.getAttributeValue (ATTR_OWNER_CONTACT_EMAIL);

    return new AppToken (getStubObjectWithCustomAttrs (aElement),
                         aAccessTokens,
                         sOwnerName,
                         sOwnerURL,
                         sOwnerContact,
                         sOwnerContactEmail);
  }
}
