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
package com.helger.photon.api;

import java.util.function.Supplier;

import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.Nullable;

import com.helger.annotation.concurrent.NotThreadSafe;
import com.helger.annotation.style.ReturnsMutableObject;
import com.helger.base.enforce.ValueEnforcer;
import com.helger.base.lang.clazz.FactoryNewInstance;
import com.helger.base.string.StringHelper;
import com.helger.base.tostring.ToStringGenerator;
import com.helger.collection.CollectionHelper;
import com.helger.collection.commons.CommonsLinkedHashSet;
import com.helger.collection.commons.ICommonsOrderedSet;
import com.helger.photon.api.pathdescriptor.PathDescriptor;

/**
 * Default implementation of {@link IAPIDescriptor}.
 *
 * @author Philip Helger
 */
@NotThreadSafe
public class APIDescriptor implements IAPIDescriptor
{
  private final APIPath m_aAPIPath;
  // The path descriptor is automatically build from the APIPath
  private final PathDescriptor m_aPathDescriptor;
  private final Supplier <? extends IAPIExecutor> m_aExecutorFactory;
  private final ICommonsOrderedSet <String> m_aRequiredHeaders = new CommonsLinkedHashSet <> ();
  private final ICommonsOrderedSet <String> m_aRequiredParams = new CommonsLinkedHashSet <> ();
  private final ICommonsOrderedSet <String> m_aAllowedMimeTypes = new CommonsLinkedHashSet <> ();
  private IAPIExecutionFilter m_aExecutionFilter;
  private IAPIExceptionMapper m_aExceptionMapper;

  /**
   * Constructor
   *
   * @param aPath
   *        API Path to be used. May not be <code>null</code>.
   * @param aExecutor
   *        The executor to be invoked for that API. May not be
   *        <code>null</code>.
   */
  public APIDescriptor (@NonNull final APIPath aPath, @NonNull final IAPIExecutor aExecutor)
  {
    this (aPath, () -> aExecutor);
  }

  /**
   * Constructor
   *
   * @param aPath
   *        API Path to be used. May not be <code>null</code>.
   * @param aExecutorClass
   *        The executor class to be instantiated for every API invocation. May
   *        not be <code>null</code>.
   */
  public APIDescriptor (@NonNull final APIPath aPath, @NonNull final Class <? extends IAPIExecutor> aExecutorClass)
  {
    this (aPath, FactoryNewInstance.create (aExecutorClass));
  }

  /**
   * Constructor
   *
   * @param aPath
   *        API Path to be used. May not be <code>null</code>.
   * @param aExecutorFactory
   *        The factory to be used to create executor instances for every API
   *        invocation. May not be <code>null</code>.
   */
  public APIDescriptor (@NonNull final APIPath aPath, @NonNull final Supplier <? extends IAPIExecutor> aExecutorFactory)
  {
    m_aAPIPath = ValueEnforcer.notNull (aPath, "Path");
    m_aPathDescriptor = PathDescriptor.create (aPath.getPath ());
    m_aExecutorFactory = ValueEnforcer.notNull (aExecutorFactory, "ExecutorFactory");
  }

  @NonNull
  public final APIPath getAPIPath ()
  {
    return m_aAPIPath;
  }

  @NonNull
  public final PathDescriptor getPathDescriptor ()
  {
    return m_aPathDescriptor;
  }

  @NonNull
  public final Supplier <? extends IAPIExecutor> getExecutorFactory ()
  {
    return m_aExecutorFactory;
  }

  /**
   * Add a required HTTP header. If this HTTP header is not present, invocation
   * will not be possible.
   *
   * @param sHeaderName
   *        The name of the required HTTP header. May be <code>null</code> or
   *        empty in which case the header is ignored.
   * @return this for chaining
   * @see #addRequiredHeaders(String...)
   */
  @NonNull
  public final APIDescriptor addRequiredHeader (@Nullable final String sHeaderName)
  {
    if (StringHelper.isNotEmpty (sHeaderName))
      m_aRequiredHeaders.add (sHeaderName);
    return this;
  }

