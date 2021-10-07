/*
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
package com.helger.photon.uicore.html.google;

import javax.annotation.Nonnull;

import com.helger.commons.annotation.UsedViaReflection;
import com.helger.web.scope.singleton.AbstractSessionWebSingleton;

/**
 * A generic session singleton maintaining the state of the captcha.
 *
 * @author Philip Helger
 * @since 8.2.2
 */
public final class CaptchaStateSessionSingleton extends AbstractSessionWebSingleton
{
  private boolean m_bChecked = false;

  @Deprecated
  @UsedViaReflection
  public CaptchaStateSessionSingleton ()
  {}

  @Nonnull
  public static CaptchaStateSessionSingleton getInstance ()
  {
    return getSessionSingleton (CaptchaStateSessionSingleton.class);
  }

  public boolean isChecked ()
  {
    return m_bChecked;
  }

  public void setChecked ()
  {
    m_bChecked = true;
  }
}
