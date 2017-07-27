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
import java.util.Map;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.concurrent.Immutable;

import com.helger.commons.annotation.Nonempty;
import com.helger.commons.annotation.ReturnsMutableObject;
import com.helger.commons.collection.attr.AttributeContainer;
import com.helger.commons.string.ToStringGenerator;

/**
 * Abstract base implementation of {@link IObjectWithCustomAttrs} that handles
 * everything except {@link #getObjectType()}, {@link #equals(Object)} and
 * {@link #hashCode()}.
 *
 * @author Philip Helger
 */
@Immutable
public abstract class AbstractBaseObjectWithCustomAttrs extends AbstractBaseObject implements IObjectWithCustomAttrs
{
  private final AttributeContainer <String, String> m_aCustomAttrs = new AttributeContainer <> ();

  public AbstractBaseObjectWithCustomAttrs (@Nonnull final IObjectWithCustomAttrs aObject)
  {
    this (aObject, aObject.customAttrs ());
  }

  public AbstractBaseObjectWithCustomAttrs (@Nonnull final IObject aObject,
                                            @Nullable final Map <String, String> aCustomAttrs)
  {
    super (aObject);
    m_aCustomAttrs.putAllIn (aCustomAttrs);
  }

  public AbstractBaseObjectWithCustomAttrs (@Nonnull @Nonempty final String sID,
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
           sDeletionUserID);
    m_aCustomAttrs.putAllIn (aCustomAttrs);
  }

  @Nonnull
  @ReturnsMutableObject
  public final AttributeContainer <String, String> customAttrs ()
  {
    return m_aCustomAttrs;
  }

  @Override
  public String toString ()
  {
    return ToStringGenerator.getDerived (super.toString ()).append ("CusomAttrs", m_aCustomAttrs).getToString ();
  }
}
