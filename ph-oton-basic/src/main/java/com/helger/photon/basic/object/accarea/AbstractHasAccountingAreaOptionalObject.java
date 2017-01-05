/**
 * Copyright (C) 2014-2017 Philip Helger (www.helger.com)
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
package com.helger.photon.basic.object.accarea;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.concurrent.Immutable;

import com.helger.commons.ValueEnforcer;
import com.helger.commons.equals.EqualsHelper;
import com.helger.commons.hashcode.HashCodeGenerator;
import com.helger.commons.string.ToStringGenerator;
import com.helger.photon.basic.object.client.AbstractHasClient;
import com.helger.photon.basic.object.client.IClient;

@Immutable
public abstract class AbstractHasAccountingAreaOptionalObject extends AbstractHasClient implements IHasAccountingArea
{
  private final IAccountingArea m_aAccountingArea;

  protected AbstractHasAccountingAreaOptionalObject (@Nonnull final IAccountingAreaObject aOther)
  {
    super (aOther.getClient ());
    m_aAccountingArea = aOther.getAccountingArea ();
  }

  public AbstractHasAccountingAreaOptionalObject (@Nonnull final IAccountingArea aAccountingArea)
  {
    this (aAccountingArea.getClient (), aAccountingArea);
  }

  public AbstractHasAccountingAreaOptionalObject (@Nonnull final IClient aClient,
                                                  @Nullable final IAccountingArea aAccountingArea)
  {
    super (aClient);
    ValueEnforcer.notNull (aAccountingArea, "AccountingArea");
    if (aAccountingArea != null && !aAccountingArea.hasSameClient (aClient))
      throw new IllegalArgumentException ("The passed accounting area '" +
                                          aAccountingArea.getID () +
                                          "' does not belong to the passed client '" +
                                          aClient.getID () +
                                          "'!");

    m_aAccountingArea = aAccountingArea;
  }

  @Nullable
  public final String getAccountingAreaID ()
  {
    return m_aAccountingArea == null ? null : m_aAccountingArea.getID ();
  }

  @Nullable
  public final IAccountingArea getAccountingArea ()
  {
    return m_aAccountingArea;
  }

  public final boolean hasSameClientAndAccountingAreaID (@Nullable final IAccountingArea aAccountingArea)
  {
    return aAccountingArea != null &&
           hasSameClientID (aAccountingArea) &&
           (m_aAccountingArea == null || m_aAccountingArea.getID ().equals (aAccountingArea.getID ()));
  }

  public final boolean hasSameClientAndAccountingAreaID (@Nullable final IAccountingAreaObject aAccountingAreaObject)
  {
    return aAccountingAreaObject != null &&
           hasSameClientID (aAccountingAreaObject) &&
           (m_aAccountingArea == null ||
            m_aAccountingArea.getID ().equals (aAccountingAreaObject.getAccountingAreaID ()));
  }

  @Override
  public boolean equals (final Object o)
  {
    if (o == this)
      return true;
    if (!super.equals (o))
      return false;
    final AbstractHasAccountingAreaOptionalObject rhs = (AbstractHasAccountingAreaOptionalObject) o;
    return EqualsHelper.equals (m_aAccountingArea, rhs.m_aAccountingArea);
  }

  @Override
  public int hashCode ()
  {
    return HashCodeGenerator.getDerived (super.hashCode ()).append (m_aAccountingArea).getHashCode ();
  }

  @Override
  public String toString ()
  {
    return ToStringGenerator.getDerived (super.toString ()).append ("accoutingArea", m_aAccountingArea).toString ();
  }
}
