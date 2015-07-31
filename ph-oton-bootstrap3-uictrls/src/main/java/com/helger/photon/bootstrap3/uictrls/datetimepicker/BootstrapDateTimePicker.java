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
package com.helger.photon.bootstrap3.uictrls.datetimepicker;

import java.util.Calendar;
import java.util.Locale;
import java.util.Set;

import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import org.joda.time.LocalDate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.helger.commons.ValueEnforcer;
import com.helger.commons.annotation.ReturnsMutableCopy;
import com.helger.commons.collection.CollectionHelper;
import com.helger.commons.string.StringHelper;
import com.helger.datetime.PDTFactory;
import com.helger.datetime.format.PDTFormatPatterns;
import com.helger.html.css.DefaultCSSClassProvider;
import com.helger.html.css.ICSSClassProvider;
import com.helger.html.hc.IHCConversionSettingsToNode;
import com.helger.html.hc.IHCHasChildrenMutable;
import com.helger.html.hc.IHCNode;
import com.helger.html.hc.html.forms.HCEdit;
import com.helger.html.jquery.JQuery;
import com.helger.html.jquery.JQueryInvocation;
import com.helger.html.jscode.JSArray;
import com.helger.html.jscode.JSAssocArray;
import com.helger.html.jscode.JSInvocation;
import com.helger.html.request.IHCRequestField;
import com.helger.photon.bootstrap3.EBootstrapIcon;
import com.helger.photon.bootstrap3.inputgroup.BootstrapInputGroup;
import com.helger.photon.bootstrap3.uictrls.EBootstrapUICtrlsCSSPathProvider;
import com.helger.photon.bootstrap3.uictrls.EBootstrapUICtrlsJSPathProvider;
import com.helger.photon.core.app.html.PhotonCSS;
import com.helger.photon.core.app.html.PhotonJS;
import com.helger.photon.core.form.RequestField;
import com.helger.photon.core.form.RequestFieldDate;

/**
 * This class represents a wrapper around the DateTime Picker for Bootstrap from
 * https://github.com/smalot/bootstrap-datetimepicker<br>
 * By default it is a date selector only. If you want to have times as well, you
 * call {@link #setMinView(EDateTimePickerViewType)} with the type
 * {@link EDateTimePickerViewType#HOUR}.
 *
 * @author Philip Helger
 */
public class BootstrapDateTimePicker extends BootstrapInputGroup
{
  public static final ICSSClassProvider CSS_CLASS_DATE = DefaultCSSClassProvider.create ("date");

  public static final boolean DEFAULT_AUTOCLOSE = true;
  public static final EDateTimePickerViewType DEFAULT_START_VIEW = EDateTimePickerViewType.MONTH;
  public static final EDateTimePickerTodayType DEFAULT_TODAY = EDateTimePickerTodayType.LINKED;
  public static final boolean DEFAULT_TODAY_HIGHLIGHT = true;
  public static final boolean DEFAULT_KEYBOARD_NAVIGATION = true;
  public static final boolean DEFAULT_FORCE_PARSE = false;
  public static final int DEFAULT_MINUTE_STEP = 5;
  public static final boolean DEFAULT_SHOW_MERIDIAN = false;
  public static final boolean DEFAULT_SHOW_RESET_BUTTON = false;

  private static final Logger s_aLogger = LoggerFactory.getLogger (BootstrapDateTimePicker.class);

  private final HCEdit m_aEdit;
  private final Locale m_aDisplayLocale;
  private final EDateTimePickerLanguage m_eLanguage;

  // Settings
  private String m_sFormat;
  private EDateTimePickerDayOfWeek m_eWeekStart;
  private LocalDate m_aStartDate;
  private LocalDate m_aEndDate;
  private Set <EDateTimePickerDayOfWeek> m_aDaysOfWeekDisabled;
  private boolean m_bAutoclose = DEFAULT_AUTOCLOSE;
  private EDateTimePickerViewType m_eStartView;
  private EDateTimePickerViewType m_eMinView = EDateTimePickerViewType.MONTH;
  private EDateTimePickerViewType m_eMaxView;
  private EDateTimePickerTodayType m_eTodayBtn = DEFAULT_TODAY;
  private boolean m_bTodayHighlight = DEFAULT_TODAY_HIGHLIGHT;
  private boolean m_bKeyboardNavigation = DEFAULT_KEYBOARD_NAVIGATION;
  private boolean m_bForceParse = DEFAULT_FORCE_PARSE;
  private int m_nMinuteStep = DEFAULT_MINUTE_STEP;
  private EDateTimePickerPositionType m_ePickerPosition;
  private boolean m_bShowMeridian = DEFAULT_SHOW_MERIDIAN;
  private LocalDate m_aInitialDate;

