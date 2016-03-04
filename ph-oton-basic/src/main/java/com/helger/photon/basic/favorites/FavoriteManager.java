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

import java.util.Comparator;
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
import com.helger.commons.collection.ext.CommonsArrayList;
import com.helger.commons.collection.ext.CommonsHashMap;
import com.helger.commons.collection.ext.ICommonsList;
import com.helger.commons.collection.ext.ICommonsMap;
import com.helger.commons.collection.multimap.MultiHashMapArrayListBased;
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
public class FavoriteManager extends AbstractWALDAO <Favorite>
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
    return m_aRWLock.readLocked ( () -> m_aMap.getTotalValueCount ());
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
  public ICommonsList <? extends IFavorite> getAllFavoritesOfUser (@Nullable final String sUserID)
  {
    return m_aRWLock.readLocked ( () -> new CommonsArrayList <> (m_aMap.get (sUserID)));
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

    final ICommonsList <Favorite> aFavorites = m_aRWLock.readLocked ( () -> m_aMap.get (sUserID));
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
   * @return <code>null</code> if no such settings are contained.
   */
  public boolean isFavorite (@Nullable final String sUserID,
                             @Nullable final String sApplicationID,
                             @Nullable final String sMenuItemID,
                             @Nullable final ICommonsMap <String, String> aAdditionalParams)
  {
    if (StringHelper.hasNoText (sUserID) ||
        StringHelper.hasNoText (sApplicationID) ||
        StringHelper.hasNoText (sMenuItemID))
      return false;

    final ICommonsMap <String, String> aRealAdditionalParams = aAdditionalParams != null ? aAdditionalParams
                                                                                         : new CommonsHashMap <> ();
    return getAllFavoritesOfUser (sUserID).containsAny (aFavorite -> aFavorite.hasSameContent (sApplicationID,
                                                                                               sMenuItemID,
                                                                                               aRealAdditionalParams));
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
                                @Nullable final ICommonsMap <String, String> aAdditionalParams)
  {
    if (StringHelper.hasText (sUserID) && StringHelper.hasText (sApplicationID) && StringHelper.hasText (sMenuItemID))
    {
      final ICommonsMap <String, String> aRealAdditionalParams = aAdditionalParams != null ? aAdditionalParams
                                                                                           : new CommonsHashMap <> ();
      return getAllFavoritesOfUser (sUserID).findFirst (aFavorite -> aFavorite.hasSameContent (sApplicationID,
                                                                                               sMenuItemID,
                                                                                               aRealAdditionalParams));
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
   * @param sDisplayName
   *        The display name to change.
   * @return {@link EChange#CHANGED} if something was changed.
   */
  @Nullable
  public EChange updateFavorite (@Nullable final String sUserID,
                                 @Nullable final String sID,
                                 @Nullable final String sDisplayName)
  {

    final ICommonsList <Favorite> aFavorites = m_aRWLock.readLocked ( () -> new CommonsArrayList <> (m_aMap.get (sUserID)));

    final Favorite aFavorite = aFavorites.findFirst (f -> f.getID ().equals (sID));
    if (aFavorite == null)
    {
      AuditHelper.onAuditModifyFailure (Favorite.OT, sID, "no-such-id");
      return EChange.UNCHANGED;
    }

    return m_aRWLock.writeLocked ( () -> {
      final EChange eChange = aFavorite.setDisplayName (sDisplayName);
      if (eChange.isUnchanged ())
        return EChange.UNCHANGED;

      markAsChanged (aFavorite, EDAOActionType.UPDATE);
      AuditHelper.onAuditModifySuccess (Favorite.OT, aFavorite.getID (), sUserID, sDisplayName);

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
    final ICommonsList <Favorite> aFavorites = m_aRWLock.readLocked ( () -> m_aMap.get (sUserID));
    if (aFavorites == null)
    {
      AuditHelper.onAuditDeleteFailure (Favorite.OT, sUserID, "no-such-id");
      return EChange.UNCHANGED;
    }

    final Favorite aFavorite = aFavorites.findFirst (aOther -> aOther.getID ().equals (sID));
    if (aFavorite == null)
    {
      AuditHelper.onAuditDeleteFailure (Favorite.OT, sUserID, sID, "no-such-id");
      return EChange.UNCHANGED;
    }

    return m_aRWLock.writeLocked ( () -> {
      final EChange eChange = aFavorites.removeObject (aFavorite);
      if (eChange.isChanged ())
      {
        markAsChanged (aFavorite, EDAOActionType.DELETE);
        AuditHelper.onAuditDeleteSuccess (Favorite.OT, sID);
      }
      else
      {
        AuditHelper.onAuditDeleteFailure (Favorite.OT, sID, "no-such-id");
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
      final ICommonsList <Favorite> aFavoritesOfUser = m_aMap.remove (sUserID);
      final EChange eChange = EChange.valueOf (aFavoritesOfUser != null);
      if (eChange.isChanged ())
      {
        markAsChanged (aFavoritesOfUser, EDAOActionType.DELETE);
        AuditHelper.onAuditDeleteSuccess (Favorite.OT, sUserID);
      }
      else
      {
        AuditHelper.onAuditDeleteFailure (Favorite.OT, sUserID, "no-such-user-id");
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
