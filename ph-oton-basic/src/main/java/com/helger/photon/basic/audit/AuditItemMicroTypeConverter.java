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
package com.helger.photon.basic.audit;

import java.time.LocalDateTime;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.concurrent.Immutable;

import com.helger.commons.state.ESuccess;
import com.helger.commons.string.StringHelper;
import com.helger.commons.string.StringParser;
import com.helger.xml.microdom.IMicroElement;
import com.helger.xml.microdom.MicroElement;
import com.helger.xml.microdom.convert.IMicroTypeConverter;

/**
 * Convert {@link AuditItem} objects to {@link IMicroElement} and vice versa.
 *
 * @author Philip Helger
 */
@Immutable
public class AuditItemMicroTypeConverter implements IMicroTypeConverter
{
  public static final String ELEMENT_ITEM = "item";
  /* Old version with different format was called "dts" */
  public static final String ATTR_DT = "ldt";
  public static final String ATTR_USER = "user";
  public static final String ATTR_TYPE = "type";
  /* initially was called "succes" by accident */
  public static final String ATTR_SUCCESS = "success";

  @Nonnull
  public IMicroElement convertToMicroElement (@Nonnull final Object aObject,
                                              @Nullable final String sNamespaceURI,
                                              @Nonnull final String sTagName)
  {
    final AuditItem aAuditItem = (AuditItem) aObject;
    final IMicroElement eItem = new MicroElement (sNamespaceURI, sTagName);
    eItem.setAttributeWithConversion (ATTR_DT, aAuditItem.getDateTime ());
    eItem.setAttribute (ATTR_USER, aAuditItem.getUserID ());
    eItem.setAttribute (ATTR_TYPE, aAuditItem.getTypeID ());
    eItem.setAttribute (ATTR_SUCCESS, aAuditItem.isSuccess ());
    eItem.appendText (aAuditItem.getAction ());
    return eItem;
  }

  @Nonnull
  public AuditItem convertToNative (@Nonnull final IMicroElement eItem)
  {
    LocalDateTime aDT = eItem.getAttributeValueWithConversion (ATTR_DT, LocalDateTime.class);
    if (aDT == null)
    {
      // Legacy code - don't remove as old items stay persistent!
      final String sDT = eItem.getAttributeValue ("dts");
      aDT = LocalDateTime.parse (sDT);
      if (aDT == null)
        throw new IllegalStateException ("Failed to parse date time '" + sDT + "'");
    }

    final String sUserID = eItem.getAttributeValue (ATTR_USER);
    if (StringHelper.hasNoText (sUserID))
      throw new IllegalStateException ("Failed to find user ID");

    final String sType = eItem.getAttributeValue (ATTR_TYPE);
    final EAuditActionType eType = EAuditActionType.getFromIDOrNull (sType);
    if (eType == null)
      throw new IllegalStateException ("Failed to parse action type '" + sType + "'");

    final String sSuccess = eItem.getAttributeValue (ATTR_SUCCESS);
    final ESuccess eSuccess = ESuccess.valueOf (StringParser.parseBool (sSuccess));
    final String sAction = eItem.getTextContent ();

    return new AuditItem (aDT, sUserID, eType, eSuccess, sAction);
  }
}
