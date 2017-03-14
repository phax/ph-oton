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
package com.helger.photon.basic.app.systemmsg;

import java.time.LocalDateTime;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.helger.commons.ValueEnforcer;
import com.helger.commons.datetime.PDTFactory;
import com.helger.commons.equals.EqualsHelper;
import com.helger.commons.state.EChange;
import com.helger.commons.string.StringHelper;
import com.helger.commons.string.ToStringGenerator;
import com.helger.photon.basic.app.dao.impl.AbstractSimpleDAO;
import com.helger.photon.basic.app.dao.impl.DAOException;
import com.helger.photon.basic.audit.AuditHelper;
import com.helger.xml.microdom.IMicroDocument;
import com.helger.xml.microdom.IMicroElement;
import com.helger.xml.microdom.MicroDocument;
import com.helger.xml.microdom.util.MicroHelper;

/**
 * This class manages global system messages.
 *
 * @author Philip Helger
 */
public final class SystemMessageManager extends AbstractSimpleDAO
{
  private static final String ELEMENT_SYSTEM_MESSAGE = "systemmessage";
  private static final String ATTR_LAST_UPDATE = "lastupdate";
  private static final String ATTR_MESSAGE_TYPE = "messagetype";
  private static final String ELEMENT_MESSAGE = "message";
  private static final Logger s_aLogger = LoggerFactory.getLogger (SystemMessageManager.class);

  private LocalDateTime m_aLastUpdate;
  private ESystemMessageType m_eMessageType = ESystemMessageType.DEFAULT;
  private String m_sMessage;

  public SystemMessageManager (@Nullable final String sFilename) throws DAOException
  {
    super (sFilename);
    initialRead ();
  }

  public void reload ()
  {
    m_aRWLock.writeLocked ( () -> {
      m_aLastUpdate = null;
      m_eMessageType = ESystemMessageType.DEFAULT;
      m_sMessage = null;
      try
      {
        initialRead ();
      }
      catch (final DAOException ex)
      {
        throw new IllegalStateException ("Failed to reload system message", ex);
      }
    });
    s_aLogger.info ("Reloaded system message!");
  }

  @Override
  protected EChange onRead (@Nonnull final IMicroDocument aDoc)
  {
    final IMicroElement eRoot = aDoc.getDocumentElement ();
    m_aLastUpdate = eRoot.getAttributeValueWithConversion (ATTR_LAST_UPDATE, LocalDateTime.class);
    m_eMessageType = ESystemMessageType.getFromIDOrDefault (eRoot.getAttributeValue (ATTR_MESSAGE_TYPE));
    m_sMessage = MicroHelper.getChildTextContent (eRoot, ELEMENT_MESSAGE);
    return EChange.UNCHANGED;
  }

  @Override
  protected IMicroDocument createWriteData ()
  {
    final IMicroDocument ret = new MicroDocument ();
    final IMicroElement eRoot = ret.appendElement (ELEMENT_SYSTEM_MESSAGE);
    eRoot.setAttributeWithConversion (ATTR_LAST_UPDATE, m_aLastUpdate);
    eRoot.setAttribute (ATTR_MESSAGE_TYPE, m_eMessageType.getID ());
    eRoot.appendElement (ELEMENT_MESSAGE).appendText (m_sMessage);
    return ret;
  }

  @Nullable
  public LocalDateTime getLastUpdateDT ()
  {
    return m_aRWLock.readLocked ( () -> m_aLastUpdate);
  }

  @Nonnull
  public ESystemMessageType getMessageType ()
  {
    return m_aRWLock.readLocked ( () -> m_eMessageType);
  }

  @Nullable
  public String getSystemMessage ()
  {
    return m_aRWLock.readLocked ( () -> m_sMessage);
  }

  public boolean hasSystemMessage ()
  {
    return StringHelper.hasText (getSystemMessage ());
  }

  @Nonnull
  public EChange setSystemMessage (@Nonnull final ESystemMessageType eMessageType, @Nullable final String sMessage)
  {
    ValueEnforcer.notNull (eMessageType, "MessageType");

    m_aRWLock.writeLock ().lock ();
    try
    {
      if (m_eMessageType.equals (eMessageType) && EqualsHelper.equals (m_sMessage, sMessage))
        return EChange.UNCHANGED;

      m_eMessageType = eMessageType;
      m_sMessage = sMessage;

      // Update last update
      m_aLastUpdate = PDTFactory.getCurrentLocalDateTime ();
      markAsChanged ();
    }
    finally
    {
      m_aRWLock.writeLock ().unlock ();
    }

    AuditHelper.onAuditExecuteSuccess ("update-system-message", eMessageType, sMessage);
    return EChange.CHANGED;
  }

  @Override
  public String toString ()
  {
    return new ToStringGenerator (this).appendIfNotNull ("lastUpdate", m_aLastUpdate)
                                       .append ("messageType", m_eMessageType)
                                       .append ("message", m_sMessage)
                                       .getToString ();
  }
}
