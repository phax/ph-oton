/*
 * Copyright (C) 2018-2024 Philip Helger (www.helger.com)
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
import com.helger.commons.collection.impl.ICommonsCollection;
import com.helger.commons.error.IError;
import com.helger.commons.error.list.IErrorList;
import com.helger.commons.string.StringHelper;
import com.helger.html.css.DefaultCSSClassProvider;
import com.helger.html.css.ICSSClassProvider;
import com.helger.html.hc.IHCNode;
import com.helger.html.hc.html.IHCElement;
import com.helger.html.hc.html.IHCElementWithChildren;
import com.helger.html.hc.html.forms.EHCInputType;
import com.helger.html.hc.html.forms.HCCtrlHelper;
import com.helger.html.hc.html.forms.IHCControl;
import com.helger.html.hc.html.forms.IHCInput;
import com.helger.html.hc.html.textlevel.HCSmall;
import com.helger.html.hc.impl.HCNodeList;
import com.helger.html.hc.impl.HCTextNode;
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
  private static final BootstrapFormHelper INSTANCE = new BootstrapFormHelper ();

  private BootstrapFormHelper ()
  {}

  public static void markAsFormControl (@Nullable final IHCNode aNode)
  {
    if (aNode instanceof IHCControl <?>)
    {
      final IHCControl <?> aCtrl = (IHCControl <?>) aNode;

      ICSSClassProvider aCSSClassToAdd = CBootstrapCSS.FORM_CONTROL;
      if (aCtrl instanceof IHCInput <?>)
      {
        // all except for checkbox, radio button and
        // hidden field
        final IHCInput <?> aInput = (IHCInput <?>) aCtrl;

        // May be null!
        final EHCInputType eType = aInput.getType ();
        if (eType != null)
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
              // Not even form-control for hidden fields
              aCSSClassToAdd = null;
              break;
          }
      }

      if (aCSSClassToAdd != null)
        aCtrl.addClass (aCSSClassToAdd);
    }
  }

  public static void markAsFormControls (@Nullable final Iterable <? extends IHCNode> aNodes)
  {
    if (aNodes != null)
      for (final IHCNode aCurNode : aNodes)
        markAsFormControl (aCurNode);
  }

  public static void markChildrenAsFormControls (@Nullable final IHCNode aParent)
  {
    if (aParent != null)
      aParent.forAllChildren (aChild -> markAsFormControls (HCCtrlHelper.getAllHCControls (aChild)));
  }

  public static void connectFormControlWithLabel (@Nullable final IHCElement <?> aCtrl,
                                                  @Nullable final HCFormLabel aLabel)
  {
    if (aCtrl != null && aLabel != null)
    {
      // Set "for" in label
      aLabel.setFor (aCtrl);

      // Set "aria-labelledby"
      aCtrl.customAttrs ().setAriaLabeledBy (aLabel);
    }
  }

  public static void connectFormControlsWithLabel (@Nullable final Iterable <? extends IHCElement <?>> aCtrls,
                                                   @Nullable final HCFormLabel aLabel)
  {
    if (aCtrls != null && aLabel != null)
    {
      final boolean bIsMandatory = aLabel.getType ().isMandatory ();
      boolean bSetLabel = false;
      for (final IHCElement <?> aCurCtrl : aCtrls)
      {
        // Set aria-required
        if (bIsMandatory)
          aCurCtrl.customAttrs ().setAriaRequired (true);

        boolean bSetLabelForForThisCtrl = false;
        if (!bSetLabel)
        {
          // Set "for" in label - can only reference one control
          aLabel.setFor (aCurCtrl);
          bSetLabel = true;
          bSetLabelForForThisCtrl = true;
        }

        // Avoid having both, because that is redundant according to WAI
        if (!bSetLabelForForThisCtrl)
        {
          // Set "aria-labelledby"
          aCurCtrl.customAttrs ().addAriaLabeledBy (aLabel);
        }
      }
    }
  }

  public static void connectFormControlsWithErrors (@Nullable final Iterable <? extends IHCElement <?>> aCtrls,
                                                    @Nullable final ICommonsCollection <? extends IHCElement <?>> aErrorNodes)
  {
    if (aCtrls != null && aErrorNodes != null)
    {
      // Use all error node HTML IDs and add them as one "aria-describedby"
      final String sDescribedBy = StringHelper.imploder ()
                                              .source (aErrorNodes, x -> x.ensureID ().getID ())
                                              .separator (' ')
                                              .build ();
      if (StringHelper.hasText (sDescribedBy))
        for (final IHCElement <?> aCurCtrl : aCtrls)
          aCurCtrl.customAttrs ().addToAriaDescribedBy (sDescribedBy);
    }
  }

  public static void connectFormControlsWithHelpText (@Nullable final Iterable <? extends IHCElement <?>> aCtrls,
                                                      @Nullable final IHCElement <?> aHelpTextNode)
  {
    if (aCtrls != null && aHelpTextNode != null)
    {
      final String sDescribedBy = aHelpTextNode.ensureID ().getID ();
      for (final IHCElement <?> aCurCtrl : aCtrls)
        aCurCtrl.customAttrs ().addToAriaDescribedBy (sDescribedBy);
    }
  }

  public static void applyFormControlValidityState (@Nullable final IHCElement <?> aElement,
                                                    @Nullable final IErrorList aErrorList)
  {
    ValueEnforcer.notNull (aElement, "Element");

    if (aErrorList != null && aErrorList.containsAtLeastOneError ())
    {
      // Required so that error text is shown
      aElement.addClass (CBootstrapCSS.IS_INVALID);
      aElement.customAttrs ().setAriaInvalid (true);
    }
  }

  public static void applyFormControlValidityState (@Nullable final Iterable <? extends IHCElement <?>> aCtrls,
                                                    @Nullable final IErrorList aErrorList)
  {
    if (aCtrls != null && aErrorList != null && aErrorList.containsAtLeastOneError ())
    {
      for (final IHCElement <?> aCurCtrl : aCtrls)
      {
        // Required so that error text is shown
        aCurCtrl.addClass (CBootstrapCSS.IS_INVALID);
        aCurCtrl.customAttrs ().setAriaInvalid (true);
      }
    }
  }

  @Nonnull
  public static BootstrapInvalidFeedback createDefaultErrorNode (@Nonnull final IError aError,
                                                                 @Nonnull final Locale aContentLocale)
  {
    return createDefaultErrorNode (aError, aContentLocale, false);
  }

  @Nonnull
  public static BootstrapInvalidFeedback createDefaultErrorNode (@Nonnull final IError aError,
                                                                 @Nonnull final Locale aContentLocale,
                                                                 final boolean bWithLocation)
  {
    String sText = "";

    if (bWithLocation)
    {
      final String sErrorLocation = aError.getErrorLocation ().getAsString ();
      if (StringHelper.hasText (sErrorLocation))
        sText += sErrorLocation + " ";
    }

    {
      final String sErrorID = aError.getErrorID ();
      if (StringHelper.hasText (sErrorID))
        sText += "[" + sErrorID + "] ";
    }

    {
      final String sErrorText = StringHelper.getNotNull (aError.getErrorText (aContentLocale));
      if (StringHelper.hasNoText (sErrorText))
        LOGGER.warn ("Error " + aError + " has no text in locale " + aContentLocale);
      else
        sText += sErrorText;
    }

    final BootstrapInvalidFeedback aErrorBlock = new BootstrapInvalidFeedback ().addClass (CSS_CLASS_FORM_GROUP_ERROR_TEXT);
    // Display it, even if it is empty (because of non-translation)
    aErrorBlock.addChild (sText);
    return aErrorBlock;
  }

  @Nonnull
  public static HCNodeList createDefaultErrorNode (@Nullable final IErrorList aErrorList,
                                                   @Nonnull final Locale aContentLocale)
  {
    return createDefaultErrorNode (aErrorList, aContentLocale, false);
  }

  @Nonnull
  public static HCNodeList createDefaultErrorNode (@Nullable final IErrorList aErrorList,
                                                   @Nonnull final Locale aContentLocale,
                                                   final boolean bWithLocation)
  {
    final HCNodeList ret = new HCNodeList ();
    if (aErrorList != null)
      for (final IError aError : aErrorList)
        ret.addChild (createDefaultErrorNode (aError, aContentLocale, bWithLocation));
    return ret;
  }

  @Nullable
  public static IHCElementWithChildren <?> createDefaultHelpTextNode (@Nullable final String sHelpText)
  {
    return createDefaultHelpTextNode (HCTextNode.createOnDemand (sHelpText));
  }

  @Nullable
  public static IHCElementWithChildren <?> createDefaultHelpTextNode (@Nullable final IHCNode aHelpText)
  {
    if (aHelpText == null)
      return null;
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

    // Trim all known suffixes like ":" and "*" from string
    return HCFormLabelHelper.trimAllKnownSuffixes (aLabel.getPlainText ());
  }

  @Nonnull
  public static HCNodeList createStandaloneFormCtrl (@Nullable final IHCNode aCtrl,
                                                     @Nullable final IErrorList aErrorList,
                                                     @Nonnull final Locale aContentLocale)
  {
    markAsFormControl (aCtrl);
    if (aCtrl instanceof IHCElement <?>)
      applyFormControlValidityState ((IHCElement <?>) aCtrl, aErrorList);
    return new HCNodeList ().addChild (aCtrl).addChild (createDefaultErrorNode (aErrorList, aContentLocale));
  }
}
