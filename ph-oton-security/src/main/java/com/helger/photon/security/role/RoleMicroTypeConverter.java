/*
 * Copyright (C) 2014-2025 Philip Helger (www.helger.com)
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

import com.helger.base.string.StringHelper;
import com.helger.photon.security.object.AbstractBusinessObjectMicroTypeConverter;
import com.helger.xml.microdom.IMicroElement;
import com.helger.xml.microdom.IMicroQName;
import com.helger.xml.microdom.MicroElement;
import com.helger.xml.microdom.MicroQName;
import com.helger.xml.microdom.util.MicroHelper;

import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;

/**
 * Convert {@link Role} objects to {@link IMicroElement} and vice versa.
 *
 * @author Philip Helger
 */
public final class RoleMicroTypeConverter extends AbstractBusinessObjectMicroTypeConverter <Role>
{
  private static final IMicroQName ATTR_NAME = new MicroQName ("name");
  private static final String ELEMENT_DESCRIPTION = "description";

  @Nonnull
  public IMicroElement convertToMicroElement (@Nonnull final Role aValue,
                                              @Nullable final String sNamespaceURI,
                                              @Nonnull final String sTagName)
  {
    final IMicroElement aElement = new MicroElement (sNamespaceURI, sTagName);
    setObjectFields (aValue, aElement);
    aElement.setAttribute (ATTR_NAME, aValue.getName ());
    if (StringHelper.isNotEmpty (aValue.getDescription ()))
      aElement.addElementNS (sNamespaceURI, ELEMENT_DESCRIPTION).addText (aValue.getDescription ());
    return aElement;
  }

  @Nonnull
  public Role convertToNative (@Nonnull final IMicroElement aElement)
  {
    final String sName = aElement.getAttributeValue (ATTR_NAME);
    final String sDescription = MicroHelper.getChildTextContentTrimmed (aElement, ELEMENT_DESCRIPTION);

    return new Role (getStubObject (aElement), sName, sDescription);
  }
}
