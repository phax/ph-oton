/*
 * Copyright (C) 2014-2025 Philip Helger (www.helger.com)
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
package com.helger.photon.core.interror;

import java.io.File;
import java.time.LocalDateTime;
import java.util.Locale;
import java.util.function.Function;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.concurrent.GuardedBy;
import javax.annotation.concurrent.ThreadSafe;

import com.helger.commons.ValueEnforcer;
import com.helger.commons.annotation.ReturnsMutableCopy;
import com.helger.commons.annotation.ReturnsMutableObject;
import com.helger.commons.callback.CallbackList;
import com.helger.commons.collection.impl.ICommonsList;
import com.helger.commons.concurrent.SimpleReadWriteLock;
import com.helger.commons.datetime.PDTFactory;
import com.helger.commons.email.IEmailAddress;
import com.helger.commons.string.StringHelper;
import com.helger.datetime.util.PDTIOHelper;
import com.helger.photon.core.interror.callback.IInternalErrorCallback;
import com.helger.photon.io.WebFileIO;
import com.helger.smtp.settings.ISMTPSettings;

/**
 * Global settings for internal error handling.
 *
 * @author Philip Helger
 * @see InternalErrorBuilder
 * @see InternalErrorEmailSettings
 */
@ThreadSafe
public final class InternalErrorSettings
{
  public static final boolean DEFAULT_ENABLE_FULL_THREAD_DUMPS = false;
  /**
   * By default each internal error is also stored as XML.
   *
   * @since 8.2.2
   */
  public static final boolean DEFAULT_SEND_EMAIL = true;
  /**
   * By default each internal error is also stored as XML.
   *
   * @since 8.2.2
   */
  public static final boolean DEFAULT_SAVE_AS_XML = true;

  private static final SimpleReadWriteLock RW_LOCK = new SimpleReadWriteLock ();
  @GuardedBy ("RW_LOCK")
  private static final InternalErrorEmailSettings EMAIL_SETTINGS = new InternalErrorEmailSettings ();
  @GuardedBy ("RW_LOCK")
  private static boolean s_bEnableDumpAllThreads = DEFAULT_ENABLE_FULL_THREAD_DUMPS;
  @GuardedBy ("RW_LOCK")
  private static boolean s_bSendEmail = DEFAULT_SEND_EMAIL;
  @GuardedBy ("RW_LOCK")
  private static boolean s_bSaveAsXML = DEFAULT_SAVE_AS_XML;
  @GuardedBy ("RW_LOCK")
  private static Locale s_aFallbackLocale = Locale.US;
  private static final CallbackList <IInternalErrorCallback> CALLBACKS = new CallbackList <> ();
  @GuardedBy ("RW_LOCK")
  private static Function <InternalErrorMetadata, File> s_aStorageFileProvider;

  static
  {
    setDefaultStorageFileProvider ();
  }

  private InternalErrorSettings ()
  {}

  public static void setSMTPSettings (@Nullable final ISMTPSettings aSMTPSettings)
  {
    RW_LOCK.writeLocked ( () -> EMAIL_SETTINGS.setSMTPSettings (aSMTPSettings));
  }

  @Nullable
  public static ISMTPSettings getSMTPSettings ()
  {
    return RW_LOCK.readLockedGet (EMAIL_SETTINGS::getSMTPSettings);
  }

  public static void setSMTPSenderAddress (@Nullable final IEmailAddress aSenderAddress)
  {
    RW_LOCK.writeLocked ( () -> EMAIL_SETTINGS.setSenderAddress (aSenderAddress));
  }

  @Nullable
  public static IEmailAddress getSMTPSenderAddress ()
  {
    return RW_LOCK.readLockedGet (EMAIL_SETTINGS::getSenderAddress);
  }

  public static void setSMTPReceiverAddress (@Nullable final IEmailAddress aReceiverAddress)
  {
    RW_LOCK.writeLocked ( () -> EMAIL_SETTINGS.setReceiverAddress (aReceiverAddress));
  }

