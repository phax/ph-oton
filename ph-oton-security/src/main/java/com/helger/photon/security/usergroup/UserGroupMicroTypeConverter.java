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
package com.helger.photon.security.usergroup;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.helger.commons.collection.CollectionHelper;
import com.helger.commons.microdom.IMicroElement;
import com.helger.commons.microdom.MicroElement;
import com.helger.commons.microdom.util.MicroHelper;
import com.helger.commons.string.StringHelper;
import com.helger.photon.security.object.AbstractObjectMicroTypeConverter;

public final class UserGroupMicroTypeConverter extends AbstractObjectMicroTypeConverter
{
  private static final String ATTR_NAME = "name";
  private static final String ELEMENT_DESCRIPTION = "description";
  private static final String ELEMENT_USER = "user";
  private static final String ELEMENT_ROLE = "role";

  @Nonnull
  public IMicroElement convertToMicroElement (@Nonnull final Object aObject,
                                              @Nullable final String sNamespaceURI,
                                              @Nonnull final String sTagName)
  {
    final IUserGroup aUserGroup = (IUserGroup) aObject;
    final IMicroElement aElement = new MicroElement (sNamespaceURI, sTagName);
    setObjectFields (aUserGroup, aElement);
    aElement.setAttribute (ATTR_NAME, aUserGroup.getName ());
    if (StringHelper.hasText (aUserGroup.getDescription ()))
      aElement.appendElement (sNamespaceURI, ELEMENT_DESCRIPTION).appendText (aUserGroup.getDescription ());
    for (final String sUserID : CollectionHelper.getSorted (aUserGroup.getAllContainedUserIDs ()))
      aElement.appendElement (ELEMENT_USER).setAttribute (ATTR_ID, sUserID);
    for (final String sRoleID : CollectionHelper.getSorted (aUserGroup.getAllContainedRoleIDs ()))
      aElement.appendElement (ELEMENT_ROLE).setAttribute (ATTR_ID, sRoleID);
    return aElement;
  }

  @Nonnull
  public UserGroup convertToNative (@Nonnull final IMicroElement aElement)
  {
    final String sName = aElement.getAttributeValue (ATTR_NAME);
    final String sDescription = MicroHelper.getChildTextContentTrimmed (aElement, ELEMENT_DESCRIPTION);

    final UserGroup aUserGroup = new UserGroup (getStubObjectWithCustomAttrs (aElement), sName, sDescription);
    for (final IMicroElement eUser : aElement.getAllChildElements (ELEMENT_USER))
      aUserGroup.assignUser (eUser.getAttributeValue (ATTR_ID));
    for (final IMicroElement eRole : aElement.getAllChildElements (ELEMENT_ROLE))
      aUserGroup.assignRole (eRole.getAttributeValue (ATTR_ID));
    return aUserGroup;
  }
}
