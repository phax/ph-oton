package com.helger.photon.basic.app.appid;

import javax.annotation.Nonnull;

import com.helger.commons.ValueEnforcer;
import com.helger.commons.annotation.Nonempty;
import com.helger.commons.annotation.UsedViaReflection;
import com.helger.commons.string.StringHelper;
import com.helger.web.scope.singleton.AbstractGlobalWebSingleton;

/**
 * This class contains all the objects that are hold "per application".
 *
 * @author Philip Helger
 */
public final class PhotonGlobalState extends AbstractGlobalWebSingleton
{
  private static final String ATTR_SERVLET_PATH = "servlet-path";

  private final PhotonStateMap m_aState = new PhotonStateMap ();

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
  public PhotonState state (@Nonnull @Nonempty final String sAppID)
  {
    return m_aState.get (sAppID);
  }

  /**
   * Set an application servlet path mapping. This overwrites existing mappings.
   *
   * @param sAppID
   *        Application ID to use. May neither be <code>null</code> nor empty.
   * @param sApplicationServletPath
   *        The path to use. May neither be <code>null</code> nor empty. Must
   *        start with a "slash" but may not end with a slash. Valid example is
   *        e.g. <code>/public</code>
   */
  public static void setApplicationServletPathMapping (@Nonnull @Nonempty final String sAppID,
                                                       @Nonnull @Nonempty final String sApplicationServletPath)
  {
    ValueEnforcer.notEmpty (sAppID, "ApplicationID");
    ValueEnforcer.notEmpty (sApplicationServletPath, "ApplicationServletPath");
    ValueEnforcer.isTrue (StringHelper.startsWith (sApplicationServletPath, '/'),
                          "ApplicationServletPath must be empty or start with a slash");
    ValueEnforcer.isFalse (StringHelper.endsWith (sApplicationServletPath, '/'),
                           "ApplicationServletPath must not end with a slash");

    getInstance ().state (sAppID).setAttr (ATTR_SERVLET_PATH, sApplicationServletPath);
  }

  @Nonnull
  public static String getApplicationServletPath (@Nonnull @Nonempty final String sAppID)
  {
    final String ret = getInstance ().state (sAppID).getAttr (ATTR_SERVLET_PATH);
    if (StringHelper.hasNoText (ret))
      throw new IllegalStateException ("No servlet path specified!");
    return ret;
  }
}
