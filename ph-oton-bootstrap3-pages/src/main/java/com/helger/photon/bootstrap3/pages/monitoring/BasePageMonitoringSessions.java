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

import java.util.Locale;
import java.util.Map;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import org.joda.time.LocalDateTime;

import com.helger.commons.CGlobal;
import com.helger.commons.annotations.Nonempty;
import com.helger.commons.annotations.Translatable;
import com.helger.commons.collections.CollectionHelper;
import com.helger.commons.compare.ESortOrder;
import com.helger.commons.lang.CGStringHelper;
import com.helger.commons.name.IHasDisplayText;
import com.helger.commons.name.IHasDisplayTextWithArgs;
import com.helger.commons.scopes.domain.ISessionApplicationScope;
import com.helger.commons.scopes.domain.ISessionScope;
import com.helger.commons.scopes.mgr.ScopeSessionManager;
import com.helger.commons.text.IReadonlyMultiLingualText;
import com.helger.commons.text.impl.TextProvider;
import com.helger.commons.text.resolve.DefaultTextResolver;
import com.helger.commons.url.ISimpleURL;
import com.helger.datetime.PDTFactory;
import com.helger.datetime.format.PDTToString;
import com.helger.html.hc.IHCNode;
import com.helger.html.hc.IHCTable;
import com.helger.html.hc.html.HCA;
import com.helger.html.hc.html.HCCol;
import com.helger.html.hc.html.HCRow;
import com.helger.html.hc.impl.HCNodeList;
import com.helger.photon.bootstrap3.button.BootstrapButtonToolbar;
import com.helger.photon.bootstrap3.form.BootstrapForm;
import com.helger.photon.bootstrap3.form.BootstrapFormGroup;
import com.helger.photon.bootstrap3.form.BootstrapViewForm;
import com.helger.photon.bootstrap3.nav.BootstrapTabBox;
import com.helger.photon.bootstrap3.pages.AbstractBootstrapWebPageForm;
import com.helger.photon.bootstrap3.table.BootstrapTable;
import com.helger.photon.bootstrap3.table.BootstrapTableFormView;
import com.helger.photon.bootstrap3.uictrls.datatables.BootstrapDataTables;
import com.helger.photon.core.EPhotonCoreText;
import com.helger.photon.uicore.UITextFormatter;
import com.helger.photon.uicore.html.tabbox.ITabBox;
import com.helger.photon.uicore.html.table.IHCTableFormView;
import com.helger.photon.uicore.html.toolbar.IButtonToolbar;
import com.helger.photon.uicore.icon.EDefaultIcon;
import com.helger.photon.uicore.page.EWebPageFormAction;
import com.helger.photon.uicore.page.EWebPageText;
import com.helger.photon.uicore.page.IWebPageExecutionContext;
import com.helger.photon.uictrls.datatables.DataTables;
import com.helger.photon.uictrls.datatables.comparator.ComparatorDTDateTime;
import com.helger.photon.uictrls.datatables.comparator.ComparatorDTInteger;
import com.helger.validation.error.FormErrors;
import com.helger.web.scopes.domain.ISessionWebScope;

/**
 * Show information on all active sessions
 *
 * @author Philip Helger
 * @param <WPECTYPE>
 *        Web page execution context type
 */
