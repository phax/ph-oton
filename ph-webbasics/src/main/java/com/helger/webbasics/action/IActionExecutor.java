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
package com.helger.webbasics.action;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import org.joda.time.DateTime;

import com.helger.web.scopes.domain.IRequestWebScopeWithoutResponse;
import com.helger.web.servlet.response.UnifiedResponse;

/**
 * Web action executor interface.
 * 
 * @author Philip Helger
 */
public interface IActionExecutor
{
  /**
   * Initialization method that is called before the main execution is called.
   * This can e.g be used to determine the last modification date time.
   * 
   * @param aRequestScope
   *        The request scope to be used, to extract parameters. Never
   *        <code>null</code>.
   */
  void initExecution (@Nonnull IRequestWebScopeWithoutResponse aRequestScope);

  /**
   * @return The last modification date time for the action result or
   *         <code>null</code> if none is applicable. This date is used to set
   *         the HTTP response last modification date time.
   */
  @Nullable
  DateTime getLastModificationDateTime ();

  /**
   * Execute the action on the passed HTTP request/response.
   * 
   * @param aRequestScope
   *        The request scope. Never <code>null</code>.
   * @param aUnifiedResponse
   *        The response to write to. Never <code>null</code>.
   * @throws Throwable
   *         In case something goes wrong.
   */
  void execute (@Nonnull IRequestWebScopeWithoutResponse aRequestScope, @Nonnull UnifiedResponse aUnifiedResponse) throws Throwable;
}
