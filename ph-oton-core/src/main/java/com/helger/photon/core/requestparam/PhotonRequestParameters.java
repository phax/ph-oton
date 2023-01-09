/*
 * Copyright (C) 2014-2023 Philip Helger (www.helger.com)
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
package com.helger.photon.core.requestparam;

import java.io.Serializable;
import java.util.Locale;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.concurrent.NotThreadSafe;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.helger.commons.locale.LocaleCache;
import com.helger.commons.string.StringHelper;
import com.helger.commons.string.ToStringGenerator;
import com.helger.photon.core.locale.ILocaleManager;
import com.helger.photon.core.menu.IMenuItemPage;
import com.helger.photon.core.menu.IMenuItemRedirectToPage;
import com.helger.photon.core.menu.IMenuObject;
import com.helger.photon.core.menu.IMenuOperations;

/**
 * Holder for the special ph-oton parameters
 *
 * @author Philip Helger
 * @since 7.0.2
 */
@NotThreadSafe
public class PhotonRequestParameters implements Serializable
{
  private static final Logger LOGGER = LoggerFactory.getLogger (PhotonRequestParameters.class);

  private Locale m_aLocale;
  private IMenuItemPage m_aMenuItem;

  public PhotonRequestParameters ()
  {}

  /**
   * @return The requested Locale. May be <code>null</code>.
   */
  @Nullable
  public Locale getLocale ()
  {
    return m_aLocale;
  }

  /**
   * @return The requested Locale as string. May be <code>null</code>.
   */
  @Nullable
  public String getLocaleAsString ()
  {
    return m_aLocale == null ? null : m_aLocale.toString ();
  }

  /**
   * @return <code>true</code> if a Locale is present
   */
  public boolean hasLocale ()
  {
    return m_aLocale != null;
  }

  /**
   * Set the current locale
   *
   * @param aLocale
   *        The Locale to use. May be <code>null</code>.
   */
  public void setLocale (@Nullable final Locale aLocale)
  {
    m_aLocale = aLocale;
  }

  @Nullable
  public Locale setLocaleFromString (@Nonnull final ILocaleManager aLocaleMgr, @Nullable final String sDisplayLocale)
  {
    Locale ret = null;
    if (StringHelper.hasText (sDisplayLocale))
    {
      // Is the locale globally valid?
      final Locale aDisplayLocale = LocaleCache.getInstance ().getLocale (sDisplayLocale);
      if (aDisplayLocale != null)
      {
        // Check if the locale is present
        if (aLocaleMgr.isSupportedLocale (aDisplayLocale))
          ret = aDisplayLocale;
      }
    }
    setLocale (ret);
    return ret;
  }

  @Nullable
  public IMenuItemPage getMenuItem ()
  {
    return m_aMenuItem;
  }

  @Nullable
  public String getMenuItemAsString ()
  {
    return m_aMenuItem == null ? null : m_aMenuItem.getID ();
  }

  public boolean hasMenuItem ()
  {
    return m_aMenuItem != null;
  }

  @Nullable
  public IMenuItemPage setMenuItemFromString (@Nonnull final IMenuOperations aTree, @Nullable final String sMenuItemID)
  {
    IMenuItemPage ret = null;
    if (StringHelper.hasText (sMenuItemID))
    {
      // Check if the menu item exists in the current application menu tree
      final IMenuObject aMenuObject = aTree.getMenuObjectOfID (sMenuItemID);
      if (aMenuObject != null)
      {
        // Only internal menu items pointing to a page are valid
        if (aMenuObject instanceof IMenuItemPage)
        {
          // Only menu items that can be displayed are valid
          if (aMenuObject.matchesDisplayFilter ())
            ret = (IMenuItemPage) aMenuObject;
        }
        else
          if (aMenuObject instanceof IMenuItemRedirectToPage)
          {
            // It's a redirect
            if (aMenuObject.matchesDisplayFilter ())
            {
              final IMenuItemPage aTarget = ((IMenuItemRedirectToPage) aMenuObject).getTargetMenuItemPage ();
              if (aTarget.matchesDisplayFilter ())
              {
                ret = aTarget;
                LOGGER.info ("Following a redirect from '" + aMenuObject.getID () + "' to '" + aTarget.getID () + "'");
              }
            }
          }
      }
    }
    m_aMenuItem = ret;
    return ret;
  }

  @Override
  public String toString ()
  {
    return new ToStringGenerator (null).append ("Locale", m_aLocale).append ("MenuItem", m_aMenuItem).getToString ();
  }
}
