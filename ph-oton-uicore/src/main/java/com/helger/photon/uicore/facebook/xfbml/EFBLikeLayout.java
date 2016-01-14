/**
 * Copyright (C) 2014-2016 Philip Helger (www.helger.com)
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
package com.helger.photon.uicore.facebook.xfbml;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.helger.commons.annotation.Nonempty;
import com.helger.commons.id.IHasID;
import com.helger.commons.lang.EnumHelper;

public enum EFBLikeLayout implements IHasID <String>
{
  /**
   * displays social text to the right of the button and friends' profile photos
   * below. Minimum width: 225 pixels. Minimum increases by 40px if action is
   * 'recommend' by and increases by 60px if send is 'true'. Default width: 450
   * pixels. Height: 35 pixels (without photos) or 80 pixels (with photos).
   */
  STANDARD ("standard"),

  /**
   * displays the total number of likes to the right of the button. Minimum
   * width: 90 pixels. Default width: 90 pixels. Height: 20 pixels.
   */
  BUTTON_COUNT ("button_count"),

  /**
   * displays the total number of likes above the button. Minimum width: 55
   * pixels. Default width: 55 pixels. Height: 65 pixels.
   */
  BOX_COUNT ("box_count");

  /** Default like layout */
  public static final EFBLikeLayout DEFAULT = STANDARD;

  private final String m_sID;

  private EFBLikeLayout (@Nonnull @Nonempty final String sID)
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
  public static EFBLikeLayout getFromID (@Nullable final String sID)
  {
    return EnumHelper.getFromIDOrNull (EFBLikeLayout.class, sID);
  }
}
