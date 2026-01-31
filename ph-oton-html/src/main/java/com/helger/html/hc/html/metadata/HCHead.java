/*
 * Copyright (C) 2014-2026 Philip Helger (www.helger.com)
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
package com.helger.html.hc.html.metadata;

import java.util.List;

import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.Nullable;

import com.helger.annotation.Nonnegative;
import com.helger.annotation.style.OverrideOnDemand;
import com.helger.annotation.style.ReturnsImmutableObject;
import com.helger.annotation.style.ReturnsMutableCopy;
import com.helger.annotation.style.ReturnsMutableObject;
import com.helger.base.enforce.ValueEnforcer;
import com.helger.base.state.EChange;
import com.helger.base.string.StringHelper;
import com.helger.base.tostring.ToStringGenerator;
import com.helger.collection.commons.CommonsArrayList;
import com.helger.collection.commons.ICommonsIterable;
import com.helger.collection.commons.ICommonsList;
import com.helger.html.CHTMLAttributes;
import com.helger.html.EHTMLElement;
import com.helger.html.hc.IHCConversionSettingsToNode;
import com.helger.html.hc.IHCNode;
import com.helger.html.hc.config.HCConsistencyChecker;
import com.helger.html.hc.html.AbstractHCElement;
import com.helger.html.hc.html.HC_Target;
import com.helger.html.hc.html.script.HCJSNodeDetector;
import com.helger.mime.CMimeType;
import com.helger.url.ISimpleURL;
import com.helger.xml.microdom.IMicroElement;

/**
 * Represents an HTML &lt;head&gt; element
 *
 * @author Philip Helger
 */
public class HCHead extends AbstractHCElement <HCHead>
{
  private String m_sProfile;
  private final HCTitle m_aPageTitle = new HCTitle ();
  private final HCBase m_aBase = new HCBase ();
  private final ICommonsList <HCMeta> m_aMetaElements = new CommonsArrayList <> ();
  private final ICommonsList <HCLink> m_aLinks = new CommonsArrayList <> ();
  private final ICommonsList <IHCNode> m_aCSS = new CommonsArrayList <> ();
  private final ICommonsList <IHCNode> m_aJS = new CommonsArrayList <> ();

  public HCHead ()
  {
    super (EHTMLElement.HEAD);
  }

  //
  // Head fields/attributes
  //

  @Nullable
  public final String getProfile ()
  {
    return m_sProfile;
  }

  @NonNull
  public final HCHead setProfile (@Nullable final String sProfile)
  {
    m_sProfile = sProfile;
    return this;
  }

  @Nullable
  public final String getPageTitle ()
  {
    return m_aPageTitle.getContent ();
  }

  @NonNull
  public final HCHead setPageTitle (@Nullable final String sPageTitle)
  {
    m_aPageTitle.setContent (sPageTitle);
    return this;
  }

  @Nullable
  public final ISimpleURL getBaseHref ()
  {
    return m_aBase.getHref ();
  }

  @NonNull
  public final HCHead setBaseHref (@Nullable final ISimpleURL aBaseHref)
  {
    m_aBase.setHref (aBaseHref);
    return this;
  }

  @Nullable
  public final HC_Target getBaseTarget ()
  {
    return m_aBase.getTarget ();
  }

  @NonNull
  public final HCHead setBaseTarget (@Nullable final HC_Target aTarget)
  {
    m_aBase.setTarget (aTarget);
    return this;
  }

  //
  // Meta elements
  //

  /**
   * @return meta element list. Never <code>null</code>.
   */
  @NonNull
  @ReturnsMutableObject
  public final ICommonsList <HCMeta> metaElements ()
  {
    return m_aMetaElements;
  }

  @NonNull
  public final EChange removeMetaElement (@Nullable final String sName)
  {
    if (StringHelper.isNotEmpty (sName))
      return EChange.valueOf (m_aMetaElements.removeIf (x -> sName.equals (x.getName ()) ||
                                                             sName.equals (x.getHttpEquiv ())));
    return EChange.UNCHANGED;
  }

  //
  // Link handling
  //
  /**
   * @return linkmeta element list. Never <code>null</code>.
   */
  @NonNull
  @ReturnsMutableObject
  public final ICommonsList <HCLink> links ()
  {
    return m_aLinks;
  }

  @NonNull
  public final HCHead setShortcutIconHref (@Nullable final ISimpleURL aShortcutIconHref)
  {
    if (aShortcutIconHref == null)
    {
      // Remove the 2 link types again
      m_aLinks.removeIf (x -> x.getRel ().equals (EHCLinkType.SHORTCUT_ICON) || x.getRel ().equals (EHCLinkType.ICON));
    }
    else
    {
      m_aLinks.add (new HCLink ().setRel (EHCLinkType.SHORTCUT_ICON).setHref (aShortcutIconHref));
      // Required for IE:
      m_aLinks.add (new HCLink ().setRel (EHCLinkType.ICON).setType (CMimeType.IMAGE_ICON).setHref (aShortcutIconHref));
    }
    return this;
  }

