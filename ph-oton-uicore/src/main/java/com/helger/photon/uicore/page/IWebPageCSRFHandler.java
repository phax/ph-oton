package com.helger.photon.uicore.page;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.helger.commons.state.EContinue;
import com.helger.html.hc.html.forms.HCHiddenField;

/**
 * Handle CSRF (Cross Site Request Forgery) issues on an {@link AbstractWebPage}
 * .
 *
 * @author Philip Helger
 */
public interface IWebPageCSRFHandler
{
  /**
   * @return <code>true</code> if CSRF prevention is enabled, <code>false</code>
   *         otherwise.
   */
  boolean isCSRFPreventionEnabled ();

  /**
   * Callback method that is executed if a CSRF nonce mismatch occurs.
   *
   * @param aSrcPage
   *        The page for which the check was executed. Never <code>null</code>.
   * @param aWPEC
   *        Web page execution context. Never <code>null</code>.
   * @param sNonce
   *        The provided nonce. May be <code>null</code>.
   */
  void onCSRFError (@Nonnull IWebPage <?> aSrcPage, @Nonnull IWebPageExecutionContext aWPEC, @Nullable String sNonce);

  /**
   * Check if the nonce if the passed WPEC is correct. Calls
   * {@link #onCSRFError(IWebPage, IWebPageExecutionContext, String)} upon
   * failure.
   *
   * @param aSrcPage
   *        The page for which the check was executed. Never <code>null</code>.
   * @param aWPEC
   *        Web page execution context. Never <code>null</code>.
   * @return {@link EContinue#CONTINUE} if CSRF checking is disabled or was
   *         successful.
   */
  @Nonnull
  EContinue checkCSRFNonce (@Nonnull IWebPage <?> aSrcPage, @Nonnull IWebPageExecutionContext aWPEC);

  /**
   * @return The HTML nonce hidden field or <code>null</code> if CSRF prevention
   *         is disabled.
   */
  @Nullable
  HCHiddenField createCSRFNonceField ();
}
