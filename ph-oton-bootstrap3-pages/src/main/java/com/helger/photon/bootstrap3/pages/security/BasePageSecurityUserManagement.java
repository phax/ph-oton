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
package com.helger.photon.bootstrap3.pages.security;

import java.util.Collection;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.helger.commons.annotation.Nonempty;
import com.helger.commons.annotation.OverrideOnDemand;
import com.helger.commons.annotation.Translatable;
import com.helger.commons.collection.CollectionHelper;
import com.helger.commons.collection.ext.ICommonsCollection;
import com.helger.commons.collection.ext.ICommonsMap;
import com.helger.commons.collection.ext.ICommonsSet;
import com.helger.commons.compare.ESortOrder;
import com.helger.commons.email.EmailAddressHelper;
import com.helger.commons.equals.EqualsHelper;
import com.helger.commons.errorlist.FormErrors;
import com.helger.commons.name.IHasName;
import com.helger.commons.string.StringHelper;
import com.helger.commons.text.IMultilingualText;
import com.helger.commons.text.display.IHasDisplayText;
import com.helger.commons.text.display.IHasDisplayTextWithArgs;
import com.helger.commons.text.resolve.DefaultTextResolver;
import com.helger.commons.text.util.TextHelper;
import com.helger.commons.url.ISimpleURL;
import com.helger.datetime.format.PDTToString;
import com.helger.html.hc.IHCNode;
import com.helger.html.hc.ext.HCA_MailTo;
import com.helger.html.hc.ext.HCExtHelper;
import com.helger.html.hc.html.forms.HCCheckBox;
import com.helger.html.hc.html.forms.HCEdit;
import com.helger.html.hc.html.forms.HCEditPassword;
import com.helger.html.hc.html.grouping.HCDiv;
import com.helger.html.hc.html.tabular.HCCol;
import com.helger.html.hc.html.tabular.HCRow;
import com.helger.html.hc.html.tabular.HCTable;
import com.helger.html.hc.html.tabular.IHCCell;
import com.helger.html.hc.html.textlevel.HCA;
import com.helger.html.hc.html.textlevel.HCEM;
import com.helger.html.hc.impl.HCNodeList;
import com.helger.html.hc.impl.HCTextNode;
import com.helger.photon.bootstrap3.alert.BootstrapErrorBox;
import com.helger.photon.bootstrap3.alert.BootstrapQuestionBox;
import com.helger.photon.bootstrap3.alert.BootstrapSuccessBox;
import com.helger.photon.bootstrap3.button.BootstrapButtonToolbar;
import com.helger.photon.bootstrap3.form.BootstrapForm;
import com.helger.photon.bootstrap3.form.BootstrapFormGroup;
import com.helger.photon.bootstrap3.form.BootstrapViewForm;
import com.helger.photon.bootstrap3.nav.BootstrapTabBox;
import com.helger.photon.bootstrap3.pages.AbstractBootstrapWebPageActionHandlerDelete;
import com.helger.photon.bootstrap3.pages.AbstractBootstrapWebPageActionHandlerUndelete;
import com.helger.photon.bootstrap3.pages.BootstrapPagesMenuConfigurator;
import com.helger.photon.bootstrap3.table.BootstrapTable;
import com.helger.photon.bootstrap3.uictrls.datatables.BootstrapDTColAction;
import com.helger.photon.bootstrap3.uictrls.datatables.BootstrapDataTables;
import com.helger.photon.bootstrap3.uictrls.ext.BootstrapSecurityUI;
import com.helger.photon.core.EPhotonCoreText;
import com.helger.photon.core.form.RequestField;
import com.helger.photon.core.form.RequestFieldBoolean;
import com.helger.photon.security.mgr.PhotonSecurityManager;
import com.helger.photon.security.password.GlobalPasswordSettings;
import com.helger.photon.security.role.IRole;
import com.helger.photon.security.user.IUser;
import com.helger.photon.security.user.UserManager;
import com.helger.photon.security.usergroup.IUserGroup;
import com.helger.photon.security.usergroup.UserGroupManager;
import com.helger.photon.security.util.SecurityHelper;
import com.helger.photon.uicore.css.CPageParam;
import com.helger.photon.uicore.html.formlabel.ELabelType;
import com.helger.photon.uicore.html.select.HCUserGroupForUserSelect;
import com.helger.photon.uicore.icon.EDefaultIcon;
import com.helger.photon.uicore.page.EWebPageFormAction;
import com.helger.photon.uicore.page.EWebPageText;
import com.helger.photon.uicore.page.IWebPageExecutionContext;
import com.helger.photon.uictrls.autosize.HCTextAreaAutosize;
import com.helger.photon.uictrls.datatables.DataTables;
import com.helger.photon.uictrls.datatables.column.DTCol;
import com.helger.photon.uictrls.datatables.column.EDTColType;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;

