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
package com.helger.photon.uictrls.fineupload;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.helger.commons.ValueEnforcer;
import com.helger.commons.idfactory.GlobalIDFactory;
import com.helger.html.EHTMLElement;
import com.helger.html.hc.IHCElement;
import com.helger.html.hc.IHCNode;
import com.helger.html.hc.IHCNodeBuilder;
import com.helger.html.hc.html.HCDiv;
import com.helger.html.hc.html.HCScriptOnDocumentReady;
import com.helger.html.hc.impl.HCNodeList;
import com.helger.html.js.builder.IJSAssignmentTarget;
import com.helger.html.js.builder.JSAnonymousFunction;
import com.helger.html.js.builder.JSExpr;
import com.helger.html.js.builder.JSPackage;
import com.helger.html.js.builder.JSVar;
import com.helger.html.js.builder.jquery.JQuery;
import com.helger.html.js.builder.jquery.JQuerySelector;
import com.helger.photon.uictrls.EUICtrlsCSSPathProvider;
import com.helger.photon.uictrls.EUICtrlsJSPathProvider;
import com.helger.web.scopes.mgr.WebScopeManager;
import com.helger.webbasics.app.html.PerRequestCSSIncludes;
import com.helger.webbasics.app.html.PerRequestJSIncludes;

public class HCFineUploaderBasic implements IHCNodeBuilder
{
  private final FineUploaderBasic m_aUploader;
  private IHCElement <?> m_aButton;

  public HCFineUploaderBasic (@Nonnull final FineUploaderBasic aUploader)
  {
    m_aUploader = ValueEnforcer.notNull (aUploader, "Uploader");
  }

  /**
   * Set the button object to use. The button element MUST NOT be manually
   * attached to the response tree - this happens inside!
   *
   * @param aButton
   *        The button object to use. May be <code>null</code>.
   * @return this
   */
  @Nonnull
  public HCFineUploaderBasic setButtonToUse (@Nullable final IHCElement <?> aButton)
  {
    m_aButton = aButton;
    return this;
  }

  @Nullable
  public IHCNode build ()
  {
    registerExternalResources ();
    final String sID = GlobalIDFactory.getNewStringID ();
    final HCNodeList ret = new HCNodeList ();
    ret.addChild (new HCDiv ().setID (sID));
    if (m_aButton != null)
    {
      // We have a special button to use
      ret.addChild (m_aButton);
      m_aUploader.setButtonElementID (m_aButton.ensureID ().getID ());
    }

    // Start building JS
    final JSPackage aPkg = new JSPackage ();

    // The global variable holding the number of files selected for upload
    IJSAssignmentTarget aGlobalCnt;
    if (!WebScopeManager.getRequestScope ().getAndSetAttributeFlag ("fineuploader-globalvars"))
      aGlobalCnt = aPkg.var ("g_nUploadCnt", 0);
    else
      aGlobalCnt = JSExpr.ref ("g_nUploadCnt");
    final JSVar aLocalCnt = aPkg.var ("nCnt" + sID, 0);

    // On submit, inc counter
    final JSAnonymousFunction aOnSubmit = new JSAnonymousFunction ();
    aOnSubmit.body ().incrPrefix (aGlobalCnt);
    aOnSubmit.body ().incrPrefix (aLocalCnt);
    // On cancel, dec counter
    final JSAnonymousFunction aOnCancel = new JSAnonymousFunction ();
    aOnCancel.body ().decrPrefix (aGlobalCnt);
    aOnCancel.body ().decrPrefix (aLocalCnt);

    final JSVar aUpload = aPkg.var ("u" + sID, JQuery.idRef (sID));
    aPkg.add (aUpload.invoke ("fineUploader")
                     .arg (m_aUploader.getJSON ())
                     .invoke ("on")
                     .arg ("submit")
                     .arg (aOnSubmit)
                     .invoke ("on")
                     .arg ("cancel")
                     .arg (aOnCancel));

    if (!m_aUploader.isAutoUpload ())
    {
      // Manually trigger upload when form is submitted

      // Get closest form to the input ID
      final JSVar aForm = aPkg.var ("f" + sID, aUpload.invoke ("closest").arg (EHTMLElement.FORM));

      final JSAnonymousFunction aOnClick = new JSAnonymousFunction ();

      // If no file was uploaded, process file normally
      aOnClick.body ()._if (aLocalCnt.eq (0))._then ()._return (true);

      {
        // assign an "onComplete" handler to the fileuploader, that submits the
        // form, as soon, as all uploaded files are handled
        final JSAnonymousFunction aOnCompete = new JSAnonymousFunction ();
        aOnCompete.body ()._if (aGlobalCnt.decrPrefix ().eq (0))._then ().add (aForm.invoke ("submit"));
        aOnClick.body ().add (aUpload.invoke ("on").arg ("complete").arg (aOnCompete));
      }
      // Start the uploading manually
      aOnClick.body ().invoke (aUpload, "fineUploader").arg ("uploadStoredFiles");
      aOnClick.body ()._return (false);

      // Find the first ":submit" element of the closest form of the passed
      // element and set the "click" handler
      aPkg.addStatement (JQuerySelector.submit.invoke ().arg (aForm).click (aOnClick));
    }
    ret.addChild (new HCScriptOnDocumentReady (aPkg));
    return ret;
  }

  public static final void registerExternalResources ()
  {
    PerRequestJSIncludes.registerJSIncludeForThisRequest (EUICtrlsJSPathProvider.FINEUPLOADER_320);
    PerRequestCSSIncludes.registerCSSIncludeForThisRequest (EUICtrlsCSSPathProvider.FINEUPLOADER_320);
  }
}
