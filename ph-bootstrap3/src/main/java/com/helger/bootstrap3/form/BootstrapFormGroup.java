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
package com.helger.bootstrap3.form;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.concurrent.NotThreadSafe;

import com.helger.commons.annotations.OverrideOnDemand;
import com.helger.commons.idfactory.GlobalIDFactory;
import com.helger.commons.string.StringHelper;
import com.helger.html.hc.IHCNode;
import com.helger.html.hc.IHCNodeBuilder;
import com.helger.html.hc.html.HCDiv;
import com.helger.html.hc.impl.HCHasCSSClasses;
import com.helger.html.hc.impl.HCHasCSSStyles;
import com.helger.html.hc.impl.HCNodeList;
import com.helger.html.hc.impl.HCTextNode;
import com.helger.validation.error.IErrorList;
import com.helger.webctrls.custom.formlabel.ELabelType;
import com.helger.webctrls.custom.formlabel.HCFormLabel;

/**
 * Represents a single form group.
 *
 * @author Philip Helger
 */
@NotThreadSafe
public class BootstrapFormGroup
{
  private String m_sID;
  private HCHasCSSClasses m_aCSSClasses;
  private HCHasCSSStyles m_aCSSStyles;
  private HCFormLabel m_aLabel;
  private IHCNode m_aCtrl;
  private IHCNode m_aHelpText;
  private IErrorList m_aErrorList;
  private EBootstrapFormGroupState m_eState = EBootstrapFormGroupState.NONE;

  public BootstrapFormGroup ()
  {}

  @Nonnull
  public final BootstrapFormGroup ensureID ()
  {
    if (StringHelper.hasNoText (m_sID))
      m_sID = GlobalIDFactory.getNewStringID ();
    return this;
  }

  @Nonnull
  public final BootstrapFormGroup setID (@Nullable final String sFormGroupID)
  {
    m_sID = sFormGroupID;
    return this;
  }

  public boolean hasID ()
  {
    return StringHelper.hasText (m_sID);
  }

  @Nullable
  public String getID ()
  {
    return m_sID;
  }

  public boolean hasCSSClasses ()
  {
    return m_aCSSClasses != null;
  }

  @Nonnull
  public HCHasCSSClasses getCSSClasses ()
  {
    if (m_aCSSClasses == null)
      m_aCSSClasses = new HCHasCSSClasses ();
    return m_aCSSClasses;
  }

  public boolean hasCSSStyles ()
  {
    return m_aCSSStyles != null;
  }

  @Nonnull
  public HCHasCSSStyles getCSSStyles ()
  {
    if (m_aCSSStyles == null)
      m_aCSSStyles = new HCHasCSSStyles ();
    return m_aCSSStyles;
  }

  @Nonnull
  public final BootstrapFormGroup setLabel (@Nullable final String sLabel)
  {
    return setLabel (sLabel == null ? null : HCFormLabel.createOptional (sLabel));
  }

  @Nonnull
  public final BootstrapFormGroup setLabelMandatory (@Nullable final String sLabel)
  {
    return setLabel (sLabel == null ? null : HCFormLabel.createMandatory (sLabel));
  }

  @Nonnull
  public final BootstrapFormGroup setLabelAlternative (@Nullable final String sLabel)
  {
    return setLabel (sLabel == null ? null : HCFormLabel.createAlternative (sLabel));
  }

  @Nonnull
  public final BootstrapFormGroup setLabel (@Nullable final String sLabel, @Nonnull final ELabelType eLabelType)
  {
    return setLabel (sLabel == null ? null : new HCFormLabel (sLabel, eLabelType));
  }

  /**
   * Called after the label cell was altered.
   *
   * @param aLabel
   *        The newly set label. May be <code>null</code>
   */
  @OverrideOnDemand
  protected void onLabelModified (@Nullable final HCFormLabel aLabel)
  {}

  @Nonnull
  public final BootstrapFormGroup setLabel (@Nullable final HCFormLabel aLabel)
  {
    m_aLabel = aLabel;
    onLabelModified (aLabel);
    return this;
  }

