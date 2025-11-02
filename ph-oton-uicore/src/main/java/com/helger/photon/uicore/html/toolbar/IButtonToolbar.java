/*
 * Copyright (C) 2014-2025 Philip Helger (www.helger.com)
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

import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.Nullable;

import com.helger.html.hc.html.forms.HCHiddenField;
import com.helger.html.hc.html.forms.IHCButton;
import com.helger.html.hc.html.grouping.IHCDiv;
import com.helger.html.js.IHasJSCode;
import com.helger.html.jscode.html.JSHtml;
import com.helger.photon.core.EPhotonCoreText;
import com.helger.photon.uicore.icon.EDefaultIcon;
import com.helger.photon.uicore.icon.IIcon;
import com.helger.url.ISimpleURL;

/**
 * Base button toolbar
 *
 * @author Philip Helger
 * @param <IMPLTYPE>
 *        Real implementation type
 */
public interface IButtonToolbar <IMPLTYPE extends IButtonToolbar <IMPLTYPE>> extends IHCDiv <IMPLTYPE>
{
  @NonNull
  ISimpleURL getSelfHref ();

  @NonNull
  default IMPLTYPE addHiddenField (@Nullable final String sName, final int nValue)
  {
    return addHiddenField (sName, Integer.toString (nValue));
  }

  @NonNull
  default IMPLTYPE addHiddenField (@Nullable final String sName, @Nullable final String sValue)
  {
    addChild (new HCHiddenField (sName, sValue));
    return thisAsT ();
  }

  @NonNull
  default IMPLTYPE addHiddenFields (@Nullable final Map <String, String> aValues)
  {
    if (aValues != null)
      for (final Map.Entry <String, String> aEntry : aValues.entrySet ())
        addChild (new HCHiddenField (aEntry.getKey (), aEntry.getValue ()));
    return thisAsT ();
  }

  @NonNull
  default IMPLTYPE addButton (@Nullable final String sCaption, @NonNull final IHasJSCode aOnClick)
  {
    return addButton (sCaption, aOnClick, (IIcon) null);
  }

  @NonNull
  default IMPLTYPE addButton (@Nullable final String sCaption, @NonNull final IHasJSCode aOnClick, @Nullable final IIcon aIcon)
  {
    addAndReturnButton (sCaption, aOnClick, aIcon);
    return thisAsT ();
  }

  @NonNull
  IHCButton <?> addAndReturnButton (@Nullable final String sCaption, @Nullable final IHasJSCode aOnClick, @Nullable final IIcon aIcon);

  @NonNull
  default IMPLTYPE addButton (@Nullable final String sCaption, @Nullable final ISimpleURL aURL)
  {
    return addButton (sCaption, aURL, (IIcon) null);
  }

  @NonNull
  default IMPLTYPE addButton (@Nullable final String sCaption, @Nullable final ISimpleURL aURL, @Nullable final IIcon aIcon)
  {
    addAndReturnButton (sCaption, aURL, aIcon);
    return thisAsT ();
  }

  @NonNull
  default IHCButton <?> addAndReturnButton (@Nullable final String sCaption, @Nullable final ISimpleURL aURL, @Nullable final IIcon aIcon)
  {
    return addAndReturnButton (sCaption, aURL == null ? null : JSHtml.windowLocationHref (aURL), aIcon);
  }

  @NonNull
  default IMPLTYPE addButtonBack (@NonNull final Locale aDisplayLocale, @NonNull final ISimpleURL aURL)
  {
    return addButton (EPhotonCoreText.BUTTON_BACK.getDisplayText (aDisplayLocale), aURL, EDefaultIcon.BACK);
  }

  @NonNull
  default IMPLTYPE addButtonBack (@NonNull final Locale aDisplayLocale, @NonNull final IHasJSCode aOnBack)
  {
    return addButton (EPhotonCoreText.BUTTON_BACK.getDisplayText (aDisplayLocale), aOnBack, EDefaultIcon.BACK);
  }

  @NonNull
  default IMPLTYPE addButtonBack (@NonNull final Locale aDisplayLocale)
  {
    return addButtonBack (aDisplayLocale, getSelfHref ());
  }

  @NonNull
  default IMPLTYPE addButtonCancel (@NonNull final Locale aDisplayLocale, @NonNull final ISimpleURL aURL)
  {
    return addButton (EPhotonCoreText.BUTTON_CANCEL.getDisplayText (aDisplayLocale), aURL, EDefaultIcon.CANCEL);
  }

