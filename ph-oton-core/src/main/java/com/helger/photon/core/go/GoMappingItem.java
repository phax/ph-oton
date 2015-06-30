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
package com.helger.photon.core.go;

import javax.annotation.Nonnull;
import javax.annotation.concurrent.Immutable;

import com.helger.commons.ValueEnforcer;
import com.helger.commons.annotation.Nonempty;
import com.helger.commons.annotation.ReturnsMutableCopy;
import com.helger.commons.hashcode.HashCodeGenerator;
import com.helger.commons.string.ToStringGenerator;
import com.helger.commons.url.ISimpleURL;
import com.helger.commons.url.SimpleURL;
import com.helger.photon.core.url.LinkUtils;

/**
 * Default implementation of go-mapping for absolute URLs.
 *
 * @author Philip Helger
 */
@Immutable
public class GoMappingItem
{
  private final String m_sKey;
  private final boolean m_bIsInternal;
  private final ISimpleURL m_aTargetURL;
  private final boolean m_bIsEditable;

  public GoMappingItem (@Nonnull @Nonempty final String sKey,
                        final boolean bIsInternal,
                        @Nonnull @Nonempty final String sTargetURL,
                        final boolean bIsEditable)
  {
    this (sKey,
          bIsInternal,
          bIsInternal ? LinkUtils.getURLWithContext (sTargetURL) : new SimpleURL (sTargetURL),
          bIsEditable);
  }

  public GoMappingItem (@Nonnull @Nonempty final String sKey,
                        final boolean bIsInternal,
                        @Nonnull final ISimpleURL aTargetURL,
                        final boolean bIsEditable)
  {
    m_sKey = ValueEnforcer.notEmpty (sKey, "Key");
    m_bIsInternal = bIsInternal;
    m_aTargetURL = ValueEnforcer.notNull (aTargetURL, "TargetURL");
    m_bIsEditable = bIsEditable;
  }

  /**
   * @return The URL key, under which the item is reachable. May neither be
   *         <code>null</code> nor empty.
   */
  @Nonnull
  @Nonempty
  public String getKey ()
  {
    return m_sKey;
  }

  /**
   * @return <code>true</code> if this is an internal URL, <code>false</code> if
   *         it is an external URL
   */
  public boolean isInternal ()
  {
    return m_bIsInternal;
  }

  /**
   * @return The target URL which should be invoked. May not be
   *         <code>null</code>.
   */
  @Nonnull
  public ISimpleURL getTargetURLReadonly ()
  {
    return m_aTargetURL;
  }

  /**
   * @return The target URL which should be invoked. May not be
   *         <code>null</code>.
   */
  @Nonnull
  @ReturnsMutableCopy
  public SimpleURL getTargetURL ()
  {
    return new SimpleURL (m_aTargetURL);
  }

  /**
   * @return The target URL which should be invoked as a string. May not be
   *         <code>null</code>.
   */
  @Nonnull
  public String getTargetURLAsString ()
  {
    return m_aTargetURL.getAsString ();
  }

  public boolean isEditable ()
  {
    return m_bIsEditable;
  }

  @Nonnull
  public GoMappingItem getAsNotEditable ()
  {
    if (!m_bIsEditable)
      return this;
    return new GoMappingItem (m_sKey, m_bIsInternal, m_aTargetURL, false);
  }

  @Override
  public boolean equals (final Object o)
  {
    if (o == this)
      return true;
    if (o == null || !getClass ().equals (o.getClass ()))
      return false;
    final GoMappingItem rhs = (GoMappingItem) o;
    return m_sKey.equals (rhs.m_sKey) &&
           m_bIsInternal == rhs.m_bIsInternal &&
           m_aTargetURL.equals (rhs.m_aTargetURL) &
           m_bIsEditable == rhs.m_bIsEditable;
  }

  @Override
  public int hashCode ()
  {
    return new HashCodeGenerator (this).append (m_sKey)
                                       .append (m_bIsInternal)
                                       .append (m_aTargetURL)
                                       .append (m_bIsEditable)
                                       .getHashCode ();
  }

  @Override
  @Nonnull
  public String toString ()
  {
    return new ToStringGenerator (this).append ("key", m_sKey)
                                       .append ("isInternal", m_bIsInternal)
                                       .append ("target", m_aTargetURL)
                                       .append ("isEditable", m_bIsEditable)
                                       .toString ();
  }
}
