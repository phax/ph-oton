/**
 * Copyright (C) 2014-2016 Philip Helger (www.helger.com)
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
import javax.annotation.Nullable;
import javax.annotation.concurrent.NotThreadSafe;

import com.helger.commons.ValueEnforcer;
import com.helger.commons.equals.EqualsHelper;
import com.helger.commons.string.ToStringGenerator;
import com.helger.html.hc.impl.HCNodeList;
import com.helger.photon.core.app.context.ILayoutExecutionContext;
import com.helger.photon.core.app.context.LayoutExecutionContext;
import com.helger.photon.uicore.css.CPageParam;

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

  public WebPageExecutionContext (@Nonnull final IWebPageExecutionContext aWPEC)
  {
    this (aWPEC, aWPEC.getWebPage ());
  }

  public WebPageExecutionContext (@Nonnull final ILayoutExecutionContext aLEC,
                                  @Nonnull final IWebPage <? extends IWebPageExecutionContext> aWebPage)
  {
    super (aLEC);
    m_aWebPage = ValueEnforcer.notNull (aWebPage, "WebPage");
  }

  /**
   * @return The invoked web page. Never <code>null</code>.
   */
  @Nonnull
  public IWebPage <? extends IWebPageExecutionContext> getWebPage ()
  {
    return m_aWebPage;
  }

  /**
   * @return The node list to be filled with content. Never <code>null</code>.
   */
  @Nonnull
  public HCNodeList getNodeList ()
  {
    return m_aNodeList;
  }

  @Nullable
  public String getAction ()
  {
    return getAttributeAsString (CPageParam.PARAM_ACTION);
  }

  public boolean hasAction (@Nullable final String sAction)
  {
    return EqualsHelper.equals (getAction (), sAction);
  }

  @Nullable
  public String getSubAction ()
  {
    return getAttributeAsString (CPageParam.PARAM_SUBACTION);
  }

  public boolean hasSubAction (@Nullable final String sSubAction)
  {
    return EqualsHelper.equals (getSubAction (), sSubAction);
  }

  @Override
  public String toString ()
  {
    return ToStringGenerator.getDerived (super.toString ())
                            .append ("webPage", m_aWebPage)
                            .append ("nodeList", m_aNodeList)
                            .toString ();
  }
}
