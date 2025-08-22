/*
 * Copyright (C) 2018-2025 Philip Helger (www.helger.com)
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
package com.helger.photon.bootstrap4.ext;

import com.helger.annotation.concurrent.GuardedBy;
import com.helger.base.concurrent.SimpleReadWriteLock;
import com.helger.base.enforce.ValueEnforcer;
import com.helger.base.string.StringHelper;
import com.helger.photon.bootstrap4.alert.AbstractBootstrapAlert;
import com.helger.photon.bootstrap4.alert.EBootstrapAlertType;
import com.helger.photon.core.mgr.PhotonBasicManager;
import com.helger.photon.core.systemmsg.ESystemMessageType;
import com.helger.photon.core.systemmsg.ISystemMessageRenderer;
import com.helger.photon.core.systemmsg.SystemMessageManager;
import com.helger.photon.uicore.systemmsg.SystemMessageRendererMarkdown;
import com.helger.photon.uicore.systemmsg.SystemMessageRendererPlainText;

import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;

/**
 * Render the system message using a Bootstrap alert window.
 *
 * @author Philip Helger
 */
public class BootstrapSystemMessage extends AbstractBootstrapAlert <BootstrapSystemMessage>
{
  public static final ISystemMessageRenderer FORMATTER_DEFAULT = SystemMessageRendererPlainText.INSTANCE;

  private static final SimpleReadWriteLock RW_LOCK = new SimpleReadWriteLock ();
  @GuardedBy ("RW_LOCK")
  private static ISystemMessageRenderer s_aFormatter = FORMATTER_DEFAULT;

  @Nonnull
  public static ISystemMessageRenderer getDefaultFormatter ()
  {
    return RW_LOCK.readLockedGet ( () -> s_aFormatter);
  }

  /**
   * Set the default text formatter to be used. This can e.g. be used to easily
   * visualize Markdown syntax in the system message.
   *
   * @param aFormatter
   *        The formatter callback. May not be <code>null</code>.
   */
  public static void setDefaultFormatter (@Nonnull final ISystemMessageRenderer aFormatter)
  {
    ValueEnforcer.notNull (aFormatter, "Formatter");
    RW_LOCK.writeLocked ( () -> s_aFormatter = aFormatter);
  }

  public static boolean isDefaultMarkdown ()
  {
    return getDefaultFormatter () instanceof SystemMessageRendererMarkdown;
  }

  /**
   * Set a default formatter that interprets the system message as Markdown.
   *
   * @param bUseMarkdown
   *        <code>true</code> to use MD <code>false</code> to use the default
   *        formatter.
   */
  public static void setDefaultUseMarkdown (final boolean bUseMarkdown)
  {
    setDefaultFormatter (bUseMarkdown ? SystemMessageRendererMarkdown.INSTANCE : SystemMessageRendererPlainText.INSTANCE);
  }

  @Nonnull
  public static EBootstrapAlertType getAlertType (@Nonnull final ESystemMessageType eSystemMessageType)
  {
    // Create empty boxes
    switch (eSystemMessageType)
    {
      case INFO:
        return EBootstrapAlertType.INFO;
      case WARNING:
        return EBootstrapAlertType.WARNING;
      case ERROR:
        return EBootstrapAlertType.DANGER;
      case SUCCESS:
        return EBootstrapAlertType.SUCCESS;
      default:
        throw new IllegalArgumentException ("Illegal message type: " + eSystemMessageType);
    }
  }

  protected BootstrapSystemMessage (@Nonnull final ESystemMessageType eSystemMessageType)
  {
    super (getAlertType (eSystemMessageType));
  }

  @Nonnull
  public BootstrapSystemMessage setSystemMessage (@Nullable final String sContent)
  {
    removeAllChildren ();
    if (StringHelper.isNotEmpty (sContent))
      getDefaultFormatter ().renderSystemMessage (sContent, this);
    return this;
  }

  @Nullable
  public static BootstrapSystemMessage createDefault ()
  {
    final SystemMessageManager aSystemMsgMgr = PhotonBasicManager.getSystemMessageMgr ();
    return create (aSystemMsgMgr.getMessageType (), aSystemMsgMgr.getSystemMessage ());
  }

  @Nullable
  public static BootstrapSystemMessage create (@Nonnull final ESystemMessageType eSystemMessageType, @Nullable final String sSystemMessage)
  {
    if (StringHelper.isEmpty (sSystemMessage))
      return null;

    return new BootstrapSystemMessage (eSystemMessageType).setSystemMessage (sSystemMessage);
  }
}
