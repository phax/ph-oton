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
package com.helger.photon.bootstrap3.uictrls.treeview;

import javax.annotation.Nonnull;
import javax.annotation.concurrent.NotThreadSafe;

import com.helger.commons.ValueEnforcer;
import com.helger.commons.annotation.ReturnsMutableCopy;
import com.helger.html.hc.IHCNode;
import com.helger.html.hc.render.HCRenderer;
import com.helger.html.jscode.IJSExpression;
import com.helger.html.jscode.JSAssocArray;
import com.helger.html.jscode.JSExpr;

@NotThreadSafe
public class BootstrapTreeViewItem
{
  private final IJSExpression m_aText;
  private boolean m_bSelectable = false;
  private boolean m_bDisabled = false;
  private boolean m_bChecked = false;

  public BootstrapTreeViewItem (@Nonnull final IHCNode aText)
  {
    this (HCRenderer.getAsHTMLStringWithoutNamespaces (aText));
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

  public void setSelectable (final boolean bSelectable)
  {
    m_bSelectable = bSelectable;
  }

  public boolean isSelectable ()
  {
    return m_bSelectable;
  }

  public void setDisabled (final boolean bDisabled)
  {
    m_bDisabled = bDisabled;
  }

  public boolean isDisabled ()
  {
    return m_bDisabled;
  }

  public void setChecked (final boolean bChecked)
  {
    m_bChecked = bChecked;
  }

  public boolean isChecked ()
  {
    return m_bChecked;
  }

  @Nonnull
  @ReturnsMutableCopy
  public JSAssocArray getAsJSAssocArray ()
  {
    final JSAssocArray ret = new JSAssocArray ();
    ret.add ("text", m_aText);
    if (m_bSelectable)
      ret.add ("selectable", true);
    if (m_bDisabled)
      ret.add ("disabled", true);
    if (m_bChecked)
      ret.add ("checked", true);
    return ret;
  }
}
