/*
 * Copyright (C) 2014-2025 Philip Helger (www.helger.com)
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
package com.helger.photon.core.appid;

import com.helger.annotation.concurrent.Immutable;

/**
 * This class contains application IDs for the most common use cases
 *
 * @author Philip Helger
 */
@Immutable
public final class CApplicationID
{
  /** The default application ID to be used for the public application */
  public static final String APP_ID_PUBLIC = "public";
  /** The default application ID to be used for the secure application */
  public static final String APP_ID_SECURE = "secure";

  private CApplicationID ()
  {}
}
