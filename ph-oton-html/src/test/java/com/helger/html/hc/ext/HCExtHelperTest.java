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
package com.helger.html.hc.ext;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;

import com.helger.html.EHTMLElement;
import com.helger.html.hc.IHCNode;
import com.helger.html.hc.html.grouping.HCDiv;
import com.helger.html.hc.html.textlevel.HCBR;
import com.helger.html.hc.impl.HCTextNode;

/**
 * Test class for class {@link HCExtHelper}.
 *
 * @author Philip Helger
 */
public final class HCExtHelperTest
{
  private static final String HTML_STRING6 = "this is a \nlinebreak test.";
  private static final String HTML_STRING7 = "\nthis is a \nlinebreak test.\n";
  private static final String HTML_STRING8 = "\n";
  private static final String HTML_STRING9 = "\n\n\n";

  @Test
  public void testNl2brList ()
  {
    final List <IHCNode> ret = HCExtHelper.nl2brList ("a\nb");
    assertNotNull (ret);
    assertEquals (3, ret.size ());
    assertTrue (ret.get (0) instanceof HCTextNode);
    assertTrue (ret.get (1) instanceof HCBR);
    assertTrue (ret.get (2) instanceof HCTextNode);

    assertEquals (3, HCExtHelper.nl2brList (HTML_STRING6).size ());
    assertEquals (5, HCExtHelper.nl2brList (HTML_STRING7).size ());
    assertEquals (1, HCExtHelper.nl2brList (HTML_STRING8).size ());
    assertEquals (3, HCExtHelper.nl2brList (HTML_STRING9).size ());
  }

  @Test
  public void testNl2divList ()
  {
    List <HCDiv> ret = HCExtHelper.nl2divList ("a\nb");
    assertNotNull (ret);
    assertEquals (2, ret.size ());

    ret = HCExtHelper.nl2divList (HTML_STRING6);
    assertEquals (2, ret.size ());
    assertEquals ("this is a ", ret.get (0).getPlainText ());
    assertEquals ("linebreak test.", ret.get (1).getPlainText ());

    ret = HCExtHelper.nl2divList (HTML_STRING7);
    assertEquals (3, ret.size ());
    assertEquals ("", ret.get (0).getPlainText ());
    assertEquals ("this is a ", ret.get (1).getPlainText ());
    assertEquals ("linebreak test.", ret.get (2).getPlainText ());

    ret = HCExtHelper.nl2divList (HTML_STRING8);
    assertEquals (1, ret.size ());
    assertEquals ("", ret.get (0).getPlainText ());

    ret = HCExtHelper.nl2divList (HTML_STRING9);
    assertEquals (3, ret.size ());
    assertEquals ("", ret.get (0).getPlainText ());
    assertEquals ("", ret.get (1).getPlainText ());
    assertEquals ("", ret.get (2).getPlainText ());
  }

  @Test
  public void testCreateHCElement ()
  {
    for (final EHTMLElement e : EHTMLElement.values ())
      assertNotNull (HCExtHelper.createHCElement (e));
  }
}
