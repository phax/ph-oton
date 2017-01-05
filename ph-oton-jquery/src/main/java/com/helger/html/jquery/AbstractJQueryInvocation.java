/**
 * Copyright (C) 2014-2017 Philip Helger (www.helger.com)
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
import com.helger.html.jscode.AbstractJSInvocation;
import com.helger.html.jscode.IJSExpression;
import com.helger.html.jscode.JSFieldRef;
import com.helger.html.jscode.JSFunction;

/**
 * Special invocation semantics for jQuery
 * 
 * This file is generated - do NOT edit!
 * @author com.helger.html.jquery.supplementary.main.Main_AbstractJQueryInvocation
 * @param <THISTYPE> Implementation type
 */
public abstract class AbstractJQueryInvocation <THISTYPE extends AbstractJQueryInvocation <THISTYPE>> extends AbstractJSInvocation <THISTYPE> implements IJQueryInvocation <THISTYPE>
{
  public AbstractJQueryInvocation (@Nonnull final JSFunction aFunction)
  {
    super (aFunction);
  }

  public AbstractJQueryInvocation (@Nullable final IJSExpression aLhs, @Nonnull @Nonempty final String sMethod)
  {
    super (aLhs, sMethod);
  }

  @Nonnull
  public THISTYPE arg (@Nullable final ICSSClassProvider aArgument)
  {
    return aArgument == null ? argNull () : arg (aArgument.getCSSClass ());
  }

  @Nonnull
  public THISTYPE arg (@Nullable final IJQuerySelector aArgument)
  {
    return aArgument == null ? argNull () : arg (aArgument.getExpression ());
  }

  @Nonnull
  public THISTYPE arg (@Nullable final JQuerySelectorList aArgument)
  {
    return aArgument == null ? argNull () : arg (aArgument.getAsExpression ());
  }

  // Properties of jQuery Object Instances

  @Deprecated
  @Nonnull
  public JSFieldRef context ()
  {
    return ref ("context");
  }

  @Nonnull
  public JSFieldRef jquery ()
  {
    return ref ("jquery");
  }

  @Nonnull
  public JSFieldRef length ()
  {
    return ref ("length");
  }

  // Methods start here

  @Nonnull
  public final THISTYPE add ()
  {
    return jqinvoke ("add");
  }

  @Nonnull
  public final THISTYPE addBack ()
  {
    return jqinvoke ("addBack");
  }

  @Nonnull
  public final THISTYPE addClass ()
  {
    return jqinvoke ("addClass");
  }

  @Nonnull
  public final THISTYPE after ()
  {
    return jqinvoke ("after");
  }

  @Nonnull
  public final THISTYPE ajaxComplete ()
  {
    return jqinvoke ("ajaxComplete");
  }

  @Nonnull
  public final THISTYPE ajaxError ()
  {
    return jqinvoke ("ajaxError");
  }

  @Nonnull
  public final THISTYPE ajaxSend ()
  {
    return jqinvoke ("ajaxSend");
  }

  @Nonnull
  public final THISTYPE ajaxStart ()
  {
    return jqinvoke ("ajaxStart");
  }

  @Nonnull
  public final THISTYPE ajaxStop ()
  {
    return jqinvoke ("ajaxStop");
  }

  @Nonnull
  public final THISTYPE ajaxSuccess ()
  {
    return jqinvoke ("ajaxSuccess");
  }

  @Deprecated
  @Nonnull
  public final THISTYPE andSelf ()
  {
    return jqinvoke ("andSelf");
  }

  @Nonnull
  public final THISTYPE animate ()
  {
    return jqinvoke ("animate");
  }

  @Nonnull
  public final THISTYPE append ()
  {
    return jqinvoke ("append");
  }

  @Nonnull
  public final THISTYPE appendTo ()
  {
    return jqinvoke ("appendTo");
  }

  @Nonnull
  public final THISTYPE attr ()
  {
    return jqinvoke ("attr");
  }

  @Nonnull
  public final THISTYPE before ()
  {
    return jqinvoke ("before");
  }

  @Deprecated
  @Nonnull
  public final THISTYPE bind ()
  {
    return jqinvoke ("bind");
  }

  @Nonnull
  public final THISTYPE blur ()
  {
    return jqinvoke ("blur");
  }

  @Nonnull
  public final THISTYPE callbacks_add ()
  {
    return jqinvoke ("add");
  }

