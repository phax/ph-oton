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
package com.helger.html.hc.htmlext;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.concurrent.Immutable;

import com.helger.commons.ValueEnforcer;
import com.helger.commons.annotations.Nonempty;
import com.helger.commons.annotations.ReturnsMutableCopy;
import com.helger.commons.microdom.IMicroElement;
import com.helger.commons.mutable.MutableBoolean;
import com.helger.commons.mutable.Wrapper;
import com.helger.commons.state.EFinish;
import com.helger.commons.string.StringHelper;
import com.helger.html.EHTMLElement;
import com.helger.html.hc.IHCControl;
import com.helger.html.hc.IHCElement;
import com.helger.html.hc.IHCHasChildren;
import com.helger.html.hc.IHCHasChildrenMutable;
import com.helger.html.hc.IHCNode;
import com.helger.html.hc.IHCWrappingNode;
import com.helger.html.hc.conversion.IHCConversionSettingsToNode;
import com.helger.html.hc.html.HCA;
import com.helger.html.hc.html.HCAbbr;
import com.helger.html.hc.html.HCAddress;
import com.helger.html.hc.html.HCB;
import com.helger.html.hc.html.HCBDO;
import com.helger.html.hc.html.HCBR;
import com.helger.html.hc.html.HCBase;
import com.helger.html.hc.html.HCBlockQuote;
import com.helger.html.hc.html.HCBody;
import com.helger.html.hc.html.HCButton;
import com.helger.html.hc.html.HCCaption;
import com.helger.html.hc.html.HCCenter;
import com.helger.html.hc.html.HCCite;
import com.helger.html.hc.html.HCCode;
import com.helger.html.hc.html.HCCol;
import com.helger.html.hc.html.HCColGroup;
import com.helger.html.hc.html.HCDD;
import com.helger.html.hc.html.HCDFN;
import com.helger.html.hc.html.HCDL;
import com.helger.html.hc.html.HCDT;
import com.helger.html.hc.html.HCDel;
import com.helger.html.hc.html.HCDir;
import com.helger.html.hc.html.HCDiv;
import com.helger.html.hc.html.HCEM;
import com.helger.html.hc.html.HCEmbed;
import com.helger.html.hc.html.HCFieldSet;
import com.helger.html.hc.html.HCFont;
import com.helger.html.hc.html.HCForm;
import com.helger.html.hc.html.HCFrame;
import com.helger.html.hc.html.HCFrameset;
import com.helger.html.hc.html.HCH1;
import com.helger.html.hc.html.HCH2;
import com.helger.html.hc.html.HCH3;
import com.helger.html.hc.html.HCH4;
import com.helger.html.hc.html.HCH5;
import com.helger.html.hc.html.HCH6;
import com.helger.html.hc.html.HCHR;
import com.helger.html.hc.html.HCHead;
import com.helger.html.hc.html.HCHtml;
import com.helger.html.hc.html.HCI;
import com.helger.html.hc.html.HCIFrame;
import com.helger.html.hc.html.HCImg;
import com.helger.html.hc.html.HCInput;
import com.helger.html.hc.html.HCIns;
import com.helger.html.hc.html.HCKBD;
import com.helger.html.hc.html.HCLI;
import com.helger.html.hc.html.HCLabel;
import com.helger.html.hc.html.HCLegend;
import com.helger.html.hc.html.HCLink;
import com.helger.html.hc.html.HCMenu;
import com.helger.html.hc.html.HCNoBR;
import com.helger.html.hc.html.HCNoScript;
import com.helger.html.hc.html.HCOL;
import com.helger.html.hc.html.HCObject;
import com.helger.html.hc.html.HCOptGroup;
import com.helger.html.hc.html.HCOption;
import com.helger.html.hc.html.HCP;
import com.helger.html.hc.html.HCParam;
import com.helger.html.hc.html.HCPre;
import com.helger.html.hc.html.HCQ;
import com.helger.html.hc.html.HCRow;
import com.helger.html.hc.html.HCS;
import com.helger.html.hc.html.HCSamp;
import com.helger.html.hc.html.HCScript;
import com.helger.html.hc.html.HCSelect;
import com.helger.html.hc.html.HCSmall;
import com.helger.html.hc.html.HCSpan;
import com.helger.html.hc.html.HCStrong;
import com.helger.html.hc.html.HCStyle;
import com.helger.html.hc.html.HCSub;
import com.helger.html.hc.html.HCSup;
import com.helger.html.hc.html.HCTBody;
import com.helger.html.hc.html.HCTD;
import com.helger.html.hc.html.HCTFoot;
import com.helger.html.hc.html.HCTH;
import com.helger.html.hc.html.HCTHead;
import com.helger.html.hc.html.HCTable;
import com.helger.html.hc.html.HCTextArea;
import com.helger.html.hc.html.HCTitle;
import com.helger.html.hc.html.HCU;
import com.helger.html.hc.html.HCUL;
import com.helger.html.hc.html.HCVar;
import com.helger.html.hc.html5.HCArticle;
import com.helger.html.hc.html5.HCAside;
import com.helger.html.hc.html5.HCAudio;
import com.helger.html.hc.html5.HCBDI;
import com.helger.html.hc.html5.HCCanvas;
import com.helger.html.hc.html5.HCCommand;
import com.helger.html.hc.html5.HCDataList;
import com.helger.html.hc.html5.HCDetails;
import com.helger.html.hc.html5.HCFigCaption;
import com.helger.html.hc.html5.HCFigure;
import com.helger.html.hc.html5.HCFooter;
import com.helger.html.hc.html5.HCHGroup;
import com.helger.html.hc.html5.HCHeader;
import com.helger.html.hc.html5.HCKeyGen;
import com.helger.html.hc.html5.HCMain;
import com.helger.html.hc.html5.HCMark;
import com.helger.html.hc.html5.HCMeter;
import com.helger.html.hc.html5.HCNav;
import com.helger.html.hc.html5.HCOutput;
import com.helger.html.hc.html5.HCProgress;
import com.helger.html.hc.html5.HCRP;
import com.helger.html.hc.html5.HCRT;
import com.helger.html.hc.html5.HCRuby;
import com.helger.html.hc.html5.HCSection;
import com.helger.html.hc.html5.HCSource;
import com.helger.html.hc.html5.HCSummary;
import com.helger.html.hc.html5.HCTime;
import com.helger.html.hc.html5.HCTrack;
import com.helger.html.hc.html5.HCVideo;
import com.helger.html.hc.html5.HCWBR;
import com.helger.html.hc.impl.AbstractHCElement;
import com.helger.html.hc.impl.AbstractHCNodeList;
import com.helger.html.hc.impl.HCGenericElementWithChildren;
import com.helger.html.hc.impl.HCNodeList;
import com.helger.html.hc.impl.HCTextNode;

