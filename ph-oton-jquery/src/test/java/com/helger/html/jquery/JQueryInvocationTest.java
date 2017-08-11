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

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.helger.html.EHTMLElement;

/**
 * Test class for class {@link JQueryInvocation}
 *
 * @author Philip Helger
 */
public final class JQueryInvocationTest
{
  @SuppressWarnings ("deprecation")
  @Test
  public void testInvokeAll ()
  {
    assertEquals ("$(document).add(5);", JQuery.jQueryDocument ().add ().arg (5).getJSCode ());
    assertEquals ("$(document).addClass(5);", JQuery.jQueryDocument ().addClass ().arg (5).getJSCode ());
    assertEquals ("$(document).after(5);", JQuery.jQueryDocument ().after ().arg (5).getJSCode ());
    assertEquals ("$(document).ajaxComplete(5);", JQuery.jQueryDocument ().ajaxComplete ().arg (5).getJSCode ());
    assertEquals ("$(document).ajaxError(5);", JQuery.jQueryDocument ().ajaxError ().arg (5).getJSCode ());
    assertEquals ("$(document).ajaxSend(5);", JQuery.jQueryDocument ().ajaxSend ().arg (5).getJSCode ());
    assertEquals ("$(document).ajaxStart(5);", JQuery.jQueryDocument ().ajaxStart ().arg (5).getJSCode ());
    assertEquals ("$(document).ajaxStop(5);", JQuery.jQueryDocument ().ajaxStop ().arg (5).getJSCode ());
    assertEquals ("$(document).ajaxSuccess(5);", JQuery.jQueryDocument ().ajaxSuccess ().arg (5).getJSCode ());
    assertEquals ("$(document).andSelf(5);", JQuery.jQueryDocument ().andSelf ().arg (5).getJSCode ());
    assertEquals ("$(document).animate(5);", JQuery.jQueryDocument ().animate ().arg (5).getJSCode ());
    assertEquals ("$(document).append(5);", JQuery.jQueryDocument ().append ().arg (5).getJSCode ());
    assertEquals ("$(document).appendTo(5);", JQuery.jQueryDocument ().appendTo ().arg (5).getJSCode ());
    assertEquals ("$(document).attr(5);", JQuery.jQueryDocument ().attr ().arg (5).getJSCode ());
    assertEquals ("$(document).before(5);", JQuery.jQueryDocument ().before ().arg (5).getJSCode ());
    assertEquals ("$(document).bind(5);", JQuery.jQueryDocument ().bind ().arg (5).getJSCode ());
    assertEquals ("$(document).blur(5);", JQuery.jQueryDocument ().blur ().arg (5).getJSCode ());
    assertEquals ("$(document).change(5);", JQuery.jQueryDocument ().change ().arg (5).getJSCode ());
    assertEquals ("$(document).children(5);", JQuery.jQueryDocument ().children ().arg (5).getJSCode ());
    assertEquals ("$(document).clearQueue(5);", JQuery.jQueryDocument ().clearQueue ().arg (5).getJSCode ());
    assertEquals ("$(document).click(5);", JQuery.jQueryDocument ().click ().arg (5).getJSCode ());
    assertEquals ("$(document).clone(5);", JQuery.jQueryDocument ()._clone ().arg (5).getJSCode ());
    assertEquals ("$(document).closest(5);", JQuery.jQueryDocument ().closest ().arg (5).getJSCode ());
    assertEquals ("$(document).contents(5);", JQuery.jQueryDocument ().contents ().arg (5).getJSCode ());
    assertEquals ("$(document).css(5);", JQuery.jQueryDocument ().css ().arg (5).getJSCode ());
    assertEquals ("$(document).data(5);", JQuery.jQueryDocument ().data ().arg (5).getJSCode ());
    assertEquals ("$(document).dblclick(5);", JQuery.jQueryDocument ().dblclick ().arg (5).getJSCode ());
    assertEquals ("$(document).delay(5);", JQuery.jQueryDocument ().delay ().arg (5).getJSCode ());
    assertEquals ("$(document).delegate(5);", JQuery.jQueryDocument ().delegate ().arg (5).getJSCode ());
    assertEquals ("$(document).dequeue(5);", JQuery.jQueryDocument ().dequeue ().arg (5).getJSCode ());
    assertEquals ("$(document).detach(5);", JQuery.jQueryDocument ().detach ().arg (5).getJSCode ());
    assertEquals ("$(document).die(5);", JQuery.jQueryDocument ().die ().arg (5).getJSCode ());
    assertEquals ("$(document).each(5);", JQuery.jQueryDocument ().each ().arg (5).getJSCode ());
    assertEquals ("$(document).empty(5);", JQuery.jQueryDocument ().empty ().arg (5).getJSCode ());
    assertEquals ("$(document).end(5);", JQuery.jQueryDocument ().end ().arg (5).getJSCode ());
    assertEquals ("$(document).eq(5);", JQuery.jQueryDocument ()._eq ().arg (5).getJSCode ());
    assertEquals ("$(document).error(5);", JQuery.jQueryDocument ().error ().arg (5).getJSCode ());
    assertEquals ("$(document).fadeIn(5);", JQuery.jQueryDocument ().fadeIn ().arg (5).getJSCode ());
    assertEquals ("$(document).fadeOut(5);", JQuery.jQueryDocument ().fadeOut ().arg (5).getJSCode ());
    assertEquals ("$(document).fadeTo(5);", JQuery.jQueryDocument ().fadeTo ().arg (5).getJSCode ());
    assertEquals ("$(document).fadeToggle(5);", JQuery.jQueryDocument ().fadeToggle ().arg (5).getJSCode ());
    assertEquals ("$(document).filter(5);", JQuery.jQueryDocument ().filter ().arg (5).getJSCode ());
    assertEquals ("$(document).find(5);", JQuery.jQueryDocument ().find ().arg (5).getJSCode ());
    assertEquals ("$(document).first(5);", JQuery.jQueryDocument ().first ().arg (5).getJSCode ());
    assertEquals ("$(document).focus(5);", JQuery.jQueryDocument ().focus ().arg (5).getJSCode ());
    assertEquals ("$(document).focusin(5);", JQuery.jQueryDocument ().focusin ().arg (5).getJSCode ());
    assertEquals ("$(document).focusout(5);", JQuery.jQueryDocument ().focusout ().arg (5).getJSCode ());
    assertEquals ("$(document).get(5);", JQuery.jQueryDocument ().get ().arg (5).getJSCode ());
    assertEquals ("$(document).has(5);", JQuery.jQueryDocument ().has ().arg (5).getJSCode ());
    assertEquals ("$(document).hasClass(5);", JQuery.jQueryDocument ().hasClass ().arg (5).getJSCode ());
    assertEquals ("$(document).height(5);", JQuery.jQueryDocument ().height ().arg (5).getJSCode ());
    assertEquals ("$(document).hide(5);", JQuery.jQueryDocument ().hide ().arg (5).getJSCode ());
    assertEquals ("$(document).hover(5);", JQuery.jQueryDocument ().hover ().arg (5).getJSCode ());
    assertEquals ("$(document).html(5);", JQuery.jQueryDocument ().html ().arg (5).getJSCode ());
    assertEquals ("$(document).index(5);", JQuery.jQueryDocument ().index ().arg (5).getJSCode ());
    assertEquals ("$(document).innerHeight(5);", JQuery.jQueryDocument ().innerHeight ().arg (5).getJSCode ());
    assertEquals ("$(document).innerWidth(5);", JQuery.jQueryDocument ().innerWidth ().arg (5).getJSCode ());
    assertEquals ("$(document).insertAfter(5);", JQuery.jQueryDocument ().insertAfter ().arg (5).getJSCode ());
    assertEquals ("$(document).insertBefore(5);", JQuery.jQueryDocument ().insertBefore ().arg (5).getJSCode ());
    assertEquals ("$(document).is(5);", JQuery.jQueryDocument ().is ().arg (5).getJSCode ());
    assertEquals ("$(document).jquery", JQuery.jQueryDocument ().jquery ().getJSCode ());
    assertEquals ("$(document).keydown(5);", JQuery.jQueryDocument ().keydown ().arg (5).getJSCode ());
    assertEquals ("$(document).keypress(5);", JQuery.jQueryDocument ().keypress ().arg (5).getJSCode ());
    assertEquals ("$(document).keyup(5);", JQuery.jQueryDocument ().keyup ().arg (5).getJSCode ());
    assertEquals ("$(document).last(5);", JQuery.jQueryDocument ().last ().arg (5).getJSCode ());
    assertEquals ("$(document).length", JQuery.jQueryDocument ().length ().getJSCode ());
    assertEquals ("$(document).live(5);", JQuery.jQueryDocument ().live ().arg (5).getJSCode ());
    assertEquals ("$(document).load(5);", JQuery.jQueryDocument ().load ().arg (5).getJSCode ());
    assertEquals ("$(document).map(5);", JQuery.jQueryDocument ().map ().arg (5).getJSCode ());
    assertEquals ("$(document).mousedown(5);", JQuery.jQueryDocument ().mousedown ().arg (5).getJSCode ());
    assertEquals ("$(document).mouseenter(5);", JQuery.jQueryDocument ().mouseenter ().arg (5).getJSCode ());
    assertEquals ("$(document).mouseleave(5);", JQuery.jQueryDocument ().mouseleave ().arg (5).getJSCode ());
    assertEquals ("$(document).mousemove(5);", JQuery.jQueryDocument ().mousemove ().arg (5).getJSCode ());
    assertEquals ("$(document).mouseout(5);", JQuery.jQueryDocument ().mouseout ().arg (5).getJSCode ());
    assertEquals ("$(document).mouseover(5);", JQuery.jQueryDocument ().mouseover ().arg (5).getJSCode ());
    assertEquals ("$(document).mouseup(5);", JQuery.jQueryDocument ().mouseup ().arg (5).getJSCode ());
    assertEquals ("$(document).next(5);", JQuery.jQueryDocument ().next ().arg (5).getJSCode ());
    assertEquals ("$(document).nextAll(5);", JQuery.jQueryDocument ().nextAll ().arg (5).getJSCode ());
    assertEquals ("$(document).nextUntil(5);", JQuery.jQueryDocument ().nextUntil ().arg (5).getJSCode ());
    assertEquals ("$(document).not(5);", JQuery.jQueryDocument ()._not ().arg (5).getJSCode ());
    assertEquals ("$(document).off(5);", JQuery.jQueryDocument ().off ().arg (5).getJSCode ());
    assertEquals ("$(document).offset(5);", JQuery.jQueryDocument ().offset ().arg (5).getJSCode ());
    assertEquals ("$(document).offsetParent(5);", JQuery.jQueryDocument ().offsetParent ().arg (5).getJSCode ());
    assertEquals ("$(document).on(5);", JQuery.jQueryDocument ().on ().arg (5).getJSCode ());
    assertEquals ("$(document).one(5);", JQuery.jQueryDocument ().one ().arg (5).getJSCode ());
    assertEquals ("$(document).outerHeight(5);", JQuery.jQueryDocument ().outerHeight ().arg (5).getJSCode ());
    assertEquals ("$(document).outerWidth(5);", JQuery.jQueryDocument ().outerWidth ().arg (5).getJSCode ());
    assertEquals ("$(document).parent(5);", JQuery.jQueryDocument ().parent ().arg (5).getJSCode ());
    assertEquals ("$(document).parents(5);", JQuery.jQueryDocument ().parents ().arg (5).getJSCode ());
    assertEquals ("$(document).parentsUntil(5);", JQuery.jQueryDocument ().parentsUntil ().arg (5).getJSCode ());
    assertEquals ("$(document).position(5);", JQuery.jQueryDocument ().position ().arg (5).getJSCode ());
    assertEquals ("$(document).prepend(5);", JQuery.jQueryDocument ().prepend ().arg (5).getJSCode ());
    assertEquals ("$(document).prependTo(5);", JQuery.jQueryDocument ().prependTo ().arg (5).getJSCode ());
    assertEquals ("$(document).prev(5);", JQuery.jQueryDocument ().prev ().arg (5).getJSCode ());
    assertEquals ("$(document).prevAll(5);", JQuery.jQueryDocument ().prevAll ().arg (5).getJSCode ());
    assertEquals ("$(document).prevUntil(5);", JQuery.jQueryDocument ().prevUntil ().arg (5).getJSCode ());
    assertEquals ("$(document).promise(5);", JQuery.jQueryDocument ().promise ().arg (5).getJSCode ());
    assertEquals ("$(document).prop(5);", JQuery.jQueryDocument ().prop ().arg (5).getJSCode ());
    assertEquals ("$(document).pushStack(5);", JQuery.jQueryDocument ().pushStack ().arg (5).getJSCode ());
    assertEquals ("$(document).queue(5);", JQuery.jQueryDocument ().queue ().arg (5).getJSCode ());
    assertEquals ("$(document).ready(5);", JQuery.jQueryDocument ().ready ().arg (5).getJSCode ());
    assertEquals ("$(document).remove(5);", JQuery.jQueryDocument ().remove ().arg (5).getJSCode ());
    assertEquals ("$(document).removeAttr(5);", JQuery.jQueryDocument ().removeAttr ().arg (5).getJSCode ());
    assertEquals ("$(document).removeClass(5);", JQuery.jQueryDocument ().removeClass ().arg (5).getJSCode ());
    assertEquals ("$(document).removeData(5);", JQuery.jQueryDocument ().removeData ().arg (5).getJSCode ());
    assertEquals ("$(document).removeProp(5);", JQuery.jQueryDocument ().removeProp ().arg (5).getJSCode ());
    assertEquals ("$(document).replaceAll(5);", JQuery.jQueryDocument ().replaceAll ().arg (5).getJSCode ());
    assertEquals ("$(document).replaceWith(5);", JQuery.jQueryDocument ().replaceWith ().arg (5).getJSCode ());
    assertEquals ("$(document).resize(5);", JQuery.jQueryDocument ().resize ().arg (5).getJSCode ());
    assertEquals ("$(document).scroll(5);", JQuery.jQueryDocument ().scroll ().arg (5).getJSCode ());
    assertEquals ("$(document).scrollLeft(5);", JQuery.jQueryDocument ().scrollLeft ().arg (5).getJSCode ());
    assertEquals ("$(document).scrollTop(5);", JQuery.jQueryDocument ().scrollTop ().arg (5).getJSCode ());
    assertEquals ("$(document).select(5);", JQuery.jQueryDocument ().select ().arg (5).getJSCode ());
    assertEquals ("$(document).serialize(5);", JQuery.jQueryDocument ().serialize ().arg (5).getJSCode ());
    assertEquals ("$(document).serializeArray(5);", JQuery.jQueryDocument ().serializeArray ().arg (5).getJSCode ());
    assertEquals ("$(document).show(5);", JQuery.jQueryDocument ().show ().arg (5).getJSCode ());
    assertEquals ("$(document).siblings(5);", JQuery.jQueryDocument ().siblings ().arg (5).getJSCode ());
    assertEquals ("$(document).size(5);", JQuery.jQueryDocument ().size ().arg (5).getJSCode ());
    assertEquals ("$(document).slice(5);", JQuery.jQueryDocument ().slice ().arg (5).getJSCode ());
    assertEquals ("$(document).slideDown(5);", JQuery.jQueryDocument ().slideDown ().arg (5).getJSCode ());
    assertEquals ("$(document).slideToggle(5);", JQuery.jQueryDocument ().slideToggle ().arg (5).getJSCode ());
    assertEquals ("$(document).slideUp(5);", JQuery.jQueryDocument ().slideUp ().arg (5).getJSCode ());
    assertEquals ("$(document).stop(5);", JQuery.jQueryDocument ().stop ().arg (5).getJSCode ());
    assertEquals ("$(document).submit(5);", JQuery.jQueryDocument ().submit ().arg (5).getJSCode ());
    assertEquals ("$(document).text(5);", JQuery.jQueryDocument ().text ().arg (5).getJSCode ());
    assertEquals ("$(document).toArray(5);", JQuery.jQueryDocument ().toArray ().arg (5).getJSCode ());
    assertEquals ("$(document).toggle(5);", JQuery.jQueryDocument ().toggle ().arg (5).getJSCode ());
    assertEquals ("$(document).toggleClass(5);", JQuery.jQueryDocument ().toggleClass ().arg (5).getJSCode ());
    assertEquals ("$(document).trigger(5);", JQuery.jQueryDocument ().trigger ().arg (5).getJSCode ());
    assertEquals ("$(document).triggerHandler(5);", JQuery.jQueryDocument ().triggerHandler ().arg (5).getJSCode ());
    assertEquals ("$(document).unbind(5);", JQuery.jQueryDocument ().unbind ().arg (5).getJSCode ());
    assertEquals ("$(document).undelegate(5);", JQuery.jQueryDocument ().undelegate ().arg (5).getJSCode ());
    assertEquals ("$(document).unload(5);", JQuery.jQueryDocument ().unload ().arg (5).getJSCode ());
    assertEquals ("$(document).unwrap(5);", JQuery.jQueryDocument ().unwrap ().arg (5).getJSCode ());
    assertEquals ("$(document).val(5);", JQuery.jQueryDocument ().val ().arg (5).getJSCode ());
    assertEquals ("$(document).width(5);", JQuery.jQueryDocument ().width ().arg (5).getJSCode ());
    assertEquals ("$(document).wrap(5);", JQuery.jQueryDocument ().wrap ().arg (5).getJSCode ());
    assertEquals ("$(document).wrapAll(5);", JQuery.jQueryDocument ().wrapAll ().arg (5).getJSCode ());
    assertEquals ("$(document).wrapInner(5);", JQuery.jQueryDocument ().wrapInner ().arg (5).getJSCode ());
    assertEquals ("$(document).enable(5);", JQuery.jQueryDocument ().enable ().arg (5).getJSCode ());
    assertEquals ("$(document).disable(5);", JQuery.jQueryDocument ().disable ().arg (5).getJSCode ());
    assertEquals ("$(document).check(5);", JQuery.jQueryDocument ().check ().arg (5).getJSCode ());
    assertEquals ("$(document).uncheck(5);", JQuery.jQueryDocument ().uncheck ().arg (5).getJSCode ());
  }

  @Test
  public void testArgs ()
  {
    assertEquals ("$(document).on($('#abc'));", JQuery.jQueryDocument ().on ().arg (JQuery.idRef ("abc")).getJSCode ());
    assertEquals ("$(document).on('#abc');",
                  JQuery.jQueryDocument ().on ().arg (JQuerySelector.id ("abc")).getJSCode ());
    assertEquals ("$(document).on('#abc td');",
                  JQuery.jQueryDocument ()
                        .on ()
                        .arg (new JQuerySelectorList (JQuerySelector.id ("abc"),
                                                      JQuerySelector.element (EHTMLElement.TD)))
                        .getJSCode ());
    assertEquals ("$(document).on('#abc td');",
                  JQuery.jQueryDocument ()
                        .on ()
                        .arg (JQuerySelector.id ("abc").descendant (JQuerySelector.element (EHTMLElement.TD)))
                        .getJSCode ());
  }
}
