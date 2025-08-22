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
package com.helger.html.parser;

import javax.xml.XMLConstants;

import com.helger.annotation.concurrent.NotThreadSafe;
import com.helger.annotation.style.ReturnsMutableCopy;
import com.helger.base.enforce.ValueEnforcer;
import com.helger.base.string.StringHelper;
import com.helger.cache.regex.RegExHelper;
import com.helger.html.EHTMLElement;
import com.helger.html.EHTMLVersion;
import com.helger.html.entity.HTMLEntityResolver;
import com.helger.xml.EXMLParserFeature;
import com.helger.xml.microdom.IMicroContainer;
import com.helger.xml.microdom.IMicroDocument;
import com.helger.xml.microdom.IMicroElement;
import com.helger.xml.microdom.IMicroNode;
import com.helger.xml.microdom.MicroContainer;
import com.helger.xml.microdom.serialize.MicroReader;
import com.helger.xml.serialize.read.ISAXReaderSettings;
import com.helger.xml.serialize.read.SAXReaderSettings;
import com.helger.xml.serialize.write.EXMLIncorrectCharacterHandling;
import com.helger.xml.serialize.write.EXMLSerializeVersion;
import com.helger.xml.serialize.write.XMLEmitter;

import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;

/**
 * Utility class for parsing stuff as HTML.
 *
 * @author Philip Helger
 */
@NotThreadSafe
public class XHTMLParser
{
  private final EHTMLVersion m_eHTMLVersion;

  private SAXReaderSettings m_aSAXReaderSettings = createDefaultSAXReaderSettings ();

  @Nonnull
  @ReturnsMutableCopy
  public static SAXReaderSettings createDefaultSAXReaderSettings ()
  {
    // By default enable secured reader settings.
    // * DOCTYPE must be allowed because it is common in HTML files
    // * parameter entities must be allowed, because otherwise the HTML DTDs
    // cannot be read correctly
    // * load external DTD must be enabled to read HTML entities
    return new SAXReaderSettings ().setFeatureValue (EXMLParserFeature.SECURE_PROCESSING, true)
                                   .setFeatureValue (EXMLParserFeature.DISALLOW_DOCTYPE_DECL, false)
                                   .setFeatureValue (EXMLParserFeature.EXTERNAL_GENERAL_ENTITIES, false)
                                   .setFeatureValue (EXMLParserFeature.EXTERNAL_PARAMETER_ENTITIES, true)
                                   .setFeatureValue (EXMLParserFeature.LOAD_EXTERNAL_DTD, true)
                                   .setEntityResolver (HTMLEntityResolver.getInstance ());
  }

  public XHTMLParser (@Nonnull final EHTMLVersion eHTMLVersion)
  {
    m_eHTMLVersion = ValueEnforcer.notNull (eHTMLVersion, "HTMLVersion");
  }

  /**
   * @return The HTML version as specified in the constructor. Never <code>null</code>.
   */
  @Nonnull
  public EHTMLVersion getHTMLVersion ()
  {
    return m_eHTMLVersion;
  }

  /**
   * @return A copy of the additional SAX reader settings that are used for parsing. By default a
   *         secure processing is active, that disallows inline DTDs in HTML documents.
   * @since 9.1.1
   */
  @Nonnull
  @ReturnsMutableCopy
  public SAXReaderSettings getSAXReaderSettings ()
  {
    // Return a clone
    return m_aSAXReaderSettings.getClone ();
  }

  /**
   * Set additional SAX reader settings that are used when an XHTML fragment is read. All settings
   * are reused when parsing except for the entity resolver which is always set to the default
   * {@link HTMLEntityResolver}.
   *
   * @param aAdditionalSaxReaderSettings
   *        The settings to be used. May be <code>null</code>.
   * @return this for chaining
   * @since 9.1.1
   */
  @Nonnull
  public XHTMLParser setSAXReaderSettings (@Nullable final ISAXReaderSettings aAdditionalSaxReaderSettings)
  {
    m_aSAXReaderSettings = SAXReaderSettings.createCloneOnDemand (aAdditionalSaxReaderSettings);
    return this;
  }

