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
package com.helger.photon.basic.app;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.helger.commons.annotation.UsedViaReflection;
import com.helger.commons.string.StringHelper;
import com.helger.commons.string.ToStringGenerator;
import com.helger.web.scope.singleton.AbstractSessionWebSingleton;

/**
 * This class maintains ph-oton specific settings within a session.
 *
 * @author Philip Helger
 * @since 6.1.0
 */
public final class PhotonSessionState extends AbstractSessionWebSingleton
{
  private String m_sLastApplicationID;

  @Deprecated
  @UsedViaReflection
  public PhotonSessionState ()
  {}

  /**
   * @return The one and only instance for the current session. Never
   *         <code>null</code>.
   */
  @Nonnull
  public static PhotonSessionState getInstance ()
  {
    return getSessionSingleton (PhotonSessionState.class);
  }

  /**
   * @return The last application ID used for the application servlet. May be
   *         <code>null</code>.
   */
  @Nullable
  public String getLastApplicationID ()
  {
    return m_sLastApplicationID;
  }

  /**
   * @return <code>true</code> if the last used application IDis present,
   *         <code>false</code> otherwise.
   */
  public boolean hasLastApplicationID ()
  {
    return StringHelper.hasText (m_sLastApplicationID);
  }

  /**
   * Set the last used application ID of an application servlet.
   *
   * @param sLastApplicationID
   *        The last application ID to be set. May be <code>null</code>.
   */
  public void setLastApplicationID (@Nullable final String sLastApplicationID)
  {
    m_sLastApplicationID = sLastApplicationID;
  }

  @Override
  public String toString ()
  {
    return ToStringGenerator.getDerived (super.toString ())
                            .appendIfNotEmpty ("LastApplicationID", m_sLastApplicationID)
                            .toString ();
  }
}
