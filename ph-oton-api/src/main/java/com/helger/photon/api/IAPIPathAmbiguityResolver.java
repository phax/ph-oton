/*
 * Copyright (C) 2014-2021 Philip Helger (www.helger.com)
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

import java.util.function.BiFunction;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.helger.commons.collection.impl.ICommonsList;

/**
 * Shared interface to resolve API ambiguities. So if a single {@link APIPath}
 * resolves to multiple potential invokable handlers.
 *
 * @author Philip Helger
 * @since 8.1.4
 */
@FunctionalInterface
public interface IAPIPathAmbiguityResolver extends
                                           BiFunction <APIPath, ICommonsList <InvokableAPIDescriptor>, InvokableAPIDescriptor>
{
  /**
   * @param aPath
   *        The path for which handlers were resolved. Never <code>null</code>.
   * @param aDescriptors
   *        The descriptors that were found. Never <code>null</code> but maybe
   *        empty.
   * @return The descriptor that should be used or <code>null</code> if it could
   *         not be determined.
   */
  @Nullable
  InvokableAPIDescriptor apply (@Nonnull APIPath aPath, @Nonnull ICommonsList <InvokableAPIDescriptor> aDescriptors);
}
