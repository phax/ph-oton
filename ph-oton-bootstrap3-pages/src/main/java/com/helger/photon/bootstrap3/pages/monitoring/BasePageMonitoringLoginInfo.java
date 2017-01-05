/**
 * Copyright (C) 2014-2017 Philip Helger (www.helger.com)
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
package com.helger.photon.bootstrap3.pages.monitoring;

import java.util.Collection;
import java.util.Locale;
import java.util.Map;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.helger.commons.annotation.Nonempty;
import com.helger.commons.annotation.OverrideOnDemand;
import com.helger.commons.annotation.Translatable;
import com.helger.commons.compare.ESortOrder;
import com.helger.commons.text.IMultilingualText;
import com.helger.commons.text.display.IHasDisplayTextWithArgs;
import com.helger.commons.text.resolve.DefaultTextResolver;
import com.helger.commons.text.util.TextHelper;
import com.helger.commons.url.ISimpleURL;
import com.helger.datetime.format.PDTToString;
import com.helger.html.hc.IHCNode;
import com.helger.html.hc.html.tabular.HCRow;
import com.helger.html.hc.html.tabular.HCTable;
import com.helger.html.hc.html.tabular.IHCCell;
import com.helger.html.hc.html.textlevel.HCA;
import com.helger.html.hc.impl.HCNodeList;
import com.helger.photon.bootstrap3.alert.BootstrapErrorBox;
import com.helger.photon.bootstrap3.alert.BootstrapQuestionBox;
import com.helger.photon.bootstrap3.alert.BootstrapSuccessBox;
import com.helger.photon.bootstrap3.button.BootstrapButtonToolbar;
import com.helger.photon.bootstrap3.form.BootstrapForm;
import com.helger.photon.bootstrap3.form.BootstrapFormGroup;
import com.helger.photon.bootstrap3.form.BootstrapViewForm;
import com.helger.photon.bootstrap3.pages.AbstractBootstrapWebPageForm;
import com.helger.photon.bootstrap3.pages.handler.AbstractBootstrapWebPageActionHandler;
import com.helger.photon.bootstrap3.uictrls.datatables.BootstrapDTColAction;
import com.helger.photon.bootstrap3.uictrls.datatables.BootstrapDataTables;
import com.helger.photon.core.EPhotonCoreText;
import com.helger.photon.core.form.FormErrorList;
import com.helger.photon.security.login.LoggedInUserManager;
import com.helger.photon.security.login.LoginInfo;
import com.helger.photon.security.user.IUser;
import com.helger.photon.security.util.SecurityHelper;
import com.helger.photon.uicore.css.CPageParam;
import com.helger.photon.uicore.icon.EDefaultIcon;
import com.helger.photon.uicore.page.EWebPageFormAction;
import com.helger.photon.uicore.page.EWebPageText;
import com.helger.photon.uicore.page.IWebPageExecutionContext;
import com.helger.photon.uictrls.datatables.DataTables;
import com.helger.photon.uictrls.datatables.column.DTCol;
import com.helger.photon.uictrls.datatables.column.EDTColType;

/**
 * Show information on all logged in users.
 *
 * @author Philip Helger
 * @param <WPECTYPE>
 *        Web page execution context type
 */
