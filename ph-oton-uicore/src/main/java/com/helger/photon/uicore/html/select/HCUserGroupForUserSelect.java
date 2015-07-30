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
package com.helger.photon.uicore.html.select;

import java.util.Collection;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.helger.commons.collection.CollectionHelper;
import com.helger.commons.name.ComparatorHasName;
import com.helger.html.hchtml.form.HCOption;
import com.helger.photon.basic.security.AccessManager;
import com.helger.photon.basic.security.usergroup.IUserGroup;
import com.helger.photon.core.form.RequestField;

/**
 * Select user groups a user should be assigned to
 *
 * @author Philip Helger
 */
public class HCUserGroupForUserSelect extends HCExtSelect
{
  public HCUserGroupForUserSelect (@Nonnull final RequestField aRF,
                                   @Nullable final Collection <String> aSelectedUserGroups)
  {
    super (aRF);
    setMultiple (true);

    final Collection <? extends IUserGroup> aAllUserGroups = AccessManager.getInstance ().getAllUserGroups ();
    setSize (Math.min (10, aAllUserGroups.size ()));
    for (final IUserGroup aUserGroup : CollectionHelper.getSorted (aAllUserGroups,
                                                                   new ComparatorHasName <IUserGroup> ()))
    {
      final HCOption aOption = addOption (aUserGroup.getID (), aUserGroup.getName ());
      if (aSelectedUserGroups != null && aSelectedUserGroups.contains (aUserGroup.getID ()))
        aOption.setSelected (true);
    }
  }
}
