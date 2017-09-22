package com.helger.photon.basic.app.appid;

import java.io.Serializable;
import java.util.Locale;

import javax.annotation.Nullable;
import javax.annotation.concurrent.ThreadSafe;

import com.helger.commons.concurrent.SimpleReadWriteLock;
import com.helger.commons.string.ToStringGenerator;
import com.helger.photon.basic.app.menu.IMenuItemPage;
import com.helger.photon.basic.app.menu.IMenuTree;

/**
 * Single session state per app
 *
 * @author Philip Helger
 * @since 8.0.0
 */
@ThreadSafe
public final class PhotonSessionStatePerApp implements Serializable
{
  private final SimpleReadWriteLock m_aRWLock = new SimpleReadWriteLock ();
  private IMenuTree m_aMenuTree;
  private IMenuItemPage m_aMenuItem;
  private Locale m_aDisplayLocale;

  public PhotonSessionStatePerApp ()
  {}

  @Nullable
  public IMenuTree getMenuTree ()
  {
    return m_aRWLock.readLocked ( () -> m_aMenuTree);
  }

  public void setMenuTree (@Nullable final IMenuTree aMenuTree)
  {
    m_aRWLock.writeLocked ( () -> m_aMenuTree = aMenuTree);
  }

  @Nullable
  public IMenuItemPage getMenuItem ()
  {
    return m_aRWLock.readLocked ( () -> m_aMenuItem);
  }

  public void setMenuItem (@Nullable final IMenuItemPage aMenuItem)
  {
    m_aRWLock.writeLocked ( () -> m_aMenuItem = aMenuItem);
  }

  @Nullable
  public Locale getDisplayLocale ()
  {
    return m_aRWLock.readLocked ( () -> m_aDisplayLocale);
  }

  public void setLocale (@Nullable final Locale aDisplayLocale)
  {
    m_aRWLock.writeLocked ( () -> m_aDisplayLocale = aDisplayLocale);
  }

  public boolean isEmpty ()
  {
    return m_aRWLock.readLocked ( () -> m_aMenuTree == null && m_aMenuItem == null && m_aDisplayLocale == null);
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
