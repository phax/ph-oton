/**
 * Copyright (C) 2014-2019 Philip Helger (www.helger.com)
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
package com.helger.html.hc.impl;

import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.concurrent.GuardedBy;
import javax.annotation.concurrent.NotThreadSafe;

import com.helger.commons.ValueEnforcer;
import com.helger.commons.annotation.ReturnsMutableCopy;
import com.helger.commons.collection.impl.CommonsArrayList;
import com.helger.commons.collection.impl.ICommonsList;
import com.helger.commons.concurrent.SimpleReadWriteLock;
import com.helger.commons.state.EChange;
import com.helger.commons.string.ToStringGenerator;
import com.helger.html.EHTMLVersion;
import com.helger.html.hc.IHCCustomizer;
import com.helger.html.hc.IHCHasChildrenMutable;
import com.helger.html.hc.IHCNode;

/**
 * An implementation of {@link IHCCustomizer} that handles a list of multiple
 * customizers.
 *
 * @author Philip Helger
 */
@NotThreadSafe
public class HCCustomizerList extends AbstractHCCustomizer
{
  private final SimpleReadWriteLock m_aRWLock = new SimpleReadWriteLock ();
  @GuardedBy ("m_aRWLock")
  private final ICommonsList <IHCCustomizer> m_aList = new CommonsArrayList<> ();

  public HCCustomizerList (@Nullable final IHCCustomizer... aCustomizers)
  {
    if (aCustomizers != null)
      for (final IHCCustomizer aCustomizer : aCustomizers)
        addCustomizer (aCustomizer);
  }

  public HCCustomizerList (@Nullable final Iterable <? extends IHCCustomizer> aCustomizers)
  {
    if (aCustomizers != null)
      for (final IHCCustomizer aCustomizer : aCustomizers)
        addCustomizer (aCustomizer);
  }

  @Nonnull
  public HCCustomizerList addCustomizer (@Nonnull final IHCCustomizer aCustomizer)
  {
    ValueEnforcer.notNull (aCustomizer, "Customizer");
    m_aRWLock.writeLocked ( () -> m_aList.add (aCustomizer));
    return this;
  }

  @Nonnull
  public EChange removeCustomizer (@Nullable final IHCCustomizer aCustomizer)
  {
    return m_aRWLock.writeLocked ( () -> m_aList.removeObject (aCustomizer));
  }

  @Nonnull
  public EChange removeAllCustomizersOfClass (@Nonnull final Class <? extends IHCCustomizer> aCustomizerClass)
  {
    ValueEnforcer.notNull (aCustomizerClass, "CustomizerClass");

    return m_aRWLock.writeLocked ( () -> {
      EChange eChange = EChange.UNCHANGED;
      for (final IHCCustomizer aCustomizer : m_aList.getClone ())
        if (aCustomizer.getClass ().equals (aCustomizerClass))
          if (m_aList.remove (aCustomizer))
            eChange = EChange.CHANGED;
      return eChange;
    });
  }

  @Nonnegative
  public int getCustomizerCount ()
  {
    return m_aRWLock.readLocked ( () -> m_aList.size ());
  }

  @Nonnull
  @ReturnsMutableCopy
  public ICommonsList <IHCCustomizer> getAllCustomizers ()
  {
    return m_aRWLock.readLocked ( () -> m_aList.getClone ());
  }

  public void customizeNode (@Nonnull final IHCNode aNode,
                             @Nonnull final EHTMLVersion eHTMLVersion,
                             @Nonnull final IHCHasChildrenMutable <?, ? super IHCNode> aTargetNode)
  {
    for (final IHCCustomizer aCustomizer : getAllCustomizers ())
      aCustomizer.customizeNode (aNode, eHTMLVersion, aTargetNode);
  }

  @Override
  public String toString ()
  {
    return ToStringGenerator.getDerived (super.toString ()).append ("List", m_aList).getToString ();
  }
}
