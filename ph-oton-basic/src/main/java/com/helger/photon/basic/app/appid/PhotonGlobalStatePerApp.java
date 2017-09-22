package com.helger.photon.basic.app.appid;

import java.io.Serializable;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.concurrent.ThreadSafe;

import com.helger.commons.ValueEnforcer;
import com.helger.commons.annotation.Nonempty;
import com.helger.commons.concurrent.SimpleReadWriteLock;
import com.helger.commons.string.StringHelper;
import com.helger.commons.string.ToStringGenerator;
import com.helger.photon.basic.app.menu.IMenuTree;

/**
 * Single global state per app
 *
 * @author Philip Helger
 * @since 8.0.0
 */
@ThreadSafe
public final class PhotonGlobalStatePerApp implements Serializable
{
  private final SimpleReadWriteLock m_aRWLock = new SimpleReadWriteLock ();
  private String m_sServletPath;
  private IMenuTree m_aMenuTree;

  public PhotonGlobalStatePerApp ()
  {}

  @Nullable
  String internalGetServletPath ()
  {
    return m_aRWLock.readLocked ( () -> m_sServletPath);
  }

  @Nonnull
  @Nonempty
  public String getServletPath () throws IllegalStateException
  {
    final String ret = internalGetServletPath ();
    if (StringHelper.hasNoText (ret))
      throw new IllegalStateException ("No servlet path specified!");
    return ret;
  }

  public void setServletPath (@Nonnull @Nonempty final String sServletPath)
  {
    ValueEnforcer.notEmpty (sServletPath, "ApplicationServletPath");
    ValueEnforcer.isTrue (StringHelper.startsWith (sServletPath, '/'),
                          "ApplicationServletPath must start with a slash");
    ValueEnforcer.isFalse (StringHelper.endsWith (sServletPath, '/'), "ApplicationServletPath must end with a slash");
    m_aRWLock.writeLocked ( () -> m_sServletPath = sServletPath);
  }

  public void removeServletPath ()
  {
    m_aRWLock.writeLocked ( () -> m_sServletPath = null);
  }

  @Nullable
  public IMenuTree getMenuTree ()
  {
    return m_aRWLock.readLocked ( () -> m_aMenuTree);
  }

  public void setMenuTree (@Nullable final IMenuTree aMenuTree)
  {
    m_aRWLock.writeLocked ( () -> m_aMenuTree = aMenuTree);
  }

  @Override
  public String toString ()
  {
    return new ToStringGenerator (null).append ("ServletPath", m_sServletPath)
                                       .append ("MenuTree", m_aMenuTree)
                                       .getToString ();
  }
}