@Immutable
public final class HCUtils
{
  private static final char PATTERN_NEWLINE = '\n';
  private static final int PATTERN_NEWLINE_LENGTH = 1;

  private HCUtils ()
  {}

  @Nonnull
  @ReturnsMutableCopy
  public static List <IHCNode> nl2brList (@Nullable final String sText)
  {
    final List <IHCNode> ret = new ArrayList <IHCNode> ();
    if (StringHelper.hasText (sText))
    {
      // Remove all "\r" chars
      final String sRealText = sText.replace ("\r", "");
      int nIndex = 0;
      while (nIndex < sRealText.length ())
      {
        final int nNext = sRealText.indexOf (PATTERN_NEWLINE, nIndex);
        if (nNext >= 0)
        {
          if (nNext > nIndex)
            ret.add (new HCTextNode (sRealText.substring (nIndex, nNext)));
          ret.add (new HCBR ());
          nIndex = nNext + PATTERN_NEWLINE_LENGTH;
        }
        else
        {
          // Add the rest
          ret.add (new HCTextNode (sRealText.substring (nIndex)));
          break;
        }
      }
    }
    return ret;
  }

  /**
   * Convert the passed text to a list of &lt;div&gt; elements. Each \n is used
   * to split the text into separate lines. \r characters are removed from the
   * string! Empty lines are preserved except for the last line. E.g.
   * <code>Hello\nworld</code> results in 2 &lt;div&gt;s:
   * &lt;div&gt;Hello&lt;/div&gt; and &lt;div&gt;world&lt;/div&gt;
   *
   * @param sText
   *        The text to be split. May be <code>null</code>.
   * @return A non-<code>null</code> but maybe empty list. The list is empty, if
   *         the string is empty.
   */
  @Nonnull
  @ReturnsMutableCopy
  public static List <HCDiv> nl2divList (@Nullable final String sText)
  {
    final List <HCDiv> ret = new ArrayList <HCDiv> ();
    if (StringHelper.hasText (sText))
    {
      // Remove all "\r" chars
      final String sRealText = sText.replace ("\r", "");
      int nIndex = 0;
      while (nIndex < sRealText.length ())
      {
        final int nNext = sRealText.indexOf (PATTERN_NEWLINE, nIndex);
        if (nNext >= 0)
        {
          // There is a newline
          ret.add (new HCDiv ().addChild (sRealText.substring (nIndex, nNext)));
          nIndex = nNext + PATTERN_NEWLINE_LENGTH;
        }
        else
        {
          // Add the rest
          final String sRest = sRealText.substring (nIndex);
          if (sRest.length () > 0)
            ret.add (new HCDiv ().addChild (sRest));
          break;
        }
      }
    }
    return ret;
  }

