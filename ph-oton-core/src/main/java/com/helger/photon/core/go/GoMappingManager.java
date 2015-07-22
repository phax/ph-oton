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
package com.helger.photon.core.go;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.concurrent.GuardedBy;
import javax.annotation.concurrent.ThreadSafe;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.helger.commons.ValueEnforcer;
import com.helger.commons.annotation.ELockType;
import com.helger.commons.annotation.MustBeLocked;
import com.helger.commons.annotation.Nonempty;
import com.helger.commons.annotation.ReturnsMutableCopy;
import com.helger.commons.callback.INonThrowingRunnableWithParameter;
import com.helger.commons.collection.CollectionHelper;
import com.helger.commons.microdom.IMicroDocument;
import com.helger.commons.microdom.IMicroElement;
import com.helger.commons.microdom.MicroDocument;
import com.helger.commons.state.EChange;
import com.helger.commons.string.StringHelper;
import com.helger.commons.string.StringParser;
import com.helger.commons.string.ToStringGenerator;
import com.helger.commons.url.SimpleURL;
import com.helger.photon.basic.app.dao.impl.AbstractSimpleDAO;
import com.helger.photon.basic.app.dao.impl.DAOException;
import com.helger.photon.basic.app.menu.IMenuTree;
import com.helger.photon.basic.app.request.ApplicationRequestManager;
import com.helger.photon.basic.app.request.IRequestManager;
import com.helger.photon.core.mgr.PhotonCoreManager;
import com.helger.photon.core.url.LinkHelper;
import com.helger.web.scope.IRequestWebScopeWithoutResponse;
import com.helger.web.scope.mgr.WebScopeManager;

/**
 * Manager for {@link GoMappingItem} objects.
 *
 * @author Philip Helger
 */
@ThreadSafe
public class GoMappingManager extends AbstractSimpleDAO
{
  public static final boolean DEFAULT_EDITABLE = true;

  private static final String ELEMENT_ROOT = "gomappings";
  private static final String ELEMENT_EXTERNAL = "external";
  private static final String ELEMENT_INTERNAL = "internal";
  private static final String ATTR_KEY = "key";
  private static final String ATTR_HREF = "href";
  private static final String ATTR_EDITABLE = "editable";
  private static final Logger s_aLogger = LoggerFactory.getLogger (GoMappingManager.class);

  @GuardedBy ("m_aRWLock")
  private final Map <String, GoMappingItem> m_aMap = new HashMap <String, GoMappingItem> ();

  public GoMappingManager (@Nullable final String sFilename) throws DAOException
  {
    super (sFilename);
    initialRead ();
  }

  @Nonnull
  private static String _unifyKey (@Nonnull final String sKey)
  {
    return sKey.toLowerCase (Locale.US);
  }

  @MustBeLocked (ELockType.WRITE)
  private EChange _addItem (@Nonnull final GoMappingItem aItem, final boolean bAllowOverwrite)
  {
    ValueEnforcer.notNull (aItem, "Item");

    final String sKey = _unifyKey (aItem.getKey ());
    final GoMappingItem aOldItem = m_aMap.get (sKey);
    if (aOldItem != null)
    {
      if (bAllowOverwrite)
      {
        // Same item as before
        if (aOldItem.equals (aItem))
          return EChange.UNCHANGED;
      }
      else
        throw new IllegalArgumentException ("Another go-mapping with the key '" + sKey + "' is already registered!");
    }
    m_aMap.put (sKey, aItem);
    return EChange.CHANGED;
  }

  public static void readFromXML (@Nonnull final IMicroDocument aDoc,
                                  @Nonnull final INonThrowingRunnableWithParameter <GoMappingItem> aCallback)
  {
    ValueEnforcer.notNull (aDoc, "Doc");
    ValueEnforcer.notNull (aCallback, "Callback");

    for (final IMicroElement eItem : aDoc.getDocumentElement ().getAllChildElements ())
    {
      final String sTagName = eItem.getTagName ();
      final String sKey = eItem.getAttributeValue (ATTR_KEY);
      final String sHref = eItem.getAttributeValue (ATTR_HREF);
      final String sIsEditable = eItem.getAttributeValue (ATTR_EDITABLE);
      final boolean bIsEditable = StringParser.parseBool (sIsEditable, DEFAULT_EDITABLE);

      if (ELEMENT_EXTERNAL.equals (sTagName))
        aCallback.run (new GoMappingItem (sKey, false, sHref, bIsEditable));
      else
        if (ELEMENT_INTERNAL.equals (sTagName))
          aCallback.run (new GoMappingItem (sKey, true, sHref, bIsEditable));
        else
          s_aLogger.error ("Unsupported go-mapping tag '" + sTagName + "'");
    }
  }

