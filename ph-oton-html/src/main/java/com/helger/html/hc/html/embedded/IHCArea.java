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
package com.helger.html.hc.html.embedded;

import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.Nullable;

import com.helger.html.hc.html.HC_Target;
import com.helger.html.hc.html.IHCElement;
import com.helger.html.hc.html.links.EHCReferrerPolicy;
import com.helger.url.ISimpleURL;

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

  @NonNull
  IMPLTYPE setAlt (@Nullable String sAlt);

  @Nullable
  String getCoords ();

  @NonNull
  IMPLTYPE setCoords (@Nullable String sCoords);

  @Nullable
  String getShape ();

  @NonNull
  IMPLTYPE setShape (@Nullable String sShape);

  @Nullable
  ISimpleURL getHref ();

  @NonNull
  IMPLTYPE setHref (@NonNull ISimpleURL aHref);

  @Nullable
  HC_Target getTarget ();

  default boolean hasTarget ()
  {
    return getTarget () != null;
  }

  @NonNull
  IMPLTYPE setTarget (@Nullable HC_Target aTarget);

  @NonNull
  default IMPLTYPE setTargetBlank ()
  {
    return setTarget (HC_Target.BLANK);
  }

  @Nullable
  String getDownload ();

  @NonNull
  IMPLTYPE setDownload (@Nullable String sDownload);

  @Nullable
  ISimpleURL getPing ();

  @NonNull
  IMPLTYPE setPing (@Nullable ISimpleURL aPing);

  @Nullable
  String getRel ();

  @NonNull
  IMPLTYPE setRel (@Nullable String sRel);

  @Nullable
  EHCReferrerPolicy getReferrerPolicy ();

  @NonNull
  IMPLTYPE setReferrerPolicy (@Nullable EHCReferrerPolicy eReferrerPolicy);
}
