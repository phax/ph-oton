/**
 * Copyright (C) 2014-2019 Philip Helger (www.helger.com)
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
package com.helger.html.markdown;

import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.annotation.Nonnull;

import com.helger.commons.annotation.ReturnsMutableCopy;
import com.helger.commons.collection.impl.CommonsArrayList;
import com.helger.commons.collection.impl.CommonsHashMap;
import com.helger.commons.collection.impl.ICommonsList;
import com.helger.commons.collection.impl.ICommonsMap;
import com.helger.commons.regex.RegExCache;
import com.helger.commons.string.StringHelper;
import com.helger.commons.url.ISimpleURL;
import com.helger.commons.url.SimpleURL;
import com.helger.html.entity.EHTMLEntity;
import com.helger.html.entity.HTMLEntity;
import com.helger.html.hc.ext.HCExtHelper;
import com.helger.html.hc.html.IHCElement;
import com.helger.html.hc.html.IHCElementWithChildren;
import com.helger.html.hc.html.embedded.HCImg;
import com.helger.html.hc.html.grouping.HCLI;
import com.helger.html.hc.html.textlevel.HCA;
import com.helger.html.hc.html.textlevel.HCAbbr;
import com.helger.html.hc.html.textlevel.HCCode;
import com.helger.html.hc.impl.HCCommentNode;
import com.helger.html.hc.impl.HCDOMWrapper;
import com.helger.html.hc.impl.HCEntityNode;
import com.helger.xml.microdom.IMicroDocument;
import com.helger.xml.microdom.IMicroElement;
import com.helger.xml.microdom.serialize.MicroReader;
import com.helger.xml.serialize.write.XMLEmitter;

/**
 * Emitter class responsible for generating HTML output.
 *
 * @author René Jeschke &lt;rene_jeschke@yahoo.de&gt
 */
final class Emitter
{
  /** Link references. */
  private final ICommonsMap <String, LinkRef> m_aLinkRefs = new CommonsHashMap <> ();
  /** The configuration. */
  private final MarkdownConfiguration m_aConfig;
  /** Extension flag. */
  private boolean m_bUseExtensions = false;
  /** Newline flag. */
  private final boolean m_bConvertNewline2Br;
  /** Plugins references **/
  private final ICommonsMap <String, AbstractMarkdownPlugin> m_aPlugins = new CommonsHashMap <> ();

  /**
   * Constructor.
   *
   * @param aConfig
   *        config to use
   */
  public Emitter (@Nonnull final MarkdownConfiguration aConfig)
  {
    m_aConfig = aConfig;
    m_bUseExtensions = aConfig.isExtendedProfile ();
    m_bConvertNewline2Br = aConfig.isConvertNewline2Br ();
    for (final AbstractMarkdownPlugin plugin : aConfig.getAllPlugins ())
      register (plugin);
  }

  void setUseExtensions (final boolean bUseExtensions)
  {
    m_bUseExtensions = bUseExtensions;
  }

  public void register (@Nonnull final AbstractMarkdownPlugin aPlugin)
  {
    m_aPlugins.put (aPlugin.getPluginID (), aPlugin);
  }

  /**
   * Adds a LinkRef to this set of LinkRefs.
   *
   * @param sKey
   *        The key/id.
   * @param aLinkRef
   *        The LinkRef.
   */
  public void addLinkRef (@Nonnull final String sKey, final LinkRef aLinkRef)
  {
    m_aLinkRefs.put (sKey.toLowerCase (Locale.US), aLinkRef);
  }

