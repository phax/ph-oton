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
package com.helger.photon.security.object;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import org.joda.time.DateTime;
import org.joda.time.LocalDateTime;

import com.helger.commons.annotation.ContainsSoftMigration;
import com.helger.commons.microdom.IMicroElement;
import com.helger.commons.microdom.convert.IMicroTypeConverter;
import com.helger.photon.basic.object.IObject;

/**
 * Abstract base class for object related micro type conversion.
 *
 * @author Philip Helger
 */
public abstract class AbstractObjectMicroTypeConverter implements IMicroTypeConverter
{
  protected static final String ATTR_ID = "id";
  protected static final String ATTR_CREATIONLDT = "creationldt";
  protected static final String ATTR_CREATIONUSERID = "creationuserid";
  protected static final String ATTR_LASTMODLDT = "lastmodldt";
  protected static final String ATTR_LASTMODUSERID = "lastmoduserid";
  protected static final String ATTR_DELETIONLDT = "deletionldt";
  protected static final String ATTR_DELETIONUSERID = "deletionuserid";

  public static final void setObjectFields (@Nonnull final IObject aValue, @Nonnull final IMicroElement aElement)
  {
    aElement.setAttribute (ATTR_ID, aValue.getID ());
    aElement.setAttributeWithConversion (ATTR_CREATIONLDT, aValue.getCreationDateTime ());
    aElement.setAttribute (ATTR_CREATIONUSERID, aValue.getCreationUserID ());
    aElement.setAttributeWithConversion (ATTR_LASTMODLDT, aValue.getLastModificationDateTime ());
    aElement.setAttribute (ATTR_LASTMODUSERID, aValue.getLastModificationUserID ());
    aElement.setAttributeWithConversion (ATTR_DELETIONLDT, aValue.getDeletionDateTime ());
    aElement.setAttribute (ATTR_DELETIONUSERID, aValue.getDeletionUserID ());
  }

  /**
   * For migration purposes - read LocalDateTime - if no present fall back to
   * DateTime
   *
   * @param aElement
   *        Element
   * @param sLDTName
   *        new local date time element name
   * @param sDTName
   *        old date time element name
   * @return May be <code>null</code>.
   */
  @Nullable
  @ContainsSoftMigration
  protected static LocalDateTime readAsLocalDateTime (@Nonnull final IMicroElement aElement,
                                                      @Nonnull final String sLDTName,
                                                      @Nonnull final String sDTName)
  {
    LocalDateTime aLDT = aElement.getAttributeValueWithConversion (sLDTName, LocalDateTime.class);
    if (aLDT == null)
    {
      final DateTime aDT = aElement.getAttributeValueWithConversion (sDTName, DateTime.class);
      if (aDT != null)
        aLDT = aDT.toLocalDateTime ();
    }
    return aLDT;
  }

  @Nonnull
  public static final StubObject getStubObject (@Nonnull final IMicroElement aElement)
  {
    // ID
    final String sID = aElement.getAttributeValue (ATTR_ID);

    // Creation
    final LocalDateTime aCreationLDT = readAsLocalDateTime (aElement, ATTR_CREATIONLDT, "creationdt");
    final String sCreationUserID = aElement.getAttributeValue (ATTR_CREATIONUSERID);

    // Last modification
    final LocalDateTime aLastModificationLDT = readAsLocalDateTime (aElement, ATTR_LASTMODLDT, "lastmodldt");
    final String sLastModificationUserID = aElement.getAttributeValue (ATTR_LASTMODUSERID);

    // Deletion
    final LocalDateTime aDeletionLDT = readAsLocalDateTime (aElement, ATTR_DELETIONLDT, "deletiondt");
    final String sDeletionUserID = aElement.getAttributeValue (ATTR_DELETIONUSERID);

    return new StubObject (sID,
                           aCreationLDT,
                           sCreationUserID,
                           aLastModificationLDT,
                           sLastModificationUserID,
                           aDeletionLDT,
                           sDeletionUserID);
  }
}
