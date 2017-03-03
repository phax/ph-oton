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
package com.helger.photon.core.ajax.response;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.concurrent.Immutable;
import javax.servlet.http.HttpServletResponse;

import com.helger.commons.ValueEnforcer;
import com.helger.commons.hashcode.HashCodeGenerator;
import com.helger.commons.string.ToStringGenerator;
import com.helger.commons.url.ISimpleURL;
import com.helger.servlet.response.ERedirectMode;
import com.helger.servlet.response.UnifiedResponse;

/**
 * A simple AJAX response that sends an HTTP status code only
 *
 * @author Philip Helger
 */
@Immutable
public class AjaxHttpStatusCodeResponse extends AbstractAjaxResponse
{
  private final int m_nStatusCode;
  private ISimpleURL m_aLocation;

  public AjaxHttpStatusCodeResponse (final int nStatusCode)
  {
    super (true);
    m_nStatusCode = nStatusCode;
  }

  /**
   * @return The HTTP status code as provided in the constructor.
   */
  @Nonnull
  public int getStatusCode ()
  {
    return m_nStatusCode;
  }

  /**
   * @return The optional location for redirects. May be <code>null</code>.
   * @since 7.0.4
   */
  @Nullable
  public ISimpleURL getLocation ()
  {
    return m_aLocation;
  }

  /**
   * Set the HTTP header "Location"
   *
   * @param aLocation
   *        The location for redirects. May be <code>null</code>.
   * @return this for chaining
   * @since 7.0.4
   */
  public AjaxHttpStatusCodeResponse setLocation (@Nullable final ISimpleURL aLocation)
  {
    m_aLocation = aLocation;
    return this;
  }

  public void applyToResponse (@Nonnull final UnifiedResponse aUnifiedResponse)
  {
    if (m_aLocation != null)
      aUnifiedResponse.setRedirect (m_aLocation, ERedirectMode.POST_REDIRECT_GET);
    else
      aUnifiedResponse.setStatus (m_nStatusCode);
  }

  @Override
  public boolean equals (final Object o)
  {
    if (o == this)
      return true;
    if (!super.equals (o))
      return false;
    final AjaxHttpStatusCodeResponse rhs = (AjaxHttpStatusCodeResponse) o;
    return m_nStatusCode == rhs.m_nStatusCode;
  }

  @Override
  public int hashCode ()
  {
    return HashCodeGenerator.getDerived (super.hashCode ()).append (m_nStatusCode).getHashCode ();
  }

  @Override
  public String toString ()
  {
    return ToStringGenerator.getDerived (super.toString ()).append ("StatusCode", m_nStatusCode).getToString ();
  }

  /**
   * @return HTTP 200 OK
   */
  @Nonnull
  public static AjaxHttpStatusCodeResponse createOk ()
  {
    return new AjaxHttpStatusCodeResponse (HttpServletResponse.SC_OK);
  }

  /**
   * @return HTTP 204 No Content
   */
  @Nonnull
  public static AjaxHttpStatusCodeResponse createNoContent ()
  {
    return new AjaxHttpStatusCodeResponse (HttpServletResponse.SC_NO_CONTENT);
  }

  /**
   * @param aLocation
   *        The redirect URL. May not be <code>null</code>.
   * @return HTTP 303 See Other
   * @since 7.0.4
   */
  @Nonnull
  public static AjaxHttpStatusCodeResponse createSeeOther (@Nonnull final ISimpleURL aLocation)
  {
    ValueEnforcer.notNull (aLocation, "Location");

    final AjaxHttpStatusCodeResponse ret = new AjaxHttpStatusCodeResponse (HttpServletResponse.SC_SEE_OTHER);
    ret.setLocation (aLocation);
    return ret;
  }

  /**
   * @return HTTP 400 Bad Request
   * @since 7.0.4
   */
  @Nonnull
  public static AjaxHttpStatusCodeResponse createBadRequest ()
  {
    return new AjaxHttpStatusCodeResponse (HttpServletResponse.SC_BAD_REQUEST);
  }

  /**
   * @return HTTP 404 Not Found
   */
  @Nonnull
  public static AjaxHttpStatusCodeResponse createNotFound ()
  {
    return new AjaxHttpStatusCodeResponse (HttpServletResponse.SC_NOT_FOUND);
  }

  /**
   * @return HTTP 412 Precondition Failed
   */
  @Nonnull
  public static AjaxHttpStatusCodeResponse createPreconditionFailed ()
  {
    return new AjaxHttpStatusCodeResponse (HttpServletResponse.SC_PRECONDITION_FAILED);
  }

  /**
   * @return HTTP 500 Internal Server Error
   */
  @Nonnull
  public static AjaxHttpStatusCodeResponse createInternalServerError ()
  {
    return new AjaxHttpStatusCodeResponse (HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
  }
}
