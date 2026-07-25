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
package com.helger.photon.icon.materialdesign;

import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.Nullable;

import com.helger.annotation.Nonempty;
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
 * Material design icons
 *
 * @author Philip Helger
 */
@Deprecated (forRemoval = true, since = "12.3.0")
@SuppressWarnings ("removal")
public enum EMaterialDesignIcon implements IIcon
{
  @Deprecated (forRemoval = true, since = "12.3.0")
  _3D_ROTATION("md-3d_rotation"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  AC_UNIT("md-ac_unit"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  ACCESS_ALARM("md-access_alarm"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  ACCESS_ALARMS("md-access_alarms"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  ACCESS_TIME("md-access_time"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  ACCESSIBILITY("md-accessibility"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  ACCESSIBLE("md-accessible"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  ACCOUNT_BALANCE("md-account_balance"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  ACCOUNT_BALANCE_WALLET("md-account_balance_wallet"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  ACCOUNT_BOX("md-account_box"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  ACCOUNT_CIRCLE("md-account_circle"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  ADB("md-adb"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  ADD("md-add"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  ADD_A_PHOTO("md-add_a_photo"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  ADD_ALARM("md-add_alarm"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  ADD_ALERT("md-add_alert"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  ADD_BOX("md-add_box"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  ADD_CIRCLE("md-add_circle"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  ADD_CIRCLE_OUTLINE("md-add_circle_outline"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  ADD_LOCATION("md-add_location"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  ADD_SHOPPING_CART("md-add_shopping_cart"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  ADD_TO_PHOTOS("md-add_to_photos"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  ADD_TO_QUEUE("md-add_to_queue"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  ADJUST("md-adjust"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  AIRLINE_SEAT_FLAT("md-airline_seat_flat"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  AIRLINE_SEAT_FLAT_ANGLED("md-airline_seat_flat_angled"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  AIRLINE_SEAT_INDIVIDUAL_SUITE("md-airline_seat_individual_suite"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  AIRLINE_SEAT_LEGROOM_EXTRA("md-airline_seat_legroom_extra"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  AIRLINE_SEAT_LEGROOM_NORMAL("md-airline_seat_legroom_normal"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  AIRLINE_SEAT_LEGROOM_REDUCED("md-airline_seat_legroom_reduced"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  AIRLINE_SEAT_RECLINE_EXTRA("md-airline_seat_recline_extra"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  AIRLINE_SEAT_RECLINE_NORMAL("md-airline_seat_recline_normal"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  AIRPLANEMODE_ACTIVE("md-airplanemode_active"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  AIRPLANEMODE_INACTIVE("md-airplanemode_inactive"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  AIRPLAY("md-airplay"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  AIRPORT_SHUTTLE("md-airport_shuttle"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  ALARM("md-alarm"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  ALARM_ADD("md-alarm_add"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  ALARM_OFF("md-alarm_off"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  ALARM_ON("md-alarm_on"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  ALBUM("md-album"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  ALL_INCLUSIVE("md-all_inclusive"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  ALL_OUT("md-all_out"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  ANDROID("md-android"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  ANNOUNCEMENT("md-announcement"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  APPS("md-apps"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  ARCHIVE("md-archive"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  ARROW_BACK("md-arrow_back"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  ARROW_DOWNWARD("md-arrow_downward"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  ARROW_DROP_DOWN("md-arrow_drop_down"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  ARROW_DROP_DOWN_CIRCLE("md-arrow_drop_down_circle"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  ARROW_DROP_UP("md-arrow_drop_up"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  ARROW_FORWARD("md-arrow_forward"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  ARROW_UPWARD("md-arrow_upward"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  ART_TRACK("md-art_track"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  ASPECT_RATIO("md-aspect_ratio"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  ASSESSMENT("md-assessment"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  ASSIGNMENT("md-assignment"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  ASSIGNMENT_IND("md-assignment_ind"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  ASSIGNMENT_LATE("md-assignment_late"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  ASSIGNMENT_RETURN("md-assignment_return"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  ASSIGNMENT_RETURNED("md-assignment_returned"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  ASSIGNMENT_TURNED_IN("md-assignment_turned_in"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  ASSISTANT("md-assistant"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  ASSISTANT_PHOTO("md-assistant_photo"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  ATTACH_FILE("md-attach_file"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  ATTACH_MONEY("md-attach_money"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  ATTACHMENT("md-attachment"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  AUDIOTRACK("md-audiotrack"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  AUTORENEW("md-autorenew"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  AV_TIMER("md-av_timer"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  BACKSPACE("md-backspace"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  BACKUP("md-backup"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  BATTERY_ALERT("md-battery_alert"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  BATTERY_CHARGING_FULL("md-battery_charging_full"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  BATTERY_FULL("md-battery_full"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  BATTERY_STD("md-battery_std"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  BATTERY_UNKNOWN("md-battery_unknown"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  BEACH_ACCESS("md-beach_access"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  BEENHERE("md-beenhere"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  BLOCK("md-block"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  BLUETOOTH("md-bluetooth"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  BLUETOOTH_AUDIO("md-bluetooth_audio"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  BLUETOOTH_CONNECTED("md-bluetooth_connected"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  BLUETOOTH_DISABLED("md-bluetooth_disabled"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  BLUETOOTH_SEARCHING("md-bluetooth_searching"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  BLUR_CIRCULAR("md-blur_circular"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  BLUR_LINEAR("md-blur_linear"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  BLUR_OFF("md-blur_off"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  BLUR_ON("md-blur_on"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  BOOK("md-book"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  BOOKMARK("md-bookmark"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  BOOKMARK_BORDER("md-bookmark_border"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  BORDER_ALL("md-border_all"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  BORDER_BOTTOM("md-border_bottom"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  BORDER_CLEAR("md-border_clear"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  BORDER_COLOR("md-border_color"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  BORDER_HORIZONTAL("md-border_horizontal"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  BORDER_INNER("md-border_inner"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  BORDER_LEFT("md-border_left"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  BORDER_OUTER("md-border_outer"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  BORDER_RIGHT("md-border_right"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  BORDER_STYLE("md-border_style"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  BORDER_TOP("md-border_top"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  BORDER_VERTICAL("md-border_vertical"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  BRANDING_WATERMARK("md-branding_watermark"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  BRIGHTNESS_1("md-brightness_1"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  BRIGHTNESS_2("md-brightness_2"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  BRIGHTNESS_3("md-brightness_3"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  BRIGHTNESS_4("md-brightness_4"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  BRIGHTNESS_5("md-brightness_5"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  BRIGHTNESS_6("md-brightness_6"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  BRIGHTNESS_7("md-brightness_7"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  BRIGHTNESS_AUTO("md-brightness_auto"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  BRIGHTNESS_HIGH("md-brightness_high"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  BRIGHTNESS_LOW("md-brightness_low"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  BRIGHTNESS_MEDIUM("md-brightness_medium"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  BROKEN_IMAGE("md-broken_image"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  BRUSH("md-brush"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  BUBBLE_CHART("md-bubble_chart"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  BUG_REPORT("md-bug_report"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  BUILD("md-build"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  BURST_MODE("md-burst_mode"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  BUSINESS("md-business"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  BUSINESS_CENTER("md-business_center"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  CACHED("md-cached"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  CAKE("md-cake"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  CALL("md-call"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  CALL_END("md-call_end"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  CALL_MADE("md-call_made"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  CALL_MERGE("md-call_merge"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  CALL_MISSED("md-call_missed"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  CALL_MISSED_OUTGOING("md-call_missed_outgoing"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  CALL_RECEIVED("md-call_received"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  CALL_SPLIT("md-call_split"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  CALL_TO_ACTION("md-call_to_action"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  CAMERA("md-camera"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  CAMERA_ALT("md-camera_alt"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  CAMERA_ENHANCE("md-camera_enhance"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  CAMERA_FRONT("md-camera_front"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  CAMERA_REAR("md-camera_rear"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  CAMERA_ROLL("md-camera_roll"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  CANCEL("md-cancel"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  CARD_GIFTCARD("md-card_giftcard"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  CARD_MEMBERSHIP("md-card_membership"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  CARD_TRAVEL("md-card_travel"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  CASINO("md-casino"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  CAST("md-cast"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  CAST_CONNECTED("md-cast_connected"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  CENTER_FOCUS_STRONG("md-center_focus_strong"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  CENTER_FOCUS_WEAK("md-center_focus_weak"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  CHANGE_HISTORY("md-change_history"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  CHAT("md-chat"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  CHAT_BUBBLE("md-chat_bubble"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  CHAT_BUBBLE_OUTLINE("md-chat_bubble_outline"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  CHECK("md-check"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  CHECK_BOX("md-check_box"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  CHECK_BOX_OUTLINE_BLANK("md-check_box_outline_blank"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  CHECK_CIRCLE("md-check_circle"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  CHEVRON_LEFT("md-chevron_left"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  CHEVRON_RIGHT("md-chevron_right"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  CHILD_CARE("md-child_care"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  CHILD_FRIENDLY("md-child_friendly"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  CHROME_READER_MODE("md-chrome_reader_mode"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  CLASS("md-class"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  CLEAR("md-clear"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  CLEAR_ALL("md-clear_all"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  CLOSE("md-close"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  CLOSED_CAPTION("md-closed_caption"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  CLOUD("md-cloud"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  CLOUD_CIRCLE("md-cloud_circle"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  CLOUD_DONE("md-cloud_done"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  CLOUD_DOWNLOAD("md-cloud_download"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  CLOUD_OFF("md-cloud_off"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  CLOUD_QUEUE("md-cloud_queue"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  CLOUD_UPLOAD("md-cloud_upload"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  CODE("md-code"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  COLLECTIONS("md-collections"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  COLLECTIONS_BOOKMARK("md-collections_bookmark"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  COLOR_LENS("md-color_lens"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  COLORIZE("md-colorize"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  COMMENT("md-comment"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  COMPARE("md-compare"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  COMPARE_ARROWS("md-compare_arrows"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  COMPUTER("md-computer"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  CONFIRMATION_NUMBER("md-confirmation_number"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  CONTACT_MAIL("md-contact_mail"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  CONTACT_PHONE("md-contact_phone"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  CONTACTS("md-contacts"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  CONTENT_COPY("md-content_copy"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  CONTENT_CUT("md-content_cut"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  CONTENT_PASTE("md-content_paste"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  CONTROL_POINT("md-control_point"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  CONTROL_POINT_DUPLICATE("md-control_point_duplicate"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  COPYRIGHT("md-copyright"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  CREATE("md-create"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  CREATE_NEW_FOLDER("md-create_new_folder"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  CREDIT_CARD("md-credit_card"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  CROP("md-crop"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  CROP_16_9("md-crop_16_9"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  CROP_3_2("md-crop_3_2"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  CROP_5_4("md-crop_5_4"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  CROP_7_5("md-crop_7_5"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  CROP_DIN("md-crop_din"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  CROP_FREE("md-crop_free"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  CROP_LANDSCAPE("md-crop_landscape"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  CROP_ORIGINAL("md-crop_original"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  CROP_PORTRAIT("md-crop_portrait"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  CROP_ROTATE("md-crop_rotate"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  CROP_SQUARE("md-crop_square"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  DASHBOARD("md-dashboard"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  DATA_USAGE("md-data_usage"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  DATE_RANGE("md-date_range"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  DEHAZE("md-dehaze"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  DELETE("md-delete"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  DELETE_FOREVER("md-delete_forever"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  DELETE_SWEEP("md-delete_sweep"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  DESCRIPTION("md-description"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  DESKTOP_MAC("md-desktop_mac"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  DESKTOP_WINDOWS("md-desktop_windows"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  DETAILS("md-details"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  DEVELOPER_BOARD("md-developer_board"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  DEVELOPER_MODE("md-developer_mode"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  DEVICE_HUB("md-device_hub"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  DEVICES("md-devices"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  DEVICES_OTHER("md-devices_other"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  DIALER_SIP("md-dialer_sip"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  DIALPAD("md-dialpad"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  DIRECTIONS("md-directions"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  DIRECTIONS_BIKE("md-directions_bike"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  DIRECTIONS_BOAT("md-directions_boat"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  DIRECTIONS_BUS("md-directions_bus"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  DIRECTIONS_CAR("md-directions_car"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  DIRECTIONS_RAILWAY("md-directions_railway"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  DIRECTIONS_RUN("md-directions_run"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  DIRECTIONS_SUBWAY("md-directions_subway"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  DIRECTIONS_TRANSIT("md-directions_transit"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  DIRECTIONS_WALK("md-directions_walk"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  DISC_FULL("md-disc_full"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  DNS("md-dns"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  DO_NOT_DISTURB("md-do_not_disturb"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  DO_NOT_DISTURB_ALT("md-do_not_disturb_alt"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  DO_NOT_DISTURB_OFF("md-do_not_disturb_off"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  DO_NOT_DISTURB_ON("md-do_not_disturb_on"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  DOCK("md-dock"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  DOMAIN("md-domain"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  DONE("md-done"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  DONE_ALL("md-done_all"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  DONUT_LARGE("md-donut_large"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  DONUT_SMALL("md-donut_small"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  DRAFTS("md-drafts"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  DRAG_HANDLE("md-drag_handle"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  DRIVE_ETA("md-drive_eta"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  DVR("md-dvr"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  EDIT("md-edit"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  EDIT_LOCATION("md-edit_location"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  EJECT("md-eject"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  EMAIL("md-email"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  ENHANCED_ENCRYPTION("md-enhanced_encryption"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  EQUALIZER("md-equalizer"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  ERROR("md-error"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  ERROR_OUTLINE("md-error_outline"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  EURO_SYMBOL("md-euro_symbol"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  EV_STATION("md-ev_station"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  EVENT("md-event"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  EVENT_AVAILABLE("md-event_available"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  EVENT_BUSY("md-event_busy"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  EVENT_NOTE("md-event_note"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  EVENT_SEAT("md-event_seat"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  EXIT_TO_APP("md-exit_to_app"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  EXPAND_LESS("md-expand_less"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  EXPAND_MORE("md-expand_more"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  EXPLICIT("md-explicit"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  EXPLORE("md-explore"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  EXPOSURE("md-exposure"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  EXPOSURE_NEG_1("md-exposure_neg_1"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  EXPOSURE_NEG_2("md-exposure_neg_2"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  EXPOSURE_PLUS_1("md-exposure_plus_1"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  EXPOSURE_PLUS_2("md-exposure_plus_2"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  EXPOSURE_ZERO("md-exposure_zero"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  EXTENSION("md-extension"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  FACE("md-face"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  FAST_FORWARD("md-fast_forward"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  FAST_REWIND("md-fast_rewind"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  FAVORITE("md-favorite"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  FAVORITE_BORDER("md-favorite_border"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  FEATURED_PLAY_LIST("md-featured_play_list"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  FEATURED_VIDEO("md-featured_video"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  FEEDBACK("md-feedback"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  FIBER_DVR("md-fiber_dvr"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  FIBER_MANUAL_RECORD("md-fiber_manual_record"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  FIBER_NEW("md-fiber_new"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  FIBER_PIN("md-fiber_pin"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  FIBER_SMART_RECORD("md-fiber_smart_record"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  FILE_DOWNLOAD("md-file_download"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  FILE_UPLOAD("md-file_upload"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  FILTER("md-filter"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  FILTER_1("md-filter_1"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  FILTER_2("md-filter_2"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  FILTER_3("md-filter_3"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  FILTER_4("md-filter_4"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  FILTER_5("md-filter_5"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  FILTER_6("md-filter_6"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  FILTER_7("md-filter_7"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  FILTER_8("md-filter_8"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  FILTER_9("md-filter_9"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  FILTER_9_PLUS("md-filter_9_plus"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  FILTER_B_AND_W("md-filter_b_and_w"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  FILTER_CENTER_FOCUS("md-filter_center_focus"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  FILTER_DRAMA("md-filter_drama"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  FILTER_FRAMES("md-filter_frames"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  FILTER_HDR("md-filter_hdr"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  FILTER_LIST("md-filter_list"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  FILTER_NONE("md-filter_none"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  FILTER_TILT_SHIFT("md-filter_tilt_shift"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  FILTER_VINTAGE("md-filter_vintage"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  FIND_IN_PAGE("md-find_in_page"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  FIND_REPLACE("md-find_replace"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  FINGERPRINT("md-fingerprint"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  FIRST_PAGE("md-first_page"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  FITNESS_CENTER("md-fitness_center"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  FLAG("md-flag"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  FLARE("md-flare"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  FLASH_AUTO("md-flash_auto"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  FLASH_OFF("md-flash_off"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  FLASH_ON("md-flash_on"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  FLIGHT("md-flight"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  FLIGHT_LAND("md-flight_land"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  FLIGHT_TAKEOFF("md-flight_takeoff"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  FLIP("md-flip"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  FLIP_TO_BACK("md-flip_to_back"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  FLIP_TO_FRONT("md-flip_to_front"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  FOLDER("md-folder"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  FOLDER_OPEN("md-folder_open"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  FOLDER_SHARED("md-folder_shared"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  FOLDER_SPECIAL("md-folder_special"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  FONT_DOWNLOAD("md-font_download"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  FORMAT_ALIGN_CENTER("md-format_align_center"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  FORMAT_ALIGN_JUSTIFY("md-format_align_justify"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  FORMAT_ALIGN_LEFT("md-format_align_left"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  FORMAT_ALIGN_RIGHT("md-format_align_right"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  FORMAT_BOLD("md-format_bold"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  FORMAT_CLEAR("md-format_clear"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  FORMAT_COLOR_FILL("md-format_color_fill"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  FORMAT_COLOR_RESET("md-format_color_reset"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  FORMAT_COLOR_TEXT("md-format_color_text"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  FORMAT_INDENT_DECREASE("md-format_indent_decrease"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  FORMAT_INDENT_INCREASE("md-format_indent_increase"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  FORMAT_ITALIC("md-format_italic"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  FORMAT_LINE_SPACING("md-format_line_spacing"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  FORMAT_LIST_BULLETED("md-format_list_bulleted"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  FORMAT_LIST_NUMBERED("md-format_list_numbered"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  FORMAT_PAINT("md-format_paint"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  FORMAT_QUOTE("md-format_quote"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  FORMAT_SHAPES("md-format_shapes"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  FORMAT_SIZE("md-format_size"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  FORMAT_STRIKETHROUGH("md-format_strikethrough"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  FORMAT_TEXTDIRECTION_L_TO_R("md-format_textdirection_l_to_r"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  FORMAT_TEXTDIRECTION_R_TO_L("md-format_textdirection_r_to_l"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  FORMAT_UNDERLINED("md-format_underlined"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  FORUM("md-forum"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  FORWARD("md-forward"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  FORWARD_10("md-forward_10"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  FORWARD_30("md-forward_30"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  FORWARD_5("md-forward_5"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  FREE_BREAKFAST("md-free_breakfast"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  FULLSCREEN("md-fullscreen"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  FULLSCREEN_EXIT("md-fullscreen_exit"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  FUNCTIONS("md-functions"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  G_TRANSLATE("md-g_translate"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  GAMEPAD("md-gamepad"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  GAMES("md-games"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  GAVEL("md-gavel"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  GESTURE("md-gesture"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  GET_APP("md-get_app"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  GIF("md-gif"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  GOLF_COURSE("md-golf_course"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  GPS_FIXED("md-gps_fixed"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  GPS_NOT_FIXED("md-gps_not_fixed"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  GPS_OFF("md-gps_off"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  GRADE("md-grade"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  GRADIENT("md-gradient"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  GRAIN("md-grain"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  GRAPHIC_EQ("md-graphic_eq"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  GRID_OFF("md-grid_off"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  GRID_ON("md-grid_on"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  GROUP("md-group"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  GROUP_ADD("md-group_add"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  GROUP_WORK("md-group_work"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  HD("md-hd"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  HDR_OFF("md-hdr_off"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  HDR_ON("md-hdr_on"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  HDR_STRONG("md-hdr_strong"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  HDR_WEAK("md-hdr_weak"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  HEADSET("md-headset"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  HEADSET_MIC("md-headset_mic"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  HEALING("md-healing"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  HEARING("md-hearing"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  HELP("md-help"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  HELP_OUTLINE("md-help_outline"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  HIGH_QUALITY("md-high_quality"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  HIGHLIGHT("md-highlight"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  HIGHLIGHT_OFF("md-highlight_off"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  HISTORY("md-history"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  HOME("md-home"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  HOT_TUB("md-hot_tub"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  HOTEL("md-hotel"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  HOURGLASS_EMPTY("md-hourglass_empty"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  HOURGLASS_FULL("md-hourglass_full"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  HTTP("md-http"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  HTTPS("md-https"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  IMAGE("md-image"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  IMAGE_ASPECT_RATIO("md-image_aspect_ratio"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  IMPORT_CONTACTS("md-import_contacts"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  IMPORT_EXPORT("md-import_export"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  IMPORTANT_DEVICES("md-important_devices"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  INBOX("md-inbox"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  INDETERMINATE_CHECK_BOX("md-indeterminate_check_box"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  INFO("md-info"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  INFO_OUTLINE("md-info_outline"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  INPUT("md-input"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  INSERT_CHART("md-insert_chart"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  INSERT_COMMENT("md-insert_comment"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  INSERT_DRIVE_FILE("md-insert_drive_file"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  INSERT_EMOTICON("md-insert_emoticon"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  INSERT_INVITATION("md-insert_invitation"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  INSERT_LINK("md-insert_link"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  INSERT_PHOTO("md-insert_photo"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  INVERT_COLORS("md-invert_colors"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  INVERT_COLORS_OFF("md-invert_colors_off"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  ISO("md-iso"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  KEYBOARD("md-keyboard"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  KEYBOARD_ARROW_DOWN("md-keyboard_arrow_down"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  KEYBOARD_ARROW_LEFT("md-keyboard_arrow_left"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  KEYBOARD_ARROW_RIGHT("md-keyboard_arrow_right"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  KEYBOARD_ARROW_UP("md-keyboard_arrow_up"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  KEYBOARD_BACKSPACE("md-keyboard_backspace"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  KEYBOARD_CAPSLOCK("md-keyboard_capslock"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  KEYBOARD_HIDE("md-keyboard_hide"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  KEYBOARD_RETURN("md-keyboard_return"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  KEYBOARD_TAB("md-keyboard_tab"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  KEYBOARD_VOICE("md-keyboard_voice"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  KITCHEN("md-kitchen"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  LABEL("md-label"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  LABEL_OUTLINE("md-label_outline"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  LANDSCAPE("md-landscape"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  LANGUAGE("md-language"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  LAPTOP("md-laptop"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  LAPTOP_CHROMEBOOK("md-laptop_chromebook"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  LAPTOP_MAC("md-laptop_mac"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  LAPTOP_WINDOWS("md-laptop_windows"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  LAST_PAGE("md-last_page"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  LAUNCH("md-launch"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  LAYERS("md-layers"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  LAYERS_CLEAR("md-layers_clear"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  LEAK_ADD("md-leak_add"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  LEAK_REMOVE("md-leak_remove"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  LENS("md-lens"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  LIBRARY_ADD("md-library_add"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  LIBRARY_BOOKS("md-library_books"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  LIBRARY_MUSIC("md-library_music"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  LIGHTBULB_OUTLINE("md-lightbulb_outline"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  LINE_STYLE("md-line_style"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  LINE_WEIGHT("md-line_weight"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  LINEAR_SCALE("md-linear_scale"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  LINK("md-link"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  LINKED_CAMERA("md-linked_camera"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  LIST("md-list"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  LIVE_HELP("md-live_help"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  LIVE_TV("md-live_tv"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  LOCAL_ACTIVITY("md-local_activity"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  LOCAL_AIRPORT("md-local_airport"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  LOCAL_ATM("md-local_atm"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  LOCAL_BAR("md-local_bar"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  LOCAL_CAFE("md-local_cafe"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  LOCAL_CAR_WASH("md-local_car_wash"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  LOCAL_CONVENIENCE_STORE("md-local_convenience_store"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  LOCAL_DINING("md-local_dining"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  LOCAL_DRINK("md-local_drink"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  LOCAL_FLORIST("md-local_florist"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  LOCAL_GAS_STATION("md-local_gas_station"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  LOCAL_GROCERY_STORE("md-local_grocery_store"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  LOCAL_HOSPITAL("md-local_hospital"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  LOCAL_HOTEL("md-local_hotel"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  LOCAL_LAUNDRY_SERVICE("md-local_laundry_service"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  LOCAL_LIBRARY("md-local_library"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  LOCAL_MALL("md-local_mall"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  LOCAL_MOVIES("md-local_movies"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  LOCAL_OFFER("md-local_offer"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  LOCAL_PARKING("md-local_parking"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  LOCAL_PHARMACY("md-local_pharmacy"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  LOCAL_PHONE("md-local_phone"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  LOCAL_PIZZA("md-local_pizza"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  LOCAL_PLAY("md-local_play"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  LOCAL_POST_OFFICE("md-local_post_office"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  LOCAL_PRINTSHOP("md-local_printshop"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  LOCAL_SEE("md-local_see"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  LOCAL_SHIPPING("md-local_shipping"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  LOCAL_TAXI("md-local_taxi"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  LOCATION_CITY("md-location_city"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  LOCATION_DISABLED("md-location_disabled"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  LOCATION_OFF("md-location_off"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  LOCATION_ON("md-location_on"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  LOCATION_SEARCHING("md-location_searching"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  LOCK("md-lock"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  LOCK_OPEN("md-lock_open"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  LOCK_OUTLINE("md-lock_outline"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  LOOKS("md-looks"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  LOOKS_3("md-looks_3"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  LOOKS_4("md-looks_4"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  LOOKS_5("md-looks_5"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  LOOKS_6("md-looks_6"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  LOOKS_ONE("md-looks_one"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  LOOKS_TWO("md-looks_two"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  LOOP("md-loop"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  LOUPE("md-loupe"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  LOW_PRIORITY("md-low_priority"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  LOYALTY("md-loyalty"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  MAIL("md-mail"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  MAIL_OUTLINE("md-mail_outline"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  MAP("md-map"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  MARKUNREAD("md-markunread"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  MARKUNREAD_MAILBOX("md-markunread_mailbox"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  MEMORY("md-memory"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  MENU("md-menu"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  MERGE_TYPE("md-merge_type"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  MESSAGE("md-message"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  MIC("md-mic"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  MIC_NONE("md-mic_none"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  MIC_OFF("md-mic_off"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  MMS("md-mms"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  MODE_COMMENT("md-mode_comment"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  MODE_EDIT("md-mode_edit"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  MONETIZATION_ON("md-monetization_on"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  MONEY_OFF("md-money_off"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  MONOCHROME_PHOTOS("md-monochrome_photos"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  MOOD("md-mood"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  MOOD_BAD("md-mood_bad"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  MORE("md-more"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  MORE_HORIZ("md-more_horiz"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  MORE_VERT("md-more_vert"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  MOTORCYCLE("md-motorcycle"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  MOUSE("md-mouse"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  MOVE_TO_INBOX("md-move_to_inbox"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  MOVIE("md-movie"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  MOVIE_CREATION("md-movie_creation"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  MOVIE_FILTER("md-movie_filter"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  MULTILINE_CHART("md-multiline_chart"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  MUSIC_NOTE("md-music_note"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  MUSIC_VIDEO("md-music_video"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  MY_LOCATION("md-my_location"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  NATURE("md-nature"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  NATURE_PEOPLE("md-nature_people"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  NAVIGATE_BEFORE("md-navigate_before"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  NAVIGATE_NEXT("md-navigate_next"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  NAVIGATION("md-navigation"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  NEAR_ME("md-near_me"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  NETWORK_CELL("md-network_cell"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  NETWORK_CHECK("md-network_check"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  NETWORK_LOCKED("md-network_locked"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  NETWORK_WIFI("md-network_wifi"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  NEW_RELEASES("md-new_releases"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  NEXT_WEEK("md-next_week"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  NFC("md-nfc"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  NO_ENCRYPTION("md-no_encryption"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  NO_SIM("md-no_sim"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  NOT_INTERESTED("md-not_interested"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  NOTE("md-note"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  NOTE_ADD("md-note_add"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  NOTIFICATIONS("md-notifications"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  NOTIFICATIONS_ACTIVE("md-notifications_active"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  NOTIFICATIONS_NONE("md-notifications_none"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  NOTIFICATIONS_OFF("md-notifications_off"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  NOTIFICATIONS_PAUSED("md-notifications_paused"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  OFFLINE_PIN("md-offline_pin"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  ONDEMAND_VIDEO("md-ondemand_video"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  OPACITY("md-opacity"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  OPEN_IN_BROWSER("md-open_in_browser"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  OPEN_IN_NEW("md-open_in_new"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  OPEN_WITH("md-open_with"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  PAGES("md-pages"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  PAGEVIEW("md-pageview"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  PALETTE("md-palette"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  PAN_TOOL("md-pan_tool"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  PANORAMA("md-panorama"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  PANORAMA_FISH_EYE("md-panorama_fish_eye"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  PANORAMA_HORIZONTAL("md-panorama_horizontal"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  PANORAMA_VERTICAL("md-panorama_vertical"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  PANORAMA_WIDE_ANGLE("md-panorama_wide_angle"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  PARTY_MODE("md-party_mode"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  PAUSE("md-pause"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  PAUSE_CIRCLE_FILLED("md-pause_circle_filled"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  PAUSE_CIRCLE_OUTLINE("md-pause_circle_outline"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  PAYMENT("md-payment"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  PEOPLE("md-people"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  PEOPLE_OUTLINE("md-people_outline"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  PERM_CAMERA_MIC("md-perm_camera_mic"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  PERM_CONTACT_CALENDAR("md-perm_contact_calendar"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  PERM_DATA_SETTING("md-perm_data_setting"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  PERM_DEVICE_INFORMATION("md-perm_device_information"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  PERM_IDENTITY("md-perm_identity"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  PERM_MEDIA("md-perm_media"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  PERM_PHONE_MSG("md-perm_phone_msg"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  PERM_SCAN_WIFI("md-perm_scan_wifi"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  PERSON("md-person"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  PERSON_ADD("md-person_add"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  PERSON_OUTLINE("md-person_outline"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  PERSON_PIN("md-person_pin"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  PERSON_PIN_CIRCLE("md-person_pin_circle"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  PERSONAL_VIDEO("md-personal_video"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  PETS("md-pets"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  PHONE("md-phone"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  PHONE_ANDROID("md-phone_android"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  PHONE_BLUETOOTH_SPEAKER("md-phone_bluetooth_speaker"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  PHONE_FORWARDED("md-phone_forwarded"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  PHONE_IN_TALK("md-phone_in_talk"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  PHONE_IPHONE("md-phone_iphone"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  PHONE_LOCKED("md-phone_locked"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  PHONE_MISSED("md-phone_missed"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  PHONE_PAUSED("md-phone_paused"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  PHONELINK("md-phonelink"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  PHONELINK_ERASE("md-phonelink_erase"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  PHONELINK_LOCK("md-phonelink_lock"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  PHONELINK_OFF("md-phonelink_off"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  PHONELINK_RING("md-phonelink_ring"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  PHONELINK_SETUP("md-phonelink_setup"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  PHOTO("md-photo"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  PHOTO_ALBUM("md-photo_album"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  PHOTO_CAMERA("md-photo_camera"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  PHOTO_FILTER("md-photo_filter"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  PHOTO_LIBRARY("md-photo_library"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  PHOTO_SIZE_SELECT_ACTUAL("md-photo_size_select_actual"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  PHOTO_SIZE_SELECT_LARGE("md-photo_size_select_large"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  PHOTO_SIZE_SELECT_SMALL("md-photo_size_select_small"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  PICTURE_AS_PDF("md-picture_as_pdf"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  PICTURE_IN_PICTURE("md-picture_in_picture"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  PICTURE_IN_PICTURE_ALT("md-picture_in_picture_alt"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  PIE_CHART("md-pie_chart"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  PIE_CHART_OUTLINED("md-pie_chart_outlined"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  PIN_DROP("md-pin_drop"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  PLACE("md-place"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  PLAY_ARROW("md-play_arrow"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  PLAY_CIRCLE_FILLED("md-play_circle_filled"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  PLAY_CIRCLE_OUTLINE("md-play_circle_outline"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  PLAY_FOR_WORK("md-play_for_work"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  PLAYLIST_ADD("md-playlist_add"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  PLAYLIST_ADD_CHECK("md-playlist_add_check"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  PLAYLIST_PLAY("md-playlist_play"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  PLUS_ONE("md-plus_one"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  POLL("md-poll"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  POLYMER("md-polymer"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  POOL("md-pool"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  PORTABLE_WIFI_OFF("md-portable_wifi_off"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  PORTRAIT("md-portrait"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  POWER("md-power"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  POWER_INPUT("md-power_input"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  POWER_SETTINGS_NEW("md-power_settings_new"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  PREGNANT_WOMAN("md-pregnant_woman"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  PRESENT_TO_ALL("md-present_to_all"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  PRINT("md-print"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  PRIORITY_HIGH("md-priority_high"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  PUBLIC("md-public"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  PUBLISH("md-publish"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  QUERY_BUILDER("md-query_builder"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  QUESTION_ANSWER("md-question_answer"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  QUEUE("md-queue"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  QUEUE_MUSIC("md-queue_music"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  QUEUE_PLAY_NEXT("md-queue_play_next"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  RADIO("md-radio"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  RADIO_BUTTON_CHECKED("md-radio_button_checked"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  RADIO_BUTTON_UNCHECKED("md-radio_button_unchecked"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  RATE_REVIEW("md-rate_review"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  RECEIPT("md-receipt"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  RECENT_ACTORS("md-recent_actors"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  RECORD_VOICE_OVER("md-record_voice_over"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  REDEEM("md-redeem"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  REDO("md-redo"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  REFRESH("md-refresh"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  REMOVE("md-remove"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  REMOVE_CIRCLE("md-remove_circle"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  REMOVE_CIRCLE_OUTLINE("md-remove_circle_outline"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  REMOVE_FROM_QUEUE("md-remove_from_queue"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  REMOVE_RED_EYE("md-remove_red_eye"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  REMOVE_SHOPPING_CART("md-remove_shopping_cart"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  REORDER("md-reorder"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  REPEAT("md-repeat"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  REPEAT_ONE("md-repeat_one"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  REPLAY("md-replay"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  REPLAY_10("md-replay_10"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  REPLAY_30("md-replay_30"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  REPLAY_5("md-replay_5"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  REPLY("md-reply"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  REPLY_ALL("md-reply_all"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  REPORT("md-report"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  REPORT_PROBLEM("md-report_problem"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  RESTAURANT("md-restaurant"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  RESTAURANT_MENU("md-restaurant_menu"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  RESTORE("md-restore"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  RESTORE_PAGE("md-restore_page"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  RING_VOLUME("md-ring_volume"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  ROOM("md-room"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  ROOM_SERVICE("md-room_service"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  ROTATE_90_DEGREES_CCW("md-rotate_90_degrees_ccw"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  ROTATE_LEFT("md-rotate_left"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  ROTATE_RIGHT("md-rotate_right"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  ROUNDED_CORNER("md-rounded_corner"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  ROUTER("md-router"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  ROWING("md-rowing"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  RSS_FEED("md-rss_feed"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  RV_HOOKUP("md-rv_hookup"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  SATELLITE("md-satellite"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  SAVE("md-save"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  SCANNER("md-scanner"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  SCHEDULE("md-schedule"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  SCHOOL("md-school"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  SCREEN_LOCK_LANDSCAPE("md-screen_lock_landscape"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  SCREEN_LOCK_PORTRAIT("md-screen_lock_portrait"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  SCREEN_LOCK_ROTATION("md-screen_lock_rotation"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  SCREEN_ROTATION("md-screen_rotation"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  SCREEN_SHARE("md-screen_share"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  SD_CARD("md-sd_card"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  SD_STORAGE("md-sd_storage"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  SEARCH("md-search"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  SECURITY("md-security"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  SELECT_ALL("md-select_all"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  SEND("md-send"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  SENTIMENT_DISSATISFIED("md-sentiment_dissatisfied"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  SENTIMENT_NEUTRAL("md-sentiment_neutral"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  SENTIMENT_SATISFIED("md-sentiment_satisfied"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  SENTIMENT_VERY_DISSATISFIED("md-sentiment_very_dissatisfied"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  SENTIMENT_VERY_SATISFIED("md-sentiment_very_satisfied"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  SETTINGS("md-settings"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  SETTINGS_APPLICATIONS("md-settings_applications"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  SETTINGS_BACKUP_RESTORE("md-settings_backup_restore"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  SETTINGS_BLUETOOTH("md-settings_bluetooth"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  SETTINGS_BRIGHTNESS("md-settings_brightness"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  SETTINGS_CELL("md-settings_cell"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  SETTINGS_ETHERNET("md-settings_ethernet"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  SETTINGS_INPUT_ANTENNA("md-settings_input_antenna"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  SETTINGS_INPUT_COMPONENT("md-settings_input_component"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  SETTINGS_INPUT_COMPOSITE("md-settings_input_composite"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  SETTINGS_INPUT_HDMI("md-settings_input_hdmi"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  SETTINGS_INPUT_SVIDEO("md-settings_input_svideo"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  SETTINGS_OVERSCAN("md-settings_overscan"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  SETTINGS_PHONE("md-settings_phone"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  SETTINGS_POWER("md-settings_power"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  SETTINGS_REMOTE("md-settings_remote"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  SETTINGS_SYSTEM_DAYDREAM("md-settings_system_daydream"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  SETTINGS_VOICE("md-settings_voice"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  SHARE("md-share"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  SHOP("md-shop"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  SHOP_TWO("md-shop_two"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  SHOPPING_BASKET("md-shopping_basket"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  SHOPPING_CART("md-shopping_cart"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  SHORT_TEXT("md-short_text"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  SHOW_CHART("md-show_chart"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  SHUFFLE("md-shuffle"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  SIGNAL_CELLULAR_4_BAR("md-signal_cellular_4_bar"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  SIGNAL_CELLULAR_CONNECTED_NO_INTERNET_4_BAR("md-signal_cellular_connected_no_internet_4_bar"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  SIGNAL_CELLULAR_NO_SIM("md-signal_cellular_no_sim"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  SIGNAL_CELLULAR_NULL("md-signal_cellular_null"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  SIGNAL_CELLULAR_OFF("md-signal_cellular_off"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  SIGNAL_WIFI_4_BAR("md-signal_wifi_4_bar"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  SIGNAL_WIFI_4_BAR_LOCK("md-signal_wifi_4_bar_lock"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  SIGNAL_WIFI_OFF("md-signal_wifi_off"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  SIM_CARD("md-sim_card"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  SIM_CARD_ALERT("md-sim_card_alert"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  SKIP_NEXT("md-skip_next"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  SKIP_PREVIOUS("md-skip_previous"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  SLIDESHOW("md-slideshow"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  SLOW_MOTION_VIDEO("md-slow_motion_video"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  SMARTPHONE("md-smartphone"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  SMOKE_FREE("md-smoke_free"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  SMOKING_ROOMS("md-smoking_rooms"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  SMS("md-sms"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  SMS_FAILED("md-sms_failed"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  SNOOZE("md-snooze"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  SORT("md-sort"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  SORT_BY_ALPHA("md-sort_by_alpha"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  SPA("md-spa"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  SPACE_BAR("md-space_bar"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  SPEAKER("md-speaker"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  SPEAKER_GROUP("md-speaker_group"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  SPEAKER_NOTES("md-speaker_notes"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  SPEAKER_NOTES_OFF("md-speaker_notes_off"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  SPEAKER_PHONE("md-speaker_phone"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  SPELLCHECK("md-spellcheck"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  STAR("md-star"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  STAR_BORDER("md-star_border"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  STAR_HALF("md-star_half"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  STARS("md-stars"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  STAY_CURRENT_LANDSCAPE("md-stay_current_landscape"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  STAY_CURRENT_PORTRAIT("md-stay_current_portrait"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  STAY_PRIMARY_LANDSCAPE("md-stay_primary_landscape"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  STAY_PRIMARY_PORTRAIT("md-stay_primary_portrait"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  STOP("md-stop"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  STOP_SCREEN_SHARE("md-stop_screen_share"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  STORAGE("md-storage"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  STORE("md-store"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  STORE_MALL_DIRECTORY("md-store_mall_directory"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  STRAIGHTEN("md-straighten"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  STREETVIEW("md-streetview"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  STRIKETHROUGH_S("md-strikethrough_s"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  STYLE("md-style"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  SUBDIRECTORY_ARROW_LEFT("md-subdirectory_arrow_left"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  SUBDIRECTORY_ARROW_RIGHT("md-subdirectory_arrow_right"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  SUBJECT("md-subject"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  SUBSCRIPTIONS("md-subscriptions"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  SUBTITLES("md-subtitles"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  SUBWAY("md-subway"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  SUPERVISOR_ACCOUNT("md-supervisor_account"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  SURROUND_SOUND("md-surround_sound"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  SWAP_CALLS("md-swap_calls"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  SWAP_HORIZ("md-swap_horiz"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  SWAP_VERT("md-swap_vert"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  SWAP_VERTICAL_CIRCLE("md-swap_vertical_circle"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  SWITCH_CAMERA("md-switch_camera"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  SWITCH_VIDEO("md-switch_video"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  SYNC("md-sync"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  SYNC_DISABLED("md-sync_disabled"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  SYNC_PROBLEM("md-sync_problem"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  SYSTEM_UPDATE("md-system_update"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  SYSTEM_UPDATE_ALT("md-system_update_alt"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  TAB("md-tab"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  TAB_UNSELECTED("md-tab_unselected"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  TABLET("md-tablet"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  TABLET_ANDROID("md-tablet_android"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  TABLET_MAC("md-tablet_mac"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  TAG_FACES("md-tag_faces"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  TAP_AND_PLAY("md-tap_and_play"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  TERRAIN("md-terrain"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  TEXT_FIELDS("md-text_fields"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  TEXT_FORMAT("md-text_format"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  TEXTSMS("md-textsms"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  TEXTURE("md-texture"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  THEATERS("md-theaters"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  THUMB_DOWN("md-thumb_down"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  THUMB_UP("md-thumb_up"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  THUMBS_UP_DOWN("md-thumbs_up_down"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  TIME_TO_LEAVE("md-time_to_leave"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  TIMELAPSE("md-timelapse"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  TIMELINE("md-timeline"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  TIMER("md-timer"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  TIMER_10("md-timer_10"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  TIMER_3("md-timer_3"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  TIMER_OFF("md-timer_off"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  TITLE("md-title"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  TOC("md-toc"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  TODAY("md-today"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  TOLL("md-toll"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  TONALITY("md-tonality"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  TOUCH_APP("md-touch_app"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  TOYS("md-toys"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  TRACK_CHANGES("md-track_changes"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  TRAFFIC("md-traffic"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  TRAIN("md-train"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  TRAM("md-tram"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  TRANSFER_WITHIN_A_STATION("md-transfer_within_a_station"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  TRANSFORM("md-transform"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  TRANSLATE("md-translate"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  TRENDING_DOWN("md-trending_down"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  TRENDING_FLAT("md-trending_flat"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  TRENDING_UP("md-trending_up"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  TUNE("md-tune"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  TURNED_IN("md-turned_in"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  TURNED_IN_NOT("md-turned_in_not"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  TV("md-tv"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  UNARCHIVE("md-unarchive"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  UNDO("md-undo"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  UNFOLD_LESS("md-unfold_less"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  UNFOLD_MORE("md-unfold_more"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  UPDATE("md-update"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  USB("md-usb"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  VERIFIED_USER("md-verified_user"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  VERTICAL_ALIGN_BOTTOM("md-vertical_align_bottom"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  VERTICAL_ALIGN_CENTER("md-vertical_align_center"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  VERTICAL_ALIGN_TOP("md-vertical_align_top"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  VIBRATION("md-vibration"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  VIDEO_CALL("md-video_call"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  VIDEO_LABEL("md-video_label"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  VIDEO_LIBRARY("md-video_library"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  VIDEOCAM("md-videocam"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  VIDEOCAM_OFF("md-videocam_off"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  VIDEOGAME_ASSET("md-videogame_asset"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  VIEW_AGENDA("md-view_agenda"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  VIEW_ARRAY("md-view_array"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  VIEW_CAROUSEL("md-view_carousel"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  VIEW_COLUMN("md-view_column"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  VIEW_COMFY("md-view_comfy"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  VIEW_COMPACT("md-view_compact"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  VIEW_DAY("md-view_day"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  VIEW_HEADLINE("md-view_headline"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  VIEW_LIST("md-view_list"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  VIEW_MODULE("md-view_module"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  VIEW_QUILT("md-view_quilt"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  VIEW_STREAM("md-view_stream"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  VIEW_WEEK("md-view_week"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  VIGNETTE("md-vignette"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  VISIBILITY("md-visibility"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  VISIBILITY_OFF("md-visibility_off"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  VOICE_CHAT("md-voice_chat"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  VOICEMAIL("md-voicemail"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  VOLUME_DOWN("md-volume_down"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  VOLUME_MUTE("md-volume_mute"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  VOLUME_OFF("md-volume_off"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  VOLUME_UP("md-volume_up"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  VPN_KEY("md-vpn_key"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  VPN_LOCK("md-vpn_lock"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  WALLPAPER("md-wallpaper"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  WARNING("md-warning"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  WATCH("md-watch"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  WATCH_LATER("md-watch_later"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  WB_AUTO("md-wb_auto"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  WB_CLOUDY("md-wb_cloudy"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  WB_INCANDESCENT("md-wb_incandescent"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  WB_IRIDESCENT("md-wb_iridescent"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  WB_SUNNY("md-wb_sunny"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  WC("md-wc"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  WEB("md-web"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  WEB_ASSET("md-web_asset"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  WEEKEND("md-weekend"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  WHATSHOT("md-whatshot"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  WIDGETS("md-widgets"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  WIFI("md-wifi"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  WIFI_LOCK("md-wifi_lock"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  WIFI_TETHERING("md-wifi_tethering"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  WORK("md-work"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  WRAP_TEXT("md-wrap_text"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  YOUTUBE_SEARCHED_FOR("md-youtube_searched_for"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  ZOOM_IN("md-zoom_in"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  ZOOM_OUT("md-zoom_out"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  ZOOM_OUT_MAP("md-zoom_out_map");

  private final ICSSClassProvider m_aCSSClass;

  EMaterialDesignIcon (@NonNull @Nonempty final String sCSSClassName)
  {
    m_aCSSClass = DefaultCSSClassProvider.create (sCSSClassName);
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
    aElement.addClasses (CMaterialDesignCSS.MATERIAL_ICONS, m_aCSSClass);
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
  public HCI getAsNode18px ()
  {
    return getAsNode ().addClass (CMaterialDesignCSS.MD_18);
  }

  @Deprecated (forRemoval = true, since = "12.3.0")
  @NonNull
  public HCI getAsNode24px ()
  {
    return getAsNode ().addClass (CMaterialDesignCSS.MD_24);
  }

  @Deprecated (forRemoval = true, since = "12.3.0")
  @NonNull
  public HCI getAsNode36px ()
  {
    return getAsNode ().addClass (CMaterialDesignCSS.MD_36);
  }

  @Deprecated (forRemoval = true, since = "12.3.0")
  @NonNull
  public HCI getAsNode48px ()
  {
    return getAsNode ().addClass (CMaterialDesignCSS.MD_48);
  }

  @Deprecated (forRemoval = true, since = "12.3.0")
  @NonNull
  public HCI getAsNodeDark ()
  {
    return getAsNode ().addClass (CMaterialDesignCSS.MD_DARK);
  }

  @Deprecated (forRemoval = true, since = "12.3.0")
  @NonNull
  public HCI getAsNodeDarkInactive ()
  {
    return getAsNodeDark ().addClass (CMaterialDesignCSS.MD_INACTIVE);
  }

  @Deprecated (forRemoval = true, since = "12.3.0")
  @NonNull
  public HCI getAsNodeLight ()
  {
    return getAsNode ().addClass (CMaterialDesignCSS.MD_LIGHT);
  }

  @Deprecated (forRemoval = true, since = "12.3.0")
  @NonNull
  public HCI getAsNodeLightInactive ()
  {
    return getAsNodeLight ().addClass (CMaterialDesignCSS.MD_INACTIVE);
  }

  @Deprecated (forRemoval = true, since = "12.3.0")
  public static void setAsDefault ()
  {
    DefaultIcons.set (EDefaultIcon.ADD, ADD);
    DefaultIcons.set (EDefaultIcon.BACK, ARROW_BACK);
    DefaultIcons.set (EDefaultIcon.BACK_TO_LIST, ARROW_BACK);
    DefaultIcons.set (EDefaultIcon.CANCEL, CANCEL);
    DefaultIcons.set (EDefaultIcon.COPY, CONTENT_COPY);
    DefaultIcons.set (EDefaultIcon.DELETE, DELETE);
    DefaultIcons.set (EDefaultIcon.DOWN, ARROW_DOWNWARD);
    DefaultIcons.set (EDefaultIcon.EDIT, EDIT);
    DefaultIcons.set (EDefaultIcon.FORWARD, FORWARD);
    DefaultIcons.set (EDefaultIcon.HELP, HELP);
    DefaultIcons.set (EDefaultIcon.INFO, INFO);
    DefaultIcons.set (EDefaultIcon.KEY, LOCK);
    DefaultIcons.set (EDefaultIcon.MAGNIFIER, ZOOM_IN);
    DefaultIcons.set (EDefaultIcon.MINUS, DELETE);
    DefaultIcons.set (EDefaultIcon.NEW, BUILD);
    DefaultIcons.set (EDefaultIcon.NEXT, FORWARD);
    DefaultIcons.set (EDefaultIcon.NO, REMOVE);
    DefaultIcons.set (EDefaultIcon.PLUS, ADD);
    DefaultIcons.set (EDefaultIcon.REFRESH, REFRESH);
    DefaultIcons.set (EDefaultIcon.SAVE, SAVE);
    DefaultIcons.set (EDefaultIcon.SAVE_ALL, SAVE);
    DefaultIcons.set (EDefaultIcon.SAVE_AS, SAVE);
    DefaultIcons.set (EDefaultIcon.SAVE_CLOSE, SAVE);
    DefaultIcons.set (EDefaultIcon.SUBMIT, SAVE);
    DefaultIcons.set (EDefaultIcon.UNDELETE, UNDO);
    DefaultIcons.set (EDefaultIcon.UP, ARROW_UPWARD);
    DefaultIcons.set (EDefaultIcon.YES, CHECK);
  }

  @Deprecated (forRemoval = true, since = "12.3.0")
  @NonNull
  public static ICommonsList <ICSSPathProvider> getAllCSSFiles ()
  {
    return new CommonsArrayList <> (EIconCSSPathProvider.MATERIAL_ICONS,
                                    EIconCSSPathProvider.MATERIAL_ICONS_LIST,
                                    EIconCSSPathProvider.PH_OTON_MATERIAL_DESIGN);
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
