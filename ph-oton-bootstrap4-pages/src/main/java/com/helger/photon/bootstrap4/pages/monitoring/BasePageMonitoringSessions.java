/**
 * Copyright (C) 2018-2020 Philip Helger (www.helger.com)
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
package com.helger.photon.bootstrap4.pages.monitoring;

import java.io.Serializable;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Locale;
import java.util.Map;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.helger.commons.CGlobal;
import com.helger.commons.annotation.Nonempty;
import com.helger.commons.annotation.Translatable;
import com.helger.commons.compare.ESortOrder;
import com.helger.commons.datetime.PDTFactory;
import com.helger.commons.datetime.PDTToString;
import com.helger.commons.lang.ClassHelper;
import com.helger.commons.serialize.SerializationHelper;
import com.helger.commons.text.IMultilingualText;
import com.helger.commons.text.display.IHasDisplayTextWithArgs;
import com.helger.commons.text.resolve.DefaultTextResolver;
import com.helger.commons.text.util.TextHelper;
import com.helger.commons.url.ISimpleURL;
import com.helger.html.hc.IHCNode;
import com.helger.html.hc.html.tabular.HCRow;
import com.helger.html.hc.html.tabular.HCTable;
import com.helger.html.hc.html.textlevel.HCA;
import com.helger.html.hc.impl.HCNodeList;
import com.helger.photon.bootstrap4.buttongroup.BootstrapButtonToolbar;
import com.helger.photon.bootstrap4.form.BootstrapForm;
import com.helger.photon.bootstrap4.form.BootstrapFormGroup;
import com.helger.photon.bootstrap4.form.BootstrapViewForm;
import com.helger.photon.bootstrap4.pages.AbstractBootstrapWebPageForm;
import com.helger.photon.bootstrap4.uictrls.datatables.BootstrapDTColAction;
import com.helger.photon.bootstrap4.uictrls.datatables.BootstrapDataTables;
import com.helger.photon.core.EPhotonCoreText;
import com.helger.photon.core.form.FormErrorList;
import com.helger.photon.security.CSecurity;
import com.helger.photon.security.util.SecurityHelper;
import com.helger.photon.uicore.UITextFormatter;
import com.helger.photon.uicore.icon.EDefaultIcon;
import com.helger.photon.uicore.page.EWebPageFormAction;
import com.helger.photon.uicore.page.EWebPageText;
import com.helger.photon.uicore.page.IWebPageExecutionContext;
import com.helger.photon.uictrls.datatables.DataTables;
import com.helger.photon.uictrls.datatables.column.DTCol;
import com.helger.photon.uictrls.datatables.column.EDTColType;
import com.helger.scope.ISessionScope;
import com.helger.scope.mgr.ScopeSessionManager;
import com.helger.web.scope.ISessionWebScope;
import com.helger.web.scope.mgr.WebScopeManager;

/**
 * Show information on all active sessions
 *
 * @author Philip Helger
 * @param <WPECTYPE>
 *        Web page execution context type
 */
