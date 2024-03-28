package com.helger.photon.uictrls.chart.v4;

import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.helger.commons.ValueEnforcer;
import com.helger.commons.annotation.Nonempty;
import com.helger.commons.annotation.OverrideOnDemand;
import com.helger.commons.id.factory.GlobalIDFactory;
import com.helger.html.hc.IHCConversionSettingsToNode;
import com.helger.html.hc.IHCHasChildrenMutable;
import com.helger.html.hc.IHCNode;
import com.helger.html.hc.html.script.AbstractHCCanvas;
import com.helger.html.hc.html.script.HCScriptInline;
import com.helger.html.jscode.IJSExpression;
import com.helger.html.jscode.JSAssocArray;
import com.helger.html.jscode.JSBlock;
import com.helger.html.jscode.JSDefinedClass;
import com.helger.html.jscode.JSDelete;
import com.helger.html.jscode.JSExpr;
import com.helger.html.jscode.JSPackage;
import com.helger.html.jscode.JSRef;
import com.helger.html.jscode.JSVar;
import com.helger.html.jscode.html.JSHtml;
import com.helger.photon.app.html.PhotonJS;
import com.helger.photon.uictrls.EUICtrlsJSPathProvider;

public class HCChartV4 extends AbstractHCCanvas <HCChartV4>
{
  // Main chart
  private final IChartV4 m_aChart;
  private final int m_nID;
  // Canvas initial width
  private IJSExpression m_aWidth;
  private IJSExpression m_aHeight;

  public HCChartV4 (@Nonnull final IChartV4 aChart)
  {
    this (aChart, GlobalIDFactory.getNewIntID ());
  }

  public HCChartV4 (@Nonnull final IChartV4 aChart, @Nonnegative final int nID)
  {
    ValueEnforcer.notNull (aChart, "Chart");
    m_aChart = aChart;
    m_nID = nID;
    setID (getCanvasID ());
  }

  /**
   * @return The chart passed in the constructor. Never <code>null</code>.
   */
  @Nonnull
  public IChartV4 getChart ()
  {
    return m_aChart;
  }

  /**
   * @return The internal ID appended as a suffix to all JS names.
   */
  public final int getJSID ()
  {
    return m_nID;
  }

  @Nonnull
  public HCChartV4 setInitialWidth (final int nWidth)
  {
    return setInitialWidth (JSExpr.lit (nWidth));
  }

  @Nonnull
  public HCChartV4 setInitialWidth (@Nullable final IJSExpression aWidth)
  {
    m_aWidth = aWidth;
    return this;
  }

  @Nonnull
  public HCChartV4 setInitialHeight (final int nHeight)
  {
    return setInitialHeight (JSExpr.lit (nHeight));
  }

  @Nonnull
  public HCChartV4 setInitialHeight (@Nullable final IJSExpression aHeight)
  {
    m_aHeight = aHeight;
    return this;
  }

  /**
   * @return The HTML ID of the canvas used.
   */
  @Nonnull
  @Nonempty
  public final String getCanvasID ()
  {
    return "canvas" + m_nID;
  }

  /**
   * @return The name of the global JS variable containing the data.
   */
  @Nonnull
  @Nonempty
  public final String getJSDataVar ()
  {
    return "data" + m_nID;
  }

  /**
   * @return The name of the global JS variable containing the main chart.
   */
  @Nonnull
  @Nonempty
  public final String getJSChartVar ()
  {
    return "chart" + m_nID;
  }

  @Nonnull
  public JSAssocArray getJSOptions ()
  {
    // First take options from chart
    final JSAssocArray aJSOptions = m_aChart.getJSOptions ();

    return aJSOptions;
  }

  /**
   * Callback method to be implemented by implementing classes to add additional
   * JS code.
   *
   * @param aJSBody
   *        The JS body where code should be appended to.
   */
  @OverrideOnDemand
  protected void onAddInitializationCode (@Nonnull final JSPackage aJSBody)
  {}

  @Override
  protected void onFinalizeNodeState (@Nonnull final IHCConversionSettingsToNode aConversionSettings,
                                      @Nonnull final IHCHasChildrenMutable <?, ? super IHCNode> aTargetNode)
  {
    super.onFinalizeNodeState (aConversionSettings, aTargetNode);

    // Wrap everything in an anonymous function to avoid spamming the global
    // variable space
    final JSPackage aJSBody = new JSPackage ();

    // Add previous clean up code
    if (false)
    {
      final JSRef aOldData = JSExpr.ref (getJSDataVar ());
      aJSBody._if (aOldData, new JSDelete (aOldData));

      final JSRef aOldChart = JSExpr.ref (getJSChartVar ());
      final JSBlock aJSDelOld = aJSBody._if (aOldChart)._then ();
      aJSDelOld.add (aOldChart.invoke ("destroy"));
      aJSDelOld.delete (aOldChart);
    }

    final JSVar aJSCanvas = aJSBody.variable (getCanvasID (), JSHtml.documentGetElementById (this));
    if (m_aWidth != null)
      aJSBody.add (aJSCanvas.ref ("width").assign (m_aWidth));
    if (m_aHeight != null)
      aJSBody.add (aJSCanvas.ref ("height").assign (m_aHeight));

    // Init after width and height was of the canvas was set

    // Get the data to be displayed
    final JSVar aJSData = aJSBody.variable (getJSDataVar (), m_aChart.getJSData ());

    // First take options from chart
    final JSAssocArray aJSOptions = getJSOptions ();

    // Build main chart
    aJSBody.variable (getJSChartVar (),
                      new JSDefinedClass ("Chart")._new ()
                                                  .arg (aJSCanvas)
                                                  .arg (new JSAssocArray ().add ("type", m_aChart.getType ())
                                                                           .add ("data", aJSData)
                                                                           .add ("options", aJSOptions)));

    // Callback
    onAddInitializationCode (aJSBody);

    // Add inline code
    addChild (new HCScriptInline (aJSBody));
  }

  @Override
  protected void onRegisterExternalResources (@Nonnull final IHCConversionSettingsToNode aConversionSettings,
                                              final boolean bForceRegistration)
  {
    PhotonJS.registerJSIncludeForThisRequest (EUICtrlsJSPathProvider.CHART_4);
  }
}
