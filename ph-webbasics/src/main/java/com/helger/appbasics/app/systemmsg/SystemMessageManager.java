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
package com.helger.appbasics.app.systemmsg;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import org.joda.time.LocalDateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.helger.appbasics.app.dao.impl.AbstractSimpleDAO;
import com.helger.appbasics.app.dao.impl.DAOException;
import com.helger.appbasics.security.audit.AuditUtils;
import com.helger.commons.ValueEnforcer;
import com.helger.commons.equals.EqualsUtils;
import com.helger.commons.microdom.IMicroDocument;
import com.helger.commons.microdom.IMicroElement;
import com.helger.commons.microdom.impl.MicroDocument;
import com.helger.commons.microdom.utils.MicroUtils;
import com.helger.commons.state.EChange;
import com.helger.commons.string.StringHelper;
import com.helger.commons.string.ToStringGenerator;
import com.helger.datetime.PDTFactory;

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
    m_aRWLock.writeLock ().lock ();
    try
    {
      m_sMessage = null;
      initialRead ();
      s_aLogger.info ("Reloaded system message!");
    }
    catch (final DAOException ex)
    {
      throw new IllegalStateException ("Failed to reload system message", ex);
    }
    finally
    {
      m_aRWLock.writeLock ().unlock ();
    }
  }

  @Override
  protected EChange onRead (@Nonnull final IMicroDocument aDoc)
  {
    final IMicroElement eRoot = aDoc.getDocumentElement ();
    m_aLastUpdate = eRoot.getAttributeValueWithConversion (ATTR_LAST_UPDATE, LocalDateTime.class);
    m_eMessageType = ESystemMessageType.getFromIDOrDefault (eRoot.getAttributeValue (ATTR_MESSAGE_TYPE));
    m_sMessage = MicroUtils.getChildTextContent (eRoot, ELEMENT_MESSAGE);
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
    m_aRWLock.readLock ().lock ();
    try
    {
      return m_aLastUpdate;
    }
    finally
    {
      m_aRWLock.readLock ().unlock ();
    }
  }

  @Nonnull
  public ESystemMessageType getMessageType ()
  {
    m_aRWLock.readLock ().lock ();
    try
    {
      return m_eMessageType;
    }
    finally
    {
      m_aRWLock.readLock ().unlock ();
    }
  }

  @Nullable
  public String getSystemMessage ()
  {
    m_aRWLock.readLock ().lock ();
    try
    {
      return m_sMessage;
    }
    finally
    {
      m_aRWLock.readLock ().unlock ();
    }
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
      if (m_eMessageType.equals (eMessageType) && EqualsUtils.equals (m_sMessage, sMessage))
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
    AuditUtils.onAuditExecuteSuccess ("update-system-message", eMessageType, sMessage);
    return EChange.CHANGED;
  }

  @Override
  public String toString ()
  {
    return new ToStringGenerator (this).appendIfNotNull ("lastUpdate", m_aLastUpdate)
                                       .append ("messageType", m_eMessageType)
                                       .append ("message", m_sMessage)
                                       .toString ();
  }
}
