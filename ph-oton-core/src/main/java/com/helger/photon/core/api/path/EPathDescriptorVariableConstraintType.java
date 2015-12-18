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
package com.helger.photon.core.api.path;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.helger.commons.annotation.Nonempty;
import com.helger.commons.id.IHasID;
import com.helger.commons.lang.EnumHelper;

/**
 * This enum contains all the variable constraint types that can be used in API
 * paths.
 * 
 * @author Philip Helger
 */
public enum EPathDescriptorVariableConstraintType implements IHasID <String>
{
  REGEX ("regex", true);

  private final String m_sID;
  private final boolean m_bRequiresValue;

  private EPathDescriptorVariableConstraintType (@Nonnull @Nonempty final String sID, final boolean bRequiresValue)
  {
    m_sID = sID;
    m_bRequiresValue = bRequiresValue;
  }

  @Nonnull
  @Nonempty
  public String getID ()
  {
    return m_sID;
  }

  public boolean isRequiresValue ()
  {
    return m_bRequiresValue;
  }

  @Nullable
  public static EPathDescriptorVariableConstraintType getFromIDOrNull (@Nullable final String sID)
  {
    return EnumHelper.getFromIDOrNull (EPathDescriptorVariableConstraintType.class, sID);
  }
}
