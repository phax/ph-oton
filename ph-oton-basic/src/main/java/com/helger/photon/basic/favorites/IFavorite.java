/**
 * Copyright (C) 2014-2015 Philip Helger (www.helger.com)
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
package com.helger.photon.basic.favorites;

import java.io.Serializable;
import java.util.Map;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.helger.commons.annotation.Nonempty;
import com.helger.commons.annotation.ReturnsMutableCopy;
import com.helger.commons.id.IHasID;
import com.helger.commons.name.IHasDisplayName;

/**
 * Base interface for a single favorite bookmark.
 *
 * @author Philip Helger
 */
public interface IFavorite extends IHasID <String>, IHasDisplayName, Serializable
{
  /**
   * @return The ID of the user owning the favorite.
   */
  @Nonnull
  @Nonempty
  String getUserID ();

  /**
   * @return The internal application ID to which this favorite belongs.
   */
  @Nonnull
  @Nonempty
  String getApplicationID ();

  /**
   * @return The menu item to which this favorite bookmark points to.
   */
  @Nonnull
  @Nonempty
  String getMenuItemID ();

  /**
   * @return The additional optional parameters of this favorite. May be empty
   *         but never be <code>null</code>.
   */
  @Nonnull
  @ReturnsMutableCopy
  Map <String, String> getAdditionalParams ();

  /**
   * @param sAppID
   *        The application ID to compare to.
   * @param sMenuItemID
   *        Menu item ID
   * @param aAdditionalParams
   *        Additional params. May be <code>null</code>.
   * @return <code>true</code> if the passed favourite has the same application
   *         ID, menuitem ID and additional params, <code>false</code>
   *         otherwise.
   */
  boolean hasSameContent (@Nullable String sAppID,
                          @Nullable String sMenuItemID,
                          @Nullable Map <String, String> aAdditionalParams);
}
