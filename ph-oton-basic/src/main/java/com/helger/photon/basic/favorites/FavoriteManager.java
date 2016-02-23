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
package com.helger.photon.basic.favorites;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.concurrent.ThreadSafe;

import com.helger.commons.ValueEnforcer;
import com.helger.commons.annotation.ELockType;
import com.helger.commons.annotation.MustBeLocked;
import com.helger.commons.annotation.Nonempty;
import com.helger.commons.annotation.ReturnsMutableCopy;
import com.helger.commons.collection.CollectionHelper;
import com.helger.commons.collection.multimap.IMultiMapListBased;
import com.helger.commons.collection.multimap.MultiHashMapArrayListBased;
import com.helger.commons.lang.IHasSize;
import com.helger.commons.microdom.IMicroDocument;
import com.helger.commons.microdom.IMicroElement;
import com.helger.commons.microdom.MicroDocument;
import com.helger.commons.microdom.convert.MicroTypeConverter;
import com.helger.commons.state.EChange;
import com.helger.commons.string.StringHelper;
import com.helger.commons.string.ToStringGenerator;
import com.helger.photon.basic.app.dao.impl.AbstractWALDAO;
import com.helger.photon.basic.app.dao.impl.DAOException;
import com.helger.photon.basic.app.dao.impl.EDAOActionType;
import com.helger.photon.basic.audit.AuditHelper;

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

  private final IMultiMapListBased <String, Favorite> m_aMap = new MultiHashMapArrayListBased <> ();

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
  public int getSize ()
  {
    return m_aRWLock.readLocked ( () -> m_aMap.size ());
  }

  public boolean isEmpty ()
  {
    return m_aRWLock.readLocked ( () -> m_aMap.isEmpty ());
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
    return m_aRWLock.readLocked ( () -> CollectionHelper.newList (m_aMap.get (sUserID)));
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

    final List <Favorite> aFavorites = m_aRWLock.readLocked ( () -> m_aMap.get (sUserID));
    return CollectionHelper.isNotEmpty (aFavorites);
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

    AuditHelper.onAuditCreateSuccess (Favorite.OT_FAVOURITE,
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

    final List <Favorite> aFavorites = m_aRWLock.readLocked ( () -> CollectionHelper.newList (m_aMap.get (sUserID)));

    final Favorite aFavorite = CollectionHelper.findFirst (aFavorites, f -> sID.equals (f.getID ()));
    if (aFavorite == null)
    {
      AuditHelper.onAuditModifyFailure (Favorite.OT_FAVOURITE, sID, "no-such-id");
      return EChange.UNCHANGED;
    }

    return m_aRWLock.writeLocked ( () -> {
      final EChange eChange = aFavorite.setDisplayName (sDisplayName);
      if (eChange.isUnchanged ())
        return EChange.UNCHANGED;

      markAsChanged (aFavorite, EDAOActionType.UPDATE);
      AuditHelper.onAuditModifySuccess (Favorite.OT_FAVOURITE, aFavorite.getID (), sUserID, sDisplayName);

      return EChange.CHANGED;
    });
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
    final List <Favorite> aFavorites = m_aRWLock.readLocked ( () -> m_aMap.get (sUserID));

    final Favorite aFavorite = CollectionHelper.findFirst (aFavorites, aOther -> sID.equals (aOther.getID ()));
    if (aFavorite == null)
    {
      AuditHelper.onAuditDeleteFailure (Favorite.OT_FAVOURITE, sID, "no-such-id");
      return EChange.UNCHANGED;
    }

    return m_aRWLock.writeLocked ( () -> {
      final EChange eChange = EChange.valueOf (aFavorites.remove (aFavorite));
      if (eChange.isChanged ())
      {
        markAsChanged (aFavorite, EDAOActionType.DELETE);
        AuditHelper.onAuditDeleteSuccess (Favorite.OT_FAVOURITE, sID);
      }
      else
      {
        AuditHelper.onAuditDeleteFailure (Favorite.OT_FAVOURITE, sID, "no-such-id");
      }
      return eChange;
    });
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
    return m_aRWLock.writeLocked ( () -> {
      final List <Favorite> aFavoritesOfUser = m_aMap.remove (sUserID);
      final EChange eChange = EChange.valueOf (aFavoritesOfUser != null);
      if (eChange.isChanged ())
      {
        markAsChanged (aFavoritesOfUser, EDAOActionType.DELETE);
        AuditHelper.onAuditDeleteSuccess (Favorite.OT_FAVOURITE, sUserID);
      }
      else
      {
        AuditHelper.onAuditDeleteFailure (Favorite.OT_FAVOURITE, sUserID, "no-such-user-id");
      }
      return eChange;
    });
  }

  @Override
  public String toString ()
  {
    return new ToStringGenerator (this).append ("map", m_aMap).toString ();
  }
}