public class BasePageSecurityUserManagement <WPECTYPE extends IWebPageExecutionContext>
                                            extends AbstractWebPageSecurityObjectWithAttributes <IUser, WPECTYPE>
{
  @Translatable
  protected static enum EText implements IHasDisplayText,IHasDisplayTextWithArgs
  {
    BUTTON_CREATE_NEW_USER ("Neuen Benutzer anlegen", "Create new user"),
    TAB_ACTIVE ("Aktive Benutzer ({0})", "Active users ({0})"),
    TAB_DISABLED ("Deaktivierte Benutzer ({0})", "Disabled users ({0})"),
    TAB_DELETED ("Gelöschte Benutzer ({0})", "Deleted users ({0})"),
    HEADER_NAME ("Name", "Name"),
    HEADER_LOGINNAME ("Benutzername", "User name"),
    HEADER_EMAIL ("E-Mail", "Email"),
    HEADER_USERGROUPS ("Benutzergruppen", "User groups"),
    HEADER_LAST_LOGIN ("Letzter Login", "Last login"),
    HEADER_VALUE ("Wert", "Value"),
    TITLE_CREATE ("Neuen Benutzer anlegen", "Create new user"),
    TITLE_EDIT ("Benutzer ''{0}'' bearbeiten", "Edit user ''{0}''"),
    TITLE_RESET_PASSWORD ("Passwort von ''{0}'' zurücksetzen", "Reset password of user ''{0}''"),
    NONE_DEFINED ("keine definiert", "none defined"),
    HEADER_DETAILS ("Details von Benutzer ''{0}''", "Details of user ''{0}''"),
    LABEL_CREATIONDATE ("Angelegt am", "Created on"),
    LABEL_LASTMODIFICATIONDATE ("Letzte Änderung am", "Last modification on"),
    LABEL_DELETIONDATE ("Gelöscht am", "Deleted on"),
    LABEL_LOGINNAME ("Benutzername", "User name"),
    LABEL_FIRSTNAME ("Vorname", "First name"),
    LABEL_LASTNAME ("Nachname", "Last name"),
    LABEL_EMAIL ("E-Mail-Adresse", "Email address"),
    LABEL_PASSWORD ("Passwort", "Password"),
    LABEL_PASSWORD_CONFIRM ("Passwort (Bestätigung)", "Password (confirmation)"),
    LABEL_ENABLED ("Aktiv?", "Enabled?"),
    LABEL_DESCRIPTION ("Beschreibung", "Description"),
    LABEL_DELETED ("Gelöscht?", "Deleted?"),
    LABEL_LAST_LOGIN ("Letzter Login", "Last login"),
    LABEL_LAST_LOGIN_NEVER ("noch nie", "never"),
    LABEL_LOGIN_COUNT ("Login-Anzahl", "Login count"),
    LABEL_CONSECUTIVE_FAILED_LOGIN_COUNT ("Fehlgeschlagene Logins", "Failed logins"),
    LABEL_PASSWORD_HASH_ALGORITHM ("Passwort Hash-Algorithmus", "Password hash algorithm"),
    LABEL_USERGROUPS_0 ("Benutzergruppen", "User groups"),
    LABEL_USERGROUPS_N ("Benutzergruppen ({0})", "User groups ({0})"),
    LABEL_ROLES_0 ("Rollen", "Roles"),
    LABEL_ROLES_N ("Rollen ({0})", "Roles ({0})"),
    LABEL_ATTRIBUTES ("Attribute", "Attributes"),
    ERROR_LOGINNAME_REQUIRED ("Es muss ein Benutzername angegeben werden!", "A user name must be specified!"),
    ERROR_LASTNAME_REQUIRED ("Es muss ein Nachname angegeben werden!", "A last name must be specified!"),
    ERROR_EMAIL_REQUIRED ("Es muss eine E-Mail-Adresse angegeben werden!", "An email address must be specified!"),
    ERROR_EMAIL_INVALID ("Es muss eine gültige E-Mail-Adresse angegeben werden!",
                         "A valid email address must be specified!"),
    ERROR_EMAIL_IN_USE ("Ein anderer Benutzer mit dieser E-Mail-Adresse existiert bereits!",
                        "Another user with this email address already exists!"),
    ERROR_PASSWORDS_DONT_MATCH ("Die Passwörter stimmen nicht überein!", "Passwords don't match"),
    ERROR_NO_USERGROUP ("Es muss mindestens eine Benutzergruppe ausgewählt werden!",
                        "At least one user group must be selected!"),
    ERROR_INVALID_USERGROUPS ("Mindestens eine der angegebenen Benutzergruppen ist ungültig!",
                              "At least one selected user group is invalid!"),
    SUCCESS_CREATE ("Der neue Benutzer wurde erfolgreich angelegt!", "Successfully created the new user!"),
    SUCCESS_EDIT ("Der Benutzer wurde erfolgreich bearbeitet!", "Sucessfully edited the user!"),
    FAILURE_CREATE ("Fehler beim Anlegen des Benutzers!", "Error creating the new user!"),
    SUCCESS_RESET_PASSWORD ("Das neue Passwort vom Benutzer ''{0}'' wurde gespeichert!",
                            "Successfully saved the new password of user ''{0}''!"),
    DEL_QUERY ("Sind Sie sicher, dass Sie den Benutzer ''{0}'' löschen wollen?",
               "Are you sure you want to delete user ''{0}''?"),
    DEL_SUCCESS ("Der Benutzer ''{0}'' wurde erfolgreich gelöscht!", "User ''{0}'' was successfully deleted!"),
    DEL_ERROR ("Beim Löschen des Benutzers ''{0}'' ist ein Fehler aufgetreten!",
               "An error occurred while deleting user ''{0}''!"),
    UNDEL_QUERY ("Sind Sie sicher, dass Sie den Benutzer ''{0}'' wiederherstellen wollen?",
                 "Are you sure you want to undelete user ''{0}''?"),
    UNDEL_SUCCESS ("Der Benutzer ''{0}'' wurde erfolgreich wiederhergestellt!",
                   "User ''{0}'' was successfully undeleted!"),
    UNDEL_ERROR ("Beim Wiederherstellen des Benutzers ''{0}'' ist ein Fehler aufgetreten!",
                 "An error occurred while undeleting user ''{0}''!");

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

    @Nullable
    public String getDisplayTextWithArgs (@Nonnull final Locale aContentLocale, @Nullable final Object... aArgs)
    {
      return DefaultTextResolver.getTextWithArgsStatic (this, m_aTP, aContentLocale, aArgs);
    }
  }

  public static final boolean DEFAULT_USER_ENABLED = true;
  public static final String FIELD_FIRSTNAME = "firstname";
  public static final String FIELD_LASTNAME = "lastname";
  public static final String FIELD_LOGINNAME = "loginname";
  public static final String FIELD_EMAILADDRESS = "emailaddress";
  public static final String FIELD_PASSWORD = "password";
  public static final String FIELD_PASSWORD_CONFIRM = "passwordconf";
  public static final String FIELD_ENABLED = "enabled";
  public static final String FIELD_DESCRIPTION = "description";
  public static final String FIELD_USERGROUPS = "usergroups";

  public static final String ACTION_RESET_PASSWORD = "resetpw";

  private Locale m_aDefaultUserLocale;

  private void _init ()
  {
    setDeleteHandler (new AbstractBootstrapWebPageActionHandlerDelete <IUser, WPECTYPE> ()
    {
      @Override
      @OverrideOnDemand
      protected void showDeleteQuery (@Nonnull final WPECTYPE aWPEC,
                                      @Nonnull final BootstrapForm aForm,
                                      @Nonnull final IUser aSelectedObject)
      {
        final Locale aDisplayLocale = aWPEC.getDisplayLocale ();
        aForm.addChild (new BootstrapQuestionBox ().addChild (EText.DEL_QUERY.getDisplayTextWithArgs (aDisplayLocale,
                                                                                                      aSelectedObject.getDisplayName ())));
      }

      @Override
      @OverrideOnDemand
      protected void performDelete (@Nonnull final WPECTYPE aWPEC, @Nonnull final IUser aSelectedObject)
      {
        final Locale aDisplayLocale = aWPEC.getDisplayLocale ();
        final UserManager aUserMgr = PhotonSecurityManager.getUserMgr ();

        if (aUserMgr.deleteUser (aSelectedObject.getID ()).isChanged ())
          aWPEC.postRedirectGet (new BootstrapSuccessBox ().addChild (EText.DEL_SUCCESS.getDisplayTextWithArgs (aDisplayLocale,
                                                                                                                aSelectedObject.getDisplayName ())));
        else
          aWPEC.postRedirectGet (new BootstrapErrorBox ().addChild (EText.DEL_ERROR.getDisplayTextWithArgs (aDisplayLocale,
                                                                                                            aSelectedObject.getDisplayName ())));
      }
    });
    setUndeleteHandler (new AbstractBootstrapWebPageActionHandlerUndelete <IUser, WPECTYPE> ()
    {
      @Override
      @OverrideOnDemand
      protected void showUndeleteQuery (@Nonnull final WPECTYPE aWPEC,
                                        @Nonnull final BootstrapForm aForm,
                                        @Nonnull final IUser aSelectedObject)
      {
        final Locale aDisplayLocale = aWPEC.getDisplayLocale ();
        aForm.addChild (new BootstrapQuestionBox ().addChild (EText.UNDEL_QUERY.getDisplayTextWithArgs (aDisplayLocale,
                                                                                                        aSelectedObject.getDisplayName ())));
      }

      @Override
      @OverrideOnDemand
      protected void performUndelete (@Nonnull final WPECTYPE aWPEC, @Nonnull final IUser aSelectedObject)
      {
        final Locale aDisplayLocale = aWPEC.getDisplayLocale ();
        final UserManager aUserMgr = PhotonSecurityManager.getUserMgr ();

        if (aUserMgr.undeleteUser (aSelectedObject.getID ()).isChanged ())
          aWPEC.postRedirectGet (new BootstrapSuccessBox ().addChild (EText.UNDEL_SUCCESS.getDisplayTextWithArgs (aDisplayLocale,
                                                                                                                  aSelectedObject.getDisplayName ())));
        else
          aWPEC.postRedirectGet (new BootstrapErrorBox ().addChild (EText.UNDEL_ERROR.getDisplayTextWithArgs (aDisplayLocale,
                                                                                                              aSelectedObject.getDisplayName ())));
      }
    });
  }

  public BasePageSecurityUserManagement (@Nonnull @Nonempty final String sID)
  {
    super (sID, EWebPageText.PAGE_NAME_SECURITY_USERS.getAsMLT ());
    _init ();
  }

  public BasePageSecurityUserManagement (@Nonnull @Nonempty final String sID, @Nonnull @Nonempty final String sName)
  {
    super (sID, sName);
    _init ();
  }

  public BasePageSecurityUserManagement (@Nonnull @Nonempty final String sID,
                                         @Nonnull final String sName,
                                         @Nullable final String sDescription)
  {
    super (sID, sName, sDescription);
    _init ();
  }

  public BasePageSecurityUserManagement (@Nonnull @Nonempty final String sID,
                                         @Nonnull final IMultilingualText aName,
                                         @Nullable final IMultilingualText aDescription)
  {
    super (sID, aName, aDescription);
    _init ();
  }

  @Nonnull
  public BasePageSecurityUserManagement <WPECTYPE> setDefaultUserLocale (@Nullable final Locale aDefaultUserLocale)
  {
    m_aDefaultUserLocale = aDefaultUserLocale;
    return this;
  }

  /**
   * Override this method to determine if the email address should be used as
   * login name or not.
   *
   * @return <code>true</code> by default
   */
  @OverrideOnDemand
  protected boolean useEmailAddressAsLoginName ()
  {
    return true;
  }

  @OverrideOnDemand
  protected boolean isLastNameMandatory ()
  {
    return true;
  }

  @OverrideOnDemand
  protected boolean isEmailMandatory ()
  {
    return true;
  }

  @Override
  @Nullable
  protected String getObjectDisplayName (@Nonnull final WPECTYPE aWPEC, @Nonnull final IUser aSelectedObject)
  {
    final Locale aDisplayLocale = aWPEC.getDisplayLocale ();
    return SecurityHelper.getUserDisplayName (aSelectedObject, aDisplayLocale);
  }

  @Override
  @Nullable
  protected IUser getSelectedObject (@Nonnull final WPECTYPE aWPEC, @Nullable final String sID)
  {
    final UserManager aUserMgr = PhotonSecurityManager.getUserMgr ();
    return aUserMgr.getUserOfID (sID);
  }

  @Override
  protected boolean isActionAllowed (@Nonnull final WPECTYPE aWPEC,
                                     @Nonnull final EWebPageFormAction eFormAction,
                                     @Nullable final IUser aSelectedObject)
  {
    if (eFormAction.isEdit ())
      return SecurityUIHelper.canBeEdited (aSelectedObject);
    if (eFormAction.isDelete ())
      return SecurityUIHelper.canBeDeleted (aSelectedObject);
    if (eFormAction.isUndelete ())
      return SecurityUIHelper.canBeUndeleted (aSelectedObject);
    return true;
  }

  /**
   * Check if the password of a user can be reset or not. Currently the
   * passwords of all not deleted users can be reset.
   *
   * @param aUser
   *        The user to check. May be <code>null</code>.
   * @return <code>true</code> if the password can be reset, <code>false</code>
   *         if not.
   */
  protected final boolean canResetPassword (@Nonnull final IUser aUser)
  {
    return SecurityUIHelper.canResetPassword (aUser);
  }

  @Override
  @OverrideOnDemand
  protected void onShowSelectedObjectTableStart (@Nonnull final WPECTYPE aWPEC,
                                                 @Nonnull final BootstrapViewForm aForm,
                                                 @Nonnull final IUser aSelectedObject)
  {
    final Locale aDisplayLocale = aWPEC.getDisplayLocale ();
    aForm.addFormGroup (new BootstrapFormGroup ().setLabel (EText.LABEL_CREATIONDATE.getDisplayText (aDisplayLocale))
                                                 .setCtrl (PDTToString.getAsString (aSelectedObject.getCreationDateTime (),
                                                                                    aDisplayLocale)));
    if (aSelectedObject.hasLastModificationDateTime ())
      aForm.addFormGroup (new BootstrapFormGroup ().setLabel (EText.LABEL_LASTMODIFICATIONDATE.getDisplayText (aDisplayLocale))
                                                   .setCtrl (PDTToString.getAsString (aSelectedObject.getLastModificationDateTime (),
                                                                                      aDisplayLocale)));
    if (aSelectedObject.hasDeletionDateTime ())
      aForm.addFormGroup (new BootstrapFormGroup ().setLabel (EText.LABEL_DELETIONDATE.getDisplayText (aDisplayLocale))
                                                   .setCtrl (PDTToString.getAsString (aSelectedObject.getDeletionDateTime (),
                                                                                      aDisplayLocale)));
  }

  @Override
  @SuppressFBWarnings ("RCN_REDUNDANT_NULLCHECK_OF_NONNULL_VALUE")
  protected void showSelectedObject (@Nonnull final WPECTYPE aWPEC, @Nonnull final IUser aSelectedObject)
  {
    final HCNodeList aNodeList = aWPEC.getNodeList ();
    final Locale aDisplayLocale = aWPEC.getDisplayLocale ();
    final UserGroupManager aUserGroupMgr = PhotonSecurityManager.getUserGroupMgr ();

    aNodeList.addChild (createActionHeader (EText.HEADER_DETAILS.getDisplayTextWithArgs (aDisplayLocale,
                                                                                         SecurityHelper.getUserDisplayName (aSelectedObject,
                                                                                                                            aDisplayLocale))));
    final BootstrapViewForm aForm = aNodeList.addAndReturnChild (new BootstrapViewForm ());
    onShowSelectedObjectTableStart (aWPEC, aForm, aSelectedObject);
    if (!useEmailAddressAsLoginName ())
    {
      aForm.addFormGroup (new BootstrapFormGroup ().setLabel (EText.LABEL_LOGINNAME.getDisplayText (aDisplayLocale))
                                                   .setCtrl (aSelectedObject.getLoginName ()));
    }
    aForm.addFormGroup (new BootstrapFormGroup ().setLabel (EText.LABEL_FIRSTNAME.getDisplayText (aDisplayLocale))
                                                 .setCtrl (aSelectedObject.getFirstName ()));
    aForm.addFormGroup (new BootstrapFormGroup ().setLabel (EText.LABEL_LASTNAME.getDisplayText (aDisplayLocale))
                                                 .setCtrl (aSelectedObject.getLastName ()));
    aForm.addFormGroup (new BootstrapFormGroup ().setLabel (EText.LABEL_EMAIL.getDisplayText (aDisplayLocale))
                                                 .setCtrl (HCA_MailTo.createLinkedEmail (aSelectedObject.getEmailAddress ())));
    aForm.addFormGroup (new BootstrapFormGroup ().setLabel (EText.LABEL_ENABLED.getDisplayText (aDisplayLocale))
                                                 .setCtrl (EPhotonCoreText.getYesOrNo (aSelectedObject.isEnabled (),
                                                                                       aDisplayLocale)));
    if (StringHelper.hasText (aSelectedObject.getDescription ()))
      aForm.addFormGroup (new BootstrapFormGroup ().setLabel (EText.LABEL_DESCRIPTION.getDisplayText (aDisplayLocale))
                                                   .setCtrl (HCExtHelper.nl2divList (aSelectedObject.getDescription ())));
    aForm.addFormGroup (new BootstrapFormGroup ().setLabel (EText.LABEL_DELETED.getDisplayText (aDisplayLocale))
                                                 .setCtrl (EPhotonCoreText.getYesOrNo (aSelectedObject.isDeleted (),
                                                                                       aDisplayLocale)));
    aForm.addFormGroup (new BootstrapFormGroup ().setLabel (EText.LABEL_LAST_LOGIN.getDisplayText (aDisplayLocale))
                                                 .setCtrl (aSelectedObject.getLastLoginDateTime () != null ? new HCTextNode (PDTToString.getAsString (aSelectedObject.getLastLoginDateTime (),
                                                                                                                                                      aDisplayLocale))
                                                                                                           : new HCEM ().addChild (EText.LABEL_LAST_LOGIN_NEVER.getDisplayText (aDisplayLocale))));
    aForm.addFormGroup (new BootstrapFormGroup ().setLabel (EText.LABEL_LOGIN_COUNT.getDisplayText (aDisplayLocale))
                                                 .setCtrl (Integer.toString (aSelectedObject.getLoginCount ())));
    aForm.addFormGroup (new BootstrapFormGroup ().setLabel (EText.LABEL_CONSECUTIVE_FAILED_LOGIN_COUNT.getDisplayText (aDisplayLocale))
                                                 .setCtrl (Integer.toString (aSelectedObject.getConsecutiveFailedLoginCount ())));

    aForm.addFormGroup (new BootstrapFormGroup ().setLabel (EText.LABEL_PASSWORD_HASH_ALGORITHM.getDisplayText (aDisplayLocale))
                                                 .setCtrl (aSelectedObject.getPasswordHash ().getAlgorithmName ()));

    // user groups
    final Collection <? extends IUserGroup> aUserGroups = aUserGroupMgr.getAllUserGroupsWithAssignedUser (aSelectedObject.getID ());
    if (aUserGroups.isEmpty ())
    {
      aForm.addFormGroup (new BootstrapFormGroup ().setLabel (EText.LABEL_USERGROUPS_0.getDisplayText (aDisplayLocale))
                                                   .setCtrl (new HCEM ().addChild (EText.NONE_DEFINED.getDisplayText (aDisplayLocale))));
    }
    else
    {
      final HCNodeList aUserGroupUI = new HCNodeList ();
      CollectionHelper.getSorted (aUserGroups, IHasName.getComparatorCollating (aDisplayLocale))
                      .forEach (aUG -> aUserGroupUI.addChild (new HCDiv ().addChild (new HCA (createViewURL (aWPEC,
                                                                                                             BootstrapPagesMenuConfigurator.MENU_ADMIN_SECURITY_USER_GROUP,
                                                                                                             aUG.getID (),
                                                                                                             null)).addChild (aUG.getName ()))));
      aForm.addFormGroup (new BootstrapFormGroup ().setLabel (EText.LABEL_USERGROUPS_N.getDisplayTextWithArgs (aDisplayLocale,
                                                                                                               Integer.toString (aUserGroups.size ())))
                                                   .setCtrl (aUserGroupUI));
    }

    // roles
    final Set <IRole> aUserRoles = SecurityHelper.getAllUserRoles (aSelectedObject.getID ());
    if (aUserRoles.isEmpty ())
    {
      aForm.addFormGroup (new BootstrapFormGroup ().setLabel (EText.LABEL_ROLES_0.getDisplayText (aDisplayLocale))
                                                   .setCtrl (new HCEM ().addChild (EText.NONE_DEFINED.getDisplayText (aDisplayLocale))));
    }
    else
    {
      final HCNodeList aRoleUI = new HCNodeList ();
      CollectionHelper.getSorted (aUserRoles, IHasName.getComparatorCollating (aDisplayLocale))
                      .forEach (aRole -> aRoleUI.addChild (new HCDiv ().addChild (new HCA (createViewURL (aWPEC,
                                                                                                          BootstrapPagesMenuConfigurator.MENU_ADMIN_SECURITY_ROLE,
                                                                                                          aRole.getID (),
                                                                                                          null)).addChild (aRole.getName ()))));
      aForm.addFormGroup (new BootstrapFormGroup ().setLabel (EText.LABEL_ROLES_N.getDisplayTextWithArgs (aDisplayLocale,
                                                                                                          Integer.toString (aUserRoles.size ())))
                                                   .setCtrl (aRoleUI));
    }

    // custom attributes
    final ICommonsMap <String, String> aCustomAttrs = aSelectedObject.getAllAttributes ();

    // Callback for custom attributes
    final ICommonsSet <String> aHandledAttrs = onShowSelectedObjectCustomAttrs (aWPEC,
                                                                                aSelectedObject,
                                                                                aCustomAttrs,
                                                                                aForm);

    if (aCustomAttrs.isNotEmpty ())
    {
      final BootstrapTable aAttrTable = new BootstrapTable (new HCCol (170), HCCol.star ());
      aAttrTable.addHeaderRow ().addCells (EText.HEADER_NAME.getDisplayText (aDisplayLocale),
                                           EText.HEADER_VALUE.getDisplayText (aDisplayLocale));
      for (final Map.Entry <String, String> aEntry : aCustomAttrs.entrySet ())
      {
        final String sName = aEntry.getKey ();
        if (aHandledAttrs == null || !aHandledAttrs.contains (sName))
        {
          final String sValue = aEntry.getValue ();
          aAttrTable.addBodyRow ().addCells (sName, sValue);
        }
      }

      // Maybe all custom attributes where handled in
      // showCustomAttrsOfSelectedObject
      if (aAttrTable.hasBodyRows ())
        aForm.addFormGroup (new BootstrapFormGroup ().setLabel (EText.LABEL_ATTRIBUTES.getDisplayText (aDisplayLocale))
                                                     .setCtrl (aAttrTable));
    }

    // Callback
    onShowSelectedObjectTableEnd (aWPEC, aForm, aSelectedObject);
  }

  @Override
  protected void validateAndSaveInputParameters (@Nonnull final WPECTYPE aWPEC,
                                                 @Nullable final IUser aSelectedObject,
                                                 @Nonnull final FormErrors aFormErrors,
                                                 @Nonnull final EWebPageFormAction eFormAction)
  {
    final boolean bEdit = eFormAction.isEdit ();
    final Locale aDisplayLocale = aWPEC.getDisplayLocale ();
    final boolean bIsAdministrator = bEdit && aSelectedObject != null && aSelectedObject.isAdministrator ();
    final UserManager aUserMgr = PhotonSecurityManager.getUserMgr ();
    final UserGroupManager aUserGroupMgr = PhotonSecurityManager.getUserGroupMgr ();

    String sLoginName = aWPEC.getAttributeAsString (FIELD_LOGINNAME);
    final String sFirstName = aWPEC.getAttributeAsString (FIELD_FIRSTNAME);
    final String sLastName = aWPEC.getAttributeAsString (FIELD_LASTNAME);
    final String sEmailAddress = aWPEC.getAttributeAsString (FIELD_EMAILADDRESS);
    final String sPassword = aWPEC.getAttributeAsString (FIELD_PASSWORD);
    final String sPasswordConf = aWPEC.getAttributeAsString (FIELD_PASSWORD_CONFIRM);
    final boolean bEnabled = bIsAdministrator ? true : aWPEC.getCheckBoxAttr (FIELD_ENABLED, DEFAULT_USER_ENABLED);
    final String sDescription = aWPEC.getAttributeAsString (FIELD_DESCRIPTION);
    final ICommonsCollection <String> aUserGroupIDs = bIsAdministrator ? aUserGroupMgr.getAllUserGroupIDsWithAssignedUser (aSelectedObject.getID ())
                                                                       : aWPEC.getAttributeAsList (FIELD_USERGROUPS);

    if (useEmailAddressAsLoginName ())
    {
      sLoginName = sEmailAddress;
    }
    else
    {
      if (StringHelper.hasNoText (sLoginName))
        aFormErrors.addFieldError (FIELD_LOGINNAME, EText.ERROR_LOGINNAME_REQUIRED.getDisplayText (aDisplayLocale));
    }

    if (StringHelper.hasNoText (sLastName))
    {
      if (isLastNameMandatory ())
        aFormErrors.addFieldError (FIELD_LASTNAME, EText.ERROR_LASTNAME_REQUIRED.getDisplayText (aDisplayLocale));
    }

    if (StringHelper.hasNoText (sEmailAddress))
    {
      if (isEmailMandatory ())
        aFormErrors.addFieldError (FIELD_EMAILADDRESS, EText.ERROR_EMAIL_REQUIRED.getDisplayText (aDisplayLocale));
    }
    else
      if (!EmailAddressHelper.isValid (sEmailAddress))
        aFormErrors.addFieldError (FIELD_EMAILADDRESS, EText.ERROR_EMAIL_INVALID.getDisplayText (aDisplayLocale));
      else
      {
        final IUser aSameLoginUser = aUserMgr.getUserOfLoginName (sEmailAddress);
        if (aSameLoginUser != null)
          if (!bEdit || !aSameLoginUser.equals (aSelectedObject))
            aFormErrors.addFieldError (FIELD_EMAILADDRESS, EText.ERROR_EMAIL_IN_USE.getDisplayText (aDisplayLocale));
      }

    if (!bEdit)
    {
      final List <String> aPasswordErrors = GlobalPasswordSettings.getPasswordConstraintList ()
                                                                  .getInvalidPasswordDescriptions (sPassword,
                                                                                                   aDisplayLocale);
      for (final String sPasswordError : aPasswordErrors)
        aFormErrors.addFieldError (FIELD_PASSWORD, sPasswordError);
      if (!EqualsHelper.equals (sPassword, sPasswordConf))
        aFormErrors.addFieldError (FIELD_PASSWORD_CONFIRM,
                                   EText.ERROR_PASSWORDS_DONT_MATCH.getDisplayText (aDisplayLocale));
    }

    if (CollectionHelper.isEmpty (aUserGroupIDs))
      aFormErrors.addFieldError (FIELD_USERGROUPS, EText.ERROR_NO_USERGROUP.getDisplayText (aDisplayLocale));
    else
      if (!aUserGroupMgr.containsAllUserGroupsWithID (aUserGroupIDs))
        aFormErrors.addFieldError (FIELD_USERGROUPS, EText.ERROR_INVALID_USERGROUPS.getDisplayText (aDisplayLocale));

    // Call custom method
    final ICommonsMap <String, String> aCustomAttrMap = validateCustomInputParameters (aWPEC,
                                                                                       aSelectedObject,
                                                                                       aFormErrors,
                                                                                       eFormAction);

    if (aFormErrors.isEmpty ())
    {
      // All fields are valid -> save
      if (bEdit)
      {
        final String sUserID = aSelectedObject.getID ();

        final Map <String, String> aAttrMap = aSelectedObject.getAllAttributes ();
        if (aCustomAttrMap != null)
          aAttrMap.putAll (aCustomAttrMap);

        // We're editing an existing object
        aUserMgr.setUserData (sUserID,
                              sLoginName,
                              sEmailAddress,
                              sFirstName,
                              sLastName,
                              sDescription,
                              m_aDefaultUserLocale,
                              aAttrMap,
                              !bEnabled);

        // assign to the matching user groups
        final Collection <String> aPrevUserGroupIDs = aUserGroupMgr.getAllUserGroupIDsWithAssignedUser (sUserID);
        // Create all missing assignments
        final Set <String> aUserGroupsToBeAssigned = CollectionHelper.getDifference (aUserGroupIDs, aPrevUserGroupIDs);
        for (final String sUserGroupID : aUserGroupsToBeAssigned)
          aUserGroupMgr.assignUserToUserGroup (sUserGroupID, sUserID);

        // Delete all old assignments
        final Set <String> aUserGroupsToBeUnassigned = CollectionHelper.getDifference (aPrevUserGroupIDs,
                                                                                       aUserGroupIDs);
        for (final String sUserGroupID : aUserGroupsToBeUnassigned)
          aUserGroupMgr.unassignUserFromUserGroup (sUserGroupID, sUserID);

        aWPEC.postRedirectGet (new BootstrapSuccessBox ().addChild (EText.SUCCESS_EDIT.getDisplayText (aDisplayLocale)));
      }
      else
      {
        // We're creating a new object
        final IUser aNewUser = aUserMgr.createNewUser (sLoginName,
                                                       sEmailAddress,
                                                       sPassword,
                                                       sFirstName,
                                                       sLastName,
                                                       sDescription,
                                                       m_aDefaultUserLocale,
                                                       aCustomAttrMap,
                                                       !bEnabled);
        if (aNewUser != null)
        {
          // assign to the matching internal user groups
          for (final String sUserGroupID : aUserGroupIDs)
            aUserGroupMgr.assignUserToUserGroup (sUserGroupID, aNewUser.getID ());

          aWPEC.postRedirectGet (new BootstrapSuccessBox ().addChild (EText.SUCCESS_CREATE.getDisplayText (aDisplayLocale)));
        }
        else
          aWPEC.postRedirectGet (new BootstrapErrorBox ().addChild (EText.FAILURE_CREATE.getDisplayText (aDisplayLocale)));
      }
    }
  }

  /**
   * Modify the user group selector, e.g. for adding JS event handlers.
   *
   * @param aSelect
   *        The user group selector. Never <code>null</code>.
   */
  @OverrideOnDemand
  protected void showInputFormModifyUserGroupSelect (@Nonnull final HCUserGroupForUserSelect aSelect)
  {}

  @Override
  protected void showInputForm (@Nonnull final WPECTYPE aWPEC,
                                @Nullable final IUser aSelectedObject,
                                @Nonnull final BootstrapForm aForm,
                                @Nonnull final EWebPageFormAction eFormAction,
                                @Nonnull final FormErrors aFormErrors)
  {
    final boolean bEdit = eFormAction.isEdit ();
    final boolean bIsAdministrator = bEdit && aSelectedObject != null && aSelectedObject.isAdministrator ();
    final Locale aDisplayLocale = aWPEC.getDisplayLocale ();
    final UserGroupManager aUserGroupMgr = PhotonSecurityManager.getUserGroupMgr ();

    aForm.addChild (createActionHeader (bEdit ? EText.TITLE_EDIT.getDisplayTextWithArgs (aDisplayLocale,
                                                                                         SecurityHelper.getUserDisplayName (aSelectedObject,
                                                                                                                            aDisplayLocale))
                                              : EText.TITLE_CREATE.getDisplayText (aDisplayLocale)));
    if (!useEmailAddressAsLoginName ())
    {
      final String sLoginName = EText.LABEL_LOGINNAME.getDisplayText (aDisplayLocale);
      aForm.addFormGroup (new BootstrapFormGroup ().setLabelMandatory (sLoginName)
                                                   .setCtrl (new HCEdit (new RequestField (FIELD_LOGINNAME,
                                                                                           aSelectedObject == null ? null
                                                                                                                   : aSelectedObject.getLoginName ())).setPlaceholder (sLoginName))
                                                   .setErrorList (aFormErrors.getListOfField (FIELD_LOGINNAME)));
    }

    {
      final String sFirstName = EText.LABEL_FIRSTNAME.getDisplayText (aDisplayLocale);
      aForm.addFormGroup (new BootstrapFormGroup ().setLabel (sFirstName)
                                                   .setCtrl (new HCEdit (new RequestField (FIELD_FIRSTNAME,
                                                                                           aSelectedObject == null ? null
                                                                                                                   : aSelectedObject.getFirstName ())).setPlaceholder (sFirstName)
                                                                                                                                                      .setAutoFocus (eFormAction.isCreate ()))
                                                   .setErrorList (aFormErrors.getListOfField (FIELD_FIRSTNAME)));
    }

    {
      final String sLastName = EText.LABEL_LASTNAME.getDisplayText (aDisplayLocale);
      aForm.addFormGroup (new BootstrapFormGroup ().setLabel (sLastName,
                                                              isLastNameMandatory () ? ELabelType.MANDATORY
                                                                                     : ELabelType.OPTIONAL)
                                                   .setCtrl (new HCEdit (new RequestField (FIELD_LASTNAME,
                                                                                           aSelectedObject == null ? null
                                                                                                                   : aSelectedObject.getLastName ())).setPlaceholder (sLastName))
                                                   .setErrorList (aFormErrors.getListOfField (FIELD_LASTNAME)));
    }

    {
      final String sEmail = EText.LABEL_EMAIL.getDisplayText (aDisplayLocale);
      aForm.addFormGroup (new BootstrapFormGroup ().setLabel (sEmail,
                                                              isEmailMandatory () ? ELabelType.MANDATORY
                                                                                  : ELabelType.OPTIONAL)
                                                   .setCtrl (new HCEdit (new RequestField (FIELD_EMAILADDRESS,
                                                                                           aSelectedObject == null ? null
                                                                                                                   : aSelectedObject.getEmailAddress ())).setPlaceholder (sEmail))
                                                   .setErrorList (aFormErrors.getListOfField (FIELD_EMAILADDRESS)));
    }

    if (!bEdit)
    {
      // Password is only shown on creation of a new user
      final boolean bHasAnyPasswordConstraint = GlobalPasswordSettings.getPasswordConstraintList ().hasConstraints ();

      final String sPassword = EText.LABEL_PASSWORD.getDisplayText (aDisplayLocale);
      aForm.addFormGroup (new BootstrapFormGroup ().setLabel (sPassword,
                                                              bHasAnyPasswordConstraint ? ELabelType.MANDATORY
                                                                                        : ELabelType.OPTIONAL)
                                                   .setCtrl (new HCEditPassword (FIELD_PASSWORD).setPlaceholder (sPassword))
                                                   .setHelpText (BootstrapSecurityUI.createPasswordConstraintTip (aDisplayLocale))
                                                   .setErrorList (aFormErrors.getListOfField (FIELD_PASSWORD)));

      final String sPasswordConfirm = EText.LABEL_PASSWORD_CONFIRM.getDisplayText (aDisplayLocale);
      aForm.addFormGroup (new BootstrapFormGroup ().setLabel (sPasswordConfirm,
                                                              bHasAnyPasswordConstraint ? ELabelType.MANDATORY
                                                                                        : ELabelType.OPTIONAL)
                                                   .setCtrl (new HCEditPassword (FIELD_PASSWORD_CONFIRM).setPlaceholder (sPasswordConfirm))
                                                   .setHelpText (BootstrapSecurityUI.createPasswordConstraintTip (aDisplayLocale))
                                                   .setErrorList (aFormErrors.getListOfField (FIELD_PASSWORD_CONFIRM)));
    }

    if (bIsAdministrator)
    {
      // Cannot edit enabled state of administrator
      aForm.addFormGroup (new BootstrapFormGroup ().setLabel (EText.LABEL_ENABLED.getDisplayText (aDisplayLocale))
                                                   .setCtrl (EPhotonCoreText.getYesOrNo (aSelectedObject.isEnabled (),
                                                                                         aDisplayLocale)));
    }
    else
    {
      aForm.addFormGroup (new BootstrapFormGroup ().setLabelMandatory (EText.LABEL_ENABLED.getDisplayText (aDisplayLocale))
                                                   .setCtrl (new HCCheckBox (new RequestFieldBoolean (FIELD_ENABLED,
                                                                                                      aSelectedObject == null ? DEFAULT_USER_ENABLED
                                                                                                                              : aSelectedObject.isEnabled ())))
                                                   .setErrorList (aFormErrors.getListOfField (FIELD_ENABLED)));
    }

    {
      // Description
      final String sDescription = EText.LABEL_DESCRIPTION.getDisplayText (aDisplayLocale);
      aForm.addFormGroup (new BootstrapFormGroup ().setLabel (sDescription)
                                                   .setCtrl (new HCTextAreaAutosize (new RequestField (FIELD_DESCRIPTION,
                                                                                                       aSelectedObject == null ? null
                                                                                                                               : aSelectedObject.getDescription ())).setPlaceholder (sDescription))
                                                   .setErrorList (aFormErrors.getListOfField (FIELD_DESCRIPTION)));
    }

    {
      final Collection <String> aUserGroupIDs = aSelectedObject == null ? aWPEC.getAttributeAsList (FIELD_USERGROUPS)
                                                                        : aUserGroupMgr.getAllUserGroupIDsWithAssignedUser (aSelectedObject.getID ());
      final HCUserGroupForUserSelect aSelect = new HCUserGroupForUserSelect (new RequestField (FIELD_USERGROUPS),
                                                                             aUserGroupIDs);
      aForm.addFormGroup (new BootstrapFormGroup ().setLabelMandatory (EText.LABEL_USERGROUPS_0.getDisplayText (aDisplayLocale))
                                                   .setCtrl (aSelect)
                                                   .setErrorList (aFormErrors.getListOfField (FIELD_USERGROUPS)));
      if (bIsAdministrator)
      {
        // Cannot edit user groups of administrator
        // Don't "disable" because than no param would be send
        aSelect.setReadOnly (true);
      }
      showInputFormModifyUserGroupSelect (aSelect);
    }

    // Custom overridable
    onShowInputFormEnd (aWPEC, aSelectedObject, aForm, eFormAction, aFormErrors);
  }

  @Override
  protected boolean handleCustomActions (@Nonnull final WPECTYPE aWPEC, @Nullable final IUser aSelectedObject)
  {
    if (aWPEC.hasAction (ACTION_RESET_PASSWORD) && aSelectedObject != null)
    {
      if (!canResetPassword (aSelectedObject))
        throw new IllegalStateException ("Won't work!");

      final HCNodeList aNodeList = aWPEC.getNodeList ();
      final Locale aDisplayLocale = aWPEC.getDisplayLocale ();
      final boolean bShowForm = true;
      final FormErrors aFormErrors = new FormErrors ();
      final UserManager aUserMgr = PhotonSecurityManager.getUserMgr ();

      if (aWPEC.hasSubAction (CPageParam.ACTION_PERFORM))
      {
        // Check if the nonce matches
        if (getCSRFHandler ().checkCSRFNonce (aWPEC).isContinue ())
        {
          final String sPlainTextPassword = aWPEC.getAttributeAsString (FIELD_PASSWORD);
          final String sPlainTextPasswordConfirm = aWPEC.getAttributeAsString (FIELD_PASSWORD_CONFIRM);

          final List <String> aPasswordErrors = GlobalPasswordSettings.getPasswordConstraintList ()
                                                                      .getInvalidPasswordDescriptions (sPlainTextPassword,
                                                                                                       aDisplayLocale);
          for (final String sPasswordError : aPasswordErrors)
            aFormErrors.addFieldError (FIELD_PASSWORD, sPasswordError);
          if (!EqualsHelper.equals (sPlainTextPassword, sPlainTextPasswordConfirm))
            aFormErrors.addFieldError (FIELD_PASSWORD_CONFIRM,
                                       EText.ERROR_PASSWORDS_DONT_MATCH.getDisplayText (aDisplayLocale));

          if (aFormErrors.isEmpty ())
          {
            aUserMgr.setUserPassword (aSelectedObject.getID (), sPlainTextPassword);
            aNodeList.addChild (new BootstrapSuccessBox ().addChild (EText.SUCCESS_RESET_PASSWORD.getDisplayTextWithArgs (aDisplayLocale,
                                                                                                                          SecurityHelper.getUserDisplayName (aSelectedObject,
                                                                                                                                                             aDisplayLocale))));
            return true;
          }
        }
      }
      if (bShowForm)
      {
        // Show input form
        final boolean bHasAnyPasswordConstraint = GlobalPasswordSettings.getPasswordConstraintList ().hasConstraints ();
        final BootstrapForm aForm = aNodeList.addAndReturnChild (getUIHandler ().createFormSelf (aWPEC));
        aForm.addChild (createActionHeader (EText.TITLE_RESET_PASSWORD.getDisplayTextWithArgs (aDisplayLocale,
                                                                                               SecurityHelper.getUserDisplayName (aSelectedObject,
                                                                                                                                  aDisplayLocale))));

        final String sPassword = EText.LABEL_PASSWORD.getDisplayText (aDisplayLocale);
        aForm.addFormGroup (new BootstrapFormGroup ().setLabel (sPassword,
                                                                bHasAnyPasswordConstraint ? ELabelType.MANDATORY
                                                                                          : ELabelType.OPTIONAL)
                                                     .setCtrl (new HCEditPassword (FIELD_PASSWORD).setPlaceholder (sPassword))
                                                     .setHelpText (BootstrapSecurityUI.createPasswordConstraintTip (aDisplayLocale))
                                                     .setErrorList (aFormErrors.getListOfField (FIELD_PASSWORD)));

        final String sPasswordConfirm = EText.LABEL_PASSWORD_CONFIRM.getDisplayText (aDisplayLocale);
        aForm.addFormGroup (new BootstrapFormGroup ().setLabel (sPasswordConfirm,
                                                                bHasAnyPasswordConstraint ? ELabelType.MANDATORY
                                                                                          : ELabelType.OPTIONAL)
                                                     .setCtrl (new HCEditPassword (FIELD_PASSWORD_CONFIRM).setPlaceholder (sPasswordConfirm))
                                                     .setHelpText (BootstrapSecurityUI.createPasswordConstraintTip (aDisplayLocale))
                                                     .setErrorList (aFormErrors.getListOfField (FIELD_PASSWORD_CONFIRM)));

        final BootstrapButtonToolbar aToolbar = aForm.addAndReturnChild (new BootstrapButtonToolbar (aWPEC));
        aToolbar.addHiddenField (CPageParam.PARAM_ACTION, ACTION_RESET_PASSWORD);
        aToolbar.addHiddenField (CPageParam.PARAM_OBJECT, aSelectedObject.getID ());
        aToolbar.addHiddenField (CPageParam.PARAM_SUBACTION, CPageParam.ACTION_PERFORM);
        // Add the nonce for CSRF check
        aToolbar.addChild (getCSRFHandler ().createCSRFNonceField ());
        aToolbar.addSubmitButtonSave (aDisplayLocale);
        aToolbar.addButtonCancel (aDisplayLocale);
      }
      return false;
    }
    return true;
  }

  @Nullable
  @OverrideOnDemand
  protected IHCNode getResetPasswordIcon ()
  {
    return EDefaultIcon.KEY.getIcon ().getAsNode ();
  }

  @Nonnull
  protected IHCNode getTabWithUsers (@Nonnull final WPECTYPE aWPEC,
                                     @Nonnull final Collection <? extends IUser> aUsers,
                                     @Nonnull @Nonempty final String sTableID)
  {
    final Locale aDisplayLocale = aWPEC.getDisplayLocale ();

    final boolean bSeparateLoginName = !useEmailAddressAsLoginName ();
    final UserGroupManager aUserGroupMgr = PhotonSecurityManager.getUserGroupMgr ();

    // List existing
    final HCTable aTable = new HCTable (new DTCol ().setVisible (false),
                                        new DTCol (EText.HEADER_NAME.getDisplayText (aDisplayLocale)),
                                        bSeparateLoginName ? new DTCol (EText.HEADER_LOGINNAME.getDisplayText (aDisplayLocale))
                                                           : null,
                                        new DTCol (EText.HEADER_EMAIL.getDisplayText (aDisplayLocale)).setInitialSorting (ESortOrder.ASCENDING),
                                        new DTCol (EText.HEADER_USERGROUPS.getDisplayText (aDisplayLocale)),
                                        new DTCol (EText.HEADER_LAST_LOGIN.getDisplayText (aDisplayLocale)).setDisplayType (EDTColType.DATETIME,
                                                                                                                            aDisplayLocale),
                                        new BootstrapDTColAction (aDisplayLocale)).setID (sTableID);

    for (final IUser aCurUser : aUsers)
    {
      final ISimpleURL aViewLink = createViewURL (aWPEC, aCurUser);

      final HCRow aRow = aTable.addBodyRow ();

      // Hidden ID
      aRow.addCell (aCurUser.getID ());

      // Name
      aRow.addCell (new HCA (aViewLink).addChild (SecurityHelper.getUserDisplayName (aCurUser, aDisplayLocale)));

      // Login name
      if (bSeparateLoginName)
        aRow.addCell (aCurUser.getLoginName ());

      // Email address
      aRow.addCell (HCA_MailTo.createLinkedEmail (aCurUser.getEmailAddress ()));

      // User groups
      {
        final IHCCell <?> aUserGroupCell = aRow.addCell ();
        aUserGroupMgr.getAllUserGroupsWithAssignedUser (aCurUser.getID ())
                     .getSorted (IHasName.getComparatorCollating (aDisplayLocale))
                     .forEach (aUG -> aUserGroupCell.addChild (new HCDiv ().addChild (new HCA (createViewURL (aWPEC,
                                                                                                              BootstrapPagesMenuConfigurator.MENU_ADMIN_SECURITY_USER_GROUP,
                                                                                                              aUG.getID (),
                                                                                                              null)).addChild (aUG.getName ()))));
      }

      // Last login
      aRow.addCell (PDTToString.getAsString (aCurUser.getLastLoginDateTime (), aDisplayLocale));

      final IHCCell <?> aActionCell = aRow.addCell ();

      // Edit user
      if (isActionAllowed (aWPEC, EWebPageFormAction.EDIT, aCurUser))
        aActionCell.addChild (createEditLink (aWPEC, aCurUser));
      else
        aActionCell.addChild (createEmptyAction ());

      // Copy user
      aActionCell.addChild (" ");
      if (isActionAllowed (aWPEC, EWebPageFormAction.COPY, aCurUser))
        aActionCell.addChild (createCopyLink (aWPEC, aCurUser));
      else
        aActionCell.addChild (createEmptyAction ());

      // Reset password of user
      aActionCell.addChild (" ");
      if (canResetPassword (aCurUser))
      {
        aActionCell.addChild (new HCA (aWPEC.getSelfHref ()
                                            .add (CPageParam.PARAM_ACTION, ACTION_RESET_PASSWORD)
                                            .add (CPageParam.PARAM_OBJECT,
                                                  aCurUser.getID ())).setTitle (EText.TITLE_RESET_PASSWORD.getDisplayTextWithArgs (aDisplayLocale,
                                                                                                                                   SecurityHelper.getUserDisplayName (aCurUser,
                                                                                                                                                                      aDisplayLocale)))
                                                                     .addChild (getResetPasswordIcon ()));
      }
      else
        aActionCell.addChild (createEmptyAction ());

      // Delete user
      if (isActionAllowed (aWPEC, EWebPageFormAction.DELETE, aCurUser))
        aActionCell.addChild (createDeleteLink (aWPEC, aCurUser));
      else
        aActionCell.addChild (createEmptyAction ());

      // Undelete user
      if (isActionAllowed (aWPEC, EWebPageFormAction.UNDELETE, aCurUser))
        aActionCell.addChild (createUndeleteLink (aWPEC, aCurUser));
      else
        aActionCell.addChild (createEmptyAction ());
    }

    final HCNodeList aNodeList = new HCNodeList ();
    aNodeList.addChild (aTable);

    final DataTables aDataTables = BootstrapDataTables.createDefaultDataTables (aWPEC, aTable);
    aNodeList.addChild (aDataTables);

    return aNodeList;
  }

  @Override
  protected void showListOfExistingObjects (@Nonnull final WPECTYPE aWPEC)
  {
    final Locale aDisplayLocale = aWPEC.getDisplayLocale ();
    final HCNodeList aNodeList = aWPEC.getNodeList ();
    final UserManager aUserMgr = PhotonSecurityManager.getUserMgr ();

    // Toolbar on top
    final BootstrapButtonToolbar aToolbar = aNodeList.addAndReturnChild (new BootstrapButtonToolbar (aWPEC));
    aToolbar.addButtonNew (EText.BUTTON_CREATE_NEW_USER.getDisplayText (aDisplayLocale), createCreateURL (aWPEC));

    final BootstrapTabBox aTabBox = new BootstrapTabBox ();

    final Collection <? extends IUser> aActiveUsers = aUserMgr.getAllActiveUsers ();
    aTabBox.addTab ("active",
                    EText.TAB_ACTIVE.getDisplayTextWithArgs (aDisplayLocale, Integer.toString (aActiveUsers.size ())),
                    getTabWithUsers (aWPEC, aActiveUsers, getID () + "1"));

    final Collection <? extends IUser> aDisabledUsers = aUserMgr.getAllDisabledUsers ();
    aTabBox.addTab ("disabled",
                    EText.TAB_DISABLED.getDisplayTextWithArgs (aDisplayLocale,
                                                               Integer.toString (aDisabledUsers.size ())),
                    getTabWithUsers (aWPEC, aDisabledUsers, getID () + "2"));

    final Collection <? extends IUser> aDeletedUsers = aUserMgr.getAllDeletedUsers ();
    aTabBox.addTab ("deleted",
                    EText.TAB_DELETED.getDisplayTextWithArgs (aDisplayLocale, Integer.toString (aDeletedUsers.size ())),
                    getTabWithUsers (aWPEC, aDeletedUsers, getID () + "3"));
    aNodeList.addChild (aTabBox);
  }
}
