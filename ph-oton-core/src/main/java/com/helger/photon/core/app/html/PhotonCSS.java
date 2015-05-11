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
package com.helger.photon.core.app.html;

import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.Set;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.helger.commons.annotations.ReturnsMutableCopy;
import com.helger.html.resource.css.ICSSPathProvider;
import com.helger.photon.core.app.resource.CSSResourceSet;
import com.helger.web.scopes.domain.IRequestWebScopeWithoutResponse;
import com.helger.web.scopes.mgr.WebScopeManager;

/**
 * This class keeps track of all the CSS files that must be included for a
 * single request, so that the controls are working properly.
 *
 * @author Philip Helger
 */
public final class PhotonCSS
{
  private static final String REQUEST_ATTR_CSSINCLUDE = PhotonCSS.class.getName ();

  private PhotonCSS ()
  {}

  @Nullable
  private static CSSResourceSet _getPerRequestSet (final boolean bCreateIfNotExisting)
  {
    final IRequestWebScopeWithoutResponse aRequestScope = WebScopeManager.getRequestScope ();
    CSSResourceSet ret = aRequestScope.getCastedAttribute (REQUEST_ATTR_CSSINCLUDE);
    if (ret == null && bCreateIfNotExisting)
    {
      ret = new CSSResourceSet ();
      aRequestScope.setAttribute (REQUEST_ATTR_CSSINCLUDE, ret);
    }
    return ret;
  }

  /**
   * Register a new CSS item only for this request
   *
   * @param aCSSPathProvider
   *        The CSS path provider to use. May not be <code>null</code>.
   */
  public static void registerCSSIncludeForThisRequest (@Nonnull final ICSSPathProvider aCSSPathProvider)
  {
    _getPerRequestSet (true).addItem (aCSSPathProvider);
  }

  /**
   * Unregister an existing CSS item only from this request
   *
   * @param aCSSPathProvider
   *        The CSS path provider to use. May not be <code>null</code>.
   */
  public static void unregisterCSSIncludeFromThisRequest (@Nonnull final ICSSPathProvider aCSSPathProvider)
  {
    final CSSResourceSet aSet = _getPerRequestSet (false);
    if (aSet != null)
      aSet.removeItem (aCSSPathProvider);
  }

  /**
   * Unregister all existing CSS items from this request
   */
  public static void unregisterAllCSSIncludesFromThisRequest ()
  {
    final CSSResourceSet aSet = _getPerRequestSet (false);
    if (aSet != null)
      aSet.removeAll ();
  }

  /**
   * @return A non-<code>null</code> set with all CSS paths to be included in
   *         this request. Order is ensured using LinkedHashSet.
   */
  @Nonnull
  @ReturnsMutableCopy
  public static Set <ICSSPathProvider> getAllRegisteredCSSIncludesForThisRequest ()
  {
    final CSSResourceSet ret = _getPerRequestSet (false);
    return ret == null ? new LinkedHashSet <ICSSPathProvider> () : ret.getAllItems ();
  }

  public static void getAllRegisteredCSSIncludesForThisRequest (@Nonnull final Collection <? super ICSSPathProvider> aTarget)
  {
    final CSSResourceSet aCSSs = _getPerRequestSet (false);
    if (aCSSs != null)
      aCSSs.getAllItems (aTarget);
  }

  /**
   * @return <code>true</code> if at least a single CSS path has been registered
   *         for this request only
   */
  public static boolean hasRegisteredCSSIncludesForThisRequest ()
  {
    final CSSResourceSet aCSSs = _getPerRequestSet (false);
    return aCSSs != null && aCSSs.isNotEmpty ();
  }
}
