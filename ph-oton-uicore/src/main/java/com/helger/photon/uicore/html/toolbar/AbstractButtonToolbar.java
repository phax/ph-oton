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

import com.helger.commons.ValueEnforcer;
import com.helger.commons.url.ISimpleURL;
import com.helger.commons.url.SimpleURL;
import com.helger.html.hc.base.AbstractHCDiv;
import com.helger.html.hc.html.HCHiddenField;
import com.helger.html.js.IHasJSCode;
import com.helger.html.jscode.html.JSHtml;
import com.helger.photon.core.EPhotonCoreText;
import com.helger.photon.uicore.icon.EDefaultIcon;
import com.helger.photon.uicore.icon.IIcon;

/**
 * Abstract button toolbar implementation
 *
 * @author Philip Helger
 * @param <IMPLTYPE>
 *        Implementation type
 */
public abstract class AbstractButtonToolbar <IMPLTYPE extends AbstractButtonToolbar <IMPLTYPE>> extends AbstractHCDiv <IMPLTYPE> implements IButtonToolbar <IMPLTYPE>
{
  private final SimpleURL m_aSelfHref;

  public AbstractButtonToolbar (@Nonnull final SimpleURL aSelfHref)
  {
    m_aSelfHref = ValueEnforcer.notNull (aSelfHref, "SelfHref");
  }

  @Nonnull
  public ISimpleURL getSelfHref ()
  {
    return m_aSelfHref;
  }

  @Nonnull
  public final IMPLTYPE addHiddenField (@Nullable final String sName, final int nValue)
  {
    addChild (new HCHiddenField (sName, nValue));
    return thisAsT ();
  }

  @Nonnull
  public final IMPLTYPE addHiddenField (@Nullable final String sName, @Nullable final String sValue)
  {
    addChild (new HCHiddenField (sName, sValue));
    return thisAsT ();
  }

  @Nonnull
  public final IMPLTYPE addHiddenFields (@Nullable final Map <String, String> aValues)
  {
    if (aValues != null)
      for (final Map.Entry <String, String> aEntry : aValues.entrySet ())
        addChild (new HCHiddenField (aEntry.getKey (), aEntry.getValue ()));
    return thisAsT ();
  }

  @Nonnull
  public final IMPLTYPE addButton (@Nullable final String sCaption, @Nonnull final IHasJSCode aJSCode)
  {
    return addButton (sCaption, aJSCode, (IIcon) null);
  }

  @Nonnull
  public final IMPLTYPE addButton (@Nullable final String sCaption,
                                   @Nonnull final IHasJSCode aOnClick,
                                   @Nullable final IIcon aIcon)
  {
    addAndReturnButton (sCaption, aOnClick, aIcon);
    return thisAsT ();
  }

  @Nonnull
  public final IMPLTYPE addButton (@Nullable final String sCaption, @Nonnull final ISimpleURL aURL)
  {
    return addButton (sCaption, aURL, (IIcon) null);
  }

  @Nonnull
  public final IMPLTYPE addButton (@Nullable final String sCaption,
                                   @Nonnull final ISimpleURL aURL,
                                   @Nullable final IIcon aIcon)
  {
    return addButton (sCaption, JSHtml.windowLocationHref (aURL), aIcon);
  }

  @Nonnull
  public IMPLTYPE addButtonBack (@Nonnull final Locale aDisplayLocale, @Nonnull final ISimpleURL aURL)
  {
    return addButton (EPhotonCoreText.BUTTON_BACK.getDisplayText (aDisplayLocale), aURL, EDefaultIcon.BACK);
  }

  @Nonnull
  public IMPLTYPE addButtonBack (@Nonnull final Locale aDisplayLocale, @Nonnull final IHasJSCode aOnBack)
  {
    return addButton (EPhotonCoreText.BUTTON_BACK.getDisplayText (aDisplayLocale), aOnBack, EDefaultIcon.BACK);
  }

  @Nonnull
  public IMPLTYPE addButtonBack (@Nonnull final Locale aDisplayLocale)
  {
    return addButtonBack (aDisplayLocale, m_aSelfHref);
  }

  @Nonnull
  public IMPLTYPE addButtonCancel (@Nonnull final Locale aDisplayLocale, @Nonnull final ISimpleURL aURL)
  {
    return addButton (EPhotonCoreText.BUTTON_CANCEL.getDisplayText (aDisplayLocale), aURL, EDefaultIcon.CANCEL);
  }

