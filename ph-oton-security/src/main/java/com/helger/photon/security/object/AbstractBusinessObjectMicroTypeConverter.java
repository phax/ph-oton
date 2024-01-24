/*
 * Copyright (C) 2014-2024 Philip Helger (www.helger.com)
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

import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.util.Map;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.helger.commons.annotation.ContainsSoftMigration;
import com.helger.commons.collection.CollectionHelper;
import com.helger.commons.collection.impl.CommonsLinkedHashMap;
import com.helger.commons.collection.impl.ICommonsOrderedMap;
import com.helger.tenancy.IBusinessObject;
import com.helger.xml.microdom.IMicroElement;
import com.helger.xml.microdom.IMicroQName;
import com.helger.xml.microdom.MicroQName;
import com.helger.xml.microdom.convert.IMicroTypeConverter;

/**
 * Abstract base class for object related micro type conversion.
 *
 * @author Philip Helger
 * @param <T>
 *        Data type to handle. Must extend {@link IBusinessObject}
 */
public abstract class AbstractBusinessObjectMicroTypeConverter <T extends IBusinessObject> implements IMicroTypeConverter <T>
{
  protected static final IMicroQName ATTR_ID = new MicroQName ("id");
  protected static final IMicroQName ATTR_CREATIONLDT = new MicroQName ("creationldt");
  protected static final IMicroQName ATTR_CREATIONUSERID = new MicroQName ("creationuserid");
  protected static final IMicroQName ATTR_LASTMODLDT = new MicroQName ("lastmodldt");
  protected static final IMicroQName ATTR_LASTMODUSERID = new MicroQName ("lastmoduserid");
  protected static final IMicroQName ATTR_DELETIONLDT = new MicroQName ("deletionldt");
  protected static final IMicroQName ATTR_DELETIONUSERID = new MicroQName ("deletionuserid");
  protected static final String ELEMENT_CUSTOM = "custom";

  protected AbstractBusinessObjectMicroTypeConverter ()
  {}

  public static final void setObjectFields (@Nonnull final IBusinessObject aValue, @Nonnull final IMicroElement aElement)
  {
    aElement.setAttribute (ATTR_ID, aValue.getID ());
    aElement.setAttributeWithConversion (ATTR_CREATIONLDT, aValue.getCreationDateTime ());
    aElement.setAttribute (ATTR_CREATIONUSERID, aValue.getCreationUserID ());
    aElement.setAttributeWithConversion (ATTR_LASTMODLDT, aValue.getLastModificationDateTime ());
    aElement.setAttribute (ATTR_LASTMODUSERID, aValue.getLastModificationUserID ());
    aElement.setAttributeWithConversion (ATTR_DELETIONLDT, aValue.getDeletionDateTime ());
    aElement.setAttribute (ATTR_DELETIONUSERID, aValue.getDeletionUserID ());
    for (final Map.Entry <String, String> aEntry : CollectionHelper.getSortedByKey (aValue.attrs ()).entrySet ())
    {
      final IMicroElement eCustom = aElement.appendElement (aElement.getNamespaceURI (), ELEMENT_CUSTOM);
      eCustom.setAttribute (ATTR_ID, aEntry.getKey ());
      eCustom.appendText (aEntry.getValue ());
    }
  }

  /**
   * For migration purposes - read LocalDateTime - if no present fall back to
   * DateTime
   *
   * @param aElement
   *        Element
   * @param aLDTName
   *        new local date time element name
   * @param aDTName
   *        old date time element name
   * @return May be <code>null</code>.
   */
  @Nullable
  @ContainsSoftMigration
  public static LocalDateTime readAsLocalDateTime (@Nonnull final IMicroElement aElement,
                                                   @Nonnull final IMicroQName aLDTName,
                                                   @Nonnull final String aDTName)
  {
    LocalDateTime aLDT = aElement.getAttributeValueWithConversion (aLDTName, LocalDateTime.class);
    if (aLDT == null)
    {
      final ZonedDateTime aDT = aElement.getAttributeValueWithConversion (aDTName, ZonedDateTime.class);
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

    final ICommonsOrderedMap <String, String> aCustomAttrs = new CommonsLinkedHashMap <> ();
    for (final IMicroElement eCustom : aElement.getAllChildElements (ELEMENT_CUSTOM))
      aCustomAttrs.put (eCustom.getAttributeValue (ATTR_ID), eCustom.getTextContent ());

    return new StubObject (sID,
                           aCreationLDT,
                           sCreationUserID,
                           aLastModificationLDT,
                           sLastModificationUserID,
                           aDeletionLDT,
                           sDeletionUserID,
                           aCustomAttrs);
  }
}
