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
package com.helger.html.entities;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import org.xml.sax.EntityResolver;
import org.xml.sax.InputSource;

import com.helger.commons.annotations.Nonempty;
import com.helger.commons.annotations.ReturnsMutableCopy;
import com.helger.commons.collections.CollectionHelper;
import com.helger.commons.io.resource.ClassPathResource;
import com.helger.commons.io.streams.StreamUtils;
import com.helger.commons.xml.sax.InputSourceFactory;
import com.helger.html.CHTMLDocTypes;

/**
 * The SAX entity resolver for XHTML resources.
 *
 * @author Philip Helger
 */
public final class HTMLEntityResolver implements EntityResolver
{
  private static final HTMLEntityResolver s_aInstance = new HTMLEntityResolver ();

  /** Maps public ID to the DTD content */
  private final Map <String, byte []> m_aResolveMap = new HashMap <String, byte []> ();

  private void _addResolvablePublicId (@Nonnull @Nonempty final String sPublicID,
                                       @Nonnull @Nonempty final String sFilePath)
  {
    if (m_aResolveMap.containsKey (sPublicID))
      throw new IllegalArgumentException ("Passed public id '" + sPublicID + "' is already contained!");
    final InputStream aIS = ClassPathResource.getInputStream (sFilePath);
    if (aIS == null)
      throw new IllegalArgumentException ("The passed resource " + sFilePath + " does not exist!");
    final byte [] aBytes = StreamUtils.getAllBytes (aIS);
    m_aResolveMap.put (sPublicID, aBytes);
  }