  /**
   * Transforms the given block recursively into HTML.
   *
   * @param aOut
   *        The StringBuilder to write to.
   * @param aRoot
   *        The Block to process.
   */
  public void emit (final MarkdownHCStack aOut, final Block aRoot)
  {
    aRoot.removeSurroundingEmptyLines ();
    final IMarkdownDecorator aDecorator = m_aConfig.getDecorator ();

    switch (aRoot.m_eType)
    {
      case RULER:
        aDecorator.appendHorizontalRuler (aOut);
        return;
      case NONE:
      case XML:
      case XML_COMMENT:
        // No open required
        break;
      case HEADLINE:
        final IHCElementWithChildren <?> aHX = aDecorator.openHeadline (aOut, aRoot.m_nHeadlineDepth);
        if (m_bUseExtensions && aRoot.m_sID != null)
          aHX.setID (aRoot.m_sID);
        break;
      case PARAGRAPH:
        aDecorator.openParagraph (aOut);
        break;
      case CODE:
      case FENCED_CODE:
        if (m_aConfig.getCodeBlockEmitter () == null)
          aDecorator.openCodeBlock (aOut);
        break;
      case BLOCKQUOTE:
        aDecorator.openBlockquote (aOut);
        break;
      case UNORDERED_LIST:
        aDecorator.openUnorderedList (aOut);
        break;
      case ORDERED_LIST:
        aDecorator.openOrderedList (aOut);
        break;
      case LIST_ITEM:
        final HCLI aLI = aDecorator.openListItem (aOut);
        if (m_bUseExtensions && aRoot.m_sID != null)
          aLI.setID (aRoot.m_sID);
        break;
      default:
        break;
    }

    if (aRoot.hasLines ())
    {
      _emitLines (aOut, aRoot);
    }
    else
    {
      Block aBlock = aRoot.m_aBlocks;
      while (aBlock != null)
      {
        emit (aOut, aBlock);
        aBlock = aBlock.m_aNext;
      }
    }

    switch (aRoot.m_eType)
    {
      case RULER:
      case NONE:
      case XML:
      case XML_COMMENT:
        break;
      case HEADLINE:
        aDecorator.closeHeadline (aOut, aRoot.m_nHeadlineDepth);
        break;
      case PARAGRAPH:
        aDecorator.closeParagraph (aOut);
        break;
      case CODE:
      case FENCED_CODE:
        if (m_aConfig.getCodeBlockEmitter () == null)
          aDecorator.closeCodeBlock (aOut);
        break;
      case BLOCKQUOTE:
        aDecorator.closeBlockquote (aOut);
        break;
      case UNORDERED_LIST:
        aDecorator.closeUnorderedList (aOut);
        break;
      case ORDERED_LIST:
        aDecorator.closeOrderedList (aOut);
        break;
      case LIST_ITEM:
        aDecorator.closeListItem (aOut);
        break;
      default:
        break;
    }
  }

  /**
   * Transforms lines into HTML.
   *
   * @param aOut
   *        The StringBuilder to write to.
   * @param aBlock
   *        The Block to process.
   */
  private void _emitLines (final MarkdownHCStack aOut, final Block aBlock)
  {
    switch (aBlock.m_eType)
    {
      case CODE:
        _emitCodeLines (aOut, aBlock.m_aLines, aBlock.m_sMeta, true);
        break;
      case FENCED_CODE:
        _emitCodeLines (aOut, aBlock.m_aLines, aBlock.m_sMeta, false);
        break;
      case PLUGIN:
        emitPluginLines (aOut, aBlock.m_aLines, aBlock.m_sMeta);
        break;
      case XML:
        _emitXMLLines (aOut, aBlock.m_aLines);
        break;
      case XML_COMMENT:
        _emitXMLComment (aOut, aBlock.m_aLines);
        break;
      case PARAGRAPH:
        _emitMarkedLines (aOut, aBlock.m_aLines);
        break;
      default:
        _emitMarkedLines (aOut, aBlock.m_aLines);
        break;
    }
  }

  /**
   * Finds the position of the given Token in the given String.
   *
   * @param sIn
   *        The String to search on.
   * @param nStart
   *        The starting character position.
   * @param eToken
   *        The token to find.
   * @return The position of the token or -1 if none could be found.
   */
  private int _findInlineToken (final String sIn, final int nStart, final EMarkToken eToken)
  {
    int nPos = nStart;
    while (nPos < sIn.length ())
    {
      if (_getToken (sIn, nPos) == eToken)
        return nPos;
      nPos++;
    }
    return -1;
  }