  /**
   * Add one or more required HTTP headers. If one of these HTTP headers are not
   * present, invocation will not be possible.
   *
   * @param aHeaderNames
   *        The names of the required HTTP headers. May be <code>null</code> or
   *        empty.
   * @return this for chaining
   * @see #addRequiredHeader(String)
   */
  @NonNull
  public final APIDescriptor addRequiredHeaders (@Nullable final String... aHeaderNames)
  {
    if (aHeaderNames != null)
      for (final String sHeaderName : aHeaderNames)
        addRequiredHeader (sHeaderName);
    return this;
  }

  @NonNull
  @ReturnsMutableObject
  public final ICommonsOrderedSet <String> requiredHeaders ()
  {
    return m_aRequiredHeaders;
  }

  /**
   * Add a required request parameter. If this request parameter is not present,
   * invocation will not be possible.
   *
   * @param sParamName
   *        The name of the required request parameter. May be <code>null</code>
   *        or empty in which case the parameter is ignored.
   * @return this for chaining
   * @see #addRequiredParams(String...)
   */
  @NonNull
  public final APIDescriptor addRequiredParam (@Nullable final String sParamName)
  {
    if (StringHelper.isNotEmpty (sParamName))
      m_aRequiredParams.add (sParamName);
    return this;
  }

  /**
   * Add one or more required request parameters. If one of these request
   * parameters are not present, invocation will not be possible.
   *
   * @param aParamNames
   *        The names of the required HTTP parameters. May be <code>null</code>
   *        or empty.
   * @return this for chaining
   * @see #addRequiredParam(String)
   */
  @NonNull
  public final APIDescriptor addRequiredParams (@Nullable final String... aParamNames)
  {
    if (aParamNames != null)
      for (final String sParamName : aParamNames)
        addRequiredParam (sParamName);
    return this;
  }

  @NonNull
  @ReturnsMutableObject
  public final ICommonsOrderedSet <String> requiredParams ()
  {
    return m_aRequiredParams;
  }

  @NonNull
  @ReturnsMutableObject
  public final ICommonsOrderedSet <String> allowedMimeTypes ()
  {
    return m_aAllowedMimeTypes;
  }

  @Nullable
  public final IAPIExecutionFilter getExecutionFilter ()
  {
    return m_aExecutionFilter;
  }

  @NonNull
  public final APIDescriptor setExecutionFilter (@Nullable final IAPIExecutionFilter aExecutionFilter)
  {
    m_aExecutionFilter = aExecutionFilter;
    return this;
  }

  @Nullable
  public final IAPIExceptionMapper getExceptionMapper ()
  {
    return m_aExceptionMapper;
  }

  @NonNull
  public final APIDescriptor setExceptionMapper (@Nullable final IAPIExceptionMapper aExceptionMapper)
  {
    m_aExceptionMapper = aExceptionMapper;
    return this;
  }

  @Override
  public String toString ()
  {
    return new ToStringGenerator (null).append ("APIPath", m_aAPIPath)
                                       .append ("PathDescriptor", m_aPathDescriptor)
                                       .append ("ExecutionFactory", m_aExecutorFactory)
                                       .appendIf ("RequiredHeaders", m_aRequiredHeaders, CollectionHelper::isNotEmpty)
                                       .appendIf ("RequiredParams", m_aRequiredParams, CollectionHelper::isNotEmpty)
                                       .appendIf ("AllowedMimeTypes", m_aAllowedMimeTypes, CollectionHelper::isNotEmpty)
                                       .appendIfNotNull ("ExecutionFilter", m_aExecutionFilter)
                                       .appendIfNotNull ("ExceptionMapper", m_aExceptionMapper)
                                       .getToString ();
  }
}
