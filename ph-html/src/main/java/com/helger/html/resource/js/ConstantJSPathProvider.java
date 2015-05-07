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
package com.helger.html.resource.js;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.helger.commons.ValueEnforcer;
import com.helger.commons.annotations.Nonempty;
import com.helger.commons.equals.EqualsUtils;
import com.helger.commons.hash.HashCodeGenerator;
import com.helger.commons.string.ToStringGenerator;

/**
 * An implementation of {@link IJSPathProvider} using constant paths.
 *
 * @author Philip Helger
 */
public final class ConstantJSPathProvider implements IJSPathProvider
{
  public static final boolean DEFAULT_CAN_BE_BUNDLED = true;
  private static final String DEFAULT_CONDITIONAL_COMMENT = null;

  private final String m_sPath;
  private final String m_sMinifiedPath;
  private final String m_sConditionalComment;
  private final boolean m_bCanBeBundled;

  public ConstantJSPathProvider (@Nonnull @Nonempty final String sPath)
  {
    this (sPath, DEFAULT_CONDITIONAL_COMMENT, DEFAULT_CAN_BE_BUNDLED);
  }

  public ConstantJSPathProvider (@Nonnull @Nonempty final String sPath, final boolean bCanBeBundled)
  {
    this (sPath, DEFAULT_CONDITIONAL_COMMENT, bCanBeBundled);
  }

  public ConstantJSPathProvider (@Nonnull @Nonempty final String sPath,
                                 @Nullable final String sConditionalComment,
                                 final boolean bCanBeBundled)
  {
    this (sPath, JSFilenameHelper.getMinifiedJSPath (sPath), sConditionalComment, bCanBeBundled);
  }

  public ConstantJSPathProvider (@Nonnull @Nonempty final String sPath, @Nonnull @Nonempty final String sMinifiedPath)
  {
    this (sPath, sMinifiedPath, DEFAULT_CONDITIONAL_COMMENT, DEFAULT_CAN_BE_BUNDLED);
  }

  public ConstantJSPathProvider (@Nonnull @Nonempty final String sPath,
                                 @Nonnull @Nonempty final String sMinifiedPath,
                                 @Nullable final String sConditionalComment,
                                 final boolean bCanBeBundled)
  {
    ValueEnforcer.notEmpty (sPath, "Path");
    if (!JSFilenameHelper.isJSFilename (sPath))
      throw new IllegalArgumentException ("path");
    ValueEnforcer.notEmpty (sMinifiedPath, "MinifiedPath");
    if (!JSFilenameHelper.isJSFilename (sMinifiedPath))
      throw new IllegalArgumentException ("minified path");
    m_sPath = sPath;
    m_sMinifiedPath = sMinifiedPath;
    m_sConditionalComment = sConditionalComment;
    m_bCanBeBundled = bCanBeBundled;
  }

  @Nonnull
  @Nonempty
  public String getJSItemPath (final boolean bRegular)
  {
    return bRegular ? m_sPath : m_sMinifiedPath;
  }

  @Nonnull
  @Nonempty
  public String getJSItemPathRegular ()
  {
    return m_sPath;
  }

  @Nonnull
  @Nonempty
  public String getJSItemPathMinified ()
  {
    return m_sMinifiedPath;
  }

  @Nullable
  public String getConditionalComment ()
  {
    return m_sConditionalComment;
  }

  @Override
  public boolean canBeBundled ()
  {
    return m_bCanBeBundled;
  }

  @Override
  public boolean equals (final Object o)
  {
    if (o == this)
      return true;
    if (!(o instanceof ConstantJSPathProvider))
      return false;
    final ConstantJSPathProvider rhs = (ConstantJSPathProvider) o;
    return m_sPath.equals (rhs.m_sPath) &&
           m_sMinifiedPath.equals (rhs.m_sMinifiedPath) &&
           EqualsUtils.equals (m_sConditionalComment, rhs.m_sConditionalComment) &&
           m_bCanBeBundled == rhs.m_bCanBeBundled;
  }

  @Override
  public int hashCode ()
  {
    return new HashCodeGenerator (this).append (m_sPath)
                                       .append (m_sMinifiedPath)
                                       .append (m_sConditionalComment)
                                       .append (m_bCanBeBundled)
                                       .getHashCode ();
  }

  @Override
  public String toString ()
  {
    return new ToStringGenerator (this).append ("path", m_sPath)
                                       .append ("minifiedPath", m_sMinifiedPath)
                                       .appendIfNotNull ("conditionalComment", m_sConditionalComment)
                                       .append ("canBeBundled", m_bCanBeBundled)
                                       .toString ();
  }
}
