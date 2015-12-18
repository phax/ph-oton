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
package com.helger.photon.core.api;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.helger.commons.annotation.UsedViaReflection;
import com.helger.web.http.EHTTPMethod;
import com.helger.web.scope.singleton.AbstractApplicationWebSingleton;

/**
 * Central API manager. Runs in an application scope.
 *
 * @author Philip Helger
 */
public final class ApplicationAPIManager extends AbstractApplicationWebSingleton
{
  private final APIDescriptorList m_aList = new APIDescriptorList ();

  @Deprecated
  @UsedViaReflection
  public ApplicationAPIManager ()
  {}

  @Nonnull
  public static ApplicationAPIManager getInstance ()
  {
    return getApplicationSingleton (ApplicationAPIManager.class);
  }

  public void registerAPI (@Nonnull final APIDescriptor aDescriptor)
  {
    m_aList.addDescriptor (aDescriptor);
  }

  @Nullable
  public InvokableAPIDescriptor getAPIByPath (@Nullable final String sPath, @Nonnull final EHTTPMethod eHTTPMethod)
  {
    return m_aList.getMatching (sPath, eHTTPMethod);
  }
}
