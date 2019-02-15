/**
 * Copyright (C) 2014-2019 Philip Helger (www.helger.com)
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
package com.helger.photon.bootstrap3;

import javax.annotation.concurrent.Immutable;

import com.helger.commons.version.Version;

/**
 * Constants for usage with Bootstrap3
 *
 * @author Philip Helger
 */
@Immutable
public final class CBootstrap
{
  /** Bootstrap version */
  public static final Version BOOTSTRAP_VERSION_340 = new Version (3, 4, 1);

  /** The maximum number of columns a grid system can be separated into */
  public static final int GRID_SYSTEM_MAX = 12;

  private CBootstrap ()
  {}
}
