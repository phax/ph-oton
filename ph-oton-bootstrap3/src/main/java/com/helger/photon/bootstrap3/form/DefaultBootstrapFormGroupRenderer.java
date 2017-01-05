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
package com.helger.photon.bootstrap3.form;

import java.util.List;
import java.util.Locale;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.concurrent.Immutable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.helger.commons.annotation.OverrideOnDemand;
import com.helger.commons.collection.CollectionHelper;
import com.helger.commons.error.IError;
import com.helger.commons.error.list.IErrorList;
import com.helger.commons.string.StringHelper;
import com.helger.html.css.DefaultCSSClassProvider;
import com.helger.html.css.ICSSClassProvider;
import com.helger.html.hc.IHCNode;
import com.helger.html.hc.html.IHCElement;
import com.helger.html.hc.html.IHCElementWithChildren;
import com.helger.html.hc.html.forms.EHCInputType;
import com.helger.html.hc.html.forms.HCCheckBox;
import com.helger.html.hc.html.forms.HCCtrlHelper;
import com.helger.html.hc.html.forms.HCLabel;
import com.helger.html.hc.html.forms.HCRadioButton;
import com.helger.html.hc.html.forms.IHCControl;
import com.helger.html.hc.html.forms.IHCInput;
import com.helger.html.hc.html.forms.IHCTextArea;
import com.helger.html.hc.html.grouping.HCDiv;
import com.helger.photon.bootstrap3.BootstrapHelper;
import com.helger.photon.bootstrap3.CBootstrapCSS;
import com.helger.photon.bootstrap3.grid.BootstrapGridSpec;
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
  protected IHCElement <?> createHelpTextNode (@Nonnull final IHCNode aHelpText)
  {
    final BootstrapHelpBlock aHelpBlock = new BootstrapHelpBlock ().addClass (CSS_CLASS_FORM_GROUP_HELP_TEXT);
    aHelpBlock.addChild (aHelpText);
    return aHelpBlock;
  }

  /**
   * Retrieve an optional CSS class that is provided to the final node. This
   * method is only called if a non-<code>null</code> and non-empty error list
   * is present.
   *
   * @param aErrorList
   *        The error list. May be <code>null</code>.
   * @return May be <code>null</code> to indicate no CSS class.
   */
  @Nullable
  @OverrideOnDemand
  protected EBootstrapFormGroupState getFormGroupStateFromErrorList (@Nullable final IErrorList aErrorList)
  {
    if (aErrorList != null && !aErrorList.isEmpty ())
    {
      if (aErrorList.containsAtLeastOneError ())
        return EBootstrapFormGroupState.ERROR;
      if (aErrorList.containsAtLeastOneFailure ())
        return EBootstrapFormGroupState.WARNING;
    }
    return null;
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

    final BootstrapHelpBlock aErrorBlock = new BootstrapHelpBlock ().addClass (CSS_CLASS_FORM_GROUP_ERROR_TEXT);
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
  protected void modifyFinalNode (@Nonnull final IBootstrapFormGroupContainer aForm,
                                  @Nonnull final BootstrapFormGroup aFormGroup,
                                  @Nonnull final HCDiv aFinalNode)
  {}

  @Override
  @Nonnull
  public HCDiv renderFormGroup (@Nonnull final IBootstrapFormGroupContainer aForm,
                                @Nonnull final BootstrapFormGroup aFormGroup,
                                @Nonnull final Locale aDisplayLocale)
  {
    final EBootstrapFormType eFormType = aForm.getFormType ();
    final BootstrapGridSpec aLeftGrid = aForm.getLeft ();
    final BootstrapGridSpec aRightGrid = aForm.getRight ();
    final HCFormLabel aLabel = aFormGroup.getLabel ();
    final IHCNode aCtrls = aFormGroup.getCtrl ();
    final IHCNode aHelpText = aFormGroup.getHelpText ();
    final IErrorList aErrorList = aFormGroup.getErrorList ();

    EBootstrapFormGroupState eState = getFormGroupStateFromErrorList (aErrorList);
    if (eState == null)
      eState = aFormGroup.getState ();

    final List <IHCControl <?>> aAllCtrls = HCCtrlHelper.getAllHCControls (aCtrls);

    // Set CSS class to all contained controls
    BootstrapHelper.markAsFormControls (aAllCtrls);

    final IHCControl <?> aFirstControl = CollectionHelper.getFirstElement (aAllCtrls);
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
      bFirstControlIsCheckBox = aFirstControl instanceof HCCheckBox;
      bFirstControlIsRadioButton = aFirstControl instanceof HCRadioButton;
    }
    boolean bUseIcons = false;
    if (bFirstControlIsCheckBox || bFirstControlIsRadioButton)
    {
      // Never icons for check box/radio button

      // Check box or radio button
      final HCDiv aCtrlDiv = new HCDiv ();
      if (bFirstControlIsCheckBox)
        aCtrlDiv.addClass (CBootstrapCSS.CHECKBOX);
      else
        if (bFirstControlIsRadioButton)
          aCtrlDiv.addClass (CBootstrapCSS.RADIO);

      if (aLabel == null || !aLabel.hasChildren ())
      {
        aCtrlDiv.addChild (new HCLabel ().addChild (aCtrls));
      }
      else
      {
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

      if (eFormType == EBootstrapFormType.HORIZONTAL)
      {
        final HCDiv aCtrlParent = new HCDiv ();
        aLeftGrid.applyOffsetTo (aCtrlParent);
        aRightGrid.applyTo (aCtrlParent);
        aFinalNode = new HCDiv ().addClass (CBootstrapCSS.FORM_GROUP).addChild (aCtrlParent.addChild (aCtrlDiv));
      }
      else
        aFinalNode = aCtrlDiv;
    }
    else
    {
      // Icons for edits?
      bUseIcons = isUseIcons () && eState.isNotNone () && aFirstControl instanceof IHCInput <?>;

      // Set static class for all direct children which are not controls
      final boolean bContainsFormControlStatic = aAllCtrls.isEmpty () &&
                                                 BootstrapHelper.containsFormControlStatic (aCtrls);

      // Other control - add in form group
      aFinalNode = new HCDiv ().addClass (CBootstrapCSS.FORM_GROUP);

      if (aLabel != null && aLabel.hasChildren ())
      {
        // We have a label

        // Screen reader only....
        if (eFormType == EBootstrapFormType.INLINE)
          aLabel.addClass (CBootstrapCSS.SR_ONLY);
        else
          if (eFormType == EBootstrapFormType.HORIZONTAL)
          {
            aLabel.addClass (CBootstrapCSS.CONTROL_LABEL);
            aLeftGrid.applyTo (aLabel);
          }

        if (aFirstControl != null)
        {
          // We have a label for a control
          aLabel.setFor (aFirstControl);

          modifyFirstControlIfLabelIsPresent (aLabel, aFirstControl);
        }

        if (eFormType == EBootstrapFormType.HORIZONTAL)
        {
          final HCDiv aCtrlParent = new HCDiv ();
          aRightGrid.applyTo (aCtrlParent);
          if (bUseIcons)
            aCtrlParent.addChild (eState.getIconAsNode ());
          if (bContainsFormControlStatic)
            aCtrlParent.addClass (CBootstrapCSS.FORM_CONTROL_STATIC);
          aCtrlParent.addChild (aCtrls);
          aFinalNode.addChildren (aLabel, aCtrlParent);
        }
        else
        {
          if (bContainsFormControlStatic)
            BootstrapHelper.makeFormControlStatic (aCtrls);
          aFinalNode.addChildren (aLabel, aCtrls);
          if (bUseIcons)
            aFinalNode.addChild (eState.getIconAsNode ());
        }
      }
      else
      {
        // No label - just add controls
        if (eFormType == EBootstrapFormType.HORIZONTAL)
        {
          final HCDiv aCtrlParent = new HCDiv ();
          aLeftGrid.applyOffsetTo (aCtrlParent);
          aRightGrid.applyTo (aCtrlParent);
          if (bUseIcons)
            aCtrlParent.addChild (eState.getIconAsNode ());
          if (bContainsFormControlStatic)
            aCtrlParent.addClass (CBootstrapCSS.FORM_CONTROL_STATIC);
          aCtrlParent.addChild (aCtrls);
          aFinalNode.addChild (aCtrlParent);
        }
        else
        {
          if (bContainsFormControlStatic)
            BootstrapHelper.makeFormControlStatic (aCtrls);
          aFinalNode.addChild (aCtrls);
          if (bUseIcons)
            aFinalNode.addChild (eState.getIconAsNode ());
        }
      }
    }

    // Help text (only if a control is present)
    if (aHelpText != null && aCtrls != null)
    {
      final IHCElement <?> aHelpTextNode = createHelpTextNode (aHelpText);

      if (eFormType == EBootstrapFormType.INLINE)
        aHelpTextNode.addClass (CBootstrapCSS.SR_ONLY);

      if (eFormType == EBootstrapFormType.HORIZONTAL)
        ((HCDiv) aFinalNode.getLastChild ()).addChild (aHelpTextNode);
      else
        aFinalNode.addChild (aHelpTextNode);
    }

    // set specified highlighting state
    aFinalNode.addClass (eState);

    // Check form errors - highlighting
    if (aErrorList != null && !aErrorList.isEmpty ())
    {
      for (final IError aError : aErrorList)
      {
        final IHCElement <?> aErrorNode = createSingleErrorNode (aError, aDisplayLocale);
        if (aErrorNode != null)
        {
          if (eFormType == EBootstrapFormType.HORIZONTAL)
          {
            aLeftGrid.applyOffsetTo (aErrorNode);
            aRightGrid.applyTo (aErrorNode);
          }
          aFinalNode.addChild (aErrorNode);
        }
      }
    }

    if (bUseIcons)
      aFinalNode.addClass (CBootstrapCSS.HAS_FEEDBACK);

    // Set ID, class and style
    aFormGroup.applyBasicHTMLTo (aFinalNode);

    modifyFinalNode (aForm, aFormGroup, aFinalNode);

    return aFinalNode;
  }
}
