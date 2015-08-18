package com.helger.photon.uictrls.datatables.plugins;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.helger.html.hc.IHCConversionSettingsToNode;
import com.helger.html.jscode.IJSExpression;
import com.helger.html.jscode.JSAssocArray;
import com.helger.html.jscode.JSExpr;
import com.helger.photon.core.app.html.PhotonCSS;
import com.helger.photon.core.app.html.PhotonJS;
import com.helger.photon.uictrls.EUICtrlsCSSPathProvider;
import com.helger.photon.uictrls.EUICtrlsJSPathProvider;

public class DataTablesPluginFixedColumns extends AbstractDataTablesPlugin
{
  public static final EDTPFixedColumnsHeightMatch DEFAULT_HEIGHT_MATCH = EDTPFixedColumnsHeightMatch.SEMI_AUTO;
  public static final int DEFAULT_LEFT_COLUMNS = 1;
  public static final int DEFAULT_RIGHT_COLUMNS = 0;

  private EDTPFixedColumnsHeightMatch m_eHeightMatch;
  private Integer m_aLeftColumns;
  private Integer m_aRightColumns;

  public DataTablesPluginFixedColumns ()
  {
    super ("fixedColumns");
  }

  @Nonnull
  public DataTablesPluginFixedColumns setHeightMatch (@Nullable final EDTPFixedColumnsHeightMatch eHeightMatch)
  {
    m_eHeightMatch = eHeightMatch;
    return this;
  }

  @Nonnull
  public DataTablesPluginFixedColumns setLeftColumns (final int nLeftColumns)
  {
    return setLeftColumns (Integer.valueOf (nLeftColumns));
  }

  @Nonnull
  public DataTablesPluginFixedColumns setLeftColumns (@Nullable final Integer aLeftColumns)
  {
    m_aLeftColumns = aLeftColumns;
    return this;
  }

  @Nonnull
  public DataTablesPluginFixedColumns setRightColumns (final int nRightColumns)
  {
    return setRightColumns (Integer.valueOf (nRightColumns));
  }

  @Nonnull
  public DataTablesPluginFixedColumns setRightColumns (@Nullable final Integer aRightColumns)
  {
    m_aRightColumns = aRightColumns;
    return this;
  }

  @Nullable
  public IJSExpression getInitParams ()
  {
    final JSAssocArray ret = new JSAssocArray ();
    if (m_eHeightMatch != null)
      ret.add ("heightMatch", m_eHeightMatch.getName ());
    if (m_aLeftColumns != null)
      ret.add ("leftColumns", m_aLeftColumns.intValue ());
    if (m_aRightColumns != null)
      ret.add ("rightColumns", m_aRightColumns.intValue ());
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
    PhotonJS.registerJSIncludeForThisRequest (EUICtrlsJSPathProvider.DATATABLES_FIXED_HEADER);
    PhotonCSS.registerCSSIncludeForThisRequest (EUICtrlsCSSPathProvider.DATATABLES_FIXED_HEADER);
  }
}
