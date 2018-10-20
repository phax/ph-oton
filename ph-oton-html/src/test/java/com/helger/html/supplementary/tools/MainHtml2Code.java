/**
 * Copyright (C) 2014-2018 Philip Helger (www.helger.com)
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
package com.helger.html.supplementary.tools;

import java.util.Locale;

import javax.annotation.Nonnull;
import javax.xml.XMLConstants;

import com.helger.commons.collection.NonBlockingStack;
import com.helger.commons.hierarchy.visit.DefaultHierarchyVisitorCallback;
import com.helger.commons.hierarchy.visit.EHierarchyVisitorReturn;
import com.helger.commons.mutable.MutableInt;
import com.helger.commons.string.StringHelper;
import com.helger.html.EHTMLVersion;
import com.helger.html.parser.XHTMLParser;
import com.helger.xml.microdom.IMicroDocument;
import com.helger.xml.microdom.IMicroElement;
import com.helger.xml.microdom.IMicroNode;
import com.helger.xml.microdom.util.MicroVisitor;

public class MainHtml2Code
{
  public static void main (final String [] args)
  {
    final String sHTML = "  <body tabindex='1' class='loadingInProgress'>\r\n" +
                         "    <div id='outerContainer'>\r\n" +
                         "\r\n" +
                         "      <div id='sidebarContainer'>\r\n" +
                         "        <div id='toolbarSidebar'>\r\n" +
                         "          <div class='splitToolbarButton toggled'>\r\n" +
                         "            <button id='viewThumbnail' class='toolbarButton toggled' title='Show Thumbnails' tabindex='2' data-l10n-id='thumbs'>\r\n" +
                         "               <span data-l10n-id='thumbs_label'>Thumbnails</span>\r\n" +
                         "            </button>\r\n" +
                         "            <button id='viewOutline' class='toolbarButton' title='Show Document Outline (double-click to expand/collapse all items)' tabindex='3' data-l10n-id='document_outline'>\r\n" +
                         "               <span data-l10n-id='document_outline_label'>Document Outline</span>\r\n" +
                         "            </button>\r\n" +
                         "            <button id='viewAttachments' class='toolbarButton' title='Show Attachments' tabindex='4' data-l10n-id='attachments'>\r\n" +
                         "               <span data-l10n-id='attachments_label'>Attachments</span>\r\n" +
                         "            </button>\r\n" +
                         "          </div>\r\n" +
                         "        </div>\r\n" +
                         "        <div id='sidebarContent'>\r\n" +
                         "          <div id='thumbnailView'>\r\n" +
                         "          </div>\r\n" +
                         "          <div id='outlineView' class='hidden'>\r\n" +
                         "          </div>\r\n" +
                         "          <div id='attachmentsView' class='hidden'>\r\n" +
                         "          </div>\r\n" +
                         "        </div>\r\n" +
                         "      </div>  <!-- sidebarContainer -->\r\n" +
                         "\r\n" +
                         "      <div id='mainContainer'>\r\n" +
                         "        <div class='findbar hidden doorHanger' id='findbar'>\r\n" +
                         "          <div id='findbarInputContainer'>\r\n" +
                         "            <input id='findInput' class='toolbarField' title='Find' placeholder='Find in document…' tabindex='91' data-l10n-id='find_input'></input>\r\n" +
                         "            <div class='splitToolbarButton'>\r\n" +
                         "              <button id='findPrevious' class='toolbarButton findPrevious' title='Find the previous occurrence of the phrase' tabindex='92' data-l10n-id='find_previous'>\r\n" +
                         "                <span data-l10n-id='find_previous_label'>Previous</span>\r\n" +
                         "              </button>\r\n" +
                         "              <div class='splitToolbarButtonSeparator'></div>\r\n" +
                         "              <button id='findNext' class='toolbarButton findNext' title='Find the next occurrence of the phrase' tabindex='93' data-l10n-id='find_next'>\r\n" +
                         "                <span data-l10n-id='find_next_label'>Next</span>\r\n" +
                         "              </button>\r\n" +
                         "            </div>\r\n" +
                         "          </div>\r\n" +
                         "\r\n" +
                         "          <div id='findbarOptionsContainer'>\r\n" +
                         "            <input type='checkbox' id='findHighlightAll' class='toolbarField' tabindex='94'></input>\r\n" +
                         "            <label for='findHighlightAll' class='toolbarLabel' data-l10n-id='find_highlight'>Highlight all</label>\r\n" +
                         "            <input type='checkbox' id='findMatchCase' class='toolbarField' tabindex='95'></input>\r\n" +
                         "            <label for='findMatchCase' class='toolbarLabel' data-l10n-id='find_match_case_label'>Match case</label>\r\n" +
                         "            <span id='findResultsCount' class='toolbarLabel hidden'></span>\r\n" +
                         "          </div>\r\n" +
                         "\r\n" +
                         "          <div id='findbarMessageContainer'>\r\n" +
                         "            <span id='findMsg' class='toolbarLabel'></span>\r\n" +
                         "          </div>\r\n" +
                         "        </div>  <!-- findbar -->\r\n" +
                         "\r\n" +
                         "        <div id='secondaryToolbar' class='secondaryToolbar hidden doorHangerRight'>\r\n" +
                         "          <div id='secondaryToolbarButtonContainer'>\r\n" +
                         "            <button id='secondaryPresentationMode' class='secondaryToolbarButton presentationMode visibleLargeView' title='Switch to Presentation Mode' tabindex='51' data-l10n-id='presentation_mode'>\r\n" +
                         "              <span data-l10n-id='presentation_mode_label'>Presentation Mode</span>\r\n" +
                         "            </button>\r\n" +
                         "\r\n" +
                         "            <button id='secondaryOpenFile' class='secondaryToolbarButton openFile visibleLargeView' title='Open File' tabindex='52' data-l10n-id='open_file'>\r\n" +
                         "              <span data-l10n-id='open_file_label'>Open</span>\r\n" +
                         "            </button>\r\n" +
                         "\r\n" +
                         "            <button id='secondaryPrint' class='secondaryToolbarButton print visibleMediumView' title='Print' tabindex='53' data-l10n-id='print'>\r\n" +
                         "              <span data-l10n-id='print_label'>Print</span>\r\n" +
                         "            </button>\r\n" +
                         "\r\n" +
                         "            <button id='secondaryDownload' class='secondaryToolbarButton download visibleMediumView' title='Download' tabindex='54' data-l10n-id='download'>\r\n" +
                         "              <span data-l10n-id='download_label'>Download</span>\r\n" +
                         "            </button>\r\n" +
                         "\r\n" +
                         "            <a href='#' id='secondaryViewBookmark' class='secondaryToolbarButton bookmark visibleSmallView' title='Current view (copy or open in new window)' tabindex='55' data-l10n-id='bookmark'>\r\n" +
                         "              <span data-l10n-id='bookmark_label'>Current View</span>\r\n" +
                         "            </a>\r\n" +
                         "\r\n" +
                         "            <div class='horizontalToolbarSeparator visibleLargeView'></div>\r\n" +
                         "\r\n" +
                         "            <button id='firstPage' class='secondaryToolbarButton firstPage' title='Go to First Page' tabindex='56' data-l10n-id='first_page'>\r\n" +
                         "              <span data-l10n-id='first_page_label'>Go to First Page</span>\r\n" +
                         "            </button>\r\n" +
                         "            <button id='lastPage' class='secondaryToolbarButton lastPage' title='Go to Last Page' tabindex='57' data-l10n-id='last_page'>\r\n" +
                         "              <span data-l10n-id='last_page_label'>Go to Last Page</span>\r\n" +
                         "            </button>\r\n" +
                         "\r\n" +
                         "            <div class='horizontalToolbarSeparator'></div>\r\n" +
                         "\r\n" +
                         "            <button id='pageRotateCw' class='secondaryToolbarButton rotateCw' title='Rotate Clockwise' tabindex='58' data-l10n-id='page_rotate_cw'>\r\n" +
                         "              <span data-l10n-id='page_rotate_cw_label'>Rotate Clockwise</span>\r\n" +
                         "            </button>\r\n" +
                         "            <button id='pageRotateCcw' class='secondaryToolbarButton rotateCcw' title='Rotate Counterclockwise' tabindex='59' data-l10n-id='page_rotate_ccw'>\r\n" +
                         "              <span data-l10n-id='page_rotate_ccw_label'>Rotate Counterclockwise</span>\r\n" +
                         "            </button>\r\n" +
                         "\r\n" +
                         "            <div class='horizontalToolbarSeparator'></div>\r\n" +
                         "\r\n" +
                         "            <button id='cursorSelectTool' class='secondaryToolbarButton selectTool toggled' title='Enable Text Selection Tool' tabindex='60' data-l10n-id='cursor_text_select_tool'>\r\n" +
                         "              <span data-l10n-id='cursor_text_select_tool_label'>Text Selection Tool</span>\r\n" +
                         "            </button>\r\n" +
                         "            <button id='cursorHandTool' class='secondaryToolbarButton handTool' title='Enable Hand Tool' tabindex='61' data-l10n-id='cursor_hand_tool'>\r\n" +
                         "              <span data-l10n-id='cursor_hand_tool_label'>Hand Tool</span>\r\n" +
                         "            </button>\r\n" +
                         "\r\n" +
                         "            <div class='horizontalToolbarSeparator'></div>\r\n" +
                         "\r\n" +
                         "            <button id='documentProperties' class='secondaryToolbarButton documentProperties' title='Document Properties…' tabindex='62' data-l10n-id='document_properties'>\r\n" +
                         "              <span data-l10n-id='document_properties_label'>Document Properties…</span>\r\n" +
                         "            </button>\r\n" +
                         "          </div>\r\n" +
                         "        </div>  <!-- secondaryToolbar -->\r\n" +
                         "\r\n" +
                         "        <div class='toolbar'>\r\n" +
                         "          <div id='toolbarContainer'>\r\n" +
                         "            <div id='toolbarViewer'>\r\n" +
                         "              <div id='toolbarViewerLeft'>\r\n" +
                         "                <button id='sidebarToggle' class='toolbarButton' title='Toggle Sidebar' tabindex='11' data-l10n-id='toggle_sidebar'>\r\n" +
                         "                  <span data-l10n-id='toggle_sidebar_label'>Toggle Sidebar</span>\r\n" +
                         "                </button>\r\n" +
                         "                <div class='toolbarButtonSpacer'></div>\r\n" +
                         "                <button id='viewFind' class='toolbarButton' title='Find in Document' tabindex='12' data-l10n-id='findbar'>\r\n" +
                         "                  <span data-l10n-id='findbar_label'>Find</span>\r\n" +
                         "                </button>\r\n" +
                         "                <div class='splitToolbarButton hiddenSmallView'>\r\n" +
                         "                  <button class='toolbarButton pageUp' title='Previous Page' id='previous' tabindex='13' data-l10n-id='previous'>\r\n" +
                         "                    <span data-l10n-id='previous_label'>Previous</span>\r\n" +
                         "                  </button>\r\n" +
                         "                  <div class='splitToolbarButtonSeparator'></div>\r\n" +
                         "                  <button class='toolbarButton pageDown' title='Next Page' id='next' tabindex='14' data-l10n-id='next'>\r\n" +
                         "                    <span data-l10n-id='next_label'>Next</span>\r\n" +
                         "                  </button>\r\n" +
                         "                </div>\r\n" +
                         "                <input type='number' id='pageNumber' class='toolbarField pageNumber' title='Page' value='1' size='4' min='1' tabindex='15' data-l10n-id='page'></input>\r\n" +
                         "                <span id='numPages' class='toolbarLabel'></span>\r\n" +
                         "              </div>\r\n" +
                         "              <div id='toolbarViewerRight'>\r\n" +
                         "                <button id='presentationMode' class='toolbarButton presentationMode hiddenLargeView' title='Switch to Presentation Mode' tabindex='31' data-l10n-id='presentation_mode'>\r\n" +
                         "                  <span data-l10n-id='presentation_mode_label'>Presentation Mode</span>\r\n" +
                         "                </button>\r\n" +
                         "\r\n" +
                         "                <button id='openFile' class='toolbarButton openFile hiddenLargeView' title='Open File' tabindex='32' data-l10n-id='open_file'>\r\n" +
                         "                  <span data-l10n-id='open_file_label'>Open</span>\r\n" +
                         "                </button>\r\n" +
                         "\r\n" +
                         "                <button id='print' class='toolbarButton print hiddenMediumView' title='Print' tabindex='33' data-l10n-id='print'>\r\n" +
                         "                  <span data-l10n-id='print_label'>Print</span>\r\n" +
                         "                </button>\r\n" +
                         "\r\n" +
                         "                <button id='download' class='toolbarButton download hiddenMediumView' title='Download' tabindex='34' data-l10n-id='download'>\r\n" +
                         "                  <span data-l10n-id='download_label'>Download</span>\r\n" +
                         "                </button>\r\n" +
                         "                <a href='#' id='viewBookmark' class='toolbarButton bookmark hiddenSmallView' title='Current view (copy or open in new window)' tabindex='35' data-l10n-id='bookmark'>\r\n" +
                         "                  <span data-l10n-id='bookmark_label'>Current View</span>\r\n" +
                         "                </a>\r\n" +
                         "\r\n" +
                         "                <div class='verticalToolbarSeparator hiddenSmallView'></div>\r\n" +
                         "\r\n" +
                         "                <button id='secondaryToolbarToggle' class='toolbarButton' title='Tools' tabindex='36' data-l10n-id='tools'>\r\n" +
                         "                  <span data-l10n-id='tools_label'>Tools</span>\r\n" +
                         "                </button>\r\n" +
                         "              </div>\r\n" +
                         "              <div id='toolbarViewerMiddle'>\r\n" +
                         "                <div class='splitToolbarButton'>\r\n" +
                         "                  <button id='zoomOut' class='toolbarButton zoomOut' title='Zoom Out' tabindex='21' data-l10n-id='zoom_out'>\r\n" +
                         "                    <span data-l10n-id='zoom_out_label'>Zoom Out</span>\r\n" +
                         "                  </button>\r\n" +
                         "                  <div class='splitToolbarButtonSeparator'></div>\r\n" +
                         "                  <button id='zoomIn' class='toolbarButton zoomIn' title='Zoom In' tabindex='22' data-l10n-id='zoom_in'>\r\n" +
                         "                    <span data-l10n-id='zoom_in_label'>Zoom In</span>\r\n" +
                         "                   </button>\r\n" +
                         "                </div>\r\n" +
                         "                <span id='scaleSelectContainer' class='dropdownToolbarButton'>\r\n" +
                         "                  <select id='scaleSelect' title='Zoom' tabindex='23' data-l10n-id='zoom'>\r\n" +
                         "                    <option id='pageAutoOption' title='' value='auto' selected='selected' data-l10n-id='page_scale_auto'>Automatic Zoom</option>\r\n" +
                         "                    <option id='pageActualOption' title='' value='page-actual' data-l10n-id='page_scale_actual'>Actual Size</option>\r\n" +
                         "                    <option id='pageFitOption' title='' value='page-fit' data-l10n-id='page_scale_fit'>Page Fit</option>\r\n" +
                         "                    <option id='pageWidthOption' title='' value='page-width' data-l10n-id='page_scale_width'>Page Width</option>\r\n" +
                         "                    <option id='customScaleOption' title='' value='custom' disabled='disabled' hidden='true'></option>\r\n" +
                         "                    <option title=\"\" value=\"0.5\" data-l10n-id=\"page_scale_percent\" data-l10n-args=\"{ 'scale': 50 }\">50%</option>\r\n" +
                         "                    <option title=\"\" value=\"0.75\" data-l10n-id=\"page_scale_percent\" data-l10n-args=\"{ 'scale': 75 }\">75%</option>\r\n" +
                         "                    <option title=\"\" value=\"1\" data-l10n-id=\"page_scale_percent\" data-l10n-args=\"{ 'scale': 100 }\">100%</option>\r\n" +
                         "                    <option title=\"\" value=\"1.25\" data-l10n-id=\"page_scale_percent\" data-l10n-args=\"{ 'scale': 125 }\">125%</option>\r\n" +
                         "                    <option title=\"\" value=\"1.5\" data-l10n-id=\"page_scale_percent\" data-l10n-args=\"{ 'scale': 150 }\">150%</option>\r\n" +
                         "                    <option title=\"\" value=\"2\" data-l10n-id=\"page_scale_percent\" data-l10n-args=\"{ 'scale': 200 }\">200%</option>\r\n" +
                         "                    <option title=\"\" value=\"3\" data-l10n-id=\"page_scale_percent\" data-l10n-args=\"{ 'scale': 300 }\">300%</option>\r\n" +
                         "                    <option title=\"\" value=\"4\" data-l10n-id=\"page_scale_percent\" data-l10n-args=\"{ 'scale': 400 }\">400%</option>\r\n" +
                         "                  </select>\r\n" +
                         "                </span>\r\n" +
                         "              </div>\r\n" +
                         "            </div>\r\n" +
                         "            <div id='loadingBar'>\r\n" +
                         "              <div class='progress'>\r\n" +
                         "                <div class='glimmer'>\r\n" +
                         "                </div>\r\n" +
                         "              </div>\r\n" +
                         "            </div>\r\n" +
                         "          </div>\r\n" +
                         "        </div>\r\n" +
                         "\r\n" +
                         "        <menu type='context' id='viewerContextMenu'>\r\n" +
                         "          <menuitem id='contextFirstPage' label='First Page'\r\n" +
                         "                    data-l10n-id='first_page'></menuitem>\r\n" +
                         "          <menuitem id='contextLastPage' label='Last Page'\r\n" +
                         "                    data-l10n-id='last_page'></menuitem>\r\n" +
                         "          <menuitem id='contextPageRotateCw' label='Rotate Clockwise'\r\n" +
                         "                    data-l10n-id='page_rotate_cw'></menuitem>\r\n" +
                         "          <menuitem id='contextPageRotateCcw' label='Rotate Counter-Clockwise'\r\n" +
                         "                    data-l10n-id='page_rotate_ccw'></menuitem>\r\n" +
                         "        </menu>\r\n" +
                         "\r\n" +
                         "        <div id='viewerContainer' tabindex='0'>\r\n" +
                         "          <div id='viewer' class='pdfViewer'></div>\r\n" +
                         "        </div>\r\n" +
                         "\r\n" +
                         "        <div id='errorWrapper' hidden='true'>\r\n" +
                         "          <div id='errorMessageLeft'>\r\n" +
                         "            <span id='errorMessage'></span>\r\n" +
                         "            <button id='errorShowMore' data-l10n-id='error_more_info'>\r\n" +
                         "              More Information\r\n" +
                         "            </button>\r\n" +
                         "            <button id='errorShowLess' data-l10n-id='error_less_info' hidden='true'>\r\n" +
                         "              Less Information\r\n" +
                         "            </button>\r\n" +
                         "          </div>\r\n" +
                         "          <div id='errorMessageRight'>\r\n" +
                         "            <button id='errorClose' data-l10n-id='error_close'>\r\n" +
                         "              Close\r\n" +
                         "            </button>\r\n" +
                         "          </div>\r\n" +
                         "          <div class='clearBoth'></div>\r\n" +
                         "          <textarea id='errorMoreInfo' hidden='true' readonly='readonly'></textarea>\r\n" +
                         "        </div>\r\n" +
                         "      </div> <!-- mainContainer -->\r\n" +
                         "\r\n" +
                         "      <div id='overlayContainer' class='hidden'>\r\n" +
                         "        <div id='passwordOverlay' class='container hidden'>\r\n" +
                         "          <div class='dialog'>\r\n" +
                         "            <div class='row'>\r\n" +
                         "              <p id='passwordText' data-l10n-id='password_label'>Enter the password to open this PDF file:</p>\r\n" +
                         "            </div>\r\n" +
                         "            <div class='row'>\r\n" +
                         "              <input type='password' id='password' class='toolbarField'></input>\r\n" +
                         "            </div>\r\n" +
                         "            <div class='buttonRow'>\r\n" +
                         "              <button id='passwordCancel' class='overlayButton'><span data-l10n-id='password_cancel'>Cancel</span></button>\r\n" +
                         "              <button id='passwordSubmit' class='overlayButton'><span data-l10n-id='password_ok'>OK</span></button>\r\n" +
                         "            </div>\r\n" +
                         "          </div>\r\n" +
                         "        </div>\r\n" +
                         "        <div id='documentPropertiesOverlay' class='container hidden'>\r\n" +
                         "          <div class='dialog'>\r\n" +
                         "            <div class='row'>\r\n" +
                         "              <span data-l10n-id='document_properties_file_name'>File name:</span> <p id='fileNameField'>-</p>\r\n" +
                         "            </div>\r\n" +
                         "            <div class='row'>\r\n" +
                         "              <span data-l10n-id='document_properties_file_size'>File size:</span> <p id='fileSizeField'>-</p>\r\n" +
                         "            </div>\r\n" +
                         "            <div class='separator'></div>\r\n" +
                         "            <div class='row'>\r\n" +
                         "              <span data-l10n-id='document_properties_title'>Title:</span> <p id='titleField'>-</p>\r\n" +
                         "            </div>\r\n" +
                         "            <div class='row'>\r\n" +
                         "              <span data-l10n-id='document_properties_author'>Author:</span> <p id='authorField'>-</p>\r\n" +
                         "            </div>\r\n" +
                         "            <div class='row'>\r\n" +
                         "              <span data-l10n-id='document_properties_subject'>Subject:</span> <p id='subjectField'>-</p>\r\n" +
                         "            </div>\r\n" +
                         "            <div class='row'>\r\n" +
                         "              <span data-l10n-id='document_properties_keywords'>Keywords:</span> <p id='keywordsField'>-</p>\r\n" +
                         "            </div>\r\n" +
                         "            <div class='row'>\r\n" +
                         "              <span data-l10n-id='document_properties_creation_date'>Creation Date:</span> <p id='creationDateField'>-</p>\r\n" +
                         "            </div>\r\n" +
                         "            <div class='row'>\r\n" +
                         "              <span data-l10n-id='document_properties_modification_date'>Modification Date:</span> <p id='modificationDateField'>-</p>\r\n" +
                         "            </div>\r\n" +
                         "            <div class='row'>\r\n" +
                         "              <span data-l10n-id='document_properties_creator'>Creator:</span> <p id='creatorField'>-</p>\r\n" +
                         "            </div>\r\n" +
                         "            <div class='separator'></div>\r\n" +
                         "            <div class='row'>\r\n" +
                         "              <span data-l10n-id='document_properties_producer'>PDF Producer:</span> <p id='producerField'>-</p>\r\n" +
                         "            </div>\r\n" +
                         "            <div class='row'>\r\n" +
                         "              <span data-l10n-id='document_properties_version'>PDF Version:</span> <p id='versionField'>-</p>\r\n" +
                         "            </div>\r\n" +
                         "            <div class='row'>\r\n" +
                         "              <span data-l10n-id='document_properties_page_count'>Page Count:</span> <p id='pageCountField'>-</p>\r\n" +
                         "            </div>\r\n" +
                         "            <div class='buttonRow'>\r\n" +
                         "              <button id='documentPropertiesClose' class='overlayButton'><span data-l10n-id='document_properties_close'>Close</span></button>\r\n" +
                         "            </div>\r\n" +
                         "          </div>\r\n" +
                         "        </div>\r\n" +
                         "        <div id='printServiceOverlay' class='container hidden'>\r\n" +
                         "          <div class='dialog'>\r\n" +
                         "            <div class='row'>\r\n" +
                         "              <span data-l10n-id='print_progress_message'>Preparing document for printing…</span>\r\n" +
                         "            </div>\r\n" +
                         "            <div class='row'>\r\n" +
                         "              <progress value='0' max='100'></progress>\r\n" +
                         "              <span data-l10n-id='print_progress_percent' data-l10n-args='{ \"progress\": 0 }' class='relative-progress'>0%</span>\r\n" +
                         "            </div>\r\n" +
                         "            <div class='buttonRow'>\r\n" +
                         "              <button id='printCancel' class='overlayButton'><span data-l10n-id='print_progress_close'>Cancel</span></button>\r\n" +
                         "            </div>\r\n" +
                         "          </div>\r\n" +
                         "        </div>\r\n" +
                         "      </div>  <!-- overlayContainer -->\r\n" +
                         "\r\n" +
                         "    </div> <!-- outerContainer -->\r\n" +
                         "    <div id='printContainer'></div>\r\n" +
                         "  </body>";

    final IMicroDocument aDoc = new XHTMLParser (EHTMLVersion.XHTML11).parseXHTMLFragment (sHTML);
    if (aDoc == null)
      throw new IllegalStateException ("Invalid");
    final IMicroElement aBody = aDoc.getDocumentElement ().getFirstChildElement ("body");
    final StringBuilder aSB = new StringBuilder ();
    final MutableInt aCount = new MutableInt (0);
    final NonBlockingStack <IMicroElement> aStack = new NonBlockingStack <> ();
    MicroVisitor.visit (aBody, new DefaultHierarchyVisitorCallback <IMicroNode> ()
    {
      @Override
      @Nonnull
      public EHierarchyVisitorReturn onItemBeforeChildren (final IMicroNode aItem)
      {
        if (aItem.isElement ())
        {
          final IMicroElement aParent = aStack.isEmpty () ? null : aStack.peek ();
          final IMicroElement aElement = (IMicroElement) aItem;
          final String sType = _getTypeName (aElement.getTagName ());
          final String sVar = "var" + aCount.inc ();
          aSB.append (sType).append (" ").append (sVar).append (" = ");
          aSB.append ("new " + sType + " ();\r\n");
          aElement.forAllAttributes ( (ns, n, v) -> {
            // Ignore xml:space
            if (!XMLConstants.XML_NS_URI.equals (ns))
              aSB.append (sVar + "." + _getSetterName (sType, n, v) + ";\n");
          });

          if (aParent != null)
            aSB.append (aParent.getAttributeValue ("__var") + ".addChild (" + sVar + ");\n");

          aStack.push (aElement);
          aElement.setAttribute ("__var", sVar);
        }
        // Always continue
        return EHierarchyVisitorReturn.CONTINUE;
      }

      @Override
      @Nonnull
      public EHierarchyVisitorReturn onItemAfterChildren (final IMicroNode aItem)
      {
        if (aItem.isElement ())
        {
          aStack.pop ();
        }
        // Always continue
        return EHierarchyVisitorReturn.CONTINUE;
      }
    });
    System.out.println (aSB.toString ());

  }

  @Nonnull
  private static String _camel (@Nonnull final String s)
  {
    return s.substring (0, 1).toUpperCase (Locale.US) + s.substring (1).toLowerCase (Locale.US);
  }

  private static String _getTypeName (final String sTagName)
  {
    if ("menuitem".equalsIgnoreCase (sTagName))
      return "HCMenuItem";
    if ("textarea".equalsIgnoreCase (sTagName))
      return "HCTextArea";

    return "HC" + _camel (sTagName);
  }

  @Nonnull
  private static String _quote (@Nonnull final String s)
  {
    return "\"" + s + "\"";
  }

  private static String _getSetterName (final String sType, final String sName, final String sValue)
  {
    String sMethod = null;
    String sParams = null;
    if (sName.equalsIgnoreCase ("tabindex"))
    {
      sMethod = "setTabIndex";
      sParams = sValue;
    }
    else
      if (sName.equalsIgnoreCase ("size"))
      {
        sParams = sValue;
      }
      else
        if (sName.equalsIgnoreCase ("class"))
        {
          int nClasses = 0;
          final StringBuilder aSB = new StringBuilder ();
          for (final String sPart : StringHelper.getExploded (" ", sValue))
            if (sPart.length () > 0)
            {
              ++nClasses;
              if (aSB.length () > 0)
                aSB.append (", ");
              aSB.append ("DefaultCSSClassProvider.create (").append (_quote (sPart.trim ())).append (')');
            }
          sParams = aSB.toString ();
          sMethod = nClasses == 1 ? "addClass" : "addClasses";
        }
        else
          if (sName.equalsIgnoreCase ("id"))
            sMethod = "setID";
          else
            if (sName.startsWith ("data-"))
            {
              sMethod = "customAttrs ().setDataAttr";
              sParams = _quote (sName.substring (5)) + ", " + _quote (sValue);
            }
            else
              if (sName.equalsIgnoreCase ("type"))
              {
                if (sType.equals ("HCButton"))
                  sParams = "EHCButtonType." + sValue.toUpperCase (Locale.US);
                else
                  if (sType.equals ("HCInput"))
                    sParams = "EHCInputType." + sValue.toUpperCase (Locale.US);
              }
              else
                if (sName.equalsIgnoreCase ("href"))
                {
                  sParams = "new SimpleURL (" + _quote (sValue) + ")";
                }
                else
                  if (sName.equalsIgnoreCase ("selected") ||
                      sName.equalsIgnoreCase ("disabled") ||
                      sName.equalsIgnoreCase ("hidden"))
                  {
                    final boolean bTrue = sName.equalsIgnoreCase (sValue) || sName.equalsIgnoreCase ("true");
                    sParams = Boolean.toString (bTrue);
                  }

    if (sMethod == null)
      sMethod = "set" + _camel (sName);

    if (sParams == null)
      sParams = _quote (sValue);

    return sMethod + "(" + sParams + ")";
  }

  @SuppressWarnings ("unused")
  private static void test ()
  {
    // TODO paste result here
  }
}
