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
package com.helger.photon.app.html;

import java.util.Collection;
import java.util.Map;

import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.Nullable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.helger.annotation.concurrent.ThreadSafe;
import com.helger.annotation.style.ReturnsMutableCopy;
import com.helger.base.concurrent.SimpleLock;
import com.helger.base.enforce.ValueEnforcer;
import com.helger.collection.commons.CommonsArrayList;
import com.helger.collection.commons.CommonsLinkedHashMap;
import com.helger.collection.commons.ICommonsList;
import com.helger.collection.commons.ICommonsOrderedMap;
import com.helger.html.meta.IMetaElement;
import com.helger.html.meta.MetaElement;
import com.helger.html.meta.MetaElementList;
import com.helger.io.resource.IReadableResource;
import com.helger.web.scope.IRequestWebScopeWithoutResponse;
import com.helger.web.scope.mgr.WebScopeManager;
import com.helger.xml.microdom.util.XMLMapHandler;

/**
 * This class keeps track of all the meta elements that must be included globally or for a single
 * request.
 *
 * @author Philip Helger
 */
@ThreadSafe
public final class PhotonMetaElements
{
  public static final String DEFAULT_FILENAME = "html/metatags.xml";

  private static final String REQUEST_ATTR_METAELEMENTS = PhotonMetaElements.class.getName ();
  private static final Logger LOGGER = LoggerFactory.getLogger (PhotonMetaElements.class);
  private static final MetaElementList GLOBAL = new MetaElementList ();
  private static final SimpleLock LOCK = new SimpleLock ();

  private PhotonMetaElements ()
  {}

  public static void _readMetaElements (@NonNull final IReadableResource aRes, @NonNull final MetaElementList aTarget)
  {
    ValueEnforcer.notNull (aRes, "Res");
    ValueEnforcer.notNull (aTarget, "Target");

    if (aRes.exists ())
    {
      final ICommonsOrderedMap <String, String> aMetaElements = new CommonsLinkedHashMap <> ();
      if (XMLMapHandler.readMap (aRes, aMetaElements).isFailure ())
        LOGGER.error ("Failed to read meta element file " + aRes.getPath ());

      for (final Map.Entry <String, String> aEntry : aMetaElements.entrySet ())
        aTarget.addMetaElement (MetaElement.createMeta (aEntry.getKey (), aEntry.getValue ()));
    }
  }

  public static void readMetaElementsForGlobal (@NonNull final IReadableResource aRes)
  {
    _readMetaElements (aRes, GLOBAL);
  }

  /**
   * Register a new meta element for global scope.
   *
   * @param aMetaElement
   *        The meta element to use. May not be <code>null</code>.
   */
  public static void registerMetaElementForGlobal (@NonNull final IMetaElement aMetaElement)
  {
    GLOBAL.addMetaElement (aMetaElement);
  }

  /**
   * Unregister an existing meta element for global scope.
   *
   * @param sMetaElementName
   *        The meta element name to be removed. May not be <code>null</code>.
   */
  public static void unregisterMetaElementForGlobal (@Nullable final String sMetaElementName)
  {
    GLOBAL.removeMetaElement (sMetaElementName);
  }

  /**
   * Unregister all existing meta elements from global scope.
   */
  public static void unregisterAllMetaElementsFromGlobal ()
  {
    GLOBAL.removeAllMetaElements ();
  }

  /**
   * @return A non-<code>null</code> set with all meta elements to be included globally.
   */
  @NonNull
  @ReturnsMutableCopy
  public static ICommonsList <IMetaElement> getAllRegisteredMetaElementsForGlobal ()
  {
    return GLOBAL.getAllMetaElements ();
  }

  public static void getAllRegisteredMetaElementsForGlobal (@NonNull final Collection <? super IMetaElement> aTarget)
  {
    GLOBAL.getAllMetaElements (aTarget);
  }

  /**
   * @return <code>true</code> if at least a single meta element has been registered globally.
   */
  public static boolean hasRegisteredMetaElementsForGlobal ()
  {
    return GLOBAL.hasMetaElements ();
  }

  @Nullable
  private static MetaElementList _getPerRequestSet (final boolean bCreateIfNotExisting)
  {
    final IRequestWebScopeWithoutResponse aRequestScope = WebScopeManager.getRequestScope ();

    return LOCK.lockedGet ( () -> {
      MetaElementList ret = aRequestScope.attrs ().getCastedValue (REQUEST_ATTR_METAELEMENTS);
      if (ret == null && bCreateIfNotExisting)
      {
        ret = new MetaElementList ();
        aRequestScope.attrs ().putIn (REQUEST_ATTR_METAELEMENTS, ret);
      }
      return ret;
    });
  }

  /**
   * Register a new meta element only for this request
   *
   * @param aMetaElement
   *        The meta element to use. May not be <code>null</code>.
   */
  public static void registerMetaElementForThisRequest (@NonNull final IMetaElement aMetaElement)
  {
    _getPerRequestSet (true).addMetaElement (aMetaElement);
  }

  /**
   * Unregister an existing meta element only from this request
   *
   * @param sMetaElementName
   *        The name of the meta element to be removed. May not be <code>null</code>.
   */
  public static void unregisterMetaElementFromThisRequest (@Nullable final String sMetaElementName)
  {
    final MetaElementList aSet = _getPerRequestSet (false);
    if (aSet != null)
      aSet.removeMetaElement (sMetaElementName);
  }

  /**
   * Unregister all existing meta elements from this request
   */
  public static void unregisterAllMetaElementsFromThisRequest ()
  {
    final MetaElementList aSet = _getPerRequestSet (false);
    if (aSet != null)
      aSet.removeAllMetaElements ();
  }

  /**
   * @return A non-<code>null</code> set with all meta elements to be included in this request.
   */
  @NonNull
  @ReturnsMutableCopy
  public static ICommonsList <IMetaElement> getAllRegisteredMetaElementsForThisRequest ()
  {
    final MetaElementList aSet = _getPerRequestSet (false);
    return aSet == null ? new CommonsArrayList <> () : aSet.getAllMetaElements ();
  }

  public static void getAllRegisteredMetaElementsForThisRequest (@NonNull final Collection <? super IMetaElement> aTarget)
  {
    final MetaElementList aSet = _getPerRequestSet (false);
    if (aSet != null)
      aSet.getAllMetaElements (aTarget);
  }

  /**
   * @return <code>true</code> if at least a single CSS path has been registered for this request
   *         only
   */
  public static boolean hasRegisteredMetaElementsForThisRequest ()
  {
    final MetaElementList aSet = _getPerRequestSet (false);
    return aSet != null && aSet.hasMetaElements ();
  }
}
