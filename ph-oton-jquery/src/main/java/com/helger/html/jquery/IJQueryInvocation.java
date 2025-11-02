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
package com.helger.html.jquery;

import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.Nullable;

import com.helger.annotation.Nonempty;
import com.helger.html.css.ICSSClassProvider;
import com.helger.html.jscode.IJSInvocation;
import com.helger.html.jscode.JSFieldRef;

/**
 * This file is generated - do NOT edit!
 * @author com.helger.html.jquery.supplementary.main.Main_IJQueryInvocation
 * @param <IMPLTYPE> Implementation type
*/
public interface IJQueryInvocation <IMPLTYPE extends IJQueryInvocation <IMPLTYPE>> extends IJSInvocation <IMPLTYPE>
{
  /**
   * Invoke an arbitrary function on this jQuery object.
   * 
   * @param sMethod
   *        The method to be invoked. May neither be <code>null</code> nor
   *        empty.
   * @return A new jQuery invocation object. Never <code>null</code>.
   */
  @NonNull
  IMPLTYPE jqinvoke (@NonNull @Nonempty String sMethod);

  /**
   * Adds a CSSClassProvider selector as a string argument.
   * 
   * @param aArgument
   *        value to be added as an argument
   * @return this
   */
  @NonNull
  default IMPLTYPE arg (@Nullable ICSSClassProvider aArgument)
  {
    return aArgument == null ? argNull () : arg (aArgument.getCSSClass ());
  }

  /**
   * Adds a JQuery selector as a string argument.
   * 
   * @param aArgument
   *        value to be added as an argument
   * @return this
   */
  @NonNull
  default IMPLTYPE arg (@Nullable IJQuerySelector aArgument)
  {
    return aArgument == null ? argNull () : arg (aArgument.getExpression ());
  }

  /**
   * Adds a JQuery selector list as a string argument.
   * 
   * @param aArgument
   *        value to be added as an argument
   * @return this
   */
  @NonNull
  default IMPLTYPE arg (@Nullable JQuerySelectorList aArgument)
  {
    return aArgument == null ? argNull () : arg (aArgument.getAsExpression ());
  }

  // Properties of jQuery Object Instances

  /**
   * @return The invocation of the jQuery field <code>jquery</code>
   */
  @NonNull
  default JSFieldRef jquery ()
  {
    return ref ("jquery");
  }

  /**
   * @return The invocation of the jQuery field <code>length()</code>
   */
  @NonNull
  default JSFieldRef length ()
  {
    return ref ("length");
  }
  @NonNull
  default IMPLTYPE add ()
  {
    return jqinvoke ("add");
  }

  @NonNull
  default IMPLTYPE addBack ()
  {
    return jqinvoke ("addBack");
  }

  @NonNull
  default IMPLTYPE addClass ()
  {
    return jqinvoke ("addClass");
  }

  @NonNull
  default IMPLTYPE after ()
  {
    return jqinvoke ("after");
  }

  @NonNull
  default IMPLTYPE ajaxComplete ()
  {
    return jqinvoke ("ajaxComplete");
  }

  @NonNull
  default IMPLTYPE ajaxError ()
  {
    return jqinvoke ("ajaxError");
  }

  @NonNull
  default IMPLTYPE ajaxSend ()
  {
    return jqinvoke ("ajaxSend");
  }

  @NonNull
  default IMPLTYPE ajaxStart ()
  {
    return jqinvoke ("ajaxStart");
  }

  @NonNull
  default IMPLTYPE ajaxStop ()
  {
    return jqinvoke ("ajaxStop");
  }

  @NonNull
  default IMPLTYPE ajaxSuccess ()
  {
    return jqinvoke ("ajaxSuccess");
  }

  @Deprecated
  @NonNull
  default IMPLTYPE andSelf ()
  {
    return jqinvoke ("andSelf");
  }

  @NonNull
  default IMPLTYPE animate ()
  {
    return jqinvoke ("animate");
  }

  @NonNull
  default IMPLTYPE append ()
  {
    return jqinvoke ("append");
  }

  @NonNull
  default IMPLTYPE appendTo ()
  {
    return jqinvoke ("appendTo");
  }

  @NonNull
  default IMPLTYPE attr ()
  {
    return jqinvoke ("attr");
  }

  @NonNull
  default IMPLTYPE before ()
  {
    return jqinvoke ("before");
  }

  @Deprecated
  @NonNull
  default IMPLTYPE bind ()
  {
    return jqinvoke ("bind");
  }

