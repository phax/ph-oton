/**
 * Copyright (C) 2014-2015 Philip Helger (www.helger.com)
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
package com.helger.appbasics.object;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import org.joda.time.DateTime;

import com.helger.appbasics.security.login.LoggedInUserManager;
import com.helger.commons.annotations.Nonempty;
import com.helger.commons.idfactory.GlobalIDFactory;
import com.helger.commons.type.ObjectType;
import com.helger.datetime.PDTFactory;

/**
 * The most simple {@link IObject} implementation. Used for serialization
 * encapsulation and the like.
 *
 * @author Philip Helger
 */
public final class StubObject extends AbstractObject
{
  public static final ObjectType OT_STUB = new ObjectType ("stub-object");

  private StubObject (@Nonnull @Nonempty final String sID, @Nullable final String sCreationUserID)
  {
    this (sID, PDTFactory.getCurrentDateTime (), sCreationUserID, null, null, null, null);
  }

  public StubObject (@Nonnull @Nonempty final String sID,
                     @Nullable final DateTime aCreationDT,
                     @Nullable final String sCreationUserID,
                     @Nullable final DateTime aLastModificationDT,
                     @Nullable final String sLastModificationUserID,
                     @Nullable final DateTime aDeletionDT,
                     @Nullable final String sDeletionUserID)
  {
    super (sID,
           aCreationDT,
           sCreationUserID,
           aLastModificationDT,
           sLastModificationUserID,
           aDeletionDT,
           sDeletionUserID);
  }

  @Nonnull
  public ObjectType getTypeID ()
  {
    return OT_STUB;
  }

  @Nonnull
  public static StubObject createForCurrentUser ()
  {
    return createForUser (LoggedInUserManager.getInstance ().getCurrentUserID ());
  }

  @Nonnull
  public static StubObject createForUser (@Nullable final String sUserID)
  {
    return new StubObject (GlobalIDFactory.getNewPersistentStringID (), sUserID);
  }

  @Nonnull
  public static StubObject createForCurrentUserAndID (@Nonnull @Nonempty final String sID)
  {
    return new StubObject (sID, LoggedInUserManager.getInstance ().getCurrentUserID ());
  }
}
