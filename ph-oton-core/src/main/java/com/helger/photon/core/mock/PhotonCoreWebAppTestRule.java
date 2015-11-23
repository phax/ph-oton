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

import java.io.File;
import java.util.Map;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.helger.commons.io.resourceprovider.IReadableResourceProvider;
import com.helger.web.mock.MockServletContext;

/**
 * A JUnit test rule that is suitable for all ph-oton projects. It is optimized
 * for the use within Java web application. For the use within Java libraries
 * using {@link PhotonCoreTestRule} is preferred, since the correct resource
 * base path is used.
 *
 * @author Philip Helger
 */
public class PhotonCoreWebAppTestRule extends PhotonCoreTestRule
{
  @SuppressWarnings ("hiding")
  public static final File RESOURCE_BASE_FILE = new File ("target/webapp-classes").getAbsoluteFile ();

  @Override
  @Nonnull
  protected MockServletContext createMockServletContext (@Nullable final String sContextPath, @Nullable final Map <String, String> aInitParams)
  {
    // Use the special resource base path
    // Use default resource provider
    return MockServletContext.create (sContextPath, RESOURCE_BASE_FILE.getAbsolutePath (), (IReadableResourceProvider) null, aInitParams);
  }
}
