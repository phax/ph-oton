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
package com.helger.appbasics.object;

import javax.annotation.Nonnull;

import org.joda.time.DateTime;

import com.helger.commons.microdom.IMicroElement;
import com.helger.commons.microdom.convert.IMicroTypeConverter;

/**
 * Abstract base class for object related micro type conversion.
 *
 * @author Philip Helger
 */
public abstract class AbstractObjectMicroTypeConverter implements IMicroTypeConverter
{
  private static final String ATTR_ID = "id";
  private static final String ATTR_CREATIONDT = "creationdt";
  private static final String ATTR_CREATIONUSERID = "creationuserid";
  private static final String ATTR_LASTMODDT = "lastmoddt";
  private static final String ATTR_LASTMODUSERID = "lastmoduserid";
  private static final String ATTR_DELETIONDT = "deletiondt";
  private static final String ATTR_DELETIONUSERID = "deletionuserid";

  public static final void setObjectFields (@Nonnull final IObject aValue, @Nonnull final IMicroElement aElement)
  {
    aElement.setAttribute (ATTR_ID, aValue.getID ());
    aElement.setAttributeWithConversion (ATTR_CREATIONDT, aValue.getCreationDateTime ());
    aElement.setAttribute (ATTR_CREATIONUSERID, aValue.getCreationUserID ());
    aElement.setAttributeWithConversion (ATTR_LASTMODDT, aValue.getLastModificationDateTime ());
    aElement.setAttribute (ATTR_LASTMODUSERID, aValue.getLastModificationUserID ());
    aElement.setAttributeWithConversion (ATTR_DELETIONDT, aValue.getDeletionDateTime ());
    aElement.setAttribute (ATTR_DELETIONUSERID, aValue.getDeletionUserID ());
  }

  @Nonnull
  public static final StubObject getStubObject (@Nonnull final IMicroElement aElement)
  {
    final String sID = aElement.getAttributeValue (ATTR_ID);
    final DateTime aCreationDT = aElement.getAttributeValueWithConversion (ATTR_CREATIONDT, DateTime.class);
    final String sCreationUserID = aElement.getAttributeValue (ATTR_CREATIONUSERID);
    final DateTime aLastModificationDT = aElement.getAttributeValueWithConversion (ATTR_LASTMODDT, DateTime.class);
    final String sLastModificationUserID = aElement.getAttributeValue (ATTR_LASTMODUSERID);
    final DateTime aDeletionDT = aElement.getAttributeValueWithConversion (ATTR_DELETIONDT, DateTime.class);
    final String sDeletionUserID = aElement.getAttributeValue (ATTR_DELETIONUSERID);
    return new StubObject (sID,
                           aCreationDT,
                           sCreationUserID,
                           aLastModificationDT,
                           sLastModificationUserID,
                           aDeletionDT,
                           sDeletionUserID);
  }
}
