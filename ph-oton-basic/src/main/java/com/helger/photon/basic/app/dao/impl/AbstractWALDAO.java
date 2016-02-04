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
package com.helger.photon.basic.app.dao.impl;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.File;
import java.io.OutputStream;
import java.io.Serializable;
import java.time.Clock;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.concurrent.ThreadSafe;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.helger.commons.ValueEnforcer;
import com.helger.commons.annotation.ELockType;
import com.helger.commons.annotation.IsLocked;
import com.helger.commons.annotation.MustBeLocked;
import com.helger.commons.annotation.Nonempty;
import com.helger.commons.annotation.OverrideOnDemand;
import com.helger.commons.collection.CollectionHelper;
import com.helger.commons.io.EAppend;
import com.helger.commons.io.file.FileHelper;
import com.helger.commons.io.file.FileIOError;
import com.helger.commons.io.resource.FileSystemResource;
import com.helger.commons.io.resource.IReadableResource;
import com.helger.commons.io.stream.StreamHelper;
import com.helger.commons.lang.TimeValue;
import com.helger.commons.microdom.IMicroComment;
import com.helger.commons.microdom.IMicroDocument;
import com.helger.commons.microdom.IMicroElement;
import com.helger.commons.microdom.MicroComment;
import com.helger.commons.microdom.convert.MicroTypeConverter;
import com.helger.commons.microdom.serialize.MicroReader;
import com.helger.commons.microdom.serialize.MicroWriter;
import com.helger.commons.state.EChange;
import com.helger.commons.state.ESuccess;
import com.helger.commons.statistics.IMutableStatisticsHandlerCounter;
import com.helger.commons.statistics.IMutableStatisticsHandlerTimer;
import com.helger.commons.statistics.StatisticsManager;
import com.helger.commons.string.ToStringGenerator;
import com.helger.commons.timing.StopWatch;
import com.helger.commons.xml.serialize.write.IXMLWriterSettings;
import com.helger.commons.xml.serialize.write.XMLWriterSettings;
import com.helger.datetime.format.PDTToString;
import com.helger.photon.basic.app.dao.IDAOIO;
import com.helger.photon.basic.app.dao.IDAOReadExceptionCallback;
import com.helger.photon.basic.app.dao.IDAOWriteExceptionCallback;
import com.helger.photon.basic.app.io.ConstantHasFilename;
import com.helger.photon.basic.app.io.IHasFilename;
import com.helger.photon.basic.audit.AuditHelper;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;

/**
 * Base class for a simple DAO using write ahead logging (WAL).
 *
 * @author Philip Helger
 * @param <DATATYPE>
 *        The data type to be serialized
 */
@ThreadSafe
public abstract class AbstractWALDAO <DATATYPE extends Serializable> extends AbstractDAO
{
  public static final TimeValue DEFAULT_WAITING_TIME = new TimeValue (TimeUnit.SECONDS, 10);
  private static final Logger s_aLogger = LoggerFactory.getLogger (AbstractWALDAO.class);

  private final IMutableStatisticsHandlerCounter m_aStatsCounterInitTotal = StatisticsManager.getCounterHandler (getClass ().getName () +
                                                                                                                 "$init-total");
  private final IMutableStatisticsHandlerCounter m_aStatsCounterInitSuccess = StatisticsManager.getCounterHandler (getClass ().getName () +
                                                                                                                   "$init-success");
  private final IMutableStatisticsHandlerTimer m_aStatsCounterInitTimer = StatisticsManager.getTimerHandler (getClass ().getName () +
                                                                                                             "$init");
  private final IMutableStatisticsHandlerCounter m_aStatsCounterReadTotal = StatisticsManager.getCounterHandler (getClass ().getName () +
                                                                                                                 "$read-total");
  private final IMutableStatisticsHandlerCounter m_aStatsCounterReadSuccess = StatisticsManager.getCounterHandler (getClass ().getName () +
                                                                                                                   "$read-success");
  private final IMutableStatisticsHandlerTimer m_aStatsCounterReadTimer = StatisticsManager.getTimerHandler (getClass ().getName () +
                                                                                                             "$read");
  private final IMutableStatisticsHandlerCounter m_aStatsCounterWriteTotal = StatisticsManager.getCounterHandler (getClass ().getName () +
                                                                                                                  "$write-total");
  private final IMutableStatisticsHandlerCounter m_aStatsCounterWriteSuccess = StatisticsManager.getCounterHandler (getClass ().getName () +
                                                                                                                    "$write-success");
  private final IMutableStatisticsHandlerCounter m_aStatsCounterWriteExceptions = StatisticsManager.getCounterHandler (getClass ().getName () +
                                                                                                                       "$write-exceptions");
  private final IMutableStatisticsHandlerTimer m_aStatsCounterWriteTimer = StatisticsManager.getTimerHandler (getClass ().getName () +
                                                                                                              "$write");

