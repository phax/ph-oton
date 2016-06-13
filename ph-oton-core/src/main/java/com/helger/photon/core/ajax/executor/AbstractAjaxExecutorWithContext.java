/**
 * Copyright (C) 2014-2016 Philip Helger (www.helger.com)
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
package com.helger.photon.core.ajax.executor;

import javax.annotation.Nonnull;

import com.helger.commons.annotation.OverrideOnDemand;
import com.helger.photon.core.ajax.response.IAjaxResponse;
import com.helger.photon.core.app.context.ILayoutExecutionContext;
import com.helger.web.scope.IRequestWebScopeWithoutResponse;

/**
 * Special {@link AbstractAjaxExecutor} that requires an
 * {@link ILayoutExecutionContext} object to be present.
 *
 * @author Philip Helger
 * @param <LECTYPE>
 *        LayoutExecutionContext implementation type
 */
public abstract class AbstractAjaxExecutorWithContext <LECTYPE extends ILayoutExecutionContext>
                                                      extends AbstractAjaxExecutor
{
  /**
   * Create the layout execution context
   *
   * @param aRequestScope
   *        The request scope to use. Never <code>null</code>.
   * @return Never <code>null</code>.
   */
  @Nonnull
  protected abstract LECTYPE createLayoutExecutionContext (@Nonnull IRequestWebScopeWithoutResponse aRequestScope);

  /**
   * This method must be overridden by every handler. It is called with the LEC
   * created by
   * {@link #createLayoutExecutionContext(IRequestWebScopeWithoutResponse)}
   *
   * @param aLEC
   *        The layout execution context. Never <code>null</code>.
   * @return the result object. May not be <code>null</code>
   * @throws Exception
   *         In case of an error
   */
  @OverrideOnDemand
  @Nonnull
  protected abstract IAjaxResponse mainHandleRequest (@Nonnull LECTYPE aLEC) throws Exception;

  @Override
  protected final IAjaxResponse mainHandleRequest (@Nonnull final IRequestWebScopeWithoutResponse aRequestScope) throws Exception
  {
    final LECTYPE aLEC = createLayoutExecutionContext (aRequestScope);
    if (aLEC == null)
      throw new IllegalStateException ("Failed to create layout execution context for request scope " + aRequestScope);
    return mainHandleRequest (aLEC);
  }
}
