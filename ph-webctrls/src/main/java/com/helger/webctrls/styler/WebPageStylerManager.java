/**
 * Copyright (C) 2014-2015 Philip Helger (www.helger.com)
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
package com.helger.webctrls.styler;

import javax.annotation.Nonnull;

import com.helger.commons.ValueEnforcer;
import com.helger.commons.annotations.UsedViaReflection;
import com.helger.web.scopes.singleton.GlobalWebSingleton;

public class WebPageStylerManager extends GlobalWebSingleton
{
  private IWebPageStyler m_aStyler = new SimpleWebPageStyler ();

  @Deprecated
  @UsedViaReflection
  public WebPageStylerManager ()
  {}

  @Nonnull
  public static WebPageStylerManager getInstance ()
  {
    return getGlobalSingleton (WebPageStylerManager.class);
  }

  @Nonnull
  public static IWebPageStyler getStyler ()
  {
    return getInstance ().m_aStyler;
  }

  public void setStyler (@Nonnull final IWebPageStyler aStyler)
  {
    m_aStyler = ValueEnforcer.notNull (aStyler, "Styler");
  }
}
