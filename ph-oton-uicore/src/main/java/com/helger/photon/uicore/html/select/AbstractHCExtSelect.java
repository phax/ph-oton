/*
 * Copyright (C) 2014-2026 Philip Helger (www.helger.com)
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

import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.Nullable;

import com.helger.collection.commons.ICommonsList;
import com.helger.html.css.DefaultCSSClassProvider;
import com.helger.html.css.ICSSClassProvider;
import com.helger.html.hc.html.forms.AbstractHCSelect;
import com.helger.html.hc.html.forms.HCOption;
import com.helger.html.hc.impl.HCTextNode;
import com.helger.html.request.IHCRequestField;
import com.helger.html.request.IHCRequestFieldMultiValue;
import com.helger.photon.core.EPhotonCoreText;

/**
 * An extension of the simple select box, where you can add a multilingual "please select" entry.
 *
 * @author Philip Helger
 * @param <IMPLTYPE>
 *        the implementation type
 * @since 10.2.1
 */
public abstract class AbstractHCExtSelect <IMPLTYPE extends AbstractHCExtSelect <IMPLTYPE>> extends
                                          AbstractHCSelect <IMPLTYPE>
{
  /** The value of the "please select" field */
  public static final String VALUE_PLEASE_SELECT = "";
  /** The value of the "none" field */
  public static final String VALUE_NONE = "";

  public static final ICSSClassProvider CSS_CLASS_SPECIAL_OPTION = DefaultCSSClassProvider.create ("select-option-special");

  protected AbstractHCExtSelect (@NonNull final IHCRequestField aRF)
  {
    super (aRF);
  }

  protected AbstractHCExtSelect (@NonNull final IHCRequestFieldMultiValue aRF)
  {
    super (aRF);
  }

  @NonNull
  public static HCOption createSpecialOption (@Nullable final String sContent)
  {
    final HCOption aOption = new HCOption ().setValue (VALUE_PLEASE_SELECT).addChild (new HCTextNode (sContent));
    aOption.addClass (CSS_CLASS_SPECIAL_OPTION);
    return aOption;
  }

  @NonNull
  public static HCOption createOptionPleaseSelect (@NonNull final Locale aDisplayLocale)
  {
    return createSpecialOption (EPhotonCoreText.PLEASE_SELECT.getDisplayText (aDisplayLocale));
  }

  @NonNull
  public final HCOption addOptionPleaseSelect (@NonNull final Locale aDisplayLocale)
  {
    return addOptionAt (0, createOptionPleaseSelect (aDisplayLocale));
  }

  @NonNull
  public static HCOption createOptionNone (@NonNull final Locale aDisplayLocale)
  {
    return createSpecialOption (EPhotonCoreText.SELECT_NONE.getDisplayText (aDisplayLocale));
  }

  @NonNull
  public final HCOption addOptionNone (@NonNull final Locale aDisplayLocale)
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
