/*
 * Copyright (C) 2014-2021 Philip Helger (www.helger.com)
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
package com.helger.html;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

/**
 * Test class for class {@link EHTMLElement}.
 *
 * @author Philip Helger
 */
public final class EHTMLElementTest
{
  @Test
  public void testMaybeSelfClosed ()
  {
    assertTrue (EHTMLElement.BR.mayBeSelfClosed ());
    assertTrue (EHTMLElement.isTagThatMayBeSelfClosed ("br"));
    assertTrue (EHTMLElement.isTagThatMayBeSelfClosed ("bR"));
    assertTrue (EHTMLElement.isTagThatMayBeSelfClosed ("BR"));
    assertFalse (EHTMLElement.isTagThatMayNotBeSelfClosed ("br"));
    assertFalse (EHTMLElement.isTagThatMayNotBeSelfClosed ("bR"));
    assertFalse (EHTMLElement.isTagThatMayNotBeSelfClosed ("BR"));

    assertFalse (EHTMLElement.A.mayBeSelfClosed ());
    assertFalse (EHTMLElement.isTagThatMayBeSelfClosed ("a"));
    assertFalse (EHTMLElement.isTagThatMayBeSelfClosed ("A"));
    assertTrue (EHTMLElement.isTagThatMayNotBeSelfClosed ("a"));
    assertTrue (EHTMLElement.isTagThatMayNotBeSelfClosed ("A"));
  }
}
