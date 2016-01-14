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
package com.helger.photon.uicore.facebook;

import javax.annotation.concurrent.Immutable;

/**
 * Facebook SDK constants
 *
 * @author Philip Helger
 */
@Immutable
public final class CFacebook
{
  /** The namespace URI for OpenGraph elements */
  public static final String OPENGRAPH_NAMESPACE_URI = "http://ogp.me/ns#";

  /** The preferred namespace prefix for OpenGraph nodes */
  public static final String OPENGRAPH_PREFIX = "og";

  /** The OpenGraph Facebook specific namespace URI */
  public static final String FACEBOOK_NAMESPACE_URI = "http://ogp.me/ns/fb#";

  /** The preferred namespace prefix for Facebook nodes */
  public static final String FACEBOOK_PREFIX = "fb";

  /**
   * Date time format used for e.g. last modification object property
   */
  public static final String FACEBOOK_DATE_TIME_FORMAT = "yyyy-MM-dd'T'HH:mm:ssZ";

  private CFacebook ()
  {}
}
