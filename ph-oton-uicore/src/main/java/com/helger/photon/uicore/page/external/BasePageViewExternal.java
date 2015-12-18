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
package com.helger.photon.uicore.page.external;

import javax.annotation.Nonnull;
import javax.annotation.OverridingMethodsMustInvokeSuper;
import javax.annotation.concurrent.GuardedBy;
import javax.annotation.concurrent.ThreadSafe;

import com.helger.commons.ValueEnforcer;
import com.helger.commons.annotation.Nonempty;
import com.helger.commons.annotation.OverrideOnDemand;
import com.helger.commons.annotation.ReturnsMutableCopy;
import com.helger.commons.io.resource.IReadableResource;
import com.helger.commons.microdom.IMicroContainer;
import com.helger.commons.microdom.IMicroNode;
import com.helger.commons.string.ToStringGenerator;
import com.helger.commons.text.IMultilingualText;
import com.helger.html.hc.impl.HCDOMWrapper;
import com.helger.html.hc.impl.HCNodeList;
import com.helger.photon.uicore.page.IWebPageExecutionContext;

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

  /**
   * This callback is called after the HTML content was successfully read
   *
   * @param aCont
   *        The micro container containing all HTML elements contained in the
   *        resource specified in the constructor. Never <code>null</code>.
   */
  @OverrideOnDemand
  protected void afterPageRead (@Nonnull final IMicroContainer aCont)
  {}

  @Nonnull
  private IMicroContainer _readFromResource (@Nonnull final IReadableResource aResource)
  {
    // Main read
    final IMicroContainer ret = readHTMLPageFragment (aResource);

    // Perform callback
    afterPageRead (ret);
    return ret;
  }

  public BasePageViewExternal (@Nonnull @Nonempty final String sID,
                               @Nonnull final String sName,
                               @Nonnull final IReadableResource aResource)
  {
    super (sID, sName);

    m_aResource = ValueEnforcer.notNull (aResource, "Resource");
    // Read once anyway to check if the resource is readable
    m_aParsedContent = _readFromResource (aResource);
  }

  public BasePageViewExternal (@Nonnull @Nonempty final String sID,
                               @Nonnull final IMultilingualText aName,
                               @Nonnull final IReadableResource aResource)
  {
    super (sID, aName);

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
    m_aRWLock.readLock ().lock ();
    try
    {
      return m_aParsedContent.getClone ();
    }
    finally
    {
      m_aRWLock.readLock ().unlock ();
    }
  }

  /**
   * Re-read the content from the underlying resource. This only makes sense, if
   * {@link #isReadEveryTime()} is <code>false</code>.
   *
   * @see #isReadEveryTime()
   * @see #setReadEveryTime(boolean)
   */
  @Override
  public void updateFromResource ()
  {
    m_aRWLock.writeLock ().lock ();
    try
    {
      m_aParsedContent = _readFromResource (m_aResource);
    }
    finally
    {
      m_aRWLock.writeLock ().unlock ();
    }
  }

  @Override
  @OverridingMethodsMustInvokeSuper
  protected void fillContent (@Nonnull final WPECTYPE aWPEC)
  {
    final HCNodeList aNodeList = aWPEC.getNodeList ();
    final boolean bReadFromResource = isReadEveryTime ();

    final IMicroNode aNode;
    m_aRWLock.readLock ().lock ();
    try
    {
      aNode = bReadFromResource ? _readFromResource (m_aResource) : m_aParsedContent;
    }
    finally
    {
      m_aRWLock.readLock ().unlock ();
    }

    aNodeList.addChild (new HCDOMWrapper (aNode));
  }

  @Override
  public String toString ()
  {
    return ToStringGenerator.getDerived (super.toString ())
                            .append ("resource", m_aResource)
                            .append ("parsedContent", m_aParsedContent)
                            .toString ();
  }
}
