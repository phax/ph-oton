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
package com.helger.photon.basic.object;

import java.time.LocalDateTime;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.helger.commons.annotation.Nonempty;
import com.helger.commons.hashcode.HashCodeGenerator;
import com.helger.commons.hashcode.IHashCodeGenerator;

/**
 * Abstract base implementation of {@link IObject} that handles everything
 * except {@link #getObjectType()}.
 *
 * @author Philip Helger
 */
public abstract class AbstractObject extends AbstractBaseObject
{
  // Status vars
  private transient int m_nHashCode = IHashCodeGenerator.ILLEGAL_HASHCODE;

  public AbstractObject (@Nonnull final IObject aObject)
  {
    super (aObject);
  }

  public AbstractObject (@Nonnull @Nonempty final String sID,
                         @Nullable final LocalDateTime aCreationDT,
                         @Nullable final String sCreationUserID,
                         @Nullable final LocalDateTime aLastModificationDT,
                         @Nullable final String sLastModificationUserID,
                         @Nullable final LocalDateTime aDeletionDT,
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

  @Override
  public final boolean equals (final Object o)
  {
    if (o == this)
      return true;
    if (o == null || !getClass ().equals (o.getClass ()))
      return false;
    final AbstractObject rhs = (AbstractObject) o;
    return getID ().equals (rhs.getID ());
  }

  @Override
  public final int hashCode ()
  {
    int ret = m_nHashCode;
    if (ret == IHashCodeGenerator.ILLEGAL_HASHCODE)
      ret = m_nHashCode = new HashCodeGenerator (this).append (getID ()).getHashCode ();
    return ret;
  }
}
