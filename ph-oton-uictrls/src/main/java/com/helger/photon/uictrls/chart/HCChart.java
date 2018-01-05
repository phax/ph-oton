/**
 * Copyright (C) 2014-2018 Philip Helger (www.helger.com)
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
package com.helger.photon.uictrls.chart;

import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.helger.commons.ValueEnforcer;
import com.helger.commons.annotation.Nonempty;
import com.helger.commons.annotation.OverrideOnDemand;
import com.helger.commons.id.factory.GlobalIDFactory;
import com.helger.commons.state.ETriState;
import com.helger.commons.string.StringHelper;
import com.helger.html.hc.IHCConversionSettingsToNode;
import com.helger.html.hc.IHCHasChildrenMutable;
import com.helger.html.hc.IHCNode;
import com.helger.html.hc.html.forms.HCLegend;
import com.helger.html.hc.html.script.AbstractHCCanvas;
import com.helger.html.hc.html.script.HCScriptInline;
import com.helger.html.jquery.JQuery;
import com.helger.html.js.IHasJSCode;
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
import com.helger.photon.core.app.html.PhotonCSS;
import com.helger.photon.core.app.html.PhotonJS;
import com.helger.photon.uictrls.EUICtrlsCSSPathProvider;
import com.helger.photon.uictrls.EUICtrlsJSPathProvider;

/**
 * Base HC implementation encapsulating any {@link IChart}.
 *
 * @author Philip Helger
 */
public class HCChart extends AbstractHCCanvas <HCChart>
{
  // Main chart
  private final IChart m_aChart;
  private final int m_nID;
  // Canvas initial width
  private IJSExpression m_aWidth;
  private IJSExpression m_aHeight;
  // Chart options
  private ETriState m_eAnimation = ETriState.UNDEFINED;
  private ETriState m_eResponsive = ETriState.UNDEFINED;
  private String m_sScaleLabel;
  private String m_sTooltipTemplate;
  // Default:
  // "<ul class=\"<%=name.toLowerCase()%>-legend\"><% for (var i=0;
  // i<datasets.length; i++){%><li><span
  // style=\"background-color:<%=datasets[i].fillColor%>\"></span><%if(datasets[i].label){%><%=datasets[i].label%><%}%></li><%}%></ul>"
  private String m_sLegendTemplate;
  // Customizations
  private ETriState m_eShowLegend = ETriState.UNDEFINED;

  public HCChart (@Nonnull final IChart aChart)
  {
    this (aChart, GlobalIDFactory.getNewIntID ());
  }

  public HCChart (@Nonnull final IChart aChart, @Nonnegative final int nID)
  {
    m_aChart = ValueEnforcer.notNull (aChart, "Chart");
    m_nID = nID;
    setID (getCanvasID ());
  }

  /**
   * @return The chart passed in the constructor. Never <code>null</code>.
   */
  @Nonnull
  public IChart getChart ()
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
  public HCChart setInitialWidth (final int nWidth)
  {
    return setInitialWidth (JSExpr.lit (nWidth));
  }

  @Nonnull
  public HCChart setInitialWidth (@Nullable final IJSExpression aWidth)
  {
    m_aWidth = aWidth;
    return this;
  }

  @Nonnull
  public HCChart setInitialHeight (final int nHeight)
  {
    return setInitialHeight (JSExpr.lit (nHeight));
  }

  @Nonnull
  public HCChart setInitialHeight (@Nullable final IJSExpression aHeight)
  {
    m_aHeight = aHeight;
    return this;
  }

  @Nonnull
  public HCChart setAnimation (final boolean bAnimation)
  {
    m_eAnimation = ETriState.valueOf (bAnimation);
    return this;
  }

  @Nonnull
  public HCChart setResponsive (final boolean bResponsive)
  {
    m_eResponsive = ETriState.valueOf (bResponsive);
    return this;
  }

  @Nonnull
  public HCChart setScaleLabel (@Nullable final String sScaleLabel)
  {
    m_sScaleLabel = sScaleLabel;
    return this;
  }

  @Nonnull
  public HCChart setTooltipTemplate (@Nullable final String sTooltipTemplate)
  {
    m_sTooltipTemplate = sTooltipTemplate;
    return this;
  }

  @Nonnull
  public HCChart setLegendTemplate (@Nullable final String sLegendTemplate)
  {
    m_sLegendTemplate = sLegendTemplate;
    return this;
  }

