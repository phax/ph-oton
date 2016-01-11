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
import com.helger.commons.annotation.Nonempty;
import com.helger.commons.annotation.ReturnsMutableCopy;
import com.helger.commons.collection.CollectionHelper;
import com.helger.commons.factory.FactoryConstantValue;
import com.helger.commons.factory.FactoryNewInstance;
import com.helger.commons.factory.IFactory;
import com.helger.commons.string.StringHelper;
import com.helger.commons.string.ToStringGenerator;
import com.helger.photon.core.api.pathdescriptor.PathDescriptor;
import com.helger.web.http.EHTTPMethod;

/**
 * Default implementation of {@link IAPIDescriptor}.
 *
 * @author Philip Helger
 */
public class APIDescriptor implements IAPIDescriptor
{
  private final EHTTPMethod m_eMethod;
  private final PathDescriptor m_aPath;
  private final Set <String> m_aRequiredHeaders = new LinkedHashSet <String> ();
  private final Set <String> m_aRequiredParams = new LinkedHashSet <String> ();
  private final IFactory <? extends IAPIExecutor> m_aExecutorFactory;

  public APIDescriptor (@Nonnull final EHTTPMethod eMethod,
                        @Nonnull @Nonempty final String sPath,
                        @Nonnull final IFactory <? extends IAPIExecutor> aExecutorFactory)
  {
    m_eMethod = ValueEnforcer.notNull (eMethod, "Method");
    m_aPath = PathDescriptor.create (sPath);
    m_aExecutorFactory = ValueEnforcer.notNull (aExecutorFactory, "ExecutorFactory");
  }

  @Nonnull
  public EHTTPMethod getHTTPMethod ()
  {
    return m_eMethod;
  }

  @Nonnull
  public PathDescriptor getPath ()
  {
    return m_aPath;
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
    return new ToStringGenerator (null).append ("HTTPMethod", m_eMethod)
                                       .append ("Path", m_aPath)
                                       .appendIfNotEmpty ("RequiredHeaders", m_aRequiredHeaders)
                                       .appendIfNotEmpty ("RequiredParams", m_aRequiredParams)
                                       .toString ();
  }

  /**
   * Create an API descriptor using HTTP GET
   *
   * @param sPath
   *        Path, relative to the owning servlet. May neither be
   *        <code>null</code> nor empty.
   * @param aExecutor
   *        The executor to be invoked for that API. May not be
   *        <code>null</code>.
   * @return A non-<code>null</code> {@link APIDescriptor}.
   */
  @Nonnull
  public static APIDescriptor get (@Nonnull @Nonempty final String sPath, @Nonnull final IAPIExecutor aExecutor)
  {
    return get (sPath, FactoryConstantValue.create (aExecutor));
  }

  /**
   * Create an API descriptor using HTTP GET
   *
   * @param sPath
   *        Path, relative to the owning servlet. May neither be
   *        <code>null</code> nor empty.
   * @param aExecutorClass
   *        The executor class to be instantiated for every API invocation. May
   *        not be <code>null</code>.
   * @return A non-<code>null</code> {@link APIDescriptor}.
   */
  @Nonnull
  public static APIDescriptor get (@Nonnull @Nonempty final String sPath,
                                   @Nonnull final Class <? extends IAPIExecutor> aExecutorClass)
  {
    return get (sPath, FactoryNewInstance.create (aExecutorClass));
  }

  /**
   * Create an API descriptor using HTTP GET
   *
   * @param sPath
   *        Path, relative to the owning servlet. May neither be
   *        <code>null</code> nor empty.
   * @param aExecutorFactory
   *        The executor factory to be invoked for every API invocation. May not
   *        be <code>null</code>.
   * @return A non-<code>null</code> {@link APIDescriptor}.
   */
  @Nonnull
  public static APIDescriptor get (@Nonnull @Nonempty final String sPath,
                                   @Nonnull final IFactory <? extends IAPIExecutor> aExecutorFactory)
  {
    return new APIDescriptor (EHTTPMethod.GET, sPath, aExecutorFactory);
  }

  /**
   * Create an API descriptor using HTTP POST
   *
   * @param sPath
   *        Path, relative to the owning servlet. May neither be
   *        <code>null</code> nor empty.
   * @param aExecutor
   *        The executor to be invoked for that API. May not be
   *        <code>null</code>.
   * @return A non-<code>null</code> {@link APIDescriptor}.
   */
  @Nonnull
  public static APIDescriptor post (@Nonnull @Nonempty final String sPath, @Nonnull final IAPIExecutor aExecutor)
  {
    return post (sPath, FactoryConstantValue.create (aExecutor));
  }

