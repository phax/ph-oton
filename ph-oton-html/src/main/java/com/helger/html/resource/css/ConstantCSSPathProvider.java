/*
 * Copyright (C) 2014-2025 Philip Helger (www.helger.com)
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
import com.helger.commons.annotation.ChangeNextMajorRelease;
import com.helger.commons.annotation.Nonempty;
import com.helger.commons.annotation.ReturnsMutableCopy;
import com.helger.commons.builder.IBuilder;
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

  @Deprecated (forRemoval = false)
  @ChangeNextMajorRelease ("Make protected")
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

  public static final class Builder implements IBuilder <ConstantCSSPathProvider>
  {
    private String m_sPath;
    private String m_sMinifiedPath;
    private String m_sConditionalComment = DEFAULT_CONDITIONAL_COMMENT;
    private ICSSMediaList m_aCSSMediaList = DEFAULT_CSS_MEDIA_LIST;
    private boolean m_bIsBundlable = DEFAULT_IS_BUNDLABLE;

    public Builder ()
    {}

    @Nonnull
    public Builder path (@Nullable final String s)
    {
      m_sPath = s;
      return this;
    }

    @Nonnull
    public Builder minifiedPath (@Nullable final String s)
    {
      m_sMinifiedPath = s;
      return this;
    }

    @Nonnull
    public Builder minifiedPathFromPath ()
    {
      return minifiedPath (CSSFilenameHelper.getMinifiedCSSFilename (m_sPath));
    }

    @Nonnull
    public Builder conditionalComment (@Nullable final String s)
    {
      m_sConditionalComment = s;
      return this;
    }

    @Nonnull
    public Builder cssMediaList (@Nullable final ICSSMediaList a)
    {
      m_aCSSMediaList = a;
      return this;
    }

    @Nonnull
    public Builder bundlable (final boolean b)
    {
      m_bIsBundlable = b;
      return this;
    }

    @Nonnull
    public ConstantCSSPathProvider build ()
    {
      return new ConstantCSSPathProvider (m_sPath,
                                          m_sMinifiedPath,
                                          m_sConditionalComment,
                                          m_aCSSMediaList,
                                          m_bIsBundlable);
    }
  }

  @Nonnull
  public static Builder builder ()
  {
    return new Builder ();
  }

  @Nonnull
  @Deprecated (forRemoval = true, since = "9.3.0")
  public static ConstantCSSPathProvider create (@Nonnull @Nonempty final String sPath)
  {
    return builder ().path (sPath).minifiedPathFromPath ().build ();
  }

  @Nonnull
  @Deprecated (forRemoval = true, since = "9.3.0")
  public static ConstantCSSPathProvider createWithConditionalComment (@Nonnull @Nonempty final String sPath,
                                                                      @Nullable final String sConditionalComment)
  {
    return builder ().path (sPath).minifiedPathFromPath ().conditionalComment (sConditionalComment).build ();
  }

  @Nonnull
  @Deprecated (forRemoval = true, since = "9.3.0")
  public static ConstantCSSPathProvider createWithConditionalComment (@Nonnull @Nonempty final String sPath,
                                                                      @Nullable final String sConditionalComment,
                                                                      @Nullable final ICSSMediaList aMediaList)
  {
    return builder ().path (sPath)
                     .minifiedPathFromPath ()
                     .conditionalComment (sConditionalComment)
                     .cssMediaList (aMediaList)
                     .build ();
  }

  @Nonnull
  @Deprecated (forRemoval = true, since = "9.3.0")
  public static ConstantCSSPathProvider createBundlable (@Nonnull @Nonempty final String sPath,
                                                         final boolean bBundlable)
  {
    return builder ().path (sPath).minifiedPathFromPath ().bundlable (bBundlable).build ();
  }

  @Nonnull
  @Deprecated (forRemoval = true, since = "9.3.0")
  public static ConstantCSSPathProvider createBundlable (@Nonnull @Nonempty final String sPath,
                                                         @Nullable final ICSSMediaList aMediaList,
                                                         final boolean bBundlable)
  {
    return builder ().path (sPath).minifiedPathFromPath ().cssMediaList (aMediaList).bundlable (bBundlable).build ();
  }

  @Nonnull
  @Deprecated (forRemoval = true, since = "9.3.0")
  public static ConstantCSSPathProvider createExternal (@Nonnull @Nonempty final String sURI)
  {
    return builder ().path (sURI).minifiedPath (sURI).bundlable (false).build ();
  }

  @Nonnull
  @Deprecated (forRemoval = true, since = "9.3.0")
  public static ConstantCSSPathProvider createExternal (@Nonnull @Nonempty final String sURI,
                                                        @Nullable final String sConditionalComment)
  {
    return builder ().path (sURI)
                     .minifiedPath (sURI)
                     .conditionalComment (sConditionalComment)
                     .bundlable (false)
                     .build ();
  }

  @Nonnull
  @Deprecated (forRemoval = true, since = "9.3.0")
  public static ConstantCSSPathProvider createExternal (@Nonnull @Nonempty final String sURI,
                                                        @Nullable final ICSSMediaList aMediaList)
  {
    return builder ().path (sURI).minifiedPath (sURI).cssMediaList (aMediaList).bundlable (false).build ();
  }

  @Nonnull
  @Deprecated (forRemoval = true, since = "9.3.0")
  public static ConstantCSSPathProvider createExternal (@Nonnull @Nonempty final String sURI,
                                                        @Nullable final String sConditionalComment,
                                                        @Nullable final ICSSMediaList aMediaList)
  {
    // External CSS are never bundleable
    return builder ().path (sURI)
                     .minifiedPath (sURI)
                     .conditionalComment (sConditionalComment)
                     .cssMediaList (aMediaList)
                     .bundlable (false)
                     .build ();
  }
}
