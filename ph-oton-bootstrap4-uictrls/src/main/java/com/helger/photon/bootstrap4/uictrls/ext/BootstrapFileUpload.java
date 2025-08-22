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
package com.helger.photon.bootstrap4.uictrls.ext;

import java.util.Locale;

import com.helger.annotation.Nonempty;
import com.helger.annotation.misc.Translatable;
import com.helger.annotation.style.OverrideOnDemand;
import com.helger.base.enforce.ValueEnforcer;
import com.helger.base.string.StringHelper;
import com.helger.base.string.StringReplace;
import com.helger.css.decl.CSSDeclaration;
import com.helger.css.decl.CSSExpression;
import com.helger.css.decl.CSSSelector;
import com.helger.css.decl.CSSSelectorSimpleMember;
import com.helger.css.decl.CSSStyleRule;
import com.helger.css.decl.CascadingStyleSheet;
import com.helger.css.property.ECSSProperty;
import com.helger.css.writer.CSSWriterSettings;
import com.helger.html.hc.IHCConversionSettingsToNode;
import com.helger.html.hc.IHCHasChildrenMutable;
import com.helger.html.hc.IHCNode;
import com.helger.html.hc.html.IHCElement;
import com.helger.html.hc.html.forms.HCEditFile;
import com.helger.html.hc.html.forms.HCLabel;
import com.helger.html.hc.html.grouping.AbstractHCDiv;
import com.helger.html.hc.html.metadata.HCStyle;
import com.helger.html.jquery.JQuery;
import com.helger.html.js.EJSEvent;
import com.helger.html.jscode.JSExpr;
import com.helger.html.jscode.html.JSHtml;
import com.helger.photon.bootstrap4.CBootstrapCSS;
import com.helger.text.IMultilingualText;
import com.helger.text.display.IHasDisplayTextWithArgs;
import com.helger.text.resolve.DefaultTextResolver;
import com.helger.text.util.TextHelper;

import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;

/**
 * Custom file upload.
 *
 * @author Philip Helger
 */
public class BootstrapFileUpload extends AbstractHCDiv <BootstrapFileUpload>
{
  @Translatable
  public enum EText implements IHasDisplayTextWithArgs
  {
    BUTTON_BROWSE ("Durchsuchen", "Browse"),
    BROWSE_LABEL ("Wählen Sie eine Datei von Ihrem Rechner aus", "Select a file from your local machine");

    private final IMultilingualText m_aTP;

    EText (final String sDE, final String sEN)
    {
      m_aTP = TextHelper.create_DE_EN (sDE, sEN);
    }

    @Nullable
    public String getDisplayText (@Nonnull final Locale aContentLocale)
    {
      return DefaultTextResolver.getTextStatic (this, m_aTP, aContentLocale);
    }
  }

  private final String m_sFieldName;
  private final Locale m_aDisplayLocale;
  private final HCEditFile m_aEditFile;
  private String m_sCustomPlaceholder;
  private String m_sCustomButtonText;

  public BootstrapFileUpload (@Nonnull @Nonempty final String sName, @Nonnull final Locale aDisplayLocale)
  {
    ValueEnforcer.notEmpty (sName, "Name");
    ValueEnforcer.notNull (aDisplayLocale, "DisplayLocale");
    m_sFieldName = sName;
    m_aDisplayLocale = aDisplayLocale;

    m_aEditFile = new HCEditFile (m_sFieldName);
    m_aEditFile.addClass (CBootstrapCSS.CUSTOM_FILE_INPUT);
    m_aEditFile.ensureID ();
  }

  @Nonnull
  @Nonempty
  public final String getFieldName ()
  {
    return m_sFieldName;
  }

  @Nonnull
  public final HCEditFile getEditFile ()
  {
    return m_aEditFile;
  }

  @Nullable
  public final String getCustomPlaceholder ()
  {
    return m_sCustomPlaceholder;
  }

  @Nonnull
  public final BootstrapFileUpload setCustomPlaceholder (@Nullable final String sCustomPlaceholder)
  {
    m_sCustomPlaceholder = sCustomPlaceholder;
    return this;
  }

  @Nullable
  public final String getCustomButtonText ()
  {
    return m_sCustomButtonText;
  }

  @Nonnull
  public final BootstrapFileUpload setCustomButtonText (@Nullable final String sCustomButtonText)
  {
    m_sCustomButtonText = sCustomButtonText;
    return this;
  }

  /**
   * Create the "placeholder" component. By default it is a "label". To switch this to a "div" or a
   * "span" just override this method.
   *
   * @param sPlaceholder
   *        Place holder text to use. May not be <code>null</code>.
   * @return A non-<code>null</code> element to use.
   * @since 8.3.1
   */
  @Nonnull
  @OverrideOnDemand
  protected IHCElement <?> createPlaceholderNode (@Nonnull final String sPlaceholder)
  {
    final HCLabel aLabel = new HCLabel ();
    aLabel.setFor (m_aEditFile);
    aLabel.addClass (CBootstrapCSS.CUSTOM_FILE_LABEL);
    aLabel.addChild (sPlaceholder);
    return aLabel;
  }

  @Override
  protected void onFinalizeNodeState (@Nonnull final IHCConversionSettingsToNode aConversionSettings,
                                      @Nonnull final IHCHasChildrenMutable <?, ? super IHCNode> aTargetNode)
  {
    super.onFinalizeNodeState (aConversionSettings, aTargetNode);
    addClass (CBootstrapCSS.CUSTOM_FILE);

    addChild (m_aEditFile);

    String sPlaceholder = m_sCustomPlaceholder;
    if (StringHelper.isEmpty (sPlaceholder))
      sPlaceholder = EText.BROWSE_LABEL.getDisplayText (m_aDisplayLocale);

    final IHCElement <?> aPlaceholder = createPlaceholderNode (sPlaceholder);
    addChild (aPlaceholder);

    String sButtonText = m_sCustomButtonText;
    if (StringHelper.isEmpty (sButtonText))
      sButtonText = EText.BUTTON_BROWSE.getDisplayText (m_aDisplayLocale);

    if (false)
    {
      final CascadingStyleSheet aCSS = new CascadingStyleSheet ();
      final CSSStyleRule aStyleRule = new CSSStyleRule ();
      final CSSSelector aSelector = new CSSSelector ();
      aSelector.addMember (new CSSSelectorSimpleMember (".custom-file-label::after"));
      aStyleRule.addSelector (aSelector);
      aStyleRule.addDeclaration (new CSSDeclaration (ECSSProperty.CONTENT.getName (),
                                                     CSSExpression.createString (sButtonText)));
      aCSS.addRule (aStyleRule);
      addChild (new HCStyle (aCSS, new CSSWriterSettings ().setOptimizedOutput (true)));
    }
    else
      addChild (new HCStyle (".custom-file-label::after { content: \"" +
                             StringReplace.replaceAll (sButtonText, "\"", "\\\"") +
                             "\";  }"));

    // Update label with selected file
    m_aEditFile.addEventHandler (EJSEvent.CHANGE,
                                 false ? JSHtml.consoleLog (JSExpr.THIS.ref ("files").component0 ().ref ("name"))
                                       : JQuery.idRef (aPlaceholder)
                                               .empty ()
                                               .append (JSExpr.THIS.ref ("files").component0 ().ref ("name")));
  }
}
