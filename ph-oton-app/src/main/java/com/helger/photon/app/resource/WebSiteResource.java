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
package com.helger.photon.app.resource;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.concurrent.Immutable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.helger.commons.ValueEnforcer;
import com.helger.commons.annotation.Nonempty;
import com.helger.commons.annotation.ReturnsMutableCopy;
import com.helger.commons.collection.ArrayHelper;
import com.helger.commons.hashcode.HashCodeGenerator;
import com.helger.commons.hashcode.IHashCodeGenerator;
import com.helger.commons.io.IHasInputStream;
import com.helger.commons.io.file.FilenameHelper;
import com.helger.commons.io.resource.IReadableResource;
import com.helger.commons.io.resource.URLResource;
import com.helger.commons.io.stream.StreamHelper;
import com.helger.commons.string.StringHelper;
import com.helger.commons.string.ToStringGenerator;
import com.helger.commons.url.ISimpleURL;
import com.helger.css.ECSSVersion;
import com.helger.css.decl.CascadingStyleSheet;
import com.helger.css.decl.visit.AbstractModifyingCSSUrlVisitor;
import com.helger.css.decl.visit.CSSVisitor;
import com.helger.css.reader.CSSReader;
import com.helger.css.writer.CSSWriter;
import com.helger.photon.app.PhotonAppSettings;
import com.helger.photon.app.url.LinkHelper;
import com.helger.security.messagedigest.EMessageDigestAlgorithm;
import com.helger.security.messagedigest.MessageDigestValue;
import com.helger.web.scope.IRequestWebScopeWithoutResponse;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;

/**
 * A single web site resource. This class is only used internally in
 * {@link WebSiteResourceCache}.
 *
 * @author Philip Helger
 */
@Immutable
@SuppressFBWarnings ("JCIP_FIELD_ISNT_FINAL_IN_IMMUTABLE_CLASS")
public class WebSiteResource
{
  public static final Charset DEFAULT_CHARSET = StandardCharsets.UTF_8;

  private static final Logger LOGGER = LoggerFactory.getLogger (WebSiteResource.class);

  private final EWebSiteResourceType m_eResourceType;
  private final String m_sPath;
  private final Charset m_aCharset;
  // Status vars
  private final IReadableResource m_aResource;
  private final boolean m_bResourceExists;
  private final byte [] m_aContentHash;
  private final String m_sContentHash;
  private int m_nHashCode = IHashCodeGenerator.ILLEGAL_HASHCODE;

  public WebSiteResource (@Nonnull final EWebSiteResourceType eResourceType,
                          @Nonnull @Nonempty final String sPath,
                          @Nonnull final Charset aCharset)
  {
    m_eResourceType = ValueEnforcer.notNull (eResourceType, "ResourceType");
    m_sPath = ValueEnforcer.notEmpty (sPath, "Path");
    m_aCharset = ValueEnforcer.notNull (aCharset, "Charset");

    m_aResource = PhotonAppSettings.getURIToURLConverter ().getAsResource (sPath);
    m_bResourceExists = m_aResource.exists ();

    // No hash for external resources
    if (m_bResourceExists && !(m_aResource instanceof URLResource))
    {
      byte [] aDigestBytes = ArrayHelper.EMPTY_BYTE_ARRAY;
      try
      {
        // In some cases "getInputStream" fails even though the file exists!
        final InputStream aIS = m_aResource.getInputStream ();
        if (aIS != null)
        {
          aDigestBytes = MessageDigestValue.create (aIS, EMessageDigestAlgorithm.SHA_512).bytes ();
        }
      }
      catch (final IOException | NullPointerException ex)
      {
        LOGGER.error ("Failed to create message digest of " + m_aResource.getPath (), ex);
      }
      m_aContentHash = aDigestBytes;
      m_sContentHash = StringHelper.getHexEncoded (aDigestBytes);
    }
    else
    {
      // No hash value
      m_aContentHash = ArrayHelper.EMPTY_BYTE_ARRAY;
      m_sContentHash = "";
    }
  }

  @Nonnull
  public EWebSiteResourceType getResourceType ()
  {
    return m_eResourceType;
  }

  @Nonnull
  @Nonempty
  public String getPath ()
  {
    return m_sPath;
  }

  @Nonnull
  public IReadableResource getResource ()
  {
    return m_aResource;
  }

  @Nonnull
  public Charset getCharset ()
  {
    return m_aCharset;
  }

