/**
 * Copyright (C) 2014-2017 Philip Helger (www.helger.com)
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
package com.helger.html.hc.html.forms;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.helger.html.hc.IHCHasID;
import com.helger.html.hc.html.IHCElementWithChildren;

/**
 * Interface for LABELs
 *
 * @author Philip Helger
 * @param <THISTYPE>
 *        Implementation type
 */
public interface IHCLabel <THISTYPE extends IHCLabel <THISTYPE>> extends IHCElementWithChildren <THISTYPE>
{
  @Nullable
  String getFor ();

  /**
   * Indicates that this label is used as the description for another object.
   *
   * @param sFor
   *        The HTML ID of the other object.
   * @return this
   */
  @Nonnull
  THISTYPE setFor (@Nullable String sFor);

  /**
   * Indicates that this label is used as the description for another object.
   *
   * @param aFor
   *        The HTML of the other object.
   * @return this
   */
  @Nonnull
  THISTYPE setFor (@Nullable IHCHasID <?> aFor);

  @Nullable
  String getForm ();

  /**
   * The value of the id attribute on the form with which to associate the
   * element.
   *
   * @param sForm
   *        The HTML ID of the form.
   * @return this
   */
  @Nonnull
  THISTYPE setForm (@Nullable String sForm);
}
