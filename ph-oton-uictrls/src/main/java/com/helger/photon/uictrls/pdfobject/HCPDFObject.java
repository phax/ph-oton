/*
 * Copyright (C) 2014-2025 Philip Helger (www.helger.com)
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
package com.helger.photon.uictrls.pdfobject;

import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.Nullable;

import com.helger.annotation.OverridingMethodsMustInvokeSuper;
import com.helger.base.enforce.ValueEnforcer;
import com.helger.html.hc.IHCConversionSettingsToNode;
import com.helger.html.hc.IHCHasChildrenMutable;
import com.helger.html.hc.IHCNode;
import com.helger.html.hc.html.grouping.AbstractHCDiv;
import com.helger.html.hc.html.script.HCScriptInline;
import com.helger.html.jquery.JQuerySelector;
import com.helger.photon.app.html.PhotonJS;
import com.helger.photon.uictrls.EUICtrlsJSPathProvider;
import com.helger.url.ISimpleURL;

/**
 * Embed an PDF inline into the page. See https://pdfobject.com/
 *
 * @author Philip Helger
 */
public class HCPDFObject extends AbstractHCDiv <HCPDFObject>
{
  private final ISimpleURL m_aPathToPDF;
  private PDFObjectOptions m_aOptions;

  public HCPDFObject (@NonNull final ISimpleURL aPathToPDF)
  {
    ensureID ();
    m_aPathToPDF = ValueEnforcer.notNull (aPathToPDF, "PathToPDF");
  }

  @NonNull
  public ISimpleURL getPathToPDF ()
  {
    return m_aPathToPDF;
  }

  @NonNull
  public HCPDFObject setOptions (@Nullable final PDFObjectOptions aOptions)
  {
    m_aOptions = aOptions;
    return this;
  }

  @Nullable
  public PDFObjectOptions getOptions ()
  {
    return m_aOptions;
  }

  @Override
  @OverridingMethodsMustInvokeSuper
  protected void onFinalizeNodeState (@NonNull final IHCConversionSettingsToNode aConversionSettings,
                                      @NonNull final IHCHasChildrenMutable <?, ? super IHCNode> aTargetNode)
  {
    super.onFinalizeNodeState (aConversionSettings, aTargetNode);

    // Add special JS code
    aTargetNode.addChild (new HCScriptInline (PDFObjectJS.embed (m_aPathToPDF, JQuerySelector.id (this).invoke (), m_aOptions)));
  }

  @Override
  protected void onRegisterExternalResources (@NonNull final IHCConversionSettingsToNode aConversionSettings,
                                              final boolean bForcedRegistration)
  {
    PhotonJS.registerJSIncludeForThisRequest (EUICtrlsJSPathProvider.PDFOBJECT2);
  }
}
