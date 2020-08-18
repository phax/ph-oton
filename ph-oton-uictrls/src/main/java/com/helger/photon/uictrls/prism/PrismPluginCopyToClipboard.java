package com.helger.photon.uictrls.prism;

import javax.annotation.Nonnull;

import com.helger.html.hc.ext.HCHasCSSClasses;
import com.helger.html.hc.ext.HCHasCSSStyles;
import com.helger.photon.app.html.PhotonJS;
import com.helger.photon.uictrls.EUICtrlsJSPathProvider;
import com.helger.xml.microdom.IMicroElement;

/**
 * Shows a "Copy to clipboard" button on the right top
 *
 * @author Philip Helger
 */
public class PrismPluginCopyToClipboard implements IPrismPlugin
{
  public PrismPluginCopyToClipboard ()
  {}

  public void registerExternalResourcesBeforePrism ()
  {
    // External JS is needed
    // Must be loaded before prism is loaded
    PhotonJS.registerJSIncludeForThisRequest (EUICtrlsJSPathProvider.CLIPBOARD);
  }

  public void applyOnPre (@Nonnull final IMicroElement aPreElement,
                          @Nonnull final HCHasCSSClasses aPreClasses,
                          @Nonnull final HCHasCSSStyles aPreStyles)
  {
    // Nothing
  }
}
