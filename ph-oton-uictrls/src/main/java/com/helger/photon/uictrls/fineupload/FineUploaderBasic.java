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
package com.helger.photon.uictrls.fineupload;

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
import com.helger.commons.collection.impl.CommonsLinkedHashMap;
import com.helger.commons.collection.impl.CommonsLinkedHashSet;
import com.helger.commons.collection.impl.ICommonsOrderedMap;
import com.helger.commons.collection.impl.ICommonsOrderedSet;
import com.helger.commons.string.StringHelper;
import com.helger.commons.url.ISimpleURL;
import com.helger.commons.url.SimpleURL;
import com.helger.html.jscode.JSArray;
import com.helger.html.jscode.JSAssocArray;
import com.helger.html.jscode.html.JSHtml;

/**
 * Wrapper for Fineuploader 3.1.1
 *
 * @author Philip Helger
 */
public class FineUploaderBasic
{
  public static final boolean DEFAULT_DEBUG = false;
  public static final boolean DEFAULT_MULTIPLE = true;
  public static final int DEFAULT_MAX_CONNECTIONS = 3;
  public static final boolean DEFAULT_DISABLE_CANCEL_FOR_FORM_UPLOADS = false;
  public static final boolean DEFAULT_AUTO_UPLOAD = true;

  public static final ISimpleURL DEFAULT_REQUEST_ENDPOINT = new SimpleURL ("/server/upload");
  public static final boolean DEFAULT_REQUEST_PARAMS_IN_BODY = false;
  public static final boolean DEFAULT_REQUEST_FORCE_MULTIPART = false;
  public static final String DEFAULT_REQUEST_INPUT_NAME = "qqfile";

  public static final int DEFAULT_VALIDATION_SIZE_LIMIT = 0;
  public static final int DEFAULT_VALIDATION_MIN_SIZE_LIMIT = 0;
  public static final boolean DEFAULT_VALIDATION_STOP_ON_FIRST_INVALID_FILE = true;

  public static final boolean DEFAULT_RETRY_ENABLE_AUTO = false;
  public static final int DEFAULT_RETRY_MAX_AUTO_ATTEMPTS = 3;
  public static final int DEFAULT_RETRY_AUTO_ATTEMPT_DELAY = 5;
  public static final String DEFAULT_RETRY_PREVENT_RETRY_RESPONSE_PROPERTY = "preventRetry";

  private final Locale m_aDisplayLocale;

  private boolean m_bDebug = DEFAULT_DEBUG;
  private String m_sButtonElementID;
  private boolean m_bMultiple = DEFAULT_MULTIPLE;
  private int m_nMaxConnections = DEFAULT_MAX_CONNECTIONS;
  private boolean m_bDisableCancelForFormUploads = DEFAULT_DISABLE_CANCEL_FOR_FORM_UPLOADS;
  private boolean m_bAutoUpload = DEFAULT_AUTO_UPLOAD;

  private ISimpleURL m_aRequestEndpoint = DEFAULT_REQUEST_ENDPOINT;
  private final ICommonsOrderedMap <String, String> m_aRequestParams = new CommonsLinkedHashMap <> ();
  private boolean m_bRequestParamsInBody = DEFAULT_REQUEST_PARAMS_IN_BODY;
  private final ICommonsOrderedMap <String, String> m_aRequestCustomHeaders = new CommonsLinkedHashMap <> ();
  private boolean m_bRequestForceMultipart = DEFAULT_REQUEST_FORCE_MULTIPART;
  private String m_sRequestInputName = DEFAULT_REQUEST_INPUT_NAME;

  private final ICommonsOrderedSet <String> m_aValidationAllowedExtensions = new CommonsLinkedHashSet <> ();
  private int m_nValidationSizeLimit = DEFAULT_VALIDATION_SIZE_LIMIT;
  private int m_nValidationMinSizeLimit = DEFAULT_VALIDATION_MIN_SIZE_LIMIT;
  private boolean m_bValidationStopOnFirstInvalidFile = DEFAULT_VALIDATION_STOP_ON_FIRST_INVALID_FILE;

