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
package com.helger.html.jscode.html;

import java.nio.charset.Charset;

import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.concurrent.Immutable;

import com.helger.commons.annotation.DevelopersNote;
import com.helger.commons.annotation.PresentForCodeCoverage;
import com.helger.commons.url.ISimpleURL;
import com.helger.html.EHTMLElement;
import com.helger.html.hc.IHCHasID;
import com.helger.html.jscode.IJSExpression;
import com.helger.html.jscode.JSAnonymousFunction;
import com.helger.html.jscode.JSAssignment;
import com.helger.html.jscode.JSExpr;
import com.helger.html.jscode.JSFieldRef;
import com.helger.html.jscode.JSInvocation;
import com.helger.html.jscode.JSRef;
import com.helger.html.jscode.JSVar;

/**
 * This class contains JS builder default constructs that are used very commonly
 *
 * @author Philip Helger
 */
@Immutable
public final class JSHtml
{
  @PresentForCodeCoverage
  private static final JSHtml s_aInstance = new JSHtml ();

  private JSHtml ()
  {}

  // --- console ---

  /**
   * @return Global console object
   * @see #windowConsole()
   */
  @Nonnull
  public static JSRef console ()
  {
    return JSExpr.ref ("console");
  }

  @Nonnull
  public static JSInvocation consoleAssert ()
  {
    return console ().invoke ("assert");
  }

  @Nonnull
  public static JSInvocation consoleClear ()
  {
    return console ().invoke ("clear");
  }

  @Nonnull
  public static JSInvocation consoleDebug ()
  {
    return console ().invoke ("debug");
  }

  @Nonnull
  public static JSInvocation consoleError ()
  {
    return console ().invoke ("error");
  }

  @Nonnull
  public static JSInvocation consoleInfo ()
  {
    return console ().invoke ("info");
  }

  @Nonnull
  public static JSInvocation consoleLog ()
  {
    return console ().invoke ("log");
  }

  @Nonnull
  public static JSInvocation consoleLog (@Nullable final String sText)
  {
    return consoleLog ().arg (sText);
  }

  @Nonnull
  public static JSInvocation consoleLog (@Nonnull final IJSExpression aExpr)
  {
    return consoleLog ().arg (aExpr);
  }

  @Nonnull
  public static JSInvocation consoleWarn ()
  {
    return console ().invoke ("warn");
  }

  // --- document ---

  /**
   * @return Global document object
   * @see #windowDocument()
   */
  @Nonnull
  public static JSRef document ()
  {
    return JSExpr.ref ("document");
  }

  @Nonnull
  public static JSFieldRef documentAnchors ()
  {
    return document ().ref ("anchors");
  }

  @Nonnull
  public static JSInvocation documentCreateAttribute ()
  {
    return document ().invoke ("createAttribute");
  }

  @Nonnull
  public static JSInvocation documentCreateElement ()
  {
    return document ().invoke ("createElement");
  }

  @Nonnull
  public static JSInvocation documentCreateElement (@Nonnull final EHTMLElement eHTMLElement)
  {
    return documentCreateElement ().arg (eHTMLElement);
  }

  @Nonnull
  public static JSInvocation documentCreateTextNode ()
  {
    return document ().invoke ("createTextNode");
  }

  @Nonnull
  public static JSFieldRef documentEmbeds ()
  {
    return document ().ref ("embeds");
  }

  @Nonnull
  public static JSFieldRef documentForms ()
  {
    return document ().ref ("forms");
  }

  @Nonnull
  public static JSInvocation documentGetElementById (@Nonnull final String sID)
  {
    return documentGetElementById (JSExpr.lit (sID));
  }

  @Nonnull
  public static JSInvocation documentGetElementById (@Nonnull final IHCHasID <?> aElement)
  {
    return documentGetElementById (aElement.ensureID ().getID ());
  }

  @Nonnull
  public static JSInvocation documentGetElementById (@Nonnull final IJSExpression aID)
  {
    return document ().invoke ("getElementById").arg (aID);
  }

