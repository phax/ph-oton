package com.helger.photon.basic.app.appid;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.helger.commons.ValueEnforcer;
import com.helger.commons.annotation.Nonempty;
import com.helger.commons.annotation.ReturnsMutableCopy;
import com.helger.commons.annotation.UsedViaReflection;
import com.helger.commons.collection.impl.CommonsHashMap;
import com.helger.commons.collection.impl.ICommonsMap;
import com.helger.commons.equals.EqualsHelper;
import com.helger.web.scope.singleton.AbstractGlobalWebSingleton;

/**
 * This class contains all the objects that are hold "per application".
 *
 * @author Philip Helger
 * @since 8.0.0
 */
public final class PhotonGlobalState extends AbstractGlobalWebSingleton
{
  private static final Logger s_aLogger = LoggerFactory.getLogger (PhotonGlobalState.class);
  private String m_sDefaultApplicationID;
  private final ICommonsMap <String, PhotonGlobalStatePerApp> m_aStateMap = new CommonsHashMap <> ();

  @Deprecated
  @UsedViaReflection
  public PhotonGlobalState ()
  {}

  @Nonnull
  public static PhotonGlobalState getInstance ()
  {
    return getGlobalSingleton (PhotonGlobalState.class);
  }

  /**
   * @return The default application ID. May be <code>null</code>.
   */
  @Nullable
  public String getDefaultApplicationID ()
  {
    return m_aRWLock.readLocked ( () -> m_sDefaultApplicationID);
  }

  /**
   * Set the default application ID of an application servlet.
   *
   * @param sDefaultApplicationID
   *        The last application ID to be set. May be <code>null</code>.
   */
  public void setDefaultApplicationID (@Nullable final String sDefaultApplicationID)
  {
    m_aRWLock.writeLocked ( () -> {
      if (!EqualsHelper.equals (m_sDefaultApplicationID, sDefaultApplicationID))
      {
        m_sDefaultApplicationID = sDefaultApplicationID;
        s_aLogger.info ("Default application ID set to '" + sDefaultApplicationID + "'");
      }
    });
  }

  @Nonnull
  public static PhotonGlobalStatePerApp state (@Nonnull @Nonempty final String sAppID)
  {
    ValueEnforcer.notEmpty (sAppID, "AppID");
    final PhotonGlobalState aGlobalState = getInstance ();
    return aGlobalState.m_aStateMap.computeIfAbsent (sAppID, k -> {
      // By default set first app ID as default
      if (aGlobalState.m_aStateMap.isEmpty ())
        aGlobalState.setDefaultApplicationID (sAppID);
      return new PhotonGlobalStatePerApp ();
    });
  }

  public static void removeAllApplicationServletPathMappings ()
  {
    getInstance ().m_aStateMap.forEachValue (PhotonGlobalStatePerApp::removeServletPath);
    getInstance ().setDefaultApplicationID (null);
  }

  public static boolean containsAnyApplicationServletPathMapping ()
  {
    return getInstance ().m_aStateMap.containsAnyValue (x -> x.internalGetServletPath () != null);
  }

  @Nonnull
  @ReturnsMutableCopy
  public static ICommonsMap <String, String> getAppIDToServletPathMap ()
  {
    final ICommonsMap <String, String> ret = new CommonsHashMap <> ();
    getInstance ().m_aStateMap.forEach ( (sAppID, aState) -> ret.put (sAppID, aState.getServletPath ()));
    return ret;
  }

}
