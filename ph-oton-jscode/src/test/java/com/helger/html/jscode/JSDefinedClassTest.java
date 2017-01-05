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
package com.helger.html.jscode;

import org.junit.Test;

import com.helger.html.jscode.html.JSHtml;

/**
 * Test class for class {@link JSDefinedClass}
 *
 * @author Philip Helger
 */
public final class JSDefinedClassTest
{
  @Test
  public void test () throws Exception
  {
    final JSPackage aPkg = new JSPackage ();
    final JSDefinedClass aClass = aPkg._class ("DummyClass");

    aClass.field ("m_nValue");
    final JSFieldVar jsDValue = aClass.field ("m_dValue", JSExpr.lit (0.0));

    JSMethod aMethod = aClass.method ("getElement");
    final JSVar jsID = aMethod.param ("id");
    aMethod.body ()._return (JSHtml.documentGetElementById (jsID));

    aMethod = aClass.method ("getElement2");
    final JSVar jsName = aMethod.param ("name");
    aMethod.body ()._return (JSHtml.documentGetElementsByTagName (jsName).ref ("value").plus (jsDValue));

    final JSConstructor aCtor = aClass.constructor ();
    final JSVar jsParam = aCtor.param ("param");
    aCtor.body ().invoke ("some_global_init").arg (jsParam);

    if (false)
      System.out.println (aPkg.getJSCode ());
  }
}