  /**
   * Create an API descriptor using HTTP POST
   *
   * @param sPath
   *        Path, relative to the owning servlet. May neither be
   *        <code>null</code> nor empty.
   * @param aExecutorClass
   *        The executor class to be instantiated for every API invocation. May
   *        not be <code>null</code>.
   * @return A non-<code>null</code> {@link APIDescriptor}.
   */
  @Nonnull
  public static APIDescriptor post (@Nonnull @Nonempty final String sPath,
                                    @Nonnull final Class <? extends IAPIExecutor> aExecutorClass)
  {
    return post (sPath, FactoryNewInstance.create (aExecutorClass));
  }

  /**
   * Create an API descriptor using HTTP POST
   *
   * @param sPath
   *        Path, relative to the owning servlet. May neither be
   *        <code>null</code> nor empty.
   * @param aExecutorFactory
   *        The executor factory to be invoked for every API invocation. May not
   *        be <code>null</code>.
   * @return A non-<code>null</code> {@link APIDescriptor}.
   */
  @Nonnull
  public static APIDescriptor post (@Nonnull @Nonempty final String sPath,
                                    @Nonnull final IFactory <? extends IAPIExecutor> aExecutorFactory)
  {
    return new APIDescriptor (EHTTPMethod.POST, sPath, aExecutorFactory);
  }

  /**
   * Create an API descriptor using HTTP PUT
   *
   * @param sPath
   *        Path, relative to the owning servlet. May neither be
   *        <code>null</code> nor empty.
   * @param aExecutor
   *        The executor to be invoked for that API. May not be
   *        <code>null</code>.
   * @return A non-<code>null</code> {@link APIDescriptor}.
   */
  @Nonnull
  public static APIDescriptor put (@Nonnull @Nonempty final String sPath, @Nonnull final IAPIExecutor aExecutor)
  {
    return put (sPath, FactoryConstantValue.create (aExecutor));
  }

  /**
   * Create an API descriptor using HTTP PUT
   *
   * @param sPath
   *        Path, relative to the owning servlet. May neither be
   *        <code>null</code> nor empty.
   * @param aExecutorClass
   *        The executor class to be instantiated for every API invocation. May
   *        not be <code>null</code>.
   * @return A non-<code>null</code> {@link APIDescriptor}.
   */
  @Nonnull
  public static APIDescriptor put (@Nonnull @Nonempty final String sPath,
                                   @Nonnull final Class <? extends IAPIExecutor> aExecutorClass)
  {
    return put (sPath, FactoryNewInstance.create (aExecutorClass));
  }

  /**
   * Create an API descriptor using HTTP PUT
   *
   * @param sPath
   *        Path, relative to the owning servlet. May neither be
   *        <code>null</code> nor empty.
   * @param aExecutorFactory
   *        The executor factory to be invoked for every API invocation. May not
   *        be <code>null</code>.
   * @return A non-<code>null</code> {@link APIDescriptor}.
   */
  @Nonnull
  public static APIDescriptor put (@Nonnull @Nonempty final String sPath,
                                   @Nonnull final IFactory <? extends IAPIExecutor> aExecutorFactory)
  {
    return new APIDescriptor (EHTTPMethod.PUT, sPath, aExecutorFactory);
  }

  /**
   * Create an API descriptor using HTTP DELETE
   *
   * @param sPath
   *        Path, relative to the owning servlet. May neither be
   *        <code>null</code> nor empty.
   * @param aExecutor
   *        The executor to be invoked for that API. May not be
   *        <code>null</code>.
   * @return A non-<code>null</code> {@link APIDescriptor}.
   */
  @Nonnull
  public static APIDescriptor delete (@Nonnull @Nonempty final String sPath, @Nonnull final IAPIExecutor aExecutor)
  {
    return delete (sPath, FactoryConstantValue.create (aExecutor));
  }

  /**
   * Create an API descriptor using HTTP DELETE
   *
   * @param sPath
   *        Path, relative to the owning servlet. May neither be
   *        <code>null</code> nor empty.
   * @param aExecutorClass
   *        The executor class to be instantiated for every API invocation. May
   *        not be <code>null</code>.
   * @return A non-<code>null</code> {@link APIDescriptor}.
   */
  @Nonnull
  public static APIDescriptor delete (@Nonnull @Nonempty final String sPath,
                                      @Nonnull final Class <? extends IAPIExecutor> aExecutorClass)
  {
    return delete (sPath, FactoryNewInstance.create (aExecutorClass));
  }

  /**
   * Create an API descriptor using HTTP DELETE
   *
   * @param sPath
   *        Path, relative to the owning servlet. May neither be
   *        <code>null</code> nor empty.
   * @param aExecutorFactory
   *        The executor factory to be invoked for every API invocation. May not
   *        be <code>null</code>.
   * @return A non-<code>null</code> {@link APIDescriptor}.
   */
  @Nonnull
  public static APIDescriptor delete (@Nonnull @Nonempty final String sPath,
                                      @Nonnull final IFactory <? extends IAPIExecutor> aExecutorFactory)
  {
    return new APIDescriptor (EHTTPMethod.DELETE, sPath, aExecutorFactory);
  }
}
