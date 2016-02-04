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
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import java.util.function.Predicate;

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
import com.helger.commons.id.IHasID;
import com.helger.commons.lang.ClassHelper;
import com.helger.commons.microdom.IMicroDocument;
import com.helger.commons.microdom.IMicroElement;
import com.helger.commons.microdom.MicroDocument;
import com.helger.commons.microdom.convert.MicroTypeConverter;
import com.helger.commons.state.EChange;
import com.helger.commons.string.StringHelper;

@ThreadSafe
public abstract class AbstractMapBasedWALDAO <INTERFACETYPE extends IHasID <String> & Serializable, IMPLTYPE extends INTERFACETYPE>
                                             extends AbstractWALDAO <IMPLTYPE> implements IMapBasedDAO <INTERFACETYPE>
{
  private final String m_sXMLItemElementName;
  private final Map <String, IMPLTYPE> m_aMap = new HashMap <> ();

  public AbstractMapBasedWALDAO (@Nonnull final Class <IMPLTYPE> aImplClass,
                                 @Nonnull @Nonempty final String sFilename,
                                 @Nonnull @Nonempty final String sXMLItemElementName) throws DAOException
  {
    super (aImplClass, sFilename);
    m_sXMLItemElementName = ValueEnforcer.notEmpty (sXMLItemElementName, "XMLItemElementName");
    initialRead ();
  }

  @Override
  protected void onRecoveryCreate (@Nonnull final IMPLTYPE aItem)
  {
    internalAddItem (aItem);
  }

  @Override
  protected void onRecoveryUpdate (@Nonnull final IMPLTYPE aItem)
  {
    internalAddItem (aItem);
  }

  @Override
  protected void onRecoveryDelete (@Nonnull final IMPLTYPE aItem)
  {
    m_aMap.remove (aItem.getID (), aItem);
  }

  @Override
  @Nonnull
  protected final EChange onRead (@Nonnull final IMicroDocument aDoc)
  {
    for (final IMicroElement eItem : aDoc.getDocumentElement ().getAllChildElements (m_sXMLItemElementName))
      internalAddItem (MicroTypeConverter.convertToNative (eItem, getDataTypeClass ()));
    return EChange.UNCHANGED;
  }

  @Override
  @Nonnull
  protected final IMicroDocument createWriteData ()
  {
    final IMicroDocument aDoc = new MicroDocument ();
    final IMicroElement eRoot = aDoc.appendElement ("root");
    for (final IMPLTYPE aItem : CollectionHelper.getSortedByKey (m_aMap).values ())
      eRoot.appendChild (MicroTypeConverter.convertToMicroElement (aItem, m_sXMLItemElementName));
    return aDoc;
  }

  @MustBeLocked (ELockType.WRITE)
  protected final void internalAddItem (@Nonnull final IMPLTYPE aItem)
  {
    ValueEnforcer.notNull (aItem, "Item");

    final String sID = aItem.getID ();
    if (m_aMap.containsKey (sID))
      throw new IllegalArgumentException (ClassHelper.getClassLocalName (getDataTypeClass ()) +
                                          " with ID '" +
                                          sID +
                                          "' is already in use!");
    m_aMap.put (sID, aItem);
  }

  @MustBeLocked (ELockType.WRITE)
  protected final IMPLTYPE internalRemoveItem (@Nullable final String sID)
  {
    if (StringHelper.hasNoText (sID))
      return null;

    return m_aMap.remove (sID);
  }

  @MustBeLocked (ELockType.WRITE)
  protected final void internalRemoveAllItems ()
  {
    m_aMap.clear ();
  }

  @Nonnull
  @ReturnsMutableCopy
  public final Collection <? extends INTERFACETYPE> getNone ()
  {
    return new ArrayList <> ();
  }

  @Nonnull
  @ReturnsMutableCopy
  public final Collection <? extends INTERFACETYPE> getAll ()
  {
    return m_aRWLock.readLocked ( () -> CollectionHelper.newList (m_aMap.values ()));
  }

  @Nonnull
  @ReturnsMutableCopy
  public final Collection <? extends INTERFACETYPE> getAll (@Nullable final Predicate <INTERFACETYPE> aFilter)
  {
    return m_aRWLock.readLocked ( () -> CollectionHelper.getAll (m_aMap.values (), aFilter));
  }

  @Nonnull
  @ReturnsMutableCopy
  public final <RETTYPE> Collection <RETTYPE> getAll (@Nullable final Predicate <INTERFACETYPE> aFilter,
                                                      @Nonnull final Function <INTERFACETYPE, RETTYPE> aMapper)
  {
    return m_aRWLock.readLocked ( () -> CollectionHelper.getAll (m_aMap.values (), aFilter, aMapper));
  }

  @Nullable
  public final INTERFACETYPE getFirst (@Nullable final Predicate <INTERFACETYPE> aFilter)
  {
    return m_aRWLock.readLocked ( () -> CollectionHelper.findFirst (m_aMap.values (), aFilter));
  }

  @Nullable
  public final <RETTYPE> RETTYPE getFirst (@Nullable final Predicate <INTERFACETYPE> aFilter,
                                           @Nonnull final Function <INTERFACETYPE, RETTYPE> aMapper)
  {
    return m_aRWLock.readLocked ( () -> CollectionHelper.findFirst (m_aMap.values (), aFilter, aMapper));
  }

  public final boolean containsAny (@Nullable final Predicate <INTERFACETYPE> aFilter)
  {
    return m_aRWLock.readLocked ( () -> CollectionHelper.containsAny (m_aMap.values (), aFilter));
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

  @Nonnegative
  public final int getCount ()
  {
    return m_aRWLock.readLocked ( () -> m_aMap.size ());
  }

  @Nonnegative
  public final int getCount (@Nullable final Predicate <INTERFACETYPE> aFilter)
  {
    return m_aRWLock.readLocked ( () -> CollectionHelper.getCount (m_aMap.values (), aFilter));
  }
}
