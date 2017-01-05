/**
 * Copyright (C) 2014-2017 Philip Helger (www.helger.com)
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
 * @param <THISTYPE>
 *        Implementation type
 */
public interface IHCObject <THISTYPE extends IHCObject <THISTYPE>> extends IHCElementWithChildren <THISTYPE>
{
  int getWidth ();

  @Nonnull
  THISTYPE setWidth (int nWidth);

  int getHeight ();

  @Nonnull
  THISTYPE setHeight (int nHeight);

  @Nullable
  String getHSpace ();

  @Nonnull
  THISTYPE setHSpace (@Nullable String sHSpace);

  @Nullable
  String getVSpace ();

  @Nonnull
  THISTYPE setVSpace (@Nullable String sVSpace);

  @Nullable
  EHCObjectAlign getAlign ();

  @Nonnull
  THISTYPE setAlign (@Nullable EHCObjectAlign eAlign);

  @Nullable
  String getArchive ();

  @Nonnull
  THISTYPE setArchive (@Nullable String sArchive);

  @Nullable
  String getBorder ();

  @Nonnull
  THISTYPE setBorder (@Nullable String sBorder);

  @Nullable
  String getClassID ();

  @Nonnull
  THISTYPE setClassID (@Nullable String sClassID);

  @Nullable
  ISimpleURL getCodeBase ();

  @Nonnull
  THISTYPE setCodeBase (@Nullable ISimpleURL aCodeBase);

  @Nullable
  IMimeType getCodeType ();

  @Nonnull
  THISTYPE setCodeType (@Nullable IMimeType aCodeType);

  @Nullable
  ISimpleURL getData ();

  @Nonnull
  THISTYPE setData (@Nullable ISimpleURL aData);

  boolean isDeclare ();

  @Nonnull
  THISTYPE setDeclare (boolean bDeclare);

  @Nullable
  String getName ();

  @Nonnull
  THISTYPE setName (@Nullable String sName);

  @Nullable
  String getStandBy ();

  @Nonnull
  THISTYPE setStandBy (@Nullable String sStandBy);

  @Nullable
  IMimeType getType ();

  @Nonnull
  THISTYPE setType (@Nullable IMimeType aType);

  @Nullable
  String getUseMap ();

  @Nonnull
  THISTYPE setUseMap (@Nullable String sUseMap);
}
