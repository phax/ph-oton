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
import javax.annotation.OverridingMethodsMustInvokeSuper;
import javax.annotation.concurrent.NotThreadSafe;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.helger.commons.annotation.Nonempty;
import com.helger.commons.annotation.OverrideOnDemand;
import com.helger.commons.annotation.ReturnsMutableCopy;
import com.helger.commons.errorlist.FormErrors;
import com.helger.commons.id.IHasID;
import com.helger.commons.id.factory.GlobalIDFactory;
import com.helger.commons.name.IHasDisplayName;
import com.helger.commons.state.EContinue;
import com.helger.commons.string.StringHelper;
import com.helger.commons.text.IMultilingualText;
import com.helger.commons.text.display.IHasDisplayText;
import com.helger.commons.url.ISimpleURL;
import com.helger.commons.url.SimpleURL;
import com.helger.css.ECSSUnit;
import com.helger.css.property.CCSSProperties;
import com.helger.html.hc.IHCNode;
import com.helger.html.hc.html.forms.HCHiddenField;
import com.helger.html.hc.html.forms.IHCForm;
import com.helger.html.hc.html.script.HCScriptInlineOnDocumentReady;
import com.helger.html.hc.html.textlevel.HCA;
import com.helger.html.hc.html.textlevel.HCSpan;
import com.helger.html.hc.impl.HCNodeList;
import com.helger.html.jscode.JSArray;
import com.helger.html.jscode.JSAssocArray;
import com.helger.photon.core.EPhotonCoreText;
import com.helger.photon.core.app.context.ILayoutExecutionContext;
import com.helger.photon.core.form.FormState;
import com.helger.photon.core.form.FormStateManager;
import com.helger.photon.core.form.RequestField;
import com.helger.photon.security.lock.ILockManager;
import com.helger.photon.security.lock.LockResult;
import com.helger.photon.security.mgr.PhotonSecurityManager;
import com.helger.photon.security.user.IUser;
import com.helger.photon.uicore.css.CPageParam;
import com.helger.photon.uicore.css.CUICoreCSS;
import com.helger.photon.uicore.form.ajax.AjaxExecutorSaveFormState;
import com.helger.photon.uicore.html.toolbar.IButtonToolbar;
import com.helger.photon.uicore.icon.EDefaultIcon;
import com.helger.photon.uicore.icon.IIcon;
import com.helger.photon.uicore.js.JSFormHelper;

/**
 * Abstract base class for a web page that has the common form handling, with a
 * list view, details view, create and edit + binding.
 *
 * @author Philip Helger
 * @param <DATATYPE>
 *        The data type of the object to be handled.
 * @param <WPECTYPE>
 *        Web page execution context type
 * @param <FORM_TYPE>
 *        The form implementation type.
 * @param <TOOLBAR_TYPE>
 *        The form implementation type.
 */
