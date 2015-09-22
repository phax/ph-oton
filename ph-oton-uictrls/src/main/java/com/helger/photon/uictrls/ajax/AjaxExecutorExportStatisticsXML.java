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
package com.helger.photon.uictrls.ajax;

import javax.annotation.Nonnull;

import com.helger.commons.microdom.IMicroDocument;
import com.helger.commons.microdom.serialize.MicroWriter;
import com.helger.commons.statistics.util.StatisticsExporter;
import com.helger.photon.core.ajax.executor.AbstractAjaxExecutor;
import com.helger.photon.core.ajax.response.AjaxStringResponse;
import com.helger.web.scope.IRequestWebScopeWithoutResponse;

/**
 * A simple actions that exports the current statistics as XML
 *
 * @author Philip Helger
 */
public class AjaxExecutorExportStatisticsXML extends AbstractAjaxExecutor
{
  @Override
  @Nonnull
  protected AjaxStringResponse mainHandleRequest (@Nonnull final IRequestWebScopeWithoutResponse aRequestScope) throws Exception
  {
    final IMicroDocument aDoc = StatisticsExporter.getAsXMLDocument ();
    final String sXMLString = MicroWriter.getXMLString (aDoc);
    return AjaxStringResponse.createForXML (true, sXMLString);
  }
}
