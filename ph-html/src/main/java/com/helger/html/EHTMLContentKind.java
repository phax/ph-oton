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
package com.helger.html;

import javax.annotation.Nonnegative;

/**
 * Source:
 * http://www.w3.org/TR/2014/REC-html5-20141028/dom.html#kinds-of-content
 *
 * @author Philip Helger
 */
public enum EHTMLContentKind
{
  METADATA (0x01),
  FLOW (0x02),
  SECTIONING (0x04),
  HEADING (0x08),
  PHRASING (0x10),
  EMBEDDED (0x20),
  INTERACTIVE (0x40);

  private final int m_nValue;

  private EHTMLContentKind (@Nonnegative final int nValue)
  {
    m_nValue = nValue;
  }

  @Nonnegative
  public int getValue ()
  {
    return m_nValue;
  }
}
