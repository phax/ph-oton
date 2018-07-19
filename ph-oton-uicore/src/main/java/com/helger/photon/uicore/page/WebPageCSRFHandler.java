/**
 * Copyright (C) 2014-2018 Philip Helger (www.helger.com)
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

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.helger.commons.state.EContinue;
import com.helger.html.hc.html.forms.HCHiddenField;
import com.helger.photon.core.form.csrf.CSRFSessionManager;
import com.helger.photon.uicore.css.CPageParam;

/**
 * Default implementation of {@link IWebPageCSRFHandler}.
 *
 * @author Philip Helger
 */
public class WebPageCSRFHandler implements IWebPageCSRFHandler
{
  /**
   * The global instance of this class. This is used by default. All changes to
   * this instance will effect all default occurrences.
   */
  public static final WebPageCSRFHandler INSTANCE = new WebPageCSRFHandler ();

  private static final Logger LOGGER = LoggerFactory.getLogger (WebPageCSRFHandler.class);

  private boolean m_bCSRFPreventionEnabled = true;

  protected WebPageCSRFHandler ()
  {}

  public final boolean isCSRFPreventionEnabled ()
  {
    return m_bCSRFPreventionEnabled;
  }

  @Nonnull
  public final WebPageCSRFHandler setCSRFPreventionEnabled (final boolean bCSRFPreventionEnabled)
  {
    m_bCSRFPreventionEnabled = bCSRFPreventionEnabled;
    return this;
  }

  public void onCSRFError (@Nonnull final IWebPageExecutionContext aWPEC, @Nullable final String sNonce)
  {
    LOGGER.error ("The expected CSRF nonce on page '" +
                     aWPEC.getWebPage ().getID () +
                     "' was not present.\nExpected: '" +
                     CSRFSessionManager.getInstance ().getNonce () +
                     "'\nBut got: '" +
                     sNonce +
                     "'");
  }

  @Nonnull
  public EContinue checkCSRFNonce (@Nonnull final IWebPageExecutionContext aWPEC)
  {
    if (m_bCSRFPreventionEnabled)
    {
      final CSRFSessionManager aCSRFSessionMgr = CSRFSessionManager.getInstance ();
      final String sNonce = aWPEC.params ().getAsString (CPageParam.FIELD_NONCE);
      if (!aCSRFSessionMgr.isExpectedNonce (sNonce))
      {
        // CSRF failure!
        onCSRFError (aWPEC, sNonce);
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
