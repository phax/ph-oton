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
import java.util.Map;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.helger.commons.annotations.Nonempty;
import com.helger.commons.annotations.OverrideOnDemand;
import com.helger.commons.email.IEmailAddress;
import com.helger.commons.id.IHasID;
import com.helger.commons.name.IHasDisplayName;
import com.helger.commons.name.IHasDisplayText;
import com.helger.commons.state.EContinue;
import com.helger.commons.state.EValidity;
import com.helger.commons.string.StringHelper;
import com.helger.commons.text.IReadonlyMultiLingualText;
import com.helger.commons.url.ISimpleURL;
import com.helger.commons.url.SMap;
import com.helger.commons.url.SimpleURL;
import com.helger.commons.url.URLValidator;
import com.helger.css.ECSSUnit;
import com.helger.css.property.CCSSProperties;
import com.helger.html.css.DefaultCSSClassProvider;
import com.helger.html.css.ICSSClassProvider;
import com.helger.html.hc.CHCParam;
import com.helger.html.hc.IHCElementWithChildren;
import com.helger.html.hc.IHCNode;
import com.helger.html.hc.html.AbstractHCForm;
import com.helger.html.hc.html.HCA;
import com.helger.html.hc.html.HCDiv;
import com.helger.html.hc.html.HCForm;
import com.helger.html.hc.html.HCH1;
import com.helger.html.hc.html.HCH4;
import com.helger.html.hc.html.HCHiddenField;
import com.helger.html.hc.html.HCSpan;
import com.helger.html.hc.html.HC_Target;
import com.helger.html.hc.htmlext.HCA_MailTo;
import com.helger.html.hc.impl.HCTextNode;
import com.helger.photon.basic.app.page.AbstractPage;
import com.helger.photon.core.EPhotonCoreText;
import com.helger.photon.core.app.context.ILayoutExecutionContext;
import com.helger.photon.core.form.csrf.CSRFSessionManager;
import com.helger.photon.core.url.LinkUtils;
import com.helger.photon.uicore.css.CUICoreCSS;
import com.helger.photon.uicore.icon.EDefaultIcon;
import com.helger.web.scopes.domain.IRequestWebScopeWithoutResponse;
import com.helger.web.scopes.mgr.WebScopeManager;

/**
 * Abstract base implementation for {@link IWebPage}.
 *
 * @author Philip Helger
 * @param <WPECTYPE>
 *        Web page execution context type
 */
public abstract class AbstractWebPage <WPECTYPE extends IWebPageExecutionContext> extends AbstractPage implements IWebPage <WPECTYPE>
{
  public static final boolean DEFAULT_CSRF_PREVENTION_ENABLED = true;

  /** The CSS class to be applied to the help div */
  public static final ICSSClassProvider CSS_PAGE_HELP_ICON = DefaultCSSClassProvider.create ("page_help_icon");
  protected static final ICSSClassProvider CSS_CLASS_LEFT = CUICoreCSS.CSS_CLASS_LEFT;
  protected static final ICSSClassProvider CSS_CLASS_CENTER = CUICoreCSS.CSS_CLASS_CENTER;
  protected static final ICSSClassProvider CSS_CLASS_RIGHT = CUICoreCSS.CSS_CLASS_RIGHT;
  protected static final ICSSClassProvider CSS_CLASS_NOWRAP = CUICoreCSS.CSS_CLASS_NOWRAP;

  public static final HC_Target HELP_WINDOW_TARGET = new HC_Target (HELP_WINDOW_NAME);

  public static final String ACTION_CANCEL = CHCParam.ACTION_CANCEL;
  public static final String ACTION_COLLAPSE = CHCParam.ACTION_COLLAPSE;
  public static final String ACTION_COPY = CHCParam.ACTION_COPY;
  public static final String ACTION_CREATE = CHCParam.ACTION_CREATE;
  public static final String ACTION_DELETE = CHCParam.ACTION_DELETE;
  public static final String ACTION_DELETE_ALL = CHCParam.ACTION_DELETE_ALL;
  public static final String ACTION_EDIT = CHCParam.ACTION_EDIT;
  public static final String ACTION_EXPAND = CHCParam.ACTION_EXPAND;
  public static final String ACTION_PERFORM = CHCParam.ACTION_PERFORM;
  public static final String ACTION_SAVE = CHCParam.ACTION_SAVE;
  public static final String ACTION_UNDELETE = CHCParam.ACTION_UNDELETE;
  public static final String ACTION_UNDELETE_ALL = CHCParam.ACTION_UNDELETE_ALL;
  public static final String ACTION_VIEW = CHCParam.ACTION_VIEW;

