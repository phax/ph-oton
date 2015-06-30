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
package com.helger.photon.core.mock;

import java.util.Map;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.helger.commons.annotation.ReturnsMutableCopy;
import com.helger.commons.url.SMap;
import com.helger.photon.core.servlet.WebAppListener;
import com.helger.web.mock.MockHttpListener;
import com.helger.web.scopes.mock.MockServletRequestListenerScopeAware;
import com.helger.web.scopes.mock.WebScopeTestRule;

/**
 * A JUnit test rule that is suitable for all ph-oton projects. It is optimized
 * for the use within Java libraries. For the use within Java web applications
 * using {@link PhotonCoreWebAppTestRule} is preferred, since the correct
 * resource base path is used.
 *
 * @author Philip Helger
 */
public class PhotonCoreTestRule extends WebScopeTestRule
{
  @Nonnull
  @ReturnsMutableCopy
  public static SMap createDefaultServletContextInitParameters ()
  {
    return new SMap ().add (WebAppListener.INIT_PARAMETER_NO_STARTUP_INFO, "true")
                      .add (WebAppListener.INIT_PARAMETER_NO_CHECK_FILE_ACCESS, "true");
  }

  public PhotonCoreTestRule ()
  {
    this (createDefaultServletContextInitParameters ());
  }

  public PhotonCoreTestRule (@Nullable final Map <String, String> aServletContextInitParameters)
  {
    super (aServletContextInitParameters);
  }

  @Override
  protected void initListener ()
  {
    MockHttpListener.removeAllDefaultListeners ();
    MockHttpListener.addDefaultListener (new WebAppListener ());
    MockHttpListener.addDefaultListener (new MockServletRequestListenerScopeAware ());
    MockHttpListener.setToDefault ();
  }
}
