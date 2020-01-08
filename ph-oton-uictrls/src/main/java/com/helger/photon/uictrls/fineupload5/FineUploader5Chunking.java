/**
 * Copyright (C) 2014-2020 Philip Helger (www.helger.com)
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
package com.helger.photon.uictrls.fineupload5;

import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.helger.commons.ValueEnforcer;
import com.helger.commons.annotation.Nonempty;
import com.helger.commons.url.ISimpleURL;
import com.helger.html.jscode.JSAssocArray;

/**
 * Wrapper for Fine Uploader 5.x chunking part
 *
 * @author Philip Helger
 */
public class FineUploader5Chunking implements IFineUploader5Part
{
  public static final boolean DEFAULT_CHUNKING_CONCURRENT_ENABLED = false;
  public static final boolean DEFAULT_CHUNKING_ENABLED = false;
  public static final boolean DEFAULT_CHUNKING_MANDATORY = false;
  public static final long DEFAULT_CHUNKING_PART_SIZE = 2_000_000;
  public static final String DEFAULT_CHUNKING_PARAM_NAMES_CHUNK_SIZE = "qqchunksize";
  public static final String DEFAULT_CHUNKING_PARAM_NAMES_PART_BYTE_OFFSET = "qqpartbyteoffset";
  public static final String DEFAULT_CHUNKING_PARAM_NAMES_PART_INDEX = "qqpartindex";
  public static final String DEFAULT_CHUNKING_PARAM_NAMES_TOTAL_PARTS = "qqtotalparts";

  private boolean m_bChunkingConcurrentEnabled = DEFAULT_CHUNKING_CONCURRENT_ENABLED;
  private boolean m_bChunkingEnabled = DEFAULT_CHUNKING_ENABLED;
  private boolean m_bChunkingMandatory = DEFAULT_CHUNKING_MANDATORY;
  private long m_nChunkingPartSize = DEFAULT_CHUNKING_PART_SIZE;
  private String m_sChunkingParamNamesChunkSize = DEFAULT_CHUNKING_PARAM_NAMES_CHUNK_SIZE;
  private String m_sChunkingParamNamesPartByteOffset = DEFAULT_CHUNKING_PARAM_NAMES_PART_BYTE_OFFSET;
  private String m_sChunkingParamNamesPartIndex = DEFAULT_CHUNKING_PARAM_NAMES_PART_INDEX;
  private String m_sChunkingParamNamesTotalParts = DEFAULT_CHUNKING_PARAM_NAMES_TOTAL_PARTS;
  private ISimpleURL m_aChunkingSuccessEndpoint;

  public FineUploader5Chunking ()
  {}

  public boolean isConcurrentEnabled ()
  {
    return m_bChunkingConcurrentEnabled;
  }

  /**
   * Allow multiple chunks to be uploaded simultaneously per file
   *
   * @param bConcurrentEnabled
   *        new value
   * @return this for chaining
   */
  @Nonnull
  public FineUploader5Chunking setConcurrentEnabled (final boolean bConcurrentEnabled)
  {
    m_bChunkingConcurrentEnabled = bConcurrentEnabled;
    return this;
  }

  public boolean isEnabled ()
  {
    return m_bChunkingEnabled;
  }

  /**
   * Enable or disable splitting the file separate chunks. Each chunks is sent
   * in a separate request.
   *
   * @param bEnabled
   *        new value
   * @return this for chaining
   */
  @Nonnull
  public FineUploader5Chunking setEnabled (final boolean bEnabled)
  {
    m_bChunkingEnabled = bEnabled;
    return this;
  }

  public boolean isMandatory ()
  {
    return m_bChunkingMandatory;
  }

  /**
   * Ensure every file is uploaded in chunks, even if the file can only be split
   * up into 1 chunk. Does not apply if chunking is not possible in the current
   * browser.
   *
   * @param bMandatory
   *        new value
   * @return this for chaining
   */
  @Nonnull
  public FineUploader5Chunking setMandatory (final boolean bMandatory)
  {
    m_bChunkingMandatory = bMandatory;
    return this;
  }

  public long getPartSize ()
  {
    return m_nChunkingPartSize;
  }

  /**
   * The maximum size of each chunk, in bytes.
   *
   * @param nPartSize
   *        New value. Must be &gt; 0.
   * @return this for chaining
   */
  @Nonnull
  public FineUploader5Chunking setPartSize (@Nonnegative final long nPartSize)
  {
    ValueEnforcer.isGT0 (nPartSize, "PartSize");
    m_nChunkingPartSize = nPartSize;
    return this;
  }

  @Nonnull
  @Nonempty
  public String getParamNameChunkSize ()
  {
    return m_sChunkingParamNamesChunkSize;
  }

  /**
   * Name of the parameter passed with a chunked request that specifies the size
   * in bytes of the associated chunk.
   *
   * @param sParamNameChunkSize
   *        New value. May neither be <code>null</code> nor empty.
   * @return this for chaining
   */
  @Nonnull
  public FineUploader5Chunking setParamNameChunkSize (@Nonnull @Nonempty final String sParamNameChunkSize)
  {
    ValueEnforcer.notEmpty (sParamNameChunkSize, "ParamNameChunkSize");
    m_sChunkingParamNamesChunkSize = sParamNameChunkSize;
    return this;
  }

