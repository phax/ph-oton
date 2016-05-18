package com.helger.photon.uictrls.pdfobject;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
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
  private PDFObjectOptions m_aOptions;

  public HCPDFObject (@Nonnull final ISimpleURL aPathToPDF)
  {
    ensureID ();
    m_aPathToPDF = ValueEnforcer.notNull (aPathToPDF, "PathToPDF");
  }

  @Nonnull
  public ISimpleURL getPathToPDF ()
  {
    return m_aPathToPDF;
  }

  @Nonnull
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
  protected void onFinalizeNodeState (@Nonnull final IHCConversionSettingsToNode aConversionSettings,
                                      @Nonnull final IHCHasChildrenMutable <?, ? super IHCNode> aTargetNode)
  {
    super.onFinalizeNodeState (aConversionSettings, aTargetNode);

    // Add special JS code
    aTargetNode.addChild (new HCScriptInline (PDFObjectJS.embed (m_aPathToPDF,
                                                                 JQuerySelector.id (this).invoke (),
                                                                 m_aOptions)));
  }

  @Override
  protected void onRegisterExternalResources (@Nonnull final IHCConversionSettingsToNode aConversionSettings,
                                              final boolean bForcedRegistration)
  {
    PhotonJS.registerJSIncludeForThisRequest (EUICtrlsJSPathProvider.PDFOBJECT2);
  }
}
