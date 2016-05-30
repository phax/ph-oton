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
package com.helger.photon.core.ajax;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.concurrent.ThreadSafe;

import com.helger.commons.annotation.Nonempty;
import com.helger.commons.annotation.ReturnsMutableCopy;
import com.helger.commons.annotation.UsedViaReflection;
import com.helger.commons.collection.ext.ICommonsMap;
import com.helger.commons.scope.mgr.ScopeManager;
import com.helger.commons.string.ToStringGenerator;
import com.helger.photon.core.ajax.response.IAjaxResponse;
import com.helger.web.scope.IRequestWebScopeWithoutResponse;
import com.helger.web.scope.singleton.AbstractApplicationWebSingleton;

/**
 * A per-application AJAX manager.
 *
 * @author Philip Helger
 */
@ThreadSafe
public final class ApplicationAjaxManager extends AbstractApplicationWebSingleton implements IAjaxInvoker
{
  private final IAjaxInvoker m_aInvoker = new AjaxInvoker ();

  /**
   * Constructor. Used only internally.
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
  @ReturnsMutableCopy
  public ICommonsMap <String, IAjaxFunctionDeclaration> getAllRegisteredFunctions ()
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
