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

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.helger.html.request.IHCRequestField;

/**
 * Represents an HTML &lt;input&gt; element with type "hidden"
 *
 * @author Philip Helger
 */
public class HCHiddenField extends AbstractHCInput <HCHiddenField>
{
  public HCHiddenField ()
  {
    super (EHCInputType.HIDDEN);
  }

  public HCHiddenField (@Nullable final String sName, @Nullable final String sValue)
  {
    this ();
    setName (sName);
    setValue (sValue);
  }

  public HCHiddenField (@Nullable final String sName, final int nValue)
  {
    this ();
    setName (sName);
    setValue (nValue);
  }

  public HCHiddenField (@Nullable final String sName, final long nValue)
  {
    this ();
    setName (sName);
    setValue (nValue);
  }

  public HCHiddenField (@Nullable final String sName, final boolean bValue)
  {
    this (sName, Boolean.toString (bValue));
  }

  public HCHiddenField (@Nonnull final IHCRequestField aRF)
  {
    this (aRF.getFieldName (), aRF.getRequestValue ());
  }
}
