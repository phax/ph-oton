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
package com.helger.photon.bootstrap.demo.pub.page.icon;

import com.helger.annotation.Nonempty;
import com.helger.html.hc.impl.HCNodeList;
import com.helger.photon.icon.fontawesome.EFontAwesome4Icon;
import com.helger.photon.uicore.page.WebPageExecutionContext;

import jakarta.annotation.Nonnull;

/**
 * Show all Font Awesome icons in action
 *
 * @author Philip Helger
 */
public final class PagePublicIconFontAwesome4 extends AbstractPagePublicIcon
{
  public PagePublicIconFontAwesome4 (@Nonnull @Nonempty final String sID)
  {
    super (sID, "Font Awesome v4 Icon Test");
  }

  @Override
  protected void fillIcons (final WebPageExecutionContext aWPEC)
  {
    final HCNodeList aNodeList = aWPEC.getNodeList ();

    EFontAwesome4Icon.registerResourcesForThisRequest ();
    for (final EFontAwesome4Icon eIcon : EFontAwesome4Icon.values ())
      aNodeList.addChild (div ().addClass (CSS_CLASS_ICON_CONTAINER)
                                .addChild (div (eIcon.getAsNode4x ()))
                                .addChild (div (small (eIcon.name ()))));
  }
}
