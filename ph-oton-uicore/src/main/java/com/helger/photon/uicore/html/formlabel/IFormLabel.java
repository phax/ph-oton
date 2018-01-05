/**
 * Copyright (C) 2014-2018 Philip Helger (www.helger.com)
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
package com.helger.photon.uicore.html.formlabel;

import javax.annotation.Nonnull;

import com.helger.html.hc.IHCNode;

/**
 * Represents the base interface for a single label (text field)
 *
 * @author Philip Helger
 */
public interface IFormLabel extends IHCNode
{
  /**
   * @return The non-<code>null</code> label type
   */
  @Nonnull
  ELabelType getType ();

  /**
   * @return <code>true</code> if this is a pure text label, <code>false</code>
   *         otherwise.
   */
  boolean isTextLabel ();

  /**
   * @return The pure label text without any type specific suffix. Never
   *         <code>null</code>.
   */
  @Nonnull
  String getPlainText ();
}
