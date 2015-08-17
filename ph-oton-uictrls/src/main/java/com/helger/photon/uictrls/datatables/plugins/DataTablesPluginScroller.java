package com.helger.photon.uictrls.datatables.plugins;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.helger.css.propertyvalue.CCSSValue;
import com.helger.html.hc.IHCConversionSettingsToNode;
import com.helger.html.jscode.IJSExpression;
import com.helger.html.jscode.JSAssocArray;
import com.helger.html.jscode.JSExpr;
import com.helger.photon.core.app.html.PhotonCSS;
import com.helger.photon.core.app.html.PhotonJS;
import com.helger.photon.uictrls.EUICtrlsCSSPathProvider;
import com.helger.photon.uictrls.EUICtrlsJSPathProvider;
import com.helger.photon.uictrls.datatables.DataTables;

/**
 * Enable and configure the Scroller extension for DataTables. <br>
 * Paging must be enabled in DataTables<br>
 * All rows must be of equal height. This is to allow row height calculations
 * for rows which have not yet been rendered.
 *
 * @author Philip Helger
 */
public class DataTablesPluginScroller extends AbstractDataTablesPlugin
{
  public static final double DEFAULT_BOUNDARY_SCALE = 0.5;
  public static final int DEFAULT_DISPLAY_BUFFER = 9;
  public static final boolean DEFAULT_LOADING_INDICATOR = false;
  public static final String DEFAULT_ROW_HEIGHT = CCSSValue.AUTO;
  public static final int DEFAULT_SERVER_WAIT = 200;

  private Double m_aBoundaryScale;
  private Integer m_aDisplayBuffer;
  private boolean m_bLoadingIndicator = DEFAULT_LOADING_INDICATOR;
  private String m_sRowHeight = DEFAULT_ROW_HEIGHT;
  private Integer m_aServerWait;

  public DataTablesPluginScroller ()
  {
    super ("scroller");
  }

  @Nonnull
  public DataTablesPluginScroller setBoundaryScale (final double dBoundaryScale)
  {
    return setBoundaryScale (Double.valueOf (dBoundaryScale));
  }

  @Nonnull
  public DataTablesPluginScroller setBoundaryScale (@Nullable final Double aBoundaryScale)
  {
    m_aBoundaryScale = aBoundaryScale;
    return this;
  }

  @Nonnull
  public DataTablesPluginScroller setDisplayBuffer (final int dDisplayBuffer)
  {
    return setDisplayBuffer (Integer.valueOf (dDisplayBuffer));
  }

  @Nonnull
  public DataTablesPluginScroller setDisplayBuffer (@Nullable final Integer aDisplayBuffer)
  {
    m_aDisplayBuffer = aDisplayBuffer;
    return this;
  }

  @Nonnull
  public DataTablesPluginScroller setLoadingIndicator (final boolean bLoadingIndicator)
  {
    m_bLoadingIndicator = bLoadingIndicator;
    return this;
  }

  @Nonnull
  public DataTablesPluginScroller setRowHeight (final int nRowHeight)
  {
    m_sRowHeight = Integer.toString (nRowHeight);
    return this;
  }

  @Nonnull
  public DataTablesPluginScroller setRowHeightAuto ()
  {
    m_sRowHeight = DEFAULT_ROW_HEIGHT;
    return this;
  }

  @Nonnull
  public DataTablesPluginScroller setServerWait (final int dServerWait)
  {
    return setServerWait (Integer.valueOf (dServerWait));
  }

  @Nonnull
  public DataTablesPluginScroller setServerWait (@Nullable final Integer aServerWait)
  {
    m_aServerWait = aServerWait;
    return this;
  }

  @Override
  public boolean canBeApplied (@Nonnull final DataTables aDT)
  {
    return aDT.isPaging ();
  }

  @Override
  public void finalizeDataTablesSettings (@Nonnull final DataTables aDT)
  {
    aDT.setDeferRender (true);
    aDT.setScrollY (true);
  }

  @Nullable
  public IJSExpression getInitParams ()
  {
    final JSAssocArray ret = new JSAssocArray ();
    if (m_aBoundaryScale != null)
      ret.add ("boundaryScale", m_aBoundaryScale.doubleValue ());
    if (m_aDisplayBuffer != null)
      ret.add ("displayBuffer", m_aDisplayBuffer.intValue ());
    if (m_bLoadingIndicator != DEFAULT_LOADING_INDICATOR)
      ret.add ("loadingIndicator", m_bLoadingIndicator);
    if (!m_sRowHeight.equals (DEFAULT_ROW_HEIGHT))
      ret.add ("rowHeight", m_sRowHeight);
    if (m_aServerWait != null)
      ret.add ("serverWait", m_aServerWait.intValue ());

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
    PhotonCSS.registerCSSIncludeForThisRequest (EUICtrlsCSSPathProvider.DATATABLES_SCROLLER);
    PhotonJS.registerJSIncludeForThisRequest (EUICtrlsJSPathProvider.DATATABLES_SCROLLER);
  }
}