  @Nonnull
  public static JSInvocation documentGetElementsByName (@Nonnull final EHTMLElement eHTMLElement)
  {
    return documentGetElementsByName (eHTMLElement.getElementName ());
  }

  @Nonnull
  public static JSInvocation documentGetElementsByName (@Nonnull final String sElementName)
  {
    return documentGetElementsByName (JSExpr.lit (sElementName));
  }

  @Nonnull
  public static JSInvocation documentGetElementsByName (@Nonnull final IJSExpression aElementName)
  {
    return document ().invoke ("getElementsByName").arg (aElementName);
  }

  @Nonnull
  public static JSInvocation documentGetElementsByTagName (@Nonnull final EHTMLElement eHTMLElement)
  {
    return documentGetElementsByTagName (eHTMLElement.getElementName ());
  }

  @Nonnull
  public static JSInvocation documentGetElementsByTagName (@Nonnull final String sElementName)
  {
    return documentGetElementsByTagName (JSExpr.lit (sElementName));
  }

  @Nonnull
  public static JSInvocation documentGetElementsByTagName (@Nonnull final IJSExpression aElementName)
  {
    return document ().invoke ("getElementsByTagName").arg (aElementName);
  }

  @Nonnull
  public static JSFieldRef documentImages ()
  {
    return document ().ref ("images");
  }

  @Nonnull
  public static JSFieldRef documentLinks ()
  {
    return document ().ref ("links");
  }

  @Nonnull
  public static JSInvocation documentWrite (@Nonnull final String sText)
  {
    return documentWrite (JSExpr.lit (sText));
  }

  @Nonnull
  public static JSInvocation documentWrite (@Nonnull final IJSExpression aText)
  {
    return document ().invoke ("write").arg (aText);
  }

  @Nonnull
  public static JSInvocation documentWriteln (@Nonnull final String sText)
  {
    return documentWriteln (JSExpr.lit (sText));
  }

  @Nonnull
  public static JSInvocation documentWriteln (@Nonnull final IJSExpression aText)
  {
    return document ().invoke ("writeln").arg (aText);
  }

  // --- event ---

  @Nonnull
  public static JSRef event ()
  {
    return JSExpr.ref ("event");
  }

  // --- history ---

  /**
   * @return The history object
   * @see #windowHistory()
   */
  @Nonnull
  public static JSRef history ()
  {
    return JSExpr.ref ("history");
  }

  @Nonnull
  public static JSInvocation historyBack ()
  {
    return history ().invoke ("back");
  }

  @Nonnull
  public static JSInvocation historyForward ()
  {
    return history ().invoke ("forward");
  }

  @Nonnull
  public static JSInvocation historyGo (final int nJumpCount)
  {
    return historyGo (JSExpr.lit (nJumpCount));
  }

  @Nonnull
  public static JSInvocation historyGo (@Nonnull final IJSExpression aJumpCount)
  {
    return history ().invoke ("go").arg (aJumpCount);
  }

  @Nonnull
  public static JSFieldRef historyLength ()
  {
    return history ().ref ("length");
  }

  // --- navigator ---

  /**
   * @return Global navigator object
   * @see #windowNavigator()
   */
  @Nonnull
  public static JSRef navigator ()
  {
    return JSExpr.ref ("navigator");
  }

  @Nonnull
  public static JSFieldRef navigatorAppCodeName ()
  {
    return navigator ().ref ("appCodeName");
  }

  @Nonnull
  public static JSFieldRef navigatorAppName ()
  {
    return navigator ().ref ("appName");
  }

  @Nonnull
  public static JSFieldRef navigatorAppVersion ()
  {
    return navigator ().ref ("appVersion");
  }

  @Nonnull
  public static JSFieldRef navigatorCookieEnabled ()
  {
    return navigator ().ref ("cookieEnabled");
  }

  @Nonnull
  public static JSFieldRef navigatorLanguage ()
  {
    return navigator ().ref ("language");
  }

