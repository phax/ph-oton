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

import java.util.Locale;
import java.util.Map;
import java.util.Set;

import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.helger.commons.ValueEnforcer;
import com.helger.commons.annotation.Nonempty;
import com.helger.commons.annotation.OverrideOnDemand;
import com.helger.commons.annotation.ReturnsMutableCopy;
import com.helger.commons.annotation.ReturnsMutableObject;
import com.helger.commons.collection.ext.CommonsLinkedHashMap;
import com.helger.commons.collection.ext.CommonsLinkedHashSet;
import com.helger.commons.collection.ext.ICommonsOrderedMap;
import com.helger.commons.collection.ext.ICommonsOrderedSet;
import com.helger.commons.mime.IMimeType;
import com.helger.commons.string.StringHelper;
import com.helger.commons.url.ISimpleURL;
import com.helger.commons.url.SimpleURL;
import com.helger.html.jscode.IJSExpression;
import com.helger.html.jscode.JSArray;
import com.helger.html.jscode.JSAssocArray;
import com.helger.html.jscode.html.JSHtml;
import com.helger.http.EHTTPMethod;

/**
 * Wrapper for Fine Uploader 5.x
 *
 * @author Philip Helger
 */
public class FineUploader5Core implements IFineUploader5Part
{
  public static final boolean DEFAULT_CORE_AUTO_UPLOAD = true;
  public static final boolean DEFAULT_CORE_DEBUG = false;
  public static final boolean DEFAULT_CORE_DISABLE_CANCEL_FOR_FORM_UPLOADS = false;
  public static final int DEFAULT_CORE_MAX_CONNECTIONS = 3;
  public static final boolean DEFAULT_CORE_MULTIPLE = true;

  // retry
  public static final int DEFAULT_RETRY_AUTO_ATTEMPT_DELAY = 5;
  public static final boolean DEFAULT_RETRY_ENABLE_AUTO = false;
  public static final int DEFAULT_RETRY_MAX_AUTO_ATTEMPTS = 3;
  public static final String DEFAULT_RETRY_PREVENT_RETRY_RESPONSE_PROPERTY = "preventRetry";

  // request
  public static final ISimpleURL DEFAULT_REQUEST_ENDPOINT = new SimpleURL ("/server/upload");
  public static final String DEFAULT_REQUEST_FILENAME_PARAM = "qqfilename";
  public static final boolean DEFAULT_REQUEST_FORCE_MULTIPART = true;
  public static final String DEFAULT_REQUEST_INPUT_NAME = "qqfile";
  public static final EHTTPMethod DEFAULT_REQUEST_METHOD = EHTTPMethod.POST;
  public static final boolean DEFAULT_REQUEST_PARAMS_IN_BODY = true;
  public static final String DEFAULT_REQUEST_UUID_NAME = "qquuid";
  public static final String DEFAULT_REQUEST_TOTAL_FILE_SIZE_NAME = "qqtotalfilesize";

  // session
  public static final boolean DEFAULT_SESSION_REFRESH_ON_RESET = true;

  // text
  public static final Set <String> DEFAULT_TEXT_SIZE_SYMBOLS = new CommonsLinkedHashSet<> ("kB",
                                                                                           "MB",
                                                                                           "GB",
                                                                                           "TB",
                                                                                           "PB",
                                                                                           "EB").getAsUnmodifiable ();

  // validation
  public static final int DEFAULT_VALIDATION_ITEM_LIMIT = 0;
  public static final int DEFAULT_VALIDATION_MIN_SIZE_LIMIT = 0;
  public static final int DEFAULT_VALIDATION_SIZE_LIMIT = 0;
  public static final boolean DEFAULT_VALIDATION_STOP_ON_FIRST_INVALID_FILE = true;
  public static final int DEFAULT_VALIDATION_IMAGE_MAX_HEIGHT = 0;
  public static final int DEFAULT_VALIDATION_IMAGE_MAX_WIDTH = 0;
  public static final int DEFAULT_VALIDATION_IMAGE_MIN_HEIGHT = 0;
  public static final int DEFAULT_VALIDATION_IMAGE_MIN_WIDTH = 0;

  // workarounds
  public static final boolean DEFAULT_WORKAROUNDS = true;

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

