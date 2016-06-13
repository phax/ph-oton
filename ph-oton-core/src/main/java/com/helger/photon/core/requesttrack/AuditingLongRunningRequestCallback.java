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
package com.helger.photon.core.requesttrack;

import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;

import com.helger.commons.annotation.Nonempty;
import com.helger.commons.string.ToStringGenerator;
import com.helger.photon.basic.audit.AuditHelper;
import com.helger.web.scope.IRequestWebScope;
import com.helger.web.scope.util.RequestHelper;

/**
 * A simple implementation of {@link ILongRunningRequestCallback} simply
 * auditing such events.
 *
 * @author Philip Helger
 * @since 4.0.0
 */
public class AuditingLongRunningRequestCallback implements ILongRunningRequestCallback
{
  public AuditingLongRunningRequestCallback ()
  {}

  public void onLongRunningRequest (@Nonnull @Nonempty final String sUniqueRequestID,
                                    @Nonnull final IRequestWebScope aRequestScope,
                                    @Nonnegative final long nRunningMilliseconds)
  {
    AuditHelper.onAuditExecuteSuccess ("long-running-request",
                                       sUniqueRequestID,
                                       Long.valueOf (nRunningMilliseconds),
                                       RequestHelper.getURL (aRequestScope.getRequest ()));
  }

  @Override
  public String toString ()
  {
    return new ToStringGenerator (this).toString ();
  }
}
