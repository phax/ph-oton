/*
 * Copyright (C) 2014-2024 Philip Helger (www.helger.com)
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
import com.helger.commons.regex.RegExHelper;
import com.helger.commons.string.StringHelper;
import com.helger.css.CSSFilenameHelper;
import com.helger.css.media.CSSMediaList;
import com.helger.css.media.ECSSMedium;
import com.helger.html.resource.IHTMLResourceProvider;
import com.helger.html.resource.css.ConstantCSSPathProvider;
import com.helger.html.resource.css.ICSSPathProvider;
import com.helger.photon.app.PhotonAppSettings;
import com.helger.web.scope.IRequestWebScopeWithoutResponse;
import com.helger.web.scope.mgr.WebScopeManager;
import com.helger.xml.microdom.IMicroDocument;
import com.helger.xml.microdom.IMicroElement;
import com.helger.xml.microdom.serialize.MicroReader;

/**
 * This class keeps track of all the CSS files that must be included globally or
 * for a single request.
 *
 * @author Philip Helger
 */
@ThreadSafe
public final class PhotonCSS
{
  public static final String DEFAULT_FILENAME = "html/css.xml";

  private static final String REQUEST_ATTR_CSSRESOURCES = PhotonCSS.class.getName ();
  private static final Logger LOGGER = LoggerFactory.getLogger (PhotonCSS.class);
  private static final CSSResourceSet GLOBAL = new CSSResourceSet ();
  private static final SimpleLock LOCK = new SimpleLock ();

  private PhotonCSS ()
  {}

  public static void _readCSSIncludes (@Nonnull final IReadableResource aRes, @Nonnull final CSSResourceSet aTarget)
  {
    ValueEnforcer.notNull (aRes, "Res");
    ValueEnforcer.notNull (aTarget, "Target");

    final IMicroDocument aDoc = aRes.exists () ? MicroReader.readMicroXML (aRes) : null;
    if (aDoc != null)
      for (final IMicroElement eChild : aDoc.getDocumentElement ().getAllChildElements ("css"))
      {
        final String sPath = eChild.getAttributeValue ("path");
        if (StringHelper.hasNoText (sPath))
        {
          LOGGER.error ("Found CSS item without a path in " + aRes.getPath ());
          continue;
        }

        // Just a consistency check to see if the resource is valid
        final IReadableResource aChildRes = PhotonAppSettings.getURIToURLConverter ().getAsResource (sPath);
        if (!aChildRes.exists ())
          throw new IllegalStateException ("The provided global CSS resource '" +
                                           sPath +
                                           "' resolved to '" +
                                           aChildRes +
                                           "' does NOT exist!");

        final String sConditionalComment = eChild.getAttributeValue ("condcomment");

        final String sMedia = eChild.getAttributeValue ("media");
        final CSSMediaList aMediaList = new CSSMediaList ();
        if (sMedia != null)
          for (final String sMedium : RegExHelper.getSplitToArray (sMedia, ",\\s*"))
          {
            final ECSSMedium eMedium = ECSSMedium.getFromNameOrNull (sMedium);
            if (eMedium == null)
            {
              LOGGER.warn ("CSS item '" + sPath + "' in " + aRes.getPath () + " has an invalid medium '" + sMedium + "' - ignoring");
              continue;
            }
            aMediaList.addMedium (eMedium);
          }

        // Add to target
        aTarget.addItem (new ConstantCSSPathProvider (sPath,
                                                      CSSFilenameHelper.getMinifiedCSSFilename (sPath),
                                                      sConditionalComment,
                                                      aMediaList,
                                                      IHTMLResourceProvider.DEFAULT_IS_BUNDLABLE));
      }
  }

  public static void readCSSIncludesForGlobal (@Nonnull final IReadableResource aRes)
  {
    _readCSSIncludes (aRes, GLOBAL);
  }

