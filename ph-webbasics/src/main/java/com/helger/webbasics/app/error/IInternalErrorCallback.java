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
package com.helger.webbasics.app.error;

import java.util.Locale;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.helger.commons.annotations.Nonempty;
import com.helger.commons.callback.ICallback;
import com.helger.web.scopes.domain.IRequestWebScopeWithoutResponse;

/**
 * Callback interface for the {@link InternalErrorHandler}.
 *
 * @author Philip Helger
 */
public interface IInternalErrorCallback extends ICallback
{
  /**
   * Called when an exception occurred. You may not re-throw the exception from
   * in here!
   *
   * @param t
   *        The exception. May be <code>null</code>.
   * @param aRequestScope
   *        The request scope in which the error occurred. May be
   *        <code>null</code>.
   * @param sErrorID
   *        The created internal error ID. Neither <code>null</code> nor empty.
   * @param aDisplayLocale
   *        The display locale for further handling if required. Never
   *        <code>null</code>.
   */
  void onInternalError (@Nullable Throwable t,
                        @Nullable IRequestWebScopeWithoutResponse aRequestScope,
                        @Nonnull @Nonempty String sErrorID,
                        @Nonnull Locale aDisplayLocale);
}
