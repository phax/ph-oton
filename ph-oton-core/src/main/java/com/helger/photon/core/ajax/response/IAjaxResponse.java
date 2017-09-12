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
package com.helger.photon.core.ajax.response;

import java.io.Serializable;

import javax.annotation.Nonnull;

import com.helger.servlet.response.UnifiedResponse;

/**
 * Base interface for an Ajax response without a specific representation.
 *
 * @author Philip Helger
 */
@Deprecated
public interface IAjaxResponse extends Serializable
{
  /**
   * Apply the AJAX response onto the passed {@link UnifiedResponse}. This
   * happens in both success and error case. It's up to the implementation to
   * decide internally what to do.
   *
   * @param aUnifiedResponse
   *        The unified response. Never <code>null</code>.
   */
  void applyToResponse (@Nonnull UnifiedResponse aUnifiedResponse);
}
