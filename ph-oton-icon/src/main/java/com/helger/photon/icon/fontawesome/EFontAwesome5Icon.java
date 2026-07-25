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
public enum EFontAwesome5Icon implements IIcon
{
  @Deprecated (forRemoval = true, since = "12.3.0")
  _500PX(CFontAwesome5CSS.FA_500PX, true),
  @Deprecated (forRemoval = true, since = "12.3.0")
  ACCESSIBLE_ICON(CFontAwesome5CSS.FA_ACCESSIBLE_ICON, true),
  @Deprecated (forRemoval = true, since = "12.3.0")
  ACCUSOFT(CFontAwesome5CSS.FA_ACCUSOFT, true),
  @Deprecated (forRemoval = true, since = "12.3.0")
  ACQUISITIONS_INCORPORATED(CFontAwesome5CSS.FA_ACQUISITIONS_INCORPORATED, true),
  @Deprecated (forRemoval = true, since = "12.3.0")
  AD(CFontAwesome5CSS.FA_AD, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  ADDRESS_BOOK(CFontAwesome5CSS.FA_ADDRESS_BOOK, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  ADDRESS_CARD(CFontAwesome5CSS.FA_ADDRESS_CARD, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  ADJUST(CFontAwesome5CSS.FA_ADJUST, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  ADN(CFontAwesome5CSS.FA_ADN, true),
  @Deprecated (forRemoval = true, since = "12.3.0")
  ADVERSAL(CFontAwesome5CSS.FA_ADVERSAL, true),
  @Deprecated (forRemoval = true, since = "12.3.0")
  AFFILIATETHEME(CFontAwesome5CSS.FA_AFFILIATETHEME, true),
  @Deprecated (forRemoval = true, since = "12.3.0")
  AIR_FRESHENER(CFontAwesome5CSS.FA_AIR_FRESHENER, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  AIRBNB(CFontAwesome5CSS.FA_AIRBNB, true),
  @Deprecated (forRemoval = true, since = "12.3.0")
  ALGOLIA(CFontAwesome5CSS.FA_ALGOLIA, true),
  @Deprecated (forRemoval = true, since = "12.3.0")
  ALIGN_CENTER(CFontAwesome5CSS.FA_ALIGN_CENTER, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  ALIGN_JUSTIFY(CFontAwesome5CSS.FA_ALIGN_JUSTIFY, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  ALIGN_LEFT(CFontAwesome5CSS.FA_ALIGN_LEFT, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  ALIGN_RIGHT(CFontAwesome5CSS.FA_ALIGN_RIGHT, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  ALIPAY(CFontAwesome5CSS.FA_ALIPAY, true),
  @Deprecated (forRemoval = true, since = "12.3.0")
  ALLERGIES(CFontAwesome5CSS.FA_ALLERGIES, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  AMAZON(CFontAwesome5CSS.FA_AMAZON, true),
  @Deprecated (forRemoval = true, since = "12.3.0")
  AMAZON_PAY(CFontAwesome5CSS.FA_AMAZON_PAY, true),
  @Deprecated (forRemoval = true, since = "12.3.0")
  AMBULANCE(CFontAwesome5CSS.FA_AMBULANCE, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  AMERICAN_SIGN_LANGUAGE_INTERPRETING(CFontAwesome5CSS.FA_AMERICAN_SIGN_LANGUAGE_INTERPRETING, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  AMILIA(CFontAwesome5CSS.FA_AMILIA, true),
  @Deprecated (forRemoval = true, since = "12.3.0")
  ANCHOR(CFontAwesome5CSS.FA_ANCHOR, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  ANDROID(CFontAwesome5CSS.FA_ANDROID, true),
  @Deprecated (forRemoval = true, since = "12.3.0")
  ANGELLIST(CFontAwesome5CSS.FA_ANGELLIST, true),
  @Deprecated (forRemoval = true, since = "12.3.0")
  ANGLE_DOUBLE_DOWN(CFontAwesome5CSS.FA_ANGLE_DOUBLE_DOWN, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  ANGLE_DOUBLE_LEFT(CFontAwesome5CSS.FA_ANGLE_DOUBLE_LEFT, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  ANGLE_DOUBLE_RIGHT(CFontAwesome5CSS.FA_ANGLE_DOUBLE_RIGHT, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  ANGLE_DOUBLE_UP(CFontAwesome5CSS.FA_ANGLE_DOUBLE_UP, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  ANGLE_DOWN(CFontAwesome5CSS.FA_ANGLE_DOWN, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  ANGLE_LEFT(CFontAwesome5CSS.FA_ANGLE_LEFT, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  ANGLE_RIGHT(CFontAwesome5CSS.FA_ANGLE_RIGHT, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  ANGLE_UP(CFontAwesome5CSS.FA_ANGLE_UP, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  ANGRY(CFontAwesome5CSS.FA_ANGRY, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  ANGRYCREATIVE(CFontAwesome5CSS.FA_ANGRYCREATIVE, true),
  @Deprecated (forRemoval = true, since = "12.3.0")
  ANGULAR(CFontAwesome5CSS.FA_ANGULAR, true),
  @Deprecated (forRemoval = true, since = "12.3.0")
  ANKH(CFontAwesome5CSS.FA_ANKH, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  APP_STORE(CFontAwesome5CSS.FA_APP_STORE, true),
  @Deprecated (forRemoval = true, since = "12.3.0")
  APP_STORE_IOS(CFontAwesome5CSS.FA_APP_STORE_IOS, true),
  @Deprecated (forRemoval = true, since = "12.3.0")
  APPER(CFontAwesome5CSS.FA_APPER, true),
  @Deprecated (forRemoval = true, since = "12.3.0")
  APPLE(CFontAwesome5CSS.FA_APPLE, true),
  @Deprecated (forRemoval = true, since = "12.3.0")
  APPLE_ALT(CFontAwesome5CSS.FA_APPLE_ALT, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  APPLE_PAY(CFontAwesome5CSS.FA_APPLE_PAY, true),
  @Deprecated (forRemoval = true, since = "12.3.0")
  ARCHIVE(CFontAwesome5CSS.FA_ARCHIVE, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  ARCHWAY(CFontAwesome5CSS.FA_ARCHWAY, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  ARROW_ALT_CIRCLE_DOWN(CFontAwesome5CSS.FA_ARROW_ALT_CIRCLE_DOWN, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  ARROW_ALT_CIRCLE_LEFT(CFontAwesome5CSS.FA_ARROW_ALT_CIRCLE_LEFT, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  ARROW_ALT_CIRCLE_RIGHT(CFontAwesome5CSS.FA_ARROW_ALT_CIRCLE_RIGHT, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  ARROW_ALT_CIRCLE_UP(CFontAwesome5CSS.FA_ARROW_ALT_CIRCLE_UP, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  ARROW_CIRCLE_DOWN(CFontAwesome5CSS.FA_ARROW_CIRCLE_DOWN, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  ARROW_CIRCLE_LEFT(CFontAwesome5CSS.FA_ARROW_CIRCLE_LEFT, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  ARROW_CIRCLE_RIGHT(CFontAwesome5CSS.FA_ARROW_CIRCLE_RIGHT, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  ARROW_CIRCLE_UP(CFontAwesome5CSS.FA_ARROW_CIRCLE_UP, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  ARROW_DOWN(CFontAwesome5CSS.FA_ARROW_DOWN, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  ARROW_LEFT(CFontAwesome5CSS.FA_ARROW_LEFT, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  ARROW_RIGHT(CFontAwesome5CSS.FA_ARROW_RIGHT, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  ARROW_UP(CFontAwesome5CSS.FA_ARROW_UP, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  ARROWS_ALT(CFontAwesome5CSS.FA_ARROWS_ALT, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  ARROWS_ALT_H(CFontAwesome5CSS.FA_ARROWS_ALT_H, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  ARROWS_ALT_V(CFontAwesome5CSS.FA_ARROWS_ALT_V, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  ARTSTATION(CFontAwesome5CSS.FA_ARTSTATION, true),
  @Deprecated (forRemoval = true, since = "12.3.0")
  ASSISTIVE_LISTENING_SYSTEMS(CFontAwesome5CSS.FA_ASSISTIVE_LISTENING_SYSTEMS, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  ASTERISK(CFontAwesome5CSS.FA_ASTERISK, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  ASYMMETRIK(CFontAwesome5CSS.FA_ASYMMETRIK, true),
  @Deprecated (forRemoval = true, since = "12.3.0")
  AT(CFontAwesome5CSS.FA_AT, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  ATLAS(CFontAwesome5CSS.FA_ATLAS, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  ATLASSIAN(CFontAwesome5CSS.FA_ATLASSIAN, true),
  @Deprecated (forRemoval = true, since = "12.3.0")
  ATOM(CFontAwesome5CSS.FA_ATOM, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  AUDIBLE(CFontAwesome5CSS.FA_AUDIBLE, true),
  @Deprecated (forRemoval = true, since = "12.3.0")
  AUDIO_DESCRIPTION(CFontAwesome5CSS.FA_AUDIO_DESCRIPTION, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  AUTOPREFIXER(CFontAwesome5CSS.FA_AUTOPREFIXER, true),
  @Deprecated (forRemoval = true, since = "12.3.0")
  AVIANEX(CFontAwesome5CSS.FA_AVIANEX, true),
  @Deprecated (forRemoval = true, since = "12.3.0")
  AVIATO(CFontAwesome5CSS.FA_AVIATO, true),
  @Deprecated (forRemoval = true, since = "12.3.0")
  AWARD(CFontAwesome5CSS.FA_AWARD, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  AWS(CFontAwesome5CSS.FA_AWS, true),
  @Deprecated (forRemoval = true, since = "12.3.0")
  BABY(CFontAwesome5CSS.FA_BABY, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  BABY_CARRIAGE(CFontAwesome5CSS.FA_BABY_CARRIAGE, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  BACKSPACE(CFontAwesome5CSS.FA_BACKSPACE, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  BACKWARD(CFontAwesome5CSS.FA_BACKWARD, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  BACON(CFontAwesome5CSS.FA_BACON, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  BACTERIA(CFontAwesome5CSS.FA_BACTERIA, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  BACTERIUM(CFontAwesome5CSS.FA_BACTERIUM, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  BAHAI(CFontAwesome5CSS.FA_BAHAI, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  BALANCE_SCALE(CFontAwesome5CSS.FA_BALANCE_SCALE, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  BALANCE_SCALE_LEFT(CFontAwesome5CSS.FA_BALANCE_SCALE_LEFT, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  BALANCE_SCALE_RIGHT(CFontAwesome5CSS.FA_BALANCE_SCALE_RIGHT, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  BAN(CFontAwesome5CSS.FA_BAN, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  BAND_AID(CFontAwesome5CSS.FA_BAND_AID, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  BANDCAMP(CFontAwesome5CSS.FA_BANDCAMP, true),
  @Deprecated (forRemoval = true, since = "12.3.0")
  BARCODE(CFontAwesome5CSS.FA_BARCODE, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  BARS(CFontAwesome5CSS.FA_BARS, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  BASEBALL_BALL(CFontAwesome5CSS.FA_BASEBALL_BALL, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  BASKETBALL_BALL(CFontAwesome5CSS.FA_BASKETBALL_BALL, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  BATH(CFontAwesome5CSS.FA_BATH, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  BATTERY_EMPTY(CFontAwesome5CSS.FA_BATTERY_EMPTY, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  BATTERY_FULL(CFontAwesome5CSS.FA_BATTERY_FULL, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  BATTERY_HALF(CFontAwesome5CSS.FA_BATTERY_HALF, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  BATTERY_QUARTER(CFontAwesome5CSS.FA_BATTERY_QUARTER, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  BATTERY_THREE_QUARTERS(CFontAwesome5CSS.FA_BATTERY_THREE_QUARTERS, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  BATTLE_NET(CFontAwesome5CSS.FA_BATTLE_NET, true),
  @Deprecated (forRemoval = true, since = "12.3.0")
  BED(CFontAwesome5CSS.FA_BED, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  BEER(CFontAwesome5CSS.FA_BEER, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  BEHANCE(CFontAwesome5CSS.FA_BEHANCE, true),
  @Deprecated (forRemoval = true, since = "12.3.0")
  BEHANCE_SQUARE(CFontAwesome5CSS.FA_BEHANCE_SQUARE, true),
  @Deprecated (forRemoval = true, since = "12.3.0")
  BELL(CFontAwesome5CSS.FA_BELL, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  BELL_SLASH(CFontAwesome5CSS.FA_BELL_SLASH, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  BEZIER_CURVE(CFontAwesome5CSS.FA_BEZIER_CURVE, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  BIBLE(CFontAwesome5CSS.FA_BIBLE, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  BICYCLE(CFontAwesome5CSS.FA_BICYCLE, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  BIKING(CFontAwesome5CSS.FA_BIKING, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  BIMOBJECT(CFontAwesome5CSS.FA_BIMOBJECT, true),
  @Deprecated (forRemoval = true, since = "12.3.0")
  BINOCULARS(CFontAwesome5CSS.FA_BINOCULARS, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  BIOHAZARD(CFontAwesome5CSS.FA_BIOHAZARD, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  BIRTHDAY_CAKE(CFontAwesome5CSS.FA_BIRTHDAY_CAKE, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  BITBUCKET(CFontAwesome5CSS.FA_BITBUCKET, true),
  @Deprecated (forRemoval = true, since = "12.3.0")
  BITCOIN(CFontAwesome5CSS.FA_BITCOIN, true),
  @Deprecated (forRemoval = true, since = "12.3.0")
  BITY(CFontAwesome5CSS.FA_BITY, true),
  @Deprecated (forRemoval = true, since = "12.3.0")
  BLACK_TIE(CFontAwesome5CSS.FA_BLACK_TIE, true),
  @Deprecated (forRemoval = true, since = "12.3.0")
  BLACKBERRY(CFontAwesome5CSS.FA_BLACKBERRY, true),
  @Deprecated (forRemoval = true, since = "12.3.0")
  BLENDER(CFontAwesome5CSS.FA_BLENDER, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  BLENDER_PHONE(CFontAwesome5CSS.FA_BLENDER_PHONE, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  BLIND(CFontAwesome5CSS.FA_BLIND, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  BLOG(CFontAwesome5CSS.FA_BLOG, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  BLOGGER(CFontAwesome5CSS.FA_BLOGGER, true),
  @Deprecated (forRemoval = true, since = "12.3.0")
  BLOGGER_B(CFontAwesome5CSS.FA_BLOGGER_B, true),
  @Deprecated (forRemoval = true, since = "12.3.0")
  BLUETOOTH(CFontAwesome5CSS.FA_BLUETOOTH, true),
  @Deprecated (forRemoval = true, since = "12.3.0")
  BLUETOOTH_B(CFontAwesome5CSS.FA_BLUETOOTH_B, true),
  @Deprecated (forRemoval = true, since = "12.3.0")
  BOLD(CFontAwesome5CSS.FA_BOLD, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  BOLT(CFontAwesome5CSS.FA_BOLT, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  BOMB(CFontAwesome5CSS.FA_BOMB, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  BONE(CFontAwesome5CSS.FA_BONE, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  BONG(CFontAwesome5CSS.FA_BONG, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  BOOK(CFontAwesome5CSS.FA_BOOK, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  BOOK_DEAD(CFontAwesome5CSS.FA_BOOK_DEAD, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  BOOK_MEDICAL(CFontAwesome5CSS.FA_BOOK_MEDICAL, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  BOOK_OPEN(CFontAwesome5CSS.FA_BOOK_OPEN, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  BOOK_READER(CFontAwesome5CSS.FA_BOOK_READER, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  BOOKMARK(CFontAwesome5CSS.FA_BOOKMARK, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  BOOTSTRAP(CFontAwesome5CSS.FA_BOOTSTRAP, true),
  @Deprecated (forRemoval = true, since = "12.3.0")
  BORDER_ALL(CFontAwesome5CSS.FA_BORDER_ALL, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  BORDER_NONE(CFontAwesome5CSS.FA_BORDER_NONE, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  BORDER_STYLE(CFontAwesome5CSS.FA_BORDER_STYLE, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  BOWLING_BALL(CFontAwesome5CSS.FA_BOWLING_BALL, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  BOX(CFontAwesome5CSS.FA_BOX, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  BOX_OPEN(CFontAwesome5CSS.FA_BOX_OPEN, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  BOX_TISSUE(CFontAwesome5CSS.FA_BOX_TISSUE, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  BOXES(CFontAwesome5CSS.FA_BOXES, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  BRAILLE(CFontAwesome5CSS.FA_BRAILLE, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  BRAIN(CFontAwesome5CSS.FA_BRAIN, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  BREAD_SLICE(CFontAwesome5CSS.FA_BREAD_SLICE, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  BRIEFCASE(CFontAwesome5CSS.FA_BRIEFCASE, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  BRIEFCASE_MEDICAL(CFontAwesome5CSS.FA_BRIEFCASE_MEDICAL, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  BROADCAST_TOWER(CFontAwesome5CSS.FA_BROADCAST_TOWER, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  BROOM(CFontAwesome5CSS.FA_BROOM, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  BRUSH(CFontAwesome5CSS.FA_BRUSH, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  BTC(CFontAwesome5CSS.FA_BTC, true),
  @Deprecated (forRemoval = true, since = "12.3.0")
  BUFFER(CFontAwesome5CSS.FA_BUFFER, true),
  @Deprecated (forRemoval = true, since = "12.3.0")
  BUG(CFontAwesome5CSS.FA_BUG, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  BUILDING(CFontAwesome5CSS.FA_BUILDING, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  BULLHORN(CFontAwesome5CSS.FA_BULLHORN, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  BULLSEYE(CFontAwesome5CSS.FA_BULLSEYE, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  BURN(CFontAwesome5CSS.FA_BURN, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  BUROMOBELEXPERTE(CFontAwesome5CSS.FA_BUROMOBELEXPERTE, true),
  @Deprecated (forRemoval = true, since = "12.3.0")
  BUS(CFontAwesome5CSS.FA_BUS, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  BUS_ALT(CFontAwesome5CSS.FA_BUS_ALT, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  BUSINESS_TIME(CFontAwesome5CSS.FA_BUSINESS_TIME, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  BUY_N_LARGE(CFontAwesome5CSS.FA_BUY_N_LARGE, true),
  @Deprecated (forRemoval = true, since = "12.3.0")
  BUYSELLADS(CFontAwesome5CSS.FA_BUYSELLADS, true),
  @Deprecated (forRemoval = true, since = "12.3.0")
  CALCULATOR(CFontAwesome5CSS.FA_CALCULATOR, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  CALENDAR(CFontAwesome5CSS.FA_CALENDAR, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  CALENDAR_ALT(CFontAwesome5CSS.FA_CALENDAR_ALT, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  CALENDAR_CHECK(CFontAwesome5CSS.FA_CALENDAR_CHECK, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  CALENDAR_DAY(CFontAwesome5CSS.FA_CALENDAR_DAY, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  CALENDAR_MINUS(CFontAwesome5CSS.FA_CALENDAR_MINUS, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  CALENDAR_PLUS(CFontAwesome5CSS.FA_CALENDAR_PLUS, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  CALENDAR_TIMES(CFontAwesome5CSS.FA_CALENDAR_TIMES, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  CALENDAR_WEEK(CFontAwesome5CSS.FA_CALENDAR_WEEK, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  CAMERA(CFontAwesome5CSS.FA_CAMERA, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  CAMERA_RETRO(CFontAwesome5CSS.FA_CAMERA_RETRO, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  CAMPGROUND(CFontAwesome5CSS.FA_CAMPGROUND, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  CANADIAN_MAPLE_LEAF(CFontAwesome5CSS.FA_CANADIAN_MAPLE_LEAF, true),
  @Deprecated (forRemoval = true, since = "12.3.0")
  CANDY_CANE(CFontAwesome5CSS.FA_CANDY_CANE, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  CANNABIS(CFontAwesome5CSS.FA_CANNABIS, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  CAPSULES(CFontAwesome5CSS.FA_CAPSULES, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  CAR(CFontAwesome5CSS.FA_CAR, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  CAR_ALT(CFontAwesome5CSS.FA_CAR_ALT, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  CAR_BATTERY(CFontAwesome5CSS.FA_CAR_BATTERY, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  CAR_CRASH(CFontAwesome5CSS.FA_CAR_CRASH, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  CAR_SIDE(CFontAwesome5CSS.FA_CAR_SIDE, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  CARAVAN(CFontAwesome5CSS.FA_CARAVAN, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  CARET_DOWN(CFontAwesome5CSS.FA_CARET_DOWN, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  CARET_LEFT(CFontAwesome5CSS.FA_CARET_LEFT, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  CARET_RIGHT(CFontAwesome5CSS.FA_CARET_RIGHT, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  CARET_SQUARE_DOWN(CFontAwesome5CSS.FA_CARET_SQUARE_DOWN, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  CARET_SQUARE_LEFT(CFontAwesome5CSS.FA_CARET_SQUARE_LEFT, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  CARET_SQUARE_RIGHT(CFontAwesome5CSS.FA_CARET_SQUARE_RIGHT, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  CARET_SQUARE_UP(CFontAwesome5CSS.FA_CARET_SQUARE_UP, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  CARET_UP(CFontAwesome5CSS.FA_CARET_UP, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  CARROT(CFontAwesome5CSS.FA_CARROT, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  CART_ARROW_DOWN(CFontAwesome5CSS.FA_CART_ARROW_DOWN, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  CART_PLUS(CFontAwesome5CSS.FA_CART_PLUS, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  CASH_REGISTER(CFontAwesome5CSS.FA_CASH_REGISTER, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  CAT(CFontAwesome5CSS.FA_CAT, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  CC_AMAZON_PAY(CFontAwesome5CSS.FA_CC_AMAZON_PAY, true),
  @Deprecated (forRemoval = true, since = "12.3.0")
  CC_AMEX(CFontAwesome5CSS.FA_CC_AMEX, true),
  @Deprecated (forRemoval = true, since = "12.3.0")
  CC_APPLE_PAY(CFontAwesome5CSS.FA_CC_APPLE_PAY, true),
  @Deprecated (forRemoval = true, since = "12.3.0")
  CC_DINERS_CLUB(CFontAwesome5CSS.FA_CC_DINERS_CLUB, true),
  @Deprecated (forRemoval = true, since = "12.3.0")
  CC_DISCOVER(CFontAwesome5CSS.FA_CC_DISCOVER, true),
  @Deprecated (forRemoval = true, since = "12.3.0")
  CC_JCB(CFontAwesome5CSS.FA_CC_JCB, true),
  @Deprecated (forRemoval = true, since = "12.3.0")
  CC_MASTERCARD(CFontAwesome5CSS.FA_CC_MASTERCARD, true),
  @Deprecated (forRemoval = true, since = "12.3.0")
  CC_PAYPAL(CFontAwesome5CSS.FA_CC_PAYPAL, true),
  @Deprecated (forRemoval = true, since = "12.3.0")
  CC_STRIPE(CFontAwesome5CSS.FA_CC_STRIPE, true),
  @Deprecated (forRemoval = true, since = "12.3.0")
  CC_VISA(CFontAwesome5CSS.FA_CC_VISA, true),
  @Deprecated (forRemoval = true, since = "12.3.0")
  CENTERCODE(CFontAwesome5CSS.FA_CENTERCODE, true),
  @Deprecated (forRemoval = true, since = "12.3.0")
  CENTOS(CFontAwesome5CSS.FA_CENTOS, true),
  @Deprecated (forRemoval = true, since = "12.3.0")
  CERTIFICATE(CFontAwesome5CSS.FA_CERTIFICATE, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  CHAIR(CFontAwesome5CSS.FA_CHAIR, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  CHALKBOARD(CFontAwesome5CSS.FA_CHALKBOARD, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  CHALKBOARD_TEACHER(CFontAwesome5CSS.FA_CHALKBOARD_TEACHER, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  CHARGING_STATION(CFontAwesome5CSS.FA_CHARGING_STATION, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  CHART_AREA(CFontAwesome5CSS.FA_CHART_AREA, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  CHART_BAR(CFontAwesome5CSS.FA_CHART_BAR, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  CHART_LINE(CFontAwesome5CSS.FA_CHART_LINE, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  CHART_PIE(CFontAwesome5CSS.FA_CHART_PIE, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  CHECK(CFontAwesome5CSS.FA_CHECK, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  CHECK_CIRCLE(CFontAwesome5CSS.FA_CHECK_CIRCLE, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  CHECK_DOUBLE(CFontAwesome5CSS.FA_CHECK_DOUBLE, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  CHECK_SQUARE(CFontAwesome5CSS.FA_CHECK_SQUARE, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  CHEESE(CFontAwesome5CSS.FA_CHEESE, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  CHESS(CFontAwesome5CSS.FA_CHESS, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  CHESS_BISHOP(CFontAwesome5CSS.FA_CHESS_BISHOP, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  CHESS_BOARD(CFontAwesome5CSS.FA_CHESS_BOARD, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  CHESS_KING(CFontAwesome5CSS.FA_CHESS_KING, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  CHESS_KNIGHT(CFontAwesome5CSS.FA_CHESS_KNIGHT, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  CHESS_PAWN(CFontAwesome5CSS.FA_CHESS_PAWN, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  CHESS_QUEEN(CFontAwesome5CSS.FA_CHESS_QUEEN, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  CHESS_ROOK(CFontAwesome5CSS.FA_CHESS_ROOK, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  CHEVRON_CIRCLE_DOWN(CFontAwesome5CSS.FA_CHEVRON_CIRCLE_DOWN, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  CHEVRON_CIRCLE_LEFT(CFontAwesome5CSS.FA_CHEVRON_CIRCLE_LEFT, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  CHEVRON_CIRCLE_RIGHT(CFontAwesome5CSS.FA_CHEVRON_CIRCLE_RIGHT, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  CHEVRON_CIRCLE_UP(CFontAwesome5CSS.FA_CHEVRON_CIRCLE_UP, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  CHEVRON_DOWN(CFontAwesome5CSS.FA_CHEVRON_DOWN, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  CHEVRON_LEFT(CFontAwesome5CSS.FA_CHEVRON_LEFT, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  CHEVRON_RIGHT(CFontAwesome5CSS.FA_CHEVRON_RIGHT, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  CHEVRON_UP(CFontAwesome5CSS.FA_CHEVRON_UP, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  CHILD(CFontAwesome5CSS.FA_CHILD, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  CHROME(CFontAwesome5CSS.FA_CHROME, true),
  @Deprecated (forRemoval = true, since = "12.3.0")
  CHROMECAST(CFontAwesome5CSS.FA_CHROMECAST, true),
  @Deprecated (forRemoval = true, since = "12.3.0")
  CHURCH(CFontAwesome5CSS.FA_CHURCH, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  CIRCLE(CFontAwesome5CSS.FA_CIRCLE, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  CIRCLE_NOTCH(CFontAwesome5CSS.FA_CIRCLE_NOTCH, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  CITY(CFontAwesome5CSS.FA_CITY, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  CLINIC_MEDICAL(CFontAwesome5CSS.FA_CLINIC_MEDICAL, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  CLIPBOARD(CFontAwesome5CSS.FA_CLIPBOARD, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  CLIPBOARD_CHECK(CFontAwesome5CSS.FA_CLIPBOARD_CHECK, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  CLIPBOARD_LIST(CFontAwesome5CSS.FA_CLIPBOARD_LIST, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  CLOCK(CFontAwesome5CSS.FA_CLOCK, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  CLONE(CFontAwesome5CSS.FA_CLONE, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  CLOSED_CAPTIONING(CFontAwesome5CSS.FA_CLOSED_CAPTIONING, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  CLOUD(CFontAwesome5CSS.FA_CLOUD, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  CLOUD_DOWNLOAD_ALT(CFontAwesome5CSS.FA_CLOUD_DOWNLOAD_ALT, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  CLOUD_MEATBALL(CFontAwesome5CSS.FA_CLOUD_MEATBALL, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  CLOUD_MOON(CFontAwesome5CSS.FA_CLOUD_MOON, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  CLOUD_MOON_RAIN(CFontAwesome5CSS.FA_CLOUD_MOON_RAIN, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  CLOUD_RAIN(CFontAwesome5CSS.FA_CLOUD_RAIN, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  CLOUD_SHOWERS_HEAVY(CFontAwesome5CSS.FA_CLOUD_SHOWERS_HEAVY, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  CLOUD_SUN(CFontAwesome5CSS.FA_CLOUD_SUN, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  CLOUD_SUN_RAIN(CFontAwesome5CSS.FA_CLOUD_SUN_RAIN, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  CLOUD_UPLOAD_ALT(CFontAwesome5CSS.FA_CLOUD_UPLOAD_ALT, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  CLOUDFLARE(CFontAwesome5CSS.FA_CLOUDFLARE, true),
  @Deprecated (forRemoval = true, since = "12.3.0")
  CLOUDSCALE(CFontAwesome5CSS.FA_CLOUDSCALE, true),
  @Deprecated (forRemoval = true, since = "12.3.0")
  CLOUDSMITH(CFontAwesome5CSS.FA_CLOUDSMITH, true),
  @Deprecated (forRemoval = true, since = "12.3.0")
  CLOUDVERSIFY(CFontAwesome5CSS.FA_CLOUDVERSIFY, true),
  @Deprecated (forRemoval = true, since = "12.3.0")
  COCKTAIL(CFontAwesome5CSS.FA_COCKTAIL, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  CODE(CFontAwesome5CSS.FA_CODE, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  CODE_BRANCH(CFontAwesome5CSS.FA_CODE_BRANCH, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  CODEPEN(CFontAwesome5CSS.FA_CODEPEN, true),
  @Deprecated (forRemoval = true, since = "12.3.0")
  CODIEPIE(CFontAwesome5CSS.FA_CODIEPIE, true),
  @Deprecated (forRemoval = true, since = "12.3.0")
  COFFEE(CFontAwesome5CSS.FA_COFFEE, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  COG(CFontAwesome5CSS.FA_COG, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  COGS(CFontAwesome5CSS.FA_COGS, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  COINS(CFontAwesome5CSS.FA_COINS, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  COLUMNS(CFontAwesome5CSS.FA_COLUMNS, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  COMMENT(CFontAwesome5CSS.FA_COMMENT, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  COMMENT_ALT(CFontAwesome5CSS.FA_COMMENT_ALT, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  COMMENT_DOLLAR(CFontAwesome5CSS.FA_COMMENT_DOLLAR, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  COMMENT_DOTS(CFontAwesome5CSS.FA_COMMENT_DOTS, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  COMMENT_MEDICAL(CFontAwesome5CSS.FA_COMMENT_MEDICAL, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  COMMENT_SLASH(CFontAwesome5CSS.FA_COMMENT_SLASH, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  COMMENTS(CFontAwesome5CSS.FA_COMMENTS, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  COMMENTS_DOLLAR(CFontAwesome5CSS.FA_COMMENTS_DOLLAR, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  COMPACT_DISC(CFontAwesome5CSS.FA_COMPACT_DISC, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  COMPASS(CFontAwesome5CSS.FA_COMPASS, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  COMPRESS(CFontAwesome5CSS.FA_COMPRESS, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  COMPRESS_ALT(CFontAwesome5CSS.FA_COMPRESS_ALT, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  COMPRESS_ARROWS_ALT(CFontAwesome5CSS.FA_COMPRESS_ARROWS_ALT, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  CONCIERGE_BELL(CFontAwesome5CSS.FA_CONCIERGE_BELL, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  CONFLUENCE(CFontAwesome5CSS.FA_CONFLUENCE, true),
  @Deprecated (forRemoval = true, since = "12.3.0")
  CONNECTDEVELOP(CFontAwesome5CSS.FA_CONNECTDEVELOP, true),
  @Deprecated (forRemoval = true, since = "12.3.0")
  CONTAO(CFontAwesome5CSS.FA_CONTAO, true),
  @Deprecated (forRemoval = true, since = "12.3.0")
  COOKIE(CFontAwesome5CSS.FA_COOKIE, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  COOKIE_BITE(CFontAwesome5CSS.FA_COOKIE_BITE, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  COPY(CFontAwesome5CSS.FA_COPY, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  COPYRIGHT(CFontAwesome5CSS.FA_COPYRIGHT, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  COTTON_BUREAU(CFontAwesome5CSS.FA_COTTON_BUREAU, true),
  @Deprecated (forRemoval = true, since = "12.3.0")
  COUCH(CFontAwesome5CSS.FA_COUCH, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  CPANEL(CFontAwesome5CSS.FA_CPANEL, true),
  @Deprecated (forRemoval = true, since = "12.3.0")
  CREATIVE_COMMONS(CFontAwesome5CSS.FA_CREATIVE_COMMONS, true),
  @Deprecated (forRemoval = true, since = "12.3.0")
  CREATIVE_COMMONS_BY(CFontAwesome5CSS.FA_CREATIVE_COMMONS_BY, true),
  @Deprecated (forRemoval = true, since = "12.3.0")
  CREATIVE_COMMONS_NC(CFontAwesome5CSS.FA_CREATIVE_COMMONS_NC, true),
  @Deprecated (forRemoval = true, since = "12.3.0")
  CREATIVE_COMMONS_NC_EU(CFontAwesome5CSS.FA_CREATIVE_COMMONS_NC_EU, true),
  @Deprecated (forRemoval = true, since = "12.3.0")
  CREATIVE_COMMONS_NC_JP(CFontAwesome5CSS.FA_CREATIVE_COMMONS_NC_JP, true),
  @Deprecated (forRemoval = true, since = "12.3.0")
  CREATIVE_COMMONS_ND(CFontAwesome5CSS.FA_CREATIVE_COMMONS_ND, true),
  @Deprecated (forRemoval = true, since = "12.3.0")
  CREATIVE_COMMONS_PD(CFontAwesome5CSS.FA_CREATIVE_COMMONS_PD, true),
  @Deprecated (forRemoval = true, since = "12.3.0")
  CREATIVE_COMMONS_PD_ALT(CFontAwesome5CSS.FA_CREATIVE_COMMONS_PD_ALT, true),
  @Deprecated (forRemoval = true, since = "12.3.0")
  CREATIVE_COMMONS_REMIX(CFontAwesome5CSS.FA_CREATIVE_COMMONS_REMIX, true),
  @Deprecated (forRemoval = true, since = "12.3.0")
  CREATIVE_COMMONS_SA(CFontAwesome5CSS.FA_CREATIVE_COMMONS_SA, true),
  @Deprecated (forRemoval = true, since = "12.3.0")
  CREATIVE_COMMONS_SAMPLING(CFontAwesome5CSS.FA_CREATIVE_COMMONS_SAMPLING, true),
  @Deprecated (forRemoval = true, since = "12.3.0")
  CREATIVE_COMMONS_SAMPLING_PLUS(CFontAwesome5CSS.FA_CREATIVE_COMMONS_SAMPLING_PLUS, true),
  @Deprecated (forRemoval = true, since = "12.3.0")
  CREATIVE_COMMONS_SHARE(CFontAwesome5CSS.FA_CREATIVE_COMMONS_SHARE, true),
  @Deprecated (forRemoval = true, since = "12.3.0")
  CREATIVE_COMMONS_ZERO(CFontAwesome5CSS.FA_CREATIVE_COMMONS_ZERO, true),
  @Deprecated (forRemoval = true, since = "12.3.0")
  CREDIT_CARD(CFontAwesome5CSS.FA_CREDIT_CARD, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  CRITICAL_ROLE(CFontAwesome5CSS.FA_CRITICAL_ROLE, true),
  @Deprecated (forRemoval = true, since = "12.3.0")
  CROP(CFontAwesome5CSS.FA_CROP, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  CROP_ALT(CFontAwesome5CSS.FA_CROP_ALT, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  CROSS(CFontAwesome5CSS.FA_CROSS, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  CROSSHAIRS(CFontAwesome5CSS.FA_CROSSHAIRS, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  CROW(CFontAwesome5CSS.FA_CROW, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  CROWN(CFontAwesome5CSS.FA_CROWN, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  CRUTCH(CFontAwesome5CSS.FA_CRUTCH, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  CSS3(CFontAwesome5CSS.FA_CSS3, true),
  @Deprecated (forRemoval = true, since = "12.3.0")
  CSS3_ALT(CFontAwesome5CSS.FA_CSS3_ALT, true),
  @Deprecated (forRemoval = true, since = "12.3.0")
  CUBE(CFontAwesome5CSS.FA_CUBE, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  CUBES(CFontAwesome5CSS.FA_CUBES, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  CUT(CFontAwesome5CSS.FA_CUT, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  CUTTLEFISH(CFontAwesome5CSS.FA_CUTTLEFISH, true),
  @Deprecated (forRemoval = true, since = "12.3.0")
  D_AND_D(CFontAwesome5CSS.FA_D_AND_D, true),
  @Deprecated (forRemoval = true, since = "12.3.0")
  D_AND_D_BEYOND(CFontAwesome5CSS.FA_D_AND_D_BEYOND, true),
  @Deprecated (forRemoval = true, since = "12.3.0")
  DAILYMOTION(CFontAwesome5CSS.FA_DAILYMOTION, true),
  @Deprecated (forRemoval = true, since = "12.3.0")
  DASHCUBE(CFontAwesome5CSS.FA_DASHCUBE, true),
  @Deprecated (forRemoval = true, since = "12.3.0")
  DATABASE(CFontAwesome5CSS.FA_DATABASE, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  DEAF(CFontAwesome5CSS.FA_DEAF, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  DEEZER(CFontAwesome5CSS.FA_DEEZER, true),
  @Deprecated (forRemoval = true, since = "12.3.0")
  DELICIOUS(CFontAwesome5CSS.FA_DELICIOUS, true),
  @Deprecated (forRemoval = true, since = "12.3.0")
  DEMOCRAT(CFontAwesome5CSS.FA_DEMOCRAT, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  DEPLOYDOG(CFontAwesome5CSS.FA_DEPLOYDOG, true),
  @Deprecated (forRemoval = true, since = "12.3.0")
  DESKPRO(CFontAwesome5CSS.FA_DESKPRO, true),
  @Deprecated (forRemoval = true, since = "12.3.0")
  DESKTOP(CFontAwesome5CSS.FA_DESKTOP, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  DEV(CFontAwesome5CSS.FA_DEV, true),
  @Deprecated (forRemoval = true, since = "12.3.0")
  DEVIANTART(CFontAwesome5CSS.FA_DEVIANTART, true),
  @Deprecated (forRemoval = true, since = "12.3.0")
  DHARMACHAKRA(CFontAwesome5CSS.FA_DHARMACHAKRA, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  DHL(CFontAwesome5CSS.FA_DHL, true),
  @Deprecated (forRemoval = true, since = "12.3.0")
  DIAGNOSES(CFontAwesome5CSS.FA_DIAGNOSES, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  DIASPORA(CFontAwesome5CSS.FA_DIASPORA, true),
  @Deprecated (forRemoval = true, since = "12.3.0")
  DICE(CFontAwesome5CSS.FA_DICE, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  DICE_D20(CFontAwesome5CSS.FA_DICE_D20, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  DICE_D6(CFontAwesome5CSS.FA_DICE_D6, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  DICE_FIVE(CFontAwesome5CSS.FA_DICE_FIVE, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  DICE_FOUR(CFontAwesome5CSS.FA_DICE_FOUR, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  DICE_ONE(CFontAwesome5CSS.FA_DICE_ONE, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  DICE_SIX(CFontAwesome5CSS.FA_DICE_SIX, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  DICE_THREE(CFontAwesome5CSS.FA_DICE_THREE, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  DICE_TWO(CFontAwesome5CSS.FA_DICE_TWO, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  DIGG(CFontAwesome5CSS.FA_DIGG, true),
  @Deprecated (forRemoval = true, since = "12.3.0")
  DIGITAL_OCEAN(CFontAwesome5CSS.FA_DIGITAL_OCEAN, true),
  @Deprecated (forRemoval = true, since = "12.3.0")
  DIGITAL_TACHOGRAPH(CFontAwesome5CSS.FA_DIGITAL_TACHOGRAPH, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  DIRECTIONS(CFontAwesome5CSS.FA_DIRECTIONS, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  DISCORD(CFontAwesome5CSS.FA_DISCORD, true),
  @Deprecated (forRemoval = true, since = "12.3.0")
  DISCOURSE(CFontAwesome5CSS.FA_DISCOURSE, true),
  @Deprecated (forRemoval = true, since = "12.3.0")
  DISEASE(CFontAwesome5CSS.FA_DISEASE, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  DIVIDE(CFontAwesome5CSS.FA_DIVIDE, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  DIZZY(CFontAwesome5CSS.FA_DIZZY, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  DNA(CFontAwesome5CSS.FA_DNA, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  DOCHUB(CFontAwesome5CSS.FA_DOCHUB, true),
  @Deprecated (forRemoval = true, since = "12.3.0")
  DOCKER(CFontAwesome5CSS.FA_DOCKER, true),
  @Deprecated (forRemoval = true, since = "12.3.0")
  DOG(CFontAwesome5CSS.FA_DOG, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  DOLLAR_SIGN(CFontAwesome5CSS.FA_DOLLAR_SIGN, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  DOLLY(CFontAwesome5CSS.FA_DOLLY, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  DOLLY_FLATBED(CFontAwesome5CSS.FA_DOLLY_FLATBED, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  DONATE(CFontAwesome5CSS.FA_DONATE, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  DOOR_CLOSED(CFontAwesome5CSS.FA_DOOR_CLOSED, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  DOOR_OPEN(CFontAwesome5CSS.FA_DOOR_OPEN, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  DOT_CIRCLE(CFontAwesome5CSS.FA_DOT_CIRCLE, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  DOVE(CFontAwesome5CSS.FA_DOVE, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  DOWNLOAD(CFontAwesome5CSS.FA_DOWNLOAD, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  DRAFT2DIGITAL(CFontAwesome5CSS.FA_DRAFT2DIGITAL, true),
  @Deprecated (forRemoval = true, since = "12.3.0")
  DRAFTING_COMPASS(CFontAwesome5CSS.FA_DRAFTING_COMPASS, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  DRAGON(CFontAwesome5CSS.FA_DRAGON, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  DRAW_POLYGON(CFontAwesome5CSS.FA_DRAW_POLYGON, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  DRIBBBLE(CFontAwesome5CSS.FA_DRIBBBLE, true),
  @Deprecated (forRemoval = true, since = "12.3.0")
  DRIBBBLE_SQUARE(CFontAwesome5CSS.FA_DRIBBBLE_SQUARE, true),
  @Deprecated (forRemoval = true, since = "12.3.0")
  DROPBOX(CFontAwesome5CSS.FA_DROPBOX, true),
  @Deprecated (forRemoval = true, since = "12.3.0")
  DRUM(CFontAwesome5CSS.FA_DRUM, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  DRUM_STEELPAN(CFontAwesome5CSS.FA_DRUM_STEELPAN, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  DRUMSTICK_BITE(CFontAwesome5CSS.FA_DRUMSTICK_BITE, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  DRUPAL(CFontAwesome5CSS.FA_DRUPAL, true),
  @Deprecated (forRemoval = true, since = "12.3.0")
  DUMBBELL(CFontAwesome5CSS.FA_DUMBBELL, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  DUMPSTER(CFontAwesome5CSS.FA_DUMPSTER, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  DUMPSTER_FIRE(CFontAwesome5CSS.FA_DUMPSTER_FIRE, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  DUNGEON(CFontAwesome5CSS.FA_DUNGEON, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  DYALOG(CFontAwesome5CSS.FA_DYALOG, true),
  @Deprecated (forRemoval = true, since = "12.3.0")
  EARLYBIRDS(CFontAwesome5CSS.FA_EARLYBIRDS, true),
  @Deprecated (forRemoval = true, since = "12.3.0")
  EBAY(CFontAwesome5CSS.FA_EBAY, true),
  @Deprecated (forRemoval = true, since = "12.3.0")
  EDGE(CFontAwesome5CSS.FA_EDGE, true),
  @Deprecated (forRemoval = true, since = "12.3.0")
  EDGE_LEGACY(CFontAwesome5CSS.FA_EDGE_LEGACY, true),
  @Deprecated (forRemoval = true, since = "12.3.0")
  EDIT(CFontAwesome5CSS.FA_EDIT, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  EGG(CFontAwesome5CSS.FA_EGG, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  EJECT(CFontAwesome5CSS.FA_EJECT, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  ELEMENTOR(CFontAwesome5CSS.FA_ELEMENTOR, true),
  @Deprecated (forRemoval = true, since = "12.3.0")
  ELLIPSIS_H(CFontAwesome5CSS.FA_ELLIPSIS_H, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  ELLIPSIS_V(CFontAwesome5CSS.FA_ELLIPSIS_V, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  ELLO(CFontAwesome5CSS.FA_ELLO, true),
  @Deprecated (forRemoval = true, since = "12.3.0")
  EMBER(CFontAwesome5CSS.FA_EMBER, true),
  @Deprecated (forRemoval = true, since = "12.3.0")
  EMPIRE(CFontAwesome5CSS.FA_EMPIRE, true),
  @Deprecated (forRemoval = true, since = "12.3.0")
  ENVELOPE(CFontAwesome5CSS.FA_ENVELOPE, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  ENVELOPE_OPEN(CFontAwesome5CSS.FA_ENVELOPE_OPEN, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  ENVELOPE_OPEN_TEXT(CFontAwesome5CSS.FA_ENVELOPE_OPEN_TEXT, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  ENVELOPE_SQUARE(CFontAwesome5CSS.FA_ENVELOPE_SQUARE, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  ENVIRA(CFontAwesome5CSS.FA_ENVIRA, true),
  @Deprecated (forRemoval = true, since = "12.3.0")
  EQUALS(CFontAwesome5CSS.FA_EQUALS, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  ERASER(CFontAwesome5CSS.FA_ERASER, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  ERLANG(CFontAwesome5CSS.FA_ERLANG, true),
  @Deprecated (forRemoval = true, since = "12.3.0")
  ETHEREUM(CFontAwesome5CSS.FA_ETHEREUM, true),
  @Deprecated (forRemoval = true, since = "12.3.0")
  ETHERNET(CFontAwesome5CSS.FA_ETHERNET, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  ETSY(CFontAwesome5CSS.FA_ETSY, true),
  @Deprecated (forRemoval = true, since = "12.3.0")
  EURO_SIGN(CFontAwesome5CSS.FA_EURO_SIGN, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  EVERNOTE(CFontAwesome5CSS.FA_EVERNOTE, true),
  @Deprecated (forRemoval = true, since = "12.3.0")
  EXCHANGE_ALT(CFontAwesome5CSS.FA_EXCHANGE_ALT, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  EXCLAMATION(CFontAwesome5CSS.FA_EXCLAMATION, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  EXCLAMATION_CIRCLE(CFontAwesome5CSS.FA_EXCLAMATION_CIRCLE, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  EXCLAMATION_TRIANGLE(CFontAwesome5CSS.FA_EXCLAMATION_TRIANGLE, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  EXPAND(CFontAwesome5CSS.FA_EXPAND, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  EXPAND_ALT(CFontAwesome5CSS.FA_EXPAND_ALT, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  EXPAND_ARROWS_ALT(CFontAwesome5CSS.FA_EXPAND_ARROWS_ALT, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  EXPEDITEDSSL(CFontAwesome5CSS.FA_EXPEDITEDSSL, true),
  @Deprecated (forRemoval = true, since = "12.3.0")
  EXTERNAL_LINK_ALT(CFontAwesome5CSS.FA_EXTERNAL_LINK_ALT, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  EXTERNAL_LINK_SQUARE_ALT(CFontAwesome5CSS.FA_EXTERNAL_LINK_SQUARE_ALT, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  EYE(CFontAwesome5CSS.FA_EYE, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  EYE_DROPPER(CFontAwesome5CSS.FA_EYE_DROPPER, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  EYE_SLASH(CFontAwesome5CSS.FA_EYE_SLASH, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  FACEBOOK(CFontAwesome5CSS.FA_FACEBOOK, true),
  @Deprecated (forRemoval = true, since = "12.3.0")
  FACEBOOK_F(CFontAwesome5CSS.FA_FACEBOOK_F, true),
  @Deprecated (forRemoval = true, since = "12.3.0")
  FACEBOOK_MESSENGER(CFontAwesome5CSS.FA_FACEBOOK_MESSENGER, true),
  @Deprecated (forRemoval = true, since = "12.3.0")
  FACEBOOK_SQUARE(CFontAwesome5CSS.FA_FACEBOOK_SQUARE, true),
  @Deprecated (forRemoval = true, since = "12.3.0")
  FAN(CFontAwesome5CSS.FA_FAN, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  FANTASY_FLIGHT_GAMES(CFontAwesome5CSS.FA_FANTASY_FLIGHT_GAMES, true),
  @Deprecated (forRemoval = true, since = "12.3.0")
  FAST_BACKWARD(CFontAwesome5CSS.FA_FAST_BACKWARD, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  FAST_FORWARD(CFontAwesome5CSS.FA_FAST_FORWARD, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  FAUCET(CFontAwesome5CSS.FA_FAUCET, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  FAX(CFontAwesome5CSS.FA_FAX, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  FEATHER(CFontAwesome5CSS.FA_FEATHER, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  FEATHER_ALT(CFontAwesome5CSS.FA_FEATHER_ALT, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  FEDEX(CFontAwesome5CSS.FA_FEDEX, true),
  @Deprecated (forRemoval = true, since = "12.3.0")
  FEDORA(CFontAwesome5CSS.FA_FEDORA, true),
  @Deprecated (forRemoval = true, since = "12.3.0")
  FEMALE(CFontAwesome5CSS.FA_FEMALE, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  FIGHTER_JET(CFontAwesome5CSS.FA_FIGHTER_JET, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  FIGMA(CFontAwesome5CSS.FA_FIGMA, true),
  @Deprecated (forRemoval = true, since = "12.3.0")
  FILE(CFontAwesome5CSS.FA_FILE, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  FILE_ALT(CFontAwesome5CSS.FA_FILE_ALT, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  FILE_ARCHIVE(CFontAwesome5CSS.FA_FILE_ARCHIVE, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  FILE_AUDIO(CFontAwesome5CSS.FA_FILE_AUDIO, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  FILE_CODE(CFontAwesome5CSS.FA_FILE_CODE, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  FILE_CONTRACT(CFontAwesome5CSS.FA_FILE_CONTRACT, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  FILE_CSV(CFontAwesome5CSS.FA_FILE_CSV, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  FILE_DOWNLOAD(CFontAwesome5CSS.FA_FILE_DOWNLOAD, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  FILE_EXCEL(CFontAwesome5CSS.FA_FILE_EXCEL, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  FILE_EXPORT(CFontAwesome5CSS.FA_FILE_EXPORT, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  FILE_IMAGE(CFontAwesome5CSS.FA_FILE_IMAGE, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  FILE_IMPORT(CFontAwesome5CSS.FA_FILE_IMPORT, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  FILE_INVOICE(CFontAwesome5CSS.FA_FILE_INVOICE, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  FILE_INVOICE_DOLLAR(CFontAwesome5CSS.FA_FILE_INVOICE_DOLLAR, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  FILE_MEDICAL(CFontAwesome5CSS.FA_FILE_MEDICAL, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  FILE_MEDICAL_ALT(CFontAwesome5CSS.FA_FILE_MEDICAL_ALT, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  FILE_PDF(CFontAwesome5CSS.FA_FILE_PDF, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  FILE_POWERPOINT(CFontAwesome5CSS.FA_FILE_POWERPOINT, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  FILE_PRESCRIPTION(CFontAwesome5CSS.FA_FILE_PRESCRIPTION, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  FILE_SIGNATURE(CFontAwesome5CSS.FA_FILE_SIGNATURE, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  FILE_UPLOAD(CFontAwesome5CSS.FA_FILE_UPLOAD, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  FILE_VIDEO(CFontAwesome5CSS.FA_FILE_VIDEO, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  FILE_WORD(CFontAwesome5CSS.FA_FILE_WORD, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  FILL(CFontAwesome5CSS.FA_FILL, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  FILL_DRIP(CFontAwesome5CSS.FA_FILL_DRIP, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  FILM(CFontAwesome5CSS.FA_FILM, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  FILTER(CFontAwesome5CSS.FA_FILTER, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  FINGERPRINT(CFontAwesome5CSS.FA_FINGERPRINT, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  FIRE(CFontAwesome5CSS.FA_FIRE, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  FIRE_ALT(CFontAwesome5CSS.FA_FIRE_ALT, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  FIRE_EXTINGUISHER(CFontAwesome5CSS.FA_FIRE_EXTINGUISHER, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  FIREFOX(CFontAwesome5CSS.FA_FIREFOX, true),
  @Deprecated (forRemoval = true, since = "12.3.0")
  FIREFOX_BROWSER(CFontAwesome5CSS.FA_FIREFOX_BROWSER, true),
  @Deprecated (forRemoval = true, since = "12.3.0")
  FIRST_AID(CFontAwesome5CSS.FA_FIRST_AID, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  FIRST_ORDER(CFontAwesome5CSS.FA_FIRST_ORDER, true),
  @Deprecated (forRemoval = true, since = "12.3.0")
  FIRST_ORDER_ALT(CFontAwesome5CSS.FA_FIRST_ORDER_ALT, true),
  @Deprecated (forRemoval = true, since = "12.3.0")
  FIRSTDRAFT(CFontAwesome5CSS.FA_FIRSTDRAFT, true),
  @Deprecated (forRemoval = true, since = "12.3.0")
  FISH(CFontAwesome5CSS.FA_FISH, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  FIST_RAISED(CFontAwesome5CSS.FA_FIST_RAISED, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  FLAG(CFontAwesome5CSS.FA_FLAG, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  FLAG_CHECKERED(CFontAwesome5CSS.FA_FLAG_CHECKERED, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  FLAG_USA(CFontAwesome5CSS.FA_FLAG_USA, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  FLASK(CFontAwesome5CSS.FA_FLASK, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  FLICKR(CFontAwesome5CSS.FA_FLICKR, true),
  @Deprecated (forRemoval = true, since = "12.3.0")
  FLIPBOARD(CFontAwesome5CSS.FA_FLIPBOARD, true),
  @Deprecated (forRemoval = true, since = "12.3.0")
  FLUSHED(CFontAwesome5CSS.FA_FLUSHED, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  FLY(CFontAwesome5CSS.FA_FLY, true),
  @Deprecated (forRemoval = true, since = "12.3.0")
  FOLDER(CFontAwesome5CSS.FA_FOLDER, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  FOLDER_MINUS(CFontAwesome5CSS.FA_FOLDER_MINUS, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  FOLDER_OPEN(CFontAwesome5CSS.FA_FOLDER_OPEN, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  FOLDER_PLUS(CFontAwesome5CSS.FA_FOLDER_PLUS, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  FONT(CFontAwesome5CSS.FA_FONT, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  FONT_AWESOME(CFontAwesome5CSS.FA_FONT_AWESOME, true),
  @Deprecated (forRemoval = true, since = "12.3.0")
  FONT_AWESOME_ALT(CFontAwesome5CSS.FA_FONT_AWESOME_ALT, true),
  @Deprecated (forRemoval = true, since = "12.3.0")
  FONT_AWESOME_FLAG(CFontAwesome5CSS.FA_FONT_AWESOME_FLAG, true),
  @Deprecated (forRemoval = true, since = "12.3.0")
  FONT_AWESOME_LOGO_FULL(CFontAwesome5CSS.FA_FONT_AWESOME_LOGO_FULL, true),
  @Deprecated (forRemoval = true, since = "12.3.0")
  FONTICONS(CFontAwesome5CSS.FA_FONTICONS, true),
  @Deprecated (forRemoval = true, since = "12.3.0")
  FONTICONS_FI(CFontAwesome5CSS.FA_FONTICONS_FI, true),
  @Deprecated (forRemoval = true, since = "12.3.0")
  FOOTBALL_BALL(CFontAwesome5CSS.FA_FOOTBALL_BALL, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  FORT_AWESOME(CFontAwesome5CSS.FA_FORT_AWESOME, true),
  @Deprecated (forRemoval = true, since = "12.3.0")
  FORT_AWESOME_ALT(CFontAwesome5CSS.FA_FORT_AWESOME_ALT, true),
  @Deprecated (forRemoval = true, since = "12.3.0")
  FORUMBEE(CFontAwesome5CSS.FA_FORUMBEE, true),
  @Deprecated (forRemoval = true, since = "12.3.0")
  FORWARD(CFontAwesome5CSS.FA_FORWARD, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  FOURSQUARE(CFontAwesome5CSS.FA_FOURSQUARE, true),
  @Deprecated (forRemoval = true, since = "12.3.0")
  FREE_CODE_CAMP(CFontAwesome5CSS.FA_FREE_CODE_CAMP, true),
  @Deprecated (forRemoval = true, since = "12.3.0")
  FREEBSD(CFontAwesome5CSS.FA_FREEBSD, true),
  @Deprecated (forRemoval = true, since = "12.3.0")
  FROG(CFontAwesome5CSS.FA_FROG, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  FROWN(CFontAwesome5CSS.FA_FROWN, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  FROWN_OPEN(CFontAwesome5CSS.FA_FROWN_OPEN, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  FULCRUM(CFontAwesome5CSS.FA_FULCRUM, true),
  @Deprecated (forRemoval = true, since = "12.3.0")
  FUNNEL_DOLLAR(CFontAwesome5CSS.FA_FUNNEL_DOLLAR, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  FUTBOL(CFontAwesome5CSS.FA_FUTBOL, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  GALACTIC_REPUBLIC(CFontAwesome5CSS.FA_GALACTIC_REPUBLIC, true),
  @Deprecated (forRemoval = true, since = "12.3.0")
  GALACTIC_SENATE(CFontAwesome5CSS.FA_GALACTIC_SENATE, true),
  @Deprecated (forRemoval = true, since = "12.3.0")
  GAMEPAD(CFontAwesome5CSS.FA_GAMEPAD, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  GAS_PUMP(CFontAwesome5CSS.FA_GAS_PUMP, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  GAVEL(CFontAwesome5CSS.FA_GAVEL, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  GEM(CFontAwesome5CSS.FA_GEM, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  GENDERLESS(CFontAwesome5CSS.FA_GENDERLESS, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  GET_POCKET(CFontAwesome5CSS.FA_GET_POCKET, true),
  @Deprecated (forRemoval = true, since = "12.3.0")
  GG(CFontAwesome5CSS.FA_GG, true),
  @Deprecated (forRemoval = true, since = "12.3.0")
  GG_CIRCLE(CFontAwesome5CSS.FA_GG_CIRCLE, true),
  @Deprecated (forRemoval = true, since = "12.3.0")
  GHOST(CFontAwesome5CSS.FA_GHOST, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  GIFT(CFontAwesome5CSS.FA_GIFT, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  GIFTS(CFontAwesome5CSS.FA_GIFTS, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  GIT(CFontAwesome5CSS.FA_GIT, true),
  @Deprecated (forRemoval = true, since = "12.3.0")
  GIT_ALT(CFontAwesome5CSS.FA_GIT_ALT, true),
  @Deprecated (forRemoval = true, since = "12.3.0")
  GIT_SQUARE(CFontAwesome5CSS.FA_GIT_SQUARE, true),
  @Deprecated (forRemoval = true, since = "12.3.0")
  GITHUB(CFontAwesome5CSS.FA_GITHUB, true),
  @Deprecated (forRemoval = true, since = "12.3.0")
  GITHUB_ALT(CFontAwesome5CSS.FA_GITHUB_ALT, true),
  @Deprecated (forRemoval = true, since = "12.3.0")
  GITHUB_SQUARE(CFontAwesome5CSS.FA_GITHUB_SQUARE, true),
  @Deprecated (forRemoval = true, since = "12.3.0")
  GITKRAKEN(CFontAwesome5CSS.FA_GITKRAKEN, true),
  @Deprecated (forRemoval = true, since = "12.3.0")
  GITLAB(CFontAwesome5CSS.FA_GITLAB, true),
  @Deprecated (forRemoval = true, since = "12.3.0")
  GITTER(CFontAwesome5CSS.FA_GITTER, true),
  @Deprecated (forRemoval = true, since = "12.3.0")
  GLASS_CHEERS(CFontAwesome5CSS.FA_GLASS_CHEERS, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  GLASS_MARTINI(CFontAwesome5CSS.FA_GLASS_MARTINI, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  GLASS_MARTINI_ALT(CFontAwesome5CSS.FA_GLASS_MARTINI_ALT, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  GLASS_WHISKEY(CFontAwesome5CSS.FA_GLASS_WHISKEY, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  GLASSES(CFontAwesome5CSS.FA_GLASSES, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  GLIDE(CFontAwesome5CSS.FA_GLIDE, true),
  @Deprecated (forRemoval = true, since = "12.3.0")
  GLIDE_G(CFontAwesome5CSS.FA_GLIDE_G, true),
  @Deprecated (forRemoval = true, since = "12.3.0")
  GLOBE(CFontAwesome5CSS.FA_GLOBE, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  GLOBE_AFRICA(CFontAwesome5CSS.FA_GLOBE_AFRICA, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  GLOBE_AMERICAS(CFontAwesome5CSS.FA_GLOBE_AMERICAS, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  GLOBE_ASIA(CFontAwesome5CSS.FA_GLOBE_ASIA, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  GLOBE_EUROPE(CFontAwesome5CSS.FA_GLOBE_EUROPE, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  GOFORE(CFontAwesome5CSS.FA_GOFORE, true),
  @Deprecated (forRemoval = true, since = "12.3.0")
  GOLF_BALL(CFontAwesome5CSS.FA_GOLF_BALL, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  GOODREADS(CFontAwesome5CSS.FA_GOODREADS, true),
  @Deprecated (forRemoval = true, since = "12.3.0")
  GOODREADS_G(CFontAwesome5CSS.FA_GOODREADS_G, true),
  @Deprecated (forRemoval = true, since = "12.3.0")
  GOOGLE(CFontAwesome5CSS.FA_GOOGLE, true),
  @Deprecated (forRemoval = true, since = "12.3.0")
  GOOGLE_DRIVE(CFontAwesome5CSS.FA_GOOGLE_DRIVE, true),
  @Deprecated (forRemoval = true, since = "12.3.0")
  GOOGLE_PAY(CFontAwesome5CSS.FA_GOOGLE_PAY, true),
  @Deprecated (forRemoval = true, since = "12.3.0")
  GOOGLE_PLAY(CFontAwesome5CSS.FA_GOOGLE_PLAY, true),
  @Deprecated (forRemoval = true, since = "12.3.0")
  GOOGLE_PLUS(CFontAwesome5CSS.FA_GOOGLE_PLUS, true),
  @Deprecated (forRemoval = true, since = "12.3.0")
  GOOGLE_PLUS_G(CFontAwesome5CSS.FA_GOOGLE_PLUS_G, true),
  @Deprecated (forRemoval = true, since = "12.3.0")
  GOOGLE_PLUS_SQUARE(CFontAwesome5CSS.FA_GOOGLE_PLUS_SQUARE, true),
  @Deprecated (forRemoval = true, since = "12.3.0")
  GOOGLE_WALLET(CFontAwesome5CSS.FA_GOOGLE_WALLET, true),
  @Deprecated (forRemoval = true, since = "12.3.0")
  GOPURAM(CFontAwesome5CSS.FA_GOPURAM, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  GRADUATION_CAP(CFontAwesome5CSS.FA_GRADUATION_CAP, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  GRATIPAY(CFontAwesome5CSS.FA_GRATIPAY, true),
  @Deprecated (forRemoval = true, since = "12.3.0")
  GRAV(CFontAwesome5CSS.FA_GRAV, true),
  @Deprecated (forRemoval = true, since = "12.3.0")
  GREATER_THAN(CFontAwesome5CSS.FA_GREATER_THAN, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  GREATER_THAN_EQUAL(CFontAwesome5CSS.FA_GREATER_THAN_EQUAL, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  GRIMACE(CFontAwesome5CSS.FA_GRIMACE, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  GRIN(CFontAwesome5CSS.FA_GRIN, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  GRIN_ALT(CFontAwesome5CSS.FA_GRIN_ALT, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  GRIN_BEAM(CFontAwesome5CSS.FA_GRIN_BEAM, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  GRIN_BEAM_SWEAT(CFontAwesome5CSS.FA_GRIN_BEAM_SWEAT, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  GRIN_HEARTS(CFontAwesome5CSS.FA_GRIN_HEARTS, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  GRIN_SQUINT(CFontAwesome5CSS.FA_GRIN_SQUINT, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  GRIN_SQUINT_TEARS(CFontAwesome5CSS.FA_GRIN_SQUINT_TEARS, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  GRIN_STARS(CFontAwesome5CSS.FA_GRIN_STARS, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  GRIN_TEARS(CFontAwesome5CSS.FA_GRIN_TEARS, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  GRIN_TONGUE(CFontAwesome5CSS.FA_GRIN_TONGUE, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  GRIN_TONGUE_SQUINT(CFontAwesome5CSS.FA_GRIN_TONGUE_SQUINT, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  GRIN_TONGUE_WINK(CFontAwesome5CSS.FA_GRIN_TONGUE_WINK, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  GRIN_WINK(CFontAwesome5CSS.FA_GRIN_WINK, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  GRIP_HORIZONTAL(CFontAwesome5CSS.FA_GRIP_HORIZONTAL, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  GRIP_LINES(CFontAwesome5CSS.FA_GRIP_LINES, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  GRIP_LINES_VERTICAL(CFontAwesome5CSS.FA_GRIP_LINES_VERTICAL, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  GRIP_VERTICAL(CFontAwesome5CSS.FA_GRIP_VERTICAL, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  GRIPFIRE(CFontAwesome5CSS.FA_GRIPFIRE, true),
  @Deprecated (forRemoval = true, since = "12.3.0")
  GRUNT(CFontAwesome5CSS.FA_GRUNT, true),
  @Deprecated (forRemoval = true, since = "12.3.0")
  GUILDED(CFontAwesome5CSS.FA_GUILDED, true),
  @Deprecated (forRemoval = true, since = "12.3.0")
  GUITAR(CFontAwesome5CSS.FA_GUITAR, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  GULP(CFontAwesome5CSS.FA_GULP, true),
  @Deprecated (forRemoval = true, since = "12.3.0")
  H_SQUARE(CFontAwesome5CSS.FA_H_SQUARE, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  HACKER_NEWS(CFontAwesome5CSS.FA_HACKER_NEWS, true),
  @Deprecated (forRemoval = true, since = "12.3.0")
  HACKER_NEWS_SQUARE(CFontAwesome5CSS.FA_HACKER_NEWS_SQUARE, true),
  @Deprecated (forRemoval = true, since = "12.3.0")
  HACKERRANK(CFontAwesome5CSS.FA_HACKERRANK, true),
  @Deprecated (forRemoval = true, since = "12.3.0")
  HAMBURGER(CFontAwesome5CSS.FA_HAMBURGER, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  HAMMER(CFontAwesome5CSS.FA_HAMMER, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  HAMSA(CFontAwesome5CSS.FA_HAMSA, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  HAND_HOLDING(CFontAwesome5CSS.FA_HAND_HOLDING, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  HAND_HOLDING_HEART(CFontAwesome5CSS.FA_HAND_HOLDING_HEART, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  HAND_HOLDING_MEDICAL(CFontAwesome5CSS.FA_HAND_HOLDING_MEDICAL, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  HAND_HOLDING_USD(CFontAwesome5CSS.FA_HAND_HOLDING_USD, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  HAND_HOLDING_WATER(CFontAwesome5CSS.FA_HAND_HOLDING_WATER, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  HAND_LIZARD(CFontAwesome5CSS.FA_HAND_LIZARD, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  HAND_MIDDLE_FINGER(CFontAwesome5CSS.FA_HAND_MIDDLE_FINGER, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  HAND_PAPER(CFontAwesome5CSS.FA_HAND_PAPER, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  HAND_PEACE(CFontAwesome5CSS.FA_HAND_PEACE, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  HAND_POINT_DOWN(CFontAwesome5CSS.FA_HAND_POINT_DOWN, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  HAND_POINT_LEFT(CFontAwesome5CSS.FA_HAND_POINT_LEFT, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  HAND_POINT_RIGHT(CFontAwesome5CSS.FA_HAND_POINT_RIGHT, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  HAND_POINT_UP(CFontAwesome5CSS.FA_HAND_POINT_UP, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  HAND_POINTER(CFontAwesome5CSS.FA_HAND_POINTER, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  HAND_ROCK(CFontAwesome5CSS.FA_HAND_ROCK, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  HAND_SCISSORS(CFontAwesome5CSS.FA_HAND_SCISSORS, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  HAND_SPARKLES(CFontAwesome5CSS.FA_HAND_SPARKLES, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  HAND_SPOCK(CFontAwesome5CSS.FA_HAND_SPOCK, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  HANDS(CFontAwesome5CSS.FA_HANDS, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  HANDS_HELPING(CFontAwesome5CSS.FA_HANDS_HELPING, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  HANDS_WASH(CFontAwesome5CSS.FA_HANDS_WASH, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  HANDSHAKE(CFontAwesome5CSS.FA_HANDSHAKE, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  HANDSHAKE_ALT_SLASH(CFontAwesome5CSS.FA_HANDSHAKE_ALT_SLASH, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  HANDSHAKE_SLASH(CFontAwesome5CSS.FA_HANDSHAKE_SLASH, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  HANUKIAH(CFontAwesome5CSS.FA_HANUKIAH, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  HARD_HAT(CFontAwesome5CSS.FA_HARD_HAT, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  HASHTAG(CFontAwesome5CSS.FA_HASHTAG, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  HAT_COWBOY(CFontAwesome5CSS.FA_HAT_COWBOY, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  HAT_COWBOY_SIDE(CFontAwesome5CSS.FA_HAT_COWBOY_SIDE, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  HAT_WIZARD(CFontAwesome5CSS.FA_HAT_WIZARD, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  HDD(CFontAwesome5CSS.FA_HDD, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  HEAD_SIDE_COUGH(CFontAwesome5CSS.FA_HEAD_SIDE_COUGH, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  HEAD_SIDE_COUGH_SLASH(CFontAwesome5CSS.FA_HEAD_SIDE_COUGH_SLASH, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  HEAD_SIDE_MASK(CFontAwesome5CSS.FA_HEAD_SIDE_MASK, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  HEAD_SIDE_VIRUS(CFontAwesome5CSS.FA_HEAD_SIDE_VIRUS, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  HEADING(CFontAwesome5CSS.FA_HEADING, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  HEADPHONES(CFontAwesome5CSS.FA_HEADPHONES, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  HEADPHONES_ALT(CFontAwesome5CSS.FA_HEADPHONES_ALT, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  HEADSET(CFontAwesome5CSS.FA_HEADSET, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  HEART(CFontAwesome5CSS.FA_HEART, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  HEART_BROKEN(CFontAwesome5CSS.FA_HEART_BROKEN, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  HEARTBEAT(CFontAwesome5CSS.FA_HEARTBEAT, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  HELICOPTER(CFontAwesome5CSS.FA_HELICOPTER, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  HIGHLIGHTER(CFontAwesome5CSS.FA_HIGHLIGHTER, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  HIKING(CFontAwesome5CSS.FA_HIKING, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  HIPPO(CFontAwesome5CSS.FA_HIPPO, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  HIPS(CFontAwesome5CSS.FA_HIPS, true),
  @Deprecated (forRemoval = true, since = "12.3.0")
  HIRE_A_HELPER(CFontAwesome5CSS.FA_HIRE_A_HELPER, true),
  @Deprecated (forRemoval = true, since = "12.3.0")
  HISTORY(CFontAwesome5CSS.FA_HISTORY, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  HIVE(CFontAwesome5CSS.FA_HIVE, true),
  @Deprecated (forRemoval = true, since = "12.3.0")
  HOCKEY_PUCK(CFontAwesome5CSS.FA_HOCKEY_PUCK, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  HOLLY_BERRY(CFontAwesome5CSS.FA_HOLLY_BERRY, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  HOME(CFontAwesome5CSS.FA_HOME, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  HOOLI(CFontAwesome5CSS.FA_HOOLI, true),
  @Deprecated (forRemoval = true, since = "12.3.0")
  HORNBILL(CFontAwesome5CSS.FA_HORNBILL, true),
  @Deprecated (forRemoval = true, since = "12.3.0")
  HORSE(CFontAwesome5CSS.FA_HORSE, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  HORSE_HEAD(CFontAwesome5CSS.FA_HORSE_HEAD, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  HOSPITAL(CFontAwesome5CSS.FA_HOSPITAL, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  HOSPITAL_ALT(CFontAwesome5CSS.FA_HOSPITAL_ALT, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  HOSPITAL_SYMBOL(CFontAwesome5CSS.FA_HOSPITAL_SYMBOL, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  HOSPITAL_USER(CFontAwesome5CSS.FA_HOSPITAL_USER, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  HOT_TUB(CFontAwesome5CSS.FA_HOT_TUB, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  HOTDOG(CFontAwesome5CSS.FA_HOTDOG, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  HOTEL(CFontAwesome5CSS.FA_HOTEL, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  HOTJAR(CFontAwesome5CSS.FA_HOTJAR, true),
  @Deprecated (forRemoval = true, since = "12.3.0")
  HOURGLASS(CFontAwesome5CSS.FA_HOURGLASS, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  HOURGLASS_END(CFontAwesome5CSS.FA_HOURGLASS_END, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  HOURGLASS_HALF(CFontAwesome5CSS.FA_HOURGLASS_HALF, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  HOURGLASS_START(CFontAwesome5CSS.FA_HOURGLASS_START, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  HOUSE_DAMAGE(CFontAwesome5CSS.FA_HOUSE_DAMAGE, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  HOUSE_USER(CFontAwesome5CSS.FA_HOUSE_USER, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  HOUZZ(CFontAwesome5CSS.FA_HOUZZ, true),
  @Deprecated (forRemoval = true, since = "12.3.0")
  HRYVNIA(CFontAwesome5CSS.FA_HRYVNIA, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  HTML5(CFontAwesome5CSS.FA_HTML5, true),
  @Deprecated (forRemoval = true, since = "12.3.0")
  HUBSPOT(CFontAwesome5CSS.FA_HUBSPOT, true),
  @Deprecated (forRemoval = true, since = "12.3.0")
  I_CURSOR(CFontAwesome5CSS.FA_I_CURSOR, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  ICE_CREAM(CFontAwesome5CSS.FA_ICE_CREAM, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  ICICLES(CFontAwesome5CSS.FA_ICICLES, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  ICONS(CFontAwesome5CSS.FA_ICONS, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  ID_BADGE(CFontAwesome5CSS.FA_ID_BADGE, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  ID_CARD(CFontAwesome5CSS.FA_ID_CARD, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  ID_CARD_ALT(CFontAwesome5CSS.FA_ID_CARD_ALT, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  IDEAL(CFontAwesome5CSS.FA_IDEAL, true),
  @Deprecated (forRemoval = true, since = "12.3.0")
  IGLOO(CFontAwesome5CSS.FA_IGLOO, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  IMAGE(CFontAwesome5CSS.FA_IMAGE, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  IMAGES(CFontAwesome5CSS.FA_IMAGES, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  IMDB(CFontAwesome5CSS.FA_IMDB, true),
  @Deprecated (forRemoval = true, since = "12.3.0")
  INBOX(CFontAwesome5CSS.FA_INBOX, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  INDENT(CFontAwesome5CSS.FA_INDENT, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  INDUSTRY(CFontAwesome5CSS.FA_INDUSTRY, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  INFINITY(CFontAwesome5CSS.FA_INFINITY, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  INFO(CFontAwesome5CSS.FA_INFO, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  INFO_CIRCLE(CFontAwesome5CSS.FA_INFO_CIRCLE, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  INNOSOFT(CFontAwesome5CSS.FA_INNOSOFT, true),
  @Deprecated (forRemoval = true, since = "12.3.0")
  INSTAGRAM(CFontAwesome5CSS.FA_INSTAGRAM, true),
  @Deprecated (forRemoval = true, since = "12.3.0")
  INSTAGRAM_SQUARE(CFontAwesome5CSS.FA_INSTAGRAM_SQUARE, true),
  @Deprecated (forRemoval = true, since = "12.3.0")
  INSTALOD(CFontAwesome5CSS.FA_INSTALOD, true),
  @Deprecated (forRemoval = true, since = "12.3.0")
  INTERCOM(CFontAwesome5CSS.FA_INTERCOM, true),
  @Deprecated (forRemoval = true, since = "12.3.0")
  INTERNET_EXPLORER(CFontAwesome5CSS.FA_INTERNET_EXPLORER, true),
  @Deprecated (forRemoval = true, since = "12.3.0")
  INVISION(CFontAwesome5CSS.FA_INVISION, true),
  @Deprecated (forRemoval = true, since = "12.3.0")
  IOXHOST(CFontAwesome5CSS.FA_IOXHOST, true),
  @Deprecated (forRemoval = true, since = "12.3.0")
  ITALIC(CFontAwesome5CSS.FA_ITALIC, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  ITCH_IO(CFontAwesome5CSS.FA_ITCH_IO, true),
  @Deprecated (forRemoval = true, since = "12.3.0")
  ITUNES(CFontAwesome5CSS.FA_ITUNES, true),
  @Deprecated (forRemoval = true, since = "12.3.0")
  ITUNES_NOTE(CFontAwesome5CSS.FA_ITUNES_NOTE, true),
  @Deprecated (forRemoval = true, since = "12.3.0")
  JAVA(CFontAwesome5CSS.FA_JAVA, true),
  @Deprecated (forRemoval = true, since = "12.3.0")
  JEDI(CFontAwesome5CSS.FA_JEDI, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  JEDI_ORDER(CFontAwesome5CSS.FA_JEDI_ORDER, true),
  @Deprecated (forRemoval = true, since = "12.3.0")
  JENKINS(CFontAwesome5CSS.FA_JENKINS, true),
  @Deprecated (forRemoval = true, since = "12.3.0")
  JIRA(CFontAwesome5CSS.FA_JIRA, true),
  @Deprecated (forRemoval = true, since = "12.3.0")
  JOGET(CFontAwesome5CSS.FA_JOGET, true),
  @Deprecated (forRemoval = true, since = "12.3.0")
  JOINT(CFontAwesome5CSS.FA_JOINT, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  JOOMLA(CFontAwesome5CSS.FA_JOOMLA, true),
  @Deprecated (forRemoval = true, since = "12.3.0")
  JOURNAL_WHILLS(CFontAwesome5CSS.FA_JOURNAL_WHILLS, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  JS(CFontAwesome5CSS.FA_JS, true),
  @Deprecated (forRemoval = true, since = "12.3.0")
  JS_SQUARE(CFontAwesome5CSS.FA_JS_SQUARE, true),
  @Deprecated (forRemoval = true, since = "12.3.0")
  JSFIDDLE(CFontAwesome5CSS.FA_JSFIDDLE, true),
  @Deprecated (forRemoval = true, since = "12.3.0")
  KAABA(CFontAwesome5CSS.FA_KAABA, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  KAGGLE(CFontAwesome5CSS.FA_KAGGLE, true),
  @Deprecated (forRemoval = true, since = "12.3.0")
  KEY(CFontAwesome5CSS.FA_KEY, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  KEYBASE(CFontAwesome5CSS.FA_KEYBASE, true),
  @Deprecated (forRemoval = true, since = "12.3.0")
  KEYBOARD(CFontAwesome5CSS.FA_KEYBOARD, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  KEYCDN(CFontAwesome5CSS.FA_KEYCDN, true),
  @Deprecated (forRemoval = true, since = "12.3.0")
  KHANDA(CFontAwesome5CSS.FA_KHANDA, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  KICKSTARTER(CFontAwesome5CSS.FA_KICKSTARTER, true),
  @Deprecated (forRemoval = true, since = "12.3.0")
  KICKSTARTER_K(CFontAwesome5CSS.FA_KICKSTARTER_K, true),
  @Deprecated (forRemoval = true, since = "12.3.0")
  KISS(CFontAwesome5CSS.FA_KISS, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  KISS_BEAM(CFontAwesome5CSS.FA_KISS_BEAM, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  KISS_WINK_HEART(CFontAwesome5CSS.FA_KISS_WINK_HEART, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  KIWI_BIRD(CFontAwesome5CSS.FA_KIWI_BIRD, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  KORVUE(CFontAwesome5CSS.FA_KORVUE, true),
  @Deprecated (forRemoval = true, since = "12.3.0")
  LANDMARK(CFontAwesome5CSS.FA_LANDMARK, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  LANGUAGE(CFontAwesome5CSS.FA_LANGUAGE, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  LAPTOP(CFontAwesome5CSS.FA_LAPTOP, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  LAPTOP_CODE(CFontAwesome5CSS.FA_LAPTOP_CODE, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  LAPTOP_HOUSE(CFontAwesome5CSS.FA_LAPTOP_HOUSE, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  LAPTOP_MEDICAL(CFontAwesome5CSS.FA_LAPTOP_MEDICAL, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  LARAVEL(CFontAwesome5CSS.FA_LARAVEL, true),
  @Deprecated (forRemoval = true, since = "12.3.0")
  LASTFM(CFontAwesome5CSS.FA_LASTFM, true),
  @Deprecated (forRemoval = true, since = "12.3.0")
  LASTFM_SQUARE(CFontAwesome5CSS.FA_LASTFM_SQUARE, true),
  @Deprecated (forRemoval = true, since = "12.3.0")
  LAUGH(CFontAwesome5CSS.FA_LAUGH, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  LAUGH_BEAM(CFontAwesome5CSS.FA_LAUGH_BEAM, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  LAUGH_SQUINT(CFontAwesome5CSS.FA_LAUGH_SQUINT, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  LAUGH_WINK(CFontAwesome5CSS.FA_LAUGH_WINK, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  LAYER_GROUP(CFontAwesome5CSS.FA_LAYER_GROUP, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  LEAF(CFontAwesome5CSS.FA_LEAF, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  LEANPUB(CFontAwesome5CSS.FA_LEANPUB, true),
  @Deprecated (forRemoval = true, since = "12.3.0")
  LEMON(CFontAwesome5CSS.FA_LEMON, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  LESS(CFontAwesome5CSS.FA_LESS, true),
  @Deprecated (forRemoval = true, since = "12.3.0")
  LESS_THAN(CFontAwesome5CSS.FA_LESS_THAN, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  LESS_THAN_EQUAL(CFontAwesome5CSS.FA_LESS_THAN_EQUAL, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  LEVEL_DOWN_ALT(CFontAwesome5CSS.FA_LEVEL_DOWN_ALT, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  LEVEL_UP_ALT(CFontAwesome5CSS.FA_LEVEL_UP_ALT, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  LIFE_RING(CFontAwesome5CSS.FA_LIFE_RING, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  LIGHTBULB(CFontAwesome5CSS.FA_LIGHTBULB, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  LINE(CFontAwesome5CSS.FA_LINE, true),
  @Deprecated (forRemoval = true, since = "12.3.0")
  LINK(CFontAwesome5CSS.FA_LINK, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  LINKEDIN(CFontAwesome5CSS.FA_LINKEDIN, true),
  @Deprecated (forRemoval = true, since = "12.3.0")
  LINKEDIN_IN(CFontAwesome5CSS.FA_LINKEDIN_IN, true),
  @Deprecated (forRemoval = true, since = "12.3.0")
  LINODE(CFontAwesome5CSS.FA_LINODE, true),
  @Deprecated (forRemoval = true, since = "12.3.0")
  LINUX(CFontAwesome5CSS.FA_LINUX, true),
  @Deprecated (forRemoval = true, since = "12.3.0")
  LIRA_SIGN(CFontAwesome5CSS.FA_LIRA_SIGN, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  LIST(CFontAwesome5CSS.FA_LIST, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  LIST_ALT(CFontAwesome5CSS.FA_LIST_ALT, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  LIST_OL(CFontAwesome5CSS.FA_LIST_OL, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  LIST_UL(CFontAwesome5CSS.FA_LIST_UL, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  LOCATION_ARROW(CFontAwesome5CSS.FA_LOCATION_ARROW, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  LOCK(CFontAwesome5CSS.FA_LOCK, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  LOCK_OPEN(CFontAwesome5CSS.FA_LOCK_OPEN, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  LONG_ARROW_ALT_DOWN(CFontAwesome5CSS.FA_LONG_ARROW_ALT_DOWN, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  LONG_ARROW_ALT_LEFT(CFontAwesome5CSS.FA_LONG_ARROW_ALT_LEFT, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  LONG_ARROW_ALT_RIGHT(CFontAwesome5CSS.FA_LONG_ARROW_ALT_RIGHT, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  LONG_ARROW_ALT_UP(CFontAwesome5CSS.FA_LONG_ARROW_ALT_UP, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  LOW_VISION(CFontAwesome5CSS.FA_LOW_VISION, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  LUGGAGE_CART(CFontAwesome5CSS.FA_LUGGAGE_CART, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  LUNGS(CFontAwesome5CSS.FA_LUNGS, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  LUNGS_VIRUS(CFontAwesome5CSS.FA_LUNGS_VIRUS, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  LYFT(CFontAwesome5CSS.FA_LYFT, true),
  @Deprecated (forRemoval = true, since = "12.3.0")
  MAGENTO(CFontAwesome5CSS.FA_MAGENTO, true),
  @Deprecated (forRemoval = true, since = "12.3.0")
  MAGIC(CFontAwesome5CSS.FA_MAGIC, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  MAGNET(CFontAwesome5CSS.FA_MAGNET, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  MAIL_BULK(CFontAwesome5CSS.FA_MAIL_BULK, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  MAILCHIMP(CFontAwesome5CSS.FA_MAILCHIMP, true),
  @Deprecated (forRemoval = true, since = "12.3.0")
  MALE(CFontAwesome5CSS.FA_MALE, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  MANDALORIAN(CFontAwesome5CSS.FA_MANDALORIAN, true),
  @Deprecated (forRemoval = true, since = "12.3.0")
  MAP(CFontAwesome5CSS.FA_MAP, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  MAP_MARKED(CFontAwesome5CSS.FA_MAP_MARKED, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  MAP_MARKED_ALT(CFontAwesome5CSS.FA_MAP_MARKED_ALT, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  MAP_MARKER(CFontAwesome5CSS.FA_MAP_MARKER, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  MAP_MARKER_ALT(CFontAwesome5CSS.FA_MAP_MARKER_ALT, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  MAP_PIN(CFontAwesome5CSS.FA_MAP_PIN, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  MAP_SIGNS(CFontAwesome5CSS.FA_MAP_SIGNS, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  MARKDOWN(CFontAwesome5CSS.FA_MARKDOWN, true),
  @Deprecated (forRemoval = true, since = "12.3.0")
  MARKER(CFontAwesome5CSS.FA_MARKER, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  MARS(CFontAwesome5CSS.FA_MARS, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  MARS_DOUBLE(CFontAwesome5CSS.FA_MARS_DOUBLE, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  MARS_STROKE(CFontAwesome5CSS.FA_MARS_STROKE, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  MARS_STROKE_H(CFontAwesome5CSS.FA_MARS_STROKE_H, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  MARS_STROKE_V(CFontAwesome5CSS.FA_MARS_STROKE_V, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  MASK(CFontAwesome5CSS.FA_MASK, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  MASTODON(CFontAwesome5CSS.FA_MASTODON, true),
  @Deprecated (forRemoval = true, since = "12.3.0")
  MAXCDN(CFontAwesome5CSS.FA_MAXCDN, true),
  @Deprecated (forRemoval = true, since = "12.3.0")
  MDB(CFontAwesome5CSS.FA_MDB, true),
  @Deprecated (forRemoval = true, since = "12.3.0")
  MEDAL(CFontAwesome5CSS.FA_MEDAL, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  MEDAPPS(CFontAwesome5CSS.FA_MEDAPPS, true),
  @Deprecated (forRemoval = true, since = "12.3.0")
  MEDIUM(CFontAwesome5CSS.FA_MEDIUM, true),
  @Deprecated (forRemoval = true, since = "12.3.0")
  MEDIUM_M(CFontAwesome5CSS.FA_MEDIUM_M, true),
  @Deprecated (forRemoval = true, since = "12.3.0")
  MEDKIT(CFontAwesome5CSS.FA_MEDKIT, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  MEDRT(CFontAwesome5CSS.FA_MEDRT, true),
  @Deprecated (forRemoval = true, since = "12.3.0")
  MEETUP(CFontAwesome5CSS.FA_MEETUP, true),
  @Deprecated (forRemoval = true, since = "12.3.0")
  MEGAPORT(CFontAwesome5CSS.FA_MEGAPORT, true),
  @Deprecated (forRemoval = true, since = "12.3.0")
  MEH(CFontAwesome5CSS.FA_MEH, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  MEH_BLANK(CFontAwesome5CSS.FA_MEH_BLANK, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  MEH_ROLLING_EYES(CFontAwesome5CSS.FA_MEH_ROLLING_EYES, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  MEMORY(CFontAwesome5CSS.FA_MEMORY, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  MENDELEY(CFontAwesome5CSS.FA_MENDELEY, true),
  @Deprecated (forRemoval = true, since = "12.3.0")
  MENORAH(CFontAwesome5CSS.FA_MENORAH, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  MERCURY(CFontAwesome5CSS.FA_MERCURY, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  METEOR(CFontAwesome5CSS.FA_METEOR, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  MICROBLOG(CFontAwesome5CSS.FA_MICROBLOG, true),
  @Deprecated (forRemoval = true, since = "12.3.0")
  MICROCHIP(CFontAwesome5CSS.FA_MICROCHIP, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  MICROPHONE(CFontAwesome5CSS.FA_MICROPHONE, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  MICROPHONE_ALT(CFontAwesome5CSS.FA_MICROPHONE_ALT, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  MICROPHONE_ALT_SLASH(CFontAwesome5CSS.FA_MICROPHONE_ALT_SLASH, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  MICROPHONE_SLASH(CFontAwesome5CSS.FA_MICROPHONE_SLASH, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  MICROSCOPE(CFontAwesome5CSS.FA_MICROSCOPE, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  MICROSOFT(CFontAwesome5CSS.FA_MICROSOFT, true),
  @Deprecated (forRemoval = true, since = "12.3.0")
  MINUS(CFontAwesome5CSS.FA_MINUS, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  MINUS_CIRCLE(CFontAwesome5CSS.FA_MINUS_CIRCLE, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  MINUS_SQUARE(CFontAwesome5CSS.FA_MINUS_SQUARE, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  MITTEN(CFontAwesome5CSS.FA_MITTEN, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  MIX(CFontAwesome5CSS.FA_MIX, true),
  @Deprecated (forRemoval = true, since = "12.3.0")
  MIXCLOUD(CFontAwesome5CSS.FA_MIXCLOUD, true),
  @Deprecated (forRemoval = true, since = "12.3.0")
  MIXER(CFontAwesome5CSS.FA_MIXER, true),
  @Deprecated (forRemoval = true, since = "12.3.0")
  MIZUNI(CFontAwesome5CSS.FA_MIZUNI, true),
  @Deprecated (forRemoval = true, since = "12.3.0")
  MOBILE(CFontAwesome5CSS.FA_MOBILE, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  MOBILE_ALT(CFontAwesome5CSS.FA_MOBILE_ALT, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  MODX(CFontAwesome5CSS.FA_MODX, true),
  @Deprecated (forRemoval = true, since = "12.3.0")
  MONERO(CFontAwesome5CSS.FA_MONERO, true),
  @Deprecated (forRemoval = true, since = "12.3.0")
  MONEY_BILL(CFontAwesome5CSS.FA_MONEY_BILL, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  MONEY_BILL_ALT(CFontAwesome5CSS.FA_MONEY_BILL_ALT, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  MONEY_BILL_WAVE(CFontAwesome5CSS.FA_MONEY_BILL_WAVE, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  MONEY_BILL_WAVE_ALT(CFontAwesome5CSS.FA_MONEY_BILL_WAVE_ALT, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  MONEY_CHECK(CFontAwesome5CSS.FA_MONEY_CHECK, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  MONEY_CHECK_ALT(CFontAwesome5CSS.FA_MONEY_CHECK_ALT, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  MONUMENT(CFontAwesome5CSS.FA_MONUMENT, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  MOON(CFontAwesome5CSS.FA_MOON, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  MORTAR_PESTLE(CFontAwesome5CSS.FA_MORTAR_PESTLE, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  MOSQUE(CFontAwesome5CSS.FA_MOSQUE, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  MOTORCYCLE(CFontAwesome5CSS.FA_MOTORCYCLE, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  MOUNTAIN(CFontAwesome5CSS.FA_MOUNTAIN, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  MOUSE(CFontAwesome5CSS.FA_MOUSE, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  MOUSE_POINTER(CFontAwesome5CSS.FA_MOUSE_POINTER, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  MUG_HOT(CFontAwesome5CSS.FA_MUG_HOT, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  MUSIC(CFontAwesome5CSS.FA_MUSIC, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  NAPSTER(CFontAwesome5CSS.FA_NAPSTER, true),
  @Deprecated (forRemoval = true, since = "12.3.0")
  NEOS(CFontAwesome5CSS.FA_NEOS, true),
  @Deprecated (forRemoval = true, since = "12.3.0")
  NETWORK_WIRED(CFontAwesome5CSS.FA_NETWORK_WIRED, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  NEUTER(CFontAwesome5CSS.FA_NEUTER, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  NEWSPAPER(CFontAwesome5CSS.FA_NEWSPAPER, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  NIMBLR(CFontAwesome5CSS.FA_NIMBLR, true),
  @Deprecated (forRemoval = true, since = "12.3.0")
  NODE(CFontAwesome5CSS.FA_NODE, true),
  @Deprecated (forRemoval = true, since = "12.3.0")
  NODE_JS(CFontAwesome5CSS.FA_NODE_JS, true),
  @Deprecated (forRemoval = true, since = "12.3.0")
  NOT_EQUAL(CFontAwesome5CSS.FA_NOT_EQUAL, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  NOTES_MEDICAL(CFontAwesome5CSS.FA_NOTES_MEDICAL, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  NPM(CFontAwesome5CSS.FA_NPM, true),
  @Deprecated (forRemoval = true, since = "12.3.0")
  NS8(CFontAwesome5CSS.FA_NS8, true),
  @Deprecated (forRemoval = true, since = "12.3.0")
  NUTRITIONIX(CFontAwesome5CSS.FA_NUTRITIONIX, true),
  @Deprecated (forRemoval = true, since = "12.3.0")
  OBJECT_GROUP(CFontAwesome5CSS.FA_OBJECT_GROUP, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  OBJECT_UNGROUP(CFontAwesome5CSS.FA_OBJECT_UNGROUP, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  OCTOPUS_DEPLOY(CFontAwesome5CSS.FA_OCTOPUS_DEPLOY, true),
  @Deprecated (forRemoval = true, since = "12.3.0")
  ODNOKLASSNIKI(CFontAwesome5CSS.FA_ODNOKLASSNIKI, true),
  @Deprecated (forRemoval = true, since = "12.3.0")
  ODNOKLASSNIKI_SQUARE(CFontAwesome5CSS.FA_ODNOKLASSNIKI_SQUARE, true),
  @Deprecated (forRemoval = true, since = "12.3.0")
  OIL_CAN(CFontAwesome5CSS.FA_OIL_CAN, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  OLD_REPUBLIC(CFontAwesome5CSS.FA_OLD_REPUBLIC, true),
  @Deprecated (forRemoval = true, since = "12.3.0")
  OM(CFontAwesome5CSS.FA_OM, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  OPENCART(CFontAwesome5CSS.FA_OPENCART, true),
  @Deprecated (forRemoval = true, since = "12.3.0")
  OPENID(CFontAwesome5CSS.FA_OPENID, true),
  @Deprecated (forRemoval = true, since = "12.3.0")
  OPERA(CFontAwesome5CSS.FA_OPERA, true),
  @Deprecated (forRemoval = true, since = "12.3.0")
  OPTIN_MONSTER(CFontAwesome5CSS.FA_OPTIN_MONSTER, true),
  @Deprecated (forRemoval = true, since = "12.3.0")
  ORCID(CFontAwesome5CSS.FA_ORCID, true),
  @Deprecated (forRemoval = true, since = "12.3.0")
  OSI(CFontAwesome5CSS.FA_OSI, true),
  @Deprecated (forRemoval = true, since = "12.3.0")
  OTTER(CFontAwesome5CSS.FA_OTTER, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  OUTDENT(CFontAwesome5CSS.FA_OUTDENT, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  PAGE4(CFontAwesome5CSS.FA_PAGE4, true),
  @Deprecated (forRemoval = true, since = "12.3.0")
  PAGELINES(CFontAwesome5CSS.FA_PAGELINES, true),
  @Deprecated (forRemoval = true, since = "12.3.0")
  PAGER(CFontAwesome5CSS.FA_PAGER, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  PAINT_BRUSH(CFontAwesome5CSS.FA_PAINT_BRUSH, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  PAINT_ROLLER(CFontAwesome5CSS.FA_PAINT_ROLLER, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  PALETTE(CFontAwesome5CSS.FA_PALETTE, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  PALFED(CFontAwesome5CSS.FA_PALFED, true),
  @Deprecated (forRemoval = true, since = "12.3.0")
  PALLET(CFontAwesome5CSS.FA_PALLET, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  PAPER_PLANE(CFontAwesome5CSS.FA_PAPER_PLANE, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  PAPERCLIP(CFontAwesome5CSS.FA_PAPERCLIP, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  PARACHUTE_BOX(CFontAwesome5CSS.FA_PARACHUTE_BOX, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  PARAGRAPH(CFontAwesome5CSS.FA_PARAGRAPH, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  PARKING(CFontAwesome5CSS.FA_PARKING, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  PASSPORT(CFontAwesome5CSS.FA_PASSPORT, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  PASTAFARIANISM(CFontAwesome5CSS.FA_PASTAFARIANISM, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  PASTE(CFontAwesome5CSS.FA_PASTE, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  PATREON(CFontAwesome5CSS.FA_PATREON, true),
  @Deprecated (forRemoval = true, since = "12.3.0")
  PAUSE(CFontAwesome5CSS.FA_PAUSE, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  PAUSE_CIRCLE(CFontAwesome5CSS.FA_PAUSE_CIRCLE, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  PAW(CFontAwesome5CSS.FA_PAW, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  PAYPAL(CFontAwesome5CSS.FA_PAYPAL, true),
  @Deprecated (forRemoval = true, since = "12.3.0")
  PEACE(CFontAwesome5CSS.FA_PEACE, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  PEN(CFontAwesome5CSS.FA_PEN, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  PEN_ALT(CFontAwesome5CSS.FA_PEN_ALT, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  PEN_FANCY(CFontAwesome5CSS.FA_PEN_FANCY, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  PEN_NIB(CFontAwesome5CSS.FA_PEN_NIB, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  PEN_SQUARE(CFontAwesome5CSS.FA_PEN_SQUARE, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  PENCIL_ALT(CFontAwesome5CSS.FA_PENCIL_ALT, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  PENCIL_RULER(CFontAwesome5CSS.FA_PENCIL_RULER, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  PENNY_ARCADE(CFontAwesome5CSS.FA_PENNY_ARCADE, true),
  @Deprecated (forRemoval = true, since = "12.3.0")
  PEOPLE_ARROWS(CFontAwesome5CSS.FA_PEOPLE_ARROWS, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  PEOPLE_CARRY(CFontAwesome5CSS.FA_PEOPLE_CARRY, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  PEPPER_HOT(CFontAwesome5CSS.FA_PEPPER_HOT, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  PERBYTE(CFontAwesome5CSS.FA_PERBYTE, true),
  @Deprecated (forRemoval = true, since = "12.3.0")
  PERCENT(CFontAwesome5CSS.FA_PERCENT, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  PERCENTAGE(CFontAwesome5CSS.FA_PERCENTAGE, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  PERISCOPE(CFontAwesome5CSS.FA_PERISCOPE, true),
  @Deprecated (forRemoval = true, since = "12.3.0")
  PERSON_BOOTH(CFontAwesome5CSS.FA_PERSON_BOOTH, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  PHABRICATOR(CFontAwesome5CSS.FA_PHABRICATOR, true),
  @Deprecated (forRemoval = true, since = "12.3.0")
  PHOENIX_FRAMEWORK(CFontAwesome5CSS.FA_PHOENIX_FRAMEWORK, true),
  @Deprecated (forRemoval = true, since = "12.3.0")
  PHOENIX_SQUADRON(CFontAwesome5CSS.FA_PHOENIX_SQUADRON, true),
  @Deprecated (forRemoval = true, since = "12.3.0")
  PHONE(CFontAwesome5CSS.FA_PHONE, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  PHONE_ALT(CFontAwesome5CSS.FA_PHONE_ALT, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  PHONE_SLASH(CFontAwesome5CSS.FA_PHONE_SLASH, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  PHONE_SQUARE(CFontAwesome5CSS.FA_PHONE_SQUARE, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  PHONE_SQUARE_ALT(CFontAwesome5CSS.FA_PHONE_SQUARE_ALT, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  PHONE_VOLUME(CFontAwesome5CSS.FA_PHONE_VOLUME, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  PHOTO_VIDEO(CFontAwesome5CSS.FA_PHOTO_VIDEO, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  PHP(CFontAwesome5CSS.FA_PHP, true),
  @Deprecated (forRemoval = true, since = "12.3.0")
  PIED_PIPER(CFontAwesome5CSS.FA_PIED_PIPER, true),
  @Deprecated (forRemoval = true, since = "12.3.0")
  PIED_PIPER_ALT(CFontAwesome5CSS.FA_PIED_PIPER_ALT, true),
  @Deprecated (forRemoval = true, since = "12.3.0")
  PIED_PIPER_HAT(CFontAwesome5CSS.FA_PIED_PIPER_HAT, true),
  @Deprecated (forRemoval = true, since = "12.3.0")
  PIED_PIPER_PP(CFontAwesome5CSS.FA_PIED_PIPER_PP, true),
  @Deprecated (forRemoval = true, since = "12.3.0")
  PIED_PIPER_SQUARE(CFontAwesome5CSS.FA_PIED_PIPER_SQUARE, true),
  @Deprecated (forRemoval = true, since = "12.3.0")
  PIGGY_BANK(CFontAwesome5CSS.FA_PIGGY_BANK, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  PILLS(CFontAwesome5CSS.FA_PILLS, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  PINTEREST(CFontAwesome5CSS.FA_PINTEREST, true),
  @Deprecated (forRemoval = true, since = "12.3.0")
  PINTEREST_P(CFontAwesome5CSS.FA_PINTEREST_P, true),
  @Deprecated (forRemoval = true, since = "12.3.0")
  PINTEREST_SQUARE(CFontAwesome5CSS.FA_PINTEREST_SQUARE, true),
  @Deprecated (forRemoval = true, since = "12.3.0")
  PIZZA_SLICE(CFontAwesome5CSS.FA_PIZZA_SLICE, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  PLACE_OF_WORSHIP(CFontAwesome5CSS.FA_PLACE_OF_WORSHIP, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  PLANE(CFontAwesome5CSS.FA_PLANE, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  PLANE_ARRIVAL(CFontAwesome5CSS.FA_PLANE_ARRIVAL, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  PLANE_DEPARTURE(CFontAwesome5CSS.FA_PLANE_DEPARTURE, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  PLANE_SLASH(CFontAwesome5CSS.FA_PLANE_SLASH, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  PLAY(CFontAwesome5CSS.FA_PLAY, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  PLAY_CIRCLE(CFontAwesome5CSS.FA_PLAY_CIRCLE, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  PLAYSTATION(CFontAwesome5CSS.FA_PLAYSTATION, true),
  @Deprecated (forRemoval = true, since = "12.3.0")
  PLUG(CFontAwesome5CSS.FA_PLUG, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  PLUS(CFontAwesome5CSS.FA_PLUS, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  PLUS_CIRCLE(CFontAwesome5CSS.FA_PLUS_CIRCLE, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  PLUS_SQUARE(CFontAwesome5CSS.FA_PLUS_SQUARE, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  PODCAST(CFontAwesome5CSS.FA_PODCAST, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  POLL(CFontAwesome5CSS.FA_POLL, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  POLL_H(CFontAwesome5CSS.FA_POLL_H, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  POO(CFontAwesome5CSS.FA_POO, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  POO_STORM(CFontAwesome5CSS.FA_POO_STORM, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  POOP(CFontAwesome5CSS.FA_POOP, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  PORTRAIT(CFontAwesome5CSS.FA_PORTRAIT, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  POUND_SIGN(CFontAwesome5CSS.FA_POUND_SIGN, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  POWER_OFF(CFontAwesome5CSS.FA_POWER_OFF, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  PRAY(CFontAwesome5CSS.FA_PRAY, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  PRAYING_HANDS(CFontAwesome5CSS.FA_PRAYING_HANDS, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  PRESCRIPTION(CFontAwesome5CSS.FA_PRESCRIPTION, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  PRESCRIPTION_BOTTLE(CFontAwesome5CSS.FA_PRESCRIPTION_BOTTLE, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  PRESCRIPTION_BOTTLE_ALT(CFontAwesome5CSS.FA_PRESCRIPTION_BOTTLE_ALT, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  PRINT(CFontAwesome5CSS.FA_PRINT, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  PROCEDURES(CFontAwesome5CSS.FA_PROCEDURES, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  PRODUCT_HUNT(CFontAwesome5CSS.FA_PRODUCT_HUNT, true),
  @Deprecated (forRemoval = true, since = "12.3.0")
  PROJECT_DIAGRAM(CFontAwesome5CSS.FA_PROJECT_DIAGRAM, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  PUMP_MEDICAL(CFontAwesome5CSS.FA_PUMP_MEDICAL, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  PUMP_SOAP(CFontAwesome5CSS.FA_PUMP_SOAP, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  PUSHED(CFontAwesome5CSS.FA_PUSHED, true),
  @Deprecated (forRemoval = true, since = "12.3.0")
  PUZZLE_PIECE(CFontAwesome5CSS.FA_PUZZLE_PIECE, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  PYTHON(CFontAwesome5CSS.FA_PYTHON, true),
  @Deprecated (forRemoval = true, since = "12.3.0")
  QQ(CFontAwesome5CSS.FA_QQ, true),
  @Deprecated (forRemoval = true, since = "12.3.0")
  QRCODE(CFontAwesome5CSS.FA_QRCODE, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  QUESTION(CFontAwesome5CSS.FA_QUESTION, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  QUESTION_CIRCLE(CFontAwesome5CSS.FA_QUESTION_CIRCLE, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  QUIDDITCH(CFontAwesome5CSS.FA_QUIDDITCH, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  QUINSCAPE(CFontAwesome5CSS.FA_QUINSCAPE, true),
  @Deprecated (forRemoval = true, since = "12.3.0")
  QUORA(CFontAwesome5CSS.FA_QUORA, true),
  @Deprecated (forRemoval = true, since = "12.3.0")
  QUOTE_LEFT(CFontAwesome5CSS.FA_QUOTE_LEFT, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  QUOTE_RIGHT(CFontAwesome5CSS.FA_QUOTE_RIGHT, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  QURAN(CFontAwesome5CSS.FA_QURAN, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  R_PROJECT(CFontAwesome5CSS.FA_R_PROJECT, true),
  @Deprecated (forRemoval = true, since = "12.3.0")
  RADIATION(CFontAwesome5CSS.FA_RADIATION, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  RADIATION_ALT(CFontAwesome5CSS.FA_RADIATION_ALT, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  RAINBOW(CFontAwesome5CSS.FA_RAINBOW, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  RANDOM(CFontAwesome5CSS.FA_RANDOM, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  RASPBERRY_PI(CFontAwesome5CSS.FA_RASPBERRY_PI, true),
  @Deprecated (forRemoval = true, since = "12.3.0")
  RAVELRY(CFontAwesome5CSS.FA_RAVELRY, true),
  @Deprecated (forRemoval = true, since = "12.3.0")
  REACT(CFontAwesome5CSS.FA_REACT, true),
  @Deprecated (forRemoval = true, since = "12.3.0")
  REACTEUROPE(CFontAwesome5CSS.FA_REACTEUROPE, true),
  @Deprecated (forRemoval = true, since = "12.3.0")
  README(CFontAwesome5CSS.FA_README, true),
  @Deprecated (forRemoval = true, since = "12.3.0")
  REBEL(CFontAwesome5CSS.FA_REBEL, true),
  @Deprecated (forRemoval = true, since = "12.3.0")
  RECEIPT(CFontAwesome5CSS.FA_RECEIPT, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  RECORD_VINYL(CFontAwesome5CSS.FA_RECORD_VINYL, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  RECYCLE(CFontAwesome5CSS.FA_RECYCLE, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  RED_RIVER(CFontAwesome5CSS.FA_RED_RIVER, true),
  @Deprecated (forRemoval = true, since = "12.3.0")
  REDDIT(CFontAwesome5CSS.FA_REDDIT, true),
  @Deprecated (forRemoval = true, since = "12.3.0")
  REDDIT_ALIEN(CFontAwesome5CSS.FA_REDDIT_ALIEN, true),
  @Deprecated (forRemoval = true, since = "12.3.0")
  REDDIT_SQUARE(CFontAwesome5CSS.FA_REDDIT_SQUARE, true),
  @Deprecated (forRemoval = true, since = "12.3.0")
  REDHAT(CFontAwesome5CSS.FA_REDHAT, true),
  @Deprecated (forRemoval = true, since = "12.3.0")
  REDO(CFontAwesome5CSS.FA_REDO, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  REDO_ALT(CFontAwesome5CSS.FA_REDO_ALT, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  REGISTERED(CFontAwesome5CSS.FA_REGISTERED, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  REMOVE_FORMAT(CFontAwesome5CSS.FA_REMOVE_FORMAT, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  RENREN(CFontAwesome5CSS.FA_RENREN, true),
  @Deprecated (forRemoval = true, since = "12.3.0")
  REPLY(CFontAwesome5CSS.FA_REPLY, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  REPLY_ALL(CFontAwesome5CSS.FA_REPLY_ALL, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  REPLYD(CFontAwesome5CSS.FA_REPLYD, true),
  @Deprecated (forRemoval = true, since = "12.3.0")
  REPUBLICAN(CFontAwesome5CSS.FA_REPUBLICAN, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  RESEARCHGATE(CFontAwesome5CSS.FA_RESEARCHGATE, true),
  @Deprecated (forRemoval = true, since = "12.3.0")
  RESOLVING(CFontAwesome5CSS.FA_RESOLVING, true),
  @Deprecated (forRemoval = true, since = "12.3.0")
  RESTROOM(CFontAwesome5CSS.FA_RESTROOM, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  RETWEET(CFontAwesome5CSS.FA_RETWEET, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  REV(CFontAwesome5CSS.FA_REV, true),
  @Deprecated (forRemoval = true, since = "12.3.0")
  RIBBON(CFontAwesome5CSS.FA_RIBBON, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  RING(CFontAwesome5CSS.FA_RING, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  ROAD(CFontAwesome5CSS.FA_ROAD, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  ROBOT(CFontAwesome5CSS.FA_ROBOT, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  ROCKET(CFontAwesome5CSS.FA_ROCKET, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  ROCKETCHAT(CFontAwesome5CSS.FA_ROCKETCHAT, true),
  @Deprecated (forRemoval = true, since = "12.3.0")
  ROCKRMS(CFontAwesome5CSS.FA_ROCKRMS, true),
  @Deprecated (forRemoval = true, since = "12.3.0")
  ROUTE(CFontAwesome5CSS.FA_ROUTE, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  RSS(CFontAwesome5CSS.FA_RSS, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  RSS_SQUARE(CFontAwesome5CSS.FA_RSS_SQUARE, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  RUBLE_SIGN(CFontAwesome5CSS.FA_RUBLE_SIGN, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  RULER(CFontAwesome5CSS.FA_RULER, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  RULER_COMBINED(CFontAwesome5CSS.FA_RULER_COMBINED, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  RULER_HORIZONTAL(CFontAwesome5CSS.FA_RULER_HORIZONTAL, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  RULER_VERTICAL(CFontAwesome5CSS.FA_RULER_VERTICAL, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  RUNNING(CFontAwesome5CSS.FA_RUNNING, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  RUPEE_SIGN(CFontAwesome5CSS.FA_RUPEE_SIGN, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  RUST(CFontAwesome5CSS.FA_RUST, true),
  @Deprecated (forRemoval = true, since = "12.3.0")
  SAD_CRY(CFontAwesome5CSS.FA_SAD_CRY, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  SAD_TEAR(CFontAwesome5CSS.FA_SAD_TEAR, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  SAFARI(CFontAwesome5CSS.FA_SAFARI, true),
  @Deprecated (forRemoval = true, since = "12.3.0")
  SALESFORCE(CFontAwesome5CSS.FA_SALESFORCE, true),
  @Deprecated (forRemoval = true, since = "12.3.0")
  SASS(CFontAwesome5CSS.FA_SASS, true),
  @Deprecated (forRemoval = true, since = "12.3.0")
  SATELLITE(CFontAwesome5CSS.FA_SATELLITE, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  SATELLITE_DISH(CFontAwesome5CSS.FA_SATELLITE_DISH, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  SAVE(CFontAwesome5CSS.FA_SAVE, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  SCHLIX(CFontAwesome5CSS.FA_SCHLIX, true),
  @Deprecated (forRemoval = true, since = "12.3.0")
  SCHOOL(CFontAwesome5CSS.FA_SCHOOL, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  SCREWDRIVER(CFontAwesome5CSS.FA_SCREWDRIVER, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  SCRIBD(CFontAwesome5CSS.FA_SCRIBD, true),
  @Deprecated (forRemoval = true, since = "12.3.0")
  SCROLL(CFontAwesome5CSS.FA_SCROLL, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  SD_CARD(CFontAwesome5CSS.FA_SD_CARD, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  SEARCH(CFontAwesome5CSS.FA_SEARCH, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  SEARCH_DOLLAR(CFontAwesome5CSS.FA_SEARCH_DOLLAR, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  SEARCH_LOCATION(CFontAwesome5CSS.FA_SEARCH_LOCATION, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  SEARCH_MINUS(CFontAwesome5CSS.FA_SEARCH_MINUS, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  SEARCH_PLUS(CFontAwesome5CSS.FA_SEARCH_PLUS, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  SEARCHENGIN(CFontAwesome5CSS.FA_SEARCHENGIN, true),
  @Deprecated (forRemoval = true, since = "12.3.0")
  SEEDLING(CFontAwesome5CSS.FA_SEEDLING, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  SELLCAST(CFontAwesome5CSS.FA_SELLCAST, true),
  @Deprecated (forRemoval = true, since = "12.3.0")
  SELLSY(CFontAwesome5CSS.FA_SELLSY, true),
  @Deprecated (forRemoval = true, since = "12.3.0")
  SERVER(CFontAwesome5CSS.FA_SERVER, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  SERVICESTACK(CFontAwesome5CSS.FA_SERVICESTACK, true),
  @Deprecated (forRemoval = true, since = "12.3.0")
  SHAPES(CFontAwesome5CSS.FA_SHAPES, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  SHARE(CFontAwesome5CSS.FA_SHARE, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  SHARE_ALT(CFontAwesome5CSS.FA_SHARE_ALT, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  SHARE_ALT_SQUARE(CFontAwesome5CSS.FA_SHARE_ALT_SQUARE, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  SHARE_SQUARE(CFontAwesome5CSS.FA_SHARE_SQUARE, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  SHEKEL_SIGN(CFontAwesome5CSS.FA_SHEKEL_SIGN, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  SHIELD_ALT(CFontAwesome5CSS.FA_SHIELD_ALT, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  SHIELD_VIRUS(CFontAwesome5CSS.FA_SHIELD_VIRUS, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  SHIP(CFontAwesome5CSS.FA_SHIP, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  SHIPPING_FAST(CFontAwesome5CSS.FA_SHIPPING_FAST, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  SHIRTSINBULK(CFontAwesome5CSS.FA_SHIRTSINBULK, true),
  @Deprecated (forRemoval = true, since = "12.3.0")
  SHOE_PRINTS(CFontAwesome5CSS.FA_SHOE_PRINTS, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  SHOPIFY(CFontAwesome5CSS.FA_SHOPIFY, true),
  @Deprecated (forRemoval = true, since = "12.3.0")
  SHOPPING_BAG(CFontAwesome5CSS.FA_SHOPPING_BAG, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  SHOPPING_BASKET(CFontAwesome5CSS.FA_SHOPPING_BASKET, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  SHOPPING_CART(CFontAwesome5CSS.FA_SHOPPING_CART, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  SHOPWARE(CFontAwesome5CSS.FA_SHOPWARE, true),
  @Deprecated (forRemoval = true, since = "12.3.0")
  SHOWER(CFontAwesome5CSS.FA_SHOWER, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  SHUTTLE_VAN(CFontAwesome5CSS.FA_SHUTTLE_VAN, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  SIGN(CFontAwesome5CSS.FA_SIGN, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  SIGN_IN_ALT(CFontAwesome5CSS.FA_SIGN_IN_ALT, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  SIGN_LANGUAGE(CFontAwesome5CSS.FA_SIGN_LANGUAGE, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  SIGN_OUT_ALT(CFontAwesome5CSS.FA_SIGN_OUT_ALT, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  SIGNAL(CFontAwesome5CSS.FA_SIGNAL, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  SIGNATURE(CFontAwesome5CSS.FA_SIGNATURE, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  SIM_CARD(CFontAwesome5CSS.FA_SIM_CARD, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  SIMPLYBUILT(CFontAwesome5CSS.FA_SIMPLYBUILT, true),
  @Deprecated (forRemoval = true, since = "12.3.0")
  SINK(CFontAwesome5CSS.FA_SINK, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  SISTRIX(CFontAwesome5CSS.FA_SISTRIX, true),
  @Deprecated (forRemoval = true, since = "12.3.0")
  SITEMAP(CFontAwesome5CSS.FA_SITEMAP, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  SITH(CFontAwesome5CSS.FA_SITH, true),
  @Deprecated (forRemoval = true, since = "12.3.0")
  SKATING(CFontAwesome5CSS.FA_SKATING, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  SKETCH(CFontAwesome5CSS.FA_SKETCH, true),
  @Deprecated (forRemoval = true, since = "12.3.0")
  SKIING(CFontAwesome5CSS.FA_SKIING, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  SKIING_NORDIC(CFontAwesome5CSS.FA_SKIING_NORDIC, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  SKULL(CFontAwesome5CSS.FA_SKULL, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  SKULL_CROSSBONES(CFontAwesome5CSS.FA_SKULL_CROSSBONES, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  SKYATLAS(CFontAwesome5CSS.FA_SKYATLAS, true),
  @Deprecated (forRemoval = true, since = "12.3.0")
  SKYPE(CFontAwesome5CSS.FA_SKYPE, true),
  @Deprecated (forRemoval = true, since = "12.3.0")
  SLACK(CFontAwesome5CSS.FA_SLACK, true),
  @Deprecated (forRemoval = true, since = "12.3.0")
  SLACK_HASH(CFontAwesome5CSS.FA_SLACK_HASH, true),
  @Deprecated (forRemoval = true, since = "12.3.0")
  SLASH(CFontAwesome5CSS.FA_SLASH, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  SLEIGH(CFontAwesome5CSS.FA_SLEIGH, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  SLIDERS_H(CFontAwesome5CSS.FA_SLIDERS_H, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  SLIDESHARE(CFontAwesome5CSS.FA_SLIDESHARE, true),
  @Deprecated (forRemoval = true, since = "12.3.0")
  SMILE(CFontAwesome5CSS.FA_SMILE, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  SMILE_BEAM(CFontAwesome5CSS.FA_SMILE_BEAM, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  SMILE_WINK(CFontAwesome5CSS.FA_SMILE_WINK, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  SMOG(CFontAwesome5CSS.FA_SMOG, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  SMOKING(CFontAwesome5CSS.FA_SMOKING, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  SMOKING_BAN(CFontAwesome5CSS.FA_SMOKING_BAN, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  SMS(CFontAwesome5CSS.FA_SMS, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  SNAPCHAT(CFontAwesome5CSS.FA_SNAPCHAT, true),
  @Deprecated (forRemoval = true, since = "12.3.0")
  SNAPCHAT_GHOST(CFontAwesome5CSS.FA_SNAPCHAT_GHOST, true),
  @Deprecated (forRemoval = true, since = "12.3.0")
  SNAPCHAT_SQUARE(CFontAwesome5CSS.FA_SNAPCHAT_SQUARE, true),
  @Deprecated (forRemoval = true, since = "12.3.0")
  SNOWBOARDING(CFontAwesome5CSS.FA_SNOWBOARDING, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  SNOWFLAKE(CFontAwesome5CSS.FA_SNOWFLAKE, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  SNOWMAN(CFontAwesome5CSS.FA_SNOWMAN, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  SNOWPLOW(CFontAwesome5CSS.FA_SNOWPLOW, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  SOAP(CFontAwesome5CSS.FA_SOAP, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  SOCKS(CFontAwesome5CSS.FA_SOCKS, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  SOLAR_PANEL(CFontAwesome5CSS.FA_SOLAR_PANEL, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  SORT(CFontAwesome5CSS.FA_SORT, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  SORT_ALPHA_DOWN(CFontAwesome5CSS.FA_SORT_ALPHA_DOWN, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  SORT_ALPHA_DOWN_ALT(CFontAwesome5CSS.FA_SORT_ALPHA_DOWN_ALT, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  SORT_ALPHA_UP(CFontAwesome5CSS.FA_SORT_ALPHA_UP, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  SORT_ALPHA_UP_ALT(CFontAwesome5CSS.FA_SORT_ALPHA_UP_ALT, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  SORT_AMOUNT_DOWN(CFontAwesome5CSS.FA_SORT_AMOUNT_DOWN, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  SORT_AMOUNT_DOWN_ALT(CFontAwesome5CSS.FA_SORT_AMOUNT_DOWN_ALT, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  SORT_AMOUNT_UP(CFontAwesome5CSS.FA_SORT_AMOUNT_UP, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  SORT_AMOUNT_UP_ALT(CFontAwesome5CSS.FA_SORT_AMOUNT_UP_ALT, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  SORT_DOWN(CFontAwesome5CSS.FA_SORT_DOWN, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  SORT_NUMERIC_DOWN(CFontAwesome5CSS.FA_SORT_NUMERIC_DOWN, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  SORT_NUMERIC_DOWN_ALT(CFontAwesome5CSS.FA_SORT_NUMERIC_DOWN_ALT, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  SORT_NUMERIC_UP(CFontAwesome5CSS.FA_SORT_NUMERIC_UP, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  SORT_NUMERIC_UP_ALT(CFontAwesome5CSS.FA_SORT_NUMERIC_UP_ALT, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  SORT_UP(CFontAwesome5CSS.FA_SORT_UP, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  SOUNDCLOUD(CFontAwesome5CSS.FA_SOUNDCLOUD, true),
  @Deprecated (forRemoval = true, since = "12.3.0")
  SOURCETREE(CFontAwesome5CSS.FA_SOURCETREE, true),
  @Deprecated (forRemoval = true, since = "12.3.0")
  SPA(CFontAwesome5CSS.FA_SPA, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  SPACE_SHUTTLE(CFontAwesome5CSS.FA_SPACE_SHUTTLE, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  SPEAKAP(CFontAwesome5CSS.FA_SPEAKAP, true),
  @Deprecated (forRemoval = true, since = "12.3.0")
  SPEAKER_DECK(CFontAwesome5CSS.FA_SPEAKER_DECK, true),
  @Deprecated (forRemoval = true, since = "12.3.0")
  SPELL_CHECK(CFontAwesome5CSS.FA_SPELL_CHECK, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  SPIDER(CFontAwesome5CSS.FA_SPIDER, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  SPINNER(CFontAwesome5CSS.FA_SPINNER, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  SPLOTCH(CFontAwesome5CSS.FA_SPLOTCH, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  SPOTIFY(CFontAwesome5CSS.FA_SPOTIFY, true),
  @Deprecated (forRemoval = true, since = "12.3.0")
  SPRAY_CAN(CFontAwesome5CSS.FA_SPRAY_CAN, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  SQUARE(CFontAwesome5CSS.FA_SQUARE, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  SQUARE_FULL(CFontAwesome5CSS.FA_SQUARE_FULL, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  SQUARE_ROOT_ALT(CFontAwesome5CSS.FA_SQUARE_ROOT_ALT, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  SQUARESPACE(CFontAwesome5CSS.FA_SQUARESPACE, true),
  @Deprecated (forRemoval = true, since = "12.3.0")
  STACK_EXCHANGE(CFontAwesome5CSS.FA_STACK_EXCHANGE, true),
  @Deprecated (forRemoval = true, since = "12.3.0")
  STACK_OVERFLOW(CFontAwesome5CSS.FA_STACK_OVERFLOW, true),
  @Deprecated (forRemoval = true, since = "12.3.0")
  STACKPATH(CFontAwesome5CSS.FA_STACKPATH, true),
  @Deprecated (forRemoval = true, since = "12.3.0")
  STAMP(CFontAwesome5CSS.FA_STAMP, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  STAR(CFontAwesome5CSS.FA_STAR, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  STAR_AND_CRESCENT(CFontAwesome5CSS.FA_STAR_AND_CRESCENT, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  STAR_HALF(CFontAwesome5CSS.FA_STAR_HALF, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  STAR_HALF_ALT(CFontAwesome5CSS.FA_STAR_HALF_ALT, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  STAR_OF_DAVID(CFontAwesome5CSS.FA_STAR_OF_DAVID, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  STAR_OF_LIFE(CFontAwesome5CSS.FA_STAR_OF_LIFE, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  STAYLINKED(CFontAwesome5CSS.FA_STAYLINKED, true),
  @Deprecated (forRemoval = true, since = "12.3.0")
  STEAM(CFontAwesome5CSS.FA_STEAM, true),
  @Deprecated (forRemoval = true, since = "12.3.0")
  STEAM_SQUARE(CFontAwesome5CSS.FA_STEAM_SQUARE, true),
  @Deprecated (forRemoval = true, since = "12.3.0")
  STEAM_SYMBOL(CFontAwesome5CSS.FA_STEAM_SYMBOL, true),
  @Deprecated (forRemoval = true, since = "12.3.0")
  STEP_BACKWARD(CFontAwesome5CSS.FA_STEP_BACKWARD, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  STEP_FORWARD(CFontAwesome5CSS.FA_STEP_FORWARD, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  STETHOSCOPE(CFontAwesome5CSS.FA_STETHOSCOPE, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  STICKER_MULE(CFontAwesome5CSS.FA_STICKER_MULE, true),
  @Deprecated (forRemoval = true, since = "12.3.0")
  STICKY_NOTE(CFontAwesome5CSS.FA_STICKY_NOTE, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  STOP(CFontAwesome5CSS.FA_STOP, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  STOP_CIRCLE(CFontAwesome5CSS.FA_STOP_CIRCLE, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  STOPWATCH(CFontAwesome5CSS.FA_STOPWATCH, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  STOPWATCH_20(CFontAwesome5CSS.FA_STOPWATCH_20, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  STORE(CFontAwesome5CSS.FA_STORE, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  STORE_ALT(CFontAwesome5CSS.FA_STORE_ALT, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  STORE_ALT_SLASH(CFontAwesome5CSS.FA_STORE_ALT_SLASH, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  STORE_SLASH(CFontAwesome5CSS.FA_STORE_SLASH, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  STRAVA(CFontAwesome5CSS.FA_STRAVA, true),
  @Deprecated (forRemoval = true, since = "12.3.0")
  STREAM(CFontAwesome5CSS.FA_STREAM, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  STREET_VIEW(CFontAwesome5CSS.FA_STREET_VIEW, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  STRIKETHROUGH(CFontAwesome5CSS.FA_STRIKETHROUGH, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  STRIPE(CFontAwesome5CSS.FA_STRIPE, true),
  @Deprecated (forRemoval = true, since = "12.3.0")
  STRIPE_S(CFontAwesome5CSS.FA_STRIPE_S, true),
  @Deprecated (forRemoval = true, since = "12.3.0")
  STROOPWAFEL(CFontAwesome5CSS.FA_STROOPWAFEL, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  STUDIOVINARI(CFontAwesome5CSS.FA_STUDIOVINARI, true),
  @Deprecated (forRemoval = true, since = "12.3.0")
  STUMBLEUPON(CFontAwesome5CSS.FA_STUMBLEUPON, true),
  @Deprecated (forRemoval = true, since = "12.3.0")
  STUMBLEUPON_CIRCLE(CFontAwesome5CSS.FA_STUMBLEUPON_CIRCLE, true),
  @Deprecated (forRemoval = true, since = "12.3.0")
  SUBSCRIPT(CFontAwesome5CSS.FA_SUBSCRIPT, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  SUBWAY(CFontAwesome5CSS.FA_SUBWAY, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  SUITCASE(CFontAwesome5CSS.FA_SUITCASE, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  SUITCASE_ROLLING(CFontAwesome5CSS.FA_SUITCASE_ROLLING, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  SUN(CFontAwesome5CSS.FA_SUN, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  SUPERPOWERS(CFontAwesome5CSS.FA_SUPERPOWERS, true),
  @Deprecated (forRemoval = true, since = "12.3.0")
  SUPERSCRIPT(CFontAwesome5CSS.FA_SUPERSCRIPT, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  SUPPLE(CFontAwesome5CSS.FA_SUPPLE, true),
  @Deprecated (forRemoval = true, since = "12.3.0")
  SURPRISE(CFontAwesome5CSS.FA_SURPRISE, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  SUSE(CFontAwesome5CSS.FA_SUSE, true),
  @Deprecated (forRemoval = true, since = "12.3.0")
  SWATCHBOOK(CFontAwesome5CSS.FA_SWATCHBOOK, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  SWIFT(CFontAwesome5CSS.FA_SWIFT, true),
  @Deprecated (forRemoval = true, since = "12.3.0")
  SWIMMER(CFontAwesome5CSS.FA_SWIMMER, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  SWIMMING_POOL(CFontAwesome5CSS.FA_SWIMMING_POOL, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  SYMFONY(CFontAwesome5CSS.FA_SYMFONY, true),
  @Deprecated (forRemoval = true, since = "12.3.0")
  SYNAGOGUE(CFontAwesome5CSS.FA_SYNAGOGUE, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  SYNC(CFontAwesome5CSS.FA_SYNC, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  SYNC_ALT(CFontAwesome5CSS.FA_SYNC_ALT, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  SYRINGE(CFontAwesome5CSS.FA_SYRINGE, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  TABLE(CFontAwesome5CSS.FA_TABLE, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  TABLE_TENNIS(CFontAwesome5CSS.FA_TABLE_TENNIS, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  TABLET(CFontAwesome5CSS.FA_TABLET, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  TABLET_ALT(CFontAwesome5CSS.FA_TABLET_ALT, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  TABLETS(CFontAwesome5CSS.FA_TABLETS, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  TACHOMETER_ALT(CFontAwesome5CSS.FA_TACHOMETER_ALT, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  TAG(CFontAwesome5CSS.FA_TAG, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  TAGS(CFontAwesome5CSS.FA_TAGS, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  TAPE(CFontAwesome5CSS.FA_TAPE, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  TASKS(CFontAwesome5CSS.FA_TASKS, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  TAXI(CFontAwesome5CSS.FA_TAXI, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  TEAMSPEAK(CFontAwesome5CSS.FA_TEAMSPEAK, true),
  @Deprecated (forRemoval = true, since = "12.3.0")
  TEETH(CFontAwesome5CSS.FA_TEETH, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  TEETH_OPEN(CFontAwesome5CSS.FA_TEETH_OPEN, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  TELEGRAM(CFontAwesome5CSS.FA_TELEGRAM, true),
  @Deprecated (forRemoval = true, since = "12.3.0")
  TELEGRAM_PLANE(CFontAwesome5CSS.FA_TELEGRAM_PLANE, true),
  @Deprecated (forRemoval = true, since = "12.3.0")
  TEMPERATURE_HIGH(CFontAwesome5CSS.FA_TEMPERATURE_HIGH, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  TEMPERATURE_LOW(CFontAwesome5CSS.FA_TEMPERATURE_LOW, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  TENCENT_WEIBO(CFontAwesome5CSS.FA_TENCENT_WEIBO, true),
  @Deprecated (forRemoval = true, since = "12.3.0")
  TENGE(CFontAwesome5CSS.FA_TENGE, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  TERMINAL(CFontAwesome5CSS.FA_TERMINAL, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  TEXT_HEIGHT(CFontAwesome5CSS.FA_TEXT_HEIGHT, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  TEXT_WIDTH(CFontAwesome5CSS.FA_TEXT_WIDTH, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  TH(CFontAwesome5CSS.FA_TH, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  TH_LARGE(CFontAwesome5CSS.FA_TH_LARGE, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  TH_LIST(CFontAwesome5CSS.FA_TH_LIST, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  THE_RED_YETI(CFontAwesome5CSS.FA_THE_RED_YETI, true),
  @Deprecated (forRemoval = true, since = "12.3.0")
  THEATER_MASKS(CFontAwesome5CSS.FA_THEATER_MASKS, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  THEMECO(CFontAwesome5CSS.FA_THEMECO, true),
  @Deprecated (forRemoval = true, since = "12.3.0")
  THEMEISLE(CFontAwesome5CSS.FA_THEMEISLE, true),
  @Deprecated (forRemoval = true, since = "12.3.0")
  THERMOMETER(CFontAwesome5CSS.FA_THERMOMETER, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  THERMOMETER_EMPTY(CFontAwesome5CSS.FA_THERMOMETER_EMPTY, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  THERMOMETER_FULL(CFontAwesome5CSS.FA_THERMOMETER_FULL, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  THERMOMETER_HALF(CFontAwesome5CSS.FA_THERMOMETER_HALF, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  THERMOMETER_QUARTER(CFontAwesome5CSS.FA_THERMOMETER_QUARTER, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  THERMOMETER_THREE_QUARTERS(CFontAwesome5CSS.FA_THERMOMETER_THREE_QUARTERS, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  THINK_PEAKS(CFontAwesome5CSS.FA_THINK_PEAKS, true),
  @Deprecated (forRemoval = true, since = "12.3.0")
  THUMBS_DOWN(CFontAwesome5CSS.FA_THUMBS_DOWN, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  THUMBS_UP(CFontAwesome5CSS.FA_THUMBS_UP, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  THUMBTACK(CFontAwesome5CSS.FA_THUMBTACK, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  TICKET_ALT(CFontAwesome5CSS.FA_TICKET_ALT, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  TIKTOK(CFontAwesome5CSS.FA_TIKTOK, true),
  @Deprecated (forRemoval = true, since = "12.3.0")
  TIMES(CFontAwesome5CSS.FA_TIMES, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  TIMES_CIRCLE(CFontAwesome5CSS.FA_TIMES_CIRCLE, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  TINT(CFontAwesome5CSS.FA_TINT, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  TINT_SLASH(CFontAwesome5CSS.FA_TINT_SLASH, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  TIRED(CFontAwesome5CSS.FA_TIRED, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  TOGGLE_OFF(CFontAwesome5CSS.FA_TOGGLE_OFF, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  TOGGLE_ON(CFontAwesome5CSS.FA_TOGGLE_ON, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  TOILET(CFontAwesome5CSS.FA_TOILET, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  TOILET_PAPER(CFontAwesome5CSS.FA_TOILET_PAPER, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  TOILET_PAPER_SLASH(CFontAwesome5CSS.FA_TOILET_PAPER_SLASH, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  TOOLBOX(CFontAwesome5CSS.FA_TOOLBOX, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  TOOLS(CFontAwesome5CSS.FA_TOOLS, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  TOOTH(CFontAwesome5CSS.FA_TOOTH, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  TORAH(CFontAwesome5CSS.FA_TORAH, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  TORII_GATE(CFontAwesome5CSS.FA_TORII_GATE, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  TRACTOR(CFontAwesome5CSS.FA_TRACTOR, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  TRADE_FEDERATION(CFontAwesome5CSS.FA_TRADE_FEDERATION, true),
  @Deprecated (forRemoval = true, since = "12.3.0")
  TRADEMARK(CFontAwesome5CSS.FA_TRADEMARK, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  TRAFFIC_LIGHT(CFontAwesome5CSS.FA_TRAFFIC_LIGHT, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  TRAILER(CFontAwesome5CSS.FA_TRAILER, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  TRAIN(CFontAwesome5CSS.FA_TRAIN, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  TRAM(CFontAwesome5CSS.FA_TRAM, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  TRANSGENDER(CFontAwesome5CSS.FA_TRANSGENDER, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  TRANSGENDER_ALT(CFontAwesome5CSS.FA_TRANSGENDER_ALT, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  TRASH(CFontAwesome5CSS.FA_TRASH, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  TRASH_ALT(CFontAwesome5CSS.FA_TRASH_ALT, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  TRASH_RESTORE(CFontAwesome5CSS.FA_TRASH_RESTORE, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  TRASH_RESTORE_ALT(CFontAwesome5CSS.FA_TRASH_RESTORE_ALT, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  TREE(CFontAwesome5CSS.FA_TREE, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  TRELLO(CFontAwesome5CSS.FA_TRELLO, true),
  @Deprecated (forRemoval = true, since = "12.3.0")
  TROPHY(CFontAwesome5CSS.FA_TROPHY, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  TRUCK(CFontAwesome5CSS.FA_TRUCK, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  TRUCK_LOADING(CFontAwesome5CSS.FA_TRUCK_LOADING, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  TRUCK_MONSTER(CFontAwesome5CSS.FA_TRUCK_MONSTER, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  TRUCK_MOVING(CFontAwesome5CSS.FA_TRUCK_MOVING, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  TRUCK_PICKUP(CFontAwesome5CSS.FA_TRUCK_PICKUP, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  TSHIRT(CFontAwesome5CSS.FA_TSHIRT, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  TTY(CFontAwesome5CSS.FA_TTY, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  TUMBLR(CFontAwesome5CSS.FA_TUMBLR, true),
  @Deprecated (forRemoval = true, since = "12.3.0")
  TUMBLR_SQUARE(CFontAwesome5CSS.FA_TUMBLR_SQUARE, true),
  @Deprecated (forRemoval = true, since = "12.3.0")
  TV(CFontAwesome5CSS.FA_TV, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  TWITCH(CFontAwesome5CSS.FA_TWITCH, true),
  @Deprecated (forRemoval = true, since = "12.3.0")
  TWITTER(CFontAwesome5CSS.FA_TWITTER, true),
  @Deprecated (forRemoval = true, since = "12.3.0")
  TWITTER_SQUARE(CFontAwesome5CSS.FA_TWITTER_SQUARE, true),
  @Deprecated (forRemoval = true, since = "12.3.0")
  TYPO3(CFontAwesome5CSS.FA_TYPO3, true),
  @Deprecated (forRemoval = true, since = "12.3.0")
  UBER(CFontAwesome5CSS.FA_UBER, true),
  @Deprecated (forRemoval = true, since = "12.3.0")
  UBUNTU(CFontAwesome5CSS.FA_UBUNTU, true),
  @Deprecated (forRemoval = true, since = "12.3.0")
  UIKIT(CFontAwesome5CSS.FA_UIKIT, true),
  @Deprecated (forRemoval = true, since = "12.3.0")
  UMBRACO(CFontAwesome5CSS.FA_UMBRACO, true),
  @Deprecated (forRemoval = true, since = "12.3.0")
  UMBRELLA(CFontAwesome5CSS.FA_UMBRELLA, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  UMBRELLA_BEACH(CFontAwesome5CSS.FA_UMBRELLA_BEACH, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  UNCHARTED(CFontAwesome5CSS.FA_UNCHARTED, true),
  @Deprecated (forRemoval = true, since = "12.3.0")
  UNDERLINE(CFontAwesome5CSS.FA_UNDERLINE, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  UNDO(CFontAwesome5CSS.FA_UNDO, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  UNDO_ALT(CFontAwesome5CSS.FA_UNDO_ALT, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  UNIREGISTRY(CFontAwesome5CSS.FA_UNIREGISTRY, true),
  @Deprecated (forRemoval = true, since = "12.3.0")
  UNITY(CFontAwesome5CSS.FA_UNITY, true),
  @Deprecated (forRemoval = true, since = "12.3.0")
  UNIVERSAL_ACCESS(CFontAwesome5CSS.FA_UNIVERSAL_ACCESS, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  UNIVERSITY(CFontAwesome5CSS.FA_UNIVERSITY, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  UNLINK(CFontAwesome5CSS.FA_UNLINK, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  UNLOCK(CFontAwesome5CSS.FA_UNLOCK, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  UNLOCK_ALT(CFontAwesome5CSS.FA_UNLOCK_ALT, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  UNSPLASH(CFontAwesome5CSS.FA_UNSPLASH, true),
  @Deprecated (forRemoval = true, since = "12.3.0")
  UNTAPPD(CFontAwesome5CSS.FA_UNTAPPD, true),
  @Deprecated (forRemoval = true, since = "12.3.0")
  UPLOAD(CFontAwesome5CSS.FA_UPLOAD, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  UPS(CFontAwesome5CSS.FA_UPS, true),
  @Deprecated (forRemoval = true, since = "12.3.0")
  USB(CFontAwesome5CSS.FA_USB, true),
  @Deprecated (forRemoval = true, since = "12.3.0")
  USER(CFontAwesome5CSS.FA_USER, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  USER_ALT(CFontAwesome5CSS.FA_USER_ALT, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  USER_ALT_SLASH(CFontAwesome5CSS.FA_USER_ALT_SLASH, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  USER_ASTRONAUT(CFontAwesome5CSS.FA_USER_ASTRONAUT, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  USER_CHECK(CFontAwesome5CSS.FA_USER_CHECK, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  USER_CIRCLE(CFontAwesome5CSS.FA_USER_CIRCLE, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  USER_CLOCK(CFontAwesome5CSS.FA_USER_CLOCK, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  USER_COG(CFontAwesome5CSS.FA_USER_COG, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  USER_EDIT(CFontAwesome5CSS.FA_USER_EDIT, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  USER_FRIENDS(CFontAwesome5CSS.FA_USER_FRIENDS, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  USER_GRADUATE(CFontAwesome5CSS.FA_USER_GRADUATE, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  USER_INJURED(CFontAwesome5CSS.FA_USER_INJURED, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  USER_LOCK(CFontAwesome5CSS.FA_USER_LOCK, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  USER_MD(CFontAwesome5CSS.FA_USER_MD, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  USER_MINUS(CFontAwesome5CSS.FA_USER_MINUS, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  USER_NINJA(CFontAwesome5CSS.FA_USER_NINJA, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  USER_NURSE(CFontAwesome5CSS.FA_USER_NURSE, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  USER_PLUS(CFontAwesome5CSS.FA_USER_PLUS, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  USER_SECRET(CFontAwesome5CSS.FA_USER_SECRET, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  USER_SHIELD(CFontAwesome5CSS.FA_USER_SHIELD, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  USER_SLASH(CFontAwesome5CSS.FA_USER_SLASH, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  USER_TAG(CFontAwesome5CSS.FA_USER_TAG, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  USER_TIE(CFontAwesome5CSS.FA_USER_TIE, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  USER_TIMES(CFontAwesome5CSS.FA_USER_TIMES, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  USERS(CFontAwesome5CSS.FA_USERS, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  USERS_COG(CFontAwesome5CSS.FA_USERS_COG, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  USERS_SLASH(CFontAwesome5CSS.FA_USERS_SLASH, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  USPS(CFontAwesome5CSS.FA_USPS, true),
  @Deprecated (forRemoval = true, since = "12.3.0")
  USSUNNAH(CFontAwesome5CSS.FA_USSUNNAH, true),
  @Deprecated (forRemoval = true, since = "12.3.0")
  UTENSIL_SPOON(CFontAwesome5CSS.FA_UTENSIL_SPOON, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  UTENSILS(CFontAwesome5CSS.FA_UTENSILS, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  VAADIN(CFontAwesome5CSS.FA_VAADIN, true),
  @Deprecated (forRemoval = true, since = "12.3.0")
  VECTOR_SQUARE(CFontAwesome5CSS.FA_VECTOR_SQUARE, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  VENUS(CFontAwesome5CSS.FA_VENUS, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  VENUS_DOUBLE(CFontAwesome5CSS.FA_VENUS_DOUBLE, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  VENUS_MARS(CFontAwesome5CSS.FA_VENUS_MARS, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  VEST(CFontAwesome5CSS.FA_VEST, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  VEST_PATCHES(CFontAwesome5CSS.FA_VEST_PATCHES, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  VIACOIN(CFontAwesome5CSS.FA_VIACOIN, true),
  @Deprecated (forRemoval = true, since = "12.3.0")
  VIADEO(CFontAwesome5CSS.FA_VIADEO, true),
  @Deprecated (forRemoval = true, since = "12.3.0")
  VIADEO_SQUARE(CFontAwesome5CSS.FA_VIADEO_SQUARE, true),
  @Deprecated (forRemoval = true, since = "12.3.0")
  VIAL(CFontAwesome5CSS.FA_VIAL, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  VIALS(CFontAwesome5CSS.FA_VIALS, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  VIBER(CFontAwesome5CSS.FA_VIBER, true),
  @Deprecated (forRemoval = true, since = "12.3.0")
  VIDEO(CFontAwesome5CSS.FA_VIDEO, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  VIDEO_SLASH(CFontAwesome5CSS.FA_VIDEO_SLASH, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  VIHARA(CFontAwesome5CSS.FA_VIHARA, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  VIMEO(CFontAwesome5CSS.FA_VIMEO, true),
  @Deprecated (forRemoval = true, since = "12.3.0")
  VIMEO_SQUARE(CFontAwesome5CSS.FA_VIMEO_SQUARE, true),
  @Deprecated (forRemoval = true, since = "12.3.0")
  VIMEO_V(CFontAwesome5CSS.FA_VIMEO_V, true),
  @Deprecated (forRemoval = true, since = "12.3.0")
  VINE(CFontAwesome5CSS.FA_VINE, true),
  @Deprecated (forRemoval = true, since = "12.3.0")
  VIRUS(CFontAwesome5CSS.FA_VIRUS, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  VIRUS_SLASH(CFontAwesome5CSS.FA_VIRUS_SLASH, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  VIRUSES(CFontAwesome5CSS.FA_VIRUSES, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  VK(CFontAwesome5CSS.FA_VK, true),
  @Deprecated (forRemoval = true, since = "12.3.0")
  VNV(CFontAwesome5CSS.FA_VNV, true),
  @Deprecated (forRemoval = true, since = "12.3.0")
  VOICEMAIL(CFontAwesome5CSS.FA_VOICEMAIL, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  VOLLEYBALL_BALL(CFontAwesome5CSS.FA_VOLLEYBALL_BALL, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  VOLUME_DOWN(CFontAwesome5CSS.FA_VOLUME_DOWN, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  VOLUME_MUTE(CFontAwesome5CSS.FA_VOLUME_MUTE, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  VOLUME_OFF(CFontAwesome5CSS.FA_VOLUME_OFF, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  VOLUME_UP(CFontAwesome5CSS.FA_VOLUME_UP, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  VOTE_YEA(CFontAwesome5CSS.FA_VOTE_YEA, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  VR_CARDBOARD(CFontAwesome5CSS.FA_VR_CARDBOARD, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  VUEJS(CFontAwesome5CSS.FA_VUEJS, true),
  @Deprecated (forRemoval = true, since = "12.3.0")
  WALKING(CFontAwesome5CSS.FA_WALKING, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  WALLET(CFontAwesome5CSS.FA_WALLET, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  WAREHOUSE(CFontAwesome5CSS.FA_WAREHOUSE, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  WATCHMAN_MONITORING(CFontAwesome5CSS.FA_WATCHMAN_MONITORING, true),
  @Deprecated (forRemoval = true, since = "12.3.0")
  WATER(CFontAwesome5CSS.FA_WATER, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  WAVE_SQUARE(CFontAwesome5CSS.FA_WAVE_SQUARE, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  WAZE(CFontAwesome5CSS.FA_WAZE, true),
  @Deprecated (forRemoval = true, since = "12.3.0")
  WEEBLY(CFontAwesome5CSS.FA_WEEBLY, true),
  @Deprecated (forRemoval = true, since = "12.3.0")
  WEIBO(CFontAwesome5CSS.FA_WEIBO, true),
  @Deprecated (forRemoval = true, since = "12.3.0")
  WEIGHT(CFontAwesome5CSS.FA_WEIGHT, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  WEIGHT_HANGING(CFontAwesome5CSS.FA_WEIGHT_HANGING, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  WEIXIN(CFontAwesome5CSS.FA_WEIXIN, true),
  @Deprecated (forRemoval = true, since = "12.3.0")
  WHATSAPP(CFontAwesome5CSS.FA_WHATSAPP, true),
  @Deprecated (forRemoval = true, since = "12.3.0")
  WHATSAPP_SQUARE(CFontAwesome5CSS.FA_WHATSAPP_SQUARE, true),
  @Deprecated (forRemoval = true, since = "12.3.0")
  WHEELCHAIR(CFontAwesome5CSS.FA_WHEELCHAIR, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  WHMCS(CFontAwesome5CSS.FA_WHMCS, true),
  @Deprecated (forRemoval = true, since = "12.3.0")
  WIFI(CFontAwesome5CSS.FA_WIFI, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  WIKIPEDIA_W(CFontAwesome5CSS.FA_WIKIPEDIA_W, true),
  @Deprecated (forRemoval = true, since = "12.3.0")
  WIND(CFontAwesome5CSS.FA_WIND, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  WINDOW_CLOSE(CFontAwesome5CSS.FA_WINDOW_CLOSE, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  WINDOW_MAXIMIZE(CFontAwesome5CSS.FA_WINDOW_MAXIMIZE, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  WINDOW_MINIMIZE(CFontAwesome5CSS.FA_WINDOW_MINIMIZE, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  WINDOW_RESTORE(CFontAwesome5CSS.FA_WINDOW_RESTORE, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  WINDOWS(CFontAwesome5CSS.FA_WINDOWS, true),
  @Deprecated (forRemoval = true, since = "12.3.0")
  WINE_BOTTLE(CFontAwesome5CSS.FA_WINE_BOTTLE, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  WINE_GLASS(CFontAwesome5CSS.FA_WINE_GLASS, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  WINE_GLASS_ALT(CFontAwesome5CSS.FA_WINE_GLASS_ALT, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  WIX(CFontAwesome5CSS.FA_WIX, true),
  @Deprecated (forRemoval = true, since = "12.3.0")
  WIZARDS_OF_THE_COAST(CFontAwesome5CSS.FA_WIZARDS_OF_THE_COAST, true),
  @Deprecated (forRemoval = true, since = "12.3.0")
  WODU(CFontAwesome5CSS.FA_WODU, true),
  @Deprecated (forRemoval = true, since = "12.3.0")
  WOLF_PACK_BATTALION(CFontAwesome5CSS.FA_WOLF_PACK_BATTALION, true),
  @Deprecated (forRemoval = true, since = "12.3.0")
  WON_SIGN(CFontAwesome5CSS.FA_WON_SIGN, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  WORDPRESS(CFontAwesome5CSS.FA_WORDPRESS, true),
  @Deprecated (forRemoval = true, since = "12.3.0")
  WORDPRESS_SIMPLE(CFontAwesome5CSS.FA_WORDPRESS_SIMPLE, true),
  @Deprecated (forRemoval = true, since = "12.3.0")
  WPBEGINNER(CFontAwesome5CSS.FA_WPBEGINNER, true),
  @Deprecated (forRemoval = true, since = "12.3.0")
  WPEXPLORER(CFontAwesome5CSS.FA_WPEXPLORER, true),
  @Deprecated (forRemoval = true, since = "12.3.0")
  WPFORMS(CFontAwesome5CSS.FA_WPFORMS, true),
  @Deprecated (forRemoval = true, since = "12.3.0")
  WPRESSR(CFontAwesome5CSS.FA_WPRESSR, true),
  @Deprecated (forRemoval = true, since = "12.3.0")
  WRENCH(CFontAwesome5CSS.FA_WRENCH, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  X_RAY(CFontAwesome5CSS.FA_X_RAY, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  XBOX(CFontAwesome5CSS.FA_XBOX, true),
  @Deprecated (forRemoval = true, since = "12.3.0")
  XING(CFontAwesome5CSS.FA_XING, true),
  @Deprecated (forRemoval = true, since = "12.3.0")
  XING_SQUARE(CFontAwesome5CSS.FA_XING_SQUARE, true),
  @Deprecated (forRemoval = true, since = "12.3.0")
  Y_COMBINATOR(CFontAwesome5CSS.FA_Y_COMBINATOR, true),
  @Deprecated (forRemoval = true, since = "12.3.0")
  YAHOO(CFontAwesome5CSS.FA_YAHOO, true),
  @Deprecated (forRemoval = true, since = "12.3.0")
  YAMMER(CFontAwesome5CSS.FA_YAMMER, true),
  @Deprecated (forRemoval = true, since = "12.3.0")
  YANDEX(CFontAwesome5CSS.FA_YANDEX, true),
  @Deprecated (forRemoval = true, since = "12.3.0")
  YANDEX_INTERNATIONAL(CFontAwesome5CSS.FA_YANDEX_INTERNATIONAL, true),
  @Deprecated (forRemoval = true, since = "12.3.0")
  YARN(CFontAwesome5CSS.FA_YARN, true),
  @Deprecated (forRemoval = true, since = "12.3.0")
  YELP(CFontAwesome5CSS.FA_YELP, true),
  @Deprecated (forRemoval = true, since = "12.3.0")
  YEN_SIGN(CFontAwesome5CSS.FA_YEN_SIGN, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  YIN_YANG(CFontAwesome5CSS.FA_YIN_YANG, false),
  @Deprecated (forRemoval = true, since = "12.3.0")
  YOAST(CFontAwesome5CSS.FA_YOAST, true),
  @Deprecated (forRemoval = true, since = "12.3.0")
  YOUTUBE(CFontAwesome5CSS.FA_YOUTUBE, true),
  @Deprecated (forRemoval = true, since = "12.3.0")
  YOUTUBE_SQUARE(CFontAwesome5CSS.FA_YOUTUBE_SQUARE, true),
  @Deprecated (forRemoval = true, since = "12.3.0")
  ZHIHU(CFontAwesome5CSS.FA_ZHIHU, true);

  private final ICSSClassProvider m_aCSSClass;
  private final boolean m_bIsBrand;

  EFontAwesome5Icon (@NonNull final ICSSClassProvider aCSSClass, final boolean bIsBrand)
  {
    m_aCSSClass = aCSSClass;
    m_bIsBrand = bIsBrand;
  }

  @Deprecated (forRemoval = true, since = "12.3.0")
  @Nullable
  public String getCSSClass ()
  {
    return m_aCSSClass.getCSSClass ();
  }

  @Deprecated (forRemoval = true, since = "12.3.0")
  public boolean isBrand ()
  {
    return m_bIsBrand;
  }

  @Deprecated (forRemoval = true, since = "12.3.0")
  @NonNull
  public <T extends IHCElement <?>> T applyToNode (@NonNull final T aElement)
  {
    if (m_bIsBrand)
      aElement.addClasses (CFontAwesome5CSS.FAB, m_aCSSClass);
    else
      aElement.addClasses (CFontAwesome5CSS.FA, m_aCSSClass);
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
  public HCI getAsNodeExtraSmall ()
  {
    return getAsNode ().addClass (CFontAwesome5CSS.FA_XS);
  }

  @Deprecated (forRemoval = true, since = "12.3.0")
  @NonNull
  public HCI getAsNodeSmall ()
  {
    return getAsNode ().addClass (CFontAwesome5CSS.FA_SM);
  }

  @Deprecated (forRemoval = true, since = "12.3.0")
  @NonNull
  public HCI getAsNodeLarge ()
  {
    return getAsNode ().addClass (CFontAwesome5CSS.FA_LG);
  }

  @Deprecated (forRemoval = true, since = "12.3.0")
  @NonNull
  public HCI getAsNode2x ()
  {
    return getAsNode ().addClass (CFontAwesome5CSS.FA_2X);
  }

  @Deprecated (forRemoval = true, since = "12.3.0")
  @NonNull
  public HCI getAsNode3x ()
  {
    return getAsNode ().addClass (CFontAwesome5CSS.FA_3X);
  }

  @Deprecated (forRemoval = true, since = "12.3.0")
  @NonNull
  public HCI getAsNode4x ()
  {
    return getAsNode ().addClass (CFontAwesome5CSS.FA_4X);
  }

  @Deprecated (forRemoval = true, since = "12.3.0")
  @NonNull
  public HCI getAsNode5x ()
  {
    return getAsNode ().addClass (CFontAwesome5CSS.FA_5X);
  }

  @Deprecated (forRemoval = true, since = "12.3.0")
  @NonNull
  public HCI getAsNode6x ()
  {
    return getAsNode ().addClass (CFontAwesome5CSS.FA_6X);
  }

  @Deprecated (forRemoval = true, since = "12.3.0")
  @NonNull
  public HCI getAsNode7x ()
  {
    return getAsNode ().addClass (CFontAwesome5CSS.FA_7X);
  }

  @Deprecated (forRemoval = true, since = "12.3.0")
  @NonNull
  public HCI getAsNode8x ()
  {
    return getAsNode ().addClass (CFontAwesome5CSS.FA_8X);
  }

  @Deprecated (forRemoval = true, since = "12.3.0")
  @NonNull
  public HCI getAsNode9x ()
  {
    return getAsNode ().addClass (CFontAwesome5CSS.FA_9X);
  }

  @Deprecated (forRemoval = true, since = "12.3.0")
  @NonNull
  public HCI getAsNode10x ()
  {
    return getAsNode ().addClass (CFontAwesome5CSS.FA_10X);
  }

  @Deprecated (forRemoval = true, since = "12.3.0")
  @NonNull
  public HCI getAsNodeFixedWidth ()
  {
    return getAsNode ().addClass (CFontAwesome5CSS.FA_FW);
  }

  @Deprecated (forRemoval = true, since = "12.3.0")
  @NonNull
  public HCI getAsNodeListBullet ()
  {
    return getAsNode ().addClass (CFontAwesome5CSS.FA_LI);
  }

  @Deprecated (forRemoval = true, since = "12.3.0")
  @NonNull
  public HCI getAsNodeSpinning ()
  {
    return getAsNode ().addClass (CFontAwesome5CSS.FA_SPIN);
  }

  @Deprecated (forRemoval = true, since = "12.3.0")
  @NonNull
  public HCI getAsNodeRotate90 ()
  {
    return getAsNode ().addClass (CFontAwesome5CSS.FA_ROTATE_90);
  }

  @Deprecated (forRemoval = true, since = "12.3.0")
  @NonNull
  public HCI getAsNodeRotate180 ()
  {
    return getAsNode ().addClass (CFontAwesome5CSS.FA_ROTATE_180);
  }

  @Deprecated (forRemoval = true, since = "12.3.0")
  @NonNull
  public HCI getAsNodeRotate270 ()
  {
    return getAsNode ().addClass (CFontAwesome5CSS.FA_ROTATE_270);
  }

  @Deprecated (forRemoval = true, since = "12.3.0")
  @NonNull
  public HCI getAsNodeFlipHorz ()
  {
    return getAsNode ().addClass (CFontAwesome5CSS.FA_FLIP_HORIZONTAL);
  }

  @Deprecated (forRemoval = true, since = "12.3.0")
  @NonNull
  public HCI getAsNodeFlipVert ()
  {
    return getAsNode ().addClass (CFontAwesome5CSS.FA_FLIP_VERTICAL);
  }

  @Deprecated (forRemoval = true, since = "12.3.0")
  @NonNull
  public HCI getAsNodeInverse ()
  {
    return getAsNode ().addClass (CFontAwesome5CSS.FA_INVERSE);
  }

  @Deprecated (forRemoval = true, since = "12.3.0")
  @NonNull
  public static HCSpan createIconStack (@NonNull final IHCElement <?> aLargeIcon,
                                        @NonNull final IHCElement <?> aSmallIcon)
  {
    final HCSpan ret = new HCSpan ().addClasses (CFontAwesome5CSS.FA_STACK, CFontAwesome5CSS.FA_LG);
    ret.addChild (aLargeIcon.addClass (CFontAwesome5CSS.FA_STACK_2X));
    ret.addChild (aSmallIcon.addClass (CFontAwesome5CSS.FA_STACK_1X));
    return ret;
  }

  @Deprecated (forRemoval = true, since = "12.3.0")
  public static void setAsDefault ()
  {
    DefaultIcons.set (EDefaultIcon.ADD, PLUS);
    DefaultIcons.set (EDefaultIcon.BACK, ARROW_LEFT);
    DefaultIcons.set (EDefaultIcon.BACK_TO_LIST, ARROW_LEFT);
    DefaultIcons.set (EDefaultIcon.CANCEL, TIMES);
    DefaultIcons.set (EDefaultIcon.COPY, COPY);
    DefaultIcons.set (EDefaultIcon.DELETE, TRASH);
    DefaultIcons.set (EDefaultIcon.DOWN, ARROW_DOWN);
    DefaultIcons.set (EDefaultIcon.EDIT, PENCIL_ALT);
    DefaultIcons.set (EDefaultIcon.FORWARD, ARROW_RIGHT);
    DefaultIcons.set (EDefaultIcon.HELP, QUESTION);
    DefaultIcons.set (EDefaultIcon.INFO, INFO);
    DefaultIcons.set (EDefaultIcon.KEY, LOCK);
    DefaultIcons.set (EDefaultIcon.MAGNIFIER, BINOCULARS);
    DefaultIcons.set (EDefaultIcon.MINUS, MINUS);
    DefaultIcons.set (EDefaultIcon.NEW, FILE);
    DefaultIcons.set (EDefaultIcon.NEXT, ARROW_RIGHT);
    DefaultIcons.set (EDefaultIcon.NO, TIMES);
    DefaultIcons.set (EDefaultIcon.PLUS, PLUS);
    DefaultIcons.set (EDefaultIcon.REFRESH, SYNC);
    DefaultIcons.set (EDefaultIcon.SAVE, SAVE);
    DefaultIcons.set (EDefaultIcon.SAVE_ALL, SAVE);
    DefaultIcons.set (EDefaultIcon.SAVE_AS, SAVE);
    DefaultIcons.set (EDefaultIcon.SAVE_CLOSE, SAVE);
    DefaultIcons.set (EDefaultIcon.SUBMIT, PAPER_PLANE);
    DefaultIcons.set (EDefaultIcon.UNDELETE, TRASH_RESTORE);
    DefaultIcons.set (EDefaultIcon.UP, ARROW_UP);
    DefaultIcons.set (EDefaultIcon.YES, CHECK);
  }

  @Deprecated (forRemoval = true, since = "12.3.0")
  @NonNull
  public static ICommonsList <ICSSPathProvider> getAllCSSFiles ()
  {
    return new CommonsArrayList <> (EIconCSSPathProvider.FONT_AWESOME5);
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
