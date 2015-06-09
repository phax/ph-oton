package com.helger.photon.uictrls.prism;

import java.util.EnumSet;

import javax.annotation.Nonnull;

import com.helger.commons.ValueEnforcer;
import com.helger.commons.microdom.IMicroElement;
import com.helger.html.hc.conversion.IHCConversionSettingsToNode;
import com.helger.html.hc.html.HCCode;
import com.helger.photon.core.app.html.PhotonCSS;
import com.helger.photon.core.app.html.PhotonJS;
import com.helger.photon.uictrls.EUICtrlsCSSPathProvider;
import com.helger.photon.uictrls.EUICtrlsJSPathProvider;

/**
 * prism.js code element. Should be wrapped inside a &lt;pre;&gt; element.
 *
 * @author Philip Helger
 */
public class HCPrismJS extends HCCode
{
  private final EPrismLanguage m_eLanguage;
  private final EnumSet <EPrismPlugin> m_aPlugins = EnumSet.noneOf (EPrismPlugin.class);

  public HCPrismJS (@Nonnull final EPrismLanguage eLanguage)
  {
    m_eLanguage = ValueEnforcer.notNull (eLanguage, "Language");
    registerExternalResources ();
  }

  @Nonnull
  public EPrismLanguage getPrismLanguage ()
  {
    return m_eLanguage;
  }

  @Nonnull
  public HCPrismJS addPlugin (@Nonnull final EPrismPlugin ePlugin)
  {
    ValueEnforcer.notNull (ePlugin, "Plugin");
    m_aPlugins.add (ePlugin);
    return this;
  }

  @Override
  protected void applyProperties (@Nonnull final IMicroElement aElement,
                                  @Nonnull final IHCConversionSettingsToNode aConversionSettings)
  {
    addClass (m_eLanguage);
    addClasses (m_aPlugins);
    super.applyProperties (aElement, aConversionSettings);
  }

  public static void registerExternalResources ()
  {
    PhotonCSS.registerCSSIncludeForThisRequest (EUICtrlsCSSPathProvider.PRISMJS);
    PhotonJS.registerJSIncludeForThisRequest (EUICtrlsJSPathProvider.PRISMJS);
  }
}
