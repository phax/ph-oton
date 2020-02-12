package com.helger.photon.core.systemmsg;

import javax.annotation.Nonnull;

import com.helger.commons.annotation.Nonempty;
import com.helger.html.hc.html.IHCElementWithChildren;

/**
 * Abstract interface for a system message renderer.
 *
 * @author Philip Helger
 * @since 8.2.2
 */
public interface ISystemMessageRenderer
{
  /**
   * Render the system message onto the provided control.
   *
   * @param sText
   *        The text to render. Never <code>null</code>.
   * @param aTargetCtrl
   *        The target control to be filled.
   */
  void renderSystemMessage (@Nonnull @Nonempty String sText, @Nonnull IHCElementWithChildren <?> aTargetCtrl);
}
