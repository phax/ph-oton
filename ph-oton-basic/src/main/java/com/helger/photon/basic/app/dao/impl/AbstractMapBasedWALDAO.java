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
import com.helger.commons.annotation.Nonempty;
import com.helger.commons.annotation.ReturnsMutableCopy;
import com.helger.commons.annotation.ReturnsMutableObject;
import com.helger.commons.callback.CallbackList;
import com.helger.commons.collection.CollectionHelper;
import com.helger.commons.collection.ext.CommonsArrayList;
import com.helger.commons.collection.ext.CommonsHashMap;
import com.helger.commons.collection.ext.ICommonsCollection;
import com.helger.commons.collection.ext.ICommonsList;
import com.helger.commons.collection.ext.ICommonsMap;
import com.helger.commons.collection.ext.ICommonsSet;
import com.helger.commons.lang.ClassHelper;
import com.helger.commons.state.EChange;
import com.helger.commons.string.StringHelper;
import com.helger.commons.string.ToStringGenerator;
import com.helger.commons.type.ITypedObject;
import com.helger.xml.microdom.IMicroDocument;
import com.helger.xml.microdom.IMicroElement;
import com.helger.xml.microdom.MicroDocument;
import com.helger.xml.microdom.convert.MicroTypeConverter;

@ThreadSafe
public abstract class AbstractMapBasedWALDAO <INTERFACETYPE extends ITypedObject <String> & Serializable, IMPLTYPE extends INTERFACETYPE>
                                             extends AbstractWALDAO <IMPLTYPE> implements IMapBasedDAO <INTERFACETYPE>
{
  private final String m_sXMLItemElementName;
  @GuardedBy ("m_aRWLock")
  private final ICommonsMap <String, IMPLTYPE> m_aMap = new CommonsHashMap <> ();
  private final CallbackList <IDAOChangeCallback <INTERFACETYPE>> m_aCallbacks = new CallbackList <> ();

  public AbstractMapBasedWALDAO (@Nonnull final Class <IMPLTYPE> aImplClass,
                                 @Nonnull @Nonempty final String sFilename,
                                 @Nonnull @Nonempty final String sXMLItemElementName) throws DAOException
  {
    this (aImplClass, sFilename, sXMLItemElementName, true);
  }

  public AbstractMapBasedWALDAO (@Nonnull final Class <IMPLTYPE> aImplClass,
                                 @Nonnull @Nonempty final String sFilename,
                                 @Nonnull @Nonempty final String sXMLItemElementName,
                                 final boolean bDoInitialRead) throws DAOException
  {
    super (aImplClass, sFilename);
    m_sXMLItemElementName = ValueEnforcer.notEmpty (sXMLItemElementName, "XMLItemElementName");
    if (bDoInitialRead)
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
    for (final IMicroElement eItem : aDoc.getDocumentElement ().getAllChildElements (m_sXMLItemElementName))
      _addItem (MicroTypeConverter.convertToNative (eItem, getDataTypeClass ()), EDAOActionType.CREATE);
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
    final IMicroElement eRoot = aDoc.appendElement ("root");
    for (final IMPLTYPE aItem : getAllSortedByKey ())
      eRoot.appendChild (MicroTypeConverter.convertToMicroElement (aItem, m_sXMLItemElementName));
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
                                            aOldItem);
    }
    else
    {
      // Update
      if (aOldItem == null)
        throw new IllegalArgumentException (ClassHelper.getClassLocalName (getDataTypeClass ()) +
                                            " with ID '" +
                                            sID +
                                            "' is not yet in use and can therefore not be updated!");
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
  protected final void internalCreateItem (@Nonnull final IMPLTYPE aNewItem)
  {
    // Add to map
    _addItem (aNewItem, EDAOActionType.CREATE);
    // Trigger save changes
    super.markAsChanged (aNewItem, EDAOActionType.CREATE);
    // Invoke callbacks
    m_aCallbacks.forEach (aCB -> aCB.onCreateItem (aNewItem));
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
  protected final void internalRemoveAllItemsNoCallback ()
  {
    m_aMap.removeAll ();
  }

  @Nonnull
  @ReturnsMutableCopy
  public final ICommonsCollection <? extends INTERFACETYPE> getNone ()
  {
    return new CommonsArrayList <> ();
  }

  @Nonnull
  @ReturnsMutableCopy
  public final ICommonsList <? extends INTERFACETYPE> getAll ()
  {
    return m_aRWLock.readLocked ( () -> m_aMap.copyOfValues ());
  }

  @Nonnull
  @ReturnsMutableCopy
  public final ICommonsList <? extends INTERFACETYPE> getAll (@Nullable final Predicate <? super INTERFACETYPE> aFilter)
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

  public final void forEachValue (@Nullable final Predicate <? super INTERFACETYPE> aFilter,
                                  @Nullable final Consumer <? super INTERFACETYPE> aConsumer)
  {
    m_aRWLock.readLocked ( () -> m_aMap.forEachValue (aFilter, aConsumer));
  }

  @Nullable
  protected final IMPLTYPE getOfID (@Nullable final String sID)
  {
    if (StringHelper.hasNoText (sID))
      return null;

    return m_aRWLock.readLocked ( () -> m_aMap.get (sID));
  }

  public final boolean containsWithID (@Nullable final String sID)
  {
    if (StringHelper.hasNoText (sID))
      return false;

    return m_aRWLock.readLocked ( () -> m_aMap.containsKey (sID));
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
    return ToStringGenerator.getDerived (super.toString ())
                            .append ("XMLItemElementName", m_sXMLItemElementName)
                            .append ("Map", m_aMap)
                            .toString ();
  }
}
