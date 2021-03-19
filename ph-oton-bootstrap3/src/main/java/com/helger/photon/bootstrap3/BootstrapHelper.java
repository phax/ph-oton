/**
 * Copyright (C) 2014-2021 Philip Helger (www.helger.com)
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
package com.helger.photon.bootstrap3;

import javax.annotation.Nullable;
import javax.annotation.concurrent.Immutable;

import com.helger.commons.annotation.PresentForCodeCoverage;
import com.helger.html.hc.IHCNode;
import com.helger.html.hc.IHCTextNode;
import com.helger.html.hc.html.IHCElement;
import com.helger.html.hc.html.forms.EHCInputType;
import com.helger.html.hc.html.forms.HCCtrlHelper;
import com.helger.html.hc.html.forms.IHCControl;
import com.helger.html.hc.html.forms.IHCInput;
import com.helger.html.hc.html.script.IHCScript;

@Immutable
public final class BootstrapHelper
{
  @PresentForCodeCoverage
  private static final BootstrapHelper INSTANCE = new BootstrapHelper ();

  private BootstrapHelper ()
  {}

  public static void markAsFormControl (@Nullable final IHCControl <?> aCtrl)
  {
    if (aCtrl != null)
    {
      boolean bAddClass = true;
      if (aCtrl instanceof IHCInput <?>)
      {
        // all except for checkbox, radio button and
        // hidden field
        final IHCInput <?> aInput = (IHCInput <?>) aCtrl;

        // May be null!
        final EHCInputType eType = aInput.getType ();
        if (eType == EHCInputType.CHECKBOX || eType == EHCInputType.RADIO || eType == EHCInputType.HIDDEN)
        {
          // Do not add the class!
          bAddClass = false;
        }
      }

      if (bAddClass)
        aCtrl.addClass (CBootstrapCSS.FORM_CONTROL);
    }
  }

  public static void markAsFormControls (@Nullable final Iterable <? extends IHCControl <?>> aCtrls)
  {
    if (aCtrls != null)
      for (final IHCControl <?> aCurCtrl : aCtrls)
        markAsFormControl (aCurCtrl);
  }

  public static void markChildrenAsFormControls (@Nullable final IHCNode aParent)
  {
    if (aParent != null)
      aParent.forAllChildren (aChild -> markAsFormControls (HCCtrlHelper.getAllHCControls (aChild)));
  }

  public static boolean containsFormControlStatic (@Nullable final IHCNode aNode)
  {
    if (aNode instanceof IHCTextNode <?>)
      return true;

    if (aNode instanceof IHCElement <?>)
    {
      return !(aNode instanceof IHCControl <?>) && !(aNode instanceof IHCScript <?>);
    }

    // Descend only in non-elements
    if (aNode != null)
    {
      // E.g. HCNodeList
      return aNode.findFirstChild (BootstrapHelper::containsFormControlStatic) != null;
    }

    return false;
  }

  public static void makeFormControlStatic (@Nullable final IHCNode aNode)
  {
    if (aNode != null)
    {
      if (aNode instanceof IHCElement <?>)
      {
        if (!(aNode instanceof IHCControl <?>) && !(aNode instanceof IHCScript <?>))
          ((IHCElement <?>) aNode).addClass (CBootstrapCSS.FORM_CONTROL_STATIC);
      }
      else
      {
        // Descend only in non-elements - e.g. HCNodeList
        aNode.forAllChildren (BootstrapHelper::makeFormControlStatic);
      }
    }
  }
}
