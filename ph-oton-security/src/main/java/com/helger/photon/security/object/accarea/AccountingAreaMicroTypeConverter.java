/*
 * Copyright (C) 2014-2023 Philip Helger (www.helger.com)
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

import com.helger.commons.annotation.Nonempty;
import com.helger.commons.system.SystemHelper;
import com.helger.masterdata.address.PostalAddress;
import com.helger.masterdata.currency.ECurrency;
import com.helger.photon.security.object.AbstractBusinessObjectMicroTypeConverter;
import com.helger.tenancy.tenant.ITenant;
import com.helger.tenancy.tenant.ITenantResolver;
import com.helger.xml.microdom.IMicroElement;
import com.helger.xml.microdom.IMicroQName;
import com.helger.xml.microdom.MicroElement;
import com.helger.xml.microdom.MicroQName;
import com.helger.xml.microdom.convert.MicroTypeConverter;

public final class AccountingAreaMicroTypeConverter extends AbstractBusinessObjectMicroTypeConverter <AccountingArea>
{
  private static final IMicroQName ATTR_TENANT_ID = new MicroQName ("clientid");
  private static final IMicroQName ATTR_DISPLAYNAME = new MicroQName ("displayname");
  private static final IMicroQName ATTR_COMPANY_TYPE = new MicroQName ("companytype");
  private static final IMicroQName ATTR_COMPANY_VATIN = new MicroQName ("companyvatin");
  private static final IMicroQName ATTR_COMPANY_NUMBER = new MicroQName ("companynumber");
  private static final IMicroQName ATTR_CUSTOMER_NUMBER = new MicroQName ("customernumber");
  private static final String ELEMENT_ADDRESS = "address";
  private static final IMicroQName ATTR_TELEPHONE = new MicroQName ("telephone");
  private static final IMicroQName ATTR_FAX = new MicroQName ("fax");
  private static final IMicroQName ATTR_EMAILADDRESS = new MicroQName ("emailaddress");
  private static final IMicroQName ATTR_WEBSITE = new MicroQName ("website");
  private static final IMicroQName ATTR_DEFAULTCURRENCY = new MicroQName ("defaultcurrency");
  private static final IMicroQName ATTR_OFFICE_LOCATION = new MicroQName ("officelocation");
  private static final IMicroQName ATTR_COMMERCIAL_REGISTRATION_NUMBER = new MicroQName ("commregno");
  private static final IMicroQName ATTR_COMMERCIAL_COURT = new MicroQName ("commcourt");

  private final ITenantResolver m_aTenantResolver;

  public AccountingAreaMicroTypeConverter (@Nonnull final ITenantResolver aTenantResolver)
  {
    m_aTenantResolver = aTenantResolver;
  }

  @Nonnull
  public IMicroElement convertToMicroElement (@Nonnull final AccountingArea aValue,
                                              @Nullable final String sNamespaceURI,
                                              @Nonnull @Nonempty final String sTagName)
  {
    final IMicroElement aElement = new MicroElement (sNamespaceURI, sTagName);
    aElement.setAttribute (ATTR_TENANT_ID, aValue.getTenantID ());
    setObjectFields (aValue, aElement);
    aElement.setAttribute (ATTR_DISPLAYNAME, aValue.getDisplayName ());
    aElement.setAttribute (ATTR_COMPANY_TYPE, aValue.getCompanyType ());
    aElement.setAttribute (ATTR_COMPANY_VATIN, aValue.getCompanyVATIN ());
    aElement.setAttribute (ATTR_COMPANY_NUMBER, aValue.getCompanyNumber ());
    aElement.setAttribute (ATTR_CUSTOMER_NUMBER, aValue.getCustomerNumber ());
    aElement.appendChild (MicroTypeConverter.convertToMicroElement (aValue.getAddress (), sNamespaceURI, ELEMENT_ADDRESS));
    aElement.setAttribute (ATTR_TELEPHONE, aValue.getTelephone ());
    aElement.setAttribute (ATTR_FAX, aValue.getFax ());
    aElement.setAttribute (ATTR_EMAILADDRESS, aValue.getEmailAddress ());
    aElement.setAttribute (ATTR_WEBSITE, aValue.getWebSite ());
    aElement.setAttribute (ATTR_DEFAULTCURRENCY, aValue.getDefaultCurrencyID ());
    aElement.setAttribute (ATTR_OFFICE_LOCATION, aValue.getOfficeLocation ());
    aElement.setAttribute (ATTR_COMMERCIAL_REGISTRATION_NUMBER, aValue.getCommercialRegistrationNumber ());
    aElement.setAttribute (ATTR_COMMERCIAL_COURT, aValue.getCommercialCourt ());
    return aElement;
  }

  @Nonnull
  public AccountingArea convertToNative (@Nonnull final IMicroElement aElement)
  {
    final Locale aLocale = SystemHelper.getSystemLocale ();
    final String sTenantID = aElement.getAttributeValue (ATTR_TENANT_ID);
    final ITenant aTenant = m_aTenantResolver.getTenantOfID (sTenantID);
    if (aTenant == null)
      throw new IllegalStateException ("Failed to resolve tenant ID '" + sTenantID + "'");

    final String sDisplayName = aElement.getAttributeValue (ATTR_DISPLAYNAME);
    final String sCompanyType = aElement.getAttributeValue (ATTR_COMPANY_TYPE);
    final String sCompanyVATIN = aElement.getAttributeValue (ATTR_COMPANY_VATIN);
    final String sCompanyNumber = aElement.getAttributeValue (ATTR_COMPANY_NUMBER);
    final String sCustomerNumber = aElement.getAttributeValue (ATTR_CUSTOMER_NUMBER);
    final PostalAddress aAddress = MicroTypeConverter.convertToNative (aElement.getFirstChildElement (ELEMENT_ADDRESS),
                                                                       PostalAddress.class);
    final String sTelephone = aElement.getAttributeValue (ATTR_TELEPHONE);
    final String sFax = aElement.getAttributeValue (ATTR_FAX);
    final String sEmailAddress = aElement.getAttributeValue (ATTR_EMAILADDRESS);
    final String sWebSite = aElement.getAttributeValue (ATTR_WEBSITE);
    final String sDefaultCurrency = aElement.getAttributeValue (ATTR_DEFAULTCURRENCY);
    final ECurrency eDefaultCurrency = ECurrency.getFromIDOrNull (sDefaultCurrency);

    final String sOfficeLocation = aElement.getAttributeValue (ATTR_OFFICE_LOCATION);
    final String sCommercialRegistrationNumber = aElement.getAttributeValue (ATTR_COMMERCIAL_REGISTRATION_NUMBER);
    final String sCommercialCourt = aElement.getAttributeValue (ATTR_COMMERCIAL_COURT);

    return new AccountingArea (aTenant,
                               getStubObject (aElement),
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
                               aLocale);
  }
}
