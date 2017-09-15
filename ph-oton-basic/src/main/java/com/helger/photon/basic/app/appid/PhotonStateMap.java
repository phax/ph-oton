package com.helger.photon.basic.app.appid;

import java.util.function.BiConsumer;

import javax.annotation.Nonnull;
import javax.annotation.concurrent.ThreadSafe;

import com.helger.commons.ValueEnforcer;
import com.helger.commons.annotation.Nonempty;
import com.helger.commons.collection.impl.CommonsHashMap;
import com.helger.commons.collection.impl.ICommonsMap;
import com.helger.commons.concurrent.SimpleReadWriteLock;
import com.helger.commons.string.ToStringGenerator;

/**
 * Generic state to be used in different scopes
 *
 * @author Philip Helger
 */
@ThreadSafe
public final class PhotonStateMap
{
  private final SimpleReadWriteLock m_aRWLock = new SimpleReadWriteLock ();
  private final ICommonsMap <String, PhotonState> m_aStates = new CommonsHashMap <> ();

  public PhotonStateMap ()
  {}

  @Nonnull
  public PhotonState get (@Nonnull @Nonempty final String sAppID)
  {
    ValueEnforcer.notEmpty (sAppID, "AppID");
    m_aRWLock.writeLock ().lock ();
    try
    {
      return m_aStates.computeIfAbsent (sAppID, k -> new PhotonState (k));
    }
    finally
    {
      m_aRWLock.writeLock ().unlock ();
    }
  }

  void forEach (@Nonnull final BiConsumer <? super String, ? super PhotonState> aConsumer)
  {
    m_aRWLock.readLock ().lock ();
    try
    {
      m_aStates.forEach (aConsumer);
    }
    finally
    {
      m_aRWLock.readLock ().unlock ();
    }
  }

  @Override
  public String toString ()
  {
    return new ToStringGenerator (null).append ("PerAppStates", m_aStates).getToString ();
  }
}
