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
package com.helger.photon.core.app.context;

import java.util.Map;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.helger.commons.annotation.Nonempty;
import com.helger.commons.url.ISimpleURL;
import com.helger.commons.url.SimpleURL;
import com.helger.html.hc.IHCNode;
import com.helger.photon.basic.app.menu.IMenuItemPage;
import com.helger.photon.core.app.redirect.ForcedRedirectException;

public interface ILayoutExecutionContext extends ISimpleWebExecutionContext
{
  /**
   * @return The selected menu item as specified in the constructor. Never
   *         <code>null</code>.
   */
  @Nonnull
  IMenuItemPage getSelectedMenuItem ();

  /**
   * @return The ID of the selected menu item as specified in the constructor.
   *         Neither <code>null</code> nor empty.
   */
  @Nonnull
  @Nonempty
  default String getSelectedMenuItemID ()
  {
    return getSelectedMenuItem ().getID ();
  }

  /**
   * Get the URL to the current page.
   *
   * @return The non-<code>null</code> URL to the current page (selected menu
   *         item) with the passed parameters.
   */
  @Nonnull
  default SimpleURL getSelfHref ()
  {
    return getLinkToMenuItem (getSelectedMenuItemID ());
  }

  /**
   * Get the URL to the current page with the provided set of parameters.
   *
   * @param aParams
   *        The optional request parameters to be used. May be <code>null</code>
   *        or empty.
   * @return The non-<code>null</code> URL to the current page (selected menu
   *         item) with the passed parameters.
   */
  @Nonnull
  default SimpleURL getSelfHref (@Nullable final Map <String, String> aParams)
  {
    return getLinkToMenuItem (getSelectedMenuItemID (), aParams);
  }

  /**
   * Throw a {@link ForcedRedirectException} with the self href of the current
   * layout context. This immediately stops the request and sends a HTTP
   * redirect to the client.
   *
   * @param aContent
   *        The content to be displayed next time the page is rendered via GET.
   *        May be <code>null</code>.
   * @throws ForcedRedirectException
   *         Every time, since this is the P-R-G indicator.
   */
  default void postRedirectGet (@Nullable final IHCNode aContent) throws ForcedRedirectException
  {
    postRedirectGet (aContent, (Map <String, String>) null);
  }

  /**
   * Throw a {@link ForcedRedirectException} with the self href of the current
   * layout context. This immediately stops the request and sends a HTTP
   * redirect to the client.
   *
   * @param aContent
   *        The content to be displayed next time the page is rendered via GET.
   *        May be <code>null</code>.
   * @param aAdditionalParameters
   *        Additional parameters to be added to the request. May be
   *        <code>null</code> or empty.
   * @throws ForcedRedirectException
   *         Every time, since this is the P-R-G indicator.
   */
  void postRedirectGet (@Nullable IHCNode aContent,
                        @Nullable Map <String, String> aAdditionalParameters) throws ForcedRedirectException;

  /**
   * Throw a {@link ForcedRedirectException} with the passed URL. This
   * immediately stops the request and sends a HTTP redirect to the client.
   *
   * @param aTargetURL
   *        The target URL to redirect to via GET. May be <code>null</code>.
   * @throws ForcedRedirectException
   *         Every time, since this is the P-R-G indicator.
   */
  void postRedirectGet (@Nonnull ISimpleURL aTargetURL) throws ForcedRedirectException;
}
