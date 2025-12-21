/*
 * Copyright (C) 2014-2025 Philip Helger (www.helger.com)
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
package com.helger.html.hc.html.traits;

import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.Nullable;

import com.helger.html.hc.IHCNode;
import com.helger.html.hc.html.grouping.HCBlockQuote;
import com.helger.html.hc.html.grouping.HCDiv;
import com.helger.html.hc.html.grouping.HCP;
import com.helger.html.hc.html.grouping.HCPre;
import com.helger.html.hc.html.sections.HCH1;
import com.helger.html.hc.html.sections.HCH2;
import com.helger.html.hc.html.sections.HCH3;
import com.helger.html.hc.html.sections.HCH4;
import com.helger.html.hc.html.sections.HCH5;
import com.helger.html.hc.html.sections.HCH6;
import com.helger.html.hc.html.textlevel.HCA;
import com.helger.html.hc.html.textlevel.HCCode;
import com.helger.html.hc.html.textlevel.HCEM;
import com.helger.html.hc.html.textlevel.HCSmall;
import com.helger.html.hc.html.textlevel.HCSpan;
import com.helger.html.hc.html.textlevel.HCStrong;
import com.helger.html.hc.html.textlevel.HCSub;
import com.helger.html.hc.html.textlevel.HCSup;
import com.helger.html.hc.impl.HCTextNode;
import com.helger.url.ISimpleURL;

/**
 * Trait interface to add simpler UI codes.
 *
 * @author Philip Helger via MainCreateHCTraitsCode
 * @since 8.2.2
 */
public interface IHCTrait
{
  @NonNull
  default HCA a ()
  {
    return new HCA ();
  }

  @NonNull
  default HCA a (@Nullable final ISimpleURL aHref)
  {
    return new HCA (aHref);
  }

  @NonNull
  default HCA a (@Nullable final IHCNode aNode)
  {
    return new HCA ().addChild (aNode);
  }

  @NonNull
  default HCA a (@Nullable final String s)
  {
    return new HCA ().addChild (s);
  }

  @NonNull
  default HCA a (@Nullable final Iterable <? extends IHCNode> aNodes)
  {
    return new HCA ().addChildren (aNodes);
  }

  @NonNull
  default HCA a (@Nullable final String... aTexts)
  {
    return new HCA ().addChildren (aTexts);
  }

  @NonNull
  default HCBlockQuote blockquote ()
  {
    return new HCBlockQuote ();
  }

  @NonNull
  default HCBlockQuote blockquote (@Nullable final IHCNode aNode)
  {
    return new HCBlockQuote ().addChild (aNode);
  }

  @NonNull
  default HCBlockQuote blockquote (@Nullable final String s)
  {
    return new HCBlockQuote ().addChild (s);
  }

  @NonNull
  default HCBlockQuote blockquote (@Nullable final Iterable <? extends IHCNode> aNodes)
  {
    return new HCBlockQuote ().addChildren (aNodes);
  }

  @NonNull
  default HCBlockQuote blockquote (@Nullable final String... aTexts)
  {
    return new HCBlockQuote ().addChildren (aTexts);
  }

  @NonNull
  default HCCode code ()
  {
    return new HCCode ();
  }

  @NonNull
  default HCCode code (@Nullable final IHCNode aNode)
  {
    return new HCCode ().addChild (aNode);
  }

  @NonNull
  default HCCode code (@Nullable final String s)
  {
    return new HCCode ().addChild (s);
  }

  @NonNull
  default HCCode code (@Nullable final Iterable <? extends IHCNode> aNodes)
  {
    return new HCCode ().addChildren (aNodes);
  }

  @NonNull
  default HCCode code (@Nullable final String... aTexts)
  {
    return new HCCode ().addChildren (aTexts);
  }

  @NonNull
  default HCDiv div ()
  {
    return new HCDiv ();
  }

  @NonNull
  default HCDiv div (@Nullable final IHCNode aNode)
  {
    return new HCDiv ().addChild (aNode);
  }

  @NonNull
  default HCDiv div (@Nullable final String s)
  {
    return new HCDiv ().addChild (s);
  }

  @NonNull
  default HCDiv div (@Nullable final Iterable <? extends IHCNode> aNodes)
  {
    return new HCDiv ().addChildren (aNodes);
  }

  @NonNull
  default HCDiv div (@Nullable final String... aTexts)
  {
    return new HCDiv ().addChildren (aTexts);
  }

  @NonNull
  default HCEM em ()
  {
    return new HCEM ();
  }

  @NonNull
  default HCEM em (@Nullable final IHCNode aNode)
  {
    return new HCEM ().addChild (aNode);
  }

  @NonNull
  default HCEM em (@Nullable final String s)
  {
    return new HCEM ().addChild (s);
  }

