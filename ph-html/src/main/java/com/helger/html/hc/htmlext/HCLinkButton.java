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
package com.helger.html.hc.htmlext;

import javax.annotation.Nonnull;

import com.helger.commons.annotations.DevelopersNote;
import com.helger.commons.url.ISimpleURL;
import com.helger.html.hc.html.AbstractHCButton;
import com.helger.html.js.builder.IJSStatement;
import com.helger.html.js.builder.html.JSHtml;

@DevelopersNote ("Do not use for pDAF3 - missing CSS information. Use HCP3Button instead!")
public class HCLinkButton extends AbstractHCButton <HCLinkButton>
{
  public HCLinkButton (final String sLabel, @Nonnull final ISimpleURL aURL)
  {
    super (sLabel, JSHtml.windowLocationHref (aURL));
  }

  public HCLinkButton (final String sLabel, final IJSStatement aJS)
  {
    super (sLabel, aJS);
  }
}
