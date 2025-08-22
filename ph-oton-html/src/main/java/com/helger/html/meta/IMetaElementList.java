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
package com.helger.html.meta;

import java.util.Collection;

import com.helger.annotation.Nonnegative;
import com.helger.annotation.style.MustImplementEqualsAndHashcode;
import com.helger.annotation.style.ReturnsMutableCopy;
import com.helger.collection.commons.ICommonsIterable;
import com.helger.collection.commons.ICommonsList;
import com.helger.collection.commons.ICommonsOrderedSet;

import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;

/**
 * Read only base interface for a list of {@link IMetaElement} objects.
 *
 * @author Philip Helger
 */
@MustImplementEqualsAndHashcode
public interface IMetaElementList extends ICommonsIterable <IMetaElement>
{
  /**
   * @return A set with used meta element names. Never <code>null</code>.
   */
  @Nonnull
  @ReturnsMutableCopy
  ICommonsOrderedSet <String> getAllMetaElementNames ();

  /**
   * @return A list with all contained meta elements. Never <code>null</code>.
   */
  @Nonnull
  @ReturnsMutableCopy
  ICommonsList <IMetaElement> getAllMetaElements ();

  /**
   * Add all contained meta elements to the passed container.
   *
   * @param aTarget
   *        The target container to be filled. May not be <code>null</code>.
   */
  void getAllMetaElements (@Nonnull Collection <? super IMetaElement> aTarget);

  /**
   * Find the meta element with the given name.
   *
   * @param sName
   *        The name to search. May be <code>null</code> or empty.
   * @return <code>null</code> if no such meta element exists.
   */
  @Nullable
  IMetaElement getMetaElementOfName (@Nullable String sName);

  /**
   * Check if a meta element with the given name exists.
   *
   * @param sName
   *        The name to check. May be <code>null</code> or empty.
   * @return <code>true</code> if a meta element with the passed name exists.
   */
  boolean containsMetaElementWithName (@Nullable String sName);

  /**
   * @return The number of contained meta elements. Always &ge; 0.
   */
  @Nonnegative
  int getMetaElementCount ();

  /**
   * @return <code>true</code> if at least one meta element is contained,
   *         <code>false</code> otherwise.
   */
  boolean hasMetaElements ();
}
