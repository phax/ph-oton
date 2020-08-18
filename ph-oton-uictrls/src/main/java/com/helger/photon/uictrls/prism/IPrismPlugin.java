package com.helger.photon.uictrls.prism;

import javax.annotation.Nonnull;

import com.helger.html.hc.ext.HCHasCSSClasses;
import com.helger.html.hc.ext.HCHasCSSStyles;
import com.helger.xml.microdom.IMicroElement;

/**
 * Interface for PrismJS plugins
 *
 * @author Philip Helger
 */
public interface IPrismPlugin
{
  /**
   * Register all CSS/JS necessary for this plugin
   */
  default void registerExternalResourcesBeforePrism ()
  {}

  /**
   * Register all CSS/JS necessary for this plugin
   */
  default void registerExternalResourcesAfterPrism ()
  {}

  /**
   * Create the HTML elements for the &lt;pre&gt; element
   *
   * @param aPreElement
   *        The pre element
   * @param aPreClasses
   *        The pre element classes
   * @param aPreStyles
   *        The pre element styles
   */
  void applyOnPre (@Nonnull IMicroElement aPreElement, @Nonnull HCHasCSSClasses aPreClasses, @Nonnull HCHasCSSStyles aPreStyles);
}
