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
import java.util.List;
import java.util.Map;

import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.concurrent.ThreadSafe;

import com.helger.appbasics.app.dao.impl.AbstractWALDAO;
import com.helger.appbasics.app.dao.impl.DAOException;
import com.helger.appbasics.app.dao.impl.EDAOActionType;
import com.helger.appbasics.security.audit.AuditUtils;
import com.helger.commons.IHasSize;
import com.helger.commons.ValueEnforcer;
import com.helger.commons.annotations.ELockType;
import com.helger.commons.annotations.MustBeLocked;
import com.helger.commons.annotations.Nonempty;
import com.helger.commons.annotations.ReturnsMutableCopy;
import com.helger.commons.collections.CollectionHelper;
import com.helger.commons.collections.multimap.IMultiMapListBased;
import com.helger.commons.collections.multimap.MultiHashMapArrayListBased;
import com.helger.commons.microdom.IMicroDocument;
import com.helger.commons.microdom.IMicroElement;
import com.helger.commons.microdom.convert.MicroTypeConverter;
import com.helger.commons.microdom.impl.MicroDocument;
import com.helger.commons.state.EChange;
import com.helger.commons.string.StringHelper;
import com.helger.commons.string.ToStringGenerator;
import com.helger.webbasics.smtp.NamedSMTPSettings;

/**
 * This class manages {@link Favorite} objects.
 *
 * @author Philip Helger
 */
@ThreadSafe
public class FavoriteManager extends AbstractWALDAO <Favorite> implements IHasSize
{
  private static final String ELEMENT_FAVORITES = "favorites";
  private static final String ELEMENT_FAVORITE = "favorite";

  private final IMultiMapListBased <String, Favorite> m_aMap = new MultiHashMapArrayListBased <String, Favorite> ();

  public FavoriteManager (@Nonnull @Nonempty final String sFilename) throws DAOException
  {
    super (Favorite.class, sFilename);
    initialRead ();
  }

  @Override
  protected void onRecoveryCreate (@Nonnull final Favorite aElement)
  {
    _addItem (aElement);
  }

  @Override
  protected void onRecoveryUpdate (@Nonnull final Favorite aElement)
  {
    _addItem (aElement);
  }

  @Override
  protected void onRecoveryDelete (@Nonnull final Favorite aElement)
  {
    m_aMap.removeSingle (aElement.getUserID (), aElement);
  }

  @Override
  @Nonnull
  protected EChange onRead (@Nonnull final IMicroDocument aDoc)
  {
    for (final IMicroElement eFavorite : aDoc.getDocumentElement ().getAllChildElements (ELEMENT_FAVORITE))
      _addItem (MicroTypeConverter.convertToNative (eFavorite, Favorite.class));
    return EChange.UNCHANGED;
  }

  @Override
  @Nonnull
  protected IMicroDocument createWriteData ()
  {
    final IMicroDocument aDoc = new MicroDocument ();
    final IMicroElement eRoot = aDoc.appendElement (ELEMENT_FAVORITES);
    for (final List <Favorite> aFavoritesOfUser : CollectionHelper.getSortedByKey (m_aMap).values ())
      for (final Favorite aFavorite : aFavoritesOfUser)
        eRoot.appendChild (MicroTypeConverter.convertToMicroElement (aFavorite, ELEMENT_FAVORITE));
    return aDoc;
  }

  public void reload () throws DAOException
  {
    m_aRWLock.writeLock ().lock ();
    try
    {
      m_aMap.clear ();
      initialRead ();
    }
    finally
    {
      m_aRWLock.writeLock ().unlock ();
    }
  }

  @MustBeLocked (ELockType.WRITE)
  private void _addItem (@Nonnull final Favorite aFavorite)
  {
    ValueEnforcer.notNull (aFavorite, "Favorite");
    m_aMap.putSingle (aFavorite.getUserID (), aFavorite);
  }

  @Nonnegative
  public int size ()
  {
    m_aRWLock.readLock ().lock ();
    try
    {
      return m_aMap.size ();
    }
    finally
    {
      m_aRWLock.readLock ().unlock ();
    }
  }

  public boolean isEmpty ()
  {
    m_aRWLock.readLock ().lock ();
    try
    {
      return m_aMap.isEmpty ();
    }
    finally
    {
      m_aRWLock.readLock ().unlock ();
    }
  }

  /**
   * @param sUserID
   *        The ID of the user.
   * @return A copy of all contained favorite as map from ID to object. Never
   *         <code>null</code> but maybe empty.
   */
  @Nonnull
  @ReturnsMutableCopy
  public List <? extends IFavorite> getAllFavoritesOfUser (@Nullable final String sUserID)
  {
    m_aRWLock.readLock ().lock ();
    try
    {
      return CollectionHelper.newList (m_aMap.get (sUserID));
    }
    finally
    {
      m_aRWLock.readLock ().unlock ();
    }
  }