  private final FineUploader5Blobs m_aBlobs = new FineUploader5Blobs ();
  // TODO camera
  private final FineUploader5Chunking m_aChunking = new FineUploader5Chunking ();
  private final FineUploader5Cors m_aCors = new FineUploader5Cors ();
  private final FineUploader5DeleteFile m_aDeleteFile = new FineUploader5DeleteFile ();
  // TODO extraButtons
  private final FineUploader5Form m_aForm = new FineUploader5Form ();
  private final FineUploader5Paste m_aPaste = new FineUploader5Paste ();
  private final FineUploader5Resume m_aResume = new FineUploader5Resume ();

  // retry
  private int m_nRetryAutoAttemptDelay = DEFAULT_RETRY_AUTO_ATTEMPT_DELAY;
  private boolean m_bRetryEnableAuto = DEFAULT_RETRY_ENABLE_AUTO;
  private int m_nRetryMaxAutoAttempts = DEFAULT_RETRY_MAX_AUTO_ATTEMPTS;
  private String m_sRetryPreventRetryResponseProperty = DEFAULT_RETRY_PREVENT_RETRY_RESPONSE_PROPERTY;

  // request
  private final ICommonsOrderedMap <String, String> m_aRequestCustomHeaders = new CommonsLinkedHashMap<> ();
  private ISimpleURL m_aRequestEndpoint = DEFAULT_REQUEST_ENDPOINT;
  private final String m_sRequestFilenameParam = DEFAULT_REQUEST_FILENAME_PARAM;
  private boolean m_bRequestForceMultipart = DEFAULT_REQUEST_FORCE_MULTIPART;
  private String m_sRequestInputName = DEFAULT_REQUEST_INPUT_NAME;
  private final EHTTPMethod m_eRequestMethod = DEFAULT_REQUEST_METHOD;
  private final ICommonsOrderedMap <String, String> m_aRequestParams = new CommonsLinkedHashMap<> ();
  private boolean m_bRequestParamsInBody = DEFAULT_REQUEST_PARAMS_IN_BODY;
  private final String m_sRequestUUIDName = DEFAULT_REQUEST_UUID_NAME;
  private final String m_sRequestTotalFileSizeName = DEFAULT_REQUEST_TOTAL_FILE_SIZE_NAME;

  // TODO scaling

  // session
  private final ICommonsOrderedMap <String, String> m_aSessionCustomHeaders = new CommonsLinkedHashMap<> ();
  private ISimpleURL m_aSessionEndpoint;
  private final ICommonsOrderedMap <String, String> m_aSessionParams = new CommonsLinkedHashMap<> ();
  private final boolean m_bSessionRefreshOnReset = DEFAULT_SESSION_REFRESH_ON_RESET;

  // text
  // partially in EFineUploader5CoreText
  private final ICommonsOrderedSet <String> m_aTextSizeSymbols = new CommonsLinkedHashSet<> (DEFAULT_TEXT_SIZE_SYMBOLS);

  // validation
  private final ICommonsOrderedSet <IMimeType> m_aValidationAcceptFiles = new CommonsLinkedHashSet<> ();
  private final ICommonsOrderedSet <String> m_aValidationAllowedExtensions = new CommonsLinkedHashSet<> ();
  private final int m_nValidationItemLimit = DEFAULT_VALIDATION_ITEM_LIMIT;
  private int m_nValidationMinSizeLimit = DEFAULT_VALIDATION_MIN_SIZE_LIMIT;
  private int m_nValidationSizeLimit = DEFAULT_VALIDATION_SIZE_LIMIT;
  private boolean m_bValidationStopOnFirstInvalidFile = DEFAULT_VALIDATION_STOP_ON_FIRST_INVALID_FILE;
  private final int m_nValidationImageMaxHeight = DEFAULT_VALIDATION_IMAGE_MAX_HEIGHT;
  private final int m_nValidationImageMaxWidth = DEFAULT_VALIDATION_IMAGE_MAX_WIDTH;
  private final int m_nValidationImageMinHeight = DEFAULT_VALIDATION_IMAGE_MIN_HEIGHT;
  private final int m_nValidationImageMinWidth = DEFAULT_VALIDATION_IMAGE_MIN_WIDTH;

  // workarounds
  private final boolean m_bWorkaroundsIosEmptyVideo = DEFAULT_WORKAROUNDS;
  private final boolean m_bWorkaroundsIos8BrowserCrash = DEFAULT_WORKAROUNDS;
  private final boolean m_bWorkaroundsIos8SafariUploads = DEFAULT_WORKAROUNDS;

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

