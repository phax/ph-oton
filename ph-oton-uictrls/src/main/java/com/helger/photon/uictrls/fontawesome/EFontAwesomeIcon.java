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
package com.helger.photon.uictrls.fontawesome;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.helger.html.css.ICSSClassProvider;
import com.helger.html.hc.html.IHCElement;
import com.helger.html.hc.html.textlevel.HCI;
import com.helger.html.hc.html.textlevel.HCSpan;
import com.helger.photon.uicore.icon.DefaultIcons;
import com.helger.photon.uicore.icon.EDefaultIcon;
import com.helger.photon.uicore.icon.IIcon;

/**
 * Font Awesome icons
 *
 * @author Philip Helger
 */
public enum EFontAwesomeIcon implements IIcon
{
  _500PX (CFontAwesomeCSS.FA_500PX),
  ADJUST (CFontAwesomeCSS.FA_ADJUST),
  ADN (CFontAwesomeCSS.FA_ADN),
  ALIGN_CENTER (CFontAwesomeCSS.FA_ALIGN_CENTER),
  ALIGN_JUSTIFY (CFontAwesomeCSS.FA_ALIGN_JUSTIFY),
  ALIGN_LEFT (CFontAwesomeCSS.FA_ALIGN_LEFT),
  ALIGN_RIGHT (CFontAwesomeCSS.FA_ALIGN_RIGHT),
  AMAZON (CFontAwesomeCSS.FA_AMAZON),
  AMBULANCE (CFontAwesomeCSS.FA_AMBULANCE),
  ANCHOR (CFontAwesomeCSS.FA_ANCHOR),
  ANDROID (CFontAwesomeCSS.FA_ANDROID),
  ANGELLIST (CFontAwesomeCSS.FA_ANGELLIST),
  ANGLE_DOUBLE_DOWN (CFontAwesomeCSS.FA_ANGLE_DOUBLE_DOWN),
  ANGLE_DOUBLE_LEFT (CFontAwesomeCSS.FA_ANGLE_DOUBLE_LEFT),
  ANGLE_DOUBLE_RIGHT (CFontAwesomeCSS.FA_ANGLE_DOUBLE_RIGHT),
  ANGLE_DOUBLE_UP (CFontAwesomeCSS.FA_ANGLE_DOUBLE_UP),
  ANGLE_DOWN (CFontAwesomeCSS.FA_ANGLE_DOWN),
  ANGLE_LEFT (CFontAwesomeCSS.FA_ANGLE_LEFT),
  ANGLE_RIGHT (CFontAwesomeCSS.FA_ANGLE_RIGHT),
  ANGLE_UP (CFontAwesomeCSS.FA_ANGLE_UP),
  APPLE (CFontAwesomeCSS.FA_APPLE),
  ARCHIVE (CFontAwesomeCSS.FA_ARCHIVE),
  AREA_CHART (CFontAwesomeCSS.FA_AREA_CHART),
  ARROW_CIRCLE_DOWN (CFontAwesomeCSS.FA_ARROW_CIRCLE_DOWN),
  ARROW_CIRCLE_LEFT (CFontAwesomeCSS.FA_ARROW_CIRCLE_LEFT),
  ARROW_CIRCLE_O_DOWN (CFontAwesomeCSS.FA_ARROW_CIRCLE_O_DOWN),
  ARROW_CIRCLE_O_LEFT (CFontAwesomeCSS.FA_ARROW_CIRCLE_O_LEFT),
  ARROW_CIRCLE_O_RIGHT (CFontAwesomeCSS.FA_ARROW_CIRCLE_O_RIGHT),
  ARROW_CIRCLE_O_UP (CFontAwesomeCSS.FA_ARROW_CIRCLE_O_UP),
  ARROW_CIRCLE_RIGHT (CFontAwesomeCSS.FA_ARROW_CIRCLE_RIGHT),
  ARROW_CIRCLE_UP (CFontAwesomeCSS.FA_ARROW_CIRCLE_UP),
  ARROW_DOWN (CFontAwesomeCSS.FA_ARROW_DOWN),
  ARROW_LEFT (CFontAwesomeCSS.FA_ARROW_LEFT),
  ARROW_RIGHT (CFontAwesomeCSS.FA_ARROW_RIGHT),
  ARROW_UP (CFontAwesomeCSS.FA_ARROW_UP),
  ARROWS (CFontAwesomeCSS.FA_ARROWS),
  ARROWS_ALT (CFontAwesomeCSS.FA_ARROWS_ALT),
  ARROWS_H (CFontAwesomeCSS.FA_ARROWS_H),
  ARROWS_V (CFontAwesomeCSS.FA_ARROWS_V),
  ASTERISK (CFontAwesomeCSS.FA_ASTERISK),
  AT (CFontAwesomeCSS.FA_AT),
  AUTOMOBILE (CFontAwesomeCSS.FA_AUTOMOBILE),
  BACKWARD (CFontAwesomeCSS.FA_BACKWARD),
  BALANCE_SCALE (CFontAwesomeCSS.FA_BALANCE_SCALE),
  BAN (CFontAwesomeCSS.FA_BAN),
  BANK (CFontAwesomeCSS.FA_BANK),
  BAR_CHART (CFontAwesomeCSS.FA_BAR_CHART),
  BAR_CHART_O (CFontAwesomeCSS.FA_BAR_CHART_O),
  BARCODE (CFontAwesomeCSS.FA_BARCODE),
  BARS (CFontAwesomeCSS.FA_BARS),
  BATTERY_0 (CFontAwesomeCSS.FA_BATTERY_0),
  BATTERY_1 (CFontAwesomeCSS.FA_BATTERY_1),
  BATTERY_2 (CFontAwesomeCSS.FA_BATTERY_2),
  BATTERY_3 (CFontAwesomeCSS.FA_BATTERY_3),
  BATTERY_4 (CFontAwesomeCSS.FA_BATTERY_4),
  BATTERY_EMPTY (CFontAwesomeCSS.FA_BATTERY_EMPTY),
  BATTERY_FULL (CFontAwesomeCSS.FA_BATTERY_FULL),
  BATTERY_HALF (CFontAwesomeCSS.FA_BATTERY_HALF),
  BATTERY_QUARTER (CFontAwesomeCSS.FA_BATTERY_QUARTER),
  BATTERY_THREE_QUARTERS (CFontAwesomeCSS.FA_BATTERY_THREE_QUARTERS),
  BED (CFontAwesomeCSS.FA_BED),
  BEER (CFontAwesomeCSS.FA_BEER),
  BEHANCE (CFontAwesomeCSS.FA_BEHANCE),
  BEHANCE_SQUARE (CFontAwesomeCSS.FA_BEHANCE_SQUARE),
  BELL (CFontAwesomeCSS.FA_BELL),
  BELL_O (CFontAwesomeCSS.FA_BELL_O),
  BELL_SLASH (CFontAwesomeCSS.FA_BELL_SLASH),
  BELL_SLASH_O (CFontAwesomeCSS.FA_BELL_SLASH_O),
  BICYCLE (CFontAwesomeCSS.FA_BICYCLE),
  BINOCULARS (CFontAwesomeCSS.FA_BINOCULARS),
  BIRTHDAY_CAKE (CFontAwesomeCSS.FA_BIRTHDAY_CAKE),
  BITBUCKET (CFontAwesomeCSS.FA_BITBUCKET),
  BITBUCKET_SQUARE (CFontAwesomeCSS.FA_BITBUCKET_SQUARE),
  BITCOIN (CFontAwesomeCSS.FA_BITCOIN),
  BLACK_TIE (CFontAwesomeCSS.FA_BLACK_TIE),
  BLUETOOTH (CFontAwesomeCSS.FA_BLUETOOTH),
  BLUETOOTH_B (CFontAwesomeCSS.FA_BLUETOOTH_B),
  BOLD (CFontAwesomeCSS.FA_BOLD),
  BOLT (CFontAwesomeCSS.FA_BOLT),
  BOMB (CFontAwesomeCSS.FA_BOMB),
  BOOK (CFontAwesomeCSS.FA_BOOK),
  BOOKMARK (CFontAwesomeCSS.FA_BOOKMARK),
  BOOKMARK_O (CFontAwesomeCSS.FA_BOOKMARK_O),
  BRIEFCASE (CFontAwesomeCSS.FA_BRIEFCASE),
  BTC (CFontAwesomeCSS.FA_BTC),
  BUG (CFontAwesomeCSS.FA_BUG),
  BUILDING (CFontAwesomeCSS.FA_BUILDING),
  BUILDING_O (CFontAwesomeCSS.FA_BUILDING_O),
  BULLHORN (CFontAwesomeCSS.FA_BULLHORN),
  BULLSEYE (CFontAwesomeCSS.FA_BULLSEYE),
  BUS (CFontAwesomeCSS.FA_BUS),
  BUYSELLADS (CFontAwesomeCSS.FA_BUYSELLADS),
  CAB (CFontAwesomeCSS.FA_CAB),
  CALCULATOR (CFontAwesomeCSS.FA_CALCULATOR),
  CALENDAR (CFontAwesomeCSS.FA_CALENDAR),
  CALENDAR_CHECK_O (CFontAwesomeCSS.FA_CALENDAR_CHECK_O),
  CALENDAR_MINUS_O (CFontAwesomeCSS.FA_CALENDAR_MINUS_O),
  CALENDAR_O (CFontAwesomeCSS.FA_CALENDAR_O),
  CALENDAR_PLUS_O (CFontAwesomeCSS.FA_CALENDAR_PLUS_O),
  CALENDAR_TIMES_O (CFontAwesomeCSS.FA_CALENDAR_TIMES_O),
  CAMERA (CFontAwesomeCSS.FA_CAMERA),
  CAMERA_RETRO (CFontAwesomeCSS.FA_CAMERA_RETRO),
  CAR (CFontAwesomeCSS.FA_CAR),
  CARET_DOWN (CFontAwesomeCSS.FA_CARET_DOWN),
  CARET_LEFT (CFontAwesomeCSS.FA_CARET_LEFT),
  CARET_RIGHT (CFontAwesomeCSS.FA_CARET_RIGHT),
  CARET_SQUARE_O_DOWN (CFontAwesomeCSS.FA_CARET_SQUARE_O_DOWN),
  CARET_SQUARE_O_LEFT (CFontAwesomeCSS.FA_CARET_SQUARE_O_LEFT),
  CARET_SQUARE_O_RIGHT (CFontAwesomeCSS.FA_CARET_SQUARE_O_RIGHT),
  CARET_SQUARE_O_UP (CFontAwesomeCSS.FA_CARET_SQUARE_O_UP),
  CARET_UP (CFontAwesomeCSS.FA_CARET_UP),
  CART_ARROW_DOWN (CFontAwesomeCSS.FA_CART_ARROW_DOWN),
  CART_PLUS (CFontAwesomeCSS.FA_CART_PLUS),
  CC (CFontAwesomeCSS.FA_CC),
  CC_AMEX (CFontAwesomeCSS.FA_CC_AMEX),
  CC_DINERS_CLUB (CFontAwesomeCSS.FA_CC_DINERS_CLUB),
  CC_DISCOVER (CFontAwesomeCSS.FA_CC_DISCOVER),
  CC_JCB (CFontAwesomeCSS.FA_CC_JCB),
  CC_MASTERCARD (CFontAwesomeCSS.FA_CC_MASTERCARD),
  CC_PAYPAL (CFontAwesomeCSS.FA_CC_PAYPAL),
  CC_STRIPE (CFontAwesomeCSS.FA_CC_STRIPE),
  CC_VISA (CFontAwesomeCSS.FA_CC_VISA),
  CERTIFICATE (CFontAwesomeCSS.FA_CERTIFICATE),
  CHAIN (CFontAwesomeCSS.FA_CHAIN),
  CHAIN_BROKEN (CFontAwesomeCSS.FA_CHAIN_BROKEN),
  CHECK (CFontAwesomeCSS.FA_CHECK),
  CHECK_CIRCLE (CFontAwesomeCSS.FA_CHECK_CIRCLE),
  CHECK_CIRCLE_O (CFontAwesomeCSS.FA_CHECK_CIRCLE_O),
  CHECK_SQUARE (CFontAwesomeCSS.FA_CHECK_SQUARE),
  CHECK_SQUARE_O (CFontAwesomeCSS.FA_CHECK_SQUARE_O),
  CHEVRON_CIRCLE_DOWN (CFontAwesomeCSS.FA_CHEVRON_CIRCLE_DOWN),
  CHEVRON_CIRCLE_LEFT (CFontAwesomeCSS.FA_CHEVRON_CIRCLE_LEFT),
  CHEVRON_CIRCLE_RIGHT (CFontAwesomeCSS.FA_CHEVRON_CIRCLE_RIGHT),
  CHEVRON_CIRCLE_UP (CFontAwesomeCSS.FA_CHEVRON_CIRCLE_UP),
  CHEVRON_DOWN (CFontAwesomeCSS.FA_CHEVRON_DOWN),
  CHEVRON_LEFT (CFontAwesomeCSS.FA_CHEVRON_LEFT),
  CHEVRON_RIGHT (CFontAwesomeCSS.FA_CHEVRON_RIGHT),
  CHEVRON_UP (CFontAwesomeCSS.FA_CHEVRON_UP),
  CHILD (CFontAwesomeCSS.FA_CHILD),
  CHROME (CFontAwesomeCSS.FA_CHROME),
  CIRCLE (CFontAwesomeCSS.FA_CIRCLE),
  CIRCLE_O (CFontAwesomeCSS.FA_CIRCLE_O),
  CIRCLE_O_NOTCH (CFontAwesomeCSS.FA_CIRCLE_O_NOTCH),
  CIRCLE_THIN (CFontAwesomeCSS.FA_CIRCLE_THIN),
  CLIPBOARD (CFontAwesomeCSS.FA_CLIPBOARD),
  CLOCK_O (CFontAwesomeCSS.FA_CLOCK_O),
  CLONE (CFontAwesomeCSS.FA_CLONE),
  CLOSE (CFontAwesomeCSS.FA_CLOSE),
  CLOUD (CFontAwesomeCSS.FA_CLOUD),
  CLOUD_DOWNLOAD (CFontAwesomeCSS.FA_CLOUD_DOWNLOAD),
  CLOUD_UPLOAD (CFontAwesomeCSS.FA_CLOUD_UPLOAD),
  CNY (CFontAwesomeCSS.FA_CNY),
  CODE (CFontAwesomeCSS.FA_CODE),
  CODE_FORK (CFontAwesomeCSS.FA_CODE_FORK),
  CODEPEN (CFontAwesomeCSS.FA_CODEPEN),
  CODIEPIE (CFontAwesomeCSS.FA_CODIEPIE),
  COFFEE (CFontAwesomeCSS.FA_COFFEE),
  COG (CFontAwesomeCSS.FA_COG),
  COGS (CFontAwesomeCSS.FA_COGS),
  COLUMNS (CFontAwesomeCSS.FA_COLUMNS),
  COMMENT (CFontAwesomeCSS.FA_COMMENT),
  COMMENT_O (CFontAwesomeCSS.FA_COMMENT_O),
  COMMENTING (CFontAwesomeCSS.FA_COMMENTING),
  COMMENTING_O (CFontAwesomeCSS.FA_COMMENTING_O),
  COMMENTS (CFontAwesomeCSS.FA_COMMENTS),
  COMMENTS_O (CFontAwesomeCSS.FA_COMMENTS_O),
  COMPASS (CFontAwesomeCSS.FA_COMPASS),
  COMPRESS (CFontAwesomeCSS.FA_COMPRESS),
  CONNECTDEVELOP (CFontAwesomeCSS.FA_CONNECTDEVELOP),
  CONTAO (CFontAwesomeCSS.FA_CONTAO),
  COPY (CFontAwesomeCSS.FA_COPY),
  COPYRIGHT (CFontAwesomeCSS.FA_COPYRIGHT),
  CREATIVE_COMMONS (CFontAwesomeCSS.FA_CREATIVE_COMMONS),
  CREDIT_CARD (CFontAwesomeCSS.FA_CREDIT_CARD),
  CREDIT_CARD_ALT (CFontAwesomeCSS.FA_CREDIT_CARD_ALT),
  CROP (CFontAwesomeCSS.FA_CROP),
  CROSSHAIRS (CFontAwesomeCSS.FA_CROSSHAIRS),
  CSS3 (CFontAwesomeCSS.FA_CSS3),
  CUBE (CFontAwesomeCSS.FA_CUBE),
  CUBES (CFontAwesomeCSS.FA_CUBES),
  CUT (CFontAwesomeCSS.FA_CUT),
  CUTLERY (CFontAwesomeCSS.FA_CUTLERY),
  DASHBOARD (CFontAwesomeCSS.FA_DASHBOARD),
  DASHCUBE (CFontAwesomeCSS.FA_DASHCUBE),
  DATABASE (CFontAwesomeCSS.FA_DATABASE),
  DEDENT (CFontAwesomeCSS.FA_DEDENT),
  DELICIOUS (CFontAwesomeCSS.FA_DELICIOUS),
  DESKTOP (CFontAwesomeCSS.FA_DESKTOP),
  DEVIANTART (CFontAwesomeCSS.FA_DEVIANTART),
  DIAMOND (CFontAwesomeCSS.FA_DIAMOND),
  DIGG (CFontAwesomeCSS.FA_DIGG),
  DOLLAR (CFontAwesomeCSS.FA_DOLLAR),
  DOT_CIRCLE_O (CFontAwesomeCSS.FA_DOT_CIRCLE_O),
  DOWNLOAD (CFontAwesomeCSS.FA_DOWNLOAD),
  DRIBBBLE (CFontAwesomeCSS.FA_DRIBBBLE),
  DROPBOX (CFontAwesomeCSS.FA_DROPBOX),
  DRUPAL (CFontAwesomeCSS.FA_DRUPAL),
  EDGE (CFontAwesomeCSS.FA_EDGE),
  EDIT (CFontAwesomeCSS.FA_EDIT),
  EJECT (CFontAwesomeCSS.FA_EJECT),
  ELLIPSIS_H (CFontAwesomeCSS.FA_ELLIPSIS_H),
  ELLIPSIS_V (CFontAwesomeCSS.FA_ELLIPSIS_V),
  EMPIRE (CFontAwesomeCSS.FA_EMPIRE),
  ENVELOPE (CFontAwesomeCSS.FA_ENVELOPE),
  ENVELOPE_O (CFontAwesomeCSS.FA_ENVELOPE_O),
  ENVELOPE_SQUARE (CFontAwesomeCSS.FA_ENVELOPE_SQUARE),
  ERASER (CFontAwesomeCSS.FA_ERASER),
  EUR (CFontAwesomeCSS.FA_EUR),
  EURO (CFontAwesomeCSS.FA_EURO),
  EXCHANGE (CFontAwesomeCSS.FA_EXCHANGE),
  EXCLAMATION (CFontAwesomeCSS.FA_EXCLAMATION),
  EXCLAMATION_CIRCLE (CFontAwesomeCSS.FA_EXCLAMATION_CIRCLE),
  EXCLAMATION_TRIANGLE (CFontAwesomeCSS.FA_EXCLAMATION_TRIANGLE),
  EXPAND (CFontAwesomeCSS.FA_EXPAND),
  EXPEDITEDSSL (CFontAwesomeCSS.FA_EXPEDITEDSSL),
  EXTERNAL_LINK (CFontAwesomeCSS.FA_EXTERNAL_LINK),
  EXTERNAL_LINK_SQUARE (CFontAwesomeCSS.FA_EXTERNAL_LINK_SQUARE),
  EYE (CFontAwesomeCSS.FA_EYE),
  EYE_SLASH (CFontAwesomeCSS.FA_EYE_SLASH),
  EYEDROPPER (CFontAwesomeCSS.FA_EYEDROPPER),
  FACEBOOK (CFontAwesomeCSS.FA_FACEBOOK),
  FACEBOOK_F (CFontAwesomeCSS.FA_FACEBOOK_F),
  FACEBOOK_OFFICIAL (CFontAwesomeCSS.FA_FACEBOOK_OFFICIAL),
  FACEBOOK_SQUARE (CFontAwesomeCSS.FA_FACEBOOK_SQUARE),
  FAST_BACKWARD (CFontAwesomeCSS.FA_FAST_BACKWARD),
  FAST_FORWARD (CFontAwesomeCSS.FA_FAST_FORWARD),
  FAX (CFontAwesomeCSS.FA_FAX),
  FEED (CFontAwesomeCSS.FA_FEED),
  FEMALE (CFontAwesomeCSS.FA_FEMALE),
  FIGHTER_JET (CFontAwesomeCSS.FA_FIGHTER_JET),
  FILE (CFontAwesomeCSS.FA_FILE),
  FILE_ARCHIVE_O (CFontAwesomeCSS.FA_FILE_ARCHIVE_O),
  FILE_AUDIO_O (CFontAwesomeCSS.FA_FILE_AUDIO_O),
  FILE_CODE_O (CFontAwesomeCSS.FA_FILE_CODE_O),
  FILE_EXCEL_O (CFontAwesomeCSS.FA_FILE_EXCEL_O),
  FILE_IMAGE_O (CFontAwesomeCSS.FA_FILE_IMAGE_O),
  FILE_MOVIE_O (CFontAwesomeCSS.FA_FILE_MOVIE_O),
  FILE_O (CFontAwesomeCSS.FA_FILE_O),
  FILE_PDF_O (CFontAwesomeCSS.FA_FILE_PDF_O),
  FILE_PHOTO_O (CFontAwesomeCSS.FA_FILE_PHOTO_O),
  FILE_PICTURE_O (CFontAwesomeCSS.FA_FILE_PICTURE_O),
  FILE_POWERPOINT_O (CFontAwesomeCSS.FA_FILE_POWERPOINT_O),
  FILE_SOUND_O (CFontAwesomeCSS.FA_FILE_SOUND_O),
  FILE_TEXT (CFontAwesomeCSS.FA_FILE_TEXT),
  FILE_TEXT_O (CFontAwesomeCSS.FA_FILE_TEXT_O),
  FILE_VIDEO_O (CFontAwesomeCSS.FA_FILE_VIDEO_O),
  FILE_WORD_O (CFontAwesomeCSS.FA_FILE_WORD_O),
  FILE_ZIP_O (CFontAwesomeCSS.FA_FILE_ZIP_O),
  FILES_O (CFontAwesomeCSS.FA_FILES_O),
  FILM (CFontAwesomeCSS.FA_FILM),
  FILTER (CFontAwesomeCSS.FA_FILTER),
  FIRE (CFontAwesomeCSS.FA_FIRE),
  FIRE_EXTINGUISHER (CFontAwesomeCSS.FA_FIRE_EXTINGUISHER),
  FIREFOX (CFontAwesomeCSS.FA_FIREFOX),
  FLAG (CFontAwesomeCSS.FA_FLAG),
  FLAG_CHECKERED (CFontAwesomeCSS.FA_FLAG_CHECKERED),
  FLAG_O (CFontAwesomeCSS.FA_FLAG_O),
  FLASH (CFontAwesomeCSS.FA_FLASH),
  FLASK (CFontAwesomeCSS.FA_FLASK),
  FLICKR (CFontAwesomeCSS.FA_FLICKR),
  FLOPPY_O (CFontAwesomeCSS.FA_FLOPPY_O),
  FOLDER (CFontAwesomeCSS.FA_FOLDER),
  FOLDER_O (CFontAwesomeCSS.FA_FOLDER_O),
  FOLDER_OPEN (CFontAwesomeCSS.FA_FOLDER_OPEN),
  FOLDER_OPEN_O (CFontAwesomeCSS.FA_FOLDER_OPEN_O),
  FONT (CFontAwesomeCSS.FA_FONT),
  FONTICONS (CFontAwesomeCSS.FA_FONTICONS),
  FORT_AWESOME (CFontAwesomeCSS.FA_FORT_AWESOME),
  FORUMBEE (CFontAwesomeCSS.FA_FORUMBEE),
  FORWARD (CFontAwesomeCSS.FA_FORWARD),
  FOURSQUARE (CFontAwesomeCSS.FA_FOURSQUARE),
  FROWN_O (CFontAwesomeCSS.FA_FROWN_O),
  FUTBOL_O (CFontAwesomeCSS.FA_FUTBOL_O),
  GAMEPAD (CFontAwesomeCSS.FA_GAMEPAD),
  GAVEL (CFontAwesomeCSS.FA_GAVEL),
  GBP (CFontAwesomeCSS.FA_GBP),
  GE (CFontAwesomeCSS.FA_GE),
  GEAR (CFontAwesomeCSS.FA_GEAR),
  GEARS (CFontAwesomeCSS.FA_GEARS),
  GENDERLESS (CFontAwesomeCSS.FA_GENDERLESS),
  GET_POCKET (CFontAwesomeCSS.FA_GET_POCKET),
  GG (CFontAwesomeCSS.FA_GG),
  GG_CIRCLE (CFontAwesomeCSS.FA_GG_CIRCLE),
  GIFT (CFontAwesomeCSS.FA_GIFT),
  GIT (CFontAwesomeCSS.FA_GIT),
  GIT_SQUARE (CFontAwesomeCSS.FA_GIT_SQUARE),
  GITHUB (CFontAwesomeCSS.FA_GITHUB),
  GITHUB_ALT (CFontAwesomeCSS.FA_GITHUB_ALT),
  GITHUB_SQUARE (CFontAwesomeCSS.FA_GITHUB_SQUARE),
  GITTIP (CFontAwesomeCSS.FA_GITTIP),
  GLASS (CFontAwesomeCSS.FA_GLASS),
  GLOBE (CFontAwesomeCSS.FA_GLOBE),
  GOOGLE (CFontAwesomeCSS.FA_GOOGLE),
  GOOGLE_PLUS (CFontAwesomeCSS.FA_GOOGLE_PLUS),
  GOOGLE_PLUS_SQUARE (CFontAwesomeCSS.FA_GOOGLE_PLUS_SQUARE),
  GOOGLE_WALLET (CFontAwesomeCSS.FA_GOOGLE_WALLET),
  GRADUATION_CAP (CFontAwesomeCSS.FA_GRADUATION_CAP),
  GRATIPAY (CFontAwesomeCSS.FA_GRATIPAY),
  GROUP (CFontAwesomeCSS.FA_GROUP),
  H_SQUARE (CFontAwesomeCSS.FA_H_SQUARE),
  HACKER_NEWS (CFontAwesomeCSS.FA_HACKER_NEWS),
  HAND_GRAB_O (CFontAwesomeCSS.FA_HAND_GRAB_O),
  HAND_LIZARD_O (CFontAwesomeCSS.FA_HAND_LIZARD_O),
  HAND_O_DOWN (CFontAwesomeCSS.FA_HAND_O_DOWN),
  HAND_O_LEFT (CFontAwesomeCSS.FA_HAND_O_LEFT),
  HAND_O_RIGHT (CFontAwesomeCSS.FA_HAND_O_RIGHT),
  HAND_O_UP (CFontAwesomeCSS.FA_HAND_O_UP),
  HAND_PAPER_O (CFontAwesomeCSS.FA_HAND_PAPER_O),
  HAND_PEACE_O (CFontAwesomeCSS.FA_HAND_PEACE_O),
  HAND_POINTER_O (CFontAwesomeCSS.FA_HAND_POINTER_O),
  HAND_ROCK_O (CFontAwesomeCSS.FA_HAND_ROCK_O),
  HAND_SCISSORS_O (CFontAwesomeCSS.FA_HAND_SCISSORS_O),
  HAND_SPOCK_O (CFontAwesomeCSS.FA_HAND_SPOCK_O),
  HAND_STOP_O (CFontAwesomeCSS.FA_HAND_STOP_O),
  HASHTAG (CFontAwesomeCSS.FA_HASHTAG),
  HDD_O (CFontAwesomeCSS.FA_HDD_O),
  HEADER (CFontAwesomeCSS.FA_HEADER),
  HEADPHONES (CFontAwesomeCSS.FA_HEADPHONES),
  HEART (CFontAwesomeCSS.FA_HEART),
  HEART_O (CFontAwesomeCSS.FA_HEART_O),
  HEARTBEAT (CFontAwesomeCSS.FA_HEARTBEAT),
  HISTORY (CFontAwesomeCSS.FA_HISTORY),
  HOME (CFontAwesomeCSS.FA_HOME),
  HOSPITAL_O (CFontAwesomeCSS.FA_HOSPITAL_O),
  HOTEL (CFontAwesomeCSS.FA_HOTEL),
  HOURGLASS (CFontAwesomeCSS.FA_HOURGLASS),
  HOURGLASS_1 (CFontAwesomeCSS.FA_HOURGLASS_1),
  HOURGLASS_2 (CFontAwesomeCSS.FA_HOURGLASS_2),
  HOURGLASS_3 (CFontAwesomeCSS.FA_HOURGLASS_3),
  HOURGLASS_END (CFontAwesomeCSS.FA_HOURGLASS_END),
  HOURGLASS_HALF (CFontAwesomeCSS.FA_HOURGLASS_HALF),
  HOURGLASS_O (CFontAwesomeCSS.FA_HOURGLASS_O),
  HOURGLASS_START (CFontAwesomeCSS.FA_HOURGLASS_START),
  HOUZZ (CFontAwesomeCSS.FA_HOUZZ),
  HTML5 (CFontAwesomeCSS.FA_HTML5),
  I_CURSOR (CFontAwesomeCSS.FA_I_CURSOR),
  ILS (CFontAwesomeCSS.FA_ILS),
  IMAGE (CFontAwesomeCSS.FA_IMAGE),
  INBOX (CFontAwesomeCSS.FA_INBOX),
  INDENT (CFontAwesomeCSS.FA_INDENT),
  INDUSTRY (CFontAwesomeCSS.FA_INDUSTRY),
  INFO (CFontAwesomeCSS.FA_INFO),
  INFO_CIRCLE (CFontAwesomeCSS.FA_INFO_CIRCLE),
  INR (CFontAwesomeCSS.FA_INR),
  INSTAGRAM (CFontAwesomeCSS.FA_INSTAGRAM),
  INSTITUTION (CFontAwesomeCSS.FA_INSTITUTION),
  INTERNET_EXPLORER (CFontAwesomeCSS.FA_INTERNET_EXPLORER),
  INTERSEX (CFontAwesomeCSS.FA_INTERSEX),
  IOXHOST (CFontAwesomeCSS.FA_IOXHOST),
  ITALIC (CFontAwesomeCSS.FA_ITALIC),
  JOOMLA (CFontAwesomeCSS.FA_JOOMLA),
  JPY (CFontAwesomeCSS.FA_JPY),
  JSFIDDLE (CFontAwesomeCSS.FA_JSFIDDLE),
  KEY (CFontAwesomeCSS.FA_KEY),
  KEYBOARD_O (CFontAwesomeCSS.FA_KEYBOARD_O),
  KRW (CFontAwesomeCSS.FA_KRW),
  LANGUAGE (CFontAwesomeCSS.FA_LANGUAGE),
  LAPTOP (CFontAwesomeCSS.FA_LAPTOP),
  LASTFM (CFontAwesomeCSS.FA_LASTFM),
  LASTFM_SQUARE (CFontAwesomeCSS.FA_LASTFM_SQUARE),
  LEAF (CFontAwesomeCSS.FA_LEAF),
  LEANPUB (CFontAwesomeCSS.FA_LEANPUB),
  LEGAL (CFontAwesomeCSS.FA_LEGAL),
  LEMON_O (CFontAwesomeCSS.FA_LEMON_O),
  LEVEL_DOWN (CFontAwesomeCSS.FA_LEVEL_DOWN),
  LEVEL_UP (CFontAwesomeCSS.FA_LEVEL_UP),
  LIFE_BOUY (CFontAwesomeCSS.FA_LIFE_BOUY),
  LIFE_BUOY (CFontAwesomeCSS.FA_LIFE_BUOY),
  LIFE_RING (CFontAwesomeCSS.FA_LIFE_RING),
  LIFE_SAVER (CFontAwesomeCSS.FA_LIFE_SAVER),
  LIGHTBULB_O (CFontAwesomeCSS.FA_LIGHTBULB_O),
  LINE_CHART (CFontAwesomeCSS.FA_LINE_CHART),
  LINK (CFontAwesomeCSS.FA_LINK),
  LINKEDIN (CFontAwesomeCSS.FA_LINKEDIN),
  LINKEDIN_SQUARE (CFontAwesomeCSS.FA_LINKEDIN_SQUARE),
  LINUX (CFontAwesomeCSS.FA_LINUX),
  LIST (CFontAwesomeCSS.FA_LIST),
  LIST_ALT (CFontAwesomeCSS.FA_LIST_ALT),
  LIST_OL (CFontAwesomeCSS.FA_LIST_OL),
  LIST_UL (CFontAwesomeCSS.FA_LIST_UL),
  LOCATION_ARROW (CFontAwesomeCSS.FA_LOCATION_ARROW),
  LOCK (CFontAwesomeCSS.FA_LOCK),
  LONG_ARROW_DOWN (CFontAwesomeCSS.FA_LONG_ARROW_DOWN),
  LONG_ARROW_LEFT (CFontAwesomeCSS.FA_LONG_ARROW_LEFT),
  LONG_ARROW_RIGHT (CFontAwesomeCSS.FA_LONG_ARROW_RIGHT),
  LONG_ARROW_UP (CFontAwesomeCSS.FA_LONG_ARROW_UP),
  MAGIC (CFontAwesomeCSS.FA_MAGIC),
  MAGNET (CFontAwesomeCSS.FA_MAGNET),
  MAIL_FORWARD (CFontAwesomeCSS.FA_MAIL_FORWARD),
  MAIL_REPLY (CFontAwesomeCSS.FA_MAIL_REPLY),
  MAIL_REPLY_ALL (CFontAwesomeCSS.FA_MAIL_REPLY_ALL),
  MALE (CFontAwesomeCSS.FA_MALE),
  MAP (CFontAwesomeCSS.FA_MAP),
  MAP_MARKER (CFontAwesomeCSS.FA_MAP_MARKER),
  MAP_O (CFontAwesomeCSS.FA_MAP_O),
  MAP_PIN (CFontAwesomeCSS.FA_MAP_PIN),
  MAP_SIGNS (CFontAwesomeCSS.FA_MAP_SIGNS),
  MARS (CFontAwesomeCSS.FA_MARS),
  MARS_DOUBLE (CFontAwesomeCSS.FA_MARS_DOUBLE),
  MARS_STROKE (CFontAwesomeCSS.FA_MARS_STROKE),
  MARS_STROKE_H (CFontAwesomeCSS.FA_MARS_STROKE_H),
  MARS_STROKE_V (CFontAwesomeCSS.FA_MARS_STROKE_V),
  MAXCDN (CFontAwesomeCSS.FA_MAXCDN),
  MEANPATH (CFontAwesomeCSS.FA_MEANPATH),
  MEDIUM (CFontAwesomeCSS.FA_MEDIUM),
  MEDKIT (CFontAwesomeCSS.FA_MEDKIT),
  MEH_O (CFontAwesomeCSS.FA_MEH_O),
  MERCURY (CFontAwesomeCSS.FA_MERCURY),
  MICROPHONE (CFontAwesomeCSS.FA_MICROPHONE),
  MICROPHONE_SLASH (CFontAwesomeCSS.FA_MICROPHONE_SLASH),
  MINUS (CFontAwesomeCSS.FA_MINUS),
  MINUS_CIRCLE (CFontAwesomeCSS.FA_MINUS_CIRCLE),
  MINUS_SQUARE (CFontAwesomeCSS.FA_MINUS_SQUARE),
  MINUS_SQUARE_O (CFontAwesomeCSS.FA_MINUS_SQUARE_O),
  MIXCLOUD (CFontAwesomeCSS.FA_MIXCLOUD),
  MOBILE (CFontAwesomeCSS.FA_MOBILE),
  MOBILE_PHONE (CFontAwesomeCSS.FA_MOBILE_PHONE),
  MODX (CFontAwesomeCSS.FA_MODX),
  MONEY (CFontAwesomeCSS.FA_MONEY),
  MOON_O (CFontAwesomeCSS.FA_MOON_O),
  MORTAR_BOARD (CFontAwesomeCSS.FA_MORTAR_BOARD),
  MOTORCYCLE (CFontAwesomeCSS.FA_MOTORCYCLE),
  MOUSE_POINTER (CFontAwesomeCSS.FA_MOUSE_POINTER),
  MUSIC (CFontAwesomeCSS.FA_MUSIC),
  NAVICON (CFontAwesomeCSS.FA_NAVICON),
  NEUTER (CFontAwesomeCSS.FA_NEUTER),
  NEWSPAPER_O (CFontAwesomeCSS.FA_NEWSPAPER_O),
  OBJECT_GROUP (CFontAwesomeCSS.FA_OBJECT_GROUP),
  OBJECT_UNGROUP (CFontAwesomeCSS.FA_OBJECT_UNGROUP),
  ODNOKLASSNIKI (CFontAwesomeCSS.FA_ODNOKLASSNIKI),
  ODNOKLASSNIKI_SQUARE (CFontAwesomeCSS.FA_ODNOKLASSNIKI_SQUARE),
  OPENCART (CFontAwesomeCSS.FA_OPENCART),
  OPENID (CFontAwesomeCSS.FA_OPENID),
  OPERA (CFontAwesomeCSS.FA_OPERA),
  OPTIN_MONSTER (CFontAwesomeCSS.FA_OPTIN_MONSTER),
  OUTDENT (CFontAwesomeCSS.FA_OUTDENT),
  PAGELINES (CFontAwesomeCSS.FA_PAGELINES),
  PAINT_BRUSH (CFontAwesomeCSS.FA_PAINT_BRUSH),
  PAPER_PLANE (CFontAwesomeCSS.FA_PAPER_PLANE),
  PAPER_PLANE_O (CFontAwesomeCSS.FA_PAPER_PLANE_O),
  PAPERCLIP (CFontAwesomeCSS.FA_PAPERCLIP),
  PARAGRAPH (CFontAwesomeCSS.FA_PARAGRAPH),
  PASTE (CFontAwesomeCSS.FA_PASTE),
  PAUSE (CFontAwesomeCSS.FA_PAUSE),
  PAUSE_CIRCLE (CFontAwesomeCSS.FA_PAUSE_CIRCLE),
  PAUSE_CIRCLE_O (CFontAwesomeCSS.FA_PAUSE_CIRCLE_O),
  PAW (CFontAwesomeCSS.FA_PAW),
  PAYPAL (CFontAwesomeCSS.FA_PAYPAL),
  PENCIL (CFontAwesomeCSS.FA_PENCIL),
  PENCIL_SQUARE (CFontAwesomeCSS.FA_PENCIL_SQUARE),
  PENCIL_SQUARE_O (CFontAwesomeCSS.FA_PENCIL_SQUARE_O),
  PERCENT (CFontAwesomeCSS.FA_PERCENT),
  PHONE (CFontAwesomeCSS.FA_PHONE),
  PHONE_SQUARE (CFontAwesomeCSS.FA_PHONE_SQUARE),
  PHOTO (CFontAwesomeCSS.FA_PHOTO),
  PICTURE_O (CFontAwesomeCSS.FA_PICTURE_O),
  PIE_CHART (CFontAwesomeCSS.FA_PIE_CHART),
  PIED_PIPER (CFontAwesomeCSS.FA_PIED_PIPER),
  PIED_PIPER_ALT (CFontAwesomeCSS.FA_PIED_PIPER_ALT),
  PINTEREST (CFontAwesomeCSS.FA_PINTEREST),
  PINTEREST_P (CFontAwesomeCSS.FA_PINTEREST_P),
  PINTEREST_SQUARE (CFontAwesomeCSS.FA_PINTEREST_SQUARE),
  PLANE (CFontAwesomeCSS.FA_PLANE),
  PLAY (CFontAwesomeCSS.FA_PLAY),
  PLAY_CIRCLE (CFontAwesomeCSS.FA_PLAY_CIRCLE),
  PLAY_CIRCLE_O (CFontAwesomeCSS.FA_PLAY_CIRCLE_O),
  PLUG (CFontAwesomeCSS.FA_PLUG),
  PLUS (CFontAwesomeCSS.FA_PLUS),
  PLUS_CIRCLE (CFontAwesomeCSS.FA_PLUS_CIRCLE),
  PLUS_SQUARE (CFontAwesomeCSS.FA_PLUS_SQUARE),
  PLUS_SQUARE_O (CFontAwesomeCSS.FA_PLUS_SQUARE_O),
  POWER_OFF (CFontAwesomeCSS.FA_POWER_OFF),
  PRINT (CFontAwesomeCSS.FA_PRINT),
  PRODUCT_HUNT (CFontAwesomeCSS.FA_PRODUCT_HUNT),
  PUZZLE_PIECE (CFontAwesomeCSS.FA_PUZZLE_PIECE),
  QQ (CFontAwesomeCSS.FA_QQ),
  QRCODE (CFontAwesomeCSS.FA_QRCODE),
  QUESTION (CFontAwesomeCSS.FA_QUESTION),
  QUESTION_CIRCLE (CFontAwesomeCSS.FA_QUESTION_CIRCLE),
  QUOTE_LEFT (CFontAwesomeCSS.FA_QUOTE_LEFT),
  QUOTE_RIGHT (CFontAwesomeCSS.FA_QUOTE_RIGHT),
  RA (CFontAwesomeCSS.FA_RA),
  RANDOM (CFontAwesomeCSS.FA_RANDOM),
  REBEL (CFontAwesomeCSS.FA_REBEL),
  RECYCLE (CFontAwesomeCSS.FA_RECYCLE),
  REDDIT (CFontAwesomeCSS.FA_REDDIT),
  REDDIT_ALIEN (CFontAwesomeCSS.FA_REDDIT_ALIEN),
  REDDIT_SQUARE (CFontAwesomeCSS.FA_REDDIT_SQUARE),
  REFRESH (CFontAwesomeCSS.FA_REFRESH),
  REGISTERED (CFontAwesomeCSS.FA_REGISTERED),
  REMOVE (CFontAwesomeCSS.FA_REMOVE),
  RENREN (CFontAwesomeCSS.FA_RENREN),
  REORDER (CFontAwesomeCSS.FA_REORDER),
  REPEAT (CFontAwesomeCSS.FA_REPEAT),
  REPLY (CFontAwesomeCSS.FA_REPLY),
  REPLY_ALL (CFontAwesomeCSS.FA_REPLY_ALL),
  RETWEET (CFontAwesomeCSS.FA_RETWEET),
  RMB (CFontAwesomeCSS.FA_RMB),
  ROAD (CFontAwesomeCSS.FA_ROAD),
  ROCKET (CFontAwesomeCSS.FA_ROCKET),
  ROTATE_LEFT (CFontAwesomeCSS.FA_ROTATE_LEFT),
  ROTATE_RIGHT (CFontAwesomeCSS.FA_ROTATE_RIGHT),
  ROUBLE (CFontAwesomeCSS.FA_ROUBLE),
  RSS (CFontAwesomeCSS.FA_RSS),
  RSS_SQUARE (CFontAwesomeCSS.FA_RSS_SQUARE),
  RUB (CFontAwesomeCSS.FA_RUB),
  RUBLE (CFontAwesomeCSS.FA_RUBLE),
  RUPEE (CFontAwesomeCSS.FA_RUPEE),
  SAFARI (CFontAwesomeCSS.FA_SAFARI),
  SAVE (CFontAwesomeCSS.FA_SAVE),
  SCISSORS (CFontAwesomeCSS.FA_SCISSORS),
  SCRIBD (CFontAwesomeCSS.FA_SCRIBD),
  SEARCH (CFontAwesomeCSS.FA_SEARCH),
  SEARCH_MINUS (CFontAwesomeCSS.FA_SEARCH_MINUS),
  SEARCH_PLUS (CFontAwesomeCSS.FA_SEARCH_PLUS),
  SELLSY (CFontAwesomeCSS.FA_SELLSY),
  SEND (CFontAwesomeCSS.FA_SEND),
  SEND_O (CFontAwesomeCSS.FA_SEND_O),
  SERVER (CFontAwesomeCSS.FA_SERVER),
  SHARE (CFontAwesomeCSS.FA_SHARE),
  SHARE_ALT (CFontAwesomeCSS.FA_SHARE_ALT),
  SHARE_ALT_SQUARE (CFontAwesomeCSS.FA_SHARE_ALT_SQUARE),
  SHARE_SQUARE (CFontAwesomeCSS.FA_SHARE_SQUARE),
  SHARE_SQUARE_O (CFontAwesomeCSS.FA_SHARE_SQUARE_O),
  SHEKEL (CFontAwesomeCSS.FA_SHEKEL),
  SHEQEL (CFontAwesomeCSS.FA_SHEQEL),
  SHIELD (CFontAwesomeCSS.FA_SHIELD),
  SHIP (CFontAwesomeCSS.FA_SHIP),
  SHIRTSINBULK (CFontAwesomeCSS.FA_SHIRTSINBULK),
  SHOPPING_BAG (CFontAwesomeCSS.FA_SHOPPING_BAG),
  SHOPPING_BASKET (CFontAwesomeCSS.FA_SHOPPING_BASKET),
  SHOPPING_CART (CFontAwesomeCSS.FA_SHOPPING_CART),
  SIGN_IN (CFontAwesomeCSS.FA_SIGN_IN),
  SIGN_OUT (CFontAwesomeCSS.FA_SIGN_OUT),
  SIGNAL (CFontAwesomeCSS.FA_SIGNAL),
  SIMPLYBUILT (CFontAwesomeCSS.FA_SIMPLYBUILT),
  SITEMAP (CFontAwesomeCSS.FA_SITEMAP),
  SKYATLAS (CFontAwesomeCSS.FA_SKYATLAS),
  SKYPE (CFontAwesomeCSS.FA_SKYPE),
  SLACK (CFontAwesomeCSS.FA_SLACK),
  SLIDERS (CFontAwesomeCSS.FA_SLIDERS),
  SLIDESHARE (CFontAwesomeCSS.FA_SLIDESHARE),
  SMILE_O (CFontAwesomeCSS.FA_SMILE_O),
  SOCCER_BALL_O (CFontAwesomeCSS.FA_SOCCER_BALL_O),
  SORT (CFontAwesomeCSS.FA_SORT),
  SORT_ALPHA_ASC (CFontAwesomeCSS.FA_SORT_ALPHA_ASC),
  SORT_ALPHA_DESC (CFontAwesomeCSS.FA_SORT_ALPHA_DESC),
  SORT_AMOUNT_ASC (CFontAwesomeCSS.FA_SORT_AMOUNT_ASC),
  SORT_AMOUNT_DESC (CFontAwesomeCSS.FA_SORT_AMOUNT_DESC),
  SORT_ASC (CFontAwesomeCSS.FA_SORT_ASC),
  SORT_DESC (CFontAwesomeCSS.FA_SORT_DESC),
  SORT_DOWN (CFontAwesomeCSS.FA_SORT_DOWN),
  SORT_NUMERIC_ASC (CFontAwesomeCSS.FA_SORT_NUMERIC_ASC),
  SORT_NUMERIC_DESC (CFontAwesomeCSS.FA_SORT_NUMERIC_DESC),
  SORT_UP (CFontAwesomeCSS.FA_SORT_UP),
  SOUNDCLOUD (CFontAwesomeCSS.FA_SOUNDCLOUD),
  SPACE_SHUTTLE (CFontAwesomeCSS.FA_SPACE_SHUTTLE),
  SPINNER (CFontAwesomeCSS.FA_SPINNER),
  SPOON (CFontAwesomeCSS.FA_SPOON),
  SPOTIFY (CFontAwesomeCSS.FA_SPOTIFY),
  SQUARE (CFontAwesomeCSS.FA_SQUARE),
  SQUARE_O (CFontAwesomeCSS.FA_SQUARE_O),
  STACK_EXCHANGE (CFontAwesomeCSS.FA_STACK_EXCHANGE),
  STACK_OVERFLOW (CFontAwesomeCSS.FA_STACK_OVERFLOW),
  STAR (CFontAwesomeCSS.FA_STAR),
  STAR_HALF (CFontAwesomeCSS.FA_STAR_HALF),
  STAR_HALF_EMPTY (CFontAwesomeCSS.FA_STAR_HALF_EMPTY),
  STAR_HALF_FULL (CFontAwesomeCSS.FA_STAR_HALF_FULL),
  STAR_HALF_O (CFontAwesomeCSS.FA_STAR_HALF_O),
  STAR_O (CFontAwesomeCSS.FA_STAR_O),
  STEAM (CFontAwesomeCSS.FA_STEAM),
  STEAM_SQUARE (CFontAwesomeCSS.FA_STEAM_SQUARE),
  STEP_BACKWARD (CFontAwesomeCSS.FA_STEP_BACKWARD),
  STEP_FORWARD (CFontAwesomeCSS.FA_STEP_FORWARD),
  STETHOSCOPE (CFontAwesomeCSS.FA_STETHOSCOPE),
  STICKY_NOTE (CFontAwesomeCSS.FA_STICKY_NOTE),
  STICKY_NOTE_O (CFontAwesomeCSS.FA_STICKY_NOTE_O),
  STOP (CFontAwesomeCSS.FA_STOP),
  STOP_CIRCLE (CFontAwesomeCSS.FA_STOP_CIRCLE),
  STOP_CIRCLE_O (CFontAwesomeCSS.FA_STOP_CIRCLE_O),
  STREET_VIEW (CFontAwesomeCSS.FA_STREET_VIEW),
  STRIKETHROUGH (CFontAwesomeCSS.FA_STRIKETHROUGH),
  STUMBLEUPON (CFontAwesomeCSS.FA_STUMBLEUPON),
  STUMBLEUPON_CIRCLE (CFontAwesomeCSS.FA_STUMBLEUPON_CIRCLE),
  SUBSCRIPT (CFontAwesomeCSS.FA_SUBSCRIPT),
  SUBWAY (CFontAwesomeCSS.FA_SUBWAY),
  SUITCASE (CFontAwesomeCSS.FA_SUITCASE),
  SUN_O (CFontAwesomeCSS.FA_SUN_O),
  SUPERSCRIPT (CFontAwesomeCSS.FA_SUPERSCRIPT),
  SUPPORT (CFontAwesomeCSS.FA_SUPPORT),
  TABLE (CFontAwesomeCSS.FA_TABLE),
  TABLET (CFontAwesomeCSS.FA_TABLET),
  TACHOMETER (CFontAwesomeCSS.FA_TACHOMETER),
  TAG (CFontAwesomeCSS.FA_TAG),
  TAGS (CFontAwesomeCSS.FA_TAGS),
  TASKS (CFontAwesomeCSS.FA_TASKS),
  TAXI (CFontAwesomeCSS.FA_TAXI),
  TELEVISION (CFontAwesomeCSS.FA_TELEVISION),
  TENCENT_WEIBO (CFontAwesomeCSS.FA_TENCENT_WEIBO),
  TERMINAL (CFontAwesomeCSS.FA_TERMINAL),
  TEXT_HEIGHT (CFontAwesomeCSS.FA_TEXT_HEIGHT),
  TEXT_WIDTH (CFontAwesomeCSS.FA_TEXT_WIDTH),
  TH (CFontAwesomeCSS.FA_TH),
  TH_LARGE (CFontAwesomeCSS.FA_TH_LARGE),
  TH_LIST (CFontAwesomeCSS.FA_TH_LIST),
  THUMB_TACK (CFontAwesomeCSS.FA_THUMB_TACK),
  THUMBS_DOWN (CFontAwesomeCSS.FA_THUMBS_DOWN),
  THUMBS_O_DOWN (CFontAwesomeCSS.FA_THUMBS_O_DOWN),
  THUMBS_O_UP (CFontAwesomeCSS.FA_THUMBS_O_UP),
  THUMBS_UP (CFontAwesomeCSS.FA_THUMBS_UP),
  TICKET (CFontAwesomeCSS.FA_TICKET),
  TIMES (CFontAwesomeCSS.FA_TIMES),
  TIMES_CIRCLE (CFontAwesomeCSS.FA_TIMES_CIRCLE),
  TIMES_CIRCLE_O (CFontAwesomeCSS.FA_TIMES_CIRCLE_O),
  TINT (CFontAwesomeCSS.FA_TINT),
  TOGGLE_DOWN (CFontAwesomeCSS.FA_TOGGLE_DOWN),
  TOGGLE_LEFT (CFontAwesomeCSS.FA_TOGGLE_LEFT),
  TOGGLE_OFF (CFontAwesomeCSS.FA_TOGGLE_OFF),
  TOGGLE_ON (CFontAwesomeCSS.FA_TOGGLE_ON),
  TOGGLE_RIGHT (CFontAwesomeCSS.FA_TOGGLE_RIGHT),
  TOGGLE_UP (CFontAwesomeCSS.FA_TOGGLE_UP),
  TRADEMARK (CFontAwesomeCSS.FA_TRADEMARK),
  TRAIN (CFontAwesomeCSS.FA_TRAIN),
  TRANSGENDER (CFontAwesomeCSS.FA_TRANSGENDER),
  TRANSGENDER_ALT (CFontAwesomeCSS.FA_TRANSGENDER_ALT),
  TRASH (CFontAwesomeCSS.FA_TRASH),
  TRASH_O (CFontAwesomeCSS.FA_TRASH_O),
  TREE (CFontAwesomeCSS.FA_TREE),
  TRELLO (CFontAwesomeCSS.FA_TRELLO),
  TRIPADVISOR (CFontAwesomeCSS.FA_TRIPADVISOR),
  TROPHY (CFontAwesomeCSS.FA_TROPHY),
  TRUCK (CFontAwesomeCSS.FA_TRUCK),
  TRY (CFontAwesomeCSS.FA_TRY),
  TTY (CFontAwesomeCSS.FA_TTY),
  TUMBLR (CFontAwesomeCSS.FA_TUMBLR),
  TUMBLR_SQUARE (CFontAwesomeCSS.FA_TUMBLR_SQUARE),
  TURKISH_LIRA (CFontAwesomeCSS.FA_TURKISH_LIRA),
  TV (CFontAwesomeCSS.FA_TV),
  TWITCH (CFontAwesomeCSS.FA_TWITCH),
  TWITTER (CFontAwesomeCSS.FA_TWITTER),
  TWITTER_SQUARE (CFontAwesomeCSS.FA_TWITTER_SQUARE),
  UMBRELLA (CFontAwesomeCSS.FA_UMBRELLA),
  UNDERLINE (CFontAwesomeCSS.FA_UNDERLINE),
  UNDO (CFontAwesomeCSS.FA_UNDO),
  UNIVERSITY (CFontAwesomeCSS.FA_UNIVERSITY),
  UNLINK (CFontAwesomeCSS.FA_UNLINK),
  UNLOCK (CFontAwesomeCSS.FA_UNLOCK),
  UNLOCK_ALT (CFontAwesomeCSS.FA_UNLOCK_ALT),
  UNSORTED (CFontAwesomeCSS.FA_UNSORTED),
  UPLOAD (CFontAwesomeCSS.FA_UPLOAD),
  USB (CFontAwesomeCSS.FA_USB),
  USD (CFontAwesomeCSS.FA_USD),
  USER (CFontAwesomeCSS.FA_USER),
  USER_MD (CFontAwesomeCSS.FA_USER_MD),
  USER_PLUS (CFontAwesomeCSS.FA_USER_PLUS),
  USER_SECRET (CFontAwesomeCSS.FA_USER_SECRET),
  USER_TIMES (CFontAwesomeCSS.FA_USER_TIMES),
  USERS (CFontAwesomeCSS.FA_USERS),
  VENUS (CFontAwesomeCSS.FA_VENUS),
  VENUS_DOUBLE (CFontAwesomeCSS.FA_VENUS_DOUBLE),
  VENUS_MARS (CFontAwesomeCSS.FA_VENUS_MARS),
  VIACOIN (CFontAwesomeCSS.FA_VIACOIN),
  VIDEO_CAMERA (CFontAwesomeCSS.FA_VIDEO_CAMERA),
  VIMEO (CFontAwesomeCSS.FA_VIMEO),
  VIMEO_SQUARE (CFontAwesomeCSS.FA_VIMEO_SQUARE),
  VINE (CFontAwesomeCSS.FA_VINE),
  VK (CFontAwesomeCSS.FA_VK),
  VOLUME_DOWN (CFontAwesomeCSS.FA_VOLUME_DOWN),
  VOLUME_OFF (CFontAwesomeCSS.FA_VOLUME_OFF),
  VOLUME_UP (CFontAwesomeCSS.FA_VOLUME_UP),
  WARNING (CFontAwesomeCSS.FA_WARNING),
  WECHAT (CFontAwesomeCSS.FA_WECHAT),
  WEIBO (CFontAwesomeCSS.FA_WEIBO),
  WEIXIN (CFontAwesomeCSS.FA_WEIXIN),
  WHATSAPP (CFontAwesomeCSS.FA_WHATSAPP),
  WHEELCHAIR (CFontAwesomeCSS.FA_WHEELCHAIR),
  WIFI (CFontAwesomeCSS.FA_WIFI),
  WIKIPEDIA_W (CFontAwesomeCSS.FA_WIKIPEDIA_W),
  WINDOWS (CFontAwesomeCSS.FA_WINDOWS),
  WON (CFontAwesomeCSS.FA_WON),
  WORDPRESS (CFontAwesomeCSS.FA_WORDPRESS),
  WRENCH (CFontAwesomeCSS.FA_WRENCH),
  XING (CFontAwesomeCSS.FA_XING),
  XING_SQUARE (CFontAwesomeCSS.FA_XING_SQUARE),
  Y_COMBINATOR (CFontAwesomeCSS.FA_Y_COMBINATOR),
  Y_COMBINATOR_SQUARE (CFontAwesomeCSS.FA_Y_COMBINATOR_SQUARE),
  YAHOO (CFontAwesomeCSS.FA_YAHOO),
  YC (CFontAwesomeCSS.FA_YC),
  YC_SQUARE (CFontAwesomeCSS.FA_YC_SQUARE),
  YELP (CFontAwesomeCSS.FA_YELP),
  YEN (CFontAwesomeCSS.FA_YEN),
  YOUTUBE (CFontAwesomeCSS.FA_YOUTUBE),
  YOUTUBE_PLAY (CFontAwesomeCSS.FA_YOUTUBE_PLAY),
  YOUTUBE_SQUARE (CFontAwesomeCSS.FA_YOUTUBE_SQUARE);

