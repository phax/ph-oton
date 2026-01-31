/*
 * Copyright (C) 2014-2026 Philip Helger (www.helger.com)
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

import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.Nullable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.helger.annotation.concurrent.GuardedBy;
import com.helger.annotation.concurrent.ThreadSafe;
import com.helger.annotation.style.ReturnsMutableCopy;
import com.helger.base.concurrent.SimpleReadWriteLock;
import com.helger.base.enforce.ValueEnforcer;
import com.helger.base.string.StringHelper;
import com.helger.base.tostring.ToStringGenerator;
import com.helger.cache.regex.RegExHelper;
import com.helger.collection.commons.CommonsHashMap;
import com.helger.collection.commons.ICommonsMap;
import com.helger.photon.ajax.decl.IAjaxFunctionDeclaration;

/**
 * The default implementation of {@link IAjaxRegistry}.
 *
 * @author Philip Helger
 * @since 8.1.4
 */
@ThreadSafe
public class AjaxRegistry implements IAjaxRegistry
{
  private static final Logger LOGGER = LoggerFactory.getLogger (AjaxRegistry.class);

  private final SimpleReadWriteLock m_aRWLock = new SimpleReadWriteLock ();
  @GuardedBy ("m_aRWLock")
  private final ICommonsMap <String, IAjaxFunctionDeclaration> m_aFuncDecls = new CommonsHashMap <> ();

  public AjaxRegistry ()
  {}

  public static boolean isValidFunctionName (@Nullable final String sFunctionName)
  {
    // All characters allowed should be valid in URLs without masking
    return StringHelper.isNotEmpty (sFunctionName) && RegExHelper.stringMatchesPattern ("^[a-zA-Z0-9\\-_]+$", sFunctionName);
  }

  @NonNull
  @ReturnsMutableCopy
  public ICommonsMap <String, IAjaxFunctionDeclaration> getAllRegisteredFunctions ()
  {
    return m_aRWLock.readLockedGet (m_aFuncDecls::getClone);
  }

  @Nullable
  public IAjaxFunctionDeclaration getRegisteredFunction (@Nullable final String sFunctionName)
  {
    if (StringHelper.isEmpty (sFunctionName))
      return null;

    return m_aRWLock.readLockedGet ( () -> m_aFuncDecls.get (sFunctionName));
  }

  public boolean isRegisteredFunction (@Nullable final String sFunctionName)
  {
    if (StringHelper.isEmpty (sFunctionName))
      return false;

    return m_aRWLock.readLockedBoolean ( () -> m_aFuncDecls.containsKey (sFunctionName));
  }

  public void registerFunction (@NonNull final IAjaxFunctionDeclaration aFunctionDeclaration)
  {
    ValueEnforcer.notNull (aFunctionDeclaration, "FunctionDeclaration");

    final String sFunctionName = aFunctionDeclaration.getName ();

    m_aRWLock.writeLocked ( () -> {
      if (m_aFuncDecls.containsKey (sFunctionName))
        throw new IllegalArgumentException ("An Ajax function with the name '" +
                                            sFunctionName +
                                            "' is already registered. Replacing the old declaration.");
      m_aFuncDecls.put (sFunctionName, aFunctionDeclaration);
    });

    if (LOGGER.isDebugEnabled ())
      LOGGER.debug ("Registered AJAX function '" + sFunctionName + "' with executor factory " + aFunctionDeclaration.getExecutorFactory ());
  }

  @Override
  public String toString ()
  {
    return new ToStringGenerator (this).append ("FuncDecls", m_aFuncDecls).getToString ();
  }
}
