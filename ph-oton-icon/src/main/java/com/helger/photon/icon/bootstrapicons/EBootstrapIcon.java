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
package com.helger.photon.icon.bootstrapicons;

import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.Nullable;

import com.helger.collection.commons.CommonsArrayList;
import com.helger.collection.commons.ICommonsList;
import com.helger.html.css.DefaultCSSClassProvider;
import com.helger.html.css.ICSSClassProvider;
import com.helger.html.hc.html.IHCElement;
import com.helger.html.hc.html.textlevel.HCI;
import com.helger.html.resource.css.ICSSPathProvider;
import com.helger.photon.app.html.PhotonCSS;
import com.helger.photon.icon.EIconCSSPathProvider;
import com.helger.photon.uicore.icon.DefaultIcons;
import com.helger.photon.uicore.icon.EDefaultIcon;
import com.helger.photon.uicore.icon.IIcon;

/**
 * Bootstrap icons v1.11.3
 *
 * @author Philip Helger
 * @since 8.3.1
 */
@Deprecated (forRemoval = true, since = "12.3.0")
public enum EBootstrapIcon implements IIcon
{
  @Deprecated (forRemoval = true, since = "12.3.0")
  _0_CIRCLE(CBootstrapIconCSS.BI_0_CIRCLE),
  @Deprecated (forRemoval = true, since = "12.3.0")
  _0_CIRCLE_FILL(CBootstrapIconCSS.BI_0_CIRCLE_FILL),
  @Deprecated (forRemoval = true, since = "12.3.0")
  _0_SQUARE(CBootstrapIconCSS.BI_0_SQUARE),
  @Deprecated (forRemoval = true, since = "12.3.0")
  _0_SQUARE_FILL(CBootstrapIconCSS.BI_0_SQUARE_FILL),
  @Deprecated (forRemoval = true, since = "12.3.0")
  _1_CIRCLE(CBootstrapIconCSS.BI_1_CIRCLE),
  @Deprecated (forRemoval = true, since = "12.3.0")
  _1_CIRCLE_FILL(CBootstrapIconCSS.BI_1_CIRCLE_FILL),
  @Deprecated (forRemoval = true, since = "12.3.0")
  _1_SQUARE(CBootstrapIconCSS.BI_1_SQUARE),
  @Deprecated (forRemoval = true, since = "12.3.0")
  _1_SQUARE_FILL(CBootstrapIconCSS.BI_1_SQUARE_FILL),
  @Deprecated (forRemoval = true, since = "12.3.0")
  _123(CBootstrapIconCSS.BI_123),
  @Deprecated (forRemoval = true, since = "12.3.0")
  _2_CIRCLE(CBootstrapIconCSS.BI_2_CIRCLE),
  @Deprecated (forRemoval = true, since = "12.3.0")
  _2_CIRCLE_FILL(CBootstrapIconCSS.BI_2_CIRCLE_FILL),
  @Deprecated (forRemoval = true, since = "12.3.0")
  _2_SQUARE(CBootstrapIconCSS.BI_2_SQUARE),
  @Deprecated (forRemoval = true, since = "12.3.0")
  _2_SQUARE_FILL(CBootstrapIconCSS.BI_2_SQUARE_FILL),
  @Deprecated (forRemoval = true, since = "12.3.0")
  _3_CIRCLE(CBootstrapIconCSS.BI_3_CIRCLE),
  @Deprecated (forRemoval = true, since = "12.3.0")
  _3_CIRCLE_FILL(CBootstrapIconCSS.BI_3_CIRCLE_FILL),
  @Deprecated (forRemoval = true, since = "12.3.0")
  _3_SQUARE(CBootstrapIconCSS.BI_3_SQUARE),
  @Deprecated (forRemoval = true, since = "12.3.0")
  _3_SQUARE_FILL(CBootstrapIconCSS.BI_3_SQUARE_FILL),
  @Deprecated (forRemoval = true, since = "12.3.0")
  _4_CIRCLE(CBootstrapIconCSS.BI_4_CIRCLE),
  @Deprecated (forRemoval = true, since = "12.3.0")
  _4_CIRCLE_FILL(CBootstrapIconCSS.BI_4_CIRCLE_FILL),
  @Deprecated (forRemoval = true, since = "12.3.0")
  _4_SQUARE(CBootstrapIconCSS.BI_4_SQUARE),
  @Deprecated (forRemoval = true, since = "12.3.0")
  _4_SQUARE_FILL(CBootstrapIconCSS.BI_4_SQUARE_FILL),
  @Deprecated (forRemoval = true, since = "12.3.0")
  _5_CIRCLE(CBootstrapIconCSS.BI_5_CIRCLE),
  @Deprecated (forRemoval = true, since = "12.3.0")
  _5_CIRCLE_FILL(CBootstrapIconCSS.BI_5_CIRCLE_FILL),
  @Deprecated (forRemoval = true, since = "12.3.0")
  _5_SQUARE(CBootstrapIconCSS.BI_5_SQUARE),
  @Deprecated (forRemoval = true, since = "12.3.0")
  _5_SQUARE_FILL(CBootstrapIconCSS.BI_5_SQUARE_FILL),
  @Deprecated (forRemoval = true, since = "12.3.0")
  _6_CIRCLE(CBootstrapIconCSS.BI_6_CIRCLE),
  @Deprecated (forRemoval = true, since = "12.3.0")
  _6_CIRCLE_FILL(CBootstrapIconCSS.BI_6_CIRCLE_FILL),
  @Deprecated (forRemoval = true, since = "12.3.0")
  _6_SQUARE(CBootstrapIconCSS.BI_6_SQUARE),
  @Deprecated (forRemoval = true, since = "12.3.0")
  _6_SQUARE_FILL(CBootstrapIconCSS.BI_6_SQUARE_FILL),
  @Deprecated (forRemoval = true, since = "12.3.0")
  _7_CIRCLE(CBootstrapIconCSS.BI_7_CIRCLE),
  @Deprecated (forRemoval = true, since = "12.3.0")
  _7_CIRCLE_FILL(CBootstrapIconCSS.BI_7_CIRCLE_FILL),
  @Deprecated (forRemoval = true, since = "12.3.0")
  _7_SQUARE(CBootstrapIconCSS.BI_7_SQUARE),
  @Deprecated (forRemoval = true, since = "12.3.0")
  _7_SQUARE_FILL(CBootstrapIconCSS.BI_7_SQUARE_FILL),
  @Deprecated (forRemoval = true, since = "12.3.0")
  _8_CIRCLE(CBootstrapIconCSS.BI_8_CIRCLE),
  @Deprecated (forRemoval = true, since = "12.3.0")
  _8_CIRCLE_FILL(CBootstrapIconCSS.BI_8_CIRCLE_FILL),
  @Deprecated (forRemoval = true, since = "12.3.0")
  _8_SQUARE(CBootstrapIconCSS.BI_8_SQUARE),
  @Deprecated (forRemoval = true, since = "12.3.0")
  _8_SQUARE_FILL(CBootstrapIconCSS.BI_8_SQUARE_FILL),
  @Deprecated (forRemoval = true, since = "12.3.0")
  _9_CIRCLE(CBootstrapIconCSS.BI_9_CIRCLE),
  @Deprecated (forRemoval = true, since = "12.3.0")
  _9_CIRCLE_FILL(CBootstrapIconCSS.BI_9_CIRCLE_FILL),
  @Deprecated (forRemoval = true, since = "12.3.0")
  _9_SQUARE(CBootstrapIconCSS.BI_9_SQUARE),
  @Deprecated (forRemoval = true, since = "12.3.0")
  _9_SQUARE_FILL(CBootstrapIconCSS.BI_9_SQUARE_FILL),
  @Deprecated (forRemoval = true, since = "12.3.0")
  ACTIVITY(CBootstrapIconCSS.BI_ACTIVITY),
  @Deprecated (forRemoval = true, since = "12.3.0")
  AIRPLANE(CBootstrapIconCSS.BI_AIRPLANE),
  @Deprecated (forRemoval = true, since = "12.3.0")
  AIRPLANE_ENGINES(CBootstrapIconCSS.BI_AIRPLANE_ENGINES),
  @Deprecated (forRemoval = true, since = "12.3.0")
  AIRPLANE_ENGINES_FILL(CBootstrapIconCSS.BI_AIRPLANE_ENGINES_FILL),
  @Deprecated (forRemoval = true, since = "12.3.0")
  AIRPLANE_FILL(CBootstrapIconCSS.BI_AIRPLANE_FILL),
  @Deprecated (forRemoval = true, since = "12.3.0")
  ALARM(CBootstrapIconCSS.BI_ALARM),
  @Deprecated (forRemoval = true, since = "12.3.0")
  ALARM_FILL(CBootstrapIconCSS.BI_ALARM_FILL),
  @Deprecated (forRemoval = true, since = "12.3.0")
  ALEXA(CBootstrapIconCSS.BI_ALEXA),
  @Deprecated (forRemoval = true, since = "12.3.0")
  ALIGN_BOTTOM(CBootstrapIconCSS.BI_ALIGN_BOTTOM),
  @Deprecated (forRemoval = true, since = "12.3.0")
  ALIGN_CENTER(CBootstrapIconCSS.BI_ALIGN_CENTER),
  @Deprecated (forRemoval = true, since = "12.3.0")
  ALIGN_END(CBootstrapIconCSS.BI_ALIGN_END),
  @Deprecated (forRemoval = true, since = "12.3.0")
  ALIGN_MIDDLE(CBootstrapIconCSS.BI_ALIGN_MIDDLE),
  @Deprecated (forRemoval = true, since = "12.3.0")
  ALIGN_START(CBootstrapIconCSS.BI_ALIGN_START),
  @Deprecated (forRemoval = true, since = "12.3.0")
  ALIGN_TOP(CBootstrapIconCSS.BI_ALIGN_TOP),
  @Deprecated (forRemoval = true, since = "12.3.0")
  ALIPAY(CBootstrapIconCSS.BI_ALIPAY),
  @Deprecated (forRemoval = true, since = "12.3.0")
  ALPHABET(CBootstrapIconCSS.BI_ALPHABET),
  @Deprecated (forRemoval = true, since = "12.3.0")
  ALPHABET_UPPERCASE(CBootstrapIconCSS.BI_ALPHABET_UPPERCASE),
  @Deprecated (forRemoval = true, since = "12.3.0")
  ALT(CBootstrapIconCSS.BI_ALT),
  @Deprecated (forRemoval = true, since = "12.3.0")
  AMAZON(CBootstrapIconCSS.BI_AMAZON),
  @Deprecated (forRemoval = true, since = "12.3.0")
  AMD(CBootstrapIconCSS.BI_AMD),
  @Deprecated (forRemoval = true, since = "12.3.0")
  ANDROID(CBootstrapIconCSS.BI_ANDROID),
  @Deprecated (forRemoval = true, since = "12.3.0")
  ANDROID2(CBootstrapIconCSS.BI_ANDROID2),
  @Deprecated (forRemoval = true, since = "12.3.0")
  APP(CBootstrapIconCSS.BI_APP),
  @Deprecated (forRemoval = true, since = "12.3.0")
  APP_INDICATOR(CBootstrapIconCSS.BI_APP_INDICATOR),
  @Deprecated (forRemoval = true, since = "12.3.0")
  APPLE(CBootstrapIconCSS.BI_APPLE),
  @Deprecated (forRemoval = true, since = "12.3.0")
  ARCHIVE(CBootstrapIconCSS.BI_ARCHIVE),
  @Deprecated (forRemoval = true, since = "12.3.0")
  ARCHIVE_FILL(CBootstrapIconCSS.BI_ARCHIVE_FILL),
  @Deprecated (forRemoval = true, since = "12.3.0")
  ARROW_90DEG_DOWN(CBootstrapIconCSS.BI_ARROW_90DEG_DOWN),
  @Deprecated (forRemoval = true, since = "12.3.0")
  ARROW_90DEG_LEFT(CBootstrapIconCSS.BI_ARROW_90DEG_LEFT),
  @Deprecated (forRemoval = true, since = "12.3.0")
  ARROW_90DEG_RIGHT(CBootstrapIconCSS.BI_ARROW_90DEG_RIGHT),
  @Deprecated (forRemoval = true, since = "12.3.0")
  ARROW_90DEG_UP(CBootstrapIconCSS.BI_ARROW_90DEG_UP),
  @Deprecated (forRemoval = true, since = "12.3.0")
  ARROW_BAR_DOWN(CBootstrapIconCSS.BI_ARROW_BAR_DOWN),
  @Deprecated (forRemoval = true, since = "12.3.0")
  ARROW_BAR_LEFT(CBootstrapIconCSS.BI_ARROW_BAR_LEFT),
  @Deprecated (forRemoval = true, since = "12.3.0")
  ARROW_BAR_RIGHT(CBootstrapIconCSS.BI_ARROW_BAR_RIGHT),
  @Deprecated (forRemoval = true, since = "12.3.0")
  ARROW_BAR_UP(CBootstrapIconCSS.BI_ARROW_BAR_UP),
  @Deprecated (forRemoval = true, since = "12.3.0")
  ARROW_CLOCKWISE(CBootstrapIconCSS.BI_ARROW_CLOCKWISE),
  @Deprecated (forRemoval = true, since = "12.3.0")
  ARROW_COUNTERCLOCKWISE(CBootstrapIconCSS.BI_ARROW_COUNTERCLOCKWISE),
  @Deprecated (forRemoval = true, since = "12.3.0")
  ARROW_DOWN(CBootstrapIconCSS.BI_ARROW_DOWN),
  @Deprecated (forRemoval = true, since = "12.3.0")
  ARROW_DOWN_CIRCLE(CBootstrapIconCSS.BI_ARROW_DOWN_CIRCLE),
  @Deprecated (forRemoval = true, since = "12.3.0")
  ARROW_DOWN_CIRCLE_FILL(CBootstrapIconCSS.BI_ARROW_DOWN_CIRCLE_FILL),
  @Deprecated (forRemoval = true, since = "12.3.0")
  ARROW_DOWN_LEFT(CBootstrapIconCSS.BI_ARROW_DOWN_LEFT),
  @Deprecated (forRemoval = true, since = "12.3.0")
  ARROW_DOWN_LEFT_CIRCLE(CBootstrapIconCSS.BI_ARROW_DOWN_LEFT_CIRCLE),
  @Deprecated (forRemoval = true, since = "12.3.0")
  ARROW_DOWN_LEFT_CIRCLE_FILL(CBootstrapIconCSS.BI_ARROW_DOWN_LEFT_CIRCLE_FILL),
  @Deprecated (forRemoval = true, since = "12.3.0")
  ARROW_DOWN_LEFT_SQUARE(CBootstrapIconCSS.BI_ARROW_DOWN_LEFT_SQUARE),
  @Deprecated (forRemoval = true, since = "12.3.0")
  ARROW_DOWN_LEFT_SQUARE_FILL(CBootstrapIconCSS.BI_ARROW_DOWN_LEFT_SQUARE_FILL),
  @Deprecated (forRemoval = true, since = "12.3.0")
  ARROW_DOWN_RIGHT(CBootstrapIconCSS.BI_ARROW_DOWN_RIGHT),
  @Deprecated (forRemoval = true, since = "12.3.0")
  ARROW_DOWN_RIGHT_CIRCLE(CBootstrapIconCSS.BI_ARROW_DOWN_RIGHT_CIRCLE),
  @Deprecated (forRemoval = true, since = "12.3.0")
  ARROW_DOWN_RIGHT_CIRCLE_FILL(CBootstrapIconCSS.BI_ARROW_DOWN_RIGHT_CIRCLE_FILL),
  @Deprecated (forRemoval = true, since = "12.3.0")
  ARROW_DOWN_RIGHT_SQUARE(CBootstrapIconCSS.BI_ARROW_DOWN_RIGHT_SQUARE),
  @Deprecated (forRemoval = true, since = "12.3.0")
  ARROW_DOWN_RIGHT_SQUARE_FILL(CBootstrapIconCSS.BI_ARROW_DOWN_RIGHT_SQUARE_FILL),
  @Deprecated (forRemoval = true, since = "12.3.0")
  ARROW_DOWN_SHORT(CBootstrapIconCSS.BI_ARROW_DOWN_SHORT),
  @Deprecated (forRemoval = true, since = "12.3.0")
  ARROW_DOWN_SQUARE(CBootstrapIconCSS.BI_ARROW_DOWN_SQUARE),
  @Deprecated (forRemoval = true, since = "12.3.0")
  ARROW_DOWN_SQUARE_FILL(CBootstrapIconCSS.BI_ARROW_DOWN_SQUARE_FILL),
  @Deprecated (forRemoval = true, since = "12.3.0")
  ARROW_DOWN_UP(CBootstrapIconCSS.BI_ARROW_DOWN_UP),
  @Deprecated (forRemoval = true, since = "12.3.0")
  ARROW_LEFT(CBootstrapIconCSS.BI_ARROW_LEFT),
  @Deprecated (forRemoval = true, since = "12.3.0")
  ARROW_LEFT_CIRCLE(CBootstrapIconCSS.BI_ARROW_LEFT_CIRCLE),
  @Deprecated (forRemoval = true, since = "12.3.0")
  ARROW_LEFT_CIRCLE_FILL(CBootstrapIconCSS.BI_ARROW_LEFT_CIRCLE_FILL),
  @Deprecated (forRemoval = true, since = "12.3.0")
  ARROW_LEFT_RIGHT(CBootstrapIconCSS.BI_ARROW_LEFT_RIGHT),
  @Deprecated (forRemoval = true, since = "12.3.0")
  ARROW_LEFT_SHORT(CBootstrapIconCSS.BI_ARROW_LEFT_SHORT),
  @Deprecated (forRemoval = true, since = "12.3.0")
  ARROW_LEFT_SQUARE(CBootstrapIconCSS.BI_ARROW_LEFT_SQUARE),
  @Deprecated (forRemoval = true, since = "12.3.0")
  ARROW_LEFT_SQUARE_FILL(CBootstrapIconCSS.BI_ARROW_LEFT_SQUARE_FILL),
  @Deprecated (forRemoval = true, since = "12.3.0")
  ARROW_REPEAT(CBootstrapIconCSS.BI_ARROW_REPEAT),
  @Deprecated (forRemoval = true, since = "12.3.0")
  ARROW_RETURN_LEFT(CBootstrapIconCSS.BI_ARROW_RETURN_LEFT),
  @Deprecated (forRemoval = true, since = "12.3.0")
  ARROW_RETURN_RIGHT(CBootstrapIconCSS.BI_ARROW_RETURN_RIGHT),
  @Deprecated (forRemoval = true, since = "12.3.0")
  ARROW_RIGHT(CBootstrapIconCSS.BI_ARROW_RIGHT),
  @Deprecated (forRemoval = true, since = "12.3.0")
  ARROW_RIGHT_CIRCLE(CBootstrapIconCSS.BI_ARROW_RIGHT_CIRCLE),
  @Deprecated (forRemoval = true, since = "12.3.0")
  ARROW_RIGHT_CIRCLE_FILL(CBootstrapIconCSS.BI_ARROW_RIGHT_CIRCLE_FILL),
  @Deprecated (forRemoval = true, since = "12.3.0")
  ARROW_RIGHT_SHORT(CBootstrapIconCSS.BI_ARROW_RIGHT_SHORT),
  @Deprecated (forRemoval = true, since = "12.3.0")
  ARROW_RIGHT_SQUARE(CBootstrapIconCSS.BI_ARROW_RIGHT_SQUARE),
  @Deprecated (forRemoval = true, since = "12.3.0")
  ARROW_RIGHT_SQUARE_FILL(CBootstrapIconCSS.BI_ARROW_RIGHT_SQUARE_FILL),
  @Deprecated (forRemoval = true, since = "12.3.0")
  ARROW_THROUGH_HEART(CBootstrapIconCSS.BI_ARROW_THROUGH_HEART),
  @Deprecated (forRemoval = true, since = "12.3.0")
  ARROW_THROUGH_HEART_FILL(CBootstrapIconCSS.BI_ARROW_THROUGH_HEART_FILL),
  @Deprecated (forRemoval = true, since = "12.3.0")
  ARROW_UP(CBootstrapIconCSS.BI_ARROW_UP),
  @Deprecated (forRemoval = true, since = "12.3.0")
  ARROW_UP_CIRCLE(CBootstrapIconCSS.BI_ARROW_UP_CIRCLE),
  @Deprecated (forRemoval = true, since = "12.3.0")
  ARROW_UP_CIRCLE_FILL(CBootstrapIconCSS.BI_ARROW_UP_CIRCLE_FILL),
  @Deprecated (forRemoval = true, since = "12.3.0")
  ARROW_UP_LEFT(CBootstrapIconCSS.BI_ARROW_UP_LEFT),
  @Deprecated (forRemoval = true, since = "12.3.0")
  ARROW_UP_LEFT_CIRCLE(CBootstrapIconCSS.BI_ARROW_UP_LEFT_CIRCLE),
  @Deprecated (forRemoval = true, since = "12.3.0")
  ARROW_UP_LEFT_CIRCLE_FILL(CBootstrapIconCSS.BI_ARROW_UP_LEFT_CIRCLE_FILL),
  @Deprecated (forRemoval = true, since = "12.3.0")
  ARROW_UP_LEFT_SQUARE(CBootstrapIconCSS.BI_ARROW_UP_LEFT_SQUARE),
  @Deprecated (forRemoval = true, since = "12.3.0")
  ARROW_UP_LEFT_SQUARE_FILL(CBootstrapIconCSS.BI_ARROW_UP_LEFT_SQUARE_FILL),
  @Deprecated (forRemoval = true, since = "12.3.0")
  ARROW_UP_RIGHT(CBootstrapIconCSS.BI_ARROW_UP_RIGHT),
  @Deprecated (forRemoval = true, since = "12.3.0")
  ARROW_UP_RIGHT_CIRCLE(CBootstrapIconCSS.BI_ARROW_UP_RIGHT_CIRCLE),
  @Deprecated (forRemoval = true, since = "12.3.0")
  ARROW_UP_RIGHT_CIRCLE_FILL(CBootstrapIconCSS.BI_ARROW_UP_RIGHT_CIRCLE_FILL),
  @Deprecated (forRemoval = true, since = "12.3.0")
  ARROW_UP_RIGHT_SQUARE(CBootstrapIconCSS.BI_ARROW_UP_RIGHT_SQUARE),
  @Deprecated (forRemoval = true, since = "12.3.0")
  ARROW_UP_RIGHT_SQUARE_FILL(CBootstrapIconCSS.BI_ARROW_UP_RIGHT_SQUARE_FILL),
  @Deprecated (forRemoval = true, since = "12.3.0")
  ARROW_UP_SHORT(CBootstrapIconCSS.BI_ARROW_UP_SHORT),
  @Deprecated (forRemoval = true, since = "12.3.0")
  ARROW_UP_SQUARE(CBootstrapIconCSS.BI_ARROW_UP_SQUARE),
  @Deprecated (forRemoval = true, since = "12.3.0")
  ARROW_UP_SQUARE_FILL(CBootstrapIconCSS.BI_ARROW_UP_SQUARE_FILL),
  @Deprecated (forRemoval = true, since = "12.3.0")
  ARROWS(CBootstrapIconCSS.BI_ARROWS),
  @Deprecated (forRemoval = true, since = "12.3.0")
  ARROWS_ANGLE_CONTRACT(CBootstrapIconCSS.BI_ARROWS_ANGLE_CONTRACT),
  @Deprecated (forRemoval = true, since = "12.3.0")
  ARROWS_ANGLE_EXPAND(CBootstrapIconCSS.BI_ARROWS_ANGLE_EXPAND),
  @Deprecated (forRemoval = true, since = "12.3.0")
  ARROWS_COLLAPSE(CBootstrapIconCSS.BI_ARROWS_COLLAPSE),
  @Deprecated (forRemoval = true, since = "12.3.0")
  ARROWS_COLLAPSE_VERTICAL(CBootstrapIconCSS.BI_ARROWS_COLLAPSE_VERTICAL),
  @Deprecated (forRemoval = true, since = "12.3.0")
  ARROWS_EXPAND(CBootstrapIconCSS.BI_ARROWS_EXPAND),
  @Deprecated (forRemoval = true, since = "12.3.0")
  ARROWS_EXPAND_VERTICAL(CBootstrapIconCSS.BI_ARROWS_EXPAND_VERTICAL),
  @Deprecated (forRemoval = true, since = "12.3.0")
  ARROWS_FULLSCREEN(CBootstrapIconCSS.BI_ARROWS_FULLSCREEN),
  @Deprecated (forRemoval = true, since = "12.3.0")
  ARROWS_MOVE(CBootstrapIconCSS.BI_ARROWS_MOVE),
  @Deprecated (forRemoval = true, since = "12.3.0")
  ARROWS_VERTICAL(CBootstrapIconCSS.BI_ARROWS_VERTICAL),
  @Deprecated (forRemoval = true, since = "12.3.0")
  ASPECT_RATIO(CBootstrapIconCSS.BI_ASPECT_RATIO),
  @Deprecated (forRemoval = true, since = "12.3.0")
  ASPECT_RATIO_FILL(CBootstrapIconCSS.BI_ASPECT_RATIO_FILL),
  @Deprecated (forRemoval = true, since = "12.3.0")
  ASTERISK(CBootstrapIconCSS.BI_ASTERISK),
  @Deprecated (forRemoval = true, since = "12.3.0")
  AT(CBootstrapIconCSS.BI_AT),
  @Deprecated (forRemoval = true, since = "12.3.0")
  AWARD(CBootstrapIconCSS.BI_AWARD),
  @Deprecated (forRemoval = true, since = "12.3.0")
  AWARD_FILL(CBootstrapIconCSS.BI_AWARD_FILL),
  @Deprecated (forRemoval = true, since = "12.3.0")
  BACK(CBootstrapIconCSS.BI_BACK),
  @Deprecated (forRemoval = true, since = "12.3.0")
  BACKPACK(CBootstrapIconCSS.BI_BACKPACK),
  @Deprecated (forRemoval = true, since = "12.3.0")
  BACKPACK_FILL(CBootstrapIconCSS.BI_BACKPACK_FILL),
  @Deprecated (forRemoval = true, since = "12.3.0")
  BACKPACK2(CBootstrapIconCSS.BI_BACKPACK2),
  @Deprecated (forRemoval = true, since = "12.3.0")
  BACKPACK2_FILL(CBootstrapIconCSS.BI_BACKPACK2_FILL),
  @Deprecated (forRemoval = true, since = "12.3.0")
  BACKPACK3(CBootstrapIconCSS.BI_BACKPACK3),
  @Deprecated (forRemoval = true, since = "12.3.0")
  BACKPACK3_FILL(CBootstrapIconCSS.BI_BACKPACK3_FILL),
  @Deprecated (forRemoval = true, since = "12.3.0")
  BACKPACK4(CBootstrapIconCSS.BI_BACKPACK4),
  @Deprecated (forRemoval = true, since = "12.3.0")
  BACKPACK4_FILL(CBootstrapIconCSS.BI_BACKPACK4_FILL),
  @Deprecated (forRemoval = true, since = "12.3.0")
  BACKSPACE(CBootstrapIconCSS.BI_BACKSPACE),
  @Deprecated (forRemoval = true, since = "12.3.0")
  BACKSPACE_FILL(CBootstrapIconCSS.BI_BACKSPACE_FILL),
  @Deprecated (forRemoval = true, since = "12.3.0")
  BACKSPACE_REVERSE(CBootstrapIconCSS.BI_BACKSPACE_REVERSE),
  @Deprecated (forRemoval = true, since = "12.3.0")
  BACKSPACE_REVERSE_FILL(CBootstrapIconCSS.BI_BACKSPACE_REVERSE_FILL),
  @Deprecated (forRemoval = true, since = "12.3.0")
  BADGE_3D(CBootstrapIconCSS.BI_BADGE_3D),
  @Deprecated (forRemoval = true, since = "12.3.0")
  BADGE_3D_FILL(CBootstrapIconCSS.BI_BADGE_3D_FILL),
  @Deprecated (forRemoval = true, since = "12.3.0")
  BADGE_4K(CBootstrapIconCSS.BI_BADGE_4K),
  @Deprecated (forRemoval = true, since = "12.3.0")
  BADGE_4K_FILL(CBootstrapIconCSS.BI_BADGE_4K_FILL),
  @Deprecated (forRemoval = true, since = "12.3.0")
  BADGE_8K(CBootstrapIconCSS.BI_BADGE_8K),
  @Deprecated (forRemoval = true, since = "12.3.0")
  BADGE_8K_FILL(CBootstrapIconCSS.BI_BADGE_8K_FILL),
  @Deprecated (forRemoval = true, since = "12.3.0")
  BADGE_AD(CBootstrapIconCSS.BI_BADGE_AD),
  @Deprecated (forRemoval = true, since = "12.3.0")
  BADGE_AD_FILL(CBootstrapIconCSS.BI_BADGE_AD_FILL),
  @Deprecated (forRemoval = true, since = "12.3.0")
  BADGE_AR(CBootstrapIconCSS.BI_BADGE_AR),
  @Deprecated (forRemoval = true, since = "12.3.0")
  BADGE_AR_FILL(CBootstrapIconCSS.BI_BADGE_AR_FILL),
  @Deprecated (forRemoval = true, since = "12.3.0")
  BADGE_CC(CBootstrapIconCSS.BI_BADGE_CC),
  @Deprecated (forRemoval = true, since = "12.3.0")
  BADGE_CC_FILL(CBootstrapIconCSS.BI_BADGE_CC_FILL),
  @Deprecated (forRemoval = true, since = "12.3.0")
  BADGE_HD(CBootstrapIconCSS.BI_BADGE_HD),
  @Deprecated (forRemoval = true, since = "12.3.0")
  BADGE_HD_FILL(CBootstrapIconCSS.BI_BADGE_HD_FILL),
  @Deprecated (forRemoval = true, since = "12.3.0")
  BADGE_SD(CBootstrapIconCSS.BI_BADGE_SD),
  @Deprecated (forRemoval = true, since = "12.3.0")
  BADGE_SD_FILL(CBootstrapIconCSS.BI_BADGE_SD_FILL),
  @Deprecated (forRemoval = true, since = "12.3.0")
  BADGE_TM(CBootstrapIconCSS.BI_BADGE_TM),
  @Deprecated (forRemoval = true, since = "12.3.0")
  BADGE_TM_FILL(CBootstrapIconCSS.BI_BADGE_TM_FILL),
  @Deprecated (forRemoval = true, since = "12.3.0")
  BADGE_VO(CBootstrapIconCSS.BI_BADGE_VO),
  @Deprecated (forRemoval = true, since = "12.3.0")
  BADGE_VO_FILL(CBootstrapIconCSS.BI_BADGE_VO_FILL),
  @Deprecated (forRemoval = true, since = "12.3.0")
  BADGE_VR(CBootstrapIconCSS.BI_BADGE_VR),
  @Deprecated (forRemoval = true, since = "12.3.0")
  BADGE_VR_FILL(CBootstrapIconCSS.BI_BADGE_VR_FILL),
  @Deprecated (forRemoval = true, since = "12.3.0")
  BADGE_WC(CBootstrapIconCSS.BI_BADGE_WC),
  @Deprecated (forRemoval = true, since = "12.3.0")
  BADGE_WC_FILL(CBootstrapIconCSS.BI_BADGE_WC_FILL),
  @Deprecated (forRemoval = true, since = "12.3.0")
  BAG(CBootstrapIconCSS.BI_BAG),
  @Deprecated (forRemoval = true, since = "12.3.0")
  BAG_CHECK(CBootstrapIconCSS.BI_BAG_CHECK),
  @Deprecated (forRemoval = true, since = "12.3.0")
  BAG_CHECK_FILL(CBootstrapIconCSS.BI_BAG_CHECK_FILL),
  @Deprecated (forRemoval = true, since = "12.3.0")
  BAG_DASH(CBootstrapIconCSS.BI_BAG_DASH),
  @Deprecated (forRemoval = true, since = "12.3.0")
  BAG_DASH_FILL(CBootstrapIconCSS.BI_BAG_DASH_FILL),
  @Deprecated (forRemoval = true, since = "12.3.0")
  BAG_FILL(CBootstrapIconCSS.BI_BAG_FILL),
  @Deprecated (forRemoval = true, since = "12.3.0")
  BAG_HEART(CBootstrapIconCSS.BI_BAG_HEART),
  @Deprecated (forRemoval = true, since = "12.3.0")
  BAG_HEART_FILL(CBootstrapIconCSS.BI_BAG_HEART_FILL),
  @Deprecated (forRemoval = true, since = "12.3.0")
  BAG_PLUS(CBootstrapIconCSS.BI_BAG_PLUS),
  @Deprecated (forRemoval = true, since = "12.3.0")
  BAG_PLUS_FILL(CBootstrapIconCSS.BI_BAG_PLUS_FILL),
  @Deprecated (forRemoval = true, since = "12.3.0")
  BAG_X(CBootstrapIconCSS.BI_BAG_X),
  @Deprecated (forRemoval = true, since = "12.3.0")
  BAG_X_FILL(CBootstrapIconCSS.BI_BAG_X_FILL),
  @Deprecated (forRemoval = true, since = "12.3.0")
  BALLOON(CBootstrapIconCSS.BI_BALLOON),
  @Deprecated (forRemoval = true, since = "12.3.0")
  BALLOON_FILL(CBootstrapIconCSS.BI_BALLOON_FILL),
  @Deprecated (forRemoval = true, since = "12.3.0")
  BALLOON_HEART(CBootstrapIconCSS.BI_BALLOON_HEART),
  @Deprecated (forRemoval = true, since = "12.3.0")
  BALLOON_HEART_FILL(CBootstrapIconCSS.BI_BALLOON_HEART_FILL),
  @Deprecated (forRemoval = true, since = "12.3.0")
  BAN(CBootstrapIconCSS.BI_BAN),
  @Deprecated (forRemoval = true, since = "12.3.0")
  BAN_FILL(CBootstrapIconCSS.BI_BAN_FILL),
  @Deprecated (forRemoval = true, since = "12.3.0")
  BANDAID(CBootstrapIconCSS.BI_BANDAID),
  @Deprecated (forRemoval = true, since = "12.3.0")
  BANDAID_FILL(CBootstrapIconCSS.BI_BANDAID_FILL),
  @Deprecated (forRemoval = true, since = "12.3.0")
  BANK(CBootstrapIconCSS.BI_BANK),
  @Deprecated (forRemoval = true, since = "12.3.0")
  BANK2(CBootstrapIconCSS.BI_BANK2),
  @Deprecated (forRemoval = true, since = "12.3.0")
  BAR_CHART(CBootstrapIconCSS.BI_BAR_CHART),
  @Deprecated (forRemoval = true, since = "12.3.0")
  BAR_CHART_FILL(CBootstrapIconCSS.BI_BAR_CHART_FILL),
  @Deprecated (forRemoval = true, since = "12.3.0")
  BAR_CHART_LINE(CBootstrapIconCSS.BI_BAR_CHART_LINE),
  @Deprecated (forRemoval = true, since = "12.3.0")
  BAR_CHART_LINE_FILL(CBootstrapIconCSS.BI_BAR_CHART_LINE_FILL),
  @Deprecated (forRemoval = true, since = "12.3.0")
  BAR_CHART_STEPS(CBootstrapIconCSS.BI_BAR_CHART_STEPS),
  @Deprecated (forRemoval = true, since = "12.3.0")
  BASKET(CBootstrapIconCSS.BI_BASKET),
  @Deprecated (forRemoval = true, since = "12.3.0")
  BASKET_FILL(CBootstrapIconCSS.BI_BASKET_FILL),
  @Deprecated (forRemoval = true, since = "12.3.0")
  BASKET2(CBootstrapIconCSS.BI_BASKET2),
  @Deprecated (forRemoval = true, since = "12.3.0")
  BASKET2_FILL(CBootstrapIconCSS.BI_BASKET2_FILL),
  @Deprecated (forRemoval = true, since = "12.3.0")
  BASKET3(CBootstrapIconCSS.BI_BASKET3),
  @Deprecated (forRemoval = true, since = "12.3.0")
  BASKET3_FILL(CBootstrapIconCSS.BI_BASKET3_FILL),
  @Deprecated (forRemoval = true, since = "12.3.0")
  BATTERY(CBootstrapIconCSS.BI_BATTERY),
  @Deprecated (forRemoval = true, since = "12.3.0")
  BATTERY_CHARGING(CBootstrapIconCSS.BI_BATTERY_CHARGING),
  @Deprecated (forRemoval = true, since = "12.3.0")
  BATTERY_FULL(CBootstrapIconCSS.BI_BATTERY_FULL),
  @Deprecated (forRemoval = true, since = "12.3.0")
  BATTERY_HALF(CBootstrapIconCSS.BI_BATTERY_HALF),
  @Deprecated (forRemoval = true, since = "12.3.0")
  BEHANCE(CBootstrapIconCSS.BI_BEHANCE),
  @Deprecated (forRemoval = true, since = "12.3.0")
  BELL(CBootstrapIconCSS.BI_BELL),
  @Deprecated (forRemoval = true, since = "12.3.0")
  BELL_FILL(CBootstrapIconCSS.BI_BELL_FILL),
  @Deprecated (forRemoval = true, since = "12.3.0")
  BELL_SLASH(CBootstrapIconCSS.BI_BELL_SLASH),
  @Deprecated (forRemoval = true, since = "12.3.0")
  BELL_SLASH_FILL(CBootstrapIconCSS.BI_BELL_SLASH_FILL),
  @Deprecated (forRemoval = true, since = "12.3.0")
  BEZIER(CBootstrapIconCSS.BI_BEZIER),
  @Deprecated (forRemoval = true, since = "12.3.0")
  BEZIER2(CBootstrapIconCSS.BI_BEZIER2),
  @Deprecated (forRemoval = true, since = "12.3.0")
  BICYCLE(CBootstrapIconCSS.BI_BICYCLE),
  @Deprecated (forRemoval = true, since = "12.3.0")
  BING(CBootstrapIconCSS.BI_BING),
  @Deprecated (forRemoval = true, since = "12.3.0")
  BINOCULARS(CBootstrapIconCSS.BI_BINOCULARS),
  @Deprecated (forRemoval = true, since = "12.3.0")
  BINOCULARS_FILL(CBootstrapIconCSS.BI_BINOCULARS_FILL),
  @Deprecated (forRemoval = true, since = "12.3.0")
  BLOCKQUOTE_LEFT(CBootstrapIconCSS.BI_BLOCKQUOTE_LEFT),
  @Deprecated (forRemoval = true, since = "12.3.0")
  BLOCKQUOTE_RIGHT(CBootstrapIconCSS.BI_BLOCKQUOTE_RIGHT),
  @Deprecated (forRemoval = true, since = "12.3.0")
  BLUETOOTH(CBootstrapIconCSS.BI_BLUETOOTH),
  @Deprecated (forRemoval = true, since = "12.3.0")
  BODY_TEXT(CBootstrapIconCSS.BI_BODY_TEXT),
  @Deprecated (forRemoval = true, since = "12.3.0")
  BOOK(CBootstrapIconCSS.BI_BOOK),
  @Deprecated (forRemoval = true, since = "12.3.0")
  BOOK_FILL(CBootstrapIconCSS.BI_BOOK_FILL),
  @Deprecated (forRemoval = true, since = "12.3.0")
  BOOK_HALF(CBootstrapIconCSS.BI_BOOK_HALF),
  @Deprecated (forRemoval = true, since = "12.3.0")
  BOOKMARK(CBootstrapIconCSS.BI_BOOKMARK),
  @Deprecated (forRemoval = true, since = "12.3.0")
  BOOKMARK_CHECK(CBootstrapIconCSS.BI_BOOKMARK_CHECK),
  @Deprecated (forRemoval = true, since = "12.3.0")
  BOOKMARK_CHECK_FILL(CBootstrapIconCSS.BI_BOOKMARK_CHECK_FILL),
  @Deprecated (forRemoval = true, since = "12.3.0")
  BOOKMARK_DASH(CBootstrapIconCSS.BI_BOOKMARK_DASH),
  @Deprecated (forRemoval = true, since = "12.3.0")
  BOOKMARK_DASH_FILL(CBootstrapIconCSS.BI_BOOKMARK_DASH_FILL),
  @Deprecated (forRemoval = true, since = "12.3.0")
  BOOKMARK_FILL(CBootstrapIconCSS.BI_BOOKMARK_FILL),
  @Deprecated (forRemoval = true, since = "12.3.0")
  BOOKMARK_HEART(CBootstrapIconCSS.BI_BOOKMARK_HEART),
  @Deprecated (forRemoval = true, since = "12.3.0")
  BOOKMARK_HEART_FILL(CBootstrapIconCSS.BI_BOOKMARK_HEART_FILL),
  @Deprecated (forRemoval = true, since = "12.3.0")
  BOOKMARK_PLUS(CBootstrapIconCSS.BI_BOOKMARK_PLUS),
  @Deprecated (forRemoval = true, since = "12.3.0")
  BOOKMARK_PLUS_FILL(CBootstrapIconCSS.BI_BOOKMARK_PLUS_FILL),
  @Deprecated (forRemoval = true, since = "12.3.0")
  BOOKMARK_STAR(CBootstrapIconCSS.BI_BOOKMARK_STAR),
  @Deprecated (forRemoval = true, since = "12.3.0")
  BOOKMARK_STAR_FILL(CBootstrapIconCSS.BI_BOOKMARK_STAR_FILL),
  @Deprecated (forRemoval = true, since = "12.3.0")
  BOOKMARK_X(CBootstrapIconCSS.BI_BOOKMARK_X),
  @Deprecated (forRemoval = true, since = "12.3.0")
  BOOKMARK_X_FILL(CBootstrapIconCSS.BI_BOOKMARK_X_FILL),
  @Deprecated (forRemoval = true, since = "12.3.0")
  BOOKMARKS(CBootstrapIconCSS.BI_BOOKMARKS),
  @Deprecated (forRemoval = true, since = "12.3.0")
  BOOKMARKS_FILL(CBootstrapIconCSS.BI_BOOKMARKS_FILL),
  @Deprecated (forRemoval = true, since = "12.3.0")
  BOOKSHELF(CBootstrapIconCSS.BI_BOOKSHELF),
  @Deprecated (forRemoval = true, since = "12.3.0")
  BOOMBOX(CBootstrapIconCSS.BI_BOOMBOX),
  @Deprecated (forRemoval = true, since = "12.3.0")
  BOOMBOX_FILL(CBootstrapIconCSS.BI_BOOMBOX_FILL),
  @Deprecated (forRemoval = true, since = "12.3.0")
  BOOTSTRAP(CBootstrapIconCSS.BI_BOOTSTRAP),
  @Deprecated (forRemoval = true, since = "12.3.0")
  BOOTSTRAP_FILL(CBootstrapIconCSS.BI_BOOTSTRAP_FILL),
  @Deprecated (forRemoval = true, since = "12.3.0")
  BOOTSTRAP_REBOOT(CBootstrapIconCSS.BI_BOOTSTRAP_REBOOT),
  @Deprecated (forRemoval = true, since = "12.3.0")
  BORDER(CBootstrapIconCSS.BI_BORDER),
  @Deprecated (forRemoval = true, since = "12.3.0")
  BORDER_ALL(CBootstrapIconCSS.BI_BORDER_ALL),
  @Deprecated (forRemoval = true, since = "12.3.0")
  BORDER_BOTTOM(CBootstrapIconCSS.BI_BORDER_BOTTOM),
  @Deprecated (forRemoval = true, since = "12.3.0")
  BORDER_CENTER(CBootstrapIconCSS.BI_BORDER_CENTER),
  @Deprecated (forRemoval = true, since = "12.3.0")
  BORDER_INNER(CBootstrapIconCSS.BI_BORDER_INNER),
  @Deprecated (forRemoval = true, since = "12.3.0")
  BORDER_LEFT(CBootstrapIconCSS.BI_BORDER_LEFT),
  @Deprecated (forRemoval = true, since = "12.3.0")
  BORDER_MIDDLE(CBootstrapIconCSS.BI_BORDER_MIDDLE),
  @Deprecated (forRemoval = true, since = "12.3.0")
  BORDER_OUTER(CBootstrapIconCSS.BI_BORDER_OUTER),
  @Deprecated (forRemoval = true, since = "12.3.0")
  BORDER_RIGHT(CBootstrapIconCSS.BI_BORDER_RIGHT),
  @Deprecated (forRemoval = true, since = "12.3.0")
  BORDER_STYLE(CBootstrapIconCSS.BI_BORDER_STYLE),
  @Deprecated (forRemoval = true, since = "12.3.0")
  BORDER_TOP(CBootstrapIconCSS.BI_BORDER_TOP),
  @Deprecated (forRemoval = true, since = "12.3.0")
  BORDER_WIDTH(CBootstrapIconCSS.BI_BORDER_WIDTH),
  @Deprecated (forRemoval = true, since = "12.3.0")
  BOUNDING_BOX(CBootstrapIconCSS.BI_BOUNDING_BOX),
  @Deprecated (forRemoval = true, since = "12.3.0")
  BOUNDING_BOX_CIRCLES(CBootstrapIconCSS.BI_BOUNDING_BOX_CIRCLES),
  @Deprecated (forRemoval = true, since = "12.3.0")
  BOX(CBootstrapIconCSS.BI_BOX),
  @Deprecated (forRemoval = true, since = "12.3.0")
  BOX_ARROW_DOWN(CBootstrapIconCSS.BI_BOX_ARROW_DOWN),
  @Deprecated (forRemoval = true, since = "12.3.0")
  BOX_ARROW_DOWN_LEFT(CBootstrapIconCSS.BI_BOX_ARROW_DOWN_LEFT),
  @Deprecated (forRemoval = true, since = "12.3.0")
  BOX_ARROW_DOWN_RIGHT(CBootstrapIconCSS.BI_BOX_ARROW_DOWN_RIGHT),
  @Deprecated (forRemoval = true, since = "12.3.0")
  BOX_ARROW_IN_DOWN(CBootstrapIconCSS.BI_BOX_ARROW_IN_DOWN),
  @Deprecated (forRemoval = true, since = "12.3.0")
  BOX_ARROW_IN_DOWN_LEFT(CBootstrapIconCSS.BI_BOX_ARROW_IN_DOWN_LEFT),
  @Deprecated (forRemoval = true, since = "12.3.0")
  BOX_ARROW_IN_DOWN_RIGHT(CBootstrapIconCSS.BI_BOX_ARROW_IN_DOWN_RIGHT),
  @Deprecated (forRemoval = true, since = "12.3.0")
  BOX_ARROW_IN_LEFT(CBootstrapIconCSS.BI_BOX_ARROW_IN_LEFT),
  @Deprecated (forRemoval = true, since = "12.3.0")
  BOX_ARROW_IN_RIGHT(CBootstrapIconCSS.BI_BOX_ARROW_IN_RIGHT),
  @Deprecated (forRemoval = true, since = "12.3.0")
  BOX_ARROW_IN_UP(CBootstrapIconCSS.BI_BOX_ARROW_IN_UP),
  @Deprecated (forRemoval = true, since = "12.3.0")
  BOX_ARROW_IN_UP_LEFT(CBootstrapIconCSS.BI_BOX_ARROW_IN_UP_LEFT),
  @Deprecated (forRemoval = true, since = "12.3.0")
  BOX_ARROW_IN_UP_RIGHT(CBootstrapIconCSS.BI_BOX_ARROW_IN_UP_RIGHT),
  @Deprecated (forRemoval = true, since = "12.3.0")
  BOX_ARROW_LEFT(CBootstrapIconCSS.BI_BOX_ARROW_LEFT),
  @Deprecated (forRemoval = true, since = "12.3.0")
  BOX_ARROW_RIGHT(CBootstrapIconCSS.BI_BOX_ARROW_RIGHT),
  @Deprecated (forRemoval = true, since = "12.3.0")
  BOX_ARROW_UP(CBootstrapIconCSS.BI_BOX_ARROW_UP),
  @Deprecated (forRemoval = true, since = "12.3.0")
  BOX_ARROW_UP_LEFT(CBootstrapIconCSS.BI_BOX_ARROW_UP_LEFT),
  @Deprecated (forRemoval = true, since = "12.3.0")
  BOX_ARROW_UP_RIGHT(CBootstrapIconCSS.BI_BOX_ARROW_UP_RIGHT),
  @Deprecated (forRemoval = true, since = "12.3.0")
  BOX_FILL(CBootstrapIconCSS.BI_BOX_FILL),
  @Deprecated (forRemoval = true, since = "12.3.0")
  BOX_SEAM(CBootstrapIconCSS.BI_BOX_SEAM),
  @Deprecated (forRemoval = true, since = "12.3.0")
  BOX_SEAM_FILL(CBootstrapIconCSS.BI_BOX_SEAM_FILL),
  @Deprecated (forRemoval = true, since = "12.3.0")
  BOX2(CBootstrapIconCSS.BI_BOX2),
  @Deprecated (forRemoval = true, since = "12.3.0")
  BOX2_FILL(CBootstrapIconCSS.BI_BOX2_FILL),
  @Deprecated (forRemoval = true, since = "12.3.0")
  BOX2_HEART(CBootstrapIconCSS.BI_BOX2_HEART),
  @Deprecated (forRemoval = true, since = "12.3.0")
  BOX2_HEART_FILL(CBootstrapIconCSS.BI_BOX2_HEART_FILL),
  @Deprecated (forRemoval = true, since = "12.3.0")
  BOXES(CBootstrapIconCSS.BI_BOXES),
  @Deprecated (forRemoval = true, since = "12.3.0")
  BRACES(CBootstrapIconCSS.BI_BRACES),
  @Deprecated (forRemoval = true, since = "12.3.0")
  BRACES_ASTERISK(CBootstrapIconCSS.BI_BRACES_ASTERISK),
  @Deprecated (forRemoval = true, since = "12.3.0")
  BRICKS(CBootstrapIconCSS.BI_BRICKS),
  @Deprecated (forRemoval = true, since = "12.3.0")
  BRIEFCASE(CBootstrapIconCSS.BI_BRIEFCASE),
  @Deprecated (forRemoval = true, since = "12.3.0")
  BRIEFCASE_FILL(CBootstrapIconCSS.BI_BRIEFCASE_FILL),
  @Deprecated (forRemoval = true, since = "12.3.0")
  BRIGHTNESS_ALT_HIGH(CBootstrapIconCSS.BI_BRIGHTNESS_ALT_HIGH),
  @Deprecated (forRemoval = true, since = "12.3.0")
  BRIGHTNESS_ALT_HIGH_FILL(CBootstrapIconCSS.BI_BRIGHTNESS_ALT_HIGH_FILL),
  @Deprecated (forRemoval = true, since = "12.3.0")
  BRIGHTNESS_ALT_LOW(CBootstrapIconCSS.BI_BRIGHTNESS_ALT_LOW),
  @Deprecated (forRemoval = true, since = "12.3.0")
  BRIGHTNESS_ALT_LOW_FILL(CBootstrapIconCSS.BI_BRIGHTNESS_ALT_LOW_FILL),
  @Deprecated (forRemoval = true, since = "12.3.0")
  BRIGHTNESS_HIGH(CBootstrapIconCSS.BI_BRIGHTNESS_HIGH),
  @Deprecated (forRemoval = true, since = "12.3.0")
  BRIGHTNESS_HIGH_FILL(CBootstrapIconCSS.BI_BRIGHTNESS_HIGH_FILL),
  @Deprecated (forRemoval = true, since = "12.3.0")
  BRIGHTNESS_LOW(CBootstrapIconCSS.BI_BRIGHTNESS_LOW),
  @Deprecated (forRemoval = true, since = "12.3.0")
  BRIGHTNESS_LOW_FILL(CBootstrapIconCSS.BI_BRIGHTNESS_LOW_FILL),
  @Deprecated (forRemoval = true, since = "12.3.0")
  BRILLIANCE(CBootstrapIconCSS.BI_BRILLIANCE),
  @Deprecated (forRemoval = true, since = "12.3.0")
  BROADCAST(CBootstrapIconCSS.BI_BROADCAST),
  @Deprecated (forRemoval = true, since = "12.3.0")
  BROADCAST_PIN(CBootstrapIconCSS.BI_BROADCAST_PIN),
  @Deprecated (forRemoval = true, since = "12.3.0")
  BROWSER_CHROME(CBootstrapIconCSS.BI_BROWSER_CHROME),
  @Deprecated (forRemoval = true, since = "12.3.0")
  BROWSER_EDGE(CBootstrapIconCSS.BI_BROWSER_EDGE),
  @Deprecated (forRemoval = true, since = "12.3.0")
  BROWSER_FIREFOX(CBootstrapIconCSS.BI_BROWSER_FIREFOX),
  @Deprecated (forRemoval = true, since = "12.3.0")
  BROWSER_SAFARI(CBootstrapIconCSS.BI_BROWSER_SAFARI),
  @Deprecated (forRemoval = true, since = "12.3.0")
  BRUSH(CBootstrapIconCSS.BI_BRUSH),
  @Deprecated (forRemoval = true, since = "12.3.0")
  BRUSH_FILL(CBootstrapIconCSS.BI_BRUSH_FILL),
  @Deprecated (forRemoval = true, since = "12.3.0")
  BUCKET(CBootstrapIconCSS.BI_BUCKET),
  @Deprecated (forRemoval = true, since = "12.3.0")
  BUCKET_FILL(CBootstrapIconCSS.BI_BUCKET_FILL),
  @Deprecated (forRemoval = true, since = "12.3.0")
  BUG(CBootstrapIconCSS.BI_BUG),
  @Deprecated (forRemoval = true, since = "12.3.0")
  BUG_FILL(CBootstrapIconCSS.BI_BUG_FILL),
  @Deprecated (forRemoval = true, since = "12.3.0")
  BUILDING(CBootstrapIconCSS.BI_BUILDING),
  @Deprecated (forRemoval = true, since = "12.3.0")
  BUILDING_ADD(CBootstrapIconCSS.BI_BUILDING_ADD),
  @Deprecated (forRemoval = true, since = "12.3.0")
  BUILDING_CHECK(CBootstrapIconCSS.BI_BUILDING_CHECK),
  @Deprecated (forRemoval = true, since = "12.3.0")
  BUILDING_DASH(CBootstrapIconCSS.BI_BUILDING_DASH),
  @Deprecated (forRemoval = true, since = "12.3.0")
  BUILDING_DOWN(CBootstrapIconCSS.BI_BUILDING_DOWN),
  @Deprecated (forRemoval = true, since = "12.3.0")
  BUILDING_EXCLAMATION(CBootstrapIconCSS.BI_BUILDING_EXCLAMATION),
  @Deprecated (forRemoval = true, since = "12.3.0")
  BUILDING_FILL(CBootstrapIconCSS.BI_BUILDING_FILL),
  @Deprecated (forRemoval = true, since = "12.3.0")
  BUILDING_FILL_ADD(CBootstrapIconCSS.BI_BUILDING_FILL_ADD),
  @Deprecated (forRemoval = true, since = "12.3.0")
  BUILDING_FILL_CHECK(CBootstrapIconCSS.BI_BUILDING_FILL_CHECK),
  @Deprecated (forRemoval = true, since = "12.3.0")
  BUILDING_FILL_DASH(CBootstrapIconCSS.BI_BUILDING_FILL_DASH),
  @Deprecated (forRemoval = true, since = "12.3.0")
  BUILDING_FILL_DOWN(CBootstrapIconCSS.BI_BUILDING_FILL_DOWN),
  @Deprecated (forRemoval = true, since = "12.3.0")
  BUILDING_FILL_EXCLAMATION(CBootstrapIconCSS.BI_BUILDING_FILL_EXCLAMATION),
  @Deprecated (forRemoval = true, since = "12.3.0")
  BUILDING_FILL_GEAR(CBootstrapIconCSS.BI_BUILDING_FILL_GEAR),
  @Deprecated (forRemoval = true, since = "12.3.0")
  BUILDING_FILL_LOCK(CBootstrapIconCSS.BI_BUILDING_FILL_LOCK),
  @Deprecated (forRemoval = true, since = "12.3.0")
  BUILDING_FILL_SLASH(CBootstrapIconCSS.BI_BUILDING_FILL_SLASH),
  @Deprecated (forRemoval = true, since = "12.3.0")
  BUILDING_FILL_UP(CBootstrapIconCSS.BI_BUILDING_FILL_UP),
  @Deprecated (forRemoval = true, since = "12.3.0")
  BUILDING_FILL_X(CBootstrapIconCSS.BI_BUILDING_FILL_X),
  @Deprecated (forRemoval = true, since = "12.3.0")
  BUILDING_GEAR(CBootstrapIconCSS.BI_BUILDING_GEAR),
  @Deprecated (forRemoval = true, since = "12.3.0")
  BUILDING_LOCK(CBootstrapIconCSS.BI_BUILDING_LOCK),
  @Deprecated (forRemoval = true, since = "12.3.0")
  BUILDING_SLASH(CBootstrapIconCSS.BI_BUILDING_SLASH),
  @Deprecated (forRemoval = true, since = "12.3.0")
  BUILDING_UP(CBootstrapIconCSS.BI_BUILDING_UP),
  @Deprecated (forRemoval = true, since = "12.3.0")
  BUILDING_X(CBootstrapIconCSS.BI_BUILDING_X),
  @Deprecated (forRemoval = true, since = "12.3.0")
  BUILDINGS(CBootstrapIconCSS.BI_BUILDINGS),
  @Deprecated (forRemoval = true, since = "12.3.0")
  BUILDINGS_FILL(CBootstrapIconCSS.BI_BUILDINGS_FILL),
  @Deprecated (forRemoval = true, since = "12.3.0")
  BULLSEYE(CBootstrapIconCSS.BI_BULLSEYE),
  @Deprecated (forRemoval = true, since = "12.3.0")
  BUS_FRONT(CBootstrapIconCSS.BI_BUS_FRONT),
  @Deprecated (forRemoval = true, since = "12.3.0")
  BUS_FRONT_FILL(CBootstrapIconCSS.BI_BUS_FRONT_FILL),
  @Deprecated (forRemoval = true, since = "12.3.0")
  C_CIRCLE(CBootstrapIconCSS.BI_C_CIRCLE),
  @Deprecated (forRemoval = true, since = "12.3.0")
  C_CIRCLE_FILL(CBootstrapIconCSS.BI_C_CIRCLE_FILL),
  @Deprecated (forRemoval = true, since = "12.3.0")
  C_SQUARE(CBootstrapIconCSS.BI_C_SQUARE),
  @Deprecated (forRemoval = true, since = "12.3.0")
  C_SQUARE_FILL(CBootstrapIconCSS.BI_C_SQUARE_FILL),
  @Deprecated (forRemoval = true, since = "12.3.0")
  CAKE(CBootstrapIconCSS.BI_CAKE),
  @Deprecated (forRemoval = true, since = "12.3.0")
  CAKE_FILL(CBootstrapIconCSS.BI_CAKE_FILL),
  @Deprecated (forRemoval = true, since = "12.3.0")
  CAKE2(CBootstrapIconCSS.BI_CAKE2),
  @Deprecated (forRemoval = true, since = "12.3.0")
  CAKE2_FILL(CBootstrapIconCSS.BI_CAKE2_FILL),
  @Deprecated (forRemoval = true, since = "12.3.0")
  CALCULATOR(CBootstrapIconCSS.BI_CALCULATOR),
  @Deprecated (forRemoval = true, since = "12.3.0")
  CALCULATOR_FILL(CBootstrapIconCSS.BI_CALCULATOR_FILL),
  @Deprecated (forRemoval = true, since = "12.3.0")
  CALENDAR(CBootstrapIconCSS.BI_CALENDAR),
  @Deprecated (forRemoval = true, since = "12.3.0")
  CALENDAR_CHECK(CBootstrapIconCSS.BI_CALENDAR_CHECK),
  @Deprecated (forRemoval = true, since = "12.3.0")
  CALENDAR_CHECK_FILL(CBootstrapIconCSS.BI_CALENDAR_CHECK_FILL),
  @Deprecated (forRemoval = true, since = "12.3.0")
  CALENDAR_DATE(CBootstrapIconCSS.BI_CALENDAR_DATE),
  @Deprecated (forRemoval = true, since = "12.3.0")
  CALENDAR_DATE_FILL(CBootstrapIconCSS.BI_CALENDAR_DATE_FILL),
  @Deprecated (forRemoval = true, since = "12.3.0")
  CALENDAR_DAY(CBootstrapIconCSS.BI_CALENDAR_DAY),
  @Deprecated (forRemoval = true, since = "12.3.0")
  CALENDAR_DAY_FILL(CBootstrapIconCSS.BI_CALENDAR_DAY_FILL),
  @Deprecated (forRemoval = true, since = "12.3.0")
  CALENDAR_EVENT(CBootstrapIconCSS.BI_CALENDAR_EVENT),
  @Deprecated (forRemoval = true, since = "12.3.0")
  CALENDAR_EVENT_FILL(CBootstrapIconCSS.BI_CALENDAR_EVENT_FILL),
  @Deprecated (forRemoval = true, since = "12.3.0")
  CALENDAR_FILL(CBootstrapIconCSS.BI_CALENDAR_FILL),
  @Deprecated (forRemoval = true, since = "12.3.0")
  CALENDAR_HEART(CBootstrapIconCSS.BI_CALENDAR_HEART),
  @Deprecated (forRemoval = true, since = "12.3.0")
  CALENDAR_HEART_FILL(CBootstrapIconCSS.BI_CALENDAR_HEART_FILL),
  @Deprecated (forRemoval = true, since = "12.3.0")
  CALENDAR_MINUS(CBootstrapIconCSS.BI_CALENDAR_MINUS),
  @Deprecated (forRemoval = true, since = "12.3.0")
  CALENDAR_MINUS_FILL(CBootstrapIconCSS.BI_CALENDAR_MINUS_FILL),
  @Deprecated (forRemoval = true, since = "12.3.0")
  CALENDAR_MONTH(CBootstrapIconCSS.BI_CALENDAR_MONTH),
  @Deprecated (forRemoval = true, since = "12.3.0")
  CALENDAR_MONTH_FILL(CBootstrapIconCSS.BI_CALENDAR_MONTH_FILL),
  @Deprecated (forRemoval = true, since = "12.3.0")
  CALENDAR_PLUS(CBootstrapIconCSS.BI_CALENDAR_PLUS),
  @Deprecated (forRemoval = true, since = "12.3.0")
  CALENDAR_PLUS_FILL(CBootstrapIconCSS.BI_CALENDAR_PLUS_FILL),
  @Deprecated (forRemoval = true, since = "12.3.0")
  CALENDAR_RANGE(CBootstrapIconCSS.BI_CALENDAR_RANGE),
  @Deprecated (forRemoval = true, since = "12.3.0")
  CALENDAR_RANGE_FILL(CBootstrapIconCSS.BI_CALENDAR_RANGE_FILL),
  @Deprecated (forRemoval = true, since = "12.3.0")
  CALENDAR_WEEK(CBootstrapIconCSS.BI_CALENDAR_WEEK),
  @Deprecated (forRemoval = true, since = "12.3.0")
  CALENDAR_WEEK_FILL(CBootstrapIconCSS.BI_CALENDAR_WEEK_FILL),
  @Deprecated (forRemoval = true, since = "12.3.0")
  CALENDAR_X(CBootstrapIconCSS.BI_CALENDAR_X),
  @Deprecated (forRemoval = true, since = "12.3.0")
  CALENDAR_X_FILL(CBootstrapIconCSS.BI_CALENDAR_X_FILL),
  @Deprecated (forRemoval = true, since = "12.3.0")
  CALENDAR2(CBootstrapIconCSS.BI_CALENDAR2),
  @Deprecated (forRemoval = true, since = "12.3.0")
  CALENDAR2_CHECK(CBootstrapIconCSS.BI_CALENDAR2_CHECK),
  @Deprecated (forRemoval = true, since = "12.3.0")
  CALENDAR2_CHECK_FILL(CBootstrapIconCSS.BI_CALENDAR2_CHECK_FILL),
  @Deprecated (forRemoval = true, since = "12.3.0")
  CALENDAR2_DATE(CBootstrapIconCSS.BI_CALENDAR2_DATE),
  @Deprecated (forRemoval = true, since = "12.3.0")
  CALENDAR2_DATE_FILL(CBootstrapIconCSS.BI_CALENDAR2_DATE_FILL),
  @Deprecated (forRemoval = true, since = "12.3.0")
  CALENDAR2_DAY(CBootstrapIconCSS.BI_CALENDAR2_DAY),
  @Deprecated (forRemoval = true, since = "12.3.0")
  CALENDAR2_DAY_FILL(CBootstrapIconCSS.BI_CALENDAR2_DAY_FILL),
  @Deprecated (forRemoval = true, since = "12.3.0")
  CALENDAR2_EVENT(CBootstrapIconCSS.BI_CALENDAR2_EVENT),
  @Deprecated (forRemoval = true, since = "12.3.0")
  CALENDAR2_EVENT_FILL(CBootstrapIconCSS.BI_CALENDAR2_EVENT_FILL),
  @Deprecated (forRemoval = true, since = "12.3.0")
  CALENDAR2_FILL(CBootstrapIconCSS.BI_CALENDAR2_FILL),
  @Deprecated (forRemoval = true, since = "12.3.0")
  CALENDAR2_HEART(CBootstrapIconCSS.BI_CALENDAR2_HEART),
  @Deprecated (forRemoval = true, since = "12.3.0")
  CALENDAR2_HEART_FILL(CBootstrapIconCSS.BI_CALENDAR2_HEART_FILL),
  @Deprecated (forRemoval = true, since = "12.3.0")
  CALENDAR2_MINUS(CBootstrapIconCSS.BI_CALENDAR2_MINUS),
  @Deprecated (forRemoval = true, since = "12.3.0")
  CALENDAR2_MINUS_FILL(CBootstrapIconCSS.BI_CALENDAR2_MINUS_FILL),
  @Deprecated (forRemoval = true, since = "12.3.0")
  CALENDAR2_MONTH(CBootstrapIconCSS.BI_CALENDAR2_MONTH),
  @Deprecated (forRemoval = true, since = "12.3.0")
  CALENDAR2_MONTH_FILL(CBootstrapIconCSS.BI_CALENDAR2_MONTH_FILL),
  @Deprecated (forRemoval = true, since = "12.3.0")
  CALENDAR2_PLUS(CBootstrapIconCSS.BI_CALENDAR2_PLUS),
  @Deprecated (forRemoval = true, since = "12.3.0")
  CALENDAR2_PLUS_FILL(CBootstrapIconCSS.BI_CALENDAR2_PLUS_FILL),
  @Deprecated (forRemoval = true, since = "12.3.0")
  CALENDAR2_RANGE(CBootstrapIconCSS.BI_CALENDAR2_RANGE),
  @Deprecated (forRemoval = true, since = "12.3.0")
  CALENDAR2_RANGE_FILL(CBootstrapIconCSS.BI_CALENDAR2_RANGE_FILL),
  @Deprecated (forRemoval = true, since = "12.3.0")
  CALENDAR2_WEEK(CBootstrapIconCSS.BI_CALENDAR2_WEEK),
  @Deprecated (forRemoval = true, since = "12.3.0")
  CALENDAR2_WEEK_FILL(CBootstrapIconCSS.BI_CALENDAR2_WEEK_FILL),
  @Deprecated (forRemoval = true, since = "12.3.0")
  CALENDAR2_X(CBootstrapIconCSS.BI_CALENDAR2_X),
  @Deprecated (forRemoval = true, since = "12.3.0")
  CALENDAR2_X_FILL(CBootstrapIconCSS.BI_CALENDAR2_X_FILL),
  @Deprecated (forRemoval = true, since = "12.3.0")
  CALENDAR3(CBootstrapIconCSS.BI_CALENDAR3),
  @Deprecated (forRemoval = true, since = "12.3.0")
  CALENDAR3_EVENT(CBootstrapIconCSS.BI_CALENDAR3_EVENT),
  @Deprecated (forRemoval = true, since = "12.3.0")
  CALENDAR3_EVENT_FILL(CBootstrapIconCSS.BI_CALENDAR3_EVENT_FILL),
  @Deprecated (forRemoval = true, since = "12.3.0")
  CALENDAR3_FILL(CBootstrapIconCSS.BI_CALENDAR3_FILL),
  @Deprecated (forRemoval = true, since = "12.3.0")
  CALENDAR3_RANGE(CBootstrapIconCSS.BI_CALENDAR3_RANGE),
  @Deprecated (forRemoval = true, since = "12.3.0")
  CALENDAR3_RANGE_FILL(CBootstrapIconCSS.BI_CALENDAR3_RANGE_FILL),
  @Deprecated (forRemoval = true, since = "12.3.0")
  CALENDAR3_WEEK(CBootstrapIconCSS.BI_CALENDAR3_WEEK),
  @Deprecated (forRemoval = true, since = "12.3.0")
  CALENDAR3_WEEK_FILL(CBootstrapIconCSS.BI_CALENDAR3_WEEK_FILL),
  @Deprecated (forRemoval = true, since = "12.3.0")
  CALENDAR4(CBootstrapIconCSS.BI_CALENDAR4),
  @Deprecated (forRemoval = true, since = "12.3.0")
  CALENDAR4_EVENT(CBootstrapIconCSS.BI_CALENDAR4_EVENT),
  @Deprecated (forRemoval = true, since = "12.3.0")
  CALENDAR4_RANGE(CBootstrapIconCSS.BI_CALENDAR4_RANGE),
  @Deprecated (forRemoval = true, since = "12.3.0")
  CALENDAR4_WEEK(CBootstrapIconCSS.BI_CALENDAR4_WEEK),
  @Deprecated (forRemoval = true, since = "12.3.0")
  CAMERA(CBootstrapIconCSS.BI_CAMERA),
  @Deprecated (forRemoval = true, since = "12.3.0")
  CAMERA_FILL(CBootstrapIconCSS.BI_CAMERA_FILL),
  @Deprecated (forRemoval = true, since = "12.3.0")
  CAMERA_REELS(CBootstrapIconCSS.BI_CAMERA_REELS),
  @Deprecated (forRemoval = true, since = "12.3.0")
  CAMERA_REELS_FILL(CBootstrapIconCSS.BI_CAMERA_REELS_FILL),
  @Deprecated (forRemoval = true, since = "12.3.0")
  CAMERA_VIDEO(CBootstrapIconCSS.BI_CAMERA_VIDEO),
  @Deprecated (forRemoval = true, since = "12.3.0")
  CAMERA_VIDEO_FILL(CBootstrapIconCSS.BI_CAMERA_VIDEO_FILL),
  @Deprecated (forRemoval = true, since = "12.3.0")
  CAMERA_VIDEO_OFF(CBootstrapIconCSS.BI_CAMERA_VIDEO_OFF),
  @Deprecated (forRemoval = true, since = "12.3.0")
  CAMERA_VIDEO_OFF_FILL(CBootstrapIconCSS.BI_CAMERA_VIDEO_OFF_FILL),
  @Deprecated (forRemoval = true, since = "12.3.0")
  CAMERA2(CBootstrapIconCSS.BI_CAMERA2),
  @Deprecated (forRemoval = true, since = "12.3.0")
  CAPSLOCK(CBootstrapIconCSS.BI_CAPSLOCK),
  @Deprecated (forRemoval = true, since = "12.3.0")
  CAPSLOCK_FILL(CBootstrapIconCSS.BI_CAPSLOCK_FILL),
  @Deprecated (forRemoval = true, since = "12.3.0")
  CAPSULE(CBootstrapIconCSS.BI_CAPSULE),
  @Deprecated (forRemoval = true, since = "12.3.0")
  CAPSULE_PILL(CBootstrapIconCSS.BI_CAPSULE_PILL),
  @Deprecated (forRemoval = true, since = "12.3.0")
  CAR_FRONT(CBootstrapIconCSS.BI_CAR_FRONT),
  @Deprecated (forRemoval = true, since = "12.3.0")
  CAR_FRONT_FILL(CBootstrapIconCSS.BI_CAR_FRONT_FILL),
  @Deprecated (forRemoval = true, since = "12.3.0")
  CARD_CHECKLIST(CBootstrapIconCSS.BI_CARD_CHECKLIST),
  @Deprecated (forRemoval = true, since = "12.3.0")
  CARD_HEADING(CBootstrapIconCSS.BI_CARD_HEADING),
  @Deprecated (forRemoval = true, since = "12.3.0")
  CARD_IMAGE(CBootstrapIconCSS.BI_CARD_IMAGE),
  @Deprecated (forRemoval = true, since = "12.3.0")
  CARD_LIST(CBootstrapIconCSS.BI_CARD_LIST),
  @Deprecated (forRemoval = true, since = "12.3.0")
  CARD_TEXT(CBootstrapIconCSS.BI_CARD_TEXT),
  @Deprecated (forRemoval = true, since = "12.3.0")
  CARET_DOWN(CBootstrapIconCSS.BI_CARET_DOWN),
  @Deprecated (forRemoval = true, since = "12.3.0")
  CARET_DOWN_FILL(CBootstrapIconCSS.BI_CARET_DOWN_FILL),
  @Deprecated (forRemoval = true, since = "12.3.0")
  CARET_DOWN_SQUARE(CBootstrapIconCSS.BI_CARET_DOWN_SQUARE),
  @Deprecated (forRemoval = true, since = "12.3.0")
  CARET_DOWN_SQUARE_FILL(CBootstrapIconCSS.BI_CARET_DOWN_SQUARE_FILL),
  @Deprecated (forRemoval = true, since = "12.3.0")
  CARET_LEFT(CBootstrapIconCSS.BI_CARET_LEFT),
  @Deprecated (forRemoval = true, since = "12.3.0")
  CARET_LEFT_FILL(CBootstrapIconCSS.BI_CARET_LEFT_FILL),
  @Deprecated (forRemoval = true, since = "12.3.0")
  CARET_LEFT_SQUARE(CBootstrapIconCSS.BI_CARET_LEFT_SQUARE),
  @Deprecated (forRemoval = true, since = "12.3.0")
  CARET_LEFT_SQUARE_FILL(CBootstrapIconCSS.BI_CARET_LEFT_SQUARE_FILL),
  @Deprecated (forRemoval = true, since = "12.3.0")
  CARET_RIGHT(CBootstrapIconCSS.BI_CARET_RIGHT),
  @Deprecated (forRemoval = true, since = "12.3.0")
  CARET_RIGHT_FILL(CBootstrapIconCSS.BI_CARET_RIGHT_FILL),
  @Deprecated (forRemoval = true, since = "12.3.0")
  CARET_RIGHT_SQUARE(CBootstrapIconCSS.BI_CARET_RIGHT_SQUARE),
  @Deprecated (forRemoval = true, since = "12.3.0")
  CARET_RIGHT_SQUARE_FILL(CBootstrapIconCSS.BI_CARET_RIGHT_SQUARE_FILL),
  @Deprecated (forRemoval = true, since = "12.3.0")
  CARET_UP(CBootstrapIconCSS.BI_CARET_UP),
  @Deprecated (forRemoval = true, since = "12.3.0")
  CARET_UP_FILL(CBootstrapIconCSS.BI_CARET_UP_FILL),
  @Deprecated (forRemoval = true, since = "12.3.0")
  CARET_UP_SQUARE(CBootstrapIconCSS.BI_CARET_UP_SQUARE),
  @Deprecated (forRemoval = true, since = "12.3.0")
  CARET_UP_SQUARE_FILL(CBootstrapIconCSS.BI_CARET_UP_SQUARE_FILL),
  @Deprecated (forRemoval = true, since = "12.3.0")
  CART(CBootstrapIconCSS.BI_CART),
  @Deprecated (forRemoval = true, since = "12.3.0")
  CART_CHECK(CBootstrapIconCSS.BI_CART_CHECK),
  @Deprecated (forRemoval = true, since = "12.3.0")
  CART_CHECK_FILL(CBootstrapIconCSS.BI_CART_CHECK_FILL),
  @Deprecated (forRemoval = true, since = "12.3.0")
  CART_DASH(CBootstrapIconCSS.BI_CART_DASH),
  @Deprecated (forRemoval = true, since = "12.3.0")
  CART_DASH_FILL(CBootstrapIconCSS.BI_CART_DASH_FILL),
  @Deprecated (forRemoval = true, since = "12.3.0")
  CART_FILL(CBootstrapIconCSS.BI_CART_FILL),
  @Deprecated (forRemoval = true, since = "12.3.0")
  CART_PLUS(CBootstrapIconCSS.BI_CART_PLUS),
  @Deprecated (forRemoval = true, since = "12.3.0")
  CART_PLUS_FILL(CBootstrapIconCSS.BI_CART_PLUS_FILL),
  @Deprecated (forRemoval = true, since = "12.3.0")
  CART_X(CBootstrapIconCSS.BI_CART_X),
  @Deprecated (forRemoval = true, since = "12.3.0")
  CART_X_FILL(CBootstrapIconCSS.BI_CART_X_FILL),
  @Deprecated (forRemoval = true, since = "12.3.0")
  CART2(CBootstrapIconCSS.BI_CART2),
  @Deprecated (forRemoval = true, since = "12.3.0")
  CART3(CBootstrapIconCSS.BI_CART3),
  @Deprecated (forRemoval = true, since = "12.3.0")
  CART4(CBootstrapIconCSS.BI_CART4),
  @Deprecated (forRemoval = true, since = "12.3.0")
  CASH(CBootstrapIconCSS.BI_CASH),
  @Deprecated (forRemoval = true, since = "12.3.0")
  CASH_COIN(CBootstrapIconCSS.BI_CASH_COIN),
  @Deprecated (forRemoval = true, since = "12.3.0")
  CASH_STACK(CBootstrapIconCSS.BI_CASH_STACK),
  @Deprecated (forRemoval = true, since = "12.3.0")
  CASSETTE(CBootstrapIconCSS.BI_CASSETTE),
  @Deprecated (forRemoval = true, since = "12.3.0")
  CASSETTE_FILL(CBootstrapIconCSS.BI_CASSETTE_FILL),
  @Deprecated (forRemoval = true, since = "12.3.0")
  CAST(CBootstrapIconCSS.BI_CAST),
  @Deprecated (forRemoval = true, since = "12.3.0")
  CC_CIRCLE(CBootstrapIconCSS.BI_CC_CIRCLE),
  @Deprecated (forRemoval = true, since = "12.3.0")
  CC_CIRCLE_FILL(CBootstrapIconCSS.BI_CC_CIRCLE_FILL),
  @Deprecated (forRemoval = true, since = "12.3.0")
  CC_SQUARE(CBootstrapIconCSS.BI_CC_SQUARE),
  @Deprecated (forRemoval = true, since = "12.3.0")
  CC_SQUARE_FILL(CBootstrapIconCSS.BI_CC_SQUARE_FILL),
  @Deprecated (forRemoval = true, since = "12.3.0")
  CHAT(CBootstrapIconCSS.BI_CHAT),
  @Deprecated (forRemoval = true, since = "12.3.0")
  CHAT_DOTS(CBootstrapIconCSS.BI_CHAT_DOTS),
  @Deprecated (forRemoval = true, since = "12.3.0")
  CHAT_DOTS_FILL(CBootstrapIconCSS.BI_CHAT_DOTS_FILL),
  @Deprecated (forRemoval = true, since = "12.3.0")
  CHAT_FILL(CBootstrapIconCSS.BI_CHAT_FILL),
  @Deprecated (forRemoval = true, since = "12.3.0")
  CHAT_HEART(CBootstrapIconCSS.BI_CHAT_HEART),
  @Deprecated (forRemoval = true, since = "12.3.0")
  CHAT_HEART_FILL(CBootstrapIconCSS.BI_CHAT_HEART_FILL),
  @Deprecated (forRemoval = true, since = "12.3.0")
  CHAT_LEFT(CBootstrapIconCSS.BI_CHAT_LEFT),
  @Deprecated (forRemoval = true, since = "12.3.0")
  CHAT_LEFT_DOTS(CBootstrapIconCSS.BI_CHAT_LEFT_DOTS),
  @Deprecated (forRemoval = true, since = "12.3.0")
  CHAT_LEFT_DOTS_FILL(CBootstrapIconCSS.BI_CHAT_LEFT_DOTS_FILL),
  @Deprecated (forRemoval = true, since = "12.3.0")
  CHAT_LEFT_FILL(CBootstrapIconCSS.BI_CHAT_LEFT_FILL),
  @Deprecated (forRemoval = true, since = "12.3.0")
  CHAT_LEFT_HEART(CBootstrapIconCSS.BI_CHAT_LEFT_HEART),
  @Deprecated (forRemoval = true, since = "12.3.0")
  CHAT_LEFT_HEART_FILL(CBootstrapIconCSS.BI_CHAT_LEFT_HEART_FILL),
  @Deprecated (forRemoval = true, since = "12.3.0")
  CHAT_LEFT_QUOTE(CBootstrapIconCSS.BI_CHAT_LEFT_QUOTE),
  @Deprecated (forRemoval = true, since = "12.3.0")
  CHAT_LEFT_QUOTE_FILL(CBootstrapIconCSS.BI_CHAT_LEFT_QUOTE_FILL),
  @Deprecated (forRemoval = true, since = "12.3.0")
  CHAT_LEFT_TEXT(CBootstrapIconCSS.BI_CHAT_LEFT_TEXT),
  @Deprecated (forRemoval = true, since = "12.3.0")
  CHAT_LEFT_TEXT_FILL(CBootstrapIconCSS.BI_CHAT_LEFT_TEXT_FILL),
  @Deprecated (forRemoval = true, since = "12.3.0")
  CHAT_QUOTE(CBootstrapIconCSS.BI_CHAT_QUOTE),
  @Deprecated (forRemoval = true, since = "12.3.0")
  CHAT_QUOTE_FILL(CBootstrapIconCSS.BI_CHAT_QUOTE_FILL),
  @Deprecated (forRemoval = true, since = "12.3.0")
  CHAT_RIGHT(CBootstrapIconCSS.BI_CHAT_RIGHT),
  @Deprecated (forRemoval = true, since = "12.3.0")
  CHAT_RIGHT_DOTS(CBootstrapIconCSS.BI_CHAT_RIGHT_DOTS),
  @Deprecated (forRemoval = true, since = "12.3.0")
  CHAT_RIGHT_DOTS_FILL(CBootstrapIconCSS.BI_CHAT_RIGHT_DOTS_FILL),
  @Deprecated (forRemoval = true, since = "12.3.0")
  CHAT_RIGHT_FILL(CBootstrapIconCSS.BI_CHAT_RIGHT_FILL),
  @Deprecated (forRemoval = true, since = "12.3.0")
  CHAT_RIGHT_HEART(CBootstrapIconCSS.BI_CHAT_RIGHT_HEART),
  @Deprecated (forRemoval = true, since = "12.3.0")
  CHAT_RIGHT_HEART_FILL(CBootstrapIconCSS.BI_CHAT_RIGHT_HEART_FILL),
  @Deprecated (forRemoval = true, since = "12.3.0")
  CHAT_RIGHT_QUOTE(CBootstrapIconCSS.BI_CHAT_RIGHT_QUOTE),
  @Deprecated (forRemoval = true, since = "12.3.0")
  CHAT_RIGHT_QUOTE_FILL(CBootstrapIconCSS.BI_CHAT_RIGHT_QUOTE_FILL),
  @Deprecated (forRemoval = true, since = "12.3.0")
  CHAT_RIGHT_TEXT(CBootstrapIconCSS.BI_CHAT_RIGHT_TEXT),
  @Deprecated (forRemoval = true, since = "12.3.0")
  CHAT_RIGHT_TEXT_FILL(CBootstrapIconCSS.BI_CHAT_RIGHT_TEXT_FILL),
  @Deprecated (forRemoval = true, since = "12.3.0")
  CHAT_SQUARE(CBootstrapIconCSS.BI_CHAT_SQUARE),
  @Deprecated (forRemoval = true, since = "12.3.0")
  CHAT_SQUARE_DOTS(CBootstrapIconCSS.BI_CHAT_SQUARE_DOTS),
  @Deprecated (forRemoval = true, since = "12.3.0")
  CHAT_SQUARE_DOTS_FILL(CBootstrapIconCSS.BI_CHAT_SQUARE_DOTS_FILL),
  @Deprecated (forRemoval = true, since = "12.3.0")
  CHAT_SQUARE_FILL(CBootstrapIconCSS.BI_CHAT_SQUARE_FILL),
  @Deprecated (forRemoval = true, since = "12.3.0")
  CHAT_SQUARE_HEART(CBootstrapIconCSS.BI_CHAT_SQUARE_HEART),
  @Deprecated (forRemoval = true, since = "12.3.0")
  CHAT_SQUARE_HEART_FILL(CBootstrapIconCSS.BI_CHAT_SQUARE_HEART_FILL),
  @Deprecated (forRemoval = true, since = "12.3.0")
  CHAT_SQUARE_QUOTE(CBootstrapIconCSS.BI_CHAT_SQUARE_QUOTE),
  @Deprecated (forRemoval = true, since = "12.3.0")
  CHAT_SQUARE_QUOTE_FILL(CBootstrapIconCSS.BI_CHAT_SQUARE_QUOTE_FILL),
  @Deprecated (forRemoval = true, since = "12.3.0")
  CHAT_SQUARE_TEXT(CBootstrapIconCSS.BI_CHAT_SQUARE_TEXT),
  @Deprecated (forRemoval = true, since = "12.3.0")
  CHAT_SQUARE_TEXT_FILL(CBootstrapIconCSS.BI_CHAT_SQUARE_TEXT_FILL),
  @Deprecated (forRemoval = true, since = "12.3.0")
  CHAT_TEXT(CBootstrapIconCSS.BI_CHAT_TEXT),
  @Deprecated (forRemoval = true, since = "12.3.0")
  CHAT_TEXT_FILL(CBootstrapIconCSS.BI_CHAT_TEXT_FILL),
  @Deprecated (forRemoval = true, since = "12.3.0")
  CHECK(CBootstrapIconCSS.BI_CHECK),
  @Deprecated (forRemoval = true, since = "12.3.0")
  CHECK_ALL(CBootstrapIconCSS.BI_CHECK_ALL),
  @Deprecated (forRemoval = true, since = "12.3.0")
  CHECK_CIRCLE(CBootstrapIconCSS.BI_CHECK_CIRCLE),
  @Deprecated (forRemoval = true, since = "12.3.0")
  CHECK_CIRCLE_FILL(CBootstrapIconCSS.BI_CHECK_CIRCLE_FILL),
  @Deprecated (forRemoval = true, since = "12.3.0")
  CHECK_LG(CBootstrapIconCSS.BI_CHECK_LG),
  @Deprecated (forRemoval = true, since = "12.3.0")
  CHECK_SQUARE(CBootstrapIconCSS.BI_CHECK_SQUARE),
  @Deprecated (forRemoval = true, since = "12.3.0")
  CHECK_SQUARE_FILL(CBootstrapIconCSS.BI_CHECK_SQUARE_FILL),
  @Deprecated (forRemoval = true, since = "12.3.0")
  CHECK2(CBootstrapIconCSS.BI_CHECK2),
  @Deprecated (forRemoval = true, since = "12.3.0")
  CHECK2_ALL(CBootstrapIconCSS.BI_CHECK2_ALL),
  @Deprecated (forRemoval = true, since = "12.3.0")
  CHECK2_CIRCLE(CBootstrapIconCSS.BI_CHECK2_CIRCLE),
  @Deprecated (forRemoval = true, since = "12.3.0")
  CHECK2_SQUARE(CBootstrapIconCSS.BI_CHECK2_SQUARE),
  @Deprecated (forRemoval = true, since = "12.3.0")
  CHEVRON_BAR_CONTRACT(CBootstrapIconCSS.BI_CHEVRON_BAR_CONTRACT),
  @Deprecated (forRemoval = true, since = "12.3.0")
  CHEVRON_BAR_DOWN(CBootstrapIconCSS.BI_CHEVRON_BAR_DOWN),
  @Deprecated (forRemoval = true, since = "12.3.0")
  CHEVRON_BAR_EXPAND(CBootstrapIconCSS.BI_CHEVRON_BAR_EXPAND),
  @Deprecated (forRemoval = true, since = "12.3.0")
  CHEVRON_BAR_LEFT(CBootstrapIconCSS.BI_CHEVRON_BAR_LEFT),
  @Deprecated (forRemoval = true, since = "12.3.0")
  CHEVRON_BAR_RIGHT(CBootstrapIconCSS.BI_CHEVRON_BAR_RIGHT),
  @Deprecated (forRemoval = true, since = "12.3.0")
  CHEVRON_BAR_UP(CBootstrapIconCSS.BI_CHEVRON_BAR_UP),
  @Deprecated (forRemoval = true, since = "12.3.0")
  CHEVRON_COMPACT_DOWN(CBootstrapIconCSS.BI_CHEVRON_COMPACT_DOWN),
  @Deprecated (forRemoval = true, since = "12.3.0")
  CHEVRON_COMPACT_LEFT(CBootstrapIconCSS.BI_CHEVRON_COMPACT_LEFT),
  @Deprecated (forRemoval = true, since = "12.3.0")
  CHEVRON_COMPACT_RIGHT(CBootstrapIconCSS.BI_CHEVRON_COMPACT_RIGHT),
  @Deprecated (forRemoval = true, since = "12.3.0")
  CHEVRON_COMPACT_UP(CBootstrapIconCSS.BI_CHEVRON_COMPACT_UP),
  @Deprecated (forRemoval = true, since = "12.3.0")
  CHEVRON_CONTRACT(CBootstrapIconCSS.BI_CHEVRON_CONTRACT),
  @Deprecated (forRemoval = true, since = "12.3.0")
  CHEVRON_DOUBLE_DOWN(CBootstrapIconCSS.BI_CHEVRON_DOUBLE_DOWN),
  @Deprecated (forRemoval = true, since = "12.3.0")
  CHEVRON_DOUBLE_LEFT(CBootstrapIconCSS.BI_CHEVRON_DOUBLE_LEFT),
  @Deprecated (forRemoval = true, since = "12.3.0")
  CHEVRON_DOUBLE_RIGHT(CBootstrapIconCSS.BI_CHEVRON_DOUBLE_RIGHT),
  @Deprecated (forRemoval = true, since = "12.3.0")
  CHEVRON_DOUBLE_UP(CBootstrapIconCSS.BI_CHEVRON_DOUBLE_UP),
  @Deprecated (forRemoval = true, since = "12.3.0")
  CHEVRON_DOWN(CBootstrapIconCSS.BI_CHEVRON_DOWN),
  @Deprecated (forRemoval = true, since = "12.3.0")
  CHEVRON_EXPAND(CBootstrapIconCSS.BI_CHEVRON_EXPAND),
  @Deprecated (forRemoval = true, since = "12.3.0")
  CHEVRON_LEFT(CBootstrapIconCSS.BI_CHEVRON_LEFT),
  @Deprecated (forRemoval = true, since = "12.3.0")
  CHEVRON_RIGHT(CBootstrapIconCSS.BI_CHEVRON_RIGHT),
  @Deprecated (forRemoval = true, since = "12.3.0")
  CHEVRON_UP(CBootstrapIconCSS.BI_CHEVRON_UP),
  @Deprecated (forRemoval = true, since = "12.3.0")
  CIRCLE(CBootstrapIconCSS.BI_CIRCLE),
  @Deprecated (forRemoval = true, since = "12.3.0")
  CIRCLE_FILL(CBootstrapIconCSS.BI_CIRCLE_FILL),
  @Deprecated (forRemoval = true, since = "12.3.0")
  CIRCLE_HALF(CBootstrapIconCSS.BI_CIRCLE_HALF),
  @Deprecated (forRemoval = true, since = "12.3.0")
  CIRCLE_SQUARE(CBootstrapIconCSS.BI_CIRCLE_SQUARE),
  @Deprecated (forRemoval = true, since = "12.3.0")
  CLIPBOARD(CBootstrapIconCSS.BI_CLIPBOARD),
  @Deprecated (forRemoval = true, since = "12.3.0")
  CLIPBOARD_CHECK(CBootstrapIconCSS.BI_CLIPBOARD_CHECK),
  @Deprecated (forRemoval = true, since = "12.3.0")
  CLIPBOARD_CHECK_FILL(CBootstrapIconCSS.BI_CLIPBOARD_CHECK_FILL),
  @Deprecated (forRemoval = true, since = "12.3.0")
  CLIPBOARD_DATA(CBootstrapIconCSS.BI_CLIPBOARD_DATA),
  @Deprecated (forRemoval = true, since = "12.3.0")
  CLIPBOARD_DATA_FILL(CBootstrapIconCSS.BI_CLIPBOARD_DATA_FILL),
  @Deprecated (forRemoval = true, since = "12.3.0")
  CLIPBOARD_FILL(CBootstrapIconCSS.BI_CLIPBOARD_FILL),
  @Deprecated (forRemoval = true, since = "12.3.0")
  CLIPBOARD_HEART(CBootstrapIconCSS.BI_CLIPBOARD_HEART),
  @Deprecated (forRemoval = true, since = "12.3.0")
  CLIPBOARD_HEART_FILL(CBootstrapIconCSS.BI_CLIPBOARD_HEART_FILL),
  @Deprecated (forRemoval = true, since = "12.3.0")
  CLIPBOARD_MINUS(CBootstrapIconCSS.BI_CLIPBOARD_MINUS),
  @Deprecated (forRemoval = true, since = "12.3.0")
  CLIPBOARD_MINUS_FILL(CBootstrapIconCSS.BI_CLIPBOARD_MINUS_FILL),
  @Deprecated (forRemoval = true, since = "12.3.0")
  CLIPBOARD_PLUS(CBootstrapIconCSS.BI_CLIPBOARD_PLUS),
  @Deprecated (forRemoval = true, since = "12.3.0")
  CLIPBOARD_PLUS_FILL(CBootstrapIconCSS.BI_CLIPBOARD_PLUS_FILL),
  @Deprecated (forRemoval = true, since = "12.3.0")
  CLIPBOARD_PULSE(CBootstrapIconCSS.BI_CLIPBOARD_PULSE),
  @Deprecated (forRemoval = true, since = "12.3.0")
  CLIPBOARD_X(CBootstrapIconCSS.BI_CLIPBOARD_X),
  @Deprecated (forRemoval = true, since = "12.3.0")
  CLIPBOARD_X_FILL(CBootstrapIconCSS.BI_CLIPBOARD_X_FILL),
  @Deprecated (forRemoval = true, since = "12.3.0")
  CLIPBOARD2(CBootstrapIconCSS.BI_CLIPBOARD2),
  @Deprecated (forRemoval = true, since = "12.3.0")
  CLIPBOARD2_CHECK(CBootstrapIconCSS.BI_CLIPBOARD2_CHECK),
  @Deprecated (forRemoval = true, since = "12.3.0")
  CLIPBOARD2_CHECK_FILL(CBootstrapIconCSS.BI_CLIPBOARD2_CHECK_FILL),
  @Deprecated (forRemoval = true, since = "12.3.0")
  CLIPBOARD2_DATA(CBootstrapIconCSS.BI_CLIPBOARD2_DATA),
  @Deprecated (forRemoval = true, since = "12.3.0")
  CLIPBOARD2_DATA_FILL(CBootstrapIconCSS.BI_CLIPBOARD2_DATA_FILL),
  @Deprecated (forRemoval = true, since = "12.3.0")
  CLIPBOARD2_FILL(CBootstrapIconCSS.BI_CLIPBOARD2_FILL),
  @Deprecated (forRemoval = true, since = "12.3.0")
  CLIPBOARD2_HEART(CBootstrapIconCSS.BI_CLIPBOARD2_HEART),
  @Deprecated (forRemoval = true, since = "12.3.0")
  CLIPBOARD2_HEART_FILL(CBootstrapIconCSS.BI_CLIPBOARD2_HEART_FILL),
  @Deprecated (forRemoval = true, since = "12.3.0")
  CLIPBOARD2_MINUS(CBootstrapIconCSS.BI_CLIPBOARD2_MINUS),
  @Deprecated (forRemoval = true, since = "12.3.0")
  CLIPBOARD2_MINUS_FILL(CBootstrapIconCSS.BI_CLIPBOARD2_MINUS_FILL),
  @Deprecated (forRemoval = true, since = "12.3.0")
  CLIPBOARD2_PLUS(CBootstrapIconCSS.BI_CLIPBOARD2_PLUS),
  @Deprecated (forRemoval = true, since = "12.3.0")
  CLIPBOARD2_PLUS_FILL(CBootstrapIconCSS.BI_CLIPBOARD2_PLUS_FILL),
  @Deprecated (forRemoval = true, since = "12.3.0")
  CLIPBOARD2_PULSE(CBootstrapIconCSS.BI_CLIPBOARD2_PULSE),
  @Deprecated (forRemoval = true, since = "12.3.0")
  CLIPBOARD2_PULSE_FILL(CBootstrapIconCSS.BI_CLIPBOARD2_PULSE_FILL),
  @Deprecated (forRemoval = true, since = "12.3.0")
  CLIPBOARD2_X(CBootstrapIconCSS.BI_CLIPBOARD2_X),
  @Deprecated (forRemoval = true, since = "12.3.0")
  CLIPBOARD2_X_FILL(CBootstrapIconCSS.BI_CLIPBOARD2_X_FILL),
  @Deprecated (forRemoval = true, since = "12.3.0")
  CLOCK(CBootstrapIconCSS.BI_CLOCK),
  @Deprecated (forRemoval = true, since = "12.3.0")
  CLOCK_FILL(CBootstrapIconCSS.BI_CLOCK_FILL),
  @Deprecated (forRemoval = true, since = "12.3.0")
  CLOCK_HISTORY(CBootstrapIconCSS.BI_CLOCK_HISTORY),
  @Deprecated (forRemoval = true, since = "12.3.0")
  CLOUD(CBootstrapIconCSS.BI_CLOUD),
  @Deprecated (forRemoval = true, since = "12.3.0")
  CLOUD_ARROW_DOWN(CBootstrapIconCSS.BI_CLOUD_ARROW_DOWN),
  @Deprecated (forRemoval = true, since = "12.3.0")
  CLOUD_ARROW_DOWN_FILL(CBootstrapIconCSS.BI_CLOUD_ARROW_DOWN_FILL),
  @Deprecated (forRemoval = true, since = "12.3.0")
  CLOUD_ARROW_UP(CBootstrapIconCSS.BI_CLOUD_ARROW_UP),
  @Deprecated (forRemoval = true, since = "12.3.0")
  CLOUD_ARROW_UP_FILL(CBootstrapIconCSS.BI_CLOUD_ARROW_UP_FILL),
  @Deprecated (forRemoval = true, since = "12.3.0")
  CLOUD_CHECK(CBootstrapIconCSS.BI_CLOUD_CHECK),
  @Deprecated (forRemoval = true, since = "12.3.0")
  CLOUD_CHECK_FILL(CBootstrapIconCSS.BI_CLOUD_CHECK_FILL),
  @Deprecated (forRemoval = true, since = "12.3.0")
  CLOUD_DOWNLOAD(CBootstrapIconCSS.BI_CLOUD_DOWNLOAD),
  @Deprecated (forRemoval = true, since = "12.3.0")
  CLOUD_DOWNLOAD_FILL(CBootstrapIconCSS.BI_CLOUD_DOWNLOAD_FILL),
  @Deprecated (forRemoval = true, since = "12.3.0")
  CLOUD_DRIZZLE(CBootstrapIconCSS.BI_CLOUD_DRIZZLE),
  @Deprecated (forRemoval = true, since = "12.3.0")
  CLOUD_DRIZZLE_FILL(CBootstrapIconCSS.BI_CLOUD_DRIZZLE_FILL),
  @Deprecated (forRemoval = true, since = "12.3.0")
  CLOUD_FILL(CBootstrapIconCSS.BI_CLOUD_FILL),
  @Deprecated (forRemoval = true, since = "12.3.0")
  CLOUD_FOG(CBootstrapIconCSS.BI_CLOUD_FOG),
  @Deprecated (forRemoval = true, since = "12.3.0")
  CLOUD_FOG_FILL(CBootstrapIconCSS.BI_CLOUD_FOG_FILL),
  @Deprecated (forRemoval = true, since = "12.3.0")
  CLOUD_FOG2(CBootstrapIconCSS.BI_CLOUD_FOG2),
  @Deprecated (forRemoval = true, since = "12.3.0")
  CLOUD_FOG2_FILL(CBootstrapIconCSS.BI_CLOUD_FOG2_FILL),
  @Deprecated (forRemoval = true, since = "12.3.0")
  CLOUD_HAIL(CBootstrapIconCSS.BI_CLOUD_HAIL),
  @Deprecated (forRemoval = true, since = "12.3.0")
  CLOUD_HAIL_FILL(CBootstrapIconCSS.BI_CLOUD_HAIL_FILL),
  @Deprecated (forRemoval = true, since = "12.3.0")
  CLOUD_HAZE(CBootstrapIconCSS.BI_CLOUD_HAZE),
  @Deprecated (forRemoval = true, since = "12.3.0")
  CLOUD_HAZE_FILL(CBootstrapIconCSS.BI_CLOUD_HAZE_FILL),
  @Deprecated (forRemoval = true, since = "12.3.0")
  CLOUD_HAZE2(CBootstrapIconCSS.BI_CLOUD_HAZE2),
  @Deprecated (forRemoval = true, since = "12.3.0")
  CLOUD_HAZE2_FILL(CBootstrapIconCSS.BI_CLOUD_HAZE2_FILL),
  @Deprecated (forRemoval = true, since = "12.3.0")
  CLOUD_LIGHTNING(CBootstrapIconCSS.BI_CLOUD_LIGHTNING),
  @Deprecated (forRemoval = true, since = "12.3.0")
  CLOUD_LIGHTNING_FILL(CBootstrapIconCSS.BI_CLOUD_LIGHTNING_FILL),
  @Deprecated (forRemoval = true, since = "12.3.0")
  CLOUD_LIGHTNING_RAIN(CBootstrapIconCSS.BI_CLOUD_LIGHTNING_RAIN),
  @Deprecated (forRemoval = true, since = "12.3.0")
  CLOUD_LIGHTNING_RAIN_FILL(CBootstrapIconCSS.BI_CLOUD_LIGHTNING_RAIN_FILL),
  @Deprecated (forRemoval = true, since = "12.3.0")
  CLOUD_MINUS(CBootstrapIconCSS.BI_CLOUD_MINUS),
  @Deprecated (forRemoval = true, since = "12.3.0")
  CLOUD_MINUS_FILL(CBootstrapIconCSS.BI_CLOUD_MINUS_FILL),
  @Deprecated (forRemoval = true, since = "12.3.0")
  CLOUD_MOON(CBootstrapIconCSS.BI_CLOUD_MOON),
  @Deprecated (forRemoval = true, since = "12.3.0")
  CLOUD_MOON_FILL(CBootstrapIconCSS.BI_CLOUD_MOON_FILL),
  @Deprecated (forRemoval = true, since = "12.3.0")
  CLOUD_PLUS(CBootstrapIconCSS.BI_CLOUD_PLUS),
  @Deprecated (forRemoval = true, since = "12.3.0")
  CLOUD_PLUS_FILL(CBootstrapIconCSS.BI_CLOUD_PLUS_FILL),
  @Deprecated (forRemoval = true, since = "12.3.0")
  CLOUD_RAIN(CBootstrapIconCSS.BI_CLOUD_RAIN),
  @Deprecated (forRemoval = true, since = "12.3.0")
  CLOUD_RAIN_FILL(CBootstrapIconCSS.BI_CLOUD_RAIN_FILL),
  @Deprecated (forRemoval = true, since = "12.3.0")
  CLOUD_RAIN_HEAVY(CBootstrapIconCSS.BI_CLOUD_RAIN_HEAVY),
  @Deprecated (forRemoval = true, since = "12.3.0")
  CLOUD_RAIN_HEAVY_FILL(CBootstrapIconCSS.BI_CLOUD_RAIN_HEAVY_FILL),
  @Deprecated (forRemoval = true, since = "12.3.0")
  CLOUD_SLASH(CBootstrapIconCSS.BI_CLOUD_SLASH),
  @Deprecated (forRemoval = true, since = "12.3.0")
  CLOUD_SLASH_FILL(CBootstrapIconCSS.BI_CLOUD_SLASH_FILL),
  @Deprecated (forRemoval = true, since = "12.3.0")
  CLOUD_SLEET(CBootstrapIconCSS.BI_CLOUD_SLEET),
  @Deprecated (forRemoval = true, since = "12.3.0")
  CLOUD_SLEET_FILL(CBootstrapIconCSS.BI_CLOUD_SLEET_FILL),
  @Deprecated (forRemoval = true, since = "12.3.0")
  CLOUD_SNOW(CBootstrapIconCSS.BI_CLOUD_SNOW),
  @Deprecated (forRemoval = true, since = "12.3.0")
  CLOUD_SNOW_FILL(CBootstrapIconCSS.BI_CLOUD_SNOW_FILL),
  @Deprecated (forRemoval = true, since = "12.3.0")
  CLOUD_SUN(CBootstrapIconCSS.BI_CLOUD_SUN),
  @Deprecated (forRemoval = true, since = "12.3.0")
  CLOUD_SUN_FILL(CBootstrapIconCSS.BI_CLOUD_SUN_FILL),
  @Deprecated (forRemoval = true, since = "12.3.0")
  CLOUD_UPLOAD(CBootstrapIconCSS.BI_CLOUD_UPLOAD),
  @Deprecated (forRemoval = true, since = "12.3.0")
  CLOUD_UPLOAD_FILL(CBootstrapIconCSS.BI_CLOUD_UPLOAD_FILL),
  @Deprecated (forRemoval = true, since = "12.3.0")
  CLOUDS(CBootstrapIconCSS.BI_CLOUDS),
  @Deprecated (forRemoval = true, since = "12.3.0")
  CLOUDS_FILL(CBootstrapIconCSS.BI_CLOUDS_FILL),
  @Deprecated (forRemoval = true, since = "12.3.0")
  CLOUDY(CBootstrapIconCSS.BI_CLOUDY),
  @Deprecated (forRemoval = true, since = "12.3.0")
  CLOUDY_FILL(CBootstrapIconCSS.BI_CLOUDY_FILL),
  @Deprecated (forRemoval = true, since = "12.3.0")
  CODE(CBootstrapIconCSS.BI_CODE),
  @Deprecated (forRemoval = true, since = "12.3.0")
  CODE_SLASH(CBootstrapIconCSS.BI_CODE_SLASH),
  @Deprecated (forRemoval = true, since = "12.3.0")
  CODE_SQUARE(CBootstrapIconCSS.BI_CODE_SQUARE),
  @Deprecated (forRemoval = true, since = "12.3.0")
  COIN(CBootstrapIconCSS.BI_COIN),
  @Deprecated (forRemoval = true, since = "12.3.0")
  COLLECTION(CBootstrapIconCSS.BI_COLLECTION),
  @Deprecated (forRemoval = true, since = "12.3.0")
  COLLECTION_FILL(CBootstrapIconCSS.BI_COLLECTION_FILL),
  @Deprecated (forRemoval = true, since = "12.3.0")
  COLLECTION_PLAY(CBootstrapIconCSS.BI_COLLECTION_PLAY),
  @Deprecated (forRemoval = true, since = "12.3.0")
  COLLECTION_PLAY_FILL(CBootstrapIconCSS.BI_COLLECTION_PLAY_FILL),
  @Deprecated (forRemoval = true, since = "12.3.0")
  COLUMNS(CBootstrapIconCSS.BI_COLUMNS),
  @Deprecated (forRemoval = true, since = "12.3.0")
  COLUMNS_GAP(CBootstrapIconCSS.BI_COLUMNS_GAP),
  @Deprecated (forRemoval = true, since = "12.3.0")
  COMMAND(CBootstrapIconCSS.BI_COMMAND),
  @Deprecated (forRemoval = true, since = "12.3.0")
  COMPASS(CBootstrapIconCSS.BI_COMPASS),
  @Deprecated (forRemoval = true, since = "12.3.0")
  COMPASS_FILL(CBootstrapIconCSS.BI_COMPASS_FILL),
  @Deprecated (forRemoval = true, since = "12.3.0")
  CONE(CBootstrapIconCSS.BI_CONE),
  @Deprecated (forRemoval = true, since = "12.3.0")
  CONE_STRIPED(CBootstrapIconCSS.BI_CONE_STRIPED),
  @Deprecated (forRemoval = true, since = "12.3.0")
  CONTROLLER(CBootstrapIconCSS.BI_CONTROLLER),
  @Deprecated (forRemoval = true, since = "12.3.0")
  COOKIE(CBootstrapIconCSS.BI_COOKIE),
  @Deprecated (forRemoval = true, since = "12.3.0")
  COPY(CBootstrapIconCSS.BI_COPY),
  @Deprecated (forRemoval = true, since = "12.3.0")
  CPU(CBootstrapIconCSS.BI_CPU),
  @Deprecated (forRemoval = true, since = "12.3.0")
  CPU_FILL(CBootstrapIconCSS.BI_CPU_FILL),
  @Deprecated (forRemoval = true, since = "12.3.0")
  CREDIT_CARD(CBootstrapIconCSS.BI_CREDIT_CARD),
  @Deprecated (forRemoval = true, since = "12.3.0")
  CREDIT_CARD_2_BACK(CBootstrapIconCSS.BI_CREDIT_CARD_2_BACK),
  @Deprecated (forRemoval = true, since = "12.3.0")
  CREDIT_CARD_2_BACK_FILL(CBootstrapIconCSS.BI_CREDIT_CARD_2_BACK_FILL),
  @Deprecated (forRemoval = true, since = "12.3.0")
  CREDIT_CARD_2_FRONT(CBootstrapIconCSS.BI_CREDIT_CARD_2_FRONT),
  @Deprecated (forRemoval = true, since = "12.3.0")
  CREDIT_CARD_2_FRONT_FILL(CBootstrapIconCSS.BI_CREDIT_CARD_2_FRONT_FILL),
  @Deprecated (forRemoval = true, since = "12.3.0")
  CREDIT_CARD_FILL(CBootstrapIconCSS.BI_CREDIT_CARD_FILL),
  @Deprecated (forRemoval = true, since = "12.3.0")
  CROP(CBootstrapIconCSS.BI_CROP),
  @Deprecated (forRemoval = true, since = "12.3.0")
  CROSSHAIR(CBootstrapIconCSS.BI_CROSSHAIR),
  @Deprecated (forRemoval = true, since = "12.3.0")
  CROSSHAIR2(CBootstrapIconCSS.BI_CROSSHAIR2),
  @Deprecated (forRemoval = true, since = "12.3.0")
  CUP(CBootstrapIconCSS.BI_CUP),
  @Deprecated (forRemoval = true, since = "12.3.0")
  CUP_FILL(CBootstrapIconCSS.BI_CUP_FILL),
  @Deprecated (forRemoval = true, since = "12.3.0")
  CUP_HOT(CBootstrapIconCSS.BI_CUP_HOT),
  @Deprecated (forRemoval = true, since = "12.3.0")
  CUP_HOT_FILL(CBootstrapIconCSS.BI_CUP_HOT_FILL),
  @Deprecated (forRemoval = true, since = "12.3.0")
  CUP_STRAW(CBootstrapIconCSS.BI_CUP_STRAW),
  @Deprecated (forRemoval = true, since = "12.3.0")
  CURRENCY_BITCOIN(CBootstrapIconCSS.BI_CURRENCY_BITCOIN),
  @Deprecated (forRemoval = true, since = "12.3.0")
  CURRENCY_DOLLAR(CBootstrapIconCSS.BI_CURRENCY_DOLLAR),
  @Deprecated (forRemoval = true, since = "12.3.0")
  CURRENCY_EURO(CBootstrapIconCSS.BI_CURRENCY_EURO),
  @Deprecated (forRemoval = true, since = "12.3.0")
  CURRENCY_EXCHANGE(CBootstrapIconCSS.BI_CURRENCY_EXCHANGE),
  @Deprecated (forRemoval = true, since = "12.3.0")
  CURRENCY_POUND(CBootstrapIconCSS.BI_CURRENCY_POUND),
  @Deprecated (forRemoval = true, since = "12.3.0")
  CURRENCY_RUPEE(CBootstrapIconCSS.BI_CURRENCY_RUPEE),
  @Deprecated (forRemoval = true, since = "12.3.0")
  CURRENCY_YEN(CBootstrapIconCSS.BI_CURRENCY_YEN),
  @Deprecated (forRemoval = true, since = "12.3.0")
  CURSOR(CBootstrapIconCSS.BI_CURSOR),
  @Deprecated (forRemoval = true, since = "12.3.0")
  CURSOR_FILL(CBootstrapIconCSS.BI_CURSOR_FILL),
  @Deprecated (forRemoval = true, since = "12.3.0")
  CURSOR_TEXT(CBootstrapIconCSS.BI_CURSOR_TEXT),
  @Deprecated (forRemoval = true, since = "12.3.0")
  DASH(CBootstrapIconCSS.BI_DASH),
  @Deprecated (forRemoval = true, since = "12.3.0")
  DASH_CIRCLE(CBootstrapIconCSS.BI_DASH_CIRCLE),
  @Deprecated (forRemoval = true, since = "12.3.0")
  DASH_CIRCLE_DOTTED(CBootstrapIconCSS.BI_DASH_CIRCLE_DOTTED),
  @Deprecated (forRemoval = true, since = "12.3.0")
  DASH_CIRCLE_FILL(CBootstrapIconCSS.BI_DASH_CIRCLE_FILL),
  @Deprecated (forRemoval = true, since = "12.3.0")
  DASH_LG(CBootstrapIconCSS.BI_DASH_LG),
  @Deprecated (forRemoval = true, since = "12.3.0")
  DASH_SQUARE(CBootstrapIconCSS.BI_DASH_SQUARE),
  @Deprecated (forRemoval = true, since = "12.3.0")
  DASH_SQUARE_DOTTED(CBootstrapIconCSS.BI_DASH_SQUARE_DOTTED),
  @Deprecated (forRemoval = true, since = "12.3.0")
  DASH_SQUARE_FILL(CBootstrapIconCSS.BI_DASH_SQUARE_FILL),
  @Deprecated (forRemoval = true, since = "12.3.0")
  DATABASE(CBootstrapIconCSS.BI_DATABASE),
  @Deprecated (forRemoval = true, since = "12.3.0")
  DATABASE_ADD(CBootstrapIconCSS.BI_DATABASE_ADD),
  @Deprecated (forRemoval = true, since = "12.3.0")
  DATABASE_CHECK(CBootstrapIconCSS.BI_DATABASE_CHECK),
  @Deprecated (forRemoval = true, since = "12.3.0")
  DATABASE_DASH(CBootstrapIconCSS.BI_DATABASE_DASH),
  @Deprecated (forRemoval = true, since = "12.3.0")
  DATABASE_DOWN(CBootstrapIconCSS.BI_DATABASE_DOWN),
  @Deprecated (forRemoval = true, since = "12.3.0")
  DATABASE_EXCLAMATION(CBootstrapIconCSS.BI_DATABASE_EXCLAMATION),
  @Deprecated (forRemoval = true, since = "12.3.0")
  DATABASE_FILL(CBootstrapIconCSS.BI_DATABASE_FILL),
  @Deprecated (forRemoval = true, since = "12.3.0")
  DATABASE_FILL_ADD(CBootstrapIconCSS.BI_DATABASE_FILL_ADD),
  @Deprecated (forRemoval = true, since = "12.3.0")
  DATABASE_FILL_CHECK(CBootstrapIconCSS.BI_DATABASE_FILL_CHECK),
  @Deprecated (forRemoval = true, since = "12.3.0")
  DATABASE_FILL_DASH(CBootstrapIconCSS.BI_DATABASE_FILL_DASH),
  @Deprecated (forRemoval = true, since = "12.3.0")
  DATABASE_FILL_DOWN(CBootstrapIconCSS.BI_DATABASE_FILL_DOWN),
  @Deprecated (forRemoval = true, since = "12.3.0")
  DATABASE_FILL_EXCLAMATION(CBootstrapIconCSS.BI_DATABASE_FILL_EXCLAMATION),
  @Deprecated (forRemoval = true, since = "12.3.0")
  DATABASE_FILL_GEAR(CBootstrapIconCSS.BI_DATABASE_FILL_GEAR),
  @Deprecated (forRemoval = true, since = "12.3.0")
  DATABASE_FILL_LOCK(CBootstrapIconCSS.BI_DATABASE_FILL_LOCK),
  @Deprecated (forRemoval = true, since = "12.3.0")
  DATABASE_FILL_SLASH(CBootstrapIconCSS.BI_DATABASE_FILL_SLASH),
  @Deprecated (forRemoval = true, since = "12.3.0")
  DATABASE_FILL_UP(CBootstrapIconCSS.BI_DATABASE_FILL_UP),
  @Deprecated (forRemoval = true, since = "12.3.0")
  DATABASE_FILL_X(CBootstrapIconCSS.BI_DATABASE_FILL_X),
  @Deprecated (forRemoval = true, since = "12.3.0")
  DATABASE_GEAR(CBootstrapIconCSS.BI_DATABASE_GEAR),
  @Deprecated (forRemoval = true, since = "12.3.0")
  DATABASE_LOCK(CBootstrapIconCSS.BI_DATABASE_LOCK),
  @Deprecated (forRemoval = true, since = "12.3.0")
  DATABASE_SLASH(CBootstrapIconCSS.BI_DATABASE_SLASH),
  @Deprecated (forRemoval = true, since = "12.3.0")
  DATABASE_UP(CBootstrapIconCSS.BI_DATABASE_UP),
  @Deprecated (forRemoval = true, since = "12.3.0")
  DATABASE_X(CBootstrapIconCSS.BI_DATABASE_X),
  @Deprecated (forRemoval = true, since = "12.3.0")
  DEVICE_HDD(CBootstrapIconCSS.BI_DEVICE_HDD),
  @Deprecated (forRemoval = true, since = "12.3.0")
  DEVICE_HDD_FILL(CBootstrapIconCSS.BI_DEVICE_HDD_FILL),
  @Deprecated (forRemoval = true, since = "12.3.0")
  DEVICE_SSD(CBootstrapIconCSS.BI_DEVICE_SSD),
  @Deprecated (forRemoval = true, since = "12.3.0")
  DEVICE_SSD_FILL(CBootstrapIconCSS.BI_DEVICE_SSD_FILL),
  @Deprecated (forRemoval = true, since = "12.3.0")
  DIAGRAM_2(CBootstrapIconCSS.BI_DIAGRAM_2),
  @Deprecated (forRemoval = true, since = "12.3.0")
  DIAGRAM_2_FILL(CBootstrapIconCSS.BI_DIAGRAM_2_FILL),
  @Deprecated (forRemoval = true, since = "12.3.0")
  DIAGRAM_3(CBootstrapIconCSS.BI_DIAGRAM_3),
  @Deprecated (forRemoval = true, since = "12.3.0")
  DIAGRAM_3_FILL(CBootstrapIconCSS.BI_DIAGRAM_3_FILL),
  @Deprecated (forRemoval = true, since = "12.3.0")
  DIAMOND(CBootstrapIconCSS.BI_DIAMOND),
  @Deprecated (forRemoval = true, since = "12.3.0")
  DIAMOND_FILL(CBootstrapIconCSS.BI_DIAMOND_FILL),
  @Deprecated (forRemoval = true, since = "12.3.0")
  DIAMOND_HALF(CBootstrapIconCSS.BI_DIAMOND_HALF),
  @Deprecated (forRemoval = true, since = "12.3.0")
  DICE_1(CBootstrapIconCSS.BI_DICE_1),
  @Deprecated (forRemoval = true, since = "12.3.0")
  DICE_1_FILL(CBootstrapIconCSS.BI_DICE_1_FILL),
  @Deprecated (forRemoval = true, since = "12.3.0")
  DICE_2(CBootstrapIconCSS.BI_DICE_2),
  @Deprecated (forRemoval = true, since = "12.3.0")
  DICE_2_FILL(CBootstrapIconCSS.BI_DICE_2_FILL),
  @Deprecated (forRemoval = true, since = "12.3.0")
  DICE_3(CBootstrapIconCSS.BI_DICE_3),
  @Deprecated (forRemoval = true, since = "12.3.0")
  DICE_3_FILL(CBootstrapIconCSS.BI_DICE_3_FILL),
  @Deprecated (forRemoval = true, since = "12.3.0")
  DICE_4(CBootstrapIconCSS.BI_DICE_4),
  @Deprecated (forRemoval = true, since = "12.3.0")
  DICE_4_FILL(CBootstrapIconCSS.BI_DICE_4_FILL),
  @Deprecated (forRemoval = true, since = "12.3.0")
  DICE_5(CBootstrapIconCSS.BI_DICE_5),
  @Deprecated (forRemoval = true, since = "12.3.0")
  DICE_5_FILL(CBootstrapIconCSS.BI_DICE_5_FILL),
  @Deprecated (forRemoval = true, since = "12.3.0")
  DICE_6(CBootstrapIconCSS.BI_DICE_6),
  @Deprecated (forRemoval = true, since = "12.3.0")
  DICE_6_FILL(CBootstrapIconCSS.BI_DICE_6_FILL),
  @Deprecated (forRemoval = true, since = "12.3.0")
  DISC(CBootstrapIconCSS.BI_DISC),
  @Deprecated (forRemoval = true, since = "12.3.0")
  DISC_FILL(CBootstrapIconCSS.BI_DISC_FILL),
  @Deprecated (forRemoval = true, since = "12.3.0")
  DISCORD(CBootstrapIconCSS.BI_DISCORD),
  @Deprecated (forRemoval = true, since = "12.3.0")
  DISPLAY(CBootstrapIconCSS.BI_DISPLAY),
  @Deprecated (forRemoval = true, since = "12.3.0")
  DISPLAY_FILL(CBootstrapIconCSS.BI_DISPLAY_FILL),
  @Deprecated (forRemoval = true, since = "12.3.0")
  DISPLAYPORT(CBootstrapIconCSS.BI_DISPLAYPORT),
  @Deprecated (forRemoval = true, since = "12.3.0")
  DISPLAYPORT_FILL(CBootstrapIconCSS.BI_DISPLAYPORT_FILL),
  @Deprecated (forRemoval = true, since = "12.3.0")
  DISTRIBUTE_HORIZONTAL(CBootstrapIconCSS.BI_DISTRIBUTE_HORIZONTAL),
  @Deprecated (forRemoval = true, since = "12.3.0")
  DISTRIBUTE_VERTICAL(CBootstrapIconCSS.BI_DISTRIBUTE_VERTICAL),
  @Deprecated (forRemoval = true, since = "12.3.0")
  DOOR_CLOSED(CBootstrapIconCSS.BI_DOOR_CLOSED),
  @Deprecated (forRemoval = true, since = "12.3.0")
  DOOR_CLOSED_FILL(CBootstrapIconCSS.BI_DOOR_CLOSED_FILL),
  @Deprecated (forRemoval = true, since = "12.3.0")
  DOOR_OPEN(CBootstrapIconCSS.BI_DOOR_OPEN),
  @Deprecated (forRemoval = true, since = "12.3.0")
  DOOR_OPEN_FILL(CBootstrapIconCSS.BI_DOOR_OPEN_FILL),
  @Deprecated (forRemoval = true, since = "12.3.0")
  DOT(CBootstrapIconCSS.BI_DOT),
  @Deprecated (forRemoval = true, since = "12.3.0")
  DOWNLOAD(CBootstrapIconCSS.BI_DOWNLOAD),
  @Deprecated (forRemoval = true, since = "12.3.0")
  DPAD(CBootstrapIconCSS.BI_DPAD),
  @Deprecated (forRemoval = true, since = "12.3.0")
  DPAD_FILL(CBootstrapIconCSS.BI_DPAD_FILL),
  @Deprecated (forRemoval = true, since = "12.3.0")
  DRIBBBLE(CBootstrapIconCSS.BI_DRIBBBLE),
  @Deprecated (forRemoval = true, since = "12.3.0")
  DROPBOX(CBootstrapIconCSS.BI_DROPBOX),
  @Deprecated (forRemoval = true, since = "12.3.0")
  DROPLET(CBootstrapIconCSS.BI_DROPLET),
  @Deprecated (forRemoval = true, since = "12.3.0")
  DROPLET_FILL(CBootstrapIconCSS.BI_DROPLET_FILL),
  @Deprecated (forRemoval = true, since = "12.3.0")
  DROPLET_HALF(CBootstrapIconCSS.BI_DROPLET_HALF),
  @Deprecated (forRemoval = true, since = "12.3.0")
  DUFFLE(CBootstrapIconCSS.BI_DUFFLE),
  @Deprecated (forRemoval = true, since = "12.3.0")
  DUFFLE_FILL(CBootstrapIconCSS.BI_DUFFLE_FILL),
  @Deprecated (forRemoval = true, since = "12.3.0")
  EAR(CBootstrapIconCSS.BI_EAR),
  @Deprecated (forRemoval = true, since = "12.3.0")
  EAR_FILL(CBootstrapIconCSS.BI_EAR_FILL),
  @Deprecated (forRemoval = true, since = "12.3.0")
  EARBUDS(CBootstrapIconCSS.BI_EARBUDS),
  @Deprecated (forRemoval = true, since = "12.3.0")
  EASEL(CBootstrapIconCSS.BI_EASEL),
  @Deprecated (forRemoval = true, since = "12.3.0")
  EASEL_FILL(CBootstrapIconCSS.BI_EASEL_FILL),
  @Deprecated (forRemoval = true, since = "12.3.0")
  EASEL2(CBootstrapIconCSS.BI_EASEL2),
  @Deprecated (forRemoval = true, since = "12.3.0")
  EASEL2_FILL(CBootstrapIconCSS.BI_EASEL2_FILL),
  @Deprecated (forRemoval = true, since = "12.3.0")
  EASEL3(CBootstrapIconCSS.BI_EASEL3),
  @Deprecated (forRemoval = true, since = "12.3.0")
  EASEL3_FILL(CBootstrapIconCSS.BI_EASEL3_FILL),
  @Deprecated (forRemoval = true, since = "12.3.0")
  EGG(CBootstrapIconCSS.BI_EGG),
  @Deprecated (forRemoval = true, since = "12.3.0")
  EGG_FILL(CBootstrapIconCSS.BI_EGG_FILL),
  @Deprecated (forRemoval = true, since = "12.3.0")
  EGG_FRIED(CBootstrapIconCSS.BI_EGG_FRIED),
  @Deprecated (forRemoval = true, since = "12.3.0")
  EJECT(CBootstrapIconCSS.BI_EJECT),
  @Deprecated (forRemoval = true, since = "12.3.0")
  EJECT_FILL(CBootstrapIconCSS.BI_EJECT_FILL),
  @Deprecated (forRemoval = true, since = "12.3.0")
  EMOJI_ANGRY(CBootstrapIconCSS.BI_EMOJI_ANGRY),
  @Deprecated (forRemoval = true, since = "12.3.0")
  EMOJI_ANGRY_FILL(CBootstrapIconCSS.BI_EMOJI_ANGRY_FILL),
  @Deprecated (forRemoval = true, since = "12.3.0")
  EMOJI_ASTONISHED(CBootstrapIconCSS.BI_EMOJI_ASTONISHED),
  @Deprecated (forRemoval = true, since = "12.3.0")
  EMOJI_ASTONISHED_FILL(CBootstrapIconCSS.BI_EMOJI_ASTONISHED_FILL),
  @Deprecated (forRemoval = true, since = "12.3.0")
  EMOJI_DIZZY(CBootstrapIconCSS.BI_EMOJI_DIZZY),
  @Deprecated (forRemoval = true, since = "12.3.0")
  EMOJI_DIZZY_FILL(CBootstrapIconCSS.BI_EMOJI_DIZZY_FILL),
  @Deprecated (forRemoval = true, since = "12.3.0")
  EMOJI_EXPRESSIONLESS(CBootstrapIconCSS.BI_EMOJI_EXPRESSIONLESS),
  @Deprecated (forRemoval = true, since = "12.3.0")
  EMOJI_EXPRESSIONLESS_FILL(CBootstrapIconCSS.BI_EMOJI_EXPRESSIONLESS_FILL),
  @Deprecated (forRemoval = true, since = "12.3.0")
  EMOJI_FROWN(CBootstrapIconCSS.BI_EMOJI_FROWN),
  @Deprecated (forRemoval = true, since = "12.3.0")
  EMOJI_FROWN_FILL(CBootstrapIconCSS.BI_EMOJI_FROWN_FILL),
  @Deprecated (forRemoval = true, since = "12.3.0")
  EMOJI_GRIMACE(CBootstrapIconCSS.BI_EMOJI_GRIMACE),
  @Deprecated (forRemoval = true, since = "12.3.0")
  EMOJI_GRIMACE_FILL(CBootstrapIconCSS.BI_EMOJI_GRIMACE_FILL),
  @Deprecated (forRemoval = true, since = "12.3.0")
  EMOJI_GRIN(CBootstrapIconCSS.BI_EMOJI_GRIN),
  @Deprecated (forRemoval = true, since = "12.3.0")
  EMOJI_GRIN_FILL(CBootstrapIconCSS.BI_EMOJI_GRIN_FILL),
  @Deprecated (forRemoval = true, since = "12.3.0")
  EMOJI_HEART_EYES(CBootstrapIconCSS.BI_EMOJI_HEART_EYES),
  @Deprecated (forRemoval = true, since = "12.3.0")
  EMOJI_HEART_EYES_FILL(CBootstrapIconCSS.BI_EMOJI_HEART_EYES_FILL),
  @Deprecated (forRemoval = true, since = "12.3.0")
  EMOJI_KISS(CBootstrapIconCSS.BI_EMOJI_KISS),
  @Deprecated (forRemoval = true, since = "12.3.0")
  EMOJI_KISS_FILL(CBootstrapIconCSS.BI_EMOJI_KISS_FILL),
  @Deprecated (forRemoval = true, since = "12.3.0")
  EMOJI_LAUGHING(CBootstrapIconCSS.BI_EMOJI_LAUGHING),
  @Deprecated (forRemoval = true, since = "12.3.0")
  EMOJI_LAUGHING_FILL(CBootstrapIconCSS.BI_EMOJI_LAUGHING_FILL),
  @Deprecated (forRemoval = true, since = "12.3.0")
  EMOJI_NEUTRAL(CBootstrapIconCSS.BI_EMOJI_NEUTRAL),
  @Deprecated (forRemoval = true, since = "12.3.0")
  EMOJI_NEUTRAL_FILL(CBootstrapIconCSS.BI_EMOJI_NEUTRAL_FILL),
  @Deprecated (forRemoval = true, since = "12.3.0")
  EMOJI_SMILE(CBootstrapIconCSS.BI_EMOJI_SMILE),
  @Deprecated (forRemoval = true, since = "12.3.0")
  EMOJI_SMILE_FILL(CBootstrapIconCSS.BI_EMOJI_SMILE_FILL),
  @Deprecated (forRemoval = true, since = "12.3.0")
  EMOJI_SMILE_UPSIDE_DOWN(CBootstrapIconCSS.BI_EMOJI_SMILE_UPSIDE_DOWN),
  @Deprecated (forRemoval = true, since = "12.3.0")
  EMOJI_SMILE_UPSIDE_DOWN_FILL(CBootstrapIconCSS.BI_EMOJI_SMILE_UPSIDE_DOWN_FILL),
  @Deprecated (forRemoval = true, since = "12.3.0")
  EMOJI_SUNGLASSES(CBootstrapIconCSS.BI_EMOJI_SUNGLASSES),
  @Deprecated (forRemoval = true, since = "12.3.0")
  EMOJI_SUNGLASSES_FILL(CBootstrapIconCSS.BI_EMOJI_SUNGLASSES_FILL),
  @Deprecated (forRemoval = true, since = "12.3.0")
  EMOJI_SURPRISE(CBootstrapIconCSS.BI_EMOJI_SURPRISE),
  @Deprecated (forRemoval = true, since = "12.3.0")
  EMOJI_SURPRISE_FILL(CBootstrapIconCSS.BI_EMOJI_SURPRISE_FILL),
  @Deprecated (forRemoval = true, since = "12.3.0")
  EMOJI_TEAR(CBootstrapIconCSS.BI_EMOJI_TEAR),
  @Deprecated (forRemoval = true, since = "12.3.0")
  EMOJI_TEAR_FILL(CBootstrapIconCSS.BI_EMOJI_TEAR_FILL),
  @Deprecated (forRemoval = true, since = "12.3.0")
  EMOJI_WINK(CBootstrapIconCSS.BI_EMOJI_WINK),
  @Deprecated (forRemoval = true, since = "12.3.0")
  EMOJI_WINK_FILL(CBootstrapIconCSS.BI_EMOJI_WINK_FILL),
  @Deprecated (forRemoval = true, since = "12.3.0")
  ENVELOPE(CBootstrapIconCSS.BI_ENVELOPE),
  @Deprecated (forRemoval = true, since = "12.3.0")
  ENVELOPE_ARROW_DOWN(CBootstrapIconCSS.BI_ENVELOPE_ARROW_DOWN),
  @Deprecated (forRemoval = true, since = "12.3.0")
  ENVELOPE_ARROW_DOWN_FILL(CBootstrapIconCSS.BI_ENVELOPE_ARROW_DOWN_FILL),
  @Deprecated (forRemoval = true, since = "12.3.0")
  ENVELOPE_ARROW_UP(CBootstrapIconCSS.BI_ENVELOPE_ARROW_UP),
  @Deprecated (forRemoval = true, since = "12.3.0")
  ENVELOPE_ARROW_UP_FILL(CBootstrapIconCSS.BI_ENVELOPE_ARROW_UP_FILL),
  @Deprecated (forRemoval = true, since = "12.3.0")
  ENVELOPE_AT(CBootstrapIconCSS.BI_ENVELOPE_AT),
  @Deprecated (forRemoval = true, since = "12.3.0")
  ENVELOPE_AT_FILL(CBootstrapIconCSS.BI_ENVELOPE_AT_FILL),
  @Deprecated (forRemoval = true, since = "12.3.0")
  ENVELOPE_CHECK(CBootstrapIconCSS.BI_ENVELOPE_CHECK),
  @Deprecated (forRemoval = true, since = "12.3.0")
  ENVELOPE_CHECK_FILL(CBootstrapIconCSS.BI_ENVELOPE_CHECK_FILL),
  @Deprecated (forRemoval = true, since = "12.3.0")
  ENVELOPE_DASH(CBootstrapIconCSS.BI_ENVELOPE_DASH),
  @Deprecated (forRemoval = true, since = "12.3.0")
  ENVELOPE_DASH_FILL(CBootstrapIconCSS.BI_ENVELOPE_DASH_FILL),
  @Deprecated (forRemoval = true, since = "12.3.0")
  ENVELOPE_EXCLAMATION(CBootstrapIconCSS.BI_ENVELOPE_EXCLAMATION),
  @Deprecated (forRemoval = true, since = "12.3.0")
  ENVELOPE_EXCLAMATION_FILL(CBootstrapIconCSS.BI_ENVELOPE_EXCLAMATION_FILL),
  @Deprecated (forRemoval = true, since = "12.3.0")
  ENVELOPE_FILL(CBootstrapIconCSS.BI_ENVELOPE_FILL),
  @Deprecated (forRemoval = true, since = "12.3.0")
  ENVELOPE_HEART(CBootstrapIconCSS.BI_ENVELOPE_HEART),
  @Deprecated (forRemoval = true, since = "12.3.0")
  ENVELOPE_HEART_FILL(CBootstrapIconCSS.BI_ENVELOPE_HEART_FILL),
  @Deprecated (forRemoval = true, since = "12.3.0")
  ENVELOPE_OPEN(CBootstrapIconCSS.BI_ENVELOPE_OPEN),
  @Deprecated (forRemoval = true, since = "12.3.0")
  ENVELOPE_OPEN_FILL(CBootstrapIconCSS.BI_ENVELOPE_OPEN_FILL),
  @Deprecated (forRemoval = true, since = "12.3.0")
  ENVELOPE_OPEN_HEART(CBootstrapIconCSS.BI_ENVELOPE_OPEN_HEART),
  @Deprecated (forRemoval = true, since = "12.3.0")
  ENVELOPE_OPEN_HEART_FILL(CBootstrapIconCSS.BI_ENVELOPE_OPEN_HEART_FILL),
  @Deprecated (forRemoval = true, since = "12.3.0")
  ENVELOPE_PAPER(CBootstrapIconCSS.BI_ENVELOPE_PAPER),
  @Deprecated (forRemoval = true, since = "12.3.0")
  ENVELOPE_PAPER_FILL(CBootstrapIconCSS.BI_ENVELOPE_PAPER_FILL),
  @Deprecated (forRemoval = true, since = "12.3.0")
  ENVELOPE_PAPER_HEART(CBootstrapIconCSS.BI_ENVELOPE_PAPER_HEART),
  @Deprecated (forRemoval = true, since = "12.3.0")
  ENVELOPE_PAPER_HEART_FILL(CBootstrapIconCSS.BI_ENVELOPE_PAPER_HEART_FILL),
  @Deprecated (forRemoval = true, since = "12.3.0")
  ENVELOPE_PLUS(CBootstrapIconCSS.BI_ENVELOPE_PLUS),
  @Deprecated (forRemoval = true, since = "12.3.0")
  ENVELOPE_PLUS_FILL(CBootstrapIconCSS.BI_ENVELOPE_PLUS_FILL),
  @Deprecated (forRemoval = true, since = "12.3.0")
  ENVELOPE_SLASH(CBootstrapIconCSS.BI_ENVELOPE_SLASH),
  @Deprecated (forRemoval = true, since = "12.3.0")
  ENVELOPE_SLASH_FILL(CBootstrapIconCSS.BI_ENVELOPE_SLASH_FILL),
  @Deprecated (forRemoval = true, since = "12.3.0")
  ENVELOPE_X(CBootstrapIconCSS.BI_ENVELOPE_X),
  @Deprecated (forRemoval = true, since = "12.3.0")
  ENVELOPE_X_FILL(CBootstrapIconCSS.BI_ENVELOPE_X_FILL),
  @Deprecated (forRemoval = true, since = "12.3.0")
  ERASER(CBootstrapIconCSS.BI_ERASER),
  @Deprecated (forRemoval = true, since = "12.3.0")
  ERASER_FILL(CBootstrapIconCSS.BI_ERASER_FILL),
  @Deprecated (forRemoval = true, since = "12.3.0")
  ESCAPE(CBootstrapIconCSS.BI_ESCAPE),
  @Deprecated (forRemoval = true, since = "12.3.0")
  ETHERNET(CBootstrapIconCSS.BI_ETHERNET),
  @Deprecated (forRemoval = true, since = "12.3.0")
  EV_FRONT(CBootstrapIconCSS.BI_EV_FRONT),
  @Deprecated (forRemoval = true, since = "12.3.0")
  EV_FRONT_FILL(CBootstrapIconCSS.BI_EV_FRONT_FILL),
  @Deprecated (forRemoval = true, since = "12.3.0")
  EV_STATION(CBootstrapIconCSS.BI_EV_STATION),
  @Deprecated (forRemoval = true, since = "12.3.0")
  EV_STATION_FILL(CBootstrapIconCSS.BI_EV_STATION_FILL),
  @Deprecated (forRemoval = true, since = "12.3.0")
  EXCLAMATION(CBootstrapIconCSS.BI_EXCLAMATION),
  @Deprecated (forRemoval = true, since = "12.3.0")
  EXCLAMATION_CIRCLE(CBootstrapIconCSS.BI_EXCLAMATION_CIRCLE),
  @Deprecated (forRemoval = true, since = "12.3.0")
  EXCLAMATION_CIRCLE_FILL(CBootstrapIconCSS.BI_EXCLAMATION_CIRCLE_FILL),
  @Deprecated (forRemoval = true, since = "12.3.0")
  EXCLAMATION_DIAMOND(CBootstrapIconCSS.BI_EXCLAMATION_DIAMOND),
  @Deprecated (forRemoval = true, since = "12.3.0")
  EXCLAMATION_DIAMOND_FILL(CBootstrapIconCSS.BI_EXCLAMATION_DIAMOND_FILL),
  @Deprecated (forRemoval = true, since = "12.3.0")
  EXCLAMATION_LG(CBootstrapIconCSS.BI_EXCLAMATION_LG),
  @Deprecated (forRemoval = true, since = "12.3.0")
  EXCLAMATION_OCTAGON(CBootstrapIconCSS.BI_EXCLAMATION_OCTAGON),
  @Deprecated (forRemoval = true, since = "12.3.0")
  EXCLAMATION_OCTAGON_FILL(CBootstrapIconCSS.BI_EXCLAMATION_OCTAGON_FILL),
  @Deprecated (forRemoval = true, since = "12.3.0")
  EXCLAMATION_SQUARE(CBootstrapIconCSS.BI_EXCLAMATION_SQUARE),
  @Deprecated (forRemoval = true, since = "12.3.0")
  EXCLAMATION_SQUARE_FILL(CBootstrapIconCSS.BI_EXCLAMATION_SQUARE_FILL),
  @Deprecated (forRemoval = true, since = "12.3.0")
  EXCLAMATION_TRIANGLE(CBootstrapIconCSS.BI_EXCLAMATION_TRIANGLE),
  @Deprecated (forRemoval = true, since = "12.3.0")
  EXCLAMATION_TRIANGLE_FILL(CBootstrapIconCSS.BI_EXCLAMATION_TRIANGLE_FILL),
  @Deprecated (forRemoval = true, since = "12.3.0")
  EXCLUDE(CBootstrapIconCSS.BI_EXCLUDE),
  @Deprecated (forRemoval = true, since = "12.3.0")
  EXPLICIT(CBootstrapIconCSS.BI_EXPLICIT),
  @Deprecated (forRemoval = true, since = "12.3.0")
  EXPLICIT_FILL(CBootstrapIconCSS.BI_EXPLICIT_FILL),
  @Deprecated (forRemoval = true, since = "12.3.0")
  EXPOSURE(CBootstrapIconCSS.BI_EXPOSURE),
  @Deprecated (forRemoval = true, since = "12.3.0")
  EYE(CBootstrapIconCSS.BI_EYE),
  @Deprecated (forRemoval = true, since = "12.3.0")
  EYE_FILL(CBootstrapIconCSS.BI_EYE_FILL),
  @Deprecated (forRemoval = true, since = "12.3.0")
  EYE_SLASH(CBootstrapIconCSS.BI_EYE_SLASH),
  @Deprecated (forRemoval = true, since = "12.3.0")
  EYE_SLASH_FILL(CBootstrapIconCSS.BI_EYE_SLASH_FILL),
  @Deprecated (forRemoval = true, since = "12.3.0")
  EYEDROPPER(CBootstrapIconCSS.BI_EYEDROPPER),
  @Deprecated (forRemoval = true, since = "12.3.0")
  EYEGLASSES(CBootstrapIconCSS.BI_EYEGLASSES),
  @Deprecated (forRemoval = true, since = "12.3.0")
  FACEBOOK(CBootstrapIconCSS.BI_FACEBOOK),
  @Deprecated (forRemoval = true, since = "12.3.0")
  FAN(CBootstrapIconCSS.BI_FAN),
  @Deprecated (forRemoval = true, since = "12.3.0")
  FAST_FORWARD(CBootstrapIconCSS.BI_FAST_FORWARD),
  @Deprecated (forRemoval = true, since = "12.3.0")
  FAST_FORWARD_BTN(CBootstrapIconCSS.BI_FAST_FORWARD_BTN),
  @Deprecated (forRemoval = true, since = "12.3.0")
  FAST_FORWARD_BTN_FILL(CBootstrapIconCSS.BI_FAST_FORWARD_BTN_FILL),
  @Deprecated (forRemoval = true, since = "12.3.0")
  FAST_FORWARD_CIRCLE(CBootstrapIconCSS.BI_FAST_FORWARD_CIRCLE),
  @Deprecated (forRemoval = true, since = "12.3.0")
  FAST_FORWARD_CIRCLE_FILL(CBootstrapIconCSS.BI_FAST_FORWARD_CIRCLE_FILL),
  @Deprecated (forRemoval = true, since = "12.3.0")
  FAST_FORWARD_FILL(CBootstrapIconCSS.BI_FAST_FORWARD_FILL),
  @Deprecated (forRemoval = true, since = "12.3.0")
  FEATHER(CBootstrapIconCSS.BI_FEATHER),
  @Deprecated (forRemoval = true, since = "12.3.0")
  FEATHER2(CBootstrapIconCSS.BI_FEATHER2),
  @Deprecated (forRemoval = true, since = "12.3.0")
  FILE(CBootstrapIconCSS.BI_FILE),
  @Deprecated (forRemoval = true, since = "12.3.0")
  FILE_ARROW_DOWN(CBootstrapIconCSS.BI_FILE_ARROW_DOWN),
  @Deprecated (forRemoval = true, since = "12.3.0")
  FILE_ARROW_DOWN_FILL(CBootstrapIconCSS.BI_FILE_ARROW_DOWN_FILL),
  @Deprecated (forRemoval = true, since = "12.3.0")
  FILE_ARROW_UP(CBootstrapIconCSS.BI_FILE_ARROW_UP),
  @Deprecated (forRemoval = true, since = "12.3.0")
  FILE_ARROW_UP_FILL(CBootstrapIconCSS.BI_FILE_ARROW_UP_FILL),
  @Deprecated (forRemoval = true, since = "12.3.0")
  FILE_BAR_GRAPH(CBootstrapIconCSS.BI_FILE_BAR_GRAPH),
  @Deprecated (forRemoval = true, since = "12.3.0")
  FILE_BAR_GRAPH_FILL(CBootstrapIconCSS.BI_FILE_BAR_GRAPH_FILL),
  @Deprecated (forRemoval = true, since = "12.3.0")
  FILE_BINARY(CBootstrapIconCSS.BI_FILE_BINARY),
  @Deprecated (forRemoval = true, since = "12.3.0")
  FILE_BINARY_FILL(CBootstrapIconCSS.BI_FILE_BINARY_FILL),
  @Deprecated (forRemoval = true, since = "12.3.0")
  FILE_BREAK(CBootstrapIconCSS.BI_FILE_BREAK),
  @Deprecated (forRemoval = true, since = "12.3.0")
  FILE_BREAK_FILL(CBootstrapIconCSS.BI_FILE_BREAK_FILL),
  @Deprecated (forRemoval = true, since = "12.3.0")
  FILE_CHECK(CBootstrapIconCSS.BI_FILE_CHECK),
  @Deprecated (forRemoval = true, since = "12.3.0")
  FILE_CHECK_FILL(CBootstrapIconCSS.BI_FILE_CHECK_FILL),
  @Deprecated (forRemoval = true, since = "12.3.0")
  FILE_CODE(CBootstrapIconCSS.BI_FILE_CODE),
  @Deprecated (forRemoval = true, since = "12.3.0")
  FILE_CODE_FILL(CBootstrapIconCSS.BI_FILE_CODE_FILL),
  @Deprecated (forRemoval = true, since = "12.3.0")
  FILE_DIFF(CBootstrapIconCSS.BI_FILE_DIFF),
  @Deprecated (forRemoval = true, since = "12.3.0")
  FILE_DIFF_FILL(CBootstrapIconCSS.BI_FILE_DIFF_FILL),
  @Deprecated (forRemoval = true, since = "12.3.0")
  FILE_EARMARK(CBootstrapIconCSS.BI_FILE_EARMARK),
  @Deprecated (forRemoval = true, since = "12.3.0")
  FILE_EARMARK_ARROW_DOWN(CBootstrapIconCSS.BI_FILE_EARMARK_ARROW_DOWN),
  @Deprecated (forRemoval = true, since = "12.3.0")
  FILE_EARMARK_ARROW_DOWN_FILL(CBootstrapIconCSS.BI_FILE_EARMARK_ARROW_DOWN_FILL),
  @Deprecated (forRemoval = true, since = "12.3.0")
  FILE_EARMARK_ARROW_UP(CBootstrapIconCSS.BI_FILE_EARMARK_ARROW_UP),
  @Deprecated (forRemoval = true, since = "12.3.0")
  FILE_EARMARK_ARROW_UP_FILL(CBootstrapIconCSS.BI_FILE_EARMARK_ARROW_UP_FILL),
  @Deprecated (forRemoval = true, since = "12.3.0")
  FILE_EARMARK_BAR_GRAPH(CBootstrapIconCSS.BI_FILE_EARMARK_BAR_GRAPH),
  @Deprecated (forRemoval = true, since = "12.3.0")
  FILE_EARMARK_BAR_GRAPH_FILL(CBootstrapIconCSS.BI_FILE_EARMARK_BAR_GRAPH_FILL),
  @Deprecated (forRemoval = true, since = "12.3.0")
  FILE_EARMARK_BINARY(CBootstrapIconCSS.BI_FILE_EARMARK_BINARY),
  @Deprecated (forRemoval = true, since = "12.3.0")
  FILE_EARMARK_BINARY_FILL(CBootstrapIconCSS.BI_FILE_EARMARK_BINARY_FILL),
  @Deprecated (forRemoval = true, since = "12.3.0")
  FILE_EARMARK_BREAK(CBootstrapIconCSS.BI_FILE_EARMARK_BREAK),
  @Deprecated (forRemoval = true, since = "12.3.0")
  FILE_EARMARK_BREAK_FILL(CBootstrapIconCSS.BI_FILE_EARMARK_BREAK_FILL),
  @Deprecated (forRemoval = true, since = "12.3.0")
  FILE_EARMARK_CHECK(CBootstrapIconCSS.BI_FILE_EARMARK_CHECK),
  @Deprecated (forRemoval = true, since = "12.3.0")
  FILE_EARMARK_CHECK_FILL(CBootstrapIconCSS.BI_FILE_EARMARK_CHECK_FILL),
  @Deprecated (forRemoval = true, since = "12.3.0")
  FILE_EARMARK_CODE(CBootstrapIconCSS.BI_FILE_EARMARK_CODE),
  @Deprecated (forRemoval = true, since = "12.3.0")
  FILE_EARMARK_CODE_FILL(CBootstrapIconCSS.BI_FILE_EARMARK_CODE_FILL),
  @Deprecated (forRemoval = true, since = "12.3.0")
  FILE_EARMARK_DIFF(CBootstrapIconCSS.BI_FILE_EARMARK_DIFF),
  @Deprecated (forRemoval = true, since = "12.3.0")
  FILE_EARMARK_DIFF_FILL(CBootstrapIconCSS.BI_FILE_EARMARK_DIFF_FILL),
  @Deprecated (forRemoval = true, since = "12.3.0")
  FILE_EARMARK_EASEL(CBootstrapIconCSS.BI_FILE_EARMARK_EASEL),
  @Deprecated (forRemoval = true, since = "12.3.0")
  FILE_EARMARK_EASEL_FILL(CBootstrapIconCSS.BI_FILE_EARMARK_EASEL_FILL),
  @Deprecated (forRemoval = true, since = "12.3.0")
  FILE_EARMARK_EXCEL(CBootstrapIconCSS.BI_FILE_EARMARK_EXCEL),
  @Deprecated (forRemoval = true, since = "12.3.0")
  FILE_EARMARK_EXCEL_FILL(CBootstrapIconCSS.BI_FILE_EARMARK_EXCEL_FILL),
  @Deprecated (forRemoval = true, since = "12.3.0")
  FILE_EARMARK_FILL(CBootstrapIconCSS.BI_FILE_EARMARK_FILL),
  @Deprecated (forRemoval = true, since = "12.3.0")
  FILE_EARMARK_FONT(CBootstrapIconCSS.BI_FILE_EARMARK_FONT),
  @Deprecated (forRemoval = true, since = "12.3.0")
  FILE_EARMARK_FONT_FILL(CBootstrapIconCSS.BI_FILE_EARMARK_FONT_FILL),
  @Deprecated (forRemoval = true, since = "12.3.0")
  FILE_EARMARK_IMAGE(CBootstrapIconCSS.BI_FILE_EARMARK_IMAGE),
  @Deprecated (forRemoval = true, since = "12.3.0")
  FILE_EARMARK_IMAGE_FILL(CBootstrapIconCSS.BI_FILE_EARMARK_IMAGE_FILL),
  @Deprecated (forRemoval = true, since = "12.3.0")
  FILE_EARMARK_LOCK(CBootstrapIconCSS.BI_FILE_EARMARK_LOCK),
  @Deprecated (forRemoval = true, since = "12.3.0")
  FILE_EARMARK_LOCK_FILL(CBootstrapIconCSS.BI_FILE_EARMARK_LOCK_FILL),
  @Deprecated (forRemoval = true, since = "12.3.0")
  FILE_EARMARK_LOCK2(CBootstrapIconCSS.BI_FILE_EARMARK_LOCK2),
  @Deprecated (forRemoval = true, since = "12.3.0")
  FILE_EARMARK_LOCK2_FILL(CBootstrapIconCSS.BI_FILE_EARMARK_LOCK2_FILL),
  @Deprecated (forRemoval = true, since = "12.3.0")
  FILE_EARMARK_MEDICAL(CBootstrapIconCSS.BI_FILE_EARMARK_MEDICAL),
  @Deprecated (forRemoval = true, since = "12.3.0")
  FILE_EARMARK_MEDICAL_FILL(CBootstrapIconCSS.BI_FILE_EARMARK_MEDICAL_FILL),
  @Deprecated (forRemoval = true, since = "12.3.0")
  FILE_EARMARK_MINUS(CBootstrapIconCSS.BI_FILE_EARMARK_MINUS),
  @Deprecated (forRemoval = true, since = "12.3.0")
  FILE_EARMARK_MINUS_FILL(CBootstrapIconCSS.BI_FILE_EARMARK_MINUS_FILL),
  @Deprecated (forRemoval = true, since = "12.3.0")
  FILE_EARMARK_MUSIC(CBootstrapIconCSS.BI_FILE_EARMARK_MUSIC),
  @Deprecated (forRemoval = true, since = "12.3.0")
  FILE_EARMARK_MUSIC_FILL(CBootstrapIconCSS.BI_FILE_EARMARK_MUSIC_FILL),
  @Deprecated (forRemoval = true, since = "12.3.0")
  FILE_EARMARK_PDF(CBootstrapIconCSS.BI_FILE_EARMARK_PDF),
  @Deprecated (forRemoval = true, since = "12.3.0")
  FILE_EARMARK_PDF_FILL(CBootstrapIconCSS.BI_FILE_EARMARK_PDF_FILL),
  @Deprecated (forRemoval = true, since = "12.3.0")
  FILE_EARMARK_PERSON(CBootstrapIconCSS.BI_FILE_EARMARK_PERSON),
  @Deprecated (forRemoval = true, since = "12.3.0")
  FILE_EARMARK_PERSON_FILL(CBootstrapIconCSS.BI_FILE_EARMARK_PERSON_FILL),
  @Deprecated (forRemoval = true, since = "12.3.0")
  FILE_EARMARK_PLAY(CBootstrapIconCSS.BI_FILE_EARMARK_PLAY),
  @Deprecated (forRemoval = true, since = "12.3.0")
  FILE_EARMARK_PLAY_FILL(CBootstrapIconCSS.BI_FILE_EARMARK_PLAY_FILL),
  @Deprecated (forRemoval = true, since = "12.3.0")
  FILE_EARMARK_PLUS(CBootstrapIconCSS.BI_FILE_EARMARK_PLUS),
  @Deprecated (forRemoval = true, since = "12.3.0")
  FILE_EARMARK_PLUS_FILL(CBootstrapIconCSS.BI_FILE_EARMARK_PLUS_FILL),
  @Deprecated (forRemoval = true, since = "12.3.0")
  FILE_EARMARK_POST(CBootstrapIconCSS.BI_FILE_EARMARK_POST),
  @Deprecated (forRemoval = true, since = "12.3.0")
  FILE_EARMARK_POST_FILL(CBootstrapIconCSS.BI_FILE_EARMARK_POST_FILL),
  @Deprecated (forRemoval = true, since = "12.3.0")
  FILE_EARMARK_PPT(CBootstrapIconCSS.BI_FILE_EARMARK_PPT),
  @Deprecated (forRemoval = true, since = "12.3.0")
  FILE_EARMARK_PPT_FILL(CBootstrapIconCSS.BI_FILE_EARMARK_PPT_FILL),
  @Deprecated (forRemoval = true, since = "12.3.0")
  FILE_EARMARK_RICHTEXT(CBootstrapIconCSS.BI_FILE_EARMARK_RICHTEXT),
  @Deprecated (forRemoval = true, since = "12.3.0")
  FILE_EARMARK_RICHTEXT_FILL(CBootstrapIconCSS.BI_FILE_EARMARK_RICHTEXT_FILL),
  @Deprecated (forRemoval = true, since = "12.3.0")
  FILE_EARMARK_RULED(CBootstrapIconCSS.BI_FILE_EARMARK_RULED),
  @Deprecated (forRemoval = true, since = "12.3.0")
  FILE_EARMARK_RULED_FILL(CBootstrapIconCSS.BI_FILE_EARMARK_RULED_FILL),
  @Deprecated (forRemoval = true, since = "12.3.0")
  FILE_EARMARK_SLIDES(CBootstrapIconCSS.BI_FILE_EARMARK_SLIDES),
  @Deprecated (forRemoval = true, since = "12.3.0")
  FILE_EARMARK_SLIDES_FILL(CBootstrapIconCSS.BI_FILE_EARMARK_SLIDES_FILL),
  @Deprecated (forRemoval = true, since = "12.3.0")
  FILE_EARMARK_SPREADSHEET(CBootstrapIconCSS.BI_FILE_EARMARK_SPREADSHEET),
  @Deprecated (forRemoval = true, since = "12.3.0")
  FILE_EARMARK_SPREADSHEET_FILL(CBootstrapIconCSS.BI_FILE_EARMARK_SPREADSHEET_FILL),
  @Deprecated (forRemoval = true, since = "12.3.0")
  FILE_EARMARK_TEXT(CBootstrapIconCSS.BI_FILE_EARMARK_TEXT),
  @Deprecated (forRemoval = true, since = "12.3.0")
  FILE_EARMARK_TEXT_FILL(CBootstrapIconCSS.BI_FILE_EARMARK_TEXT_FILL),
  @Deprecated (forRemoval = true, since = "12.3.0")
  FILE_EARMARK_WORD(CBootstrapIconCSS.BI_FILE_EARMARK_WORD),
  @Deprecated (forRemoval = true, since = "12.3.0")
  FILE_EARMARK_WORD_FILL(CBootstrapIconCSS.BI_FILE_EARMARK_WORD_FILL),
  @Deprecated (forRemoval = true, since = "12.3.0")
  FILE_EARMARK_X(CBootstrapIconCSS.BI_FILE_EARMARK_X),
  @Deprecated (forRemoval = true, since = "12.3.0")
  FILE_EARMARK_X_FILL(CBootstrapIconCSS.BI_FILE_EARMARK_X_FILL),
  @Deprecated (forRemoval = true, since = "12.3.0")
  FILE_EARMARK_ZIP(CBootstrapIconCSS.BI_FILE_EARMARK_ZIP),
  @Deprecated (forRemoval = true, since = "12.3.0")
  FILE_EARMARK_ZIP_FILL(CBootstrapIconCSS.BI_FILE_EARMARK_ZIP_FILL),
  @Deprecated (forRemoval = true, since = "12.3.0")
  FILE_EASEL(CBootstrapIconCSS.BI_FILE_EASEL),
  @Deprecated (forRemoval = true, since = "12.3.0")
  FILE_EASEL_FILL(CBootstrapIconCSS.BI_FILE_EASEL_FILL),
  @Deprecated (forRemoval = true, since = "12.3.0")
  FILE_EXCEL(CBootstrapIconCSS.BI_FILE_EXCEL),
  @Deprecated (forRemoval = true, since = "12.3.0")
  FILE_EXCEL_FILL(CBootstrapIconCSS.BI_FILE_EXCEL_FILL),
  @Deprecated (forRemoval = true, since = "12.3.0")
  FILE_FILL(CBootstrapIconCSS.BI_FILE_FILL),
  @Deprecated (forRemoval = true, since = "12.3.0")
  FILE_FONT(CBootstrapIconCSS.BI_FILE_FONT),
  @Deprecated (forRemoval = true, since = "12.3.0")
  FILE_FONT_FILL(CBootstrapIconCSS.BI_FILE_FONT_FILL),
  @Deprecated (forRemoval = true, since = "12.3.0")
  FILE_IMAGE(CBootstrapIconCSS.BI_FILE_IMAGE),
  @Deprecated (forRemoval = true, since = "12.3.0")
  FILE_IMAGE_FILL(CBootstrapIconCSS.BI_FILE_IMAGE_FILL),
  @Deprecated (forRemoval = true, since = "12.3.0")
  FILE_LOCK(CBootstrapIconCSS.BI_FILE_LOCK),
  @Deprecated (forRemoval = true, since = "12.3.0")
  FILE_LOCK_FILL(CBootstrapIconCSS.BI_FILE_LOCK_FILL),
  @Deprecated (forRemoval = true, since = "12.3.0")
  FILE_LOCK2(CBootstrapIconCSS.BI_FILE_LOCK2),
  @Deprecated (forRemoval = true, since = "12.3.0")
  FILE_LOCK2_FILL(CBootstrapIconCSS.BI_FILE_LOCK2_FILL),
  @Deprecated (forRemoval = true, since = "12.3.0")
  FILE_MEDICAL(CBootstrapIconCSS.BI_FILE_MEDICAL),
  @Deprecated (forRemoval = true, since = "12.3.0")
  FILE_MEDICAL_FILL(CBootstrapIconCSS.BI_FILE_MEDICAL_FILL),
  @Deprecated (forRemoval = true, since = "12.3.0")
  FILE_MINUS(CBootstrapIconCSS.BI_FILE_MINUS),
  @Deprecated (forRemoval = true, since = "12.3.0")
  FILE_MINUS_FILL(CBootstrapIconCSS.BI_FILE_MINUS_FILL),
  @Deprecated (forRemoval = true, since = "12.3.0")
  FILE_MUSIC(CBootstrapIconCSS.BI_FILE_MUSIC),
  @Deprecated (forRemoval = true, since = "12.3.0")
  FILE_MUSIC_FILL(CBootstrapIconCSS.BI_FILE_MUSIC_FILL),
  @Deprecated (forRemoval = true, since = "12.3.0")
  FILE_PDF(CBootstrapIconCSS.BI_FILE_PDF),
  @Deprecated (forRemoval = true, since = "12.3.0")
  FILE_PDF_FILL(CBootstrapIconCSS.BI_FILE_PDF_FILL),
  @Deprecated (forRemoval = true, since = "12.3.0")
  FILE_PERSON(CBootstrapIconCSS.BI_FILE_PERSON),
  @Deprecated (forRemoval = true, since = "12.3.0")
  FILE_PERSON_FILL(CBootstrapIconCSS.BI_FILE_PERSON_FILL),
  @Deprecated (forRemoval = true, since = "12.3.0")
  FILE_PLAY(CBootstrapIconCSS.BI_FILE_PLAY),
  @Deprecated (forRemoval = true, since = "12.3.0")
  FILE_PLAY_FILL(CBootstrapIconCSS.BI_FILE_PLAY_FILL),
  @Deprecated (forRemoval = true, since = "12.3.0")
  FILE_PLUS(CBootstrapIconCSS.BI_FILE_PLUS),
  @Deprecated (forRemoval = true, since = "12.3.0")
  FILE_PLUS_FILL(CBootstrapIconCSS.BI_FILE_PLUS_FILL),
  @Deprecated (forRemoval = true, since = "12.3.0")
  FILE_POST(CBootstrapIconCSS.BI_FILE_POST),
  @Deprecated (forRemoval = true, since = "12.3.0")
  FILE_POST_FILL(CBootstrapIconCSS.BI_FILE_POST_FILL),
  @Deprecated (forRemoval = true, since = "12.3.0")
  FILE_PPT(CBootstrapIconCSS.BI_FILE_PPT),
  @Deprecated (forRemoval = true, since = "12.3.0")
  FILE_PPT_FILL(CBootstrapIconCSS.BI_FILE_PPT_FILL),
  @Deprecated (forRemoval = true, since = "12.3.0")
  FILE_RICHTEXT(CBootstrapIconCSS.BI_FILE_RICHTEXT),
  @Deprecated (forRemoval = true, since = "12.3.0")
  FILE_RICHTEXT_FILL(CBootstrapIconCSS.BI_FILE_RICHTEXT_FILL),
  @Deprecated (forRemoval = true, since = "12.3.0")
  FILE_RULED(CBootstrapIconCSS.BI_FILE_RULED),
  @Deprecated (forRemoval = true, since = "12.3.0")
  FILE_RULED_FILL(CBootstrapIconCSS.BI_FILE_RULED_FILL),
  @Deprecated (forRemoval = true, since = "12.3.0")
  FILE_SLIDES(CBootstrapIconCSS.BI_FILE_SLIDES),
  @Deprecated (forRemoval = true, since = "12.3.0")
  FILE_SLIDES_FILL(CBootstrapIconCSS.BI_FILE_SLIDES_FILL),
  @Deprecated (forRemoval = true, since = "12.3.0")
  FILE_SPREADSHEET(CBootstrapIconCSS.BI_FILE_SPREADSHEET),
  @Deprecated (forRemoval = true, since = "12.3.0")
  FILE_SPREADSHEET_FILL(CBootstrapIconCSS.BI_FILE_SPREADSHEET_FILL),
  @Deprecated (forRemoval = true, since = "12.3.0")
  FILE_TEXT(CBootstrapIconCSS.BI_FILE_TEXT),
  @Deprecated (forRemoval = true, since = "12.3.0")
  FILE_TEXT_FILL(CBootstrapIconCSS.BI_FILE_TEXT_FILL),
  @Deprecated (forRemoval = true, since = "12.3.0")
  FILE_WORD(CBootstrapIconCSS.BI_FILE_WORD),
  @Deprecated (forRemoval = true, since = "12.3.0")
  FILE_WORD_FILL(CBootstrapIconCSS.BI_FILE_WORD_FILL),
  @Deprecated (forRemoval = true, since = "12.3.0")
  FILE_X(CBootstrapIconCSS.BI_FILE_X),
  @Deprecated (forRemoval = true, since = "12.3.0")
  FILE_X_FILL(CBootstrapIconCSS.BI_FILE_X_FILL),
  @Deprecated (forRemoval = true, since = "12.3.0")
  FILE_ZIP(CBootstrapIconCSS.BI_FILE_ZIP),
  @Deprecated (forRemoval = true, since = "12.3.0")
  FILE_ZIP_FILL(CBootstrapIconCSS.BI_FILE_ZIP_FILL),
  @Deprecated (forRemoval = true, since = "12.3.0")
  FILES(CBootstrapIconCSS.BI_FILES),
  @Deprecated (forRemoval = true, since = "12.3.0")
  FILES_ALT(CBootstrapIconCSS.BI_FILES_ALT),
  @Deprecated (forRemoval = true, since = "12.3.0")
  FILETYPE_AAC(CBootstrapIconCSS.BI_FILETYPE_AAC),
  @Deprecated (forRemoval = true, since = "12.3.0")
  FILETYPE_AI(CBootstrapIconCSS.BI_FILETYPE_AI),
  @Deprecated (forRemoval = true, since = "12.3.0")
  FILETYPE_BMP(CBootstrapIconCSS.BI_FILETYPE_BMP),
  @Deprecated (forRemoval = true, since = "12.3.0")
  FILETYPE_CS(CBootstrapIconCSS.BI_FILETYPE_CS),
  @Deprecated (forRemoval = true, since = "12.3.0")
  FILETYPE_CSS(CBootstrapIconCSS.BI_FILETYPE_CSS),
  @Deprecated (forRemoval = true, since = "12.3.0")
  FILETYPE_CSV(CBootstrapIconCSS.BI_FILETYPE_CSV),
  @Deprecated (forRemoval = true, since = "12.3.0")
  FILETYPE_DOC(CBootstrapIconCSS.BI_FILETYPE_DOC),
  @Deprecated (forRemoval = true, since = "12.3.0")
  FILETYPE_DOCX(CBootstrapIconCSS.BI_FILETYPE_DOCX),
  @Deprecated (forRemoval = true, since = "12.3.0")
  FILETYPE_EXE(CBootstrapIconCSS.BI_FILETYPE_EXE),
  @Deprecated (forRemoval = true, since = "12.3.0")
  FILETYPE_GIF(CBootstrapIconCSS.BI_FILETYPE_GIF),
  @Deprecated (forRemoval = true, since = "12.3.0")
  FILETYPE_HEIC(CBootstrapIconCSS.BI_FILETYPE_HEIC),
  @Deprecated (forRemoval = true, since = "12.3.0")
  FILETYPE_HTML(CBootstrapIconCSS.BI_FILETYPE_HTML),
  @Deprecated (forRemoval = true, since = "12.3.0")
  FILETYPE_JAVA(CBootstrapIconCSS.BI_FILETYPE_JAVA),
  @Deprecated (forRemoval = true, since = "12.3.0")
  FILETYPE_JPG(CBootstrapIconCSS.BI_FILETYPE_JPG),
  @Deprecated (forRemoval = true, since = "12.3.0")
  FILETYPE_JS(CBootstrapIconCSS.BI_FILETYPE_JS),
  @Deprecated (forRemoval = true, since = "12.3.0")
  FILETYPE_JSON(CBootstrapIconCSS.BI_FILETYPE_JSON),
  @Deprecated (forRemoval = true, since = "12.3.0")
  FILETYPE_JSX(CBootstrapIconCSS.BI_FILETYPE_JSX),
  @Deprecated (forRemoval = true, since = "12.3.0")
  FILETYPE_KEY(CBootstrapIconCSS.BI_FILETYPE_KEY),
  @Deprecated (forRemoval = true, since = "12.3.0")
  FILETYPE_M4P(CBootstrapIconCSS.BI_FILETYPE_M4P),
  @Deprecated (forRemoval = true, since = "12.3.0")
  FILETYPE_MD(CBootstrapIconCSS.BI_FILETYPE_MD),
  @Deprecated (forRemoval = true, since = "12.3.0")
  FILETYPE_MDX(CBootstrapIconCSS.BI_FILETYPE_MDX),
  @Deprecated (forRemoval = true, since = "12.3.0")
  FILETYPE_MOV(CBootstrapIconCSS.BI_FILETYPE_MOV),
  @Deprecated (forRemoval = true, since = "12.3.0")
  FILETYPE_MP3(CBootstrapIconCSS.BI_FILETYPE_MP3),
  @Deprecated (forRemoval = true, since = "12.3.0")
  FILETYPE_MP4(CBootstrapIconCSS.BI_FILETYPE_MP4),
  @Deprecated (forRemoval = true, since = "12.3.0")
  FILETYPE_OTF(CBootstrapIconCSS.BI_FILETYPE_OTF),
  @Deprecated (forRemoval = true, since = "12.3.0")
  FILETYPE_PDF(CBootstrapIconCSS.BI_FILETYPE_PDF),
  @Deprecated (forRemoval = true, since = "12.3.0")
  FILETYPE_PHP(CBootstrapIconCSS.BI_FILETYPE_PHP),
  @Deprecated (forRemoval = true, since = "12.3.0")
  FILETYPE_PNG(CBootstrapIconCSS.BI_FILETYPE_PNG),
  @Deprecated (forRemoval = true, since = "12.3.0")
  FILETYPE_PPT(CBootstrapIconCSS.BI_FILETYPE_PPT),
  @Deprecated (forRemoval = true, since = "12.3.0")
  FILETYPE_PPTX(CBootstrapIconCSS.BI_FILETYPE_PPTX),
  @Deprecated (forRemoval = true, since = "12.3.0")
  FILETYPE_PSD(CBootstrapIconCSS.BI_FILETYPE_PSD),
  @Deprecated (forRemoval = true, since = "12.3.0")
  FILETYPE_PY(CBootstrapIconCSS.BI_FILETYPE_PY),
  @Deprecated (forRemoval = true, since = "12.3.0")
  FILETYPE_RAW(CBootstrapIconCSS.BI_FILETYPE_RAW),
  @Deprecated (forRemoval = true, since = "12.3.0")
  FILETYPE_RB(CBootstrapIconCSS.BI_FILETYPE_RB),
  @Deprecated (forRemoval = true, since = "12.3.0")
  FILETYPE_SASS(CBootstrapIconCSS.BI_FILETYPE_SASS),
  @Deprecated (forRemoval = true, since = "12.3.0")
  FILETYPE_SCSS(CBootstrapIconCSS.BI_FILETYPE_SCSS),
  @Deprecated (forRemoval = true, since = "12.3.0")
  FILETYPE_SH(CBootstrapIconCSS.BI_FILETYPE_SH),
  @Deprecated (forRemoval = true, since = "12.3.0")
  FILETYPE_SQL(CBootstrapIconCSS.BI_FILETYPE_SQL),
  @Deprecated (forRemoval = true, since = "12.3.0")
  FILETYPE_SVG(CBootstrapIconCSS.BI_FILETYPE_SVG),
  @Deprecated (forRemoval = true, since = "12.3.0")
  FILETYPE_TIFF(CBootstrapIconCSS.BI_FILETYPE_TIFF),
  @Deprecated (forRemoval = true, since = "12.3.0")
  FILETYPE_TSX(CBootstrapIconCSS.BI_FILETYPE_TSX),
  @Deprecated (forRemoval = true, since = "12.3.0")
  FILETYPE_TTF(CBootstrapIconCSS.BI_FILETYPE_TTF),
  @Deprecated (forRemoval = true, since = "12.3.0")
  FILETYPE_TXT(CBootstrapIconCSS.BI_FILETYPE_TXT),
  @Deprecated (forRemoval = true, since = "12.3.0")
  FILETYPE_WAV(CBootstrapIconCSS.BI_FILETYPE_WAV),
  @Deprecated (forRemoval = true, since = "12.3.0")
  FILETYPE_WOFF(CBootstrapIconCSS.BI_FILETYPE_WOFF),
  @Deprecated (forRemoval = true, since = "12.3.0")
  FILETYPE_XLS(CBootstrapIconCSS.BI_FILETYPE_XLS),
  @Deprecated (forRemoval = true, since = "12.3.0")
  FILETYPE_XLSX(CBootstrapIconCSS.BI_FILETYPE_XLSX),
  @Deprecated (forRemoval = true, since = "12.3.0")
  FILETYPE_XML(CBootstrapIconCSS.BI_FILETYPE_XML),
  @Deprecated (forRemoval = true, since = "12.3.0")
  FILETYPE_YML(CBootstrapIconCSS.BI_FILETYPE_YML),
  @Deprecated (forRemoval = true, since = "12.3.0")
  FILM(CBootstrapIconCSS.BI_FILM),
  @Deprecated (forRemoval = true, since = "12.3.0")
  FILTER(CBootstrapIconCSS.BI_FILTER),
  @Deprecated (forRemoval = true, since = "12.3.0")
  FILTER_CIRCLE(CBootstrapIconCSS.BI_FILTER_CIRCLE),
  @Deprecated (forRemoval = true, since = "12.3.0")
  FILTER_CIRCLE_FILL(CBootstrapIconCSS.BI_FILTER_CIRCLE_FILL),
  @Deprecated (forRemoval = true, since = "12.3.0")
  FILTER_LEFT(CBootstrapIconCSS.BI_FILTER_LEFT),
  @Deprecated (forRemoval = true, since = "12.3.0")
  FILTER_RIGHT(CBootstrapIconCSS.BI_FILTER_RIGHT),
  @Deprecated (forRemoval = true, since = "12.3.0")
  FILTER_SQUARE(CBootstrapIconCSS.BI_FILTER_SQUARE),
  @Deprecated (forRemoval = true, since = "12.3.0")
  FILTER_SQUARE_FILL(CBootstrapIconCSS.BI_FILTER_SQUARE_FILL),
  @Deprecated (forRemoval = true, since = "12.3.0")
  FINGERPRINT(CBootstrapIconCSS.BI_FINGERPRINT),
  @Deprecated (forRemoval = true, since = "12.3.0")
  FIRE(CBootstrapIconCSS.BI_FIRE),
  @Deprecated (forRemoval = true, since = "12.3.0")
  FLAG(CBootstrapIconCSS.BI_FLAG),
  @Deprecated (forRemoval = true, since = "12.3.0")
  FLAG_FILL(CBootstrapIconCSS.BI_FLAG_FILL),
  @Deprecated (forRemoval = true, since = "12.3.0")
  FLOPPY(CBootstrapIconCSS.BI_FLOPPY),
  @Deprecated (forRemoval = true, since = "12.3.0")
  FLOPPY_FILL(CBootstrapIconCSS.BI_FLOPPY_FILL),
  @Deprecated (forRemoval = true, since = "12.3.0")
  FLOPPY2(CBootstrapIconCSS.BI_FLOPPY2),
  @Deprecated (forRemoval = true, since = "12.3.0")
  FLOPPY2_FILL(CBootstrapIconCSS.BI_FLOPPY2_FILL),
  @Deprecated (forRemoval = true, since = "12.3.0")
  FLOWER1(CBootstrapIconCSS.BI_FLOWER1),
  @Deprecated (forRemoval = true, since = "12.3.0")
  FLOWER2(CBootstrapIconCSS.BI_FLOWER2),
  @Deprecated (forRemoval = true, since = "12.3.0")
  FLOWER3(CBootstrapIconCSS.BI_FLOWER3),
  @Deprecated (forRemoval = true, since = "12.3.0")
  FOLDER(CBootstrapIconCSS.BI_FOLDER),
  @Deprecated (forRemoval = true, since = "12.3.0")
  FOLDER_CHECK(CBootstrapIconCSS.BI_FOLDER_CHECK),
  @Deprecated (forRemoval = true, since = "12.3.0")
  FOLDER_FILL(CBootstrapIconCSS.BI_FOLDER_FILL),
  @Deprecated (forRemoval = true, since = "12.3.0")
  FOLDER_MINUS(CBootstrapIconCSS.BI_FOLDER_MINUS),
  @Deprecated (forRemoval = true, since = "12.3.0")
  FOLDER_PLUS(CBootstrapIconCSS.BI_FOLDER_PLUS),
  @Deprecated (forRemoval = true, since = "12.3.0")
  FOLDER_SYMLINK(CBootstrapIconCSS.BI_FOLDER_SYMLINK),
  @Deprecated (forRemoval = true, since = "12.3.0")
  FOLDER_SYMLINK_FILL(CBootstrapIconCSS.BI_FOLDER_SYMLINK_FILL),
  @Deprecated (forRemoval = true, since = "12.3.0")
  FOLDER_X(CBootstrapIconCSS.BI_FOLDER_X),
  @Deprecated (forRemoval = true, since = "12.3.0")
  FOLDER2(CBootstrapIconCSS.BI_FOLDER2),
  @Deprecated (forRemoval = true, since = "12.3.0")
  FOLDER2_OPEN(CBootstrapIconCSS.BI_FOLDER2_OPEN),
  @Deprecated (forRemoval = true, since = "12.3.0")
  FONTS(CBootstrapIconCSS.BI_FONTS),
  @Deprecated (forRemoval = true, since = "12.3.0")
  FORWARD(CBootstrapIconCSS.BI_FORWARD),
  @Deprecated (forRemoval = true, since = "12.3.0")
  FORWARD_FILL(CBootstrapIconCSS.BI_FORWARD_FILL),
  @Deprecated (forRemoval = true, since = "12.3.0")
  FRONT(CBootstrapIconCSS.BI_FRONT),
  @Deprecated (forRemoval = true, since = "12.3.0")
  FUEL_PUMP(CBootstrapIconCSS.BI_FUEL_PUMP),
  @Deprecated (forRemoval = true, since = "12.3.0")
  FUEL_PUMP_DIESEL(CBootstrapIconCSS.BI_FUEL_PUMP_DIESEL),
  @Deprecated (forRemoval = true, since = "12.3.0")
  FUEL_PUMP_DIESEL_FILL(CBootstrapIconCSS.BI_FUEL_PUMP_DIESEL_FILL),
  @Deprecated (forRemoval = true, since = "12.3.0")
  FUEL_PUMP_FILL(CBootstrapIconCSS.BI_FUEL_PUMP_FILL),
  @Deprecated (forRemoval = true, since = "12.3.0")
  FULLSCREEN(CBootstrapIconCSS.BI_FULLSCREEN),
  @Deprecated (forRemoval = true, since = "12.3.0")
  FULLSCREEN_EXIT(CBootstrapIconCSS.BI_FULLSCREEN_EXIT),
  @Deprecated (forRemoval = true, since = "12.3.0")
  FUNNEL(CBootstrapIconCSS.BI_FUNNEL),
  @Deprecated (forRemoval = true, since = "12.3.0")
  FUNNEL_FILL(CBootstrapIconCSS.BI_FUNNEL_FILL),
  @Deprecated (forRemoval = true, since = "12.3.0")
  GEAR(CBootstrapIconCSS.BI_GEAR),
  @Deprecated (forRemoval = true, since = "12.3.0")
  GEAR_FILL(CBootstrapIconCSS.BI_GEAR_FILL),
  @Deprecated (forRemoval = true, since = "12.3.0")
  GEAR_WIDE(CBootstrapIconCSS.BI_GEAR_WIDE),
  @Deprecated (forRemoval = true, since = "12.3.0")
  GEAR_WIDE_CONNECTED(CBootstrapIconCSS.BI_GEAR_WIDE_CONNECTED),
  @Deprecated (forRemoval = true, since = "12.3.0")
  GEM(CBootstrapIconCSS.BI_GEM),
  @Deprecated (forRemoval = true, since = "12.3.0")
  GENDER_AMBIGUOUS(CBootstrapIconCSS.BI_GENDER_AMBIGUOUS),
  @Deprecated (forRemoval = true, since = "12.3.0")
  GENDER_FEMALE(CBootstrapIconCSS.BI_GENDER_FEMALE),
  @Deprecated (forRemoval = true, since = "12.3.0")
  GENDER_MALE(CBootstrapIconCSS.BI_GENDER_MALE),
  @Deprecated (forRemoval = true, since = "12.3.0")
  GENDER_NEUTER(CBootstrapIconCSS.BI_GENDER_NEUTER),
  @Deprecated (forRemoval = true, since = "12.3.0")
  GENDER_TRANS(CBootstrapIconCSS.BI_GENDER_TRANS),
  @Deprecated (forRemoval = true, since = "12.3.0")
  GEO(CBootstrapIconCSS.BI_GEO),
  @Deprecated (forRemoval = true, since = "12.3.0")
  GEO_ALT(CBootstrapIconCSS.BI_GEO_ALT),
  @Deprecated (forRemoval = true, since = "12.3.0")
  GEO_ALT_FILL(CBootstrapIconCSS.BI_GEO_ALT_FILL),
  @Deprecated (forRemoval = true, since = "12.3.0")
  GEO_FILL(CBootstrapIconCSS.BI_GEO_FILL),
  @Deprecated (forRemoval = true, since = "12.3.0")
  GIFT(CBootstrapIconCSS.BI_GIFT),
  @Deprecated (forRemoval = true, since = "12.3.0")
  GIFT_FILL(CBootstrapIconCSS.BI_GIFT_FILL),
  @Deprecated (forRemoval = true, since = "12.3.0")
  GIT(CBootstrapIconCSS.BI_GIT),
  @Deprecated (forRemoval = true, since = "12.3.0")
  GITHUB(CBootstrapIconCSS.BI_GITHUB),
  @Deprecated (forRemoval = true, since = "12.3.0")
  GITLAB(CBootstrapIconCSS.BI_GITLAB),
  @Deprecated (forRemoval = true, since = "12.3.0")
  GLOBE(CBootstrapIconCSS.BI_GLOBE),
  @Deprecated (forRemoval = true, since = "12.3.0")
  GLOBE_AMERICAS(CBootstrapIconCSS.BI_GLOBE_AMERICAS),
  @Deprecated (forRemoval = true, since = "12.3.0")
  GLOBE_ASIA_AUSTRALIA(CBootstrapIconCSS.BI_GLOBE_ASIA_AUSTRALIA),
  @Deprecated (forRemoval = true, since = "12.3.0")
  GLOBE_CENTRAL_SOUTH_ASIA(CBootstrapIconCSS.BI_GLOBE_CENTRAL_SOUTH_ASIA),
  @Deprecated (forRemoval = true, since = "12.3.0")
  GLOBE_EUROPE_AFRICA(CBootstrapIconCSS.BI_GLOBE_EUROPE_AFRICA),
  @Deprecated (forRemoval = true, since = "12.3.0")
  GLOBE2(CBootstrapIconCSS.BI_GLOBE2),
  @Deprecated (forRemoval = true, since = "12.3.0")
  GOOGLE(CBootstrapIconCSS.BI_GOOGLE),
  @Deprecated (forRemoval = true, since = "12.3.0")
  GOOGLE_PLAY(CBootstrapIconCSS.BI_GOOGLE_PLAY),
  @Deprecated (forRemoval = true, since = "12.3.0")
  GPU_CARD(CBootstrapIconCSS.BI_GPU_CARD),
  @Deprecated (forRemoval = true, since = "12.3.0")
  GRAPH_DOWN(CBootstrapIconCSS.BI_GRAPH_DOWN),
  @Deprecated (forRemoval = true, since = "12.3.0")
  GRAPH_DOWN_ARROW(CBootstrapIconCSS.BI_GRAPH_DOWN_ARROW),
  @Deprecated (forRemoval = true, since = "12.3.0")
  GRAPH_UP(CBootstrapIconCSS.BI_GRAPH_UP),
  @Deprecated (forRemoval = true, since = "12.3.0")
  GRAPH_UP_ARROW(CBootstrapIconCSS.BI_GRAPH_UP_ARROW),
  @Deprecated (forRemoval = true, since = "12.3.0")
  GRID(CBootstrapIconCSS.BI_GRID),
  @Deprecated (forRemoval = true, since = "12.3.0")
  GRID_1X2(CBootstrapIconCSS.BI_GRID_1X2),
  @Deprecated (forRemoval = true, since = "12.3.0")
  GRID_1X2_FILL(CBootstrapIconCSS.BI_GRID_1X2_FILL),
  @Deprecated (forRemoval = true, since = "12.3.0")
  GRID_3X2(CBootstrapIconCSS.BI_GRID_3X2),
  @Deprecated (forRemoval = true, since = "12.3.0")
  GRID_3X2_GAP(CBootstrapIconCSS.BI_GRID_3X2_GAP),
  @Deprecated (forRemoval = true, since = "12.3.0")
  GRID_3X2_GAP_FILL(CBootstrapIconCSS.BI_GRID_3X2_GAP_FILL),
  @Deprecated (forRemoval = true, since = "12.3.0")
  GRID_3X3(CBootstrapIconCSS.BI_GRID_3X3),
  @Deprecated (forRemoval = true, since = "12.3.0")
  GRID_3X3_GAP(CBootstrapIconCSS.BI_GRID_3X3_GAP),
  @Deprecated (forRemoval = true, since = "12.3.0")
  GRID_3X3_GAP_FILL(CBootstrapIconCSS.BI_GRID_3X3_GAP_FILL),
  @Deprecated (forRemoval = true, since = "12.3.0")
  GRID_FILL(CBootstrapIconCSS.BI_GRID_FILL),
  @Deprecated (forRemoval = true, since = "12.3.0")
  GRIP_HORIZONTAL(CBootstrapIconCSS.BI_GRIP_HORIZONTAL),
  @Deprecated (forRemoval = true, since = "12.3.0")
  GRIP_VERTICAL(CBootstrapIconCSS.BI_GRIP_VERTICAL),
  @Deprecated (forRemoval = true, since = "12.3.0")
  H_CIRCLE(CBootstrapIconCSS.BI_H_CIRCLE),
  @Deprecated (forRemoval = true, since = "12.3.0")
  H_CIRCLE_FILL(CBootstrapIconCSS.BI_H_CIRCLE_FILL),
  @Deprecated (forRemoval = true, since = "12.3.0")
  H_SQUARE(CBootstrapIconCSS.BI_H_SQUARE),
  @Deprecated (forRemoval = true, since = "12.3.0")
  H_SQUARE_FILL(CBootstrapIconCSS.BI_H_SQUARE_FILL),
  @Deprecated (forRemoval = true, since = "12.3.0")
  HAMMER(CBootstrapIconCSS.BI_HAMMER),
  @Deprecated (forRemoval = true, since = "12.3.0")
  HAND_INDEX(CBootstrapIconCSS.BI_HAND_INDEX),
  @Deprecated (forRemoval = true, since = "12.3.0")
  HAND_INDEX_FILL(CBootstrapIconCSS.BI_HAND_INDEX_FILL),
  @Deprecated (forRemoval = true, since = "12.3.0")
  HAND_INDEX_THUMB(CBootstrapIconCSS.BI_HAND_INDEX_THUMB),
  @Deprecated (forRemoval = true, since = "12.3.0")
  HAND_INDEX_THUMB_FILL(CBootstrapIconCSS.BI_HAND_INDEX_THUMB_FILL),
  @Deprecated (forRemoval = true, since = "12.3.0")
  HAND_THUMBS_DOWN(CBootstrapIconCSS.BI_HAND_THUMBS_DOWN),
  @Deprecated (forRemoval = true, since = "12.3.0")
  HAND_THUMBS_DOWN_FILL(CBootstrapIconCSS.BI_HAND_THUMBS_DOWN_FILL),
  @Deprecated (forRemoval = true, since = "12.3.0")
  HAND_THUMBS_UP(CBootstrapIconCSS.BI_HAND_THUMBS_UP),
  @Deprecated (forRemoval = true, since = "12.3.0")
  HAND_THUMBS_UP_FILL(CBootstrapIconCSS.BI_HAND_THUMBS_UP_FILL),
  @Deprecated (forRemoval = true, since = "12.3.0")
  HANDBAG(CBootstrapIconCSS.BI_HANDBAG),
  @Deprecated (forRemoval = true, since = "12.3.0")
  HANDBAG_FILL(CBootstrapIconCSS.BI_HANDBAG_FILL),
  @Deprecated (forRemoval = true, since = "12.3.0")
  HASH(CBootstrapIconCSS.BI_HASH),
  @Deprecated (forRemoval = true, since = "12.3.0")
  HDD(CBootstrapIconCSS.BI_HDD),
  @Deprecated (forRemoval = true, since = "12.3.0")
  HDD_FILL(CBootstrapIconCSS.BI_HDD_FILL),
  @Deprecated (forRemoval = true, since = "12.3.0")
  HDD_NETWORK(CBootstrapIconCSS.BI_HDD_NETWORK),
  @Deprecated (forRemoval = true, since = "12.3.0")
  HDD_NETWORK_FILL(CBootstrapIconCSS.BI_HDD_NETWORK_FILL),
  @Deprecated (forRemoval = true, since = "12.3.0")
  HDD_RACK(CBootstrapIconCSS.BI_HDD_RACK),
  @Deprecated (forRemoval = true, since = "12.3.0")
  HDD_RACK_FILL(CBootstrapIconCSS.BI_HDD_RACK_FILL),
  @Deprecated (forRemoval = true, since = "12.3.0")
  HDD_STACK(CBootstrapIconCSS.BI_HDD_STACK),
  @Deprecated (forRemoval = true, since = "12.3.0")
  HDD_STACK_FILL(CBootstrapIconCSS.BI_HDD_STACK_FILL),
  @Deprecated (forRemoval = true, since = "12.3.0")
  HDMI(CBootstrapIconCSS.BI_HDMI),
  @Deprecated (forRemoval = true, since = "12.3.0")
  HDMI_FILL(CBootstrapIconCSS.BI_HDMI_FILL),
  @Deprecated (forRemoval = true, since = "12.3.0")
  HEADPHONES(CBootstrapIconCSS.BI_HEADPHONES),
  @Deprecated (forRemoval = true, since = "12.3.0")
  HEADSET(CBootstrapIconCSS.BI_HEADSET),
  @Deprecated (forRemoval = true, since = "12.3.0")
  HEADSET_VR(CBootstrapIconCSS.BI_HEADSET_VR),
  @Deprecated (forRemoval = true, since = "12.3.0")
  HEART(CBootstrapIconCSS.BI_HEART),
  @Deprecated (forRemoval = true, since = "12.3.0")
  HEART_ARROW(CBootstrapIconCSS.BI_HEART_ARROW),
  @Deprecated (forRemoval = true, since = "12.3.0")
  HEART_FILL(CBootstrapIconCSS.BI_HEART_FILL),
  @Deprecated (forRemoval = true, since = "12.3.0")
  HEART_HALF(CBootstrapIconCSS.BI_HEART_HALF),
  @Deprecated (forRemoval = true, since = "12.3.0")
  HEART_PULSE(CBootstrapIconCSS.BI_HEART_PULSE),
  @Deprecated (forRemoval = true, since = "12.3.0")
  HEART_PULSE_FILL(CBootstrapIconCSS.BI_HEART_PULSE_FILL),
  @Deprecated (forRemoval = true, since = "12.3.0")
  HEARTBREAK(CBootstrapIconCSS.BI_HEARTBREAK),
  @Deprecated (forRemoval = true, since = "12.3.0")
  HEARTBREAK_FILL(CBootstrapIconCSS.BI_HEARTBREAK_FILL),
  @Deprecated (forRemoval = true, since = "12.3.0")
  HEARTS(CBootstrapIconCSS.BI_HEARTS),
  @Deprecated (forRemoval = true, since = "12.3.0")
  HEPTAGON(CBootstrapIconCSS.BI_HEPTAGON),
  @Deprecated (forRemoval = true, since = "12.3.0")
  HEPTAGON_FILL(CBootstrapIconCSS.BI_HEPTAGON_FILL),
  @Deprecated (forRemoval = true, since = "12.3.0")
  HEPTAGON_HALF(CBootstrapIconCSS.BI_HEPTAGON_HALF),
  @Deprecated (forRemoval = true, since = "12.3.0")
  HEXAGON(CBootstrapIconCSS.BI_HEXAGON),
  @Deprecated (forRemoval = true, since = "12.3.0")
  HEXAGON_FILL(CBootstrapIconCSS.BI_HEXAGON_FILL),
  @Deprecated (forRemoval = true, since = "12.3.0")
  HEXAGON_HALF(CBootstrapIconCSS.BI_HEXAGON_HALF),
  @Deprecated (forRemoval = true, since = "12.3.0")
  HIGHLIGHTER(CBootstrapIconCSS.BI_HIGHLIGHTER),
  @Deprecated (forRemoval = true, since = "12.3.0")
  HIGHLIGHTS(CBootstrapIconCSS.BI_HIGHLIGHTS),
  @Deprecated (forRemoval = true, since = "12.3.0")
  HOSPITAL(CBootstrapIconCSS.BI_HOSPITAL),
  @Deprecated (forRemoval = true, since = "12.3.0")
  HOSPITAL_FILL(CBootstrapIconCSS.BI_HOSPITAL_FILL),
  @Deprecated (forRemoval = true, since = "12.3.0")
  HOURGLASS(CBootstrapIconCSS.BI_HOURGLASS),
  @Deprecated (forRemoval = true, since = "12.3.0")
  HOURGLASS_BOTTOM(CBootstrapIconCSS.BI_HOURGLASS_BOTTOM),
  @Deprecated (forRemoval = true, since = "12.3.0")
  HOURGLASS_SPLIT(CBootstrapIconCSS.BI_HOURGLASS_SPLIT),
  @Deprecated (forRemoval = true, since = "12.3.0")
  HOURGLASS_TOP(CBootstrapIconCSS.BI_HOURGLASS_TOP),
  @Deprecated (forRemoval = true, since = "12.3.0")
  HOUSE(CBootstrapIconCSS.BI_HOUSE),
  @Deprecated (forRemoval = true, since = "12.3.0")
  HOUSE_ADD(CBootstrapIconCSS.BI_HOUSE_ADD),
  @Deprecated (forRemoval = true, since = "12.3.0")
  HOUSE_ADD_FILL(CBootstrapIconCSS.BI_HOUSE_ADD_FILL),
  @Deprecated (forRemoval = true, since = "12.3.0")
  HOUSE_CHECK(CBootstrapIconCSS.BI_HOUSE_CHECK),
  @Deprecated (forRemoval = true, since = "12.3.0")
  HOUSE_CHECK_FILL(CBootstrapIconCSS.BI_HOUSE_CHECK_FILL),
  @Deprecated (forRemoval = true, since = "12.3.0")
  HOUSE_DASH(CBootstrapIconCSS.BI_HOUSE_DASH),
  @Deprecated (forRemoval = true, since = "12.3.0")
  HOUSE_DASH_FILL(CBootstrapIconCSS.BI_HOUSE_DASH_FILL),
  @Deprecated (forRemoval = true, since = "12.3.0")
  HOUSE_DOOR(CBootstrapIconCSS.BI_HOUSE_DOOR),
  @Deprecated (forRemoval = true, since = "12.3.0")
  HOUSE_DOOR_FILL(CBootstrapIconCSS.BI_HOUSE_DOOR_FILL),
  @Deprecated (forRemoval = true, since = "12.3.0")
  HOUSE_DOWN(CBootstrapIconCSS.BI_HOUSE_DOWN),
  @Deprecated (forRemoval = true, since = "12.3.0")
  HOUSE_DOWN_FILL(CBootstrapIconCSS.BI_HOUSE_DOWN_FILL),
  @Deprecated (forRemoval = true, since = "12.3.0")
  HOUSE_EXCLAMATION(CBootstrapIconCSS.BI_HOUSE_EXCLAMATION),
  @Deprecated (forRemoval = true, since = "12.3.0")
  HOUSE_EXCLAMATION_FILL(CBootstrapIconCSS.BI_HOUSE_EXCLAMATION_FILL),
  @Deprecated (forRemoval = true, since = "12.3.0")
  HOUSE_FILL(CBootstrapIconCSS.BI_HOUSE_FILL),
  @Deprecated (forRemoval = true, since = "12.3.0")
  HOUSE_GEAR(CBootstrapIconCSS.BI_HOUSE_GEAR),
  @Deprecated (forRemoval = true, since = "12.3.0")
  HOUSE_GEAR_FILL(CBootstrapIconCSS.BI_HOUSE_GEAR_FILL),
  @Deprecated (forRemoval = true, since = "12.3.0")
  HOUSE_HEART(CBootstrapIconCSS.BI_HOUSE_HEART),
  @Deprecated (forRemoval = true, since = "12.3.0")
  HOUSE_HEART_FILL(CBootstrapIconCSS.BI_HOUSE_HEART_FILL),
  @Deprecated (forRemoval = true, since = "12.3.0")
  HOUSE_LOCK(CBootstrapIconCSS.BI_HOUSE_LOCK),
  @Deprecated (forRemoval = true, since = "12.3.0")
  HOUSE_LOCK_FILL(CBootstrapIconCSS.BI_HOUSE_LOCK_FILL),
  @Deprecated (forRemoval = true, since = "12.3.0")
  HOUSE_SLASH(CBootstrapIconCSS.BI_HOUSE_SLASH),
  @Deprecated (forRemoval = true, since = "12.3.0")
  HOUSE_SLASH_FILL(CBootstrapIconCSS.BI_HOUSE_SLASH_FILL),
  @Deprecated (forRemoval = true, since = "12.3.0")
  HOUSE_UP(CBootstrapIconCSS.BI_HOUSE_UP),
  @Deprecated (forRemoval = true, since = "12.3.0")
  HOUSE_UP_FILL(CBootstrapIconCSS.BI_HOUSE_UP_FILL),
  @Deprecated (forRemoval = true, since = "12.3.0")
  HOUSE_X(CBootstrapIconCSS.BI_HOUSE_X),
  @Deprecated (forRemoval = true, since = "12.3.0")
  HOUSE_X_FILL(CBootstrapIconCSS.BI_HOUSE_X_FILL),
  @Deprecated (forRemoval = true, since = "12.3.0")
  HOUSES(CBootstrapIconCSS.BI_HOUSES),
  @Deprecated (forRemoval = true, since = "12.3.0")
  HOUSES_FILL(CBootstrapIconCSS.BI_HOUSES_FILL),
  @Deprecated (forRemoval = true, since = "12.3.0")
  HR(CBootstrapIconCSS.BI_HR),
  @Deprecated (forRemoval = true, since = "12.3.0")
  HURRICANE(CBootstrapIconCSS.BI_HURRICANE),
  @Deprecated (forRemoval = true, since = "12.3.0")
  HYPNOTIZE(CBootstrapIconCSS.BI_HYPNOTIZE),
  @Deprecated (forRemoval = true, since = "12.3.0")
  IMAGE(CBootstrapIconCSS.BI_IMAGE),
  @Deprecated (forRemoval = true, since = "12.3.0")
  IMAGE_ALT(CBootstrapIconCSS.BI_IMAGE_ALT),
  @Deprecated (forRemoval = true, since = "12.3.0")
  IMAGE_FILL(CBootstrapIconCSS.BI_IMAGE_FILL),
  @Deprecated (forRemoval = true, since = "12.3.0")
  IMAGES(CBootstrapIconCSS.BI_IMAGES),
  @Deprecated (forRemoval = true, since = "12.3.0")
  INBOX(CBootstrapIconCSS.BI_INBOX),
  @Deprecated (forRemoval = true, since = "12.3.0")
  INBOX_FILL(CBootstrapIconCSS.BI_INBOX_FILL),
  @Deprecated (forRemoval = true, since = "12.3.0")
  INBOXES(CBootstrapIconCSS.BI_INBOXES),
  @Deprecated (forRemoval = true, since = "12.3.0")
  INBOXES_FILL(CBootstrapIconCSS.BI_INBOXES_FILL),
  @Deprecated (forRemoval = true, since = "12.3.0")
  INCOGNITO(CBootstrapIconCSS.BI_INCOGNITO),
  @Deprecated (forRemoval = true, since = "12.3.0")
  INDENT(CBootstrapIconCSS.BI_INDENT),
  @Deprecated (forRemoval = true, since = "12.3.0")
  INFINITY(CBootstrapIconCSS.BI_INFINITY),
  @Deprecated (forRemoval = true, since = "12.3.0")
  INFO(CBootstrapIconCSS.BI_INFO),
  @Deprecated (forRemoval = true, since = "12.3.0")
  INFO_CIRCLE(CBootstrapIconCSS.BI_INFO_CIRCLE),
  @Deprecated (forRemoval = true, since = "12.3.0")
  INFO_CIRCLE_FILL(CBootstrapIconCSS.BI_INFO_CIRCLE_FILL),
  @Deprecated (forRemoval = true, since = "12.3.0")
  INFO_LG(CBootstrapIconCSS.BI_INFO_LG),
  @Deprecated (forRemoval = true, since = "12.3.0")
  INFO_SQUARE(CBootstrapIconCSS.BI_INFO_SQUARE),
  @Deprecated (forRemoval = true, since = "12.3.0")
  INFO_SQUARE_FILL(CBootstrapIconCSS.BI_INFO_SQUARE_FILL),
  @Deprecated (forRemoval = true, since = "12.3.0")
  INPUT_CURSOR(CBootstrapIconCSS.BI_INPUT_CURSOR),
  @Deprecated (forRemoval = true, since = "12.3.0")
  INPUT_CURSOR_TEXT(CBootstrapIconCSS.BI_INPUT_CURSOR_TEXT),
  @Deprecated (forRemoval = true, since = "12.3.0")
  INSTAGRAM(CBootstrapIconCSS.BI_INSTAGRAM),
  @Deprecated (forRemoval = true, since = "12.3.0")
  INTERSECT(CBootstrapIconCSS.BI_INTERSECT),
  @Deprecated (forRemoval = true, since = "12.3.0")
  JOURNAL(CBootstrapIconCSS.BI_JOURNAL),
  @Deprecated (forRemoval = true, since = "12.3.0")
  JOURNAL_ALBUM(CBootstrapIconCSS.BI_JOURNAL_ALBUM),
  @Deprecated (forRemoval = true, since = "12.3.0")
  JOURNAL_ARROW_DOWN(CBootstrapIconCSS.BI_JOURNAL_ARROW_DOWN),
  @Deprecated (forRemoval = true, since = "12.3.0")
  JOURNAL_ARROW_UP(CBootstrapIconCSS.BI_JOURNAL_ARROW_UP),
  @Deprecated (forRemoval = true, since = "12.3.0")
  JOURNAL_BOOKMARK(CBootstrapIconCSS.BI_JOURNAL_BOOKMARK),
  @Deprecated (forRemoval = true, since = "12.3.0")
  JOURNAL_BOOKMARK_FILL(CBootstrapIconCSS.BI_JOURNAL_BOOKMARK_FILL),
  @Deprecated (forRemoval = true, since = "12.3.0")
  JOURNAL_CHECK(CBootstrapIconCSS.BI_JOURNAL_CHECK),
  @Deprecated (forRemoval = true, since = "12.3.0")
  JOURNAL_CODE(CBootstrapIconCSS.BI_JOURNAL_CODE),
  @Deprecated (forRemoval = true, since = "12.3.0")
  JOURNAL_MEDICAL(CBootstrapIconCSS.BI_JOURNAL_MEDICAL),
  @Deprecated (forRemoval = true, since = "12.3.0")
  JOURNAL_MINUS(CBootstrapIconCSS.BI_JOURNAL_MINUS),
  @Deprecated (forRemoval = true, since = "12.3.0")
  JOURNAL_PLUS(CBootstrapIconCSS.BI_JOURNAL_PLUS),
  @Deprecated (forRemoval = true, since = "12.3.0")
  JOURNAL_RICHTEXT(CBootstrapIconCSS.BI_JOURNAL_RICHTEXT),
  @Deprecated (forRemoval = true, since = "12.3.0")
  JOURNAL_TEXT(CBootstrapIconCSS.BI_JOURNAL_TEXT),
  @Deprecated (forRemoval = true, since = "12.3.0")
  JOURNAL_X(CBootstrapIconCSS.BI_JOURNAL_X),
  @Deprecated (forRemoval = true, since = "12.3.0")
  JOURNALS(CBootstrapIconCSS.BI_JOURNALS),
  @Deprecated (forRemoval = true, since = "12.3.0")
  JOYSTICK(CBootstrapIconCSS.BI_JOYSTICK),
  @Deprecated (forRemoval = true, since = "12.3.0")
  JUSTIFY(CBootstrapIconCSS.BI_JUSTIFY),
  @Deprecated (forRemoval = true, since = "12.3.0")
  JUSTIFY_LEFT(CBootstrapIconCSS.BI_JUSTIFY_LEFT),
  @Deprecated (forRemoval = true, since = "12.3.0")
  JUSTIFY_RIGHT(CBootstrapIconCSS.BI_JUSTIFY_RIGHT),
  @Deprecated (forRemoval = true, since = "12.3.0")
  KANBAN(CBootstrapIconCSS.BI_KANBAN),
  @Deprecated (forRemoval = true, since = "12.3.0")
  KANBAN_FILL(CBootstrapIconCSS.BI_KANBAN_FILL),
  @Deprecated (forRemoval = true, since = "12.3.0")
  KEY(CBootstrapIconCSS.BI_KEY),
  @Deprecated (forRemoval = true, since = "12.3.0")
  KEY_FILL(CBootstrapIconCSS.BI_KEY_FILL),
  @Deprecated (forRemoval = true, since = "12.3.0")
  KEYBOARD(CBootstrapIconCSS.BI_KEYBOARD),
  @Deprecated (forRemoval = true, since = "12.3.0")
  KEYBOARD_FILL(CBootstrapIconCSS.BI_KEYBOARD_FILL),
  @Deprecated (forRemoval = true, since = "12.3.0")
  LADDER(CBootstrapIconCSS.BI_LADDER),
  @Deprecated (forRemoval = true, since = "12.3.0")
  LAMP(CBootstrapIconCSS.BI_LAMP),
  @Deprecated (forRemoval = true, since = "12.3.0")
  LAMP_FILL(CBootstrapIconCSS.BI_LAMP_FILL),
  @Deprecated (forRemoval = true, since = "12.3.0")
  LAPTOP(CBootstrapIconCSS.BI_LAPTOP),
  @Deprecated (forRemoval = true, since = "12.3.0")
  LAPTOP_FILL(CBootstrapIconCSS.BI_LAPTOP_FILL),
  @Deprecated (forRemoval = true, since = "12.3.0")
  LAYER_BACKWARD(CBootstrapIconCSS.BI_LAYER_BACKWARD),
  @Deprecated (forRemoval = true, since = "12.3.0")
  LAYER_FORWARD(CBootstrapIconCSS.BI_LAYER_FORWARD),
  @Deprecated (forRemoval = true, since = "12.3.0")
  LAYERS(CBootstrapIconCSS.BI_LAYERS),
  @Deprecated (forRemoval = true, since = "12.3.0")
  LAYERS_FILL(CBootstrapIconCSS.BI_LAYERS_FILL),
  @Deprecated (forRemoval = true, since = "12.3.0")
  LAYERS_HALF(CBootstrapIconCSS.BI_LAYERS_HALF),
  @Deprecated (forRemoval = true, since = "12.3.0")
  LAYOUT_SIDEBAR(CBootstrapIconCSS.BI_LAYOUT_SIDEBAR),
  @Deprecated (forRemoval = true, since = "12.3.0")
  LAYOUT_SIDEBAR_INSET(CBootstrapIconCSS.BI_LAYOUT_SIDEBAR_INSET),
  @Deprecated (forRemoval = true, since = "12.3.0")
  LAYOUT_SIDEBAR_INSET_REVERSE(CBootstrapIconCSS.BI_LAYOUT_SIDEBAR_INSET_REVERSE),
  @Deprecated (forRemoval = true, since = "12.3.0")
  LAYOUT_SIDEBAR_REVERSE(CBootstrapIconCSS.BI_LAYOUT_SIDEBAR_REVERSE),
  @Deprecated (forRemoval = true, since = "12.3.0")
  LAYOUT_SPLIT(CBootstrapIconCSS.BI_LAYOUT_SPLIT),
  @Deprecated (forRemoval = true, since = "12.3.0")
  LAYOUT_TEXT_SIDEBAR(CBootstrapIconCSS.BI_LAYOUT_TEXT_SIDEBAR),
  @Deprecated (forRemoval = true, since = "12.3.0")
  LAYOUT_TEXT_SIDEBAR_REVERSE(CBootstrapIconCSS.BI_LAYOUT_TEXT_SIDEBAR_REVERSE),
  @Deprecated (forRemoval = true, since = "12.3.0")
  LAYOUT_TEXT_WINDOW(CBootstrapIconCSS.BI_LAYOUT_TEXT_WINDOW),
  @Deprecated (forRemoval = true, since = "12.3.0")
  LAYOUT_TEXT_WINDOW_REVERSE(CBootstrapIconCSS.BI_LAYOUT_TEXT_WINDOW_REVERSE),
  @Deprecated (forRemoval = true, since = "12.3.0")
  LAYOUT_THREE_COLUMNS(CBootstrapIconCSS.BI_LAYOUT_THREE_COLUMNS),
  @Deprecated (forRemoval = true, since = "12.3.0")
  LAYOUT_WTF(CBootstrapIconCSS.BI_LAYOUT_WTF),
  @Deprecated (forRemoval = true, since = "12.3.0")
  LIFE_PRESERVER(CBootstrapIconCSS.BI_LIFE_PRESERVER),
  @Deprecated (forRemoval = true, since = "12.3.0")
  LIGHTBULB(CBootstrapIconCSS.BI_LIGHTBULB),
  @Deprecated (forRemoval = true, since = "12.3.0")
  LIGHTBULB_FILL(CBootstrapIconCSS.BI_LIGHTBULB_FILL),
  @Deprecated (forRemoval = true, since = "12.3.0")
  LIGHTBULB_OFF(CBootstrapIconCSS.BI_LIGHTBULB_OFF),
  @Deprecated (forRemoval = true, since = "12.3.0")
  LIGHTBULB_OFF_FILL(CBootstrapIconCSS.BI_LIGHTBULB_OFF_FILL),
  @Deprecated (forRemoval = true, since = "12.3.0")
  LIGHTNING(CBootstrapIconCSS.BI_LIGHTNING),
  @Deprecated (forRemoval = true, since = "12.3.0")
  LIGHTNING_CHARGE(CBootstrapIconCSS.BI_LIGHTNING_CHARGE),
  @Deprecated (forRemoval = true, since = "12.3.0")
  LIGHTNING_CHARGE_FILL(CBootstrapIconCSS.BI_LIGHTNING_CHARGE_FILL),
  @Deprecated (forRemoval = true, since = "12.3.0")
  LIGHTNING_FILL(CBootstrapIconCSS.BI_LIGHTNING_FILL),
  @Deprecated (forRemoval = true, since = "12.3.0")
  LINE(CBootstrapIconCSS.BI_LINE),
  @Deprecated (forRemoval = true, since = "12.3.0")
  LINK(CBootstrapIconCSS.BI_LINK),
  @Deprecated (forRemoval = true, since = "12.3.0")
  LINK_45DEG(CBootstrapIconCSS.BI_LINK_45DEG),
  @Deprecated (forRemoval = true, since = "12.3.0")
  LINKEDIN(CBootstrapIconCSS.BI_LINKEDIN),
  @Deprecated (forRemoval = true, since = "12.3.0")
  LIST(CBootstrapIconCSS.BI_LIST),
  @Deprecated (forRemoval = true, since = "12.3.0")
  LIST_CHECK(CBootstrapIconCSS.BI_LIST_CHECK),
  @Deprecated (forRemoval = true, since = "12.3.0")
  LIST_COLUMNS(CBootstrapIconCSS.BI_LIST_COLUMNS),
  @Deprecated (forRemoval = true, since = "12.3.0")
  LIST_COLUMNS_REVERSE(CBootstrapIconCSS.BI_LIST_COLUMNS_REVERSE),
  @Deprecated (forRemoval = true, since = "12.3.0")
  LIST_NESTED(CBootstrapIconCSS.BI_LIST_NESTED),
  @Deprecated (forRemoval = true, since = "12.3.0")
  LIST_OL(CBootstrapIconCSS.BI_LIST_OL),
  @Deprecated (forRemoval = true, since = "12.3.0")
  LIST_STARS(CBootstrapIconCSS.BI_LIST_STARS),
  @Deprecated (forRemoval = true, since = "12.3.0")
  LIST_TASK(CBootstrapIconCSS.BI_LIST_TASK),
  @Deprecated (forRemoval = true, since = "12.3.0")
  LIST_UL(CBootstrapIconCSS.BI_LIST_UL),
  @Deprecated (forRemoval = true, since = "12.3.0")
  LOCK(CBootstrapIconCSS.BI_LOCK),
  @Deprecated (forRemoval = true, since = "12.3.0")
  LOCK_FILL(CBootstrapIconCSS.BI_LOCK_FILL),
  @Deprecated (forRemoval = true, since = "12.3.0")
  LUGGAGE(CBootstrapIconCSS.BI_LUGGAGE),
  @Deprecated (forRemoval = true, since = "12.3.0")
  LUGGAGE_FILL(CBootstrapIconCSS.BI_LUGGAGE_FILL),
  @Deprecated (forRemoval = true, since = "12.3.0")
  LUNGS(CBootstrapIconCSS.BI_LUNGS),
  @Deprecated (forRemoval = true, since = "12.3.0")
  LUNGS_FILL(CBootstrapIconCSS.BI_LUNGS_FILL),
  @Deprecated (forRemoval = true, since = "12.3.0")
  MAGIC(CBootstrapIconCSS.BI_MAGIC),
  @Deprecated (forRemoval = true, since = "12.3.0")
  MAGNET(CBootstrapIconCSS.BI_MAGNET),
  @Deprecated (forRemoval = true, since = "12.3.0")
  MAGNET_FILL(CBootstrapIconCSS.BI_MAGNET_FILL),
  @Deprecated (forRemoval = true, since = "12.3.0")
  MAILBOX(CBootstrapIconCSS.BI_MAILBOX),
  @Deprecated (forRemoval = true, since = "12.3.0")
  MAILBOX_FLAG(CBootstrapIconCSS.BI_MAILBOX_FLAG),
  @Deprecated (forRemoval = true, since = "12.3.0")
  MAILBOX2(CBootstrapIconCSS.BI_MAILBOX2),
  @Deprecated (forRemoval = true, since = "12.3.0")
  MAILBOX2_FLAG(CBootstrapIconCSS.BI_MAILBOX2_FLAG),
  @Deprecated (forRemoval = true, since = "12.3.0")
  MAP(CBootstrapIconCSS.BI_MAP),
  @Deprecated (forRemoval = true, since = "12.3.0")
  MAP_FILL(CBootstrapIconCSS.BI_MAP_FILL),
  @Deprecated (forRemoval = true, since = "12.3.0")
  MARKDOWN(CBootstrapIconCSS.BI_MARKDOWN),
  @Deprecated (forRemoval = true, since = "12.3.0")
  MARKDOWN_FILL(CBootstrapIconCSS.BI_MARKDOWN_FILL),
  @Deprecated (forRemoval = true, since = "12.3.0")
  MARKER_TIP(CBootstrapIconCSS.BI_MARKER_TIP),
  @Deprecated (forRemoval = true, since = "12.3.0")
  MASK(CBootstrapIconCSS.BI_MASK),
  @Deprecated (forRemoval = true, since = "12.3.0")
  MASTODON(CBootstrapIconCSS.BI_MASTODON),
  @Deprecated (forRemoval = true, since = "12.3.0")
  MEDIUM(CBootstrapIconCSS.BI_MEDIUM),
  @Deprecated (forRemoval = true, since = "12.3.0")
  MEGAPHONE(CBootstrapIconCSS.BI_MEGAPHONE),
  @Deprecated (forRemoval = true, since = "12.3.0")
  MEGAPHONE_FILL(CBootstrapIconCSS.BI_MEGAPHONE_FILL),
  @Deprecated (forRemoval = true, since = "12.3.0")
  MEMORY(CBootstrapIconCSS.BI_MEMORY),
  @Deprecated (forRemoval = true, since = "12.3.0")
  MENU_APP(CBootstrapIconCSS.BI_MENU_APP),
  @Deprecated (forRemoval = true, since = "12.3.0")
  MENU_APP_FILL(CBootstrapIconCSS.BI_MENU_APP_FILL),
  @Deprecated (forRemoval = true, since = "12.3.0")
  MENU_BUTTON(CBootstrapIconCSS.BI_MENU_BUTTON),
  @Deprecated (forRemoval = true, since = "12.3.0")
  MENU_BUTTON_FILL(CBootstrapIconCSS.BI_MENU_BUTTON_FILL),
  @Deprecated (forRemoval = true, since = "12.3.0")
  MENU_BUTTON_WIDE(CBootstrapIconCSS.BI_MENU_BUTTON_WIDE),
  @Deprecated (forRemoval = true, since = "12.3.0")
  MENU_BUTTON_WIDE_FILL(CBootstrapIconCSS.BI_MENU_BUTTON_WIDE_FILL),
  @Deprecated (forRemoval = true, since = "12.3.0")
  MENU_DOWN(CBootstrapIconCSS.BI_MENU_DOWN),
  @Deprecated (forRemoval = true, since = "12.3.0")
  MENU_UP(CBootstrapIconCSS.BI_MENU_UP),
  @Deprecated (forRemoval = true, since = "12.3.0")
  MESSENGER(CBootstrapIconCSS.BI_MESSENGER),
  @Deprecated (forRemoval = true, since = "12.3.0")
  META(CBootstrapIconCSS.BI_META),
  @Deprecated (forRemoval = true, since = "12.3.0")
  MIC(CBootstrapIconCSS.BI_MIC),
  @Deprecated (forRemoval = true, since = "12.3.0")
  MIC_FILL(CBootstrapIconCSS.BI_MIC_FILL),
  @Deprecated (forRemoval = true, since = "12.3.0")
  MIC_MUTE(CBootstrapIconCSS.BI_MIC_MUTE),
  @Deprecated (forRemoval = true, since = "12.3.0")
  MIC_MUTE_FILL(CBootstrapIconCSS.BI_MIC_MUTE_FILL),
  @Deprecated (forRemoval = true, since = "12.3.0")
  MICROSOFT(CBootstrapIconCSS.BI_MICROSOFT),
  @Deprecated (forRemoval = true, since = "12.3.0")
  MICROSOFT_TEAMS(CBootstrapIconCSS.BI_MICROSOFT_TEAMS),
  @Deprecated (forRemoval = true, since = "12.3.0")
  MINECART(CBootstrapIconCSS.BI_MINECART),
  @Deprecated (forRemoval = true, since = "12.3.0")
  MINECART_LOADED(CBootstrapIconCSS.BI_MINECART_LOADED),
  @Deprecated (forRemoval = true, since = "12.3.0")
  MODEM(CBootstrapIconCSS.BI_MODEM),
  @Deprecated (forRemoval = true, since = "12.3.0")
  MODEM_FILL(CBootstrapIconCSS.BI_MODEM_FILL),
  @Deprecated (forRemoval = true, since = "12.3.0")
  MOISTURE(CBootstrapIconCSS.BI_MOISTURE),
  @Deprecated (forRemoval = true, since = "12.3.0")
  MOON(CBootstrapIconCSS.BI_MOON),
  @Deprecated (forRemoval = true, since = "12.3.0")
  MOON_FILL(CBootstrapIconCSS.BI_MOON_FILL),
  @Deprecated (forRemoval = true, since = "12.3.0")
  MOON_STARS(CBootstrapIconCSS.BI_MOON_STARS),
  @Deprecated (forRemoval = true, since = "12.3.0")
  MOON_STARS_FILL(CBootstrapIconCSS.BI_MOON_STARS_FILL),
  @Deprecated (forRemoval = true, since = "12.3.0")
  MORTARBOARD(CBootstrapIconCSS.BI_MORTARBOARD),
  @Deprecated (forRemoval = true, since = "12.3.0")
  MORTARBOARD_FILL(CBootstrapIconCSS.BI_MORTARBOARD_FILL),
  @Deprecated (forRemoval = true, since = "12.3.0")
  MOTHERBOARD(CBootstrapIconCSS.BI_MOTHERBOARD),
  @Deprecated (forRemoval = true, since = "12.3.0")
  MOTHERBOARD_FILL(CBootstrapIconCSS.BI_MOTHERBOARD_FILL),
  @Deprecated (forRemoval = true, since = "12.3.0")
  MOUSE(CBootstrapIconCSS.BI_MOUSE),
  @Deprecated (forRemoval = true, since = "12.3.0")
  MOUSE_FILL(CBootstrapIconCSS.BI_MOUSE_FILL),
  @Deprecated (forRemoval = true, since = "12.3.0")
  MOUSE2(CBootstrapIconCSS.BI_MOUSE2),
  @Deprecated (forRemoval = true, since = "12.3.0")
  MOUSE2_FILL(CBootstrapIconCSS.BI_MOUSE2_FILL),
  @Deprecated (forRemoval = true, since = "12.3.0")
  MOUSE3(CBootstrapIconCSS.BI_MOUSE3),
  @Deprecated (forRemoval = true, since = "12.3.0")
  MOUSE3_FILL(CBootstrapIconCSS.BI_MOUSE3_FILL),
  @Deprecated (forRemoval = true, since = "12.3.0")
  MUSIC_NOTE(CBootstrapIconCSS.BI_MUSIC_NOTE),
  @Deprecated (forRemoval = true, since = "12.3.0")
  MUSIC_NOTE_BEAMED(CBootstrapIconCSS.BI_MUSIC_NOTE_BEAMED),
  @Deprecated (forRemoval = true, since = "12.3.0")
  MUSIC_NOTE_LIST(CBootstrapIconCSS.BI_MUSIC_NOTE_LIST),
  @Deprecated (forRemoval = true, since = "12.3.0")
  MUSIC_PLAYER(CBootstrapIconCSS.BI_MUSIC_PLAYER),
  @Deprecated (forRemoval = true, since = "12.3.0")
  MUSIC_PLAYER_FILL(CBootstrapIconCSS.BI_MUSIC_PLAYER_FILL),
  @Deprecated (forRemoval = true, since = "12.3.0")
  NEWSPAPER(CBootstrapIconCSS.BI_NEWSPAPER),
  @Deprecated (forRemoval = true, since = "12.3.0")
  NINTENDO_SWITCH(CBootstrapIconCSS.BI_NINTENDO_SWITCH),
  @Deprecated (forRemoval = true, since = "12.3.0")
  NODE_MINUS(CBootstrapIconCSS.BI_NODE_MINUS),
  @Deprecated (forRemoval = true, since = "12.3.0")
  NODE_MINUS_FILL(CBootstrapIconCSS.BI_NODE_MINUS_FILL),
  @Deprecated (forRemoval = true, since = "12.3.0")
  NODE_PLUS(CBootstrapIconCSS.BI_NODE_PLUS),
  @Deprecated (forRemoval = true, since = "12.3.0")
  NODE_PLUS_FILL(CBootstrapIconCSS.BI_NODE_PLUS_FILL),
  @Deprecated (forRemoval = true, since = "12.3.0")
  NOISE_REDUCTION(CBootstrapIconCSS.BI_NOISE_REDUCTION),
  @Deprecated (forRemoval = true, since = "12.3.0")
  NUT(CBootstrapIconCSS.BI_NUT),
  @Deprecated (forRemoval = true, since = "12.3.0")
  NUT_FILL(CBootstrapIconCSS.BI_NUT_FILL),
  @Deprecated (forRemoval = true, since = "12.3.0")
  NVIDIA(CBootstrapIconCSS.BI_NVIDIA),
  @Deprecated (forRemoval = true, since = "12.3.0")
  NVME(CBootstrapIconCSS.BI_NVME),
  @Deprecated (forRemoval = true, since = "12.3.0")
  NVME_FILL(CBootstrapIconCSS.BI_NVME_FILL),
  @Deprecated (forRemoval = true, since = "12.3.0")
  OCTAGON(CBootstrapIconCSS.BI_OCTAGON),
  @Deprecated (forRemoval = true, since = "12.3.0")
  OCTAGON_FILL(CBootstrapIconCSS.BI_OCTAGON_FILL),
  @Deprecated (forRemoval = true, since = "12.3.0")
  OCTAGON_HALF(CBootstrapIconCSS.BI_OCTAGON_HALF),
  @Deprecated (forRemoval = true, since = "12.3.0")
  OPENCOLLECTIVE(CBootstrapIconCSS.BI_OPENCOLLECTIVE),
  @Deprecated (forRemoval = true, since = "12.3.0")
  OPTICAL_AUDIO(CBootstrapIconCSS.BI_OPTICAL_AUDIO),
  @Deprecated (forRemoval = true, since = "12.3.0")
  OPTICAL_AUDIO_FILL(CBootstrapIconCSS.BI_OPTICAL_AUDIO_FILL),
  @Deprecated (forRemoval = true, since = "12.3.0")
  OPTION(CBootstrapIconCSS.BI_OPTION),
  @Deprecated (forRemoval = true, since = "12.3.0")
  OUTLET(CBootstrapIconCSS.BI_OUTLET),
  @Deprecated (forRemoval = true, since = "12.3.0")
  P_CIRCLE(CBootstrapIconCSS.BI_P_CIRCLE),
  @Deprecated (forRemoval = true, since = "12.3.0")
  P_CIRCLE_FILL(CBootstrapIconCSS.BI_P_CIRCLE_FILL),
  @Deprecated (forRemoval = true, since = "12.3.0")
  P_SQUARE(CBootstrapIconCSS.BI_P_SQUARE),
  @Deprecated (forRemoval = true, since = "12.3.0")
  P_SQUARE_FILL(CBootstrapIconCSS.BI_P_SQUARE_FILL),
  @Deprecated (forRemoval = true, since = "12.3.0")
  PAINT_BUCKET(CBootstrapIconCSS.BI_PAINT_BUCKET),
  @Deprecated (forRemoval = true, since = "12.3.0")
  PALETTE(CBootstrapIconCSS.BI_PALETTE),
  @Deprecated (forRemoval = true, since = "12.3.0")
  PALETTE_FILL(CBootstrapIconCSS.BI_PALETTE_FILL),
  @Deprecated (forRemoval = true, since = "12.3.0")
  PALETTE2(CBootstrapIconCSS.BI_PALETTE2),
  @Deprecated (forRemoval = true, since = "12.3.0")
  PAPERCLIP(CBootstrapIconCSS.BI_PAPERCLIP),
  @Deprecated (forRemoval = true, since = "12.3.0")
  PARAGRAPH(CBootstrapIconCSS.BI_PARAGRAPH),
  @Deprecated (forRemoval = true, since = "12.3.0")
  PASS(CBootstrapIconCSS.BI_PASS),
  @Deprecated (forRemoval = true, since = "12.3.0")
  PASS_FILL(CBootstrapIconCSS.BI_PASS_FILL),
  @Deprecated (forRemoval = true, since = "12.3.0")
  PASSPORT(CBootstrapIconCSS.BI_PASSPORT),
  @Deprecated (forRemoval = true, since = "12.3.0")
  PASSPORT_FILL(CBootstrapIconCSS.BI_PASSPORT_FILL),
  @Deprecated (forRemoval = true, since = "12.3.0")
  PATCH_CHECK(CBootstrapIconCSS.BI_PATCH_CHECK),
  @Deprecated (forRemoval = true, since = "12.3.0")
  PATCH_CHECK_FILL(CBootstrapIconCSS.BI_PATCH_CHECK_FILL),
  @Deprecated (forRemoval = true, since = "12.3.0")
  PATCH_EXCLAMATION(CBootstrapIconCSS.BI_PATCH_EXCLAMATION),
  @Deprecated (forRemoval = true, since = "12.3.0")
  PATCH_EXCLAMATION_FILL(CBootstrapIconCSS.BI_PATCH_EXCLAMATION_FILL),
  @Deprecated (forRemoval = true, since = "12.3.0")
  PATCH_MINUS(CBootstrapIconCSS.BI_PATCH_MINUS),
  @Deprecated (forRemoval = true, since = "12.3.0")
  PATCH_MINUS_FILL(CBootstrapIconCSS.BI_PATCH_MINUS_FILL),
  @Deprecated (forRemoval = true, since = "12.3.0")
  PATCH_PLUS(CBootstrapIconCSS.BI_PATCH_PLUS),
  @Deprecated (forRemoval = true, since = "12.3.0")
  PATCH_PLUS_FILL(CBootstrapIconCSS.BI_PATCH_PLUS_FILL),
  @Deprecated (forRemoval = true, since = "12.3.0")
  PATCH_QUESTION(CBootstrapIconCSS.BI_PATCH_QUESTION),
  @Deprecated (forRemoval = true, since = "12.3.0")
  PATCH_QUESTION_FILL(CBootstrapIconCSS.BI_PATCH_QUESTION_FILL),
  @Deprecated (forRemoval = true, since = "12.3.0")
  PAUSE(CBootstrapIconCSS.BI_PAUSE),
  @Deprecated (forRemoval = true, since = "12.3.0")
  PAUSE_BTN(CBootstrapIconCSS.BI_PAUSE_BTN),
  @Deprecated (forRemoval = true, since = "12.3.0")
  PAUSE_BTN_FILL(CBootstrapIconCSS.BI_PAUSE_BTN_FILL),
  @Deprecated (forRemoval = true, since = "12.3.0")
  PAUSE_CIRCLE(CBootstrapIconCSS.BI_PAUSE_CIRCLE),
  @Deprecated (forRemoval = true, since = "12.3.0")
  PAUSE_CIRCLE_FILL(CBootstrapIconCSS.BI_PAUSE_CIRCLE_FILL),
  @Deprecated (forRemoval = true, since = "12.3.0")
  PAUSE_FILL(CBootstrapIconCSS.BI_PAUSE_FILL),
  @Deprecated (forRemoval = true, since = "12.3.0")
  PAYPAL(CBootstrapIconCSS.BI_PAYPAL),
  @Deprecated (forRemoval = true, since = "12.3.0")
  PC(CBootstrapIconCSS.BI_PC),
  @Deprecated (forRemoval = true, since = "12.3.0")
  PC_DISPLAY(CBootstrapIconCSS.BI_PC_DISPLAY),
  @Deprecated (forRemoval = true, since = "12.3.0")
  PC_DISPLAY_HORIZONTAL(CBootstrapIconCSS.BI_PC_DISPLAY_HORIZONTAL),
  @Deprecated (forRemoval = true, since = "12.3.0")
  PC_HORIZONTAL(CBootstrapIconCSS.BI_PC_HORIZONTAL),
  @Deprecated (forRemoval = true, since = "12.3.0")
  PCI_CARD(CBootstrapIconCSS.BI_PCI_CARD),
  @Deprecated (forRemoval = true, since = "12.3.0")
  PCI_CARD_NETWORK(CBootstrapIconCSS.BI_PCI_CARD_NETWORK),
  @Deprecated (forRemoval = true, since = "12.3.0")
  PCI_CARD_SOUND(CBootstrapIconCSS.BI_PCI_CARD_SOUND),
  @Deprecated (forRemoval = true, since = "12.3.0")
  PEACE(CBootstrapIconCSS.BI_PEACE),
  @Deprecated (forRemoval = true, since = "12.3.0")
  PEACE_FILL(CBootstrapIconCSS.BI_PEACE_FILL),
  @Deprecated (forRemoval = true, since = "12.3.0")
  PEN(CBootstrapIconCSS.BI_PEN),
  @Deprecated (forRemoval = true, since = "12.3.0")
  PEN_FILL(CBootstrapIconCSS.BI_PEN_FILL),
  @Deprecated (forRemoval = true, since = "12.3.0")
  PENCIL(CBootstrapIconCSS.BI_PENCIL),
  @Deprecated (forRemoval = true, since = "12.3.0")
  PENCIL_FILL(CBootstrapIconCSS.BI_PENCIL_FILL),
  @Deprecated (forRemoval = true, since = "12.3.0")
  PENCIL_SQUARE(CBootstrapIconCSS.BI_PENCIL_SQUARE),
  @Deprecated (forRemoval = true, since = "12.3.0")
  PENTAGON(CBootstrapIconCSS.BI_PENTAGON),
  @Deprecated (forRemoval = true, since = "12.3.0")
  PENTAGON_FILL(CBootstrapIconCSS.BI_PENTAGON_FILL),
  @Deprecated (forRemoval = true, since = "12.3.0")
  PENTAGON_HALF(CBootstrapIconCSS.BI_PENTAGON_HALF),
  @Deprecated (forRemoval = true, since = "12.3.0")
  PEOPLE(CBootstrapIconCSS.BI_PEOPLE),
  @Deprecated (forRemoval = true, since = "12.3.0")
  PEOPLE_FILL(CBootstrapIconCSS.BI_PEOPLE_FILL),
  @Deprecated (forRemoval = true, since = "12.3.0")
  PERCENT(CBootstrapIconCSS.BI_PERCENT),
  @Deprecated (forRemoval = true, since = "12.3.0")
  PERSON(CBootstrapIconCSS.BI_PERSON),
  @Deprecated (forRemoval = true, since = "12.3.0")
  PERSON_ADD(CBootstrapIconCSS.BI_PERSON_ADD),
  @Deprecated (forRemoval = true, since = "12.3.0")
  PERSON_ARMS_UP(CBootstrapIconCSS.BI_PERSON_ARMS_UP),
  @Deprecated (forRemoval = true, since = "12.3.0")
  PERSON_BADGE(CBootstrapIconCSS.BI_PERSON_BADGE),
  @Deprecated (forRemoval = true, since = "12.3.0")
  PERSON_BADGE_FILL(CBootstrapIconCSS.BI_PERSON_BADGE_FILL),
  @Deprecated (forRemoval = true, since = "12.3.0")
  PERSON_BOUNDING_BOX(CBootstrapIconCSS.BI_PERSON_BOUNDING_BOX),
  @Deprecated (forRemoval = true, since = "12.3.0")
  PERSON_CHECK(CBootstrapIconCSS.BI_PERSON_CHECK),
  @Deprecated (forRemoval = true, since = "12.3.0")
  PERSON_CHECK_FILL(CBootstrapIconCSS.BI_PERSON_CHECK_FILL),
  @Deprecated (forRemoval = true, since = "12.3.0")
  PERSON_CIRCLE(CBootstrapIconCSS.BI_PERSON_CIRCLE),
  @Deprecated (forRemoval = true, since = "12.3.0")
  PERSON_DASH(CBootstrapIconCSS.BI_PERSON_DASH),
  @Deprecated (forRemoval = true, since = "12.3.0")
  PERSON_DASH_FILL(CBootstrapIconCSS.BI_PERSON_DASH_FILL),
  @Deprecated (forRemoval = true, since = "12.3.0")
  PERSON_DOWN(CBootstrapIconCSS.BI_PERSON_DOWN),
  @Deprecated (forRemoval = true, since = "12.3.0")
  PERSON_EXCLAMATION(CBootstrapIconCSS.BI_PERSON_EXCLAMATION),
  @Deprecated (forRemoval = true, since = "12.3.0")
  PERSON_FILL(CBootstrapIconCSS.BI_PERSON_FILL),
  @Deprecated (forRemoval = true, since = "12.3.0")
  PERSON_FILL_ADD(CBootstrapIconCSS.BI_PERSON_FILL_ADD),
  @Deprecated (forRemoval = true, since = "12.3.0")
  PERSON_FILL_CHECK(CBootstrapIconCSS.BI_PERSON_FILL_CHECK),
  @Deprecated (forRemoval = true, since = "12.3.0")
  PERSON_FILL_DASH(CBootstrapIconCSS.BI_PERSON_FILL_DASH),
  @Deprecated (forRemoval = true, since = "12.3.0")
  PERSON_FILL_DOWN(CBootstrapIconCSS.BI_PERSON_FILL_DOWN),
  @Deprecated (forRemoval = true, since = "12.3.0")
  PERSON_FILL_EXCLAMATION(CBootstrapIconCSS.BI_PERSON_FILL_EXCLAMATION),
  @Deprecated (forRemoval = true, since = "12.3.0")
  PERSON_FILL_GEAR(CBootstrapIconCSS.BI_PERSON_FILL_GEAR),
  @Deprecated (forRemoval = true, since = "12.3.0")
  PERSON_FILL_LOCK(CBootstrapIconCSS.BI_PERSON_FILL_LOCK),
  @Deprecated (forRemoval = true, since = "12.3.0")
  PERSON_FILL_SLASH(CBootstrapIconCSS.BI_PERSON_FILL_SLASH),
  @Deprecated (forRemoval = true, since = "12.3.0")
  PERSON_FILL_UP(CBootstrapIconCSS.BI_PERSON_FILL_UP),
  @Deprecated (forRemoval = true, since = "12.3.0")
  PERSON_FILL_X(CBootstrapIconCSS.BI_PERSON_FILL_X),
  @Deprecated (forRemoval = true, since = "12.3.0")
  PERSON_GEAR(CBootstrapIconCSS.BI_PERSON_GEAR),
  @Deprecated (forRemoval = true, since = "12.3.0")
  PERSON_HEART(CBootstrapIconCSS.BI_PERSON_HEART),
  @Deprecated (forRemoval = true, since = "12.3.0")
  PERSON_HEARTS(CBootstrapIconCSS.BI_PERSON_HEARTS),
  @Deprecated (forRemoval = true, since = "12.3.0")
  PERSON_LINES_FILL(CBootstrapIconCSS.BI_PERSON_LINES_FILL),
  @Deprecated (forRemoval = true, since = "12.3.0")
  PERSON_LOCK(CBootstrapIconCSS.BI_PERSON_LOCK),
  @Deprecated (forRemoval = true, since = "12.3.0")
  PERSON_PLUS(CBootstrapIconCSS.BI_PERSON_PLUS),
  @Deprecated (forRemoval = true, since = "12.3.0")
  PERSON_PLUS_FILL(CBootstrapIconCSS.BI_PERSON_PLUS_FILL),
  @Deprecated (forRemoval = true, since = "12.3.0")
  PERSON_RAISED_HAND(CBootstrapIconCSS.BI_PERSON_RAISED_HAND),
  @Deprecated (forRemoval = true, since = "12.3.0")
  PERSON_ROLODEX(CBootstrapIconCSS.BI_PERSON_ROLODEX),
  @Deprecated (forRemoval = true, since = "12.3.0")
  PERSON_SLASH(CBootstrapIconCSS.BI_PERSON_SLASH),
  @Deprecated (forRemoval = true, since = "12.3.0")
  PERSON_SQUARE(CBootstrapIconCSS.BI_PERSON_SQUARE),
  @Deprecated (forRemoval = true, since = "12.3.0")
  PERSON_STANDING(CBootstrapIconCSS.BI_PERSON_STANDING),
  @Deprecated (forRemoval = true, since = "12.3.0")
  PERSON_STANDING_DRESS(CBootstrapIconCSS.BI_PERSON_STANDING_DRESS),
  @Deprecated (forRemoval = true, since = "12.3.0")
  PERSON_UP(CBootstrapIconCSS.BI_PERSON_UP),
  @Deprecated (forRemoval = true, since = "12.3.0")
  PERSON_VCARD(CBootstrapIconCSS.BI_PERSON_VCARD),
  @Deprecated (forRemoval = true, since = "12.3.0")
  PERSON_VCARD_FILL(CBootstrapIconCSS.BI_PERSON_VCARD_FILL),
  @Deprecated (forRemoval = true, since = "12.3.0")
  PERSON_VIDEO(CBootstrapIconCSS.BI_PERSON_VIDEO),
  @Deprecated (forRemoval = true, since = "12.3.0")
  PERSON_VIDEO2(CBootstrapIconCSS.BI_PERSON_VIDEO2),
  @Deprecated (forRemoval = true, since = "12.3.0")
  PERSON_VIDEO3(CBootstrapIconCSS.BI_PERSON_VIDEO3),
  @Deprecated (forRemoval = true, since = "12.3.0")
  PERSON_WALKING(CBootstrapIconCSS.BI_PERSON_WALKING),
  @Deprecated (forRemoval = true, since = "12.3.0")
  PERSON_WHEELCHAIR(CBootstrapIconCSS.BI_PERSON_WHEELCHAIR),
  @Deprecated (forRemoval = true, since = "12.3.0")
  PERSON_WORKSPACE(CBootstrapIconCSS.BI_PERSON_WORKSPACE),
  @Deprecated (forRemoval = true, since = "12.3.0")
  PERSON_X(CBootstrapIconCSS.BI_PERSON_X),
  @Deprecated (forRemoval = true, since = "12.3.0")
  PERSON_X_FILL(CBootstrapIconCSS.BI_PERSON_X_FILL),
  @Deprecated (forRemoval = true, since = "12.3.0")
  PHONE(CBootstrapIconCSS.BI_PHONE),
  @Deprecated (forRemoval = true, since = "12.3.0")
  PHONE_FILL(CBootstrapIconCSS.BI_PHONE_FILL),
  @Deprecated (forRemoval = true, since = "12.3.0")
  PHONE_FLIP(CBootstrapIconCSS.BI_PHONE_FLIP),
  @Deprecated (forRemoval = true, since = "12.3.0")
  PHONE_LANDSCAPE(CBootstrapIconCSS.BI_PHONE_LANDSCAPE),
  @Deprecated (forRemoval = true, since = "12.3.0")
  PHONE_LANDSCAPE_FILL(CBootstrapIconCSS.BI_PHONE_LANDSCAPE_FILL),
  @Deprecated (forRemoval = true, since = "12.3.0")
  PHONE_VIBRATE(CBootstrapIconCSS.BI_PHONE_VIBRATE),
  @Deprecated (forRemoval = true, since = "12.3.0")
  PHONE_VIBRATE_FILL(CBootstrapIconCSS.BI_PHONE_VIBRATE_FILL),
  @Deprecated (forRemoval = true, since = "12.3.0")
  PIE_CHART(CBootstrapIconCSS.BI_PIE_CHART),
  @Deprecated (forRemoval = true, since = "12.3.0")
  PIE_CHART_FILL(CBootstrapIconCSS.BI_PIE_CHART_FILL),
  @Deprecated (forRemoval = true, since = "12.3.0")
  PIGGY_BANK(CBootstrapIconCSS.BI_PIGGY_BANK),
  @Deprecated (forRemoval = true, since = "12.3.0")
  PIGGY_BANK_FILL(CBootstrapIconCSS.BI_PIGGY_BANK_FILL),
  @Deprecated (forRemoval = true, since = "12.3.0")
  PIN(CBootstrapIconCSS.BI_PIN),
  @Deprecated (forRemoval = true, since = "12.3.0")
  PIN_ANGLE(CBootstrapIconCSS.BI_PIN_ANGLE),
  @Deprecated (forRemoval = true, since = "12.3.0")
  PIN_ANGLE_FILL(CBootstrapIconCSS.BI_PIN_ANGLE_FILL),
  @Deprecated (forRemoval = true, since = "12.3.0")
  PIN_FILL(CBootstrapIconCSS.BI_PIN_FILL),
  @Deprecated (forRemoval = true, since = "12.3.0")
  PIN_MAP(CBootstrapIconCSS.BI_PIN_MAP),
  @Deprecated (forRemoval = true, since = "12.3.0")
  PIN_MAP_FILL(CBootstrapIconCSS.BI_PIN_MAP_FILL),
  @Deprecated (forRemoval = true, since = "12.3.0")
  PINTEREST(CBootstrapIconCSS.BI_PINTEREST),
  @Deprecated (forRemoval = true, since = "12.3.0")
  PIP(CBootstrapIconCSS.BI_PIP),
  @Deprecated (forRemoval = true, since = "12.3.0")
  PIP_FILL(CBootstrapIconCSS.BI_PIP_FILL),
  @Deprecated (forRemoval = true, since = "12.3.0")
  PLAY(CBootstrapIconCSS.BI_PLAY),
  @Deprecated (forRemoval = true, since = "12.3.0")
  PLAY_BTN(CBootstrapIconCSS.BI_PLAY_BTN),
  @Deprecated (forRemoval = true, since = "12.3.0")
  PLAY_BTN_FILL(CBootstrapIconCSS.BI_PLAY_BTN_FILL),
  @Deprecated (forRemoval = true, since = "12.3.0")
  PLAY_CIRCLE(CBootstrapIconCSS.BI_PLAY_CIRCLE),
  @Deprecated (forRemoval = true, since = "12.3.0")
  PLAY_CIRCLE_FILL(CBootstrapIconCSS.BI_PLAY_CIRCLE_FILL),
  @Deprecated (forRemoval = true, since = "12.3.0")
  PLAY_FILL(CBootstrapIconCSS.BI_PLAY_FILL),
  @Deprecated (forRemoval = true, since = "12.3.0")
  PLAYSTATION(CBootstrapIconCSS.BI_PLAYSTATION),
  @Deprecated (forRemoval = true, since = "12.3.0")
  PLUG(CBootstrapIconCSS.BI_PLUG),
  @Deprecated (forRemoval = true, since = "12.3.0")
  PLUG_FILL(CBootstrapIconCSS.BI_PLUG_FILL),
  @Deprecated (forRemoval = true, since = "12.3.0")
  PLUGIN(CBootstrapIconCSS.BI_PLUGIN),
  @Deprecated (forRemoval = true, since = "12.3.0")
  PLUS(CBootstrapIconCSS.BI_PLUS),
  @Deprecated (forRemoval = true, since = "12.3.0")
  PLUS_CIRCLE(CBootstrapIconCSS.BI_PLUS_CIRCLE),
  @Deprecated (forRemoval = true, since = "12.3.0")
  PLUS_CIRCLE_DOTTED(CBootstrapIconCSS.BI_PLUS_CIRCLE_DOTTED),
  @Deprecated (forRemoval = true, since = "12.3.0")
  PLUS_CIRCLE_FILL(CBootstrapIconCSS.BI_PLUS_CIRCLE_FILL),
  @Deprecated (forRemoval = true, since = "12.3.0")
  PLUS_LG(CBootstrapIconCSS.BI_PLUS_LG),
  @Deprecated (forRemoval = true, since = "12.3.0")
  PLUS_SLASH_MINUS(CBootstrapIconCSS.BI_PLUS_SLASH_MINUS),
  @Deprecated (forRemoval = true, since = "12.3.0")
  PLUS_SQUARE(CBootstrapIconCSS.BI_PLUS_SQUARE),
  @Deprecated (forRemoval = true, since = "12.3.0")
  PLUS_SQUARE_DOTTED(CBootstrapIconCSS.BI_PLUS_SQUARE_DOTTED),
  @Deprecated (forRemoval = true, since = "12.3.0")
  PLUS_SQUARE_FILL(CBootstrapIconCSS.BI_PLUS_SQUARE_FILL),
  @Deprecated (forRemoval = true, since = "12.3.0")
  POSTAGE(CBootstrapIconCSS.BI_POSTAGE),
  @Deprecated (forRemoval = true, since = "12.3.0")
  POSTAGE_FILL(CBootstrapIconCSS.BI_POSTAGE_FILL),
  @Deprecated (forRemoval = true, since = "12.3.0")
  POSTAGE_HEART(CBootstrapIconCSS.BI_POSTAGE_HEART),
  @Deprecated (forRemoval = true, since = "12.3.0")
  POSTAGE_HEART_FILL(CBootstrapIconCSS.BI_POSTAGE_HEART_FILL),
  @Deprecated (forRemoval = true, since = "12.3.0")
  POSTCARD(CBootstrapIconCSS.BI_POSTCARD),
  @Deprecated (forRemoval = true, since = "12.3.0")
  POSTCARD_FILL(CBootstrapIconCSS.BI_POSTCARD_FILL),
  @Deprecated (forRemoval = true, since = "12.3.0")
  POSTCARD_HEART(CBootstrapIconCSS.BI_POSTCARD_HEART),
  @Deprecated (forRemoval = true, since = "12.3.0")
  POSTCARD_HEART_FILL(CBootstrapIconCSS.BI_POSTCARD_HEART_FILL),
  @Deprecated (forRemoval = true, since = "12.3.0")
  POWER(CBootstrapIconCSS.BI_POWER),
  @Deprecated (forRemoval = true, since = "12.3.0")
  PRESCRIPTION(CBootstrapIconCSS.BI_PRESCRIPTION),
  @Deprecated (forRemoval = true, since = "12.3.0")
  PRESCRIPTION2(CBootstrapIconCSS.BI_PRESCRIPTION2),
  @Deprecated (forRemoval = true, since = "12.3.0")
  PRINTER(CBootstrapIconCSS.BI_PRINTER),
  @Deprecated (forRemoval = true, since = "12.3.0")
  PRINTER_FILL(CBootstrapIconCSS.BI_PRINTER_FILL),
  @Deprecated (forRemoval = true, since = "12.3.0")
  PROJECTOR(CBootstrapIconCSS.BI_PROJECTOR),
  @Deprecated (forRemoval = true, since = "12.3.0")
  PROJECTOR_FILL(CBootstrapIconCSS.BI_PROJECTOR_FILL),
  @Deprecated (forRemoval = true, since = "12.3.0")
  PUZZLE(CBootstrapIconCSS.BI_PUZZLE),
  @Deprecated (forRemoval = true, since = "12.3.0")
  PUZZLE_FILL(CBootstrapIconCSS.BI_PUZZLE_FILL),
  @Deprecated (forRemoval = true, since = "12.3.0")
  QR_CODE(CBootstrapIconCSS.BI_QR_CODE),
  @Deprecated (forRemoval = true, since = "12.3.0")
  QR_CODE_SCAN(CBootstrapIconCSS.BI_QR_CODE_SCAN),
  @Deprecated (forRemoval = true, since = "12.3.0")
  QUESTION(CBootstrapIconCSS.BI_QUESTION),
  @Deprecated (forRemoval = true, since = "12.3.0")
  QUESTION_CIRCLE(CBootstrapIconCSS.BI_QUESTION_CIRCLE),
  @Deprecated (forRemoval = true, since = "12.3.0")
  QUESTION_CIRCLE_FILL(CBootstrapIconCSS.BI_QUESTION_CIRCLE_FILL),
  @Deprecated (forRemoval = true, since = "12.3.0")
  QUESTION_DIAMOND(CBootstrapIconCSS.BI_QUESTION_DIAMOND),
  @Deprecated (forRemoval = true, since = "12.3.0")
  QUESTION_DIAMOND_FILL(CBootstrapIconCSS.BI_QUESTION_DIAMOND_FILL),
  @Deprecated (forRemoval = true, since = "12.3.0")
  QUESTION_LG(CBootstrapIconCSS.BI_QUESTION_LG),
  @Deprecated (forRemoval = true, since = "12.3.0")
  QUESTION_OCTAGON(CBootstrapIconCSS.BI_QUESTION_OCTAGON),
  @Deprecated (forRemoval = true, since = "12.3.0")
  QUESTION_OCTAGON_FILL(CBootstrapIconCSS.BI_QUESTION_OCTAGON_FILL),
  @Deprecated (forRemoval = true, since = "12.3.0")
  QUESTION_SQUARE(CBootstrapIconCSS.BI_QUESTION_SQUARE),
  @Deprecated (forRemoval = true, since = "12.3.0")
  QUESTION_SQUARE_FILL(CBootstrapIconCSS.BI_QUESTION_SQUARE_FILL),
  @Deprecated (forRemoval = true, since = "12.3.0")
  QUORA(CBootstrapIconCSS.BI_QUORA),
  @Deprecated (forRemoval = true, since = "12.3.0")
  QUOTE(CBootstrapIconCSS.BI_QUOTE),
  @Deprecated (forRemoval = true, since = "12.3.0")
  R_CIRCLE(CBootstrapIconCSS.BI_R_CIRCLE),
  @Deprecated (forRemoval = true, since = "12.3.0")
  R_CIRCLE_FILL(CBootstrapIconCSS.BI_R_CIRCLE_FILL),
  @Deprecated (forRemoval = true, since = "12.3.0")
  R_SQUARE(CBootstrapIconCSS.BI_R_SQUARE),
  @Deprecated (forRemoval = true, since = "12.3.0")
  R_SQUARE_FILL(CBootstrapIconCSS.BI_R_SQUARE_FILL),
  @Deprecated (forRemoval = true, since = "12.3.0")
  RADAR(CBootstrapIconCSS.BI_RADAR),
  @Deprecated (forRemoval = true, since = "12.3.0")
  RADIOACTIVE(CBootstrapIconCSS.BI_RADIOACTIVE),
  @Deprecated (forRemoval = true, since = "12.3.0")
  RAINBOW(CBootstrapIconCSS.BI_RAINBOW),
  @Deprecated (forRemoval = true, since = "12.3.0")
  RECEIPT(CBootstrapIconCSS.BI_RECEIPT),
  @Deprecated (forRemoval = true, since = "12.3.0")
  RECEIPT_CUTOFF(CBootstrapIconCSS.BI_RECEIPT_CUTOFF),
  @Deprecated (forRemoval = true, since = "12.3.0")
  RECEPTION_0(CBootstrapIconCSS.BI_RECEPTION_0),
  @Deprecated (forRemoval = true, since = "12.3.0")
  RECEPTION_1(CBootstrapIconCSS.BI_RECEPTION_1),
  @Deprecated (forRemoval = true, since = "12.3.0")
  RECEPTION_2(CBootstrapIconCSS.BI_RECEPTION_2),
  @Deprecated (forRemoval = true, since = "12.3.0")
  RECEPTION_3(CBootstrapIconCSS.BI_RECEPTION_3),
  @Deprecated (forRemoval = true, since = "12.3.0")
  RECEPTION_4(CBootstrapIconCSS.BI_RECEPTION_4),
  @Deprecated (forRemoval = true, since = "12.3.0")
  RECORD(CBootstrapIconCSS.BI_RECORD),
  @Deprecated (forRemoval = true, since = "12.3.0")
  RECORD_BTN(CBootstrapIconCSS.BI_RECORD_BTN),
  @Deprecated (forRemoval = true, since = "12.3.0")
  RECORD_BTN_FILL(CBootstrapIconCSS.BI_RECORD_BTN_FILL),
  @Deprecated (forRemoval = true, since = "12.3.0")
  RECORD_CIRCLE(CBootstrapIconCSS.BI_RECORD_CIRCLE),
  @Deprecated (forRemoval = true, since = "12.3.0")
  RECORD_CIRCLE_FILL(CBootstrapIconCSS.BI_RECORD_CIRCLE_FILL),
  @Deprecated (forRemoval = true, since = "12.3.0")
  RECORD_FILL(CBootstrapIconCSS.BI_RECORD_FILL),
  @Deprecated (forRemoval = true, since = "12.3.0")
  RECORD2(CBootstrapIconCSS.BI_RECORD2),
  @Deprecated (forRemoval = true, since = "12.3.0")
  RECORD2_FILL(CBootstrapIconCSS.BI_RECORD2_FILL),
  @Deprecated (forRemoval = true, since = "12.3.0")
  RECYCLE(CBootstrapIconCSS.BI_RECYCLE),
  @Deprecated (forRemoval = true, since = "12.3.0")
  REDDIT(CBootstrapIconCSS.BI_REDDIT),
  @Deprecated (forRemoval = true, since = "12.3.0")
  REGEX(CBootstrapIconCSS.BI_REGEX),
  @Deprecated (forRemoval = true, since = "12.3.0")
  REPEAT(CBootstrapIconCSS.BI_REPEAT),
  @Deprecated (forRemoval = true, since = "12.3.0")
  REPEAT_1(CBootstrapIconCSS.BI_REPEAT_1),
  @Deprecated (forRemoval = true, since = "12.3.0")
  REPLY(CBootstrapIconCSS.BI_REPLY),
  @Deprecated (forRemoval = true, since = "12.3.0")
  REPLY_ALL(CBootstrapIconCSS.BI_REPLY_ALL),
  @Deprecated (forRemoval = true, since = "12.3.0")
  REPLY_ALL_FILL(CBootstrapIconCSS.BI_REPLY_ALL_FILL),
  @Deprecated (forRemoval = true, since = "12.3.0")
  REPLY_FILL(CBootstrapIconCSS.BI_REPLY_FILL),
  @Deprecated (forRemoval = true, since = "12.3.0")
  REWIND(CBootstrapIconCSS.BI_REWIND),
  @Deprecated (forRemoval = true, since = "12.3.0")
  REWIND_BTN(CBootstrapIconCSS.BI_REWIND_BTN),
  @Deprecated (forRemoval = true, since = "12.3.0")
  REWIND_BTN_FILL(CBootstrapIconCSS.BI_REWIND_BTN_FILL),
  @Deprecated (forRemoval = true, since = "12.3.0")
  REWIND_CIRCLE(CBootstrapIconCSS.BI_REWIND_CIRCLE),
  @Deprecated (forRemoval = true, since = "12.3.0")
  REWIND_CIRCLE_FILL(CBootstrapIconCSS.BI_REWIND_CIRCLE_FILL),
  @Deprecated (forRemoval = true, since = "12.3.0")
  REWIND_FILL(CBootstrapIconCSS.BI_REWIND_FILL),
  @Deprecated (forRemoval = true, since = "12.3.0")
  ROBOT(CBootstrapIconCSS.BI_ROBOT),
  @Deprecated (forRemoval = true, since = "12.3.0")
  ROCKET(CBootstrapIconCSS.BI_ROCKET),
  @Deprecated (forRemoval = true, since = "12.3.0")
  ROCKET_FILL(CBootstrapIconCSS.BI_ROCKET_FILL),
  @Deprecated (forRemoval = true, since = "12.3.0")
  ROCKET_TAKEOFF(CBootstrapIconCSS.BI_ROCKET_TAKEOFF),
  @Deprecated (forRemoval = true, since = "12.3.0")
  ROCKET_TAKEOFF_FILL(CBootstrapIconCSS.BI_ROCKET_TAKEOFF_FILL),
  @Deprecated (forRemoval = true, since = "12.3.0")
  ROUTER(CBootstrapIconCSS.BI_ROUTER),
  @Deprecated (forRemoval = true, since = "12.3.0")
  ROUTER_FILL(CBootstrapIconCSS.BI_ROUTER_FILL),
  @Deprecated (forRemoval = true, since = "12.3.0")
  RSS(CBootstrapIconCSS.BI_RSS),
  @Deprecated (forRemoval = true, since = "12.3.0")
  RSS_FILL(CBootstrapIconCSS.BI_RSS_FILL),
  @Deprecated (forRemoval = true, since = "12.3.0")
  RULERS(CBootstrapIconCSS.BI_RULERS),
  @Deprecated (forRemoval = true, since = "12.3.0")
  SAFE(CBootstrapIconCSS.BI_SAFE),
  @Deprecated (forRemoval = true, since = "12.3.0")
  SAFE_FILL(CBootstrapIconCSS.BI_SAFE_FILL),
  @Deprecated (forRemoval = true, since = "12.3.0")
  SAFE2(CBootstrapIconCSS.BI_SAFE2),
  @Deprecated (forRemoval = true, since = "12.3.0")
  SAFE2_FILL(CBootstrapIconCSS.BI_SAFE2_FILL),
  @Deprecated (forRemoval = true, since = "12.3.0")
  SAVE(CBootstrapIconCSS.BI_SAVE),
  @Deprecated (forRemoval = true, since = "12.3.0")
  SAVE_FILL(CBootstrapIconCSS.BI_SAVE_FILL),
  @Deprecated (forRemoval = true, since = "12.3.0")
  SAVE2(CBootstrapIconCSS.BI_SAVE2),
  @Deprecated (forRemoval = true, since = "12.3.0")
  SAVE2_FILL(CBootstrapIconCSS.BI_SAVE2_FILL),
  @Deprecated (forRemoval = true, since = "12.3.0")
  SCISSORS(CBootstrapIconCSS.BI_SCISSORS),
  @Deprecated (forRemoval = true, since = "12.3.0")
  SCOOTER(CBootstrapIconCSS.BI_SCOOTER),
  @Deprecated (forRemoval = true, since = "12.3.0")
  SCREWDRIVER(CBootstrapIconCSS.BI_SCREWDRIVER),
  @Deprecated (forRemoval = true, since = "12.3.0")
  SD_CARD(CBootstrapIconCSS.BI_SD_CARD),
  @Deprecated (forRemoval = true, since = "12.3.0")
  SD_CARD_FILL(CBootstrapIconCSS.BI_SD_CARD_FILL),
  @Deprecated (forRemoval = true, since = "12.3.0")
  SEARCH(CBootstrapIconCSS.BI_SEARCH),
  @Deprecated (forRemoval = true, since = "12.3.0")
  SEARCH_HEART(CBootstrapIconCSS.BI_SEARCH_HEART),
  @Deprecated (forRemoval = true, since = "12.3.0")
  SEARCH_HEART_FILL(CBootstrapIconCSS.BI_SEARCH_HEART_FILL),
  @Deprecated (forRemoval = true, since = "12.3.0")
  SEGMENTED_NAV(CBootstrapIconCSS.BI_SEGMENTED_NAV),
  @Deprecated (forRemoval = true, since = "12.3.0")
  SEND(CBootstrapIconCSS.BI_SEND),
  @Deprecated (forRemoval = true, since = "12.3.0")
  SEND_ARROW_DOWN(CBootstrapIconCSS.BI_SEND_ARROW_DOWN),
  @Deprecated (forRemoval = true, since = "12.3.0")
  SEND_ARROW_DOWN_FILL(CBootstrapIconCSS.BI_SEND_ARROW_DOWN_FILL),
  @Deprecated (forRemoval = true, since = "12.3.0")
  SEND_ARROW_UP(CBootstrapIconCSS.BI_SEND_ARROW_UP),
  @Deprecated (forRemoval = true, since = "12.3.0")
  SEND_ARROW_UP_FILL(CBootstrapIconCSS.BI_SEND_ARROW_UP_FILL),
  @Deprecated (forRemoval = true, since = "12.3.0")
  SEND_CHECK(CBootstrapIconCSS.BI_SEND_CHECK),
  @Deprecated (forRemoval = true, since = "12.3.0")
  SEND_CHECK_FILL(CBootstrapIconCSS.BI_SEND_CHECK_FILL),
  @Deprecated (forRemoval = true, since = "12.3.0")
  SEND_DASH(CBootstrapIconCSS.BI_SEND_DASH),
  @Deprecated (forRemoval = true, since = "12.3.0")
  SEND_DASH_FILL(CBootstrapIconCSS.BI_SEND_DASH_FILL),
  @Deprecated (forRemoval = true, since = "12.3.0")
  SEND_EXCLAMATION(CBootstrapIconCSS.BI_SEND_EXCLAMATION),
  @Deprecated (forRemoval = true, since = "12.3.0")
  SEND_EXCLAMATION_FILL(CBootstrapIconCSS.BI_SEND_EXCLAMATION_FILL),
  @Deprecated (forRemoval = true, since = "12.3.0")
  SEND_FILL(CBootstrapIconCSS.BI_SEND_FILL),
  @Deprecated (forRemoval = true, since = "12.3.0")
  SEND_PLUS(CBootstrapIconCSS.BI_SEND_PLUS),
  @Deprecated (forRemoval = true, since = "12.3.0")
  SEND_PLUS_FILL(CBootstrapIconCSS.BI_SEND_PLUS_FILL),
  @Deprecated (forRemoval = true, since = "12.3.0")
  SEND_SLASH(CBootstrapIconCSS.BI_SEND_SLASH),
  @Deprecated (forRemoval = true, since = "12.3.0")
  SEND_SLASH_FILL(CBootstrapIconCSS.BI_SEND_SLASH_FILL),
  @Deprecated (forRemoval = true, since = "12.3.0")
  SEND_X(CBootstrapIconCSS.BI_SEND_X),
  @Deprecated (forRemoval = true, since = "12.3.0")
  SEND_X_FILL(CBootstrapIconCSS.BI_SEND_X_FILL),
  @Deprecated (forRemoval = true, since = "12.3.0")
  SERVER(CBootstrapIconCSS.BI_SERVER),
  @Deprecated (forRemoval = true, since = "12.3.0")
  SHADOWS(CBootstrapIconCSS.BI_SHADOWS),
  @Deprecated (forRemoval = true, since = "12.3.0")
  SHARE(CBootstrapIconCSS.BI_SHARE),
  @Deprecated (forRemoval = true, since = "12.3.0")
  SHARE_FILL(CBootstrapIconCSS.BI_SHARE_FILL),
  @Deprecated (forRemoval = true, since = "12.3.0")
  SHIELD(CBootstrapIconCSS.BI_SHIELD),
  @Deprecated (forRemoval = true, since = "12.3.0")
  SHIELD_CHECK(CBootstrapIconCSS.BI_SHIELD_CHECK),
  @Deprecated (forRemoval = true, since = "12.3.0")
  SHIELD_EXCLAMATION(CBootstrapIconCSS.BI_SHIELD_EXCLAMATION),
  @Deprecated (forRemoval = true, since = "12.3.0")
  SHIELD_FILL(CBootstrapIconCSS.BI_SHIELD_FILL),
  @Deprecated (forRemoval = true, since = "12.3.0")
  SHIELD_FILL_CHECK(CBootstrapIconCSS.BI_SHIELD_FILL_CHECK),
  @Deprecated (forRemoval = true, since = "12.3.0")
  SHIELD_FILL_EXCLAMATION(CBootstrapIconCSS.BI_SHIELD_FILL_EXCLAMATION),
  @Deprecated (forRemoval = true, since = "12.3.0")
  SHIELD_FILL_MINUS(CBootstrapIconCSS.BI_SHIELD_FILL_MINUS),
  @Deprecated (forRemoval = true, since = "12.3.0")
  SHIELD_FILL_PLUS(CBootstrapIconCSS.BI_SHIELD_FILL_PLUS),
  @Deprecated (forRemoval = true, since = "12.3.0")
  SHIELD_FILL_X(CBootstrapIconCSS.BI_SHIELD_FILL_X),
  @Deprecated (forRemoval = true, since = "12.3.0")
  SHIELD_LOCK(CBootstrapIconCSS.BI_SHIELD_LOCK),
  @Deprecated (forRemoval = true, since = "12.3.0")
  SHIELD_LOCK_FILL(CBootstrapIconCSS.BI_SHIELD_LOCK_FILL),
  @Deprecated (forRemoval = true, since = "12.3.0")
  SHIELD_MINUS(CBootstrapIconCSS.BI_SHIELD_MINUS),
  @Deprecated (forRemoval = true, since = "12.3.0")
  SHIELD_PLUS(CBootstrapIconCSS.BI_SHIELD_PLUS),
  @Deprecated (forRemoval = true, since = "12.3.0")
  SHIELD_SHADED(CBootstrapIconCSS.BI_SHIELD_SHADED),
  @Deprecated (forRemoval = true, since = "12.3.0")
  SHIELD_SLASH(CBootstrapIconCSS.BI_SHIELD_SLASH),
  @Deprecated (forRemoval = true, since = "12.3.0")
  SHIELD_SLASH_FILL(CBootstrapIconCSS.BI_SHIELD_SLASH_FILL),
  @Deprecated (forRemoval = true, since = "12.3.0")
  SHIELD_X(CBootstrapIconCSS.BI_SHIELD_X),
  @Deprecated (forRemoval = true, since = "12.3.0")
  SHIFT(CBootstrapIconCSS.BI_SHIFT),
  @Deprecated (forRemoval = true, since = "12.3.0")
  SHIFT_FILL(CBootstrapIconCSS.BI_SHIFT_FILL),
  @Deprecated (forRemoval = true, since = "12.3.0")
  SHOP(CBootstrapIconCSS.BI_SHOP),
  @Deprecated (forRemoval = true, since = "12.3.0")
  SHOP_WINDOW(CBootstrapIconCSS.BI_SHOP_WINDOW),
  @Deprecated (forRemoval = true, since = "12.3.0")
  SHUFFLE(CBootstrapIconCSS.BI_SHUFFLE),
  @Deprecated (forRemoval = true, since = "12.3.0")
  SIGN_DEAD_END(CBootstrapIconCSS.BI_SIGN_DEAD_END),
  @Deprecated (forRemoval = true, since = "12.3.0")
  SIGN_DEAD_END_FILL(CBootstrapIconCSS.BI_SIGN_DEAD_END_FILL),
  @Deprecated (forRemoval = true, since = "12.3.0")
  SIGN_DO_NOT_ENTER(CBootstrapIconCSS.BI_SIGN_DO_NOT_ENTER),
  @Deprecated (forRemoval = true, since = "12.3.0")
  SIGN_DO_NOT_ENTER_FILL(CBootstrapIconCSS.BI_SIGN_DO_NOT_ENTER_FILL),
  @Deprecated (forRemoval = true, since = "12.3.0")
  SIGN_INTERSECTION(CBootstrapIconCSS.BI_SIGN_INTERSECTION),
  @Deprecated (forRemoval = true, since = "12.3.0")
  SIGN_INTERSECTION_FILL(CBootstrapIconCSS.BI_SIGN_INTERSECTION_FILL),
  @Deprecated (forRemoval = true, since = "12.3.0")
  SIGN_INTERSECTION_SIDE(CBootstrapIconCSS.BI_SIGN_INTERSECTION_SIDE),
  @Deprecated (forRemoval = true, since = "12.3.0")
  SIGN_INTERSECTION_SIDE_FILL(CBootstrapIconCSS.BI_SIGN_INTERSECTION_SIDE_FILL),
  @Deprecated (forRemoval = true, since = "12.3.0")
  SIGN_INTERSECTION_T(CBootstrapIconCSS.BI_SIGN_INTERSECTION_T),
  @Deprecated (forRemoval = true, since = "12.3.0")
  SIGN_INTERSECTION_T_FILL(CBootstrapIconCSS.BI_SIGN_INTERSECTION_T_FILL),
  @Deprecated (forRemoval = true, since = "12.3.0")
  SIGN_INTERSECTION_Y(CBootstrapIconCSS.BI_SIGN_INTERSECTION_Y),
  @Deprecated (forRemoval = true, since = "12.3.0")
  SIGN_INTERSECTION_Y_FILL(CBootstrapIconCSS.BI_SIGN_INTERSECTION_Y_FILL),
  @Deprecated (forRemoval = true, since = "12.3.0")
  SIGN_MERGE_LEFT(CBootstrapIconCSS.BI_SIGN_MERGE_LEFT),
  @Deprecated (forRemoval = true, since = "12.3.0")
  SIGN_MERGE_LEFT_FILL(CBootstrapIconCSS.BI_SIGN_MERGE_LEFT_FILL),
  @Deprecated (forRemoval = true, since = "12.3.0")
  SIGN_MERGE_RIGHT(CBootstrapIconCSS.BI_SIGN_MERGE_RIGHT),
  @Deprecated (forRemoval = true, since = "12.3.0")
  SIGN_MERGE_RIGHT_FILL(CBootstrapIconCSS.BI_SIGN_MERGE_RIGHT_FILL),
  @Deprecated (forRemoval = true, since = "12.3.0")
  SIGN_NO_LEFT_TURN(CBootstrapIconCSS.BI_SIGN_NO_LEFT_TURN),
  @Deprecated (forRemoval = true, since = "12.3.0")
  SIGN_NO_LEFT_TURN_FILL(CBootstrapIconCSS.BI_SIGN_NO_LEFT_TURN_FILL),
  @Deprecated (forRemoval = true, since = "12.3.0")
  SIGN_NO_PARKING(CBootstrapIconCSS.BI_SIGN_NO_PARKING),
  @Deprecated (forRemoval = true, since = "12.3.0")
  SIGN_NO_PARKING_FILL(CBootstrapIconCSS.BI_SIGN_NO_PARKING_FILL),
  @Deprecated (forRemoval = true, since = "12.3.0")
  SIGN_NO_RIGHT_TURN(CBootstrapIconCSS.BI_SIGN_NO_RIGHT_TURN),
  @Deprecated (forRemoval = true, since = "12.3.0")
  SIGN_NO_RIGHT_TURN_FILL(CBootstrapIconCSS.BI_SIGN_NO_RIGHT_TURN_FILL),
  @Deprecated (forRemoval = true, since = "12.3.0")
  SIGN_RAILROAD(CBootstrapIconCSS.BI_SIGN_RAILROAD),
  @Deprecated (forRemoval = true, since = "12.3.0")
  SIGN_RAILROAD_FILL(CBootstrapIconCSS.BI_SIGN_RAILROAD_FILL),
  @Deprecated (forRemoval = true, since = "12.3.0")
  SIGN_STOP(CBootstrapIconCSS.BI_SIGN_STOP),
  @Deprecated (forRemoval = true, since = "12.3.0")
  SIGN_STOP_FILL(CBootstrapIconCSS.BI_SIGN_STOP_FILL),
  @Deprecated (forRemoval = true, since = "12.3.0")
  SIGN_STOP_LIGHTS(CBootstrapIconCSS.BI_SIGN_STOP_LIGHTS),
  @Deprecated (forRemoval = true, since = "12.3.0")
  SIGN_STOP_LIGHTS_FILL(CBootstrapIconCSS.BI_SIGN_STOP_LIGHTS_FILL),
  @Deprecated (forRemoval = true, since = "12.3.0")
  SIGN_TURN_LEFT(CBootstrapIconCSS.BI_SIGN_TURN_LEFT),
  @Deprecated (forRemoval = true, since = "12.3.0")
  SIGN_TURN_LEFT_FILL(CBootstrapIconCSS.BI_SIGN_TURN_LEFT_FILL),
  @Deprecated (forRemoval = true, since = "12.3.0")
  SIGN_TURN_RIGHT(CBootstrapIconCSS.BI_SIGN_TURN_RIGHT),
  @Deprecated (forRemoval = true, since = "12.3.0")
  SIGN_TURN_RIGHT_FILL(CBootstrapIconCSS.BI_SIGN_TURN_RIGHT_FILL),
  @Deprecated (forRemoval = true, since = "12.3.0")
  SIGN_TURN_SLIGHT_LEFT(CBootstrapIconCSS.BI_SIGN_TURN_SLIGHT_LEFT),
  @Deprecated (forRemoval = true, since = "12.3.0")
  SIGN_TURN_SLIGHT_LEFT_FILL(CBootstrapIconCSS.BI_SIGN_TURN_SLIGHT_LEFT_FILL),
  @Deprecated (forRemoval = true, since = "12.3.0")
  SIGN_TURN_SLIGHT_RIGHT(CBootstrapIconCSS.BI_SIGN_TURN_SLIGHT_RIGHT),
  @Deprecated (forRemoval = true, since = "12.3.0")
  SIGN_TURN_SLIGHT_RIGHT_FILL(CBootstrapIconCSS.BI_SIGN_TURN_SLIGHT_RIGHT_FILL),
  @Deprecated (forRemoval = true, since = "12.3.0")
  SIGN_YIELD(CBootstrapIconCSS.BI_SIGN_YIELD),
  @Deprecated (forRemoval = true, since = "12.3.0")
  SIGN_YIELD_FILL(CBootstrapIconCSS.BI_SIGN_YIELD_FILL),
  @Deprecated (forRemoval = true, since = "12.3.0")
  SIGNAL(CBootstrapIconCSS.BI_SIGNAL),
  @Deprecated (forRemoval = true, since = "12.3.0")
  SIGNPOST(CBootstrapIconCSS.BI_SIGNPOST),
  @Deprecated (forRemoval = true, since = "12.3.0")
  SIGNPOST_2(CBootstrapIconCSS.BI_SIGNPOST_2),
  @Deprecated (forRemoval = true, since = "12.3.0")
  SIGNPOST_2_FILL(CBootstrapIconCSS.BI_SIGNPOST_2_FILL),
  @Deprecated (forRemoval = true, since = "12.3.0")
  SIGNPOST_FILL(CBootstrapIconCSS.BI_SIGNPOST_FILL),
  @Deprecated (forRemoval = true, since = "12.3.0")
  SIGNPOST_SPLIT(CBootstrapIconCSS.BI_SIGNPOST_SPLIT),
  @Deprecated (forRemoval = true, since = "12.3.0")
  SIGNPOST_SPLIT_FILL(CBootstrapIconCSS.BI_SIGNPOST_SPLIT_FILL),
  @Deprecated (forRemoval = true, since = "12.3.0")
  SIM(CBootstrapIconCSS.BI_SIM),
  @Deprecated (forRemoval = true, since = "12.3.0")
  SIM_FILL(CBootstrapIconCSS.BI_SIM_FILL),
  @Deprecated (forRemoval = true, since = "12.3.0")
  SIM_SLASH(CBootstrapIconCSS.BI_SIM_SLASH),
  @Deprecated (forRemoval = true, since = "12.3.0")
  SIM_SLASH_FILL(CBootstrapIconCSS.BI_SIM_SLASH_FILL),
  @Deprecated (forRemoval = true, since = "12.3.0")
  SINA_WEIBO(CBootstrapIconCSS.BI_SINA_WEIBO),
  @Deprecated (forRemoval = true, since = "12.3.0")
  SKIP_BACKWARD(CBootstrapIconCSS.BI_SKIP_BACKWARD),
  @Deprecated (forRemoval = true, since = "12.3.0")
  SKIP_BACKWARD_BTN(CBootstrapIconCSS.BI_SKIP_BACKWARD_BTN),
  @Deprecated (forRemoval = true, since = "12.3.0")
  SKIP_BACKWARD_BTN_FILL(CBootstrapIconCSS.BI_SKIP_BACKWARD_BTN_FILL),
  @Deprecated (forRemoval = true, since = "12.3.0")
  SKIP_BACKWARD_CIRCLE(CBootstrapIconCSS.BI_SKIP_BACKWARD_CIRCLE),
  @Deprecated (forRemoval = true, since = "12.3.0")
  SKIP_BACKWARD_CIRCLE_FILL(CBootstrapIconCSS.BI_SKIP_BACKWARD_CIRCLE_FILL),
  @Deprecated (forRemoval = true, since = "12.3.0")
  SKIP_BACKWARD_FILL(CBootstrapIconCSS.BI_SKIP_BACKWARD_FILL),
  @Deprecated (forRemoval = true, since = "12.3.0")
  SKIP_END(CBootstrapIconCSS.BI_SKIP_END),
  @Deprecated (forRemoval = true, since = "12.3.0")
  SKIP_END_BTN(CBootstrapIconCSS.BI_SKIP_END_BTN),
  @Deprecated (forRemoval = true, since = "12.3.0")
  SKIP_END_BTN_FILL(CBootstrapIconCSS.BI_SKIP_END_BTN_FILL),
  @Deprecated (forRemoval = true, since = "12.3.0")
  SKIP_END_CIRCLE(CBootstrapIconCSS.BI_SKIP_END_CIRCLE),
  @Deprecated (forRemoval = true, since = "12.3.0")
  SKIP_END_CIRCLE_FILL(CBootstrapIconCSS.BI_SKIP_END_CIRCLE_FILL),
  @Deprecated (forRemoval = true, since = "12.3.0")
  SKIP_END_FILL(CBootstrapIconCSS.BI_SKIP_END_FILL),
  @Deprecated (forRemoval = true, since = "12.3.0")
  SKIP_FORWARD(CBootstrapIconCSS.BI_SKIP_FORWARD),
  @Deprecated (forRemoval = true, since = "12.3.0")
  SKIP_FORWARD_BTN(CBootstrapIconCSS.BI_SKIP_FORWARD_BTN),
  @Deprecated (forRemoval = true, since = "12.3.0")
  SKIP_FORWARD_BTN_FILL(CBootstrapIconCSS.BI_SKIP_FORWARD_BTN_FILL),
  @Deprecated (forRemoval = true, since = "12.3.0")
  SKIP_FORWARD_CIRCLE(CBootstrapIconCSS.BI_SKIP_FORWARD_CIRCLE),
  @Deprecated (forRemoval = true, since = "12.3.0")
  SKIP_FORWARD_CIRCLE_FILL(CBootstrapIconCSS.BI_SKIP_FORWARD_CIRCLE_FILL),
  @Deprecated (forRemoval = true, since = "12.3.0")
  SKIP_FORWARD_FILL(CBootstrapIconCSS.BI_SKIP_FORWARD_FILL),
  @Deprecated (forRemoval = true, since = "12.3.0")
  SKIP_START(CBootstrapIconCSS.BI_SKIP_START),
  @Deprecated (forRemoval = true, since = "12.3.0")
  SKIP_START_BTN(CBootstrapIconCSS.BI_SKIP_START_BTN),
  @Deprecated (forRemoval = true, since = "12.3.0")
  SKIP_START_BTN_FILL(CBootstrapIconCSS.BI_SKIP_START_BTN_FILL),
  @Deprecated (forRemoval = true, since = "12.3.0")
  SKIP_START_CIRCLE(CBootstrapIconCSS.BI_SKIP_START_CIRCLE),
  @Deprecated (forRemoval = true, since = "12.3.0")
  SKIP_START_CIRCLE_FILL(CBootstrapIconCSS.BI_SKIP_START_CIRCLE_FILL),
  @Deprecated (forRemoval = true, since = "12.3.0")
  SKIP_START_FILL(CBootstrapIconCSS.BI_SKIP_START_FILL),
  @Deprecated (forRemoval = true, since = "12.3.0")
  SKYPE(CBootstrapIconCSS.BI_SKYPE),
  @Deprecated (forRemoval = true, since = "12.3.0")
  SLACK(CBootstrapIconCSS.BI_SLACK),
  @Deprecated (forRemoval = true, since = "12.3.0")
  SLASH(CBootstrapIconCSS.BI_SLASH),
  @Deprecated (forRemoval = true, since = "12.3.0")
  SLASH_CIRCLE(CBootstrapIconCSS.BI_SLASH_CIRCLE),
  @Deprecated (forRemoval = true, since = "12.3.0")
  SLASH_CIRCLE_FILL(CBootstrapIconCSS.BI_SLASH_CIRCLE_FILL),
  @Deprecated (forRemoval = true, since = "12.3.0")
  SLASH_LG(CBootstrapIconCSS.BI_SLASH_LG),
  @Deprecated (forRemoval = true, since = "12.3.0")
  SLASH_SQUARE(CBootstrapIconCSS.BI_SLASH_SQUARE),
  @Deprecated (forRemoval = true, since = "12.3.0")
  SLASH_SQUARE_FILL(CBootstrapIconCSS.BI_SLASH_SQUARE_FILL),
  @Deprecated (forRemoval = true, since = "12.3.0")
  SLIDERS(CBootstrapIconCSS.BI_SLIDERS),
  @Deprecated (forRemoval = true, since = "12.3.0")
  SLIDERS2(CBootstrapIconCSS.BI_SLIDERS2),
  @Deprecated (forRemoval = true, since = "12.3.0")
  SLIDERS2_VERTICAL(CBootstrapIconCSS.BI_SLIDERS2_VERTICAL),
  @Deprecated (forRemoval = true, since = "12.3.0")
  SMARTWATCH(CBootstrapIconCSS.BI_SMARTWATCH),
  @Deprecated (forRemoval = true, since = "12.3.0")
  SNAPCHAT(CBootstrapIconCSS.BI_SNAPCHAT),
  @Deprecated (forRemoval = true, since = "12.3.0")
  SNOW(CBootstrapIconCSS.BI_SNOW),
  @Deprecated (forRemoval = true, since = "12.3.0")
  SNOW2(CBootstrapIconCSS.BI_SNOW2),
  @Deprecated (forRemoval = true, since = "12.3.0")
  SNOW3(CBootstrapIconCSS.BI_SNOW3),
  @Deprecated (forRemoval = true, since = "12.3.0")
  SORT_ALPHA_DOWN(CBootstrapIconCSS.BI_SORT_ALPHA_DOWN),
  @Deprecated (forRemoval = true, since = "12.3.0")
  SORT_ALPHA_DOWN_ALT(CBootstrapIconCSS.BI_SORT_ALPHA_DOWN_ALT),
  @Deprecated (forRemoval = true, since = "12.3.0")
  SORT_ALPHA_UP(CBootstrapIconCSS.BI_SORT_ALPHA_UP),
  @Deprecated (forRemoval = true, since = "12.3.0")
  SORT_ALPHA_UP_ALT(CBootstrapIconCSS.BI_SORT_ALPHA_UP_ALT),
  @Deprecated (forRemoval = true, since = "12.3.0")
  SORT_DOWN(CBootstrapIconCSS.BI_SORT_DOWN),
  @Deprecated (forRemoval = true, since = "12.3.0")
  SORT_DOWN_ALT(CBootstrapIconCSS.BI_SORT_DOWN_ALT),
  @Deprecated (forRemoval = true, since = "12.3.0")
  SORT_NUMERIC_DOWN(CBootstrapIconCSS.BI_SORT_NUMERIC_DOWN),
  @Deprecated (forRemoval = true, since = "12.3.0")
  SORT_NUMERIC_DOWN_ALT(CBootstrapIconCSS.BI_SORT_NUMERIC_DOWN_ALT),
  @Deprecated (forRemoval = true, since = "12.3.0")
  SORT_NUMERIC_UP(CBootstrapIconCSS.BI_SORT_NUMERIC_UP),
  @Deprecated (forRemoval = true, since = "12.3.0")
  SORT_NUMERIC_UP_ALT(CBootstrapIconCSS.BI_SORT_NUMERIC_UP_ALT),
  @Deprecated (forRemoval = true, since = "12.3.0")
  SORT_UP(CBootstrapIconCSS.BI_SORT_UP),
  @Deprecated (forRemoval = true, since = "12.3.0")
  SORT_UP_ALT(CBootstrapIconCSS.BI_SORT_UP_ALT),
  @Deprecated (forRemoval = true, since = "12.3.0")
  SOUNDWAVE(CBootstrapIconCSS.BI_SOUNDWAVE),
  @Deprecated (forRemoval = true, since = "12.3.0")
  SOURCEFORGE(CBootstrapIconCSS.BI_SOURCEFORGE),
  @Deprecated (forRemoval = true, since = "12.3.0")
  SPEAKER(CBootstrapIconCSS.BI_SPEAKER),
  @Deprecated (forRemoval = true, since = "12.3.0")
  SPEAKER_FILL(CBootstrapIconCSS.BI_SPEAKER_FILL),
  @Deprecated (forRemoval = true, since = "12.3.0")
  SPEEDOMETER(CBootstrapIconCSS.BI_SPEEDOMETER),
  @Deprecated (forRemoval = true, since = "12.3.0")
  SPEEDOMETER2(CBootstrapIconCSS.BI_SPEEDOMETER2),
  @Deprecated (forRemoval = true, since = "12.3.0")
  SPELLCHECK(CBootstrapIconCSS.BI_SPELLCHECK),
  @Deprecated (forRemoval = true, since = "12.3.0")
  SPOTIFY(CBootstrapIconCSS.BI_SPOTIFY),
  @Deprecated (forRemoval = true, since = "12.3.0")
  SQUARE(CBootstrapIconCSS.BI_SQUARE),
  @Deprecated (forRemoval = true, since = "12.3.0")
  SQUARE_FILL(CBootstrapIconCSS.BI_SQUARE_FILL),
  @Deprecated (forRemoval = true, since = "12.3.0")
  SQUARE_HALF(CBootstrapIconCSS.BI_SQUARE_HALF),
  @Deprecated (forRemoval = true, since = "12.3.0")
  STACK(CBootstrapIconCSS.BI_STACK),
  @Deprecated (forRemoval = true, since = "12.3.0")
  STACK_OVERFLOW(CBootstrapIconCSS.BI_STACK_OVERFLOW),
  @Deprecated (forRemoval = true, since = "12.3.0")
  STAR(CBootstrapIconCSS.BI_STAR),
  @Deprecated (forRemoval = true, since = "12.3.0")
  STAR_FILL(CBootstrapIconCSS.BI_STAR_FILL),
  @Deprecated (forRemoval = true, since = "12.3.0")
  STAR_HALF(CBootstrapIconCSS.BI_STAR_HALF),
  @Deprecated (forRemoval = true, since = "12.3.0")
  STARS(CBootstrapIconCSS.BI_STARS),
  @Deprecated (forRemoval = true, since = "12.3.0")
  STEAM(CBootstrapIconCSS.BI_STEAM),
  @Deprecated (forRemoval = true, since = "12.3.0")
  STICKIES(CBootstrapIconCSS.BI_STICKIES),
  @Deprecated (forRemoval = true, since = "12.3.0")
  STICKIES_FILL(CBootstrapIconCSS.BI_STICKIES_FILL),
  @Deprecated (forRemoval = true, since = "12.3.0")
  STICKY(CBootstrapIconCSS.BI_STICKY),
  @Deprecated (forRemoval = true, since = "12.3.0")
  STICKY_FILL(CBootstrapIconCSS.BI_STICKY_FILL),
  @Deprecated (forRemoval = true, since = "12.3.0")
  STOP(CBootstrapIconCSS.BI_STOP),
  @Deprecated (forRemoval = true, since = "12.3.0")
  STOP_BTN(CBootstrapIconCSS.BI_STOP_BTN),
  @Deprecated (forRemoval = true, since = "12.3.0")
  STOP_BTN_FILL(CBootstrapIconCSS.BI_STOP_BTN_FILL),
  @Deprecated (forRemoval = true, since = "12.3.0")
  STOP_CIRCLE(CBootstrapIconCSS.BI_STOP_CIRCLE),
  @Deprecated (forRemoval = true, since = "12.3.0")
  STOP_CIRCLE_FILL(CBootstrapIconCSS.BI_STOP_CIRCLE_FILL),
  @Deprecated (forRemoval = true, since = "12.3.0")
  STOP_FILL(CBootstrapIconCSS.BI_STOP_FILL),
  @Deprecated (forRemoval = true, since = "12.3.0")
  STOPLIGHTS(CBootstrapIconCSS.BI_STOPLIGHTS),
  @Deprecated (forRemoval = true, since = "12.3.0")
  STOPLIGHTS_FILL(CBootstrapIconCSS.BI_STOPLIGHTS_FILL),
  @Deprecated (forRemoval = true, since = "12.3.0")
  STOPWATCH(CBootstrapIconCSS.BI_STOPWATCH),
  @Deprecated (forRemoval = true, since = "12.3.0")
  STOPWATCH_FILL(CBootstrapIconCSS.BI_STOPWATCH_FILL),
  @Deprecated (forRemoval = true, since = "12.3.0")
  STRAVA(CBootstrapIconCSS.BI_STRAVA),
  @Deprecated (forRemoval = true, since = "12.3.0")
  STRIPE(CBootstrapIconCSS.BI_STRIPE),
  @Deprecated (forRemoval = true, since = "12.3.0")
  SUBSCRIPT(CBootstrapIconCSS.BI_SUBSCRIPT),
  @Deprecated (forRemoval = true, since = "12.3.0")
  SUBSTACK(CBootstrapIconCSS.BI_SUBSTACK),
  @Deprecated (forRemoval = true, since = "12.3.0")
  SUBTRACT(CBootstrapIconCSS.BI_SUBTRACT),
  @Deprecated (forRemoval = true, since = "12.3.0")
  SUIT_CLUB(CBootstrapIconCSS.BI_SUIT_CLUB),
  @Deprecated (forRemoval = true, since = "12.3.0")
  SUIT_CLUB_FILL(CBootstrapIconCSS.BI_SUIT_CLUB_FILL),
  @Deprecated (forRemoval = true, since = "12.3.0")
  SUIT_DIAMOND(CBootstrapIconCSS.BI_SUIT_DIAMOND),
  @Deprecated (forRemoval = true, since = "12.3.0")
  SUIT_DIAMOND_FILL(CBootstrapIconCSS.BI_SUIT_DIAMOND_FILL),
  @Deprecated (forRemoval = true, since = "12.3.0")
  SUIT_HEART(CBootstrapIconCSS.BI_SUIT_HEART),
  @Deprecated (forRemoval = true, since = "12.3.0")
  SUIT_HEART_FILL(CBootstrapIconCSS.BI_SUIT_HEART_FILL),
  @Deprecated (forRemoval = true, since = "12.3.0")
  SUIT_SPADE(CBootstrapIconCSS.BI_SUIT_SPADE),
  @Deprecated (forRemoval = true, since = "12.3.0")
  SUIT_SPADE_FILL(CBootstrapIconCSS.BI_SUIT_SPADE_FILL),
  @Deprecated (forRemoval = true, since = "12.3.0")
  SUITCASE(CBootstrapIconCSS.BI_SUITCASE),
  @Deprecated (forRemoval = true, since = "12.3.0")
  SUITCASE_FILL(CBootstrapIconCSS.BI_SUITCASE_FILL),
  @Deprecated (forRemoval = true, since = "12.3.0")
  SUITCASE_LG(CBootstrapIconCSS.BI_SUITCASE_LG),
  @Deprecated (forRemoval = true, since = "12.3.0")
  SUITCASE_LG_FILL(CBootstrapIconCSS.BI_SUITCASE_LG_FILL),
  @Deprecated (forRemoval = true, since = "12.3.0")
  SUITCASE2(CBootstrapIconCSS.BI_SUITCASE2),
  @Deprecated (forRemoval = true, since = "12.3.0")
  SUITCASE2_FILL(CBootstrapIconCSS.BI_SUITCASE2_FILL),
  @Deprecated (forRemoval = true, since = "12.3.0")
  SUN(CBootstrapIconCSS.BI_SUN),
  @Deprecated (forRemoval = true, since = "12.3.0")
  SUN_FILL(CBootstrapIconCSS.BI_SUN_FILL),
  @Deprecated (forRemoval = true, since = "12.3.0")
  SUNGLASSES(CBootstrapIconCSS.BI_SUNGLASSES),
  @Deprecated (forRemoval = true, since = "12.3.0")
  SUNRISE(CBootstrapIconCSS.BI_SUNRISE),
  @Deprecated (forRemoval = true, since = "12.3.0")
  SUNRISE_FILL(CBootstrapIconCSS.BI_SUNRISE_FILL),
  @Deprecated (forRemoval = true, since = "12.3.0")
  SUNSET(CBootstrapIconCSS.BI_SUNSET),
  @Deprecated (forRemoval = true, since = "12.3.0")
  SUNSET_FILL(CBootstrapIconCSS.BI_SUNSET_FILL),
  @Deprecated (forRemoval = true, since = "12.3.0")
  SUPERSCRIPT(CBootstrapIconCSS.BI_SUPERSCRIPT),
  @Deprecated (forRemoval = true, since = "12.3.0")
  SYMMETRY_HORIZONTAL(CBootstrapIconCSS.BI_SYMMETRY_HORIZONTAL),
  @Deprecated (forRemoval = true, since = "12.3.0")
  SYMMETRY_VERTICAL(CBootstrapIconCSS.BI_SYMMETRY_VERTICAL),
  @Deprecated (forRemoval = true, since = "12.3.0")
  TABLE(CBootstrapIconCSS.BI_TABLE),
  @Deprecated (forRemoval = true, since = "12.3.0")
  TABLET(CBootstrapIconCSS.BI_TABLET),
  @Deprecated (forRemoval = true, since = "12.3.0")
  TABLET_FILL(CBootstrapIconCSS.BI_TABLET_FILL),
  @Deprecated (forRemoval = true, since = "12.3.0")
  TABLET_LANDSCAPE(CBootstrapIconCSS.BI_TABLET_LANDSCAPE),
  @Deprecated (forRemoval = true, since = "12.3.0")
  TABLET_LANDSCAPE_FILL(CBootstrapIconCSS.BI_TABLET_LANDSCAPE_FILL),
  @Deprecated (forRemoval = true, since = "12.3.0")
  TAG(CBootstrapIconCSS.BI_TAG),
  @Deprecated (forRemoval = true, since = "12.3.0")
  TAG_FILL(CBootstrapIconCSS.BI_TAG_FILL),
  @Deprecated (forRemoval = true, since = "12.3.0")
  TAGS(CBootstrapIconCSS.BI_TAGS),
  @Deprecated (forRemoval = true, since = "12.3.0")
  TAGS_FILL(CBootstrapIconCSS.BI_TAGS_FILL),
  @Deprecated (forRemoval = true, since = "12.3.0")
  TAXI_FRONT(CBootstrapIconCSS.BI_TAXI_FRONT),
  @Deprecated (forRemoval = true, since = "12.3.0")
  TAXI_FRONT_FILL(CBootstrapIconCSS.BI_TAXI_FRONT_FILL),
  @Deprecated (forRemoval = true, since = "12.3.0")
  TELEGRAM(CBootstrapIconCSS.BI_TELEGRAM),
  @Deprecated (forRemoval = true, since = "12.3.0")
  TELEPHONE(CBootstrapIconCSS.BI_TELEPHONE),
  @Deprecated (forRemoval = true, since = "12.3.0")
  TELEPHONE_FILL(CBootstrapIconCSS.BI_TELEPHONE_FILL),
  @Deprecated (forRemoval = true, since = "12.3.0")
  TELEPHONE_FORWARD(CBootstrapIconCSS.BI_TELEPHONE_FORWARD),
  @Deprecated (forRemoval = true, since = "12.3.0")
  TELEPHONE_FORWARD_FILL(CBootstrapIconCSS.BI_TELEPHONE_FORWARD_FILL),
  @Deprecated (forRemoval = true, since = "12.3.0")
  TELEPHONE_INBOUND(CBootstrapIconCSS.BI_TELEPHONE_INBOUND),
  @Deprecated (forRemoval = true, since = "12.3.0")
  TELEPHONE_INBOUND_FILL(CBootstrapIconCSS.BI_TELEPHONE_INBOUND_FILL),
  @Deprecated (forRemoval = true, since = "12.3.0")
  TELEPHONE_MINUS(CBootstrapIconCSS.BI_TELEPHONE_MINUS),
  @Deprecated (forRemoval = true, since = "12.3.0")
  TELEPHONE_MINUS_FILL(CBootstrapIconCSS.BI_TELEPHONE_MINUS_FILL),
  @Deprecated (forRemoval = true, since = "12.3.0")
  TELEPHONE_OUTBOUND(CBootstrapIconCSS.BI_TELEPHONE_OUTBOUND),
  @Deprecated (forRemoval = true, since = "12.3.0")
  TELEPHONE_OUTBOUND_FILL(CBootstrapIconCSS.BI_TELEPHONE_OUTBOUND_FILL),
  @Deprecated (forRemoval = true, since = "12.3.0")
  TELEPHONE_PLUS(CBootstrapIconCSS.BI_TELEPHONE_PLUS),
  @Deprecated (forRemoval = true, since = "12.3.0")
  TELEPHONE_PLUS_FILL(CBootstrapIconCSS.BI_TELEPHONE_PLUS_FILL),
  @Deprecated (forRemoval = true, since = "12.3.0")
  TELEPHONE_X(CBootstrapIconCSS.BI_TELEPHONE_X),
  @Deprecated (forRemoval = true, since = "12.3.0")
  TELEPHONE_X_FILL(CBootstrapIconCSS.BI_TELEPHONE_X_FILL),
  @Deprecated (forRemoval = true, since = "12.3.0")
  TENCENT_QQ(CBootstrapIconCSS.BI_TENCENT_QQ),
  @Deprecated (forRemoval = true, since = "12.3.0")
  TERMINAL(CBootstrapIconCSS.BI_TERMINAL),
  @Deprecated (forRemoval = true, since = "12.3.0")
  TERMINAL_DASH(CBootstrapIconCSS.BI_TERMINAL_DASH),
  @Deprecated (forRemoval = true, since = "12.3.0")
  TERMINAL_FILL(CBootstrapIconCSS.BI_TERMINAL_FILL),
  @Deprecated (forRemoval = true, since = "12.3.0")
  TERMINAL_PLUS(CBootstrapIconCSS.BI_TERMINAL_PLUS),
  @Deprecated (forRemoval = true, since = "12.3.0")
  TERMINAL_SPLIT(CBootstrapIconCSS.BI_TERMINAL_SPLIT),
  @Deprecated (forRemoval = true, since = "12.3.0")
  TERMINAL_X(CBootstrapIconCSS.BI_TERMINAL_X),
  @Deprecated (forRemoval = true, since = "12.3.0")
  TEXT_CENTER(CBootstrapIconCSS.BI_TEXT_CENTER),
  @Deprecated (forRemoval = true, since = "12.3.0")
  TEXT_INDENT_LEFT(CBootstrapIconCSS.BI_TEXT_INDENT_LEFT),
  @Deprecated (forRemoval = true, since = "12.3.0")
  TEXT_INDENT_RIGHT(CBootstrapIconCSS.BI_TEXT_INDENT_RIGHT),
  @Deprecated (forRemoval = true, since = "12.3.0")
  TEXT_LEFT(CBootstrapIconCSS.BI_TEXT_LEFT),
  @Deprecated (forRemoval = true, since = "12.3.0")
  TEXT_PARAGRAPH(CBootstrapIconCSS.BI_TEXT_PARAGRAPH),
  @Deprecated (forRemoval = true, since = "12.3.0")
  TEXT_RIGHT(CBootstrapIconCSS.BI_TEXT_RIGHT),
  @Deprecated (forRemoval = true, since = "12.3.0")
  TEXT_WRAP(CBootstrapIconCSS.BI_TEXT_WRAP),
  @Deprecated (forRemoval = true, since = "12.3.0")
  TEXTAREA(CBootstrapIconCSS.BI_TEXTAREA),
  @Deprecated (forRemoval = true, since = "12.3.0")
  TEXTAREA_RESIZE(CBootstrapIconCSS.BI_TEXTAREA_RESIZE),
  @Deprecated (forRemoval = true, since = "12.3.0")
  TEXTAREA_T(CBootstrapIconCSS.BI_TEXTAREA_T),
  @Deprecated (forRemoval = true, since = "12.3.0")
  THERMOMETER(CBootstrapIconCSS.BI_THERMOMETER),
  @Deprecated (forRemoval = true, since = "12.3.0")
  THERMOMETER_HALF(CBootstrapIconCSS.BI_THERMOMETER_HALF),
  @Deprecated (forRemoval = true, since = "12.3.0")
  THERMOMETER_HIGH(CBootstrapIconCSS.BI_THERMOMETER_HIGH),
  @Deprecated (forRemoval = true, since = "12.3.0")
  THERMOMETER_LOW(CBootstrapIconCSS.BI_THERMOMETER_LOW),
  @Deprecated (forRemoval = true, since = "12.3.0")
  THERMOMETER_SNOW(CBootstrapIconCSS.BI_THERMOMETER_SNOW),
  @Deprecated (forRemoval = true, since = "12.3.0")
  THERMOMETER_SUN(CBootstrapIconCSS.BI_THERMOMETER_SUN),
  @Deprecated (forRemoval = true, since = "12.3.0")
  THREADS(CBootstrapIconCSS.BI_THREADS),
  @Deprecated (forRemoval = true, since = "12.3.0")
  THREADS_FILL(CBootstrapIconCSS.BI_THREADS_FILL),
  @Deprecated (forRemoval = true, since = "12.3.0")
  THREE_DOTS(CBootstrapIconCSS.BI_THREE_DOTS),
  @Deprecated (forRemoval = true, since = "12.3.0")
  THREE_DOTS_VERTICAL(CBootstrapIconCSS.BI_THREE_DOTS_VERTICAL),
  @Deprecated (forRemoval = true, since = "12.3.0")
  THUNDERBOLT(CBootstrapIconCSS.BI_THUNDERBOLT),
  @Deprecated (forRemoval = true, since = "12.3.0")
  THUNDERBOLT_FILL(CBootstrapIconCSS.BI_THUNDERBOLT_FILL),
  @Deprecated (forRemoval = true, since = "12.3.0")
  TICKET(CBootstrapIconCSS.BI_TICKET),
  @Deprecated (forRemoval = true, since = "12.3.0")
  TICKET_DETAILED(CBootstrapIconCSS.BI_TICKET_DETAILED),
  @Deprecated (forRemoval = true, since = "12.3.0")
  TICKET_DETAILED_FILL(CBootstrapIconCSS.BI_TICKET_DETAILED_FILL),
  @Deprecated (forRemoval = true, since = "12.3.0")
  TICKET_FILL(CBootstrapIconCSS.BI_TICKET_FILL),
  @Deprecated (forRemoval = true, since = "12.3.0")
  TICKET_PERFORATED(CBootstrapIconCSS.BI_TICKET_PERFORATED),
  @Deprecated (forRemoval = true, since = "12.3.0")
  TICKET_PERFORATED_FILL(CBootstrapIconCSS.BI_TICKET_PERFORATED_FILL),
  @Deprecated (forRemoval = true, since = "12.3.0")
  TIKTOK(CBootstrapIconCSS.BI_TIKTOK),
  @Deprecated (forRemoval = true, since = "12.3.0")
  TOGGLE_OFF(CBootstrapIconCSS.BI_TOGGLE_OFF),
  @Deprecated (forRemoval = true, since = "12.3.0")
  TOGGLE_ON(CBootstrapIconCSS.BI_TOGGLE_ON),
  @Deprecated (forRemoval = true, since = "12.3.0")
  TOGGLE2_OFF(CBootstrapIconCSS.BI_TOGGLE2_OFF),
  @Deprecated (forRemoval = true, since = "12.3.0")
  TOGGLE2_ON(CBootstrapIconCSS.BI_TOGGLE2_ON),
  @Deprecated (forRemoval = true, since = "12.3.0")
  TOGGLES(CBootstrapIconCSS.BI_TOGGLES),
  @Deprecated (forRemoval = true, since = "12.3.0")
  TOGGLES2(CBootstrapIconCSS.BI_TOGGLES2),
  @Deprecated (forRemoval = true, since = "12.3.0")
  TOOLS(CBootstrapIconCSS.BI_TOOLS),
  @Deprecated (forRemoval = true, since = "12.3.0")
  TORNADO(CBootstrapIconCSS.BI_TORNADO),
  @Deprecated (forRemoval = true, since = "12.3.0")
  TRAIN_FREIGHT_FRONT(CBootstrapIconCSS.BI_TRAIN_FREIGHT_FRONT),
  @Deprecated (forRemoval = true, since = "12.3.0")
  TRAIN_FREIGHT_FRONT_FILL(CBootstrapIconCSS.BI_TRAIN_FREIGHT_FRONT_FILL),
  @Deprecated (forRemoval = true, since = "12.3.0")
  TRAIN_FRONT(CBootstrapIconCSS.BI_TRAIN_FRONT),
  @Deprecated (forRemoval = true, since = "12.3.0")
  TRAIN_FRONT_FILL(CBootstrapIconCSS.BI_TRAIN_FRONT_FILL),
  @Deprecated (forRemoval = true, since = "12.3.0")
  TRAIN_LIGHTRAIL_FRONT(CBootstrapIconCSS.BI_TRAIN_LIGHTRAIL_FRONT),
  @Deprecated (forRemoval = true, since = "12.3.0")
  TRAIN_LIGHTRAIL_FRONT_FILL(CBootstrapIconCSS.BI_TRAIN_LIGHTRAIL_FRONT_FILL),
  @Deprecated (forRemoval = true, since = "12.3.0")
  TRANSLATE(CBootstrapIconCSS.BI_TRANSLATE),
  @Deprecated (forRemoval = true, since = "12.3.0")
  TRANSPARENCY(CBootstrapIconCSS.BI_TRANSPARENCY),
  @Deprecated (forRemoval = true, since = "12.3.0")
  TRASH(CBootstrapIconCSS.BI_TRASH),
  @Deprecated (forRemoval = true, since = "12.3.0")
  TRASH_FILL(CBootstrapIconCSS.BI_TRASH_FILL),
  @Deprecated (forRemoval = true, since = "12.3.0")
  TRASH2(CBootstrapIconCSS.BI_TRASH2),
  @Deprecated (forRemoval = true, since = "12.3.0")
  TRASH2_FILL(CBootstrapIconCSS.BI_TRASH2_FILL),
  @Deprecated (forRemoval = true, since = "12.3.0")
  TRASH3(CBootstrapIconCSS.BI_TRASH3),
  @Deprecated (forRemoval = true, since = "12.3.0")
  TRASH3_FILL(CBootstrapIconCSS.BI_TRASH3_FILL),
  @Deprecated (forRemoval = true, since = "12.3.0")
  TREE(CBootstrapIconCSS.BI_TREE),
  @Deprecated (forRemoval = true, since = "12.3.0")
  TREE_FILL(CBootstrapIconCSS.BI_TREE_FILL),
  @Deprecated (forRemoval = true, since = "12.3.0")
  TRELLO(CBootstrapIconCSS.BI_TRELLO),
  @Deprecated (forRemoval = true, since = "12.3.0")
  TRIANGLE(CBootstrapIconCSS.BI_TRIANGLE),
  @Deprecated (forRemoval = true, since = "12.3.0")
  TRIANGLE_FILL(CBootstrapIconCSS.BI_TRIANGLE_FILL),
  @Deprecated (forRemoval = true, since = "12.3.0")
  TRIANGLE_HALF(CBootstrapIconCSS.BI_TRIANGLE_HALF),
  @Deprecated (forRemoval = true, since = "12.3.0")
  TROPHY(CBootstrapIconCSS.BI_TROPHY),
  @Deprecated (forRemoval = true, since = "12.3.0")
  TROPHY_FILL(CBootstrapIconCSS.BI_TROPHY_FILL),
  @Deprecated (forRemoval = true, since = "12.3.0")
  TROPICAL_STORM(CBootstrapIconCSS.BI_TROPICAL_STORM),
  @Deprecated (forRemoval = true, since = "12.3.0")
  TRUCK(CBootstrapIconCSS.BI_TRUCK),
  @Deprecated (forRemoval = true, since = "12.3.0")
  TRUCK_FLATBED(CBootstrapIconCSS.BI_TRUCK_FLATBED),
  @Deprecated (forRemoval = true, since = "12.3.0")
  TRUCK_FRONT(CBootstrapIconCSS.BI_TRUCK_FRONT),
  @Deprecated (forRemoval = true, since = "12.3.0")
  TRUCK_FRONT_FILL(CBootstrapIconCSS.BI_TRUCK_FRONT_FILL),
  @Deprecated (forRemoval = true, since = "12.3.0")
  TSUNAMI(CBootstrapIconCSS.BI_TSUNAMI),
  @Deprecated (forRemoval = true, since = "12.3.0")
  TV(CBootstrapIconCSS.BI_TV),
  @Deprecated (forRemoval = true, since = "12.3.0")
  TV_FILL(CBootstrapIconCSS.BI_TV_FILL),
  @Deprecated (forRemoval = true, since = "12.3.0")
  TWITCH(CBootstrapIconCSS.BI_TWITCH),
  @Deprecated (forRemoval = true, since = "12.3.0")
  TWITTER(CBootstrapIconCSS.BI_TWITTER),
  @Deprecated (forRemoval = true, since = "12.3.0")
  TWITTER_X(CBootstrapIconCSS.BI_TWITTER_X),
  @Deprecated (forRemoval = true, since = "12.3.0")
  TYPE(CBootstrapIconCSS.BI_TYPE),
  @Deprecated (forRemoval = true, since = "12.3.0")
  TYPE_BOLD(CBootstrapIconCSS.BI_TYPE_BOLD),
  @Deprecated (forRemoval = true, since = "12.3.0")
  TYPE_H1(CBootstrapIconCSS.BI_TYPE_H1),
  @Deprecated (forRemoval = true, since = "12.3.0")
  TYPE_H2(CBootstrapIconCSS.BI_TYPE_H2),
  @Deprecated (forRemoval = true, since = "12.3.0")
  TYPE_H3(CBootstrapIconCSS.BI_TYPE_H3),
  @Deprecated (forRemoval = true, since = "12.3.0")
  TYPE_H4(CBootstrapIconCSS.BI_TYPE_H4),
  @Deprecated (forRemoval = true, since = "12.3.0")
  TYPE_H5(CBootstrapIconCSS.BI_TYPE_H5),
  @Deprecated (forRemoval = true, since = "12.3.0")
  TYPE_H6(CBootstrapIconCSS.BI_TYPE_H6),
  @Deprecated (forRemoval = true, since = "12.3.0")
  TYPE_ITALIC(CBootstrapIconCSS.BI_TYPE_ITALIC),
  @Deprecated (forRemoval = true, since = "12.3.0")
  TYPE_STRIKETHROUGH(CBootstrapIconCSS.BI_TYPE_STRIKETHROUGH),
  @Deprecated (forRemoval = true, since = "12.3.0")
  TYPE_UNDERLINE(CBootstrapIconCSS.BI_TYPE_UNDERLINE),
  @Deprecated (forRemoval = true, since = "12.3.0")
  UBUNTU(CBootstrapIconCSS.BI_UBUNTU),
  @Deprecated (forRemoval = true, since = "12.3.0")
  UI_CHECKS(CBootstrapIconCSS.BI_UI_CHECKS),
  @Deprecated (forRemoval = true, since = "12.3.0")
  UI_CHECKS_GRID(CBootstrapIconCSS.BI_UI_CHECKS_GRID),
  @Deprecated (forRemoval = true, since = "12.3.0")
  UI_RADIOS(CBootstrapIconCSS.BI_UI_RADIOS),
  @Deprecated (forRemoval = true, since = "12.3.0")
  UI_RADIOS_GRID(CBootstrapIconCSS.BI_UI_RADIOS_GRID),
  @Deprecated (forRemoval = true, since = "12.3.0")
  UMBRELLA(CBootstrapIconCSS.BI_UMBRELLA),
  @Deprecated (forRemoval = true, since = "12.3.0")
  UMBRELLA_FILL(CBootstrapIconCSS.BI_UMBRELLA_FILL),
  @Deprecated (forRemoval = true, since = "12.3.0")
  UNINDENT(CBootstrapIconCSS.BI_UNINDENT),
  @Deprecated (forRemoval = true, since = "12.3.0")
  UNION(CBootstrapIconCSS.BI_UNION),
  @Deprecated (forRemoval = true, since = "12.3.0")
  UNITY(CBootstrapIconCSS.BI_UNITY),
  @Deprecated (forRemoval = true, since = "12.3.0")
  UNIVERSAL_ACCESS(CBootstrapIconCSS.BI_UNIVERSAL_ACCESS),
  @Deprecated (forRemoval = true, since = "12.3.0")
  UNIVERSAL_ACCESS_CIRCLE(CBootstrapIconCSS.BI_UNIVERSAL_ACCESS_CIRCLE),
  @Deprecated (forRemoval = true, since = "12.3.0")
  UNLOCK(CBootstrapIconCSS.BI_UNLOCK),
  @Deprecated (forRemoval = true, since = "12.3.0")
  UNLOCK_FILL(CBootstrapIconCSS.BI_UNLOCK_FILL),
  @Deprecated (forRemoval = true, since = "12.3.0")
  UPC(CBootstrapIconCSS.BI_UPC),
  @Deprecated (forRemoval = true, since = "12.3.0")
  UPC_SCAN(CBootstrapIconCSS.BI_UPC_SCAN),
  @Deprecated (forRemoval = true, since = "12.3.0")
  UPLOAD(CBootstrapIconCSS.BI_UPLOAD),
  @Deprecated (forRemoval = true, since = "12.3.0")
  USB(CBootstrapIconCSS.BI_USB),
  @Deprecated (forRemoval = true, since = "12.3.0")
  USB_C(CBootstrapIconCSS.BI_USB_C),
  @Deprecated (forRemoval = true, since = "12.3.0")
  USB_C_FILL(CBootstrapIconCSS.BI_USB_C_FILL),
  @Deprecated (forRemoval = true, since = "12.3.0")
  USB_DRIVE(CBootstrapIconCSS.BI_USB_DRIVE),
  @Deprecated (forRemoval = true, since = "12.3.0")
  USB_DRIVE_FILL(CBootstrapIconCSS.BI_USB_DRIVE_FILL),
  @Deprecated (forRemoval = true, since = "12.3.0")
  USB_FILL(CBootstrapIconCSS.BI_USB_FILL),
  @Deprecated (forRemoval = true, since = "12.3.0")
  USB_MICRO(CBootstrapIconCSS.BI_USB_MICRO),
  @Deprecated (forRemoval = true, since = "12.3.0")
  USB_MICRO_FILL(CBootstrapIconCSS.BI_USB_MICRO_FILL),
  @Deprecated (forRemoval = true, since = "12.3.0")
  USB_MINI(CBootstrapIconCSS.BI_USB_MINI),
  @Deprecated (forRemoval = true, since = "12.3.0")
  USB_MINI_FILL(CBootstrapIconCSS.BI_USB_MINI_FILL),
  @Deprecated (forRemoval = true, since = "12.3.0")
  USB_PLUG(CBootstrapIconCSS.BI_USB_PLUG),
  @Deprecated (forRemoval = true, since = "12.3.0")
  USB_PLUG_FILL(CBootstrapIconCSS.BI_USB_PLUG_FILL),
  @Deprecated (forRemoval = true, since = "12.3.0")
  USB_SYMBOL(CBootstrapIconCSS.BI_USB_SYMBOL),
  @Deprecated (forRemoval = true, since = "12.3.0")
  VALENTINE(CBootstrapIconCSS.BI_VALENTINE),
  @Deprecated (forRemoval = true, since = "12.3.0")
  VALENTINE2(CBootstrapIconCSS.BI_VALENTINE2),
  @Deprecated (forRemoval = true, since = "12.3.0")
  VECTOR_PEN(CBootstrapIconCSS.BI_VECTOR_PEN),
  @Deprecated (forRemoval = true, since = "12.3.0")
  VIEW_LIST(CBootstrapIconCSS.BI_VIEW_LIST),
  @Deprecated (forRemoval = true, since = "12.3.0")
  VIEW_STACKED(CBootstrapIconCSS.BI_VIEW_STACKED),
  @Deprecated (forRemoval = true, since = "12.3.0")
  VIGNETTE(CBootstrapIconCSS.BI_VIGNETTE),
  @Deprecated (forRemoval = true, since = "12.3.0")
  VIMEO(CBootstrapIconCSS.BI_VIMEO),
  @Deprecated (forRemoval = true, since = "12.3.0")
  VINYL(CBootstrapIconCSS.BI_VINYL),
  @Deprecated (forRemoval = true, since = "12.3.0")
  VINYL_FILL(CBootstrapIconCSS.BI_VINYL_FILL),
  @Deprecated (forRemoval = true, since = "12.3.0")
  VIRUS(CBootstrapIconCSS.BI_VIRUS),
  @Deprecated (forRemoval = true, since = "12.3.0")
  VIRUS2(CBootstrapIconCSS.BI_VIRUS2),
  @Deprecated (forRemoval = true, since = "12.3.0")
  VOICEMAIL(CBootstrapIconCSS.BI_VOICEMAIL),
  @Deprecated (forRemoval = true, since = "12.3.0")
  VOLUME_DOWN(CBootstrapIconCSS.BI_VOLUME_DOWN),
  @Deprecated (forRemoval = true, since = "12.3.0")
  VOLUME_DOWN_FILL(CBootstrapIconCSS.BI_VOLUME_DOWN_FILL),
  @Deprecated (forRemoval = true, since = "12.3.0")
  VOLUME_MUTE(CBootstrapIconCSS.BI_VOLUME_MUTE),
  @Deprecated (forRemoval = true, since = "12.3.0")
  VOLUME_MUTE_FILL(CBootstrapIconCSS.BI_VOLUME_MUTE_FILL),
  @Deprecated (forRemoval = true, since = "12.3.0")
  VOLUME_OFF(CBootstrapIconCSS.BI_VOLUME_OFF),
  @Deprecated (forRemoval = true, since = "12.3.0")
  VOLUME_OFF_FILL(CBootstrapIconCSS.BI_VOLUME_OFF_FILL),
  @Deprecated (forRemoval = true, since = "12.3.0")
  VOLUME_UP(CBootstrapIconCSS.BI_VOLUME_UP),
  @Deprecated (forRemoval = true, since = "12.3.0")
  VOLUME_UP_FILL(CBootstrapIconCSS.BI_VOLUME_UP_FILL),
  @Deprecated (forRemoval = true, since = "12.3.0")
  VR(CBootstrapIconCSS.BI_VR),
  @Deprecated (forRemoval = true, since = "12.3.0")
  WALLET(CBootstrapIconCSS.BI_WALLET),
  @Deprecated (forRemoval = true, since = "12.3.0")
  WALLET_FILL(CBootstrapIconCSS.BI_WALLET_FILL),
  @Deprecated (forRemoval = true, since = "12.3.0")
  WALLET2(CBootstrapIconCSS.BI_WALLET2),
  @Deprecated (forRemoval = true, since = "12.3.0")
  WATCH(CBootstrapIconCSS.BI_WATCH),
  @Deprecated (forRemoval = true, since = "12.3.0")
  WATER(CBootstrapIconCSS.BI_WATER),
  @Deprecated (forRemoval = true, since = "12.3.0")
  WEBCAM(CBootstrapIconCSS.BI_WEBCAM),
  @Deprecated (forRemoval = true, since = "12.3.0")
  WEBCAM_FILL(CBootstrapIconCSS.BI_WEBCAM_FILL),
  @Deprecated (forRemoval = true, since = "12.3.0")
  WECHAT(CBootstrapIconCSS.BI_WECHAT),
  @Deprecated (forRemoval = true, since = "12.3.0")
  WHATSAPP(CBootstrapIconCSS.BI_WHATSAPP),
  @Deprecated (forRemoval = true, since = "12.3.0")
  WIFI(CBootstrapIconCSS.BI_WIFI),
  @Deprecated (forRemoval = true, since = "12.3.0")
  WIFI_1(CBootstrapIconCSS.BI_WIFI_1),
  @Deprecated (forRemoval = true, since = "12.3.0")
  WIFI_2(CBootstrapIconCSS.BI_WIFI_2),
  @Deprecated (forRemoval = true, since = "12.3.0")
  WIFI_OFF(CBootstrapIconCSS.BI_WIFI_OFF),
  @Deprecated (forRemoval = true, since = "12.3.0")
  WIKIPEDIA(CBootstrapIconCSS.BI_WIKIPEDIA),
  @Deprecated (forRemoval = true, since = "12.3.0")
  WIND(CBootstrapIconCSS.BI_WIND),
  @Deprecated (forRemoval = true, since = "12.3.0")
  WINDOW(CBootstrapIconCSS.BI_WINDOW),
  @Deprecated (forRemoval = true, since = "12.3.0")
  WINDOW_DASH(CBootstrapIconCSS.BI_WINDOW_DASH),
  @Deprecated (forRemoval = true, since = "12.3.0")
  WINDOW_DESKTOP(CBootstrapIconCSS.BI_WINDOW_DESKTOP),
  @Deprecated (forRemoval = true, since = "12.3.0")
  WINDOW_DOCK(CBootstrapIconCSS.BI_WINDOW_DOCK),
  @Deprecated (forRemoval = true, since = "12.3.0")
  WINDOW_FULLSCREEN(CBootstrapIconCSS.BI_WINDOW_FULLSCREEN),
  @Deprecated (forRemoval = true, since = "12.3.0")
  WINDOW_PLUS(CBootstrapIconCSS.BI_WINDOW_PLUS),
  @Deprecated (forRemoval = true, since = "12.3.0")
  WINDOW_SIDEBAR(CBootstrapIconCSS.BI_WINDOW_SIDEBAR),
  @Deprecated (forRemoval = true, since = "12.3.0")
  WINDOW_SPLIT(CBootstrapIconCSS.BI_WINDOW_SPLIT),
  @Deprecated (forRemoval = true, since = "12.3.0")
  WINDOW_STACK(CBootstrapIconCSS.BI_WINDOW_STACK),
  @Deprecated (forRemoval = true, since = "12.3.0")
  WINDOW_X(CBootstrapIconCSS.BI_WINDOW_X),
  @Deprecated (forRemoval = true, since = "12.3.0")
  WINDOWS(CBootstrapIconCSS.BI_WINDOWS),
  @Deprecated (forRemoval = true, since = "12.3.0")
  WORDPRESS(CBootstrapIconCSS.BI_WORDPRESS),
  @Deprecated (forRemoval = true, since = "12.3.0")
  WRENCH(CBootstrapIconCSS.BI_WRENCH),
  @Deprecated (forRemoval = true, since = "12.3.0")
  WRENCH_ADJUSTABLE(CBootstrapIconCSS.BI_WRENCH_ADJUSTABLE),
  @Deprecated (forRemoval = true, since = "12.3.0")
  WRENCH_ADJUSTABLE_CIRCLE(CBootstrapIconCSS.BI_WRENCH_ADJUSTABLE_CIRCLE),
  @Deprecated (forRemoval = true, since = "12.3.0")
  WRENCH_ADJUSTABLE_CIRCLE_FILL(CBootstrapIconCSS.BI_WRENCH_ADJUSTABLE_CIRCLE_FILL),
  @Deprecated (forRemoval = true, since = "12.3.0")
  X(CBootstrapIconCSS.BI_X),
  @Deprecated (forRemoval = true, since = "12.3.0")
  X_CIRCLE(CBootstrapIconCSS.BI_X_CIRCLE),
  @Deprecated (forRemoval = true, since = "12.3.0")
  X_CIRCLE_FILL(CBootstrapIconCSS.BI_X_CIRCLE_FILL),
  @Deprecated (forRemoval = true, since = "12.3.0")
  X_DIAMOND(CBootstrapIconCSS.BI_X_DIAMOND),
  @Deprecated (forRemoval = true, since = "12.3.0")
  X_DIAMOND_FILL(CBootstrapIconCSS.BI_X_DIAMOND_FILL),
  @Deprecated (forRemoval = true, since = "12.3.0")
  X_LG(CBootstrapIconCSS.BI_X_LG),
  @Deprecated (forRemoval = true, since = "12.3.0")
  X_OCTAGON(CBootstrapIconCSS.BI_X_OCTAGON),
  @Deprecated (forRemoval = true, since = "12.3.0")
  X_OCTAGON_FILL(CBootstrapIconCSS.BI_X_OCTAGON_FILL),
  @Deprecated (forRemoval = true, since = "12.3.0")
  X_SQUARE(CBootstrapIconCSS.BI_X_SQUARE),
  @Deprecated (forRemoval = true, since = "12.3.0")
  X_SQUARE_FILL(CBootstrapIconCSS.BI_X_SQUARE_FILL),
  @Deprecated (forRemoval = true, since = "12.3.0")
  XBOX(CBootstrapIconCSS.BI_XBOX),
  @Deprecated (forRemoval = true, since = "12.3.0")
  YELP(CBootstrapIconCSS.BI_YELP),
  @Deprecated (forRemoval = true, since = "12.3.0")
  YIN_YANG(CBootstrapIconCSS.BI_YIN_YANG),
  @Deprecated (forRemoval = true, since = "12.3.0")
  YOUTUBE(CBootstrapIconCSS.BI_YOUTUBE),
  @Deprecated (forRemoval = true, since = "12.3.0")
  ZOOM_IN(CBootstrapIconCSS.BI_ZOOM_IN),
  @Deprecated (forRemoval = true, since = "12.3.0")
  ZOOM_OUT(CBootstrapIconCSS.BI_ZOOM_OUT);

  private static final ICSSClassProvider CSS_CLASS_SCALE_2 = DefaultCSSClassProvider.create ("bi-scale-2");
  private static final ICSSClassProvider CSS_CLASS_SCALE_3 = DefaultCSSClassProvider.create ("bi-scale-3");
  private static final ICSSClassProvider CSS_CLASS_SCALE_4 = DefaultCSSClassProvider.create ("bi-scale-4");

  private final ICSSClassProvider m_aCSSClass;

  EBootstrapIcon (@NonNull final ICSSClassProvider aCSSClass)
  {
    m_aCSSClass = aCSSClass;
  }

  @Deprecated (forRemoval = true, since = "12.3.0")
  @Nullable
  public String getCSSClass ()
  {
    return m_aCSSClass.getCSSClass ();
  }

  @Deprecated (forRemoval = true, since = "12.3.0")
  @NonNull
  public <T extends IHCElement <?>> T applyToNode (@NonNull final T aElement)
  {
    // No additional classes needed
    aElement.addClass (m_aCSSClass);
    aElement.customAttrs ().setAriaHidden (true);
    return aElement;
  }

  @Deprecated (forRemoval = true, since = "12.3.0")
  @NonNull
  public HCI getAsNode ()
  {
    return applyToNode (new HCI ());
  }

  @Deprecated (forRemoval = true, since = "12.3.0")
  @NonNull
  public HCI getAsNode2x ()
  {
    return getAsNode ().addClass (CSS_CLASS_SCALE_2);
  }

  @Deprecated (forRemoval = true, since = "12.3.0")
  @NonNull
  public HCI getAsNode3x ()
  {
    return getAsNode ().addClass (CSS_CLASS_SCALE_3);
  }

  @Deprecated (forRemoval = true, since = "12.3.0")
  @NonNull
  public HCI getAsNode4x ()
  {
    return getAsNode ().addClass (CSS_CLASS_SCALE_4);
  }

  @Deprecated (forRemoval = true, since = "12.3.0")
  public static void setAsDefault ()
  {
    DefaultIcons.set (EDefaultIcon.ADD, PLUS_CIRCLE);
    DefaultIcons.set (EDefaultIcon.BACK, ARROW_LEFT);
    DefaultIcons.set (EDefaultIcon.BACK_TO_LIST, ARROW_LEFT);
    DefaultIcons.set (EDefaultIcon.CANCEL, X);
    DefaultIcons.set (EDefaultIcon.COPY, FILES);
    DefaultIcons.set (EDefaultIcon.DELETE, X_CIRCLE);
    DefaultIcons.set (EDefaultIcon.DOWN, ARROW_DOWN);
    DefaultIcons.set (EDefaultIcon.EDIT, PENCIL);
    DefaultIcons.set (EDefaultIcon.FORWARD, FORWARD);
    DefaultIcons.set (EDefaultIcon.HELP, QUESTION_CIRCLE);
    DefaultIcons.set (EDefaultIcon.INFO, INFO);
    DefaultIcons.set (EDefaultIcon.KEY, LOCK);
    DefaultIcons.set (EDefaultIcon.MAGNIFIER, SEARCH);
    DefaultIcons.set (EDefaultIcon.MINUS, DASH_CIRCLE);
    DefaultIcons.set (EDefaultIcon.NEW, FILE);
    DefaultIcons.set (EDefaultIcon.NEXT, FORWARD);
    DefaultIcons.set (EDefaultIcon.NO, X);
    DefaultIcons.set (EDefaultIcon.PLUS, PLUS_CIRCLE);
    DefaultIcons.set (EDefaultIcon.REFRESH, ARROW_COUNTERCLOCKWISE);
    DefaultIcons.set (EDefaultIcon.SAVE, SAVE);
    DefaultIcons.set (EDefaultIcon.SAVE_ALL, SAVE);
    DefaultIcons.set (EDefaultIcon.SAVE_AS, SAVE);
    DefaultIcons.set (EDefaultIcon.SAVE_CLOSE, SAVE);
    DefaultIcons.set (EDefaultIcon.SUBMIT, ARROW_RIGHT_CIRCLE_FILL);
    DefaultIcons.set (EDefaultIcon.UNDELETE, FILE_ARROW_UP);
    DefaultIcons.set (EDefaultIcon.UP, ARROW_UP);
    DefaultIcons.set (EDefaultIcon.YES, CHECK);
  }

  @Deprecated (forRemoval = true, since = "12.3.0")
  @NonNull
  public static ICommonsList <ICSSPathProvider> getAllCSSFiles ()
  {
    return new CommonsArrayList <> (EIconCSSPathProvider.BOOTSTRAP_ICONS, EIconCSSPathProvider.PH_OTON_BOOTSTRAP_ICONS);
  }

  @Deprecated (forRemoval = true, since = "12.3.0")
  public static void registerResourcesForGlobal ()
  {
    for (final ICSSPathProvider aItem : getAllCSSFiles ())
      PhotonCSS.registerCSSIncludeForGlobal (aItem);
  }

  @Deprecated (forRemoval = true, since = "12.3.0")
  public static void registerResourcesForThisRequest ()
  {
    for (final ICSSPathProvider aItem : getAllCSSFiles ())
      PhotonCSS.registerCSSIncludeForThisRequest (aItem);
  }
}
