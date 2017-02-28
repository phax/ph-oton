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
package com.helger.photon.basic.buildinfo;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.time.LocalDateTime;
import java.util.Map;

import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.helger.commons.ValueEnforcer;
import com.helger.commons.annotation.ReturnsMutableCopy;
import com.helger.commons.collection.ext.CommonsHashMap;
import com.helger.commons.collection.ext.ICommonsMap;
import com.helger.commons.datetime.PDTFactory;
import com.helger.commons.io.resource.IReadableResource;
import com.helger.commons.string.StringParser;
import com.helger.commons.string.ToStringGenerator;
import com.helger.xml.microdom.util.XMLMapHandler;

/**
 * This class allows you to read the buildinfo.xml files generated by the
 * buildinfo-maven-plugin.
 *
 * @author Philip Helger
 */
public final class BuildInfo
{
  /**
   * The default filename as stored in the JAR files.
   */
  public static final String BUILDINFO_FILENAME = "META-INF/buildinfo.xml";

  /**
   * Indicate that certain fields are only available from V2
   *
   * @author Philip Helger
   */
  @Retention (RetentionPolicy.SOURCE)
  @Documented
  public @interface SinceBuildInfoV2
  {}

  private static final String PREFIX_SYSPROPERTY = "systemproperty.";
  private static final String PREFIX_ENVVAR = "envvar.";

  private final ICommonsMap <String, String> m_aMap;
  private final int m_nVersion;
  private final ICommonsMap <String, String> m_aSysProperties = new CommonsHashMap<> ();
  private final ICommonsMap <String, String> m_aEnvVars = new CommonsHashMap<> ();

  public BuildInfo (@Nonnull final ICommonsMap <String, String> aMap)
  {
    m_aMap = ValueEnforcer.notNull (aMap, "Map");
    m_nVersion = getInt ("buildinfo.version");
    if (m_nVersion < 1)
      throw new IllegalArgumentException ("The passed map is not a buildinfo map!");

    // Extract all system properties and env vars
    for (final Map.Entry <String, String> aEntry : aMap.entrySet ())
    {
      final String sKey = aEntry.getKey ();
      if (sKey.startsWith (PREFIX_SYSPROPERTY))
        m_aSysProperties.put (sKey.substring (PREFIX_SYSPROPERTY.length ()), aEntry.getValue ());
      else
        if (sKey.startsWith (PREFIX_ENVVAR))
          m_aEnvVars.put (sKey.substring (PREFIX_ENVVAR.length ()), aEntry.getValue ());
    }
  }

  /**
   * @return The build info version number.
   */
  @Nonnegative
  public int getVersion ()
  {
    return m_nVersion;
  }

  /**
   * Get a string property.
   *
   * @param sKey
   *        The key to retrieve
   * @return <code>null</code> if the key does not exist
   */
  @Nullable
  public String getString (@Nullable final String sKey)
  {
    return m_aMap.get (sKey);
  }

  public long getLong (@Nullable final String sKey)
  {
    return StringParser.parseLong (getString (sKey), 0);
  }

  public int getInt (@Nullable final String sKey)
  {
    return StringParser.parseInt (getString (sKey), 0);
  }

  public boolean getBoolean (@Nullable final String sKey)
  {
    return StringParser.parseBool (getString (sKey));
  }

  @Nonnull
  public String getProjectGroupID ()
  {
    return getString ("project.groupid");
  }

  @Nonnull
  public String getProjectArtifactID ()
  {
    return getString ("project.artifactid");
  }

  @Nonnull
  public String getProjectVersion ()
  {
    return getString ("project.version");
  }

  @Nonnull
  public String getProjectName ()
  {
    return getString ("project.name");
  }

  @Nonnull
  public String getProjectPackaging ()
  {
    return getString ("project.packaging");
  }

  @Nonnull
  public String getParentProjectGroupID ()
  {
    return getString ("parentproject.groupid");
  }

  @Nonnull
  public String getParentProjectArtifactID ()
  {
    return getString ("parentproject.artifactid");
  }

  @Nonnull
  public String getParentProjectVersion ()
  {
    return getString ("parentproject.version");
  }

