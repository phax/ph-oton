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
package com.helger.photon.core.mock;

import java.io.File;
import java.util.Map;

import com.helger.annotation.style.OverrideOnDemand;
import com.helger.annotation.style.ReturnsMutableCopy;
import com.helger.collection.commons.ICommonsMap;
import com.helger.io.resourceprovider.IReadableResourceProvider;
import com.helger.photon.core.servlet.WebAppListener;
import com.helger.servlet.mock.MockHttpListener;
import com.helger.servlet.mock.MockServletContext;
import com.helger.typeconvert.collection.StringMap;
import com.helger.web.scope.mock.MockServletRequestListenerScopeAware;
import com.helger.web.scope.mock.WebScopeTestRule;

import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;

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
  public static final File RESOURCE_BASE_FILE = new File ("target/test-classes").getAbsoluteFile ();

  /**
   * @return The default Servlet Context init parameters to be used. Never
   *         <code>null</code>.
   */
  @Nonnull
  @ReturnsMutableCopy
  public static StringMap createDefaultServletContextInitParameters ()
  {
    return new StringMap ().add (WebAppListener.INIT_PARAMETER_NO_STARTUP_INFO, true)
                           .add (WebAppListener.INIT_PARAMETER_NO_CHECK_FILE_ACCESS, true);
  }

  public PhotonCoreTestRule ()
  {
    this (createDefaultServletContextInitParameters ());
  }

  public PhotonCoreTestRule (@Nullable final ICommonsMap <String, String> aServletContextInitParameters)
  {
    super (aServletContextInitParameters);
  }

  @Override
  @OverrideOnDemand
  protected void initListener ()
  {
    MockHttpListener.removeAllDefaultListeners ();
    MockHttpListener.addDefaultListener (new WebAppListener ().setHandleStatisticsOnEnd (false));
    MockHttpListener.addDefaultListener (new MockServletRequestListenerScopeAware ());
    MockHttpListener.setCurrentToDefault ();
  }

  @Override
  @Nonnull
  @OverrideOnDemand
  protected MockServletContext createMockServletContext (@Nullable final String sContextPath,
                                                         @Nullable final Map <String, String> aInitParams)
  {
    // Use the special resource base path
    // Use default resource provider
    // This is, where all the ServletContext Listeners are invoked
    return MockServletContext.create (sContextPath, RESOURCE_BASE_FILE.getAbsolutePath (), (IReadableResourceProvider) null, aInitParams);
  }
}
