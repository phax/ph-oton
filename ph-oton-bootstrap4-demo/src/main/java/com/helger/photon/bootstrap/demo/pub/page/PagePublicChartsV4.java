/*
 * Copyright (C) 2018-2024 Philip Helger (www.helger.com)
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
package com.helger.photon.bootstrap.demo.pub.page;

import java.util.concurrent.ThreadLocalRandom;

import javax.annotation.Nonnull;

import com.helger.html.hc.impl.HCNodeList;
import com.helger.html.jscode.JSAssocArray;
import com.helger.photon.bootstrap.demo.app.ui.AbstractAppWebPage;
import com.helger.photon.uicore.page.WebPageExecutionContext;
import com.helger.photon.uictrls.chart.v4.ChartBar;
import com.helger.photon.uictrls.chart.v4.ChartDataSet;
import com.helger.photon.uictrls.chart.v4.ChartLine;
import com.helger.photon.uictrls.chart.v4.ChartPie;
import com.helger.photon.uictrls.chart.v4.HCChartV4;

public class PagePublicChartsV4 extends AbstractAppWebPage
{
  public PagePublicChartsV4 (final String sID)
  {
    super (sID, "Charts V4 example");
  }

  @Override
  protected void fillContent (@Nonnull final WebPageExecutionContext aWPEC)
  {
    final HCNodeList aNodeList = aWPEC.getNodeList ();

    // Bar Chart
    {
      aNodeList.addChild (h3 ("Bar chart (v4)"));
      final ChartBar aChart = new ChartBar ();
      aChart.setUseAnimations (false);
      aChart.addDataSet (new ChartDataSet ().setData (10, 15, 10 + ThreadLocalRandom.current ().nextInt (10), 20, 15)
                                            .setLabel ("Week days")
                                            .setBorderWidth (2));
      aChart.setLabels ("Mon", "Tue", "Wed", "Thu", "Fri");

      final HCChartV4 aHCChart = new HCChartV4 (aChart);
      aHCChart.setWidth (800).setHeight (300);
      aNodeList.addChild (aHCChart);
    }

    // Line Chart
    {
      aNodeList.addChild (h3 ("Line chart (v4)"));
      final ChartLine aChart = new ChartLine ();
      aChart.setUseAnimations (false);
      aChart.addDataSet (new ChartDataSet ().setData (10, 15, 10 + ThreadLocalRandom.current ().nextInt (10), 20, 15)
                                            .setLabel ("Sequence 1")
                                            .setBorderColor ("#4c9")
                                            .setFill (false)
                                            .setTension (0));
      aChart.addDataSet (new ChartDataSet ().setData (20, 5, 10 - ThreadLocalRandom.current ().nextInt (10), 10, 25)
                                            .setLabel ("Sequence 2")
                                            .setBorderColor ("red")
                                            .setFill (new JSAssocArray ().add ("target", "origin")
                                                                         .add ("above", "rgb(255, 128, 0)")
                                                                         .add ("below", "rgb(0, 0, 255)"))
                                            .setTension (0.5));
      aChart.setLabels ("Mon", "Tue", "Wed", "Thu", "Fri");

      final HCChartV4 aHCChart = new HCChartV4 (aChart);
      aHCChart.setWidth (800).setHeight (300);
      aNodeList.addChild (aHCChart);
    }

    // Pie Chart
    {
      aNodeList.addChild (h3 ("Pie chart (v4)"));
      final ChartPie aChart = new ChartPie ();
      aChart.setUseAnimations (false);
      aChart.addDataSet (new ChartDataSet ().setData (10, 15, 10 + ThreadLocalRandom.current ().nextInt (10), 20, 15)
                                            .setLabel ("Sequence 1")
                                            .setBorderColor ("#4c9")
                                            .setFill (false)
                                            .setTension (0));
      aChart.addDataSet (new ChartDataSet ().setData (20, 5, 10 - ThreadLocalRandom.current ().nextInt (10), 10, 25)
                                            .setLabel ("Sequence 2")
                                            .setBorderColor ("red")
                                            .setFill (new JSAssocArray ().add ("target", "origin")
                                                                         .add ("above", "rgb(255, 128, 0)")
                                                                         .add ("below", "rgb(0, 0, 255)"))
                                            .setTension (0.5));
      aChart.setLabels ("Mon", "Tue", "Wed", "Thu", "Fri");

      final HCChartV4 aHCChart = new HCChartV4 (aChart);
      aHCChart.setWidth (800).setHeight (300);
      aNodeList.addChild (aHCChart);
    }
  }
}