public class BasePageMonitoringSessions <WPECTYPE extends IWebPageExecutionContext> extends AbstractBootstrapWebPageForm <ISessionScope, WPECTYPE>
{
  @Translatable
  protected static enum EText implements IHasDisplayText, IHasDisplayTextWithArgs
  {
    MSG_SESSION ("Session Kontext", "Session scope"),
    MSG_SESSION_APPLICATION_SCOPE ("Session Application Kontext ''{0}''", "Session app scope ''{0}''"),
    MSG_ID ("ID", "ID"),
    MSG_ATTRCOUNT ("Attribute", "Attributes"),
    MSG_LAST_ACCESS ("Letzter Zugriff", "Last access"),
    MSG_SCOPE_ID ("Kontext ID", "Scope ID"),
    MSG_SCOPE_VALID ("Kontext gültig?", "Scope valid?"),
    MSG_SCOPE_IN_DESTRUCTION ("Kontext in Zerstörung?", "Scope in destruction?"),
    MSG_SCOPE_DESTROYED ("Kontext zerstört?", "Scope destroyed?"),
    MSG_SESSION_APPLICATION_SCOPES ("Session Application Kontexte", "Session application scopes"),
    MSG_SCOPE_ATTRS ("Attribute", "Attributes"),
    MSG_SCOPE_CREATION_DT ("Erstellungszeit", "Creation date time"),
    MSG_SCOPE_LASTACCESS_DT ("Letzter Zugriff", "Last access date time"),
    MSG_SCOPE_SESSION_TIMEOUT ("Session Timeout", "Session timeout"),
    MSG_SCOPE_SESSION_TIMEOUT_TEXT ("{0} Sekunden (={1} Minuten)", "{0} seconds (={1} minutes)"),
    MSG_SCOPE_EXPIRATION_DT ("Geplanter Ablauf", "Planned expiration date time"),
    MSG_SCOPE_IS_NEW ("Neue Session?", "Is new session?"),
    MSG_NAME ("Name", "Wert"),
    MSG_TYPE ("Typ", "Type"),
    MSG_VALUE ("Wert", "Value");

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

  public BasePageMonitoringSessions (@Nonnull @Nonempty final String sID)
  {
    super (sID, EWebPageText.PAGE_NAME_MONITORING_SESSIONS.getAsMLT ());
  }

  public BasePageMonitoringSessions (@Nonnull @Nonempty final String sID, @Nonnull @Nonempty final String sName)
  {
    super (sID, sName);
  }

  public BasePageMonitoringSessions (@Nonnull @Nonempty final String sID,
                                     @Nonnull final String sName,
                                     @Nullable final String sDescription)
  {
    super (sID, sName, sDescription);
  }

  public BasePageMonitoringSessions (@Nonnull @Nonempty final String sID,
                                     @Nonnull final IReadonlyMultiLingualText aName,
                                     @Nullable final IReadonlyMultiLingualText aDescription)
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
    return true;
  }

