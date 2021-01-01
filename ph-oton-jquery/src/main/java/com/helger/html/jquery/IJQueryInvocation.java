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
package com.helger.html.jquery;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.helger.commons.annotation.Nonempty;
import com.helger.html.css.ICSSClassProvider;
import com.helger.html.jscode.IJSInvocation;
import com.helger.html.jscode.JSFieldRef;

/**
 * This file is generated - do NOT edit!
 * 
 * @author com.helger.html.jquery.supplementary.main.Main_IJQueryInvocation
 * @param <IMPLTYPE>
 *        Implementation type
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
  @Nonnull
  IMPLTYPE jqinvoke (@Nonnull @Nonempty String sMethod);

  /**
   * Adds a CSSClassProvider selector as a string argument.
   * 
   * @param aArgument
   *        value to be added as an argument
   * @return this
   */
  @Nonnull
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
  @Nonnull
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
  @Nonnull
  default IMPLTYPE arg (@Nullable JQuerySelectorList aArgument)
  {
    return aArgument == null ? argNull () : arg (aArgument.getAsExpression ());
  }

  // Properties of jQuery Object Instances

  /**
   * @return The invocation of the jQuery field <code>jquery</code>
   */
  @Nonnull
  default JSFieldRef jquery ()
  {
    return ref ("jquery");
  }

  /**
   * @return The invocation of the jQuery field <code>length()</code>
   */
  @Nonnull
  default JSFieldRef length ()
  {
    return ref ("length");
  }

  @Nonnull
  default IMPLTYPE add ()
  {
    return jqinvoke ("add");
  }

  @Nonnull
  default IMPLTYPE addBack ()
  {
    return jqinvoke ("addBack");
  }

  @Nonnull
  default IMPLTYPE addClass ()
  {
    return jqinvoke ("addClass");
  }

  @Nonnull
  default IMPLTYPE after ()
  {
    return jqinvoke ("after");
  }

  @Nonnull
  default IMPLTYPE ajaxComplete ()
  {
    return jqinvoke ("ajaxComplete");
  }

  @Nonnull
  default IMPLTYPE ajaxError ()
  {
    return jqinvoke ("ajaxError");
  }

  @Nonnull
  default IMPLTYPE ajaxSend ()
  {
    return jqinvoke ("ajaxSend");
  }

  @Nonnull
  default IMPLTYPE ajaxStart ()
  {
    return jqinvoke ("ajaxStart");
  }

  @Nonnull
  default IMPLTYPE ajaxStop ()
  {
    return jqinvoke ("ajaxStop");
  }

  @Nonnull
  default IMPLTYPE ajaxSuccess ()
  {
    return jqinvoke ("ajaxSuccess");
  }

  @Deprecated
  @Nonnull
  default IMPLTYPE andSelf ()
  {
    return jqinvoke ("andSelf");
  }

  @Nonnull
  default IMPLTYPE animate ()
  {
    return jqinvoke ("animate");
  }

  @Nonnull
  default IMPLTYPE append ()
  {
    return jqinvoke ("append");
  }

  @Nonnull
  default IMPLTYPE appendTo ()
  {
    return jqinvoke ("appendTo");
  }

  @Nonnull
  default IMPLTYPE attr ()
  {
    return jqinvoke ("attr");
  }

  @Nonnull
  default IMPLTYPE before ()
  {
    return jqinvoke ("before");
  }

  @Deprecated
  @Nonnull
  default IMPLTYPE bind ()
  {
    return jqinvoke ("bind");
  }

  @Nonnull
  default IMPLTYPE blur ()
  {
    return jqinvoke ("blur");
  }

  @Nonnull
  default IMPLTYPE callbacks_add ()
  {
    return jqinvoke ("add");
  }

  @Nonnull
  default IMPLTYPE callbacks_disable ()
  {
    return jqinvoke ("disable");
  }

  @Nonnull
  default IMPLTYPE callbacks_disabled ()
  {
    return jqinvoke ("disabled");
  }

  @Nonnull
  default IMPLTYPE callbacks_empty ()
  {
    return jqinvoke ("empty");
  }

  @Nonnull
  default IMPLTYPE callbacks_fire ()
  {
    return jqinvoke ("fire");
  }

  @Nonnull
  default IMPLTYPE callbacks_fireWith ()
  {
    return jqinvoke ("fireWith");
  }

  @Nonnull
  default IMPLTYPE callbacks_fired ()
  {
    return jqinvoke ("fired");
  }

  @Nonnull
  default IMPLTYPE callbacks_has ()
  {
    return jqinvoke ("has");
  }

  @Nonnull
  default IMPLTYPE callbacks_lock ()
  {
    return jqinvoke ("lock");
  }

  @Nonnull
  default IMPLTYPE callbacks_locked ()
  {
    return jqinvoke ("locked");
  }

  @Nonnull
  default IMPLTYPE callbacks_remove ()
  {
    return jqinvoke ("remove");
  }

  @Nonnull
  default IMPLTYPE change ()
  {
    return jqinvoke ("change");
  }

  @Nonnull
  default IMPLTYPE children ()
  {
    return jqinvoke ("children");
  }

  @Nonnull
  default IMPLTYPE clearQueue ()
  {
    return jqinvoke ("clearQueue");
  }

  @Nonnull
  default IMPLTYPE click ()
  {
    return jqinvoke ("click");
  }

  @Nonnull
  default IMPLTYPE _clone ()
  {
    return jqinvoke ("clone");
  }

  @Nonnull
  default IMPLTYPE closest ()
  {
    return jqinvoke ("closest");
  }

  @Nonnull
  default IMPLTYPE contents ()
  {
    return jqinvoke ("contents");
  }

  @Nonnull
  default IMPLTYPE contextmenu ()
  {
    return jqinvoke ("contextmenu");
  }

  @Nonnull
  default IMPLTYPE css ()
  {
    return jqinvoke ("css");
  }

  @Nonnull
  default IMPLTYPE data ()
  {
    return jqinvoke ("data");
  }

  @Nonnull
  default IMPLTYPE dblclick ()
  {
    return jqinvoke ("dblclick");
  }

  @Nonnull
  default IMPLTYPE deferred_always ()
  {
    return jqinvoke ("always");
  }

  @Nonnull
  default IMPLTYPE deferred_done ()
  {
    return jqinvoke ("done");
  }

  @Nonnull
  default IMPLTYPE deferred_fail ()
  {
    return jqinvoke ("fail");
  }

  @Deprecated
  @Nonnull
  default IMPLTYPE deferred_isRejected ()
  {
    return jqinvoke ("isRejected");
  }

  @Deprecated
  @Nonnull
  default IMPLTYPE deferred_isResolved ()
  {
    return jqinvoke ("isResolved");
  }

  @Nonnull
  default IMPLTYPE deferred_notify ()
  {
    return jqinvoke ("notify");
  }

  @Nonnull
  default IMPLTYPE deferred_notifyWith ()
  {
    return jqinvoke ("notifyWith");
  }

  @Deprecated
  @Nonnull
  default IMPLTYPE deferred_pipe ()
  {
    return jqinvoke ("pipe");
  }

  @Nonnull
  default IMPLTYPE deferred_progress ()
  {
    return jqinvoke ("progress");
  }

  @Nonnull
  default IMPLTYPE deferred_promise ()
  {
    return jqinvoke ("promise");
  }

  @Nonnull
  default IMPLTYPE deferred_reject ()
  {
    return jqinvoke ("reject");
  }

  @Nonnull
  default IMPLTYPE deferred_rejectWith ()
  {
    return jqinvoke ("rejectWith");
  }

  @Nonnull
  default IMPLTYPE deferred_resolve ()
  {
    return jqinvoke ("resolve");
  }

  @Nonnull
  default IMPLTYPE deferred_resolveWith ()
  {
    return jqinvoke ("resolveWith");
  }

  @Nonnull
  default IMPLTYPE deferred_state ()
  {
    return jqinvoke ("state");
  }

  @Nonnull
  default IMPLTYPE deferred_then ()
  {
    return jqinvoke ("then");
  }

  @Nonnull
  default IMPLTYPE delay ()
  {
    return jqinvoke ("delay");
  }

  @Deprecated
  @Nonnull
  default IMPLTYPE delegate ()
  {
    return jqinvoke ("delegate");
  }

  @Nonnull
  default IMPLTYPE dequeue ()
  {
    return jqinvoke ("dequeue");
  }

  @Nonnull
  default IMPLTYPE detach ()
  {
    return jqinvoke ("detach");
  }

  @Deprecated
  @Nonnull
  default IMPLTYPE die ()
  {
    return jqinvoke ("die");
  }

  @Nonnull
  default IMPLTYPE each ()
  {
    return jqinvoke ("each");
  }

  @Nonnull
  default IMPLTYPE empty ()
  {
    return jqinvoke ("empty");
  }

  @Nonnull
  default IMPLTYPE end ()
  {
    return jqinvoke ("end");
  }

  @Nonnull
  default IMPLTYPE _eq ()
  {
    return jqinvoke ("eq");
  }

  @Deprecated
  @Nonnull
  default IMPLTYPE error ()
  {
    return jqinvoke ("error");
  }

  @Nonnull
  default IMPLTYPE event_isDefaultPrevented ()
  {
    return jqinvoke ("isDefaultPrevented");
  }

  @Nonnull
  default IMPLTYPE event_isImmediatePropagationStopped ()
  {
    return jqinvoke ("isImmediatePropagationStopped");
  }

  @Nonnull
  default IMPLTYPE event_isPropagationStopped ()
  {
    return jqinvoke ("isPropagationStopped");
  }

  @Nonnull
  default IMPLTYPE event_preventDefault ()
  {
    return jqinvoke ("preventDefault");
  }

  @Nonnull
  default IMPLTYPE event_stopImmediatePropagation ()
  {
    return jqinvoke ("stopImmediatePropagation");
  }

  @Nonnull
  default IMPLTYPE event_stopPropagation ()
  {
    return jqinvoke ("stopPropagation");
  }

  @Nonnull
  default IMPLTYPE fadeIn ()
  {
    return jqinvoke ("fadeIn");
  }

  @Nonnull
  default IMPLTYPE fadeOut ()
  {
    return jqinvoke ("fadeOut");
  }

  @Nonnull
  default IMPLTYPE fadeTo ()
  {
    return jqinvoke ("fadeTo");
  }

  @Nonnull
  default IMPLTYPE fadeToggle ()
  {
    return jqinvoke ("fadeToggle");
  }

  @Nonnull
  default IMPLTYPE filter ()
  {
    return jqinvoke ("filter");
  }

  @Nonnull
  default IMPLTYPE find ()
  {
    return jqinvoke ("find");
  }

  @Nonnull
  default IMPLTYPE finish ()
  {
    return jqinvoke ("finish");
  }

  @Nonnull
  default IMPLTYPE first ()
  {
    return jqinvoke ("first");
  }

  @Nonnull
  default IMPLTYPE focus ()
  {
    return jqinvoke ("focus");
  }

  @Nonnull
  default IMPLTYPE focusin ()
  {
    return jqinvoke ("focusin");
  }

  @Nonnull
  default IMPLTYPE focusout ()
  {
    return jqinvoke ("focusout");
  }

  @Nonnull
  default IMPLTYPE get ()
  {
    return jqinvoke ("get");
  }

  @Nonnull
  default IMPLTYPE has ()
  {
    return jqinvoke ("has");
  }

  @Nonnull
  default IMPLTYPE hasClass ()
  {
    return jqinvoke ("hasClass");
  }

  @Nonnull
  default IMPLTYPE height ()
  {
    return jqinvoke ("height");
  }

  @Nonnull
  default IMPLTYPE hide ()
  {
    return jqinvoke ("hide");
  }

  @Nonnull
  default IMPLTYPE hover ()
  {
    return jqinvoke ("hover");
  }

  @Nonnull
  default IMPLTYPE html ()
  {
    return jqinvoke ("html");
  }

  @Nonnull
  default IMPLTYPE index ()
  {
    return jqinvoke ("index");
  }

  @Nonnull
  default IMPLTYPE innerHeight ()
  {
    return jqinvoke ("innerHeight");
  }

  @Nonnull
  default IMPLTYPE innerWidth ()
  {
    return jqinvoke ("innerWidth");
  }

  @Nonnull
  default IMPLTYPE insertAfter ()
  {
    return jqinvoke ("insertAfter");
  }

  @Nonnull
  default IMPLTYPE insertBefore ()
  {
    return jqinvoke ("insertBefore");
  }

  @Nonnull
  default IMPLTYPE is ()
  {
    return jqinvoke ("is");
  }

  @Nonnull
  default IMPLTYPE keydown ()
  {
    return jqinvoke ("keydown");
  }

  @Nonnull
  default IMPLTYPE keypress ()
  {
    return jqinvoke ("keypress");
  }

  @Nonnull
  default IMPLTYPE keyup ()
  {
    return jqinvoke ("keyup");
  }

  @Nonnull
  default IMPLTYPE last ()
  {
    return jqinvoke ("last");
  }

  @Deprecated
  @Nonnull
  default IMPLTYPE live ()
  {
    return jqinvoke ("live");
  }

  @Nonnull
  default IMPLTYPE load ()
  {
    return jqinvoke ("load");
  }

  @Nonnull
  default IMPLTYPE map ()
  {
    return jqinvoke ("map");
  }

  @Nonnull
  default IMPLTYPE mousedown ()
  {
    return jqinvoke ("mousedown");
  }

  @Nonnull
  default IMPLTYPE mouseenter ()
  {
    return jqinvoke ("mouseenter");
  }

  @Nonnull
  default IMPLTYPE mouseleave ()
  {
    return jqinvoke ("mouseleave");
  }

  @Nonnull
  default IMPLTYPE mousemove ()
  {
    return jqinvoke ("mousemove");
  }

  @Nonnull
  default IMPLTYPE mouseout ()
  {
    return jqinvoke ("mouseout");
  }

  @Nonnull
  default IMPLTYPE mouseover ()
  {
    return jqinvoke ("mouseover");
  }

  @Nonnull
  default IMPLTYPE mouseup ()
  {
    return jqinvoke ("mouseup");
  }

  @Nonnull
  default IMPLTYPE next ()
  {
    return jqinvoke ("next");
  }

  @Nonnull
  default IMPLTYPE nextAll ()
  {
    return jqinvoke ("nextAll");
  }

  @Nonnull
  default IMPLTYPE nextUntil ()
  {
    return jqinvoke ("nextUntil");
  }

  @Nonnull
  default IMPLTYPE _not ()
  {
    return jqinvoke ("not");
  }

  @Nonnull
  default IMPLTYPE off ()
  {
    return jqinvoke ("off");
  }

  @Nonnull
  default IMPLTYPE offset ()
  {
    return jqinvoke ("offset");
  }

  @Nonnull
  default IMPLTYPE offsetParent ()
  {
    return jqinvoke ("offsetParent");
  }

  @Nonnull
  default IMPLTYPE on ()
  {
    return jqinvoke ("on");
  }

  @Nonnull
  default IMPLTYPE one ()
  {
    return jqinvoke ("one");
  }

  @Nonnull
  default IMPLTYPE outerHeight ()
  {
    return jqinvoke ("outerHeight");
  }

  @Nonnull
  default IMPLTYPE outerWidth ()
  {
    return jqinvoke ("outerWidth");
  }

  @Nonnull
  default IMPLTYPE parent ()
  {
    return jqinvoke ("parent");
  }

  @Nonnull
  default IMPLTYPE parents ()
  {
    return jqinvoke ("parents");
  }

  @Nonnull
  default IMPLTYPE parentsUntil ()
  {
    return jqinvoke ("parentsUntil");
  }

  @Nonnull
  default IMPLTYPE position ()
  {
    return jqinvoke ("position");
  }

  @Nonnull
  default IMPLTYPE prepend ()
  {
    return jqinvoke ("prepend");
  }

  @Nonnull
  default IMPLTYPE prependTo ()
  {
    return jqinvoke ("prependTo");
  }

  @Nonnull
  default IMPLTYPE prev ()
  {
    return jqinvoke ("prev");
  }

  @Nonnull
  default IMPLTYPE prevAll ()
  {
    return jqinvoke ("prevAll");
  }

  @Nonnull
  default IMPLTYPE prevUntil ()
  {
    return jqinvoke ("prevUntil");
  }

  @Nonnull
  default IMPLTYPE promise ()
  {
    return jqinvoke ("promise");
  }

  @Nonnull
  default IMPLTYPE prop ()
  {
    return jqinvoke ("prop");
  }

  @Nonnull
  default IMPLTYPE pushStack ()
  {
    return jqinvoke ("pushStack");
  }

  @Nonnull
  default IMPLTYPE queue ()
  {
    return jqinvoke ("queue");
  }

  @Nonnull
  default IMPLTYPE ready ()
  {
    return jqinvoke ("ready");
  }

  @Nonnull
  default IMPLTYPE remove ()
  {
    return jqinvoke ("remove");
  }

  @Nonnull
  default IMPLTYPE removeAttr ()
  {
    return jqinvoke ("removeAttr");
  }

  @Nonnull
  default IMPLTYPE removeClass ()
  {
    return jqinvoke ("removeClass");
  }

  @Nonnull
  default IMPLTYPE removeData ()
  {
    return jqinvoke ("removeData");
  }

  @Nonnull
  default IMPLTYPE removeProp ()
  {
    return jqinvoke ("removeProp");
  }

  @Nonnull
  default IMPLTYPE replaceAll ()
  {
    return jqinvoke ("replaceAll");
  }

  @Nonnull
  default IMPLTYPE replaceWith ()
  {
    return jqinvoke ("replaceWith");
  }

  @Nonnull
  default IMPLTYPE resize ()
  {
    return jqinvoke ("resize");
  }

  @Nonnull
  default IMPLTYPE scroll ()
  {
    return jqinvoke ("scroll");
  }

  @Nonnull
  default IMPLTYPE scrollLeft ()
  {
    return jqinvoke ("scrollLeft");
  }

  @Nonnull
  default IMPLTYPE scrollTop ()
  {
    return jqinvoke ("scrollTop");
  }

  @Nonnull
  default IMPLTYPE select ()
  {
    return jqinvoke ("select");
  }

  @Nonnull
  default IMPLTYPE serialize ()
  {
    return jqinvoke ("serialize");
  }

  @Nonnull
  default IMPLTYPE serializeArray ()
  {
    return jqinvoke ("serializeArray");
  }

  @Nonnull
  default IMPLTYPE show ()
  {
    return jqinvoke ("show");
  }

  @Nonnull
  default IMPLTYPE siblings ()
  {
    return jqinvoke ("siblings");
  }

  @Deprecated
  @Nonnull
  default IMPLTYPE size ()
  {
    return jqinvoke ("size");
  }

  @Nonnull
  default IMPLTYPE slice ()
  {
    return jqinvoke ("slice");
  }

  @Nonnull
  default IMPLTYPE slideDown ()
  {
    return jqinvoke ("slideDown");
  }

  @Nonnull
  default IMPLTYPE slideToggle ()
  {
    return jqinvoke ("slideToggle");
  }

  @Nonnull
  default IMPLTYPE slideUp ()
  {
    return jqinvoke ("slideUp");
  }

  @Nonnull
  default IMPLTYPE stop ()
  {
    return jqinvoke ("stop");
  }

  @Nonnull
  default IMPLTYPE submit ()
  {
    return jqinvoke ("submit");
  }

  @Nonnull
  default IMPLTYPE text ()
  {
    return jqinvoke ("text");
  }

  @Nonnull
  default IMPLTYPE toArray ()
  {
    return jqinvoke ("toArray");
  }

  @Nonnull
  default IMPLTYPE toggle ()
  {
    return jqinvoke ("toggle");
  }

  @Nonnull
  default IMPLTYPE toggleClass ()
  {
    return jqinvoke ("toggleClass");
  }

  @Nonnull
  default IMPLTYPE trigger ()
  {
    return jqinvoke ("trigger");
  }

  @Nonnull
  default IMPLTYPE triggerHandler ()
  {
    return jqinvoke ("triggerHandler");
  }

  @Deprecated
  @Nonnull
  default IMPLTYPE unbind ()
  {
    return jqinvoke ("unbind");
  }

  @Deprecated
  @Nonnull
  default IMPLTYPE undelegate ()
  {
    return jqinvoke ("undelegate");
  }

  @Deprecated
  @Nonnull
  default IMPLTYPE unload ()
  {
    return jqinvoke ("unload");
  }

  @Nonnull
  default IMPLTYPE unwrap ()
  {
    return jqinvoke ("unwrap");
  }

  @Nonnull
  default IMPLTYPE val ()
  {
    return jqinvoke ("val");
  }

  @Nonnull
  default IMPLTYPE width ()
  {
    return jqinvoke ("width");
  }

  @Nonnull
  default IMPLTYPE wrap ()
  {
    return jqinvoke ("wrap");
  }

  @Nonnull
  default IMPLTYPE wrapAll ()
  {
    return jqinvoke ("wrapAll");
  }

  @Nonnull
  default IMPLTYPE wrapInner ()
  {
    return jqinvoke ("wrapInner");
  }

}
