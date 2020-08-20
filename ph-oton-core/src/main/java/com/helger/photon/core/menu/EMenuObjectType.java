/**
 * Copyright (C) 2014-2020 Philip Helger (www.helger.com)
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
package com.helger.photon.core.menu;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.helger.commons.annotation.Nonempty;
import com.helger.commons.id.IHasID;
import com.helger.commons.lang.EnumHelper;

/**
 * Enumeration with the different type of menu objects.
 *
 * @author Philip Helger
 */
public enum EMenuObjectType implements IHasID <String>
{
  /** Separator */
  SEPARATOR ("separator"),
  /** Menu item pointing to an internal page */
  PAGE ("page"),
  /** Menu item pointing to an external page */
  EXTERNAL ("external"),
  /** An internal redirect to an existing page */
  REDIRECT_TO_PAGE ("redirect-to-page");

  private final String m_sID;

  EMenuObjectType (@Nonnull @Nonempty final String sID)
  {
    m_sID = sID;
  }

  @Nonnull
  @Nonempty
  public String getID ()
  {
    return m_sID;
  }

  public boolean isSeparator ()
  {
    return this == SEPARATOR;
  }

  public boolean isNotSeparator ()
  {
    return this != SEPARATOR;
  }

  public boolean isPage ()
  {
    return this == PAGE;
  }

  public boolean isExternal ()
  {
    return this == EXTERNAL;
  }

  public boolean isRedirect ()
  {
    return this == EMenuObjectType.REDIRECT_TO_PAGE;
  }

  @Nullable
  public static EMenuObjectType getFromIDOrNull (@Nullable final String sID)
  {
    return EnumHelper.getFromIDOrNull (EMenuObjectType.class, sID);
  }
}