  @Nonnull
  public final THISTYPE callbacks_disable ()
  {
    return jqinvoke ("disable");
  }

  @Nonnull
  public final THISTYPE callbacks_disabled ()
  {
    return jqinvoke ("disabled");
  }

  @Nonnull
  public final THISTYPE callbacks_empty ()
  {
    return jqinvoke ("empty");
  }

  @Nonnull
  public final THISTYPE callbacks_fire ()
  {
    return jqinvoke ("fire");
  }

  @Nonnull
  public final THISTYPE callbacks_fireWith ()
  {
    return jqinvoke ("fireWith");
  }

  @Nonnull
  public final THISTYPE callbacks_fired ()
  {
    return jqinvoke ("fired");
  }

  @Nonnull
  public final THISTYPE callbacks_has ()
  {
    return jqinvoke ("has");
  }

  @Nonnull
  public final THISTYPE callbacks_lock ()
  {
    return jqinvoke ("lock");
  }

  @Nonnull
  public final THISTYPE callbacks_locked ()
  {
    return jqinvoke ("locked");
  }

  @Nonnull
  public final THISTYPE callbacks_remove ()
  {
    return jqinvoke ("remove");
  }

  @Nonnull
  public final THISTYPE change ()
  {
    return jqinvoke ("change");
  }

  @Nonnull
  public final THISTYPE children ()
  {
    return jqinvoke ("children");
  }

  @Nonnull
  public final THISTYPE clearQueue ()
  {
    return jqinvoke ("clearQueue");
  }

  @Nonnull
  public final THISTYPE click ()
  {
    return jqinvoke ("click");
  }

  @Nonnull
  public final THISTYPE _clone ()
  {
    return jqinvoke ("clone");
  }

  @Nonnull
  public final THISTYPE closest ()
  {
    return jqinvoke ("closest");
  }

  @Nonnull
  public final THISTYPE contents ()
  {
    return jqinvoke ("contents");
  }

  @Nonnull
  public final THISTYPE contextmenu ()
  {
    return jqinvoke ("contextmenu");
  }

  @Nonnull
  public final THISTYPE css ()
  {
    return jqinvoke ("css");
  }

  @Nonnull
  public final THISTYPE data ()
  {
    return jqinvoke ("data");
  }

  @Nonnull
  public final THISTYPE dblclick ()
  {
    return jqinvoke ("dblclick");
  }

  @Nonnull
  public final THISTYPE deferred_always ()
  {
    return jqinvoke ("always");
  }

  @Nonnull
  public final THISTYPE deferred_done ()
  {
    return jqinvoke ("done");
  }

  @Nonnull
  public final THISTYPE deferred_fail ()
  {
    return jqinvoke ("fail");
  }

  @Deprecated
  @Nonnull
  public final THISTYPE deferred_isRejected ()
  {
    return jqinvoke ("isRejected");
  }

  @Deprecated
  @Nonnull
  public final THISTYPE deferred_isResolved ()
  {
    return jqinvoke ("isResolved");
  }

  @Nonnull
  public final THISTYPE deferred_notify ()
  {
    return jqinvoke ("notify");
  }

  @Nonnull
  public final THISTYPE deferred_notifyWith ()
  {
    return jqinvoke ("notifyWith");
  }

  @Deprecated
  @Nonnull
  public final THISTYPE deferred_pipe ()
  {
    return jqinvoke ("pipe");
  }

  @Nonnull
  public final THISTYPE deferred_progress ()
  {
    return jqinvoke ("progress");
  }

  @Nonnull
  public final THISTYPE deferred_promise ()
  {
    return jqinvoke ("promise");
  }

  @Nonnull
  public final THISTYPE deferred_reject ()
  {
    return jqinvoke ("reject");
  }

  @Nonnull
  public final THISTYPE deferred_rejectWith ()
  {
    return jqinvoke ("rejectWith");
  }

  @Nonnull
  public final THISTYPE deferred_resolve ()
  {
    return jqinvoke ("resolve");
  }

  @Nonnull
  public final THISTYPE deferred_resolveWith ()
  {
    return jqinvoke ("resolveWith");
  }

  @Nonnull
  public final THISTYPE deferred_state ()
  {
    return jqinvoke ("state");
  }

  @Nonnull
  public final THISTYPE deferred_then ()
  {
    return jqinvoke ("then");
  }

  @Nonnull
  public final THISTYPE delay ()
  {
    return jqinvoke ("delay");
  }

