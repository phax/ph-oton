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
package com.helger.photon.core.resource;

import java.io.InputStream;
import java.io.Writer;
import java.nio.charset.Charset;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import org.joda.time.LocalDateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.helger.commons.ValueEnforcer;
import com.helger.commons.annotation.Nonempty;
import com.helger.commons.charset.CCharset;
import com.helger.commons.io.EAppend;
import com.helger.commons.io.IHasInputStream;
import com.helger.commons.io.resource.FileSystemResource;
import com.helger.commons.io.stream.StreamHelper;
import com.helger.commons.mime.IMimeType;
import com.helger.commons.string.ToStringGenerator;
import com.helger.commons.url.ISimpleURL;
import com.helger.datetime.PDTFactory;
import com.helger.html.hc.IHCNode;
import com.helger.photon.basic.app.io.WebFileIO;
import com.helger.photon.core.url.LinkHelper;
import com.helger.web.scope.IRequestWebScopeWithoutResponse;

/**
 * This class combines a {@link WebSiteResourceBundle} with an internal ID and a
 * creation date time. It is so to say the serialized version of a
 * {@link WebSiteResourceBundle}.
 *
 * @author Philip Helger
 */
public class WebSiteResourceBundleSerialized implements IHasInputStream
{
  public static final Charset CHARSET_TO_USE = CCharset.CHARSET_UTF_8_OBJ;

  private static final Logger s_aLogger = LoggerFactory.getLogger (WebSiteResourceBundleSerialized.class);

  private final String m_sBundleID;
  private final WebSiteResourceBundle m_aBundle;
  private final LocalDateTime m_aCreationDT;

  public WebSiteResourceBundleSerialized (@Nonnull @Nonempty final String sBundleID,
                                          @Nonnull final WebSiteResourceBundle aBundle,
                                          final boolean bRegular)
  {
    this (sBundleID, aBundle, PDTFactory.getCurrentLocalDateTime ());
    // Always serialize new bundles
    _ensureSerialized (bRegular);
  }

  WebSiteResourceBundleSerialized (@Nonnull @Nonempty final String sBundleID,
                                   @Nonnull final WebSiteResourceBundle aBundle,
                                   @Nonnull final LocalDateTime aCreationDT)
  {
    m_sBundleID = ValueEnforcer.notEmpty (sBundleID, "BundleID");
    m_aBundle = ValueEnforcer.notNull (aBundle, "Bundle");
    m_aCreationDT = ValueEnforcer.notNull (aCreationDT, "CreationDT");
  }

  private void _ensureSerialized (final boolean bRegular)
  {
    final FileSystemResource aTargetRes = WebFileIO.getDataIO ().getResource (
                                                                              WebSiteResourceBundleManager.RESOURCE_BUNDLE_PATH +
                                                                              m_sBundleID);
    if (!aTargetRes.exists ())
    {
      // persist file by merging all items
      try (final Writer aWriter = StreamHelper.getBuffered (aTargetRes.getWriter (CCharset.CHARSET_UTF_8_OBJ,
                                                                                  EAppend.TRUNCATE)))
      {
        // Write all used files into the result file (at least for now)
        for (final WebSiteResource aRes : m_aBundle.getAllResources ())
        {
          // This type of comment works for CSS and JS!
          final String sMetaInfo = "/* " + aRes.getPath () + " - " + aRes.getContentHashAsString () + " */\n";
          aWriter.write (sMetaInfo);
        }

        // Write all resources themselves
        for (final WebSiteResource aRes : m_aBundle.getAllResources ())
        {
          final String sContent = aRes.getContent (bRegular);
          if (sContent != null)
            aWriter.write (sContent);
          else
            s_aLogger.error ("Web site resource '" +
                             aRes.getPath () +
                             "' at '" +
                             aRes.getAsURLString () +
                             "' has no content/does not exist!");
        }

        s_aLogger.info ("Serialized " +
                        m_aBundle.getResourceType ().getID () +
                        " bundle '" +
                        m_sBundleID +
                        "' with " +
                        m_aBundle.getAllResourcePaths () +
                        (m_aBundle.hasConditionalComment () ? " and conditional comment '" +
                                                              m_aBundle.getConditionalComment () +
                                                              "'"
                                                            : ""));
      }
      catch (final Throwable t)
      {
        s_aLogger.error ("Error serializing bundle '" + m_sBundleID + "' with " + m_aBundle.getAllResourcePaths (), t);
        throw new IllegalStateException (t);
      }
    }
  }

  @Nonnull
  @Nonempty
  public String getBundleID ()
  {
    return m_sBundleID;
  }

  @Nonnull
  public WebSiteResourceBundle getBundle ()
  {
    return m_aBundle;
  }

  @Nullable
  public static InputStream getInputStream (@Nonnull @Nonempty final String sBundleID)
  {
    ValueEnforcer.notEmpty (sBundleID, "BundleID");
    return WebFileIO.getDataIO ().getInputStream (WebSiteResourceBundleManager.RESOURCE_BUNDLE_PATH + sBundleID);
  }

  @Nullable
  public InputStream getInputStream ()
  {
    return getInputStream (m_sBundleID);
  }

  @Nonnull
  public IMimeType getMimeType ()
  {
    return m_aBundle.getMimeType ();
  }

  @Nonnull
  public LocalDateTime getCreationDT ()
  {
    return m_aCreationDT;
  }

  @Nonnull
  public IHCNode createNode (@Nonnull final IRequestWebScopeWithoutResponse aRequestScope)
  {
    ISimpleURL aURL = null;
    if (m_aBundle.getResourceCount () == 1 && !m_aBundle.isBundlable ())
    {
      // Special handling for resource bundles with a single item - use the
      // original path (e.g. for TinyMCE because it cannot be bundled)
      final WebSiteResource aResource = m_aBundle.getResourceAtIndex (0);
      aURL = aResource.getAsURL (aRequestScope);
    }

    if (aURL == null)
    {
      // Use the ResourceBundleServlet path by default
      aURL = LinkHelper.getURLWithContext (aRequestScope,
                                           ResourceBundleServlet.SERVLET_DEFAULT_PATH +
                                                          "/" +
                                                          m_sBundleID +
                                                          m_aBundle.getResourceType ().getFileExtension ());
    }

    // Create the main node
    final IHCNode aNode = m_aBundle.getResourceType ().createNode (aURL, m_aBundle.getMediaList ());

    // Wrap in conditional comment (if any)
    return m_aBundle.getWrapped (aNode);
  }

  @Override
  public String toString ()
  {
    return new ToStringGenerator (this).append ("bundleID", m_sBundleID)
                                       .append ("bundle", m_aBundle)
                                       .append ("creationDT", m_aCreationDT)
                                       .toString ();
  }
}
