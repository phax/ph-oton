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

import java.util.Locale;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.helger.commons.annotation.Nonempty;
import com.helger.commons.microdom.IMicroElement;
import com.helger.commons.microdom.MicroElement;
import com.helger.commons.microdom.convert.MicroTypeConverter;
import com.helger.commons.system.SystemHelper;
import com.helger.masterdata.address.Address;
import com.helger.masterdata.currency.ECurrency;
import com.helger.photon.security.object.AbstractObjectMicroTypeConverter;
import com.helger.photon.security.object.client.IClient;
import com.helger.photon.security.object.client.IClientResolver;

public final class AccountingAreaMicroTypeConverter extends AbstractObjectMicroTypeConverter
{
  private static final String ATTR_CLIENTID = "clientid";
  private static final String ATTR_DISPLAYNAME = "displayname";
  private static final String ATTR_COMPANYTYPE = "companytype";
  private static final String ATTR_COMPANYVATIN = "companyvatin";
  private static final String ATTR_COMPANYNUMBER = "companynumber";
  private static final String ELEMENT_ADDRESS = "address";
  private static final String ATTR_TELEPHONE = "telephone";
  private static final String ATTR_FAX = "fax";
  private static final String ATTR_EMAILADDRESS = "emailaddress";
  private static final String ATTR_WEBSITE = "website";
  private static final String ATTR_DEFAULTCURRENCY = "defaultcurrency";
  private static final String ATTR_OFFICE_LOCATION = "officelocation";
  private static final String ATTR_COMMERCIAL_REGISTRATION_NUMBER = "commregno";
  private static final String ATTR_COMMERCIAL_COURT = "commcourt";

  private final IClientResolver m_aClientResolver;

  public AccountingAreaMicroTypeConverter (@Nonnull final IClientResolver aClientResolver)
  {
    m_aClientResolver = aClientResolver;
  }

  @Nonnull
  public IMicroElement convertToMicroElement (@Nonnull final Object aObject,
                                              @Nullable final String sNamespaceURI,
                                              @Nonnull @Nonempty final String sTagName)
  {
    final IAccountingArea aValue = (IAccountingArea) aObject;
    final IMicroElement aElement = new MicroElement (sNamespaceURI, sTagName);
    aElement.setAttribute (ATTR_CLIENTID, aValue.getClientID ());
    setObjectFields (aValue, aElement);
    aElement.setAttribute (ATTR_DISPLAYNAME, aValue.getDisplayName ());
    aElement.setAttribute (ATTR_COMPANYTYPE, aValue.getCompanyType ());
    aElement.setAttribute (ATTR_COMPANYVATIN, aValue.getCompanyVATIN ());
    aElement.setAttribute (ATTR_COMPANYNUMBER, aValue.getCompanyNumber ());
    aElement.appendChild (MicroTypeConverter.convertToMicroElement (aValue.getAddress (),
                                                                    sNamespaceURI,
                                                                    ELEMENT_ADDRESS));
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
    final String sClientID = aElement.getAttributeValue (ATTR_CLIENTID);
    final IClient aClient = m_aClientResolver.getClientOfID (sClientID);
    if (aClient == null)
      throw new IllegalStateException ("Failed to resolve client ID '" + sClientID + "'");

    final String sDisplayName = aElement.getAttributeValue (ATTR_DISPLAYNAME);
    final String sCompanyType = aElement.getAttributeValue (ATTR_COMPANYTYPE);
    final String sCompanyVATIN = aElement.getAttributeValue (ATTR_COMPANYVATIN);
    final String sCompanyNumber = aElement.getAttributeValue (ATTR_COMPANYNUMBER);
    final Address aAddress = MicroTypeConverter.convertToNative (aElement.getFirstChildElement (ELEMENT_ADDRESS),
                                                                 Address.class);
    final String sTelephone = aElement.getAttributeValue (ATTR_TELEPHONE);
    final String sFax = aElement.getAttributeValue (ATTR_FAX);
    final String sEmailAddress = aElement.getAttributeValue (ATTR_EMAILADDRESS);
    final String sWebSite = aElement.getAttributeValue (ATTR_WEBSITE);
    final String sDefaultCurrency = aElement.getAttributeValue (ATTR_DEFAULTCURRENCY);
    final ECurrency eDefaultCurrency = ECurrency.getFromIDOrNull (sDefaultCurrency);

    final String sOfficeLocation = aElement.getAttributeValue (ATTR_OFFICE_LOCATION);
    final String sCommercialRegistrationNumber = aElement.getAttributeValue (ATTR_COMMERCIAL_REGISTRATION_NUMBER);
    final String sCommercialCourt = aElement.getAttributeValue (ATTR_COMMERCIAL_COURT);

    return new AccountingArea (aClient,
                               getStubObject (aElement),
                               sDisplayName,
                               sCompanyType,
                               sCompanyVATIN,
                               sCompanyNumber,
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
