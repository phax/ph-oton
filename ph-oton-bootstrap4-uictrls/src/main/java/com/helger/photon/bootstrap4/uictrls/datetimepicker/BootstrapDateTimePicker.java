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

import com.helger.commons.ValueEnforcer;
import com.helger.commons.annotation.OverrideOnDemand;
import com.helger.commons.datetime.PDTFormatPatterns;
import com.helger.commons.debug.GlobalDebug;
import com.helger.commons.functional.IFunction;
import com.helger.commons.state.ETriState;
import com.helger.commons.string.StringHelper;
import com.helger.html.css.DefaultCSSClassProvider;
import com.helger.html.css.ICSSClassProvider;
import com.helger.html.hc.IHCConversionSettingsToNode;
import com.helger.html.hc.IHCHasChildrenMutable;
import com.helger.html.hc.IHCNode;
import com.helger.html.hc.html.forms.HCEdit;
import com.helger.html.hc.html.grouping.HCDiv;
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
import com.helger.photon.icon.fontawesome.EFontAwesome4Icon;
import com.helger.photon.uicore.EUICoreJSPathProvider;

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

  public static EBootstrap4DateTimePickerViewModeType DEFAULT_VIEW_MODE = EBootstrap4DateTimePickerViewModeType.DAYS;

  public static enum EMode
  {
    DATE (d -> PDTFormatPatterns.getDefaultPatternDate (d)),
    DATE_TIME (d -> PDTFormatPatterns.getDefaultPatternDateTime (d)),
    TIME (d -> PDTFormatPatterns.getDefaultPatternTime (d));

    public static final EMode DEFAULT = DATE;

    private final IFunction <Locale, String> m_aFormatSupplier;

    private EMode (@Nonnull final IFunction <Locale, String> aFormatSupplier)
    {
      m_aFormatSupplier = aFormatSupplier;
    }

    @Nonnull
    public String getFormat (@Nonnull final Locale aDisplayLocale)
    {
      final String s = m_aFormatSupplier.apply (aDisplayLocale);
      return Bootstrap4DateTimePickerFormatBuilder.fromJavaPattern (s).getJSCalendarFormatString ();
    }
  }

  private final HCEdit m_aEdit;
  private final Locale m_aDisplayLocale;
  private EMode m_eMode = EMode.DEFAULT;
  private EBootstrap4DateTimePickerViewModeType m_eViewMode;
  private ETriState m_eSideBySide = ETriState.UNDEFINED;

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
    prefixes ().addChild (EFontAwesome4Icon.CALENDAR.getAsNode ());
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
  public final BootstrapDateTimePicker setReadOnly (final boolean bReadOnly)
  {
    m_aEdit.setReadOnly (bReadOnly);
    return this;
  }

  @Nonnull
  public EMode getMode ()
  {
    return m_eMode;
  }

  @Nonnull
  public final BootstrapDateTimePicker setMode (@Nullable final EMode eMode)
  {
    m_eMode = eMode;
    return this;
  }

  @Nullable
  public EBootstrap4DateTimePickerViewModeType getViewMode ()
  {
    return m_eViewMode;
  }

  @Nonnull
  public final BootstrapDateTimePicker setViewMode (@Nullable final EBootstrap4DateTimePickerViewModeType eViewMode)
  {
    m_eViewMode = eViewMode;
    return this;
  }

  /**
   * Show date and time picker side by side?
   *
   * @return Never <code>null</code>
   */
  @Nonnull
  public ETriState getSideBySide ()
  {
    return m_eSideBySide;
  }

  @Nonnull
  public final BootstrapDateTimePicker setSideBySide (final boolean bSideBySide)
  {
    m_eSideBySide = ETriState.valueOf (bSideBySide);
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

  private void _add (@Nonnull final JSAssocArray ret,
                     @Nonnull final String sKey,
                     @Nonnull final EBootstrap4DateTimePickerTexts eText)
  {
    final String sValue = eText.getDisplayText (m_aDisplayLocale);
    if (StringHelper.hasText (sValue))
      ret.add (sKey, sValue);
  }

  @Nonnull
  public JSAssocArray getJSTooltipTexts ()
  {
    final JSAssocArray ret = new JSAssocArray ();
    _add (ret, "today", EBootstrap4DateTimePickerTexts.TODAY);
    _add (ret, "clear", EBootstrap4DateTimePickerTexts.CLEAR);
    _add (ret, "close", EBootstrap4DateTimePickerTexts.CLOSE);
    _add (ret, "selectMonth", EBootstrap4DateTimePickerTexts.SELECTMONTH);
    _add (ret, "prevMonth", EBootstrap4DateTimePickerTexts.PREVMONTH);
    _add (ret, "nextMonth", EBootstrap4DateTimePickerTexts.NEXTMONTH);
    _add (ret, "selectYear", EBootstrap4DateTimePickerTexts.SELECTYEAR);
    _add (ret, "prevYear", EBootstrap4DateTimePickerTexts.PREVYEAR);
    _add (ret, "nextYear", EBootstrap4DateTimePickerTexts.NEXTYEAR);
    _add (ret, "selectDecade", EBootstrap4DateTimePickerTexts.SELECTDECADE);
    _add (ret, "prevDecade", EBootstrap4DateTimePickerTexts.PREVDECADE);
    _add (ret, "nextDecade", EBootstrap4DateTimePickerTexts.NEXTDECADE);
    _add (ret, "prevCentury", EBootstrap4DateTimePickerTexts.PREVCENTURY);
    _add (ret, "nextCentury", EBootstrap4DateTimePickerTexts.NEXTCENTURY);
    _add (ret, "incrementHour", EBootstrap4DateTimePickerTexts.INCREMENTHOUR);
    _add (ret, "pickHour", EBootstrap4DateTimePickerTexts.PICKHOUR);
    _add (ret, "decrementHour", EBootstrap4DateTimePickerTexts.DECREMENTHOUR);
    _add (ret, "incrementMinute", EBootstrap4DateTimePickerTexts.INCREMENTMINUTE);
    _add (ret, "pickMinute", EBootstrap4DateTimePickerTexts.PICKMINUTE);
    _add (ret, "decrementMinute", EBootstrap4DateTimePickerTexts.DECREMENTMINUTE);
    _add (ret, "incrementSecond", EBootstrap4DateTimePickerTexts.INCREMENTSECOND);
    _add (ret, "pickSecond", EBootstrap4DateTimePickerTexts.PICKSECOND);
    _add (ret, "decrementSecond", EBootstrap4DateTimePickerTexts.DECREMENTSECOND);
    return ret;
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
    if (m_eViewMode != null)
      aOptions.add ("viewMode", m_eViewMode.getJSValueString ());

    final JSAssocArray aTooltips = getJSTooltipTexts ();
    if (aTooltips.isNotEmpty ())
      aOptions.add ("tooltips", aTooltips);

    if (m_eSideBySide.isDefined ())
      aOptions.add ("sideBySide", m_eSideBySide.getAsBooleanValue ());

    aOptions.add ("calendarWeeks", true);

    aOptions.add ("format", m_eMode.getFormat (m_aDisplayLocale));

    return aOptions;
  }

  @Override
  @Nonnull
  @OverrideOnDemand
  protected HCDiv createPrependGroup ()
  {
    // Make the whole prepend thing a toggle
    final HCDiv ret = super.createPrependGroup ();
    ret.customAttrs ().setDataAttr ("toggle", "datetimepicker");
    ret.customAttrs ().setDataAttr ("target", "#" + getID ());
    return ret;
  }

  @Override
  protected void onFinalizeNodeState (@Nonnull final IHCConversionSettingsToNode aConversionSettings,
                                      @Nonnull final IHCHasChildrenMutable <?, ? super IHCNode> aTargetNode)
  {
    super.onFinalizeNodeState (aConversionSettings, aTargetNode);
    addClass (CSS_CLASS_DATE);

    // Add JS if necessary
    if (!m_aEdit.isReadOnly ())
      addChild (new Bootstrap4DateTimePickerJS (this));
  }

  @Override
  protected void onRegisterExternalResources (@Nonnull final IHCConversionSettingsToNode aConversionSettings,
                                              final boolean bForceRegistration)
  {
    super.onRegisterExternalResources (aConversionSettings, bForceRegistration);
    registerResourcesForThisRequest ();
  }

  public static void registerResourcesForThisRequest ()
  {
    PhotonJS.registerJSIncludeForThisRequest (EUICoreJSPathProvider.JQUERY_3);
    PhotonJS.registerJSIncludeForThisRequest (EUICoreJSPathProvider.MOMENT);
    PhotonJS.registerJSIncludeForThisRequest (EBootstrapUICtrlsJSPathProvider.DATETIMEPICKER);

    EFontAwesome4Icon.registerResourcesForThisRequest ();
    PhotonCSS.registerCSSIncludeForThisRequest (EBootstrapUICtrlsCSSPathProvider.DATETIMEPICKER);
  }
}
