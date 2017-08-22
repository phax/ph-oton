/**
 * Copyright (C) 2014-2017 Philip Helger (www.helger.com)
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
import javax.annotation.concurrent.Immutable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.helger.commons.annotation.OverrideOnDemand;
import com.helger.commons.collection.CollectionHelper;
import com.helger.commons.collection.impl.ICommonsList;
import com.helger.commons.error.IError;
import com.helger.commons.error.list.IErrorList;
import com.helger.commons.string.StringHelper;
import com.helger.html.CHTMLAttributes;
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
import com.helger.html.hc.html.grouping.HCDiv;
import com.helger.photon.bootstrap4.BootstrapHelper;
import com.helger.photon.bootstrap4.CBootstrapCSS;
import com.helger.photon.bootstrap4.grid.BootstrapGridSpec;
import com.helger.photon.uicore.html.formlabel.HCFormLabel;
import com.helger.photon.uicore.html.formlabel.HCFormLabelHelper;

/**
 * This is the default implementation of {@link IBootstrapFormGroupRenderer}
 * which performs standard rendering. It offers the possibility to modify
 * certain styling by overriding the provided protected methods.
 *
 * @author Philip Helger
 */
@Immutable
public class DefaultBootstrapFormGroupRenderer implements IBootstrapFormGroupRenderer
{
  public static final ICSSClassProvider CSS_CLASS_FORM_GROUP_HELP_TEXT = DefaultCSSClassProvider.create ("form-group-help-text");
  public static final ICSSClassProvider CSS_CLASS_FORM_GROUP_ERROR_TEXT = DefaultCSSClassProvider.create ("form-group-error-text");
  private static final Logger s_aLogger = LoggerFactory.getLogger (DefaultBootstrapFormGroupRenderer.class);

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
  protected BootstrapInvalidFeedback createHelpTextNode (@Nonnull final IHCNode aHelpText)
  {
    final BootstrapInvalidFeedback aHelpBlock = new BootstrapInvalidFeedback ().addClass (CSS_CLASS_FORM_GROUP_HELP_TEXT);
    aHelpBlock.addChild (aHelpText);
    return aHelpBlock;
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
    String sErrorText = StringHelper.getNotNull (aError.getErrorText (aContentLocale));
    if (StringHelper.hasNoText (sErrorText))
      s_aLogger.warn ("Error " + aError + " has no test in locale " + aContentLocale);

    final String sErrorID = aError.getErrorID ();
    if (StringHelper.hasText (sErrorID))
      sErrorText = "[" + sErrorID + "] " + sErrorText;

    final BootstrapInvalidFeedback aErrorBlock = new BootstrapInvalidFeedback ().addClass (CSS_CLASS_FORM_GROUP_ERROR_TEXT);
    // Display it, even if it is empty (because of non-translation)
    aErrorBlock.addChild (sErrorText);
    return aErrorBlock;
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
                                  @Nonnull final HCDiv aFinalNode)
  {}

  @Override
  @Nonnull
  public HCDiv renderFormGroup (@Nonnull final IBootstrapFormGroupContainer <?> aForm,
                                @Nonnull final BootstrapFormGroup aFormGroup,
                                @Nonnull final Locale aDisplayLocale)
  {
    final EBootstrapFormType eFormType = aForm.getFormType ();
    final BootstrapGridSpec aLeftGrid = aForm.getLeft ();
    final BootstrapGridSpec aRightGrid = aForm.getRight ();
    HCFormLabel aLabel = aFormGroup.getLabel ();
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
          aCurCtrl.setCustomAttr (CHTMLAttributes.ARIA_LABELLEDBY, aLabel.ensureID ().getID ());
    }

    final IHCControl <?> aFirstControl = CollectionHelper.getFirstElement (aAllCtrls);

    if (aErrorList != null && aFirstControl != null)
    {
      if (aErrorList.containsAtLeastOneError ())
        aFirstControl.addClass (CBootstrapCSS.IS_INVALID);
      else
        aFirstControl.addClass (CBootstrapCSS.IS_VALID);
    }

    HCDiv aFinalNode;
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

      // Check box or radio button
      final HCDiv aCtrlDiv = new HCDiv ();
      aCtrlDiv.addClass (CBootstrapCSS.FORM_CHECK);

      if (aLabel == null || aLabel.hasNoChildren ())
      {
        aLabel = new HCFormLabel (aCtrls).addClass (CBootstrapCSS.FORM_CHECK_LABEL);
        aCtrlDiv.addChild (aLabel);
      }
      else
      {
        aLabel.addClass (CBootstrapCSS.FORM_CHECK_LABEL);
        if (aLabel.isTextLabel ())
        {
          // Use only the text
          final String sLabelText = aLabel.getPlainText ();
          aLabel.removeAllChildren ().addChild (sLabelText);
        }

        aLabel.addChildAt (0, aCtrls);
        aLabel.addChildAt (1, " ");
        aCtrlDiv.addChild (aLabel);
      }

      aFinalNode = aCtrlDiv;
    }
    else
    {
      // Set static class for all direct children which are not controls
      final boolean bContainsFormControlPlaintext = aAllCtrls.isEmpty () &&
                                                    BootstrapHelper.containsFormControlPlaintext (aCtrls);

      // Other control - add in form group
      aFinalNode = new HCDiv ().addClass (CBootstrapCSS.FORM_GROUP);

      if (aLabel == null || aLabel.hasNoChildren ())
      {
        // No label - just add controls
        if (bContainsFormControlPlaintext)
          BootstrapHelper.makeFormControlPlaintext (aCtrls);
        aFinalNode.addChild (aCtrls);
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

        if (bContainsFormControlPlaintext)
          BootstrapHelper.makeFormControlPlaintext (aCtrls);
        aFinalNode.addChildren (aLabel, aCtrls);
      }
    }

    // Help text (only if a control is present)
    if (aHelpText != null && aCtrls != null)
    {
      final IHCElement <?> aHelpTextNode = createHelpTextNode (aHelpText);

      if (eFormType == EBootstrapFormType.INLINE)
        aHelpTextNode.addClass (CBootstrapCSS.SR_ONLY);

      aFinalNode.addChild (aHelpTextNode);
    }

    // Check form errors - highlighting
    if (aErrorList != null && !aErrorList.isEmpty ())
    {
      for (final IError aError : aErrorList)
      {
        final IHCElement <?> aErrorNode = createSingleErrorNode (aError, aDisplayLocale);
        if (aErrorNode != null)
        {
          aFinalNode.addChild (aErrorNode);
        }
      }
    }

    // Set ID, class and style
    aFormGroup.applyBasicHTMLTo (aFinalNode);

    modifyFinalNode (aForm, aFormGroup, aFinalNode);

    return aFinalNode;
  }
}
