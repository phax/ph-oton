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
package com.helger.photon.core.interror.callback;

import javax.annotation.Nonnull;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.helger.commons.ValueEnforcer;
import com.helger.commons.annotation.Nonempty;
import com.helger.commons.collection.ArrayHelper;
import com.helger.commons.deadlock.IThreadDeadlockCallback;
import com.helger.commons.deadlock.ThreadDeadlockInfo;
import com.helger.commons.lang.StackTraceHelper;
import com.helger.photon.core.interror.InternalErrorHandler;
import com.helger.photon.core.interror.InternalErrorSettings;
import com.helger.smtp.data.EEmailType;
import com.helger.smtp.data.EmailData;
import com.helger.smtp.scope.ScopedMailAPI;
import com.helger.xml.util.thread.ThreadDescriptor;
import com.helger.xml.util.thread.ThreadDescriptorList;

/**
 * An implementation if {@link IThreadDeadlockCallback} that sends an internal
 * error mail
 *
 * @author Philip Helger
 */
public class MailingThreadDeadlockCallback implements IThreadDeadlockCallback
{
  private static final Logger LOGGER = LoggerFactory.getLogger (MailingThreadDeadlockCallback.class);

  @Nonnull
  public static String getAsString (@Nonnull final ThreadDeadlockInfo aTDI)
  {
    ValueEnforcer.notNull (aTDI, "TDI");

    // Always ends with a newline char
    final StringBuilder aTI = new StringBuilder (aTDI.getThreadInfo ().toString ());
    final StackTraceElement [] aSTE = aTDI.getAllStackTraceElements ();
    if (aSTE != null)
      aTI.append (StackTraceHelper.getStackAsString (aSTE, false));
    return aTI.toString ();
  }

  public void onDeadlockDetected (@Nonnull @Nonempty final ThreadDeadlockInfo [] aDeadlockedThreads)
  {
    LOGGER.warn ("Deadlock of " + ArrayHelper.getSize (aDeadlockedThreads) + " threads detected!");

    final StringBuilder aSB = new StringBuilder ();
    aSB.append (InternalErrorHandler.fillInternalErrorMetaData (null, null, null).getAsString ());
    for (final ThreadDeadlockInfo aTDI : aDeadlockedThreads)
      aSB.append ('\n').append (getAsString (aTDI));

    aSB.append ("\n---------------------------------------------------------------\n")
       .append (ThreadDescriptor.createForCurrentThread (null).getAsString ())
       .append ("\n---------------------------------------------------------------\n")
       .append (ThreadDescriptorList.createWithAllThreads ().getAsString ())
       .append ("\n---------------------------------------------------------------");

    final EmailData aEmailData = new EmailData (EEmailType.TEXT);
    aEmailData.setFrom (InternalErrorSettings.getSMTPSenderAddress ());
    aEmailData.to ().addAll (InternalErrorSettings.getSMTPReceiverAddresses ());
    aEmailData.setSubject ("[ph-oton] Dead lock of " + aDeadlockedThreads.length + " threads detected");
    aEmailData.setBody (aSB.toString ());

    ScopedMailAPI.getInstance ().queueMail (InternalErrorSettings.getSMTPSettings (), aEmailData);
  }
}