  @Nonnull
  @ReturnsMutableCopy
  public static List <IHCNode> list2brList (@Nullable final Iterable <String> aCont)
  {
    final List <IHCNode> ret = new ArrayList <IHCNode> ();
    if (aCont != null)
    {
      for (final String sText : aCont)
      {
        if (!ret.isEmpty ())
          ret.add (new HCBR ());
        ret.add (new HCTextNode (sText));
      }
    }
    return ret;
  }

  @Nonnull
  @ReturnsMutableCopy
  public static List <IHCNode> list2divList (@Nullable final Iterable <String> aCont)
  {
    final List <IHCNode> ret = new ArrayList <IHCNode> ();
    if (aCont != null)
      for (final String sText : aCont)
        ret.add (new HCDiv ().addChild (sText));
    return ret;
  }

  /**
   * Helper method to enforce correct element nesting. See
   * http://www.w3.org/TR/xhtml1#prohibitions
   *
   * @param aOwner
   *        Owner to start searching
   * @param aElements
   *        The tag names to search. May not be <code>null</code>.
   * @return The first element with the passed tag name on any level, or
   *         <code>null</code> if no such element exists.
   */
  @Nullable
  public static IHCElement <?> recursiveGetFirstChildWithTagName (@Nonnull final IHCHasChildren aOwner,
                                                                  @Nonnull @Nonempty final EHTMLElement... aElements)
  {
    ValueEnforcer.notNull (aOwner, "Owner");
    ValueEnforcer.notEmpty (aElements, "Elements");

    final Wrapper <IHCElement <?>> ret = new Wrapper <IHCElement <?>> ();
    iterateChildren (aOwner, new IHCIteratorCallback ()
    {
      @Nullable
      public EFinish call (@Nullable final IHCHasChildren aParentNode, @Nonnull final IHCNode aChildNode)
      {
        if (aChildNode instanceof IHCElement <?>)
        {
          final IHCElement <?> aCurrentElement = (IHCElement <?>) aChildNode;
          final String sCurrentTagName = aCurrentElement.getTagName ();
          for (final EHTMLElement aElement : aElements)
            if (sCurrentTagName.equalsIgnoreCase (aElement.getElementName ()))
            {
              ret.set (aCurrentElement);
              return EFinish.FINISHED;
            }
        }
        return EFinish.UNFINISHED;
      }
    });
    return ret.get ();
  }

  public static boolean recursiveContainsChildWithTagName (@Nonnull final IHCHasChildren aOwner,
                                                           @Nonnull @Nonempty final EHTMLElement... aElements)
  {
    return recursiveGetFirstChildWithTagName (aOwner, aElements) != null;
  }

  /**
   * Helper method to enforce correct element nesting. See
   * http://www.w3.org/TR/xhtml1#prohibitions
   *
   * @param aOwner
   *        Owner where to start searching
   * @param aElements
   *        The tag names to search. May not be <code>null</code>.
   * @return All elements with the passed element name on any level. Never
   *         <code>null</code>.
   */
  @Nonnull
  @ReturnsMutableCopy
  public static List <IHCElement <?>> recursiveGetAllChildrenWithTagName (@Nonnull final IHCHasChildren aOwner,
                                                                          @Nonnull @Nonempty final EHTMLElement... aElements)
  {
    ValueEnforcer.notNull (aOwner, "Owner");
    ValueEnforcer.notEmpty (aElements, "Elements");

    final List <IHCElement <?>> ret = new ArrayList <IHCElement <?>> ();
    iterateChildren (aOwner, new IHCIteratorCallback ()
    {
      @Nullable
      public EFinish call (@Nullable final IHCHasChildren aParentNode, @Nonnull final IHCNode aChildNode)
      {
        if (aChildNode instanceof IHCElement <?>)
        {
          final IHCElement <?> aCurrentElement = (IHCElement <?>) aChildNode;
          final String sCurrentTagName = aCurrentElement.getTagName ();
          for (final EHTMLElement aElement : aElements)
            if (sCurrentTagName.equalsIgnoreCase (aElement.getElementName ()))
            {
              ret.add (aCurrentElement);
              break;
            }
        }
        return EFinish.UNFINISHED;
      }
    });
    return ret;
  }

