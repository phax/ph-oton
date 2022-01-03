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
package com.helger.photon.uicore.page;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.helger.commons.annotation.Nonempty;
import com.helger.commons.id.IHasID;
import com.helger.commons.lang.EnumHelper;
import com.helger.photon.uicore.css.CPageParam;

/**
 * Determines the different form actions to be handled in
 * {@link AbstractWebPageSimpleForm}.
 *
 * @author Philip Helger
 */
public enum EWebPageSimpleFormAction implements IHasID <String>
{
  VIEW (CPageParam.ACTION_VIEW),
  EDIT (CPageParam.ACTION_EDIT),
  CUSTOM ("$custom$");

  public static final EWebPageSimpleFormAction DEFAULT = VIEW;

  private final String m_sID;

  EWebPageSimpleFormAction (@Nonnull @Nonempty final String sID)
  {
    m_sID = sID;
  }

  @Nonnull
  @Nonempty
  public String getID ()
  {
    return m_sID;
  }

  /**
   * @return <code>true</code> if this is {@link #VIEW}, <code>false</code>
   *         otherwise.
   */
  public boolean isView ()
  {
    return this == VIEW;
  }

  /**
   * @return <code>true</code> if this is {@link #EDIT}, <code>false</code>
   *         otherwise.
   */
  public boolean isEdit ()
  {
    return this == EDIT;
  }

  /**
   * @return <code>true</code> if this is {@link #CUSTOM}, <code>false</code>
   *         otherwise.
   */
  public boolean isCustom ()
  {
    return this == CUSTOM;
  }

  /**
   * @return <code>true</code> if this action only reads an existing format,
   *         <code>false</code> if the action needs write access.
   */
  public boolean isReadonly ()
  {
    return this == VIEW;
  }

  /**
   * @return <code>true</code> if this action modifies an existing object,
   *         <code>false</code> if either creates a new object or just reads an
   *         existing object.
   */
  public boolean isModifying ()
  {
    return this == EDIT;
  }

  @Nullable
  public static EWebPageSimpleFormAction getFromIDOrNull (@Nullable final String sID)
  {
    return EnumHelper.getFromIDOrNull (EWebPageSimpleFormAction.class, sID);
  }

  @Nullable
  public static EWebPageSimpleFormAction getFromIDOrDefault (@Nullable final String sID, @Nullable final EWebPageSimpleFormAction eDefault)
  {
    return EnumHelper.getFromIDOrDefault (EWebPageSimpleFormAction.class, sID, eDefault);
  }
}
