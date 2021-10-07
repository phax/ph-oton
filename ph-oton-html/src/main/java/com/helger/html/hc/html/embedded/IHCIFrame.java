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

import java.util.EnumSet;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.helger.commons.annotation.ReturnsMutableCopy;
import com.helger.commons.url.ISimpleURL;
import com.helger.html.hc.html.EHCScrolling;
import com.helger.html.hc.html.IHCElementWithChildren;

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

  @Nonnull
  IMPLTYPE setSrc (@Nullable ISimpleURL aSrc);

  @Nullable
  String getName ();

  @Nonnull
  IMPLTYPE setName (@Nullable String sName);

  @Nullable
  String getLongDesc ();

  @Nonnull
  IMPLTYPE setLongDesc (@Nullable String sLongDesc);

  @Nullable
  EHCScrolling getScrolling ();

  @Nonnull
  IMPLTYPE setScrolling (@Nullable EHCScrolling eScrolling);

  @Nullable
  EHCIFrameAlign getAlign ();

  @Nonnull
  IMPLTYPE setAlign (@Nullable EHCIFrameAlign eAlign);

  boolean isFrameBorder ();

  @Nonnull
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
  @Nonnull
  IMPLTYPE setWidth (int nWidth);

  @Nonnull
  IMPLTYPE setWidthPercentage (double dPercentage);

  @Nullable
  String getHeight ();

  @Nonnull
  IMPLTYPE setHeight (int nHeight);

  @Nonnull
  IMPLTYPE setHeightPercentage (double dPercentage);

  int getMarginWidth ();

  @Nonnull
  IMPLTYPE setMarginWidth (int nMarginWidth);

  int getMarginHeight ();

  @Nonnull
  IMPLTYPE setMarginHeight (int nMarginHeight);

  boolean isSandbox ();

  @Nonnull
  @ReturnsMutableCopy
  EnumSet <EHCSandboxAllow> getSandboxAllow ();

  @Nonnull
  IMPLTYPE setSandbox (boolean bSandbox, @Nullable EHCSandboxAllow... aSandboxAllows);
}
