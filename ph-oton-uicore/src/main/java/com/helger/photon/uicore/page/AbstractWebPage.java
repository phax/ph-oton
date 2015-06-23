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
import com.helger.commons.state.IValidityIndicator;
import com.helger.commons.string.StringHelper;
import com.helger.commons.text.IReadonlyMultiLingualText;
import com.helger.commons.url.ISimpleURL;
import com.helger.commons.url.SimpleURL;
import com.helger.commons.url.URLValidator;
import com.helger.css.ECSSUnit;
import com.helger.css.property.CCSSProperties;
import com.helger.html.css.ICSSClassProvider;
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
import com.helger.photon.core.app.context.ILayoutExecutionContext;
import com.helger.photon.core.form.csrf.CSRFSessionManager;
import com.helger.photon.uicore.css.CPageParam;
import com.helger.photon.uicore.css.CUICoreCSS;
import com.helger.photon.uicore.icon.EDefaultIcon;

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

  protected static final ICSSClassProvider CSS_CLASS_LEFT = CUICoreCSS.CSS_CLASS_LEFT;
  protected static final ICSSClassProvider CSS_CLASS_CENTER = CUICoreCSS.CSS_CLASS_CENTER;
  protected static final ICSSClassProvider CSS_CLASS_RIGHT = CUICoreCSS.CSS_CLASS_RIGHT;
  protected static final ICSSClassProvider CSS_CLASS_NOWRAP = CUICoreCSS.CSS_CLASS_NOWRAP;

  protected static final String ACTION_CANCEL = CPageParam.ACTION_CANCEL;
  protected static final String ACTION_COLLAPSE = CPageParam.ACTION_COLLAPSE;
  protected static final String ACTION_COPY = CPageParam.ACTION_COPY;
  protected static final String ACTION_CREATE = CPageParam.ACTION_CREATE;
  protected static final String ACTION_DELETE = CPageParam.ACTION_DELETE;
  protected static final String ACTION_DELETE_ALL = CPageParam.ACTION_DELETE_ALL;
  protected static final String ACTION_EDIT = CPageParam.ACTION_EDIT;
  protected static final String ACTION_EXPAND = CPageParam.ACTION_EXPAND;
  protected static final String ACTION_PERFORM = CPageParam.ACTION_PERFORM;
  protected static final String ACTION_SAVE = CPageParam.ACTION_SAVE;
  protected static final String ACTION_UNDELETE = CPageParam.ACTION_UNDELETE;
  protected static final String ACTION_UNDELETE_ALL = CPageParam.ACTION_UNDELETE_ALL;
  protected static final String ACTION_VIEW = CPageParam.ACTION_VIEW;

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
    return aLEC.getLinkToMenuItem (sMenuItemID).add (CPageParam.PARAM_ACTION, ACTION_CREATE);
  }

  @Nonnull
  public static SimpleURL createCreateURL (@Nonnull final ILayoutExecutionContext aLEC)
  {
    return aLEC.getSelfHref ().add (CPageParam.PARAM_ACTION, ACTION_CREATE);
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
               .add (CPageParam.PARAM_ACTION, ACTION_VIEW)
               .add (CPageParam.PARAM_OBJECT, sObjectID);
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
    return aLEC.getSelfHref ().add (CPageParam.PARAM_ACTION, ACTION_VIEW).add (CPageParam.PARAM_OBJECT, sObjectID);
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
               .add (CPageParam.PARAM_ACTION, ACTION_EDIT)
               .add (CPageParam.PARAM_OBJECT, aCurObject.getID ());
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
               .add (CPageParam.PARAM_ACTION, ACTION_COPY)
               .add (CPageParam.PARAM_OBJECT, aCurObject.getID ());
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
               .add (CPageParam.PARAM_ACTION, ACTION_DELETE)
               .add (CPageParam.PARAM_OBJECT, aCurObject.getID ());
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
               .add (CPageParam.PARAM_ACTION, ACTION_UNDELETE)
               .add (CPageParam.PARAM_OBJECT, aCurObject.getID ());
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
    final ISimpleURL aURL = createCreateURL (aLEC).add (CPageParam.PARAM_OBJECT, aCurObject.getID ());
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
  protected IValidityIndicator isValidToDisplayPage (@Nonnull final WPECTYPE aWPEC)
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
   */
  @OverrideOnDemand
  protected void afterFillContent ()
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
      beforeFillContent ();

      // Create the main page content
      fillContent (aWPEC);

      // "after"-callback
      afterFillContent ();
    }
  }
}
