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
package com.helger.html.jscode.customize;

import java.util.Map;

import org.jspecify.annotations.NonNull;

import com.helger.base.id.factory.GlobalIDFactory;
import com.helger.html.EHTMLVersion;
import com.helger.html.hc.IHCHasChildrenMutable;
import com.helger.html.hc.IHCNode;
import com.helger.html.hc.html.IHCElement;
import com.helger.html.hc.html.script.HCScriptInline;
import com.helger.html.hc.html.script.IHCScript;
import com.helger.html.hc.impl.AbstractHCCustomizer;
import com.helger.html.js.CollectingJSCodeProvider;
import com.helger.html.js.EJSEvent;
import com.helger.html.js.JSEventMap;
import com.helger.html.jscode.JSAnonymousFunction;
import com.helger.html.jscode.JSConst;
import com.helger.html.jscode.JSPackage;
import com.helger.html.jscode.html.JSHtml;

public class HCCustomizerExternalizeAttrJS extends AbstractHCCustomizer
{
  public HCCustomizerExternalizeAttrJS ()
  {}

  @Override
  public void customizeNode (@NonNull final IHCNode aNode,
                             @NonNull final EHTMLVersion eHTMLVersion,
                             @NonNull final IHCHasChildrenMutable <?, ? super IHCNode> aTargetNode)
  {
    if (aNode instanceof IHCElement && !(aNode instanceof IHCScript <?>))
    {
      final IHCElement <?> aElement = (IHCElement <?>) aNode;
      final JSEventMap aEventMap = aElement.getEventMap ();
      if (aEventMap != null && !aEventMap.isEmpty ())
      {
        // Make sure the element has an ID
        aElement.ensureID ();

        final JSPackage aJS = new JSPackage ();
        if (aEventMap.getCount () == 1)
        {
          // Simpler code without explicit variable
          final Map.Entry <EJSEvent, CollectingJSCodeProvider> aEntry = aEventMap.getAllEventHandler ()
                                                                                 .getFirstEntry ();
          final JSAnonymousFunction aAnonFunction = new JSAnonymousFunction ();
          aAnonFunction.body ().add (aEntry.getValue ());
          aJS.add (JSHtml.documentGetElementById (aElement)
                         .invoke ("addEventListener")
                         .arg (aEntry.getKey ().getJSEventName ())
                         .arg (aAnonFunction));
        }
        else
        {
          // Remember element in variable
          final JSConst jsElem = aJS._const ("_elem" + GlobalIDFactory.getNewStringID (),
                                             JSHtml.documentGetElementById (aElement));

          // Convert all inline JS to addEventListener calls
          for (final Map.Entry <EJSEvent, CollectingJSCodeProvider> aEntry : aEventMap.getAllEventHandler ()
                                                                                      .entrySet ())
          {
            final JSAnonymousFunction aAnonFunction = new JSAnonymousFunction ();
            aAnonFunction.body ().add (aEntry.getValue ());
            aJS.add (jsElem.invoke ("addEventListener").arg (aEntry.getKey ().getJSEventName ()).arg (aAnonFunction));
          }
        }
        aEventMap.removeAll ();

        // add inline script node
        aTargetNode.addChild (new HCScriptInline (aJS));
      }
    }
  }
}
