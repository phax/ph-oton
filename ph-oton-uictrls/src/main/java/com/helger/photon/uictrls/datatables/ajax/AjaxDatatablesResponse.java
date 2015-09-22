package com.helger.photon.uictrls.datatables.ajax;

import javax.annotation.Nonnull;

import com.helger.commons.charset.CCharset;
import com.helger.commons.mime.CMimeType;
import com.helger.html.hc.special.HCSpecialNodes;
import com.helger.html.hc.special.IHCSpecialNodes;
import com.helger.json.IJsonObject;
import com.helger.photon.core.ajax.response.AbstractAjaxResponse;
import com.helger.photon.core.ajax.response.AjaxHtmlResponse;
import com.helger.web.servlet.response.UnifiedResponse;

public class AjaxDatatablesResponse extends AbstractAjaxResponse
{
  private final IJsonObject m_aResponseData;
  private final HCSpecialNodes m_aSpecialNodes = new HCSpecialNodes ();

  public AjaxDatatablesResponse (@Nonnull final IJsonObject aResponseData,
                                 @Nonnull final IHCSpecialNodes aAdditionalSpecialNodes)
  {
    super (true);
    m_aResponseData = aResponseData;
    m_aSpecialNodes.addAll (aAdditionalSpecialNodes);
  }

  public void applyToResponse (final UnifiedResponse aUnifiedResponse)
  {
    final IJsonObject aJson = AjaxHtmlResponse.getResponseAsJSON (isSuccess (), m_aResponseData, m_aSpecialNodes, null);
    aUnifiedResponse.setContentAndCharset (aJson.getAsString (), CCharset.CHARSET_UTF_8_OBJ)
                    .setMimeType (CMimeType.APPLICATION_JSON);
  }
}
