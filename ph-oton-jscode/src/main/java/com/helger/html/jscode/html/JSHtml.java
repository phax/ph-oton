/*
 * Copyright (C) 2014-2026 Philip Helger (www.helger.com)
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
package com.helger.html.jscode.html;

import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.Nullable;

import com.helger.annotation.Nonnegative;
import com.helger.annotation.concurrent.Immutable;
import com.helger.annotation.misc.DevelopersNote;
import com.helger.annotation.style.PresentForCodeCoverage;
import com.helger.html.EHTMLElement;
import com.helger.html.hc.IHCHasID;
import com.helger.html.jscode.AbstractJSVariable;
import com.helger.html.jscode.IJSExpression;
import com.helger.html.jscode.JSAnonymousFunction;
import com.helger.html.jscode.JSAssignment;
import com.helger.html.jscode.JSExpr;
import com.helger.html.jscode.JSFieldRef;
import com.helger.html.jscode.JSInvocation;
import com.helger.html.jscode.JSRef;
import com.helger.url.ISimpleURL;

/**
 * This class contains JS builder default constructs that are used very commonly
 *
 * @author Philip Helger
 */
@Immutable
public final class JSHtml
{
  @PresentForCodeCoverage
  private static final JSHtml INSTANCE = new JSHtml ();

  private JSHtml ()
  {}

  // --- console ---

  /**
   * @return Global console object
   * @see #windowConsole()
   */
  @NonNull
  public static JSRef console ()
  {
    return JSExpr.ref ("console");
  }

  @NonNull
  public static JSInvocation consoleAssert ()
  {
    return console ().invoke ("assert");
  }

  @NonNull
  public static JSInvocation consoleClear ()
  {
    return console ().invoke ("clear");
  }

  @NonNull
  public static JSInvocation consoleDebug ()
  {
    return console ().invoke ("debug");
  }

  @NonNull
  public static JSInvocation consoleError ()
  {
    return console ().invoke ("error");
  }

  @NonNull
  public static JSInvocation consoleInfo ()
  {
    return console ().invoke ("info");
  }

  @NonNull
  public static JSInvocation consoleLog ()
  {
    return console ().invoke ("log");
  }

  @NonNull
  public static JSInvocation consoleLog (@Nullable final String sText)
  {
    return consoleLog ().arg (sText);
  }

  @NonNull
  public static JSInvocation consoleLog (@NonNull final IJSExpression aExpr)
  {
    return consoleLog ().arg (aExpr);
  }

  @NonNull
  public static JSInvocation consoleWarn ()
  {
    return console ().invoke ("warn");
  }

  // --- document ---

  /**
   * @return Global document object
   * @see #windowDocument()
   */
  @NonNull
  public static JSRef document ()
  {
    return JSExpr.ref ("document");
  }

  @NonNull
  public static JSFieldRef documentAnchors ()
  {
    return document ().ref ("anchors");
  }

  @NonNull
  public static JSInvocation documentAddEventListener ()
  {
    return document ().invoke ("addEventListener");
  }

  @NonNull
  public static JSInvocation documentCreateAttribute ()
  {
    return document ().invoke ("createAttribute");
  }

  @NonNull
  public static JSInvocation documentCreateElement ()
  {
    return document ().invoke ("createElement");
  }

  @NonNull
  public static JSInvocation documentCreateElement (@NonNull final EHTMLElement eHTMLElement)
  {
    return documentCreateElement ().arg (eHTMLElement);
  }

  @NonNull
  public static JSInvocation documentCreateTextNode ()
  {
    return document ().invoke ("createTextNode");
  }

  @NonNull
  public static JSFieldRef documentEmbeds ()
  {
    return document ().ref ("embeds");
  }

  @NonNull
  public static JSFieldRef documentForms ()
  {
    return document ().ref ("forms");
  }

  @NonNull
  public static JSInvocation documentGetElementById (@NonNull final String sID)
  {
    return documentGetElementById (JSExpr.lit (sID));
  }

