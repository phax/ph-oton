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
package com.helger.webctrls.page.settings;

import java.util.Collection;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.helger.appbasics.app.systemmsg.ESystemMessageType;
import com.helger.appbasics.app.systemmsg.SystemMessageManager;
import com.helger.commons.ValueEnforcer;
import com.helger.commons.annotations.PresentForCodeCoverage;
import com.helger.commons.collections.ArrayHelper;
import com.helger.commons.collections.CollectionHelper;
import com.helger.commons.string.StringHelper;
import com.helger.html.hc.IHCElementWithChildren;
import com.helger.html.hc.IHCNode;
import com.helger.html.hc.htmlext.HCUtils;
import com.helger.webbasics.app.page.IWebPageExecutionContext;
import com.helger.webbasics.mgr.MetaSystemManager;
import com.helger.webctrls.styler.IWebPageStyler;
import com.helger.webctrls.styler.WebPageStylerManager;

public final class SystemMessageUIHelper
{
  @PresentForCodeCoverage
  private static final SystemMessageUIHelper s_aInstance = new SystemMessageUIHelper ();

  private SystemMessageUIHelper ()
  {}

  @Nonnull
  public static IHCElementWithChildren <?> createEmptyBox (@Nonnull final IWebPageExecutionContext aWPEC,
                                                           @Nonnull final ESystemMessageType eSystemMessageType)
  {
    ValueEnforcer.notNull (aWPEC, "WPEC");
    ValueEnforcer.notNull (eSystemMessageType, "SystemMessageType");

    final IWebPageStyler aStyler = WebPageStylerManager.getStyler ();

    // Create empty boxes
    switch (eSystemMessageType)
    {
      case INFO:
        return aStyler.createInfoBox (aWPEC, (String) null);
      case WARNING:
        return aStyler.createWarnBox (aWPEC, (String) null);
      case ERROR:
        return aStyler.createErrorBox (aWPEC, (String) null);
      case SUCCESS:
        return aStyler.createSuccessBox (aWPEC, (String) null);
      default:
        throw new IllegalArgumentException ("Illegal message type: " + eSystemMessageType);
    }
  }

  @Nonnull
  public static IHCElementWithChildren <?> createDefaultBox (@Nonnull final IWebPageExecutionContext aWPEC)
  {
    final SystemMessageManager aSystemMsgMgr = MetaSystemManager.getSystemMessageMgr ();
    return createBox (aWPEC, aSystemMsgMgr.getMessageType (), HCUtils.nl2divList (aSystemMsgMgr.getSystemMessage ()));
  }

  @Nullable
  public static IHCElementWithChildren <?> createBox (@Nonnull final IWebPageExecutionContext aWPEC,
                                                      @Nonnull final ESystemMessageType eSystemMessageType,
                                                      @Nullable final String sMessage)
  {
    if (StringHelper.hasNoText (sMessage))
      return null;

    return createEmptyBox (aWPEC, eSystemMessageType).addChild (sMessage);
  }

  @Nullable
  public static IHCElementWithChildren <?> createBox (@Nonnull final IWebPageExecutionContext aWPEC,
                                                      @Nonnull final ESystemMessageType eSystemMessageType,
                                                      @Nullable final IHCNode aMessage)
  {
    if (aMessage == null)
      return null;

    return createEmptyBox (aWPEC, eSystemMessageType).addChild (aMessage);
  }

  @Nullable
  public static IHCElementWithChildren <?> createBox (@Nonnull final IWebPageExecutionContext aWPEC,
                                                      @Nonnull final ESystemMessageType eSystemMessageType,
                                                      @Nullable final Collection <? extends IHCNode> aMessage)
  {
    if (CollectionHelper.isEmpty (aMessage))
      return null;

    return createEmptyBox (aWPEC, eSystemMessageType).addChildren (aMessage);
  }

  @Nullable
  public static IHCElementWithChildren <?> createBox (@Nonnull final IWebPageExecutionContext aWPEC,
                                                      @Nonnull final ESystemMessageType eSystemMessageType,
                                                      @Nullable final IHCNode... aMessage)
  {
    if (ArrayHelper.isEmpty (aMessage))
      return null;

    return createEmptyBox (aWPEC, eSystemMessageType).addChildren (aMessage);
  }
}
