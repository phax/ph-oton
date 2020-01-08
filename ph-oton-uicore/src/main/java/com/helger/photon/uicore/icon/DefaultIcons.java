/**
 * Copyright (C) 2014-2020 Philip Helger (www.helger.com)
 * philip[at]helger[dot]com
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.helger.photon.uicore.icon;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.concurrent.NotThreadSafe;

import com.helger.commons.ValueEnforcer;
import com.helger.commons.annotation.ReturnsMutableCopy;
import com.helger.commons.collection.impl.CommonsHashMap;
import com.helger.commons.collection.impl.ICommonsMap;

/**
 * This class maintains the actual icon objects to the different default icons.
 *
 * @author Philip Helger
 */
@NotThreadSafe
public final class DefaultIcons
{
  private static final ICommonsMap <EDefaultIcon, IIcon> s_aMap = new CommonsHashMap <> ();

  private DefaultIcons ()
  {}

  /**
   * @return <code>true</code> if at least a single default icon is defined,
   *         <code>false</code> if no default icon is defined.
   */
  public static boolean areDefined ()
  {
    return s_aMap.isNotEmpty ();
  }

  /**
   * Get the icon assigned to the passed default icon.
   *
   * @param eDefaultIcon
   *        The default icon to query. May be <code>null</code>.
   * @return <code>null</code> if no icon was found.
   */
  @Nullable
  public static IIcon get (@Nullable final EDefaultIcon eDefaultIcon)
  {
    if (eDefaultIcon == null)
      return null;
    return s_aMap.get (eDefaultIcon);
  }

  /**
   * @return A copy of all currently defined default icons. Never
   *         <code>null</code> but maybe empty.
   */
  @Nonnull
  @ReturnsMutableCopy
  public static ICommonsMap <EDefaultIcon, IIcon> getAll ()
  {
    return s_aMap.getClone ();
  }

  /**
   * Set the icon to be used for the specified default icon. Existing
   * definitions are simply overwritten.
   *
   * @param eDefaultIcon
   *        The default icon to use. May not be <code>null</code>.
   * @param aIcon
   *        The icon to set. May be <code>null</code> in which case the
   *        assignment is removed.
   */
  public static void set (@Nonnull final EDefaultIcon eDefaultIcon, @Nullable final IIcon aIcon)
  {
    ValueEnforcer.notNull (eDefaultIcon, "DefaultIcon");
    if (aIcon != null)
      s_aMap.put (eDefaultIcon, aIcon);
    else
      s_aMap.remove (eDefaultIcon);
  }

  /**
   * Remove all default icon assignments.
   */
  public static void removeAll ()
  {
    s_aMap.clear ();
  }
}
