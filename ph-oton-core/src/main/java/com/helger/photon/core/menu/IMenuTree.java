/*
 * Copyright (C) 2014-2025 Philip Helger (www.helger.com)
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
package com.helger.photon.core.menu;

import javax.annotation.Nullable;

import com.helger.tree.withid.DefaultTreeItemWithID;
import com.helger.tree.withid.unique.ITreeWithGlobalUniqueID;

/**
 * A combination of a tree and the menu operations
 *
 * @author philip
 */
public interface IMenuTree extends
                           ITreeWithGlobalUniqueID <String, IMenuObject, DefaultTreeItemWithID <String, IMenuObject>>,
                           IMenuOperations
{
  @Nullable
  DefaultTreeItemWithID <String, IMenuObject> getRootItemOfItemWithID (@Nullable String sMenuItemID);

  @Nullable
  IMenuObject getRootItemDataOfItemWithID (@Nullable String sMenuItemID);
}
