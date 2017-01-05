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
 * @param <THISTYPE>
 *        Implementation type
 */
public interface IHCIFrame <THISTYPE extends IHCIFrame <THISTYPE>> extends IHCElementWithChildren <THISTYPE>
{
  @Nullable
  ISimpleURL getSrc ();

  @Nonnull
  THISTYPE setSrc (@Nullable ISimpleURL aSrc);

  @Nullable
  String getName ();

  @Nonnull
  THISTYPE setName (@Nullable String sName);

  @Nullable
  String getLongDesc ();

  @Nonnull
  THISTYPE setLongDesc (@Nullable String sLongDesc);

  @Nullable
  EHCScrolling getScrolling ();

  @Nonnull
  THISTYPE setScrolling (@Nullable EHCScrolling eScrolling);

  @Nullable
  EHCIFrameAlign getAlign ();

  @Nonnull
  THISTYPE setAlign (@Nullable EHCIFrameAlign eAlign);

  boolean isFrameBorder ();

  @Nonnull
  THISTYPE setFrameBorder (boolean bFrameBorder);

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
  THISTYPE setWidth (int nWidth);

  @Nonnull
  THISTYPE setWidthPercentage (double dPercentage);

  @Nullable
  String getHeight ();

  @Nonnull
  THISTYPE setHeight (int nHeight);

  @Nonnull
  THISTYPE setHeightPercentage (double dPercentage);

  int getMarginWidth ();

  @Nonnull
  THISTYPE setMarginWidth (int nMarginWidth);

  int getMarginHeight ();

  @Nonnull
  THISTYPE setMarginHeight (int nMarginHeight);

  boolean isSandbox ();

  @Nonnull
  @ReturnsMutableCopy
  EnumSet <EHCSandboxAllow> getSandboxAllow ();

  @Nonnull
  THISTYPE setSandbox (boolean bSandbox, @Nullable EHCSandboxAllow... aSandboxAllows);
}
