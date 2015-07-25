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
package com.helger.photon.uictrls.colorpicker;

import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.concurrent.NotThreadSafe;

import com.helger.commons.ValueEnforcer;
import com.helger.commons.annotation.Nonempty;
import com.helger.commons.annotation.ReturnsMutableCopy;
import com.helger.css.utils.ECSSColorName;
import com.helger.html.CHTMLAttributes;
import com.helger.html.jscode.JSAnonymousFunction;
import com.helger.html.jscode.JSAssocArray;

/**
 * This class collects all options for the {@link HCColorPicker} class.
 *
 * @author Philip Helger
 */
@NotThreadSafe
public class ColorPickerOptions
{
  public static final boolean DEFAULT_REQUIRED = true;
  public static final boolean DEFAULT_ADJUST = true;
  public static final boolean DEFAULT_HASH = false;
  public static final boolean DEFAULT_CAPS = true;
  public static final boolean DEFAULT_SLIDER = true;
  public static final String DEFAULT_VALUE_ELEMENT = CHTMLAttributes.TARGET;
  public static final String DEFAULT_STYLE_ELEMENT = CHTMLAttributes.TARGET;
  public static final boolean DEFAULT_PICKER_ON_FOCUS = true;
  public static final EColorPickerMode DEFAULT_PICKER_MODE = EColorPickerMode.HSV;
  public static final EColorPickerPosition DEFAULT_PICKER_POSITION = EColorPickerPosition.BOTTOM;
  public static final boolean DEFAULT_PICKER_SMART_POSITION = true;
  public static final int DEFAULT_PICKER_BUTTON_HEIGHT = 20;
  public static final boolean DEFAULT_PICKER_CLOSABLE = false;
  public static final String DEFAULT_PICKER_CLOSE_TEXT = "Close";
  public static final String DEFAULT_PICKER_BUTTON_COLOR = ECSSColorName.BUTTONTEXT.getName ();
  public static final int DEFAULT_PICKER_FACE_PX = 10;
  public static final String DEFAULT_PICKER_FACE_COLOR = ECSSColorName.THREEDFACE.getName ();
  public static final int DEFAULT_PICKER_BORDER_PX = 1;
  public static final String DEFAULT_PICKER_BORDER_COLOR = ECSSColorName.THREEDHIGHLIGHT.getName () +
                                                           ' ' +
                                                           ECSSColorName.THREEDSHADOW.getName () +
                                                           ' ' +
                                                           ECSSColorName.THREEDSHADOW.getName () +
                                                           ' ' +
                                                           ECSSColorName.THREEDHIGHLIGHT.getName ();
  public static final int DEFAULT_PICKER_INSET_PX = 1;
  public static final String DEFAULT_PICKER_INSET_COLOR = ECSSColorName.THREEDSHADOW.getName () +
                                                          ' ' +
                                                          ECSSColorName.THREEDHIGHLIGHT.getName () +
                                                          ' ' +
                                                          ECSSColorName.THREEDHIGHLIGHT.getName () +
                                                          ' ' +
                                                          ECSSColorName.THREEDSHADOW.getName ();
  public static final int DEFAULT_PICKER_ZINDEX = 10000;

  private boolean m_bRequired = DEFAULT_REQUIRED;
  private boolean m_bAdjust = DEFAULT_ADJUST;
  private boolean m_bHash = DEFAULT_HASH;
  private boolean m_bCaps = DEFAULT_CAPS;
  private boolean m_bSlider = DEFAULT_SLIDER;
  private String m_sValueElement = DEFAULT_VALUE_ELEMENT;
  private String m_sStyleElement = DEFAULT_STYLE_ELEMENT;
  private JSAnonymousFunction m_aOnImmediateChange = null;
  private boolean m_bPickerOnFocus = DEFAULT_PICKER_ON_FOCUS;
  private EColorPickerMode m_ePickerMode = DEFAULT_PICKER_MODE;
  private EColorPickerPosition m_ePickerPosition = DEFAULT_PICKER_POSITION;
  private boolean m_bPickerSmartPosition = DEFAULT_PICKER_SMART_POSITION;
  private int m_nPickerButtonHeight = DEFAULT_PICKER_BUTTON_HEIGHT;
  private boolean m_bPickerClosable = DEFAULT_PICKER_CLOSABLE;
  private String m_sPickerCloseText = DEFAULT_PICKER_CLOSE_TEXT;
  private String m_sPickerButtonColor = DEFAULT_PICKER_BUTTON_COLOR;
  private int m_nPickerFacePx = DEFAULT_PICKER_FACE_PX;
  private String m_sPickerFaceColor = DEFAULT_PICKER_FACE_COLOR;
  private int m_nPickerBorderPx = DEFAULT_PICKER_BORDER_PX;
  private String m_sPickerBorderColor = DEFAULT_PICKER_BORDER_COLOR;
  private int m_nPickerInsetPx = DEFAULT_PICKER_INSET_PX;
  private String m_sPickerInsetColor = DEFAULT_PICKER_INSET_COLOR;
  private int m_nPickerZIndex = DEFAULT_PICKER_ZINDEX;

