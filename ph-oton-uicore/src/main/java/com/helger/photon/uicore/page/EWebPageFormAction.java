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
 * {@link AbstractWebPageForm}.
 *
 * @author Philip Helger
 */
public enum EWebPageFormAction implements IHasID <String>
{
  SHOW_LIST ("$none$"),
  VIEW (CPageParam.ACTION_VIEW),
  CREATE (CPageParam.ACTION_CREATE),
  EDIT (CPageParam.ACTION_EDIT),
  COPY (CPageParam.ACTION_COPY),
  DELETE (CPageParam.ACTION_DELETE),
  UNDELETE (CPageParam.ACTION_UNDELETE),
  CUSTOM ("$custom$");

  public static final EWebPageFormAction DEFAULT = SHOW_LIST;

  private final String m_sID;

  EWebPageFormAction (@Nonnull @Nonempty final String sID)
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
   * @return <code>true</code> if this is {@link #SHOW_LIST}, <code>false</code>
   *         otherwise.
   */
  public boolean isShowList ()
  {
    return this == EWebPageFormAction.SHOW_LIST;
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
   * @return <code>true</code> if this is {@link #CREATE}, <code>false</code>
   *         otherwise.
   */
  public boolean isCreate ()
  {
    return this == CREATE;
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
   * @return <code>true</code> if this is {@link #COPY}, <code>false</code>
   *         otherwise.
   */
  public boolean isCopy ()
  {
    return this == COPY;
  }

  /**
   * @return <code>true</code> if this is {@link #DELETE}, <code>false</code>
   *         otherwise.
   */
  public boolean isDelete ()
  {
    return this == DELETE;
  }

  /**
   * @return <code>true</code> if this is {@link #UNDELETE}, <code>false</code>
   *         otherwise.
   */
  public boolean isUndelete ()
  {
    return this == UNDELETE;
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
   * @return <code>true</code> if this action creates a new object (CREATE,
   *         COPY), <code>false</code> otherwise.
   */
  public boolean isCreating ()
  {
    return this == CREATE || this == COPY;
  }

  /**
   * @return <code>true</code> if this action modifies an existing object (EDIT,
   *         DELETE, UNDELETE), <code>false</code> if either creates a new
   *         object or just reads an existing object.
   */
  public boolean isModifying ()
  {
    return this == EDIT || this == DELETE || this == UNDELETE;
  }

  /**
   * @return <code>true</code> if this action only reads an existing format
   *         (SHOW_LIST, VIEW), <code>false</code> if the action needs write
   *         access.
   */
  public boolean isReadonly ()
  {
    return this == SHOW_LIST || this == VIEW;
  }

  /**
   * @return <code>true</code> if this action needs write access (for creating
   *         or modifying; CREATE, COPY, EDIT, DELETE, UNDELETE),
   *         <code>false</code> if the action is read-only.
   * @see #isCreating()
   * @see #isModifying()
   */
  public boolean isWriting ()
  {
    return isCreating () || isModifying ();
  }

  @Nullable
  public static EWebPageFormAction getFromIDOrNull (@Nullable final String sID)
  {
    return EnumHelper.getFromIDOrNull (EWebPageFormAction.class, sID);
  }

  @Nullable
  public static EWebPageFormAction getFromIDOrDefault (@Nullable final String sID, @Nullable final EWebPageFormAction eDefault)
  {
    return EnumHelper.getFromIDOrDefault (EWebPageFormAction.class, sID, eDefault);
  }
}
