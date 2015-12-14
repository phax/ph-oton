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
package com.helger.photon.core.app.init;

import javax.annotation.Nonnull;

import com.helger.photon.basic.app.locale.ILocaleManager;
import com.helger.photon.basic.app.menu.IMenuTree;
import com.helger.photon.core.ajax.IAjaxInvoker;
import com.helger.photon.core.app.context.ILayoutExecutionContext;
import com.helger.photon.core.app.layout.ILayoutManager;

/**
 * Default implementation class of {@link IApplicationInitializer} doing
 * nothing. Use this as the base class for your implementation.
 *
 * @author Philip Helger
 * @param <LECTYPE>
 *        Layout execution context type
 */
public class DefaultApplicationInitializer <LECTYPE extends ILayoutExecutionContext>
                                           implements IApplicationInitializer <LECTYPE>
{
  public void initApplicationSettings ()
  {}

  public void initLocales (@Nonnull final ILocaleManager aLocaleMgr)
  {}

  public void initLayout (@Nonnull final ILayoutManager <LECTYPE> aLayoutMgr)
  {}

  public void initMenu (@Nonnull final IMenuTree aMenuTree)
  {}

  public void initAjax (@Nonnull final IAjaxInvoker aAjaxInvoker)
  {}

  public void initRest ()
  {}
}
