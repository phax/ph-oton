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
package com.helger.photon.core.app.error.uihandler;

import java.util.Locale;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.helger.commons.GlobalDebug;
import com.helger.commons.ValueEnforcer;
import com.helger.commons.lang.StackTraceHelper;
import com.helger.css.ECSSUnit;
import com.helger.css.property.CCSSProperties;
import com.helger.css.propertyvalue.CCSSValue;
import com.helger.html.hc.IHCNodeWithChildren;
import com.helger.html.hc.html.HCDiv;
import com.helger.html.hc.html.HCH1;
import com.helger.html.hc.html.HCTextArea;
import com.helger.html.hc.htmlext.HCUtils;
import com.helger.photon.core.EPhotonCoreText;

/**
 * The default implementation of {@link IUIInternalErrorHandler}
 *
 * @author Philip Helger
 */
public class UIInternalErrorHandler implements IUIInternalErrorHandler
{
  private final IHCNodeWithChildren <?> m_aParentNode;

  public UIInternalErrorHandler (@Nonnull final IHCNodeWithChildren <?> aParentNode)
  {
    m_aParentNode = ValueEnforcer.notNull (aParentNode, "ParentNode");
  }

  @Nonnull
  public IHCNodeWithChildren <?> getParentNode ()
  {
    return m_aParentNode;
  }

  public void onInternalError (@Nullable final Throwable t,
                               @Nonnull final String sErrorID,
                               @Nonnull final Locale aDisplayLocale)
  {
    m_aParentNode.addChild (new HCH1 ().addChild (EPhotonCoreText.INTERNAL_ERROR_TITLE.getDisplayText (aDisplayLocale)));
    m_aParentNode.addChild (new HCDiv ().addChildren (HCUtils.nl2brList (EPhotonCoreText.INTERNAL_ERROR_DESCRIPTION.getDisplayTextWithArgs (aDisplayLocale,
                                                                                                                                           sErrorID))));

    // Show stack trace details
    if (GlobalDebug.isDebugMode () && t != null)
    {
      // Get error stack trace
      final String sStackTrace = StackTraceHelper.getStackAsString (t, false);
      final HCTextArea aStackTrace = new HCTextArea ("callstack").setValue (sStackTrace)
                                                                 .setRows (20)
                                                                 .addStyle (CCSSProperties.WIDTH.newValue (ECSSUnit.perc (98)))
                                                                 .addStyle (CCSSProperties.FONT_SIZE.newValue (ECSSUnit.pt (10)))
                                                                 .addStyle (CCSSProperties.FONT_FAMILY.newValue (CCSSValue.FONT_MONOSPACE));

      m_aParentNode.addChild (aStackTrace);
    }
  }
}
