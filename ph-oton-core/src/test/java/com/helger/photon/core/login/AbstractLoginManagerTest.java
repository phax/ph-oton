/*
 * Copyright (C) 2014-2026 Philip Helger (www.helger.com)
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
package com.helger.photon.core.login;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import org.junit.Test;

/**
 * Test class for class {@link AbstractLoginManager}
 *
 * @author Philip Helger
 */
public final class AbstractLoginManagerTest
{
  @Test
  public void testXForwardedForEmpty ()
  {
    assertNull (AbstractLoginManager.getFirstIPFromXForwardedForValue (null));
    assertNull (AbstractLoginManager.getFirstIPFromXForwardedForValue (""));
    assertNull (AbstractLoginManager.getFirstIPFromXForwardedForValue ("   "));
    // Empty first (left-most) entry
    assertNull (AbstractLoginManager.getFirstIPFromXForwardedForValue (","));
    assertNull (AbstractLoginManager.getFirstIPFromXForwardedForValue (", 70.41.3.18"));
    assertNull (AbstractLoginManager.getFirstIPFromXForwardedForValue ("  , 70.41.3.18"));
  }

  @Test
  public void testXForwardedForSingle ()
  {
    assertEquals ("203.0.113.195", AbstractLoginManager.getFirstIPFromXForwardedForValue ("203.0.113.195"));
    // Surrounding whitespace is trimmed
    assertEquals ("203.0.113.195", AbstractLoginManager.getFirstIPFromXForwardedForValue ("  203.0.113.195  "));
    assertEquals ("203.0.113.195", AbstractLoginManager.getFirstIPFromXForwardedForValue ("\t203.0.113.195\t"));
    // A trailing comma does not hurt
    assertEquals ("203.0.113.195", AbstractLoginManager.getFirstIPFromXForwardedForValue ("203.0.113.195,"));
  }

  @Test
  public void testXForwardedForMultiple ()
  {
    // Left-most entry is the original client
    assertEquals ("203.0.113.195",
                  AbstractLoginManager.getFirstIPFromXForwardedForValue ("203.0.113.195, 70.41.3.18, 150.172.238.178"));
    assertEquals ("203.0.113.195",
                  AbstractLoginManager.getFirstIPFromXForwardedForValue ("203.0.113.195,70.41.3.18,150.172.238.178"));
    assertEquals ("203.0.113.195",
                  AbstractLoginManager.getFirstIPFromXForwardedForValue ("  203.0.113.195 , 70.41.3.18 "));
  }

  @Test
  public void testXForwardedForIPv6 ()
  {
    // IPv6 is not bracketed and carries no port in X-Forwarded-For
    assertEquals ("2001:db8::17", AbstractLoginManager.getFirstIPFromXForwardedForValue ("2001:db8::17"));
    assertEquals ("2001:db8::17", AbstractLoginManager.getFirstIPFromXForwardedForValue ("2001:db8::17, 70.41.3.18"));
  }

  @Test
  public void testForwardedEmpty ()
  {
    assertNull (AbstractLoginManager.getFirstIPFromForwardedValue (null));
    assertNull (AbstractLoginManager.getFirstIPFromForwardedValue (""));
    assertNull (AbstractLoginManager.getFirstIPFromForwardedValue ("   "));
  }

  @Test
  public void testForwardedNoForParameter ()
  {
    assertNull (AbstractLoginManager.getFirstIPFromForwardedValue ("proto=http;by=203.0.113.43"));
    assertNull (AbstractLoginManager.getFirstIPFromForwardedValue ("by=203.0.113.43"));
    // "for" only as a substring of another token must not match
    assertNull (AbstractLoginManager.getFirstIPFromForwardedValue ("before=192.0.2.60"));
    // Empty "for" value
    assertNull (AbstractLoginManager.getFirstIPFromForwardedValue ("for="));
    assertNull (AbstractLoginManager.getFirstIPFromForwardedValue ("for=\"\""));
  }

