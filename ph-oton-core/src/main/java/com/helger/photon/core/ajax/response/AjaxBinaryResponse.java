/**
 * Copyright (C) 2014-2017 Philip Helger (www.helger.com)
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
import java.nio.charset.StandardCharsets;

import javax.annotation.Nonnull;
import javax.annotation.concurrent.Immutable;

import com.helger.commons.ValueEnforcer;
import com.helger.commons.annotation.Nonempty;
import com.helger.commons.annotation.ReturnsMutableObject;
import com.helger.commons.equals.EqualsHelper;
import com.helger.commons.hashcode.HashCodeGenerator;
import com.helger.commons.mime.CMimeType;
import com.helger.commons.mime.EMimeContentType;
import com.helger.commons.mime.IMimeType;
import com.helger.commons.string.ToStringGenerator;
import com.helger.servlet.response.EContentDispositionType;
import com.helger.servlet.response.UnifiedResponse;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;

/**
 * A simple AJAX response, based on a byte array value and a message disposition
 * filename.
 *
 * @author Philip Helger
 */
@Immutable
@Deprecated
public class AjaxBinaryResponse implements IAjaxResponse
{
  private final byte [] m_aValue;
  private final IMimeType m_aMimeType;
  private final String m_sDispositionFilename;
  private EContentDispositionType m_eDispositionType = EContentDispositionType.ATTACHMENT;
  private Charset m_aTextCharset = StandardCharsets.UTF_8;

  @SuppressFBWarnings ("EI_EXPOSE_REP2")
  public AjaxBinaryResponse (@Nonnull @Nonempty final byte [] aValue,
                             @Nonnull final IMimeType aMimeType,
                             @Nonnull @Nonempty final String sDispositionFilename)
  {
    m_aValue = ValueEnforcer.notEmpty (aValue, "Value");
    m_aMimeType = ValueEnforcer.notNull (aMimeType, "MimeType");
    m_sDispositionFilename = ValueEnforcer.notEmpty (sDispositionFilename, "DispositionFilename");
  }

  /**
   * @return The passed byte array with the response content. Never
   *         <code>null</code>. Note: the returned array is exactly the one send
   *         as a response, so don't modify it!
   */
  @Nonnull
  @Nonempty
  @ReturnsMutableObject ("memory reasons")
  public byte [] getResponseData ()
  {
    return m_aValue;
  }

  /**
   * @return The response MIME type as passed in the constructor. Never
   *         <code>null</code>.
   */
  @Nonnull
  public IMimeType getMimeType ()
  {
    return m_aMimeType;
  }

  /**
   * @return The content disposition filename as passed in the constructor.
   *         Neither <code>null</code> nor empty.
   */
  @Nonnull
  @Nonempty
  public String getDispositionFilename ()
  {
    return m_sDispositionFilename;
  }

  /**
   * Set the content disposition type. The default is
   * {@link EContentDispositionType#ATTACHMENT}.
   *
   * @param eDispositionType
   *        The type to use. May not be <code>null</code>.
   * @return this for chaining
   * @since 7.0.4
   */
  @Nonnull
  public AjaxBinaryResponse setDispositionType (@Nonnull final EContentDispositionType eDispositionType)
  {
    m_eDispositionType = ValueEnforcer.notNull (eDispositionType, "DispositionType");
    return this;
  }

  /**
   * @return The content disposition type to be used. Never <code>null</code>.
   *         The default is {@link EContentDispositionType#ATTACHMENT}.
   * @since 7.0.4
   */
  @Nonnull
  public EContentDispositionType getDispositionType ()
  {
    return m_eDispositionType;
  }

  /**
   * Set the charset to be used for responses that have a "text/" based MIME
   * type.
   *
   * @param aTextCharset
   *        The charset to use. May not be <code>null</code>.
   * @return this for chaining
   */
  @Nonnull
  public AjaxBinaryResponse setTextCharset (@Nonnull final Charset aTextCharset)
  {
    m_aTextCharset = ValueEnforcer.notNull (aTextCharset, "TextCharset");
    return this;
  }

  /**
   * @return The charset to be used for "text/" based MIME types. Never
   *         <code>null</code>.
   */
  @Nonnull
  public Charset getTextCharset ()
  {
    return m_aTextCharset;
  }

  public void applyToResponse (@Nonnull final UnifiedResponse aUnifiedResponse)
  {
    aUnifiedResponse.setContent (m_aValue)
                    .setMimeType (m_aMimeType)
                    .setContentDispositionFilename (m_sDispositionFilename)
                    .setContentDispositionType (m_eDispositionType);

    if (m_aMimeType.getContentType () == EMimeContentType.TEXT)
      aUnifiedResponse.setCharset (m_aTextCharset);
  }

  @Override
  public boolean equals (final Object o)
  {
    if (o == this)
      return true;
    if (!super.equals (o))
      return false;
    final AjaxBinaryResponse rhs = (AjaxBinaryResponse) o;
    return EqualsHelper.equals (m_aValue, rhs.m_aValue) &&
           m_aMimeType.equals (rhs.m_aMimeType) &&
           m_sDispositionFilename.equals (rhs.m_sDispositionFilename) &&
           m_aTextCharset.equals (rhs.m_aTextCharset);
  }

  @Override
  public int hashCode ()
  {
    return HashCodeGenerator.getDerived (super.hashCode ())
                            .append (m_aValue)
                            .append (m_aMimeType)
                            .append (m_sDispositionFilename)
                            .append (m_aTextCharset)
                            .getHashCode ();
  }

  @Override
  public String toString ()
  {
    return ToStringGenerator.getDerived (super.toString ())
                            .append ("Value.length", m_aValue.length)
                            .append ("MimeType", m_aMimeType)
                            .append ("DispositionFilename", m_sDispositionFilename)
                            .append ("TextCharset", m_aTextCharset)
                            .getToString ();
  }

  @Nonnull
  public static AjaxBinaryResponse createForPDF (@Nonnull @Nonempty final byte [] aValue,
                                                 @Nonnull @Nonempty final String sDispositionFilename)
  {
    return new AjaxBinaryResponse (aValue, CMimeType.APPLICATION_PDF, sDispositionFilename);
  }
}
