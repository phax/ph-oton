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
package com.helger.photon.uictrls.fineupload5;

import java.util.Locale;
import java.util.Set;

import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.helger.commons.ValueEnforcer;
import com.helger.commons.annotation.CodingStyleguideUnaware;
import com.helger.commons.annotation.Nonempty;
import com.helger.commons.annotation.OverrideOnDemand;
import com.helger.commons.annotation.ReturnsMutableObject;
import com.helger.commons.collection.impl.CommonsLinkedHashSet;
import com.helger.commons.collection.impl.ICommonsOrderedSet;
import com.helger.commons.string.StringHelper;
import com.helger.html.jscode.IJSExpression;
import com.helger.html.jscode.JSArray;
import com.helger.html.jscode.JSAssocArray;
import com.helger.html.jscode.html.JSHtml;

/**
 * Wrapper for Fine Uploader 5.x
 *
 * @author Philip Helger
 */
public class FineUploader5Core implements IFineUploader5Part
{
  protected static final String KEY_MESSAGES = "messages";
  protected static final String KEY_TEXT = "text";
  protected static final String KEY_BLOBS = "blobs";
  protected static final String KEY_CHUNKING = "chunking";
  protected static final String KEY_CORS = "cors";
  protected static final String KEY_DELETE_FILE = "deleteFile";
  protected static final String KEY_FORM = "form";
  protected static final String KEY_PASTE = "paste";
  protected static final String KEY_RESUME = "resume";
  protected static final String KEY_RETRY = "retry";
  protected static final String KEY_REQUEST = "request";
  protected static final String KEY_SESSION = "session";
  protected static final String KEY_VALIDATION = "validation";
  protected static final String KEY_WORKAROUNDS = "workarounds";

  public static final boolean DEFAULT_CORE_AUTO_UPLOAD = true;
  public static final boolean DEFAULT_CORE_DEBUG = false;
  public static final boolean DEFAULT_CORE_DISABLE_CANCEL_FOR_FORM_UPLOADS = false;
  public static final int DEFAULT_CORE_MAX_CONNECTIONS = 3;
  public static final boolean DEFAULT_CORE_MULTIPLE = true;

  // text
  @CodingStyleguideUnaware
  public static final Set <String> DEFAULT_TEXT_SIZE_SYMBOLS = new CommonsLinkedHashSet <> ("kB",
                                                                                            "MB",
                                                                                            "GB",
                                                                                            "TB",
                                                                                            "PB",
                                                                                            "EB").getAsUnmodifiable ();

  // generic
  private final Locale m_aDisplayLocale;

  // core
  private boolean m_bCoreAutoUpload = DEFAULT_CORE_AUTO_UPLOAD;
  private String m_sCoreButtonElementID;
  private boolean m_bCoreDebug = DEFAULT_CORE_DEBUG;
  private boolean m_bCoreDisableCancelForFormUploads = DEFAULT_CORE_DISABLE_CANCEL_FOR_FORM_UPLOADS;
  private IJSExpression m_aCoreFormatFileName;
  private int m_nCoreMaxConnections = DEFAULT_CORE_MAX_CONNECTIONS;
  private boolean m_bCoreMultiple = DEFAULT_CORE_MULTIPLE;

  // messages
  // All in EFineUploader5CoreText

  // text
  // partially in EFineUploader5CoreText
  private final ICommonsOrderedSet <String> m_aTextSizeSymbols = new CommonsLinkedHashSet <> (DEFAULT_TEXT_SIZE_SYMBOLS);

  private final FineUploader5Blobs m_aBlobs = new FineUploader5Blobs ();
  // TODO camera
  private final FineUploader5Chunking m_aChunking = new FineUploader5Chunking ();
  private final FineUploader5Cors m_aCors = new FineUploader5Cors ();
  private final FineUploader5DeleteFile m_aDeleteFile = new FineUploader5DeleteFile ();
  // TODO extraButtons
  private final FineUploader5Form m_aForm = new FineUploader5Form ();
  private final FineUploader5Paste m_aPaste = new FineUploader5Paste ();
  private final FineUploader5Resume m_aResume = new FineUploader5Resume ();
  private final FineUploader5Retry m_aRetry = new FineUploader5Retry ();
  private final FineUploader5Request m_aRequest = new FineUploader5Request ();
  // TODO scaling
  private final FineUploader5Session m_aSession = new FineUploader5Session ();
  private final FineUploader5Validation m_aValidation = new FineUploader5Validation ();
  private final FineUploader5Workarounds m_aWorkarounds = new FineUploader5Workarounds ();

