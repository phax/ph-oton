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
package com.helger.webctrls.page;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.concurrent.GuardedBy;
import javax.annotation.concurrent.NotThreadSafe;
import javax.annotation.concurrent.ThreadSafe;

import com.helger.commons.ValueEnforcer;
import com.helger.commons.annotations.Nonempty;
import com.helger.commons.annotations.OverrideOnDemand;
import com.helger.commons.annotations.ReturnsMutableCopy;
import com.helger.commons.io.IReadableResource;
import com.helger.commons.locale.LocaleUtils;
import com.helger.commons.microdom.IMicroContainer;
import com.helger.commons.microdom.IMicroNode;
import com.helger.commons.string.ToStringGenerator;
import com.helger.commons.text.IReadonlyMultiLingualText;
import com.helger.html.hc.impl.HCDOMWrapper;
import com.helger.html.hc.impl.HCNodeList;
import com.helger.webbasics.app.page.IWebPageExecutionContext;

/**
 * Renders a page with HTML code that is provided from an external resource
 * (e.g. for static pages). The content of the page is language dependent.
 *
 * @author Philip Helger
 * @param <WPECTYPE>
 *        Web page execution context type
 */
@ThreadSafe
public class PageViewExternalMultiLingual <WPECTYPE extends IWebPageExecutionContext> extends AbstractPageViewExternal <WPECTYPE>
{
  @NotThreadSafe
  private static final class ContentPerLocale implements Serializable
  {
    private final IReadableResource m_aResource;
    private IMicroContainer m_aCont;

    public ContentPerLocale (@Nonnull final IReadableResource aResource, @Nonnull final IMicroContainer aCont)
    {
      m_aResource = ValueEnforcer.notNull (aResource, "Resource");
      setContainer (aCont);
    }

    @Nonnull
    public IReadableResource getResource ()
    {
      return m_aResource;
    }

    @Nonnull
    @ReturnsMutableCopy
    public IMicroContainer getContainerClone ()
    {
      return m_aCont.getClone ();
    }

    public void setContainer (@Nonnull final IMicroContainer aCont)
    {
      m_aCont = ValueEnforcer.notNull (aCont, "Cont");
    }

    @Override
    public String toString ()
    {
      return new ToStringGenerator (this).append ("resource", m_aResource).append ("cont", m_aCont).toString ();
    }
  }

  private final Locale m_aDefaultLocale;
  @GuardedBy ("m_aRWLock")
  private final Map <Locale, ContentPerLocale> m_aContent = new HashMap <Locale, ContentPerLocale> ();

  /**
   * This callback is called after the HTML content was successfully read
   *
   * @param aLocale
   *        The locale of the read resource. Never <code>null</code>.
   * @param aResource
   *        The read resource. Never <code>null</code>.
   * @param aCont
   *        The micro container containing all HTML elements contained in the
   *        resource specified in the constructor. Never <code>null</code>.
   */
  @OverrideOnDemand
  protected void afterPageRead (@Nonnull final Locale aLocale,
                                @Nonnull final IReadableResource aResource,
                                @Nonnull final IMicroContainer aCont)
  {}

  @Nonnull
  private IMicroContainer _readFromResource (@Nonnull final Locale aLocale, @Nonnull final IReadableResource aResource)
  {
    // Main read
    final IMicroContainer ret = readHTMLPageFragment (aResource);

    // Perform callback
    afterPageRead (aLocale, aResource, ret);
    return ret;
  }

  private void _init (@Nonnull final Map <Locale, IReadableResource> aResources)
  {
    // Read once anyway to check if the resource is readable
    for (final Map.Entry <Locale, IReadableResource> aEntry : aResources.entrySet ())
    {
      final Locale aLocale = aEntry.getKey ();
      final IReadableResource aResource = aEntry.getValue ();
      final IMicroContainer aCont = _readFromResource (aLocale, aResource);
      m_aContent.put (aLocale, new ContentPerLocale (aResource, aCont));
    }
  }