  @Nonnull
  private IHCNode _getSessionScopeInfo (@Nonnull final WPECTYPE aWPEC, @Nonnull final ISessionScope aScope)
  {
    final Locale aDisplayLocale = aWPEC.getDisplayLocale ();
    final HCNodeList ret = new HCNodeList ();

    final BootstrapViewForm aTableScope = ret.addAndReturnChild (new BootstrapViewForm ());
    aTableScope.addFormGroup (new BootstrapFormGroup ().setLabel (EText.MSG_SCOPE_ID.getDisplayText (aDisplayLocale))
                                                       .setCtrl (aScope.getID ()));
    aTableScope.addFormGroup (new BootstrapFormGroup ().setLabel (EText.MSG_SCOPE_VALID.getDisplayText (aDisplayLocale))
                                                       .setCtrl (EPhotonCoreText.getYesOrNo (aScope.isValid (),
                                                                                             aDisplayLocale)));
    aTableScope.addFormGroup (new BootstrapFormGroup ().setLabel (EText.MSG_SCOPE_IN_DESTRUCTION.getDisplayText (aDisplayLocale))
                                                       .setCtrl (EPhotonCoreText.getYesOrNo (aScope.isInDestruction (),
                                                                                             aDisplayLocale)));
    aTableScope.addFormGroup (new BootstrapFormGroup ().setLabel (EText.MSG_SCOPE_DESTROYED.getDisplayText (aDisplayLocale))
                                                       .setCtrl (EPhotonCoreText.getYesOrNo (aScope.isDestroyed (),
                                                                                             aDisplayLocale)));
    aTableScope.addFormGroup (new BootstrapFormGroup ().setLabel (EText.MSG_SESSION_APPLICATION_SCOPES.getDisplayText (aDisplayLocale))
                                                       .setCtrl (Integer.toString (aScope.getSessionApplicationScopeCount ())));
    aTableScope.addFormGroup (new BootstrapFormGroup ().setLabel (EText.MSG_SCOPE_ATTRS.getDisplayText (aDisplayLocale))
                                                       .setCtrl (Integer.toString (aScope.getAttributeCount ())));

    if (aScope instanceof ISessionWebScope)
    {
      final ISessionWebScope aWebScope = (ISessionWebScope) aScope;
      final LocalDateTime aCreationDT = PDTFactory.createLocalDateTimeFromMillis (aWebScope.getCreationTime ());
      final LocalDateTime aLastAccessDT = PDTFactory.createLocalDateTimeFromMillis (aWebScope.getLastAccessedTime ());

      aTableScope.addFormGroup (new BootstrapFormGroup ().setLabel (EText.MSG_SCOPE_CREATION_DT.getDisplayText (aDisplayLocale))
                                                         .setCtrl (PDTToString.getAsString (aCreationDT, aDisplayLocale)));
      aTableScope.addFormGroup (new BootstrapFormGroup ().setLabel (EText.MSG_SCOPE_LASTACCESS_DT.getDisplayText (aDisplayLocale))
                                                         .setCtrl (PDTToString.getAsString (aLastAccessDT,
                                                                                            aDisplayLocale)));
      aTableScope.addFormGroup (new BootstrapFormGroup ().setLabel (EText.MSG_SCOPE_SESSION_TIMEOUT.getDisplayText (aDisplayLocale))
                                                         .setCtrl (EText.MSG_SCOPE_SESSION_TIMEOUT_TEXT.getDisplayTextWithArgs (aDisplayLocale,
                                                                                                                                Long.toString (aWebScope.getMaxInactiveInterval ()),
                                                                                                                                Long.toString (aWebScope.getMaxInactiveInterval () /
                                                                                                                                               CGlobal.SECONDS_PER_MINUTE))));
      aTableScope.addFormGroup (new BootstrapFormGroup ().setLabel (EText.MSG_SCOPE_EXPIRATION_DT.getDisplayText (aDisplayLocale))
                                                         .setCtrl (PDTToString.getAsString (aLastAccessDT.plusSeconds ((int) aWebScope.getMaxInactiveInterval ()),
                                                                                            aDisplayLocale)));
      aTableScope.addFormGroup (new BootstrapFormGroup ().setLabel (EText.MSG_SCOPE_IS_NEW.getDisplayText (aDisplayLocale))
                                                         .setCtrl (EPhotonCoreText.getYesOrNo (aWebScope.isNew (),
                                                                                               aDisplayLocale)));
    }

    // All scope attributes
    final IHCTableFormView <?> aTableAttrs = new BootstrapTableFormView (HCCol.star (), HCCol.star (), HCCol.star ()).setID ("sessionscope-" +
                                                                                                                             aScope.getID ());
    aTableAttrs.addHeaderRow ().addCells (EText.MSG_NAME.getDisplayText (aDisplayLocale),
                                          EText.MSG_TYPE.getDisplayText (aDisplayLocale),
                                          EText.MSG_VALUE.getDisplayText (aDisplayLocale));
    for (final Map.Entry <String, Object> aEntry : aScope.getAllAttributes ().entrySet ())
      aTableAttrs.addBodyRow ()
                 .addCell (aEntry.getKey ())
                 .addCell (CGStringHelper.getClassLocalName (aEntry.getValue ()))
                 .addCell (UITextFormatter.getToStringContent (aEntry.getValue ()));
    ret.addChild (aTableAttrs);

    final DataTables aDataTables = BootstrapDataTables.createDefaultDataTables (aWPEC, aTableAttrs);
    aDataTables.setInitialSorting (0, ESortOrder.ASCENDING);
    ret.addChild (aDataTables);

    return ret;
  }