  @Deprecated
  @Nonnull
  public final THISTYPE delegate ()
  {
    return jqinvoke ("delegate");
  }

  @Nonnull
  public final THISTYPE dequeue ()
  {
    return jqinvoke ("dequeue");
  }

  @Nonnull
  public final THISTYPE detach ()
  {
    return jqinvoke ("detach");
  }

  @Deprecated
  @Nonnull
  public final THISTYPE die ()
  {
    return jqinvoke ("die");
  }

  @Nonnull
  public final THISTYPE each ()
  {
    return jqinvoke ("each");
  }

  @Nonnull
  public final THISTYPE empty ()
  {
    return jqinvoke ("empty");
  }

  @Nonnull
  public final THISTYPE end ()
  {
    return jqinvoke ("end");
  }

  @Nonnull
  public final THISTYPE _eq ()
  {
    return jqinvoke ("eq");
  }

  @Deprecated
  @Nonnull
  public final THISTYPE error ()
  {
    return jqinvoke ("error");
  }

  @Nonnull
  public final THISTYPE event_isDefaultPrevented ()
  {
    return jqinvoke ("isDefaultPrevented");
  }

  @Nonnull
  public final THISTYPE event_isImmediatePropagationStopped ()
  {
    return jqinvoke ("isImmediatePropagationStopped");
  }

  @Nonnull
  public final THISTYPE event_isPropagationStopped ()
  {
    return jqinvoke ("isPropagationStopped");
  }

  @Nonnull
  public final THISTYPE event_preventDefault ()
  {
    return jqinvoke ("preventDefault");
  }

  @Nonnull
  public final THISTYPE event_stopImmediatePropagation ()
  {
    return jqinvoke ("stopImmediatePropagation");
  }

  @Nonnull
  public final THISTYPE event_stopPropagation ()
  {
    return jqinvoke ("stopPropagation");
  }

  @Nonnull
  public final THISTYPE fadeIn ()
  {
    return jqinvoke ("fadeIn");
  }

  @Nonnull
  public final THISTYPE fadeOut ()
  {
    return jqinvoke ("fadeOut");
  }

  @Nonnull
  public final THISTYPE fadeTo ()
  {
    return jqinvoke ("fadeTo");
  }

  @Nonnull
  public final THISTYPE fadeToggle ()
  {
    return jqinvoke ("fadeToggle");
  }

  @Nonnull
  public final THISTYPE filter ()
  {
    return jqinvoke ("filter");
  }

  @Nonnull
  public final THISTYPE find ()
  {
    return jqinvoke ("find");
  }

  @Nonnull
  public final THISTYPE finish ()
  {
    return jqinvoke ("finish");
  }

  @Nonnull
  public final THISTYPE first ()
  {
    return jqinvoke ("first");
  }

  @Nonnull
  public final THISTYPE focus ()
  {
    return jqinvoke ("focus");
  }

  @Nonnull
  public final THISTYPE focusin ()
  {
    return jqinvoke ("focusin");
  }

  @Nonnull
  public final THISTYPE focusout ()
  {
    return jqinvoke ("focusout");
  }

  @Nonnull
  public final THISTYPE get ()
  {
    return jqinvoke ("get");
  }

  @Nonnull
  public final THISTYPE has ()
  {
    return jqinvoke ("has");
  }

  @Nonnull
  public final THISTYPE hasClass ()
  {
    return jqinvoke ("hasClass");
  }

  @Nonnull
  public final THISTYPE height ()
  {
    return jqinvoke ("height");
  }

  @Nonnull
  public final THISTYPE hide ()
  {
    return jqinvoke ("hide");
  }

  @Nonnull
  public final THISTYPE hover ()
  {
    return jqinvoke ("hover");
  }

  @Nonnull
  public final THISTYPE html ()
  {
    return jqinvoke ("html");
  }

  @Nonnull
  public final THISTYPE index ()
  {
    return jqinvoke ("index");
  }

  @Nonnull
  public final THISTYPE innerHeight ()
  {
    return jqinvoke ("innerHeight");
  }

  @Nonnull
  public final THISTYPE innerWidth ()
  {
    return jqinvoke ("innerWidth");
  }

  @Nonnull
  public final THISTYPE insertAfter ()
  {
    return jqinvoke ("insertAfter");
  }

  @Nonnull
  public final THISTYPE insertBefore ()
  {
    return jqinvoke ("insertBefore");
  }

