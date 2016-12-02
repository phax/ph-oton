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

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.concurrent.Immutable;

import com.helger.commons.annotation.Nonempty;
import com.helger.commons.annotation.ReturnsMutableCopy;
import com.helger.commons.collection.ext.ICommonsIterable;
import com.helger.commons.string.ToStringGenerator;
import com.helger.commons.url.ISimpleURL;
import com.helger.commons.url.SimpleURL;
import com.helger.commons.url.URLParameter;
import com.helger.commons.url.URLParameterList;
import com.helger.web.scope.IRequestWebScopeWithoutResponse;

/**
 * An implementation of {@link IRequestParameterHandler} that takes the request
 * parameters from the URL parameters. It build URLs in the form
 * <code>basePath[[?&amp;]<i>paramName</i>=<i>paramValue</i>]*</code>
 *
 * @author Philip Helger
 * @since 7.0.2
 */
@Immutable
public class RequestParameterHandlerURLParameter implements IRequestParameterHandler
{
  public RequestParameterHandlerURLParameter ()
  {}

  @Nonnull
  @ReturnsMutableCopy
  public URLParameterList getParametersFromRequest (@Nonnull final IRequestWebScopeWithoutResponse aRequestScope)
  {
    // Use request parameters
    final URLParameterList ret = new URLParameterList ();
    aRequestScope.forAllAttributes ( (k, v) -> {
      if (v instanceof String)
        ret.add (k, (String) v);
    });
    return ret;
  }

  @Nonnull
  @ReturnsMutableCopy
  public URLParameterList getParametersFromURL (@Nonnull final ISimpleURL aURL)
  {
    // Use request parameters
    return aURL.getAllParams ();
  }

  @Nonnull
  public SimpleURL buildURL (@Nonnull final IRequestWebScopeWithoutResponse aRequestScope,
                             @Nonnull @Nonempty final String sBasePath,
                             @Nullable final ICommonsIterable <? extends URLParameter> aParameters)
  {
    // Add menu item parameter as URL parameter
    final SimpleURL ret = new SimpleURL (aRequestScope.encodeURL (sBasePath));
    if (aParameters != null)
      for (final URLParameter aParam : aParameters)
        ret.add (aParam);
    return ret;
  }

  @Override
  public String toString ()
  {
    return new ToStringGenerator (this).toString ();
  }
}
