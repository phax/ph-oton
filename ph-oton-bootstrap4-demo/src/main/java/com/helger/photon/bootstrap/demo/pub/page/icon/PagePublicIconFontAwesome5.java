/*
 * Copyright (C) 2018-2021 Philip Helger (www.helger.com)
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

import javax.annotation.Nonnull;

import com.helger.commons.annotation.Nonempty;
import com.helger.html.hc.impl.HCNodeList;
import com.helger.photon.icon.fontawesome.EFontAwesome5Icon;
import com.helger.photon.uicore.page.WebPageExecutionContext;

/**
 * Show all Font Awesome icons in action
 *
 * @author Philip Helger
 */
public final class PagePublicIconFontAwesome5 extends AbstractPagePublicIcon
{
  public PagePublicIconFontAwesome5 (@Nonnull @Nonempty final String sID)
  {
    super (sID, "Font Awesome v5 Icon Test");
  }

  @Override
  protected void fillIcons (final WebPageExecutionContext aWPEC)
  {
    final HCNodeList aNodeList = aWPEC.getNodeList ();

    EFontAwesome5Icon.registerResourcesForThisRequest ();
    for (final EFontAwesome5Icon eIcon : EFontAwesome5Icon.values ())
      aNodeList.addChild (div ().addClass (CSS_CLASS_ICON_CONTAINER)
                                .addChild (div (eIcon.getAsNode4x ()))
                                .addChild (div (small (eIcon.name ()))));
  }
}