public class BasePageMonitoringSessions <WPECTYPE extends IWebPageExecutionContext> extends
                                        AbstractBootstrapWebPageForm <ISessionScope, WPECTYPE>
{
  @Translatable
  protected enum EText implements IHasDisplayTextWithArgs
  {
    MSG_SESSION ("Session Kontext", "Session scope"),
    MSG_ID ("ID", "ID"),
    MSG_ATTRCOUNT ("Attribute", "Attributes"),
    MSG_LAST_ACCESS ("Letzter Zugriff", "Last access"),
    MSG_SCOPE_ID ("Kontext ID", "Scope ID"),
    MSG_SCOPE_MY_SESSION ("Meine Session?", "My session?"),
    MSG_SCOPE_VALID ("Kontext gültig?", "Scope valid?"),
    MSG_SCOPE_IN_DESTRUCTION ("Kontext in Zerstörung?", "Scope in destruction?"),
    MSG_SCOPE_DESTROYED ("Kontext zerstört?", "Scope destroyed?"),
    MSG_SCOPE_ATTRS ("Attribute", "Attributes"),
    MSG_SCOPE_CREATION_DT ("Erstellungszeit", "Creation date time"),
    MSG_SCOPE_LASTACCESS_DT ("Letzter Zugriff", "Last access date time"),
    MSG_SCOPE_SESSION_AGE ("Session-Alter", "Session age"),
    MSG_SCOPE_SESSION_TIMEOUT ("Session Timeout", "Session timeout"),
    MSG_SCOPE_SESSION_TIMEOUT_TEXT ("{0} Sekunden (={1} Minuten)", "{0} seconds (={1} minutes)"),
    MSG_SCOPE_EXPIRATION_DT ("Geplanter Ablauf", "Planned expiration date time"),
    MSG_SCOPE_IS_NEW ("Neue Session?", "Is new session?"),
    MSG_NAME ("Name", "Wert"),
    MSG_TYPE ("Typ", "Type"),
    MSG_VALUE ("Wert", "Value"),
    MSG_MY_SESSION (" [ich]", " [me]");

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

  public BasePageMonitoringSessions (@Nonnull @Nonempty final String sID)
  {
    super (sID, EWebPageText.PAGE_NAME_MONITORING_SESSIONS.getAsMLT ());
  }

  public BasePageMonitoringSessions (@Nonnull @Nonempty final String sID, @Nonnull @Nonempty final String sName)
  {
    super (sID, sName);
  }

  public BasePageMonitoringSessions (@Nonnull @Nonempty final String sID, @Nonnull final String sName, @Nullable final String sDescription)
  {
    super (sID, sName, sDescription);
  }

  public BasePageMonitoringSessions (@Nonnull @Nonempty final String sID,
                                     @Nonnull final IMultilingualText aName,
                                     @Nullable final IMultilingualText aDescription)
  {
    super (sID, aName, aDescription);
  }

  @Override
  @Nullable
  protected ISessionScope getSelectedObject (@Nonnull final WPECTYPE aWPEC, @Nullable final String sID)
  {
    return ScopeSessionManager.getInstance ().getSessionScopeOfID (sID);
  }

  @Override
  protected boolean isActionAllowed (@Nonnull final WPECTYPE aWPEC,
                                     @Nonnull final EWebPageFormAction eFormAction,
                                     @Nullable final ISessionScope aSelectedObject)
  {
    if (eFormAction.isEdit ())
      return false;
    return super.isActionAllowed (aWPEC, eFormAction, aSelectedObject);
  }

  @Nonnull
  private IHCNode _getSessionScopeInfo (@Nonnull final WPECTYPE aWPEC, @Nonnull final ISessionScope aScope)
  {
    final Locale aDisplayLocale = aWPEC.getDisplayLocale ();
    final HCNodeList ret = new HCNodeList ();

    final boolean bIsMySession = aScope.getID ().equals (WebScopeManager.getSessionScope ().getID ());
    final BootstrapViewForm aViewForm = ret.addAndReturnChild (new BootstrapViewForm ());

    aViewForm.addFormGroup (new BootstrapFormGroup ().setLabel (EText.MSG_SCOPE_ID.getDisplayText (aDisplayLocale))
                                                     .setCtrl (aScope.getID ()));
    aViewForm.addFormGroup (new BootstrapFormGroup ().setLabel (EText.MSG_SCOPE_MY_SESSION.getDisplayText (aDisplayLocale))
                                                     .setCtrl (EPhotonCoreText.getYesOrNo (bIsMySession, aDisplayLocale)));
    aViewForm.addFormGroup (new BootstrapFormGroup ().setLabel (EText.MSG_SCOPE_VALID.getDisplayText (aDisplayLocale))
                                                     .setCtrl (EPhotonCoreText.getYesOrNo (aScope.isValid (), aDisplayLocale)));
    aViewForm.addFormGroup (new BootstrapFormGroup ().setLabel (EText.MSG_SCOPE_IN_DESTRUCTION.getDisplayText (aDisplayLocale))
                                                     .setCtrl (EPhotonCoreText.getYesOrNo (aScope.isInDestruction (), aDisplayLocale)));
    aViewForm.addFormGroup (new BootstrapFormGroup ().setLabel (EText.MSG_SCOPE_DESTROYED.getDisplayText (aDisplayLocale))
                                                     .setCtrl (EPhotonCoreText.getYesOrNo (aScope.isDestroyed (), aDisplayLocale)));
    aViewForm.addFormGroup (new BootstrapFormGroup ().setLabel (EText.MSG_SCOPE_ATTRS.getDisplayText (aDisplayLocale))
                                                     .setCtrl (Integer.toString (aScope.attrs ().size ())));

    if (aScope instanceof ISessionWebScope)
    {
      final ISessionWebScope aWebScope = (ISessionWebScope) aScope;
      final LocalDateTime aCreationDT = PDTFactory.createLocalDateTime (aWebScope.getSession ().getCreationTime ());
      final LocalDateTime aLastAccessDT = PDTFactory.createLocalDateTime (aWebScope.getSession ().getLastAccessedTime ());

      aViewForm.addFormGroup (new BootstrapFormGroup ().setLabel (EText.MSG_SCOPE_CREATION_DT.getDisplayText (aDisplayLocale))
                                                       .setCtrl (PDTToString.getAsString (aCreationDT, aDisplayLocale)));
      aViewForm.addFormGroup (new BootstrapFormGroup ().setLabel (EText.MSG_SCOPE_LASTACCESS_DT.getDisplayText (aDisplayLocale))
                                                       .setCtrl (PDTToString.getAsString (aLastAccessDT, aDisplayLocale)));
      aViewForm.addFormGroup (new BootstrapFormGroup ().setLabel (EText.MSG_SCOPE_SESSION_AGE.getDisplayText (aDisplayLocale))
                                                       .setCtrl (Duration.between (aCreationDT, PDTFactory.getCurrentLocalDateTime ())
                                                                         .toString ()));
      aViewForm.addFormGroup (new BootstrapFormGroup ().setLabel (EText.MSG_SCOPE_SESSION_TIMEOUT.getDisplayText (aDisplayLocale))
                                                       .setCtrl (EText.MSG_SCOPE_SESSION_TIMEOUT_TEXT.getDisplayTextWithArgs (aDisplayLocale,
                                                                                                                              Long.toString (aWebScope.getSession ()
                                                                                                                                                      .getMaxInactiveInterval ()),
                                                                                                                              Long.toString (aWebScope.getSession ()
                                                                                                                                                      .getMaxInactiveInterval () /
                                                                                                                                             CGlobal.SECONDS_PER_MINUTE))));
      aViewForm.addFormGroup (new BootstrapFormGroup ().setLabel (EText.MSG_SCOPE_EXPIRATION_DT.getDisplayText (aDisplayLocale))
                                                       .setCtrl (PDTToString.getAsString (aLastAccessDT.plusSeconds (aWebScope.getSession ()
                                                                                                                              .getMaxInactiveInterval ()),
                                                                                          aDisplayLocale)));
      aViewForm.addFormGroup (new BootstrapFormGroup ().setLabel (EText.MSG_SCOPE_IS_NEW.getDisplayText (aDisplayLocale))
                                                       .setCtrl (EPhotonCoreText.getYesOrNo (aWebScope.getSession ().isNew (),
                                                                                             aDisplayLocale)));
    }

    // All scope attributes
    final boolean bIsAdmin = SecurityHelper.isCurrentUserAssignedToUserGroup (CSecurity.USERGROUP_ADMINISTRATORS_ID);

    final HCTable aTableAttrs = new HCTable (new DTCol (EText.MSG_NAME.getDisplayText (aDisplayLocale)).setInitialSorting (ESortOrder.ASCENDING),
                                             new DTCol (EText.MSG_TYPE.getDisplayText (aDisplayLocale)),
                                             new DTCol (EText.MSG_VALUE.getDisplayText (aDisplayLocale))).setID ("sessionscope-" + aScope.getID ());
    for (final Map.Entry <String, Object> aEntry : aScope.attrs ().entrySet ())
    {
      final Object aValue = aEntry.getValue ();

      final HCRow aRow = aTableAttrs.addBodyRow ();
      aRow.addCell (aEntry.getKey ())
          .addCell (ClassHelper.getClassLocalName (aEntry.getValue ()) + (bIsAdmin ? _getUISize (aValue) : ""))
          .addCell (UITextFormatter.getToStringContent (aValue));
    }
    ret.addChild (aTableAttrs);

    final DataTables aDataTables = BootstrapDataTables.createDefaultDataTables (aWPEC, aTableAttrs);
    ret.addChild (aDataTables);

    return ret;
  }

  @Nonnull
  @Nonempty
  private static String _getUISize (final Object aValue)
  {
    try
    {
      final int nLen = SerializationHelper.getSerializedByteArray ((Serializable) aValue).length;
      return " [" + nLen + " Bytes]";
    }
    catch (final RuntimeException ex)
    {
      return " [unknown - " + ex.getMessage () + "]";
    }
  }

  @Override
  protected void showSelectedObject (@Nonnull final WPECTYPE aWPEC, @Nonnull final ISessionScope aScope)
  {
    final HCNodeList aNodeList = aWPEC.getNodeList ();
    final Locale aDisplayLocale = aWPEC.getDisplayLocale ();

    // Refresh button
    final BootstrapButtonToolbar aToolbar = new BootstrapButtonToolbar (aWPEC);
    aToolbar.addButton (EPhotonCoreText.BACK_TO_OVERVIEW.getDisplayText (aDisplayLocale), aWPEC.getSelfHref (), EDefaultIcon.BACK_TO_LIST);
    aToolbar.addButton (EPhotonCoreText.BUTTON_REFRESH.getDisplayText (aDisplayLocale),
                        createViewURL (aWPEC, aScope),
                        EDefaultIcon.REFRESH);
    aNodeList.addChild (aToolbar);

    aNodeList.addChild (_getSessionScopeInfo (aWPEC, aScope));
  }

  @Override
  protected void validateAndSaveInputParameters (@Nonnull final WPECTYPE aWPEC,
                                                 @Nullable final ISessionScope aSelectedObject,
                                                 @Nonnull final FormErrorList aFormErrors,
                                                 @Nonnull final EWebPageFormAction eFormAction)
  {
    throw new UnsupportedOperationException ();
  }

  @Override
  protected void showInputForm (@Nonnull final WPECTYPE aWPEC,
                                @Nullable final ISessionScope aSelectedObject,
                                @Nonnull final BootstrapForm aForm,
                                final boolean bIsFormSubmitted,
                                @Nonnull final EWebPageFormAction eFormAction,
                                @Nonnull final FormErrorList aFormErrors)
  {
    throw new UnsupportedOperationException ();
  }

  @Override
  protected void showListOfExistingObjects (@Nonnull final WPECTYPE aWPEC)
  {
    final Locale aDisplayLocale = aWPEC.getDisplayLocale ();
    final HCNodeList aNodeList = aWPEC.getNodeList ();

    // Refresh button
    final BootstrapButtonToolbar aToolbar = new BootstrapButtonToolbar (aWPEC);
    aToolbar.addButton (EPhotonCoreText.BUTTON_REFRESH.getDisplayText (aDisplayLocale), aWPEC.getSelfHref (), EDefaultIcon.REFRESH);
    aNodeList.addChild (aToolbar);

    final HCTable aTable = new HCTable (new DTCol (EText.MSG_ID.getDisplayText (aDisplayLocale)),
                                        new DTCol (EText.MSG_ATTRCOUNT.getDisplayText (aDisplayLocale)).setDisplayType (EDTColType.INT,
                                                                                                                        aDisplayLocale),
                                        new DTCol (EText.MSG_LAST_ACCESS.getDisplayText (aDisplayLocale)).setDisplayType (EDTColType.DATETIME,
                                                                                                                          aDisplayLocale)
                                                                                                         .setInitialSorting (ESortOrder.DESCENDING),
                                        new BootstrapDTColAction (aDisplayLocale)).setID (getID ());

    final String sMySessionID = WebScopeManager.getSessionScope ().getID ();

    for (final ISessionScope aSessionScope : ScopeSessionManager.getInstance ().getAllSessionScopes ())
    {
      final ISessionWebScope aWebScope = aSessionScope instanceof ISessionWebScope ? (ISessionWebScope) aSessionScope : null;
      final ISimpleURL aViewLink = createViewURL (aWPEC, aSessionScope);
      final boolean bIsMySession = aSessionScope.getID ().equals (sMySessionID);

      final HCRow aRow = aTable.addBodyRow ();
      aRow.addCell (new HCA (aViewLink).addChild (aSessionScope.getID () +
                                                  (bIsMySession ? EText.MSG_MY_SESSION.getDisplayText (aDisplayLocale) : "")));
      aRow.addCell (Integer.toString (aSessionScope.attrs ().size ()));
      if (aWebScope != null)
        aRow.addCell (PDTToString.getAsString (PDTFactory.createLocalDateTime (aWebScope.getSession ().getLastAccessedTime ()),
                                               aDisplayLocale));
      else
        aRow.addCell ();

      // Actions
      aRow.addCell ();
    }

    aNodeList.addChild (aTable);

    final DataTables aDataTables = BootstrapDataTables.createDefaultDataTables (aWPEC, aTable);
    aNodeList.addChild (aDataTables);
  }
}
