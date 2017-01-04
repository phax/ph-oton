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
package com.helger.photon.uictrls.fineupload5;

import java.util.Collection;

import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.helger.commons.ValueEnforcer;
import com.helger.commons.annotation.Nonempty;
import com.helger.commons.annotation.ReturnsMutableCopy;
import com.helger.commons.collection.ext.CommonsLinkedHashSet;
import com.helger.commons.collection.ext.ICommonsOrderedSet;
import com.helger.commons.mime.IMimeType;
import com.helger.html.jscode.JSArray;
import com.helger.html.jscode.JSAssocArray;

/**
 * Wrapper for Fine Uploader 5.x validation part
 *
 * @author Philip Helger
 */
public class FineUploader5Validation implements IFineUploader5Part
{
  public static final int DEFAULT_VALIDATION_ITEM_LIMIT = 0;
  public static final int DEFAULT_VALIDATION_MIN_SIZE_LIMIT = 0;
  public static final int DEFAULT_VALIDATION_SIZE_LIMIT = 0;
  public static final boolean DEFAULT_VALIDATION_STOP_ON_FIRST_INVALID_FILE = true;
  public static final int DEFAULT_VALIDATION_IMAGE_MAX_HEIGHT = 0;
  public static final int DEFAULT_VALIDATION_IMAGE_MAX_WIDTH = 0;
  public static final int DEFAULT_VALIDATION_IMAGE_MIN_HEIGHT = 0;
  public static final int DEFAULT_VALIDATION_IMAGE_MIN_WIDTH = 0;

  private final ICommonsOrderedSet <IMimeType> m_aValidationAcceptFiles = new CommonsLinkedHashSet <> ();
  private final ICommonsOrderedSet <String> m_aValidationAllowedExtensions = new CommonsLinkedHashSet <> ();
  private int m_nValidationItemLimit = DEFAULT_VALIDATION_ITEM_LIMIT;
  private int m_nValidationMinSizeLimit = DEFAULT_VALIDATION_MIN_SIZE_LIMIT;
  private int m_nValidationSizeLimit = DEFAULT_VALIDATION_SIZE_LIMIT;
  private boolean m_bValidationStopOnFirstInvalidFile = DEFAULT_VALIDATION_STOP_ON_FIRST_INVALID_FILE;
  private int m_nValidationImageMaxHeight = DEFAULT_VALIDATION_IMAGE_MAX_HEIGHT;
  private int m_nValidationImageMaxWidth = DEFAULT_VALIDATION_IMAGE_MAX_WIDTH;
  private int m_nValidationImageMinHeight = DEFAULT_VALIDATION_IMAGE_MIN_HEIGHT;
  private int m_nValidationImageMinWidth = DEFAULT_VALIDATION_IMAGE_MIN_WIDTH;

  public FineUploader5Validation ()
  {}

  @Nonnull
  @ReturnsMutableCopy
  public ICommonsOrderedSet <IMimeType> getAllAcceptFiles ()
  {
    return m_aValidationAcceptFiles.getClone ();
  }

  /**
   * Used by the file selection dialog. Restrict the valid file types that
   * appear in the selection dialog by listing valid content-type specifiers
   * here. See docs on the accept attribute of the &lt;input&gt; element
   *
   * @param aAcceptFiles
   *        The MIME types to be set.
   * @return this for chaining
   */
  @Nonnull
  public FineUploader5Validation setAcceptFiles (@Nullable final Collection <? extends IMimeType> aAcceptFiles)
  {
    m_aValidationAcceptFiles.setAll (aAcceptFiles);
    return this;
  }

  /**
   * Used by the file selection dialog. Restrict the valid file types that
   * appear in the selection dialog by listing valid content-type specifiers
   * here. See docs on the accept attribute of the &lt;input&gt; element
   *
   * @param aAcceptFiles
   *        The MIME types to be added.
   * @return this for chaining
   */
  @Nonnull
  public FineUploader5Validation addAcceptFiles (@Nullable final Collection <? extends IMimeType> aAcceptFiles)
  {
    if (aAcceptFiles != null)
      m_aValidationAcceptFiles.addAll (aAcceptFiles);
    return this;
  }

  /**
   * Used by the file selection dialog. Restrict the valid file types that
   * appear in the selection dialog by listing valid content-type specifiers
   * here. See docs on the accept attribute of the &lt;input&gt; element
   *
   * @param aAcceptFile
   *        The MIME type to be added. May not be <code>null</code>.
   * @return this for chaining
   */
  @Nonnull
  public FineUploader5Validation addAcceptFile (@Nonnull final IMimeType aAcceptFile)
  {
    ValueEnforcer.notNull (aAcceptFile, "AcceptFile");
    m_aValidationAcceptFiles.add (aAcceptFile);
    return this;
  }

