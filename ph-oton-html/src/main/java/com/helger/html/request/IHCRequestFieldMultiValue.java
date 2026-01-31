/*
 * Copyright (C) 2014-2026 Philip Helger (www.helger.com)
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
package com.helger.html.request;

import org.jspecify.annotations.NonNull;

import com.helger.collection.commons.ICommonsList;

/**
 * Defines an abstract request field for input controls. It encapsulates a name
 * and 0-n request values.
 *
 * @author Philip Helger´
 * @since 8.0.2
 */
public interface IHCRequestFieldMultiValue extends IHCBaseRequestField
{
  /**
   * Get the default values that should be used if no request value is present.
   *
   * @return The default value to be used as fallback. May not be
   *         <code>null</code> but maybe empty.
   */
  @NonNull
  ICommonsList <String> getDefaultValues ();

  /**
   * In case multiple request parameters with the same value are present (e.g.
   * multi-selects) this method retrieves all request values. If no such request
   * value is present, the default values are returned.
   *
   * @return A list of request values with the same field name or the default
   *         values if no such request values are present.
   */
  @NonNull
  ICommonsList <String> getRequestValues ();
}