  /**
   * Check if the specified user ID is contained or not.
   *
   * @param sUserID
   *        The UserID to check. May be <code>null</code>.
   * @return <code>true</code> if the passed ID is contained
   */
  public boolean containsFavoritesOfUser (@Nullable final String sUserID)
  {
    if (StringHelper.hasNoText (sUserID))
      return false;

    List <Favorite> aFavorites = null;
    m_aRWLock.readLock ().lock ();
    try
    {
      aFavorites = m_aMap.get (sUserID);
    }
    finally
    {
      m_aRWLock.readLock ().unlock ();
    }

    if (CollectionHelper.isNotEmpty (aFavorites))
      return CollectionHelper.isNotEmpty (aFavorites);

    return false;
  }

  /**
   * Get a list favorites of the specified user ID
   *
   * @param sUserID
   *        The ID of the user.
   * @param sApplicationID
   *        The application ID to compare to.
   * @param sMenuItemID
   *        Menu item ID
   * @param aAdditionalParams
   *        Additional params. May be <code>null</code>.
   * @return <code>null</code> if no such settings are contained.
   */
  public boolean isFavorite (@Nullable final String sUserID,
                             @Nullable final String sApplicationID,
                             @Nullable final String sMenuItemID,
                             @Nullable final Map <String, String> aAdditionalParams)
  {
    if (StringHelper.hasNoText (sUserID) ||
        StringHelper.hasNoText (sApplicationID) ||
        StringHelper.hasNoText (sMenuItemID))
      return false;

    final Map <String, String> aRealAdditionalParams = aAdditionalParams == null ? new HashMap <String, String> ()
                                                                                : aAdditionalParams;
    for (final IFavorite aFavorite : getAllFavoritesOfUser (sUserID))
      if (aFavorite.hasSameContent (sApplicationID, sMenuItemID, aRealAdditionalParams))
        return true;
    return false;
  }

  /**
   * Get the favorite of the specified user ID
   *
   * @param sUserID
   *        The ID of the user.
   * @param sApplicationID
   *        The application ID to compare to.
   * @param sMenuItemID
   *        Menu item ID
   * @param aAdditionalParams
   *        Additional params. May be <code>null</code>.
   * @return <code>null</code> if no such settings are contained.
   */
  @Nullable
  public IFavorite getFavorite (@Nullable final String sUserID,
                                @Nullable final String sApplicationID,
                                @Nullable final String sMenuItemID,
                                @Nullable final Map <String, String> aAdditionalParams)
  {
    if (StringHelper.hasText (sUserID) && StringHelper.hasText (sApplicationID) && StringHelper.hasText (sMenuItemID))
    {

      final Map <String, String> aRealAdditionalParams = aAdditionalParams == null ? new HashMap <String, String> ()
                                                                                  : aAdditionalParams;
      for (final IFavorite aFavorite : getAllFavoritesOfUser (sUserID))
        if (aFavorite.hasSameContent (sApplicationID, sMenuItemID, aRealAdditionalParams))
          return aFavorite;
    }
    return null;
  }

  /**
   * Create a new favorite object.
   *
   * @param sUserID
   *        The ID of the user. May neither be <code>null</code> nor empty.
   * @param sApplicationID
   *        The application ID. May neither be <code>null</code> nor empty.
   * @param sMenuItemID
   *        Menu item ID. May neither be <code>null</code> nor empty.
   * @param sDisplayName
   *        Display name of the favorite. May neither be <code>null</code> nor
   *        empty.
   * @param aAdditionalParams
   *        Additional params. May be <code>null</code>.
   * @return The created {@link NamedSMTPSettings} object and never
   *         <code>null</code>.
   */
  @Nonnull
  public IFavorite addFavorite (@Nonnull @Nonempty final String sUserID,
                                @Nonnull @Nonempty final String sApplicationID,
                                @Nonnull @Nonempty final String sMenuItemID,
                                @Nonnull @Nonempty final String sDisplayName,
                                @Nullable final Map <String, String> aAdditionalParams)
  {
    final Favorite aFavorite = new Favorite (sUserID, sApplicationID, sMenuItemID, sDisplayName, aAdditionalParams);

    m_aRWLock.writeLock ().lock ();
    try
    {
      _addItem (aFavorite);
      markAsChanged (aFavorite, EDAOActionType.CREATE);
    }
    finally
    {
      m_aRWLock.writeLock ().unlock ();
    }

    AuditUtils.onAuditCreateSuccess (Favorite.OT_FAVOURITE,
                                     aFavorite.getID (),
                                     sUserID,
                                     sApplicationID,
                                     sMenuItemID,
                                     sDisplayName,
                                     aAdditionalParams);
    return aFavorite;
  }

