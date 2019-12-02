package com.helger.photon.core.menu;

import java.util.Locale;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.helger.commons.annotation.UnsupportedOperation;

/**
 * A special menu item that is just a link to an existing other menu item.
 * Basically this is used to change the URL of an existing menu item without
 * breaking links.
 *
 * @author Philip Helger
 * @since 8.2.2
 */
public interface IMenuItemRedirectToPage extends IMenuItem
{
  /**
   * @return The target page to which this item links. May not be
   *         <code>null</code>.
   */
  @Nonnull
  IMenuItemPage getTargetMenuItemPage ();

  @Nullable
  default String getTarget ()
  {
    return getTargetMenuItemPage ().getTarget ();
  }

  @UnsupportedOperation
  default IMenuItem setTarget (@Nullable final String sTarget)
  {
    throw new UnsupportedOperationException ("The target of a redirect cannot be changed");
  }

  @Nullable
  default String getDisplayText (@Nonnull final Locale aContentLocale)
  {
    return getTargetMenuItemPage ().getDisplayText (aContentLocale);
  }
}
