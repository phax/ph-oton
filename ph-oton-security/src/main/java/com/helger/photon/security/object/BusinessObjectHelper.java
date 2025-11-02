/*
 * Copyright (C) 2014-2025 Philip Helger (www.helger.com)
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

import org.jspecify.annotations.NonNull;

import com.helger.annotation.Nonempty;
import com.helger.annotation.concurrent.Immutable;
import com.helger.base.state.EChange;
import com.helger.base.string.StringHelper;
import com.helger.datetime.helper.PDTFactory;
import com.helger.photon.security.login.LoggedInUserManager;
import com.helger.security.authentication.subject.user.CUserID;
import com.helger.tenancy.AbstractBusinessObject;
import com.helger.tenancy.IBusinessObject;

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

  @NonNull
  @Nonempty
  public static String getUserIDOrFallback ()
  {
    String sCurrentUserID = LoggedInUserManager.getInstance ().getCurrentUserID ();
    if (StringHelper.isEmpty (sCurrentUserID))
    {
      // No user is logged in- use the internal guest user ID
      sCurrentUserID = CUserID.USER_ID_GUEST;
    }
    return sCurrentUserID;
  }

  public static void setLastModificationNow (@NonNull final AbstractBusinessObject aObj)
  {
    aObj.setLastModification (PDTFactory.getCurrentLocalDateTime (), getUserIDOrFallback ());
  }

  @NonNull
  public static EChange setDeletionNow (@NonNull final AbstractBusinessObject aObj)
  {
    return aObj.setDeletion (PDTFactory.getCurrentLocalDateTime (), getUserIDOrFallback ());
  }

  @NonNull
  public static EChange setUndeletionNow (@NonNull final AbstractBusinessObject aObj)
  {
    return aObj.setUndeletion (PDTFactory.getCurrentLocalDateTime (), getUserIDOrFallback ());
  }
}
