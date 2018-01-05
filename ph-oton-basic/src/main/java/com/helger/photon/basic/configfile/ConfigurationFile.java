/**
 * Copyright (C) 2014-2018 Philip Helger (www.helger.com)
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
package com.helger.photon.basic.configfile;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.concurrent.NotThreadSafe;

import com.helger.commons.ValueEnforcer;
import com.helger.commons.id.IHasID;
import com.helger.commons.io.resource.IReadableResource;
import com.helger.commons.io.stream.StreamHelper;
import com.helger.commons.string.ToStringGenerator;
import com.helger.commons.text.IHasDescription;

@NotThreadSafe
public class ConfigurationFile implements IHasID <String>, IHasDescription
{
  private final IReadableResource m_aRes;
  private String m_sDescription;
  private Charset m_aDefaultCharset = StandardCharsets.UTF_8;
  private EConfigurationFileSyntax m_eSyntaxHighlightLanguage = EConfigurationFileSyntax.NONE;

  /**
   * Constructor
   *
   * @param aRes
   *        The resource that points to the relevant configuration file.
   */
  public ConfigurationFile (@Nonnull final IReadableResource aRes)
  {
    m_aRes = ValueEnforcer.notNull (aRes, "Resource");
  }

  @Nonnull
  public String getID ()
  {
    return m_aRes.getPath ();
  }

  @Nonnull
  public IReadableResource getResource ()
  {
    return m_aRes;
  }

  public boolean exists ()
  {
    return m_aRes.exists ();
  }

  @Nullable
  public String getContentAsString ()
  {
    return getContentAsString (m_aDefaultCharset);
  }

  @Nullable
  public String getContentAsString (@Nonnull final Charset aCharset)
  {
    return StreamHelper.getAllBytesAsString (m_aRes, aCharset);
  }

  /**
   * @return The optional description of this configuration file. May be
   *         <code>null</code>.
   */
  @Nullable
  public String getDescription ()
  {
    return m_sDescription;
  }

  /**
   * Set the description to use.
   *
   * @param sDescription
   *        The description for this configuration file. May be
   *        <code>null</code>.
   * @return this
   */
  @Nonnull
  public ConfigurationFile setDescription (@Nullable final String sDescription)
  {
    m_sDescription = sDescription;
    return this;
  }

  @Nonnull
  public Charset getDefaultCharset ()
  {
    return m_aDefaultCharset;
  }

  @Nonnull
  public ConfigurationFile setDefaultCharset (@Nonnull final Charset aDefaultCharset)
  {
    m_aDefaultCharset = ValueEnforcer.notNull (aDefaultCharset, "DefaultCharset");
    return this;
  }

  @Nonnull
  public EConfigurationFileSyntax getSyntaxHighlightLanguage ()
  {
    return m_eSyntaxHighlightLanguage;
  }

  @Nonnull
  public ConfigurationFile setSyntaxHighlightLanguage (@Nonnull final EConfigurationFileSyntax eSyntaxHighlightLanguage)
  {
    m_eSyntaxHighlightLanguage = ValueEnforcer.notNull (eSyntaxHighlightLanguage, "SyntaxHighlightLanguage");
    return this;
  }

  @Override
  public String toString ()
  {
    return new ToStringGenerator (this).append ("Resource", m_aRes)
                                       .append ("Description", m_sDescription)
                                       .append ("DefaultCharset", m_aDefaultCharset)
                                       .append ("Syntax", m_eSyntaxHighlightLanguage)
                                       .getToString ();
  }
}
