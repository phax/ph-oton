/*
 * Copyright (C) 2014-2024 Philip Helger (www.helger.com)
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

import java.util.function.BiFunction;

import javax.annotation.Nonnull;

import com.helger.commons.annotation.Nonempty;
import com.helger.commons.id.IHasID;
import com.helger.html.hc.html.forms.IHCForm;
import com.helger.photon.uicore.css.CPageParam;
import com.helger.photon.uicore.html.toolbar.IButtonToolbar;
import com.helger.photon.uicore.page.IWebPageExecutionContext;
import com.helger.photon.uicore.page.IWebPageFormUIHandler;

public abstract class AbstractWebPageActionHandlerMultiUndelete <DATATYPE extends IHasID <String>, WPECTYPE extends IWebPageExecutionContext, FORM_TYPE extends IHCForm <FORM_TYPE>, TOOLBAR_TYPE extends IButtonToolbar <TOOLBAR_TYPE>>
                                                                extends
                                                                AbstractWebPageActionHandlerMultiWithQuery <DATATYPE, WPECTYPE, FORM_TYPE, TOOLBAR_TYPE>
{
  public static final String FORM_ID_UNDELETE = "undeleteform";

  public AbstractWebPageActionHandlerMultiUndelete (@Nonnull final IWebPageFormUIHandler <FORM_TYPE, TOOLBAR_TYPE> aUIHandler,
                                                    @Nonnull @Nonempty final String sFieldName,
                                                    @Nonnull final BiFunction <WPECTYPE, String, DATATYPE> aResolver)
  {
    super (aUIHandler, sFieldName, aResolver, CPageParam.ACTION_UNDELETE, FORM_ID_UNDELETE);
  }
}
