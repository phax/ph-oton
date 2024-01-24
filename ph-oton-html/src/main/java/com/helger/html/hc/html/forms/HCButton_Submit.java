/*
 * Copyright (C) 2014-2024 Philip Helger (www.helger.com)
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
package com.helger.html.hc.html.forms;

import javax.annotation.Nullable;

/**
 * Represents an HTML &lt;button&gt; element with type "submit"
 *
 * @author Philip Helger
 */
public class HCButton_Submit extends AbstractHCButton <HCButton_Submit>
{
  private void _init ()
  {
    setType (EHCButtonType.SUBMIT);
  }

  public HCButton_Submit ()
  {
    _init ();
  }

  public HCButton_Submit (@Nullable final String sLabel)
  {
    super (sLabel);
    _init ();
  }
}
