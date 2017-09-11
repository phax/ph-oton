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
import com.helger.commons.functional.IPredicate;
import com.helger.commons.functional.ISupplier;
import com.helger.commons.string.ToStringGenerator;
import com.helger.photon.core.ajax.AjaxInvoker;
import com.helger.photon.core.ajax.IAjaxExecutor;
import com.helger.photon.core.ajax.IAjaxFunctionDeclaration;
import com.helger.photon.core.ajax.servlet.PhotonAjaxServlet;
import com.helger.web.scope.IRequestWebScopeWithoutResponse;

/**
 * Abstract base implementation of {@link IAjaxFunctionDeclaration}
 *
 * @author Philip Helger
 */
@Immutable
public class AjaxFunctionDeclaration implements IAjaxFunctionDeclaration
{
  private final String m_sFunctionName;
  private final ISupplier <? extends IAjaxExecutor> m_aExecutorFactory;
  private final IPredicate <? super IRequestWebScopeWithoutResponse> m_aExecutionFilter;
  private final String m_sApplicationID;
  private final String m_sServletPath;

  public AjaxFunctionDeclaration (@Nonnull @Nonempty final String sFunctionName,
                                  @Nonnull final ISupplier <? extends IAjaxExecutor> aExecutorFactory,
                                  @Nullable final IPredicate <? super IRequestWebScopeWithoutResponse> aExecutionFilter,
                                  @Nullable final String sApplicationID,
                                  @Nonnull @Nonempty final String sServletPath)
  {
    ValueEnforcer.isTrue (AjaxInvoker.isValidFunctionName (sFunctionName), "Invalid Ajax functionName provided");
    ValueEnforcer.notNull (aExecutorFactory, "ExecutorFactory");
    ValueEnforcer.notEmpty (sServletPath, "ServletPath");
    ValueEnforcer.isTrue (sServletPath.startsWith ("/"), "Servlet path must start with /");
    ValueEnforcer.isTrue (sServletPath.endsWith ("/"), "Servlet path must end with /");
    m_sFunctionName = sFunctionName;
    m_aExecutorFactory = aExecutorFactory;
    m_aExecutionFilter = aExecutionFilter;
    m_sApplicationID = sApplicationID;
    m_sServletPath = sServletPath;
  }

  @Nonnull
  @Nonempty
  public final String getName ()
  {
    return m_sFunctionName;
  }

  @Nonnull
  public final ISupplier <? extends IAjaxExecutor> getExecutorFactory ()
  {
    return m_aExecutorFactory;
  }

  @Nullable
  public final IPredicate <? super IRequestWebScopeWithoutResponse> getExecutionFilter ()
  {
    return m_aExecutionFilter;
  }

  public boolean canExecute (@Nonnull final IRequestWebScopeWithoutResponse aRequestScope)
  {
    if (m_aExecutionFilter == null)
      return true;

    return m_aExecutionFilter.test (aRequestScope);
  }

  @Nullable
  public String getSpecialApplicationID ()
  {
    return m_sApplicationID;
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
                                       .appendIfNotNull ("SpecialApplicationID", m_sApplicationID)
                                       .getToString ();
  }

  @Nonnull
  public static Builder builder (@Nonnull @Nonempty final String sFunctionName)
  {
    return new Builder (sFunctionName);
  }

  /**
   * Builder for {@link AjaxFunctionDeclaration} objects.
   *
   * @author Philip Helger
   */
  public static class Builder
  {
    private final String m_sFunctionName;
    private ISupplier <? extends IAjaxExecutor> m_aExecutorFactory;
    private IPredicate <? super IRequestWebScopeWithoutResponse> m_aExecutionFilter;
    private String m_sApplicationID;
    private String m_sServletPath = PhotonAjaxServlet.SERVLET_DEFAULT_PATH + '/';

    public Builder (@Nonnull @Nonempty final String sFunctionName)
    {
      ValueEnforcer.notEmpty (sFunctionName, "FunctionName");
      m_sFunctionName = sFunctionName;
    }

    @Nonnull
    public final Builder withExecutor (@Nonnull final IAjaxExecutor aAjaxExecutor)
    {
      ValueEnforcer.notNull (aAjaxExecutor, "AjaxExecutor");
      return withExecutor ( () -> aAjaxExecutor);
    }

    @Nonnull
    public final Builder withExecutor (@Nonnull final Class <? extends IAjaxExecutor> aAjaxExecutorClass)
    {
      return withExecutor (FactoryNewInstance.create (aAjaxExecutorClass));
    }

    @Nonnull
    public final Builder withExecutor (@Nonnull final ISupplier <? extends IAjaxExecutor> aSupplier)
    {
      ValueEnforcer.notNull (aSupplier, "Supplier");
      m_aExecutorFactory = aSupplier;
      return this;
    }

    @Nonnull
    public final Builder withFilter (@Nonnull final IPredicate <? super IRequestWebScopeWithoutResponse> aFilter)
    {
      ValueEnforcer.notNull (aFilter, "Filter");
      m_aExecutionFilter = aFilter;
      return this;
    }

    @Nonnull
    public final Builder withApplicationID (@Nonnull @Nonempty final String sApplicationID)
    {
      ValueEnforcer.notEmpty (sApplicationID, "ApplicationID");
      m_sApplicationID = sApplicationID;
      return this;
    }

    @Nonnull
    public final Builder withServletPath (@Nonnull @Nonempty final String sServletPath)
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
      return new AjaxFunctionDeclaration (m_sFunctionName,
                                          m_aExecutorFactory,
                                          m_aExecutionFilter,
                                          m_sApplicationID,
                                          m_sServletPath);
    }
  }
}
