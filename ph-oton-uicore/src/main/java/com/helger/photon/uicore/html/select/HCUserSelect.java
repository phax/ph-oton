/*
 * Copyright (C) 2014-2024 Philip Helger (www.helger.com)
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
import java.util.function.Function;
import java.util.function.Predicate;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.helger.commons.collection.impl.ICommonsList;
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
  public static final Function <? super IUser, String> DEFAULT_DISPLAY_TEXT_PROVIDER = aUser -> aUser.getDisplayName () +
                                                                                                " (" +
                                                                                                aUser.getLoginName () +
                                                                                                ")";

  public HCUserSelect (@Nonnull final IHCRequestField aRF,
                       @Nonnull final Locale aDisplayLocale,
                       @Nullable final Predicate <? super IUser> aFilter)
  {
    this (aRF, aDisplayLocale, aFilter, DEFAULT_DISPLAY_TEXT_PROVIDER);
  }

  public HCUserSelect (@Nonnull final IHCRequestField aRF,
                       @Nonnull final Locale aDisplayLocale,
                       @Nullable final Predicate <? super IUser> aFilter,
                       @Nonnull final Function <? super IUser, String> aDisplayTextProvider)
  {
    this (aRF,
          PhotonSecurityManager.getUserMgr ()
                               .getAllActiveUsers ()
                               .getSortedInline (IHasDisplayName.getComparatorCollating (aDisplayLocale)),
          aFilter,
          aDisplayTextProvider);
  }

  public HCUserSelect (@Nonnull final IHCRequestField aRF,
                       @Nonnull final ICommonsList <? extends IUser> aUsers,
                       @Nullable final Predicate <? super IUser> aFilter,
                       @Nonnull final Function <? super IUser, String> aDisplayTextProvider)
  {
    super (aRF);

    // for all items
    for (final IUser aUser : aUsers)
      if (aFilter == null || aFilter.test (aUser))
        addOption (aUser.getID (), aDisplayTextProvider.apply (aUser));
  }
}
