/**
 * Copyright (C) 2014-2020 Philip Helger (www.helger.com)
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
package com.helger.photon.uicore.page;

import javax.annotation.Nonnull;
import javax.annotation.concurrent.NotThreadSafe;

import com.helger.commons.ValueEnforcer;
import com.helger.commons.string.ToStringGenerator;
import com.helger.html.hc.impl.HCNodeList;
import com.helger.photon.core.execcontext.ILayoutExecutionContext;
import com.helger.photon.core.execcontext.LayoutExecutionContext;

/**
 * This page is instantiated per page view, so that the thread safety of the
 * execution parameters is more clear.
 *
 * @author Philip Helger
 */
@NotThreadSafe
public class WebPageExecutionContext extends LayoutExecutionContext implements IWebPageExecutionContext
{
  private final IWebPage <? extends IWebPageExecutionContext> m_aWebPage;
  private final HCNodeList m_aNodeList = new HCNodeList ();

  public WebPageExecutionContext (@Nonnull final ILayoutExecutionContext aLEC,
                                  @Nonnull final IWebPage <? extends IWebPageExecutionContext> aWebPage)
  {
    super (aLEC);
    m_aWebPage = ValueEnforcer.notNull (aWebPage, "WebPage");
  }

  @Nonnull
  public final IWebPage <? extends IWebPageExecutionContext> getWebPage ()
  {
    return m_aWebPage;
  }

  @Nonnull
  public final HCNodeList getNodeList ()
  {
    return m_aNodeList;
  }

  @Override
  public String toString ()
  {
    return ToStringGenerator.getDerived (super.toString ())
                            .append ("webPage", m_aWebPage)
                            .append ("nodeList", m_aNodeList)
                            .getToString ();
  }
}
