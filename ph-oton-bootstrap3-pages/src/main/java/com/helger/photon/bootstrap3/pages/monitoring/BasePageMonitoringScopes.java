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

import com.helger.commons.annotation.Nonempty;
import com.helger.commons.annotation.Translatable;
import com.helger.commons.collection.CollectionHelper;
import com.helger.commons.compare.ESortOrder;
import com.helger.commons.lang.ClassHelper;
import com.helger.commons.scope.IApplicationScope;
import com.helger.commons.text.IMultilingualText;
import com.helger.commons.text.display.IHasDisplayText;
import com.helger.commons.text.display.IHasDisplayTextWithArgs;
import com.helger.commons.text.resolve.DefaultTextResolver;
import com.helger.commons.text.util.TextHelper;
import com.helger.html.hc.IHCNode;
import com.helger.html.hc.impl.HCNodeList;
import com.helger.html.hchtml.tabular.HCTable;
import com.helger.photon.bootstrap3.button.BootstrapButtonToolbar;
import com.helger.photon.bootstrap3.form.BootstrapFormGroup;
import com.helger.photon.bootstrap3.form.BootstrapViewForm;
import com.helger.photon.bootstrap3.nav.BootstrapTabBox;
import com.helger.photon.bootstrap3.pages.AbstractBootstrapWebPage;
import com.helger.photon.bootstrap3.uictrls.datatables.BootstrapDataTables;
import com.helger.photon.core.EPhotonCoreText;
import com.helger.photon.uicore.UITextFormatter;
import com.helger.photon.uicore.html.tabbox.ITabBox;
import com.helger.photon.uicore.html.toolbar.IButtonToolbar;
import com.helger.photon.uicore.icon.EDefaultIcon;
import com.helger.photon.uicore.page.EWebPageText;
import com.helger.photon.uicore.page.IWebPageExecutionContext;
import com.helger.photon.uictrls.datatables.DTCol;
import com.helger.photon.uictrls.datatables.DataTables;
import com.helger.web.scope.IGlobalWebScope;
import com.helger.web.scope.mgr.WebScopeManager;

/**
 * Show information on global and application scopes
 *
 * @author Philip Helger
 * @param <WPECTYPE>
 *        Web page execution context type
 */
public class BasePageMonitoringScopes <WPECTYPE extends IWebPageExecutionContext> extends AbstractBootstrapWebPage <WPECTYPE>
{
  @Translatable
  protected static enum EText implements IHasDisplayText,IHasDisplayTextWithArgs
  {
   MSG_GLOBAL_SCOPE ("Globaler Kontext ''{0}''", "Global scope ''{0}''"),
   MSG_APPLICATION_SCOPE ("Application Kontext ''{0}''", "Application scope ''{0}''"),
   MSG_SCOPE_ID ("Kontext ID", "Scope ID"),
   MSG_SCOPE_VALID ("Kontext gültig?", "Scope valid?"),
   MSG_SCOPE_IN_DESTRUCTION ("Kontext in Zerstörung?", "Scope in destruction?"),
   MSG_SCOPE_DESTROYED ("Kontext zerstört?", "Scope destroyed?"),
   MSG_APPLICATION_SCOPES ("Application Kontexte", "Application scopes"),
   MSG_SCOPE_ATTRS ("Attribute", "Attributes"),
   MSG_NAME ("Name", "Wert"),
   MSG_TYPE ("Typ", "Type"),
   MSG_VALUE ("Wert", "Value");

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

  public BasePageMonitoringScopes (@Nonnull @Nonempty final String sID)
  {
    super (sID, EWebPageText.PAGE_NAME_MONITORING_SCOPES.getAsMLT ());
  }

  public BasePageMonitoringScopes (@Nonnull @Nonempty final String sID, @Nonnull @Nonempty final String sName)
  {
    super (sID, sName);
  }

  public BasePageMonitoringScopes (@Nonnull @Nonempty final String sID,
                                   @Nonnull final String sName,
                                   @Nullable final String sDescription)
  {
    super (sID, sName, sDescription);
  }

  public BasePageMonitoringScopes (@Nonnull @Nonempty final String sID,
                                   @Nonnull final IMultilingualText aName,
                                   @Nullable final IMultilingualText aDescription)
  {
    super (sID, aName, aDescription);
  }