  private Class <DATATYPE> m_aDataTypeClass;
  private final IDAOIO m_aDAOIO;
  private final IHasFilename m_aFilenameProvider;
  private String m_sPreviousFilename;
  private int m_nInitCount = 0;
  private LocalDateTime m_aLastInitDT;
  private int m_nReadCount = 0;
  private LocalDateTime m_aLastReadDT;
  private int m_nWriteCount = 0;
  private LocalDateTime m_aLastWriteDT;
  private boolean m_bCanWriteWAL = true;
  private TimeValue m_aWaitingTime = DEFAULT_WAITING_TIME;

  // Status vars
  private WALListener m_aWALListener;

  protected AbstractWALDAO (@Nonnull final Class <DATATYPE> aDataTypeClass, @Nullable final String sFilename)
  {
    this (aDataTypeClass, new ConstantHasFilename (sFilename));
  }

  protected AbstractWALDAO (@Nonnull final Class <DATATYPE> aDataTypeClass,
                            @Nonnull final IHasFilename aFilenameProvider)
  {
    this (aDataTypeClass, DAOWebFileIO.getInstance (), aFilenameProvider);
  }

  protected AbstractWALDAO (@Nonnull final Class <DATATYPE> aDataTypeClass,
                            @Nonnull final IDAOIO aDAOIO,
                            @Nullable final String sFilename)
  {
    this (aDataTypeClass, aDAOIO, new ConstantHasFilename (sFilename));
  }

  protected AbstractWALDAO (@Nonnull final Class <DATATYPE> aDataTypeClass,
                            @Nonnull final IDAOIO aDAOIO,
                            @Nonnull final IHasFilename aFilenameProvider)
  {
    m_aDataTypeClass = ValueEnforcer.notNull (aDataTypeClass, "DataTypeClass");
    m_aDAOIO = ValueEnforcer.notNull (aDAOIO, "DAOIO");
    m_aFilenameProvider = ValueEnforcer.notNull (aFilenameProvider, "FilenameProvider");

    // Remember instance in case it is trigger upon shutdown
    m_aWALListener = WALListener.getInstance ();
  }

  /**
   * @return The DAO IO object used by this instance.
   */
  @Nonnull
  public final IDAOIO getDAOIO ()
  {
    return m_aDAOIO;
  }

  /**
   * @return The filename provider used internally to build filenames. Never
   *         <code>null</code>.
   */
  @Nonnull
  public final IHasFilename getFilenameProvider ()
  {
    return m_aFilenameProvider;
  }

  /**
   * Custom initialization routine. Called only if the underlying file does not
   * exist yet. This method is only called within a write lock!
   *
   * @return {@link EChange#CHANGED} if something was modified inside this
   *         method
   */
  @Nonnull
  @OverrideOnDemand
  protected EChange onInit ()
  {
    return EChange.UNCHANGED;
  }

  /**
   * Fill the internal structures with from the passed XML document. This method
   * is only called within a write lock!
   *
   * @param aDoc
   *        The XML document to read from. Never <code>null</code>.
   * @return {@link EChange#CHANGED} if reading the data changed something in
   *         the internal structures that requires a writing.
   */
  @Nonnull
  @MustBeLocked (ELockType.WRITE)
  protected abstract EChange onRead (@Nonnull IMicroDocument aDoc);

