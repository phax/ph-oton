/**
 * Copyright (C) 2014-2021 Philip Helger (www.helger.com)
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
package com.helger.photon.uicore;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.concurrent.Immutable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.helger.commons.collection.impl.CommonsArrayList;
import com.helger.commons.collection.impl.ICommonsList;
import com.helger.commons.hierarchy.visit.IHierarchyVisitorCallback;
import com.helger.commons.regex.RegExHelper;
import com.helger.commons.string.StringHelper;
import com.helger.html.EHTMLVersion;
import com.helger.html.hc.IHCNode;
import com.helger.html.hc.config.HCSettings;
import com.helger.html.hc.html.grouping.HCDiv;
import com.helger.html.hc.html.grouping.HCP;
import com.helger.html.hc.impl.HCDOMWrapper;
import com.helger.html.hc.impl.HCNodeList;
import com.helger.html.hc.impl.HCTextNode;
import com.helger.html.markdown.MarkdownConfiguration;
import com.helger.html.markdown.MarkdownProcessor;
import com.helger.html.parser.XHTMLParser;
import com.helger.photon.uicore.page.external.PageViewExternalHTMLCleanser;
import com.helger.xml.microdom.IMicroContainer;
import com.helger.xml.microdom.IMicroNode;
import com.helger.xml.microdom.util.MicroVisitor;

@Immutable
public final class UITextFormatter
{
  private static final Logger LOGGER = LoggerFactory.getLogger (UITextFormatter.class);
  private static final MarkdownProcessor MARKDOWN_PROC = new MarkdownProcessor (MarkdownConfiguration.DEFAULT_EXTENSIONS);

  private UITextFormatter ()
  {}

  /**
   * Try to separate the value created from regular <code>toString</code>
   * implementations back into fields :) This is more of a heuristic.
   *
   * @param aValue
   *        The source object which's <code>toString</code> method is invoked.
   *        May be <code>null</code>.
   * @return A slightly beautified version.
   */
  @Nonnull
  public static IHCNode getToStringContent (final Object aValue)
  {
    final String sOrigValue = String.valueOf (aValue);
    if (StringHelper.startsWith (sOrigValue, '[') && StringHelper.endsWith (sOrigValue, ']'))
    {
      try
      {
        final ICommonsList <String> aParts = new CommonsArrayList <> ();
        String sValue = sOrigValue.substring (1, sOrigValue.length () - 1);

        final String [] aObjStart = RegExHelper.getAllMatchingGroupValues ("([\\[]*)([A-Za-z0-9_$]+@0x[0-9a-fA-F]{8})(?:: (.+))?", sValue);
        aParts.add (aObjStart[1]);
        if (aObjStart[2] != null)
        {
          sValue = StringHelper.getConcatenatedOnDemand (aObjStart[0], aObjStart[2]).trim ();
          if (sValue.length () > 0)
          {
            sValue = StringHelper.replaceAll (sValue, "; ", ";\n");
            aParts.addAll (StringHelper.getExploded ('\n', sValue));
          }
        }

        final HCNodeList ret = new HCNodeList ();
        for (final String s : aParts)
          ret.addChild (new HCDiv ().addChild (s));
        return ret;
      }
      catch (final Exception ex)
      {
        LOGGER.error ("Failed to format", ex);
      }
    }
    return new HCTextNode (sOrigValue);
  }

  /**
   * Process the provided String as Markdown and return the created IHCNode.
   *
   * @param sMD
   *        The Markdown source to be invoked. May be <code>null</code>.
   * @return Either the processed markdown code or in case of an internal error
   *         a {@link HCTextNode} which contains the source text.
   */
  @Nonnull
  public static IHCNode markdown (@Nullable final String sMD)
  {
    try
    {
      final HCNodeList aNL = MARKDOWN_PROC.process (sMD).getNodeList ();

      // Replace a single <p> element with its contents
      if (aNL.getChildCount () == 1 && aNL.getChildAtIndex (0) instanceof HCP)
        return ((HCP) aNL.getChildAtIndex (0)).getAllChildrenAsNodeList ();

      return aNL;
    }
    catch (final Exception ex)
    {
      LOGGER.warn ("Failed to markdown '" + sMD + "': " + ex.getMessage ());
      return new HCTextNode (sMD);
    }
  }

  /**
   * Same as {@link #markdown(String)} but returning <code>null</code> if the
   * passed string is <code>null</code> or empty.
   *
   * @param sMD
   *        The Markdown source to be invoked. May be <code>null</code>.
   * @return <code>null</code> if the parameter is <code>null</code> or empty.
   * @see #markdown(String)
   */
  @Nullable
  public static IHCNode markdownOnDemand (@Nullable final String sMD)
  {
    return StringHelper.hasText (sMD) ? markdown (sMD) : null;
  }

  /**
   * Parse the provided HTML string and convert it to an {@link IHCNode}. After
   * parsing the code is cleaned up using {@link PageViewExternalHTMLCleanser}.
   *
   * @param sHTML
   *        The source HTML. May not be <code>null</code>.
   * @return The parsed HTML code as an {@link IHCNode}
   * @throws IllegalStateException
   *         If parsing fails
   */
  @Nonnull
  public static IHCNode unescapeHTML (@Nonnull final String sHTML)
  {
    // Do standard cleansing (setting the correct namespace URI etc.)
    return unescapeHTML (sHTML, new PageViewExternalHTMLCleanser (HCSettings.getConversionSettings ().getHTMLVersion ()));
  }

  /**
   * Parse the provided HTML string and convert it to an {@link IHCNode}. If
   * necessary a separate cleanup can be performed using the provided handler.
   *
   * @param sHTML
   *        The source HTML. May not be <code>null</code>.
   * @param aCleanupHandler
   *        An optional cleanup handler that is invoked if parsing succeeded.
   *        May be <code>null</code>.
   * @return The parsed HTML code as an {@link IHCNode}
   * @throws IllegalStateException
   *         If parsing fails
   */
  @Nonnull
  public static IHCNode unescapeHTML (@Nonnull final String sHTML,
                                      @Nullable final IHierarchyVisitorCallback <? super IMicroNode> aCleanupHandler)
  {
    // Parse content with a non-HTML5 parser because entity resolving would fail
    // otherwise
    final XHTMLParser aXHTMLParser = new XHTMLParser (EHTMLVersion.XHTML11);
    final IMicroContainer ret = aXHTMLParser.unescapeXHTMLFragment (sHTML);
    if (ret == null)
      throw new IllegalStateException ("Failed to parse HTML code: " + sHTML);

    if (aCleanupHandler != null)
      MicroVisitor.visit (ret, aCleanupHandler);

    // Convert micro container to IHCNode
    return new HCDOMWrapper (ret);
  }
}
