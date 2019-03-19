/**
 * Copyright (C) 2014-2019 Philip Helger (www.helger.com)
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
package com.helger.photon.uicore.html.swf;

import java.util.Map;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.helger.commons.collection.impl.CommonsLinkedHashMap;
import com.helger.commons.collection.impl.ICommonsOrderedMap;
import com.helger.commons.url.ISimpleURL;
import com.helger.css.ECSSUnit;
import com.helger.html.hc.IHCConversionSettingsToNode;
import com.helger.html.hc.IHCHasChildrenMutable;
import com.helger.html.hc.IHCNode;
import com.helger.html.hc.html.grouping.AbstractHCDiv;
import com.helger.html.hc.html.script.HCScriptInline;
import com.helger.html.js.JSMarshaller;
import com.helger.html.js.tostring.JSToString;
import com.helger.html.jscode.JSAssocArray;
import com.helger.html.jscode.JSExpr;
import com.helger.html.jscode.JSInvocation;
import com.helger.photon.app.html.PhotonJS;
import com.helger.photon.uicore.EUICoreJSPathProvider;

/**
 * Create the necessary tags for embedding a flash files using SWFObject.<br>
 * See <a href="http://code.google.com/p/swfobject/">SWFObject web site</a>
 *
 * @author Philip Helger
 */
public class HCSWFObject extends AbstractHCDiv <HCSWFObject>
{
  // Required:
  private ISimpleURL m_aSWFURL;
  private String m_sWidth;
  private String m_sHeight;
  private String m_sRequiredSWFVersion;
  // Optional
  private ISimpleURL m_aExpressInstallSWFURL;
  private ICommonsOrderedMap <String, Object> m_aFlashVars;
  private ICommonsOrderedMap <String, String> m_aObjectParams;
  private ICommonsOrderedMap <String, String> m_aObjectAttrs;

  public HCSWFObject ()
  {
    ensureID ();
  }

  @Nonnull
  public final HCSWFObject setSWFURL (final ISimpleURL aSWFURL)
  {
    m_aSWFURL = aSWFURL;
    return this;
  }

  /**
   * Set the width in pixel.
   *
   * @param nWidth
   *        The widths in pixel
   * @return this
   */
  @Nonnull
  public final HCSWFObject setWidth (final int nWidth)
  {
    if (nWidth > 0)
      m_sWidth = Integer.toString (nWidth);
    return this;
  }

  /**
   * Set the width in percent relative to the parent object
   *
   * @param nWidth
   *        The width in percent. Should be between 0 and 100
   * @return this
   */
  @Nonnull
  public final HCSWFObject setWidthPerc (final int nWidth)
  {
    m_sWidth = ECSSUnit.perc (nWidth);
    return this;
  }

  /**
   * Set the height in pixel.
   *
   * @param nHeight
   *        The height in pixel
   * @return this
   */
  @Nonnull
  public final HCSWFObject setHeight (final int nHeight)
  {
    if (nHeight > 0)
      m_sHeight = Integer.toString (nHeight);
    return this;
  }

  /**
   * Set the height in percent relative to the parent object
   *
   * @param nHeight
   *        The height in percent. Should be between 0 and 100
   * @return this
   */
  @Nonnull
  public final HCSWFObject setHeightPerc (final int nHeight)
  {
    m_sHeight = ECSSUnit.perc (nHeight);
    return this;
  }

  /**
   * Set the required flash player version
   *
   * @param sRequiredSWFVersion
   *        Text version string like "10.0.0". No check on the formatting is
   *        done.
   * @return this
   */
  @Nonnull
  public final HCSWFObject setRequiredSWFVersion (@Nullable final String sRequiredSWFVersion)
  {
    m_sRequiredSWFVersion = sRequiredSWFVersion;
    return this;
  }

  /**
   * Set an optional URL where the express installation SWF file is located.
   *
   * @param aExpressInstallSWFURL
   *        The URL to the object. May be <code>null</code>.
   * @return this
   */
  @Nonnull
  public final HCSWFObject setExpressInstallSWFURL (@Nullable final ISimpleURL aExpressInstallSWFURL)
  {
    m_aExpressInstallSWFURL = aExpressInstallSWFURL;
    return this;
  }

  /**
   * Add a parameter to be passed to the Flash object
   *
   * @param sName
   *        Parameter name
   * @param aValue
   *        Parameter value
   * @return this
   */
  @Nonnull
  public final HCSWFObject addFlashVar (@Nonnull final String sName, final Object aValue)
  {
    if (!JSMarshaller.isJSIdentifier (sName))
      throw new IllegalArgumentException ("The name '" + sName + "' is not a legal JS identifier!");

    if (m_aFlashVars == null)
      m_aFlashVars = new CommonsLinkedHashMap <> ();
    m_aFlashVars.put (sName, aValue);
    return this;
  }

