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
package com.helger.photon.security.token.user;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.helger.commons.annotation.Nonempty;
import com.helger.commons.microdom.IMicroElement;
import com.helger.commons.microdom.MicroElement;
import com.helger.commons.microdom.convert.MicroTypeConverter;
import com.helger.photon.security.mgr.PhotonSecurityManager;
import com.helger.photon.security.object.AbstractObjectMicroTypeConverter;
import com.helger.photon.security.token.accesstoken.AccessToken;
import com.helger.photon.security.token.accesstoken.IAccessToken;
import com.helger.photon.security.token.app.AppTokenManager;
import com.helger.photon.security.token.app.IAppToken;

/**
 * Micro type converter for class {@link UserToken}.
 *
 * @author Philip Helger
 */
public final class UserTokenMicroTypeConverter extends AbstractObjectMicroTypeConverter
{
  private static final String ELEMENT_ACCESS_TOKEN = "accesstoken";
  private static final String ATTR_APP_TOKEN_ID = "apptoken";
  private static final String ATTR_USER_NAME = "username";

  @Nonnull
  public IMicroElement convertToMicroElement (@Nonnull final Object aObject,
                                              @Nullable final String sNamespaceURI,
                                              @Nonnull @Nonempty final String sTagName)
  {
    final IUserToken aValue = (IUserToken) aObject;
    final IMicroElement aElement = new MicroElement (sNamespaceURI, sTagName);
    setObjectFields (aValue, aElement);
    for (final IAccessToken aAccessToken : aValue.getAllAccessTokens ())
      aElement.appendChild (MicroTypeConverter.convertToMicroElement (aAccessToken,
                                                                      sNamespaceURI,
                                                                      ELEMENT_ACCESS_TOKEN));
    aElement.setAttribute (ATTR_APP_TOKEN_ID, aValue.getAppToken ().getID ());
    aElement.setAttribute (ATTR_USER_NAME, aValue.getUserName ());
    return aElement;
  }

  @Nonnull
  public UserToken convertToNative (@Nonnull final IMicroElement aElement)
  {
    final AppTokenManager aAppTokenMgr = PhotonSecurityManager.getAppTokenMgr ();

    final List <AccessToken> aAccessTokens = new ArrayList <> ();
    for (final IMicroElement e : aElement.getAllChildElements (ELEMENT_ACCESS_TOKEN))
      aAccessTokens.add (MicroTypeConverter.convertToNative (e, AccessToken.class));

    final String sAppTokenID = aElement.getAttributeValue (ATTR_APP_TOKEN_ID);
    final IAppToken aAppToken = aAppTokenMgr.getAppTokenOfID (sAppTokenID);
    if (aAppToken == null)
      throw new IllegalStateException ("Failed to resolve app token with ID '" + sAppTokenID + "'");

    final String sUserName = aElement.getAttributeValue (ATTR_USER_NAME);

    return new UserToken (getStubObjectWithCustomAttrs (aElement), aAccessTokens, aAppToken, sUserName);
  }
}