  /**
   * Helper method to enforce correct element nesting. See
   * http://www.w3.org/TR/xhtml1#prohibitions
   *
   * @param aOwner
   *        Owner where to start searching
   * @param aElements
   *        The tag names to search. May not be <code>null</code>.
   * @return The first element with a different than the passed tag name on any
   *         level, or <code>null</code> if no such element exists.
   */
  @Nullable
  public static IHCElement <?> recursiveGetFirstChildWithDifferentTagName (@Nonnull final IHCHasChildren aOwner,
                                                                           @Nonnull @Nonempty final EHTMLElement... aElements)
  {
    ValueEnforcer.notNull (aOwner, "Owner");
    ValueEnforcer.notEmpty (aElements, "Elements");

    final Wrapper <IHCElement <?>> ret = new Wrapper <IHCElement <?>> ();
    iterateChildren (aOwner, new IHCIteratorCallback ()
    {
      @Nullable
      public EFinish call (@Nullable final IHCHasChildren aParentNode, @Nonnull final IHCNode aChildNode)
      {
        if (aChildNode instanceof IHCElement <?>)
        {
          final IHCElement <?> aCurrentElement = (IHCElement <?>) aChildNode;
          final String sCurrentTagName = aCurrentElement.getTagName ();
          boolean bFound = false;
          for (final EHTMLElement aElement : aElements)
            if (sCurrentTagName.equalsIgnoreCase (aElement.getElementName ()))
            {
              bFound = true;
              break;
            }
          if (!bFound)
          {
            ret.set (aCurrentElement);
            return EFinish.FINISHED;
          }
        }
        return EFinish.UNFINISHED;
      }
    });
    return ret.get ();
  }

  public static boolean recursiveContainsChildWithDifferentTagName (@Nonnull final IHCHasChildren aOwner,
                                                                    @Nonnull @Nonempty final EHTMLElement... aElements)
  {
    return recursiveGetFirstChildWithDifferentTagName (aOwner, aElements) != null;
  }

  public static boolean recursiveContainsAtLeastOneTextNode (@Nullable final IHCNode aStartNode)
  {
    if (aStartNode == null)
      return false;

    if (aStartNode instanceof HCTextNode)
      return true;

    if (aStartNode instanceof IHCHasChildren)
    {
      final MutableBoolean ret = new MutableBoolean (false);
      iterateChildren ((IHCHasChildren) aStartNode, new IHCIteratorCallback ()
      {
        @Nullable
        public EFinish call (@Nullable final IHCHasChildren aParentNode, @Nonnull final IHCNode aChildNode)
        {
          if (aChildNode instanceof HCTextNode)
          {
            ret.set (true);
            return EFinish.FINISHED;
          }
          return EFinish.UNFINISHED;
        }
      });
      return ret.booleanValue ();
    }

    return false;
  }

  @Nonnull
  private static EFinish _recursiveIterateTree (@Nonnull final IHCHasChildren aNode,
                                                @Nonnull final IHCIteratorCallback aCallback)
  {
    if (aNode.hasChildren ())
    {
      for (final IHCNode aChild : aNode.getAllChildren ())
      {
        // call callback
        if (aCallback.call (aNode, aChild).isFinished ())
          return EFinish.FINISHED;

        // does the node has children
        if (aChild instanceof IHCHasChildren)
          if (_recursiveIterateTree ((IHCHasChildren) aChild, aCallback).isFinished ())
            return EFinish.FINISHED;
      }
    }
    return EFinish.UNFINISHED;
  }