  // UI options
  private boolean m_bShowResetButton = DEFAULT_SHOW_RESET_BUTTON;

  public BootstrapDateTimePicker (@Nonnull final RequestFieldDate aRFD)
  {
    this (aRFD.getFieldName (), aRFD.getRequestValue (), aRFD.getDisplayLocale ());
  }

  public BootstrapDateTimePicker (@Nonnull final IHCRequestField aRF, @Nonnull final Locale aDisplayLocale)
  {
    this (aRF.getFieldName (), aRF.getRequestValue (), aDisplayLocale);
  }

  public BootstrapDateTimePicker (@Nullable final String sName,
                                  @Nullable final String sValue,
                                  @Nonnull final Locale aDisplayLocale)
  {
    super (new HCEdit (new RequestField (sName, sValue)).setPlaceholder (""));
    ValueEnforcer.notNull (aDisplayLocale, "DisplayLocale");

    ensureID ();
    m_aEdit = (HCEdit) getInput ();
    m_aDisplayLocale = aDisplayLocale;
    m_eLanguage = EDateTimePickerLanguage.getFromLocaleOrNull (aDisplayLocale);
    if (m_eLanguage == null && !"en".equals (aDisplayLocale.getLanguage ()))
      s_aLogger.warn ("Unsupported EDateTimePickerLanguage provided: " + aDisplayLocale);
    m_eWeekStart = EDateTimePickerDayOfWeek.getFromJavaValueOrNull (Calendar.getInstance (aDisplayLocale)
                                                                            .getFirstDayOfWeek ());
    // Use the calendar icon as default prefix
    addPrefix (EBootstrapIcon.CALENDAR.getAsNode ());
    // Default to end date + 1 year
    setEndDate (PDTFactory.getCurrentLocalDate ().plusYears (1));
  }

  /**
   * @return The contained edit. You may modify the styles.
   */
  @Nonnull
  public HCEdit getEdit ()
  {
    return m_aEdit;
  }

  /**
   * @return The datetime picker language to use. May be <code>null</code>.
   */
  @Nullable
  public EDateTimePickerLanguage getDateTimeLanguage ()
  {
    return m_eLanguage;
  }

  /**
   * @return The control date format string.
   */
  @Nullable
  public String getFormat ()
  {
    return m_sFormat;
  }

  @Nonnull
  public BootstrapDateTimePicker setFormat (@Nullable final String sFormat)
  {
    m_sFormat = sFormat;
    return this;
  }

  @Nullable
  public EDateTimePickerDayOfWeek getWeekStart ()
  {
    return m_eWeekStart;
  }

  @Nonnull
  public BootstrapDateTimePicker setWeekStart (@Nullable final EDateTimePickerDayOfWeek eWeekStart)
  {
    m_eWeekStart = eWeekStart;
    return this;
  }

  @Nullable
  public LocalDate getStartDate ()
  {
    return m_aStartDate;
  }

  @Nonnull
  public BootstrapDateTimePicker setStartDate (@Nullable final LocalDate aStartDate)
  {
    m_aStartDate = aStartDate;
    return this;
  }

  @Nullable
  public LocalDate getEndDate ()
  {
    return m_aEndDate;
  }

  @Nonnull
  public BootstrapDateTimePicker setEndDate (@Nullable final LocalDate aEndDate)
  {
    m_aEndDate = aEndDate;
    return this;
  }

  @Nonnull
  @ReturnsMutableCopy
  public Set <EDateTimePickerDayOfWeek> getDaysOfWeekDisabled ()
  {
    return CollectionHelper.newEnumSet (EDateTimePickerDayOfWeek.class, m_aDaysOfWeekDisabled);
  }

  @Nonnull
  public BootstrapDateTimePicker setDaysOfWeekDisabled (@Nullable final Set <EDateTimePickerDayOfWeek> aDaysOfWeekDisabled)
  {
    if (aDaysOfWeekDisabled == null)
      m_aDaysOfWeekDisabled = null;
    else
      m_aDaysOfWeekDisabled = CollectionHelper.newEnumSet (EDateTimePickerDayOfWeek.class, m_aDaysOfWeekDisabled);
    return this;
  }

  public boolean isAutoclose ()
  {
    return m_bAutoclose;
  }

  @Nonnull
  public BootstrapDateTimePicker setAutoclose (final boolean bAutoclose)
  {
    m_bAutoclose = bAutoclose;
    return this;
  }

