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
package com.helger.html.resource.js;

import com.helger.annotation.Nonempty;
import com.helger.base.builder.IBuilder;
import com.helger.base.enforce.ValueEnforcer;
import com.helger.base.equals.EqualsHelper;
import com.helger.base.hashcode.HashCodeGenerator;
import com.helger.base.tostring.ToStringGenerator;
import com.helger.html.hc.html.script.EHCScriptLoadingMode;
import com.helger.html.js.JSFilenameHelper;

import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;

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
  private final EHCScriptLoadingMode m_eScriptLoadingMode;

  protected ConstantJSPathProvider (@Nonnull @Nonempty final String sPath,
                                    @Nonnull @Nonempty final String sMinifiedPath,
                                    @Nullable final String sConditionalComment,
                                    final boolean bIsBundlable,
                                    @Nonnull final EHCScriptLoadingMode eScriptLoadingMode)
  {
    ValueEnforcer.notEmpty (sPath, "Path");
    ValueEnforcer.isTrue (JSFilenameHelper.isJSFilename (sPath), () -> "'" + sPath + "' is not a valid JS filename");
    ValueEnforcer.notEmpty (sMinifiedPath, "MinifiedPath");
    ValueEnforcer.isTrue (JSFilenameHelper.isJSFilename (sMinifiedPath),
                          () -> "'" + sMinifiedPath + "' is not a valid minified JS filename");
    ValueEnforcer.notNull (eScriptLoadingMode, "ScriptLoadingMode");
    m_sPath = sPath;
    m_sMinifiedPath = sMinifiedPath;
    m_sConditionalComment = sConditionalComment;
    m_bIsBundlable = bIsBundlable;
    m_eScriptLoadingMode = eScriptLoadingMode;
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

  @Nonnull
  public EHCScriptLoadingMode getScriptLoadingMode ()
  {
    return m_eScriptLoadingMode;
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
           m_bIsBundlable == rhs.m_bIsBundlable &&
           m_eScriptLoadingMode == rhs.m_eScriptLoadingMode;
  }

  @Override
  public int hashCode ()
  {
    return new HashCodeGenerator (this).append (m_sPath)
                                       .append (m_sMinifiedPath)
                                       .append (m_sConditionalComment)
                                       .append (m_bIsBundlable)
                                       .append (m_eScriptLoadingMode)
                                       .getHashCode ();
  }

  @Override
  public String toString ()
  {
    return new ToStringGenerator (this).append ("path", m_sPath)
                                       .append ("minifiedPath", m_sMinifiedPath)
                                       .appendIfNotNull ("conditionalComment", m_sConditionalComment)
                                       .append ("isBundlable", m_bIsBundlable)
                                       .append ("ScriptLoadingMode", m_eScriptLoadingMode)
                                       .getToString ();
  }

  public static final class Builder implements IBuilder <ConstantJSPathProvider>
  {
    private String m_sPath;
    private String m_sMinifiedPath;
    private String m_sConditionalComment = DEFAULT_CONDITIONAL_COMMENT;
    private boolean m_bIsBundlable = DEFAULT_IS_BUNDLABLE;
    private EHCScriptLoadingMode m_eScriptLoadingMode = EHCScriptLoadingMode.DEFAULT;

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
      return minifiedPath (JSFilenameHelper.getMinifiedJSFilename (m_sPath));
    }

    @Nonnull
    public Builder conditionalComment (@Nullable final String s)
    {
      m_sConditionalComment = s;
      return this;
    }

    @Nonnull
    public Builder bundlable (final boolean b)
    {
      m_bIsBundlable = b;
      return this;
    }

    @Nonnull
    public Builder scriptLoadingMode (@Nullable final EHCScriptLoadingMode e)
    {
      m_eScriptLoadingMode = e;
      return this;
    }

    @Nonnull
    public Builder scriptLoadingAsync ()
    {
      return scriptLoadingMode (EHCScriptLoadingMode.ASYNC);
    }

    @Nonnull
    public ConstantJSPathProvider build ()
    {
      return new ConstantJSPathProvider (m_sPath,
                                         m_sMinifiedPath,
                                         m_sConditionalComment,
                                         m_bIsBundlable,
                                         m_eScriptLoadingMode);
    }
  }

  @Nonnull
  public static Builder builder ()
  {
    return new Builder ();
  }
}
