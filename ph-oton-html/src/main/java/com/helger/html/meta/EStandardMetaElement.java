/**
 * Copyright (C) 2014-2017 Philip Helger (www.helger.com)
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
package com.helger.html.meta;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.helger.commons.annotation.Nonempty;
import com.helger.commons.lang.EnumHelper;

/**
 * A class with a set of predefined meta tag names.
 *
 * @author Philip Helger
 */
public enum EStandardMetaElement implements IMetaElementDeclaration
{
  // Source: http://vancouver-webpages.com/META/metatags.detail.html
  CONTENT_TYPE ("Content-Type", true),
  CONTENT_SCRIPT_TYPE ("Content-Script-Type", true),
  CONTENT_SECURITY_POLICY ("Content-Security-Policy", true),
  CONTENT_STYLE_TYPE ("Content-Style-Type", true),
  CONTENT_DISPOSITION ("Content-Disposition", true),
  EXPIRES ("Expires", true),
  PRAGMA ("Pragma", true),
  DEFAULT_STYLE ("Default-Style", true),
  CONTENT_LANGUAGE ("Content-Language", true),
  REFRESH ("Refresh", true),
  WINDOW_TARGET ("Window-target", true),
  EXT_CACHE ("Ext-cache", true),
  SET_COOKIE ("Set-Cookie", true),
  PICS_LABEL ("PICS-Label", true),
  CACHE_CONTROL ("Cache-Control", true),
  VARY ("Vary", true),
  LOTUS ("Lotus", true),
  X_UA_COMPATIBLE ("X-UA-Compatible", true),
  // Non HTTP equiv:
  DESCRIPTION ("Description", false),
  KEYWORDS ("Keywords", false),
  AUTHOR ("Author", false),
  COPYRIGHT ("Copyright", false),
  LANGUAGE ("Language", false),
  VIEWPORT ("viewport", false),
  GENERATOR ("generator", false),
  // http://msdn.microsoft.com/en-us/library/ie/dn255024%28v=vs.85%29.aspx
  APPLICATION_NAME ("application-name", false),
  MSAPPLICATION_ALLOW_DOMAIN_API_CALLS ("msapplication-allowDomainApiCalls", false),
  MSAPPLICATION_ALLOW_DOMAIN_META_TAGS ("msapplication-allowDomainMetaTags", false),
  MSAPPLICATION_BADGE ("msapplication-badge", false),
  MSAPPLICATION_CONFIG ("msapplication-config", false),
  MSAPPLICATION_NAVBUTTON_COLOR ("msapplication-navbutton-color", false),
  MSAPPLICATION_NOTIFICATION ("msapplication-notification", false),
  MSAPPLICATION_SQUARE_150X150_LOGO ("msapplication-square150x150logo", false),
  MSAPPLICATION_SQUARE_310X310_LOGO ("msapplication-square310x310logo", false),
  MSAPPLICATION_SQUARE_70X70_LOGO ("msapplication-square70x70logo", false),
  MSAPPLICATION_WIDE_310X150_LOGO ("msapplication-wide310x150logo", false),
  MSAPPLICATION_STARTURL ("msapplication-starturl", false),
  MSAPPLICATION_TASK ("msapplication-task", false),
  MSAPPLICATION_TASK_SEPARATOR ("msapplication-task-separator", false),
  MSAPPLICATION_TILE_COLOR ("msapplication-TileColor", false),
  MSAPPLICATION_TILE_IMAGE ("msapplication-TileImage", false),
  MSAPPLICATION_TOOLTIP ("msapplication-tooltip", false),
  MSAPPLICATION_WINDOW ("msapplication-window", false),
  // http://msdn.microsoft.com/en-us/library/ie/dn265018%28v=vs.85%29.aspx
  FORMAT_DETECTION ("format-detection", false);

  private final String m_sName;
  private final boolean m_bIsHttpEquiv;

  private EStandardMetaElement (@Nonnull @Nonempty final String sName, final boolean bIsHttpEquiv)
  {
    m_sName = sName;
    m_bIsHttpEquiv = bIsHttpEquiv;
  }

  /**
   * Get the meta tag name.
   *
   * @return the meta tag name
   */
  @Nonnull
  @Nonempty
  public String getName ()
  {
    return m_sName;
  }

  /*
   * All standard meta tags have no scheme! Always <code>null</code>.
   */
  @Nullable
  public String getScheme ()
  {
    return null;
  }

  public boolean isHttpEquiv ()
  {
    return m_bIsHttpEquiv;
  }

  @Nonnull
  public IMetaElement getAsMetaElement (@Nullable final String sContent)
  {
    return new MetaElement (this, sContent);
  }

  @Nullable
  public static EStandardMetaElement getStandardElementOfNameOrNull (@Nullable final String sName)
  {
    return EnumHelper.getFromNameOrNull (EStandardMetaElement.class, sName);
  }

  @Nullable
  public static EStandardMetaElement getStandardElementOfNameOrNullIgnoreCase (@Nullable final String sName)
  {
    return EnumHelper.getFromNameCaseInsensitiveOrNull (EStandardMetaElement.class, sName);
  }

  public static boolean isHttpEquivMetaElement (@Nullable final String sName)
  {
    final EStandardMetaElement eStdMetaTag = getStandardElementOfNameOrNullIgnoreCase (sName);
    return eStdMetaTag != null && eStdMetaTag.isHttpEquiv ();
  }
}
