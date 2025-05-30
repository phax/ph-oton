/*
 * Copyright (C) 2018-2025 Philip Helger (www.helger.com)
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
package com.helger.photon.bootstrap4.pages.handler;

import javax.annotation.Nonnull;

import com.helger.commons.annotation.Nonempty;
import com.helger.commons.id.IHasID;
import com.helger.photon.bootstrap4.buttongroup.BootstrapButtonToolbar;
import com.helger.photon.bootstrap4.form.BootstrapForm;
import com.helger.photon.bootstrap4.pages.BootstrapWebPageUIHandler;
import com.helger.photon.uicore.page.IWebPageExecutionContext;
import com.helger.photon.uicore.page.handler.AbstractWebPageActionHandlerWithQuery;

public abstract class AbstractBootstrapWebPageActionHandlerWithQuery <DATATYPE extends IHasID <String>, WPECTYPE extends IWebPageExecutionContext>
                                                                     extends
                                                                     AbstractWebPageActionHandlerWithQuery <DATATYPE, WPECTYPE, BootstrapForm, BootstrapButtonToolbar>
{
  public AbstractBootstrapWebPageActionHandlerWithQuery (final boolean bSelectedObjectRequired,
                                                         @Nonnull @Nonempty final String sAction,
                                                         @Nonnull @Nonempty final String sFormID)
  {
    super (bSelectedObjectRequired, BootstrapWebPageUIHandler.INSTANCE, sAction, sFormID);
  }
}
