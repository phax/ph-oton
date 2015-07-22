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
package com.helger.photon.core.action;

import java.util.Map;

import javax.annotation.CheckForSigned;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.helger.commons.annotation.ReturnsMutableCopy;
import com.helger.commons.annotation.ReturnsMutableObject;
import com.helger.commons.callback.CallbackList;
import com.helger.web.scope.IRequestWebScopeWithoutResponse;
import com.helger.web.servlet.response.UnifiedResponse;

/**
 * Action invoker interface.
 *
 * @author Philip Helger
 */
public interface IActionInvoker
{
  @Nonnull
  @ReturnsMutableObject ("design")
  CallbackList <IActionExceptionCallback> getExceptionCallbacks ();

  @Nonnull
  @ReturnsMutableObject ("design")
  CallbackList <IActionBeforeExecutionCallback> getBeforeExecutionCallbacks ();

  @Nonnull
  @ReturnsMutableObject ("design")
  CallbackList <IActionAfterExecutionCallback> getAfterExecutionCallbacks ();

  /**
   * @return The milliseconds after which an execution is considered long
   *         running. Only values &gt; 0 are considered.
   */
  @CheckForSigned
  long getLongRunningExecutionLimitTime ();

  /**
   * Set the milliseconds after which an execution is considered long running.
   *
   * @param nLongRunningExecutionLimitTime
   *        The milliseconds to use. Value &le; 0 are considered "no limit"
   */
  void setLongRunningExecutionLimitTime (long nLongRunningExecutionLimitTime);

  @Nonnull
  @ReturnsMutableObject ("design")
  CallbackList <IActionLongRunningExecutionCallback> getLongRunningExecutionCallbacks ();

  /**
   * @return A map from action name to action executor factory. Never
   *         <code>null</code>.
   */
  @Nonnull
  @ReturnsMutableCopy
  Map <String, IActionDeclaration> getAllRegisteredActions ();

  /**
   * Get the executor factory associated with the given action.
   *
   * @param sActionName
   *        The name of the action to check. May be <code>null</code>.
   * @return <code>null</code> if no such action exists.
   */
  @Nullable
  IActionDeclaration getRegisteredAction (@Nullable String sActionName);

  /**
   * Check whether an action with the given name is present
   *
   * @param sActionName
   *        The name of the action to check. May be <code>null</code>.
   * @return <code>true</code> if an action with the given name is contained,
   *         <code>false</code> otherwise.
   */
  boolean isRegisteredAction (@Nullable String sActionName);

  /**
   * Register an action.
   *
   * @param aAction
   *        The action declaration to be used. May not be <code>null</code>.
   */
  void registerAction (@Nonnull IActionDeclaration aAction);

  /**
   * Invoke the specified action
   *
   * @param sActionName
   *        The name of the action to be invoked. May not be <code>null</code>.
   * @param aActionExecutor
   *        The executor to be invoked. May not be <code>null</code>.
   * @param aRequestScope
   *        The current request scope. May not be <code>null</code>.
   * @param aUnifiedResponse
   *        The response to be filled. May not be <code>null</code>.
   * @throws Throwable
   *         In case the {@link IActionExecutor} threw an exception.
   */
  void invokeAction (@Nonnull String sActionName,
                     @Nonnull IActionExecutor aActionExecutor,
                     @Nonnull IRequestWebScopeWithoutResponse aRequestScope,
                     @Nonnull UnifiedResponse aUnifiedResponse) throws Throwable;
}
