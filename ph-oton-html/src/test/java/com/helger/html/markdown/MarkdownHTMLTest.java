/*
 * Copyright (C) 2014-2025 Philip Helger (www.helger.com)
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

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

/**
 * Test class for class MarkdownHTML
 *
 * @author Philip Helger
 */
public final class MarkdownHTMLTest
{
  @Test
  public void testLinkPrefix ()
  {
    assertTrue (MarkdownHTML.isLinkPrefix ("http"));
    assertTrue (MarkdownHTML.isLinkPrefix ("https"));
  }

  @Test
  public void testIsUnsafeHtmlElement ()
  {
    assertTrue (MarkdownHTML.isUnsafeHtmlElement ("script"));
    assertTrue (MarkdownHTML.isUnsafeHtmlElement ("Script"));
    assertTrue (MarkdownHTML.isUnsafeHtmlElement ("SCRIPT"));
    assertFalse (MarkdownHTML.isUnsafeHtmlElement ("div"));
    assertFalse (MarkdownHTML.isUnsafeHtmlElement ("Div"));
    assertFalse (MarkdownHTML.isUnsafeHtmlElement ("DIV"));
  }
}
