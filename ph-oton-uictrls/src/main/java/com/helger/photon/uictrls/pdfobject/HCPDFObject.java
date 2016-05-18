package com.helger.photon.uictrls.pdfobject;

import javax.annotation.Nonnull;
import javax.annotation.OverridingMethodsMustInvokeSuper;

import com.helger.commons.ValueEnforcer;
import com.helger.commons.url.ISimpleURL;
import com.helger.html.hc.IHCConversionSettingsToNode;
import com.helger.html.hc.IHCHasChildrenMutable;
import com.helger.html.hc.IHCNode;
import com.helger.html.hc.html.grouping.AbstractHCDiv;
import com.helger.html.hc.html.script.HCScriptInline;
import com.helger.html.jquery.JQuerySelector;
import com.helger.photon.core.app.html.PhotonJS;
import com.helger.photon.uictrls.EUICtrlsJSPathProvider;

/**
 * Embed an PDF inline into the page. See https://pdfobject.com/
 *
 * @author Philip Helger
 */
public class HCPDFObject extends AbstractHCDiv <HCPDFObject>
{
  private final ISimpleURL m_aPathToPDF;

  public HCPDFObject (@Nonnull final ISimpleURL aPathToPDF)
  {
    ensureID ();
    m_aPathToPDF = ValueEnforcer.notNull (aPathToPDF, "PathToPDF");
  }

  @Override
  @OverridingMethodsMustInvokeSuper
  protected void onFinalizeNodeState (@Nonnull final IHCConversionSettingsToNode aConversionSettings,
                                      @Nonnull final IHCHasChildrenMutable <?, ? super IHCNode> aTargetNode)
  {
    super.onFinalizeNodeState (aConversionSettings, aTargetNode);

    // Add special JS code
    aTargetNode.addChild (new HCScriptInline (PDFObjectJS.embed (m_aPathToPDF,
                                                                 JQuerySelector.id (this).invoke (),
                                                                 null)));
  }

  @Override
  protected void onRegisterExternalResources (@Nonnull final IHCConversionSettingsToNode aConversionSettings,
                                              final boolean bForcedRegistration)
  {
    PhotonJS.registerJSIncludeForThisRequest (EUICtrlsJSPathProvider.PDFOBJECT2);
  }
}
