/*
 * Copyright (C) 2014-2023 Philip Helger (www.helger.com)
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

import java.util.Collection;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.helger.commons.collection.impl.ICommonsList;
import com.helger.commons.name.IHasName;
import com.helger.html.hc.html.forms.HCOption;
import com.helger.photon.core.form.RequestField;
import com.helger.photon.security.mgr.PhotonSecurityManager;
import com.helger.photon.security.role.IRole;

/**
 * Select roles a user group should be assigned to
 *
 * @author Philip Helger
 */
public class HCRoleForUserGroupSelect extends HCExtSelect
{
  public HCRoleForUserGroupSelect (@Nonnull final RequestField aRF, @Nullable final Collection <String> aSelectedRoles)
  {
    super (aRF);
    setMultiple (true);

    final ICommonsList <IRole> aAllRoles = PhotonSecurityManager.getRoleMgr ().getAll ();
    setSize (Math.min (10, aAllRoles.size ()));
    for (final IRole aRole : aAllRoles.getSortedInline (IHasName.getComparatorName ()))
    {
      final HCOption aOption = addOption (aRole.getID (), aRole.getName ());
      if (aSelectedRoles != null && aSelectedRoles.contains (aRole.getID ()))
        aOption.setSelected (true);
    }
  }
}
