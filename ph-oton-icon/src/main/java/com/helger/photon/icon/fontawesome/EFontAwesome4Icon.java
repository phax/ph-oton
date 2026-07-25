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
package com.helger.photon.icon.fontawesome;

import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.Nullable;

import com.helger.collection.commons.CommonsArrayList;
import com.helger.collection.commons.ICommonsList;
import com.helger.html.css.ICSSClassProvider;
import com.helger.html.hc.html.IHCElement;
import com.helger.html.hc.html.textlevel.HCI;
import com.helger.html.hc.html.textlevel.HCSpan;
import com.helger.html.resource.css.ICSSPathProvider;
import com.helger.photon.app.html.PhotonCSS;
import com.helger.photon.icon.EIconCSSPathProvider;
import com.helger.photon.uicore.icon.DefaultIcons;
import com.helger.photon.uicore.icon.EDefaultIcon;
import com.helger.photon.uicore.icon.IIcon;

/**
 * Font Awesome icons
 *
 * @author Philip Helger
 */
@Deprecated (forRemoval = true, since = "12.3.0")
public enum EFontAwesome4Icon implements IIcon
{
  @Deprecated (forRemoval = true, since = "12.3.0")
  _500PX(CFontAwesome4CSS.FA_500PX),
  @Deprecated (forRemoval = true, since = "12.3.0")
  ADDRESS_BOOK(CFontAwesome4CSS.FA_ADDRESS_BOOK),
  @Deprecated (forRemoval = true, since = "12.3.0")
  ADDRESS_BOOK_O(CFontAwesome4CSS.FA_ADDRESS_BOOK_O),
  @Deprecated (forRemoval = true, since = "12.3.0")
  ADDRESS_CARD(CFontAwesome4CSS.FA_ADDRESS_CARD),
  @Deprecated (forRemoval = true, since = "12.3.0")
  ADDRESS_CARD_O(CFontAwesome4CSS.FA_ADDRESS_CARD_O),
  @Deprecated (forRemoval = true, since = "12.3.0")
  ADJUST(CFontAwesome4CSS.FA_ADJUST),
  @Deprecated (forRemoval = true, since = "12.3.0")
  ADN(CFontAwesome4CSS.FA_ADN),
  @Deprecated (forRemoval = true, since = "12.3.0")
  ALIGN_CENTER(CFontAwesome4CSS.FA_ALIGN_CENTER),
  @Deprecated (forRemoval = true, since = "12.3.0")
  ALIGN_JUSTIFY(CFontAwesome4CSS.FA_ALIGN_JUSTIFY),
  @Deprecated (forRemoval = true, since = "12.3.0")
  ALIGN_LEFT(CFontAwesome4CSS.FA_ALIGN_LEFT),
  @Deprecated (forRemoval = true, since = "12.3.0")
  ALIGN_RIGHT(CFontAwesome4CSS.FA_ALIGN_RIGHT),
  @Deprecated (forRemoval = true, since = "12.3.0")
  AMAZON(CFontAwesome4CSS.FA_AMAZON),
  @Deprecated (forRemoval = true, since = "12.3.0")
  AMBULANCE(CFontAwesome4CSS.FA_AMBULANCE),
  @Deprecated (forRemoval = true, since = "12.3.0")
  AMERICAN_SIGN_LANGUAGE_INTERPRETING(CFontAwesome4CSS.FA_AMERICAN_SIGN_LANGUAGE_INTERPRETING),
  @Deprecated (forRemoval = true, since = "12.3.0")
  ANCHOR(CFontAwesome4CSS.FA_ANCHOR),
  @Deprecated (forRemoval = true, since = "12.3.0")
  ANDROID(CFontAwesome4CSS.FA_ANDROID),
  @Deprecated (forRemoval = true, since = "12.3.0")
  ANGELLIST(CFontAwesome4CSS.FA_ANGELLIST),
  @Deprecated (forRemoval = true, since = "12.3.0")
  ANGLE_DOUBLE_DOWN(CFontAwesome4CSS.FA_ANGLE_DOUBLE_DOWN),
  @Deprecated (forRemoval = true, since = "12.3.0")
  ANGLE_DOUBLE_LEFT(CFontAwesome4CSS.FA_ANGLE_DOUBLE_LEFT),
  @Deprecated (forRemoval = true, since = "12.3.0")
  ANGLE_DOUBLE_RIGHT(CFontAwesome4CSS.FA_ANGLE_DOUBLE_RIGHT),
  @Deprecated (forRemoval = true, since = "12.3.0")
  ANGLE_DOUBLE_UP(CFontAwesome4CSS.FA_ANGLE_DOUBLE_UP),
  @Deprecated (forRemoval = true, since = "12.3.0")
  ANGLE_DOWN(CFontAwesome4CSS.FA_ANGLE_DOWN),
  @Deprecated (forRemoval = true, since = "12.3.0")
  ANGLE_LEFT(CFontAwesome4CSS.FA_ANGLE_LEFT),
  @Deprecated (forRemoval = true, since = "12.3.0")
  ANGLE_RIGHT(CFontAwesome4CSS.FA_ANGLE_RIGHT),
  @Deprecated (forRemoval = true, since = "12.3.0")
  ANGLE_UP(CFontAwesome4CSS.FA_ANGLE_UP),
  @Deprecated (forRemoval = true, since = "12.3.0")
  APPLE(CFontAwesome4CSS.FA_APPLE),
  @Deprecated (forRemoval = true, since = "12.3.0")
  ARCHIVE(CFontAwesome4CSS.FA_ARCHIVE),
  @Deprecated (forRemoval = true, since = "12.3.0")
  AREA_CHART(CFontAwesome4CSS.FA_AREA_CHART),
  @Deprecated (forRemoval = true, since = "12.3.0")
  ARROW_CIRCLE_DOWN(CFontAwesome4CSS.FA_ARROW_CIRCLE_DOWN),
  @Deprecated (forRemoval = true, since = "12.3.0")
  ARROW_CIRCLE_LEFT(CFontAwesome4CSS.FA_ARROW_CIRCLE_LEFT),
  @Deprecated (forRemoval = true, since = "12.3.0")
  ARROW_CIRCLE_O_DOWN(CFontAwesome4CSS.FA_ARROW_CIRCLE_O_DOWN),
  @Deprecated (forRemoval = true, since = "12.3.0")
  ARROW_CIRCLE_O_LEFT(CFontAwesome4CSS.FA_ARROW_CIRCLE_O_LEFT),
  @Deprecated (forRemoval = true, since = "12.3.0")
  ARROW_CIRCLE_O_RIGHT(CFontAwesome4CSS.FA_ARROW_CIRCLE_O_RIGHT),
  @Deprecated (forRemoval = true, since = "12.3.0")
  ARROW_CIRCLE_O_UP(CFontAwesome4CSS.FA_ARROW_CIRCLE_O_UP),
  @Deprecated (forRemoval = true, since = "12.3.0")
  ARROW_CIRCLE_RIGHT(CFontAwesome4CSS.FA_ARROW_CIRCLE_RIGHT),
  @Deprecated (forRemoval = true, since = "12.3.0")
  ARROW_CIRCLE_UP(CFontAwesome4CSS.FA_ARROW_CIRCLE_UP),
  @Deprecated (forRemoval = true, since = "12.3.0")
  ARROW_DOWN(CFontAwesome4CSS.FA_ARROW_DOWN),
  @Deprecated (forRemoval = true, since = "12.3.0")
  ARROW_LEFT(CFontAwesome4CSS.FA_ARROW_LEFT),
  @Deprecated (forRemoval = true, since = "12.3.0")
  ARROW_RIGHT(CFontAwesome4CSS.FA_ARROW_RIGHT),
  @Deprecated (forRemoval = true, since = "12.3.0")
  ARROW_UP(CFontAwesome4CSS.FA_ARROW_UP),
  @Deprecated (forRemoval = true, since = "12.3.0")
  ARROWS(CFontAwesome4CSS.FA_ARROWS),
  @Deprecated (forRemoval = true, since = "12.3.0")
  ARROWS_ALT(CFontAwesome4CSS.FA_ARROWS_ALT),
  @Deprecated (forRemoval = true, since = "12.3.0")
  ARROWS_H(CFontAwesome4CSS.FA_ARROWS_H),
  @Deprecated (forRemoval = true, since = "12.3.0")
  ARROWS_V(CFontAwesome4CSS.FA_ARROWS_V),
  @Deprecated (forRemoval = true, since = "12.3.0")
  ASL_INTERPRETING(CFontAwesome4CSS.FA_ASL_INTERPRETING),
  @Deprecated (forRemoval = true, since = "12.3.0")
  ASSISTIVE_LISTENING_SYSTEMS(CFontAwesome4CSS.FA_ASSISTIVE_LISTENING_SYSTEMS),
  @Deprecated (forRemoval = true, since = "12.3.0")
  ASTERISK(CFontAwesome4CSS.FA_ASTERISK),
  @Deprecated (forRemoval = true, since = "12.3.0")
  AT(CFontAwesome4CSS.FA_AT),
  @Deprecated (forRemoval = true, since = "12.3.0")
  AUDIO_DESCRIPTION(CFontAwesome4CSS.FA_AUDIO_DESCRIPTION),
  @Deprecated (forRemoval = true, since = "12.3.0")
  AUTOMOBILE(CFontAwesome4CSS.FA_AUTOMOBILE),
  @Deprecated (forRemoval = true, since = "12.3.0")
  BACKWARD(CFontAwesome4CSS.FA_BACKWARD),
  @Deprecated (forRemoval = true, since = "12.3.0")
  BALANCE_SCALE(CFontAwesome4CSS.FA_BALANCE_SCALE),
  @Deprecated (forRemoval = true, since = "12.3.0")
  BAN(CFontAwesome4CSS.FA_BAN),
  @Deprecated (forRemoval = true, since = "12.3.0")
  BANDCAMP(CFontAwesome4CSS.FA_BANDCAMP),
  @Deprecated (forRemoval = true, since = "12.3.0")
  BANK(CFontAwesome4CSS.FA_BANK),
  @Deprecated (forRemoval = true, since = "12.3.0")
  BAR_CHART(CFontAwesome4CSS.FA_BAR_CHART),
  @Deprecated (forRemoval = true, since = "12.3.0")
  BAR_CHART_O(CFontAwesome4CSS.FA_BAR_CHART_O),
  @Deprecated (forRemoval = true, since = "12.3.0")
  BARCODE(CFontAwesome4CSS.FA_BARCODE),
  @Deprecated (forRemoval = true, since = "12.3.0")
  BARS(CFontAwesome4CSS.FA_BARS),
  @Deprecated (forRemoval = true, since = "12.3.0")
  BATH(CFontAwesome4CSS.FA_BATH),
  @Deprecated (forRemoval = true, since = "12.3.0")
  BATHTUB(CFontAwesome4CSS.FA_BATHTUB),
  @Deprecated (forRemoval = true, since = "12.3.0")
  BATTERY(CFontAwesome4CSS.FA_BATTERY),
  @Deprecated (forRemoval = true, since = "12.3.0")
  BATTERY_0(CFontAwesome4CSS.FA_BATTERY_0),
  @Deprecated (forRemoval = true, since = "12.3.0")
  BATTERY_1(CFontAwesome4CSS.FA_BATTERY_1),
  @Deprecated (forRemoval = true, since = "12.3.0")
  BATTERY_2(CFontAwesome4CSS.FA_BATTERY_2),
  @Deprecated (forRemoval = true, since = "12.3.0")
  BATTERY_3(CFontAwesome4CSS.FA_BATTERY_3),
  @Deprecated (forRemoval = true, since = "12.3.0")
  BATTERY_4(CFontAwesome4CSS.FA_BATTERY_4),
  @Deprecated (forRemoval = true, since = "12.3.0")
  BATTERY_EMPTY(CFontAwesome4CSS.FA_BATTERY_EMPTY),
  @Deprecated (forRemoval = true, since = "12.3.0")
  BATTERY_FULL(CFontAwesome4CSS.FA_BATTERY_FULL),
  @Deprecated (forRemoval = true, since = "12.3.0")
  BATTERY_HALF(CFontAwesome4CSS.FA_BATTERY_HALF),
  @Deprecated (forRemoval = true, since = "12.3.0")
  BATTERY_QUARTER(CFontAwesome4CSS.FA_BATTERY_QUARTER),
  @Deprecated (forRemoval = true, since = "12.3.0")
  BATTERY_THREE_QUARTERS(CFontAwesome4CSS.FA_BATTERY_THREE_QUARTERS),
  @Deprecated (forRemoval = true, since = "12.3.0")
  BED(CFontAwesome4CSS.FA_BED),
  @Deprecated (forRemoval = true, since = "12.3.0")
  BEER(CFontAwesome4CSS.FA_BEER),
  @Deprecated (forRemoval = true, since = "12.3.0")
  BEHANCE(CFontAwesome4CSS.FA_BEHANCE),
  @Deprecated (forRemoval = true, since = "12.3.0")
  BEHANCE_SQUARE(CFontAwesome4CSS.FA_BEHANCE_SQUARE),
  @Deprecated (forRemoval = true, since = "12.3.0")
  BELL(CFontAwesome4CSS.FA_BELL),
  @Deprecated (forRemoval = true, since = "12.3.0")
  BELL_O(CFontAwesome4CSS.FA_BELL_O),
  @Deprecated (forRemoval = true, since = "12.3.0")
  BELL_SLASH(CFontAwesome4CSS.FA_BELL_SLASH),
  @Deprecated (forRemoval = true, since = "12.3.0")
  BELL_SLASH_O(CFontAwesome4CSS.FA_BELL_SLASH_O),
  @Deprecated (forRemoval = true, since = "12.3.0")
  BICYCLE(CFontAwesome4CSS.FA_BICYCLE),
  @Deprecated (forRemoval = true, since = "12.3.0")
  BINOCULARS(CFontAwesome4CSS.FA_BINOCULARS),
  @Deprecated (forRemoval = true, since = "12.3.0")
  BIRTHDAY_CAKE(CFontAwesome4CSS.FA_BIRTHDAY_CAKE),
  @Deprecated (forRemoval = true, since = "12.3.0")
  BITBUCKET(CFontAwesome4CSS.FA_BITBUCKET),
  @Deprecated (forRemoval = true, since = "12.3.0")
  BITBUCKET_SQUARE(CFontAwesome4CSS.FA_BITBUCKET_SQUARE),
  @Deprecated (forRemoval = true, since = "12.3.0")
  BITCOIN(CFontAwesome4CSS.FA_BITCOIN),
  @Deprecated (forRemoval = true, since = "12.3.0")
  BLACK_TIE(CFontAwesome4CSS.FA_BLACK_TIE),
  @Deprecated (forRemoval = true, since = "12.3.0")
  BLIND(CFontAwesome4CSS.FA_BLIND),
  @Deprecated (forRemoval = true, since = "12.3.0")
  BLUETOOTH(CFontAwesome4CSS.FA_BLUETOOTH),
  @Deprecated (forRemoval = true, since = "12.3.0")
  BLUETOOTH_B(CFontAwesome4CSS.FA_BLUETOOTH_B),
  @Deprecated (forRemoval = true, since = "12.3.0")
  BOLD(CFontAwesome4CSS.FA_BOLD),
  @Deprecated (forRemoval = true, since = "12.3.0")
  BOLT(CFontAwesome4CSS.FA_BOLT),
  @Deprecated (forRemoval = true, since = "12.3.0")
  BOMB(CFontAwesome4CSS.FA_BOMB),
  @Deprecated (forRemoval = true, since = "12.3.0")
  BOOK(CFontAwesome4CSS.FA_BOOK),
  @Deprecated (forRemoval = true, since = "12.3.0")
  BOOKMARK(CFontAwesome4CSS.FA_BOOKMARK),
  @Deprecated (forRemoval = true, since = "12.3.0")
  BOOKMARK_O(CFontAwesome4CSS.FA_BOOKMARK_O),
  @Deprecated (forRemoval = true, since = "12.3.0")
  BRAILLE(CFontAwesome4CSS.FA_BRAILLE),
  @Deprecated (forRemoval = true, since = "12.3.0")
  BRIEFCASE(CFontAwesome4CSS.FA_BRIEFCASE),
  @Deprecated (forRemoval = true, since = "12.3.0")
  BTC(CFontAwesome4CSS.FA_BTC),
  @Deprecated (forRemoval = true, since = "12.3.0")
  BUG(CFontAwesome4CSS.FA_BUG),
  @Deprecated (forRemoval = true, since = "12.3.0")
  BUILDING(CFontAwesome4CSS.FA_BUILDING),
  @Deprecated (forRemoval = true, since = "12.3.0")
  BUILDING_O(CFontAwesome4CSS.FA_BUILDING_O),
  @Deprecated (forRemoval = true, since = "12.3.0")
  BULLHORN(CFontAwesome4CSS.FA_BULLHORN),
  @Deprecated (forRemoval = true, since = "12.3.0")
  BULLSEYE(CFontAwesome4CSS.FA_BULLSEYE),
  @Deprecated (forRemoval = true, since = "12.3.0")
  BUS(CFontAwesome4CSS.FA_BUS),
  @Deprecated (forRemoval = true, since = "12.3.0")
  BUYSELLADS(CFontAwesome4CSS.FA_BUYSELLADS),
  @Deprecated (forRemoval = true, since = "12.3.0")
  CAB(CFontAwesome4CSS.FA_CAB),
  @Deprecated (forRemoval = true, since = "12.3.0")
  CALCULATOR(CFontAwesome4CSS.FA_CALCULATOR),
  @Deprecated (forRemoval = true, since = "12.3.0")
  CALENDAR(CFontAwesome4CSS.FA_CALENDAR),
  @Deprecated (forRemoval = true, since = "12.3.0")
  CALENDAR_CHECK_O(CFontAwesome4CSS.FA_CALENDAR_CHECK_O),
  @Deprecated (forRemoval = true, since = "12.3.0")
  CALENDAR_MINUS_O(CFontAwesome4CSS.FA_CALENDAR_MINUS_O),
  @Deprecated (forRemoval = true, since = "12.3.0")
  CALENDAR_O(CFontAwesome4CSS.FA_CALENDAR_O),
  @Deprecated (forRemoval = true, since = "12.3.0")
  CALENDAR_PLUS_O(CFontAwesome4CSS.FA_CALENDAR_PLUS_O),
  @Deprecated (forRemoval = true, since = "12.3.0")
  CALENDAR_TIMES_O(CFontAwesome4CSS.FA_CALENDAR_TIMES_O),
  @Deprecated (forRemoval = true, since = "12.3.0")
  CAMERA(CFontAwesome4CSS.FA_CAMERA),
  @Deprecated (forRemoval = true, since = "12.3.0")
  CAMERA_RETRO(CFontAwesome4CSS.FA_CAMERA_RETRO),
  @Deprecated (forRemoval = true, since = "12.3.0")
  CAR(CFontAwesome4CSS.FA_CAR),
  @Deprecated (forRemoval = true, since = "12.3.0")
  CARET_DOWN(CFontAwesome4CSS.FA_CARET_DOWN),
  @Deprecated (forRemoval = true, since = "12.3.0")
  CARET_LEFT(CFontAwesome4CSS.FA_CARET_LEFT),
  @Deprecated (forRemoval = true, since = "12.3.0")
  CARET_RIGHT(CFontAwesome4CSS.FA_CARET_RIGHT),
  @Deprecated (forRemoval = true, since = "12.3.0")
  CARET_SQUARE_O_DOWN(CFontAwesome4CSS.FA_CARET_SQUARE_O_DOWN),
  @Deprecated (forRemoval = true, since = "12.3.0")
  CARET_SQUARE_O_LEFT(CFontAwesome4CSS.FA_CARET_SQUARE_O_LEFT),
  @Deprecated (forRemoval = true, since = "12.3.0")
  CARET_SQUARE_O_RIGHT(CFontAwesome4CSS.FA_CARET_SQUARE_O_RIGHT),
  @Deprecated (forRemoval = true, since = "12.3.0")
  CARET_SQUARE_O_UP(CFontAwesome4CSS.FA_CARET_SQUARE_O_UP),
  @Deprecated (forRemoval = true, since = "12.3.0")
  CARET_UP(CFontAwesome4CSS.FA_CARET_UP),
  @Deprecated (forRemoval = true, since = "12.3.0")
  CART_ARROW_DOWN(CFontAwesome4CSS.FA_CART_ARROW_DOWN),
  @Deprecated (forRemoval = true, since = "12.3.0")
  CART_PLUS(CFontAwesome4CSS.FA_CART_PLUS),
  @Deprecated (forRemoval = true, since = "12.3.0")
  CC(CFontAwesome4CSS.FA_CC),
  @Deprecated (forRemoval = true, since = "12.3.0")
  CC_AMEX(CFontAwesome4CSS.FA_CC_AMEX),
  @Deprecated (forRemoval = true, since = "12.3.0")
  CC_DINERS_CLUB(CFontAwesome4CSS.FA_CC_DINERS_CLUB),
  @Deprecated (forRemoval = true, since = "12.3.0")
  CC_DISCOVER(CFontAwesome4CSS.FA_CC_DISCOVER),
  @Deprecated (forRemoval = true, since = "12.3.0")
  CC_JCB(CFontAwesome4CSS.FA_CC_JCB),
  @Deprecated (forRemoval = true, since = "12.3.0")
  CC_MASTERCARD(CFontAwesome4CSS.FA_CC_MASTERCARD),
  @Deprecated (forRemoval = true, since = "12.3.0")
  CC_PAYPAL(CFontAwesome4CSS.FA_CC_PAYPAL),
  @Deprecated (forRemoval = true, since = "12.3.0")
  CC_STRIPE(CFontAwesome4CSS.FA_CC_STRIPE),
  @Deprecated (forRemoval = true, since = "12.3.0")
  CC_VISA(CFontAwesome4CSS.FA_CC_VISA),
  @Deprecated (forRemoval = true, since = "12.3.0")
  CERTIFICATE(CFontAwesome4CSS.FA_CERTIFICATE),
  @Deprecated (forRemoval = true, since = "12.3.0")
  CHAIN(CFontAwesome4CSS.FA_CHAIN),
  @Deprecated (forRemoval = true, since = "12.3.0")
  CHAIN_BROKEN(CFontAwesome4CSS.FA_CHAIN_BROKEN),
  @Deprecated (forRemoval = true, since = "12.3.0")
  CHECK(CFontAwesome4CSS.FA_CHECK),
  @Deprecated (forRemoval = true, since = "12.3.0")
  CHECK_CIRCLE(CFontAwesome4CSS.FA_CHECK_CIRCLE),
  @Deprecated (forRemoval = true, since = "12.3.0")
  CHECK_CIRCLE_O(CFontAwesome4CSS.FA_CHECK_CIRCLE_O),
  @Deprecated (forRemoval = true, since = "12.3.0")
  CHECK_SQUARE(CFontAwesome4CSS.FA_CHECK_SQUARE),
  @Deprecated (forRemoval = true, since = "12.3.0")
  CHECK_SQUARE_O(CFontAwesome4CSS.FA_CHECK_SQUARE_O),
  @Deprecated (forRemoval = true, since = "12.3.0")
  CHEVRON_CIRCLE_DOWN(CFontAwesome4CSS.FA_CHEVRON_CIRCLE_DOWN),
  @Deprecated (forRemoval = true, since = "12.3.0")
  CHEVRON_CIRCLE_LEFT(CFontAwesome4CSS.FA_CHEVRON_CIRCLE_LEFT),
  @Deprecated (forRemoval = true, since = "12.3.0")
  CHEVRON_CIRCLE_RIGHT(CFontAwesome4CSS.FA_CHEVRON_CIRCLE_RIGHT),
  @Deprecated (forRemoval = true, since = "12.3.0")
  CHEVRON_CIRCLE_UP(CFontAwesome4CSS.FA_CHEVRON_CIRCLE_UP),
  @Deprecated (forRemoval = true, since = "12.3.0")
  CHEVRON_DOWN(CFontAwesome4CSS.FA_CHEVRON_DOWN),
  @Deprecated (forRemoval = true, since = "12.3.0")
  CHEVRON_LEFT(CFontAwesome4CSS.FA_CHEVRON_LEFT),
  @Deprecated (forRemoval = true, since = "12.3.0")
  CHEVRON_RIGHT(CFontAwesome4CSS.FA_CHEVRON_RIGHT),
  @Deprecated (forRemoval = true, since = "12.3.0")
  CHEVRON_UP(CFontAwesome4CSS.FA_CHEVRON_UP),
  @Deprecated (forRemoval = true, since = "12.3.0")
  CHILD(CFontAwesome4CSS.FA_CHILD),
  @Deprecated (forRemoval = true, since = "12.3.0")
  CHROME(CFontAwesome4CSS.FA_CHROME),
  @Deprecated (forRemoval = true, since = "12.3.0")
  CIRCLE(CFontAwesome4CSS.FA_CIRCLE),
  @Deprecated (forRemoval = true, since = "12.3.0")
  CIRCLE_O(CFontAwesome4CSS.FA_CIRCLE_O),
  @Deprecated (forRemoval = true, since = "12.3.0")
  CIRCLE_O_NOTCH(CFontAwesome4CSS.FA_CIRCLE_O_NOTCH),
  @Deprecated (forRemoval = true, since = "12.3.0")
  CIRCLE_THIN(CFontAwesome4CSS.FA_CIRCLE_THIN),
  @Deprecated (forRemoval = true, since = "12.3.0")
  CLIPBOARD(CFontAwesome4CSS.FA_CLIPBOARD),
  @Deprecated (forRemoval = true, since = "12.3.0")
  CLOCK_O(CFontAwesome4CSS.FA_CLOCK_O),
  @Deprecated (forRemoval = true, since = "12.3.0")
  CLONE(CFontAwesome4CSS.FA_CLONE),
  @Deprecated (forRemoval = true, since = "12.3.0")
  CLOSE(CFontAwesome4CSS.FA_CLOSE),
  @Deprecated (forRemoval = true, since = "12.3.0")
  CLOUD(CFontAwesome4CSS.FA_CLOUD),
  @Deprecated (forRemoval = true, since = "12.3.0")
  CLOUD_DOWNLOAD(CFontAwesome4CSS.FA_CLOUD_DOWNLOAD),
  @Deprecated (forRemoval = true, since = "12.3.0")
  CLOUD_UPLOAD(CFontAwesome4CSS.FA_CLOUD_UPLOAD),
  @Deprecated (forRemoval = true, since = "12.3.0")
  CNY(CFontAwesome4CSS.FA_CNY),
  @Deprecated (forRemoval = true, since = "12.3.0")
  CODE(CFontAwesome4CSS.FA_CODE),
  @Deprecated (forRemoval = true, since = "12.3.0")
  CODE_FORK(CFontAwesome4CSS.FA_CODE_FORK),
  @Deprecated (forRemoval = true, since = "12.3.0")
  CODEPEN(CFontAwesome4CSS.FA_CODEPEN),
  @Deprecated (forRemoval = true, since = "12.3.0")
  CODIEPIE(CFontAwesome4CSS.FA_CODIEPIE),
  @Deprecated (forRemoval = true, since = "12.3.0")
  COFFEE(CFontAwesome4CSS.FA_COFFEE),
  @Deprecated (forRemoval = true, since = "12.3.0")
  COG(CFontAwesome4CSS.FA_COG),
  @Deprecated (forRemoval = true, since = "12.3.0")
  COGS(CFontAwesome4CSS.FA_COGS),
  @Deprecated (forRemoval = true, since = "12.3.0")
  COLUMNS(CFontAwesome4CSS.FA_COLUMNS),
  @Deprecated (forRemoval = true, since = "12.3.0")
  COMMENT(CFontAwesome4CSS.FA_COMMENT),
  @Deprecated (forRemoval = true, since = "12.3.0")
  COMMENT_O(CFontAwesome4CSS.FA_COMMENT_O),
  @Deprecated (forRemoval = true, since = "12.3.0")
  COMMENTING(CFontAwesome4CSS.FA_COMMENTING),
  @Deprecated (forRemoval = true, since = "12.3.0")
  COMMENTING_O(CFontAwesome4CSS.FA_COMMENTING_O),
  @Deprecated (forRemoval = true, since = "12.3.0")
  COMMENTS(CFontAwesome4CSS.FA_COMMENTS),
  @Deprecated (forRemoval = true, since = "12.3.0")
  COMMENTS_O(CFontAwesome4CSS.FA_COMMENTS_O),
  @Deprecated (forRemoval = true, since = "12.3.0")
  COMPASS(CFontAwesome4CSS.FA_COMPASS),
  @Deprecated (forRemoval = true, since = "12.3.0")
  COMPRESS(CFontAwesome4CSS.FA_COMPRESS),
  @Deprecated (forRemoval = true, since = "12.3.0")
  CONNECTDEVELOP(CFontAwesome4CSS.FA_CONNECTDEVELOP),
  @Deprecated (forRemoval = true, since = "12.3.0")
  CONTAO(CFontAwesome4CSS.FA_CONTAO),
  @Deprecated (forRemoval = true, since = "12.3.0")
  COPY(CFontAwesome4CSS.FA_COPY),
  @Deprecated (forRemoval = true, since = "12.3.0")
  COPYRIGHT(CFontAwesome4CSS.FA_COPYRIGHT),
  @Deprecated (forRemoval = true, since = "12.3.0")
  CREATIVE_COMMONS(CFontAwesome4CSS.FA_CREATIVE_COMMONS),
  @Deprecated (forRemoval = true, since = "12.3.0")
  CREDIT_CARD(CFontAwesome4CSS.FA_CREDIT_CARD),
  @Deprecated (forRemoval = true, since = "12.3.0")
  CREDIT_CARD_ALT(CFontAwesome4CSS.FA_CREDIT_CARD_ALT),
  @Deprecated (forRemoval = true, since = "12.3.0")
  CROP(CFontAwesome4CSS.FA_CROP),
  @Deprecated (forRemoval = true, since = "12.3.0")
  CROSSHAIRS(CFontAwesome4CSS.FA_CROSSHAIRS),
  @Deprecated (forRemoval = true, since = "12.3.0")
  CSS3(CFontAwesome4CSS.FA_CSS3),
  @Deprecated (forRemoval = true, since = "12.3.0")
  CUBE(CFontAwesome4CSS.FA_CUBE),
  @Deprecated (forRemoval = true, since = "12.3.0")
  CUBES(CFontAwesome4CSS.FA_CUBES),
  @Deprecated (forRemoval = true, since = "12.3.0")
  CUT(CFontAwesome4CSS.FA_CUT),
  @Deprecated (forRemoval = true, since = "12.3.0")
  CUTLERY(CFontAwesome4CSS.FA_CUTLERY),
  @Deprecated (forRemoval = true, since = "12.3.0")
  DASHBOARD(CFontAwesome4CSS.FA_DASHBOARD),
  @Deprecated (forRemoval = true, since = "12.3.0")
  DASHCUBE(CFontAwesome4CSS.FA_DASHCUBE),
  @Deprecated (forRemoval = true, since = "12.3.0")
  DATABASE(CFontAwesome4CSS.FA_DATABASE),
  @Deprecated (forRemoval = true, since = "12.3.0")
  DEAF(CFontAwesome4CSS.FA_DEAF),
  @Deprecated (forRemoval = true, since = "12.3.0")
  DEAFNESS(CFontAwesome4CSS.FA_DEAFNESS),
  @Deprecated (forRemoval = true, since = "12.3.0")
  DEDENT(CFontAwesome4CSS.FA_DEDENT),
  @Deprecated (forRemoval = true, since = "12.3.0")
  DELICIOUS(CFontAwesome4CSS.FA_DELICIOUS),
  @Deprecated (forRemoval = true, since = "12.3.0")
  DESKTOP(CFontAwesome4CSS.FA_DESKTOP),
  @Deprecated (forRemoval = true, since = "12.3.0")
  DEVIANTART(CFontAwesome4CSS.FA_DEVIANTART),
  @Deprecated (forRemoval = true, since = "12.3.0")
  DIAMOND(CFontAwesome4CSS.FA_DIAMOND),
  @Deprecated (forRemoval = true, since = "12.3.0")
  DIGG(CFontAwesome4CSS.FA_DIGG),
  @Deprecated (forRemoval = true, since = "12.3.0")
  DOLLAR(CFontAwesome4CSS.FA_DOLLAR),
  @Deprecated (forRemoval = true, since = "12.3.0")
  DOT_CIRCLE_O(CFontAwesome4CSS.FA_DOT_CIRCLE_O),
  @Deprecated (forRemoval = true, since = "12.3.0")
  DOWNLOAD(CFontAwesome4CSS.FA_DOWNLOAD),
  @Deprecated (forRemoval = true, since = "12.3.0")
  DRIBBBLE(CFontAwesome4CSS.FA_DRIBBBLE),
  @Deprecated (forRemoval = true, since = "12.3.0")
  DRIVERS_LICENSE(CFontAwesome4CSS.FA_DRIVERS_LICENSE),
  @Deprecated (forRemoval = true, since = "12.3.0")
  DRIVERS_LICENSE_O(CFontAwesome4CSS.FA_DRIVERS_LICENSE_O),
  @Deprecated (forRemoval = true, since = "12.3.0")
  DROPBOX(CFontAwesome4CSS.FA_DROPBOX),
  @Deprecated (forRemoval = true, since = "12.3.0")
  DRUPAL(CFontAwesome4CSS.FA_DRUPAL),
  @Deprecated (forRemoval = true, since = "12.3.0")
  EDGE(CFontAwesome4CSS.FA_EDGE),
  @Deprecated (forRemoval = true, since = "12.3.0")
  EDIT(CFontAwesome4CSS.FA_EDIT),
  @Deprecated (forRemoval = true, since = "12.3.0")
  EERCAST(CFontAwesome4CSS.FA_EERCAST),
  @Deprecated (forRemoval = true, since = "12.3.0")
  EJECT(CFontAwesome4CSS.FA_EJECT),
  @Deprecated (forRemoval = true, since = "12.3.0")
  ELLIPSIS_H(CFontAwesome4CSS.FA_ELLIPSIS_H),
  @Deprecated (forRemoval = true, since = "12.3.0")
  ELLIPSIS_V(CFontAwesome4CSS.FA_ELLIPSIS_V),
  @Deprecated (forRemoval = true, since = "12.3.0")
  EMPIRE(CFontAwesome4CSS.FA_EMPIRE),
  @Deprecated (forRemoval = true, since = "12.3.0")
  ENVELOPE(CFontAwesome4CSS.FA_ENVELOPE),
  @Deprecated (forRemoval = true, since = "12.3.0")
  ENVELOPE_O(CFontAwesome4CSS.FA_ENVELOPE_O),
  @Deprecated (forRemoval = true, since = "12.3.0")
  ENVELOPE_OPEN(CFontAwesome4CSS.FA_ENVELOPE_OPEN),
  @Deprecated (forRemoval = true, since = "12.3.0")
  ENVELOPE_OPEN_O(CFontAwesome4CSS.FA_ENVELOPE_OPEN_O),
  @Deprecated (forRemoval = true, since = "12.3.0")
  ENVELOPE_SQUARE(CFontAwesome4CSS.FA_ENVELOPE_SQUARE),
  @Deprecated (forRemoval = true, since = "12.3.0")
  ENVIRA(CFontAwesome4CSS.FA_ENVIRA),
  @Deprecated (forRemoval = true, since = "12.3.0")
  ERASER(CFontAwesome4CSS.FA_ERASER),
  @Deprecated (forRemoval = true, since = "12.3.0")
  ETSY(CFontAwesome4CSS.FA_ETSY),
  @Deprecated (forRemoval = true, since = "12.3.0")
  EUR(CFontAwesome4CSS.FA_EUR),
  @Deprecated (forRemoval = true, since = "12.3.0")
  EURO(CFontAwesome4CSS.FA_EURO),
  @Deprecated (forRemoval = true, since = "12.3.0")
  EXCHANGE(CFontAwesome4CSS.FA_EXCHANGE),
  @Deprecated (forRemoval = true, since = "12.3.0")
  EXCLAMATION(CFontAwesome4CSS.FA_EXCLAMATION),
  @Deprecated (forRemoval = true, since = "12.3.0")
  EXCLAMATION_CIRCLE(CFontAwesome4CSS.FA_EXCLAMATION_CIRCLE),
  @Deprecated (forRemoval = true, since = "12.3.0")
  EXCLAMATION_TRIANGLE(CFontAwesome4CSS.FA_EXCLAMATION_TRIANGLE),
  @Deprecated (forRemoval = true, since = "12.3.0")
  EXPAND(CFontAwesome4CSS.FA_EXPAND),
  @Deprecated (forRemoval = true, since = "12.3.0")
  EXPEDITEDSSL(CFontAwesome4CSS.FA_EXPEDITEDSSL),
  @Deprecated (forRemoval = true, since = "12.3.0")
  EXTERNAL_LINK(CFontAwesome4CSS.FA_EXTERNAL_LINK),
  @Deprecated (forRemoval = true, since = "12.3.0")
  EXTERNAL_LINK_SQUARE(CFontAwesome4CSS.FA_EXTERNAL_LINK_SQUARE),
  @Deprecated (forRemoval = true, since = "12.3.0")
  EYE(CFontAwesome4CSS.FA_EYE),
  @Deprecated (forRemoval = true, since = "12.3.0")
  EYE_SLASH(CFontAwesome4CSS.FA_EYE_SLASH),
  @Deprecated (forRemoval = true, since = "12.3.0")
  EYEDROPPER(CFontAwesome4CSS.FA_EYEDROPPER),
  @Deprecated (forRemoval = true, since = "12.3.0")
  FA(CFontAwesome4CSS.FA_FA),
  @Deprecated (forRemoval = true, since = "12.3.0")
  FACEBOOK(CFontAwesome4CSS.FA_FACEBOOK),
  @Deprecated (forRemoval = true, since = "12.3.0")
  FACEBOOK_F(CFontAwesome4CSS.FA_FACEBOOK_F),
  @Deprecated (forRemoval = true, since = "12.3.0")
  FACEBOOK_OFFICIAL(CFontAwesome4CSS.FA_FACEBOOK_OFFICIAL),
  @Deprecated (forRemoval = true, since = "12.3.0")
  FACEBOOK_SQUARE(CFontAwesome4CSS.FA_FACEBOOK_SQUARE),
  @Deprecated (forRemoval = true, since = "12.3.0")
  FAST_BACKWARD(CFontAwesome4CSS.FA_FAST_BACKWARD),
  @Deprecated (forRemoval = true, since = "12.3.0")
  FAST_FORWARD(CFontAwesome4CSS.FA_FAST_FORWARD),
  @Deprecated (forRemoval = true, since = "12.3.0")
  FAX(CFontAwesome4CSS.FA_FAX),
  @Deprecated (forRemoval = true, since = "12.3.0")
  FEED(CFontAwesome4CSS.FA_FEED),
  @Deprecated (forRemoval = true, since = "12.3.0")
  FEMALE(CFontAwesome4CSS.FA_FEMALE),
  @Deprecated (forRemoval = true, since = "12.3.0")
  FIGHTER_JET(CFontAwesome4CSS.FA_FIGHTER_JET),
  @Deprecated (forRemoval = true, since = "12.3.0")
  FILE(CFontAwesome4CSS.FA_FILE),
  @Deprecated (forRemoval = true, since = "12.3.0")
  FILE_ARCHIVE_O(CFontAwesome4CSS.FA_FILE_ARCHIVE_O),
  @Deprecated (forRemoval = true, since = "12.3.0")
  FILE_AUDIO_O(CFontAwesome4CSS.FA_FILE_AUDIO_O),
  @Deprecated (forRemoval = true, since = "12.3.0")
  FILE_CODE_O(CFontAwesome4CSS.FA_FILE_CODE_O),
  @Deprecated (forRemoval = true, since = "12.3.0")
  FILE_EXCEL_O(CFontAwesome4CSS.FA_FILE_EXCEL_O),
  @Deprecated (forRemoval = true, since = "12.3.0")
  FILE_IMAGE_O(CFontAwesome4CSS.FA_FILE_IMAGE_O),
  @Deprecated (forRemoval = true, since = "12.3.0")
  FILE_MOVIE_O(CFontAwesome4CSS.FA_FILE_MOVIE_O),
  @Deprecated (forRemoval = true, since = "12.3.0")
  FILE_O(CFontAwesome4CSS.FA_FILE_O),
  @Deprecated (forRemoval = true, since = "12.3.0")
  FILE_PDF_O(CFontAwesome4CSS.FA_FILE_PDF_O),
  @Deprecated (forRemoval = true, since = "12.3.0")
  FILE_PHOTO_O(CFontAwesome4CSS.FA_FILE_PHOTO_O),
  @Deprecated (forRemoval = true, since = "12.3.0")
  FILE_PICTURE_O(CFontAwesome4CSS.FA_FILE_PICTURE_O),
  @Deprecated (forRemoval = true, since = "12.3.0")
  FILE_POWERPOINT_O(CFontAwesome4CSS.FA_FILE_POWERPOINT_O),
  @Deprecated (forRemoval = true, since = "12.3.0")
  FILE_SOUND_O(CFontAwesome4CSS.FA_FILE_SOUND_O),
  @Deprecated (forRemoval = true, since = "12.3.0")
  FILE_TEXT(CFontAwesome4CSS.FA_FILE_TEXT),
  @Deprecated (forRemoval = true, since = "12.3.0")
  FILE_TEXT_O(CFontAwesome4CSS.FA_FILE_TEXT_O),
  @Deprecated (forRemoval = true, since = "12.3.0")
  FILE_VIDEO_O(CFontAwesome4CSS.FA_FILE_VIDEO_O),
  @Deprecated (forRemoval = true, since = "12.3.0")
  FILE_WORD_O(CFontAwesome4CSS.FA_FILE_WORD_O),
  @Deprecated (forRemoval = true, since = "12.3.0")
  FILE_ZIP_O(CFontAwesome4CSS.FA_FILE_ZIP_O),
  @Deprecated (forRemoval = true, since = "12.3.0")
  FILES_O(CFontAwesome4CSS.FA_FILES_O),
  @Deprecated (forRemoval = true, since = "12.3.0")
  FILM(CFontAwesome4CSS.FA_FILM),
  @Deprecated (forRemoval = true, since = "12.3.0")
  FILTER(CFontAwesome4CSS.FA_FILTER),
  @Deprecated (forRemoval = true, since = "12.3.0")
  FIRE(CFontAwesome4CSS.FA_FIRE),
  @Deprecated (forRemoval = true, since = "12.3.0")
  FIRE_EXTINGUISHER(CFontAwesome4CSS.FA_FIRE_EXTINGUISHER),
  @Deprecated (forRemoval = true, since = "12.3.0")
  FIREFOX(CFontAwesome4CSS.FA_FIREFOX),
  @Deprecated (forRemoval = true, since = "12.3.0")
  FIRST_ORDER(CFontAwesome4CSS.FA_FIRST_ORDER),
  @Deprecated (forRemoval = true, since = "12.3.0")
  FLAG(CFontAwesome4CSS.FA_FLAG),
  @Deprecated (forRemoval = true, since = "12.3.0")
  FLAG_CHECKERED(CFontAwesome4CSS.FA_FLAG_CHECKERED),
  @Deprecated (forRemoval = true, since = "12.3.0")
  FLAG_O(CFontAwesome4CSS.FA_FLAG_O),
  @Deprecated (forRemoval = true, since = "12.3.0")
  FLASH(CFontAwesome4CSS.FA_FLASH),
  @Deprecated (forRemoval = true, since = "12.3.0")
  FLASK(CFontAwesome4CSS.FA_FLASK),
  @Deprecated (forRemoval = true, since = "12.3.0")
  FLICKR(CFontAwesome4CSS.FA_FLICKR),
  @Deprecated (forRemoval = true, since = "12.3.0")
  FLOPPY_O(CFontAwesome4CSS.FA_FLOPPY_O),
  @Deprecated (forRemoval = true, since = "12.3.0")
  FOLDER(CFontAwesome4CSS.FA_FOLDER),
  @Deprecated (forRemoval = true, since = "12.3.0")
  FOLDER_O(CFontAwesome4CSS.FA_FOLDER_O),
  @Deprecated (forRemoval = true, since = "12.3.0")
  FOLDER_OPEN(CFontAwesome4CSS.FA_FOLDER_OPEN),
  @Deprecated (forRemoval = true, since = "12.3.0")
  FOLDER_OPEN_O(CFontAwesome4CSS.FA_FOLDER_OPEN_O),
  @Deprecated (forRemoval = true, since = "12.3.0")
  FONT(CFontAwesome4CSS.FA_FONT),
  @Deprecated (forRemoval = true, since = "12.3.0")
  FONT_AWESOME(CFontAwesome4CSS.FA_FONT_AWESOME),
  @Deprecated (forRemoval = true, since = "12.3.0")
  FONTICONS(CFontAwesome4CSS.FA_FONTICONS),
  @Deprecated (forRemoval = true, since = "12.3.0")
  FORT_AWESOME(CFontAwesome4CSS.FA_FORT_AWESOME),
  @Deprecated (forRemoval = true, since = "12.3.0")
  FORUMBEE(CFontAwesome4CSS.FA_FORUMBEE),
  @Deprecated (forRemoval = true, since = "12.3.0")
  FORWARD(CFontAwesome4CSS.FA_FORWARD),
  @Deprecated (forRemoval = true, since = "12.3.0")
  FOURSQUARE(CFontAwesome4CSS.FA_FOURSQUARE),
  @Deprecated (forRemoval = true, since = "12.3.0")
  FREE_CODE_CAMP(CFontAwesome4CSS.FA_FREE_CODE_CAMP),
  @Deprecated (forRemoval = true, since = "12.3.0")
  FROWN_O(CFontAwesome4CSS.FA_FROWN_O),
  @Deprecated (forRemoval = true, since = "12.3.0")
  FUTBOL_O(CFontAwesome4CSS.FA_FUTBOL_O),
  @Deprecated (forRemoval = true, since = "12.3.0")
  GAMEPAD(CFontAwesome4CSS.FA_GAMEPAD),
  @Deprecated (forRemoval = true, since = "12.3.0")
  GAVEL(CFontAwesome4CSS.FA_GAVEL),
  @Deprecated (forRemoval = true, since = "12.3.0")
  GBP(CFontAwesome4CSS.FA_GBP),
  @Deprecated (forRemoval = true, since = "12.3.0")
  GE(CFontAwesome4CSS.FA_GE),
  @Deprecated (forRemoval = true, since = "12.3.0")
  GEAR(CFontAwesome4CSS.FA_GEAR),
  @Deprecated (forRemoval = true, since = "12.3.0")
  GEARS(CFontAwesome4CSS.FA_GEARS),
  @Deprecated (forRemoval = true, since = "12.3.0")
  GENDERLESS(CFontAwesome4CSS.FA_GENDERLESS),
  @Deprecated (forRemoval = true, since = "12.3.0")
  GET_POCKET(CFontAwesome4CSS.FA_GET_POCKET),
  @Deprecated (forRemoval = true, since = "12.3.0")
  GG(CFontAwesome4CSS.FA_GG),
  @Deprecated (forRemoval = true, since = "12.3.0")
  GG_CIRCLE(CFontAwesome4CSS.FA_GG_CIRCLE),
  @Deprecated (forRemoval = true, since = "12.3.0")
  GIFT(CFontAwesome4CSS.FA_GIFT),
  @Deprecated (forRemoval = true, since = "12.3.0")
  GIT(CFontAwesome4CSS.FA_GIT),
  @Deprecated (forRemoval = true, since = "12.3.0")
  GIT_SQUARE(CFontAwesome4CSS.FA_GIT_SQUARE),
  @Deprecated (forRemoval = true, since = "12.3.0")
  GITHUB(CFontAwesome4CSS.FA_GITHUB),
  @Deprecated (forRemoval = true, since = "12.3.0")
  GITHUB_ALT(CFontAwesome4CSS.FA_GITHUB_ALT),
  @Deprecated (forRemoval = true, since = "12.3.0")
  GITHUB_SQUARE(CFontAwesome4CSS.FA_GITHUB_SQUARE),
  @Deprecated (forRemoval = true, since = "12.3.0")
  GITLAB(CFontAwesome4CSS.FA_GITLAB),
  @Deprecated (forRemoval = true, since = "12.3.0")
  GITTIP(CFontAwesome4CSS.FA_GITTIP),
  @Deprecated (forRemoval = true, since = "12.3.0")
  GLASS(CFontAwesome4CSS.FA_GLASS),
  @Deprecated (forRemoval = true, since = "12.3.0")
  GLIDE(CFontAwesome4CSS.FA_GLIDE),
  @Deprecated (forRemoval = true, since = "12.3.0")
  GLIDE_G(CFontAwesome4CSS.FA_GLIDE_G),
  @Deprecated (forRemoval = true, since = "12.3.0")
  GLOBE(CFontAwesome4CSS.FA_GLOBE),
  @Deprecated (forRemoval = true, since = "12.3.0")
  GOOGLE(CFontAwesome4CSS.FA_GOOGLE),
  @Deprecated (forRemoval = true, since = "12.3.0")
  GOOGLE_PLUS(CFontAwesome4CSS.FA_GOOGLE_PLUS),
  @Deprecated (forRemoval = true, since = "12.3.0")
  GOOGLE_PLUS_CIRCLE(CFontAwesome4CSS.FA_GOOGLE_PLUS_CIRCLE),
  @Deprecated (forRemoval = true, since = "12.3.0")
  GOOGLE_PLUS_OFFICIAL(CFontAwesome4CSS.FA_GOOGLE_PLUS_OFFICIAL),
  @Deprecated (forRemoval = true, since = "12.3.0")
  GOOGLE_PLUS_SQUARE(CFontAwesome4CSS.FA_GOOGLE_PLUS_SQUARE),
  @Deprecated (forRemoval = true, since = "12.3.0")
  GOOGLE_WALLET(CFontAwesome4CSS.FA_GOOGLE_WALLET),
  @Deprecated (forRemoval = true, since = "12.3.0")
  GRADUATION_CAP(CFontAwesome4CSS.FA_GRADUATION_CAP),
  @Deprecated (forRemoval = true, since = "12.3.0")
  GRATIPAY(CFontAwesome4CSS.FA_GRATIPAY),
  @Deprecated (forRemoval = true, since = "12.3.0")
  GRAV(CFontAwesome4CSS.FA_GRAV),
  @Deprecated (forRemoval = true, since = "12.3.0")
  GROUP(CFontAwesome4CSS.FA_GROUP),
  @Deprecated (forRemoval = true, since = "12.3.0")
  H_SQUARE(CFontAwesome4CSS.FA_H_SQUARE),
  @Deprecated (forRemoval = true, since = "12.3.0")
  HACKER_NEWS(CFontAwesome4CSS.FA_HACKER_NEWS),
  @Deprecated (forRemoval = true, since = "12.3.0")
  HAND_GRAB_O(CFontAwesome4CSS.FA_HAND_GRAB_O),
  @Deprecated (forRemoval = true, since = "12.3.0")
  HAND_LIZARD_O(CFontAwesome4CSS.FA_HAND_LIZARD_O),
  @Deprecated (forRemoval = true, since = "12.3.0")
  HAND_O_DOWN(CFontAwesome4CSS.FA_HAND_O_DOWN),
  @Deprecated (forRemoval = true, since = "12.3.0")
  HAND_O_LEFT(CFontAwesome4CSS.FA_HAND_O_LEFT),
  @Deprecated (forRemoval = true, since = "12.3.0")
  HAND_O_RIGHT(CFontAwesome4CSS.FA_HAND_O_RIGHT),
  @Deprecated (forRemoval = true, since = "12.3.0")
  HAND_O_UP(CFontAwesome4CSS.FA_HAND_O_UP),
  @Deprecated (forRemoval = true, since = "12.3.0")
  HAND_PAPER_O(CFontAwesome4CSS.FA_HAND_PAPER_O),
  @Deprecated (forRemoval = true, since = "12.3.0")
  HAND_PEACE_O(CFontAwesome4CSS.FA_HAND_PEACE_O),
  @Deprecated (forRemoval = true, since = "12.3.0")
  HAND_POINTER_O(CFontAwesome4CSS.FA_HAND_POINTER_O),
  @Deprecated (forRemoval = true, since = "12.3.0")
  HAND_ROCK_O(CFontAwesome4CSS.FA_HAND_ROCK_O),
  @Deprecated (forRemoval = true, since = "12.3.0")
  HAND_SCISSORS_O(CFontAwesome4CSS.FA_HAND_SCISSORS_O),
  @Deprecated (forRemoval = true, since = "12.3.0")
  HAND_SPOCK_O(CFontAwesome4CSS.FA_HAND_SPOCK_O),
  @Deprecated (forRemoval = true, since = "12.3.0")
  HAND_STOP_O(CFontAwesome4CSS.FA_HAND_STOP_O),
  @Deprecated (forRemoval = true, since = "12.3.0")
  HANDSHAKE_O(CFontAwesome4CSS.FA_HANDSHAKE_O),
  @Deprecated (forRemoval = true, since = "12.3.0")
  HARD_OF_HEARING(CFontAwesome4CSS.FA_HARD_OF_HEARING),
  @Deprecated (forRemoval = true, since = "12.3.0")
  HASHTAG(CFontAwesome4CSS.FA_HASHTAG),
  @Deprecated (forRemoval = true, since = "12.3.0")
  HDD_O(CFontAwesome4CSS.FA_HDD_O),
  @Deprecated (forRemoval = true, since = "12.3.0")
  HEADER(CFontAwesome4CSS.FA_HEADER),
  @Deprecated (forRemoval = true, since = "12.3.0")
  HEADPHONES(CFontAwesome4CSS.FA_HEADPHONES),
  @Deprecated (forRemoval = true, since = "12.3.0")
  HEART(CFontAwesome4CSS.FA_HEART),
  @Deprecated (forRemoval = true, since = "12.3.0")
  HEART_O(CFontAwesome4CSS.FA_HEART_O),
  @Deprecated (forRemoval = true, since = "12.3.0")
  HEARTBEAT(CFontAwesome4CSS.FA_HEARTBEAT),
  @Deprecated (forRemoval = true, since = "12.3.0")
  HISTORY(CFontAwesome4CSS.FA_HISTORY),
  @Deprecated (forRemoval = true, since = "12.3.0")
  HOME(CFontAwesome4CSS.FA_HOME),
  @Deprecated (forRemoval = true, since = "12.3.0")
  HOSPITAL_O(CFontAwesome4CSS.FA_HOSPITAL_O),
  @Deprecated (forRemoval = true, since = "12.3.0")
  HOTEL(CFontAwesome4CSS.FA_HOTEL),
  @Deprecated (forRemoval = true, since = "12.3.0")
  HOURGLASS(CFontAwesome4CSS.FA_HOURGLASS),
  @Deprecated (forRemoval = true, since = "12.3.0")
  HOURGLASS_1(CFontAwesome4CSS.FA_HOURGLASS_1),
  @Deprecated (forRemoval = true, since = "12.3.0")
  HOURGLASS_2(CFontAwesome4CSS.FA_HOURGLASS_2),
  @Deprecated (forRemoval = true, since = "12.3.0")
  HOURGLASS_3(CFontAwesome4CSS.FA_HOURGLASS_3),
  @Deprecated (forRemoval = true, since = "12.3.0")
  HOURGLASS_END(CFontAwesome4CSS.FA_HOURGLASS_END),
  @Deprecated (forRemoval = true, since = "12.3.0")
  HOURGLASS_HALF(CFontAwesome4CSS.FA_HOURGLASS_HALF),
  @Deprecated (forRemoval = true, since = "12.3.0")
  HOURGLASS_O(CFontAwesome4CSS.FA_HOURGLASS_O),
  @Deprecated (forRemoval = true, since = "12.3.0")
  HOURGLASS_START(CFontAwesome4CSS.FA_HOURGLASS_START),
  @Deprecated (forRemoval = true, since = "12.3.0")
  HOUZZ(CFontAwesome4CSS.FA_HOUZZ),
  @Deprecated (forRemoval = true, since = "12.3.0")
  HTML5(CFontAwesome4CSS.FA_HTML5),
  @Deprecated (forRemoval = true, since = "12.3.0")
  I_CURSOR(CFontAwesome4CSS.FA_I_CURSOR),
  @Deprecated (forRemoval = true, since = "12.3.0")
  ID_BADGE(CFontAwesome4CSS.FA_ID_BADGE),
  @Deprecated (forRemoval = true, since = "12.3.0")
  ID_CARD(CFontAwesome4CSS.FA_ID_CARD),
  @Deprecated (forRemoval = true, since = "12.3.0")
  ID_CARD_O(CFontAwesome4CSS.FA_ID_CARD_O),
  @Deprecated (forRemoval = true, since = "12.3.0")
  ILS(CFontAwesome4CSS.FA_ILS),
  @Deprecated (forRemoval = true, since = "12.3.0")
  IMAGE(CFontAwesome4CSS.FA_IMAGE),
  @Deprecated (forRemoval = true, since = "12.3.0")
  IMDB(CFontAwesome4CSS.FA_IMDB),
  @Deprecated (forRemoval = true, since = "12.3.0")
  INBOX(CFontAwesome4CSS.FA_INBOX),
  @Deprecated (forRemoval = true, since = "12.3.0")
  INDENT(CFontAwesome4CSS.FA_INDENT),
  @Deprecated (forRemoval = true, since = "12.3.0")
  INDUSTRY(CFontAwesome4CSS.FA_INDUSTRY),
  @Deprecated (forRemoval = true, since = "12.3.0")
  INFO(CFontAwesome4CSS.FA_INFO),
  @Deprecated (forRemoval = true, since = "12.3.0")
  INFO_CIRCLE(CFontAwesome4CSS.FA_INFO_CIRCLE),
  @Deprecated (forRemoval = true, since = "12.3.0")
  INR(CFontAwesome4CSS.FA_INR),
  @Deprecated (forRemoval = true, since = "12.3.0")
  INSTAGRAM(CFontAwesome4CSS.FA_INSTAGRAM),
  @Deprecated (forRemoval = true, since = "12.3.0")
  INSTITUTION(CFontAwesome4CSS.FA_INSTITUTION),
  @Deprecated (forRemoval = true, since = "12.3.0")
  INTERNET_EXPLORER(CFontAwesome4CSS.FA_INTERNET_EXPLORER),
  @Deprecated (forRemoval = true, since = "12.3.0")
  INTERSEX(CFontAwesome4CSS.FA_INTERSEX),
  @Deprecated (forRemoval = true, since = "12.3.0")
  IOXHOST(CFontAwesome4CSS.FA_IOXHOST),
  @Deprecated (forRemoval = true, since = "12.3.0")
  ITALIC(CFontAwesome4CSS.FA_ITALIC),
  @Deprecated (forRemoval = true, since = "12.3.0")
  JOOMLA(CFontAwesome4CSS.FA_JOOMLA),
  @Deprecated (forRemoval = true, since = "12.3.0")
  JPY(CFontAwesome4CSS.FA_JPY),
  @Deprecated (forRemoval = true, since = "12.3.0")
  JSFIDDLE(CFontAwesome4CSS.FA_JSFIDDLE),
  @Deprecated (forRemoval = true, since = "12.3.0")
  KEY(CFontAwesome4CSS.FA_KEY),
  @Deprecated (forRemoval = true, since = "12.3.0")
  KEYBOARD_O(CFontAwesome4CSS.FA_KEYBOARD_O),
  @Deprecated (forRemoval = true, since = "12.3.0")
  KRW(CFontAwesome4CSS.FA_KRW),
  @Deprecated (forRemoval = true, since = "12.3.0")
  LANGUAGE(CFontAwesome4CSS.FA_LANGUAGE),
  @Deprecated (forRemoval = true, since = "12.3.0")
  LAPTOP(CFontAwesome4CSS.FA_LAPTOP),
  @Deprecated (forRemoval = true, since = "12.3.0")
  LASTFM(CFontAwesome4CSS.FA_LASTFM),
  @Deprecated (forRemoval = true, since = "12.3.0")
  LASTFM_SQUARE(CFontAwesome4CSS.FA_LASTFM_SQUARE),
  @Deprecated (forRemoval = true, since = "12.3.0")
  LEAF(CFontAwesome4CSS.FA_LEAF),
  @Deprecated (forRemoval = true, since = "12.3.0")
  LEANPUB(CFontAwesome4CSS.FA_LEANPUB),
  @Deprecated (forRemoval = true, since = "12.3.0")
  LEGAL(CFontAwesome4CSS.FA_LEGAL),
  @Deprecated (forRemoval = true, since = "12.3.0")
  LEMON_O(CFontAwesome4CSS.FA_LEMON_O),
  @Deprecated (forRemoval = true, since = "12.3.0")
  LEVEL_DOWN(CFontAwesome4CSS.FA_LEVEL_DOWN),
  @Deprecated (forRemoval = true, since = "12.3.0")
  LEVEL_UP(CFontAwesome4CSS.FA_LEVEL_UP),
  @Deprecated (forRemoval = true, since = "12.3.0")
  LIFE_BOUY(CFontAwesome4CSS.FA_LIFE_BOUY),
  @Deprecated (forRemoval = true, since = "12.3.0")
  LIFE_BUOY(CFontAwesome4CSS.FA_LIFE_BUOY),
  @Deprecated (forRemoval = true, since = "12.3.0")
  LIFE_RING(CFontAwesome4CSS.FA_LIFE_RING),
  @Deprecated (forRemoval = true, since = "12.3.0")
  LIFE_SAVER(CFontAwesome4CSS.FA_LIFE_SAVER),
  @Deprecated (forRemoval = true, since = "12.3.0")
  LIGHTBULB_O(CFontAwesome4CSS.FA_LIGHTBULB_O),
  @Deprecated (forRemoval = true, since = "12.3.0")
  LINE_CHART(CFontAwesome4CSS.FA_LINE_CHART),
  @Deprecated (forRemoval = true, since = "12.3.0")
  LINK(CFontAwesome4CSS.FA_LINK),
  @Deprecated (forRemoval = true, since = "12.3.0")
  LINKEDIN(CFontAwesome4CSS.FA_LINKEDIN),
  @Deprecated (forRemoval = true, since = "12.3.0")
  LINKEDIN_SQUARE(CFontAwesome4CSS.FA_LINKEDIN_SQUARE),
  @Deprecated (forRemoval = true, since = "12.3.0")
  LINODE(CFontAwesome4CSS.FA_LINODE),
  @Deprecated (forRemoval = true, since = "12.3.0")
  LINUX(CFontAwesome4CSS.FA_LINUX),
  @Deprecated (forRemoval = true, since = "12.3.0")
  LIST(CFontAwesome4CSS.FA_LIST),
  @Deprecated (forRemoval = true, since = "12.3.0")
  LIST_ALT(CFontAwesome4CSS.FA_LIST_ALT),
  @Deprecated (forRemoval = true, since = "12.3.0")
  LIST_OL(CFontAwesome4CSS.FA_LIST_OL),
  @Deprecated (forRemoval = true, since = "12.3.0")
  LIST_UL(CFontAwesome4CSS.FA_LIST_UL),
  @Deprecated (forRemoval = true, since = "12.3.0")
  LOCATION_ARROW(CFontAwesome4CSS.FA_LOCATION_ARROW),
  @Deprecated (forRemoval = true, since = "12.3.0")
  LOCK(CFontAwesome4CSS.FA_LOCK),
  @Deprecated (forRemoval = true, since = "12.3.0")
  LONG_ARROW_DOWN(CFontAwesome4CSS.FA_LONG_ARROW_DOWN),
  @Deprecated (forRemoval = true, since = "12.3.0")
  LONG_ARROW_LEFT(CFontAwesome4CSS.FA_LONG_ARROW_LEFT),
  @Deprecated (forRemoval = true, since = "12.3.0")
  LONG_ARROW_RIGHT(CFontAwesome4CSS.FA_LONG_ARROW_RIGHT),
  @Deprecated (forRemoval = true, since = "12.3.0")
  LONG_ARROW_UP(CFontAwesome4CSS.FA_LONG_ARROW_UP),
  @Deprecated (forRemoval = true, since = "12.3.0")
  LOW_VISION(CFontAwesome4CSS.FA_LOW_VISION),
  @Deprecated (forRemoval = true, since = "12.3.0")
  MAGIC(CFontAwesome4CSS.FA_MAGIC),
  @Deprecated (forRemoval = true, since = "12.3.0")
  MAGNET(CFontAwesome4CSS.FA_MAGNET),
  @Deprecated (forRemoval = true, since = "12.3.0")
  MAIL_FORWARD(CFontAwesome4CSS.FA_MAIL_FORWARD),
  @Deprecated (forRemoval = true, since = "12.3.0")
  MAIL_REPLY(CFontAwesome4CSS.FA_MAIL_REPLY),
  @Deprecated (forRemoval = true, since = "12.3.0")
  MAIL_REPLY_ALL(CFontAwesome4CSS.FA_MAIL_REPLY_ALL),
  @Deprecated (forRemoval = true, since = "12.3.0")
  MALE(CFontAwesome4CSS.FA_MALE),
  @Deprecated (forRemoval = true, since = "12.3.0")
  MAP(CFontAwesome4CSS.FA_MAP),
  @Deprecated (forRemoval = true, since = "12.3.0")
  MAP_MARKER(CFontAwesome4CSS.FA_MAP_MARKER),
  @Deprecated (forRemoval = true, since = "12.3.0")
  MAP_O(CFontAwesome4CSS.FA_MAP_O),
  @Deprecated (forRemoval = true, since = "12.3.0")
  MAP_PIN(CFontAwesome4CSS.FA_MAP_PIN),
  @Deprecated (forRemoval = true, since = "12.3.0")
  MAP_SIGNS(CFontAwesome4CSS.FA_MAP_SIGNS),
  @Deprecated (forRemoval = true, since = "12.3.0")
  MARS(CFontAwesome4CSS.FA_MARS),
  @Deprecated (forRemoval = true, since = "12.3.0")
  MARS_DOUBLE(CFontAwesome4CSS.FA_MARS_DOUBLE),
  @Deprecated (forRemoval = true, since = "12.3.0")
  MARS_STROKE(CFontAwesome4CSS.FA_MARS_STROKE),
  @Deprecated (forRemoval = true, since = "12.3.0")
  MARS_STROKE_H(CFontAwesome4CSS.FA_MARS_STROKE_H),
  @Deprecated (forRemoval = true, since = "12.3.0")
  MARS_STROKE_V(CFontAwesome4CSS.FA_MARS_STROKE_V),
  @Deprecated (forRemoval = true, since = "12.3.0")
  MAXCDN(CFontAwesome4CSS.FA_MAXCDN),
  @Deprecated (forRemoval = true, since = "12.3.0")
  MEANPATH(CFontAwesome4CSS.FA_MEANPATH),
  @Deprecated (forRemoval = true, since = "12.3.0")
  MEDIUM(CFontAwesome4CSS.FA_MEDIUM),
  @Deprecated (forRemoval = true, since = "12.3.0")
  MEDKIT(CFontAwesome4CSS.FA_MEDKIT),
  @Deprecated (forRemoval = true, since = "12.3.0")
  MEETUP(CFontAwesome4CSS.FA_MEETUP),
  @Deprecated (forRemoval = true, since = "12.3.0")
  MEH_O(CFontAwesome4CSS.FA_MEH_O),
  @Deprecated (forRemoval = true, since = "12.3.0")
  MERCURY(CFontAwesome4CSS.FA_MERCURY),
  @Deprecated (forRemoval = true, since = "12.3.0")
  MICROCHIP(CFontAwesome4CSS.FA_MICROCHIP),
  @Deprecated (forRemoval = true, since = "12.3.0")
  MICROPHONE(CFontAwesome4CSS.FA_MICROPHONE),
  @Deprecated (forRemoval = true, since = "12.3.0")
  MICROPHONE_SLASH(CFontAwesome4CSS.FA_MICROPHONE_SLASH),
  @Deprecated (forRemoval = true, since = "12.3.0")
  MINUS(CFontAwesome4CSS.FA_MINUS),
  @Deprecated (forRemoval = true, since = "12.3.0")
  MINUS_CIRCLE(CFontAwesome4CSS.FA_MINUS_CIRCLE),
  @Deprecated (forRemoval = true, since = "12.3.0")
  MINUS_SQUARE(CFontAwesome4CSS.FA_MINUS_SQUARE),
  @Deprecated (forRemoval = true, since = "12.3.0")
  MINUS_SQUARE_O(CFontAwesome4CSS.FA_MINUS_SQUARE_O),
  @Deprecated (forRemoval = true, since = "12.3.0")
  MIXCLOUD(CFontAwesome4CSS.FA_MIXCLOUD),
  @Deprecated (forRemoval = true, since = "12.3.0")
  MOBILE(CFontAwesome4CSS.FA_MOBILE),
  @Deprecated (forRemoval = true, since = "12.3.0")
  MOBILE_PHONE(CFontAwesome4CSS.FA_MOBILE_PHONE),
  @Deprecated (forRemoval = true, since = "12.3.0")
  MODX(CFontAwesome4CSS.FA_MODX),
  @Deprecated (forRemoval = true, since = "12.3.0")
  MONEY(CFontAwesome4CSS.FA_MONEY),
  @Deprecated (forRemoval = true, since = "12.3.0")
  MOON_O(CFontAwesome4CSS.FA_MOON_O),
  @Deprecated (forRemoval = true, since = "12.3.0")
  MORTAR_BOARD(CFontAwesome4CSS.FA_MORTAR_BOARD),
  @Deprecated (forRemoval = true, since = "12.3.0")
  MOTORCYCLE(CFontAwesome4CSS.FA_MOTORCYCLE),
  @Deprecated (forRemoval = true, since = "12.3.0")
  MOUSE_POINTER(CFontAwesome4CSS.FA_MOUSE_POINTER),
  @Deprecated (forRemoval = true, since = "12.3.0")
  MUSIC(CFontAwesome4CSS.FA_MUSIC),
  @Deprecated (forRemoval = true, since = "12.3.0")
  NAVICON(CFontAwesome4CSS.FA_NAVICON),
  @Deprecated (forRemoval = true, since = "12.3.0")
  NEUTER(CFontAwesome4CSS.FA_NEUTER),
  @Deprecated (forRemoval = true, since = "12.3.0")
  NEWSPAPER_O(CFontAwesome4CSS.FA_NEWSPAPER_O),
  @Deprecated (forRemoval = true, since = "12.3.0")
  OBJECT_GROUP(CFontAwesome4CSS.FA_OBJECT_GROUP),
  @Deprecated (forRemoval = true, since = "12.3.0")
  OBJECT_UNGROUP(CFontAwesome4CSS.FA_OBJECT_UNGROUP),
  @Deprecated (forRemoval = true, since = "12.3.0")
  ODNOKLASSNIKI(CFontAwesome4CSS.FA_ODNOKLASSNIKI),
  @Deprecated (forRemoval = true, since = "12.3.0")
  ODNOKLASSNIKI_SQUARE(CFontAwesome4CSS.FA_ODNOKLASSNIKI_SQUARE),
  @Deprecated (forRemoval = true, since = "12.3.0")
  OPENCART(CFontAwesome4CSS.FA_OPENCART),
  @Deprecated (forRemoval = true, since = "12.3.0")
  OPENID(CFontAwesome4CSS.FA_OPENID),
  @Deprecated (forRemoval = true, since = "12.3.0")
  OPERA(CFontAwesome4CSS.FA_OPERA),
  @Deprecated (forRemoval = true, since = "12.3.0")
  OPTIN_MONSTER(CFontAwesome4CSS.FA_OPTIN_MONSTER),
  @Deprecated (forRemoval = true, since = "12.3.0")
  OUTDENT(CFontAwesome4CSS.FA_OUTDENT),
  @Deprecated (forRemoval = true, since = "12.3.0")
  PAGELINES(CFontAwesome4CSS.FA_PAGELINES),
  @Deprecated (forRemoval = true, since = "12.3.0")
  PAINT_BRUSH(CFontAwesome4CSS.FA_PAINT_BRUSH),
  @Deprecated (forRemoval = true, since = "12.3.0")
  PAPER_PLANE(CFontAwesome4CSS.FA_PAPER_PLANE),
  @Deprecated (forRemoval = true, since = "12.3.0")
  PAPER_PLANE_O(CFontAwesome4CSS.FA_PAPER_PLANE_O),
  @Deprecated (forRemoval = true, since = "12.3.0")
  PAPERCLIP(CFontAwesome4CSS.FA_PAPERCLIP),
  @Deprecated (forRemoval = true, since = "12.3.0")
  PARAGRAPH(CFontAwesome4CSS.FA_PARAGRAPH),
  @Deprecated (forRemoval = true, since = "12.3.0")
  PASTE(CFontAwesome4CSS.FA_PASTE),
  @Deprecated (forRemoval = true, since = "12.3.0")
  PAUSE(CFontAwesome4CSS.FA_PAUSE),
  @Deprecated (forRemoval = true, since = "12.3.0")
  PAUSE_CIRCLE(CFontAwesome4CSS.FA_PAUSE_CIRCLE),
  @Deprecated (forRemoval = true, since = "12.3.0")
  PAUSE_CIRCLE_O(CFontAwesome4CSS.FA_PAUSE_CIRCLE_O),
  @Deprecated (forRemoval = true, since = "12.3.0")
  PAW(CFontAwesome4CSS.FA_PAW),
  @Deprecated (forRemoval = true, since = "12.3.0")
  PAYPAL(CFontAwesome4CSS.FA_PAYPAL),
  @Deprecated (forRemoval = true, since = "12.3.0")
  PENCIL(CFontAwesome4CSS.FA_PENCIL),
  @Deprecated (forRemoval = true, since = "12.3.0")
  PENCIL_SQUARE(CFontAwesome4CSS.FA_PENCIL_SQUARE),
  @Deprecated (forRemoval = true, since = "12.3.0")
  PENCIL_SQUARE_O(CFontAwesome4CSS.FA_PENCIL_SQUARE_O),
  @Deprecated (forRemoval = true, since = "12.3.0")
  PERCENT(CFontAwesome4CSS.FA_PERCENT),
  @Deprecated (forRemoval = true, since = "12.3.0")
  PHONE(CFontAwesome4CSS.FA_PHONE),
  @Deprecated (forRemoval = true, since = "12.3.0")
  PHONE_SQUARE(CFontAwesome4CSS.FA_PHONE_SQUARE),
  @Deprecated (forRemoval = true, since = "12.3.0")
  PHOTO(CFontAwesome4CSS.FA_PHOTO),
  @Deprecated (forRemoval = true, since = "12.3.0")
  PICTURE_O(CFontAwesome4CSS.FA_PICTURE_O),
  @Deprecated (forRemoval = true, since = "12.3.0")
  PIE_CHART(CFontAwesome4CSS.FA_PIE_CHART),
  @Deprecated (forRemoval = true, since = "12.3.0")
  PIED_PIPER(CFontAwesome4CSS.FA_PIED_PIPER),
  @Deprecated (forRemoval = true, since = "12.3.0")
  PIED_PIPER_ALT(CFontAwesome4CSS.FA_PIED_PIPER_ALT),
  @Deprecated (forRemoval = true, since = "12.3.0")
  PIED_PIPER_PP(CFontAwesome4CSS.FA_PIED_PIPER_PP),
  @Deprecated (forRemoval = true, since = "12.3.0")
  PINTEREST(CFontAwesome4CSS.FA_PINTEREST),
  @Deprecated (forRemoval = true, since = "12.3.0")
  PINTEREST_P(CFontAwesome4CSS.FA_PINTEREST_P),
  @Deprecated (forRemoval = true, since = "12.3.0")
  PINTEREST_SQUARE(CFontAwesome4CSS.FA_PINTEREST_SQUARE),
  @Deprecated (forRemoval = true, since = "12.3.0")
  PLANE(CFontAwesome4CSS.FA_PLANE),
  @Deprecated (forRemoval = true, since = "12.3.0")
  PLAY(CFontAwesome4CSS.FA_PLAY),
  @Deprecated (forRemoval = true, since = "12.3.0")
  PLAY_CIRCLE(CFontAwesome4CSS.FA_PLAY_CIRCLE),
  @Deprecated (forRemoval = true, since = "12.3.0")
  PLAY_CIRCLE_O(CFontAwesome4CSS.FA_PLAY_CIRCLE_O),
  @Deprecated (forRemoval = true, since = "12.3.0")
  PLUG(CFontAwesome4CSS.FA_PLUG),
  @Deprecated (forRemoval = true, since = "12.3.0")
  PLUS(CFontAwesome4CSS.FA_PLUS),
  @Deprecated (forRemoval = true, since = "12.3.0")
  PLUS_CIRCLE(CFontAwesome4CSS.FA_PLUS_CIRCLE),
  @Deprecated (forRemoval = true, since = "12.3.0")
  PLUS_SQUARE(CFontAwesome4CSS.FA_PLUS_SQUARE),
  @Deprecated (forRemoval = true, since = "12.3.0")
  PLUS_SQUARE_O(CFontAwesome4CSS.FA_PLUS_SQUARE_O),
  @Deprecated (forRemoval = true, since = "12.3.0")
  PODCAST(CFontAwesome4CSS.FA_PODCAST),
  @Deprecated (forRemoval = true, since = "12.3.0")
  POWER_OFF(CFontAwesome4CSS.FA_POWER_OFF),
  @Deprecated (forRemoval = true, since = "12.3.0")
  PRINT(CFontAwesome4CSS.FA_PRINT),
  @Deprecated (forRemoval = true, since = "12.3.0")
  PRODUCT_HUNT(CFontAwesome4CSS.FA_PRODUCT_HUNT),
  @Deprecated (forRemoval = true, since = "12.3.0")
  PUZZLE_PIECE(CFontAwesome4CSS.FA_PUZZLE_PIECE),
  @Deprecated (forRemoval = true, since = "12.3.0")
  QQ(CFontAwesome4CSS.FA_QQ),
  @Deprecated (forRemoval = true, since = "12.3.0")
  QRCODE(CFontAwesome4CSS.FA_QRCODE),
  @Deprecated (forRemoval = true, since = "12.3.0")
  QUESTION(CFontAwesome4CSS.FA_QUESTION),
  @Deprecated (forRemoval = true, since = "12.3.0")
  QUESTION_CIRCLE(CFontAwesome4CSS.FA_QUESTION_CIRCLE),
  @Deprecated (forRemoval = true, since = "12.3.0")
  QUESTION_CIRCLE_O(CFontAwesome4CSS.FA_QUESTION_CIRCLE_O),
  @Deprecated (forRemoval = true, since = "12.3.0")
  QUORA(CFontAwesome4CSS.FA_QUORA),
  @Deprecated (forRemoval = true, since = "12.3.0")
  QUOTE_LEFT(CFontAwesome4CSS.FA_QUOTE_LEFT),
  @Deprecated (forRemoval = true, since = "12.3.0")
  QUOTE_RIGHT(CFontAwesome4CSS.FA_QUOTE_RIGHT),
  @Deprecated (forRemoval = true, since = "12.3.0")
  RA(CFontAwesome4CSS.FA_RA),
  @Deprecated (forRemoval = true, since = "12.3.0")
  RANDOM(CFontAwesome4CSS.FA_RANDOM),
  @Deprecated (forRemoval = true, since = "12.3.0")
  RAVELRY(CFontAwesome4CSS.FA_RAVELRY),
  @Deprecated (forRemoval = true, since = "12.3.0")
  REBEL(CFontAwesome4CSS.FA_REBEL),
  @Deprecated (forRemoval = true, since = "12.3.0")
  RECYCLE(CFontAwesome4CSS.FA_RECYCLE),
  @Deprecated (forRemoval = true, since = "12.3.0")
  REDDIT(CFontAwesome4CSS.FA_REDDIT),
  @Deprecated (forRemoval = true, since = "12.3.0")
  REDDIT_ALIEN(CFontAwesome4CSS.FA_REDDIT_ALIEN),
  @Deprecated (forRemoval = true, since = "12.3.0")
  REDDIT_SQUARE(CFontAwesome4CSS.FA_REDDIT_SQUARE),
  @Deprecated (forRemoval = true, since = "12.3.0")
  REFRESH(CFontAwesome4CSS.FA_REFRESH),
  @Deprecated (forRemoval = true, since = "12.3.0")
  REGISTERED(CFontAwesome4CSS.FA_REGISTERED),
  @Deprecated (forRemoval = true, since = "12.3.0")
  REMOVE(CFontAwesome4CSS.FA_REMOVE),
  @Deprecated (forRemoval = true, since = "12.3.0")
  RENREN(CFontAwesome4CSS.FA_RENREN),
  @Deprecated (forRemoval = true, since = "12.3.0")
  REORDER(CFontAwesome4CSS.FA_REORDER),
  @Deprecated (forRemoval = true, since = "12.3.0")
  REPEAT(CFontAwesome4CSS.FA_REPEAT),
  @Deprecated (forRemoval = true, since = "12.3.0")
  REPLY(CFontAwesome4CSS.FA_REPLY),
  @Deprecated (forRemoval = true, since = "12.3.0")
  REPLY_ALL(CFontAwesome4CSS.FA_REPLY_ALL),
  @Deprecated (forRemoval = true, since = "12.3.0")
  RESISTANCE(CFontAwesome4CSS.FA_RESISTANCE),
  @Deprecated (forRemoval = true, since = "12.3.0")
  RETWEET(CFontAwesome4CSS.FA_RETWEET),
  @Deprecated (forRemoval = true, since = "12.3.0")
  RMB(CFontAwesome4CSS.FA_RMB),
  @Deprecated (forRemoval = true, since = "12.3.0")
  ROAD(CFontAwesome4CSS.FA_ROAD),
  @Deprecated (forRemoval = true, since = "12.3.0")
  ROCKET(CFontAwesome4CSS.FA_ROCKET),
  @Deprecated (forRemoval = true, since = "12.3.0")
  ROTATE_LEFT(CFontAwesome4CSS.FA_ROTATE_LEFT),
  @Deprecated (forRemoval = true, since = "12.3.0")
  ROTATE_RIGHT(CFontAwesome4CSS.FA_ROTATE_RIGHT),
  @Deprecated (forRemoval = true, since = "12.3.0")
  ROUBLE(CFontAwesome4CSS.FA_ROUBLE),
  @Deprecated (forRemoval = true, since = "12.3.0")
  RSS(CFontAwesome4CSS.FA_RSS),
  @Deprecated (forRemoval = true, since = "12.3.0")
  RSS_SQUARE(CFontAwesome4CSS.FA_RSS_SQUARE),
  @Deprecated (forRemoval = true, since = "12.3.0")
  RUB(CFontAwesome4CSS.FA_RUB),
  @Deprecated (forRemoval = true, since = "12.3.0")
  RUBLE(CFontAwesome4CSS.FA_RUBLE),
  @Deprecated (forRemoval = true, since = "12.3.0")
  RUPEE(CFontAwesome4CSS.FA_RUPEE),
  @Deprecated (forRemoval = true, since = "12.3.0")
  S15(CFontAwesome4CSS.FA_S15),
  @Deprecated (forRemoval = true, since = "12.3.0")
  SAFARI(CFontAwesome4CSS.FA_SAFARI),
  @Deprecated (forRemoval = true, since = "12.3.0")
  SAVE(CFontAwesome4CSS.FA_SAVE),
  @Deprecated (forRemoval = true, since = "12.3.0")
  SCISSORS(CFontAwesome4CSS.FA_SCISSORS),
  @Deprecated (forRemoval = true, since = "12.3.0")
  SCRIBD(CFontAwesome4CSS.FA_SCRIBD),
  @Deprecated (forRemoval = true, since = "12.3.0")
  SEARCH(CFontAwesome4CSS.FA_SEARCH),
  @Deprecated (forRemoval = true, since = "12.3.0")
  SEARCH_MINUS(CFontAwesome4CSS.FA_SEARCH_MINUS),
  @Deprecated (forRemoval = true, since = "12.3.0")
  SEARCH_PLUS(CFontAwesome4CSS.FA_SEARCH_PLUS),
  @Deprecated (forRemoval = true, since = "12.3.0")
  SELLSY(CFontAwesome4CSS.FA_SELLSY),
  @Deprecated (forRemoval = true, since = "12.3.0")
  SEND(CFontAwesome4CSS.FA_SEND),
  @Deprecated (forRemoval = true, since = "12.3.0")
  SEND_O(CFontAwesome4CSS.FA_SEND_O),
  @Deprecated (forRemoval = true, since = "12.3.0")
  SERVER(CFontAwesome4CSS.FA_SERVER),
  @Deprecated (forRemoval = true, since = "12.3.0")
  SHARE(CFontAwesome4CSS.FA_SHARE),
  @Deprecated (forRemoval = true, since = "12.3.0")
  SHARE_ALT(CFontAwesome4CSS.FA_SHARE_ALT),
  @Deprecated (forRemoval = true, since = "12.3.0")
  SHARE_ALT_SQUARE(CFontAwesome4CSS.FA_SHARE_ALT_SQUARE),
  @Deprecated (forRemoval = true, since = "12.3.0")
  SHARE_SQUARE(CFontAwesome4CSS.FA_SHARE_SQUARE),
  @Deprecated (forRemoval = true, since = "12.3.0")
  SHARE_SQUARE_O(CFontAwesome4CSS.FA_SHARE_SQUARE_O),
  @Deprecated (forRemoval = true, since = "12.3.0")
  SHEKEL(CFontAwesome4CSS.FA_SHEKEL),
  @Deprecated (forRemoval = true, since = "12.3.0")
  SHEQEL(CFontAwesome4CSS.FA_SHEQEL),
  @Deprecated (forRemoval = true, since = "12.3.0")
  SHIELD(CFontAwesome4CSS.FA_SHIELD),
  @Deprecated (forRemoval = true, since = "12.3.0")
  SHIP(CFontAwesome4CSS.FA_SHIP),
  @Deprecated (forRemoval = true, since = "12.3.0")
  SHIRTSINBULK(CFontAwesome4CSS.FA_SHIRTSINBULK),
  @Deprecated (forRemoval = true, since = "12.3.0")
  SHOPPING_BAG(CFontAwesome4CSS.FA_SHOPPING_BAG),
  @Deprecated (forRemoval = true, since = "12.3.0")
  SHOPPING_BASKET(CFontAwesome4CSS.FA_SHOPPING_BASKET),
  @Deprecated (forRemoval = true, since = "12.3.0")
  SHOPPING_CART(CFontAwesome4CSS.FA_SHOPPING_CART),
  @Deprecated (forRemoval = true, since = "12.3.0")
  SHOWER(CFontAwesome4CSS.FA_SHOWER),
  @Deprecated (forRemoval = true, since = "12.3.0")
  SIGN_IN(CFontAwesome4CSS.FA_SIGN_IN),
  @Deprecated (forRemoval = true, since = "12.3.0")
  SIGN_LANGUAGE(CFontAwesome4CSS.FA_SIGN_LANGUAGE),
  @Deprecated (forRemoval = true, since = "12.3.0")
  SIGN_OUT(CFontAwesome4CSS.FA_SIGN_OUT),
  @Deprecated (forRemoval = true, since = "12.3.0")
  SIGNAL(CFontAwesome4CSS.FA_SIGNAL),
  @Deprecated (forRemoval = true, since = "12.3.0")
  SIGNING(CFontAwesome4CSS.FA_SIGNING),
  @Deprecated (forRemoval = true, since = "12.3.0")
  SIMPLYBUILT(CFontAwesome4CSS.FA_SIMPLYBUILT),
  @Deprecated (forRemoval = true, since = "12.3.0")
  SITEMAP(CFontAwesome4CSS.FA_SITEMAP),
  @Deprecated (forRemoval = true, since = "12.3.0")
  SKYATLAS(CFontAwesome4CSS.FA_SKYATLAS),
  @Deprecated (forRemoval = true, since = "12.3.0")
  SKYPE(CFontAwesome4CSS.FA_SKYPE),
  @Deprecated (forRemoval = true, since = "12.3.0")
  SLACK(CFontAwesome4CSS.FA_SLACK),
  @Deprecated (forRemoval = true, since = "12.3.0")
  SLIDERS(CFontAwesome4CSS.FA_SLIDERS),
  @Deprecated (forRemoval = true, since = "12.3.0")
  SLIDESHARE(CFontAwesome4CSS.FA_SLIDESHARE),
  @Deprecated (forRemoval = true, since = "12.3.0")
  SMILE_O(CFontAwesome4CSS.FA_SMILE_O),
  @Deprecated (forRemoval = true, since = "12.3.0")
  SNAPCHAT(CFontAwesome4CSS.FA_SNAPCHAT),
  @Deprecated (forRemoval = true, since = "12.3.0")
  SNAPCHAT_GHOST(CFontAwesome4CSS.FA_SNAPCHAT_GHOST),
  @Deprecated (forRemoval = true, since = "12.3.0")
  SNAPCHAT_SQUARE(CFontAwesome4CSS.FA_SNAPCHAT_SQUARE),
  @Deprecated (forRemoval = true, since = "12.3.0")
  SNOWFLAKE_O(CFontAwesome4CSS.FA_SNOWFLAKE_O),
  @Deprecated (forRemoval = true, since = "12.3.0")
  SOCCER_BALL_O(CFontAwesome4CSS.FA_SOCCER_BALL_O),
  @Deprecated (forRemoval = true, since = "12.3.0")
  SORT(CFontAwesome4CSS.FA_SORT),
  @Deprecated (forRemoval = true, since = "12.3.0")
  SORT_ALPHA_ASC(CFontAwesome4CSS.FA_SORT_ALPHA_ASC),
  @Deprecated (forRemoval = true, since = "12.3.0")
  SORT_ALPHA_DESC(CFontAwesome4CSS.FA_SORT_ALPHA_DESC),
  @Deprecated (forRemoval = true, since = "12.3.0")
  SORT_AMOUNT_ASC(CFontAwesome4CSS.FA_SORT_AMOUNT_ASC),
  @Deprecated (forRemoval = true, since = "12.3.0")
  SORT_AMOUNT_DESC(CFontAwesome4CSS.FA_SORT_AMOUNT_DESC),
  @Deprecated (forRemoval = true, since = "12.3.0")
  SORT_ASC(CFontAwesome4CSS.FA_SORT_ASC),
  @Deprecated (forRemoval = true, since = "12.3.0")
  SORT_DESC(CFontAwesome4CSS.FA_SORT_DESC),
  @Deprecated (forRemoval = true, since = "12.3.0")
  SORT_DOWN(CFontAwesome4CSS.FA_SORT_DOWN),
  @Deprecated (forRemoval = true, since = "12.3.0")
  SORT_NUMERIC_ASC(CFontAwesome4CSS.FA_SORT_NUMERIC_ASC),
  @Deprecated (forRemoval = true, since = "12.3.0")
  SORT_NUMERIC_DESC(CFontAwesome4CSS.FA_SORT_NUMERIC_DESC),
  @Deprecated (forRemoval = true, since = "12.3.0")
  SORT_UP(CFontAwesome4CSS.FA_SORT_UP),
  @Deprecated (forRemoval = true, since = "12.3.0")
  SOUNDCLOUD(CFontAwesome4CSS.FA_SOUNDCLOUD),
  @Deprecated (forRemoval = true, since = "12.3.0")
  SPACE_SHUTTLE(CFontAwesome4CSS.FA_SPACE_SHUTTLE),
  @Deprecated (forRemoval = true, since = "12.3.0")
  SPINNER(CFontAwesome4CSS.FA_SPINNER),
  @Deprecated (forRemoval = true, since = "12.3.0")
  SPOON(CFontAwesome4CSS.FA_SPOON),
  @Deprecated (forRemoval = true, since = "12.3.0")
  SPOTIFY(CFontAwesome4CSS.FA_SPOTIFY),
  @Deprecated (forRemoval = true, since = "12.3.0")
  SQUARE(CFontAwesome4CSS.FA_SQUARE),
  @Deprecated (forRemoval = true, since = "12.3.0")
  SQUARE_O(CFontAwesome4CSS.FA_SQUARE_O),
  @Deprecated (forRemoval = true, since = "12.3.0")
  STACK_EXCHANGE(CFontAwesome4CSS.FA_STACK_EXCHANGE),
  @Deprecated (forRemoval = true, since = "12.3.0")
  STACK_OVERFLOW(CFontAwesome4CSS.FA_STACK_OVERFLOW),
  @Deprecated (forRemoval = true, since = "12.3.0")
  STAR(CFontAwesome4CSS.FA_STAR),
  @Deprecated (forRemoval = true, since = "12.3.0")
  STAR_HALF(CFontAwesome4CSS.FA_STAR_HALF),
  @Deprecated (forRemoval = true, since = "12.3.0")
  STAR_HALF_EMPTY(CFontAwesome4CSS.FA_STAR_HALF_EMPTY),
  @Deprecated (forRemoval = true, since = "12.3.0")
  STAR_HALF_FULL(CFontAwesome4CSS.FA_STAR_HALF_FULL),
  @Deprecated (forRemoval = true, since = "12.3.0")
  STAR_HALF_O(CFontAwesome4CSS.FA_STAR_HALF_O),
  @Deprecated (forRemoval = true, since = "12.3.0")
  STAR_O(CFontAwesome4CSS.FA_STAR_O),
  @Deprecated (forRemoval = true, since = "12.3.0")
  STEAM(CFontAwesome4CSS.FA_STEAM),
  @Deprecated (forRemoval = true, since = "12.3.0")
  STEAM_SQUARE(CFontAwesome4CSS.FA_STEAM_SQUARE),
  @Deprecated (forRemoval = true, since = "12.3.0")
  STEP_BACKWARD(CFontAwesome4CSS.FA_STEP_BACKWARD),
  @Deprecated (forRemoval = true, since = "12.3.0")
  STEP_FORWARD(CFontAwesome4CSS.FA_STEP_FORWARD),
  @Deprecated (forRemoval = true, since = "12.3.0")
  STETHOSCOPE(CFontAwesome4CSS.FA_STETHOSCOPE),
  @Deprecated (forRemoval = true, since = "12.3.0")
  STICKY_NOTE(CFontAwesome4CSS.FA_STICKY_NOTE),
  @Deprecated (forRemoval = true, since = "12.3.0")
  STICKY_NOTE_O(CFontAwesome4CSS.FA_STICKY_NOTE_O),
  @Deprecated (forRemoval = true, since = "12.3.0")
  STOP(CFontAwesome4CSS.FA_STOP),
  @Deprecated (forRemoval = true, since = "12.3.0")
  STOP_CIRCLE(CFontAwesome4CSS.FA_STOP_CIRCLE),
  @Deprecated (forRemoval = true, since = "12.3.0")
  STOP_CIRCLE_O(CFontAwesome4CSS.FA_STOP_CIRCLE_O),
  @Deprecated (forRemoval = true, since = "12.3.0")
  STREET_VIEW(CFontAwesome4CSS.FA_STREET_VIEW),
  @Deprecated (forRemoval = true, since = "12.3.0")
  STRIKETHROUGH(CFontAwesome4CSS.FA_STRIKETHROUGH),
  @Deprecated (forRemoval = true, since = "12.3.0")
  STUMBLEUPON(CFontAwesome4CSS.FA_STUMBLEUPON),
  @Deprecated (forRemoval = true, since = "12.3.0")
  STUMBLEUPON_CIRCLE(CFontAwesome4CSS.FA_STUMBLEUPON_CIRCLE),
  @Deprecated (forRemoval = true, since = "12.3.0")
  SUBSCRIPT(CFontAwesome4CSS.FA_SUBSCRIPT),
  @Deprecated (forRemoval = true, since = "12.3.0")
  SUBWAY(CFontAwesome4CSS.FA_SUBWAY),
  @Deprecated (forRemoval = true, since = "12.3.0")
  SUITCASE(CFontAwesome4CSS.FA_SUITCASE),
  @Deprecated (forRemoval = true, since = "12.3.0")
  SUN_O(CFontAwesome4CSS.FA_SUN_O),
  @Deprecated (forRemoval = true, since = "12.3.0")
  SUPERPOWERS(CFontAwesome4CSS.FA_SUPERPOWERS),
  @Deprecated (forRemoval = true, since = "12.3.0")
  SUPERSCRIPT(CFontAwesome4CSS.FA_SUPERSCRIPT),
  @Deprecated (forRemoval = true, since = "12.3.0")
  SUPPORT(CFontAwesome4CSS.FA_SUPPORT),
  @Deprecated (forRemoval = true, since = "12.3.0")
  TABLE(CFontAwesome4CSS.FA_TABLE),
  @Deprecated (forRemoval = true, since = "12.3.0")
  TABLET(CFontAwesome4CSS.FA_TABLET),
  @Deprecated (forRemoval = true, since = "12.3.0")
  TACHOMETER(CFontAwesome4CSS.FA_TACHOMETER),
  @Deprecated (forRemoval = true, since = "12.3.0")
  TAG(CFontAwesome4CSS.FA_TAG),
  @Deprecated (forRemoval = true, since = "12.3.0")
  TAGS(CFontAwesome4CSS.FA_TAGS),
  @Deprecated (forRemoval = true, since = "12.3.0")
  TASKS(CFontAwesome4CSS.FA_TASKS),
  @Deprecated (forRemoval = true, since = "12.3.0")
  TAXI(CFontAwesome4CSS.FA_TAXI),
  @Deprecated (forRemoval = true, since = "12.3.0")
  TELEGRAM(CFontAwesome4CSS.FA_TELEGRAM),
  @Deprecated (forRemoval = true, since = "12.3.0")
  TELEVISION(CFontAwesome4CSS.FA_TELEVISION),
  @Deprecated (forRemoval = true, since = "12.3.0")
  TENCENT_WEIBO(CFontAwesome4CSS.FA_TENCENT_WEIBO),
  @Deprecated (forRemoval = true, since = "12.3.0")
  TERMINAL(CFontAwesome4CSS.FA_TERMINAL),
  @Deprecated (forRemoval = true, since = "12.3.0")
  TEXT_HEIGHT(CFontAwesome4CSS.FA_TEXT_HEIGHT),
  @Deprecated (forRemoval = true, since = "12.3.0")
  TEXT_WIDTH(CFontAwesome4CSS.FA_TEXT_WIDTH),
  @Deprecated (forRemoval = true, since = "12.3.0")
  TH(CFontAwesome4CSS.FA_TH),
  @Deprecated (forRemoval = true, since = "12.3.0")
  TH_LARGE(CFontAwesome4CSS.FA_TH_LARGE),
  @Deprecated (forRemoval = true, since = "12.3.0")
  TH_LIST(CFontAwesome4CSS.FA_TH_LIST),
  @Deprecated (forRemoval = true, since = "12.3.0")
  THEMEISLE(CFontAwesome4CSS.FA_THEMEISLE),
  @Deprecated (forRemoval = true, since = "12.3.0")
  THERMOMETER(CFontAwesome4CSS.FA_THERMOMETER),
  @Deprecated (forRemoval = true, since = "12.3.0")
  THERMOMETER_0(CFontAwesome4CSS.FA_THERMOMETER_0),
  @Deprecated (forRemoval = true, since = "12.3.0")
  THERMOMETER_1(CFontAwesome4CSS.FA_THERMOMETER_1),
  @Deprecated (forRemoval = true, since = "12.3.0")
  THERMOMETER_2(CFontAwesome4CSS.FA_THERMOMETER_2),
  @Deprecated (forRemoval = true, since = "12.3.0")
  THERMOMETER_3(CFontAwesome4CSS.FA_THERMOMETER_3),
  @Deprecated (forRemoval = true, since = "12.3.0")
  THERMOMETER_4(CFontAwesome4CSS.FA_THERMOMETER_4),
  @Deprecated (forRemoval = true, since = "12.3.0")
  THERMOMETER_EMPTY(CFontAwesome4CSS.FA_THERMOMETER_EMPTY),
  @Deprecated (forRemoval = true, since = "12.3.0")
  THERMOMETER_FULL(CFontAwesome4CSS.FA_THERMOMETER_FULL),
  @Deprecated (forRemoval = true, since = "12.3.0")
  THERMOMETER_HALF(CFontAwesome4CSS.FA_THERMOMETER_HALF),
  @Deprecated (forRemoval = true, since = "12.3.0")
  THERMOMETER_QUARTER(CFontAwesome4CSS.FA_THERMOMETER_QUARTER),
  @Deprecated (forRemoval = true, since = "12.3.0")
  THERMOMETER_THREE_QUARTERS(CFontAwesome4CSS.FA_THERMOMETER_THREE_QUARTERS),
  @Deprecated (forRemoval = true, since = "12.3.0")
  THUMB_TACK(CFontAwesome4CSS.FA_THUMB_TACK),
  @Deprecated (forRemoval = true, since = "12.3.0")
  THUMBS_DOWN(CFontAwesome4CSS.FA_THUMBS_DOWN),
  @Deprecated (forRemoval = true, since = "12.3.0")
  THUMBS_O_DOWN(CFontAwesome4CSS.FA_THUMBS_O_DOWN),
  @Deprecated (forRemoval = true, since = "12.3.0")
  THUMBS_O_UP(CFontAwesome4CSS.FA_THUMBS_O_UP),
  @Deprecated (forRemoval = true, since = "12.3.0")
  THUMBS_UP(CFontAwesome4CSS.FA_THUMBS_UP),
  @Deprecated (forRemoval = true, since = "12.3.0")
  TICKET(CFontAwesome4CSS.FA_TICKET),
  @Deprecated (forRemoval = true, since = "12.3.0")
  TIMES(CFontAwesome4CSS.FA_TIMES),
  @Deprecated (forRemoval = true, since = "12.3.0")
  TIMES_CIRCLE(CFontAwesome4CSS.FA_TIMES_CIRCLE),
  @Deprecated (forRemoval = true, since = "12.3.0")
  TIMES_CIRCLE_O(CFontAwesome4CSS.FA_TIMES_CIRCLE_O),
  @Deprecated (forRemoval = true, since = "12.3.0")
  TIMES_RECTANGLE(CFontAwesome4CSS.FA_TIMES_RECTANGLE),
  @Deprecated (forRemoval = true, since = "12.3.0")
  TIMES_RECTANGLE_O(CFontAwesome4CSS.FA_TIMES_RECTANGLE_O),
  @Deprecated (forRemoval = true, since = "12.3.0")
  TINT(CFontAwesome4CSS.FA_TINT),
  @Deprecated (forRemoval = true, since = "12.3.0")
  TOGGLE_DOWN(CFontAwesome4CSS.FA_TOGGLE_DOWN),
  @Deprecated (forRemoval = true, since = "12.3.0")
  TOGGLE_LEFT(CFontAwesome4CSS.FA_TOGGLE_LEFT),
  @Deprecated (forRemoval = true, since = "12.3.0")
  TOGGLE_OFF(CFontAwesome4CSS.FA_TOGGLE_OFF),
  @Deprecated (forRemoval = true, since = "12.3.0")
  TOGGLE_ON(CFontAwesome4CSS.FA_TOGGLE_ON),
  @Deprecated (forRemoval = true, since = "12.3.0")
  TOGGLE_RIGHT(CFontAwesome4CSS.FA_TOGGLE_RIGHT),
  @Deprecated (forRemoval = true, since = "12.3.0")
  TOGGLE_UP(CFontAwesome4CSS.FA_TOGGLE_UP),
  @Deprecated (forRemoval = true, since = "12.3.0")
  TRADEMARK(CFontAwesome4CSS.FA_TRADEMARK),
  @Deprecated (forRemoval = true, since = "12.3.0")
  TRAIN(CFontAwesome4CSS.FA_TRAIN),
  @Deprecated (forRemoval = true, since = "12.3.0")
  TRANSGENDER(CFontAwesome4CSS.FA_TRANSGENDER),
  @Deprecated (forRemoval = true, since = "12.3.0")
  TRANSGENDER_ALT(CFontAwesome4CSS.FA_TRANSGENDER_ALT),
  @Deprecated (forRemoval = true, since = "12.3.0")
  TRASH(CFontAwesome4CSS.FA_TRASH),
  @Deprecated (forRemoval = true, since = "12.3.0")
  TRASH_O(CFontAwesome4CSS.FA_TRASH_O),
  @Deprecated (forRemoval = true, since = "12.3.0")
  TREE(CFontAwesome4CSS.FA_TREE),
  @Deprecated (forRemoval = true, since = "12.3.0")
  TRELLO(CFontAwesome4CSS.FA_TRELLO),
  @Deprecated (forRemoval = true, since = "12.3.0")
  TRIPADVISOR(CFontAwesome4CSS.FA_TRIPADVISOR),
  @Deprecated (forRemoval = true, since = "12.3.0")
  TROPHY(CFontAwesome4CSS.FA_TROPHY),
  @Deprecated (forRemoval = true, since = "12.3.0")
  TRUCK(CFontAwesome4CSS.FA_TRUCK),
  @Deprecated (forRemoval = true, since = "12.3.0")
  TRY(CFontAwesome4CSS.FA_TRY),
  @Deprecated (forRemoval = true, since = "12.3.0")
  TTY(CFontAwesome4CSS.FA_TTY),
  @Deprecated (forRemoval = true, since = "12.3.0")
  TUMBLR(CFontAwesome4CSS.FA_TUMBLR),
  @Deprecated (forRemoval = true, since = "12.3.0")
  TUMBLR_SQUARE(CFontAwesome4CSS.FA_TUMBLR_SQUARE),
  @Deprecated (forRemoval = true, since = "12.3.0")
  TURKISH_LIRA(CFontAwesome4CSS.FA_TURKISH_LIRA),
  @Deprecated (forRemoval = true, since = "12.3.0")
  TV(CFontAwesome4CSS.FA_TV),
  @Deprecated (forRemoval = true, since = "12.3.0")
  TWITCH(CFontAwesome4CSS.FA_TWITCH),
  @Deprecated (forRemoval = true, since = "12.3.0")
  TWITTER(CFontAwesome4CSS.FA_TWITTER),
  @Deprecated (forRemoval = true, since = "12.3.0")
  TWITTER_SQUARE(CFontAwesome4CSS.FA_TWITTER_SQUARE),
  @Deprecated (forRemoval = true, since = "12.3.0")
  UMBRELLA(CFontAwesome4CSS.FA_UMBRELLA),
  @Deprecated (forRemoval = true, since = "12.3.0")
  UNDERLINE(CFontAwesome4CSS.FA_UNDERLINE),
  @Deprecated (forRemoval = true, since = "12.3.0")
  UNDO(CFontAwesome4CSS.FA_UNDO),
  @Deprecated (forRemoval = true, since = "12.3.0")
  UNIVERSAL_ACCESS(CFontAwesome4CSS.FA_UNIVERSAL_ACCESS),
  @Deprecated (forRemoval = true, since = "12.3.0")
  UNIVERSITY(CFontAwesome4CSS.FA_UNIVERSITY),
  @Deprecated (forRemoval = true, since = "12.3.0")
  UNLINK(CFontAwesome4CSS.FA_UNLINK),
  @Deprecated (forRemoval = true, since = "12.3.0")
  UNLOCK(CFontAwesome4CSS.FA_UNLOCK),
  @Deprecated (forRemoval = true, since = "12.3.0")
  UNLOCK_ALT(CFontAwesome4CSS.FA_UNLOCK_ALT),
  @Deprecated (forRemoval = true, since = "12.3.0")
  UNSORTED(CFontAwesome4CSS.FA_UNSORTED),
  @Deprecated (forRemoval = true, since = "12.3.0")
  UPLOAD(CFontAwesome4CSS.FA_UPLOAD),
  @Deprecated (forRemoval = true, since = "12.3.0")
  USB(CFontAwesome4CSS.FA_USB),
  @Deprecated (forRemoval = true, since = "12.3.0")
  USD(CFontAwesome4CSS.FA_USD),
  @Deprecated (forRemoval = true, since = "12.3.0")
  USER(CFontAwesome4CSS.FA_USER),
  @Deprecated (forRemoval = true, since = "12.3.0")
  USER_CIRCLE(CFontAwesome4CSS.FA_USER_CIRCLE),
  @Deprecated (forRemoval = true, since = "12.3.0")
  USER_CIRCLE_O(CFontAwesome4CSS.FA_USER_CIRCLE_O),
  @Deprecated (forRemoval = true, since = "12.3.0")
  USER_MD(CFontAwesome4CSS.FA_USER_MD),
  @Deprecated (forRemoval = true, since = "12.3.0")
  USER_O(CFontAwesome4CSS.FA_USER_O),
  @Deprecated (forRemoval = true, since = "12.3.0")
  USER_PLUS(CFontAwesome4CSS.FA_USER_PLUS),
  @Deprecated (forRemoval = true, since = "12.3.0")
  USER_SECRET(CFontAwesome4CSS.FA_USER_SECRET),
  @Deprecated (forRemoval = true, since = "12.3.0")
  USER_TIMES(CFontAwesome4CSS.FA_USER_TIMES),
  @Deprecated (forRemoval = true, since = "12.3.0")
  USERS(CFontAwesome4CSS.FA_USERS),
  @Deprecated (forRemoval = true, since = "12.3.0")
  VCARD(CFontAwesome4CSS.FA_VCARD),
  @Deprecated (forRemoval = true, since = "12.3.0")
  VCARD_O(CFontAwesome4CSS.FA_VCARD_O),
  @Deprecated (forRemoval = true, since = "12.3.0")
  VENUS(CFontAwesome4CSS.FA_VENUS),
  @Deprecated (forRemoval = true, since = "12.3.0")
  VENUS_DOUBLE(CFontAwesome4CSS.FA_VENUS_DOUBLE),
  @Deprecated (forRemoval = true, since = "12.3.0")
  VENUS_MARS(CFontAwesome4CSS.FA_VENUS_MARS),
  @Deprecated (forRemoval = true, since = "12.3.0")
  VIACOIN(CFontAwesome4CSS.FA_VIACOIN),
  @Deprecated (forRemoval = true, since = "12.3.0")
  VIADEO(CFontAwesome4CSS.FA_VIADEO),
  @Deprecated (forRemoval = true, since = "12.3.0")
  VIADEO_SQUARE(CFontAwesome4CSS.FA_VIADEO_SQUARE),
  @Deprecated (forRemoval = true, since = "12.3.0")
  VIDEO_CAMERA(CFontAwesome4CSS.FA_VIDEO_CAMERA),
  @Deprecated (forRemoval = true, since = "12.3.0")
  VIMEO(CFontAwesome4CSS.FA_VIMEO),
  @Deprecated (forRemoval = true, since = "12.3.0")
  VIMEO_SQUARE(CFontAwesome4CSS.FA_VIMEO_SQUARE),
  @Deprecated (forRemoval = true, since = "12.3.0")
  VINE(CFontAwesome4CSS.FA_VINE),
  @Deprecated (forRemoval = true, since = "12.3.0")
  VK(CFontAwesome4CSS.FA_VK),
  @Deprecated (forRemoval = true, since = "12.3.0")
  VOLUME_CONTROL_PHONE(CFontAwesome4CSS.FA_VOLUME_CONTROL_PHONE),
  @Deprecated (forRemoval = true, since = "12.3.0")
  VOLUME_DOWN(CFontAwesome4CSS.FA_VOLUME_DOWN),
  @Deprecated (forRemoval = true, since = "12.3.0")
  VOLUME_OFF(CFontAwesome4CSS.FA_VOLUME_OFF),
  @Deprecated (forRemoval = true, since = "12.3.0")
  VOLUME_UP(CFontAwesome4CSS.FA_VOLUME_UP),
  @Deprecated (forRemoval = true, since = "12.3.0")
  WARNING(CFontAwesome4CSS.FA_WARNING),
  @Deprecated (forRemoval = true, since = "12.3.0")
  WECHAT(CFontAwesome4CSS.FA_WECHAT),
  @Deprecated (forRemoval = true, since = "12.3.0")
  WEIBO(CFontAwesome4CSS.FA_WEIBO),
  @Deprecated (forRemoval = true, since = "12.3.0")
  WEIXIN(CFontAwesome4CSS.FA_WEIXIN),
  @Deprecated (forRemoval = true, since = "12.3.0")
  WHATSAPP(CFontAwesome4CSS.FA_WHATSAPP),
  @Deprecated (forRemoval = true, since = "12.3.0")
  WHEELCHAIR(CFontAwesome4CSS.FA_WHEELCHAIR),
  @Deprecated (forRemoval = true, since = "12.3.0")
  WHEELCHAIR_ALT(CFontAwesome4CSS.FA_WHEELCHAIR_ALT),
  @Deprecated (forRemoval = true, since = "12.3.0")
  WIFI(CFontAwesome4CSS.FA_WIFI),
  @Deprecated (forRemoval = true, since = "12.3.0")
  WIKIPEDIA_W(CFontAwesome4CSS.FA_WIKIPEDIA_W),
  @Deprecated (forRemoval = true, since = "12.3.0")
  WINDOW_CLOSE(CFontAwesome4CSS.FA_WINDOW_CLOSE),
  @Deprecated (forRemoval = true, since = "12.3.0")
  WINDOW_CLOSE_O(CFontAwesome4CSS.FA_WINDOW_CLOSE_O),
  @Deprecated (forRemoval = true, since = "12.3.0")
  WINDOW_MAXIMIZE(CFontAwesome4CSS.FA_WINDOW_MAXIMIZE),
  @Deprecated (forRemoval = true, since = "12.3.0")
  WINDOW_MINIMIZE(CFontAwesome4CSS.FA_WINDOW_MINIMIZE),
  @Deprecated (forRemoval = true, since = "12.3.0")
  WINDOW_RESTORE(CFontAwesome4CSS.FA_WINDOW_RESTORE),
  @Deprecated (forRemoval = true, since = "12.3.0")
  WINDOWS(CFontAwesome4CSS.FA_WINDOWS),
  @Deprecated (forRemoval = true, since = "12.3.0")
  WON(CFontAwesome4CSS.FA_WON),
  @Deprecated (forRemoval = true, since = "12.3.0")
  WORDPRESS(CFontAwesome4CSS.FA_WORDPRESS),
  @Deprecated (forRemoval = true, since = "12.3.0")
  WPBEGINNER(CFontAwesome4CSS.FA_WPBEGINNER),
  @Deprecated (forRemoval = true, since = "12.3.0")
  WPEXPLORER(CFontAwesome4CSS.FA_WPEXPLORER),
  @Deprecated (forRemoval = true, since = "12.3.0")
  WPFORMS(CFontAwesome4CSS.FA_WPFORMS),
  @Deprecated (forRemoval = true, since = "12.3.0")
  WRENCH(CFontAwesome4CSS.FA_WRENCH),
  @Deprecated (forRemoval = true, since = "12.3.0")
  XING(CFontAwesome4CSS.FA_XING),
  @Deprecated (forRemoval = true, since = "12.3.0")
  XING_SQUARE(CFontAwesome4CSS.FA_XING_SQUARE),
  @Deprecated (forRemoval = true, since = "12.3.0")
  Y_COMBINATOR(CFontAwesome4CSS.FA_Y_COMBINATOR),
  @Deprecated (forRemoval = true, since = "12.3.0")
  Y_COMBINATOR_SQUARE(CFontAwesome4CSS.FA_Y_COMBINATOR_SQUARE),
  @Deprecated (forRemoval = true, since = "12.3.0")
  YAHOO(CFontAwesome4CSS.FA_YAHOO),
  @Deprecated (forRemoval = true, since = "12.3.0")
  YC(CFontAwesome4CSS.FA_YC),
  @Deprecated (forRemoval = true, since = "12.3.0")
  YC_SQUARE(CFontAwesome4CSS.FA_YC_SQUARE),
  @Deprecated (forRemoval = true, since = "12.3.0")
  YELP(CFontAwesome4CSS.FA_YELP),
  @Deprecated (forRemoval = true, since = "12.3.0")
  YEN(CFontAwesome4CSS.FA_YEN),
  @Deprecated (forRemoval = true, since = "12.3.0")
  YOAST(CFontAwesome4CSS.FA_YOAST),
  @Deprecated (forRemoval = true, since = "12.3.0")
  YOUTUBE(CFontAwesome4CSS.FA_YOUTUBE),
  @Deprecated (forRemoval = true, since = "12.3.0")
  YOUTUBE_PLAY(CFontAwesome4CSS.FA_YOUTUBE_PLAY),
  @Deprecated (forRemoval = true, since = "12.3.0")
  YOUTUBE_SQUARE(CFontAwesome4CSS.FA_YOUTUBE_SQUARE);

