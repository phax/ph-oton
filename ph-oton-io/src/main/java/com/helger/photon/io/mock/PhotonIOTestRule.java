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
package com.helger.photon.io.mock;

import java.io.File;

import org.junit.rules.ExternalResource;

import com.helger.annotation.Nonempty;
import com.helger.annotation.OverridingMethodsMustInvokeSuper;
import com.helger.base.enforce.ValueEnforcer;
import com.helger.dao.AbstractDAO;
import com.helger.photon.io.WebFileIO;
import com.helger.scope.mock.ScopeTestRule;

import jakarta.annotation.Nonnull;

/**
 * Non-web scope aware test rule, with a defined storage root directory
 *
 * @author Philip Helger
 */
public class PhotonIOTestRule extends ExternalResource
{
  private final File m_aDataPath;
  private final String m_sServletContextPath;
  private boolean m_bOldDAOSilentMode;
  private boolean m_bOldWebFileIOSilentMode;

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
    this (aDataPath, aDataPath.getAbsolutePath ());
  }

  /**
   * Ctor with an arbitrary path
   *
   * @param aDataPath
   *        The data path to be used. May not be <code>null</code>.
   * @param sServletContextPath
   *        The servlet context path to be used. May not be <code>null</code>.
   */
  public PhotonIOTestRule (@Nonnull final File aDataPath, @Nonnull @Nonempty final String sServletContextPath)
  {
    ValueEnforcer.notNull (aDataPath, "DataPath");
    ValueEnforcer.notNull (sServletContextPath, "ServletContextPath");
    m_aDataPath = aDataPath.getAbsoluteFile ();
    m_sServletContextPath = sServletContextPath;
  }

  /**
   * @return The used data path. Never <code>null</code>.
   */
  @Nonnull
  public final File getDataPath ()
  {
    return m_aDataPath;
  }

  /**
   * @return The used servlet context path. Never <code>null</code>.
   */
  @Nonnull
  @Nonempty
  public final String getServletContextPath ()
  {
    return m_sServletContextPath;
  }

  @Override
  @OverridingMethodsMustInvokeSuper
  public void before ()
  {
    m_bOldDAOSilentMode = AbstractDAO.setSilentMode (true);
    m_bOldWebFileIOSilentMode = WebFileIO.setSilentMode (true);
    WebFileIO.initPaths (m_aDataPath, m_sServletContextPath, false);
  }

  @Override
  @OverridingMethodsMustInvokeSuper
  public void after ()
  {
    WebFileIO.resetPaths ();
    WebFileIO.setSilentMode (m_bOldWebFileIOSilentMode);
    AbstractDAO.setSilentMode (m_bOldDAOSilentMode);
  }
}
