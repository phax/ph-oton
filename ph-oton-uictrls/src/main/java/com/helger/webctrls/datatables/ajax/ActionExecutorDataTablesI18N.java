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
package com.helger.webctrls.datatables.ajax;

import java.nio.charset.Charset;
import java.util.Locale;

import javax.annotation.Nonnull;

import com.helger.commons.CGlobal;
import com.helger.commons.ValueEnforcer;
import com.helger.commons.locale.LocaleCache;
import com.helger.commons.mime.CMimeType;
import com.helger.commons.mime.MimeType;
import com.helger.commons.string.StringHelper;
import com.helger.html.CHTMLCharset;
import com.helger.json.IJsonObject;
import com.helger.web.scopes.domain.IRequestWebScopeWithoutResponse;
import com.helger.web.servlet.response.ResponseHelperSettings;
import com.helger.web.servlet.response.UnifiedResponse;
import com.helger.webbasics.action.executor.AbstractActionExecutor;
import com.helger.webctrls.datatables.DataTables;

/**
 * Action executor for providing the DataTables translated texts
 *
 * @author Philip Helger
 */
public class ActionExecutorDataTablesI18N extends AbstractActionExecutor
{
  // This parameter must contain the locale - otherwise English is returned
  public static final String LANGUAGE_ID = "language";

  private final Locale m_aDefaultLocale;

  public ActionExecutorDataTablesI18N ()
  {
    this (CGlobal.DEFAULT_LOCALE);
  }

  public ActionExecutorDataTablesI18N (@Nonnull final Locale aDefaultLocale)
  {
    ValueEnforcer.notNull (aDefaultLocale, "DefaultLocale");
    if (StringHelper.hasNoText (aDefaultLocale.getLanguage ()))
      throw new IllegalArgumentException ("defaultLocale muts have a language: " + aDefaultLocale);
    m_aDefaultLocale = aDefaultLocale;
  }

  public void execute (@Nonnull final IRequestWebScopeWithoutResponse aRequestScope,
                       @Nonnull final UnifiedResponse aUnifiedResponse) throws Exception
  {
    // Resolve language
    final String sLanguage = aRequestScope.getAttributeAsString (LANGUAGE_ID);
    Locale aLanguage = LocaleCache.getLocale (sLanguage);
    if (aLanguage == null)
    {
      // None or invalid locale specified - use default from constructor
      aLanguage = m_aDefaultLocale;
    }

    // Main action
    final IJsonObject aData = DataTables.createLanguageJson (aLanguage);

    // Fill HTTP response
    final Charset aCharset = CHTMLCharset.CHARSET_HTML_OBJ;
    aUnifiedResponse.setContentAndCharset (aData.getAsString (), aCharset)
                    .setMimeType (new MimeType (CMimeType.APPLICATION_JSON).addParameter (CMimeType.PARAMETER_NAME_CHARSET,
                                                                                          aCharset.name ()))
                    .enableCaching (ResponseHelperSettings.getExpirationSeconds ());
  }
}
