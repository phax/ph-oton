/**
 * Copyright (C) 2014-2016 Philip Helger (www.helger.com)
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
import com.helger.html.hc.html.IHCElement;
import com.helger.html.hc.html.forms.AbstractHCInput;
import com.helger.html.hc.html.forms.HCCtrlHelper;
import com.helger.html.hc.html.forms.IHCControl;
import com.helger.html.hc.html.script.IHCScript;

@Immutable
public final class BootstrapHelper
{
  @PresentForCodeCoverage
  private static final BootstrapHelper s_aInstance = new BootstrapHelper ();

  private BootstrapHelper ()
  {}

  public static void markAsFormControl (@Nullable final IHCControl <?> aCtrl)
  {
    if (aCtrl != null)
    {
      if (aCtrl instanceof AbstractHCInput <?>)
      {
        // all except for checkbox, radio button and
        // hidden field
        final AbstractHCInput <?> aInput = (AbstractHCInput <?>) aCtrl;
        switch (aInput.getType ())
        {
          case CHECKBOX:
          case RADIO:
          case HIDDEN:
            // Do not add the class!
            break;
          default:
            aCtrl.addClass (CBootstrapCSS.FORM_CONTROL);
            break;
        }
      }
      else
      {
        aCtrl.addClass (CBootstrapCSS.FORM_CONTROL);
      }
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
      for (final IHCNode aChild : aParent.getAllChildren ())
        markAsFormControls (HCCtrlHelper.getAllHCControls (aChild));
  }

  public static boolean containsFormControlStatic (@Nullable final IHCNode aNode)
  {
    if (aNode instanceof IHCElement <?>)
    {
      if (!(aNode instanceof IHCControl <?>) && !(aNode instanceof IHCScript <?>))
        return true;
      return false;
    }

    // Descend only in non-elements
    if (aNode != null)
    {
      // E.g. HCNodeList
      if (aNode.hasChildren ())
        for (final IHCNode aChild : aNode.getAllChildren ())
          if (containsFormControlStatic (aChild))
            return true;
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
        if (aNode.hasChildren ())
          for (final IHCNode aChild : aNode.getAllChildren ())
            makeFormControlStatic (aChild);
      }
    }
  }
}
