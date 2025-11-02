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
package com.helger.html.hc.html.script;

import java.nio.charset.Charset;

import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.Nullable;

import com.helger.html.hc.html.IHCElement;
import com.helger.html.hc.html.embedded.EHCCORSSettings;
import com.helger.mime.IMimeType;

/**
 * Interface for SCRIPTs
 *
 * @author Philip Helger
 * @param <IMPLTYPE>
 *        Implementation type
 */
public interface IHCScript <IMPLTYPE extends IHCScript <IMPLTYPE>> extends IHCElement <IMPLTYPE>
{
  String TYPE_IMPORT_MAP = "importmap";
  String TYPE_MODULE = "module";

  @Nullable
  String getType ();

  @NonNull
  IMPLTYPE setType (@Nullable String sType);

  @NonNull
  default IMPLTYPE setType (@NonNull final IMimeType aType)
  {
    return setType (aType.getAsString ());
  }

  @NonNull
  default IMPLTYPE setTypeImportMap ()
  {
    return setType (TYPE_IMPORT_MAP);
  }

  @NonNull
  default IMPLTYPE setTypeModule ()
  {
    return setType (TYPE_MODULE);
  }

  @Nullable
  String getCharset ();

  @NonNull
  default IMPLTYPE setCharset (@Nullable final Charset aCharset)
  {
    return setCharset (aCharset == null ? null : aCharset.name ());
  }

  @NonNull
  IMPLTYPE setCharset (@Nullable String sCharset);

  @Nullable
  EHCCORSSettings getCrossOrigin ();

  @NonNull
  IMPLTYPE setCrossOrigin (@Nullable EHCCORSSettings eCrossOrigin);

  @Nullable
  String getIntegrity ();

  @NonNull
  IMPLTYPE setIntegrity (@Nullable String sIntegrity);
}
