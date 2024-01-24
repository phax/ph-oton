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
package com.helger.html.markdown;

import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.WillClose;

import com.helger.commons.ValueEnforcer;
import com.helger.commons.io.resource.IReadableResource;
import com.helger.commons.io.stream.NonBlockingStringReader;
import com.helger.commons.io.stream.StreamHelper;
import com.helger.commons.string.StringHelper;

/**
 * Markdown processor class.
 * <p>
 * Example usage:
 * </p>
 *
 * <pre>
 * <code>String result = MarkdownProcessor.process("This is ***TXTMARK***");
 * </code>
 * </pre>
 *
 * @author René Jeschke &lt;rene_jeschke@yahoo.de&gt;
 */
public class MarkdownProcessor
{
  /** The emitter. */
  private final Emitter m_aEmitter;
  /** The Configuration. */
  final MarkdownConfiguration m_aConfig;
  /** Extension flag. */
  private boolean m_bUseExtensions;

  public MarkdownProcessor ()
  {
    this (MarkdownConfiguration.DEFAULT);
  }

  /**
   * Constructor.
   *
   * @param aConfig
   *        The configuration to use. May not be <code>null</code>.
   */
  public MarkdownProcessor (@Nonnull final MarkdownConfiguration aConfig)
  {
    ValueEnforcer.notNull (aConfig, "Config");

    m_aConfig = aConfig;
    m_bUseExtensions = aConfig.isExtendedProfile ();
    m_aEmitter = new Emitter (m_aConfig);
  }

  /**
   * Reads all lines from our reader.
   * <p>
   * Takes care of markdown link references.
   * </p>
   *
   * @return A Block containing all lines.
   * @throws IOException
   *         If an IO error occurred.
   */
  @Nonnull
  private Block _readLines (@Nonnull final Reader aReader) throws IOException
  {
    final Block aBlock = new Block ();
    final StringBuilder aSB = new StringBuilder (80);
    int c = aReader.read ();
    LinkRef aLastLinkRef = null;
    while (c != -1)
    {
      aSB.setLength (0);
      int nPos = 0;
      boolean bEOL = false;
      while (!bEOL)
      {
        switch (c)
        {
          case -1:
            bEOL = true;
            break;
          case '\n':
            c = aReader.read ();
            if (c == '\r')
              c = aReader.read ();
            bEOL = true;
            break;
          case '\r':
            c = aReader.read ();
            if (c == '\n')
              c = aReader.read ();
            bEOL = true;
            break;
          case '\t':
          {
            final int nTabPos = nPos + (4 - (nPos & 3));
            while (nPos < nTabPos)
            {
              aSB.append (' ');
              nPos++;
            }
            c = aReader.read ();
            break;
          }
          default:
            nPos++;
            aSB.append ((char) c);
            c = aReader.read ();
            break;
        }
      }

      final Line aLine = new Line ();
      aLine.m_sValue = aSB.toString ();
      aLine.init ();

      // Check for link definitions
      boolean bIsLinkRef = false;
      String sID = null;
      String sLink = null;
      String sComment = null;
      if (!aLine.m_bIsEmpty && aLine.m_nLeading < 4 && aLine.m_sValue.charAt (aLine.m_nLeading) == '[')
      {
        aLine.m_nPos = aLine.m_nLeading + 1;
        // Read ID up to ']'
        sID = aLine.readUntil (']');
        // Is ID valid and are there any more characters?
        if (sID != null && aLine.m_nPos + 2 < aLine.m_sValue.length ())
        {
          // Check for ':' ([...]:...)
          if (aLine.m_sValue.charAt (aLine.m_nPos + 1) == ':')
          {
            aLine.m_nPos += 2;
            aLine.skipSpaces ();
            // Check for link syntax
            if (aLine.m_sValue.charAt (aLine.m_nPos) == '<')
            {
              aLine.m_nPos++;
              sLink = aLine.readUntil ('>');
              aLine.m_nPos++;
            }
            else
              sLink = aLine.readUntil (' ', '\n');

            // Is link valid?
            if (sLink != null)
            {
              // Any non-whitespace characters following?
              if (aLine.skipSpaces ())
              {
                final char ch = aLine.m_sValue.charAt (aLine.m_nPos);
                // Read comment
                if (ch == '\"' || ch == '\'' || ch == '(')
                {
                  aLine.m_nPos++;
                  sComment = aLine.readUntil (ch == '(' ? ')' : ch);
                  // Valid linkRef only if comment is valid
                  if (sComment != null)
                    bIsLinkRef = true;
                }
              }
              else
                bIsLinkRef = true;
            }
          }
        }
      }

      // To make compiler happy: add != null checks
      if (bIsLinkRef && sID != null && sLink != null)
      {
        if (sID.equalsIgnoreCase ("$profile$"))
        {
          m_bUseExtensions = sLink.equalsIgnoreCase ("extended");
          m_aEmitter.setUseExtensions (m_bUseExtensions);
          aLastLinkRef = null;
        }
        else
        {
          // Store linkRef and skip line
          final LinkRef aLinkRef = new LinkRef (sLink,
                                                sComment,
                                                sComment != null && (sLink.length () == 1 && sLink.charAt (0) == '*'));
          m_aEmitter.addLinkRef (sID, aLinkRef);
          if (sComment == null)
            aLastLinkRef = aLinkRef;
        }
      }
      else
      {
        sComment = null;
        // Check for multi-line linkRef
        if (!aLine.m_bIsEmpty && aLastLinkRef != null)
        {
          aLine.m_nPos = aLine.m_nLeading;
          final char ch = aLine.m_sValue.charAt (aLine.m_nPos);
          if (ch == '\"' || ch == '\'' || ch == '(')
          {
            aLine.m_nPos++;
            sComment = aLine.readUntil (ch == '(' ? ')' : ch);
          }
          if (sComment != null)
            aLastLinkRef.setTitle (sComment);

          aLastLinkRef = null;
        }

        // No multi-line linkRef, store line
        if (sComment == null)
        {
          aLine.m_nPos = 0;
          aBlock.appendLine (aLine);
        }
      }
    }

    return aBlock;
  }

