/**
 * Copyright (C) 2014-2016 Philip Helger (www.helger.com)
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
package com.helger.photon.security.user;

import java.time.LocalDateTime;
import java.util.Locale;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.helger.commons.annotation.ContainsSoftMigration;
import com.helger.commons.locale.LocaleCache;
import com.helger.commons.string.StringHelper;
import com.helger.commons.string.StringParser;
import com.helger.photon.security.object.AbstractObjectMicroTypeConverter;
import com.helger.security.password.hash.PasswordHash;
import com.helger.security.password.hash.PasswordHashCreatorPBKDF2_1000_48;
import com.helger.security.password.salt.IPasswordSalt;
import com.helger.security.password.salt.PasswordSalt;
import com.helger.xml.microdom.IMicroElement;
import com.helger.xml.microdom.MicroElement;
import com.helger.xml.microdom.util.MicroHelper;

public final class UserMicroTypeConverter extends AbstractObjectMicroTypeConverter
{
  private static final String ATTR_DESIREDLOCALE = "desiredlocale";
  private static final String ATTR_LASTLOGINLDT = "lastloginldt";
  private static final String ATTR_LOGINCOUNT = "logincount";
  private static final String ATTR_CONSECUTIVEFAILEDLOGINCOUNT = "consecutivefailedlogincount";
  private static final String ELEMENT_LOGINNAME = "loginname";
  private static final String ELEMENT_EMAILADDRESS = "emailaddress";
  private static final String ELEMENT_PASSWORDHASH = "passwordhash";
  private static final String ATTR_ALGORITHM = "algorithm";
  private static final String ATTR_SALT = "salt";
  private static final String ELEMENT_FIRSTNAME = "firstname";
  private static final String ELEMENT_LASTNAME = "lastname";
  private static final String ELEMENT_DESCRIPTION = "description";
  private static final String ATTR_DISABLED = "disabled";

  @Nonnull
  public IMicroElement convertToMicroElement (@Nonnull final Object aObject,
                                              @Nullable final String sNamespaceURI,
                                              @Nonnull final String sTagName)
  {
    final IUser aUser = (IUser) aObject;
    final IMicroElement aElement = new MicroElement (sNamespaceURI, sTagName);
    setObjectFields (aUser, aElement);
    aElement.appendElement (ELEMENT_LOGINNAME).appendText (aUser.getLoginName ());
    if (aUser.getEmailAddress () != null)
      aElement.appendElement (ELEMENT_EMAILADDRESS).appendText (aUser.getEmailAddress ());
    final IMicroElement ePasswordHash = aElement.appendElement (ELEMENT_PASSWORDHASH);
    ePasswordHash.setAttribute (ATTR_ALGORITHM, aUser.getPasswordHash ().getAlgorithmName ());
    if (aUser.getPasswordHash ().hasSalt ())
      ePasswordHash.setAttribute (ATTR_SALT, aUser.getPasswordHash ().getSaltAsString ());
    ePasswordHash.appendText (aUser.getPasswordHash ().getPasswordHashValue ());
    if (aUser.getFirstName () != null)
      aElement.appendElement (ELEMENT_FIRSTNAME).appendText (aUser.getFirstName ());
    if (aUser.getLastName () != null)
      aElement.appendElement (ELEMENT_LASTNAME).appendText (aUser.getLastName ());
    if (StringHelper.hasText (aUser.getDescription ()))
      aElement.appendElement (sNamespaceURI, ELEMENT_DESCRIPTION).appendText (aUser.getDescription ());
    if (aUser.getDesiredLocale () != null)
      aElement.setAttribute (ATTR_DESIREDLOCALE, aUser.getDesiredLocale ().toString ());
    if (aUser.getLastLoginDateTime () != null)
      aElement.setAttributeWithConversion (ATTR_LASTLOGINLDT, aUser.getLastLoginDateTime ());
    aElement.setAttribute (ATTR_LOGINCOUNT, aUser.getLoginCount ());
    aElement.setAttribute (ATTR_CONSECUTIVEFAILEDLOGINCOUNT, aUser.getConsecutiveFailedLoginCount ());
    aElement.setAttribute (ATTR_DISABLED, Boolean.toString (aUser.isDisabled ()));
    return aElement;
  }

  @ContainsSoftMigration
  @Nonnull
  public User convertToNative (@Nonnull final IMicroElement aElement)
  {
    final String sLoginName = MicroHelper.getChildTextContentTrimmed (aElement, ELEMENT_LOGINNAME);
    final String sEmailAddress = MicroHelper.getChildTextContentTrimmed (aElement, ELEMENT_EMAILADDRESS);
    final IMicroElement ePasswordHash = aElement.getFirstChildElement (ELEMENT_PASSWORDHASH);
    String sPasswordHashAlgorithm = ePasswordHash.getAttributeValue (ATTR_ALGORITHM);
    if (sPasswordHashAlgorithm == null)
    {
      // migration
      sPasswordHashAlgorithm = PasswordHashCreatorPBKDF2_1000_48.ALGORITHM;
    }
    final String sSalt = ePasswordHash.getAttributeValue (ATTR_SALT);
    final IPasswordSalt aSalt = PasswordSalt.createFromStringMaybe (sSalt);
    final String sPasswordHashValue = ePasswordHash.getTextContentTrimmed ();
    final PasswordHash aPasswordHash = new PasswordHash (sPasswordHashAlgorithm, aSalt, sPasswordHashValue);
    final String sFirstName = MicroHelper.getChildTextContentTrimmed (aElement, ELEMENT_FIRSTNAME);
    final String sLastName = MicroHelper.getChildTextContentTrimmed (aElement, ELEMENT_LASTNAME);
    final String sDescription = MicroHelper.getChildTextContentTrimmed (aElement, ELEMENT_DESCRIPTION);
    final String sDesiredLocale = aElement.getAttributeValue (ATTR_DESIREDLOCALE);
    final Locale aDesiredLocale = sDesiredLocale == null ? null : LocaleCache.getInstance ().getLocale (sDesiredLocale);
    final LocalDateTime aLastLoginLDT = readAsLocalDateTime (aElement, ATTR_LASTLOGINLDT, "lastlogindt");
    final int nLoginCount = StringParser.parseInt (aElement.getAttributeValue (ATTR_LOGINCOUNT), 0);
    final int nConsecutiveFailedLoginCount = StringParser.parseInt (aElement.getAttributeValue (ATTR_CONSECUTIVEFAILEDLOGINCOUNT),
                                                                    0);
    final String sDisabled = aElement.getAttributeValue (ATTR_DISABLED);
    final boolean bDisabled = StringParser.parseBool (sDisabled);

    return new User (getStubObjectWithCustomAttrs (aElement),
                     sLoginName,
                     sEmailAddress,
                     aPasswordHash,
                     sFirstName,
                     sLastName,
                     sDescription,
                     aDesiredLocale,
                     aLastLoginLDT,
                     nLoginCount,
                     nConsecutiveFailedLoginCount,
                     bDisabled);
  }
}