  public static final String FIELD_NONCE = "$ph_nonce";

  public static final String PARAM_SOURCE_MENU_ITEM = "srcmi";

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
  public AbstractWebPage (@Nonnull @Nonempty final String sID, @Nonnull final IReadonlyMultiLingualText aName)
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
                          @Nonnull final IReadonlyMultiLingualText aName,
                          @Nullable final IReadonlyMultiLingualText aDescription)
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
      final String sNonce = aWPEC.getAttributeAsString (FIELD_NONCE);
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
      return new HCHiddenField (FIELD_NONCE, aCSRFSessionMgr.getNonce ());
    }

    // If disabled, don't emit a nonce
    return null;
  }

  @Nonnull
  public static HCSpan createEmptyAction ()
  {
    // Assume each icon has a width of 16px
    return new HCSpan ().addClass (CUICoreCSS.CSS_CLASS_EMPTY_ACTION)
                        .addStyle (CCSSProperties.DISPLAY_INLINE_BLOCK)
                        .addStyle (CCSSProperties.WIDTH.newValue (ECSSUnit.px (16)));
  }

  @Nonnull
  public static SimpleURL createCreateURL (@Nonnull final ILayoutExecutionContext aLEC,
                                           @Nonnull final String sMenuItemID)
  {
    return aLEC.getLinkToMenuItem (sMenuItemID).add (CHCParam.PARAM_ACTION, ACTION_CREATE);
  }

  @Nonnull
  public static SimpleURL createCreateURL (@Nonnull final ILayoutExecutionContext aLEC)
  {
    return aLEC.getSelfHref ().add (CHCParam.PARAM_ACTION, ACTION_CREATE);
  }

  @Nonnull
  public static SimpleURL createViewURL (@Nonnull final ILayoutExecutionContext aLEC,
                                         @Nonnull final String sMenuItemID,
                                         @Nonnull final IHasID <String> aCurObject)
  {
    return createViewURL (aLEC, sMenuItemID, aCurObject.getID ());
  }

  @Nonnull
  public static SimpleURL createViewURL (@Nonnull final ILayoutExecutionContext aLEC,
                                         @Nonnull final String sMenuItemID,
                                         @Nonnull final String sObjectID)
  {
    return aLEC.getLinkToMenuItem (sMenuItemID)
               .add (CHCParam.PARAM_ACTION, ACTION_VIEW)
               .add (CHCParam.PARAM_OBJECT, sObjectID);
  }

  @Nonnull
  public static SimpleURL createViewURL (@Nonnull final ILayoutExecutionContext aLEC,
                                         @Nonnull final IHasID <String> aCurObject)
  {
    return createViewURL (aLEC, aCurObject.getID ());
  }

  @Nonnull
  public static SimpleURL createViewURL (@Nonnull final ILayoutExecutionContext aLEC, @Nonnull final String sObjectID)
  {
    return aLEC.getSelfHref ().add (CHCParam.PARAM_ACTION, ACTION_VIEW).add (CHCParam.PARAM_OBJECT, sObjectID);
  }

  @Nonnull
  public static <T extends IHasDisplayName & IHasID <String>> HCA createEditLink (@Nonnull final ILayoutExecutionContext aLEC,
                                                                                  @Nonnull final T aCurObject)
  {
    return createEditLink (aLEC, aCurObject, (Map <String, String>) null);
  }

  @Nonnull
  public static <T extends IHasDisplayName & IHasID <String>> HCA createEditLink (@Nonnull final ILayoutExecutionContext aLEC,
                                                                                  @Nonnull final T aCurObject,
                                                                                  @Nullable final Map <String, String> aParams)
  {
    final Locale aDisplayLocale = aLEC.getDisplayLocale ();
    return createEditLink (aLEC,
                           aCurObject,
                           EWebPageText.OBJECT_EDIT.getDisplayTextWithArgs (aDisplayLocale,
                                                                            aCurObject.getDisplayName ()),
                           aParams);
  }

  @Nonnull
  public static <T extends IHasDisplayText & IHasID <String>> HCA createEditLink (@Nonnull final ILayoutExecutionContext aLEC,
                                                                                  @Nonnull final T aCurObject)
  {
    return createEditLink (aLEC, aCurObject, (Map <String, String>) null);
  }

  @Nonnull
  public static <T extends IHasDisplayText & IHasID <String>> HCA createEditLink (@Nonnull final ILayoutExecutionContext aLEC,
                                                                                  @Nonnull final T aCurObject,
                                                                                  @Nullable final Map <String, String> aParams)
  {
    final Locale aDisplayLocale = aLEC.getDisplayLocale ();
    return createEditLink (aLEC,
                           aCurObject,
                           EWebPageText.OBJECT_EDIT.getDisplayTextWithArgs (aDisplayLocale,
                                                                            aCurObject.getDisplayText (aDisplayLocale)),
                           aParams);
  }

  @Nonnull
  public static IHCNode getEditImg ()
  {
    return EDefaultIcon.EDIT.getAsNode ();
  }

  @Nonnull
  public static SimpleURL createEditURL (@Nonnull final ILayoutExecutionContext aLEC,
                                         @Nonnull final IHasID <String> aCurObject)
  {
    return aLEC.getSelfHref ()
               .add (CHCParam.PARAM_ACTION, ACTION_EDIT)
               .add (CHCParam.PARAM_OBJECT, aCurObject.getID ());
  }

  @Nonnull
  public static HCA createEditLink (@Nonnull final ILayoutExecutionContext aLEC,
                                    @Nonnull final IHasID <String> aCurObject,
                                    @Nullable final String sTitle)
  {
    return createEditLink (aLEC, aCurObject, sTitle, (Map <String, String>) null);
  }

  @Nonnull
  public static HCA createEditLink (@Nonnull final ILayoutExecutionContext aLEC,
                                    @Nonnull final IHasID <String> aCurObject,
                                    @Nullable final String sTitle,
                                    @Nullable final Map <String, String> aParams)
  {
    final ISimpleURL aEditURL = createEditURL (aLEC, aCurObject).addAll (aParams);
    return new HCA (aEditURL).setTitle (sTitle).addChild (getEditImg ());
  }

  @Nonnull
  public static <T extends IHasDisplayName & IHasID <String>> HCA createCopyLink (@Nonnull final ILayoutExecutionContext aLEC,
                                                                                  @Nonnull final T aCurObject)
  {
    final Locale aDisplayLocale = aLEC.getDisplayLocale ();
    return createCopyLink (aLEC,
                           aCurObject,
                           EWebPageText.OBJECT_COPY.getDisplayTextWithArgs (aDisplayLocale,
                                                                            aCurObject.getDisplayName ()));
  }

  @Nonnull
  public static <T extends IHasDisplayText & IHasID <String>> HCA createCopyLink (@Nonnull final ILayoutExecutionContext aLEC,
                                                                                  @Nonnull final T aCurObject)
  {
    final Locale aDisplayLocale = aLEC.getDisplayLocale ();
    return createCopyLink (aLEC,
                           aCurObject,
                           EWebPageText.OBJECT_COPY.getDisplayTextWithArgs (aDisplayLocale,
                                                                            aCurObject.getDisplayText (aDisplayLocale)));
  }

  @Nonnull
  public static IHCNode getCopyImg ()
  {
    return EDefaultIcon.COPY.getAsNode ();
  }

  @Nonnull
  public static SimpleURL createCopyURL (@Nonnull final ILayoutExecutionContext aLEC,
                                         @Nonnull final IHasID <String> aCurObject)
  {
    return aLEC.getSelfHref ()
               .add (CHCParam.PARAM_ACTION, ACTION_COPY)
               .add (CHCParam.PARAM_OBJECT, aCurObject.getID ());
  }

  @Nonnull
  public static HCA createCopyLink (@Nonnull final ILayoutExecutionContext aLEC,
                                    @Nonnull final IHasID <String> aCurObject,
                                    @Nullable final String sTitle)
  {
    final ISimpleURL aCopyURL = createCopyURL (aLEC, aCurObject);
    return new HCA (aCopyURL).setTitle (sTitle).addChild (getCopyImg ());
  }

  @Nonnull
  public static <T extends IHasDisplayName & IHasID <String>> HCA createDeleteLink (@Nonnull final ILayoutExecutionContext aLEC,
                                                                                    @Nonnull final T aCurObject)
  {
    final Locale aDisplayLocale = aLEC.getDisplayLocale ();
    return createDeleteLink (aLEC,
                             aCurObject,
                             EWebPageText.OBJECT_DELETE.getDisplayTextWithArgs (aDisplayLocale,
                                                                                aCurObject.getDisplayName ()));
  }

  @Nonnull
  public static <T extends IHasDisplayText & IHasID <String>> HCA createDeleteLink (@Nonnull final ILayoutExecutionContext aLEC,
                                                                                    @Nonnull final T aCurObject)
  {
    final Locale aDisplayLocale = aLEC.getDisplayLocale ();
    return createDeleteLink (aLEC,
                             aCurObject,
                             EWebPageText.OBJECT_DELETE.getDisplayTextWithArgs (aDisplayLocale,
                                                                                aCurObject.getDisplayText (aDisplayLocale)));
  }

  @Nonnull
  public static IHCNode getDeleteImg ()
  {
    return EDefaultIcon.DELETE.getAsNode ();
  }

  @Nonnull
  public static SimpleURL createDeleteURL (@Nonnull final ILayoutExecutionContext aLEC,
                                           @Nonnull final IHasID <String> aCurObject)
  {
    return aLEC.getSelfHref ()
               .add (CHCParam.PARAM_ACTION, ACTION_DELETE)
               .add (CHCParam.PARAM_OBJECT, aCurObject.getID ());
  }

  @Nonnull
  public static HCA createDeleteLink (@Nonnull final ILayoutExecutionContext aLEC,
                                      @Nonnull final IHasID <String> aCurObject,
                                      @Nullable final String sTitle)
  {
    final ISimpleURL aURL = createDeleteURL (aLEC, aCurObject);
    return new HCA (aURL).setTitle (sTitle).addChild (getDeleteImg ());
  }

  @Nonnull
  public static SimpleURL createUndeleteURL (@Nonnull final ILayoutExecutionContext aLEC,
                                             @Nonnull final IHasID <String> aCurObject)
  {
    return aLEC.getSelfHref ()
               .add (CHCParam.PARAM_ACTION, ACTION_UNDELETE)
               .add (CHCParam.PARAM_OBJECT, aCurObject.getID ());
  }

  @Nonnull
  public static IHCNode getCreateImg ()
  {
    return EDefaultIcon.NEW.getAsNode ();
  }

  @Nonnull
  public static HCA createNestedCreateLink (@Nonnull final ILayoutExecutionContext aLEC,
                                            @Nonnull final IHasID <String> aCurObject,
                                            @Nullable final String sTitle)
  {
    final ISimpleURL aURL = createCreateURL (aLEC).add (CHCParam.PARAM_OBJECT, aCurObject.getID ());
    return new HCA (aURL).setTitle (sTitle).addChild (getCreateImg ());
  }

  @Nullable
  public static IHCNode createEmailLink (@Nullable final String sEmailAddress)
  {
    return HCA_MailTo.createLinkedEmail (sEmailAddress);
  }

  @Nullable
  public static IHCNode createEmailLink (@Nullable final IEmailAddress aEmail)
  {
    return HCA_MailTo.createLinkedEmail (aEmail);
  }

  @Nullable
  public static IHCNode createWebLink (@Nullable final String sWebSite)
  {
    return createWebLink (sWebSite, HC_Target.BLANK);
  }

  @Nullable
  public static IHCNode createWebLink (@Nullable final String sWebSite, @Nullable final HC_Target aTarget)
  {
    if (StringHelper.hasNoText (sWebSite))
      return null;
    if (!URLValidator.isValid (sWebSite))
      return new HCTextNode (sWebSite);
    return new HCA (sWebSite).setTarget (aTarget).addChild (sWebSite);
  }

  @Nullable
  public static IHCElementWithChildren <?> createActionHeaderDefault (@Nullable final String sText)
  {
    if (StringHelper.hasNoText (sText))
      return null;
    return new HCH4 ().addClass (CUICoreCSS.CSS_CLASS_ACTION_HEADER).addChild (sText);
  }

  /**
   * @deprecated Use {@link #createActionHeader(String)} instead
   */
  @Deprecated
  @Nullable
  protected IHCNode createInPageHeader (@Nullable final String sText)
  {
    return createActionHeader (sText);
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

  @Nullable
  @OverrideOnDemand
  public String getHeaderText (@Nonnull final WPECTYPE aWPEC)
  {
    return getDisplayText (aWPEC.getDisplayLocale ());
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

  @Nonnull
  @Deprecated
  public static final IRequestWebScopeWithoutResponse getScope ()
  {
    return WebScopeManager.getRequestScope ();
  }

  /**
   * @param aLEC
   *        Layout execution context
   * @return A form that links to the current page.
   */
  @Nonnull
  public AbstractHCForm <?> createFormSelf (@Nonnull final ILayoutExecutionContext aLEC)
  {
    return new HCForm (aLEC.getSelfHref ());
  }

  /**
   * @param aLEC
   *        Layout execution context
   * @return A file upload form that links to the current page.
   */
  @Nonnull
  public AbstractHCForm <?> createFormFileUploadSelf (@Nonnull final ILayoutExecutionContext aLEC)
  {
    return createFormSelf (aLEC).setEncTypeFileUpload ();
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
  protected EValidity isValidToDisplayPage (@Nonnull final WPECTYPE aWPEC)
  {
    return EValidity.VALID;
  }

  /**
   * This method is called before the main
   * {@link #fillContent(IWebPageExecutionContext)} method is called.
   */
  @OverrideOnDemand
  protected void beforeFillContent ()
  {}

  /**
   * Abstract method to be implemented by subclasses, that creates the main page
   * content, without the help icon.
   *
   * @param aWPEC
   *        The web page execution context. Never <code>null</code>.
   */
  protected abstract void fillContent (@Nonnull WPECTYPE aWPEC);

  /**
   * This method is called after the main
   * {@link #fillContent(IWebPageExecutionContext)} method is called.
   */
  @OverrideOnDemand
  protected void afterFillContent ()
  {}

  /**
   * Get the help URL of the current page
   *
   * @param aRequestScope
   *        The request web scope to be used. Required for cookie-less handling.
   *        May not be <code>null</code>.
   * @param aDisplayLocale
   *        The current display locale. Never <code>null</code>.
   * @return The help URL for this page. May not be <code>null</code>.
   */
  @Nonnull
  @OverrideOnDemand
  protected ISimpleURL getHelpURL (@Nonnull final IRequestWebScopeWithoutResponse aRequestScope,
                                   @Nonnull final Locale aDisplayLocale)
  {
    return LinkUtils.getURLWithContext (aRequestScope,
                                        "help/" + getID (),
                                        new SMap ().add ("locale", aDisplayLocale.toString ()));
  }

  /**
   * Create the HC node to represent the help icon. This method is only called,
   * if help is available for this page. The created code looks like this by
   * default:<br>
   * <code>&lt;a href="<i>helpURL</i>" title="Show help for page <i>pageName</i>" target="simplehelpwindow"&gt;<br>
   * &lt;span class="page_help_icon"&gt;&lt;/span&gt;<br>
   * &lt;/a&gt;</code>
   *
   * @param aWPEC
   *        The web page execution context
   * @return The created help icon node. May be <code>null</code>.
   */
  @Nullable
  @OverrideOnDemand
  protected IHCNode getHelpIconNode (@Nonnull final WPECTYPE aWPEC)
  {
    final IRequestWebScopeWithoutResponse aRequestScope = aWPEC.getRequestScope ();
    final Locale aDisplayLocale = aWPEC.getDisplayLocale ();

    final HCA aHelpNode = new HCA (getHelpURL (aRequestScope, aDisplayLocale));
    final String sPageName = getDisplayText (aDisplayLocale);
    aHelpNode.setTitle (EPhotonCoreText.PAGE_HELP_TITLE.getDisplayTextWithArgs (aDisplayLocale, sPageName));
    aHelpNode.addChild (new HCSpan ().addClass (CSS_PAGE_HELP_ICON));
    aHelpNode.setTarget (HELP_WINDOW_TARGET);
    return aHelpNode;
  }

  /**
   * Check if is help is available for the current execution context
   *
   * @param aWPEC
   *        The web page execution context
   * @return <code>true</code> if help is available, <code>false</code> if not
   */
  @OverrideOnDemand
  public boolean isHelpAvailable (@Nonnull final WPECTYPE aWPEC)
  {
    return false;
  }

  /**
   * Overridable method to attach the help node to the page. This is called as
   * the last action.
   *
   * @param aWPEC
   *        The web page execution context. Never <code>null</code>.
   * @param aHelpNode
   *        The help node to be inserted. Never <code>null</code>.
   */
  @OverrideOnDemand
  protected void insertHelpNode (@Nonnull final WPECTYPE aWPEC, @Nonnull final IHCNode aHelpNode)
  {
    // Add the help icon as the first child of the resulting node list
    aWPEC.getNodeList ().addChild (0, aHelpNode);
  }

  /**
   * Default implementation calling the abstract fillContent method and creating
   * the help node if desired.
   */
  public final void getContent (@Nonnull final WPECTYPE aWPEC)
  {
    if (isValidToDisplayPage (aWPEC).isValid ())
    {
      // "before"-callback
      beforeFillContent ();

      // Create the main page content
      fillContent (aWPEC);

      // "after"-callback
      afterFillContent ();
    }

    // Is help available for this page?
    if (isHelpAvailable (aWPEC))
    {
      final IHCNode aHelpNode = getHelpIconNode (aWPEC);
      if (aHelpNode != null)
        insertHelpNode (aWPEC, aHelpNode);
    }
  }
}