  /**
   * Initializes a list block by separating it into list item blocks.
   *
   * @param aRoot
   *        The Block to process.
   */
  private void _initListBlock (@Nonnull final Block aRoot)
  {
    Line aLine = aRoot.m_aLines;
    aLine = aLine.m_aNext;
    while (aLine != null)
    {
      final ELineType eType = aLine.getLineType (m_bUseExtensions);
      if (eType == ELineType.OLIST ||
          eType == ELineType.ULIST ||
          (!aLine.m_bIsEmpty && aLine.m_bPrevEmpty && aLine.m_nLeading == 0))
      {
        aRoot.split (aLine.m_aPrevious).m_eType = EBlockType.LIST_ITEM;
      }
      aLine = aLine.m_aNext;
    }
    aRoot.split (aRoot.m_aLineTail).m_eType = EBlockType.LIST_ITEM;
  }

  /**
   * Recursively process the given Block.
   *
   * @param aRoot
   *        The Block to process.
   * @param bListMode
   *        Flag indicating that we're in a list item block.
   */
  private void _recurse (@Nonnull final Block aRoot, final boolean bListMode)
  {
    Block aBlock;
    Block aList;
    Line aLine = aRoot.m_aLines;

    if (bListMode)
    {
      aRoot.removeListIndent (m_bUseExtensions);
      if (m_bUseExtensions && aRoot.m_aLines != null && aRoot.m_aLines.getLineType (m_bUseExtensions) != ELineType.CODE)
      {
        aRoot.m_sID = aRoot.m_aLines.stripID ();
      }
    }

    while (aLine != null && aLine.m_bIsEmpty)
      aLine = aLine.m_aNext;
    if (aLine == null)
      return;

    while (aLine != null)
    {
      final ELineType eType = aLine.getLineType (m_bUseExtensions);
      switch (eType)
      {
        case OTHER:
        {
          final boolean bWasEmpty = aLine.m_bPrevEmpty;
          while (aLine != null && !aLine.m_bIsEmpty)
          {
            final ELineType eType2 = aLine.getLineType (m_bUseExtensions);
            if ((bListMode || m_bUseExtensions) && (eType2 == ELineType.OLIST || eType2 == ELineType.ULIST))
              break;
            if (m_bUseExtensions &&
                (eType2 == ELineType.CODE || eType2 == ELineType.FENCED_CODE || eType2 == ELineType.PLUGIN))
              break;
            if (eType2 == ELineType.HEADLINE ||
                eType2 == ELineType.HEADLINE1 ||
                eType2 == ELineType.HEADLINE2 ||
                eType2 == ELineType.HR ||
                eType2 == ELineType.BQUOTE ||
                eType2 == ELineType.XML ||
                eType2 == ELineType.XML_COMMENT)
              break;
            aLine = aLine.m_aNext;
          }
          final EBlockType eBlockType;
          if (aLine != null && !aLine.m_bIsEmpty)
          {
            eBlockType = (bListMode && !bWasEmpty) ? EBlockType.NONE : EBlockType.PARAGRAPH;
            aRoot.split (aLine.m_aPrevious).m_eType = eBlockType;
            aRoot.removeLeadingEmptyLines ();
          }
          else
          {
            eBlockType = (bListMode && (aLine == null || !aLine.m_bIsEmpty) && !bWasEmpty) ? EBlockType.NONE
                                                                                           : EBlockType.PARAGRAPH;
            aRoot.split (aLine == null ? aRoot.m_aLineTail : aLine).m_eType = eBlockType;
            aRoot.removeLeadingEmptyLines ();
          }
          aLine = aRoot.m_aLines;
          break;
        }
        case CODE:
          while (aLine != null && (aLine.m_bIsEmpty || aLine.m_nLeading > 3))
          {
            aLine = aLine.m_aNext;
          }
          aBlock = aRoot.split (aLine != null ? aLine.m_aPrevious : aRoot.m_aLineTail);
          aBlock.m_eType = EBlockType.CODE;
          aBlock.removeSurroundingEmptyLines ();
          break;
        case XML:
        case XML_COMMENT:
          if (aLine.m_aPrevious != null)
          {
            // FIXME ... this looks wrong
            aRoot.split (aLine.m_aPrevious);
          }
          aRoot.split (aLine.m_aXmlEndLine).m_eType = eType == ELineType.XML ? EBlockType.XML : EBlockType.XML_COMMENT;
          aRoot.removeLeadingEmptyLines ();
          aLine = aRoot.m_aLines;
          break;
        case BQUOTE:
          while (aLine != null)
          {
            if (!aLine.m_bIsEmpty &&
                aLine.m_bPrevEmpty &&
                aLine.m_nLeading == 0 &&
                aLine.getLineType (m_bUseExtensions) != ELineType.BQUOTE)
              break;
            aLine = aLine.m_aNext;
          }
          aBlock = aRoot.split (aLine != null ? aLine.m_aPrevious : aRoot.m_aLineTail);
          aBlock.m_eType = EBlockType.BLOCKQUOTE;
          aBlock.removeSurroundingEmptyLines ();
          aBlock.removeBlockQuotePrefix ();
          _recurse (aBlock, false);
          aLine = aRoot.m_aLines;
          break;
        case HR:
          if (aLine.m_aPrevious != null)
          {
            // FIXME ... this looks wrong
            aRoot.split (aLine.m_aPrevious);
          }
          aRoot.split (aLine).m_eType = EBlockType.RULER;
          aRoot.removeLeadingEmptyLines ();
          aLine = aRoot.m_aLines;
          break;
        case FENCED_CODE:
          aLine = aLine.m_aNext;
          while (aLine != null)
          {
            if (aLine.getLineType (m_bUseExtensions) == ELineType.FENCED_CODE)
              break;
            // TODO ... is this really necessary? Maybe add a special
            // flag?
            aLine = aLine.m_aNext;
          }
          if (aLine != null)
            aLine = aLine.m_aNext;
          aBlock = aRoot.split (aLine != null ? aLine.m_aPrevious : aRoot.m_aLineTail);
          aBlock.m_eType = EBlockType.FENCED_CODE;
          aBlock.m_sMeta = MarkdownHelper.getMetaFromFence (aBlock.m_aLines.m_sValue);
          aBlock.m_aLines.setEmpty ();
          if (aBlock.m_aLineTail.getLineType (m_bUseExtensions) == ELineType.FENCED_CODE)
            aBlock.m_aLineTail.setEmpty ();
          aBlock.removeSurroundingEmptyLines ();
          break;
        case PLUGIN:
          aLine = aLine.m_aNext;
          while (aLine != null)
          {
            if (aLine.getLineType (m_bUseExtensions) == ELineType.PLUGIN)
              break;
            // TODO ... is this really necessary? Maybe add a special
            // flag?
            aLine = aLine.m_aNext;
          }
          if (aLine != null)
            aLine = aLine.m_aNext;
          aBlock = aRoot.split (aLine != null ? aLine.m_aPrevious : aRoot.m_aLineTail);
          aBlock.m_eType = EBlockType.PLUGIN;
          aBlock.m_sMeta = MarkdownHelper.getMetaFromFence (aBlock.m_aLines.m_sValue);
          aBlock.m_aLines.setEmpty ();
          if (aBlock.m_aLineTail.getLineType (m_bUseExtensions) == ELineType.PLUGIN)
            aBlock.m_aLineTail.setEmpty ();
          aBlock.removeSurroundingEmptyLines ();
          break;
        case HEADLINE:
        case HEADLINE1:
        case HEADLINE2:
          if (aLine.m_aPrevious != null)
            aRoot.split (aLine.m_aPrevious);
          if (eType != ELineType.HEADLINE)
            aLine.m_aNext.setEmpty ();
          aBlock = aRoot.split (aLine);
          aBlock.m_eType = EBlockType.HEADLINE;
          if (eType != ELineType.HEADLINE)
            aBlock.m_nHeadlineDepth = eType == ELineType.HEADLINE1 ? 1 : 2;
          if (m_bUseExtensions)
            aBlock.m_sID = aBlock.m_aLines.stripID ();
          aBlock.transfromHeadline ();
          aRoot.removeLeadingEmptyLines ();
          aLine = aRoot.m_aLines;
          break;
        case OLIST:
        case ULIST:
          while (aLine != null)
          {
            final ELineType eType2 = aLine.getLineType (m_bUseExtensions);
            if (!aLine.m_bIsEmpty &&
                (aLine.m_bPrevEmpty &&
                 aLine.m_nLeading == 0 &&
                 !(eType2 == ELineType.OLIST || eType2 == ELineType.ULIST)))
              break;
            aLine = aLine.m_aNext;
          }
          aList = aRoot.split (aLine != null ? aLine.m_aPrevious : aRoot.m_aLineTail);
          aList.m_eType = eType == ELineType.OLIST ? EBlockType.ORDERED_LIST : EBlockType.UNORDERED_LIST;
          aList.m_aLines.m_bPrevEmpty = false;
          aList.removeSurroundingEmptyLines ();
          aList.m_aLines.m_bPrevEmpty = false;
          _initListBlock (aList);
          aBlock = aList.m_aBlocks;
          while (aBlock != null)
          {
            _recurse (aBlock, true);
            aBlock = aBlock.m_aNext;
          }
          aList.expandListParagraphs ();
          break;
        default:
          aLine = aLine.m_aNext;
          break;
      }
    }
  }

