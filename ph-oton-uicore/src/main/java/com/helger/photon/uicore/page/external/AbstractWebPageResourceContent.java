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

import java.nio.charset.Charset;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.concurrent.GuardedBy;
import javax.annotation.concurrent.ThreadSafe;

import com.helger.commons.annotation.Nonempty;
import com.helger.commons.charset.CCharset;
import com.helger.commons.debug.GlobalDebug;
import com.helger.commons.io.resource.IReadableResource;
import com.helger.commons.io.stream.StreamHelper;
import com.helger.commons.microdom.IMicroContainer;
import com.helger.commons.microdom.util.MicroVisitor;
import com.helger.commons.string.ToStringGenerator;
import com.helger.commons.text.IMultilingualText;
import com.helger.commons.xml.serialize.read.SAXReaderSettings;
import com.helger.html.EHTMLVersion;
import com.helger.html.hc.config.HCSettings;
import com.helger.html.parser.XHTMLParser;
import com.helger.photon.uicore.page.AbstractWebPage;
import com.helger.photon.uicore.page.IWebPageExecutionContext;

/**
 * Base class for pages consisting of external HTML code that is provided from
 * an external resource (e.g. for static pages).
 *
 * @author Philip Helger
 * @param <WPECTYPE>
 *        Web page execution context type
 */
@ThreadSafe
public abstract class AbstractWebPageResourceContent <WPECTYPE extends IWebPageExecutionContext> extends AbstractWebPage <WPECTYPE>
{
  public static final Charset DEFAULT_CHARSET = CCharset.CHARSET_UTF_8_OBJ;

  protected final ReadWriteLock m_aRWLock = new ReentrantReadWriteLock ();

  @GuardedBy ("m_aRWLock")
  private boolean m_bReadEveryTime = GlobalDebug.isDebugMode ();

  @Nonnull
  public static IMicroContainer readHTMLPageFragment (@Nonnull final IReadableResource aResource)
  {
    return readHTMLPageFragment (aResource,
                                 DEFAULT_CHARSET,
                                 HCSettings.getConversionSettings ().getHTMLVersion (),
                                 (SAXReaderSettings) null);
  }

  @Nonnull
  public static IMicroContainer readHTMLPageFragment (@Nonnull final IReadableResource aResource,
                                                      @Nonnull final Charset aCharset,
                                                      @Nonnull final EHTMLVersion eHTMLVersion,
                                                      @Nullable final SAXReaderSettings aAdditionalSaxReaderSettings)
  {
    // Read content once
    final String sContent = StreamHelper.getAllBytesAsString (aResource, aCharset);
    if (sContent == null)
      throw new IllegalStateException ("Failed to read resource " + aResource.toString ());

    // Parse content

    // XXX hack - explicitly use XHTML 1.1 for reading, to avoid problems with
    // entity resolving, because HTML5 has no public ID and therefore the entity
    // resolver is not called!
    final EHTMLVersion eParseHTMLVersion = eHTMLVersion.isAtLeastHTML5 () ? EHTMLVersion.XHTML11 : eHTMLVersion;
    final XHTMLParser aXHTMLParser = new XHTMLParser (eParseHTMLVersion);
    aXHTMLParser.setAdditionalSAXReaderSettings (aAdditionalSaxReaderSettings);
    final IMicroContainer ret = aXHTMLParser.unescapeXHTMLFragment (sContent);
    if (ret == null)
      throw new IllegalStateException ("Failed to parse HTML code of resource " + aResource.toString ());

    // Do standard cleansing with the provided HTML version!
    MicroVisitor.visit (ret, new PageViewExternalHTMLCleanser (eHTMLVersion));

    return ret;
  }

  public AbstractWebPageResourceContent (@Nonnull @Nonempty final String sID, @Nonnull final String sName)
  {
    super (sID, sName);
  }

  public AbstractWebPageResourceContent (@Nonnull @Nonempty final String sID, @Nonnull final IMultilingualText aName)
  {
    super (sID, aName);
  }

  /**
   * @return <code>true</code> if the underlying resource should be read each
   *         time {@link #fillContent(IWebPageExecutionContext)} is invoked or
   *         <code>false</code> if the resource is read once in the constructor
   *         and re-used over and over.
   */
  @SuppressWarnings ("javadoc")
  public final boolean isReadEveryTime ()
  {
    m_aRWLock.readLock ().lock ();
    try
    {
      return m_bReadEveryTime;
    }
    finally
    {
      m_aRWLock.readLock ().unlock ();
    }
  }

  /**
   * @param bReadEveryTime
   *        <code>true</code> if the underlying resource should be read each
   *        time {@link #fillContent(IWebPageExecutionContext)} is invoked or
   *        <code>false</code> if the resource should be read once in the
   *        constructor and re-used over and over.
   * @return this
   */
  @SuppressWarnings ("javadoc")
  @Nonnull
  public final AbstractWebPageResourceContent <WPECTYPE> setReadEveryTime (final boolean bReadEveryTime)
  {
    m_aRWLock.writeLock ().lock ();
    try
    {
      m_bReadEveryTime = bReadEveryTime;
    }
    finally
    {
      m_aRWLock.writeLock ().unlock ();
    }
    return this;
  }

  /**
   * Re-read the content from the underlying resource. This only makes sense, if
   * {@link #isReadEveryTime()} is <code>false</code>.
   *
   * @see #isReadEveryTime()
   * @see #setReadEveryTime(boolean)
   */
  public abstract void updateFromResource ();

  @Override
  public String toString ()
  {
    return ToStringGenerator.getDerived (super.toString ()).append ("readEveryTime", m_bReadEveryTime).toString ();
  }
}
