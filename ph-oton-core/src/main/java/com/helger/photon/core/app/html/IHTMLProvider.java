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
package com.helger.photon.core.app.html;

import javax.annotation.Nonnull;

import com.helger.html.hchtml.root.HCHtml;
import com.helger.web.scope.IRequestWebScopeWithoutResponse;

/**
 * Base interface for an HTML creator
 *
 * @author Philip Helger
 */
public interface IHTMLProvider
{
  /**
   * Create HTML for the provided request.
   *
   * @param aRequestScope
   *        The current request. Never <code>null</code>.
   * @return The created HTML content. May not be <code>null</code>.
   */
  @Nonnull
  HCHtml createHTML (@Nonnull IRequestWebScopeWithoutResponse aRequestScope);
}