  @Nonnull
  public final THISTYPE is ()
  {
    return jqinvoke ("is");
  }

  @Nonnull
  public final THISTYPE keydown ()
  {
    return jqinvoke ("keydown");
  }

  @Nonnull
  public final THISTYPE keypress ()
  {
    return jqinvoke ("keypress");
  }

  @Nonnull
  public final THISTYPE keyup ()
  {
    return jqinvoke ("keyup");
  }

  @Nonnull
  public final THISTYPE last ()
  {
    return jqinvoke ("last");
  }

  @Deprecated
  @Nonnull
  public final THISTYPE live ()
  {
    return jqinvoke ("live");
  }

  @Nonnull
  public final THISTYPE load ()
  {
    return jqinvoke ("load");
  }

  @Nonnull
  public final THISTYPE map ()
  {
    return jqinvoke ("map");
  }

  @Nonnull
  public final THISTYPE mousedown ()
  {
    return jqinvoke ("mousedown");
  }

  @Nonnull
  public final THISTYPE mouseenter ()
  {
    return jqinvoke ("mouseenter");
  }

  @Nonnull
  public final THISTYPE mouseleave ()
  {
    return jqinvoke ("mouseleave");
  }

  @Nonnull
  public final THISTYPE mousemove ()
  {
    return jqinvoke ("mousemove");
  }

  @Nonnull
  public final THISTYPE mouseout ()
  {
    return jqinvoke ("mouseout");
  }

  @Nonnull
  public final THISTYPE mouseover ()
  {
    return jqinvoke ("mouseover");
  }

  @Nonnull
  public final THISTYPE mouseup ()
  {
    return jqinvoke ("mouseup");
  }

  @Nonnull
  public final THISTYPE next ()
  {
    return jqinvoke ("next");
  }

  @Nonnull
  public final THISTYPE nextAll ()
  {
    return jqinvoke ("nextAll");
  }

  @Nonnull
  public final THISTYPE nextUntil ()
  {
    return jqinvoke ("nextUntil");
  }

  @Nonnull
  public final THISTYPE _not ()
  {
    return jqinvoke ("not");
  }

  @Nonnull
  public final THISTYPE off ()
  {
    return jqinvoke ("off");
  }

  @Nonnull
  public final THISTYPE offset ()
  {
    return jqinvoke ("offset");
  }

  @Nonnull
  public final THISTYPE offsetParent ()
  {
    return jqinvoke ("offsetParent");
  }

  @Nonnull
  public final THISTYPE on ()
  {
    return jqinvoke ("on");
  }

  @Nonnull
  public final THISTYPE one ()
  {
    return jqinvoke ("one");
  }

  @Nonnull
  public final THISTYPE outerHeight ()
  {
    return jqinvoke ("outerHeight");
  }

  @Nonnull
  public final THISTYPE outerWidth ()
  {
    return jqinvoke ("outerWidth");
  }

  @Nonnull
  public final THISTYPE parent ()
  {
    return jqinvoke ("parent");
  }

  @Nonnull
  public final THISTYPE parents ()
  {
    return jqinvoke ("parents");
  }

  @Nonnull
  public final THISTYPE parentsUntil ()
  {
    return jqinvoke ("parentsUntil");
  }

  @Nonnull
  public final THISTYPE position ()
  {
    return jqinvoke ("position");
  }

  @Nonnull
  public final THISTYPE prepend ()
  {
    return jqinvoke ("prepend");
  }

  @Nonnull
  public final THISTYPE prependTo ()
  {
    return jqinvoke ("prependTo");
  }

  @Nonnull
  public final THISTYPE prev ()
  {
    return jqinvoke ("prev");
  }

  @Nonnull
  public final THISTYPE prevAll ()
  {
    return jqinvoke ("prevAll");
  }

  @Nonnull
  public final THISTYPE prevUntil ()
  {
    return jqinvoke ("prevUntil");
  }

  @Nonnull
  public final THISTYPE promise ()
  {
    return jqinvoke ("promise");
  }

  @Nonnull
  public final THISTYPE prop ()
  {
    return jqinvoke ("prop");
  }

  @Nonnull
  public final THISTYPE pushStack ()
  {
    return jqinvoke ("pushStack");
  }

  @Nonnull
  public final THISTYPE queue ()
  {
    return jqinvoke ("queue");
  }

  @Nonnull
  public final THISTYPE ready ()
  {
    return jqinvoke ("ready");
  }