  @NonNull
  default HCEM em (@Nullable final Iterable <? extends IHCNode> aNodes)
  {
    return new HCEM ().addChildren (aNodes);
  }

  @NonNull
  default HCEM em (@Nullable final String... aTexts)
  {
    return new HCEM ().addChildren (aTexts);
  }

  @NonNull
  default HCH1 h1 ()
  {
    return new HCH1 ();
  }

  @NonNull
  default HCH1 h1 (@Nullable final IHCNode aNode)
  {
    return new HCH1 ().addChild (aNode);
  }

  @NonNull
  default HCH1 h1 (@Nullable final String s)
  {
    return new HCH1 ().addChild (s);
  }

  @NonNull
  default HCH1 h1 (@Nullable final Iterable <? extends IHCNode> aNodes)
  {
    return new HCH1 ().addChildren (aNodes);
  }

  @NonNull
  default HCH1 h1 (@Nullable final String... aTexts)
  {
    return new HCH1 ().addChildren (aTexts);
  }

  @NonNull
  default HCH2 h2 ()
  {
    return new HCH2 ();
  }

  @NonNull
  default HCH2 h2 (@Nullable final IHCNode aNode)
  {
    return new HCH2 ().addChild (aNode);
  }

  @NonNull
  default HCH2 h2 (@Nullable final String s)
  {
    return new HCH2 ().addChild (s);
  }

  @NonNull
  default HCH2 h2 (@Nullable final Iterable <? extends IHCNode> aNodes)
  {
    return new HCH2 ().addChildren (aNodes);
  }

  @NonNull
  default HCH2 h2 (@Nullable final String... aTexts)
  {
    return new HCH2 ().addChildren (aTexts);
  }

  @NonNull
  default HCH3 h3 ()
  {
    return new HCH3 ();
  }

  @NonNull
  default HCH3 h3 (@Nullable final IHCNode aNode)
  {
    return new HCH3 ().addChild (aNode);
  }

  @NonNull
  default HCH3 h3 (@Nullable final String s)
  {
    return new HCH3 ().addChild (s);
  }

  @NonNull
  default HCH3 h3 (@Nullable final Iterable <? extends IHCNode> aNodes)
  {
    return new HCH3 ().addChildren (aNodes);
  }

  @NonNull
  default HCH3 h3 (@Nullable final String... aTexts)
  {
    return new HCH3 ().addChildren (aTexts);
  }

  @NonNull
  default HCH4 h4 ()
  {
    return new HCH4 ();
  }

  @NonNull
  default HCH4 h4 (@Nullable final IHCNode aNode)
  {
    return new HCH4 ().addChild (aNode);
  }

  @NonNull
  default HCH4 h4 (@Nullable final String s)
  {
    return new HCH4 ().addChild (s);
  }

  @NonNull
  default HCH4 h4 (@Nullable final Iterable <? extends IHCNode> aNodes)
  {
    return new HCH4 ().addChildren (aNodes);
  }

  @NonNull
  default HCH4 h4 (@Nullable final String... aTexts)
  {
    return new HCH4 ().addChildren (aTexts);
  }

  @NonNull
  default HCH5 h5 ()
  {
    return new HCH5 ();
  }

  @NonNull
  default HCH5 h5 (@Nullable final IHCNode aNode)
  {
    return new HCH5 ().addChild (aNode);
  }

  @NonNull
  default HCH5 h5 (@Nullable final String s)
  {
    return new HCH5 ().addChild (s);
  }

  @NonNull
  default HCH5 h5 (@Nullable final Iterable <? extends IHCNode> aNodes)
  {
    return new HCH5 ().addChildren (aNodes);
  }

  @NonNull
  default HCH5 h5 (@Nullable final String... aTexts)
  {
    return new HCH5 ().addChildren (aTexts);
  }

  @NonNull
  default HCH6 h6 ()
  {
    return new HCH6 ();
  }

  @NonNull
  default HCH6 h6 (@Nullable final IHCNode aNode)
  {
    return new HCH6 ().addChild (aNode);
  }

  @NonNull
  default HCH6 h6 (@Nullable final String s)
  {
    return new HCH6 ().addChild (s);
  }

  @NonNull
  default HCH6 h6 (@Nullable final Iterable <? extends IHCNode> aNodes)
  {
    return new HCH6 ().addChildren (aNodes);
  }

  @NonNull
  default HCH6 h6 (@Nullable final String... aTexts)
  {
    return new HCH6 ().addChildren (aTexts);
  }

  @NonNull
  default HCP p ()
  {
    return new HCP ();
  }

  @NonNull
  default HCP p (@Nullable final IHCNode aNode)
  {
    return new HCP ().addChild (aNode);
  }

  @NonNull
  default HCP p (@Nullable final String s)
  {
    return new HCP ().addChild (s);
  }

