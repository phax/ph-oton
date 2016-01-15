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
package com.helger.photon.uicore.html.table;

import javax.annotation.Nullable;

import com.helger.commons.version.Version;

/**
 * This comparator is responsible for sorting cells by {@link Version}
 *
 * @author Philip Helger
 */
public class ComparatorCellVersion extends AbstractComparatorCell <Version>
{
  public ComparatorCellVersion ()
  {
    this (null, null);
  }

  public ComparatorCellVersion (@Nullable final String sCommonPrefix, @Nullable final String sCommonSuffix)
  {
    super (aCell -> new Version (getCellText (aCell, sCommonPrefix, sCommonSuffix)));
  }
}