  @Override
  protected EChange onRead (@Nonnull final IMicroDocument aDoc)
  {
    readFromXML (aDoc, new INonThrowingRunnableWithParameter <GoMappingItem> ()
    {
      public void run (final GoMappingItem aCurrentObject)
      {
        _addItem (aCurrentObject, false);
      }
    });
    return EChange.UNCHANGED;
  }

  @Override
  protected IMicroDocument createWriteData ()
  {
    final String sContextPath = WebScopeManager.getGlobalScope ().getContextPath ();
    final IMicroDocument ret = new MicroDocument ();
    final IMicroElement eRoot = ret.appendElement (ELEMENT_ROOT);
    for (final GoMappingItem aItem : CollectionHelper.getSortedByKey (m_aMap).values ())
    {
      if (aItem.isInternal ())
      {
        final IMicroElement eItem = eRoot.appendElement (ELEMENT_INTERNAL);
        eItem.setAttribute (ATTR_KEY, aItem.getKey ());
        // Remove the context path, when deserializing stuff
        eItem.setAttribute (ATTR_HREF, StringHelper.trimStart (aItem.getTargetURLAsString (), sContextPath));
      }
      else
      {
        final IMicroElement eItem = eRoot.appendElement (ELEMENT_EXTERNAL);
        eItem.setAttribute (ATTR_KEY, aItem.getKey ());
        eItem.setAttribute (ATTR_HREF, aItem.getTargetURLAsString ());
      }
    }
    return ret;
  }

  public void reload ()
  {
    m_aRWLock.writeLock ().lock ();
    try
    {
      m_aMap.clear ();
      initialRead ();
      s_aLogger.info ("Reloaded " + m_aMap.size () + " go-mappings!");
    }
    catch (final DAOException ex)
    {
      throw new IllegalStateException ("Failed to reload go-mappings", ex);
    }
    finally
    {
      m_aRWLock.writeLock ().unlock ();
    }
  }

  @Nonnull
  public EChange addItem (@Nonnull @Nonempty final String sKey,
                          final boolean bIsInternal,
                          @Nonnull @Nonempty final String sTargetURL,
                          final boolean bIsEditable)
  {
    return addItem (new GoMappingItem (sKey, bIsInternal, sTargetURL, bIsEditable));
  }

  @Nonnull
  public EChange addItem (@Nonnull final GoMappingItem aItem)
  {
    ValueEnforcer.notNull (aItem, "Item");

    final String sRealKey = _unifyKey (aItem.getKey ());

    m_aRWLock.writeLock ().lock ();
    try
    {
      if (m_aMap.containsKey (sRealKey))
        return EChange.UNCHANGED;

      _addItem (aItem, false);
      markAsChanged ();
    }
    finally
    {
      m_aRWLock.writeLock ().unlock ();
    }
    return EChange.CHANGED;
  }

  @Nonnull
  public EChange setItem (@Nonnull @Nonempty final String sKey,
                          final boolean bIsInternal,
                          @Nonnull @Nonempty final String sTargetURL,
                          final boolean bIsEditable)
  {
    return setItem (new GoMappingItem (sKey, bIsInternal, sTargetURL, bIsEditable));
  }

  @Nonnull
  public EChange setItem (@Nonnull final GoMappingItem aItem)
  {
    ValueEnforcer.notNull (aItem, "Item");

    m_aRWLock.writeLock ().lock ();
    try
    {
      // Allow overwrite
      if (_addItem (aItem, true).isUnchanged ())
        return EChange.UNCHANGED;
      markAsChanged ();
    }
    finally
    {
      m_aRWLock.writeLock ().unlock ();
    }
    return EChange.CHANGED;
  }

  @Nonnull
  public EChange removeItem (@Nullable final String sKey)
  {
    if (StringHelper.hasNoText (sKey))
      return EChange.UNCHANGED;

    final String sRealKey = _unifyKey (sKey);

    m_aRWLock.writeLock ().lock ();
    try
    {
      if (m_aMap.remove (sRealKey) == null)
        return EChange.UNCHANGED;
      markAsChanged ();
    }
    finally
    {
      m_aRWLock.writeLock ().unlock ();
    }
    return EChange.CHANGED;
  }

  @Nonnull
  public EChange removeAllItems ()
  {
    m_aRWLock.writeLock ().lock ();
    try
    {
      if (m_aMap.isEmpty ())
        return EChange.UNCHANGED;
      m_aMap.clear ();
      markAsChanged ();
    }
    finally
    {
      m_aRWLock.writeLock ().unlock ();
    }
    return EChange.CHANGED;
  }