  @Nonnull
  protected final File getSafeFile (@Nonnull final String sFilename, @Nonnull final EMode eMode) throws DAOException
  {
    ValueEnforcer.notNull (sFilename, "Filename");
    ValueEnforcer.notNull (eMode, "Mode");

    final File aFile = m_aDAOIO.getFileIO ().getFile (sFilename);
    if (aFile.exists ())
    {
      // file exist -> must be a file!
      if (!aFile.isFile ())
        throw new DAOException ("The passed filename '" +
                                sFilename +
                                "' is not a file - maybe a directory? Path is '" +
                                aFile.getAbsolutePath () +
                                "'");

      switch (eMode)
      {
        case READ:
          // Check for read-rights
          if (!FileHelper.canRead (aFile))
            throw new DAOException ("The DAO of class " +
                                    getClass ().getName () +
                                    " has no access rights to read from '" +
                                    aFile.getAbsolutePath () +
                                    "'");
          break;
        case WRITE:
          // Check for write-rights
          if (!FileHelper.canWrite (aFile))
            throw new DAOException ("The DAO of class " +
                                    getClass ().getName () +
                                    " has no access rights to write to '" +
                                    aFile.getAbsolutePath () +
                                    "'");
          break;
      }
    }
    else
    {
      // Ensure the parent directory is present
      final File aParentDir = aFile.getParentFile ();
      if (aParentDir != null)
      {
        final FileIOError aError = m_aDAOIO.getFileOperationMgr ().createDirRecursiveIfNotExisting (aParentDir);
        if (aError.isFailure ())
          throw new DAOException ("The DAO of class " +
                                  getClass ().getName () +
                                  " failed to create parent directory '" +
                                  aParentDir +
                                  "': " +
                                  aError);
      }
    }

    return aFile;
  }

  /**
   * Trigger the registered custom exception handlers for read errors.
   *
   * @param t
   *        Thrown exception. Never <code>null</code>.
   * @param bIsInitialization
   *        <code>true</code> if this happened during initialization of a new
   *        file, <code>false</code> if it happened during regular reading.
   * @param aFile
   *        The file that was read. May be <code>null</code> for in-memory DAOs.
   */
  protected static void triggerExceptionHandlersRead (@Nonnull final Throwable t,
                                                      final boolean bIsInitialization,
                                                      @Nullable final File aFile)
  {
    // Custom exception handler for reading
    if (getExceptionHandlersRead ().hasCallbacks ())
    {
      final IReadableResource aRes = aFile == null ? null : new FileSystemResource (aFile);
      for (final IDAOReadExceptionCallback aExceptionHandlerRead : getExceptionHandlersRead ().getAllCallbacks ())
      {
        try
        {
          aExceptionHandlerRead.onDAOReadException (t, bIsInitialization, aRes);
        }
        catch (final Throwable t2)
        {
          s_aLogger.error ("Error in custom exception handler for reading " +
                           (aRes == null ? "memory-only" : aRes.toString ()),
                           t2);
        }
      }
    }
  }

  /**
   * @return The implementation class as specified in the constructor. Never
   *         <code>null</code>.
   */
  @Nonnull
  protected final Class <DATATYPE> getDataTypeClass ()
  {
    return m_aDataTypeClass;
  }

  /**
   * This method is used upon recovery to convert a stored object to its native
   * representation. If you overwrite this method, you should consider
   * overriding {@link #convertToString(Serializable)} as well.
   *
   * @param sElement
   *        The string representation to be converted. Never <code>null</code>.
   * @return The native representation of the object. If the return value is
   *         <code>null</code>, the recovery will fail with an exception!
   */
  @Nullable
  @OverrideOnDemand
  @IsLocked (ELockType.WRITE)
  protected DATATYPE convertToNative (@Nonnull final String sElement)
  {
    final IMicroDocument aDoc = MicroReader.readMicroXML (sElement);
    if (aDoc != null && aDoc.getDocumentElement () != null)
      return MicroTypeConverter.convertToNative (aDoc.getDocumentElement (), m_aDataTypeClass);
    return null;
  }

  /**
   * Called when a recovery is needed to create a new item.
   *
   * @param aElement
   *        The element to be created. Never <code>null</code>.
   */
  @IsLocked (ELockType.WRITE)
  protected abstract void onRecoveryCreate (@Nonnull DATATYPE aElement);

  /**
   * Called when a recovery is needed to update an existing item.
   *
   * @param aElement
   *        The element to be updated. Never <code>null</code>.
   */
  @IsLocked (ELockType.WRITE)
  protected abstract void onRecoveryUpdate (@Nonnull DATATYPE aElement);

  /**
   * Called when a recovery is needed to delete an existing item.
   *
   * @param aElement
   *        The element to be deleted. Never <code>null</code>.
   */
  @IsLocked (ELockType.WRITE)
  protected abstract void onRecoveryDelete (@Nonnull DATATYPE aElement);

