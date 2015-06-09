package com.helger.photon.bootstrap3.pages.sysinfo;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Nonnull;

import com.helger.commons.ValueEnforcer;
import com.helger.commons.annotations.ReturnsMutableCopy;
import com.helger.commons.annotations.UsedViaReflection;
import com.helger.commons.collections.CollectionHelper;
import com.helger.commons.scopes.singleton.GlobalSingleton;

public final class ConfigurationFileManager extends GlobalSingleton
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
