/**
 * Copyright (C) 2006-2017 BRZ GmbH
 * http://www.brz.gv.at
 *
 * All rights reserved
 */
package com.helger.photon.xservlet;

import javax.annotation.Nonnull;

import com.helger.commons.ValueEnforcer;
import com.helger.commons.string.StringHelper;
import com.helger.servlet.response.UnifiedResponse;
import com.helger.web.scope.IRequestWebScopeWithoutResponse;
import com.helger.xservlet.handler.simple.IXServletSimpleHandler;

/**
 * An {@link IXServletSimpleHandler} that does the necessary redirect for a ROOT
 * servlet. This handler should be registered for all action HTTP methods (GET,
 * POST, PUT, DELETE and PATCH).
 *
 * @author Philip Helger
 */
public class RootXServletHandler implements IXServletSimpleHandler
{
  private final String m_sServletPath;

  public RootXServletHandler (final String sServletPath)
  {
    ValueEnforcer.notEmpty (sServletPath, "ServletPath");
    ValueEnforcer.isTrue (sServletPath.startsWith ("/"), "Path must start with '/'!");

    m_sServletPath = sServletPath;
  }

  public void handleRequest (@Nonnull final IRequestWebScopeWithoutResponse aRequestScope,
                             @Nonnull final UnifiedResponse aUnifiedResponse) throws Exception
  {
    String sRedirectURL = aRequestScope.getContextPath () + m_sServletPath;

    final String sQueryString = aRequestScope.getQueryString ();
    if (StringHelper.hasText (sQueryString))
      sRedirectURL += "?" + sQueryString;

    aUnifiedResponse.setRedirect (sRedirectURL);
  }
}
