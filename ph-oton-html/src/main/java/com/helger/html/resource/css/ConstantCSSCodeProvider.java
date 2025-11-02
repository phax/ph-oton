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

import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.Nullable;

import com.helger.annotation.Nonempty;
import com.helger.annotation.style.ReturnsMutableCopy;
import com.helger.base.enforce.ValueEnforcer;
import com.helger.base.hashcode.HashCodeGenerator;
import com.helger.base.tostring.ToStringGenerator;
import com.helger.css.media.CSSMediaList;
import com.helger.css.media.ICSSMediaList;

/**
 * Implementation of {@link ICSSCodeProvider} with constant values.
 *
 * @author Philip Helger
 */
public final class ConstantCSSCodeProvider implements ICSSCodeProvider
{
  private final String m_sCSSCode;
  private final String m_sConditionalComment;
  private final CSSMediaList m_aCSSMediaList;
  private final boolean m_bIsBundlable;

  public ConstantCSSCodeProvider (@NonNull @Nonempty final String sCSSCode,
                                  @Nullable final String sConditionalComment,
                                  @Nullable final ICSSMediaList aMediaList,
                                  final boolean bIsBundlable)
  {
    ValueEnforcer.notEmpty (sCSSCode, "CSSCode");
    m_sCSSCode = sCSSCode;
    m_sConditionalComment = sConditionalComment;
    m_aCSSMediaList = aMediaList == null ? new CSSMediaList () : new CSSMediaList (aMediaList);
    m_bIsBundlable = bIsBundlable;
  }

  @NonNull
  @Nonempty
  public String getCSSCode ()
  {
    return m_sCSSCode;
  }

  @Nullable
  public String getConditionalComment ()
  {
    return m_sConditionalComment;
  }

  @NonNull
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
    final ConstantCSSCodeProvider rhs = (ConstantCSSCodeProvider) o;
    return m_sCSSCode.equals (rhs.m_sCSSCode) && m_bIsBundlable == rhs.m_bIsBundlable;
  }

  @Override
  public int hashCode ()
  {
    return new HashCodeGenerator (this).append (m_sCSSCode).append (m_bIsBundlable).getHashCode ();
  }

  @Override
  public String toString ()
  {
    return new ToStringGenerator (this).append ("CSSCode", m_sCSSCode)
                                       .appendIfNotNull ("conditionalComment", m_sConditionalComment)
                                       .appendIfNotNull ("CSSMediaList", m_aCSSMediaList)
                                       .append ("isBundlable", m_bIsBundlable)
                                       .getToString ();
  }

  @NonNull
  public static ConstantCSSCodeProvider create (@NonNull @Nonempty final String sCSSCode)
  {
    return new ConstantCSSCodeProvider (sCSSCode, DEFAULT_CONDITIONAL_COMMENT, DEFAULT_CSS_MEDIA_LIST, DEFAULT_IS_BUNDLABLE);
  }

  @NonNull
  public static ConstantCSSCodeProvider createWithConditionalComment (@NonNull @Nonempty final String sCSSCode,
                                                                      @Nullable final String sConditionalComment)
  {
    return createWithConditionalComment (sCSSCode, sConditionalComment, DEFAULT_CSS_MEDIA_LIST);
  }

  @NonNull
  public static ConstantCSSCodeProvider createWithConditionalComment (@NonNull @Nonempty final String sCSSCode,
                                                                      @Nullable final String sConditionalComment,
                                                                      @Nullable final ICSSMediaList aMediaList)
  {
    return new ConstantCSSCodeProvider (sCSSCode, sConditionalComment, aMediaList, DEFAULT_IS_BUNDLABLE);
  }

  @NonNull
  public static ConstantCSSCodeProvider createBundlable (@NonNull @Nonempty final String sCSSCode, final boolean bBundlable)
  {
    return createBundlable (sCSSCode, DEFAULT_CSS_MEDIA_LIST, bBundlable);
  }

  @NonNull
  public static ConstantCSSCodeProvider createBundlable (@NonNull @Nonempty final String sCSSCode,
                                                         @Nullable final ICSSMediaList aMediaList,
                                                         final boolean bBundlable)
  {
    return new ConstantCSSCodeProvider (sCSSCode, DEFAULT_CONDITIONAL_COMMENT, aMediaList, bBundlable);
  }
}
