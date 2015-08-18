package com.helger.photon.uictrls.datatables.plugins;

import javax.annotation.Nonnull;

import com.helger.commons.CGlobal;
import com.helger.commons.ValueEnforcer;
import com.helger.commons.state.ETriState;
import com.helger.html.jscode.IJSExpression;
import com.helger.html.jscode.JSAssocArray;
import com.helger.html.jscode.JSExpr;

public class DTPButtonsButtonKey
{
  /** The character to listen for. The character is case insensitive. */
  private char m_cKey = CGlobal.ILLEGAL_CHAR;
  /**
   * When set to true activation will only occur if the shift key is also being
   * held.
   */
  private ETriState m_eShiftKey = ETriState.UNDEFINED;
  /**
   * When set to true activation will only occur if the alt key is also being
   * held.
   */
  private ETriState m_eAltKey = ETriState.UNDEFINED;
  /**
   * When set to true activation will only occur if the ctrl key is also being
   * held.
   */
  private ETriState m_eCtrlKey = ETriState.UNDEFINED;
  /**
   * When set to true activation will only occur if the cmd key (Mac) or Windows
   * key (Windows) is also being held.
   */
  private ETriState m_eMetaKey = ETriState.UNDEFINED;

  public DTPButtonsButtonKey (final char cKey)
  {
    m_cKey = cKey;
  }

  @Nonnull
  public DTPButtonsButtonKey setKey (final char cKey)
  {
    m_cKey = cKey;
    return this;
  }

  @Nonnull
  public DTPButtonsButtonKey setShiftKey (final boolean bShiftKey)
  {
    return setShiftKey (ETriState.valueOf (bShiftKey));
  }

  @Nonnull
  public DTPButtonsButtonKey setShiftKey (@Nonnull final ETriState eShiftKey)
  {
    ValueEnforcer.notNull (eShiftKey, "ShiftKey");
    m_eShiftKey = eShiftKey;
    return this;
  }

  @Nonnull
  public DTPButtonsButtonKey setAltKey (final boolean bAltKey)
  {
    return setAltKey (ETriState.valueOf (bAltKey));
  }

  @Nonnull
  public DTPButtonsButtonKey setAltKey (@Nonnull final ETriState eAltKey)
  {
    ValueEnforcer.notNull (eAltKey, "AltKey");
    m_eAltKey = eAltKey;
    return this;
  }

  @Nonnull
  public DTPButtonsButtonKey setCtrlKey (final boolean bCtrlKey)
  {
    return setCtrlKey (ETriState.valueOf (bCtrlKey));
  }

  @Nonnull
  public DTPButtonsButtonKey setCtrlKey (@Nonnull final ETriState eCtrlKey)
  {
    ValueEnforcer.notNull (eCtrlKey, "CtrlKey");
    m_eCtrlKey = eCtrlKey;
    return this;
  }

  @Nonnull
  public DTPButtonsButtonKey setMetaKey (final boolean bMetaKey)
  {
    return setMetaKey (ETriState.valueOf (bMetaKey));
  }

  @Nonnull
  public DTPButtonsButtonKey setMetaKey (@Nonnull final ETriState eMetaKey)
  {
    ValueEnforcer.notNull (eMetaKey, "MetaKey");
    m_eMetaKey = eMetaKey;
    return this;
  }

  @Nonnull
  public IJSExpression getAsJS ()
  {
    final JSAssocArray ret = new JSAssocArray ();
    ret.add ("key", m_cKey);
    if (m_eShiftKey.isDefined ())
      ret.add ("shiftKey", m_eShiftKey.getAsBooleanValue (false));
    if (m_eAltKey.isDefined ())
      ret.add ("altKey", m_eAltKey.getAsBooleanValue (false));
    if (m_eShiftKey.isDefined ())
      ret.add ("ctrlKey", m_eCtrlKey.getAsBooleanValue (false));
    if (m_eMetaKey.isDefined ())
      ret.add ("metaKey", m_eMetaKey.getAsBooleanValue (false));
    if (ret.size () == 1)
    {
      // Only the key present
      return JSExpr.lit (m_cKey);
    }
    return ret;
  }
}
