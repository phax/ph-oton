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
package com.helger.photon.core.action.executor;

import javax.annotation.Nonnull;

import com.helger.photon.core.app.context.ILayoutExecutionContext;
import com.helger.web.scope.IRequestWebScopeWithoutResponse;
import com.helger.web.servlet.response.UnifiedResponse;

public abstract class AbstractActionExecutorWithContext <LECTYPE extends ILayoutExecutionContext> extends AbstractActionExecutor
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
   * This method must be overridden by every handler
   *
   * @param aLEC
   *        The layout execution context. Never <code>null</code>.
   * @param aUnifiedResponse
   *        The response to write to. Never <code>null</code>.
   * @throws Throwable
   *         in case of an error
   */
  @Nonnull
  protected abstract void mainExecute (@Nonnull LECTYPE aLEC,
                                       @Nonnull UnifiedResponse aUnifiedResponse) throws Throwable;

  public final void execute (@Nonnull final IRequestWebScopeWithoutResponse aRequestScope,
                             @Nonnull final UnifiedResponse aUnifiedResponse) throws Throwable
  {
    final LECTYPE aLEC = createLayoutExecutionContext (aRequestScope);
    if (aLEC == null)
      throw new IllegalStateException ("Failed to create layout execution context!");
    mainExecute (aLEC, aUnifiedResponse);
  }
}
