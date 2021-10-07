/*
 * Copyright (C) 2014-2021 Philip Helger (www.helger.com)
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

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.helger.commons.ValueEnforcer;
import com.helger.commons.annotation.Nonempty;
import com.helger.commons.annotation.OverrideOnDemand;
import com.helger.commons.annotation.ReturnsMutableCopy;
import com.helger.commons.collection.impl.CommonsArrayList;
import com.helger.commons.collection.impl.ICommonsList;
import com.helger.commons.string.StringHelper;
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

  @Nonnull
  @ReturnsMutableCopy
  @OverrideOnDemand
  protected DataTablesDom createDom ()
  {
    return new DataTablesDom ();
  }

  @OverrideOnDemand
  protected void weaveIntoDom (@Nonnull final DataTablesDom aDom)
  {
    aDom.setPosition (0).addCustom ("B").openDiv ("clear").closeDiv ();
  }

  @Override
  public void finalizeDataTablesSettings (@Nonnull final DataTables aDT)
  {
    DataTablesDom aDom = aDT.directGetDom ();
    if (aDom == null)
    {
      aDom = createDom ();
      aDT.setDom (aDom);
    }
    weaveIntoDom (aDom);
  }

  @Nonnull
  public DataTablesPluginButtons addButton (@Nonnull @Nonempty final String sButton)
  {
    ValueEnforcer.notEmpty (sButton, "Button");
    m_aButtons.add (sButton);
    return this;
  }

  @Nonnull
  public DataTablesPluginButtons addButton (@Nonnull final DTPButtonsButton aButton)
  {
    ValueEnforcer.notNull (aButton, "Button");
    m_aButtons.add (aButton);
    return this;
  }

  @Nonnull
  public DataTablesPluginButtons setDom (@Nullable final DTPButtonsDom aDom)
  {
    m_aDom = aDom;
    return this;
  }

  @Nonnull
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
        if (aObj instanceof String)
          aButtons.add ((String) aObj);
        else
          aButtons.add (((DTPButtonsButton) aObj).getAsJS ());
      ret.add (PLUGIN_NAME, aButtons);
    }
    if (m_aDom != null)
      ret.add ("dom", m_aDom.getAsJS ());
    if (StringHelper.hasText (m_sName))
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
  public void registerExternalResources (@Nonnull final IHCConversionSettingsToNode aConversionSettings)
  {
    PhotonJS.registerJSIncludeForThisRequest (EDataTablesJSPathProvider.DATATABLES_BUTTONS);
    PhotonCSS.registerCSSIncludeForThisRequest (EDataTablesCSSPathProvider.DATATABLES_BUTTONS);
    for (final Object aObj : m_aButtons)
      if (aObj instanceof DTPButtonsButton)
        ((DTPButtonsButton) aObj).registerExternalResources (aConversionSettings);
  }
}
