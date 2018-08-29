/**
 * Copyright (C) 2014-2018 Philip Helger (www.helger.com)
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
package com.helger.photon.bootstrap4.uictrls.datetimepicker;

import java.util.Locale;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.helger.commons.ValueEnforcer;
import com.helger.commons.debug.GlobalDebug;
import com.helger.commons.string.StringHelper;
import com.helger.html.css.DefaultCSSClassProvider;
import com.helger.html.css.ICSSClassProvider;
import com.helger.html.hc.IHCConversionSettingsToNode;
import com.helger.html.hc.IHCHasChildrenMutable;
import com.helger.html.hc.IHCNode;
import com.helger.html.hc.html.IHCElement;
import com.helger.html.hc.html.forms.HCEdit;
import com.helger.html.jquery.JQuery;
import com.helger.html.jquery.JQueryInvocation;
import com.helger.html.jscode.JSAssocArray;
import com.helger.html.jscode.JSInvocation;
import com.helger.html.request.IHCRequestField;
import com.helger.photon.bootstrap4.inputgroup.BootstrapInputGroup;
import com.helger.photon.bootstrap4.uictrls.EBootstrapUICtrlsCSSPathProvider;
import com.helger.photon.bootstrap4.uictrls.EBootstrapUICtrlsJSPathProvider;
import com.helger.photon.core.app.html.PhotonCSS;
import com.helger.photon.core.app.html.PhotonJS;
import com.helger.photon.core.form.RequestField;
import com.helger.photon.core.form.RequestFieldDate;
import com.helger.photon.uicore.EUICoreJSPathProvider;
import com.helger.photon.uictrls.EUICtrlsCSSPathProvider;
import com.helger.photon.uictrls.famfam.EFamFamIcon;

/**
 * This class represents a wrapper around the DateTime Picker for Bootstrap from
 * https://github.com/tempusdominus/bootstrap-4<br>
 *
 * @author Philip Helger
 */
public class BootstrapDateTimePicker extends BootstrapInputGroup
{
  public static final ICSSClassProvider CSS_CLASS_DATE = DefaultCSSClassProvider.create ("date");
  public static final ICSSClassProvider CSS_CLASS_DATETIMEPICKER_INPUT = DefaultCSSClassProvider.create ("datetimepicker-input");

  private static final Logger LOGGER = LoggerFactory.getLogger (BootstrapDateTimePicker.class);

  private final HCEdit m_aEdit;
  private final Locale m_aDisplayLocale;

  //
  public BootstrapDateTimePicker (@Nonnull final RequestFieldDate aRFD)
  {
    this (aRFD.getFieldName (), aRFD.getRequestValue (), aRFD.getDisplayLocale ());
  }

  public BootstrapDateTimePicker (@Nonnull final IHCRequestField aRF, @Nonnull final Locale aDisplayLocale)
  {
    this (aRF.getFieldName (), aRF.getRequestValue (), aDisplayLocale);
  }

  public BootstrapDateTimePicker (@Nonnull final String sName,
                                  @Nullable final String sValue,
                                  @Nonnull final Locale aDisplayLocale)
  {
    super (new HCEdit (new RequestField (sName, sValue)));
    ValueEnforcer.notNull (aDisplayLocale, "DisplayLocale");

    m_aDisplayLocale = aDisplayLocale;

    // Customize UI
    ensureID ();
    customAttrs ().setDataAttr ("target-input", "nearest");

    m_aEdit = (HCEdit) getInput ();
    m_aEdit.setPlaceholder ("");
    m_aEdit.addClass (CSS_CLASS_DATETIMEPICKER_INPUT);
    m_aEdit.customAttrs ().setDataAttr ("toggle", "datetimepicker");
    m_aEdit.customAttrs ().setDataAttr ("target", "#" + getID ());

    // Use the calendar icon as default prefix
    final IHCElement <?> aIcon = EFamFamIcon.CALENDAR.getAsNode ();
    aIcon.customAttrs ().setDataAttr ("toggle", "datetimepicker");
    aIcon.customAttrs ().setDataAttr ("target", "#" + getID ());
    prefixes ().addChild (aIcon);
  }

  /**
   * @return The contained edit. You may modify the styles.
   */
  @Nonnull
  public HCEdit getEdit ()
  {
    return m_aEdit;
  }

  @Nonnull
  public BootstrapDateTimePicker setReadOnly (final boolean bReadOnly)
  {
    m_aEdit.setReadOnly (bReadOnly);
    return this;
  }

  @Nonnull
  public static JSInvocation invoke (@Nonnull final JQueryInvocation aJQueryInvocation)
  {
    return aJQueryInvocation.invoke ("datetimepicker");
  }

  @Nonnull
  public JSInvocation invoke ()
  {
    return invoke (JQuery.idRef (this));
  }

  @Nonnull
  public static JSInvocation invoke (@Nonnull final JQueryInvocation aJQueryInvocation,
                                     @Nonnull final JSAssocArray aOptions)
  {
    return invoke (aJQueryInvocation).arg (aOptions);
  }

  @Nonnull
  public JSInvocation invoke (@Nonnull final JSAssocArray aOptions)
  {
    return invoke ().arg (aOptions);
  }

  /**
   * @return A {@link JSAssocArray} with all options for this date and time
   *         Picker. Never <code>null</code>.
   */
  @Nonnull
  public JSAssocArray getJSOptions ()
  {
    final JSAssocArray aOptions = new JSAssocArray ();

    if (false && GlobalDebug.isDebugMode ())
      aOptions.add ("debug", true);

    if (StringHelper.hasText (m_aDisplayLocale.getLanguage ()))
      aOptions.add ("locale", m_aDisplayLocale.getLanguage ());

    return aOptions;
  }

  @Override
  protected void onFinalizeNodeState (@Nonnull final IHCConversionSettingsToNode aConversionSettings,
                                      @Nonnull final IHCHasChildrenMutable <?, ? super IHCNode> aTargetNode)
  {
    super.onFinalizeNodeState (aConversionSettings, aTargetNode);
    addClass (CSS_CLASS_DATE);

    // Add JS if necessary
    if (!m_aEdit.isReadOnly ())
      addChild (new BootstrapDateTimePickerJS (this));
  }

  @Override
  protected void onRegisterExternalResources (@Nonnull final IHCConversionSettingsToNode aConversionSettings,
                                              final boolean bForceRegistration)
  {
    super.onRegisterExternalResources (aConversionSettings, bForceRegistration);

    PhotonJS.registerJSIncludeForThisRequest (EUICoreJSPathProvider.JQUERY_3);
    PhotonJS.registerJSIncludeForThisRequest (EUICoreJSPathProvider.MOMENT);
    PhotonJS.registerJSIncludeForThisRequest (EBootstrapUICtrlsJSPathProvider.DATETIMEPICKER);

    PhotonCSS.registerCSSIncludeForThisRequest (EBootstrapUICtrlsCSSPathProvider.DATETIMEPICKER);
    PhotonCSS.registerCSSIncludeForThisRequest (EUICtrlsCSSPathProvider.FAMFAM_ICONS);
  }
}
