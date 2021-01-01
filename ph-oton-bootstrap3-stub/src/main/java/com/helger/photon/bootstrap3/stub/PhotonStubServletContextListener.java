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
package com.helger.photon.bootstrap3.stub;

import java.util.concurrent.atomic.AtomicBoolean;

import javax.annotation.Nonnull;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import com.helger.photon.bootstrap3.stub.init.PhotonStubInitializer;

/**
 * This class triggers some default configuration to run ph-oton applications
 * more easy.
 *
 * @author Philip Helger
 */
public final class PhotonStubServletContextListener implements ServletContextListener
{
  private static final AtomicBoolean s_aInitialized = new AtomicBoolean (false);

  public static boolean isInitialized ()
  {
    return s_aInitialized.get ();
  }

  public void contextInitialized (@Nonnull final ServletContextEvent aSCE)
  {
    if (s_aInitialized.compareAndSet (false, true))
    {
      PhotonStubInitializer.onContextInitialized ();
      PhotonStubInitializer.registerDefaultResources ();
    }
  }

  public void contextDestroyed (@Nonnull final ServletContextEvent aSCE)
  {}
}
