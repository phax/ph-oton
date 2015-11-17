package com.helger.photon.security.object;

import javax.annotation.Nonnull;
import javax.annotation.concurrent.Immutable;

import com.helger.commons.state.EChange;
import com.helger.commons.string.StringHelper;
import com.helger.datetime.PDTFactory;
import com.helger.photon.basic.auth.CBasicSecurity;
import com.helger.photon.basic.object.AbstractBaseObject;
import com.helger.photon.basic.object.IObject;
import com.helger.photon.security.login.LoggedInUserManager;

/**
 * Helper class to work with {@link IObject} implementations.
 * 
 * @author Philip Helger
 */
@Immutable
public final class ObjectHelper
{
  private ObjectHelper ()
  {}

  public static void setLastModificationNow (@Nonnull final AbstractBaseObject aObj)
  {
    String sCurrentUserID = LoggedInUserManager.getInstance ().getCurrentUserID ();
    if (StringHelper.hasNoText (sCurrentUserID))
    {
      // No user is logged in- use the internal guest user ID
      sCurrentUserID = CBasicSecurity.USER_ID_NONE_LOGGED_IN;
    }
    aObj.setLastModification (PDTFactory.getCurrentLocalDateTime (), sCurrentUserID);
  }

  @Nonnull
  public static EChange setDeletionNow (@Nonnull final AbstractBaseObject aObj)
  {
    return aObj.setDeletion (PDTFactory.getCurrentLocalDateTime (), LoggedInUserManager.getInstance ().getCurrentUserID ());
  }

  @Nonnull
  public static EChange setUndeletionNow (@Nonnull final AbstractBaseObject aObj)
  {
    return aObj.setUndeletion (PDTFactory.getCurrentLocalDateTime (), LoggedInUserManager.getInstance ().getCurrentUserID ());
  }
}
