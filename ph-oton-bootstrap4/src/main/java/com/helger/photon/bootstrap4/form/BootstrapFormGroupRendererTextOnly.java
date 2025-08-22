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
import com.helger.base.reflection.GenericReflection;
import com.helger.collection.commons.CommonsArrayList;
import com.helger.collection.commons.ICommonsList;
import com.helger.diagnostics.error.IError;
import com.helger.diagnostics.error.list.IErrorList;
import com.helger.html.hc.IHCNode;
import com.helger.html.hc.html.IHCElement;
import com.helger.html.hc.html.IHCElementWithChildren;
import com.helger.html.hc.html.grouping.AbstractHCDiv;
import com.helger.html.hc.html.grouping.HCDiv;
import com.helger.html.hc.html.textlevel.HCSpan;
import com.helger.html.hc.impl.HCNodeList;
import com.helger.photon.bootstrap4.CBootstrapCSS;
import com.helger.photon.bootstrap4.grid.BootstrapRow;
import com.helger.photon.uicore.html.formlabel.HCFormLabel;

import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;

/**
 * This is an implementation of {@link IBootstrapFormGroupRenderer} for
 * displaying text controls only.
 *
 * @author Philip Helger
 */
@NotThreadSafe
public class BootstrapFormGroupRendererTextOnly implements IBootstrapFormGroupRenderer
{
  private boolean m_bUseIcons = false;

  public BootstrapFormGroupRendererTextOnly ()
  {}

  @Override
  public boolean isUseIcons ()
  {
    return m_bUseIcons;
  }

  @Override
  @Nonnull
  public BootstrapFormGroupRendererTextOnly setUseIcons (final boolean bUseIcons)
  {
    m_bUseIcons = bUseIcons;
    return this;
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

  @Nullable
  private static IHCElement <?> _toElement (@Nullable final IHCNode a)
  {
    if (a == null)
      return null;
    if (a instanceof IHCElement <?>)
      return (IHCElement <?>) a;
    return new HCSpan ().addChild (a);
  }

  @Override
  @Nonnull
  public AbstractHCDiv <?> renderFormGroup (@Nonnull final IBootstrapFormGroupContainer <?> aForm,
                                            @Nonnull final BootstrapFormGroup aFormGroup,
                                            @Nonnull final Locale aDisplayLocale)
  {
    final HCFormLabel aLabel = aFormGroup.getLabel ();
    final var aLabelRenderContent = aLabel == null ? null : aLabel.isTextLabel () ? new HCSpan ().addChild (aLabel
                                                                                                                  .getPlainText ())
                                                                                  : _toElement (aLabel.getFirstChild ());

    final IHCNode aCtrls = aFormGroup.getCtrl ();
    final IHCNode aHelpText = aFormGroup.getHelpText ();
    final IErrorList aErrorList = aFormGroup.getErrorList ();
    final boolean bIsInline = aForm.getFormType ().isInline ();

    // Check form errors - highlighting
    final ICommonsList <IHCElement <?>> aErrorCommonList = new CommonsArrayList <> ();
    if (aErrorList != null)
      for (final IError aError : aErrorList)
      {
        final IHCElement <?> aErrorNode = createSingleErrorNode (aError, aDisplayLocale);
        // Enforce display!
        aErrorNode.addClass (CBootstrapCSS.D_BLOCK);
        aErrorCommonList.add (aErrorNode);
      }

    final HCNodeList aErrorListNode = new HCNodeList ();
    aErrorListNode.addChildren (aErrorCommonList);

    // Help text (only if a control is present)
    IHCElement <?> aHelpTextNode = null;
    if (aHelpText != null && aCtrls != null)
    {
      aHelpTextNode = createHelpTextNode (aHelpText);

      if (bIsInline)
        aHelpTextNode.addClass (CBootstrapCSS.SR_ONLY);
    }

    AbstractHCDiv <?> aFinalNode;
    // Other control
    if (bIsInline)
    {
      final HCDiv aDivRight = new HCDiv ().addChild (aCtrls).addChild (aErrorListNode).addChild (aHelpTextNode);
      if (aLabelRenderContent == null || aLabelRenderContent.hasNoChildren ())
      {
        // No label - just add controls
        aFinalNode = aDivRight;
      }
      else
      {
        // We have a label

        // Screen reader only....
        aLabelRenderContent.addClass (CBootstrapCSS.SR_ONLY);

        // DIV is unnecessary
        aFinalNode = new HCDiv ().addChild (aLabelRenderContent).addChild (aDivRight);
      }
    }
    else
    {
      // add in form group
      aFinalNode = new BootstrapRow ().addClass (CBootstrapCSS.FORM_GROUP);

      final HCDiv aDivRight = new HCDiv ().addChild (aCtrls).addChild (aErrorListNode).addChild (aHelpTextNode);
      aForm.getRight ().applyTo (aDivRight);

      if (aLabelRenderContent == null || aLabelRenderContent.hasNoChildren ())
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
          aLabelRenderContent.addClass (CBootstrapCSS.SR_ONLY);

        if (!bIsInline)
        {
          // Hack to satisfy API
          aForm.getLeft ().applyTo (GenericReflection.uncheckedCast (aLabelRenderContent));
        }

        aFinalNode.addChild (aLabelRenderContent).addChild (aDivRight);
      }
    }

    // Set ID, class and style
    aFormGroup.applyBasicHTMLTo (aFinalNode);

    modifyFinalNode (aForm, aFormGroup, aFinalNode);

    return aFinalNode;
  }
}
