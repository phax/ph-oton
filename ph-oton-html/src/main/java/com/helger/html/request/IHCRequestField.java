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
package com.helger.html.request;

import java.io.Serializable;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.helger.commons.annotation.Nonempty;
import com.helger.commons.collection.impl.ICommonsList;

/**
 * Defines an abstract request field for input controls. It encapsulates a name
 * and a request value.
 *
 * @author Philip Helger
 */
public interface IHCRequestField extends Serializable
{
  /**
   * @return The field name of this request field. Neither <code>null</code> nor
   *         empty.
   */
  @Nonnull
  @Nonempty
  String getFieldName ();

  /**
   * Get the default value that should be used if no request value is present.
   *
   * @return The default value to be used as fallback. May not be
   *         <code>null</code>. <code>null</code> have to be returned as empty
   *         strings!
   */
  @Nonnull
  String getDefaultValue ();

  /**
   * Get the value of the request - optionally falling back to an eventually
   * provided default value if no such request parameter is present
   *
   * @return A single request value as string.
   * @see #getDefaultValue()
   */
  @Nonnull
  String getRequestValue ();

  /**
   * In case multiple request parameters with the same value are present (e.g.
   * multi-selects or checkboxes) this method retrieves all request values. If
   * no such request value is present a list with one entry (the default value)
   * is returned, in case the default value is non-empty
   *
   * @return A list of simple request values with the same key or
   *         <code>null</code> if no such request parameter is present and no
   *         (or an empty) default value was provided.
   */
  @Nullable
  ICommonsList <String> getRequestValueAsList ();
}
