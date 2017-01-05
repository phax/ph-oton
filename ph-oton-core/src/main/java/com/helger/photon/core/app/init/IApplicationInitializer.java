/**
 * Copyright (C) 2014-2017 Philip Helger (www.helger.com)
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
import com.helger.photon.core.api.IAPIInvoker;
import com.helger.photon.core.app.context.ILayoutExecutionContext;
import com.helger.photon.core.app.layout.ILayoutManager;

/**
 * Base interface for an application-specific initializer. The methods are
 * called in the correct order they are required for dependencies.
 *
 * @author Philip Helger
 * @param <LECTYPE>
 *        Layout execution context type
 */
public interface IApplicationInitializer <LECTYPE extends ILayoutExecutionContext>
{
  /**
   * Init all application specific settings. This method is called before all
   * named methods afterwards.
   */
  default void initApplicationSettings ()
  {}

  /**
   * Init all application locales
   *
   * @param aLocaleMgr
   *        Locale manager to use
   */
  default void initLocales (@Nonnull final ILocaleManager aLocaleMgr)
  {}

  /**
   * Register all layout handler
   *
   * @param aLayoutMgr
   *        The layout manager to use
   */
  default void initLayout (@Nonnull final ILayoutManager <LECTYPE> aLayoutMgr)
  {}

  /**
   * Create all menu items
   *
   * @param aMenuTree
   *        The menu tree to init
   */
  default void initMenu (@Nonnull final IMenuTree aMenuTree)
  {}

  /**
   * Register all ajax functions
   *
   * @param aAjaxInvoker
   *        The ajax invoker to use
   */
  default void initAjax (@Nonnull final IAjaxInvoker aAjaxInvoker)
  {}

  /**
   * Register all API functions
   *
   * @param aAPIInvoker
   *        The API invoker to use
   */
  default void initAPI (@Nonnull final IAPIInvoker aAPIInvoker)
  {}

  /**
   * Init all things for which no special method is present after the predefined
   * methods.
   */
  default void initRest ()
  {}
}
