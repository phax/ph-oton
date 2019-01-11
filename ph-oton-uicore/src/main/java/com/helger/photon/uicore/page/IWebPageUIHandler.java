/**
 * Copyright (C) 2014-2019 Philip Helger (www.helger.com)
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
package com.helger.photon.uicore.page;

import java.io.Serializable;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.helger.commons.string.StringHelper;
import com.helger.html.hc.html.IHCElementWithChildren;
import com.helger.html.hc.html.grouping.HCDiv;
import com.helger.photon.uicore.css.CUICoreCSS;

public interface IWebPageUIHandler extends Serializable
{
  @Nullable
  default IHCElementWithChildren <?> createActionHeader ()
  {
    return new HCDiv ().addClass (CUICoreCSS.CSS_CLASS_ACTION_HEADER);
  }

  @Nullable
  default IHCElementWithChildren <?> createActionHeader (@Nullable final String sText)
  {
    if (StringHelper.hasNoText (sText))
      return null;
    return createActionHeader ().addChild (sText);
  }

  @Nonnull
  default IHCElementWithChildren <?> createDataGroupHeader ()
  {
    return new HCDiv ().addClass (CUICoreCSS.CSS_CLASS_DATAGROUP_HEADER);
  }

  @Nullable
  default IHCElementWithChildren <?> createDataGroupHeader (@Nullable final String sText)
  {
    if (StringHelper.hasNoText (sText))
      return null;
    return createDataGroupHeader ().addChild (sText);
  }
}
