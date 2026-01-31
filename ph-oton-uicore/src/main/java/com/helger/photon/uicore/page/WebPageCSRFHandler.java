/*
 * Copyright (C) 2014-2026 Philip Helger (www.helger.com)
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
package com.helger.photon.uicore.page;

import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.Nullable;

import com.helger.base.enforce.ValueEnforcer;
import com.helger.base.state.EContinue;
import com.helger.html.hc.html.forms.HCHiddenField;
import com.helger.photon.app.csrf.CSRFSessionManager;
import com.helger.photon.uicore.css.CPageParam;

/**
 * Default implementation of {@link IWebPageCSRFHandler}. Using the nonce from
 * {@link CSRFSessionManager} internally.
 *
 * @author Philip Helger
 */
public class WebPageCSRFHandler implements IWebPageCSRFHandler
{
  public static final boolean DEFAULT_CSRF_PREVENTION_ENABLED = true;

  /**
   * The global instance of this class. This is used by default. All changes to this instance will
   * effect all default occurrences.
   */
  public static final WebPageCSRFHandler INSTANCE = new WebPageCSRFHandler ();

  private boolean m_bCSRFPreventionEnabled = DEFAULT_CSRF_PREVENTION_ENABLED;
  private ICSRFErrorHandler m_aErrorHdl = new LoggingCSRFErrorHandler ();

  protected WebPageCSRFHandler ()
  {}

  public final boolean isCSRFPreventionEnabled ()
  {
    return m_bCSRFPreventionEnabled;
  }

  @NonNull
  public final WebPageCSRFHandler setCSRFPreventionEnabled (final boolean bCSRFPreventionEnabled)
  {
    m_bCSRFPreventionEnabled = bCSRFPreventionEnabled;
    return this;
  }

  @NonNull
  public final ICSRFErrorHandler getCSRFErrorHandler ()
  {
    return m_aErrorHdl;
  }

  @NonNull
  public final WebPageCSRFHandler setCSRFErrorHandler (@NonNull final ICSRFErrorHandler aErrorHdl)
  {
    ValueEnforcer.notNull (aErrorHdl, "ErrorHdl");
    m_aErrorHdl = aErrorHdl;
    return this;
  }

  @NonNull
  public EContinue checkCSRFNonce (@NonNull final IWebPageExecutionContext aWPEC)
  {
    if (m_bCSRFPreventionEnabled)
    {
      final CSRFSessionManager aCSRFSessionMgr = CSRFSessionManager.getInstance ();
      final String sRetrievedNonce = aWPEC.params ().getAsString (CPageParam.FIELD_NONCE);
      if (!aCSRFSessionMgr.isExpectedNonce (sRetrievedNonce))
      {
        // CSRF failure!
        m_aErrorHdl.onCSRFError (aWPEC, sRetrievedNonce, aCSRFSessionMgr.getNonce ());

        // Create a new nonce after one failure
        // Important: this needs to be done BEFORE the nonce is emitted.
        // Make sure to check first (using this method) before emitting
        // anything.
        aCSRFSessionMgr.generateNewNonce ();

        return EContinue.BREAK;
      }
    }

    // Nonce is valid or check is disabled
    return EContinue.CONTINUE;
  }

  @Nullable
  public HCHiddenField createCSRFNonceField ()
  {
    if (m_bCSRFPreventionEnabled)
    {
      final CSRFSessionManager aCSRFSessionMgr = CSRFSessionManager.getInstance ();
      return new HCHiddenField (CPageParam.FIELD_NONCE, aCSRFSessionMgr.getNonce ());
    }

    // If disabled, don't emit a nonce
    return null;
  }
}