  /**
   * Checks if there is a valid markdown link definition.
   *
   * @param aOut
   *        The StringBuilder containing the generated output.
   * @param sIn
   *        Input String.
   * @param nStart
   *        Starting position.
   * @param eToken
   *        Either LINK or IMAGE.
   * @return The new position or -1 if there is no valid markdown link.
   */
  private int _checkInlineLink (final MarkdownHCStack aOut, final String sIn, final int nStart, final EMarkToken eToken)
  {
    boolean bIsAbbrev = false;
    int nPos = nStart + (eToken == EMarkToken.LINK ? 1 : 2);
    final StringBuilder aTmp = new StringBuilder ();

    aTmp.setLength (0);
    nPos = MarkdownHelper.readMdLinkId (aTmp, sIn, nPos);
    if (nPos < nStart)
      return -1;

    final String sName = aTmp.toString ();
    String sLink = null;
    String sComment = null;
    final int nOldPos = nPos++;
    nPos = MarkdownHelper.skipSpaces (sIn, nPos);
    if (nPos < nStart)
    {
      final LinkRef aLR = m_aLinkRefs.get (sName.toLowerCase (Locale.US));
      if (aLR == null)
        return -1;
      bIsAbbrev = aLR.isAbbrev ();
      sLink = aLR.getLink ();
      sComment = aLR.getTitle ();
      nPos = nOldPos;
    }
    else
      if (sIn.charAt (nPos) == '(')
      {
        nPos++;
        nPos = MarkdownHelper.skipSpaces (sIn, nPos);
        if (nPos < nStart)
          return -1;
        aTmp.setLength (0);
        final boolean bUseLt = sIn.charAt (nPos) == '<';
        nPos = bUseLt ? MarkdownHelper.readUntil (aTmp, sIn, nPos + 1, '>')
                      : MarkdownHelper.readMdLink (aTmp, sIn, nPos);
        if (nPos < nStart)
          return -1;
        if (bUseLt)
          nPos++;
        sLink = aTmp.toString ();

        if (sIn.charAt (nPos) == ' ')
        {
          nPos = MarkdownHelper.skipSpaces (sIn, nPos);
          if (nPos > nStart && sIn.charAt (nPos) == '"')
          {
            nPos++;
            aTmp.setLength (0);
            nPos = MarkdownHelper.readUntil (aTmp, sIn, nPos, '"');
            if (nPos < nStart)
              return -1;
            sComment = aTmp.toString ();
            nPos++;
            nPos = MarkdownHelper.skipSpaces (sIn, nPos);
            if (nPos == -1)
              return -1;
          }
        }
        if (sIn.charAt (nPos) != ')')
          return -1;
      }
      else
        if (sIn.charAt (nPos) == '[')
        {
          nPos++;
          aTmp.setLength (0);
          nPos = MarkdownHelper.readRawUntil (aTmp, sIn, nPos, ']');
          if (nPos < nStart)
            return -1;
          final String sID = aTmp.length () > 0 ? aTmp.toString () : sName;
          final LinkRef aLR = m_aLinkRefs.get (sID.toLowerCase (Locale.US));
          if (aLR != null)
          {
            sLink = aLR.getLink ();
            sComment = aLR.getTitle ();
          }
        }
        else
        {
          final LinkRef aLR = m_aLinkRefs.get (sName.toLowerCase (Locale.US));
          if (aLR == null)
            return -1;
          bIsAbbrev = aLR.isAbbrev ();
          sLink = aLR.getLink ();
          sComment = aLR.getTitle ();
          nPos = nOldPos;
        }

    if (sLink == null)
      return -1;

    if (eToken == EMarkToken.LINK)
    {
      if (bIsAbbrev && sComment != null)
      {
        if (!m_bUseExtensions)
          return -1;
        aOut.push (new HCAbbr ().setTitle (sComment));
        _recursiveEmitLine (aOut, sName, 0, EMarkToken.NONE);
        aOut.pop ();
      }
      else
      {
        final HCA aLink = m_aConfig.getDecorator ().openLink (aOut);
        aLink.setHref (new SimpleURL (sLink));
        if (sComment != null)
          aLink.setTitle (sComment);
        _recursiveEmitLine (aOut, sName, 0, EMarkToken.NONE);
        m_aConfig.getDecorator ().closeLink (aOut);
      }
    }
    else
    {
      final HCImg aImg = m_aConfig.getDecorator ().appendImage (aOut);
      aImg.setSrc (new SimpleURL (sLink));
      aImg.setAlt (sName);
      if (sComment != null)
        aImg.setTitle (sComment);
    }

    return nPos;
  }

