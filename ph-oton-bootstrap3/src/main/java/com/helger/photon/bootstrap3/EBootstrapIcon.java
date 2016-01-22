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
package com.helger.photon.bootstrap3;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.helger.html.CHTMLAttributes;
import com.helger.html.css.ICSSClassProvider;
import com.helger.html.hc.html.IHCElement;
import com.helger.html.hc.html.textlevel.HCSpan;
import com.helger.photon.uicore.icon.DefaultIcons;
import com.helger.photon.uicore.icon.EDefaultIcon;
import com.helger.photon.uicore.icon.IIcon;

/**
 * Bootstrap3 icons
 *
 * @author Philip Helger
 */
public enum EBootstrapIcon implements IIcon
{
  ADJUST (CBootstrapCSS.GLYPHICON_ADJUST),
  ALERT (CBootstrapCSS.GLYPHICON_ALERT),
  ALIGN_CENTER (CBootstrapCSS.GLYPHICON_ALIGN_CENTER),
  ALIGN_JUSTIFY (CBootstrapCSS.GLYPHICON_ALIGN_JUSTIFY),
  ALIGN_LEFT (CBootstrapCSS.GLYPHICON_ALIGN_LEFT),
  ALIGN_RIGHT (CBootstrapCSS.GLYPHICON_ALIGN_RIGHT),
  APPLE (CBootstrapCSS.GLYPHICON_APPLE),
  ARROW_DOWN (CBootstrapCSS.GLYPHICON_ARROW_DOWN),
  ARROW_LEFT (CBootstrapCSS.GLYPHICON_ARROW_LEFT),
  ARROW_RIGHT (CBootstrapCSS.GLYPHICON_ARROW_RIGHT),
  ARROW_UP (CBootstrapCSS.GLYPHICON_ARROW_UP),
  ASTERISK (CBootstrapCSS.GLYPHICON_ASTERISK),
  BABY_FORMULA (CBootstrapCSS.GLYPHICON_BABY_FORMULA),
  BACKWARD (CBootstrapCSS.GLYPHICON_BACKWARD),
  BAN_CIRCLE (CBootstrapCSS.GLYPHICON_BAN_CIRCLE),
  BARCODE (CBootstrapCSS.GLYPHICON_BARCODE),
  BED (CBootstrapCSS.GLYPHICON_BED),
  BELL (CBootstrapCSS.GLYPHICON_BELL),
  BISHOP (CBootstrapCSS.GLYPHICON_BISHOP),
  BITCOIN (CBootstrapCSS.GLYPHICON_BITCOIN),
  BLACKBOARD (CBootstrapCSS.GLYPHICON_BLACKBOARD),
  BOLD (CBootstrapCSS.GLYPHICON_BOLD),
  BOOK (CBootstrapCSS.GLYPHICON_BOOK),
  BOOKMARK (CBootstrapCSS.GLYPHICON_BOOKMARK),
  BRIEFCASE (CBootstrapCSS.GLYPHICON_BRIEFCASE),
  BTC (CBootstrapCSS.GLYPHICON_BTC),
  BULLHORN (CBootstrapCSS.GLYPHICON_BULLHORN),
  CALENDAR (CBootstrapCSS.GLYPHICON_CALENDAR),
  CAMERA (CBootstrapCSS.GLYPHICON_CAMERA),
  CD (CBootstrapCSS.GLYPHICON_CD),
  CERTIFICATE (CBootstrapCSS.GLYPHICON_CERTIFICATE),
  CHECK (CBootstrapCSS.GLYPHICON_CHECK),
  CHEVRON_DOWN (CBootstrapCSS.GLYPHICON_CHEVRON_DOWN),
  CHEVRON_LEFT (CBootstrapCSS.GLYPHICON_CHEVRON_LEFT),
  CHEVRON_RIGHT (CBootstrapCSS.GLYPHICON_CHEVRON_RIGHT),
  CHEVRON_UP (CBootstrapCSS.GLYPHICON_CHEVRON_UP),
  CIRCLE_ARROW_DOWN (CBootstrapCSS.GLYPHICON_CIRCLE_ARROW_DOWN),
  CIRCLE_ARROW_LEFT (CBootstrapCSS.GLYPHICON_CIRCLE_ARROW_LEFT),
  CIRCLE_ARROW_RIGHT (CBootstrapCSS.GLYPHICON_CIRCLE_ARROW_RIGHT),
  CIRCLE_ARROW_UP (CBootstrapCSS.GLYPHICON_CIRCLE_ARROW_UP),
  CLOUD (CBootstrapCSS.GLYPHICON_CLOUD),
  CLOUD_DOWNLOAD (CBootstrapCSS.GLYPHICON_CLOUD_DOWNLOAD),
  CLOUD_UPLOAD (CBootstrapCSS.GLYPHICON_CLOUD_UPLOAD),
  COG (CBootstrapCSS.GLYPHICON_COG),
  COLLAPSE_DOWN (CBootstrapCSS.GLYPHICON_COLLAPSE_DOWN),
  COLLAPSE_UP (CBootstrapCSS.GLYPHICON_COLLAPSE_UP),
  COMMENT (CBootstrapCSS.GLYPHICON_COMMENT),
  COMPRESSED (CBootstrapCSS.GLYPHICON_COMPRESSED),
  CONSOLE (CBootstrapCSS.GLYPHICON_CONSOLE),
  COPY (CBootstrapCSS.GLYPHICON_COPY),
  COPYRIGHT_MARK (CBootstrapCSS.GLYPHICON_COPYRIGHT_MARK),
  CREDIT_CARD (CBootstrapCSS.GLYPHICON_CREDIT_CARD),
  CUTLERY (CBootstrapCSS.GLYPHICON_CUTLERY),
  DASHBOARD (CBootstrapCSS.GLYPHICON_DASHBOARD),
  DOWNLOAD (CBootstrapCSS.GLYPHICON_DOWNLOAD),
  DOWNLOAD_ALT (CBootstrapCSS.GLYPHICON_DOWNLOAD_ALT),
  DUPLICATE (CBootstrapCSS.GLYPHICON_DUPLICATE),
  EARPHONE (CBootstrapCSS.GLYPHICON_EARPHONE),
  EDIT (CBootstrapCSS.GLYPHICON_EDIT),
  EDUCATION (CBootstrapCSS.GLYPHICON_EDUCATION),
  EJECT (CBootstrapCSS.GLYPHICON_EJECT),
  ENVELOPE (CBootstrapCSS.GLYPHICON_ENVELOPE),
  EQUALIZER (CBootstrapCSS.GLYPHICON_EQUALIZER),
  ERASE (CBootstrapCSS.GLYPHICON_ERASE),
  EUR (CBootstrapCSS.GLYPHICON_EUR),
  EURO (CBootstrapCSS.GLYPHICON_EURO),
  EXCLAMATION_SIGN (CBootstrapCSS.GLYPHICON_EXCLAMATION_SIGN),
  EXPAND (CBootstrapCSS.GLYPHICON_EXPAND),
  EXPORT (CBootstrapCSS.GLYPHICON_EXPORT),
  EYE_CLOSE (CBootstrapCSS.GLYPHICON_EYE_CLOSE),
  EYE_OPEN (CBootstrapCSS.GLYPHICON_EYE_OPEN),
  FACETIME_VIDEO (CBootstrapCSS.GLYPHICON_FACETIME_VIDEO),
  FAST_BACKWARD (CBootstrapCSS.GLYPHICON_FAST_BACKWARD),
  FAST_FORWARD (CBootstrapCSS.GLYPHICON_FAST_FORWARD),
  FILE (CBootstrapCSS.GLYPHICON_FILE),
  FILM (CBootstrapCSS.GLYPHICON_FILM),
  FILTER (CBootstrapCSS.GLYPHICON_FILTER),
  FIRE (CBootstrapCSS.GLYPHICON_FIRE),
  FLAG (CBootstrapCSS.GLYPHICON_FLAG),
  FLASH (CBootstrapCSS.GLYPHICON_FLASH),
  FLOPPY_DISK (CBootstrapCSS.GLYPHICON_FLOPPY_DISK),
  FLOPPY_OPEN (CBootstrapCSS.GLYPHICON_FLOPPY_OPEN),
  FLOPPY_REMOVE (CBootstrapCSS.GLYPHICON_FLOPPY_REMOVE),
  FLOPPY_SAVE (CBootstrapCSS.GLYPHICON_FLOPPY_SAVE),
  FLOPPY_SAVED (CBootstrapCSS.GLYPHICON_FLOPPY_SAVED),
  FOLDER_CLOSE (CBootstrapCSS.GLYPHICON_FOLDER_CLOSE),
  FOLDER_OPEN (CBootstrapCSS.GLYPHICON_FOLDER_OPEN),
  FONT (CBootstrapCSS.GLYPHICON_FONT),
  FORWARD (CBootstrapCSS.GLYPHICON_FORWARD),
  FULLSCREEN (CBootstrapCSS.GLYPHICON_FULLSCREEN),
  GBP (CBootstrapCSS.GLYPHICON_GBP),
  GIFT (CBootstrapCSS.GLYPHICON_GIFT),
  GLASS (CBootstrapCSS.GLYPHICON_GLASS),
  GLOBE (CBootstrapCSS.GLYPHICON_GLOBE),
  GRAIN (CBootstrapCSS.GLYPHICON_GRAIN),
  HAND_DOWN (CBootstrapCSS.GLYPHICON_HAND_DOWN),
  HAND_LEFT (CBootstrapCSS.GLYPHICON_HAND_LEFT),
  HAND_RIGHT (CBootstrapCSS.GLYPHICON_HAND_RIGHT),
  HAND_UP (CBootstrapCSS.GLYPHICON_HAND_UP),
  HD_VIDEO (CBootstrapCSS.GLYPHICON_HD_VIDEO),
  HDD (CBootstrapCSS.GLYPHICON_HDD),
  HEADER (CBootstrapCSS.GLYPHICON_HEADER),
  HEADPHONES (CBootstrapCSS.GLYPHICON_HEADPHONES),
  HEART (CBootstrapCSS.GLYPHICON_HEART),
  HEART_EMPTY (CBootstrapCSS.GLYPHICON_HEART_EMPTY),
  HOME (CBootstrapCSS.GLYPHICON_HOME),
  HOURGLASS (CBootstrapCSS.GLYPHICON_HOURGLASS),
  ICE_LOLLY (CBootstrapCSS.GLYPHICON_ICE_LOLLY),
  ICE_LOLLY_TASTED (CBootstrapCSS.GLYPHICON_ICE_LOLLY_TASTED),
  IMPORT (CBootstrapCSS.GLYPHICON_IMPORT),
  INBOX (CBootstrapCSS.GLYPHICON_INBOX),
  INDENT_LEFT (CBootstrapCSS.GLYPHICON_INDENT_LEFT),
  INDENT_RIGHT (CBootstrapCSS.GLYPHICON_INDENT_RIGHT),
  INFO_SIGN (CBootstrapCSS.GLYPHICON_INFO_SIGN),
  ITALIC (CBootstrapCSS.GLYPHICON_ITALIC),
  JPY (CBootstrapCSS.GLYPHICON_JPY),
  KING (CBootstrapCSS.GLYPHICON_KING),
  KNIGHT (CBootstrapCSS.GLYPHICON_KNIGHT),
  LAMP (CBootstrapCSS.GLYPHICON_LAMP),
  LEAF (CBootstrapCSS.GLYPHICON_LEAF),
  LEVEL_UP (CBootstrapCSS.GLYPHICON_LEVEL_UP),
  LINK (CBootstrapCSS.GLYPHICON_LINK),
  LIST (CBootstrapCSS.GLYPHICON_LIST),
  LIST_ALT (CBootstrapCSS.GLYPHICON_LIST_ALT),
  LOCK (CBootstrapCSS.GLYPHICON_LOCK),
  LOG_IN (CBootstrapCSS.GLYPHICON_LOG_IN),
  LOG_OUT (CBootstrapCSS.GLYPHICON_LOG_OUT),
  MAGNET (CBootstrapCSS.GLYPHICON_MAGNET),
  MAP_MARKER (CBootstrapCSS.GLYPHICON_MAP_MARKER),
  MENU_DOWN (CBootstrapCSS.GLYPHICON_MENU_DOWN),
  MENU_HAMBURGER (CBootstrapCSS.GLYPHICON_MENU_HAMBURGER),
  MENU_LEFT (CBootstrapCSS.GLYPHICON_MENU_LEFT),
  MENU_RIGHT (CBootstrapCSS.GLYPHICON_MENU_RIGHT),
  MENU_UP (CBootstrapCSS.GLYPHICON_MENU_UP),
  MINUS (CBootstrapCSS.GLYPHICON_MINUS),
  MINUS_SIGN (CBootstrapCSS.GLYPHICON_MINUS_SIGN),
  MODAL_WINDOW (CBootstrapCSS.GLYPHICON_MODAL_WINDOW),
  MOVE (CBootstrapCSS.GLYPHICON_MOVE),
  MUSIC (CBootstrapCSS.GLYPHICON_MUSIC),
  NEW_WINDOW (CBootstrapCSS.GLYPHICON_NEW_WINDOW),
  OBJECT_ALIGN_BOTTOM (CBootstrapCSS.GLYPHICON_OBJECT_ALIGN_BOTTOM),
  OBJECT_ALIGN_HORIZONTAL (CBootstrapCSS.GLYPHICON_OBJECT_ALIGN_HORIZONTAL),
  OBJECT_ALIGN_LEFT (CBootstrapCSS.GLYPHICON_OBJECT_ALIGN_LEFT),
  OBJECT_ALIGN_RIGHT (CBootstrapCSS.GLYPHICON_OBJECT_ALIGN_RIGHT),
  OBJECT_ALIGN_TOP (CBootstrapCSS.GLYPHICON_OBJECT_ALIGN_TOP),
  OBJECT_ALIGN_VERTICAL (CBootstrapCSS.GLYPHICON_OBJECT_ALIGN_VERTICAL),
  OFF (CBootstrapCSS.GLYPHICON_OFF),
  OIL (CBootstrapCSS.GLYPHICON_OIL),
  OK (CBootstrapCSS.GLYPHICON_OK),
  OK_CIRCLE (CBootstrapCSS.GLYPHICON_OK_CIRCLE),
  OK_SIGN (CBootstrapCSS.GLYPHICON_OK_SIGN),
  OPEN (CBootstrapCSS.GLYPHICON_OPEN),
  OPEN_FILE (CBootstrapCSS.GLYPHICON_OPEN_FILE),
  OPTION_HORIZONTAL (CBootstrapCSS.GLYPHICON_OPTION_HORIZONTAL),
  OPTION_VERTICAL (CBootstrapCSS.GLYPHICON_OPTION_VERTICAL),
  PAPERCLIP (CBootstrapCSS.GLYPHICON_PAPERCLIP),
  PASTE (CBootstrapCSS.GLYPHICON_PASTE),
  PAUSE (CBootstrapCSS.GLYPHICON_PAUSE),
  PAWN (CBootstrapCSS.GLYPHICON_PAWN),
  PENCIL (CBootstrapCSS.GLYPHICON_PENCIL),
  PHONE (CBootstrapCSS.GLYPHICON_PHONE),
  PHONE_ALT (CBootstrapCSS.GLYPHICON_PHONE_ALT),
  PICTURE (CBootstrapCSS.GLYPHICON_PICTURE),
  PIGGY_BANK (CBootstrapCSS.GLYPHICON_PIGGY_BANK),
  PLANE (CBootstrapCSS.GLYPHICON_PLANE),
  PLAY (CBootstrapCSS.GLYPHICON_PLAY),
  PLAY_CIRCLE (CBootstrapCSS.GLYPHICON_PLAY_CIRCLE),
  PLUS (CBootstrapCSS.GLYPHICON_PLUS),
  PLUS_SIGN (CBootstrapCSS.GLYPHICON_PLUS_SIGN),
  PRINT (CBootstrapCSS.GLYPHICON_PRINT),
  PUSHPIN (CBootstrapCSS.GLYPHICON_PUSHPIN),
  QRCODE (CBootstrapCSS.GLYPHICON_QRCODE),
  QUEEN (CBootstrapCSS.GLYPHICON_QUEEN),
  QUESTION_SIGN (CBootstrapCSS.GLYPHICON_QUESTION_SIGN),
  RANDOM (CBootstrapCSS.GLYPHICON_RANDOM),
  RECORD (CBootstrapCSS.GLYPHICON_RECORD),
  REFRESH (CBootstrapCSS.GLYPHICON_REFRESH),
  REGISTRATION_MARK (CBootstrapCSS.GLYPHICON_REGISTRATION_MARK),
  REMOVE (CBootstrapCSS.GLYPHICON_REMOVE),
  REMOVE_CIRCLE (CBootstrapCSS.GLYPHICON_REMOVE_CIRCLE),
  REMOVE_SIGN (CBootstrapCSS.GLYPHICON_REMOVE_SIGN),
  REPEAT (CBootstrapCSS.GLYPHICON_REPEAT),
  RESIZE_FULL (CBootstrapCSS.GLYPHICON_RESIZE_FULL),
  RESIZE_HORIZONTAL (CBootstrapCSS.GLYPHICON_RESIZE_HORIZONTAL),
  RESIZE_SMALL (CBootstrapCSS.GLYPHICON_RESIZE_SMALL),
  RESIZE_VERTICAL (CBootstrapCSS.GLYPHICON_RESIZE_VERTICAL),
  RETWEET (CBootstrapCSS.GLYPHICON_RETWEET),
  ROAD (CBootstrapCSS.GLYPHICON_ROAD),
  RUB (CBootstrapCSS.GLYPHICON_RUB),
  RUBLE (CBootstrapCSS.GLYPHICON_RUBLE),
  SAVE (CBootstrapCSS.GLYPHICON_SAVE),
  SAVE_FILE (CBootstrapCSS.GLYPHICON_SAVE_FILE),
  SAVED (CBootstrapCSS.GLYPHICON_SAVED),
  SCALE (CBootstrapCSS.GLYPHICON_SCALE),
  SCISSORS (CBootstrapCSS.GLYPHICON_SCISSORS),
  SCREENSHOT (CBootstrapCSS.GLYPHICON_SCREENSHOT),
  SD_VIDEO (CBootstrapCSS.GLYPHICON_SD_VIDEO),
  SEARCH (CBootstrapCSS.GLYPHICON_SEARCH),
  SEND (CBootstrapCSS.GLYPHICON_SEND),
  SHARE (CBootstrapCSS.GLYPHICON_SHARE),
  SHARE_ALT (CBootstrapCSS.GLYPHICON_SHARE_ALT),
  SHOPPING_CART (CBootstrapCSS.GLYPHICON_SHOPPING_CART),
  SIGNAL (CBootstrapCSS.GLYPHICON_SIGNAL),
  SORT (CBootstrapCSS.GLYPHICON_SORT),
  SORT_BY_ALPHABET (CBootstrapCSS.GLYPHICON_SORT_BY_ALPHABET),
  SORT_BY_ALPHABET_ALT (CBootstrapCSS.GLYPHICON_SORT_BY_ALPHABET_ALT),
  SORT_BY_ATTRIBUTES (CBootstrapCSS.GLYPHICON_SORT_BY_ATTRIBUTES),
  SORT_BY_ATTRIBUTES_ALT (CBootstrapCSS.GLYPHICON_SORT_BY_ATTRIBUTES_ALT),
  SORT_BY_ORDER (CBootstrapCSS.GLYPHICON_SORT_BY_ORDER),
  SORT_BY_ORDER_ALT (CBootstrapCSS.GLYPHICON_SORT_BY_ORDER_ALT),
  SOUND_5_1 (CBootstrapCSS.GLYPHICON_SOUND_5_1),
  SOUND_6_1 (CBootstrapCSS.GLYPHICON_SOUND_6_1),
  SOUND_7_1 (CBootstrapCSS.GLYPHICON_SOUND_7_1),
  SOUND_DOLBY (CBootstrapCSS.GLYPHICON_SOUND_DOLBY),
  SOUND_STEREO (CBootstrapCSS.GLYPHICON_SOUND_STEREO),
  STAR (CBootstrapCSS.GLYPHICON_STAR),
  STAR_EMPTY (CBootstrapCSS.GLYPHICON_STAR_EMPTY),
  STATS (CBootstrapCSS.GLYPHICON_STATS),
  STEP_BACKWARD (CBootstrapCSS.GLYPHICON_STEP_BACKWARD),
  STEP_FORWARD (CBootstrapCSS.GLYPHICON_STEP_FORWARD),
  STOP (CBootstrapCSS.GLYPHICON_STOP),
  SUBSCRIPT (CBootstrapCSS.GLYPHICON_SUBSCRIPT),
  SUBTITLES (CBootstrapCSS.GLYPHICON_SUBTITLES),
  SUNGLASSES (CBootstrapCSS.GLYPHICON_SUNGLASSES),
  SUPERSCRIPT (CBootstrapCSS.GLYPHICON_SUPERSCRIPT),
  TAG (CBootstrapCSS.GLYPHICON_TAG),
  TAGS (CBootstrapCSS.GLYPHICON_TAGS),
  TASKS (CBootstrapCSS.GLYPHICON_TASKS),
  TENT (CBootstrapCSS.GLYPHICON_TENT),
  TEXT_BACKGROUND (CBootstrapCSS.GLYPHICON_TEXT_BACKGROUND),
  TEXT_COLOR (CBootstrapCSS.GLYPHICON_TEXT_COLOR),
  TEXT_HEIGHT (CBootstrapCSS.GLYPHICON_TEXT_HEIGHT),
  TEXT_SIZE (CBootstrapCSS.GLYPHICON_TEXT_SIZE),
  TEXT_WIDTH (CBootstrapCSS.GLYPHICON_TEXT_WIDTH),
  TH (CBootstrapCSS.GLYPHICON_TH),
  TH_LARGE (CBootstrapCSS.GLYPHICON_TH_LARGE),
  TH_LIST (CBootstrapCSS.GLYPHICON_TH_LIST),
  THUMBS_DOWN (CBootstrapCSS.GLYPHICON_THUMBS_DOWN),
  THUMBS_UP (CBootstrapCSS.GLYPHICON_THUMBS_UP),
  TIME (CBootstrapCSS.GLYPHICON_TIME),
  TINT (CBootstrapCSS.GLYPHICON_TINT),
  TOWER (CBootstrapCSS.GLYPHICON_TOWER),
  TRANSFER (CBootstrapCSS.GLYPHICON_TRANSFER),
  TRASH (CBootstrapCSS.GLYPHICON_TRASH),
  TREE_CONIFER (CBootstrapCSS.GLYPHICON_TREE_CONIFER),
  TREE_DECIDUOUS (CBootstrapCSS.GLYPHICON_TREE_DECIDUOUS),
  TRIANGLE_BOTTOM (CBootstrapCSS.GLYPHICON_TRIANGLE_BOTTOM),
  TRIANGLE_LEFT (CBootstrapCSS.GLYPHICON_TRIANGLE_LEFT),
  TRIANGLE_RIGHT (CBootstrapCSS.GLYPHICON_TRIANGLE_RIGHT),
  TRIANGLE_TOP (CBootstrapCSS.GLYPHICON_TRIANGLE_TOP),
  UNCHECKED (CBootstrapCSS.GLYPHICON_UNCHECKED),
  UPLOAD (CBootstrapCSS.GLYPHICON_UPLOAD),
  USD (CBootstrapCSS.GLYPHICON_USD),
  USER (CBootstrapCSS.GLYPHICON_USER),
  VOLUME_DOWN (CBootstrapCSS.GLYPHICON_VOLUME_DOWN),
  VOLUME_OFF (CBootstrapCSS.GLYPHICON_VOLUME_OFF),
  VOLUME_UP (CBootstrapCSS.GLYPHICON_VOLUME_UP),
  WARNING_SIGN (CBootstrapCSS.GLYPHICON_WARNING_SIGN),
  WRENCH (CBootstrapCSS.GLYPHICON_WRENCH),
  XBT (CBootstrapCSS.GLYPHICON_XBT),
  YEN (CBootstrapCSS.GLYPHICON_YEN),
  ZOOM_IN (CBootstrapCSS.GLYPHICON_ZOOM_IN),
  ZOOM_OUT (CBootstrapCSS.GLYPHICON_ZOOM_OUT);

