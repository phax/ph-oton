/*
 * Copyright (C) 2018-2025 Philip Helger (www.helger.com)
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
package com.helger.photon.bootstrap4.uictrls.datetimepicker;

import com.helger.base.tostring.ToStringGenerator;
import com.helger.html.annotation.OutOfBandNode;
import com.helger.html.hc.html.script.AbstractHCScriptInline;
import com.helger.html.hc.special.SpecialNodeListModifier;

import jakarta.annotation.Nonnull;

/**
 * A special script that initializes the {@link BootstrapDateTimePicker}. It is
 * a separate class, so that potentially identical options can be merged to a
 * single invocation.
 *
 * @author Philip Helger
 */
@OutOfBandNode
@SpecialNodeListModifier (Bootstrap4DateTimePickerSpecialNodeListModifier.class)
public class Bootstrap4DateTimePickerJS extends AbstractHCScriptInline <Bootstrap4DateTimePickerJS>
{
  private final BootstrapDateTimePicker m_aDTP;

  public Bootstrap4DateTimePickerJS (@Nonnull final BootstrapDateTimePicker aDTP)
  {
    super (aDTP.invoke (aDTP.getJSOptions ()));
    m_aDTP = aDTP;
  }

  @Nonnull
  public BootstrapDateTimePicker getDateTimePicker ()
  {
    return m_aDTP;
  }

  @Override
  public String toString ()
  {
    return ToStringGenerator.getDerived (super.toString ()).append ("DTP", m_aDTP).getToString ();
  }
}
