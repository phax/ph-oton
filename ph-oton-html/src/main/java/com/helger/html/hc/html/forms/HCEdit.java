/*
 * Copyright (C) 2014-2023 Philip Helger (www.helger.com)
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

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.helger.html.request.IHCRequestField;

/**
 * Represents an HTML &lt;input&gt; element with type "text"
 *
 * @author Philip Helger
 */
public class HCEdit extends AbstractHCInput <HCEdit>
{
  public HCEdit ()
  {
    super (EHCInputType.TEXT);
  }

  public HCEdit (@Nullable final String sName)
  {
    this ();
    setName (sName);
  }

  public HCEdit (@Nonnull final IHCRequestField aRF)
  {
    this (aRF.getFieldName ());
    setValue (aRF.getRequestValue ());
  }
}
