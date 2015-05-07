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
package com.helger.photon.core.app.error;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.helger.commons.annotations.Nonempty;
import com.helger.commons.io.IReadableResource;
import com.helger.commons.string.StringHelper;
import com.helger.photon.basic.app.dao.IDAOReadExceptionCallback;
import com.helger.photon.basic.app.dao.IDAOWriteExceptionCallback;
import com.helger.photon.core.action.IActionExceptionCallback;
import com.helger.photon.core.action.IActionExecutor;
import com.helger.photon.core.action.IActionInvoker;
import com.helger.photon.core.ajax.IAjaxExceptionCallback;
import com.helger.photon.core.ajax.IAjaxExecutor;
import com.helger.photon.core.ajax.IAjaxInvoker;
import com.helger.web.scopes.domain.IRequestWebScopeWithoutResponse;

/**
 * A base class for a central error callback that handles all kind of errors and
 * exceptions
 *
 * @author Philip Helger
 */
public abstract class AbstractErrorCallback implements IAjaxExceptionCallback, IActionExceptionCallback, IDAOReadExceptionCallback, IDAOWriteExceptionCallback
{
  /**
   * Implement this method to handle all errors in a similar way.
   *
   * @param t
   *        The exception that occurred.
   * @param aRequestScope
   *        The request scope in which the error occurred. May be
   *        <code>null</code>.
   * @param sErrorCode
   *        The unique error code for this error. Neither <code>null</code> not
   *        empty.
   */
  protected abstract void onError (@Nonnull Throwable t,
                                   @Nullable IRequestWebScopeWithoutResponse aRequestScope,
                                   @Nonnull @Nonempty String sErrorCode);

  public void onAjaxExecutionException (@Nullable final IAjaxInvoker aAjaxInvoker,
                                        @Nullable final String sAjaxFunctionName,
                                        @Nonnull final IAjaxExecutor aAjaxExecutor,
                                        @Nonnull final IRequestWebScopeWithoutResponse aRequestScope,
                                        @Nonnull final Throwable t)
  {
    final String sErrorCode = "ajax-error-" +
                              (StringHelper.hasText (sAjaxFunctionName) ? sAjaxFunctionName + "-" : "") +
                              InternalErrorHandler.createNewErrorID ();
    onError (t, aRequestScope, sErrorCode);
  }

  public void onActionExecutionException (@Nonnull final IActionInvoker aActionInvoker,
                                          @Nonnull final String sActionName,
                                          @Nonnull final IActionExecutor aActionExecutor,
                                          @Nonnull final IRequestWebScopeWithoutResponse aRequestScope,
                                          @Nonnull final Throwable t)
  {
    final String sErrorCode = "action-error-" + sActionName + "-" + InternalErrorHandler.createNewErrorID ();
    onError (t, aRequestScope, sErrorCode);
  }

  public void onDAOReadException (@Nonnull final Throwable t,
                                  final boolean bInit,
                                  @Nullable final IReadableResource aRes)
  {
    onError (t, null, "DAO " + (bInit ? "init" : "read") + " error" + (aRes == null ? "" : " for " + aRes.getPath ()));
  }

  public void onDAOWriteException (@Nonnull final Throwable t,
                                   @Nonnull final IReadableResource aRes,
                                   @Nonnull final CharSequence aFileContent)
  {
    onError (t, null, "DAO write error for " + aRes.getPath () + " with " + aFileContent.length () + " chars");
  }
}