  // OLD

  @Nonnull
  public ISimpleURL getRequestEndpoint ()
  {
    return m_aRequestEndpoint;
  }

  /**
   * The is the endpoint used by both the form and ajax uploader. In the case of
   * the form uploader, it is part of the form's action attribute value along
   * with all parameters. In the case of the ajax uploader, it is makes up part
   * of the URL of the XHR request (again, along with the parameters).
   *
   * @param aRequestEndpoint
   *        The new action URL. May not be <code>null</code>.
   * @return this
   */
  @Nonnull
  public FineUploader5Core setRequestEndpoint (@Nonnull final ISimpleURL aRequestEndpoint)
  {
    m_aRequestEndpoint = ValueEnforcer.notNull (aRequestEndpoint, "RequestEndpoint");
    return this;
  }

  @Nonnull
  @ReturnsMutableCopy
  public ICommonsOrderedMap <String, String> getAllRequestParams ()
  {
    return m_aRequestParams.getClone ();
  }

  /**
   * These parameters are sent with the request to the endpoint specified in the
   * action option.
   *
   * @param aParams
   *        New parameters to be set.
   * @return this
   */
  @Nonnull
  public FineUploader5Core setRequestParams (@Nullable final Map <String, String> aParams)
  {
    m_aRequestParams.setAll (aParams);
    return this;
  }

  /**
   * These parameters are sent with the request to the endpoint specified in the
   * action option.
   *
   * @param aParams
   *        New parameters to be added.
   * @return this
   */
  @Nonnull
  public FineUploader5Core addRequestParams (@Nullable final Map <String, String> aParams)
  {
    m_aRequestParams.addAll (aParams);
    return this;
  }

  /**
   * These parameters are sent with the request to the endpoint specified in the
   * action option.
   *
   * @param sKey
   *        Parameter name
   * @param sValue
   *        Parameter value
   * @return this
   */
  @Nonnull
  public FineUploader5Core addRequestParam (@Nonnull @Nonempty final String sKey, @Nonnull final String sValue)
  {
    ValueEnforcer.notEmpty (sKey, "Key");
    ValueEnforcer.notNull (sValue, "Value");

    m_aRequestParams.put (sKey, sValue);
    return this;
  }

  public boolean isRequestParamsInBody ()
  {
    return m_bRequestParamsInBody;
  }

  /**
   * Set this to <code>true</code> if you want all parameters to be sent in the
   * request body. Note that setting this option to <code>true</code> will force
   * all requests to be multipart encoded. If the value is false all params will
   * be included in the query string. See the associated blog post
   * (http://blog.fineuploader
   * .com/2012/11/include-params-in-request-body-or-query.html) for more
   * details.
   *
   * @param bRequestParamsInBody
   *        <code>true</code> to put request params in bodx
   * @return this
   */
  @Nonnull
  public FineUploader5Core setRequestParamsInBody (final boolean bRequestParamsInBody)
  {
    m_bRequestParamsInBody = bRequestParamsInBody;
    return this;
  }

  @Nonnull
  @ReturnsMutableCopy
  public ICommonsOrderedMap <String, String> getAllRequestCustomHeaders ()
  {
    return m_aRequestCustomHeaders.getClone ();
  }

  /**
   * Additional headers sent along with the XHR POST request. Note that is
   * option is only relevant to the ajax/XHR uploader.
   *
   * @param aCustomHeaders
   *        Custom headers to be set.
   * @return this
   */
  @Nonnull
  public FineUploader5Core setRequestCustomHeaders (@Nullable final Map <String, String> aCustomHeaders)
  {
    m_aRequestCustomHeaders.setAll (aCustomHeaders);
    return this;
  }

  /**
   * Additional headers sent along with the XHR POST request. Note that is
   * option is only relevant to the ajax/XHR uploader.
   *
   * @param aCustomHeaders
   *        Custom headers to be added.
   * @return this
   */
  @Nonnull
  public FineUploader5Core addRequestCustomHeaders (@Nullable final Map <String, String> aCustomHeaders)
  {
    m_aRequestCustomHeaders.addAll (aCustomHeaders);
    return this;
  }

