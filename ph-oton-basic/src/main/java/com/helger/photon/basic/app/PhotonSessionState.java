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
package com.helger.photon.basic.app;

import java.util.Locale;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.concurrent.ThreadSafe;

import com.helger.commons.ValueEnforcer;
import com.helger.commons.annotation.Nonempty;
import com.helger.commons.annotation.UsedViaReflection;
import com.helger.commons.collection.ext.CommonsHashMap;
import com.helger.commons.collection.ext.ICommonsMap;
import com.helger.commons.string.StringHelper;
import com.helger.commons.string.ToStringGenerator;
import com.helger.photon.basic.app.menu.IMenuItemPage;
import com.helger.web.scope.singleton.AbstractSessionWebSingleton;

/**
 * This class maintains ph-oton specific settings within a session.
 *
 * @author Philip Helger
 * @since 6.1.0
 */
@ThreadSafe
public final class PhotonSessionState extends AbstractSessionWebSingleton
{
  private String m_sLastApplicationID;
  private final ICommonsMap <String, IMenuItemPage> m_aSelectedMenuItems = new CommonsHashMap<> ();
  private final ICommonsMap <String, Locale> m_aSelectedLocales = new CommonsHashMap<> ();

  @Deprecated
  @UsedViaReflection
  public PhotonSessionState ()
  {}

  /**
   * @return The one and only instance for the current session. Never
   *         <code>null</code>.
   * @see #getInstanceIfInstantiated()
   */
  @Nonnull
  public static PhotonSessionState getInstance ()
  {
    return getSessionSingleton (PhotonSessionState.class);
  }

  /**
   * @return The one and only instance for the current session. It may be
   *         <code>null</code> if this singleton was not yet instantiated.
   * @see #getInstance()
   */
  @Nullable
  public static PhotonSessionState getInstanceIfInstantiated ()
  {
    return getSessionSingletonIfInstantiated (PhotonSessionState.class);
  }

  /**
   * @return The last application ID used for the application servlet. May be
   *         <code>null</code>.
   */
  @Nullable
  public String getLastApplicationID ()
  {
    return m_aRWLock.readLocked ( () -> m_sLastApplicationID);
  }

  /**
   * @return <code>true</code> if the last used application IDis present,
   *         <code>false</code> otherwise.
   */
  public boolean hasLastApplicationID ()
  {
    return StringHelper.hasText (getLastApplicationID ());
  }

  /**
   * Set the last used application ID of an application servlet.
   *
   * @param sLastApplicationID
   *        The last application ID to be set. May be <code>null</code>.
   */
  public void setLastApplicationID (@Nullable final String sLastApplicationID)
  {
    m_aRWLock.writeLocked ( () -> m_sLastApplicationID = sLastApplicationID);
  }

  @Nullable
  public IMenuItemPage getSelectedMenuItem (@Nullable final String sApplicationID)
  {
    if (StringHelper.hasNoText (sApplicationID))
      return null;
    return m_aRWLock.readLocked ( () -> m_aSelectedMenuItems.get (sApplicationID));
  }

  @Nullable
  public String getSelectedMenuItemID (@Nullable final String sApplicationID)
  {
    final IMenuItemPage aMenuItem = getSelectedMenuItem (sApplicationID);
    return aMenuItem == null ? null : aMenuItem.getID ();
  }

  @Nullable
  public static IMenuItemPage getSelectedMenuItemOfCurrentSession (@Nullable final String sApplicationID)
  {
    final PhotonSessionState aSessionState = getInstanceIfInstantiated ();
    return aSessionState == null ? null : aSessionState.getSelectedMenuItem (sApplicationID);
  }

  @Nullable
  public static String getSelectedMenuItemIDOfCurrentSession (@Nullable final String sApplicationID)
  {
    final PhotonSessionState aSessionState = getInstanceIfInstantiated ();
    return aSessionState == null ? null : aSessionState.getSelectedMenuItemID (sApplicationID);
  }

  public void setSelectedMenuItem (@Nonnull @Nonempty final String sApplicationID,
                                   @Nonnull final IMenuItemPage aMenuItem)
  {
    ValueEnforcer.notEmpty (sApplicationID, "ApplicationID");
    ValueEnforcer.notNull (aMenuItem, "MenuItemID");
    m_aRWLock.writeLocked ( () -> m_aSelectedMenuItems.put (sApplicationID, aMenuItem));
  }

  @Nullable
  public Locale getSelectedLocale (@Nullable final String sApplicationID)
  {
    if (StringHelper.hasNoText (sApplicationID))
      return null;
    return m_aRWLock.readLocked ( () -> m_aSelectedLocales.get (sApplicationID));
  }

  @Nullable
  public static Locale getSelectedLocaleOfCurrentSession (@Nullable final String sApplicationID)
  {
    final PhotonSessionState aSessionState = getInstanceIfInstantiated ();
    return aSessionState == null ? null : aSessionState.getSelectedLocale (sApplicationID);
  }

  public void setSelectedLocale (@Nonnull @Nonempty final String sApplicationID, @Nonnull final Locale aLocale)
  {
    ValueEnforcer.notEmpty (sApplicationID, "ApplicationID");
    ValueEnforcer.notNull (aLocale, "Locale");
    m_aRWLock.writeLocked ( () -> m_aSelectedLocales.put (sApplicationID, aLocale));
  }

  @Override
  public String toString ()
  {
    return ToStringGenerator.getDerived (super.toString ())
                            .append ("LastApplicationID", m_sLastApplicationID)
                            .append ("SelectedMenuItems", m_aSelectedMenuItems)
                            .append ("SelectedLocales", m_aSelectedLocales)
                            .toString ();
  }
}
