/**
 * Copyright (C) 2014-2020 Philip Helger (www.helger.com)
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

import javax.annotation.Nonnull;

import com.helger.commons.id.factory.GlobalIDFactory;
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
import com.helger.html.jscode.JSFunction;

public class HCCustomizerExternalizeAttrJS extends AbstractHCCustomizer
{
  public HCCustomizerExternalizeAttrJS ()
  {}

  @Override
  public void customizeNode (@Nonnull final IHCNode aNode,
                             @Nonnull final EHTMLVersion eHTMLVersion,
                             @Nonnull final IHCHasChildrenMutable <?, ? super IHCNode> aTargetNode)
  {
    if (aNode instanceof IHCElement && !(aNode instanceof IHCScript <?>))
    {
      final JSEventMap aEventMap = ((IHCElement <?>) aNode).getEventMap ();
      if (aEventMap != null)
        for (final Map.Entry <EJSEvent, CollectingJSCodeProvider> aEntry : aEventMap.getAllEventHandler ().entrySet ())
        {
          // "ag" for "automatically generated"
          final JSFunction aFunc = new JSFunction ("_photon_ag" + GlobalIDFactory.getNewIntID ());
          aFunc.body ().add (aEntry.getValue ());
          aTargetNode.addChild (new HCScriptInline (aFunc));
          aEventMap.setHandler (aEntry.getKey (), aFunc.invoke ());
        }
    }
  }
}
