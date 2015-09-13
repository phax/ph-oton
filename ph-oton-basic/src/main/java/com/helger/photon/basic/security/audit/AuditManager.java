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

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.concurrent.ThreadSafe;

import org.joda.time.DateTimeConstants;
import org.joda.time.LocalDate;
import org.joda.time.LocalDateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.helger.commons.ValueEnforcer;
import com.helger.commons.annotation.Nonempty;
import com.helger.commons.annotation.ReturnsMutableCopy;
import com.helger.commons.callback.IThrowingRunnableWithParameter;
import com.helger.commons.equals.EqualsHelper;
import com.helger.commons.hashcode.HashCodeGenerator;
import com.helger.commons.io.file.FilenameHelper;
import com.helger.commons.io.file.iterate.FileSystemIterator;
import com.helger.commons.microdom.IMicroDocument;
import com.helger.commons.microdom.IMicroElement;
import com.helger.commons.microdom.MicroDocument;
import com.helger.commons.microdom.serialize.MicroReader;
import com.helger.commons.state.EChange;
import com.helger.commons.state.ESuccess;
import com.helger.commons.string.StringHelper;
import com.helger.commons.string.StringParser;
import com.helger.commons.string.ToStringGenerator;
import com.helger.datetime.PDTFactory;
import com.helger.datetime.util.PDTIOHelper;
import com.helger.photon.basic.app.dao.impl.AbstractSimpleDAO;
import com.helger.photon.basic.app.dao.impl.DAOException;
import com.helger.photon.basic.app.io.IHasFilename;
import com.helger.photon.basic.app.io.IMutablePathRelativeIO;
import com.helger.photon.basic.app.io.IPathRelativeIO;
import com.helger.photon.basic.app.io.WebFileIO;
import com.helger.photon.basic.security.login.ICurrentUserIDProvider;

/**
 * The class handles all audit items
 *
 * @author Philip Helger
 */
@ThreadSafe
public final class AuditManager extends AbstractSimpleDAO implements IAuditManager
{
  private static final class AuditHasFilename implements IHasFilename
  {
    private final String m_sBaseDir;

    AuditHasFilename (@Nullable final String sBaseDir)
    {
      if (StringHelper.hasText (sBaseDir))
        ValueEnforcer.isTrue (FilenameHelper.endsWithPathSeparatorChar (sBaseDir),
                              "BaseDir must end with path separator!");
      m_sBaseDir = sBaseDir;
    }

    @Nullable
    public String getFilename ()
    {
      // No base dir -> in memory only
      if (StringHelper.hasNoText (m_sBaseDir))
        return null;
      return m_sBaseDir + getRelativeAuditFilename (PDTFactory.getCurrentLocalDate ());
    }

    @Override
    public boolean equals (final Object o)
    {
      if (o == this)
        return true;
      if (o == null || !getClass ().equals (o.getClass ()))
        return false;
      final AuditHasFilename rhs = (AuditHasFilename) o;
      return EqualsHelper.equals (m_sBaseDir, rhs.m_sBaseDir);
    }

    @Override
    public int hashCode ()
    {
      return new HashCodeGenerator (this).append (m_sBaseDir).getHashCode ();
    }

    @Override
    public String toString ()
    {
      return new ToStringGenerator (this).append ("baseDir", m_sBaseDir).toString ();
    }
  }

