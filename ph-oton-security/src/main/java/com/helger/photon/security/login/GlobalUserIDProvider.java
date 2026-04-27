/*
 * Copyright (C) 2014-2026 Philip Helger (www.helger.com)
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
package com.helger.photon.security.login;

import java.util.function.Supplier;

import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.Nullable;

import com.helger.annotation.concurrent.GuardedBy;
import com.helger.annotation.concurrent.ThreadSafe;
import com.helger.annotation.style.PresentForCodeCoverage;
import com.helger.base.concurrent.SimpleReadWriteLock;
import com.helger.base.enforce.ValueEnforcer;

/**
 * Global, application wide provider for the current user ID. Internally a {@link Supplier} of
 * {@link String} is used. The default implementation returns
 * {@link LoggedInUserManager#getCurrentUserID()}.
 *
 * @author Philip Helger
 * @since 10.2.3
 */
@ThreadSafe
public final class GlobalUserIDProvider
{
  /**
   * The default supplier returning the current user ID from the {@link LoggedInUserManager}.
   */
  public static final Supplier <String> DEFAULT_SUPPLIER = () -> LoggedInUserManager.getInstance ().getCurrentUserID ();

  private static final SimpleReadWriteLock RW_LOCK = new SimpleReadWriteLock ();

  @GuardedBy ("RW_LOCK")
  private static Supplier <String> s_aSupplier = DEFAULT_SUPPLIER;

  @PresentForCodeCoverage
  private static final GlobalUserIDProvider INSTANCE = new GlobalUserIDProvider ();

  private GlobalUserIDProvider ()
  {}

  /**
   * @return The currently active user ID supplier. Never <code>null</code>.
   */
  @NonNull
  public static Supplier <String> getUserIDSupplier ()
  {
    return RW_LOCK.readLockedGet ( () -> s_aSupplier);
  }

  /**
   * Set the supplier to be used for retrieving the current user ID.
   *
   * @param aSupplier
   *        The supplier to use. May not be <code>null</code>.
   */
  public static void setUserIDSupplier (@NonNull final Supplier <String> aSupplier)
  {
    ValueEnforcer.notNull (aSupplier, "Supplier");
    RW_LOCK.writeLocked ( () -> s_aSupplier = aSupplier);
  }

  /**
   * Reset the user ID supplier to the default one ({@link #DEFAULT_SUPPLIER}).
   */
  public static void setUserIDSupplierToDefault ()
  {
    setUserIDSupplier (DEFAULT_SUPPLIER);
  }

  /**
   * @return The current user ID as returned by the configured supplier. May be <code>null</code> if
   *         no user is logged in or the supplier returns <code>null</code>.
   */
  @Nullable
  public static String getCurrentUserID ()
  {
    return getUserIDSupplier ().get ();
  }
}
