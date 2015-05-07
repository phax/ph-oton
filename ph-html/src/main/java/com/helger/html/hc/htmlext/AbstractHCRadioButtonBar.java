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
package com.helger.html.hc.htmlext;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.helger.commons.collections.CollectionHelper;
import com.helger.html.hc.IHCNode;
import com.helger.html.hc.html.AbstractHCSpan;
import com.helger.html.hc.html.HCRadioButton;
import com.helger.html.hc.impl.HCTextNode;

public abstract class AbstractHCRadioButtonBar extends AbstractHCSpan <AbstractHCRadioButtonBar>
{
  private final String m_sName;
  private final List <HCRadioButton> m_aButtons = new ArrayList <HCRadioButton> ();

  protected AbstractHCRadioButtonBar (@Nullable final String sName)
  {
    m_sName = sName;
  }

  @Nullable
  protected abstract IHCNode getSeparator ();

  @Nonnull
  public final AbstractHCRadioButtonBar addRadioButton (final int nValue, final String sLabel, final boolean bChecked)
  {
    return addRadioButton (Integer.toString (nValue), sLabel, bChecked);
  }

  @Nonnull
  public final AbstractHCRadioButtonBar addRadioButton (final String sValue, final String sLabel, final boolean bChecked)
  {
    return addRadioButton (sValue, HCTextNode.createOnDemand (sLabel), bChecked);
  }

  @Nonnull
  public final AbstractHCRadioButtonBar addRadioButton (final String sValue,
                                                        final IHCNode aLabel,
                                                        final boolean bChecked)
  {
    final HCRadioButton aButton = new HCRadioButton (m_sName, sValue, bChecked);
    if (hasChildren ())
      addChild (getSeparator ());
    addChild (aButton);
    addChild (aLabel);
    m_aButtons.add (aButton);
    return this;
  }

  @Nullable
  public final HCRadioButton getRadioButtonAtIndex (final int nIndex)
  {
    return CollectionHelper.getSafe (m_aButtons, nIndex);
  }
}