  @Nonnull
  @ReturnsMutableCopy
  public ICommonsOrderedSet <String> getAllAllowedExtensions ()
  {
    return m_aValidationAllowedExtensions.getClone ();
  }

  /**
   * Specify file valid file extensions here to restrict uploads to specific
   * types.
   *
   * @param aAllowedExtensions
   *        The allowed extensions to be set.
   * @return this for chaining
   */
  @Nonnull
  public FineUploader5Validation setAllowedExtensions (@Nullable final Collection <String> aAllowedExtensions)
  {
    m_aValidationAllowedExtensions.setAll (aAllowedExtensions);
    return this;
  }

  /**
   * Specify file valid file extensions here to restrict uploads to specific
   * types.
   *
   * @param aAllowedExtensions
   *        The allowed extensions to be added.
   * @return this for chaining
   */
  @Nonnull
  public FineUploader5Validation addAllowedExtensions (@Nullable final Collection <String> aAllowedExtensions)
  {
    if (aAllowedExtensions != null)
      m_aValidationAllowedExtensions.addAll (aAllowedExtensions);
    return this;
  }

  /**
   * Specify file valid file extensions here to restrict uploads to specific
   * types.
   *
   * @param sAllowedExtension
   *        The allowed extension to be added. E.g. ("jpeg", "jpg", "gif")
   * @return this for chaining
   */
  @Nonnull
  public FineUploader5Validation addAllowedExtension (@Nonnull @Nonempty final String sAllowedExtension)
  {
    ValueEnforcer.notEmpty (sAllowedExtension, "AllowedExtension");
    m_aValidationAllowedExtensions.add (sAllowedExtension);
    return this;
  }

  @Nonnegative
  public int getItemLimit ()
  {
    return m_nValidationItemLimit;
  }

  /**
   * Maximum number of items that can be potentially uploaded in this session.
   * Will reject all items that are added or retried after this limit is
   * reached.
   *
   * @param nItemLimit
   *        Minimum size limit. 0 == unlimited
   * @return this for chaining
   */
  @Nonnull
  public FineUploader5Validation setItemLimit (@Nonnegative final int nItemLimit)
  {
    ValueEnforcer.isGE0 (nItemLimit, "ItemLimit");
    m_nValidationItemLimit = nItemLimit;
    return this;
  }

  @Nonnegative
  public int getMinSizeLimit ()
  {
    return m_nValidationMinSizeLimit;
  }

  /**
   * The minimum allowable size, in bytes, for an item.
   *
   * @param nMinSizeLimit
   *        Minimum size limit. 0 == unlimited
   * @return this for chaining
   */
  @Nonnull
  public FineUploader5Validation setMinSizeLimit (@Nonnegative final int nMinSizeLimit)
  {
    ValueEnforcer.isGE0 (nMinSizeLimit, "MinSizeLimit");
    m_nValidationMinSizeLimit = nMinSizeLimit;
    return this;
  }

  @Nonnegative
  public int getSizeLimit ()
  {
    return m_nValidationSizeLimit;
  }

  /**
   * The maximum allowable size, in bytes, for an item.
   *
   * @param nSizeLimit
   *        Size limit. 0 == unlimited
   * @return this for chaining
   */
  @Nonnull
  public FineUploader5Validation setSizeLimit (@Nonnegative final int nSizeLimit)
  {
    ValueEnforcer.isGE0 (nSizeLimit, "SizeLimit");
    m_nValidationSizeLimit = nSizeLimit;
    return this;
  }

  public boolean isStopOnFirstInvalidFile ()
  {
    return m_bValidationStopOnFirstInvalidFile;
  }

  /**
   * When true the first invalid item will stop processing further files.
   *
   * @param bStopOnFirstInvalidFile
   *        <code>false</code> to not stop
   * @return this for chaining
   */
  @Nonnull
  public FineUploader5Validation setStopOnFirstInvalidFile (final boolean bStopOnFirstInvalidFile)
  {
    m_bValidationStopOnFirstInvalidFile = bStopOnFirstInvalidFile;
    return this;
  }

  @Nonnegative
  public int getImageMaxHeight ()
  {
    return m_nValidationImageMaxHeight;
  }