  @Nonnull
  private IHCNode _getSessionApplicationScopeInfo (@Nonnull final WPECTYPE aWPEC,
                                                   @Nonnull final ISessionApplicationScope aScope)
  {
    final Locale aDisplayLocale = aWPEC.getDisplayLocale ();
    final HCNodeList aNodeList = new HCNodeList ();

    final BootstrapViewForm aTableScope = new BootstrapViewForm ();
    aTableScope.addFormGroup (new BootstrapFormGroup ().setLabel (EText.MSG_SCOPE_ID.getDisplayText (aDisplayLocale))
                                                       .setCtrl (aScope.getID ()));
    aTableScope.addFormGroup (new BootstrapFormGroup ().setLabel (EText.MSG_SCOPE_VALID.getDisplayText (aDisplayLocale))
                                                       .setCtrl (EPhotonCoreText.getYesOrNo (aScope.isValid (),
                                                                                             aDisplayLocale)));
    aTableScope.addFormGroup (new BootstrapFormGroup ().setLabel (EText.MSG_SCOPE_IN_DESTRUCTION.getDisplayText (aDisplayLocale))
                                                       .setCtrl (EPhotonCoreText.getYesOrNo (aScope.isInDestruction (),
                                                                                             aDisplayLocale)));
    aTableScope.addFormGroup (new BootstrapFormGroup ().setLabel (EText.MSG_SCOPE_DESTROYED.getDisplayText (aDisplayLocale))
                                                       .setCtrl (EPhotonCoreText.getYesOrNo (aScope.isDestroyed (),
                                                                                             aDisplayLocale)));
    aTableScope.addFormGroup (new BootstrapFormGroup ().setLabel (EText.MSG_SCOPE_ATTRS.getDisplayText (aDisplayLocale))
                                                       .setCtrl (Integer.toString (aScope.getAttributeCount ())));
    aNodeList.addChild (aTableScope);

    // All scope attributes
    final IHCTableFormView <?> aTableAttrs = new BootstrapTableFormView (HCCol.star (), HCCol.star (), HCCol.star ()).setID ("sessionappscope" +
                                                                                                                             aScope.getID ());
    aTableAttrs.addHeaderRow ().addCells (EText.MSG_NAME.getDisplayText (aDisplayLocale),
                                          EText.MSG_TYPE.getDisplayText (aDisplayLocale),
                                          EText.MSG_VALUE.getDisplayText (aDisplayLocale));
    for (final Map.Entry <String, Object> aEntry : aScope.getAllAttributes ().entrySet ())
      aTableAttrs.addBodyRow ()
                 .addCell (aEntry.getKey ())
                 .addCell (CGStringHelper.getClassLocalName (aEntry.getValue ()))
                 .addCell (UITextFormatter.getToStringContent (aEntry.getValue ()));
    aNodeList.addChild (aTableAttrs);

    final DataTables aDataTables = BootstrapDataTables.createDefaultDataTables (aWPEC, aTableAttrs);
    aDataTables.setInitialSorting (0, ESortOrder.ASCENDING);
    aNodeList.addChild (aDataTables);

    return aNodeList;
  }

  @Override
  protected void showSelectedObject (@Nonnull final WPECTYPE aWPEC, @Nonnull final ISessionScope aScope)
  {
    final HCNodeList aNodeList = aWPEC.getNodeList ();
    final Locale aDisplayLocale = aWPEC.getDisplayLocale ();

    // Refresh button
    final IButtonToolbar <?> aToolbar = new BootstrapButtonToolbar (aWPEC);
    aToolbar.addButton (EPhotonCoreText.BACK_TO_OVERVIEW.getDisplayText (aDisplayLocale),
                        aWPEC.getSelfHref (),
                        EDefaultIcon.BACK_TO_LIST);
    aToolbar.addButton (EPhotonCoreText.BUTTON_REFRESH.getDisplayText (aDisplayLocale),
                        createViewURL (aWPEC, aScope),
                        EDefaultIcon.REFRESH);
    aNodeList.addChild (aToolbar);

    final ITabBox <?> aTabBox = new BootstrapTabBox ();
    aTabBox.addTab (EText.MSG_SESSION.getDisplayText (aDisplayLocale), _getSessionScopeInfo (aWPEC, aScope));
    for (final ISessionApplicationScope aSessionAppScope : CollectionHelper.getSortedByKey (aScope.getAllSessionApplicationScopes ())
                                                                           .values ())
      aTabBox.addTab (EText.MSG_SESSION_APPLICATION_SCOPES.getDisplayTextWithArgs (aDisplayLocale,
                                                                                   aSessionAppScope.getID ()),
                      _getSessionApplicationScopeInfo (aWPEC, aSessionAppScope));
    aNodeList.addChild (aTabBox);
  }

