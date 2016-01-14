/**
 * Copyright (C) 2014-2016 Philip Helger (www.helger.com)
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
package com.helger.photon.core.app.layout;

import java.util.List;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.helger.commons.ValueEnforcer;
import com.helger.commons.annotation.Nonempty;
import com.helger.commons.annotation.OverrideOnDemand;
import com.helger.commons.annotation.ReturnsMutableCopy;
import com.helger.commons.collection.CollectionHelper;
import com.helger.commons.string.ToStringGenerator;
import com.helger.html.css.DefaultCSSClassProvider;
import com.helger.html.css.ICSSClassProvider;
import com.helger.html.hc.IHCNode;
import com.helger.html.hc.html.metadata.HCHead;
import com.helger.html.hc.html.root.HCHtml;
import com.helger.html.hc.html.sections.HCBody;
import com.helger.html.hc.html.textlevel.HCSpan;
import com.helger.photon.basic.app.request.ApplicationRequestManager;
import com.helger.photon.basic.app.request.IRequestManager;
import com.helger.photon.core.app.context.ILayoutExecutionContext;
import com.helger.photon.core.app.context.ISimpleWebExecutionContext;
import com.helger.photon.core.app.error.InternalErrorBuilder;
import com.helger.photon.core.app.html.AbstractHTMLProvider;
import com.helger.photon.core.app.redirect.ForcedRedirectException;

/**
 * Abstract class for create layouts based on certain areas.
 *
 * @author Philip Helger
 * @param <LECTYPE>
 *        Layout execution context type
 */
public abstract class AbstractLayoutHTMLProvider <LECTYPE extends ILayoutExecutionContext> extends AbstractHTMLProvider
{
  public static final boolean DEFAULT_CREATE_LAYOUT_AREA_SPAN = true;

  /** CSS class for each layout area - mainly for debugging */
  public static final ICSSClassProvider CSS_CLASS_LAYOUT_AREA = DefaultCSSClassProvider.create ("layout_area");

  private final List <String> m_aLayoutAreaIDs;
  private boolean m_bCreateLayoutAreaSpan = DEFAULT_CREATE_LAYOUT_AREA_SPAN;

  public AbstractLayoutHTMLProvider (@Nonnull @Nonempty final List <String> aLayoutAreaIDs)
  {
    ValueEnforcer.notEmpty (aLayoutAreaIDs, "LayoutAreaIDs");

    m_aLayoutAreaIDs = CollectionHelper.newList (aLayoutAreaIDs);
  }

  /**
   * @return A non-<code>null</code> , non-empty list of all contained layout
   *         area IDs.
   */
  @Nonnull
  @Nonempty
  @ReturnsMutableCopy
  public final List <String> getAllLayoutAreaIDs ()
  {
    return CollectionHelper.newList (m_aLayoutAreaIDs);
  }

  /**
   * @return <code>true</code> if a span surrounding all layout elements should
   *         be created, <code>false</code> if created layout elements should
   *         directly be attached to the HTML body. Default value is
   *         {@link #DEFAULT_CREATE_LAYOUT_AREA_SPAN}.
   */
  public boolean isCreateLayoutAreaSpan ()
  {
    return m_bCreateLayoutAreaSpan;
  }

  /**
   * @param bCreateLayoutAreaSpan
   *        <code>true</code> if a span surrounding all layout elements should
   *        be created, <code>false</code> if not.
   * @return this
   */
  @Nonnull
  public AbstractLayoutHTMLProvider <LECTYPE> setCreateLayoutAreaSpan (final boolean bCreateLayoutAreaSpan)
  {
    m_bCreateLayoutAreaSpan = bCreateLayoutAreaSpan;
    return this;
  }

  @Nonnull
  protected abstract LECTYPE createLayoutExecutionContext (@Nonnull ISimpleWebExecutionContext aSWEC,
                                                           @Nonnull IRequestManager aRequestManager);

  /**
   * Overridable method that is called before the content areas are rendered
   *
   * @param aLEC
   *        The layout execution context to use. Never <code>null</code>.
   * @param aHtml
   *        HTML element
   */
  @OverrideOnDemand
  protected void prepareBodyBeforeAreas (@Nonnull final LECTYPE aLEC, @Nonnull final HCHtml aHtml)
  {}

  /**
   * Determine the content of a single area.
   *
   * @param sAreaID
   *        The area ID to be rendered.
   * @param aLEC
   *        The layout execution context to use. Never <code>null</code>.
   * @param aHead
   *        The HC head element. E.g. for filling custom meta elements.
   * @return The node to be rendered for the passed layout area. May be
   *         <code>null</code>.
   */
  @Nullable
  protected abstract IHCNode getContentOfArea (@Nonnull String sAreaID, @Nonnull LECTYPE aLEC, @Nonnull HCHead aHead);

  /**
   * Overridable method that is called after the content areas are rendered
   *
   * @param aLEC
   *        Layout execution context
   * @param aHtml
   *        HTML element
   */
  @OverrideOnDemand
  protected void prepareBodyAfterAreas (@Nonnull final LECTYPE aLEC, @Nonnull final HCHtml aHtml)
  {}

  @Override
  protected void fillBody (@Nonnull final ISimpleWebExecutionContext aSWEC,
                           @Nonnull final HCHtml aHtml) throws ForcedRedirectException
  {
    final LECTYPE aLEC = createLayoutExecutionContext (aSWEC, ApplicationRequestManager.getRequestMgr ());

    // create the default layout and fill the areas
    final HCHead aHead = aHtml.getHead ();
    final HCBody aBody = aHtml.getBody ();

    // Before callback
    prepareBodyBeforeAreas (aLEC, aHtml);

    // For all layout areas
    for (final String sAreaID : m_aLayoutAreaIDs)
    {
      try
      {
        final IHCNode aContent = getContentOfArea (sAreaID, aLEC, aHead);
        if (m_bCreateLayoutAreaSpan)
        {
          // Create a span around the context
          final HCSpan aSpan = new HCSpan ().addClass (CSS_CLASS_LAYOUT_AREA).setID (sAreaID);
          aSpan.addChild (aContent);
          // Append to body if no error occurred
          aBody.addChild (aSpan);
        }
        else
          aBody.addChild (aContent);
      }
      catch (final Throwable t)
      {
        // Special exception ignored here
        if (t instanceof ForcedRedirectException)
          throw (ForcedRedirectException) t;

        // send internal error mail here
        new InternalErrorBuilder ().setUIErrorHandlerFor (aBody)
                                   .setThrowable (t)
                                   .addCustomData ("areaid", sAreaID)
                                   .setFromWebExecutionContext (aLEC)
                                   .handle ();
      }
    }

    // After callback
    prepareBodyAfterAreas (aLEC, aHtml);
  }

  @Override
  public String toString ()
  {
    return new ToStringGenerator (this).append ("layoutAreaIDs", m_aLayoutAreaIDs)
                                       .append ("createLayoutAreaSpan", m_bCreateLayoutAreaSpan)
                                       .toString ();
  }
}
