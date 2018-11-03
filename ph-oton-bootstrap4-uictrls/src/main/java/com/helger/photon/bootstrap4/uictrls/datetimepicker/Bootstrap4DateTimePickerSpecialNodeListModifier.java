/**
 * Copyright (C) 2018 Philip Helger (www.helger.com)
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
package com.helger.photon.bootstrap4.uictrls.datetimepicker;

import java.util.Iterator;

import javax.annotation.Nonnull;

import com.helger.commons.collection.impl.CommonsArrayList;
import com.helger.commons.collection.impl.ICommonsList;
import com.helger.html.hc.IHCNode;
import com.helger.html.hc.html.script.HCScriptInline;
import com.helger.html.hc.special.IHCSpecialNodeListModifier;
import com.helger.html.jquery.IJQuerySelector;
import com.helger.html.jquery.JQuerySelector;
import com.helger.html.js.CollectingJSCodeProvider;
import com.helger.html.jscode.JSAssocArray;

public final class Bootstrap4DateTimePickerSpecialNodeListModifier implements IHCSpecialNodeListModifier
{
  public ICommonsList <? extends IHCNode> modifySpecialNodes (@Nonnull final ICommonsList <? extends IHCNode> aNodes)
  {
    final ICommonsList <IHCNode> ret = new CommonsArrayList <> ();
    final ICommonsList <Bootstrap4DateTimePickerJS> aDTPs = new CommonsArrayList <> ();
    int nFirstIndex = -1;
    int nIndex = 0;
    for (final IHCNode aNode : aNodes)
    {
      if (aNode instanceof Bootstrap4DateTimePickerJS)
      {
        aDTPs.add ((Bootstrap4DateTimePickerJS) aNode);
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
    final ICommonsList <Bootstrap4DateTimePickerJS> aRest = aDTPs.getClone ();
    while (aRest.isNotEmpty ())
    {
      final Bootstrap4DateTimePickerJS aCurrent = aRest.removeFirst ();
      final JSAssocArray aCurrentJSOptions = aCurrent.getDateTimePicker ().getJSOptions ();

      // Find all other date time pickers with the same options
      final ICommonsList <Bootstrap4DateTimePickerJS> aSameOptions = new CommonsArrayList <> ();
      final Iterator <Bootstrap4DateTimePickerJS> itRest = aRest.iterator ();
      while (itRest.hasNext ())
      {
        final Bootstrap4DateTimePickerJS aCurrentRest = itRest.next ();
        if (aCurrentRest.getDateTimePicker ().getJSOptions ().equals (aCurrentJSOptions))
        {
          aSameOptions.add (aCurrentRest);
          itRest.remove ();
        }
      }

      if (aSameOptions.isEmpty ())
      {
        // No other object has the same options
        aMergedJS.append (aCurrent.getJSCodeProvider ());
      }
      else
      {
        // We have multiple objects with the same options
        // Create a common selector
        IJQuerySelector aJQI = JQuerySelector.id (aCurrent.getDateTimePicker ());
        for (final Bootstrap4DateTimePickerJS aSameOption : aSameOptions)
          aJQI = aJQI.multiple (JQuerySelector.id (aSameOption.getDateTimePicker ()));
        // And apply once
        aMergedJS.append (BootstrapDateTimePicker.invoke (aJQI.invoke (), aCurrentJSOptions));
      }
    }

    // Add at the first index, where it was in the source list
    ret.add (nFirstIndex, new HCScriptInline (aMergedJS));
    return ret;
  }
}
