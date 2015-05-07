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

import java.util.Locale;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.helger.commons.annotations.UnsupportedOperation;
import com.helger.commons.email.IEmailAddress;
import com.helger.commons.string.StringHelper;
import com.helger.commons.url.URLValidator;
import com.helger.html.css.DefaultCSSClassProvider;
import com.helger.html.css.ICSSClassProvider;
import com.helger.html.hc.IHCElement;
import com.helger.html.hc.IHCElementWithChildren;
import com.helger.html.hc.IHCNode;
import com.helger.html.hc.IHCTable;
import com.helger.html.hc.html.AbstractHCForm;
import com.helger.html.hc.html.HCA;
import com.helger.html.hc.html.HCButton;
import com.helger.html.hc.html.HCCol;
import com.helger.html.hc.html.HCDiv;
import com.helger.html.hc.html.HCForm;
import com.helger.html.hc.html.HCTable;
import com.helger.html.hc.html.HC_Target;
import com.helger.html.hc.htmlext.HCA_MailTo;
import com.helger.html.hc.impl.HCTextNode;
import com.helger.webbasics.EWebBasicsText;
import com.helger.webbasics.app.layout.ILayoutExecutionContext;
import com.helger.webbasics.app.page.IWebPageExecutionContext;
import com.helger.webctrls.custom.tabbox.ITabBox;
import com.helger.webctrls.custom.table.HCTableForm;
import com.helger.webctrls.custom.table.HCTableFormView;
import com.helger.webctrls.custom.table.IHCTableForm;
import com.helger.webctrls.custom.table.IHCTableFormView;
import com.helger.webctrls.custom.toolbar.IButtonToolbar;
import com.helger.webctrls.custom.toolbar.SimpleButtonToolbar;
import com.helger.webctrls.datatables.DataTables;

public class SimpleWebPageStyler implements IWebPageStyler
{
  public static final ICSSClassProvider CSS_CLASS_INFOBOX = DefaultCSSClassProvider.create ("infobox");
  public static final ICSSClassProvider CSS_CLASS_WARNBOX = DefaultCSSClassProvider.create ("warnbox");
  public static final ICSSClassProvider CSS_CLASS_ERRORBOX = DefaultCSSClassProvider.create ("errorbox");
  public static final ICSSClassProvider CSS_CLASS_SUCCESSBOX = DefaultCSSClassProvider.create ("successbox");
  public static final ICSSClassProvider CSS_CLASS_QUESTIONBOX = DefaultCSSClassProvider.create ("questionbox");

  @Nonnull
  public AbstractHCForm <?> createForm (@Nonnull final ILayoutExecutionContext aLEC)
  {
    return new HCForm (aLEC.getSelfHref ());
  }

  @Nonnull
  public AbstractHCForm <?> createFormFileUpload (@Nonnull final ILayoutExecutionContext aLEC)
  {
    return createForm (aLEC).setEncTypeFileUpload ();
  }

  @Nullable
  public IHCNode createEmailLink (@Nullable final String sEmailAddress)
  {
    if (StringHelper.hasNoText (sEmailAddress))
      return null;
    return HCA_MailTo.createLinkedEmail (sEmailAddress);
  }

  @Nullable
  public IHCNode createEmailLink (@Nullable final IEmailAddress aEmail)
  {
    if (aEmail == null)
      return null;
    return HCA_MailTo.createLinkedEmail (aEmail.getAddress (), aEmail.getPersonal ());
  }

  @Nullable
  public IHCNode createWebLink (@Nullable final String sWebSite)
  {
    return createWebLink (sWebSite, HC_Target.BLANK);
  }

  @Nullable
  public IHCNode createWebLink (@Nullable final String sWebSite, @Nullable final HC_Target aTarget)
  {
    if (StringHelper.hasNoText (sWebSite))
      return null;
    if (!URLValidator.isValid (sWebSite))
      return new HCTextNode (sWebSite);
    return new HCA (sWebSite).setTarget (aTarget).addChild (sWebSite);
  }

  @Nonnull
  public final IHCNode createIncorrectInputBox (@Nonnull final ILayoutExecutionContext aLEC)
  {
    final Locale aDisplayLocale = aLEC.getDisplayLocale ();
    return createErrorBox (aLEC, EWebBasicsText.MSG_ERR_INCORRECT_INPUT.getDisplayText (aDisplayLocale));
  }

  @Nonnull
  public IHCElementWithChildren <?> createInfoBox (@Nonnull final ILayoutExecutionContext aWPEC,
                                                   @Nullable final String sText)
  {
    return new HCDiv ().addChild (sText).addClass (CSS_CLASS_INFOBOX);
  }

  @Nonnull
  public IHCElementWithChildren <?> createWarnBox (@Nonnull final ILayoutExecutionContext aWPEC,
                                                   @Nullable final String sText)
  {
    return new HCDiv ().addChild (sText).addClass (CSS_CLASS_WARNBOX);
  }

  @Nonnull
  public IHCElementWithChildren <?> createErrorBox (@Nonnull final ILayoutExecutionContext aWPEC,
                                                    @Nullable final String sText)
  {
    return new HCDiv ().addChild (sText).addClass (CSS_CLASS_ERRORBOX);
  }

  @Nonnull
  public IHCElementWithChildren <?> createSuccessBox (@Nonnull final ILayoutExecutionContext aWPEC,
                                                      @Nullable final String sText)
  {
    return new HCDiv ().addChild (sText).addClass (CSS_CLASS_SUCCESSBOX);
  }

  @Nonnull
  public IHCElementWithChildren <?> createQuestionBox (@Nonnull final ILayoutExecutionContext aWPEC,
                                                       @Nullable final String sText)
  {
    return new HCDiv ().addChild (sText).addClass (CSS_CLASS_QUESTIONBOX);
  }

  @Nonnull
  public IHCTable <?> createTable (@Nullable final HCCol... aCols)
  {
    return new HCTable (aCols);
  }

  @Nonnull
  public IHCTableForm <?> createTableForm (@Nullable final HCCol... aCols)
  {
    return new HCTableForm (aCols);
  }

  @Nonnull
  public IHCTableFormView <?> createTableFormView (@Nullable final HCCol... aCols)
  {
    return new HCTableFormView (aCols);
  }

  @Nonnull
  public DataTables createDefaultDataTables (@Nonnull final IWebPageExecutionContext aWPEC,
                                             @Nonnull final IHCTable <?> aTable)
  {
    final DataTables ret = new DataTables (aTable);
    ret.setDisplayLocale (aWPEC.getDisplayLocale ());
    ret.addAllColumns (aTable);
    return ret;
  }

  @Nonnull
  public IHCElement <?> createUploadButton (@Nonnull final IWebPageExecutionContext aWPEC)
  {
    final Locale aDisplayLocale = aWPEC.getDisplayLocale ();
    return new HCButton (EWebBasicsText.FILE_SELECT.getDisplayText (aDisplayLocale));
  }

  @Nonnull
  public IButtonToolbar <?> createToolbar (@Nonnull final IWebPageExecutionContext aWPEC)
  {
    return new SimpleButtonToolbar (aWPEC);
  }

  @Nonnull
  @UnsupportedOperation
  public ITabBox <?> createTabBox (@Nonnull final IWebPageExecutionContext aWPEC)
  {
    throw new UnsupportedOperationException ();
  }

  @Nullable
  public IHCNode createPasswordConstraintToolTip (@Nonnull final IWebPageExecutionContext aWPEC)
  {
    throw new UnsupportedOperationException ();
  }
}