  @NonNull
  default IMPLTYPE blur ()
  {
    return jqinvoke ("blur");
  }

  @NonNull
  default IMPLTYPE callbacks_add ()
  {
    return jqinvoke ("add");
  }

  @NonNull
  default IMPLTYPE callbacks_disable ()
  {
    return jqinvoke ("disable");
  }

  @NonNull
  default IMPLTYPE callbacks_disabled ()
  {
    return jqinvoke ("disabled");
  }

  @NonNull
  default IMPLTYPE callbacks_empty ()
  {
    return jqinvoke ("empty");
  }

  @NonNull
  default IMPLTYPE callbacks_fire ()
  {
    return jqinvoke ("fire");
  }

  @NonNull
  default IMPLTYPE callbacks_fireWith ()
  {
    return jqinvoke ("fireWith");
  }

  @NonNull
  default IMPLTYPE callbacks_fired ()
  {
    return jqinvoke ("fired");
  }

  @NonNull
  default IMPLTYPE callbacks_has ()
  {
    return jqinvoke ("has");
  }

  @NonNull
  default IMPLTYPE callbacks_lock ()
  {
    return jqinvoke ("lock");
  }

  @NonNull
  default IMPLTYPE callbacks_locked ()
  {
    return jqinvoke ("locked");
  }

  @NonNull
  default IMPLTYPE callbacks_remove ()
  {
    return jqinvoke ("remove");
  }

  @NonNull
  default IMPLTYPE change ()
  {
    return jqinvoke ("change");
  }

  @NonNull
  default IMPLTYPE children ()
  {
    return jqinvoke ("children");
  }

  @NonNull
  default IMPLTYPE clearQueue ()
  {
    return jqinvoke ("clearQueue");
  }

  @NonNull
  default IMPLTYPE click ()
  {
    return jqinvoke ("click");
  }

  @NonNull
  default IMPLTYPE _clone ()
  {
    return jqinvoke ("clone");
  }

  @NonNull
  default IMPLTYPE closest ()
  {
    return jqinvoke ("closest");
  }

  @NonNull
  default IMPLTYPE contents ()
  {
    return jqinvoke ("contents");
  }

  @NonNull
  default IMPLTYPE contextmenu ()
  {
    return jqinvoke ("contextmenu");
  }

  @NonNull
  default IMPLTYPE css ()
  {
    return jqinvoke ("css");
  }

  @NonNull
  default IMPLTYPE data ()
  {
    return jqinvoke ("data");
  }

  @NonNull
  default IMPLTYPE dblclick ()
  {
    return jqinvoke ("dblclick");
  }

  @NonNull
  default IMPLTYPE deferred_always ()
  {
    return jqinvoke ("always");
  }

  @NonNull
  default IMPLTYPE deferred_done ()
  {
    return jqinvoke ("done");
  }

  @NonNull
  default IMPLTYPE deferred_fail ()
  {
    return jqinvoke ("fail");
  }

  @Deprecated
  @NonNull
  default IMPLTYPE deferred_isRejected ()
  {
    return jqinvoke ("isRejected");
  }

  @Deprecated
  @NonNull
  default IMPLTYPE deferred_isResolved ()
  {
    return jqinvoke ("isResolved");
  }

  @NonNull
  default IMPLTYPE deferred_notify ()
  {
    return jqinvoke ("notify");
  }

  @NonNull
  default IMPLTYPE deferred_notifyWith ()
  {
    return jqinvoke ("notifyWith");
  }

  @Deprecated
  @NonNull
  default IMPLTYPE deferred_pipe ()
  {
    return jqinvoke ("pipe");
  }

  @NonNull
  default IMPLTYPE deferred_progress ()
  {
    return jqinvoke ("progress");
  }

  @NonNull
  default IMPLTYPE deferred_promise ()
  {
    return jqinvoke ("promise");
  }

  @NonNull
  default IMPLTYPE deferred_reject ()
  {
    return jqinvoke ("reject");
  }

  @NonNull
  default IMPLTYPE deferred_rejectWith ()
  {
    return jqinvoke ("rejectWith");
  }

  @NonNull
  default IMPLTYPE deferred_resolve ()
  {
    return jqinvoke ("resolve");
  }

  @NonNull
  default IMPLTYPE deferred_resolveWith ()
  {
    return jqinvoke ("resolveWith");
  }

  @NonNull
  default IMPLTYPE deferred_state ()
  {
    return jqinvoke ("state");
  }

