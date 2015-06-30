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
package com.helger.photon.core.action.executor;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import org.joda.time.DateTime;

import com.helger.commons.annotation.OverrideOnDemand;
import com.helger.commons.string.ToStringGenerator;
import com.helger.photon.core.action.IActionExecutor;
import com.helger.web.scopes.domain.IRequestWebScopeWithoutResponse;

/**
 * Abstract base class in case there will be some common functionality some
 * time.
 *
 * @author Philip Helger
 */
public abstract class AbstractActionExecutor implements IActionExecutor
{
  @OverrideOnDemand
  public void initExecution (@Nonnull final IRequestWebScopeWithoutResponse aRequestScope)
  {
    // By default do nothing
  }

  @Nullable
  @OverrideOnDemand
  public DateTime getLastModificationDateTime ()
  {
    return null;
  }

  @Override
  public String toString ()
  {
    return new ToStringGenerator (this).toString ();
  }
}
