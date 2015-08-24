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
import com.helger.commons.annotation.Nonempty;
import com.helger.commons.equals.EqualsHelper;
import com.helger.commons.hashcode.HashCodeGenerator;
import com.helger.commons.string.StringHelper;
import com.helger.commons.string.ToStringGenerator;
import com.helger.css.CSSFilenameHelper;
import com.helger.css.media.CSSMediaList;
import com.helger.css.media.ICSSMediaList;
import com.helger.html.js.JSFilenameHelper;
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
  private final boolean m_bIsBundlable;
  private final CSSMediaList m_aMediaList;

  /**
   * Constructor
   *
   * @param eType
   *        Resource type. May not be <code>null</code>.
   * @param sPath
   *        Path to the resource. May neither be <code>null</code> nor empty.
   * @param sConditionalComment
   *        Optional conditional comment.
   * @param bIsBundlable
   *        <code>true</code> if this resource can be bundled. Use
   *        <code>true</code> if you are unsure.
   * @param aMediaList
   *        Optional media list, used only for CSS resources.
   */
  public WebSiteResourceWithCondition (@Nonnull final EWebSiteResourceType eType,
                                       @Nonnull @Nonempty final String sPath,
                                       @Nullable final String sConditionalComment,
                                       final boolean bIsBundlable,
                                       @Nullable final ICSSMediaList aMediaList)
  {
    this (WebSiteResourceCache.getOrCreateResource (eType, sPath), sConditionalComment, bIsBundlable, aMediaList);
  }

  protected WebSiteResourceWithCondition (@Nonnull final WebSiteResource aResource,
                                          @Nullable final String sConditionalComment,
                                          final boolean bIsBundlable,
                                          @Nullable final ICSSMediaList aMediaList)
  {
    m_aResource = ValueEnforcer.notNull (aResource, "Resource");
    m_sConditionalComment = sConditionalComment;
    m_bIsBundlable = bIsBundlable;
    m_aMediaList = aMediaList == null || aMediaList.hasNoMedia () ? null : new CSSMediaList (aMediaList);
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
    if (!m_bIsBundlable || !aOther.isBundlable ())
      return false;

    // Can only bundle resources of the same type
    if (!m_aResource.getResourceType ().equals (aOther.m_aResource.getResourceType ()))
      return false;

    // If the conditional comment is different, items cannot be bundled!
    if (!EqualsHelper.equals (m_sConditionalComment, aOther.m_sConditionalComment))
      return false;

    // If the CSS media list is different, items cannot be bundled!
    if (!EqualsHelper.equals (m_aMediaList, aOther.m_aMediaList))
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
  public boolean isBundlable ()
  {
    return m_bIsBundlable;
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
    return new ConstantCSSPathProvider (m_aResource.getPath (),
                                        CSSFilenameHelper.getMinifiedCSSFilename (m_aResource.getPath ()),
                                        m_sConditionalComment,
                                        m_aMediaList,
                                        m_bIsBundlable);
  }

  @Nonnull
  public ConstantJSPathProvider getAsJSPathProvider ()
  {
    if (m_aResource.getResourceType () != EWebSiteResourceType.JS)
      throw new IllegalStateException ("This can only be performed on a JS resource!");
    return new ConstantJSPathProvider (m_aResource.getPath (),
                                       JSFilenameHelper.getMinifiedJSFilename (m_aResource.getPath ()),
                                       m_sConditionalComment,
                                       m_bIsBundlable);
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
           EqualsHelper.equals (m_sConditionalComment, rhs.m_sConditionalComment) &&
           m_bIsBundlable == rhs.m_bIsBundlable &&
           EqualsHelper.equals (m_aMediaList, rhs.m_aMediaList);
  }

  @Override
  public int hashCode ()
  {
    return new HashCodeGenerator (this).append (m_aResource)
                                       .append (m_sConditionalComment)
                                       .append (m_bIsBundlable)
                                       .append (m_aMediaList)
                                       .getHashCode ();
  }

  @Override
  public String toString ()
  {
    return new ToStringGenerator (this).append ("resource", m_aResource)
                                       .appendIfNotNull ("conditionalComment", m_sConditionalComment)
                                       .append ("isBundlable", m_bIsBundlable)
                                       .appendIfNotNull ("mediaList", m_aMediaList)
                                       .toString ();
  }

  /**
   * Factory method for JavaScript resources.
   *
   * @param aPP
   *        The path provider.
   * @param bRegular
   *        <code>true</code> for regular version, <code>false</code> for the
   *        minified/optimized version.
   * @return New {@link WebSiteResourceWithCondition} object. Never
   *         <code>null</code>.
   */
  @Nonnull
  public static WebSiteResourceWithCondition createForJS (@Nonnull final IJSPathProvider aPP, final boolean bRegular)
  {
    return createForJS (aPP.getJSItemPath (bRegular), aPP.getConditionalComment (), aPP.isBundlable ());
  }

  @Nonnull
  public static WebSiteResourceWithCondition createForJS (@Nonnull @Nonempty final String sPath,
                                                          @Nullable final String sConditionalComment,
                                                          final boolean bIsBundlable)
  {
    return new WebSiteResourceWithCondition (EWebSiteResourceType.JS,
                                             sPath,
                                             sConditionalComment,
                                             bIsBundlable,
                                             (ICSSMediaList) null);
  }

  /**
   * Factory method for CSS resources.
   *
   * @param aPP
   *        The path provider.
   * @param bRegular
   *        <code>true</code> for regular version, <code>false</code> for the
   *        minified/optimized version.
   * @return New {@link WebSiteResourceWithCondition} object. Never
   *         <code>null</code>.
   */
  @Nonnull
  public static WebSiteResourceWithCondition createForCSS (@Nonnull final ICSSPathProvider aPP, final boolean bRegular)
  {
    return createForCSS (aPP.getCSSItemPath (bRegular),
                         aPP.getConditionalComment (),
                         aPP.isBundlable (),
                         aPP.getMediaList ());
  }

  @Nonnull
  public static WebSiteResourceWithCondition createForCSS (@Nonnull @Nonempty final String sPath,
                                                           @Nullable final String sConditionalComment,
                                                           final boolean bIsBundlable,
                                                           @Nullable final ICSSMediaList aMediaList)
  {
    return new WebSiteResourceWithCondition (EWebSiteResourceType.CSS,
                                             sPath,
                                             sConditionalComment,
                                             bIsBundlable,
                                             aMediaList);
  }
}
