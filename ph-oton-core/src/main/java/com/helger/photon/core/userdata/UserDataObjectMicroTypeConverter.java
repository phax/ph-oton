/**
 * Copyright (C) 2014-2018 Philip Helger (www.helger.com)
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
package com.helger.photon.core.userdata;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.helger.commons.annotation.Nonempty;
import com.helger.xml.microdom.IMicroElement;
import com.helger.xml.microdom.IMicroQName;
import com.helger.xml.microdom.MicroElement;
import com.helger.xml.microdom.MicroQName;
import com.helger.xml.microdom.convert.IMicroTypeConverter;

/**
 * XML converter for {@link UserDataObject} objects.
 * 
 * @author Philip Helger
 */
public final class UserDataObjectMicroTypeConverter implements IMicroTypeConverter <UserDataObject>
{
  private static final IMicroQName ATTR_PATH = new MicroQName ("path");

  @Nonnull
  public IMicroElement convertToMicroElement (@Nonnull final UserDataObject aValue,
                                              @Nullable final String sNamespaceURI,
                                              @Nonnull @Nonempty final String sTagName)
  {
    final IMicroElement aElement = new MicroElement (sNamespaceURI, sTagName);
    aElement.setAttribute (ATTR_PATH, aValue.getPath ());
    return aElement;
  }

  @Nonnull
  public UserDataObject convertToNative (@Nonnull final IMicroElement aElement)
  {
    final String sPath = aElement.getAttributeValue (ATTR_PATH);

    return new UserDataObject (sPath);
  }
}
