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
package com.helger.photon.core.ajax.response;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.concurrent.Immutable;

import com.helger.commons.GlobalDebug;
import com.helger.commons.ValueEnforcer;
import com.helger.commons.equals.EqualsUtils;
import com.helger.commons.hash.HashCodeGenerator;
import com.helger.commons.string.ToStringGenerator;
import com.helger.html.hc.IHCHasChildren;
import com.helger.html.hc.IHCNode;
import com.helger.html.hc.IHCNodeWithChildren;
import com.helger.html.hc.conversion.HCSettings;
import com.helger.html.hc.htmlext.HCUtils;
import com.helger.html.hc.impl.HCNodeList;
import com.helger.html.hc.utils.AbstractHCSpecialNodes;
import com.helger.html.hc.utils.HCSpecialNodeHandler;
import com.helger.html.resource.css.ICSSPathProvider;
import com.helger.html.resource.js.IJSPathProvider;
import com.helger.json.IJson;
import com.helger.json.impl.JsonObject;
import com.helger.json.serialize.JsonWriter;
import com.helger.photon.core.app.html.PhotonHTMLSettings;
import com.helger.photon.core.app.html.PhotonCSS;
import com.helger.photon.core.app.html.PhotonJS;
import com.helger.web.scopes.domain.IRequestWebScopeWithoutResponse;

@Immutable
public class AjaxDefaultResponse extends AbstractHCSpecialNodes <AjaxDefaultResponse> implements IAjaxResponse
{
  /** Success property */
  public static final String PROPERTY_SUCCESS = "success";
  /**
   * Response value property - only in case of success - contains the response
   * data as object
   */
  public static final String PROPERTY_VALUE = "value";
  /**
   * Additional CSS files - only in case of success - contains a list of strings
   */
  public static final String PROPERTY_EXTERNAL_CSS = "externalcss";
  /** Additional inline CSS - only in case of success - contains a string */
  public static final String PROPERTY_INLINE_CSS = "inlinecss";
  /** Additional JS files - only in case of success - contains a list of strings */
  public static final String PROPERTY_EXTERNAL_JS = "externaljs";
  /** Additional inline JS - only in case of success - contains a string */
  public static final String PROPERTY_INLINE_JS = "inlinejs";
  /** Error message property - only in case of error */
  public static final String PROPERTY_ERRORMESSAGE = "errormessage";
  /** Default property for HTML content */
  public static final String PROPERTY_HTML = "html";

  private final boolean m_bSuccess;
  private final String m_sErrorMessage;
  private final IJson m_aSuccessValue;

  private void _addCSSAndJS (@Nonnull final IRequestWebScopeWithoutResponse aRequestScope)
  {
    ValueEnforcer.notNull (aRequestScope, "RequestScope");

    // Grab per-request CSS/JS only in success case!
    // Grab all CSS/JS independent of conditional comment :(
    final boolean bRegular = GlobalDebug.isDebugMode ();

    for (final ICSSPathProvider aCSS : PhotonCSS.getAllRegisteredCSSIncludesForThisRequest ())
      addExternalCSS (PhotonHTMLSettings.getCSSPath (aRequestScope, aCSS, bRegular).getAsString ());

    for (final IJSPathProvider aJS : PhotonJS.getAllRegisteredJSIncludesForThisRequest ())
      addExternalJS (PhotonHTMLSettings.getJSPath (aRequestScope, aJS, bRegular).getAsString ());
  }

  /**
   * Success constructor for HC nodes
   *
   * @param aNode
   *        The response HTML node. May be <code>null</code>.
   */
  protected AjaxDefaultResponse (@Nonnull final IRequestWebScopeWithoutResponse aRequestScope,
                                 @Nullable final IHCNode aNode)
  {
    // Do it first
    _addCSSAndJS (aRequestScope);

    // Now decompose the HCNode itself
    final JsonObject aObj = new JsonObject ();
    if (aNode != null)
    {
      // Customize before extracting special content
      if (aNode instanceof IHCNodeWithChildren <?>)
        HCUtils.customizeNodes ((IHCNodeWithChildren <?>) aNode, HCSettings.getConversionSettings ());

      IHCNode aRealNode;
      if (aNode instanceof IHCHasChildren)
      {
        // no need to keepOnDocumentReady stuff as the document is already
        // loaded
        final boolean bKeepOnDocumentReady = false;
        aRealNode = HCSpecialNodeHandler.extractSpecialContent ((IHCHasChildren) aNode, this, bKeepOnDocumentReady);
      }
      else
        aRealNode = aNode;

      // Serialize remaining node to HTML
      aObj.add (PROPERTY_HTML, HCSettings.getAsHTMLStringWithoutNamespaces (aRealNode));
    }
    m_bSuccess = true;
    m_sErrorMessage = null;
    m_aSuccessValue = aObj;
  }

