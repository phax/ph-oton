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
package com.helger.photon.basic.object.accarea;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.concurrent.Immutable;

import org.joda.time.DateTime;

import com.helger.commons.ValueEnforcer;
import com.helger.commons.annotations.Nonempty;
import com.helger.commons.equals.EqualsUtils;
import com.helger.commons.hash.HashCodeGenerator;
import com.helger.commons.string.ToStringGenerator;
import com.helger.photon.basic.object.AbstractBaseObject;
import com.helger.photon.basic.object.StubObject;
import com.helger.photon.basic.object.client.IClient;
import com.helger.photon.basic.object.client.IClientObject;

/**
 * Like {@link AbstractAccountingAreaObject} but with an optional accounting
 * area
 * 
 * @author Philip Helger
 */
@Immutable
public abstract class AbstractAccountingAreaOptionalObject extends AbstractBaseObject implements IAccountingAreaObject
{
  private final IClient m_aClient;
  private final IAccountingArea m_aAccountingArea;
  private Integer m_aHashCode;

  protected AbstractAccountingAreaOptionalObject (@Nonnull final IAccountingAreaObject aOther)
  {
    super (aOther);
    m_aClient = aOther.getClient ();
    m_aAccountingArea = aOther.getAccountingArea ();
  }

  public AbstractAccountingAreaOptionalObject (@Nonnull final IAccountingArea aAccountingArea,
                                               @Nonnull final StubObject aObject)
  {
    this (aAccountingArea.getClient (), aAccountingArea, aObject);
  }

  public AbstractAccountingAreaOptionalObject (@Nonnull final IClient aClient,
                                               @Nullable final IAccountingArea aAccountingArea,
                                               @Nonnull final StubObject aObject)
  {
    super (aObject);
    ValueEnforcer.notNull (aClient, "Client");
    if (aAccountingArea != null && !aAccountingArea.hasSameClient (aClient))
      throw new IllegalArgumentException ("The passed accounting area '" +
                                          aAccountingArea.getID () +
                                          "' does not belong to the passed client '" +
                                          aClient.getID () +
                                          "'!");

    m_aClient = aClient;
    m_aAccountingArea = aAccountingArea;
  }

  public AbstractAccountingAreaOptionalObject (@Nonnull final IClient aClient,
                                               @Nullable final IAccountingArea aAccountingArea,
                                               @Nonnull @Nonempty final String sID,
                                               @Nonnull final DateTime aCreationDT,
                                               @Nullable final String sCreationUserID,
                                               @Nullable final DateTime aLastModificationDT,
                                               @Nullable final String sLastModificationUserID,
                                               @Nullable final DateTime aDeletionDT,
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
    if (aAccountingArea != null && !aAccountingArea.hasSameClient (aClient))
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
    return getClientID ().equals (sClientID);
  }

  public boolean hasSameClient (@Nullable final IClient aClient)
  {
    return m_aClient.equals (aClient);
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
           (m_aAccountingArea == null || m_aAccountingArea.getID ()
                                                          .equals (aAccountingAreaObject.getAccountingAreaID ()));
  }

  @Override
  public final boolean equals (final Object o)
  {
    if (o == this)
      return true;
    if (o == null || !getClass ().equals (o.getClass ()))
      return false;
    final AbstractAccountingAreaOptionalObject rhs = (AbstractAccountingAreaOptionalObject) o;
    return m_aClient.equals (rhs.m_aClient) &&
           EqualsUtils.equals (m_aAccountingArea, rhs.m_aAccountingArea) &&
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
