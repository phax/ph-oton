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
package com.helger.photon.core.smtp;

import javax.annotation.Nonnull;
import javax.mail.event.TransportEvent;

import com.helger.commons.string.ToStringGenerator;
import com.helger.photon.basic.security.audit.AuditUtils;
import com.helger.smtp.IEmailData;
import com.helger.smtp.IEmailDataTransportListener;
import com.helger.smtp.ISMTPSettings;
import com.helger.smtp.transport.listener.LoggingTransportListener;

/**
 * An implementation of {@link IEmailDataTransportListener} that performs audit
 * calls for successfully delivered, not delivered and partially delivered
 * messages.
 *
 * @author Philip Helger
 */
public class AuditingEmailDataTransportListener implements IEmailDataTransportListener
{
  public void messageDelivered (@Nonnull final ISMTPSettings aSMTPSettings,
                                @Nonnull final IEmailData aEmailData,
                                @Nonnull final TransportEvent aEvent)
  {
    AuditUtils.onAuditExecuteSuccess ("email-message-delivered",
                                      aEmailData.getSubject (),
                                      aEvent.getValidSentAddresses (),
                                      aEvent.getValidUnsentAddresses (),
                                      aEvent.getInvalidAddresses (),
                                      LoggingTransportListener.getMessageString (aEvent.getMessage ()));
  }

  public void messageNotDelivered (@Nonnull final ISMTPSettings aSMTPSettings,
                                   @Nonnull final IEmailData aEmailData,
                                   @Nonnull final TransportEvent aEvent)
  {
    AuditUtils.onAuditExecuteFailure ("email-message-delivered",
                                      "not-delivered",
                                      aEmailData.getSubject (),
                                      aEvent.getValidSentAddresses (),
                                      aEvent.getValidUnsentAddresses (),
                                      aEvent.getInvalidAddresses (),
                                      LoggingTransportListener.getMessageString (aEvent.getMessage ()));
  }

  public void messagePartiallyDelivered (@Nonnull final ISMTPSettings aSMTPSettings,
                                         @Nonnull final IEmailData aEmailData,
                                         @Nonnull final TransportEvent aEvent)
  {
    AuditUtils.onAuditExecuteFailure ("email-message-delivered",
                                      "partially-delivered",
                                      aEmailData.getSubject (),
                                      aEvent.getValidSentAddresses (),
                                      aEvent.getValidUnsentAddresses (),
                                      aEvent.getInvalidAddresses (),
                                      LoggingTransportListener.getMessageString (aEvent.getMessage ()));
  }

  @Override
  public String toString ()
  {
    return new ToStringGenerator (this).toString ();
  }
}