  @Override
  protected void validateAndSaveInputParameters (@Nonnull final WPECTYPE aWPEC,
                                                 @Nullable final ISessionScope aSelectedObject,
                                                 @Nonnull final FormErrors aFormErrors,
                                                 @Nonnull final EWebPageFormAction eFormAction)
  {
    throw new UnsupportedOperationException ();
  }

  @Override
  protected void showInputForm (@Nonnull final WPECTYPE aWPEC,
                                @Nullable final ISessionScope aSelectedObject,
                                @Nonnull final BootstrapForm aForm,
                                @Nonnull final EWebPageFormAction eFormAction,
                                @Nonnull final FormErrors aFormErrors)
  {
    throw new UnsupportedOperationException ();
  }

  @Override
  protected void showListOfExistingObjects (@Nonnull final WPECTYPE aWPEC)
  {
    final Locale aDisplayLocale = aWPEC.getDisplayLocale ();
    final HCNodeList aNodeList = aWPEC.getNodeList ();

    // Refresh button
    final IButtonToolbar <?> aToolbar = new BootstrapButtonToolbar (aWPEC);
    aToolbar.addButton (EPhotonCoreText.BUTTON_REFRESH.getDisplayText (aDisplayLocale),
                        aWPEC.getSelfHref (),
                        EDefaultIcon.REFRESH);
    aNodeList.addChild (aToolbar);

    final IHCTable <?> aTable = new BootstrapTable (HCCol.star (),
                                                    new HCCol (60),
                                                    new HCCol (COLUMN_WIDTH_DATETIME),
                                                    createActionCol (1)).setID (getID ());
    aTable.addHeaderRow ().addCells (EText.MSG_ID.getDisplayText (aDisplayLocale),
                                     EText.MSG_ATTRCOUNT.getDisplayText (aDisplayLocale),
                                     EText.MSG_LAST_ACCESS.getDisplayText (aDisplayLocale),
                                     EPhotonCoreText.ACTIONS.getDisplayText (aDisplayLocale));
    for (final ISessionScope aSessionScope : ScopeSessionManager.getInstance ().getAllSessionScopes ())
    {
      final ISessionWebScope aWebScope = aSessionScope instanceof ISessionWebScope ? (ISessionWebScope) aSessionScope
                                                                                  : null;
      final ISimpleURL aViewLink = createViewURL (aWPEC, aSessionScope);

      final HCRow aRow = aTable.addBodyRow ();
      aRow.addCell (new HCA (aViewLink).addChild (aSessionScope.getID ()));
      aRow.addCell (Integer.toString (aSessionScope.getAttributeCount ()));
      if (aWebScope != null)
        aRow.addCell (PDTToString.getAsString (PDTFactory.createLocalDateTimeFromMillis (aWebScope.getLastAccessedTime ()),
                                               aDisplayLocale));
      else
        aRow.addCell ();

      // Actions
      aRow.addCell ();
    }

    aNodeList.addChild (aTable);

    final DataTables aDataTables = BootstrapDataTables.createDefaultDataTables (aWPEC, aTable);
    aDataTables.getOrCreateColumnOfTarget (1)
               .addClass (CSS_CLASS_RIGHT)
               .setComparator (new ComparatorDTInteger (aDisplayLocale));
    aDataTables.getOrCreateColumnOfTarget (2)
               .addClass (CSS_CLASS_RIGHT)
               .setComparator (new ComparatorDTDateTime (aDisplayLocale));
    aDataTables.getOrCreateColumnOfTarget (3).addClass (CSS_CLASS_ACTION_COL).setSortable (false);
    aDataTables.setInitialSorting (2, ESortOrder.DESCENDING);
    aNodeList.addChild (aDataTables);
  }
}
