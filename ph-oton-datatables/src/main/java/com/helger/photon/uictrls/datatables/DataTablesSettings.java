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
package com.helger.photon.uictrls.datatables;

import javax.annotation.concurrent.NotThreadSafe;

@NotThreadSafe
public final class DataTablesSettings
{
  public static final boolean DEFAULT_GENERATE_ON_DOCUMENT_READY = false;

  private static boolean s_bDefaultGenerateOnDocumentReady = DEFAULT_GENERATE_ON_DOCUMENT_READY;

  private DataTablesSettings ()
  {}

  public static boolean isDefaultGenerateOnDocumentReady ()
  {
    return s_bDefaultGenerateOnDocumentReady;
  }

  public static void setDefaultGenerateOnDocumentReady (final boolean bGenerateOnDocumentReady)
  {
    s_bDefaultGenerateOnDocumentReady = bGenerateOnDocumentReady;
  }
}
