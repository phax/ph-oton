/**
 * Copyright (C) 2014-2016 Philip Helger (www.helger.com)
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

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.helger.commons.collection.CollectionHelper;
import com.helger.commons.filter.IFilter;
import com.helger.commons.name.CollatingComparatorHasDisplayName;
import com.helger.html.request.IHCRequestField;
import com.helger.photon.security.mgr.PhotonSecurityManager;
import com.helger.photon.security.token.app.IAppToken;

public class HCAppTokenSelect extends HCExtSelect
{
  public HCAppTokenSelect (@Nonnull final IHCRequestField aRF,
                           @Nonnull final Locale aDisplayLocale,
                           @Nullable final IFilter <IAppToken> aFilter)
  {
    super (aRF);

    // for all items
    for (final IAppToken aAppToken : CollectionHelper.getSorted (PhotonSecurityManager.getAppTokenMgr ()
                                                                                      .getAllAppTokens (),
                                                                 new CollatingComparatorHasDisplayName <> (aDisplayLocale)))
      if (aFilter == null || aFilter.matchesFilter (aAppToken))
      {
        final String sDisplayText = aAppToken.getDisplayName ();
        addOption (aAppToken.getID (), sDisplayText);
      }
  }
}