  /**
   * Register a new CSS item for global scope.
   *
   * @param aCSSPathProvider
   *        The CSS path provider to use. May not be <code>null</code>.
   */
  public static void registerCSSIncludeForGlobal (@Nonnull final ICSSPathProvider aCSSPathProvider)
  {
    GLOBAL.addItem (aCSSPathProvider);
  }

  /**
   * Register a new CSS item for global scope.
   *
   * @param nIndex
   *        The index to be used. If the value is &lt; 0 the value is ignored
   *        and item is appended.
   * @param aCSSPathProvider
   *        The CSS path provider to use. May not be <code>null</code>.
   */
  public static void registerCSSIncludeForGlobal (final int nIndex, @Nonnull final ICSSPathProvider aCSSPathProvider)
  {
    GLOBAL.addItem (nIndex, aCSSPathProvider);
  }

  /**
   * Unregister an existing CSS item for global scope.
   *
   * @param aCSSPathProvider
   *        The CSS path provider to use. May not be <code>null</code>.
   */
  public static void unregisterCSSIncludeForGlobal (@Nonnull final ICSSPathProvider aCSSPathProvider)
  {
    GLOBAL.removeItem (aCSSPathProvider);
  }

  /**
   * Unregister all existing CSS items from global scope.
   */
  public static void unregisterAllCSSIncludesFromGlobal ()
  {
    GLOBAL.removeAll ();
  }

  /**
   * @return A non-<code>null</code> set with all CSS paths to be included
   *         globally.
   */
  @Nonnull
  @ReturnsMutableCopy
  public static ICommonsOrderedSet <ICSSPathProvider> getAllRegisteredCSSIncludesForGlobal ()
  {
    return GLOBAL.getAllItems ();
  }

  public static void getAllRegisteredCSSIncludesForGlobal (@Nonnull final Collection <? super ICSSPathProvider> aTarget)
  {
    GLOBAL.getAllItems (aTarget);
  }

  /**
   * @return <code>true</code> if at least a single CSS path has been registered
   *         globally.
   */
  public static boolean hasRegisteredCSSIncludesForGlobal ()
  {
    return GLOBAL.isNotEmpty ();
  }

  @Nullable
  private static CSSResourceSet _getPerRequestSet (final boolean bCreateIfNotExisting)
  {
    final IRequestWebScopeWithoutResponse aRequestScope = WebScopeManager.getRequestScope ();

    return LOCK.lockedGet ( () -> {
      CSSResourceSet ret = aRequestScope.attrs ().getCastedValue (REQUEST_ATTR_CSSRESOURCES);
      if (ret == null && bCreateIfNotExisting)
      {
        ret = new CSSResourceSet ();
        aRequestScope.attrs ().putIn (REQUEST_ATTR_CSSRESOURCES, ret);
      }
      return ret;
    });
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
   *         this request.
   */
  @Nonnull
  @ReturnsMutableCopy
  public static ICommonsOrderedSet <ICSSPathProvider> getAllRegisteredCSSIncludesForThisRequest ()
  {
    final CSSResourceSet aSet = _getPerRequestSet (false);
    if (aSet == null)
      return new CommonsLinkedHashSet <> ();
    aSet.markAsCollected ();
    return aSet.getAllItems ();
  }

  public static void getAllRegisteredCSSIncludesForThisRequest (@Nonnull final Collection <? super ICSSPathProvider> aTarget)
  {
    final CSSResourceSet aSet = _getPerRequestSet (false);
    if (aSet != null)
    {
      aSet.markAsCollected ();
      aSet.getAllItems (aTarget);
    }
  }

  /**
   * @return <code>true</code> if at least a single CSS path has been registered
   *         for this request only
   */
  public static boolean hasRegisteredCSSIncludesForThisRequest ()
  {
    final CSSResourceSet aSet = _getPerRequestSet (false);
    return aSet != null && aSet.isNotEmpty ();
  }
}