  public ColorPickerOptions ()
  {}

  public boolean isRequired ()
  {
    return m_bRequired;
  }

  @Nonnull
  public ColorPickerOptions setRequired (final boolean bRequired)
  {
    m_bRequired = bRequired;
    return this;
  }

  public boolean isAdjust ()
  {
    return m_bAdjust;
  }

  @Nonnull
  public ColorPickerOptions setAdjust (final boolean bAdjust)
  {
    m_bAdjust = bAdjust;
    return this;
  }

  public boolean isHash ()
  {
    return m_bHash;
  }

  @Nonnull
  public ColorPickerOptions setHash (final boolean bHash)
  {
    m_bHash = bHash;
    return this;
  }

  public boolean isCaps ()
  {
    return m_bCaps;
  }

  @Nonnull
  public ColorPickerOptions setCaps (final boolean bCaps)
  {
    m_bCaps = bCaps;
    return this;
  }

  public boolean isSlider ()
  {
    return m_bSlider;
  }

  @Nonnull
  public ColorPickerOptions setSlider (final boolean bSlider)
  {
    m_bSlider = bSlider;
    return this;
  }

  @Nonnull
  @Nonempty
  public String getValueElement ()
  {
    return m_sValueElement;
  }

  @Nonnull
  public ColorPickerOptions setValueElement (@Nonnull @Nonempty final String sValueElement)
  {
    ValueEnforcer.notEmpty (sValueElement, "ValueElement");
    m_sValueElement = sValueElement;
    return this;
  }

  @Nonnull
  @Nonempty
  public String getStyleElement ()
  {
    return m_sStyleElement;
  }

  @Nonnull
  public ColorPickerOptions setStyleElement (@Nonnull @Nonempty final String sStyleElement)
  {
    ValueEnforcer.notEmpty (sStyleElement, "StyleElement");
    m_sStyleElement = sStyleElement;
    return this;
  }

  @Nullable
  public JSAnonymousFunction getOnImmediateChange ()
  {
    return m_aOnImmediateChange;
  }

  @Nonnull
  public ColorPickerOptions setOnImmediateChange (@Nullable final JSAnonymousFunction aOnImmediateChange)
  {
    m_aOnImmediateChange = aOnImmediateChange;
    return this;
  }

  public boolean isPickerOnFocus ()
  {
    return m_bPickerOnFocus;
  }

  @Nonnull
  public ColorPickerOptions setPickerOnFocus (final boolean bPickerOnFocus)
  {
    m_bPickerOnFocus = bPickerOnFocus;
    return this;
  }

  @Nonnull
  public EColorPickerMode getPickerMode ()
  {
    return m_ePickerMode;
  }

  @Nonnull
  public ColorPickerOptions setPickerMode (@Nonnull final EColorPickerMode ePickerMode)
  {
    ValueEnforcer.notNull (ePickerMode, "PickerMode");
    m_ePickerMode = ePickerMode;
    return this;
  }

  @Nonnull
  public EColorPickerPosition getPickerPosition ()
  {
    return m_ePickerPosition;
  }

  @Nonnull
  public ColorPickerOptions setPickerPosition (@Nonnull final EColorPickerPosition ePickerPosition)
  {
    ValueEnforcer.notNull (ePickerPosition, "PickerPosition");
    m_ePickerPosition = ePickerPosition;
    return this;
  }

