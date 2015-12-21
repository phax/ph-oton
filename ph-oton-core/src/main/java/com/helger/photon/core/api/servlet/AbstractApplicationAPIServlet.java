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
package com.helger.photon.core.api.servlet;

import javax.annotation.Nonnull;

import com.helger.commons.annotation.Nonempty;
import com.helger.photon.core.api.ApplicationAPIManager;
import com.helger.photon.core.api.IAPIInvoker;
import com.helger.web.scope.IRequestWebScopeWithoutResponse;

/**
 * Abstract implementation of a servlet that invokes API functions.
 *
 * @author Philip Helger
 */
public abstract class AbstractApplicationAPIServlet extends AbstractAPIServlet
{
  @Override
  @Nonnull
  @Nonempty
  protected abstract String getApplicationID ();

  @Override
  @Nonnull
  protected final IAPIInvoker getAPIInvoker (@Nonnull final IRequestWebScopeWithoutResponse aRequestScope)
  {
    return ApplicationAPIManager.getInstance ();
  }
}