  public FineUploader5Core (@Nullable final Locale aDisplayLocale)
  {
    m_aDisplayLocale = aDisplayLocale;
  }

  @Nullable
  public Locale getDisplayLocale ()
  {
    return m_aDisplayLocale;
  }

  public boolean isAutoUpload ()
  {
    return m_bCoreAutoUpload;
  }

  /**
   * Set to false if you want to be able to upload queued items later by calling
   * the uploadStoredFiles() method.
   *
   * @param bAutoUpload
   *        New value
   * @return this
   */
  @Nonnull
  public FineUploader5Core setAutoUpload (final boolean bAutoUpload)
  {
    m_bCoreAutoUpload = bAutoUpload;
    return this;
  }

  @Nullable
  public String getButtonElementID ()
  {
    return m_sCoreButtonElementID;
  }

  /**
   * Specify an element to use as the 'select files' button. Cannot be a
   * &lt;button&gt;.
   *
   * @param sButtonElementID
   *        Element ID of the button
   * @return this
   */
  @Nonnull
  public FineUploader5Core setButtonElementID (@Nullable final String sButtonElementID)
  {
    m_sCoreButtonElementID = sButtonElementID;
    return this;
  }

  public boolean isDebug ()
  {
    return m_bCoreDebug;
  }

  /**
   * This will result in log messages being written to the window.console
   * object.
   *
   * @param bCoreDebug
   *        New value
   * @return this
   */
  @Nonnull
  public FineUploader5Core setDebug (final boolean bCoreDebug)
  {
    m_bCoreDebug = bCoreDebug;
    return this;
  }

  public boolean isDisableCancelForFormUploads ()
  {
    return m_bCoreDisableCancelForFormUploads;
  }

  /**
   * When true the cancel link does not appear next to files when the form
   * uploader is used.
   *
   * @param bDisableCancelForFormUploads
   *        New value
   * @return this
   */
  @Nonnull
  public FineUploader5Core setDisableCancelForFormUploads (final boolean bDisableCancelForFormUploads)
  {
    m_bCoreDisableCancelForFormUploads = bDisableCancelForFormUploads;
    return this;
  }

  @Nullable
  public IJSExpression getFormatFileName ()
  {
    return m_aCoreFormatFileName;
  }

  /**
   * Provide a function to control the display of file names. The raw file name
   * is passed into the function when it is invoked. Your function may return a
   * modified file name. Note that this does not affect the actual file name,
   * only the displayed file name.
   *
   * @param aCoreFormatFileName
   *        New value
   * @return this
   */
  @Nonnull
  public FineUploader5Core setFormatFileName (@Nullable final IJSExpression aCoreFormatFileName)
  {
    m_aCoreFormatFileName = aCoreFormatFileName;
    return this;
  }

  @Nonnegative
  public int getMaxConnections ()
  {
    return m_nCoreMaxConnections;
  }

  /**
   * Maximum allowable concurrent requests
   *
   * @param nMaxConnections
   *        Maximum number. Must be &gt; 0.
   * @return this
   */
  @Nonnull
  public FineUploader5Core setMaxConnections (@Nonnegative final int nMaxConnections)
  {
    ValueEnforcer.isGT0 (nMaxConnections, "MaxConnections");
    m_nCoreMaxConnections = nMaxConnections;
    return this;
  }

  public boolean isMultiple ()
  {
    return m_bCoreMultiple;
  }

  /**
   * When false this will prevent the user from simultaneously selecting or
   * dropping more than one item.
   *
   * @param bMultiple
   *        <code>true</code> for multiple, <code>false</code> for single
   * @return this
   */
  @Nonnull
  public FineUploader5Core setMultiple (final boolean bMultiple)
  {
    m_bCoreMultiple = bMultiple;
    return this;
  }

  @Nonnull
  @ReturnsMutableObject ("design")
  public FineUploader5Blobs getBlobs ()
  {
    return m_aBlobs;
  }

