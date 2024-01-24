/*
 * Copyright (C) 2018-2024 Philip Helger (www.helger.com)
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
package com.helger.photon.bootstrap4.stub;

import java.util.concurrent.atomic.AtomicBoolean;

import javax.annotation.Nonnull;

import com.helger.photon.bootstrap4.stub.init.PhotonStubInitializer;

import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;

/**
 * This class triggers some default configuration to run ph-oton applications
 * more easy.
 *
 * @author Philip Helger
 */
public final class PhotonStubServletContextListener implements ServletContextListener
{
  private static final AtomicBoolean INITIALIZED = new AtomicBoolean (false);

  public static boolean isInitialized ()
  {
    return INITIALIZED.get ();
  }

  public void contextInitialized (@Nonnull final ServletContextEvent aSCE)
  {
    if (INITIALIZED.compareAndSet (false, true))
    {
      PhotonStubInitializer.onContextInitialized ();
      PhotonStubInitializer.registerDefaultResources ();
    }
  }

  public void contextDestroyed (@Nonnull final ServletContextEvent aSCE)
  {}
}