  public static final String ELEMENT_ITEMS = "items";
  public static final String ELEMENT_ITEM = "item";
  public static final String ATTR_DT_STRING = "dts";
  public static final String ATTR_USER = "user";
  public static final String ATTR_TYPE = "type";
  /* initially was called "succes" by accident */
  public static final String ATTR_SUCCESS = "success";

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
           StringHelper.getLeadingZero (aDate.getMonthOfYear (), 2) +
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
   * @param aUserIDProvider
   *        The current user ID provider. May not be <code>null</code>.
   * @throws DAOException
   *         In case reading failed
   */
  public AuditManager (@Nullable final String sBaseDir,
                       @Nonnull final ICurrentUserIDProvider aUserIDProvider) throws DAOException
  {
    super (new AuditHasFilename (sBaseDir));
    ValueEnforcer.notNull (aUserIDProvider, "UserIDProvider");

    // Ensure base path is present (if provided)
    m_sBaseDir = sBaseDir;
    if (StringHelper.hasText (sBaseDir))
    {
      final IMutablePathRelativeIO aIO = getDAOIO ().getFileIO ();
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

    final IThrowingRunnableWithParameter <List <IAuditItem>, Exception> aPerformer = new IThrowingRunnableWithParameter <List <IAuditItem>, Exception> ()
    {
      public void run (@Nonnull final List <IAuditItem> aAuditItems)
      {
        if (!aAuditItems.isEmpty ())
        {
          m_aRWLock.writeLock ().lock ();
          try
          {
            for (final IAuditItem aItem : aAuditItems)
              m_aItems.internalAddItem (aItem);

            // In write-lock - it should be safe anyway since the caller
            // serializes the calls to this method
            markAsChanged ();
          }
          finally
          {
            m_aRWLock.writeLock ().unlock ();
          }
        }
      }
    };

    m_aAuditor = new AsynchronousAuditor (aUserIDProvider, aPerformer);
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

  private static interface IReadHandler
  {
    void onReadAuditItem (@Nonnull IAuditItem aItem);
  }

  public static void readFromXML (@Nonnull final IMicroDocument aDoc, @Nonnull final IReadHandler aHandler)
  {
    ValueEnforcer.notNull (aDoc, "Doc");
    ValueEnforcer.notNull (aHandler, "Handler");

    for (final IMicroElement eItem : aDoc.getDocumentElement ().getAllChildElements (ELEMENT_ITEM))
    {
      final String sDT = eItem.getAttributeValue (ATTR_DT_STRING);
      final LocalDateTime aDT = PDTFactory.createLocalDateTime (sDT);
      if (aDT == null)
      {
        s_aLogger.warn ("Failed to parse date time '" + sDT + "'");
        continue;
      }

      final String sUserID = eItem.getAttributeValue (ATTR_USER);
      if (StringHelper.hasNoText (sUserID))
      {
        s_aLogger.warn ("Failed find user ID");
        continue;
      }

      final String sType = eItem.getAttributeValue (ATTR_TYPE);
      final EAuditActionType eType = EAuditActionType.getFromIDOrNull (sType);
      if (eType == null)
      {
        s_aLogger.warn ("Failed to parse change type '" + sType + "'");
        continue;
      }

      final String sSuccess = eItem.getAttributeValue (ATTR_SUCCESS);
      final ESuccess eSuccess = ESuccess.valueOf (StringParser.parseBool (sSuccess));

      final String sAction = eItem.getTextContent ();
      aHandler.onReadAuditItem (new AuditItem (aDT, sUserID, eType, eSuccess, sAction));
    }
  }

  @Override
  @Nonnull
  protected EChange onRead (@Nonnull final IMicroDocument aDoc)
  {
    readFromXML (aDoc, new IReadHandler ()
    {
      public void onReadAuditItem (@Nonnull final IAuditItem aItem)
      {
        m_aItems.internalAddItem (aItem);
      }
    });
    // read-only :)
    return EChange.UNCHANGED;
  }

  @Override
  protected IMicroDocument createWriteData ()
  {
    final IMicroDocument aDoc = new MicroDocument ();
    final IMicroElement eCIL = aDoc.appendElement (ELEMENT_ITEMS);
    // Is sorted internally!
    for (final IAuditItem aAuditItem : m_aItems.getAllItems ())
    {
      final IMicroElement eItem = eCIL.appendElement (ELEMENT_ITEM);
      eItem.setAttribute (ATTR_DT_STRING, aAuditItem.getDateTime ().toString ());
      eItem.setAttribute (ATTR_USER, aAuditItem.getUserID ());
      eItem.setAttribute (ATTR_TYPE, aAuditItem.getType ().getID ());
      eItem.setAttribute (ATTR_SUCCESS, Boolean.toString (aAuditItem.getSuccess ().isSuccess ()));
      eItem.appendText (aAuditItem.getAction ());
    }
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
    m_aRWLock.readLock ().lock ();
    try
    {
      return m_aItems.getItemCount ();
    }
    finally
    {
      m_aRWLock.readLock ().unlock ();
    }
  }

  @Nonnull
  @ReturnsMutableCopy
  public List <IAuditItem> getLastAuditItems (@Nonnegative final int nMaxItems)
  {
    m_aRWLock.readLock ().lock ();
    try
    {
      return m_aItems.getLastItems (nMaxItems);
    }
    finally
    {
      m_aRWLock.readLock ().unlock ();
    }
  }

  public void stop ()
  {
    // Do not call in write-lock as this call blocks until the auditor is
    // stopped
    m_aAuditor.stop ();
  }

  @Nullable
  @ReturnsMutableCopy
  public List <IAuditItem> getAllAuditItemsOfDate (@Nonnull final LocalDate aDate)
  {
    ValueEnforcer.notNull (aDate, "Date");

    final String sFilename = m_sBaseDir + getRelativeAuditFilename (aDate);
    final File aFile = getDAOIO ().getFileIO ().getFile (sFilename);
    if (!aFile.exists ())
      return null;

    final List <IAuditItem> ret = new ArrayList <IAuditItem> ();
    final IMicroDocument aDoc = MicroReader.readMicroXML (aFile);
    readFromXML (aDoc, new IReadHandler ()
    {
      public void onReadAuditItem (@Nonnull final IAuditItem aItem)
      {
        ret.add (aItem);
      }
    });
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
        final IPathRelativeIO aDataIO = WebFileIO.getDataIO ();

        // check for year (from now back)
        int nYear = aEarliest.getYear ();
        while (aDataIO.getFile (m_sBaseDir + getRelativeAuditDirectoryYear (nYear)).isDirectory ())
          --nYear;

        // Undo last "--"
        ++nYear;

        // Find first month
        aEarliest = PDTFactory.createLocalDate (nYear, DateTimeConstants.JANUARY, 1);
        for (int nMonth = DateTimeConstants.JANUARY; nMonth <= DateTimeConstants.DECEMBER; ++nMonth)
        {
          aEarliest = aEarliest.withMonthOfYear (nMonth);
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
                            .toString ();
  }
}
