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
package com.helger.photon.security.object.client;

import java.time.LocalDateTime;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.concurrent.Immutable;

import com.helger.commons.ValueEnforcer;
import com.helger.commons.annotation.Nonempty;
import com.helger.commons.hashcode.HashCodeGenerator;
import com.helger.commons.hashcode.IHashCodeGenerator;
import com.helger.commons.string.ToStringGenerator;
import com.helger.photon.basic.object.AbstractBaseObject;
import com.helger.photon.basic.object.client.IClient;
import com.helger.photon.basic.object.client.IClientObject;
import com.helger.photon.security.object.StubObject;

@Immutable
public abstract class AbstractClientObject extends AbstractBaseObject implements IClientObject
{
  private final IClient m_aClient;
  // Status vars
  private transient int m_nHashCode = IHashCodeGenerator.ILLEGAL_HASHCODE;

  protected AbstractClientObject (@Nonnull final IClientObject aBase)
  {
    super (aBase);
    m_aClient = aBase.getClient ();
    // Recalculate hash code, if this implementation class is different from the
    // passed implementation class
  }

  public AbstractClientObject (@Nonnull final IClient aClient, @Nonnull final StubObject aObject)
  {
    super (aObject);
    m_aClient = ValueEnforcer.notNull (aClient, "Client");
  }

  public AbstractClientObject (@Nonnull final IClient aClient,
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
    m_aClient = ValueEnforcer.notNull (aClient, "Client");
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

  @Override
  public final boolean equals (final Object o)
  {
    if (o == this)
      return true;
    if (o == null || !getClass ().equals (o.getClass ()))
      return false;
    final AbstractClientObject rhs = (AbstractClientObject) o;
    return m_aClient.equals (rhs.m_aClient) && getID ().equals (rhs.getID ());
  }

  @Override
  public final int hashCode ()
  {
    int ret = m_nHashCode;
    if (ret == IHashCodeGenerator.ILLEGAL_HASHCODE)
      ret = m_nHashCode = new HashCodeGenerator (this).append (m_aClient).append (getID ()).getHashCode ();
    return ret;
  }

  @Override
  public String toString ()
  {
    return ToStringGenerator.getDerived (super.toString ()).append ("client", m_aClient).toString ();
  }
}
