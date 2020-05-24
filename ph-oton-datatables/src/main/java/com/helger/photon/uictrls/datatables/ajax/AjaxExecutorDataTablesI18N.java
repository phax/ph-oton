/**
 * Copyright (C) 2014-2020 Philip Helger (www.helger.com)
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
package com.helger.photon.uictrls.datatables.ajax;

import java.util.Locale;

import javax.annotation.Nonnull;

import com.helger.commons.CGlobal;
import com.helger.commons.ValueEnforcer;
import com.helger.commons.annotation.OverrideOnDemand;
import com.helger.commons.locale.LocaleCache;
import com.helger.commons.string.StringHelper;
import com.helger.json.IJsonObject;
import com.helger.photon.ajax.executor.IAjaxExecutor;
import com.helger.photon.app.PhotonUnifiedResponse;
import com.helger.photon.uictrls.datatables.DataTables;
import com.helger.servlet.response.ResponseHelperSettings;
import com.helger.web.scope.IRequestWebScopeWithoutResponse;

/**
 * Action executor for providing the DataTables translated texts
 *
 * @author Philip Helger
 */
public class AjaxExecutorDataTablesI18N implements IAjaxExecutor
{
  // This parameter must contain the locale - otherwise English is returned
  public static final String LANGUAGE_ID = "language";

  private final Locale m_aDefaultLocale;

  public AjaxExecutorDataTablesI18N ()
  {
    this (CGlobal.DEFAULT_LOCALE);
  }

  public AjaxExecutorDataTablesI18N (@Nonnull final Locale aDefaultLocale)
  {
    ValueEnforcer.notNull (aDefaultLocale, "DefaultLocale");
    if (StringHelper.hasNoText (aDefaultLocale.getLanguage ()))
      throw new IllegalArgumentException ("defaultLocale muts have a language: " + aDefaultLocale);
    m_aDefaultLocale = aDefaultLocale;
  }

  @OverrideOnDemand
  protected IJsonObject getText (@Nonnull final Locale aLanguage)
  {
    return DataTables.createLanguageJson (aLanguage);
  }

  public void handleRequest (@Nonnull final IRequestWebScopeWithoutResponse aRequestScope,
                             @Nonnull final PhotonUnifiedResponse aAjaxResponse) throws Exception
  {
    // Resolve language
    final String sLanguage = aRequestScope.params ().getAsString (LANGUAGE_ID);
    Locale aLanguage = LocaleCache.getInstance ().getLocale (sLanguage);
    if (aLanguage == null)
    {
      // None or invalid locale specified - use default from constructor
      aLanguage = m_aDefaultLocale;
    }

    // Main action
    final IJsonObject aData = getText (aLanguage);
    aAjaxResponse.json (aData);
    aAjaxResponse.enableCaching (ResponseHelperSettings.getExpirationSeconds ());
  }
}
