/**
 * Copyright (C) 2014-2017 Philip Helger (www.helger.com)
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
package com.helger.photon.uicore.html.select;

import java.util.Locale;
import java.util.function.Predicate;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.helger.commons.collection.CollectionHelper;
import com.helger.commons.name.IHasDisplayName;
import com.helger.html.request.IHCRequestField;
import com.helger.photon.security.mgr.PhotonSecurityManager;
import com.helger.photon.security.user.IUser;

/**
 * A select for {@link IUser} objects.
 * 
 * @author Philip Helger
 */
public class HCUserSelect extends HCExtSelect
{
  public HCUserSelect (@Nonnull final IHCRequestField aRF,
                       @Nonnull final Locale aDisplayLocale,
                       @Nullable final Predicate <IUser> aFilter)
  {
    super (aRF);

    // for all items
    for (final IUser aUser : CollectionHelper.getSorted (PhotonSecurityManager.getUserMgr ().getAllUsers (),
                                                         IHasDisplayName.getComparatorCollating (aDisplayLocale)))
      if (aFilter == null || aFilter.test (aUser))
        addOption (aUser.getID (), aUser.getDisplayName () + " (" + aUser.getLoginName () + ")");
  }
}
