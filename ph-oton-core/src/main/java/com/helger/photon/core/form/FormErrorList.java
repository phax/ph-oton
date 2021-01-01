/**
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

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.helger.commons.annotation.Nonempty;
import com.helger.commons.error.IError;
import com.helger.commons.error.SingleError;
import com.helger.commons.error.list.ErrorList;

/**
 * Special error list for form errors. Basically an {@link ErrorList} with
 * sanity methods.
 *
 * @author Philip Helger
 */
public class FormErrorList extends ErrorList
{
  public FormErrorList ()
  {}

  public FormErrorList (@Nullable final Iterable <? extends IError> aList)
  {
    super (aList);
  }

  /**
   * Add a field specific information message.
   *
   * @param sFieldName
   *        The field name for which the message is to be recorded. May neither
   *        be <code>null</code> nor empty.
   * @param sText
   *        The text to use. May neither be <code>null</code> nor empty.
   */
  public void addFieldInfo (@Nonnull @Nonempty final String sFieldName, @Nonnull @Nonempty final String sText)
  {
    add (SingleError.builderInfo ().setErrorFieldName (sFieldName).setErrorText (sText).build ());
  }

  /**
   * Add a field specific warning message.
   *
   * @param sFieldName
   *        The field name for which the message is to be recorded. May neither
   *        be <code>null</code> nor empty.
   * @param sText
   *        The text to use. May neither be <code>null</code> nor empty.
   */
  public void addFieldWarning (@Nonnull @Nonempty final String sFieldName, @Nonnull @Nonempty final String sText)
  {
    add (SingleError.builderWarn ().setErrorFieldName (sFieldName).setErrorText (sText).build ());
  }

  /**
   * Add a field specific error message.
   *
   * @param sFieldName
   *        The field name for which the message is to be recorded. May neither
   *        be <code>null</code> nor empty.
   * @param sText
   *        The text to use. May neither be <code>null</code> nor empty.
   */
  public void addFieldError (@Nonnull @Nonempty final String sFieldName, @Nonnull @Nonempty final String sText)
  {
    add (SingleError.builderError ().setErrorFieldName (sFieldName).setErrorText (sText).build ());
  }

  @Override
  @Nonnull
  public FormErrorList getClone ()
  {
    return new FormErrorList (this);
  }
}
