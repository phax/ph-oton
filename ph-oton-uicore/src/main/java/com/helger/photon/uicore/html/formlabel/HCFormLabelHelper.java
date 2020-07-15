/**
 * Copyright (C) 2014-2020 Philip Helger (www.helger.com)
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
package com.helger.photon.uicore.html.formlabel;

import javax.annotation.Nonnull;
import javax.annotation.concurrent.Immutable;

import com.helger.commons.ValueEnforcer;
import com.helger.commons.string.StringHelper;
import com.helger.html.hc.HCHelper;
import com.helger.html.hc.IHCNodeWithChildren;

@Immutable
public final class HCFormLabelHelper
{
  public static final String SIGN_MANDATORY = "*";
  public static final String SIGN_ALTERNATIVE = "°";
  public static final String LABEL_END = ":";

  private HCFormLabelHelper ()
  {}

  @Nonnull
  public static String getSuffixString (@Nonnull final ELabelType eType)
  {
    if (eType.equals (ELabelType.MANDATORY))
      return SIGN_MANDATORY;
    if (eType.equals (ELabelType.ALTERNATIVE))
      return SIGN_ALTERNATIVE;
    return "";
  }

  @Nonnull
  public static String getSuffix (@Nonnull final ELabelType eType, final boolean bAppendColon)
  {
    final String sSuffix = getSuffixString (eType);
    return bAppendColon ? sSuffix + LABEL_END : sSuffix;
  }

  @Nonnull
  public static String getTextWithState (@Nonnull final String sText, @Nonnull final ELabelType eType)
  {
    ValueEnforcer.notNull (sText, "Text");
    ValueEnforcer.notNull (eType, "Type");

    // Trim optional trailing colon
    String sPlainText = StringHelper.trimEnd (sText.trim (), LABEL_END);
    // Append suffix only, if at least some text is present
    if (StringHelper.hasText (sPlainText))
      sPlainText += getSuffix (eType, !StringHelper.endsWith (sPlainText, '?'));
    return sPlainText;
  }

  @Nonnull
  public static <T extends IHCNodeWithChildren <?>> T getNodeWithState (@Nonnull final T aNode, @Nonnull final ELabelType eType)
  {
    ValueEnforcer.notNull (aNode, "Node");
    ValueEnforcer.notNull (eType, "Type");

    // Only append the suffix, if at least one text child is present
    if (HCHelper.recursiveContainsAtLeastOneTextNode (aNode))
    {
      final String sPlainText = aNode.getPlainText ();
      if (sPlainText.length () > 0)
      {
        final String sSuffixString = getSuffixString (eType);
        if (StringHelper.hasText (sSuffixString) && StringHelper.endsWith (sPlainText, sSuffixString))
        {
          // Append only colon
          aNode.addChild (LABEL_END);
        }
        else
          if (!StringHelper.endsWith (sPlainText, LABEL_END))
            aNode.addChild (getSuffix (eType, true));
      }
    }
    return aNode;
  }
}
