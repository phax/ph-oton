/**
 * Copyright (C) 2014-2019 Philip Helger (www.helger.com)
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

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.helger.commons.error.level.EErrorLevel;
import com.helger.commons.error.level.IErrorLevel;
import com.helger.commons.log.LogHelper;
import com.helger.commons.string.StringHelper;
import com.helger.photon.ajax.executor.IAjaxExecutor;
import com.helger.photon.core.PhotonUnifiedResponse;
import com.helger.web.scope.IRequestWebScopeWithoutResponse;

public class AjaxExecutorServerLog implements IAjaxExecutor
{
  private static final Logger LOGGER = LoggerFactory.getLogger (AjaxExecutorServerLog.class);
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

  public void handleRequest (@Nonnull final IRequestWebScopeWithoutResponse aRequestScope,
                             @Nonnull final PhotonUnifiedResponse aAjaxResponse) throws Exception
  {
    final String sSeverity = aRequestScope.params ().getAsString (PARAM_SEVERITY);
    final String sMessage = aRequestScope.params ().getAsString (PARAM_MESSAGE);
    final String sKey = aRequestScope.params ().getAsString (PARAM_KEY);
    final String sExpectedKey = ServerLogSessionKey.getGeneratedSessionKey ();
    if (StringHelper.hasNoText (sMessage) || sExpectedKey == null || !sExpectedKey.equals (sKey))
    {
      LOGGER.error ("Missing required parameter");
      aAjaxResponse.createBadRequest ();
      return;
    }

    // Main logging
    final IErrorLevel aSeverity = getErrorLevelFromString (sSeverity);
    LogHelper.log (AjaxExecutorServerLog.class, aSeverity, sMessage);
    aAjaxResponse.jsonEmpty ();
  }
}
