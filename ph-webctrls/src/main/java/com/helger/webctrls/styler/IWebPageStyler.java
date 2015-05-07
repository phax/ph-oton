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
package com.helger.webctrls.styler;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.helger.commons.email.IEmailAddress;
import com.helger.html.hc.IHCElement;
import com.helger.html.hc.IHCElementWithChildren;
import com.helger.html.hc.IHCNode;
import com.helger.html.hc.IHCTable;
import com.helger.html.hc.html.AbstractHCForm;
import com.helger.html.hc.html.HCCol;
import com.helger.html.hc.html.HC_Target;
import com.helger.webbasics.app.layout.ILayoutExecutionContext;
import com.helger.webbasics.app.page.IWebPageExecutionContext;
import com.helger.webctrls.custom.tabbox.ITabBox;
import com.helger.webctrls.custom.table.IHCTableForm;
import com.helger.webctrls.custom.table.IHCTableFormView;
import com.helger.webctrls.custom.toolbar.IButtonToolbar;
import com.helger.webctrls.datatables.DataTables;

/**
 * Interface for create UI dependent controls that are used in the default pages
 *
 * @author Philip Helger
 */
public interface IWebPageStyler
{
  @Nonnull
  AbstractHCForm <?> createForm (@Nonnull ILayoutExecutionContext aLEC);

  @Nonnull
  AbstractHCForm <?> createFormFileUpload (@Nonnull ILayoutExecutionContext aLEC);

  @Nullable
  IHCNode createEmailLink (@Nullable String sEmailAddress);

  @Nullable
  IHCNode createEmailLink (@Nullable IEmailAddress aEmail);

  @Nullable
  IHCNode createWebLink (@Nullable String sWebSite);

  @Nullable
  IHCNode createWebLink (@Nullable String sWebSite, @Nullable HC_Target aTarget);

  /**
   * Create a box that shows a constant note, that changes could not be saved,
   * because the user did not enter all form data correctly.
   *
   * @param aWPEC
   *        web page execution context. Never <code>null</code>.
   * @return The control to display
   */
  @Nonnull
  IHCNode createIncorrectInputBox (@Nonnull ILayoutExecutionContext aWPEC);

  @Nonnull
  IHCElementWithChildren <?> createInfoBox (@Nonnull ILayoutExecutionContext aWPEC, @Nullable String sText);

  @Nonnull
  IHCElementWithChildren <?> createWarnBox (@Nonnull ILayoutExecutionContext aWPEC, @Nullable String sText);

  @Nonnull
  IHCElementWithChildren <?> createErrorBox (@Nonnull ILayoutExecutionContext aWPEC, @Nullable String sText);

  @Nonnull
  IHCElementWithChildren <?> createSuccessBox (@Nonnull ILayoutExecutionContext aWPEC, @Nullable String sText);

  @Nonnull
  IHCElementWithChildren <?> createQuestionBox (@Nonnull ILayoutExecutionContext aWPEC, @Nullable String sText);

  @Nonnull
  IHCTable <?> createTable (@Nullable HCCol... aCols);

  @Nonnull
  IHCTableForm <?> createTableForm (@Nullable HCCol... aCols);

  @Nonnull
  IHCTableFormView <?> createTableFormView (@Nullable HCCol... aCols);

  @Nonnull
  DataTables createDefaultDataTables (@Nonnull IWebPageExecutionContext aWPEC, @Nonnull IHCTable <?> aTable);

  @Nonnull
  IHCElement <?> createUploadButton (@Nonnull IWebPageExecutionContext aWPEC);

  @Nonnull
  IButtonToolbar <?> createToolbar (@Nonnull IWebPageExecutionContext aWPEC);

  @Nonnull
  ITabBox <?> createTabBox (@Nonnull IWebPageExecutionContext aWPEC);

  @Nullable
  IHCNode createPasswordConstraintToolTip (@Nonnull IWebPageExecutionContext aWPEC);
}