  private final ICSSClassProvider m_aCSSClass;

  EFontAwesome4Icon (@NonNull final ICSSClassProvider aCSSClass)
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
    aElement.addClasses (CFontAwesome4CSS.FA, m_aCSSClass);
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
  public HCI getAsNodeLarge ()
  {
    return getAsNode ().addClass (CFontAwesome4CSS.FA_LG);
  }

  @Deprecated (forRemoval = true, since = "12.3.0")
  @NonNull
  public HCI getAsNode2x ()
  {
    return getAsNode ().addClass (CFontAwesome4CSS.FA_2X);
  }

  @Deprecated (forRemoval = true, since = "12.3.0")
  @NonNull
  public HCI getAsNode3x ()
  {
    return getAsNode ().addClass (CFontAwesome4CSS.FA_3X);
  }

  @Deprecated (forRemoval = true, since = "12.3.0")
  @NonNull
  public HCI getAsNode4x ()
  {
    return getAsNode ().addClass (CFontAwesome4CSS.FA_4X);
  }

  @Deprecated (forRemoval = true, since = "12.3.0")
  @NonNull
  public HCI getAsNode5x ()
  {
    return getAsNode ().addClass (CFontAwesome4CSS.FA_5X);
  }

  @Deprecated (forRemoval = true, since = "12.3.0")
  @NonNull
  public HCI getAsNodeFixedWidth ()
  {
    return getAsNode ().addClass (CFontAwesome4CSS.FA_FW);
  }

