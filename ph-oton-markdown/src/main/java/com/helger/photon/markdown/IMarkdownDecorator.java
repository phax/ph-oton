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
package com.helger.photon.markdown;

import org.jspecify.annotations.NonNull;

import com.helger.html.hc.html.IHCElementWithChildren;
import com.helger.html.hc.html.embedded.HCImg;
import com.helger.html.hc.html.grouping.HCLI;
import com.helger.html.hc.html.textlevel.HCA;
import com.helger.html.hc.html.textlevel.HCCode;

/**
 * Decorator interface.
 *
 * @author René Jeschke &lt;rene_jeschke@yahoo.de&gt;
 */
public interface IMarkdownDecorator
{
  /**
   * Called when a paragraph is opened.
   * <p>
   * Default implementation is:
   * </p>
   *
   * <pre>
   * <code>out.append("&lt;p&gt;");</code>
   * </pre>
   *
   * @param out
   *        The StringBuilder to write to.
   */
  void openParagraph (@NonNull MarkdownHCStack out);

  /**
   * Called when a paragraph is closed.
   * <p>
   * Default implementation is:
   * </p>
   *
   * <pre>
   * <code>out.append("&lt;/p&gt;\n");</code>
   * </pre>
   *
   * @param out
   *        The StringBuilder to write to.
   */
  void closeParagraph (@NonNull MarkdownHCStack out);

  /**
   * Called when a blockquote is opened. Default implementation is:
   *
   * <pre>
   * <code>out.append("&lt;blockquote&gt;");</code>
   * </pre>
   *
   * @param out
   *        The StringBuilder to write to.
   */
  void openBlockquote (@NonNull MarkdownHCStack out);

  /**
   * Called when a blockquote is closed.
   * <p>
   * Default implementation is:
   * </p>
   *
   * <pre>
   * <code>out.append("&lt;/blockquote&gt;\n");</code>
   * </pre>
   *
   * @param out
   *        The StringBuilder to write to.
   */
  void closeBlockquote (@NonNull MarkdownHCStack out);

  /**
   * Called when a code block is opened.
   * <p>
   * Default implementation is:
   * </p>
   *
   * <pre>
   * <code>out.append("&lt;pre&gt;&lt;code&gt;");</code>
   * </pre>
   *
   * @param out
   *        The StringBuilder to write to.
   */
  void openCodeBlock (@NonNull MarkdownHCStack out);

  /**
   * Called when a code block is closed.
   * <p>
   * Default implementation is:
   * </p>
   *
   * <pre>
   * <code>out.append("&lt;/code&gt;&lt;/pre&gt;\n");</code>
   * </pre>
   *
   * @param out
   *        The StringBuilder to write to.
   */
  void closeCodeBlock (@NonNull MarkdownHCStack out);

  /**
   * Called when a code span is opened.
   * <p>
   * Default implementation is:
   * </p>
   *
   * <pre>
   * <code>out.append("&lt;code&gt;");</code>
   * </pre>
   *
   * @param out
   *        The StringBuilder to write to.
   * @return code element
   */
  HCCode openCodeSpan (@NonNull MarkdownHCStack out);

  /**
   * Called when a code span is closed.
   * <p>
   * Default implementation is:
   * </p>
   *
   * <pre>
   * <code>out.append("&lt;/code&gt;");</code>
   * </pre>
   *
   * @param out
   *        The StringBuilder to write to.
   */
  void closeCodeSpan (@NonNull MarkdownHCStack out);

  /**
   * Called when a headline is opened.
   * <p>
   * <strong>Note:</strong> Don't close the HTML tag!
   * </p>
   * <p>
   * Default implementation is:
   * </p>
   *
   * <pre>
   * <code> out.append("&lt;h");
   * out.append(level);</code>
   * </pre>
   *
   * @param out
   *        The StringBuilder to write to.
   * @param level
   *        Headline level
   * @return Headline element
   */
  @NonNull
  IHCElementWithChildren <?> openHeadline (@NonNull MarkdownHCStack out, int level);

  /**
   * Called when a headline is closed.
   * <p>
   * Default implementation is:
   * </p>
   *
   * <pre>
   * <code> out.append("&lt;/h");
   * out.append(level);
   * out.append("&gt;\n");</code>
   * </pre>
   *
   * @param out
   *        The StringBuilder to write to.
   * @param level
   *        Headline level
   */
  void closeHeadline (@NonNull MarkdownHCStack out, int level);

  /**
   * Called when a strong span is opened.
   * <p>
   * Default implementation is:
   * </p>
   *
   * <pre>
   * <code>out.append("&lt;strong&gt;");</code>
   * </pre>
   *
   * @param out
   *        The StringBuilder to write to.
   */
  void openStrong (@NonNull MarkdownHCStack out);

  /**
   * Called when a strong span is closed.
   * <p>
   * Default implementation is:
   * </p>
   *
   * <pre>
   * <code>out.append("&lt;/strong&gt;");</code>
   * </pre>
   *
   * @param out
   *        The StringBuilder to write to.
   */
  void closeStrong (@NonNull MarkdownHCStack out);

  /**
   * Called when a strike span is opened.
   * <p>
   * Default implementation is:
   * </p>
   *
   * <pre>
   * <code>out.append("&lt;s&gt;");</code>
   * </pre>
   *
   * @param out
   *        The StringBuilder to write to.
   */
  void openStrike (@NonNull MarkdownHCStack out);

