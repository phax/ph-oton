/**
 * Copyright (C) 2014-2019 Philip Helger (www.helger.com)
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
import com.helger.photon.ajax.executor.IAjaxExecutor;
import com.helger.photon.app.PhotonUnifiedResponse;
import com.helger.photon.core.app.context.ILayoutExecutionContext;
import com.helger.web.scope.IRequestWebScopeWithoutResponse;

/**
 * Special {@link IAjaxExecutor} that requires an
 * {@link ILayoutExecutionContext} object to be present.
 *
 * @author Philip Helger
 * @param <LECTYPE>
 *        LayoutExecutionContext implementation type
 */
public abstract class AbstractAjaxExecutorWithContext <LECTYPE extends ILayoutExecutionContext> implements IAjaxExecutor
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
   * @param aAjaxResponse
   *        The Ajax response to be filled. Never <code>null</code>.
   * @throws Exception
   *         In case of an error
   */
  @OverrideOnDemand
  protected abstract void mainHandleRequest (@Nonnull LECTYPE aLEC,
                                             @Nonnull PhotonUnifiedResponse aAjaxResponse) throws Exception;

  public void handleRequest (@Nonnull final IRequestWebScopeWithoutResponse aRequestScope,
                             @Nonnull final PhotonUnifiedResponse aAjaxResponse) throws Exception
  {
    final LECTYPE aLEC = createLayoutExecutionContext (aRequestScope);
    mainHandleRequest (aLEC, aAjaxResponse);
  }
}
