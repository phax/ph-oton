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
package com.helger.photon.security.object.tenant;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.helger.commons.annotation.Nonempty;
import com.helger.commons.state.EChange;
import com.helger.dao.DAOException;
import com.helger.photon.app.dao.AbstractPhotonMapBasedWALDAO;
import com.helger.photon.audit.AuditHelper;
import com.helger.photon.security.object.BusinessObjectHelper;
import com.helger.tenancy.tenant.CTenant;
import com.helger.tenancy.tenant.ITenant;
import com.helger.tenancy.tenant.ITenantResolver;

/**
 * Manages all available tenants (before v8 called client).
 *
 * @author Philip Helger
 */
public class TenantManager extends AbstractPhotonMapBasedWALDAO <ITenant, Tenant> implements ITenantResolver
{
  public TenantManager (@Nonnull @Nonempty final String sFilename) throws DAOException
  {
    super (Tenant.class, sFilename);
  }

  @Override
  @Nonnull
  protected EChange onInit ()
  {
    internalCreateItem (new Tenant (CTenant.GLOBAL_TENANT_ID, CTenant.GLOBAL_TENANT_NAME));
    return EChange.CHANGED;
  }

  @Nullable
  public ITenant createTenant (@Nonnull @Nonempty final String sTenantID, @Nonnull @Nonempty final String sDisplayName)
  {
    if (containsWithID (sTenantID))
      return null;

    final Tenant aTenant = new Tenant (sTenantID, sDisplayName);

    m_aRWLock.writeLocked ( () -> {
      internalCreateItem (aTenant);
    });
    AuditHelper.onAuditCreateSuccess (Tenant.OT, aTenant.getID (), sDisplayName);

    return aTenant;
  }

  @Nonnull
  public EChange updateTenant (@Nonnull @Nonempty final String sTenantID, @Nonnull @Nonempty final String sDisplayName)
  {
    final Tenant aTenant = getOfID (sTenantID);
    if (aTenant == null)
    {
      AuditHelper.onAuditModifyFailure (Tenant.OT, sTenantID, "no-such-id");
      return EChange.UNCHANGED;
    }

    m_aRWLock.writeLock ().lock ();
    try
    {
      EChange eChange = EChange.UNCHANGED;
      eChange = eChange.or (aTenant.setDisplayName (sDisplayName));
      if (eChange.isUnchanged ())
        return EChange.UNCHANGED;

      BusinessObjectHelper.setLastModificationNow (aTenant);
      internalUpdateItem (aTenant);
    }
    finally
    {
      m_aRWLock.writeLock ().unlock ();
    }
    AuditHelper.onAuditModifySuccess (Tenant.OT, "all", sTenantID, sDisplayName);

    return EChange.CHANGED;
  }

  @Nonnull
  public EChange deleteTenant (@Nullable final String sTenantID)
  {
    final Tenant aDeletedObject = getOfID (sTenantID);
    if (aDeletedObject == null)
    {
      AuditHelper.onAuditDeleteFailure (Tenant.OT, "no-such-object-id", sTenantID);
      return EChange.UNCHANGED;
    }

    m_aRWLock.writeLock ().lock ();
    try
    {
      if (BusinessObjectHelper.setDeletionNow (aDeletedObject).isUnchanged ())
      {
        AuditHelper.onAuditDeleteFailure (Tenant.OT, "already-deleted", sTenantID);
        return EChange.UNCHANGED;
      }
      internalMarkItemDeleted (aDeletedObject);
    }
    finally
    {
      m_aRWLock.writeLock ().unlock ();
    }
    AuditHelper.onAuditDeleteSuccess (Tenant.OT, sTenantID);

    return EChange.CHANGED;
  }

  public boolean containsAnyExceptGlobal ()
  {
    return containsAny (x -> !x.isGlobalTenant ());
  }

  @Nullable
  public ITenant getTenantOfID (@Nullable final String sID)
  {
    return getOfID (sID);
  }
}
