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

import static org.junit.Assert.assertEquals;

import javax.annotation.Nonnull;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.helger.commons.mock.CommonsTestHelper;
import com.helger.html.js.JSWriterSettings;
import com.helger.html.js.UnparsedJSCodeProvider;
import com.helger.html.jscode.type.JSPrimitiveType;

/**
 * Test class for class {@link JSPackage}
 *
 * @author Philip Helger
 */
public final class JSPackageTest
{
  private static final Logger s_aLogger = LoggerFactory.getLogger (JSPackageTest.class);

  @Nonnull
  private JSPackage _createMockPackage ()
  {
    final JSPackage aPkg = new JSPackage ();

    // Global variable
    aPkg.var ("g_aRoot", JSExpr.lit (0));

    // Crude function
    {
      final JSFunction aFuncMain = aPkg.function ("mainAdd");
      aFuncMain.jsDoc ().add ("This is a global function");
      aFuncMain.jsDoc ().add ("It does only crappy things");
      final JSVar m1 = aFuncMain.param ("m1");
      aFuncMain.jsDoc ().addParam (m1).add ("Any kind of value");

      // function variable
      final JSVar aRoot = aFuncMain.body ().var ("root", JSExpr.lit (5));

      // inline function
      final JSFunction aFunc = aFuncMain.body ().function ("add");
      {
        aFunc.jsDoc ().add ("This is a nested function");
        final JSVar s1 = aFunc.param ("s1");
        final JSVar s2 = aFunc.param ("s2");
        aFunc.body ()._return (s1.plus (s2));

        // Call nested function
        aFuncMain.body ().invoke (aFunc).arg (32).arg (-4);
      }

      // Dynamic function
      {
        final JSVar aAdd2 = aFuncMain.body ()
                                     .var ("add2",
                                           JSPrimitiveType.FUNCTION._new ().arg ("x").arg ("y").arg ("return x+y"));
        aFuncMain.body ().invoke (aAdd2.name ()).arg (1).arg (2);
      }

      // if
      final JSConditional aCond = aFuncMain.body ()._if (m1.isTypeof (JSPrimitiveType.STRING));

      // try catch finally
      aCond._then ().comment ("Test try/catch/finally");
      final JSTryBlock aTB = aCond._then ()._try ();
      aTB.body ()._return (5);
      final JSCatchBlock aCB = aTB._catch ("ex");
      aCB.body ()._throw (JSPrimitiveType.ERROR._new ().arg (aCB.param ()));
      aTB._finally ().invoke (aRoot, "substring").arg (0).arg (1);

      // RegExp
      aFuncMain.body ().comment ("Test reg exps");
      aFuncMain.body ().invoke (JSExpr.regex ("water(mark)?").gim (true, true, true), "test").arg ("waterMark");
      aFuncMain.body ().invoke (JSExpr.regex ("water(mark)?").gim (false, true, false), "test").arg ("Water");
      aFuncMain.body ().invoke (JSExpr.lit ("string"), "search").arg (JSExpr.regex ("expression"));
      aFuncMain.body ().invoke (JSExpr.lit ("string"), "replace").arg (JSExpr.regex ("expression")).arg ("replacement");

      // Anonymous function
      {
        final JSAnonymousFunction a = new JSAnonymousFunction ();
        final JSVar av = a.param ("a");
        a.body ()._return (av.plus (0.5));
        aFuncMain.body ().invoke (a).arg (7.5);
      }

      // Array
      final JSVar aArray1 = aFuncMain.body ().var ("array1", new JSArray ().add (5));
      aFuncMain.body ().assign (aArray1.component (0), 6);

      final JSVar aArray1a = aFuncMain.body ().var ("array1a", JSPrimitiveType.ARRAY._new ().arg (5));
      aFuncMain.body ().assign (aArray1a.component (0), 7);
      aFuncMain.body ().invoke (aArray1a, "push").arg ("pushed");

      // Associative Array
      final JSVar aArray2 = aFuncMain.body ().var ("array2",
                                                   new JSAssocArray ().add ("num", 1)
                                                                      .add ("array", aArray1)
                                                                      .add ("assocarray",
                                                                            new JSAssocArray ().add ("key", "value")
                                                                                               .add ("key2",
                                                                                                     "anything else")));
      aFuncMain.body ().assign (aArray2.component ("num"), 6);

      // concatenate misc things
      aFuncMain.body ()
               ._return (m1.plus (JSExpr.lit ("abc").ref ("length"))
                           .plus (aRoot)
                           .plus (aFunc.invoke ().arg (2).arg (4))
                           .plus (7)
                           .mul (1.5)
                           .plus (5)
                           .minus (3)
                           .div (2));
    }

    {
      /**
       * <pre>
       * function sajax_extract_htmlcomments (sHTML) {
       *   var sComments = '';
       *   // Lazy quantifier "*?"
       *   sHTML = sHTML.replace(/<!--([\s\S]*?)-->/g, function(all, sComment){
       *     sComments += sComment + '\n';
       *     return '';
       *   });
       *   // Remaining HTML + comments content
       *   return { html:sHTML, comments:sComments };;
       * }
       * </pre>
       */
      final JSFunction aFuncMain = aPkg.function ("sajax_extract_htmlcomments");
      final JSVar sHTML = aFuncMain.param ("sHTML");
      final JSVar sComments = aFuncMain.body ().var ("sComments", "");
      aFuncMain.body ().comment ("Lazy quantifier \"*?\"");
      final JSAnonymousFunction anonFunction = new JSAnonymousFunction ();
      anonFunction.param ("all");
      final JSVar sComment = anonFunction.param ("sComment");
      anonFunction.body ().assignPlus (sComments, sComment.plus ('\n'));
      anonFunction.body ()._return (JSExpr.lit (""));
      aFuncMain.body ().assign (sHTML,
                                sHTML.invoke ("replace")
                                     .arg (JSExpr.regex ("<!--([\\s\\S]*?)-->").global (true))
                                     .arg (anonFunction));
      aFuncMain.body ().comment ("Remaining HTML + comments content");
      aFuncMain.body ()._return (new JSAssocArray ().add ("html", sHTML).add ("comments", sComments));

      aPkg.invoke (aFuncMain).arg ("<div>Test</div>");
    }

    // for-in-loop
    {
      final JSVar aI = new JSVar ("i");
      final JSLabel aLabel = aPkg.label ("loop");
      final JSForIn aFI = aPkg.forIn (aI, new JSArray ().add (1).add (2).add (4));
      final JSConditional aIf = aFI.body ()._if (aI.eq (2));
      aIf._then ()._break ();
      aIf._else ()._continue (aLabel);
    }

    // for-loop
    {
      final JSForLoop aFor = aPkg._for ();
      final JSVar aI = aFor.init ("i", 0);
      aFor.test (aI.lt (5));
      aFor.update (aI.incrPostfix ());
      aFor.body ()._continue ();

      aPkg._for ().simpleLoop ("i", 5, 0);
      aPkg._for ().simpleLoop ("i", 0, 5);
    }

    // do-loop
    {
      final JSRef aI = JSExpr.ref ("i");
      aPkg._do (aI.lt (1000)).body ().incrPostfix (aI);
    }

    // while-loop
    {
      final JSRef aI = JSExpr.ref ("i");
      aPkg._while (aI.gt (0)).body ().decrPostfix (aI);
    }

    return aPkg;
  }

