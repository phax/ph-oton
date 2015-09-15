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
package com.helger.photon.core.ajax.response;

import com.helger.commons.string.ToStringGenerator;

/**
 * Base class for AJAX response.
 *
 * @author Philip Helger
 */
public abstract class AbstractAjaxResponse implements IAjaxResponse
{
  private final boolean m_bSuccess;

  protected AbstractAjaxResponse (final boolean bSuccess)
  {
    m_bSuccess = bSuccess;
  }

  public final boolean isSuccess ()
  {
    return m_bSuccess;
  }

  public final boolean isFailure ()
  {
    return !m_bSuccess;
  }

  @Override
  public String toString ()
  {
    return new ToStringGenerator (this).append ("success", m_bSuccess).toString ();
  }
}
