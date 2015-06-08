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
package com.helger.photon.bootstrap3.form;

import javax.annotation.Nonnull;

/**
 * Mutable interface for a form group container.
 *
 * @author Philip Helger
 */
public interface IMutableBootstrapFormGroupContainer extends IBootstrapFormGroupContainer
{
  /**
   * Add a new form group at the end.
   *
   * @param aFormGroup
   *        The form group to be added. May not be <code>null</code>.
   * @return this
   */
  @Nonnull
  IMutableBootstrapFormGroupContainer addFormGroup (@Nonnull BootstrapFormGroup aFormGroup);
}