  /**
   * Call this method inside the constructor to read the file contents directly.
   * This method is write locked!
   *
   * @throws DAOException
   *         in case initialization or reading failed!
   */
  protected final void initialRead () throws DAOException
  {
    File aFile = null;
    final String sFilename = m_aFilenameProvider.getFilename ();
    if (sFilename == null)
    {
      // required for testing
      s_aLogger.warn ("This DAO of class " + getClass ().getName () + " will not be able to read from a file");

      // do not return - run initialization anyway
    }
    else
    {
      // Check consistency
      aFile = getSafeFile (sFilename, EMode.READ);
    }

    final boolean bIsInitialization = aFile == null || !aFile.exists ();
    final File aFinalFile = aFile;

    m_aRWLock.writeLockedThrowing ( () -> {
      try
      {
        m_bCanWriteWAL = false;

        try
        {
          ESuccess eWriteSuccess = ESuccess.SUCCESS;
          if (bIsInitialization)
          {
            // initial setup for non-existing file
            if (isDebugLogging ())
              s_aLogger.debug ("Trying to initialize DAO XML file '" + aFinalFile.getAbsolutePath () + "'");

            beginWithoutAutoSave ();
            try
            {
              m_aStatsCounterInitTotal.increment ();
              final StopWatch aSW = StopWatch.createdStarted ();

              if (onInit ().isChanged ())
                if (aFinalFile != null)
                  eWriteSuccess = _writeToFile ();

              m_aStatsCounterInitTimer.addTime (aSW.stopAndGetMillis ());
              m_aStatsCounterInitSuccess.increment ();
              m_nInitCount++;
              m_aLastInitDT = LocalDateTime.now ();
            }
            finally
            {
              endWithoutAutoSave ();
              // reset any pending changes, because the initialization should
              // be read-only. If the implementing class changed something,
              // the return value of onInit() is what counts
              internalSetPendingChanges (false);
            }
          }
          else
          {
            // Read existing file
            if (isDebugLogging ())
              s_aLogger.debug ("Trying to read DAO XML file '" + aFinalFile.getAbsolutePath () + "'");

            m_aStatsCounterReadTotal.increment ();
            final IMicroDocument aDoc = MicroReader.readMicroXML (aFinalFile);
            if (aDoc == null)
              s_aLogger.error ("Failed to read DAO XML document from file '" + aFinalFile.getAbsolutePath () + "'");
            else
            {
              // Valid XML - start interpreting
              beginWithoutAutoSave ();
              try
              {
                final StopWatch aSW = StopWatch.createdStarted ();

                if (onRead (aDoc).isChanged ())
                  eWriteSuccess = _writeToFile ();

                m_aStatsCounterReadTimer.addTime (aSW.stopAndGetMillis ());
                m_aStatsCounterReadSuccess.increment ();
                m_nReadCount++;
                m_aLastReadDT = LocalDateTime.now ();
              }
              finally
              {
                endWithoutAutoSave ();
                // reset any pending changes, because the initialization should
                // be read-only. If the implementing class changed something,
                // the return value of onRead() is what counts
                internalSetPendingChanges (false);
              }
            }
          }

          // Check if writing was successful on any of the 2 branches
          if (eWriteSuccess.isSuccess ())
          {
            // Reset any pending changes, since the changes were already saved
            internalSetPendingChanges (false);
          }
          else
          {
            // There is something wrong
            s_aLogger.error ("File '" + aFinalFile.getAbsolutePath () + "' has pending changes after initialRead!");
          }
        }
        catch (final Throwable t)
        {
          triggerExceptionHandlersRead (t, bIsInitialization, aFinalFile);
          throw new DAOException ("Error " +
                                  (bIsInitialization ? "initializing" : "reading") +
                                  " the file '" +
                                  aFinalFile.getAbsolutePath () +
                                  "'",
                                  t);
        }

        // Check if there is anything to recover
        final String sWALFilename = _getWALFilename ();
        final File aWALFile = sWALFilename == null ? null : m_aDAOIO.getFileIO ().getFile (sWALFilename);
        if (aWALFile != null && aWALFile.exists ())
        {
          s_aLogger.info ("Trying to recover from WAL file " + aWALFile.getAbsolutePath ());
          DataInputStream aOIS = null;
          boolean bFinishedSuccessful = false;
          boolean bPerformedAtLeastOnRecovery = false;

          // Avoid writing the recovery actions to the WAL file again :)
          try
          {
            aOIS = new DataInputStream (FileHelper.getInputStream (aWALFile));
            while (true)
            {
              // Read action type
              String sActionType;
              try
              {
                sActionType = StreamHelper.readSafeUTF (aOIS);
              }
              catch (final EOFException ex)
              {
                break;
              }
              final EDAOActionType eActionType = EDAOActionType.getFromIDOrThrow (sActionType);
              // Read number of elements
              final int nElements = aOIS.readInt ();
              // Read all elements
              for (int i = 0; i < nElements; ++i)
              {
                final String sElement = StreamHelper.readSafeUTF (aOIS);
                final DATATYPE aElement = convertToNative (sElement);
                if (aElement == null)
                  throw new IllegalStateException ("Action [" +
                                                   eActionType +
                                                   "][" +
                                                   i +
                                                   "]: failed to convert the following element to native:\n" +
                                                   sElement);
                switch (eActionType)
                {
                  case CREATE:
                    try
                    {
                      onRecoveryCreate (aElement);
                      bPerformedAtLeastOnRecovery = true;
                      AuditHelper.onAuditExecuteSuccess ("wal-recovery", "create", aElement);
                    }
                    catch (final RuntimeException ex)
                    {
                      AuditHelper.onAuditExecuteFailure ("wal-recovery", "create", aElement, ex);
                      throw ex;
                    }
                    break;
                  case UPDATE:
                    try
                    {
                      onRecoveryUpdate (aElement);
                      bPerformedAtLeastOnRecovery = true;
                      AuditHelper.onAuditExecuteSuccess ("wal-recovery", "update", aElement);
                      break;
                    }
                    catch (final RuntimeException ex)
                    {
                      AuditHelper.onAuditExecuteFailure ("wal-recovery", "update", aElement, ex);
                      throw ex;
                    }
                  case DELETE:
                    try
                    {
                      onRecoveryDelete (aElement);
                      bPerformedAtLeastOnRecovery = true;
                      AuditHelper.onAuditExecuteSuccess ("wal-recovery", "delete", aElement);
                      break;
                    }
                    catch (final RuntimeException ex)
                    {
                      AuditHelper.onAuditExecuteFailure ("wal-recovery", "delete", aElement, ex);
                      throw ex;
                    }
                  default:
                    throw new IllegalStateException ("Unsupported action type provided: " + eActionType);
                }
              }
            }
            bFinishedSuccessful = true;
            s_aLogger.info ("Successfully finished recovery from WAL file " + aWALFile.getAbsolutePath ());
          }
          catch (final Throwable t)
          {
            s_aLogger.error ("Failed to recover from WAL file " + aWALFile.getAbsolutePath (), t);
            triggerExceptionHandlersRead (t, false, aWALFile);
            throw new DAOException ("Error the WAL file '" + aWALFile.getAbsolutePath () + "'", t);
          }
          finally
          {
            StreamHelper.close (aOIS);
          }

          if (bFinishedSuccessful)
          {
            // Finished recovery successfully
            // Perform the remaining actions AFTER the WAL input stream was
            // closed!
            if (bPerformedAtLeastOnRecovery)
            {
              // Write the file without using WAL
              _writeToFileAndResetPendingChanges ("onRecovery");
            }

            // Finally delete the WAL file, as the recovery has finished
            _deleteWALFile (sWALFilename);
          }
        }
      }
      finally
      {
        // Now a WAL file can be written again
        m_bCanWriteWAL = true;
      }
    });
  }