  /**
   * Unify all paths in a CSS relative to the passed base path.
   *
   * @param aISP
   *        Input stream provider.
   * @param sBasePath
   *        The base path, where the source CSS is read from.
   * @param bRegular
   *        <code>true</code> for normal output, <code>false</code> for minified
   *        output.
   * @return The modified String.
   */
  @Nonnull
  private String _readAndParseCSS (@Nonnull final IHasInputStream aISP,
                                   @Nonnull @Nonempty final String sBasePath,
                                   final boolean bRegular)
  {
    final CascadingStyleSheet aCSS = CSSReader.readFromStream (aISP, m_aCharset, ECSSVersion.CSS30);
    if (aCSS == null)
    {
      LOGGER.error ("Failed to parse CSS. Returning 'as-is'");
      return StreamHelper.getAllBytesAsString (aISP, m_aCharset);
    }
    CSSVisitor.visitCSSUrl (aCSS, new AbstractModifyingCSSUrlVisitor ()
    {
      @Override
      protected String getModifiedURI (@Nonnull final String sURI)
      {
        if (LinkHelper.hasKnownProtocol (sURI))
        {
          // If e.g. an external resource is includes.
          // Example: https://fonts.googleapis.com/css
          return sURI;
        }
        return FilenameHelper.getCleanConcatenatedUrlPath (sBasePath, sURI);
      }
    });

    // Write again after modification
    return new CSSWriter (ECSSVersion.CSS30, !bRegular).setWriteHeaderText (false)
                                                       .setWriteFooterText (false)
                                                       .getCSSAsString (aCSS);
  }

  @Nullable
  public String getContent (final boolean bRegular)
  {
    if (!m_bResourceExists)
      return null;

    switch (m_eResourceType)
    {
      case JS:
        // Read JS as UTF-8 and return it as one, global block
        // Don't prefix with "(function(){" and don't suffix with "})();" as
        // this has undesired side effects such that global functions are not
        // available etc.
        // In case of an error, fix the relevant JS file instead.
        return StreamHelper.getAllBytesAsString (m_aResource, m_aCharset);
      case CSS:
        // Remove the filename from the path
        // Not using a requestScope is okay here, because we don't want to link
        // anything right now
        final String sBasePath = FilenameHelper.getPath (PhotonAppSettings.getURIToURLConverter ()
                                                                          .getAsURL (m_sPath)
                                                                          .getAsStringWithEncodedParameters ());
        return _readAndParseCSS (m_aResource, sBasePath, bRegular);
      default:
        throw new IllegalStateException ("Unsupported resource type " + m_eResourceType);
    }
  }

  public boolean isExisting ()
  {
    return m_bResourceExists;
  }

  @Nullable
  public String getAsURLString ()
  {
    final URL aURL = m_aResource.getAsURL ();
    if (aURL == null)
      return null;
    return aURL.toExternalForm ();
  }

  @Nonnull
  @ReturnsMutableCopy
  public byte [] getContentHashBytes ()
  {
    return ArrayHelper.getCopy (m_aContentHash);
  }

  @Nonnull
  public String getContentHashAsString ()
  {
    return m_sContentHash;
  }

  @Nonnull
  public ISimpleURL getAsURL (@Nonnull final IRequestWebScopeWithoutResponse aRequestScope)
  {
    // Append the version number to work around caching issues
    // Cut it down to the first 16 bytes, because the SHA512 hash is 128 bytes
    // long
    final String sVersion = m_sContentHash.length () >= 16 ? m_sContentHash.substring (0, 16) : "";
    return PhotonAppSettings.getURIToURLConverter ()
                            .getAsURL (aRequestScope, m_sPath)
                            .addIf ("version", sVersion, StringHelper::hasText);
  }

  @Override
  public boolean equals (final Object o)
  {
    if (o == this)
      return true;
    if (o == null || !getClass ().equals (o.getClass ()))
      return false;
    final WebSiteResource rhs = (WebSiteResource) o;
    return m_eResourceType.equals (rhs.m_eResourceType) &&
           m_sPath.equals (rhs.m_sPath) &&
           m_aCharset.equals (rhs.m_aCharset);
  }

  @Override
  public int hashCode ()
  {
    // Cache hashCode :)
    int ret = m_nHashCode;
    if (ret == IHashCodeGenerator.ILLEGAL_HASHCODE)
      ret = m_nHashCode = new HashCodeGenerator (this).append (m_eResourceType)
                                                      .append (m_sPath)
                                                      .append (m_aCharset)
                                                      .getHashCode ();
    return ret;
  }

  @Override
  public String toString ()
  {
    return new ToStringGenerator (this).append ("ResourceType", m_eResourceType)
                                       .append ("Path", m_sPath)
                                       .append ("Charset", m_aCharset)
                                       .getToString ();
  }
}
