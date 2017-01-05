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
package com.helger.html.request;

import javax.annotation.Nonnull;

/**
 * Special request field for check boxes and radio buttons where the same field
 * name is used with multiple different values.
 *
 * @author Philip Helger
 * @see IHCRequestFieldBoolean
 */
public interface IHCRequestFieldBooleanMultiValue extends IHCRequestFieldBoolean
{
  /**
   * @return <code>true</code> if the check box with the passed value is checked
   *         is checked or if no such request parameter is present and the
   *         fall-back is <code>true</code>, <code>false</code> otherwise.
   */
  boolean isChecked ();

  /**
   * @return The value to be used for the check box or radio button.
   */
  @Nonnull
  String getValue ();
}
