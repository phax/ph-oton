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
package com.helger.webctrls.page.monitoring;

import java.util.Locale;
import java.util.Map;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.helger.commons.annotations.Nonempty;
import com.helger.commons.annotations.Translatable;
import com.helger.commons.collections.CollectionHelper;
import com.helger.commons.compare.ESortOrder;
import com.helger.commons.lang.CGStringHelper;
import com.helger.commons.name.IHasDisplayText;
import com.helger.commons.name.IHasDisplayTextWithArgs;
import com.helger.commons.scopes.domain.IApplicationScope;
import com.helger.commons.text.IReadonlyMultiLingualText;
import com.helger.commons.text.impl.TextProvider;
import com.helger.commons.text.resolve.DefaultTextResolver;
import com.helger.html.hc.IHCNode;
import com.helger.html.hc.html.HCCol;
import com.helger.html.hc.impl.HCNodeList;
import com.helger.web.scopes.domain.IGlobalWebScope;
import com.helger.web.scopes.mgr.WebScopeManager;
import com.helger.webbasics.EWebBasicsText;
import com.helger.webbasics.app.page.IWebPageExecutionContext;
import com.helger.webctrls.custom.EDefaultIcon;
import com.helger.webctrls.custom.tabbox.ITabBox;
import com.helger.webctrls.custom.table.IHCTableFormView;
import com.helger.webctrls.custom.toolbar.IButtonToolbar;
import com.helger.webctrls.datatables.DataTables;
import com.helger.webctrls.page.AbstractWebPageExt;
import com.helger.webctrls.page.EWebPageText;
import com.helger.webctrls.page.UITextFormatter;

/**
 * Show information on global and application scopes
 *
 * @author Philip Helger
 * @param <WPECTYPE>
 *        Web page execution context type
 */
