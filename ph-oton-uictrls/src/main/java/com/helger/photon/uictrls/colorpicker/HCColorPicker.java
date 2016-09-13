/**
 * Copyright (C) 2014-2016 Philip Helger (www.helger.com)
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
package com.helger.photon.uictrls.colorpicker;

import javax.annotation.Nonnull;
import javax.annotation.OverridingMethodsMustInvokeSuper;

import com.helger.commons.ValueEnforcer;
import com.helger.commons.annotation.OverrideOnDemand;
import com.helger.commons.annotation.ReturnsMutableObject;
import com.helger.html.css.DefaultCSSClassProvider;
import com.helger.html.css.ICSSClassProvider;
import com.helger.html.css.UncheckedCSSClassProvider;
import com.helger.html.hc.IHCConversionSettingsToNode;
import com.helger.html.hc.IHCHasChildrenMutable;
import com.helger.html.hc.IHCNode;
import com.helger.html.hc.html.forms.AbstractHCInput;
import com.helger.html.jscode.JSAssocArray;
import com.helger.html.request.IHCRequestField;
import com.helger.photon.core.app.html.PhotonCSS;
import com.helger.photon.core.app.html.PhotonJS;
import com.helger.photon.uictrls.EUICtrlsCSSPathProvider;
import com.helger.photon.uictrls.EUICtrlsJSPathProvider;

/**
 * A wrapper around the JSColor color picker control. http://jscolor.com/
 *
 * @author Philip Helger
 */
public class HCColorPicker extends AbstractHCInput <HCColorPicker>
{
  public static final String CSS_CLASS_NAME_COLOR = "color";
  public static final ICSSClassProvider CSS_CLASS_COLOR = DefaultCSSClassProvider.create (CSS_CLASS_NAME_COLOR);

  private ColorPickerOptions m_aOptions = new ColorPickerOptions ();

  public HCColorPicker ()
  {
    // No input type needed
  }

  public HCColorPicker (@Nonnull final IHCRequestField aRF)
  {
    this ();
    setName (aRF.getFieldName ());
    setValue (aRF.getRequestValue ());
  }

  @Nonnull
  @ReturnsMutableObject ("design")
  public ColorPickerOptions getOptions ()
  {
    return m_aOptions;
  }

  @Nonnull
  public HCColorPicker setOptions (@Nonnull final ColorPickerOptions aOptions)
  {
    ValueEnforcer.notNull (aOptions, "Options");
    m_aOptions = aOptions;
    return this;
  }

  @Override
  @OverrideOnDemand
  @OverridingMethodsMustInvokeSuper
  protected void onFinalizeNodeState (@Nonnull final IHCConversionSettingsToNode aConversionSettings,
                                      @Nonnull final IHCHasChildrenMutable <?, ? super IHCNode> aTargetNode)
  {
    super.onFinalizeNodeState (aConversionSettings, aTargetNode);

    final JSAssocArray aJSOptions = m_aOptions.getJSOptions ();
    if (aJSOptions.isEmpty ())
    {
      // No options - add only "color" class
      addClass (CSS_CLASS_COLOR);
    }
    else
    {
      // Append options
      final String sFullClass = CSS_CLASS_NAME_COLOR + " " + aJSOptions.getJSCode ();
      addClass (new UncheckedCSSClassProvider (sFullClass));
    }
  }

  @Override
  protected void onRegisterExternalResources (@Nonnull final IHCConversionSettingsToNode aConversionSettings,
                                              final boolean bForceRegistration)
  {
    super.onRegisterExternalResources (aConversionSettings, bForceRegistration);
    PhotonJS.registerJSIncludeForThisRequest (EUICtrlsJSPathProvider.JSCOLOR);
    PhotonCSS.registerCSSIncludeForThisRequest (EUICtrlsCSSPathProvider.JSCOLOR);
  }
}