  private HTMLEntityResolver ()
  {
    _addResolvablePublicId (CHTMLDocTypes.DOCTYPE_XHTML10_STRICT_QNAME, "html/xhtml1-strict.dtd");
    _addResolvablePublicId (CHTMLDocTypes.DOCTYPE_XHTML10_TRANS_QNAME, "html/xhtml1-transitional.dtd");
    _addResolvablePublicId (CHTMLDocTypes.DOCTYPE_XHTML11_QNAME, "html/xhtml11.dtd");
    // XHTML 1.0
    _addResolvablePublicId ("-//W3C//ENTITIES Latin 1 for XHTML//EN", "html/xhtml-lat1.ent");
    _addResolvablePublicId ("-//W3C//ENTITIES Symbols for XHTML//EN", "html/xhtml-symbol.ent");
    _addResolvablePublicId ("-//W3C//ENTITIES Special for XHTML//EN", "html/xhtml-special.ent");
    // XHTML 1.1
    _addResolvablePublicId ("-//W3C//ELEMENTS XHTML Inline Style 1.0//EN", "html/xhtml-inlstyle-1.mod");
    _addResolvablePublicId ("-//W3C//ENTITIES XHTML 1.1 Document Model 1.0//EN", "html/xhtml11-model-1.mod");
    _addResolvablePublicId ("-//W3C//ENTITIES XHTML Modular Framework 1.0//EN", "html/xhtml-framework-1.mod");
    _addResolvablePublicId ("-//W3C//ELEMENTS XHTML Text 1.0//EN", "html/xhtml-text-1.mod");
    _addResolvablePublicId ("-//W3C//ELEMENTS XHTML Hypertext 1.0//EN", "html/xhtml-hypertext-1.mod");
    _addResolvablePublicId ("-//W3C//ELEMENTS XHTML Lists 1.0//EN", "html/xhtml-list-1.mod");
    _addResolvablePublicId ("-//W3C//ELEMENTS XHTML Editing Elements 1.0//EN", "html/xhtml-edit-1.mod");
    _addResolvablePublicId ("-//W3C//ELEMENTS XHTML BIDI Override Element 1.0//EN", "html/xhtml-bdo-1.mod");
    _addResolvablePublicId ("-//W3C//ELEMENTS XHTML Ruby 1.0//EN", "html/xhtml-ruby-1.mod");
    _addResolvablePublicId ("-//W3C//ELEMENTS XHTML Presentation 1.0//EN", "html/xhtml-pres-1.mod");
    _addResolvablePublicId ("-//W3C//ELEMENTS XHTML Link Element 1.0//EN", "html/xhtml-link-1.mod");
    _addResolvablePublicId ("-//W3C//ELEMENTS XHTML Metainformation 1.0//EN", "html/xhtml-meta-1.mod");
    _addResolvablePublicId ("-//W3C//ELEMENTS XHTML Base Element 1.0//EN", "html/xhtml-base-1.mod");
    _addResolvablePublicId ("-//W3C//ELEMENTS XHTML Scripting 1.0//EN", "html/xhtml-script-1.mod");
    _addResolvablePublicId ("-//W3C//ELEMENTS XHTML Style Sheets 1.0//EN", "html/xhtml-style-1.mod");
    _addResolvablePublicId ("-//W3C//ELEMENTS XHTML Images 1.0//EN", "html/xhtml-image-1.mod");
    _addResolvablePublicId ("-//W3C//ELEMENTS XHTML Client-side Image Maps 1.0//EN", "html/xhtml-csismap-1.mod");
    _addResolvablePublicId ("-//W3C//ELEMENTS XHTML Server-side Image Maps 1.0//EN", "html/xhtml-ssismap-1.mod");
    _addResolvablePublicId ("-//W3C//ELEMENTS XHTML Param Element 1.0//EN", "html/xhtml-param-1.mod");
    _addResolvablePublicId ("-//W3C//ELEMENTS XHTML Embedded Object 1.0//EN", "html/xhtml-object-1.mod");
    _addResolvablePublicId ("-//W3C//ELEMENTS XHTML Tables 1.0//EN", "html/xhtml-table-1.mod");
    _addResolvablePublicId ("-//W3C//ELEMENTS XHTML Forms 1.0//EN", "html/xhtml-form-1.mod");
    _addResolvablePublicId ("-//W3C//ELEMENTS XHTML Target 1.0//EN", "html/xhtml-target-1.mod");
    _addResolvablePublicId ("-//W3C//ELEMENTS XHTML Legacy Markup 1.0//EN", "html/xhtml-legacy-1.mod");
    _addResolvablePublicId ("-//W3C//ELEMENTS XHTML Document Structure 1.0//EN", "html/xhtml-struct-1.mod");
    _addResolvablePublicId ("-//W3C//ELEMENTS XHTML Base Architecture 1.0//EN", "html/xhtml-arch-1.mod");
    _addResolvablePublicId ("-//W3C//NOTATIONS XHTML Notations 1.0//EN", "html/xhtml-notations-1.mod");
    _addResolvablePublicId ("-//W3C//ENTITIES XHTML Datatypes 1.0//EN", "html/xhtml-datatypes-1.mod");
    _addResolvablePublicId ("-//W3C//ENTITIES XHTML Qualified Names 1.0//EN", "html/xhtml-qname-1.mod");
    _addResolvablePublicId ("-//W3C//ENTITIES XHTML Intrinsic Events 1.0//EN", "html/xhtml-events-1.mod");
    _addResolvablePublicId ("-//W3C//ENTITIES XHTML Common Attributes 1.0//EN", "html/xhtml-attribs-1.mod");
    _addResolvablePublicId ("-//W3C//ENTITIES XHTML Character Entities 1.0//EN", "html/xhtml-charent-1.mod");
    _addResolvablePublicId ("-//W3C//ELEMENTS XHTML Inline Structural 1.0//EN", "html/xhtml-inlstruct-1.mod");
    _addResolvablePublicId ("-//W3C//ELEMENTS XHTML Inline Phrasal 1.0//EN", "html/xhtml-inlphras-1.mod");
    _addResolvablePublicId ("-//W3C//ELEMENTS XHTML Block Structural 1.0//EN", "html/xhtml-blkstruct-1.mod");
    _addResolvablePublicId ("-//W3C//ELEMENTS XHTML Block Phrasal 1.0//EN", "html/xhtml-blkphras-1.mod");
    _addResolvablePublicId ("-//W3C//ELEMENTS XHTML Inline Presentation 1.0//EN", "html/xhtml-inlpres-1.mod");
    _addResolvablePublicId ("-//W3C//ELEMENTS XHTML Block Presentation 1.0//EN", "html/xhtml-blkpres-1.mod");
  }

  @Nonnull
  public static HTMLEntityResolver getInstance ()
  {
    return s_aInstance;
  }

  @Nonnull
  @ReturnsMutableCopy
  public Set <String> getAllPublicIds ()
  {
    return CollectionHelper.newSet (m_aResolveMap.keySet ());
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