  public boolean isPickerSmartPosition ()
  {
    return m_bPickerSmartPosition;
  }

  @Nonnull
  public ColorPickerOptions setPickerSmartPosition (final boolean bPickerSmartPosition)
  {
    m_bPickerSmartPosition = bPickerSmartPosition;
    return this;
  }

  @Nonnegative
  public int getPickerButtonHeight ()
  {
    return m_nPickerButtonHeight;
  }

  @Nonnull
  public ColorPickerOptions setPickerButtonHeight (@Nonnegative final int nPickerButtonHeight)
  {
    ValueEnforcer.isGT0 (nPickerButtonHeight, "PickerButtonHeight");
    m_nPickerButtonHeight = nPickerButtonHeight;
    return this;
  }

  public boolean isPickerClosable ()
  {
    return m_bPickerClosable;
  }

  @Nonnull
  public ColorPickerOptions setPickerClosable (final boolean bPickerClosable)
  {
    m_bPickerClosable = bPickerClosable;
    return this;
  }

  @Nonnull
  @Nonempty
  public String getPickerCloseText ()
  {
    return m_sPickerCloseText;
  }

  @Nonnull
  public ColorPickerOptions setPickerCloseText (@Nonnull @Nonempty final String sPickerCloseText)
  {
    ValueEnforcer.notEmpty (sPickerCloseText, "PickerCloseText");
    m_sPickerCloseText = sPickerCloseText;
    return this;
  }

  @Nonnull
  @Nonempty
  public String getPickerButtonColor ()
  {
    return m_sPickerButtonColor;
  }

  @Nonnull
  public ColorPickerOptions setPickerButtonColor (@Nonnull @Nonempty final String sPickerButtonColor)
  {
    ValueEnforcer.notEmpty (sPickerButtonColor, "PickerButtonColor");
    m_sPickerButtonColor = sPickerButtonColor;
    return this;
  }

  @Nonnegative
  public int getPickerFacePx ()
  {
    return m_nPickerFacePx;
  }

  @Nonnull
  public ColorPickerOptions setPickerFacePx (@Nonnegative final int nPickerFacePx)
  {
    ValueEnforcer.isGT0 (nPickerFacePx, "PickerFacePx");
    m_nPickerFacePx = nPickerFacePx;
    return this;
  }

  @Nonnull
  @Nonempty
  public String getPickerFaceColor ()
  {
    return m_sPickerFaceColor;
  }

  @Nonnull
  public ColorPickerOptions setPickerFaceColor (@Nonnull @Nonempty final String sPickerFaceColor)
  {
    ValueEnforcer.notEmpty (sPickerFaceColor, "PickerFaceColor");
    m_sPickerFaceColor = sPickerFaceColor;
    return this;
  }

  @Nonnegative
  public int getPickerBorderPx ()
  {
    return m_nPickerBorderPx;
  }

  @Nonnull
  public ColorPickerOptions setPickerBorderPx (@Nonnegative final int nPickerBorderPx)
  {
    ValueEnforcer.isGT0 (nPickerBorderPx, "PickerBorderPx");
    m_nPickerBorderPx = nPickerBorderPx;
    return this;
  }

  @Nonnull
  @Nonempty
  public String getPickerBorderColor ()
  {
    return m_sPickerBorderColor;
  }

  @Nonnull
  public ColorPickerOptions setPickerBorderColor (@Nonnull @Nonempty final String sPickerBorderColor)
  {
    ValueEnforcer.notEmpty (sPickerBorderColor, "PickerBorderColor");
    m_sPickerBorderColor = sPickerBorderColor;
    return this;
  }

  @Nonnegative
  public int getPickerInsetPx ()
  {
    return m_nPickerInsetPx;
  }

  @Nonnull
  public ColorPickerOptions setPickerInsetPx (@Nonnegative final int nPickerInsetPx)
  {
    ValueEnforcer.isGT0 (nPickerInsetPx, "PickerInsetPx");
    m_nPickerInsetPx = nPickerInsetPx;
    return this;
  }

  @Nonnull
  @Nonempty
  public String getPickerInsetColor ()
  {
    return m_sPickerInsetColor;
  }

