package com.helger.photon.basic.app.menu;

import com.helger.commons.filter.IFilter;
import com.helger.commons.text.display.IHasDisplayText;

/**
 * Special menu item filter to determine the visibility of a menu item. If
 * consists of the filtering and provides an optional descriptive text.
 *
 * @author Philip Helger
 */
public interface IMenuObjectFilter extends IFilter <IMenuObject>, IHasDisplayText
{
  /* empty */
}
