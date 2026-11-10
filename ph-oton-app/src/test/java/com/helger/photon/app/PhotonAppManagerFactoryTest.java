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
package com.helger.photon.app;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.io.InputStream;
import java.io.UncheckedIOException;

import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.Nullable;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExternalResource;
import org.junit.rules.RuleChain;
import org.junit.rules.TestRule;

import com.helger.annotation.Nonempty;
import com.helger.base.io.nonblocking.NonBlockingByteArrayInputStream;
import com.helger.base.io.nonblocking.NonBlockingStringWriter;
import com.helger.base.io.stream.StreamHelper;
import com.helger.collection.commons.CommonsArrayList;
import com.helger.collection.commons.CommonsHashMap;
import com.helger.collection.commons.ICommonsList;
import com.helger.collection.commons.ICommonsMap;
import com.helger.datetime.helper.PDTFactory;
import com.helger.html.resource.js.ConstantJSPathProvider;
import com.helger.photon.app.mock.PhotonAppWebTestRule;
import com.helger.photon.app.resource.IWebSiteResourceBundleManager;
import com.helger.photon.app.resource.WebSiteResourceBundle;
import com.helger.photon.app.resource.WebSiteResourceBundleSerialized;
import com.helger.photon.app.resource.WebSiteResourceWithCondition;

/**
 * Test class for the factory of class {@link PhotonAppManager}.
 *
 * @author Philip Helger
 */
public final class PhotonAppManagerFactoryTest
{
  /**
   * A serialized bundle that keeps the merged content in memory instead of the file system.
   *
   * @author Philip Helger
   */
  private static final class InMemoryBundleSerialized extends WebSiteResourceBundleSerialized
  {
    private final byte [] m_aContent;

    InMemoryBundleSerialized (@NonNull @Nonempty final String sBundleID,
                              @NonNull final WebSiteResourceBundle aBundle,
                              final boolean bRegular)
    {
      super (sBundleID, aBundle, PDTFactory.getCurrentLocalDateTime ());
      final NonBlockingStringWriter aSW = new NonBlockingStringWriter ();
      try
      {
        writeMergedContent (aSW, bRegular);
      }
      catch (final IOException ex)
      {
        throw new UncheckedIOException (ex);
      }
      m_aContent = aSW.getAsString ().getBytes (CHARSET_TO_USE);
    }

    @Override
    @NonNull
    public InputStream getInputStream ()
    {
      return new NonBlockingByteArrayInputStream (m_aContent);
    }
  }

  /**
   * A simple manager that creates one in-memory bundle per call.
   *
   * @author Philip Helger
   */
  private static final class InMemoryBundleManager implements IWebSiteResourceBundleManager
  {
    private final ICommonsMap <String, WebSiteResourceBundleSerialized> m_aMap = new CommonsHashMap <> ();

    @NonNull
    public ICommonsList <WebSiteResourceBundleSerialized> getResourceBundles (@NonNull @Nonempty final ICommonsList <WebSiteResourceWithCondition> aList,
                                                                              final boolean bRegular)
    {
      final WebSiteResourceWithCondition aFirst = aList.getFirstOrNull ();
      final WebSiteResourceBundle aBundle = new WebSiteResourceBundle (aList,
                                                                       aFirst.getConditionalComment (),
                                                                       true,
                                                                       aFirst.getMediaList ());
      final String sBundleID = "bundle" + m_aMap.size ();
      final WebSiteResourceBundleSerialized aBundleSerialized = new InMemoryBundleSerialized (sBundleID,
                                                                                              aBundle,
                                                                                              bRegular);
      m_aMap.put (sBundleID, aBundleSerialized);
      return new CommonsArrayList <> (aBundleSerialized);
    }

    @NonNull
    public ICommonsMap <String, WebSiteResourceBundleSerialized> getAllResourceBundles ()
    {
      return m_aMap.getClone ();
    }

    @NonNull
    public ICommonsList <WebSiteResourceBundleSerialized> getAllResourceBundlesSerialized ()
    {
      return m_aMap.copyOfValues ();
    }

    @Nullable
    public WebSiteResourceBundleSerialized getResourceBundleOfID (@Nullable final String sBundleID)
    {
      return m_aMap.get (sBundleID);
    }

    public boolean containsResourceBundleOfID (@Nullable final String sBundleID)
    {
      return m_aMap.containsKey (sBundleID);
    }
  }

  /**
   * Restores the previously installed factory. This must happen after the global scope was
   * destroyed, because {@link PhotonAppManager#setFactory(PhotonAppManager.IFactory)} ignores all
   * calls as long as the manager is initialized.
   *
   * @author Philip Helger
   */
  private static final class RestoreFactoryRule extends ExternalResource
  {
    private PhotonAppManager.IFactory m_aOldFactory;

    @Override
    protected void before ()
    {
      m_aOldFactory = PhotonAppManager.getFactory ();
      PhotonAppManager.setFactory (InMemoryBundleManager::new);
    }

    @Override
    protected void after ()
    {
      PhotonAppManager.setFactory (m_aOldFactory);
      // If this fails, all subsequent tests would silently use the custom factory
      assertSame (m_aOldFactory, PhotonAppManager.getFactory ());
    }
  }

  @Rule
  public final TestRule m_aRule = RuleChain.outerRule (new RestoreFactoryRule ()).around (new PhotonAppWebTestRule ());

  @Test
  public void testWebSiteResourceBundleMgrIsTakenFromFactory ()
  {
    final IWebSiteResourceBundleManager aMgr = PhotonAppManager.getWebSiteResourceBundleMgr ();
    assertTrue (aMgr instanceof InMemoryBundleManager);
    assertSame (aMgr, PhotonAppManager.getWebSiteResourceBundleMgr ());

    final ICommonsList <WebSiteResourceWithCondition> aList = new CommonsArrayList <> ();
    aList.add (WebSiteResourceWithCondition.createForJS (ConstantJSPathProvider.builder ()
                                                                               .path ("external/res/serverlog.js")
                                                                               .minifiedPathFromPath ()
                                                                               .build (), true));
    aList.add (WebSiteResourceWithCondition.createForJS (ConstantJSPathProvider.builder ()
                                                                               .path ("external/res/stacktrace.js")
                                                                               .minifiedPathFromPath ()
                                                                               .build (), true));
    final ICommonsList <WebSiteResourceBundleSerialized> aBundles = aMgr.getResourceBundles (aList, true);
    assertEquals (1, aBundles.size ());

    final WebSiteResourceBundleSerialized aBundle = aBundles.getFirstOrNull ();
    assertTrue (aMgr.containsResourceBundleOfID (aBundle.getBundleID ()));
    assertSame (aBundle, aMgr.getResourceBundleOfID (aBundle.getBundleID ()));
    assertFalse (aMgr.containsResourceBundleOfID ("does-not-exist"));
    assertNull (aMgr.getResourceBundleOfID ("does-not-exist"));

    // The merged content is taken from memory and not from the file system
    assertNull (WebSiteResourceBundleSerialized.getResource (aBundle.getBundleID ()).getInputStream ());
    final String sContent = StreamHelper.getAllBytesAsString (aBundle,
                                                              WebSiteResourceBundleSerialized.CHARSET_TO_USE);
    assertNotNull (sContent);
    assertTrue (sContent.contains ("/* external/res/serverlog.js - "));
    assertTrue (sContent.contains ("/* external/res/stacktrace.js - "));
  }
}
