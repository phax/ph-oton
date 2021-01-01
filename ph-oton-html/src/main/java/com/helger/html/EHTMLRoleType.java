/**
 * Copyright (C) 2014-2021 Philip Helger (www.helger.com)
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

/**
 * Enumeration with the classification of the HTML roles. Source:
 * http://www.w3.org/TR/wai-aria/roles
 *
 * @author Philip Helger
 */
public enum EHTMLRoleType
{
  /**
   * Are used for the ontology. Authors MUST NOT not use abstract roles in
   * content.
   */
  ABSTRACT,
  /**
   * Act as standalone user interface widgets or as part of larger, composite
   * widgets.
   */
  WIDGET,
  /**
   * Act as composite user interface widgets. These roles typically act as
   * containers that manage other, contained widgets.
   */
  WIDGET_CONTAINER,
  /**
   * Describe structures that organize content in a page. Document structures
   * are not usually interactive.
   */
  DOCUMENT_STRUCTURE,
  /**
   * Are regions of the page intended as navigational landmarks.
   */
  LANDMARK;
}
