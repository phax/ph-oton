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
package com.helger.photon.core.resource;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.concurrent.Immutable;

import com.helger.commons.ValueEnforcer;
import com.helger.commons.annotation.Nonempty;
import com.helger.commons.annotation.ReturnsMutableCopy;
import com.helger.commons.collection.CollectionHelper;
import com.helger.commons.equals.EqualsHelper;
import com.helger.commons.hashcode.HashCodeGenerator;
import com.helger.commons.mime.IMimeType;
import com.helger.commons.string.StringHelper;
import com.helger.commons.string.ToStringGenerator;
import com.helger.css.media.CSSMediaList;
import com.helger.css.media.ICSSMediaList;
import com.helger.html.hcapi.IHCNode;
import com.helger.html.hchtml.HCConditionalCommentNode;

/**
 * This is a bundle of 1-n {@link WebSiteResource} objects that share the same
 * conditional comment and can be bundled with each other.
 *
 * @author Philip Helger
 */
@Immutable
public class WebSiteResourceBundle
{
  private final List <WebSiteResource> m_aResources;
  private final String m_sConditionalComment;
  private final boolean m_bCanBeBundled;
  private final CSSMediaList m_aMediaList;
  // Status vars
  private final EWebSiteResourceType m_eResourceType;
  private Integer m_aHashCode;

  public WebSiteResourceBundle (@Nonnull @Nonempty final List <WebSiteResourceWithCondition> aResources,
                                @Nullable final String sConditionalComment,
                                final boolean bCanBeBundled,
                                @Nullable final ICSSMediaList aMediaList)
  {
    ValueEnforcer.notEmptyNoNullValue (aResources, "Resources");
    m_aResources = new ArrayList <> ();
    for (final WebSiteResourceWithCondition aResourceWithCond : aResources)
      m_aResources.add (aResourceWithCond.getResource ());
    m_sConditionalComment = sConditionalComment;
    m_bCanBeBundled = bCanBeBundled;
    m_aMediaList = aMediaList == null || aMediaList.hasNoMedia () ? null : new CSSMediaList (aMediaList);
    m_eResourceType = aResources.get (0).getResource ().getResourceType ();

    // Consistency check
    for (final WebSiteResourceWithCondition aResource : aResources)
      if (!aResource.getResource ().getResourceType ().equals (m_eResourceType))
        throw new IllegalArgumentException ("The passed resources are mixed of different resource types: " +
                                            aResources);
  }

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
  public List <WebSiteResource> getAllResources ()
  {
    return CollectionHelper.newList (m_aResources);
  }

  @Nullable
  public WebSiteResource getResourceAtIndex (@Nonnegative final int nIndex)
  {
    return CollectionHelper.getSafe (m_aResources, nIndex);
  }

  @Nonnull
  @Nonempty
  @ReturnsMutableCopy
  public List <String> getAllResourcePaths ()
  {
    final List <String> ret = new ArrayList <String> ();
    for (final WebSiteResource aResource : m_aResources)
      ret.add (aResource.getPath ());
    return ret;
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

  public boolean canBeBundled ()
  {
    return m_bCanBeBundled;
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
           m_bCanBeBundled == rhs.m_bCanBeBundled &&
           EqualsHelper.equals (m_aMediaList, rhs.m_aMediaList);
  }

  @Override
  public int hashCode ()
  {
    if (m_aHashCode == null)
      m_aHashCode = new HashCodeGenerator (this).append (m_aResources)
                                                .append (m_sConditionalComment)
                                                .append (m_bCanBeBundled)
                                                .append (m_aMediaList)
                                                .getHashCodeObj ();
    return m_aHashCode.intValue ();
  }

  @Override
  public String toString ()
  {
    return new ToStringGenerator (this).append ("resources", m_aResources)
                                       .appendIfNotNull ("ConditionalComment", m_sConditionalComment)
                                       .append ("CanBeBundled", m_bCanBeBundled)
                                       .appendIfNotNull ("MediaList", m_aMediaList)
                                       .toString ();
  }
}
