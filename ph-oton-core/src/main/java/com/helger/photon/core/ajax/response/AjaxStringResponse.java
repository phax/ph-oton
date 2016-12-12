/**
 * Copyright (C) 2014-2016 Philip Helger (www.helger.com)
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
package com.helger.photon.core.ajax.response;

import java.nio.charset.Charset;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.OverridingMethodsMustInvokeSuper;
import javax.annotation.concurrent.Immutable;

import org.w3c.dom.Node;

import com.helger.commons.ValueEnforcer;
import com.helger.commons.hashcode.HashCodeGenerator;
import com.helger.commons.mime.CMimeType;
import com.helger.commons.mime.IMimeType;
import com.helger.commons.string.StringHelper;
import com.helger.commons.string.ToStringGenerator;
import com.helger.html.hc.config.HCSettings;
import com.helger.photon.core.app.html.PhotonHTMLHelper;
import com.helger.servlet.response.UnifiedResponse;
import com.helger.xml.microdom.IMicroNode;
import com.helger.xml.microdom.serialize.MicroWriter;
import com.helger.xml.serialize.write.IXMLWriterSettings;
import com.helger.xml.serialize.write.XMLWriter;
import com.helger.xml.serialize.write.XMLWriterSettings;

/**
 * A simple AJAX response, based on an {@link String} value.
 *
 * @author Philip Helger
 */
@Immutable
public class AjaxStringResponse extends AbstractAjaxResponse
{
  private final String m_sValue;
  private final Charset m_aCharset;
  private final IMimeType m_aMimeType;

  public AjaxStringResponse (final boolean bSuccess,
                             @Nullable final String sValue,
                             @Nonnull final Charset aCharset,
                             @Nonnull final IMimeType aMimeType)
  {
    super (bSuccess);
    m_sValue = StringHelper.getNotNull (sValue);
    m_aCharset = ValueEnforcer.notNull (aCharset, "Charset");
    m_aMimeType = ValueEnforcer.notNull (aMimeType, "MimeType");
  }

  @Nonnull
  public String getResponseString ()
  {
    return m_sValue;
  }

  @Nonnull
  public Charset getCharset ()
  {
    return m_aCharset;
  }

  @Nonnull
  public IMimeType getMimeType ()
  {
    return m_aMimeType;
  }

  @OverridingMethodsMustInvokeSuper
  public void applyToResponse (@Nonnull final UnifiedResponse aUnifiedResponse)
  {
    aUnifiedResponse.setContentAndCharset (m_sValue, m_aCharset).setMimeType (m_aMimeType);
  }

  @Override
  public boolean equals (final Object o)
  {
    if (o == this)
      return true;
    if (!super.equals (o))
      return false;
    final AjaxStringResponse rhs = (AjaxStringResponse) o;
    return m_sValue.equals (rhs.m_sValue);
  }

  @Override
  public int hashCode ()
  {
    return HashCodeGenerator.getDerived (super.hashCode ()).append (m_sValue).getHashCode ();
  }

  @Override
  public String toString ()
  {
    return ToStringGenerator.getDerived (super.toString ())
                            .append ("Value", m_sValue)
                            .append ("MimeType", m_aMimeType)
                            .toString ();
  }

  @Nonnull
  public static AjaxStringResponse createForXML (final boolean bSuccess, @Nullable final String sValue)
  {
    return new AjaxStringResponse (bSuccess,
                                   sValue,
                                   XMLWriterSettings.DEFAULT_XML_CHARSET_OBJ,
                                   CMimeType.APPLICATION_XML);
  }

  @Nonnull
  public static AjaxStringResponse createForXML (final boolean bSuccess, @Nullable final IMicroNode aNode)
  {
    return createForXML (bSuccess, aNode, XMLWriterSettings.DEFAULT_XML_SETTINGS);
  }

  @Nonnull
  public static AjaxStringResponse createForXML (final boolean bSuccess,
                                                 @Nullable final IMicroNode aNode,
                                                 @Nonnull final IXMLWriterSettings aSettings)
  {
    return new AjaxStringResponse (bSuccess,
                                   aNode == null ? null : MicroWriter.getNodeAsString (aNode, aSettings),
                                   aSettings.getCharsetObj (),
                                   CMimeType.APPLICATION_XML);
  }

  @Nonnull
  public static AjaxStringResponse createForXML (final boolean bSuccess, @Nullable final Node aNode)
  {
    return createForXML (bSuccess, aNode, XMLWriterSettings.DEFAULT_XML_SETTINGS);
  }

  @Nonnull
  public static AjaxStringResponse createForXML (final boolean bSuccess,
                                                 @Nullable final Node aNode,
                                                 @Nonnull final IXMLWriterSettings aSettings)
  {
    return new AjaxStringResponse (bSuccess,
                                   aNode == null ? null : XMLWriter.getNodeAsString (aNode, aSettings),
                                   aSettings.getCharsetObj (),
                                   CMimeType.APPLICATION_XML);
  }

  @Nonnull
  public static AjaxStringResponse createForHTML (final boolean bSuccess, @Nullable final String sValue)
  {
    return new AjaxStringResponse (bSuccess, sValue, HCSettings.getHTMLCharset (), PhotonHTMLHelper.getMimeType (null));
  }

  @Nonnull
  public static AjaxStringResponse createForText (final boolean bSuccess, @Nullable final String sValue)
  {
    return new AjaxStringResponse (bSuccess, sValue, XMLWriterSettings.DEFAULT_XML_CHARSET_OBJ, CMimeType.TEXT_PLAIN);
  }
}