  /**
   * Check if there is a valid HTML tag here. This method also transforms auto
   * links and mailto auto links.
   *
   * @param out
   *        The StringBuilder to write to.
   * @param in
   *        Input String.
   * @param nStart
   *        Starting position.
   * @return The new position or -1 if nothing valid has been found.
   */
  private int _checkInlineHtml (final MarkdownHCStack out, final String in, final int nStart)
  {
    final StringBuilder aTmp = new StringBuilder ();

    // Check for auto links
    aTmp.setLength (0);
    int nPos = MarkdownHelper.readUntil (aTmp, in, nStart + 1, ':', ' ', '>', '\n');
    if (nPos != -1 && in.charAt (nPos) == ':' && MarkdownHTML.isLinkPrefix (aTmp.toString ()))
    {
      nPos = MarkdownHelper.readUntil (aTmp, in, nPos, '>');
      if (nPos != -1)
      {
        final String sLink = aTmp.toString ();
        final HCA aLink = m_aConfig.getDecorator ().openLink (out);
        aLink.setHref (new SimpleURL (sLink)).addChild (sLink);
        m_aConfig.getDecorator ().closeLink (out);
        return nPos;
      }
    }

    // Check for mailto or address auto link
    aTmp.setLength (0);
    nPos = MarkdownHelper.readUntil (aTmp, in, nStart + 1, '@', ' ', '>', '\n');
    if (nPos != -1 && in.charAt (nPos) == '@')
    {
      nPos = MarkdownHelper.readUntil (aTmp, in, nPos, '>');
      if (nPos != -1)
      {
        final String sLink = aTmp.toString ();
        final HCA aLink = m_aConfig.getDecorator ().openLink (out);
        if (sLink.startsWith ("@"))
        {
          // address auto links
          final String sAddress = sLink.substring (1);
          final ISimpleURL aUrl = new SimpleURL ("https://maps.google.com/maps").add ("q", sAddress);
          aLink.setHref (aUrl).addChild (sAddress);
        }
        else
        {
          // mailto auto links
          aLink.setHref (new SimpleURL ("mailto:" + sLink)).addChild (sLink);
        }
        m_aConfig.getDecorator ().closeLink (out);
        return nPos;
      }
    }

    // Check for inline html
    if (nStart + 2 < in.length ())
    {
      nPos = nStart;
      if (nStart + 3 < in.length () &&
          in.charAt (nStart + 1) == '!' &&
          in.charAt (nStart + 2) == '-' &&
          in.charAt (nStart + 3) == '-')
      {
        nPos = nStart + 4;
        final int nCommentStartPos = nPos;
        while (true)
        {
          while (nPos < in.length () && in.charAt (nPos) != '-')
            nPos++;

          if (nPos == in.length ())
          {
            // FIXME End of line in comment
            return -1;
          }
          if (nPos + 2 < in.length () && in.charAt (nPos + 1) == '-' && in.charAt (nPos + 2) == '>')
          {
            // XML comment inline
            out.append (new HCCommentNode (in.substring (nCommentStartPos, nPos)));
            return nPos + 2;
          }
          nPos++;
        }
      }

      aTmp.setLength (0);
      final int nNewPos = MarkdownHelper.readXMLElement (aTmp, in, nStart, m_aConfig.isSafeMode ());
      if (nNewPos != -1)
      {
        final String sElement = aTmp.toString ();
        if (sElement.endsWith ("/>"))
        {
          // Self closed tag - can be parsed
          final IMicroDocument aXML = MicroReader.readMicroXML (sElement);
          if (aXML == null)
            throw new MarkdownException ("Failed to parse XML: " + sElement);
          // And use the root element
          out.append (new HCDOMWrapper (aXML.getDocumentElement ().detachFromParent ()));
        }
        else
          if (sElement.startsWith ("</"))
          {
            // Closing tag
            out.pop ();
          }
          else
          {
            // Opening tag - parse as self-closed tag and push to stack
            final String sParseCode = sElement.substring (0, sElement.length () - 1) + "/>";
            final IMicroDocument aXML = MicroReader.readMicroXML (sParseCode);
            if (aXML == null)
              throw new MarkdownException ("Failed to parse XML: " + sParseCode);
            final IMicroElement eRoot = aXML.getDocumentElement ();

            // And use the root element
            final IHCElement <?> aHC = HCExtHelper.createHCElementFromName (eRoot.getTagName ());
            if (aHC == null)
              throw new MarkdownException ("Failed to get HC element: " + eRoot.getTagName ());

            // Clone all attributes
            eRoot.forAllAttributes (aAttr -> aHC.customAttrs ().putIn (aAttr.getAttributeQName (),
                                                                       aAttr.getAttributeValue ()));

            if (aHC.getElement ().mayBeSelfClosed ())
            {
              // e.g. <hr />
              out.append (aHC);
            }
            else
            {
              // Push
              out.push (aHC);
            }
          }

        return nNewPos - 1;
      }
    }

    return -1;
  }

  /**
   * Check if this is a valid XML/HTML entity.
   *
   * @param aOut
   *        The StringBuilder to write to.
   * @param sIn
   *        Input String.
   * @param nStart
   *        Starting position
   * @return The new position or -1 if this entity in invalid.
   */
  private static int _checkInlineEntity (final StringBuilder aOut, final String sIn, final int nStart)
  {
    final int nPos = MarkdownHelper.readUntil (aOut, sIn, nStart, ';');
    if (nPos < 0 || aOut.length () < 3)
      return -1;
    if (aOut.charAt (1) == '#')
    {
      if (aOut.charAt (2) == 'x' || aOut.charAt (2) == 'X')
      {
        if (aOut.length () < 4)
          return -1;
        for (int i = 3; i < aOut.length (); i++)
        {
          final char c = aOut.charAt (i);
          if ((c < '0' || c > '9') && ((c < 'a' || c > 'f') && (c < 'A' || c > 'F')))
            return -1;
        }
      }
      else
      {
        for (int i = 2; i < aOut.length (); i++)
        {
          final char c = aOut.charAt (i);
          if (c < '0' || c > '9')
            return -1;
        }
      }
      aOut.append (';');
    }
    else
    {
      for (int i = 1; i < aOut.length (); i++)
      {
        final char c = aOut.charAt (i);
        if ((c < 'a' || c > 'z') && (c < 'A' || c > 'Z'))
          return -1;
      }
      aOut.append (';');
      return EHTMLEntity.isValidEntityReference (aOut.toString ()) ? nPos : -1;
    }

    return nPos;
  }

