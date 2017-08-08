/**
 * Copyright (C) 2014-2017 Philip Helger (www.helger.com)
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
package com.helger.photon.basic.audit;

import java.io.File;
import java.time.LocalDate;
import java.time.Month;
import java.util.List;
import java.util.function.Consumer;

import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.concurrent.ThreadSafe;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.helger.commons.ValueEnforcer;
import com.helger.commons.annotation.CodingStyleguideUnaware;
import com.helger.commons.annotation.ContainsSoftMigration;
import com.helger.commons.annotation.Nonempty;
import com.helger.commons.annotation.ReturnsMutableCopy;
import com.helger.commons.collection.impl.CommonsArrayList;
import com.helger.commons.collection.impl.ICommonsList;
import com.helger.commons.concurrent.collector.IConcurrentPerformer;
import com.helger.commons.datetime.PDTFactory;
import com.helger.commons.functional.ISupplier;
import com.helger.commons.hashcode.HashCodeGenerator;
import com.helger.commons.io.file.FileSystemIterator;
import com.helger.commons.io.file.FilenameHelper;
import com.helger.commons.io.relative.IFileRelativeIO;
import com.helger.commons.state.EChange;
import com.helger.commons.string.StringHelper;
import com.helger.commons.string.ToStringGenerator;
import com.helger.dao.DAOException;
import com.helger.datetime.util.PDTIOHelper;
import com.helger.photon.basic.app.dao.AbstractPhotonSimpleDAO;
import com.helger.photon.basic.app.io.WebFileIO;
import com.helger.photon.basic.auth.ICurrentUserIDProvider;
import com.helger.xml.microdom.IMicroDocument;
import com.helger.xml.microdom.IMicroElement;
import com.helger.xml.microdom.MicroDocument;
import com.helger.xml.microdom.convert.MicroTypeConverter;
import com.helger.xml.microdom.serialize.MicroReader;

/**
 * The class handles all system audit actions. It collects them asynchronously
 * (see {@link AsynchronousAuditor}) and writes them to XML files on a per-day
 * basis.
 *
 * @author Philip Helger
 */
@ThreadSafe
public class AuditManager extends AbstractPhotonSimpleDAO implements IAuditManager
{
  private static final class AuditHasFilename implements ISupplier <String>
  {
    private final String m_sBaseDir;

    AuditHasFilename (@Nullable final String sBaseDir)
    {
      ValueEnforcer.isTrue (StringHelper.hasNoText (sBaseDir) || FilenameHelper.endsWithPathSeparatorChar (sBaseDir),
                            () -> "BaseDir '" + sBaseDir + "' must end with path separator!");
      m_sBaseDir = sBaseDir;
    }

    @Nullable
    public String get ()
    {
      // No base dir -> in memory only
      if (StringHelper.hasNoText (m_sBaseDir))
        return null;
      return m_sBaseDir + getRelativeAuditFilename (PDTFactory.getCurrentLocalDate ());
    }
  }

  /** Element name of the root element of the serialized XML */
  public static final String ELEMENT_ITEMS = "items";
  /** Element name of each item in the serialized XML */
  public static final String ELEMENT_ITEM = "item";

  private static final Logger s_aLogger = LoggerFactory.getLogger (AuditManager.class);

  private final String m_sBaseDir;
  private final AuditItemList m_aItems = new AuditItemList ();
  private final AsynchronousAuditor m_aAuditor;

  // status vars
  private LocalDate m_aEarliestAuditDate;

  @Nonnull
  @Nonempty
  public static String getRelativeAuditDirectoryYear (@Nonnull final int nYear)
  {
    return nYear + "/";
  }

  @Nonnull
  @Nonempty
  public static String getRelativeAuditDirectory (@Nonnull final LocalDate aDate)
  {
    return getRelativeAuditDirectoryYear (aDate.getYear ()) +
           StringHelper.getLeadingZero (aDate.getMonthValue (), 2) +
           "/";
  }

