/**
 * Copyright (C) 2014-2018 Philip Helger (www.helger.com)
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

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.OverridingMethodsMustInvokeSuper;
import javax.annotation.concurrent.GuardedBy;
import javax.annotation.concurrent.ThreadSafe;

import com.helger.commons.ValueEnforcer;
import com.helger.commons.annotation.Nonempty;
import com.helger.commons.annotation.ReturnsMutableCopy;
import com.helger.commons.functional.IConsumer;
import com.helger.commons.io.resource.IReadableResource;
import com.helger.commons.string.ToStringGenerator;
import com.helger.commons.text.IMultilingualText;
import com.helger.html.hc.impl.HCDOMWrapper;
import com.helger.html.hc.impl.HCNodeList;
import com.helger.photon.uicore.page.IWebPageExecutionContext;
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
public class BasePageViewExternal <WPECTYPE extends IWebPageExecutionContext>
                                  extends AbstractWebPageResourceContent <WPECTYPE>
{
  protected final IReadableResource m_aResource;
  @GuardedBy ("m_aRWLock")
  protected IMicroContainer m_aParsedContent;

  @Nonnull
  private IMicroContainer _readFromResource (@Nonnull final IReadableResource aResource)
  {
    final boolean bHasContentCleanser = hasContentCleanser ();

    // Main read
    final IMicroContainer ret = readHTMLPageFragment (aResource, !bHasContentCleanser);

    // Perform callback
    if (bHasContentCleanser)
      getContentCleanser ().accept (ret);
    return ret;
  }

  public BasePageViewExternal (@Nonnull @Nonempty final String sID,
                               @Nonnull final String sName,
                               @Nonnull final IReadableResource aResource,
                               @Nullable final IConsumer <? super IMicroContainer> aContentCleanser)
  {
    this (sID, getAsMLT (sName), aResource, aContentCleanser);
  }

  public BasePageViewExternal (@Nonnull @Nonempty final String sID,
                               @Nonnull final IMultilingualText aName,
                               @Nonnull final IReadableResource aResource,
                               @Nullable final IConsumer <? super IMicroContainer> aContentCleanser)
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
  @Nonnull
  public final IReadableResource getResource ()
  {
    return m_aResource;
  }

  /**
   * @return A clone of the passed content. Never <code>null</code>.
   */
  @Nonnull
  @ReturnsMutableCopy
  public IMicroContainer getParsedContent ()
  {
    return m_aRWLock.readLocked ( () -> m_aParsedContent.getClone ());
  }

  public void updateFromResource ()
  {
    m_aRWLock.writeLocked ( () -> m_aParsedContent = _readFromResource (m_aResource));
  }

  @Override
  @OverridingMethodsMustInvokeSuper
  protected void fillContent (@Nonnull final WPECTYPE aWPEC)
  {
    final HCNodeList aNodeList = aWPEC.getNodeList ();
    final boolean bReadFromResource = isReadEveryTime ();

    final IMicroNode aNode = m_aRWLock.readLocked ( () -> bReadFromResource ? _readFromResource (m_aResource)
                                                                            : m_aParsedContent);

    aNodeList.addChild (new HCDOMWrapper (aNode));
  }

  @Override
  public String toString ()
  {
    return ToStringGenerator.getDerived (super.toString ())
                            .append ("resource", m_aResource)
                            .append ("parsedContent", m_aParsedContent)
                            .getToString ();
  }
}