  @Nonnull
  public String getParentProjectName ()
  {
    return getString ("parentproject.name");
  }

  @Nonnegative
  public int getBuildPluginCount ()
  {
    return getInt ("build.plugin.count");
  }

  @Nonnull
  public String getBuildPluginGroupID (@Nonnegative final int nIndex)
  {
    return getString ("build.plugin." + nIndex + ".groupid");
  }

  @Nonnull
  public String getBuildPluginArtifactID (@Nonnegative final int nIndex)
  {
    return getString ("build.plugin." + nIndex + ".artifactid");
  }

  @Nonnull
  public String getBuildPluginVersion (@Nonnegative final int nIndex)
  {
    return getString ("build.plugin." + nIndex + ".version");
  }

  @Nonnull
  public String getBuildPluginConfiguration (@Nonnegative final int nIndex)
  {
    return getString ("build.plugin." + nIndex + ".configuration");
  }

  @SinceBuildInfoV2
  public String getBuildPluginKey (@Nonnegative final int nIndex)
  {
    return getString ("build.plugin." + nIndex + ".key");
  }

  @Nonnegative
  @SinceBuildInfoV2
  public int getDependencyCount ()
  {
    return getInt ("dependency.count");
  }

  @Nonnull
  @SinceBuildInfoV2
  public String getDependencyGroupID (@Nonnegative final int nIndex)
  {
    return getString ("dependency." + nIndex + ".groupid");
  }

  @Nonnull
  @SinceBuildInfoV2
  public String getDependencyArtifactID (@Nonnegative final int nIndex)
  {
    return getString ("dependency." + nIndex + ".artifactid");
  }

  @Nonnull
  @SinceBuildInfoV2
  public String getDependencyVersion (@Nonnegative final int nIndex)
  {
    return getString ("dependency." + nIndex + ".version");
  }

  @Nonnull
  @SinceBuildInfoV2
  public String getDependencyType (@Nonnegative final int nIndex)
  {
    return getString ("dependency." + nIndex + ".type");
  }

  @Nullable
  @SinceBuildInfoV2
  public String getDependencyClassifier (@Nonnegative final int nIndex)
  {
    return getString ("dependency." + nIndex + ".classifier");
  }

  @Nonnull
  @SinceBuildInfoV2
  public String getDependencyScope (@Nonnegative final int nIndex)
  {
    return getString ("dependency." + nIndex + ".scope");
  }

  @Nullable
  @SinceBuildInfoV2
  public String getDependencySystemPath (@Nonnegative final int nIndex)
  {
    return getString ("dependency." + nIndex + ".systempath");
  }

  @SinceBuildInfoV2
  public boolean isDependencyOptional (@Nonnegative final int nIndex)
  {
    return getBoolean ("dependency." + nIndex + ".optional");
  }

  /**
   * @param nIndex
   *        dependency index
   * @return <code><i>groupID</i>:<i>artifactid</i>:<i>type</i></code> - no
   *         version info!
   */
  @Nonnull
  @SinceBuildInfoV2
  public String getDependencyManagementKey (@Nonnegative final int nIndex)
  {
    return getString ("dependency." + nIndex + ".managementkey");
  }

  // TODO add exclusions

  @Nonnull
  public LocalDateTime getBuildDateTime ()
  {
    return PDTFactory.createLocalDateTime (getLong ("build.datetime.millis"));
  }

  @Nonnull
  @ReturnsMutableCopy
  public ICommonsMap <String, String> getAllSystemProperties ()
  {
    return m_aSysProperties.getClone ();
  }

  @Nonnull
  @ReturnsMutableCopy
  public ICommonsMap <String, String> getAllEnvVars ()
  {
    return m_aEnvVars.getClone ();
  }

  @Override
  public String toString ()
  {
    return new ToStringGenerator (this).append ("version", m_nVersion).append ("map", m_aMap).getToString ();
  }

  @Nullable
  public static BuildInfo createFromResource (@Nonnull final IReadableResource aRes)
  {
    final ICommonsMap <String, String> aMap = XMLMapHandler.readMap (aRes);
    if (aMap == null)
      return null;
    return new BuildInfo (aMap);
  }
}