  public static void setSMTPReceiverAddresses (@Nullable final Iterable <? extends IEmailAddress> aReceiverAddresses)
  {
    RW_LOCK.writeLocked ( () -> EMAIL_SETTINGS.setReceiverAddresses (aReceiverAddresses));
  }

  public static void setSMTPReceiverAddresses (@Nullable final IEmailAddress... aReceiverAddresses)
  {
    RW_LOCK.writeLocked ( () -> EMAIL_SETTINGS.setReceiverAddresses (aReceiverAddresses));
  }

  @Nonnull
  @ReturnsMutableCopy
  public static ICommonsList <IEmailAddress> getSMTPReceiverAddresses ()
  {
    return RW_LOCK.readLockedGet (EMAIL_SETTINGS::getAllReceiverAddresses);
  }

  @Nonnull
  @ReturnsMutableCopy
  public static InternalErrorEmailSettings getCopyOfEmailSettings ()
  {
    return RW_LOCK.readLockedGet (EMAIL_SETTINGS::getClone);
  }

  /**
   * Enable the creation of a dump of all threads. Warning: this takes a lot of
   * CPU, so enable this only when you are not running a performance critical
   * application! The default is {@value #DEFAULT_ENABLE_FULL_THREAD_DUMPS}.
   *
   * @param bEnableDumpAllThreads
   *        <code>true</code> to enabled, <code>false</code> to disable.
   */
  public static void setDumpAllThreads (final boolean bEnableDumpAllThreads)
  {
    RW_LOCK.writeLocked ( () -> s_bEnableDumpAllThreads = bEnableDumpAllThreads);
  }

  /**
   * @return <code>true</code> to dump all threads on internal error,
   *         <code>false</code> to not do it.
   */
  public static boolean isDumpAllThreads ()
  {
    return RW_LOCK.readLockedBoolean ( () -> s_bEnableDumpAllThreads);
  }

  /**
   * Send an email when an internal error occurs? Sending happens only if
   * configuration is available.
   *
   * @param bSendEmail
   *        <code>true</code> to send, <code>false</code> to not send
   */
  public static void setSendEmail (final boolean bSendEmail)
  {
    RW_LOCK.writeLocked ( () -> s_bSendEmail = bSendEmail);
  }

  /**
   * @return <code>true</code> to send an email on an internal error,
   *         <code>false</code> to not do it.
   */
  public static boolean isSendEmail ()
  {
    return RW_LOCK.readLockedBoolean ( () -> s_bSendEmail);
  }

  /**
   * Save an internal error as XML?
   *
   * @param bSaveAsXML
   *        <code>true</code> to save as XML, <code>false</code> to not save
   */
  public static void setSaveAsXML (final boolean bSaveAsXML)
  {
    RW_LOCK.writeLocked ( () -> s_bSaveAsXML = bSaveAsXML);
  }

  /**
   * @return <code>true</code> to save internal error as XML, <code>false</code>
   *         to not do it.
   */
  public static boolean isSaveAsXML ()
  {
    return RW_LOCK.readLockedBoolean ( () -> s_bSaveAsXML);
  }

  /**
   * @return The fallback locale to use. Never <code>null</code>.
   * @since 7.0.4
   */
  @Nonnull
  public static Locale getFallbackLocale ()
  {
    return RW_LOCK.readLockedGet ( () -> s_aFallbackLocale);
  }

  /**
   * Set the fallback locale in case none could be determined.
   *
   * @param aFallbackLocale
   *        Locale to use. May not be <code>null</code>.
   * @since 7.0.4
   */
  public static void setFallbackLocale (@Nonnull final Locale aFallbackLocale)
  {
    ValueEnforcer.notNull (aFallbackLocale, "FallbackLocale");
    RW_LOCK.writeLocked ( () -> s_aFallbackLocale = aFallbackLocale);
  }

