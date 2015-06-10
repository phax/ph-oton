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

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.concurrent.Immutable;

import com.helger.commons.ValueEnforcer;
import com.helger.commons.annotations.Nonempty;
import com.helger.commons.equals.EqualsUtils;
import com.helger.commons.hash.HashCodeGenerator;
import com.helger.commons.string.StringHelper;
import com.helger.commons.string.ToStringGenerator;
import com.helger.css.media.CSSMediaList;
import com.helger.css.media.ICSSMediaList;
import com.helger.html.resource.css.ConstantCSSPathProvider;
import com.helger.html.resource.css.ICSSPathProvider;
import com.helger.html.resource.js.ConstantJSPathProvider;
import com.helger.html.resource.js.IJSPathProvider;

/**
 * This encapsulates a {@link WebSiteResource} together with an optional
 * conditional comment.
 *
 * @author Philip Helger
 */
@Immutable
public class WebSiteResourceWithCondition
{
  private final WebSiteResource m_aResource;
  private final String m_sConditionalComment;
  private final boolean m_bCanBeBundled;
  private final CSSMediaList m_aMediaList;

  /**
   * Constructor for JavaScript resources.
   *
   * @param aPP
   *        The path provider.
   * @param bRegular
   *        <code>true</code> for regular version, <code>false</code> for the
   *        minified/optimized version.
   */
  public WebSiteResourceWithCondition (@Nonnull final IJSPathProvider aPP, final boolean bRegular)
  {
    // IWebURIToURLConverter is not needed for JS
    this (EWebSiteResourceType.JS,
          aPP.getJSItemPath (bRegular),
          aPP.getConditionalComment (),
          aPP.canBeBundled (),
          (ICSSMediaList) null);
  }

  /**
   * Constructor for CSS resources.
   *
   * @param aPP
   *        The path provider.
   * @param bRegular
   *        <code>true</code> for regular version, <code>false</code> for the
   *        minified/optimized version.
   */
  public WebSiteResourceWithCondition (@Nonnull final ICSSPathProvider aPP, final boolean bRegular)
  {
    this (EWebSiteResourceType.CSS,
          aPP.getCSSItemPath (bRegular),
          aPP.getConditionalComment (),
          true,
          aPP.getMediaList ());
  }

  private WebSiteResourceWithCondition (@Nonnull final EWebSiteResourceType eType,
                                        @Nonnull @Nonempty final String sPath,
                                        @Nullable final String sConditionalComment,
                                        final boolean bCanBeBundled,
                                        @Nullable final ICSSMediaList aMediaList)
  {
    this (WebSiteResourceCache.getOrCreateResource (eType, sPath), sConditionalComment, bCanBeBundled, aMediaList);
  }

  protected WebSiteResourceWithCondition (@Nonnull final WebSiteResource aResource,
                                          @Nullable final String sConditionalComment,
                                          final boolean bCanBeBundled,
                                          @Nullable final ICSSMediaList aMediaList)
  {
    m_aResource = aResource;
    m_sConditionalComment = sConditionalComment;
    m_bCanBeBundled = bCanBeBundled;
    m_aMediaList = aMediaList == null || aMediaList.isEmpty () ? null : new CSSMediaList (aMediaList);
  }

  @Nonnull
  public WebSiteResource getResource ()
  {
    return m_aResource;
  }

  /**
   * Check if this resource can be bundled with the passed resource.
   *
   * @param aOther
   *        The resource to check against. May not be <code>null</code>.
   * @return <code>true</code> if this resource can be bundled with the passed
   *         resource, <code>false</code> if not.
   */
  public boolean canBeBundledWith (@Nonnull final WebSiteResourceWithCondition aOther)
  {
    ValueEnforcer.notNull (aOther, "Other");

    // Resource cannot be bundled at all
    if (!m_bCanBeBundled || !aOther.canBeBundled ())
      return false;

    // Can only bundle resources of the same type
    if (!m_aResource.getResourceType ().equals (aOther.m_aResource.getResourceType ()))
      return false;

    // If the conditional comment is different, items cannot be bundled!
    if (!EqualsUtils.equals (m_sConditionalComment, aOther.m_sConditionalComment))
      return false;

    // If the CSS media list is different, items cannot be bundled!
    if (!EqualsUtils.equals (m_aMediaList, aOther.m_aMediaList))
      return false;

    // Can be bundled!
    return true;
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

  /**
   * Check if this resource can be bundled, independent of the conditional
   * comment.
   *
   * @return <code>true</code> if this resource can be bundled,
   *         <code>false</code> if not.
   */
  public boolean canBeBundled ()
  {
    return m_bCanBeBundled;
  }

  @Nullable
  public ICSSMediaList getMediaList ()
  {
    return m_aMediaList;
  }

  @Nonnull
  public ConstantCSSPathProvider getAsCSSPathProvider ()
  {
    if (m_aResource.getResourceType () != EWebSiteResourceType.CSS)
      throw new IllegalStateException ("This can only be performed on a CSS resource!");
    return new ConstantCSSPathProvider (m_aResource.getPath (), m_sConditionalComment, m_aMediaList);
  }

  @Nonnull
  public ConstantJSPathProvider getAsJSPathProvider ()
  {
    if (m_aResource.getResourceType () != EWebSiteResourceType.JS)
      throw new IllegalStateException ("This can only be performed on a JS resource!");
    return new ConstantJSPathProvider (m_aResource.getPath (), m_sConditionalComment);
  }

  @Override
  public boolean equals (final Object o)
  {
    if (o == this)
      return true;
    if (o == null || !getClass ().equals (o.getClass ()))
      return false;
    final WebSiteResourceWithCondition rhs = (WebSiteResourceWithCondition) o;
    return m_aResource.equals (rhs.m_aResource) &&
           EqualsUtils.equals (m_sConditionalComment, rhs.m_sConditionalComment) &&
           m_bCanBeBundled == rhs.m_bCanBeBundled &&
           EqualsUtils.equals (m_aMediaList, rhs.m_aMediaList);
  }

  @Override
  public int hashCode ()
  {
    return new HashCodeGenerator (this).append (m_aResource)
                                       .append (m_sConditionalComment)
                                       .append (m_bCanBeBundled)
                                       .append (m_aMediaList)
                                       .getHashCode ();
  }

  @Override
  public String toString ()
  {
    return new ToStringGenerator (this).append ("resource", m_aResource)
                                       .appendIfNotNull ("conditionalComment", m_sConditionalComment)
                                       .append ("canBeBundled", m_bCanBeBundled)
                                       .appendIfNotNull ("mediaList", m_aMediaList)
                                       .toString ();
  }
}
