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
package com.helger.photon.basic.app.dao.container;

import java.util.function.Supplier;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.OverridingMethodsMustInvokeSuper;
import javax.annotation.concurrent.ThreadSafe;

import com.helger.commons.ValueEnforcer;
import com.helger.commons.concurrent.SimpleReadWriteLock;
import com.helger.photon.basic.app.dao.IDAO;

/**
 * Abstract base implementation of {@link IDAOContainer}.
 *
 * @author Philip Helger
 */
@ThreadSafe
public abstract class AbstractDAOContainer implements IDAOContainer
{
  protected final SimpleReadWriteLock m_aRWLock = new SimpleReadWriteLock ();

  @OverridingMethodsMustInvokeSuper
  public boolean isAutoSaveEnabled ()
  {
    return m_aRWLock.readLocked ( () -> containsAny (x -> x != null && x.isAutoSaveEnabled ()));
  }

  public final void beginWithoutAutoSave ()
  {
    m_aRWLock.writeLocked ( () -> {
      for (final IDAO aDAO : getAllContainedDAOs ())
        if (aDAO != null)
          aDAO.beginWithoutAutoSave ();
    });
  }

  public final void endWithoutAutoSave ()
  {
    m_aRWLock.writeLocked ( () -> {
      for (final IDAO aDAO : getAllContainedDAOs ())
        if (aDAO != null)
          aDAO.endWithoutAutoSave ();
    });
  }

  @OverridingMethodsMustInvokeSuper
  public void performWithoutAutoSave (@Nonnull final Runnable aRunnable)
  {
    ValueEnforcer.notNull (aRunnable, "Runnable");

    beginWithoutAutoSave ();
    try
    {
      aRunnable.run ();
    }
    finally
    {
      endWithoutAutoSave ();
    }
  }

  @OverridingMethodsMustInvokeSuper
  @Nullable
  public <RETURNTYPE> RETURNTYPE performWithoutAutoSave (@Nonnull final Supplier <RETURNTYPE> aCallable)
  {
    ValueEnforcer.notNull (aCallable, "Callable");

    beginWithoutAutoSave ();
    try
    {
      return aCallable.get ();
    }
    finally
    {
      endWithoutAutoSave ();
    }
  }
}
