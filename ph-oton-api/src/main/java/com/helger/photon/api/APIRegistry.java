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

import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.Nullable;

import com.helger.annotation.concurrent.GuardedBy;
import com.helger.annotation.concurrent.ThreadSafe;
import com.helger.annotation.style.ReturnsMutableCopy;
import com.helger.base.concurrent.SimpleReadWriteLock;
import com.helger.base.tostring.ToStringGenerator;
import com.helger.collection.commons.ICommonsList;

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

  public void registerAPI (@NonNull final APIDescriptor aDescriptor)
  {
    m_aRWLock.writeLocked ( () -> m_aApiDecls.addDescriptor (aDescriptor));
  }

  @NonNull
  @ReturnsMutableCopy
  public ICommonsList <IAPIDescriptor> getAllAPIDescriptors ()
  {
    return m_aRWLock.readLockedGet (m_aApiDecls::getAllDescriptors);
  }

  @Nullable
  public InvokableAPIDescriptor getAPIByPath (@NonNull final APIPath aPath, @NonNull final IAPIPathAmbiguityResolver aAmbiguityResolver)
  {
    return m_aRWLock.readLockedGet ( () -> m_aApiDecls.getMatching (aPath, aAmbiguityResolver));
  }

  @Override
  public String toString ()
  {
    return new ToStringGenerator (this).append ("APIDeclarations", m_aApiDecls).getToString ();
  }
}
