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
package com.helger.photon.uictrls.datatables.comparator;

import java.util.function.Function;

import javax.annotation.Nullable;

import com.helger.commons.version.Version;

/**
 * This comparator is responsible for sorting cells by {@link Version}
 *
 * @author Philip Helger
 */
public class ComparatorDTVersion extends AbstractComparatorDT <Version>
{
  public ComparatorDTVersion ()
  {
    this (null);
  }

  public ComparatorDTVersion (@Nullable final Function <? super String, String> aFormatter)
  {
    super (aFormatter, sCellText -> new Version (sCellText));
  }
}