  private final ICSSClassProvider m_aCSSClass;

  private EFontAwesomeIcon (@Nonnull final ICSSClassProvider aCSSClass)
  {
    m_aCSSClass = aCSSClass;
  }

  @Nullable
  public String getCSSClass ()
  {
    return m_aCSSClass.getCSSClass ();
  }

  @Nonnull
  public <T extends IHCElement <?>> T applyToNode (@Nonnull final T aElement)
  {
    aElement.addClasses (CFontAwesomeCSS.FA, m_aCSSClass);
    return aElement;
  }

  @Nonnull
  public HCI getAsNode ()
  {
    return applyToNode (new HCI ());
  }

  @Nonnull
  public HCI getAsNodeLarge ()
  {
    return getAsNode ().addClass (CFontAwesomeCSS.FA_LG);
  }

  @Nonnull
  public HCI getAsNode2x ()
  {
    return getAsNode ().addClass (CFontAwesomeCSS.FA_2X);
  }

  @Nonnull
  public HCI getAsNode3x ()
  {
    return getAsNode ().addClass (CFontAwesomeCSS.FA_3X);
  }

  @Nonnull
  public HCI getAsNode4x ()
  {
    return getAsNode ().addClass (CFontAwesomeCSS.FA_4X);
  }

  @Nonnull
  public HCI getAsNode5x ()
  {
    return getAsNode ().addClass (CFontAwesomeCSS.FA_5X);
  }

