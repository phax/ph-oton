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
package com.helger.photon.uicore.systemmsg;

import java.util.Locale;

import com.helger.annotation.Nonempty;
import com.helger.annotation.misc.Translatable;
import com.helger.html.hc.html.IHCElementWithChildren;
import com.helger.photon.core.systemmsg.ISystemMessageRenderer;
import com.helger.photon.uicore.UITextFormatter;
import com.helger.text.IMultilingualText;
import com.helger.text.display.IHasDisplayText;
import com.helger.text.resolve.DefaultTextResolver;
import com.helger.text.util.TextHelper;

import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;

/**
 * An implementation of {@link ISystemMessageRenderer} that renders the text with Markdown.
 *
 * @author Philip Helger
 * @since 8.4.3
 */
public class SystemMessageRendererMarkdown implements ISystemMessageRenderer
{
  @Translatable
  protected enum EText implements IHasDisplayText
  {
    DESCRIPTION ("Die Nachricht wird per Markdown (MD) formatiert", "The message will be rendered with Markdown (MD)");

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

  public static final SystemMessageRendererMarkdown INSTANCE = new SystemMessageRendererMarkdown ();

  protected SystemMessageRendererMarkdown ()
  {}

  @Nonnull
  public String getDisplayText (@Nonnull final Locale aContentLocale)
  {
    return EText.DESCRIPTION.getDisplayText (aContentLocale);
  }

  public void renderSystemMessage (@Nonnull @Nonempty final String sText, final IHCElementWithChildren <?> aTargetCtrl)
  {
    aTargetCtrl.addChild (UITextFormatter.markdown (sText));
  }
}
