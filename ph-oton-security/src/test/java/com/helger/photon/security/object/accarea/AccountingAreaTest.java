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
package com.helger.photon.security.object.accarea;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;

import java.util.Locale;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestRule;

import com.helger.masterdata.address.EPostalAddressType;
import com.helger.masterdata.address.PostalAddress;
import com.helger.masterdata.currency.ECurrency;
import com.helger.photon.basic.mock.PhotonBasicWebTestRule;
import com.helger.photon.basic.object.tenant.ITenant;
import com.helger.photon.security.object.tenant.Tenant;
import com.helger.xml.mock.XMLTestHelper;

/**
 * Test class for class {@link AccountingArea}
 *
 * @author Philip Helger
 */
public final class AccountingAreaTest
{
  @Rule
  public final TestRule m_aRule = new PhotonBasicWebTestRule ();

  @Test
  public void testBasic ()
  {
    final ITenant aClient = new Tenant ("anyid", "Mock client");
    final PostalAddress aAddress = new PostalAddress (EPostalAddressType.PERSONAL,
                                          "AT",
                                          "Wien",
                                          "1234",
                                          "Vienna",
                                          "Test street",
                                          "123",
                                          "PO1",
                                          "co",
                                          Locale.GERMANY);
    final AccountingArea a = new AccountingArea (aClient,
                                                 "Accounting area 1",
                                                 "GmbH",
                                                 "ATU00000000",
                                                 "company number",
                                                 "debtor number",
                                                 aAddress,
                                                 "54321",
                                                 "12345",
                                                 "any@example.org",
                                                 "http://www.helger.com",
                                                 ECurrency.ISK,
                                                 "Wien",
                                                 "ABC",
                                                 "Wien2",
                                                 Locale.GERMANY);
    assertSame (aClient, a.getTenant ());
    assertEquals ("Accounting area 1", a.getDisplayName ());
    assertEquals ("GmbH", a.getCompanyType ());
    assertEquals ("ATU00000000", a.getCompanyVATIN ());
    assertEquals ("company number", a.getCompanyNumber ());
    assertEquals (aAddress, a.getAddress ());
    assertEquals ("54321", a.getTelephone ());
    assertEquals ("12345", a.getFax ());
    assertEquals ("any@example.org", a.getEmailAddress ());
    assertEquals ("http://www.helger.com", a.getWebSite ());
    assertSame (ECurrency.ISK, a.getDefaultCurrency ());
    assertEquals ("Wien", a.getOfficeLocation ());
    assertEquals ("ABC", a.getCommercialRegistrationNumber ());
    assertEquals ("Wien2", a.getCommercialCourt ());

    XMLTestHelper.testMicroTypeConversion (a);
  }
}
