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
package com.helger.photon.uicore.html.google;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.helger.commons.ValueEnforcer;
import com.helger.commons.annotation.Nonempty;
import com.helger.commons.debug.GlobalDebug;
import com.helger.commons.string.StringHelper;
import com.helger.html.annotation.OutOfBandNode;
import com.helger.html.hc.html.script.AbstractHCScriptInline;
import com.helger.html.jscode.JSAnonymousFunction;
import com.helger.html.jscode.JSArray;
import com.helger.html.jscode.JSAssocArray;
import com.helger.html.jscode.JSExpr;
import com.helger.html.jscode.JSInvocation;
import com.helger.html.jscode.JSPackage;
import com.helger.html.jscode.JSVar;
import com.helger.html.jscode.html.JSHtml;
import com.helger.html.jscode.type.JSPrimitiveType;

/**
 * Control for emitting Google Universal Analytics code.<br>
 * <a href=
 * "https://developers.google.com/analytics/devguides/collection/analyticsjs/advanced"
 * >Source</a>
 *
 * @author Philip Helger
 */
@OutOfBandNode
public class HCUniversalAnalytics extends AbstractHCScriptInline <HCUniversalAnalytics>
{
  private final String m_sAccount;

  @Nonnull
  public static JSPackage createJSCode (@Nonnull @Nonempty final String sAccount,
                                        final boolean bForceSSL,
                                        final boolean bEnhancedLinkAttribution,
                                        final boolean bUseIPAnonymization,
                                        final boolean bUseDisplayAdvertising)
  {
    ValueEnforcer.notEmpty (sAccount, "Account");

    final JSPackage aPkg = new JSPackage ();
    final JSAnonymousFunction f = new JSAnonymousFunction ();
    {
      final JSVar jsI = f.param ("i");
      final JSVar jsS = f.param ("s");
      final JSVar jsO = f.param ("o");
      final JSVar jsG = f.param ("g");
      final JSVar jsR = f.param ("r");
      final JSVar jsA = f.param ("a");
      final JSVar jsM = f.param ("m");
      // Acts as a pointer to support renaming.
      f.body ().add (jsI.component ("GoogleAnalyticsObject").assign (jsR));
      {
        // Creates an initial ga() function. The queued commands will be
        // executed once analytics.js loads.
        final JSAnonymousFunction f2 = new JSAnonymousFunction ();
        f2.body ().add (jsI.component (jsR).ref ("q").assign (jsI.component (jsR).ref ("q").cor (new JSArray ())));
        f2.body ().add (jsI.component (jsR).ref ("q").invoke ("push").arg (JSExpr.ref ("arguments")));
        f.body ().add (jsI.component (jsR).assign (jsI.component (jsR).cor (f2)));
      }
      // Sets the time (as an integer) this tag was executed. Used for timing
      // hits.
      f.body ().add (jsI.component (jsR).ref ("l").assign (JSExpr.lit (1).mul (JSPrimitiveType.DATE._new ())));
      // Insert the script tag asynchronously. Inserts above current tag to
      // prevent blocking in addition to using the async attribute.
      f.body ().add (jsA.assign (jsS.invoke ("createElement").arg (jsO)));
      f.body ().add (jsM.assign (jsS.invoke ("getElementsByTagName").arg (jsO).component0 ()));
      f.body ().add (jsA.ref ("async").assign (1));
      f.body ().add (jsA.ref ("src").assign (jsG));
      f.body ().add (jsM.ref ("parentNode").invoke ("insertBefore").arg (jsA).arg (jsM));
    }
    final String sFunctionName = "ga";
    aPkg.add (f.invoke ()
               .arg (JSHtml.window ())
               .arg (JSHtml.document ())
               .arg ("script")
               .arg ("//www.google-analytics.com/analytics.js")
               .arg (sFunctionName));

    // Creates the tracker with default parameters.
    final JSAssocArray aCreateParams = new JSAssocArray ();
    if (GlobalDebug.isDebugMode ())
    {
      // In some cases you might want to test analytics.js from a webserver
      // running on localhost. To set analytics.js cookies, you need to disable
      // the default cookie domain using:
      aCreateParams.add ("cookieDomain", "none");
    }
    aPkg.invoke (sFunctionName).arg ("create").arg (sAccount).arg ("auto").arg (aCreateParams);

    if (bForceSSL)
    {
      // "By default, Google Analytics will match the protocol of the host page
      // when sending outbound requests. To force Google Analytics to always
      // send data using SSL, even from insecure pages (HTTP), set the forceSSL
      // field to true:
      aPkg.invoke (sFunctionName).arg ("set").arg ("forceSSL").arg (true);
    }

    if (bEnhancedLinkAttribution)
    {
      // Enhanced Link Attribution improves the accuracy of your In-Page
      // Analytics report by automatically differentiating between multiple
      // links to the same URL on a single page by using link element IDs.
      aPkg.invoke (sFunctionName).arg ("require").arg ("linkid");
    }

    if (bUseIPAnonymization)
    {
      // In some cases, you might need to anonymize the IP address of the hit
      // (http request) sent to Google Analytics. You can anonymize the IP
      // addresses for all the hits sent from a page (the lifetime of the
      // tracker object) by using the set command and setting the anonymizeIp
      // field to true:
      aPkg.invoke (sFunctionName).arg ("set").arg ("anonymizeIp").arg (true);
    }

    if (bUseDisplayAdvertising)
    {
      // When you enable Display features for a web property, then Google
      // Analytics collects the information it normally does, as well as the
      // DoubleClick cookie when that cookie is present.
      aPkg.invoke (sFunctionName).arg ("require").arg ("displayfeatures");
    }

    // Sends a pageview hit.
    aPkg.invoke (sFunctionName).arg ("send").arg ("pageview");
    return aPkg;
  }

  public HCUniversalAnalytics (@Nonnull @Nonempty final String sAccount,
                               final boolean bForceSSL,
                               final boolean bEnhancedLinkAttribution,
                               final boolean bUseIPAnonymization,
                               final boolean bUseDisplayAdvertising)
  {
    super (createJSCode (sAccount, bForceSSL, bEnhancedLinkAttribution, bUseIPAnonymization, bUseDisplayAdvertising));
    m_sAccount = sAccount;
  }

  @Nonnull
  @Nonempty
  public String getAccount ()
  {
    return m_sAccount;
  }

  @Nonnull
  public JSPackage getJSPackage ()
  {
    return (JSPackage) getJSCodeProvider ();
  }

  /**
   * Set this in the "onclick" events of links to track them
   *
   * @param sCategory
   *        The category. May not be <code>null</code>.
   * @param sAction
   *        The action. May not be <code>null</code>.
   * @param sLabel
   *        The label. May be <code>null</code>.
   * @param aValue
   *        Optional numeric value. May be <code>null</code>.
   * @return The JS code to be set to "onclick" event
   */
  @Nonnull
  public static JSInvocation createEventTrackingCode (@Nonnull final String sCategory,
                                                      @Nonnull final String sAction,
                                                      @Nullable final String sLabel,
                                                      @Nullable final Integer aValue)
  {
    final JSAssocArray aArray = new JSAssocArray ().add ("hitType", "event").add ("eventCategory", sCategory).add ("eventAction", sAction);
    if (StringHelper.hasText (sLabel))
      aArray.add ("eventLabel", sLabel);
    if (aValue != null)
      aArray.add ("eventValue", aValue.intValue ());

    return JSExpr.invoke ("ga").arg ("send").arg (aArray);
  }
}
