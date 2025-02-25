/*
 * Copyright (C) 2014-2025 Philip Helger and contributors
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
package com.helger.photon.bootstrap.demo.servlet;

import java.io.IOException;

import javax.annotation.Nonnull;

import com.helger.commons.http.CHttpHeader;
import com.helger.http.csp.CSPDirective;
import com.helger.http.csp.CSPPolicy;
import com.helger.http.csp.CSPSourceList;
import com.helger.http.csp.ECSPMode;
import com.helger.photon.app.csrf.CSRFSessionManager;
import com.helger.photon.core.servlet.AbstractApplicationXServletHandler;
import com.helger.servlet.response.UnifiedResponse;
import com.helger.web.scope.IRequestWebScopeWithoutResponse;

import jakarta.servlet.ServletException;

/**
 * CSP enabled application servlet handler
 *
 * @author Philip Helger
 */
public abstract class AbstractApplicationXServletHandlerWithCSP extends AbstractApplicationXServletHandler
{
  private final ECSPMode m_eCSPMode;

  protected AbstractApplicationXServletHandlerWithCSP (@Nonnull final ECSPMode eCSPMode)
  {
    m_eCSPMode = eCSPMode;
  }

  @Override
  public void handleRequest (final IRequestWebScopeWithoutResponse aRequestScope,
                             final UnifiedResponse aUnifiedResponse) throws IOException, ServletException
  {
    final CSPSourceList aScriptSrcList = new CSPSourceList ().addKeywordSelf ()
                                                             .addNonce (CSRFSessionManager.getInstance ().getNonce ())
                                                             .addKeywordReportSample ();
    final CSPSourceList aStyleSrcList = new CSPSourceList ().addKeywordSelf ()
                                                            .addNonce (CSRFSessionManager.getInstance ().getNonce ())
                                                            .addKeywordReportSample ();
    // Required for data tables
    final CSPSourceList aStyleSrcAttrList = false ? null
                                                  : new CSPSourceList ().addKeywordSelf ().addKeywordUnsafeInline ();
    // Allow data images for Bootstrap 4
    final CSPSourceList aImgSrcList = new CSPSourceList ().addKeywordSelf ().addHost ("data:");
    final CSPSourceList aConnectSrcList = new CSPSourceList ().addKeywordSelf ();
    final CSPSourceList aFontSrcList = new CSPSourceList ().addKeywordSelf ();

    final CSPPolicy aPolicy = new CSPPolicy ();
    aPolicy.addDirective (CSPDirective.createDefaultSrc (new CSPSourceList ().addKeywordNone ()))
           .addDirective (CSPDirective.createScriptSrc (aScriptSrcList))
           .addDirective (CSPDirective.createStyleSrc (aStyleSrcList))
           .addDirective (CSPDirective.createStyleSrcAttr (aStyleSrcAttrList))
           .addDirective (CSPDirective.createImgSrc (aImgSrcList))
           .addDirective (CSPDirective.createConnectSrc (aConnectSrcList))
           .addDirective (CSPDirective.createFontSrc (aFontSrcList));

    if (m_eCSPMode.isReporting ())
    {
      // Report only if enabled - avoid spamming
      aPolicy.addDirective (CSPDirective.createReportURI (aRequestScope.getContextPath () +
                                                          CSPReportingServlet.SERVLET_DEFAULT_PATH));
    }

    // Default
    aUnifiedResponse.addCustomResponseHeader (m_eCSPMode.isReportingOnly () ? CHttpHeader.CONTENT_SECURITY_POLICY_REPORT_ONLY
                                                                            : CHttpHeader.CONTENT_SECURITY_POLICY,
                                              aPolicy.getAsString ());
    // IE specific
    aUnifiedResponse.addCustomResponseHeader (m_eCSPMode.isReportingOnly () ? CHttpHeader.X_CONTENT_SECURITY_POLICY_REPORT_ONLY
                                                                            : CHttpHeader.X_CONTENT_SECURITY_POLICY,
                                              aPolicy.getAsString ());

    super.handleRequest (aRequestScope, aUnifiedResponse);
  }
}
