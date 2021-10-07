/*
 * Copyright (C) 2014-2021 Philip Helger (www.helger.com)
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
package com.helger.photon.core.form;

import java.util.Collection;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.helger.commons.annotation.Nonempty;
import com.helger.html.request.IHCRequestFieldMultiValue;
import com.helger.web.scope.util.RequestFieldDataMultiValue;

public class RequestFieldMultiValue extends RequestFieldDataMultiValue implements IHCRequestFieldMultiValue
{
  /**
   * Copy constructor
   *
   * @param aRF
   *        The request field to copy the values from. May not be
   *        <code>null</code>.
   */
  public RequestFieldMultiValue (@Nonnull final RequestFieldDataMultiValue aRF)
  {
    super (aRF);
  }

  /**
   * Create a new request field that has no default value
   *
   * @param sFieldName
   *        The field name to use. May neither be <code>null</code> nor empty.
   */
  public RequestFieldMultiValue (@Nonnull @Nonempty final String sFieldName)
  {
    super (sFieldName);
  }

  /**
   * Default constructor.
   *
   * @param sFieldName
   *        The field name to use. May neither be <code>null</code> nor empty.
   * @param aDefaultValues
   *        The default values to use, if no value is present in the request
   *        scope.
   */
  public RequestFieldMultiValue (@Nonnull @Nonempty final String sFieldName, @Nullable final Collection <String> aDefaultValues)
  {
    super (sFieldName, aDefaultValues);
  }
}
