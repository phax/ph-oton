/**
 * Copyright (C) 2014-2021 Philip Helger (www.helger.com)
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
package com.helger.photon.ajax;

import javax.annotation.Nonnull;
import javax.annotation.concurrent.ThreadSafe;

import com.helger.commons.ValueEnforcer;
import com.helger.commons.annotation.UsedViaReflection;
import com.helger.commons.annotation.VisibleForTesting;
import com.helger.commons.string.ToStringGenerator;
import com.helger.web.scope.singleton.AbstractGlobalWebSingleton;

/**
 * Global AJAX invoker. Was reworked in v8.1.4 to clearly separate between
 * registry and invoker.
 *
 * @author Philip Helger
 */
@ThreadSafe
public final class GlobalAjaxInvoker extends AbstractGlobalWebSingleton
{
  private IAjaxRegistry m_aRegistry = new AjaxRegistry ();
  private IAjaxInvoker m_aInvoker = new AjaxInvoker ();

  /**
   * Constructor. Used only internally.
   */
  @Deprecated
  @UsedViaReflection
  public GlobalAjaxInvoker ()
  {}

  @Nonnull
  public static GlobalAjaxInvoker getInstance ()
  {
    return getGlobalSingleton (GlobalAjaxInvoker.class);
  }

  /**
   * Reset all values to default. This is only intended for testing purposes.
   * ATTENTION: this removes ALL registrations!
   */
  @VisibleForTesting
  public void resetToDefault ()
  {
    m_aRegistry = new AjaxRegistry ();
    m_aInvoker = new AjaxInvoker ();
  }

  @Nonnull
  public IAjaxRegistry getRegistry ()
  {
    return m_aRWLock.readLockedGet ( () -> m_aRegistry);
  }

  /**
   * Set the global registry to be used. Note: this API can only called BEFORE
   * registrations are performed. Afterwards an {@link IllegalStateException} is
   * thrown if this API is invoked.
   *
   * @param aRegistry
   *        The registry to use. May not be <code>null</code>.
   */
  @Nonnull
  public void setRegistry (@Nonnull final IAjaxRegistry aRegistry)
  {
    ValueEnforcer.notNull (aRegistry, "Registry");
    if (m_aRWLock.readLockedBoolean ( () -> m_aRegistry.getAllRegisteredFunctions ().isNotEmpty ()))
      throw new IllegalStateException ("Cannot change the registry after a function was already registered!");

    m_aRWLock.writeLockedGet ( () -> m_aRegistry = aRegistry);
  }

  @Nonnull
  public IAjaxInvoker getInvoker ()
  {
    return m_aRWLock.readLockedGet ( () -> m_aInvoker);
  }

  /**
   * Set the global invoker to be used. This can be changed during the runtime
   * of the application and is independent of the registry state. Use this to
   * e.g. increase the debug logging or tracing of the invocations.
   *
   * @param aInvoker
   *        The invoker to be used. May not be <code>null</code>.
   */
  @Nonnull
  public void setInvoker (@Nonnull final IAjaxInvoker aInvoker)
  {
    ValueEnforcer.notNull (aInvoker, "Invoker");
    m_aRWLock.writeLockedGet ( () -> m_aInvoker = aInvoker);
  }

  @Override
  public String toString ()
  {
    return ToStringGenerator.getDerived (super.toString ()).append ("Registry", m_aRegistry).append ("Invoker", m_aInvoker).getToString ();
  }
}
