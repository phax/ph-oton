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
package com.helger.photon.basic.app.dao.impl;

import java.io.Serializable;
import java.util.Collection;
import java.util.Comparator;
import java.util.function.BiConsumer;
import java.util.function.BiPredicate;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.OverridingMethodsMustInvokeSuper;
import javax.annotation.concurrent.GuardedBy;
import javax.annotation.concurrent.ThreadSafe;

import com.helger.commons.ValueEnforcer;
import com.helger.commons.annotation.CodingStyleguideUnaware;
import com.helger.commons.annotation.ELockType;
import com.helger.commons.annotation.MustBeLocked;
import com.helger.commons.annotation.ReturnsMutableCopy;
import com.helger.commons.annotation.ReturnsMutableObject;
import com.helger.commons.callback.CallbackList;
import com.helger.commons.collection.CollectionHelper;
import com.helger.commons.collection.ext.CommonsArrayList;
import com.helger.commons.collection.ext.CommonsHashMap;
import com.helger.commons.collection.ext.CommonsLinkedHashMap;
import com.helger.commons.collection.ext.ICommonsList;
import com.helger.commons.collection.ext.ICommonsMap;
import com.helger.commons.collection.ext.ICommonsSet;
import com.helger.commons.filter.IFilter;
import com.helger.commons.id.IHasID;
import com.helger.commons.lang.ClassHelper;
import com.helger.commons.state.EChange;
import com.helger.commons.string.StringHelper;
import com.helger.commons.string.ToStringGenerator;
import com.helger.xml.microdom.IMicroDocument;
import com.helger.xml.microdom.IMicroElement;
import com.helger.xml.microdom.MicroDocument;
import com.helger.xml.microdom.convert.MicroTypeConverter;

/**
 * Base class for WAL based DAO that uses a simple {@link ICommonsMap} for data
 * storage.
 *
 * @author Philip Helger
 * @param <INTERFACETYPE>
 *        Interface type to be handled
 * @param <IMPLTYPE>
 *        Implementation type to be handled
 */
