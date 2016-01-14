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

import javax.annotation.Nonnull;
import javax.annotation.concurrent.Immutable;

import com.helger.commons.ValueEnforcer;
import com.helger.commons.annotation.Nonempty;
import com.helger.commons.annotation.ReturnsMutableObject;
import com.helger.commons.charset.CCharset;
import com.helger.commons.equals.EqualsHelper;
import com.helger.commons.hashcode.HashCodeGenerator;
import com.helger.commons.mime.CMimeType;
import com.helger.commons.mime.EMimeContentType;
import com.helger.commons.mime.IMimeType;
import com.helger.commons.string.ToStringGenerator;
import com.helger.web.servlet.response.UnifiedResponse;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;

/**
 * A simple AJAX response, based on a byte array value and a message disposition
 * filename.
 *
 * @author Philip Helger
 */
@Immutable
public class AjaxBinaryResponse extends AbstractAjaxResponse
{
  private final byte [] m_aValue;
  private final IMimeType m_aMimeType;
  private final String m_sDispositionFilename;

  @SuppressFBWarnings ("EI_EXPOSE_REP2")
  public AjaxBinaryResponse (@Nonnull @Nonempty final byte [] aValue,
                             @Nonnull final IMimeType aMimeType,
                             @Nonnull @Nonempty final String sDispositionFilename)
  {
    super (true);
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

  public void applyToResponse (@Nonnull final UnifiedResponse aUnifiedResponse)
  {
    aUnifiedResponse.setContent (m_aValue)
                    .setMimeType (m_aMimeType)
                    .setContentDispositionFilename (m_sDispositionFilename);

    if (m_aMimeType.getContentType () == EMimeContentType.TEXT)
      aUnifiedResponse.setCharset (CCharset.CHARSET_UTF_8_OBJ);
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
           m_sDispositionFilename.equals (rhs.m_sDispositionFilename);
  }

  @Override
  public int hashCode ()
  {
    return HashCodeGenerator.getDerived (super.hashCode ())
                            .append (m_aValue)
                            .append (m_aMimeType)
                            .append (m_sDispositionFilename)
                            .getHashCode ();
  }

  @Override
  public String toString ()
  {
    return ToStringGenerator.getDerived (super.toString ())
                            .append ("Value.length", m_aValue.length)
                            .append ("MimeType", m_aMimeType)
                            .append ("DispositionFilename", m_sDispositionFilename)
                            .toString ();
  }

  @Nonnull
  public static AjaxBinaryResponse createForPDF (@Nonnull @Nonempty final byte [] aValue,
                                                 @Nonnull @Nonempty final String sDispositionFilename)
  {
    return new AjaxBinaryResponse (aValue, CMimeType.APPLICATION_PDF, sDispositionFilename);
  }
}
