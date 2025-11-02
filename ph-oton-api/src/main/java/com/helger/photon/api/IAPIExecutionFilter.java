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
package com.helger.photon.api;

import java.io.Serializable;

import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.Nullable;

import com.helger.web.scope.IRequestWebScopeWithoutResponse;

/**
 * Generic API execution filter.
 *
 * @author Philip Helger
 * @since 7.0.2
 */
@FunctionalInterface
public interface IAPIExecutionFilter extends Serializable
{
  /**
   * Check if the passed request can be executed or not.
   *
   * @param aRequestScope
   *        The current request scope. Never <code>null</code>.
   * @return <code>true</code> if execution can continue, <code>false</code> if
   *         not.
   */
  boolean canExecute (@NonNull IRequestWebScopeWithoutResponse aRequestScope);

  @NonNull
  default IAPIExecutionFilter and (@Nullable final IAPIExecutionFilter aOther)
  {
    if (aOther == null)
      return this;
    return x -> this.canExecute (x) && aOther.canExecute (x);
  }

  @Nullable
  static IAPIExecutionFilter and (@Nullable final IAPIExecutionFilter aFirst, @Nullable final IAPIExecutionFilter aSecond)
  {
    return aFirst != null ? aSecond != null ? aFirst.and (aSecond) : aFirst : aSecond;
  }
}