  /**
   * Called after a successful write of the file, if the filename is different
   * from the previous filename. This can e.g. be used to clear old data.
   *
   * @param sPreviousFilename
   *        The previous filename. May be <code>null</code>.
   * @param sNewFilename
   *        The new filename. Never <code>null</code>.
   */
  @OverrideOnDemand
  @MustBeLocked (ELockType.WRITE)
  protected void onFilenameChange (@Nullable final String sPreviousFilename, @Nonnull final String sNewFilename)
  {}

  /**
   * Create the XML document that should be saved to the file. This method is
   * only called within a write lock!
   *
   * @return The non-<code>null</code> document to write to the file.
   */
  @Nonnull
  @MustBeLocked (ELockType.WRITE)
  protected abstract IMicroDocument createWriteData ();

  /**
   * Modify the created document by e.g. adding some comment or digital
   * signature or whatsoever.
   *
   * @param aDoc
   *        The created non-<code>null</code> document.
   */
  @OverrideOnDemand
  @MustBeLocked (ELockType.WRITE)
  protected void modifyWriteData (@Nonnull final IMicroDocument aDoc)
  {
    final IMicroComment aComment = new MicroComment ("This file was generated automatically - do NOT modify!\n" +
                                                     "Written at " +
                                                     PDTToString.getAsString (ZonedDateTime.now (Clock.systemUTC ()),
                                                                              Locale.US));
    final IMicroElement eRoot = aDoc.getDocumentElement ();
    // Add a small comment
    if (eRoot != null)
      aDoc.insertBefore (aComment, eRoot);
    else
      aDoc.appendChild (aComment);
  }

