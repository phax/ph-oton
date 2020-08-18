package com.helger.photon.uictrls.prism;

import javax.annotation.Nonnull;

import com.helger.html.hc.ext.HCHasCSSClasses;
import com.helger.html.hc.ext.HCHasCSSStyles;
import com.helger.xml.microdom.IMicroElement;

/**
 * Shows a "Download" button on the right top. Works only if the "src" is used.
 *
 * @author Philip Helger
 */
public class PrismPluginDownloadLink implements IPrismPlugin
{
  public PrismPluginDownloadLink ()
  {}

  public void applyOnPre (@Nonnull final IMicroElement aPreElement,
                          @Nonnull final HCHasCSSClasses aPreClasses,
                          @Nonnull final HCHasCSSStyles aPreStyles)
  {
    aPreElement.setAttribute ("data-download-link", "true");
  }
}