  @Nullable
  public EDateTimePickerViewType getStartView ()
  {
    return m_eStartView;
  }

  @Nonnull
  public BootstrapDateTimePicker setStartView (@Nullable final EDateTimePickerViewType eStartView)
  {
    if (eStartView != null && m_eMinView != null && eStartView.isLessThan (m_eMinView))
      throw new IllegalArgumentException ("StartView may not be before MinView");
    if (eStartView != null && m_eMaxView != null && eStartView.isGreaterThan (m_eMaxView))
      throw new IllegalArgumentException ("StartView may not be after MaxView");
    m_eStartView = eStartView;
    return this;
  }

  @Nullable
  public EDateTimePickerViewType getMinView ()
  {
    return m_eMinView;
  }

  @Nonnull
  public BootstrapDateTimePicker setMinView (@Nullable final EDateTimePickerViewType eMinView)
  {
    if (eMinView != null && m_eStartView != null && m_eStartView.isLessThan (eMinView))
      throw new IllegalArgumentException ("StartView may not be before MinView");
    if (eMinView != null && m_eMaxView != null && m_eMaxView.isLessThan (eMinView))
      throw new IllegalArgumentException ("MaxView may not be before MinView");
    m_eMinView = eMinView;
    return this;
  }

  @Nullable
  public EDateTimePickerViewType getMaxView ()
  {
    return m_eMaxView;
  }

  @Nonnull
  public BootstrapDateTimePicker setMaxView (@Nullable final EDateTimePickerViewType eMaxView)
  {
    if (eMaxView != null && m_eStartView != null && m_eStartView.isGreaterThan (eMaxView))
      throw new IllegalArgumentException ("StartView may not be after MaxView");
    if (eMaxView != null && m_eMinView != null && m_eMinView.isGreaterThan (eMaxView))
      throw new IllegalArgumentException ("MinView may not be after MaxView");
    m_eMaxView = eMaxView;
    return this;
  }

  @Nullable
  public EDateTimePickerTodayType getTodayBtn ()
  {
    return m_eTodayBtn;
  }

  @Nonnull
  public BootstrapDateTimePicker setTodayBtn (@Nullable final EDateTimePickerTodayType eTodayBtn)
  {
    m_eTodayBtn = eTodayBtn;
    return this;
  }

  public boolean isTodayHighlight ()
  {
    return m_bTodayHighlight;
  }

  @Nonnull
  public BootstrapDateTimePicker setTodayHighlight (final boolean bTodayHighlight)
  {
    m_bTodayHighlight = bTodayHighlight;
    return this;
  }

  public boolean isKeyboardNavigation ()
  {
    return m_bKeyboardNavigation;
  }

  @Nonnull
  public BootstrapDateTimePicker setKeyboardNavigation (final boolean bKeyboardNavigation)
  {
    m_bKeyboardNavigation = bKeyboardNavigation;
    return this;
  }

  public boolean isForceParse ()
  {
    return m_bForceParse;
  }

  @Nonnull
  public BootstrapDateTimePicker setForceParse (final boolean bForceParse)
  {
    m_bForceParse = bForceParse;
    return this;
  }

  public int getMinuteStep ()
  {
    return m_nMinuteStep;
  }

  @Nonnull
  public BootstrapDateTimePicker setMinuteStep (@Nonnegative final int nMinuteStep)
  {
    if (nMinuteStep < 1 || nMinuteStep > 59)
      throw new IllegalArgumentException ("Invalid minute step: " + nMinuteStep);
    m_nMinuteStep = nMinuteStep;
    return this;
  }

  @Nullable
  public EDateTimePickerPositionType getPickerPosition ()
  {
    return m_ePickerPosition;
  }

  @Nonnull
  public BootstrapDateTimePicker setPickerPosition (@Nullable final EDateTimePickerPositionType ePickerPosition)
  {
    m_ePickerPosition = ePickerPosition;
    return this;
  }

  @Nullable
  public LocalDate getInitialDate ()
  {
    return m_aInitialDate;
  }

  @Nonnull
  public BootstrapDateTimePicker setInitialDate (@Nullable final LocalDate aInitialDate)
  {
    m_aInitialDate = aInitialDate;
    return this;
  }

  public boolean isShowMeridian ()
  {
    return m_bShowMeridian;
  }

  @Nonnull
  public BootstrapDateTimePicker setShowMeridian (final boolean bShowMeridian)
  {
    m_bShowMeridian = bShowMeridian;
    return this;
  }

  public boolean isShowResetButton ()
  {
    return m_bShowResetButton;
  }

  @Nonnull
  public BootstrapDateTimePicker setShowResetButton (final boolean bShowResetButton)
  {
    m_bShowResetButton = bShowResetButton;
    return this;
  }

