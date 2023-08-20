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
package com.helger.photon.io;

import java.io.File;

import javax.annotation.Nonnull;

import org.junit.rules.ExternalResource;

import com.helger.commons.ValueEnforcer;
import com.helger.dao.AbstractDAO;
import com.helger.scope.mock.ScopeTestRule;

/**
 * Non-web scope aware test rule, with a defined storage root directory
 *
 * @author Philip Helger
 */
public class PhotonIOTestRule extends ExternalResource
{
  private final File m_aDataPath;
  private boolean m_bOldDAOSilentMode;

  /**
   * Ctor using the default storage path from {@link ScopeTestRule}
   */
  public PhotonIOTestRule ()
  {
    this (ScopeTestRule.STORAGE_PATH);
  }

  /**
   * Ctor with an arbitrary path
   *
   * @param aDataPath
   *        The data path to be used. May not be <code>null</code>.
   */
  public PhotonIOTestRule (@Nonnull final File aDataPath)
  {
    ValueEnforcer.notNull (aDataPath, "DataPath");
    m_aDataPath = aDataPath.getAbsoluteFile ();
  }

  /**
   * @return The used data path. Never <code>null</code>.
   */
  @Nonnull
  public final File getDataPath ()
  {
    return m_aDataPath;
  }

  @Override
  public void before ()
  {
    m_bOldDAOSilentMode = AbstractDAO.setSilentMode (true);
    WebFileIO.initPaths (m_aDataPath, m_aDataPath.getAbsolutePath (), false);
  }

  @Override
  public void after ()
  {
    WebFileIO.resetPaths ();
    AbstractDAO.setSilentMode (m_bOldDAOSilentMode);
  }
}
