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
package com.helger.html.hc.html.embedded;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.helger.commons.url.ISimpleURL;
import com.helger.html.hc.html.IHCElementWithInternalChildren;
import com.helger.html.hc.html.IHCMediaElementChild;

/**
 * Interface for media elements
 *
 * @author Philip Helger
 * @param <IMPLTYPE>
 *        Implementation type
 */
public interface IHCMediaElement <IMPLTYPE extends IHCMediaElement <IMPLTYPE>> extends
                                 IHCElementWithInternalChildren <IMPLTYPE, IHCMediaElementChild <?>>
{
  boolean isAutoPlay ();

  @Nonnull
  IMPLTYPE setAutoPlay (final boolean bAutoPlay);

  @Nullable
  EHCPreload getPreload ();

  @Nonnull
  IMPLTYPE setPreload (@Nullable EHCPreload ePreload);

  boolean isControls ();

  @Nonnull
  IMPLTYPE setControls (final boolean bControls);

  boolean isLoop ();

  @Nonnull
  IMPLTYPE setLoop (final boolean bLoop);

  boolean isMuted ();

  @Nonnull
  IMPLTYPE setMuted (final boolean bMuted);

  @Nullable
  ISimpleURL getSrc ();

  @Nonnull
  IMPLTYPE setSrc (@Nullable ISimpleURL aSrc);

  @Nullable
  EHCCORSSettings getCrossOrigin ();

  @Nonnull
  IMPLTYPE setCrossOrigin (@Nullable EHCCORSSettings eCrossOrigin);

  @Nonnull
  IMPLTYPE addSource (@Nullable HCSource aSource);

  @Nonnull
  IMPLTYPE addTrack (@Nullable HCTrack aTrack);
}
