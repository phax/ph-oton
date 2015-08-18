package com.helger.photon.uictrls.datatables.plugins;

import javax.annotation.Nullable;

import com.helger.commons.state.ETriState;
import com.helger.html.hc.IHCConversionSettingsToNode;
import com.helger.html.jscode.IJSExpression;
import com.helger.html.jscode.JSAssocArray;
import com.helger.html.jscode.JSExpr;
import com.helger.photon.core.app.html.PhotonCSS;
import com.helger.photon.core.app.html.PhotonJS;
import com.helger.photon.uictrls.EUICtrlsCSSPathProvider;
import com.helger.photon.uictrls.EUICtrlsJSPathProvider;

public class DataTablesPluginFixedHeader extends AbstractDataTablesPlugin
{
  public static final boolean DEFAULT_FOOTER = false;
  public static final int DEFAULT_FOOTER_OFFSET = 0;
  public static final boolean DEFAULT_HEADER = true;
  public static final int DEFAULT_HEADER_OFFSET = 0;

  private final ETriState m_eFooter = ETriState.UNDEFINED;
  private Integer m_aFooterOffset;
  private final ETriState m_eHeader = ETriState.UNDEFINED;
  private Integer m_aHeaderOffset;

  public DataTablesPluginFixedHeader ()
  {
    super ("fixedHeader");
  }

  @Nullable
  public IJSExpression getInitParams ()
  {
    final JSAssocArray ret = new JSAssocArray ();
    if (m_eFooter.isDefined ())
      ret.add ("footer", m_eFooter.getAsBooleanValue (DEFAULT_FOOTER));
    if (m_aFooterOffset != null)
      ret.add ("footerOffset", m_aFooterOffset.intValue ());
    if (m_eHeader.isDefined ())
      ret.add ("header", m_eHeader.getAsBooleanValue (DEFAULT_HEADER));
    if (m_aHeaderOffset != null)
      ret.add ("headerOffset", m_aHeaderOffset.intValue ());
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
