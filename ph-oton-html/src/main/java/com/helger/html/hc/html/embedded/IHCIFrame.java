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

import java.util.EnumSet;

import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.Nullable;

import com.helger.annotation.style.ReturnsMutableCopy;
import com.helger.html.hc.html.EHCScrolling;
import com.helger.html.hc.html.IHCElementWithChildren;
import com.helger.url.ISimpleURL;

/**
 * Interface for IFRAMEs
 *
 * @author Philip Helger
 * @param <IMPLTYPE>
 *        Implementation type
 */
public interface IHCIFrame <IMPLTYPE extends IHCIFrame <IMPLTYPE>> extends IHCElementWithChildren <IMPLTYPE>
{
  @Nullable
  ISimpleURL getSrc ();

  @NonNull
  IMPLTYPE setSrc (@Nullable ISimpleURL aSrc);

  @Nullable
  String getName ();

  @NonNull
  IMPLTYPE setName (@Nullable String sName);

  @Nullable
  String getLongDesc ();

  @NonNull
  IMPLTYPE setLongDesc (@Nullable String sLongDesc);

  @Nullable
  EHCScrolling getScrolling ();

  @NonNull
  IMPLTYPE setScrolling (@Nullable EHCScrolling eScrolling);

  @Nullable
  EHCIFrameAlign getAlign ();

  @NonNull
  IMPLTYPE setAlign (@Nullable EHCIFrameAlign eAlign);

  boolean isFrameBorder ();

  @NonNull
  IMPLTYPE setFrameBorder (boolean bFrameBorder);

  @Nullable
  String getWidth ();

  /**
   * Set the width in pixel
   *
   * @param nWidth
   *        the width in pixel
   * @return this
   */
  @NonNull
  IMPLTYPE setWidth (int nWidth);

  @NonNull
  IMPLTYPE setWidthPercentage (double dPercentage);

  @Nullable
  String getHeight ();

  @NonNull
  IMPLTYPE setHeight (int nHeight);

  @NonNull
  IMPLTYPE setHeightPercentage (double dPercentage);

  int getMarginWidth ();

  @NonNull
  IMPLTYPE setMarginWidth (int nMarginWidth);

  int getMarginHeight ();

  @NonNull
  IMPLTYPE setMarginHeight (int nMarginHeight);

  boolean isSandbox ();

  @NonNull
  @ReturnsMutableCopy
  EnumSet <EHCSandboxAllow> getSandboxAllow ();

  @NonNull
  IMPLTYPE setSandbox (boolean bSandbox, @Nullable EHCSandboxAllow... aSandboxAllows);
}
