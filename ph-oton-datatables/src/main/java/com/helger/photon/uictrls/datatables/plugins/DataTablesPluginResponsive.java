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
package com.helger.photon.uictrls.datatables.plugins;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.helger.commons.ValueEnforcer;
import com.helger.commons.collection.ext.CommonsArrayList;
import com.helger.commons.collection.ext.ICommonsList;
import com.helger.commons.state.ETriState;
import com.helger.html.css.DefaultCSSClassProvider;
import com.helger.html.css.ICSSClassProvider;
import com.helger.html.hc.IHCConversionSettingsToNode;
import com.helger.html.jscode.IJSExpression;
import com.helger.html.jscode.JSAnonymousFunction;
import com.helger.html.jscode.JSArray;
import com.helger.html.jscode.JSAssocArray;
import com.helger.html.jscode.JSExpr;
import com.helger.photon.core.app.html.PhotonCSS;
import com.helger.photon.core.app.html.PhotonJS;
import com.helger.photon.uictrls.datatables.DataTables;
import com.helger.photon.uictrls.datatables.EDataTablesCSSPathProvider;
import com.helger.photon.uictrls.datatables.EDataTablesJSPathProvider;
import com.helger.photon.uictrls.datatables.column.DataTablesColumnDef;

public class DataTablesPluginResponsive extends AbstractDataTablesPlugin
{
  public static final ICSSClassProvider CSS_CLASS_ALL = DefaultCSSClassProvider.create ("all");
  public static final ICSSClassProvider CSS_CLASS_CONTROL = DefaultCSSClassProvider.create ("control");
  public static final ICSSClassProvider CSS_CLASS_NONE = DefaultCSSClassProvider.create ("none");
  public static final ICSSClassProvider CSS_CLASS_NEVER = DefaultCSSClassProvider.create ("never");

  private ICommonsList <DTPResponsiveBreakpoint> m_aBreakpoints;
  private ETriState m_eDetails = ETriState.UNDEFINED;
  /** function renderer( api, rowIdx ) */
  private JSAnonymousFunction m_aDetailsRenderer;
  private IJSExpression m_aDetailsTarget;
  private EDTPResponsiveType m_eDetailsType;

  public DataTablesPluginResponsive ()
  {
    super ("responsive");
  }

  @Nonnull
  public DataTablesPluginResponsive addBreakpoint (@Nonnull final DTPResponsiveBreakpoint aBreakpoint)
  {
    ValueEnforcer.notNull (aBreakpoint, "Breakpoint");
    if (m_aBreakpoints == null)
      m_aBreakpoints = new CommonsArrayList <> ();
    m_aBreakpoints.add (aBreakpoint);
    return this;
  }

  @Nonnull
  public DataTablesPluginResponsive setDetails (@Nonnull final ETriState eDetails)
  {
    ValueEnforcer.notNull (eDetails, "Details");
    m_eDetails = eDetails;
    return this;
  }

  @Nonnull
  public DataTablesPluginResponsive setDetailsRenderer (@Nullable final JSAnonymousFunction aDetailsRenderer)
  {
    m_aDetailsRenderer = aDetailsRenderer;
    return this;
  }

  @Nonnull
  public DataTablesPluginResponsive setDetailsTarget (final int nDetailsTarget)
  {
    return setDetailsTarget (JSExpr.lit (nDetailsTarget));
  }

  @Nonnull
  public DataTablesPluginResponsive setDetailsTarget (final String sDetailsTarget)
  {
    return setDetailsTarget (JSExpr.lit (sDetailsTarget));
  }

  @Nonnull
  public DataTablesPluginResponsive setDetailsTarget (@Nullable final IJSExpression aDetailsTarget)
  {
    m_aDetailsTarget = aDetailsTarget;
    return this;
  }

  @Nonnull
  public DataTablesPluginResponsive setDetailsType (@Nullable final EDTPResponsiveType eDetailsType)
  {
    m_eDetailsType = eDetailsType;
    return this;
  }

  @Override
  public void finalizeDataTablesSettings (@Nonnull final DataTables aDT)
  {
    // Source:
    // https://github.com/DataTables/Responsive/issues/8
    for (final DataTablesColumnDef aColumnDef : aDT.getAllColumns ())
      if (!aColumnDef.isVisible ())
        aColumnDef.addClass (CSS_CLASS_NEVER);
  }

  @Nullable
  public IJSExpression getInitParams ()
  {
    final JSAssocArray ret = new JSAssocArray ();
    if (m_aBreakpoints != null)
    {
      final JSArray aArray = new JSArray ();
      for (final DTPResponsiveBreakpoint aBreakpoint : m_aBreakpoints)
        aArray.add (aBreakpoint.getAsJS ());
      ret.add ("breakpoints", aArray);
    }
    final JSAssocArray aDetails = new JSAssocArray ();
    {
      if (m_aDetailsRenderer != null)
        aDetails.add ("renderer", m_aDetailsRenderer);
      if (m_aDetailsTarget != null)
        aDetails.add ("target", m_aDetailsTarget);
      if (m_eDetailsType != null)
        aDetails.add ("type", m_eDetailsType.getName ());
    }
    if (!aDetails.isEmpty ())
      ret.add ("details", aDetails);
    else
      if (m_eDetails.isDefined ())
        ret.add ("details", m_eDetails.getAsBooleanValue (true));

    if (ret.isEmpty ())
    {
      // No properties present
      return JSExpr.TRUE;
    }
    return ret;
  }

  @Override
  public void registerExternalResources (final IHCConversionSettingsToNode aConversionSettings)
  {
    PhotonJS.registerJSIncludeForThisRequest (EDataTablesJSPathProvider.DATATABLES_RESPONSIVE);
    PhotonCSS.registerCSSIncludeForThisRequest (EDataTablesCSSPathProvider.DATATABLES_RESPONSIVE);
  }
}
