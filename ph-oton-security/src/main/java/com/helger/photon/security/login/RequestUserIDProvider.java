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
package com.helger.photon.security.login;

import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.Nullable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.helger.annotation.Nonempty;
import com.helger.annotation.style.UsedViaReflection;
import com.helger.base.enforce.ValueEnforcer;
import com.helger.base.state.EChange;
import com.helger.base.tostring.ToStringGenerator;
import com.helger.web.scope.mgr.WebScopeManager;
import com.helger.web.scope.singleton.AbstractRequestWebSingleton;

/**
 * This class holds the ID of the user that was authenticated for the current request only. It is
 * meant to be used for stateless authentication - like REST APIs using HTTP Basic Auth or Bearer
 * tokens - where the user is deliberately not logged into a UI session. Because the data is stored
 * in the request scope only, it can never leak into another request and it never creates an HTTP
 * session.<br>
 * The stored user ID is preferred over the session user ID by
 * {@link GlobalUserIDProvider#DEFAULT_SUPPLIER}, so that auditing and the creation of business
 * objects are correctly attributed to the authenticated user.
 *
 * @author Philip Helger
 * @since 10.5.0
 */
public final class RequestUserIDProvider extends AbstractRequestWebSingleton
{
  private static final Logger LOGGER = LoggerFactory.getLogger (RequestUserIDProvider.class);

  private String m_sUserID;

  @Deprecated (forRemoval = false)
  @UsedViaReflection
  public RequestUserIDProvider ()
  {}

  /**
   * @return The instance of the current request. If none exists, an instance is created. Never
   *         <code>null</code>.
   */
  @NonNull
  private static RequestUserIDProvider _getInstance ()
  {
    return getRequestSingleton (RequestUserIDProvider.class);
  }

  /**
   * @return The instance of the current request. If none exists, <code>null</code> is returned.
   *         This also works if no request scope is present at all.
   */
  @Nullable
  private static RequestUserIDProvider _getInstanceIfInstantiated ()
  {
    return getRequestSingletonIfInstantiated (RequestUserIDProvider.class);
  }

  /**
   * Remember the ID of the user that was authenticated for the current request. If no request web
   * scope is present, this method has no effect.
   *
   * @param sUserID
   *        The ID of the authenticated user. May neither be <code>null</code> nor empty.
   */
  public static void setCurrentUserID (@NonNull @Nonempty final String sUserID)
  {
    ValueEnforcer.notEmpty (sUserID, "UserID");

    if (!WebScopeManager.isRequestScopePresent ())
    {
      // E.g. if the authentication happens outside of a web request
      if (LOGGER.isDebugEnabled ())
        LOGGER.debug ("Not remembering the request user ID '" +
                      sUserID +
                      "' because no request web scope is present");
      return;
    }

    _getInstance ().m_sUserID = sUserID;
  }

  /**
   * Remove the user ID of the current request, if any is present.
   *
   * @return {@link EChange#CHANGED} if a user ID was removed, {@link EChange#UNCHANGED} otherwise.
   */
  @NonNull
  public static EChange removeCurrentUserID ()
  {
    final RequestUserIDProvider aInstance = _getInstanceIfInstantiated ();
    if (aInstance == null || aInstance.m_sUserID == null)
      return EChange.UNCHANGED;

    aInstance.m_sUserID = null;
    return EChange.CHANGED;
  }

  /**
   * @return The ID of the user that was authenticated for the current request or <code>null</code>
   *         if no such user is present. This also returns <code>null</code> if no request scope is
   *         present at all.
   */
  @Nullable
  public static String getCurrentUserID ()
  {
    final RequestUserIDProvider aInstance = _getInstanceIfInstantiated ();
    return aInstance == null ? null : aInstance.m_sUserID;
  }

  @Override
  public String toString ()
  {
    return ToStringGenerator.getDerived (super.toString ()).append ("UserID", m_sUserID).getToString ();
  }
}