  /**
   * Optional callback method that is invoked before the file handle gets
   * opened. This method can e.g. be used to create backups.
   *
   * @param sFilename
   *        The filename provided by the internal filename provider. Never
   *        <code>null</code>.
   * @param aFile
   *        The resolved file. It is already consistency checked. Never
   *        <code>null</code>.
   */
  @OverrideOnDemand
  @MustBeLocked (ELockType.WRITE)
  protected void beforeWriteToFile (@Nonnull final String sFilename, @Nonnull final File aFile)
  {}

  /**
   * @return The {@link IXMLWriterSettings} to be used to serialize the data.
   */
  @Nonnull
  @OverrideOnDemand
  protected IXMLWriterSettings getXMLWriterSettings ()
  {
    return XMLWriterSettings.DEFAULT_XML_SETTINGS;
  }

  /**
   * @return The filename to which was written last. May be <code>null</code> if
   *         no wrote action was performed yet.
   */
  @Nullable
  public final String getLastFilename ()
  {
    return m_aRWLock.readLocked ( () -> m_sPreviousFilename);
  }

  /**
   * Trigger the registered custom exception handlers for read errors.
   *
   * @param t
   *        Thrown exception. Never <code>null</code>.
   * @param sErrorFilename
   *        The filename tried to write to. Never <code>null</code>.
   * @param aDoc
   *        The XML content that should be written. May be <code>null</code> if
   *        the error occurred in XML creation.
   */
  protected static void triggerExceptionHandlersWrite (@Nonnull final Throwable t,
                                                       @Nonnull final String sErrorFilename,
                                                       @Nullable final IMicroDocument aDoc)
  {
    // Check if a custom exception handler is present
    if (getExceptionHandlersWrite ().hasCallbacks ())
    {
      final IReadableResource aRes = new FileSystemResource (sErrorFilename);
      final String sXMLContent = aDoc == null ? "no XML document created" : MicroWriter.getXMLString (aDoc);
      for (final IDAOWriteExceptionCallback aExceptionHandlerWrite : getExceptionHandlersWrite ().getAllCallbacks ())
      {
        try
        {
          aExceptionHandlerWrite.onDAOWriteException (t, aRes, sXMLContent);
        }
        catch (final Throwable t2)
        {
          s_aLogger.error ("Error in custom exception handler for writing " + aRes.toString (), t2);
        }
      }
    }
  }

  /**
   * The main method for writing the new data to a file. This method may only be
   * called within a write lock!
   *
   * @return {@link ESuccess} and never <code>null</code>.
   */
  @Nonnull
  @SuppressFBWarnings ("RCN_REDUNDANT_NULLCHECK_OF_NONNULL_VALUE")
  @MustBeLocked (ELockType.WRITE)
  private ESuccess _writeToFile ()
  {
    // Build the filename to write to
    final String sFilename = m_aFilenameProvider.getFilename ();
    if (sFilename == null)
    {
      // We're not operating on a file! Required for testing
      s_aLogger.warn ("The DAO of class " + getClass ().getName () + " cannot write to a file");
      return ESuccess.FAILURE;
    }

    // Check for a filename change before writing
    if (!sFilename.equals (m_sPreviousFilename))
    {
      onFilenameChange (m_sPreviousFilename, sFilename);
      m_sPreviousFilename = sFilename;
    }

    if (isDebugLogging ())
      s_aLogger.info ("Trying to write DAO file '" + sFilename + "'");

    File aFile = null;
    IMicroDocument aDoc = null;
    try
    {
      // Get the file handle
      aFile = getSafeFile (sFilename, EMode.WRITE);

      m_aStatsCounterWriteTotal.increment ();
      final StopWatch aSW = StopWatch.createdStarted ();

      // Create XML document to write
      aDoc = createWriteData ();
      if (aDoc == null)
        throw new DAOException ("Failed to create data to write to file");

      // Generic modification
      modifyWriteData (aDoc);

      // Perform optional stuff like backup etc. Must be done BEFORE the output
      // stream is opened!
      beforeWriteToFile (sFilename, aFile);

      // Get the output stream
      final OutputStream aOS = FileHelper.getOutputStream (aFile);
      if (aOS == null)
      {
        // Happens, when another application has the file open!
        // Logger warning already emitted
        throw new DAOException ("Failed to open output stream for '" + aFile.getAbsolutePath () + "'");
      }

      // Write to file (closes the OS)
      final IXMLWriterSettings aXWS = getXMLWriterSettings ();
      if (MicroWriter.writeToStream (aDoc, aOS, aXWS).isFailure ())
        throw new DAOException ("Failed to write DAO XML data to file");

      m_aStatsCounterWriteTimer.addTime (aSW.stopAndGetMillis ());
      m_aStatsCounterWriteSuccess.increment ();
      m_nWriteCount++;
      m_aLastWriteDT = LocalDateTime.now ();
      return ESuccess.SUCCESS;
    }
    catch (final Throwable t)
    {
      final String sErrorFilename = aFile != null ? aFile.getAbsolutePath () : sFilename;

      s_aLogger.error ("The DAO of class " +
                       getClass ().getName () +
                       " failed to write the DAO data to '" +
                       sErrorFilename +
                       "'",
                       t);

      triggerExceptionHandlersWrite (t, sErrorFilename, aDoc);
      m_aStatsCounterWriteExceptions.increment ();
      return ESuccess.FAILURE;
    }
  }