  /**
   * Additional headers sent along with the XHR POST request. Note that is
   * option is only relevant to the ajax/XHR uploader.
   *
   * @param sKey
   *        Custom header name
   * @param sValue
   *        Custom header value
   * @return this
   */
  @Nonnull
  public FineUploader5Core addRequestCustomHeader (@Nonnull @Nonempty final String sKey, @Nonnull final String sValue)
  {
    ValueEnforcer.notEmpty (sKey, "Key");
    ValueEnforcer.notNull (sValue, "Value");

    m_aRequestCustomHeaders.put (sKey, sValue);
    return this;
  }

  public boolean isRequestForceMultipart ()
  {
    return m_bRequestForceMultipart;
  }

  /**
   * While form-based uploads will always be multipart requests, this forces XHR
   * uploads to send files using multipart requests as well.
   *
   * @param bForceMultipart
   *        <code>true</code> to force
   * @return this
   */
  @Nonnull
  public FineUploader5Core setRequestForceMultipart (final boolean bForceMultipart)
  {
    m_bRequestForceMultipart = bForceMultipart;
    return this;
  }

  @Nonnull
  @ReturnsMutableCopy
  public ICommonsOrderedSet <String> getAllAllowedExtensions ()
  {
    return m_aValidationAllowedExtensions.getClone ();
  }

  /**
   * This may be helpful if you want to restrict uploaded files to specific file
   * types. Note that this validation option is only enforced by examining the
   * extension of uploaded file names. For a more complete verification of the
   * file type, you should use, for example, magic byte file identification on
   * the server side and return {"success": false} in the response if the file
   * type is not on your whitelist.
   *
   * @param aAllowedExtensions
   *        The allowed extensions to be set.
   * @return this
   */
  @Nonnull
  public FineUploader5Core setAllowedExtensions (@Nullable final Set <String> aAllowedExtensions)
  {
    m_aValidationAllowedExtensions.setAll (aAllowedExtensions);
    return this;
  }

  /**
   * This may be helpful if you want to restrict uploaded files to specific file
   * types. Note that this validation option is only enforced by examining the
   * extension of uploaded file names. For a more complete verification of the
   * file type, you should use, for example, magic byte file identification on
   * the server side and return {"success": false} in the response if the file
   * type is not on your whitelist.
   *
   * @param aAllowedExtensions
   *        The allowed extensions to be added.
   * @return this
   */
  @Nonnull
  public FineUploader5Core addAllowedExtensions (@Nullable final Set <String> aAllowedExtensions)
  {
    if (aAllowedExtensions != null)
      m_aValidationAllowedExtensions.addAll (aAllowedExtensions);
    return this;
  }

  /**
   * This may be helpful if you want to restrict uploaded files to specific file
   * types. Note that this validation option is only enforced by examining the
   * extension of uploaded file names. For a more complete verification of the
   * file type, you should use, for example, magic byte file identification on
   * the server side and return {"success": false} in the response if the file
   * type is not on your whitelist.
   *
   * @param sAllowedExtension
   *        The allowed extension to be added. E.g. ("jpeg", "jpg", "gif")
   * @return this
   */
  @Nonnull
  public FineUploader5Core addAllowedExtension (@Nonnull @Nonempty final String sAllowedExtension)
  {
    ValueEnforcer.notEmpty (sAllowedExtension, "allowedExtension");
    m_aValidationAllowedExtensions.add (sAllowedExtension);
    return this;
  }

  @Nonnegative
  public int getSizeLimit ()
  {
    return m_nValidationSizeLimit;
  }

  /**
   * Maximum allowable size, in bytes, for a file.
   *
   * @param nSizeLimit
   *        Size limit. 0 == unlimited
   * @return this
   */
  @Nonnull
  public FineUploader5Core setSizeLimit (@Nonnegative final int nSizeLimit)
  {
    ValueEnforcer.isGE0 (nSizeLimit, "SizeLimit");
    m_nValidationSizeLimit = nSizeLimit;
    return this;
  }

  @Nonnegative
  public int getMinSizeLimit ()
  {
    return m_nValidationMinSizeLimit;
  }

  /**
   * Minimum allowable size, in bytes, for a file.
   *
   * @param nMinSizeLimit
   *        Minimum size limit. 0 == unlimited
   * @return this
   */
  @Nonnull
  public FineUploader5Core setMinSizeLimit (@Nonnegative final int nMinSizeLimit)
  {
    ValueEnforcer.isGE0 (nMinSizeLimit, "MinSizeLimit");
    m_nValidationMinSizeLimit = nMinSizeLimit;
    return this;
  }

