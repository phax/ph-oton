/*
 * Copyright (C) 2014-2026 Philip Helger (www.helger.com)
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

import java.util.Locale;

import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.Nullable;

import com.helger.base.string.StringHelper;
import com.helger.html.hc.IHCNode;
import com.helger.html.hc.html.sections.HCH1;
import com.helger.photon.core.page.IPage;

/**
 * The base interface for a single web page of content.
 *
 * @author Philip Helger
 * @param <WPECTYPE>
 *        Web page execution context type
 */
public interface IWebPage <WPECTYPE extends IWebPageExecutionContext> extends IPage
{
  /**
   * @return The current CSRF handler. Never <code>null</code>.
   */
  @NonNull
  IWebPageCSRFHandler getCSRFHandler ();

  /**
   * Get the headline of the page. By default it is equal to the page name.
   *
   * @param aWPEC
   *        The web page execution context. Never <code>null</code>.
   * @return The header/headline of the page. May be <code>null</code>.
   */
  @Nullable
  default String getHeaderText (@NonNull final WPECTYPE aWPEC)
  {
    final Locale aDisplayLocale = aWPEC.getDisplayLocale ();
    return getDisplayText (aDisplayLocale);
  }

  /**
   * Get the headline of the page. By default it is a &lt;h1&gt; element with
   * the result of {@link #getHeaderText(IWebPageExecutionContext)} as the
   * content. This method is especially relevant when the page header contains
   * entity nodes or other special formatting.
   *
   * @param aWPEC
   *        The web page execution context. Never <code>null</code>.
   * @return The header/headline of the page. May be <code>null</code>.
   */
  @Nullable
  default IHCNode getHeaderNode (@NonNull final WPECTYPE aWPEC)
  {
    final String sHeaderText = getHeaderText (aWPEC);
    if (StringHelper.isEmpty (sHeaderText))
      return null;
    return new HCH1 ().addChild (sHeaderText);
  }

  /**
   * @param aWPEC
   *        The web page execution context. Never <code>null</code>.
   */
  void getContent (@NonNull WPECTYPE aWPEC);
}