  /**
   * Recursively scans through the given line, taking care of any markdown
   * stuff.
   *
   * @param aOut
   *        The StringBuilder to write to.
   * @param sIn
   *        Input String.
   * @param nStart
   *        Start position.
   * @param eToken
   *        The matching Token (for e.g. '*')
   * @return The position of the matching Token or -1 if token was NONE or no
   *         Token could be found.
   */
  private int _recursiveEmitLine (final MarkdownHCStack aOut,
                                  final String sIn,
                                  final int nStart,
                                  final EMarkToken eToken)
  {
    int nPos = nStart;
    int a;
    int b;
    final MarkdownHCStack aTmp = new MarkdownHCStack ();
    final StringBuilder aTmpSB = new StringBuilder ();
    while (nPos < sIn.length ())
    {
      final EMarkToken eCurToken = _getToken (sIn, nPos);
      if (eToken != EMarkToken.NONE)
        if (eCurToken.equals (eToken) ||
            (eToken == EMarkToken.EM_STAR && eCurToken == EMarkToken.STRONG_STAR) ||
            (eToken == EMarkToken.EM_UNDERSCORE && eCurToken == EMarkToken.STRONG_UNDERSCORE))
          return nPos;

      switch (eCurToken)
      {
        case IMAGE:
        case LINK:
          b = _checkInlineLink (aOut, sIn, nPos, eCurToken);
          if (b > 0)
            nPos = b;
          else
            aOut.append (sIn.charAt (nPos));
          break;
        case EM_STAR:
        case EM_UNDERSCORE:
          aTmp.reset ();
          b = _recursiveEmitLine (aTmp, sIn, nPos + 1, eCurToken);
          if (b > 0)
          {
            m_aConfig.getDecorator ().openEmphasis (aOut);
            aOut.append (aTmp.getRoot ());
            m_aConfig.getDecorator ().closeEmphasis (aOut);
            nPos = b;
          }
          else
          {
            aOut.append (sIn.charAt (nPos));
          }
          break;
        case STRONG_STAR:
        case STRONG_UNDERSCORE:
          aTmp.reset ();
          b = _recursiveEmitLine (aTmp, sIn, nPos + 2, eCurToken);
          if (b > 0)
          {
            m_aConfig.getDecorator ().openStrong (aOut);
            aOut.append (aTmp.getRoot ());
            m_aConfig.getDecorator ().closeStrong (aOut);
            nPos = b + 1;
          }
          else
          {
            aOut.append (sIn.charAt (nPos));
          }
          break;
        case STRIKE:
          aTmp.reset ();
          b = _recursiveEmitLine (aTmp, sIn, nPos + 2, eCurToken);
          if (b > 0)
          {
            m_aConfig.getDecorator ().openStrike (aOut);
            aOut.append (aTmp.getRoot ());
            m_aConfig.getDecorator ().closeStrike (aOut);
            nPos = b + 1;
          }
          else
          {
            aOut.append (sIn.charAt (nPos));
          }
          break;
        case SUPER:
          aTmp.reset ();
          b = _recursiveEmitLine (aTmp, sIn, nPos + 1, eCurToken);
          if (b > 0)
          {
            m_aConfig.getDecorator ().openSuper (aOut);
            aOut.append (aTmp.getRoot ());
            m_aConfig.getDecorator ().closeSuper (aOut);
            nPos = b;
          }
          else
          {
            aOut.append (sIn.charAt (nPos));
          }
          break;
        case CODE_SINGLE:
        case CODE_DOUBLE:
          a = nPos + (eCurToken == EMarkToken.CODE_DOUBLE ? 2 : 1);
          b = _findInlineToken (sIn, a, eCurToken);
          if (b > 0)
          {
            nPos = b + (eCurToken == EMarkToken.CODE_DOUBLE ? 1 : 0);
            while (a < b && sIn.charAt (a) == ' ')
              a++;
            if (a < b)
            {
              while (sIn.charAt (b - 1) == ' ')
                b--;
              final HCCode aCode = m_aConfig.getDecorator ().openCodeSpan (aOut);
              aCode.addChild (sIn.substring (a, b));
              m_aConfig.getDecorator ().closeCodeSpan (aOut);
            }
          }
          else
          {
            aOut.append (sIn.charAt (nPos));
          }
          break;
        case HTML:
          b = _checkInlineHtml (aOut, sIn, nPos);
          if (b > 0)
          {
            nPos = b;
          }
          else
          {
            aOut.append ('<');
          }
          break;
        case ENTITY:
          aTmpSB.setLength (0);
          b = _checkInlineEntity (aTmpSB, sIn, nPos);
          if (b > 0)
          {
            // Remove leading '&' and trailing ';'
            aOut.append (new HCEntityNode (new HTMLEntity (aTmpSB.substring (1, aTmpSB.length () - 1)), " "));
            nPos = b;
          }
          else
          {
            aOut.append ('&');
          }
          break;
        case X_LINK_OPEN:
          aTmp.reset ();
          b = _recursiveEmitLine (aTmp, sIn, nPos + 2, EMarkToken.X_LINK_CLOSE);
          if (b > 0 && m_aConfig.getSpecialLinkEmitter () != null)
          {
            m_aConfig.getSpecialLinkEmitter ().emitSpan (aOut, aTmp);
            nPos = b + 1;
          }
          else
          {
            aOut.append (sIn.charAt (nPos));
          }
          break;
        case X_COPY:
          aOut.append (HCEntityNode.newCopy ());
          nPos += 2;
          break;
        case X_REG:
          aOut.append (new HCEntityNode (EHTMLEntity.copy, "(r)"));
          nPos += 2;
          break;
        case X_TRADE:
          aOut.append (new HCEntityNode (EHTMLEntity.trade, "TM"));
          nPos += 3;
          break;
        case X_NDASH:
          aOut.append (new HCEntityNode (EHTMLEntity.ndash, "--"));
          nPos++;
          break;
        case X_MDASH:
          aOut.append (new HCEntityNode (EHTMLEntity.mdash, "---"));
          nPos += 2;
          break;
        case X_HELLIP:
          aOut.append (new HCEntityNode (EHTMLEntity.hellip, "..."));
          nPos += 2;
          break;
        case X_LAQUO:
          aOut.append (new HCEntityNode (EHTMLEntity.laquo, "<<"));
          nPos++;
          break;
        case X_RAQUO:
          aOut.append (new HCEntityNode (EHTMLEntity.raquo, ">>"));
          nPos++;
          break;
        case X_RDQUO:
          aOut.append (new HCEntityNode (EHTMLEntity.rdquo, "\""));
          break;
        case X_LDQUO:
          aOut.append (new HCEntityNode (EHTMLEntity.ldquo, "\""));
          break;
        case ESCAPE:
          nPos++;
          aOut.append (sIn.charAt (nPos));
          break;
        default:
          aOut.append (sIn.charAt (nPos));
          break;
      }
      nPos++;
    }
    return -1;
  }