  //
  // CSS handling
  //

  @NonNull
  public final HCHead addCSS (@NonNull final IHCNode aCSS)
  {
    ValueEnforcer.notNull (aCSS, "CSS");
    if (!HCCSSNodeDetector.isCSSNode (aCSS))
      throw new IllegalArgumentException (aCSS + " is not a valid CSS node!");
    m_aCSS.add (aCSS);
    return this;
  }

  /**
   * Add a CSS node at the specified index.
   *
   * @param nIndex
   *        The index to add. Should be &ge; 0.
   * @param aCSS
   *        The CSS node to be added. May not be <code>null</code>.
   * @return this for chaining
   */
  @NonNull
  public final HCHead addCSSAt (@Nonnegative final int nIndex, @NonNull final IHCNode aCSS)
  {
    ValueEnforcer.notNull (aCSS, "CSS");
    if (!HCCSSNodeDetector.isCSSNode (aCSS))
      throw new IllegalArgumentException (aCSS + " is not a valid CSS node!");
    m_aCSS.add (nIndex, aCSS);
    return this;
  }

  @Nonnegative
  public final int getCSSCount ()
  {
    return m_aCSS.size ();
  }

  @NonNull
  @ReturnsMutableObject
  public final ICommonsList <IHCNode> cssNodes ()
  {
    return m_aCSS;
  }

  @NonNull
  @ReturnsImmutableObject
  public final ICommonsIterable <IHCNode> getCSSNodes ()
  {
    return m_aCSS;
  }

  @NonNull
  @ReturnsMutableCopy
  public final ICommonsList <IHCNode> getAllCSSNodes ()
  {
    return m_aCSS.getClone ();
  }

  public final void getAllAndRemoveAllCSSNodes (@NonNull final List <IHCNode> aTargetList)
  {
    ValueEnforcer.notNull (aTargetList, "TargetList");
    aTargetList.addAll (m_aCSS);
    m_aCSS.clear ();
  }

  @NonNull
  public final HCHead removeAllCSS ()
  {
    m_aCSS.clear ();
    return this;
  }

  //
  // JS handling
  //

  /**
   * Append some JavaScript code
   *
   * @param aJS
   *        The JS to be added. May not be <code>null</code>.
   * @return this
   */
  @NonNull
  public final HCHead addJS (@NonNull final IHCNode aJS)
  {
    ValueEnforcer.notNull (aJS, "JS");
    if (!HCJSNodeDetector.isJSNode (aJS))
      throw new IllegalArgumentException (aJS + " is not a valid JS node!");
    m_aJS.add (aJS);
    return this;
  }

  /**
   * Append some JavaScript code at the specified index
   *
   * @param nIndex
   *        The index where the JS should be added (counting only JS elements)
   * @param aJS
   *        The JS to be added. May not be <code>null</code>.
   * @return this
   */
  @NonNull
  public final HCHead addJSAt (@Nonnegative final int nIndex, @NonNull final IHCNode aJS)
  {
    ValueEnforcer.notNull (aJS, "JS");
    if (!HCJSNodeDetector.isJSNode (aJS))
      throw new IllegalArgumentException (aJS + " is not a valid JS node!");
    m_aJS.add (nIndex, aJS);
    return this;
  }

  /**
   * @return The number of contained JS elements
   */
  @Nonnegative
  public final int getJSCount ()
  {
    return m_aJS.size ();
  }

  @NonNull
  @ReturnsMutableObject
  public final ICommonsList <IHCNode> jsNodes ()
  {
    return m_aJS;
  }

  @NonNull
  @ReturnsImmutableObject
  public final ICommonsIterable <IHCNode> getJSNodes ()
  {
    return m_aJS;
  }

  @NonNull
  @ReturnsMutableCopy
  public final ICommonsList <IHCNode> getAllJSNodes ()
  {
    return m_aJS.getClone ();
  }

  public final void getAllAndRemoveAllJSNodes (@NonNull final List <IHCNode> aTargetList)
  {
    ValueEnforcer.notNull (aTargetList, "TargetList");
    aTargetList.addAll (m_aJS);
    m_aJS.clear ();
  }

  @NonNull
  public final HCHead removeAllJS ()
  {
    m_aJS.clear ();
    return this;
  }

  //
  // Code generation
  //

  @OverrideOnDemand
  protected void emitLinks (@NonNull final IMicroElement eHead,
                            @NonNull final IHCConversionSettingsToNode aConversionSettings)
  {
    for (final HCLink aLink : m_aLinks)
      eHead.addChild (aLink.convertToMicroNode (aConversionSettings));
  }

  @OverrideOnDemand
  protected void emitCSS (@NonNull final IMicroElement eHead,
                          @NonNull final IHCConversionSettingsToNode aConversionSettings)
  {
    int nCSSExternals = 0;
    for (final IHCNode aCSS : m_aCSS)
    {
      if (aCSS instanceof HCLink)
        ++nCSSExternals;
      eHead.addChild (aCSS.convertToMicroNode (aConversionSettings));
    }

    if (aConversionSettings.areConsistencyChecksEnabled ())
    {
      // This check must be done here because not all elements where available
      // at the time of regular consistency checking
      HCConsistencyChecker.checkForMaximumCSSResources (nCSSExternals);
    }
  }

