package com.helger.photon.uictrls.prism;

import java.util.EnumSet;

import javax.annotation.Nonnull;

import com.helger.commons.ValueEnforcer;
import com.helger.commons.microdom.IMicroElement;
import com.helger.html.CHTMLAttributes;
import com.helger.html.EHTMLElement;
import com.helger.html.css.DefaultCSSClassProvider;
import com.helger.html.css.ICSSClassProvider;
import com.helger.html.hc.conversion.IHCConversionSettingsToNode;
import com.helger.html.hc.html.HCPre;
import com.helger.html.hc.impl.HCHasCSSClasses;
import com.helger.photon.core.app.html.PhotonCSS;
import com.helger.photon.core.app.html.PhotonJS;
import com.helger.photon.uictrls.EUICtrlsCSSPathProvider;
import com.helger.photon.uictrls.EUICtrlsJSPathProvider;

/**
 * prism.js pre + code element.
 *
 * @author Philip Helger
 */
public class HCPrismJS extends HCPre
{
  public static final ICSSClassProvider CSS_CLASS_PRISMJS = DefaultCSSClassProvider.create ("prismjs");

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
    // Plugins must go on pre
    final HCHasCSSClasses p = new HCHasCSSClasses ();
    p.addClass (CSS_CLASS_PRISMJS);
    p.addClass (m_eLanguage);
    p.addClasses (m_aPlugins);
    aElement.setAttribute (CHTMLAttributes.CLASS, p.getAllClassesAsString ());

    // Create nested code tag
    final IMicroElement eCode = aElement.appendElement (aElement.getNamespaceURI (),
                                                        EHTMLElement.CODE.getElementNameLowerCase ());
    super.applyProperties (eCode, aConversionSettings);
  }

  public static void registerExternalResources ()
  {
    PhotonCSS.registerCSSIncludeForThisRequest (EUICtrlsCSSPathProvider.PRISMJS);
    PhotonCSS.registerCSSIncludeForThisRequest (EUICtrlsCSSPathProvider.PRISMJS_BOOTSTRAP);
    PhotonJS.registerJSIncludeForThisRequest (EUICtrlsJSPathProvider.PRISMJS);
  }
}
