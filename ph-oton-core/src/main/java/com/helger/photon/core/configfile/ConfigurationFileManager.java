/*
 * Copyright (C) 2014-2022 Philip Helger (www.helger.com)
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
package com.helger.photon.core.configfile;

import javax.annotation.Nonnull;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.helger.commons.ValueEnforcer;
import com.helger.commons.annotation.ReturnsMutableCopy;
import com.helger.commons.annotation.UsedViaReflection;
import com.helger.commons.collection.impl.CommonsLinkedHashMap;
import com.helger.commons.collection.impl.ICommonsList;
import com.helger.commons.collection.impl.ICommonsOrderedMap;
import com.helger.commons.string.ToStringGenerator;
import com.helger.config.IConfig;
import com.helger.config.source.res.ConfigurationSourceJson;
import com.helger.config.source.res.ConfigurationSourceProperties;
import com.helger.config.source.res.IConfigurationSourceResource;
import com.helger.scope.singleton.AbstractGlobalSingleton;

/**
 * A non-persisting manager for {@link ConfigurationFile} objects. Needs to be
 * initialized manually at startup.
 *
 * @author Philip Helger
 */
public final class ConfigurationFileManager extends AbstractGlobalSingleton
{
  private static final Logger LOGGER = LoggerFactory.getLogger (ConfigurationFileManager.class);

  private final ICommonsOrderedMap <String, ConfigurationFile> m_aMap = new CommonsLinkedHashMap <> ();

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

  /**
   * Register all configuration sources that are based on resources.
   *
   * @param aConfig
   *        The configuration to use. May not be <code>null</code>.
   * @since 8.2.7
   */
  public void registerAll (@Nonnull final IConfig aConfig)
  {
    ValueEnforcer.notNull (aConfig, "Config");

    aConfig.forEachConfigurationValueProvider ( (cvp, prio) -> {
      if (cvp instanceof IConfigurationSourceResource)
      {
        final IConfigurationSourceResource aCVP = (IConfigurationSourceResource) cvp;

        // Find syntax
        final EConfigurationFileSyntax eSHL;
        if (aCVP instanceof ConfigurationSourceJson)
          eSHL = EConfigurationFileSyntax.JSON;
        else
          if (aCVP instanceof ConfigurationSourceProperties)
            eSHL = EConfigurationFileSyntax.PROPERTIES;
          else
            eSHL = EConfigurationFileSyntax.NONE;

        // Register
        final ConfigurationFile aCF = new ConfigurationFile (aCVP.getResource ()).setSyntaxHighlightLanguage (eSHL);
        if (!m_aMap.containsKey (aCF.getID ()))
          registerConfigurationFile (aCF);
        else
          if (LOGGER.isDebugEnabled ())
            LOGGER.debug ("Ignoring Configuration file '" + aCVP.getResource ().getPath () + "' because it is already registered.");
      }
    });
  }

  @Nonnull
  @ReturnsMutableCopy
  public ICommonsList <ConfigurationFile> getAllConfigurationFiles ()
  {
    return m_aMap.copyOfValues ();
  }

  @Override
  public String toString ()
  {
    return ToStringGenerator.getDerived (super.toString ()).append ("Map", m_aMap).getToString ();
  }
}
