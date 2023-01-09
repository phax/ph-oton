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
package com.helger.photon.bootstrap3.badge;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.helger.html.hc.IHCNode;
import com.helger.html.hc.html.textlevel.AbstractHCSpan;
import com.helger.photon.bootstrap3.CBootstrapCSS;

/**
 * Bootstrap3 badge.
 *
 * @author Philip Helger
 */
public class BootstrapBadge extends AbstractHCSpan <BootstrapBadge>
{
  public BootstrapBadge ()
  {
    addClass (CBootstrapCSS.BADGE);
  }

  @Nullable
  public static BootstrapBadge createOnDemand (@Nullable final IHCNode aNode)
  {
    return aNode == null ? null : new BootstrapBadge ().addChild (aNode);
  }

  @Nonnull
  public static BootstrapBadge createNumeric (final int nValue)
  {
    return new BootstrapBadge ().addChild (Integer.toString (nValue));
  }

  @Nonnull
  public static BootstrapBadge createNumeric (final long nValue)
  {
    return new BootstrapBadge ().addChild (Long.toString (nValue));
  }
}
