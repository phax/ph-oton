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
package com.helger.photon.basic.app.request;

import java.io.Serializable;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.helger.commons.annotation.Nonempty;
import com.helger.commons.annotation.ReturnsMutableCopy;
import com.helger.commons.collection.ext.ICommonsIterable;
import com.helger.commons.url.ISimpleURL;
import com.helger.commons.url.SimpleURL;
import com.helger.commons.url.URLParameter;
import com.helger.commons.url.URLParameterList;
import com.helger.web.scope.IRequestWebScopeWithoutResponse;

/**
 * Interface for extracting parameters from URLs.
 *
 * @author Philip Helger
 * @since 7.0.2
 */
public interface IRequestParameterHandler extends Serializable
{
  /**
   * Get all request parameters from the provided request.
   * 
   * @param aRequestScope
   *        The current request scope. May not be <code>null</code>.
   * @return A non-<code>null</code> list of all parameters.
   */
  @Nonnull
  @ReturnsMutableCopy
  URLParameterList getParametersFromRequest (@Nonnull IRequestWebScopeWithoutResponse aRequestScope);

  /**
   * Get all request parameters from the provided URL.
   * 
   * @param aURL
   *        The URL to extract the parameters from. May not be
   *        <code>null</code>.
   * @return A non-<code>null</code> list of all parameters.
   */
  @Nonnull
  @ReturnsMutableCopy
  URLParameterList getParametersFromURL (@Nonnull ISimpleURL aURL);

  /**
   * Build a URL based on the passed base path and an optional list of
   * parameters.
   *
   * @param aRequestScope
   *        Current request scope. May not be <code>null</code>.
   * @param sBasePath
   *        The base path to use. May not be <code>null</code> and must NOT end
   *        with a "/".
   * @param aParameters
   *        The list of parameters to add. May be <code>null</code> or empty.
   * @return The URL to use.
   */
  @Nonnull
  SimpleURL buildURL (@Nonnull IRequestWebScopeWithoutResponse aRequestScope,
                      @Nonnull @Nonempty String sBasePath,
                      @Nullable ICommonsIterable <? extends URLParameter> aParameters);

  /**
   * Check if the passed parameter name is valid according to the rules of this
   * parameter handler.
   *
   * @param sParamName
   *        The parameter name to check. May not be <code>null</code>.
   * @return <code>true</code> if the parameter is valid, <code>false</code>
   *         otherwise.
   */
  default boolean isValidParameterName (@Nonnull final String sParamName)
  {
    return true;
  }
}
