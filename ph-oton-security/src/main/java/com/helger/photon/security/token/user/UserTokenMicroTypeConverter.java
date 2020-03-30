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
package com.helger.photon.security.token.user;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.helger.commons.annotation.Nonempty;
import com.helger.commons.collection.impl.CommonsArrayList;
import com.helger.commons.collection.impl.ICommonsList;
import com.helger.photon.security.mgr.PhotonSecurityManager;
import com.helger.photon.security.object.AbstractBusinessObjectMicroTypeConverter;
import com.helger.photon.security.token.accesstoken.AccessToken;
import com.helger.photon.security.token.accesstoken.IAccessToken;
import com.helger.photon.security.user.IUser;
import com.helger.photon.security.user.IUserManager;
import com.helger.xml.microdom.IMicroElement;
import com.helger.xml.microdom.MicroElement;
import com.helger.xml.microdom.convert.MicroTypeConverter;

/**
 * Micro type converter for class {@link UserToken}.
 *
 * @author Philip Helger
 */
public final class UserTokenMicroTypeConverter extends AbstractBusinessObjectMicroTypeConverter <UserToken>
{
  private static final String ELEMENT_ACCESS_TOKEN = "accesstoken";
  private static final String ATTR_USER_ID = "userid";

  @Nonnull
  public IMicroElement convertToMicroElement (@Nonnull final UserToken aValue,
                                              @Nullable final String sNamespaceURI,
                                              @Nonnull @Nonempty final String sTagName)
  {
    final IMicroElement aElement = new MicroElement (sNamespaceURI, sTagName);
    setObjectFields (aValue, aElement);
    for (final IAccessToken aAccessToken : aValue.getAllAccessTokens ())
      aElement.appendChild (MicroTypeConverter.convertToMicroElement (aAccessToken,
                                                                      sNamespaceURI,
                                                                      ELEMENT_ACCESS_TOKEN));
    aElement.setAttribute (ATTR_USER_ID, aValue.getUser ().getID ());
    return aElement;
  }

  @Nonnull
  public UserToken convertToNative (@Nonnull final IMicroElement aElement)
  {
    final IUserManager aUserMgr = PhotonSecurityManager.getUserMgr ();

    final ICommonsList <AccessToken> aAccessTokens = new CommonsArrayList <> ();
    for (final IMicroElement e : aElement.getAllChildElements (ELEMENT_ACCESS_TOKEN))
      aAccessTokens.add (MicroTypeConverter.convertToNative (e, AccessToken.class));

    final String sUserID = aElement.getAttributeValue (ATTR_USER_ID);
    final IUser aUser = aUserMgr.getUserOfID (sUserID);
    if (aUser == null)
      throw new IllegalStateException ("Failed to resolve user with ID '" + sUserID + "'");

    return new UserToken (getStubObject (aElement), aAccessTokens, aUser);
  }
}
