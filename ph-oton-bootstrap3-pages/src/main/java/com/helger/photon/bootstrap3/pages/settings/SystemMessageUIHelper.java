/**
 * Copyright (C) 2014-2016 Philip Helger (www.helger.com)
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
package com.helger.photon.bootstrap3.pages.settings;

import java.util.Collection;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.helger.commons.annotation.PresentForCodeCoverage;
import com.helger.commons.collection.ArrayHelper;
import com.helger.commons.collection.CollectionHelper;
import com.helger.commons.string.StringHelper;
import com.helger.html.hc.IHCNode;
import com.helger.html.hc.ext.HCExtHelper;
import com.helger.html.hc.html.IHCElementWithChildren;
import com.helger.photon.basic.app.systemmsg.ESystemMessageType;
import com.helger.photon.basic.app.systemmsg.SystemMessageManager;
import com.helger.photon.basic.mgr.PhotonBasicManager;
import com.helger.photon.bootstrap3.pages.BootstrapUI;

public final class SystemMessageUIHelper
{
  @PresentForCodeCoverage
  private static final SystemMessageUIHelper s_aInstance = new SystemMessageUIHelper ();

  private SystemMessageUIHelper ()
  {}

  @Nonnull
  public static IHCElementWithChildren <?> createDefaultBox ()
  {
    final SystemMessageManager aSystemMsgMgr = PhotonBasicManager.getSystemMessageMgr ();
    return createBox (aSystemMsgMgr.getMessageType (), HCExtHelper.nl2divList (aSystemMsgMgr.getSystemMessage ()));
  }

  @Nullable
  public static IHCElementWithChildren <?> createBox (@Nonnull final ESystemMessageType eSystemMessageType,
                                                      @Nullable final String sMessage)
  {
    if (StringHelper.hasNoText (sMessage))
      return null;

    return BootstrapUI.createEmptySystemMessageBox (eSystemMessageType).addChild (sMessage);
  }

  @Nullable
  public static IHCElementWithChildren <?> createBox (@Nonnull final ESystemMessageType eSystemMessageType,
                                                      @Nullable final IHCNode aMessage)
  {
    if (aMessage == null)
      return null;

    return BootstrapUI.createEmptySystemMessageBox (eSystemMessageType).addChild (aMessage);
  }

  @Nullable
  public static IHCElementWithChildren <?> createBox (@Nonnull final ESystemMessageType eSystemMessageType,
                                                      @Nullable final Collection <? extends IHCNode> aMessage)
  {
    if (CollectionHelper.isEmpty (aMessage))
      return null;

    return BootstrapUI.createEmptySystemMessageBox (eSystemMessageType).addChildren (aMessage);
  }

  @Nullable
  public static IHCElementWithChildren <?> createBox (@Nonnull final ESystemMessageType eSystemMessageType,
                                                      @Nullable final IHCNode... aMessage)
  {
    if (ArrayHelper.isEmpty (aMessage))
      return null;

    return BootstrapUI.createEmptySystemMessageBox (eSystemMessageType).addChildren (aMessage);
  }
}
