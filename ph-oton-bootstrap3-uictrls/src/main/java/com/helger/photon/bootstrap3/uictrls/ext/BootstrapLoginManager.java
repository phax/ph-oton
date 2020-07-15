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
package com.helger.photon.bootstrap3.uictrls.ext;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.helger.html.hc.IHCNode;
import com.helger.html.hc.impl.HCTextNode;
import com.helger.photon.app.html.IHTMLProvider;
import com.helger.photon.core.login.AbstractLoginManager;
import com.helger.security.authentication.credentials.ICredentialValidationResult;

public class BootstrapLoginManager extends AbstractLoginManager
{
  private final IHCNode m_aPageTitle;

  public BootstrapLoginManager (@Nullable final String sPageTitle)
  {
    this (HCTextNode.createOnDemand (sPageTitle));
  }

  public BootstrapLoginManager (@Nullable final IHCNode aPageTitle)
  {
    m_aPageTitle = aPageTitle;
  }

  @Nullable
  protected final IHCNode getPageTitle ()
  {
    return m_aPageTitle;
  }

  @Override
  protected IHTMLProvider createLoginScreen (final boolean bLoginError, @Nonnull final ICredentialValidationResult aLoginResult)
  {
    return new BootstrapLoginHTMLProvider (bLoginError, aLoginResult, m_aPageTitle);
  }
}
