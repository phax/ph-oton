/**
 * Copyright (C) 2014-2020 Philip Helger (www.helger.com)
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
package com.helger.html.jscode;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.helger.html.js.JSWriterSettings;

/**
 * Test class for class {@link JSBlock}
 *
 * @author Philip Helger
 */
public final class JSBlockTest
{
  @Test
  public void test () throws Exception
  {
    final JSWriterSettings aSettings = new JSWriterSettings ().setIndentAndAlign (false);

    final JSBlock aBlock = new JSBlock ();
    assertEquals (0, aBlock.pos ());
    assertTrue (aBlock.isEmpty ());
    assertEquals ("{}", aBlock.getJSCode (aSettings));

    JSVar a = aBlock.var ("a");
    assertEquals (1, aBlock.pos ());
    assertFalse (aBlock.isEmpty ());
    assertEquals ("{var a;}", aBlock.getJSCode (aSettings));

    final JSVar b = aBlock.var ("b", 5);
    assertEquals (2, aBlock.pos ());
    assertFalse (aBlock.isEmpty ());
    assertEquals ("{var a;var b=5;}", aBlock.getJSCode (aSettings));

    aBlock.assign (a, b);
    assertEquals (3, aBlock.pos ());
    assertEquals ("{var a;var b=5;a=b;}", aBlock.getJSCode (aSettings));

    aBlock.assign (b, true);
    assertEquals (4, aBlock.pos ());
    assertEquals ("{var a;var b=5;a=b;b=true;}", aBlock.getJSCode (aSettings));

    aBlock.assign (a, 'c');
    assertEquals ("{var a;var b=5;a=b;b=true;a='c';}", aBlock.getJSCode (aSettings));

    aBlock.assign (b, 3.1234);
    assertEquals ("{var a;var b=5;a=b;b=true;a='c';b=3.1234;}", aBlock.getJSCode (aSettings));

    aBlock.assign (a, 47.5f);
    assertEquals ("{var a;var b=5;a=b;b=true;a='c';b=3.1234;a=47.5;}", aBlock.getJSCode (aSettings));

    aBlock.assign (b, 65599);
    assertEquals ("{var a;var b=5;a=b;b=true;a='c';b=3.1234;a=47.5;b=65599;}", aBlock.getJSCode (aSettings));

    aBlock.assign (a, 655996559965599L);
    assertEquals ("{var a;var b=5;a=b;b=true;a='c';b=3.1234;a=47.5;b=65599;a=655996559965599;}", aBlock.getJSCode (aSettings));
    final int nCurPos = aBlock.pos ();

    aBlock.assign (b, "Ha llo");
    assertEquals ("{var a;var b=5;a=b;b=true;a='c';b=3.1234;a=47.5;b=65599;a=655996559965599;b='Ha llo';}", aBlock.getJSCode (aSettings));

    aBlock.assignPlus (b, '!');
    assertEquals ("{var a;var b=5;a=b;b=true;a='c';b=3.1234;a=47.5;b=65599;a=655996559965599;b='Ha llo';b+='!';}",
                  aBlock.getJSCode (aSettings));

    aBlock.pos (nCurPos);
    aBlock.assignPlus (a, 5.0);
    assertEquals ("{var a;var b=5;a=b;b=true;a='c';b=3.1234;a=47.5;b=65599;a=655996559965599;a+=5.0;b='Ha llo';b+='!';}",
                  aBlock.getJSCode (aSettings));

    aBlock.assignPlus (a, -4.0);
    assertEquals ("{var a;var b=5;a=b;b=true;a='c';b=3.1234;a=47.5;b=65599;a=655996559965599;a+=5.0;a-=4.0;b='Ha llo';b+='!';}",
                  aBlock.getJSCode (aSettings));

    aBlock.assignPlus (a, 0.0);
    assertEquals ("{var a;var b=5;a=b;b=true;a='c';b=3.1234;a=47.5;b=65599;a=655996559965599;a+=5.0;a-=4.0;b='Ha llo';b+='!';}",
                  aBlock.getJSCode (aSettings));

    aBlock.assignMinus (a, 0.0);
    assertEquals ("{var a;var b=5;a=b;b=true;a='c';b=3.1234;a=47.5;b=65599;a=655996559965599;a+=5.0;a-=4.0;b='Ha llo';b+='!';}",
                  aBlock.getJSCode (aSettings));

    aBlock.assignPlus (a, 27f);
    assertEquals ("{var a;var b=5;a=b;b=true;a='c';b=3.1234;a=47.5;b=65599;a=655996559965599;a+=5.0;a-=4.0;a+=27.0;b='Ha llo';b+='!';}",
                  aBlock.getJSCode (aSettings));

    aBlock.assignPlus (a, -26f);
    assertEquals ("{var a;var b=5;a=b;b=true;a='c';b=3.1234;a=47.5;b=65599;a=655996559965599;a+=5.0;a-=4.0;a+=27.0;a-=26.0;b='Ha llo';b+='!';}",
                  aBlock.getJSCode (aSettings));

    aBlock.assignPlus (a, 0.0f);
    assertEquals ("{var a;var b=5;a=b;b=true;a='c';b=3.1234;a=47.5;b=65599;a=655996559965599;a+=5.0;a-=4.0;a+=27.0;a-=26.0;b='Ha llo';b+='!';}",
                  aBlock.getJSCode (aSettings));

    aBlock.assignMinus (a, 0.0f);
    assertEquals ("{var a;var b=5;a=b;b=true;a='c';b=3.1234;a=47.5;b=65599;a=655996559965599;a+=5.0;a-=4.0;a+=27.0;a-=26.0;b='Ha llo';b+='!';}",
                  aBlock.getJSCode (aSettings));

    aBlock.assignPlus (a, 32);
    assertEquals ("{var a;var b=5;a=b;b=true;a='c';b=3.1234;a=47.5;b=65599;a=655996559965599;a+=5.0;a-=4.0;a+=27.0;a-=26.0;a+=32;b='Ha llo';b+='!';}",
                  aBlock.getJSCode (aSettings));

    aBlock.assignPlus (a, -33);
    assertEquals ("{var a;var b=5;a=b;b=true;a='c';b=3.1234;a=47.5;b=65599;a=655996559965599;a+=5.0;a-=4.0;a+=27.0;a-=26.0;a+=32;a-=33;b='Ha llo';b+='!';}",
                  aBlock.getJSCode (aSettings));

    aBlock.assignPlus (a, 0);
    assertEquals ("{var a;var b=5;a=b;b=true;a='c';b=3.1234;a=47.5;b=65599;a=655996559965599;a+=5.0;a-=4.0;a+=27.0;a-=26.0;a+=32;a-=33;b='Ha llo';b+='!';}",
                  aBlock.getJSCode (aSettings));

    aBlock.assignMinus (a, 0);
    assertEquals ("{var a;var b=5;a=b;b=true;a='c';b=3.1234;a=47.5;b=65599;a=655996559965599;a+=5.0;a-=4.0;a+=27.0;a-=26.0;a+=32;a-=33;b='Ha llo';b+='!';}",
                  aBlock.getJSCode (aSettings));

    aBlock.assignPlus (a, 1234567890111L);
    assertEquals ("{var a;var b=5;a=b;b=true;a='c';b=3.1234;a=47.5;b=65599;a=655996559965599;a+=5.0;a-=4.0;a+=27.0;a-=26.0;a+=32;a-=33;a+=1234567890111;b='Ha llo';b+='!';}",
                  aBlock.getJSCode (aSettings));

    aBlock.assignPlus (a, -9876543219888L);
    assertEquals ("{var a;var b=5;a=b;b=true;a='c';b=3.1234;a=47.5;b=65599;a=655996559965599;a+=5.0;a-=4.0;a+=27.0;a-=26.0;a+=32;a-=33;a+=1234567890111;a-=9876543219888;b='Ha llo';b+='!';}",
                  aBlock.getJSCode (aSettings));

    aBlock.assignPlus (a, 0L);
    assertEquals ("{var a;var b=5;a=b;b=true;a='c';b=3.1234;a=47.5;b=65599;a=655996559965599;a+=5.0;a-=4.0;a+=27.0;a-=26.0;a+=32;a-=33;a+=1234567890111;a-=9876543219888;b='Ha llo';b+='!';}",
                  aBlock.getJSCode (aSettings));

    aBlock.assignMinus (a, 0L);
    assertEquals ("{var a;var b=5;a=b;b=true;a='c';b=3.1234;a=47.5;b=65599;a=655996559965599;a+=5.0;a-=4.0;a+=27.0;a-=26.0;a+=32;a-=33;a+=1234567890111;a-=9876543219888;b='Ha llo';b+='!';}",
                  aBlock.getJSCode (aSettings));

    aBlock.posEnd ();
    aBlock.assignPlus (b, " oder?");
    assertEquals ("{var a;var b=5;a=b;b=true;a='c';b=3.1234;a=47.5;b=65599;a=655996559965599;a+=5.0;a-=4.0;a+=27.0;a-=26.0;a+=32;a-=33;a+=1234567890111;a-=9876543219888;b='Ha llo';b+='!';b+=' oder?';}",
                  aBlock.getJSCode (aSettings));

    aBlock.clear ();
    assertEquals ("{}", aBlock.getJSCode (aSettings));
    assertTrue (aBlock.isEmpty ());
    assertEquals (0, aBlock.pos ());

    a = aBlock.var ("a", 5);
    assertEquals (1, aBlock.pos ());
    assertFalse (aBlock.isEmpty ());
    assertEquals ("{var a=5;}", aBlock.getJSCode (aSettings));

    aBlock.assignMultiply (a, 2.5);
    assertEquals ("{var a=5;a*=2.5;}", aBlock.getJSCode (aSettings));

    aBlock.assignMultiply (a, 1.0);
    assertEquals ("{var a=5;a*=2.5;}", aBlock.getJSCode (aSettings));

    aBlock.assignMultiply (a, 3.25f);
    assertEquals ("{var a=5;a*=2.5;a*=3.25;}", aBlock.getJSCode (aSettings));

    aBlock.assignMultiply (a, 1.0f);
    assertEquals ("{var a=5;a*=2.5;a*=3.25;}", aBlock.getJSCode (aSettings));

    aBlock.assignMultiply (a, 4);
    assertEquals ("{var a=5;a*=2.5;a*=3.25;a*=4;}", aBlock.getJSCode (aSettings));

    aBlock.assignMultiply (a, 1);
    assertEquals ("{var a=5;a*=2.5;a*=3.25;a*=4;}", aBlock.getJSCode (aSettings));

    aBlock.assignMultiply (a, 56L);
    assertEquals ("{var a=5;a*=2.5;a*=3.25;a*=4;a*=56;}", aBlock.getJSCode (aSettings));

    aBlock.assignMultiply (a, 1L);
    assertEquals ("{var a=5;a*=2.5;a*=3.25;a*=4;a*=56;}", aBlock.getJSCode (aSettings));

    aBlock.assignDivide (a, 4.5);
    assertEquals ("{var a=5;a*=2.5;a*=3.25;a*=4;a*=56;a/=4.5;}", aBlock.getJSCode (aSettings));

    aBlock.assignDivide (a, 1.0);
    assertEquals ("{var a=5;a*=2.5;a*=3.25;a*=4;a*=56;a/=4.5;}", aBlock.getJSCode (aSettings));

    aBlock.assignDivide (a, 22f);
    assertEquals ("{var a=5;a*=2.5;a*=3.25;a*=4;a*=56;a/=4.5;a/=22.0;}", aBlock.getJSCode (aSettings));

    aBlock.assignDivide (a, 1f);
    assertEquals ("{var a=5;a*=2.5;a*=3.25;a*=4;a*=56;a/=4.5;a/=22.0;}", aBlock.getJSCode (aSettings));

    aBlock.assignDivide (a, 3);
    assertEquals ("{var a=5;a*=2.5;a*=3.25;a*=4;a*=56;a/=4.5;a/=22.0;a/=3;}", aBlock.getJSCode (aSettings));

    aBlock.assignDivide (a, 1);
    assertEquals ("{var a=5;a*=2.5;a*=3.25;a*=4;a*=56;a/=4.5;a/=22.0;a/=3;}", aBlock.getJSCode (aSettings));

    aBlock.assignDivide (a, 11L);
    assertEquals ("{var a=5;a*=2.5;a*=3.25;a*=4;a*=56;a/=4.5;a/=22.0;a/=3;a/=11;}", aBlock.getJSCode (aSettings));

    aBlock.assignDivide (a, 1L);
    assertEquals ("{var a=5;a*=2.5;a*=3.25;a*=4;a*=56;a/=4.5;a/=22.0;a/=3;a/=11;}", aBlock.getJSCode (aSettings));

    aBlock.assignModulo (a, 101);
    assertEquals ("{var a=5;a*=2.5;a*=3.25;a*=4;a*=56;a/=4.5;a/=22.0;a/=3;a/=11;a%=101;}", aBlock.getJSCode (aSettings));

    aBlock.assignModulo (a, 56L);
    assertEquals ("{var a=5;a*=2.5;a*=3.25;a*=4;a*=56;a/=4.5;a/=22.0;a/=3;a/=11;a%=101;a%=56;}", aBlock.getJSCode (aSettings));

    aBlock.assign (a, "Test");
    assertEquals ("{var a=5;a*=2.5;a*=3.25;a*=4;a*=56;a/=4.5;a/=22.0;a/=3;a/=11;a%=101;a%=56;a='Test';}", aBlock.getJSCode (aSettings));

    aBlock.invoke (a, "length");
    assertEquals ("{var a=5;a*=2.5;a*=3.25;a*=4;a*=56;a/=4.5;a/=22.0;a/=3;a/=11;a%=101;a%=56;a='Test';a.length();}",
                  aBlock.getJSCode (aSettings));

    final JSBlock aSubBlock = aBlock._if (JSOp.eeq (JSExpr.lit (1), JSExpr.lit (1)))._then ();
    aSubBlock._return (false);
    assertEquals ("{var a=5;a*=2.5;a*=3.25;a*=4;a*=56;a/=4.5;a/=22.0;a/=3;a/=11;a%=101;a%=56;a='Test';a.length();if(1===1){return false;}}",
                  aBlock.getJSCode (aSettings));

    assertEquals (aBlock.toString (), aBlock.toString ());
  }

  @Test
  public void testCoverage ()
  {
    AbstractJSExpression a = JSExpr.ref ("a");
    a = a.plus ('a').plus (1d).plus (2f).plus (5).plus (6l).plus ("abc");
    a = a.minus (1d).minus (2f).minus (5).minus (6l).minus ();
    a = a.mul (1d).mul (2f).mul (5).mul (6l);
    a = a.div (1d).div (2f).div (5).div (6l);
    a = a.gt (1d).gt (2f).gt (5).gt (6l);
    a = a.gte (1d).gte (2f).gte (5).gte (6l);
    a = a.lt (1d).lt (2f).lt (5).lt (6l);
    a = a.lte (1d).lte (2f).lte (5).lte (6l);
    a = a.eq (true).eq ('a').eq (1d).eq (2f).eq (5).eq (6l).eq ("abc");
    a = a.eeq (true).eeq ('a').eeq (1d).eeq (2f).eeq (5).eeq (6l).eeq ("abc");
    a = a.ne (true).ne ('a').ne (1d).ne (2f).ne (5).ne (6l).ne ("abc");
    a = a.ene (true).ene ('a').ene (1d).ene (2f).ene (5).ene (6l).ene ("abc");
    assertNotNull (a);
  }
}
