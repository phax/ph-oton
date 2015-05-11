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

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.concurrent.ThreadSafe;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.helger.commons.ValueEnforcer;
import com.helger.commons.annotations.ReturnsMutableCopy;
import com.helger.commons.io.IReadableResource;
import com.helger.commons.microdom.reader.XMLMapHandler;
import com.helger.html.meta.IMetaElement;
import com.helger.html.meta.MetaElement;
import com.helger.html.meta.MetaElementList;
import com.helger.web.scopes.domain.IRequestWebScopeWithoutResponse;
import com.helger.web.scopes.mgr.WebScopeManager;

/**
 * This class keeps track of all the meta elements that must be included
 * globally or for a single request.
 *
 * @author Philip Helger
 */
@ThreadSafe
public final class PhotonMetaElements
{
  public static final String DEFAULT_FILENAME = "html/metatags.xml";

  private static final String REQUEST_ATTR_METAELEMENTS = PhotonMetaElements.class.getName ();
  private static final Logger s_aLogger = LoggerFactory.getLogger (PhotonMetaElements.class);
  private static final MetaElementList s_aGlobal = new MetaElementList ();
  private static final Lock s_aLock = new ReentrantLock ();

  private PhotonMetaElements ()
  {}

  public static void _readMetaElements (@Nonnull final IReadableResource aRes, @Nonnull final MetaElementList aTarget)
  {
    ValueEnforcer.notNull (aRes, "Res");
    ValueEnforcer.notNull (aTarget, "Target");

    if (aRes.exists ())
    {
      final Map <String, String> aMetaElements = new LinkedHashMap <String, String> ();
      if (XMLMapHandler.readMap (aRes, aMetaElements).isFailure ())
        s_aLogger.error ("Failed to read meta element file " + aRes.getPath ());

      for (final Map.Entry <String, String> aEntry : aMetaElements.entrySet ())
        aTarget.addMetaElement (new MetaElement (aEntry.getKey (), aEntry.getValue ()));
    }
  }

  public static void readMetaElementsForGlobal (@Nonnull final IReadableResource aRes)
  {
    _readMetaElements (aRes, s_aGlobal);
  }

  /**
   * Register a new meta element for global scope.
   *
   * @param aMetaElement
   *        The meta element to use. May not be <code>null</code>.
   */
  public static void registerMetaElementForGlobal (@Nonnull final IMetaElement aMetaElement)
  {
    s_aGlobal.addMetaElement (aMetaElement);
  }

  /**
   * Unregister an existing meta element for global scope.
   *
   * @param sMetaElementName
   *        The meta element name to be removed. May not be <code>null</code>.
   */
  public static void unregisterMetaElementForGlobal (@Nullable final String sMetaElementName)
  {
    s_aGlobal.removeMetaElement (sMetaElementName);
  }

  /**
   * Unregister all existing meta elements from global scope.
   */
  public static void unregisterAllMetaElementsFromGlobal ()
  {
    s_aGlobal.removeAllMetaElements ();
  }

  /**
   * @return A non-<code>null</code> set with all meta elements to be included
   *         globally.
   */
  @Nonnull
  @ReturnsMutableCopy
  public static List <IMetaElement> getAllRegisteredMetaElementsForGlobal ()
  {
    return s_aGlobal.getAllMetaElements ();
  }

  public static void getAllRegisteredMetaElementsForGlobal (@Nonnull final Collection <? super IMetaElement> aTarget)
  {
    ValueEnforcer.notNull (aTarget, "Target");
    aTarget.addAll (s_aGlobal.getAllMetaElements ());
  }

  /**
   * @return <code>true</code> if at least a single meta element has been
   *         registered globally.
   */
  public static boolean hasRegisteredMetaElementsForGlobal ()
  {
    return s_aGlobal.hasMetaElements ();
  }

  @Nullable
  private static MetaElementList _getPerRequestSet (final boolean bCreateIfNotExisting)
  {
    final IRequestWebScopeWithoutResponse aRequestScope = WebScopeManager.getRequestScope ();

    s_aLock.lock ();
    try
    {
      MetaElementList ret = aRequestScope.getCastedAttribute (REQUEST_ATTR_METAELEMENTS);
      if (ret == null && bCreateIfNotExisting)
      {
        ret = new MetaElementList ();
        aRequestScope.setAttribute (REQUEST_ATTR_METAELEMENTS, ret);
      }
      return ret;
    }
    finally
    {
      s_aLock.unlock ();
    }
  }

  /**
   * Register a new meta element only for this request
   *
   * @param aMetaElement
   *        The meta element to use. May not be <code>null</code>.
   */
  public static void registerMetaElementForThisRequest (@Nonnull final IMetaElement aMetaElement)
  {
    _getPerRequestSet (true).addMetaElement (aMetaElement);
  }

  /**
   * Unregister an existing meta element only from this request
   *
   * @param sMetaElementName
   *        The name of the meta element to be removed. May not be
   *        <code>null</code>.
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
   * @return A non-<code>null</code> set with all meta elements to be included
   *         in this request.
   */
  @Nonnull
  @ReturnsMutableCopy
  public static List <IMetaElement> getAllRegisteredMetaElementsForThisRequest ()
  {
    final MetaElementList aSet = _getPerRequestSet (false);
    return aSet == null ? new ArrayList <IMetaElement> () : aSet.getAllMetaElements ();
  }

  public static void getAllRegisteredMetaElementsForThisRequest (@Nonnull final Collection <? super IMetaElement> aTarget)
  {
    ValueEnforcer.notNull (aTarget, "Target");
    final MetaElementList aSet = _getPerRequestSet (false);
    if (aSet != null)
      aTarget.addAll (aSet.getAllMetaElements ());
  }

  /**
   * @return <code>true</code> if at least a single CSS path has been registered
   *         for this request only
   */
  public static boolean hasRegisteredMetaElementsForThisRequest ()
  {
    final MetaElementList aSet = _getPerRequestSet (false);
    return aSet != null && aSet.hasMetaElements ();
  }
}
