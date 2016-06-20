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
   * this instance will effect all default occurances.
   */
  public static final WebPageCSRFHandler INSTANCE = new WebPageCSRFHandler ();

  private static final Logger s_aLogger = LoggerFactory.getLogger (WebPageCSRFHandler.class);

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

  public void onCSRFError (@Nonnull final IWebPage <?> aSrcPage,
                           @Nonnull final IWebPageExecutionContext aWPEC,
                           @Nullable final String sNonce)
  {
    s_aLogger.error ("The expected CSRF nonce on page '" +
                     aSrcPage.getID () +
                     "' was not present.\nExpected: '" +
                     CSRFSessionManager.getInstance ().getNonce () +
                     "'\nBut got: '" +
                     sNonce +
                     "'");
  }

  @Nonnull
  public EContinue checkCSRFNonce (@Nonnull final IWebPage <?> aSrcPage, @Nonnull final IWebPageExecutionContext aWPEC)
  {
    if (m_bCSRFPreventionEnabled)
    {
      final CSRFSessionManager aCSRFSessionMgr = CSRFSessionManager.getInstance ();
      final String sNonce = aWPEC.getAttributeAsString (CPageParam.FIELD_NONCE);
      if (!aCSRFSessionMgr.isExpectedNonce (sNonce))
      {
        // CSRF failure!
        onCSRFError (aSrcPage, aWPEC, sNonce);
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
