/*
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
// full day names, starting and ending with Sunday!
Calendar._DN = new Array
(%CALENDAR_DAY_LONG_SUNDAY%,
 %CALENDAR_DAY_LONG_MONDAY%,
 %CALENDAR_DAY_LONG_TUESDAY%,
 %CALENDAR_DAY_LONG_WEDNESDAY%,
 %CALENDAR_DAY_LONG_THURSDAY%,
 %CALENDAR_DAY_LONG_FRIDAY%,
 %CALENDAR_DAY_LONG_SATURDAY%,
 %CALENDAR_DAY_LONG_SUNDAY%);

//Please note that the following array of short day names (and the same goes
//for short month names, _SMN) isn't absolutely necessary.  We give it here
//for exemplification on how one can customize the short day names, but if
//they are simply the first N letters of the full name you can simply say:
//
//Calendar._SDN_len = N; // short day name length
//Calendar._SMN_len = N; // short month name length
//
//If N = 3 then this is not needed either since we assume a value of 3 if not
//present, to be compatible with translation files that were written before
//this feature.

// short day names, starting and ending with Sunday
Calendar._SDN = new Array
(%CALENDAR_DAY_SHORT_SUNDAY%,
 %CALENDAR_DAY_SHORT_MONDAY%,
 %CALENDAR_DAY_SHORT_TUESDAY%,
 %CALENDAR_DAY_SHORT_WEDNESDAY%,
 %CALENDAR_DAY_SHORT_THURSDAY%,
 %CALENDAR_DAY_SHORT_FRIDAY%,
 %CALENDAR_DAY_SHORT_SATURDAY%,
 %CALENDAR_DAY_SHORT_SUNDAY%);

// First day of the week. "0" means display Sunday first, "1" means display
// Monday first, etc.
Calendar._FD = %CALENDAR_FIRST_DAY_OF_WEEK%;

// full month names, from January to December
Calendar._MN = new Array
(%CALENDAR_MONTH_LONG_JANUARY%,
 %CALENDAR_MONTH_LONG_FEBRUARY%,
 %CALENDAR_MONTH_LONG_MARCH%,
 %CALENDAR_MONTH_LONG_APRIL%,
 %CALENDAR_MONTH_LONG_MAY%,
 %CALENDAR_MONTH_LONG_JUNE%,
 %CALENDAR_MONTH_LONG_JULY%,
 %CALENDAR_MONTH_LONG_AUGUST%,
 %CALENDAR_MONTH_LONG_SEPTEMBER%,
 %CALENDAR_MONTH_LONG_OCTOBER%,
 %CALENDAR_MONTH_LONG_NOVEMBER%,
 %CALENDAR_MONTH_LONG_DECEMBER%);

// short month names, from January to December
Calendar._SMN = new Array
(%CALENDAR_MONTH_SHORT_JANUARY%,
 %CALENDAR_MONTH_SHORT_FEBRUARY%,
 %CALENDAR_MONTH_SHORT_MARCH%,
 %CALENDAR_MONTH_SHORT_APRIL%,
 %CALENDAR_MONTH_SHORT_MAY%,
 %CALENDAR_MONTH_SHORT_JUNE%,
 %CALENDAR_MONTH_SHORT_JULY%,
 %CALENDAR_MONTH_SHORT_AUGUST%,
 %CALENDAR_MONTH_SHORT_SEPTEMBER%,
 %CALENDAR_MONTH_SHORT_OCTOBER%,
 %CALENDAR_MONTH_SHORT_NOVEMBER%,
 %CALENDAR_MONTH_SHORT_DECEMBER%);

// tooltips
Calendar._TT = {};
Calendar._TT.INFO = %CALENDAR_INFO%;

Calendar._TT.ABOUT =
"DHTML Date/Time Selector\n" +
"(c) dynarch.com 2002-2005 / Author: Mihai Bazon\n" + // don't translate this ;-)
"For latest version visit: http://www.dynarch.com/projects/calendar/\n" +
"Distributed under GNU LGPL.  See http://gnu.org/licenses/lgpl.html for details." +
"\n\n" + %CALENDAR_ABOUT%;
Calendar._TT.ABOUT_TIME = "\n\n" + %CALENDAR_ABOUT_TIME%;

Calendar._TT.PREV_YEAR = %CALENDAR_PREV_YEAR%;
Calendar._TT.PREV_MONTH = %CALENDAR_PREV_MONTH%;
Calendar._TT.GO_TODAY = %CALENDAR_GO_TODAY%;
Calendar._TT.NEXT_MONTH = %CALENDAR_NEXT_MONTH%;
Calendar._TT.NEXT_YEAR = %CALENDAR_NEXT_YEAR%;
Calendar._TT.SEL_DATE = %CALENDAR_SEL_DATE%;
Calendar._TT.DRAG_TO_MOVE = %CALENDAR_DRAG_TO_MOVE%;
Calendar._TT.PART_TODAY = %CALENDAR_PART_TODAY%;

// the following is to inform that "%s" is to be the first day of week
// %s will be replaced with the day name.
Calendar._TT.DAY_FIRST = %CALENDAR_DAY_FIRST%;

// This may be locale-dependent.  It specifies the week-end days, as an array
// of comma-separated numbers.  The numbers are from 0 to 6: 0 means Sunday, 1
// means Monday, etc.
Calendar._TT.WEEKEND = %CALENDAR_WEEKEND%;

Calendar._TT.CLOSE = %CALENDAR_CLOSE%;
Calendar._TT.TODAY = %CALENDAR_TODAY%;
Calendar._TT.TIME_PART = %CALENDAR_TIME_PART%;

// date formats
Calendar._TT.DEF_DATE_FORMAT = %CALENDAR_DEF_DATE_FORMAT%;
Calendar._TT.TT_DATE_FORMAT = %CALENDAR_TT_DATE_FORMAT%;

// added
Calendar._TT.DEF_TIME_FORMAT = %CALENDAR_DEF_TIME_FORMAT%;

Calendar._TT.WK = %CALENDAR_WK%;
Calendar._TT.TIME = %CALENDAR_TIME%;
