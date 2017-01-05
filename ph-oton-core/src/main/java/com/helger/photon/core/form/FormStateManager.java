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
package com.helger.photon.core.form;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.concurrent.GuardedBy;
import javax.annotation.concurrent.ThreadSafe;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.helger.commons.ValueEnforcer;
import com.helger.commons.annotation.ReturnsMutableCopy;
import com.helger.commons.annotation.UsedViaReflection;
import com.helger.commons.collection.ext.CommonsHashMap;
import com.helger.commons.collection.ext.ICommonsCollection;
import com.helger.commons.collection.ext.ICommonsMap;
import com.helger.commons.debug.GlobalDebug;
import com.helger.commons.state.EChange;
import com.helger.commons.string.ToStringGenerator;
import com.helger.web.scope.singleton.AbstractSessionWebSingleton;

@ThreadSafe
public final class FormStateManager extends AbstractSessionWebSingleton
{
  private static final Logger s_aLogger = LoggerFactory.getLogger (FormStateManager.class);

  @GuardedBy ("m_aRWLock")
  private final ICommonsMap <String, FormState> m_aMap = new CommonsHashMap <> ();
  @GuardedBy ("m_aRWLock")
  private boolean m_bAtLeastOnceAFormState = false;

  @Deprecated
  @UsedViaReflection
  public FormStateManager ()
  {}

  @Nonnull
  public static FormStateManager getInstance ()
  {
    return getSessionSingleton (FormStateManager.class);
  }

  public void saveFormState (@Nonnull final FormState aFormState)
  {
    ValueEnforcer.notNull (aFormState, "FormState");

    m_aRWLock.writeLocked ( () -> {
      m_aMap.put (aFormState.getFlowID (), aFormState);
      m_bAtLeastOnceAFormState = true;
    });

    if (GlobalDebug.isDebugMode ())
      s_aLogger.info ("Saved form state: " + aFormState.toString ());
    else
      s_aLogger.info ("Saved form state for page " + aFormState.getPageID ());
  }

  public boolean containedOnceAFormState ()
  {
    return m_aRWLock.readLocked ( () -> m_bAtLeastOnceAFormState);
  }

  public boolean containsAnySavedFormState ()
  {
    return m_aRWLock.readLocked ( () -> m_aMap.isNotEmpty ());
  }

  @Nullable
  public FormState getFormStateOfID (@Nullable final String sFlowID)
  {
    return m_aRWLock.readLocked ( () -> m_aMap.get (sFlowID));
  }

  @Nonnull
  @ReturnsMutableCopy
  public ICommonsCollection <FormState> getAllFormStates ()
  {
    return m_aRWLock.readLocked ( () -> m_aMap.copyOfValues ());
  }

  @Nonnull
  public EChange deleteFormState (@Nonnull final String sFlowID)
  {
    return m_aRWLock.writeLocked ( () -> m_aMap.removeObject (sFlowID));
  }

  @Nonnull
  public EChange deleteAllFormStates ()
  {
    return m_aRWLock.writeLocked ( () -> m_aMap.removeAll ());
  }

  @Override
  public String toString ()
  {
    return ToStringGenerator.getDerived (super.toString ()).append ("map", m_aMap).toString ();
  }
}
