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
package com.helger.photon.core.servlet;

import org.jspecify.annotations.NonNull;

import com.helger.io.resource.ClassPathResource;
import com.helger.io.resource.IReadableResource;
import com.helger.url.codec.URLCoder;
import com.helger.web.scope.IRequestWebScopeWithoutResponse;

public class ClassPathResourceHttpHandler extends AbstractResourceDeliveryHttpHandler
{
  @Override
  @NonNull
  protected IReadableResource getResource (@NonNull final IRequestWebScopeWithoutResponse aRequestScope,
                                           @NonNull final String sFilename)
  {
    // URL decode is required because requests contain e.g. "%20"
    final String sFilename1 = URLCoder.urlDecodeOrDefault (sFilename, sFilename);

    return new ClassPathResource (sFilename1);
  }
}
