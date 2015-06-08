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

import javax.annotation.Nonnegative;
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
import com.helger.commons.string.StringHelper;
import com.helger.commons.text.IReadonlyMultiLingualText;
import com.helger.commons.url.ISimpleURL;
import com.helger.commons.url.SimpleURL;
import com.helger.commons.url.URLValidator;
import com.helger.css.ECSSUnit;
import com.helger.css.property.CCSSProperties;
import com.helger.html.css.DefaultCSSClassProvider;
import com.helger.html.css.ICSSClassProvider;
import com.helger.html.hc.CHCParam;
import com.helger.html.hc.IHCElementWithChildren;
import com.helger.html.hc.IHCNode;
import com.helger.html.hc.html.HCA;
import com.helger.html.hc.html.HCCol;
import com.helger.html.hc.html.HCDiv;
import com.helger.html.hc.html.HCH4;
import com.helger.html.hc.html.HCHiddenField;
import com.helger.html.hc.html.HCSpan;
import com.helger.html.hc.html.HC_Target;
import com.helger.html.hc.htmlext.HCA_MailTo;
import com.helger.html.hc.impl.HCTextNode;
import com.helger.photon.core.app.context.ILayoutExecutionContext;
import com.helger.photon.core.form.csrf.CSRFSessionManager;
import com.helger.photon.uicore.css.WebCtrlsCSS;
import com.helger.photon.uicore.icon.EDefaultIcon;

/**
 * {@link AbstractWebPage} with additional utility methods and constants.
 *
 * @author Philip Helger
 * @param <WPECTYPE>
 *        The web page execution implementation type to use
 */
public abstract class AbstractWebPageExt <WPECTYPE extends IWebPageExecutionContext> extends AbstractWebPage <WPECTYPE>
{
  public static final boolean DEFAULT_CSRF_PREVENTION_ENABLED = true;

  /** The width of a single action inside the action column in pixels */
  public static final int DEFAULT_ACTION_COL_WIDTH = 20;

  /** Column width for date time values, so that it matches multiple languages */
  public static final int COLUMN_WIDTH_DATE = 100;
  public static final int COLUMN_WIDTH_DATETIME = 170;

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

  protected static final ICSSClassProvider CSS_CLASS_LEFT = WebCtrlsCSS.CSS_CLASS_LEFT;
  protected static final ICSSClassProvider CSS_CLASS_CENTER = WebCtrlsCSS.CSS_CLASS_CENTER;
  protected static final ICSSClassProvider CSS_CLASS_RIGHT = WebCtrlsCSS.CSS_CLASS_RIGHT;
  protected static final ICSSClassProvider CSS_CLASS_NOWRAP = WebCtrlsCSS.CSS_CLASS_NOWRAP;
  protected static final ICSSClassProvider CSS_CLASS_ACTION_COL = WebCtrlsCSS.CSS_CLASS_ACTION_COL;
  protected static final ICSSClassProvider CSS_CLASS_EMPTY_ACTION = WebCtrlsCSS.CSS_CLASS_EMPTY_ACTION;
  protected static final ICSSClassProvider CSS_CLASS_ACTION_HEADER = DefaultCSSClassProvider.create ("action-header");
  protected static final ICSSClassProvider CSS_CLASS_DATAGROUP_HEADER = DefaultCSSClassProvider.create ("datagroup-header");

  private static final Logger s_aLogger = LoggerFactory.getLogger (AbstractWebPageExt.class);

  private static int s_nActionColWidth = DEFAULT_ACTION_COL_WIDTH;

  private boolean m_bCSRFPreventionEnabled = DEFAULT_CSRF_PREVENTION_ENABLED;

  public AbstractWebPageExt (@Nonnull @Nonempty final String sID, @Nonnull final String sName)
  {
    super (sID, sName);
  }

  public AbstractWebPageExt (@Nonnull @Nonempty final String sID, @Nonnull final IReadonlyMultiLingualText aName)
  {
    super (sID, aName);
  }

  public AbstractWebPageExt (@Nonnull @Nonempty final String sID,
                             @Nonnull final String sName,
                             @Nullable final String sDescription)
  {
    super (sID, sName, sDescription);
  }

  public AbstractWebPageExt (@Nonnull @Nonempty final String sID,
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

  @Nonnegative
  public static int getActionColWidth ()
  {
    return s_nActionColWidth;
  }

  public static void setActionColWidth (@Nonnegative final int nActionColWidth)
  {
    s_nActionColWidth = nActionColWidth;
  }

  /**
   * Create a HCCol (table column) for the specified number of actions. Each
   * action represents a width of {@link #getActionColWidth()} pixels. At least
   * the width of 3 actions is displayed, so that the header text fits :)
   *
   * @param nActions
   *        Number of actions. Must be &ge; 0.
   * @return The column with the according column width.
   */
  @Nonnull
  public static HCCol createActionCol (@Nonnegative final int nActions)
  {
    // Assume each action icon is 20 pixels (incl. margin) - at least 3 column
    // widths are required for the header
    final int nWidth = getActionColWidth () * Math.max (3, nActions);
    return new HCCol (nWidth);
  }

  @Nonnull
  public static HCSpan createEmptyAction ()
  {
    // Assume each icon has a width of 16px
    return new HCSpan ().addClass (CSS_CLASS_EMPTY_ACTION)
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
    return new HCH4 ().addClass (CSS_CLASS_ACTION_HEADER).addChild (sText);
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

  @Nullable
  public static IHCElementWithChildren <?> createDataGroupHeaderDefault (@Nullable final String sText)
  {
    if (StringHelper.hasNoText (sText))
      return null;
    return new HCDiv ().addClass (CSS_CLASS_DATAGROUP_HEADER).addChild (sText);
  }

  @Nullable
  protected IHCElementWithChildren <?> createDataGroupHeader (@Nullable final String sText)
  {
    return createDataGroupHeaderDefault (sText);
  }
}