  /**
   * Update an existing favorite object.
   *
   * @param sUserID
   *        The ID of the user of the favorite object to be updated.
   * @param sID
   *        The ID of the favorite object to be updated.
   * @param sDisplayName
   *        The display name to change.
   * @return {@link EChange#CHANGED} if something was changed.
   */
  @Nullable
  public EChange updateFavorite (@Nullable final String sUserID,
                                 @Nullable final String sID,
                                 @Nullable final String sDisplayName)
  {

    List <Favorite> aFavorites = null;
    m_aRWLock.readLock ().lock ();
    try
    {
      aFavorites = CollectionHelper.newList (m_aMap.get (sUserID));
    }
    finally
    {
      m_aRWLock.readLock ().unlock ();
    }

    Favorite aFavorite = null;
    if (CollectionHelper.isNotEmpty (aFavorites))
      for (final Favorite aOther : aFavorites)
        if (sID.equals (aOther.getID ()))
        {
          aFavorite = aOther;
          break;
        }

    if (aFavorite == null)
    {
      AuditUtils.onAuditModifyFailure (Favorite.OT_FAVOURITE, sID, "no-such-id");
      return EChange.UNCHANGED;
    }

    m_aRWLock.writeLock ().lock ();
    try
    {
      final EChange eChange = aFavorite.setDisplayName (sDisplayName);
      if (eChange.isUnchanged ())
        return EChange.UNCHANGED;
      markAsChanged (aFavorite, EDAOActionType.UPDATE);
    }
    finally
    {
      m_aRWLock.writeLock ().unlock ();
    }

    AuditUtils.onAuditModifySuccess (Favorite.OT_FAVOURITE, aFavorite.getID (), sUserID, sDisplayName);

    return EChange.CHANGED;
  }

  /**
   * Remove the favorites with the specified ID.
   *
   * @param sUserID
   *        The ID of the user of the favorite object to be updated.
   * @param sID
   *        The ID of the favorite object to be updated.
   * @return {@link EChange#CHANGED} if a removal was performed.
   */
  @Nullable
  public EChange removeFavorite (@Nullable final String sUserID, @Nullable final String sID)
  {
    List <Favorite> aFavorites = null;
    m_aRWLock.readLock ().lock ();
    try
    {
      aFavorites = m_aMap.get (sUserID);
    }
    finally
    {
      m_aRWLock.readLock ().unlock ();
    }

    Favorite aFavorite = null;
    if (CollectionHelper.isNotEmpty (aFavorites))
      for (final Favorite aOther : aFavorites)
        if (sID.equals (aOther.getID ()))
        {
          aFavorite = aOther;
          break;
        }

    if (aFavorite == null)
    {
      AuditUtils.onAuditDeleteFailure (Favorite.OT_FAVOURITE, sID, "no-such-id");
      return EChange.UNCHANGED;
    }

    EChange eChange;
    m_aRWLock.writeLock ().lock ();
    try
    {
      eChange = EChange.valueOf (aFavorites.remove (aFavorite));
      if (eChange.isChanged ())
        markAsChanged (aFavorite, EDAOActionType.DELETE);
    }
    finally
    {
      m_aRWLock.writeLock ().unlock ();
    }
    if (eChange.isChanged ())
      AuditUtils.onAuditDeleteSuccess (Favorite.OT_FAVOURITE, sID);
    else
      AuditUtils.onAuditDeleteFailure (Favorite.OT_FAVOURITE, sID, "no-such-id");
    return eChange;
  }

  /**
   * Remove all contained favorites objects of an user.
   *
   * @param sUserID
   *        The ID of the user of the favorite objects to be updated.
   * @return {@link EChange#CHANGED} if a removal was performed.
   */
  @Nullable
  public EChange removeAllFavoritesOfUser (@Nullable final String sUserID)
  {
    EChange eChange;
    m_aRWLock.writeLock ().lock ();
    try
    {
      final List <Favorite> aFavoritesOfUser = m_aMap.remove (sUserID);
      eChange = EChange.valueOf (aFavoritesOfUser != null);
      if (eChange.isChanged ())
        markAsChanged (aFavoritesOfUser, EDAOActionType.DELETE);
    }
    finally
    {
      m_aRWLock.writeLock ().unlock ();
    }
    if (eChange.isChanged ())
      AuditUtils.onAuditDeleteSuccess (Favorite.OT_FAVOURITE, sUserID);
    else
      AuditUtils.onAuditDeleteFailure (Favorite.OT_FAVOURITE, sUserID, "no-such-user-id");
    return eChange;
  }

  @Override
  public String toString ()
  {
    return new ToStringGenerator (this).append ("map", m_aMap).toString ();
  }
}
