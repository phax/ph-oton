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

package com.helger.appbasics.favorites;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.concurrent.NotThreadSafe;

import com.helger.commons.ValueEnforcer;
import com.helger.commons.annotations.Nonempty;
import com.helger.commons.annotations.ReturnsMutableCopy;
import com.helger.commons.collections.CollectionHelper;
import com.helger.commons.idfactory.GlobalIDFactory;
import com.helger.commons.state.EChange;
import com.helger.commons.string.ToStringGenerator;
import com.helger.commons.type.ObjectType;

/**
 * Default implementation of favourites for bookmarks.
 */
@NotThreadSafe
public class Favorite implements IFavorite
{
  public static final ObjectType OT_FAVOURITE = new ObjectType ("favourite");

  private final String m_sID;
  private final String m_sUserID;
  private final String m_sApplicationID;
  private final String m_sMenuItemID;
  private String m_sDisplayName;
  private final Map <String, String> m_aAdditionalParams;

  public Favorite (@Nonnull @Nonempty final String sUserID,
                   @Nonnull @Nonempty final String sApplicationID,
                   @Nonnull @Nonempty final String sMenuItemID,
                   @Nonnull @Nonempty final String sDisplayName,
                   @Nullable final Map <String, String> aAdditionalParams)
  {
    this (GlobalIDFactory.getNewPersistentStringID (),
          sUserID,
          sApplicationID,
          sMenuItemID,
          sDisplayName,
          aAdditionalParams);
  }

  Favorite (@Nonnull @Nonempty final String sID,
            @Nonnull @Nonempty final String sUserID,
            @Nonnull @Nonempty final String sApplicationID,
            @Nonnull @Nonempty final String sMenuItemID,
            @Nonnull @Nonempty final String sDisplayName,
            @Nullable final Map <String, String> aAdditionalParams)
  {
    m_sID = ValueEnforcer.notEmpty (sID, "ID");
    m_sUserID = ValueEnforcer.notEmpty (sUserID, "User ID");
    m_sMenuItemID = ValueEnforcer.notEmpty (sMenuItemID, "menu item ID");
    m_sApplicationID = ValueEnforcer.notEmpty (sApplicationID, "application ID");
    setDisplayName (sDisplayName);
    m_aAdditionalParams = CollectionHelper.newMap (aAdditionalParams);
  }

  @Nonnull
  @Nonempty
  public String getID ()
  {
    return m_sID;
  }

  @Nonnull
  @Nonempty
  public String getUserID ()
  {
    return m_sUserID;
  }

  @Nonnull
  @Nonempty
  public String getApplicationID ()
  {
    return m_sApplicationID;
  }

  @Nonnull
  @Nonempty
  public String getMenuItemID ()
  {
    return m_sMenuItemID;
  }

  @Nonnull
  @Nonempty
  public String getDisplayName ()
  {
    return m_sDisplayName;
  }

  @Nonnull
  public EChange setDisplayName (@Nonnull @Nonempty final String sDisplayName)
  {
    ValueEnforcer.notEmpty (sDisplayName, "display name");

    if (sDisplayName.equals (m_sDisplayName))
      return EChange.UNCHANGED;

    m_sDisplayName = sDisplayName;
    return EChange.CHANGED;
  }

  @Nonnull
  @ReturnsMutableCopy
  public Map <String, String> getAdditionalParams ()
  {
    return CollectionHelper.newMap (m_aAdditionalParams);
  }

  public boolean hasSameContent (@Nullable final String sAppID,
                                 @Nullable final String sMenuItemID,
                                 @Nullable final Map <String, String> aAdditionalParams)
  {
    final Map <String, String> aRealAdditionalParams = aAdditionalParams == null ? new HashMap <String, String> ()
                                                                                : aAdditionalParams;
    return m_sApplicationID.equals (sAppID) &&
           m_sMenuItemID.equals (sMenuItemID) &&
           m_aAdditionalParams.equals (aRealAdditionalParams);
  }

  @Override
  @Nonnull
  public String toString ()
  {
    return new ToStringGenerator (this).append ("id", m_sID)
                                       .append ("userID", m_sUserID)
                                       .append ("applicationID", m_sApplicationID)
                                       .append ("menuItemID", m_sMenuItemID)
                                       .append ("displayName", m_sDisplayName)
                                       .append ("additionalParams", m_aAdditionalParams)
                                       .toString ();
  }
}