  @Nonnull
  public IMPLTYPE addButtonCancel (@Nonnull final Locale aDisplayLocale, @Nonnull final IHasJSCode aOnCancel)
  {
    return addButton (EPhotonCoreText.BUTTON_CANCEL.getDisplayText (aDisplayLocale), aOnCancel, EDefaultIcon.CANCEL);
  }

  @Nonnull
  public IMPLTYPE addButtonCancel (@Nonnull final Locale aDisplayLocale)
  {
    return addButtonCancel (aDisplayLocale, m_aSelfHref);
  }

  @Nonnull
  public IMPLTYPE addButtonNo (@Nonnull final Locale aDisplayLocale, @Nonnull final ISimpleURL aURL)
  {
    return addButton (EPhotonCoreText.BUTTON_NO.getDisplayText (aDisplayLocale), aURL, EDefaultIcon.NO);
  }

  @Nonnull
  public IMPLTYPE addButtonNo (@Nonnull final Locale aDisplayLocale, @Nonnull final IHasJSCode aOnNo)
  {
    return addButton (EPhotonCoreText.BUTTON_NO.getDisplayText (aDisplayLocale), aOnNo, EDefaultIcon.NO);
  }

  @Nonnull
  public IMPLTYPE addButtonNo (@Nonnull final Locale aDisplayLocale)
  {
    return addButtonNo (aDisplayLocale, m_aSelfHref);
  }

  @Nonnull
  public IMPLTYPE addButtonEdit (@Nonnull final Locale aDisplayLocale, @Nonnull final ISimpleURL aURL)
  {
    return addButton (EPhotonCoreText.BUTTON_EDIT.getDisplayText (aDisplayLocale), aURL, EDefaultIcon.EDIT);
  }

  @Nonnull
  public IMPLTYPE addButtonEdit (@Nonnull final Locale aDisplayLocale, @Nonnull final IHasJSCode aOnEdit)
  {
    return addButton (EPhotonCoreText.BUTTON_EDIT.getDisplayText (aDisplayLocale), aOnEdit, EDefaultIcon.EDIT);
  }

  @Nonnull
  public IMPLTYPE addButtonSave (@Nonnull final Locale aDisplayLocale, @Nonnull final ISimpleURL aURL)
  {
    return addButton (EPhotonCoreText.BUTTON_SAVE.getDisplayText (aDisplayLocale), aURL, EDefaultIcon.SAVE);
  }

  @Nonnull
  public IMPLTYPE addButtonSave (@Nonnull final Locale aDisplayLocale, @Nonnull final IHasJSCode aOnSave)
  {
    return addButton (EPhotonCoreText.BUTTON_SAVE.getDisplayText (aDisplayLocale), aOnSave, EDefaultIcon.SAVE);
  }

  @Nonnull
  public IMPLTYPE addButtonNew (@Nullable final String sCaption, @Nonnull final ISimpleURL aURL)
  {
    return addButton (sCaption, aURL, EDefaultIcon.NEW);
  }

  @Nonnull
  public final IMPLTYPE addSubmitButton (@Nullable final String sCaption)
  {
    return addSubmitButton (sCaption, (IHasJSCode) null, (IIcon) null);
  }

  @Nonnull
  public final IMPLTYPE addSubmitButton (@Nullable final String sCaption, @Nullable final IHasJSCode aOnClick)
  {
    return addSubmitButton (sCaption, aOnClick, (IIcon) null);
  }

  @Nonnull
  public final IMPLTYPE addSubmitButton (@Nullable final String sCaption,
                                         @Nullable final IHasJSCode aOnClick,
                                         @Nullable final IIcon aIcon)
  {
    addAndReturnSubmitButton (sCaption, aOnClick, aIcon);
    return thisAsT ();
  }

  @Nonnull
  public final IMPLTYPE addSubmitButton (@Nullable final String sCaption, @Nullable final IIcon aIcon)
  {
    return addSubmitButton (sCaption, (IHasJSCode) null, aIcon);
  }

  @Nonnull
  public final IMPLTYPE addSubmitButtonSave (@Nonnull final Locale aDisplayLocale)
  {
    return addSubmitButton (EPhotonCoreText.BUTTON_SAVE.getDisplayText (aDisplayLocale), EDefaultIcon.SAVE);
  }

  @Nonnull
  public final IMPLTYPE addSubmitButtonYes (@Nonnull final Locale aDisplayLocale)
  {
    return addSubmitButton (EPhotonCoreText.BUTTON_YES.getDisplayText (aDisplayLocale), EDefaultIcon.YES);
  }
}