  public boolean isStopOnFirstInvalidFile ()
  {
    return m_bValidationStopOnFirstInvalidFile;
  }

  /**
   * If true, when submitting multiple files, once a file is determined to be
   * invalid, the upload process will terminate. If false, all valid files will
   * be uploaded. Note: One downside to a false value can be seen if the default
   * showMessage implementation is not overriden. In this case, an alert dialog
   * will appear for each invalid file in the batch, and the upload process will
   * not continue until the dialog is dismissed. If this is bothersome, simply
   * override showMessage with a desirable implementation. 3.0 will likely have
   * a showMessage default implementation that does not use the alert function.
   *
   * @param bStopOnFirstInvalidFile
   *        <code>false</code> to not stop
   * @return this
   */
  @Nonnull
  public FineUploader5Core setStopOnFirstInvalidFile (final boolean bStopOnFirstInvalidFile)
  {
    m_bValidationStopOnFirstInvalidFile = bStopOnFirstInvalidFile;
    return this;
  }

  @Nonnull
  @Nonempty
  public String getInputName ()
  {
    return m_sRequestInputName;
  }

  /**
   * This usually only useful with the ajax uploader, which sends the name of
   * the file as a parameter, using a key name equal to the value of this
   * options. In the case of the form uploader, this is simply the value of the
   * name attribute of the file's associated input element.
   *
   * @param sInputName
   *        The input name
   * @return this
   */
  @Nonnull
  public FineUploader5Core setInputName (@Nonnull @Nonempty final String sInputName)
  {
    ValueEnforcer.notEmpty (sInputName, "InputName");

    m_sRequestInputName = sInputName;
    return this;
  }

  public boolean isRetryEnableAuto ()
  {
    return m_bRetryEnableAuto;
  }

  /**
   * If set to <code>true</code>, any error or non-200 response will prompt the
   * uploader to automatically attempt to upload the file again. Default:
   * <code>false</code>
   *
   * @param bRetryEnableAuto
   *        <code>true</code> or <code>false</code>
   * @return this
   */
  @Nonnull
  public FineUploader5Core setRetryEnableAuto (final boolean bRetryEnableAuto)
  {
    m_bRetryEnableAuto = bRetryEnableAuto;
    return this;
  }

  public int getRetryMaxAutoAttempts ()
  {
    return m_nRetryMaxAutoAttempts;
  }

  /**
   * The maximum number of times the uploader will attempt to retry a failed
   * upload. Ignored if retryEnableAuto is <code>false</code>.
   *
   * @param nRetryMaxAutoAttempts
   *        The number of retry attempts.
   * @return this
   */
  @Nonnull
  public FineUploader5Core setRetryMaxAutoAttempts (final int nRetryMaxAutoAttempts)
  {
    m_nRetryMaxAutoAttempts = nRetryMaxAutoAttempts;
    return this;
  }

  public int getRetryAutoAttemptDelay ()
  {
    return m_nRetryAutoAttemptDelay;
  }

  /**
   * The number of seconds the uploader will wait in between automatic retry
   * attempts. Ignored if enableAuto is false.
   *
   * @param nRetryAutoAttemptDelay
   *        Number of seconds
   * @return this
   */
  @Nonnull
  public FineUploader5Core setRetryAutoAttemptDelay (final int nRetryAutoAttemptDelay)
  {
    m_nRetryAutoAttemptDelay = nRetryAutoAttemptDelay;
    return this;
  }

  public String getRetryPreventRetryResponseProperty ()
  {
    return m_sRetryPreventRetryResponseProperty;
  }

  /**
   * If this property is present in the server response and contains a value of
   * true, the uploader will not allow any further retries of this file (manual
   * or automatic).
   *
   * @param sRetryPreventRetryResponseProperty
   *        property name
   * @return this
   */
  @Nonnull
  public FineUploader5Core setRetryPreventRetryResponseProperty (@Nullable final String sRetryPreventRetryResponseProperty)
  {
    m_sRetryPreventRetryResponseProperty = sRetryPreventRetryResponseProperty;
    return this;
  }

