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

import java.time.LocalDateTime;
import java.util.Map;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.helger.commons.annotation.Nonempty;
import com.helger.commons.datetime.PDTFactory;
import com.helger.commons.id.factory.GlobalIDFactory;
import com.helger.commons.type.ObjectType;
import com.helger.photon.security.login.LoggedInUserManager;
import com.helger.tenancy.AbstractBusinessObject;
import com.helger.tenancy.IBusinessObject;

/**
 * The most simple {@link IBusinessObject} implementation. Used for
 * serialization encapsulation and the like.
 *
 * @author Philip Helger
 */
public final class StubObject extends AbstractBusinessObject
{
  public static final ObjectType OT_STUB = new ObjectType ("stub-object");

  public StubObject (@Nonnull @Nonempty final String sID,
                     @Nullable final String sCreationUserID,
                     @Nullable final Map <String, String> aCustomAttrs)
  {
    this (sID,
          PDTFactory.getCurrentLocalDateTime (),
          sCreationUserID,
          (LocalDateTime) null,
          (String) null,
          (LocalDateTime) null,
          (String) null,
          aCustomAttrs);
  }

  public StubObject (@Nonnull @Nonempty final String sID,
                     @Nullable final LocalDateTime aCreationDT,
                     @Nullable final String sCreationUserID,
                     @Nullable final LocalDateTime aLastModificationDT,
                     @Nullable final String sLastModificationUserID,
                     @Nullable final LocalDateTime aDeletionDT,
                     @Nullable final String sDeletionUserID,
                     @Nullable final Map <String, String> aCustomAttrs)
  {
    super (sID, aCreationDT, sCreationUserID, aLastModificationDT, sLastModificationUserID, aDeletionDT, sDeletionUserID, aCustomAttrs);
  }

  @Nonnull
  public ObjectType getObjectType ()
  {
    return OT_STUB;
  }

  /**
   * Create a {@link StubObject} for the current user creating a new object ID
   *
   * @return Never <code>null</code>.
   */
  @Nonnull
  public static StubObject createForCurrentUser ()
  {
    return createForUser (LoggedInUserManager.getInstance ().getCurrentUserID ());
  }

  /**
   * Create a {@link StubObject} for the current user creating a new object ID
   *
   * @param aCustomAttrs
   *        Custom attributes. May be <code>null</code>.
   * @return Never <code>null</code>.
   */
  @Nonnull
  public static StubObject createForCurrentUser (@Nullable final Map <String, String> aCustomAttrs)
  {
    return createForUser (LoggedInUserManager.getInstance ().getCurrentUserID (), aCustomAttrs);
  }

  /**
   * Create a {@link StubObject} using the provided user ID and a new object ID
   *
   * @param sUserID
   *        User ID
   * @return Never <code>null</code>.
   */
  @Nonnull
  public static StubObject createForUser (@Nullable final String sUserID)
  {
    return new StubObject (GlobalIDFactory.getNewPersistentStringID (), sUserID, null);
  }

  /**
   * Create a {@link StubObject} using the provided user ID and a new object ID
   *
   * @param sUserID
   *        User ID
   * @param aCustomAttrs
   *        Custom attributes. May be <code>null</code>.
   * @return Never <code>null</code>.
   */
  @Nonnull
  public static StubObject createForUser (@Nullable final String sUserID, @Nullable final Map <String, String> aCustomAttrs)
  {
    return new StubObject (GlobalIDFactory.getNewPersistentStringID (), sUserID, aCustomAttrs);
  }

  /**
   * Create a {@link StubObject} using the current user ID and the provided
   * object ID
   *
   * @param sID
   *        Object ID
   * @return Never <code>null</code>.
   */
  @Nonnull
  public static StubObject createForCurrentUserAndID (@Nonnull @Nonempty final String sID)
  {
    return new StubObject (sID, LoggedInUserManager.getInstance ().getCurrentUserID (), null);
  }

  /**
   * Create a {@link StubObject} using the current user ID and the provided
   * object ID
   *
   * @param sID
   *        Object ID
   * @param aCustomAttrs
   *        Custom attributes. May be <code>null</code>.
   * @return Never <code>null</code>.
   */
  @Nonnull
  public static StubObject createForCurrentUserAndID (@Nonnull @Nonempty final String sID,
                                                      @Nullable final Map <String, String> aCustomAttrs)
  {
    return new StubObject (sID, LoggedInUserManager.getInstance ().getCurrentUserID (), aCustomAttrs);
  }
}
