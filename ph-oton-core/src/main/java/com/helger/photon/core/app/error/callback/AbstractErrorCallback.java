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
import java.util.Locale;
import java.util.Map;

import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.helger.commons.annotation.Nonempty;
import com.helger.commons.collection.ext.ICommonsSet;
import com.helger.commons.collection.impl.LRUSet;
import com.helger.commons.io.resource.IReadableResource;
import com.helger.commons.scope.mgr.ScopeManager;
import com.helger.commons.string.StringHelper;
import com.helger.commons.url.SMap;
import com.helger.photon.basic.app.dao.IDAOReadExceptionCallback;
import com.helger.photon.basic.app.dao.IDAOWriteExceptionCallback;
import com.helger.photon.basic.app.dao.impl.AbstractDAO;
import com.helger.photon.basic.app.request.RequestParameterManager;
import com.helger.photon.core.ajax.AjaxSettings;
import com.helger.photon.core.ajax.IAjaxExceptionCallback;
import com.helger.photon.core.ajax.IAjaxExecutor;
import com.helger.photon.core.ajax.IAjaxInvoker;
import com.helger.photon.core.api.APISettings;
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
import com.helger.web.scope.mgr.WebScopeManager;
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
  private static final Logger s_aLogger = LoggerFactory.getLogger (AbstractErrorCallback.class);
  private final ICommonsSet <String> m_aHandledLongRunning = new LRUSet<> (1000);

  @Nonnull
  public static final Locale getSafeDisplayLocale (@Nonnull final Locale aFallback)
  {
    try
    {
      // This may fail, if a weird application context is used
      return RequestParameterManager.getInstance ().getRequestDisplayLocale ();
    }
    catch (final IllegalStateException ex)
    {
      // I just want to know, where and how this happens...
      final IRequestWebScope aRequestScope = WebScopeManager.getRequestScopeOrNull ();
      final String sAppID = aRequestScope == null ? "<no request scope present>"
                                                  : ScopeManager.getRequestApplicationID (aRequestScope);
      s_aLogger.warn ("Failed to retrieve default locale for application ID '" + sAppID + "'");
      return aFallback;
    }
  }

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
                                        @Nonnull final Throwable t)
  {
    final String sErrorCode = "ajax-error-" +
                              (StringHelper.hasText (sAjaxFunctionName) ? sAjaxFunctionName + "-" : "") +
                              InternalErrorHandler.createNewErrorID ();
    onError (t, aRequestScope, sErrorCode, new SMap ().add ("ajax-function-name", sAjaxFunctionName));
  }

  public void onAPIExecutionException (@Nullable final IAPIInvoker aAPIInvoker,
                                       @Nonnull final InvokableAPIDescriptor aDescriptor,
                                       @Nonnull final IRequestWebScopeWithoutResponse aRequestScope,
                                       @Nonnull final Throwable t)
  {
    final String sErrorCode = "api-error-" + InternalErrorHandler.createNewErrorID () + "-" + aDescriptor.getPath ();
    onError (t, aRequestScope, sErrorCode, new SMap ().add ("api-path", aDescriptor.getPath ()));
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
             new SMap ().add ("action", bInit ? "init" : "read").add ("path", aRes == null ? null : aRes.getPath ()));
  }

  public void onDAOWriteException (@Nonnull final Throwable t,
                                   @Nonnull final IReadableResource aRes,
                                   @Nonnull final CharSequence aFileContent)
  {
    final String sErrorCode = "DAO write error for " + aRes.getPath () + " with " + aFileContent.length () + " chars";
    onError (t,
             null,
             sErrorCode,
             new SMap ().add ("action", "write").add ("path", aRes.getPath ()).add ("content", aFileContent));
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
               new SMap ().add ("request-id", sUniqueRequestID)
                          .add ("millisecconds", nRunningMilliseconds)
                          .add ("URL", RequestHelper.getURL (aRequestScope.getRequest ())));
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
             new SMap ().add ("parallel-requests", nParallelRequests));
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
   * </li>
   * </ul>
   *
   * @param aCallback
   *        The callback to be installed. May not be <code>null</code>.
   */
  public static void install (@Nonnull final AbstractErrorCallback aCallback)
  {
    AjaxSettings.getExceptionCallbacks ().addCallback (aCallback);
    APISettings.getExceptionCallbacks ().addCallback (aCallback);
    AbstractDAO.getExceptionHandlersRead ().addCallback (aCallback);
    AbstractDAO.getExceptionHandlersWrite ().addCallback (aCallback);
    RequestTracker.getLongRunningRequestCallbacks ().addCallback (aCallback);
    RequestTracker.getParallelRunningRequestCallbacks ().addCallback (aCallback);
  }
}
