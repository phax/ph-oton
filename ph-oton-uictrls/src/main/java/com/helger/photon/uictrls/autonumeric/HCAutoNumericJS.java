/**
 * Copyright (C) 2014-2021 Philip Helger (www.helger.com)
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
package com.helger.photon.uictrls.autonumeric;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.helger.commons.annotation.MustImplementEqualsAndHashcode;
import com.helger.commons.hashcode.HashCodeGenerator;
import com.helger.commons.string.ToStringGenerator;
import com.helger.html.annotation.OutOfBandNode;
import com.helger.html.hc.html.script.HCScriptInlineOnDocumentReady;
import com.helger.html.hc.special.SpecialNodeListModifier;
import com.helger.html.jquery.JQuery;
import com.helger.html.jquery.JQueryInvocation;
import com.helger.html.js.IHasJSCode;

/**
 * A special script that initializes the auto numeric. It is a separate class,
 * so that potentially identical options can be merged to a single invocation.
 * <br>
 * Must implement equals and hashcode to be able to get removed "stateless" from
 * a node
 *
 * @author Philip Helger
 */
@OutOfBandNode
@MustImplementEqualsAndHashcode
@SpecialNodeListModifier (HCAutoNumericSpecialNodeListModifier.class)
public class HCAutoNumericJS extends HCScriptInlineOnDocumentReady
{
  private final AbstractHCAutoNumeric <?> m_aAutoNumeric;

  @Nonnull
  public static IHasJSCode createInitCode (@Nullable final JQueryInvocation aExplicitAutoNumeric,
                                           @Nonnull final AbstractHCAutoNumeric <?> aAutoNumeric)
  {
    final JQueryInvocation aInvocation = aExplicitAutoNumeric != null ? aExplicitAutoNumeric : JQuery.idRef (aAutoNumeric);

    return HCAutoNumeric.autoNumericInit (aInvocation, aAutoNumeric.getJSOptions ());
  }

  public HCAutoNumericJS (@Nonnull final AbstractHCAutoNumeric <?> aAutoNumeric)
  {
    super (createInitCode (null, aAutoNumeric));
    m_aAutoNumeric = aAutoNumeric;
  }

  @Nonnull
  public AbstractHCAutoNumeric <?> getAutoNumeric ()
  {
    return m_aAutoNumeric;
  }

  @Override
  public boolean equals (final Object o)
  {
    if (o == this)
      return true;
    if (o == null || !getClass ().equals (o.getClass ()))
      return false;
    final HCAutoNumericJS rhs = (HCAutoNumericJS) o;
    return m_aAutoNumeric.equals (rhs.m_aAutoNumeric);
  }

  @Override
  public int hashCode ()
  {
    return new HashCodeGenerator (this).append (m_aAutoNumeric).getHashCode ();
  }

  @Override
  public String toString ()
  {
    return new ToStringGenerator (this).append ("autoNumeric", m_aAutoNumeric).getToString ();
  }
}
