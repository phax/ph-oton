/*
 * Copyright (C) 2014-2022 Philip Helger (www.helger.com)
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
package com.helger.photon.uictrls.datatables;

import javax.annotation.Nonnull;

import com.helger.commons.annotation.Nonempty;
import com.helger.commons.annotation.Since;
import com.helger.commons.name.IHasName;

/**
 * DataTables pagination type
 *
 * @author Philip Helger
 */
public enum EDataTablesPagingType implements IHasName
{
  @Since ("1.10.8")
  NUMBERS("numbers"),
  SIMPLE ("simple"),
  SIMPLE_NUMBERS ("simple_numbers"),
  FULL ("full"),
  FULL_NUMBERS ("full_numbers"),
  BOOTSTRAP ("bootstrap");

  private final String m_sName;

  EDataTablesPagingType (@Nonnull @Nonempty final String sName)
  {
    m_sName = sName;
  }

  @Nonnull
  @Nonempty
  public String getName ()
  {
    return m_sName;
  }
}
