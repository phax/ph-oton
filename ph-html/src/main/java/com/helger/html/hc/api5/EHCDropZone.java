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
package com.helger.html.hc.api5;

import javax.annotation.Nonnull;

import com.helger.commons.annotations.Nonempty;
import com.helger.html.hc.api.IHCHasHTMLAttributeValue;

/**
 * Draggable?
 * 
 * @author Philip Helger
 */
public enum EHCDropZone implements IHCHasHTMLAttributeValue
{
  /**
   * Indicates that dropping an accepted item on the element will result in a
   * copy of the dragged data.
   */
  COPY ("copy"),
  /**
   * Indicates that dropping an accepted item on the element will result in the
   * dragged data being moved to the new location.
   */
  MOVE ("move"),
  /**
   * Indicates that dropping an accepted item on the element will result in a
   * link to the original data.
   */
  LINK ("link");

  private final String m_sAttrValue;

  private EHCDropZone (@Nonnull @Nonempty final String sAttrValue)
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