  // is an array!
  @Nonnull
  public static JSFieldRef navigatorMimeTypes ()
  {
    return navigator ().ref ("mimeTypes");
  }

  @Nonnull
  public static JSFieldRef navigatorOnLine ()
  {
    return navigator ().ref ("onLine");
  }

  @Nonnull
  public static JSFieldRef navigatorPlatform ()
  {
    return navigator ().ref ("platform");
  }

  // is an array!
  @Nonnull
  public static JSFieldRef navigatorPlugins ()
  {
    return navigator ().ref ("plugins");
  }

  @Nonnull
  public static JSFieldRef navigatorUserAgent ()
  {
    return navigator ().ref ("userAgent");
  }

  @Nonnull
  public static JSInvocation navigatorJavaEnabled ()
  {
    return navigator ().invoke ("javaEnabled");
  }

  /*
   * IE and Opera only
   */
  @Nonnull
  public static JSInvocation navigatorTaintEnabled ()
  {
    return navigator ().invoke ("taintEnabled");
  }

  // --- screen ---

  /**
   * @return Global screen object
   * @see #windowScreen()
   */
  @Nonnull
  public static JSRef screen ()
  {
    return JSExpr.ref ("screen");
  }

  @Nonnull
  public static JSFieldRef screenAvailHeight ()
  {
    return screen ().ref ("availHeight");
  }

  @Nonnull
  public static JSFieldRef screenAvailWidth ()
  {
    return screen ().ref ("availWidth");
  }

  @Nonnull
  public static JSFieldRef screenColorDepth ()
  {
    return screen ().ref ("colorDepth");
  }

  @Nonnull
  public static JSFieldRef screenHeight ()
  {
    return screen ().ref ("height");
  }

  @Nonnull
  public static JSFieldRef screenPixelDepth ()
  {
    return screen ().ref ("pixelDepth");
  }

  @Nonnull
  public static JSFieldRef screenWidth ()
  {
    return screen ().ref ("width");
  }

  // --- window ---

  /**
   * @return <code>window</code>
   */
  @Nonnull
  public static JSRef window ()
  {
    return JSExpr.ref ("window");
  }

  /**
   * @return <code>window.alert</code>
   */
  @Nonnull
  public static JSInvocation windowAlert ()
  {
    return window ().invoke ("alert");
  }

  @Nonnull
  public static JSInvocation windowAlert (@Nullable final String sMessage)
  {
    return windowAlert ().arg (sMessage);
  }

  @Nonnull
  public static JSInvocation windowAlert (@Nonnull final IJSExpression aExpr)
  {
    return windowAlert ().arg (aExpr);
  }

  /**
   * @return <code>window.blur</code>
   */
  @Nonnull
  public static JSInvocation windowBlur ()
  {
    return window ().invoke ("blur");
  }

  /**
   * @return <code>window.clearInterval</code>
   */
  @Nonnull
  public static JSInvocation windowClearInterval ()
  {
    return window ().invoke ("clearInterval");
  }

  @Nonnull
  public static JSInvocation windowClearInterval (@Nonnull final JSVar aVar)
  {
    return windowClearInterval ().arg (aVar);
  }

  /**
   * @return <code>window.clearTimeout</code>
   */
  @Nonnull
  public static JSInvocation windowClearTimeout ()
  {
    return window ().invoke ("clearTimeout");
  }

  @Nonnull
  public static JSInvocation windowClearTimeout (@Nonnull final JSVar aVar)
  {
    return windowClearTimeout ().arg (aVar);
  }

  /**
   * @return <code>window.close</code>
   */
  @Nonnull
  public static JSInvocation windowClose ()
  {
    return window ().invoke ("close");
  }

  /**
   * @return <code>window.closed</code>
   */
  @Nonnull
  public static JSFieldRef windowClosed ()
  {
    return window ().ref ("closed");
  }

  /**
   * @return <code>window.confirm</code>
   */
  @Nonnull
  public static JSInvocation windowConfirm ()
  {
    return window ().invoke ("confirm");
  }