  @NonNull
  default IMPLTYPE deferred_then ()
  {
    return jqinvoke ("then");
  }

  @NonNull
  default IMPLTYPE delay ()
  {
    return jqinvoke ("delay");
  }

  @Deprecated
  @NonNull
  default IMPLTYPE delegate ()
  {
    return jqinvoke ("delegate");
  }

  @NonNull
  default IMPLTYPE dequeue ()
  {
    return jqinvoke ("dequeue");
  }

  @NonNull
  default IMPLTYPE detach ()
  {
    return jqinvoke ("detach");
  }

  @Deprecated
  @NonNull
  default IMPLTYPE die ()
  {
    return jqinvoke ("die");
  }

  @NonNull
  default IMPLTYPE each ()
  {
    return jqinvoke ("each");
  }

  @NonNull
  default IMPLTYPE empty ()
  {
    return jqinvoke ("empty");
  }

  @NonNull
  default IMPLTYPE end ()
  {
    return jqinvoke ("end");
  }

  @NonNull
  default IMPLTYPE _eq ()
  {
    return jqinvoke ("eq");
  }

  @Deprecated
  @NonNull
  default IMPLTYPE error ()
  {
    return jqinvoke ("error");
  }

  @NonNull
  default IMPLTYPE event_isDefaultPrevented ()
  {
    return jqinvoke ("isDefaultPrevented");
  }

  @NonNull
  default IMPLTYPE event_isImmediatePropagationStopped ()
  {
    return jqinvoke ("isImmediatePropagationStopped");
  }

  @NonNull
  default IMPLTYPE event_isPropagationStopped ()
  {
    return jqinvoke ("isPropagationStopped");
  }

  @NonNull
  default IMPLTYPE event_preventDefault ()
  {
    return jqinvoke ("preventDefault");
  }

  @NonNull
  default IMPLTYPE event_stopImmediatePropagation ()
  {
    return jqinvoke ("stopImmediatePropagation");
  }

  @NonNull
  default IMPLTYPE event_stopPropagation ()
  {
    return jqinvoke ("stopPropagation");
  }

  @NonNull
  default IMPLTYPE fadeIn ()
  {
    return jqinvoke ("fadeIn");
  }

  @NonNull
  default IMPLTYPE fadeOut ()
  {
    return jqinvoke ("fadeOut");
  }

  @NonNull
  default IMPLTYPE fadeTo ()
  {
    return jqinvoke ("fadeTo");
  }

  @NonNull
  default IMPLTYPE fadeToggle ()
  {
    return jqinvoke ("fadeToggle");
  }

  @NonNull
  default IMPLTYPE filter ()
  {
    return jqinvoke ("filter");
  }

  @NonNull
  default IMPLTYPE find ()
  {
    return jqinvoke ("find");
  }

  @NonNull
  default IMPLTYPE finish ()
  {
    return jqinvoke ("finish");
  }

  @NonNull
  default IMPLTYPE first ()
  {
    return jqinvoke ("first");
  }

  @NonNull
  default IMPLTYPE focus ()
  {
    return jqinvoke ("focus");
  }

  @NonNull
  default IMPLTYPE focusin ()
  {
    return jqinvoke ("focusin");
  }

  @NonNull
  default IMPLTYPE focusout ()
  {
    return jqinvoke ("focusout");
  }

  @NonNull
  default IMPLTYPE get ()
  {
    return jqinvoke ("get");
  }

  @NonNull
  default IMPLTYPE has ()
  {
    return jqinvoke ("has");
  }

  @NonNull
  default IMPLTYPE hasClass ()
  {
    return jqinvoke ("hasClass");
  }

  @NonNull
  default IMPLTYPE height ()
  {
    return jqinvoke ("height");
  }

  @NonNull
  default IMPLTYPE hide ()
  {
    return jqinvoke ("hide");
  }

  @NonNull
  default IMPLTYPE hover ()
  {
    return jqinvoke ("hover");
  }

  @NonNull
  default IMPLTYPE html ()
  {
    return jqinvoke ("html");
  }

  @NonNull
  default IMPLTYPE index ()
  {
    return jqinvoke ("index");
  }

  @NonNull
  default IMPLTYPE innerHeight ()
  {
    return jqinvoke ("innerHeight");
  }

  @NonNull
  default IMPLTYPE innerWidth ()
  {
    return jqinvoke ("innerWidth");
  }

  @NonNull
  default IMPLTYPE insertAfter ()
  {
    return jqinvoke ("insertAfter");
  }

