/**
 * Copyright (C) 2014-2015 Philip Helger (www.helger.com)
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
package com.helger.webbasics.resource;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.concurrent.Immutable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.helger.appbasics.app.io.WebFileIO;
import com.helger.commons.ValueEnforcer;
import com.helger.commons.annotations.Nonempty;
import com.helger.commons.charset.CCharset;
import com.helger.commons.collections.ArrayHelper;
import com.helger.commons.equals.EqualsUtils;
import com.helger.commons.hash.HashCodeGenerator;
import com.helger.commons.io.IInputStreamProvider;
import com.helger.commons.io.IReadableResource;
import com.helger.commons.io.file.FilenameHelper;
import com.helger.commons.io.resource.ClassPathResource;
import com.helger.commons.io.streams.StreamUtils;
import com.helger.commons.messagedigest.EMessageDigestAlgorithm;
import com.helger.commons.messagedigest.MessageDigestGeneratorHelper;
import com.helger.commons.string.StringHelper;
import com.helger.commons.string.ToStringGenerator;
import com.helger.commons.url.ISimpleURL;
import com.helger.commons.url.SMap;
import com.helger.css.ECSSVersion;
import com.helger.css.decl.CascadingStyleSheet;
import com.helger.css.decl.visit.AbstractModifyingCSSUrlVisitor;
import com.helger.css.decl.visit.CSSVisitor;
import com.helger.css.reader.CSSReader;
import com.helger.css.writer.CSSWriter;
import com.helger.web.scopes.domain.IRequestWebScopeWithoutResponse;
import com.helger.webbasics.app.LinkUtils;
import com.helger.webbasics.app.html.WebHTMLCreator;

/**
 * A single web site resource. This class is only used internally in
 * {@link WebSiteResourceCache}.
 *
 * @author Philip Helger
 */
@Immutable
public class WebSiteResource
{
  private static final Logger s_aLogger = LoggerFactory.getLogger (WebSiteResource.class);

  private final EWebSiteResourceType m_eResourceType;
  private final String m_sPath;
  // Status vars
  private final IReadableResource m_aResource;
  private final boolean m_bResourceExists;
  private final byte [] m_aContentHash;
  private final String m_sContentHash;
  private Integer m_aHashCode;

  public WebSiteResource (@Nonnull final EWebSiteResourceType eResourceType, @Nonnull @Nonempty final String sPath)
  {
    m_eResourceType = ValueEnforcer.notNull (eResourceType, "ResourceType");
    m_sPath = ValueEnforcer.notEmpty (sPath, "Path");

    IReadableResource aRes = new ClassPathResource (m_sPath);
    if (aRes.exists ())
    {
      m_bResourceExists = true;
    }
    else
    {
      // Required for project specific files (like app.js) when running in
      // Jetty
      aRes = WebFileIO.getServletContextIO ().getResource (m_sPath);
      m_bResourceExists = aRes.exists ();
    }

    m_aResource = aRes;

    if (m_bResourceExists)
    {
      m_aContentHash = MessageDigestGeneratorHelper.getDigestFromInputStream (aRes.getInputStream (),
                                                                              EMessageDigestAlgorithm.SHA_512);
      m_sContentHash = StringHelper.getHexEncoded (m_aContentHash);
    }
    else
    {
      m_aContentHash = new byte [0];
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
  private static String _readAndParseCSS (@Nonnull final IInputStreamProvider aISP,
                                          @Nonnull @Nonempty final String sBasePath,
                                          final boolean bRegular)
  {
    final CascadingStyleSheet aCSS = CSSReader.readFromStream (aISP, CCharset.CHARSET_UTF_8_OBJ, ECSSVersion.CSS30);
    if (aCSS == null)
    {
      s_aLogger.error ("Failed to parse CSS. Returning 'as-is'");
      return StreamUtils.getAllBytesAsString (aISP, CCharset.CHARSET_UTF_8_OBJ);
    }
    CSSVisitor.visitCSSUrl (aCSS, new AbstractModifyingCSSUrlVisitor ()
    {
      @Override
      protected String getModifiedURI (@Nonnull final String sURI)
      {
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
        return StreamUtils.getAllBytesAsString (m_aResource.getInputStream (), CCharset.CHARSET_UTF_8_OBJ);
      case CSS:
        // Remove the filename from the path
        // Not using a requestScope is okay here, because we don't want to link
        // anything right now
        final String sBasePath = FilenameHelper.getPath (WebHTMLCreator.getURIToURLConverter ()
                                                                       .getAsURL (m_sPath)
                                                                       .getAsString ());
        return _readAndParseCSS (m_aResource, sBasePath, bRegular);
      default:
        throw new IllegalStateException ("Unsupported resource type!");
    }
  }

  public boolean isExisting ()
  {
    return m_bResourceExists;
  }

  @Nonnull
  @Nonempty
  public String getAsURLString ()
  {
    return m_aResource.getAsURL ().toExternalForm ();
  }

  @Nonnull
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
    if (m_sPath.startsWith ("/"))
    {
      // Project resource
      return LinkUtils.getURLWithContext (aRequestScope, m_sPath, new SMap ().add ("version", m_sContentHash));
    }

    // Classpath resource - won't change
    return LinkUtils.getStreamURL (aRequestScope, m_sPath);
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
           EqualsUtils.equals (m_aContentHash, rhs.m_aContentHash);
  }

  @Override
  public int hashCode ()
  {
    // Cache hashCode :)
    if (m_aHashCode == null)
      m_aHashCode = new HashCodeGenerator (this).append (m_eResourceType)
                                                .append (m_sPath)
                                                .append (m_aContentHash)
                                                .getHashCodeObj ();
    return m_aHashCode.intValue ();
  }

  @Override
  public String toString ()
  {
    return new ToStringGenerator (this).append ("type", m_eResourceType).append ("path", m_sPath).toString ();
  }
}
