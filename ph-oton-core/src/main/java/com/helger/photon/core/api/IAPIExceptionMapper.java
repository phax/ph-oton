package com.helger.photon.core.api;

import java.io.Serializable;

import javax.annotation.Nonnull;

import com.helger.commons.lang.StackTraceHelper;
import com.helger.commons.state.EHandled;
import com.helger.photon.core.PhotonUnifiedResponse;
import com.helger.web.scope.IRequestWebScopeWithoutResponse;

/**
 * API exception mapper to convert exceptions into reasonable responses. It is
 * called from inside {@link GlobalAPIInvoker} in case an exception is thrown.
 * See {@link IAPIDescriptor} for the assignment.
 *
 * @author Philip Helger
 * @since 8.1.3
 */
public interface IAPIExceptionMapper extends Serializable
{
  @Nonnull
  static String getResponseEntityWithoutStackTrace (@Nonnull final Throwable ex)
  {
    // The class name does not really matter
    return ex.getMessage ();
  }

  @Nonnull
  static String getResponseEntityWithStackTrace (@Nonnull final Throwable ex)
  {
    // Includes class name and message
    return StackTraceHelper.getStackAsString (ex);
  }

  /**
   * @param aInvokableDescriptor
   *        The current invokable descriptor. Never <code>null</code>.
   * @param aRequestScope
   *        The current request scope. Never <code>null</code>.
   * @param aUnifiedResponse
   *        The current response. Never <code>null</code>.
   * @param aThrowable
   *        The thrown Exception. Never <code>null</code>.
   * @return {@link EHandled#HANDLED} to indicate that the exception was handled
   *         and should NOT be re-thrown.
   */
  @Nonnull
  EHandled applyExceptionOnResponse (@Nonnull InvokableAPIDescriptor aInvokableDescriptor,
                                     @Nonnull IRequestWebScopeWithoutResponse aRequestScope,
                                     @Nonnull PhotonUnifiedResponse aUnifiedResponse,
                                     @Nonnull Throwable aThrowable);
}
