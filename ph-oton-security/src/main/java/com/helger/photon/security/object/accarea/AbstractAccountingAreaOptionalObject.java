/*
 * Copyright (C) 2014-2025 Philip Helger (www.helger.com)
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

import java.time.LocalDateTime;
import java.util.Map;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.concurrent.Immutable;

import com.helger.commons.ValueEnforcer;
import com.helger.commons.annotation.Nonempty;
import com.helger.commons.equals.EqualsHelper;
import com.helger.commons.hashcode.HashCodeGenerator;
import com.helger.commons.hashcode.IHashCodeGenerator;
import com.helger.commons.string.ToStringGenerator;
import com.helger.tenancy.AbstractBusinessObject;
import com.helger.tenancy.IBusinessObject;
import com.helger.tenancy.accarea.IAccountingArea;
import com.helger.tenancy.accarea.IAccountingAreaObject;
import com.helger.tenancy.tenant.ITenant;

/**
 * Like {@link AbstractAccountingAreaObject} but with an optional accounting
 * area
 *
 * @author Philip Helger
 */
@Immutable
public abstract class AbstractAccountingAreaOptionalObject extends AbstractBusinessObject implements IAccountingAreaObject
{
  private final ITenant m_aTenant;
  private final IAccountingArea m_aAccountingArea;
  // Status vars
  private int m_nHashCode = IHashCodeGenerator.ILLEGAL_HASHCODE;

  protected AbstractAccountingAreaOptionalObject (@Nonnull final IAccountingAreaObject aOther)
  {
    super (aOther);
    m_aTenant = aOther.getTenant ();
    m_aAccountingArea = aOther.getAccountingArea ();
  }

  public AbstractAccountingAreaOptionalObject (@Nonnull final IAccountingArea aAccountingArea, @Nonnull final IBusinessObject aObject)
  {
    this (aAccountingArea.getTenant (), aAccountingArea, aObject);
  }

  public AbstractAccountingAreaOptionalObject (@Nonnull final ITenant aTenant,
                                               @Nullable final IAccountingArea aAccountingArea,
                                               @Nonnull final IBusinessObject aObject)
  {
    super (aObject);
    ValueEnforcer.notNull (aTenant, "Tenant");
    if (aAccountingArea != null && !aAccountingArea.hasSameTenant (aTenant))
      throw new IllegalArgumentException ("The passed accounting area '" +
                                          aAccountingArea.getID () +
                                          "' does not belong to the passed tenant '" +
                                          aTenant.getID () +
                                          "'!");

    m_aTenant = aTenant;
    m_aAccountingArea = aAccountingArea;
  }

  public AbstractAccountingAreaOptionalObject (@Nonnull final ITenant aTenant,
                                               @Nullable final IAccountingArea aAccountingArea,
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
           sDeletionUserID,
           (Map <String, String>) null);
    ValueEnforcer.notNull (aTenant, "Tenant");
    if (aAccountingArea != null && !aAccountingArea.hasSameTenant (aTenant))
      throw new IllegalArgumentException ("The passed accounting area '" +
                                          aAccountingArea.getID () +
                                          "' does not belong to the passed tenant '" +
                                          aTenant.getID () +
                                          "'!");

    m_aTenant = aTenant;
    m_aAccountingArea = aAccountingArea;
  }

  @Nonnull
  public final ITenant getTenant ()
  {
    return m_aTenant;
  }

  @Nullable
  @Override
  public final String getAccountingAreaID ()
  {
    return m_aAccountingArea == null ? null : m_aAccountingArea.getID ();
  }

  @Nullable
  public final IAccountingArea getAccountingArea ()
  {
    return m_aAccountingArea;
  }

  public final boolean hasSameTenantAndAccountingAreaID (@Nullable final IAccountingArea aAccountingArea)
  {
    return aAccountingArea != null &&
           hasSameTenantID (aAccountingArea) &&
           (m_aAccountingArea == null || m_aAccountingArea.getID ().equals (aAccountingArea.getID ()));
  }

  public final boolean hasSameTenantAndAccountingAreaID (@Nullable final IAccountingAreaObject aAccountingAreaObject)
  {
    return aAccountingAreaObject != null &&
           hasSameTenantID (aAccountingAreaObject) &&
           (m_aAccountingArea == null || m_aAccountingArea.getID ().equals (aAccountingAreaObject.getAccountingAreaID ()));
  }

  @Override
  public final boolean equals (final Object o)
  {
    if (o == this)
      return true;
    if (o == null || !getClass ().equals (o.getClass ()))
      return false;
    final AbstractAccountingAreaOptionalObject rhs = (AbstractAccountingAreaOptionalObject) o;
    return m_aTenant.equals (rhs.m_aTenant) &&
           EqualsHelper.equals (m_aAccountingArea, rhs.m_aAccountingArea) &&
           getID ().equals (rhs.getID ());
  }

  @Override
  public final int hashCode ()
  {
    int ret = m_nHashCode;
    if (ret == IHashCodeGenerator.ILLEGAL_HASHCODE)
      ret = m_nHashCode = new HashCodeGenerator (this).append (m_aTenant).append (m_aAccountingArea).append (getID ()).getHashCode ();
    return ret;
  }

  @Override
  public String toString ()
  {
    return ToStringGenerator.getDerived (super.toString ())
                            .append ("Tenant", m_aTenant)
                            .append ("AccoutingArea", m_aAccountingArea)
                            .getToString ();
  }
}
