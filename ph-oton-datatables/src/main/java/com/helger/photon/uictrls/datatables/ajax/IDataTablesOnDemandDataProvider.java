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
import org.jspecify.annotations.Nullable;

import com.helger.web.scope.IRequestWebScopeWithoutResponse;

/**
 * Callback interface to provide the data of a single DataTables page in the server side mode
 * {@link com.helger.photon.uictrls.datatables.EDataTablesServerSideMode#ON_DEMAND}. Contrary to the
 * mode <code>PRERENDERED</code> nothing is kept in the session - the implementation of this
 * interface is invoked for every single AJAX request and is expected to query only the rows of the
 * requested page from the underlying data store.<br>
 * Note on security: the sort field names of
 * {@link DataTablesOnDemandRequest#getPagingSpec()} as well as the search text are provided by the
 * client and must be treated as untrusted input. Use the field names to look up a known field only
 * - never build a query fragment from them.
 *
 * @author Philip Helger
 * @since 10.4.0
 */
@FunctionalInterface
public interface IDataTablesOnDemandDataProvider
{
  /**
   * Get the data for a single DataTables request.
   *
   * @param aRequest
   *        The request to be handled, containing the paging specification and the search text. May
   *        not be <code>null</code>.
   * @param aRequestScope
   *        The current request scope, e.g. to access additional request parameters. May not be
   *        <code>null</code>.
   * @return The result to be sent back. May be <code>null</code> to indicate, that the request
   *         cannot be handled, in which case a "404 not found" is returned to the client.
   * @throws Exception
   *         In case of an error.
   */
  @Nullable
  DataTablesOnDemandResult getData (@NonNull DataTablesOnDemandRequest aRequest,
                                    @NonNull IRequestWebScopeWithoutResponse aRequestScope) throws Exception;
}
