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

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

import javax.annotation.Nonnull;
import javax.annotation.concurrent.GuardedBy;
import javax.annotation.concurrent.ThreadSafe;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.helger.commons.ValueEnforcer;
import com.helger.commons.annotations.ReturnsMutableCopy;
import com.helger.commons.collections.CollectionHelper;
import com.helger.commons.io.IReadableResource;
import com.helger.commons.io.resource.ClassPathResource;
import com.helger.commons.microdom.IMicroDocument;
import com.helger.commons.microdom.IMicroElement;
import com.helger.commons.microdom.reader.XMLMapHandler;
import com.helger.commons.microdom.serialize.MicroReader;
import com.helger.commons.regex.RegExHelper;
import com.helger.commons.string.StringHelper;
import com.helger.commons.url.ISimpleURL;
import com.helger.css.media.CSSMediaList;
import com.helger.css.media.ECSSMedium;
import com.helger.html.EHTMLVersion;
import com.helger.html.hc.conversion.HCConversionSettingsProvider;
import com.helger.html.hc.conversion.HCSettings;
import com.helger.html.hc.html.HCScript;
import com.helger.html.meta.IMetaElement;
import com.helger.html.meta.MetaElement;
import com.helger.html.meta.MetaElementList;
import com.helger.html.resource.css.ConstantCSSPathProvider;
import com.helger.html.resource.css.ICSSPathProvider;
import com.helger.html.resource.js.ConstantJSPathProvider;
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
  /** Filename containing the CSS includes */
  public static final String FILENAME_CSS_XML = "css.xml";
  /** Filename containing the JS includes */
  public static final String FILENAME_JS_XML = "js.xml";

  private static final Logger s_aLogger = LoggerFactory.getLogger (HTMLConfigManager.class);

  private final ReadWriteLock m_aRWLock = new ReentrantReadWriteLock ();
  private static final ReadWriteLock s_aRWLock = new ReentrantReadWriteLock ();
  @GuardedBy ("m_aRWLock")
  private final MetaElementList m_aMetaElements = new MetaElementList ();
  @GuardedBy ("m_aRWLock")
  private final Set <ICSSPathProvider> m_aCSSItems = new LinkedHashSet <ICSSPathProvider> ();
  @GuardedBy ("m_aRWLock")
  private final Set <IJSPathProvider> m_aJSItems = new LinkedHashSet <IJSPathProvider> ();
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
  @ReturnsMutableCopy
  public static List <ICSSPathProvider> getAllCSSItemsOfResource (@Nonnull final IReadableResource aRes,
                                                                  @Nonnull final IWebURIToURLConverter aURIToURLConverter)
  {
    ValueEnforcer.notNull (aRes, "Res");
    ValueEnforcer.notNull (aURIToURLConverter, "URIToURLConverter");

    final List <ICSSPathProvider> ret = new ArrayList <ICSSPathProvider> ();
    final IMicroDocument aDoc = aRes.exists () ? MicroReader.readMicroXML (aRes) : null;
    if (aDoc != null)
      for (final IMicroElement eChild : aDoc.getDocumentElement ().getAllChildElements ("css"))
      {
        final String sPath = eChild.getAttributeValue ("path");
        if (StringHelper.hasNoText (sPath))
        {
          s_aLogger.error ("Found CSS item without a path in " + aRes.getPath ());
          continue;
        }

        final IReadableResource aChildRes = aURIToURLConverter.getAsResource (sPath);
        if (!aChildRes.exists ())
          throw new IllegalStateException ("The provided global CSS resource '" +
                                           sPath +
                                           "' resolved to '" +
                                           aChildRes.getAsURL () +
                                           "' does NOT exist!");

        final String sConditionalComment = eChild.getAttributeValue ("condcomment");

        final String sMedia = eChild.getAttributeValue ("media");
        final CSSMediaList aMediaList = new CSSMediaList ();
        if (sMedia != null)
          for (final String sMedium : RegExHelper.getSplitToArray (sMedia, ",\\s*"))
          {
            final ECSSMedium eMedium = ECSSMedium.getFromNameOrNull (sMedium);
            if (eMedium == null)
            {
              s_aLogger.warn ("CSS item '" +
                              sPath +
                              "' in " +
                              aRes.getPath () +
                              " has an invalid medium '" +
                              sMedium +
                              "' - ignoring");
              continue;
            }
            aMediaList.addMedium (eMedium);
          }
        ret.add (new ConstantCSSPathProvider (sPath, sConditionalComment, aMediaList));
      }
    return ret;
  }

  @Nonnull
  @ReturnsMutableCopy
  public static List <IJSPathProvider> getAllJSItemsOfResource (@Nonnull final IReadableResource aRes,
                                                                @Nonnull final IWebURIToURLConverter aURIToURLConverter)
  {
    ValueEnforcer.notNull (aRes, "Res");
    ValueEnforcer.notNull (aURIToURLConverter, "URIToURLConverter");

    final List <IJSPathProvider> ret = new ArrayList <IJSPathProvider> ();
    final IMicroDocument aDoc = aRes.exists () ? MicroReader.readMicroXML (aRes) : null;
    if (aDoc != null)
      for (final IMicroElement eChild : aDoc.getDocumentElement ().getAllChildElements ("js"))
      {
        final String sPath = eChild.getAttributeValue ("path");
        if (StringHelper.hasNoText (sPath))
        {
          s_aLogger.error ("Found JS item without a path in " + aRes.getPath ());
          continue;
        }

        final IReadableResource aChildRes = aURIToURLConverter.getAsResource (sPath);
        if (!aChildRes.exists ())
          throw new IllegalStateException ("The provided global JS resource '" +
                                           sPath +
                                           "' resolved to '" +
                                           aChildRes.getAsURL () +
                                           "' does NOT exist!");

        final String sConditionalComment = eChild.getAttributeValue ("condcomment");

        ret.add (new ConstantJSPathProvider (sPath, sConditionalComment, true));
      }
    return ret;
  }

  @Nonnull
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

      // read all CSS files
      m_aCSSItems.addAll (getAllCSSItemsOfResource (new ClassPathResource (sBasePath + FILENAME_CSS_XML),
                                                    m_aURIToURLConverter));

      // read all JS files
      m_aJSItems.addAll (getAllJSItemsOfResource (new ClassPathResource (sBasePath + FILENAME_JS_XML),
                                                  m_aURIToURLConverter));
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
   * @return A list of all global CSS items. Never <code>null</code>.
   */
  @Nonnull
  @ReturnsMutableCopy
  public List <ICSSPathProvider> getAllCSSItems ()
  {
    m_aRWLock.readLock ().lock ();
    try
    {
      return CollectionHelper.newList (m_aCSSItems);
    }
    finally
    {
      m_aRWLock.readLock ().unlock ();
    }
  }

  @Nonnull
  public HTMLConfigManager addCSSItem (@Nonnull final ICSSPathProvider aCSSItem)
  {
    ValueEnforcer.notNull (aCSSItem, "CSSItem");
    m_aRWLock.writeLock ().lock ();
    try
    {
      m_aCSSItems.add (aCSSItem);
    }
    finally
    {
      m_aRWLock.writeLock ().unlock ();
    }
    return this;
  }

  @Nonnull
  public HTMLConfigManager removeAllCSSItems ()
  {
    m_aRWLock.writeLock ().lock ();
    try
    {
      m_aCSSItems.clear ();
    }
    finally
    {
      m_aRWLock.writeLock ().unlock ();
    }
    return this;
  }

  /**
   * @return A list of all global JS items. Never <code>null</code>.
   */
  @Nonnull
  @ReturnsMutableCopy
  public List <IJSPathProvider> getAllJSItems ()
  {
    m_aRWLock.readLock ().lock ();
    try
    {
      return CollectionHelper.newList (m_aJSItems);
    }
    finally
    {
      m_aRWLock.readLock ().unlock ();
    }
  }

  @Nonnull
  public HTMLConfigManager addJSItem (@Nonnull final IJSPathProvider aJSItem)
  {
    ValueEnforcer.notNull (aJSItem, "JSItem");
    m_aRWLock.writeLock ().lock ();
    try
    {
      m_aJSItems.add (aJSItem);
    }
    finally
    {
      m_aRWLock.writeLock ().unlock ();
    }
    return this;
  }

  @Nonnull
  public HTMLConfigManager removeAllJSItems ()
  {
    m_aRWLock.writeLock ().lock ();
    try
    {
      m_aJSItems.clear ();
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
