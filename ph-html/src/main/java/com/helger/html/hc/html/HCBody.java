/**
 * Copyright (C) 2014-2015 Philip Helger (www.helger.com)
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
package com.helger.html.hc.html;

import javax.annotation.Nonnull;
import javax.annotation.OverridingMethodsMustInvokeSuper;

import com.helger.commons.annotations.OverrideOnDemand;
import com.helger.commons.microdom.IMicroElement;
import com.helger.commons.microdom.IMicroNode;
import com.helger.html.EHTMLElement;
import com.helger.html.hc.conversion.HCConsistencyChecker;
import com.helger.html.hc.conversion.IHCConversionSettingsToNode;
import com.helger.html.hc.impl.AbstractHCElementWithChildren;

/**
 * Represents an HTML &lt;body&gt; element
 *
 * @author Philip Helger
 */
public class HCBody extends AbstractHCElementWithChildren <HCBody>
{
  public HCBody ()
  {
    super (EHTMLElement.BODY);
  }

  @Override
  protected IMicroNode internalConvertToNode (@Nonnull final IHCConversionSettingsToNode aConversionSettings)
  {
    final IMicroNode ret = super.internalConvertToNode (aConversionSettings);

    // Check if only unique IDs are used
    if (aConversionSettings.areConsistencyChecksEnabled ())
      HCConsistencyChecker.checkForUniqueIDs (this);

    return ret;
  }

  @Override
  @OverrideOnDemand
  @OverridingMethodsMustInvokeSuper
  protected void applyProperties (@Nonnull final IMicroElement eHead,
                                  @Nonnull final IHCConversionSettingsToNode aConversionSettings)
  {
    super.applyProperties (eHead, aConversionSettings);
  }
}