  @Nonnull
  public HCChart setShowLegend (final boolean bShowLegend)
  {
    m_eShowLegend = ETriState.valueOf (bShowLegend);
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
   * @return The HTML ID of the legend used (if enabled).
   * @see #setShowLegend(boolean)
   */
  @Nonnull
  @Nonempty
  public final String getLegendID ()
  {
    return "legend" + m_nID;
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

    // And overwrite or extend with the ones explicitly stated
    if (m_eAnimation.isDefined ())
      aJSOptions.add ("animation", m_eAnimation.getAsBooleanValue (true));
    if (m_eResponsive.isDefined ())
      aJSOptions.add ("responsive", m_eResponsive.getAsBooleanValue (true));
    if (StringHelper.hasText (m_sScaleLabel))
      aJSOptions.add ("scaleLabel", m_sScaleLabel);
    if (StringHelper.hasText (m_sTooltipTemplate))
      aJSOptions.add ("tooltipTemplate", m_sTooltipTemplate);
    if (StringHelper.hasText (m_sLegendTemplate))
      aJSOptions.add ("legendTemplate", m_sLegendTemplate);

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
    {
      final JSRef aOldData = JSExpr.ref (getJSDataVar ());
      aJSBody._if (aOldData, new JSDelete (aOldData));

      final JSRef aOldChart = JSExpr.ref (getJSChartVar ());
      final JSBlock aJSDelOld = aJSBody._if (aOldChart)._then ();
      aJSDelOld.add (aOldChart.invoke ("destroy"));
      aJSDelOld.delete (aOldChart);
    }

    final JSVar aJSCanvas = aJSBody.var (getCanvasID (), JSHtml.documentGetElementById (this));
    if (m_aWidth != null)
      aJSBody.add (aJSCanvas.ref ("width").assign (m_aWidth));
    if (m_aHeight != null)
      aJSBody.add (aJSCanvas.ref ("height").assign (m_aHeight));

    // Init after width and height was of the canvas was set

    // Get the data to be displayed
    final JSVar aJSData = aJSBody.var (getJSDataVar (), m_aChart.getJSData ());

    // First take options from chart
    final JSAssocArray aJSOptions = getJSOptions ();

    // Build main chart
    final JSVar aJSChart = aJSBody.var (getJSChartVar (),
                                        new JSDefinedClass ("Chart")._new ()
                                                                    .arg (aJSCanvas.invoke ("getContext").arg ("2d"))
                                                                    .invoke (m_aChart.getJSMethodName ())
                                                                    .arg (aJSData)
                                                                    .arg (aJSOptions));

    if (m_eShowLegend.isTrue ())
    {
      // Create a <legend> for the legend :) This is just a stub element that is
      // filled from chart.js
      // Must be the next sibling of the canvas. Safest version is to use JS for
      // insertion.
      aJSBody.add (JQuery.jQuery (new HCLegend ().setID (getLegendID ())).insertAfter (JQuery.idRef (this)));
      // Fill the stub legend with content
      aJSBody.add (JSHtml.documentGetElementById (getLegendID ())
                         .ref ("innerHTML")
                         .assign (aJSChart.invoke ("generateLegend")));
    }

    // Callback
    onAddInitializationCode (aJSBody);

    // Add inline code
    addChild (new HCScriptInline (aJSBody));
  }

  @Override
  protected void onRegisterExternalResources (@Nonnull final IHCConversionSettingsToNode aConversionSettings,
                                              final boolean bForceRegistration)
  {
    PhotonJS.registerJSIncludeForThisRequest (EUICtrlsJSPathProvider.CHART_1);
    PhotonJS.registerJSIncludeForThisRequest (EUICtrlsJSPathProvider.EXCANVAS);
    PhotonCSS.registerCSSIncludeForThisRequest (EUICtrlsCSSPathProvider.CHART_1);
  }

  /**
   * Update the chart with new datasets. This destroys the old chart.
   *
   * @param aJSDataVar
   *        The data parameter used to draw the graph.
   * @return The JS code needed to do so.
   */
  @Nonnull
  public IHasJSCode getJSUpdateCode (@Nonnull final IJSExpression aJSDataVar)
  {
    final JSPackage ret = new JSPackage ();
    // Cleanup old chart
    ret.invoke (JSExpr.ref (getJSChartVar ()), "destroy");
    // Use new chart
    ret.assign (JSExpr.ref (getJSChartVar ()), new JSDefinedClass ("Chart")._new ()
                                                                           .arg (JSExpr.ref (getCanvasID ())
                                                                                       .invoke ("getContext")
                                                                                       .arg ("2d"))
                                                                           .invoke (m_aChart.getJSMethodName ())
                                                                           .arg (aJSDataVar)
                                                                           .arg (getJSOptions ()));
    return ret;
  }
}
