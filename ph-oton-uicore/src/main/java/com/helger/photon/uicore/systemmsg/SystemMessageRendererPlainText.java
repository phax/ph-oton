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
package com.helger.photon.uicore.systemmsg;

import java.util.Locale;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.helger.commons.annotation.Nonempty;
import com.helger.commons.annotation.Translatable;
import com.helger.commons.text.IMultilingualText;
import com.helger.commons.text.display.IHasDisplayText;
import com.helger.commons.text.resolve.DefaultTextResolver;
import com.helger.commons.text.util.TextHelper;
import com.helger.html.hc.ext.HCExtHelper;
import com.helger.html.hc.html.IHCElementWithChildren;
import com.helger.photon.core.systemmsg.ISystemMessageRenderer;

/**
 * An implementation of {@link ISystemMessageRenderer} that renders the text as
 * plain text.
 *
 * @author Philip Helger
 * @since 8.4.3
 */
public class SystemMessageRendererPlainText implements ISystemMessageRenderer
{
  @Translatable
  protected enum EText implements IHasDisplayText
  {
    DESCRIPTION ("Die Nachricht wird ohne Formatierung dargestellt", "The message will be rendered as plain text");

    private final IMultilingualText m_aTP;

    EText (@Nonnull final String sDE, @Nonnull final String sEN)
    {
      m_aTP = TextHelper.create_DE_EN (sDE, sEN);
    }

    @Nullable
    public String getDisplayText (final Locale aContentLocale)
    {
      return DefaultTextResolver.getTextStatic (this, m_aTP, aContentLocale);
    }
  }

  public static final SystemMessageRendererPlainText INSTANCE = new SystemMessageRendererPlainText ();

  protected SystemMessageRendererPlainText ()
  {}

  @Nonnull
  public String getDisplayText (@Nonnull final Locale aContentLocale)
  {
    return EText.DESCRIPTION.getDisplayText (aContentLocale);
  }

  public void renderSystemMessage (@Nonnull @Nonempty final String sText, final IHCElementWithChildren <?> aTargetCtrl)
  {
    aTargetCtrl.addChildren (HCExtHelper.nl2divList (sText));
  }
}