  @Deprecated (forRemoval = true, since = "12.3.0")
  @NonNull
  public HCI getAsNodeListBullet ()
  {
    return getAsNode ().addClass (CFontAwesome4CSS.FA_LI);
  }

  @Deprecated (forRemoval = true, since = "12.3.0")
  @NonNull
  public HCI getAsNodeSpinning ()
  {
    return getAsNode ().addClass (CFontAwesome4CSS.FA_SPIN);
  }

  @Deprecated (forRemoval = true, since = "12.3.0")
  @NonNull
  public HCI getAsNodeRotate90 ()
  {
    return getAsNode ().addClass (CFontAwesome4CSS.FA_ROTATE_90);
  }

  @Deprecated (forRemoval = true, since = "12.3.0")
  @NonNull
  public HCI getAsNodeRotate180 ()
  {
    return getAsNode ().addClass (CFontAwesome4CSS.FA_ROTATE_180);
  }

  @Deprecated (forRemoval = true, since = "12.3.0")
  @NonNull
  public HCI getAsNodeRotate270 ()
  {
    return getAsNode ().addClass (CFontAwesome4CSS.FA_ROTATE_270);
  }

  @Deprecated (forRemoval = true, since = "12.3.0")
  @NonNull
  public HCI getAsNodeFlipHorz ()
  {
    return getAsNode ().addClass (CFontAwesome4CSS.FA_FLIP_HORIZONTAL);
  }

