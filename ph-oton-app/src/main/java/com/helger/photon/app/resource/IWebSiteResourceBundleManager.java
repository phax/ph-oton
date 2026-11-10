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
package com.helger.photon.app.resource;

import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.Nullable;

import com.helger.annotation.style.ReturnsMutableCopy;
import com.helger.collection.commons.ICommonsList;
import com.helger.collection.commons.ICommonsMap;

/**
 * Base interface for a manager of resource bundles. In addition to creating the bundles, it allows
 * to resolve existing bundles by their ID.
 *
 * @author Philip Helger
 * @since 10.8.0
 */
public interface IWebSiteResourceBundleManager extends IWebSiteResourceBundleProvider
{
  /**
   * @return A map from bundle ID to the serialized resource bundle for all contained bundles. Never
   *         <code>null</code> but maybe empty.
   */
  @NonNull
  @ReturnsMutableCopy
  ICommonsMap <String, WebSiteResourceBundleSerialized> getAllResourceBundles ();

  /**
   * @return A list with all contained serialized resource bundles. Never <code>null</code> but maybe
   *         empty.
   */
  @NonNull
  @ReturnsMutableCopy
  ICommonsList <WebSiteResourceBundleSerialized> getAllResourceBundlesSerialized ();

  /**
   * Get the serialized resource bundle with the passed ID.
   *
   * @param sBundleID
   *        The bundle ID to be resolved. May be <code>null</code>.
   * @return <code>null</code> if no such bundle exists.
   */
  @Nullable
  WebSiteResourceBundleSerialized getResourceBundleOfID (@Nullable String sBundleID);

  /**
   * Check if the passed resource bundle ID is contained.
   *
   * @param sBundleID
   *        The bundle ID to be checked. May be <code>null</code>.
   * @return <code>true</code> if the passed bundle exists, <code>false</code> otherwise.
   */
  boolean containsResourceBundleOfID (@Nullable String sBundleID);
}