  @Nonnull
  private IHCNode _getGlobalScopeInfo (@Nonnull final WPECTYPE aWPEC, @Nonnull final IGlobalWebScope aScope)
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
    aTableScope.addFormGroup (new BootstrapFormGroup ().setLabel (EText.MSG_APPLICATION_SCOPES.getDisplayText (aDisplayLocale))
                                                       .setCtrl (Integer.toString (aScope.getApplicationScopeCount ())));
    aTableScope.addFormGroup (new BootstrapFormGroup ().setLabel (EText.MSG_SCOPE_ATTRS.getDisplayText (aDisplayLocale))
                                                       .setCtrl (Integer.toString (aScope.getAttributeCount ())));
    aNodeList.addChild (aTableScope);

    // All scope attributes
    final HCTable aTableAttrs = new HCTable (new DTCol (EText.MSG_NAME.getDisplayText (aDisplayLocale)).setInitialSorting (ESortOrder.ASCENDING),
                                             new DTCol (EText.MSG_TYPE.getDisplayText (aDisplayLocale)),
                                             new DTCol (EText.MSG_VALUE.getDisplayText (aDisplayLocale))).setID ("globalscope");
    for (final Map.Entry <String, Object> aEntry : aScope.getAllAttributes ().entrySet ())
      aTableAttrs.addBodyRow ()
                 .addCell (aEntry.getKey ())
                 .addCell (ClassHelper.getClassLocalName (aEntry.getValue ()))
                 .addCell (UITextFormatter.getToStringContent (aEntry.getValue ()));
    aNodeList.addChild (aTableAttrs);

    final DataTables aDataTables = BootstrapDataTables.createDefaultDataTables (aWPEC, aTableAttrs);
    aNodeList.addChild (aDataTables);

    return aNodeList;
  }

  @Nonnull
  private IHCNode _getApplicationScopeInfo (@Nonnull final WPECTYPE aWPEC, @Nonnull final IApplicationScope aScope)
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
    final HCTable aTableAttrs = new HCTable (new DTCol (EText.MSG_NAME.getDisplayText (aDisplayLocale)).setInitialSorting (ESortOrder.ASCENDING),
                                             new DTCol (EText.MSG_TYPE.getDisplayText (aDisplayLocale)),
                                             new DTCol (EText.MSG_VALUE.getDisplayText (aDisplayLocale))).setID ("appscope" +
                                                                                                                 aScope.getID ());
    for (final Map.Entry <String, Object> aEntry : aScope.getAllAttributes ().entrySet ())
      aTableAttrs.addBodyRow ()
                 .addCell (aEntry.getKey ())
                 .addCell (ClassHelper.getClassLocalName (aEntry.getValue ()))
                 .addCell (UITextFormatter.getToStringContent (aEntry.getValue ()));
    aNodeList.addChild (aTableAttrs);

    final DataTables aDataTables = BootstrapDataTables.createDefaultDataTables (aWPEC, aTableAttrs);
    aNodeList.addChild (aDataTables);

    return aNodeList;
  }

  @Override
  protected void fillContent (@Nonnull final WPECTYPE aWPEC)
  {
    final HCNodeList aNodeList = aWPEC.getNodeList ();
    final Locale aDisplayLocale = aWPEC.getDisplayLocale ();
    final IGlobalWebScope aGlobalScope = WebScopeManager.getGlobalScope ();

    // Refresh button
    final IButtonToolbar <?> aToolbar = new BootstrapButtonToolbar (aWPEC);
    aToolbar.addButton (EPhotonCoreText.BUTTON_REFRESH.getDisplayText (aDisplayLocale),
                        aWPEC.getSelfHref (),
                        EDefaultIcon.REFRESH);
    aNodeList.addChild (aToolbar);

    final ITabBox <?> aTabBox = new BootstrapTabBox ();
    // Global scope
    aTabBox.addTab (EText.MSG_GLOBAL_SCOPE.getDisplayTextWithArgs (aDisplayLocale, aGlobalScope.getID ()),
                    _getGlobalScopeInfo (aWPEC, aGlobalScope));

    // Application scopes
    for (final IApplicationScope aAppScope : CollectionHelper.getSortedByKey (aGlobalScope.getAllApplicationScopes ())
                                                             .values ())
      aTabBox.addTab (EText.MSG_APPLICATION_SCOPE.getDisplayTextWithArgs (aDisplayLocale, aAppScope.getID ()),
                      _getApplicationScopeInfo (aWPEC, aAppScope));

    aNodeList.addChild (aTabBox);
  }
}
