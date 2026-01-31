/*
 * Copyright (C) 2014-2026 Philip Helger (www.helger.com)
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

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.Nullable;

import com.helger.annotation.concurrent.NotThreadSafe;
import com.helger.base.enforce.ValueEnforcer;
import com.helger.base.id.IHasID;
import com.helger.base.io.stream.StreamHelper;
import com.helger.base.tostring.ToStringGenerator;
import com.helger.io.resource.IReadableResource;
import com.helger.text.IHasDescription;

@NotThreadSafe
public class ConfigurationFile implements IHasID <String>, IHasDescription
{
  private final IReadableResource m_aRes;
  private final String m_sID;
  private String m_sDescription;
  private Charset m_aDefaultCharset = StandardCharsets.UTF_8;
  private EConfigurationFileSyntax m_eSyntaxHighlightLanguage = EConfigurationFileSyntax.NONE;

  @NonNull
  private static String _unify (@NonNull final String s)
  {
    final StringBuilder aSB = new StringBuilder (s.length ());
    for (final char c : s.toCharArray ())
      if (Character.isLetterOrDigit (c))
        aSB.append (c);
      else
        aSB.append ('_');
    return aSB.toString ();
  }

  /**
   * Constructor
   *
   * @param aRes
   *        The resource that points to the relevant configuration file.
   */
  public ConfigurationFile (@NonNull final IReadableResource aRes)
  {
    m_aRes = ValueEnforcer.notNull (aRes, "Resource");
    m_sID = _unify (m_aRes.getPath ());
  }

  @NonNull
  public final String getID ()
  {
    return m_sID;
  }

  @NonNull
  public final IReadableResource getResource ()
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
  public String getContentAsString (@NonNull final Charset aCharset)
  {
    return StreamHelper.getAllBytesAsString (m_aRes, aCharset);
  }

  /**
   * @return The optional description of this configuration file. May be
   *         <code>null</code>.
   */
  @Nullable
  public final String getDescription ()
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
  @NonNull
  public final ConfigurationFile setDescription (@Nullable final String sDescription)
  {
    m_sDescription = sDescription;
    return this;
  }

  @NonNull
  public final Charset getDefaultCharset ()
  {
    return m_aDefaultCharset;
  }

  @NonNull
  public final ConfigurationFile setDefaultCharset (@NonNull final Charset aDefaultCharset)
  {
    m_aDefaultCharset = ValueEnforcer.notNull (aDefaultCharset, "DefaultCharset");
    return this;
  }

  @NonNull
  public final EConfigurationFileSyntax getSyntaxHighlightLanguage ()
  {
    return m_eSyntaxHighlightLanguage;
  }

  @NonNull
  public final ConfigurationFile setSyntaxHighlightLanguage (@NonNull final EConfigurationFileSyntax eSyntaxHighlightLanguage)
  {
    m_eSyntaxHighlightLanguage = ValueEnforcer.notNull (eSyntaxHighlightLanguage, "SyntaxHighlightLanguage");
    return this;
  }

  @Override
  public String toString ()
  {
    return new ToStringGenerator (this).append ("Resource", m_aRes)
                                       .append ("ID", m_sID)
                                       .append ("Description", m_sDescription)
                                       .append ("DefaultCharset", m_aDefaultCharset)
                                       .append ("Syntax", m_eSyntaxHighlightLanguage)
                                       .getToString ();
  }
}
