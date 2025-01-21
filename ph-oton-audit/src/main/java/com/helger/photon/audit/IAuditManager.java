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
package com.helger.photon.audit;

import java.time.LocalDate;
import java.util.List;

import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.helger.commons.annotation.CodingStyleguideUnaware;
import com.helger.commons.annotation.ReturnsMutableCopy;

/**
 * Interface for a manager that can handle audit items.
 *
 * @author Philip Helger
 */
public interface IAuditManager
{
  /**
   * @return <code>true</code> if this manager operates only in memory,
   *         <code>false</code> if it keeps persistent files.
   * @see #getBaseDir()
   */
  boolean isInMemory ();

  /**
   * @return The base directory used for audit entries. May be <code>null</code>
   *         to indicate in-memory only auditing. If the results is not
   *         <code>null</code> it must end with a path separator (slash).
   * @see #isInMemory()
   */
  @Nullable
  String getBaseDir ();

  /**
   * @return The underlying auditor. Never <code>null</code>.
   */
  @Nonnull
  IAuditor getAuditor ();

  /**
   * @param nMaxItems
   *        The maximum number of items. Must be &gt; 0.
   * @return The n latest audit items. Never <code>null</code>.
   */
  @Nonnull
  @ReturnsMutableCopy
  @CodingStyleguideUnaware
  List <IAuditItem> getLastAuditItems (@Nonnegative int nMaxItems);

  /**
   * Stop taking new audits. Call this upon shutdown for correct cleanup!
   * Consecutive calls to this method have no further effect.
   */
  void stop ();

  /**
   * @return The earliest date for which auditing information is present. If
   *         this is an in-memory audit manager, it must return the current
   *         date. May be <code>null</code> if a persistent storage is used and
   *         no entry is present yet.
   */
  @Nullable
  LocalDate getEarliestAuditDate ();
}
