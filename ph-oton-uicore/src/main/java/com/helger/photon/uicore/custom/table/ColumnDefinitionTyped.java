/**
 * Copyright (C) 2014-2015 Philip Helger (www.helger.com)
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
package com.helger.photon.uicore.custom.table;

import javax.annotation.Nonnull;
import javax.annotation.concurrent.Immutable;

import com.helger.commons.ValueEnforcer;
import com.helger.commons.string.ToStringGenerator;
import com.helger.html.hc.html.HCCol;

@Immutable
public final class ColumnDefinitionTyped extends ColumnDefinition
{
  private final Class <?> m_aType;

  public ColumnDefinitionTyped (@Nonnull final String sName,
                                @Nonnull final HCCol aCol,
                                @Nonnull final String sFieldID,
                                @Nonnull final Class <?> aType)
  {
    super (sName, aCol, sFieldID);
    // field ID cannot be empty for typed columns
    ValueEnforcer.notEmpty (sFieldID, "FieldID");
    ValueEnforcer.notNull (aType, "Type");
    m_aType = aType;
  }

  @Nonnull
  public Class <?> getType ()
  {
    return m_aType;
  }

  @Override
  public String toString ()
  {
    return ToStringGenerator.getDerived (super.toString ()).append ("type", m_aType).toString ();
  }
}
