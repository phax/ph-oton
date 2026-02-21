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
package com.helger.photon.mgrs;

import java.util.Locale;

import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.Nullable;

import com.helger.annotation.misc.Translatable;
import com.helger.text.IMultilingualText;
import com.helger.text.display.IHasDisplayTextWithArgs;
import com.helger.text.resolve.DefaultTextResolver;
import com.helger.text.util.TextHelper;

/**
 * Contains some global texts.
 *
 * @author Philip Helger
 * @since 10.2.0
 */
@Translatable
public enum EPhotonManagersText implements IHasDisplayTextWithArgs
{
  // System messages
  SYSTEM_MESSAGE_TYPE_INFO ("Information", "Information"),
  SYSTEM_MESSAGE_TYPE_WARNING ("Warnung", "Warning"),
  SYSTEM_MESSAGE_TYPE_ERROR ("Fehler", "Error"),
  SYSTEM_MESSAGE_TYPE_SUCCESS ("Erfolg", "Success");

  private final IMultilingualText m_aTP;

  EPhotonManagersText (final String sDE, final String sEN)
  {
    m_aTP = TextHelper.create_DE_EN (sDE, sEN);
  }

  @Nullable
  public String getDisplayText (@NonNull final Locale aContentLocale)
  {
    return DefaultTextResolver.getTextStatic (this, m_aTP, aContentLocale);
  }

  @NonNull
  public IMultilingualText getMultilingualText ()
  {
    return m_aTP;
  }
}