  @Test
  public void testForwardedObfuscated ()
  {
    // RFC 7239 allows "unknown" and "_"-prefixed obfuscated identifiers - not real IPs
    assertNull (AbstractLoginManager.getFirstIPFromForwardedValue ("for=unknown"));
    assertNull (AbstractLoginManager.getFirstIPFromForwardedValue ("for=Unknown"));
    assertNull (AbstractLoginManager.getFirstIPFromForwardedValue ("for=\"unknown\""));
    assertNull (AbstractLoginManager.getFirstIPFromForwardedValue ("for=_hidden"));
    assertNull (AbstractLoginManager.getFirstIPFromForwardedValue ("for=_gazonk;proto=http"));
  }

  @Test
  public void testForwardedIPv4 ()
  {
    assertEquals ("192.0.2.60", AbstractLoginManager.getFirstIPFromForwardedValue ("for=192.0.2.60"));
    // Additional parameters after "for"
    assertEquals ("192.0.2.60",
                  AbstractLoginManager.getFirstIPFromForwardedValue ("for=192.0.2.60;proto=http;by=203.0.113.43"));
    // "for" is not the first parameter of the element
    assertEquals ("192.0.2.60",
                  AbstractLoginManager.getFirstIPFromForwardedValue ("by=203.0.113.43;for=192.0.2.60;proto=http"));
    // Case insensitive parameter name
    assertEquals ("192.0.2.60", AbstractLoginManager.getFirstIPFromForwardedValue ("For=192.0.2.60"));
    assertEquals ("192.0.2.60", AbstractLoginManager.getFirstIPFromForwardedValue ("FOR=192.0.2.60"));
    // Whitespace around the element and value
    assertEquals ("192.0.2.60", AbstractLoginManager.getFirstIPFromForwardedValue ("  for=192.0.2.60  "));
    // Quoted value
    assertEquals ("192.0.2.60", AbstractLoginManager.getFirstIPFromForwardedValue ("for=\"192.0.2.60\""));
  }

  @Test
  public void testForwardedIPv4WithPort ()
  {
    assertEquals ("192.0.2.60", AbstractLoginManager.getFirstIPFromForwardedValue ("for=192.0.2.60:1234"));
    assertEquals ("192.0.2.60", AbstractLoginManager.getFirstIPFromForwardedValue ("for=\"192.0.2.60:1234\""));
    assertEquals ("192.0.2.60", AbstractLoginManager.getFirstIPFromForwardedValue ("For=\"192.0.2.60:1234\";proto=http"));
  }

  @Test
  public void testForwardedIPv6 ()
  {
    // IPv6 in square brackets, with port
    assertEquals ("2001:db8:cafe::17",
                  AbstractLoginManager.getFirstIPFromForwardedValue ("for=\"[2001:db8:cafe::17]:4711\""));
    // IPv6 in square brackets, without port
    assertEquals ("2001:db8::17", AbstractLoginManager.getFirstIPFromForwardedValue ("for=\"[2001:db8::17]\""));
    // Unquoted brackets are tolerated
    assertEquals ("2001:db8::17", AbstractLoginManager.getFirstIPFromForwardedValue ("for=[2001:db8::17]"));
    // Broken bracket -> nothing usable
    assertNull (AbstractLoginManager.getFirstIPFromForwardedValue ("for=\"[\""));
    assertNull (AbstractLoginManager.getFirstIPFromForwardedValue ("for=\"[]\""));
  }

  @Test
  public void testForwardedMultipleElements ()
  {
    // The first forwarded element (the original client) is used
    assertEquals ("192.0.2.43", AbstractLoginManager.getFirstIPFromForwardedValue ("for=192.0.2.43, for=198.51.100.17"));
    assertEquals ("192.0.2.43",
                  AbstractLoginManager.getFirstIPFromForwardedValue ("for=192.0.2.43;proto=http, for=198.51.100.17;proto=https"));
    // If the first element has no usable "for", later elements are NOT consulted
    assertNull (AbstractLoginManager.getFirstIPFromForwardedValue ("for=unknown, for=198.51.100.17"));
    assertNull (AbstractLoginManager.getFirstIPFromForwardedValue ("proto=http, for=198.51.100.17"));
  }
}
