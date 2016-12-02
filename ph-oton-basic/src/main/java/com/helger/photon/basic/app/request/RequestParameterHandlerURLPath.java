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

import com.helger.commons.ValueEnforcer;
import com.helger.commons.annotation.Nonempty;
import com.helger.commons.annotation.ReturnsMutableCopy;
import com.helger.commons.collection.ext.ICommonsIterable;
import com.helger.commons.collection.ext.ICommonsList;
import com.helger.commons.string.StringHelper;
import com.helger.commons.string.ToStringGenerator;
import com.helger.commons.url.ISimpleURL;
import com.helger.commons.url.SimpleURL;
import com.helger.commons.url.URLParameter;
import com.helger.commons.url.URLParameterList;
import com.helger.web.scope.IRequestWebScopeWithoutResponse;

/**
 * An implementation of {@link IRequestParameterHandler} that takes the request
 * parameters from the URL path. It build URLs in the form
 * <code>basePath[/<i>paramName</i><b>separator</b><i>paramValue</i>]*</code>
 *
 * @author Philip Helger
 * @since 7.0.2
 */
@Immutable
public class RequestParameterHandlerURLPath implements IRequestParameterHandler
{
  /**
   * The separator char to be used if path based handling is enabled, to
   * separate name and value
   */
  public static final char DEFAULT_SEPARATOR_CHAR = '-';

  private final String m_sSeparator;

  /**
   * Default constructor using {@link #DEFAULT_SEPARATOR_CHAR} as the separator.
   */
  public RequestParameterHandlerURLPath ()
  {
    this (Character.toString (DEFAULT_SEPARATOR_CHAR));
  }

  /**
   * Constructor with a custom separator.
   *
   * @param sSeparator
   *        The separator to use. May neither be <code>null</code> nor empty.
   *        May not contain the "/" character!
   */
  public RequestParameterHandlerURLPath (@Nonnull @Nonempty final String sSeparator)
  {
    ValueEnforcer.notEmpty (sSeparator, "Separator");
    ValueEnforcer.isTrue (sSeparator.indexOf ('/') < 0, "The separator may not contain a path delimiter '/'!");
    m_sSeparator = sSeparator;
  }

  /**
   * @return The separator as passed in the constructor. Neither
   *         <code>null</code> nor empty.
   */
  @Nonnull
  @Nonempty
  public String getSeparator ()
  {
    return m_sSeparator;
  }

  @Nonnull
  protected URLParameterList getParametersFromPath (@Nonnull final String sPath)
  {
    // Use paths for standard menu items
    final URLParameterList ret = new URLParameterList ();
    for (final String sPair : StringHelper.getExploded ('/', StringHelper.trimStartAndEnd (sPath, "/")))
    {
      final ICommonsList <String> aElements = StringHelper.getExploded (m_sSeparator, sPair, 2);
      if (aElements.size () == 2)
        ret.add (aElements.get (0), aElements.get (1));
    }
    return ret;
  }

  @Nonnull
  @ReturnsMutableCopy
  public URLParameterList getParametersFromRequest (@Nonnull final IRequestWebScopeWithoutResponse aRequestScope)
  {
    return getParametersFromPath (aRequestScope.getPathInfo ());
  }

  @Nonnull
  @ReturnsMutableCopy
  public URLParameterList getParametersFromURL (@Nonnull final ISimpleURL aURL)
  {
    return getParametersFromPath (aURL.getPath ());
  }

  @Nonnull
  public SimpleURL buildURL (@Nonnull final IRequestWebScopeWithoutResponse aRequestScope,
                             @Nonnull @Nonempty final String sBasePath,
                             @Nullable final ICommonsIterable <? extends URLParameter> aParameters)
  {
    // Encode menuitem parameter into path
    final StringBuilder aFullPath = new StringBuilder (sBasePath);
    if (aParameters != null)
      for (final URLParameter aParam : aParameters)
      {
        final String sParamName = aParam.getName ();
        if (!isValidParameterName (sParamName))
          throw new IllegalArgumentException ("The passed parameter name '" +
                                              sParamName +
                                              "' contains the specified separator char and can therefore not be used!");
        aFullPath.append ('/').append (sParamName).append (m_sSeparator).append (aParam.getValue ());
      }

    return new SimpleURL (aRequestScope.encodeURL (aFullPath.toString ()));
  }

  public boolean isValidParameterName (@Nonnull final String sParamName)
  {
    // Separator may not be part of the parameter name
    return sParamName.indexOf (m_sSeparator) < 0;
  }

  @Override
  public String toString ()
  {
    return new ToStringGenerator (this).append ("Separator", m_sSeparator).toString ();
  }
}
