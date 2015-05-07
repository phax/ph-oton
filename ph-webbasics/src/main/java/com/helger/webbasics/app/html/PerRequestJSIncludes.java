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
package com.helger.webbasics.app.html;

import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.Set;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.helger.commons.ValueEnforcer;
import com.helger.commons.annotations.ReturnsMutableCopy;
import com.helger.commons.collections.CollectionHelper;
import com.helger.html.resource.js.IJSPathProvider;
import com.helger.web.scopes.domain.IRequestWebScopeWithoutResponse;
import com.helger.web.scopes.mgr.WebScopeManager;

/**
 * This class keeps track of all the JS files that must be included for a single
 * request, so that the controls are working properly.
 *
 * @author Philip Helger
 */
public final class PerRequestJSIncludes
{
  private static final String REQUEST_ATTR_JSINCLUDE = PerRequestJSIncludes.class.getName ();

  private PerRequestJSIncludes ()
  {}

  @Nullable
  private static Set <IJSPathProvider> _getPerRequestSet (final boolean bCreateIfNotExisting)
  {
    final IRequestWebScopeWithoutResponse aRequestScope = WebScopeManager.getRequestScope ();
    Set <IJSPathProvider> ret = aRequestScope.getCastedAttribute (REQUEST_ATTR_JSINCLUDE);
    if (ret == null && bCreateIfNotExisting)
    {
      ret = new LinkedHashSet <IJSPathProvider> ();
      aRequestScope.setAttribute (REQUEST_ATTR_JSINCLUDE, ret);
    }
    return ret;
  }

  /**
   * Register a new JS item only for this request
   *
   * @param aJSPathProvider
   *        The JS path provider to use. May not be <code>null</code>.
   */
  public static void registerJSIncludeForThisRequest (@Nonnull final IJSPathProvider aJSPathProvider)
  {
    ValueEnforcer.notNull (aJSPathProvider, "JSPathProvider");

    _getPerRequestSet (true).add (aJSPathProvider);
  }

  /**
   * Unregister a existing JS item only from this request
   *
   * @param aJSPathProvider
   *        The JS path provider to use. May not be <code>null</code>.
   */
  public static void unregisterJSIncludeFromThisRequest (@Nonnull final IJSPathProvider aJSPathProvider)
  {
    ValueEnforcer.notNull (aJSPathProvider, "JSPathProvider");

    _getPerRequestSet (true).remove (aJSPathProvider);
  }

  /**
   * Unregister all existing JS items from this request
   */
  public static void unregisterAllJSIncludesFromThisRequest ()
  {
    final Set <IJSPathProvider> aSet = _getPerRequestSet (false);
    if (aSet != null)
      aSet.clear ();
  }

  /**
   * @return A non-<code>null</code> set with all JS paths to be included in
   *         this request. Order is ensured using LinkedHashSet.
   */
  @Nonnull
  @ReturnsMutableCopy
  public static Set <IJSPathProvider> getAllRegisteredJSIncludesForThisRequest ()
  {
    final Set <IJSPathProvider> ret = _getPerRequestSet (false);
    return CollectionHelper.newOrderedSet (ret);
  }

  public static void getAllRegisteredJSIncludesForThisRequest (@Nonnull final Collection <? super IJSPathProvider> aTarget)
  {
    final Set <IJSPathProvider> aJSs = _getPerRequestSet (false);
    if (aJSs != null)
      aTarget.addAll (aJSs);
  }

  /**
   * @return <code>true</code> if at least a single JS path has been registered
   *         for this request only
   */
  public static boolean hasRegisteredJSIncludesForThisRequest ()
  {
    return CollectionHelper.isNotEmpty (_getPerRequestSet (false));
  }
}
