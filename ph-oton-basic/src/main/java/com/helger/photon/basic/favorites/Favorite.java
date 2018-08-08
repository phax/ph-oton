/**
 * Copyright (C) 2014-2018 Philip Helger (www.helger.com)
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
package com.helger.photon.basic.favorites;

import java.util.Map;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.concurrent.NotThreadSafe;

import com.helger.commons.ValueEnforcer;
import com.helger.commons.annotation.Nonempty;
import com.helger.commons.annotation.ReturnsMutableCopy;
import com.helger.commons.collection.impl.CommonsHashMap;
import com.helger.commons.collection.impl.ICommonsMap;
import com.helger.commons.equals.EqualsHelper;
import com.helger.commons.id.factory.GlobalIDFactory;
import com.helger.commons.state.EChange;
import com.helger.commons.string.ToStringGenerator;
import com.helger.commons.type.ObjectType;

/**
 * Default implementation of favourites for bookmarks.
 */
@NotThreadSafe
public class Favorite implements IFavorite
{
  public static final ObjectType OT = new ObjectType ("favourite");

  private final String m_sID;
  private final String m_sUserID;
  private final String m_sApplicationID;
  private final String m_sMenuItemID;
  private String m_sDisplayName;
  private final ICommonsMap <String, String> m_aAdditionalParams;

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
    m_aAdditionalParams = new CommonsHashMap <> (aAdditionalParams);
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
  public ICommonsMap <String, String> getAllAdditionalParams ()
  {
    return m_aAdditionalParams.getClone ();
  }

  public boolean hasSameContent (@Nullable final String sAppID,
                                 @Nullable final String sMenuItemID,
                                 @Nullable final ICommonsMap <String, String> aAdditionalParams)
  {
    final ICommonsMap <String, String> aRealAdditionalParams = aAdditionalParams != null ? aAdditionalParams
                                                                                         : new CommonsHashMap <> ();
    return m_sApplicationID.equals (sAppID) &&
           m_sMenuItemID.equals (sMenuItemID) &&
           EqualsHelper.equalsMap (m_aAdditionalParams, aRealAdditionalParams);
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
                                       .getToString ();
  }
}
