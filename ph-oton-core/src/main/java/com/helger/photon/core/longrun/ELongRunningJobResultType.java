/*
 * Copyright (C) 2014-2026 Philip Helger (www.helger.com)
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
package com.helger.photon.core.longrun;

import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.Nullable;

import com.helger.annotation.Nonempty;
import com.helger.base.id.IHasID;
import com.helger.base.lang.EnumHelper;

/**
 * Defines the result type of a long running job.
 *
 * @author Philip Helger
 */
public enum ELongRunningJobResultType implements IHasID <String>
{
  FILE ("file"),
  XML ("xml"),
  TEXT ("text"),
  LINK ("link"),
  JSON ("json");

  private final String m_sID;

  ELongRunningJobResultType (@NonNull @Nonempty final String sID)
  {
    m_sID = sID;
  }

  @NonNull
  @Nonempty
  public String getID ()
  {
    return m_sID;
  }

  @Nullable
  public static ELongRunningJobResultType getFromIDOrNull (@Nullable final String sID)
  {
    // Old ID
    if ("html".equals (sID))
      return ELongRunningJobResultType.XML;
    return EnumHelper.getFromIDOrNull (ELongRunningJobResultType.class, sID);
  }
}
