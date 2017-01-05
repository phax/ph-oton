/**
 * Copyright (C) 2014-2017 Philip Helger (www.helger.com)
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
package com.helger.html.hc.ext;

import javax.annotation.Nonnull;

import com.helger.commons.url.ISimpleURL;
import com.helger.html.hc.html.FakeJS;
import com.helger.html.hc.html.forms.AbstractHCButton;
import com.helger.html.js.IHasJSCodeWithSettings;

/**
 * A special &lt;button&gt; that changes the location of the current page.
 *
 * @author Philip Helger
 */
public class HCLinkButton extends AbstractHCButton <HCLinkButton>
{
  public HCLinkButton (final String sLabel, @Nonnull final ISimpleURL aURL)
  {
    super (sLabel, FakeJS.windowLocationHref (aURL));
  }

  public HCLinkButton (final String sLabel, final IHasJSCodeWithSettings aJS)
  {
    super (sLabel, aJS);
  }
}
