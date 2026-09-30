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
package com.helger.photon.security.mgr;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

import org.jspecify.annotations.NonNull;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExternalResource;
import org.junit.rules.RuleChain;
import org.junit.rules.TestRule;

import com.helger.photon.app.mock.PhotonAppWebTestRule;
import com.helger.photon.security.lock.DefaultLockManager;
import com.helger.photon.security.lock.ILockManager;
import com.helger.photon.security.login.GlobalUserIDProvider;

/**
 * Test class for the lock manager factory of class {@link PhotonSecurityManager}.
 *
 * @author Philip Helger
 */
public final class PhotonSecurityManagerLockMgrFactoryTest
{
  /**
   * A lock manager implementation that is different from the default one, so that it can be
   * identified by its type.
   *
   * @author Philip Helger
   */
  private static final class MockLockManager extends DefaultLockManager <String>
  {
    MockLockManager ()
    {
      super (GlobalUserIDProvider::getCurrentUserID);
    }
  }

  /**
   * A factory that only replaces the lock manager and uses the XML defaults for everything else.
   *
   * @author Philip Helger
   */
  private static final class MockFactory extends PhotonSecurityManager.FactoryXML
  {
    @Override
    @NonNull
    public ILockManager <String> createLockMgr ()
    {
      return new MockLockManager ();
    }
  }

  /**
   * Restores the previously installed factory. This must happen after the global scope was
   * destroyed, because {@link PhotonSecurityManager#setFactory(PhotonSecurityManager.IFactory)}
   * ignores all calls as long as the manager is initialized.
   *
   * @author Philip Helger
   */
  private static final class RestoreFactoryRule extends ExternalResource
  {
    private PhotonSecurityManager.IFactory m_aOldFactory;

    @Override
    protected void before ()
    {
      m_aOldFactory = PhotonSecurityManager.getFactory ();
      PhotonSecurityManager.setFactory (new MockFactory ());
    }

    @Override
    protected void after ()
    {
      PhotonSecurityManager.setFactory (m_aOldFactory);
      // If this fails, all subsequent tests would silently use the MockFactory
      assertSame (m_aOldFactory, PhotonSecurityManager.getFactory ());
    }
  }

  @Rule
  public final TestRule m_aRule = RuleChain.outerRule (new RestoreFactoryRule ()).around (new PhotonAppWebTestRule ());

  @Test
  public void testLockMgrIsTakenFromFactory ()
  {
    final ILockManager <String> aLockMgr = PhotonSecurityManager.getLockMgr ();
    assertNotNull (aLockMgr);
    assertTrue (aLockMgr instanceof MockLockManager);

    // The same instance must be returned on every call
    assertSame (aLockMgr, PhotonSecurityManager.getLockMgr ());
    assertSame (aLockMgr, PhotonSecurityManager.getLockMgrIfInstantiated ());
  }
}
