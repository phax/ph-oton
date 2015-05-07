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
package com.helger.photon.uicore;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Nonnull;
import javax.annotation.concurrent.Immutable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.helger.commons.regex.RegExHelper;
import com.helger.commons.string.StringHelper;
import com.helger.html.hc.IHCNode;
import com.helger.html.hc.html.HCDiv;
import com.helger.html.hc.impl.HCNodeList;
import com.helger.html.hc.impl.HCTextNode;

@Immutable
public final class UITextFormatter
{
  private static final Logger s_aLogger = LoggerFactory.getLogger (UITextFormatter.class);

  private UITextFormatter ()
  {}

  @Nonnull
  public static IHCNode getToStringContent (final Object aValue)
  {
    final String sOrigValue = String.valueOf (aValue);
    if (StringHelper.startsWith (sOrigValue, '[') && StringHelper.endsWith (sOrigValue, ']'))
    {
      try
      {
        final List <String> aParts = new ArrayList <String> ();
        String sValue = sOrigValue.substring (1, sOrigValue.length () - 1);

        final String [] aObjStart = RegExHelper.getAllMatchingGroupValues ("([\\[]*)([A-Za-z0-9_$]+@0x[0-9a-fA-F]{8})(?:: (.+))?",
                                                                           sValue);
        aParts.add (aObjStart[1]);
        if (aObjStart[2] != null)
        {
          sValue = StringHelper.getConcatenatedOnDemand (aObjStart[0], aObjStart[2]).trim ();
          if (sValue.length () > 0)
          {
            sValue = StringHelper.replaceAll (sValue, "; ", ";\n");
            aParts.addAll (StringHelper.getExploded ('\n', sValue));
          }
        }

        final HCNodeList ret = new HCNodeList ();
        for (final String s : aParts)
          ret.addChild (HCDiv.create (s));
        return ret;
      }
      catch (final Exception ex)
      {
        s_aLogger.error ("Failed to format", ex);
      }
    }
    return new HCTextNode (sOrigValue);
  }
}