  /**
   * Turns every whitespace character into a space character.
   *
   * @param c
   *        Character to check
   * @return 32 is c was a whitespace, c otherwise
   */
  private static char _whitespaceToSpace (final char c)
  {
    return Character.isWhitespace (c) ? ' ' : c;
  }

  /**
   * Check if there is any markdown Token.
   *
   * @param sIn
   *        Input String.
   * @param nPos
   *        Starting position.
   * @return The Token.
   */
  @Nonnull
  private EMarkToken _getToken (final String sIn, final int nPos)
  {
    final char c0 = nPos > 0 ? _whitespaceToSpace (sIn.charAt (nPos - 1)) : ' ';
    final char c = _whitespaceToSpace (sIn.charAt (nPos));
    final char c1 = nPos + 1 < sIn.length () ? _whitespaceToSpace (sIn.charAt (nPos + 1)) : ' ';
    final char c2 = nPos + 2 < sIn.length () ? _whitespaceToSpace (sIn.charAt (nPos + 2)) : ' ';
    final char c3 = nPos + 3 < sIn.length () ? _whitespaceToSpace (sIn.charAt (nPos + 3)) : ' ';

    switch (c)
    {
      case '*':
        if (c1 == '*')
        {
          return c0 != ' ' || c2 != ' ' ? EMarkToken.STRONG_STAR : EMarkToken.EM_STAR;
        }
        return c0 != ' ' || c1 != ' ' ? EMarkToken.EM_STAR : EMarkToken.NONE;
      case '_':
        if (c1 == '_')
        {
          return c0 != ' ' || c2 != ' ' ? EMarkToken.STRONG_UNDERSCORE : EMarkToken.EM_UNDERSCORE;
        }
        if (m_bUseExtensions)
        {
          return Character.isLetterOrDigit (c0) &&
                 c0 != '_' &&
                 Character.isLetterOrDigit (c1) ? EMarkToken.NONE : EMarkToken.EM_UNDERSCORE;
        }
        return c0 != ' ' || c1 != ' ' ? EMarkToken.EM_UNDERSCORE : EMarkToken.NONE;
      case '~':
        if (m_bUseExtensions && c1 == '~')
        {
          return EMarkToken.STRIKE;
        }
        return EMarkToken.NONE;
      case '!':
        if (c1 == '[')
          return EMarkToken.IMAGE;
        return EMarkToken.NONE;
      case '[':
        if (m_bUseExtensions && c1 == '[')
          return EMarkToken.X_LINK_OPEN;
        return EMarkToken.LINK;
      case ']':
        if (m_bUseExtensions && c1 == ']')
          return EMarkToken.X_LINK_CLOSE;
        return EMarkToken.NONE;
      case '`':
        return c1 == '`' ? EMarkToken.CODE_DOUBLE : EMarkToken.CODE_SINGLE;
      case '\\':
        if (MarkdownHelper.isEscapeChar (c1))
          return EMarkToken.ESCAPE;
        return EMarkToken.NONE;
      case '<':
        if (m_bUseExtensions && c1 == '<')
          return EMarkToken.X_LAQUO;
        return EMarkToken.HTML;
      case '&':
        return EMarkToken.ENTITY;
      default:
        if (m_bUseExtensions)
        {
          switch (c)
          {
            case '-':
              if (c1 == '-')
                return c2 == '-' ? EMarkToken.X_MDASH : EMarkToken.X_NDASH;
              break;
            case '^':
              return c0 == '^' || c1 == '^' ? EMarkToken.NONE : EMarkToken.SUPER;
            case '>':
              if (c1 == '>')
                return EMarkToken.X_RAQUO;
              break;
            case '.':
              if (c1 == '.' && c2 == '.')
                return EMarkToken.X_HELLIP;
              break;
            case '(':
              if (c1 == 'C' && c2 == ')')
                return EMarkToken.X_COPY;
              if (c1 == 'R' && c2 == ')')
                return EMarkToken.X_REG;
              if (c1 == 'T' & c2 == 'M' & c3 == ')')
                return EMarkToken.X_TRADE;
              break;
            case '"':
              if (!Character.isLetterOrDigit (c0) && c1 != ' ')
                return EMarkToken.X_LDQUO;
              if (c0 != ' ' && !Character.isLetterOrDigit (c1))
                return EMarkToken.X_RDQUO;
              break;
          }
        }
        return EMarkToken.NONE;
    }
  }