  /**
   * @return <code>window.console</code>
   */
  @Nonnull
  public static JSFieldRef windowConsole ()
  {
    return window ().ref ("console");
  }

  /**
   * @return <code>window.createPopup</code>
   */
  @Nonnull
  public static JSInvocation windowCreatePopup ()
  {
    return window ().invoke ("createPopup");
  }

  /**
   * @return <code>window.defaultStatus</code>
   */
  @Nonnull
  public static JSFieldRef windowDefaultStatus ()
  {
    return window ().ref ("defaultStatus");
  }

  /**
   * @return <code>window.document</code>
   */
  @Nonnull
  public static JSFieldRef windowDocument ()
  {
    return window ().ref ("document");
  }

  /**
   * @return <code>window.focus</code>
   */
  @Nonnull
  public static JSInvocation windowFocus ()
  {
    return window ().invoke ("focus");
  }

  /**
   * @return <code>window.frames</code>
   */
  @Nonnull
  public static JSFieldRef windowFrames ()
  {
    return window ().ref ("frames");
  }

  /**
   * @return <code>window.history</code>
   */
  @Nonnull
  public static JSFieldRef windowHistory ()
  {
    return window ().ref ("history");
  }

  /**
   * @return <code>window.innerHeight</code>
   */
  @Nonnull
  public static JSFieldRef windowInnerHeight ()
  {
    return window ().ref ("innerHeight");
  }

  /**
   * @return <code>window.innerWidth</code>
   */
  @Nonnull
  public static JSFieldRef windowInnerWidth ()
  {
    return window ().ref ("innerWidth");
  }

  /**
   * @return <code>window.length</code>
   */
  @Nonnull
  public static JSFieldRef windowLength ()
  {
    return window ().ref ("length");
  }

  /**
   * @return <code>window.localStorage</code>
   */
  @Nonnull
  public static JSFieldRef windowLocalStorage ()
  {
    return window ().ref ("localStorage");
  }

  /**
   * @return <code>window.location</code>
   */
  @Nonnull
  public static JSFieldRef windowLocation ()
  {
    return window ().ref ("location");
  }

  /**
   * @return <code>window.location.hash</code>
   */
  @Nonnull
  public static JSFieldRef windowLocationHash ()
  {
    return windowLocation ().ref ("hash");
  }

  /**
   * @return <code>window.location.host</code>
   */
  @Nonnull
  public static JSFieldRef windowLocationHost ()
  {
    return windowLocation ().ref ("host");
  }

  /**
   * @return <code>window.location.hostname</code>
   */
  @Nonnull
  public static JSFieldRef windowLocationHostname ()
  {
    return windowLocation ().ref ("hostname");
  }

  /**
   * @return <code>window.location.href</code>
   */
  @Nonnull
  public static JSFieldRef windowLocationHref ()
  {
    return windowLocation ().ref ("href");
  }

  @Nonnull
  @DevelopersNote ("Should be deprecated but is used too often")
  public static JSAssignment windowLocationHref (@Nonnull final ISimpleURL aURL)
  {
    // Use the version with the default charset - not nice :(
    return windowLocationHref ().assign (aURL.getAsStringWithEncodedParameters ());
  }

  @Nonnull
  public static JSAssignment windowLocationHref (@Nonnull final ISimpleURL aURL, @Nonnull final Charset aCharset)
  {
    return windowLocationHref ().assign (aURL.getAsStringWithEncodedParameters (aCharset));
  }

  /**
   * @return <code>window.location.pathname</code>
   */
  @Nonnull
  public static JSFieldRef windowLocationPathname ()
  {
    return windowLocation ().ref ("pathname");
  }

  /**
   * @return <code>window.location.port</code>
   */
  @Nonnull
  public static JSFieldRef windowLocationPort ()
  {
    return windowLocation ().ref ("port");
  }

  /**
   * @return <code>window.location.protocol</code>
   */
  @Nonnull
  public static JSFieldRef windowLocationProtocol ()
  {
    return windowLocation ().ref ("protocol");
  }

