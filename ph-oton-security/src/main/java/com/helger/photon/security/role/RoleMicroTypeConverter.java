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
package com.helger.photon.security.role;

import java.util.LinkedHashMap;
import java.util.Map;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.helger.commons.collection.CollectionHelper;
import com.helger.commons.microdom.IMicroElement;
import com.helger.commons.microdom.MicroElement;
import com.helger.commons.microdom.convert.IMicroTypeConverter;
import com.helger.commons.microdom.util.MicroHelper;
import com.helger.commons.string.StringHelper;

/**
 * Convert {@link Role} objects to {@link IMicroElement} and vice versa.
 *
 * @author Philip Helger
 */
public final class RoleMicroTypeConverter implements IMicroTypeConverter
{
  private static final String ATTR_ID = "id";
  private static final String ATTR_NAME = "name";
  private static final String ELEMENT_DESCRIPTION = "description";
  private static final String ELEMENT_CUSTOM = "custom";

  @Nonnull
  public IMicroElement convertToMicroElement (@Nonnull final Object aObject, @Nullable final String sNamespaceURI, @Nonnull final String sTagName)
  {
    final IRole aRole = (IRole) aObject;
    final IMicroElement eRole = new MicroElement (sNamespaceURI, sTagName);
    eRole.setAttribute (ATTR_ID, aRole.getID ());
    eRole.setAttribute (ATTR_NAME, aRole.getName ());
    if (StringHelper.hasText (aRole.getDescription ()))
      eRole.appendElement (sNamespaceURI, ELEMENT_DESCRIPTION).appendText (aRole.getDescription ());
    for (final Map.Entry <String, String> aEntry : CollectionHelper.getSortedByKey (aRole.getAllAttributes ()).entrySet ())
    {
      final IMicroElement eCustom = eRole.appendElement (ELEMENT_CUSTOM);
      eCustom.setAttribute (ATTR_ID, aEntry.getKey ());
      eCustom.appendText (aEntry.getValue ());
    }
    return eRole;
  }

  @Nonnull
  public Role convertToNative (@Nonnull final IMicroElement eRole)
  {
    final String sID = eRole.getAttributeValue (ATTR_ID);
    final String sName = eRole.getAttributeValue (ATTR_NAME);
    final String sDescription = MicroHelper.getChildTextContentTrimmed (eRole, ELEMENT_DESCRIPTION);
    final Map <String, String> aCustomAttrs = new LinkedHashMap <String, String> ();
    for (final IMicroElement eCustom : eRole.getAllChildElements (ELEMENT_CUSTOM))
      aCustomAttrs.put (eCustom.getAttributeValue (ATTR_ID), eCustom.getTextContent ());
    return new Role (sID, sName, sDescription, aCustomAttrs);
  }
}