  /**
   * Restrict images to a maximum height in pixels (wherever possible).
   *
   * @param nImageMaxHeight
   *        New value. Must be &ge; 0.
   * @return this for chaining
   */
  @Nonnull
  public FineUploader5Validation setImageMaxHeight (@Nonnegative final int nImageMaxHeight)
  {
    ValueEnforcer.isGE0 (nImageMaxHeight, "ImageMaxHeight");
    m_nValidationImageMaxHeight = nImageMaxHeight;
    return this;
  }

  @Nonnegative
  public int getImageMaxWidth ()
  {
    return m_nValidationImageMaxWidth;
  }

  /**
   * Restrict images to a maximum width in pixels (wherever possible).
   *
   * @param nImageMaxWidth
   *        New value. Must be &ge; 0.
   * @return this for chaining
   */
  @Nonnull
  public FineUploader5Validation setImageMaxWidth (@Nonnegative final int nImageMaxWidth)
  {
    ValueEnforcer.isGE0 (nImageMaxWidth, "ImageMaxWidth");
    m_nValidationImageMaxWidth = nImageMaxWidth;
    return this;
  }

  @Nonnegative
  public int getImageMinHeight ()
  {
    return m_nValidationImageMinHeight;
  }

  /**
   * Restrict images to a minimum height in pixels (wherever possible).
   *
   * @param nImageMinHeight
   *        New value. Must be &ge; 0.
   * @return this for chaining
   */
  @Nonnull
  public FineUploader5Validation setImageMinHeight (@Nonnegative final int nImageMinHeight)
  {
    ValueEnforcer.isGE0 (nImageMinHeight, "ImageMinHeight");
    m_nValidationImageMinHeight = nImageMinHeight;
    return this;
  }

  @Nonnegative
  public int getImageMinWidth ()
  {
    return m_nValidationImageMinWidth;
  }

  /**
   * Restrict images to a minimum width in pixels (wherever possible).
   *
   * @param nImageMinWidth
   *        New value. Must be &ge; 0.
   * @return this for chaining
   */
  @Nonnull
  public FineUploader5Validation setImageMinWidth (@Nonnegative final int nImageMinWidth)
  {
    ValueEnforcer.isGE0 (nImageMinWidth, "ImageMinWidth");
    m_nValidationImageMinWidth = nImageMinWidth;
    return this;
  }

  @Nonnull
  public JSAssocArray getJSCode ()
  {
    final JSAssocArray aSub = new JSAssocArray ();

    if (m_aValidationAcceptFiles.isNotEmpty ())
    {
      final JSArray aArray = new JSArray ();
      for (final IMimeType aMimeType : m_aValidationAcceptFiles)
        aArray.add (aMimeType.getAsString ());
      aSub.add ("acceptFiles", aArray);
    }
    if (m_aValidationAllowedExtensions.isNotEmpty ())
      aSub.add ("allowedExtensions", new JSArray ().addAll (m_aValidationAllowedExtensions));
    if (m_nValidationItemLimit != DEFAULT_VALIDATION_ITEM_LIMIT)
      aSub.add ("itemLimit", m_nValidationSizeLimit);
    if (m_nValidationMinSizeLimit != DEFAULT_VALIDATION_MIN_SIZE_LIMIT)
      aSub.add ("minSizeLimit", m_nValidationMinSizeLimit);
    if (m_nValidationSizeLimit != DEFAULT_VALIDATION_SIZE_LIMIT)
      aSub.add ("sizeLimit", m_nValidationSizeLimit);
    if (m_bValidationStopOnFirstInvalidFile != DEFAULT_VALIDATION_STOP_ON_FIRST_INVALID_FILE)
      aSub.add ("stopOnFirstInvalidFile", m_bValidationStopOnFirstInvalidFile);

    final JSAssocArray aImage = new JSAssocArray ();
    if (m_nValidationImageMaxHeight != DEFAULT_VALIDATION_IMAGE_MAX_HEIGHT)
      aImage.add ("maxHeight", m_nValidationImageMaxHeight);
    if (m_nValidationImageMaxWidth != DEFAULT_VALIDATION_IMAGE_MAX_WIDTH)
      aImage.add ("maxWidth", m_nValidationImageMaxWidth);
    if (m_nValidationImageMinHeight != DEFAULT_VALIDATION_IMAGE_MIN_HEIGHT)
      aImage.add ("minHeight", m_nValidationImageMinHeight);
    if (m_nValidationImageMinWidth != DEFAULT_VALIDATION_IMAGE_MIN_WIDTH)
      aImage.add ("minWidth", m_nValidationImageMinWidth);
    if (!aImage.isEmpty ())
      aSub.add ("image", aImage);
    return aSub;
  }
}