  @NonNull
  default IMPLTYPE insertBefore ()
  {
    return jqinvoke ("insertBefore");
  }

  @NonNull
  default IMPLTYPE is ()
  {
    return jqinvoke ("is");
  }

  @NonNull
  default IMPLTYPE keydown ()
  {
    return jqinvoke ("keydown");
  }

  @NonNull
  default IMPLTYPE keypress ()
  {
    return jqinvoke ("keypress");
  }

  @NonNull
  default IMPLTYPE keyup ()
  {
    return jqinvoke ("keyup");
  }

  @NonNull
  default IMPLTYPE last ()
  {
    return jqinvoke ("last");
  }

  @Deprecated
  @NonNull
  default IMPLTYPE live ()
  {
    return jqinvoke ("live");
  }

  @NonNull
  default IMPLTYPE load ()
  {
    return jqinvoke ("load");
  }

  @NonNull
  default IMPLTYPE map ()
  {
    return jqinvoke ("map");
  }

  @NonNull
  default IMPLTYPE mousedown ()
  {
    return jqinvoke ("mousedown");
  }

  @NonNull
  default IMPLTYPE mouseenter ()
  {
    return jqinvoke ("mouseenter");
  }

  @NonNull
  default IMPLTYPE mouseleave ()
  {
    return jqinvoke ("mouseleave");
  }

  @NonNull
  default IMPLTYPE mousemove ()
  {
    return jqinvoke ("mousemove");
  }

  @NonNull
  default IMPLTYPE mouseout ()
  {
    return jqinvoke ("mouseout");
  }

  @NonNull
  default IMPLTYPE mouseover ()
  {
    return jqinvoke ("mouseover");
  }

  @NonNull
  default IMPLTYPE mouseup ()
  {
    return jqinvoke ("mouseup");
  }

  @NonNull
  default IMPLTYPE next ()
  {
    return jqinvoke ("next");
  }

  @NonNull
  default IMPLTYPE nextAll ()
  {
    return jqinvoke ("nextAll");
  }

  @NonNull
  default IMPLTYPE nextUntil ()
  {
    return jqinvoke ("nextUntil");
  }

  @NonNull
  default IMPLTYPE _not ()
  {
    return jqinvoke ("not");
  }

  @NonNull
  default IMPLTYPE off ()
  {
    return jqinvoke ("off");
  }

  @NonNull
  default IMPLTYPE offset ()
  {
    return jqinvoke ("offset");
  }

  @NonNull
  default IMPLTYPE offsetParent ()
  {
    return jqinvoke ("offsetParent");
  }

  @NonNull
  default IMPLTYPE on ()
  {
    return jqinvoke ("on");
  }

  @NonNull
  default IMPLTYPE one ()
  {
    return jqinvoke ("one");
  }

  @NonNull
  default IMPLTYPE outerHeight ()
  {
    return jqinvoke ("outerHeight");
  }

  @NonNull
  default IMPLTYPE outerWidth ()
  {
    return jqinvoke ("outerWidth");
  }

  @NonNull
  default IMPLTYPE parent ()
  {
    return jqinvoke ("parent");
  }

  @NonNull
  default IMPLTYPE parents ()
  {
    return jqinvoke ("parents");
  }

  @NonNull
  default IMPLTYPE parentsUntil ()
  {
    return jqinvoke ("parentsUntil");
  }

  @NonNull
  default IMPLTYPE position ()
  {
    return jqinvoke ("position");
  }

  @NonNull
  default IMPLTYPE prepend ()
  {
    return jqinvoke ("prepend");
  }

  @NonNull
  default IMPLTYPE prependTo ()
  {
    return jqinvoke ("prependTo");
  }

  @NonNull
  default IMPLTYPE prev ()
  {
    return jqinvoke ("prev");
  }

  @NonNull
  default IMPLTYPE prevAll ()
  {
    return jqinvoke ("prevAll");
  }

  @NonNull
  default IMPLTYPE prevUntil ()
  {
    return jqinvoke ("prevUntil");
  }

  @NonNull
  default IMPLTYPE promise ()
  {
    return jqinvoke ("promise");
  }

  @NonNull
  default IMPLTYPE prop ()
  {
    return jqinvoke ("prop");
  }

  @NonNull
  default IMPLTYPE pushStack ()
  {
    return jqinvoke ("pushStack");
  }

  @NonNull
  default IMPLTYPE queue ()
  {
    return jqinvoke ("queue");
  }

