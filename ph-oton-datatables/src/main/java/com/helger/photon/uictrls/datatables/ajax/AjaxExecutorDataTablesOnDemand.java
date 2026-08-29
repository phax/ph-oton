/*
 * Copyright (C) 2014-2026 Philip Helger (www.helger.com)
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
package com.helger.photon.uictrls.datatables.ajax;

import org.jspecify.annotations.NonNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.helger.base.enforce.ValueEnforcer;
import com.helger.base.tostring.ToStringGenerator;
import com.helger.collection.commons.CommonsArrayList;
import com.helger.collection.commons.ICommonsList;
import com.helger.html.hc.html.tabular.HCRow;
import com.helger.html.hc.special.HCSpecialNodes;
import com.helger.json.IJsonObject;
import com.helger.photon.ajax.executor.IAjaxExecutor;
import com.helger.photon.app.PhotonUnifiedResponse;
import com.helger.web.scope.IRequestWebScopeWithoutResponse;

/**
 * AJAX handler for filling a DataTables that runs in the server side mode
 * {@link com.helger.photon.uictrls.datatables.EDataTablesServerSideMode#ON_DEMAND}. Contrary to
 * {@link AjaxExecutorDataTables} nothing is read from the session - all the data comes from the
 * provided {@link IDataTablesOnDemandDataProvider}, so only the rows of the requested page are ever
 * rendered.
 *
 * @author Philip Helger
 * @since 10.4.0
 */
public class AjaxExecutorDataTablesOnDemand implements IAjaxExecutor
{
  private static final Logger LOGGER = LoggerFactory.getLogger (AjaxExecutorDataTablesOnDemand.class);

  private final IDataTablesOnDemandDataProvider m_aDataProvider;

  /**
   * Constructor.
   *
   * @param aDataProvider
   *        The provider that delivers the rows of a single page. May not be <code>null</code>.
   */
  public AjaxExecutorDataTablesOnDemand (@NonNull final IDataTablesOnDemandDataProvider aDataProvider)
  {
    m_aDataProvider = ValueEnforcer.notNull (aDataProvider, "DataProvider");
  }

  /**
   * @return The data provider as provided in the constructor. Never <code>null</code>.
   */
  @NonNull
  public final IDataTablesOnDemandDataProvider getDataProvider ()
  {
    return m_aDataProvider;
  }

  public void handleRequest (@NonNull final IRequestWebScopeWithoutResponse aRequestScope,
                             @NonNull final PhotonUnifiedResponse aAjaxResponse) throws Exception
  {
    final DTSSRequestData aRequestData = AjaxExecutorDataTables.extractDTSRequestData (aRequestScope);
    final DataTablesOnDemandRequest aRequest = new DataTablesOnDemandRequest (aRequestData);

    if (LOGGER.isDebugEnabled ())
      LOGGER.debug ("DataTables on demand AJAX request: " + aRequest);

    final DataTablesOnDemandResult aResult = m_aDataProvider.getData (aRequest, aRequestScope);
    if (aResult == null)
    {
      LOGGER.error ("The DataTables on demand data provider was not able to handle the request " + aRequest);
      aAjaxResponse.createNotFound ();
      return;
    }

    // Render only the rows of the current page
    final HCSpecialNodes aSpecialNodes = new HCSpecialNodes ();
    final ICommonsList <IJsonObject> aData = new CommonsArrayList <> (aResult.directGetAllRows ().size ());
    for (final HCRow aRow : aResult.directGetAllRows ())
      aData.add (new DataTablesServerDataRow (aRow).getAsJson (aSpecialNodes));

    final String sErrorMsg = null;
    final DTSSResponseData aResponseData = new DTSSResponseData (aRequestData.getDraw (),
                                                                 aResult.getTotalCount (),
                                                                 aResult.getFilteredCount (),
                                                                 aData,
                                                                 sErrorMsg,
                                                                 aSpecialNodes);

    // Convert the response to JSON and add the special nodes
    aAjaxResponse.json (PhotonUnifiedResponse.HtmlHelper.getResponseAsJSON (aResponseData.getAsJson (),
                                                                            aResponseData.getSpecialNodes ()));
  }

  @Override
  public String toString ()
  {
    return new ToStringGenerator (this).append ("DataProvider", m_aDataProvider).getToString ();
  }
}
