/**
 * Copyright (C) 2014-2020 Philip Helger (www.helger.com)
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

import java.io.Serializable;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.helger.commons.annotation.ReturnsMutableCopy;
import com.helger.commons.collection.impl.ICommonsList;

/**
 * Base interface for an API registry. It has a set of {@link APIDescriptor}
 * instances that it can invoke.
 *
 * @author Philip Helger
 * @since 8.1.4
 */
public interface IAPIRegistry extends Serializable
{
  /**
   * Register a new API descriptor. It is the callers responsibility to ensure
   * each API is only registered once.
   *
   * @param aDescriptor
   *        The descriptor to be registered. May not be <code>null</code>.
   */
  void registerAPI (@Nonnull APIDescriptor aDescriptor);

  /**
   * @return A mutable list of all registered descriptors. Never
   *         <code>null</code> but maybe empty.
   */
  @Nonnull
  @ReturnsMutableCopy
  ICommonsList <IAPIDescriptor> getAllAPIDescriptors ();

  /**
   * Find an {@link InvokableAPIDescriptor} that matches the provided path.
   *
   * @param aPath
   *        The path to search. May not be <code>null</code>.
   * @return <code>null</code> if no matching invoker is registered meaning the
   *         path cannot be handled by this invoker.
   */
  @Nullable
  default InvokableAPIDescriptor getAPIByPath (@Nonnull final APIPath aPath)
  {
    return getAPIByPath (aPath, new LoggingAPIPathAmbiguityResolver ());
  }

  /**
   * Find an {@link InvokableAPIDescriptor} that matches the provided path.
   *
   * @param aPath
   *        The path to search. May not be <code>null</code>.
   * @param aAmbiguityResolver
   *        The ambiguity resolver to be used. May not be <code>null</code>.
   * @return <code>null</code> if no matching invoker is registered meaning the
   *         path cannot be handled by this invoker.
   * @since 8.1.4
   */
  @Nullable
  InvokableAPIDescriptor getAPIByPath (@Nonnull APIPath aPath, @Nonnull IAPIPathAmbiguityResolver aAmbiguityResolver);
}