  @Nonnull
  public ColorPickerOptions setPickerInsetColor (@Nonnull @Nonempty final String sPickerInsetColor)
  {
    ValueEnforcer.notEmpty (sPickerInsetColor, "PickerInsetColor");
    m_sPickerInsetColor = sPickerInsetColor;
    return this;
  }

  @Nonnegative
  public int getPickerZIndex ()
  {
    return m_nPickerZIndex;
  }

  @Nonnull
  public ColorPickerOptions setPickerZIndex (@Nonnegative final int nPickerZIndex)
  {
    ValueEnforcer.isGT0 (nPickerZIndex, "PickerZIndex");
    m_nPickerZIndex = nPickerZIndex;
    return this;
  }

  @Nonnull
  @ReturnsMutableCopy
  public JSAssocArray getJSOptions ()
  {
    final JSAssocArray ret = new JSAssocArray ();
    if (m_bRequired != DEFAULT_REQUIRED)
      ret.add ("required", m_bRequired);
    if (m_bAdjust != DEFAULT_ADJUST)
      ret.add ("adjust", m_bAdjust);
    if (m_bHash != DEFAULT_HASH)
      ret.add ("hash", m_bHash);
    if (m_bCaps != DEFAULT_CAPS)
      ret.add ("caps", m_bCaps);
    if (m_bSlider != DEFAULT_SLIDER)
      ret.add ("slider", m_bSlider);
    if (!DEFAULT_VALUE_ELEMENT.equals (m_sValueElement))
      ret.add ("valueElement", m_sValueElement);
    if (!DEFAULT_STYLE_ELEMENT.equals (m_sStyleElement))
      ret.add ("styleElement", m_sStyleElement);
    if (m_aOnImmediateChange != null)
      ret.add ("onImmediateChange", m_aOnImmediateChange);
    if (m_bPickerOnFocus != DEFAULT_PICKER_ON_FOCUS)
      ret.add ("pickerOnfocus", m_bPickerOnFocus);
    if (m_ePickerMode != DEFAULT_PICKER_MODE)
      ret.add ("pickerMode", m_ePickerMode.getAttrValue ());
    if (m_ePickerPosition != DEFAULT_PICKER_POSITION)
      ret.add ("pickerPosition", m_ePickerPosition.getAttrValue ());
    if (m_bPickerSmartPosition != DEFAULT_PICKER_SMART_POSITION)
      ret.add ("pickerSmartPosition", m_bPickerSmartPosition);
    if (m_nPickerButtonHeight != DEFAULT_PICKER_BUTTON_HEIGHT)
      ret.add ("pickerButtonHeight", m_nPickerButtonHeight);
    if (m_bPickerClosable != DEFAULT_PICKER_CLOSABLE)
      ret.add ("pickerClosable", m_bPickerClosable);
    if (!DEFAULT_PICKER_CLOSE_TEXT.equals (m_sPickerCloseText))
      ret.add ("pickerCloseText", m_sPickerCloseText);
    if (!DEFAULT_PICKER_BUTTON_COLOR.equals (m_sPickerButtonColor))
      ret.add ("pickerButtonColor", m_sPickerButtonColor);
    if (m_nPickerFacePx != DEFAULT_PICKER_FACE_PX)
      ret.add ("pickerFace", m_nPickerFacePx);
    if (!DEFAULT_PICKER_FACE_COLOR.equals (m_sPickerFaceColor))
      ret.add ("pickerFaceColor", m_sPickerFaceColor);
    if (m_nPickerBorderPx != DEFAULT_PICKER_BORDER_PX)
      ret.add ("pickerBorder", m_nPickerBorderPx);
    if (!DEFAULT_PICKER_BORDER_COLOR.equals (m_sPickerBorderColor))
      ret.add ("pickerBorderColor", m_sPickerBorderColor);
    if (m_nPickerInsetPx != DEFAULT_PICKER_INSET_PX)
      ret.add ("pickerInset", m_nPickerInsetPx);
    if (!DEFAULT_PICKER_INSET_COLOR.equals (m_sPickerInsetColor))
      ret.add ("pickerInsetColor", m_sPickerInsetColor);
    if (m_nPickerZIndex != DEFAULT_PICKER_ZINDEX)
      ret.add ("pickerZIndex", m_nPickerZIndex);
    return ret;
  }
}
