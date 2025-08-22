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
package com.helger.photon.uictrls.chart.v4;

import com.helger.annotation.Nonempty;
import com.helger.annotation.OverridingMethodsMustInvokeSuper;
import com.helger.annotation.concurrent.NotThreadSafe;
import com.helger.annotation.style.ReturnsMutableCopy;
import com.helger.base.enforce.ValueEnforcer;
import com.helger.base.state.ETriState;
import com.helger.base.tostring.ToStringGenerator;
import com.helger.base.traits.IGenericImplTrait;
import com.helger.html.jscode.JSAssocArray;

import jakarta.annotation.Nonnull;

/**
 * Abstract chart
 *
 * @author Philip Helger
 * @param <IMPLTYPE>
 *        Real implementation type
 */
@NotThreadSafe
public abstract class AbstractChartV4 <IMPLTYPE extends AbstractChartV4 <IMPLTYPE>> implements
                                      IChartV4,
                                      IGenericImplTrait <IMPLTYPE>
{
  private final String m_sType;
  private ETriState m_eAnimations = ETriState.UNDEFINED;

  protected AbstractChartV4 (@Nonnull @Nonempty final String sType)
  {
    ValueEnforcer.notEmpty (sType, "Type");
    m_sType = sType;
  }

  @Nonnull
  @Nonempty
  public final String getType ()
  {
    return m_sType;
  }

  public final boolean isUseAnimations ()
  {
    return m_eAnimations.getAsBooleanValue (true);
  }

  @Nonnull
  public final IMPLTYPE setUseAnimations (final boolean b)
  {
    m_eAnimations = ETriState.valueOf (b);
    return thisAsT ();
  }

  @Nonnull
  @ReturnsMutableCopy
  @OverridingMethodsMustInvokeSuper
  public JSAssocArray getJSOptions ()
  {
    final JSAssocArray ret = new JSAssocArray ();
    if (m_eAnimations.isDefined ())
      ret.add ("animation", m_eAnimations.getAsBooleanValue ());
    return ret;
  }

  @Override
  public String toString ()
  {
    return new ToStringGenerator (null).append ("Type", m_sType).append ("Animations", m_eAnimations).getToString ();
  }
}
