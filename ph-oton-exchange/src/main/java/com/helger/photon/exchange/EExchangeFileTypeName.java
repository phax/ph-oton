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
package com.helger.photon.exchange;

import java.util.Locale;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.helger.commons.annotation.Translatable;
import com.helger.commons.text.IMultilingualText;
import com.helger.commons.text.display.IHasDisplayText;
import com.helger.commons.text.resolve.DefaultTextResolver;
import com.helger.commons.text.util.TextHelper;

/**
 * Exchange file type names.
 *
 * @author Philip Helger
 */
@Translatable
public enum EExchangeFileTypeName implements IHasDisplayText
{
  /** CSV - comma separated values */
  CSV ("CSV", "CSV"),
  /** Old Excel */
  XLS ("Excel 2003 (XLS)", "Excel 2003 (XLS)"),
  /** New Excel */
  XLSX ("Excel 2007 (XLSX)", "Excel 2007 (XLSX)"),
  /** XML structured text */
  XML ("XML", "XML"),
  /** Pure text file */
  TXT ("Text", "Text"),
  /** JSON file */
  JSON ("JSON", "JSON"),
  /** JSON file without types */
  JSON_SIMPLE ("JSON (einfach)", "JSON (simple)");

  private final IMultilingualText m_aTP;

  EExchangeFileTypeName (@Nonnull final String sDE, @Nonnull final String sEN)
  {
    m_aTP = TextHelper.create_DE_EN (sDE, sEN);
  }

  @Nullable
  public String getDisplayText (@Nonnull final Locale aContentLocale)
  {
    return DefaultTextResolver.getTextStatic (this, m_aTP, aContentLocale);
  }
}