  @Nonnull
  @ReturnsMutableObject ("design")
  public FineUploader5Chunking getChunking ()
  {
    return m_aChunking;
  }

  @Nonnull
  @ReturnsMutableObject ("design")
  public FineUploader5Cors getCors ()
  {
    return m_aCors;
  }

  @Nonnull
  @ReturnsMutableObject ("design")
  public FineUploader5DeleteFile getDeleteFile ()
  {
    return m_aDeleteFile;
  }

  @Nonnull
  @ReturnsMutableObject ("design")
  public FineUploader5Form getForm ()
  {
    return m_aForm;
  }

  @Nonnull
  @ReturnsMutableObject ("design")
  public FineUploader5Paste getPaste ()
  {
    return m_aPaste;
  }

  @Nonnull
  @ReturnsMutableObject ("design")
  public FineUploader5Resume getResume ()
  {
    return m_aResume;
  }

  @Nonnull
  @ReturnsMutableObject ("design")
  public FineUploader5Retry getRetry ()
  {
    return m_aRetry;
  }

  @Nonnull
  @ReturnsMutableObject ("design")
  public FineUploader5Request getRequest ()
  {
    return m_aRequest;
  }

  @Nonnull
  @ReturnsMutableObject ("design")
  public FineUploader5Session getSession ()
  {
    return m_aSession;
  }

  @Nonnull
  @ReturnsMutableObject ("design")
  public FineUploader5Validation getValidation ()
  {
    return m_aValidation;
  }

  @Nonnull
  @ReturnsMutableObject ("design")
  public FineUploader5Workarounds getWorkarounds ()
  {
    return m_aWorkarounds;
  }

  /**
   * @param sKey
   *        The key under which it is added to the root JSON. Neither
   *        <code>null</code> nor empty.
   * @param aAssocArray
   *        The JSON messages object to extend
   * @param aDisplayLocale
   *        The locale to be used for test resolving. May be <code>null</code>
   *        if none was provided in the constructor.
   */
  @OverrideOnDemand
  protected void extendJSONPart (@Nonnull @Nonempty final String sKey,
                                 @Nonnull final JSAssocArray aAssocArray,
                                 @Nullable final Locale aDisplayLocale)
  {}

  /**
   * @param aAssocArray
   *        The JSON messages object to extend
   * @param aDisplayLocale
   *        The locale to be used for test resolving. May be <code>null</code>
   *        if none passed in the constructor.
   */
  @OverrideOnDemand
  protected void extendJSON (@Nonnull final JSAssocArray aAssocArray, @Nullable final Locale aDisplayLocale)
  {}

  private void _extendAndAdd (@Nonnull final JSAssocArray aTo,
                              @Nonnull @Nonempty final String sKey,
                              @Nonnull final JSAssocArray aAssocArray)
  {
    extendJSONPart (sKey, aAssocArray, m_aDisplayLocale);
    aTo.add (sKey, aAssocArray);
  }

