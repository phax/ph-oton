/*
 * Copyright (C) 2014-2026 Philip Helger (www.helger.com)
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
package com.helger.html.hc.html.embedded;

import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.Nullable;

import com.helger.html.hc.html.IHCElementWithChildren;
import com.helger.mime.IMimeType;
import com.helger.url.ISimpleURL;

/**
 * Interface for OBJECTs
 *
 * @author Philip Helger
 * @param <IMPLTYPE>
 *        Implementation type
 */
public interface IHCObject <IMPLTYPE extends IHCObject <IMPLTYPE>> extends IHCElementWithChildren <IMPLTYPE>
{
  int getWidth ();

  @NonNull
  IMPLTYPE setWidth (int nWidth);

  int getHeight ();

  @NonNull
  IMPLTYPE setHeight (int nHeight);

  @Nullable
  String getHSpace ();

  @NonNull
  IMPLTYPE setHSpace (@Nullable String sHSpace);

  @Nullable
  String getVSpace ();

  @NonNull
  IMPLTYPE setVSpace (@Nullable String sVSpace);

  @Nullable
  EHCObjectAlign getAlign ();

  @NonNull
  IMPLTYPE setAlign (@Nullable EHCObjectAlign eAlign);

  @Nullable
  String getArchive ();

  @NonNull
  IMPLTYPE setArchive (@Nullable String sArchive);

  @Nullable
  String getBorder ();

  @NonNull
  IMPLTYPE setBorder (@Nullable String sBorder);

  @Nullable
  String getClassID ();

  @NonNull
  IMPLTYPE setClassID (@Nullable String sClassID);

  @Nullable
  ISimpleURL getCodeBase ();

  @NonNull
  IMPLTYPE setCodeBase (@Nullable ISimpleURL aCodeBase);

  @Nullable
  IMimeType getCodeType ();

  @NonNull
  IMPLTYPE setCodeType (@Nullable IMimeType aCodeType);

  @Nullable
  ISimpleURL getData ();

  @NonNull
  IMPLTYPE setData (@Nullable ISimpleURL aData);

  boolean isDeclare ();

  @NonNull
  IMPLTYPE setDeclare (boolean bDeclare);

  @Nullable
  String getName ();

  @NonNull
  IMPLTYPE setName (@Nullable String sName);

  @Nullable
  String getStandBy ();

  @NonNull
  IMPLTYPE setStandBy (@Nullable String sStandBy);

  @Nullable
  IMimeType getType ();

  @NonNull
  IMPLTYPE setType (@Nullable IMimeType aType);

  @Nullable
  String getUseMap ();

  @NonNull
  IMPLTYPE setUseMap (@Nullable String sUseMap);
}
