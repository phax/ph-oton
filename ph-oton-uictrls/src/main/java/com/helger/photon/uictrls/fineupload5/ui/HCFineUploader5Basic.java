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
package com.helger.photon.uictrls.fineupload5.ui;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.helger.commons.ValueEnforcer;
import com.helger.commons.id.factory.GlobalIDFactory;
import com.helger.html.EHTMLElement;
import com.helger.html.hc.IHCConversionSettingsToNode;
import com.helger.html.hc.IHCHasChildrenMutable;
import com.helger.html.hc.IHCNode;
import com.helger.html.hc.html.IHCElement;
import com.helger.html.hc.html.grouping.HCDiv;
import com.helger.html.hc.html.script.HCScriptInlineOnDocumentReady;
import com.helger.html.hc.impl.AbstractHCNodeList;
import com.helger.html.jquery.JQuery;
import com.helger.html.jquery.JQuerySelector;
import com.helger.html.jscode.IJSAssignmentTarget;
import com.helger.html.jscode.JSAnonymousFunction;
import com.helger.html.jscode.JSExpr;
import com.helger.html.jscode.JSPackage;
import com.helger.html.jscode.JSVar;
import com.helger.photon.app.html.PhotonCSS;
import com.helger.photon.app.html.PhotonJS;
import com.helger.photon.uictrls.EUICtrlsCSSPathProvider;
import com.helger.photon.uictrls.EUICtrlsJSPathProvider;
import com.helger.photon.uictrls.fineupload5.FineUploader5Core;
import com.helger.web.scope.mgr.WebScopeManager;

public class HCFineUploader5Basic extends AbstractHCNodeList <HCFineUploader5Basic>
{
  private final FineUploader5Core m_aUploader;
  private IHCElement <?> m_aButton;

  public HCFineUploader5Basic (@Nonnull final FineUploader5Core aUploader)
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
  public HCFineUploader5Basic setButtonToUse (@Nullable final IHCElement <?> aButton)
  {
    m_aButton = aButton;
    return this;
  }

  @Override
  protected void onFinalizeNodeState (@Nonnull final IHCConversionSettingsToNode aConversionSettings,
                                      @Nonnull final IHCHasChildrenMutable <?, ? super IHCNode> aTargetNode)
  {
    super.onFinalizeNodeState (aConversionSettings, aTargetNode);

    final String sID = GlobalIDFactory.getNewStringID ();
    addChild (new HCDiv ().setID (sID));
    if (m_aButton != null)
    {
      // We have a special button to use
      addChild (m_aButton);
      m_aUploader.setButtonElementID (m_aButton.ensureID ().getID ());
    }

    // Start building JS
    final JSPackage aPkg = new JSPackage ();

    // The global variable holding the number of files selected for upload
    IJSAssignmentTarget aGlobalCnt;
    if (!WebScopeManager.getRequestScope ().attrs ().getAndSetFlag ("fineuploader5-globalvars"))
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
                     .arg (m_aUploader.getJSCode ())
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
    addChild (new HCScriptInlineOnDocumentReady (aPkg));
  }

  @Override
  protected void onRegisterExternalResources (@Nonnull final IHCConversionSettingsToNode aConversionSettings,
                                              final boolean bForceRegistration)
  {
    PhotonJS.registerJSIncludeForThisRequest (EUICtrlsJSPathProvider.FINEUPLOADER_5);
    PhotonCSS.registerCSSIncludeForThisRequest (EUICtrlsCSSPathProvider.FINEUPLOADER_5);
  }
}
