/*
 * Copyright (C) 2014-2022 Philip Helger (www.helger.com)
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

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.concurrent.NotThreadSafe;

import com.helger.commons.annotation.Nonempty;
import com.helger.commons.type.ObjectType;
import com.helger.masterdata.address.IPostalAddress;
import com.helger.masterdata.currency.ECurrency;
import com.helger.photon.security.object.StubObject;
import com.helger.tenancy.accarea.AbstractAccountingArea;
import com.helger.tenancy.accarea.IAccountingArea;
import com.helger.tenancy.tenant.ITenant;

/**
 * Default implementation of {@link IAccountingArea}
 *
 * @author Philip Helger
 */
@NotThreadSafe
public class AccountingArea extends AbstractAccountingArea
{
  public static final ObjectType OT = new ObjectType ("accountingarea");

  /**
   * Constructor for new accounting area
   *
   * @param aTenant
   *        Tenant
   * @param sDisplayName
   *        display name
   * @param sCompanyType
   *        company type
   * @param sCompanyVATIN
   *        company VATIN
   * @param sCompanyNumber
   *        company number
   * @param sCustomerNumber
   *        Customer number
   * @param aAddress
   *        address
   * @param sTelephone
   *        Telephone number
   * @param sFax
   *        Fax number
   * @param sEmailAddress
   *        Email address
   * @param sWebSite
   *        Web site
   * @param eDefaultCurrency
   *        default currency
   * @param sOfficeLocation
   *        Office location
   * @param sCommercialRegistrationNumber
   *        Commercial registration number
   * @param sCommercialCourt
   *        Commercial court
   * @param aDisplayLocale
   *        The display locale to use. May not be <code>null</code>.
   */
  public AccountingArea (@Nonnull final ITenant aTenant,
                         @Nonnull @Nonempty final String sDisplayName,
                         @Nullable final String sCompanyType,
                         @Nullable final String sCompanyVATIN,
                         @Nullable final String sCompanyNumber,
                         @Nullable final String sCustomerNumber,
                         @Nonnull final IPostalAddress aAddress,
                         @Nullable final String sTelephone,
                         @Nullable final String sFax,
                         @Nullable final String sEmailAddress,
                         @Nullable final String sWebSite,
                         @Nullable final ECurrency eDefaultCurrency,
                         @Nullable final String sOfficeLocation,
                         @Nullable final String sCommercialRegistrationNumber,
                         @Nullable final String sCommercialCourt,
                         @Nonnull final Locale aDisplayLocale)
  {
    this (aTenant,
          StubObject.createForCurrentUser (),
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
  }

  AccountingArea (@Nonnull final ITenant aTenant,
                  @Nonnull final StubObject aStubObject,
                  @Nonnull @Nonempty final String sDisplayName,
                  @Nullable final String sCompanyType,
                  @Nullable final String sCompanyVATIN,
                  @Nullable final String sCompanyNumber,
                  @Nullable final String sCustomerNumber,
                  @Nonnull final IPostalAddress aAddress,
                  @Nullable final String sTelephone,
                  @Nullable final String sFax,
                  @Nullable final String sEmailAddress,
                  @Nullable final String sWebSite,
                  @Nullable final ECurrency eDefaultCurrency,
                  @Nullable final String sOfficeLocation,
                  @Nullable final String sCommercialRegistrationNumber,
                  @Nullable final String sCommercialCourt,
                  @Nonnull final Locale aDisplayLocale)
  {
    super (aTenant,
           aStubObject,
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
  }

  @Override
  @Nonnull
  public ObjectType getObjectType ()
  {
    return OT;
  }
}
