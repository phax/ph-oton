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
package com.helger.photon.basic.security.usergroup;

import java.util.LinkedHashMap;
import java.util.Map;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.helger.commons.collections.CollectionHelper;
import com.helger.commons.microdom.IMicroElement;
import com.helger.commons.microdom.convert.IMicroTypeConverter;
import com.helger.commons.microdom.impl.MicroElement;
import com.helger.commons.microdom.utils.MicroUtils;
import com.helger.commons.string.StringHelper;

public final class UserGroupMicroTypeConverter implements IMicroTypeConverter
{
  private static final String ATTR_ID = "id";
  private static final String ATTR_NAME = "name";
  private static final String ELEMENT_DESCRIPTION = "description";
  private static final String ELEMENT_USER = "user";
  private static final String ELEMENT_ROLE = "role";
  private static final String ELEMENT_CUSTOM = "custom";

  @Nonnull
  public IMicroElement convertToMicroElement (@Nonnull final Object aObject,
                                              @Nullable final String sNamespaceURI,
                                              @Nonnull final String sTagName)
  {
    final IUserGroup aUserGroup = (IUserGroup) aObject;
    final IMicroElement eUserGroup = new MicroElement (sNamespaceURI, sTagName);
    eUserGroup.setAttribute (ATTR_ID, aUserGroup.getID ());
    eUserGroup.setAttribute (ATTR_NAME, aUserGroup.getName ());
    if (StringHelper.hasText (aUserGroup.getDescription ()))
      eUserGroup.appendElement (sNamespaceURI, ELEMENT_DESCRIPTION).appendText (aUserGroup.getDescription ());
    for (final Map.Entry <String, Object> aEntry : CollectionHelper.getSortedByKey (aUserGroup.getAllAttributes ())
                                                                  .entrySet ())
    {
      final IMicroElement eCustom = eUserGroup.appendElement (ELEMENT_CUSTOM);
      eCustom.setAttribute (ATTR_ID, aEntry.getKey ());
      eCustom.appendText (String.valueOf (aEntry.getValue ()));
    }
    for (final String sUserID : CollectionHelper.getSorted (aUserGroup.getAllContainedUserIDs ()))
      eUserGroup.appendElement (ELEMENT_USER).setAttribute (ATTR_ID, sUserID);
    for (final String sRoleID : CollectionHelper.getSorted (aUserGroup.getAllContainedRoleIDs ()))
      eUserGroup.appendElement (ELEMENT_ROLE).setAttribute (ATTR_ID, sRoleID);
    return eUserGroup;
  }

  @Nonnull
  public UserGroup convertToNative (@Nonnull final IMicroElement eUserGroup)
  {
    final String sID = eUserGroup.getAttributeValue (ATTR_ID);
    final String sName = eUserGroup.getAttributeValue (ATTR_NAME);
    final String sDescription = MicroUtils.getChildTextContentTrimmed (eUserGroup, ELEMENT_DESCRIPTION);
    final Map <String, String> aCustomAttrs = new LinkedHashMap <String, String> ();
    for (final IMicroElement eCustom : eUserGroup.getAllChildElements (ELEMENT_CUSTOM))
      aCustomAttrs.put (eCustom.getAttributeValue (ATTR_ID), eCustom.getTextContent ());

    final UserGroup aUserGroup = new UserGroup (sID, sName, sDescription, aCustomAttrs);
    for (final IMicroElement eUser : eUserGroup.getAllChildElements (ELEMENT_USER))
      aUserGroup.assignUser (eUser.getAttributeValue (ATTR_ID));
    for (final IMicroElement eRole : eUserGroup.getAllChildElements (ELEMENT_ROLE))
      aUserGroup.assignRole (eRole.getAttributeValue (ATTR_ID));
    return aUserGroup;
  }
}
