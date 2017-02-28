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
package com.helger.photon.core.ajax.decl;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.concurrent.Immutable;

import com.helger.commons.ValueEnforcer;
import com.helger.commons.annotation.Nonempty;
import com.helger.commons.factory.FactoryNewInstance;
import com.helger.commons.factory.IFactory;
import com.helger.commons.filter.IFilter;
import com.helger.commons.string.ToStringGenerator;
import com.helger.photon.core.ajax.AjaxInvoker;
import com.helger.photon.core.ajax.IAjaxExecutor;
import com.helger.photon.core.ajax.IAjaxFunctionDeclaration;
import com.helger.web.scope.IRequestWebScopeWithoutResponse;

/**
 * Abstract base implementation of {@link IAjaxFunctionDeclaration}
 *
 * @author Philip Helger
 */
@Immutable
public abstract class AbstractAjaxFunctionDeclaration implements IAjaxFunctionDeclaration
{
  private final String m_sFunctionName;
  private final IFactory <? extends IAjaxExecutor> m_aExecutorFactory;
  private final IFilter <IRequestWebScopeWithoutResponse> m_aExecutionFilter;

  public AbstractAjaxFunctionDeclaration (@Nonnull @Nonempty final String sFunctionName,
                                          @Nonnull final IAjaxExecutor aExecutor)
  {
    this (sFunctionName, () -> aExecutor, (IFilter <IRequestWebScopeWithoutResponse>) null);
  }

  public AbstractAjaxFunctionDeclaration (@Nonnull @Nonempty final String sFunctionName,
                                          @Nonnull final Class <? extends IAjaxExecutor> aExecutorClass)
  {
    this (sFunctionName, FactoryNewInstance.create (aExecutorClass), (IFilter <IRequestWebScopeWithoutResponse>) null);
  }

  public AbstractAjaxFunctionDeclaration (@Nonnull @Nonempty final String sFunctionName,
                                          @Nonnull final IFactory <? extends IAjaxExecutor> aExecutorFactory,
                                          @Nullable final IFilter <IRequestWebScopeWithoutResponse> aExecutionFilter)
  {
    ValueEnforcer.isTrue (AjaxInvoker.isValidFunctionName (sFunctionName), "Invalid Ajax functionName provided");
    m_sFunctionName = sFunctionName;
    m_aExecutorFactory = ValueEnforcer.notNull (aExecutorFactory, "ExecutorFactory");
    m_aExecutionFilter = aExecutionFilter;
  }

  @Nonnull
  @Nonempty
  public final String getName ()
  {
    return m_sFunctionName;
  }

  @Nonnull
  public final IFactory <? extends IAjaxExecutor> getExecutorFactory ()
  {
    return m_aExecutorFactory;
  }

  @Nullable
  public final IFilter <IRequestWebScopeWithoutResponse> getExecutionFilter ()
  {
    return m_aExecutionFilter;
  }

  public boolean canExecute (@Nonnull final IRequestWebScopeWithoutResponse aRequestScope)
  {
    if (m_aExecutionFilter == null)
      return true;

    return m_aExecutionFilter.test (aRequestScope);
  }

  @Override
  public String toString ()
  {
    return new ToStringGenerator (this).append ("FunctionName", m_sFunctionName)
                                       .append ("ExecutorFactory", m_aExecutorFactory)
                                       .appendIfNotNull ("ExecutorFilter", m_aExecutionFilter)
                                       .getToString ();
  }
}
