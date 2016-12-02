package com.helger.photon.basic.app.request;

import java.io.Serializable;
import java.util.Locale;

import javax.annotation.Nullable;
import javax.annotation.concurrent.NotThreadSafe;

import com.helger.commons.locale.LocaleCache;
import com.helger.commons.string.StringHelper;
import com.helger.commons.string.ToStringGenerator;
import com.helger.photon.basic.app.locale.ApplicationLocaleManager;
import com.helger.photon.basic.app.menu.ApplicationMenuTree;
import com.helger.photon.basic.app.menu.IMenuItemPage;
import com.helger.photon.basic.app.menu.IMenuObject;

/**
 * Holder for the special ph-oton request parameters
 *
 * @author Philip Helger
 */
@NotThreadSafe
public class PhotonRequestParameters implements Serializable
{
  private Locale m_aLocale;
  private IMenuItemPage m_aMenuItem;

  public PhotonRequestParameters ()
  {}

  @Nullable
  public Locale getLocale ()
  {
    return m_aLocale;
  }

  @Nullable
  public String getLocaleAsString ()
  {
    return m_aLocale == null ? null : m_aLocale.toString ();
  }

  public boolean hasLocale ()
  {
    return m_aLocale != null;
  }

  public void setLocale (@Nullable final Locale aLocale)
  {
    m_aLocale = aLocale;
  }

  @Nullable
  public Locale setLocaleFromString (@Nullable final String sDisplayLocale)
  {
    Locale ret = null;
    if (StringHelper.hasText (sDisplayLocale))
    {
      final Locale aDisplayLocale = LocaleCache.getInstance ().getLocale (sDisplayLocale);
      if (aDisplayLocale != null)
      {
        // Check if the locale is present in the locale manager
        if (ApplicationLocaleManager.getLocaleMgr ().isSupportedLocale (aDisplayLocale))
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

  public void setMenuItem (@Nullable final IMenuItemPage aMenuItem)
  {
    m_aMenuItem = aMenuItem;
  }

  @Nullable
  public IMenuItemPage setMenuItemFromString (@Nullable final String sMenuItemID)
  {
    IMenuItemPage ret = null;
    if (StringHelper.hasText (sMenuItemID))
    {
      // Validate the menu item ID and check the display filter!
      final IMenuObject aMenuObject = ApplicationMenuTree.getTree ().getMenuObjectOfID (sMenuItemID);
      if (aMenuObject != null)
      {
        if (aMenuObject instanceof IMenuItemPage && aMenuObject.matchesDisplayFilter ())
          ret = (IMenuItemPage) aMenuObject;
      }
    }
    setMenuItem (ret);
    return ret;
  }

  @Override
  public String toString ()
  {
    return new ToStringGenerator (null).append ("Locale", m_aLocale).append ("MenuItem", m_aMenuItem).toString ();
  }
}