  @NonNull
  public static JSInvocation documentGetElementById (@NonNull final IHCHasID <?> aElement)
  {
    return documentGetElementById (aElement.ensureID ().getID ());
  }

  @NonNull
  public static JSInvocation documentGetElementById (@NonNull final IJSExpression aID)
  {
    return document ().invoke ("getElementById").arg (aID);
  }

  @NonNull
  public static JSInvocation documentGetElementsByName (@NonNull final EHTMLElement eHTMLElement)
  {
    return documentGetElementsByName (eHTMLElement.getElementName ());
  }

  @NonNull
  public static JSInvocation documentGetElementsByName (@NonNull final String sElementName)
  {
    return documentGetElementsByName (JSExpr.lit (sElementName));
  }

  @NonNull
  public static JSInvocation documentGetElementsByName (@NonNull final IJSExpression aElementName)
  {
    return document ().invoke ("getElementsByName").arg (aElementName);
  }

  @NonNull
  public static JSInvocation documentGetElementsByTagName (@NonNull final EHTMLElement eHTMLElement)
  {
    return documentGetElementsByTagName (eHTMLElement.getElementName ());
  }

  @NonNull
  public static JSInvocation documentGetElementsByTagName (@NonNull final String sElementName)
  {
    return documentGetElementsByTagName (JSExpr.lit (sElementName));
  }

  @NonNull
  public static JSInvocation documentGetElementsByTagName (@NonNull final IJSExpression aElementName)
  {
    return document ().invoke ("getElementsByTagName").arg (aElementName);
  }

  @NonNull
  public static JSFieldRef documentImages ()
  {
    return document ().ref ("images");
  }

  @NonNull
  public static JSFieldRef documentLinks ()
  {
    return document ().ref ("links");
  }

  @NonNull
  public static JSInvocation documentWrite (@NonNull final String sText)
  {
    return documentWrite (JSExpr.lit (sText));
  }

  @NonNull
  public static JSInvocation documentWrite (@NonNull final IJSExpression aText)
  {
    return document ().invoke ("write").arg (aText);
  }

  @NonNull
  public static JSInvocation documentWriteln (@NonNull final String sText)
  {
    return documentWriteln (JSExpr.lit (sText));
  }

  @NonNull
  public static JSInvocation documentWriteln (@NonNull final IJSExpression aText)
  {
    return document ().invoke ("writeln").arg (aText);
  }

  // --- event ---

  @NonNull
  public static JSRef event ()
  {
    return JSExpr.ref ("event");
  }

  // --- history ---

  /**
   * @return The history object
   * @see #windowHistory()
   */
  @NonNull
  public static JSRef history ()
  {
    return JSExpr.ref ("history");
  }

  @NonNull
  public static JSInvocation historyBack ()
  {
    return history ().invoke ("back");
  }

  @NonNull
  public static JSInvocation historyForward ()
  {
    return history ().invoke ("forward");
  }

  @NonNull
  public static JSInvocation historyGo (final int nJumpCount)
  {
    return historyGo (JSExpr.lit (nJumpCount));
  }

  @NonNull
  public static JSInvocation historyGo (@NonNull final IJSExpression aJumpCount)
  {
    return history ().invoke ("go").arg (aJumpCount);
  }

  @NonNull
  public static JSFieldRef historyLength ()
  {
    return history ().ref ("length");
  }

  // --- navigator ---

  /**
   * @return Global navigator object
   * @see #windowNavigator()
   */
  @NonNull
  public static JSRef navigator ()
  {
    return JSExpr.ref ("navigator");
  }

  @NonNull
  public static JSFieldRef navigatorAppCodeName ()
  {
    return navigator ().ref ("appCodeName");
  }

  @NonNull
  public static JSFieldRef navigatorAppName ()
  {
    return navigator ().ref ("appName");
  }

  @NonNull
  public static JSFieldRef navigatorAppVersion ()
  {
    return navigator ().ref ("appVersion");
  }

