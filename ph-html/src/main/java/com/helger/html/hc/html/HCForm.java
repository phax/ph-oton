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
package com.helger.html.hc.html;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.helger.commons.url.ISimpleURL;
import com.helger.html.js.builder.IJSStatement;

/**
 * Represents an HTML &lt;form&gt; element
 *
 * @author Philip Helger
 */
public class HCForm extends AbstractHCForm <HCForm>
{
  public HCForm ()
  {
    super ();
  }

  public HCForm (@Nullable final String sAction)
  {
    super (sAction);
  }

  public HCForm (@Nonnull final ISimpleURL aAction)
  {
    super (aAction);
  }

  public HCForm (@Nullable final IJSStatement aAction)
  {
    super (aAction);
  }
}
