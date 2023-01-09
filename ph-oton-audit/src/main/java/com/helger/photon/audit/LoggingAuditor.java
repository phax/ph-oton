/*
 * Copyright (C) 2014-2023 Philip Helger (www.helger.com)
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
package com.helger.photon.audit;

import java.time.format.DateTimeFormatter;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.helger.commons.annotation.OverrideOnDemand;
import com.helger.commons.string.StringHelper;
import com.helger.security.authentication.subject.user.ICurrentUserIDProvider;

/**
 * An implementation of {@link IAuditor} using SLF4J logging.
 *
 * @author Philip Helger
 */
public class LoggingAuditor extends AbstractAuditor
{
  public static final String VALUE_SUCCESS = "success";
  public static final String VALUE_FAILURE = "failure";
  private static final Logger LOGGER = LoggerFactory.getLogger (LoggingAuditor.class);

  private final String m_sCommonPrefix;

  public LoggingAuditor (@Nonnull final ICurrentUserIDProvider aUserIDProvider)
  {
    this (aUserIDProvider, "");
  }

  public LoggingAuditor (@Nonnull final ICurrentUserIDProvider aUserIDProvider, @Nullable final String sCommonPrefix)
  {
    super (aUserIDProvider);
    m_sCommonPrefix = StringHelper.getNotNull (sCommonPrefix, "");
  }

  /**
   * @return The prefix to be used in all logging lines. Never <code>null</code>
   *         but maybe empty.
   */
  @Nonnull
  public String getCommonPrefix ()
  {
    return m_sCommonPrefix;
  }

  @Nonnull
  @OverrideOnDemand
  public static String getDefaultAuditItemString (@Nonnull final IAuditItem aAuditItem)
  {
    return DateTimeFormatter.ISO_DATE_TIME.format (aAuditItem.getDateTime ()) +
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
    return m_sCommonPrefix + getDefaultAuditItemString (aAuditItem);
  }

  @Override
  protected void handleAuditItem (@Nonnull final IAuditItem aAuditItem)
  {
    LOGGER.info (getAuditItemString (aAuditItem));
  }
}
