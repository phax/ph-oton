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
package com.helger.photon.basic.mock;

import java.io.File;

import javax.annotation.Nonnull;

import com.helger.commons.ValueEnforcer;
import com.helger.commons.annotation.Nonempty;
import com.helger.photon.basic.PhotonBasic;
import com.helger.scope.mock.ScopeTestRule;

/**
 * Non-web scope aware test rule, with a defined storage root directory
 *
 * @author Philip Helger
 */
public class PhotonBasicTestRule extends ScopeTestRule
{
  private final File m_aDataPath;
  private final String m_sServletContextPath;

  /**
   * Ctor using the default storage path from {@link ScopeTestRule}
   */
  public PhotonBasicTestRule ()
  {
    this (ScopeTestRule.STORAGE_PATH, ScopeTestRule.STORAGE_PATH.getAbsolutePath ());
  }

  /**
   * Ctor with an arbitrary path
   *
   * @param aDataPath
   *        The data path to be used. May not be <code>null</code>.
   * @param sServletContextPath
   *        The servlet context path to be used. May not be <code>null</code>.
   */
  public PhotonBasicTestRule (@Nonnull final File aDataPath, @Nonnull @Nonempty final String sServletContextPath)
  {
    ValueEnforcer.notNull (aDataPath, "DataPath");
    ValueEnforcer.notEmpty (sServletContextPath, "ServletContextPath");
    m_aDataPath = aDataPath.getAbsoluteFile ();
    m_sServletContextPath = sServletContextPath;
  }

  /**
   * @return The used data path. Never <code>null</code>.
   */
  @Nonnull
  public File getDataPath ()
  {
    return m_aDataPath;
  }

  /**
   * @return The used servlet context path. Never <code>null</code>.
   */
  @Nonnull
  @Nonempty
  public String getServletContextPath ()
  {
    return m_sServletContextPath;
  }

  @Override
  public void before ()
  {
    super.before ();
    PhotonBasicTestInit.init (m_aDataPath, m_sServletContextPath);
  }

  @Override
  public void after ()
  {
    PhotonBasic.shutDown ();
    super.after ();
  }
}