  @Nonnull
  @Nonempty
  public static String getRelativeAuditFilename (@Nonnull final LocalDate aDate)
  {
    return getRelativeAuditDirectory (aDate) + PDTIOHelper.getDateForFilename (aDate) + ".xml";
  }

  /**
   * Constructor
   *
   * @param sBaseDir
   *        The base directory, relative to the default IO base directory. May
   *        be <code>null</code> to indicate an in-memory auditor only.
   * @param aCurrentUserIDProvider
   *        The current user ID provider. May not be <code>null</code>.
   * @throws DAOException
   *         In case reading failed
   */
  @ContainsSoftMigration
  public AuditManager (@Nullable final String sBaseDir,
                       @Nonnull final ICurrentUserIDProvider aCurrentUserIDProvider) throws DAOException
  {
    super (new AuditHasFilename (sBaseDir));
    ValueEnforcer.notNull (aCurrentUserIDProvider, "CurrentUserIDProvider");

    // Ensure base path is present (if provided)
    m_sBaseDir = sBaseDir;
    if (StringHelper.hasText (sBaseDir))
    {
      final IFileRelativeIO aIO = getIO ();
      aIO.createDirectory (sBaseDir, true);

      // Migrate to new directory structure
      if (!aIO.existsDir (sBaseDir + PDTFactory.getCurrentYear ()))
      {
        s_aLogger.info ("Moving audit files");
        int nCount = 0;
        // Move all files
        for (final File aFile : new FileSystemIterator (aIO.getFile (sBaseDir)))
          if (aFile.isFile () && aFile.getName ().endsWith (".xml"))
          {
            final String sFilename = aFile.getName ();
            final String sDestDir = sBaseDir + sFilename.substring (0, 4) + "/" + sFilename.substring (4, 6);
            aIO.createDirectory (sDestDir, true);
            aIO.renameFile (sBaseDir + sFilename, sDestDir + "/" + sFilename);
            nCount++;
          }
        s_aLogger.info ("Finished moving " + nCount + " files");
      }
    }

    // This is the performer that invoked in a background thread
    final IConcurrentPerformer <List <IAuditItem>> aPerformer = aAuditItems -> {
      if (!aAuditItems.isEmpty ())
      {
        m_aRWLock.writeLocked ( () -> {
          for (final IAuditItem aItem : aAuditItems)
            m_aItems.internalAddItem (aItem);

          // In write-lock - it should be safe anyway since the caller
          // serializes the calls to this method
          markAsChanged ();
        });
      }
    };

    m_aAuditor = new AsynchronousAuditor (aCurrentUserIDProvider, aPerformer);
    initialRead ();
  }

  public boolean isInMemory ()
  {
    return m_sBaseDir == null;
  }

  @Nullable
  public String getBaseDir ()
  {
    return m_sBaseDir;
  }

  @Nonnull
  public AsynchronousAuditor getAuditor ()
  {
    return m_aAuditor;
  }

  public static void readFromXML (@Nonnull final IMicroDocument aDoc,
                                  @Nonnull final Consumer <? super IAuditItem> aHandler)
  {
    ValueEnforcer.notNull (aDoc, "Doc");
    ValueEnforcer.notNull (aHandler, "Handler");

    for (final IMicroElement eItem : aDoc.getDocumentElement ().getAllChildElements (ELEMENT_ITEM))
    {
      final AuditItem aAuditItem = MicroTypeConverter.convertToNative (eItem, AuditItem.class);
      aHandler.accept (aAuditItem);
    }
  }

  @Override
  @Nonnull
  protected EChange onRead (@Nonnull final IMicroDocument aDoc)
  {
    readFromXML (aDoc, aItem -> m_aItems.internalAddItem (aItem));
    // read-only :)
    return EChange.UNCHANGED;
  }

