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
package com.helger.photon.uictrls.datatables.ajax;

import java.nio.charset.StandardCharsets;

import javax.annotation.Nonnull;

import com.helger.commons.mime.CMimeType;
import com.helger.html.hc.special.HCSpecialNodes;
import com.helger.html.hc.special.IHCSpecialNodes;
import com.helger.json.IJsonObject;
import com.helger.photon.core.ajax.response.AjaxHtmlResponse;
import com.helger.photon.core.ajax.response.IAjaxResponse;
import com.helger.servlet.response.UnifiedResponse;

public class AjaxDatatablesResponse implements IAjaxResponse
{
  private final IJsonObject m_aResponseData;
  private final HCSpecialNodes m_aSpecialNodes = new HCSpecialNodes ();

  public AjaxDatatablesResponse (@Nonnull final IJsonObject aResponseData,
                                 @Nonnull final IHCSpecialNodes aAdditionalSpecialNodes)
  {
    m_aResponseData = aResponseData;
    m_aSpecialNodes.addAll (aAdditionalSpecialNodes);
  }

  public void applyToResponse (final UnifiedResponse aUnifiedResponse)
  {
    final IJsonObject aJson = AjaxHtmlResponse.getResponseAsJSON (m_aResponseData, m_aSpecialNodes);
    aUnifiedResponse.setContentAndCharset (aJson.getAsJsonString (), StandardCharsets.UTF_8)
                    .setMimeType (CMimeType.APPLICATION_JSON);
  }
}
