/*
 * Copyright (C) 2014-2026 Philip Helger (www.helger.com)
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
package com.helger.photon.uictrls.datatables.plugins;

import org.jspecify.annotations.NonNull;

import com.helger.base.CGlobal;
import com.helger.base.enforce.ValueEnforcer;
import com.helger.base.state.ETriState;
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

  @NonNull
  public DTPButtonsButtonKey setKey (final char cKey)
  {
    m_cKey = cKey;
    return this;
  }

  @NonNull
  public DTPButtonsButtonKey setShiftKey (final boolean bShiftKey)
  {
    return setShiftKey (ETriState.valueOf (bShiftKey));
  }

  @NonNull
  public DTPButtonsButtonKey setShiftKey (@NonNull final ETriState eShiftKey)
  {
    ValueEnforcer.notNull (eShiftKey, "ShiftKey");
    m_eShiftKey = eShiftKey;
    return this;
  }

  @NonNull
  public DTPButtonsButtonKey setAltKey (final boolean bAltKey)
  {
    return setAltKey (ETriState.valueOf (bAltKey));
  }

  @NonNull
  public DTPButtonsButtonKey setAltKey (@NonNull final ETriState eAltKey)
  {
    ValueEnforcer.notNull (eAltKey, "AltKey");
    m_eAltKey = eAltKey;
    return this;
  }

  @NonNull
  public DTPButtonsButtonKey setCtrlKey (final boolean bCtrlKey)
  {
    return setCtrlKey (ETriState.valueOf (bCtrlKey));
  }

  @NonNull
  public DTPButtonsButtonKey setCtrlKey (@NonNull final ETriState eCtrlKey)
  {
    ValueEnforcer.notNull (eCtrlKey, "CtrlKey");
    m_eCtrlKey = eCtrlKey;
    return this;
  }

  @NonNull
  public DTPButtonsButtonKey setMetaKey (final boolean bMetaKey)
  {
    return setMetaKey (ETriState.valueOf (bMetaKey));
  }

  @NonNull
  public DTPButtonsButtonKey setMetaKey (@NonNull final ETriState eMetaKey)
  {
    ValueEnforcer.notNull (eMetaKey, "MetaKey");
    m_eMetaKey = eMetaKey;
    return this;
  }

  @NonNull
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