  @Nullable
  public final HCFormLabel getLabel ()
  {
    return m_aLabel;
  }

  /**
   * Called after the control was changed
   *
   * @param aCtrl
   *        The new control. May be <code>null</code>.
   */
  @OverrideOnDemand
  protected void onCtrlModified (@Nullable final IHCNode aCtrl)
  {}

  @Nonnull
  public final BootstrapFormGroup setCtrl (@Nullable final String sValue)
  {
    final HCDiv aP = new HCDiv ();
    return setCtrl (aP.addChild (sValue));
  }

  @Nonnull
  public final BootstrapFormGroup setCtrl (@Nullable final String... aValues)
  {
    final HCNodeList aNodeList = new HCNodeList ();
    if (aValues != null)
      for (final String sValue : aValues)
      {
        final HCDiv aP = new HCDiv ();
        aNodeList.addChild (aP.addChild (sValue));
      }
    return setCtrl (aNodeList);
  }

  @Nonnull
  public final BootstrapFormGroup setCtrl (@Nullable final IHCNodeBuilder aCtrlBuilder)
  {
    return setCtrl (aCtrlBuilder == null ? null : aCtrlBuilder.build ());
  }

  @Nonnull
  public final BootstrapFormGroup setCtrl (@Nullable final IHCNode aCtrl)
  {
    m_aCtrl = aCtrl;
    onCtrlModified (aCtrl);
    return this;
  }

  @Nonnull
  public final BootstrapFormGroup setCtrl (@Nullable final IHCNode... aCtrls)
  {
    return setCtrl (HCNodeList.create (aCtrls));
  }

  @Nonnull
  public final BootstrapFormGroup setCtrl (@Nullable final Iterable <? extends IHCNode> aCtrls)
  {
    return setCtrl (HCNodeList.create (aCtrls));
  }

  @Nullable
  public final IHCNode getCtrl ()
  {
    return m_aCtrl;
  }

  @Nonnull
  public final BootstrapFormGroup setErrorList (@Nullable final IErrorList aErrorList)
  {
    m_aErrorList = aErrorList;
    return this;
  }

  @Nullable
  public final IErrorList getErrorList ()
  {
    return m_aErrorList;
  }

  /**
   * Called after the help text was altered.
   *
   * @param aNote
   *        The new note
   */
  @OverrideOnDemand
  protected void onHelpTextModified (@Nullable final IHCNode aNote)
  {}

  @Nonnull
  public final BootstrapFormGroup setHelpText (@Nullable final String sHelpText)
  {
    return setHelpText (HCTextNode.createOnDemand (sHelpText));
  }

  @Nonnull
  public final BootstrapFormGroup setHelpText (@Nullable final IHCNode aHelpText)
  {
    m_aHelpText = aHelpText;
    onHelpTextModified (aHelpText);
    return this;
  }

  @Nonnull
  public final BootstrapFormGroup setHelpText (@Nullable final String... aHelpTexts)
  {
    return setHelpText (new HCNodeList ().addChildren (aHelpTexts));
  }

  @Nonnull
  public final BootstrapFormGroup setHelpText (@Nullable final IHCNode... aHelpTexts)
  {
    return setHelpText (new HCNodeList ().addChildren (aHelpTexts));
  }

  @Nonnull
  public final BootstrapFormGroup setHelpText (@Nullable final Iterable <? extends IHCNode> aHelpTexts)
  {
    return setHelpText (new HCNodeList ().addChildren (aHelpTexts));
  }

  @Nullable
  public final IHCNode getHelpText ()
  {
    return m_aHelpText;
  }

  @Nonnull
  public final BootstrapFormGroup setState (@Nullable final EBootstrapFormGroupState eState)
  {
    m_eState = eState != null ? eState : EBootstrapFormGroupState.NONE;
    return this;
  }

  @Nonnull
  public EBootstrapFormGroupState getState ()
  {
    return m_eState;
  }
}
