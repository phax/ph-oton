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

import com.helger.annotation.Nonnegative;
import com.helger.collection.commons.CommonsArrayList;

import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;

/**
 * A specific implementation of {@link IAuditManager} that does nothing.
 *
 * @author Philip Helger
 * @since 8.4.1
 */
public final class DoNothingAuditManager implements IAuditManager
{
  private final IAuditor m_aAuditor = new DoNothingAuditor ( () -> null);

  public boolean isInMemory ()
  {
    return true;
  }

  @Nullable
  public String getBaseDir ()
  {
    return null;
  }

  @Nonnull
  public IAuditor getAuditor ()
  {
    return m_aAuditor;
  }

  @Nonnull
  public List <IAuditItem> getLastAuditItems (@Nonnegative final int nMaxItems)
  {
    return new CommonsArrayList <> ();
  }

  public void stop ()
  {
    // Nothing to do
  }

  @Nullable
  public LocalDate getEarliestAuditDate ()
  {
    return null;
  }
}
