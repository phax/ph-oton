/**
 * Copyright (C) 2014-2020 Philip Helger (www.helger.com)
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

import java.util.Locale;

import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.helger.commons.annotation.Nonempty;
import com.helger.commons.annotation.ReturnsMutableCopy;
import com.helger.commons.collection.impl.ICommonsList;
import com.helger.commons.state.EChange;
import com.helger.commons.string.StringHelper;
import com.helger.dao.DAOException;
import com.helger.masterdata.address.IPostalAddress;
import com.helger.masterdata.currency.ECurrency;
import com.helger.photon.app.dao.AbstractPhotonMapBasedWALDAO;
import com.helger.photon.audit.AuditHelper;
import com.helger.photon.security.object.BusinessObjectHelper;
import com.helger.tenancy.accarea.IAccountingArea;
import com.helger.tenancy.accarea.IAccountingAreaResolver;
import com.helger.tenancy.tenant.ITenant;

/**
 * Manages all available accounting areas.
 *
 * @author Philip Helger
 */
public class AccountingAreaManager extends AbstractPhotonMapBasedWALDAO <IAccountingArea, AccountingArea> implements IAccountingAreaResolver
{
  public AccountingAreaManager (@Nonnull @Nonempty final String sFilename) throws DAOException
  {
    super (AccountingArea.class, sFilename);
  }

  @Nonnull
  public IAccountingArea createAccountingArea (@Nonnull final ITenant aTenant,
                                               @Nonnull @Nonempty final String sDisplayName,
                                               @Nullable final String sCompanyType,
                                               @Nullable final String sCompanyVATIN,
                                               @Nullable final String sCompanyNumber,
                                               @Nullable final String sCustomerNumber,
                                               @Nonnull final IPostalAddress aAddress,
                                               @Nonnull final String sTelephone,
                                               @Nullable final String sFax,
                                               @Nullable final String sEmailAddress,
                                               @Nullable final String sWebSite,
                                               @Nonnull final ECurrency eDefaultCurrency,
                                               @Nullable final String sOfficeLocation,
                                               @Nullable final String sCommercialRegistrationNumber,
                                               @Nullable final String sCommercialCourt,
                                               @Nonnull final Locale aDisplayLocale)
  {
    final AccountingArea aAccountingArea = new AccountingArea (aTenant,
                                                               sDisplayName,
                                                               sCompanyType,
                                                               sCompanyVATIN,
                                                               sCompanyNumber,
                                                               sCustomerNumber,
                                                               aAddress,
                                                               sTelephone,
                                                               sFax,
                                                               sEmailAddress,
                                                               sWebSite,
                                                               eDefaultCurrency,
                                                               sOfficeLocation,
                                                               sCommercialRegistrationNumber,
                                                               sCommercialCourt,
                                                               aDisplayLocale);

    // Store
    m_aRWLock.writeLockedGet ( () -> internalCreateItem (aAccountingArea));
    AuditHelper.onAuditCreateSuccess (AccountingArea.OT,
                                      aAccountingArea.getID (),
                                      sDisplayName,
                                      sCompanyType,
                                      sCompanyVATIN,
                                      sCompanyNumber,
                                      sCustomerNumber,
                                      aAddress,
                                      sTelephone,
                                      sFax,
                                      sEmailAddress,
                                      eDefaultCurrency,
                                      sOfficeLocation,
                                      sCommercialRegistrationNumber,
                                      sCommercialCourt);

    return aAccountingArea;
  }

  @Nonnull
  public EChange updateAccountingArea (@Nonnull @Nonempty final String sAccountingAreaID,
                                       @Nonnull @Nonempty final String sDisplayName,
                                       @Nullable final String sCompanyType,
                                       @Nullable final String sCompanyVATIN,
                                       @Nullable final String sCompanyNumber,
                                       @Nullable final String sCustomerNumber,
                                       @Nonnull final IPostalAddress aAddress,
                                       @Nonnull final String sTelephone,
                                       @Nullable final String sFax,
                                       @Nullable final String sEmailAddress,
                                       @Nullable final String sWebSite,
                                       @Nonnull final ECurrency eDefaultCurrency,
                                       @Nullable final String sOfficeLocation,
                                       @Nullable final String sCommercialRegistrationNumber,
                                       @Nullable final String sCommercialCourt,
                                       @Nonnull final Locale aDisplayLocale)
  {
    final AccountingArea aAccountingArea = getOfID (sAccountingAreaID);
    if (aAccountingArea == null)
    {
      AuditHelper.onAuditModifyFailure (AccountingArea.OT, sAccountingAreaID, "no-such-id");
      return EChange.UNCHANGED;
    }

    m_aRWLock.writeLock ().lock ();
    try
    {
      EChange eChange = EChange.UNCHANGED;
      eChange = eChange.or (aAccountingArea.setDisplayName (sDisplayName));
      eChange = eChange.or (aAccountingArea.setCompanyType (sCompanyType));
      eChange = eChange.or (aAccountingArea.setCompanyVATIN (sCompanyVATIN));
      eChange = eChange.or (aAccountingArea.setCompanyNumber (sCompanyNumber));
      eChange = eChange.or (aAccountingArea.setCustomerNumber (sCustomerNumber));
      eChange = eChange.or (aAccountingArea.setAddress (aAddress, aDisplayLocale));
      eChange = eChange.or (aAccountingArea.setTelephone (sTelephone));
      eChange = eChange.or (aAccountingArea.setFax (sFax));
      eChange = eChange.or (aAccountingArea.setEmailAddress (sEmailAddress));
      eChange = eChange.or (aAccountingArea.setWebSite (sWebSite));
      eChange = eChange.or (aAccountingArea.setDefaultCurrency (eDefaultCurrency));
      eChange = eChange.or (aAccountingArea.setOfficeLocation (sOfficeLocation));
      eChange = eChange.or (aAccountingArea.setCommercialRegistrationNumber (sCommercialRegistrationNumber));
      eChange = eChange.or (aAccountingArea.setCommercialCourt (sCommercialCourt));
      if (eChange.isUnchanged ())
        return EChange.UNCHANGED;

      BusinessObjectHelper.setLastModificationNow (aAccountingArea);
      internalUpdateItem (aAccountingArea);
    }
    finally
    {
      m_aRWLock.writeLock ().unlock ();
    }
    AuditHelper.onAuditModifySuccess (AccountingArea.OT,
                                      "all",
                                      sAccountingAreaID,
                                      sDisplayName,
                                      sCompanyType,
                                      sCompanyVATIN,
                                      sCompanyNumber,
                                      aAddress,
                                      sTelephone,
                                      sFax,
                                      sEmailAddress,
                                      eDefaultCurrency,
                                      sOfficeLocation,
                                      sCommercialRegistrationNumber,
                                      sCommercialCourt);

    return EChange.CHANGED;
  }