  @NonNull
  default IMPLTYPE addButtonCancel (@NonNull final Locale aDisplayLocale, @NonNull final IHasJSCode aOnCancel)
  {
    return addButton (EPhotonCoreText.BUTTON_CANCEL.getDisplayText (aDisplayLocale), aOnCancel, EDefaultIcon.CANCEL);
  }

  @NonNull
  default IMPLTYPE addButtonCancel (@NonNull final Locale aDisplayLocale)
  {
    return addButtonCancel (aDisplayLocale, getSelfHref ());
  }

  @NonNull
  default IMPLTYPE addButtonNo (@NonNull final Locale aDisplayLocale, @NonNull final ISimpleURL aURL)
  {
    return addButton (EPhotonCoreText.BUTTON_NO.getDisplayText (aDisplayLocale), aURL, EDefaultIcon.NO);
  }

  @NonNull
  default IMPLTYPE addButtonNo (@NonNull final Locale aDisplayLocale, @NonNull final IHasJSCode aOnNo)
  {
    return addButton (EPhotonCoreText.BUTTON_NO.getDisplayText (aDisplayLocale), aOnNo, EDefaultIcon.NO);
  }

  @NonNull
  default IMPLTYPE addButtonNo (@NonNull final Locale aDisplayLocale)
  {
    return addButtonNo (aDisplayLocale, getSelfHref ());
  }

  @NonNull
  default IMPLTYPE addButtonEdit (@NonNull final Locale aDisplayLocale, @NonNull final ISimpleURL aURL)
  {
    return addButton (EPhotonCoreText.BUTTON_EDIT.getDisplayText (aDisplayLocale), aURL, EDefaultIcon.EDIT);
  }

  @NonNull
  default IMPLTYPE addButtonEdit (@NonNull final Locale aDisplayLocale, @NonNull final IHasJSCode aOnEdit)
  {
    return addButton (EPhotonCoreText.BUTTON_EDIT.getDisplayText (aDisplayLocale), aOnEdit, EDefaultIcon.EDIT);
  }

  @NonNull
  default IMPLTYPE addButtonSave (@NonNull final Locale aDisplayLocale, @NonNull final ISimpleURL aURL)
  {
    return addButton (EPhotonCoreText.BUTTON_SAVE.getDisplayText (aDisplayLocale), aURL, EDefaultIcon.SAVE);
  }

  @NonNull
  default IMPLTYPE addButtonSave (@NonNull final Locale aDisplayLocale, @NonNull final IHasJSCode aOnSave)
  {
    return addButton (EPhotonCoreText.BUTTON_SAVE.getDisplayText (aDisplayLocale), aOnSave, EDefaultIcon.SAVE);
  }

  @NonNull
  default IMPLTYPE addButtonNew (@Nullable final String sCaption, @NonNull final ISimpleURL aURL)
  {
    return addButton (sCaption, aURL, EDefaultIcon.NEW);
  }

  @NonNull
  default IMPLTYPE addSubmitButton (@Nullable final String sCaption)
  {
    return addSubmitButton (sCaption, (IHasJSCode) null, (IIcon) null);
  }

  @NonNull
  default IMPLTYPE addSubmitButton (@Nullable final String sCaption, @Nullable final IHasJSCode aOnClick)
  {
    return addSubmitButton (sCaption, aOnClick, (IIcon) null);
  }

  @NonNull
  default IMPLTYPE addSubmitButton (@Nullable final String sCaption, @Nullable final IIcon aIcon)
  {
    addAndReturnSubmitButton (sCaption, (IHasJSCode) null, aIcon);
    return thisAsT ();
  }

  @NonNull
  IHCButton <?> addAndReturnSubmitButton (@Nullable String sCaption, @Nullable IHasJSCode aOnClick, @Nullable IIcon aIcon);

  @NonNull
  default IMPLTYPE addSubmitButton (@Nullable final String sCaption, @Nullable final IHasJSCode aOnClick, @Nullable final IIcon aIcon)
  {
    addAndReturnSubmitButton (sCaption, aOnClick, aIcon);
    return thisAsT ();
  }

  @NonNull
  default IMPLTYPE addSubmitButtonSave (@NonNull final Locale aDisplayLocale)
  {
    return addSubmitButton (EPhotonCoreText.BUTTON_SAVE.getDisplayText (aDisplayLocale), EDefaultIcon.SAVE);
  }

  @NonNull
  default IMPLTYPE addSubmitButtonYes (@NonNull final Locale aDisplayLocale)
  {
    return addSubmitButton (EPhotonCoreText.BUTTON_YES.getDisplayText (aDisplayLocale), EDefaultIcon.YES);
  }
}
