/*
 * Copyright (C) 2014-2021 Philip Helger (www.helger.com)
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
package com.helger.photon.app.resource;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.helger.commons.annotation.Nonempty;
import com.helger.commons.id.IHasID;
import com.helger.commons.lang.EnumHelper;
import com.helger.commons.mime.CMimeType;
import com.helger.commons.mime.IMimeType;
import com.helger.commons.name.IHasDisplayName;
import com.helger.commons.url.ISimpleURL;
import com.helger.css.CCSS;
import com.helger.css.media.ICSSMediaList;
import com.helger.html.hc.IHCNode;
import com.helger.html.hc.html.metadata.HCLink;
import com.helger.html.hc.html.script.HCScriptFile;
import com.helger.html.js.CJS;

/**
 * Defines the available resource types.
 *
 * @author Philip Helger
 */
public enum EWebSiteResourceType implements IHasID <String>, IHasDisplayName
{
  JS ("js", CMimeType.TEXT_JAVASCRIPT, CJS.FILE_EXTENSION_JS, "JavaScript")
  {
    @Override
    @Nonnull
    public IHCNode createNode (@Nonnull final ISimpleURL aURL, @Nullable final ICSSMediaList aMediaList)
    {
      return new HCScriptFile ().setSrc (aURL);
    }
  },
  CSS ("css", CMimeType.TEXT_CSS, CCSS.FILE_EXTENSION_CSS, "CSS")
  {
    @Override
    @Nonnull
    public IHCNode createNode (@Nonnull final ISimpleURL aURL, @Nullable final ICSSMediaList aMediaList)
    {
      return HCLink.createCSSLink (aURL).setMedia (aMediaList);
    }
  };

  private final String m_sID;
  private final IMimeType m_aMimeType;
  private final String m_sFileExtension;
  private final String m_sDisplayName;

  EWebSiteResourceType (@Nonnull @Nonempty final String sID,
                        @Nonnull final IMimeType aMimeType,
                        @Nonnull @Nonempty final String sFileExtension,
                        @Nonnull @Nonempty final String sDisplayName)
  {
    m_sID = sID;
    m_aMimeType = aMimeType;
    m_sFileExtension = sFileExtension;
    m_sDisplayName = sDisplayName;
  }

  @Nonnull
  @Nonempty
  public String getID ()
  {
    return m_sID;
  }

  /**
   * @return The MIME type. Never <code>null</code>.
   */
  @Nonnull
  public IMimeType getMimeType ()
  {
    return m_aMimeType;
  }

  /**
   * @return The file extension including the leading dot. E.g. ".css"
   */
  @Nonnull
  @Nonempty
  public String getFileExtension ()
  {
    return m_sFileExtension;
  }

  @Nonnull
  @Nonempty
  public String getDisplayName ()
  {
    return m_sDisplayName;
  }

  @Nonnull
  public abstract IHCNode createNode (@Nonnull ISimpleURL aURL, @Nullable ICSSMediaList aMediaList);

  @Nullable
  public static EWebSiteResourceType getFromIDOrNull (@Nullable final String sID)
  {
    return EnumHelper.getFromIDOrNull (EWebSiteResourceType.class, sID);
  }
}