public class BasePageMonitoringScopes <WPECTYPE extends IWebPageExecutionContext> extends AbstractWebPageExt <WPECTYPE>
{
  @Translatable
  protected static enum EText implements IHasDisplayText, IHasDisplayTextWithArgs
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
                                   @Nonnull final IReadonlyMultiLingualText aName,
                                   @Nullable final IReadonlyMultiLingualText aDescription)
  {
    super (sID, aName, aDescription);
  }

  @Nonnull
  private IHCNode _getGlobalScopeInfo (@Nonnull final WPECTYPE aWPEC, @Nonnull final IGlobalWebScope aScope)
  {
    final Locale aDisplayLocale = aWPEC.getDisplayLocale ();
    final HCNodeList aNodeList = new HCNodeList ();

    final IHCTableFormView <?> aTableScope = getStyler ().createTableFormView (new HCCol (200), HCCol.star ());
    aTableScope.createItemRow ()
               .setLabel (EText.MSG_SCOPE_ID.getDisplayText (aDisplayLocale))
               .setCtrl (aScope.getID ());
    aTableScope.createItemRow ()
               .setLabel (EText.MSG_SCOPE_VALID.getDisplayText (aDisplayLocale))
               .setCtrl (EWebBasicsText.getYesOrNo (aScope.isValid (), aDisplayLocale));
    aTableScope.createItemRow ()
               .setLabel (EText.MSG_SCOPE_IN_DESTRUCTION.getDisplayText (aDisplayLocale))
               .setCtrl (EWebBasicsText.getYesOrNo (aScope.isInDestruction (), aDisplayLocale));
    aTableScope.createItemRow ()
               .setLabel (EText.MSG_SCOPE_DESTROYED.getDisplayText (aDisplayLocale))
               .setCtrl (EWebBasicsText.getYesOrNo (aScope.isDestroyed (), aDisplayLocale));
    aTableScope.createItemRow ()
               .setLabel (EText.MSG_APPLICATION_SCOPES.getDisplayText (aDisplayLocale))
               .setCtrl (Integer.toString (aScope.getApplicationScopeCount ()));
    aTableScope.createItemRow ()
               .setLabel (EText.MSG_SCOPE_ATTRS.getDisplayText (aDisplayLocale))
               .setCtrl (Integer.toString (aScope.getAttributeCount ()));
    aNodeList.addChild (aTableScope);

    // All scope attributes
    final IHCTableFormView <?> aTableAttrs = getStyler ().createTableFormView (HCCol.star (),
                                                                               HCCol.star (),
                                                                               HCCol.star ()).setID ("globalscope");
    aTableAttrs.addHeaderRow ().addCells (EText.MSG_NAME.getDisplayText (aDisplayLocale),
                                          EText.MSG_TYPE.getDisplayText (aDisplayLocale),
                                          EText.MSG_VALUE.getDisplayText (aDisplayLocale));
    for (final Map.Entry <String, Object> aEntry : aScope.getAllAttributes ().entrySet ())
      aTableAttrs.addBodyRow ()
                 .addCell (aEntry.getKey ())
                 .addCell (CGStringHelper.getClassLocalName (aEntry.getValue ()))
                 .addCell (UITextFormatter.getToStringContent (aEntry.getValue ()));
    aNodeList.addChild (aTableAttrs);

    final DataTables aDataTables = getStyler ().createDefaultDataTables (aWPEC, aTableAttrs);
    aDataTables.setInitialSorting (0, ESortOrder.ASCENDING);
    aNodeList.addChild (aDataTables);

    return aNodeList;
  }

  @Nonnull
  private IHCNode _getApplicationScopeInfo (@Nonnull final WPECTYPE aWPEC, @Nonnull final IApplicationScope aScope)
  {
    final Locale aDisplayLocale = aWPEC.getDisplayLocale ();
    final HCNodeList aNodeList = new HCNodeList ();

    final IHCTableFormView <?> aTableScope = getStyler ().createTableFormView (new HCCol (200), HCCol.star ());
    aTableScope.createItemRow ()
               .setLabel (EText.MSG_SCOPE_ID.getDisplayText (aDisplayLocale))
               .setCtrl (aScope.getID ());
    aTableScope.createItemRow ()
               .setLabel (EText.MSG_SCOPE_VALID.getDisplayText (aDisplayLocale))
               .setCtrl (EWebBasicsText.getYesOrNo (aScope.isValid (), aDisplayLocale));
    aTableScope.createItemRow ()
               .setLabel (EText.MSG_SCOPE_IN_DESTRUCTION.getDisplayText (aDisplayLocale))
               .setCtrl (EWebBasicsText.getYesOrNo (aScope.isInDestruction (), aDisplayLocale));
    aTableScope.createItemRow ()
               .setLabel (EText.MSG_SCOPE_DESTROYED.getDisplayText (aDisplayLocale))
               .setCtrl (EWebBasicsText.getYesOrNo (aScope.isDestroyed (), aDisplayLocale));
    aTableScope.createItemRow ()
               .setLabel (EText.MSG_SCOPE_ATTRS.getDisplayText (aDisplayLocale))
               .setCtrl (Integer.toString (aScope.getAttributeCount ()));
    aNodeList.addChild (aTableScope);

    // All scope attributes
    final IHCTableFormView <?> aTableAttrs = getStyler ().createTableFormView (HCCol.star (),
                                                                               HCCol.star (),
                                                                               HCCol.star ()).setID ("appscope" +
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

    final DataTables aDataTables = getStyler ().createDefaultDataTables (aWPEC, aTableAttrs);
    aDataTables.setInitialSorting (0, ESortOrder.ASCENDING);
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
    final IButtonToolbar <?> aToolbar = getStyler ().createToolbar (aWPEC);
    aToolbar.addButton (EWebBasicsText.MSG_BUTTON_REFRESH.getDisplayText (aDisplayLocale),
                        aWPEC.getSelfHref (),
                        EDefaultIcon.REFRESH);
    aNodeList.addChild (aToolbar);

    final ITabBox <?> aTabBox = getStyler ().createTabBox (aWPEC);
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
