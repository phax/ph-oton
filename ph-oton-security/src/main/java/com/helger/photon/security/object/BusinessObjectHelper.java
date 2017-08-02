/**
 * Copyright (C) 2014-2017 Philip Helger (www.helger.com)
 * philip[at]helger[dot]com
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.helger.photon.security.object;

import javax.annotation.Nonnull;
import javax.annotation.concurrent.Immutable;

import com.helger.commons.datetime.PDTFactory;
import com.helger.commons.state.EChange;
import com.helger.commons.string.StringHelper;
import com.helger.photon.basic.auth.CBasicSecurity;
import com.helger.photon.basic.object.AbstractBusinessObject;
import com.helger.photon.basic.object.IBusinessObject;
import com.helger.photon.security.login.LoggedInUserManager;

/**
 * Helper class to work with {@link IBusinessObject} implementations.
 *
 * @author Philip Helger
 */
@Immutable
public final class BusinessObjectHelper
{
  private BusinessObjectHelper ()
  {}

  public static void setLastModificationNow (@Nonnull final AbstractBusinessObject aObj)
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
  public static EChange setDeletionNow (@Nonnull final AbstractBusinessObject aObj)
  {
    return aObj.setDeletion (PDTFactory.getCurrentLocalDateTime (),
                             LoggedInUserManager.getInstance ().getCurrentUserID ());
  }

  @Nonnull
  public static EChange setUndeletionNow (@Nonnull final AbstractBusinessObject aObj)
  {
    return aObj.setUndeletion (PDTFactory.getCurrentLocalDateTime (),
                               LoggedInUserManager.getInstance ().getCurrentUserID ());
  }
}
