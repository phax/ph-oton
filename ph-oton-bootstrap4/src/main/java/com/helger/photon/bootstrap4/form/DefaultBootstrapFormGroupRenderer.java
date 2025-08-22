/*
 * Copyright (C) 2018-2025 Philip Helger (www.helger.com)
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
package com.helger.photon.bootstrap4.form;

import java.util.Locale;

import com.helger.annotation.concurrent.NotThreadSafe;
import com.helger.annotation.style.OverrideOnDemand;
import com.helger.collection.commons.CommonsArrayList;
import com.helger.collection.commons.ICommonsList;
import com.helger.diagnostics.error.IError;
import com.helger.diagnostics.error.list.IErrorList;
import com.helger.html.hc.IHCNode;
import com.helger.html.hc.html.IHCElement;
import com.helger.html.hc.html.IHCElementWithChildren;
import com.helger.html.hc.html.forms.AbstractHCCheckBox;
import com.helger.html.hc.html.forms.AbstractHCRadioButton;
import com.helger.html.hc.html.forms.EHCInputType;
import com.helger.html.hc.html.forms.HCCtrlHelper;
import com.helger.html.hc.html.forms.IHCControl;
import com.helger.html.hc.html.forms.IHCInput;
import com.helger.html.hc.html.forms.IHCTextArea;
import com.helger.html.hc.html.grouping.AbstractHCDiv;
import com.helger.html.hc.html.grouping.HCDiv;
import com.helger.html.hc.impl.HCNodeList;
import com.helger.photon.bootstrap4.CBootstrapCSS;
import com.helger.photon.bootstrap4.grid.BootstrapRow;
import com.helger.photon.uicore.html.formlabel.HCFormLabel;

import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;

/**
 * This is the default implementation of {@link IBootstrapFormGroupRenderer}
 * which performs standard rendering. It offers the possibility to modify
 * certain styling by overriding the provided protected methods.
 *
 * @author Philip Helger
 */
@NotThreadSafe
public class DefaultBootstrapFormGroupRenderer implements IBootstrapFormGroupRenderer
{
  private boolean m_bUseIcons = false;
  private boolean m_bForceNoCheckBoxHandling = false;

  public DefaultBootstrapFormGroupRenderer ()
  {}

  @Override
  public boolean isUseIcons ()
  {
    return m_bUseIcons;
  }

  @Override
  @Nonnull
  public DefaultBootstrapFormGroupRenderer setUseIcons (final boolean bUseIcons)
  {
    m_bUseIcons = bUseIcons;
    return this;
  }

  public boolean isForceNoCheckBoxHandling ()
  {
    return m_bForceNoCheckBoxHandling;
  }

  @Nonnull
  public DefaultBootstrapFormGroupRenderer setForceNoCheckBoxHandling (final boolean bForceNoCheckBoxHandling)
  {
    m_bForceNoCheckBoxHandling = bForceNoCheckBoxHandling;
    return this;
  }

  /**
   * Modify the first control that is inserted. This method is only called when
   * a label is present.
   *
   * @param aLabel
   *        The label that was provided. Never <code>null</code>.
   * @param aFirstControl
   *        The first control that was provided. Never <code>null</code>.
   */
  @OverrideOnDemand
  protected void modifyFirstControlIfLabelIsPresent (@Nonnull final IHCElementWithChildren <?> aLabel,
                                                     @Nonnull final IHCControl <?> aFirstControl)
  {
    // Set the default placeholder (if none is present)
    if (aFirstControl instanceof IHCInput <?>)
    {
      final IHCInput <?> aEdit = (IHCInput <?>) aFirstControl;
      final EHCInputType eType = aEdit.getType ();
      if (eType != null && eType.hasPlaceholder () && !aEdit.hasPlaceholder ())
        aEdit.setPlaceholder (BootstrapFormHelper.getDefaultPlaceholderText (aLabel));
    }
    else
      if (aFirstControl instanceof IHCTextArea <?>)
      {
        final IHCTextArea <?> aTextArea = (IHCTextArea <?>) aFirstControl;
        if (!aTextArea.hasPlaceholder ())
          aTextArea.setPlaceholder (BootstrapFormHelper.getDefaultPlaceholderText (aLabel));
      }
  }

