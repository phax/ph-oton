/**
 * Copyright (C) 2014-2019 Philip Helger (www.helger.com)
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
package com.helger.photon.tinymce4.type;

import javax.annotation.Nonnull;
import javax.annotation.concurrent.Immutable;

import com.helger.commons.ValueEnforcer;
import com.helger.commons.annotation.Nonempty;
import com.helger.commons.hashcode.HashCodeGenerator;
import com.helger.commons.string.ToStringGenerator;
import com.helger.commons.url.ISimpleURL;

/**
 * This class represents a single TinyMCE4 external plugin
 *
 * @author Philip Helger
 */
@Immutable
public class TinyMCE4ExternalPlugin
{
  private final String m_sPluginName;
  private final ISimpleURL m_aPluginURL;

  /**
   * Constructor
   *
   * @param sPluginName
   *        Name of the plugin. May neither be <code>null</code> nor empty.
   * @param aPluginURL
   *        URL of the plugin. May not be <code>null</code>.
   */
  public TinyMCE4ExternalPlugin (@Nonnull @Nonempty final String sPluginName, @Nonnull final ISimpleURL aPluginURL)
  {
    ValueEnforcer.notEmpty (sPluginName, "PluginName");
    ValueEnforcer.notNull (aPluginURL, "PluginURL");
    m_sPluginName = sPluginName;
    m_aPluginURL = aPluginURL;
  }

  @Nonnull
  @Nonempty
  public String getPluginName ()
  {
    return m_sPluginName;
  }

  @Nonnull
  public ISimpleURL getPluginURL ()
  {
    return m_aPluginURL;
  }

  @Override
  public boolean equals (final Object o)
  {
    if (o == this)
      return true;
    if (o == null || !getClass ().equals (o.getClass ()))
      return false;
    final TinyMCE4ExternalPlugin rhs = (TinyMCE4ExternalPlugin) o;
    return m_sPluginName.equals (rhs.m_sPluginName) && m_aPluginURL.equals (rhs.m_aPluginURL);
  }

  @Override
  public int hashCode ()
  {
    return new HashCodeGenerator (this).append (m_sPluginName).append (m_aPluginURL).getHashCode ();
  }

  @Override
  public String toString ()
  {
    return new ToStringGenerator (this).append ("pluginName", m_sPluginName)
                                       .append ("pluginURL", m_aPluginURL)
                                       .getToString ();
  }
}