  /**
   * @return <code>window.location.reload</code>
   */
  @Nonnull
  public static JSInvocation windowLocationReload ()
  {
    return windowLocation ().invoke ("reload");
  }

  /**
   * @return <code>window.location.replace</code>
   */
  @Nonnull
  public static JSInvocation windowLocationReplace ()
  {
    return windowLocation ().invoke ("replace");
  }

  @Nonnull
  public static JSInvocation windowLocationReplace (@Nonnull final ISimpleURL aURL)
  {
    return windowLocationReplace (JSExpr.lit (aURL.getAsStringWithEncodedParameters ()));
  }

  @Nonnull
  public static JSInvocation windowLocationReplace (@Nonnull final IJSExpression aURL)
  {
    return windowLocationReplace ().arg (aURL);
  }

  /**
   * @return <code>window.location.search</code>
   */
  @Nonnull
  public static JSFieldRef windowLocationSearch ()
  {
    return windowLocation ().ref ("search");
  }

  /**
   * @return <code>window.moveBy</code>
   */
  @Nonnull
  public static JSInvocation windowMoveBy ()
  {
    return window ().invoke ("moveBy");
  }

  /**
   * @return <code>window.moveTo</code>
   */
  @Nonnull
  public static JSInvocation windowMoveTo ()
  {
    return window ().invoke ("moveTo");
  }

  /**
   * @return <code>window.name</code>
   */
  @Nonnull
  public static JSFieldRef windowName ()
  {
    return window ().ref ("name");
  }

  /**
   * @return <code>window.navigator</code>
   */
  @Nonnull
  public static JSFieldRef windowNavigator ()
  {
    return window ().ref ("navigator");
  }

  /**
   * @return <code>window.open</code>
   */
  @Nonnull
  public static JSInvocation windowOpen ()
  {
    return window ().invoke ("open");
  }

  /**
   * @return <code>window.opener</code>
   */
  @Nonnull
  public static JSFieldRef windowOpener ()
  {
    return window ().ref ("opener");
  }

  /**
   * @return <code>window.outerHeight</code>
   */
  @Nonnull
  public static JSFieldRef windowOuterHeight ()
  {
    return window ().ref ("outerHeight");
  }

  /**
   * @return <code>window.outerWidth</code>
   */
  @Nonnull
  public static JSFieldRef windowOuterWidth ()
  {
    return window ().ref ("outerWidth");
  }

  /**
   * @return <code>window.pageXOffset</code>
   */
  @Nonnull
  public static JSFieldRef windowPageXOffset ()
  {
    return window ().ref ("pageXOffset");
  }

  /**
   * @return <code>window.pageYOffset</code>
   */
  @Nonnull
  public static JSFieldRef windowPageYOffset ()
  {
    return window ().ref ("pageYOffset");
  }

  /**
   * @return <code>window.parent</code>
   */
  @Nonnull
  public static JSFieldRef windowParent ()
  {
    return window ().ref ("parent");
  }

  /**
   * @return <code>window.parent.frames</code>
   */
  @Nonnull
  public static JSFieldRef windowParentFrames ()
  {
    return windowParent ().ref ("frames");
  }

  /**
   * @return <code>window.print</code>
   */
  @Nonnull
  public static JSInvocation windowPrint ()
  {
    return window ().invoke ("print");
  }

  /**
   * @return <code>window.prompt</code>
   */
  @Nonnull
  public static JSInvocation windowPrompt ()
  {
    return window ().invoke ("prompt");
  }

  /**
   * @return <code>window.resizeBy</code>
   */
  @Nonnull
  public static JSInvocation windowResizeBy ()
  {
    return window ().invoke ("resizeBy");
  }

  /**
   * @return <code>window.resizeTo</code>
   */
  @Nonnull
  public static JSInvocation windowResizeTo ()
  {
    return window ().invoke ("resizeTo");
  }

