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
 * @param <THISTYPE>
 *        Implementation type
 */
public interface IJQueryInvocation <THISTYPE extends IJQueryInvocation <THISTYPE>> extends IJSInvocation <THISTYPE>
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
  THISTYPE jqinvoke (@Nonnull @Nonempty String sMethod);

  /**
   * Adds a CSSClassProvider selector as a string argument.
   *
   * @param aArgument
   *        value to be added as an argument
   * @return this
   */
  @Nonnull
  default THISTYPE arg (@Nullable final ICSSClassProvider aArgument)
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
  default THISTYPE arg (@Nullable final IJQuerySelector aArgument)
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
  default THISTYPE arg (@Nullable final JQuerySelectorList aArgument)
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
  default THISTYPE add ()
  {
    return jqinvoke ("add");
  }

  @Nonnull
  default THISTYPE addBack ()
  {
    return jqinvoke ("addBack");
  }

  @Nonnull
  default THISTYPE addClass ()
  {
    return jqinvoke ("addClass");
  }

  @Nonnull
  default THISTYPE after ()
  {
    return jqinvoke ("after");
  }

  @Nonnull
  default THISTYPE ajaxComplete ()
  {
    return jqinvoke ("ajaxComplete");
  }

  @Nonnull
  default THISTYPE ajaxError ()
  {
    return jqinvoke ("ajaxError");
  }

  @Nonnull
  default THISTYPE ajaxSend ()
  {
    return jqinvoke ("ajaxSend");
  }

  @Nonnull
  default THISTYPE ajaxStart ()
  {
    return jqinvoke ("ajaxStart");
  }

  @Nonnull
  default THISTYPE ajaxStop ()
  {
    return jqinvoke ("ajaxStop");
  }

  @Nonnull
  default THISTYPE ajaxSuccess ()
  {
    return jqinvoke ("ajaxSuccess");
  }

  @Deprecated
  @Nonnull
  default THISTYPE andSelf ()
  {
    return jqinvoke ("andSelf");
  }

  @Nonnull
  default THISTYPE animate ()
  {
    return jqinvoke ("animate");
  }

  @Nonnull
  default THISTYPE append ()
  {
    return jqinvoke ("append");
  }

  @Nonnull
  default THISTYPE appendTo ()
  {
    return jqinvoke ("appendTo");
  }

  @Nonnull
  default THISTYPE attr ()
  {
    return jqinvoke ("attr");
  }

  @Nonnull
  default THISTYPE before ()
  {
    return jqinvoke ("before");
  }

  @Deprecated
  @Nonnull
  default THISTYPE bind ()
  {
    return jqinvoke ("bind");
  }

  @Nonnull
  default THISTYPE blur ()
  {
    return jqinvoke ("blur");
  }

  @Nonnull
  default THISTYPE callbacks_add ()
  {
    return jqinvoke ("add");
  }

  @Nonnull
  default THISTYPE callbacks_disable ()
  {
    return jqinvoke ("disable");
  }

  @Nonnull
  default THISTYPE callbacks_disabled ()
  {
    return jqinvoke ("disabled");
  }

  @Nonnull
  default THISTYPE callbacks_empty ()
  {
    return jqinvoke ("empty");
  }

  @Nonnull
  default THISTYPE callbacks_fire ()
  {
    return jqinvoke ("fire");
  }

  @Nonnull
  default THISTYPE callbacks_fireWith ()
  {
    return jqinvoke ("fireWith");
  }

  @Nonnull
  default THISTYPE callbacks_fired ()
  {
    return jqinvoke ("fired");
  }

  @Nonnull
  default THISTYPE callbacks_has ()
  {
    return jqinvoke ("has");
  }

  @Nonnull
  default THISTYPE callbacks_lock ()
  {
    return jqinvoke ("lock");
  }

  @Nonnull
  default THISTYPE callbacks_locked ()
  {
    return jqinvoke ("locked");
  }

  @Nonnull
  default THISTYPE callbacks_remove ()
  {
    return jqinvoke ("remove");
  }

  @Nonnull
  default THISTYPE change ()
  {
    return jqinvoke ("change");
  }

  @Nonnull
  default THISTYPE children ()
  {
    return jqinvoke ("children");
  }

  @Nonnull
  default THISTYPE clearQueue ()
  {
    return jqinvoke ("clearQueue");
  }

  @Nonnull
  default THISTYPE click ()
  {
    return jqinvoke ("click");
  }

  @Nonnull
  default THISTYPE _clone ()
  {
    return jqinvoke ("clone");
  }

  @Nonnull
  default THISTYPE closest ()
  {
    return jqinvoke ("closest");
  }

  @Nonnull
  default THISTYPE contents ()
  {
    return jqinvoke ("contents");
  }

  @Nonnull
  default THISTYPE contextmenu ()
  {
    return jqinvoke ("contextmenu");
  }

  @Nonnull
  default THISTYPE css ()
  {
    return jqinvoke ("css");
  }

  @Nonnull
  default THISTYPE data ()
  {
    return jqinvoke ("data");
  }

  @Nonnull
  default THISTYPE dblclick ()
  {
    return jqinvoke ("dblclick");
  }

  @Nonnull
  default THISTYPE deferred_always ()
  {
    return jqinvoke ("always");
  }

  @Nonnull
  default THISTYPE deferred_done ()
  {
    return jqinvoke ("done");
  }

  @Nonnull
  default THISTYPE deferred_fail ()
  {
    return jqinvoke ("fail");
  }

  @Deprecated
  @Nonnull
  default THISTYPE deferred_isRejected ()
  {
    return jqinvoke ("isRejected");
  }

  @Deprecated
  @Nonnull
  default THISTYPE deferred_isResolved ()
  {
    return jqinvoke ("isResolved");
  }

  @Nonnull
  default THISTYPE deferred_notify ()
  {
    return jqinvoke ("notify");
  }

  @Nonnull
  default THISTYPE deferred_notifyWith ()
  {
    return jqinvoke ("notifyWith");
  }

  @Deprecated
  @Nonnull
  default THISTYPE deferred_pipe ()
  {
    return jqinvoke ("pipe");
  }

  @Nonnull
  default THISTYPE deferred_progress ()
  {
    return jqinvoke ("progress");
  }

  @Nonnull
  default THISTYPE deferred_promise ()
  {
    return jqinvoke ("promise");
  }

  @Nonnull
  default THISTYPE deferred_reject ()
  {
    return jqinvoke ("reject");
  }

  @Nonnull
  default THISTYPE deferred_rejectWith ()
  {
    return jqinvoke ("rejectWith");
  }

  @Nonnull
  default THISTYPE deferred_resolve ()
  {
    return jqinvoke ("resolve");
  }

  @Nonnull
  default THISTYPE deferred_resolveWith ()
  {
    return jqinvoke ("resolveWith");
  }

  @Nonnull
  default THISTYPE deferred_state ()
  {
    return jqinvoke ("state");
  }

  @Nonnull
  default THISTYPE deferred_then ()
  {
    return jqinvoke ("then");
  }

  @Nonnull
  default THISTYPE delay ()
  {
    return jqinvoke ("delay");
  }

  @Deprecated
  @Nonnull
  default THISTYPE delegate ()
  {
    return jqinvoke ("delegate");
  }

  @Nonnull
  default THISTYPE dequeue ()
  {
    return jqinvoke ("dequeue");
  }

  @Nonnull
  default THISTYPE detach ()
  {
    return jqinvoke ("detach");
  }

  @Deprecated
  @Nonnull
  default THISTYPE die ()
  {
    return jqinvoke ("die");
  }

  @Nonnull
  default THISTYPE each ()
  {
    return jqinvoke ("each");
  }

  @Nonnull
  default THISTYPE empty ()
  {
    return jqinvoke ("empty");
  }

  @Nonnull
  default THISTYPE end ()
  {
    return jqinvoke ("end");
  }

  @Nonnull
  default THISTYPE _eq ()
  {
    return jqinvoke ("eq");
  }

  @Deprecated
  @Nonnull
  default THISTYPE error ()
  {
    return jqinvoke ("error");
  }

  @Nonnull
  default THISTYPE event_isDefaultPrevented ()
  {
    return jqinvoke ("isDefaultPrevented");
  }

  @Nonnull
  default THISTYPE event_isImmediatePropagationStopped ()
  {
    return jqinvoke ("isImmediatePropagationStopped");
  }

  @Nonnull
  default THISTYPE event_isPropagationStopped ()
  {
    return jqinvoke ("isPropagationStopped");
  }

  @Nonnull
  default THISTYPE event_preventDefault ()
  {
    return jqinvoke ("preventDefault");
  }

  @Nonnull
  default THISTYPE event_stopImmediatePropagation ()
  {
    return jqinvoke ("stopImmediatePropagation");
  }

  @Nonnull
  default THISTYPE event_stopPropagation ()
  {
    return jqinvoke ("stopPropagation");
  }

  @Nonnull
  default THISTYPE fadeIn ()
  {
    return jqinvoke ("fadeIn");
  }

  @Nonnull
  default THISTYPE fadeOut ()
  {
    return jqinvoke ("fadeOut");
  }

  @Nonnull
  default THISTYPE fadeTo ()
  {
    return jqinvoke ("fadeTo");
  }

  @Nonnull
  default THISTYPE fadeToggle ()
  {
    return jqinvoke ("fadeToggle");
  }

  @Nonnull
  default THISTYPE filter ()
  {
    return jqinvoke ("filter");
  }

  @Nonnull
  default THISTYPE find ()
  {
    return jqinvoke ("find");
  }

  @Nonnull
  default THISTYPE finish ()
  {
    return jqinvoke ("finish");
  }

  @Nonnull
  default THISTYPE first ()
  {
    return jqinvoke ("first");
  }

  @Nonnull
  default THISTYPE focus ()
  {
    return jqinvoke ("focus");
  }

  @Nonnull
  default THISTYPE focusin ()
  {
    return jqinvoke ("focusin");
  }

  @Nonnull
  default THISTYPE focusout ()
  {
    return jqinvoke ("focusout");
  }

  @Nonnull
  default THISTYPE get ()
  {
    return jqinvoke ("get");
  }

  @Nonnull
  default THISTYPE has ()
  {
    return jqinvoke ("has");
  }

  @Nonnull
  default THISTYPE hasClass ()
  {
    return jqinvoke ("hasClass");
  }

  @Nonnull
  default THISTYPE height ()
  {
    return jqinvoke ("height");
  }

  @Nonnull
  default THISTYPE hide ()
  {
    return jqinvoke ("hide");
  }

  @Nonnull
  default THISTYPE hover ()
  {
    return jqinvoke ("hover");
  }

  @Nonnull
  default THISTYPE html ()
  {
    return jqinvoke ("html");
  }

  @Nonnull
  default THISTYPE index ()
  {
    return jqinvoke ("index");
  }

  @Nonnull
  default THISTYPE innerHeight ()
  {
    return jqinvoke ("innerHeight");
  }

  @Nonnull
  default THISTYPE innerWidth ()
  {
    return jqinvoke ("innerWidth");
  }

  @Nonnull
  default THISTYPE insertAfter ()
  {
    return jqinvoke ("insertAfter");
  }

  @Nonnull
  default THISTYPE insertBefore ()
  {
    return jqinvoke ("insertBefore");
  }

  @Nonnull
  default THISTYPE is ()
  {
    return jqinvoke ("is");
  }

  @Nonnull
  default THISTYPE keydown ()
  {
    return jqinvoke ("keydown");
  }

  @Nonnull
  default THISTYPE keypress ()
  {
    return jqinvoke ("keypress");
  }

  @Nonnull
  default THISTYPE keyup ()
  {
    return jqinvoke ("keyup");
  }

  @Nonnull
  default THISTYPE last ()
  {
    return jqinvoke ("last");
  }

  @Deprecated
  @Nonnull
  default THISTYPE live ()
  {
    return jqinvoke ("live");
  }

  @Nonnull
  default THISTYPE load ()
  {
    return jqinvoke ("load");
  }

  @Nonnull
  default THISTYPE map ()
  {
    return jqinvoke ("map");
  }

  @Nonnull
  default THISTYPE mousedown ()
  {
    return jqinvoke ("mousedown");
  }

  @Nonnull
  default THISTYPE mouseenter ()
  {
    return jqinvoke ("mouseenter");
  }

  @Nonnull
  default THISTYPE mouseleave ()
  {
    return jqinvoke ("mouseleave");
  }

  @Nonnull
  default THISTYPE mousemove ()
  {
    return jqinvoke ("mousemove");
  }

  @Nonnull
  default THISTYPE mouseout ()
  {
    return jqinvoke ("mouseout");
  }

  @Nonnull
  default THISTYPE mouseover ()
  {
    return jqinvoke ("mouseover");
  }

  @Nonnull
  default THISTYPE mouseup ()
  {
    return jqinvoke ("mouseup");
  }

  @Nonnull
  default THISTYPE next ()
  {
    return jqinvoke ("next");
  }

  @Nonnull
  default THISTYPE nextAll ()
  {
    return jqinvoke ("nextAll");
  }

  @Nonnull
  default THISTYPE nextUntil ()
  {
    return jqinvoke ("nextUntil");
  }

  @Nonnull
  default THISTYPE _not ()
  {
    return jqinvoke ("not");
  }

  @Nonnull
  default THISTYPE off ()
  {
    return jqinvoke ("off");
  }

  @Nonnull
  default THISTYPE offset ()
  {
    return jqinvoke ("offset");
  }

  @Nonnull
  default THISTYPE offsetParent ()
  {
    return jqinvoke ("offsetParent");
  }

  @Nonnull
  default THISTYPE on ()
  {
    return jqinvoke ("on");
  }

  @Nonnull
  default THISTYPE one ()
  {
    return jqinvoke ("one");
  }

  @Nonnull
  default THISTYPE outerHeight ()
  {
    return jqinvoke ("outerHeight");
  }

  @Nonnull
  default THISTYPE outerWidth ()
  {
    return jqinvoke ("outerWidth");
  }

  @Nonnull
  default THISTYPE parent ()
  {
    return jqinvoke ("parent");
  }

  @Nonnull
  default THISTYPE parents ()
  {
    return jqinvoke ("parents");
  }

  @Nonnull
  default THISTYPE parentsUntil ()
  {
    return jqinvoke ("parentsUntil");
  }

  @Nonnull
  default THISTYPE position ()
  {
    return jqinvoke ("position");
  }

  @Nonnull
  default THISTYPE prepend ()
  {
    return jqinvoke ("prepend");
  }

  @Nonnull
  default THISTYPE prependTo ()
  {
    return jqinvoke ("prependTo");
  }

  @Nonnull
  default THISTYPE prev ()
  {
    return jqinvoke ("prev");
  }

  @Nonnull
  default THISTYPE prevAll ()
  {
    return jqinvoke ("prevAll");
  }

  @Nonnull
  default THISTYPE prevUntil ()
  {
    return jqinvoke ("prevUntil");
  }

  @Nonnull
  default THISTYPE promise ()
  {
    return jqinvoke ("promise");
  }

  @Nonnull
  default THISTYPE prop ()
  {
    return jqinvoke ("prop");
  }

  @Nonnull
  default THISTYPE pushStack ()
  {
    return jqinvoke ("pushStack");
  }

  @Nonnull
  default THISTYPE queue ()
  {
    return jqinvoke ("queue");
  }

  @Nonnull
  default THISTYPE ready ()
  {
    return jqinvoke ("ready");
  }

  @Nonnull
  default THISTYPE remove ()
  {
    return jqinvoke ("remove");
  }

  @Nonnull
  default THISTYPE removeAttr ()
  {
    return jqinvoke ("removeAttr");
  }

  @Nonnull
  default THISTYPE removeClass ()
  {
    return jqinvoke ("removeClass");
  }

  @Nonnull
  default THISTYPE removeData ()
  {
    return jqinvoke ("removeData");
  }

  @Nonnull
  default THISTYPE removeProp ()
  {
    return jqinvoke ("removeProp");
  }

  @Nonnull
  default THISTYPE replaceAll ()
  {
    return jqinvoke ("replaceAll");
  }

  @Nonnull
  default THISTYPE replaceWith ()
  {
    return jqinvoke ("replaceWith");
  }

  @Nonnull
  default THISTYPE resize ()
  {
    return jqinvoke ("resize");
  }

  @Nonnull
  default THISTYPE scroll ()
  {
    return jqinvoke ("scroll");
  }

  @Nonnull
  default THISTYPE scrollLeft ()
  {
    return jqinvoke ("scrollLeft");
  }

  @Nonnull
  default THISTYPE scrollTop ()
  {
    return jqinvoke ("scrollTop");
  }

  @Nonnull
  default THISTYPE select ()
  {
    return jqinvoke ("select");
  }

  @Nonnull
  default THISTYPE serialize ()
  {
    return jqinvoke ("serialize");
  }

  @Nonnull
  default THISTYPE serializeArray ()
  {
    return jqinvoke ("serializeArray");
  }

  @Nonnull
  default THISTYPE show ()
  {
    return jqinvoke ("show");
  }

  @Nonnull
  default THISTYPE siblings ()
  {
    return jqinvoke ("siblings");
  }

  @Deprecated
  @Nonnull
  default THISTYPE size ()
  {
    return jqinvoke ("size");
  }

  @Nonnull
  default THISTYPE slice ()
  {
    return jqinvoke ("slice");
  }

  @Nonnull
  default THISTYPE slideDown ()
  {
    return jqinvoke ("slideDown");
  }

  @Nonnull
  default THISTYPE slideToggle ()
  {
    return jqinvoke ("slideToggle");
  }

  @Nonnull
  default THISTYPE slideUp ()
  {
    return jqinvoke ("slideUp");
  }

  @Nonnull
  default THISTYPE stop ()
  {
    return jqinvoke ("stop");
  }

  @Nonnull
  default THISTYPE submit ()
  {
    return jqinvoke ("submit");
  }

  @Nonnull
  default THISTYPE text ()
  {
    return jqinvoke ("text");
  }

  @Nonnull
  default THISTYPE toArray ()
  {
    return jqinvoke ("toArray");
  }

  @Nonnull
  default THISTYPE toggle ()
  {
    return jqinvoke ("toggle");
  }

  @Nonnull
  default THISTYPE toggleClass ()
  {
    return jqinvoke ("toggleClass");
  }

  @Nonnull
  default THISTYPE trigger ()
  {
    return jqinvoke ("trigger");
  }

  @Nonnull
  default THISTYPE triggerHandler ()
  {
    return jqinvoke ("triggerHandler");
  }

  @Deprecated
  @Nonnull
  default THISTYPE unbind ()
  {
    return jqinvoke ("unbind");
  }

  @Deprecated
  @Nonnull
  default THISTYPE undelegate ()
  {
    return jqinvoke ("undelegate");
  }

  @Deprecated
  @Nonnull
  default THISTYPE unload ()
  {
    return jqinvoke ("unload");
  }

  @Nonnull
  default THISTYPE unwrap ()
  {
    return jqinvoke ("unwrap");
  }

  @Nonnull
  default THISTYPE val ()
  {
    return jqinvoke ("val");
  }

  @Nonnull
  default THISTYPE width ()
  {
    return jqinvoke ("width");
  }

  @Nonnull
  default THISTYPE wrap ()
  {
    return jqinvoke ("wrap");
  }

  @Nonnull
  default THISTYPE wrapAll ()
  {
    return jqinvoke ("wrapAll");
  }

  @Nonnull
  default THISTYPE wrapInner ()
  {
    return jqinvoke ("wrapInner");
  }

}
