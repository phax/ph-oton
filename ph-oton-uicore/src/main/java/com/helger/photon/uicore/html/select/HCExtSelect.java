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
package com.helger.photon.uicore.html.select;

import java.util.Locale;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.helger.commons.collection.impl.ICommonsList;
import com.helger.html.css.DefaultCSSClassProvider;
import com.helger.html.css.ICSSClassProvider;
import com.helger.html.hc.html.forms.AbstractHCSelect;
import com.helger.html.hc.html.forms.HCOption;
import com.helger.html.hc.impl.HCTextNode;
import com.helger.html.request.IHCRequestField;
import com.helger.html.request.IHCRequestFieldMultiValue;
import com.helger.photon.core.EPhotonCoreText;

/**
 * An extension of the simple select box, where you can add a multilingual
 * "please select" entry.
 *
 * @author Philip Helger
 */
public class HCExtSelect extends AbstractHCSelect <HCExtSelect>
{
  /** The value of the "please select" field */
  public static final String VALUE_PLEASE_SELECT = "";
  /** The value of the "none" field */
  public static final String VALUE_NONE = "";

  public static final ICSSClassProvider CSS_CLASS_SPECIAL_OPTION = DefaultCSSClassProvider.create ("select-option-special");

  public HCExtSelect (@Nonnull final IHCRequestField aRF)
  {
    super (aRF);
  }

  public HCExtSelect (@Nonnull final IHCRequestFieldMultiValue aRF)
  {
    super (aRF);
  }

  @Nonnull
  public static HCOption createSpecialOption (@Nullable final String sContent)
  {
    final HCOption aOption = new HCOption ().setValue (VALUE_PLEASE_SELECT).addChild (new HCTextNode (sContent));
    aOption.addClass (CSS_CLASS_SPECIAL_OPTION);
    return aOption;
  }

  @Nonnull
  public static HCOption createOptionPleaseSelect (@Nonnull final Locale aDisplayLocale)
  {
    return createSpecialOption (EPhotonCoreText.PLEASE_SELECT.getDisplayText (aDisplayLocale));
  }

  @Nonnull
  public final HCOption addOptionPleaseSelect (@Nonnull final Locale aDisplayLocale)
  {
    return addOptionAt (0, createOptionPleaseSelect (aDisplayLocale));
  }

  @Nonnull
  public static HCOption createOptionNone (@Nonnull final Locale aDisplayLocale)
  {
    return createSpecialOption (EPhotonCoreText.SELECT_NONE.getDisplayText (aDisplayLocale));
  }

  @Nonnull
  public final HCOption addOptionNone (@Nonnull final Locale aDisplayLocale)
  {
    return addOptionAt (0, createOptionNone (aDisplayLocale));
  }

  public final boolean containsEffectiveOption ()
  {
    // If at least one option is not the "special option" then an effective
    // option is present
    final ICommonsList <HCOption> aAllOptions = getAllOptions ();
    return aAllOptions.containsAny (x -> !x.containsClass (CSS_CLASS_SPECIAL_OPTION));
  }
}
