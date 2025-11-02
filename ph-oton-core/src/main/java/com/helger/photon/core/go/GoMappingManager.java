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
package com.helger.photon.core.go;

import java.util.Comparator;
import java.util.Locale;
import java.util.function.Consumer;

import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.Nullable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.helger.annotation.Nonempty;
import com.helger.annotation.Nonnegative;
import com.helger.annotation.concurrent.ELockType;
import com.helger.annotation.concurrent.GuardedBy;
import com.helger.annotation.concurrent.MustBeLocked;
import com.helger.annotation.concurrent.ThreadSafe;
import com.helger.annotation.style.ReturnsMutableCopy;
import com.helger.base.enforce.ValueEnforcer;
import com.helger.base.state.EChange;
import com.helger.base.string.StringHelper;
import com.helger.base.string.StringParser;
import com.helger.base.tostring.ToStringGenerator;
import com.helger.collection.commons.CommonsHashMap;
import com.helger.collection.commons.ICommonsMap;
import com.helger.dao.DAOException;
import com.helger.photon.app.url.LinkHelper;
import com.helger.photon.core.menu.IMenuTree;
import com.helger.photon.core.mgr.PhotonCoreManager;
import com.helger.photon.core.requestparam.IRequestParameterManager;
import com.helger.photon.core.requestparam.RequestParameterManager;
import com.helger.photon.io.dao.AbstractPhotonSimpleDAO;
import com.helger.servlet.ServletContextPathHolder;
import com.helger.url.SimpleURL;
import com.helger.web.scope.IRequestWebScopeWithoutResponse;
import com.helger.xml.microdom.IMicroDocument;
import com.helger.xml.microdom.IMicroElement;
import com.helger.xml.microdom.MicroDocument;

/**
 * Manager for {@link GoMappingItem} objects.
 *
 * @author Philip Helger
 */
@ThreadSafe
public class GoMappingManager extends AbstractPhotonSimpleDAO
{
  public static final boolean DEFAULT_EDITABLE = true;

  private static final String ELEMENT_ROOT = "gomappings";
  private static final String ELEMENT_EXTERNAL = "external";
  private static final String ELEMENT_INTERNAL = "internal";
  private static final String ATTR_KEY = "key";
  private static final String ATTR_HREF = "href";
  private static final String ATTR_EDITABLE = "editable";
  private static final Logger LOGGER = LoggerFactory.getLogger (GoMappingManager.class);

  @GuardedBy ("m_aRWLock")
  private final ICommonsMap <String, GoMappingItem> m_aMap = new CommonsHashMap <> ();

  public GoMappingManager (@Nullable final String sFilename) throws DAOException
  {
    super (sFilename);
    initialRead ();
  }

  @NonNull
  private static String _unifyKey (@NonNull final String sKey)
  {
    return sKey.toLowerCase (Locale.US);
  }

  @MustBeLocked (ELockType.WRITE)
  private EChange _addItem (@NonNull final GoMappingItem aItem, final boolean bAllowOverwrite)
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

  public static void readFromXML (@NonNull final IMicroDocument aDoc, @NonNull final Consumer <GoMappingItem> aCallback)
  {
    ValueEnforcer.notNull (aDoc, "Doc");
    ValueEnforcer.notNull (aCallback, "Callback");

    aDoc.getDocumentElement ().forAllChildElements (eItem -> {
      final String sTagName = eItem.getTagName ();
      final String sKey = eItem.getAttributeValue (ATTR_KEY);
      final String sHref = eItem.getAttributeValue (ATTR_HREF);
      final String sIsEditable = eItem.getAttributeValue (ATTR_EDITABLE);
      final boolean bIsEditable = StringParser.parseBool (sIsEditable, DEFAULT_EDITABLE);

      if (ELEMENT_EXTERNAL.equals (sTagName))
        aCallback.accept (new GoMappingItem (sKey, false, sHref, bIsEditable));
      else
        if (ELEMENT_INTERNAL.equals (sTagName))
          aCallback.accept (new GoMappingItem (sKey, true, sHref, bIsEditable));
        else
          LOGGER.error ("Unsupported go-mapping tag '" + sTagName + "'");
    });
  }

