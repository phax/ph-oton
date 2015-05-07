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
package com.helger.bootstrap3.button;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.helger.bootstrap3.CBootstrapCSS;
import com.helger.css.property.CCSSProperties;
import com.helger.css.propertyvalue.CCSSValue;
import com.helger.html.EHTMLRole;
import com.helger.html.hc.html.AbstractHCDiv;
import com.helger.html.hc.html.HCEdit;
import com.helger.html.hc.html.HCEditFile;
import com.helger.html.hc.html.HCScript;
import com.helger.html.hc.impl.HCNodeList;
import com.helger.html.js.builder.JSAnonymousFunction;
import com.helger.html.js.builder.JSExpr;
import com.helger.html.js.builder.jquery.JQuery;

/**
 * A special upload button, that hides the browser differences in a nice way.
 * Important that this is a DIV and not a button, so that the click event is
 * propagated to the contained nested input element<br>
 * Source:
 * http://geniuscarrier.com/how-to-style-a-html-file-upload-button-in-pure-css/
 *
 * @author Philip Helger
 */
public class BootstrapUploadButton extends AbstractHCDiv <BootstrapUploadButton>
{
  private final HCEditFile m_aEdit;

  /**
   * @param sName
   *        The field name of the file input. Should not be <code>null</code>.
   */
  public BootstrapUploadButton (@Nullable final String sName)
  {
    setRole (EHTMLRole.BUTTON);
    addClasses (CBootstrapCSS.BTN, EBootstrapButtonType.DEFAULT, EBootstrapButtonSize.DEFAULT);
    addStyle (CCSSProperties.POSITION.newValue (CCSSValue.RELATIVE));
    addStyle (CCSSProperties.OVERFLOW.newValue (CCSSValue.HIDDEN));
    addStyle (CCSSProperties.DIRECTION.newValue (CCSSValue.LTR));
    m_aEdit = addAndReturnChild (new HCEditFile (sName));
    m_aEdit.addStyle (CCSSProperties.POSITION.newValue (CCSSValue.ABSOLUTE));
    m_aEdit.addStyle (CCSSProperties.RIGHT.newValue ("0"));
    m_aEdit.addStyle (CCSSProperties.TOP.newValue ("0"));
    m_aEdit.addStyle (CCSSProperties.FONT_FAMILY.newValue (CCSSValue.FONT_ARIAL));
    m_aEdit.addStyle (CCSSProperties.FONT_SIZE.newValue ("118px"));
    m_aEdit.addStyle (CCSSProperties.MARGIN_0);
    m_aEdit.addStyle (CCSSProperties.PADDING_0);
    m_aEdit.addStyle (CCSSProperties.CURSOR.newValue (CCSSValue.POINTER));
    m_aEdit.addStyle (CCSSProperties.OPACITY.newValue ("0"));
  }

  @Nonnull
  public HCEditFile getFileEdit ()
  {
    return m_aEdit;
  }

  /**
   * Create a readonly edit that contains the value of the selected file. It can
   * be placed anywhere in the DOM.
   *
   * @param sPlaceholder
   *        The placeholder text to be used if no file is selected.
   * @return A nodelist with the edit and a JavaScript
   */
  @Nonnull
  public HCNodeList createSelectedFileEdit (@Nullable final String sPlaceholder)
  {
    final HCEdit aEdit = new HCEdit ().setPlaceholder (sPlaceholder).setReadonly (true);
    final HCScript aScript = new HCScript (JQuery.idRef (m_aEdit)
                                                 .on ("change",
                                                      new JSAnonymousFunction (JQuery.idRef (aEdit)
                                                                                     .val (JSExpr.THIS.ref ("value")))));
    return new HCNodeList ().addChildren (aEdit, aScript);
  }
}
