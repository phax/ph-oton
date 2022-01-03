/*
 * Copyright (C) 2014-2022 Philip Helger (www.helger.com)
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
package com.helger.html.hc.ext;

import javax.annotation.Nonnull;

import com.helger.commons.hashcode.HashCodeGenerator;
import com.helger.commons.state.EContinue;
import com.helger.commons.wrapper.Wrapper;
import com.helger.html.EHTMLVersion;
import com.helger.html.hc.HCHelper;
import com.helger.html.hc.IHCHasChildrenMutable;
import com.helger.html.hc.IHCNode;
import com.helger.html.hc.html.forms.HCHiddenField;
import com.helger.html.hc.html.forms.IHCControl;
import com.helger.html.hc.html.forms.IHCForm;
import com.helger.html.hc.html.forms.IHCHasFocus;
import com.helger.html.hc.impl.AbstractHCCustomizer;

/**
 * Special customizer that customizes the first input element on the page (if
 * available).
 *
 * @author Philip Helger
 */
public class HCCustomizerAutoFocusFirstCtrl extends AbstractHCCustomizer
{
  public HCCustomizerAutoFocusFirstCtrl ()
  {}

  public static void setAutoFocusOnFirstControl (@Nonnull final IHCNode aStartNode)
  {
    final Wrapper <IHCHasFocus <?>> aFirstCtrl = new Wrapper <> ();

    HCHelper.iterateChildrenNoCopy (aStartNode, (aParentNode, aChildNode) -> {
      if (aChildNode instanceof IHCControl <?>)
      {
        final IHCControl <?> aCurCtrl = (IHCControl <?>) aChildNode;
        if (aCurCtrl.isAutoFocus ())
        {
          // No need to continue because an existing control already has the
          // focus
          return EContinue.BREAK;
        }

        if (!aFirstCtrl.isSet ())
        {
          if (aCurCtrl instanceof HCHiddenField)
          {
            // cannot focus hidden field
          }
          else
            if (aCurCtrl.isReadOnly () || aCurCtrl.isDisabled ())
            {
              // cannot focus read-only/disabled controls
            }
            else
              aFirstCtrl.set (aCurCtrl);
        }
      }

      return EContinue.CONTINUE;
    });

    // Anything to focus?
    final IHCHasFocus <?> aFirst = aFirstCtrl.get ();
    if (aFirst != null)
      aFirst.setAutoFocus (true);
  }

  @Override
  public void customizeNode (@Nonnull final IHCNode aNode,
                             @Nonnull final EHTMLVersion eHTMLVersion,
                             @Nonnull final IHCHasChildrenMutable <?, ? super IHCNode> aTargetNode)
  {
    if (aNode instanceof IHCForm <?>)
      setAutoFocusOnFirstControl (aNode);
  }

  @Override
  public boolean equals (final Object o)
  {
    if (o == this)
      return true;
    if (o == null || !getClass ().equals (o.getClass ()))
      return false;
    return true;
  }

  @Override
  public int hashCode ()
  {
    return new HashCodeGenerator (this).getHashCode ();
  }
}