@NotThreadSafe
public abstract class AbstractWebPageForm <DATATYPE extends IHasID <String>, WPECTYPE extends IWebPageExecutionContext, FORM_TYPE extends IHCForm <FORM_TYPE>, TOOLBAR_TYPE extends IButtonToolbar <TOOLBAR_TYPE>>
                                          extends AbstractWebPage <WPECTYPE>
{
  // all internal IDs starting with "$" to prevent accidental overwrite with
  // actual field
  public static final String FIELD_FLOW_ID = AjaxExecutorSaveFormState.FIELD_FLOW_ID;
  public static final String FIELD_RESTORE_FLOW_ID = AjaxExecutorSaveFormState.FIELD_RESTORE_FLOW_ID;
  public static final String FORM_ID_INPUT = "inputform";
  public static final String FORM_ID_DELETE = "deleteform";

  private static final Logger s_aLogger = LoggerFactory.getLogger (AbstractWebPageForm.class);

  public AbstractWebPageForm (@Nonnull @Nonempty final String sID, @Nonnull final String sName)
  {
    super (sID, sName);
  }

  public AbstractWebPageForm (@Nonnull @Nonempty final String sID, @Nonnull final IMultilingualText aName)
  {
    super (sID, aName);
  }

  public AbstractWebPageForm (@Nonnull @Nonempty final String sID,
                              @Nonnull final String sName,
                              @Nullable final String sDescription)
  {
    super (sID, sName, sDescription);
  }

  public AbstractWebPageForm (@Nonnull @Nonempty final String sID,
                              @Nonnull final IMultilingualText aName,
                              @Nullable final IMultilingualText aDescription)
  {
    super (sID, aName, aDescription);
  }

  /**
   * @param aLEC
   *        Layout execution context
   * @return A form that links to the current page.
   */
  @Nonnull
  protected abstract FORM_TYPE createFormSelf (@Nonnull final ILayoutExecutionContext aLEC);

  /**
   * @param aLEC
   *        Layout execution context
   * @return A file upload form that links to the current page.
   */
  @Nonnull
  protected abstract FORM_TYPE createFormFileUploadSelf (@Nonnull final ILayoutExecutionContext aLEC);

  @Nonnull
  protected abstract TOOLBAR_TYPE createToolbar (@Nonnull WPECTYPE aWPEC);

  @Nullable
  protected abstract IHCNode createErrorBox (@Nonnull WPECTYPE aWPEC, @Nullable String sErrorMsg);

  @Nullable
  protected abstract IHCNode createIncorrectInputBox (@Nonnull WPECTYPE aWPEC);

  /**
   * Get the display name of the passed object.
   *
   * @param aWPEC
   *        The current web page execution context. Never <code>null</code>.
   * @param aSelectedObject
   *        The object to get the display name from. Never <code>null</code>.
   * @return <code>null</code> to indicate that no display name is available
   */
  @Nullable
  @OverrideOnDemand
  protected String getObjectDisplayName (@Nonnull final WPECTYPE aWPEC, @Nonnull final DATATYPE aSelectedObject)
  {
    return null;
  }

  /**
   * @param aWPEC
   *        The current web page execution context. Never <code>null</code>.
   * @return <code>true</code> if the form for
   *         {@link #showInputForm(IWebPageExecutionContext, IHasID, IHCForm, EWebPageFormAction, FormErrors)}
   *         should be a file-upload form, <code>false</code> if a regular form
   *         is sufficient.
   */
  @OverrideOnDemand
  protected boolean isFileUploadForm (@Nonnull final WPECTYPE aWPEC)
  {
    return false;
  }

  /**
   * Get the ID of the selected object from the passed execution context.
   *
   * @param aWPEC
   *        The current web page execution context. Never <code>null</code>.
   * @return <code>null</code> if no selected object is present.
   */
  @Nullable
  protected final String getSelectedObjectID (@Nonnull final WPECTYPE aWPEC)
  {
    return aWPEC.getAttributeAsString (CPageParam.PARAM_OBJECT);
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
    return aLEC.getLinkToMenuItem (sMenuItemID).add (CPageParam.PARAM_ACTION, CPageParam.ACTION_CREATE);
  }

  @Nonnull
  public static SimpleURL createCreateURL (@Nonnull final ILayoutExecutionContext aLEC)
  {
    return aLEC.getSelfHref ().add (CPageParam.PARAM_ACTION, CPageParam.ACTION_CREATE);
  }

  @Nonnull
  public static SimpleURL createViewURL (@Nonnull final ILayoutExecutionContext aLEC,
                                         @Nonnull final String sMenuItemID,
                                         @Nonnull final IHasID <String> aCurObject)
  {
    return createViewURL (aLEC, sMenuItemID, aCurObject, (Map <String, String>) null);
  }

  @Nonnull
  public static SimpleURL createViewURL (@Nonnull final ILayoutExecutionContext aLEC,
                                         @Nonnull final String sMenuItemID,
                                         @Nonnull final IHasID <String> aCurObject,
                                         @Nullable final Map <String, String> aParams)
  {
    return createViewURL (aLEC, sMenuItemID, aCurObject.getID (), aParams);
  }

  @Nonnull
  public static SimpleURL createViewURL (@Nonnull final ILayoutExecutionContext aLEC,
                                         @Nonnull final String sMenuItemID,
                                         @Nonnull final String sObjectID)
  {
    return createViewURL (aLEC, sMenuItemID, sObjectID, (Map <String, String>) null);
  }

  @Nonnull
  public static SimpleURL createViewURL (@Nonnull final ILayoutExecutionContext aLEC,
                                         @Nonnull final String sMenuItemID,
                                         @Nonnull final String sObjectID,
                                         @Nullable final Map <String, String> aParams)
  {
    return aLEC.getLinkToMenuItem (sMenuItemID)
               .add (CPageParam.PARAM_ACTION, CPageParam.ACTION_VIEW)
               .add (CPageParam.PARAM_OBJECT, sObjectID)
               .addAll (aParams);
  }

  @Nonnull
  public static SimpleURL createViewURL (@Nonnull final ILayoutExecutionContext aLEC,
                                         @Nonnull final IHasID <String> aCurObject)
  {
    return createViewURL (aLEC, aCurObject, (Map <String, String>) null);
  }

  @Nonnull
  public static SimpleURL createViewURL (@Nonnull final ILayoutExecutionContext aLEC,
                                         @Nonnull final IHasID <String> aCurObject,
                                         @Nullable final Map <String, String> aParams)
  {
    return createViewURL (aLEC, aCurObject.getID (), aParams);
  }

  @Nonnull
  public static SimpleURL createViewURL (@Nonnull final ILayoutExecutionContext aLEC, @Nonnull final String sObjectID)
  {
    return createViewURL (aLEC, sObjectID, (Map <String, String>) null);
  }

  @Nonnull
  public static SimpleURL createViewURL (@Nonnull final ILayoutExecutionContext aLEC,
                                         @Nonnull final String sObjectID,
                                         @Nullable final Map <String, String> aParams)
  {
    return aLEC.getSelfHref ()
               .add (CPageParam.PARAM_ACTION, CPageParam.ACTION_VIEW)
               .add (CPageParam.PARAM_OBJECT, sObjectID)
               .addAll (aParams);
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
  public static SimpleURL createEditURL (@Nonnull final ILayoutExecutionContext aLEC,
                                         @Nonnull final IHasID <String> aCurObject)
  {
    return aLEC.getSelfHref ().add (CPageParam.PARAM_ACTION, CPageParam.ACTION_EDIT).add (CPageParam.PARAM_OBJECT,
                                                                                          aCurObject.getID ());
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
    return new HCA (aEditURL).setTitle (sTitle).addChild (EDefaultIcon.EDIT.getAsNode ());
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
  public static SimpleURL createCopyURL (@Nonnull final ILayoutExecutionContext aLEC,
                                         @Nonnull final IHasID <String> aCurObject)
  {
    return createCopyURL (aLEC, aCurObject, (Map <String, String>) null);
  }

  @Nonnull
  public static SimpleURL createCopyURL (@Nonnull final ILayoutExecutionContext aLEC,
                                         @Nonnull final IHasID <String> aCurObject,
                                         @Nullable final Map <String, String> aParams)
  {
    return aLEC.getSelfHref ()
               .add (CPageParam.PARAM_ACTION, CPageParam.ACTION_COPY)
               .add (CPageParam.PARAM_OBJECT, aCurObject.getID ())
               .addAll (aParams);
  }

  @Nonnull
  public static HCA createCopyLink (@Nonnull final ILayoutExecutionContext aLEC,
                                    @Nonnull final IHasID <String> aCurObject,
                                    @Nullable final String sTitle)
  {
    return createCopyLink (aLEC, aCurObject, sTitle, (Map <String, String>) null);
  }

  @Nonnull
  public static HCA createCopyLink (@Nonnull final ILayoutExecutionContext aLEC,
                                    @Nonnull final IHasID <String> aCurObject,
                                    @Nullable final String sTitle,
                                    @Nullable final Map <String, String> aParams)
  {
    final ISimpleURL aCopyURL = createCopyURL (aLEC, aCurObject, aParams);
    return new HCA (aCopyURL).setTitle (sTitle).addChild (EDefaultIcon.COPY.getAsNode ());
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
  public static SimpleURL createDeleteURL (@Nonnull final ILayoutExecutionContext aLEC,
                                           @Nonnull final IHasID <String> aCurObject)
  {
    return createDeleteURL (aLEC, aCurObject, (Map <String, String>) null);
  }

  @Nonnull
  public static SimpleURL createDeleteURL (@Nonnull final ILayoutExecutionContext aLEC,
                                           @Nonnull final IHasID <String> aCurObject,
                                           @Nullable final Map <String, String> aParams)
  {
    return aLEC.getSelfHref ()
               .add (CPageParam.PARAM_ACTION, CPageParam.ACTION_DELETE)
               .add (CPageParam.PARAM_OBJECT, aCurObject.getID ())
               .addAll (aParams);
  }

  @Nonnull
  public static HCA createDeleteLink (@Nonnull final ILayoutExecutionContext aLEC,
                                      @Nonnull final IHasID <String> aCurObject,
                                      @Nullable final String sTitle)
  {
    return createDeleteLink (aLEC, aCurObject, sTitle, (Map <String, String>) null);
  }

  @Nonnull
  public static HCA createDeleteLink (@Nonnull final ILayoutExecutionContext aLEC,
                                      @Nonnull final IHasID <String> aCurObject,
                                      @Nullable final String sTitle,
                                      @Nullable final Map <String, String> aParams)
  {
    final ISimpleURL aURL = createDeleteURL (aLEC, aCurObject, aParams);
    return new HCA (aURL).setTitle (sTitle).addChild (EDefaultIcon.DELETE.getAsNode ());
  }

  @Nonnull
  public static SimpleURL createUndeleteURL (@Nonnull final ILayoutExecutionContext aLEC,
                                             @Nonnull final IHasID <String> aCurObject)
  {
    return aLEC.getSelfHref ().add (CPageParam.PARAM_ACTION, CPageParam.ACTION_UNDELETE).add (CPageParam.PARAM_OBJECT,
                                                                                              aCurObject.getID ());
  }

  @Nonnull
  public static HCA createNestedCreateLink (@Nonnull final ILayoutExecutionContext aLEC,
                                            @Nonnull final IHasID <String> aCurObject,
                                            @Nullable final String sTitle)
  {
    final ISimpleURL aURL = createCreateURL (aLEC).add (CPageParam.PARAM_OBJECT, aCurObject.getID ());
    return new HCA (aURL).setTitle (sTitle).addChild (EDefaultIcon.NEW.getAsNode ());
  }

  /**
   * @param aWPEC
   *        Web page execution context. May not be <code>null</code>.
   * @return A newly created toolbar. May be overridden to create other types of
   *         toolbars. May not be <code>null</code>.
   */
  @Nonnull
  @OverrideOnDemand
  protected TOOLBAR_TYPE createNewViewToolbar (@Nonnull final WPECTYPE aWPEC)
  {
    return createToolbar (aWPEC);
  }

  /**
   * @param aWPEC
   *        The web page execution context
   * @param aSelectedObject
   *        The selected object
   * @return <code>true</code> to show the view toolbar, <code>false</code> to
   *         draw your own toolbar
   */
  @OverrideOnDemand
  protected boolean showViewToolbar (@Nonnull final WPECTYPE aWPEC, @Nonnull final DATATYPE aSelectedObject)
  {
    return true;
  }

  /**
   * Add additional elements to the view toolbar
   *
   * @param aWPEC
   *        The web page execution context
   * @param aSelectedObject
   *        The selected object
   * @param aToolbar
   *        The toolbar to be modified
   */
  @OverrideOnDemand
  protected void modifyViewToolbar (@Nonnull final WPECTYPE aWPEC,
                                    @Nonnull final DATATYPE aSelectedObject,
                                    @Nonnull final TOOLBAR_TYPE aToolbar)
  {}

  /**
   * Create toolbar for viewing an existing object. Contains the back button and
   * the edit button.
   *
   * @param aWPEC
   *        The web page execution context. Never <code>null</code>.
   * @param bCanGoBack
   *        <code>true</code> to enable back button
   * @param aSelectedObject
   *        The selected object
   * @return Never <code>null</code>.
   */
  @Nonnull
  @OverrideOnDemand
  protected TOOLBAR_TYPE createViewToolbar (@Nonnull final WPECTYPE aWPEC,
                                            final boolean bCanGoBack,
                                            @Nonnull final DATATYPE aSelectedObject)
  {
    final Locale aDisplayLocale = aWPEC.getDisplayLocale ();

    final TOOLBAR_TYPE aToolbar = createNewViewToolbar (aWPEC);
    if (bCanGoBack)
    {
      // Back to list
      aToolbar.addButtonBack (aDisplayLocale);
    }
    if (isActionAllowed (aWPEC, EWebPageFormAction.EDIT, aSelectedObject))
    {
      // Edit object
      aToolbar.addButtonEdit (aDisplayLocale, createEditURL (aWPEC, aSelectedObject));
    }

    // Callback
    modifyViewToolbar (aWPEC, aSelectedObject, aToolbar);
    return aToolbar;
  }

  /**
   * @param aWPEC
   *        Web page execution context. May not be <code>null</code>.
   * @return A newly created toolbar. May be overridden to create other types of
   *         toolbars :). May not be <code>null</code>.
   */
  @Nonnull
  @OverrideOnDemand
  protected TOOLBAR_TYPE createNewEditToolbar (@Nonnull final WPECTYPE aWPEC)
  {
    return createToolbar (aWPEC);
  }

  /**
   * @param aWPEC
   *        The web page execution context
   * @param aSelectedObject
   *        The selected object
   * @return <code>true</code> to show the edit toolbar, <code>false</code> to
   *         draw your own toolbar
   */
  @OverrideOnDemand
  protected boolean showEditToolbar (@Nonnull final WPECTYPE aWPEC, @Nonnull final DATATYPE aSelectedObject)
  {
    return true;
  }

  /**
   * Add additional elements to the edit toolbar
   *
   * @param aWPEC
   *        The web page execution context
   * @param aSelectedObject
   *        The selected object. Never <code>null</code>.
   * @param aToolbar
   *        The toolbar to be modified
   */
  @OverrideOnDemand
  protected void modifyEditToolbar (@Nonnull final WPECTYPE aWPEC,
                                    @Nonnull final DATATYPE aSelectedObject,
                                    @Nonnull final TOOLBAR_TYPE aToolbar)
  {}

  @Nullable
  @OverrideOnDemand
  protected String getEditToolbarSubmitButtonText (@Nonnull final Locale aDisplayLocale)
  {
    return EPhotonCoreText.BUTTON_SAVE.getDisplayText (aDisplayLocale);
  }

  @Nullable
  @OverrideOnDemand
  protected IIcon getEditToolbarSubmitButtonIcon ()
  {
    return EDefaultIcon.SAVE;
  }

  /**
   * Create toolbar for editing an existing object
   *
   * @param aWPEC
   *        The web page execution context. Never <code>null</code>.
   * @param aForm
   *        The handled form. Never <code>null</code>.
   * @param aSelectedObject
   *        The selected object. Never <code>null</code>.
   * @return Never <code>null</code>.
   */
  @Nonnull
  @OverrideOnDemand
  protected TOOLBAR_TYPE createEditToolbar (@Nonnull final WPECTYPE aWPEC,
                                            @Nonnull final FORM_TYPE aForm,
                                            @Nonnull final DATATYPE aSelectedObject)
  {
    final Locale aDisplayLocale = aWPEC.getDisplayLocale ();

    final TOOLBAR_TYPE aToolbar = createNewEditToolbar (aWPEC);
    aToolbar.addHiddenField (CPageParam.PARAM_ACTION, CPageParam.ACTION_EDIT);
    aToolbar.addHiddenField (CPageParam.PARAM_OBJECT, aSelectedObject.getID ());
    aToolbar.addHiddenField (CPageParam.PARAM_SUBACTION, CPageParam.ACTION_SAVE);
    // Save button
    aToolbar.addSubmitButton (getEditToolbarSubmitButtonText (aDisplayLocale), getEditToolbarSubmitButtonIcon ());
    // Cancel button
    aToolbar.addButtonCancel (aDisplayLocale);

    // Callback
    modifyEditToolbar (aWPEC, aSelectedObject, aToolbar);
    return aToolbar;
  }

  /**
   * @param aWPEC
   *        The web page execution context
   * @param aSelectedObject
   *        The selected object. May be <code>null</code>.
   * @return <code>true</code> to show the create toolbar, <code>false</code> to
   *         draw your own toolbar
   */
  @OverrideOnDemand
  protected boolean showCreateToolbar (@Nonnull final WPECTYPE aWPEC, @Nullable final DATATYPE aSelectedObject)
  {
    return true;
  }

  /**
   * @param aWPEC
   *        Web page execution context. May not be <code>null</code>.
   * @return A newly created toolbar. May be overridden to create other types of
   *         toolbars :). May not be <code>null</code>.
   */
  @Nonnull
  @OverrideOnDemand
  protected TOOLBAR_TYPE createNewCreateToolbar (@Nonnull final WPECTYPE aWPEC)
  {
    return createToolbar (aWPEC);
  }

  /**
   * Add additional elements to the create toolbar
   *
   * @param aWPEC
   *        The web page execution context
   * @param aToolbar
   *        The toolbar to be modified
   */
  @OverrideOnDemand
  protected void modifyCreateToolbar (@Nonnull final WPECTYPE aWPEC, @Nonnull final TOOLBAR_TYPE aToolbar)
  {}

  @Nullable
  @OverrideOnDemand
  protected String getCreateToolbarSubmitButtonText (@Nonnull final Locale aDisplayLocale)
  {
    return EPhotonCoreText.BUTTON_SAVE.getDisplayText (aDisplayLocale);
  }

  @Nullable
  @OverrideOnDemand
  protected IIcon getCreateToolbarSubmitButtonIcon ()
  {
    return EDefaultIcon.SAVE;
  }

  /**
   * Create toolbar for creating a new object
   *
   * @param aWPEC
   *        The web page execution context
   * @param aForm
   *        The handled form. Never <code>null</code>.
   * @param aSelectedObject
   *        Optional selected object. May be <code>null</code>.
   * @return Never <code>null</code>.
   */
  @Nonnull
  @OverrideOnDemand
  protected TOOLBAR_TYPE createCreateToolbar (@Nonnull final WPECTYPE aWPEC,
                                              @Nonnull final FORM_TYPE aForm,
                                              @Nullable final DATATYPE aSelectedObject)
  {
    final Locale aDisplayLocale = aWPEC.getDisplayLocale ();

    final TOOLBAR_TYPE aToolbar = createNewCreateToolbar (aWPEC);
    aToolbar.addHiddenField (CPageParam.PARAM_ACTION, CPageParam.ACTION_CREATE);
    if (aSelectedObject != null)
      aToolbar.addHiddenField (CPageParam.PARAM_OBJECT, aSelectedObject.getID ());
    aToolbar.addHiddenField (CPageParam.PARAM_SUBACTION, CPageParam.ACTION_SAVE);
    // Save button
    aToolbar.addSubmitButton (getCreateToolbarSubmitButtonText (aDisplayLocale), getCreateToolbarSubmitButtonIcon ());
    // Cancel button
    aToolbar.addButtonCancel (aDisplayLocale);

    // Callback
    modifyCreateToolbar (aWPEC, aToolbar);
    return aToolbar;
  }

  /**
   * Check if passed object allows for the specified action.
   *
   * @param aWPEC
   *        The web page execution context. Never <code>null</code>.
   * @param eFormAction
   *        The form action that is to be checked. Never <code>null</code> and
   *        never {@link EWebPageFormAction#SHOW_LIST}.
   * @param aSelectedObject
   *        The currently selected object. May be <code>null</code>.
   * @return <code>true</code> if the action is allowed, <code>false</code> if
   *         not
   */
  @OverrideOnDemand
  protected boolean isActionAllowed (@Nonnull final WPECTYPE aWPEC,
                                     @Nonnull final EWebPageFormAction eFormAction,
                                     @Nullable final DATATYPE aSelectedObject)
  {
    return true;
  }

  /**
   * Get the selected object we're operating on
   *
   * @param aWPEC
   *        The web page execution context. Never <code>null</code>.
   * @param sID
   *        selected object ID
   * @return <code>null</code> if no such object exists
   */
  @Nullable
  protected abstract DATATYPE getSelectedObject (@Nonnull WPECTYPE aWPEC, @Nullable String sID);

  /**
   * @return <code>true</code> if object locking should be active,
   *         <code>false</code> if not. By default (for backwards compatibility
   *         reasons) locking is disabled.
   */
  @OverrideOnDemand
  protected boolean isObjectLockingEnabled ()
  {
    return false;
  }

  /**
   * Check if locking should be performed on the current request or not.
   * Override with care!
   *
   * @param aWPEC
   *        The current web page execution context. Never <code>null</code>.
   * @param aSelectedObject
   *        The currently selected object. Never <code>null</code>.
   * @param eFormAction
   *        The current form action. Never <code>null</code>.
   * @return <code>true</code> if locking for the current request should be
   *         performed, <code>false</code> otherwise.
   */
  @OverrideOnDemand
  @OverridingMethodsMustInvokeSuper
  protected boolean performLocking (@Nonnull final WPECTYPE aWPEC,
                                    @Nonnull final DATATYPE aSelectedObject,
                                    @Nonnull final EWebPageFormAction eFormAction)
  {
    // Lock EDIT and DELETE if an object is present
    // Also lock custom actions if an object is selected
    return eFormAction.isModifying () || eFormAction.isCustom ();
  }

  /**
   * This method is called before the main processing starts. It can e.g. be
   * used to try to lock the specified object. When overriding the method make
   * sure to emit all error messages on your own, when e.g. an object is locked.
   * If {@link EContinue#BREAK} is returned, the list of objects is shown by
   * default.<br>
   * If locking is enabled, try to lock the specified object. When overriding
   * the method make sure to emit all error messages on your own, when e.g. an
   * object is locked.
   *
   * @param aWPEC
   *        The current web page execution context. Never <code>null</code>.
   * @param aSelectedObject
   *        The currently selected object. May be <code>null</code> if no object
   *        is selected.
   * @param eFormAction
   *        The current form action. Never <code>null</code>.
   * @return {@link EContinue#CONTINUE} if normal execution can continue or
   *         {@link EContinue#BREAK} if execution cannot continue (e.g. because
   *         object is already locked).
   */
  @Nonnull
  @OverrideOnDemand
  @OverridingMethodsMustInvokeSuper
  protected EContinue beforeProcessing (@Nonnull final WPECTYPE aWPEC,
                                        @Nullable final DATATYPE aSelectedObject,
                                        @Nonnull final EWebPageFormAction eFormAction)
  {
    if (isObjectLockingEnabled ())
    {
      final ILockManager <String> aOLM = PhotonSecurityManager.getLockMgr ();
      if (aSelectedObject != null && performLocking (aWPEC, aSelectedObject, eFormAction))
      {
        // Try to lock object
        final String sObjectID = aSelectedObject.getID ();
        final LockResult <String> aLockResult = aOLM.lockObjectAndUnlockAllOthers (sObjectID);
        if (aLockResult.isNotLocked ())
        {
          // Failed to lock object
          final Locale aDisplayLocale = aWPEC.getDisplayLocale ();
          final HCNodeList aNodeList = aWPEC.getNodeList ();

          final String sLockUserID = aOLM.getLockUserID (sObjectID);
          final IUser aLockUser = PhotonSecurityManager.getUserMgr ().getUserOfID (sLockUserID);
          final String sObjectName = getObjectDisplayName (aWPEC, aSelectedObject);
          final String sDisplayObjectName = StringHelper.hasText (sObjectName) ? " '" + sObjectName + "'" : "";
          final String sDisplayUserName = aLockUser != null ? "'" + aLockUser.getDisplayName () + "'"
                                                            : EWebPageText.LOCKING_OTHER_USER.getDisplayText (aDisplayLocale);
          aNodeList.addChild (createErrorBox (aWPEC,
                                              EWebPageText.LOCKING_FAILED.getDisplayTextWithArgs (aDisplayLocale,
                                                                                                  sDisplayObjectName,
                                                                                                  sDisplayUserName)));
          return EContinue.BREAK;
        }
      }
      else
      {
        // No lock action required - unlock all
        aOLM.unlockAllObjectsOfCurrentUser ();
      }
    }
    return EContinue.CONTINUE;
  }

  /**
   * This method is called after the processing as the last action.
   *
   * @param aWPEC
   *        The current web page execution context. Never <code>null</code>.
   * @param aSelectedObject
   *        The currently selected object. May be <code>null</code> if no object
   *        is selected.
   * @param eFormAction
   *        The current form action. Never <code>null</code>.
   */
  @OverrideOnDemand
  protected void afterProcessing (@Nonnull final WPECTYPE aWPEC,
                                  @Nullable final DATATYPE aSelectedObject,
                                  @Nonnull final EWebPageFormAction eFormAction)
  {}

  /**
   * @param aWPEC
   *        The web page execution context. Never <code>null</code>.
   * @param aSelectedObject
   *        The currently selected object. Never <code>null</code>.
   */
  protected abstract void showSelectedObject (@Nonnull WPECTYPE aWPEC, @Nonnull DATATYPE aSelectedObject);

  protected final void handleViewObject (@Nonnull final WPECTYPE aWPEC, @Nonnull final DATATYPE aSelectedObject)
  {
    // Valid object found - show details
    showSelectedObject (aWPEC, aSelectedObject);

    if (showViewToolbar (aWPEC, aSelectedObject))
    {
      // Toolbar on bottom
      aWPEC.getNodeList ().addChild (createViewToolbar (aWPEC, true, aSelectedObject));
    }
  }

  /**
   * @param aWPEC
   *        Web page execution context. Never <code>null</code>.
   * @param aSelectedObject
   *        The currently selected object. May be <code>null</code> when
   *        creating a new object
   * @param aFormErrors
   *        Object for storing the validation errors. Never <code>null</code>.
   * @param eFormAction
   *        The form action mode. Either create, copy or edit.
   */
  protected abstract void validateAndSaveInputParameters (@Nonnull WPECTYPE aWPEC,
                                                          @Nullable DATATYPE aSelectedObject,
                                                          @Nonnull FormErrors aFormErrors,
                                                          @Nonnull EWebPageFormAction eFormAction);

  /**
   * Callback method invoked when the input form finished successfully.
   *
   * @param aWPEC
   *        Web page execution context. Never <code>null</code>.
   */
  @OverrideOnDemand
  protected void onInputFormFinishedSuccess (@Nonnull final WPECTYPE aWPEC)
  {}

  /**
   * Callback method invoked when the input form contains errors.
   *
   * @param aWPEC
   *        Web page execution context. Never <code>null</code>.
   * @param aFormErrors
   *        Object for storing the validation errors. Never <code>null</code>.
   */
  @OverrideOnDemand
  protected void onInputFormError (@Nonnull final WPECTYPE aWPEC, @Nonnull final FormErrors aFormErrors)
  {}

  /**
   * Add additional form IDs (e.g. client and accounting area). This method is
   * called before
   * {@link #showInputForm(IWebPageExecutionContext, IHasID, IHCForm, EWebPageFormAction, FormErrors)}
   * is called.
   *
   * @param aWPEC
   *        Web page execution context
   * @param aForm
   *        the form to add the elements to
   */
  @OverrideOnDemand
  protected void modifyFormBeforeShowInputForm (@Nonnull final WPECTYPE aWPEC, @Nonnull final FORM_TYPE aForm)
  {}

  /**
   * Show the input form for a new or existing object.
   *
   * @param aWPEC
   *        The web page execution context. Never <code>null</code>.
   * @param aSelectedObject
   *        The currently selected object. May be <code>null</code> for newly
   *        created objects.
   * @param aForm
   *        The parent form. Use this as parent and not the node list from the
   *        web page execution context! Never <code>null</code>.
   * @param eFormAction
   *        The form action used. Either create, copy or edit.
   * @param aFormErrors
   *        Previous errors from validation. Never <code>null</code> but maybe
   *        empty.
   */
  protected abstract void showInputForm (@Nonnull WPECTYPE aWPEC,
                                         @Nullable DATATYPE aSelectedObject,
                                         @Nonnull FORM_TYPE aForm,
                                         @Nonnull EWebPageFormAction eFormAction,
                                         @Nonnull FormErrors aFormErrors);

  /**
   * Add additional form IDs (e.g. client and accounting area). This method is
   * called after
   * {@link #showInputForm(IWebPageExecutionContext, IHasID, IHCForm, EWebPageFormAction, FormErrors)}
   * was called but before the toolbars are added.
   *
   * @param aWPEC
   *        Web page execution context
   * @param aForm
   *        the form to add the elements to
   */
  @OverrideOnDemand
  protected void modifyFormAfterShowInputForm (@Nonnull final WPECTYPE aWPEC, @Nonnull final FORM_TYPE aForm)
  {}

  /**
   * Show the delete query.
   *
   * @param aWPEC
   *        The web page execution context
   * @param aForm
   *        The handled form. Never <code>null</code>.
   * @param aSelectedObject
   *        The object to be deleted. Never <code>null</code>.
   */
  @OverrideOnDemand
  protected void showDeleteQuery (@Nonnull final WPECTYPE aWPEC,
                                  @Nonnull final FORM_TYPE aForm,
                                  @Nonnull final DATATYPE aSelectedObject)
  {}

  /**
   * Perform object delete
   *
   * @param aWPEC
   *        The web page execution context
   * @param aSelectedObject
   *        The object to be deleted. Never <code>null</code>.
   */
  @OverrideOnDemand
  protected void performDelete (@Nonnull final WPECTYPE aWPEC, @Nonnull final DATATYPE aSelectedObject)
  {}

  /**
   * @param aWPEC
   *        The web page execution context
   * @param aSelectedObject
   *        The selected object. Never <code>null</code>.
   * @return <code>true</code> to show the delete toolbar, <code>false</code> to
   *         draw your own toolbar
   */
  @OverrideOnDemand
  protected boolean showDeleteToolbar (@Nonnull final WPECTYPE aWPEC, @Nonnull final DATATYPE aSelectedObject)
  {
    return true;
  }

  /**
   * @param aWPEC
   *        Web page execution context. May not be <code>null</code>.
   * @return A newly created toolbar. May be overridden to create other types of
   *         toolbars :). May not be <code>null</code>.
   */
  @Nonnull
  @OverrideOnDemand
  protected TOOLBAR_TYPE createNewDeleteToolbar (@Nonnull final WPECTYPE aWPEC)
  {
    return createToolbar (aWPEC);
  }

  /**
   * Add additional elements to the delete toolbar
   *
   * @param aWPEC
   *        The web page execution context
   * @param aToolbar
   *        The toolbar to be modified
   */
  @OverrideOnDemand
  protected void modifyDeleteToolbar (@Nonnull final WPECTYPE aWPEC, @Nonnull final TOOLBAR_TYPE aToolbar)
  {}

  @Nullable
  @OverrideOnDemand
  protected String getDeleteToolbarSubmitButtonText (@Nonnull final Locale aDisplayLocale)
  {
    return EPhotonCoreText.BUTTON_YES.getDisplayText (aDisplayLocale);
  }

  @Nullable
  @OverrideOnDemand
  protected IIcon getDeleteToolbarSubmitButtonIcon ()
  {
    return EDefaultIcon.YES;
  }

  /**
   * Create toolbar for deleting an existing object
   *
   * @param aWPEC
   *        The web page execution context
   * @param aForm
   *        The handled form. Never <code>null</code>.
   * @param aSelectedObject
   *        Selected object. Never <code>null</code>.
   * @return Never <code>null</code>.
   */
  @Nonnull
  @OverrideOnDemand
  protected TOOLBAR_TYPE createDeleteToolbar (@Nonnull final WPECTYPE aWPEC,
                                              @Nonnull final FORM_TYPE aForm,
                                              @Nonnull final DATATYPE aSelectedObject)
  {
    final Locale aDisplayLocale = aWPEC.getDisplayLocale ();

    final TOOLBAR_TYPE aToolbar = createNewDeleteToolbar (aWPEC);
    aToolbar.addHiddenField (CPageParam.PARAM_ACTION, CPageParam.ACTION_DELETE);
    aToolbar.addHiddenField (CPageParam.PARAM_SUBACTION, CPageParam.ACTION_SAVE);
    aToolbar.addHiddenField (CPageParam.PARAM_OBJECT, aSelectedObject.getID ());
    // Yes button
    aToolbar.addSubmitButton (getDeleteToolbarSubmitButtonText (aDisplayLocale), getDeleteToolbarSubmitButtonIcon ());
    // No button
    aToolbar.addButtonNo (aDisplayLocale);

    // Callback
    modifyDeleteToolbar (aWPEC, aToolbar);
    return aToolbar;
  }

  /**
   * Perform object undelete
   *
   * @param aWPEC
   *        The web page execution context
   * @param aSelectedObject
   *        The object to be undeleted. Never <code>null</code>.
   * @return <code>true</code> to show the list afterwards. <code>false</code>
   *         if the object list should not be shown.
   */
  @OverrideOnDemand
  protected boolean handleUndeleteAction (@Nonnull final WPECTYPE aWPEC, @Nonnull final DATATYPE aSelectedObject)
  {
    return true;
  }

  /**
   * Handle some other custom action
   *
   * @param aWPEC
   *        The web page execution context
   * @param aSelectedObject
   *        The selected object. May be <code>null</code>.
   * @return <code>true</code> to show the list afterwards. <code>false</code>
   *         if the object list should not be shown.
   */
  @OverrideOnDemand
  protected boolean handleCustomActions (@Nonnull final WPECTYPE aWPEC, @Nullable final DATATYPE aSelectedObject)
  {
    return true;
  }

  /**
   * @param aWPEC
   *        The web page execution context. Never <code>null</code>.
   */
  protected abstract void showListOfExistingObjects (@Nonnull WPECTYPE aWPEC);

  @Nonnull
  @ReturnsMutableCopy
  private static JSAssocArray _getAsAssocArray (final FormState aFormState)
  {
    final JSAssocArray ret = new JSAssocArray ();
    for (final Map.Entry <String, Object> aEntry : aFormState.getAllAttributes ().getAllAttributes ().entrySet ())
    {
      final String sKey = aEntry.getKey ();
      final Object aValue = aEntry.getValue ();
      if (aValue instanceof String)
        ret.add (sKey, (String) aValue);
      else
        if (aValue instanceof String [])
        {
          final JSArray aArray = new JSArray ();
          for (final String sElement : (String []) aValue)
            aArray.add (sElement);
          ret.add (sKey, aArray);
        }
      // else e.g. fileitem -> ignore
    }
    return ret;
  }

  @Override
  protected final void fillContent (@Nonnull final WPECTYPE aWPEC)
  {
    final HCNodeList aNodeList = aWPEC.getNodeList ();

    // Get the selected object
    final String sSelectedObjectID = getSelectedObjectID (aWPEC);
    final DATATYPE aSelectedObject = getSelectedObject (aWPEC, sSelectedObjectID);

    final String sAction = aWPEC.getAction ();

    // Default value is show list
    EWebPageFormAction eFormAction = EWebPageFormAction.SHOW_LIST;
    if (StringHelper.hasText (sAction))
    {
      if (CPageParam.ACTION_VIEW.equals (sAction))
      {
        if (aSelectedObject != null)
          eFormAction = EWebPageFormAction.VIEW;
      }
      else
        if (CPageParam.ACTION_CREATE.equals (sAction))
          eFormAction = EWebPageFormAction.CREATE;
        else
          if (CPageParam.ACTION_EDIT.equals (sAction))
          {
            if (aSelectedObject != null)
              eFormAction = EWebPageFormAction.EDIT;
          }
          else
            if (CPageParam.ACTION_COPY.equals (sAction))
            {
              if (aSelectedObject != null)
                eFormAction = EWebPageFormAction.COPY;
            }
            else
              if (CPageParam.ACTION_DELETE.equals (sAction))
              {
                if (aSelectedObject != null)
                  eFormAction = EWebPageFormAction.DELETE;
              }
              else
                if (CPageParam.ACTION_UNDELETE.equals (sAction))
                {
                  if (aSelectedObject != null)
                    eFormAction = EWebPageFormAction.UNDELETE;
                }
                else
                {
                  // Non standard action - must be custom
                  eFormAction = EWebPageFormAction.CUSTOM;
                }
    }

    if (!eFormAction.isShowList ())
    {
      // An action was specified - check if it is allowed at all
      if (!isActionAllowed (aWPEC, eFormAction, aSelectedObject))
      {
        // Default back to custom
        s_aLogger.info ("Action " +
                        eFormAction +
                        " is not allowed on object " +
                        (aSelectedObject == null ? "null" : aSelectedObject.getID ()));
        eFormAction = EWebPageFormAction.CUSTOM;
      }
    }

    // Try to lock object
    boolean bShowList = true;
    if (beforeProcessing (aWPEC, aSelectedObject, eFormAction).isContinue ())
    {
      switch (eFormAction)
      {
        case SHOW_LIST:
        {
          // Handled down there
          break;
        }
        case VIEW:
        {
          // Valid object found - show details
          handleViewObject (aWPEC, aSelectedObject);

          // Don't show object list
          bShowList = false;
          break;
        }
        case CREATE:
        case EDIT:
        case COPY:
        {
          // Create or edit a client
          final FormErrors aFormErrors = new FormErrors ();
          boolean bShowInputForm = true;
          final boolean bIsFormSubmitted = aWPEC.hasSubAction (CPageParam.ACTION_SAVE);

          if (bIsFormSubmitted)
          {
            // Check if the nonce matches
            if (checkCSRFNonce (aWPEC).isContinue ())
            {
              // try to save, only if nonce is as expected
              validateAndSaveInputParameters (aWPEC, aSelectedObject, aFormErrors, eFormAction);
              if (aFormErrors.isEmpty ())
              {
                // Save successful
                bShowInputForm = false;

                // Remove an optionally stored state
                final String sFlowID = aWPEC.getAttributeAsString (FIELD_FLOW_ID);
                FormStateManager.getInstance ().deleteFormState (sFlowID);

                // Callback method
                onInputFormFinishedSuccess (aWPEC);
              }
              else
              {
                // Show: changes could not be saved...
                aNodeList.addChild (createIncorrectInputBox (aWPEC));

                // Callback method
                onInputFormError (aWPEC, aFormErrors);
              }
            }
          }

          if (bShowInputForm)
          {
            // Show the input form. Either for the first time or because of form
            // errors a n-th time
            bShowList = false;
            final FORM_TYPE aForm = isFileUploadForm (aWPEC) ? createFormFileUploadSelf (aWPEC)
                                                             : createFormSelf (aWPEC);
            aNodeList.addChild (aForm);

            // Set unique ID
            aForm.setID (FORM_ID_INPUT);

            // Add the nonce for CSRF check
            aForm.addChild (createCSRFNonceField ());

            // The unique form ID, that allows to identify on "transaction"
            // -> Used only for "form state remembering"
            // Use a RequestField here, to create the flow ID only once per
            // "new page" view. For following calls, the flow ID should be
            // re-used!
            aForm.addChild (new HCHiddenField (new RequestField (FIELD_FLOW_ID, GlobalIDFactory.getNewStringID ())));

            // Callback method
            modifyFormBeforeShowInputForm (aWPEC, aForm);

            // Is there as saved state to use?
            final String sRestoreFlowID = aWPEC.getAttributeAsString (FIELD_RESTORE_FLOW_ID);
            if (sRestoreFlowID != null)
            {
              final FormState aSavedState = FormStateManager.getInstance ().getFormStateOfID (sRestoreFlowID);
              if (aSavedState != null)
              {
                // Restore all form values
                aForm.addChild (new HCScriptInlineOnDocumentReady (JSFormHelper.setAllFormValues (FORM_ID_INPUT,
                                                                                                  _getAsAssocArray (aSavedState))));
              }
            }

            // Show the main input form
            showInputForm (aWPEC, aSelectedObject, aForm, eFormAction, aFormErrors);

            // Callback method
            modifyFormAfterShowInputForm (aWPEC, aForm);

            // Toolbar on bottom
            if (eFormAction == EWebPageFormAction.EDIT)
            {
              if (showEditToolbar (aWPEC, aSelectedObject))
                aForm.addChild (createEditToolbar (aWPEC, aForm, aSelectedObject));
            }
            else
            {
              if (showCreateToolbar (aWPEC, aSelectedObject))
                aForm.addChild (createCreateToolbar (aWPEC, aForm, aSelectedObject));
            }
          }
          break;
        }
        case DELETE:
        {
          final boolean bIsFormSubmitted = aWPEC.hasSubAction (CPageParam.ACTION_SAVE);

          if (bIsFormSubmitted)
          {
            // Check if the nonce matches
            if (checkCSRFNonce (aWPEC).isContinue ())
            {
              performDelete (aWPEC, aSelectedObject);
            }

            bShowList = true;
          }
          else
          {
            final FORM_TYPE aForm = createFormSelf (aWPEC);
            aNodeList.addChild (aForm);

            // Set unique ID
            aForm.setID (FORM_ID_DELETE);

            // Add the nonce for CSRF check
            aForm.addChild (createCSRFNonceField ());

            showDeleteQuery (aWPEC, aForm, aSelectedObject);
            if (showDeleteToolbar (aWPEC, aSelectedObject))
              aForm.addChild (createDeleteToolbar (aWPEC, aForm, aSelectedObject));
            bShowList = false;
          }

          break;
        }
        case UNDELETE:
        {
          bShowList = handleUndeleteAction (aWPEC, aSelectedObject);
          break;
        }
        case CUSTOM:
        {
          // Other proprietary actions
          bShowList = handleCustomActions (aWPEC, aSelectedObject);
          break;
        }
      }
    }

    if (bShowList)
    {
      showListOfExistingObjects (aWPEC);
    }

    // Call after everything
    afterProcessing (aWPEC, aSelectedObject, eFormAction);
  }
}
