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
package com.helger.photon.core.csp;

import java.util.Map;

import org.jspecify.annotations.NonNull;

import com.helger.web.scope.IRequestWebScopeWithoutResponse;

/**
 * Contributes the query parameters that are appended to the CSP report URI of the current
 * request.<br>
 * The report body itself is created by the browser from a fixed set of fields and cannot be
 * extended. Query parameters of the report URI on the other hand are sent back verbatim, which
 * makes them the only way to add server side context to a report.<br>
 * <b>Security note:</b> the report URI is part of the policy, and the policy is both readable by
 * anyone loading the page and echoed back in the "original-policy" field of every report. Only
 * non-sensitive values belong here - a page identifier and a build version are fine, a session ID,
 * a user ID or anything else that can be correlated to a user is not.
 *
 * @author Philip Helger
 * @since 10.4.0
 */
@FunctionalInterface
public interface ICSPReportingParameterProvider
{
  /** The default implementation, contributing no parameters at all. */
  ICSPReportingParameterProvider NONE = (aRequestScope, aTarget) -> {};

  /**
   * Add the query parameters for the current request.
   *
   * @param aRequestScope
   *        The current request scope. Never <code>null</code>.
   * @param aTarget
   *        The map to add the parameters to. Never <code>null</code>.
   */
  void addReportingParameters (@NonNull IRequestWebScopeWithoutResponse aRequestScope,
                               @NonNull Map <String, String> aTarget);
}