  @NonNull
  public static JSFieldRef navigatorCookieEnabled ()
  {
    return navigator ().ref ("cookieEnabled");
  }

  @NonNull
  public static JSFieldRef navigatorLanguage ()
  {
    return navigator ().ref ("language");
  }

  // is an array!
  @NonNull
  public static JSFieldRef navigatorMimeTypes ()
  {
    return navigator ().ref ("mimeTypes");
  }

  @NonNull
  public static JSFieldRef navigatorOnLine ()
  {
    return navigator ().ref ("onLine");
  }

  @NonNull
  public static JSFieldRef navigatorPlatform ()
  {
    return navigator ().ref ("platform");
  }

  // is an array!
  @NonNull
  public static JSFieldRef navigatorPlugins ()
  {
    return navigator ().ref ("plugins");
  }

  @NonNull
  public static JSFieldRef navigatorUserAgent ()
  {
    return navigator ().ref ("userAgent");
  }

  @NonNull
  public static JSInvocation navigatorJavaEnabled ()
  {
    return navigator ().invoke ("javaEnabled");
  }

  /*
   * IE and Opera only
   */
  @NonNull
  public static JSInvocation navigatorTaintEnabled ()
  {
    return navigator ().invoke ("taintEnabled");
  }

  // --- screen ---

  /**
   * @return Global screen object
   * @see #windowScreen()
   */
  @NonNull
  public static JSRef screen ()
  {
    return JSExpr.ref ("screen");
  }

  @NonNull
  public static JSFieldRef screenAvailHeight ()
  {
    return screen ().ref ("availHeight");
  }

  @NonNull
  public static JSFieldRef screenAvailWidth ()
  {
    return screen ().ref ("availWidth");
  }

  @NonNull
  public static JSFieldRef screenColorDepth ()
  {
    return screen ().ref ("colorDepth");
  }

  @NonNull
  public static JSFieldRef screenHeight ()
  {
    return screen ().ref ("height");
  }

  @NonNull
  public static JSFieldRef screenPixelDepth ()
  {
    return screen ().ref ("pixelDepth");
  }

  @NonNull
  public static JSFieldRef screenWidth ()
  {
    return screen ().ref ("width");
  }

  // --- window ---

  /**
   * @return <code>window</code>
   */
  @NonNull
  public static JSRef window ()
  {
    return JSExpr.ref ("window");
  }

  /**
   * @return <code>window.alert</code>
   */
  @NonNull
  public static JSInvocation windowAlert ()
  {
    return window ().invoke ("alert");
  }

  @NonNull
  public static JSInvocation windowAlert (@Nullable final String sMessage)
  {
    return windowAlert ().arg (sMessage);
  }

  @NonNull
  public static JSInvocation windowAlert (@NonNull final IJSExpression aExpr)
  {
    return windowAlert ().arg (aExpr);
  }

  /**
   * @return <code>window.blur</code>
   */
  @NonNull
  public static JSInvocation windowBlur ()
  {
    return window ().invoke ("blur");
  }

  /**
   * @return <code>window.clearInterval</code>
   */
  @NonNull
  public static JSInvocation windowClearInterval ()
  {
    return window ().invoke ("clearInterval");
  }

  @NonNull
  public static JSInvocation windowClearInterval (@NonNull final AbstractJSVariable <?> aVar)
  {
    return windowClearInterval ().arg (aVar);
  }

  /**
   * @return <code>window.clearTimeout</code>
   */
  @NonNull
  public static JSInvocation windowClearTimeout ()
  {
    return window ().invoke ("clearTimeout");
  }

  @NonNull
  public static JSInvocation windowClearTimeout (@NonNull final AbstractJSVariable <?> aVar)
  {
    return windowClearTimeout ().arg (aVar);
  }

  /**
   * @return <code>window.close</code>
   */
  @NonNull
  public static JSInvocation windowClose ()
  {
    return window ().invoke ("close");
  }