  @Nonnull
  @Nonempty
  public String getParamNamePartByteOffset ()
  {
    return m_sChunkingParamNamesPartByteOffset;
  }

  /**
   * Name of the parameter passed with a chunked request that specifies the
   * starting byte of the associated chunk.
   *
   * @param sParamNamePartByteOffset
   *        New value. May neither be <code>null</code> nor empty.
   * @return this for chaining
   */
  @Nonnull
  public FineUploader5Chunking setParamNamePartByteOffset (@Nonnull @Nonempty final String sParamNamePartByteOffset)
  {
    ValueEnforcer.notEmpty (sParamNamePartByteOffset, "ParamNamePartByteOffset");
    m_sChunkingParamNamesPartByteOffset = sParamNamePartByteOffset;
    return this;
  }

  @Nonnull
  @Nonempty
  public String getParamNamePartIndex ()
  {
    return m_sChunkingParamNamesPartIndex;
  }

  /**
   * Name of the parameter passed with a chunked request that specifies the
   * index of the associated partition.
   *
   * @param sParamNamePartIndex
   *        New value. May neither be <code>null</code> nor empty.
   * @return this for chaining
   */
  @Nonnull
  public FineUploader5Chunking setParamNamePartIndex (@Nonnull @Nonempty final String sParamNamePartIndex)
  {
    ValueEnforcer.notEmpty (sParamNamePartIndex, "ParamNamePartIndex");
    m_sChunkingParamNamesPartIndex = sParamNamePartIndex;
    return this;
  }

  @Nonnull
  @Nonempty
  public String getParamNameTotalParts ()
  {
    return m_sChunkingParamNamesTotalParts;
  }

  /**
   * Name of the parameter passed with a chunked request that specifies the
   * total number of chunks associated with the File or Blob.
   *
   * @param sParamNameTotalParts
   *        New value. May neither be <code>null</code> nor empty.
   * @return this for chaining
   */
  @Nonnull
  public FineUploader5Chunking setParamNameTotalParts (@Nonnull @Nonempty final String sParamNameTotalParts)
  {
    ValueEnforcer.notEmpty (sParamNameTotalParts, "ParamNameTotalParts");
    m_sChunkingParamNamesTotalParts = sParamNameTotalParts;
    return this;
  }

  @Nullable
  public ISimpleURL getSuccessEndpoint ()
  {
    return m_aChunkingSuccessEndpoint;
  }

  /**
   * Endpoint to send a POST after all chunks have been successfully uploaded
   * for each file. Required if the concurrent.enabled option is set.
   *
   * @param aSuccessEndpoint
   *        New value. May be <code>null</code>.
   * @return this for chaining
   */
  @Nonnull
  public FineUploader5Chunking setSuccessEndpoint (@Nullable final ISimpleURL aSuccessEndpoint)
  {
    m_aChunkingSuccessEndpoint = aSuccessEndpoint;
    return this;
  }

  @Nonnull
  public JSAssocArray getJSCode ()
  {
    final JSAssocArray aSub = new JSAssocArray ();

    if (m_bChunkingConcurrentEnabled != DEFAULT_CHUNKING_CONCURRENT_ENABLED)
      aSub.add ("concurrent", new JSAssocArray ().add ("enabled", m_bChunkingConcurrentEnabled));
    if (m_bChunkingEnabled != DEFAULT_CHUNKING_ENABLED)
      aSub.add ("enabled", m_bChunkingEnabled);
    if (m_bChunkingMandatory != DEFAULT_CHUNKING_MANDATORY)
      aSub.add ("mandatory", m_bChunkingMandatory);
    if (m_nChunkingPartSize != DEFAULT_CHUNKING_PART_SIZE)
      aSub.add ("partSize", m_nChunkingPartSize);

    final JSAssocArray aParamNames = new JSAssocArray ();
    if (!m_sChunkingParamNamesChunkSize.equals (DEFAULT_CHUNKING_PARAM_NAMES_CHUNK_SIZE))
      aSub.add ("chunkSize", m_sChunkingParamNamesChunkSize);
    if (!m_sChunkingParamNamesPartByteOffset.equals (DEFAULT_CHUNKING_PARAM_NAMES_PART_BYTE_OFFSET))
      aSub.add ("partByteOffset", m_sChunkingParamNamesPartByteOffset);
    if (!m_sChunkingParamNamesPartIndex.equals (DEFAULT_CHUNKING_PARAM_NAMES_PART_INDEX))
      aSub.add ("partIndex", m_sChunkingParamNamesPartIndex);
    if (!m_sChunkingParamNamesTotalParts.equals (DEFAULT_CHUNKING_PARAM_NAMES_TOTAL_PARTS))
      aSub.add ("totalParts", m_sChunkingParamNamesTotalParts);
    if (!aParamNames.isEmpty ())
      aSub.add ("paramNames", aParamNames);

    if (m_aChunkingSuccessEndpoint != null)
      aSub.add ("success",
                new JSAssocArray ().add ("endpoint", m_aChunkingSuccessEndpoint.getAsStringWithEncodedParameters ()));
    return aSub;
  }
}
