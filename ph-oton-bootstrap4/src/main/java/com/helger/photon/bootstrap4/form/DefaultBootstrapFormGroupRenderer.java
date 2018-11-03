/**
 * Copyright (C) 2018 Philip Helger (www.helger.com)
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

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.concurrent.NotThreadSafe;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.helger.commons.annotation.OverrideOnDemand;
import com.helger.commons.collection.CollectionHelper;
import com.helger.commons.collection.impl.ICommonsList;
import com.helger.commons.error.IError;
import com.helger.commons.error.list.IErrorList;
import com.helger.commons.string.StringHelper;
import com.helger.html.css.DefaultCSSClassProvider;
import com.helger.html.css.ICSSClassProvider;
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
import com.helger.html.hc.html.textlevel.HCSmall;
import com.helger.html.hc.impl.HCNodeList;
import com.helger.photon.bootstrap4.BootstrapHelper;
import com.helger.photon.bootstrap4.CBootstrapCSS;
import com.helger.photon.bootstrap4.grid.BootstrapRow;
import com.helger.photon.uicore.html.formlabel.HCFormLabel;
import com.helger.photon.uicore.html.formlabel.HCFormLabelHelper;

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
  public static final ICSSClassProvider CSS_CLASS_FORM_GROUP_HELP_TEXT = DefaultCSSClassProvider.create ("form-group-help-text");
  public static final ICSSClassProvider CSS_CLASS_FORM_GROUP_ERROR_TEXT = DefaultCSSClassProvider.create ("form-group-error-text");
  private static final Logger LOGGER = LoggerFactory.getLogger (DefaultBootstrapFormGroupRenderer.class);

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

  @Nonnull
  private static String _getPlaceholderText (@Nonnull final IHCElementWithChildren <?> aLabel)
  {
    if (aLabel instanceof HCFormLabel)
    {
      // Special handling for the form label, which has explicit support
      // for label texts
      return aLabel.getPlainText ();
    }

    // Trim eventually trailing ":" from string
    String sNewPlaceholder = StringHelper.trimEnd (aLabel.getPlainText (), HCFormLabelHelper.LABEL_END);
    // Trim trailing "*" or "°" marker
    sNewPlaceholder = StringHelper.trimEnd (sNewPlaceholder, HCFormLabelHelper.SIGN_ALTERNATIVE);
    sNewPlaceholder = StringHelper.trimEnd (sNewPlaceholder, HCFormLabelHelper.SIGN_MANDATORY);
    return sNewPlaceholder;
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
        aEdit.setPlaceholder (_getPlaceholderText (aLabel));
    }
    else
      if (aFirstControl instanceof IHCTextArea <?>)
      {
        final IHCTextArea <?> aTextArea = (IHCTextArea <?>) aFirstControl;
        if (!aTextArea.hasPlaceholder ())
          aTextArea.setPlaceholder (_getPlaceholderText (aLabel));
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
    final HCSmall aHelpBlock = new HCSmall ();
    aHelpBlock.addClass (CBootstrapCSS.FORM_TEXT);
    aHelpBlock.addClass (CBootstrapCSS.TEXT_MUTED);
    aHelpBlock.addClass (CSS_CLASS_FORM_GROUP_HELP_TEXT);
    aHelpBlock.addChild (aHelpText);
    return aHelpBlock;
  }

  @Nonnull
  public static BootstrapInvalidFeedback createDefaultErrorNode (@Nonnull final IError aError,
                                                                 @Nonnull final Locale aContentLocale)
  {
    String sErrorText = StringHelper.getNotNull (aError.getErrorText (aContentLocale));
    if (StringHelper.hasNoText (sErrorText))
      LOGGER.warn ("Error " + aError + " has no text in locale " + aContentLocale);

    final String sErrorID = aError.getErrorID ();
    if (StringHelper.hasText (sErrorID))
      sErrorText = "[" + sErrorID + "] " + sErrorText;

    final BootstrapInvalidFeedback aErrorBlock = new BootstrapInvalidFeedback ().addClass (CSS_CLASS_FORM_GROUP_ERROR_TEXT);
    // Display it, even if it is empty (because of non-translation)
    aErrorBlock.addChild (sErrorText);
    return aErrorBlock;
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
    return createDefaultErrorNode (aError, aContentLocale);
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
    final EBootstrapFormType eFormType = aForm.getFormType ();
    final HCFormLabel aLabel = aFormGroup.getLabel ();
    final IHCNode aCtrls = aFormGroup.getCtrl ();
    final IHCNode aHelpText = aFormGroup.getHelpText ();
    final IErrorList aErrorList = aFormGroup.getErrorList ();

    final ICommonsList <IHCControl <?>> aAllCtrls = HCCtrlHelper.getAllHCControls (aCtrls);

    if (aAllCtrls != null)
    {
      // Set CSS class to all contained controls
      BootstrapHelper.markAsFormControls (aAllCtrls);

      // Set "aria-labelledby"
      if (aLabel != null)
        for (final IHCControl <?> aCurCtrl : aAllCtrls)
          aCurCtrl.customAttrs ().setAriaLabeledBy (aLabel);
    }

    // Add marker on first control
    final IHCControl <?> aFirstControl = CollectionHelper.getFirstElement (aAllCtrls);
    if (aErrorList != null && aFirstControl != null)
    {
      if (aErrorList.containsAtLeastOneError ())
      {
        // Required so that error text is shown
        aFirstControl.addClass (CBootstrapCSS.IS_INVALID);
      }
    }

    // Check form errors - highlighting
    final HCNodeList aErrorListNode = new HCNodeList ();
    if (aErrorList != null && aErrorList.isNotEmpty ())
    {
      for (final IError aError : aErrorList)
      {
        final IHCElement <?> aErrorNode = createSingleErrorNode (aError, aDisplayLocale);
        if (aFirstControl == null)
        {
          // Enforce display!
          aErrorNode.addClass (CBootstrapCSS.D_BLOCK);
        }
        aErrorListNode.addChild (aErrorNode);
      }
    }

    // Help text (only if a control is present)
    IHCElement <?> aHelpTextNode = null;
    if (aHelpText != null && aCtrls != null)
    {
      aHelpTextNode = createHelpTextNode (aHelpText);

      if (eFormType == EBootstrapFormType.INLINE)
        aHelpTextNode.addClass (CBootstrapCSS.SR_ONLY);
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
      // Never icons for check box/radio button

      final boolean bCustom = false;

      // Check box or radio button
      final HCDiv aCtrlDiv = new HCDiv ();
      if (bCustom)
      {
        aCtrlDiv.addClass (CBootstrapCSS.CUSTOM_CONTROL);
        if (bFirstControlIsCheckBox)
          aCtrlDiv.addClass (CBootstrapCSS.CUSTOM_CHECKBOX);
        if (bFirstControlIsRadioButton)
          aCtrlDiv.addClass (CBootstrapCSS.CUSTOM_CHECKBOX);
      }
      else
        aCtrlDiv.addClass (CBootstrapCSS.FORM_CHECK);

      if (bCustom)
        aFirstControl.addClass (CBootstrapCSS.CUSTOM_CONTROL_INPUT);
      else
        aFirstControl.addClass (CBootstrapCSS.FORM_CHECK_INPUT);
      aCtrlDiv.addChild (aCtrls);

      if (aLabel != null)
      {
        if (bCustom)
          aLabel.addClass (CBootstrapCSS.CUSTOM_CONTROL_LABEL);
        else
          aLabel.addClass (CBootstrapCSS.FORM_CHECK_LABEL);
        aCtrlDiv.addChild (aLabel);

        // We have a label for a control
        aLabel.setFor (aFirstControl);
        modifyFirstControlIfLabelIsPresent (aLabel, aFirstControl);
      }

      aCtrlDiv.addChild (aErrorListNode).addChild (aHelpTextNode);

      aFinalNode = aCtrlDiv;
    }
    else
    {
      // Other control - add in form group
      aFinalNode = new BootstrapRow ();
      aFinalNode.addClass (CBootstrapCSS.FORM_GROUP);

      if (aLabel == null || aLabel.hasNoChildren ())
      {
        // No label - just add controls
        aFinalNode.addChild (aCtrls).addChild (aErrorListNode).addChild (aHelpTextNode);
      }
      else
      {
        // We have a label

        // Screen reader only....
        if (eFormType == EBootstrapFormType.INLINE)
          aLabel.addClass (CBootstrapCSS.SR_ONLY);

        if (aFirstControl != null)
        {
          // We have a label for a control
          aLabel.setFor (aFirstControl);
          modifyFirstControlIfLabelIsPresent (aLabel, aFirstControl);
        }

        aForm.getLeft ().applyTo (aLabel);
        final HCDiv aDivRight = new HCDiv ().addChild (aCtrls).addChild (aErrorListNode).addChild (aHelpTextNode);
        aForm.getRight ().applyTo (aDivRight);
        aFinalNode.addChildren (aLabel, aDivRight);
      }
    }

    // Set ID, class and style
    aFormGroup.applyBasicHTMLTo (aFinalNode);

    modifyFinalNode (aForm, aFormGroup, aFinalNode);

    return aFinalNode;
  }
}
