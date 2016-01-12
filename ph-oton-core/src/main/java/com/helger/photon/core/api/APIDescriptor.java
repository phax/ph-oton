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
package com.helger.photon.core.api;

import java.util.LinkedHashSet;
import java.util.Set;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.helger.commons.ValueEnforcer;
import com.helger.commons.annotation.ReturnsMutableCopy;
import com.helger.commons.collection.CollectionHelper;
import com.helger.commons.factory.FactoryConstantValue;
import com.helger.commons.factory.FactoryNewInstance;
import com.helger.commons.factory.IFactory;
import com.helger.commons.string.StringHelper;
import com.helger.commons.string.ToStringGenerator;
import com.helger.photon.core.api.pathdescriptor.PathDescriptor;

/**
 * Default implementation of {@link IAPIDescriptor}.
 *
 * @author Philip Helger
 */
public class APIDescriptor implements IAPIDescriptor
{
  private final APIPath m_aAPIPath;
  private final PathDescriptor m_aPathDescriptor;
  private final Set <String> m_aRequiredHeaders = new LinkedHashSet <String> ();
  private final Set <String> m_aRequiredParams = new LinkedHashSet <String> ();
  private final IFactory <? extends IAPIExecutor> m_aExecutorFactory;

  /**
   * Constructor
   *
   * @param aPath
   *        API Path to be used. May not be <code>null</code>.
   * @param aExecutor
   *        The executor to be invoked for that API. May not be
   *        <code>null</code>.
   */
  public APIDescriptor (@Nonnull final APIPath aPath, @Nonnull final IAPIExecutor aExecutor)
  {
    this (aPath, FactoryConstantValue.create (aExecutor));
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
  public APIDescriptor (@Nonnull final APIPath aPath, @Nonnull final Class <? extends IAPIExecutor> aExecutorClass)
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
  public APIDescriptor (@Nonnull final APIPath aPath, @Nonnull final IFactory <? extends IAPIExecutor> aExecutorFactory)
  {
    m_aAPIPath = ValueEnforcer.notNull (aPath, "Path");
    m_aPathDescriptor = PathDescriptor.create (aPath.getPath ());
    m_aExecutorFactory = ValueEnforcer.notNull (aExecutorFactory, "ExecutorFactory");
  }

  @Nonnull
  public APIPath getAPIPath ()
  {
    return m_aAPIPath;
  }

  @Nonnull
  public PathDescriptor getPathDescriptor ()
  {
    return m_aPathDescriptor;
  }

  @Nonnull
  public IFactory <? extends IAPIExecutor> getExecutorFactory ()
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
  @Nonnull
  public APIDescriptor addRequiredHeader (@Nullable final String sHeaderName)
  {
    if (StringHelper.hasText (sHeaderName))
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
  @Nonnull
  public APIDescriptor addRequiredHeaders (@Nullable final String... aHeaderNames)
  {
    if (aHeaderNames != null)
      for (final String sHeaderName : aHeaderNames)
        addRequiredHeader (sHeaderName);
    return this;
  }

  public boolean hasRequiredHeaders ()
  {
    return !m_aRequiredHeaders.isEmpty ();
  }

  @Nonnull
  @ReturnsMutableCopy
  public Set <String> getAllRequiredHeaders ()
  {
    return CollectionHelper.newOrderedSet (m_aRequiredHeaders);
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
  @Nonnull
  public APIDescriptor addRequiredParam (@Nullable final String sParamName)
  {
    if (StringHelper.hasText (sParamName))
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
  @Nonnull
  public APIDescriptor addRequiredParams (@Nullable final String... aParamNames)
  {
    if (aParamNames != null)
      for (final String sParamName : aParamNames)
        addRequiredParam (sParamName);
    return this;
  }

  public boolean hasRequiredParams ()
  {
    return !m_aRequiredParams.isEmpty ();
  }

  @Nonnull
  @ReturnsMutableCopy
  public Set <String> getAllRequiredParams ()
  {
    return CollectionHelper.newOrderedSet (m_aRequiredParams);
  }

  @Override
  public String toString ()
  {
    return new ToStringGenerator (null).append ("APIPath", m_aAPIPath)
                                       .append ("PathDescriptor", m_aPathDescriptor)
                                       .appendIfNotEmpty ("RequiredHeaders", m_aRequiredHeaders)
                                       .appendIfNotEmpty ("RequiredParams", m_aRequiredParams)
                                       .toString ();
  }
}