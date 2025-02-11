/*
 * Copyright (C) 2014-2025 Philip Helger (www.helger.com)
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

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.helger.commons.annotation.Nonempty;
import com.helger.commons.collection.impl.CommonsArrayList;
import com.helger.commons.collection.impl.ICommonsList;
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
public enum EMaterialDesignIcon implements IIcon
{
  _3D_ROTATION ("md-3d_rotation"),
  AC_UNIT ("md-ac_unit"),
  ACCESS_ALARM ("md-access_alarm"),
  ACCESS_ALARMS ("md-access_alarms"),
  ACCESS_TIME ("md-access_time"),
  ACCESSIBILITY ("md-accessibility"),
  ACCESSIBLE ("md-accessible"),
  ACCOUNT_BALANCE ("md-account_balance"),
  ACCOUNT_BALANCE_WALLET ("md-account_balance_wallet"),
  ACCOUNT_BOX ("md-account_box"),
  ACCOUNT_CIRCLE ("md-account_circle"),
  ADB ("md-adb"),
  ADD ("md-add"),
  ADD_A_PHOTO ("md-add_a_photo"),
  ADD_ALARM ("md-add_alarm"),
  ADD_ALERT ("md-add_alert"),
  ADD_BOX ("md-add_box"),
  ADD_CIRCLE ("md-add_circle"),
  ADD_CIRCLE_OUTLINE ("md-add_circle_outline"),
  ADD_LOCATION ("md-add_location"),
  ADD_SHOPPING_CART ("md-add_shopping_cart"),
  ADD_TO_PHOTOS ("md-add_to_photos"),
  ADD_TO_QUEUE ("md-add_to_queue"),
  ADJUST ("md-adjust"),
  AIRLINE_SEAT_FLAT ("md-airline_seat_flat"),
  AIRLINE_SEAT_FLAT_ANGLED ("md-airline_seat_flat_angled"),
  AIRLINE_SEAT_INDIVIDUAL_SUITE ("md-airline_seat_individual_suite"),
  AIRLINE_SEAT_LEGROOM_EXTRA ("md-airline_seat_legroom_extra"),
  AIRLINE_SEAT_LEGROOM_NORMAL ("md-airline_seat_legroom_normal"),
  AIRLINE_SEAT_LEGROOM_REDUCED ("md-airline_seat_legroom_reduced"),
  AIRLINE_SEAT_RECLINE_EXTRA ("md-airline_seat_recline_extra"),
  AIRLINE_SEAT_RECLINE_NORMAL ("md-airline_seat_recline_normal"),
  AIRPLANEMODE_ACTIVE ("md-airplanemode_active"),
  AIRPLANEMODE_INACTIVE ("md-airplanemode_inactive"),
  AIRPLAY ("md-airplay"),
  AIRPORT_SHUTTLE ("md-airport_shuttle"),
  ALARM ("md-alarm"),
  ALARM_ADD ("md-alarm_add"),
  ALARM_OFF ("md-alarm_off"),
  ALARM_ON ("md-alarm_on"),
  ALBUM ("md-album"),
  ALL_INCLUSIVE ("md-all_inclusive"),
  ALL_OUT ("md-all_out"),
  ANDROID ("md-android"),
  ANNOUNCEMENT ("md-announcement"),
  APPS ("md-apps"),
  ARCHIVE ("md-archive"),
  ARROW_BACK ("md-arrow_back"),
  ARROW_DOWNWARD ("md-arrow_downward"),
  ARROW_DROP_DOWN ("md-arrow_drop_down"),
  ARROW_DROP_DOWN_CIRCLE ("md-arrow_drop_down_circle"),
  ARROW_DROP_UP ("md-arrow_drop_up"),
  ARROW_FORWARD ("md-arrow_forward"),
  ARROW_UPWARD ("md-arrow_upward"),
  ART_TRACK ("md-art_track"),
  ASPECT_RATIO ("md-aspect_ratio"),
  ASSESSMENT ("md-assessment"),
  ASSIGNMENT ("md-assignment"),
  ASSIGNMENT_IND ("md-assignment_ind"),
  ASSIGNMENT_LATE ("md-assignment_late"),
  ASSIGNMENT_RETURN ("md-assignment_return"),
  ASSIGNMENT_RETURNED ("md-assignment_returned"),
  ASSIGNMENT_TURNED_IN ("md-assignment_turned_in"),
  ASSISTANT ("md-assistant"),
  ASSISTANT_PHOTO ("md-assistant_photo"),
  ATTACH_FILE ("md-attach_file"),
  ATTACH_MONEY ("md-attach_money"),
  ATTACHMENT ("md-attachment"),
  AUDIOTRACK ("md-audiotrack"),
  AUTORENEW ("md-autorenew"),
  AV_TIMER ("md-av_timer"),
  BACKSPACE ("md-backspace"),
  BACKUP ("md-backup"),
  BATTERY_ALERT ("md-battery_alert"),
  BATTERY_CHARGING_FULL ("md-battery_charging_full"),
  BATTERY_FULL ("md-battery_full"),
  BATTERY_STD ("md-battery_std"),
  BATTERY_UNKNOWN ("md-battery_unknown"),
  BEACH_ACCESS ("md-beach_access"),
  BEENHERE ("md-beenhere"),
  BLOCK ("md-block"),
  BLUETOOTH ("md-bluetooth"),
  BLUETOOTH_AUDIO ("md-bluetooth_audio"),
  BLUETOOTH_CONNECTED ("md-bluetooth_connected"),
  BLUETOOTH_DISABLED ("md-bluetooth_disabled"),
  BLUETOOTH_SEARCHING ("md-bluetooth_searching"),
  BLUR_CIRCULAR ("md-blur_circular"),
  BLUR_LINEAR ("md-blur_linear"),
  BLUR_OFF ("md-blur_off"),
  BLUR_ON ("md-blur_on"),
  BOOK ("md-book"),
  BOOKMARK ("md-bookmark"),
  BOOKMARK_BORDER ("md-bookmark_border"),
  BORDER_ALL ("md-border_all"),
  BORDER_BOTTOM ("md-border_bottom"),
  BORDER_CLEAR ("md-border_clear"),
  BORDER_COLOR ("md-border_color"),
  BORDER_HORIZONTAL ("md-border_horizontal"),
  BORDER_INNER ("md-border_inner"),
  BORDER_LEFT ("md-border_left"),
  BORDER_OUTER ("md-border_outer"),
  BORDER_RIGHT ("md-border_right"),
  BORDER_STYLE ("md-border_style"),
  BORDER_TOP ("md-border_top"),
  BORDER_VERTICAL ("md-border_vertical"),
  BRANDING_WATERMARK ("md-branding_watermark"),
  BRIGHTNESS_1 ("md-brightness_1"),
  BRIGHTNESS_2 ("md-brightness_2"),
  BRIGHTNESS_3 ("md-brightness_3"),
  BRIGHTNESS_4 ("md-brightness_4"),
  BRIGHTNESS_5 ("md-brightness_5"),
  BRIGHTNESS_6 ("md-brightness_6"),
  BRIGHTNESS_7 ("md-brightness_7"),
  BRIGHTNESS_AUTO ("md-brightness_auto"),
  BRIGHTNESS_HIGH ("md-brightness_high"),
  BRIGHTNESS_LOW ("md-brightness_low"),
  BRIGHTNESS_MEDIUM ("md-brightness_medium"),
  BROKEN_IMAGE ("md-broken_image"),
  BRUSH ("md-brush"),
  BUBBLE_CHART ("md-bubble_chart"),
  BUG_REPORT ("md-bug_report"),
  BUILD ("md-build"),
  BURST_MODE ("md-burst_mode"),
  BUSINESS ("md-business"),
  BUSINESS_CENTER ("md-business_center"),
  CACHED ("md-cached"),
  CAKE ("md-cake"),
  CALL ("md-call"),
  CALL_END ("md-call_end"),
  CALL_MADE ("md-call_made"),
  CALL_MERGE ("md-call_merge"),
  CALL_MISSED ("md-call_missed"),
  CALL_MISSED_OUTGOING ("md-call_missed_outgoing"),
  CALL_RECEIVED ("md-call_received"),
  CALL_SPLIT ("md-call_split"),
  CALL_TO_ACTION ("md-call_to_action"),
  CAMERA ("md-camera"),
  CAMERA_ALT ("md-camera_alt"),
  CAMERA_ENHANCE ("md-camera_enhance"),
  CAMERA_FRONT ("md-camera_front"),
  CAMERA_REAR ("md-camera_rear"),
  CAMERA_ROLL ("md-camera_roll"),
  CANCEL ("md-cancel"),
  CARD_GIFTCARD ("md-card_giftcard"),
  CARD_MEMBERSHIP ("md-card_membership"),
  CARD_TRAVEL ("md-card_travel"),
  CASINO ("md-casino"),
  CAST ("md-cast"),
  CAST_CONNECTED ("md-cast_connected"),
  CENTER_FOCUS_STRONG ("md-center_focus_strong"),
  CENTER_FOCUS_WEAK ("md-center_focus_weak"),
  CHANGE_HISTORY ("md-change_history"),
  CHAT ("md-chat"),
  CHAT_BUBBLE ("md-chat_bubble"),
  CHAT_BUBBLE_OUTLINE ("md-chat_bubble_outline"),
  CHECK ("md-check"),
  CHECK_BOX ("md-check_box"),
  CHECK_BOX_OUTLINE_BLANK ("md-check_box_outline_blank"),
  CHECK_CIRCLE ("md-check_circle"),
  CHEVRON_LEFT ("md-chevron_left"),
  CHEVRON_RIGHT ("md-chevron_right"),
  CHILD_CARE ("md-child_care"),
  CHILD_FRIENDLY ("md-child_friendly"),
  CHROME_READER_MODE ("md-chrome_reader_mode"),
  CLASS ("md-class"),
  CLEAR ("md-clear"),
  CLEAR_ALL ("md-clear_all"),
  CLOSE ("md-close"),
  CLOSED_CAPTION ("md-closed_caption"),
  CLOUD ("md-cloud"),
  CLOUD_CIRCLE ("md-cloud_circle"),
  CLOUD_DONE ("md-cloud_done"),
  CLOUD_DOWNLOAD ("md-cloud_download"),
  CLOUD_OFF ("md-cloud_off"),
  CLOUD_QUEUE ("md-cloud_queue"),
  CLOUD_UPLOAD ("md-cloud_upload"),
  CODE ("md-code"),
  COLLECTIONS ("md-collections"),
  COLLECTIONS_BOOKMARK ("md-collections_bookmark"),
  COLOR_LENS ("md-color_lens"),
  COLORIZE ("md-colorize"),
  COMMENT ("md-comment"),
  COMPARE ("md-compare"),
  COMPARE_ARROWS ("md-compare_arrows"),
  COMPUTER ("md-computer"),
  CONFIRMATION_NUMBER ("md-confirmation_number"),
  CONTACT_MAIL ("md-contact_mail"),
  CONTACT_PHONE ("md-contact_phone"),
  CONTACTS ("md-contacts"),
  CONTENT_COPY ("md-content_copy"),
  CONTENT_CUT ("md-content_cut"),
  CONTENT_PASTE ("md-content_paste"),
  CONTROL_POINT ("md-control_point"),
  CONTROL_POINT_DUPLICATE ("md-control_point_duplicate"),
  COPYRIGHT ("md-copyright"),
  CREATE ("md-create"),
  CREATE_NEW_FOLDER ("md-create_new_folder"),
  CREDIT_CARD ("md-credit_card"),
  CROP ("md-crop"),
  CROP_16_9 ("md-crop_16_9"),
  CROP_3_2 ("md-crop_3_2"),
  CROP_5_4 ("md-crop_5_4"),
  CROP_7_5 ("md-crop_7_5"),
  CROP_DIN ("md-crop_din"),
  CROP_FREE ("md-crop_free"),
  CROP_LANDSCAPE ("md-crop_landscape"),
  CROP_ORIGINAL ("md-crop_original"),
  CROP_PORTRAIT ("md-crop_portrait"),
  CROP_ROTATE ("md-crop_rotate"),
  CROP_SQUARE ("md-crop_square"),
  DASHBOARD ("md-dashboard"),
  DATA_USAGE ("md-data_usage"),
  DATE_RANGE ("md-date_range"),
  DEHAZE ("md-dehaze"),
  DELETE ("md-delete"),
  DELETE_FOREVER ("md-delete_forever"),
  DELETE_SWEEP ("md-delete_sweep"),
  DESCRIPTION ("md-description"),
  DESKTOP_MAC ("md-desktop_mac"),
  DESKTOP_WINDOWS ("md-desktop_windows"),
  DETAILS ("md-details"),
  DEVELOPER_BOARD ("md-developer_board"),
  DEVELOPER_MODE ("md-developer_mode"),
  DEVICE_HUB ("md-device_hub"),
  DEVICES ("md-devices"),
  DEVICES_OTHER ("md-devices_other"),
  DIALER_SIP ("md-dialer_sip"),
  DIALPAD ("md-dialpad"),
  DIRECTIONS ("md-directions"),
  DIRECTIONS_BIKE ("md-directions_bike"),
  DIRECTIONS_BOAT ("md-directions_boat"),
  DIRECTIONS_BUS ("md-directions_bus"),
  DIRECTIONS_CAR ("md-directions_car"),
  DIRECTIONS_RAILWAY ("md-directions_railway"),
  DIRECTIONS_RUN ("md-directions_run"),
  DIRECTIONS_SUBWAY ("md-directions_subway"),
  DIRECTIONS_TRANSIT ("md-directions_transit"),
  DIRECTIONS_WALK ("md-directions_walk"),
  DISC_FULL ("md-disc_full"),
  DNS ("md-dns"),
  DO_NOT_DISTURB ("md-do_not_disturb"),
  DO_NOT_DISTURB_ALT ("md-do_not_disturb_alt"),
  DO_NOT_DISTURB_OFF ("md-do_not_disturb_off"),
  DO_NOT_DISTURB_ON ("md-do_not_disturb_on"),
  DOCK ("md-dock"),
  DOMAIN ("md-domain"),
  DONE ("md-done"),
  DONE_ALL ("md-done_all"),
  DONUT_LARGE ("md-donut_large"),
  DONUT_SMALL ("md-donut_small"),
  DRAFTS ("md-drafts"),
  DRAG_HANDLE ("md-drag_handle"),
  DRIVE_ETA ("md-drive_eta"),
  DVR ("md-dvr"),
  EDIT ("md-edit"),
  EDIT_LOCATION ("md-edit_location"),
  EJECT ("md-eject"),
  EMAIL ("md-email"),
  ENHANCED_ENCRYPTION ("md-enhanced_encryption"),
  EQUALIZER ("md-equalizer"),
  ERROR ("md-error"),
  ERROR_OUTLINE ("md-error_outline"),
  EURO_SYMBOL ("md-euro_symbol"),
  EV_STATION ("md-ev_station"),
  EVENT ("md-event"),
  EVENT_AVAILABLE ("md-event_available"),
  EVENT_BUSY ("md-event_busy"),
  EVENT_NOTE ("md-event_note"),
  EVENT_SEAT ("md-event_seat"),
  EXIT_TO_APP ("md-exit_to_app"),
  EXPAND_LESS ("md-expand_less"),
  EXPAND_MORE ("md-expand_more"),
  EXPLICIT ("md-explicit"),
  EXPLORE ("md-explore"),
  EXPOSURE ("md-exposure"),
  EXPOSURE_NEG_1 ("md-exposure_neg_1"),
  EXPOSURE_NEG_2 ("md-exposure_neg_2"),
  EXPOSURE_PLUS_1 ("md-exposure_plus_1"),
  EXPOSURE_PLUS_2 ("md-exposure_plus_2"),
  EXPOSURE_ZERO ("md-exposure_zero"),
  EXTENSION ("md-extension"),
  FACE ("md-face"),
  FAST_FORWARD ("md-fast_forward"),
  FAST_REWIND ("md-fast_rewind"),
  FAVORITE ("md-favorite"),
  FAVORITE_BORDER ("md-favorite_border"),
  FEATURED_PLAY_LIST ("md-featured_play_list"),
  FEATURED_VIDEO ("md-featured_video"),
  FEEDBACK ("md-feedback"),
  FIBER_DVR ("md-fiber_dvr"),
  FIBER_MANUAL_RECORD ("md-fiber_manual_record"),
  FIBER_NEW ("md-fiber_new"),
  FIBER_PIN ("md-fiber_pin"),
  FIBER_SMART_RECORD ("md-fiber_smart_record"),
  FILE_DOWNLOAD ("md-file_download"),
  FILE_UPLOAD ("md-file_upload"),
  FILTER ("md-filter"),
  FILTER_1 ("md-filter_1"),
  FILTER_2 ("md-filter_2"),
  FILTER_3 ("md-filter_3"),
  FILTER_4 ("md-filter_4"),
  FILTER_5 ("md-filter_5"),
  FILTER_6 ("md-filter_6"),
  FILTER_7 ("md-filter_7"),
  FILTER_8 ("md-filter_8"),
  FILTER_9 ("md-filter_9"),
  FILTER_9_PLUS ("md-filter_9_plus"),
  FILTER_B_AND_W ("md-filter_b_and_w"),
  FILTER_CENTER_FOCUS ("md-filter_center_focus"),
  FILTER_DRAMA ("md-filter_drama"),
  FILTER_FRAMES ("md-filter_frames"),
  FILTER_HDR ("md-filter_hdr"),
  FILTER_LIST ("md-filter_list"),
  FILTER_NONE ("md-filter_none"),
  FILTER_TILT_SHIFT ("md-filter_tilt_shift"),
  FILTER_VINTAGE ("md-filter_vintage"),
  FIND_IN_PAGE ("md-find_in_page"),
  FIND_REPLACE ("md-find_replace"),
  FINGERPRINT ("md-fingerprint"),
  FIRST_PAGE ("md-first_page"),
  FITNESS_CENTER ("md-fitness_center"),
  FLAG ("md-flag"),
  FLARE ("md-flare"),
  FLASH_AUTO ("md-flash_auto"),
  FLASH_OFF ("md-flash_off"),
  FLASH_ON ("md-flash_on"),
  FLIGHT ("md-flight"),
  FLIGHT_LAND ("md-flight_land"),
  FLIGHT_TAKEOFF ("md-flight_takeoff"),
  FLIP ("md-flip"),
  FLIP_TO_BACK ("md-flip_to_back"),
  FLIP_TO_FRONT ("md-flip_to_front"),
  FOLDER ("md-folder"),
  FOLDER_OPEN ("md-folder_open"),
  FOLDER_SHARED ("md-folder_shared"),
  FOLDER_SPECIAL ("md-folder_special"),
  FONT_DOWNLOAD ("md-font_download"),
  FORMAT_ALIGN_CENTER ("md-format_align_center"),
  FORMAT_ALIGN_JUSTIFY ("md-format_align_justify"),
  FORMAT_ALIGN_LEFT ("md-format_align_left"),
  FORMAT_ALIGN_RIGHT ("md-format_align_right"),
  FORMAT_BOLD ("md-format_bold"),
  FORMAT_CLEAR ("md-format_clear"),
  FORMAT_COLOR_FILL ("md-format_color_fill"),
  FORMAT_COLOR_RESET ("md-format_color_reset"),
  FORMAT_COLOR_TEXT ("md-format_color_text"),
  FORMAT_INDENT_DECREASE ("md-format_indent_decrease"),
  FORMAT_INDENT_INCREASE ("md-format_indent_increase"),
  FORMAT_ITALIC ("md-format_italic"),
  FORMAT_LINE_SPACING ("md-format_line_spacing"),
  FORMAT_LIST_BULLETED ("md-format_list_bulleted"),
  FORMAT_LIST_NUMBERED ("md-format_list_numbered"),
  FORMAT_PAINT ("md-format_paint"),
  FORMAT_QUOTE ("md-format_quote"),
  FORMAT_SHAPES ("md-format_shapes"),
  FORMAT_SIZE ("md-format_size"),
  FORMAT_STRIKETHROUGH ("md-format_strikethrough"),
  FORMAT_TEXTDIRECTION_L_TO_R ("md-format_textdirection_l_to_r"),
  FORMAT_TEXTDIRECTION_R_TO_L ("md-format_textdirection_r_to_l"),
  FORMAT_UNDERLINED ("md-format_underlined"),
  FORUM ("md-forum"),
  FORWARD ("md-forward"),
  FORWARD_10 ("md-forward_10"),
  FORWARD_30 ("md-forward_30"),
  FORWARD_5 ("md-forward_5"),
  FREE_BREAKFAST ("md-free_breakfast"),
  FULLSCREEN ("md-fullscreen"),
  FULLSCREEN_EXIT ("md-fullscreen_exit"),
  FUNCTIONS ("md-functions"),
  G_TRANSLATE ("md-g_translate"),
  GAMEPAD ("md-gamepad"),
  GAMES ("md-games"),
  GAVEL ("md-gavel"),
  GESTURE ("md-gesture"),
  GET_APP ("md-get_app"),
  GIF ("md-gif"),
  GOLF_COURSE ("md-golf_course"),
  GPS_FIXED ("md-gps_fixed"),
  GPS_NOT_FIXED ("md-gps_not_fixed"),
  GPS_OFF ("md-gps_off"),
  GRADE ("md-grade"),
  GRADIENT ("md-gradient"),
  GRAIN ("md-grain"),
  GRAPHIC_EQ ("md-graphic_eq"),
  GRID_OFF ("md-grid_off"),
  GRID_ON ("md-grid_on"),
  GROUP ("md-group"),
  GROUP_ADD ("md-group_add"),
  GROUP_WORK ("md-group_work"),
  HD ("md-hd"),
  HDR_OFF ("md-hdr_off"),
  HDR_ON ("md-hdr_on"),
  HDR_STRONG ("md-hdr_strong"),
  HDR_WEAK ("md-hdr_weak"),
  HEADSET ("md-headset"),
  HEADSET_MIC ("md-headset_mic"),
  HEALING ("md-healing"),
  HEARING ("md-hearing"),
  HELP ("md-help"),
  HELP_OUTLINE ("md-help_outline"),
  HIGH_QUALITY ("md-high_quality"),
  HIGHLIGHT ("md-highlight"),
  HIGHLIGHT_OFF ("md-highlight_off"),
  HISTORY ("md-history"),
  HOME ("md-home"),
  HOT_TUB ("md-hot_tub"),
  HOTEL ("md-hotel"),
  HOURGLASS_EMPTY ("md-hourglass_empty"),
  HOURGLASS_FULL ("md-hourglass_full"),
  HTTP ("md-http"),
  HTTPS ("md-https"),
  IMAGE ("md-image"),
  IMAGE_ASPECT_RATIO ("md-image_aspect_ratio"),
  IMPORT_CONTACTS ("md-import_contacts"),
  IMPORT_EXPORT ("md-import_export"),
  IMPORTANT_DEVICES ("md-important_devices"),
  INBOX ("md-inbox"),
  INDETERMINATE_CHECK_BOX ("md-indeterminate_check_box"),
  INFO ("md-info"),
  INFO_OUTLINE ("md-info_outline"),
  INPUT ("md-input"),
  INSERT_CHART ("md-insert_chart"),
  INSERT_COMMENT ("md-insert_comment"),
  INSERT_DRIVE_FILE ("md-insert_drive_file"),
  INSERT_EMOTICON ("md-insert_emoticon"),
  INSERT_INVITATION ("md-insert_invitation"),
  INSERT_LINK ("md-insert_link"),
  INSERT_PHOTO ("md-insert_photo"),
  INVERT_COLORS ("md-invert_colors"),
  INVERT_COLORS_OFF ("md-invert_colors_off"),
  ISO ("md-iso"),
  KEYBOARD ("md-keyboard"),
  KEYBOARD_ARROW_DOWN ("md-keyboard_arrow_down"),
  KEYBOARD_ARROW_LEFT ("md-keyboard_arrow_left"),
  KEYBOARD_ARROW_RIGHT ("md-keyboard_arrow_right"),
  KEYBOARD_ARROW_UP ("md-keyboard_arrow_up"),
  KEYBOARD_BACKSPACE ("md-keyboard_backspace"),
  KEYBOARD_CAPSLOCK ("md-keyboard_capslock"),
  KEYBOARD_HIDE ("md-keyboard_hide"),
  KEYBOARD_RETURN ("md-keyboard_return"),
  KEYBOARD_TAB ("md-keyboard_tab"),
  KEYBOARD_VOICE ("md-keyboard_voice"),
  KITCHEN ("md-kitchen"),
  LABEL ("md-label"),
  LABEL_OUTLINE ("md-label_outline"),
  LANDSCAPE ("md-landscape"),
  LANGUAGE ("md-language"),
  LAPTOP ("md-laptop"),
  LAPTOP_CHROMEBOOK ("md-laptop_chromebook"),
  LAPTOP_MAC ("md-laptop_mac"),
  LAPTOP_WINDOWS ("md-laptop_windows"),
  LAST_PAGE ("md-last_page"),
  LAUNCH ("md-launch"),
  LAYERS ("md-layers"),
  LAYERS_CLEAR ("md-layers_clear"),
  LEAK_ADD ("md-leak_add"),
  LEAK_REMOVE ("md-leak_remove"),
  LENS ("md-lens"),
  LIBRARY_ADD ("md-library_add"),
  LIBRARY_BOOKS ("md-library_books"),
  LIBRARY_MUSIC ("md-library_music"),
  LIGHTBULB_OUTLINE ("md-lightbulb_outline"),
  LINE_STYLE ("md-line_style"),
  LINE_WEIGHT ("md-line_weight"),
  LINEAR_SCALE ("md-linear_scale"),
  LINK ("md-link"),
  LINKED_CAMERA ("md-linked_camera"),
  LIST ("md-list"),
  LIVE_HELP ("md-live_help"),
  LIVE_TV ("md-live_tv"),
  LOCAL_ACTIVITY ("md-local_activity"),
  LOCAL_AIRPORT ("md-local_airport"),
  LOCAL_ATM ("md-local_atm"),
  LOCAL_BAR ("md-local_bar"),
  LOCAL_CAFE ("md-local_cafe"),
  LOCAL_CAR_WASH ("md-local_car_wash"),
  LOCAL_CONVENIENCE_STORE ("md-local_convenience_store"),
  LOCAL_DINING ("md-local_dining"),
  LOCAL_DRINK ("md-local_drink"),
  LOCAL_FLORIST ("md-local_florist"),
  LOCAL_GAS_STATION ("md-local_gas_station"),
  LOCAL_GROCERY_STORE ("md-local_grocery_store"),
  LOCAL_HOSPITAL ("md-local_hospital"),
  LOCAL_HOTEL ("md-local_hotel"),
  LOCAL_LAUNDRY_SERVICE ("md-local_laundry_service"),
  LOCAL_LIBRARY ("md-local_library"),
  LOCAL_MALL ("md-local_mall"),
  LOCAL_MOVIES ("md-local_movies"),
  LOCAL_OFFER ("md-local_offer"),
  LOCAL_PARKING ("md-local_parking"),
  LOCAL_PHARMACY ("md-local_pharmacy"),
  LOCAL_PHONE ("md-local_phone"),
  LOCAL_PIZZA ("md-local_pizza"),
  LOCAL_PLAY ("md-local_play"),
  LOCAL_POST_OFFICE ("md-local_post_office"),
  LOCAL_PRINTSHOP ("md-local_printshop"),
  LOCAL_SEE ("md-local_see"),
  LOCAL_SHIPPING ("md-local_shipping"),
  LOCAL_TAXI ("md-local_taxi"),
  LOCATION_CITY ("md-location_city"),
  LOCATION_DISABLED ("md-location_disabled"),
  LOCATION_OFF ("md-location_off"),
  LOCATION_ON ("md-location_on"),
  LOCATION_SEARCHING ("md-location_searching"),
  LOCK ("md-lock"),
  LOCK_OPEN ("md-lock_open"),
  LOCK_OUTLINE ("md-lock_outline"),
  LOOKS ("md-looks"),
  LOOKS_3 ("md-looks_3"),
  LOOKS_4 ("md-looks_4"),
  LOOKS_5 ("md-looks_5"),
  LOOKS_6 ("md-looks_6"),
  LOOKS_ONE ("md-looks_one"),
  LOOKS_TWO ("md-looks_two"),
  LOOP ("md-loop"),
  LOUPE ("md-loupe"),
  LOW_PRIORITY ("md-low_priority"),
  LOYALTY ("md-loyalty"),
  MAIL ("md-mail"),
  MAIL_OUTLINE ("md-mail_outline"),
  MAP ("md-map"),
  MARKUNREAD ("md-markunread"),
  MARKUNREAD_MAILBOX ("md-markunread_mailbox"),
  MEMORY ("md-memory"),
  MENU ("md-menu"),
  MERGE_TYPE ("md-merge_type"),
  MESSAGE ("md-message"),
  MIC ("md-mic"),
  MIC_NONE ("md-mic_none"),
  MIC_OFF ("md-mic_off"),
  MMS ("md-mms"),
  MODE_COMMENT ("md-mode_comment"),
  MODE_EDIT ("md-mode_edit"),
  MONETIZATION_ON ("md-monetization_on"),
  MONEY_OFF ("md-money_off"),
  MONOCHROME_PHOTOS ("md-monochrome_photos"),
  MOOD ("md-mood"),
  MOOD_BAD ("md-mood_bad"),
  MORE ("md-more"),
  MORE_HORIZ ("md-more_horiz"),
  MORE_VERT ("md-more_vert"),
  MOTORCYCLE ("md-motorcycle"),
  MOUSE ("md-mouse"),
  MOVE_TO_INBOX ("md-move_to_inbox"),
  MOVIE ("md-movie"),
  MOVIE_CREATION ("md-movie_creation"),
  MOVIE_FILTER ("md-movie_filter"),
  MULTILINE_CHART ("md-multiline_chart"),
  MUSIC_NOTE ("md-music_note"),
  MUSIC_VIDEO ("md-music_video"),
  MY_LOCATION ("md-my_location"),
  NATURE ("md-nature"),
  NATURE_PEOPLE ("md-nature_people"),
  NAVIGATE_BEFORE ("md-navigate_before"),
  NAVIGATE_NEXT ("md-navigate_next"),
  NAVIGATION ("md-navigation"),
  NEAR_ME ("md-near_me"),
  NETWORK_CELL ("md-network_cell"),
  NETWORK_CHECK ("md-network_check"),
  NETWORK_LOCKED ("md-network_locked"),
  NETWORK_WIFI ("md-network_wifi"),
  NEW_RELEASES ("md-new_releases"),
  NEXT_WEEK ("md-next_week"),
  NFC ("md-nfc"),
  NO_ENCRYPTION ("md-no_encryption"),
  NO_SIM ("md-no_sim"),
  NOT_INTERESTED ("md-not_interested"),
  NOTE ("md-note"),
  NOTE_ADD ("md-note_add"),
  NOTIFICATIONS ("md-notifications"),
  NOTIFICATIONS_ACTIVE ("md-notifications_active"),
  NOTIFICATIONS_NONE ("md-notifications_none"),
  NOTIFICATIONS_OFF ("md-notifications_off"),
  NOTIFICATIONS_PAUSED ("md-notifications_paused"),
  OFFLINE_PIN ("md-offline_pin"),
  ONDEMAND_VIDEO ("md-ondemand_video"),
  OPACITY ("md-opacity"),
  OPEN_IN_BROWSER ("md-open_in_browser"),
  OPEN_IN_NEW ("md-open_in_new"),
  OPEN_WITH ("md-open_with"),
  PAGES ("md-pages"),
  PAGEVIEW ("md-pageview"),
  PALETTE ("md-palette"),
  PAN_TOOL ("md-pan_tool"),
  PANORAMA ("md-panorama"),
  PANORAMA_FISH_EYE ("md-panorama_fish_eye"),
  PANORAMA_HORIZONTAL ("md-panorama_horizontal"),
  PANORAMA_VERTICAL ("md-panorama_vertical"),
  PANORAMA_WIDE_ANGLE ("md-panorama_wide_angle"),
  PARTY_MODE ("md-party_mode"),
  PAUSE ("md-pause"),
  PAUSE_CIRCLE_FILLED ("md-pause_circle_filled"),
  PAUSE_CIRCLE_OUTLINE ("md-pause_circle_outline"),
  PAYMENT ("md-payment"),
  PEOPLE ("md-people"),
  PEOPLE_OUTLINE ("md-people_outline"),
  PERM_CAMERA_MIC ("md-perm_camera_mic"),
  PERM_CONTACT_CALENDAR ("md-perm_contact_calendar"),
  PERM_DATA_SETTING ("md-perm_data_setting"),
  PERM_DEVICE_INFORMATION ("md-perm_device_information"),
  PERM_IDENTITY ("md-perm_identity"),
  PERM_MEDIA ("md-perm_media"),
  PERM_PHONE_MSG ("md-perm_phone_msg"),
  PERM_SCAN_WIFI ("md-perm_scan_wifi"),
  PERSON ("md-person"),
  PERSON_ADD ("md-person_add"),
  PERSON_OUTLINE ("md-person_outline"),
  PERSON_PIN ("md-person_pin"),
  PERSON_PIN_CIRCLE ("md-person_pin_circle"),
  PERSONAL_VIDEO ("md-personal_video"),
  PETS ("md-pets"),
  PHONE ("md-phone"),
  PHONE_ANDROID ("md-phone_android"),
  PHONE_BLUETOOTH_SPEAKER ("md-phone_bluetooth_speaker"),
  PHONE_FORWARDED ("md-phone_forwarded"),
  PHONE_IN_TALK ("md-phone_in_talk"),
  PHONE_IPHONE ("md-phone_iphone"),
  PHONE_LOCKED ("md-phone_locked"),
  PHONE_MISSED ("md-phone_missed"),
  PHONE_PAUSED ("md-phone_paused"),
  PHONELINK ("md-phonelink"),
  PHONELINK_ERASE ("md-phonelink_erase"),
  PHONELINK_LOCK ("md-phonelink_lock"),
  PHONELINK_OFF ("md-phonelink_off"),
  PHONELINK_RING ("md-phonelink_ring"),
  PHONELINK_SETUP ("md-phonelink_setup"),
  PHOTO ("md-photo"),
  PHOTO_ALBUM ("md-photo_album"),
  PHOTO_CAMERA ("md-photo_camera"),
  PHOTO_FILTER ("md-photo_filter"),
  PHOTO_LIBRARY ("md-photo_library"),
  PHOTO_SIZE_SELECT_ACTUAL ("md-photo_size_select_actual"),
  PHOTO_SIZE_SELECT_LARGE ("md-photo_size_select_large"),
  PHOTO_SIZE_SELECT_SMALL ("md-photo_size_select_small"),
  PICTURE_AS_PDF ("md-picture_as_pdf"),
  PICTURE_IN_PICTURE ("md-picture_in_picture"),
  PICTURE_IN_PICTURE_ALT ("md-picture_in_picture_alt"),
  PIE_CHART ("md-pie_chart"),
  PIE_CHART_OUTLINED ("md-pie_chart_outlined"),
  PIN_DROP ("md-pin_drop"),
  PLACE ("md-place"),
  PLAY_ARROW ("md-play_arrow"),
  PLAY_CIRCLE_FILLED ("md-play_circle_filled"),
  PLAY_CIRCLE_OUTLINE ("md-play_circle_outline"),
  PLAY_FOR_WORK ("md-play_for_work"),
  PLAYLIST_ADD ("md-playlist_add"),
  PLAYLIST_ADD_CHECK ("md-playlist_add_check"),
  PLAYLIST_PLAY ("md-playlist_play"),
  PLUS_ONE ("md-plus_one"),
  POLL ("md-poll"),
  POLYMER ("md-polymer"),
  POOL ("md-pool"),
  PORTABLE_WIFI_OFF ("md-portable_wifi_off"),
  PORTRAIT ("md-portrait"),
  POWER ("md-power"),
  POWER_INPUT ("md-power_input"),
  POWER_SETTINGS_NEW ("md-power_settings_new"),
  PREGNANT_WOMAN ("md-pregnant_woman"),
  PRESENT_TO_ALL ("md-present_to_all"),
  PRINT ("md-print"),
  PRIORITY_HIGH ("md-priority_high"),
  PUBLIC ("md-public"),
  PUBLISH ("md-publish"),
  QUERY_BUILDER ("md-query_builder"),
  QUESTION_ANSWER ("md-question_answer"),
  QUEUE ("md-queue"),
  QUEUE_MUSIC ("md-queue_music"),
  QUEUE_PLAY_NEXT ("md-queue_play_next"),
  RADIO ("md-radio"),
  RADIO_BUTTON_CHECKED ("md-radio_button_checked"),
  RADIO_BUTTON_UNCHECKED ("md-radio_button_unchecked"),
  RATE_REVIEW ("md-rate_review"),
  RECEIPT ("md-receipt"),
  RECENT_ACTORS ("md-recent_actors"),
  RECORD_VOICE_OVER ("md-record_voice_over"),
  REDEEM ("md-redeem"),
  REDO ("md-redo"),
  REFRESH ("md-refresh"),
  REMOVE ("md-remove"),
  REMOVE_CIRCLE ("md-remove_circle"),
  REMOVE_CIRCLE_OUTLINE ("md-remove_circle_outline"),
  REMOVE_FROM_QUEUE ("md-remove_from_queue"),
  REMOVE_RED_EYE ("md-remove_red_eye"),
  REMOVE_SHOPPING_CART ("md-remove_shopping_cart"),
  REORDER ("md-reorder"),
  REPEAT ("md-repeat"),
  REPEAT_ONE ("md-repeat_one"),
  REPLAY ("md-replay"),
  REPLAY_10 ("md-replay_10"),
  REPLAY_30 ("md-replay_30"),
  REPLAY_5 ("md-replay_5"),
  REPLY ("md-reply"),
  REPLY_ALL ("md-reply_all"),
  REPORT ("md-report"),
  REPORT_PROBLEM ("md-report_problem"),
  RESTAURANT ("md-restaurant"),
  RESTAURANT_MENU ("md-restaurant_menu"),
  RESTORE ("md-restore"),
  RESTORE_PAGE ("md-restore_page"),
  RING_VOLUME ("md-ring_volume"),
  ROOM ("md-room"),
  ROOM_SERVICE ("md-room_service"),
  ROTATE_90_DEGREES_CCW ("md-rotate_90_degrees_ccw"),
  ROTATE_LEFT ("md-rotate_left"),
  ROTATE_RIGHT ("md-rotate_right"),
  ROUNDED_CORNER ("md-rounded_corner"),
  ROUTER ("md-router"),
  ROWING ("md-rowing"),
  RSS_FEED ("md-rss_feed"),
  RV_HOOKUP ("md-rv_hookup"),
  SATELLITE ("md-satellite"),
  SAVE ("md-save"),
  SCANNER ("md-scanner"),
  SCHEDULE ("md-schedule"),
  SCHOOL ("md-school"),
  SCREEN_LOCK_LANDSCAPE ("md-screen_lock_landscape"),
  SCREEN_LOCK_PORTRAIT ("md-screen_lock_portrait"),
  SCREEN_LOCK_ROTATION ("md-screen_lock_rotation"),
  SCREEN_ROTATION ("md-screen_rotation"),
  SCREEN_SHARE ("md-screen_share"),
  SD_CARD ("md-sd_card"),
  SD_STORAGE ("md-sd_storage"),
  SEARCH ("md-search"),
  SECURITY ("md-security"),
  SELECT_ALL ("md-select_all"),
  SEND ("md-send"),
  SENTIMENT_DISSATISFIED ("md-sentiment_dissatisfied"),
  SENTIMENT_NEUTRAL ("md-sentiment_neutral"),
  SENTIMENT_SATISFIED ("md-sentiment_satisfied"),
  SENTIMENT_VERY_DISSATISFIED ("md-sentiment_very_dissatisfied"),
  SENTIMENT_VERY_SATISFIED ("md-sentiment_very_satisfied"),
  SETTINGS ("md-settings"),
  SETTINGS_APPLICATIONS ("md-settings_applications"),
  SETTINGS_BACKUP_RESTORE ("md-settings_backup_restore"),
  SETTINGS_BLUETOOTH ("md-settings_bluetooth"),
  SETTINGS_BRIGHTNESS ("md-settings_brightness"),
  SETTINGS_CELL ("md-settings_cell"),
  SETTINGS_ETHERNET ("md-settings_ethernet"),
  SETTINGS_INPUT_ANTENNA ("md-settings_input_antenna"),
  SETTINGS_INPUT_COMPONENT ("md-settings_input_component"),
  SETTINGS_INPUT_COMPOSITE ("md-settings_input_composite"),
  SETTINGS_INPUT_HDMI ("md-settings_input_hdmi"),
  SETTINGS_INPUT_SVIDEO ("md-settings_input_svideo"),
  SETTINGS_OVERSCAN ("md-settings_overscan"),
  SETTINGS_PHONE ("md-settings_phone"),
  SETTINGS_POWER ("md-settings_power"),
  SETTINGS_REMOTE ("md-settings_remote"),
  SETTINGS_SYSTEM_DAYDREAM ("md-settings_system_daydream"),
  SETTINGS_VOICE ("md-settings_voice"),
  SHARE ("md-share"),
  SHOP ("md-shop"),
  SHOP_TWO ("md-shop_two"),
  SHOPPING_BASKET ("md-shopping_basket"),
  SHOPPING_CART ("md-shopping_cart"),
  SHORT_TEXT ("md-short_text"),
  SHOW_CHART ("md-show_chart"),
  SHUFFLE ("md-shuffle"),
  SIGNAL_CELLULAR_4_BAR ("md-signal_cellular_4_bar"),
  SIGNAL_CELLULAR_CONNECTED_NO_INTERNET_4_BAR ("md-signal_cellular_connected_no_internet_4_bar"),
  SIGNAL_CELLULAR_NO_SIM ("md-signal_cellular_no_sim"),
  SIGNAL_CELLULAR_NULL ("md-signal_cellular_null"),
  SIGNAL_CELLULAR_OFF ("md-signal_cellular_off"),
  SIGNAL_WIFI_4_BAR ("md-signal_wifi_4_bar"),
  SIGNAL_WIFI_4_BAR_LOCK ("md-signal_wifi_4_bar_lock"),
  SIGNAL_WIFI_OFF ("md-signal_wifi_off"),
  SIM_CARD ("md-sim_card"),
  SIM_CARD_ALERT ("md-sim_card_alert"),
  SKIP_NEXT ("md-skip_next"),
  SKIP_PREVIOUS ("md-skip_previous"),
  SLIDESHOW ("md-slideshow"),
  SLOW_MOTION_VIDEO ("md-slow_motion_video"),
  SMARTPHONE ("md-smartphone"),
  SMOKE_FREE ("md-smoke_free"),
  SMOKING_ROOMS ("md-smoking_rooms"),
  SMS ("md-sms"),
  SMS_FAILED ("md-sms_failed"),
  SNOOZE ("md-snooze"),
  SORT ("md-sort"),
  SORT_BY_ALPHA ("md-sort_by_alpha"),
  SPA ("md-spa"),
  SPACE_BAR ("md-space_bar"),
  SPEAKER ("md-speaker"),
  SPEAKER_GROUP ("md-speaker_group"),
  SPEAKER_NOTES ("md-speaker_notes"),
  SPEAKER_NOTES_OFF ("md-speaker_notes_off"),
  SPEAKER_PHONE ("md-speaker_phone"),
  SPELLCHECK ("md-spellcheck"),
  STAR ("md-star"),
  STAR_BORDER ("md-star_border"),
  STAR_HALF ("md-star_half"),
  STARS ("md-stars"),
  STAY_CURRENT_LANDSCAPE ("md-stay_current_landscape"),
  STAY_CURRENT_PORTRAIT ("md-stay_current_portrait"),
  STAY_PRIMARY_LANDSCAPE ("md-stay_primary_landscape"),
  STAY_PRIMARY_PORTRAIT ("md-stay_primary_portrait"),
  STOP ("md-stop"),
  STOP_SCREEN_SHARE ("md-stop_screen_share"),
  STORAGE ("md-storage"),
  STORE ("md-store"),
  STORE_MALL_DIRECTORY ("md-store_mall_directory"),
  STRAIGHTEN ("md-straighten"),
  STREETVIEW ("md-streetview"),
  STRIKETHROUGH_S ("md-strikethrough_s"),
  STYLE ("md-style"),
  SUBDIRECTORY_ARROW_LEFT ("md-subdirectory_arrow_left"),
  SUBDIRECTORY_ARROW_RIGHT ("md-subdirectory_arrow_right"),
  SUBJECT ("md-subject"),
  SUBSCRIPTIONS ("md-subscriptions"),
  SUBTITLES ("md-subtitles"),
  SUBWAY ("md-subway"),
  SUPERVISOR_ACCOUNT ("md-supervisor_account"),
  SURROUND_SOUND ("md-surround_sound"),
  SWAP_CALLS ("md-swap_calls"),
  SWAP_HORIZ ("md-swap_horiz"),
  SWAP_VERT ("md-swap_vert"),
  SWAP_VERTICAL_CIRCLE ("md-swap_vertical_circle"),
  SWITCH_CAMERA ("md-switch_camera"),
  SWITCH_VIDEO ("md-switch_video"),
  SYNC ("md-sync"),
  SYNC_DISABLED ("md-sync_disabled"),
  SYNC_PROBLEM ("md-sync_problem"),
  SYSTEM_UPDATE ("md-system_update"),
  SYSTEM_UPDATE_ALT ("md-system_update_alt"),
  TAB ("md-tab"),
  TAB_UNSELECTED ("md-tab_unselected"),
  TABLET ("md-tablet"),
  TABLET_ANDROID ("md-tablet_android"),
  TABLET_MAC ("md-tablet_mac"),
  TAG_FACES ("md-tag_faces"),
  TAP_AND_PLAY ("md-tap_and_play"),
  TERRAIN ("md-terrain"),
  TEXT_FIELDS ("md-text_fields"),
  TEXT_FORMAT ("md-text_format"),
  TEXTSMS ("md-textsms"),
  TEXTURE ("md-texture"),
  THEATERS ("md-theaters"),
  THUMB_DOWN ("md-thumb_down"),
  THUMB_UP ("md-thumb_up"),
  THUMBS_UP_DOWN ("md-thumbs_up_down"),
  TIME_TO_LEAVE ("md-time_to_leave"),
  TIMELAPSE ("md-timelapse"),
  TIMELINE ("md-timeline"),
  TIMER ("md-timer"),
  TIMER_10 ("md-timer_10"),
  TIMER_3 ("md-timer_3"),
  TIMER_OFF ("md-timer_off"),
  TITLE ("md-title"),
  TOC ("md-toc"),
  TODAY ("md-today"),
  TOLL ("md-toll"),
  TONALITY ("md-tonality"),
  TOUCH_APP ("md-touch_app"),
  TOYS ("md-toys"),
  TRACK_CHANGES ("md-track_changes"),
  TRAFFIC ("md-traffic"),
  TRAIN ("md-train"),
  TRAM ("md-tram"),
  TRANSFER_WITHIN_A_STATION ("md-transfer_within_a_station"),
  TRANSFORM ("md-transform"),
  TRANSLATE ("md-translate"),
  TRENDING_DOWN ("md-trending_down"),
  TRENDING_FLAT ("md-trending_flat"),
  TRENDING_UP ("md-trending_up"),
  TUNE ("md-tune"),
  TURNED_IN ("md-turned_in"),
  TURNED_IN_NOT ("md-turned_in_not"),
  TV ("md-tv"),
  UNARCHIVE ("md-unarchive"),
  UNDO ("md-undo"),
  UNFOLD_LESS ("md-unfold_less"),
  UNFOLD_MORE ("md-unfold_more"),
  UPDATE ("md-update"),
  USB ("md-usb"),
  VERIFIED_USER ("md-verified_user"),
  VERTICAL_ALIGN_BOTTOM ("md-vertical_align_bottom"),
  VERTICAL_ALIGN_CENTER ("md-vertical_align_center"),
  VERTICAL_ALIGN_TOP ("md-vertical_align_top"),
  VIBRATION ("md-vibration"),
  VIDEO_CALL ("md-video_call"),
  VIDEO_LABEL ("md-video_label"),
  VIDEO_LIBRARY ("md-video_library"),
  VIDEOCAM ("md-videocam"),
  VIDEOCAM_OFF ("md-videocam_off"),
  VIDEOGAME_ASSET ("md-videogame_asset"),
  VIEW_AGENDA ("md-view_agenda"),
  VIEW_ARRAY ("md-view_array"),
  VIEW_CAROUSEL ("md-view_carousel"),
  VIEW_COLUMN ("md-view_column"),
  VIEW_COMFY ("md-view_comfy"),
  VIEW_COMPACT ("md-view_compact"),
  VIEW_DAY ("md-view_day"),
  VIEW_HEADLINE ("md-view_headline"),
  VIEW_LIST ("md-view_list"),
  VIEW_MODULE ("md-view_module"),
  VIEW_QUILT ("md-view_quilt"),
  VIEW_STREAM ("md-view_stream"),
  VIEW_WEEK ("md-view_week"),
  VIGNETTE ("md-vignette"),
  VISIBILITY ("md-visibility"),
  VISIBILITY_OFF ("md-visibility_off"),
  VOICE_CHAT ("md-voice_chat"),
  VOICEMAIL ("md-voicemail"),
  VOLUME_DOWN ("md-volume_down"),
  VOLUME_MUTE ("md-volume_mute"),
  VOLUME_OFF ("md-volume_off"),
  VOLUME_UP ("md-volume_up"),
  VPN_KEY ("md-vpn_key"),
  VPN_LOCK ("md-vpn_lock"),
  WALLPAPER ("md-wallpaper"),
  WARNING ("md-warning"),
  WATCH ("md-watch"),
  WATCH_LATER ("md-watch_later"),
  WB_AUTO ("md-wb_auto"),
  WB_CLOUDY ("md-wb_cloudy"),
  WB_INCANDESCENT ("md-wb_incandescent"),
  WB_IRIDESCENT ("md-wb_iridescent"),
  WB_SUNNY ("md-wb_sunny"),
  WC ("md-wc"),
  WEB ("md-web"),
  WEB_ASSET ("md-web_asset"),
  WEEKEND ("md-weekend"),
  WHATSHOT ("md-whatshot"),
  WIDGETS ("md-widgets"),
  WIFI ("md-wifi"),
  WIFI_LOCK ("md-wifi_lock"),
  WIFI_TETHERING ("md-wifi_tethering"),
  WORK ("md-work"),
  WRAP_TEXT ("md-wrap_text"),
  YOUTUBE_SEARCHED_FOR ("md-youtube_searched_for"),
  ZOOM_IN ("md-zoom_in"),
  ZOOM_OUT ("md-zoom_out"),
  ZOOM_OUT_MAP ("md-zoom_out_map");

  private final ICSSClassProvider m_aCSSClass;

  EMaterialDesignIcon (@Nonnull @Nonempty final String sCSSClassName)
  {
    m_aCSSClass = DefaultCSSClassProvider.create (sCSSClassName);
  }

  @Nullable
  public String getCSSClass ()
  {
    return m_aCSSClass.getCSSClass ();
  }

  @Nonnull
  public <T extends IHCElement <?>> T applyToNode (@Nonnull final T aElement)
  {
    aElement.addClasses (CMaterialDesignCSS.MATERIAL_ICONS, m_aCSSClass);
    aElement.customAttrs ().setAriaHidden (true);
    return aElement;
  }

  @Nonnull
  public HCI getAsNode ()
  {
    return applyToNode (new HCI ());
  }

  @Nonnull
  public HCI getAsNode18px ()
  {
    return getAsNode ().addClass (CMaterialDesignCSS.MD_18);
  }

  @Nonnull
  public HCI getAsNode24px ()
  {
    return getAsNode ().addClass (CMaterialDesignCSS.MD_24);
  }

  @Nonnull
  public HCI getAsNode36px ()
  {
    return getAsNode ().addClass (CMaterialDesignCSS.MD_36);
  }

  @Nonnull
  public HCI getAsNode48px ()
  {
    return getAsNode ().addClass (CMaterialDesignCSS.MD_48);
  }

  @Nonnull
  public HCI getAsNodeDark ()
  {
    return getAsNode ().addClass (CMaterialDesignCSS.MD_DARK);
  }

  @Nonnull
  public HCI getAsNodeDarkInactive ()
  {
    return getAsNodeDark ().addClass (CMaterialDesignCSS.MD_INACTIVE);
  }

  @Nonnull
  public HCI getAsNodeLight ()
  {
    return getAsNode ().addClass (CMaterialDesignCSS.MD_LIGHT);
  }

  @Nonnull
  public HCI getAsNodeLightInactive ()
  {
    return getAsNodeLight ().addClass (CMaterialDesignCSS.MD_INACTIVE);
  }

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

  @Nonnull
  public static ICommonsList <ICSSPathProvider> getAllCSSFiles ()
  {
    return new CommonsArrayList <> (EIconCSSPathProvider.MATERIAL_ICONS,
                                    EIconCSSPathProvider.MATERIAL_ICONS_LIST,
                                    EIconCSSPathProvider.PH_OTON_MATERIAL_DESIGN);
  }

  public static void registerResourcesForGlobal ()
  {
    for (final ICSSPathProvider aItem : getAllCSSFiles ())
      PhotonCSS.registerCSSIncludeForGlobal (aItem);
  }

  public static void registerResourcesForThisRequest ()
  {
    for (final ICSSPathProvider aItem : getAllCSSFiles ())
      PhotonCSS.registerCSSIncludeForThisRequest (aItem);
  }
}
