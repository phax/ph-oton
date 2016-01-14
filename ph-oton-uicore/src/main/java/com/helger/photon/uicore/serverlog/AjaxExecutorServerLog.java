/**
 * Copyright (C) 2014-2016 Philip Helger (www.helger.com)
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
package com.helger.photon.uicore.serverlog;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.helger.commons.error.EErrorLevel;
import com.helger.commons.error.IErrorLevel;
import com.helger.commons.log.LogHelper;
import com.helger.commons.string.StringHelper;
import com.helger.photon.core.ajax.executor.AbstractAjaxExecutor;
import com.helger.photon.core.ajax.response.AjaxJsonResponse;
import com.helger.web.scope.IRequestWebScopeWithoutResponse;

public class AjaxExecutorServerLog extends AbstractAjaxExecutor
{
  private static final IErrorLevel DEFAULT_SEVERITY = EErrorLevel.INFO;
  private static final String PARAM_SEVERITY = "severity";
  private static final String PARAM_MESSAGE = "message";
  private static final String PARAM_KEY = "key";

  @Nonnull
  public static IErrorLevel getErrorLevelFromString (@Nullable final String sSeverity)
  {
    if (StringHelper.hasText (sSeverity))
    {
      if ("fatal".equalsIgnoreCase (sSeverity))
        return EErrorLevel.FATAL_ERROR;
      if ("error".equalsIgnoreCase (sSeverity))
        return EErrorLevel.ERROR;
      if ("warn".equalsIgnoreCase (sSeverity) || "warning".equalsIgnoreCase (sSeverity))
        return EErrorLevel.WARN;
      if ("info".equalsIgnoreCase (sSeverity))
        return EErrorLevel.INFO;
      if ("success".equalsIgnoreCase (sSeverity) ||
          "debug".equalsIgnoreCase (sSeverity) ||
          "trace".equalsIgnoreCase (sSeverity))
        return EErrorLevel.SUCCESS;
    }
    return DEFAULT_SEVERITY;
  }

  @Override
  @Nonnull
  protected AjaxJsonResponse mainHandleRequest (@Nonnull final IRequestWebScopeWithoutResponse aRequestScope) throws Exception
  {
    final String sSeverity = aRequestScope.getAttributeAsString (PARAM_SEVERITY);
    final String sMessage = aRequestScope.getAttributeAsString (PARAM_MESSAGE);
    final String sKey = aRequestScope.getAttributeAsString (PARAM_KEY);
    final String sExpectedKey = ServerLogSessionKey.getGeneratedSessionKey ();
    if (StringHelper.hasNoText (sMessage) || sExpectedKey == null || !sExpectedKey.equals (sKey))
      return AjaxJsonResponse.createError ("Missing required parameter");

    // Main logging
    final IErrorLevel aSeverity = getErrorLevelFromString (sSeverity);
    LogHelper.log (AjaxExecutorServerLog.class, aSeverity, sMessage);

    return AjaxJsonResponse.createSuccess ();
  }
}
