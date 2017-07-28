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
package com.helger.photon.core.api.pathdescriptor;

import javax.annotation.Nonnull;

import com.helger.commons.ValueEnforcer;
import com.helger.commons.annotation.ReturnsMutableCopy;
import com.helger.commons.collection.impl.CommonsLinkedHashMap;
import com.helger.commons.collection.impl.ICommonsOrderedMap;
import com.helger.commons.string.ToStringGenerator;

/**
 * This class represents the overall result of a path matching. Upon successful
 * matching all variable path parts are returned as a map in the order of the
 * path.
 *
 * @author Philip Helger
 */
public final class PathMatchingResult
{
  public static final PathMatchingResult NO_MATCH = new PathMatchingResult ();

  private final boolean m_bMatch;
  private final ICommonsOrderedMap <String, String> m_aVariableValues;

  /**
   * Internal constructor for "no match"
   */
  private PathMatchingResult ()
  {
    m_bMatch = false;
    m_aVariableValues = null;
  }

  /**
   * Constructor for "match"
   *
   * @param aVariableValues
   *        Non-<code>null</code> but may empty matching variables.
   */
  public PathMatchingResult (@Nonnull final ICommonsOrderedMap <String, String> aVariableValues)
  {
    ValueEnforcer.notNull (aVariableValues, "VariableValues");
    m_bMatch = true;
    m_aVariableValues = aVariableValues;
  }

  /**
   * @return <code>true</code> if the path matching was successful,
   *         <code>false</code> otherwise.
   */
  public boolean isMatch ()
  {
    return m_bMatch;
  }

  @Nonnull
  @ReturnsMutableCopy
  public ICommonsOrderedMap <String, String> getAllVariableValues ()
  {
    return new CommonsLinkedHashMap<> (m_aVariableValues);
  }

  @Override
  public String toString ()
  {
    return new ToStringGenerator (null).append ("Match", m_bMatch)
                                       .appendIfNotNull ("VariableValues", m_aVariableValues)
                                       .getToString ();
  }

  @Nonnull
  public static PathMatchingResult createSuccess (@Nonnull final ICommonsOrderedMap <String, String> aVariableValues)
  {
    return new PathMatchingResult (aVariableValues);
  }
}