  /**
   * Recursively iterate the node and all child nodes of the passed node. The
   * difference to {@link #iterateChildren(IHCHasChildren, IHCIteratorCallback)}
   * is, that the callback is also invoked on the passed node.
   *
   * @param aNode
   *        The node to be iterated.
   * @param aCallback
   *        The callback to be invoked on every child
   */
  public static void iterateTree (@Nonnull final IHCHasChildren aNode, @Nonnull final IHCIteratorCallback aCallback)
  {
    ValueEnforcer.notNull (aNode, "node");
    ValueEnforcer.notNull (aCallback, "callback");

    // call callback on start node
    if (aCallback.call (null, aNode).isUnfinished ())
      _recursiveIterateTree (aNode, aCallback);
  }

  /**
   * Recursively iterate all child nodes of the passed node.
   *
   * @param aNode
   *        The node who's children should be iterated.
   * @param aCallback
   *        The callback to be invoked on every child
   */
  public static void iterateChildren (@Nonnull final IHCHasChildren aNode, @Nonnull final IHCIteratorCallback aCallback)
  {
    ValueEnforcer.notNull (aNode, "node");
    ValueEnforcer.notNull (aCallback, "callback");

    _recursiveIterateTree (aNode, aCallback);
  }

  /**
   * Find the first HTML child element within a start element. This check
   * considers both lower- and upper-case element names. Mixed case is not
   * supported!
   *
   * @param aElement
   *        The element to search in
   * @param eHTMLElement
   *        The HTML element to search.
   * @return <code>null</code> if no such child element is present.
   */
  @Nullable
  public static IMicroElement getFirstChildElement (@Nonnull final IMicroElement aElement,
                                                    @Nonnull final EHTMLElement eHTMLElement)
  {
    ValueEnforcer.notNull (aElement, "element");
    ValueEnforcer.notNull (eHTMLElement, "HTMLElement");

    IMicroElement aChild = aElement.getFirstChildElement (eHTMLElement.getElementNameLowerCase ());
    if (aChild == null)
      aChild = aElement.getFirstChildElement (eHTMLElement.getElementNameUpperCase ());
    return aChild;
  }

  /**
   * Get a list of all HTML child elements of the given element. This methods
   * handles lower- and upper-cased elements.
   *
   * @param aElement
   *        The element to search in
   * @param eHTMLElement
   *        The HTML element to search
   * @return A non-<code>null</code> list where the lower-case elements are
   *         listed before the upper-case elements.
   */
  @Nonnull
  @ReturnsMutableCopy
  public static List <IMicroElement> getChildElements (@Nonnull final IMicroElement aElement,
                                                       @Nonnull final EHTMLElement eHTMLElement)
  {
    ValueEnforcer.notNull (aElement, "element");
    ValueEnforcer.notNull (eHTMLElement, "HTMLElement");

    final List <IMicroElement> ret = new ArrayList <IMicroElement> ();
    ret.addAll (aElement.getAllChildElements (eHTMLElement.getElementNameLowerCase ()));
    ret.addAll (aElement.getAllChildElements (eHTMLElement.getElementNameUpperCase ()));
    return ret;
  }

  private static void _recursiveAddFlattened (@Nullable final IHCNode aNode, @Nonnull final List <IHCNode> aRealList)
  {
    ValueEnforcer.notNull (aRealList, "RealList");

    if (aNode != null)
    {
      // Only check HCNodeList and not IHCNodeWithChildren because other
      // surrounding elements would not be handled correctly!
      if (aNode instanceof AbstractHCNodeList <?>)
      {
        final AbstractHCNodeList <?> aNodeList = (AbstractHCNodeList <?>) aNode;
        if (aNodeList.hasChildren ())
          for (final IHCNode aChild : aNodeList.getAllChildren ())
            _recursiveAddFlattened (aChild, aRealList);
      }
      else
        aRealList.add (aNode);
    }
  }

  /**
   * Inline all contained node lists so that a "flat" list results. This only
   * flattens something if the passed node is an {@link HCNodeList} and all
   * node-lists directly contained in the other node lists. Node-lists that are
   * hidden deep inside the tree are not considered!
   *
   * @param aNode
   *        The source node. May be <code>null</code>.
   * @return A non-<code>null</code> flattened list.
   */
  @Nonnull
  @ReturnsMutableCopy
  public static List <IHCNode> getAsFlattenedList (@Nullable final IHCNode aNode)
  {
    final List <IHCNode> ret = new ArrayList <IHCNode> ();
    _recursiveAddFlattened (aNode, ret);
    return ret;
  }

