package com.helger.photon.basic.app.systemmsg;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.concurrent.NotThreadSafe;

import com.helger.commons.ValueEnforcer;
import com.helger.commons.annotation.Nonempty;
import com.helger.commons.datetime.PDTFactory;
import com.helger.commons.equals.EqualsHelper;
import com.helger.commons.hashcode.HashCodeGenerator;
import com.helger.commons.state.EChange;
import com.helger.commons.string.StringHelper;
import com.helger.commons.string.ToStringGenerator;

/**
 * System message data used in {@link SystemMessageManager}.
 *
 * @author Philip Helger
 * @since 7.1.0
 */
@NotThreadSafe
public class SystemMessageData implements Serializable
{
  private LocalDateTime m_aLastUpdate;
  private ESystemMessageType m_eMessageType = ESystemMessageType.DEFAULT;
  private String m_sMessage;

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

  @Nonnull
  @Nonempty
  public String getMessageTypeID ()
  {
    return getMessageType ().getID ();
  }

  @Nullable
  public String getMessage ()
  {
    return m_sMessage;
  }

  public boolean hasMessage ()
  {
    return StringHelper.hasText (getMessage ());
  }

  void internalReset ()
  {
    internalSetLastUpdate (null);
    internalSetMessageType (ESystemMessageType.DEFAULT);
    internalSetMessage (null);
  }

  void internalSetLastUpdate (@Nullable final LocalDateTime aLastUpdate)
  {
    m_aLastUpdate = aLastUpdate;
  }

  void internalSetMessageType (@Nonnull final ESystemMessageType eType)
  {
    ValueEnforcer.notNull (eType, "Type");
    m_eMessageType = eType;
  }

  void internalSetMessage (@Nullable final String sMessage)
  {
    m_sMessage = sMessage;
  }

  @Nonnull
  public EChange setSystemMessage (@Nonnull final ESystemMessageType eMessageType, @Nullable final String sMessage)
  {
    ValueEnforcer.notNull (eMessageType, "MessageType");

    if (m_eMessageType.equals (eMessageType) && EqualsHelper.equals (m_sMessage, sMessage))
      return EChange.UNCHANGED;

    m_eMessageType = eMessageType;
    m_sMessage = sMessage;

    // Update last update
    m_aLastUpdate = PDTFactory.getCurrentLocalDateTime ();
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
    return new HashCodeGenerator (this).append (m_aLastUpdate)
                                       .append (m_eMessageType)
                                       .append (m_sMessage)
                                       .getHashCode ();
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
