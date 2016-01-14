/**
 * Copyright (C) 2014-2016 Philip Helger (www.helger.com)
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
package com.helger.photon.bootstrap3.pages.sysinfo;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Nonnull;

import com.helger.commons.ValueEnforcer;
import com.helger.commons.annotation.ReturnsMutableCopy;
import com.helger.commons.annotation.UsedViaReflection;
import com.helger.commons.collection.CollectionHelper;
import com.helger.commons.scope.singleton.AbstractGlobalSingleton;

public final class ConfigurationFileManager extends AbstractGlobalSingleton
{
  private final Map <String, ConfigurationFile> m_aMap = new LinkedHashMap <String, ConfigurationFile> ();

  @Deprecated
  @UsedViaReflection
  public ConfigurationFileManager ()
  {}

  @Nonnull
  public static ConfigurationFileManager getInstance ()
  {
    return getGlobalSingleton (ConfigurationFileManager.class);
  }

  public void registerConfigurationFile (@Nonnull final ConfigurationFile aConfigurationFile)
  {
    ValueEnforcer.notNull (aConfigurationFile, "ConfigurationFile");

    final String sID = aConfigurationFile.getID ();
    if (m_aMap.containsKey (sID))
      throw new IllegalArgumentException ("A configuration file " + sID + " is already registered!");
    m_aMap.put (sID, aConfigurationFile);
  }

  @Nonnull
  @ReturnsMutableCopy
  public List <ConfigurationFile> getAllConfigurationFiles ()
  {
    return CollectionHelper.newList (m_aMap.values ());
  }
}
