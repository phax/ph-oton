package com.helger.photon.basic.app.systemmsg;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.helger.commons.annotation.Nonempty;
import com.helger.commons.string.StringHelper;

/**
 * System message data read-only interface.
 *
 * @author Philip Helger
 * @since 7.1.0
 */
public interface ISystemMessageData extends Serializable
{
  @Nullable
  LocalDateTime getLastUpdateDT ();

  @Nonnull
  ESystemMessageType getMessageType ();

  @Nonnull
  @Nonempty
  default String getMessageTypeID ()
  {
    return getMessageType ().getID ();
  }

  @Nullable
  String getMessage ();

  default boolean hasMessage ()
  {
    return StringHelper.hasText (getMessage ());
  }
}
