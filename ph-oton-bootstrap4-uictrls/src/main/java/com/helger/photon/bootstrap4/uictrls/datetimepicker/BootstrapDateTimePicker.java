/*
 * Copyright (C) 2018-2022 Philip Helger (www.helger.com)
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

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.helger.commons.ValueEnforcer;
import com.helger.commons.annotation.OverrideOnDemand;
import com.helger.commons.annotation.ReturnsMutableObject;
import com.helger.commons.collection.impl.CommonsLinkedHashMap;
import com.helger.commons.collection.impl.ICommonsOrderedMap;
import com.helger.commons.datetime.PDTFactory;
import com.helger.commons.datetime.PDTToString;
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
import com.helger.json.IJsonObject;
import com.helger.json.JsonObject;
import com.helger.photon.app.html.PhotonCSS;
import com.helger.photon.app.html.PhotonJS;
import com.helger.photon.bootstrap4.form.BootstrapFormHelper;
import com.helger.photon.bootstrap4.inputgroup.BootstrapInputGroup;
import com.helger.photon.bootstrap4.uictrls.EBootstrapUICtrlsCSSPathProvider;
import com.helger.photon.bootstrap4.uictrls.EBootstrapUICtrlsJSPathProvider;
import com.helger.photon.core.form.RequestField;
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

  public static final String EVENT_SUFFIX = ".datetimepicker";
  public static final String EVENT_NAME_CHANGE = "change" + EVENT_SUFFIX;

  public static final EBootstrap4DateTimePickerViewModeType DEFAULT_VIEW_MODE = EBootstrap4DateTimePickerViewModeType.DAYS;

  // Use the calendar icon as default prefix
  public static final IHCNode DEFAULT_PREPEND_ICON = EFontAwesome4Icon.CALENDAR.getAsNode ();

  private static final LocalDate DUMMY_DATE = PDTFactory.createLocalDate (2018, Month.OCTOBER, 24);
  private static final LocalTime DUMMY_TIME = PDTFactory.createLocalTime (12, 10, 34);

  private final HCEdit m_aEdit;
  private final Locale m_aDisplayLocale;
  private LocalDateTime m_aInitialDate;
  private ETriState m_eShowCalendarWeeks = ETriState.FALSE;
  private ETriState m_eShowToday = ETriState.TRUE;
  private ETriState m_eShowClear = ETriState.TRUE;
  private ETriState m_eShowClose = ETriState.TRUE;
  private EBootstrap4DateTimePickerMode m_eMode = EBootstrap4DateTimePickerMode.DEFAULT;
  private String m_sFormat;
  private EBootstrap4DateTimePickerViewModeType m_eViewMode;
  private ETriState m_eSideBySide = ETriState.UNDEFINED;
  private LocalDateTime m_aMinDate;
  private LocalDateTime m_aMaxDate;
  private ETriState m_eUseCurrent = ETriState.FALSE;
  private IHCNode m_aPrependIcon = DEFAULT_PREPEND_ICON;
  private final ICommonsOrderedMap <String, String> m_aIcons = new CommonsLinkedHashMap <> ();

  @Nonnull
  public static String getAsModeSpecificISOString (@Nonnull final EBootstrap4DateTimePickerMode eMode, @Nonnull final LocalDateTime aDT)
  {
    // Always format with ISO mode
    switch (eMode)
    {
      case TIME:
        return DateTimeFormatter.ISO_TIME.format (aDT.toLocalTime ());
      case DATE:
        return DateTimeFormatter.ISO_DATE.format (aDT.toLocalDate ());
      case DATE_TIME:
        return DateTimeFormatter.ISO_DATE_TIME.format (aDT);
      default:
        throw new IllegalStateException ("Unsupported mode " + eMode);
    }
  }

  @Nullable
  public static String getAsModeSpecificUIString (@Nonnull final EBootstrap4DateTimePickerMode eMode,
                                                  @Nullable final LocalDateTime aDT,
                                                  @Nonnull final Locale aDisplayLocale)
  {
    if (aDT == null)
      return null;
    switch (eMode)
    {
      case TIME:
        return PDTToString.getAsString (aDT.toLocalTime (), aDisplayLocale);
      case DATE:
        return PDTToString.getAsString (aDT.toLocalDate (), aDisplayLocale);
      case DATE_TIME:
        return PDTToString.getAsString (aDT, aDisplayLocale);
      default:
        throw new IllegalStateException ("Unsupported mode " + eMode);
    }
  }

  /**
   * Constructor.
   *
   * @param sName
   *        Field name. May not be <code>null</code>.
   * @param aInitialValue
   *        Field initial value. May be <code>null</code>.
   * @param aDisplayLocale
   *        The locale to use. May not be <code>null</code>.
   * @param eMode
   *        Mode to use. May not be <code>null</code>.
   */
  protected BootstrapDateTimePicker (@Nonnull final String sName,
                                     @Nullable final LocalDateTime aInitialValue,
                                     @Nonnull final Locale aDisplayLocale,
                                     @Nonnull final EBootstrap4DateTimePickerMode eMode)
  {
    ValueEnforcer.notNull (aDisplayLocale, "DisplayLocale");
    ValueEnforcer.notNull (eMode, "Mode");

    m_aDisplayLocale = aDisplayLocale;
    m_aInitialDate = aInitialValue;

    // Customize UI
    ensureID ();
    customAttrs ().setDataAttr ("target-input", "nearest");

    m_aEdit = new HCEdit (new RequestField (sName, getAsModeSpecificUIString (eMode, aInitialValue, aDisplayLocale)));
    m_aEdit.setPlaceholder ("");
    m_aEdit.addClass (CSS_CLASS_DATETIMEPICKER_INPUT);
    m_aEdit.customAttrs ().setDataAttr ("toggle", "datetimepicker");
    m_aEdit.customAttrs ().setDataAttr ("target", "#" + getID ());
    BootstrapFormHelper.markAsFormControl (m_aEdit);

    addChild (m_aEdit);

    setMode (eMode);

    // Change default icons
    m_aIcons.put ("clear", "fa fa-eraser");
    m_aIcons.put ("close", "fa fa-close");
  }

  /**
   * @return The contained edit. You may modify the styles.
   */
  @Nonnull
  public final HCEdit getEdit ()
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
  public final EBootstrap4DateTimePickerMode getMode ()
  {
    return m_eMode;
  }

  /**
   * Set the overall mode. By default DATE is selected. This implies, that the
   * default format for the locale (as specified in the constructor is used). If
   * you don't like the default, manually set the format but this should not be
   * necessary.
   *
   * @param eMode
   *        Mode to use. May not be <code>null</code>.
   * @return this for chaining
   * @see #setFormat(String)
   */
  @Nonnull
  public final BootstrapDateTimePicker setMode (@Nonnull final EBootstrap4DateTimePickerMode eMode)
  {
    ValueEnforcer.notNull (eMode, "Mode");
    m_eMode = eMode;
    return this;
  }

  @Nullable
  public final String getFormat ()
  {
    return m_sFormat;
  }

  /**
   * Set the format string to be used. This is only necessary, if the default
   * one from {@link #setMode(EBootstrap4DateTimePickerMode)} is not applicable.
   *
   * @param sFormat
   *        Format string to be used. May be <code>null</code>.
   * @return this for chaining
   */
  @Nonnull
  public final BootstrapDateTimePicker setFormat (@Nullable final String sFormat)
  {
    m_sFormat = sFormat;
    return this;
  }

  @Nonnull
  public final ETriState getShowCalendarWeeks ()
  {
    return m_eShowCalendarWeeks;
  }

  @Nonnull
  public final BootstrapDateTimePicker setShowCalendarWeeks (final boolean bShowCalendarWeeks)
  {
    return setShowCalendarWeeks (ETriState.valueOf (bShowCalendarWeeks));
  }

  @Nonnull
  public final BootstrapDateTimePicker setShowCalendarWeeks (@Nonnull final ETriState eShowCalendarWeeks)
  {
    ValueEnforcer.notNull (eShowCalendarWeeks, "ShowCalendarWeeks");
    m_eShowCalendarWeeks = eShowCalendarWeeks;
    return this;
  }

  @Nonnull
  public final ETriState getShowToday ()
  {
    return m_eShowToday;
  }

  @Nonnull
  public final BootstrapDateTimePicker setShowToday (final boolean bShowToday)
  {
    return setShowToday (ETriState.valueOf (bShowToday));
  }

  @Nonnull
  public final BootstrapDateTimePicker setShowToday (@Nonnull final ETriState eShowToday)
  {
    ValueEnforcer.notNull (eShowToday, "ShowToday");
    m_eShowToday = eShowToday;
    return this;
  }

  @Nonnull
  public final ETriState getShowClear ()
  {
    return m_eShowClear;
  }

  @Nonnull
  public final BootstrapDateTimePicker setShowClear (final boolean bShowClear)
  {
    return setShowClear (ETriState.valueOf (bShowClear));
  }

  @Nonnull
  public final BootstrapDateTimePicker setShowClear (@Nonnull final ETriState eShowClear)
  {
    ValueEnforcer.notNull (eShowClear, "ShowClear");
    m_eShowClear = eShowClear;
    return this;
  }

  @Nonnull
  public final ETriState getShowClose ()
  {
    return m_eShowClose;
  }

  @Nonnull
  public final BootstrapDateTimePicker setShowClose (final boolean bShowClose)
  {
    return setShowClose (ETriState.valueOf (bShowClose));
  }

  @Nonnull
  public final BootstrapDateTimePicker setShowClose (@Nonnull final ETriState eShowClose)
  {
    ValueEnforcer.notNull (eShowClose, "ShowClose");
    m_eShowClose = eShowClose;
    return this;
  }

  @Nullable
  public final EBootstrap4DateTimePickerViewModeType getViewMode ()
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
  public final ETriState getSideBySide ()
  {
    return m_eSideBySide;
  }

  @Nonnull
  public final BootstrapDateTimePicker setSideBySide (final boolean bSideBySide)
  {
    m_eSideBySide = ETriState.valueOf (bSideBySide);
    return this;
  }

  @Nullable
  public final LocalDateTime getInitialDate ()
  {
    return m_aInitialDate;
  }

  @Nonnull
  public final BootstrapDateTimePicker setInitialDate (@Nullable final LocalTime aInitialTime)
  {
    ValueEnforcer.isTrue (m_eMode == EBootstrap4DateTimePickerMode.TIME, "Current action mode is not TIME");
    if (aInitialTime == null)
      m_aInitialDate = null;
    else
      m_aInitialDate = DUMMY_DATE.atTime (aInitialTime);
    return this;
  }

  @Nonnull
  public final BootstrapDateTimePicker setInitialDate (@Nullable final LocalDate aInitialDate)
  {
    ValueEnforcer.isTrue (m_eMode == EBootstrap4DateTimePickerMode.DATE, "Current action mode is not DATE");
    if (aInitialDate == null)
      m_aInitialDate = null;
    else
      m_aInitialDate = aInitialDate.atTime (DUMMY_TIME);
    return this;
  }

  @Nonnull
  public final BootstrapDateTimePicker setInitialDate (@Nullable final LocalDateTime aInitialDateTime)
  {
    ValueEnforcer.isTrue (m_eMode == EBootstrap4DateTimePickerMode.DATE_TIME, "Current action mode is not DATE_TIME");
    m_aInitialDate = aInitialDateTime;
    return this;
  }

  @Nullable
  public final LocalDateTime getMinDate ()
  {
    return m_aMinDate;
  }

  @Nonnull
  public final BootstrapDateTimePicker setMinDate (@Nullable final LocalTime aMinTime)
  {
    ValueEnforcer.isTrue (m_eMode == EBootstrap4DateTimePickerMode.TIME, "Current action mode is not TIME");
    if (aMinTime == null)
      m_aMinDate = null;
    else
      m_aMinDate = DUMMY_DATE.atTime (aMinTime);
    return this;
  }

  @Nonnull
  public final BootstrapDateTimePicker setMinDate (@Nullable final LocalDate aMinDate)
  {
    ValueEnforcer.isTrue (m_eMode == EBootstrap4DateTimePickerMode.DATE, "Current action mode is not DATE");
    if (aMinDate == null)
      m_aMinDate = null;
    else
      m_aMinDate = aMinDate.atTime (DUMMY_TIME);
    return this;
  }

  @Nonnull
  public final BootstrapDateTimePicker setMinDate (@Nullable final LocalDateTime aMinDateTime)
  {
    ValueEnforcer.isTrue (m_eMode == EBootstrap4DateTimePickerMode.DATE_TIME, "Current action mode is not DATE_TIME");
    m_aMinDate = aMinDateTime;
    return this;
  }

  @Nullable
  public final LocalDateTime getMaxDate ()
  {
    return m_aMaxDate;
  }

  @Nonnull
  public final BootstrapDateTimePicker setMaxDate (@Nullable final LocalTime aMaxTime)
  {
    ValueEnforcer.isTrue (m_eMode == EBootstrap4DateTimePickerMode.TIME, "Current action mode is not TIME");
    if (aMaxTime == null)
      m_aMaxDate = null;
    else
      m_aMaxDate = DUMMY_DATE.atTime (aMaxTime);
    return this;
  }

  @Nonnull
  public final BootstrapDateTimePicker setMaxDate (@Nullable final LocalDate aMaxDate)
  {
    ValueEnforcer.isTrue (m_eMode == EBootstrap4DateTimePickerMode.DATE, "Current action mode is not DATE");
    if (aMaxDate == null)
      m_aMaxDate = null;
    else
      m_aMaxDate = aMaxDate.atTime (DUMMY_TIME);
    return this;
  }

  @Nonnull
  public final BootstrapDateTimePicker setMaxDate (@Nullable final LocalDateTime aMaxDateTime)
  {
    ValueEnforcer.isTrue (m_eMode == EBootstrap4DateTimePickerMode.DATE_TIME, "Current action mode is not DATE_TIME");
    m_aMaxDate = aMaxDateTime;
    return this;
  }

  /**
   * On show, will set the picker to the current date/time.?
   *
   * @return Never <code>null</code>
   */
  @Nonnull
  public final ETriState getUseCurrent ()
  {
    return m_eUseCurrent;
  }

  @Nonnull
  public final BootstrapDateTimePicker setUseCurrent (final boolean bUseCurrent)
  {
    return setUseCurrent (ETriState.valueOf (bUseCurrent));
  }

  @Nonnull
  public final BootstrapDateTimePicker setUseCurrent (@Nonnull final ETriState eUseCurrent)
  {
    ValueEnforcer.notNull (eUseCurrent, "UseCurrent");
    m_eUseCurrent = eUseCurrent;
    return this;
  }

  /**
   * @return The icon that is by default prepended to each date time picker
   *         input group. By default it is {@link #DEFAULT_PREPEND_ICON}. May
   *         also be <code>null</code>.
   * @since 8.3.1
   */
  @Nullable
  public final IHCNode getPrependIcon ()
  {
    return m_aPrependIcon;
  }

  /**
   * Set the default icon that is prepended to each date time picker input
   * group. Call this method with <code>null</code> to avoid the default
   * calendar icon to be drawn.
   *
   * @param aPrependIcon
   *        The prepend icon to be used. May be <code>null</code>.
   * @return this for chaining
   * @since 8.3.1
   */
  @Nonnull
  public final BootstrapDateTimePicker setPrependIcon (@Nullable final IHCNode aPrependIcon)
  {
    m_aPrependIcon = aPrependIcon;
    return this;
  }

  /**
   * @return The mutable icon map for the calendar. The key depends on the JS
   *         version used. Currently this can be
   *         <code>time, date, up, down, previous, next, today, clear or close</code>.
   *         The value is the String that contains the CSS classes to be applied
   *         (e.g. <code>fa fa-calendar</code>).
   */
  @Nonnull
  @ReturnsMutableObject
  public final ICommonsOrderedMap <String, String> icons ()
  {
    return m_aIcons;
  }

  @Nonnull
  public static JSInvocation invoke (@Nonnull final JQueryInvocation aJQueryInvocation)
  {
    return aJQueryInvocation.invoke ("datetimepicker");
  }

  @Nonnull
  public final JSInvocation invoke ()
  {
    return invoke (JQuery.idRef (this));
  }

  @Nonnull
  public static JSInvocation invoke (@Nonnull final JQueryInvocation aJQueryInvocation, @Nonnull final JSAssocArray aOptions)
  {
    return invoke (aJQueryInvocation).arg (aOptions);
  }

  @Nonnull
  public final JSInvocation invoke (@Nonnull final JSAssocArray aOptions)
  {
    return invoke ().arg (aOptions);
  }

  private void _add (@Nonnull final JSAssocArray ret, @Nonnull final String sKey, @Nonnull final EBootstrap4DateTimePickerTexts eText)
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

    if (false)
      aOptions.add ("debug", true);

    if (StringHelper.hasText (m_aDisplayLocale.getLanguage ()))
      aOptions.add ("locale", m_aDisplayLocale.getLanguage ());

    if (m_eViewMode != null)
      aOptions.add ("viewMode", m_eViewMode.getJSValueString ());

    if (m_eShowCalendarWeeks.isDefined ())
      aOptions.add ("calendarWeeks", m_eShowCalendarWeeks.getAsBooleanValue ());

    if (m_eShowToday.isDefined () || m_eShowClose.isDefined () || m_eShowClear.isDefined ())
    {
      final IJsonObject aButtons = new JsonObject ();
      if (m_eShowToday.isDefined ())
        aButtons.add ("showToday", m_eShowToday.getAsBooleanValue ());
      if (m_eShowClear.isDefined ())
        aButtons.add ("showClear", m_eShowClear.getAsBooleanValue ());
      if (m_eShowClose.isDefined ())
        aButtons.add ("showClose", m_eShowClose.getAsBooleanValue ());
      aOptions.add ("buttons", aButtons);
    }

    final JSAssocArray aTooltips = getJSTooltipTexts ();
    if (aTooltips.isNotEmpty ())
      aOptions.add ("tooltips", aTooltips);

    if (m_eSideBySide.isDefined ())
      aOptions.add ("sideBySide", m_eSideBySide.getAsBooleanValue ());

    // Explicit format present?
    aOptions.add ("format", StringHelper.hasText (m_sFormat) ? m_sFormat : m_eMode.getJSFormat (m_aDisplayLocale));

    // Set before min, max and initial!
    if (m_eUseCurrent.isDefined ())
      aOptions.add ("useCurrent", m_eUseCurrent.getAsBooleanValue ());

    // min and max
    if (m_aMinDate != null)
      aOptions.add ("minDate", getAsModeSpecificISOString (m_eMode, m_aMinDate));
    if (m_aMaxDate != null)
      aOptions.add ("maxDate", getAsModeSpecificISOString (m_eMode, m_aMaxDate));

    // Default date present?
    // Put in options after minDate and maxDate
    if (m_aInitialDate != null)
      aOptions.add ("date", getAsModeSpecificISOString (m_eMode, m_aInitialDate));

    // Add icons
    if (m_aIcons.isNotEmpty ())
      aOptions.add ("icons", new JsonObject ().addAll (m_aIcons));

    return aOptions;
  }

  @Override
  @Nonnull
  @OverrideOnDemand
  public HCDiv createGroupPrepend ()
  {
    // Make the whole prepend thing a toggle
    final HCDiv ret = super.createGroupPrepend ();
    ret.customAttrs ().setDataAttr ("toggle", "datetimepicker");
    ret.customAttrs ().setDataAttr ("target", "#" + getID ());
    return ret;
  }

  @Override
  protected void onFinalizeNodeState (@Nonnull final IHCConversionSettingsToNode aConversionSettings,
                                      @Nonnull final IHCHasChildrenMutable <?, ? super IHCNode> aTargetNode)
  {
    if (m_aPrependIcon != null)
      getOrCreateGroupPrepend ().addChildAt (0, getWrapped (m_aPrependIcon));

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

  @Nonnull
  public static BootstrapDateTimePicker create (@Nonnull final String sName,
                                                @Nullable final LocalDate aInitialValue,
                                                @Nonnull final Locale aDisplayLocale)
  {
    return new BootstrapDateTimePicker (sName,
                                        aInitialValue == null ? null : aInitialValue.atTime (DUMMY_TIME),
                                        aDisplayLocale,
                                        EBootstrap4DateTimePickerMode.DATE);
  }

  @Nonnull
  public static BootstrapDateTimePicker create (@Nonnull final String sName,
                                                @Nullable final LocalTime aInitialValue,
                                                @Nonnull final Locale aDisplayLocale)
  {
    return new BootstrapDateTimePicker (sName,
                                        aInitialValue == null ? null : DUMMY_DATE.atTime (aInitialValue),
                                        aDisplayLocale,
                                        EBootstrap4DateTimePickerMode.TIME);
  }

  @Nonnull
  public static BootstrapDateTimePicker create (@Nonnull final String sName,
                                                @Nullable final LocalDateTime aInitialValue,
                                                @Nonnull final Locale aDisplayLocale)
  {
    return new BootstrapDateTimePicker (sName, aInitialValue, aDisplayLocale, EBootstrap4DateTimePickerMode.DATE_TIME);
  }

  @Nonnull
  public static BootstrapDateTimePicker create (@Nonnull final String sName,
                                                @Nonnull final Locale aDisplayLocale,
                                                @Nonnull final EBootstrap4DateTimePickerMode eMode)
  {
    return new BootstrapDateTimePicker (sName, null, aDisplayLocale, eMode);
  }
}