  @Nonnull
  public JSAssocArray getJSCode ()
  {
    final JSAssocArray ret = new JSAssocArray ();

    // Core
    if (m_bCoreAutoUpload != DEFAULT_CORE_AUTO_UPLOAD)
      ret.add ("autoUpload", m_bCoreAutoUpload);
    if (StringHelper.hasText (m_sCoreButtonElementID))
      ret.add ("button", JSHtml.documentGetElementById (m_sCoreButtonElementID));
    if (m_bCoreDebug != DEFAULT_CORE_DEBUG)
      ret.add ("debug", m_bCoreDebug);
    if (m_bCoreDisableCancelForFormUploads != DEFAULT_CORE_DISABLE_CANCEL_FOR_FORM_UPLOADS)
      ret.add ("disableCancelForFormUploads", m_bCoreDisableCancelForFormUploads);
    if (m_aCoreFormatFileName != null)
      ret.add ("formatFileName", m_aCoreFormatFileName);
    if (m_nCoreMaxConnections != DEFAULT_CORE_MAX_CONNECTIONS)
      ret.add ("maxConnections", m_nCoreMaxConnections);
    if (m_bCoreMultiple != DEFAULT_CORE_MULTIPLE)
      ret.add ("multiple", m_bCoreMultiple);

    // messages
    if (m_aDisplayLocale != null)
    {
      final JSAssocArray aSub = new JSAssocArray ();
      aSub.add ("emptyError", EFineUploader5CoreText.EMPTY_ERROR.getDisplayText (m_aDisplayLocale));
      aSub.add ("maxHeightImageError", EFineUploader5CoreText.MAX_HEIGHT_IMAGE_ERROR.getDisplayText (m_aDisplayLocale));
      aSub.add ("maxWidthImageError", EFineUploader5CoreText.MAX_WIDTH_IMAGE_ERROR.getDisplayText (m_aDisplayLocale));
      aSub.add ("minHeightImageError", EFineUploader5CoreText.MIN_HEIGHT_IMAGE_ERROR.getDisplayText (m_aDisplayLocale));
      aSub.add ("minWidthImageError", EFineUploader5CoreText.MIN_WIDTH_IMAGE_ERROR.getDisplayText (m_aDisplayLocale));
      aSub.add ("minSizeError", EFineUploader5CoreText.MIN_SIZE_ERROR.getDisplayText (m_aDisplayLocale));
      aSub.add ("noFilesError", EFineUploader5CoreText.NO_FILES_ERROR.getDisplayText (m_aDisplayLocale));
      aSub.add ("onLeave", EFineUploader5CoreText.ON_LEAVE.getDisplayText (m_aDisplayLocale));
      aSub.add ("retryFailTooManyItemsError",
                EFineUploader5CoreText.RETRY_FAIL_TOO_MANY_ITEMS_ERROR.getDisplayText (m_aDisplayLocale));
      aSub.add ("sizeError", EFineUploader5CoreText.SIZE_ERROR.getDisplayText (m_aDisplayLocale));
      aSub.add ("tooManyItemsError", EFineUploader5CoreText.TOO_MANY_ITEMS_ERROR.getDisplayText (m_aDisplayLocale));
      aSub.add ("typeError", EFineUploader5CoreText.TYPE_ERROR.getDisplayText (m_aDisplayLocale));
      aSub.add ("unsupportedBrowserIos8Safari",
                EFineUploader5CoreText.UNSUPPORTED_BROWSER_IOS8_SAFARI.getDisplayText (m_aDisplayLocale));

      // extended
      _extendAndAdd (ret, KEY_MESSAGES, aSub);
    }

    // text
    {
      final JSAssocArray aSub = new JSAssocArray ();
      if (m_aDisplayLocale != null)
      {
        aSub.add ("defaultResponseError",
                  EFineUploader5CoreText.DEFAULT_RESPONSE_ERROR.getDisplayText (m_aDisplayLocale));
        aSub.add ("fileInputTitle", EFineUploader5CoreText.FILE_INPUT_TITLE.getDisplayText (m_aDisplayLocale));
      }
      if (!m_aTextSizeSymbols.equals (DEFAULT_TEXT_SIZE_SYMBOLS))
        aSub.add ("sizeSymbols", new JSArray ().addAll (m_aTextSizeSymbols));

      _extendAndAdd (ret, KEY_TEXT, aSub);
    }

    _extendAndAdd (ret, KEY_BLOBS, m_aBlobs.getJSCode ());
    // TODO camera
    _extendAndAdd (ret, KEY_CHUNKING, m_aChunking.getJSCode ());
    _extendAndAdd (ret, KEY_CORS, m_aCors.getJSCode ());
    _extendAndAdd (ret, KEY_DELETE_FILE, m_aDeleteFile.getJSCode ());
    // TODO extraButtons
    _extendAndAdd (ret, KEY_FORM, m_aForm.getJSCode ());
    _extendAndAdd (ret, KEY_PASTE, m_aPaste.getJSCode ());
    _extendAndAdd (ret, KEY_RESUME, m_aResume.getJSCode ());
    _extendAndAdd (ret, KEY_RETRY, m_aRetry.getJSCode ());
    _extendAndAdd (ret, KEY_REQUEST, m_aRequest.getJSCode ());
    // TODO scaling
    _extendAndAdd (ret, KEY_SESSION, m_aSession.getJSCode ());
    _extendAndAdd (ret, KEY_VALIDATION, m_aValidation.getJSCode ());
    _extendAndAdd (ret, KEY_WORKAROUNDS, m_aWorkarounds.getJSCode ());

    extendJSON (ret, m_aDisplayLocale);
    return ret;
  }
}
