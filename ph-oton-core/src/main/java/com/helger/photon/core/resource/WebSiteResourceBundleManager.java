/**
 * Copyright (C) 2014-2016 Philip Helger (www.helger.com)
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

import java.nio.charset.Charset;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.concurrent.GuardedBy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.helger.commons.ValueEnforcer;
import com.helger.commons.annotation.Nonempty;
import com.helger.commons.annotation.ReturnsMutableCopy;
import com.helger.commons.charset.CharsetManager;
import com.helger.commons.collection.CollectionHelper;
import com.helger.commons.id.factory.GlobalIDFactory;
import com.helger.commons.microdom.IMicroDocument;
import com.helger.commons.microdom.IMicroElement;
import com.helger.commons.microdom.MicroDocument;
import com.helger.commons.state.EChange;
import com.helger.commons.string.StringHelper;
import com.helger.commons.string.StringParser;
import com.helger.css.media.CSSMediaList;
import com.helger.css.media.ECSSMedium;
import com.helger.photon.basic.app.dao.impl.AbstractSimpleDAO;
import com.helger.photon.basic.app.dao.impl.DAOException;

public final class WebSiteResourceBundleManager extends AbstractSimpleDAO
{
  public static final String RESOURCE_BUNDLE_PATH = "resource-bundles/";

  private static final String ELEMENT_RESOURCE_BUNDLES = "resource-bundles";
  private static final String ELEMENT_RESOURCE_BUNDLE = "resource-bundle";
  private static final String ATTR_ID = "id";
  private static final String ATTR_CREATIONDT = "creationdt";
  private static final String ATTR_CONDITIONAL_COMMENT = "conditionalcomment";
  // Legacy name!
  private static final String ATTR_IS_BUNDLABLE = "canbebundled";
  private static final String ELEMENT_MEDIUM = "medium";
  private static final String ELEMENT_RESOURCE = "resource";
  private static final String ATTR_RESOURCE_TYPE = "resourcetype";
  private static final String ATTR_PATH = "path";
  private static final String ATTR_URL = "url";
  private static final String ATTR_CONTENT_HASH = "contenthash";
  private static final String ATTR_CHARSET = "charset";

  private static final Logger s_aLogger = LoggerFactory.getLogger (WebSiteResourceBundleManager.class);
  @GuardedBy ("m_aRWLock")
  private final Map <WebSiteResourceBundle, String> m_aMapToData = new HashMap <WebSiteResourceBundle, String> ();
  @GuardedBy ("m_aRWLock")
  private final Map <String, WebSiteResourceBundleSerialized> m_aMapToBundle = new HashMap <String, WebSiteResourceBundleSerialized> ();

  public WebSiteResourceBundleManager (@Nullable final String sFilename) throws DAOException
  {
    super (sFilename);
    initialRead ();
  }

  @Override
  @Nonnull
  protected EChange onRead (@Nonnull final IMicroDocument aDoc)
  {
    boolean bAnyResourceIsOutOfSync = false;
    for (final IMicroElement eResourceBundle : aDoc.getDocumentElement ().getAllChildElements (ELEMENT_RESOURCE_BUNDLE))
    {
      boolean bResourcesAreOutOfSync = false;
      final String sBundleID = eResourceBundle.getAttributeValue (ATTR_ID);
      final LocalDateTime aCreationDT = eResourceBundle.getAttributeValueWithConversion (ATTR_CREATIONDT,
                                                                                         LocalDateTime.class);
      final String sConditionalComment = eResourceBundle.getAttributeValue (ATTR_CONDITIONAL_COMMENT);

      // This attribute was added - default to true
      final String sCanBeBundled = eResourceBundle.getAttributeValue (ATTR_IS_BUNDLABLE);
      final boolean bCanBeBundled = StringParser.parseBool (sCanBeBundled, true);

      final CSSMediaList aMediaList = new CSSMediaList ();
      for (final IMicroElement eMedium : eResourceBundle.getAllChildElements (ELEMENT_MEDIUM))
      {
        final String sCSSMedium = eMedium.getTextContentTrimmed ();
        final ECSSMedium eCSSMedium = ECSSMedium.getFromNameOrNull (sCSSMedium);
        if (eCSSMedium == null)
          throw new IllegalStateException ("Failed to resolve CSS medium '" + sCSSMedium + "'");
        aMediaList.addMedium (eCSSMedium);
      }

      final List <WebSiteResourceWithCondition> aResources = new ArrayList <WebSiteResourceWithCondition> ();
      for (final IMicroElement eResource : eResourceBundle.getAllChildElements (ELEMENT_RESOURCE))
      {
        final String sResourceType = eResource.getAttributeValue (ATTR_RESOURCE_TYPE);
        final EWebSiteResourceType eResourceType = EWebSiteResourceType.getFromIDOrNull (sResourceType);
        if (eResourceType == null)
        {
          s_aLogger.warn ("No such resource type: " + sResourceType);
          bResourcesAreOutOfSync = true;
          continue;
        }
        final String sPath = eResource.getAttributeValue (ATTR_PATH);
        final String sURL = eResource.getAttributeValue (ATTR_URL);
        final String sHash = eResource.getAttributeValue (ATTR_CONTENT_HASH);
        final String sCharset = eResource.getAttributeValue (ATTR_CHARSET);
        // Soft migration as charset was added later
        final Charset aCharset = sCharset == null ? WebSiteResource.DEFAULT_CHARSET
                                                  : CharsetManager.getCharsetFromName (sCharset);

        final WebSiteResource aNewResource = new WebSiteResource (eResourceType, sPath, aCharset);
        if (!aNewResource.isExisting ())
        {
          s_aLogger.info ("Skipping resource bundle '" +
                          sBundleID +
                          "' skipping because resource '" +
                          sPath +
                          "' does not exist");
          bResourcesAreOutOfSync = true;
          continue;
        }

        // The relocation check makes now sense, because the hash code is
        // the relevant enough...
        if (false)
          if (!aNewResource.getAsURLString ().equals (sURL))
          {
            s_aLogger.info ("Skipping resource bundle '" +
                            sBundleID +
                            "' because resource '" +
                            sPath +
                            "' was relocated from '" +
                            sURL +
                            "' to '" +
                            aNewResource.getAsURLString () +
                            "'");
            bResourcesAreOutOfSync = true;
            continue;
          }

        if (!aNewResource.getContentHashAsString ().equals (sHash))
        {
          s_aLogger.info ("Skipping resource bundle '" +
                          sBundleID +
                          "' skipping because resource '" +
                          sPath +
                          "' changed (hash mismatch)");
          bResourcesAreOutOfSync = true;
          continue;
        }
        aResources.add (new WebSiteResourceWithCondition (aNewResource,
                                                          sConditionalComment,
                                                          bCanBeBundled,
                                                          aMediaList));
      }

      if (bResourcesAreOutOfSync)
      {
        // Remember to save changes
        bAnyResourceIsOutOfSync = true;
      }
      else
      {
        // Restore bundle
        final WebSiteResourceBundle aBundle = new WebSiteResourceBundle (aResources,
                                                                         sConditionalComment,
                                                                         bCanBeBundled,
                                                                         aMediaList);
        final WebSiteResourceBundleSerialized aBundleSerialized = new WebSiteResourceBundleSerialized (sBundleID,
                                                                                                       aBundle,
                                                                                                       aCreationDT);
        m_aMapToData.put (aBundle, sBundleID);
        m_aMapToBundle.put (sBundleID, aBundleSerialized);
      }
    }

    s_aLogger.info ("Successfully read " + m_aMapToBundle.size () + " resource bundles");
    return bAnyResourceIsOutOfSync ? EChange.CHANGED : EChange.UNCHANGED;
  }

  @Override
  @Nonnull
  protected IMicroDocument createWriteData ()
  {
    final IMicroDocument aDoc = new MicroDocument ();
    final IMicroElement eRoot = aDoc.appendElement (ELEMENT_RESOURCE_BUNDLES);
    for (final WebSiteResourceBundleSerialized aResourceBundle : CollectionHelper.getSortedByKey (m_aMapToBundle)
                                                                                 .values ())
    {
      final IMicroElement eBundle = eRoot.appendElement (ELEMENT_RESOURCE_BUNDLE);
      eBundle.setAttribute (ATTR_ID, aResourceBundle.getBundleID ());
      eBundle.setAttributeWithConversion (ATTR_CREATIONDT, aResourceBundle.getCreationDT ());

      final WebSiteResourceBundle aBundle = aResourceBundle.getBundle ();
      eBundle.setAttributeWithConversion (ATTR_CONDITIONAL_COMMENT, aBundle.getConditionalComment ());
      eBundle.setAttribute (ATTR_IS_BUNDLABLE, Boolean.toString (aBundle.isBundlable ()));
      if (aBundle.hasMediaList ())
        for (final ECSSMedium eMedium : aBundle.getMediaList ().getAllMedia ())
          eBundle.appendElement (ELEMENT_MEDIUM).appendText (eMedium.getName ());
      for (final WebSiteResource aResource : aBundle.getAllResources ())
      {
        final IMicroElement eResource = eBundle.appendElement (ELEMENT_RESOURCE);
        eResource.setAttribute (ATTR_RESOURCE_TYPE, aResource.getResourceType ().getID ());
        eResource.setAttribute (ATTR_PATH, aResource.getPath ());
        eResource.setAttribute (ATTR_URL, aResource.getAsURLString ());
        eResource.setAttribute (ATTR_CONTENT_HASH, aResource.getContentHashAsString ());
        eResource.setAttribute (ATTR_CHARSET, aResource.getCharset ().name ());
      }
    }
    return aDoc;
  }

  @Nonnull
  @ReturnsMutableCopy
  public Map <String, WebSiteResourceBundleSerialized> getAllResourceBundles ()
  {
    m_aRWLock.readLock ().lock ();
    try
    {
      return CollectionHelper.newMap (m_aMapToBundle);
    }
    finally
    {
      m_aRWLock.readLock ().unlock ();
    }
  }

  /**
   * Get the serialized resource bundle with the passed ID.
   *
   * @param sBundleID
   *        The bundle ID to be resolved. May be <code>null</code>.
   * @return <code>null</code> if no such bundle exists.
   */
  @Nullable
  public WebSiteResourceBundleSerialized getResourceBundleOfID (@Nullable final String sBundleID)
  {
    if (StringHelper.hasNoText (sBundleID))
      return null;

    m_aRWLock.readLock ().lock ();
    try
    {
      return m_aMapToBundle.get (sBundleID);
    }
    finally
    {
      m_aRWLock.readLock ().unlock ();
    }
  }

  /**
   * Check if the passed resource bundle ID is contained.
   *
   * @param sBundleID
   *        The bundle ID to be checked. May be <code>null</code>.
   * @return <code>true</code> if the passed bundle exists, <code>false</code>
   *         otherwise.
   */
  public boolean containsResourceBundleOfID (@Nullable final String sBundleID)
  {
    if (StringHelper.hasNoText (sBundleID))
      return false;

    m_aRWLock.readLock ().lock ();
    try
    {
      return m_aMapToBundle.containsKey (sBundleID);
    }
    finally
    {
      m_aRWLock.readLock ().unlock ();
    }
  }

  @Nonnull
  @ReturnsMutableCopy
  public List <WebSiteResourceBundleSerialized> getResourceBundles (@Nonnull @Nonempty final List <WebSiteResourceWithCondition> aList,
                                                                    final boolean bRegular)
  {
    ValueEnforcer.notEmptyNoNullValue (aList, "List");

    final List <WebSiteResourceBundleSerialized> ret = new ArrayList <WebSiteResourceBundleSerialized> ();

    // Create a copy for modification
    boolean bCreatedAnyBundle = false;
    final List <WebSiteResourceWithCondition> aCopy = CollectionHelper.newList (aList);
    while (!aCopy.isEmpty ())
    {
      final WebSiteResourceWithCondition aFirst = aCopy.remove (0);

      // Find all resources that can be bundled with aFirst
      final List <WebSiteResourceWithCondition> aBundleResources = CollectionHelper.newList (aFirst);
      while (!aCopy.isEmpty ())
      {
        final WebSiteResourceWithCondition aBundleCandidate = aCopy.get (0);
        if (aFirst.canBeBundledWith (aBundleCandidate))
        {
          // Can be bundled
          aBundleResources.add (aBundleCandidate);
          aCopy.remove (0);
        }
        else
        {
          // Cannot be bundled - put into next bundle
          break;
        }
      }

      // Create the bundle
      final WebSiteResourceBundle aBundle = new WebSiteResourceBundle (aBundleResources,
                                                                       aFirst.getConditionalComment (),
                                                                       aBundleResources.size () != 1 ||
                                                                                                        aFirst.isBundlable (),
                                                                       aFirst.getMediaList ());

      // Try to find existing bundle (ID and serialized one)
      String sBundleID;
      WebSiteResourceBundleSerialized aBundleSerialized;
      m_aRWLock.readLock ().lock ();
      try
      {
        sBundleID = m_aMapToData.get (aBundle);
        aBundleSerialized = m_aMapToBundle.get (sBundleID);
      }
      finally
      {
        m_aRWLock.readLock ().unlock ();
      }

      if (aBundleSerialized == null)
      {
        // No bundle found so far
        m_aRWLock.writeLock ().lock ();
        try
        {
          // Try again in write lock
          sBundleID = m_aMapToData.get (aBundle);
          if (sBundleID == null)
          {
            // Create a new bundle ID
            sBundleID = GlobalIDFactory.getNewPersistentStringID ();
            m_aMapToData.put (aBundle, sBundleID);

            // Create the main bundle
            aBundleSerialized = new WebSiteResourceBundleSerialized (sBundleID, aBundle, bRegular);
            m_aMapToBundle.put (sBundleID, aBundleSerialized);

            // Remember that we created a bundle
            bCreatedAnyBundle = true;
          }
          else
          {
            // We found it in the write-lock :)
            aBundleSerialized = m_aMapToBundle.get (sBundleID);
          }
        }
        finally
        {
          m_aRWLock.writeLock ().unlock ();
        }
      }

      // Use bundled version
      ret.add (aBundleSerialized);
    }

    // Write once at the end
    if (bCreatedAnyBundle)
    {
      m_aRWLock.writeLocked ( () -> {
        markAsChanged ();
      });
    }

    return ret;
  }
}
