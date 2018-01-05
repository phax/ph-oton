/**
 * Copyright (C) 2014-2018 Philip Helger (www.helger.com)
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
package com.helger.photon.bootstrap3.pageheader;

import javax.annotation.Nullable;

import com.helger.html.hc.IHCNode;
import com.helger.html.hc.html.grouping.AbstractHCDiv;
import com.helger.photon.bootstrap3.CBootstrapCSS;

/**
 * Bootstrap3 page header.
 *
 * @author Philip Helger
 */
public class BootstrapPageHeader extends AbstractHCDiv <BootstrapPageHeader>
{
  public BootstrapPageHeader ()
  {
    super ();
    addClass (CBootstrapCSS.PAGE_HEADER);
  }

  @Nullable
  public static BootstrapPageHeader createOnDemand (@Nullable final IHCNode aNode)
  {
    return aNode == null ? null : new BootstrapPageHeader ().addChild (aNode);
  }
}
