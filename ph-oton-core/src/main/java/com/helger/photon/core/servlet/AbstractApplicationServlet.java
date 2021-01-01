/**
 * Copyright (C) 2014-2021 Philip Helger (www.helger.com)
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
package com.helger.photon.core.servlet;

import java.util.EnumSet;

import javax.annotation.Nonnull;

import com.helger.commons.annotation.Nonempty;
import com.helger.commons.http.EHttpMethod;
import com.helger.photon.core.appid.XServletFilterAppIDExplicit;
import com.helger.xservlet.AbstractXServlet;

/**
 * The servlet to show the secure application
 *
 * @author Philip Helger
 */
public abstract class AbstractApplicationServlet extends AbstractXServlet
{
  protected AbstractApplicationServlet (@Nonnull final AbstractApplicationXServletHandler aHandler, @Nonnull @Nonempty final String sAppID)
  {
    handlerRegistry ().registerHandler (EHttpMethod.GET, aHandler);
    // Must support POST for form submits :)
    handlerRegistry ().copyHandler (EHttpMethod.GET, EnumSet.of (EHttpMethod.POST));
    filterHighLevelList ().add (new XServletFilterAppIDExplicit (sAppID));
  }
}
