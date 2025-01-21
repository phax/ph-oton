/*
 * Copyright (C) 2018-2025 Philip Helger (www.helger.com)
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
import com.helger.photon.bootstrap.demo.app.ui.AbstractAppWebPage;
import com.helger.photon.uicore.page.WebPageExecutionContext;
import com.helger.photon.uictrls.chart.v1.ChartBar;
import com.helger.photon.uictrls.chart.v1.ChartLine;
import com.helger.photon.uictrls.chart.v1.HCChart;

public class PagePublicChartsV1 extends AbstractAppWebPage
{
  public PagePublicChartsV1 (final String sID)
  {
    super (sID, "Charts V1 example");
  }

  @Override
  protected void fillContent (@Nonnull final WebPageExecutionContext aWPEC)
  {
    final HCNodeList aNodeList = aWPEC.getNodeList ();

    // Bar Chart
    {
      aNodeList.addChild (h3 ("Bar chart (v1)"));
      final ChartBar aChart = new ChartBar ();
      aChart.addDataSet (new ChartBar.DataSet ().setData (10,
                                                          15,
                                                          10 + ThreadLocalRandom.current ().nextInt (10),
                                                          20,
                                                          15)
                                                .setLabel ("Week days")
                                                .setFillColor ("#c94"));
      aChart.setLabels ("Mon", "Tue", "Wed", "Thu", "Fri");

      final HCChart aHCChart = new HCChart (aChart);
      aHCChart.setWidth (800).setHeight (300);
      aHCChart.setShowLegend (true);
      aNodeList.addChild (aHCChart);
    }

    // Line Chart
    {
      aNodeList.addChild (h3 ("Line chart (v1)"));
      final ChartLine aChart = new ChartLine ();
      aChart.addDataSet (new ChartLine.DataSet ().setData (10,
                                                           15,
                                                           10 + ThreadLocalRandom.current ().nextInt (10),
                                                           20,
                                                           15)
                                                 .setLabel ("Sequence 1")
                                                 .setStrokeColor ("#4c9"));
      aChart.addDataSet (new ChartLine.DataSet ().setData (20,
                                                           5,
                                                           10 - ThreadLocalRandom.current ().nextInt (10),
                                                           10,
                                                           25)
                                                 .setLabel ("Sequence 2")
                                                 .setStrokeColor ("#c94")
                                                 .setPointColor ("red"));
      aChart.setLabels ("Mon", "Tue", "Wed", "Thu", "Fri");
      aChart.setDatasetFill (false);

      final HCChart aHCChart = new HCChart (aChart);
      aHCChart.setWidth (800).setHeight (300);
      aHCChart.setShowLegend (true);
      aNodeList.addChild (aHCChart);
    }
  }
}
