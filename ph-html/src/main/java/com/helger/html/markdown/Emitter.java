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
package com.helger.html.markdown;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.annotation.Nonnull;

import com.helger.commons.microdom.IMicroDocument;
import com.helger.commons.microdom.IMicroElement;
import com.helger.commons.microdom.IMicroQName;
import com.helger.commons.microdom.serialize.MicroReader;
import com.helger.commons.regex.RegExPool;
import com.helger.commons.string.StringHelper;
import com.helger.commons.xml.serialize.XMLEmitter;
import com.helger.html.entities.EHTMLEntity;
import com.helger.html.entities.HTMLEntity;
import com.helger.html.hc.html.HCA;
import com.helger.html.hc.html.HCAbbr;
import com.helger.html.hc.html.HCCode;
import com.helger.html.hc.html.HCImg;
import com.helger.html.hc.html.HCLI;
import com.helger.html.hc.htmlext.HCUtils;
import com.helger.html.hc.impl.AbstractHCElement;
import com.helger.html.hc.impl.AbstractHCElementWithChildren;
import com.helger.html.hc.impl.HCCommentNode;
import com.helger.html.hc.impl.HCDOMWrapper;
import com.helger.html.hc.impl.HCEntityNode;

/**
 * Emitter class responsible for generating HTML output.
 *
 * @author René Jeschke &lt;rene_jeschke@yahoo.de&gt
 */
final class Emitter
{
  /** Link references. */
  private final Map <String, LinkRef> m_aLinkRefs = new HashMap <String, LinkRef> ();
  /** The configuration. */
  private final MarkdownConfiguration m_aConfig;
  /** Extension flag. */
  private boolean m_bUseExtensions = false;
  /** Newline flag. */
  private boolean m_bConvertNewline2Br = false;
  /** Plugins references **/
  private final Map <String, AbstractMarkdownPlugin> m_aPlugins = new HashMap <String, AbstractMarkdownPlugin> ();

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

  public void register (@Nonnull final AbstractMarkdownPlugin plugin)
  {
    m_aPlugins.put (plugin.getPluginID (), plugin);
  }

  /**
   * Adds a LinkRef to this set of LinkRefs.
   *
   * @param key
   *        The key/id.
   * @param linkRef
   *        The LinkRef.
   */
  public void addLinkRef (@Nonnull final String key, final LinkRef linkRef)
  {
    m_aLinkRefs.put (key.toLowerCase (Locale.US), linkRef);
  }

