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
package com.helger.html.entities;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.helger.commons.string.StringHelper;

/**
 * Test class for class {@link EHTMLEntity}
 *
 * @author Philip Helger
 */
public final class EHTMLEntityTest
{
  @Test
  public void testBasic ()
  {
    for (final EHTMLEntity e : EHTMLEntity.values ())
    {
      assertTrue (StringHelper.hasText (e.getEntityName ()));
      assertTrue (StringHelper.hasText (e.getEntityReference ()));
      assertSame (e, EHTMLEntity.getFromEntityReferenceOrNull (e.getEntityReference ()));
      assertSame (e, EHTMLEntity.getFromCharOrNull (e.getChar ()));
    }
    assertEquals (EHTMLEntity.values ().length, EHTMLEntity.getEntityRefToEntityMap ().size ());
    assertEquals (EHTMLEntity.values ().length, EHTMLEntity.getCharToEntityMap ().size ());
    assertEquals (EHTMLEntity.values ().length, EHTMLEntity.getEntityRefToCharMap ().size ());
    assertEquals (EHTMLEntity.values ().length, EHTMLEntity.getEntityRefToCharStringMap ().size ());
    assertEquals (EHTMLEntity.values ().length, EHTMLEntity.getCharToEntityRefMap ().size ());
  }

  @Test
  public void testHTMLEscape ()
  {
    assertNull (EHTMLEntity.htmlEscape (null));
    assertEquals ("", EHTMLEntity.htmlEscape (""));
    assertEquals ("abc", EHTMLEntity.htmlEscape ("abc"));
    assertEquals ("abc&auml;&ouml;&uuml;", EHTMLEntity.htmlEscape ("abcäöü"));
    assertEquals ("&auml;&ouml;&uuml;&auml;&ouml;&uuml;", EHTMLEntity.htmlEscape ("äöüäöü"));
    assertEquals ("&auml;&ouml;&uuml;&Auml;&Ouml;&Uuml;", EHTMLEntity.htmlEscape ("äöüÄÖÜ"));
    assertEquals ("p&amp;h", EHTMLEntity.htmlEscape ("p&h"));
    assertEquals ("&auml;&amp;&ouml;", EHTMLEntity.htmlEscape ("ä&ö"));
  }
}
