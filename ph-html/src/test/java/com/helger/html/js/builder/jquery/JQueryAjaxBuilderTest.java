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
package com.helger.html.js.builder.jquery;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.helger.html.js.writer.JSWriterSettings;

/**
 * Test class for class {@link JQueryAjaxBuilder}.
 *
 * @author Philip Helger
 */
public final class JQueryAjaxBuilderTest
{
  @Test
  public void testBasic ()
  {
    final JSWriterSettings aSettings = new JSWriterSettings ().setIndentAndAlign (false);

    final JQueryAjaxBuilder aJAB = new JQueryAjaxBuilder ();
    assertEquals ("$.ajax({});", aJAB.build ().getJSCode (aSettings));
    aJAB.async (false);
    assertEquals ("$.ajax({async:false});", aJAB.build ().getJSCode (aSettings));
  }
}
