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
package com.helger.photon.core.ajax.executor;

import javax.annotation.Nonnull;
import javax.annotation.concurrent.NotThreadSafe;

import com.helger.commons.annotation.OverrideOnDemand;
import com.helger.commons.collection.attr.IMutableAttributeContainerAny;
import com.helger.commons.lang.ClassHelper;
import com.helger.commons.string.ToStringGenerator;
import com.helger.photon.core.ajax.IAjaxExecutor;
import com.helger.photon.core.ajax.response.IAjaxResponse;
import com.helger.web.scope.domain.IRequestWebScopeWithoutResponse;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;

/**
 * Provides a common implementation of the {@link IAjaxExecutor} interface for
 * as easy reuse as possible.
 *
 * @author Philip Helger
 */
@NotThreadSafe
public abstract class AbstractAjaxExecutor implements IAjaxExecutor
{
  /**
   * The name of the request parameter used by jQuery to indicate "no cache".
   * Use this constant for parameter filtering.
   */
  public static final String REQUEST_PARAM_JQUERY_NO_CACHE = "_";

  public AbstractAjaxExecutor ()
  {}

  @OverrideOnDemand
  public void initExecution (@Nonnull final IRequestWebScopeWithoutResponse aRequestScope)
  {
    // By default do nothing
  }

  @OverrideOnDemand
  public void registerExternalResources ()
  {
    // empty default implementation
  }

  @OverrideOnDemand
  protected void modifyRequestParamMap (@Nonnull final IMutableAttributeContainerAny <String> aParams)
  {
    // Remove the jQuery timestamp parameter
    aParams.removeAttribute (REQUEST_PARAM_JQUERY_NO_CACHE);
  }

  /**
   * This method must be overridden by every handler
   *
   * @param aRequestScope
   *        The current request scope. Never <code>null</code>.
   * @return the result object. May not be <code>null</code>
   * @throws Exception
   */
  @OverrideOnDemand
  @Nonnull
  protected abstract IAjaxResponse mainHandleRequest (@Nonnull IRequestWebScopeWithoutResponse aRequestScope) throws Exception;

  @Nonnull
  @SuppressFBWarnings ("RCN_REDUNDANT_NULLCHECK_OF_NONNULL_VALUE")
  public final IAjaxResponse handleRequest (@Nonnull final IRequestWebScopeWithoutResponse aRequestScope) throws Exception
  {
    // Main invocation
    final IAjaxResponse aResult = mainHandleRequest (aRequestScope);
    if (aResult == null)
      throw new IllegalStateException ("Invocation of " +
                                       ClassHelper.getClassLocalName (getClass ()) +
                                       " returned null response!");

    // Return invocation result
    return aResult;
  }

  @Override
  public String toString ()
  {
    return new ToStringGenerator (this).toString ();
  }
}
