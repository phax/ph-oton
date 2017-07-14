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
package com.helger.photon.core.servlet;

import javax.annotation.OverridingMethodsMustInvokeSuper;
import javax.servlet.ServletException;

import com.helger.photon.core.servletstatus.ServletStatusManager;
import com.helger.servlet.http.AbstractHttpServlet;
import com.helger.servlet.response.UnifiedResponse;

/**
 * Abstract base class for a servlets delivering responses via
 * {@link UnifiedResponse}.
 *
 * @author Philip Helger
 */
public abstract class AbstractUnifiedResponseServlet2 extends AbstractHttpServlet
{
  private final ServletStatusManager m_aStatusMgr;

  protected AbstractUnifiedResponseServlet2 ()
  {
    m_aStatusMgr = ServletStatusManager.getInstance ();
    m_aStatusMgr.onServletCtor (getClass ());
  }

  @Override
  @OverridingMethodsMustInvokeSuper
  public void init () throws ServletException
  {
    super.init ();
    m_aStatusMgr.onServletInit (getClass ());
  }

  @Override
  @OverridingMethodsMustInvokeSuper
  public void destroy ()
  {
    m_aStatusMgr.onServletDestroy (getClass ());
    super.destroy ();
  }
}
