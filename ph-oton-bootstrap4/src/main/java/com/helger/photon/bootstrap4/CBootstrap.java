/*
 * Copyright (C) 2018-2025 Philip Helger (www.helger.com)
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
package com.helger.photon.bootstrap4;

import com.helger.annotation.concurrent.Immutable;
import com.helger.base.version.Version;

/**
 * Constants for usage with Bootstrap4
 *
 * @author Philip Helger
 */
@Immutable
public final class CBootstrap
{
  /** Bootstrap version */
  public static final Version BOOTSTRAP_VERSION_46 = new Version (4, 6, 2);

  /** The maximum number of columns a grid system can be separated into */
  public static final int GRID_SYSTEM_MAX = 12;

  private CBootstrap ()
  {}
}
