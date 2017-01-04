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
package com.helger.photon.core.app.layout;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.concurrent.GuardedBy;
import javax.annotation.concurrent.ThreadSafe;

import com.helger.commons.ValueEnforcer;
import com.helger.commons.annotation.Nonempty;
import com.helger.commons.annotation.ReturnsMutableCopy;
import com.helger.commons.collection.ext.CommonsArrayList;
import com.helger.commons.collection.ext.CommonsLinkedHashMap;
import com.helger.commons.collection.ext.ICommonsList;
import com.helger.commons.collection.ext.ICommonsOrderedMap;
import com.helger.commons.concurrent.SimpleReadWriteLock;
import com.helger.commons.string.ToStringGenerator;
import com.helger.html.hc.IHCNode;
import com.helger.html.hc.html.metadata.HCHead;
import com.helger.photon.core.app.context.ILayoutExecutionContext;

/**
 * This class handles the mapping of the area ID to a content provider.
 *
 * @author Philip Helger
 * @param <LECTYPE>
 *        Layout execution context type
 */
@ThreadSafe
public class LayoutManagerProxy <LECTYPE extends ILayoutExecutionContext> implements ILayoutManager <LECTYPE>
{
  private final SimpleReadWriteLock m_aRWLock = new SimpleReadWriteLock ();
  @GuardedBy ("m_aRWLock")
  private final ICommonsOrderedMap <String, ILayoutAreaContentProvider <LECTYPE>> m_aContentProviders = new CommonsLinkedHashMap <> ();

  public LayoutManagerProxy ()
  {}

  public void registerAreaContentProvider (@Nonnull final String sAreaID,
                                           @Nonnull final ILayoutAreaContentProvider <LECTYPE> aContentProvider)
  {
    ValueEnforcer.notEmpty (sAreaID, "AreaID");
    ValueEnforcer.notNull (aContentProvider, "ContentProvider");

    m_aRWLock.writeLocked ( () -> {
      if (m_aContentProviders.containsKey (sAreaID))
        throw new IllegalArgumentException ("A content provider for the area ID '" +
                                            sAreaID +
                                            "' is already registered!");
      m_aContentProviders.put (sAreaID, aContentProvider);
    });
  }

  @Nonnull
  @ReturnsMutableCopy
  public ICommonsList <String> getAllAreaIDs ()
  {
    return m_aRWLock.readLocked ( () -> new CommonsArrayList <> (m_aContentProviders.keySet ()));
  }

  @Nullable
  public IHCNode getContentOfArea (@Nonnull @Nonempty final String sAreaID,
                                   @Nonnull final LECTYPE aLEC,
                                   @Nonnull final HCHead aHead)
  {
    ValueEnforcer.notNull (sAreaID, "AreaID");

    final ILayoutAreaContentProvider <LECTYPE> aContentProvider = m_aRWLock.readLocked ( () -> m_aContentProviders.get (sAreaID));
    return aContentProvider == null ? null : aContentProvider.getContent (aLEC, aHead);
  }

  @Override
  public String toString ()
  {
    return new ToStringGenerator (this).append ("contentProviders", m_aContentProviders).toString ();
  }
}