  @Test
  public void testMinimumCodeSize () throws Exception
  {
    final JSPackage aPkg = _createMockPackage ();
    CommonsTestHelper.testDefaultImplementationWithEqualContentObject (aPkg, _createMockPackage ());

    final String sCode = aPkg.getJSCode (new JSWriterSettings ().setMinimumCodeSize (false));
    s_aLogger.info (sCode);
    s_aLogger.info ("--------");
    final String sCompressedCode = aPkg.getJSCode (new JSWriterSettings ().setMinimumCodeSize (true));
    assertEquals ("var g_aRoot=0;" +
                  "function mainAdd(m1){" +
                  "var root=5;" +
                  "function add(s1,s2){return (s1+s2);}" +
                  "add(32,-4);" +
                  "var add2=new Function('x','y','return x+y');" +
                  "add2(1,2);" +
                  "if(typeof m1==='String')" +
                  "{try{return 5;}catch (ex){throw new Error(ex);}finally{root.substring(0,1);}}" +
                  "/water(mark)?/gim.test('waterMark');" +
                  "/water(mark)?/i.test('Water');" +
                  "'string'.search(/expression/);" +
                  "'string'.replace(/expression/,'replacement');" +
                  "(function(a){return (a+0.5);})(7.5);" +
                  "var array1=[5];" +
                  "array1[0]=6;" +
                  "var array1a=new Array(5);" +
                  "array1a[0]=7;" +
                  "array1a.push('pushed');" +
                  "var array2={num:1,array:array1,assocarray:{key:'value',key2:'anything else'}};" +
                  "array2['num']=6;" +
                  "return (((((m1+'abc'.length+root+add(2,4)+7)*1.5)+5)-3)/2);}" +
                  "function sajax_extract_htmlcomments(sHTML){" +
                  "var sComments='';" +
                  "sHTML=sHTML.replace(/<!--([\\s\\S]*?)-->/g,function(all,sComment){sComments+=(sComment+'\\n');return '';});" +
                  "return {html:sHTML,comments:sComments};}" +
                  "sajax_extract_htmlcomments('<div>Test<\\/div>');" +
                  "loop:for(var i in [1,2,4]){" +
                  "if(i==2){break;}" +
                  "else{continue loop;}" +
                  "}" +
                  "for(var i=0;(i<5);i++){" +
                  "continue;" +
                  "}" +
                  "for(var i=5;(i>0);i--);" +
                  "for(var i=0;(i<5);i++);" +
                  "do{" +
                  "i++;" +
                  "}while(i<1000);" +
                  "while(i>0){" +
                  "i--;" +
                  "}",
                  sCompressedCode);
    s_aLogger.info ("Saved " +
                    (sCode.length () - sCompressedCode.length ()) +
                    " chars. " +
                    sCompressedCode.length () +
                    " chars are left");
    s_aLogger.info ("--------");
  }

