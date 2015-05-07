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
package com.helger.bootstrap3.treeview;

import javax.annotation.Nonnull;

import com.helger.commons.ValueEnforcer;
import com.helger.commons.annotations.ReturnsMutableCopy;
import com.helger.html.hc.IHCNode;
import com.helger.html.hc.conversion.HCSettings;
import com.helger.html.js.builder.IJSExpression;
import com.helger.html.js.builder.JSAssocArray;
import com.helger.html.js.builder.JSExpr;

public class BootstrapTreeViewItem
{
  private final IJSExpression m_aText;

  public BootstrapTreeViewItem (@Nonnull final IHCNode aText)
  {
    this (HCSettings.getAsHTMLStringWithoutNamespaces (aText));
  }

  public BootstrapTreeViewItem (@Nonnull final String sText)
  {
    this (JSExpr.lit (sText));
  }

  public BootstrapTreeViewItem (@Nonnull final IJSExpression aText)
  {
    m_aText = ValueEnforcer.notNull (aText, "Text");
  }

  @Nonnull
  public IJSExpression getText ()
  {
    return m_aText;
  }

  @Nonnull
  @ReturnsMutableCopy
  public JSAssocArray getAsJSAssocArray ()
  {
    final JSAssocArray ret = new JSAssocArray ();
    ret.add ("text", m_aText);
    return ret;
  }
}