  public PageViewExternalMultiLingual (@Nonnull @Nonempty final String sID,
                                       @Nonnull final String sName,
                                       @Nonnull final Map <Locale, IReadableResource> aResources,
                                       @Nonnull final Locale aDefaultLocale)
  {
    super (sID, sName);
    ValueEnforcer.notEmpty (aResources, "Resources");
    m_aDefaultLocale = ValueEnforcer.notNull (aDefaultLocale, "DefaultLocale");
    if (!aResources.containsKey (aDefaultLocale))
      throw new IllegalArgumentException ("The provided default locale " +
                                          aDefaultLocale +
                                          " is not present in the resource locales: " +
                                          aResources.keySet ());
    _init (aResources);
  }

  public PageViewExternalMultiLingual (@Nonnull @Nonempty final String sID,
                                       @Nonnull final IReadonlyMultiLingualText aName,
                                       @Nonnull final Map <Locale, IReadableResource> aResources,
                                       @Nonnull final Locale aDefaultLocale)
  {
    super (sID, aName);
    ValueEnforcer.notEmpty (aResources, "Resources");
    m_aDefaultLocale = ValueEnforcer.notNull (aDefaultLocale, "DefaultLocale");
    if (!aResources.containsKey (aDefaultLocale))
      throw new IllegalArgumentException ("The provided default locale " +
                                          aDefaultLocale +
                                          " is not present in the resource locales: " +
                                          aResources.keySet ());
    _init (aResources);
  }

  /**
   * @param aLocale
   *        The locale to be used. May be <code>null</code>.
   * @return A clone of the passed content. <code>null</code> if no such content
   *         is present.
   */
  @Nullable
  public IMicroContainer getParsedContent (@Nullable final Locale aLocale)
  {
    if (aLocale == null)
      return null;

    m_aRWLock.readLock ().lock ();
    try
    {
      // Determine locale to use
      final Locale aLocaleToUse = LocaleUtils.getLocaleToUseOrNull (aLocale, m_aContent.keySet ());
      if (aLocaleToUse == null)
        return null;

      final ContentPerLocale aContent = m_aContent.get (aLocaleToUse);
      if (aContent == null)
      {
        // Should never happen, but anyway..
        return null;
      }
      return aContent.getContainerClone ();
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
      for (final Map.Entry <Locale, ContentPerLocale> aEntry : m_aContent.entrySet ())
      {
        final Locale aLocale = aEntry.getKey ();
        final ContentPerLocale aContent = aEntry.getValue ();
        // Read again
        final IMicroContainer aCont = _readFromResource (aLocale, aContent.getResource ());
        // And update object on the fly
        aContent.setContainer (aCont);
      }
    }
    finally
    {
      m_aRWLock.writeLock ().unlock ();
    }
  }

  @Override
  protected void fillContent (@Nonnull final WPECTYPE aWPEC)
  {
    final HCNodeList aNodeList = aWPEC.getNodeList ();
    final Locale aDisplayLocale = aWPEC.getDisplayLocale ();
    final boolean bReadFromResource = isReadEveryTime ();

    final IMicroNode aNode;
    m_aRWLock.readLock ().lock ();
    try
    {
      // Use the default locale as fallback, since we ensured that the default
      // locale is contained!
      final Locale aLocaleToUse = LocaleUtils.getLocaleToUseOrFallback (aDisplayLocale,
                                                                        m_aContent.keySet (),
                                                                        m_aDefaultLocale);
      final ContentPerLocale aContent = m_aContent.get (aLocaleToUse);
      if (aContent == null)
        throw new IllegalStateException ("Found no content for locale " +
                                         aLocaleToUse +
                                         " and display locale " +
                                         aDisplayLocale +
                                         " in page " +
                                         getID ());
      aNode = bReadFromResource ? _readFromResource (aLocaleToUse, aContent.getResource ())
                               : aContent.getContainerClone ();
    }
    finally
    {
      m_aRWLock.readLock ().unlock ();
    }

    aNodeList.addChild (new HCDOMWrapper (aNode));
  }
}
