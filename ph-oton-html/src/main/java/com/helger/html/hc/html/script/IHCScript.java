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
package com.helger.html.hc.html.script;

import java.nio.charset.Charset;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.helger.commons.mime.IMimeType;
import com.helger.html.hc.html.IHCElement;
import com.helger.html.hc.html.embedded.EHCCORSSettings;

/**
 * Interface for SCRIPTs
 *
 * @author Philip Helger
 * @param <IMPLTYPE>
 *        Implementation type
 */
public interface IHCScript <IMPLTYPE extends IHCScript <IMPLTYPE>> extends IHCElement <IMPLTYPE>
{
  @Nonnull
  IMimeType getType ();

  @Nonnull
  IMPLTYPE setType (@Nonnull IMimeType aType);

  @Nullable
  String getCharset ();

  @Nonnull
  default IMPLTYPE setCharset (@Nullable final Charset aCharset)
  {
    return setCharset (aCharset == null ? null : aCharset.name ());
  }

  @Nonnull
  IMPLTYPE setCharset (@Nullable String sCharset);

  @Nullable
  EHCCORSSettings getCrossOrigin ();

  @Nonnull
  IMPLTYPE setCrossOrigin (@Nullable EHCCORSSettings eCrossOrigin);

  @Nullable
  String getIntegrity ();

  @Nonnull
  IMPLTYPE setIntegrity (@Nullable String sIntegrity);
}