  /**
   * @return <code>window.closed</code>
   */
  @NonNull
  public static JSFieldRef windowClosed ()
  {
    return window ().ref ("closed");
  }

  /**
   * @return <code>window.confirm</code>
   */
  @NonNull
  public static JSInvocation windowConfirm ()
  {
    return window ().invoke ("confirm");
  }

  /**
   * @return <code>window.console</code>
   */
  @NonNull
  public static JSFieldRef windowConsole ()
  {
    return window ().ref ("console");
  }

  /**
   * @return <code>window.createPopup</code>
   */
  @NonNull
  public static JSInvocation windowCreatePopup ()
  {
    return window ().invoke ("createPopup");
  }

  /**
   * @return <code>window.defaultStatus</code>
   */
  @NonNull
  public static JSFieldRef windowDefaultStatus ()
  {
    return window ().ref ("defaultStatus");
  }

  /**
   * @return <code>window.document</code>
   */
  @NonNull
  public static JSFieldRef windowDocument ()
  {
    return window ().ref ("document");
  }

  /**
   * @return <code>window.focus</code>
   */
  @NonNull
  public static JSInvocation windowFocus ()
  {
    return window ().invoke ("focus");
  }

  /**
   * @return <code>window.frames</code>
   */
  @NonNull
  public static JSFieldRef windowFrames ()
  {
    return window ().ref ("frames");
  }

  /**
   * @return <code>window.history</code>
   */
  @NonNull
  public static JSFieldRef windowHistory ()
  {
    return window ().ref ("history");
  }

  /**
   * @return <code>window.innerHeight</code>
   */
  @NonNull
  public static JSFieldRef windowInnerHeight ()
  {
    return window ().ref ("innerHeight");
  }

  /**
   * @return <code>window.innerWidth</code>
   */
  @NonNull
  public static JSFieldRef windowInnerWidth ()
  {
    return window ().ref ("innerWidth");
  }

  /**
   * @return <code>window.length</code>
   */
  @NonNull
  public static JSFieldRef windowLength ()
  {
    return window ().ref ("length");
  }

  /**
   * @return <code>window.localStorage</code>
   */
  @NonNull
  public static JSFieldRef windowLocalStorage ()
  {
    return window ().ref ("localStorage");
  }

  /**
   * @return <code>window.location</code>
   */
  @NonNull
  public static JSFieldRef windowLocation ()
  {
    return window ().ref ("location");
  }

  /**
   * @return <code>window.location.hash</code>
   */
  @NonNull
  public static JSFieldRef windowLocationHash ()
  {
    return windowLocation ().ref ("hash");
  }

  /**
   * @return <code>window.location.host</code>
   */
  @NonNull
  public static JSFieldRef windowLocationHost ()
  {
    return windowLocation ().ref ("host");
  }

  /**
   * @return <code>window.location.hostname</code>
   */
  @NonNull
  public static JSFieldRef windowLocationHostname ()
  {
    return windowLocation ().ref ("hostname");
  }

  /**
   * @return <code>window.location.href</code>
   */
  @NonNull
  public static JSFieldRef windowLocationHref ()
  {
    return windowLocation ().ref ("href");
  }

  @NonNull
  @DevelopersNote ("Should be deprecated but is used too often")
  public static JSAssignment windowLocationHref (@NonNull final ISimpleURL aURL)
  {
    // Use the version with the default charset - not nice :(
    return windowLocationHref ().assign (aURL.getAsString ());
  }

  /**
   * @return <code>window.location.pathname</code>
   */
  @NonNull
  public static JSFieldRef windowLocationPathname ()
  {
    return windowLocation ().ref ("pathname");
  }

  /**
   * @return <code>window.location.port</code>
   */
  @NonNull
  public static JSFieldRef windowLocationPort ()
  {
    return windowLocation ().ref ("port");
  }

