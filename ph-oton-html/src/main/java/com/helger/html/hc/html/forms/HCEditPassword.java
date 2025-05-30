/*
 * Copyright (C) 2014-2025 Philip Helger (www.helger.com)
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

import com.helger.html.hc.config.HCSettings;

/**
 * Represents an HTML &lt;input&gt; element with type "password"
 *
 * @author Philip Helger
 */
public class HCEditPassword extends AbstractHCInput <HCEditPassword>
{
  public HCEditPassword ()
  {
    super (EHCInputType.PASSWORD);
    if (HCSettings.isAutoCompleteOffForPasswordEdits ())
      setAutoComplete (EHCAutoComplete.OFF);
  }

  public HCEditPassword (@Nullable final String sName)
  {
    this ();
    setName (sName);
  }
}
