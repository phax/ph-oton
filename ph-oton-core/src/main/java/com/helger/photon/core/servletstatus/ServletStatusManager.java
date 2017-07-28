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
package com.helger.photon.core.servletstatus;

import java.lang.reflect.Modifier;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.concurrent.GuardedBy;
import javax.annotation.concurrent.ThreadSafe;
import javax.servlet.GenericServlet;
import javax.servlet.ServletContext;
import javax.servlet.ServletRegistration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.helger.commons.ValueEnforcer;
import com.helger.commons.annotation.ELockType;
import com.helger.commons.annotation.MustBeLocked;
import com.helger.commons.annotation.Nonempty;
import com.helger.commons.annotation.ReturnsMutableCopy;
import com.helger.commons.annotation.UsedViaReflection;
import com.helger.commons.collection.impl.CommonsHashMap;
import com.helger.commons.collection.impl.ICommonsMap;
import com.helger.scope.singleton.AbstractGlobalSingleton;
import com.helger.web.scope.IGlobalWebScope;
import com.helger.web.scope.mgr.WebScopeManager;

/**
 * A manager for keeping track of the default servlets state.
 *
 * @author Philip Helger
 */
@ThreadSafe
public final class ServletStatusManager extends AbstractGlobalSingleton
{
  private static final Logger s_aLogger = LoggerFactory.getLogger (ServletStatusManager.class);

  @GuardedBy ("m_aRWLock")
  private final ICommonsMap <String, ServletStatus> m_aMap = new CommonsHashMap <> ();

  @Deprecated
  @UsedViaReflection
  public ServletStatusManager ()
  {}

  @Nonnull
  public static ServletStatusManager getInstance ()
  {
    return getGlobalSingleton (ServletStatusManager.class);
  }

  /**
   * Reset all contained information!
   */
  public void reset ()
  {
    m_aRWLock.writeLocked ( () -> m_aMap.clear ());
  }

  @Nonnull
  @Nonempty
  private static String _getKey (@Nonnull final Class <? extends GenericServlet> aServletClass)
  {
    return aServletClass.getName ();
  }

  @Nonnull
  @MustBeLocked (ELockType.WRITE)
  private ServletStatus _getOrCreateServletStatus (@Nonnull final Class <? extends GenericServlet> aServletClass)
  {
    ValueEnforcer.notNull (aServletClass, "Servlet class");
    if (Modifier.isAbstract (aServletClass.getModifiers ()))
      throw new IllegalStateException ("Passed servlet class is abstract: " + aServletClass);

    final String sKey = _getKey (aServletClass);
    return m_aMap.computeIfAbsent (sKey, k -> new ServletStatus (aServletClass.getName ()));
  }

  private void _updateStatus (@Nonnull final Class <? extends GenericServlet> aServletClass,
                              @Nonnull final EServletStatus eNewStatus)
  {
    ValueEnforcer.notNull (eNewStatus, "NewStatus");

    m_aRWLock.writeLocked ( () -> _getOrCreateServletStatus (aServletClass).internalSetCurrentStatus (eNewStatus));

    if (s_aLogger.isDebugEnabled ())
      s_aLogger.debug ("Servlet status of " + aServletClass + " changed to " + eNewStatus);
  }

  public void onServletCtor (@Nonnull final Class <? extends GenericServlet> aServletClass)
  {
    _updateStatus (aServletClass, EServletStatus.CONSTRUCTED);
  }

  public void onServletInit (@Nonnull final Class <? extends GenericServlet> aServletClass)
  {
    _updateStatus (aServletClass, EServletStatus.INITED);
  }

  public void onServletInvocation (@Nonnull final Class <? extends GenericServlet> aServletClass)
  {
    m_aRWLock.writeLocked ( () -> _getOrCreateServletStatus (aServletClass).internalIncrementInvocationCount ());
  }

  public void onServletDestroy (@Nonnull final Class <? extends GenericServlet> aServletClass)
  {
    _updateStatus (aServletClass, EServletStatus.DESTROYED);
  }

  @Nullable
  public ServletStatus getStatus (@Nullable final Class <? extends GenericServlet> aServletClass)
  {
    if (aServletClass == null)
      return null;

    final String sKey = _getKey (aServletClass);
    return m_aRWLock.readLocked ( () -> m_aMap.get (sKey));
  }

  @Nonnull
  @ReturnsMutableCopy
  public ICommonsMap <String, ServletStatus> getAllStatus ()
  {
    return m_aRWLock.readLocked ( () -> m_aMap.getClone ());
  }

  /**
   * Static utility method that checks the {@link ServletContext} whether the
   * passed servlet class is registered or not.
   *
   * @param aServletClass
   *        The servlet class to be checked. May not be <code>null</code>.
   * @return <code>true</code> if the passed servlet class is contained in the
   *         {@link ServletContext}.
   */
  public boolean isServletRegistered (@Nonnull final Class <? extends GenericServlet> aServletClass)
  {
    final String sClassName = ValueEnforcer.notNull (aServletClass, "ServletClass").getName ();

    // May be null for unit tests
    final IGlobalWebScope aGlobalScope = WebScopeManager.getGlobalScopeOrNull ();
    if (aGlobalScope != null)
    {
      try
      {
        for (final ServletRegistration aRegistration : aGlobalScope.getServletContext ()
                                                                   .getServletRegistrations ()
                                                                   .values ())
          if (aRegistration.getClassName ().equals (sClassName))
            return true;
      }
      catch (final UnsupportedOperationException ex)
      {
        // Happens for mock servlet contexts
      }
    }
    return false;
  }
}
