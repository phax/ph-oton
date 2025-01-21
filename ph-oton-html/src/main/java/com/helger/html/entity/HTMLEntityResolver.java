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
package com.helger.html.entity;

import java.io.InputStream;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import org.xml.sax.EntityResolver;
import org.xml.sax.InputSource;

import com.helger.commons.annotation.Nonempty;
import com.helger.commons.annotation.ReturnsMutableCopy;
import com.helger.commons.annotation.Singleton;
import com.helger.commons.collection.impl.CommonsHashMap;
import com.helger.commons.collection.impl.ICommonsMap;
import com.helger.commons.collection.impl.ICommonsSet;
import com.helger.commons.io.resource.ClassPathResource;
import com.helger.commons.io.stream.StreamHelper;
import com.helger.html.CHTMLDocTypes;
import com.helger.xml.sax.InputSourceFactory;

/**
 * The SAX entity resolver for XHTML resources.
 *
 * @author Philip Helger
 */
@Singleton
public final class HTMLEntityResolver implements EntityResolver
{
  private static final HTMLEntityResolver INSTANCE = new HTMLEntityResolver ();

  /** Maps public ID to the DTD content for performance reasons */
  private final ICommonsMap <String, byte []> m_aResolveMap = new CommonsHashMap <> ();

  private void _addResolvablePublicId (@Nonnull @Nonempty final String sPublicID,
                                       @Nonnull @Nonempty final String sFilePath)
  {
    if (m_aResolveMap.containsKey (sPublicID))
      throw new IllegalArgumentException ("Passed public id '" + sPublicID + "' is already contained!");

    final InputStream aIS = ClassPathResource.getInputStream (sFilePath);
    if (aIS == null)
      throw new IllegalArgumentException ("The passed resource " + sFilePath + " does not exist!");

    final byte [] aBytes = StreamHelper.getAllBytes (aIS);
    m_aResolveMap.put (sPublicID, aBytes);
  }

