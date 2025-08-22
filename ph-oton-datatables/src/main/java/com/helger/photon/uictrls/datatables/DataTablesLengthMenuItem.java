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
package com.helger.photon.uictrls.datatables;

import java.util.Locale;

import com.helger.annotation.CheckForSigned;
import com.helger.annotation.concurrent.Immutable;
import com.helger.base.enforce.ValueEnforcer;
import com.helger.base.tostring.ToStringGenerator;
import com.helger.text.display.IHasDisplayText;

import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;

@Immutable
public class DataTablesLengthMenuItem implements IHasDisplayText
{
  private final int m_nItemCount;
  private final IHasDisplayText m_aText;

  public DataTablesLengthMenuItem (final int nItemCount, @Nonnull final IHasDisplayText aText)
  {
    m_nItemCount = nItemCount;
    m_aText = ValueEnforcer.notNull (aText, "Text");
  }

  public boolean isAllItem ()
  {
    return m_nItemCount == DataTablesLengthMenu.COUNT_ALL;
  }

  @CheckForSigned
  public int getItemCount ()
  {
    return m_nItemCount;
  }

  @Nonnull
  public IHasDisplayText getText ()
  {
    return m_aText;
  }

  @Nullable
  public String getDisplayText (@Nonnull final Locale aContentLocale)
  {
    return m_aText.getDisplayText (aContentLocale);
  }

  @Override
  @Nonnull
  public String toString ()
  {
    return new ToStringGenerator (this).append ("itemCount", m_nItemCount).append ("text", m_aText).getToString ();
  }
}