  @MustBeLocked (ELockType.WRITE)
  final void _writeToFileAndResetPendingChanges (@Nonnull final String sCallingMethodName)
  {
    if (_writeToFile ().isSuccess ())
      internalSetPendingChanges (false);
    else
    {
      s_aLogger.error ("The DAO of class " +
                       getClass ().getName () +
                       " still has pending changes after " +
                       sCallingMethodName +
                       "!");
    }
  }

  /**
   * @return The name of the WAL file of this DAO or <code>null</code> if this
   *         DAO does not support WAL files.
   */
  @Nullable
  private String _getWALFilename ()
  {
    final String sWALFilename = m_aFilenameProvider.getFilename ();
    if (sWALFilename == null)
      return null;
    return sWALFilename + ".wal";
  }

  /**
   * This method may only be triggered with valid WAL filenames, as the passed
   * file is deleted!
   */
  final void _deleteWALFile (@Nonnull @Nonempty final String sWALFilename)
  {
    ValueEnforcer.notEmpty (sWALFilename, "WALFilename");
    final File aWALFile = m_aDAOIO.getFileIO ().getFile (sWALFilename);
    if (m_aDAOIO.getFileOperationMgr ().deleteFile (aWALFile).isFailure ())
      s_aLogger.error ("Failed to delete WAL file " + aWALFile.getAbsolutePath ());
  }

  @Nonnull
  @OverrideOnDemand
  protected String convertToString (@Nonnull final DATATYPE aModifiedElement)
  {
    final IMicroElement aElement = MicroTypeConverter.convertToMicroElement (aModifiedElement, "item");
    if (aElement == null)
      throw new IllegalStateException ("Failed to convert " +
                                       aModifiedElement +
                                       " of class " +
                                       aModifiedElement.getClass ().getName () +
                                       " to XML!");
    return MicroWriter.getXMLString (aElement);
  }

  @Nonnull
  @MustBeLocked (ELockType.WRITE)
  private ESuccess _writeWALFile (@Nonnull @Nonempty final List <DATATYPE> aModifiedElements,
                                  @Nonnull final EDAOActionType eActionType,
                                  @Nonnull @Nonempty final String sWALFilename)
  {
    DataOutputStream aDOS = null;
    try
    {
      aDOS = new DataOutputStream (m_aDAOIO.getFileIO ().getOutputStream (sWALFilename, EAppend.APPEND));
      // Write action type ID
      StreamHelper.writeSafeUTF (aDOS, eActionType.getID ());
      // Write number of elements
      aDOS.writeInt (aModifiedElements.size ());
      // Write all data elements as XML Strings :)
      for (final DATATYPE aModifiedElement : aModifiedElements)
      {
        final String sElement = convertToString (aModifiedElement);
        StreamHelper.writeSafeUTF (aDOS, sElement);
      }
      return ESuccess.SUCCESS;
    }
    catch (final Throwable t)
    {
      s_aLogger.error ("Error writing WAL file " + sWALFilename, t);
      triggerExceptionHandlersWrite (t, sWALFilename, (IMicroDocument) null);
    }
    finally
    {
      StreamHelper.close (aDOS);
    }
    return ESuccess.FAILURE;
  }

  protected final void markAsCreated (@Nonnull final DATATYPE aModifiedElement)
  {
    markAsChanged (aModifiedElement, EDAOActionType.CREATE);
  }

  protected final void markAsUpdated (@Nonnull final DATATYPE aModifiedElement)
  {
    markAsChanged (aModifiedElement, EDAOActionType.UPDATE);
  }

  protected final void markAsDeleted (@Nonnull final DATATYPE aModifiedElement)
  {
    markAsChanged (aModifiedElement, EDAOActionType.DELETE);
  }