  /**
   * Check whether the passed text looks like it contains XHTML code. This is a heuristic check only
   * and does not perform actual parsing!
   *
   * @param sText
   *        The text to check.
   * @return <code>true</code> if the text looks like HTML
   */
  public static boolean looksLikeXHTML (@Nullable final String sText)
  {
    // If the text contains an open angle bracket followed by a character that
    // we think of it as HTML
    // (?s) enables the "dotall" mode - see Pattern.DOTALL
    return StringHelper.isNotEmpty (sText) && RegExHelper.stringMatchesPattern ("(?s).*<[a-zA-Z].+", sText);
  }

  /**
   * Check if the given fragment is valid XHTML 1.1 mark-up. This method tries to parse the XHTML
   * fragment, so it is potentially slow!
   *
   * @param sXHTMLFragment
   *        The XHTML fragment to parse. It is not checked, whether the value looks like HTML or
   *        not.
   * @return <code>true</code> if the fragment is valid, <code>false</code> otherwise.
   */
  public boolean isValidXHTMLFragment (@Nullable final String sXHTMLFragment)
  {
    return StringHelper.isEmpty (sXHTMLFragment) || parseXHTMLFragment (sXHTMLFragment) != null;
  }

  /**
   * Parse the given fragment as XHTML 1.1. This is a sanity method for
   * {@link #parseXHTMLFragment(String)} with the predefined XHTML 1.1 document type.
   *
   * @param sXHTMLFragment
   *        The XHTML fragment to parse. May be <code>null</code>.
   * @return <code>null</code> if parsing failed.
   */
  @Nullable
  public IMicroDocument parseXHTMLFragment (@Nullable final String sXHTMLFragment)
  {
    // Build mini HTML and insert fragment in the middle.
    // If parsing succeeds, it is considered valid HTML.
    final String sHTMLNamespaceURI = m_eHTMLVersion.getNamespaceURI ();
    final String sXHTML = XMLEmitter.getDocTypeHTMLRepresentation (EXMLSerializeVersion.XML_10,
                                                                   EXMLIncorrectCharacterHandling.DEFAULT,
                                                                   m_eHTMLVersion.getDocType ()) +
                          "<html" +
                          (sHTMLNamespaceURI != null ? ' ' +
                                                       XMLConstants.XMLNS_ATTRIBUTE +
                                                       "=\"" +
                                                       sHTMLNamespaceURI +
                                                       '"' : "") +
                          "><head><title></title></head><body>" +
                          StringHelper.getNotNull (sXHTMLFragment) +
                          "</body></html>";
    return parseXHTMLDocument (sXHTML);
  }

  /**
   * This method parses a full HTML document into a {@link IMicroDocument} using the additional SAX
   * reader settings and always the {@link HTMLEntityResolver} as an entity resolver.
   *
   * @param sXHTML
   *        The complete XHTML document as a string. May be <code>null</code>.
   * @return <code>null</code> if interpretation failed
   */
  @Nullable
  public IMicroDocument parseXHTMLDocument (@Nullable final String sXHTML)
  {
    return MicroReader.readMicroXML (sXHTML, m_aSAXReaderSettings);
  }

  /**
   * Interpret the passed XHTML fragment as HTML and retrieve a result container with all body
   * elements.
   *
   * @param sXHTML
   *        The XHTML text fragment. This fragment is parsed as an HTML body and may therefore not
   *        contain the &lt;body&gt; tag.
   * @return <code>null</code> if the passed text could not be interpreted as XHTML or if no body
   *         element was found, an {@link IMicroContainer} with all body children otherwise.
   */
  @Nullable
  public IMicroContainer unescapeXHTMLFragment (@Nullable final String sXHTML)
  {
    // Ensure that the content is surrounded by a single tag
    final IMicroDocument aDoc = parseXHTMLFragment (sXHTML);
    if (aDoc != null && aDoc.getDocumentElement () != null)
    {
      // Find "body" case insensitive
      final IMicroElement eBody = aDoc.getDocumentElement ().getFirstChildElement (EHTMLElement.BODY.getElementName ());
      if (eBody != null)
      {
        final IMicroContainer ret = new MicroContainer ();
        if (eBody.hasChildren ())
        {
          // We need a copy because detachFromParent is modifying
          for (final IMicroNode aChildNode : eBody.getAllChildren ())
            ret.addChild (aChildNode.detachFromParent ());
        }
        return ret;
      }
    }
    return null;
  }
}