  private boolean m_bRetryEnableAuto = DEFAULT_RETRY_ENABLE_AUTO;
  private int m_nRetryMaxAutoAttempts = DEFAULT_RETRY_MAX_AUTO_ATTEMPTS;
  private int m_nRetryAutoAttemptDelay = DEFAULT_RETRY_AUTO_ATTEMPT_DELAY;
  private String m_sRetryPreventRetryResponseProperty = DEFAULT_RETRY_PREVENT_RETRY_RESPONSE_PROPERTY;

  public FineUploaderBasic (@Nullable final Locale aDisplayLocale)
  {
    m_aDisplayLocale = aDisplayLocale;
  }

  @Nullable
  public Locale getDisplayLocale ()
  {
    return m_aDisplayLocale;
  }

  public boolean isDebug ()
  {
    return m_bDebug;
  }

  /**
   * If enabled, this will result in log messages (such as server response)
   * being written to the javascript console. If your browser does not support
   * the [window.console
   * object](https://developer.mozilla.org/en-US/docs/DOM/console.log), the
   * value of this option is irrelevant.
   *
   * @param bDebug
   *        New value
   * @return this
   */
  @Nonnull
  public FineUploaderBasic setDebug (final boolean bDebug)
  {
    m_bDebug = bDebug;
    return this;
  }

  @Nonnull
  public ISimpleURL getEndpoint ()
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
  public FineUploaderBasic setEndpoint (@Nonnull final ISimpleURL aRequestEndpoint)
  {
    m_aRequestEndpoint = ValueEnforcer.notNull (aRequestEndpoint, "RequestEndpoint");
    return this;
  }

  @Nonnull
  @ReturnsMutableCopy
  public ICommonsOrderedMap <String, String> getAllParams ()
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
  public FineUploaderBasic setParams (@Nullable final Map <String, String> aParams)
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
  public FineUploaderBasic addParams (@Nullable final Map <String, String> aParams)
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
  public FineUploaderBasic addParam (@Nonnull @Nonempty final String sKey, @Nonnull final String sValue)
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
  public FineUploaderBasic setRequestParamsInBody (final boolean bRequestParamsInBody)
  {
    m_bRequestParamsInBody = bRequestParamsInBody;
    return this;
  }

  @Nonnull
  @ReturnsMutableCopy
  public ICommonsOrderedMap <String, String> getAllCustomHeaders ()
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
  public FineUploaderBasic setCustomHeaders (@Nullable final Map <String, String> aCustomHeaders)
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
  public FineUploaderBasic addCustomHeaders (@Nullable final Map <String, String> aCustomHeaders)
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
  public FineUploaderBasic addCustomHeader (@Nonnull @Nonempty final String sKey, @Nonnull final String sValue)
  {
    ValueEnforcer.notEmpty (sKey, "Key");
    ValueEnforcer.notNull (sValue, "Value");

    m_aRequestCustomHeaders.put (sKey, sValue);
    return this;
  }

  public boolean isForceMultipart ()
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
  public FineUploaderBasic setForceMultipart (final boolean bForceMultipart)
  {
    m_bRequestForceMultipart = bForceMultipart;
    return this;
  }

  @Nullable
  public String getButtonElementID ()
  {
    return m_sButtonElementID;
  }

  /**
   * Specify an element to use as the "select files" button. Note that this may
   * <b>NOT</b> be a &lt;button&gt;, otherwise it will not work in Internet
   * Explorer. Please see issue #33 for details.
   *
   * @param sButtonElementID
   *        Element ID of the button
   * @return this
   */
  @Nonnull
  public FineUploaderBasic setButtonElementID (@Nullable final String sButtonElementID)
  {
    m_sButtonElementID = sButtonElementID;
    return this;
  }

  public boolean isMultiple ()
  {
    return m_bMultiple;
  }

  /**
   * Set to false puts the uploader into what is best described as 'single-file
   * upload mode'. See the [demo](http://fineuploader.com) for an example.
   *
   * @param bMultiple
   *        <code>true</code> for multiple, <code>false</code> for single
   * @return this
   */
  @Nonnull
  public FineUploaderBasic setMultiple (final boolean bMultiple)
  {
    m_bMultiple = bMultiple;
    return this;
  }

  @Nonnegative
  public int getMaxConnections ()
  {
    return m_nMaxConnections;
  }

