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

import com.helger.annotation.concurrent.Immutable;
import com.helger.photon.app.CAppTelemetry;
import com.helger.telemetry.TelemetryAttributes;

/**
 * Emits the ph-telemetry metrics for the web site resource cache and the resource bundling. All
 * emission happens through the vendor neutral ph-telemetry facades, so without a registered SPI
 * everything degrades to cheap no-ops.<br>
 * Neither a resource path nor a content hash is ever used as an attribute.
 *
 * @author Philip Helger
 * @since 10.6.0
 */
@Immutable
final class WebSiteResourceTelemetry
{
  private WebSiteResourceTelemetry ()
  {}

  /**
   * Count one access to the web site resource cache.
   *
   * @param eResourceType
   *        The type of the requested resource. May not be <code>null</code>.
   * @param bHit
   *        <code>true</code> if the resource was served from the cache. An access while the cache
   *        is disabled counts as a miss.
   */
  static void onCacheAccess (@NonNull final EWebSiteResourceType eResourceType, final boolean bHit)
  {
    WebSiteResourceMetrics.CACHE_ACCESS.add (1,
                                             TelemetryAttributes.builder ()
                                                                .put (CAppTelemetry.ATTR_RESOURCE_TYPE,
                                                                      eResourceType.getID ())
                                                                .put (CAppTelemetry.ATTR_RESOURCE_CACHE_HIT, bHit)
                                                                .build ());
  }

  /**
   * Count one newly created resource bundle.
   */
  static void onBundleCreated ()
  {
    WebSiteResourceMetrics.BUNDLES_CREATED.add (1);
  }

  /**
   * Count one persisted resource bundle that was skipped on startup, because at least one contained
   * resource is missing or changed.
   */
  static void onBundleSkipped ()
  {
    WebSiteResourceMetrics.BUNDLES_SKIPPED.add (1);
  }
}
