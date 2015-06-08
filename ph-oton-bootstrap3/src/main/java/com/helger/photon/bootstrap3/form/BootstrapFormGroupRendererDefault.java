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
package com.helger.photon.bootstrap3.form;

import java.util.List;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.concurrent.Immutable;

import com.helger.commons.annotations.OverrideOnDemand;
import com.helger.commons.collections.CollectionHelper;
import com.helger.commons.string.StringHelper;
import com.helger.html.css.DefaultCSSClassProvider;
import com.helger.html.css.ICSSClassProvider;
import com.helger.html.hc.IHCControl;
import com.helger.html.hc.IHCElement;
import com.helger.html.hc.IHCElementWithChildren;
import com.helger.html.hc.IHCNode;
import com.helger.html.hc.html.HCCheckBox;
import com.helger.html.hc.html.HCDiv;
import com.helger.html.hc.html.HCLabel;
import com.helger.html.hc.html.HCRadioButton;
import com.helger.html.hc.htmlext.HCUtils;
import com.helger.html.hc.impl.AbstractHCInput;
import com.helger.photon.bootstrap3.BootstrapHelper;
import com.helger.photon.bootstrap3.CBootstrapCSS;
import com.helger.photon.bootstrap3.grid.BootstrapGridSpec;
import com.helger.photon.uicore.html.formlabel.HCFormLabel;
import com.helger.photon.uicore.html.formlabel.HCFormLabelUtils;
import com.helger.validation.error.IError;
import com.helger.validation.error.IErrorList;

/**
 * This is the default implementation of {@link IBootstrapFormGroupRenderer}
 * which performs standard rendering. It offers the possibility to modify
 * certain styling by overriding the provided protected methods.
 *
 * @author Philip Helger
 */
@Immutable
public class BootstrapFormGroupRendererDefault implements IBootstrapFormGroupRenderer
{
  public static final ICSSClassProvider CSS_CLASS_FORM_GROUP_HELP_TEXT = DefaultCSSClassProvider.create ("form-group-help-text");
  public static final ICSSClassProvider CSS_CLASS_FORM_GROUP_ERROR_TEXT = DefaultCSSClassProvider.create ("form-group-error-text");

  private boolean m_bUseIcons = false;

  public BootstrapFormGroupRendererDefault ()
  {}

  public boolean isUseIcons ()
  {
    return m_bUseIcons;
  }

  public void setUseIcons (final boolean bUseIcons)
  {
    m_bUseIcons = bUseIcons;
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
    if (aFirstControl instanceof AbstractHCInput <?>)
    {
      final AbstractHCInput <?> aEdit = (AbstractHCInput <?>) aFirstControl;

      // Only check for null, so that empty string overrides this
      // default behaviour
      if (aEdit.getPlaceholder () == null)
      {
        if (aLabel instanceof HCFormLabel)
        {
          // Special handling for the form label, which has explicit support for
          // label texts
          aEdit.setPlaceholder (((HCFormLabel) aLabel).getLabelText ());
        }
        else
        {
          // Trim eventually trailing ":" from string
          String sNewPlaceholder = StringHelper.trimEnd (aLabel.getPlainText (), HCFormLabelUtils.LABEL_END);
          // Trim trailing "*" or "°" marker
          sNewPlaceholder = StringHelper.trimEnd (sNewPlaceholder, HCFormLabelUtils.SIGN_ALTERNATIVE);
          sNewPlaceholder = StringHelper.trimEnd (sNewPlaceholder, HCFormLabelUtils.SIGN_MANDATORY);
          aEdit.setPlaceholder (sNewPlaceholder);
        }
      }
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
   * @return Never <code>null</code>.
   */
  @Nonnull
  @OverrideOnDemand
  protected IHCElement <?> createSingleErrorNode (@Nonnull final IError aError)
  {
    final BootstrapHelpBlock aErrorBlock = new BootstrapHelpBlock ().addClass (CSS_CLASS_FORM_GROUP_ERROR_TEXT);
    aErrorBlock.addChild (aError.getErrorText ());
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
  protected void modifyFinalNode (@Nonnull final BootstrapForm aForm,
                                  @Nonnull final BootstrapFormGroup aFormGroup,
                                  @Nonnull final HCDiv aFinalNode)
  {}

  @Nonnull
  public IHCElement <?> renderFormGroup (@Nonnull final BootstrapForm aForm,
                                         @Nonnull final BootstrapFormGroup aFormGroup)
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

    final List <IHCControl <?>> aAllCtrls = HCUtils.getAllHCControls (aCtrls);

    // Set CSS class to all contained controls
    BootstrapHelper.markAsFormControls (aAllCtrls);

    final IHCControl <?> aFirstControl = CollectionHelper.getFirstElement (aAllCtrls);
    HCDiv aFinalNode;
    final boolean bFirstControlIsCheckBox = aAllCtrls.size () == 1 && aFirstControl instanceof HCCheckBox;
    final boolean bFirstControlIsRadioButton = aAllCtrls.size () == 1 && aFirstControl instanceof HCRadioButton;
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
          final String sLabelText = aLabel.getLabelText ();
          aLabel.removeAllChildren ().addChild (sLabelText);
        }

        aLabel.addChild (0, aCtrls);
        aLabel.addChild (1, " ");
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
      bUseIcons = isUseIcons () && eState.isNotNone () && aFirstControl instanceof AbstractHCInput <?>;

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

    // Help text
    if (aHelpText != null)
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
        final IHCElement <?> aErrorNode = createSingleErrorNode (aError);

        if (eFormType == EBootstrapFormType.HORIZONTAL)
        {
          aLeftGrid.applyOffsetTo (aErrorNode);
          aRightGrid.applyTo (aErrorNode);
        }
        aFinalNode.addChild (aErrorNode);
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
