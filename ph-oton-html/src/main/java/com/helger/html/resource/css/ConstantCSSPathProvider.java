/*
 * Copyright (C) 2014-2023 Philip Helger (www.helger.com)
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
package com.helger.html.resource.css;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.helger.commons.ValueEnforcer;
import com.helger.commons.annotation.Nonempty;
import com.helger.commons.annotation.ReturnsMutableCopy;
import com.helger.commons.hashcode.HashCodeGenerator;
import com.helger.commons.string.ToStringGenerator;
import com.helger.css.CSSFilenameHelper;
import com.helger.css.media.CSSMediaList;
import com.helger.css.media.ICSSMediaList;

/**
 * Implementation of {@link ICSSPathProvider} with constant paths.
 *
 * @author Philip Helger
 */
public final class ConstantCSSPathProvider implements ICSSPathProvider
{
  private final String m_sPath;
  private final String m_sMinifiedPath;
  private final String m_sConditionalComment;
  private final CSSMediaList m_aCSSMediaList;
  private final boolean m_bIsBundlable;

  public ConstantCSSPathProvider (@Nonnull @Nonempty final String sPath,
                                  @Nonnull @Nonempty final String sMinifiedPath,
                                  @Nullable final String sConditionalComment,
                                  @Nullable final ICSSMediaList aMediaList,
                                  final boolean bIsBundlable)
  {
    ValueEnforcer.notEmpty (sPath, "Path");
    ValueEnforcer.isTrue (CSSFilenameHelper.isCSSFilename (sPath), () -> "'" + sPath + "' is not a valid CSS filename");
    ValueEnforcer.notEmpty (sMinifiedPath, "MinifiedPath");
    ValueEnforcer.isTrue (CSSFilenameHelper.isCSSFilename (sMinifiedPath),
                          () -> "'" + sMinifiedPath + "' is not a valid minified JS filename");
    m_sPath = sPath;
    m_sMinifiedPath = sMinifiedPath;
    m_sConditionalComment = sConditionalComment;
    m_aCSSMediaList = aMediaList == null ? new CSSMediaList () : new CSSMediaList (aMediaList);
    m_bIsBundlable = bIsBundlable;
  }

  @Nonnull
  @Nonempty
  public String getCSSItemPath (final boolean bRegular)
  {
    return bRegular ? m_sPath : m_sMinifiedPath;
  }

  @Nullable
  public String getConditionalComment ()
  {
    return m_sConditionalComment;
  }

  @Nonnull
  @ReturnsMutableCopy
  public ICSSMediaList getMediaList ()
  {
    return m_aCSSMediaList.getClone ();
  }

  public boolean isBundlable ()
  {
    return m_bIsBundlable;
  }

  @Override
  public boolean equals (final Object o)
  {
    if (o == this)
      return true;
    if (o == null || !getClass ().equals (o.getClass ()))
      return false;
    final ConstantCSSPathProvider rhs = (ConstantCSSPathProvider) o;
    return m_sPath.equals (rhs.m_sPath) &&
           m_sMinifiedPath.equals (rhs.m_sMinifiedPath) &&
           m_bIsBundlable == rhs.m_bIsBundlable;
  }

  @Override
  public int hashCode ()
  {
    return new HashCodeGenerator (this).append (m_sPath)
                                       .append (m_sMinifiedPath)
                                       .append (m_bIsBundlable)
                                       .getHashCode ();
  }

  @Override
  public String toString ()
  {
    return new ToStringGenerator (this).append ("path", m_sPath)
                                       .append ("minifiedPath", m_sMinifiedPath)
                                       .appendIfNotNull ("conditionalComment", m_sConditionalComment)
                                       .appendIfNotNull ("CSSMediaList", m_aCSSMediaList)
                                       .append ("isBundlable", m_bIsBundlable)
                                       .getToString ();
  }

  @Nonnull
  public static ConstantCSSPathProvider create (@Nonnull @Nonempty final String sPath)
  {
    return new ConstantCSSPathProvider (sPath,
                                        CSSFilenameHelper.getMinifiedCSSFilename (sPath),
                                        DEFAULT_CONDITIONAL_COMMENT,
                                        DEFAULT_CSS_MEDIA_LIST,
                                        DEFAULT_IS_BUNDLABLE);
  }

  @Nonnull
  public static ConstantCSSPathProvider createWithConditionalComment (@Nonnull @Nonempty final String sPath,
                                                                      @Nullable final String sConditionalComment)
  {
    return createWithConditionalComment (sPath, sConditionalComment, DEFAULT_CSS_MEDIA_LIST);
  }

  @Nonnull
  public static ConstantCSSPathProvider createWithConditionalComment (@Nonnull @Nonempty final String sPath,
                                                                      @Nullable final String sConditionalComment,
                                                                      @Nullable final ICSSMediaList aMediaList)
  {
    return new ConstantCSSPathProvider (sPath,
                                        CSSFilenameHelper.getMinifiedCSSFilename (sPath),
                                        sConditionalComment,
                                        aMediaList,
                                        DEFAULT_IS_BUNDLABLE);
  }

  @Nonnull
  public static ConstantCSSPathProvider createBundlable (@Nonnull @Nonempty final String sPath,
                                                         final boolean bBundlable)
  {
    return createBundlable (sPath, DEFAULT_CSS_MEDIA_LIST, bBundlable);
  }

  @Nonnull
  public static ConstantCSSPathProvider createBundlable (@Nonnull @Nonempty final String sPath,
                                                         @Nullable final ICSSMediaList aMediaList,
                                                         final boolean bBundlable)
  {
    return new ConstantCSSPathProvider (sPath,
                                        CSSFilenameHelper.getMinifiedCSSFilename (sPath),
                                        DEFAULT_CONDITIONAL_COMMENT,
                                        aMediaList,
                                        bBundlable);
  }

  @Nonnull
  public static ConstantCSSPathProvider createExternal (@Nonnull @Nonempty final String sURI)
  {
    return createExternal (sURI, DEFAULT_CONDITIONAL_COMMENT, DEFAULT_CSS_MEDIA_LIST);
  }

  @Nonnull
  public static ConstantCSSPathProvider createExternal (@Nonnull @Nonempty final String sURI,
                                                        @Nullable final String sConditionalComment)
  {
    return createExternal (sURI, sConditionalComment, DEFAULT_CSS_MEDIA_LIST);
  }

  @Nonnull
  public static ConstantCSSPathProvider createExternal (@Nonnull @Nonempty final String sURI,
                                                        @Nullable final ICSSMediaList aMediaList)
  {
    return createExternal (sURI, DEFAULT_CONDITIONAL_COMMENT, aMediaList);
  }

  @Nonnull
  public static ConstantCSSPathProvider createExternal (@Nonnull @Nonempty final String sURI,
                                                        @Nullable final String sConditionalComment,
                                                        @Nullable final ICSSMediaList aMediaList)
  {
    // External CSS are never bundleable
    return new ConstantCSSPathProvider (sURI, sURI, sConditionalComment, aMediaList, false);
  }
}
