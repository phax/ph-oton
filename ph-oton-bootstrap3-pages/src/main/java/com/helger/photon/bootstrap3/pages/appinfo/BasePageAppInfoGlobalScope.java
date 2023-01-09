/*
 * Copyright (C) 2014-2023 Philip Helger (www.helger.com)
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
package com.helger.photon.bootstrap3.pages.appinfo;

import java.util.Locale;
import java.util.Map;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.helger.commons.annotation.Nonempty;
import com.helger.commons.annotation.Translatable;
import com.helger.commons.compare.ESortOrder;
import com.helger.commons.datetime.PDTToString;
import com.helger.commons.lang.ClassHelper;
import com.helger.commons.text.IMultilingualText;
import com.helger.commons.text.display.IHasDisplayTextWithArgs;
import com.helger.commons.text.resolve.DefaultTextResolver;
import com.helger.commons.text.util.TextHelper;
import com.helger.html.hc.IHCNode;
import com.helger.html.hc.html.tabular.HCTable;
import com.helger.html.hc.impl.HCNodeList;
import com.helger.photon.bootstrap3.button.BootstrapButtonToolbar;
import com.helger.photon.bootstrap3.form.BootstrapFormGroup;
import com.helger.photon.bootstrap3.form.BootstrapViewForm;
import com.helger.photon.bootstrap3.pages.AbstractBootstrapWebPage;
import com.helger.photon.bootstrap3.uictrls.datatables.BootstrapDataTables;
import com.helger.photon.core.EPhotonCoreText;
import com.helger.photon.uicore.UITextFormatter;
import com.helger.photon.uicore.icon.EDefaultIcon;
import com.helger.photon.uicore.page.EWebPageText;
import com.helger.photon.uicore.page.IWebPageExecutionContext;
import com.helger.photon.uictrls.datatables.DataTables;
import com.helger.photon.uictrls.datatables.column.DTCol;
import com.helger.web.scope.IGlobalWebScope;
import com.helger.web.scope.mgr.WebScopeManager;

/**
 * Show information on global and application scopes
 *
 * @author Philip Helger
 * @param <WPECTYPE>
 *        Web page execution context type
 */
public class BasePageAppInfoGlobalScope <WPECTYPE extends IWebPageExecutionContext> extends AbstractBootstrapWebPage <WPECTYPE>
{
  @Translatable
  protected enum EText implements IHasDisplayTextWithArgs
  {
    MSG_SCOPE_ID ("Kontext ID", "Scope ID"),
    MSG_SCOPE_CREATION_DT ("Erstellungszeit", "Creation date time"),
    MSG_SCOPE_VALID ("Kontext gültig?", "Scope valid?"),
    MSG_SCOPE_IN_DESTRUCTION ("Kontext in Zerstörung?", "Scope in destruction?"),
    MSG_SCOPE_DESTROYED ("Kontext zerstört?", "Scope destroyed?"),
    MSG_SCOPE_ATTRS ("Attribute", "Attributes"),
    MSG_NAME ("Name", "Wert"),
    MSG_TYPE ("Typ", "Type"),
    MSG_VALUE ("Wert", "Value");

    private final IMultilingualText m_aTP;

    EText (final String sDE, final String sEN)
    {
      m_aTP = TextHelper.create_DE_EN (sDE, sEN);
    }

    @Nullable
    public String getDisplayText (@Nonnull final Locale aContentLocale)
    {
      return DefaultTextResolver.getTextStatic (this, m_aTP, aContentLocale);
    }
  }

  public BasePageAppInfoGlobalScope (@Nonnull @Nonempty final String sID)
  {
    super (sID, EWebPageText.PAGE_NAME_APPINFO_GLOBAL_SCOPE.getAsMLT ());
  }

  public BasePageAppInfoGlobalScope (@Nonnull @Nonempty final String sID, @Nonnull @Nonempty final String sName)
  {
    super (sID, sName);
  }

  public BasePageAppInfoGlobalScope (@Nonnull @Nonempty final String sID, @Nonnull final String sName, @Nullable final String sDescription)
  {
    super (sID, sName, sDescription);
  }

  public BasePageAppInfoGlobalScope (@Nonnull @Nonempty final String sID,
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

    final BootstrapViewForm aViewForm = new BootstrapViewForm ();
    aViewForm.setCondensed (true);
    aViewForm.addFormGroup (new BootstrapFormGroup ().setLabel (EText.MSG_SCOPE_ID.getDisplayText (aDisplayLocale))
                                                     .setCtrl (aScope.getID ()));
    aViewForm.addFormGroup (new BootstrapFormGroup ().setLabel (EText.MSG_SCOPE_CREATION_DT.getDisplayText (aDisplayLocale))
                                                     .setCtrl (PDTToString.getAsString (aScope.getCreationDateTime (), aDisplayLocale)));
    aViewForm.addFormGroup (new BootstrapFormGroup ().setLabel (EText.MSG_SCOPE_VALID.getDisplayText (aDisplayLocale))
                                                     .setCtrl (EPhotonCoreText.getYesOrNo (aScope.isValid (), aDisplayLocale)));
    aViewForm.addFormGroup (new BootstrapFormGroup ().setLabel (EText.MSG_SCOPE_IN_DESTRUCTION.getDisplayText (aDisplayLocale))
                                                     .setCtrl (EPhotonCoreText.getYesOrNo (aScope.isInDestruction (), aDisplayLocale)));
    aViewForm.addFormGroup (new BootstrapFormGroup ().setLabel (EText.MSG_SCOPE_DESTROYED.getDisplayText (aDisplayLocale))
                                                     .setCtrl (EPhotonCoreText.getYesOrNo (aScope.isDestroyed (), aDisplayLocale)));
    aViewForm.addFormGroup (new BootstrapFormGroup ().setLabel (EText.MSG_SCOPE_ATTRS.getDisplayText (aDisplayLocale))
                                                     .setCtrl (Integer.toString (aScope.attrs ().size ())));
    aNodeList.addChild (aViewForm);

    // All scope attributes
    final HCTable aTableAttrs = new HCTable (new DTCol (EText.MSG_NAME.getDisplayText (aDisplayLocale)).setInitialSorting (ESortOrder.ASCENDING),
                                             new DTCol (EText.MSG_TYPE.getDisplayText (aDisplayLocale)),
                                             new DTCol (EText.MSG_VALUE.getDisplayText (aDisplayLocale))).setID ("globalscope");
    for (final Map.Entry <String, Object> aEntry : aScope.attrs ().entrySet ())
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
    final BootstrapButtonToolbar aToolbar = new BootstrapButtonToolbar (aWPEC);
    aToolbar.addButton (EPhotonCoreText.BUTTON_REFRESH.getDisplayText (aDisplayLocale), aWPEC.getSelfHref (), EDefaultIcon.REFRESH);
    aNodeList.addChild (aToolbar);

    // Global scope
    aNodeList.addChild (_getGlobalScopeInfo (aWPEC, aGlobalScope));
  }
}