  /**
   * Create the help text node
   *
   * @param aHelpText
   *        The source help text. Never <code>null</code>.
   * @return Never <code>null</code>.
   */
  @Nonnull
  @OverrideOnDemand
  protected IHCElementWithChildren <?> createHelpTextNode (@Nonnull final IHCNode aHelpText)
  {
    return BootstrapFormHelper.createDefaultHelpTextNode (aHelpText);
  }

  /**
   * Create the node for a single error.
   *
   * @param aError
   *        The provided error. Never <code>null</code>.
   * @param aContentLocale
   *        Locale to be used to show error text.
   * @return May be <code>null</code>.
   */
  @Nullable
  @OverrideOnDemand
  protected IHCElement <?> createSingleErrorNode (@Nonnull final IError aError, @Nonnull final Locale aContentLocale)
  {
    return BootstrapFormHelper.createDefaultErrorNode (aError, aContentLocale);
  }

  /**
   * Callback possibility to change the finally created node before it is
   * returned. By default nothing happens in here.
   *
   * @param aForm
   *        The source form. Never <code>null</code>.
   * @param aFormGroup
   *        The source form group. Never <code>null</code>.
   * @param aFinalNode
   *        The created node so far. Never <code>null</code>.
   */
  @OverrideOnDemand
  protected void modifyFinalNode (@Nonnull final IBootstrapFormGroupContainer <?> aForm,
                                  @Nonnull final BootstrapFormGroup aFormGroup,
                                  @Nonnull final AbstractHCDiv <?> aFinalNode)
  {}

