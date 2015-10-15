package com.helger.photon.security.mgr;
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

import javax.annotation.Nonnull;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.helger.commons.annotation.UsedViaReflection;
import com.helger.commons.exception.InitializationException;
import com.helger.commons.lang.ClassHelper;
import com.helger.commons.scope.IScope;
import com.helger.commons.scope.singleton.AbstractGlobalSingleton;
import com.helger.photon.basic.audit.AuditHelper;
import com.helger.photon.basic.audit.AuditManager;
import com.helger.photon.security.lock.ObjectLockManager;
import com.helger.photon.security.login.LoggedInUserManager;

/**
 * The meta system manager encapsulates all managers that are located in this
 * project. Currently the contained managers are:
 * <ul>
 * <li>{@link AuditManager}</li>
 * </ul>
 *
 * @author Philip Helger
 */
public final class PhotonSecurityManager extends AbstractGlobalSingleton
{
  public static final String DIRECTORY_AUDITS = "audits/";

  private static final Logger s_aLogger = LoggerFactory.getLogger (PhotonSecurityManager.class);

  private AuditManager m_aAuditMgr;

  @Deprecated
  @UsedViaReflection
  public PhotonSecurityManager ()
  {}

  @Override
  protected void onAfterInstantiation (@Nonnull final IScope aScope)
  {
    try
    {
      m_aAuditMgr = new AuditManager (DIRECTORY_AUDITS, LoggedInUserManager.getInstance ());
      AuditHelper.setAuditor (m_aAuditMgr.getAuditor ());
      AuditHelper.onAuditExecuteSuccess ("audit-initialized");

      s_aLogger.info (ClassHelper.getClassLocalName (this) + " was initialized");
    }
    catch (final Throwable t)
    {
      throw new InitializationException ("Failed to init " + ClassHelper.getClassLocalName (this), t);
    }
  }

  @Override
  protected void onBeforeDestroy (@Nonnull final IScope aScopeToBeDestroyed)
  {
    if (m_aAuditMgr != null)
    {
      /*
       * Call here to ensure that the AuditManager is still present! Otherwise
       * the destruction order of the singletons is relevant!
       */
      AuditHelper.onAuditExecuteSuccess ("audit-shutdown");
    }
  }

  @Override
  protected void onDestroy (@Nonnull final IScope aScopeInDestruction)
  {
    if (m_aAuditMgr != null)
    {
      AuditHelper.setDefaultAuditor ();
      m_aAuditMgr.stop ();
    }
  }

  @Nonnull
  public static PhotonSecurityManager getInstance ()
  {
    return getGlobalSingleton (PhotonSecurityManager.class);
  }

  @Nonnull
  public static AuditManager getAuditMgr ()
  {
    return getInstance ().m_aAuditMgr;
  }

  @Nonnull
  public static ObjectLockManager getLockMgr ()
  {
    return ObjectLockManager.getInstance ();
  }
}
