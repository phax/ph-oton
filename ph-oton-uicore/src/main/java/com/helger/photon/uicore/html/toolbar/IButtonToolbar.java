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
package com.helger.photon.uicore.html.toolbar;

import java.util.Locale;
import java.util.Map;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.helger.commons.url.ISimpleURL;
import com.helger.html.hchtml.forms.IHCButton;
import com.helger.html.hchtml.grouping.IHCDiv;
import com.helger.html.js.IHasJSCode;
import com.helger.photon.uicore.icon.IIcon;

/**
 * Base button toolbar
 *
 * @author Philip Helger
 * @param <IMPLTYPE>
 *        Real implementation type
 */
public interface IButtonToolbar <IMPLTYPE extends IButtonToolbar <IMPLTYPE>> extends IHCDiv <IMPLTYPE>
{
  @Nonnull
  IMPLTYPE addHiddenField (@Nullable String sName, int nValue);

  @Nonnull
  IMPLTYPE addHiddenField (@Nullable String sName, @Nullable String sValue);

  @Nonnull
  IMPLTYPE addHiddenFields (@Nullable Map <String, String> aValues);

  @Nonnull
  IMPLTYPE addButton (@Nullable String sCaption, @Nonnull IHasJSCode aJSCode);

  @Nonnull
  IMPLTYPE addButton (@Nullable String sCaption, @Nonnull IHasJSCode aJSCode, @Nullable IIcon aIcon);

  @Nonnull
  IHCButton <?> addAndReturnButton (@Nullable String sCaption, @Nullable IHasJSCode aOnClick, @Nullable IIcon aIcon);

  @Nonnull
  IMPLTYPE addButton (@Nullable String sCaption, @Nonnull ISimpleURL aURL);

  @Nonnull
  IMPLTYPE addButton (@Nullable String sCaption, @Nonnull ISimpleURL aURL, @Nullable IIcon aIcon);

  @Nonnull
  IMPLTYPE addButtonBack (@Nonnull Locale aDisplayLocale, @Nonnull ISimpleURL aURL);

  @Nonnull
  IMPLTYPE addButtonBack (@Nonnull Locale aDisplayLocale, @Nonnull IHasJSCode aOnBack);

  @Nonnull
  IMPLTYPE addButtonBack (@Nonnull Locale aDisplayLocale);

  @Nonnull
  IMPLTYPE addButtonCancel (@Nonnull Locale aDisplayLocale, @Nonnull ISimpleURL aURL);

  @Nonnull
  IMPLTYPE addButtonCancel (@Nonnull Locale aDisplayLocale, @Nonnull IHasJSCode aOnCancel);

  @Nonnull
  IMPLTYPE addButtonCancel (@Nonnull Locale aDisplayLocale);

  @Nonnull
  IMPLTYPE addButtonNo (@Nonnull Locale aDisplayLocale, @Nonnull ISimpleURL aURL);

  @Nonnull
  IMPLTYPE addButtonNo (@Nonnull Locale aDisplayLocale, @Nonnull IHasJSCode aOnNo);

  @Nonnull
  IMPLTYPE addButtonNo (@Nonnull Locale aDisplayLocale);

  @Nonnull
  IMPLTYPE addButtonEdit (@Nonnull Locale aDisplayLocale, @Nonnull ISimpleURL aURL);

  @Nonnull
  IMPLTYPE addButtonEdit (@Nonnull Locale aDisplayLocale, @Nonnull IHasJSCode aOnEdit);

  @Nonnull
  IMPLTYPE addButtonSave (@Nonnull Locale aDisplayLocale, @Nonnull ISimpleURL aURL);

  @Nonnull
  IMPLTYPE addButtonSave (@Nonnull Locale aDisplayLocale, @Nonnull IHasJSCode aOnSave);

  @Nonnull
  IMPLTYPE addButtonNew (@Nullable String sCaption, @Nonnull ISimpleURL aURL);

  @Nonnull
  IMPLTYPE addSubmitButton (@Nullable String sCaption);

  @Nonnull
  IMPLTYPE addSubmitButton (@Nullable String sCaption, @Nullable IHasJSCode aOnClick);

  @Nonnull
  IMPLTYPE addSubmitButton (@Nullable String sCaption, @Nullable IIcon aIcon);

  @Nonnull
  IHCButton <?> addAndReturnSubmitButton (@Nullable String sCaption,
                                          @Nullable IHasJSCode aOnClick,
                                          @Nullable IIcon aIcon);

  @Nonnull
  IMPLTYPE addSubmitButton (@Nullable String sCaption, @Nullable IHasJSCode aOnClick, @Nullable IIcon aIcon);

  @Nonnull
  IMPLTYPE addSubmitButtonSave (@Nonnull Locale aDisplayLocale);

  @Nonnull
  IMPLTYPE addSubmitButtonYes (@Nonnull Locale aDisplayLocale);
}
