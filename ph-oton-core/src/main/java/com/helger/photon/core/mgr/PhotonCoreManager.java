/*
 * Copyright (C) 2014-2023 Philip Helger (www.helger.com)
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
package com.helger.photon.core.mgr;

import javax.annotation.Nonnull;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.helger.commons.annotation.UsedViaReflection;
import com.helger.commons.exception.InitializationException;
import com.helger.commons.lang.ClassHelper;
import com.helger.photon.core.go.GoMappingManager;
import com.helger.photon.core.smtp.FailedMailQueueWithDAO;
import com.helger.photon.core.smtp.NamedSMTPSettingsManager;
import com.helger.scope.IScope;
import com.helger.scope.singleton.AbstractGlobalSingleton;
import com.helger.smtp.scope.ScopedMailAPI;

/**
 * The meta system manager encapsulates all managers that are located in this
 * project. Currently the contained managers are:
 * <ul>
 * <li>{@link FailedMailQueueWithDAO}</li>
 * <li>{@link GoMappingManager}</li>
 * <li>{@link NamedSMTPSettingsManager}</li>
 * </ul>
 *
 * @author Philip Helger
 */
public final class PhotonCoreManager extends AbstractGlobalSingleton
{
  public static final String DIRECTORY_AUDITS = "audits/";
  public static final String FAILED_MAILS_XML = "failedmails.xml";
  public static final String GO_XML = "go.xml";
  public static final String SMTP_SETTINGS_XML = "smtpsettings.xml";

  private static final Logger LOGGER = LoggerFactory.getLogger (PhotonCoreManager.class);

  private FailedMailQueueWithDAO m_aFailedMailQueue;
  private GoMappingManager m_aGoMappingMgr;
  private NamedSMTPSettingsManager m_aSMTPSettingsMgr;

  @Deprecated
  @UsedViaReflection
  public PhotonCoreManager ()
  {}

  @Override
  protected void onAfterInstantiation (@Nonnull final IScope aScope)
  {
    try
    {
      m_aSMTPSettingsMgr = new NamedSMTPSettingsManager (SMTP_SETTINGS_XML);

      m_aFailedMailQueue = new FailedMailQueueWithDAO (FAILED_MAILS_XML);
      ScopedMailAPI.getInstance ().setFailedMailQueue (m_aFailedMailQueue);

      m_aGoMappingMgr = new GoMappingManager (GO_XML);

      LOGGER.info (ClassHelper.getClassLocalName (this) + " was initialized");
    }
    catch (final Exception ex)
    {
      throw new InitializationException ("Failed to init " + ClassHelper.getClassLocalName (this), ex);
    }
  }

  @Nonnull
  public static PhotonCoreManager getInstance ()
  {
    return getGlobalSingleton (PhotonCoreManager.class);
  }

  @Nonnull
  public static NamedSMTPSettingsManager getSMTPSettingsMgr ()
  {
    return getInstance ().m_aSMTPSettingsMgr;
  }

  @Nonnull
  public static FailedMailQueueWithDAO getFailedMailQueue ()
  {
    return getInstance ().m_aFailedMailQueue;
  }

  @Nonnull
  public static GoMappingManager getGoMappingMgr ()
  {
    return getInstance ().m_aGoMappingMgr;
  }
}
