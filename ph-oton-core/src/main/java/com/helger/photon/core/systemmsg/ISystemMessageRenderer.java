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
package com.helger.photon.core.systemmsg;

import javax.annotation.Nonnull;

import com.helger.commons.annotation.Nonempty;
import com.helger.html.hc.html.IHCElementWithChildren;

/**
 * Abstract interface for a system message renderer.
 *
 * @author Philip Helger
 * @since 8.2.2
 */
public interface ISystemMessageRenderer
{
  /**
   * Render the system message onto the provided control.
   *
   * @param sText
   *        The text to render. Never <code>null</code>.
   * @param aTargetCtrl
   *        The target control to be filled.
   */
  void renderSystemMessage (@Nonnull @Nonempty String sText, @Nonnull IHCElementWithChildren <?> aTargetCtrl);
}