  protected AjaxDefaultResponse (final boolean bSuccess,
                                 @Nullable final String sErrorMessage,
                                 @Nullable final IJson aSuccessValue,
                                 @Nullable final IRequestWebScopeWithoutResponse aRequestScope)
  {
    m_bSuccess = bSuccess;
    m_sErrorMessage = sErrorMessage;
    m_aSuccessValue = aSuccessValue;
    if (bSuccess)
      _addCSSAndJS (aRequestScope);
  }

  public boolean isSuccess ()
  {
    return m_bSuccess;
  }

  public boolean isFailure ()
  {
    return !m_bSuccess;
  }

  /**
   * @return In case this is a failure, this field contains the error message.
   *         May be <code>null</code>.
   */
  @Nullable
  public String getErrorMessage ()
  {
    return m_sErrorMessage;
  }

  /**
   * @return In case this is a success, this field contains the success object.
   *         May be <code>null</code>.
   */
  @Nullable
  public IJson getSuccessValue ()
  {
    return m_aSuccessValue;
  }

  @Nonnull
  public String getSerializedAsJSON (final boolean bIndentAndAlign)
  {
    final JsonObject aAssocArray = new JsonObject ();
    aAssocArray.add (PROPERTY_SUCCESS, m_bSuccess);
    if (m_bSuccess)
    {
      if (m_aSuccessValue != null)
        aAssocArray.add (PROPERTY_VALUE, m_aSuccessValue);
      if (hasExternalCSSs ())
        aAssocArray.add (PROPERTY_EXTERNAL_CSS, getAllExternalCSSs ());
      if (hasInlineCSS ())
        aAssocArray.add (PROPERTY_INLINE_CSS, getInlineCSS ());
      if (hasExternalJSs ())
        aAssocArray.add (PROPERTY_EXTERNAL_JS, getAllExternalJSs ());
      if (hasInlineJS ())
        aAssocArray.add (PROPERTY_INLINE_JS, getInlineJS ().getJSCode ());
    }
    else
    {
      aAssocArray.add (PROPERTY_ERRORMESSAGE, m_sErrorMessage != null ? m_sErrorMessage : "");
    }
    return JsonWriter.getAsString (aAssocArray);
  }

  @Override
  public boolean equals (final Object o)
  {
    if (o == this)
      return true;
    if (!super.equals (o))
      return false;
    final AjaxDefaultResponse rhs = (AjaxDefaultResponse) o;
    return m_bSuccess == rhs.m_bSuccess &&
           EqualsUtils.equals (m_sErrorMessage, rhs.m_sErrorMessage) &&
           EqualsUtils.equals (m_aSuccessValue, rhs.m_aSuccessValue);
  }

  @Override
  public int hashCode ()
  {
    return HashCodeGenerator.getDerived (super.hashCode ())
                            .append (m_bSuccess)
                            .append (m_sErrorMessage)
                            .append (m_aSuccessValue)
                            .getHashCode ();
  }

  @Override
  public String toString ()
  {
    return ToStringGenerator.getDerived (super.toString ())
                            .append ("success", m_bSuccess)
                            .appendIfNotNull ("errorMsg", m_sErrorMessage)
                            .appendIfNotNull ("successValue", m_aSuccessValue)
                            .toString ();
  }

  @Nonnull
  public static AjaxDefaultResponse createSuccess (@Nonnull final IRequestWebScopeWithoutResponse aRequestScope)
  {
    return createSuccess (aRequestScope, (IJson) null);
  }

  @Nonnull
  public static AjaxDefaultResponse createSuccess (@Nonnull final IRequestWebScopeWithoutResponse aRequestScope,
                                                   @Nullable final IJson aSuccessValue)
  {
    return new AjaxDefaultResponse (true, null, aSuccessValue, aRequestScope);
  }

  @Nonnull
  public static AjaxDefaultResponse createSuccess (@Nonnull final IRequestWebScopeWithoutResponse aRequestScope,
                                                   @Nullable final IHCNode... aNodes)
  {
    // Use the default converter here
    return createSuccess (aRequestScope, new HCNodeList ().addChildren (aNodes));
  }

  @Nonnull
  public static AjaxDefaultResponse createSuccess (@Nonnull final IRequestWebScopeWithoutResponse aRequestScope,
                                                   @Nullable final IHCNode aNode)
  {
    // Special case required
    return new AjaxDefaultResponse (aRequestScope, aNode);
  }

  @Nonnull
  public static AjaxDefaultResponse createError ()
  {
    return createError ((String) null);
  }

  @Nonnull
  public static AjaxDefaultResponse createError (@Nullable final String sErrorMessage)
  {
    // No request scope needed in case of error!
    // No converter needed in case of error!
    return new AjaxDefaultResponse (false, sErrorMessage, (IJson) null, (IRequestWebScopeWithoutResponse) null);
  }
}
