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

import javax.annotation.Nonnull;

import com.helger.html.hc.html.HCA;
import com.helger.html.hc.html.HCBlockQuote;
import com.helger.html.hc.html.HCCode;
import com.helger.html.hc.html.HCEM;
import com.helger.html.hc.html.HCH1;
import com.helger.html.hc.html.HCH2;
import com.helger.html.hc.html.HCH3;
import com.helger.html.hc.html.HCH4;
import com.helger.html.hc.html.HCH5;
import com.helger.html.hc.html.HCH6;
import com.helger.html.hc.html.HCHR;
import com.helger.html.hc.html.HCImg;
import com.helger.html.hc.html.HCLI;
import com.helger.html.hc.html.HCOL;
import com.helger.html.hc.html.HCP;
import com.helger.html.hc.html.HCPre;
import com.helger.html.hc.html.HCS;
import com.helger.html.hc.html.HCStrong;
import com.helger.html.hc.html.HCSup;
import com.helger.html.hc.html.HCUL;
import com.helger.html.hc.impl.AbstractHCElementWithChildren;

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

  public void openParagraph (@Nonnull final HCStack out)
  {
    out.push (new HCP ());
  }

  public void closeParagraph (@Nonnull final HCStack out)
  {
    out.pop ();
  }

  public void openBlockquote (@Nonnull final HCStack out)
  {
    out.push (new HCBlockQuote ());
  }

  public void closeBlockquote (@Nonnull final HCStack out)
  {
    out.pop ();
  }

  public void openCodeBlock (@Nonnull final HCStack out)
  {
    out.push (new HCPre ());
    out.push (new HCCode ());
  }

  public void closeCodeBlock (@Nonnull final HCStack out)
  {
    out.pop ();
    out.pop ();
  }

  @Nonnull
  public HCCode openCodeSpan (@Nonnull final HCStack out)
  {
    return out.push (new HCCode ());
  }

  public void closeCodeSpan (@Nonnull final HCStack out)
  {
    out.pop ();
  }

  @Nonnull
  public AbstractHCElementWithChildren <?> openHeadline (@Nonnull final HCStack out, final int level)
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

  public void closeHeadline (@Nonnull final HCStack out, final int level)
  {
    out.pop ();
  }

  public void openStrong (@Nonnull final HCStack out)
  {
    out.push (new HCStrong ());
  }

  public void closeStrong (@Nonnull final HCStack out)
  {
    out.pop ();
  }

  public void openStrike (@Nonnull final HCStack out)
  {
    out.push (new HCS ());
  }

  public void closeStrike (@Nonnull final HCStack out)
  {
    out.pop ();
  }

  public void openEmphasis (@Nonnull final HCStack out)
  {
    out.push (new HCEM ());
  }

  public void closeEmphasis (@Nonnull final HCStack out)
  {
    out.pop ();
  }

  public void openSuper (@Nonnull final HCStack out)
  {
    out.push (new HCSup ());
  }

  public void closeSuper (@Nonnull final HCStack out)
  {
    out.pop ();
  }

  public void openOrderedList (@Nonnull final HCStack out)
  {
    out.push (new HCOL ());
  }

  public void closeOrderedList (@Nonnull final HCStack out)
  {
    out.pop ();
  }

  public void openUnorderedList (@Nonnull final HCStack out)
  {
    out.push (new HCUL ());
  }

  public void closeUnorderedList (@Nonnull final HCStack out)
  {
    out.pop ();
  }

  @Nonnull
  public HCLI openListItem (@Nonnull final HCStack out)
  {
    return out.push (new HCLI ());
  }

  public void closeListItem (@Nonnull final HCStack out)
  {
    out.pop ();
  }

  public void appendHorizontalRuler (@Nonnull final HCStack out)
  {
    out.append (new HCHR ());
  }

  @Nonnull
  public HCA openLink (@Nonnull final HCStack out)
  {
    return out.push (new HCA ());
  }

  public void closeLink (@Nonnull final HCStack out)
  {
    out.pop ();
  }

  @Nonnull
  public HCImg appendImage (@Nonnull final HCStack out)
  {
    final HCImg ret = new HCImg ();
    out.append (ret);
    return ret;
  }
}
