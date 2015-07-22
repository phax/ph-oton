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
package com.helger.photon.core.servletstatus;

import java.lang.reflect.Modifier;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.concurrent.GuardedBy;
import javax.annotation.concurrent.ThreadSafe;
import javax.servlet.ServletContext;
import javax.servlet.ServletRegistration;
import javax.servlet.http.HttpServlet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.helger.commons.ValueEnforcer;
import com.helger.commons.annotation.ELockType;
import com.helger.commons.annotation.MustBeLocked;
import com.helger.commons.annotation.Nonempty;
import com.helger.commons.annotation.ReturnsMutableCopy;
import com.helger.commons.collection.CollectionHelper;
import com.helger.web.scope.IGlobalWebScope;
import com.helger.web.scope.mgr.WebScopeManager;

/**
 * A manager for keeping track of the default servlets state.
 *
 * @author Philip Helger
 */
@ThreadSafe
public final class ServletStatusManager
{
  private static final Logger s_aLogger = LoggerFactory.getLogger (ServletStatusManager.class);

  private static final ReadWriteLock s_aRWLock = new ReentrantReadWriteLock ();
  @GuardedBy ("s_aRWLock")
  private static final Map <String, ServletStatus> s_aMap = new HashMap <String, ServletStatus> ();

  private ServletStatusManager ()
  {}

  @Nonnull
  @Nonempty
  private static String _getKey (@Nonnull final Class <? extends HttpServlet> aServletClass)
  {
    return aServletClass.getName ();
  }

  @Nonnull
  @MustBeLocked (ELockType.WRITE)
  private static ServletStatus _getOrCreateServletStatus (@Nonnull final Class <? extends HttpServlet> aServletClass)
  {
    ValueEnforcer.notNull (aServletClass, "Servlet class");
    if (Modifier.isAbstract (aServletClass.getModifiers ()))
      throw new IllegalStateException ("Passed servlet class is abstract: " + aServletClass);

    final String sKey = _getKey (aServletClass);
    ServletStatus aStatus = s_aMap.get (sKey);
    if (aStatus == null)
    {
      aStatus = new ServletStatus (aServletClass.getName ());
      s_aMap.put (sKey, aStatus);
    }
    return aStatus;
  }

  private static void _updateStatus (@Nonnull final Class <? extends HttpServlet> aServletClass,
                                     @Nonnull final EServletStatus eNewStatus)
  {
    ValueEnforcer.notNull (eNewStatus, "NewStatus");

    s_aRWLock.writeLock ().lock ();
    try
    {
      _getOrCreateServletStatus (aServletClass).setCurrentStatus (eNewStatus);
    }
    finally
    {
      s_aRWLock.writeLock ().unlock ();
    }

    if (s_aLogger.isDebugEnabled ())
      s_aLogger.debug ("Servlet status of " + aServletClass + " changed to " + eNewStatus);
  }

  public static void onServletCtor (@Nonnull final Class <? extends HttpServlet> aServletClass)
  {
    _updateStatus (aServletClass, EServletStatus.CONSTRUCTED);
  }

  public static void onServletInit (@Nonnull final Class <? extends HttpServlet> aServletClass)
  {
    _updateStatus (aServletClass, EServletStatus.INITED);
  }

  public static void onServletInvocation (@Nonnull final Class <? extends HttpServlet> aServletClass)
  {
    s_aRWLock.writeLock ().lock ();
    try
    {
      _getOrCreateServletStatus (aServletClass).incrementInvocationCount ();
    }
    finally
    {
      s_aRWLock.writeLock ().unlock ();
    }
  }

  public static void onServletDestroy (@Nonnull final Class <? extends HttpServlet> aServletClass)
  {
    _updateStatus (aServletClass, EServletStatus.DESTROYED);
  }

  @Nullable
  public static ServletStatus getStatus (@Nullable final Class <? extends HttpServlet> aServletClass)
  {
    if (aServletClass == null)
      return null;

    s_aRWLock.readLock ().lock ();
    try
    {
      return s_aMap.get (_getKey (aServletClass));
    }
    finally
    {
      s_aRWLock.readLock ().unlock ();
    }
  }

  @Nonnull
  @ReturnsMutableCopy
  public static Map <String, ServletStatus> getAllStatus ()
  {
    s_aRWLock.readLock ().lock ();
    try
    {
      return CollectionHelper.newMap (s_aMap);
    }
    finally
    {
      s_aRWLock.readLock ().unlock ();
    }
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
  public static boolean isServletRegistered (@Nonnull final Class <? extends HttpServlet> aServletClass)
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
