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
import com.helger.html.js.builder.JSFieldRef;

/**
 * This file is generated - do NOT edit!
 * @author com.helger.html.supplementary.jquery.Main_IJQueryInvocation
 * @param <IMPLTYPE> Implementation type
*/
public interface IJQueryInvocation <IMPLTYPE extends IJQueryInvocation <IMPLTYPE>>
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
  IMPLTYPE arg (@Nullable ICSSClassProvider aArgument);

  /**
   * Adds a JQuery selector as a string argument.
   * 
   * @param aArgument
   *        value to be added as an argument
   * @return this
   */
  @Nonnull
  IMPLTYPE arg (@Nullable IJQuerySelector aArgument);

  /**
   * Adds a JQuery selector list as a string argument.
   * 
   * @param aArgument
   *        value to be added as an argument
   * @return this
   */
  @Nonnull
  IMPLTYPE arg (@Nullable JQuerySelectorList aArgument);

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
   */
  @Nonnull
  IMPLTYPE add ();

  /**
   * @return The invocation of the jQuery function <code>addBack()</code> with return type jQuery
   */
  @Nonnull
  IMPLTYPE addBack ();

  /**
   * @return The invocation of the jQuery function <code>addClass()</code> with return type jQuery
   */
  @Nonnull
  IMPLTYPE addClass ();

  /**
   * @return The invocation of the jQuery function <code>after()</code> with return type jQuery
   */
  @Nonnull
  IMPLTYPE after ();

  /**
   * @return The invocation of the jQuery function <code>ajaxComplete()</code> with return type jQuery
   */
  @Nonnull
  IMPLTYPE ajaxComplete ();

  /**
   * @return The invocation of the jQuery function <code>ajaxError()</code> with return type jQuery
   */
  @Nonnull
  IMPLTYPE ajaxError ();

  /**
   * @return The invocation of the jQuery function <code>ajaxSend()</code> with return type jQuery
   */
  @Nonnull
  IMPLTYPE ajaxSend ();

  /**
   * @return The invocation of the jQuery function <code>ajaxStart()</code> with return type jQuery
   */
  @Nonnull
  IMPLTYPE ajaxStart ();

  /**
   * @return The invocation of the jQuery function <code>ajaxStop()</code> with return type jQuery
   */
  @Nonnull
  IMPLTYPE ajaxStop ();

  /**
   * @return The invocation of the jQuery function <code>ajaxSuccess()</code> with return type jQuery
   */
  @Nonnull
  IMPLTYPE ajaxSuccess ();

  /**
   * @return The invocation of the jQuery function <code>andSelf()</code> with return type jQuery
   * @deprecated Deprecated since jQuery 1.8
   * @since jQuery 1.2
   */
  @Deprecated
  @Nonnull
  IMPLTYPE andSelf ();

  /**
   * @return The invocation of the jQuery function <code>animate()</code> with return type jQuery
   */
  @Nonnull
  IMPLTYPE animate ();

  /**
   * @return The invocation of the jQuery function <code>append()</code> with return type jQuery
   */
  @Nonnull
  IMPLTYPE append ();

  /**
   * @return The invocation of the jQuery function <code>appendTo()</code> with return type jQuery
   */
  @Nonnull
  IMPLTYPE appendTo ();

  /**
   * @return The invocation of the jQuery function <code>attr()</code> with return type String or jQuery
   */
  @Nonnull
  IMPLTYPE attr ();

  /**
   * @return The invocation of the jQuery function <code>before()</code> with return type jQuery
   */
  @Nonnull
  IMPLTYPE before ();

  /**
   * @return The invocation of the jQuery function <code>bind()</code> with return type jQuery
   */
  @Nonnull
  IMPLTYPE bind ();

  /**
   * @return The invocation of the jQuery function <code>blur()</code> with return type jQuery
   */
  @Nonnull
  IMPLTYPE blur ();

  /**
   * @return The invocation of the jQuery callbacks function <code>add()</code> with return type Callbacks
   * @since jQuery 1.7
   */
  @Nonnull
  IMPLTYPE callbacks_add ();

  /**
   * @return The invocation of the jQuery callbacks function <code>disable()</code> with return type Callbacks
   * @since jQuery 1.7
   */
  @Nonnull
  IMPLTYPE callbacks_disable ();

  /**
   * @return The invocation of the jQuery callbacks function <code>disabled()</code> with return type Boolean
   * @since jQuery 1.7
   */
  @Nonnull
  IMPLTYPE callbacks_disabled ();

  /**
   * @return The invocation of the jQuery callbacks function <code>empty()</code> with return type Callbacks
   * @since jQuery 1.7
   */
  @Nonnull
  IMPLTYPE callbacks_empty ();

  /**
   * @return The invocation of the jQuery callbacks function <code>fire()</code> with return type Callbacks
   * @since jQuery 1.7
   */
  @Nonnull
  IMPLTYPE callbacks_fire ();

  /**
   * @return The invocation of the jQuery callbacks function <code>fireWith()</code> with return type Callbacks
   */
  @Nonnull
  IMPLTYPE callbacks_fireWith ();

  /**
   * @return The invocation of the jQuery callbacks function <code>fired()</code> with return type Boolean
   * @since jQuery 1.7
   */
  @Nonnull
  IMPLTYPE callbacks_fired ();

  /**
   * @return The invocation of the jQuery callbacks function <code>has()</code> with return type Boolean
   */
  @Nonnull
  IMPLTYPE callbacks_has ();

  /**
   * @return The invocation of the jQuery callbacks function <code>lock()</code> with return type Callbacks
   * @since jQuery 1.7
   */
  @Nonnull
  IMPLTYPE callbacks_lock ();

  /**
   * @return The invocation of the jQuery callbacks function <code>locked()</code> with return type Boolean
   * @since jQuery 1.7
   */
  @Nonnull
  IMPLTYPE callbacks_locked ();

  /**
   * @return The invocation of the jQuery callbacks function <code>remove()</code> with return type Callbacks
   * @since jQuery 1.7
   */
  @Nonnull
  IMPLTYPE callbacks_remove ();

  /**
   * @return The invocation of the jQuery function <code>change()</code> with return type jQuery
   */
  @Nonnull
  IMPLTYPE change ();

  /**
   * @return The invocation of the jQuery function <code>children()</code> with return type jQuery
   */
  @Nonnull
  IMPLTYPE children ();

  /**
   * @return The invocation of the jQuery function <code>clearQueue()</code> with return type jQuery
   */
  @Nonnull
  IMPLTYPE clearQueue ();

  /**
   * @return The invocation of the jQuery function <code>click()</code> with return type jQuery
   */
  @Nonnull
  IMPLTYPE click ();

  /**
   * @return The invocation of the jQuery function <code>clone()</code> with return type jQuery
   */
  @Nonnull
  IMPLTYPE _clone ();

  /**
  * Certain versions of this method are deprecated since jQuery 1.7
   * @return The invocation of the jQuery function <code>closest()</code> with return type jQuery or Array
   */
  @Nonnull
  IMPLTYPE closest ();

  /**
   * @return The invocation of the jQuery function <code>contents()</code> with return type jQuery
   * @since jQuery 1.2
   */
  @Nonnull
  IMPLTYPE contents ();

  /**
   * @return The invocation of the jQuery function <code>css()</code> with return type String or jQuery
   */
  @Nonnull
  IMPLTYPE css ();

  /**
   * @return The invocation of the jQuery function <code>data()</code> with return type jQuery or Object
   */
  @Nonnull
  IMPLTYPE data ();

  /**
   * @return The invocation of the jQuery function <code>dblclick()</code> with return type jQuery
   */
  @Nonnull
  IMPLTYPE dblclick ();

  /**
   * @return The invocation of the jQuery deferred function <code>always()</code> with return type Deferred
   */
  @Nonnull
  IMPLTYPE deferred_always ();

  /**
   * @return The invocation of the jQuery deferred function <code>done()</code> with return type Deferred
   */
  @Nonnull
  IMPLTYPE deferred_done ();

  /**
   * @return The invocation of the jQuery deferred function <code>fail()</code> with return type Deferred
   */
  @Nonnull
  IMPLTYPE deferred_fail ();

  /**
   * @return The invocation of the jQuery deferred function <code>isRejected()</code> with return type Boolean
   * @deprecated Deprecated since jQuery 1.7
   * @since jQuery 1.5
   */
  @Deprecated
  @Nonnull
  IMPLTYPE deferred_isRejected ();

  /**
   * @return The invocation of the jQuery deferred function <code>isResolved()</code> with return type Boolean
   * @deprecated Deprecated since jQuery 1.7
   * @since jQuery 1.5
   */
  @Deprecated
  @Nonnull
  IMPLTYPE deferred_isResolved ();

  /**
   * @return The invocation of the jQuery deferred function <code>notify()</code> with return type Deferred
   * @since jQuery 1.7
   */
  @Nonnull
  IMPLTYPE deferred_notify ();

  /**
   * @return The invocation of the jQuery deferred function <code>notifyWith()</code> with return type Deferred
   */
  @Nonnull
  IMPLTYPE deferred_notifyWith ();

  /**
   * @return The invocation of the jQuery deferred function <code>pipe()</code> with return type Promise
   * @deprecated Deprecated since jQuery 1.8
   */
  @Deprecated
  @Nonnull
  IMPLTYPE deferred_pipe ();

  /**
   * @return The invocation of the jQuery deferred function <code>progress()</code> with return type Deferred
   * @since jQuery 1.7
   */
  @Nonnull
  IMPLTYPE deferred_progress ();

  /**
   * @return The invocation of the jQuery deferred function <code>promise()</code> with return type Promise
   */
  @Nonnull
  IMPLTYPE deferred_promise ();

  /**
   * @return The invocation of the jQuery deferred function <code>reject()</code> with return type Deferred
   */
  @Nonnull
  IMPLTYPE deferred_reject ();

  /**
   * @return The invocation of the jQuery deferred function <code>rejectWith()</code> with return type Deferred
   */
  @Nonnull
  IMPLTYPE deferred_rejectWith ();

  /**
   * @return The invocation of the jQuery deferred function <code>resolve()</code> with return type Deferred
   */
  @Nonnull
  IMPLTYPE deferred_resolve ();

  /**
   * @return The invocation of the jQuery deferred function <code>resolveWith()</code> with return type Deferred
   */
  @Nonnull
  IMPLTYPE deferred_resolveWith ();

  /**
   * @return The invocation of the jQuery deferred function <code>state()</code> with return type String
   * @since jQuery 1.7
   */
  @Nonnull
  IMPLTYPE deferred_state ();

  /**
   * @return The invocation of the jQuery deferred function <code>then()</code> with return type Promise
   */
  @Nonnull
  IMPLTYPE deferred_then ();

  /**
   * @return The invocation of the jQuery function <code>delay()</code> with return type jQuery
   */
  @Nonnull
  IMPLTYPE delay ();

  /**
   * @return The invocation of the jQuery function <code>delegate()</code> with return type jQuery
   */
  @Nonnull
  IMPLTYPE delegate ();

  /**
   * @return The invocation of the jQuery function <code>dequeue()</code> with return type jQuery
   */
  @Nonnull
  IMPLTYPE dequeue ();

  /**
   * @return The invocation of the jQuery function <code>detach()</code> with return type jQuery
   */
  @Nonnull
  IMPLTYPE detach ();

  /**
   * @return The invocation of the jQuery function <code>die()</code> with return type jQuery
   * @deprecated Deprecated since jQuery 1.7
   */
  @Deprecated
  @Nonnull
  IMPLTYPE die ();

  /**
   * @return The invocation of the jQuery function <code>each()</code> with return type jQuery
   */
  @Nonnull
  IMPLTYPE each ();

  /**
   * @return The invocation of the jQuery function <code>empty()</code> with return type jQuery
   */
  @Nonnull
  IMPLTYPE empty ();

  /**
   * @return The invocation of the jQuery function <code>end()</code> with return type jQuery
   */
  @Nonnull
  IMPLTYPE end ();

  /**
   * @return The invocation of the jQuery function <code>eq()</code> with return type jQuery
   * @since jQuery 1.1.2
   */
  @Nonnull
  IMPLTYPE _eq ();

  /**
   * @return The invocation of the jQuery function <code>error()</code> with return type jQuery
   * @deprecated Deprecated since jQuery 1.8
   */
  @Deprecated
  @Nonnull
  IMPLTYPE error ();

  /**
   * @return The invocation of the jQuery event function <code>isDefaultPrevented()</code> with return type Boolean
   * @since jQuery 1.3
   */
  @Nonnull
  IMPLTYPE event_isDefaultPrevented ();

  /**
   * @return The invocation of the jQuery event function <code>isImmediatePropagationStopped()</code> with return type Boolean
   * @since jQuery 1.3
   */
  @Nonnull
  IMPLTYPE event_isImmediatePropagationStopped ();

  /**
   * @return The invocation of the jQuery event function <code>isPropagationStopped()</code> with return type Boolean
   * @since jQuery 1.3
   */
  @Nonnull
  IMPLTYPE event_isPropagationStopped ();

  /**
   * @return The invocation of the jQuery event function <code>preventDefault()</code> with return type undefined
   */
  @Nonnull
  IMPLTYPE event_preventDefault ();

  /**
   * @return The invocation of the jQuery event function <code>stopImmediatePropagation()</code> with return type void
   * @since jQuery 1.3
   */
  @Nonnull
  IMPLTYPE event_stopImmediatePropagation ();

  /**
   * @return The invocation of the jQuery event function <code>stopPropagation()</code> with return type void
   */
  @Nonnull
  IMPLTYPE event_stopPropagation ();

  /**
   * @return The invocation of the jQuery function <code>fadeIn()</code> with return type jQuery
   */
  @Nonnull
  IMPLTYPE fadeIn ();

  /**
   * @return The invocation of the jQuery function <code>fadeOut()</code> with return type jQuery
   */
  @Nonnull
  IMPLTYPE fadeOut ();

  /**
   * @return The invocation of the jQuery function <code>fadeTo()</code> with return type jQuery
   */
  @Nonnull
  IMPLTYPE fadeTo ();

  /**
   * @return The invocation of the jQuery function <code>fadeToggle()</code> with return type jQuery
   * @since jQuery 1.4.4
   */
  @Nonnull
  IMPLTYPE fadeToggle ();

  /**
   * @return The invocation of the jQuery function <code>filter()</code> with return type jQuery
   */
  @Nonnull
  IMPLTYPE filter ();

  /**
   * @return The invocation of the jQuery function <code>find()</code> with return type jQuery
   */
  @Nonnull
  IMPLTYPE find ();

  /**
   * @return The invocation of the jQuery function <code>finish()</code> with return type jQuery
   */
  @Nonnull
  IMPLTYPE finish ();

  /**
   * @return The invocation of the jQuery function <code>first()</code> with return type jQuery
   * @since jQuery 1.4
   */
  @Nonnull
  IMPLTYPE first ();

  /**
   * @return The invocation of the jQuery function <code>focus()</code> with return type jQuery
   */
  @Nonnull
  IMPLTYPE focus ();

  /**
   * @return The invocation of the jQuery function <code>focusin()</code> with return type jQuery
   */
  @Nonnull
  IMPLTYPE focusin ();

  /**
   * @return The invocation of the jQuery function <code>focusout()</code> with return type jQuery
   */
  @Nonnull
  IMPLTYPE focusout ();

  /**
   * @return The invocation of the jQuery function <code>get()</code> with return type Element or Array
   */
  @Nonnull
  IMPLTYPE get ();

  /**
   * @return The invocation of the jQuery function <code>has()</code> with return type jQuery
   */
  @Nonnull
  IMPLTYPE has ();

  /**
   * @return The invocation of the jQuery function <code>hasClass()</code> with return type Boolean
   * @since jQuery 1.2
   */
  @Nonnull
  IMPLTYPE hasClass ();

  /**
   * @return The invocation of the jQuery function <code>height()</code> with return type Number or jQuery
   */
  @Nonnull
  IMPLTYPE height ();

  /**
   * @return The invocation of the jQuery function <code>hide()</code> with return type jQuery
   */
  @Nonnull
  IMPLTYPE hide ();

  /**
   * @return The invocation of the jQuery function <code>hover()</code> with return type jQuery
   */
  @Nonnull
  IMPLTYPE hover ();

  /**
   * @return The invocation of the jQuery function <code>html()</code> with return type String or jQuery
   */
  @Nonnull
  IMPLTYPE html ();

  /**
   * @return The invocation of the jQuery function <code>index()</code> with return type Number
   */
  @Nonnull
  IMPLTYPE index ();

  /**
   * @return The invocation of the jQuery function <code>innerHeight()</code> with return type Number or jQuery
   */
  @Nonnull
  IMPLTYPE innerHeight ();

  /**
   * @return The invocation of the jQuery function <code>innerWidth()</code> with return type Integer or jQuery
   */
  @Nonnull
  IMPLTYPE innerWidth ();

  /**
   * @return The invocation of the jQuery function <code>insertAfter()</code> with return type jQuery
   */
  @Nonnull
  IMPLTYPE insertAfter ();

  /**
   * @return The invocation of the jQuery function <code>insertBefore()</code> with return type jQuery
   */
  @Nonnull
  IMPLTYPE insertBefore ();

  /**
   * @return The invocation of the jQuery function <code>is()</code> with return type Boolean
   */
  @Nonnull
  IMPLTYPE is ();

  /**
   * @return The invocation of the jQuery function <code>keydown()</code> with return type jQuery
   */
  @Nonnull
  IMPLTYPE keydown ();

  /**
   * @return The invocation of the jQuery function <code>keypress()</code> with return type jQuery
   */
  @Nonnull
  IMPLTYPE keypress ();

  /**
   * @return The invocation of the jQuery function <code>keyup()</code> with return type jQuery
   */
  @Nonnull
  IMPLTYPE keyup ();

  /**
   * @return The invocation of the jQuery function <code>last()</code> with return type jQuery
   * @since jQuery 1.4
   */
  @Nonnull
  IMPLTYPE last ();

  /**
   * @return The invocation of the jQuery function <code>live()</code> with return type jQuery
   * @deprecated Deprecated since jQuery 1.7
   */
  @Deprecated
  @Nonnull
  IMPLTYPE live ();

  /**
  * Certain versions of this method are deprecated since jQuery 1.8
   * @return The invocation of the jQuery function <code>load()</code> with return type jQuery
   */
  @Nonnull
  IMPLTYPE load ();

  /**
   * @return The invocation of the jQuery function <code>map()</code> with return type jQuery
   * @since jQuery 1.2
   */
  @Nonnull
  IMPLTYPE map ();

  /**
   * @return The invocation of the jQuery function <code>mousedown()</code> with return type jQuery
   */
  @Nonnull
  IMPLTYPE mousedown ();

  /**
   * @return The invocation of the jQuery function <code>mouseenter()</code> with return type jQuery
   */
  @Nonnull
  IMPLTYPE mouseenter ();

  /**
   * @return The invocation of the jQuery function <code>mouseleave()</code> with return type jQuery
   */
  @Nonnull
  IMPLTYPE mouseleave ();

  /**
   * @return The invocation of the jQuery function <code>mousemove()</code> with return type jQuery
   */
  @Nonnull
  IMPLTYPE mousemove ();

  /**
   * @return The invocation of the jQuery function <code>mouseout()</code> with return type jQuery
   */
  @Nonnull
  IMPLTYPE mouseout ();

  /**
   * @return The invocation of the jQuery function <code>mouseover()</code> with return type jQuery
   */
  @Nonnull
  IMPLTYPE mouseover ();

  /**
   * @return The invocation of the jQuery function <code>mouseup()</code> with return type jQuery
   */
  @Nonnull
  IMPLTYPE mouseup ();

  /**
   * @return The invocation of the jQuery function <code>next()</code> with return type jQuery
   */
  @Nonnull
  IMPLTYPE next ();

  /**
   * @return The invocation of the jQuery function <code>nextAll()</code> with return type jQuery
   */
  @Nonnull
  IMPLTYPE nextAll ();

  /**
   * @return The invocation of the jQuery function <code>nextUntil()</code> with return type jQuery
   */
  @Nonnull
  IMPLTYPE nextUntil ();

  /**
   * @return The invocation of the jQuery function <code>not()</code> with return type jQuery
   */
  @Nonnull
  IMPLTYPE _not ();

  /**
   * @return The invocation of the jQuery function <code>off()</code> with return type jQuery
   */
  @Nonnull
  IMPLTYPE off ();

  /**
   * @return The invocation of the jQuery function <code>offset()</code> with return type Object or jQuery
   */
  @Nonnull
  IMPLTYPE offset ();

  /**
   * @return The invocation of the jQuery function <code>offsetParent()</code> with return type jQuery
   * @since jQuery 1.2.6
   */
  @Nonnull
  IMPLTYPE offsetParent ();

  /**
   * @return The invocation of the jQuery function <code>on()</code> with return type jQuery
   */
  @Nonnull
  IMPLTYPE on ();

  /**
   * @return The invocation of the jQuery function <code>one()</code> with return type jQuery
   */
  @Nonnull
  IMPLTYPE one ();

  /**
   * @return The invocation of the jQuery function <code>outerHeight()</code> with return type Number or jQuery
   */
  @Nonnull
  IMPLTYPE outerHeight ();

  /**
   * @return The invocation of the jQuery function <code>outerWidth()</code> with return type Number or jQuery
   */
  @Nonnull
  IMPLTYPE outerWidth ();

  /**
   * @return The invocation of the jQuery function <code>parent()</code> with return type jQuery
   */
  @Nonnull
  IMPLTYPE parent ();

  /**
   * @return The invocation of the jQuery function <code>parents()</code> with return type jQuery
   */
  @Nonnull
  IMPLTYPE parents ();

  /**
   * @return The invocation of the jQuery function <code>parentsUntil()</code> with return type jQuery
   */
  @Nonnull
  IMPLTYPE parentsUntil ();

  /**
   * @return The invocation of the jQuery function <code>position()</code> with return type Object
   * @since jQuery 1.2
   */
  @Nonnull
  IMPLTYPE position ();

  /**
   * @return The invocation of the jQuery function <code>prepend()</code> with return type jQuery
   */
  @Nonnull
  IMPLTYPE prepend ();

  /**
   * @return The invocation of the jQuery function <code>prependTo()</code> with return type jQuery
   */
  @Nonnull
  IMPLTYPE prependTo ();

  /**
   * @return The invocation of the jQuery function <code>prev()</code> with return type jQuery
   */
  @Nonnull
  IMPLTYPE prev ();

  /**
   * @return The invocation of the jQuery function <code>prevAll()</code> with return type jQuery
   */
  @Nonnull
  IMPLTYPE prevAll ();

  /**
   * @return The invocation of the jQuery function <code>prevUntil()</code> with return type jQuery
   */
  @Nonnull
  IMPLTYPE prevUntil ();

  /**
   * @return The invocation of the jQuery function <code>promise()</code> with return type Promise
   */
  @Nonnull
  IMPLTYPE promise ();

  /**
   * @return The invocation of the jQuery function <code>prop()</code> with return type void or jQuery
   */
  @Nonnull
  IMPLTYPE prop ();

  /**
   * @return The invocation of the jQuery function <code>pushStack()</code> with return type jQuery
   */
  @Nonnull
  IMPLTYPE pushStack ();

  /**
   * @return The invocation of the jQuery function <code>queue()</code> with return type Array or jQuery
   */
  @Nonnull
  IMPLTYPE queue ();

  /**
   * @return The invocation of the jQuery function <code>ready()</code> with return type jQuery
   */
  @Nonnull
  IMPLTYPE ready ();

  /**
   * @return The invocation of the jQuery function <code>remove()</code> with return type jQuery
   */
  @Nonnull
  IMPLTYPE remove ();

  /**
   * @return The invocation of the jQuery function <code>removeAttr()</code> with return type jQuery
   */
  @Nonnull
  IMPLTYPE removeAttr ();

  /**
   * @return The invocation of the jQuery function <code>removeClass()</code> with return type jQuery
   */
  @Nonnull
  IMPLTYPE removeClass ();

  /**
   * @return The invocation of the jQuery function <code>removeData()</code> with return type jQuery
   */
  @Nonnull
  IMPLTYPE removeData ();

  /**
   * @return The invocation of the jQuery function <code>removeProp()</code> with return type jQuery
   * @since jQuery 1.6
   */
  @Nonnull
  IMPLTYPE removeProp ();

  /**
   * @return The invocation of the jQuery function <code>replaceAll()</code> with return type jQuery
   * @since jQuery 1.2
   */
  @Nonnull
  IMPLTYPE replaceAll ();

  /**
   * @return The invocation of the jQuery function <code>replaceWith()</code> with return type jQuery
   */
  @Nonnull
  IMPLTYPE replaceWith ();

  /**
   * @return The invocation of the jQuery function <code>resize()</code> with return type jQuery
   */
  @Nonnull
  IMPLTYPE resize ();

  /**
   * @return The invocation of the jQuery function <code>scroll()</code> with return type jQuery
   */
  @Nonnull
  IMPLTYPE scroll ();

  /**
   * @return The invocation of the jQuery function <code>scrollLeft()</code> with return type Integer or jQuery
   */
  @Nonnull
  IMPLTYPE scrollLeft ();

  /**
   * @return The invocation of the jQuery function <code>scrollTop()</code> with return type Integer or jQuery
   */
  @Nonnull
  IMPLTYPE scrollTop ();

  /**
   * @return The invocation of the jQuery function <code>select()</code> with return type jQuery
   */
  @Nonnull
  IMPLTYPE select ();

  /**
   * @return The invocation of the jQuery function <code>serialize()</code> with return type String
   */
  @Nonnull
  IMPLTYPE serialize ();

  /**
   * @return The invocation of the jQuery function <code>serializeArray()</code> with return type Array
   * @since jQuery 1.2
   */
  @Nonnull
  IMPLTYPE serializeArray ();

  /**
   * @return The invocation of the jQuery function <code>show()</code> with return type jQuery
   */
  @Nonnull
  IMPLTYPE show ();

  /**
   * @return The invocation of the jQuery function <code>siblings()</code> with return type jQuery
   */
  @Nonnull
  IMPLTYPE siblings ();

  /**
   * @return The invocation of the jQuery function <code>size()</code> with return type Integer
   * @deprecated Deprecated since jQuery 1.8
   */
  @Deprecated
  @Nonnull
  IMPLTYPE size ();

  /**
   * @return The invocation of the jQuery function <code>slice()</code> with return type jQuery
   */
  @Nonnull
  IMPLTYPE slice ();

  /**
   * @return The invocation of the jQuery function <code>slideDown()</code> with return type jQuery
   */
  @Nonnull
  IMPLTYPE slideDown ();

  /**
   * @return The invocation of the jQuery function <code>slideToggle()</code> with return type jQuery
   */
  @Nonnull
  IMPLTYPE slideToggle ();

  /**
   * @return The invocation of the jQuery function <code>slideUp()</code> with return type jQuery
   */
  @Nonnull
  IMPLTYPE slideUp ();

  /**
   * @return The invocation of the jQuery function <code>stop()</code> with return type jQuery
   */
  @Nonnull
  IMPLTYPE stop ();

  /**
   * @return The invocation of the jQuery function <code>submit()</code> with return type jQuery
   */
  @Nonnull
  IMPLTYPE submit ();

  /**
   * @return The invocation of the jQuery function <code>text()</code> with return type String or jQuery
   */
  @Nonnull
  IMPLTYPE text ();

  /**
   * @return The invocation of the jQuery function <code>toArray()</code> with return type Array
   * @since jQuery 1.4
   */
  @Nonnull
  IMPLTYPE toArray ();

  /**
  * Certain versions of this method are deprecated since jQuery 1.8
   * @return The invocation of the jQuery function <code>toggle()</code> with return type jQuery
   */
  @Nonnull
  IMPLTYPE toggle ();

  /**
   * @return The invocation of the jQuery function <code>toggleClass()</code> with return type jQuery
   */
  @Nonnull
  IMPLTYPE toggleClass ();

  /**
   * @return The invocation of the jQuery function <code>trigger()</code> with return type jQuery
   */
  @Nonnull
  IMPLTYPE trigger ();

  /**
   * @return The invocation of the jQuery function <code>triggerHandler()</code> with return type Object
   */
  @Nonnull
  IMPLTYPE triggerHandler ();

  /**
   * @return The invocation of the jQuery function <code>unbind()</code> with return type jQuery
   */
  @Nonnull
  IMPLTYPE unbind ();

  /**
   * @return The invocation of the jQuery function <code>undelegate()</code> with return type jQuery
   */
  @Nonnull
  IMPLTYPE undelegate ();

  /**
   * @return The invocation of the jQuery function <code>unload()</code> with return type jQuery
   * @deprecated Deprecated since jQuery 1.8
   */
  @Deprecated
  @Nonnull
  IMPLTYPE unload ();

  /**
   * @return The invocation of the jQuery function <code>unwrap()</code> with return type jQuery
   * @since jQuery 1.4
   */
  @Nonnull
  IMPLTYPE unwrap ();

  /**
   * @return The invocation of the jQuery function <code>val()</code> with return type void or jQuery
   */
  @Nonnull
  IMPLTYPE val ();

  /**
   * @return The invocation of the jQuery function <code>width()</code> with return type Number or jQuery
   */
  @Nonnull
  IMPLTYPE width ();

  /**
   * @return The invocation of the jQuery function <code>wrap()</code> with return type jQuery
   */
  @Nonnull
  IMPLTYPE wrap ();

  /**
   * @return The invocation of the jQuery function <code>wrapAll()</code> with return type jQuery
   */
  @Nonnull
  IMPLTYPE wrapAll ();

  /**
   * @return The invocation of the jQuery function <code>wrapInner()</code> with return type jQuery
   */
  @Nonnull
  IMPLTYPE wrapInner ();

}
