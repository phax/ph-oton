package com.helger.photon.basic.app.appid;

import javax.annotation.Nonnull;

import com.helger.commons.ValueEnforcer;
import com.helger.commons.annotation.Nonempty;
import com.helger.commons.annotation.ReturnsMutableCopy;
import com.helger.commons.annotation.UsedViaReflection;
import com.helger.commons.collection.impl.CommonsHashMap;
import com.helger.commons.collection.impl.ICommonsMap;
import com.helger.commons.string.StringHelper;
import com.helger.web.scope.singleton.AbstractGlobalWebSingleton;

/**
 * This class contains all the objects that are hold "per application".
 *
 * @author Philip Helger
 */
public final class PhotonGlobalState extends AbstractGlobalWebSingleton
{
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

  @Nonnull
  public static PhotonGlobalStatePerApp state (@Nonnull @Nonempty final String sAppID)
  {
    ValueEnforcer.notEmpty (sAppID, "AppID");
    return getInstance ().m_aStateMap.computeIfAbsent (sAppID, k -> new PhotonGlobalStatePerApp ());
  }

  public static void removeAllApplicationServletPathMappings ()
  {
    getInstance ().m_aStateMap.forEachValue (PhotonGlobalStatePerApp::removeServletPath);
  }

  public static boolean containsAnyApplicationServletPathMapping ()
  {
    return getInstance ().m_aStateMap.containsAnyValue (x -> x.getServletPath () != null);
  }

  @Nonnull
  public static String getApplicationServletPath (@Nonnull @Nonempty final String sAppID)
  {
    final String ret = state (sAppID).getServletPath ();
    if (StringHelper.hasNoText (ret))
      throw new IllegalStateException ("No servlet path specified!");
    return ret;
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
