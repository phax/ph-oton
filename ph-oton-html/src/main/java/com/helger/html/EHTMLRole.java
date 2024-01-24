/*
 * Copyright (C) 2014-2024 Philip Helger (www.helger.com)
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
package com.helger.html;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.helger.commons.annotation.Nonempty;
import com.helger.commons.id.IHasID;
import com.helger.commons.lang.EnumHelper;

/**
 * Enumeration with all HTML roles. Source: http://www.w3.org/TR/wai-aria/roles
 * <br>
 * Note: the deprecated rules are the abstract ones, as they should not be used
 * in HTML generation. They are only present for completeness!
 *
 * @author Philip Helger
 */
public enum EHTMLRole implements IHasID <String>
{
  ALERT ("alert",
         EHTMLRoleType.WIDGET,
         "A message with important, and usually time-sensitive, information. See related alertdialog and status."),
  ALERTDIALOG ("alertdialog",
               EHTMLRoleType.WIDGET,
               "A type of dialog that contains an alert message, where initial focus goes to an element within the dialog. See related alert and dialog."),
  APPLICATION ("application", EHTMLRoleType.LANDMARK, "A region declared as a web application, as opposed to a web document."),
  ARTICLE ("article",
           EHTMLRoleType.DOCUMENT_STRUCTURE,
           "A section of a page that consists of a composition that forms an independent part of a document, page, or site."),
  BANNER ("banner", EHTMLRoleType.LANDMARK, "A region that contains mostly site-oriented content, rather than page-specific content."),
  BUTTON ("button", EHTMLRoleType.WIDGET, "An input that allows for user-triggered actions when clicked or pressed. See related link."),
  CHECKBOX ("checkbox", EHTMLRoleType.WIDGET, "A checkable input that has three possible values: true, false, or mixed."),
  COLUMNHEADER ("columnheader", EHTMLRoleType.DOCUMENT_STRUCTURE, "A cell containing header information for a column."),
  COMBOBOX ("combobox",
            EHTMLRoleType.WIDGET_CONTAINER,
            "A presentation of a select; usually similar to a textbox where users can type ahead to select an option, or type to enter arbitrary text as a new item in the list. See related listbox."),
  @Deprecated
  COMMAND ("command", EHTMLRoleType.ABSTRACT, "A form of widget that performs an action but does not receive input data."),
  COMPLEMENTARY ("complementary",
                 EHTMLRoleType.LANDMARK,
                 "A supporting section of the document, designed to be complementary to the main content at a similar level in the DOM hierarchy, but remains meaningful when separated from the main content."),
  @Deprecated
  COMPOSITE ("composite", EHTMLRoleType.ABSTRACT, "A widget that may contain navigable descendants or owned children."),
  CONTENTINFO ("contentinfo", EHTMLRoleType.LANDMARK, "A large perceivable region that contains information about the parent document."),
  DEFINITION ("definition", EHTMLRoleType.DOCUMENT_STRUCTURE, "A definition of a term or concept."),
  DIALOG ("dialog",
          EHTMLRoleType.WIDGET,
          "A dialog is an application window that is designed to interrupt the current processing of an application in order to prompt the user to enter information or require a response. See related alertdialog."),
  DIRECTORY ("directory",
             EHTMLRoleType.DOCUMENT_STRUCTURE,
             "A list of references to members of a group, such as a static table of contents."),
  DOCUMENT ("document",
            EHTMLRoleType.DOCUMENT_STRUCTURE,
            "A region containing related information that is declared as document content, as opposed to a web application."),
  FORM ("form",
        EHTMLRoleType.LANDMARK,
        "A landmark region that contains a collection of items and objects that, as a whole, combine to create a form. See related search."),
  GRID ("grid",
        EHTMLRoleType.WIDGET_CONTAINER,
        "A grid is an interactive control which contains cells of tabular data arranged in rows and columns, like a table."),
  GRIDCELL ("gridcell", EHTMLRoleType.WIDGET, "A cell in a grid or treegrid."),
  GROUP ("group",
         EHTMLRoleType.DOCUMENT_STRUCTURE,
         "A set of user interface objects which are not intended to be included in a page summary or table of contents by assistive technologies."),
  HEADING ("heading", EHTMLRoleType.DOCUMENT_STRUCTURE, "A heading for a section of the page."),
  IMG ("img", EHTMLRoleType.DOCUMENT_STRUCTURE, "A container for a collection of elements that form an image."),
  @Deprecated
  INPUT ("input", EHTMLRoleType.ABSTRACT, "A generic type of widget that allows user input."),
  @Deprecated
  LANDMARK ("landmark", EHTMLRoleType.ABSTRACT, "A region of the page intended as a navigational landmark."),
  LINK ("link",
        EHTMLRoleType.WIDGET,
        "An interactive reference to an internal or external resource that, when activated, causes the user agent to navigate to that resource. See related button."),
  LIST ("list", EHTMLRoleType.DOCUMENT_STRUCTURE, "A group of non-interactive list items. See related listbox."),
  LISTBOX ("listbox",
           EHTMLRoleType.WIDGET_CONTAINER,
           "A widget that allows the user to select one or more items from a list of choices. See related combobox and list."),
  LISTITEM ("listitem", EHTMLRoleType.DOCUMENT_STRUCTURE, "A single item in a list or directory."),
  LOG ("log",
       EHTMLRoleType.WIDGET,
       "A type of live region where new information is added in meaningful order and old information may disappear. See related marquee."),
  MAIN ("main", EHTMLRoleType.LANDMARK, "The main content of a document."),
  MARQUEE ("marquee", EHTMLRoleType.WIDGET, "A type of live region where non-essential information changes frequently. See related log."),
  MATH ("math", EHTMLRoleType.DOCUMENT_STRUCTURE, "Content that represents a mathematical expression. "),
  MENU ("menu", EHTMLRoleType.WIDGET_CONTAINER, "A type of widget that offers a list of choices to the user."),
  MENUBAR ("menubar",
           EHTMLRoleType.WIDGET_CONTAINER,
           "A presentation of menu that usually remains visible and is usually presented horizontally."),
  MENUITEM ("menuitem", EHTMLRoleType.WIDGET, "An option in a group of choices contained by a menu or menubar."),
  MENUITEMCHECKBOX ("menuitemcheckbox",
                    EHTMLRoleType.WIDGET,
                    "A checkable menuitem that has three possible values: true, false, or mixed."),
  MENUITEMRADIO ("menuitemradio",
                 EHTMLRoleType.WIDGET,
                 "A checkable menuitem in a group of menuitemradio roles, only one of which can be checked at a time."),
  NAVIGATION ("navigation",
              EHTMLRoleType.LANDMARK,
              "A collection of navigational elements (usually links) for navigating the document or related documents."),
  NOTE ("note",
        EHTMLRoleType.DOCUMENT_STRUCTURE,
        "A section whose content is parenthetic or ancillary to the main content of the resource."),
  OPTION ("option", EHTMLRoleType.WIDGET, "A selectable item in a select list."),
  PRESENTATION ("presentation",
                EHTMLRoleType.DOCUMENT_STRUCTURE,
                "An element whose implicit native role semantics will not be mapped to the accessibility API."),
  PROGRESSBAR ("progressbar", EHTMLRoleType.WIDGET, "An element that displays the progress status for tasks that take a long time."),
  RADIO ("radio", EHTMLRoleType.WIDGET, "A checkable input in a group of radio roles, only one of which can be checked at a time."),
  RADIOGROUP ("radiogroup", EHTMLRoleType.WIDGET_CONTAINER, "A group of radio buttons."),
  @Deprecated
  RANGE ("range", EHTMLRoleType.ABSTRACT, "An input representing a range of values that can be set by the user."),
  REGION ("region",
          EHTMLRoleType.DOCUMENT_STRUCTURE,
          "A large perceivable section of a web page or document, that the author feels is important enough to be included in a page summary or table of contents, for example, an area of the page containing live sporting event statistics."),
  @Deprecated
  ROLETYPE ("roletype", EHTMLRoleType.ABSTRACT, "The base role from which all other roles in this taxonomy inherit."),
  ROW ("row", EHTMLRoleType.DOCUMENT_STRUCTURE, "A row of cells in a grid."),
  // No metatype defined - so I assume document structure
  ROWGROUP ("rowgroup", EHTMLRoleType.DOCUMENT_STRUCTURE, "A group containing one or more row elements in a grid."),
  ROWHEADER ("rowheader", EHTMLRoleType.DOCUMENT_STRUCTURE, "A cell containing header information for a row in a grid."),
  SCROLLBAR ("scrollbar",
             EHTMLRoleType.WIDGET,
             "A graphical object that controls the scrolling of content within a viewing area, regardless of whether the content is fully displayed within the viewing area."),
  SEARCH ("search",
          EHTMLRoleType.LANDMARK,
          "A landmark region that contains a collection of items and objects that, as a whole, combine to create a search facility. See related form."),
  @Deprecated
  SECTION ("section", EHTMLRoleType.ABSTRACT, "A renderable structural containment unit in a document or application."),
  @Deprecated
  SECTIONHEAD ("sectionhead", EHTMLRoleType.ABSTRACT, "A structure that labels or summarizes the topic of its related section."),
  @Deprecated
  SELECT ("select", EHTMLRoleType.ABSTRACT, "A form widget that allows the user to make selections from a set of choices."),
  SEPARATOR ("separator",
             EHTMLRoleType.DOCUMENT_STRUCTURE,
             "A divider that separates and distinguishes sections of content or groups of menuitems."),
  SLIDER ("slider", EHTMLRoleType.WIDGET, "A user input where the user selects a value from within a given range."),
  SPINBUTTON ("spinbutton", EHTMLRoleType.WIDGET, "A form of range that expects the user to select from among discrete choices."),
  STATUS ("status",
          EHTMLRoleType.WIDGET,
          "A container whose content is advisory information for the user but is not important enough to justify an alert, often but not necessarily presented as a status bar. See related alert."),
  @Deprecated
  STRUCTURE ("structure", EHTMLRoleType.ABSTRACT, "A document structural element."),
  TAB ("tab",
       EHTMLRoleType.WIDGET,
       "A grouping label providing a mechanism for selecting the tab content that is to be rendered to the user."),
  TABLIST ("tablist", EHTMLRoleType.WIDGET_CONTAINER, "A list of tab elements, which are references to tabpanel elements."),
  TABPANEL ("tabpanel",
            EHTMLRoleType.WIDGET,
            "A container for the resources associated with a tab, where each tab is contained in a tablist."),
  TEXTBOX ("textbox", EHTMLRoleType.WIDGET, "Input that allows free-form text as its value."),
  TIMER ("timer",
         EHTMLRoleType.WIDGET,
         "A type of live region containing a numerical counter which indicates an amount of elapsed time from a start point, or the time remaining until an end point."),
  TOOLBAR ("toolbar",
           EHTMLRoleType.DOCUMENT_STRUCTURE,
           "A collection of commonly used function buttons represented in compact visual form."),
  TOOLTIP ("tooltip", EHTMLRoleType.WIDGET, "A contextual popup that displays a description for an element."),
  TREE ("tree",
        EHTMLRoleType.WIDGET_CONTAINER,
        "A type of list that may contain sub-level nested groups that can be collapsed and expanded."),
  TREEGRID ("treegrid",
            EHTMLRoleType.WIDGET_CONTAINER,
            "A grid whose rows can be expanded and collapsed in the same manner as for a tree."),
  TREEITEM ("treeitem",
            EHTMLRoleType.WIDGET,
            "An option item of a tree. This is an element within a tree that may be expanded or collapsed if it contains a sub-level group of treeitems."),
  @Deprecated
  WIDGET ("widget", EHTMLRoleType.ABSTRACT, "An interactive component of a graphical user interface (GUI)."),
  @Deprecated
  WINDOW ("window", EHTMLRoleType.ABSTRACT, "A browser or application window.");

  private final String m_sID;
  private final EHTMLRoleType m_eType;
  private final String m_sDescription;

  EHTMLRole (@Nonnull @Nonempty final String sID, @Nonnull final EHTMLRoleType eType, @Nonnull @Nonempty final String sDescription)
  {
    m_sID = sID;
    m_eType = eType;
    m_sDescription = sDescription;
  }

  @Nonnull
  @Nonempty
  public String getID ()
  {
    return m_sID;
  }

  @Nonnull
  public EHTMLRoleType getType ()
  {
    return m_eType;
  }

  public boolean isAbstract ()
  {
    return m_eType == EHTMLRoleType.ABSTRACT;
  }

  @Nonnull
  @Nonempty
  public String getDescription ()
  {
    return m_sDescription;
  }

  @Nullable
  public static EHTMLRole getFromIDOrNull (@Nullable final String sID)
  {
    return EnumHelper.getFromIDOrNull (EHTMLRole.class, sID);
  }
}