  /**
   * Transforms the given block recursively into HTML.
   *
   * @param out
   *        The StringBuilder to write to.
   * @param aRoot
   *        The Block to process.
   */
  public void emit (final HCStack out, final Block aRoot)
  {
    aRoot.removeSurroundingEmptyLines ();
    final IMarkdownDecorator aDecorator = m_aConfig.getDecorator ();

    switch (aRoot.m_eType)
    {
      case RULER:
        aDecorator.appendHorizontalRuler (out);
        return;
      case NONE:
      case XML:
      case XML_COMMENT:
        // No open required
        break;
      case HEADLINE:
        final AbstractHCElementWithChildren <?> aHX = aDecorator.openHeadline (out, aRoot.m_nHeadlineDepth);
        if (m_bUseExtensions && aRoot.m_sId != null)
          aHX.setID (aRoot.m_sId);
        break;
      case PARAGRAPH:
        aDecorator.openParagraph (out);
        break;
      case CODE:
      case FENCED_CODE:
        if (m_aConfig.getCodeBlockEmitter () == null)
          aDecorator.openCodeBlock (out);
        break;
      case BLOCKQUOTE:
        aDecorator.openBlockquote (out);
        break;
      case UNORDERED_LIST:
        aDecorator.openUnorderedList (out);
        break;
      case ORDERED_LIST:
        aDecorator.openOrderedList (out);
        break;
      case LIST_ITEM:
        final HCLI aLI = aDecorator.openListItem (out);
        if (m_bUseExtensions && aRoot.m_sId != null)
          aLI.setID (aRoot.m_sId);
        break;
      default:
        break;
    }

    if (aRoot.hasLines ())
    {
      _emitLines (out, aRoot);
    }
    else
    {
      Block block = aRoot.m_aBlocks;
      while (block != null)
      {
        emit (out, block);
        block = block.m_aNext;
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
        aDecorator.closeHeadline (out, aRoot.m_nHeadlineDepth);
        break;
      case PARAGRAPH:
        aDecorator.closeParagraph (out);
        break;
      case CODE:
      case FENCED_CODE:
        if (m_aConfig.getCodeBlockEmitter () == null)
          aDecorator.closeCodeBlock (out);
        break;
      case BLOCKQUOTE:
        aDecorator.closeBlockquote (out);
        break;
      case UNORDERED_LIST:
        aDecorator.closeUnorderedList (out);
        break;
      case ORDERED_LIST:
        aDecorator.closeOrderedList (out);
        break;
      case LIST_ITEM:
        aDecorator.closeListItem (out);
        break;
      default:
        break;
    }
  }

  /**
   * Transforms lines into HTML.
   *
   * @param out
   *        The StringBuilder to write to.
   * @param block
   *        The Block to process.
   */
  private void _emitLines (final HCStack out, final Block block)
  {
    switch (block.m_eType)
    {
      case CODE:
        _emitCodeLines (out, block.m_aLines, block.m_sMeta, true);
        break;
      case FENCED_CODE:
        _emitCodeLines (out, block.m_aLines, block.m_sMeta, false);
        break;
      case PLUGIN:
        emitPluginLines (out, block.m_aLines, block.m_sMeta);
        break;
      case XML:
        _emitXMLLines (out, block.m_aLines);
        break;
      case XML_COMMENT:
        _emitXMLComment (out, block.m_aLines);
        break;
      case PARAGRAPH:
        _emitMarkedLines (out, block.m_aLines);
        break;
      default:
        _emitMarkedLines (out, block.m_aLines);
        break;
    }
  }

  /**
   * Finds the position of the given Token in the given String.
   *
   * @param in
   *        The String to search on.
   * @param start
   *        The starting character position.
   * @param token
   *        The token to find.
   * @return The position of the token or -1 if none could be found.
   */
  private int _findInlineToken (final String in, final int start, final EMarkToken token)
  {
    int pos = start;
    while (pos < in.length ())
    {
      if (_getToken (in, pos) == token)
        return pos;
      pos++;
    }
    return -1;
  }

  /**
   * Checks if there is a valid markdown link definition.
   *
   * @param out
   *        The StringBuilder containing the generated output.
   * @param in
   *        Input String.
   * @param start
   *        Starting position.
   * @param token
   *        Either LINK or IMAGE.
   * @return The new position or -1 if there is no valid markdown link.
   */
  private int _checkInlineLink (final HCStack out, final String in, final int start, final EMarkToken token)
  {
    boolean isAbbrev = false;
    int pos = start + (token == EMarkToken.LINK ? 1 : 2);
    final StringBuilder temp = new StringBuilder ();

    temp.setLength (0);
    pos = Utils.readMdLinkId (temp, in, pos);
    if (pos < start)
      return -1;

    final String name = temp.toString ();
    String link = null, comment = null;
    final int oldPos = pos++;
    pos = Utils.skipSpaces (in, pos);
    if (pos < start)
    {
      final LinkRef lr = m_aLinkRefs.get (name.toLowerCase (Locale.US));
      if (lr == null)
        return -1;
      isAbbrev = lr.isAbbrev ();
      link = lr.getLink ();
      comment = lr.getTitle ();
      pos = oldPos;
    }
    else
      if (in.charAt (pos) == '(')
      {
        pos++;
        pos = Utils.skipSpaces (in, pos);
        if (pos < start)
          return -1;
        temp.setLength (0);
        final boolean useLt = in.charAt (pos) == '<';
        pos = useLt ? Utils.readUntil (temp, in, pos + 1, '>') : Utils.readMdLink (temp, in, pos);
        if (pos < start)
          return -1;
        if (useLt)
          pos++;
        link = temp.toString ();

        if (in.charAt (pos) == ' ')
        {
          pos = Utils.skipSpaces (in, pos);
          if (pos > start && in.charAt (pos) == '"')
          {
            pos++;
            temp.setLength (0);
            pos = Utils.readUntil (temp, in, pos, '"');
            if (pos < start)
              return -1;
            comment = temp.toString ();
            pos++;
            pos = Utils.skipSpaces (in, pos);
            if (pos == -1)
              return -1;
          }
        }
        if (in.charAt (pos) != ')')
          return -1;
      }
      else
        if (in.charAt (pos) == '[')
        {
          pos++;
          temp.setLength (0);
          pos = Utils.readRawUntil (temp, in, pos, ']');
          if (pos < start)
            return -1;
          final String id = temp.length () > 0 ? temp.toString () : name;
          final LinkRef lr = m_aLinkRefs.get (id.toLowerCase (Locale.US));
          if (lr != null)
          {
            link = lr.getLink ();
            comment = lr.getTitle ();
          }
        }
        else
        {
          final LinkRef lr = m_aLinkRefs.get (name.toLowerCase (Locale.US));
          if (lr == null)
            return -1;
          isAbbrev = lr.isAbbrev ();
          link = lr.getLink ();
          comment = lr.getTitle ();
          pos = oldPos;
        }

    if (link == null)
      return -1;

    if (token == EMarkToken.LINK)
    {
      if (isAbbrev && comment != null)
      {
        if (!m_bUseExtensions)
          return -1;
        out.push (new HCAbbr ().setTitle (comment));
        _recursiveEmitLine (out, name, 0, EMarkToken.NONE);
        out.pop ();
      }
      else
      {
        final HCA aLink = m_aConfig.getDecorator ().openLink (out);
        aLink.setHref (link);
        if (comment != null)
          aLink.setTitle (comment);
        _recursiveEmitLine (out, name, 0, EMarkToken.NONE);
        m_aConfig.getDecorator ().closeLink (out);
      }
    }
    else
    {
      final HCImg aImg = m_aConfig.getDecorator ().appendImage (out);
      aImg.setSrc (link);
      aImg.setAlt (name);
      if (comment != null)
        aImg.setTitle (comment);
    }

    return pos;
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
  private int _checkInlineHtml (final HCStack out, final String in, final int nStart)
  {
    final StringBuilder aTemp = new StringBuilder ();

    // Check for auto links
    aTemp.setLength (0);
    int nPos = Utils.readUntil (aTemp, in, nStart + 1, ':', ' ', '>', '\n');
    if (nPos != -1 && in.charAt (nPos) == ':' && MarkdownHTML.isLinkPrefix (aTemp.toString ()))
    {
      nPos = Utils.readUntil (aTemp, in, nPos, '>');
      if (nPos != -1)
      {
        final String sLink = aTemp.toString ();
        final HCA aLink = m_aConfig.getDecorator ().openLink (out);
        aLink.setHref (sLink).addChild (sLink);
        m_aConfig.getDecorator ().closeLink (out);
        return nPos;
      }
    }

    // Check for mailto or adress auto link
    aTemp.setLength (0);
    nPos = Utils.readUntil (aTemp, in, nStart + 1, '@', ' ', '>', '\n');
    if (nPos != -1 && in.charAt (nPos) == '@')
    {
      nPos = Utils.readUntil (aTemp, in, nPos, '>');
      if (nPos != -1)
      {
        final String sLink = aTemp.toString ();
        final HCA aLink = m_aConfig.getDecorator ().openLink (out);
        if (sLink.startsWith ("@"))
        {
          // address auto links
          final String sAddress = sLink.substring (1);
          final String sUrl = "https://maps.google.com/maps?q=" + sAddress.replace (' ', '+');
          aLink.setHref (sUrl).addChild (sAddress);
        }
        else
        {
          // mailto auto links
          aLink.setHref ("mailto:" + sLink).addChild (sLink);
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

      aTemp.setLength (0);
      final int t = Utils.readXMLElement (aTemp, in, nStart, m_aConfig.isSafeMode ());
      if (t != -1)
      {
        final String sElement = aTemp.toString ();
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
            final AbstractHCElement <?> aHC = HCUtils.createHCElementFromName (eRoot.getTagName ());
            if (aHC == null)
              throw new MarkdownException ("Failed to get HC element: " + eRoot.getTagName ());

            // Clone all attributes
            if (eRoot.hasAttributes ())
              for (final Map.Entry <IMicroQName, String> aEntry : eRoot.getAllQAttributes ().entrySet ())
                aHC.setCustomAttr (aEntry.getKey ().getName (), aEntry.getValue ());

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

        return t - 1;
      }
    }

    return -1;
  }

  /**
   * Check if this is a valid XML/HTML entity.
   *
   * @param out
   *        The StringBuilder to write to.
   * @param in
   *        Input String.
   * @param start
   *        Starting position
   * @return The new position or -1 if this entity in invalid.
   */
  private static int _checkInlineEntity (final StringBuilder out, final String in, final int start)
  {
    final int pos = Utils.readUntil (out, in, start, ';');
    if (pos < 0 || out.length () < 3)
      return -1;
    if (out.charAt (1) == '#')
    {
      if (out.charAt (2) == 'x' || out.charAt (2) == 'X')
      {
        if (out.length () < 4)
          return -1;
        for (int i = 3; i < out.length (); i++)
        {
          final char c = out.charAt (i);
          if ((c < '0' || c > '9') && ((c < 'a' || c > 'f') && (c < 'A' || c > 'F')))
            return -1;
        }
      }
      else
      {
        for (int i = 2; i < out.length (); i++)
        {
          final char c = out.charAt (i);
          if (c < '0' || c > '9')
            return -1;
        }
      }
      out.append (';');
    }
    else
    {
      for (int i = 1; i < out.length (); i++)
      {
        final char c = out.charAt (i);
        if ((c < 'a' || c > 'z') && (c < 'A' || c > 'Z'))
          return -1;
      }
      out.append (';');
      return EHTMLEntity.isValidEntityReference (out.toString ()) ? pos : -1;
    }

    return pos;
  }

  /**
   * Recursively scans through the given line, taking care of any markdown
   * stuff.
   *
   * @param out
   *        The StringBuilder to write to.
   * @param in
   *        Input String.
   * @param start
   *        Start position.
   * @param token
   *        The matching Token (for e.g. '*')
   * @return The position of the matching Token or -1 if token was NONE or no
   *         Token could be found.
   */
  private int _recursiveEmitLine (final HCStack out, final String in, final int start, final EMarkToken token)
  {
    int pos = start;
    int a;
    int b;
    final HCStack temp = new HCStack ();
    final StringBuilder tempSB = new StringBuilder ();
    while (pos < in.length ())
    {
      final EMarkToken mt = _getToken (in, pos);
      if (token != EMarkToken.NONE)
        if (mt == token ||
            (token == EMarkToken.EM_STAR && mt == EMarkToken.STRONG_STAR) ||
            (token == EMarkToken.EM_UNDERSCORE && mt == EMarkToken.STRONG_UNDERSCORE))
          return pos;

      switch (mt)
      {
        case IMAGE:
        case LINK:
          b = _checkInlineLink (out, in, pos, mt);
          if (b > 0)
          {
            pos = b;
          }
          else
          {
            out.append (in.charAt (pos));
          }
          break;
        case EM_STAR:
        case EM_UNDERSCORE:
          temp.reset ();
          b = _recursiveEmitLine (temp, in, pos + 1, mt);
          if (b > 0)
          {
            m_aConfig.getDecorator ().openEmphasis (out);
            out.append (temp.getRoot ());
            m_aConfig.getDecorator ().closeEmphasis (out);
            pos = b;
          }
          else
          {
            out.append (in.charAt (pos));
          }
          break;
        case STRONG_STAR:
        case STRONG_UNDERSCORE:
          temp.reset ();
          b = _recursiveEmitLine (temp, in, pos + 2, mt);
          if (b > 0)
          {
            m_aConfig.getDecorator ().openStrong (out);
            out.append (temp.getRoot ());
            m_aConfig.getDecorator ().closeStrong (out);
            pos = b + 1;
          }
          else
          {
            out.append (in.charAt (pos));
          }
          break;
        case STRIKE:
          temp.reset ();
          b = _recursiveEmitLine (temp, in, pos + 2, mt);
          if (b > 0)
          {
            m_aConfig.getDecorator ().openStrike (out);
            out.append (temp.getRoot ());
            m_aConfig.getDecorator ().closeStrike (out);
            pos = b + 1;
          }
          else
          {
            out.append (in.charAt (pos));
          }
          break;
        case SUPER:
          temp.reset ();
          b = _recursiveEmitLine (temp, in, pos + 1, mt);
          if (b > 0)
          {
            m_aConfig.getDecorator ().openSuper (out);
            out.append (temp.getRoot ());
            m_aConfig.getDecorator ().closeSuper (out);
            pos = b;
          }
          else
          {
            out.append (in.charAt (pos));
          }
          break;
        case CODE_SINGLE:
        case CODE_DOUBLE:
          a = pos + (mt == EMarkToken.CODE_DOUBLE ? 2 : 1);
          b = _findInlineToken (in, a, mt);
          if (b > 0)
          {
            pos = b + (mt == EMarkToken.CODE_DOUBLE ? 1 : 0);
            while (a < b && in.charAt (a) == ' ')
              a++;
            if (a < b)
            {
              while (in.charAt (b - 1) == ' ')
                b--;
              final HCCode aCode = m_aConfig.getDecorator ().openCodeSpan (out);
              aCode.addChild (in.substring (a, b));
              m_aConfig.getDecorator ().closeCodeSpan (out);
            }
          }
          else
          {
            out.append (in.charAt (pos));
          }
          break;
        case HTML:
          b = _checkInlineHtml (out, in, pos);
          if (b > 0)
          {
            pos = b;
          }
          else
          {
            out.append ('<');
          }
          break;
        case ENTITY:
          tempSB.setLength (0);
          b = _checkInlineEntity (tempSB, in, pos);
          if (b > 0)
          {
            // Remove leading '&' and trailing ';'
            out.append (new HCEntityNode (new HTMLEntity (tempSB.substring (1, tempSB.length () - 1)), " "));
            pos = b;
          }
          else
          {
            out.append ('&');
          }
          break;
        case X_LINK_OPEN:
          temp.reset ();
          b = _recursiveEmitLine (temp, in, pos + 2, EMarkToken.X_LINK_CLOSE);
          if (b > 0 && m_aConfig.getSpecialLinkEmitter () != null)
          {
            m_aConfig.getSpecialLinkEmitter ().emitSpan (out, temp);
            pos = b + 1;
          }
          else
          {
            out.append (in.charAt (pos));
          }
          break;
        case X_COPY:
          out.append (HCEntityNode.newCopy ());
          pos += 2;
          break;
        case X_REG:
          out.append (new HCEntityNode (EHTMLEntity.copy, "(r)"));
          pos += 2;
          break;
        case X_TRADE:
          out.append (new HCEntityNode (EHTMLEntity.trade, "TM"));
          pos += 3;
          break;
        case X_NDASH:
          out.append (new HCEntityNode (EHTMLEntity.ndash, "--"));
          pos++;
          break;
        case X_MDASH:
          out.append (new HCEntityNode (EHTMLEntity.mdash, "---"));
          pos += 2;
          break;
        case X_HELLIP:
          out.append (new HCEntityNode (EHTMLEntity.hellip, "..."));
          pos += 2;
          break;
        case X_LAQUO:
          out.append (new HCEntityNode (EHTMLEntity.laquo, "<<"));
          pos++;
          break;
        case X_RAQUO:
          out.append (new HCEntityNode (EHTMLEntity.raquo, ">>"));
          pos++;
          break;
        case X_RDQUO:
          out.append (new HCEntityNode (EHTMLEntity.rdquo, "\""));
          break;
        case X_LDQUO:
          out.append (new HCEntityNode (EHTMLEntity.ldquo, "\""));
          break;
        case ESCAPE:
          pos++;
          out.append (in.charAt (pos));
          break;
        default:
          out.append (in.charAt (pos));
          break;
      }
      pos++;
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
   * @param in
   *        Input String.
   * @param pos
   *        Starting position.
   * @return The Token.
   */
  @Nonnull
  private EMarkToken _getToken (final String in, final int pos)
  {
    final char c0 = pos > 0 ? _whitespaceToSpace (in.charAt (pos - 1)) : ' ';
    final char c = _whitespaceToSpace (in.charAt (pos));
    final char c1 = pos + 1 < in.length () ? _whitespaceToSpace (in.charAt (pos + 1)) : ' ';
    final char c2 = pos + 2 < in.length () ? _whitespaceToSpace (in.charAt (pos + 2)) : ' ';
    final char c3 = pos + 3 < in.length () ? _whitespaceToSpace (in.charAt (pos + 3)) : ' ';

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
          return Character.isLetterOrDigit (c0) && c0 != '_' && Character.isLetterOrDigit (c1) ? EMarkToken.NONE
                                                                                              : EMarkToken.EM_UNDERSCORE;
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
        if (Utils.isEscapeChar (c1))
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
   * @param out
   *        The StringBuilder to write to.
   * @param lines
   *        The lines to write.
   */
  private void _emitMarkedLines (final HCStack out, final Line lines)
  {
    final StringBuilder in = new StringBuilder ();
    Line line = lines;
    while (line != null)
    {
      if (!line.m_bIsEmpty)
      {
        in.append (line.m_sValue.substring (line.m_nLeading, line.m_sValue.length () - line.m_nTrailing));
        if (line.m_nTrailing >= 2 && !m_bConvertNewline2Br)
          in.append ("<br />");
      }
      if (line.m_aNext != null)
      {
        in.append ('\n');
        if (m_bConvertNewline2Br)
          in.append ("<br />");
      }
      line = line.m_aNext;
    }

    _recursiveEmitLine (out, in.toString (), 0, EMarkToken.NONE);
  }

  /**
   * Writes a set of raw lines into the StringBuilder.
   *
   * @param out
   *        The StringBuilder to write to.
   * @param lines
   *        The lines to write.
   */
  private void _emitXMLLines (final HCStack out, final Line lines)
  {
    Line line = lines;
    if (m_aConfig.isSafeMode ())
    {
      final StringBuilder temp = new StringBuilder ();
      while (line != null)
      {
        if (!line.m_bIsEmpty)
          temp.append (line.m_sValue.trim ());
        line = line.m_aNext;
      }
      final String in = temp.toString ();
      for (int pos = 0; pos < in.length (); pos++)
      {
        if (in.charAt (pos) == '<')
        {
          temp.setLength (0);
          final int t = Utils.readXMLElement (temp, in, pos, m_aConfig.isSafeMode ());
          if (t != -1)
          {
            // XXX Is this correct???
            out.append (temp.toString ());
            pos = t;
          }
          else
          {
            out.append (in.charAt (pos));
          }
        }
        else
        {
          out.append (in.charAt (pos));
        }
      }
    }
    else
    {
      final StringBuilder aXML = new StringBuilder ();
      int nLines = 0;
      while (line != null)
      {
        if (!line.m_bIsEmpty)
        {
          aXML.append (line.m_sValue.trim ());
          ++nLines;
        }
        line = line.m_aNext;
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

      out.append (new HCDOMWrapper (aDoc.getDocumentElement ().detachFromParent ()));
    }
  }

  private void _emitXMLComment (final HCStack out, final Line lines)
  {
    Line line = lines;
    final StringBuilder aXML = new StringBuilder ();
    while (line != null)
    {
      if (!line.m_bIsEmpty)
      {
        // Append without trimming!
        aXML.append (line.m_sValue);
      }
      aXML.append ('\n');
      line = line.m_aNext;
    }

    // Trim only once, so that newlines before or after a comment start/close is
    // removed
    final String sContent = StringHelper.trimStartAndEnd (aXML.toString ().trim (),
                                                          XMLEmitter.COMMENT_START,
                                                          XMLEmitter.COMMENT_END);
    out.append (new HCCommentNode (sContent));
  }

  /**
   * Writes a code block into the StringBuilder.
   *
   * @param out
   *        The StringBuilder to write to.
   * @param aLines
   *        The lines to write.
   * @param meta
   *        Meta information.
   */
  private void _emitCodeLines (final HCStack out,
                               final Line aLines,
                               @Nonnull final String meta,
                               final boolean removeIndent)
  {
    Line aLine = aLines;
    if (m_aConfig.getCodeBlockEmitter () != null)
    {
      final List <String> list = new ArrayList <String> ();
      while (aLine != null)
      {
        if (aLine.m_bIsEmpty)
          list.add ("");
        else
          list.add (removeIndent ? aLine.m_sValue.substring (4) : aLine.m_sValue);
        aLine = aLine.m_aNext;
      }
      m_aConfig.getCodeBlockEmitter ().emitBlock (out, list, meta);
    }
    else
    {
      while (aLine != null)
      {
        if (!aLine.m_bIsEmpty)
          out.append (aLine.m_sValue.substring (4));
        out.append ('\n');
        aLine = aLine.m_aNext;
      }
    }
  }

  /**
   * interprets a plugin block into the StringBuilder.
   *
   * @param out
   *        The StringBuilder to write to.
   * @param lines
   *        The lines to write.
   * @param meta
   *        Meta information.
   */
  protected void emitPluginLines (final HCStack out, final Line lines, @Nonnull final String meta)
  {
    Line line = lines;

    String idPlugin = meta;
    String sparams = null;
    Map <String, String> params = null;
    final int iow = meta.indexOf (' ');
    if (iow != -1)
    {
      idPlugin = meta.substring (0, iow);
      sparams = meta.substring (iow + 1);
      if (sparams != null)
      {
        params = parsePluginParams (sparams);
      }
    }

    if (params == null)
    {
      params = new HashMap <String, String> ();
    }
    final ArrayList <String> list = new ArrayList <String> ();
    while (line != null)
    {
      if (line.m_bIsEmpty)
        list.add ("");
      else
        list.add (line.m_sValue);
      line = line.m_aNext;
    }

    final AbstractMarkdownPlugin plugin = m_aPlugins.get (idPlugin);
    if (plugin != null)
    {
      plugin.emit (out, list, params);
    }
  }

  protected Map <String, String> parsePluginParams (final String s)
  {
    final Map <String, String> params = new HashMap <String, String> ();
    final Pattern p = RegExPool.getPattern ("(\\w+)=\"*((?<=\")[^\"]+(?=\")|([^\\s]+))\"*");

    final Matcher m = p.matcher (s);
    while (m.find ())
    {
      params.put (m.group (1), m.group (2));
    }

    return params;
  }

}
