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
package com.helger.html.hc;

import javax.annotation.Nullable;

/**
 * Special interface that adds dynamic elements to the conversion settings
 *
 * @author Philip Helger
 */
public interface IHCConversionSettingsToNode extends IHCConversionSettingsGlobal
{
  /**
   * @return The value of 'nonce' attribute for script elements. May be
   *         <code>null</code>.
   * @since 9.3.0
   */
  @Nullable
  String getNonceScript ();

  /**
   * @return The value of 'nonce' attribute for style elements. May be
   *         <code>null</code>.
   * @since 9.3.0
   */
  @Nullable
  String getNonceStyle ();
}
