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
package com.helger.bootstrap3.styler;

import java.util.Locale;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.helger.bootstrap3.CBootstrapCSS;
import com.helger.bootstrap3.alert.BootstrapErrorBox;
import com.helger.bootstrap3.alert.BootstrapInfoBox;
import com.helger.bootstrap3.alert.BootstrapQuestionBox;
import com.helger.bootstrap3.alert.BootstrapSuccessBox;
import com.helger.bootstrap3.alert.BootstrapWarnBox;
import com.helger.bootstrap3.button.BootstrapButtonToolbar;
import com.helger.bootstrap3.button.EBootstrapButtonType;
import com.helger.bootstrap3.ext.BootstrapDataTables;
import com.helger.bootstrap3.ext.BootstrapSecurityUI;
import com.helger.bootstrap3.form.BootstrapForm;
import com.helger.bootstrap3.nav.BootstrapTabBox;
import com.helger.bootstrap3.table.BootstrapTable;
import com.helger.bootstrap3.table.BootstrapTableForm;
import com.helger.bootstrap3.table.BootstrapTableFormView;
import com.helger.html.EHTMLRole;
import com.helger.html.hc.IHCNode;
import com.helger.html.hc.IHCTable;
import com.helger.html.hc.html.HCCol;
import com.helger.html.hc.html.HCSpan;
import com.helger.webbasics.EWebBasicsText;
import com.helger.webbasics.app.layout.ILayoutExecutionContext;
import com.helger.webbasics.app.page.IWebPageExecutionContext;
import com.helger.webctrls.styler.SimpleWebPageStyler;

public class BootstrapWebPageStyler extends SimpleWebPageStyler
{
  @Override
  @Nonnull
  public BootstrapForm createForm (@Nonnull final ILayoutExecutionContext aLEC)
  {
    return new BootstrapForm (aLEC.getSelfHref ());
  }

  @Override
  @Nonnull
  public BootstrapForm createFormFileUpload (@Nonnull final ILayoutExecutionContext aLEC)
  {
    return createForm (aLEC).setEncTypeFileUpload ();
  }

  @Override
  @Nonnull
  public BootstrapInfoBox createInfoBox (@Nonnull final ILayoutExecutionContext aWPEC, @Nullable final String sText)
  {
    return BootstrapInfoBox.create (sText);
  }

  @Override
  @Nonnull
  public BootstrapWarnBox createWarnBox (@Nonnull final ILayoutExecutionContext aWPEC, @Nullable final String sText)
  {
    return BootstrapWarnBox.create (sText);
  }

  @Override
  @Nonnull
  public BootstrapErrorBox createErrorBox (@Nonnull final ILayoutExecutionContext aWPEC, @Nullable final String sText)
  {
    return BootstrapErrorBox.create (sText);
  }

  @Override
  @Nonnull
  public BootstrapSuccessBox createSuccessBox (@Nonnull final ILayoutExecutionContext aWPEC,
                                               @Nullable final String sText)
  {
    return BootstrapSuccessBox.create (sText);
  }

  @Override
  @Nonnull
  public BootstrapQuestionBox createQuestionBox (@Nonnull final ILayoutExecutionContext aWPEC,
                                                 @Nullable final String sText)
  {
    return BootstrapQuestionBox.create (sText);
  }

  @Override
  @Nonnull
  public BootstrapTable createTable (@Nullable final HCCol... aCols)
  {
    // Must be a regular Bootstrap3Table to work with the data tables
    return new BootstrapTable (aCols);
  }

  @Override
  @Nonnull
  public BootstrapTableForm createTableForm (@Nullable final HCCol... aCols)
  {
    return new BootstrapTableForm (aCols);
  }

  @Override
  @Nonnull
  public BootstrapTableFormView createTableFormView (@Nullable final HCCol... aCols)
  {
    return new BootstrapTableFormView (aCols);
  }

  @Override
  @Nonnull
  public BootstrapDataTables createDefaultDataTables (@Nonnull final IWebPageExecutionContext aWPEC,
                                                      @Nonnull final IHCTable <?> aTable)
  {
    final BootstrapDataTables ret = new BootstrapDataTables (aTable);
    ret.setDisplayLocale (aWPEC.getDisplayLocale ());
    ret.addAllColumns (aTable);
    return ret;
  }

  @Override
  @Nonnull
  public HCSpan createUploadButton (@Nonnull final IWebPageExecutionContext aWPEC)
  {
    final Locale aDisplayLocale = aWPEC.getDisplayLocale ();
    return new HCSpan ().addClasses (CBootstrapCSS.BTN, EBootstrapButtonType.SUCCESS)
                        .setRole (EHTMLRole.BUTTON)
                        .addChild (EWebBasicsText.FILE_SELECT.getDisplayText (aDisplayLocale));
  }

  @Override
  @Nonnull
  public BootstrapButtonToolbar createToolbar (@Nonnull final IWebPageExecutionContext aWPEC)
  {
    return new BootstrapButtonToolbar (aWPEC);
  }

  @Override
  @Nonnull
  public BootstrapTabBox createTabBox (@Nonnull final IWebPageExecutionContext aWPEC)
  {
    return new BootstrapTabBox ();
  }

  @Override
  @Nullable
  public IHCNode createPasswordConstraintToolTip (@Nonnull final IWebPageExecutionContext aWPEC)
  {
    final Locale aDisplayLocale = aWPEC.getDisplayLocale ();
    return BootstrapSecurityUI.createPasswordConstraintTip (aDisplayLocale);
  }
}
