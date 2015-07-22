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
package com.helger.photon.core.ajax;

import java.util.Map;

import javax.annotation.CheckForSigned;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.concurrent.ThreadSafe;

import com.helger.commons.annotation.Nonempty;
import com.helger.commons.annotation.ReturnsMutableCopy;
import com.helger.commons.annotation.ReturnsMutableObject;
import com.helger.commons.annotation.UsedViaReflection;
import com.helger.commons.callback.CallbackList;
import com.helger.commons.scope.mgr.ScopeManager;
import com.helger.commons.scope.singleton.AbstractApplicationSingleton;
import com.helger.commons.string.ToStringGenerator;
import com.helger.photon.core.ajax.response.IAjaxResponse;
import com.helger.web.scope.domain.IRequestWebScopeWithoutResponse;

/**
 * A per-application AJAX manager.
 *
 * @author Philip Helger
 */
@ThreadSafe
public class ApplicationAjaxManager extends AbstractApplicationSingleton implements IAjaxInvoker
{
  private final IAjaxInvoker m_aInvoker = new AjaxInvoker ();

  /**
   * Private constructor. Avoid outside instantiation
   */
  @Deprecated
  @UsedViaReflection
  public ApplicationAjaxManager ()
  {}

  @Nonnull
  public static ApplicationAjaxManager getInstance ()
  {
    return getApplicationSingleton (ApplicationAjaxManager.class);
  }

  @Nonnull
  public static ApplicationAjaxManager getInstanceOfScope (@Nonnull @Nonempty final String sApplicationID)
  {
    return getSingleton (ScopeManager.getApplicationScope (sApplicationID), ApplicationAjaxManager.class);
  }

  @Nonnull
  @ReturnsMutableObject ("design")
  public CallbackList <IAjaxExceptionCallback> getExceptionCallbacks ()
  {
    return m_aInvoker.getExceptionCallbacks ();
  }

  @Nonnull
  @ReturnsMutableObject ("design")
  public CallbackList <IAjaxBeforeExecutionCallback> getBeforeExecutionCallbacks ()
  {
    return m_aInvoker.getBeforeExecutionCallbacks ();
  }

  @Nonnull
  @ReturnsMutableObject ("design")
  public CallbackList <IAjaxAfterExecutionCallback> getAfterExecutionCallbacks ()
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
  @ReturnsMutableObject ("design")
  public CallbackList <IAjaxLongRunningExecutionCallback> getLongRunningExecutionCallbacks ()
  {
    return m_aInvoker.getLongRunningExecutionCallbacks ();
  }

  @Nonnull
  @ReturnsMutableCopy
  public Map <String, IAjaxFunctionDeclaration> getAllRegisteredFunctions ()
  {
    return m_aInvoker.getAllRegisteredFunctions ();
  }

  @Nullable
  public IAjaxFunctionDeclaration getRegisteredFunction (@Nullable final String sFunctionName)
  {
    return m_aInvoker.getRegisteredFunction (sFunctionName);
  }

  public boolean isRegisteredFunction (@Nullable final String sFunctionName)
  {
    return m_aInvoker.isRegisteredFunction (sFunctionName);
  }

  public void registerFunction (@Nonnull final IAjaxFunctionDeclaration aFunction)
  {
    m_aInvoker.registerFunction (aFunction);
  }

  @Nonnull
  public IAjaxResponse invokeFunction (@Nonnull final String sFunctionName,
                                       @Nonnull final IAjaxExecutor aAjaxExecutor,
                                       @Nonnull final IRequestWebScopeWithoutResponse aRequestScope) throws Throwable
  {
    return m_aInvoker.invokeFunction (sFunctionName, aAjaxExecutor, aRequestScope);
  }

  @Override
  public String toString ()
  {
    return ToStringGenerator.getDerived (super.toString ()).append ("invoker", m_aInvoker).toString ();
  }
}