  @Override
  protected EChange onRead (@NonNull final IMicroDocument aDoc)
  {
    readFromXML (aDoc, aCurrentObject -> _addItem (aCurrentObject, false));
    return EChange.UNCHANGED;
  }

  @Override
  protected IMicroDocument createWriteData ()
  {
    final String sContextPath = ServletContextPathHolder.getContextPath ();
    final IMicroDocument ret = new MicroDocument ();
    final IMicroElement eRoot = ret.addElement (ELEMENT_ROOT);
    for (final GoMappingItem aItem : m_aMap.getSortedByKey (Comparator.naturalOrder ()).values ())
    {
      if (aItem.isInternal ())
      {
        final IMicroElement eItem = eRoot.addElement (ELEMENT_INTERNAL);
        eItem.setAttribute (ATTR_KEY, aItem.getKey ());
        // Remove the context path, when deserializing stuff
        eItem.setAttribute (ATTR_HREF, StringHelper.trimStart (aItem.getTargetURLAsString (), sContextPath));
      }
      else
      {
        final IMicroElement eItem = eRoot.addElement (ELEMENT_EXTERNAL);
        eItem.setAttribute (ATTR_KEY, aItem.getKey ());
        eItem.setAttribute (ATTR_HREF, aItem.getTargetURLAsString ());
      }
    }
    return ret;
  }

  @Override
  public boolean isReloadable ()
  {
    return true;
  }

  @Override
  public void reload () throws DAOException
  {
    m_aRWLock.writeLockedThrowing ( () -> {
      m_aMap.clear ();
      initialRead ();
    });
    LOGGER.info ("Reloaded " + m_aMap.size () + " go-mappings!");
  }

  @NonNull
  public EChange addItem (@NonNull @Nonempty final String sKey,
                          final boolean bIsInternal,
                          @NonNull @Nonempty final String sTargetURL,
                          final boolean bIsEditable)
  {
    return addItem (new GoMappingItem (sKey, bIsInternal, sTargetURL, bIsEditable));
  }

  @NonNull
  public EChange addItem (@NonNull final GoMappingItem aItem)
  {
    ValueEnforcer.notNull (aItem, "Item");

    final String sRealKey = _unifyKey (aItem.getKey ());

    return m_aRWLock.writeLockedGet ( () -> {
      if (m_aMap.containsKey (sRealKey))
        return EChange.UNCHANGED;

      _addItem (aItem, false);
      markAsChanged ();
      return EChange.CHANGED;
    });
  }

  @NonNull
  public EChange setItem (@NonNull @Nonempty final String sKey,
                          final boolean bIsInternal,
                          @NonNull @Nonempty final String sTargetURL,
                          final boolean bIsEditable)
  {
    return setItem (new GoMappingItem (sKey, bIsInternal, sTargetURL, bIsEditable));
  }

  @NonNull
  public EChange setItem (@NonNull final GoMappingItem aItem)
  {
    ValueEnforcer.notNull (aItem, "Item");

    return m_aRWLock.writeLockedGet ( () -> {
      // Allow overwrite
      if (_addItem (aItem, true).isUnchanged ())
        return EChange.UNCHANGED;
      markAsChanged ();
      return EChange.CHANGED;
    });
  }

  @NonNull
  public EChange removeItem (@Nullable final String sKey)
  {
    if (StringHelper.isEmpty (sKey))
      return EChange.UNCHANGED;

    final String sRealKey = _unifyKey (sKey);

    return m_aRWLock.writeLockedGet ( () -> {
      if (m_aMap.remove (sRealKey) == null)
        return EChange.UNCHANGED;
      markAsChanged ();
      return EChange.CHANGED;
    });
  }

  @NonNull
  public EChange removeAllItems ()
  {
    return m_aRWLock.writeLockedGet ( () -> {
      if (m_aMap.removeAll ().isUnchanged ())
        return EChange.UNCHANGED;
      markAsChanged ();
      return EChange.CHANGED;
    });
  }

  public boolean containsItemWithKey (@Nullable final String sKey)
  {
    if (StringHelper.isEmpty (sKey))
      return false;

    final String sRealKey = _unifyKey (sKey);
    return m_aRWLock.readLockedBoolean ( () -> m_aMap.containsKey (sRealKey));
  }

