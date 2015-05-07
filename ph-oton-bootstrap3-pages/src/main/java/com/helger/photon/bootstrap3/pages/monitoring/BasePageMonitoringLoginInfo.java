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
package com.helger.photon.bootstrap3.pages.monitoring;

import java.util.Collection;
import java.util.Locale;
import java.util.Map;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.helger.commons.annotations.Nonempty;
import com.helger.commons.annotations.OverrideOnDemand;
import com.helger.commons.annotations.Translatable;
import com.helger.commons.compare.ESortOrder;
import com.helger.commons.name.IHasDisplayText;
import com.helger.commons.name.IHasDisplayTextWithArgs;
import com.helger.commons.text.IReadonlyMultiLingualText;
import com.helger.commons.text.impl.TextProvider;
import com.helger.commons.text.resolve.DefaultTextResolver;
import com.helger.commons.url.ISimpleURL;
import com.helger.datetime.format.PDTToString;
import com.helger.html.hc.CHCParam;
import com.helger.html.hc.IHCCell;
import com.helger.html.hc.IHCNode;
import com.helger.html.hc.IHCTable;
import com.helger.html.hc.html.AbstractHCForm;
import com.helger.html.hc.html.HCA;
import com.helger.html.hc.html.HCCol;
import com.helger.html.hc.html.HCRow;
import com.helger.html.hc.impl.HCNodeList;
import com.helger.photon.basic.security.login.LoggedInUserManager;
import com.helger.photon.basic.security.login.LoginInfo;
import com.helger.photon.basic.security.user.IUser;
import com.helger.photon.basic.security.util.SecurityUtils;
import com.helger.photon.bootstrap3.alert.BootstrapErrorBox;
import com.helger.photon.bootstrap3.alert.BootstrapSuccessBox;
import com.helger.photon.bootstrap3.button.BootstrapButtonToolbar;
import com.helger.photon.bootstrap3.pages.AbstractBootstrapWebPageForm;
import com.helger.photon.bootstrap3.table.BootstrapTable;
import com.helger.photon.bootstrap3.table.BootstrapTableFormView;
import com.helger.photon.bootstrap3.uictrls.datatables.BootstrapDataTables;
import com.helger.photon.uicore.html.table.IHCTableFormView;
import com.helger.photon.uicore.html.toolbar.IButtonToolbar;
import com.helger.photon.uicore.icon.EDefaultIcon;
import com.helger.photon.uicore.page.EWebPageFormAction;
import com.helger.photon.uicore.page.EWebPageText;
import com.helger.photon.uicore.page.IWebPageExecutionContext;
import com.helger.photon.uictrls.datatables.DataTables;
import com.helger.photon.uictrls.datatables.comparator.ComparatorDTDateTime;
import com.helger.validation.error.FormErrors;
import com.helger.webbasics.EWebBasicsText;

/**
 * Show information on all logged in users.
 *
 * @author Philip Helger
 * @param <WPECTYPE>
 *        Web page execution context type
 */
public class BasePageMonitoringLoginInfo <WPECTYPE extends IWebPageExecutionContext> extends AbstractBootstrapWebPageForm <LoginInfo, WPECTYPE>
{
  @Translatable
  protected static enum EText implements IHasDisplayText, IHasDisplayTextWithArgs
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
    LOGOUT_QUESTION ("Sind Sie sicher, dass Sie den Benutzer ''{0}'' abmelden wollen?", "Are you sure you want to log out user ''{0}''?"),
    LOGOUT_SUCCESS ("Benutzer ''{0}'' wurde erfolgreich abgemeldet.", "User ''{0}'' was successfully logged out."),
    LOGOUT_ERROR ("Benutzer ''{0}'' konnte nicht abgemeldet werden, weil er nicht mehr angemeldet war.", "User ''{0}'' could not be logged out because he was not logged in.");

    private final TextProvider m_aTP;

    private EText (final String sDE, final String sEN)
    {
      m_aTP = TextProvider.create_DE_EN (sDE, sEN);
    }

    @Nullable
    public String getDisplayText (@Nonnull final Locale aContentLocale)
    {
      return DefaultTextResolver.getText (this, m_aTP, aContentLocale);
    }

