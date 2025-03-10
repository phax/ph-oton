/*
 * Copyright (C) 2014-2025 Philip Helger (www.helger.com)
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
package com.helger.photon.uicore.page;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.helger.commons.equals.EqualsHelper;
import com.helger.html.hc.impl.HCNodeList;
import com.helger.photon.core.execcontext.ILayoutExecutionContext;
import com.helger.photon.uicore.css.CPageParam;

public interface IWebPageExecutionContext extends ILayoutExecutionContext
{
  /**
   * @return The invoked web page. Never <code>null</code>.
   */
  @Nonnull
  IWebPage <? extends IWebPageExecutionContext> getWebPage ();

  /**
   * @return The node list to be filled with content. Never <code>null</code>.
   */
  @Nonnull
  HCNodeList getNodeList ();

  /**
   * @return The special request parameter value of
   *         {@link CPageParam#PARAM_ACTION}. May be <code>null</code>.
   */
  @Nullable
  default String getAction ()
  {
    return params ().getAsString (CPageParam.PARAM_ACTION);
  }

  /**
   * Check if the specified action is present in the request scope.
   *
   * @param sAction
   *        Action to check.
   * @return <code>true</code> if <code>getAction().equals (sAction)</code>
   */
  default boolean hasAction (@Nullable final String sAction)
  {
    return EqualsHelper.equals (getAction (), sAction);
  }

  /**
   * @return The special request parameter value of
   *         {@link CPageParam#PARAM_SUBACTION}. May be <code>null</code>.
   */
  @Nullable
  default String getSubAction ()
  {
    return params ().getAsString (CPageParam.PARAM_SUBACTION);
  }

  /**
   * Check if the specified sub action is present in the request scope.
   *
   * @param sSubAction
   *        Sub action to check.
   * @return <code>true</code> if
   *         <code>getSubAction().equals (sSubAction)</code>
   */
  default boolean hasSubAction (@Nullable final String sSubAction)
  {
    return EqualsHelper.equals (getSubAction (), sSubAction);
  }

}
