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
package com.helger.photon.bootstrap4.uictrls.ext;

import java.util.Locale;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.helger.commons.ValueEnforcer;
import com.helger.commons.annotation.Nonempty;
import com.helger.commons.annotation.Translatable;
import com.helger.commons.text.IMultilingualText;
import com.helger.commons.text.display.IHasDisplayTextWithArgs;
import com.helger.commons.text.resolve.DefaultTextResolver;
import com.helger.commons.text.util.TextHelper;
import com.helger.html.hc.IHCConversionSettingsToNode;
import com.helger.html.hc.IHCHasChildrenMutable;
import com.helger.html.hc.IHCNode;
import com.helger.html.hc.html.forms.HCEditFile;
import com.helger.html.hc.html.forms.HCLabel;
import com.helger.html.hc.html.grouping.AbstractHCDiv;
import com.helger.html.hc.html.metadata.HCStyle;
import com.helger.html.jquery.JQuery;
import com.helger.html.js.EJSEvent;
import com.helger.html.jscode.JSExpr;
import com.helger.html.jscode.html.JSHtml;
import com.helger.photon.bootstrap4.CBootstrapCSS;

/**
 * Custom file upload.
 *
 * @author Philip Helger
 */
public class BootstrapFileUpload extends AbstractHCDiv <BootstrapFileUpload>
{
  @Translatable
  public static enum EText implements IHasDisplayTextWithArgs
  {
    BUTTON_BROWSE ("Durchsuchen", "Browse"),
    BROWSE_LABEL ("Wählen Sie eine Datei von Ihrem Rechner aus", "Select a file from your local machine");

    private final IMultilingualText m_aTP;

    private EText (final String sDE, final String sEN)
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

  public BootstrapFileUpload (@Nonnull @Nonempty final String sName, @Nonnull final Locale aDisplayLocale)
  {
    ValueEnforcer.notEmpty (sName, "Name");
    ValueEnforcer.notNull (aDisplayLocale, "DisplayLocale");
    m_sFieldName = sName;
    m_aDisplayLocale = aDisplayLocale;
  }

  @Nonnull
  public final String getFieldName ()
  {
    return m_sFieldName;
  }

  @Override
  protected void onFinalizeNodeState (@Nonnull final IHCConversionSettingsToNode aConversionSettings,
                                      @Nonnull final IHCHasChildrenMutable <?, ? super IHCNode> aTargetNode)
  {
    super.onFinalizeNodeState (aConversionSettings, aTargetNode);
    addClass (CBootstrapCSS.CUSTOM_FILE);

    final HCEditFile aEditFile = new HCEditFile (m_sFieldName);
    aEditFile.addClass (CBootstrapCSS.CUSTOM_FILE_INPUT);
    addChild (aEditFile);

    final HCLabel aLabel = new HCLabel ();
    aLabel.setFor (aEditFile);
    aLabel.addClass (CBootstrapCSS.CUSTOM_FILE_LABEL);
    aLabel.addChild (EText.BROWSE_LABEL.getDisplayText (m_aDisplayLocale));
    addChild (aLabel);

    addChild (new HCStyle (".custom-file-label::after { content: \"" +
                           EText.BUTTON_BROWSE.getDisplayText (m_aDisplayLocale) +
                           "\";  }"));

    if (false)
      aEditFile.addEventHandler (EJSEvent.CHANGE,
                                 false ? JSHtml.consoleLog (JSExpr.THIS.ref ("files").component0 ().ref ("name"))
                                       : JQuery.idRef (aLabel)
                                               .empty ()
                                               .append (JSExpr.THIS.ref ("files").component0 ().ref ("name")));
  }
}