@ThreadSafe
public abstract class AbstractMapBasedWALDAO <INTERFACETYPE extends IHasID <String> & Serializable, IMPLTYPE extends INTERFACETYPE>
                                             extends AbstractWALDAO <IMPLTYPE> implements IMapBasedDAO <INTERFACETYPE>
{
  /**
   * Extensible constructor parameter builder. Must be static because it is used
   * in the constructor and no <code>this</code> is present.
   *
   * @author Philip Helger
   * @param <IMPLTYPE>
   *        Implementation type to use.
   */
  public static final class InitSettings <IMPLTYPE>
  {
    private boolean m_bDoInitialRead = true;
    private Supplier <ICommonsMap <String, IMPLTYPE>> m_aMapSupplier = () -> new CommonsHashMap<> ();
    private IFilter <IMicroElement> m_aReadElementFilter = IFilter.all ();

    @Nonnull
    public InitSettings <IMPLTYPE> setDoInitialRead (final boolean bDoInitialRead)
    {
      m_bDoInitialRead = bDoInitialRead;
      return this;
    }

    @Nonnull
    public InitSettings <IMPLTYPE> setMapSupplier (@Nonnull final Supplier <ICommonsMap <String, IMPLTYPE>> aMapSupplier)
    {
      m_aMapSupplier = ValueEnforcer.notNull (aMapSupplier, "MapSupplier");
      return this;
    }

    @Nonnull
    public InitSettings <IMPLTYPE> setOrderedMapSupplier ()
    {
      return setMapSupplier ( () -> new CommonsLinkedHashMap<> ());
    }

    @Nonnull
    public InitSettings <IMPLTYPE> setReadElementFilter (@Nonnull final IFilter <IMicroElement> aReadElementFilter)
    {
      m_aReadElementFilter = ValueEnforcer.notNull (aReadElementFilter, "ReadElementFilter");
      return this;
    }
  }

  protected static final String ELEMENT_ROOT = "root";
  protected static final String ELEMENT_ITEM = "item";

  @GuardedBy ("m_aRWLock")
  private final ICommonsMap <String, IMPLTYPE> m_aMap;
  private final CallbackList <IDAOChangeCallback <INTERFACETYPE>> m_aCallbacks = new CallbackList<> ();
  private IFilter <IMicroElement> m_aReadElementFilter;

  /**
   * Default constructor
   *
   * @param aImplClass
   *        Implementation class. May not be <code>null</code>.
   * @param sFilename
   *        The filename to read and write.
   * @throws DAOException
   *         If reading and reading fails
   */
  public AbstractMapBasedWALDAO (@Nonnull final Class <IMPLTYPE> aImplClass,
                                 @Nullable final String sFilename) throws DAOException
  {
    this (aImplClass, sFilename, new InitSettings<> ());
  }

  public AbstractMapBasedWALDAO (@Nonnull final Class <IMPLTYPE> aImplClass,
                                 @Nullable final String sFilename,
                                 @Nonnull final InitSettings <IMPLTYPE> aInitSettings) throws DAOException
  {
    super (aImplClass, sFilename);
    m_aMap = aInitSettings.m_aMapSupplier.get ();
    m_aReadElementFilter = aInitSettings.m_aReadElementFilter;
    if (aInitSettings.m_bDoInitialRead)
      initialRead ();
  }

  @Override
  protected void onRecoveryCreate (@Nonnull final IMPLTYPE aItem)
  {
    _addItem (aItem, EDAOActionType.CREATE);
  }

  @Override
  protected void onRecoveryUpdate (@Nonnull final IMPLTYPE aItem)
  {
    _addItem (aItem, EDAOActionType.UPDATE);
  }

  @Override
  protected void onRecoveryDelete (@Nonnull final IMPLTYPE aItem)
  {
    m_aMap.remove (aItem.getID (), aItem);
  }

  @Override
  @Nonnull
  protected EChange onRead (@Nonnull final IMicroDocument aDoc)
  {
    // Read all child elements independent of the name - soft migration
    aDoc.getDocumentElement ()
        .forAllChildElements (m_aReadElementFilter,
                              eItem -> _addItem (MicroTypeConverter.convertToNative (eItem, getDataTypeClass ()),
                                                 EDAOActionType.CREATE));
    return EChange.UNCHANGED;
  }

  @MustBeLocked (ELockType.READ)
  @CodingStyleguideUnaware
  protected final Collection <IMPLTYPE> getAllSortedByKey ()
  {
    return m_aMap.getSortedByKey (Comparator.naturalOrder ()).values ();
  }

  @Override
  @Nonnull
  protected IMicroDocument createWriteData ()
  {
    final IMicroDocument aDoc = new MicroDocument ();
    final IMicroElement eRoot = aDoc.appendElement (ELEMENT_ROOT);
    for (final IMPLTYPE aItem : getAllSortedByKey ())
      eRoot.appendChild (MicroTypeConverter.convertToMicroElement (aItem, ELEMENT_ITEM));
    return aDoc;
  }

  @Nonnull
  @ReturnsMutableObject ("design")
  public CallbackList <IDAOChangeCallback <INTERFACETYPE>> getCallbacks ()
  {
    return m_aCallbacks;
  }

  /**
   * Add or update an item
   *
   * @param aItem
   *        The item to be added or updated
   * @param eActionType
   *        The action type. Must be CREATE or UPDATE!
   */
  @MustBeLocked (ELockType.WRITE)
  private void _addItem (@Nonnull final IMPLTYPE aItem, @Nonnull final EDAOActionType eActionType)
  {
    ValueEnforcer.notNull (aItem, "Item");
    ValueEnforcer.isTrue (eActionType == EDAOActionType.CREATE ||
                          eActionType == EDAOActionType.UPDATE,
                          "Invalid action type provided!");

    final String sID = aItem.getID ();
    final IMPLTYPE aOldItem = m_aMap.get (sID);
    if (eActionType == EDAOActionType.CREATE)
    {
      if (aOldItem != null)
        throw new IllegalArgumentException (ClassHelper.getClassLocalName (getDataTypeClass ()) +
                                            " with ID '" +
                                            sID +
                                            "' is already in use and can therefore not be created again. Old item = " +
                                            aOldItem +
                                            "; New item = " +
                                            aItem);
    }
    else
    {
      // Update
      if (aOldItem == null)
        throw new IllegalArgumentException (ClassHelper.getClassLocalName (getDataTypeClass ()) +
                                            " with ID '" +
                                            sID +
                                            "' is not yet in use and can therefore not be updated! Updated item = " +
                                            aItem);
    }

    m_aMap.put (sID, aItem);
  }

  @Override
  @MustBeLocked (ELockType.WRITE)
  @OverridingMethodsMustInvokeSuper
  @Deprecated
  protected final void markAsChanged (@Nonnull final IMPLTYPE aModifiedElement,
                                      @Nonnull final EDAOActionType eActionType)
  {
    super.markAsChanged (aModifiedElement, eActionType);
  }

  /**
   * Add an item including invoking the callback
   *
   * @param aNewItem
   *        The item to be added. May not be <code>null</code>.
   */
  @MustBeLocked (ELockType.WRITE)
  protected final IMPLTYPE internalCreateItem (@Nonnull final IMPLTYPE aNewItem)
  {
    // Add to map
    _addItem (aNewItem, EDAOActionType.CREATE);
    // Trigger save changes
    super.markAsChanged (aNewItem, EDAOActionType.CREATE);
    // Invoke callbacks
    m_aCallbacks.forEach (aCB -> aCB.onCreateItem (aNewItem));
    return aNewItem;
  }

  /**
   * Update and existing item including invoking the callback
   *
   * @param aItem
   *        The item to be updated. May not be <code>null</code>.
   */
  @MustBeLocked (ELockType.WRITE)
  protected final void internalUpdateItem (@Nonnull final IMPLTYPE aItem)
  {
    // Add to map - ensure to overwrite any existing
    _addItem (aItem, EDAOActionType.UPDATE);
    // Trigger save changes
    super.markAsChanged (aItem, EDAOActionType.UPDATE);
    // Invoke callbacks
    m_aCallbacks.forEach (aCB -> aCB.onUpdateItem (aItem));
  }

  @MustBeLocked (ELockType.WRITE)
  @Nullable
  protected final IMPLTYPE internalDeleteItem (@Nullable final String sID)
  {
    if (StringHelper.hasNoText (sID))
      return null;

    final IMPLTYPE aDeletedItem = m_aMap.remove (sID);
    if (aDeletedItem == null)
      return null;

    // Trigger save changes
    super.markAsChanged (aDeletedItem, EDAOActionType.DELETE);
    // Invoke callbacks
    m_aCallbacks.forEach (aCB -> aCB.onDeleteItem (aDeletedItem));
    return aDeletedItem;
  }

  @MustBeLocked (ELockType.WRITE)
  protected final void internalMarkItemDeleted (@Nonnull final IMPLTYPE aItem)
  {
    // Trigger save changes
    super.markAsChanged (aItem, EDAOActionType.UPDATE);
    // Invoke callbacks
    m_aCallbacks.forEach (aCB -> aCB.onMarkItemDeleted (aItem));
  }

  @MustBeLocked (ELockType.WRITE)
  protected final void internalMarkItemUndeleted (@Nonnull final IMPLTYPE aItem)
  {
    // Trigger save changes
    super.markAsChanged (aItem, EDAOActionType.UPDATE);
    // Invoke callbacks
    m_aCallbacks.forEach (aCB -> aCB.onMarkItemUndeleted (aItem));
  }

  @MustBeLocked (ELockType.WRITE)
  protected final void internalRemoveAllItemsNoCallback ()
  {
    m_aMap.removeAll ();
  }

  @Nonnull
  @ReturnsMutableCopy
  public final <T> ICommonsList <T> getNone ()
  {
    return new CommonsArrayList<> ();
  }

  @Nonnull
  @ReturnsMutableCopy
  public final ICommonsList <INTERFACETYPE> getAll ()
  {
    // Use new CommonsArrayList to get the return type to NOT use "? extends
    // INTERFACETYPE"
    return m_aRWLock.readLocked ( () -> new CommonsArrayList<> (m_aMap.values ()));
  }

  @Nonnull
  @ReturnsMutableCopy
  public final ICommonsList <INTERFACETYPE> getAll (@Nullable final Predicate <? super INTERFACETYPE> aFilter)
  {
    if (aFilter == null)
      return getAll ();

    // Use new CommonsArrayList to get the return type to NOT use "? extends
    // INTERFACETYPE"
    final ICommonsList <INTERFACETYPE> ret = new CommonsArrayList<> ();
    m_aRWLock.readLocked ( () -> CollectionHelper.findAll (m_aMap.values (), aFilter, ret::add));
    return ret;
  }

  @Nonnull
  @ReturnsMutableCopy
  protected final Iterable <IMPLTYPE> internalDirectGetAll ()
  {
    return m_aRWLock.readLocked ( () -> m_aMap.values ());
  }

  @Nonnull
  @ReturnsMutableCopy
  protected final ICommonsList <IMPLTYPE> internalGetAll (@Nullable final Predicate <? super IMPLTYPE> aFilter)
  {
    return m_aRWLock.readLocked ( () -> m_aMap.copyOfValues (aFilter));
  }

  public final void findAll (@Nullable final Predicate <? super INTERFACETYPE> aFilter,
                             @Nonnull final Consumer <? super INTERFACETYPE> aConsumer)
  {
    m_aRWLock.readLocked ( () -> CollectionHelper.findAll (m_aMap.values (), aFilter, aConsumer));
  }

  @Nonnull
  @ReturnsMutableCopy
  public final <RETTYPE> ICommonsList <RETTYPE> getAllMapped (@Nullable final Predicate <? super INTERFACETYPE> aFilter,
                                                              @Nonnull final Function <? super INTERFACETYPE, ? extends RETTYPE> aMapper)
  {
    return m_aRWLock.readLocked ( () -> m_aMap.copyOfValuesMapped (aFilter, aMapper));
  }

  public final <RETTYPE> void findAllMapped (@Nullable final Predicate <? super INTERFACETYPE> aFilter,
                                             @Nonnull final Function <? super INTERFACETYPE, ? extends RETTYPE> aMapper,
                                             @Nonnull final Consumer <? super RETTYPE> aConsumer)
  {
    m_aRWLock.readLocked ( () -> CollectionHelper.findAllMapped (m_aMap.values (), aFilter, aMapper, aConsumer));
  }

  @Nullable
  public final INTERFACETYPE findFirst (@Nullable final Predicate <? super INTERFACETYPE> aFilter)
  {
    return m_aRWLock.readLocked ( () -> CollectionHelper.findFirst (m_aMap.values (), aFilter));
  }

  @Nullable
  public final <RETTYPE> RETTYPE findFirstMapped (@Nullable final Predicate <? super INTERFACETYPE> aFilter,
                                                  @Nonnull final Function <? super INTERFACETYPE, ? extends RETTYPE> aMapper)
  {
    return m_aRWLock.readLocked ( () -> CollectionHelper.findFirstMapped (m_aMap.values (), aFilter, aMapper));
  }

  public final boolean containsAny ()
  {
    return m_aRWLock.readLocked ( () -> m_aMap.isNotEmpty ());
  }

  public final boolean containsAny (@Nullable final Predicate <? super INTERFACETYPE> aFilter)
  {
    return m_aRWLock.readLocked ( () -> CollectionHelper.containsAny (m_aMap.values (), aFilter));
  }

  public final boolean containsNone ()
  {
    return m_aRWLock.readLocked ( () -> m_aMap.isEmpty ());
  }

  public final boolean containsNone (@Nullable final Predicate <? super INTERFACETYPE> aFilter)
  {
    return m_aRWLock.readLocked ( () -> CollectionHelper.containsNone (m_aMap.values (), aFilter));
  }

  public final boolean containsOnly (@Nullable final Predicate <? super INTERFACETYPE> aFilter)
  {
    return m_aRWLock.readLocked ( () -> CollectionHelper.containsOnly (m_aMap.values (), aFilter));
  }

  public final void forEach (@Nullable final BiConsumer <? super String, ? super INTERFACETYPE> aConsumer)
  {
    m_aRWLock.readLocked ( () -> m_aMap.forEach (aConsumer));
  }

  public final void forEach (@Nullable final BiPredicate <? super String, ? super INTERFACETYPE> aFilter,
                             @Nullable final BiConsumer <? super String, ? super INTERFACETYPE> aConsumer)
  {
    m_aRWLock.readLocked ( () -> m_aMap.forEach (aFilter, aConsumer));
  }

  public final void forEachKey (@Nullable final Consumer <? super String> aConsumer)
  {
    m_aRWLock.readLocked ( () -> m_aMap.forEachKey (aConsumer));
  }

  public final void forEachKey (@Nullable final Predicate <? super String> aFilter,
                                @Nullable final Consumer <? super String> aConsumer)
  {
    m_aRWLock.readLocked ( () -> m_aMap.forEachKey (aFilter, aConsumer));
  }

  public final void forEachValue (@Nullable final Consumer <? super INTERFACETYPE> aConsumer)
  {
    m_aRWLock.readLocked ( () -> m_aMap.forEachValue (aConsumer));
  }

  protected final void internalForEachValue (@Nullable final Consumer <? super IMPLTYPE> aConsumer)
  {
    m_aRWLock.readLocked ( () -> m_aMap.forEachValue (aConsumer));
  }

  public final void forEachValue (@Nullable final Predicate <? super INTERFACETYPE> aFilter,
                                  @Nullable final Consumer <? super INTERFACETYPE> aConsumer)
  {
    m_aRWLock.readLocked ( () -> m_aMap.forEachValue (aFilter, aConsumer));
  }

  protected final void internalForEachValue (@Nullable final Predicate <? super IMPLTYPE> aFilter,
                                             @Nullable final Consumer <? super IMPLTYPE> aConsumer)
  {
    m_aRWLock.readLocked ( () -> m_aMap.forEachValue (aFilter, aConsumer));
  }

  @Nullable
  @MustBeLocked (ELockType.READ)
  protected final IMPLTYPE getOfIDLocked (@Nullable final String sID)
  {
    if (StringHelper.hasNoText (sID))
      return null;

    return m_aMap.get (sID);
  }

  @Nullable
  protected final IMPLTYPE getOfID (@Nullable final String sID)
  {
    if (StringHelper.hasNoText (sID))
      return null;

    return m_aRWLock.readLocked ( () -> m_aMap.get (sID));
  }

  /**
   * Get the item at the specified index. This method only returns defined
   * results if a CommonLinkedHashMap is used for data storage.
   *
   * @param nIndex
   *        The index to retrieve. Should be &ge; 0.
   * @return <code>null</code> if an invalid index was provided.
   */
  @Nullable
  protected final INTERFACETYPE getAtIndex (@Nonnegative final int nIndex)
  {
    return m_aRWLock.readLocked ( () -> CollectionHelper.getAtIndex (m_aMap.values (), nIndex));
  }

  public final boolean containsWithID (@Nullable final String sID)
  {
    if (StringHelper.hasNoText (sID))
      return false;

    return m_aRWLock.readLocked ( () -> m_aMap.containsKey (sID));
  }

  public final boolean containsAllIDs (@Nullable final Iterable <String> aIDs)
  {
    if (aIDs != null)
      for (final String sID : aIDs)
        if (!containsWithID (sID))
          return false;
    return true;
  }

  @Nonnull
  @ReturnsMutableCopy
  public final ICommonsSet <String> getAllIDs ()
  {
    return m_aRWLock.readLocked ( () -> m_aMap.copyOfKeySet ());
  }

  @Nonnegative
  public final int getCount ()
  {
    return m_aRWLock.readLocked ( () -> m_aMap.size ());
  }

  @Nonnegative
  public final int getCount (@Nullable final Predicate <? super INTERFACETYPE> aFilter)
  {
    return m_aRWLock.readLocked ( () -> CollectionHelper.getCount (m_aMap.values (), aFilter));
  }

  @Override
  public String toString ()
  {
    return ToStringGenerator.getDerived (super.toString ()).append ("Map", m_aMap).toString ();
  }
}
