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
package com.helger.html.js.builder.jquery;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.helger.commons.annotations.Nonempty;
import com.helger.html.css.ICSSClassProvider;
import com.helger.html.js.builder.AbstractJSInvocation;
import com.helger.html.js.builder.IJSExpression;
import com.helger.html.js.builder.JSFieldRef;
import com.helger.html.js.builder.JSFunction;

/**
 * Special invocation semantics for jQuery
 * 
 * This file is generated - do NOT edit!
 * @author com.helger.html.supplementary.jquery.Main_AbstractJQueryInvocation
 * @param <IMPLTYPE> Implementation type
 */
public abstract class AbstractJQueryInvocation <IMPLTYPE extends AbstractJQueryInvocation <IMPLTYPE>> extends AbstractJSInvocation <IMPLTYPE> implements IJQueryInvocation <IMPLTYPE>
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
  public IMPLTYPE arg (@Nullable final ICSSClassProvider aArgument)
  {
    return aArgument == null ? argNull () : arg (aArgument.getCSSClass ());
  }

  @Nonnull
  public IMPLTYPE arg (@Nullable final IJQuerySelector aArgument)
  {
    return aArgument == null ? argNull () : arg (aArgument.getExpression ());
  }

  @Nonnull
  public IMPLTYPE arg (@Nullable final JQuerySelectorList aArgument)
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
  public final IMPLTYPE add ()
  {
    return jqinvoke ("add");
  }

  @Nonnull
  public final IMPLTYPE addBack ()
  {
    return jqinvoke ("addBack");
  }

  @Nonnull
  public final IMPLTYPE addClass ()
  {
    return jqinvoke ("addClass");
  }

  @Nonnull
  public final IMPLTYPE after ()
  {
    return jqinvoke ("after");
  }

  @Nonnull
  public final IMPLTYPE ajaxComplete ()
  {
    return jqinvoke ("ajaxComplete");
  }

  @Nonnull
  public final IMPLTYPE ajaxError ()
  {
    return jqinvoke ("ajaxError");
  }

  @Nonnull
  public final IMPLTYPE ajaxSend ()
  {
    return jqinvoke ("ajaxSend");
  }

  @Nonnull
  public final IMPLTYPE ajaxStart ()
  {
    return jqinvoke ("ajaxStart");
  }

  @Nonnull
  public final IMPLTYPE ajaxStop ()
  {
    return jqinvoke ("ajaxStop");
  }

  @Nonnull
  public final IMPLTYPE ajaxSuccess ()
  {
    return jqinvoke ("ajaxSuccess");
  }

  @Deprecated
  @Nonnull
  public final IMPLTYPE andSelf ()
  {
    return jqinvoke ("andSelf");
  }

  @Nonnull
  public final IMPLTYPE animate ()
  {
    return jqinvoke ("animate");
  }

  @Nonnull
  public final IMPLTYPE append ()
  {
    return jqinvoke ("append");
  }

  @Nonnull
  public final IMPLTYPE appendTo ()
  {
    return jqinvoke ("appendTo");
  }

  @Nonnull
  public final IMPLTYPE attr ()
  {
    return jqinvoke ("attr");
  }

  @Nonnull
  public final IMPLTYPE before ()
  {
    return jqinvoke ("before");
  }

  @Nonnull
  public final IMPLTYPE bind ()
  {
    return jqinvoke ("bind");
  }

  @Nonnull
  public final IMPLTYPE blur ()
  {
    return jqinvoke ("blur");
  }

  @Nonnull
  public final IMPLTYPE callbacks_add ()
  {
    return jqinvoke ("add");
  }

  @Nonnull
  public final IMPLTYPE callbacks_disable ()
  {
    return jqinvoke ("disable");
  }

  @Nonnull
  public final IMPLTYPE callbacks_disabled ()
  {
    return jqinvoke ("disabled");
  }

  @Nonnull
  public final IMPLTYPE callbacks_empty ()
  {
    return jqinvoke ("empty");
  }

  @Nonnull
  public final IMPLTYPE callbacks_fire ()
  {
    return jqinvoke ("fire");
  }

  @Nonnull
  public final IMPLTYPE callbacks_fireWith ()
  {
    return jqinvoke ("fireWith");
  }

  @Nonnull
  public final IMPLTYPE callbacks_fired ()
  {
    return jqinvoke ("fired");
  }

  @Nonnull
  public final IMPLTYPE callbacks_has ()
  {
    return jqinvoke ("has");
  }

  @Nonnull
  public final IMPLTYPE callbacks_lock ()
  {
    return jqinvoke ("lock");
  }

  @Nonnull
  public final IMPLTYPE callbacks_locked ()
  {
    return jqinvoke ("locked");
  }

  @Nonnull
  public final IMPLTYPE callbacks_remove ()
  {
    return jqinvoke ("remove");
  }

  @Nonnull
  public final IMPLTYPE change ()
  {
    return jqinvoke ("change");
  }

  @Nonnull
  public final IMPLTYPE children ()
  {
    return jqinvoke ("children");
  }

  @Nonnull
  public final IMPLTYPE clearQueue ()
  {
    return jqinvoke ("clearQueue");
  }

  @Nonnull
  public final IMPLTYPE click ()
  {
    return jqinvoke ("click");
  }

  @Nonnull
  public final IMPLTYPE _clone ()
  {
    return jqinvoke ("clone");
  }

  @Nonnull
  public final IMPLTYPE closest ()
  {
    return jqinvoke ("closest");
  }

  @Nonnull
  public final IMPLTYPE contents ()
  {
    return jqinvoke ("contents");
  }

  @Nonnull
  public final IMPLTYPE css ()
  {
    return jqinvoke ("css");
  }

  @Nonnull
  public final IMPLTYPE data ()
  {
    return jqinvoke ("data");
  }

  @Nonnull
  public final IMPLTYPE dblclick ()
  {
    return jqinvoke ("dblclick");
  }

  @Nonnull
  public final IMPLTYPE deferred_always ()
  {
    return jqinvoke ("always");
  }

  @Nonnull
  public final IMPLTYPE deferred_done ()
  {
    return jqinvoke ("done");
  }

  @Nonnull
  public final IMPLTYPE deferred_fail ()
  {
    return jqinvoke ("fail");
  }

  @Deprecated
  @Nonnull
  public final IMPLTYPE deferred_isRejected ()
  {
    return jqinvoke ("isRejected");
  }

  @Deprecated
  @Nonnull
  public final IMPLTYPE deferred_isResolved ()
  {
    return jqinvoke ("isResolved");
  }

  @Nonnull
  public final IMPLTYPE deferred_notify ()
  {
    return jqinvoke ("notify");
  }

  @Nonnull
  public final IMPLTYPE deferred_notifyWith ()
  {
    return jqinvoke ("notifyWith");
  }

  @Deprecated
  @Nonnull
  public final IMPLTYPE deferred_pipe ()
  {
    return jqinvoke ("pipe");
  }

  @Nonnull
  public final IMPLTYPE deferred_progress ()
  {
    return jqinvoke ("progress");
  }

  @Nonnull
  public final IMPLTYPE deferred_promise ()
  {
    return jqinvoke ("promise");
  }

  @Nonnull
  public final IMPLTYPE deferred_reject ()
  {
    return jqinvoke ("reject");
  }

  @Nonnull
  public final IMPLTYPE deferred_rejectWith ()
  {
    return jqinvoke ("rejectWith");
  }

  @Nonnull
  public final IMPLTYPE deferred_resolve ()
  {
    return jqinvoke ("resolve");
  }

  @Nonnull
  public final IMPLTYPE deferred_resolveWith ()
  {
    return jqinvoke ("resolveWith");
  }

  @Nonnull
  public final IMPLTYPE deferred_state ()
  {
    return jqinvoke ("state");
  }

  @Nonnull
  public final IMPLTYPE deferred_then ()
  {
    return jqinvoke ("then");
  }

  @Nonnull
  public final IMPLTYPE delay ()
  {
    return jqinvoke ("delay");
  }

  @Nonnull
  public final IMPLTYPE delegate ()
  {
    return jqinvoke ("delegate");
  }

  @Nonnull
  public final IMPLTYPE dequeue ()
  {
    return jqinvoke ("dequeue");
  }

  @Nonnull
  public final IMPLTYPE detach ()
  {
    return jqinvoke ("detach");
  }

  @Deprecated
  @Nonnull
  public final IMPLTYPE die ()
  {
    return jqinvoke ("die");
  }

  @Nonnull
  public final IMPLTYPE each ()
  {
    return jqinvoke ("each");
  }

  @Nonnull
  public final IMPLTYPE empty ()
  {
    return jqinvoke ("empty");
  }

  @Nonnull
  public final IMPLTYPE end ()
  {
    return jqinvoke ("end");
  }

  @Nonnull
  public final IMPLTYPE _eq ()
  {
    return jqinvoke ("eq");
  }

  @Deprecated
  @Nonnull
  public final IMPLTYPE error ()
  {
    return jqinvoke ("error");
  }

  @Nonnull
  public final IMPLTYPE event_isDefaultPrevented ()
  {
    return jqinvoke ("isDefaultPrevented");
  }

  @Nonnull
  public final IMPLTYPE event_isImmediatePropagationStopped ()
  {
    return jqinvoke ("isImmediatePropagationStopped");
  }

  @Nonnull
  public final IMPLTYPE event_isPropagationStopped ()
  {
    return jqinvoke ("isPropagationStopped");
  }

  @Nonnull
  public final IMPLTYPE event_preventDefault ()
  {
    return jqinvoke ("preventDefault");
  }

  @Nonnull
  public final IMPLTYPE event_stopImmediatePropagation ()
  {
    return jqinvoke ("stopImmediatePropagation");
  }

  @Nonnull
  public final IMPLTYPE event_stopPropagation ()
  {
    return jqinvoke ("stopPropagation");
  }

  @Nonnull
  public final IMPLTYPE fadeIn ()
  {
    return jqinvoke ("fadeIn");
  }

  @Nonnull
  public final IMPLTYPE fadeOut ()
  {
    return jqinvoke ("fadeOut");
  }

  @Nonnull
  public final IMPLTYPE fadeTo ()
  {
    return jqinvoke ("fadeTo");
  }

  @Nonnull
  public final IMPLTYPE fadeToggle ()
  {
    return jqinvoke ("fadeToggle");
  }

  @Nonnull
  public final IMPLTYPE filter ()
  {
    return jqinvoke ("filter");
  }

  @Nonnull
  public final IMPLTYPE find ()
  {
    return jqinvoke ("find");
  }

  @Nonnull
  public final IMPLTYPE finish ()
  {
    return jqinvoke ("finish");
  }

  @Nonnull
  public final IMPLTYPE first ()
  {
    return jqinvoke ("first");
  }

  @Nonnull
  public final IMPLTYPE focus ()
  {
    return jqinvoke ("focus");
  }

  @Nonnull
  public final IMPLTYPE focusin ()
  {
    return jqinvoke ("focusin");
  }

  @Nonnull
  public final IMPLTYPE focusout ()
  {
    return jqinvoke ("focusout");
  }

  @Nonnull
  public final IMPLTYPE get ()
  {
    return jqinvoke ("get");
  }

  @Nonnull
  public final IMPLTYPE has ()
  {
    return jqinvoke ("has");
  }

  @Nonnull
  public final IMPLTYPE hasClass ()
  {
    return jqinvoke ("hasClass");
  }

  @Nonnull
  public final IMPLTYPE height ()
  {
    return jqinvoke ("height");
  }

  @Nonnull
  public final IMPLTYPE hide ()
  {
    return jqinvoke ("hide");
  }

  @Nonnull
  public final IMPLTYPE hover ()
  {
    return jqinvoke ("hover");
  }

  @Nonnull
  public final IMPLTYPE html ()
  {
    return jqinvoke ("html");
  }

  @Nonnull
  public final IMPLTYPE index ()
  {
    return jqinvoke ("index");
  }

  @Nonnull
  public final IMPLTYPE innerHeight ()
  {
    return jqinvoke ("innerHeight");
  }

  @Nonnull
  public final IMPLTYPE innerWidth ()
  {
    return jqinvoke ("innerWidth");
  }

  @Nonnull
  public final IMPLTYPE insertAfter ()
  {
    return jqinvoke ("insertAfter");
  }

  @Nonnull
  public final IMPLTYPE insertBefore ()
  {
    return jqinvoke ("insertBefore");
  }

  @Nonnull
  public final IMPLTYPE is ()
  {
    return jqinvoke ("is");
  }

  @Nonnull
  public final IMPLTYPE keydown ()
  {
    return jqinvoke ("keydown");
  }

  @Nonnull
  public final IMPLTYPE keypress ()
  {
    return jqinvoke ("keypress");
  }

  @Nonnull
  public final IMPLTYPE keyup ()
  {
    return jqinvoke ("keyup");
  }

  @Nonnull
  public final IMPLTYPE last ()
  {
    return jqinvoke ("last");
  }

  @Deprecated
  @Nonnull
  public final IMPLTYPE live ()
  {
    return jqinvoke ("live");
  }

  @Nonnull
  public final IMPLTYPE load ()
  {
    return jqinvoke ("load");
  }

  @Nonnull
  public final IMPLTYPE map ()
  {
    return jqinvoke ("map");
  }

  @Nonnull
  public final IMPLTYPE mousedown ()
  {
    return jqinvoke ("mousedown");
  }

  @Nonnull
  public final IMPLTYPE mouseenter ()
  {
    return jqinvoke ("mouseenter");
  }

  @Nonnull
  public final IMPLTYPE mouseleave ()
  {
    return jqinvoke ("mouseleave");
  }

  @Nonnull
  public final IMPLTYPE mousemove ()
  {
    return jqinvoke ("mousemove");
  }

  @Nonnull
  public final IMPLTYPE mouseout ()
  {
    return jqinvoke ("mouseout");
  }

  @Nonnull
  public final IMPLTYPE mouseover ()
  {
    return jqinvoke ("mouseover");
  }

  @Nonnull
  public final IMPLTYPE mouseup ()
  {
    return jqinvoke ("mouseup");
  }

  @Nonnull
  public final IMPLTYPE next ()
  {
    return jqinvoke ("next");
  }

  @Nonnull
  public final IMPLTYPE nextAll ()
  {
    return jqinvoke ("nextAll");
  }

  @Nonnull
  public final IMPLTYPE nextUntil ()
  {
    return jqinvoke ("nextUntil");
  }

  @Nonnull
  public final IMPLTYPE _not ()
  {
    return jqinvoke ("not");
  }

  @Nonnull
  public final IMPLTYPE off ()
  {
    return jqinvoke ("off");
  }

  @Nonnull
  public final IMPLTYPE offset ()
  {
    return jqinvoke ("offset");
  }

  @Nonnull
  public final IMPLTYPE offsetParent ()
  {
    return jqinvoke ("offsetParent");
  }

  @Nonnull
  public final IMPLTYPE on ()
  {
    return jqinvoke ("on");
  }

  @Nonnull
  public final IMPLTYPE one ()
  {
    return jqinvoke ("one");
  }

  @Nonnull
  public final IMPLTYPE outerHeight ()
  {
    return jqinvoke ("outerHeight");
  }

  @Nonnull
  public final IMPLTYPE outerWidth ()
  {
    return jqinvoke ("outerWidth");
  }

  @Nonnull
  public final IMPLTYPE parent ()
  {
    return jqinvoke ("parent");
  }

  @Nonnull
  public final IMPLTYPE parents ()
  {
    return jqinvoke ("parents");
  }

  @Nonnull
  public final IMPLTYPE parentsUntil ()
  {
    return jqinvoke ("parentsUntil");
  }

  @Nonnull
  public final IMPLTYPE position ()
  {
    return jqinvoke ("position");
  }

  @Nonnull
  public final IMPLTYPE prepend ()
  {
    return jqinvoke ("prepend");
  }

  @Nonnull
  public final IMPLTYPE prependTo ()
  {
    return jqinvoke ("prependTo");
  }

  @Nonnull
  public final IMPLTYPE prev ()
  {
    return jqinvoke ("prev");
  }

  @Nonnull
  public final IMPLTYPE prevAll ()
  {
    return jqinvoke ("prevAll");
  }

  @Nonnull
  public final IMPLTYPE prevUntil ()
  {
    return jqinvoke ("prevUntil");
  }

  @Nonnull
  public final IMPLTYPE promise ()
  {
    return jqinvoke ("promise");
  }

  @Nonnull
  public final IMPLTYPE prop ()
  {
    return jqinvoke ("prop");
  }

  @Nonnull
  public final IMPLTYPE pushStack ()
  {
    return jqinvoke ("pushStack");
  }

  @Nonnull
  public final IMPLTYPE queue ()
  {
    return jqinvoke ("queue");
  }

  @Nonnull
  public final IMPLTYPE ready ()
  {
    return jqinvoke ("ready");
  }

  @Nonnull
  public final IMPLTYPE remove ()
  {
    return jqinvoke ("remove");
  }

  @Nonnull
  public final IMPLTYPE removeAttr ()
  {
    return jqinvoke ("removeAttr");
  }

  @Nonnull
  public final IMPLTYPE removeClass ()
  {
    return jqinvoke ("removeClass");
  }

  @Nonnull
  public final IMPLTYPE removeData ()
  {
    return jqinvoke ("removeData");
  }

  @Nonnull
  public final IMPLTYPE removeProp ()
  {
    return jqinvoke ("removeProp");
  }

  @Nonnull
  public final IMPLTYPE replaceAll ()
  {
    return jqinvoke ("replaceAll");
  }

  @Nonnull
  public final IMPLTYPE replaceWith ()
  {
    return jqinvoke ("replaceWith");
  }

  @Nonnull
  public final IMPLTYPE resize ()
  {
    return jqinvoke ("resize");
  }

  @Nonnull
  public final IMPLTYPE scroll ()
  {
    return jqinvoke ("scroll");
  }

  @Nonnull
  public final IMPLTYPE scrollLeft ()
  {
    return jqinvoke ("scrollLeft");
  }

  @Nonnull
  public final IMPLTYPE scrollTop ()
  {
    return jqinvoke ("scrollTop");
  }

  @Nonnull
  public final IMPLTYPE select ()
  {
    return jqinvoke ("select");
  }

  @Nonnull
  public final IMPLTYPE serialize ()
  {
    return jqinvoke ("serialize");
  }

  @Nonnull
  public final IMPLTYPE serializeArray ()
  {
    return jqinvoke ("serializeArray");
  }

  @Nonnull
  public final IMPLTYPE show ()
  {
    return jqinvoke ("show");
  }

  @Nonnull
  public final IMPLTYPE siblings ()
  {
    return jqinvoke ("siblings");
  }

  @Deprecated
  @Nonnull
  public final IMPLTYPE size ()
  {
    return jqinvoke ("size");
  }

  @Nonnull
  public final IMPLTYPE slice ()
  {
    return jqinvoke ("slice");
  }

  @Nonnull
  public final IMPLTYPE slideDown ()
  {
    return jqinvoke ("slideDown");
  }

  @Nonnull
  public final IMPLTYPE slideToggle ()
  {
    return jqinvoke ("slideToggle");
  }

  @Nonnull
  public final IMPLTYPE slideUp ()
  {
    return jqinvoke ("slideUp");
  }

  @Nonnull
  public final IMPLTYPE stop ()
  {
    return jqinvoke ("stop");
  }

  @Nonnull
  public final IMPLTYPE submit ()
  {
    return jqinvoke ("submit");
  }

  @Nonnull
  public final IMPLTYPE text ()
  {
    return jqinvoke ("text");
  }

  @Nonnull
  public final IMPLTYPE toArray ()
  {
    return jqinvoke ("toArray");
  }

  @Nonnull
  public final IMPLTYPE toggle ()
  {
    return jqinvoke ("toggle");
  }

  @Nonnull
  public final IMPLTYPE toggleClass ()
  {
    return jqinvoke ("toggleClass");
  }

  @Nonnull
  public final IMPLTYPE trigger ()
  {
    return jqinvoke ("trigger");
  }

  @Nonnull
  public final IMPLTYPE triggerHandler ()
  {
    return jqinvoke ("triggerHandler");
  }

  @Nonnull
  public final IMPLTYPE unbind ()
  {
    return jqinvoke ("unbind");
  }

  @Nonnull
  public final IMPLTYPE undelegate ()
  {
    return jqinvoke ("undelegate");
  }

  @Deprecated
  @Nonnull
  public final IMPLTYPE unload ()
  {
    return jqinvoke ("unload");
  }

  @Nonnull
  public final IMPLTYPE unwrap ()
  {
    return jqinvoke ("unwrap");
  }

  @Nonnull
  public final IMPLTYPE val ()
  {
    return jqinvoke ("val");
  }

  @Nonnull
  public final IMPLTYPE width ()
  {
    return jqinvoke ("width");
  }

  @Nonnull
  public final IMPLTYPE wrap ()
  {
    return jqinvoke ("wrap");
  }

  @Nonnull
  public final IMPLTYPE wrapAll ()
  {
    return jqinvoke ("wrapAll");
  }

  @Nonnull
  public final IMPLTYPE wrapInner ()
  {
    return jqinvoke ("wrapInner");
  }

}
