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

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.function.Consumer;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.concurrent.GuardedBy;
import javax.annotation.concurrent.ThreadSafe;

import com.helger.commons.annotation.Nonempty;
import com.helger.commons.concurrent.SimpleReadWriteLock;
import com.helger.commons.debug.GlobalDebug;
import com.helger.commons.io.resource.IReadableResource;
import com.helger.commons.io.stream.StreamHelper;
import com.helger.commons.string.ToStringGenerator;
import com.helger.commons.text.IMultilingualText;
import com.helger.html.EHTMLVersion;
import com.helger.html.hc.config.HCSettings;
import com.helger.html.parser.XHTMLParser;
import com.helger.photon.uicore.page.AbstractWebPage;
import com.helger.photon.uicore.page.IWebPageExecutionContext;
import com.helger.xml.microdom.IMicroContainer;
import com.helger.xml.microdom.util.MicroVisitor;
import com.helger.xml.serialize.read.SAXReaderSettings;

/**
 * Base class for pages consisting of external HTML code that is provided from
 * an external resource (e.g. for static pages).
 *
 * @author Philip Helger
 * @param <WPECTYPE>
 *        Web page execution context type
 */
@ThreadSafe
public abstract class AbstractWebPageResourceContent <WPECTYPE extends IWebPageExecutionContext> extends
                                                     AbstractWebPage <WPECTYPE> implements
                                                     IWebPageResourceContent
{
  public static final Charset DEFAULT_CHARSET = StandardCharsets.UTF_8;

  protected final SimpleReadWriteLock m_aRWLock = new SimpleReadWriteLock ();

  private final Consumer <? super IMicroContainer> m_aContentCleanser;
  @GuardedBy ("m_aRWLock")
  private boolean m_bReadEveryTime = GlobalDebug.isDebugMode ();

  @Nonnull
  public static IMicroContainer readHTMLPageFragment (@Nonnull final IReadableResource aResource,
                                                      final boolean bPeformStandardCleansing)
  {
    return readHTMLPageFragment (aResource,
                                 DEFAULT_CHARSET,
                                 HCSettings.getConversionSettings ().getHTMLVersion (),
                                 (SAXReaderSettings) null,
                                 bPeformStandardCleansing);
  }

  @Nonnull
  public static IMicroContainer readHTMLPageFragment (@Nonnull final IReadableResource aResource,
                                                      @Nonnull final Charset aCharset,
                                                      @Nonnull final EHTMLVersion eHTMLVersion,
                                                      @Nullable final SAXReaderSettings aAdditionalSaxReaderSettings,
                                                      final boolean bPeformStandardCleansing)
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
    if (aAdditionalSaxReaderSettings != null)
      aXHTMLParser.setSAXReaderSettings (aAdditionalSaxReaderSettings);
    final IMicroContainer ret = aXHTMLParser.unescapeXHTMLFragment (sContent);
    if (ret == null)
      throw new IllegalStateException ("Failed to parse HTML code of resource " + aResource.toString ());

    if (bPeformStandardCleansing)
    {
      // Do standard cleansing with the provided HTML version!
      MicroVisitor.visit (ret, new PageViewExternalHTMLCleanser (eHTMLVersion));
    }
    return ret;
  }

  public AbstractWebPageResourceContent (@Nonnull @Nonempty final String sID,
                                         @Nonnull final IMultilingualText aName,
                                         @Nullable final Consumer <? super IMicroContainer> aContentCleanser)
  {
    super (sID, aName, null);
    m_aContentCleanser = aContentCleanser;
  }

  public final boolean isReadEveryTime ()
  {
    return m_aRWLock.readLockedBoolean ( () -> m_bReadEveryTime);
  }

  @Nonnull
  public final AbstractWebPageResourceContent <WPECTYPE> setReadEveryTime (final boolean bReadEveryTime)
  {
    m_aRWLock.writeLocked ( () -> m_bReadEveryTime = bReadEveryTime);
    return this;
  }

  @Nullable
  public final Consumer <? super IMicroContainer> getContentCleanser ()
  {
    return m_aContentCleanser;
  }

  public final boolean hasContentCleanser ()
  {
    return m_aContentCleanser != null;
  }

  @Override
  public String toString ()
  {
    return ToStringGenerator.getDerived (super.toString ())
                            .append ("ContentCleanser", m_aContentCleanser)
                            .append ("ReadEveryTime", m_bReadEveryTime)
                            .getToString ();
  }
}