  @Nonnull
  public MarkdownProcessingResult process (@Nonnull final IReadableResource aRes) throws IOException
  {
    ValueEnforcer.notNull (aRes, "Resource");

    return process (aRes.getReader (m_aConfig.getEncoding ()));
  }

  @Nonnull
  public MarkdownProcessingResult process (@Nonnull final InputStream aIS) throws IOException
  {
    ValueEnforcer.notNull (aIS, "InputStream");

    return process (StreamHelper.getBuffered (StreamHelper.createReader (aIS, m_aConfig.getEncoding ())));
  }

  @Nonnull
  public MarkdownProcessingResult process (@Nullable final String sText) throws IOException
  {
    return process (new NonBlockingStringReader (StringHelper.getNotNull (sText)));
  }

  /**
   * Does all the processing.
   *
   * @param aReader
   *        The reader to read from
   * @return The processing result.
   * @throws IOException
   *         If an IO error occurred.
   */
  @Nonnull
  public MarkdownProcessingResult process (@Nonnull @WillClose final Reader aReader) throws IOException
  {
    try
    {
      final Block aParent = _readLines (aReader);
      aParent.removeSurroundingEmptyLines ();
      _recurse (aParent, false);

      final MarkdownHCStack aOut = new MarkdownHCStack ();
      Block aBlock = aParent.m_aBlocks;
      while (aBlock != null)
      {
        m_aEmitter.emit (aOut, aBlock);
        aBlock = aBlock.m_aNext;
      }
      return new MarkdownProcessingResult (aOut);
    }
    finally
    {
      StreamHelper.close (aReader);
    }
  }
}
