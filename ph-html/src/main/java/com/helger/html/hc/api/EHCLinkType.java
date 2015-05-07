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
package com.helger.html.hc.api;

import javax.annotation.Nonnull;

import com.helger.commons.annotations.Nonempty;

/**
 * The possible values of the <code>rel</code> attribute of a
 * {@link com.helger.html.hc.html.HCLink} element.<br>
 * Source: http://de.selfhtml.org/html/kopfdaten/beziehungen.htm
 *
 * @author Philip Helger
 */
public enum EHCLinkType implements IHCLinkType
{
  CONTENTS ("contents"),
  CHAPTER ("chapter", true),
  SECTION ("section", true),
  SUBSECTION ("subsection", true),
  INDEX ("index"),
  GLOSSARY ("glossary"),
  APPENDIX ("appendix", true),
  SEARCH ("search"),
  AUTHOR ("author"),
  COPYRIGHT ("copyright"),
  NEXT ("next"),
  PREV ("prev"),
  FIRST ("first"),
  LAST ("last"),
  UP ("up"),
  TOP ("top"),
  START ("start"),
  HELP ("help"),
  BOOKMARK ("bookmark", true),
  STYLESHEET ("stylesheet", true),
  ALTERNATE ("alternate", true),
  ALTERNATE_STYLESHEET ("alternate stylesheet", true),
  SHORTCUT_ICON ("shortcut icon"),
  ICON ("icon"),
  LICENSE ("license"),
  NOFOLLOW ("nofollow"),
  NOREFERRER ("noreferrer"),
  PREFETCH ("prefetch"),
  TAG ("tag"),
  // Apple iOS
  // (http://developer.apple.com/library/IOs/#documentation/AppleApplications/Reference/SafariWebContent/ConfiguringWebApplications/ConfiguringWebApplications.html)
  APPLE_TOUCH_ICON ("apple-touch-icon"),
  APPLE_TOUCH_ICON_PRECOMPOSED ("apple-touch-icon-precomposed"),
  APPLE_TOUCH_STARTUP_IMAGE ("apple-touch-startup-image"),
  // Dublin Core stuff
  DC_SCHEMA ("schema.DC"),
  DC_SCHEMA_TERMS ("schema.DCTERMS");

  private final String m_sAttrValue;
  private final boolean m_bAllowedMoreThanOnce;

  private EHCLinkType (@Nonnull @Nonempty final String sAttrValue)
  {
    this (sAttrValue, false);
  }

  private EHCLinkType (@Nonnull @Nonempty final String sAttrValue, final boolean bAllowedMoreThanOnce)
  {
    m_sAttrValue = sAttrValue;
    m_bAllowedMoreThanOnce = bAllowedMoreThanOnce;
  }

  @Nonnull
  @Nonempty
  public String getAttrValue ()
  {
    return m_sAttrValue;
  }

  public boolean isAllowedMoreThanOnce ()
  {
    return m_bAllowedMoreThanOnce;
  }
}
