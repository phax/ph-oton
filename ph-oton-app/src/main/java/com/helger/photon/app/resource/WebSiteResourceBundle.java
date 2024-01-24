/*
 * Copyright (C) 2014-2024 Philip Helger (www.helger.com)
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

import java.util.List;

import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.concurrent.Immutable;

import com.helger.commons.ValueEnforcer;
import com.helger.commons.annotation.Nonempty;
import com.helger.commons.annotation.ReturnsMutableCopy;
import com.helger.commons.collection.impl.CommonsArrayList;
import com.helger.commons.collection.impl.ICommonsList;
import com.helger.commons.equals.EqualsHelper;
import com.helger.commons.hashcode.HashCodeGenerator;
import com.helger.commons.hashcode.IHashCodeGenerator;
import com.helger.commons.mime.IMimeType;
import com.helger.commons.string.StringHelper;
import com.helger.commons.string.ToStringGenerator;
import com.helger.css.media.CSSMediaList;
import com.helger.css.media.ICSSMediaList;
import com.helger.html.hc.IHCNode;
import com.helger.html.hc.ext.HCConditionalCommentNode;

/**
 * This is a bundle of 1-n {@link WebSiteResource} objects that share the same
 * conditional comment and can be bundled with each other.
 *
 * @author Philip Helger
 */
@Immutable
public class WebSiteResourceBundle
{
  private final ICommonsList <WebSiteResource> m_aResources = new CommonsArrayList <> ();
  private final String m_sConditionalComment;
  private final boolean m_bIsBundlable;
  private final CSSMediaList m_aMediaList;
  // Status vars
  private final EWebSiteResourceType m_eResourceType;
  private int m_nHashCode = IHashCodeGenerator.ILLEGAL_HASHCODE;

  public WebSiteResourceBundle (@Nonnull @Nonempty final List <WebSiteResourceWithCondition> aResources,
                                @Nullable final String sConditionalComment,
                                final boolean bIsBundlable,
                                @Nullable final ICSSMediaList aMediaList)
  {
    ValueEnforcer.notEmptyNoNullValue (aResources, "Resources");
    m_aResources.addAllMapped (aResources, WebSiteResourceWithCondition::getResource);
    m_sConditionalComment = sConditionalComment;
    m_bIsBundlable = bIsBundlable;
    m_aMediaList = aMediaList == null || aMediaList.hasNoMedia () ? null : new CSSMediaList (aMediaList);
    m_eResourceType = m_aResources.getFirst ().getResourceType ();

    // Consistency check
    for (final WebSiteResourceWithCondition aResource : aResources)
      if (!aResource.getResource ().getResourceType ().equals (m_eResourceType))
        throw new IllegalArgumentException ("The passed resources are mixed of different resource types: " + aResources);
  }

  /**
   * @return The number of contained resources. Always &ge; 1.
   */
  @Nonnegative
  public int getResourceCount ()
  {
    return m_aResources.size ();
  }

  /**
   * @return A list of all bundled resources. Neither <code>null</code> nor
   *         empty.
   */
  @Nonnull
  @Nonempty
  @ReturnsMutableCopy
  public ICommonsList <WebSiteResource> getAllResources ()
  {
    return m_aResources.getClone ();
  }

  @Nullable
  public WebSiteResource getResourceAtIndex (@Nonnegative final int nIndex)
  {
    return m_aResources.getAtIndex (nIndex);
  }

  @Nonnull
  @Nonempty
  @ReturnsMutableCopy
  public ICommonsList <String> getAllResourcePaths ()
  {
    return m_aResources.getAllMapped (WebSiteResource::getPath);
  }

  /**
   * @return <code>true</code> if a conditional comment is present,
   *         <code>false</code> if not.
   */
  public boolean hasConditionalComment ()
  {
    return StringHelper.hasText (m_sConditionalComment);
  }

  /**
   * @return The conditional comment to use or <code>null</code> if no such
   *         conditional comment is present.
   */
  @Nullable
  public String getConditionalComment ()
  {
    return m_sConditionalComment;
  }

  public boolean isBundlable ()
  {
    return m_bIsBundlable;
  }

  @Nullable
  public ICSSMediaList getMediaList ()
  {
    return m_aMediaList;
  }

  public boolean hasMediaList ()
  {
    return m_aMediaList != null;
  }

  @Nonnull
  public EWebSiteResourceType getResourceType ()
  {
    return m_eResourceType;
  }

  @Nonnull
  public IMimeType getMimeType ()
  {
    return m_eResourceType.getMimeType ();
  }

  @Nonnull
  public IHCNode getWrapped (@Nonnull final IHCNode aNode)
  {
    if (hasConditionalComment ())
      return new HCConditionalCommentNode (m_sConditionalComment, aNode);

    // Return as-is
    return aNode;
  }

  @Override
  public boolean equals (final Object o)
  {
    if (o == this)
      return true;
    if (o == null || !getClass ().equals (o.getClass ()))
      return false;
    final WebSiteResourceBundle rhs = (WebSiteResourceBundle) o;
    return m_aResources.equals (rhs.m_aResources) &&
           EqualsHelper.equals (m_sConditionalComment, rhs.m_sConditionalComment) &&
           m_bIsBundlable == rhs.m_bIsBundlable &&
           EqualsHelper.equals (m_aMediaList, rhs.m_aMediaList);
  }

  @Override
  public int hashCode ()
  {
    int ret = m_nHashCode;
    if (ret == IHashCodeGenerator.ILLEGAL_HASHCODE)
      ret = m_nHashCode = new HashCodeGenerator (this).append (m_aResources)
                                                      .append (m_sConditionalComment)
                                                      .append (m_bIsBundlable)
                                                      .append (m_aMediaList)
                                                      .getHashCode ();
    return ret;
  }

  @Override
  public String toString ()
  {
    return new ToStringGenerator (this).append ("Resources", m_aResources)
                                       .appendIfNotNull ("ConditionalComment", m_sConditionalComment)
                                       .append ("IsBundlable", m_bIsBundlable)
                                       .appendIfNotNull ("MediaList", m_aMediaList)
                                       .getToString ();
  }
}
