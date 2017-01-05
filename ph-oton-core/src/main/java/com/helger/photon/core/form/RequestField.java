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
package com.helger.photon.core.form;

import java.math.BigDecimal;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.helger.commons.annotation.Nonempty;
import com.helger.commons.id.IHasID;
import com.helger.html.request.IHCRequestField;
import com.helger.web.scope.util.RequestFieldData;

public class RequestField extends RequestFieldData implements IHCRequestField
{
  /**
   * Copy constructor
   *
   * @param aRF
   *        The request field to copy the values from. May not be
   *        <code>null</code>.
   */
  public RequestField (@Nonnull final RequestFieldData aRF)
  {
    super (aRF);
  }

  /**
   * Create a new request field that has no default value
   *
   * @param sFieldName
   *        The field name to use. May neither be <code>null</code> nor empty.
   */
  public RequestField (@Nonnull @Nonempty final String sFieldName)
  {
    super (sFieldName);
  }

  /**
   * Default constructor.
   *
   * @param sFieldName
   *        The field name to use. May neither be <code>null</code> nor empty.
   * @param sDefaultValue
   *        The default value to use, if no value is present in the request
   *        scope.
   */
  public RequestField (@Nonnull @Nonempty final String sFieldName, @Nullable final String sDefaultValue)
  {
    super (sFieldName, sDefaultValue);
  }

  /**
   * Utility constructor that uses an optional default value provider that has
   * an ID
   *
   * @param sFieldName
   *        The field name to use. May neither be <code>null</code> nor empty.
   * @param aDefaultValueProvider
   *        The object who's ID is to be used. May be <code>null</code> in which
   *        case no default value is used
   */
  public RequestField (@Nonnull @Nonempty final String sFieldName,
                       @Nullable final IHasID <String> aDefaultValueProvider)
  {
    super (sFieldName, aDefaultValueProvider);
  }

  /**
   * Helper constructor using a BigDecimal instead of a String.
   *
   * @param sFieldName
   *        The field name to use. May neither be <code>null</code> nor empty.
   * @param aDefaultValue
   *        The default value to be used. Is converted to a String. May be
   *        <code>null</code>.
   */
  public RequestField (@Nonnull @Nonempty final String sFieldName, @Nullable final BigDecimal aDefaultValue)
  {
    super (sFieldName, aDefaultValue == null ? null : aDefaultValue.toString ());
  }

  /**
   * Helper constructor using an int instead of a String.
   *
   * @param sFieldName
   *        The field name to use. May neither be <code>null</code> nor empty.
   * @param nDefaultValue
   *        The default value to be used. Is converted to a String
   */
  public RequestField (@Nonnull @Nonempty final String sFieldName, final int nDefaultValue)
  {
    super (sFieldName, nDefaultValue);
  }

  /**
   * Helper constructor using an Integer instead of a String.
   *
   * @param sFieldName
   *        The field name to use. May neither be <code>null</code> nor empty.
   * @param aDefaultValue
   *        The default value to be used. Is converted to a String. May be
   *        <code>null</code>.
   */
  public RequestField (@Nonnull @Nonempty final String sFieldName, @Nullable final Integer aDefaultValue)
  {
    super (sFieldName, aDefaultValue == null ? null : aDefaultValue.toString ());
  }

  /**
   * Helper constructor using a long instead of a String.
   *
   * @param sFieldName
   *        The field name to use. May neither be <code>null</code> nor empty.
   * @param nDefaultValue
   *        The default value to be used. Is converted to a String
   */
  public RequestField (@Nonnull @Nonempty final String sFieldName, final long nDefaultValue)
  {
    super (sFieldName, nDefaultValue);
  }

  /**
   * Helper constructor using a Long instead of a String.
   *
   * @param sFieldName
   *        The field name to use. May neither be <code>null</code> nor empty.
   * @param aDefaultValue
   *        The default value to be used. Is converted to a String. May be
   *        <code>null</code>.
   */
  public RequestField (@Nonnull @Nonempty final String sFieldName, @Nullable final Long aDefaultValue)
  {
    super (sFieldName, aDefaultValue == null ? null : aDefaultValue.toString ());
  }
}
