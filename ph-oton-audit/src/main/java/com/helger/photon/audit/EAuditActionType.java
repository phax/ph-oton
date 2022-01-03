/*
 * Copyright (C) 2014-2022 Philip Helger (www.helger.com)
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
package com.helger.photon.audit;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.helger.commons.annotation.Nonempty;
import com.helger.commons.id.IHasID;
import com.helger.commons.lang.EnumHelper;

/**
 * Determines the different types of actions of an audit item.
 *
 * @author Philip Helger
 */
public enum EAuditActionType implements IHasID <String>
{
  CREATE ("create"),
  MODIFY ("modify"),
  DELETE ("delete"),
  UNDELETE ("undelete"),
  EXECUTE ("execute");

  /**
   * The maximum ID length - that needs to be maintained for database
   * compatibility.
   */
  public static final int MAX_ID_LENGTH = 10;

  private final String m_sID;

  EAuditActionType (@Nonnull @Nonempty final String sID)
  {
    m_sID = sID;
  }

  @Nonnull
  @Nonempty
  public String getID ()
  {
    return m_sID;
  }

  @Nullable
  public static EAuditActionType getFromIDOrNull (@Nullable final String sID)
  {
    return EnumHelper.getFromIDOrNull (EAuditActionType.class, sID);
  }
}
