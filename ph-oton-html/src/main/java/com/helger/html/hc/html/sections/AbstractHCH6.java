/**
 * Copyright (C) 2014-2018 Philip Helger (www.helger.com)
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
 * Represents an HTML &lt;H6&gt; element
 *
 * @author Philip Helger
 * @param <IMPLTYPE>
 *        Implementation type
 */
public abstract class AbstractHCH6 <IMPLTYPE extends AbstractHCH6 <IMPLTYPE>> extends
                                   AbstractHCElementWithChildren <IMPLTYPE>
{
  /**
   * Create a new H6 element
   */
  public AbstractHCH6 ()
  {
    super (EHTMLElement.H6);
  }

  @Override
  protected void onConsistencyCheck (@Nonnull final IHCConversionSettingsToNode aConversionSettings)
  {
    super.onConsistencyCheck (aConversionSettings);
    if (HCHTMLHelper.recursiveContainsChildWithDifferentTagName (this,
                                                                 EHTMLElement.H1,
                                                                 EHTMLElement.H2,
                                                                 EHTMLElement.H4,
                                                                 EHTMLElement.H5,
                                                                 EHTMLElement.H5,
                                                                 EHTMLElement.H6))
      HCConsistencyChecker.consistencyError ("H6 contains other nested header");
  }
}
