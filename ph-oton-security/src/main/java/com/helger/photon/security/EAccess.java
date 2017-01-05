/**
 * Copyright (C) 2014-2017 Philip Helger (www.helger.com)
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
package com.helger.photon.security;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.helger.commons.annotation.Nonempty;
import com.helger.commons.id.IHasID;
import com.helger.commons.lang.EnumHelper;

/**
 * Generic access enum
 *
 * @author Philip Helger
 */
public enum EAccess implements IHasID <String>
{
  GRANTED ("grant", true),
  DENIED ("deny", false),
  INHERITED ("inherit", CSecurity.NO_RIGHT_SPECIFIED_MEANS_HAS_ACCESS);

  private final String m_sID;
  private final boolean m_bHasAccess;

  private EAccess (@Nonnull @Nonempty final String sID, final boolean bHasAccess)
  {
    m_sID = sID;
    m_bHasAccess = bHasAccess;
  }

  @Nonnull
  @Nonempty
  public String getID ()
  {
    return m_sID;
  }

  public boolean hasAccess ()
  {
    return m_bHasAccess;
  }

  public boolean isInherited ()
  {
    return this == INHERITED;
  }

  @Nullable
  public static EAccess getFromIDOrNull (@Nullable final String sID)
  {
    return EnumHelper.getFromIDOrNull (EAccess.class, sID);
  }
}
