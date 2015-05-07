/**
 * Copyright (C) 2014-2015 Philip Helger (www.helger.com)
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
package com.helger.html.js.provider;

import javax.annotation.Nullable;

import com.helger.commons.annotations.MustImplementEqualsAndHashcode;
import com.helger.html.js.IJSCodeProvider;
import com.helger.html.js.writer.IJSWriterSettings;

/**
 * Basic interface for object providing JavaScript code.
 *
 * @author Philip Helger
 */
@MustImplementEqualsAndHashcode
public interface IJSCodeProviderWithSettings extends IJSCodeProvider
{
  /**
   * @param aSettings
   *        The formatter settings to be used. May be <code>null</code> for the
   *        default settings.
   * @return The JavaScript code representation. May be <code>null</code> to
   *         indicate no JS code.
   */
  @Nullable
  String getJSCode (@Nullable IJSWriterSettings aSettings);
}
