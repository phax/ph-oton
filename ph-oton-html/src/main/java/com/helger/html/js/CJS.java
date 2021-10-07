/*
 * Copyright (C) 2014-2021 Philip Helger (www.helger.com)
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
package com.helger.html.js;

import javax.annotation.concurrent.Immutable;

import com.helger.commons.annotation.PresentForCodeCoverage;
import com.helger.commons.url.EURLProtocol;

/**
 * JavaScript constants.
 *
 * @author Philip Helger
 */
@Immutable
public final class CJS
{
  /** The JavaScript prefix "javascript:" */
  public static final String JS_PREFIX = EURLProtocol.JAVASCRIPT.getProtocol ();
  public static final String JS_NULL = "null";

  /** The void statement "javascript:void(0);" */
  public static final String JS_VOID = JS_PREFIX + "void(0);";

  /** Regular JS file extension */
  public static final String FILE_EXTENSION_JS = ".js";

  /** Minified JS file extension */
  public static final String FILE_EXTENSION_MIN_JS = ".min.js";

  @PresentForCodeCoverage
  private static final CJS INSTANCE = new CJS ();

  private CJS ()
  {}
}
