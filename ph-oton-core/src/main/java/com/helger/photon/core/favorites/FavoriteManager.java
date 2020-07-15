/**
 * Copyright (C) 2014-2020 Philip Helger (www.helger.com)
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

import java.util.Comparator;
import java.util.Map;

import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.concurrent.ThreadSafe;

import com.helger.collection.multimap.IMultiMapListBased;
import com.helger.collection.multimap.MultiHashMapArrayListBased;
import com.helger.commons.ValueEnforcer;
import com.helger.commons.annotation.ELockType;
import com.helger.commons.annotation.MustBeLocked;
import com.helger.commons.annotation.Nonempty;
import com.helger.commons.annotation.ReturnsMutableCopy;
import com.helger.commons.collection.impl.CommonsArrayList;
import com.helger.commons.collection.impl.ICommonsList;
import com.helger.commons.collection.impl.ICommonsSet;
import com.helger.commons.state.EChange;
import com.helger.commons.string.StringHelper;
import com.helger.commons.string.ToStringGenerator;
import com.helger.dao.DAOException;
import com.helger.dao.EDAOActionType;
import com.helger.photon.app.dao.AbstractPhotonWALDAO;
import com.helger.photon.audit.AuditHelper;
import com.helger.xml.microdom.IMicroDocument;
import com.helger.xml.microdom.IMicroElement;
import com.helger.xml.microdom.MicroDocument;
import com.helger.xml.microdom.convert.MicroTypeConverter;

/**
 * This class manages {@link Favorite} objects.
 *
 * @author Philip Helger
 */
@ThreadSafe
public class FavoriteManager extends AbstractPhotonWALDAO <Favorite>
{
  private static final String ELEMENT_ITEM = "favorite";

