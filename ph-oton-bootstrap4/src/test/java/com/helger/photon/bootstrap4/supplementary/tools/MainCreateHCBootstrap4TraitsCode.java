/**
 * Copyright (C) 2018-2020 Philip Helger (www.helger.com)
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

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.helger.commons.collection.impl.CommonsLinkedHashMap;
import com.helger.commons.collection.impl.ICommonsMap;
import com.helger.commons.lang.ClassHelper;
import com.helger.photon.bootstrap4.alert.BootstrapDangerBox;
import com.helger.photon.bootstrap4.alert.BootstrapErrorBox;
import com.helger.photon.bootstrap4.alert.BootstrapInfoBox;
import com.helger.photon.bootstrap4.alert.BootstrapQuestionBox;
import com.helger.photon.bootstrap4.alert.BootstrapSuccessBox;
import com.helger.photon.bootstrap4.alert.BootstrapWarnBox;
import com.helger.photon.bootstrap4.badge.BootstrapBadge;

public class MainCreateHCBootstrap4TraitsCode
{
  private static final Logger LOGGER = LoggerFactory.getLogger (MainCreateHCBootstrap4TraitsCode.class);

  public static void main (final String [] args)
  {
    final ICommonsMap <String, Class <?>> aMap = new CommonsLinkedHashMap <> ();
    aMap.put ("badge", BootstrapBadge.class);
    aMap.put ("danger", BootstrapDangerBox.class);
    aMap.put ("error", BootstrapErrorBox.class);
    aMap.put ("info", BootstrapInfoBox.class);
    aMap.put ("question", BootstrapQuestionBox.class);
    aMap.put ("success", BootstrapSuccessBox.class);
    aMap.put ("warn", BootstrapWarnBox.class);

    final StringBuilder aSB = new StringBuilder ();
    for (final Map.Entry <String, Class <?>> e : aMap.entrySet ())
    {
      final String sType = ClassHelper.getClassLocalName (e.getValue ());
      final String sMethod = e.getKey ();
      final boolean bAddNumeric = "badge".equals (sMethod);

      aSB.append ("@Nonnull default " + sType + " " + sMethod + " (){return new " + sType + " ();}\n");
      if (bAddNumeric)
      {
        aSB.append ("@Nonnull default " +
                    sType +
                    " " +
                    sMethod +
                    " (final int nValue){return new " +
                    sType +
                    "().addChild (Integer.toString(nValue));}\n");
        aSB.append ("@Nonnull default " +
                    sType +
                    " " +
                    sMethod +
                    " (final long nValue){return new " +
                    sType +
                    "().addChild (Long.toString(nValue));}\n");
      }
      aSB.append ("@Nonnull default " +
                  sType +
                  " " +
                  sMethod +
                  " (@Nullable final IHCNode aNode){return new " +
                  sType +
                  "().addChild (aNode);}\n");
      aSB.append ("@Nonnull default " +
                  sType +
                  " " +
                  sMethod +
                  " (@Nullable final String s){return new " +
                  sType +
                  "().addChild (s);}\n");
      aSB.append ("@Nonnull default " +
                  sType +
                  " " +
                  sMethod +
                  " (@Nullable final Iterable <? extends IHCNode> aNodes){return new " +
                  sType +
                  " ().addChildren (aNodes);}\n");
    }
    LOGGER.info ("\n" + aSB.toString ());
  }
}
