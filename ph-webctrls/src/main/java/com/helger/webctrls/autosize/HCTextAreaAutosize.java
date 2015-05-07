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
package com.helger.webctrls.autosize;

import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.helger.html.hc.IHCHasChildrenMutable;
import com.helger.html.hc.html.HCTextArea;
import com.helger.html.request.IHCRequestField;
import com.helger.webbasics.app.html.PerRequestJSIncludes;
import com.helger.webctrls.EUICtrlsJSPathProvider;

/**
 * jQuery autosize plugin from
 *
 * <pre>
 * http://www.jacklmoore.com/autosize
 * </pre>
 *
 * @author Philip Helger
 */
public class HCTextAreaAutosize extends HCTextArea
{
  public HCTextAreaAutosize (@Nullable final String sName)
  {
    super (sName);
  }

  public HCTextAreaAutosize (@Nullable final String sName, @Nullable final String sValue)
  {
    super (sName, sValue);
  }

  public HCTextAreaAutosize (@Nonnull final IHCRequestField aRF)
  {
    super (aRF);
  }

  @Override
  public void onAdded (@Nonnegative final int nIndex, @Nonnull final IHCHasChildrenMutable <?, ?> aParent)
  {
    super.onAdded (nIndex, aParent);
    registerExternalResources ();
  }

  public static void registerExternalResources ()
  {
    PerRequestJSIncludes.registerJSIncludeForThisRequest (EUICtrlsJSPathProvider.AUTOSIZE);
    PerRequestJSIncludes.registerJSIncludeForThisRequest (EUICtrlsJSPathProvider.AUTOSIZE_ALL);
  }
}
