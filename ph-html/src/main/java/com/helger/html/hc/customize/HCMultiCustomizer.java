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
package com.helger.html.hc.customize;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.concurrent.NotThreadSafe;

import com.helger.commons.ValueEnforcer;
import com.helger.commons.annotations.ReturnsMutableCopy;
import com.helger.commons.collections.CollectionHelper;
import com.helger.commons.state.EChange;
import com.helger.commons.string.ToStringGenerator;
import com.helger.html.EHTMLVersion;
import com.helger.html.hc.IHCHasChildrenMutable;
import com.helger.html.hc.IHCNode;
import com.helger.html.hc.html.HCBody;
import com.helger.html.hc.html.HCHead;

/**
 * An implementation of {@link IHCCustomizer} that handles multiple customizers.
 *
 * @author Philip Helger
 */
@NotThreadSafe
public class HCMultiCustomizer implements IHCCustomizer
{
  private final List <IHCCustomizer> m_aCustomizers = new ArrayList <IHCCustomizer> ();

  public HCMultiCustomizer (@Nullable final IHCCustomizer... aCustomizers)
  {
    if (aCustomizers != null)
      for (final IHCCustomizer aCustomizer : aCustomizers)
        addCustomizer (aCustomizer);
  }

  public HCMultiCustomizer (@Nullable final Iterable <? extends IHCCustomizer> aCustomizers)
  {
    if (aCustomizers != null)
      for (final IHCCustomizer aCustomizer : aCustomizers)
        addCustomizer (aCustomizer);
  }

  @Nonnull
  public HCMultiCustomizer addCustomizer (@Nonnull final IHCCustomizer aCustomizer)
  {
    ValueEnforcer.notNull (aCustomizer, "Customizer");
    m_aCustomizers.add (aCustomizer);
    return this;
  }

  @Nonnull
  public EChange removeCustomizer (@Nullable final IHCCustomizer aCustomizer)
  {
    return EChange.valueOf (m_aCustomizers.add (aCustomizer));
  }

  @Nonnegative
  public int getCustomizerCount ()
  {
    return m_aCustomizers.size ();
  }

  @Nonnull
  @ReturnsMutableCopy
  public List <IHCCustomizer> getAllCustomizers ()
  {
    return CollectionHelper.newList (m_aCustomizers);
  }

  public void customizeNode (@Nonnull final IHCHasChildrenMutable <?, ? super IHCNode> aParentElement,
                             @Nonnull final IHCNode aNode,
                             @Nonnull final EHTMLVersion eHTMLVersion)
  {
    for (final IHCCustomizer aCustomizer : m_aCustomizers)
      aCustomizer.customizeNode (aParentElement, aNode, eHTMLVersion);
  }

  public void handleOutOfBandNodes (@Nonnull final List <IHCNode> aOutOfBandNodes,
                                    @Nonnull final HCHead aHead,
                                    @Nonnull final HCBody aBody)
  {
    for (final IHCCustomizer aCustomizer : m_aCustomizers)
      aCustomizer.handleOutOfBandNodes (aOutOfBandNodes, aHead, aBody);
  }

  @Override
  public String toString ()
  {
    return new ToStringGenerator (this).append ("customizers", m_aCustomizers).toString ();
  }
}