  @Test
  public void testJQueryExtension ()
  {
    /**
     * <pre>
     * ( function($) {
     *   // Mark elements as enabled or disabled
     *   $.fn.setDisabled = function(bDisabled) {
     *     return this.each( function() {
     *       if (typeof this.disabled != "undefined")
     *         this.disabled = bDisabled;
     *     });
     *   };
     * })(jQuery);
     * </pre>
     */

    final JSPackage aPkg = new JSPackage ();
    final JSAnonymousFunction f = new JSAnonymousFunction ();
    final JSVar aDollar = f.param ("$");
    f.body ().comment ("Mark elements as enabled or disabled");
    {
      final JSAnonymousFunction fED = new JSAnonymousFunction ();
      final JSVar aDisabled = fED.param ("bDisabled");
      {
        final JSAnonymousFunction fEDEach = new JSAnonymousFunction ();
        fEDEach.body ()
               ._if (JSExpr.refThis ("disabled").isNotUndefined ())
               ._then ()
               .assign (JSExpr.refThis ("disabled"), aDisabled);
        fED.body ()._return (JSExpr.invokeThis ("each").arg (fEDEach));
      }
      f.body ().assign (JSExpr.ref (aDollar, "fn", "setDisabled"), fED);
    }
    aPkg.invoke (f).arg (JSExpr.direct ("jQuery"));
    s_aLogger.info (aPkg.getJSCode ());

    // Use an unparsed JS code inside a block
    assertEquals ("{a=b;}",
                  JSPrinter.getAsString (new JSWriterSettings ().setIndentAndAlign (false),
                                         (IJSGeneratable) new JSBlock ().add (new UnparsedJSCodeProvider ("a=b;"))));
  }

  @Test
  public void testEmpty ()
  {
    final JSPackage aPkg = new JSPackage ();
    assertEquals ("", aPkg.getJSCode ());
  }
}
