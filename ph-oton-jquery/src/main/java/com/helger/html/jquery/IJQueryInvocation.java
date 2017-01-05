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
import com.helger.html.jscode.JSFieldRef;

/**
 * This file is generated - do NOT edit!
 * @author com.helger.html.jquery.supplementary.main.Main_IJQueryInvocation
 * @param <THISTYPE> Implementation type
*/
public interface IJQueryInvocation <THISTYPE extends IJQueryInvocation <THISTYPE>>
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
  THISTYPE arg (@Nullable ICSSClassProvider aArgument);

  /**
   * Adds a JQuery selector as a string argument.
   * 
   * @param aArgument
   *        value to be added as an argument
   * @return this
   */
  @Nonnull
  THISTYPE arg (@Nullable IJQuerySelector aArgument);

  /**
   * Adds a JQuery selector list as a string argument.
   * 
   * @param aArgument
   *        value to be added as an argument
   * @return this
   */
  @Nonnull
  THISTYPE arg (@Nullable JQuerySelectorList aArgument);

  // Properties of jQuery Object Instances

  /**
   * @return The invocation of the jQuery function <code>context()</code>
   * @since jQuery 1.3
   * @deprecated Deprecated since jQuery 1.10
   */
  @Deprecated
  @Nonnull
  JSFieldRef context ();

  /**
   * @return The invocation of the jQuery field <code>jquery</code>
   */
  @Nonnull
  JSFieldRef jquery ();

  /**
   * @return The invocation of the jQuery field <code>length()</code>
   */
  @Nonnull
  JSFieldRef length ();
  /**
   * @return The invocation of the jQuery function <code>add()</code> with return type jQuery
   * @since jQuery 1
   */
  @Nonnull
  THISTYPE add ();

  /**
   * @return The invocation of the jQuery function <code>addBack()</code> with return type jQuery
   * @since jQuery 1.8
   */
  @Nonnull
  THISTYPE addBack ();

  /**
   * @return The invocation of the jQuery function <code>addClass()</code> with return type jQuery
   * @since jQuery 1
   */
  @Nonnull
  THISTYPE addClass ();

  /**
   * @return The invocation of the jQuery function <code>after()</code> with return type jQuery
   * @since jQuery 1
   */
  @Nonnull
  THISTYPE after ();

  /**
   * @return The invocation of the jQuery function <code>ajaxComplete()</code> with return type jQuery
   * @since jQuery 1
   */
  @Nonnull
  THISTYPE ajaxComplete ();

  /**
   * @return The invocation of the jQuery function <code>ajaxError()</code> with return type jQuery
   * @since jQuery 1
   */
  @Nonnull
  THISTYPE ajaxError ();

  /**
   * @return The invocation of the jQuery function <code>ajaxSend()</code> with return type jQuery
   * @since jQuery 1
   */
  @Nonnull
  THISTYPE ajaxSend ();

  /**
   * @return The invocation of the jQuery function <code>ajaxStart()</code> with return type jQuery
   * @since jQuery 1
   */
  @Nonnull
  THISTYPE ajaxStart ();

  /**
   * @return The invocation of the jQuery function <code>ajaxStop()</code> with return type jQuery
   * @since jQuery 1
   */
  @Nonnull
  THISTYPE ajaxStop ();

  /**
   * @return The invocation of the jQuery function <code>ajaxSuccess()</code> with return type jQuery
   * @since jQuery 1
   */
  @Nonnull
  THISTYPE ajaxSuccess ();

  /**
   * @return The invocation of the jQuery function <code>andSelf()</code> with return type jQuery
   * @deprecated Deprecated since jQuery 1.8
   * @since jQuery 1.2
   */
  @Deprecated
  @Nonnull
  THISTYPE andSelf ();

  /**
   * @return The invocation of the jQuery function <code>animate()</code> with return type jQuery
   * @since jQuery 1
   */
  @Nonnull
  THISTYPE animate ();

  /**
   * @return The invocation of the jQuery function <code>append()</code> with return type jQuery
   * @since jQuery 1
   */
  @Nonnull
  THISTYPE append ();

  /**
   * @return The invocation of the jQuery function <code>appendTo()</code> with return type jQuery
   * @since jQuery 1
   */
  @Nonnull
  THISTYPE appendTo ();

  /**
   * @return The invocation of the jQuery function <code>attr()</code> with return type String or jQuery
   * @since jQuery 1
   */
  @Nonnull
  THISTYPE attr ();

  /**
   * @return The invocation of the jQuery function <code>before()</code> with return type jQuery
   * @since jQuery 1
   */
  @Nonnull
  THISTYPE before ();

  /**
   * @return The invocation of the jQuery function <code>bind()</code> with return type jQuery
   * @deprecated Deprecated since jQuery 3
   * @since jQuery 1
   */
  @Deprecated
  @Nonnull
  THISTYPE bind ();

  /**
   * @return The invocation of the jQuery function <code>blur()</code> with return type jQuery
   * @since jQuery 1
   */
  @Nonnull
  THISTYPE blur ();

  /**
   * @return The invocation of the jQuery callbacks function <code>add()</code> with return type Callbacks
   * @since jQuery 1.7
   */
  @Nonnull
  THISTYPE callbacks_add ();

  /**
   * @return The invocation of the jQuery callbacks function <code>disable()</code> with return type Callbacks
   * @since jQuery 1.7
   */
  @Nonnull
  THISTYPE callbacks_disable ();

  /**
   * @return The invocation of the jQuery callbacks function <code>disabled()</code> with return type Boolean
   * @since jQuery 1.7
   */
  @Nonnull
  THISTYPE callbacks_disabled ();

  /**
   * @return The invocation of the jQuery callbacks function <code>empty()</code> with return type Callbacks
   * @since jQuery 1.7
   */
  @Nonnull
  THISTYPE callbacks_empty ();

  /**
   * @return The invocation of the jQuery callbacks function <code>fire()</code> with return type Callbacks
   * @since jQuery 1.7
   */
  @Nonnull
  THISTYPE callbacks_fire ();

  /**
   * @return The invocation of the jQuery callbacks function <code>fireWith()</code> with return type Callbacks
   * @since jQuery 1.7
   */
  @Nonnull
  THISTYPE callbacks_fireWith ();

  /**
   * @return The invocation of the jQuery callbacks function <code>fired()</code> with return type Boolean
   * @since jQuery 1.7
   */
  @Nonnull
  THISTYPE callbacks_fired ();

  /**
   * @return The invocation of the jQuery callbacks function <code>has()</code> with return type Boolean
   * @since jQuery 1.7
   */
  @Nonnull
  THISTYPE callbacks_has ();

  /**
   * @return The invocation of the jQuery callbacks function <code>lock()</code> with return type Callbacks
   * @since jQuery 1.7
   */
  @Nonnull
  THISTYPE callbacks_lock ();

  /**
   * @return The invocation of the jQuery callbacks function <code>locked()</code> with return type Boolean
   * @since jQuery 1.7
   */
  @Nonnull
  THISTYPE callbacks_locked ();

  /**
   * @return The invocation of the jQuery callbacks function <code>remove()</code> with return type Callbacks
   * @since jQuery 1.7
   */
  @Nonnull
  THISTYPE callbacks_remove ();

  /**
   * @return The invocation of the jQuery function <code>change()</code> with return type jQuery
   * @since jQuery 1
   */
  @Nonnull
  THISTYPE change ();

  /**
   * @return The invocation of the jQuery function <code>children()</code> with return type jQuery
   * @since jQuery 1
   */
  @Nonnull
  THISTYPE children ();

  /**
   * @return The invocation of the jQuery function <code>clearQueue()</code> with return type jQuery
   * @since jQuery 1.4
   */
  @Nonnull
  THISTYPE clearQueue ();

  /**
   * @return The invocation of the jQuery function <code>click()</code> with return type jQuery
   * @since jQuery 1
   */
  @Nonnull
  THISTYPE click ();

  /**
   * @return The invocation of the jQuery function <code>clone()</code> with return type jQuery
   * @since jQuery 1
   */
  @Nonnull
  THISTYPE _clone ();

  /**
  * Certain versions of this method are deprecated since jQuery 1.7
   * @return The invocation of the jQuery function <code>closest()</code> with return type jQuery or Array
   * @since jQuery 1.3
   */
  @Nonnull
  THISTYPE closest ();

  /**
   * @return The invocation of the jQuery function <code>contents()</code> with return type jQuery
   * @since jQuery 1.2
   */
  @Nonnull
  THISTYPE contents ();

  /**
   * @return The invocation of the jQuery function <code>contextmenu()</code> with return type jQuery
   * @since jQuery 1
   */
  @Nonnull
  THISTYPE contextmenu ();

  /**
   * @return The invocation of the jQuery function <code>css()</code> with return type String or jQuery
   * @since jQuery 1
   */
  @Nonnull
  THISTYPE css ();

  /**
   * @return The invocation of the jQuery function <code>data()</code> with return type jQuery or Object
   * @since jQuery 1.2.3
   */
  @Nonnull
  THISTYPE data ();

  /**
   * @return The invocation of the jQuery function <code>dblclick()</code> with return type jQuery
   * @since jQuery 1
   */
  @Nonnull
  THISTYPE dblclick ();

  /**
   * @return The invocation of the jQuery deferred function <code>always()</code> with return type Deferred
   * @since jQuery 1.6
   */
  @Nonnull
  THISTYPE deferred_always ();

  /**
   * @return The invocation of the jQuery deferred function <code>done()</code> with return type Deferred
   * @since jQuery 1.5
   */
  @Nonnull
  THISTYPE deferred_done ();

  /**
   * @return The invocation of the jQuery deferred function <code>fail()</code> with return type Deferred
   * @since jQuery 1.5
   */
  @Nonnull
  THISTYPE deferred_fail ();

  /**
   * @return The invocation of the jQuery deferred function <code>isRejected()</code> with return type Boolean
   * @deprecated Deprecated since jQuery 1.7
   * @since jQuery 1.5
   */
  @Deprecated
  @Nonnull
  THISTYPE deferred_isRejected ();

  /**
   * @return The invocation of the jQuery deferred function <code>isResolved()</code> with return type Boolean
   * @deprecated Deprecated since jQuery 1.7
   * @since jQuery 1.5
   */
  @Deprecated
  @Nonnull
  THISTYPE deferred_isResolved ();

  /**
   * @return The invocation of the jQuery deferred function <code>notify()</code> with return type Deferred
   * @since jQuery 1.7
   */
  @Nonnull
  THISTYPE deferred_notify ();

  /**
   * @return The invocation of the jQuery deferred function <code>notifyWith()</code> with return type Deferred
   * @since jQuery 1.7
   */
  @Nonnull
  THISTYPE deferred_notifyWith ();

  /**
   * @return The invocation of the jQuery deferred function <code>pipe()</code> with return type Promise
   * @deprecated Deprecated since jQuery 1.8
   * @since jQuery 1.6
   */
  @Deprecated
  @Nonnull
  THISTYPE deferred_pipe ();

  /**
   * @return The invocation of the jQuery deferred function <code>progress()</code> with return type Deferred
   * @since jQuery 1.7
   */
  @Nonnull
  THISTYPE deferred_progress ();

  /**
   * @return The invocation of the jQuery deferred function <code>promise()</code> with return type Promise
   * @since jQuery 1.5
   */
  @Nonnull
  THISTYPE deferred_promise ();

  /**
   * @return The invocation of the jQuery deferred function <code>reject()</code> with return type Deferred
   * @since jQuery 1.5
   */
  @Nonnull
  THISTYPE deferred_reject ();

  /**
   * @return The invocation of the jQuery deferred function <code>rejectWith()</code> with return type Deferred
   * @since jQuery 1.5
   */
  @Nonnull
  THISTYPE deferred_rejectWith ();

  /**
   * @return The invocation of the jQuery deferred function <code>resolve()</code> with return type Deferred
   * @since jQuery 1.5
   */
  @Nonnull
  THISTYPE deferred_resolve ();

  /**
   * @return The invocation of the jQuery deferred function <code>resolveWith()</code> with return type Deferred
   * @since jQuery 1.5
   */
  @Nonnull
  THISTYPE deferred_resolveWith ();

  /**
   * @return The invocation of the jQuery deferred function <code>state()</code> with return type String
   * @since jQuery 1.7
   */
  @Nonnull
  THISTYPE deferred_state ();

  /**
   * @return The invocation of the jQuery deferred function <code>then()</code> with return type Promise
   * @since jQuery 1.5
   */
  @Nonnull
  THISTYPE deferred_then ();

  /**
   * @return The invocation of the jQuery function <code>delay()</code> with return type jQuery
   * @since jQuery 1.4
   */
  @Nonnull
  THISTYPE delay ();

  /**
   * @return The invocation of the jQuery function <code>delegate()</code> with return type jQuery
   * @deprecated Deprecated since jQuery 3
   * @since jQuery 1.4.2
   */
  @Deprecated
  @Nonnull
  THISTYPE delegate ();

  /**
   * @return The invocation of the jQuery function <code>dequeue()</code> with return type jQuery
   * @since jQuery 1.2
   */
  @Nonnull
  THISTYPE dequeue ();

  /**
   * @return The invocation of the jQuery function <code>detach()</code> with return type jQuery
   * @since jQuery 1.4
   */
  @Nonnull
  THISTYPE detach ();

  /**
   * @return The invocation of the jQuery function <code>die()</code> with return type jQuery
   * @deprecated Deprecated since jQuery 1.7
   * @since jQuery 1.3
   */
  @Deprecated
  @Nonnull
  THISTYPE die ();

  /**
   * @return The invocation of the jQuery function <code>each()</code> with return type jQuery
   * @since jQuery 1
   */
  @Nonnull
  THISTYPE each ();

  /**
   * @return The invocation of the jQuery function <code>empty()</code> with return type jQuery
   * @since jQuery 1
   */
  @Nonnull
  THISTYPE empty ();

  /**
   * @return The invocation of the jQuery function <code>end()</code> with return type jQuery
   * @since jQuery 1
   */
  @Nonnull
  THISTYPE end ();

  /**
   * @return The invocation of the jQuery function <code>eq()</code> with return type jQuery
   * @since jQuery 1.1.2
   */
  @Nonnull
  THISTYPE _eq ();

  /**
   * @return The invocation of the jQuery function <code>error()</code> with return type jQuery
   * @deprecated Deprecated since jQuery 1.8
   * @since jQuery 1
   */
  @Deprecated
  @Nonnull
  THISTYPE error ();

  /**
   * @return The invocation of the jQuery event function <code>isDefaultPrevented()</code> with return type Boolean
   * @since jQuery 1.3
   */
  @Nonnull
  THISTYPE event_isDefaultPrevented ();

  /**
   * @return The invocation of the jQuery event function <code>isImmediatePropagationStopped()</code> with return type Boolean
   * @since jQuery 1.3
   */
  @Nonnull
  THISTYPE event_isImmediatePropagationStopped ();

  /**
   * @return The invocation of the jQuery event function <code>isPropagationStopped()</code> with return type Boolean
   * @since jQuery 1.3
   */
  @Nonnull
  THISTYPE event_isPropagationStopped ();

  /**
   * @return The invocation of the jQuery event function <code>preventDefault()</code> with return type undefined
   * @since jQuery 1
   */
  @Nonnull
  THISTYPE event_preventDefault ();

  /**
   * @return The invocation of the jQuery event function <code>stopImmediatePropagation()</code> with return type void
   * @since jQuery 1.3
   */
  @Nonnull
  THISTYPE event_stopImmediatePropagation ();

  /**
   * @return The invocation of the jQuery event function <code>stopPropagation()</code> with return type void
   * @since jQuery 1
   */
  @Nonnull
  THISTYPE event_stopPropagation ();

  /**
   * @return The invocation of the jQuery function <code>fadeIn()</code> with return type jQuery
   * @since jQuery 1
   */
  @Nonnull
  THISTYPE fadeIn ();

  /**
   * @return The invocation of the jQuery function <code>fadeOut()</code> with return type jQuery
   * @since jQuery 1
   */
  @Nonnull
  THISTYPE fadeOut ();

  /**
   * @return The invocation of the jQuery function <code>fadeTo()</code> with return type jQuery
   * @since jQuery 1
   */
  @Nonnull
  THISTYPE fadeTo ();

  /**
   * @return The invocation of the jQuery function <code>fadeToggle()</code> with return type jQuery
   * @since jQuery 1.4.4
   */
  @Nonnull
  THISTYPE fadeToggle ();

  /**
   * @return The invocation of the jQuery function <code>filter()</code> with return type jQuery
   * @since jQuery 1
   */
  @Nonnull
  THISTYPE filter ();

  /**
   * @return The invocation of the jQuery function <code>find()</code> with return type jQuery
   * @since jQuery 1
   */
  @Nonnull
  THISTYPE find ();

  /**
   * @return The invocation of the jQuery function <code>finish()</code> with return type jQuery
   * @since jQuery 1.9
   */
  @Nonnull
  THISTYPE finish ();

  /**
   * @return The invocation of the jQuery function <code>first()</code> with return type jQuery
   * @since jQuery 1.4
   */
  @Nonnull
  THISTYPE first ();

  /**
   * @return The invocation of the jQuery function <code>focus()</code> with return type jQuery
   * @since jQuery 1
   */
  @Nonnull
  THISTYPE focus ();

  /**
   * @return The invocation of the jQuery function <code>focusin()</code> with return type jQuery
   * @since jQuery 1
   */
  @Nonnull
  THISTYPE focusin ();

  /**
   * @return The invocation of the jQuery function <code>focusout()</code> with return type jQuery
   * @since jQuery 1
   */
  @Nonnull
  THISTYPE focusout ();

  /**
   * @return The invocation of the jQuery function <code>get()</code> with return type Element or Array
   * @since jQuery 1
   */
  @Nonnull
  THISTYPE get ();

  /**
   * @return The invocation of the jQuery function <code>has()</code> with return type jQuery
   * @since jQuery 1.4
   */
  @Nonnull
  THISTYPE has ();

  /**
   * @return The invocation of the jQuery function <code>hasClass()</code> with return type Boolean
   * @since jQuery 1.2
   */
  @Nonnull
  THISTYPE hasClass ();

  /**
   * @return The invocation of the jQuery function <code>height()</code> with return type Number or jQuery
   * @since jQuery 1
   */
  @Nonnull
  THISTYPE height ();

  /**
   * @return The invocation of the jQuery function <code>hide()</code> with return type jQuery
   * @since jQuery 1
   */
  @Nonnull
  THISTYPE hide ();

  /**
   * @return The invocation of the jQuery function <code>hover()</code> with return type jQuery
   * @since jQuery 1
   */
  @Nonnull
  THISTYPE hover ();

  /**
   * @return The invocation of the jQuery function <code>html()</code> with return type String or jQuery
   * @since jQuery 1
   */
  @Nonnull
  THISTYPE html ();

  /**
   * @return The invocation of the jQuery function <code>index()</code> with return type Integer
   * @since jQuery 1
   */
  @Nonnull
  THISTYPE index ();

  /**
   * @return The invocation of the jQuery function <code>innerHeight()</code> with return type Number or jQuery
   * @since jQuery 1.2.6
   */
  @Nonnull
  THISTYPE innerHeight ();

  /**
   * @return The invocation of the jQuery function <code>innerWidth()</code> with return type Number or jQuery
   * @since jQuery 1.2.6
   */
  @Nonnull
  THISTYPE innerWidth ();

  /**
   * @return The invocation of the jQuery function <code>insertAfter()</code> with return type jQuery
   * @since jQuery 1
   */
  @Nonnull
  THISTYPE insertAfter ();

  /**
   * @return The invocation of the jQuery function <code>insertBefore()</code> with return type jQuery
   * @since jQuery 1
   */
  @Nonnull
  THISTYPE insertBefore ();

  /**
   * @return The invocation of the jQuery function <code>is()</code> with return type Boolean
   * @since jQuery 1
   */
  @Nonnull
  THISTYPE is ();

  /**
   * @return The invocation of the jQuery function <code>keydown()</code> with return type jQuery
   * @since jQuery 1
   */
  @Nonnull
  THISTYPE keydown ();

  /**
   * @return The invocation of the jQuery function <code>keypress()</code> with return type jQuery
   * @since jQuery 1
   */
  @Nonnull
  THISTYPE keypress ();

  /**
   * @return The invocation of the jQuery function <code>keyup()</code> with return type jQuery
   * @since jQuery 1
   */
  @Nonnull
  THISTYPE keyup ();

  /**
   * @return The invocation of the jQuery function <code>last()</code> with return type jQuery
   * @since jQuery 1.4
   */
  @Nonnull
  THISTYPE last ();

  /**
   * @return The invocation of the jQuery function <code>live()</code> with return type jQuery
   * @deprecated Deprecated since jQuery 1.7
   * @since jQuery 1.3
   */
  @Deprecated
  @Nonnull
  THISTYPE live ();

  /**
  * Certain versions of this method are deprecated since jQuery 1.8
   * @return The invocation of the jQuery function <code>load()</code> with return type jQuery
   * @since jQuery 1
   */
  @Nonnull
  THISTYPE load ();

  /**
   * @return The invocation of the jQuery function <code>map()</code> with return type jQuery
   * @since jQuery 1.2
   */
  @Nonnull
  THISTYPE map ();

  /**
   * @return The invocation of the jQuery function <code>mousedown()</code> with return type jQuery
   * @since jQuery 1
   */
  @Nonnull
  THISTYPE mousedown ();

  /**
   * @return The invocation of the jQuery function <code>mouseenter()</code> with return type jQuery
   * @since jQuery 1
   */
  @Nonnull
  THISTYPE mouseenter ();

  /**
   * @return The invocation of the jQuery function <code>mouseleave()</code> with return type jQuery
   * @since jQuery 1
   */
  @Nonnull
  THISTYPE mouseleave ();

  /**
   * @return The invocation of the jQuery function <code>mousemove()</code> with return type jQuery
   * @since jQuery 1
   */
  @Nonnull
  THISTYPE mousemove ();

  /**
   * @return The invocation of the jQuery function <code>mouseout()</code> with return type jQuery
   * @since jQuery 1
   */
  @Nonnull
  THISTYPE mouseout ();

  /**
   * @return The invocation of the jQuery function <code>mouseover()</code> with return type jQuery
   * @since jQuery 1
   */
  @Nonnull
  THISTYPE mouseover ();

  /**
   * @return The invocation of the jQuery function <code>mouseup()</code> with return type jQuery
   * @since jQuery 1
   */
  @Nonnull
  THISTYPE mouseup ();

  /**
   * @return The invocation of the jQuery function <code>next()</code> with return type jQuery
   * @since jQuery 1
   */
  @Nonnull
  THISTYPE next ();

  /**
   * @return The invocation of the jQuery function <code>nextAll()</code> with return type jQuery
   * @since jQuery 1.2
   */
  @Nonnull
  THISTYPE nextAll ();

  /**
   * @return The invocation of the jQuery function <code>nextUntil()</code> with return type jQuery
   * @since jQuery 1.4
   */
  @Nonnull
  THISTYPE nextUntil ();

  /**
   * @return The invocation of the jQuery function <code>not()</code> with return type jQuery
   * @since jQuery 1
   */
  @Nonnull
  THISTYPE _not ();

  /**
   * @return The invocation of the jQuery function <code>off()</code> with return type jQuery
   * @since jQuery 1.7
   */
  @Nonnull
  THISTYPE off ();

  /**
   * @return The invocation of the jQuery function <code>offset()</code> with return type Object or jQuery
   * @since jQuery 1.2
   */
  @Nonnull
  THISTYPE offset ();

  /**
   * @return The invocation of the jQuery function <code>offsetParent()</code> with return type jQuery
   * @since jQuery 1.2.6
   */
  @Nonnull
  THISTYPE offsetParent ();

  /**
   * @return The invocation of the jQuery function <code>on()</code> with return type jQuery
   * @since jQuery 1.7
   */
  @Nonnull
  THISTYPE on ();

  /**
   * @return The invocation of the jQuery function <code>one()</code> with return type jQuery
   * @since jQuery 1.1
   */
  @Nonnull
  THISTYPE one ();

  /**
   * @return The invocation of the jQuery function <code>outerHeight()</code> with return type Number or jQuery
   * @since jQuery 1.2.6
   */
  @Nonnull
  THISTYPE outerHeight ();

  /**
   * @return The invocation of the jQuery function <code>outerWidth()</code> with return type Number or jQuery
   * @since jQuery 1.2.6
   */
  @Nonnull
  THISTYPE outerWidth ();

  /**
   * @return The invocation of the jQuery function <code>parent()</code> with return type jQuery
   * @since jQuery 1
   */
  @Nonnull
  THISTYPE parent ();

  /**
   * @return The invocation of the jQuery function <code>parents()</code> with return type jQuery
   * @since jQuery 1
   */
  @Nonnull
  THISTYPE parents ();

  /**
   * @return The invocation of the jQuery function <code>parentsUntil()</code> with return type jQuery
   * @since jQuery 1.4
   */
  @Nonnull
  THISTYPE parentsUntil ();

  /**
   * @return The invocation of the jQuery function <code>position()</code> with return type Object
   * @since jQuery 1.2
   */
  @Nonnull
  THISTYPE position ();

  /**
   * @return The invocation of the jQuery function <code>prepend()</code> with return type jQuery
   * @since jQuery 1
   */
  @Nonnull
  THISTYPE prepend ();

  /**
   * @return The invocation of the jQuery function <code>prependTo()</code> with return type jQuery
   * @since jQuery 1
   */
  @Nonnull
  THISTYPE prependTo ();

  /**
   * @return The invocation of the jQuery function <code>prev()</code> with return type jQuery
   * @since jQuery 1
   */
  @Nonnull
  THISTYPE prev ();

  /**
   * @return The invocation of the jQuery function <code>prevAll()</code> with return type jQuery
   * @since jQuery 1.2
   */
  @Nonnull
  THISTYPE prevAll ();

  /**
   * @return The invocation of the jQuery function <code>prevUntil()</code> with return type jQuery
   * @since jQuery 1.4
   */
  @Nonnull
  THISTYPE prevUntil ();

  /**
   * @return The invocation of the jQuery function <code>promise()</code> with return type Promise
   * @since jQuery 1.6
   */
  @Nonnull
  THISTYPE promise ();

  /**
   * @return The invocation of the jQuery function <code>prop()</code> with return type void or jQuery
   * @since jQuery 1.6
   */
  @Nonnull
  THISTYPE prop ();

  /**
   * @return The invocation of the jQuery function <code>pushStack()</code> with return type jQuery
   * @since jQuery 1
   */
  @Nonnull
  THISTYPE pushStack ();

  /**
   * @return The invocation of the jQuery function <code>queue()</code> with return type Array or jQuery
   * @since jQuery 1.2
   */
  @Nonnull
  THISTYPE queue ();

  /**
   * @return The invocation of the jQuery function <code>ready()</code> with return type jQuery
   * @since jQuery 1
   */
  @Nonnull
  THISTYPE ready ();

  /**
   * @return The invocation of the jQuery function <code>remove()</code> with return type jQuery
   * @since jQuery 1
   */
  @Nonnull
  THISTYPE remove ();

  /**
   * @return The invocation of the jQuery function <code>removeAttr()</code> with return type jQuery
   * @since jQuery 1
   */
  @Nonnull
  THISTYPE removeAttr ();

  /**
   * @return The invocation of the jQuery function <code>removeClass()</code> with return type jQuery
   * @since jQuery 1
   */
  @Nonnull
  THISTYPE removeClass ();

  /**
   * @return The invocation of the jQuery function <code>removeData()</code> with return type jQuery
   * @since jQuery 1.2.3
   */
  @Nonnull
  THISTYPE removeData ();

  /**
   * @return The invocation of the jQuery function <code>removeProp()</code> with return type jQuery
   * @since jQuery 1.6
   */
  @Nonnull
  THISTYPE removeProp ();

  /**
   * @return The invocation of the jQuery function <code>replaceAll()</code> with return type jQuery
   * @since jQuery 1.2
   */
  @Nonnull
  THISTYPE replaceAll ();

  /**
   * @return The invocation of the jQuery function <code>replaceWith()</code> with return type jQuery
   * @since jQuery 1.2
   */
  @Nonnull
  THISTYPE replaceWith ();

  /**
   * @return The invocation of the jQuery function <code>resize()</code> with return type jQuery
   * @since jQuery 1
   */
  @Nonnull
  THISTYPE resize ();

  /**
   * @return The invocation of the jQuery function <code>scroll()</code> with return type jQuery
   * @since jQuery 1
   */
  @Nonnull
  THISTYPE scroll ();

  /**
   * @return The invocation of the jQuery function <code>scrollLeft()</code> with return type Integer or jQuery
   * @since jQuery 1.2.6
   */
  @Nonnull
  THISTYPE scrollLeft ();

  /**
   * @return The invocation of the jQuery function <code>scrollTop()</code> with return type Number or jQuery
   * @since jQuery 1.2.6
   */
  @Nonnull
  THISTYPE scrollTop ();

  /**
   * @return The invocation of the jQuery function <code>select()</code> with return type jQuery
   * @since jQuery 1
   */
  @Nonnull
  THISTYPE select ();

  /**
   * @return The invocation of the jQuery function <code>serialize()</code> with return type String
   * @since jQuery 1
   */
  @Nonnull
  THISTYPE serialize ();

  /**
   * @return The invocation of the jQuery function <code>serializeArray()</code> with return type Array
   * @since jQuery 1.2
   */
  @Nonnull
  THISTYPE serializeArray ();

  /**
   * @return The invocation of the jQuery function <code>show()</code> with return type jQuery
   * @since jQuery 1
   */
  @Nonnull
  THISTYPE show ();

  /**
   * @return The invocation of the jQuery function <code>siblings()</code> with return type jQuery
   * @since jQuery 1
   */
  @Nonnull
  THISTYPE siblings ();

  /**
   * @return The invocation of the jQuery function <code>size()</code> with return type Integer
   * @deprecated Deprecated since jQuery 1.8
   * @since jQuery 1
   */
  @Deprecated
  @Nonnull
  THISTYPE size ();

  /**
   * @return The invocation of the jQuery function <code>slice()</code> with return type jQuery
   * @since jQuery 1.1.4
   */
  @Nonnull
  THISTYPE slice ();

  /**
   * @return The invocation of the jQuery function <code>slideDown()</code> with return type jQuery
   * @since jQuery 1
   */
  @Nonnull
  THISTYPE slideDown ();

  /**
   * @return The invocation of the jQuery function <code>slideToggle()</code> with return type jQuery
   * @since jQuery 1
   */
  @Nonnull
  THISTYPE slideToggle ();

  /**
   * @return The invocation of the jQuery function <code>slideUp()</code> with return type jQuery
   * @since jQuery 1
   */
  @Nonnull
  THISTYPE slideUp ();

  /**
   * @return The invocation of the jQuery function <code>stop()</code> with return type jQuery
   * @since jQuery 1.2
   */
  @Nonnull
  THISTYPE stop ();

  /**
   * @return The invocation of the jQuery function <code>submit()</code> with return type jQuery
   * @since jQuery 1
   */
  @Nonnull
  THISTYPE submit ();

  /**
   * @return The invocation of the jQuery function <code>text()</code> with return type String or jQuery
   * @since jQuery 1
   */
  @Nonnull
  THISTYPE text ();

  /**
   * @return The invocation of the jQuery function <code>toArray()</code> with return type Array
   * @since jQuery 1.4
   */
  @Nonnull
  THISTYPE toArray ();

  /**
  * Certain versions of this method are deprecated since jQuery 1.8
   * @return The invocation of the jQuery function <code>toggle()</code> with return type jQuery
   * @since jQuery 1
   */
  @Nonnull
  THISTYPE toggle ();

  /**
  * Certain versions of this method are deprecated since jQuery 3
   * @return The invocation of the jQuery function <code>toggleClass()</code> with return type jQuery
   * @since jQuery 1
   */
  @Nonnull
  THISTYPE toggleClass ();

  /**
   * @return The invocation of the jQuery function <code>trigger()</code> with return type jQuery
   * @since jQuery 1
   */
  @Nonnull
  THISTYPE trigger ();

  /**
   * @return The invocation of the jQuery function <code>triggerHandler()</code> with return type Object
   * @since jQuery 1.2
   */
  @Nonnull
  THISTYPE triggerHandler ();

  /**
   * @return The invocation of the jQuery function <code>unbind()</code> with return type jQuery
   * @deprecated Deprecated since jQuery 3
   * @since jQuery 1
   */
  @Deprecated
  @Nonnull
  THISTYPE unbind ();

  /**
   * @return The invocation of the jQuery function <code>undelegate()</code> with return type jQuery
   * @deprecated Deprecated since jQuery 3
   * @since jQuery 1.4.2
   */
  @Deprecated
  @Nonnull
  THISTYPE undelegate ();

  /**
   * @return The invocation of the jQuery function <code>unload()</code> with return type jQuery
   * @deprecated Deprecated since jQuery 1.8
   * @since jQuery 1
   */
  @Deprecated
  @Nonnull
  THISTYPE unload ();

  /**
   * @return The invocation of the jQuery function <code>unwrap()</code> with return type jQuery
   * @since jQuery 1.4
   */
  @Nonnull
  THISTYPE unwrap ();

  /**
   * @return The invocation of the jQuery function <code>val()</code> with return type void or jQuery
   * @since jQuery 1
   */
  @Nonnull
  THISTYPE val ();

  /**
   * @return The invocation of the jQuery function <code>width()</code> with return type Number or jQuery
   * @since jQuery 1
   */
  @Nonnull
  THISTYPE width ();

  /**
   * @return The invocation of the jQuery function <code>wrap()</code> with return type jQuery
   * @since jQuery 1
   */
  @Nonnull
  THISTYPE wrap ();

  /**
   * @return The invocation of the jQuery function <code>wrapAll()</code> with return type jQuery
   * @since jQuery 1.2
   */
  @Nonnull
  THISTYPE wrapAll ();

  /**
   * @return The invocation of the jQuery function <code>wrapInner()</code> with return type jQuery
   * @since jQuery 1.2
   */
  @Nonnull
  THISTYPE wrapInner ();

}