  /**
   * Inline all contained node lists so that a "flat" list results. This only
   * flattens something if the passed node is an {@link HCNodeList} and all
   * node-lists directly contained in the other node lists. Node-lists that are
   * hidden deep inside the tree are not considered!
   *
   * @param aNodes
   *        The source nodes. May be <code>null</code> or empty.
   * @return A non-<code>null</code> flattened list.
   */
  @Nonnull
  @ReturnsMutableCopy
  public static List <IHCNode> getAsFlattenedList (@Nullable final Iterable <? extends IHCNode> aNodes)
  {
    final List <IHCNode> ret = new ArrayList <IHCNode> ();
    if (aNodes != null)
      for (final IHCNode aNode : aNodes)
        _recursiveAddFlattened (aNode, ret);
    return ret;
  }

  /**
   * Resolve all wrappings via {@link IHCWrappingNode} of the passed node. This
   * is usually either an HCOutOfBandNode or a HCConditionalCommentNode.
   *
   * @param aHCNode
   *        The node to be unwrapped. May be <code>null</code>.
   * @return The unwrapped node. May be the same as the parameter, if the node
   *         is not wrapped. May be <code>null</code> if the parameter node is
   *         <code>null</code>.
   */
  @Nullable
  public static IHCNode getUnwrappedNode (@Nullable final IHCNode aHCNode)
  {
    if (isWrappedNode (aHCNode))
      return getUnwrappedNode (((IHCWrappingNode) aHCNode).getWrappedNode ());

    return aHCNode;
  }

  /**
   * Check if the passed node is a wrapped node by checking if it implements
   * {@link IHCWrappingNode}.
   *
   * @param aHCNode
   *        The node to be checked. May be <code>null</code>.
   * @return <code>true</code> if the node is not <code>null</code> and if it
   *         implements {@link IHCWrappingNode}.
   */
  public static boolean isWrappedNode (@Nullable final IHCNode aHCNode)
  {
    return aHCNode instanceof IHCWrappingNode;
  }

  /**
   * Find the first instance of {@link IHCControl} that is either the passed
   * element or a child of the passed element.
   *
   * @param aNode
   *        The source node to start searching. May be <code>null</code>.
   * @return <code>null</code> if no {@link IHCControl} can be found below the
   *         passed node.
   */
  @Nullable
  public static IHCControl <?> getFirstHCControl (@Nullable final IHCNode aNode)
  {
    if (aNode instanceof IHCControl <?>)
      return (IHCControl <?>) aNode;

    if (aNode instanceof IHCHasChildren)
    {
      // E.g. HCNodeList
      final IHCHasChildren aParent = (IHCHasChildren) aNode;
      if (aParent.hasChildren ())
        for (final IHCNode aChild : aParent.getAllChildren ())
        {
          final IHCControl <?> aNestedCtrl = getFirstHCControl (aChild);
          if (aNestedCtrl != null)
            return aNestedCtrl;
        }
    }

    return null;
  }

  /**
   * Recursively determine all {@link IHCControl} elements from and incl. the
   * passed node
   *
   * @param aNode
   *        The start node. May be <code>null</code>.
   * @param aTargetList
   *        The target list to be filled. May not be <code>null</code>.
   */
  public static void getAllHCControls (@Nullable final IHCNode aNode,
                                       @Nonnull final List <? super IHCControl <?>> aTargetList)
  {
    ValueEnforcer.notNull (aTargetList, "TargetList");

    if (aNode instanceof IHCControl <?>)
      aTargetList.add ((IHCControl <?>) aNode);

    if (aNode instanceof IHCHasChildren)
    {
      // E.g. HCNodeList
      final IHCHasChildren aParent = (IHCHasChildren) aNode;
      if (aParent.hasChildren ())
        for (final IHCNode aChild : aParent.getAllChildren ())
          getAllHCControls (aChild, aTargetList);
    }
  }

  /**
   * Recursively determine all {@link IHCControl} elements from and incl. the
   * passed node
   *
   * @param aNode
   *        The start node. May be <code>null</code>.
   * @return The filled list with all controls. Never <code>null</code> but
   *         maybe empty.
   */
  @Nonnull
  @ReturnsMutableCopy
  public static List <IHCControl <?>> getAllHCControls (@Nullable final IHCNode aNode)
  {
    final List <IHCControl <?>> ret = new ArrayList <IHCControl <?>> ();
    getAllHCControls (aNode, ret);
    return ret;
  }

