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
package com.helger.appbasics.exchange.bulkexport.format;

import java.io.IOException;
import java.io.OutputStream;
import java.io.Writer;
import java.nio.charset.Charset;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.WillClose;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.helger.appbasics.exchange.EExchangeFileType;
import com.helger.appbasics.exchange.bulkexport.IExportRecord;
import com.helger.appbasics.exchange.bulkexport.IExportRecordField;
import com.helger.appbasics.exchange.bulkexport.IExportRecordProvider;
import com.helger.appbasics.exchange.bulkexport.IExporterFile;
import com.helger.commons.ValueEnforcer;
import com.helger.commons.annotations.Nonempty;
import com.helger.commons.annotations.ReturnsMutableObject;
import com.helger.commons.charset.CCharset;
import com.helger.commons.collections.iterate.IterableIterator;
import com.helger.commons.io.streams.StreamUtils;
import com.helger.commons.state.ESuccess;
import com.helger.json.IJsonArray;
import com.helger.json.IJsonObject;
import com.helger.json.impl.JsonArray;
import com.helger.json.impl.JsonObject;
import com.helger.json.serialize.JsonWriter;
import com.helger.json.serialize.JsonWriterSettings;

/**
 * Implementation of {@link IExporterFile} for JSON files
 *
 * @author Philip Helger
 */
public final class ExporterJSON implements IExporterFile
{
  public static final boolean DEFAULT_EMIT_TYPE = true;
  private static final String ELEMENT_HEADER = "header";
  private static final String ELEMENT_BODY = "body";
  private static final String ELEMENT_FOOTER = "footer";
  private static final String ATTR_TYPE = "type";
  private static final String ATTR_VALUE = "value";

  private static final Logger s_aLogger = LoggerFactory.getLogger (ExporterJSON.class);

  private final JsonWriterSettings m_aJWS = new JsonWriterSettings ();
  private Charset m_aCharset = CCharset.CHARSET_UTF_8_OBJ;
  private boolean m_bEmitType = DEFAULT_EMIT_TYPE;

  public ExporterJSON ()
  {}

  @Nonnull
  public ExporterJSON setCharset (@Nonnull final Charset aCharset)
  {
    m_aCharset = ValueEnforcer.notNull (aCharset, "Charset");
    return this;
  }

  @Nonnull
  public Charset getCharset ()
  {
    return m_aCharset;
  }

  @Nonnull
  @ReturnsMutableObject (reason = "Design")
  public JsonWriterSettings getJsonWriterSettings ()
  {
    return m_aJWS;
  }

  public boolean isEmitType ()
  {
    return m_bEmitType;
  }

  @Nonnull
  public ExporterJSON setEmitType (final boolean bEmitType)
  {
    m_bEmitType = bEmitType;
    return this;
  }

  @Nonnull
  @Nonempty
  private IJsonArray _emitRecord (@Nonnull final IExportRecord aRecord)
  {
    final IJsonArray ret = new JsonArray ();
    for (final IExportRecordField aField : aRecord.getAllFields ())
    {
      final Object aFieldValue = aField.getFieldValue ();
      final IJsonObject aObject = new JsonObject ();
      if (m_bEmitType)
        aObject.add (ATTR_TYPE, aField.getFieldType ().getID ());
      if (aFieldValue != null)
        aObject.add (ATTR_VALUE, aFieldValue);
      ret.add (aObject);
    }
    return ret;
  }

  @Nullable
  public IJsonObject convertRecords (@Nonnull final IExportRecordProvider aProvider)
  {
    ValueEnforcer.notNull (aProvider, "Provider");

    final IJsonObject aDoc = new JsonObject ();

    // Header
    for (final IExportRecord aHeaderRecord : aProvider.getHeaderRecords ())
      aDoc.add (ELEMENT_HEADER, _emitRecord (aHeaderRecord));

    // Body
    for (final IExportRecord aBodyRecord : IterableIterator.create (aProvider.getBodyRecords ()))
      aDoc.add (ELEMENT_BODY, _emitRecord (aBodyRecord));

    // Footer
    for (final IExportRecord aFooterRecord : aProvider.getFooterRecords ())
      aDoc.add (ELEMENT_FOOTER, _emitRecord (aFooterRecord));

    if (aDoc.isEmpty ())
      return null;

    return aDoc;
  }

  @Override
  @Nonnull
  public ESuccess exportRecords (@Nonnull final IExportRecordProvider aProvider,
                                 @Nonnull @WillClose final OutputStream aOS)
  {
    try
    {
      ValueEnforcer.notNull (aProvider, "Provider");
      ValueEnforcer.notNull (aOS, "OutputStream");

      final IJsonObject aDoc = convertRecords (aProvider);
      if (aDoc == null)
        return ESuccess.FAILURE;

      final Writer aWriter = StreamUtils.createWriter (aOS, m_aCharset);
      try
      {
        new JsonWriter (m_aJWS).writeNodeToWriter (aDoc, aWriter);
      }
      finally
      {
        // Important to close writer so that the content gets flushed!
        StreamUtils.close (aWriter);
      }
      return ESuccess.SUCCESS;
    }
    catch (final IOException ex)
    {
      if (!StreamUtils.isKnownEOFException (ex))
        s_aLogger.error ("Failed to write JSON to output stream " + aOS, ex);
      return ESuccess.FAILURE;
    }
    finally
    {
      StreamUtils.close (aOS);
    }
  }

  @Override
  @Nonnull
  public EExchangeFileType getFileType ()
  {
    return EExchangeFileType.XML;
  }
}
