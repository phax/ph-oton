package com.helger.photon.bootstrap3.uictrls.ext;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.helger.html.hc.IHCNode;
import com.helger.html.hc.impl.HCTextNode;
import com.helger.photon.basic.auth.credentials.ICredentialValidationResult;
import com.helger.photon.core.app.html.IHTMLProvider;
import com.helger.photon.core.login.AbstractLoginManager;

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
  protected IHTMLProvider createLoginScreen (final boolean bLoginError,
                                             @Nonnull final ICredentialValidationResult aLoginResult)
  {
    return new BootstrapLoginHTMLProvider (bLoginError, aLoginResult, m_aPageTitle);
  }
}