public class BasePageMonitoringLoginInfo <WPECTYPE extends IWebPageExecutionContext>
                                         extends AbstractBootstrapWebPageForm <LoginInfo, WPECTYPE>
{
  @Translatable
  protected static enum EText implements IHasDisplayTextWithArgs
  {
    MSG_USERNAME ("Benutzername", "User name"),
    MSG_LOGINDT ("Anmeldezeit", "Login time"),
    MSG_LASTACCESSDT ("Letzter Zugriff", "Last access"),
    HEADER_DETAILS ("Details des angemeldeten Benutzers", "Details of logged in user"),
    MSG_USERID ("Benutzer-ID", "User ID"),
    MSG_LOGOUTDT ("Abmeldezeit", "Logout time"),
    MSG_SESSION_ID ("Session-ID", "Session ID"),
    MSG_ATTRS ("Attribute", "Attributes"),
    MSG_NAME ("Name", "Wert"),
    MSG_VALUE ("Wert", "Value"),
    MSG_LOGOUT_USER ("Benutzer {0} abmelden", "Log out user {0}"),
    LOGOUT_QUESTION ("Sind Sie sicher, dass Sie den Benutzer ''{0}'' abmelden wollen?",
                     "Are you sure you want to log out user ''{0}''?"),
    LOGOUT_SUCCESS ("Benutzer ''{0}'' wurde erfolgreich abgemeldet.", "User ''{0}'' was successfully logged out."),
    LOGOUT_ERROR ("Benutzer ''{0}'' konnte nicht abgemeldet werden, weil er nicht mehr angemeldet war.",
                  "User ''{0}'' could not be logged out because he was not logged in.");

    private final IMultilingualText m_aTP;

    private EText (final String sDE, final String sEN)
    {
      m_aTP = TextHelper.create_DE_EN (sDE, sEN);
    }

    @Nullable
    public String getDisplayText (@Nonnull final Locale aContentLocale)
    {
      return DefaultTextResolver.getTextStatic (this, m_aTP, aContentLocale);
    }
  }

  private static final String ACTION_LOGOUT_USER = "logoutuser";

  private void _init ()
  {
    addCustomHandler (ACTION_LOGOUT_USER, new AbstractBootstrapWebPageActionHandler <LoginInfo, WPECTYPE> (true)
    {
      public boolean handleAction (final WPECTYPE aWPEC, final LoginInfo aSelectedObject)
      {
        if (!canLogoutUser (aSelectedObject.getUser ()))
          throw new IllegalStateException ("Won't work!");

        final Locale aDisplayLocale = aWPEC.getDisplayLocale ();
        final HCNodeList aNodeList = aWPEC.getNodeList ();
        final String sUserName = SecurityHelper.getUserDisplayName (aSelectedObject.getUser (), aDisplayLocale);

        if (aWPEC.hasSubAction (CPageParam.ACTION_PERFORM))
        {
          // Destroy the session of the user -> this triggers the logout
          aSelectedObject.getSessionScope ().selfDestruct ();

          // Check if logout worked
          if (aSelectedObject.isLogout ())
          {
            aWPEC.postRedirectGetInternal (new BootstrapSuccessBox ().addChild (EText.LOGOUT_SUCCESS.getDisplayTextWithArgs (aDisplayLocale,
                                                                                                                             sUserName)));
          }
          else
          {
            aWPEC.postRedirectGetInternal (new BootstrapErrorBox ().addChild (EText.LOGOUT_ERROR.getDisplayTextWithArgs (aDisplayLocale,
                                                                                                                         sUserName)));
          }
        }

        // Show question
        final BootstrapForm aForm = aNodeList.addAndReturnChild (getUIHandler ().createFormSelf (aWPEC));
        aForm.addChild (new BootstrapQuestionBox ().addChild (EText.LOGOUT_QUESTION.getDisplayTextWithArgs (aDisplayLocale,
                                                                                                            sUserName)));

        final BootstrapButtonToolbar aToolbar = aForm.addAndReturnChild (new BootstrapButtonToolbar (aWPEC));
        aToolbar.addHiddenField (CPageParam.PARAM_ACTION, ACTION_LOGOUT_USER);
        aToolbar.addHiddenField (CPageParam.PARAM_OBJECT, aSelectedObject.getID ());
        aToolbar.addHiddenField (CPageParam.PARAM_SUBACTION, CPageParam.ACTION_PERFORM);
        aToolbar.addSubmitButtonYes (aDisplayLocale);
        aToolbar.addButtonNo (aDisplayLocale);
        return false;
      }
    });
  }

  public BasePageMonitoringLoginInfo (@Nonnull @Nonempty final String sID)
  {
    super (sID, EWebPageText.PAGE_NAME_MONITORING_LOGIN_INFO.getAsMLT ());
    _init ();
  }

  public BasePageMonitoringLoginInfo (@Nonnull @Nonempty final String sID, @Nonnull @Nonempty final String sName)
  {
    super (sID, sName);
    _init ();
  }

  public BasePageMonitoringLoginInfo (@Nonnull @Nonempty final String sID,
                                      @Nonnull final String sName,
                                      @Nullable final String sDescription)
  {
    super (sID, sName, sDescription);
    _init ();
  }

  public BasePageMonitoringLoginInfo (@Nonnull @Nonempty final String sID,
                                      @Nonnull final IMultilingualText aName,
                                      @Nullable final IMultilingualText aDescription)
  {
    super (sID, aName, aDescription);
    _init ();
  }

  @Override
  protected boolean isObjectLockingEnabled ()
  {
    return true;
  }

  @Override
  @Nullable
  protected String getObjectDisplayName (@Nonnull final WPECTYPE aWPEC, @Nonnull final LoginInfo aSelectedObject)
  {
    final Locale aDisplayLocale = aWPEC.getDisplayLocale ();
    return SecurityHelper.getUserDisplayName (aSelectedObject.getUser (), aDisplayLocale);
  }

  @Override
  @Nullable
  protected LoginInfo getSelectedObject (@Nonnull final WPECTYPE aWPEC, @Nullable final String sID)
  {
    return LoggedInUserManager.getInstance ().getLoginInfo (sID);
  }

  @Override
  protected boolean isActionAllowed (@Nonnull final WPECTYPE aWPEC,
                                     @Nonnull final EWebPageFormAction eFormAction,
                                     @Nullable final LoginInfo aSelectedObject)
  {
    if (eFormAction.isEdit ())
      return false;
    return true;
  }

  @Override
  protected void showSelectedObject (@Nonnull final WPECTYPE aWPEC, @Nonnull final LoginInfo aSelectedObject)
  {
    final HCNodeList aNodeList = aWPEC.getNodeList ();
    final Locale aDisplayLocale = aWPEC.getDisplayLocale ();

    aNodeList.addChild (getUIHandler ().createActionHeader (EText.HEADER_DETAILS.getDisplayText (aDisplayLocale)));
    final BootstrapViewForm aTable = aNodeList.addAndReturnChild (new BootstrapViewForm ());
    aTable.addFormGroup (new BootstrapFormGroup ().setLabel (EText.MSG_USERID.getDisplayText (aDisplayLocale))
                                                  .setCtrl (aSelectedObject.getUserID ()));
    aTable.addFormGroup (new BootstrapFormGroup ().setLabel (EText.MSG_USERNAME.getDisplayText (aDisplayLocale))
                                                  .setCtrl (SecurityHelper.getUserDisplayName (aSelectedObject.getUser (),
                                                                                               aDisplayLocale)));
    aTable.addFormGroup (new BootstrapFormGroup ().setLabel (EText.MSG_LOGINDT.getDisplayText (aDisplayLocale))
                                                  .setCtrl (PDTToString.getAsString (aSelectedObject.getLoginDT (),
                                                                                     aDisplayLocale)));
    aTable.addFormGroup (new BootstrapFormGroup ().setLabel (EText.MSG_LASTACCESSDT.getDisplayText (aDisplayLocale))
                                                  .setCtrl (PDTToString.getAsString (aSelectedObject.getLastAccessDT (),
                                                                                     aDisplayLocale)));
    if (aSelectedObject.getLogoutDT () != null)
    {
      aTable.addFormGroup (new BootstrapFormGroup ().setLabel (EText.MSG_LOGOUTDT.getDisplayText (aDisplayLocale))
                                                    .setCtrl (PDTToString.getAsString (aSelectedObject.getLogoutDT (),
                                                                                       aDisplayLocale)));
    }
    aTable.addFormGroup (new BootstrapFormGroup ().setLabel (EText.MSG_SESSION_ID.getDisplayText (aDisplayLocale))
                                                  .setCtrl (aSelectedObject.getSessionScope ().getID ()));

    // Add custom attributes
    final Map <String, String> aAttrs = aSelectedObject.getAllAttributes ();
    if (!aAttrs.isEmpty ())
    {
      final HCTable aCustomAttrTable = new HCTable (new DTCol (EText.MSG_NAME.getDisplayText (aDisplayLocale)).setInitialSorting (ESortOrder.ASCENDING),
                                                    new DTCol (EText.MSG_VALUE.getDisplayText (aDisplayLocale))).setID (aSelectedObject.getID ());
      for (final Map.Entry <String, String> aEntry : aAttrs.entrySet ())
        aCustomAttrTable.addBodyRow ().addCells (aEntry.getKey (), aEntry.getValue ());

      final DataTables aDataTables = BootstrapDataTables.createDefaultDataTables (aWPEC, aCustomAttrTable);
      aTable.addFormGroup (new BootstrapFormGroup ().setLabel (EText.MSG_ATTRS.getDisplayText (aDisplayLocale))
                                                    .setCtrl (aCustomAttrTable, aDataTables));
    }
  }

  @Override
  protected void validateAndSaveInputParameters (@Nonnull final WPECTYPE aWPEC,
                                                 @Nullable final LoginInfo aSelectedObject,
                                                 @Nonnull final FormErrorList aFormErrors,
                                                 @Nonnull final EWebPageFormAction eFormAction)
  {
    throw new UnsupportedOperationException ();
  }

  @Override
  protected void showInputForm (@Nonnull final WPECTYPE aWPEC,
                                @Nullable final LoginInfo aSelectedObject,
                                @Nonnull final BootstrapForm aForm,
                                @Nonnull final EWebPageFormAction eFormAction,
                                @Nonnull final FormErrorList aFormErrors)
  {
    throw new UnsupportedOperationException ();
  }

  protected final boolean canLogoutUser (@Nullable final IUser aUser)
  {
    if (aUser == null)
      return false;

    // Cannot logout the admin and myself
    return aUser.isEnabled () &&
           !aUser.isDeleted () &&
           !aUser.isAdministrator () &&
           !aUser.equals (LoggedInUserManager.getInstance ().getCurrentUser ());
  }

  @Nullable
  @OverrideOnDemand
  protected IHCNode getLogoutUserIcon ()
  {
    return EDefaultIcon.KEY.getIcon ().getAsNode ();
  }

  @Override
  protected void showListOfExistingObjects (@Nonnull final WPECTYPE aWPEC)
  {
    final Locale aDisplayLocale = aWPEC.getDisplayLocale ();
    final HCNodeList aNodeList = aWPEC.getNodeList ();

    final BootstrapButtonToolbar aToolbar = aNodeList.addAndReturnChild (new BootstrapButtonToolbar (aWPEC));
    aToolbar.addButton (EPhotonCoreText.BUTTON_REFRESH.getDisplayText (aDisplayLocale),
                        aWPEC.getSelfHref (),
                        EDefaultIcon.REFRESH);

    final HCTable aTable = new HCTable (new DTCol (EText.MSG_USERNAME.getDisplayText (aDisplayLocale)),
                                        new DTCol (EText.MSG_LOGINDT.getDisplayText (aDisplayLocale)).setDisplayType (EDTColType.DATETIME,
                                                                                                                      aDisplayLocale)
                                                                                                     .setInitialSorting (ESortOrder.DESCENDING),
                                        new DTCol (EText.MSG_LASTACCESSDT.getDisplayText (aDisplayLocale)).setDisplayType (EDTColType.DATETIME,
                                                                                                                           aDisplayLocale),
                                        new BootstrapDTColAction (aDisplayLocale)).setID (getID ());
    final Collection <LoginInfo> aLoginInfos = LoggedInUserManager.getInstance ().getAllLoginInfos ();
    for (final LoginInfo aLoginInfo : aLoginInfos)
    {
      final ISimpleURL aViewLink = createViewURL (aWPEC, aLoginInfo);

      final HCRow aRow = aTable.addBodyRow ();
      aRow.addCell (new HCA (aViewLink).addChild (SecurityHelper.getUserDisplayName (aLoginInfo.getUser (),
                                                                                     aDisplayLocale)));
      aRow.addCell (PDTToString.getAsString (aLoginInfo.getLoginDT (), aDisplayLocale));
      aRow.addCell (PDTToString.getAsString (aLoginInfo.getLastAccessDT (), aDisplayLocale));

      final IHCCell <?> aActionCell = aRow.addCell ();
      if (canLogoutUser (aLoginInfo.getUser ()))
      {
        final String sUserName = SecurityHelper.getUserDisplayName (aLoginInfo.getUser (), aDisplayLocale);
        aActionCell.addChild (new HCA (aWPEC.getSelfHref ()
                                            .add (CPageParam.PARAM_ACTION, ACTION_LOGOUT_USER)
                                            .add (CPageParam.PARAM_OBJECT,
                                                  aLoginInfo.getID ())).setTitle (EText.MSG_LOGOUT_USER.getDisplayTextWithArgs (aDisplayLocale,
                                                                                                                                sUserName))
                                                                       .addChild (getLogoutUserIcon ()));
      }
      else
      {
        aActionCell.addChild (createEmptyAction ());
      }
    }

    aNodeList.addChild (aTable);

    final DataTables aDataTables = BootstrapDataTables.createDefaultDataTables (aWPEC, aTable);
    aNodeList.addChild (aDataTables);
  }
}