  /**
   * Remove a flash variable
   *
   * @param sName
   *        The name of the flash variable to be removed
   * @return this
   */
  @Nonnull
  public final HCSWFObject removeFlashVar (@Nullable final String sName)
  {
    if (m_aFlashVars != null)
      m_aFlashVars.remove (sName);
    return this;
  }

  /**
   * Add a <code>param</code> tag to the created <code>object</code> tag
   *
   * @param sName
   *        Parameter name
   * @param sValue
   *        Parameter value
   * @return this
   */
  @Nonnull
  public final HCSWFObject addObjectParam (@Nonnull final String sName, final String sValue)
  {
    if (!JSMarshaller.isJSIdentifier (sName))
      throw new IllegalArgumentException ("The name '" + sName + "' is not a legal JS identifier!");

    if (m_aObjectParams == null)
      m_aObjectParams = new CommonsLinkedHashMap <> ();
    m_aObjectParams.put (sName, sValue);
    return this;
  }

  /**
   * Remove a <code>param</code> tag that should be created underneath the
   * <code>object</code> tag.
   *
   * @param sName
   *        The name of the parameter to be removed
   * @return this
   */
  @Nonnull
  public final HCSWFObject removeObjectParam (@Nullable final String sName)
  {
    if (m_aObjectParams != null)
      m_aObjectParams.remove (sName);
    return this;
  }

  /**
   * Add an attribute to the created <code>object</code> tag
   *
   * @param sName
   *        Attribute name
   * @param sValue
   *        Attribute value
   * @return this
   */
  @Nonnull
  public final HCSWFObject addObjectAttr (@Nonnull final String sName, final String sValue)
  {
    if (!JSMarshaller.isJSIdentifier (sName))
      throw new IllegalArgumentException ("The name '" + sName + "' is not a legal JS identifier!");

    if (m_aObjectAttrs == null)
      m_aObjectAttrs = new CommonsLinkedHashMap <> ();
    m_aObjectAttrs.put (sName, sValue);
    return this;
  }

  /**
   * Remove an attribute that of the <code>object</code> tag.
   *
   * @param sName
   *        The name of the attribute to be removed
   * @return this
   */
  @Nonnull
  public final HCSWFObject removeObjectAttr (@Nullable final String sName)
  {
    if (m_aObjectAttrs != null)
      m_aObjectAttrs.remove (sName);
    return this;
  }

  @Override
  protected void onFinalizeNodeState (@Nonnull final IHCConversionSettingsToNode aConversionSettings,
                                      @Nonnull final IHCHasChildrenMutable <?, ? super IHCNode> aTargetNode)
  {
    super.onFinalizeNodeState (aConversionSettings, aTargetNode);

    // JS init code
    final JSAssocArray jsFlashvars = new JSAssocArray ();
    if (m_aFlashVars != null)
      for (final Map.Entry <String, Object> aEntry : m_aFlashVars.entrySet ())
        jsFlashvars.add (aEntry.getKey (), JSExpr.direct (JSToString.objectToJSString (aEntry.getValue ())));

    final JSAssocArray jsParams = new JSAssocArray ();
    if (m_aObjectParams != null)
      for (final Map.Entry <String, String> aEntry : m_aObjectParams.entrySet ())
        jsParams.add (aEntry.getKey (), JSExpr.direct (JSToString.objectToJSString (aEntry.getValue ())));

    final JSAssocArray jsAttributes = new JSAssocArray ();
    if (m_aObjectAttrs != null)
      for (final Map.Entry <String, String> aEntry : m_aObjectAttrs.entrySet ())
        jsAttributes.add (aEntry.getKey (), JSExpr.direct (JSToString.objectToJSString (aEntry.getValue ())));

    // Call embedder
    final JSInvocation aInvocation = JSExpr.ref ("swfobject")
                                           .invoke ("embedSWF")
                                           .arg (m_aSWFURL.getAsStringWithEncodedParameters ())
                                           .arg (getID ())
                                           .arg (m_sWidth)
                                           .arg (m_sHeight)
                                           .arg (m_sRequiredSWFVersion);
    // only supported by Flash Player 6.0.65; m_nWidth >= 310 && m_nHeight >=
    // 147;
    if (m_aExpressInstallSWFURL != null)
      aInvocation.arg (m_aExpressInstallSWFURL.getAsStringWithEncodedParameters ());
    else
      aInvocation.argNull ();
    aInvocation.arg (jsFlashvars).arg (jsParams).arg (jsAttributes);

    aTargetNode.addChild (new HCScriptInline (aInvocation));
  }

  @Override
  protected void onRegisterExternalResources (@Nonnull final IHCConversionSettingsToNode aConversionSettings,
                                              final boolean bForcedRegistration)
  {
    // Register resources
    PhotonJS.registerJSIncludeForThisRequest (EUICoreJSPathProvider.SWFOBJECT);
  }
}
