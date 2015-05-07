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
package com.helger.bootstrap3.embed;

import javax.annotation.Nonnull;

import com.helger.bootstrap3.CBootstrapCSS;
import com.helger.html.hc.html.AbstractHCDiv;
import com.helger.html.hc.html.HCEmbed;
import com.helger.html.hc.html.HCIFrame;
import com.helger.html.hc.html.HCObject;

/**
 * Bootstrap responsive embed. Available from Bootstrap 3.2.0 on. It works with
 * {@link HCObject}, {@link HCIFrame} and {@link HCEmbed}.
 *
 * @author Philip Helger
 */
public class BootstrapEmbed extends AbstractHCDiv <BootstrapEmbed>
{
  public BootstrapEmbed (@Nonnull final EBootstrapEmbedType eType, @Nonnull final HCObject aObject)
  {
    addClasses (CBootstrapCSS.EMBED_RESPONSIVE, eType);
    addChild (aObject.addClass (CBootstrapCSS.EMBED_RESPONSIVE_ITEM));
  }

  public BootstrapEmbed (@Nonnull final EBootstrapEmbedType eType, @Nonnull final HCIFrame aIFrame)
  {
    addClasses (CBootstrapCSS.EMBED_RESPONSIVE, eType);
    addChild (aIFrame.addClass (CBootstrapCSS.EMBED_RESPONSIVE_ITEM));
  }

  public BootstrapEmbed (@Nonnull final EBootstrapEmbedType eType, @Nonnull final HCEmbed aEmbed)
  {
    addClasses (CBootstrapCSS.EMBED_RESPONSIVE, eType);
    addChild (aEmbed.addClass (CBootstrapCSS.EMBED_RESPONSIVE_ITEM));
  }
}
