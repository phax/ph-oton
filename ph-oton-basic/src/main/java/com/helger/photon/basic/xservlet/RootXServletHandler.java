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
package com.helger.photon.basic.xservlet;

import javax.annotation.Nonnull;

import com.helger.commons.annotation.Nonempty;
import com.helger.xservlet.handler.simple.IXServletSimpleHandler;
import com.helger.xservlet.handler.specific.RedirectToServletXServletHandler;

/**
 * An {@link IXServletSimpleHandler} that does the necessary redirect for a ROOT
 * servlet. This handler should be registered for all action HTTP methods (GET,
 * POST, PUT, DELETE and PATCH).
 *
 * @author Philip Helger
 */
public class RootXServletHandler extends RedirectToServletXServletHandler
{
  public RootXServletHandler (@Nonnull @Nonempty final String sServletPath)
  {
    super (sServletPath);
  }
}
