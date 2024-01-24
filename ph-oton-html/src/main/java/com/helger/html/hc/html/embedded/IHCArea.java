/*
 * Copyright (C) 2014-2024 Philip Helger (www.helger.com)
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

import com.helger.commons.url.ISimpleURL;
import com.helger.html.hc.html.HC_Target;
import com.helger.html.hc.html.IHCElement;
import com.helger.html.hc.html.links.EHCReferrerPolicy;

/**
 * Interface for AREAs
 *
 * @author Philip Helger
 * @param <IMPLTYPE>
 *        Implementation type
 */
public interface IHCArea <IMPLTYPE extends IHCArea <IMPLTYPE>> extends IHCElement <IMPLTYPE>
{
  @Nullable
  String getAlt ();

  @Nonnull
  IMPLTYPE setAlt (@Nullable String sAlt);

  @Nullable
  String getCoords ();

  @Nonnull
  IMPLTYPE setCoords (@Nullable String sCoords);

  @Nullable
  String getShape ();

  @Nonnull
  IMPLTYPE setShape (@Nullable String sShape);

  @Nullable
  ISimpleURL getHref ();

  @Nonnull
  IMPLTYPE setHref (@Nonnull ISimpleURL aHref);

  @Nullable
  HC_Target getTarget ();

  default boolean hasTarget ()
  {
    return getTarget () != null;
  }

  @Nonnull
  IMPLTYPE setTarget (@Nullable HC_Target aTarget);

  @Nonnull
  default IMPLTYPE setTargetBlank ()
  {
    return setTarget (HC_Target.BLANK);
  }

  @Nullable
  String getDownload ();

  @Nonnull
  IMPLTYPE setDownload (@Nullable String sDownload);

  @Nullable
  ISimpleURL getPing ();

  @Nonnull
  IMPLTYPE setPing (@Nullable ISimpleURL aPing);

  @Nullable
  String getRel ();

  @Nonnull
  IMPLTYPE setRel (@Nullable String sRel);

  @Nullable
  EHCReferrerPolicy getReferrerPolicy ();

  @Nonnull
  IMPLTYPE setReferrerPolicy (@Nullable EHCReferrerPolicy eReferrerPolicy);
}
