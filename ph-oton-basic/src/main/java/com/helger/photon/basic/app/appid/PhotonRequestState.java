package com.helger.photon.basic.app.appid;

import java.io.Serializable;
import java.util.Locale;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.concurrent.ThreadSafe;

import com.helger.commons.ValueEnforcer;
import com.helger.commons.string.ToStringGenerator;
import com.helger.photon.basic.app.locale.GlobalLocaleManager;
import com.helger.photon.basic.app.menu.IMenuItemPage;
import com.helger.photon.basic.app.menu.IMenuTree;

/**
 * Request state
 *
 * @author Philip Helger
 */
@ThreadSafe
public final class PhotonRequestState implements Serializable
{
  private final IMenuTree m_aMenuTree;
  private final IMenuItemPage m_aMenuItem;
  private final Locale m_aDisplayLocale;

  public PhotonRequestState (@Nonnull final PhotonSessionStatePerApp aState)
  {
    this (aState.getMenuTree (), aState.getMenuItem (), aState.getDisplayLocale ());
  }

  public PhotonRequestState (@Nonnull final PhotonGlobalStatePerApp aState)
  {
    this (aState.getMenuTree (),
          aState.getMenuTree ().getDefaultMenuItem (),
          GlobalLocaleManager.getInstance ().getDefaultLocale ());
  }

  public PhotonRequestState (@Nonnull final IMenuTree aMenuTree,
                             @Nonnull final IMenuItemPage aMenuItem,
                             @Nonnull final Locale aDisplayLocale)
  {
    m_aMenuTree = ValueEnforcer.notNull (aMenuTree, "MenuTree");
    m_aMenuItem = ValueEnforcer.notNull (aMenuItem, "MenuItem");
    m_aDisplayLocale = ValueEnforcer.notNull (aDisplayLocale, "DisplayLocale");
  }

  @Nullable
  public IMenuTree getMenuTree ()
  {
    return m_aMenuTree;
  }

  @Nullable
  public IMenuItemPage getMenuItem ()
  {
    return m_aMenuItem;
  }

  @Nullable
  public Locale getDisplayLocale ()
  {
    return m_aDisplayLocale;
  }

  @Override
  public String toString ()
  {
    return new ToStringGenerator (null).append ("MenuTree", m_aMenuTree)
                                       .append ("MenuItem", m_aMenuItem)
                                       .append ("DisplayLocale", m_aDisplayLocale)
                                       .getToString ();
  }
}
