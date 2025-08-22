/*
 * Copyright (C) 2018-2025 Philip Helger (www.helger.com)
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
package com.helger.photon.bootstrap.demo.app;

import com.helger.annotation.style.UsedViaReflection;
import com.helger.base.debug.GlobalDebug;
import com.helger.config.ConfigFactory;
import com.helger.config.IConfig;
import com.helger.scope.singleton.AbstractGlobalSingleton;

import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;

/**
 * This class provides access to the settings as contained in the
 * <code>webapp.properties</code> file.
 *
 * @author Philip Helger
 */
public final class AppSettings extends AbstractGlobalSingleton
{
  @Deprecated (forRemoval = false)
  @UsedViaReflection
  private AppSettings ()
  {}

  @Nonnull
  public static IConfig getConfig ()
  {
    return ConfigFactory.getDefaultConfig ();
  }

  @Nullable
  public static String getGlobalDebug ()
  {
    return getConfig ().getAsString ("global.debug");
  }

  @Nullable
  public static String getGlobalProduction ()
  {
    return getConfig ().getAsString ("global.production");
  }

  @Nullable
  public static String getDataPath ()
  {
    return getConfig ().getAsString ("webapp.datapath");
  }

  public static boolean isCheckFileAccess ()
  {
    return getConfig ().getAsBoolean ("webapp.checkfileaccess", false);
  }

  public static boolean isTestVersion ()
  {
    return getConfig ().getAsBoolean ("webapp.testversion", GlobalDebug.isDebugMode ());
  }
}
