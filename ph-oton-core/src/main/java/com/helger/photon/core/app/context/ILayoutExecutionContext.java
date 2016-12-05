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

import java.util.Locale;
import java.util.Map;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.helger.commons.annotation.Nonempty;
import com.helger.commons.url.ISimpleURL;
import com.helger.commons.url.SimpleURL;
import com.helger.html.hc.IHCNode;
import com.helger.photon.basic.app.menu.IMenuItemPage;
import com.helger.photon.basic.app.page.IPage;
import com.helger.photon.core.app.redirect.ForcedRedirectException;
import com.helger.photon.core.app.redirect.ForcedRedirectManager;

/**
 * Base interface for a current request context that also has "layout"
 * information, meaning the currently selected menu item.
 *
 * @author Philip Helger
 */
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

  @Nonnull
  default IPage getSelectedPage ()
  {
    final IMenuItemPage aSelectedMenuItem = getSelectedMenuItem ();

    // Resolve the page of the selected menu item (if found)
    if (aSelectedMenuItem.matchesDisplayFilter ())
    {
      // Only if we have display rights!
      return aSelectedMenuItem.getPage ();
    }

    // No rights -> goto start page
    return getMenuTree ().getDefaultMenuItem ().getPage ();
  }

  /**
   * Get the URL to the current page in the current locale.
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
   * Get the URL to the current page in the specified locale.
   *
   * @param aDisplayLocale
   *        The specific display locale to be used. May not be
   *        <code>null</code>.
   * @return The non-<code>null</code> URL to the current page (selected menu
   *         item) with the passed parameters.
   * @since 7.0.2
   */
  @Nonnull
  default SimpleURL getSelfHref (@Nonnull final Locale aDisplayLocale)
  {
    return getLinkToMenuItem (aDisplayLocale, getSelectedMenuItemID ());
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
   * @deprecated Use {@link #postRedirectGetInternal(IHCNode)} instead
   */
  @Deprecated
  default void postRedirectGet (@Nullable final IHCNode aContent) throws ForcedRedirectException
  {
    postRedirectGetInternal (aContent);
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
  default void postRedirectGetInternal (@Nullable final IHCNode aContent) throws ForcedRedirectException
  {
    postRedirectGetInternal (aContent, (Map <String, String>) null);
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
   * @deprecated Use {@link #postRedirectGetInternal(IHCNode,Map)} instead
   */
  @Deprecated
  default void postRedirectGet (@Nullable final IHCNode aContent,
                                @Nullable final Map <String, String> aAdditionalParameters) throws ForcedRedirectException
  {
    postRedirectGetInternal (aContent, aAdditionalParameters);
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
  default void postRedirectGetInternal (@Nullable final IHCNode aContent,
                                        @Nullable final Map <String, String> aAdditionalParameters) throws ForcedRedirectException
  {
    // Add the "PRG active" parameter
    postRedirectGet (getSelfHref ().add (ForcedRedirectManager.REQUEST_PARAMETER_PRG_ACTIVE)
                                   .addAll (aAdditionalParameters),
                     aContent);
  }

  /**
   * Throw a {@link ForcedRedirectException} with the provided internal URL and
   * the passed content. This immediately stops the request and sends a HTTP
   * redirect to the client. Important: this only works for application internal
   * URLs, because external URLs will not have access to the provided content.
   *
   * @param aTargetURL
   *        The target URL to redirect to via GET. May be <code>null</code>.
   * @param aContent
   *        The content to be displayed next time the page is rendered via GET.
   *        May be <code>null</code>.
   * @throws ForcedRedirectException
   *         Every time, since this is the P-R-G indicator.
   */
  default void postRedirectGetInternal (@Nonnull final SimpleURL aTargetURL,
                                        @Nullable final IHCNode aContent) throws ForcedRedirectException
  {
    postRedirectGet (aTargetURL.add (ForcedRedirectManager.REQUEST_PARAMETER_PRG_ACTIVE), aContent);
  }

  /**
   * Throw a {@link ForcedRedirectException} with the passed URL. This
   * immediately stops the request and sends a HTTP redirect to the client.
   *
   * @param aTargetURL
   *        The target URL to redirect to via GET. May be <code>null</code>.
   * @throws ForcedRedirectException
   *         Every time, since this is the P-R-G indicator.
   * @deprecated Use {@link #postRedirectGetExternal(ISimpleURL)} instead
   */
  @Deprecated
  default void postRedirectGet (@Nonnull final ISimpleURL aTargetURL) throws ForcedRedirectException
  {
    postRedirectGetExternal (aTargetURL);
  }

  /**
   * Throw a {@link ForcedRedirectException} with the passed URL. This
   * immediately stops the request and sends a HTTP redirect to the client.
   *
   * @param aTargetURL
   *        The target URL to redirect to via GET. May be <code>null</code>.
   * @throws ForcedRedirectException
   *         Every time, since this is the P-R-G indicator.
   */
  default void postRedirectGetExternal (@Nonnull final ISimpleURL aTargetURL) throws ForcedRedirectException
  {
    postRedirectGet (aTargetURL, (IHCNode) null);
  }

  /**
   * Throw a {@link ForcedRedirectException} with the provided URL and the
   * passed content. This immediately stops the request and sends a HTTP
   * redirect to the client. This method should not be called explicitly.
   * Instead the "external" or "internal" PRG methods should be called.
   *
   * @param aTargetURL
   *        The target URL to redirect to via GET. May be <code>null</code>.
   * @param aContent
   *        The content to be displayed next time the page is rendered via GET.
   *        May be <code>null</code>.
   * @throws ForcedRedirectException
   *         Every time, since this is the P-R-G indicator.
   * @see #postRedirectGetInternal(IHCNode)
   * @see #postRedirectGetInternal(IHCNode, Map)
   * @see #postRedirectGetInternal(SimpleURL, IHCNode)
   * @see #postRedirectGetExternal(ISimpleURL)
   */
  void postRedirectGet (@Nonnull ISimpleURL aTargetURL, @Nullable IHCNode aContent) throws ForcedRedirectException;
}