  @NonNull
  default IMPLTYPE ready ()
  {
    return jqinvoke ("ready");
  }

  @NonNull
  default IMPLTYPE remove ()
  {
    return jqinvoke ("remove");
  }

  @NonNull
  default IMPLTYPE removeAttr ()
  {
    return jqinvoke ("removeAttr");
  }

  @NonNull
  default IMPLTYPE removeClass ()
  {
    return jqinvoke ("removeClass");
  }

  @NonNull
  default IMPLTYPE removeData ()
  {
    return jqinvoke ("removeData");
  }

  @NonNull
  default IMPLTYPE removeProp ()
  {
    return jqinvoke ("removeProp");
  }

  @NonNull
  default IMPLTYPE replaceAll ()
  {
    return jqinvoke ("replaceAll");
  }

  @NonNull
  default IMPLTYPE replaceWith ()
  {
    return jqinvoke ("replaceWith");
  }

  @NonNull
  default IMPLTYPE resize ()
  {
    return jqinvoke ("resize");
  }

  @NonNull
  default IMPLTYPE scroll ()
  {
    return jqinvoke ("scroll");
  }

  @NonNull
  default IMPLTYPE scrollLeft ()
  {
    return jqinvoke ("scrollLeft");
  }

  @NonNull
  default IMPLTYPE scrollTop ()
  {
    return jqinvoke ("scrollTop");
  }

  @NonNull
  default IMPLTYPE select ()
  {
    return jqinvoke ("select");
  }

  @NonNull
  default IMPLTYPE serialize ()
  {
    return jqinvoke ("serialize");
  }

  @NonNull
  default IMPLTYPE serializeArray ()
  {
    return jqinvoke ("serializeArray");
  }

  @NonNull
  default IMPLTYPE show ()
  {
    return jqinvoke ("show");
  }

  @NonNull
  default IMPLTYPE siblings ()
  {
    return jqinvoke ("siblings");
  }

  @Deprecated
  @NonNull
  default IMPLTYPE size ()
  {
    return jqinvoke ("size");
  }

  @NonNull
  default IMPLTYPE slice ()
  {
    return jqinvoke ("slice");
  }

  @NonNull
  default IMPLTYPE slideDown ()
  {
    return jqinvoke ("slideDown");
  }

  @NonNull
  default IMPLTYPE slideToggle ()
  {
    return jqinvoke ("slideToggle");
  }

  @NonNull
  default IMPLTYPE slideUp ()
  {
    return jqinvoke ("slideUp");
  }

  @NonNull
  default IMPLTYPE stop ()
  {
    return jqinvoke ("stop");
  }

  @NonNull
  default IMPLTYPE submit ()
  {
    return jqinvoke ("submit");
  }

  @NonNull
  default IMPLTYPE text ()
  {
    return jqinvoke ("text");
  }

  @NonNull
  default IMPLTYPE toArray ()
  {
    return jqinvoke ("toArray");
  }

  @NonNull
  default IMPLTYPE toggle ()
  {
    return jqinvoke ("toggle");
  }

  @NonNull
  default IMPLTYPE toggleClass ()
  {
    return jqinvoke ("toggleClass");
  }

  @NonNull
  default IMPLTYPE trigger ()
  {
    return jqinvoke ("trigger");
  }

  @NonNull
  default IMPLTYPE triggerHandler ()
  {
    return jqinvoke ("triggerHandler");
  }

  @Deprecated
  @NonNull
  default IMPLTYPE unbind ()
  {
    return jqinvoke ("unbind");
  }

  @Deprecated
  @NonNull
  default IMPLTYPE undelegate ()
  {
    return jqinvoke ("undelegate");
  }

  @Deprecated
  @NonNull
  default IMPLTYPE unload ()
  {
    return jqinvoke ("unload");
  }

  @NonNull
  default IMPLTYPE unwrap ()
  {
    return jqinvoke ("unwrap");
  }

  @NonNull
  default IMPLTYPE val ()
  {
    return jqinvoke ("val");
  }

  @NonNull
  default IMPLTYPE width ()
  {
    return jqinvoke ("width");
  }

  @NonNull
  default IMPLTYPE wrap ()
  {
    return jqinvoke ("wrap");
  }

  @NonNull
  default IMPLTYPE wrapAll ()
  {
    return jqinvoke ("wrapAll");
  }

  @NonNull
  default IMPLTYPE wrapInner ()
  {
    return jqinvoke ("wrapInner");
  }

}