  @Nonnull
  public final THISTYPE remove ()
  {
    return jqinvoke ("remove");
  }

  @Nonnull
  public final THISTYPE removeAttr ()
  {
    return jqinvoke ("removeAttr");
  }

  @Nonnull
  public final THISTYPE removeClass ()
  {
    return jqinvoke ("removeClass");
  }

  @Nonnull
  public final THISTYPE removeData ()
  {
    return jqinvoke ("removeData");
  }

  @Nonnull
  public final THISTYPE removeProp ()
  {
    return jqinvoke ("removeProp");
  }

  @Nonnull
  public final THISTYPE replaceAll ()
  {
    return jqinvoke ("replaceAll");
  }

  @Nonnull
  public final THISTYPE replaceWith ()
  {
    return jqinvoke ("replaceWith");
  }

  @Nonnull
  public final THISTYPE resize ()
  {
    return jqinvoke ("resize");
  }

  @Nonnull
  public final THISTYPE scroll ()
  {
    return jqinvoke ("scroll");
  }

  @Nonnull
  public final THISTYPE scrollLeft ()
  {
    return jqinvoke ("scrollLeft");
  }

  @Nonnull
  public final THISTYPE scrollTop ()
  {
    return jqinvoke ("scrollTop");
  }

  @Nonnull
  public final THISTYPE select ()
  {
    return jqinvoke ("select");
  }

  @Nonnull
  public final THISTYPE serialize ()
  {
    return jqinvoke ("serialize");
  }

  @Nonnull
  public final THISTYPE serializeArray ()
  {
    return jqinvoke ("serializeArray");
  }

  @Nonnull
  public final THISTYPE show ()
  {
    return jqinvoke ("show");
  }

  @Nonnull
  public final THISTYPE siblings ()
  {
    return jqinvoke ("siblings");
  }

  @Deprecated
  @Nonnull
  public final THISTYPE size ()
  {
    return jqinvoke ("size");
  }

  @Nonnull
  public final THISTYPE slice ()
  {
    return jqinvoke ("slice");
  }

  @Nonnull
  public final THISTYPE slideDown ()
  {
    return jqinvoke ("slideDown");
  }

  @Nonnull
  public final THISTYPE slideToggle ()
  {
    return jqinvoke ("slideToggle");
  }

  @Nonnull
  public final THISTYPE slideUp ()
  {
    return jqinvoke ("slideUp");
  }

  @Nonnull
  public final THISTYPE stop ()
  {
    return jqinvoke ("stop");
  }

  @Nonnull
  public final THISTYPE submit ()
  {
    return jqinvoke ("submit");
  }

  @Nonnull
  public final THISTYPE text ()
  {
    return jqinvoke ("text");
  }

  @Nonnull
  public final THISTYPE toArray ()
  {
    return jqinvoke ("toArray");
  }

  @Nonnull
  public final THISTYPE toggle ()
  {
    return jqinvoke ("toggle");
  }

  @Nonnull
  public final THISTYPE toggleClass ()
  {
    return jqinvoke ("toggleClass");
  }

  @Nonnull
  public final THISTYPE trigger ()
  {
    return jqinvoke ("trigger");
  }

  @Nonnull
  public final THISTYPE triggerHandler ()
  {
    return jqinvoke ("triggerHandler");
  }

  @Deprecated
  @Nonnull
  public final THISTYPE unbind ()
  {
    return jqinvoke ("unbind");
  }

  @Deprecated
  @Nonnull
  public final THISTYPE undelegate ()
  {
    return jqinvoke ("undelegate");
  }

  @Deprecated
  @Nonnull
  public final THISTYPE unload ()
  {
    return jqinvoke ("unload");
  }

  @Nonnull
  public final THISTYPE unwrap ()
  {
    return jqinvoke ("unwrap");
  }

  @Nonnull
  public final THISTYPE val ()
  {
    return jqinvoke ("val");
  }

  @Nonnull
  public final THISTYPE width ()
  {
    return jqinvoke ("width");
  }

  @Nonnull
  public final THISTYPE wrap ()
  {
    return jqinvoke ("wrap");
  }

  @Nonnull
  public final THISTYPE wrapAll ()
  {
    return jqinvoke ("wrapAll");
  }

  @Nonnull
  public final THISTYPE wrapInner ()
  {
    return jqinvoke ("wrapInner");
  }

}
