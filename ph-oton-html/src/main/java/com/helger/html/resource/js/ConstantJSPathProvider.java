/**
 * Copyright (C) 2014-2018 Philip Helger (www.helger.com)
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
import com.helger.commons.annotation.Nonempty;
import com.helger.commons.equals.EqualsHelper;
import com.helger.commons.hashcode.HashCodeGenerator;
import com.helger.commons.string.ToStringGenerator;
import com.helger.html.js.JSFilenameHelper;

/**
 * An implementation of {@link IJSPathProvider} using constant paths.
 *
 * @author Philip Helger
 */
public final class ConstantJSPathProvider implements IJSPathProvider
{
  private final String m_sPath;
  private final String m_sMinifiedPath;
  private final String m_sConditionalComment;
  private final boolean m_bIsBundlable;

  public ConstantJSPathProvider (@Nonnull @Nonempty final String sPath,
                                 @Nonnull @Nonempty final String sMinifiedPath,
                                 @Nullable final String sConditionalComment,
                                 final boolean bIsBundlable)
  {
    ValueEnforcer.notEmpty (sPath, "Path");
    ValueEnforcer.isTrue (JSFilenameHelper.isJSFilename (sPath), "Path");
    ValueEnforcer.notEmpty (sMinifiedPath, "MinifiedPath");
    ValueEnforcer.isTrue (JSFilenameHelper.isJSFilename (sMinifiedPath), "Minified Path");
    m_sPath = sPath;
    m_sMinifiedPath = sMinifiedPath;
    m_sConditionalComment = sConditionalComment;
    m_bIsBundlable = bIsBundlable;
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
    final ConstantJSPathProvider rhs = (ConstantJSPathProvider) o;
    return m_sPath.equals (rhs.m_sPath) &&
           m_sMinifiedPath.equals (rhs.m_sMinifiedPath) &&
           EqualsHelper.equals (m_sConditionalComment, rhs.m_sConditionalComment) &&
           m_bIsBundlable == rhs.m_bIsBundlable;
  }

  @Override
  public int hashCode ()
  {
    return new HashCodeGenerator (this).append (m_sPath)
                                       .append (m_sMinifiedPath)
                                       .append (m_sConditionalComment)
                                       .append (m_bIsBundlable)
                                       .getHashCode ();
  }

  @Override
  public String toString ()
  {
    return new ToStringGenerator (this).append ("path", m_sPath)
                                       .append ("minifiedPath", m_sMinifiedPath)
                                       .appendIfNotNull ("conditionalComment", m_sConditionalComment)
                                       .append ("isBundlable", m_bIsBundlable)
                                       .getToString ();
  }

  @Nonnull
  public static ConstantJSPathProvider create (@Nonnull @Nonempty final String sPath)
  {
    return new ConstantJSPathProvider (sPath,
                                       JSFilenameHelper.getMinifiedJSFilename (sPath),
                                       DEFAULT_CONDITIONAL_COMMENT,
                                       DEFAULT_IS_BUNDLABLE);
  }

  @Nonnull
  public static ConstantJSPathProvider createWithConditionalComment (@Nonnull @Nonempty final String sPath,
                                                                     @Nullable final String sConditionalComment)
  {
    return new ConstantJSPathProvider (sPath,
                                       JSFilenameHelper.getMinifiedJSFilename (sPath),
                                       sConditionalComment,
                                       DEFAULT_IS_BUNDLABLE);
  }

  @Nonnull
  public static ConstantJSPathProvider createBundlable (@Nonnull @Nonempty final String sPath, final boolean bBundlable)
  {
    return new ConstantJSPathProvider (sPath,
                                       JSFilenameHelper.getMinifiedJSFilename (sPath),
                                       DEFAULT_CONDITIONAL_COMMENT,
                                       bBundlable);
  }

  @Nonnull
  public static ConstantJSPathProvider createExternal (@Nonnull @Nonempty final String sURI)
  {
    return createExternal (sURI, DEFAULT_CONDITIONAL_COMMENT);
  }

  @Nonnull
  public static ConstantJSPathProvider createExternal (@Nonnull @Nonempty final String sURI,
                                                       @Nullable final String sConditionalComment)
  {
    // External JS are never bundleable
    return new ConstantJSPathProvider (sURI, sURI, sConditionalComment, false);
  }
}
