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
package com.helger.webbasics.action;

import java.util.Map;

import javax.annotation.CheckForSigned;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.concurrent.ThreadSafe;

import com.helger.commons.annotations.Nonempty;
import com.helger.commons.annotations.ReturnsMutableCopy;
import com.helger.commons.annotations.ReturnsMutableObject;
import com.helger.commons.annotations.UsedViaReflection;
import com.helger.commons.callback.CallbackList;
import com.helger.commons.scopes.mgr.ScopeManager;
import com.helger.commons.scopes.singleton.ApplicationSingleton;
import com.helger.commons.string.ToStringGenerator;
import com.helger.web.scopes.domain.IRequestWebScopeWithoutResponse;
import com.helger.web.servlet.response.UnifiedResponse;

/**
 * This class represents a per-application singleton {@link IActionInvoker}.
 *
 * @author Philip Helger
 */
@ThreadSafe
public final class ApplicationActionManager extends ApplicationSingleton implements IActionInvoker
{
  // Main container
  private final IActionInvoker m_aInvoker = new ActionInvoker ();

  @Deprecated
  @UsedViaReflection
  public ApplicationActionManager ()
  {}

  @Nonnull
  public static ApplicationActionManager getInstance ()
  {
    return getApplicationSingleton (ApplicationActionManager.class);
  }

  @Nonnull
  public static ApplicationActionManager getInstanceOfScope (@Nonnull @Nonempty final String sApplicationID)
  {
    return getSingleton (ScopeManager.getApplicationScope (sApplicationID), ApplicationActionManager.class);
  }

  @Nonnull
  @ReturnsMutableObject (reason = "design")
  public CallbackList <IActionExceptionCallback> getExceptionCallbacks ()
  {
    return m_aInvoker.getExceptionCallbacks ();
  }

  @Nonnull
  @ReturnsMutableObject (reason = "design")
  public CallbackList <IActionBeforeExecutionCallback> getBeforeExecutionCallbacks ()
  {
    return m_aInvoker.getBeforeExecutionCallbacks ();
  }

  @Nonnull
  @ReturnsMutableObject (reason = "design")
  public CallbackList <IActionAfterExecutionCallback> getAfterExecutionCallbacks ()
  {
    return m_aInvoker.getAfterExecutionCallbacks ();
  }

  @CheckForSigned
  public long getLongRunningExecutionLimitTime ()
  {
    return m_aInvoker.getLongRunningExecutionLimitTime ();
  }

  public void setLongRunningExecutionLimitTime (final long nLongRunningExecutionLimitTime)
  {
    m_aInvoker.setLongRunningExecutionLimitTime (nLongRunningExecutionLimitTime);
  }

  @Nonnull
  @ReturnsMutableObject (reason = "design")
  public CallbackList <IActionLongRunningExecutionCallback> getLongRunningExecutionCallbacks ()
  {
    return m_aInvoker.getLongRunningExecutionCallbacks ();
  }

  @Nonnull
  @ReturnsMutableCopy
  public Map <String, IActionDeclaration> getAllRegisteredActions ()
  {
    return m_aInvoker.getAllRegisteredActions ();
  }

  @Nullable
  public IActionDeclaration getRegisteredAction (@Nullable final String sActionName)
  {
    return m_aInvoker.getRegisteredAction (sActionName);
  }

  @Nullable
  public IActionExecutor createExecutor (@Nullable final String sActionName)
  {
    return m_aInvoker.createExecutor (sActionName);
  }

  public boolean isRegisteredAction (@Nullable final String sActionName)
  {
    return m_aInvoker.isRegisteredAction (sActionName);
  }

  public void registerAction (@Nonnull final IActionDeclaration aAction)
  {
    m_aInvoker.registerAction (aAction);
  }

  public void invokeAction (@Nonnull final String sActionName,
                            @Nonnull final IActionExecutor aActionExecutor,
                            @Nonnull final IRequestWebScopeWithoutResponse aRequestScope,
                            @Nonnull final UnifiedResponse aUnifiedResponse) throws Throwable
  {
    m_aInvoker.invokeAction (sActionName, aActionExecutor, aRequestScope, aUnifiedResponse);
  }

  @Override
  public String toString ()
  {
    return ToStringGenerator.getDerived (super.toString ()).append ("invoker", m_aInvoker).toString ();
  }
}
