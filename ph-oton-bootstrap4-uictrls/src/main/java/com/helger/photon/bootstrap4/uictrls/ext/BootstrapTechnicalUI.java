/*
 * Copyright (C) 2018-2023 Philip Helger (www.helger.com)
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
package com.helger.photon.bootstrap4.uictrls.ext;

import java.util.Locale;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.concurrent.Immutable;

import com.helger.commons.annotation.Translatable;
import com.helger.commons.lang.ClassHelper;
import com.helger.commons.string.StringHelper;
import com.helger.commons.text.IMultilingualText;
import com.helger.commons.text.display.IHasDisplayTextWithArgs;
import com.helger.commons.text.resolve.DefaultTextResolver;
import com.helger.commons.text.util.TextHelper;
import com.helger.html.hc.html.grouping.HCDiv;
import com.helger.html.hc.impl.HCNodeList;

/**
 * UI helper class for technical details.
 *
 * @author Philip Helger
 * @since 8.2.2
 */
@Immutable
public final class BootstrapTechnicalUI
{
  @Translatable
  public enum EText implements IHasDisplayTextWithArgs
  {
    TECHNICAL_DETAILS ("Technische Details", "Technical details"),
    CAUSED_BY ("Verursacht von", "Caused by");

    private final IMultilingualText m_aTP;

    EText (final String sDE, final String sEN)
    {
      m_aTP = TextHelper.create_DE_EN (sDE, sEN);
    }

    @Nullable
    public String getDisplayText (@Nonnull final Locale aContentLocale)
    {
      return DefaultTextResolver.getTextStatic (this, m_aTP, aContentLocale);
    }
  }

  private BootstrapTechnicalUI ()
  {}

  @Nonnull
  private static String _getString (@Nonnull final Throwable t)
  {
    return StringHelper.getConcatenatedOnDemand (ClassHelper.getClassLocalName (t.getClass ()), " - ", t.getMessage ());
  }

  @Nullable
  public static HCNodeList getTechnicalDetailsNode (@Nullable final Throwable t, @Nonnull final Locale aDisplayLocale)
  {
    if (t == null)
      return null;

    final HCNodeList ret = new HCNodeList ();
    Throwable aCur = t;
    while (aCur != null)
    {
      if (ret.hasNoChildren ())
        ret.addChild (new HCDiv ().addChild (EText.TECHNICAL_DETAILS.getDisplayText (aDisplayLocale) + ": " + _getString (aCur)));
      else
        ret.addChild (new HCDiv ().addChild (EText.CAUSED_BY.getDisplayText (aDisplayLocale) + ": " + _getString (aCur)));
      aCur = aCur.getCause ();
    }
    return ret;
  }

  @Nullable
  public static String getTechnicalDetailsString (@Nullable final Throwable t, @Nonnull final Locale aDisplayLocale)
  {
    if (t == null)
      return null;

    final StringBuilder ret = new StringBuilder ();
    Throwable aCur = t;
    while (aCur != null)
    {
      if (ret.length () == 0)
        ret.append (EText.TECHNICAL_DETAILS.getDisplayText (aDisplayLocale));
      else
        ret.append ('\n').append (EText.CAUSED_BY.getDisplayText (aDisplayLocale));
      ret.append (": ").append (_getString (aCur));
      aCur = aCur.getCause ();
    }
    return ret.toString ();
  }
}
