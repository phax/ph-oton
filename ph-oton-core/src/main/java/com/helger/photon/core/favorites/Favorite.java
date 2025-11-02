/*
 * Copyright (C) 2014-2025 Philip Helger (www.helger.com)
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
package com.helger.photon.core.favorites;

import java.util.Map;

import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.Nullable;

import com.helger.annotation.Nonempty;
import com.helger.annotation.concurrent.NotThreadSafe;
import com.helger.annotation.style.ReturnsMutableCopy;
import com.helger.base.enforce.ValueEnforcer;
import com.helger.base.id.factory.GlobalIDFactory;
import com.helger.base.state.EChange;
import com.helger.base.tostring.ToStringGenerator;
import com.helger.base.type.ObjectType;
import com.helger.collection.commons.CommonsHashMap;
import com.helger.collection.commons.ICommonsMap;

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
  private String m_sMenuItemID;
  private String m_sDisplayName;
  private ICommonsMap <String, String> m_aAdditionalParams;

  public Favorite (@NonNull @Nonempty final String sUserID,
                   @NonNull @Nonempty final String sApplicationID,
                   @NonNull @Nonempty final String sMenuItemID,
                   @NonNull @Nonempty final String sDisplayName,
                   @Nullable final Map <String, String> aAdditionalParams)
  {
    this (GlobalIDFactory.getNewPersistentStringID (),
          sUserID,
          sApplicationID,
          sMenuItemID,
          sDisplayName,
          aAdditionalParams);
  }

  Favorite (@NonNull @Nonempty final String sID,
            @NonNull @Nonempty final String sUserID,
            @NonNull @Nonempty final String sApplicationID,
            @NonNull @Nonempty final String sMenuItemID,
            @NonNull @Nonempty final String sDisplayName,
            @Nullable final Map <String, String> aAdditionalParams)
  {
    m_sID = ValueEnforcer.notEmpty (sID, "ID");
    m_sUserID = ValueEnforcer.notEmpty (sUserID, "User ID");
    m_sApplicationID = ValueEnforcer.notEmpty (sApplicationID, "application ID");
    setMenuItemID (sMenuItemID);
    setDisplayName (sDisplayName);
    setAdditionalParams (aAdditionalParams);
  }

  @NonNull
  @Nonempty
  public String getID ()
  {
    return m_sID;
  }

  @NonNull
  @Nonempty
  public String getUserID ()
  {
    return m_sUserID;
  }

  @NonNull
  @Nonempty
  public String getApplicationID ()
  {
    return m_sApplicationID;
  }

  @NonNull
  @Nonempty
  public String getMenuItemID ()
  {
    return m_sMenuItemID;
  }

  @NonNull
  public final EChange setMenuItemID (@NonNull @Nonempty final String sMenuItemID)
  {
    ValueEnforcer.notEmpty (sMenuItemID, "menu item ID");

    if (sMenuItemID.equals (m_sMenuItemID))
      return EChange.UNCHANGED;

    m_sMenuItemID = sMenuItemID;
    return EChange.CHANGED;
  }

  @NonNull
  @Nonempty
  public String getDisplayName ()
  {
    return m_sDisplayName;
  }

  @NonNull
  public final EChange setDisplayName (@NonNull @Nonempty final String sDisplayName)
  {
    ValueEnforcer.notEmpty (sDisplayName, "display name");

    if (sDisplayName.equals (m_sDisplayName))
      return EChange.UNCHANGED;

    m_sDisplayName = sDisplayName;
    return EChange.CHANGED;
  }

  @NonNull
  @ReturnsMutableCopy
  public ICommonsMap <String, String> getAllAdditionalParams ()
  {
    return m_aAdditionalParams.getClone ();
  }

  @NonNull
  public final EChange setAdditionalParams (@Nullable final Map <String, String> aAdditionalParams)
  {
    // Ensure same type
    final ICommonsMap <String, String> aRealAdditionalParams = new CommonsHashMap <> (aAdditionalParams);
    if (aRealAdditionalParams.equals (m_aAdditionalParams))
      return EChange.UNCHANGED;

    m_aAdditionalParams = aRealAdditionalParams;
    return EChange.CHANGED;
  }

  public boolean hasSameContent (@Nullable final String sAppID,
                                 @Nullable final String sMenuItemID,
                                 @Nullable final Map <String, String> aAdditionalParams)
  {
    // Ensure same type
    final ICommonsMap <String, String> aRealAdditionalParams = new CommonsHashMap <> (aAdditionalParams);
    return m_sApplicationID.equals (sAppID) &&
           m_sMenuItemID.equals (sMenuItemID) &&
           m_aAdditionalParams.equals (aRealAdditionalParams);
  }

  @Override
  @NonNull
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