  @Override
  @Nonnull
  public AbstractHCDiv <?> renderFormGroup (@Nonnull final IBootstrapFormGroupContainer <?> aForm,
                                            @Nonnull final BootstrapFormGroup aFormGroup,
                                            @Nonnull final Locale aDisplayLocale)
  {
    final HCFormLabel aLabel = aFormGroup.getLabel ();
    final IHCNode aCtrls = aFormGroup.getCtrl ();
    final IHCNode aHelpText = aFormGroup.getHelpText ();
    final IErrorList aErrorList = aFormGroup.getErrorList ();
    final boolean bIsInline = aForm.getFormType ().isInline ();

    final ICommonsList <IHCControl <?>> aAllCtrls = HCCtrlHelper.getAllHCControls (aCtrls);
    final IHCControl <?> aFirstControl;
    if (aAllCtrls.isNotEmpty ())
    {
      aFirstControl = aAllCtrls.getFirstOrNull ();

      // Set CSS class to all contained controls
      BootstrapFormHelper.markAsFormControls (aAllCtrls);

      // Set "aria-labelledby"
      BootstrapFormHelper.connectFormControlsWithLabel (aAllCtrls, aLabel);

      // Required so that error text is shown
      BootstrapFormHelper.applyFormControlValidityState (aAllCtrls, aErrorList);
    }
    else
      aFirstControl = null;

    // Check form errors - highlighting
    final ICommonsList <IHCElement <?>> aErrorCommonList = new CommonsArrayList <> ();
    if (aErrorList != null)
      for (final IError aError : aErrorList)
      {
        final IHCElement <?> aErrorNode = createSingleErrorNode (aError, aDisplayLocale);
        if (aFirstControl == null)
        {
          // Enforce display!
          aErrorNode.addClass (CBootstrapCSS.D_BLOCK);
        }
        aErrorCommonList.add (aErrorNode);
      }

    // Set "aria-describedby"
    BootstrapFormHelper.connectFormControlsWithErrors (aAllCtrls, aErrorCommonList);

    final HCNodeList aErrorListNode = new HCNodeList ();
    aErrorListNode.addChildren (aErrorCommonList);

    // Help text (only if a control is present)
    IHCElement <?> aHelpTextNode = null;
    if (aHelpText != null && aCtrls != null)
    {
      aHelpTextNode = createHelpTextNode (aHelpText);

      if (bIsInline)
        aHelpTextNode.addClass (CBootstrapCSS.SR_ONLY);

      // Set "aria-describedby"
      BootstrapFormHelper.connectFormControlsWithHelpText (aAllCtrls, aHelpTextNode);
    }

    AbstractHCDiv <?> aFinalNode;
    boolean bFirstControlIsCheckBox;
    boolean bFirstControlIsRadioButton;
    if (m_bForceNoCheckBoxHandling || aAllCtrls.size () != 1)
    {
      bFirstControlIsCheckBox = false;
      bFirstControlIsRadioButton = false;
    }
    else
    {
      bFirstControlIsCheckBox = aFirstControl instanceof AbstractHCCheckBox <?>;
      bFirstControlIsRadioButton = aFirstControl instanceof AbstractHCRadioButton <?>;
    }
    if (bFirstControlIsCheckBox || bFirstControlIsRadioButton)
    {
      // Check box or radio button
      aFirstControl.addClass (CBootstrapCSS.FORM_CHECK_INPUT);

      final HCDiv aDivFormCheck = new HCDiv ().addClass (CBootstrapCSS.FORM_CHECK).addChild (aCtrls);

      if (bIsInline)
      {
        if (aLabel != null)
        {
          aLabel.addClass (CBootstrapCSS.FORM_CHECK_LABEL);
          aDivFormCheck.addChild (aLabel);

          // We have a label for a control
          modifyFirstControlIfLabelIsPresent (aLabel, aFirstControl);
        }

        aFinalNode = aDivFormCheck.addClass (CBootstrapCSS.MR_SM_2).addChild (aErrorListNode).addChild (aHelpTextNode);
      }
      else
      {
        if (aLabel != null)
        {
          aLabel.addClass (CBootstrapCSS.FORM_CHECK_LABEL);
          aDivFormCheck.addChild (aLabel);

          // We have a label for a control
          modifyFirstControlIfLabelIsPresent (aLabel, aFirstControl);
        }

        // Add an offset to the controls
        final HCDiv aDivRight = new HCDiv ().addChild (aDivFormCheck)
                                            .addChild (aErrorListNode)
                                            .addChild (aHelpTextNode);

        if (!bIsInline)
        {
          aForm.getLeft ().applyOffsetTo (aDivRight);
          aForm.getRight ().applyTo (aDivRight);
        }

        aFinalNode = bIsInline ? new HCDiv () : new BootstrapRow ();
        aFinalNode.addClass (CBootstrapCSS.FORM_GROUP).addChild (aDivRight);
      }
    }
    else
    {
      // Other control
      if (bIsInline)
      {
        if (aFirstControl != null)
          aFirstControl.addClass (CBootstrapCSS.MR_SM_2);

        final HCDiv aDivRight = new HCDiv ().addChild (aCtrls).addChild (aErrorListNode).addChild (aHelpTextNode);
        if (aLabel == null || aLabel.hasNoChildren ())
        {
          // No label - just add controls
          aFinalNode = aDivRight;
        }
        else
        {
          // We have a label

          // Screen reader only....
          aLabel.addClass (CBootstrapCSS.SR_ONLY);

          if (aFirstControl != null)
          {
            // We have a label for a control
            modifyFirstControlIfLabelIsPresent (aLabel, aFirstControl);
          }

          // DIV is unnecessary
          aFinalNode = new HCDiv ().addChild (aLabel).addChild (aDivRight);
        }
      }
      else
      {
        // add in form group
        aFinalNode = new BootstrapRow ().addClass (CBootstrapCSS.FORM_GROUP);

        final HCDiv aDivRight = new HCDiv ().addChild (aCtrls).addChild (aErrorListNode).addChild (aHelpTextNode);
        aForm.getRight ().applyTo (aDivRight);

        if (aLabel == null || aLabel.hasNoChildren ())
        {
          // No label - just add controls

          if (!bIsInline)
          {
            // Add an offset to the controls
            aForm.getLeft ().applyOffsetTo (aDivRight);
          }

          aFinalNode.addChild (aDivRight);
        }
        else
        {
          // We have a label

          // Screen reader only....
          if (bIsInline)
            aLabel.addClass (CBootstrapCSS.SR_ONLY);

          if (aFirstControl != null)
          {
            // We have a label for a control
            modifyFirstControlIfLabelIsPresent (aLabel, aFirstControl);
          }

          if (!bIsInline)
            aForm.getLeft ().applyTo (aLabel);

          aFinalNode.addChild (aLabel).addChild (aDivRight);
        }
      }
    }

    // Set ID, class and style
    aFormGroup.applyBasicHTMLTo (aFinalNode);

    modifyFinalNode (aForm, aFormGroup, aFinalNode);

    return aFinalNode;
  }
}
