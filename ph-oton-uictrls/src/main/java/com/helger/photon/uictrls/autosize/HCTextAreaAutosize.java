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
package com.helger.photon.uictrls.autosize;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.helger.html.hc.IHCConversionSettingsToNode;
import com.helger.html.hc.html.forms.AbstractHCTextArea;
import com.helger.html.request.IHCRequestField;
import com.helger.photon.app.html.PhotonJS;
import com.helger.photon.uictrls.EUICtrlsJSPathProvider;

/**
 * jQuery autosize plugin from
 *
 * <pre>
 * http://www.jacklmoore.com/autosize
 * </pre>
 *
 * @author Philip Helger
 */
public class HCTextAreaAutosize extends AbstractHCTextArea <HCTextAreaAutosize>
{
  private boolean m_bUseV3 = true;

  public HCTextAreaAutosize (@Nullable final String sName)
  {
    super (sName);
  }

  public HCTextAreaAutosize (@Nullable final String sName, @Nullable final String sValue)
  {
    super (sName, sValue);
  }

  public HCTextAreaAutosize (@Nonnull final IHCRequestField aRF)
  {
    super (aRF);
  }

  public boolean isUseV3 ()
  {
    return m_bUseV3;
  }

  @Nonnull
  public HCTextAreaAutosize setUseV3 (final boolean bUseV3)
  {
    m_bUseV3 = bUseV3;
    return this;
  }

  @Override
  protected void onRegisterExternalResources (@Nonnull final IHCConversionSettingsToNode aConversionSettings,
                                              final boolean bForcedRegistration)
  {
    if (m_bUseV3)
    {
      PhotonJS.registerJSIncludeForThisRequest (EUICtrlsJSPathProvider.AUTOSIZE3);
      PhotonJS.registerJSIncludeForThisRequest (EUICtrlsJSPathProvider.AUTOSIZE3_ALL);
    }
    else
    {
      PhotonJS.registerJSIncludeForThisRequest (EUICtrlsJSPathProvider.AUTOSIZE);
      PhotonJS.registerJSIncludeForThisRequest (EUICtrlsJSPathProvider.AUTOSIZE_ALL);
    }
  }
}
