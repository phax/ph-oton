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
package com.helger.photon.basic.object;

import java.util.Map;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import org.joda.time.LocalDateTime;

import com.helger.commons.annotation.Nonempty;
import com.helger.commons.hashcode.HashCodeGenerator;

/**
 * Abstract base implementation of {@link IObjectWithCustomAttrs} that handles
 * everything except {@link #getObjectType()}.
 *
 * @author Philip Helger
 */
public abstract class AbstractObjectWithCustomAttrs extends AbstractBaseObjectWithCustomAttrs
{
  // Status member
  private Integer m_aHashCode;

  public AbstractObjectWithCustomAttrs (@Nonnull final IObjectWithCustomAttrs aObject)
  {
    super (aObject);
  }

  public AbstractObjectWithCustomAttrs (@Nonnull final IObject aObject,
                                        @Nullable final Map <String, String> aCustomAttrs)
  {
    super (aObject, aCustomAttrs);
  }

  public AbstractObjectWithCustomAttrs (@Nonnull @Nonempty final String sID,
                                        @Nullable final LocalDateTime aCreationDT,
                                        @Nullable final String sCreationUserID,
                                        @Nullable final LocalDateTime aLastModificationDT,
                                        @Nullable final String sLastModificationUserID,
                                        @Nullable final LocalDateTime aDeletionDT,
                                        @Nullable final String sDeletionUserID,
                                        @Nullable final Map <String, String> aCustomAttrs)
  {
    super (sID,
           aCreationDT,
           sCreationUserID,
           aLastModificationDT,
           sLastModificationUserID,
           aDeletionDT,
           sDeletionUserID,
           aCustomAttrs);
  }

  @Override
  public final boolean equals (final Object o)
  {
    if (o == this)
      return true;
    if (o == null || !getClass ().equals (o.getClass ()))
      return false;
    final AbstractObjectWithCustomAttrs rhs = (AbstractObjectWithCustomAttrs) o;
    return getID ().equals (rhs.getID ());
  }

  @Override
  public final int hashCode ()
  {
    Integer aObj = m_aHashCode;
    if (aObj == null)
    {
      aObj = new HashCodeGenerator (this).append (getID ()).getHashCodeObj ();
      m_aHashCode = aObj;
    }
    return aObj.intValue ();
  }
}
