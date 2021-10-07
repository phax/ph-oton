/*
 * Copyright (C) 2018-2021 Philip Helger (www.helger.com)
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
package com.helger.photon.bootstrap4.supplementary.tools;

import java.util.Map;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.helger.commons.collection.impl.CommonsArrayList;
import com.helger.commons.collection.impl.CommonsLinkedHashMap;
import com.helger.commons.collection.impl.ICommonsList;
import com.helger.commons.collection.impl.ICommonsMap;
import com.helger.commons.lang.ClassHelper;
import com.helger.commons.string.StringHelper;
import com.helger.photon.bootstrap4.alert.BootstrapDangerBox;
import com.helger.photon.bootstrap4.alert.BootstrapErrorBox;
import com.helger.photon.bootstrap4.alert.BootstrapInfoBox;
import com.helger.photon.bootstrap4.alert.BootstrapQuestionBox;
import com.helger.photon.bootstrap4.alert.BootstrapSuccessBox;
import com.helger.photon.bootstrap4.alert.BootstrapWarnBox;
import com.helger.photon.bootstrap4.badge.BootstrapBadge;
import com.helger.photon.bootstrap4.traits.IHCBootstrap4Trait;

/**
 * Code creator for {@link IHCBootstrap4Trait}.
 *
 * @author Philip Helger
 */
public class MainCreateHCBootstrap4TraitsCode
{
  private static final Logger LOGGER = LoggerFactory.getLogger (MainCreateHCBootstrap4TraitsCode.class);

  private static class Details
  {
    private final String m_sType;
    private final ICommonsList <String> m_aParams;

    Details (@Nonnull final Class <?> aClass, @Nullable final String... aParams)
    {
      m_sType = ClassHelper.getClassLocalName (aClass);
      m_aParams = new CommonsArrayList <> (aParams);
    }
  }

  public static void main (final String [] args)
  {
    final ICommonsMap <String, Details> aMap = new CommonsLinkedHashMap <> ();
    aMap.put ("badge", new Details (BootstrapBadge.class));
    aMap.put ("badgeDanger", new Details (BootstrapBadge.class, "EBootstrapBadgeType.DANGER"));
    aMap.put ("badgeInfo", new Details (BootstrapBadge.class, "EBootstrapBadgeType.INFO"));
    aMap.put ("badgePrimary", new Details (BootstrapBadge.class, "EBootstrapBadgeType.PRIMARY"));
    aMap.put ("badgeSuccess", new Details (BootstrapBadge.class, "EBootstrapBadgeType.SUCCESS"));
    aMap.put ("badgeWarn", new Details (BootstrapBadge.class, "EBootstrapBadgeType.WARNING"));
    aMap.put ("danger", new Details (BootstrapDangerBox.class));
    aMap.put ("error", new Details (BootstrapErrorBox.class));
    aMap.put ("info", new Details (BootstrapInfoBox.class));
    aMap.put ("question", new Details (BootstrapQuestionBox.class));
    aMap.put ("success", new Details (BootstrapSuccessBox.class));
    aMap.put ("warn", new Details (BootstrapWarnBox.class));

    final StringBuilder aSB = new StringBuilder ();
    for (final Map.Entry <String, Details> e : aMap.entrySet ())
    {
      final Details aDetails = e.getValue ();
      final String sType = aDetails.m_sType;
      final String sMethod = e.getKey ();
      final String sParams = StringHelper.getImploded (", ", aDetails.m_aParams);
      final boolean bAddNumeric = sMethod.startsWith ("badge");

      aSB.append ("@Nonnull default " + sType + " " + sMethod + " (){return new " + sType + " (" + sParams + ");}\n");
      if (bAddNumeric)
      {
        aSB.append ("@Nonnull default " +
                    sType +
                    " " +
                    sMethod +
                    " (final int nValue){return new " +
                    sType +
                    "(" +
                    sParams +
                    ").addChild (Integer.toString(nValue));}\n");
        aSB.append ("@Nonnull default " +
                    sType +
                    " " +
                    sMethod +
                    " (final long nValue){return new " +
                    sType +
                    "(" +
                    sParams +
                    ").addChild (Long.toString(nValue));}\n");
      }
      aSB.append ("@Nonnull default " +
                  sType +
                  " " +
                  sMethod +
                  " (@Nullable final IHCNode aNode){return new " +
                  sType +
                  "(" +
                  sParams +
                  ").addChild (aNode);}\n");
      aSB.append ("@Nonnull default " +
                  sType +
                  " " +
                  sMethod +
                  " (@Nullable final String s){return new " +
                  sType +
                  "(" +
                  sParams +
                  ").addChild (s);}\n");
      aSB.append ("@Nonnull default " +
                  sType +
                  " " +
                  sMethod +
                  " (@Nullable final Iterable <? extends IHCNode> aNodes){return new " +
                  sType +
                  " (" +
                  sParams +
                  ").addChildren (aNodes);}\n");
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
