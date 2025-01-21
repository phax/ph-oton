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
package com.helger.html.supplementary.tools;

import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.helger.commons.collection.impl.CommonsArrayList;
import com.helger.commons.collection.impl.ICommonsList;
import com.helger.html.EHTMLElement;

public class MainCreateHCTraitsCode
{
  private static final Logger LOGGER = LoggerFactory.getLogger (MainCreateHCTraitsCode.class);

  private static String _ucFirst (final String s)
  {
    if (s.length () == 1)
      return s.toUpperCase (Locale.US);
    return s.substring (0, 1).toUpperCase (Locale.US) + s.substring (1);
  }

  public static void main (final String [] args)
  {
    final ICommonsList <EHTMLElement> es = new CommonsArrayList <> (EHTMLElement.BLOCKQUOTE,
                                                                    EHTMLElement.CODE,
                                                                    EHTMLElement.DIV,
                                                                    EHTMLElement.EM,
                                                                    EHTMLElement.H1,
                                                                    EHTMLElement.H2,
                                                                    EHTMLElement.H3,
                                                                    EHTMLElement.H4,
                                                                    EHTMLElement.H5,
                                                                    EHTMLElement.H6,
                                                                    EHTMLElement.P,
                                                                    EHTMLElement.PRE,
                                                                    EHTMLElement.SMALL,
                                                                    EHTMLElement.SPAN,
                                                                    EHTMLElement.STRONG,
                                                                    EHTMLElement.SUB,
                                                                    EHTMLElement.SUP);
    final StringBuilder aSB = new StringBuilder ();
    for (final EHTMLElement e : es)
    {
      final String sType;
      if (e == EHTMLElement.BLOCKQUOTE)
        sType = "HCBlockQuote";
      else
        if (e == EHTMLElement.EM)
          sType = "HCEM";
        else
          sType = "HC" + _ucFirst (e.getElementName ());
      final String sMethod = e.getElementName ();
      aSB.append ("@Nonnull default " + sType + " " + sMethod + " (){return new " + sType + " ();}\n");
      aSB.append ("@Nonnull default " +
                  sType +
                  " " +
                  sMethod +
                  " (@Nullable final IHCNode aNode){return new " +
                  sType +
                  "().addChild (aNode);}\n");
      aSB.append ("@Nonnull default " + sType + " " + sMethod + " (@Nullable final String s){return new " + sType + "().addChild (s);}\n");
      aSB.append ("@Nonnull default " +
                  sType +
                  " " +
                  sMethod +
                  " (@Nullable final Iterable <? extends IHCNode> aNodes){return new " +
                  sType +
                  " ().addChildren (aNodes);}\n");
      aSB.append ("@Nonnull default " +
                  sType +
                  " " +
                  sMethod +
                  " (@Nullable final String... aTexts){return new " +
                  sType +
                  " ().addChildren (aTexts);}\n");
    }
    LOGGER.info ("\n" + aSB.toString ());
  }
}
