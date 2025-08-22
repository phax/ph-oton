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
package com.helger.html.hc.html.grouping;

import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;

/**
 * Interface for OLs
 *
 * @author Philip Helger
 * @param <IMPLTYPE>
 *        Implementation type
 * @param <ITEMTYPE>
 *        Item type
 */
public interface IHCOLBase <IMPLTYPE extends IHCOLBase <IMPLTYPE, ITEMTYPE>, ITEMTYPE extends IHCLI <ITEMTYPE>> extends
                           IHCList <IMPLTYPE, ITEMTYPE>
{
  @Nullable
  Integer getStart ();

  @Nonnull
  default IMPLTYPE setStart (final int nStart)
  {
    return setStart (Integer.valueOf (nStart));
  }

  @Nonnull
  IMPLTYPE setStart (@Nullable Integer aStart);

  boolean isReversed ();

  @Nonnull
  IMPLTYPE setReversed (boolean bReversed);

  @Nullable
  EHCOLType getType ();

  @Nonnull
  IMPLTYPE setType (@Nullable EHCOLType eType);
}
