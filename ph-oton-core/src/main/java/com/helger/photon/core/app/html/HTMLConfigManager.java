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
package com.helger.photon.core.app.html;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

import javax.annotation.Nonnull;
import javax.annotation.concurrent.GuardedBy;
import javax.annotation.concurrent.ThreadSafe;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.helger.commons.ValueEnforcer;
import com.helger.commons.annotations.ReturnsMutableCopy;
import com.helger.commons.io.IReadableResource;
import com.helger.commons.io.resource.ClassPathResource;
import com.helger.commons.microdom.reader.XMLMapHandler;
import com.helger.commons.url.ISimpleURL;
import com.helger.html.EHTMLVersion;
import com.helger.html.hc.conversion.HCConversionSettingsProvider;
import com.helger.html.hc.conversion.HCSettings;
import com.helger.html.hc.html.HCScript;
import com.helger.html.meta.IMetaElement;
import com.helger.html.meta.MetaElement;
import com.helger.html.meta.MetaElementList;
import com.helger.html.resource.css.ICSSPathProvider;
import com.helger.html.resource.js.IJSPathProvider;
import com.helger.photon.core.url.IWebURIToURLConverter;
import com.helger.photon.core.url.StreamOrLocalURIToURLConverter;
import com.helger.web.scopes.domain.IRequestWebScopeWithoutResponse;

/**
 * This class holds the central configuration settings.
 * <ul>
 * <li>All global meta elements</li>
 * <li>All global page CSS files</li>
 * <li>All global page JavaScript files</li>
 * </ul>
 *
 * @author Philip Helger
 */
@ThreadSafe
public class HTMLConfigManager
{
  /** Filename containing the meta elements */
  public static final String FILENAME_METATAGS_XML = "metatags.xml";

  private static final Logger s_aLogger = LoggerFactory.getLogger (HTMLConfigManager.class);

  private final ReadWriteLock m_aRWLock = new ReentrantReadWriteLock ();
  private static final ReadWriteLock s_aRWLock = new ReentrantReadWriteLock ();
  @GuardedBy ("m_aRWLock")
  private final MetaElementList m_aMetaElements = new MetaElementList ();
  @GuardedBy ("s_aRWLock")
  private static EHTMLVersion m_eHTMLVersion = EHTMLVersion.DEFAULT;
  @GuardedBy ("s_aRWLock")
  private static IWebURIToURLConverter m_aURIToURLConverter = StreamOrLocalURIToURLConverter.getInstance ();

  public HTMLConfigManager ()
  {}

  @Nonnull
  @ReturnsMutableCopy
  public static MetaElementList getAllMetaElementsOfResource (@Nonnull final IReadableResource aRes)
  {
    ValueEnforcer.notNull (aRes, "Res");

    final MetaElementList ret = new MetaElementList ();

    if (aRes.exists ())
    {
      final Map <String, String> aMetaElements = new LinkedHashMap <String, String> ();
      if (XMLMapHandler.readMap (aRes, aMetaElements).isFailure ())
        s_aLogger.error ("Failed to read meta element file " + aRes.getPath ());

      for (final Map.Entry <String, String> aEntry : aMetaElements.entrySet ())
        ret.addMetaElement (new MetaElement (aEntry.getKey (), aEntry.getValue ()));
    }

    return ret;
  }

  @Nonnull
  @Deprecated
  public HTMLConfigManager readAllFiles (@Nonnull final String sBasePath)
  {
    ValueEnforcer.notNull (sBasePath, "BasePath");
    if (sBasePath.length () > 0 && !sBasePath.endsWith ("/"))
      throw new IllegalArgumentException ("BasePath must end with a '/'!");

    m_aRWLock.writeLock ().lock ();
    try
    {
      // read all static MetaTags
      m_aMetaElements.addMetaElements (getAllMetaElementsOfResource (new ClassPathResource (sBasePath +
                                                                                            FILENAME_METATAGS_XML)));
    }
    finally
    {
      m_aRWLock.writeLock ().unlock ();
    }
    return this;
  }

