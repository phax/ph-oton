/*
 * Copyright (C) 2014-2025 Philip Helger (www.helger.com)
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
package com.helger.photon.ajax.decl;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Predicate;
import java.util.function.Supplier;

import com.helger.annotation.Nonempty;
import com.helger.annotation.concurrent.Immutable;
import com.helger.base.builder.IBuilder;
import com.helger.base.enforce.ValueEnforcer;
import com.helger.base.lang.clazz.FactoryNewInstance;
import com.helger.base.string.StringHelper;
import com.helger.base.tostring.ToStringGenerator;
import com.helger.photon.ajax.AjaxRegistry;
import com.helger.photon.ajax.executor.IAjaxExecutor;
import com.helger.photon.ajax.servlet.PhotonAjaxServlet;
import com.helger.web.scope.IRequestWebScopeWithoutResponse;

import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;

/**
 * Abstract base implementation of {@link IAjaxFunctionDeclaration}
 *
 * @author Philip Helger
 */
@Immutable
public class AjaxFunctionDeclaration implements IAjaxFunctionDeclaration
{
  private final String m_sFunctionName;
  private final Supplier <? extends IAjaxExecutor> m_aExecutorFactory;
  private final Predicate <? super IRequestWebScopeWithoutResponse> m_aExecutionFilter;
  private final String m_sServletPath;

  public AjaxFunctionDeclaration (@Nonnull @Nonempty final String sFunctionName,
                                  @Nonnull final Supplier <? extends IAjaxExecutor> aExecutorFactory,
                                  @Nullable final Predicate <? super IRequestWebScopeWithoutResponse> aExecutionFilter,
                                  @Nonnull @Nonempty final String sServletPath)
  {
    ValueEnforcer.isTrue (AjaxRegistry.isValidFunctionName (sFunctionName), "Invalid Ajax functionName provided");
    ValueEnforcer.notNull (aExecutorFactory, "ExecutorFactory");
    ValueEnforcer.notEmpty (sServletPath, "ServletPath");
    ValueEnforcer.isTrue (sServletPath.startsWith ("/"), "Servlet path must start with /");
    ValueEnforcer.isTrue (sServletPath.endsWith ("/"), "Servlet path must end with /");
    m_sFunctionName = sFunctionName;
    m_aExecutorFactory = aExecutorFactory;
    m_aExecutionFilter = aExecutionFilter;
    m_sServletPath = sServletPath;
  }

  @Nonnull
  @Nonempty
  public final String getName ()
  {
    return m_sFunctionName;
  }

  @Nonnull
  public final Supplier <? extends IAjaxExecutor> getExecutorFactory ()
  {
    return m_aExecutorFactory;
  }

  @Nullable
  public final Predicate <? super IRequestWebScopeWithoutResponse> getExecutionFilter ()
  {
    return m_aExecutionFilter;
  }

  public boolean canExecute (@Nonnull final IRequestWebScopeWithoutResponse aRequestScope)
  {
    if (m_aExecutionFilter == null)
      return true;

    return m_aExecutionFilter.test (aRequestScope);
  }

  @Nonnull
  @Nonempty
  public String getAjaxServletPath ()
  {
    return m_sServletPath;
  }

  @Override
  public String toString ()
  {
    return new ToStringGenerator (this).append ("FunctionName", m_sFunctionName)
                                       .append ("ExecutorFactory", m_aExecutorFactory)
                                       .appendIfNotNull ("ExecutorFilter", m_aExecutionFilter)
                                       .appendIfNotNull ("ServletPath", m_sServletPath)
                                       .getToString ();
  }

  private static final AtomicInteger FUN_COUNTER = new AtomicInteger (0);

  /**
   * Create a function that is not named. The created name is ensured to be
   * unique. The registration is removed once the global context is shutdown so
   * the created path is not durable, as the next time the context is
   * initialized a different number might be assigned. Use
   * {@link #builder(String)} for a permanent name.
   *
   * @return A new function declaration builder. Never <code>null</code>.
   * @see #builder(String)
   */
  @Nonnull
  public static Builder builder ()
  {
    return builder (null);
  }

  public static int getUniqueFunctionID ()
  {
    return FUN_COUNTER.incrementAndGet ();
  }

  /**
   * Define the function name to use. If the parameter is empty, a "random"
   * function name, starting with <code>fun</code> is created.
   *
   * @param sFunctionName
   *        The function name to use.
   * @return A new {@link Builder} and never <code>null</code>.
   */
  @Nonnull
  public static Builder builder (@Nullable final String sFunctionName)
  {
    // Create dynamic name on demand
    final String sRealFunctionName = StringHelper.isNotEmpty (sFunctionName) ? sFunctionName
                                                                          : "fun" + getUniqueFunctionID ();
    return new Builder (sRealFunctionName);
  }

  /**
   * Builder for {@link AjaxFunctionDeclaration} objects.
   *
   * @author Philip Helger
   */
  public static class Builder implements IBuilder <AjaxFunctionDeclaration>
  {
    private final String m_sFunctionName;
    private Supplier <? extends IAjaxExecutor> m_aExecutorFactory;
    private Predicate <? super IRequestWebScopeWithoutResponse> m_aExecutionFilter;
    private String m_sServletPath = PhotonAjaxServlet.SERVLET_DEFAULT_PATH + '/';

    public Builder (@Nonnull @Nonempty final String sFunctionName)
    {
      ValueEnforcer.notEmpty (sFunctionName, "FunctionName");
      m_sFunctionName = sFunctionName;
    }

    @Nonnull
    public final Builder executor (@Nonnull final IAjaxExecutor aAjaxExecutor)
    {
      ValueEnforcer.notNull (aAjaxExecutor, "AjaxExecutor");
      return executor ( () -> aAjaxExecutor);
    }

    @Nonnull
    public final Builder executor (@Nonnull final Class <? extends IAjaxExecutor> aAjaxExecutorClass)
    {
      return executor (FactoryNewInstance.create (aAjaxExecutorClass));
    }

    @Nonnull
    public final Builder executor (@Nonnull final Supplier <? extends IAjaxExecutor> aSupplier)
    {
      ValueEnforcer.notNull (aSupplier, "Supplier");
      m_aExecutorFactory = aSupplier;
      return this;
    }

    @Nonnull
    public final Builder filter (@Nonnull final Predicate <? super IRequestWebScopeWithoutResponse> aFilter)
    {
      ValueEnforcer.notNull (aFilter, "Filter");
      m_aExecutionFilter = aFilter;
      return this;
    }

    @Nonnull
    public final Builder servletPath (@Nonnull @Nonempty final String sServletPath)
    {
      ValueEnforcer.notEmpty (sServletPath, "ServletPath");
      ValueEnforcer.isTrue (sServletPath.startsWith ("/"), "Servlet path must start with /");
      ValueEnforcer.isTrue (sServletPath.endsWith ("/"), "Servlet path must end with /");
      m_sServletPath = sServletPath;
      return this;
    }

    @Nonnull
    public AjaxFunctionDeclaration build ()
    {
      return new AjaxFunctionDeclaration (m_sFunctionName, m_aExecutorFactory, m_aExecutionFilter, m_sServletPath);
    }
  }
}
