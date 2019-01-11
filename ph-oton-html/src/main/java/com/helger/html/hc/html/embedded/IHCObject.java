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
package com.helger.html.hc.html.embedded;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.helger.commons.mime.IMimeType;
import com.helger.commons.url.ISimpleURL;
import com.helger.html.hc.html.IHCElementWithChildren;

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

  @Nonnull
  IMPLTYPE setWidth (int nWidth);

  int getHeight ();

  @Nonnull
  IMPLTYPE setHeight (int nHeight);

  @Nullable
  String getHSpace ();

  @Nonnull
  IMPLTYPE setHSpace (@Nullable String sHSpace);

  @Nullable
  String getVSpace ();

  @Nonnull
  IMPLTYPE setVSpace (@Nullable String sVSpace);

  @Nullable
  EHCObjectAlign getAlign ();

  @Nonnull
  IMPLTYPE setAlign (@Nullable EHCObjectAlign eAlign);

  @Nullable
  String getArchive ();

  @Nonnull
  IMPLTYPE setArchive (@Nullable String sArchive);

  @Nullable
  String getBorder ();

  @Nonnull
  IMPLTYPE setBorder (@Nullable String sBorder);

  @Nullable
  String getClassID ();

  @Nonnull
  IMPLTYPE setClassID (@Nullable String sClassID);

  @Nullable
  ISimpleURL getCodeBase ();

  @Nonnull
  IMPLTYPE setCodeBase (@Nullable ISimpleURL aCodeBase);

  @Nullable
  IMimeType getCodeType ();

  @Nonnull
  IMPLTYPE setCodeType (@Nullable IMimeType aCodeType);

  @Nullable
  ISimpleURL getData ();

  @Nonnull
  IMPLTYPE setData (@Nullable ISimpleURL aData);

  boolean isDeclare ();

  @Nonnull
  IMPLTYPE setDeclare (boolean bDeclare);

  @Nullable
  String getName ();

  @Nonnull
  IMPLTYPE setName (@Nullable String sName);

  @Nullable
  String getStandBy ();

  @Nonnull
  IMPLTYPE setStandBy (@Nullable String sStandBy);

  @Nullable
  IMimeType getType ();

  @Nonnull
  IMPLTYPE setType (@Nullable IMimeType aType);

  @Nullable
  String getUseMap ();

  @Nonnull
  IMPLTYPE setUseMap (@Nullable String sUseMap);
}
