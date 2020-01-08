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
package com.helger.photon.core.interror.callback;

import java.util.List;
import java.util.Map;

import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.helger.commons.annotation.Nonempty;
import com.helger.commons.annotation.OverrideOnDemand;
import com.helger.commons.collection.attr.StringMap;
import com.helger.commons.collection.impl.ICommonsSet;
import com.helger.commons.collection.map.LRUSet;
import com.helger.commons.io.resource.IReadableResource;
import com.helger.commons.string.StringHelper;
import com.helger.dao.AbstractDAO;
import com.helger.dao.IDAOReadExceptionCallback;
import com.helger.dao.IDAOWriteExceptionCallback;
import com.helger.photon.ajax.AjaxSettings;
import com.helger.photon.ajax.IAjaxInvoker;
import com.helger.photon.ajax.callback.IAjaxExceptionCallback;
import com.helger.photon.ajax.executor.IAjaxExecutor;
import com.helger.photon.api.APISettings;
import com.helger.photon.api.IAPIExceptionCallback;
import com.helger.photon.api.IAPIInvoker;
import com.helger.photon.api.InvokableAPIDescriptor;
import com.helger.photon.core.interror.InternalErrorHandler;
import com.helger.photon.core.longrun.ILongRunningJob;
import com.helger.quartz.IJob;
import com.helger.schedule.job.AbstractJob;
import com.helger.schedule.job.IJobExceptionCallback;
import com.helger.web.scope.IRequestWebScope;
import com.helger.web.scope.IRequestWebScopeWithoutResponse;
import com.helger.xservlet.requesttrack.ILongRunningRequestCallback;
import com.helger.xservlet.requesttrack.IParallelRunningRequestCallback;
import com.helger.xservlet.requesttrack.RequestTracker;
import com.helger.xservlet.requesttrack.TrackedRequest;

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
                                            IJobExceptionCallback,
                                            ILongRunningRequestCallback,
                                            IParallelRunningRequestCallback
{
  private final ICommonsSet <String> m_aHandledLongRunning = new LRUSet <> (1000);

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
   * @param aCustomAttrs
   *        Optional custom attributes to be emitted. May be <code>null</code>.
   */
  protected abstract void onError (@Nullable Throwable t,
                                   @Nullable IRequestWebScopeWithoutResponse aRequestScope,
                                   @Nonnull @Nonempty String sErrorCode,
                                   @Nullable Map <String, String> aCustomAttrs);

  public void onAjaxExecutionException (@Nullable final IAjaxInvoker aAjaxInvoker,
                                        @Nullable final String sAjaxFunctionName,
                                        @Nonnull final IAjaxExecutor aAjaxExecutor,
                                        @Nonnull final IRequestWebScopeWithoutResponse aRequestScope,
                                        @Nonnull final Exception ex)
  {
    final String sErrorCode = "ajax-error-" +
                              (StringHelper.hasText (sAjaxFunctionName) ? sAjaxFunctionName + "-" : "") +
                              InternalErrorHandler.createNewErrorID ();
    onError (ex, aRequestScope, sErrorCode, new StringMap ().add ("ajax-function-name", sAjaxFunctionName));
  }

  public void onAPIExecutionException (@Nullable final IAPIInvoker aAPIInvoker,
                                       @Nonnull final InvokableAPIDescriptor aDescriptor,
                                       @Nonnull final IRequestWebScopeWithoutResponse aRequestScope,
                                       @Nonnull final Throwable t)
  {
    final String sErrorCode = "api-error-" + InternalErrorHandler.createNewErrorID () + "-" + aDescriptor.getPath ();
    onError (t, aRequestScope, sErrorCode, new StringMap ().add ("api-path", aDescriptor.getPath ()));
  }

  public void onDAOReadException (@Nonnull final Throwable t,
                                  final boolean bInit,
                                  @Nullable final IReadableResource aRes)
  {
    final String sErrorCode = "DAO " +
                              (bInit ? "init" : "read") +
                              " error" +
                              (aRes == null ? "" : " for " + aRes.getPath ());
    onError (t,
             null,
             sErrorCode,
             new StringMap ().add ("action", bInit ? "init" : "read").add ("path",
                                                                           aRes == null ? null : aRes.getPath ()));
  }

  public void onDAOWriteException (@Nonnull final Throwable t,
                                   @Nonnull final IReadableResource aRes,
                                   @Nonnull final CharSequence aFileContent)
  {
    final String sErrorCode = "DAO write error for " + aRes.getPath () + " with " + aFileContent.length () + " chars";
    onError (t,
             null,
             sErrorCode,
             new StringMap ().add ("action", "write").add ("path", aRes.getPath ()).add ("content", aFileContent));
  }

  public void onScheduledJobException (@Nonnull final Throwable t,
                                       @Nullable final String sJobClassName,
                                       @Nonnull final IJob aJob)
  {
    onError (t,
             (IRequestWebScopeWithoutResponse) null,
             "Error executing background job " + sJobClassName,
             new StringMap ().addIfNotNull ("job-class", sJobClassName)
                             .add ("job-object", aJob)
                             .add ("long-running", aJob instanceof ILongRunningJob));
  }

  public void onLongRunningRequest (@Nonnull @Nonempty final String sUniqueRequestID,
                                    @Nonnull final IRequestWebScope aRequestScope,
                                    @Nonnegative final long nRunningMilliseconds)
  {
    if (m_aHandledLongRunning.add (sUniqueRequestID))
    {
      onError ((Throwable) null,
               aRequestScope,
               "Long running request.",
               new StringMap ().add ("request-id", sUniqueRequestID)
                               .add ("millisecconds", nRunningMilliseconds)
                               .add ("URL", aRequestScope.getURL ()));
    }
  }

  public void onParallelRunningRequests (@Nonnegative final int nParallelRequests,
                                         @Nonnull @Nonempty final List <TrackedRequest> aRequests)
  {
    final StringBuilder aURLs = new StringBuilder ();
    for (final TrackedRequest aRequest : aRequests)
      aURLs.append ('\n').append (aRequest.getRequestScope ().getURL ());
    onError ((Throwable) null,
             (IRequestWebScopeWithoutResponse) null,
             "Currently " + nParallelRequests + " parallel requests are active: " + aURLs.toString (),
             new StringMap ().add ("parallel-requests", nParallelRequests));
  }

  public void onParallelRunningRequestsBelowLimit ()
  {}

  /**
   * Install this error callback in all relevant places.
   *
   * @see #installToPhoton(AbstractErrorCallback)
   */
  @OverrideOnDemand
  public void install ()
  {
    installToPhoton (this);
  }

  /**
   * Install the provided error callback for the following things:
   * <ul>
   * <li>ApplicationAjaxManager - exception handler</li>
   * <li>ApplicationAPIManager - exception handler</li>
   * <li>AbstractDAO - read and write exception handler</li>
   * <li>AbstractJob - exception callback</li>
   * <li>RequestTracker - long running requests and parallel running
   * requests</li>
   * </ul>
   *
   * @param aCallback
   *        The callback to be installed. May not be <code>null</code>.
   */
  public static void installToPhoton (@Nonnull final AbstractErrorCallback aCallback)
  {
    AjaxSettings.exceptionCallbacks ().add (aCallback);
    APISettings.exceptionCallbacks ().add (aCallback);
    AbstractDAO.exceptionHandlersRead ().add (aCallback);
    AbstractDAO.exceptionHandlersWrite ().add (aCallback);
    AbstractJob.exceptionCallbacks ().add (aCallback);
    RequestTracker.longRunningRequestCallbacks ().add (aCallback);
    RequestTracker.parallelRunningRequestCallbacks ().add (aCallback);
  }
}
