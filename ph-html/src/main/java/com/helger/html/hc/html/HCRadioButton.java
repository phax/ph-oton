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

import com.helger.html.hc.api.EHCInputType;
import com.helger.html.hc.impl.AbstractHCInput;
import com.helger.html.request.IHCRequestFieldBoolean;
import com.helger.html.request.IHCRequestFieldBooleanMultiValue;

/**
 * Represents an HTML &lt;input&gt; element of type "radio"
 *
 * @author Philip Helger
 */
public class HCRadioButton extends AbstractHCInput <HCRadioButton>
{
  public HCRadioButton (@Nullable final String sName)
  {
    this (sName, null, DEFAULT_CHECKED);
  }

  public HCRadioButton (@Nullable final String sName, @Nullable final String sValue)
  {
    this (sName, sValue, DEFAULT_CHECKED);
  }

  public HCRadioButton (@Nullable final String sName, final boolean bChecked)
  {
    this (sName, null, bChecked);
  }

  public HCRadioButton (@Nullable final String sName, @Nullable final String sValue, final boolean bChecked)
  {
    super (EHCInputType.RADIO);
    setName (sName);
    setValue (sValue);
    setChecked (bChecked);
  }

  public HCRadioButton (@Nonnull final IHCRequestFieldBoolean aRF)
  {
    this (aRF.getFieldName (), null, aRF.isChecked ());
  }

  public HCRadioButton (@Nonnull final IHCRequestFieldBooleanMultiValue aRF)
  {
    this (aRF.getFieldName (), aRF.getValue (), aRF.isChecked ());
  }
}