  /**
   * Writes a set of markdown lines into the StringBuilder.
   *
   * @param aOut
   *        The StringBuilder to write to.
   * @param aLines
   *        The lines to write.
   */
  private void _emitMarkedLines (final MarkdownHCStack aOut, final Line aLines)
  {
    final StringBuilder aIn = new StringBuilder ();
    Line aLine = aLines;
    while (aLine != null)
    {
      if (!aLine.m_bIsEmpty)
      {
        aIn.append (aLine.m_sValue.substring (aLine.m_nLeading, aLine.m_sValue.length () - aLine.m_nTrailing));
        if (aLine.m_nTrailing >= 2 && !m_bConvertNewline2Br)
          aIn.append ("<br />");
      }
      if (aLine.m_aNext != null)
      {
        aIn.append ('\n');
        if (m_bConvertNewline2Br)
          aIn.append ("<br />");
      }
      aLine = aLine.m_aNext;
    }

    _recursiveEmitLine (aOut, aIn.toString (), 0, EMarkToken.NONE);
  }

  /**
   * Writes a set of raw lines into the StringBuilder.
   *
   * @param aOut
   *        The StringBuilder to write to.
   * @param aLines
   *        The lines to write.
   */
  private void _emitXMLLines (final MarkdownHCStack aOut, final Line aLines)
  {
    Line aLine = aLines;
    if (m_aConfig.isSafeMode ())
    {
      final StringBuilder aTmpSB = new StringBuilder ();
      while (aLine != null)
      {
        if (!aLine.m_bIsEmpty)
          aTmpSB.append (aLine.m_sValue.trim ());
        aLine = aLine.m_aNext;
      }
      final String sIn = aTmpSB.toString ();
      for (int nPos = 0; nPos < sIn.length (); nPos++)
      {
        if (sIn.charAt (nPos) == '<')
        {
          aTmpSB.setLength (0);
          final int nNewPos = MarkdownHelper.readXMLElement (aTmpSB, sIn, nPos, m_aConfig.isSafeMode ());
          if (nNewPos != -1)
          {
            // XXX Is this correct???
            aOut.append (aTmpSB.toString ());
            nPos = nNewPos;
          }
          else
          {
            aOut.append (sIn.charAt (nPos));
          }
        }
        else
        {
          aOut.append (sIn.charAt (nPos));
        }
      }
    }
    else
    {
      final StringBuilder aXML = new StringBuilder ();
      int nLines = 0;
      while (aLine != null)
      {
        if (!aLine.m_bIsEmpty)
        {
          aXML.append (aLine.m_sValue.trim ());
          ++nLines;
        }
        aLine = aLine.m_aNext;
      }

      String sXML = aXML.toString ();
      if (nLines == 1 && !sXML.contains ("/>") && !sXML.contains ("</"))
      {
        // Unclosed tag - parse as self-closed tag and push to stack
        // Workaround e.g. for <hr>
        sXML = sXML.substring (0, sXML.length () - 1) + "/>";
      }

      final IMicroDocument aDoc = MicroReader.readMicroXML (sXML);
      if (aDoc == null)
        throw new MarkdownException ("Failed to parse XML: " + sXML);

      aOut.append (new HCDOMWrapper (aDoc.getDocumentElement ().detachFromParent ()));
    }
  }

