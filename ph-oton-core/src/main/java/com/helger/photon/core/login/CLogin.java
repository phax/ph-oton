/*
 * Copyright (C) 2014-2023 Philip Helger (www.helger.com)
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
package com.helger.photon.core.login;

import javax.annotation.concurrent.Immutable;

import com.helger.html.css.DefaultCSSClassProvider;
import com.helger.html.css.ICSSClassProvider;

/**
 * Login constants
 *
 * @author Philip Helger
 */
@Immutable
public final class CLogin
{
  public static final String LAYOUT_AREAID_LOGIN = "login";

  public static final ICSSClassProvider CSS_CLASS_LOGIN_APPLOGO = DefaultCSSClassProvider.create ("login_applogo");
  public static final ICSSClassProvider CSS_CLASS_LOGIN_HEADER = DefaultCSSClassProvider.create ("login_appheader");
  public static final ICSSClassProvider CSS_CLASS_LOGIN_ERRORMSG = DefaultCSSClassProvider.create ("login_errormsg");

  /** The name of the field that contains the user name for the login. */
  public static final String REQUEST_ATTR_USERID = "userid";
  /** The name of the field that contains the password for the login. */
  public static final String REQUEST_ATTR_PASSWORD = "password";
  /** The name of the hidden field to be used in login. */
  public static final String REQUEST_PARAM_ACTION = "login-action";
  /**
   * The action value to be passed in a hidden field to ensure that user name
   * and password are checked. The name of the field must be
   * {@link #REQUEST_PARAM_ACTION}
   */
  public static final String REQUEST_ACTION_VALIDATE_LOGIN_CREDENTIALS = "validate-login-credentials";

  private CLogin ()
  {}
}