  @Nonnull
  public EChange deleteAccountingArea (@Nullable final String sAccountingAreaID)
  {
    final AccountingArea aDeletedAccountingArea = getOfID (sAccountingAreaID);
    if (aDeletedAccountingArea == null)
    {
      AuditHelper.onAuditDeleteFailure (AccountingArea.OT, "no-such-object-id", sAccountingAreaID);
      return EChange.UNCHANGED;
    }

    m_aRWLock.writeLock ().lock ();
    try
    {
      if (BusinessObjectHelper.setDeletionNow (aDeletedAccountingArea).isUnchanged ())
      {
        AuditHelper.onAuditDeleteFailure (AccountingArea.OT, "already-deleted", sAccountingAreaID);
        return EChange.UNCHANGED;
      }
      internalMarkItemDeleted (aDeletedAccountingArea);
    }
    finally
    {
      m_aRWLock.writeLock ().unlock ();
    }
    AuditHelper.onAuditDeleteSuccess (AccountingArea.OT, sAccountingAreaID);

    return EChange.CHANGED;
  }

  @Nonnull
  @ReturnsMutableCopy
  public ICommonsList <IAccountingArea> getAllAccountingAreasOfTenant (@Nullable final String sTenantID)
  {
    return getAll (x -> x.hasSameTenantID (sTenantID));
  }

  @Nonnull
  @ReturnsMutableCopy
  public ICommonsList <IAccountingArea> getAllAccountingAreasOfTenant (@Nullable final ITenant aTenant)
  {
    return getAll (x -> x.hasSameTenant (aTenant));
  }

  @Nonnegative
  public int getCountAccountingAreasOfTenant (@Nullable final ITenant aTenant)
  {
    return getCount (x -> x.hasSameTenant (aTenant));
  }

  @Nonnull
  @ReturnsMutableCopy
  public ICommonsList <String> getAllAccountingAreaIDsOfTenant (@Nullable final String sTenantID)
  {
    return getAllMapped (x -> x.hasSameTenantID (sTenantID), IAccountingArea::getID);
  }

  @Nonnull
  @ReturnsMutableCopy
  public ICommonsList <String> getAllAccountingAreaIDsOfTenant (@Nullable final ITenant aTenant)
  {
    return getAllMapped (x -> x.hasSameTenant (aTenant), IAccountingArea::getID);
  }

  @Nonnegative
  public int getCountAccountingAreaIDsOfTenant (@Nullable final ITenant aTenant)
  {
    return getCount (x -> x.hasSameTenant (aTenant));
  }

  @Nullable
  public IAccountingArea getAccountingAreaOfID (@Nullable final String sID)
  {
    // Return type
    return getOfID (sID);
  }

  @Nullable
  public IAccountingArea getAccountingAreaOfID (@Nullable final String sID, @Nullable final ITenant aTenant)
  {
    final IAccountingArea aAccountingArea = getAccountingAreaOfID (sID);
    return aAccountingArea != null && aAccountingArea.hasSameTenant (aTenant) ? aAccountingArea : null;
  }

  public boolean containsAccountingAreaWithID (@Nullable final String sID, @Nullable final ITenant aTenant)
  {
    return getAccountingAreaOfID (sID, aTenant) != null;
  }

  @Nullable
  public IAccountingArea getAccountingAreaOfName (@Nullable final String sName, @Nullable final ITenant aTenant)
  {
    if (StringHelper.hasNoText (sName) || aTenant == null)
      return null;
    return findFirst (a -> a.hasSameTenant (aTenant) && a.getDisplayName ().equals (sName));
  }
}
