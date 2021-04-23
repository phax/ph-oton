/**
 * Copyright (C) 2014-2021 Philip Helger (www.helger.com)
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

import java.io.IOException;
import java.io.OutputStream;
import java.io.Writer;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.WillClose;
import javax.annotation.concurrent.NotThreadSafe;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.helger.commons.ValueEnforcer;
import com.helger.commons.annotation.Nonempty;
import com.helger.commons.annotation.ReturnsImmutableObject;
import com.helger.commons.annotation.ReturnsMutableObject;
import com.helger.commons.io.stream.StreamHelper;
import com.helger.commons.state.ESuccess;
import com.helger.json.IJsonArray;
import com.helger.json.IJsonObject;
import com.helger.json.JsonArray;
import com.helger.json.JsonObject;
import com.helger.json.JsonValue;
import com.helger.json.serialize.IJsonWriterSettings;
import com.helger.json.serialize.JsonWriter;
import com.helger.json.serialize.JsonWriterSettings;
import com.helger.photon.exchange.EExchangeFileType;
import com.helger.photon.exchange.bulkexport.IExportRecord;
import com.helger.photon.exchange.bulkexport.IExportRecordField;
import com.helger.photon.exchange.bulkexport.IExportRecordProvider;
import com.helger.photon.exchange.bulkexport.IExporterFile;

/**
 * Implementation of {@link IExporterFile} for JSON files
 *
 * @author Philip Helger
 */
@NotThreadSafe
public class ExporterJSON implements IExporterFile
{
  public static final boolean DEFAULT_EMIT_TYPE = true;
  public static final String ELEMENT_HEADER = "header";
  public static final String ELEMENT_BODY = "body";
  public static final String ELEMENT_FOOTER = "footer";
  public static final String ATTR_TYPE = "type";
  public static final String ATTR_VALUE = "value";

  private static final Logger LOGGER = LoggerFactory.getLogger (ExporterJSON.class);

  private JsonWriterSettings m_aJWS = new JsonWriterSettings ();
  private Charset m_aCharset = StandardCharsets.UTF_8;
  private boolean m_bEmitType = DEFAULT_EMIT_TYPE;

  public ExporterJSON ()
  {}

  @Override
  @Nonnull
  public final EExchangeFileType getFileType ()
  {
    return EExchangeFileType.XML;
  }

  @Nonnull
  public final Charset getCharset ()
  {
    return m_aCharset;
  }

  @Nonnull
  public final ExporterJSON setCharset (@Nonnull final Charset aCharset)
  {
    m_aCharset = ValueEnforcer.notNull (aCharset, "Charset");
    return this;
  }

  @Nonnull
  @ReturnsMutableObject
  public final JsonWriterSettings jsonWriterSettings ()
  {
    return m_aJWS;
  }

  @Nonnull
  @ReturnsImmutableObject
  public final IJsonWriterSettings getJsonWriterSettings ()
  {
    return m_aJWS;
  }

  @Nonnull
  public final ExporterJSON setJsonWriterSettings (@Nonnull final JsonWriterSettings aJWS)
  {
    m_aJWS = ValueEnforcer.notNull (aJWS, "JsonWriterSettings");
    return this;
  }

  public final boolean isEmitType ()
  {
    return m_bEmitType;
  }

  @Nonnull
  public final ExporterJSON setEmitType (final boolean bEmitType)
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
      if (m_bEmitType)
      {
        final IJsonObject aObject = new JsonObject ();
        aObject.add (ATTR_TYPE, aField.getFieldType ().getID ());
        if (aFieldValue == null)
          aObject.addJson (ATTR_VALUE, JsonValue.NULL);
        else
          aObject.add (ATTR_VALUE, aFieldValue);
        ret.add (aObject);
      }
      else
      {
        ret.add (aFieldValue == null ? JsonValue.NULL : aFieldValue);
      }
    }
    return ret;
  }

  @Nullable
  public IJsonObject convertRecords (@Nonnull final IExportRecordProvider aProvider)
  {
    ValueEnforcer.notNull (aProvider, "Provider");

    final IJsonObject aDoc = new JsonObject ();

    // Header
    final IJsonArray aHeader = new JsonArray ();
    aProvider.forEachHeaderRecord (x -> aHeader.add (_emitRecord (x)));
    if (aHeader.isNotEmpty ())
      aDoc.addJson (ELEMENT_HEADER, aHeader);

    // Body
    final IJsonArray aBody = new JsonArray ();
    aProvider.forEachBodyRecord (x -> aBody.add (_emitRecord (x)));
    if (aBody.isNotEmpty ())
      aDoc.addJson (ELEMENT_BODY, aBody);

    // Footer
    final IJsonArray aFooter = new JsonArray ();
    aProvider.forEachFooterRecord (x -> aFooter.add (_emitRecord (x)));
    if (aFooter.isNotEmpty ())
      aDoc.addJson (ELEMENT_FOOTER, aFooter);

    if (aDoc.isEmpty ())
      return null;

    return aDoc;
  }

  @Override
  @Nonnull
  public ESuccess exportRecords (@Nonnull final IExportRecordProvider aProvider, @Nonnull @WillClose final OutputStream aOS)
  {
    try
    {
      ValueEnforcer.notNull (aProvider, "Provider");
      ValueEnforcer.notNull (aOS, "OutputStream");

      final IJsonObject aDoc = convertRecords (aProvider);
      if (aDoc == null)
        return ESuccess.FAILURE;

      try (final Writer aWriter = StreamHelper.createWriter (aOS, m_aCharset))
      {
        new JsonWriter (m_aJWS).writeToWriter (aDoc, aWriter);
      }
      return ESuccess.SUCCESS;
    }
    catch (final IOException ex)
    {
      if (!StreamHelper.isKnownEOFException (ex))
        LOGGER.error ("Failed to write JSON to output stream " + aOS, ex);
      return ESuccess.FAILURE;
    }
    finally
    {
      StreamHelper.close (aOS);
    }
  }
}
