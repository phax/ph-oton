/*
 * Copyright (C) 2014-2026 Philip Helger (www.helger.com)
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

import java.io.Serializable;

import org.jspecify.annotations.NonNull;

import com.helger.annotation.Nonempty;
import com.helger.annotation.concurrent.Immutable;
import com.helger.annotation.style.MustImplementEqualsAndHashcode;
import com.helger.base.enforce.ValueEnforcer;
import com.helger.base.hashcode.HashCodeGenerator;
import com.helger.base.tostring.ToStringGenerator;
import com.helger.http.EHttpMethod;
import com.helger.io.file.FilenameHelper;
import com.helger.servlet.request.RequestHelper;
import com.helger.url.SimpleURL;
import com.helger.web.scope.IRequestWebScopeWithoutResponse;

/**
 * This class contains a combination of path and HTTP method.
 *
 * @author Philip Helger
 */
@Immutable
@MustImplementEqualsAndHashcode
public class APIPath implements Serializable
{
  private final EHttpMethod m_eHttpMethod;
  private final String m_sPath;

  /**
   * Constructor
   *
   * @param eHttpMethod
   *        The HTTP method to be used. May not be <code>null</code>.
   * @param sPath
   *        The path relative to the owning servlet.
   */
  public APIPath (@NonNull final EHttpMethod eHttpMethod, @NonNull @Nonempty final String sPath)
  {
    m_eHttpMethod = ValueEnforcer.notNull (eHttpMethod, "HttpMethod");
    m_sPath = ValueEnforcer.notEmpty (sPath, "Path");
  }

  /**
   * @return The HTTP method as provided in the constructor. Never
   *         <code>null</code>.
   */
  @NonNull
  public EHttpMethod getHttpMethod ()
  {
    return m_eHttpMethod;
  }

  /**
   * @return The path as provided in the constructor. Neither <code>null</code>
   *         nor empty.
   */
  @NonNull
  @Nonempty
  public String getPath ()
  {
    return m_sPath;
  }

  /**
   * Get the invocation URL of this API path.
   *
   * @param sBasePath
   *        The HTTP base path of the server (e.g. http://www.example.org/api"),
   *        which is prepended to the relative path of this object. May not be
   *        <code>null</code> but maybe empty (since v8.1.4)
   * @return The new URL. Never <code>null</code>.
   * @see #getPath()
   */
  @NonNull
  public SimpleURL getInvocationURL (@NonNull final String sBasePath)
  {
    ValueEnforcer.notNull (sBasePath, "BasePath");
    return new SimpleURL (sBasePath + m_sPath);
  }

  @Override
  public boolean equals (final Object o)
  {
    if (o == this)
      return true;
    if (o == null || !getClass ().equals (o.getClass ()))
      return false;
    final APIPath rhs = (APIPath) o;
    return m_eHttpMethod.equals (rhs.m_eHttpMethod) && m_sPath.equals (rhs.m_sPath);
  }

  @Override
  public int hashCode ()
  {
    return new HashCodeGenerator (this).append (m_eHttpMethod).append (m_sPath).getHashCode ();
  }

  @Override
  public String toString ()
  {
    return new ToStringGenerator (null).append ("HTTPMethod", m_eHttpMethod).append ("Path", m_sPath).getToString ();
  }

  @NonNull
  public static APIPath get (@NonNull @Nonempty final String sPath)
  {
    return new APIPath (EHttpMethod.GET, sPath);
  }

  @NonNull
  public static APIPath post (@NonNull @Nonempty final String sPath)
  {
    return new APIPath (EHttpMethod.POST, sPath);
  }

  @NonNull
  public static APIPath put (@NonNull @Nonempty final String sPath)
  {
    return new APIPath (EHttpMethod.PUT, sPath);
  }

  @NonNull
  public static APIPath delete (@NonNull @Nonempty final String sPath)
  {
    return new APIPath (EHttpMethod.DELETE, sPath);
  }

  @NonNull
  public static APIPath createForFilter (@NonNull final IRequestWebScopeWithoutResponse aRequestScope)
  {
    // Ensure to use the encoded path
    String sPath = RequestHelper.getPathWithinServletContext (aRequestScope.getRequest (), true);

    // ensure leading "/"
    if (sPath != null && !FilenameHelper.startsWithPathSeparatorChar (sPath))
      sPath = '/' + sPath;

    return new APIPath (aRequestScope.getHttpMethod (), sPath);
  }

  @NonNull
  public static APIPath createForServlet (@NonNull final IRequestWebScopeWithoutResponse aRequestScope)
  {
    // Ensure to use the encoded path
    String sPath = RequestHelper.getPathWithinServlet (aRequestScope.getRequest (), true);

    // ensure leading "/"
    if (sPath != null && !FilenameHelper.startsWithPathSeparatorChar (sPath))
      sPath = '/' + sPath;

    return new APIPath (aRequestScope.getHttpMethod (), sPath);
  }
}
