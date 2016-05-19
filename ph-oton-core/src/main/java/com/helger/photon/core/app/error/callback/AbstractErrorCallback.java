/**
 * Copyright (C) 2014-2016 Philip Helger (www.helger.com)
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
package com.helger.photon.core.app.error.callback;

import java.util.List;

import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.helger.commons.annotation.Nonempty;
import com.helger.commons.io.resource.IReadableResource;
import com.helger.commons.string.StringHelper;
import com.helger.photon.basic.app.dao.IDAOReadExceptionCallback;
import com.helger.photon.basic.app.dao.IDAOWriteExceptionCallback;
import com.helger.photon.basic.app.dao.impl.AbstractDAO;
import com.helger.photon.core.ajax.ApplicationAjaxManager;
import com.helger.photon.core.ajax.IAjaxExceptionCallback;
import com.helger.photon.core.ajax.IAjaxExecutor;
import com.helger.photon.core.ajax.IAjaxInvoker;
import com.helger.photon.core.api.ApplicationAPIManager;
import com.helger.photon.core.api.IAPIExceptionCallback;
import com.helger.photon.core.api.IAPIInvoker;
import com.helger.photon.core.api.InvokableAPIDescriptor;
import com.helger.photon.core.app.error.InternalErrorHandler;
import com.helger.photon.core.requesttrack.ILongRunningRequestCallback;
import com.helger.photon.core.requesttrack.IParallelRunningRequestCallback;
import com.helger.photon.core.requesttrack.RequestTracker;
import com.helger.photon.core.requesttrack.TrackedRequest;
import com.helger.web.scope.IRequestWebScope;
import com.helger.web.scope.IRequestWebScopeWithoutResponse;
import com.helger.web.servlet.request.RequestHelper;

/**
 * A base class for a central error callback that handles all kind of errors and
 * exceptions
 *
 * @author Philip Helger
 */
public abstract class AbstractErrorCallback implements
                                            IAjaxExceptionCallback,
                                            IAPIExceptionCallback,
                                            IDAOReadExceptionCallback,
                                            IDAOWriteExceptionCallback,
                                            ILongRunningRequestCallback,
                                            IParallelRunningRequestCallback
{
  /**
   * Implement this method to handle all errors in a similar way.
   *
   * @param t
   *        The exception that occurred. Maybe <code>null</code> if no exception
   *        occurred.
   * @param aRequestScope
   *        The request scope in which the error occurred. May be
   *        <code>null</code>.
   * @param sErrorCode
   *        The unique error code for this error. Neither <code>null</code> nor
   *        empty.
   */
  protected abstract void onError (@Nullable Throwable t,
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

  public void onAPIExecutionException (@Nullable final IAPIInvoker aAPIInvoker,
                                       @Nonnull final InvokableAPIDescriptor aDescriptor,
                                       @Nonnull final IRequestWebScopeWithoutResponse aRequestScope,
                                       @Nonnull final Throwable t)
  {
    final String sErrorCode = "api-error-" + InternalErrorHandler.createNewErrorID () + "-" + aDescriptor.getPath ();
    onError (t, aRequestScope, sErrorCode);
  }

  public void onDAOReadException (@Nonnull final Throwable t,
                                  final boolean bInit,
                                  @Nullable final IReadableResource aRes)
  {
    final String sErrorCode = "DAO " +
                              (bInit ? "init" : "read") +
                              " error" +
                              (aRes == null ? "" : " for " + aRes.getPath ());
    onError (t, null, sErrorCode);
  }

  public void onDAOWriteException (@Nonnull final Throwable t,
                                   @Nonnull final IReadableResource aRes,
                                   @Nonnull final CharSequence aFileContent)
  {
    final String sErrorCode = "DAO write error for " + aRes.getPath () + " with " + aFileContent.length () + " chars";
    onError (t, null, sErrorCode);
  }

  public void onLongRunningRequest (@Nonnull @Nonempty final String sUniqueRequestID,
                                    @Nonnull final IRequestWebScope aRequestScope,
                                    @Nonnegative final long nRunningMilliseconds)
  {
    onError ((Throwable) null,
             aRequestScope,
             "Long running request. ID=" +
                            sUniqueRequestID +
                            "; millisecs=" +
                            nRunningMilliseconds +
                            "; URL=" +
                            RequestHelper.getURL (aRequestScope.getRequest ()));
  }

  public void onParallelRunningRequests (@Nonnegative final int nParallelRequests,
                                         @Nonnull @Nonempty final List <TrackedRequest> aRequests)
  {
    final StringBuilder aURLs = new StringBuilder ();
    for (final TrackedRequest aRequest : aRequests)
      aURLs.append ('\n').append (aRequest.getRequestScope ().getURL ());
    onError ((Throwable) null,
             (IRequestWebScopeWithoutResponse) null,
             "Currently " + nParallelRequests + " parallel requests are active: " + aURLs.toString ());
  }

  public void onParallelRunningRequestsBelowLimit ()
  {}

  /**
   * Install the provided error callback for the following things:
   * <ul>
   * <li>ApplicationAjaxManager - exception handler</li>
   * <li>ApplicationAPIManager - exception handler</li>
   * <li>AbstractDAO - read and write exception handler</li>
   * <li>RequestTracker - long running requests and parallel running requests
   * </li></li>
   * 
   * @param aCallback
   *        The callback to be installed. May not be <code>null</code>.
   */
  public static void install (@Nonnull final AbstractErrorCallback aCallback)
  {
    ApplicationAjaxManager.getInstance ().getExceptionCallbacks ().addCallback (aCallback);
    ApplicationAPIManager.getInstance ().getExceptionCallbacks ().addCallback (aCallback);
    AbstractDAO.getExceptionHandlersRead ().addCallback (aCallback);
    AbstractDAO.getExceptionHandlersWrite ().addCallback (aCallback);
    RequestTracker.getLongRunningRequestCallbacks ().addCallback (aCallback);
    RequestTracker.getParallelRunningRequestCallbacks ().addCallback (aCallback);
  }
}
