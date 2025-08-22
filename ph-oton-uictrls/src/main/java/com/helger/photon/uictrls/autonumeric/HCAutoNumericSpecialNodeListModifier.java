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
package com.helger.photon.uictrls.autonumeric;

import java.util.Iterator;

import com.helger.annotation.style.ReturnsMutableCopy;
import com.helger.collection.commons.CommonsArrayList;
import com.helger.collection.commons.ICommonsList;
import com.helger.html.hc.IHCNode;
import com.helger.html.hc.html.script.HCScriptInlineOnDocumentReady;
import com.helger.html.hc.special.IHCSpecialNodeListModifier;
import com.helger.html.jquery.IJQuerySelector;
import com.helger.html.jquery.JQuerySelector;
import com.helger.html.js.CollectingJSCodeProvider;
import com.helger.html.jscode.JSAssocArray;

import jakarta.annotation.Nonnull;

public final class HCAutoNumericSpecialNodeListModifier implements IHCSpecialNodeListModifier
{
  @Nonnull
  @ReturnsMutableCopy
  public ICommonsList <? extends IHCNode> modifySpecialNodes (@Nonnull final ICommonsList <? extends IHCNode> aNodes)
  {
    final ICommonsList <IHCNode> ret = new CommonsArrayList <> ();
    final ICommonsList <HCAutoNumericJS> aDTPs = new CommonsArrayList <> ();
    int nFirstIndex = -1;
    int nIndex = 0;
    for (final IHCNode aNode : aNodes)
    {
      if (aNode instanceof HCAutoNumericJS)
      {
        aDTPs.add ((HCAutoNumericJS) aNode);
        if (nFirstIndex < 0)
          nFirstIndex = nIndex;
      }
      else
        ret.add (aNode);
      nIndex++;
    }

    if (aDTPs.size () <= 1)
    {
      // Nothing to merge
      return aNodes;
    }

    final CollectingJSCodeProvider aMergedJS = new CollectingJSCodeProvider ();
    final ICommonsList <HCAutoNumericJS> aRest = aDTPs.getClone ();
    while (aRest.isNotEmpty ())
    {
      final HCAutoNumericJS aCurrent = aRest.remove (0);
      final AbstractHCAutoNumeric <?> aCurrentAutoNumeric = aCurrent.getAutoNumeric ();
      final JSAssocArray aCurrentJSOptions = aCurrentAutoNumeric.getJSOptions ();

      // Find all other auto numerics with the same options
      final ICommonsList <HCAutoNumericJS> aSameOptions = new CommonsArrayList <> ();
      final Iterator <HCAutoNumericJS> itRest = aRest.iterator ();
      while (itRest.hasNext ())
      {
        final HCAutoNumericJS aCurrentRest = itRest.next ();
        final AbstractHCAutoNumeric <?> aCurrentRestAutoNumeric = aCurrentRest.getAutoNumeric ();
        if (aCurrentRestAutoNumeric.getJSOptions ().equals (aCurrentJSOptions))
        {
          aSameOptions.add (aCurrentRest);
          itRest.remove ();
        }
      }

      if (aSameOptions.isEmpty ())
      {
        // No other object has the same options
        aMergedJS.append (aCurrent.getOnDocumentReadyCode ());
      }
      else
      {
        // We have multiple objects with the same options
        // Create a common selector
        IJQuerySelector aJQI = JQuerySelector.id (aCurrentAutoNumeric);
        for (final HCAutoNumericJS aSameOption : aSameOptions)
          aJQI = aJQI.multiple (JQuerySelector.id (aSameOption.getAutoNumeric ()));
        // And apply once
        aMergedJS.append (HCAutoNumericJS.createInitCode (aJQI.invoke (), aCurrentAutoNumeric));
      }
    }

    // Add at the first index, where it was in the source list
    ret.add (nFirstIndex, new HCScriptInlineOnDocumentReady (aMergedJS));
    return ret;
  }
}
