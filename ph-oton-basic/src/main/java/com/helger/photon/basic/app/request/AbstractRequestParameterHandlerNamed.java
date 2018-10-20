/**
 * Copyright (C) 2014-2018 Philip Helger (www.helger.com)
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
package com.helger.photon.basic.app.request;

import javax.annotation.Nonnull;
import javax.annotation.concurrent.NotThreadSafe;

import com.helger.commons.ValueEnforcer;
import com.helger.commons.annotation.Nonempty;
import com.helger.commons.string.ToStringGenerator;

/**
 * Base class for {@link IRequestParameterHandler} implementations that support
 * special parameter names
 *
 * @author Philip Helger
 * @since 7.0.2
 */
@NotThreadSafe
public abstract class AbstractRequestParameterHandlerNamed implements IRequestParameterHandler
{
  /** The default name of the parameter selecting the current display locale */
  public static final String DEFAULT_REQUEST_PARAMETER_DISPLAY_LOCALE = "locale";

  /** The default name of the parameter selecting the current menu item */
  public static final String DEFAULT_REQUEST_PARAMETER_MENUITEM = "menuitem";

  private String m_sRequestParamNameLocale = DEFAULT_REQUEST_PARAMETER_DISPLAY_LOCALE;
  private String m_sRequestParamNameMenuItem = DEFAULT_REQUEST_PARAMETER_MENUITEM;

  public AbstractRequestParameterHandlerNamed ()
  {}

  /**
   * Check if the passed parameter name is valid according to the rules of this
   * parameter handler.
   *
   * @param sParamName
   *        The parameter name to check. May not be <code>null</code>.
   * @return <code>true</code> if the parameter is valid, <code>false</code>
   *         otherwise.
   */
  public abstract boolean isValidParameterName (@Nonnull final String sParamName);

  @Nonnull
  @Nonempty
  public final String getRequestParamNameLocale ()
  {
    return m_sRequestParamNameLocale;
  }

  @Nonnull
  public final AbstractRequestParameterHandlerNamed setRequestParamNameLocale (@Nonnull @Nonempty final String sRequestParamNameLocale)
  {
    ValueEnforcer.notEmpty (sRequestParamNameLocale, "RequestParamNameLocale");
    ValueEnforcer.isTrue (isValidParameterName (sRequestParamNameLocale),
                          () -> "Request parameter name '" + sRequestParamNameLocale + "' is invalid!");
    m_sRequestParamNameLocale = sRequestParamNameLocale;
    return this;
  }

  @Nonnull
  @Nonempty
  public final String getRequestParamNameMenuItem ()
  {
    return m_sRequestParamNameMenuItem;
  }

  @Nonnull
  public final AbstractRequestParameterHandlerNamed setRequestParamNameMenuItem (@Nonnull @Nonempty final String sRequestParamNameMenuItem)
  {
    ValueEnforcer.notEmpty (sRequestParamNameMenuItem, "RequestParamNameMenuItem");
    ValueEnforcer.isTrue (isValidParameterName (sRequestParamNameMenuItem),
                          () -> "Request parameter name '" + sRequestParamNameMenuItem + "' is invalid!");
    m_sRequestParamNameMenuItem = sRequestParamNameMenuItem;
    return this;
  }

  @Override
  public String toString ()
  {
    return new ToStringGenerator (this).append ("ParameterNameLocale", m_sRequestParamNameLocale)
                                       .append ("ParameterNameMenuItem", m_sRequestParamNameMenuItem)
                                       .getToString ();
  }
}
