/**
 * Copyright (C) 2014-2015 Philip Helger (www.helger.com)
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
package com.helger.photon.basic.security.audit;

import javax.annotation.Nonnull;

import org.joda.time.format.ISODateTimeFormat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.helger.commons.annotation.OverrideOnDemand;
import com.helger.photon.basic.security.login.ICurrentUserIDProvider;

/**
 * An implementation of {@link IAuditor} using SLF4J logging.
 * 
 * @author Philip Helger
 */
public class LoggingAuditor extends AbstractAuditor
{
  public static final String VALUE_SUCCESS = "success";
  public static final String VALUE_FAILURE = "failure";
  private static final Logger s_aLogger = LoggerFactory.getLogger (LoggingAuditor.class);

  public LoggingAuditor (@Nonnull final ICurrentUserIDProvider aUserIDProvider)
  {
    super (aUserIDProvider);
  }

  @Nonnull
  @OverrideOnDemand
  public static String getDefaultAuditItemString (@Nonnull final IAuditItem aAuditItem)
  {
    return ISODateTimeFormat.basicDateTime ().print (aAuditItem.getDateTime ()) +
           " " +
           aAuditItem.getUserID () +
           " " +
           aAuditItem.getType ().getID () +
           " " +
           (aAuditItem.isSuccess () ? VALUE_SUCCESS : VALUE_FAILURE) +
           " " +
           aAuditItem.getAction ();
  }

  @Nonnull
  @OverrideOnDemand
  protected String getAuditItemString (@Nonnull final IAuditItem aAuditItem)
  {
    return getDefaultAuditItemString (aAuditItem);
  }

  @Override
  protected void handleAuditItem (@Nonnull final IAuditItem aAuditItem)
  {
    s_aLogger.info (getAuditItemString (aAuditItem));
  }
}
