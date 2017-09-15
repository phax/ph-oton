package com.helger.photon.basic.app.appid;

import java.util.Locale;
import java.util.function.BiConsumer;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.concurrent.GuardedBy;
import javax.annotation.concurrent.ThreadSafe;

import com.helger.commons.ValueEnforcer;
import com.helger.commons.annotation.Nonempty;
import com.helger.commons.collection.attr.AttributeContainerAny;
import com.helger.commons.concurrent.SimpleReadWriteLock;
import com.helger.commons.lang.ICloneable;
import com.helger.commons.string.ToStringGenerator;
import com.helger.photon.basic.app.menu.IMenuItemPage;
import com.helger.photon.basic.app.menu.IMenuTree;

/**
 * Single state
 *
 * @author Philip Helger
 */
@ThreadSafe
public final class PhotonState implements ICloneable <PhotonState>
{
  private final SimpleReadWriteLock m_aRWLock = new SimpleReadWriteLock ();
  private final String m_sAppID;
  @GuardedBy ("m_aRWLock")
  private final AttributeContainerAny <String> m_aAttrs = new AttributeContainerAny <> ();

  public PhotonState (@Nonnull @Nonempty final String sAppID)
  {
    ValueEnforcer.notEmpty (sAppID, "AppID");
    m_sAppID = sAppID;
  }

  public PhotonState (@Nonnull final PhotonState aOther)
  {
    m_sAppID = aOther.m_sAppID;
    m_aAttrs.putAll (aOther.m_aAttrs);
  }

  @Nonnull
  @Nonempty
  String getAppID ()
  {
    return m_sAppID;
  }

  @Nullable
  <T> T getAttr (@Nonnull @Nonempty final String sAttr)
  {
    m_aRWLock.readLock ().lock ();
    try
    {
      return m_aAttrs.getCastedValue (sAttr);
    }
    finally
    {
      m_aRWLock.readLock ().unlock ();
    }
  }

  void setAttr (@Nonnull @Nonempty final String sAttr, @Nonnull final Object aValue)
  {
    m_aRWLock.writeLock ().lock ();
    try
    {
      m_aAttrs.putIn (sAttr, aValue);
    }
    finally
    {
      m_aRWLock.writeLock ().unlock ();
    }
  }

  public boolean isEmpty ()
  {
    return m_aRWLock.readLocked ( () -> m_aAttrs.isEmpty ());
  }

  @Nullable
  public IMenuTree getMenuTree ()
  {
    return getAttr (IMenuTree.class.getName ());
  }

  public void setMenuTree (@Nonnull final IMenuTree aMenuTree)
  {
    setAttr (IMenuTree.class.getName (), aMenuTree);
  }

  @Nullable
  public IMenuItemPage getMenuItem ()
  {
    return getAttr (IMenuItemPage.class.getName ());
  }

  public void setMenuItem (@Nonnull final IMenuItemPage aMenuItem)
  {
    setAttr (IMenuItemPage.class.getName (), aMenuItem);
  }

  @Nullable
  public Locale getLocale ()
  {
    return getAttr (Locale.class.getName ());
  }

  public void setLocale (@Nonnull final Locale aLocale)
  {
    setAttr (Locale.class.getName (), aLocale);
  }

  @Nonnull
  public PhotonState getClone ()
  {
    return new PhotonState (this);
  }

  @Deprecated
  public void setCustom (@Nonnull @Nonempty final String sKey, @Nonnull final Object aValue)
  {
    setAttr (sKey, aValue);
  }

  @Nullable
  @Deprecated
  public <T> T getCustom (@Nonnull @Nonempty final String sKey)
  {
    return getAttr (sKey);
  }

  void forEach (@Nonnull final BiConsumer <? super String, ? super Object> aConsumer)
  {
    m_aAttrs.forEach (aConsumer);
  }

  @Override
  public String toString ()
  {
    return new ToStringGenerator (null).append ("AppID", m_sAppID).append ("Attrs", m_aAttrs).getToString ();
  }
}