  @Override
  protected IMicroDocument createWriteData ()
  {
    final IMicroDocument aDoc = new MicroDocument ();
    final IMicroElement eRoot = aDoc.appendElement (ELEMENT_ITEMS);
    // Is sorted internally!
    for (final IAuditItem aAuditItem : m_aItems.getAllItems ())
      eRoot.appendChild (MicroTypeConverter.convertToMicroElement (aAuditItem, ELEMENT_ITEM));
    return aDoc;
  }

  @Override
  protected void onFilenameChange (@Nullable final String sPreviousFilename, @Nonnull final String sNewFilename)
  {
    // Called within a write lock
    if (sPreviousFilename != null)
    {
      // Don't update for the first read, as this would remove all previously
      // read items
      m_aItems.internalKeepOnlyLast ();
    }
  }

  @Nonnegative
  public int getAuditItemCount ()
  {
    return m_aRWLock.readLocked (m_aItems::getItemCount);
  }

  @Nonnull
  @ReturnsMutableCopy
  @CodingStyleguideUnaware
  public List <IAuditItem> getLastAuditItems (@Nonnegative final int nMaxItems)
  {
    return m_aRWLock.readLocked ( () -> m_aItems.getLastItems (nMaxItems));
  }

  public void stop ()
  {
    // Do not call in write-lock as this call blocks until the auditor is
    // stopped
    m_aAuditor.stop ();
  }

  @Nullable
  @ReturnsMutableCopy
  public ICommonsList <IAuditItem> getAllAuditItemsOfDate (@Nonnull final LocalDate aDate)
  {
    ValueEnforcer.notNull (aDate, "Date");

    final String sFilename = m_sBaseDir + getRelativeAuditFilename (aDate);
    final File aFile = getIO ().getFile (sFilename);
    if (!aFile.exists ())
      return null;

    final ICommonsList <IAuditItem> ret = new CommonsArrayList <> ();
    final IMicroDocument aDoc = MicroReader.readMicroXML (aFile);
    readFromXML (aDoc, ret::add);
    return ret;
  }

  @Nonnull
  public LocalDate getEarliestAuditDate ()
  {
    if (m_aEarliestAuditDate == null)
    {
      final LocalDate aNow = PDTFactory.getCurrentLocalDate ();
      LocalDate aEarliest = aNow;
      // In in memory only the current data is available
      if (!isInMemory ())
      {
        final IFileRelativeIO aDataIO = WebFileIO.getDataIO ();

        // check for year (from now back)
        int nYear = aEarliest.getYear ();
        while (aDataIO.getFile (m_sBaseDir + getRelativeAuditDirectoryYear (nYear)).isDirectory ())
          --nYear;

        // Undo last "--"
        ++nYear;

        // Find first month
        aEarliest = LocalDate.of (nYear, Month.JANUARY, 1);
        for (final Month eMonth : Month.values ())
        {
          aEarliest = aEarliest.withMonth (eMonth.getValue ());
          if (aDataIO.getFile (m_sBaseDir + getRelativeAuditDirectory (aEarliest)).isDirectory ())
            break;
        }

        // Find first day - looks weird but should work even if an empty
        // directory is present
        while (aEarliest.isBefore (aNow))
        {
          aEarliest = aEarliest.plusDays (1);
          if (aDataIO.getFile (m_sBaseDir + getRelativeAuditFilename (aEarliest)).isFile ())
            break;
        }
      }
      // Cache result
      m_aEarliestAuditDate = aEarliest;
    }
    return m_aEarliestAuditDate;
  }

  @Override
  public boolean equals (final Object o)
  {
    if (o == this)
      return true;
    if (!super.equals (o))
      return false;
    final AuditManager rhs = (AuditManager) o;
    return m_aItems.equals (rhs.m_aItems);
  }

  @Override
  public int hashCode ()
  {
    return HashCodeGenerator.getDerived (super.hashCode ()).append (m_aItems).getHashCode ();
  }

  @Override
  public String toString ()
  {
    return ToStringGenerator.getDerived (super.toString ())
                            .append ("items", m_aItems)
                            .append ("auditor", m_aAuditor)
                            .getToString ();
  }
}
