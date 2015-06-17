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
package com.helger.photon.uicore.html.table;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.helger.commons.annotations.OverrideOnDemand;
import com.helger.html.hc.IHCCell;
import com.helger.html.hc.IHCNode;
import com.helger.html.hc.IHCNodeBuilder;
import com.helger.html.hc.html.HCRow;
import com.helger.html.hc.impl.HCNodeList;
import com.helger.html.hc.impl.HCTextNode;
import com.helger.photon.uicore.html.formlabel.HCFormLabel;

@Deprecated
public class HCTableFormViewItemRow extends HCRow
{
  private final IHCCell <?> m_aLabelCell;
  private final IHCCell <?> m_aCtrlCell;

  public HCTableFormViewItemRow (final boolean bHeader)
  {
    super (bHeader);
    m_aLabelCell = addCell ();
    m_aCtrlCell = addCell ();
  }

  @Nonnull
  public HCTableFormViewItemRow setLabel (@Nullable final String sLabel)
  {
    return setLabel (sLabel == null ? null : HCFormLabel.createOptional (sLabel));
  }

  /**
   * Called after the label cell was altered
   */
  @OverrideOnDemand
  protected void onLabelModified ()
  {}

  @Nonnull
  public HCTableFormViewItemRow setLabel (@Nullable final HCFormLabel aLabel)
  {
    m_aLabelCell.removeAllChildren ().addChild (aLabel);
    onLabelModified ();
    return this;
  }

  @Nonnull
  public IHCCell <?> getLabelCell ()
  {
    return m_aLabelCell;
  }

  @Nonnull
  public HCTableFormViewItemRow setCtrl (@Nullable final String sValue)
  {
    return setCtrl (new HCTextNode (sValue));
  }

  @Nonnull
  public HCTableFormViewItemRow setCtrl (@Nullable final String... aValues)
  {
    return setCtrl (HCNodeList.create (aValues));
  }

  @Nonnull
  public HCTableFormViewItemRow setCtrl (@Nullable final IHCNodeBuilder aCtrlBuilder)
  {
    return setCtrl (aCtrlBuilder == null ? null : aCtrlBuilder.build ());
  }

  /**
   * Called after the control cell was altered
   */
  @OverrideOnDemand
  protected void onCtrlsModified ()
  {}

  @Nonnull
  public HCTableFormViewItemRow setCtrl (@Nullable final IHCNode aCtrl)
  {
    m_aCtrlCell.removeAllChildren ().addChild (aCtrl);
    onCtrlsModified ();
    return this;
  }

  @Nonnull
  public HCTableFormViewItemRow setCtrl (@Nullable final IHCNode... aCtrls)
  {
    m_aCtrlCell.removeAllChildren ().addChildren (aCtrls);
    onCtrlsModified ();
    return this;
  }

  @Nonnull
  public HCTableFormViewItemRow setCtrl (@Nullable final Iterable <? extends IHCNode> aCtrls)
  {
    m_aCtrlCell.removeAllChildren ().addChildren (aCtrls);
    onCtrlsModified ();
    return this;
  }

  @Nonnull
  public IHCCell <?> getCtrlCell ()
  {
    return m_aCtrlCell;
  }
}
