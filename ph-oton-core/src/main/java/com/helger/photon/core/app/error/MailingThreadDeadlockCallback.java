/**
 * Copyright (C) 2006-2015 BRZ GmbH
 * http://www.brz.gv.at
 *
 * All rights reserved
 */
package com.helger.photon.core.app.error;

import javax.annotation.Nonnull;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.helger.commons.annotation.Nonempty;
import com.helger.commons.collection.ArrayHelper;
import com.helger.commons.deadlock.IThreadDeadlockCallback;
import com.helger.commons.deadlock.ThreadDeadlockInfo;
import com.helger.commons.lang.StackTraceHelper;
import com.helger.photon.basic.thread.ThreadDescriptor;
import com.helger.photon.basic.thread.ThreadDescriptorList;
import com.helger.smtp.data.EEmailType;
import com.helger.smtp.data.EmailData;
import com.helger.smtp.scope.ScopedMailAPI;

/**
 * An implementation if {@link IThreadDeadlockCallback} that sends an internal
 * error mail
 *
 * @author Philip Helger
 */
public class MailingThreadDeadlockCallback implements IThreadDeadlockCallback
{
  private static final Logger s_aLogger = LoggerFactory.getLogger (MailingThreadDeadlockCallback.class);

  @Nonnull
  private static String _getAsString (@Nonnull final ThreadDeadlockInfo aTDI)
  {
    // Always ends with a newline char
    final StringBuilder aTI = new StringBuilder (aTDI.getThreadInfo ().toString ());
    final StackTraceElement [] aSTE = aTDI.getStackTrace ();
    if (aSTE != null)
      aTI.append (StackTraceHelper.getStackAsString (aSTE, false));
    return aTI.toString ();
  }

  public void onDeadlockDetected (@Nonnull @Nonempty final ThreadDeadlockInfo [] aDeadlockedThreads)
  {
    s_aLogger.warn ("Deadlock of " + ArrayHelper.getSize (aDeadlockedThreads) + " threads detected!");
    final StringBuilder aSB = new StringBuilder ();
    aSB.append (InternalErrorHandler.fillInternalErrorMetaData (null, null, null).getAsString ());
    for (final ThreadDeadlockInfo aTDI : aDeadlockedThreads)
      aSB.append ('\n').append (_getAsString (aTDI));

    aSB.append ("\n---------------------------------------------------------------\n")
       .append (ThreadDescriptor.createForCurrentThread (null).getAsString ())
       .append ("\n---------------------------------------------------------------\n")
       .append (ThreadDescriptorList.createWithAllThreads ().getAsString ())
       .append ("\n---------------------------------------------------------------");

    final EmailData aEmailData = new EmailData (EEmailType.TEXT);
    aEmailData.setFrom (InternalErrorHandler.getSMTPSenderAddress ());
    aEmailData.setTo (InternalErrorHandler.getSMTPReceiverAddresses ());
    aEmailData.setSubject ("[system-monitor] Dead lock of " + aDeadlockedThreads.length + " threads detected");
    aEmailData.setBody (aSB.toString ());

    ScopedMailAPI.getInstance ().queueMail (InternalErrorHandler.getSMTPSettings (), aEmailData);
  }
}
