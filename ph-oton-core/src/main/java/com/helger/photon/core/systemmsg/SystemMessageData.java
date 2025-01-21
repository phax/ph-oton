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
package com.helger.photon.core.systemmsg;

import java.time.LocalDateTime;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.concurrent.NotThreadSafe;

import com.helger.commons.ValueEnforcer;
import com.helger.commons.datetime.PDTFactory;
import com.helger.commons.equals.EqualsHelper;
import com.helger.commons.hashcode.HashCodeGenerator;
import com.helger.commons.state.EChange;
import com.helger.commons.string.ToStringGenerator;

/**
 * System message data used in {@link SystemMessageManager}.
 *
 * @author Philip Helger
 * @since 7.0.5
 */
@NotThreadSafe
public class SystemMessageData implements ISystemMessageData
{
  private LocalDateTime m_aLastUpdate;
  private ESystemMessageType m_eMessageType = ESystemMessageType.DEFAULT;
  private String m_sMessage;

  public SystemMessageData ()
  {}

  public SystemMessageData (@Nonnull final ESystemMessageType eMessageType, @Nullable final String sMessage)
  {
    internalSetMessageType (eMessageType);
    internalSetMessage (sMessage);
  }

  @Nullable
  public LocalDateTime getLastUpdateDT ()
  {
    return m_aLastUpdate;
  }

  @Nonnull
  public ESystemMessageType getMessageType ()
  {
    return m_eMessageType;
  }

  @Nullable
  public String getMessage ()
  {
    return m_sMessage;
  }

  final void internalReset ()
  {
    setLastUpdate (null);
    internalSetMessageType (ESystemMessageType.DEFAULT);
    internalSetMessage (null);
  }

  public final void setLastUpdate (@Nullable final LocalDateTime aLastUpdate)
  {
    m_aLastUpdate = aLastUpdate;
  }

  final void internalSetMessageType (@Nonnull final ESystemMessageType eType)
  {
    ValueEnforcer.notNull (eType, "Type");
    m_eMessageType = eType;
  }

  final void internalSetMessage (@Nullable final String sMessage)
  {
    m_sMessage = sMessage;
  }

  /**
   * Set the system message type and text, and update the last modification
   * date.
   * 
   * @param eMessageType
   *        Message type. May not be <code>null</code>.
   * @param sMessage
   *        The message text. May be <code>null</code>.
   * @return {@link EChange}
   */
  @Nonnull
  public EChange setSystemMessage (@Nonnull final ESystemMessageType eMessageType, @Nullable final String sMessage)
  {
    ValueEnforcer.notNull (eMessageType, "MessageType");

    if (m_eMessageType.equals (eMessageType) && EqualsHelper.equals (m_sMessage, sMessage))
      return EChange.UNCHANGED;

    internalSetMessageType (eMessageType);
    internalSetMessage (sMessage);
    setLastUpdate (PDTFactory.getCurrentLocalDateTime ());

    return EChange.CHANGED;
  }

  @Override
  public boolean equals (final Object o)
  {
    if (o == this)
      return true;
    if (o == null || !getClass ().equals (o.getClass ()))
      return false;
    final SystemMessageData rhs = (SystemMessageData) o;
    return EqualsHelper.equals (m_aLastUpdate, rhs.m_aLastUpdate) &&
           m_eMessageType.equals (rhs.m_eMessageType) &&
           EqualsHelper.equals (m_sMessage, rhs.m_sMessage);
  }

  @Override
  public int hashCode ()
  {
    return new HashCodeGenerator (this).append (m_aLastUpdate).append (m_eMessageType).append (m_sMessage).getHashCode ();
  }

  @Override
  public String toString ()
  {
    return new ToStringGenerator (this).append ("LastUpdate", m_aLastUpdate)
                                       .append ("MessageType", m_eMessageType)
                                       .append ("Message", m_sMessage)
                                       .getToString ();
  }
}
