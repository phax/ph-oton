/*
 * Copyright (C) 2014-2026 Philip Helger (www.helger.com)
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
package com.helger.photon.security.totp;

import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.Nullable;

import com.helger.base.string.StringParser;
import com.helger.collection.commons.CommonsArrayList;
import com.helger.collection.commons.ICommonsList;
import com.helger.security.password.hash.PasswordHash;
import com.helger.security.password.salt.IPasswordSalt;
import com.helger.security.password.salt.PasswordSalt;
import com.helger.xml.microdom.IMicroElement;
import com.helger.xml.microdom.IMicroQName;
import com.helger.xml.microdom.MicroElement;
import com.helger.xml.microdom.MicroQName;
import com.helger.xml.microdom.convert.IMicroTypeConverter;
import com.helger.xml.microdom.util.MicroHelper;

/**
 * XML round-trip for {@link UserTotpRecord}.
 *
 * @author Philip Helger
 */
public final class UserTotpRecordMicroTypeConverter implements IMicroTypeConverter <UserTotpRecord>
{
  private static final IMicroQName ATTR_USERID = new MicroQName ("userid");
  private static final IMicroQName ATTR_STATE = new MicroQName ("state");
  private static final IMicroQName ATTR_LASTCOUNTER = new MicroQName ("lastcounter");
  private static final String ELEMENT_SECRET = "secret";
  private static final String ELEMENT_RECOVERY_CODE = "recoverycode";
  private static final IMicroQName ATTR_ALGORITHM = new MicroQName ("algorithm");
  private static final IMicroQName ATTR_SALT = new MicroQName ("salt");

  @NonNull
  public IMicroElement convertToMicroElement (@NonNull final UserTotpRecord aRecord,
                                              @Nullable final String sNamespaceURI,
                                              @NonNull final String sTagName)
  {
    final IMicroElement aElement = new MicroElement (sNamespaceURI, sTagName);
    aElement.setAttribute (ATTR_USERID, aRecord.getUserID ());
    aElement.setAttribute (ATTR_STATE, aRecord.getState ().getID ());
    aElement.setAttribute (ATTR_LASTCOUNTER, Long.toString (aRecord.getLastAcceptedCounter ()));
    aElement.addElementNS (sNamespaceURI, ELEMENT_SECRET).addText (aRecord.getSecret ());
    for (final PasswordHash aHash : aRecord.recoveryCodes ())
    {
      final IMicroElement eCode = aElement.addElementNS (sNamespaceURI, ELEMENT_RECOVERY_CODE);
      eCode.setAttribute (ATTR_ALGORITHM, aHash.getAlgorithmName ());
      if (aHash.hasSalt ())
        eCode.setAttribute (ATTR_SALT, aHash.getSaltAsString ());
      eCode.addText (aHash.getPasswordHashValue ());
    }
    return aElement;
  }

  @NonNull
  public UserTotpRecord convertToNative (@NonNull final IMicroElement aElement)
  {
    final String sUserID = aElement.getAttributeValue (ATTR_USERID);
    final ETotpEnrollmentState eState = ETotpEnrollmentState.getFromIDOrNull (aElement.getAttributeValue (ATTR_STATE));
    final long nLastCounter = StringParser.parseLong (aElement.getAttributeValue (ATTR_LASTCOUNTER), 0L);
    final String sSecret = MicroHelper.getChildTextContentTrimmed (aElement, ELEMENT_SECRET);
    final ICommonsList <PasswordHash> aCodes = new CommonsArrayList <> ();
    for (final IMicroElement eCode : aElement.getAllChildElements (ELEMENT_RECOVERY_CODE))
    {
      final String sAlgorithm = eCode.getAttributeValue (ATTR_ALGORITHM);
      final IPasswordSalt aSalt = PasswordSalt.createFromStringMaybe (eCode.getAttributeValue (ATTR_SALT));
      final String sHash = eCode.getTextContentTrimmed ();
      aCodes.add (new PasswordHash (sAlgorithm, aSalt, sHash));
    }
    return new UserTotpRecord (sUserID,
                               eState != null ? eState : ETotpEnrollmentState.PENDING,
                               sSecret,
                               aCodes,
                               nLastCounter);
  }
}
