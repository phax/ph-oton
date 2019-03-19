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
package com.helger.photon.core.interror;

import java.io.File;
import java.time.LocalDateTime;
import java.util.Locale;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.concurrent.GuardedBy;
import javax.annotation.concurrent.ThreadSafe;

import com.helger.commons.CGlobal;
import com.helger.commons.ValueEnforcer;
import com.helger.commons.annotation.ReturnsMutableCopy;
import com.helger.commons.annotation.ReturnsMutableObject;
import com.helger.commons.callback.CallbackList;
import com.helger.commons.collection.impl.ICommonsList;
import com.helger.commons.concurrent.SimpleReadWriteLock;
import com.helger.commons.datetime.PDTFactory;
import com.helger.commons.email.IEmailAddress;
import com.helger.commons.functional.IFunction;
import com.helger.commons.string.StringHelper;
import com.helger.datetime.util.PDTIOHelper;
import com.helger.photon.app.io.WebFileIO;
import com.helger.photon.core.interror.callback.IInternalErrorCallback;
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

  private static final SimpleReadWriteLock s_aRWLock = new SimpleReadWriteLock ();
  @GuardedBy ("s_aRWLock")
  private static final InternalErrorEmailSettings s_aEmailSettings = new InternalErrorEmailSettings ();
  @GuardedBy ("s_aRWLock")
  private static boolean s_bEnableDumpAllThreads = DEFAULT_ENABLE_FULL_THREAD_DUMPS;
  @GuardedBy ("s_aRWLock")
  private static Locale s_aFallbackLocale = CGlobal.DEFAULT_LOCALE;
  private static CallbackList <IInternalErrorCallback> s_aCallbacks = new CallbackList <> ();
  private static IFunction <InternalErrorMetadata, File> s_aStorageFileProvider;

  static
  {
    setDefaultStorageFileProvider ();
  }

  private InternalErrorSettings ()
  {}

  public static void setSMTPSettings (@Nullable final ISMTPSettings aSMTPSettings)
  {
    s_aRWLock.writeLocked ( () -> s_aEmailSettings.setSMTPSettings (aSMTPSettings));
  }

  @Nullable
  public static ISMTPSettings getSMTPSettings ()
  {
    return s_aRWLock.readLocked ( () -> s_aEmailSettings.getSMTPSettings ());
  }

  public static void setSMTPSenderAddress (@Nullable final IEmailAddress aSenderAddress)
  {
    s_aRWLock.writeLocked ( () -> s_aEmailSettings.setSenderAddress (aSenderAddress));
  }

  @Nullable
  public static IEmailAddress getSMTPSenderAddress ()
  {
    return s_aRWLock.readLocked (s_aEmailSettings::getSenderAddress);
  }

  public static void setSMTPReceiverAddress (@Nullable final IEmailAddress aReceiverAddress)
  {
    s_aRWLock.writeLocked ( () -> s_aEmailSettings.setReceiverAddress (aReceiverAddress));
  }

  public static void setSMTPReceiverAddresses (@Nullable final Iterable <? extends IEmailAddress> aReceiverAddresses)
  {
    s_aRWLock.writeLocked ( () -> s_aEmailSettings.setReceiverAddresses (aReceiverAddresses));
  }

  public static void setSMTPReceiverAddresses (@Nullable final IEmailAddress... aReceiverAddresses)
  {
    s_aRWLock.writeLocked ( () -> s_aEmailSettings.setReceiverAddresses (aReceiverAddresses));
  }

  @Nonnull
  @ReturnsMutableCopy
  public static ICommonsList <IEmailAddress> getSMTPReceiverAddresses ()
  {
    return s_aRWLock.readLocked (s_aEmailSettings::getAllReceiverAddresses);
  }

  @Nonnull
  @ReturnsMutableCopy
  public static InternalErrorEmailSettings getCopyOfEmailSettings ()
  {
    return s_aRWLock.readLocked ( () -> s_aEmailSettings.getClone ());
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
    s_aRWLock.writeLocked ( () -> s_bEnableDumpAllThreads = bEnableDumpAllThreads);
  }

  /**
   * @return <code>true</code> to dump all threads on internal error,
   *         <code>false</code> to not do it.
   */
  public static boolean isDumpAllThreads ()
  {
    return s_aRWLock.readLocked ( () -> s_bEnableDumpAllThreads);
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
    s_aRWLock.writeLocked ( () -> s_aFallbackLocale = aFallbackLocale);
  }

  /**
   * @return The fallback locale to use. Never <code>null</code>.
   * @since 7.0.4
   */
  @Nonnull
  public static Locale getFallbackLocale ()
  {
    return s_aRWLock.readLocked ( () -> s_aFallbackLocale);
  }

  /**
   * @return The current custom internal error callbacks. Never <code>null</code>.
   */
  @Nonnull
  @ReturnsMutableObject
  public static CallbackList <IInternalErrorCallback> callbacks ()
  {
    return s_aCallbacks;
  }

  /**
   * @return The provider that defines where to save internal errors to.
   * @since 8.0.3
   */
  @Nonnull
  public static IFunction <InternalErrorMetadata, File> getStorageFileProvider ()
  {
    return s_aStorageFileProvider;
  }

  /**
   * Set the provider that defines how to build the filename to save internal
   * error files.
   *
   * @param aStorageFileProvider
   *        Storage provider. May not be <code>null</code>
   * @since 8.0.3
   */
  public static void setStorageFileProvider (@Nonnull final IFunction <InternalErrorMetadata, File> aStorageFileProvider)
  {
    ValueEnforcer.notNull (aStorageFileProvider, "StorageFileProvider");
    s_aStorageFileProvider = aStorageFileProvider;
  }

  /**
   * Set the default storage file provider. In case you played around and want to
   * restore the default behavior.
   *
   * @see #setStorageFileProvider(IFunction)
   * @since 8.0.3
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
                                sFilename);
    });
  }

  /**
   * Set the storage file provider to the default before v8.0.3. The difference to
   * {@link #setDefaultStorageFileProvider()} is, that this version does not
   * contain a "month" subfolder.
   *
   * @see #setStorageFileProvider(IFunction)
   * @since 8.0.3
   */
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