  private void _emitXMLComment (final MarkdownHCStack aOut, final Line aLines)
  {
    Line aLine = aLines;
    final StringBuilder aXML = new StringBuilder ();
    while (aLine != null)
    {
      if (!aLine.m_bIsEmpty)
      {
        // Append without trimming!
        aXML.append (aLine.m_sValue);
      }
      aXML.append ('\n');
      aLine = aLine.m_aNext;
    }

    // Trim only once, so that newlines before or after a comment start/close is
    // removed
    final String sContent = StringHelper.trimStartAndEnd (aXML.toString ().trim (),
                                                          XMLEmitter.COMMENT_START,
                                                          XMLEmitter.COMMENT_END);
    aOut.append (new HCCommentNode (sContent));
  }

  /**
   * Writes a code block into the StringBuilder.
   *
   * @param aOut
   *        The StringBuilder to write to.
   * @param aLines
   *        The lines to write.
   * @param sMeta
   *        Meta information.
   */
  private void _emitCodeLines (final MarkdownHCStack aOut,
                               final Line aLines,
                               @Nonnull final String sMeta,
                               final boolean bRemoveIndent)
  {
    Line aLine = aLines;
    if (m_aConfig.getCodeBlockEmitter () != null)
    {
      final ICommonsList <String> aList = new CommonsArrayList <> ();
      while (aLine != null)
      {
        if (aLine.m_bIsEmpty)
          aList.add ("");
        else
          aList.add (bRemoveIndent ? aLine.m_sValue.substring (4) : aLine.m_sValue);
        aLine = aLine.m_aNext;
      }
      m_aConfig.getCodeBlockEmitter ().emitBlock (aOut, aList, sMeta);
    }
    else
    {
      while (aLine != null)
      {
        if (!aLine.m_bIsEmpty)
          aOut.append (aLine.m_sValue.substring (4));
        aOut.append ('\n');
        aLine = aLine.m_aNext;
      }
    }
  }

  /**
   * interprets a plugin block into the StringBuilder.
   *
   * @param aOut
   *        The StringBuilder to write to.
   * @param aLines
   *        The lines to write.
   * @param sMeta
   *        Meta information.
   */
  protected void emitPluginLines (final MarkdownHCStack aOut, final Line aLines, @Nonnull final String sMeta)
  {
    Line aLine = aLines;

    String sIDPlugin = sMeta;
    String sParams = null;
    ICommonsMap <String, String> aParams = null;
    final int nIdxOfSpace = sMeta.indexOf (' ');
    if (nIdxOfSpace != -1)
    {
      sIDPlugin = sMeta.substring (0, nIdxOfSpace);
      sParams = sMeta.substring (nIdxOfSpace + 1);
      if (sParams != null)
      {
        aParams = parsePluginParams (sParams);
      }
    }

    if (aParams == null)
    {
      aParams = new CommonsHashMap <> ();
    }
    final ICommonsList <String> aList = new CommonsArrayList <> ();
    while (aLine != null)
    {
      if (aLine.m_bIsEmpty)
        aList.add ("");
      else
        aList.add (aLine.m_sValue);
      aLine = aLine.m_aNext;
    }

    final AbstractMarkdownPlugin aPlugin = m_aPlugins.get (sIDPlugin);
    if (aPlugin != null)
    {
      aPlugin.emit (aOut, aList, aParams);
    }
  }

  @Nonnull
  @ReturnsMutableCopy
  protected ICommonsMap <String, String> parsePluginParams (@Nonnull final String s)
  {
    final ICommonsMap <String, String> ret = new CommonsHashMap <> ();
    final Pattern aPattern = RegExCache.getPattern ("(\\w+)=\"*((?<=\")[^\"]+(?=\")|([^\\s]+))\"*");

    final Matcher aMatcher = aPattern.matcher (s);
    while (aMatcher.find ())
      ret.put (aMatcher.group (1), aMatcher.group (2));

    return ret;
  }
}