  /** Map from user ID to favorites */
  private final MultiHashMapArrayListBased <String, Favorite> m_aMap = new MultiHashMapArrayListBased <> ();

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
    for (final IMicroElement eFavorite : aDoc.getDocumentElement ().getAllChildElements (ELEMENT_ITEM))
      _addItem (MicroTypeConverter.convertToNative (eFavorite, Favorite.class));
    return EChange.UNCHANGED;
  }

  @Override
  @Nonnull
  protected IMicroDocument createWriteData ()
  {
    final IMicroDocument aDoc = new MicroDocument ();
    final IMicroElement eRoot = aDoc.appendElement ("root");
    for (final ICommonsList <Favorite> aFavoritesOfUser : m_aMap.getSortedByKey (Comparator.naturalOrder ()).values ())
      for (final Favorite aFavorite : aFavoritesOfUser)
        eRoot.appendChild (MicroTypeConverter.convertToMicroElement (aFavorite, ELEMENT_ITEM));
    return aDoc;
  }

  public boolean isReloadable ()
  {
    return true;
  }

  public void reload () throws DAOException
  {
    m_aRWLock.writeLockedThrowing ( () -> {
      m_aMap.clear ();
      initialRead ();
    });
  }

  @MustBeLocked (ELockType.WRITE)
  private void _addItem (@Nonnull final Favorite aFavorite)
  {
    ValueEnforcer.notNull (aFavorite, "Favorite");
    m_aMap.putSingle (aFavorite.getUserID (), aFavorite);
  }

  @Nonnegative
  public long getSize ()
  {
    return m_aRWLock.readLockedLong (m_aMap::getTotalValueCount);
  }

  public boolean isEmpty ()
  {
    return m_aRWLock.readLockedBoolean (m_aMap::isEmpty);
  }

  @Nonnull
  @ReturnsMutableCopy
  public ICommonsSet <String> getAllUserIDsWithFavorites ()
  {
    return m_aRWLock.readLockedGet (m_aMap::copyOfKeySet);
  }

  /**
   * @param sUserID
   *        The ID of the user.
   * @return A copy of all contained favorite as map from ID to object. Never
   *         <code>null</code> but maybe empty.
   */
  @Nonnull
  @ReturnsMutableCopy
  public ICommonsList <IFavorite> getAllFavoritesOfUser (@Nullable final String sUserID)
  {
    return m_aRWLock.readLockedGet ( () -> new CommonsArrayList <> (m_aMap.get (sUserID)));
  }

  @Nonnull
  @ReturnsMutableCopy
  public IMultiMapListBased <String, IFavorite> getAll ()
  {
    final IMultiMapListBased <String, IFavorite> ret = new MultiHashMapArrayListBased <> ();
    for (final Map.Entry <String, ICommonsList <Favorite>> aEntry : m_aMap.entrySet ())
      ret.put (aEntry.getKey (), new CommonsArrayList <> (aEntry.getValue ()));
    return ret;
  }

  @Nonnull
  @ReturnsMutableCopy
  public ICommonsList <IFavorite> getAllFavorites ()
  {
    final ICommonsList <IFavorite> ret = new CommonsArrayList <> ();
    for (final Map.Entry <String, ICommonsList <Favorite>> aEntry : m_aMap.entrySet ())
      ret.addAll (aEntry.getValue ());
    return ret;
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

    final ICommonsList <Favorite> aFavorites = m_aRWLock.readLockedGet ( () -> m_aMap.get (sUserID));
    return aFavorites != null && aFavorites.isNotEmpty ();
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
   * @return <code>false</code> if no such favorite is contained.
   */
  public boolean isFavorite (@Nullable final String sUserID,
                             @Nullable final String sApplicationID,
                             @Nullable final String sMenuItemID,
                             @Nullable final Map <String, String> aAdditionalParams)
  {
    return getFavorite (sUserID, sApplicationID, sMenuItemID, aAdditionalParams) != null;
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
   * @return <code>null</code> if no such favorite is contained.
   */
  @Nullable
  public IFavorite getFavorite (@Nullable final String sUserID,
                                @Nullable final String sApplicationID,
                                @Nullable final String sMenuItemID,
                                @Nullable final Map <String, String> aAdditionalParams)
  {
    if (StringHelper.hasText (sUserID) && StringHelper.hasText (sApplicationID) && StringHelper.hasText (sMenuItemID))
    {
      final ICommonsList <Favorite> aFavs = m_aRWLock.readLockedGet ( () -> m_aMap.get (sUserID));
      if (aFavs != null)
        return aFavs.findFirst (x -> x.hasSameContent (sApplicationID, sMenuItemID, aAdditionalParams));
    }
    return null;
  }

  @Nullable
  public IFavorite getFavorite (@Nullable final String sUserID, @Nullable final String sFavoriteID)
  {
    if (StringHelper.hasText (sUserID) && StringHelper.hasText (sFavoriteID))
    {
      final ICommonsList <Favorite> aFavs = m_aRWLock.readLockedGet ( () -> m_aMap.get (sUserID));
      if (aFavs != null)
        return aFavs.findFirst (x -> x.getID ().equals (sFavoriteID));
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
   * @return The created {@link IFavorite} object and never <code>null</code>.
   */
  @Nonnull
  public IFavorite addFavorite (@Nonnull @Nonempty final String sUserID,
                                @Nonnull @Nonempty final String sApplicationID,
                                @Nonnull @Nonempty final String sMenuItemID,
                                @Nonnull @Nonempty final String sDisplayName,
                                @Nullable final Map <String, String> aAdditionalParams)
  {
    final Favorite aFavorite = new Favorite (sUserID, sApplicationID, sMenuItemID, sDisplayName, aAdditionalParams);

    m_aRWLock.writeLocked ( () -> {
      _addItem (aFavorite);
      markAsChanged (aFavorite, EDAOActionType.CREATE);
    });

    AuditHelper.onAuditCreateSuccess (Favorite.OT,
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
   * @param sMenuItemID
   *        Menu item ID. May neither be <code>null</code> nor empty.
   * @param sDisplayName
   *        The display name to change. May neither be <code>null</code> nor
   *        empty.
   * @param aAdditionalParams
   *        Additional params. May be <code>null</code>.
   * @return {@link EChange#CHANGED} if something was changed.
   */
  @Nullable
  public EChange updateFavorite (@Nullable final String sUserID,
                                 @Nullable final String sID,
                                 @Nonnull @Nonempty final String sMenuItemID,
                                 @Nonnull @Nonempty final String sDisplayName,
                                 @Nullable final Map <String, String> aAdditionalParams)
  {

    final ICommonsList <Favorite> aFavorites = m_aRWLock.readLockedGet ( () -> m_aMap.get (sUserID));
    final Favorite aFavorite = aFavorites == null ? null : aFavorites.findFirst (x -> x.getID ().equals (sID));
    if (aFavorite == null)
    {
      AuditHelper.onAuditModifyFailure (Favorite.OT, sID, "no-such-id");
      return EChange.UNCHANGED;
    }

    m_aRWLock.writeLock ().lock ();
    try
    {
      EChange eChange = EChange.UNCHANGED;
      eChange = eChange.or (aFavorite.setMenuItemID (sMenuItemID));
      eChange = eChange.or (aFavorite.setDisplayName (sDisplayName));
      eChange = eChange.or (aFavorite.setAdditionalParams (aAdditionalParams));
      if (eChange.isUnchanged ())
      {
        AuditHelper.onAuditModifyFailure (Favorite.OT, aFavorite.getID (), "unchanged");
        return EChange.UNCHANGED;
      }

      markAsChanged (aFavorite, EDAOActionType.UPDATE);
    }
    finally
    {
      m_aRWLock.writeLock ().unlock ();
    }
    AuditHelper.onAuditModifySuccess (Favorite.OT, aFavorite.getID (), sUserID, sMenuItemID, sDisplayName, aAdditionalParams);
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
    final ICommonsList <Favorite> aFavorites = m_aRWLock.readLockedGet ( () -> m_aMap.get (sUserID));
    final Favorite aFavorite = aFavorites == null ? null : aFavorites.findFirst (x -> x.getID ().equals (sID));
    if (aFavorite == null)
    {
      AuditHelper.onAuditDeleteFailure (Favorite.OT, sUserID, sID, "no-such-id");
      return EChange.UNCHANGED;
    }

    m_aRWLock.writeLock ().lock ();
    try
    {
      if (aFavorites.removeObject (aFavorite).isUnchanged ())
      {
        AuditHelper.onAuditDeleteFailure (Favorite.OT, sID, "no-such-id");
        return EChange.UNCHANGED;
      }
      markAsChanged (aFavorite, EDAOActionType.DELETE);
    }
    finally
    {
      m_aRWLock.writeLock ().unlock ();
    }
    AuditHelper.onAuditDeleteSuccess (Favorite.OT, sID);
    return EChange.CHANGED;
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
    m_aRWLock.writeLock ().lock ();
    try
    {
      final ICommonsList <Favorite> aFavoritesOfUser = m_aMap.remove (sUserID);
      if (aFavoritesOfUser == null)
      {
        AuditHelper.onAuditDeleteFailure (Favorite.OT, sUserID, "no-such-user-id");
        return EChange.UNCHANGED;
      }
      markAsChanged (aFavoritesOfUser, EDAOActionType.DELETE);
    }
    finally
    {
      m_aRWLock.writeLock ().unlock ();
    }

    AuditHelper.onAuditDeleteSuccess (Favorite.OT, sUserID);
    return EChange.CHANGED;
  }

  @Override
  public String toString ()
  {
    return new ToStringGenerator (this).append ("map", m_aMap).getToString ();
  }
}
