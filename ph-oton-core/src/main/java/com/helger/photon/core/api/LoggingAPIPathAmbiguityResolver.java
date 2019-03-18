/**
 * Copyright (C) 2014-2019 Philip Helger (www.helger.com)
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

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.helger.commons.collection.impl.ICommonsList;

/**
 * Logging instance of {@link IAPIPathAmbiguityResolver}.
 *
 * @author Philip Helger
 * @since 8.1.4
 */
public class LoggingAPIPathAmbiguityResolver implements IAPIPathAmbiguityResolver
{
  private static final Logger LOGGER = LoggerFactory.getLogger (LoggingAPIPathAmbiguityResolver.class);

  @Nullable
  public InvokableAPIDescriptor apply (@Nonnull final APIPath aPath,
                                       @Nonnull final ICommonsList <InvokableAPIDescriptor> aDescriptors)
  {
    if (aDescriptors.isNotEmpty ())
      LOGGER.warn ("Found more than one API descriptor matching path '" + aPath.getPath () + "': " + aDescriptors);
    return null;
  }
}