  @NonNull
  default HCP p (@Nullable final Iterable <? extends IHCNode> aNodes)
  {
    return new HCP ().addChildren (aNodes);
  }

  @NonNull
  default HCP p (@Nullable final String... aTexts)
  {
    return new HCP ().addChildren (aTexts);
  }

  @NonNull
  default HCPre pre ()
  {
    return new HCPre ();
  }

  @NonNull
  default HCPre pre (@Nullable final IHCNode aNode)
  {
    return new HCPre ().addChild (aNode);
  }

  @NonNull
  default HCPre pre (@Nullable final String s)
  {
    return new HCPre ().addChild (s);
  }

  @NonNull
  default HCPre pre (@Nullable final Iterable <? extends IHCNode> aNodes)
  {
    return new HCPre ().addChildren (aNodes);
  }

  @NonNull
  default HCPre pre (@Nullable final String... aTexts)
  {
    return new HCPre ().addChildren (aTexts);
  }

  @NonNull
  default HCSmall small ()
  {
    return new HCSmall ();
  }

  @NonNull
  default HCSmall small (@Nullable final IHCNode aNode)
  {
    return new HCSmall ().addChild (aNode);
  }

  @NonNull
  default HCSmall small (@Nullable final String s)
  {
    return new HCSmall ().addChild (s);
  }

  @NonNull
  default HCSmall small (@Nullable final Iterable <? extends IHCNode> aNodes)
  {
    return new HCSmall ().addChildren (aNodes);
  }

  @NonNull
  default HCSmall small (@Nullable final String... aTexts)
  {
    return new HCSmall ().addChildren (aTexts);
  }

  @NonNull
  default HCSpan span ()
  {
    return new HCSpan ();
  }

  @NonNull
  default HCSpan span (@Nullable final IHCNode aNode)
  {
    return new HCSpan ().addChild (aNode);
  }

  @NonNull
  default HCSpan span (@Nullable final String s)
  {
    return new HCSpan ().addChild (s);
  }

  @NonNull
  default HCSpan span (@Nullable final Iterable <? extends IHCNode> aNodes)
  {
    return new HCSpan ().addChildren (aNodes);
  }

  @NonNull
  default HCSpan span (@Nullable final String... aTexts)
  {
    return new HCSpan ().addChildren (aTexts);
  }

  @NonNull
  default HCStrong strong ()
  {
    return new HCStrong ();
  }

  @NonNull
  default HCStrong strong (@Nullable final IHCNode aNode)
  {
    return new HCStrong ().addChild (aNode);
  }

  @NonNull
  default HCStrong strong (@Nullable final String s)
  {
    return new HCStrong ().addChild (s);
  }

  @NonNull
  default HCStrong strong (@Nullable final Iterable <? extends IHCNode> aNodes)
  {
    return new HCStrong ().addChildren (aNodes);
  }

  @NonNull
  default HCStrong strong (@Nullable final String... aTexts)
  {
    return new HCStrong ().addChildren (aTexts);
  }

  @NonNull
  default HCSub sub ()
  {
    return new HCSub ();
  }

  @NonNull
  default HCSub sub (@Nullable final IHCNode aNode)
  {
    return new HCSub ().addChild (aNode);
  }

  @NonNull
  default HCSub sub (@Nullable final String s)
  {
    return new HCSub ().addChild (s);
  }

  @NonNull
  default HCSub sub (@Nullable final Iterable <? extends IHCNode> aNodes)
  {
    return new HCSub ().addChildren (aNodes);
  }

  @NonNull
  default HCSub sub (@Nullable final String... aTexts)
  {
    return new HCSub ().addChildren (aTexts);
  }

  @NonNull
  default HCSup sup ()
  {
    return new HCSup ();
  }

  @NonNull
  default HCSup sup (@Nullable final IHCNode aNode)
  {
    return new HCSup ().addChild (aNode);
  }

  @NonNull
  default HCSup sup (@Nullable final String s)
  {
    return new HCSup ().addChild (s);
  }

  @NonNull
  default HCSup sup (@Nullable final Iterable <? extends IHCNode> aNodes)
  {
    return new HCSup ().addChildren (aNodes);
  }

  @NonNull
  default HCSup sup (@Nullable final String... aTexts)
  {
    return new HCSup ().addChildren (aTexts);
  }

  @NonNull
  default HCTextNode text (@Nullable final String s)
  {
    return HCTextNode.createOnDemand (s);
  }

  @NonNull
  default HCTextNode text (final char @Nullable [] a)
  {
    return HCTextNode.createOnDemand (a);
  }

  @NonNull
  default HCTextNode text (final char n)
  {
    return new HCTextNode (n);
  }

  @NonNull
  default HCTextNode text (final int n)
  {
    return new HCTextNode (n);
  }

  @NonNull
  default HCTextNode text (final long n)
  {
    return new HCTextNode (n);
  }
}
