/*
 * Copyright (C) 2018-2025 Philip Helger (www.helger.com)
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
package com.helger.photon.bootstrap4.pages.utils;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Locale;

import org.apache.hc.client5.http.classic.methods.HttpUriRequestBase;
import org.apache.hc.core5.http.ClassicHttpResponse;
import org.apache.hc.core5.http.ContentType;
import org.apache.hc.core5.http.Header;
import org.apache.hc.core5.http.HttpEntity;
import org.apache.hc.core5.http.io.HttpClientResponseHandler;
import org.apache.hc.core5.http.message.StatusLine;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.helger.annotation.Nonempty;
import com.helger.annotation.concurrent.NotThreadSafe;
import com.helger.base.compare.ESortOrder;
import com.helger.base.enforce.ValueEnforcer;
import com.helger.base.id.IHasID;
import com.helger.base.name.IHasDisplayName;
import com.helger.base.state.EChange;
import com.helger.base.string.StringCount;
import com.helger.base.string.StringHelper;
import com.helger.base.timing.StopWatch;
import com.helger.collection.commons.CommonsHashMap;
import com.helger.collection.commons.ICommonsMap;
import com.helger.html.hc.html.forms.HCEdit;
import com.helger.html.hc.html.forms.HCHiddenField;
import com.helger.html.hc.html.forms.HCTextArea;
import com.helger.html.hc.html.tabular.HCTable;
import com.helger.html.hc.impl.HCNodeList;
import com.helger.html.hc.impl.HCTextNode;
import com.helger.http.EHttpMethod;
import com.helger.http.header.HttpHeaderMap;
import com.helger.httpclient.HttpClientHelper;
import com.helger.httpclient.HttpClientManager;
import com.helger.httpclient.HttpClientSettings;
import com.helger.httpclient.response.ResponseHandlerHttpEntity;
import com.helger.photon.bootstrap4.CBootstrapCSS;
import com.helger.photon.bootstrap4.button.BootstrapSubmitButton;
import com.helger.photon.bootstrap4.form.BootstrapForm;
import com.helger.photon.bootstrap4.form.BootstrapFormGroup;
import com.helger.photon.bootstrap4.pages.AbstractBootstrapWebPage;
import com.helger.photon.bootstrap4.uictrls.datatables.BootstrapDataTables;
import com.helger.photon.bootstrap4.uictrls.ext.BootstrapTechnicalUI;
import com.helger.photon.core.form.FormErrorList;
import com.helger.photon.core.form.RequestField;
import com.helger.photon.uicore.css.CPageParam;
import com.helger.photon.uicore.html.select.HCExtSelect;
import com.helger.photon.uicore.page.EWebPageText;
import com.helger.photon.uicore.page.IWebPageExecutionContext;
import com.helger.photon.uictrls.datatables.DataTablesLengthMenu;
import com.helger.photon.uictrls.datatables.column.DTCol;
import com.helger.text.IMultilingualText;
import com.helger.url.SimpleURL;

import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;

/**
 * Page with the possibility to perform a remote query to check if the network connectivity is
 * given. Using {@link HttpClientConfigRegistry} external configurations can be added.
 *
 * @author Philip Helger
 * @param <WPECTYPE>
 *        Web Page Execution Context type
 * @since 8.2.3
 */