  /**
   * Called when a strike span is closed.
   * <p>
   * Default implementation is:
   * </p>
   *
   * <pre>
   * <code>out.append("&lt;/s&gt;");</code>
   * </pre>
   *
   * @param out
   *        The StringBuilder to write to.
   */
  void closeStrike (@NonNull MarkdownHCStack out);

  /**
   * Called when an emphasis span is opened.
   * <p>
   * Default implementation is:
   * </p>
   *
   * <pre>
   * <code>out.append("&lt;em&gt;");</code>
   * </pre>
   *
   * @param out
   *        The StringBuilder to write to.
   */
  void openEmphasis (@NonNull MarkdownHCStack out);

  /**
   * Called when an emphasis span is closed.
   * <p>
   * Default implementation is:
   * </p>
   *
   * <pre>
   * <code>out.append("&lt;/em&gt;");</code>
   * </pre>
   *
   * @param out
   *        The StringBuilder to write to.
   */
  void closeEmphasis (@NonNull MarkdownHCStack out);

  /**
   * Called when a superscript span is opened.
   * <p>
   * Default implementation is:
   * </p>
   *
   * <pre>
   * <code>out.append("&lt;sup&gt;");</code>
   * </pre>
   *
   * @param out
   *        The StringBuilder to write to.
   */
  void openSuper (@NonNull MarkdownHCStack out);

  /**
   * Called when a superscript span is closed.
   * <p>
   * Default implementation is:
   * </p>
   *
   * <pre>
   * <code>out.append("&lt;/sup&gt;");</code>
   * </pre>
   *
   * @param out
   *        The StringBuilder to write to.
   */
  void closeSuper (@NonNull MarkdownHCStack out);

  /**
   * Called when an ordered list is opened.
   * <p>
   * Default implementation is:
   * </p>
   *
   * <pre>
   * <code>out.append("&lt;ol&gt;\n");</code>
   * </pre>
   *
   * @param out
   *        The StringBuilder to write to.
   */
  void openOrderedList (@NonNull MarkdownHCStack out);

  /**
   * Called when an ordered list is closed.
   * <p>
   * Default implementation is:
   * </p>
   *
   * <pre>
   * <code>out.append("&lt;/ol&gt;\n");</code>
   * </pre>
   *
   * @param out
   *        The StringBuilder to write to.
   */
  void closeOrderedList (@NonNull MarkdownHCStack out);

  /**
   * Called when an unordered list is opened.
   * <p>
   * Default implementation is:
   * </p>
   *
   * <pre>
   * <code>out.append("&lt;ul&gt;\n");</code>
   * </pre>
   *
   * @param out
   *        The StringBuilder to write to.
   */
  void openUnorderedList (@NonNull MarkdownHCStack out);

  /**
   * Called when an unordered list is closed.
   * <p>
   * Default implementation is:
   * </p>
   *
   * <pre>
   * <code>out.append("&lt;/ul&gt;\n");</code>
   * </pre>
   *
   * @param out
   *        The StringBuilder to write to.
   */
  void closeUnorderedList (@NonNull MarkdownHCStack out);

  /**
   * Called when a list item is opened.
   * <p>
   * <strong>Note:</strong> Don't close the HTML tag!
   * </p>
   * <p>
   * Default implementation is:
   * </p>
   *
   * <pre>
   * <code>out.append("&lt;li");</code>
   * </pre>
   *
   * @param out
   *        The StringBuilder to write to.
   * @return List item element
   */
  @NonNull
  HCLI openListItem (@NonNull MarkdownHCStack out);

  /**
   * Called when a list item is closed.
   * <p>
   * Default implementation is:
   * </p>
   *
   * <pre>
   * <code>out.append("&lt;/li&gt;\n");</code>
   * </pre>
   *
   * @param out
   *        The StringBuilder to write to.
   */
  void closeListItem (@NonNull MarkdownHCStack out);

  /**
   * Called when a horizontal ruler is encountered.
   * <p>
   * Default implementation is:
   * </p>
   *
   * <pre>
   * <code>out.append("&lt;hr /&gt;\n");</code>
   * </pre>
   *
   * @param out
   *        The StringBuilder to write to.
   */
  void appendHorizontalRuler (@NonNull MarkdownHCStack out);

  /**
   * Called when a link is opened.
   * <p>
   * <strong>Note:</strong> Don't close the HTML tag!
   * </p>
   * <p>
   * Default implementation is:
   * </p>
   *
   * <pre>
   * <code>out.append("&lt;a");</code>
   * </pre>
   *
   * @param out
   *        The stack to write to.
   * @return Link element
   */
  @NonNull
  HCA openLink (@NonNull MarkdownHCStack out);

  void closeLink (@NonNull MarkdownHCStack out);

  /**
   * Called when an image is opened.
   * <p>
   * <strong>Note:</strong> Don't close the HTML tag!
   * </p>
   * <p>
   * Default implementation is:
   * </p>
   *
   * <pre>
   * <code>out.append("&lt;img");</code>
   * </pre>
   *
   * @param out
   *        The StringBuilder to write to.
   * @return Image element
   */
  @NonNull
  HCImg appendImage (@NonNull MarkdownHCStack out);
}
