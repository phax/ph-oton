package com.helger.photon.bootstrap3.ext;

import java.util.function.BiConsumer;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.concurrent.NotThreadSafe;

import com.helger.commons.ValueEnforcer;
import com.helger.commons.string.StringHelper;
import com.helger.html.hc.ext.HCExtHelper;
import com.helger.photon.basic.app.systemmsg.ESystemMessageType;
import com.helger.photon.basic.app.systemmsg.SystemMessageManager;
import com.helger.photon.basic.mgr.PhotonBasicManager;
import com.helger.photon.bootstrap3.alert.AbstractBootstrapAlert;
import com.helger.photon.bootstrap3.alert.EBootstrapAlertType;
import com.helger.photon.uicore.UITextFormatter;

@NotThreadSafe
public class BootstrapSystemMessage extends AbstractBootstrapAlert <BootstrapSystemMessage>
{
  private static final BiConsumer <String, BootstrapSystemMessage> DEFAULT_FORMATTER = (s,
                                                                                        t) -> t.addChildren (HCExtHelper.nl2divList (s));
  private static BiConsumer <String, BootstrapSystemMessage> s_aFormatter = DEFAULT_FORMATTER;

  /**
   * Set the default text formatter to be used. This can e.g. be used to easily
   * visualize Markdown syntax in the system message.
   *
   * @param aFormatter
   *        The formatter callback. May not be <code>null</code>.
   */
  public static void setDefaultFormatter (@Nonnull final BiConsumer <String, BootstrapSystemMessage> aFormatter)
  {
    s_aFormatter = ValueEnforcer.notNull (aFormatter, "Formatter");
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
    setDefaultFormatter (bUseMarkdown ? (s, t) -> t.addChild (UITextFormatter.markdown (s)) : DEFAULT_FORMATTER);
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
    if (StringHelper.hasText (sContent))
      s_aFormatter.accept (sContent, this);
    return this;
  }

  @Nullable
  public static BootstrapSystemMessage createDefault ()
  {
    final SystemMessageManager aSystemMsgMgr = PhotonBasicManager.getSystemMessageMgr ();
    return create (aSystemMsgMgr.getMessageType (), aSystemMsgMgr.getSystemMessage ());
  }

  @Nullable
  public static BootstrapSystemMessage create (@Nonnull final ESystemMessageType eSystemMessageType,
                                               @Nullable final String sSystemMessage)
  {
    if (StringHelper.hasNoText (sSystemMessage))
      return null;

    return new BootstrapSystemMessage (eSystemMessageType).setSystemMessage (sSystemMessage);
  }
}
