/**
 * Copyright (C) 2014-2016 Philip Helger (www.helger.com)
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

import com.helger.commons.ValueEnforcer;
import com.helger.commons.annotation.Nonempty;
import com.helger.commons.annotation.OverrideOnDemand;
import com.helger.commons.id.IHasID;
import com.helger.commons.name.IHasDisplayName;
import com.helger.commons.state.EContinue;
import com.helger.commons.string.StringHelper;
import com.helger.commons.text.IMultilingualText;
import com.helger.commons.text.display.IHasDisplayText;
import com.helger.commons.url.ISimpleURL;
import com.helger.commons.url.SimpleURL;
import com.helger.html.hc.html.forms.IHCForm;
import com.helger.html.hc.html.textlevel.HCA;
import com.helger.html.hc.impl.HCNodeList;
import com.helger.photon.core.EPhotonCoreText;
import com.helger.photon.core.app.context.ILayoutExecutionContext;
import com.helger.photon.core.form.FormErrorList;
import com.helger.photon.security.lock.ILockManager;
import com.helger.photon.security.lock.LockResult;
import com.helger.photon.security.mgr.PhotonSecurityManager;
import com.helger.photon.security.user.IUser;
import com.helger.photon.uicore.css.CPageParam;
import com.helger.photon.uicore.html.toolbar.IButtonToolbar;
import com.helger.photon.uicore.icon.EDefaultIcon;
import com.helger.photon.uicore.icon.IIcon;

/**
 * Abstract base class for a web page that has the common form handling, with a
 * details view and an edit binding. Use this page when showing and editing a
 * single object like global settings.
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
public abstract class AbstractWebPageSimpleForm <DATATYPE extends IHasID <String>, WPECTYPE extends IWebPageExecutionContext, FORM_TYPE extends IHCForm <FORM_TYPE>, TOOLBAR_TYPE extends IButtonToolbar <TOOLBAR_TYPE>>
                                                extends AbstractWebPage <WPECTYPE>
{
  public static final String FORM_ID_INPUT = "inputform";

  private static final Logger s_aLogger = LoggerFactory.getLogger (AbstractWebPageSimpleForm.class);

  private final IWebPageFormUIHandler <FORM_TYPE, TOOLBAR_TYPE> m_aUIHandler;

  public AbstractWebPageSimpleForm (@Nonnull @Nonempty final String sID,
                                    @Nonnull final IMultilingualText aName,
                                    @Nullable final IMultilingualText aDescription,
                                    @Nonnull final IWebPageFormUIHandler <FORM_TYPE, TOOLBAR_TYPE> aUIHandler)
  {
    super (sID, aName, aDescription);
    m_aUIHandler = ValueEnforcer.notNull (aUIHandler, "UIHandler");
  }

  @Nonnull
  protected final IWebPageFormUIHandler <FORM_TYPE, TOOLBAR_TYPE> getUIHandler ()
  {
    return m_aUIHandler;
  }

  /**
   * Get the display name of the passed object.
   *
   * @param aWPEC
   *        The current web page execution context. Never <code>null</code>.
   * @param aObject
   *        The object to get the display name from. Never <code>null</code>.
   * @return <code>null</code> to indicate that no display name is available
   */
  @Nullable
  @OverrideOnDemand
  protected String getObjectDisplayName (@Nonnull final WPECTYPE aWPEC, @Nonnull final DATATYPE aObject)
  {
    return null;
  }

  /**
   * @param aWPEC
   *        The current web page execution context. Never <code>null</code>.
   * @return <code>true</code> if the form for
   *         {@link #showInputForm(IWebPageExecutionContext, IHasID, IHCForm, EWebPageSimpleFormAction, FormErrorList)}
   *         should be a file-upload form, <code>false</code> if a regular form
   *         is sufficient.
   */
  @OverrideOnDemand
  protected boolean isFileUploadForm (@Nonnull final WPECTYPE aWPEC)
  {
    return false;
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
               .add (CPageParam.PARAM_ACTION, CPageParam.ACTION_VIEW)
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
    return aLEC.getSelfHref ().add (CPageParam.PARAM_ACTION, CPageParam.ACTION_VIEW).add (CPageParam.PARAM_OBJECT,
                                                                                          sObjectID);
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
    return getUIHandler ().createToolbar (aWPEC);
  }

  /**
   * @param aWPEC
   *        The web page execution context
   * @param aObject
   *        The object
   * @return <code>true</code> to show the view toolbar, <code>false</code> to
   *         draw your own toolbar
   */
  @OverrideOnDemand
  protected boolean showViewToolbar (@Nonnull final WPECTYPE aWPEC, @Nonnull final DATATYPE aObject)
  {
    return true;
  }

  /**
   * Add additional elements to the view toolbar
   *
   * @param aWPEC
   *        The web page execution context
   * @param aObject
   *        The object
   * @param aToolbar
   *        The toolbar to be modified
   */
  @OverrideOnDemand
  protected void modifyViewToolbar (@Nonnull final WPECTYPE aWPEC,
                                    @Nonnull final DATATYPE aObject,
                                    @Nonnull final TOOLBAR_TYPE aToolbar)
  {}

  /**
   * Create toolbar for viewing an existing object. Contains the back button and
   * the edit button.
   *
   * @param aWPEC
   *        The web page execution context. Never <code>null</code>.
   * @param aObject
   *        The object
   * @return Never <code>null</code>.
   */
  @Nonnull
  @OverrideOnDemand
  protected TOOLBAR_TYPE createViewToolbar (@Nonnull final WPECTYPE aWPEC, @Nonnull final DATATYPE aObject)
  {
    final Locale aDisplayLocale = aWPEC.getDisplayLocale ();

    final TOOLBAR_TYPE aToolbar = createNewViewToolbar (aWPEC);
    if (isActionAllowed (aWPEC, EWebPageSimpleFormAction.EDIT, aObject))
    {
      // Edit object
      aToolbar.addButtonEdit (aDisplayLocale, createEditURL (aWPEC, aObject));
    }

    // Callback
    modifyViewToolbar (aWPEC, aObject, aToolbar);
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
    return getUIHandler ().createToolbar (aWPEC);
  }

  /**
   * @param aWPEC
   *        The web page execution context
   * @param aObject
   *        The object
   * @return <code>true</code> to show the edit toolbar, <code>false</code> to
   *         draw your own toolbar
   */
  @OverrideOnDemand
  protected boolean showEditToolbar (@Nonnull final WPECTYPE aWPEC, @Nonnull final DATATYPE aObject)
  {
    return true;
  }

  /**
   * Add additional elements to the edit toolbar
   *
   * @param aWPEC
   *        The web page execution context
   * @param aObject
   *        The object. Never <code>null</code>.
   * @param aToolbar
   *        The toolbar to be modified
   */
  @OverrideOnDemand
  protected void modifyEditToolbar (@Nonnull final WPECTYPE aWPEC,
                                    @Nonnull final DATATYPE aObject,
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
   * @param aObject
   *        The object. Never <code>null</code>.
   * @return Never <code>null</code>.
   */
  @Nonnull
  @OverrideOnDemand
  protected TOOLBAR_TYPE createEditToolbar (@Nonnull final WPECTYPE aWPEC,
                                            @Nonnull final FORM_TYPE aForm,
                                            @Nonnull final DATATYPE aObject)
  {
    final Locale aDisplayLocale = aWPEC.getDisplayLocale ();

    final TOOLBAR_TYPE aToolbar = createNewEditToolbar (aWPEC);
    aToolbar.addHiddenField (CPageParam.PARAM_ACTION, CPageParam.ACTION_EDIT);
    aToolbar.addHiddenField (CPageParam.PARAM_OBJECT, aObject.getID ());
    aToolbar.addHiddenField (CPageParam.PARAM_SUBACTION, CPageParam.ACTION_SAVE);
    // Save button
    aToolbar.addSubmitButton (getEditToolbarSubmitButtonText (aDisplayLocale), getEditToolbarSubmitButtonIcon ());
    // Cancel button
    aToolbar.addButtonCancel (aDisplayLocale);

    // Callback
    modifyEditToolbar (aWPEC, aObject, aToolbar);
    return aToolbar;
  }

  /**
   * Check if passed object allows for the specified action.
   *
   * @param aWPEC
   *        The web page execution context. Never <code>null</code>.
   * @param eSimpleFormAction
   *        The form action that is to be checked. Never <code>null</code>.
   * @param aObject
   *        The currently object. Never <code>null</code>.
   * @return <code>true</code> if the action is allowed, <code>false</code> if
   *         not
   */
  @OverrideOnDemand
  protected boolean isActionAllowed (@Nonnull final WPECTYPE aWPEC,
                                     @Nonnull final EWebPageSimpleFormAction eSimpleFormAction,
                                     @Nonnull final DATATYPE aObject)
  {
    return true;
  }

  /**
   * Get the object we're operating on
   *
   * @param aWPEC
   *        The web page execution context. Never <code>null</code>.
   * @return Must not be <code>null</code>
   */
  @Nonnull
  protected abstract DATATYPE getObject (@Nonnull WPECTYPE aWPEC);

  /**
   * @return <code>true</code> if object locking should be active,
   *         <code>false</code> if not. By default locking is enabled.
   */
  @OverrideOnDemand
  protected boolean isObjectLockingEnabled ()
  {
    return true;
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
   * @param aObject
   *        The currently object. May be <code>null</code> if no object is
   *        selected.
   * @param eSimpleFormAction
   *        The current form action. Never <code>null</code>.
   * @return {@link EContinue#CONTINUE} if normal execution can continue or
   *         {@link EContinue#BREAK} if execution cannot continue (e.g. because
   *         object is already locked).
   */
  @Nonnull
  @OverrideOnDemand
  @OverridingMethodsMustInvokeSuper
  protected EContinue beforeProcessing (@Nonnull final WPECTYPE aWPEC,
                                        @Nonnull final DATATYPE aObject,
                                        @Nonnull final EWebPageSimpleFormAction eSimpleFormAction)
  {
    if (isObjectLockingEnabled ())
    {
      final ILockManager <String> aOLM = PhotonSecurityManager.getLockMgr ();
      // Lock EDIT if an object is present
      if (eSimpleFormAction.isModifying () && aObject != null)
      {
        // Try to lock object
        final String sObjectID = aObject.getID ();
        final LockResult <String> aLockResult = aOLM.lockObjectAndUnlockAllOthers (sObjectID);
        if (aLockResult.isNotLocked ())
        {
          // Failed to lock object
          final Locale aDisplayLocale = aWPEC.getDisplayLocale ();
          final HCNodeList aNodeList = aWPEC.getNodeList ();

          final String sLockUserID = aOLM.getLockUserID (sObjectID);
          final IUser aLockUser = PhotonSecurityManager.getUserMgr ().getUserOfID (sLockUserID);
          final String sObjectName = getObjectDisplayName (aWPEC, aObject);
          final String sDisplayObjectName = StringHelper.hasText (sObjectName) ? " '" + sObjectName + "'" : "";
          final String sDisplayUserName = aLockUser != null ? "'" + aLockUser.getDisplayName () + "'"
                                                            : EWebPageText.LOCKING_OTHER_USER.getDisplayText (aDisplayLocale);
          aNodeList.addChild (getUIHandler ().createErrorBox (aWPEC,
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
   * @param aObject
   *        The currently object. May be <code>null</code> if no object is
   *        selected.
   * @param eSimpleFormAction
   *        The current form action. May be <code>null</code> if a non-standard
   *        action is handled.
   */
  @OverrideOnDemand
  protected void afterProcessing (@Nonnull final WPECTYPE aWPEC,
                                  @Nonnull final DATATYPE aObject,
                                  @Nonnull final EWebPageSimpleFormAction eSimpleFormAction)
  {}

  /**
   * @param aWPEC
   *        The web page execution context. Never <code>null</code>.
   * @param aObject
   *        The currently object. Never <code>null</code>.
   */
  protected abstract void showObject (@Nonnull WPECTYPE aWPEC, @Nonnull DATATYPE aObject);

  protected final void handleViewObject (@Nonnull final WPECTYPE aWPEC, @Nonnull final DATATYPE aObject)
  {
    // Valid object found - show details
    showObject (aWPEC, aObject);

    if (showViewToolbar (aWPEC, aObject))
    {
      // Toolbar on bottom
      aWPEC.getNodeList ().addChild (createViewToolbar (aWPEC, aObject));
    }
  }

  /**
   * @param aWPEC
   *        Web page execution context. Never <code>null</code>.
   * @param aObject
   *        The currently object. Never <code>null</code>.
   * @param aFormErrors
   *        Object for storing the validation errors. Never <code>null</code>.
   * @param eSimpleFormAction
   *        The form action mode. Either create, copy or edit.
   */
  protected abstract void validateAndSaveInputParameters (@Nonnull WPECTYPE aWPEC,
                                                          @Nonnull DATATYPE aObject,
                                                          @Nonnull FormErrorList aFormErrors,
                                                          @Nonnull EWebPageSimpleFormAction eSimpleFormAction);

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
  protected void onInputFormError (@Nonnull final WPECTYPE aWPEC, @Nonnull final FormErrorList aFormErrors)
  {}

  /**
   * Add additional form IDs (e.g. client and accounting area). This method is
   * called before
   * {@link #showInputForm(IWebPageExecutionContext, IHasID, IHCForm, EWebPageSimpleFormAction, FormErrorList)}
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
   * @param aObject
   *        The currently object. Never <code>null</code>.
   * @param aForm
   *        The parent form. Use this as parent and not the node list from the
   *        web page execution context! Never <code>null</code>.
   * @param eSimpleFormAction
   *        The form action used. Either create, copy or edit.
   * @param aFormErrors
   *        Previous errors from validation. Never <code>null</code> but maybe
   *        empty.
   */
  protected abstract void showInputForm (@Nonnull WPECTYPE aWPEC,
                                         @Nonnull DATATYPE aObject,
                                         @Nonnull FORM_TYPE aForm,
                                         @Nonnull EWebPageSimpleFormAction eSimpleFormAction,
                                         @Nonnull FormErrorList aFormErrors);

  /**
   * Add additional form IDs (e.g. client and accounting area). This method is
   * called after
   * {@link #showInputForm(IWebPageExecutionContext, IHasID, IHCForm, EWebPageSimpleFormAction, FormErrorList)}
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
   * Handle some other custom action
   *
   * @param aWPEC
   *        The web page execution context
   * @param aObject
   *        The object. Never <code>null</code>.
   * @return <code>true</code> to show the object afterwards. <code>false</code>
   *         if the object should not be shown.
   */
  @OverrideOnDemand
  protected boolean handleCustomActions (@Nonnull final WPECTYPE aWPEC, @Nonnull final DATATYPE aObject)
  {
    return true;
  }

  @Override
  protected final void fillContent (@Nonnull final WPECTYPE aWPEC)
  {
    final HCNodeList aNodeList = aWPEC.getNodeList ();

    // Get the object
    final DATATYPE aObject = getObject (aWPEC);
    if (aObject == null)
      throw new IllegalStateException ("No object present!");

    boolean bViewObject = true;
    final String sAction = aWPEC.getAction ();

    // Default value is view
    EWebPageSimpleFormAction eSimpleFormAction = EWebPageSimpleFormAction.DEFAULT;
    if (CPageParam.ACTION_EDIT.equals (sAction))
    {
      eSimpleFormAction = EWebPageSimpleFormAction.EDIT;
    }

    // An action was specified - check if it is allowed at all
    if (!isActionAllowed (aWPEC, eSimpleFormAction, aObject))
    {
      // Default back to custom
      s_aLogger.info ("Action " + eSimpleFormAction + " is not allowed on object " + aObject.getID ());
      eSimpleFormAction = EWebPageSimpleFormAction.DEFAULT;
    }

    // Try to lock object
    if (beforeProcessing (aWPEC, aObject, eSimpleFormAction).isContinue ())
    {
      switch (eSimpleFormAction)
      {
        case EDIT:
        {
          // Create or edit a client
          final FormErrorList aFormErrors = new FormErrorList ();
          boolean bShowInputForm = true;
          final boolean bIsFormSubmitted = aWPEC.hasSubAction (CPageParam.ACTION_SAVE);

          if (bIsFormSubmitted)
          {
            // Check if the nonce matches
            if (getCSRFHandler ().checkCSRFNonce (aWPEC).isContinue ())
            {
              // try to save, only if nonce is as expected
              validateAndSaveInputParameters (aWPEC, aObject, aFormErrors, eSimpleFormAction);
              if (aFormErrors.isEmpty ())
              {
                // Save successful
                bShowInputForm = false;

                // Callback method
                onInputFormFinishedSuccess (aWPEC);
              }
              else
              {
                // Show: changes could not be saved...
                aNodeList.addChild (getUIHandler ().createIncorrectInputBox (aWPEC));

                // Callback method
                onInputFormError (aWPEC, aFormErrors);
              }
            }
          }

          if (bShowInputForm)
          {
            // Show the input form. Either for the first time or because of form
            // errors a n-th time
            bViewObject = false;
            final FORM_TYPE aForm = isFileUploadForm (aWPEC) ? getUIHandler ().createFormFileUploadSelf (aWPEC)
                                                             : getUIHandler ().createFormSelf (aWPEC);
            aNodeList.addChild (aForm);

            // Set unique ID
            aForm.setID (FORM_ID_INPUT);

            // Add the nonce for CSRF check
            aForm.addChild (getCSRFHandler ().createCSRFNonceField ());

            // Callback method
            modifyFormBeforeShowInputForm (aWPEC, aForm);

            // Show the main input form
            showInputForm (aWPEC, aObject, aForm, eSimpleFormAction, aFormErrors);

            // Callback method
            modifyFormAfterShowInputForm (aWPEC, aForm);

            // Toolbar on bottom
            if (showEditToolbar (aWPEC, aObject))
              aForm.addChild (createEditToolbar (aWPEC, aForm, aObject));
          }
          break;
        }
        case CUSTOM:
        {
          // Other proprietary actions
          bViewObject = handleCustomActions (aWPEC, aObject);
          break;
        }
      }
    }

    if (bViewObject)
    {
      // show details
      handleViewObject (aWPEC, aObject);
    }

    // Call after everything
    afterProcessing (aWPEC, aObject, eSimpleFormAction);
  }
}