  private HTMLEntityResolver ()
  {
    final String sPrefix = "external/html/";
    _addResolvablePublicId (CHTMLDocTypes.DOCTYPE_XHTML10_STRICT_QNAME, sPrefix + "xhtml1-strict.dtd");
    _addResolvablePublicId (CHTMLDocTypes.DOCTYPE_XHTML10_TRANS_QNAME, sPrefix + "xhtml1-transitional.dtd");
    _addResolvablePublicId (CHTMLDocTypes.DOCTYPE_XHTML11_QNAME, sPrefix + "xhtml11.dtd");
    // XHTML 1.0
    _addResolvablePublicId ("-//W3C//ENTITIES Latin 1 for XHTML//EN", sPrefix + "xhtml-lat1.ent");
    _addResolvablePublicId ("-//W3C//ENTITIES Symbols for XHTML//EN", sPrefix + "xhtml-symbol.ent");
    _addResolvablePublicId ("-//W3C//ENTITIES Special for XHTML//EN", sPrefix + "xhtml-special.ent");
    // XHTML 1.1
    _addResolvablePublicId ("-//W3C//ELEMENTS XHTML Inline Style 1.0//EN", sPrefix + "xhtml-inlstyle-1.mod");
    _addResolvablePublicId ("-//W3C//ENTITIES XHTML 1.1 Document Model 1.0//EN", sPrefix + "xhtml11-model-1.mod");
    _addResolvablePublicId ("-//W3C//ENTITIES XHTML Modular Framework 1.0//EN", sPrefix + "xhtml-framework-1.mod");
    _addResolvablePublicId ("-//W3C//ELEMENTS XHTML Text 1.0//EN", sPrefix + "xhtml-text-1.mod");
    _addResolvablePublicId ("-//W3C//ELEMENTS XHTML Hypertext 1.0//EN", sPrefix + "xhtml-hypertext-1.mod");
    _addResolvablePublicId ("-//W3C//ELEMENTS XHTML Lists 1.0//EN", sPrefix + "xhtml-list-1.mod");
    _addResolvablePublicId ("-//W3C//ELEMENTS XHTML Editing Elements 1.0//EN", sPrefix + "xhtml-edit-1.mod");
    _addResolvablePublicId ("-//W3C//ELEMENTS XHTML BIDI Override Element 1.0//EN", sPrefix + "xhtml-bdo-1.mod");
    _addResolvablePublicId ("-//W3C//ELEMENTS XHTML Ruby 1.0//EN", sPrefix + "xhtml-ruby-1.mod");
    _addResolvablePublicId ("-//W3C//ELEMENTS XHTML Presentation 1.0//EN", sPrefix + "xhtml-pres-1.mod");
    _addResolvablePublicId ("-//W3C//ELEMENTS XHTML Link Element 1.0//EN", sPrefix + "xhtml-link-1.mod");
    _addResolvablePublicId ("-//W3C//ELEMENTS XHTML Metainformation 1.0//EN", sPrefix + "xhtml-meta-1.mod");
    _addResolvablePublicId ("-//W3C//ELEMENTS XHTML Base Element 1.0//EN", sPrefix + "xhtml-base-1.mod");
    _addResolvablePublicId ("-//W3C//ELEMENTS XHTML Scripting 1.0//EN", sPrefix + "xhtml-script-1.mod");
    _addResolvablePublicId ("-//W3C//ELEMENTS XHTML Style Sheets 1.0//EN", sPrefix + "xhtml-style-1.mod");
    _addResolvablePublicId ("-//W3C//ELEMENTS XHTML Images 1.0//EN", sPrefix + "xhtml-image-1.mod");
    _addResolvablePublicId ("-//W3C//ELEMENTS XHTML Client-side Image Maps 1.0//EN", sPrefix + "xhtml-csismap-1.mod");
    _addResolvablePublicId ("-//W3C//ELEMENTS XHTML Server-side Image Maps 1.0//EN", sPrefix + "xhtml-ssismap-1.mod");
    _addResolvablePublicId ("-//W3C//ELEMENTS XHTML Param Element 1.0//EN", sPrefix + "xhtml-param-1.mod");
    _addResolvablePublicId ("-//W3C//ELEMENTS XHTML Embedded Object 1.0//EN", sPrefix + "xhtml-object-1.mod");
    _addResolvablePublicId ("-//W3C//ELEMENTS XHTML Tables 1.0//EN", sPrefix + "xhtml-table-1.mod");
    _addResolvablePublicId ("-//W3C//ELEMENTS XHTML Forms 1.0//EN", sPrefix + "xhtml-form-1.mod");
    _addResolvablePublicId ("-//W3C//ELEMENTS XHTML Target 1.0//EN", sPrefix + "xhtml-target-1.mod");
    _addResolvablePublicId ("-//W3C//ELEMENTS XHTML Legacy Markup 1.0//EN", sPrefix + "xhtml-legacy-1.mod");
    _addResolvablePublicId ("-//W3C//ELEMENTS XHTML Document Structure 1.0//EN", sPrefix + "xhtml-struct-1.mod");
    _addResolvablePublicId ("-//W3C//ELEMENTS XHTML Base Architecture 1.0//EN", sPrefix + "xhtml-arch-1.mod");
    _addResolvablePublicId ("-//W3C//NOTATIONS XHTML Notations 1.0//EN", sPrefix + "xhtml-notations-1.mod");
    _addResolvablePublicId ("-//W3C//ENTITIES XHTML Datatypes 1.0//EN", sPrefix + "xhtml-datatypes-1.mod");
    _addResolvablePublicId ("-//W3C//ENTITIES XHTML Qualified Names 1.0//EN", sPrefix + "xhtml-qname-1.mod");
    _addResolvablePublicId ("-//W3C//ENTITIES XHTML Intrinsic Events 1.0//EN", sPrefix + "xhtml-events-1.mod");
    _addResolvablePublicId ("-//W3C//ENTITIES XHTML Common Attributes 1.0//EN", sPrefix + "xhtml-attribs-1.mod");
    _addResolvablePublicId ("-//W3C//ENTITIES XHTML Character Entities 1.0//EN", sPrefix + "xhtml-charent-1.mod");
    _addResolvablePublicId ("-//W3C//ELEMENTS XHTML Inline Structural 1.0//EN", sPrefix + "xhtml-inlstruct-1.mod");
    _addResolvablePublicId ("-//W3C//ELEMENTS XHTML Inline Phrasal 1.0//EN", sPrefix + "xhtml-inlphras-1.mod");
    _addResolvablePublicId ("-//W3C//ELEMENTS XHTML Block Structural 1.0//EN", sPrefix + "xhtml-blkstruct-1.mod");
    _addResolvablePublicId ("-//W3C//ELEMENTS XHTML Block Phrasal 1.0//EN", sPrefix + "xhtml-blkphras-1.mod");
    _addResolvablePublicId ("-//W3C//ELEMENTS XHTML Inline Presentation 1.0//EN", sPrefix + "xhtml-inlpres-1.mod");
    _addResolvablePublicId ("-//W3C//ELEMENTS XHTML Block Presentation 1.0//EN", sPrefix + "xhtml-blkpres-1.mod");
  }

  @Nonnull
  public static HTMLEntityResolver getInstance ()
  {
    return INSTANCE;
  }

  @Nonnull
  @ReturnsMutableCopy
  public ICommonsSet <String> getAllPublicIds ()
  {
    return m_aResolveMap.copyOfKeySet ();
  }

  @Nullable
  public InputSource resolveEntity (@Nullable final String sPublicId, @Nullable final String sSystemId)
  {
    return resolveEntity (sPublicId);
  }

  @Nullable
  public InputSource resolveEntity (@Nullable final String sPublicId)
  {
    final byte [] aBytes = m_aResolveMap.get (sPublicId);
    return aBytes == null ? null : InputSourceFactory.create (aBytes);
  }
}
