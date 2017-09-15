/**
 * Copyright (C) 2014-2017 Philip Helger (www.helger.com)
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
package com.helger.photon.bootstrap.demo.app.ui;

import javax.annotation.Nonnull;

import com.helger.commons.annotation.Nonempty;
import com.helger.commons.annotation.OverrideOnDemand;
import com.helger.html.hc.html.root.HCHtml;
import com.helger.photon.basic.app.appid.PhotonGlobalState;
import com.helger.photon.basic.app.appid.RequestSettings;
import com.helger.photon.basic.app.request.IRequestParameterManager;
import com.helger.photon.bootstrap.demo.app.CApp;
import com.helger.photon.core.app.context.ISimpleWebExecutionContext;
import com.helger.photon.core.app.context.LayoutExecutionContext;
import com.helger.photon.core.app.layout.AbstractLayoutManagerBasedLayoutHTMLProvider;

/**
 * Main class for creating HTML output
 *
 * @author Philip Helger
 */
public class AppLayoutHTMLProvider extends AbstractLayoutManagerBasedLayoutHTMLProvider <LayoutExecutionContext>
{
  public AppLayoutHTMLProvider (@Nonnull @Nonempty final String sAppID)
  {
    super (PhotonGlobalState.getInstance ().state (sAppID).getCustom ("lm"));
    setCreateLayoutAreaSpan (false);
  }

  @Override
  protected LayoutExecutionContext createLayoutExecutionContext (@Nonnull final ISimpleWebExecutionContext aSWEC,
                                                                 @Nonnull final IRequestParameterManager aRequestManager)
  {
    return new LayoutExecutionContext (aSWEC, RequestSettings.getMenuItem (aSWEC.getRequestScope ()));
  }

  /**
   * Fill the HTML HEAD element.
   *
   * @param aHtml
   *        The HTML object to be filled.
   */
  @Override
  @OverrideOnDemand
  protected void fillHead (@Nonnull final ISimpleWebExecutionContext aSWEC, @Nonnull final HCHtml aHtml)
  {
    super.fillHead (aSWEC, aHtml);
    aHtml.getHead ().setPageTitle (CApp.getApplicationTitle ());
  }
}
