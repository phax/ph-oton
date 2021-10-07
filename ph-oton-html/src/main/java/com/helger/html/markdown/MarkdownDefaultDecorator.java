/*
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
package com.helger.html.markdown;

import javax.annotation.Nonnull;

import com.helger.html.hc.html.IHCElementWithChildren;
import com.helger.html.hc.html.embedded.HCImg;
import com.helger.html.hc.html.grouping.HCBlockQuote;
import com.helger.html.hc.html.grouping.HCHR;
import com.helger.html.hc.html.grouping.HCLI;
import com.helger.html.hc.html.grouping.HCOL;
import com.helger.html.hc.html.grouping.HCP;
import com.helger.html.hc.html.grouping.HCPre;
import com.helger.html.hc.html.grouping.HCUL;
import com.helger.html.hc.html.sections.HCH1;
import com.helger.html.hc.html.sections.HCH2;
import com.helger.html.hc.html.sections.HCH3;
import com.helger.html.hc.html.sections.HCH4;
import com.helger.html.hc.html.sections.HCH5;
import com.helger.html.hc.html.sections.HCH6;
import com.helger.html.hc.html.textlevel.HCA;
import com.helger.html.hc.html.textlevel.HCCode;
import com.helger.html.hc.html.textlevel.HCEM;
import com.helger.html.hc.html.textlevel.HCS;
import com.helger.html.hc.html.textlevel.HCStrong;
import com.helger.html.hc.html.textlevel.HCSup;

/**
 * Default Decorator implementation.
 * <p>
 * Example for a user Decorator having a class attribute on &lt;p&gt; tags.
 * </p>
 *
 * <pre>
 * <code>public class MyDecorator extends DefaultDecorator
 * {
 *     &#64;Override
 *     public void openParagraph(StringBuilder out)
 *     {
 *         out.append("&lt;p class=\"myclass\"&gt;");
 *     }
 * }
 * </code>
 * </pre>
 *
 * @author René Jeschke &lt;rene_jeschke@yahoo.de&gt;
 */
public class MarkdownDefaultDecorator implements IMarkdownDecorator
{
  public MarkdownDefaultDecorator ()
  {}

  public void openParagraph (@Nonnull final MarkdownHCStack out)
  {
    out.push (new HCP ());
  }

  public void closeParagraph (@Nonnull final MarkdownHCStack out)
  {
    out.pop ();
  }

  public void openBlockquote (@Nonnull final MarkdownHCStack out)
  {
    out.push (new HCBlockQuote ());
  }

  public void closeBlockquote (@Nonnull final MarkdownHCStack out)
  {
    out.pop ();
  }

  public void openCodeBlock (@Nonnull final MarkdownHCStack out)
  {
    out.push (new HCPre ());
    out.push (new HCCode ());
  }

  public void closeCodeBlock (@Nonnull final MarkdownHCStack out)
  {
    out.pop ();
    out.pop ();
  }

  @Nonnull
  public HCCode openCodeSpan (@Nonnull final MarkdownHCStack out)
  {
    return out.push (new HCCode ());
  }

  public void closeCodeSpan (@Nonnull final MarkdownHCStack out)
  {
    out.pop ();
  }

  @Nonnull
  public IHCElementWithChildren <?> openHeadline (@Nonnull final MarkdownHCStack out, final int level)
  {
    switch (level)
    {
      case 1:
        return out.push (new HCH1 ());
      case 2:
        return out.push (new HCH2 ());
      case 3:
        return out.push (new HCH3 ());
      case 4:
        return out.push (new HCH4 ());
      case 5:
        return out.push (new HCH5 ());
      case 6:
        return out.push (new HCH6 ());
    }
    throw new IllegalArgumentException ();
  }

  public void closeHeadline (@Nonnull final MarkdownHCStack out, final int level)
  {
    out.pop ();
  }

  public void openStrong (@Nonnull final MarkdownHCStack out)
  {
    out.push (new HCStrong ());
  }

  public void closeStrong (@Nonnull final MarkdownHCStack out)
  {
    out.pop ();
  }

  public void openStrike (@Nonnull final MarkdownHCStack out)
  {
    out.push (new HCS ());
  }

  public void closeStrike (@Nonnull final MarkdownHCStack out)
  {
    out.pop ();
  }

  public void openEmphasis (@Nonnull final MarkdownHCStack out)
  {
    out.push (new HCEM ());
  }

  public void closeEmphasis (@Nonnull final MarkdownHCStack out)
  {
    out.pop ();
  }

  public void openSuper (@Nonnull final MarkdownHCStack out)
  {
    out.push (new HCSup ());
  }

  public void closeSuper (@Nonnull final MarkdownHCStack out)
  {
    out.pop ();
  }

  public void openOrderedList (@Nonnull final MarkdownHCStack out)
  {
    out.push (new HCOL ());
  }

  public void closeOrderedList (@Nonnull final MarkdownHCStack out)
  {
    out.pop ();
  }

  public void openUnorderedList (@Nonnull final MarkdownHCStack out)
  {
    out.push (new HCUL ());
  }

  public void closeUnorderedList (@Nonnull final MarkdownHCStack out)
  {
    out.pop ();
  }

  @Nonnull
  public HCLI openListItem (@Nonnull final MarkdownHCStack out)
  {
    return out.push (new HCLI ());
  }

  public void closeListItem (@Nonnull final MarkdownHCStack out)
  {
    out.pop ();
  }

  public void appendHorizontalRuler (@Nonnull final MarkdownHCStack out)
  {
    out.append (new HCHR ());
  }

  @Nonnull
  public HCA openLink (@Nonnull final MarkdownHCStack out)
  {
    return out.push (new HCA ());
  }

  public void closeLink (@Nonnull final MarkdownHCStack out)
  {
    out.pop ();
  }

  @Nonnull
  public HCImg appendImage (@Nonnull final MarkdownHCStack out)
  {
    final HCImg ret = new HCImg ();
    out.append (ret);
    return ret;
  }
}
