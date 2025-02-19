/*
 * Copyright (C) 2014-2025 Philip Helger (www.helger.com)
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
package com.helger.photon.app.html;

import java.util.Collection;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.concurrent.ThreadSafe;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.helger.commons.ValueEnforcer;
import com.helger.commons.annotation.ReturnsMutableCopy;
import com.helger.commons.collection.impl.CommonsLinkedHashSet;
import com.helger.commons.collection.impl.ICommonsOrderedSet;
import com.helger.commons.concurrent.SimpleLock;
import com.helger.commons.io.resource.IReadableResource;
import com.helger.commons.string.StringHelper;
import com.helger.html.resource.js.ConstantJSPathProvider;
import com.helger.html.resource.js.IJSPathProvider;
import com.helger.photon.app.PhotonAppSettings;
import com.helger.web.scope.IRequestWebScopeWithoutResponse;
import com.helger.web.scope.mgr.WebScopeManager;
import com.helger.xml.microdom.IMicroDocument;
import com.helger.xml.microdom.IMicroElement;
import com.helger.xml.microdom.serialize.MicroReader;

/**
 * This class keeps track of all the JS files that must be included globally for
 * a single request.
 *
 * @author Philip Helger
 */
@ThreadSafe
public final class PhotonJS
{
  public static final String DEFAULT_FILENAME = "html/js.xml";

  private static final String REQUEST_ATTR_JSRESOURCES = PhotonJS.class.getName ();
  private static final Logger LOGGER = LoggerFactory.getLogger (PhotonJS.class);
  private static final JSResourceSet GLOBAL = new JSResourceSet ();
  private static final SimpleLock LOCK = new SimpleLock ();

  private PhotonJS ()
  {}

  public static void _readJSIncludes (@Nonnull final IReadableResource aRes, @Nonnull final JSResourceSet aTarget)
  {
    ValueEnforcer.notNull (aRes, "Res");
    ValueEnforcer.notNull (aTarget, "Target");

    final IMicroDocument aDoc = aRes.exists () ? MicroReader.readMicroXML (aRes) : null;
    if (aDoc != null)
      for (final IMicroElement eChild : aDoc.getDocumentElement ().getAllChildElements ("js"))
      {
        final String sPath = eChild.getAttributeValue ("path");
        if (StringHelper.hasNoText (sPath))
        {
          LOGGER.error ("Found JS item without a path in " + aRes.getPath ());
          continue;
        }

        // Just a consistency check to see if the resource is valid
        final IReadableResource aChildRes = PhotonAppSettings.getURIToURLConverter ().getAsResource (sPath);
        if (!aChildRes.exists ())
          throw new IllegalStateException ("The provided global JS resource '" +
                                           sPath +
                                           "' resolved to '" +
                                           aChildRes +
                                           "' does NOT exist!");

        final String sConditionalComment = eChild.getAttributeValue ("condcomment");

        // Add to target
        aTarget.addItem (ConstantJSPathProvider.builder ()
                                               .path (sPath)
                                               .minifiedPathFromPath ()
                                               .conditionalComment (sConditionalComment)
                                               .build ());
      }
  }

  public static void readJSIncludesForGlobal (@Nonnull final IReadableResource aRes)
  {
    _readJSIncludes (aRes, GLOBAL);
  }

  /**
   * Register a new JS item for global scope.
   *
   * @param aJSPathProvider
   *        The JS path provider to use. May not be <code>null</code>.
   */
  public static void registerJSIncludeForGlobal (@Nonnull final IJSPathProvider aJSPathProvider)
  {
    GLOBAL.addItem (aJSPathProvider);
  }

  /**
   * Register a new JS item for global scope.
   *
   * @param nIndex
   *        The index to be used. If the value is &lt; 0 the value is ignored
   *        and item is appended.
   * @param aJSPathProvider
   *        The JS path provider to use. May not be <code>null</code>.
   */
  public static void registerJSIncludeForGlobal (final int nIndex, @Nonnull final IJSPathProvider aJSPathProvider)
  {
    GLOBAL.addItem (nIndex, aJSPathProvider);
  }

