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
package com.helger.html.hc;

import java.util.Set;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.helger.commons.annotations.DevelopersNote;
import com.helger.commons.annotations.ReturnsMutableCopy;
import com.helger.html.css.ICSSClassProvider;

/**
 * Base interface for objects having CSS classes
 * 
 * @author Philip Helger
 * @param <THISTYPE>
 *        Implementation type
 */
public interface IHCHasCSSClasses <THISTYPE extends IHCHasCSSClasses <THISTYPE>>
{
  @Nonnull
  THISTYPE addClass (@Nullable ICSSClassProvider aProvider);

  @Deprecated
  @DevelopersNote ("Use addClass instead!")
  @Nonnull
  THISTYPE addClasses (@Nullable ICSSClassProvider aProvider);

  /**
   * Add multiple unique CSS classes at once. Each CSS class that is already
   * present, is ignored.
   * 
   * @param aProviders
   *        The CSS classed to add. May neither be <code>null</code> nor empty.
   * @return this
   */
  @Nonnull
  THISTYPE addClasses (@Nullable ICSSClassProvider... aProviders);

  /**
   * Add multiple unique CSS classes at once. Each CSS class that is already
   * present, is ignored.
   * 
   * @param aProviders
   *        The CSS classed to add. May neither be <code>null</code> nor empty.
   * @return this
   */
  @Nonnull
  THISTYPE addClasses (@Nullable Iterable <? extends ICSSClassProvider> aProviders);

  @Nonnull
  THISTYPE removeClass (@Nullable ICSSClassProvider aProvider);

  @Nonnull
  THISTYPE removeAllClasses ();

  boolean containsClass (@Nullable ICSSClassProvider aProvider);

  @Nonnull
  @ReturnsMutableCopy
  Set <ICSSClassProvider> getAllClasses ();

  @Nonnull
  @ReturnsMutableCopy
  Set <String> getAllClassNames ();

  /**
   * @return <code>true</code> if at least one CSS class is assigned,
   *         <code>false</code> otherwise.
   */
  boolean hasAnyClass ();

  /**
   * Get the string representation of all contained classes as it should be set
   * to the HTML class attribute.
   * 
   * @return <code>null</code> if no classes are present.
   */
  @Nullable
  String getAllClassesAsString ();
}