  /**
   * Maximum allowable concurrent uploads.
   *
   * @param nMaxConnections
   *        Maximum number. Must be &gt; 0.
   * @return this
   */
  @Nonnull
  public FineUploaderBasic setMaxConnections (@Nonnegative final int nMaxConnections)
  {
    ValueEnforcer.isGT0 (nMaxConnections, "MaxConnections");
    m_nMaxConnections = nMaxConnections;
    return this;
  }

  public boolean isDisableCancelForFormUploads ()
  {
    return m_bDisableCancelForFormUploads;
  }

  /**
   * If true, the cancel link does not appear next to files when the form
   * uploader is used. This may be desired since it may not be possible to
   * interrupt a form-based upload in some cases.
   *
   * @param bDisableCancelForFormUploads
   *        disable?
   * @return this
   */
  @Nonnull
  public FineUploaderBasic setDisableCancelForFormUploads (final boolean bDisableCancelForFormUploads)
  {
    m_bDisableCancelForFormUploads = bDisableCancelForFormUploads;
    return this;
  }

  public boolean isAutoUpload ()
  {
    return m_bAutoUpload;
  }

  /**
   * Set to false if you want to be able to begin uploading selected/queued
   * files later, by calling uploadStoredFiles().
   *
   * @param bAutoUpload
   *        <code>false</code> to disable auto upload
   * @return this
   */
  @Nonnull
  public FineUploaderBasic setAutoUpload (final boolean bAutoUpload)
  {
    m_bAutoUpload = bAutoUpload;
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
  public FineUploaderBasic setAllowedExtensions (@Nullable final Set <String> aAllowedExtensions)
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
  public FineUploaderBasic addAllowedExtensions (@Nullable final Set <String> aAllowedExtensions)
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
  public FineUploaderBasic addAllowedExtension (@Nonnull @Nonempty final String sAllowedExtension)
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
  public FineUploaderBasic setSizeLimit (@Nonnegative final int nSizeLimit)
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
  public FineUploaderBasic setMinSizeLimit (@Nonnegative final int nMinSizeLimit)
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
  public FineUploaderBasic setStopOnFirstInvalidFile (final boolean bStopOnFirstInvalidFile)
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
  public FineUploaderBasic setInputName (@Nonnull @Nonempty final String sInputName)
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
  public FineUploaderBasic setRetryEnableAuto (final boolean bRetryEnableAuto)
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
  public FineUploaderBasic setRetryMaxAutoAttempts (final int nRetryMaxAutoAttempts)
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
  public FineUploaderBasic setRetryAutoAttemptDelay (final int nRetryAutoAttemptDelay)
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
  public FineUploaderBasic setRetryPreventRetryResponseProperty (@Nullable final String sRetryPreventRetryResponseProperty)
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

  @Nonnull
  public final JSAssocArray getJSON ()
  {
    final JSAssocArray ret = new JSAssocArray ();
    if (m_bDebug != DEFAULT_DEBUG)
      ret.add ("debug", m_bDebug);
    if (StringHelper.hasText (m_sButtonElementID))
      ret.add ("button", JSHtml.documentGetElementById (m_sButtonElementID));
    if (m_bMultiple != DEFAULT_MULTIPLE)
      ret.add ("multiple", m_bMultiple);
    if (m_nMaxConnections != DEFAULT_MAX_CONNECTIONS)
      ret.add ("maxConnections", m_nMaxConnections);
    if (m_bDisableCancelForFormUploads != DEFAULT_DISABLE_CANCEL_FOR_FORM_UPLOADS)
      ret.add ("disableCancelForFormUploads", m_bDisableCancelForFormUploads);
    if (m_bAutoUpload != DEFAULT_AUTO_UPLOAD)
      ret.add ("autoUpload", m_bAutoUpload);

    // request
    {
      final JSAssocArray aRequest = new JSAssocArray ();
      if (!m_aRequestEndpoint.equals (DEFAULT_REQUEST_ENDPOINT))
        aRequest.add ("endpoint", m_aRequestEndpoint.getAsStringWithEncodedParameters ());
      if (m_aRequestParams.isNotEmpty ())
      {
        final JSAssocArray aParams = new JSAssocArray ();
        for (final Map.Entry <String, String> aEntry : m_aRequestParams.entrySet ())
          aParams.add (aEntry.getKey (), aEntry.getValue ());
        aRequest.add ("params", aParams);
      }
      if (m_bRequestParamsInBody != DEFAULT_REQUEST_PARAMS_IN_BODY)
        aRequest.add ("paramsInBody", m_bRequestParamsInBody);
      if (m_aRequestCustomHeaders.isNotEmpty ())
      {
        final JSAssocArray aCustomHeaders = new JSAssocArray ();
        for (final Map.Entry <String, String> aEntry : m_aRequestCustomHeaders.entrySet ())
          aCustomHeaders.add (aEntry.getKey (), aEntry.getValue ());
        aRequest.add ("customHeaders", aCustomHeaders);
      }
      if (m_bRequestForceMultipart != DEFAULT_REQUEST_FORCE_MULTIPART)
        aRequest.add ("forceMultipart", m_bRequestForceMultipart);
      if (!m_sRequestInputName.equals (DEFAULT_REQUEST_INPUT_NAME))
        aRequest.add ("inputName", m_sRequestInputName);

      if (!aRequest.isEmpty ())
        ret.add ("request", aRequest);
    }

    // validation
    {
      final JSAssocArray aValidation = new JSAssocArray ();
      if (m_aValidationAllowedExtensions.isNotEmpty ())
        aValidation.add ("allowedExtensions", new JSArray ().addAll (m_aValidationAllowedExtensions));
      if (m_nValidationSizeLimit != DEFAULT_VALIDATION_SIZE_LIMIT)
        aValidation.add ("sizeLimit", m_nValidationSizeLimit);
      if (m_nValidationMinSizeLimit != DEFAULT_VALIDATION_MIN_SIZE_LIMIT)
        aValidation.add ("minSizeLimit", m_nValidationMinSizeLimit);
      if (m_bValidationStopOnFirstInvalidFile != DEFAULT_VALIDATION_STOP_ON_FIRST_INVALID_FILE)
        aValidation.add ("stopOnFirstInvalidFile", m_bValidationStopOnFirstInvalidFile);
      if (!aValidation.isEmpty ())
        ret.add ("validation", aValidation);
    }

    // messages
    if (m_aDisplayLocale != null)
    {
      final JSAssocArray aMessages = new JSAssocArray ();
      aMessages.add ("typeError", EFineUploaderBasicText.TYPE_ERROR.getDisplayText (m_aDisplayLocale));
      aMessages.add ("sizeError", EFineUploaderBasicText.SIZE_ERROR.getDisplayText (m_aDisplayLocale));
      aMessages.add ("minSizeError", EFineUploaderBasicText.MIN_SIZE_ERROR.getDisplayText (m_aDisplayLocale));
      aMessages.add ("emptyError", EFineUploaderBasicText.EMPTY_ERROR.getDisplayText (m_aDisplayLocale));
      aMessages.add ("noFilesError", EFineUploaderBasicText.NO_FILES_ERROR.getDisplayText (m_aDisplayLocale));
      aMessages.add ("onLeave", EFineUploaderBasicText.ON_LEAVE.getDisplayText (m_aDisplayLocale));
      // extended
      extendJSONMessages (aMessages, m_aDisplayLocale);
      ret.add ("messages", aMessages);
    }

    // retry
    {
      final JSAssocArray aRetry = new JSAssocArray ();
      if (m_bRetryEnableAuto != DEFAULT_RETRY_ENABLE_AUTO)
        aRetry.add ("enableAuto", m_bRetryEnableAuto);
      if (m_nRetryMaxAutoAttempts != DEFAULT_RETRY_MAX_AUTO_ATTEMPTS)
        aRetry.add ("maxAutoAttempts", m_nRetryMaxAutoAttempts);
      if (m_nRetryAutoAttemptDelay != DEFAULT_RETRY_AUTO_ATTEMPT_DELAY)
        aRetry.add ("autoAttemptDelay", m_nRetryAutoAttemptDelay);
      if (!DEFAULT_RETRY_PREVENT_RETRY_RESPONSE_PROPERTY.equals (m_sRetryPreventRetryResponseProperty))
        aRetry.add ("preventRetryResponseProperty", m_sRetryPreventRetryResponseProperty);
      if (!aRetry.isEmpty ())
        ret.add ("retry", aRetry);
    }

    extendJSON (ret, m_aDisplayLocale);
    return ret;
  }
}
