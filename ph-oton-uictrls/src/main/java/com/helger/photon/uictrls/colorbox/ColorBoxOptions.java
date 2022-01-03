/*
 * Copyright (C) 2014-2022 Philip Helger (www.helger.com)
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
package com.helger.photon.uictrls.colorbox;

import java.util.Locale;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.helger.commons.annotation.ReturnsMutableCopy;
import com.helger.commons.string.StringHelper;
import com.helger.html.jscode.JSAssocArray;

/**
 * jQuery colorbox plugin from
 *
 * <pre>
 * http://www.jacklmoore.com/colorbox
 * </pre>
 *
 * @author Philip Helger
 */
public class ColorBoxOptions
{
  public static final boolean DEFAULT_PHOTO = false;

  private Locale m_aDisplayLocale;
  private boolean m_bPhoto = DEFAULT_PHOTO;
  private String m_sMaxWidth;
  private String m_sMaxHeight;

  public ColorBoxOptions ()
  {}

  @Nullable
  public Locale getDisplayLocale ()
  {
    return m_aDisplayLocale;
  }

  @Nonnull
  public ColorBoxOptions setDisplayLocale (@Nonnull final Locale aDisplayLocale)
  {
    m_aDisplayLocale = aDisplayLocale;
    return this;
  }

  public boolean isPhoto ()
  {
    return m_bPhoto;
  }

  @Nonnull
  public ColorBoxOptions setPhoto (final boolean bPhoto)
  {
    m_bPhoto = bPhoto;
    return this;
  }

  @Nullable
  public String getMaxWidth ()
  {
    return m_sMaxWidth;
  }

  @Nonnull
  public ColorBoxOptions setMaxWidth (@Nullable final String sMaxWidth)
  {
    m_sMaxWidth = sMaxWidth;
    return this;
  }

  @Nullable
  public String getMaxHeight ()
  {
    return m_sMaxHeight;
  }

  @Nonnull
  public ColorBoxOptions setMaxHeight (@Nullable final String sMaxHeight)
  {
    m_sMaxHeight = sMaxHeight;
    return this;
  }

  @Nonnull
  @ReturnsMutableCopy
  public JSAssocArray getJSOptions ()
  {
    // Build parameters
    final JSAssocArray aArgs = new JSAssocArray ();
    if (m_bPhoto != DEFAULT_PHOTO)
      aArgs.add ("photo", m_bPhoto);
    if (StringHelper.hasText (m_sMaxWidth))
      aArgs.add ("maxWidth", m_sMaxWidth);
    if (StringHelper.hasText (m_sMaxHeight))
      aArgs.add ("maxHeight", m_sMaxHeight);

    if (m_aDisplayLocale != null)
    {
      aArgs.add ("current", EColorBoxText.CURRENT.getDisplayText (m_aDisplayLocale));
      aArgs.add ("previous", EColorBoxText.PREVIOUS.getDisplayText (m_aDisplayLocale));
      aArgs.add ("next", EColorBoxText.NEXT.getDisplayText (m_aDisplayLocale));
      aArgs.add ("close", EColorBoxText.CLOSE.getDisplayText (m_aDisplayLocale));
      aArgs.add ("xhrError", EColorBoxText.XHR_ERROR.getDisplayText (m_aDisplayLocale));
      aArgs.add ("imgError", EColorBoxText.IMG_ERROR.getDisplayText (m_aDisplayLocale));
      aArgs.add ("slideshowStart", EColorBoxText.SLIDESHOW_START.getDisplayText (m_aDisplayLocale));
      aArgs.add ("slideshowStop", EColorBoxText.SLIDESHOW_STOP.getDisplayText (m_aDisplayLocale));
    }

    return aArgs;
  }
}