  /**
   * @return <code>window.screen</code>
   */
  @Nonnull
  public static JSFieldRef windowScreen ()
  {
    return window ().ref ("screen");
  }

  /**
   * @return <code>window.screenLeft</code>
   */
  @Nonnull
  public static JSFieldRef windowScreenLeft ()
  {
    return window ().ref ("screenLeft");
  }

  /**
   * @return <code>window.screenTop</code>
   */
  @Nonnull
  public static JSFieldRef windowScreenTop ()
  {
    return window ().ref ("screenTop");
  }

  /**
   * @return <code>window.screenX</code>
   */
  @Nonnull
  public static JSFieldRef windowScreenX ()
  {
    return window ().ref ("screenX");
  }

  /**
   * @return <code>window.screenY</code>
   */
  @Nonnull
  public static JSFieldRef windowScreenY ()
  {
    return window ().ref ("screenY");
  }

  /**
   * @return <code>window.scroll</code>
   */
  @Nonnull
  public static JSInvocation windowScroll ()
  {
    return window ().invoke ("scroll");
  }

  /**
   * @return <code>window.scrollBy</code>
   */
  @Nonnull
  public static JSInvocation windowScrollBy ()
  {
    return window ().invoke ("scrollBy");
  }

  /**
   * @return <code>window.scrollTo</code>
   */
  @Nonnull
  public static JSInvocation windowScrollTo ()
  {
    return window ().invoke ("scrollTo");
  }

  /**
   * @return <code>window.self</code>
   */
  @Nonnull
  public static JSFieldRef windowSelf ()
  {
    return window ().ref ("self");
  }

  /**
   * @return <code>window.setInterval</code>
   */
  @Nonnull
  public static JSInvocation windowSetInterval ()
  {
    return window ().invoke ("setInterval");
  }

  @Nonnull
  public static JSInvocation windowSetInterval (@Nonnull final JSAnonymousFunction aCallback,
                                                @Nonnegative final int nMillis)
  {
    return windowSetInterval ().arg (aCallback).arg (nMillis);
  }

  /**
   * @return <code>window.setTimeout</code>
   */
  @Nonnull
  public static JSInvocation windowSetTimeout ()
  {
    return window ().invoke ("setTimeout");
  }

  @Nonnull
  public static JSInvocation windowSetTimeout (@Nonnull final JSAnonymousFunction aCallback,
                                               @Nonnegative final int nMillis)
  {
    return windowSetTimeout ().arg (aCallback).arg (nMillis);
  }

  /**
   * @return <code>window.status</code>
   */
  @Nonnull
  public static JSFieldRef windowStatus ()
  {
    return window ().ref ("status");
  }

  /**
   * @return <code>window.top</code>
   */
  @Nonnull
  public static JSFieldRef windowTop ()
  {
    return window ().ref ("top");
  }

  /**
   * @return <code>window.onbeforeunload</code>
   */
  @Nonnull
  public static JSFieldRef windowOnbeforeunload ()
  {
    return window ().ref ("onbeforeunload");
  }

  /**
   * window.onbeforeunload
   *
   * @param aCallback
   *        Callback function with one parameter (the event) and return type
   *        string to display a message or null to display none.
   * @return The JS assignment
   */
  @Nonnull
  public static JSAssignment windowOnbeforeunload (@Nonnull final JSAnonymousFunction aCallback)
  {
    return windowOnbeforeunload ().assign (aCallback);
  }

  // -- others ---

  /**
   * @param aExpr
   *        Source expression
   * @return <code>aExpr.options[aExpr.selectedIndex].value</code>
   */
  @Nonnull
  public static JSFieldRef getSelectSelectedValue (@Nonnull final IJSExpression aExpr)
  {
    return aExpr.ref ("options").component (aExpr.ref ("selectedIndex")).ref ("value");
  }

  /**
   * @return <code>this.options[this.selectedIndex].value</code>
   */
  @Nonnull
  public static JSFieldRef getSelectSelectedValue ()
  {
    return getSelectSelectedValue (JSExpr.THIS);
  }
}
