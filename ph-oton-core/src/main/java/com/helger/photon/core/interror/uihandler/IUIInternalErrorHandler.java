/**
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
package com.helger.photon.core.interror.uihandler;

import java.io.Serializable;
import java.util.Locale;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/**
 * Callback interface to show internal errors on the UI in a generic way.
 *
 * @author Philip Helger
 */
@FunctionalInterface
public interface IUIInternalErrorHandler extends Serializable
{
  /**
   * @param t
   *        Exception thrown. May be <code>null</code>.
   * @param sErrorID
   *        The created unique error ID. Never <code>null</code>.
   * @param aDisplayLocale
   *        The display locale to be used. Never <code>null</code>.
   */
  void onInternalError (@Nullable Throwable t, @Nonnull String sErrorID, @Nonnull Locale aDisplayLocale);
}