public class BasePageUtilsHttpClient <WPECTYPE extends IWebPageExecutionContext> extends
                                     AbstractBootstrapWebPage <WPECTYPE>
{
  public interface IHttpClientMetaProvider
  {
    /**
     * @param sTargetURI
     *        The target URL to be accessed. May neither be <code>null</code> nor empty.
     * @return The HTTP client settings to use. May not be <code>null</code>.
     */
    @Nonnull
    HttpClientSettings getHttpClientSettings (@Nonnull @Nonempty String sTargetURI);
  }

  public interface IHttpClientConfig extends IHasID <String>, IHasDisplayName, IHttpClientMetaProvider
  {
    /* empty */
  }

  public static class HttpClientConfig implements IHttpClientConfig
  {
    private final String m_sID;
    private final String m_sDisplayName;
    private final IHttpClientMetaProvider m_aHCMP;

    public HttpClientConfig (@Nonnull @Nonempty final String sID,
                             @Nonnull @Nonempty final String sDisplayName,
                             @Nonnull final IHttpClientMetaProvider aHCMP)
    {
      ValueEnforcer.notEmpty (sID, "ID");
      ValueEnforcer.notEmpty (sDisplayName, "DisplayName");
      ValueEnforcer.notNull (aHCMP, "HCMP");
      m_sID = sID;
      m_sDisplayName = sDisplayName;
      m_aHCMP = aHCMP;
    }

    @Nonnull
    @Nonempty
    public String getID ()
    {
      return m_sID;
    }

    @Nonnull
    @Nonempty
    public String getDisplayName ()
    {
      return m_sDisplayName;
    }

    @Nonnull
    public HttpClientSettings getHttpClientSettings (@Nonnull @Nonempty final String sTargetURI)
    {
      return m_aHCMP.getHttpClientSettings (sTargetURI);
    }
  }

  @NotThreadSafe
  public static class HttpClientConfigRegistry
  {
    public static final String DEFAULT_CONFIG_ID = "systemdefault";

    private static final ICommonsMap <String, IHttpClientConfig> s_aMap = new CommonsHashMap <> ();

    private HttpClientConfigRegistry ()
    {}

    /**
     * Register a new http client configuration.
     *
     * @param aHCC
     *        The configuration to register. May not be <code>null</code>.
     */
    public static void register (@Nonnull final IHttpClientConfig aHCC)
    {
      ValueEnforcer.notNull (aHCC, "HCC");
      final String sID = aHCC.getID ();
      if (s_aMap.containsKey (sID))
        throw new IllegalArgumentException ("Another configuration with ID '" + sID + "' is already registered");
      s_aMap.put (sID, aHCC);
    }

    @Nonnull
    public static EChange unregister (@Nullable final String sID)
    {
      if (StringHelper.isEmpty (sID))
        return EChange.UNCHANGED;
      return s_aMap.removeObject (sID);
    }

    @Nullable
    public static IHttpClientConfig getFromID (@Nullable final String sID)
    {
      if (StringHelper.isEmpty (sID))
        return null;
      return s_aMap.get (sID);
    }

    @Nonnull
    public static Iterable <IHttpClientConfig> iterate ()
    {
      return s_aMap.values ();
    }

    /**
     * Remove all existing entries and register only the default configuration.
     */
    public static void setToDefault ()
    {
      s_aMap.clear ();
      register (new HttpClientConfig (DEFAULT_CONFIG_ID, "System default settings", x -> new HttpClientSettings ()));
    }

    static
    {
      // set to default
      setToDefault ();
    }
  }

  private static final class DebugResponseHandler implements HttpClientResponseHandler <String>
  {
    private final Charset m_aDefaultCharset;
    private StatusLine m_aUsedStatusLine;
    private Charset m_aUsedCharset;
    private final HttpHeaderMap m_aUsedHeaders = new HttpHeaderMap ();

    public DebugResponseHandler (@Nonnull final Charset aDefaultCharset)
    {
      m_aDefaultCharset = aDefaultCharset;
    }

    @Nullable
    public String handleResponse (@Nonnull final ClassicHttpResponse aHttpResponse) throws IOException
    {
      // Convert to entity
      final HttpEntity aEntity = ResponseHandlerHttpEntity.INSTANCE.handleResponse (aHttpResponse);
      if (aEntity == null)
        return null;

      final Charset aCharset;
      final ContentType aContentType = HttpClientHelper.getContentType (aEntity);
      if (aContentType == null)
        aCharset = m_aDefaultCharset;
      else
      {
        // Get the charset from the content type or the default charset
        aCharset = HttpClientHelper.getCharset (aContentType, m_aDefaultCharset);
      }
      m_aUsedStatusLine = new StatusLine (aHttpResponse);
      m_aUsedCharset = aCharset;
      m_aUsedHeaders.removeAll ();
      for (final Header aHeader : aHttpResponse.getHeaders ())
        m_aUsedHeaders.addHeader (aHeader.getName (), aHeader.getValue ());

      return HttpClientHelper.entityToString (aEntity, aCharset);
    }
  }

  private static final Logger LOGGER = LoggerFactory.getLogger (BasePageUtilsHttpClient.class);
  private static final String FIELD_CONFIG = "config";
  private static final String FIELD_HTTP_METHOD = "http_method";
  private static final String FIELD_URI = "uri";

  public BasePageUtilsHttpClient (@Nonnull @Nonempty final String sID)
  {
    super (sID, EWebPageText.PAGE_NAME_UTILS_HTTP_CLIENT.getAsMLT ());
  }

  public BasePageUtilsHttpClient (@Nonnull @Nonempty final String sID, @Nonnull final String sName)
  {
    super (sID, sName);
  }

  public BasePageUtilsHttpClient (@Nonnull @Nonempty final String sID,
                                  @Nonnull final String sName,
                                  @Nullable final String sDescription)
  {
    super (sID, sName, sDescription);
  }

  public BasePageUtilsHttpClient (@Nonnull @Nonempty final String sID,
                                  @Nonnull final IMultilingualText aName,
                                  @Nullable final IMultilingualText aDescription)
  {
    super (sID, aName, aDescription);
  }

  @Override
  protected void fillContent (@Nonnull final WPECTYPE aWPEC)
  {
    final HCNodeList aNodeList = aWPEC.getNodeList ();
    final Locale aDisplayLocale = aWPEC.getDisplayLocale ();

    aNodeList.addChild (info ("This page allows to perform arbitrary http(s) queries to test network connectivity problems."));

    final FormErrorList aFormErrors = new FormErrorList ();
    if (aWPEC.hasAction (CPageParam.ACTION_PERFORM))
    {
      final String sConfigID = aWPEC.params ().getAsStringTrimmed (FIELD_CONFIG);
      final IHttpClientConfig aConfig = HttpClientConfigRegistry.getFromID (sConfigID);
      final String sHttpMethod = aWPEC.params ().getAsStringTrimmed (FIELD_HTTP_METHOD);
      final EHttpMethod eHttpMethod = EHttpMethod.getFromNameOrNull (sHttpMethod);
      final String sURI = aWPEC.params ().getAsStringTrimmed (FIELD_URI);

      if (StringHelper.isEmpty (sConfigID))
        aFormErrors.addFieldError (FIELD_CONFIG, "A configuration must be selected.");
      else
        if (aConfig == null)
          aFormErrors.addFieldError (FIELD_CONFIG, "Please select a valid configuration.");

      if (StringHelper.isEmpty (sHttpMethod))
        aFormErrors.addFieldError (FIELD_HTTP_METHOD, "A HTTP method must be selected.");
      else
        if (eHttpMethod == null)
          aFormErrors.addFieldError (FIELD_HTTP_METHOD, "Please select a valid HTTP method.");

      if (StringHelper.isEmpty (sURI))
        aFormErrors.addFieldError (FIELD_URI, "A URI must be provided.");
      else
        if (!sURI.startsWith ("http://") && !sURI.startsWith ("https://"))
          aFormErrors.addFieldError (FIELD_URI, "The URI must start with 'http://' or 'https://'");
      if (aFormErrors.isEmpty ())
      {
        String sResultContent;
        boolean bSuccess = false;

        LOGGER.info ("http client " +
                     eHttpMethod.getName () +
                     " query '" +
                     sURI +
                     "' using configuration '" +
                     aConfig.getID () +
                     "'");

        final StopWatch aSW = StopWatch.createdStarted ();
        final HttpClientSettings aHCS = aConfig.getHttpClientSettings (sURI);
        final DebugResponseHandler aResponseHdl = new DebugResponseHandler (StandardCharsets.UTF_8);
        try (final HttpClientManager aHCM = HttpClientManager.create (aHCS))
        {
          // Create depending on the method
          final HttpUriRequestBase aReq = HttpClientHelper.createRequest (eHttpMethod, new SimpleURL (sURI));
          sResultContent = aHCM.execute (aReq, aResponseHdl);
          bSuccess = true;
          LOGGER.info ("http client " + eHttpMethod.getName () + " query succeeded");
        }
        catch (final IOException ex)
        {
          sResultContent = BootstrapTechnicalUI.getTechnicalDetailsString (ex, aDisplayLocale);
          LOGGER.warn ("http client " +
                       eHttpMethod.getName () +
                       " query failed with " +
                       ex.getClass ().getName () +
                       " - " +
                       ex.getMessage ());
        }
        aSW.stop ();

        aNodeList.addChild (div ("Output of querying ").addChild (code (sURI))
                                                       .addChild (" using ")
                                                       .addChild (em (aConfig.getDisplayName ()))
                                                       .addChild (": ")
                                                       .addChild (bSuccess ? badgeSuccess ("success") : badgeDanger (
                                                                                                                     "error")));
        aNodeList.addChild (div ("Querying took " + aSW.getMillis () + " milliseconds"));
        if (aResponseHdl.m_aUsedStatusLine != null)
        {
          // toString of ProtocolVersion is fine
          aNodeList.addChild (div ("Response protocol version: ").addChild (code (String.valueOf (aResponseHdl.m_aUsedStatusLine.getProtocolVersion ()))));
          aNodeList.addChild (div ("Response status code: ").addChild (code (Integer.toString (aResponseHdl.m_aUsedStatusLine.getStatusCode ()))));
          aNodeList.addChild (div ("Response reason phrase: ").addChild (code (aResponseHdl.m_aUsedStatusLine.getReasonPhrase ())));
        }
        if (aResponseHdl.m_aUsedCharset != null)
          aNodeList.addChild (div ("Response charset used: ").addChild (code (aResponseHdl.m_aUsedCharset.name ())));
        if (aResponseHdl.m_aUsedHeaders.isNotEmpty ())
        {
          aNodeList.addChild (div ("Response HTTP headers:"));
          final HCTable aTable = new HCTable (new DTCol ("Name").setInitialSorting (ESortOrder.ASCENDING),
                                              new DTCol ("Value")).setID ("httpresponseheaders");
          aResponseHdl.m_aUsedHeaders.forEachSingleHeader ( (n, v) -> aTable.addBodyRow ().addCells (n, v), false);
          final BootstrapDataTables aDT = BootstrapDataTables.createDefaultDataTables (aWPEC, aTable);
          aDT.setLengthMenu (DataTablesLengthMenu.INSTANCE_ALL);
          aDT.setPaging (false);
          aDT.setInfo (false);
          aNodeList.addChild (aTable).addChild (aDT);
        }
        aNodeList.addChild (new HCTextArea ("responsepayload").setRows (Math.min (10,
                                                                                  1 +
                                                                                      StringCount.getCharCount (sResultContent,
                                                                                                                '\n')))
                                                              .setValue (sResultContent)
                                                              .addClass (CBootstrapCSS.FORM_CONTROL)
                                                              .addClass (CBootstrapCSS.TEXT_MONOSPACE)
                                                              .addClass (CBootstrapCSS.MB_2));
      }
    }
    final BootstrapForm aForm = aNodeList.addAndReturnChild (new BootstrapForm (aWPEC));
    aForm.setLeft (2);
    {
      final HCExtSelect aSelect = new HCExtSelect (new RequestField (FIELD_CONFIG,
                                                                     HttpClientConfigRegistry.DEFAULT_CONFIG_ID));
      aSelect.addOptionPleaseSelect (aDisplayLocale);
      for (final IHttpClientConfig aHCC : HttpClientConfigRegistry.iterate ())
        aSelect.addOption (aHCC.getID (), aHCC.getDisplayName ());
      aForm.addFormGroup (new BootstrapFormGroup ().setLabelMandatory ("Configuration to use")
                                                   .setCtrl (aSelect)
                                                   .setErrorList (aFormErrors.getListOfField (FIELD_CONFIG)));
    }
    {
      final HCExtSelect aSelect = new HCExtSelect (new RequestField (FIELD_HTTP_METHOD, EHttpMethod.GET.getName ()));
      aSelect.addOptionPleaseSelect (aDisplayLocale);
      for (final EHttpMethod e : EHttpMethod.values ())
        if (e != EHttpMethod.CONNECT)
          aSelect.addOption (e.getName ());
      aForm.addFormGroup (new BootstrapFormGroup ().setLabelMandatory ("HTTP method")
                                                   .setCtrl (aSelect)
                                                   .setErrorList (aFormErrors.getListOfField (FIELD_HTTP_METHOD)));
    }
    aForm.addFormGroup (new BootstrapFormGroup ().setLabelMandatory ("URI to query")
                                                 .setCtrl (new HCEdit (new RequestField (FIELD_URI)))
                                                 .setHelpText (new HCTextNode ("The URI to query. Must start with "),
                                                               code ("http://"),
                                                               new HCTextNode (" or "),
                                                               code ("https://"),
                                                               new HCTextNode ("."))
                                                 .setErrorList (aFormErrors.getListOfField (FIELD_URI)));
    aForm.addChild (new HCHiddenField (CPageParam.PARAM_ACTION, CPageParam.ACTION_PERFORM));
    aForm.addChild (new BootstrapSubmitButton ().addChild ("Query now"));
  }
}
