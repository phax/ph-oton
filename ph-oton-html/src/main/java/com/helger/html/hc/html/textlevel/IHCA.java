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
package com.helger.html.hc.html.textlevel;

import com.helger.html.hc.IHCHasName;
import com.helger.html.hc.html.HC_Target;
import com.helger.html.hc.html.IHCElementWithChildren;
import com.helger.html.hc.html.IHCHasMedia;
import com.helger.html.hc.html.links.EHCReferrerPolicy;
import com.helger.html.js.EJSEvent;
import com.helger.html.js.IHasJSCode;
import com.helger.mime.IMimeType;
import com.helger.url.ISimpleURL;

import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;

/**
 * Interface for As
 *
 * @author Philip Helger
 * @param <IMPLTYPE>
 *        Implementation type
 */
public interface IHCA <IMPLTYPE extends IHCA <IMPLTYPE>> extends
                      IHCElementWithChildren <IMPLTYPE>,
                      IHCHasMedia <IMPLTYPE>,
                      IHCHasName <IMPLTYPE>
{
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
  IMimeType getType ();

  @Nonnull
  IMPLTYPE setType (@Nullable IMimeType aType);

  @Nullable
  EHCReferrerPolicy getReferrerPolicy ();

  @Nonnull
  IMPLTYPE setReferrerPolicy (@Nullable EHCReferrerPolicy eReferrerPolicy);

  /**
   * Shortcut for <code>setEventHandler(EJSEvent.ONCLICK, aOnClick)</code>
   *
   * @param aOnClick
   *        JS event to trigger
   * @return this
   */
  @Nonnull
  default IMPLTYPE setOnClick (@Nullable final IHasJSCode aOnClick)
  {
    return setEventHandler (EJSEvent.CLICK, aOnClick);
  }

  /**
   * Shortcut for <code>addEventHandler(EJSEvent.ONCLICK, aOnClick)</code>
   *
   * @param aOnClick
   *        JS event to trigger
   * @return this
   */
  @Nonnull
  default IMPLTYPE addOnClick (@Nullable final IHasJSCode aOnClick)
  {
    return addEventHandler (EJSEvent.CLICK, aOnClick);
  }
}