  /**
   * @return The current custom internal error callbacks. Never
   *         <code>null</code>.
   */
  @Nonnull
  @ReturnsMutableObject
  public static CallbackList <IInternalErrorCallback> callbacks ()
  {
    return CALLBACKS;
  }

  /**
   * @return The provider that defines where to save internal errors to.
   * @since 8.0.3
   */
  @Nonnull
  public static Function <InternalErrorMetadata, File> getStorageFileProvider ()
  {
    return RW_LOCK.readLockedGet ( () -> s_aStorageFileProvider);
  }

  /**
   * Set the provider that defines how to build the filename to save internal
   * error files.
   *
   * @param aStorageFileProvider
   *        Storage provider. May not be <code>null</code>
   * @since 8.0.3
   */
  public static void setStorageFileProvider (@Nonnull final Function <InternalErrorMetadata, File> aStorageFileProvider)
  {
    ValueEnforcer.notNull (aStorageFileProvider, "StorageFileProvider");
    RW_LOCK.writeLocked ( () -> s_aStorageFileProvider = aStorageFileProvider);
  }

  /**
   * Set the default storage file provider. In case you played around and want
   * to restore the default behavior.
   *
   * @see #setStorageFileProvider(Function)
   * @since 9.2.2
   */
  public static void setDefaultStorageFileProvider ()
  {
    setStorageFileProvider (aMetadata -> {
      final LocalDateTime aNow = PDTFactory.getCurrentLocalDateTime ();
      final String sFilename = StringHelper.getConcatenatedOnDemand (PDTIOHelper.getLocalDateTimeForFilename (aNow),
                                                                     "-",
                                                                     aMetadata.getErrorID ()) +
                               ".xml";
      return WebFileIO.getDataIO ()
                      .getFile ("internal-errors/" +
                                aNow.getYear () +
                                "/" +
                                StringHelper.getLeadingZero (aNow.getMonthValue (), 2) +
                                "/" +
                                StringHelper.getLeadingZero (aNow.getDayOfMonth (), 2) +
                                "/" +
                                sFilename);
    });
  }

  /**
   * Set the storage file provider to the default before v9.2.2. The difference
   * to {@link #setDefaultStorageFileProvider()} is, that this version does not
   * contain a "day" subfolder.
   *
   * @see #setStorageFileProvider(Function)
   * @since 8.0.3
   */
  @Deprecated (forRemoval = false)
  public static void setDefaultStorageFileProviderUpTo921 ()
  {
    setStorageFileProvider (aMetadata -> {
      final LocalDateTime aNow = PDTFactory.getCurrentLocalDateTime ();
      final String sFilename = StringHelper.getConcatenatedOnDemand (PDTIOHelper.getLocalDateTimeForFilename (aNow),
                                                                     "-",
                                                                     aMetadata.getErrorID ()) +
                               ".xml";
      return WebFileIO.getDataIO ()
                      .getFile ("internal-errors/" +
                                aNow.getYear () +
                                "/" +
                                StringHelper.getLeadingZero (aNow.getMonthValue (), 2) +
                                "/" +
                                sFilename);
    });
  }

  /**
   * Set the storage file provider to the default before v8.0.3. The difference
   * to {@link #setDefaultStorageFileProvider()} is, that this version does not
   * contain a "month" subfolder.
   *
   * @see #setStorageFileProvider(Function)
   */
  @Deprecated (forRemoval = false)
  public static void setDefaultStorageFileProviderUpTo802 ()
  {
    setStorageFileProvider (aMetadata -> {
      final LocalDateTime aNow = PDTFactory.getCurrentLocalDateTime ();
      final String sFilename = StringHelper.getConcatenatedOnDemand (PDTIOHelper.getLocalDateTimeForFilename (aNow),
                                                                     "-",
                                                                     aMetadata.getErrorID ()) +
                               ".xml";
      return WebFileIO.getDataIO ().getFile ("internal-errors/" + aNow.getYear () + "/" + sFilename);
    });
  }
}
