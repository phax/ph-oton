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
package com.helger.photon.api;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.concurrent.GuardedBy;
import javax.annotation.concurrent.ThreadSafe;

import com.helger.commons.annotation.ReturnsMutableCopy;
import com.helger.commons.collection.impl.ICommonsList;
import com.helger.commons.concurrent.SimpleReadWriteLock;
import com.helger.commons.string.ToStringGenerator;

/**
 * Default implementation if {@link IAPIRegistry}.
 *
 * @author Philip Helger
 */
@ThreadSafe
public class APIRegistry implements IAPIRegistry
{
  private final SimpleReadWriteLock m_aRWLock = new SimpleReadWriteLock ();
  @GuardedBy ("m_aRWLock")
  private final APIDescriptorList m_aApiDecls = new APIDescriptorList ();

  public APIRegistry ()
  {}

  public void registerAPI (@Nonnull final APIDescriptor aDescriptor)
  {
    m_aRWLock.writeLocked ( () -> m_aApiDecls.addDescriptor (aDescriptor));
  }

  @Nonnull
  @ReturnsMutableCopy
  public ICommonsList <IAPIDescriptor> getAllAPIDescriptors ()
  {
    return m_aRWLock.readLockedGet (m_aApiDecls::getAllDescriptors);
  }

  @Nullable
  public InvokableAPIDescriptor getAPIByPath (@Nonnull final APIPath aPath, @Nonnull final IAPIPathAmbiguityResolver aAmbiguityResolver)
  {
    return m_aRWLock.readLockedGet ( () -> m_aApiDecls.getMatching (aPath, aAmbiguityResolver));
  }

  @Override
  public String toString ()
  {
    return new ToStringGenerator (this).append ("APIDeclarations", m_aApiDecls).getToString ();
  }
}
