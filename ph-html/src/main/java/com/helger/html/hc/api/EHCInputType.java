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
package com.helger.html.hc.api;

import javax.annotation.Nonnull;

import com.helger.commons.annotations.Nonempty;
import com.helger.html.annotations.SinceHTML5;

/**
 * The type of an {@link com.helger.html.hc.impl.AbstractHCInput} element.
 *
 * @author Philip Helger
 */
public enum EHCInputType implements IHCHasHTMLAttributeValue
{
  HIDDEN ("hidden"),
  TEXT ("text"),
  @SinceHTML5
  SEARCH ("search"),
  @SinceHTML5
  TEL ("tel"),
  @SinceHTML5
  URL ("url"),
  @SinceHTML5
  EMAIL ("email"),
  PASSWORD ("password"),
  @SinceHTML5
  DATE ("date"),
  @SinceHTML5
  TIME ("time"),
  @SinceHTML5
  NUMBER ("number"),
  @SinceHTML5
  RANGE ("range"),
  @SinceHTML5
  COLOR ("color"),
  CHECKBOX ("checkbox"),
  RADIO ("radio"),
  FILE ("file"),
  SUBMIT ("submit"),
  IMAGE ("image"),
  RESET ("reset"),
  BUTTON ("button");

  private final String m_sAttrValue;

  private EHCInputType (@Nonnull @Nonempty final String sAttrValue)
  {
    m_sAttrValue = sAttrValue;
  }

  @Nonnull
  @Nonempty
  public String getAttrValue ()
  {
    return m_sAttrValue;
  }
}
