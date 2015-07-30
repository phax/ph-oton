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
package com.helger.photon.bootstrap3;

import javax.annotation.Nullable;
import javax.annotation.concurrent.Immutable;

import com.helger.commons.annotation.PresentForCodeCoverage;
import com.helger.html.hcapi.IHCNode;
import com.helger.html.hchtml.HCHTMLHelper;
import com.helger.html.hchtml.IHCControl;
import com.helger.html.hchtml.IHCElement;
import com.helger.html.hchtml.base.IHCScript;
import com.helger.html.hchtml.impl.HCCheckBox;
import com.helger.html.hchtml.impl.HCHiddenField;
import com.helger.html.hchtml.impl.HCRadioButton;

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
      if (!(aCtrl instanceof HCCheckBox) && !(aCtrl instanceof HCRadioButton) && !(aCtrl instanceof HCHiddenField))
      {
        // input, select and textarea except for checkbox, radio button and
        // hidden field
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
        markAsFormControls (HCHTMLHelper.getAllHCControls (aChild));
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
    if (aNode instanceof IHCElement <?>)
    {
      if (!(aNode instanceof IHCControl <?>) && !(aNode instanceof IHCScript <?>))
      {
        ((IHCElement <?>) aNode).addClass (CBootstrapCSS.FORM_CONTROL_STATIC);
      }
    }
    else
      // Descend only in non-elements
      if (aNode != null)
      {
        // E.g. HCNodeList
        if (aNode.hasChildren ())
          for (final IHCNode aChild : aNode.getAllChildren ())
            makeFormControlStatic (aChild);
      }
  }
}
