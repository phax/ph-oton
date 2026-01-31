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
package com.helger.html.hc.html.tabular;

import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.Nullable;

import com.helger.annotation.Nonnegative;
import com.helger.css.ECSSUnit;
import com.helger.html.CHTMLAttributeValues;

/**
 * Represents an HTML &lt;col&gt; element
 *
 * @author Philip Helger
 */
public class HCCol extends AbstractHCCol <HCCol>
{
  public HCCol ()
  {
    super ();
  }

  public HCCol (@Nonnegative final int nWidth)
  {
    super (nWidth);
  }

  /**
   * @return A new "star" column (<code>&lt;col width="*" /&gt;</code>). Never
   *         <code>null</code>.
   */
  @NonNull
  public static HCCol star ()
  {
    return new HCCol ().setWidth (CHTMLAttributeValues.STAR);
  }

  /**
   * Create a new column with a certain percentage.
   *
   * @param nPerc
   *        The percentage to be used. Should ideally be between 0 and 100.
   * @return Never <code>null</code>.
   */
  @NonNull
  public static HCCol perc (@Nonnegative final int nPerc)
  {
    return new HCCol ().setWidth (ECSSUnit.perc (nPerc));
  }

  /**
   * Create a new column with an arbitrary width definition.
   *
   * @param sWidth
   *        The width to be used. May be <code>null</code>.
   * @return Never <code>null</code>.
   */
  @NonNull
  public static HCCol fromString (@Nullable final String sWidth)
  {
    return new HCCol ().setWidth (sWidth);
  }
}
