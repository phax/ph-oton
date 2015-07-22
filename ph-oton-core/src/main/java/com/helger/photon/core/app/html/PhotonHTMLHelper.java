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
package com.helger.photon.core.app.html;

import javax.annotation.Nonnull;
import javax.annotation.concurrent.NotThreadSafe;

import com.helger.commons.mime.CMimeType;
import com.helger.commons.mime.IMimeType;
import com.helger.commons.mime.MimeType;
import com.helger.commons.string.StringHelper;
import com.helger.html.hc.IHCNode;
import com.helger.html.hc.conversion.HCSettings;
import com.helger.html.hc.html.HCHtml;
import com.helger.html.hc.html.HCLink;
import com.helger.html.hc.html.HCScriptFile;
import com.helger.html.hc.impl.HCConditionalCommentNode;
import com.helger.html.resource.css.ICSSPathProvider;
import com.helger.html.resource.js.IJSPathProvider;
import com.helger.web.scope.IRequestWebScopeWithoutResponse;
import com.helger.web.servlet.response.UnifiedResponse;

/**
 * A utility class for consistent HTML creation.
 *
 * @author Philip Helger
 */
@NotThreadSafe
public final class PhotonHTMLHelper
{
  private PhotonHTMLHelper ()
  {}

  /**
   * Get the HTML MIME type to use
   *
   * @param aRequestScope
   *        The request scope
   * @return Never <code>null</code>.
   */
  @Nonnull
  public static IMimeType getMimeType (@Nonnull final IRequestWebScopeWithoutResponse aRequestScope)
  {
    // Add the charset to the MIME type
    return new MimeType (CMimeType.TEXT_HTML).addParameter (CMimeType.PARAMETER_NAME_CHARSET,
                                                            HCSettings.getHTMLCharset ().name ());
  }

  @Nonnull
  public static IHCNode getCSSNode (@Nonnull final IRequestWebScopeWithoutResponse aRequestScope,
                                    @Nonnull final ICSSPathProvider aCSS,
                                    final boolean bRegular)
  {
    final HCLink aLink = HCLink.createCSSLink (PhotonHTMLSettings.getCSSPath (aRequestScope, aCSS, bRegular));
    aLink.setMedia (aCSS.getMediaList ());

    final String sConditionalComment = aCSS.getConditionalComment ();
    if (StringHelper.hasText (sConditionalComment))
      return new HCConditionalCommentNode (sConditionalComment, aLink);

    return aLink;
  }

  @Nonnull
  public static IHCNode getJSNode (@Nonnull final IRequestWebScopeWithoutResponse aRequestScope,
                                   @Nonnull final IJSPathProvider aJS,
                                   final boolean bRegular)
  {
    final HCScriptFile aScript = HCScriptFile.create (PhotonHTMLSettings.getJSPath (aRequestScope, aJS, bRegular));

    final String sConditionalComment = aJS.getConditionalComment ();
    if (StringHelper.hasText (sConditionalComment))
      return new HCConditionalCommentNode (sConditionalComment, aScript);

    return aScript;
  }

  public static void createHTMLResponse (@Nonnull final IRequestWebScopeWithoutResponse aRequestScope,
                                         @Nonnull final UnifiedResponse aUnifiedResponse,
                                         @Nonnull final IHTMLProvider aHTMLProvider)
  {
    // Build the main HC tree
    final HCHtml aHtml = aHTMLProvider.createHTML (aRequestScope);

    // Convert HTML to String, including namespaces
    final String sXMLCode = HCSettings.getAsHTMLString (aHtml);

    // Write to response
    final IMimeType aMimeType = getMimeType (aRequestScope);
    aUnifiedResponse.setMimeType (aMimeType)
                    .setContentAndCharset (sXMLCode, HCSettings.getHTMLCharset ())
                    .disableCaching ();
  }
}