  public boolean containsItemWithKey (@Nullable final String sKey)
  {
    if (StringHelper.hasNoText (sKey))
      return false;

    final String sRealKey = _unifyKey (sKey);

    m_aRWLock.readLock ().lock ();
    try
    {
      return m_aMap.containsKey (sRealKey);
    }
    finally
    {
      m_aRWLock.readLock ().unlock ();
    }
  }

  @Nullable
  public GoMappingItem getItemOfKey (@Nullable final String sKey)
  {
    if (StringHelper.hasNoText (sKey))
      return null;

    final String sRealKey = _unifyKey (sKey);

    m_aRWLock.readLock ().lock ();
    try
    {
      return m_aMap.get (sRealKey);
    }
    finally
    {
      m_aRWLock.readLock ().unlock ();
    }
  }

  @Nonnegative
  public int getItemCount ()
  {
    m_aRWLock.readLock ().lock ();
    try
    {
      return m_aMap.size ();
    }
    finally
    {
      m_aRWLock.readLock ().unlock ();
    }
  }

  @Nonnull
  @ReturnsMutableCopy
  public Map <String, GoMappingItem> getAllItems ()
  {
    m_aRWLock.readLock ().lock ();
    try
    {
      return CollectionHelper.newMap (m_aMap);
    }
    finally
    {
      m_aRWLock.readLock ().unlock ();
    }
  }

  /**
   * Check whether all internal go links, that point to a page use existing menu
   * item IDs
   *
   * @param aMenuTree
   *        The menu tree to search. May not be <code>null</code>.
   */
  public void checkInternalMappings (@Nonnull final IMenuTree aMenuTree)
  {
    ValueEnforcer.notNull (aMenuTree, "MenuTree");
    final IRequestManager aARM = ApplicationRequestManager.getRequestMgr ();

    m_aRWLock.readLock ().lock ();
    try
    {
      int nCount = 0;
      for (final GoMappingItem aItem : m_aMap.values ())
        if (aItem.isInternal ())
        {
          // Get value of "menu item" parameter and check for existence
          final String sParamValue = aARM.getMenuItemFromURL (aItem.getTargetURL ());
          if (sParamValue != null)
          {
            ++nCount;
            if (aMenuTree.getItemWithID (sParamValue) == null)
              throw new IllegalStateException ("Go-mapping item " + aItem + " is invalid");
          }
        }
      s_aLogger.info ("Successfully checked " + nCount + " internal go-mappings for consistency");
    }
    finally
    {
      m_aRWLock.readLock ().unlock ();
    }
  }

  @Override
  public String toString ()
  {
    return new ToStringGenerator (this).append ("map", m_aMap).toString ();
  }

  /**
   * This method is ONLY to be used within the MenuManager, since the renderer
   * takes care, that the link to the menu item is handled correctly even if no
   * session information are present.
   *
   * @param sKey
   *        Go mapping key. May neither be <code>null</code> nor empty.
   * @return <code>/webapp-context/go/<i>key</i></code>. Never <code>null</code>
   */
  @Nonnull
  public static SimpleURL getGoLinkForMenuItem (@Nonnull @Nonempty final String sKey)
  {
    ValueEnforcer.notEmpty (sKey, "Key");

    if (PhotonCoreManager.getGoMappingMgr ().getItemOfKey (sKey) == null)
      s_aLogger.warn ("Building URL from invalid go-mapping item '" + sKey + "'");

    return LinkHelper.getURLWithContext (GoServlet.SERVLET_DEFAULT_NAME + "/" + sKey);
  }

  /**
   * @param aRequestScope
   *        Current request scope. May not be <code>null</code>.
   * @param sKey
   *        Go mapping key. May neither be <code>null</code> nor empty.
   * @return <code>/webapp-context/go/<i>key</i></code>. Never <code>null</code>
   */
  @Nonnull
  public static SimpleURL getGoLink (@Nonnull final IRequestWebScopeWithoutResponse aRequestScope,
                                     @Nonnull @Nonempty final String sKey)
  {
    ValueEnforcer.notEmpty (sKey, "Key");

    if (PhotonCoreManager.getGoMappingMgr ().getItemOfKey (sKey) == null)
      s_aLogger.warn ("Building URL from invalid go-mapping item '" + sKey + "'");

    return LinkHelper.getURLWithContext (aRequestScope, GoServlet.SERVLET_DEFAULT_NAME + "/" + sKey);
  }
}
