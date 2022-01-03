/*
 * Copyright (C) 2014-2022 Philip Helger (www.helger.com)
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
import com.helger.html.hc.html.forms.HCHiddenField;
import com.helger.html.hc.html.forms.IHCButton;
import com.helger.html.hc.html.grouping.IHCDiv;
import com.helger.html.js.IHasJSCode;
import com.helger.html.jscode.html.JSHtml;
import com.helger.photon.core.EPhotonCoreText;
import com.helger.photon.uicore.icon.EDefaultIcon;
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
  ISimpleURL getSelfHref ();

  @Nonnull
  default IMPLTYPE addHiddenField (@Nullable final String sName, final int nValue)
  {
    return addHiddenField (sName, Integer.toString (nValue));
  }

  @Nonnull
  default IMPLTYPE addHiddenField (@Nullable final String sName, @Nullable final String sValue)
  {
    addChild (new HCHiddenField (sName, sValue));
    return thisAsT ();
  }

  @Nonnull
  default IMPLTYPE addHiddenFields (@Nullable final Map <String, String> aValues)
  {
    if (aValues != null)
      for (final Map.Entry <String, String> aEntry : aValues.entrySet ())
        addChild (new HCHiddenField (aEntry.getKey (), aEntry.getValue ()));
    return thisAsT ();
  }

  @Nonnull
  default IMPLTYPE addButton (@Nullable final String sCaption, @Nonnull final IHasJSCode aOnClick)
  {
    return addButton (sCaption, aOnClick, (IIcon) null);
  }

  @Nonnull
  default IMPLTYPE addButton (@Nullable final String sCaption, @Nonnull final IHasJSCode aOnClick, @Nullable final IIcon aIcon)
  {
    addAndReturnButton (sCaption, aOnClick, aIcon);
    return thisAsT ();
  }

  @Nonnull
  IHCButton <?> addAndReturnButton (@Nullable final String sCaption, @Nullable final IHasJSCode aOnClick, @Nullable final IIcon aIcon);

  @Nonnull
  default IMPLTYPE addButton (@Nullable final String sCaption, @Nullable final ISimpleURL aURL)
  {
    return addButton (sCaption, aURL, (IIcon) null);
  }

  @Nonnull
  default IMPLTYPE addButton (@Nullable final String sCaption, @Nullable final ISimpleURL aURL, @Nullable final IIcon aIcon)
  {
    addAndReturnButton (sCaption, aURL, aIcon);
    return thisAsT ();
  }

  @Nonnull
  default IHCButton <?> addAndReturnButton (@Nullable final String sCaption, @Nullable final ISimpleURL aURL, @Nullable final IIcon aIcon)
  {
    return addAndReturnButton (sCaption, aURL == null ? null : JSHtml.windowLocationHref (aURL), aIcon);
  }

  @Nonnull
  default IMPLTYPE addButtonBack (@Nonnull final Locale aDisplayLocale, @Nonnull final ISimpleURL aURL)
  {
    return addButton (EPhotonCoreText.BUTTON_BACK.getDisplayText (aDisplayLocale), aURL, EDefaultIcon.BACK);
  }

  @Nonnull
  default IMPLTYPE addButtonBack (@Nonnull final Locale aDisplayLocale, @Nonnull final IHasJSCode aOnBack)
  {
    return addButton (EPhotonCoreText.BUTTON_BACK.getDisplayText (aDisplayLocale), aOnBack, EDefaultIcon.BACK);
  }

  @Nonnull
  default IMPLTYPE addButtonBack (@Nonnull final Locale aDisplayLocale)
  {
    return addButtonBack (aDisplayLocale, getSelfHref ());
  }

  @Nonnull
  default IMPLTYPE addButtonCancel (@Nonnull final Locale aDisplayLocale, @Nonnull final ISimpleURL aURL)
  {
    return addButton (EPhotonCoreText.BUTTON_CANCEL.getDisplayText (aDisplayLocale), aURL, EDefaultIcon.CANCEL);
  }

  @Nonnull
  default IMPLTYPE addButtonCancel (@Nonnull final Locale aDisplayLocale, @Nonnull final IHasJSCode aOnCancel)
  {
    return addButton (EPhotonCoreText.BUTTON_CANCEL.getDisplayText (aDisplayLocale), aOnCancel, EDefaultIcon.CANCEL);
  }

  @Nonnull
  default IMPLTYPE addButtonCancel (@Nonnull final Locale aDisplayLocale)
  {
    return addButtonCancel (aDisplayLocale, getSelfHref ());
  }

  @Nonnull
  default IMPLTYPE addButtonNo (@Nonnull final Locale aDisplayLocale, @Nonnull final ISimpleURL aURL)
  {
    return addButton (EPhotonCoreText.BUTTON_NO.getDisplayText (aDisplayLocale), aURL, EDefaultIcon.NO);
  }

  @Nonnull
  default IMPLTYPE addButtonNo (@Nonnull final Locale aDisplayLocale, @Nonnull final IHasJSCode aOnNo)
  {
    return addButton (EPhotonCoreText.BUTTON_NO.getDisplayText (aDisplayLocale), aOnNo, EDefaultIcon.NO);
  }

  @Nonnull
  default IMPLTYPE addButtonNo (@Nonnull final Locale aDisplayLocale)
  {
    return addButtonNo (aDisplayLocale, getSelfHref ());
  }

  @Nonnull
  default IMPLTYPE addButtonEdit (@Nonnull final Locale aDisplayLocale, @Nonnull final ISimpleURL aURL)
  {
    return addButton (EPhotonCoreText.BUTTON_EDIT.getDisplayText (aDisplayLocale), aURL, EDefaultIcon.EDIT);
  }

  @Nonnull
  default IMPLTYPE addButtonEdit (@Nonnull final Locale aDisplayLocale, @Nonnull final IHasJSCode aOnEdit)
  {
    return addButton (EPhotonCoreText.BUTTON_EDIT.getDisplayText (aDisplayLocale), aOnEdit, EDefaultIcon.EDIT);
  }

  @Nonnull
  default IMPLTYPE addButtonSave (@Nonnull final Locale aDisplayLocale, @Nonnull final ISimpleURL aURL)
  {
    return addButton (EPhotonCoreText.BUTTON_SAVE.getDisplayText (aDisplayLocale), aURL, EDefaultIcon.SAVE);
  }

  @Nonnull
  default IMPLTYPE addButtonSave (@Nonnull final Locale aDisplayLocale, @Nonnull final IHasJSCode aOnSave)
  {
    return addButton (EPhotonCoreText.BUTTON_SAVE.getDisplayText (aDisplayLocale), aOnSave, EDefaultIcon.SAVE);
  }

  @Nonnull
  default IMPLTYPE addButtonNew (@Nullable final String sCaption, @Nonnull final ISimpleURL aURL)
  {
    return addButton (sCaption, aURL, EDefaultIcon.NEW);
  }

  @Nonnull
  default IMPLTYPE addSubmitButton (@Nullable final String sCaption)
  {
    return addSubmitButton (sCaption, (IHasJSCode) null, (IIcon) null);
  }

  @Nonnull
  default IMPLTYPE addSubmitButton (@Nullable final String sCaption, @Nullable final IHasJSCode aOnClick)
  {
    return addSubmitButton (sCaption, aOnClick, (IIcon) null);
  }

  @Nonnull
  default IMPLTYPE addSubmitButton (@Nullable final String sCaption, @Nullable final IIcon aIcon)
  {
    addAndReturnSubmitButton (sCaption, (IHasJSCode) null, aIcon);
    return thisAsT ();
  }

  @Nonnull
  IHCButton <?> addAndReturnSubmitButton (@Nullable String sCaption, @Nullable IHasJSCode aOnClick, @Nullable IIcon aIcon);

  @Nonnull
  default IMPLTYPE addSubmitButton (@Nullable final String sCaption, @Nullable final IHasJSCode aOnClick, @Nullable final IIcon aIcon)
  {
    addAndReturnSubmitButton (sCaption, aOnClick, aIcon);
    return thisAsT ();
  }

  @Nonnull
  default IMPLTYPE addSubmitButtonSave (@Nonnull final Locale aDisplayLocale)
  {
    return addSubmitButton (EPhotonCoreText.BUTTON_SAVE.getDisplayText (aDisplayLocale), EDefaultIcon.SAVE);
  }

  @Nonnull
  default IMPLTYPE addSubmitButtonYes (@Nonnull final Locale aDisplayLocale)
  {
    return addSubmitButton (EPhotonCoreText.BUTTON_YES.getDisplayText (aDisplayLocale), EDefaultIcon.YES);
  }
}