  public boolean isShowTime ()
  {
    return m_eMinView == null || m_eMinView.isLessThan (EDateTimePickerViewType.MONTH);
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
   * @return A {@link JSAssocArray} with all options for this Datetime Picker.
   *         Never <code>null</code>.
   */
  @Nonnull
  public JSAssocArray getJSOptions ()
  {
    final JSAssocArray aOptions = new JSAssocArray ();
    if (StringHelper.hasText (m_sFormat))
      aOptions.add ("format", m_sFormat);
    else
    {
      final String sDefaultFormat = BootstrapDateTimePickerFormatBuilder.fromJavaPattern (isShowTime () ? PDTFormatPatterns.getDefaultPatternDateTime (m_aDisplayLocale)
                                                                                                        : PDTFormatPatterns.getDefaultPatternDate (m_aDisplayLocale))
                                                                        .getJSFormatString ();
      aOptions.add ("format", sDefaultFormat);
    }
    if (m_eWeekStart != null)
      aOptions.add ("weekStart", m_eWeekStart.getJSValue ());
    if (m_aStartDate != null)
    {
      // Print ISO8601 formatted
      aOptions.add ("startDate", m_aStartDate.toString ());
    }
    if (m_aEndDate != null)
    {
      // Print ISO8601 formatted
      aOptions.add ("endDate", m_aEndDate.toString ());
    }
    if (m_aDaysOfWeekDisabled != null && !m_aDaysOfWeekDisabled.isEmpty ())
    {
      final JSArray aArray = new JSArray ();
      for (final EDateTimePickerDayOfWeek eDayOfWeek : m_aDaysOfWeekDisabled)
        aArray.add (eDayOfWeek.getJSValue ());
      aOptions.add ("daysOfWeekDisabled", aArray);
    }
    aOptions.add ("autoclose", m_bAutoclose);
    if (m_eStartView != null)
      aOptions.add ("startView", m_eStartView.getJSValueString ());
    if (m_eMinView != null)
      aOptions.add ("minView", m_eMinView.getJSValueString ());
    if (m_eMaxView != null)
      aOptions.add ("maxView", m_eMaxView.getJSValueString ());
    if (m_eTodayBtn != null)
      aOptions.add ("todayBtn", m_eTodayBtn.getJSValue ());
    aOptions.add ("todayHighlight", m_bTodayHighlight);
    aOptions.add ("keyboardNavigation", m_bKeyboardNavigation);
    if (m_eLanguage != null)
      aOptions.add ("language", m_eLanguage.getLanguageID ());
    aOptions.add ("forceParse", m_bForceParse);
    if (m_nMinuteStep != DEFAULT_MINUTE_STEP)
      aOptions.add ("minuteStep", m_nMinuteStep);
    if (m_ePickerPosition != null)
      aOptions.add ("pickerPosition", m_ePickerPosition.getJSValue ());
    aOptions.add ("showMeridian", m_bShowMeridian);
    if (m_aInitialDate != null)
    {
      // Print ISO8601 formatted
      aOptions.add ("initialDate", m_aInitialDate.toString ());
    }
    return aOptions;
  }

  @Override
  protected void onFinalizeNodeState (@Nonnull final IHCConversionSettingsToNode aConversionSettings,
                                      @Nonnull final IHCHasChildrenMutable <?, ? super IHCNode> aTargetNode)
  {
    super.onFinalizeNodeState (aConversionSettings, aTargetNode);

    if (m_bShowResetButton)
      addSuffix (EBootstrapIcon.REMOVE.getAsNode ());

    addClass (CSS_CLASS_DATE);

    // Assemble
    addChild (new BootstrapDateTimePickerJS (this));
  }

  @Override
  protected void onRegisterExternalResources (@Nonnull final IHCConversionSettingsToNode aConversionSettings,
                                              final boolean bForceRegistration)
  {
    super.onRegisterExternalResources (aConversionSettings, bForceRegistration);
    PhotonJS.registerJSIncludeForThisRequest (EBootstrapUICtrlsJSPathProvider.DATETIMEPICKER);
    if (m_eLanguage != null)
    {
      // Locales must be after the main datetime picker
      PhotonJS.registerJSIncludeForThisRequest (EBootstrapUICtrlsJSPathProvider.DATETIMEPICKER_LOCALE.getInstance (m_eLanguage.getLanguageID ()));
    }
    PhotonCSS.registerCSSIncludeForThisRequest (EBootstrapUICtrlsCSSPathProvider.DATETIMEPICKER);
  }
}