    @Nullable
    public String getDisplayTextWithArgs (@Nonnull final Locale aContentLocale, @Nullable final Object... aArgs)
    {
      return DefaultTextResolver.getTextWithArgs (this, m_aTP, aContentLocale, aArgs);
    }
  }

  private static final String ACTION_LOGOUT_USER = "logoutuser";

  public BasePageMonitoringLoginInfo (@Nonnull @Nonempty final String sID)
  {
    super (sID, EWebPageText.PAGE_NAME_MONITORING_LOGIN_INFO.getAsMLT ());
  }

  public BasePageMonitoringLoginInfo (@Nonnull @Nonempty final String sID, @Nonnull @Nonempty final String sName)
  {
    super (sID, sName);
  }

  public BasePageMonitoringLoginInfo (@Nonnull @Nonempty final String sID,
                                      @Nonnull final String sName,
                                      @Nullable final String sDescription)
  {
    super (sID, sName, sDescription);
  }

  public BasePageMonitoringLoginInfo (@Nonnull @Nonempty final String sID,
                                      @Nonnull final IReadonlyMultiLingualText aName,
                                      @Nullable final IReadonlyMultiLingualText aDescription)
  {
    super (sID, aName, aDescription);
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
    return SecurityUtils.getUserDisplayName (aSelectedObject.getUser (), aDisplayLocale);
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

    final IHCTableFormView <?> aTable = aNodeList.addAndReturnChild (new BootstrapTableFormView (new HCCol (170),
                                                                                                 HCCol.star ()));
    aTable.setSpanningHeaderContent (EText.HEADER_DETAILS.getDisplayText (aDisplayLocale));
    aTable.createItemRow ()
          .setLabel (EText.MSG_USERID.getDisplayText (aDisplayLocale))
          .setCtrl (aSelectedObject.getUserID ());
    aTable.createItemRow ()
          .setLabel (EText.MSG_USERNAME.getDisplayText (aDisplayLocale))
          .setCtrl (SecurityUtils.getUserDisplayName (aSelectedObject.getUser (), aDisplayLocale));
    aTable.createItemRow ()
          .setLabel (EText.MSG_LOGINDT.getDisplayText (aDisplayLocale))
          .setCtrl (PDTToString.getAsString (aSelectedObject.getLoginDT (), aDisplayLocale));
    aTable.createItemRow ()
          .setLabel (EText.MSG_LASTACCESSDT.getDisplayText (aDisplayLocale))
          .setCtrl (PDTToString.getAsString (aSelectedObject.getLastAccessDT (), aDisplayLocale));
    if (aSelectedObject.getLogoutDT () != null)
    {
      aTable.createItemRow ()
            .setLabel (EText.MSG_LOGOUTDT.getDisplayText (aDisplayLocale))
            .setCtrl (PDTToString.getAsString (aSelectedObject.getLogoutDT (), aDisplayLocale));
    }
    aTable.createItemRow ()
          .setLabel (EText.MSG_SESSION_ID.getDisplayText (aDisplayLocale))
          .setCtrl (aSelectedObject.getSessionScope ().getID ());

    // Add custom attributes
    final Map <String, Object> aAttrs = aSelectedObject.getAllAttributes ();
    if (!aAttrs.isEmpty ())
    {
      final IHCTable <?> aCustomAttrTable = new BootstrapTable (new HCCol (170), HCCol.star ()).setID (aSelectedObject.getID ());
      aCustomAttrTable.addHeaderRow ().addCells (EText.MSG_NAME.getDisplayText (aDisplayLocale),
                                                 EText.MSG_VALUE.getDisplayText (aDisplayLocale));
      for (final Map.Entry <String, Object> aEntry : aAttrs.entrySet ())
        aCustomAttrTable.addBodyRow ().addCells (aEntry.getKey (), String.valueOf (aEntry.getValue ()));

      final DataTables aDataTables = BootstrapDataTables.createDefaultDataTables (aWPEC, aCustomAttrTable);
      aDataTables.setInitialSorting (0, ESortOrder.ASCENDING);

      aTable.createItemRow ()
            .setLabel (EText.MSG_ATTRS.getDisplayText (aDisplayLocale))
            .setCtrl (aCustomAttrTable, aDataTables.build ());
    }
  }

  @Override
  protected void validateAndSaveInputParameters (@Nonnull final WPECTYPE aWPEC,
                                                 @Nullable final LoginInfo aSelectedObject,
                                                 @Nonnull final FormErrors aFormErrors,
                                                 @Nonnull final EWebPageFormAction eFormAction)
  {
    throw new UnsupportedOperationException ();
  }

  @Override
  protected void showInputForm (@Nonnull final WPECTYPE aWPEC,
                                @Nullable final LoginInfo aSelectedObject,
                                @Nonnull final AbstractHCForm <?> aForm,
                                @Nonnull final EWebPageFormAction eFormAction,
                                @Nonnull final FormErrors aFormErrors)
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
  protected boolean handleCustomActions (@Nonnull final WPECTYPE aWPEC, @Nullable final LoginInfo aSelectedObject)
  {
    if (aWPEC.hasAction (ACTION_LOGOUT_USER) && aSelectedObject != null)
    {
      if (!canLogoutUser (aSelectedObject.getUser ()))
        throw new IllegalStateException ("Won't work!");

      final Locale aDisplayLocale = aWPEC.getDisplayLocale ();
      final HCNodeList aNodeList = aWPEC.getNodeList ();
      final String sUserName = SecurityUtils.getUserDisplayName (aSelectedObject.getUser (), aDisplayLocale);

      if (aWPEC.hasSubAction (ACTION_PERFORM))
      {
        // Destroy the session of the user -> this triggers the logout
        aSelectedObject.getSessionScope ().selfDestruct ();

        // Check if logout worked
        if (aSelectedObject.isLogout ())
        {
          aNodeList.addChild (new BootstrapSuccessBox ().addChild (EText.LOGOUT_SUCCESS.getDisplayTextWithArgs (aDisplayLocale,
                                                                                                                sUserName)));
        }
        else
        {
          aNodeList.addChild (new BootstrapErrorBox ().addChild (EText.LOGOUT_ERROR.getDisplayTextWithArgs (aDisplayLocale,
                                                                                                            sUserName)));
        }

        // Show list
        return true;
      }

      // Show question
      final AbstractHCForm <?> aForm = aNodeList.addAndReturnChild (createFormSelf (aWPEC));
      aForm.addChild (new BootstrapSuccessBox ().addChild (EText.LOGOUT_QUESTION.getDisplayTextWithArgs (aDisplayLocale,
                                                                                                         sUserName)));

      final IButtonToolbar <?> aToolbar = aForm.addAndReturnChild (new BootstrapButtonToolbar (aWPEC));
      aToolbar.addHiddenField (CHCParam.PARAM_ACTION, ACTION_LOGOUT_USER);
      aToolbar.addHiddenField (CHCParam.PARAM_OBJECT, aSelectedObject.getID ());
      aToolbar.addHiddenField (CHCParam.PARAM_SUBACTION, ACTION_PERFORM);
      aToolbar.addSubmitButtonYes (aDisplayLocale);
      aToolbar.addButtonNo (aDisplayLocale);
      return false;
    }
    return true;
  }

  @Override
  protected void showListOfExistingObjects (@Nonnull final WPECTYPE aWPEC)
  {
    final Locale aDisplayLocale = aWPEC.getDisplayLocale ();
    final HCNodeList aNodeList = aWPEC.getNodeList ();

    final IButtonToolbar <?> aToolbar = aNodeList.addAndReturnChild (new BootstrapButtonToolbar (aWPEC));
    aToolbar.addButton (EWebBasicsText.MSG_BUTTON_REFRESH.getDisplayText (aDisplayLocale),
                        aWPEC.getSelfHref (),
                        EDefaultIcon.REFRESH);

    final IHCTable <?> aTable = new BootstrapTable (HCCol.star (),
                                                    new HCCol (COLUMN_WIDTH_DATETIME),
                                                    new HCCol (COLUMN_WIDTH_DATETIME),
                                                    createActionCol (2)).setID (getID ());
    aTable.addHeaderRow ().addCells (EText.MSG_USERNAME.getDisplayText (aDisplayLocale),
                                     EText.MSG_LOGINDT.getDisplayText (aDisplayLocale),
                                     EText.MSG_LASTACCESSDT.getDisplayText (aDisplayLocale),
                                     EWebBasicsText.MSG_ACTIONS.getDisplayText (aDisplayLocale));
    final Collection <LoginInfo> aLoginInfos = LoggedInUserManager.getInstance ().getAllLoginInfos ();
    for (final LoginInfo aLoginInfo : aLoginInfos)
    {
      final ISimpleURL aViewLink = createViewURL (aWPEC, aLoginInfo);

      final HCRow aRow = aTable.addBodyRow ();
      aRow.addCell (new HCA (aViewLink).addChild (SecurityUtils.getUserDisplayName (aLoginInfo.getUser (),
                                                                                    aDisplayLocale)));
      aRow.addCell (PDTToString.getAsString (aLoginInfo.getLoginDT (), aDisplayLocale));
      aRow.addCell (PDTToString.getAsString (aLoginInfo.getLastAccessDT (), aDisplayLocale));

      final IHCCell <?> aActionCell = aRow.addCell ();
      if (canLogoutUser (aLoginInfo.getUser ()))
      {
        final String sUserName = SecurityUtils.getUserDisplayName (aLoginInfo.getUser (), aDisplayLocale);
        aActionCell.addChild (new HCA (aWPEC.getSelfHref ()
                                            .add (CHCParam.PARAM_ACTION, ACTION_LOGOUT_USER)
                                            .add (CHCParam.PARAM_OBJECT, aLoginInfo.getID ())).setTitle (EText.MSG_LOGOUT_USER.getDisplayTextWithArgs (aDisplayLocale,
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
    aDataTables.getOrCreateColumnOfTarget (1)
               .addClass (CSS_CLASS_RIGHT)
               .setComparator (new ComparatorDTDateTime (aDisplayLocale));
    aDataTables.getOrCreateColumnOfTarget (2)
               .addClass (CSS_CLASS_RIGHT)
               .setComparator (new ComparatorDTDateTime (aDisplayLocale));
    aDataTables.getOrCreateColumnOfTarget (3).addClass (CSS_CLASS_ACTION_COL).setSortable (false);
    aDataTables.setInitialSorting (1, ESortOrder.ASCENDING);
    aNodeList.addChild (aDataTables);
  }
}