  /**
   * @return <code>window.location.protocol</code>
   */
  @NonNull
  public static JSFieldRef windowLocationProtocol ()
  {
    return windowLocation ().ref ("protocol");
  }

  /**
   * @return <code>window.location.reload</code>
   */
  @NonNull
  public static JSInvocation windowLocationReload ()
  {
    return windowLocation ().invoke ("reload");
  }

  /**
   * @return <code>window.location.replace</code>
   */
  @NonNull
  public static JSInvocation windowLocationReplace ()
  {
    return windowLocation ().invoke ("replace");
  }

  @NonNull
  public static JSInvocation windowLocationReplace (@NonNull final ISimpleURL aURL)
  {
    return windowLocationReplace (JSExpr.lit (aURL.getAsString ()));
  }

  @NonNull
  public static JSInvocation windowLocationReplace (@NonNull final IJSExpression aURL)
  {
    return windowLocationReplace ().arg (aURL);
  }

  /**
   * @return <code>window.location.search</code>
   */
  @NonNull
  public static JSFieldRef windowLocationSearch ()
  {
    return windowLocation ().ref ("search");
  }

  /**
   * @return <code>window.moveBy</code>
   */
  @NonNull
  public static JSInvocation windowMoveBy ()
  {
    return window ().invoke ("moveBy");
  }

  /**
   * @return <code>window.moveTo</code>
   */
  @NonNull
  public static JSInvocation windowMoveTo ()
  {
    return window ().invoke ("moveTo");
  }

  /**
   * @return <code>window.name</code>
   */
  @NonNull
  public static JSFieldRef windowName ()
  {
    return window ().ref ("name");
  }

  /**
   * @return <code>window.navigator</code>
   */
  @NonNull
  public static JSFieldRef windowNavigator ()
  {
    return window ().ref ("navigator");
  }

  /**
   * @return <code>window.open</code>
   */
  @NonNull
  public static JSInvocation windowOpen ()
  {
    return window ().invoke ("open");
  }

  /**
   * @return <code>window.opener</code>
   */
  @NonNull
  public static JSFieldRef windowOpener ()
  {
    return window ().ref ("opener");
  }

  /**
   * @return <code>window.outerHeight</code>
   */
  @NonNull
  public static JSFieldRef windowOuterHeight ()
  {
    return window ().ref ("outerHeight");
  }

  /**
   * @return <code>window.outerWidth</code>
   */
  @NonNull
  public static JSFieldRef windowOuterWidth ()
  {
    return window ().ref ("outerWidth");
  }

  /**
   * @return <code>window.pageXOffset</code>
   */
  @NonNull
  public static JSFieldRef windowPageXOffset ()
  {
    return window ().ref ("pageXOffset");
  }

  /**
   * @return <code>window.pageYOffset</code>
   */
  @NonNull
  public static JSFieldRef windowPageYOffset ()
  {
    return window ().ref ("pageYOffset");
  }

  /**
   * @return <code>window.parent</code>
   */
  @NonNull
  public static JSFieldRef windowParent ()
  {
    return window ().ref ("parent");
  }

  /**
   * @return <code>window.parent.frames</code>
   */
  @NonNull
  public static JSFieldRef windowParentFrames ()
  {
    return windowParent ().ref ("frames");
  }

  /**
   * @return <code>window.print</code>
   */
  @NonNull
  public static JSInvocation windowPrint ()
  {
    return window ().invoke ("print");
  }

  /**
   * @return <code>window.prompt</code>
   */
  @NonNull
  public static JSInvocation windowPrompt ()
  {
    return window ().invoke ("prompt");
  }

  /**
   * @return <code>window.resizeBy</code>
   */
  @NonNull
  public static JSInvocation windowResizeBy ()
  {
    return window ().invoke ("resizeBy");
  }

  /**
   * @return <code>window.resizeTo</code>
   */
  @NonNull
  public static JSInvocation windowResizeTo ()
  {
    return window ().invoke ("resizeTo");
  }