  /**
   * Unregister an existing JS item for global scope.
   *
   * @param aJSPathProvider
   *        The JS path provider to use. May not be <code>null</code>.
   */
  public static void unregisterJSIncludeForGlobal (@Nonnull final IJSPathProvider aJSPathProvider)
  {
    GLOBAL.removeItem (aJSPathProvider);
  }

  /**
   * Unregister all existing JS items from global scope.
   */
  public static void unregisterAllJSIncludesFromGlobal ()
  {
    GLOBAL.removeAll ();
  }

  /**
   * @return A non-<code>null</code> set with all JS paths to be included
   *         globally.
   */
  @Nonnull
  @ReturnsMutableCopy
  public static ICommonsOrderedSet <IJSPathProvider> getAllRegisteredJSIncludesForGlobal ()
  {
    return GLOBAL.getAllItems ();
  }

  public static void getAllRegisteredJSIncludesForGlobal (@Nonnull final Collection <? super IJSPathProvider> aTarget)
  {
    GLOBAL.getAllItems (aTarget);
  }

  /**
   * @return <code>true</code> if at least a single JS path has been registered
   *         globally.
   */
  public static boolean hasRegisteredJSIncludesForGlobal ()
  {
    return GLOBAL.isNotEmpty ();
  }

  @Nullable
  private static JSResourceSet _getPerRequestSet (final boolean bCreateIfNotExisting)
  {
    final IRequestWebScopeWithoutResponse aRequestScope = WebScopeManager.getRequestScope ();

    return LOCK.lockedGet ( () -> {
      JSResourceSet ret = aRequestScope.attrs ().getCastedValue (REQUEST_ATTR_JSRESOURCES);
      if (ret == null && bCreateIfNotExisting)
      {
        ret = new JSResourceSet ();
        aRequestScope.attrs ().putIn (REQUEST_ATTR_JSRESOURCES, ret);
      }
      return ret;
    });
  }

  /**
   * Register a new JS item only for this request
   *
   * @param aJSPathProvider
   *        The JS path provider to use. May not be <code>null</code>.
   */
  public static void registerJSIncludeForThisRequest (@Nonnull final IJSPathProvider aJSPathProvider)
  {
    _getPerRequestSet (true).addItem (aJSPathProvider);
  }

  /**
   * Unregister a existing JS item only from this request
   *
   * @param aJSPathProvider
   *        The JS path provider to use. May not be <code>null</code>.
   */
  public static void unregisterJSIncludeFromThisRequest (@Nonnull final IJSPathProvider aJSPathProvider)
  {
    final JSResourceSet aSet = _getPerRequestSet (false);
    if (aSet != null)
      aSet.removeItem (aJSPathProvider);
  }

  /**
   * Unregister all existing JS items from this request
   */
  public static void unregisterAllJSIncludesFromThisRequest ()
  {
    final JSResourceSet aSet = _getPerRequestSet (false);
    if (aSet != null)
      aSet.removeAll ();
  }

  /**
   * @return A non-<code>null</code> set with all JS paths to be included in
   *         this request.
   */
  @Nonnull
  @ReturnsMutableCopy
  public static ICommonsOrderedSet <IJSPathProvider> getAllRegisteredJSIncludesForThisRequest ()
  {
    final JSResourceSet aSet = _getPerRequestSet (false);
    if (aSet == null)
      return new CommonsLinkedHashSet <> ();
    aSet.markAsCollected ();
    return aSet.getAllItems ();
  }

  public static void getAllRegisteredJSIncludesForThisRequest (@Nonnull final Collection <? super IJSPathProvider> aTarget)
  {
    final JSResourceSet aSet = _getPerRequestSet (false);
    if (aSet != null)
    {
      aSet.markAsCollected ();
      aSet.getAllItems (aTarget);
    }
  }

  /**
   * @return <code>true</code> if at least a single JS path has been registered
   *         for this request only
   */
  public static boolean hasRegisteredJSIncludesForThisRequest ()
  {
    final JSResourceSet aSet = _getPerRequestSet (false);
    return aSet != null && aSet.isNotEmpty ();
  }
}