  /**
   * @return A list of all global meta elements. Never <code>null</code>.
   */
  @Nonnull
  @ReturnsMutableCopy
  public MetaElementList getAllMetaElements ()
  {
    m_aRWLock.readLock ().lock ();
    try
    {
      return m_aMetaElements.getClone ();
    }
    finally
    {
      m_aRWLock.readLock ().unlock ();
    }
  }

  @Nonnull
  public HTMLConfigManager addMetaElement (@Nonnull final IMetaElement aMetaElement)
  {
    ValueEnforcer.notNull (aMetaElement, "MetaElement");
    m_aRWLock.writeLock ().lock ();
    try
    {
      m_aMetaElements.addMetaElement (aMetaElement);
    }
    finally
    {
      m_aRWLock.writeLock ().unlock ();
    }
    return this;
  }

  @Nonnull
  public HTMLConfigManager removeAllMetaElements ()
  {
    m_aRWLock.writeLock ().lock ();
    try
    {
      m_aMetaElements.removeAllMetaElements ();
    }
    finally
    {
      m_aRWLock.writeLock ().unlock ();
    }
    return this;
  }

  /**
   * @return The HTML version to use. Never <code>null</code>.
   */
  @Nonnull
  public static EHTMLVersion getHTMLVersion ()
  {
    s_aRWLock.readLock ().lock ();
    try
    {
      return m_eHTMLVersion;
    }
    finally
    {
      s_aRWLock.readLock ().unlock ();
    }
  }

  /**
   * Set the default HTML version to use. This implicitly creates a new
   * {@link HCConversionSettingsProvider} that will be used in
   * {@link HCSettings}. So if you are customizing the settings ensure that this
   * is done after setting the HTML version!
   *
   * @param eHTMLVersion
   *        The HTML version. May not be <code>null</code>.
   */
  public static void setHTMLVersion (@Nonnull final EHTMLVersion eHTMLVersion)
  {
    ValueEnforcer.notNull (eHTMLVersion, "HTMLVersion");

    s_aRWLock.writeLock ().lock ();
    try
    {
      if (eHTMLVersion != m_eHTMLVersion)
      {
        // Store the changed HTML version
        m_eHTMLVersion = eHTMLVersion;

        // Update the HCSettings
        HCSettings.getConversionSettingsProvider ().setHTMLVersion (eHTMLVersion);
        if (eHTMLVersion.isAtLeastHTML5 ())
        {
          // No need to put anything in a comment
          HCScript.setDefaultMode (HCScript.EMode.PLAIN_TEXT_NO_ESCAPE);
        }
        else
        {
          // Use default mode
          HCScript.setDefaultMode (HCScript.DEFAULT_MODE);
        }
      }
    }
    finally
    {
      s_aRWLock.writeLock ().unlock ();
    }
  }

  @Nonnull
  public static IWebURIToURLConverter getURIToURLConverter ()
  {
    s_aRWLock.readLock ().lock ();
    try
    {
      return m_aURIToURLConverter;
    }
    finally
    {
      s_aRWLock.readLock ().unlock ();
    }
  }

  public static void setURIToURLConverter (@Nonnull final IWebURIToURLConverter aURIToURLConverter)
  {
    ValueEnforcer.notNull (aURIToURLConverter, "URIToURLConverter");

    s_aRWLock.writeLock ().lock ();
    try
    {
      m_aURIToURLConverter = aURIToURLConverter;
    }
    finally
    {
      s_aRWLock.writeLock ().unlock ();
    }
  }

  @Nonnull
  public static ISimpleURL getCSSPath (@Nonnull final IRequestWebScopeWithoutResponse aRequestScope,
                                       @Nonnull final ICSSPathProvider aCSS,
                                       final boolean bRegular)
  {
    return m_aURIToURLConverter.getAsURL (aRequestScope, aCSS.getCSSItemPath (bRegular));
  }

  @Nonnull
  public static ISimpleURL getJSPath (@Nonnull final IRequestWebScopeWithoutResponse aRequestScope,
                                      @Nonnull final IJSPathProvider aJS,
                                      final boolean bRegular)
  {
    return m_aURIToURLConverter.getAsURL (aRequestScope, aJS.getJSItemPath (bRegular));
  }
}
