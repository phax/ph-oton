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
package com.helger.bootstrap3;

import javax.annotation.Nullable;
import javax.annotation.concurrent.Immutable;

import com.helger.commons.annotations.PresentForCodeCoverage;
import com.helger.html.hc.IHCControl;
import com.helger.html.hc.IHCElement;
import com.helger.html.hc.IHCHasChildren;
import com.helger.html.hc.IHCNode;
import com.helger.html.hc.html.AbstractHCScript;
import com.helger.html.hc.html.HCCheckBox;
import com.helger.html.hc.html.HCHiddenField;
import com.helger.html.hc.html.HCRadioButton;
import com.helger.html.hc.htmlext.HCUtils;

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

  public static void markChildrenAsFormControls (@Nullable final IHCHasChildren aParent)
  {
    if (aParent != null)
      for (final IHCNode aChild : aParent.getAllChildren ())
        markAsFormControls (HCUtils.getAllHCControls (aChild));
  }

  public static boolean containsFormControlStatic (@Nullable final IHCNode aNode)
  {
    if (aNode instanceof IHCElement <?>)
    {
      if (!(aNode instanceof IHCControl <?>) && !(aNode instanceof AbstractHCScript <?>))
        return true;
      return false;
    }

    // Decend only in non-elements
    if (aNode instanceof IHCHasChildren)
    {
      // E.g. HCNodeList
      final IHCHasChildren aParent = (IHCHasChildren) aNode;
      if (aParent.hasChildren ())
        for (final IHCNode aChild : aParent.getAllChildren ())
          if (containsFormControlStatic (aChild))
            return true;
    }

    return false;
  }

  public static void makeFormControlStatic (@Nullable final IHCNode aNode)
  {
    if (aNode instanceof IHCElement <?>)
    {
      if (!(aNode instanceof IHCControl <?>) && !(aNode instanceof AbstractHCScript <?>))
      {
        ((IHCElement <?>) aNode).addClass (CBootstrapCSS.FORM_CONTROL_STATIC);
      }
    }
    else
      // Decend only in non-elements
      if (aNode instanceof IHCHasChildren)
      {
        // E.g. HCNodeList
        final IHCHasChildren aParent = (IHCHasChildren) aNode;
        if (aParent.hasChildren ())
          for (final IHCNode aChild : aParent.getAllChildren ())
            makeFormControlStatic (aChild);
      }
  }
}
