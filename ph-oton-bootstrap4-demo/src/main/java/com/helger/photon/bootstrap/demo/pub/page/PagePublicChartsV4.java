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

import com.helger.html.hc.impl.HCNodeList;
import com.helger.html.jscode.JSAssocArray;
import com.helger.photon.bootstrap.demo.app.ui.AbstractAppWebPage;
import com.helger.photon.bootstrap4.CBootstrapCSS;
import com.helger.photon.uicore.page.WebPageExecutionContext;
import com.helger.photon.uictrls.chart.v4.ChartBar;
import com.helger.photon.uictrls.chart.v4.ChartDataSetBar;
import com.helger.photon.uictrls.chart.v4.ChartDataSetLine;
import com.helger.photon.uictrls.chart.v4.ChartDataSetPie;
import com.helger.photon.uictrls.chart.v4.ChartLine;
import com.helger.photon.uictrls.chart.v4.ChartPie;
import com.helger.photon.uictrls.chart.v4.HCChartV4;

import jakarta.annotation.Nonnull;

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
      aChart.addDataSet (new ChartDataSetBar ().setData (10, 15, 10 + ThreadLocalRandom.current ().nextInt (10), 20, 15)
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
      aChart.addDataSet (new ChartDataSetLine ().setData (10,
                                                          15,
                                                          10 + ThreadLocalRandom.current ().nextInt (10),
                                                          20,
                                                          15)
                                                .setLabel ("Sequence 1")
                                                .setBorderColor ("#4c9")
                                                .setFill ("start")
                                                .setTension (0));
      aChart.addDataSet (new ChartDataSetLine ().setData (20, 5, 10 - ThreadLocalRandom.current ().nextInt (10), 10, 25)
                                                .setLabel ("Sequence 2")
                                                .setBorderColor ("red")
                                                .setFill (new JSAssocArray ().add ("target", "origin")
                                                                             .add ("above", "rgb(255, 128, 0)")
                                                                             .add ("below", "rgb(0, 0, 255)"))
                                                .setTension (0.5));
      aChart.setLabels ("Mon", "Tue", "Wed", "Thu", "Fri");

      final HCChartV4 aHCChart = new HCChartV4 (aChart);
      aNodeList.addChild (aHCChart);
    }

    // Pie Chart
    {
      aNodeList.addChild (h3 ("Pie chart (v4)"));
      final ChartPie aChart = new ChartPie ()
      {
        @Override
        public JSAssocArray getJSOptions ()
        {
          final JSAssocArray ret = super.getJSOptions ();
          final JSAssocArray aPlugins = (JSAssocArray) ret.computeIfAbsent ("plugins", j -> new JSAssocArray ());
          aPlugins.add ("legend", new JSAssocArray ().add ("position", "bottom"));
          aPlugins.add ("title", new JSAssocArray ().add ("display", "true").add ("text", "Legend title"));
          return ret;
        }
      };
      aChart.setUseAnimations (false);
      aChart.addDataSet (new ChartDataSetPie ().setData (10, 15, 10 + ThreadLocalRandom.current ().nextInt (10), 20, 15)
                                               .setLabel ("Dataset")
                                               .setBackgroundColor ("rgb(255, 99, 132)",
                                                                    "rgb(54, 162, 235)",
                                                                    "rgb(255, 205, 86)"));
      aChart.addDataSet (new ChartDataSetPie ().setData (20, 5, 10 - ThreadLocalRandom.current ().nextInt (10), 10, 25)
                                               .setLabel ("Datatset 2")
                                               .setBorderColor ("blue", "red", "green")
                                               .setBackgroundColor ("red", "green", "blue"));
      aChart.setLabels ("Mon", "Tue", "Wed", "Thu", "Fri");

      final HCChartV4 aHCChart = new HCChartV4 (aChart);
      aNodeList.addChild (div (aHCChart).addClass (CBootstrapCSS.W_50));
    }
  }
}
