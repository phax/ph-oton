/**
 * Copyright (C) 2014-2016 Philip Helger (www.helger.com)
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
package com.helger.photon.bootstrap3.pages;

import javax.annotation.Nullable;
import javax.annotation.concurrent.Immutable;

import com.helger.commons.string.StringHelper;
import com.helger.html.hc.html.sections.HCH1;
import com.helger.photon.bootstrap3.pageheader.BootstrapPageHeader;

/**
 * This class contains static methods to create common UIs.
 *
 * @author Philip Helger
 */
@Immutable
public final class BootstrapUI
{
  private BootstrapUI ()
  {}

  @Nullable
  public static BootstrapPageHeader createPageHeader (@Nullable final String sHeaderText)
  {
    if (StringHelper.hasNoText (sHeaderText))
      return null;
    return new BootstrapPageHeader ().addChild (new HCH1 ().addChild (sHeaderText));
  }
}
