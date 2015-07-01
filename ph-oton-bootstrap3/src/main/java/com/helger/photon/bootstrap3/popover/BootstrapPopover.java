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
package com.helger.photon.bootstrap3.popover;

/**
 * Bootstrap Popover
 * 
 * @author Philip Helger
 */
public class BootstrapPopover
{
  /**
   * This event fires immediately when the show instance method is called.
   */
  public static final String JS_EVENT_SHOW = "show.bs.popover";
  /**
   * This event is fired when the popover has been made visible to the user
   * (will wait for CSS transitions to complete).
   */
  public static final String JS_EVENT_SHOWN = "shown.bs.popover";
  /**
   * This event is fired immediately when the hide instance method has been
   * called.
   */
  public static final String JS_EVENT_HIDE = "hide.bs.popover";
  /**
   * This event is fired when the popover has finished being hidden from the
   * user (will wait for CSS transitions to complete).
   */
  public static final String JS_EVENT_HIDDEN = "hidden.bs.popover";
  /**
   * This event is fired after the show.bs.popover event when the popover
   * template has been added to the DOM.
   */
  public static final String JS_EVENT_INSERTED = "inserted.bs.popover";
}
