/**
 * Copyright (C) 2014-2016 Philip Helger (www.helger.com)
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
package com.helger.photon.basic.object;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.concurrent.Immutable;

import org.joda.time.LocalDateTime;

import com.helger.commons.ValueEnforcer;
import com.helger.commons.annotation.Nonempty;
import com.helger.commons.state.EChange;
import com.helger.commons.string.ToStringGenerator;
import com.helger.datetime.util.PDTHelper;

/**
 * Abstract base implementation of {@link IObject} that handles everything
 * except {@link #getObjectType()}, {@link #equals(Object)} and
 * {@link #hashCode()}.
 *
 * @author Philip Helger
 */
@Immutable
public abstract class AbstractBaseObject implements IObject
{
  private final String m_sID;
  private final LocalDateTime m_aCreationDT;
  private final String m_sCreationUserID;
  private LocalDateTime m_aLastModificationDT;
  private String m_sLastModificationUserID;
  private LocalDateTime m_aDeletionDT;
  private String m_sDeletionUserID;

  public AbstractBaseObject (@Nonnull final IObject aObject)
  {
    this (aObject.getID (),
          aObject.getCreationDateTime (),
          aObject.getCreationUserID (),
          aObject.getLastModificationDateTime (),
          aObject.getLastModificationUserID (),
          aObject.getDeletionDateTime (),
          aObject.getDeletionUserID ());
  }

  public AbstractBaseObject (@Nonnull @Nonempty final String sID,
                             @Nullable final LocalDateTime aCreationDT,
                             @Nullable final String sCreationUserID,
                             @Nullable final LocalDateTime aLastModificationDT,
                             @Nullable final String sLastModificationUserID,
                             @Nullable final LocalDateTime aDeletionDT,
                             @Nullable final String sDeletionUserID)
  {
    m_sID = ValueEnforcer.notEmpty (sID, "ID");
    m_aCreationDT = aCreationDT;
    m_sCreationUserID = sCreationUserID;
    m_aLastModificationDT = aLastModificationDT;
    m_sLastModificationUserID = sLastModificationUserID;
    m_aDeletionDT = aDeletionDT;
    m_sDeletionUserID = sDeletionUserID;
  }

  @Nonnull
  @Nonempty
  public final String getID ()
  {
    return m_sID;
  }

  @Nullable
  public final LocalDateTime getCreationDateTime ()
  {
    return m_aCreationDT;
  }

  @Nullable
  public final String getCreationUserID ()
  {
    return m_sCreationUserID;
  }

  @Nullable
  public final LocalDateTime getLastModificationDateTime ()
  {
    return m_aLastModificationDT;
  }

  @Nullable
  public final String getLastModificationUserID ()
  {
    return m_sLastModificationUserID;
  }

  public final void setLastModification (@Nonnull final LocalDateTime aLastModificationDT,
                                         @Nonnull @Nonempty final String sLastModificationUserID)
  {
    ValueEnforcer.notNull (aLastModificationDT, "LastModificationDT");
    ValueEnforcer.notEmpty (sLastModificationUserID, "LastModificationUserID");

    if (isDeleted ())
      throw new IllegalStateException ("Object is deleted and can therefore not be modified!");

    m_aLastModificationDT = aLastModificationDT;
    m_sLastModificationUserID = sLastModificationUserID;
  }

  @Nullable
  public final LocalDateTime getDeletionDateTime ()
  {
    return m_aDeletionDT;
  }

  @Nullable
  public final String getDeletionUserID ()
  {
    return m_sDeletionUserID;
  }

  @Nonnull
  public final EChange setDeletion (@Nonnull final LocalDateTime aDeletionDT,
                                    @Nonnull @Nonempty final String sDeletionUserID)
  {
    ValueEnforcer.notNull (aDeletionDT, "DeletionDT");
    ValueEnforcer.notEmpty (sDeletionUserID, "DeletionUserID");

    if (m_aDeletionDT != null)
    {
      // Object is already deleted...
      return EChange.UNCHANGED;
    }

    m_aDeletionDT = aDeletionDT;
    m_sDeletionUserID = sDeletionUserID;
    return EChange.CHANGED;
  }

  @Nonnull
  public final EChange setUndeletion (@Nonnull final LocalDateTime aUndeletionDT,
                                      @Nonnull @Nonempty final String sUndeletionUserID)
  {
    ValueEnforcer.notNull (aUndeletionDT, "UndeletionDT");
    ValueEnforcer.notEmpty (sUndeletionUserID, "UndeletionUserID");

    if (m_aDeletionDT == null)
    {
      // Object is not deleted and can therefore not be undeleted
      return EChange.UNCHANGED;
    }

    setLastModification (aUndeletionDT, sUndeletionUserID);
    m_aDeletionDT = null;
    m_sDeletionUserID = null;
    return EChange.CHANGED;
  }

  public final boolean isDeleted ()
  {
    return m_aDeletionDT != null;
  }

  public final boolean isDeleted (@Nonnull final LocalDateTime aDT)
  {
    ValueEnforcer.notNull (aDT, "LocalDateTime");
    return m_aDeletionDT != null && PDTHelper.isLessOrEqual (m_aDeletionDT, aDT);
  }

  @Override
  public String toString ()
  {
    return new ToStringGenerator (this).append ("ID", m_sID)
                                       .appendIfNotNull ("creationDT", m_aCreationDT)
                                       .appendIfNotNull ("creationUserID", m_sCreationUserID)
                                       .appendIfNotNull ("lastModificationDT", m_aLastModificationDT)
                                       .appendIfNotNull ("lastModificationUserID", m_sLastModificationUserID)
                                       .appendIfNotNull ("deletionDT", m_aDeletionDT)
                                       .appendIfNotNull ("deletionUserID", m_sDeletionUserID)
                                       .toString ();
  }
}