  /**
   * @param aRoot
   *        The JSON messages object to extend
   * @param aDisplayLocale
   *        The locale to be used for test resolving. May be <code>null</code>
   *        if none passed.
   */
  @OverrideOnDemand
  protected void extendJSON (@Nonnull final JSAssocArray aRoot, @Nullable final Locale aDisplayLocale)
  {}

  /**
   * @param aMessages
   *        The JSON messages object to extend
   * @param aDisplayLocale
   *        The locale to be used for test resolving
   */
  @OverrideOnDemand
  protected void extendJSONMessages (@Nonnull final JSAssocArray aMessages, @Nonnull final Locale aDisplayLocale)
  {}

  private static void _addIf (@Nonnull final JSAssocArray aAssocArray,
                              @Nonnull @Nonempty final String sKey,
                              @Nullable final JSAssocArray aExpr)
  {
    if (aExpr != null && !aExpr.isEmpty ())
      aAssocArray.add (sKey, aExpr);
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

    _addIf (ret, "blobs", m_aBlobs.getJSCode ());
    // TODO camera
    _addIf (ret, "chunking", m_aChunking.getJSCode ());
    _addIf (ret, "cors", m_aCors.getJSCode ());
    _addIf (ret, "deleteFile", m_aDeleteFile.getJSCode ());
    // TODO extraButtons
    _addIf (ret, "form", m_aForm.getJSCode ());

    // messages
    if (m_aDisplayLocale != null)
    {
      final JSAssocArray aMessages = new JSAssocArray ();
      aMessages.add ("emptyError", EFineUploader5CoreText.EMPTY_ERROR.getDisplayText (m_aDisplayLocale));
      aMessages.add ("maxHeightImageError",
                     EFineUploader5CoreText.MAX_HEIGHT_IMAGE_ERROR.getDisplayText (m_aDisplayLocale));
      aMessages.add ("maxWidthImageError",
                     EFineUploader5CoreText.MAX_WIDTH_IMAGE_ERROR.getDisplayText (m_aDisplayLocale));
      aMessages.add ("minHeightImageError",
                     EFineUploader5CoreText.MIN_HEIGHT_IMAGE_ERROR.getDisplayText (m_aDisplayLocale));
      aMessages.add ("minWidthImageError",
                     EFineUploader5CoreText.MIN_WIDTH_IMAGE_ERROR.getDisplayText (m_aDisplayLocale));
      aMessages.add ("minSizeError", EFineUploader5CoreText.MIN_SIZE_ERROR.getDisplayText (m_aDisplayLocale));
      aMessages.add ("noFilesError", EFineUploader5CoreText.NO_FILES_ERROR.getDisplayText (m_aDisplayLocale));
      aMessages.add ("onLeave", EFineUploader5CoreText.ON_LEAVE.getDisplayText (m_aDisplayLocale));
      aMessages.add ("retryFailTooManyItemsError",
                     EFineUploader5CoreText.RETRY_FAIL_TOO_MANY_ITEMS_ERROR.getDisplayText (m_aDisplayLocale));
      aMessages.add ("sizeError", EFineUploader5CoreText.SIZE_ERROR.getDisplayText (m_aDisplayLocale));
      aMessages.add ("tooManyItemsError",
                     EFineUploader5CoreText.TOO_MANY_ITEMS_ERROR.getDisplayText (m_aDisplayLocale));
      aMessages.add ("typeError", EFineUploader5CoreText.TYPE_ERROR.getDisplayText (m_aDisplayLocale));
      aMessages.add ("unsupportedBrowserIos8Safari",
                     EFineUploader5CoreText.UNSUPPORTED_BROWSER_IOS8_SAFARI.getDisplayText (m_aDisplayLocale));

      // extended
      extendJSONMessages (aMessages, m_aDisplayLocale);
      ret.add ("messages", aMessages);
    }

    _addIf (ret, "paste", m_aPaste.getJSCode ());
    _addIf (ret, "resume", m_aResume.getJSCode ());

    // retry
    {
      final JSAssocArray aSub = new JSAssocArray ();

      if (m_nRetryAutoAttemptDelay != DEFAULT_RETRY_AUTO_ATTEMPT_DELAY)
        aSub.add ("autoAttemptDelay", m_nRetryAutoAttemptDelay);
      if (m_bRetryEnableAuto != DEFAULT_RETRY_ENABLE_AUTO)
        aSub.add ("enableAuto", m_bRetryEnableAuto);
      if (m_nRetryMaxAutoAttempts != DEFAULT_RETRY_MAX_AUTO_ATTEMPTS)
        aSub.add ("maxAutoAttempts", m_nRetryMaxAutoAttempts);
      if (!m_sRetryPreventRetryResponseProperty.equals (DEFAULT_RETRY_PREVENT_RETRY_RESPONSE_PROPERTY))
        aSub.add ("preventRetryResponseProperty", m_sRetryPreventRetryResponseProperty);

      if (!aSub.isEmpty ())
        ret.add ("retry", aSub);
    }

    // request
    {
      final JSAssocArray aSub = new JSAssocArray ();
      if (m_aRequestCustomHeaders.isNotEmpty ())
        aSub.add ("customHeaders", getAsJSAA (m_aRequestCustomHeaders));
      if (!m_aRequestEndpoint.equals (DEFAULT_REQUEST_ENDPOINT))
        aSub.add ("endpoint", m_aRequestEndpoint.getAsStringWithEncodedParameters ());
      if (!m_sRequestFilenameParam.equals (DEFAULT_REQUEST_FILENAME_PARAM))
        aSub.add ("filenameParam", m_sRequestFilenameParam);
      if (m_bRequestForceMultipart != DEFAULT_REQUEST_FORCE_MULTIPART)
        aSub.add ("forceMultipart", m_bRequestForceMultipart);
      if (!m_sRequestInputName.equals (DEFAULT_REQUEST_INPUT_NAME))
        aSub.add ("inputName", m_sRequestInputName);
      if (!m_eRequestMethod.equals (DEFAULT_REQUEST_METHOD))
        aSub.add ("method", m_eRequestMethod.getName ());
      if (m_aRequestParams.isNotEmpty ())
        aSub.add ("params", getAsJSAA (m_aRequestParams));
      if (m_bRequestParamsInBody != DEFAULT_REQUEST_PARAMS_IN_BODY)
        aSub.add ("paramsInBody", m_bRequestParamsInBody);
      if (!m_sRequestUUIDName.equals (DEFAULT_REQUEST_UUID_NAME))
        aSub.add ("uuidName", m_sRequestUUIDName);
      if (!m_sRequestTotalFileSizeName.equals (DEFAULT_REQUEST_TOTAL_FILE_SIZE_NAME))
        aSub.add ("totalFileSizeName", m_sRequestTotalFileSizeName);

      if (!aSub.isEmpty ())
        ret.add ("request", aSub);
    }

    // TODO scaling

    // session
    {
      final JSAssocArray aSub = new JSAssocArray ();

      if (m_aSessionCustomHeaders.isNotEmpty ())
        aSub.add ("customHeaders", getAsJSAA (m_aSessionCustomHeaders));
      if (m_aSessionEndpoint != null)
        aSub.add ("endpoint", m_aSessionEndpoint.getAsStringWithEncodedParameters ());
      if (m_aSessionParams.isNotEmpty ())
        aSub.add ("params", getAsJSAA (m_aSessionParams));
      if (m_bSessionRefreshOnReset != DEFAULT_SESSION_REFRESH_ON_RESET)
        aSub.add ("refreshOnReset", m_bSessionRefreshOnReset);

      if (!aSub.isEmpty ())
        ret.add ("session", aSub);
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

      if (!aSub.isEmpty ())
        ret.add ("text", aSub);
    }

    // validation
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

      if (!aSub.isEmpty ())
        ret.add ("validation", aSub);
    }

    // workarounds
    {
      final JSAssocArray aSub = new JSAssocArray ();
      if (m_bWorkaroundsIosEmptyVideo != DEFAULT_WORKAROUNDS)
        aSub.add ("iosEmptyVideos", m_bWorkaroundsIosEmptyVideo);
      if (m_bWorkaroundsIos8BrowserCrash != DEFAULT_WORKAROUNDS)
        aSub.add ("ios8BrowserCrash", m_bWorkaroundsIos8BrowserCrash);
      if (m_bWorkaroundsIos8SafariUploads != DEFAULT_WORKAROUNDS)
        aSub.add ("ios8SafariUploads", m_bWorkaroundsIos8SafariUploads);

      if (!aSub.isEmpty ())
        ret.add ("workarounds", aSub);
    }

    extendJSON (ret, m_aDisplayLocale);
    return ret;
  }
}
