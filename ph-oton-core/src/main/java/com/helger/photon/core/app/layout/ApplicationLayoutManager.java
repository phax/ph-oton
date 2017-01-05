/**
 * Copyright (C) 2014-2017 Philip Helger (www.helger.com)
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
package com.helger.photon.core.app.layout;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.concurrent.ThreadSafe;

import com.helger.commons.annotation.Nonempty;
import com.helger.commons.annotation.ReturnsMutableCopy;
import com.helger.commons.annotation.UsedViaReflection;
import com.helger.commons.collection.ext.ICommonsList;
import com.helger.commons.scope.mgr.ScopeManager;
import com.helger.commons.scope.singleton.AbstractApplicationSingleton;
import com.helger.commons.string.ToStringGenerator;
import com.helger.html.hc.IHCNode;
import com.helger.html.hc.html.metadata.HCHead;
import com.helger.photon.core.app.context.ILayoutExecutionContext;

/**
 * This class handles the mapping of the area ID to a content provider.
 *
 * @author Philip Helger
 * @param <LECTYPE>
 *        Layout execution context type
 */
@ThreadSafe
public final class ApplicationLayoutManager <LECTYPE extends ILayoutExecutionContext>
                                            extends AbstractApplicationSingleton implements ILayoutManager <LECTYPE>
{
  private final LayoutManagerProxy <LECTYPE> m_aProxy = new LayoutManagerProxy <LECTYPE> ();

  @UsedViaReflection
  @Deprecated
  public ApplicationLayoutManager ()
  {}

  @Nonnull
  public static <LECTYPE extends ILayoutExecutionContext> ApplicationLayoutManager <LECTYPE> getInstance ()
  {
    return getApplicationSingleton (ApplicationLayoutManager.class);
  }

  @Nonnull
  public static <LECTYPE extends ILayoutExecutionContext> ApplicationLayoutManager <LECTYPE> getInstanceOfScope (@Nonnull @Nonempty final String sApplicationID)
  {
    return getSingleton (ScopeManager.getApplicationScope (sApplicationID), ApplicationLayoutManager.class);
  }

  public void registerAreaContentProvider (@Nonnull final String sAreaID,
                                           @Nonnull final ILayoutAreaContentProvider <LECTYPE> aContentProvider)
  {
    m_aProxy.registerAreaContentProvider (sAreaID, aContentProvider);
  }

  @Nonnull
  @ReturnsMutableCopy
  public ICommonsList <String> getAllAreaIDs ()
  {
    return m_aProxy.getAllAreaIDs ();
  }

  @Nullable
  public IHCNode getContentOfArea (@Nonnull final String sAreaID,
                                   @Nonnull final LECTYPE aLEC,
                                   @Nonnull final HCHead aHead)
  {
    return m_aProxy.getContentOfArea (sAreaID, aLEC, aHead);
  }

  @Override
  public String toString ()
  {
    return ToStringGenerator.getDerived (super.toString ()).append ("proxy", m_aProxy).toString ();
  }
}
