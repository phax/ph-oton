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
package com.helger.photon.security.object.accarea;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.concurrent.Immutable;

import org.joda.time.LocalDateTime;

import com.helger.commons.ValueEnforcer;
import com.helger.commons.annotation.Nonempty;
import com.helger.commons.hashcode.HashCodeGenerator;
import com.helger.commons.string.ToStringGenerator;
import com.helger.photon.basic.object.AbstractBaseObject;
import com.helger.photon.basic.object.accarea.IAccountingArea;
import com.helger.photon.basic.object.accarea.IAccountingAreaObject;
import com.helger.photon.basic.object.client.IClient;
import com.helger.photon.basic.object.client.IClientObject;
import com.helger.photon.security.object.StubObject;

@Immutable
public abstract class AbstractAccountingAreaObject extends AbstractBaseObject implements IAccountingAreaObject
{
  private final IClient m_aClient;
  private final IAccountingArea m_aAccountingArea;
  private Integer m_aHashCode;

  protected AbstractAccountingAreaObject (@Nonnull final IAccountingAreaObject aOther)
  {
    super (aOther);
    m_aClient = aOther.getClient ();
    m_aAccountingArea = aOther.getAccountingArea ();
  }

  public AbstractAccountingAreaObject (@Nonnull final IAccountingArea aAccountingArea,
                                       @Nonnull final StubObject aObject)
  {
    this (aAccountingArea.getClient (), aAccountingArea, aObject);
  }

  public AbstractAccountingAreaObject (@Nonnull final IClient aClient,
                                       @Nonnull final IAccountingArea aAccountingArea,
                                       @Nonnull final StubObject aObject)
  {
    super (aObject);
    ValueEnforcer.notNull (aClient, "Client");
    ValueEnforcer.notNull (aAccountingArea, "AccountingArea");
    if (!aAccountingArea.hasSameClient (aClient))
      throw new IllegalArgumentException ("The passed accounting area '" +
                                          aAccountingArea.getID () +
                                          "' does not belong to the passed client '" +
                                          aClient.getID () +
                                          "'!");

    m_aClient = aClient;
    m_aAccountingArea = aAccountingArea;
  }

  public AbstractAccountingAreaObject (@Nonnull final IClient aClient,
                                       @Nonnull final IAccountingArea aAccountingArea,
                                       @Nonnull @Nonempty final String sID,
                                       @Nonnull final LocalDateTime aCreationDT,
                                       @Nullable final String sCreationUserID,
                                       @Nullable final LocalDateTime aLastModificationDT,
                                       @Nullable final String sLastModificationUserID,
                                       @Nullable final LocalDateTime aDeletionDT,
                                       @Nullable final String sDeletionUserID)
  {
    super (sID,
           aCreationDT,
           sCreationUserID,
           aLastModificationDT,
           sLastModificationUserID,
           aDeletionDT,
           sDeletionUserID);
    ValueEnforcer.notNull (aClient, "Client");
    ValueEnforcer.notNull (aAccountingArea, "AccountingArea");
    if (!aAccountingArea.hasSameClient (aClient))
      throw new IllegalArgumentException ("The passed accounting area '" +
                                          aAccountingArea.getID () +
                                          "' does not belong to the passed client '" +
                                          aClient.getID () +
                                          "'!");

    m_aClient = aClient;
    m_aAccountingArea = aAccountingArea;
  }

  @Nonnull
  @Nonempty
  public final String getClientID ()
  {
    return m_aClient.getID ();
  }

  @Nonnull
  public final IClient getClient ()
  {
    return m_aClient;
  }

  public final boolean hasSameClientID (@Nullable final IClientObject aClientObject)
  {
    return aClientObject != null && hasSameClientID (aClientObject.getClientID ());
  }

  public final boolean hasSameClientID (@Nullable final String sClientID)
  {
    return m_aClient.getID ().equals (sClientID);
  }

  public boolean hasSameClient (@Nullable final IClient aClient)
  {
    return m_aClient.equals (aClient);
  }

  @Nonnull
  @Nonempty
  public final String getAccountingAreaID ()
  {
    return m_aAccountingArea.getID ();
  }

  @Nonnull
  public final IAccountingArea getAccountingArea ()
  {
    return m_aAccountingArea;
  }

  public final boolean hasSameClientAndAccountingAreaID (@Nullable final IAccountingArea aAccountingArea)
  {
    return aAccountingArea != null &&
           hasSameClientID (aAccountingArea) &&
           m_aAccountingArea.getID ().equals (aAccountingArea.getID ());
  }

  public final boolean hasSameClientAndAccountingAreaID (@Nullable final IAccountingAreaObject aAccountingAreaObject)
  {
    return aAccountingAreaObject != null &&
           hasSameClientID (aAccountingAreaObject) &&
           m_aAccountingArea.getID ().equals (aAccountingAreaObject.getAccountingAreaID ());
  }

  @Override
  public final boolean equals (final Object o)
  {
    if (o == this)
      return true;
    if (o == null || !getClass ().equals (o.getClass ()))
      return false;
    final AbstractAccountingAreaObject rhs = (AbstractAccountingAreaObject) o;
    return m_aClient.equals (rhs.m_aClient) &&
           m_aAccountingArea.equals (rhs.m_aAccountingArea) &&
           getID ().equals (rhs.getID ());
  }

  @Override
  public final int hashCode ()
  {
    Integer aObj = m_aHashCode;
    if (aObj == null)
    {
      aObj = new HashCodeGenerator (this).append (m_aClient)
                                         .append (m_aAccountingArea)
                                         .append (getID ())
                                         .getHashCodeObj ();
      m_aHashCode = aObj;
    }
    return aObj.intValue ();
  }

  @Override
  public String toString ()
  {
    return ToStringGenerator.getDerived (super.toString ())
                            .append ("client", m_aClient)
                            .append ("accoutingArea", m_aAccountingArea)
                            .toString ();
  }
}