  @Deprecated (forRemoval = true, since = "12.3.0")
  @NonNull
  public HCI getAsNodeFlipVert ()
  {
    return getAsNode ().addClass (CFontAwesome4CSS.FA_FLIP_VERTICAL);
  }

  @Deprecated (forRemoval = true, since = "12.3.0")
  @NonNull
  public HCI getAsNodeInverse ()
  {
    return getAsNode ().addClass (CFontAwesome4CSS.FA_INVERSE);
  }

  @Deprecated (forRemoval = true, since = "12.3.0")
  @NonNull
  public static HCSpan createIconStack (@NonNull final IHCElement <?> aLargeIcon,
                                        @NonNull final IHCElement <?> aSmallIcon)
  {
    final HCSpan ret = new HCSpan ().addClasses (CFontAwesome4CSS.FA_STACK, CFontAwesome4CSS.FA_LG);
    ret.addChild (aLargeIcon.addClass (CFontAwesome4CSS.FA_STACK_2X));
    ret.addChild (aSmallIcon.addClass (CFontAwesome4CSS.FA_STACK_1X));
    return ret;
  }

  @Deprecated (forRemoval = true, since = "12.3.0")
  public static void setAsDefault ()
  {
    DefaultIcons.set (EDefaultIcon.ADD, PLUS);
    DefaultIcons.set (EDefaultIcon.BACK, ARROW_LEFT);
    DefaultIcons.set (EDefaultIcon.BACK_TO_LIST, ARROW_LEFT);
    DefaultIcons.set (EDefaultIcon.CANCEL, REMOVE);
    DefaultIcons.set (EDefaultIcon.COPY, COPY);
    DefaultIcons.set (EDefaultIcon.DELETE, TRASH);
    DefaultIcons.set (EDefaultIcon.DOWN, ARROW_DOWN);
    DefaultIcons.set (EDefaultIcon.EDIT, PENCIL);
    DefaultIcons.set (EDefaultIcon.FORWARD, ARROW_RIGHT);
    DefaultIcons.set (EDefaultIcon.HELP, QUESTION);
    DefaultIcons.set (EDefaultIcon.INFO, INFO);
    DefaultIcons.set (EDefaultIcon.KEY, LOCK);
    DefaultIcons.set (EDefaultIcon.MAGNIFIER, BINOCULARS);
    DefaultIcons.set (EDefaultIcon.MINUS, MINUS);
    DefaultIcons.set (EDefaultIcon.NEW, FILE_O);
    DefaultIcons.set (EDefaultIcon.NEXT, ARROW_RIGHT);
    DefaultIcons.set (EDefaultIcon.NO, REMOVE);
    DefaultIcons.set (EDefaultIcon.PLUS, PLUS);
    DefaultIcons.set (EDefaultIcon.REFRESH, REFRESH);
    DefaultIcons.set (EDefaultIcon.SAVE, SAVE);
    DefaultIcons.set (EDefaultIcon.SAVE_ALL, SAVE);
    DefaultIcons.set (EDefaultIcon.SAVE_AS, SAVE);
    DefaultIcons.set (EDefaultIcon.SAVE_CLOSE, SAVE);
    DefaultIcons.set (EDefaultIcon.SUBMIT, PAPER_PLANE);
    DefaultIcons.set (EDefaultIcon.UNDELETE, ARROW_LEFT);
    DefaultIcons.set (EDefaultIcon.UP, ARROW_UP);
    DefaultIcons.set (EDefaultIcon.YES, CHECK);
  }

  @Deprecated (forRemoval = true, since = "12.3.0")
  @NonNull
  public static ICommonsList <ICSSPathProvider> getAllCSSFiles ()
  {
    return new CommonsArrayList <> (EIconCSSPathProvider.FONT_AWESOME4);
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
