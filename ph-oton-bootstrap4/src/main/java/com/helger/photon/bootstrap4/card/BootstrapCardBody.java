/*
 * Copyright (C) 2018-2025 Philip Helger (www.helger.com)
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
package com.helger.photon.bootstrap4.card;

import com.helger.html.hc.html.embedded.HCImg;
import com.helger.html.hc.html.grouping.HCP;
import com.helger.html.hc.html.sections.HCH5;
import com.helger.html.hc.html.sections.HCH6;
import com.helger.html.hc.html.textlevel.HCA;
import com.helger.photon.bootstrap4.CBootstrapCSS;
import com.helger.photon.bootstrap4.base.AbstractBootstrapDiv;

import jakarta.annotation.Nonnull;

/**
 * Special Bootstrap 4 card body.
 *
 * @author Philip Helger
 */
public class BootstrapCardBody extends AbstractBootstrapDiv <BootstrapCardBody>
{
  public BootstrapCardBody ()
  {
    addClass (CBootstrapCSS.CARD_BODY);
  }

  @Nonnull
  public static HCImg createImgTop ()
  {
    return new HCImg ().addClass (CBootstrapCSS.CARD_IMG_TOP);
  }

  @Nonnull
  public HCImg createAndAddImgTop ()
  {
    return addAndReturnChild (createImgTop ());
  }

  @Nonnull
  public static HCImg createImgBottom ()
  {
    return new HCImg ().addClass (CBootstrapCSS.CARD_IMG_BOTTOM);
  }

  @Nonnull
  public HCImg createAndAddImgBottom ()
  {
    return addAndReturnChild (createImgBottom ());
  }

  @Nonnull
  public static HCH5 createTitle ()
  {
    return new HCH5 ().addClass (CBootstrapCSS.CARD_TITLE);
  }

  @Nonnull
  public HCH5 createAndAddTitle ()
  {
    return addAndReturnChild (createTitle ());
  }

  @Nonnull
  public static HCH6 createSubtitle ()
  {
    return new HCH6 ().addClass (CBootstrapCSS.CARD_SUBTITLE);
  }

  @Nonnull
  public HCH6 createAndAddSubtitle ()
  {
    return addAndReturnChild (createSubtitle ());
  }

  @Nonnull
  public static HCP createText ()
  {
    return new HCP ().addClass (CBootstrapCSS.CARD_TEXT);
  }

  @Nonnull
  public HCP createAndAddText ()
  {
    return addAndReturnChild (createText ());
  }

  @Nonnull
  public static HCA createLink ()
  {
    return new HCA ().addClass (CBootstrapCSS.CARD_LINK);
  }

  @Nonnull
  public HCA createAndAddLink ()
  {
    return addAndReturnChild (createLink ());
  }
}
