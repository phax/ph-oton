package com.helger.photon.tinymce4.type;

import javax.annotation.Nonnull;

import com.helger.commons.annotations.Nonempty;
import com.helger.photon.core.url.LinkUtils;
import com.helger.web.scopes.domain.IRequestWebScopeWithoutResponse;

public enum ETinyMCE4ExternalPlugin
{
  TEXTMACRO ("textmacro");

  private final String m_sValue;

  private ETinyMCE4ExternalPlugin (@Nonnull @Nonempty final String sValue)
  {
    m_sValue = sValue;
  }

  @Nonnull
  @Nonempty
  public String getPluginName ()
  {
    return m_sValue;
  }

  @Nonnull
  public TinyMCE4ExternalPlugin getAsExternalPlugin (@Nonnull final IRequestWebScopeWithoutResponse aRequestScope)
  {
    return new TinyMCE4ExternalPlugin (m_sValue, LinkUtils.getStreamURL (aRequestScope, "/tinymce-plugins/" +
                                                                                        m_sValue +
                                                                                        "/plugin.js"));
  }
}