  /**
   * Recursively determine all {@link IHCControl} elements from and incl. the
   * passed nodes
   *
   * @param aNodes
   *        The start nodes. May be <code>null</code>.
   * @param aTargetList
   *        The target list to be filled. May not be <code>null</code>.
   */
  public static void getAllHCControls (@Nullable final Iterable <? extends IHCNode> aNodes,
                                       @Nonnull final List <? super IHCControl <?>> aTargetList)
  {
    ValueEnforcer.notNull (aTargetList, "TargetList");

    if (aNodes != null)
      for (final IHCNode aNode : aNodes)
        getAllHCControls (aNode, aTargetList);
  }

  /**
   * Recursively determine all {@link IHCControl} elements from and incl. the
   * passed nodes
   *
   * @param aNodes
   *        The start nodes. May be <code>null</code>.
   * @return The filled list with all controls. Never <code>null</code> but
   *         maybe empty.
   */
  @Nonnull
  @ReturnsMutableCopy
  public static List <IHCControl <?>> getAllHCControls (@Nullable final Iterable <? extends IHCNode> aNodes)
  {
    final List <IHCControl <?>> ret = new ArrayList <IHCControl <?>> ();
    getAllHCControls (aNodes, ret);
    return ret;
  }

  /**
   * Customize the passed base node and all child nodes recursively.
   *
   * @param aBaseNode
   *        Base node to start customizing (incl.). May not be <code>null</code>
   *        .
   * @param aConversionSettings
   *        The conversion settings to use. May not be <code>null</code>.
   */
  public static void customizeNodes (@Nonnull final IHCHasChildrenMutable <?, ? super IHCNode> aBaseNode,
                                     @Nonnull final IHCConversionSettingsToNode aConversionSettings)
  {
    ValueEnforcer.notNull (aBaseNode, "BaseNode");
    ValueEnforcer.notNull (aConversionSettings, "ConversionSettings");

    // Customize element, before extracting out-of-band nodes, in case the
    // customizer adds some out-of-band nodes as well
    iterateTree (aBaseNode, new IHCIteratorCallback ()
    {
      @Nonnull
      public EFinish call (@Nullable final IHCHasChildren aParentNode, @Nonnull final IHCNode aChildNode)
      {
        // Append all additional nodes to the passed base node
        aChildNode.applyCustomization (aConversionSettings, aBaseNode);
        return EFinish.UNFINISHED;
      }
    });
  }

  @Nullable
  public static AbstractHCElement <?> createHCElementFromName (@Nullable final String sTagName)
  {
    final EHTMLElement eElement = EHTMLElement.getFromTagNameOrNull (sTagName);
    return createHCElement (eElement);
  }

