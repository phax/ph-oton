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
package com.helger.html.hc;

import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.Nullable;

import com.helger.base.id.factory.GlobalIDFactory;
import com.helger.base.string.StringHelper;
import com.helger.base.trait.IGenericImplTrait;

/**
 * Special interface for HC elements having an optional ID
 *
 * @author Philip Helger
 * @param <IMPLTYPE>
 *        The implementation type
 */
public interface IHCHasID <IMPLTYPE extends IHCHasID <IMPLTYPE>> extends IGenericImplTrait <IMPLTYPE>
{
  /**
   * Get the HTML ID of this object.<br>
   * Note: we cannot use <code>IHasID&lt;String&gt;</code> because the constraint of IHasID is, that
   * the returned ID may not be <code>null</code> whereas here the HTML ID can be <code>null</code>!
   *
   * @return The HTML ID of this object.
   */
  @Nullable
  String getID ();

  /**
   * @return <code>true</code> if this element has an ID, <code>false</code> if not.
   */
  default boolean hasID ()
  {
    return StringHelper.isNotEmpty (getID ());
  }

  /**
   * @return <code>true</code> if this element has no ID, <code>false</code> if it has one.
   */
  default boolean hasNoID ()
  {
    return StringHelper.isEmpty (getID ());
  }

  /**
   * Set the HTML ID of this object.
   *
   * @param sID
   *        The ID to use. Must conform to the HTML rules for an element ID.
   * @return this
   */
  @NonNull
  IMPLTYPE setID (@Nullable String sID);

  /**
   * Set a unique HTML ID for this object. Equal to
   * <code>setID (GlobalIDFactory.getNewStringID ())</code>
   *
   * @return this
   */
  @NonNull
  default IMPLTYPE setUniqueID ()
  {
    return setID (GlobalIDFactory.getNewStringID ());
  }

  /**
   * Set a new ID if none is present. This is a shortcut for
   * <code>if (!hasID())setUniqueID ();</code>
   *
   * @return this
   */
  @NonNull
  default IMPLTYPE ensureID ()
  {
    if (!hasID ())
      setUniqueID ();
    return thisAsT ();
  }
}
