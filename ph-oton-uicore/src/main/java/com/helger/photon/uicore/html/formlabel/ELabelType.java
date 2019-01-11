/**
 * Copyright (C) 2014-2019 Philip Helger (www.helger.com)
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
package com.helger.photon.uicore.html.formlabel;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.helger.commons.annotation.Nonempty;
import com.helger.commons.id.IHasID;
import com.helger.commons.lang.EnumHelper;

/**
 * Represents the possible label states.
 *
 * @author Philip Helger
 */
public enum ELabelType implements IHasID <String>
{
  OPTIONAL ("optional"),
  MANDATORY ("mandatory"),
  ALTERNATIVE ("alternative"),
  NONE ("none");

  public static final ELabelType DEFAULT = OPTIONAL;

  private final String m_sID;

  private ELabelType (@Nonnull @Nonempty final String sID)
  {
    m_sID = sID;
  }

  @Nonnull
  @Nonempty
  public String getID ()
  {
    return m_sID;
  }

  public boolean isOptional ()
  {
    return this == OPTIONAL;
  }

  public boolean isMandatory ()
  {
    return this == MANDATORY;
  }

  public boolean isAlternative ()
  {
    return this == ALTERNATIVE;
  }

  @Nullable
  public static ELabelType getFromIDOrNull (@Nullable final String sID)
  {
    return EnumHelper.getFromIDOrNull (ELabelType.class, sID);
  }
}
