/*
 * Copyright (C) 2014-2026 Philip Helger (www.helger.com)
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

import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.Nullable;

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

  @NonNull
  default IMPLTYPE setStart (final int nStart)
  {
    return setStart (Integer.valueOf (nStart));
  }

  @NonNull
  IMPLTYPE setStart (@Nullable Integer aStart);

  boolean isReversed ();

  @NonNull
  IMPLTYPE setReversed (boolean bReversed);

  @Nullable
  EHCOLType getType ();

  @NonNull
  IMPLTYPE setType (@Nullable EHCOLType eType);
}