  @Nullable
  public static AbstractHCElement <?> createHCElement (@Nullable final EHTMLElement eElement)
  {
    if (eElement == null)
      return null;

    switch (eElement)
    {
      case A:
        return new HCA ();
      case ABBR:
        return new HCAbbr ();
      case ADDRESS:
        return new HCAddress ();
      case APPLET:
        return new HCGenericElementWithChildren (eElement);
      case AREA:
        return new HCGenericElementWithChildren (eElement);
      case ARTICLE:
        return new HCArticle ();
      case ASIDE:
        return new HCAside ();
      case AUDIO:
        return new HCAudio ();
      case B:
        return new HCB ();
      case BASE:
        return new HCBase ();
      case BDI:
        return new HCBDI ();
      case BDO:
        return new HCBDO ();
      case BLOCKQUOTE:
        return new HCBlockQuote ();
      case BODY:
        return new HCBody ();
      case BR:
        return new HCBR ();
      case BUTTON:
        return new HCButton ();
      case CANVAS:
        return new HCCanvas ();
      case CAPTION:
        return new HCCaption ();
      case CENTER:
        return new HCCenter ();
      case CITE:
        return new HCCite ();
      case CODE:
        return new HCCode ();
      case COL:
        return new HCCol ();
      case COLGROUP:
        return new HCColGroup ();
      case COMMAND:
        return new HCCommand ();
      case DATALIST:
        return new HCDataList ();
      case DD:
        return new HCDD ();
      case DEL:
        return new HCDel ();
      case DETAILS:
        return new HCDetails ();
      case DFN:
        return new HCDFN ();
      case DIR:
        return new HCDir ();
      case DIV:
        return new HCDiv ();
      case DL:
        return new HCDL ();
      case DT:
        return new HCDT ();
      case EM:
        return new HCEM ();
      case EMBED:
        return new HCEmbed ();
      case FIELDSET:
        return new HCFieldSet ();
      case FIGCAPTION:
        return new HCFigCaption ();
      case FIGURE:
        return new HCFigure ();
      case FONT:
        return new HCFont ();
      case FOOTER:
        return new HCFooter ();
      case FORM:
        return new HCForm ();
      case FRAME:
        return new HCFrame ();
      case FRAMESET:
        return new HCFrameset ();
      case H1:
        return new HCH1 ();
      case H2:
        return new HCH2 ();
      case H3:
        return new HCH3 ();
      case H4:
        return new HCH4 ();
      case H5:
        return new HCH5 ();
      case H6:
        return new HCH6 ();
      case HEAD:
        return new HCHead ();
      case HEADER:
        return new HCHeader ();
      case HGROUP:
        return new HCHGroup ();
      case HR:
        return new HCHR ();
      case HTML:
        return new HCHtml ();
      case I:
        return new HCI ();
      case IFRAME:
        return new HCIFrame ();
      case IMG:
        return new HCImg ();
      case INS:
        return new HCIns ();
      case INPUT:
        return new HCInput ();
      case KBD:
        return new HCKBD ();
      case KEYGEN:
        return new HCKeyGen ();
      case LABEL:
        return new HCLabel ();
      case LEGEND:
        return new HCLegend ();
      case LI:
        return new HCLI ();
      case LINK:
        return new HCLink ();
      case MAIN:
        return new HCMain ();
      case MAP:
        return new HCGenericElementWithChildren (eElement);
      case MARK:
        return new HCMark ();
      case MENU:
        return new HCMenu ();
      case META:
        return new HCGenericElementWithChildren (eElement);
      case METER:
        return new HCMeter ();
      case NAV:
        return new HCNav ();
      case NOBR:
        return new HCNoBR ();
      case NOSCRIPT:
        return new HCNoScript ();
      case OBJECT:
        return new HCObject ();
      case OL:
        return new HCOL ();
      case OPTGROUP:
        return new HCOptGroup ();
      case OPTION:
        return new HCOption ();
      case OUTPUT:
        return new HCOutput ();
      case P:
        return new HCP ();
      case PARAM:
        return new HCParam ();
      case PRE:
        return new HCPre ();
      case PROGRESS:
        return new HCProgress ();
      case RP:
        return new HCRP ();
      case RT:
        return new HCRT ();
      case RUBY:
        return new HCRuby ();
      case Q:
        return new HCQ ();
      case S:
        return new HCS ();
      case SAMP:
        return new HCSamp ();
      case SCRIPT:
        return new HCScript ();
      case SECTION:
        return new HCSection ();
      case SELECT:
        return new HCSelect ();
      case SMALL:
        return new HCSmall ();
      case SOURCE:
        return new HCSource ();
      case SPAN:
        return new HCSpan ();
      case STRONG:
        return new HCStrong ();
      case SUB:
        return new HCSub ();
      case SUMMARY:
        return new HCSummary ();
      case SUP:
        return new HCSup ();
      case STYLE:
        return new HCStyle ();
      case TABLE:
        return new HCTable ();
      case TBODY:
        return new HCTBody ();
      case TD:
        return new HCTD ();
      case TEXTAREA:
        return new HCTextArea ();
      case TEMPLATE:
        return new HCGenericElementWithChildren (eElement);
      case TFOOT:
        return new HCTFoot ();
      case TH:
        return new HCTH ();
      case THEAD:
        return new HCTHead ();
      case TIME:
        return new HCTime ();
      case TITLE:
        return new HCTitle ();
      case TR:
        return new HCRow ();
      case TRACK:
        return new HCTrack ();
      case U:
        return new HCU ();
      case UL:
        return new HCUL ();
      case VAR:
        return new HCVar ();
      case VIDEO:
        return new HCVideo ();
      case WBR:
        return new HCWBR ();
    }
    throw new IllegalStateException ("Failed to resolve element type from " + eElement);
  }
}
