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
package com.helger.html.hc.html.sections;

import javax.annotation.Nonnull;

import com.helger.html.EHTMLElement;
import com.helger.html.hc.IHCConversionSettingsToNode;
import com.helger.html.hc.config.HCConsistencyChecker;
import com.helger.html.hc.html.AbstractHCElementWithChildren;
import com.helger.html.hc.html.HCHTMLHelper;

/**
 * Represents an HTML &lt;H1&gt; element
 *
 * @author Philip Helger
 * @param <IMPLTYPE>
 *        Implementation type
 */
public abstract class AbstractHCH1 <IMPLTYPE extends AbstractHCH1 <IMPLTYPE>> extends AbstractHCElementWithChildren <IMPLTYPE>
{
  /**
   * Create a new H1 element
   */
  public AbstractHCH1 ()
  {
    super (EHTMLElement.H1);
  }

  @Override
  protected void onConsistencyCheck (@Nonnull final IHCConversionSettingsToNode aConversionSettings)
  {
    super.onConsistencyCheck (aConversionSettings);
    if (HCHTMLHelper.recursiveContainsChildWithTagName (this,
                                                        EHTMLElement.H1,
                                                        EHTMLElement.H2,
                                                        EHTMLElement.H4,
                                                        EHTMLElement.H5,
                                                        EHTMLElement.H5,
                                                        EHTMLElement.H6))
      HCConsistencyChecker.consistencyError ("H1 contains other nested header");
  }
}
