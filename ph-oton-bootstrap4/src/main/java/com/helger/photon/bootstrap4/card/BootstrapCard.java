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
package com.helger.photon.bootstrap4.card;

import javax.annotation.Nonnull;

import com.helger.commons.annotation.ReturnsMutableCopy;
import com.helger.html.hc.html.grouping.AbstractHCDiv;
import com.helger.photon.bootstrap4.CBootstrapCSS;

/**
 * Special Bootstrap 4 card.
 *
 * @author Philip Helger
 */
public class BootstrapCard extends AbstractHCDiv <BootstrapCard>
{
  public BootstrapCard ()
  {
    addClass (CBootstrapCSS.CARD);
  }

  @Nonnull
  @ReturnsMutableCopy
  public BootstrapCardHeader createAndAddHeader ()
  {
    return addAndReturnChild (new BootstrapCardHeader ());
  }

  @Nonnull
  @ReturnsMutableCopy
  public BootstrapCardBody createAndAddBody ()
  {
    return addAndReturnChild (new BootstrapCardBody ());
  }

  @Nonnull
  @ReturnsMutableCopy
  public BootstrapCardFooter createAndAddFooter ()
  {
    return addAndReturnChild (new BootstrapCardFooter ());
  }
}
