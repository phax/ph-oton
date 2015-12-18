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
package com.helger.photon.uicore.page;

import java.util.Locale;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.helger.commons.annotation.Nonempty;
import com.helger.commons.annotation.OverrideOnDemand;
import com.helger.commons.state.EContinue;
import com.helger.commons.state.EValidity;
import com.helger.commons.state.IValidityIndicator;
import com.helger.commons.string.StringHelper;
import com.helger.commons.text.IMultilingualText;
import com.helger.html.css.ICSSClassProvider;
import com.helger.html.hc.IHCNode;
import com.helger.html.hc.html.IHCElementWithChildren;
import com.helger.html.hc.html.forms.HCHiddenField;
import com.helger.html.hc.html.grouping.HCDiv;
import com.helger.html.hc.html.sections.HCH1;
import com.helger.photon.basic.app.page.AbstractPage;
import com.helger.photon.core.form.csrf.CSRFSessionManager;
import com.helger.photon.uicore.css.CPageParam;
import com.helger.photon.uicore.css.CUICoreCSS;

/**
 * Abstract base implementation for {@link IWebPage}.
 *
 * @author Philip Helger
 * @param <WPECTYPE>
 *        Web page execution context type
 */
public abstract class AbstractWebPage <WPECTYPE extends IWebPageExecutionContext> extends AbstractPage
                                      implements IWebPage <WPECTYPE>
{
  public static final boolean DEFAULT_CSRF_PREVENTION_ENABLED = true;

  // Commonly used CSS classes
  protected static final ICSSClassProvider CSS_CLASS_LEFT = CUICoreCSS.CSS_CLASS_LEFT;
  protected static final ICSSClassProvider CSS_CLASS_CENTER = CUICoreCSS.CSS_CLASS_CENTER;
  protected static final ICSSClassProvider CSS_CLASS_RIGHT = CUICoreCSS.CSS_CLASS_RIGHT;
  protected static final ICSSClassProvider CSS_CLASS_NOWRAP = CUICoreCSS.CSS_CLASS_NOWRAP;

  private static final Logger s_aLogger = LoggerFactory.getLogger (AbstractWebPage.class);

  private boolean m_bCSRFPreventionEnabled = DEFAULT_CSRF_PREVENTION_ENABLED;

  /**
   * Constructor
   *
   * @param sID
   *        The unique page ID. May not be <code>null</code>.
   */
  public AbstractWebPage (@Nonnull @Nonempty final String sID)
  {
    super (sID);
  }

  /**
   * Constructor
   *
   * @param sID
   *        The unique page ID. May not be <code>null</code>.
   * @param sName
   *        The constant (non-translatable) name of the page. May not be
   *        <code>null</code>.
   */
  public AbstractWebPage (@Nonnull @Nonempty final String sID, @Nonnull final String sName)
  {
    super (sID, sName);
  }

  /**
   * Constructor
   *
   * @param sID
   *        The unique page ID. May not be <code>null</code>.
   * @param sName
   *        The constant (non-translatable) name of the page. May not be
   *        <code>null</code>.
   * @param sDescription
   *        The constant (non-translatable) description of the page. May be
   *        <code>null</code>.
   */
  public AbstractWebPage (@Nonnull @Nonempty final String sID,
                          @Nonnull final String sName,
                          @Nullable final String sDescription)
  {
    super (sID, sName, sDescription);
  }

  /**
   * Constructor
   *
   * @param sID
   *        The unique page ID. May not be <code>null</code>.
   * @param aName
   *        The name of the page. May not be <code>null</code>.
   */
  public AbstractWebPage (@Nonnull @Nonempty final String sID, @Nonnull final IMultilingualText aName)
  {
    super (sID, aName);
  }

  /**
   * Constructor
   *
   * @param sID
   *        The unique page ID. May not be <code>null</code>.
   * @param aName
   *        The name of the page. May not be <code>null</code>.
   * @param aDescription
   *        Optional description of the page. May be <code>null</code>.
   */
  public AbstractWebPage (@Nonnull @Nonempty final String sID,
                          @Nonnull final IMultilingualText aName,
                          @Nullable final IMultilingualText aDescription)
  {
    super (sID, aName, aDescription);
  }

  public final boolean isCSRFPreventionEnabled ()
  {
    return m_bCSRFPreventionEnabled;
  }

  public final void setCSRFPreventionEnabled (final boolean bCSRFPreventionEnabled)
  {
    m_bCSRFPreventionEnabled = bCSRFPreventionEnabled;
  }

  /**
   * Callback method that is executed if a CSRF nonce mismatch occurs.
   *
   * @param aWPEC
   *        Web page execution context. Never <code>null</code>.
   * @param sNonce
   *        The provided nonce. May be <code>null</code>.
   */
  @OverrideOnDemand
  protected void onCSRFError (@Nonnull final WPECTYPE aWPEC, @Nullable final String sNonce)
  {
    s_aLogger.error ("The expected CSRF nonce on page '" +
                     getID () +
                     "' was not present.\nExpected: '" +
                     CSRFSessionManager.getInstance ().getNonce () +
                     "'\nBut got: '" +
                     sNonce +
                     "'");
  }

  @Nonnull
  public final EContinue checkCSRFNonce (@Nonnull final WPECTYPE aWPEC)
  {
    if (m_bCSRFPreventionEnabled)
    {
      final CSRFSessionManager aCSRFSessionMgr = CSRFSessionManager.getInstance ();
      final String sNonce = aWPEC.getAttributeAsString (CPageParam.FIELD_NONCE);
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
  public final HCHiddenField createCSRFNonceField ()
  {
    if (m_bCSRFPreventionEnabled)
    {
      final CSRFSessionManager aCSRFSessionMgr = CSRFSessionManager.getInstance ();
      return new HCHiddenField (CPageParam.FIELD_NONCE, aCSRFSessionMgr.getNonce ());
    }

    // If disabled, don't emit a nonce
    return null;
  }

  @Nullable
  @OverrideOnDemand
  public String getHeaderText (@Nonnull final WPECTYPE aWPEC)
  {
    final Locale aDisplayLocale = aWPEC.getDisplayLocale ();
    return getDisplayText (aDisplayLocale);
  }

  @Nullable
  @OverrideOnDemand
  public IHCNode getHeaderNode (@Nonnull final WPECTYPE aWPEC)
  {
    final String sHeaderText = getHeaderText (aWPEC);
    if (StringHelper.hasNoText (sHeaderText))
      return null;
    return new HCH1 ().addChild (sHeaderText);
  }

  /**
   * Check some pre-requisites. This is called as the very first action on each
   * page view.
   *
   * @param aWPEC
   *        The web page execution context
   * @return Never <code>null</code>.
   */
  @OverrideOnDemand
  @Nonnull
  protected IValidityIndicator isValidToDisplayPage (@Nonnull final WPECTYPE aWPEC)
  {
    return EValidity.VALID;
  }

  /**
   * This method is called before the main
   * {@link #fillContent(IWebPageExecutionContext)} method is called.
   *
   * @param aWPEC
   *        The web page execution context. Never <code>null</code>.
   */
  @OverrideOnDemand
  protected void beforeFillContent (@Nonnull final WPECTYPE aWPEC)
  {}

  /**
   * Abstract method to be implemented by subclasses, that creates the main page
   * content. This method is only called, when
   * {@link #isValidToDisplayPage(IWebPageExecutionContext)} returned
   * <code>true</code>.
   *
   * @param aWPEC
   *        The web page execution context. Never <code>null</code>.
   */
  protected abstract void fillContent (@Nonnull WPECTYPE aWPEC);

  /**
   * This method is called after the main
   * {@link #fillContent(IWebPageExecutionContext)} method is called.
   *
   * @param aWPEC
   *        The web page execution context. Never <code>null</code>.
   */
  @OverrideOnDemand
  protected void afterFillContent (@Nonnull final WPECTYPE aWPEC)
  {}

  /**
   * A callback method that is is invoked if
   * {@link #isValidToDisplayPage(IWebPageExecutionContext)} returned
   * <code>false</code>.
   *
   * @param aWPEC
   *        The web page execution context. Never <code>null</code>.
   */
  @OverrideOnDemand
  protected void onInvalidToDisplayPage (@Nonnull final WPECTYPE aWPEC)
  {}

  /**
   * Default implementation calling the abstract fillContent method and creating
   * the help node if desired.
   */
  public final void getContent (@Nonnull final WPECTYPE aWPEC)
  {
    if (isValidToDisplayPage (aWPEC).isValid ())
    {
      // "before"-callback
      beforeFillContent (aWPEC);

      // Create the main page content
      fillContent (aWPEC);

      // "after"-callback
      afterFillContent (aWPEC);
    }
    else
    {
      // Invalid to display page
      onInvalidToDisplayPage (aWPEC);
    }
  }

  // Utility methods start here

  @Nullable
  public static IHCElementWithChildren <?> createActionHeaderDefault ()
  {
    return new HCDiv ().addClass (CUICoreCSS.CSS_CLASS_ACTION_HEADER);
  }

  @Nullable
  public static IHCElementWithChildren <?> createActionHeaderDefault (@Nullable final String sText)
  {
    if (StringHelper.hasNoText (sText))
      return null;
    return createActionHeaderDefault ().addChild (sText);
  }

  @Nonnull
  protected IHCElementWithChildren <?> createActionHeader ()
  {
    return createActionHeaderDefault ();
  }

  @Nullable
  protected IHCElementWithChildren <?> createActionHeader (@Nullable final String sText)
  {
    return createActionHeaderDefault (sText);
  }

  @Nonnull
  public static IHCElementWithChildren <?> createDataGroupHeaderDefault ()
  {
    return new HCDiv ().addClass (CUICoreCSS.CSS_CLASS_DATAGROUP_HEADER);
  }

  @Nullable
  public static IHCElementWithChildren <?> createDataGroupHeaderDefault (@Nullable final String sText)
  {
    if (StringHelper.hasNoText (sText))
      return null;
    return createDataGroupHeaderDefault ().addChild (sText);
  }

  @Nonnull
  protected IHCElementWithChildren <?> createDataGroupHeader ()
  {
    return createDataGroupHeaderDefault ();
  }

  @Nullable
  protected IHCElementWithChildren <?> createDataGroupHeader (@Nullable final String sText)
  {
    return createDataGroupHeaderDefault (sText);
  }
}
