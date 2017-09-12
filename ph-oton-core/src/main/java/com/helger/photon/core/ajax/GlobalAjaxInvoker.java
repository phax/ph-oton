/**
 * Copyright (C) 2014-2017 Philip Helger (www.helger.com)
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

import com.helger.commons.annotation.ReturnsMutableCopy;
import com.helger.commons.annotation.UsedViaReflection;
import com.helger.commons.collection.impl.ICommonsMap;
import com.helger.commons.string.ToStringGenerator;
import com.helger.photon.core.PhotonUnifiedResponse;
import com.helger.web.scope.IRequestWebScopeWithoutResponse;
import com.helger.web.scope.singleton.AbstractGlobalWebSingleton;

/**
 * Global AJAX invoker
 *
 * @author Philip Helger
 */
@ThreadSafe
public final class GlobalAjaxInvoker extends AbstractGlobalWebSingleton implements IAjaxInvoker
{
  private final IAjaxInvoker m_aInvoker = new AjaxInvoker ();

  /**
   * Constructor. Used only internally.
   */
  @Deprecated
  @UsedViaReflection
  public GlobalAjaxInvoker ()
  {}

  @Nonnull
  public static GlobalAjaxInvoker getInstance ()
  {
    return getGlobalSingleton (GlobalAjaxInvoker.class);
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

  public void invokeFunction (@Nonnull final String sFunctionName,
                              @Nonnull final IAjaxExecutor aAjaxExecutor,
                              @Nonnull final IRequestWebScopeWithoutResponse aRequestScope,
                              @Nonnull final PhotonUnifiedResponse aAjaxResponse) throws Exception
  {
    m_aInvoker.invokeFunction (sFunctionName, aAjaxExecutor, aRequestScope, aAjaxResponse);
  }

  @Override
  public String toString ()
  {
    return ToStringGenerator.getDerived (super.toString ()).append ("Invoker", m_aInvoker).getToString ();
  }
}
