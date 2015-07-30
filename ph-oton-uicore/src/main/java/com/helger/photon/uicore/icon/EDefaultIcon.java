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
package com.helger.photon.uicore.icon;

import javax.annotation.Nullable;

import com.helger.html.hcapi.IHCNode;

/**
 * Contains all default icon types
 *
 * @author Philip Helger
 */
public enum EDefaultIcon implements IIcon
{
  ADD,
  BACK,
  BACK_TO_LIST,
  CANCEL,
  COPY,
  DELETE,
  DOWN,
  EDIT,
  HELP,
  INFO,
  KEY,
  MAGNIFIER,
  MINUS,
  NEW,
  NEXT,
  NO,
  PLUS,
  REFRESH,
  SAVE,
  SAVE_ALL,
  SAVE_AS,
  SAVE_CLOSE,
  UP,
  YES;

  @Nullable
  public IIcon getIcon ()
  {
    return DefaultIcons.get (this);
  }

  /**
   * Shortcut for <code>getIcon ().getAsNode ()</code>
   *
   * @return The icon HC node or <code>null</code> if no such default icon is
   *         set.
   */
  @Nullable
  public IHCNode getAsNode ()
  {
    final IIcon aIcon = getIcon ();
    return aIcon == null ? null : aIcon.getAsNode ();
  }

  @Nullable
  public String getCSSClass ()
  {
    final IIcon aIcon = getIcon ();
    return aIcon == null ? null : aIcon.getCSSClass ();
  }
}