  @OverrideOnDemand
  protected void emitJS (@NonNull final IMicroElement eHead,
                         @NonNull final IHCConversionSettingsToNode aConversionSettings)
  {
    for (final IHCNode aJS : m_aJS)
      eHead.addChild (aJS.convertToMicroNode (aConversionSettings));
  }

  @Override
  protected void fillMicroElement (@NonNull final IMicroElement eHead,
                                   @NonNull final IHCConversionSettingsToNode aConversionSettings)
  {
    super.fillMicroElement (eHead, aConversionSettings);

    if (StringHelper.isNotEmpty (m_sProfile))
      eHead.setAttribute (CHTMLAttributes.PROFILE, m_sProfile);

    // Append meta element first for charset encoding!
    for (final HCMeta aMetaElement : m_aMetaElements)
      eHead.addChild (aMetaElement.convertToMicroNode (aConversionSettings));

    // page title
    eHead.addChild (m_aPageTitle.convertToMicroNode (aConversionSettings));

    // base
    eHead.addChild (m_aBase.convertToMicroNode (aConversionSettings));

    // links
    emitLinks (eHead, aConversionSettings);

    // CSS stuff
    emitCSS (eHead, aConversionSettings);

    // JS files
    emitJS (eHead, aConversionSettings);

    // Ensure tag is not self-closed
    if (!eHead.hasChildren () && EHTMLElement.HEAD.mayNotBeSelfClosed ())
      eHead.addText ("");
  }

  @Override
  @NonNull
  public String getPlainText ()
  {
    // Use the page title as plain text
    return m_aPageTitle.getPlainText ();
  }

  @Override
  @NonNull
  @ReturnsMutableCopy
  public final ICommonsList <IHCNode> getAllChildren ()
  {
    final ICommonsList <IHCNode> ret = new CommonsArrayList <> ();
    ret.add (m_aPageTitle);
    ret.add (m_aBase);
    ret.addAll (m_aMetaElements);
    ret.addAll (m_aLinks);
    ret.addAll (m_aCSS);
    ret.addAll (m_aJS);
    return ret;
  }

  @Override
  @NonNull
  public final ICommonsIterable <IHCNode> getChildren ()
  {
    return getAllChildren ();
  }

  @Override
  @Nullable
  public final IHCNode getChildAtIndex (@Nonnegative final int nIndex)
  {
    if (nIndex == 0)
      return m_aPageTitle;
    if (nIndex == 1)
      return m_aBase;

    int nStart = 2;
    int nEnd = nStart + m_aMetaElements.size ();
    if (nIndex >= nStart && nIndex < nEnd)
      return m_aMetaElements.getAtIndex (nIndex - nStart);

    nStart = nEnd;
    nEnd = nStart + m_aLinks.size ();
    if (nIndex >= nStart && nIndex < nEnd)
      return m_aLinks.getAtIndex (nIndex - nStart);

    nStart = nEnd;
    nEnd = nStart + m_aCSS.size ();
    if (nIndex >= nStart && nIndex < nEnd)
      return m_aCSS.getAtIndex (nIndex - nStart);

    nStart = nEnd;
    nEnd = nStart + m_aJS.size ();
    if (nIndex >= nStart && nIndex < nEnd)
      return m_aJS.getAtIndex (nIndex - nStart);

    return null;
  }

  @Override
  @Nullable
  public final IHCNode getFirstChild ()
  {
    return m_aPageTitle;
  }

  @Override
  @Nullable
  public final IHCNode getLastChild ()
  {
    if (m_aJS.isNotEmpty ())
      return m_aJS.getLastOrNull ();
    if (m_aCSS.isNotEmpty ())
      return m_aCSS.getLastOrNull ();
    if (m_aLinks.isNotEmpty ())
      return m_aLinks.getLastOrNull ();
    if (m_aMetaElements.isNotEmpty ())
      return m_aMetaElements.getLastOrNull ();
    return m_aBase;
  }

  @Override
  public final boolean hasChildren ()
  {
    return true;
  }

  @Override
  public final int getChildCount ()
  {
    return 1 + 1 + m_aMetaElements.size () + m_aLinks.size () + m_aCSS.size () + m_aJS.size ();
  }

  @Override
  public String toString ()
  {
    return ToStringGenerator.getDerived (super.toString ())
                            .appendIfNotNull ("profile", m_sProfile)
                            .append ("pageTitle", m_aPageTitle)
                            .append ("base", m_aBase)
                            .append ("metaElements", m_aMetaElements)
                            .append ("links", m_aLinks)
                            .append ("CSS", m_aCSS)
                            .append ("JS", m_aJS)
                            .getToString ();
  }
}