  /**
   * This method must be called every time something changed in the DAO. It
   * triggers the writing to a file if auto-save is active. This method must be
   * called within a write-lock as it is not locked!
   *
   * @param aModifiedElement
   *        The modified data element. May not be <code>null</code>.
   * @param eActionType
   *        The action that was performed. May not be <code>null</code>.
   */
  @MustBeLocked (ELockType.WRITE)
  protected final void markAsChanged (@Nonnull final DATATYPE aModifiedElement,
                                      @Nonnull final EDAOActionType eActionType)
  {
    ValueEnforcer.notNull (aModifiedElement, "ModifiedElement");
    ValueEnforcer.notNull (eActionType, "ActionType");

    // Convert single item to list
    markAsChanged (CollectionHelper.newList (aModifiedElement), eActionType);
  }

  /**
   * @return The waiting time used before the file is effectively written. Never
   *         <code>null</code>. Default value is 10 seconds.
   */
  @Nonnull
  public TimeValue getWaitingTime ()
  {
    return m_aWaitingTime;
  }

  /**
   * Set the waiting time used before the file is effectively written. Default
   * value is 10 seconds. Setting the value to a duration of 0 means that the
   * write ahead is effectively disabled.
   *
   * @param aWaitingTime
   *        The waiting time to be used. May not be <code>null</code>.
   */
  protected void setWaitingTime (@Nonnull final TimeValue aWaitingTime)
  {
    ValueEnforcer.notNull (aWaitingTime, "WaitingTime");
    m_aWaitingTime = aWaitingTime;
  }

  @MustBeLocked (ELockType.WRITE)
  protected final void markAsChanged (@Nonnull final List <DATATYPE> aModifiedElements,
                                      @Nonnull final EDAOActionType eActionType)
  {
    ValueEnforcer.notNull (aModifiedElements, "ModifiedElements");
    ValueEnforcer.notNull (eActionType, "ActionType");

    // Just remember that something changed
    internalSetPendingChanges (true);
    if (internalIsAutoSaveEnabled ())
    {
      // Auto save

      // Write a WAL file
      final String sWALFilename = _getWALFilename ();
      // Note: writing a WAL is forbidden when a WAL file is recovered upon
      // startup!
      // Note: writing a WAL makes no sense, if the waiting time is zero
      if (m_bCanWriteWAL &&
          m_aWaitingTime.getDuration () > 0 &&
          sWALFilename != null &&
          _writeWALFile (aModifiedElements, eActionType, sWALFilename).isSuccess ())
      {
        // Remember change for later writing
        // Note: pass the WAL filename in case the filename changes over time!
        m_aWALListener.registerForLaterWriting (this, sWALFilename, m_aWaitingTime);
      }
      else
      {
        // write directly
        _writeToFileAndResetPendingChanges ("markAsChanged(" + eActionType.getID () + ")");
      }
    }
  }

  /**
   * In case there are pending changes write them to the file. This method is
   * write locked!
   */
  public final void writeToFileOnPendingChanges ()
  {
    if (hasPendingChanges ())
    {
      m_aRWLock.writeLocked ( () -> {
        // Write to file
        _writeToFileAndResetPendingChanges ("writeToFileOnPendingChanges");
      });
    }
  }

  @Nonnegative
  public int getInitCount ()
  {
    return m_nInitCount;
  }

  @Nullable
  public final LocalDateTime getLastInitDateTime ()
  {
    return m_aLastInitDT;
  }

  @Nonnegative
  public int getReadCount ()
  {
    return m_nReadCount;
  }

  @Nullable
  public final LocalDateTime getLastReadDateTime ()
  {
    return m_aLastReadDT;
  }

  @Nonnegative
  public int getWriteCount ()
  {
    return m_nWriteCount;
  }

  @Nullable
  public final LocalDateTime getLastWriteDateTime ()
  {
    return m_aLastWriteDT;
  }

  @Override
  public String toString ()
  {
    return ToStringGenerator.getDerived (super.toString ())
                            .append ("filenameProvider", m_aFilenameProvider)
                            .append ("previousFilename", m_sPreviousFilename)
                            .append ("initCount", m_nInitCount)
                            .appendIfNotNull ("lastInitDT", m_aLastInitDT)
                            .append ("readCount", m_nReadCount)
                            .appendIfNotNull ("lastReadDT", m_aLastReadDT)
                            .append ("writeCount", m_nWriteCount)
                            .appendIfNotNull ("lastWriteDT", m_aLastWriteDT)
                            .toString ();
  }
}
