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
package com.helger.photon.core.smtp;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.Nullable;

import com.helger.annotation.concurrent.ELockType;
import com.helger.annotation.concurrent.MustBeLocked;
import com.helger.annotation.concurrent.ThreadSafe;
import com.helger.annotation.style.ReturnsMutableCopy;
import com.helger.base.exception.InitializationException;
import com.helger.base.state.EChange;
import com.helger.collection.commons.ICommonsList;
import com.helger.dao.DAOException;
import com.helger.photon.audit.AuditHelper;
import com.helger.photon.io.dao.AbstractPhotonSimpleDAO;
import com.helger.smtp.failed.FailedMailData;
import com.helger.smtp.failed.FailedMailQueue;
import com.helger.xml.microdom.IMicroDocument;
import com.helger.xml.microdom.IMicroElement;
import com.helger.xml.microdom.MicroDocument;
import com.helger.xml.microdom.convert.MicroTypeConverter;

/**
 * A special {@link FailedMailQueue} that supports persistent storage.
 *
 * @author Philip Helger
 */
@ThreadSafe
public class FailedMailQueueWithDAO extends FailedMailQueue
{
  private final class FMQDAO extends AbstractPhotonSimpleDAO
  {
    private static final String ELEMENT_FAILEDMAILS = "failedmails";
    private static final String ELEMENT_FAILEDMAILDATA = "failedmaildata";

    public FMQDAO (@Nullable final String sFilename)
    {
      super (sFilename);
    }

    /**
     * Initial read - just to make initialRead accessible
     *
     * @throws DAOException
     *         In case of error
     */
    @MustBeLocked (ELockType.WRITE)
    void myInitialRead () throws DAOException
    {
      initialRead ();
    }

    /**
     * Mark as changed - just to make markAsChanged accessible
     */
    @MustBeLocked (ELockType.WRITE)
    void myMarkChanged ()
    {
      super.markAsChanged ();
    }

    @Override
    @NonNull
    protected EChange onRead (@NonNull final IMicroDocument aDoc)
    {
      for (final IMicroElement eFMD : aDoc.getDocumentElement ().getAllChildElements (ELEMENT_FAILEDMAILDATA))
      {
        final FailedMailData aFMD = MicroTypeConverter.convertToNative (eFMD, FailedMailData.class);
        if (aFMD != null)
          FailedMailQueueWithDAO.this.add (aFMD, false);
      }
      return EChange.UNCHANGED;
    }

    @Override
    @NonNull
    protected IMicroDocument createWriteData ()
    {
      final IMicroDocument aDoc = new MicroDocument ();
      final IMicroElement eRoot = aDoc.addElement (ELEMENT_FAILEDMAILS);
      for (final FailedMailData aFMD : FailedMailQueueWithDAO.this.getAllFailedMails ())
        eRoot.addChild (MicroTypeConverter.convertToMicroElement (aFMD, ELEMENT_FAILEDMAILDATA));
      return aDoc;
    }
  }

  private FMQDAO m_aDAO;

  public FailedMailQueueWithDAO (@Nullable final String sFilename)
  {
    try
    {
      m_aDAO = new FMQDAO (sFilename);
      // Read outside FMQDAO constructor, so that the DAO can be used
      m_aDAO.myInitialRead ();
    }
    catch (final DAOException ex)
    {
      throw new InitializationException ("Failed to init FailedMailQueueWithDAO with filename '" + sFilename + "'", ex);
    }
  }

  private void writeObject (@NonNull final ObjectOutputStream aOOS) throws IOException
  {
    aOOS.defaultWriteObject ();
    aOOS.writeUTF (m_aDAO.getFilenameProvider ().get ());
  }

  private void readObject (@NonNull final ObjectInputStream aOIS) throws IOException, ClassNotFoundException
  {
    aOIS.defaultReadObject ();
    final String sFilename = aOIS.readUTF ();
    try
    {
      m_aDAO = new FMQDAO (sFilename);
      // Read outside FMQDAO constructor, so that the DAO can be used
      m_aDAO.myInitialRead ();
    }
    catch (final DAOException ex)
    {
      throw new InitializationException ("Failed to init FailedMailQueueWithDAO with filename '" + sFilename + "'", ex);
    }
  }

  private void _markAsChanged ()
  {
    // Must be called in a lock
    m_aRWLock.writeLocked (m_aDAO::myMarkChanged);
  }

  @Override
  public void add (@NonNull final FailedMailData aFailedMailData)
  {
    this.add (aFailedMailData, true);
  }

  public void add (@NonNull final FailedMailData aFailedMailData, final boolean bAudit)
  {
    super.add (aFailedMailData);
    _markAsChanged ();
    if (bAudit)
      AuditHelper.onAuditExecuteSuccess ("failedmail-add", aFailedMailData);
  }

  @Override
  @Nullable
  public FailedMailData remove (@Nullable final String sID)
  {
    final FailedMailData ret = super.remove (sID);
    if (ret != null)
    {
      _markAsChanged ();
      AuditHelper.onAuditExecuteSuccess ("failedmail-remove", sID);
    }
    else
      AuditHelper.onAuditExecuteFailure ("failedmail-remove", sID);
    return ret;
  }

  @Override
  @NonNull
  @ReturnsMutableCopy
  public ICommonsList <FailedMailData> removeAll ()
  {
    final ICommonsList <FailedMailData> ret = super.removeAll ();
    if (!ret.isEmpty ())
    {
      _markAsChanged ();
      AuditHelper.onAuditExecuteSuccess ("failedmail-remove-all", Integer.valueOf (ret.size ()));
    }
    return ret;
  }
}
