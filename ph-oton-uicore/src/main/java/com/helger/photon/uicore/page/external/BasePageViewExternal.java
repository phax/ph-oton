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
package com.helger.photon.uicore.page.external;

import java.util.function.Consumer;

import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.Nullable;

import com.helger.annotation.Nonempty;
import com.helger.annotation.OverridingMethodsMustInvokeSuper;
import com.helger.annotation.concurrent.GuardedBy;
import com.helger.annotation.concurrent.ThreadSafe;
import com.helger.annotation.style.ReturnsMutableCopy;
import com.helger.base.enforce.ValueEnforcer;
import com.helger.base.tostring.ToStringGenerator;
import com.helger.html.hc.impl.HCDOMWrapper;
import com.helger.html.hc.impl.HCNodeList;
import com.helger.io.resource.IReadableResource;
import com.helger.photon.uicore.page.IWebPageExecutionContext;
import com.helger.text.IMultilingualText;
import com.helger.xml.microdom.IMicroContainer;
import com.helger.xml.microdom.IMicroNode;

/**
 * Renders a page with HTML code that is provided from an external resource
 * (e.g. for static pages). The content of the page is language independent.
 *
 * @author Philip Helger
 * @param <WPECTYPE>
 *        Web page execution context type
 */
@ThreadSafe
public class BasePageViewExternal <WPECTYPE extends IWebPageExecutionContext> extends AbstractWebPageResourceContent <WPECTYPE>
{
  protected final IReadableResource m_aResource;
  @GuardedBy ("m_aRWLock")
  protected IMicroContainer m_aParsedContent;

  @NonNull
  private IMicroContainer _readFromResource (@NonNull final IReadableResource aResource)
  {
    final boolean bHasContentCleanser = hasContentCleanser ();

    // Main read
    final IMicroContainer ret = readHTMLPageFragment (aResource, !bHasContentCleanser);

    // Perform callback
    if (bHasContentCleanser)
      getContentCleanser ().accept (ret);
    return ret;
  }

  public BasePageViewExternal (@NonNull @Nonempty final String sID,
                               @NonNull final String sName,
                               @NonNull final IReadableResource aResource,
                               @Nullable final Consumer <? super IMicroContainer> aContentCleanser)
  {
    this (sID, getAsMLT (sName), aResource, aContentCleanser);
  }

  public BasePageViewExternal (@NonNull @Nonempty final String sID,
                               @NonNull final IMultilingualText aName,
                               @NonNull final IReadableResource aResource,
                               @Nullable final Consumer <? super IMicroContainer> aContentCleanser)
  {
    super (sID, aName, aContentCleanser);

    m_aResource = ValueEnforcer.notNull (aResource, "Resource");
    // Read once anyway to check if the resource is readable
    m_aParsedContent = _readFromResource (aResource);
  }

  /**
   * @return The resource to be read as specified in the constructor. Never
   *         <code>null</code>.
   */
  @NonNull
  public final IReadableResource getResource ()
  {
    return m_aResource;
  }

  /**
   * @return A clone of the passed content. Never <code>null</code>.
   */
  @NonNull
  @ReturnsMutableCopy
  public IMicroContainer getParsedContent ()
  {
    return m_aRWLock.readLockedGet (m_aParsedContent::getClone);
  }

  public void updateFromResource ()
  {
    m_aRWLock.writeLocked ( () -> m_aParsedContent = _readFromResource (m_aResource));
  }

  @Override
  @OverridingMethodsMustInvokeSuper
  protected void fillContent (@NonNull final WPECTYPE aWPEC)
  {
    final HCNodeList aNodeList = aWPEC.getNodeList ();
    final boolean bReadFromResource = isReadEveryTime ();

    final IMicroNode aNode = m_aRWLock.readLockedGet ( () -> bReadFromResource ? _readFromResource (m_aResource) : m_aParsedContent);

    aNodeList.addChild (new HCDOMWrapper (aNode));
  }

  @Override
  public String toString ()
  {
    return ToStringGenerator.getDerived (super.toString ())
                            .append ("Resource", m_aResource)
                            .append ("ParsedContent", m_aParsedContent)
                            .getToString ();
  }
}
