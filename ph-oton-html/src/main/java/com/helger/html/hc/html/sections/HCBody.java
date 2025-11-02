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

import org.jspecify.annotations.NonNull;

import com.helger.annotation.OverridingMethodsMustInvokeSuper;
import com.helger.annotation.style.OverrideOnDemand;
import com.helger.base.string.StringHelper;
import com.helger.collection.commons.CommonsHashSet;
import com.helger.collection.commons.ICommonsSet;
import com.helger.html.EHTMLElement;
import com.helger.html.hc.HCHelper;
import com.helger.html.hc.IHCConversionSettingsToNode;
import com.helger.html.hc.IHCHasID;
import com.helger.html.hc.config.HCConsistencyChecker;
import com.helger.html.hc.html.AbstractHCElementWithChildren;
import com.helger.html.hc.html.IHCElement;
import com.helger.xml.microdom.IMicroElement;

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
  protected void onConsistencyCheck (@NonNull final IHCConversionSettingsToNode aConversionSettings)
  {
    super.onConsistencyCheck (aConversionSettings);

    final ICommonsSet <String> aUsedIDs = new CommonsHashSet <> ();
    HCHelper.iterateTreeNonBreakable (this, (aParentNode, aChildNode) -> {
      if (aChildNode instanceof IHCHasID <?>)
      {
        final IHCHasID <?> aElement = (IHCHasID <?>) aChildNode;
        final String sID = aElement.getID ();
        if (StringHelper.isNotEmpty (sID) && !aUsedIDs.add (sID))
        {
          HCConsistencyChecker.consistencyError ("The ID '" +
                                                 sID +
                                                 "' is used more than once within a single HTML page!" +
                                                 (aElement instanceof IHCElement <?> ? " The second usage is at an '" +
                                                                                       ((IHCElement <?>) aElement).getTagName () +
                                                                                       "' element"
                                                                                     : ""));
        }
      }
    });
  }

  @Override
  @OverrideOnDemand
  @OverridingMethodsMustInvokeSuper
  protected void fillMicroElement (@NonNull final IMicroElement eHead, @NonNull final IHCConversionSettingsToNode aConversionSettings)
  {
    super.fillMicroElement (eHead, aConversionSettings);
  }
}