  private final ICSSClassProvider m_aCSSClass;

  private EBootstrapIcon (@Nonnull final ICSSClassProvider aCSSClass)
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
    aElement.addClasses (CBootstrapCSS.GLYPHICON, m_aCSSClass);
    aElement.setCustomAttr (CHTMLAttributes.ARIA_HIDDEN, "true");
    return aElement;
  }

  @Nonnull
  public HCSpan getAsNode ()
  {
    return applyToNode (new HCSpan ());
  }

  public static void setAsDefault ()
  {
    DefaultIcons.set (EDefaultIcon.ADD, PLUS_SIGN);
    DefaultIcons.set (EDefaultIcon.BACK, CIRCLE_ARROW_LEFT);
    DefaultIcons.set (EDefaultIcon.BACK_TO_LIST, CIRCLE_ARROW_LEFT);
    DefaultIcons.set (EDefaultIcon.CANCEL, REMOVE_CIRCLE);
    DefaultIcons.set (EDefaultIcon.COPY, RETWEET);
    DefaultIcons.set (EDefaultIcon.DELETE, REMOVE);
    DefaultIcons.set (EDefaultIcon.DOWN, ARROW_DOWN);
    DefaultIcons.set (EDefaultIcon.EDIT, PENCIL);
    DefaultIcons.set (EDefaultIcon.HELP, QUESTION_SIGN);
    DefaultIcons.set (EDefaultIcon.INFO, INFO_SIGN);
    DefaultIcons.set (EDefaultIcon.KEY, LOCK);
    DefaultIcons.set (EDefaultIcon.MAGNIFIER, ZOOM_IN);
    DefaultIcons.set (EDefaultIcon.MINUS, MINUS);
    DefaultIcons.set (EDefaultIcon.NEW, FILE);
    DefaultIcons.set (EDefaultIcon.NEXT, CIRCLE_ARROW_RIGHT);
    DefaultIcons.set (EDefaultIcon.NO, REMOVE);
    DefaultIcons.set (EDefaultIcon.PLUS, PLUS);
    DefaultIcons.set (EDefaultIcon.REFRESH, REFRESH);
    DefaultIcons.set (EDefaultIcon.SAVE, HDD);
    DefaultIcons.set (EDefaultIcon.SAVE_ALL, HDD);
    DefaultIcons.set (EDefaultIcon.SAVE_AS, HDD);
    DefaultIcons.set (EDefaultIcon.SAVE_CLOSE, HDD);
    DefaultIcons.set (EDefaultIcon.UNDELETE, ARROW_LEFT);
    DefaultIcons.set (EDefaultIcon.UP, ARROW_UP);
    DefaultIcons.set (EDefaultIcon.YES, OK);
  }
}
