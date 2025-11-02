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
package com.helger.photon.exchange.bulkexport.format;

import java.io.OutputStream;
import java.nio.charset.Charset;

import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.Nullable;

import com.helger.annotation.WillClose;
import com.helger.annotation.concurrent.NotThreadSafe;
import com.helger.annotation.style.ReturnsImmutableObject;
import com.helger.annotation.style.ReturnsMutableObject;
import com.helger.base.enforce.ValueEnforcer;
import com.helger.base.io.stream.StreamHelper;
import com.helger.base.state.ESuccess;
import com.helger.photon.exchange.EExchangeFileType;
import com.helger.photon.exchange.bulkexport.IExportRecord;
import com.helger.photon.exchange.bulkexport.IExportRecordField;
import com.helger.photon.exchange.bulkexport.IExportRecordProvider;
import com.helger.photon.exchange.bulkexport.IExporterFile;
import com.helger.xml.microdom.IMicroDocument;
import com.helger.xml.microdom.IMicroElement;
import com.helger.xml.microdom.MicroDocument;
import com.helger.xml.microdom.serialize.MicroWriter;
import com.helger.xml.serialize.write.IXMLWriterSettings;
import com.helger.xml.serialize.write.XMLWriterSettings;

/**
 * Implementation of {@link IExporterFile} for XML files
 *
 * @author Philip Helger
 */
@NotThreadSafe
public class ExporterXML implements IExporterFile
{
  public static final boolean DEFAULT_EMIT_TYPE_ATTRIBUTE = true;
  public static final String ELEMENT_ROOT = "root";
  public static final String ELEMENT_HEADER = "header";
  public static final String ELEMENT_BODY = "body";
  public static final String ELEMENT_FOOTER = "footer";
  public static final String ELEMENT_RECORD = "record";
  public static final String ELEMENT_FIELD = "field";
  public static final String ATTR_TYPE = "type";

  private final XMLWriterSettings m_aXWS = new XMLWriterSettings ();
  private boolean m_bEmitTypeAttr = DEFAULT_EMIT_TYPE_ATTRIBUTE;

  public ExporterXML ()
  {}

  @Override
  @NonNull
  public final EExchangeFileType getFileType ()
  {
    return EExchangeFileType.XML;
  }

  @NonNull
  public final Charset getCharset ()
  {
    return m_aXWS.getCharset ();
  }

  @NonNull
  public final ExporterXML setCharset (@NonNull final Charset aCharset)
  {
    m_aXWS.setCharset (aCharset);
    return this;
  }

  @NonNull
  @ReturnsMutableObject
  public final XMLWriterSettings xmlWriterSettings ()
  {
    return m_aXWS;
  }

  @NonNull
  @ReturnsImmutableObject
  public final IXMLWriterSettings getXMLWriterSettings ()
  {
    return m_aXWS;
  }

  public final boolean isEmitTypeAttr ()
  {
    return m_bEmitTypeAttr;
  }

  @NonNull
  public final ExporterXML setEmitTypeAttr (final boolean bEmitTypeAttr)
  {
    m_bEmitTypeAttr = bEmitTypeAttr;
    return this;
  }

  private void _emitRecord (@NonNull final IMicroElement eParentRow, @NonNull final IExportRecord aRecord)
  {
    final IMicroElement eRecord = eParentRow.addElement (ELEMENT_RECORD);
    for (final IExportRecordField aField : aRecord.getAllFields ())
    {
      final Object aFieldValue = aField.getFieldValue ();
      final IMicroElement eField = eRecord.addElement (ELEMENT_FIELD);
      if (m_bEmitTypeAttr)
        eField.setAttribute (ATTR_TYPE, aField.getFieldType ().getID ());
      if (aFieldValue != null)
        eField.addTextWithConversion (aFieldValue);
    }
  }

  @Nullable
  public IMicroDocument convertRecords (@NonNull final IExportRecordProvider aProvider)
  {
    ValueEnforcer.notNull (aProvider, "Provider");

    final IMicroDocument aDoc = new MicroDocument ();
    final IMicroElement eRoot = aDoc.addElement (ELEMENT_ROOT);

    // Header
    final IMicroElement eHeader = eRoot.addElement (ELEMENT_HEADER);
    aProvider.forEachHeaderRecord (x -> _emitRecord (eHeader, x));

    // Body
    final IMicroElement eBody = eRoot.addElement (ELEMENT_BODY);
    aProvider.forEachBodyRecord (x -> _emitRecord (eBody, x));

    // Footer
    final IMicroElement eFooter = eRoot.addElement (ELEMENT_FOOTER);
    aProvider.forEachFooterRecord (x -> _emitRecord (eFooter, x));

    // The body element is always present
    if (eBody.hasNoChildren ())
      return null;

    return aDoc;
  }

  @Override
  @NonNull
  public ESuccess exportRecords (@NonNull final IExportRecordProvider aProvider,
                                 @NonNull @WillClose final OutputStream aOS)
  {
    try
    {
      ValueEnforcer.notNull (aProvider, "Provider");
      ValueEnforcer.notNull (aOS, "OutputStream");

      final IMicroDocument aDoc = convertRecords (aProvider);
      if (aDoc == null)
        return ESuccess.FAILURE;

      MicroWriter.writeToStream (aDoc, aOS, m_aXWS);
      return ESuccess.SUCCESS;
    }
    finally
    {
      StreamHelper.close (aOS);
    }
  }
}
