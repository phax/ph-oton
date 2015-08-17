package com.helger.photon.uictrls.datatables.plugins;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.helger.commons.ValueEnforcer;
import com.helger.commons.state.ETriState;
import com.helger.html.hc.IHCConversionSettingsToNode;
import com.helger.html.jscode.IJSExpression;
import com.helger.html.jscode.JSAnonymousFunction;
import com.helger.html.jscode.JSArray;
import com.helger.html.jscode.JSAssocArray;
import com.helger.html.jscode.JSExpr;
import com.helger.photon.core.app.html.PhotonCSS;
import com.helger.photon.uictrls.EUICtrlsCSSPathProvider;

public class DataTablesPluginResponsive extends AbstractDataTablesPlugin
{
  private List <DTPResponsiveBreakpoint> m_aBreakpoints;
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
      m_aBreakpoints = new ArrayList <> ();
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
    PhotonCSS.registerCSSIncludeForThisRequest (EUICtrlsCSSPathProvider.DATATABLES_RESPONSIVE);
  }
}