  /**
   * @return <code>window.screen</code>
   */
  @NonNull
  public static JSFieldRef windowScreen ()
  {
    return window ().ref ("screen");
  }

  /**
   * @return <code>window.screenLeft</code>
   */
  @NonNull
  public static JSFieldRef windowScreenLeft ()
  {
    return window ().ref ("screenLeft");
  }

  /**
   * @return <code>window.screenTop</code>
   */
  @NonNull
  public static JSFieldRef windowScreenTop ()
  {
    return window ().ref ("screenTop");
  }

  /**
   * @return <code>window.screenX</code>
   */
  @NonNull
  public static JSFieldRef windowScreenX ()
  {
    return window ().ref ("screenX");
  }

  /**
   * @return <code>window.screenY</code>
   */
  @NonNull
  public static JSFieldRef windowScreenY ()
  {
    return window ().ref ("screenY");
  }

  /**
   * @return <code>window.scroll</code>
   */
  @NonNull
  public static JSInvocation windowScroll ()
  {
    return window ().invoke ("scroll");
  }

  /**
   * @return <code>window.scrollBy</code>
   */
  @NonNull
  public static JSInvocation windowScrollBy ()
  {
    return window ().invoke ("scrollBy");
  }

  /**
   * @return <code>window.scrollTo</code>
   */
  @NonNull
  public static JSInvocation windowScrollTo ()
  {
    return window ().invoke ("scrollTo");
  }

  /**
   * @return <code>window.self</code>
   */
  @NonNull
  public static JSFieldRef windowSelf ()
  {
    return window ().ref ("self");
  }

  /**
   * @return <code>window.setInterval</code>
   */
  @NonNull
  public static JSInvocation windowSetInterval ()
  {
    return window ().invoke ("setInterval");
  }

  @NonNull
  public static JSInvocation windowSetInterval (@NonNull final JSAnonymousFunction aCallback,
                                                @Nonnegative final int nMillis)
  {
    return windowSetInterval ().arg (aCallback).arg (nMillis);
  }

  /**
   * @return <code>window.setTimeout</code>
   */
  @NonNull
  public static JSInvocation windowSetTimeout ()
  {
    return window ().invoke ("setTimeout");
  }

  @NonNull
  public static JSInvocation windowSetTimeout (@NonNull final JSAnonymousFunction aCallback,
                                               @Nonnegative final int nMillis)
  {
    return windowSetTimeout ().arg (aCallback).arg (nMillis);
  }

  /**
   * @return <code>window.status</code>
   */
  @NonNull
  public static JSFieldRef windowStatus ()
  {
    return window ().ref ("status");
  }

  /**
   * @return <code>window.top</code>
   */
  @NonNull
  public static JSFieldRef windowTop ()
  {
    return window ().ref ("top");
  }

  /**
   * @return <code>window.onbeforeunload</code>
   */
  @NonNull
  public static JSFieldRef windowOnbeforeunload ()
  {
    return window ().ref ("onbeforeunload");
  }

  /**
   * window.onbeforeunload
   *
   * @param aCallback
   *        Callback function with one parameter (the event) and return type string to display a
   *        message or null to display none.
   * @return The JS assignment
   */
  @NonNull
  public static JSAssignment windowOnbeforeunload (@NonNull final JSAnonymousFunction aCallback)
  {
    return windowOnbeforeunload ().assign (aCallback);
  }

  // -- others ---

  /**
   * @param aExpr
   *        Source expression
   * @return <code>aExpr.options[aExpr.selectedIndex].value</code>
   */
  @NonNull
  public static JSFieldRef getSelectSelectedValue (@NonNull final IJSExpression aExpr)
  {
    return aExpr.ref ("options").component (aExpr.ref ("selectedIndex")).ref ("value");
  }

  /**
   * @return <code>this.options[this.selectedIndex].value</code>
   */
  @NonNull
  public static JSFieldRef getSelectSelectedValue ()
  {
    return getSelectSelectedValue (JSExpr.THIS);
  }
}
