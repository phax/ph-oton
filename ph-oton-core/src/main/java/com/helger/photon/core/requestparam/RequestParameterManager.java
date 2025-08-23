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
package com.helger.photon.core.requestparam;

import java.util.Locale;

import com.helger.annotation.Nonempty;
import com.helger.annotation.concurrent.NotThreadSafe;
import com.helger.annotation.style.UsedViaReflection;
import com.helger.base.enforce.ValueEnforcer;
import com.helger.base.tostring.ToStringGenerator;
import com.helger.photon.core.appid.PhotonGlobalState;
import com.helger.url.SimpleURL;
import com.helger.web.scope.IRequestWebScopeWithoutResponse;
import com.helger.web.scope.singleton.AbstractGlobalWebSingleton;

import jakarta.annotation.Nonnull;

/**
 * This class holds the per-request configuration settings.
 * <ul>
 * <li>Menu item to show</li>
 * <li>Display locale</li>
 * </ul>
 *
 * @author Philip Helger
 * @since 7.0.2
 */
@NotThreadSafe
public class RequestParameterManager extends AbstractGlobalWebSingleton implements IRequestParameterManager
{
  private IRequestParameterHandler m_aRequestParamHdl = new RequestParameterHandlerURLParameter ();

  @Deprecated (forRemoval = false)
  @UsedViaReflection
  public RequestParameterManager ()
  {}

  @Nonnull
  public static RequestParameterManager getInstance ()
  {
    return getGlobalSingleton (RequestParameterManager.class);
  }

  @Nonnull
  public final IRequestParameterHandler getParameterHandler ()
  {
    return m_aRequestParamHdl;
  }

  public final void setParameterHandler (@Nonnull final IRequestParameterHandler aRequestParameterHdl)
  {
    ValueEnforcer.notNull (aRequestParameterHdl, "RequestParameterHdl");
    m_aRequestParamHdl = aRequestParameterHdl;
  }

  @Nonnull
  public SimpleURL getLinkToMenuItem (@Nonnull @Nonempty final String sAppID,
                                      @Nonnull final IRequestWebScopeWithoutResponse aRequestScope,
                                      @Nonnull final Locale aDisplayLocale,
                                      @Nonnull final String sMenuItemID)
  {
    // Get the servlet path from the app ID
    final String sServletPath = PhotonGlobalState.state (sAppID).getServletPath ();
    // Prepend the context path
    final String sBasePath = aRequestScope.getContextPath () + sServletPath;
    return m_aRequestParamHdl.buildURL (aRequestScope, sBasePath, aDisplayLocale, sMenuItemID);
  }

  @Override
  public String toString ()
  {
    return new ToStringGenerator (this).append ("RequestParamHandler", m_aRequestParamHdl).getToString ();
  }
}
