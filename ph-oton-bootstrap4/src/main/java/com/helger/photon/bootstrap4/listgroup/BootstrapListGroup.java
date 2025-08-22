/*
 * Copyright (C) 2018-2025 Philip Helger (www.helger.com)
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
package com.helger.photon.bootstrap4.listgroup;

import com.helger.html.hc.html.grouping.AbstractHCUL;
import com.helger.html.hc.html.grouping.HCLI;
import com.helger.photon.bootstrap4.CBootstrapCSS;

import jakarta.annotation.Nonnull;

public class BootstrapListGroup extends AbstractHCUL <BootstrapListGroup>
{
  public BootstrapListGroup ()
  {
    addClass (CBootstrapCSS.LIST_GROUP);
  }

  @Override
  @Nonnull
  protected HCLI createEmptyItem ()
  {
    return new HCLI ().addClass (CBootstrapCSS.LIST_GROUP_ITEM);
  }
}
