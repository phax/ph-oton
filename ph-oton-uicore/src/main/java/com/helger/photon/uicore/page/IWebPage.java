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
package com.helger.photon.uicore.page;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.helger.appbasics.app.page.IPage;
import com.helger.commons.annotations.ReturnsMutableObject;
import com.helger.html.hc.IHCNode;
import com.helger.html.meta.MetaElementList;

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
   * @return The meta elements of this page as a modifiable object.
   */
  @Nonnull
  @ReturnsMutableObject (reason = "design")
  MetaElementList getMetaElements ();

  /**
   * @return The icon for the web page. May be <code>null</code>.
   */
  @Nullable
  IWebPageIcon getIcon ();

  /**
   * Get the headline of the page. By default it is equal to the page name.
   *
   * @param aWPEC
   *        The web page execution context. Never <code>null</code>.
   * @return The header/headline of the page. May be <code>null</code>.
   */
  @Nullable
  String getHeaderText (@Nonnull WPECTYPE aWPEC);

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
  IHCNode getHeaderNode (@Nonnull WPECTYPE aWPEC);

  /**
   * @param aWPEC
   *        The web page execution context. Never <code>null</code>.
   */
  void getContent (@Nonnull WPECTYPE aWPEC);
}
