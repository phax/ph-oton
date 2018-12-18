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
import javax.annotation.concurrent.Immutable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.helger.commons.ValueEnforcer;
import com.helger.commons.annotation.PresentForCodeCoverage;
import com.helger.commons.error.IError;
import com.helger.commons.error.list.IErrorList;
import com.helger.commons.state.EChange;
import com.helger.commons.string.StringHelper;
import com.helger.html.css.DefaultCSSClassProvider;
import com.helger.html.css.ICSSClassProvider;
import com.helger.html.hc.IHCNode;
import com.helger.html.hc.html.IHCElementWithChildren;
import com.helger.html.hc.html.forms.EHCInputType;
import com.helger.html.hc.html.forms.HCCtrlHelper;
import com.helger.html.hc.html.forms.IHCControl;
import com.helger.html.hc.html.forms.IHCInput;
import com.helger.html.hc.html.textlevel.HCSmall;
import com.helger.html.hc.impl.HCNodeList;
import com.helger.photon.bootstrap4.CBootstrapCSS;
import com.helger.photon.uicore.html.formlabel.HCFormLabel;
import com.helger.photon.uicore.html.formlabel.HCFormLabelHelper;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;

@Immutable
@SuppressFBWarnings ("JCIP_FIELD_ISNT_FINAL_IN_IMMUTABLE_CLASS")
public final class BootstrapFormHelper
{
  public static final ICSSClassProvider CSS_CLASS_FORM_GROUP_ERROR_TEXT = DefaultCSSClassProvider.create ("form-group-error-text");
  public static final ICSSClassProvider CSS_CLASS_FORM_GROUP_HELP_TEXT = DefaultCSSClassProvider.create ("form-group-help-text");

  private static final Logger LOGGER = LoggerFactory.getLogger (BootstrapFormHelper.class);

  @PresentForCodeCoverage
  private static final BootstrapFormHelper s_aInstance = new BootstrapFormHelper ();

  private BootstrapFormHelper ()
  {}

  @Nonnull
  public static EChange markAsFormControl (@Nullable final IHCControl <?> aCtrl)
  {
    if (aCtrl != null)
    {
      ICSSClassProvider aCSSClassToAdd = CBootstrapCSS.FORM_CONTROL;
      if (aCtrl instanceof IHCInput <?>)
      {
        // all except for checkbox, radio button and
        // hidden field
        final IHCInput <?> aInput = (IHCInput <?>) aCtrl;

        // May be null!
        final EHCInputType eType = aInput.getType ();
        switch (eType)
        {
          case CHECKBOX:
          case RADIO:
            aCSSClassToAdd = CBootstrapCSS.FORM_CHECK_INPUT;
            break;
          case FILE:
            aCSSClassToAdd = CBootstrapCSS.FORM_CONTROL_FILE;
            break;
          case HIDDEN:
            aCSSClassToAdd = null;
            break;
        }
      }

      if (aCSSClassToAdd != null)
      {
        aCtrl.addClass (aCSSClassToAdd);
        return EChange.CHANGED;
      }
    }
    return EChange.UNCHANGED;
  }

  public static void markAsFormControls (@Nullable final Iterable <? extends IHCControl <?>> aCtrls)
  {
    if (aCtrls != null)
      for (final IHCControl <?> aCurCtrl : aCtrls)
        markAsFormControl (aCurCtrl);
  }

  public static void markChildrenAsFormControls (@Nullable final IHCNode aParent)
  {
    if (aParent != null)
      aParent.forAllChildren (aChild -> markAsFormControls (HCCtrlHelper.getAllHCControls (aChild)));
  }

  public static void connectFormControlsWithLabel (@Nullable final Iterable <? extends IHCControl <?>> aCtrls,
                                                   @Nullable final HCFormLabel aLabel)
  {
    // Set "aria-labelledby"
    if (aCtrls != null && aLabel != null)
      for (final IHCControl <?> aCurCtrl : aCtrls)
        aCurCtrl.customAttrs ().setAriaLabeledBy (aLabel);
  }

  public static void applyFormControlValidityState (@Nullable final IHCControl <?> aElement,
                                                    @Nullable final IErrorList aErrorList)
  {
    ValueEnforcer.notNull (aElement, "Element");

    if (aErrorList != null)
      if (aErrorList.containsAtLeastOneError ())
      {
        // Required so that error text is shown
        aElement.addClass (CBootstrapCSS.IS_INVALID);
      }
  }

  public static void applyFormControlValidityState (@Nullable final Iterable <? extends IHCControl <?>> aCtrls,
                                                    @Nullable final IErrorList aErrorList)
  {
    if (aCtrls != null && aErrorList != null && aErrorList.containsAtLeastOneError ())
      for (final IHCControl <?> aCurCtrl : aCtrls)
      {
        // Required so that error text is shown
        aCurCtrl.addClass (CBootstrapCSS.IS_INVALID);
      }
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

  @Nonnull
  public static HCNodeList createDefaultErrorNode (@Nullable final IErrorList aErrorList,
                                                   @Nonnull final Locale aContentLocale)
  {
    final HCNodeList ret = new HCNodeList ();
    if (aErrorList != null)
      for (final IError aError : aErrorList)
        ret.addChild (createDefaultErrorNode (aError, aContentLocale));
    return ret;
  }

  @Nonnull
  public static IHCElementWithChildren <?> createDefaultHelpTextNode (@Nonnull final IHCNode aHelpText)
  {
    final HCSmall aHelpBlock = new HCSmall ();
    aHelpBlock.addClass (CBootstrapCSS.FORM_TEXT);
    aHelpBlock.addClass (CBootstrapCSS.TEXT_MUTED);
    aHelpBlock.addClass (CSS_CLASS_FORM_GROUP_HELP_TEXT);
    aHelpBlock.addChild (aHelpText);
    return aHelpBlock;
  }

  @Nonnull
  public static String getDefaultPlaceholderText (@Nonnull final IHCElementWithChildren <?> aLabel)
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
}
