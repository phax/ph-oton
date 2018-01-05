/**
 * Copyright (C) 2014-2018 Philip Helger (www.helger.com)
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
package com.helger.photon.uicore.page.handler;

import javax.annotation.Nonnull;

import com.helger.commons.ValueEnforcer;
import com.helger.commons.id.IHasID;
import com.helger.html.hc.html.forms.IHCForm;
import com.helger.photon.uicore.html.toolbar.IButtonToolbar;
import com.helger.photon.uicore.page.IWebPageExecutionContext;
import com.helger.photon.uicore.page.IWebPageFormUIHandler;

public abstract class AbstractWebPageActionHandler <DATATYPE extends IHasID <String>, WPECTYPE extends IWebPageExecutionContext, FORM_TYPE extends IHCForm <FORM_TYPE>, TOOLBAR_TYPE extends IButtonToolbar <TOOLBAR_TYPE>>
                                                   implements IWebPageActionHandler <DATATYPE, WPECTYPE>
{
  private final boolean m_bSelectedObjectRequired;
  private final IWebPageFormUIHandler <FORM_TYPE, TOOLBAR_TYPE> m_aUIHandler;

  protected AbstractWebPageActionHandler (final boolean bSelectedObjectRequired,
                                          @Nonnull final IWebPageFormUIHandler <FORM_TYPE, TOOLBAR_TYPE> aUIHandler)
  {
    m_bSelectedObjectRequired = bSelectedObjectRequired;
    m_aUIHandler = ValueEnforcer.notNull (aUIHandler, "UIHandler");
  }

  public final boolean isSelectedObjectRequired ()
  {
    return m_bSelectedObjectRequired;
  }

  @Nonnull
  protected final IWebPageFormUIHandler <FORM_TYPE, TOOLBAR_TYPE> getUIHandler ()
  {
    return m_aUIHandler;
  }
}