  @Nullable
  public GoMappingItem getItemOfKey (@Nullable final String sKey)
  {
    if (StringHelper.isEmpty (sKey))
      return null;

    final String sRealKey = _unifyKey (sKey);
    return m_aRWLock.readLockedGet ( () -> m_aMap.get (sRealKey));
  }

  @Nonnegative
  public int getItemCount ()
  {
    return m_aRWLock.readLockedInt (m_aMap::size);
  }

  @NonNull
  @ReturnsMutableCopy
  public ICommonsMap <String, GoMappingItem> getAllItems ()
  {
    return m_aRWLock.readLockedGet (m_aMap::getClone);
  }

  /**
   * Check whether all internal go links, that point to a page use existing menu item IDs
   *
   * @param aMenuTree
   *        The menu tree to search. May not be <code>null</code>.
   * @param aErrorCallback
   *        The callback that is invoked for all invalid {@link GoMappingItem} objects.
   * @return The number of errors occurred. Always &ge; 0.
   */
  @Nonnegative
  public int checkInternalMappings (@NonNull final IMenuTree aMenuTree,
                                    @NonNull final Consumer <GoMappingItem> aErrorCallback)
  {
    ValueEnforcer.notNull (aMenuTree, "MenuTree");
    ValueEnforcer.notNull (aErrorCallback, "ErrorCallback");
    final IRequestParameterManager aRPM = RequestParameterManager.getInstance ();

    int nCount = 0;
    int nErrors = 0;
    m_aRWLock.readLock ().lock ();
    try
    {
      for (final GoMappingItem aItem : m_aMap.values ())
        if (aItem.isInternal ())
        {
          // Get value of "menu item" parameter and check for existence
          final String sParamValue = aRPM.getMenuItemFromURL (aItem.getTargetURLReadonly (), aMenuTree);
          if (sParamValue != null)
          {
            ++nCount;
            if (aMenuTree.getItemWithID (sParamValue) == null)
            {
              ++nErrors;
              aErrorCallback.accept (aItem);
            }
          }
        }
    }
    finally
    {
      m_aRWLock.readLock ().unlock ();
    }
    if (nErrors == 0)
      LOGGER.info ("Successfully checked " + nCount + " internal go-mappings for consistency");
    else
      LOGGER.warn ("Checked " + nCount + " internal go-mappings for consistency and found " + nErrors + " errors!");
    return nErrors;
  }

  @Override
  public String toString ()
  {
    return new ToStringGenerator (this).append ("map", m_aMap).getToString ();
  }

  /**
   * @param aRequestScope
   *        Current request scope. May not be <code>null</code>.
   * @param sKey
   *        Go mapping key. May neither be <code>null</code> nor empty.
   * @return <code>/webapp-context/go/<i>key</i></code>. Never <code>null</code>
   */
  @NonNull
  public static SimpleURL getGoLink (@NonNull final IRequestWebScopeWithoutResponse aRequestScope,
                                     @NonNull @Nonempty final String sKey)
  {
    ValueEnforcer.notEmpty (sKey, "Key");

    if (PhotonCoreManager.getGoMappingMgr ().getItemOfKey (sKey) == null)
      LOGGER.warn ("Building URL from non-existing go-mapping item '" + sKey + "'");

    return LinkHelper.getURLWithContext (aRequestScope, GoServlet.SERVLET_DEFAULT_NAME + "/" + sKey);
  }

  /**
   * @param sKey
   *        Go mapping key. May neither be <code>null</code> nor empty.
   * @return <code>/webapp-context/go/<i>key</i></code>. Never <code>null</code>
   */
  @NonNull
  public static String getGoLinkURI (@NonNull @Nonempty final String sKey)
  {
    ValueEnforcer.notEmpty (sKey, "Key");

    if (PhotonCoreManager.getGoMappingMgr ().getItemOfKey (sKey) == null)
      LOGGER.warn ("Building URI from non-existing go-mapping item '" + sKey + "'");

    return LinkHelper.getURIWithContext (GoServlet.SERVLET_DEFAULT_NAME + "/" + sKey);
  }
}