  @Nonnull
  public HCI getAsNodeFixedWidth ()
  {
    return getAsNode ().addClass (CFontAwesomeCSS.FA_FW);
  }

  @Nonnull
  public HCI getAsNodeListBullet ()
  {
    return getAsNode ().addClass (CFontAwesomeCSS.FA_LI);
  }

  @Nonnull
  public HCI getAsNodeSpinning ()
  {
    return getAsNode ().addClass (CFontAwesomeCSS.FA_SPIN);
  }

  @Nonnull
  public HCI getAsNodeRotate90 ()
  {
    return getAsNode ().addClass (CFontAwesomeCSS.FA_ROTATE_90);
  }

  @Nonnull
  public HCI getAsNodeRotate180 ()
  {
    return getAsNode ().addClass (CFontAwesomeCSS.FA_ROTATE_180);
  }

  @Nonnull
  public HCI getAsNodeRotate270 ()
  {
    return getAsNode ().addClass (CFontAwesomeCSS.FA_ROTATE_270);
  }

  @Nonnull
  public HCI getAsNodeFlipHorz ()
  {
    return getAsNode ().addClass (CFontAwesomeCSS.FA_FLIP_HORIZONTAL);
  }

  @Nonnull
  public HCI getAsNodeFlipVert ()
  {
    return getAsNode ().addClass (CFontAwesomeCSS.FA_FLIP_VERTICAL);
  }

  @Nonnull
  public HCI getAsNodeInverse ()
  {
    return getAsNode ().addClass (CFontAwesomeCSS.FA_INVERSE);
  }

  @Nonnull
  public static HCSpan createIconStack (@Nonnull final IHCElement <?> aLargeIcon,
                                        @Nonnull final IHCElement <?> aSmallIcon)
  {
    final HCSpan ret = new HCSpan ().addClasses (CFontAwesomeCSS.FA_STACK, CFontAwesomeCSS.FA_LG);
    ret.addChild (aLargeIcon.addClass (CFontAwesomeCSS.FA_STACK_2X));
    ret.addChild (aSmallIcon.addClass (CFontAwesomeCSS.FA_STACK_1X));
    return ret;
  }

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
    DefaultIcons.set (EDefaultIcon.UP, ARROW_UP);
    DefaultIcons.set (EDefaultIcon.YES, CHECK);
  }
}
