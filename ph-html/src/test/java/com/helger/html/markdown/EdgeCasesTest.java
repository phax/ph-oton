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
/*
 Copyright (c) 2005, Pete Bevin.
 <http://markdownj.petebevin.com>

 All rights reserved.

 Redistribution and use in source and binary forms, with or without
 modification, are permitted provided that the following conditions are
 met:

 * Redistributions of source code must retain the above copyright notice,
 this list of conditions and the following disclaimer.

 * Redistributions in binary form must reproduce the above copyright
 notice, this list of conditions and the following disclaimer in the
 documentation and/or other materials provided with the distribution.

 * Neither the name "Markdown" nor the names of its contributors may
 be used to endorse or promote products derived from this software
 without specific prior written permission.

 This software is provided by the copyright holders and contributors "as
 is" and any express or implied warranties, including, but not limited
 to, the implied warranties of merchantability and fitness for a
 particular purpose are disclaimed. In no event shall the copyright owner
 or contributors be liable for any direct, indirect, incidental, special,
 exemplary, or consequential damages (including, but not limited to,
 procurement of substitute goods or services; loss of use, data, or
 profits; or business interruption) however caused and on any theory of
 liability, whether in contract, strict liability, or tort (including
 negligence or otherwise) arising in any way out of the use of this
 software, even if advised of the possibility of such damage.

 */

package com.helger.html.markdown;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.util.regex.Pattern;

import org.junit.Rule;
import org.junit.Test;

import com.helger.commons.regex.RegExPool;
import com.helger.html.mock.HCTestRuleOptimized;

public final class EdgeCasesTest
{
  @Rule
  public final HCTestRuleOptimized m_aRule = new HCTestRuleOptimized ();

  @Test
  public void testEdgeCases () throws IOException
  {
    final MarkdownProcessor p = new MarkdownProcessor ();
    assertEquals ("", p.process ("").getAsHTMLString ());
    assertEquals ("", p.process ("  ").getAsHTMLString ());
    assertEquals ("", p.process ((String) null).getAsHTMLString ());
    assertEquals ("<p>First line<table><tbody><tr><td><td>Block level</td></td></tr></tbody></table>.</p>",
                  p.process ("First line<table><tr><td>Block level</td></tr></table>.").getAsHTMLString ());
    assertEquals ("<p>First line<table><tbody><tr><td><td>Block level</td></td></tr></tbody></table>.</p>",
                  p.process ("First line<table><tbody><tr><td>Block level</td></tr></tbody></table>.")
                   .getAsHTMLString ());
    assertEquals ("<p>First line<table><thead><tr><td><td>Block level</td></td></tr></thead></table>.</p>",
                  p.process ("First line<table><thead><tr><td>Block level</td></tr></thead></table>.")
                   .getAsHTMLString ());
    assertEquals ("<p>First line<table><tfoot><tr><td><td>Block level</td></td></tr></tfoot></table>.</p>",
                  p.process ("First line<table><tfoot><tr><td>Block level</td></tr></tfoot></table>.")
                   .getAsHTMLString ());
    assertEquals ("<p>First line *unclosed</p>", p.process ("First line *unclosed").getAsHTMLString ());
    assertEquals ("<p>First line **unclosed</p>", p.process ("First line **unclosed").getAsHTMLString ());
    assertEquals ("<p>First line unclosed*</p>", p.process ("First line unclosed*").getAsHTMLString ());
    assertEquals ("<p>First line unclosed**</p>", p.process ("First line unclosed**").getAsHTMLString ());
  }

  @Test
  public void testSplitAssumption ()
  {
    // In Perl, split(/x/, "") returns the empty string.
    // But in Java, it's the array { "" }.
    final Pattern x = RegExPool.getPattern ("x");
    final String [] xs = x.split ("");
    assertEquals (1, xs.length);
    assertEquals ("", xs[0]);
  }

  @Test
  public void testUnixLineConventions () throws IOException
  {
    final String sExpected = "<p>a\nb\nc</p>";
    assertEquals (sExpected, new MarkdownProcessor ().process ("a\nb\nc\n").getAsHTMLString ());
    assertEquals (sExpected, new MarkdownProcessor ().process ("a\r\nb\r\nc\r\n").getAsHTMLString ());
    assertEquals (sExpected, new MarkdownProcessor ().process ("a\rb\rc\r").getAsHTMLString ());
  }

  @Test
  public void testImages () throws IOException
  {
    final String url = "![an *image*](/images/an_image_with_underscores.jpg \"An_image_title\")";
    final String processed = new MarkdownProcessor ().process (url).getAsHTMLString ();
    final String output = "<p><img title=\"An_image_title\" src=\"/images/an_image_with_underscores.jpg\" alt=\"an *image*\" /></p>";
    assertEquals (output, processed);
  }

  @Test
  public void testAutoLinks () throws IOException
  {
    final String url = "[a _link_](http://url.com/a_tale_of_two_cities?var1=a_query_&var2=string \"A_link_title\")";
    final String processed = new MarkdownProcessor ().process (url).getAsHTMLString ();
    final String output = "<p><a title=\"A_link_title\" href=\"http://url.com/a_tale_of_two_cities?var1=a_query_&amp;var2=string\">a <em>link</em></a></p>";
    assertEquals (output, processed);
  }

  @Test
  public void testLinkPrefix ()
  {
    assertTrue (MarkdownHTML.isLinkPrefix ("http"));
    assertTrue (MarkdownHTML.isLinkPrefix ("https"));
  }
}
