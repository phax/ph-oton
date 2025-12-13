/*
 * Copyright (C) 2014-2025 Philip Helger (www.helger.com)
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
package com.helger.photon.uictrls.datatables.plugins;

import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.Nullable;

import com.helger.annotation.Nonempty;
import com.helger.annotation.style.OverrideOnDemand;
import com.helger.annotation.style.ReturnsMutableCopy;
import com.helger.base.enforce.ValueEnforcer;
import com.helger.base.string.StringHelper;
import com.helger.collection.commons.CommonsArrayList;
import com.helger.collection.commons.ICommonsList;
import com.helger.html.hc.IHCConversionSettingsToNode;
import com.helger.html.jscode.IJSExpression;
import com.helger.html.jscode.JSArray;
import com.helger.html.jscode.JSAssocArray;
import com.helger.html.jscode.JSAtom;
import com.helger.html.jscode.JSExpr;
import com.helger.photon.app.html.PhotonCSS;
import com.helger.photon.app.html.PhotonJS;
import com.helger.photon.uictrls.datatables.DataTables;
import com.helger.photon.uictrls.datatables.DataTablesDom;
import com.helger.photon.uictrls.datatables.EDataTablesCSSPathProvider;
import com.helger.photon.uictrls.datatables.EDataTablesJSPathProvider;

public class DataTablesPluginButtons extends AbstractDataTablesPlugin
{
  public static final String PLUGIN_NAME = "buttons";
  public static final String DEFAULT_NAME = "main";

  /** List of buttons to be created. */
  private final ICommonsList <Object> m_aButtons = new CommonsArrayList <> ();
  /** Options to control the DOM structure Buttons creates. */
  private DTPButtonsDom m_aDom;
  /** Set a name for the instance for the group selector. */
  private String m_sName;

  public DataTablesPluginButtons ()
  {
    super (PLUGIN_NAME);
  }

  @NonNull
  @ReturnsMutableCopy
  @OverrideOnDemand
  protected DataTablesDom createDom ()
  {
    return new DataTablesDom ();
  }

  @OverrideOnDemand
  protected void weaveIntoDom (@NonNull final DataTablesDom aDom)
  {
    aDom.setPosition (0).addCustom ("B").openDiv ("clear").closeDiv ();
  }

  @Override
  public void finalizeDataTablesSettings (@NonNull final DataTables aDT)
  {
    DataTablesDom aDom = aDT.directGetDom ();
    if (aDom == null)
    {
      aDom = createDom ();
      aDT.setDom (aDom);
    }
    weaveIntoDom (aDom);
  }

  @NonNull
  public DataTablesPluginButtons addButton (@NonNull @Nonempty final String sButton)
  {
    ValueEnforcer.notEmpty (sButton, "Button");
    m_aButtons.add (sButton);
    return this;
  }

  @NonNull
  public DataTablesPluginButtons addButton (@NonNull final DTPButtonsButton aButton)
  {
    ValueEnforcer.notNull (aButton, "Button");
    m_aButtons.add (aButton);
    return this;
  }

  @NonNull
  public DataTablesPluginButtons setDom (@Nullable final DTPButtonsDom aDom)
  {
    m_aDom = aDom;
    return this;
  }

  @NonNull
  public DataTablesPluginButtons setName (@Nullable final String sName)
  {
    m_sName = sName;
    return this;
  }

  @Nullable
  public IJSExpression getInitParams ()
  {
    final JSAssocArray ret = new JSAssocArray ();

    if (m_aButtons.isNotEmpty ())
    {
      final JSArray aButtons = new JSArray ();
      for (final Object aObj : m_aButtons)
        if (aObj instanceof final String sObj)
          aButtons.add (sObj);
        else
          aButtons.add (((DTPButtonsButton) aObj).getAsJS ());
      ret.add (PLUGIN_NAME, aButtons);
    }
    if (m_aDom != null)
      ret.add ("dom", m_aDom.getAsJS ());
    if (StringHelper.isNotEmpty (m_sName))
      ret.add ("name", m_sName);

    if (ret.size () == 1)
    {
      final IJSExpression aValue = ret.get (new JSAtom (PLUGIN_NAME));
      if (aValue != null)
        return aValue;
    }

    if (ret.isEmpty ())
    {
      // No properties present
      return JSExpr.TRUE;
    }
    return ret;
  }

  @Override
  public void registerExternalResources (@NonNull final IHCConversionSettingsToNode aConversionSettings)
  {
    PhotonJS.registerJSIncludeForThisRequest (EDataTablesJSPathProvider.DATATABLES_BUTTONS);
    PhotonCSS.registerCSSIncludeForThisRequest (EDataTablesCSSPathProvider.DATATABLES_BUTTONS);
    for (final Object aObj : m_aButtons)
      if (aObj instanceof final DTPButtonsButton aButton)
        aButton.registerExternalResources (aConversionSettings);
  }
}
