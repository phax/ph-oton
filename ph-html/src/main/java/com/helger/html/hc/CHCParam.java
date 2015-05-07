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
package com.helger.html.hc;

import javax.annotation.concurrent.Immutable;

/**
 * Contains default request parameters for typical use cases.
 *
 * @author Philip Helger
 */
@Immutable
public final class CHCParam
{
  /**
   * Selector for a main form.
   */
  public static final String PARAM_FORM = "form";

  /**
   * Selector for an action within a form.
   */
  public static final String PARAM_ACTION = "action";

  /**
   * Selector for a nested action within a form.
   */
  public static final String PARAM_SUBACTION = "action2";

  /**
   * Selector for an object to perform an action on.
   */
  public static final String PARAM_OBJECT = "object";

  /**
   * Selector for a nest object to perform an action on.
   */
  public static final String PARAM_SUBOBJECT = "object2";

  /**
   * Selector for a state.
   */
  public static final String PARAM_STATE = "state";

  // predefined actions
  // Expand/collapse
  public static final String ACTION_EXPAND = "expand";
  public static final String ACTION_COLLAPSE = "collapse";
  public static final String ACTION_ALL_EXPAND = "aexpand";
  public static final String ACTION_ALL_COLLAPSE = "acollapse";
  // assign/drop
  public static final String ACTION_ASSIGN = "assign";
  public static final String ACTION_DROP = "drop";
  // accept/reject
  public static final String ACTION_ACCEPT = "accept";
  public static final String ACTION_REJECT = "reject";
  // Misc
  public static final String ACTION_CANCEL = "cancel";
  public static final String ACTION_COPY = "copy";
  public static final String ACTION_CREATE = "create";
  public static final String ACTION_EDIT = "edit";
  public static final String ACTION_DELETE = "delete";
  public static final String ACTION_DELETE_ALL = "delete-all";
  public static final String ACTION_RESTORE = "restore";
  public static final String ACTION_PERFORM = "perform";
  public static final String ACTION_RENAME = "rename";
  public static final String ACTION_SAVE = "save";
  public static final String ACTION_SETDEFAULT = "setdef";
  public static final String ACTION_VIEW = "view";
  public static final String ACTION_UNDELETE = "undelete";
  public static final String ACTION_UNDELETE_ALL = "undelete-all";

  // for yes/no queries
  public static final String STATE_CONFIRMED = "confirmed";
  public static final String STATE_RIGOROUS = "rigorous";
  public static final String STATE_SUBMITTED = "submitted";

  // predefined values
  // Don't change them - it is expected that they are "true" and "false"!
  public static final String VALUE_CHECKED = Boolean.TRUE.toString ();
  public static final String VALUE_UNCHECKED = Boolean.FALSE.toString ();

  private CHCParam ()
  {}
}
